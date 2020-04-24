package agents;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Random;

import infra.RunSimulator;
import queryGenerators.InsertOrderGenerator;
import queryGenerators.InsertOrderedProductGenerator;
import queryGenerators.InsertReviewGenerator;
import utils.ExecuteQueries;
import utils.Utilities;
import utils.ExecuteQueries.Utils;

public class BuyerReviewerAgent extends Agent implements Runnable {

	@Override
	public void run() {

		final int NUM_OF_PRODUCTS = 3;

		// This is code from BuyerAgent
		ExecuteQueries eq = new ExecuteQueries();
		ExecuteQueries.Utils utils = eq.new Utils();

		InsertOrderedProductGenerator iopg = new InsertOrderedProductGenerator();
		InsertOrderGenerator iog = new InsertOrderGenerator();

		Map<String, String> params = new HashMap<String, String>();
		ArrayList<String> orderedProducts = new ArrayList<String>();
		params.put("userId", this.uuid);
		InputStream inputStream = Thread.currentThread().getContextClassLoader()
				.getResourceAsStream("config.properties");
		Properties appProps = new Properties();
		try {
			appProps.load(inputStream);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		int seed = Integer.parseInt(appProps.get("seed").toString());
		Random r = new Random(seed);
		
		// Place a number of orders
		int numOfOrders = r.nextInt(5);
		for (int y = 0; y < numOfOrders; y++) {
			// Buy i products and put them in an order
			for (int i = 0; i < NUM_OF_PRODUCTS; i++) {
				params.put("seed", String.valueOf(seed));
				try {
//			utils.executeUpdateAndReturnPostvent(irg.generateQuery(null));
					String orderedProductQuery = iopg.generateQuery(params);
					String productId = orderedProductQuery.split("product: \\[\\#")[1].split("]")[0];
//					utils.createAndPublishPostEvent(orderedProductQuery);
					utils.executeUpdate(orderedProductQuery);
					// FIXME: Should pick randomly (with a seed) from all products
					orderedProducts.add(productId);
					this.randomSleep(1000, 5000);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				seed++;
			}
			params.put("orderedProducts", orderedProducts.toString());
			params.put("productToReviewId", orderedProducts.get(r.nextInt(NUM_OF_PRODUCTS - 1)));
			String orderQuery = iog.generateQuery(params);
			try {
//				utils.createAndPublishPostEvent(orderQuery);
				utils.executeUpdate(orderQuery);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			// Write review
			try {
				InsertReviewGenerator irg = new InsertReviewGenerator();
//				utils.createAndPublishPostEvent(irg.generateQuery(params));
				utils.executeUpdate(irg.generateQuery(params));
				this.randomSleep(1000, 5000);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		// Sleep a random amount of time between each buy
		try {
			this.randomSleep(1000, 5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
