import java.io.File;

import utilities.Utilities;

public class PopulateDBAndQueues {

	public static void main(String[] args) throws Exception {

		final File folder = new File("files/VolkswagenSimulatedData/");
		for (File file : folder.listFiles()) {
			if (file.getName().contains("ESP")) {
				Utilities.fromESPJsonToQuery(file);
			} else if (file.getName().contains("Metadata_")) {
				Utilities.fromMetadataJsonToQuery(file);
			} else {
				Utilities.fromVehicleWeatherCSVToQuery(file);
			}
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
