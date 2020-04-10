package infra;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import agents.BuyerAgent;
import agents.BuyerReviewerAgent;
import agents.ReviewerNoBuyerAgent;
import entityUtils.ProductCreator;
import entityUtils.UserCreator;
import queryGenerators.InsertAddressGenerator;
import queryGenerators.InsertUserGenerator;
import utils.ExecuteQueries;
import utils.Utilities;

public class RunSimulator {
	public static ArrayList<String> allUsers = new ArrayList<String>();
	public static ArrayList<String> allProducts = new ArrayList<String>();
	
	public static void main(String[] args) throws Exception {
		
		init();
		
		InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("config.properties");
		Properties appProps = new Properties();
		appProps.load(inputStream);
		int numOfBuyerAgents = Integer.parseInt(appProps.getProperty("num_of_buyer_agents"));
		ArrayList<Thread> allBuyerAgents = new ArrayList<Thread>();
		for (int i=0; i<numOfBuyerAgents; i++) {
			allBuyerAgents.add(new Thread(new BuyerAgent()));
		}
		for (Thread agent : allBuyerAgents) {
			agent.start();
		}
		
		int numOfBuyerReviewerAgents = Integer.parseInt(appProps.getProperty("num_of_buyer_reviewer_agents"));
		ArrayList<Thread> allBuyerReviewerAgents = new ArrayList<Thread>();
		for (int i=0; i<numOfBuyerReviewerAgents; i++) {
			allBuyerReviewerAgents.add(new Thread(new BuyerReviewerAgent()));
		}
		for (Thread agent : allBuyerReviewerAgents) {
			agent.start();
		}
		Thread at3 = new Thread(new ReviewerNoBuyerAgent());
		at3.start();
	}
	
	public static void init() throws Exception {
		InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("config.properties");
		Properties appProps = new Properties();
		appProps.load(inputStream);
		ExecuteQueries eq = new ExecuteQueries();
		ExecuteQueries.Utils utils = eq.new Utils();
		// Create Users
		System.out.println("Create users? " + appProps.getProperty("generate_users"));
		if(Boolean.parseBoolean(appProps.getProperty("generate_users"))) {
			System.out.println("Started user creation");
			UserCreator uc = new UserCreator();
			int seed = Integer.parseInt(appProps.getProperty("seed"));
			for (int i=0; i < Integer.parseInt(appProps.getProperty("num_of_users")); i++) {
				String userId = uc.create(seed);
				allUsers.add(userId);
				seed++;
			}
			System.out.println("User creation finished.");
		}

		System.out.println("Create products? " + appProps.getProperty("generate_products"));
		
		// Create Products
		if(Boolean.parseBoolean(appProps.getProperty("generate_products"))) {
			// Start by creating Categories
			System.out.println("Started product creation");
			ProductCreator pc = new ProductCreator();
			ArrayList<String> categoryIds = ProductCreator.createCategories();
			// Create products
			int seed = Integer.parseInt(appProps.getProperty("seed"));
			for (int i=0; i < Integer.parseInt(appProps.getProperty("num_of_products")); i++) {
				String productId = pc.create(seed, categoryIds);
				allProducts.add(productId);
				seed++;
			}
			System.out.println("Product creation finished.");
		}
	}
	
	
}
