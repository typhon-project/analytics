package agents;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import infra.RunSimulator;
import queryGenerators.InsertOrderProductGenerator;
import queryGenerators.InsertReviewGenerator;
import utils.ExecuteQueries;
import utils.Utilities;
import utils.ExecuteQueries.Utils;

public class ReviewerNoBuyerAgent extends Agent implements Runnable {

	@Override
	public void run() {

		ExecuteQueries eq = new ExecuteQueries();
		ExecuteQueries.Utils utils = eq.new Utils();
		Random r = new Random();
		
		System.out.println("Reviewer (no buyer) agent: " + this.uuid);
		Map<String, String> params = new HashMap<String, String>();
		params.put("userId", this.uuid);

		// Write review
		try {
			InsertReviewGenerator irg = new InsertReviewGenerator();
//			utils.executeUpdateAndReturnPostvent(irg.generateQuery(null));
			System.out.println(irg.generateQuery(params));
			this.randomSleep(1000, 5000);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
