package agents;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import queryGenerators.InsertOrderProductGenerator;
import queryGenerators.InsertReviewGenerator;
import utils.ExecuteQueries;
import utils.Utilities;
import utils.ExecuteQueries.Utils;

public class BuyerAgent extends Agent implements Runnable {

	@Override
	public void run() {

		ExecuteQueries eq = new ExecuteQueries();
		ExecuteQueries.Utils utils = eq.new Utils();

		System.out.println("Buyer agent: " + this.uuid);
		InsertOrderProductGenerator iopg = new InsertOrderProductGenerator();
		Map<String,String> params = new HashMap<String, String>();
		params.put("userId", this.uuid);
		for (int i = 0; i < 1; i++) {
			try {
//			utils.executeUpdateAndReturnPostvent(irg.generateQuery(null));
				System.out.println(iopg.generateQuery(params));
				this.randomSleep(1000, 5000);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
