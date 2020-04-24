package ac.york.typhon.analytics.commons.deserialization;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.flink.api.common.functions.MapFunction;

import ac.york.typhon.analytics.commons.datatypes.commands.Database;
import ac.york.typhon.analytics.commons.datatypes.commands.DeleteCommand;
import ac.york.typhon.analytics.commons.datatypes.commands.InsertCommand;
import ac.york.typhon.analytics.commons.datatypes.commands.SelectCommand;
import ac.york.typhon.analytics.commons.datatypes.commands.UpdateCommand;
import ac.york.typhon.analytics.commons.datatypes.events.DeserializedPostEvent;
import ac.york.typhon.analytics.commons.datatypes.events.Entity;
import ac.york.typhon.analytics.commons.datatypes.events.Event;
import ac.york.typhon.analytics.commons.datatypes.events.PostEvent;
import engineering.swat.typhonql.ast.Request;
import engineering.swat.typhonql.ast.TyphonQLASTParser;

public class DeserializationMapper implements MapFunction<Event, Event> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8388259253683396770L;

	@Override
	public Event map(Event event) throws Exception {

		if (event instanceof PostEvent &&  !(event instanceof DeserializedPostEvent)) {
			//TODO add clause and targetdb

			DeserializedPostEvent postEvent = new DeserializedPostEvent((PostEvent) event);
			String query = postEvent.getQuery();
			String rs = postEvent.getResultSet();
			String invertedQuery = postEvent.getPreEvent().getInvertedQuery();
			String invertedResultSet = postEvent.getInvertedQueryResultSet();
			//
			Request request = TyphonQLASTParser.parseTyphonQLRequest((query).toCharArray());
			

			if (request.hasStm() && request.getStm().isInsert()) {
				InsertDeserializer id = new InsertDeserializer();
				ArrayList<Entity> insertedEntities = id.deserialize(query, null, rs, null);
				//System.out.println(insertedEntities);
				InsertCommand c = new InsertCommand();
				c.setAffected(discoverAffected(request));
				c.setInsertedEntities(insertedEntities);
				c.setClause("this is the where of the query");
				c.setTargetDb(new Database()//"do we need to check the tml here?"
						);
				postEvent.addCommand(c);
			} else if (request.hasStm() && request.getStm().isDelete()) {
				DeleteDeserializer dd = new DeleteDeserializer();
				ArrayList<Entity> deletedEntities = dd.deserialize(query, invertedQuery, rs, invertedResultSet);
				//System.out.println(deletedEntities);
				DeleteCommand c = new DeleteCommand();
				c.setAffected(discoverAffected(request));
				c.setDeletedEntities(deletedEntities);
				c.setClause("this is the where of the query");
				c.setTargetDb(new Database()//"do we need to check the tml here?"
						);
				postEvent.addCommand(c);
			} else if (request.hasStm() && request.getStm().isUpdate()) {
				//FIXME: REMOVE when the resultset is populated properly by clms
				ExecuteQueries eq = new ExecuteQueries();
				ExecuteQueries.Utils utils = eq.new Utils();
				rs = utils.executeQuery(query);
				//System.out.println(">>>"+rs);
				//
				//FIXME: REMOVE when the resultset is populated properly by clms
				rs = utils.executeQuery(invertedQuery);
				//System.out.println(">>>"+rs);
				//
				UpdateDeserializer up = new UpdateDeserializer();
				ArrayList<Entity> updatedEntities = up.deserialize(query, invertedQuery, rs, invertedResultSet);
				//System.out.println(updatedEntities);
				UpdateCommand c = new UpdateCommand();
				c.setAffected(discoverAffected(request));
				c.setUpdatedEntities(updatedEntities);
				c.setClause("this is the where of the query");
				c.setTargetDb(new Database()//"do we need to check the tml here?"
						);
				postEvent.addCommand(c);
			} else {
				// It is a Select.
				if (request.hasStm() && request.getQry().getBindings().size() > 1) {
					// TODO: This is a "join". We need to implement a "View" with slots
				} else {
					//FIXME: REMOVE when the resultset is populated properly by clms
					ExecuteQueries eq = new ExecuteQueries();
					ExecuteQueries.Utils utils = eq.new Utils();
					rs = utils.executeQuery(query);
					//System.out.println(">>>"+rs);
					//
					SelectDeserializer sd = new SelectDeserializer();
					ArrayList<Entity> selectedEntities = sd.deserialize(query, null, rs, null);
					//System.out.println(">>"+selectedEntities);
					SelectCommand c = new SelectCommand();
					c.setAffected(discoverAffected(request));
					c.setReturnedEntities(selectedEntities);
					c.setClause("this is the where of the query");
					c.setTargetDb(new Database()//"do we need to check the tml here?"
							);
					postEvent.addCommand(c);
				}

			}
			//
			return postEvent;
			
		}

		return event;

	}

	private Map<String, List<String>> discoverAffected(Request request) {

		Map<String, List<String>> ret = new HashMap<>();
		
		//
		// TODO populate affected types + fields		
		//
		
		return ret;
	}

}
