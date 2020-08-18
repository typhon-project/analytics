package utils;

import java.util.ArrayList;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import utils.ExecuteQueries.Utils;

public class Utilities {

	// Code taken from here:
	// https://mkyong.com/java/java-generate-random-integers-in-a-range/
	public static int getRandomNumberInRange(int min, int max) {

		if (min >= max) {
			throw new IllegalArgumentException("max must be greater than min");
		}

		Random r = new Random();
		return r.nextInt((max - min) + 1) + min;
	}

	public static ArrayList<String> getAllUserUUIDs() throws Exception {
		ExecuteQueries eq = new ExecuteQueries();
		ExecuteQueries.Utils utils = eq.new Utils();
		
		String resultSet = utils.executeQuery("from User u select u");
		ArrayList<String> allUUIDsWithPattern  = getPatternGroupsFromResultSet(resultSet, "\\b[0-9a-f]{8}\\b-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{4}-\\b[0-9a-f]{12}\\b");
//		ArrayList<String> allUUIDs = new ArrayList<String>();
//		for (String uuidWithPattern : allUUIDsWithPattern) {
//			String uuid = uuidWithPattern.split("\"uuid\":\"")[1].split("\",")[0];
//			allUUIDs.add(uuid);
//		}
		return allUUIDsWithPattern;
	}
	
	public static ArrayList<String> getAllProductUUIDs() throws Exception {
		ExecuteQueries eq = new ExecuteQueries();
		ExecuteQueries.Utils utils = eq.new Utils();
		
		String resultSet = utils.executeQuery("from Product p select p");
		ArrayList<String> allUUIDsWithPattern  = getPatternGroupsFromResultSet(resultSet, "\\b[0-9a-f]{8}\\b-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{4}-\\b[0-9a-f]{12}\\b");
//		ArrayList<String> allUUIDs = new ArrayList<String>();
//		for (String uuidWithPattern : allUUIDsWithPattern) {
//			String uuid = uuidWithPattern.split("\"uuid\":\"")[1].split("\",")[0];
//			allUUIDs.add(uuid);
//		}
		return allUUIDsWithPattern;
	}

	
	public static ArrayList<String> getPatternGroupsFromResultSet(String resultSet,  String  stringPattern) {
		ArrayList<String> patternGroups = new ArrayList<String>();
		Pattern pattern = Pattern.compile(stringPattern);
		Matcher matcher = pattern.matcher(resultSet);
		while (matcher.find()) {
			patternGroups.add(matcher.group());
		}
		return patternGroups;
	}

	
}
