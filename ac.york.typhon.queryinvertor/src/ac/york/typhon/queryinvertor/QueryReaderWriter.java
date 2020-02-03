package ac.york.typhon.queryinvertor;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class QueryReaderWriter {
	
	public ArrayList<String> readQueriesFromFile() {
		ArrayList<String> qlQueries = new ArrayList<String>();
        String fileName = "./files/queries.txt";
        String line = null;

        try {
            FileReader fileReader = new FileReader(fileName);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            while((line = bufferedReader.readLine()) != null) {
            	qlQueries.add(line);
            }   
            bufferedReader.close();         
        }
        catch(FileNotFoundException ex) {
            System.out.println("Unable to open file '" + fileName + "'");                
        }
        catch(IOException ex) {
            System.out.println("Error reading file '" + fileName + "'");                  
        }
		
        return qlQueries;
	}
	
	public void writeInverterQueriesToFile(ArrayList<String> invertedQueriesList) {
		
        String fileName = "./files/invertedQueries.txt";

        try {
            FileWriter fileWriter = new FileWriter(fileName);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            for (String invertedQuery : invertedQueriesList) {
                bufferedWriter.write(invertedQuery);
                bufferedWriter.newLine();
            }
        

            bufferedWriter.close();
        }
        catch(IOException ex) {
            System.out.println("Error writing to file '" + fileName + "'");
        }
	}
}
