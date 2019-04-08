package ac.uk.york.typhon.query.generator.helper;

import java.lang.reflect.Field;

import org.apache.commons.lang3.StringUtils;

public class Utils {

	public static void prepareStatement() {
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
}
