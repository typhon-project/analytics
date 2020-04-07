package utilities;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Utilities {
	
	/**
	 * Calculate distance between two points in latitude and longitude taking
	 * into account height difference. If you are not interested in height
	 * difference pass 0.0. Uses Haversine method as its base.
	 * 
	 * lat1, lon1 Start point lat2, lon2 End point el1 Start altitude in meters
	 * el2 End altitude in meters. Taken from https://stackoverflow.com/questions/3694380/calculating-distance-between-two-points-using-latitude-longitude
	 * @returns Distance in Meters
	 */
	public static double distance(double lat1, double lat2, double lon1,
	        double lon2, double el1, double el2) {

	    final int R = 6371; // Radius of the earth

	    double latDistance = Math.toRadians(lat2 - lat1);
	    double lonDistance = Math.toRadians(lon2 - lon1);
	    double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
	            + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
	            * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
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
		str.append("insert EPS {");
		str.append("VIN: " + VIN + ", ");
		str.append("timestamp: " + timestamp + ", ");
		// FIXME: How to represent a point?
		str.append("vehicle_position: " + lat + " " + lon + ", ");
		str.append("esp_edl: " + esp_edl + ", ");
		str.append("esp_idd: " + esp_idd + ", ");
		str.append("esp_abs: " + esp_abs );
		str.append("}");
		// TODO: What about weather reference?
		
		query = str.toString();
		System.out.println(query);
		return query;
	}

}
