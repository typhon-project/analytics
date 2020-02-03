package ac.york.typhon.queryinvertor;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class QueryInvertor {
	
	public static void main(String[] args) {
		QueryReaderWriter qrw = new QueryReaderWriter();
		ArrayList<String> queriesList = qrw.readQueriesFromFile();
		ArrayList<String> invertedQueriesList = new ArrayList<String>();

		for (String query: queriesList) {
			System.out.println(query);
			String invertedQuery = "";
			// TODO: Invert queries here
			invertedQueriesList.add(invertedQuery);
		}

		
		qrw.writeInverterQueriesToFile(invertedQueriesList);
    }
}
