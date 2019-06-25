package ac.york.typhon.generator;

import ac.york.typhon.generator.source.ISource;
import ac.york.typhon.generator.source.impl.AlphaBankMariaDBImpl;

/*
 * This class is to simulate TyphonQL generated PRE events
 */

public class PreEventsGenerator {

	public static void main(String[] args) throws Exception {

		// Generate Random CRUD statements using a CSV table extract
		// ISource source = new
		// CsvSourceImpl(GeneratorConstants.FileNames.NON_FINANCIAL_EVENTS);

		// ISource source = new CsvSourceImpl(
		// GeneratorConstants.FileNames.FINANCIAL_TRANSATIONS);

		// Generate PreEvents from a SQl Log of SkyServer
		// http://skyserver.sdss.org
		// http://skyserver.sdss.org/log/en/traffic/sql.asp
		// ISource source = new SkyServerSourceImpl();

		// continuous load of events from the CSV and send them to the database
		// and the message queue.
		// while (true) {
		// source.generate();
		// }

		ISource source = new AlphaBankMariaDBImpl();

//		while (true) {
			source.generate();
//		}

	}

}