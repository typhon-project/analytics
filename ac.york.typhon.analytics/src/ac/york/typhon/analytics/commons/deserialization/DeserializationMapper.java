package ac.york.typhon.analytics.commons.deserialization;

import java.util.ArrayList;
import java.util.HashMap;
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
import engineering.swat.typhonql.ast.Request;
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
				postEvent.setJsonquery(jsonquery);
				String query = jsonquery.getQuery();
				//
				if (jsonquery.getBoundRows() != null) {
					// FIXME update query placeholder values with actual values
					
				}
				//
				String rs = postEvent.getResultSet();
				String invertedQuery = postEvent.getPreEvent().getInvertedQuery();
				String invertedResultSet = postEvent.getInvertedQueryResultSet();
				boolean resultSetNeeded = postEvent.getPreEvent().isResultSetNeeded();
				boolean invertedResultSetNeeded = postEvent.getPreEvent().isInvertedNeeded();
				//
				System.out.println((jsonquery));
				Request request = TyphonQLASTParser.parseTyphonQLRequest((query).toCharArray());
				String clause = null;
				try {
					clause = request.getQry().getWhere().yieldTree();
				} catch (Exception e) {
					// no where clause
				}

				if (request.hasStm() && request.getStm().isInsert()) {
					InsertDeserializer id = new InsertDeserializer();
					ArrayList<Entity> insertedEntities = id.deserialize(query, null, rs, null, resultSetNeeded, null);
					// System.out.println(insertedEntities);
					InsertCommand c = new InsertCommand();
					c.setAffected(discoverAffected(request));
					c.setInsertedEntities(insertedEntities);
					c.setClause(clause);
					postEvent.addCommand(c);
				} else if (request.hasStm() && request.getStm().isDelete()) {
					DeleteDeserializer dd = new DeleteDeserializer();
					List<Entity> deletedEntities = dd.deserialize(query, invertedQuery, rs, invertedResultSet,
							resultSetNeeded, invertedResultSetNeeded);
					// System.out.println(deletedEntities);
					DeleteCommand c = new DeleteCommand();
					c.setAffected(discoverAffected(request));
					c.setDeletedEntities(deletedEntities);
					c.setClause(clause);
					postEvent.addCommand(c);
				} else if (request.hasStm() && request.getStm().isUpdate()) {
					UpdateDeserializer up = new UpdateDeserializer();
					List<Entity> updatedEntities = up.deserialize(query, invertedQuery, rs, invertedResultSet,
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
					List<Entity> selectedEntities = sd.deserialize(query, null, rs, null, resultSetNeeded, null);
					// System.out.println(">>"+selectedEntities);
					SelectCommand c = new SelectCommand();
					c.setAffected(discoverAffected(request));
					c.setReturnedEntities(selectedEntities);
					c.setClause(clause);
					postEvent.addCommand(c);
				}
				//
				out.collect(postEvent);

			}

		}

		out.collect(event);

	}

	private Map<String, List<String>> discoverAffected(Request request) {

		Map<String, List<String>> ret = new HashMap<>();

		// parse the query and discover the entity and the fields in it that are
		// affected
		// NB: for views (such as select with >1 entity you need to map the fields with
		// their respective entities using vid

		return ret;
	}

}
