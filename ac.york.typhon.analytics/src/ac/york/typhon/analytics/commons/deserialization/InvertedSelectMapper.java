package ac.york.typhon.analytics.commons.deserialization;

import org.apache.flink.api.common.functions.MapFunction;

import com.fasterxml.jackson.databind.ObjectMapper;

import ac.york.typhon.analytics.commons.datatypes.events.Event;
import ac.york.typhon.analytics.commons.datatypes.events.JSONQuery;
import ac.york.typhon.analytics.commons.datatypes.events.PreEvent;
import engineering.swat.typhonql.ast.ASTConversionException;
import engineering.swat.typhonql.ast.Request;
import engineering.swat.typhonql.ast.TyphonQLASTParser;

public class InvertedSelectMapper implements MapFunction<Event, Event> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4511017031461532594L;

	@Override
	public Event map(Event event) throws Exception {

		if (event instanceof PreEvent) {

			//
			String originalquery = event.getQuery();
			JSONQuery jsonquery = new ObjectMapper().readValue(originalquery, JSONQuery.class);
			String query = jsonquery.getQuery();
			// XXX if this is a prepared statement we disable needing inverted selects as
			// they are not supported
			if (jsonquery.getBoundRows() != null)
				((PreEvent) event).setInvertedNeeded(false);
			//

			if (((PreEvent) event).isInvertedNeeded()) {
				Request request = null;
				try {
					request = TyphonQLASTParser.parseTyphonQLRequest((query).toCharArray());
				} catch (ASTConversionException e1) {
					System.err.println("cannot create request:");
					e1.printStackTrace();
				}
				Utilities util = new Utilities();
				String invertedQuery = util.createInvertedSelect(request);
				if(Utilities.debug)
					System.out.println(invertedQuery);
				((PreEvent) event).setInvertedQuery(invertedQuery);
			}

		}

		return event;

	}

}
