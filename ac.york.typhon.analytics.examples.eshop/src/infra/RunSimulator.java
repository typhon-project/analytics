package infra;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import agents.BuyerAgent;
import agents.BuyerReviewerAgent;
import agents.ReviewerNoBuyerAgent;
import entityCreator.ProductCreator;
import queryGenerators.InsertAddressGenerator;
import queryGenerators.InsertUserGenerator;
import utils.ExecuteQueries;
import utils.Utilities;

public class RunSimulator {
	public static ArrayList<String> allUsers = new ArrayList<String>();
	public static ArrayList<String> allProducts = new ArrayList<String>();
	
	public static void main(String[] args) throws Exception {
		
		init();
		
		Thread at1 = new Thread(new BuyerReviewerAgent());
//		at1.start();
		Thread at2 = new Thread(new BuyerAgent());
//		at2.start();
		Thread at3 = new Thread(new ReviewerNoBuyerAgent());
//		at3.start();
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
			InsertUserGenerator iug = new InsertUserGenerator();
			InsertAddressGenerator iadg = new InsertAddressGenerator();

			Map<String, String> params = new HashMap<String, String>();
			int seed = Integer.parseInt(appProps.getProperty("seed"));
			for (int i=0; i < Integer.parseInt(appProps.getProperty("num_of_users")); i++) {
				seed++;
				params.put("seed", ""  + seed);
				// Create Address queries
				// FIXME: After execution we need to keep the UUID to put it in the User query
				System.out.println(iadg.generateQuery(params));
				// Create User queries
				System.out.println(iug.generateQuery(params));
//				String response = utils.executeUpdate(iug.generateQuery(params));
			}
			System.out.println("User creation finished.");
		}
		// Store all users created
//		allUsers = Utilities.getAllUserUUIDs();

		System.out.println("Create products? " + appProps.getProperty("generate_products"));
		
		// Create Products
		if(Boolean.parseBoolean(appProps.getProperty("generate_products"))) {
			// Start by creating Categories
			System.out.println("Started product creation");
			ProductCreator pc = new ProductCreator();
			ArrayList<String> categoryIds = ProductCreator.createCategoryies();
			// Create products
			ArrayList<String> productIds = new ArrayList<String>();
			int seed = Integer.parseInt(appProps.getProperty("seed"));
			for (int i=0; i < Integer.parseInt(appProps.getProperty("num_of_products")); i++) {
				String productId = pc.create(seed, categoryIds);
				productIds.add(productId);
				seed++;
			}
			System.out.println("Product creation finished.");
		}
		// Store all products created
//		allProducts = Utilities.getAllProductUUIDs();
	}
	
	
}
