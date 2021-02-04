package analytics;

import java.util.Base64;
import java.util.LinkedList;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

import ac.york.typhon.analytics.commons.datatypes.events.Entity;
import ac.york.typhon.analytics.commons.datatypes.events.JSONQuery;
import ac.york.typhon.analytics.commons.deserialization.SelectDeserializer;

public class PolyStoreUtils {

	ClassLoader engineClassLoader;

	public PolyStoreUtils(ClassLoader engineClassLoader) {
		this.engineClassLoader = engineClassLoader;
	}

	private static String authStringEnc;
	private static WebResource webResource;
	private static WebResource webResourceU;

	//private static final String ip = "typhon-polystore-service";
	private static final String ip = "localhost";
	
	private void connectToPolyStore() {

		String url = "http://" + ip + ":8080/api/noAnalytics/query";
		String name = "admin";
		String password = "admin1@";
		String authString = name + ":" + password;
		authStringEnc = Base64.getEncoder().encodeToString(authString.getBytes());
		Client restClient = Client.create();
		webResource = restClient.resource(url);

	}

	private void connectToPolyStoreU() {

		String url = "http://" + ip + ":8080/api/noAnalytics/update";
		String name = "admin";
		String password = "admin1@";
		String authString = name + ":" + password;
		authStringEnc = Base64.getEncoder().encodeToString(authString.getBytes());
		Client restClient = Client.create();
		webResourceU = restClient.resource(url);

	}

	public List<Entity> queryPolyStore(String query, boolean update) throws Exception {

		if (webResource == null)
			connectToPolyStore();
		if (webResourceU == null)
			connectToPolyStoreU();

		Query queryObj = new Query();

		queryObj.setQuery(query);
		ObjectMapper mapper = new ObjectMapper();
		String json = "";
		try {
			json = mapper.writeValueAsString(queryObj);
			System.out.println("ResultingJSONstring = " + json);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}

		ClientResponse resp = null;
		if (update)
			resp = webResourceU.accept("application/json").header("Authorization", "Basic " + authStringEnc)
					.type("application/json").post(ClientResponse.class, json);
		else
			resp = webResource.accept("application/json").header("Authorization", "Basic " + authStringEnc)
					.type("application/json").post(ClientResponse.class, json);
		
		//System.out.println("Responce = "+resp.getEntity(String.class));
		
		if (resp.getStatus() != 200) {
			System.out.println("Unable to connect to the server: " + resp.getStatus());			
		} else if (!update)
			return parseResultSet(query, resp.getEntity(String.class));
		return new LinkedList<Entity>();
	}

	private List<Entity> parseResultSet(String query, String rs) throws Exception {

		SelectDeserializer sd = new SelectDeserializer(engineClassLoader);
		JSONQuery jsonquery = new JSONQuery();
		jsonquery.setQuery(query);
		List<Entity> selectedEntities = sd.deserialize(jsonquery, null, rs, null, true, null, 0);

		return selectedEntities;

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

	public static void main(String[] args) throws Exception {
		System.out.println(new PolyStoreUtils(ClassLoader.getSystemClassLoader())
				.queryPolyStore("from NON_FNC_EV p select p.NON_FNC_EV_ACTN_DSC, p.NON_FNC_EV_ACTN_CODE", false));
	}

}