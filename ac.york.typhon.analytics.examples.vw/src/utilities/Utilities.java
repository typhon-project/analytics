package utilities;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

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

	public static String fromESPJsonToQuery(File jsonFile) throws FileNotFoundException, IOException, ParseException {
		String query = "";
		Object obj = new JSONParser().parse(new FileReader(jsonFile));
		JSONObject jo = (JSONObject) obj;

		Map eventMessage = (Map) jo.get("eventMessage");

		// Find ESP event type
		String espSignal = (String) eventMessage.get("esp_signal");
		String esp_edl = "false";
		String esp_idd = "false";
		String esp_abs = "false";
		if (espSignal.equalsIgnoreCase("esp_edl")) {
			esp_edl = "true";
		} else if (espSignal.equalsIgnoreCase("esp_idd")) {
			esp_idd = "true";
		} else if (espSignal.equalsIgnoreCase("esp_abs")) {
			esp_abs = "true";
		}

		Map data = (Map) ((JSONArray) eventMessage.get("data")).get(0);
		String VIN = data.get("VIN").toString();
		String timestamp = data.get("timestamp").toString();
		Map geoposition = (Map) ((JSONArray) data.get("geoposition")).get(0);
		String lat = geoposition.get("lat").toString();
		String lon = geoposition.get("lon").toString();

		StringBuilder str = new StringBuilder();
		str.append("insert ESP {");
		str.append("VIN: " + VIN + ", ");
		str.append("timeStamp: \"" + timestamp + "\", ");
		// FIXME: How to represent a point?
		str.append("vehicle_position: \"" + lat + " " + lon + "\", ");
		str.append("esp_edl: " + esp_edl + ", ");
		str.append("esp_idd: " + esp_idd + ", ");
		str.append("esp_abs: " + esp_abs);
		str.append("}");
		// TODO: What about weather reference?

		query = str.toString();
		System.out.println(query);
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

		StringBuilder str = new StringBuilder();
		str.append("insert VehicleMetadata {");
		str.append("VIN: " + VIN + ", ");
		str.append("brand: \"" + brand + "\", ");
		str.append("model: \"" + model + "\", ");
		str.append("constr_year: " + constr_year + ", ");
		str.append("color: \"" + color + "\", ");
		str.append("t_sensor_h: " + t_sensor_h + ", ");
		str.append("engine_type: \"" + engine_type + "\"");
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
					String temprature = tokens[4];
					String rain_intensity = tokens[5];
					String solar_intensity = tokens[6];

					StringBuilder str = new StringBuilder();
					str.append("insert VehicleWeatherData {");
					str.append("timestamp: " + timestamp + ", ");
					// FIXME: How to store Point?
					str.append("vehicle_position: " + lat + " " + lon + ", ");
					str.append("temperature: " + temprature + ", ");
					str.append("rain_Intensity: " + rain_intensity + ", ");
					str.append("solar_Intensity: " + solar_intensity + "");
					// TODO: Think about how to store vehicle metadata here
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

}
