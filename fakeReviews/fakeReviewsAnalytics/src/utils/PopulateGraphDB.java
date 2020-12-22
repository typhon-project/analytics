package utils;

import java.nio.file.Files;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

import java.io.File;

public class PopulateGraphDB {

	private static String authStringEnc;
	private static WebResource webResource;

	public static void main(String[] args) {

	}

	private void populateDB() throws Exception {
		Map<String, String> allUsersToUUID = new HashMap<String,String>();
		Map<String, String> allProductsToUUID = new HashMap<String,String>();

		// Create all the users, products and reviews in the graph
		for (String line : Files.readAllLines(new File("files/AMAZON_FASHION.csv").toPath())) {

			String[] parts = line.split(",");
			String user = parts[0].trim();
			String product = parts[1].trim();
			Double rating = Double.parseDouble(parts[2]);
			String userUUID = "";
			if (!allUsersToUUID.containsKey(user)) {
				userUUID = UUID.randomUUID().toString();
				String insertUserQuery = "insert User {@id: #" + userUUID + ", name: \"" + user + "\"}";
				queryPolyStore(insertUserQuery);
				allUsersToUUID.put(user, userUUID);
			} else {
				userUUID = allUsersToUUID.get(user);
			}
			String productUUID = "";
			if (!allProductsToUUID.containsKey(product)) {
				productUUID = UUID.randomUUID().toString();
				String insertProductQuery = "insert Product {@id: #" + productUUID + ", name: \"" + product + "\"}";
				queryPolyStore(insertProductQuery);
				allProductsToUUID.put(product, productUUID);
			} else {
				productUUID = allProductsToUUID.get(product);
			}
			
			String insertReviewQuery = "insert Review {rating: " + rating + ", user: #" + userUUID + ", product: #" + productUUID + "}";
			queryPolyStore(insertReviewQuery);

			/*
			parameters = new HashMap<>();
			parameters.put("userName", user);
			parameters.put("productName", product);
			parameters.put("rating", rating);

			tx.run("MATCH (u:User),(p:Product)\n" + "WHERE u.name = $userName AND p.name = $productName\n"
					+ "MERGE (u)-[r:Review {rating: $rating}]->(p)", parameters);

			i++;

			if (i == 1000) {
				tx.commit();
				tx = session.beginTransaction();
				i = 0;
				System.out.println("Batch:" + batch++);
			}
			*/

		}
	}

	private void connectToPolyStore() {

		String url = "http://localhost:8080/api/noAnalytics/update";
		String name = "admin";
		String password = "admin1@";
		String authString = name + ":" + password;
		authStringEnc = Base64.getEncoder().encodeToString(authString.getBytes());
		Client restClient = Client.create();
		webResource = restClient.resource(url);

	}

	public String queryPolyStore(String query) throws Exception {

		if (webResource == null)
			connectToPolyStore();

		Query queryObj = new Query();

		queryObj.setQuery(query);
		ObjectMapper mapper = new ObjectMapper();
		String json = "";
		try {
			json = mapper.writeValueAsString(queryObj);
			// System.out.println("ResultingJSONstring = " + json);
			// System.out.println(json);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}

		ClientResponse resp = webResource.accept("application/json").header("Authorization", "Basic " + authStringEnc)
				.type("application/json").post(ClientResponse.class, json);
		if (resp.getStatus() != 200) {
			System.err.println("Unable to connect to the server");
		}
		return resp.getEntity(String.class);
	}
	
	private class Query {

		String query;

		public String getQuery() {
			return query;
		}

		public void setQuery(String query) {
			this.query = query;
		}

	}

}
