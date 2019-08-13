package ac.york.typhon.generator.source.impl;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.lang3.StringUtils;

import com.google.common.io.Resources;

import ac.york.typhon.analytics.commons.datatypes.events.Event;
import ac.york.typhon.analytics.commons.datatypes.events.PreEvent;
import ac.york.typhon.analytics.commons.enums.AnalyticTopicType;
import ac.york.typhon.analytics.messaging.TopicPublisher;
import ac.york.typhon.generator.helper.Utils;
import ac.york.typhon.generator.helper.GeneratorConstants.RemoteResourceHeader;
import ac.york.typhon.generator.source.IFileSource;
import ac.york.typhon.generator.sqlbuilder.StatementFactory;

public class LocalFileSouceImpl extends FileSourceImpl implements IFileSource {

	private String absoluteFilePath;
	private Reader reader;

	public LocalFileSouceImpl(String absoluteFilePath) {
		this.absoluteFilePath = absoluteFilePath;
	}

	@Override
	public Reader getReader() {

		try {
			reader = new FileReader(absoluteFilePath);
		} catch (FileNotFoundException e) {

			e.printStackTrace();
		}

		return reader;
	}

	@Override
	public void closeStream() {

		try {
			reader.close();
		} catch (IOException e) {

			e.printStackTrace();
		}

	}

	// @Override
	// public void generate() throws Exception {
	// try {
	//
	// // load events from the CSV files
	// // Iterable<CSVRecord> records =
	// // retrieveRecordsIterator(this.csvFileName);
	// //
	// //
	// //
	// // StatementFactory.setTableName(this.csvFileName.split("\\.")[0]);
	// // String id = null;
	//
	// // URL url = Resources.getResource(this.csvFileName);
	// // URL url = new File(this.csvFileName).toURI().toURL();
	// //
	// // // URL url = ClassLoaderUtil.getResource(
	// // // GeneratorConstants.CSV.FILE_NAME, PreEventsGenerator.class);
	// //
	// // Path path = Paths.get(url.toURI());
	// //
	// // Reader reader = new FileReader(path.toString());
	//
	// // Reader reader =
	// // this.getResourceReaderWithAbsolutePath(this.csvFileName);
	// // Reader reader =
	// // this.getResourceReaderWithFileName(this.csvFileName);
	//
	// // Iterable<CSVRecord> records =
	// // CSVFormat.EXCEL.withHeader().parse(reader);
	//
	// Iterable<CSVRecord> records = CSVFormat.MYSQL.withHeader(
	// RemoteResourceHeader.EVENT_TIME,
	// RemoteResourceHeader.USER_HOST,
	// RemoteResourceHeader.THREAD_ID,
	// RemoteResourceHeader.SERVER_ID,
	// RemoteResourceHeader.COMMAND_TYPE,
	// RemoteResourceHeader.ARGUMENT).parse(reader);
	//
	// for (CSVRecord record : records) {
	// System.out.println(record);
	//
	// // String query = StatementFactory.initializeDMLString(record);
	// //
	// // if (StringUtils.isNotBlank(query)) {
	// //
	// // id = Utils.generateRandomId();
	// //
	// // Thread.sleep(RandomUtils.nextLong(0, 1000));
	// //
	// // Event preEvent = new PreEvent(id, query, "user",
	// // Utils.generateTimeStamp(), "dbUser");
	// //
	// // TopicPublisher.publish(AnalyticTopicType.PRE, preEvent);
	// //
	// // Thread.sleep(RandomUtils.nextLong(0, 1000));
	// //
	// // }
	// //
	//
	// }
	// } catch (FileNotFoundException e1) {
	//
	// e1.printStackTrace();
	// }
	//
	// }

}
