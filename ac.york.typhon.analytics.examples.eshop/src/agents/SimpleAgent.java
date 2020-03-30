package agents;

import java.util.Random;

import queryGenerators.InsertReviewGenerator;
import utils.ExecuteQueries;
import utils.Utilities;
import utils.ExecuteQueries.Utils;

public class SimpleAgent extends Agent implements Runnable {

	@Override
	public void run() {

		ExecuteQueries eq = new ExecuteQueries();
		ExecuteQueries.Utils utils = eq.new Utils();

		// Number of products navigated
		int numOfProds = Utilities.getRandomNumberInRange(5, 10);

		System.out.println("SA 1: " + this.uuid);
		InsertReviewGenerator irg = new InsertReviewGenerator();
		for (int i = 0; i < 5; i++) {
			try {
//			utils.executeUpdateAndReturnPostvent(irg.generateQuery(null));
				System.out.println("Agent 1 is about to sleep...");
				this.randomSleep(1000, 5000);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
