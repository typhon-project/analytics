package infra;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.UUID;

import com.sun.jersey.api.client.ClientResponse;

import agents.BuyerAgent;
import agents.BuyerReviewerAgent;
import agents.ReviewerNoBuyerAgent;
import agents.SimpleAgent;
import agents.SimpleAgent2;
import queryGenerators.InsertCreditCardGenerator;
import queryGenerators.InsertProductGenerator;
import queryGenerators.InsertUserGenerator;
import utils.ExecuteQueries;
import utils.ExecuteQueries.Utils;
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
			Map<String, String> params = new HashMap<String, String>();
			for (int i=0; i < Integer.parseInt(appProps.getProperty("num_of_users")); i++) {
				int seed = Integer.parseInt(appProps.getProperty("seed"));
				seed+=i;
				params.put("seed", ""  + seed);
				System.out.println(iug.generateQuery(params));
//				String response = utils.executeUpdate(iug.generateQuery(params));
			}
			System.out.println("User creation finished.");
		}
		// Store all users created
		allUsers = Utilities.getAllUserUUIDs();

		System.out.println("Create products? " + appProps.getProperty("generate_products"));
		
		// Create Products
		if(Boolean.parseBoolean(appProps.getProperty("generate_products"))) {
			// Start by creating Categories
			System.out.println("Started categories creation");
			createCategories();
			System.out.println("Started product creation");
			InsertProductGenerator ipg = new InsertProductGenerator();
			for (int i=0; i < Integer.parseInt(appProps.getProperty("num_of_products")); i++) {
				utils.executeUpdate(ipg.generateQuery(null));
			}
			System.out.println("Product creation finished.");
		}
		// Store all products created
		allProducts = Utilities.getAllProductUUIDs();
	}
	
	public static void createCategories() throws Exception {
		ExecuteQueries eq = new ExecuteQueries();
		ExecuteQueries.Utils utils = eq.new Utils();
		
		ArrayList<String> possibleCategories = new ArrayList<String>();
		possibleCategories.add("Computers");
		possibleCategories.add("Groceries");
		possibleCategories.add("Health");
		possibleCategories.add("Clothing");
		possibleCategories.add("Kitchen");
		possibleCategories.add("Bathroom");
		possibleCategories.add("Furniture");
		
		for (int i=0; i<possibleCategories.size(); i++) {
			String category = possibleCategories.get(i);
			StringBuilder str = new StringBuilder();
			str.append("insert Category {");
			str.append("id: \"" + i + "\", ");
			str.append("name: \"" + category + "\"");
			str.append("}");
			utils.executeUpdate(str.toString());
		}
	}

}
