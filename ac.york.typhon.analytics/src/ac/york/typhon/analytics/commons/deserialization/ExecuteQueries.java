package ac.york.typhon.analytics.commons.deserialization;

import java.util.Date;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

import sun.misc.BASE64Encoder;


public class ExecuteQueries {
	
	// Make sure you put the local ip address of your computer
	final String IP_ADDRESS = "192.168.1.18";

	public static void main(String[] args) throws Exception {
		ExecuteQueries eq = new ExecuteQueries();
		ExecuteQueries.Utils utils = eq.new Utils();
		utils.executeQuery("from User v select v");
//		utils.executeUpdateAndReturnPostvent("insert @vmd1277771325 VehicleMetadata {VIN: 1277771325, brand: \"Volkswagen\", model: \"Golf-7\", constr_year: 2015, color: \"black201\", t_sensor_h: 62, engine_type: \"combustion\"}");
	}

	public class Utils {
		// Executes a select query
		public String executeQuery(String query) throws Exception {
			// This is the REST url that executes a select query. Authentication is done
			// using the polystore's credentials.
			String url = "http://localhost:8080/api/query";
			String name = "admin";
			String password = "admin1@";
			String authString = name + ":" + password;
			String authStringEnc = new BASE64Encoder().encode(authString.getBytes());
			Client restClient = Client.create();
			WebResource webResource = restClient.resource(url);

			// Start timing for calculating execution time
			Date startTime = new Date();
			ClientResponse resp = webResource.accept("application/json")
					.header("Authorization", "Basic " + authStringEnc).post(ClientResponse.class, query);
			if (resp.getStatus() != 200) {
				System.err.println("Unable to connect to the server");
			}

			return resp.getEntity(String.class);

		}

		// Executes an insert/delete/update query
		public String executeUpdate(String query) throws Exception {
			// This is the REST url that executes a select query. Authentication is done
			// using the polystore's credentials.
			String url = "http://localhost:8080/api/update";
			String name = "admin";
			String password = "admin1@";
			String authString = name + ":" + password;
			String authStringEnc = new BASE64Encoder().encode(authString.getBytes());
			Client restClient = Client.create();
			WebResource webResource = restClient.resource(url);

			// Start timing for calculating execution time
			Date startTime = new Date();
			ClientResponse resp = webResource.accept("application/json")
					.header("Authorization", "Basic " + authStringEnc).post(ClientResponse.class, query);
			if (resp.getStatus() != 200) {
				System.err.println("Unable to connect to the server");
			}

			return resp.getEntity(String.class);


		}
	}

}
