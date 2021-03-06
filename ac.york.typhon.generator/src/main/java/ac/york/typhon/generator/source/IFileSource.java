package ac.york.typhon.generator.source;

import java.io.Reader;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

public interface IFileSource {

	enum Host {
		LOCAL, REMOTE
	}

	Iterable<CSVRecord> getRecordsIterator(CSVFormat csvFormat);

	Reader getReader();

	void closeStream();

}
