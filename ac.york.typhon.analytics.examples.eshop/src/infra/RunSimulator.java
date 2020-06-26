package infra;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import agents.BrowsingAgent;
import agents.BrowsingWithCommentAgent;
import agents.BuyerAgent;
import agents.BuyerAgentToPre;
import agents.BuyerReviewerAgent;
import agents.ReviewerNoBuyerAgent;
import agents.UndecisiveAgent;
import entityUtils.ProductCreator;
import entityUtils.UserCreator;
import queryGenerators.InsertAddressGenerator;
import queryGenerators.InsertUserGenerator;
import utils.ExecuteQueries;
import utils.QueueProducer;
import utils.Utilities;
import utils.ExecuteQueries.Utils;

public class RunSimulator {
	public static ArrayList<String> allUsers = new ArrayList<String>();
	public static ArrayList<String> allProducts = new ArrayList<String>();
	public static Boolean goThroughPolystore = false;
	public static Date PreviousDate;
	public static String topic = "";
	// Make sure you put the local ip address of your computer
//	final static String IP_ADDRESS = "localhost:9092";
//	final static String IP_ADDRESS = "localhost:29092";
	final static String IP_ADDRESS = "localhost:9092";

	public static QueueProducer qp = new QueueProducer(IP_ADDRESS);
	public static void main(String[] args) throws Exception {
		ExecuteQueries eq = new ExecuteQueries();
		ExecuteQueries.Utils utils = eq.new Utils();
		InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("config.properties");
		Properties appProps = new Properties();
		appProps.load(inputStream);
		goThroughPolystore = Boolean.parseBoolean(appProps.get("goThroughPolystore").toString());
		int numOfBrowsingAgents = Integer.parseInt(appProps.getProperty("num_of_browsing_agents"));
		topic = appProps.get("topic").toString();
		init();
		
		ArrayList<Thread> allBrowsingAgents = new ArrayList<Thread>();
		for (int i=0; i<numOfBrowsingAgents; i++) {
			allBrowsingAgents.add(new Thread(new BrowsingAgent()));
		}
		for (Thread agent : allBrowsingAgents) {
			agent.start();
		}
		
		int numOfBrowsingWithCommentAgents = Integer.parseInt(appProps.getProperty("num_of_browsing_with_comment_agents"));
		ArrayList<Thread> allBrowsingWithCommentAgents = new ArrayList<Thread>();
		for (int i=0; i<numOfBrowsingWithCommentAgents; i++) {
			allBrowsingWithCommentAgents.add(new Thread(new BrowsingWithCommentAgent()));
		}
		for (Thread agent : allBrowsingWithCommentAgents) {
			agent.start();
		}
		
		int numOfBuyerAgents = Integer.parseInt(appProps.getProperty("num_of_buyer_agents"));
		ArrayList<Thread> allBuyerAgents = new ArrayList<Thread>();
		for (int i=0; i<numOfBuyerAgents; i++) {
			allBuyerAgents.add(new Thread(new BuyerAgent()));
		}
		for (Thread agent : allBuyerAgents) {
			agent.start();
		}
		
		int numOfBuyerAgentToPre = Integer.parseInt(appProps.getProperty("num_of_buyer_to_pre_agents"));
		ArrayList<Thread> allBuyerAgentToPre = new ArrayList<Thread>();
		for (int i=0; i<numOfBuyerAgentToPre; i++) {
			allBuyerAgentToPre.add(new Thread(new BuyerAgentToPre()));
		}
		for (Thread agent : allBuyerAgentToPre) {
			agent.start();
		}
		
//		
//		int numOfBuyerReviewerAgents = Integer.parseInt(appProps.getProperty("num_of_buyer_reviewer_agents"));
//		ArrayList<Thread> allBuyerReviewerAgents = new ArrayList<Thread>();
//		for (int i=0; i<numOfBuyerReviewerAgents; i++) {
//			allBuyerReviewerAgents.add(new Thread(new BuyerReviewerAgent()));
//		}
//		for (Thread agent : allBuyerReviewerAgents) {
//			agent.start();
//		}
//		
//		int numOfUndecisiveAgents = Integer.parseInt(appProps.getProperty("num_of_undecisive_agents"));
//		ArrayList<Thread> allUndecisiveAgents = new ArrayList<Thread>();
//		for (int i=0; i<numOfUndecisiveAgents; i++) {
//			allUndecisiveAgents.add(new Thread(new UndecisiveAgent()));
//		}
//		for (Thread agent : allUndecisiveAgents) {
//			agent.start();
//		}
		
		// TODO: Wait until all threads have finished to create closing window post event. 
		
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
			int num_of_users = Integer.parseInt(appProps.getProperty("num_of_users"));
			UserCreator uc = new UserCreator();
			int seed = Integer.parseInt(appProps.getProperty("seed"));
			for (int i=0; i < num_of_users; i++) {
				String userId = uc.create(seed);
				allUsers.add(userId);
				seed++;
			}
			System.out.println("User creation finished.");
		} else {
			System.out.println("Getting already created users from DB.");
			allUsers = Utilities.getAllUserUUIDs();
			System.out.println("Got " + allUsers.size() + " users from DB.");
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
		} else {
			System.out.println("Getting already created products from DB.");
			allProducts = Utilities.getAllProductUUIDs();
			System.out.println("Got " + allProducts.size() + " products from DB.");
		}
	}
	
	
}
