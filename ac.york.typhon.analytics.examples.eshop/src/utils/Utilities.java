package utils;

import java.util.ArrayList;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

	public static void getUserUUID(String resultSet) {
		ArrayList<String> allUUIDsWithPattern  = getPatternGroupsFromResultSet(resultSet, "\"uuid\":\"[a-zA-Z0-9\\-]*\",\"fields\"");
		ArrayList<String> allUUIDs = new ArrayList<String>();
		for (String uuidWithPattern : allUUIDsWithPattern) {
			String uuid = uuidWithPattern.split("\"uuid\":\"")[1].split("\",")[0];
			allUUIDs.add(uuid);
		}
		System.out.println(allUUIDs);
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
