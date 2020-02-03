package ac.york.typhon.queryinvertor;

import java.util.ArrayList;

import ac.york.typhon.queryinvertor.events.PreEvent;

public class QueryInvertor {

	public static void main(String[] args) {
		QueryReaderWriter qrw = new QueryReaderWriter();
		EventCreator eventCreator = new EventCreator();
		ArrayList<String> queriesList = qrw.readQueriesFromFile();
		ArrayList<String> allInvertedQueriesList = new ArrayList<String>();
		ArrayList<String> invertedQueriesListForCurrentQuery = new ArrayList<String>();
		ArrayList<PreEvent> allPreEventsList = new ArrayList<PreEvent>();

		for (String query: queriesList) {
			invertedQueriesListForCurrentQuery.clear();
			System.out.println(query);
			String invertedQuery = "";
			// TODO: Invert queries here and create preEvent
			invertedQueriesListForCurrentQuery.add(invertedQuery);
			PreEvent preEvent = eventCreator.createPreEvent(query, invertedQueriesListForCurrentQuery);

			allPreEventsList.add(preEvent);
			allInvertedQueriesList.add(invertedQuery);
		}

		
		qrw.writeInverterQueriesToFile(allInvertedQueriesList);
		
		
    }
}
