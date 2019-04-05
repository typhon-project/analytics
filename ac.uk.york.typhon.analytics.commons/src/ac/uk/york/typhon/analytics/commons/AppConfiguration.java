package ac.uk.york.typhon.analytics.commons;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.security.InvalidParameterException;
import java.text.MessageFormat;
import java.util.Properties;

import com.google.common.io.ByteSource;
import com.google.common.io.Resources;

public class AppConfiguration {

	// private static Configuration config;
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

		//
		// Parameters params = new Parameters();
		// try {
		// FileBasedConfigurationBuilder<FileBasedConfiguration> builder = new
		// FileBasedConfigurationBuilder<FileBasedConfiguration>(
		// PropertiesConfiguration.class).configure(params
		// .properties()
		// .setFileName(Constants.PROPERTIES_FILENAME)
		// .setListDelimiterHandler(
		// new DefaultListDelimiterHandler(',')));
		//
		// config = builder.getConfiguration();
		// } catch (ConfigurationException e) {
		// throw new Exception("Failed to load properties file!", e);
		//
		// }

	}

	public static String getString(String key) {

		String value = (String) props.get(key);
		if (value == null) {
			throw new InvalidParameterException(MessageFormat.format(
					"Missing key - {0} - in configuration file ", key));
		}
		return value;

	}

}

//
// PropertiesConfiguration.PropertiesReader reader = new
// PropertiesConfiguration.PropertiesReader(reader);
// PropertiesConfiguration properties = new PropertiesConfiguration();
// properties.setInclude();
// properties.
// "config.properties"
//
// String fixture = null;
//
// try {
// fixture = Resources.toString(
// Resources.getResource("config.properties"), Charsets.UTF_8);
// } catch (IOException e1) {
// // TODO Auto-generated catch block
// e1.printStackTrace();
// }
//
//
// Resources.getResource("config.properties").getp
//
//
//
// Configurations configs = new Configurations();
// try {
//
// try {
// fixture = Resources.toString(
// Resources.getResource("config.properties"), Charsets.UTF_8);
// config =
// configs.properties(Resources.getResource("config.properties")
// } catch (IOException e) {
// // TODO Auto-generated catch block
// e.printStackTrace();
// }
// Path resourceDirectory = Paths.get("src","main","resources",
// "config.properties");
// File f = resourceDirectory.toFile();
// File resourcesDirectory = new File(resourceDirectory);
// f.exists()
//
// try {
// URI myURL = Resources.getResource("config.properties").toURI();
// File f = new File(myURL);
// config = configs.properties(f);
// } catch (ConfigurationException | URISyntaxException e) {
// // TODO Auto-generated catch block
// e.printStackTrace();
// }
// File resourcesDirectory = new File("src/main/resources");

// ClassLoader classLoader = AppConfiguration.class.getClassLoader();
// File file = new
// File(classLoader.getResource("config.properties").getFile());
// System.out.println(file.getAbsolutePath());
//

// String filePath =
// AppConfiguration.class.getName()getCanonicalName()getResource(
// Constants.PROPERTIES_FILENAME).getFile();
// File file = new File(filePath);

// if (file.exists(Resources.getResource("config.properties"))) {

// config = configs.properties(file);
// } else {
//
// throw new FileNotFoundException(
// "Could not locate properties file");
// }

// } catch (ConfigurationException ex) {
// ex.printStackTrace();
// } catch (FileNotFoundException ex) {
// ex.printStackTrace();
// }

// private static void initializeProperties() {
// PropertiesConfiguration propConfiguration = new
// PropertiesConfiguration();
// propConfiguration.
// Configurations configs = new Configurations();
// try {
// String filePath = AppConfiguration.class.getResource(
// Constants.PROPERTIES_FILENAME).getFile();
// File file = new File(filePath);
//
// if (file.exists()) {
// config = configs.properties(file);
// } else {
//
// throw new FileNotFoundException(
// "Could not locate properties file");
// }
//
// } catch (ConfigurationException ex) {
// ex.printStackTrace();
// } catch (FileNotFoundException ex) {
// ex.printStackTrace();
// }
//
// }