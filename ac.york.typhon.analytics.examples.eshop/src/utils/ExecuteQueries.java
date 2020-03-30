package utils;

import sun.misc.BASE64Encoder;

import java.util.Date;
import java.util.UUID;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

import commons.PostEvent;


public class ExecuteQueries {
	
	// Make sure you put the local ip address of your computer
	final String IP_ADDRESS = "192.168.1.16";

	public class Utils {
		// Executes a select query
		public String executeQueryAndReturnPostvent(String query) throws Exception {
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

			// Get date/time when query execution has finished
			Date endTime = new Date();

			PostEvent postEvent = new PostEvent();
			postEvent.setId(UUID.randomUUID().toString());
			postEvent.setQuery(query);
			postEvent.setSuccess(true);
			postEvent.setResultSet(output);
			postEvent.setStartTime(startTime);
			postEvent.setEndTime(endTime);
			
			// Publish PostEvent to POST queue
			produce(postEvent);
			
			return output;
			
		}

		// Executes an insert/delete/update query
		public String executeUpdateAndReturnPostvent(String query) throws Exception {
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

			// Get date/time when query execution has finished
			Date endTime = new Date();

			PostEvent postEvent = new PostEvent();
			postEvent.setId(UUID.randomUUID().toString());
			postEvent.setQuery(query);
			postEvent.setSuccess(true);
			postEvent.setResultSet(output);
			postEvent.setStartTime(startTime);
			postEvent.setEndTime(endTime);

			// Publish PostEvent to POST queue
			produce(postEvent);

			return output;
		}

		public void produce(PostEvent postEvent) throws Exception {
			String kafkaConnection = IP_ADDRESS + ":29092";
			QueueProducer qp = new QueueProducer(kafkaConnection);
			qp.produce("POST", postEvent);
		}
	}

}
