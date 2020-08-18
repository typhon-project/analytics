package analytics.utilities;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ResultsCountChecking {

	public static void main(String[] args) {

		int total = 0;
		try {
			File myObj = new File("resources/results.txt");
			Scanner myReader = new Scanner(myObj);
			while (myReader.hasNextLine()) {
				String data = myReader.nextLine();
				int value;
				
				if (data.contains("times")) {
					// Spammers
					String[] splitted = data.split("\\(")[1].split("\\s+");
//					System.out.println(splitted[0]);
					value = Integer.parseInt(splitted[0]);
					total += value;
				} else {
					// Good
					String[] splitted = data.split(",")[2].split("\\)");
//					System.out.println(splitted[0]);
					value = Integer.parseInt(splitted[0]);
					total += value;
				}
			}
			myReader.close();
		} catch (FileNotFoundException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}
		System.out.println("Total: " + total);

	}

}
