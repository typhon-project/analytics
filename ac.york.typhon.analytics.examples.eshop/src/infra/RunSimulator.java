package infra;

import java.io.InputStream;
import java.util.Properties;

import queryGenerators.InsertProductGenerator;
import queryGenerators.InsertUserGenerator;
import utils.ExecuteQueries;
import utils.ExecuteQueries.Utils;

public class RunSimulator {
	
	
	
	public static final Boolean GENERATE_USERS = true;
	public static final Boolean GENERATE_PRODUCTS = true;
	public static final int NUMBER_OF_USERS = 30;
	public static final int NUMBER_OF_PRODUCTS = 30;

	public static void main(String[] args) throws Exception {
		InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("config.properties");
		Properties appProps = new Properties();
		appProps.load(inputStream);
		ExecuteQueries eq = new ExecuteQueries();
		ExecuteQueries.Utils utils = eq.new Utils();
	
		if(Boolean.parseBoolean(appProps.getProperty("generate_users"))) {
			InsertUserGenerator iug = new InsertUserGenerator();
			for (int i=0; i < Integer.parseInt(appProps.getProperty("num_of_users")); i++) {
				utils.executeUpdateAndReturnPostvent(iug.generateQuery());
			}
		}
		
		if(Boolean.parseBoolean(appProps.getProperty("generate_products"))) {
			InsertProductGenerator ipg = new InsertProductGenerator();
			for (int i=0; i < Integer.parseInt(appProps.getProperty("num_of_products")); i++) {
				utils.executeUpdateAndReturnPostvent(ipg.generateQuery());
			}
		}
		
		// Create query generators
//		InsertCommentGenerator insertComment = new InsertCommentGenerator();
//		
//		Action a1 = new Action(insertComment.generateQuery());
//		a1.setName("Action");
//		a1.start();
	}

}
