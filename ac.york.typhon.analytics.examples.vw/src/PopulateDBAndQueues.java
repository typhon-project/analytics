import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import mains.ExecuteQueries;
import mains.ExecuteQueries.Utils;

public class PopulateDBAndQueues {

	public static void main(String[] args) throws Exception {

		ExecuteQueries eq = new ExecuteQueries();
		ExecuteQueries.Utils utils = eq.new Utils();

		try {
			File myObj = new File("files/mixedQueries.tql");
			Scanner myReader = new Scanner(myObj);
			while (myReader.hasNextLine()) {
				String data = myReader.nextLine();
				utils.executeUpdateAndReturnPostvent(data);
			}
			myReader.close();
		} catch (FileNotFoundException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}
	}

}
