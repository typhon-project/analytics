package ac.york.typhon.generator.source.impl;

import java.io.FileReader;
import java.io.Reader;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

import com.google.common.io.Resources;

public abstract class SourceImpl {

	public Iterable<CSVRecord> retrieveRecordsIterator(String fileName)
			throws Exception {

		URL url = Resources.getResource(fileName);

		// URL url = ClassLoaderUtil.getResource(
		// GeneratorConstants.CSV.FILE_NAME, PreEventsGenerator.class);

		Path path = Paths.get(url.toURI());

		Reader reader = new FileReader(path.toString());
		Iterable<CSVRecord> records = null;

		records = CSVFormat.EXCEL.withHeader().parse(reader);

		return records;
	}

}
