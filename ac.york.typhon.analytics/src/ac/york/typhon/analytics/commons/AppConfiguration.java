package ac.york.typhon.analytics.commons;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.security.InvalidParameterException;
import java.text.MessageFormat;
import java.util.Properties;

import com.google.common.io.ByteSource;
import com.google.common.io.Resources;

public class AppConfiguration {

	private static Properties props;

	static {

		try {
			initialize();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static void initialize() throws Exception {

		URL url = Resources.getResource(Constants.PROPERTIES_FILENAME);
		props = new Properties();
		final ByteSource byteSource = Resources.asByteSource(url);
		try {

			InputStream inputStream = byteSource.openBufferedStream();

			props.load(inputStream);
		} catch (IOException e) {
			throw new Exception("Failed to open a Buffered Stream!", e);
		}

	}

	public static String getString(String key) {

		String value = (String) props.get(key);
		if (value == null) {
			throw new InvalidParameterException(MessageFormat.format(
					"Missing key - {0} - in configuration file ", key));
		}
		return value;

	}

	public static Integer getInteger(String key) {

		Integer value = Integer.parseInt((String) props.get(key));
		if (value == null) {
			throw new InvalidParameterException(MessageFormat.format(
					"Missing key - {0} - in configuration file ", key));
		}
		return value;

	}

}
