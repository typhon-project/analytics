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
import queryGenerators.SelectProductGenerator;
import utils.ExecuteQueries;
import utils.Utilities;
import utils.ExecuteQueries.Utils;

public class BrowsingWithCommentAgent extends Agent implements Runnable {

	@Override
	public void run() {

		final int MAX_NUM_OF_PRODUCTS = 50;
		String user = this.uuid;
		// This is code from BuyerAgent
		ExecuteQueries eq = new ExecuteQueries();
		ExecuteQueries.Utils utils = eq.new Utils();

		SelectProductGenerator spg = new SelectProductGenerator();

		Map<String, String> params = new HashMap<String, String>();
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

		// Buy i products and put them in an order
		for (int i = 0; i < MAX_NUM_OF_PRODUCTS; i++) {
			params.put("seed", String.valueOf(seed));
			if (RunSimulator.goThroughPolystore) {
				try {
					utils.executeQuery(spg.generateQuery(params));
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else {
				String query = spg.generateQuery(params);

				try {
					utils.createAndPublishPostEvent(query, user);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			try {
				this.randomSleep(100, 2001);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			seed++;
		}
		
		try {
			inputStream.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
