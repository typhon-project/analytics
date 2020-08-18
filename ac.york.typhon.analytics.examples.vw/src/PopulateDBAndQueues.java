import java.io.File;

import mains.ExecuteQueries;
import mains.ExecuteQueries.Utils;
import utilities.Utilities;

public class PopulateDBAndQueues {

	public static void main(String[] args) throws Exception {

		ExecuteQueries eq = new ExecuteQueries();
		final File folder = new File("files/VolkswagenSimulatedData/");
		for (File file : folder.listFiles()) {
			if (file.getName().contains("ESP")) {
				String query = Utilities.fromESPJsonToQuery(file);
				Utilities.executeUpdate(query);
			} 
//			else if (file.getName().contains("Metadata_")) {
//				Utilities.fromMetadataJsonToQuery(file);
//			} else {
//				Utilities.fromVehicleWeatherCSVToQuery(file);
//			}
		}

//
//		ExecuteQueries eq = new ExecuteQueries();
//		ExecuteQueries.Utils utils = eq.new Utils();
//
//		try {
//			File myObj = new File("files/mixedQueries.tql");
//			Scanner myReader = new Scanner(myObj);
//			while (myReader.hasNextLine()) {
//				String data = myReader.nextLine();
//				utils.executeUpdateAndReturnPostvent(data);
//			}
//			myReader.close();
//		} catch (FileNotFoundException e) {
//			System.out.println("An error occurred.");
//			e.printStackTrace();
//		}
	}

	public static void listFilesForFolder(final File folder) {

	}

}
