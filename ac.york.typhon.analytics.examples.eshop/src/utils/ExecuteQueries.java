package utils;

import java.util.Date;
import java.util.UUID;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

import ac.york.typhon.analytics.commons.datatypes.events.PostEvent;
import sun.misc.BASE64Encoder;

public class ExecuteQueries {

	// Make sure you put the local ip address of your computer
	final String IP_ADDRESS = "192.168.1.3:9092";
	
	QueueProducer qp = new QueueProducer(IP_ADDRESS);

	public class Utils {

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
			
			// Publish PostEvent to POST queue
			produce(postEvent);
			
		}
		
		public void produce(PostEvent postEvent) throws Exception {
			String kafkaConnection = IP_ADDRESS;
			QueueProducer qp = new QueueProducer(kafkaConnection);
			qp.produce("POST", postEvent);
		}
	}
}
