package ac.york.typhon.generator;

import ac.york.typhon.generator.generators.IGenerator;
import ac.york.typhon.generator.generators.impl.TwickyGeneratorImpl;
import ac.york.typhon.generator.helper.GeneratorConstants;
import ac.york.typhon.generator.source.IFileSource;
import ac.york.typhon.generator.source.impl.LocalFileSouceImpl;
import ac.york.typhon.generator.source.impl.RemoteFileSourceImpl;

/*
 * This class is to simulate TyphonQL generated PRE events
 */

public class PreEventsGenerator {

	public static void main(String[] args) throws Exception {

		// Generate Random CRUD statements using a CSV table extract
		
		
		// ISource source =
		// new(CsvSourceImpl(GeneratorConstants.FileNames.NON_FINANCIAL_EVENTS);
		
//		 IFileSource source =
//		 new LocalFileSouceImpl(GeneratorConstants.FileNames.TWICKY_CSV_CAUSING_ISSUE_COMPLETE_FILE);
		
		// ISource source = new
		// CsvSourceImpl(GeneratorConstants.FileNames.FINANCIAL_TRANSATIONS);
		
		// ISource source = new SkyServerSourceImpl();

		// ISource source = new AlphaBankMariaDBImpl();
//		ISource source = new RemoteCSVSourceImpl();

		// load of events from source and send to Kafka topic
		
		IGenerator generator = new TwickyGeneratorImpl();
		generator.generate();

	}

}
