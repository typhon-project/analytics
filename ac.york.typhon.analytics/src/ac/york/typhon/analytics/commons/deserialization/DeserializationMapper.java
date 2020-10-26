package ac.york.typhon.analytics.commons.deserialization;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.flink.api.common.functions.FlatMapFunction;
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

public class DeserializationMapper implements FlatMapFunction<Event, Event> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8388259253683396770L;

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

						String query = jsonquery.getQuery();

						for (int j = 0; j < jsonquery.getParameterNames().length; j++) {
							String name = jsonquery.getParameterNames()[j];
							String value = ((List) jsonquery.getBoundRows()[i]).get(j).toString();
							// XXX converting json value to ql value -- WIP
							value = value.replace("POLYGON", "#polygon").replace("POINT", "#point");
							//
							query = query.replace("??" + name, value);
						}

						jsonquery.setResolvedQuery(query);
						//
					}
					//
					String rs = postEvent.getResultSet();

					String iq = postEvent.getPreEvent().getInvertedQuery();
					JSONQuery invertedQuery = null;
					if (iq != null)
						invertedQuery = new ObjectMapper().readValue(iq, JSONQuery.class);

					String invertedResultSet = postEvent.getInvertedQueryResultSet();
					boolean resultSetNeeded = postEvent.getPreEvent().isResultSetNeeded();
					boolean invertedResultSetNeeded = postEvent.getPreEvent().isInvertedNeeded();
					//
					if(Utilities.debug)
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
						InsertDeserializer id = new InsertDeserializer();
						List<Entity> insertedEntities = id.deserialize(jsonquery, (JSONQuery) null, rs, null,
								resultSetNeeded, null);
						// System.out.println(insertedEntities);
						InsertCommand c = new InsertCommand();
						c.setAffected(discoverAffected(request));
						c.setInsertedEntities(insertedEntities);
						c.setClause(clause);
						postEvent.addCommand(c);
					} else if (request.hasStm() && request.getStm().isDelete()) {
						DeleteDeserializer dd = new DeleteDeserializer();
						List<Entity> deletedEntities = dd.deserialize(jsonquery, invertedQuery, rs, invertedResultSet,
								resultSetNeeded, invertedResultSetNeeded);
						// System.out.println(deletedEntities);
						DeleteCommand c = new DeleteCommand();
						c.setAffected(discoverAffected(request));
						c.setDeletedEntities(deletedEntities);
						c.setClause(clause);
						postEvent.addCommand(c);
					} else if (request.hasStm() && request.getStm().isUpdate()) {
						UpdateDeserializer up = new UpdateDeserializer();
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
						SelectDeserializer sd = new SelectDeserializer();
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
						fields.add(k.getKey().yieldTree());
					ret.put(entityName, fields);
				}
			}

		} else {

			Query query = request.getQry();
			for (Binding b : query.getBindings()) {
				System.err.println(b.getEntity().getString());
				System.err.println(b.getVar());
				for (Result r : query.getSelected()) {
					System.err.print("--- ");
					System.err.print(r.getExpr().getVar());
					System.err.println(" "+r.getExpr().yieldTree());
					if (r.getExpr().getVar().equals(b.getVar())) {
						System.err.println("<>");
						String field = r.getExpr().yieldTree();
						field = field.substring(field.indexOf(".") + 1);
						fields.add(field);
					}
				}
				ret.put(b.getEntity().yieldTree(), fields);
				fields = new LinkedList<String>();
			}
		}
		if(Utilities.debug)
			System.out.println("> " + ret);

		return ret;
	}

}
