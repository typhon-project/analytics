package ac.york.typhon.analytics.commons.deserialization;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import org.apache.flink.api.common.functions.RichFlatMapFunction;
import org.apache.flink.configuration.Configuration;
import org.apache.flink.util.Collector;

import com.fasterxml.jackson.databind.ObjectMapper;

import ac.york.typhon.analytics.commons.datatypes.commands.DeleteCommand;
import ac.york.typhon.analytics.commons.datatypes.commands.InsertCommand;
import ac.york.typhon.analytics.commons.datatypes.commands.SelectCommand;
import ac.york.typhon.analytics.commons.datatypes.commands.UpdateCommand;
import ac.york.typhon.analytics.commons.datatypes.events.DeserializedPostEvent;
import ac.york.typhon.analytics.commons.datatypes.events.Entity;
import ac.york.typhon.analytics.commons.datatypes.events.Event;
import ac.york.typhon.analytics.commons.datatypes.events.JSONQuery;
import ac.york.typhon.analytics.commons.datatypes.events.PostEvent;
import engineering.swat.typhonql.ast.Binding;
import engineering.swat.typhonql.ast.KeyVal;
import engineering.swat.typhonql.ast.Obj;
import engineering.swat.typhonql.ast.Query;
import engineering.swat.typhonql.ast.Request;
import engineering.swat.typhonql.ast.Result;
import engineering.swat.typhonql.ast.Statement;
import engineering.swat.typhonql.ast.TyphonQLASTParser;

public class DeserializationMapper extends RichFlatMapFunction<Event, Event> {

	private static final long serialVersionUID = -8388259253683396770L;

	ClassLoader engineClassLoader;

	@Override
	public void open(Configuration parameters) throws Exception {
		super.open(parameters);
		engineClassLoader = getRuntimeContext().getUserCodeClassLoader();
	}

	public static String resolveQuery(JSONQuery jsonquery, int i) {
		String query = jsonquery.getQuery();

		for (int j = 0; j < jsonquery.getParameterNames().length; j++) {
			String name = jsonquery.getParameterNames()[j];
			String value = ((List) jsonquery.getBoundRows()[i]).get(j).toString();
			// XXX converting json value to ql value -- WIP
			value = value.replace("POLYGON", "#polygon").replace("POINT", "#point");
			// XXX converting datetime ie: 2020-10-26T16:00:05.100Z
			// $2020-10-26T16:00:05Z$
			if (Pattern.matches("\\d{4}-\\d{2}-\\d{2}T\\d{2}:\\d{2}:\\d{2}.*", value)) {
				if (value.contains("."))
					value = value.substring(0, value.indexOf(".")) + "Z";
				value = "$" + value + "$";
			}
			if (Pattern.matches("\\d{4}-\\d{2}-\\d{2}", value)) {
				value = "$" + value + "$";
			}
			// XXX add back quotes for strings
			if (jsonquery.getParameterTypes()[j].equals("string"))
				value = "\"" + value + "\"";
			if (jsonquery.getParameterTypes()[j].equals("uuid"))
				value = "#" + value;

			query = query.replace("??" + name, value);
		}
		return query;
	}

	@Override
	public void flatMap(Event event, Collector<Event> out) throws Exception {

		if (event instanceof PostEvent && !(event instanceof DeserializedPostEvent)) {

			String originalquery = ((PostEvent) event).getPreEvent().getQuery();
			JSONQuery jsonquery = new ObjectMapper().readValue(originalquery, JSONQuery.class);

			int count = jsonquery.getBoundRows() == null ? 1 : jsonquery.getBoundRows().length;

			for (int i = 0; i < count; i++) {

				DeserializedPostEvent postEvent = new DeserializedPostEvent((PostEvent) event);
				//

				try {

					postEvent.setJsonquery(jsonquery);
					// String query = jsonquery.getQuery();
					//
					if (jsonquery.getBoundRows() != null) {

						jsonquery.setResolvedQuery(resolveQuery(jsonquery, i));
						//
					}
					//
					String rs = postEvent.getResultSet();

					String iq = postEvent.getPreEvent().getInvertedQuery();
					JSONQuery invertedQuery = null;
					if (iq != null && iq.length() > 0)
						invertedQuery = new ObjectMapper().readValue(iq, JSONQuery.class);

					String invertedResultSet = postEvent.getInvertedQueryResultSet();
					boolean resultSetNeeded = postEvent.getPreEvent().isResultSetNeeded();
					boolean invertedResultSetNeeded = postEvent.getPreEvent().isInvertedNeeded();
					//
					if (Utilities.debug)
						System.out.println((jsonquery));
					//
					Request request = TyphonQLASTParser.parseTyphonQLRequest((jsonquery.getQuery()).toCharArray());
					String clause = null;
					try {
						clause = request.getQry().getWhere().yieldTree();
					} catch (Exception e) {
						// no where clause
					}

					if (request.hasStm() && request.getStm().isInsert()) {
						InsertDeserializer id = new InsertDeserializer(engineClassLoader);
						List<Entity> insertedEntities = id.deserialize(jsonquery, (JSONQuery) null, rs, null,
								resultSetNeeded, null);
						// System.out.println(insertedEntities);
						InsertCommand c = new InsertCommand();
						c.setAffected(discoverAffected(request));
						c.setInsertedEntities(insertedEntities);
						c.setClause(clause);
						postEvent.addCommand(c);
					} else if (request.hasStm() && request.getStm().isDelete()) {
						DeleteDeserializer dd = new DeleteDeserializer(engineClassLoader);
						List<Entity> deletedEntities = dd.deserialize(jsonquery, invertedQuery, rs, invertedResultSet,
								resultSetNeeded, invertedResultSetNeeded);
						// System.out.println(deletedEntities);
						DeleteCommand c = new DeleteCommand();
						c.setAffected(discoverAffected(request));
						c.setDeletedEntities(deletedEntities);
						c.setClause(clause);
						postEvent.addCommand(c);
					} else if (request.hasStm() && request.getStm().isUpdate()) {
						UpdateDeserializer up = new UpdateDeserializer(engineClassLoader);
						List<Entity> updatedEntities = up.deserialize(jsonquery, invertedQuery, rs, invertedResultSet,
								resultSetNeeded, invertedResultSetNeeded);
						// System.out.println(updatedEntities);
						UpdateCommand c = new UpdateCommand();
						c.setAffected(discoverAffected(request));
						c.setUpdatedEntities(updatedEntities);
						c.setClause(clause);
						postEvent.addCommand(c);
					} else {
						// It is a Select.
						SelectDeserializer sd = new SelectDeserializer(engineClassLoader);
						List<Entity> selectedEntities = sd.deserialize(jsonquery, null, rs, null, resultSetNeeded,
								null);
						// System.out.println(">>"+selectedEntities);
						SelectCommand c = new SelectCommand();
						c.setAffected(discoverAffected(request));
						c.setReturnedEntities(selectedEntities);
						c.setClause(clause);
						postEvent.addCommand(c);
					}
					//

				} catch (Exception e) {
					e.printStackTrace();
				}

				out.collect(postEvent);

			}

		} else
			out.collect(event);

	}

	private Map<String, List<String>> discoverAffected(Request request) {

		Map<String, List<String>> ret = new HashMap<>();

		// parse the query and discover the entity and the fields in it that are
		// affected

		String entityName = null;
		List<String> fields = new LinkedList<String>();

		// inserts/deletes/updates
		if (request.hasStm()) {

			// System.out.println(request.getStm().getBinding());
			Statement stm = request.getStm();

			// delete/update
			if (request.getStm().hasBinding()) {
				entityName = request.getStm().getBinding().getEntity().yieldTree();

				if (stm.hasKeyVals()) {
					for (KeyVal e : stm.getKeyVals())
						fields.add(e.getKey().yieldTree());
				}

				ret.put(entityName, fields);

				// insert
			} else {
				for (Obj o : request.getStm().getObjs()) {
					entityName = o.getEntity().yieldTree();

					for (KeyVal k : o.getKeyVals())
						if (k.hasKey())
							fields.add(k.getKey().yieldTree());
					ret.put(entityName, fields);
				}
			}

		} else {

			Query query = request.getQry();
			for (Binding b : query.getBindings()) {
				for (Result r : query.getSelected()) {
					if (r.getExpr().getVar().equals(b.getVar())) {
						String field = r.getExpr().yieldTree();
						field = field.substring(field.indexOf(".") + 1);
						fields.add(field);
					}
				}
				ret.put(b.getEntity().yieldTree(), fields);
				fields = new LinkedList<String>();
			}
		}
		if (Utilities.debug)
			System.out.println("> " + ret);

		return ret;
	}

}
