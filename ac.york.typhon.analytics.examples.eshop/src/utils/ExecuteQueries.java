package utils;

import java.util.Date;
import java.util.HashSet;
import java.util.UUID;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

import ac.york.typhon.analytics.commons.datatypes.events.PostEvent;
import ac.york.typhon.analytics.commons.datatypes.events.PreEvent;
import ac.york.typhon.analytics.commons.datatypes.events.Slot;
import infra.RunSimulator;
import sun.misc.BASE64Encoder;

public class ExecuteQueries {

	// Make sure you put the local ip address of your computer
//	final String IP_ADDRESS = "localhost:29092";
//	
//	QueueProducer qp = new QueueProducer(IP_ADDRESS);

	public class Utils {

		public String executeQuery(String query) throws Exception {
			System.out.println(query);
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
			ClientResponse resp = webResource.accept("application/json")
					.header("Authorization", "Basic " + authStringEnc).post(ClientResponse.class, query);
			if (resp.getStatus() != 200) {
				System.err.println("Unable to connect to the server");
			}

			String output = resp.getEntity(String.class);

			return output;
		}

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

			String output = resp.getEntity(String.class);

			return output;
		}

		public void createAndPublishPostEvent(String query) throws Exception {

			PreEvent preEvent = new PreEvent();
			preEvent.setQuery(query);
			// Start timing for calculating execution time
			Date startTime = new Date();

			// Get date/time when query execution has finished
			Date endTime = new Date();

			PostEvent postEvent = new PostEvent();
			postEvent.setId(UUID.randomUUID().toString());
			postEvent.setQuery(query);
			postEvent.setSuccess(true);
			postEvent.setStartTime(startTime);
			postEvent.setEndTime(endTime);
			postEvent.setPreEvent(preEvent);
			// XXX: This is fake. We only do it to cheat the deserialiser in the case we don't go through the Polystore. It's ok.
			postEvent.setResultSet("{\n" + 
					"  \"affectedEntities\": -1,\n" + 
					"  \"createdUuids\": {\n" + 
					"    \"uuid\": \"e1910e20-75c2-4f3e-b886-d99a6db50520\"\n" + 
					"  }\n" + 
					"}");
			// Publish PostEvent to POST queue
			produce(postEvent);
			
		}
		
		public void createAndPublishPostEvent(String query, String user) throws Exception {

			PreEvent preEvent = new PreEvent();
			preEvent.setQuery(query);
			HashSet<Slot> slots = new HashSet<Slot>();
			Slot userSlot = new Slot("String", "user", user);
			slots.add(userSlot);
			preEvent.setSlots(slots);
			// Start timing for calculating execution time
			Date startTime = new Date();

			// Get date/time when query execution has finished
			Date endTime = new Date();

			PostEvent postEvent = new PostEvent();
			postEvent.setId(UUID.randomUUID().toString());
			postEvent.setQuery(query);
			postEvent.setSuccess(true);
			postEvent.setStartTime(startTime);
			postEvent.setEndTime(endTime);
			postEvent.setPreEvent(preEvent);
			// XXX: This is fake. We only do it to cheat the deserialiser in the case we don't go through the Polystore. It's ok.
			postEvent.setResultSet("{\n" + 
					"  \"affectedEntities\": -1,\n" + 
					"  \"createdUuids\": {\n" + 
					"    \"uuid\": \"e1910e20-75c2-4f3e-b886-d99a6db50520\"\n" + 
					"  }\n" + 
					"}");
			// Publish PostEvent to POST queue
			produce(postEvent);
			
		}
		
		public void produce(PostEvent postEvent) throws Exception {
//			String kafkaConnection = IP_ADDRESS;
//			QueueProducer qp = new QueueProducer(kafkaConnection);
			RunSimulator.qp.produce("POST", postEvent);
		}
	}
}
