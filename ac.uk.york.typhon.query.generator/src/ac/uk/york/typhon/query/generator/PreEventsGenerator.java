package ac.uk.york.typhon.query.generator;

import ac.uk.york.typhon.query.generator.helper.GeneratorConstants;
import ac.uk.york.typhon.query.generator.source.ISource;
import ac.uk.york.typhon.query.generator.source.impl.CsvSourceImpl;
import ac.uk.york.typhon.query.generator.source.impl.SkyServerSourceImpl;

/*
 * This class is to simulate TyphonQL generated PRE events
 */

public class PreEventsGenerator {

	public static void main(String[] args) throws Exception {

		// Generate Random CRUD statements using a CSV table extract
		// ISource source = new
		// CsvSourceImpl(GeneratorConstants.FileNames.NON_FINANCIAL_EVENTS);

		ISource source = new CsvSourceImpl(
				GeneratorConstants.FileNames.FINANCIAL_TRANSATIONS);

		// Generate PreEvents from a SQl Log of SkyServer
		// http://skyserver.sdss.org
		// http://skyserver.sdss.org/log/en/traffic/sql.asp
		// ISource source = new SkyServerSourceImpl();

		// continuous load of events from the CSV and send them to the database
		// and the message queue.
		//while (true) {
			source.generate();
		//}

	}

}
