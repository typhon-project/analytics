package ac.york.typhon.analytics.commons.deserialization;

import org.apache.flink.api.common.functions.MapFunction;

import ac.york.typhon.analytics.commons.datatypes.events.Event;
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
			String query = event.getQuery();
			Request request = null;
			try {
				request = TyphonQLASTParser.parseTyphonQLRequest((query).toCharArray());
			} catch (ASTConversionException e1) {
				e1.printStackTrace();
			}
			Utilities util = new Utilities();
			String invertedQuery = util.createInvertedSelect(request);
			System.out.println(invertedQuery);
			((PreEvent) event).setInvertedQuery(invertedQuery);
		}

		return event;

	}

}
