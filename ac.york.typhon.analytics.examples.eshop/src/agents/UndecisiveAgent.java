package agents;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Random;
import java.util.UUID;

import entityUtils.BasketPopulator;
import infra.RunSimulator;
import queryGenerators.InsertOrderGenerator;
import queryGenerators.InsertOrderedProductGenerator;
import utils.ExecuteQueries;

public class UndecisiveAgent extends Agent implements Runnable {

	@Override
	public void run() {

		ExecuteQueries eq = new ExecuteQueries();
		ExecuteQueries.Utils utils = eq.new Utils();
		InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("config.properties");
		Properties appProps = new Properties();
		try {
			appProps.load(inputStream);
		} catch (IOException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}

		int seed = Integer.parseInt(appProps.get("seed").toString());
		Random r = new Random(seed);

		String productUUID = RunSimulator.allProducts.get(r.nextInt(RunSimulator.allProducts.size()));
		BasketPopulator bp = new BasketPopulator();
		String bpUUID = RunSimulator.allBaskets.get(r.nextInt(RunSimulator.allBaskets.size()));
		// FIXME: Basket UUID?
		try {
			bpUUID = bp.add(productUUID, bpUUID, seed);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		try {
			this.randomSleep(1000, 10000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			bp.remove(bpUUID, seed);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
