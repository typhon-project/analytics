package infra;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Properties;
import java.util.Random;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import agents.BuyerAgent;
import agents.BuyerReviewerAgent;
import agents.UndecisiveAgent;
import entityUtils.ProductCreator;
import entityUtils.UserCreator;
import utils.ExecuteQueries;

public class RunSimulator {
	public static ArrayList<String> allUsers = new ArrayList<String>();
	public static ArrayList<String> allProducts = new ArrayList<String>();

	public static void main(String[] args) throws Exception {

		InputStream inputStream = Thread.currentThread().getContextClassLoader()
				.getResourceAsStream("config.properties");
		Properties appProps = new Properties();
		appProps.load(inputStream);
		if (Boolean.parseBoolean(appProps.getProperty("generate_users"))) {
			init();
		} else {
			allUsers = getAlreadyCreatedUsers();
			allProducts = getAlreadyCreatedProducts();
			System.out.println(allUsers);
			System.out.println(allProducts);
			int numOfBuyerAgents = Integer.parseInt(appProps.getProperty("num_of_buyer_agents"));
			ArrayList<Thread> allBuyerAgents = new ArrayList<Thread>();
			for (int i = 0; i < numOfBuyerAgents; i++) {
				allBuyerAgents.add(new Thread(new BuyerAgent()));
			}
			for (Thread agent : allBuyerAgents) {
				agent.start();
			}

			int numOfBuyerReviewerAgents = Integer.parseInt(appProps.getProperty("num_of_buyer_reviewer_agents"));
			ArrayList<Thread> allBuyerReviewerAgents = new ArrayList<Thread>();
			for (int i = 0; i < numOfBuyerReviewerAgents; i++) {
				allBuyerReviewerAgents.add(new Thread(new BuyerReviewerAgent()));
			}
			for (Thread agent : allBuyerReviewerAgents) {
				agent.start();
			}

			int numOfUndecisiveAgents = Integer.parseInt(appProps.getProperty("num_of_undecisive_agents"));
			ArrayList<Thread> allUndecisiveAgents = new ArrayList<Thread>();
			for (int i = 0; i < numOfUndecisiveAgents; i++) {
				allUndecisiveAgents.add(new Thread(new UndecisiveAgent()));
			}
			for (Thread agent : allUndecisiveAgents) {
				agent.start();
			}
		}

	}

	public static void init() throws Exception {
		InputStream inputStream = Thread.currentThread().getContextClassLoader()
				.getResourceAsStream("config.properties");
		Properties appProps = new Properties();
		appProps.load(inputStream);
		ExecuteQueries eq = new ExecuteQueries();
		ExecuteQueries.Utils utils = eq.new Utils();
		Thread usersThread = new Thread() {
			public void run() {
				// Create Users
				System.out.println("Create users? " + appProps.getProperty("generate_users"));
				if (Boolean.parseBoolean(appProps.getProperty("generate_users"))) {
					System.out.println("Started user creation");
					int seed = Integer.parseInt(appProps.getProperty("seed"));
					for (int i = 0; i < Integer.parseInt(appProps.getProperty("num_of_users")); i++) {
						Runnable r = new UserCreator(seed);
						new Thread(r).start();
						seed++;
						try {
							Thread.sleep(1000);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
			}
		};
		usersThread.start();

		Thread productsThread = new Thread() {
			public void run() {
				System.out.println("Create products? " + appProps.getProperty("generate_products"));

				// Create Products
				if (Boolean.parseBoolean(appProps.getProperty("generate_products"))) {
					// Start by creating Categories
					System.out.println("Started product creation");
					ArrayList<String> categoryIds = null;
					try {
						categoryIds = createCategories();
						System.out.println("Cat ids: " + categoryIds);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					// Create products
					int seed = Integer.parseInt(appProps.getProperty("seed"));
					for (int i = 0; i < Integer.parseInt(appProps.getProperty("num_of_products")); i++) {
						String productId = null;
						Runnable r = new ProductCreator(seed, categoryIds);
						new Thread(r).start();
						seed++;
						try {
							Thread.sleep(1000);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

					}
				}
			}
		};
		productsThread.start();
	}

	public static ArrayList<String> createCategories() throws Exception {

		System.out.println("Started Category Creation");
		ExecuteQueries eq = new ExecuteQueries();
		ExecuteQueries.Utils utils = eq.new Utils();
		Random r = new Random();

		ArrayList<String> categoryIds = new ArrayList<String>();
		ArrayList<String> possibleCategories = new ArrayList<String>();
		possibleCategories.add("Computers");
		possibleCategories.add("Groceries");
		possibleCategories.add("Health");
		possibleCategories.add("Clothing");
		possibleCategories.add("Kitchen");
		possibleCategories.add("Bathroom");
		possibleCategories.add("Furniture");

		for (int i = 0; i < possibleCategories.size(); i++) {
			String category = possibleCategories.get(i);
			StringBuilder str = new StringBuilder();
			str.append("insert Category {");
			str.append("id: \"" + i + "\", ");
			str.append("name: \"" + category + "\"");
			str.append("}");
			String categoryId = utils.executeUpdate(str.toString()).split("\\{\"uuid\":\"")[1].split("\"\\}}")[0];
//			utils.createAndPublishPostEvent(str.toString());
//			String categoryId = UUID.randomUUID().toString();
			categoryIds.add(categoryId);
		}
		System.out.println("Finished Category Creation");
		return categoryIds;
	}
	
	public static ArrayList<String> getAlreadyCreatedUsers() throws Exception {
		ArrayList<String> users = new ArrayList<String>();
		String getAllUsersUUIDsQuery = "from User u select u";
		ExecuteQueries eq = new ExecuteQueries();
		ExecuteQueries.Utils utils = eq.new Utils();
		String usersResult = utils.executeQuery(getAllUsersUUIDsQuery);
		JSONParser parser = new JSONParser();
		JSONObject jsonObject = (JSONObject) parser.parse(usersResult);
		JSONArray msg = (JSONArray) jsonObject.get("values");
		Iterator<JSONArray> iterator = msg.iterator();
        while (iterator.hasNext()) {
        	users.add((String) iterator.next().get(0));
        }
        return users;
	}
	
	public static ArrayList<String> getAlreadyCreatedProducts() throws Exception {
		ArrayList<String> products = new ArrayList<String>();
		String getAllProductsUUIDsQuery = "from Product p select p";
		ExecuteQueries eq = new ExecuteQueries();
		ExecuteQueries.Utils utils = eq.new Utils();
		String usersResult = utils.executeQuery(getAllProductsUUIDsQuery);
		JSONParser parser = new JSONParser();
		JSONObject jsonObject = (JSONObject) parser.parse(usersResult);
		JSONArray msg = (JSONArray) jsonObject.get("values");
		Iterator<JSONArray> iterator = msg.iterator();
        while (iterator.hasNext()) {
        	products.add((String) iterator.next().get(0));
        }
        return products;
	}

}
