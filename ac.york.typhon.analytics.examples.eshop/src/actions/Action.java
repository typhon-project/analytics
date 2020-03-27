package actions;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import utils.ExecuteQueries;

public class Action extends Thread {
	
	String query;
	
	public Action(String query) {
		this.query = query;
	}

	ExecuteQueries eq = new ExecuteQueries();
	ExecuteQueries.Utils utils = eq.new Utils();

	public void run() {
		try {
			utils.executeUpdateAndReturnPostvent(query);
		} catch (Exception e) {
			System.err.println("Failed executing query");
			e.printStackTrace();
		}
	}

}
