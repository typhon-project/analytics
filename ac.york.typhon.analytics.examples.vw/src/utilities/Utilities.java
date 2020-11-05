package utilities;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.UUID;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

import sun.misc.BASE64Encoder;

public class Utilities {

	/**
	 * Calculate distance between two points in latitude and longitude taking into
	 * account height difference. If you are not interested in height difference
	 * pass 0.0. Uses Haversine method as its base.
	 * 
	 * lat1, lon1 Start point lat2, lon2 End point el1 Start altitude in meters el2
	 * End altitude in meters. Taken from
	 * https://stackoverflow.com/questions/3694380/calculating-distance-between-two-points-using-latitude-longitude
	 * 
	 * @returns Distance in Meters
	 */
	public static double distance(double lat1, double lat2, double lon1, double lon2, double el1, double el2) {

		final int R = 6371; // Radius of the earth

		double latDistance = Math.toRadians(lat2 - lat1);
		double lonDistance = Math.toRadians(lon2 - lon1);
		double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2) + Math.cos(Math.toRadians(lat1))
				* Math.cos(Math.toRadians(lat2)) * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
		double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
		double distance = R * c * 1000; // convert to meters

		double height = el1 - el2;

		distance = Math.pow(distance, 2) + Math.pow(height, 2);

		return Math.sqrt(distance);
	}

	/*
	 * 
	 entity ESPData{
		VIN: int	
		timeStamp : datetime
		vehicle_position : point
		esp_edl: bool
		esp_idd: bool
		esp_abs: bool
		vehicleWeatherData -> VehicleWeatherData[0..*] 
	 }
	 * 
	 * */
	public static String fromESPJsonToQuery(File jsonFile) throws FileNotFoundException, IOException, ParseException {
		String query = "";
		Object obj = new JSONParser().parse(new FileReader(jsonFile));
		JSONObject jo = (JSONObject) obj;

		Map eventMessage = (Map) jo.get("eventMessage");

		// Find ESP event type
		String espSignal = (String) eventMessage.get("esp_signal");
		boolean esp_edl = false;
		boolean esp_idd = false;
		boolean esp_abs = false;
		if (espSignal.equalsIgnoreCase("esp_edl")) {
			esp_edl = true;
		} else if (espSignal.equalsIgnoreCase("esp_idd")) {
			esp_idd = true;
		} else if (espSignal.equalsIgnoreCase("esp_abs")) {
			esp_abs = true;
		}

		Map data = (Map) ((JSONArray) eventMessage.get("data")).get(0);
		String VIN = data.get("VIN").toString();
		String timestamp = data.get("timestamp").toString();
		Map geoposition = (Map) ((JSONArray) data.get("geoposition")).get(0);
		String lat = geoposition.get("lat").toString();
		String lon = geoposition.get("lon").toString();

//		create the id of the entry
		UUID myuuid = UUID.nameUUIDFromBytes(VIN.getBytes());
		
		StringBuilder str = new StringBuilder();
		//FIXME this is the old QL query form we have to change to the new one { "query" : "insert ..." }
		str.append("{ \"query\" : \" insert ESPData  { ");
//		str.append("insert ESPData {");
		
		str.append("id: " + myuuid + ", ");
		str.append("VIN: " + VIN + ", ");
		//TODO 04.11.2020 check if $ is the correct thing as @ https://github.com/typhon-project/typhonql/blob/master/typhonql/doc/typhonql.md 
		str.append("timeStamp: $" + timestamp + "$, ");
		// FIXME: How to represent a point?
		// TODO check if this is correct like so:
		str.append("vehicle_position: #point(" + lat + " " + lon + "), ");
				
		str.append("esp_edl: " + esp_edl + ", ");
		str.append("esp_idd: " + esp_idd + ", ");
		str.append("esp_abs: " + esp_abs + ", ");

		// TODO: What about weather reference? 
		// TODO: check if this is correct
		str.append("vehicleWeatherData: #" + myuuid);

		//FIXME end of the new query form - like so? 
		str.append("} \"");
		
		str.append("}");
		
		query = str.toString();
		System.out.println(query);
//		output -> { "query" : " insert VehicleMetadata VIN: 1277000000, timeStamp: $2020-03-31T00:00:00Z$, vehicle_position: #point(52.525861 13.416833), esp_edl: true, esp_idd: false, esp_abs: false, vehicleWeatherData: #1277000000"}
		return query;
	}
	
	public static String fromMetadataJsonToQuery(File jsonFile)
			throws FileNotFoundException, IOException, ParseException {
		String query = "";
		
		Object obj = new JSONParser().parse(new FileReader(jsonFile));
		JSONObject jo = (JSONObject) obj;

		Map codMessage = (Map) jo.get("codMessage");

		Map ignitionOn = (Map) ((JSONArray) codMessage.get("ignitionOn")).get(0);
		String VIN = ignitionOn.get("VIN").toString();
		String brand = ignitionOn.get("brand").toString();
		String model = ignitionOn.get("model").toString();
		String constr_year = ignitionOn.get("constr_year").toString();
		String color = ignitionOn.get("color").toString();
		String t_sensor_h = ignitionOn.get("t_sensor_h").toString();
		String engine_type = ignitionOn.get("engine_type").toString();

//		create the id of the entry
		UUID myuuid = UUID.nameUUIDFromBytes(VIN.getBytes());
		
		StringBuilder str = new StringBuilder();
		//FIXME this is the old QL query form we have to change to the new one { "query" : "insert ..." }
		str.append("{ \"query\" : \" insert VehicleMetadata  { ");
//		str.append("insert VehicleMetadata {");
		
		//adding the id explicitely to the insert statement
		str.append("id: " + myuuid + ", ");
		str.append("VIN: " + VIN + ", ");
		str.append("brand: \"" + brand + "\", ");
		str.append("model: \"" + model + "\", ");
		str.append("constr_year: " + constr_year + ", ");
		str.append("color: \"" + color + "\", ");
		str.append("t_sensor_h: " + t_sensor_h + ", ");
		str.append("engine_type: \"" + engine_type + "\", ");

		// TODO: What about weather reference? 
		// TODO: check if this is correct
		str.append("vehicleWeatherData: #" + myuuid);
		
		//FIXME end of the new query form - like so? 
		str.append("} \"");
		
		str.append("}");
		
		query = str.toString();
		System.out.println(query);
		return query;
	}

	public static ArrayList<String> fromVehicleWeatherCSVToQuery(File csvFile)
			throws FileNotFoundException, IOException, ParseException {
		String query = "";
		ArrayList<String> allQueries = new ArrayList<String>();
		BufferedReader fileReader = null;
		final String DELIMITER = ",";
		try {
			String line = "";
			fileReader = new BufferedReader(new FileReader(csvFile));
			int i = 0;
			while ((line = fileReader.readLine()) != null) {
				if (i == 0) {
					i++;
				} else {
					String[] tokens = line.split(DELIMITER);
					String VIN = tokens[0];
					String timestamp = tokens[1];
					String lat = tokens[2];
					String lon = tokens[3];
					String temperature = tokens[4];
					String rain_intensity = tokens[5];
					String solar_intensity = tokens[6];

//					create the id of the entry
					UUID myuuid = UUID.nameUUIDFromBytes(VIN.getBytes());

					StringBuilder str = new StringBuilder();
					//FIXME this is the old QL query form we have to change to the new one { "query" : "insert ..." }
					str.append("{ \"query\" : \" insert VehicleWeatherData { ");
//					str.append("insert VehicleWeatherData {");

					//adding the id explicitely to the insert statement
					str.append("id: " + myuuid + ", ");
					str.append("VIN: " + VIN + ", ");
					str.append("timeStamp: $" + timestamp + "$, ");
					// FIXME: How to represent a point?
					// TODO check if this is correct like so:
					str.append("vehicle_position: #point(" + lat + " " + lon + "), ");

					str.append("temperature: " + temperature + ", ");
					str.append("rain_Intensity: " + rain_intensity + ", ");
					str.append("solar_Intensity: " + solar_intensity + ", ");
					
					// TODO: Think about how to store vehicle metadata here
					
					// TODO: check if this is correct
					str.append("ESPData: #" + myuuid);
					//FIXME end of the new query form - like so? 
					str.append("} \"");
					
					str.append("}");
					
					query = str.toString();
					System.out.println(query);
					allQueries.add(query);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				fileReader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return allQueries;
	}
	
	// Executes an insert/delete/update query
	public static void executeUpdate(String query) throws Exception {
		// This is the REST url that executes a select query. Authentication is done
		// using the polystore's credentials.
		//TODO check if these are the proper current credentials
		String url = "http://localhost:8080/api/update";
		String name = "admin";
		String password = "admin1@";
		String authString = name + ":" + password;
		String authStringEnc = new BASE64Encoder().encode(authString.getBytes());
		Client restClient = Client.create();
		WebResource webResource = restClient.resource(url);
		ClientResponse resp = webResource.accept("application/json")
				.header("Authorization", "Basic " + authStringEnc).post(ClientResponse.class, query);
		if (resp.getStatus() != 200) {
			System.err.println("Unable to connect to the server");
		}
	}

}
