package ac.york.typhon.generator;

import org.apache.commons.csv.CSVFormat;

import ac.york.typhon.analytics.commons.AppConfiguration;
import ac.york.typhon.generator.generators.IGenerator;
import ac.york.typhon.generator.generators.impl.CSVGeneratorImpl;
import ac.york.typhon.generator.generators.impl.TwickyGeneratorImpl;
import ac.york.typhon.generator.helper.GeneratorConstants;
import ac.york.typhon.generator.helper.GeneratorConstants.ResourceHeader;
import ac.york.typhon.generator.source.IFileSource;
import ac.york.typhon.generator.source.impl.LocalFileSouceImpl;
import ac.york.typhon.generator.source.impl.RemoteFileSourceImpl;

/*
 * This class is to simulate TyphonQL generated PRE events
 */

public class PreEventsGenerator {

	private static String user;
	private static String host;
	private static String password;
	private static int port;
	private static String filePath;

	static {
		// loadRemoteConfiguration();
		loadLocalConfiguration();
	}

	private static void loadRemoteConfiguration() {
		user = AppConfiguration
				.getString(GeneratorConstants.RemoteResourceCredentials.USERNAME);
		host = AppConfiguration
				.getString(GeneratorConstants.RemoteResourceCredentials.HOST);
		password = AppConfiguration
				.getString(GeneratorConstants.RemoteResourceCredentials.PASSWORD);
		port = AppConfiguration
				.getInteger(GeneratorConstants.RemoteResourceCredentials.PORT);
		filePath = AppConfiguration
				.getString(GeneratorConstants.RemoteResourceCredentials.FILE_PATH);

	}

	private static void loadLocalConfiguration() {
		filePath = AppConfiguration
				.getString(GeneratorConstants.LocalResourceCredentials.FILE_PATH);

	}

	public static void main(String[] args) throws Exception {

		// Generate Random CRUD statements using a CSV table extract

		// ISource source =
		// new(CsvSourceImpl(GeneratorConstants.FileNames.NON_FINANCIAL_EVENTS);

		// IFileSource source =
		// new
		// LocalFileSouceImpl(GeneratorConstants.FileNames.TWICKY_CSV_CAUSING_ISSUE_COMPLETE_FILE);

		// ISource source = new
		// CsvSourceImpl(GeneratorConstants.FileNames.FINANCIAL_TRANSATIONS);

		// ISource source = new SkyServerSourceImpl();

		// ISource source = new AlphaBankMariaDBImpl();
		// ISource source = new RemoteCSVSourceImpl();

		// load of events from source and send to Kafka topic

		// IGenerator generator = new TwickyGeneratorImpl();
		// generator.generate();

//		IFileSource source = new LocalFileSouceImpl("");
//
//		CSVFormat csvFormat = CSVFormat.ORACLE.withHeader(
//				ResourceHeader.EVENT_TIME,
//				ResourceHeader.USER_HOST, ResourceHeader.THREAD_ID,
//				ResourceHeader.SERVER_ID,
//				ResourceHeader.COMMAND_TYPE,
//				ResourceHeader.ARGUMENT);
//
//		IGenerator generator = new CSVGeneratorImpl(source, csvFormat);
//		generator.generate();
//
//		source.closeStream();
		
		

	}

}
