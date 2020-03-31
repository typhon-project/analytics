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

public class BuyerReviewerAgent extends Agent implements Runnable {

	@Override
	public void run() {

		ExecuteQueries eq = new ExecuteQueries();
		ExecuteQueries.Utils utils = eq.new Utils();
		Random r = new Random();
		
		System.out.println("Buyer Reviewer agent: " + this.uuid);
		// Buy product
		Map<String, String> params = new HashMap<String, String>();
		params.put("userId", this.uuid);
		params.put("productId", RunSimulator.allProducts.get(r.nextInt(RunSimulator.allProducts.size())));

		try {
			InsertOrderProductGenerator iopg = new InsertOrderProductGenerator();
//			utils.executeUpdateAndReturnPostvent(irg.generateQuery(null));
			System.out.println(iopg.generateQuery(params));
			this.randomSleep(1000, 5000);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
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
