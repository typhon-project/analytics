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
import queryGenerators.InsertOrderGeneratorToPre;
import queryGenerators.InsertOrderedProductGenerator;
import utils.ExecuteQueries;

public class BuyerAgentToPre extends Agent implements Runnable {

	@Override
	public void run() {

		ExecuteQueries eq = new ExecuteQueries();
		ExecuteQueries.Utils utils = eq.new Utils();

		InsertOrderedProductGenerator iopg = new InsertOrderedProductGenerator();
		InsertOrderGeneratorToPre iog = new InsertOrderGeneratorToPre();

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
		for (int y = 0; y < 75; y++) {
			// Buy i products and put them in an order
			for (int i = 0; i < 1; i++) {
				orderedProducts.clear();
				params.put("seed", String.valueOf(seed));
				try {
					String orderedProductQuery = iopg.generateQuery(params);
					if (RunSimulator.goThroughPolystore) {
						// TODO: Write the code, the following might be wrong, not tested.
						// utils.executeUpdateAndReturnPostvent(irg.generateQuery(null));
					} else {
						utils.createAndPublishPreEvent(orderedProductQuery);
						// FIXME: Should pick randomly (with a seed) from all products
						orderedProducts.add(String.valueOf(seed));
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				seed++;
			}
			params.put("orderedProducts", orderedProducts.toString());
			String orderQuery = iog.generateQuery(params);
			try {
				if (RunSimulator.goThroughPolystore) {
					// TODO: Write the code
				} else {
					utils.createAndPublishPreEvent(orderQuery);
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				this.randomSleep(100, 101);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		try {
			inputStream.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
