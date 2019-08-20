package ac.york.typhon.generator.helper;

import java.lang.reflect.Field;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import org.apache.commons.lang3.StringUtils;

public class Utils {

	private static Random random = new Random();

	public static String generateRandomId() {

		return String.valueOf(Math.abs(random.nextLong()));
	}

	public static Timestamp generateTimeStamp() {

		Date date = new Date();
		long time = date.getTime();
		Timestamp ts = new Timestamp(time);

		return ts;
	}
	
	public static Timestamp convertStringToTimeStamp(String dateStr) throws ParseException {

		String pattern = "yyyy-MM-dd hh:mm:ss";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);

		Date date = simpleDateFormat.parse(dateStr);
		
	
		long time = date.getTime();
		Timestamp ts = new Timestamp(time);

		return ts;
	}


	public static boolean isAnyFieldEmpty(Object obj) {
		boolean isEmptyFieldFlag = false;
		for (Field f : obj.getClass().getDeclaredFields()) {
			f.setAccessible(true);
			try {

				if (StringUtils.isEmpty((String) f.get(obj))) {

					isEmptyFieldFlag = true;
					// System.out.println("Found an empty field!");
				}

			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return isEmptyFieldFlag;
	}

	public static String removeSquareBrackets(String str) {
		return str.replace("[", "").replace("]", "");
	}

	public static String replaceSquareBracketsWithBrackets(String str) {
		return str.replace("[", "(").replace("]", ")");
	}
	
	
	public static long milliSeconds(int seconds) {
		return seconds * 1000;
	}

}
