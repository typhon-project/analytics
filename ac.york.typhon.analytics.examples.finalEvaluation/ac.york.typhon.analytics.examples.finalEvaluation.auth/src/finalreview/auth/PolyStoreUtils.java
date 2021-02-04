package finalreview.auth;

import java.util.Base64;
import java.util.Collections;
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
		String name = "admin";
		String password = "admin1@";
		String authString = name + ":" + password;
		authStringEnc = Base64.getEncoder().encodeToString(authString.getBytes());
		this.engineClassLoader = engineClassLoader;
	}

	private static String authStringEnc;
	private static WebResource webResource;
	private static WebResource webResourceU;
	private static WebResource webResourceA;
	private static WebResource webResourceAU;

	// private static final String ip = "typhon-polystore-service";
	private static final String ip = "localhost";

	public PolyStoreUtils() {
		String name = "admin";
		String password = "admin1@";
		String authString = name + ":" + password;
		authStringEnc = Base64.getEncoder().encodeToString(authString.getBytes());
	}

	public int resetDatabases() {

		String url = "http://" + ip + ":8080/api/resetdatabases";
		Client restClient = Client.create();

		return restClient.resource(url).accept("application/json").header("Authorization", "Basic " + authStringEnc)
				.type("application/json").get(ClientResponse.class).getStatus();

	}

	private void connectToPolyStore() {

		String url = "http://" + ip + ":8080/api/noAnalytics/query";
		Client restClient = Client.create();
		webResource = restClient.resource(url);

	}

	private void connectToPolyStoreU() {

		String url = "http://" + ip + ":8080/api/noAnalytics/update";
		Client restClient = Client.create();
		webResourceU = restClient.resource(url);

	}

	private void connectToPolyStoreA() {

		String url = "http://" + ip + ":8080/api/query";
		Client restClient = Client.create();
		webResourceA = restClient.resource(url);

	}

	private void connectToPolyStoreAU() {

		String url = "http://" + ip + ":8080/api/update";
		Client restClient = Client.create();
		webResourceAU = restClient.resource(url);

	}

	public String queryPolyStoreRaw(String query, boolean update, boolean analytics, boolean parametarised, String pt,
			String pn, List<List<String>> br) throws Exception {

		if (analytics) {
			if (webResourceA == null)
				connectToPolyStoreA();
			if (webResourceAU == null)
				connectToPolyStoreAU();
		} else {
			if (webResource == null)
				connectToPolyStore();
			if (webResourceU == null)
				connectToPolyStoreU();
		}

		Query queryObj = null;
		ParametarisedQuery pqo = null;

		if (parametarised) {
			pqo = new ParametarisedQuery();
			pqo.setQuery(query);
			pqo.setParameterNames(Collections.singletonList(pn));
			pqo.setParameterTypes(Collections.singletonList(pt));
			pqo.setBoundRows(br);
		} else {
			queryObj = new Query();
			queryObj.setQuery(query);
		}

		ObjectMapper mapper = new ObjectMapper();
		String json = "";
		try {
			if (parametarised)
				json = mapper.writeValueAsString(pqo);
			else
				json = mapper.writeValueAsString(queryObj);
			System.out.println("ResultingJSONstring = " + json);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}

		ClientResponse resp = null;

		if (analytics) {
			if (update)
				resp = webResourceAU.accept("application/json").header("Authorization", "Basic " + authStringEnc)
						.type("application/json").post(ClientResponse.class, json);
			else
				resp = webResourceA.accept("application/json").header("Authorization", "Basic " + authStringEnc)
						.type("application/json").post(ClientResponse.class, json);
		} else {
			if (update)
				resp = webResourceU.accept("application/json").header("Authorization", "Basic " + authStringEnc)
						.type("application/json").post(ClientResponse.class, json);
			else
				resp = webResource.accept("application/json").header("Authorization", "Basic " + authStringEnc)
						.type("application/json").post(ClientResponse.class, json);
		}

		// System.out.println("Responce = "+resp.getEntity(String.class));

		if (resp.getStatus() != 200) {
			System.out.println("Unable to connect to the server: " + resp.getStatus());
			return query;
		} else
			return resp.getEntity(String.class);
	}

	public List<Entity> queryPolyStore(String query, boolean update, boolean analytics) throws Exception {

		if (analytics) {
			if (webResourceA == null)
				connectToPolyStoreA();
			if (webResourceAU == null)
				connectToPolyStoreAU();
		} else {
			if (webResource == null)
				connectToPolyStore();
			if (webResourceU == null)
				connectToPolyStoreU();
		}

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

		if (analytics) {
			if (update)
				resp = webResourceAU.accept("application/json").header("Authorization", "Basic " + authStringEnc)
						.type("application/json").post(ClientResponse.class, json);
			else
				resp = webResourceA.accept("application/json").header("Authorization", "Basic " + authStringEnc)
						.type("application/json").post(ClientResponse.class, json);
		} else {
			if (update)
				resp = webResourceU.accept("application/json").header("Authorization", "Basic " + authStringEnc)
						.type("application/json").post(ClientResponse.class, json);
			else
				resp = webResource.accept("application/json").header("Authorization", "Basic " + authStringEnc)
						.type("application/json").post(ClientResponse.class, json);
		}

		// System.out.println("Responce = "+resp.getEntity(String.class));

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

	private class ParametarisedQuery {

		private String query;
		private List<String> parameterTypes;
		private List<String> parameterNames;
		private List<List<String>> boundRows;

		public String getQuery() {
			return query;
		}

		public void setQuery(String query) {
			this.query = query;
		}

		public List getParameterTypes() {
			return parameterTypes;
		}

		public void setParameterTypes(List parameterTypes) {
			this.parameterTypes = parameterTypes;
		}

		public List getParameterNames() {
			return parameterNames;
		}

		public void setParameterNames(List parameterNames) {
			this.parameterNames = parameterNames;
		}

		public List getBoundRows() {
			return boundRows;
		}

		public void setBoundRows(List<List<String>> br) {
			this.boundRows = br;
		}

	}

	public static void main(String[] args) throws Exception {
		System.out.println(new PolyStoreUtils(ClassLoader.getSystemClassLoader()).queryPolyStore(
				"from NON_FNC_EV p select p.NON_FNC_EV_ACTN_DSC, p.NON_FNC_EV_ACTN_CODE", false, false));
	}

}