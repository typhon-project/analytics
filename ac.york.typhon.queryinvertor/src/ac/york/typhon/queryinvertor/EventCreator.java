package ac.york.typhon.queryinvertor;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;

import ac.york.typhon.generator.helper.GeneratorConstants.ResourceHeader;
import ac.york.typhon.generator.helper.Utils;
import ac.york.typhon.queryinvertor.events.Event;
import ac.york.typhon.queryinvertor.events.PreEvent;

public class EventCreator {
	
	public PreEvent createPreEvent(String originalQuery, ArrayList<String> additionalQueries) {
        Date date = new Date();

		String id = Utils.generateRandomId();
		String query = originalQuery;
		Date queryTime = new Timestamp(date.getTime());
		String user = "dbUser";
		PreEvent preEvent = new PreEvent(id, query, user, queryTime, "dbUser", additionalQueries);
		
		return preEvent;
	}

}
