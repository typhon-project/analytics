package ac.york.typhon.generator.generators.impl;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.lang3.RandomUtils;

import ac.york.typhon.analytics.commons.AppConfiguration;
import ac.york.typhon.analytics.commons.datatypes.events.Event;
import ac.york.typhon.analytics.commons.datatypes.events.PreEvent;
import ac.york.typhon.analytics.commons.enums.AnalyticTopicType;
import ac.york.typhon.analytics.streaming.TopicPublisher;
import ac.york.typhon.generator.generators.IGenerator;
import ac.york.typhon.generator.helper.GeneratorConstants;
import ac.york.typhon.generator.source.IFileSource;
import ac.york.typhon.generator.source.impl.LocalFileSouceImpl;
import ac.york.typhon.generator.source.impl.RemoteFileSourceImpl;

public abstract class CSVGeneratorImpl implements IGenerator {
	protected IFileSource fileSource;
	protected CSVFormat csvFormat;

	public CSVGeneratorImpl(IFileSource.Host host) {

		switch (host) {
		case REMOTE:
			this.fileSource = loadRemoteResource();
			break;
		default:
			this.fileSource = loadLocalResource();
		}

	}

	public abstract PreEvent populatePreEvent(CSVRecord record);

	private IFileSource loadRemoteResource() {
		String user = AppConfiguration
				.getString(GeneratorConstants.RemoteResourceCredentials.USERNAME);
		String host = AppConfiguration
				.getString(GeneratorConstants.RemoteResourceCredentials.HOST);
		String password = AppConfiguration
				.getString(GeneratorConstants.RemoteResourceCredentials.PASSWORD);
		int port = AppConfiguration
				.getInteger(GeneratorConstants.RemoteResourceCredentials.PORT);
		String filePath = AppConfiguration
				.getString(GeneratorConstants.RemoteResourceCredentials.FILE_PATH);
		IFileSource source = new RemoteFileSourceImpl(host, port, user,
				password, filePath);

		return source;
	}

	private IFileSource loadLocalResource() {
		String filePath = AppConfiguration
				.getString(GeneratorConstants.LocalResourceCredentials.FILE_PATH);
		IFileSource source = new LocalFileSouceImpl(filePath);
		return source;
	}

	@Override
	public void generate() {

		Iterable<CSVRecord> records = fileSource.getRecordsIterator(csvFormat);

		for (CSVRecord record : records) {

			Event preEvent = populatePreEvent(record);
			System.out.println(preEvent);
			/*************************************************/
			/*********** Comment Sleep if NOT NEEDED *********/
			try {
				Thread.sleep(RandomUtils.nextLong(0, 10));
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			/*************************************************/
			/*************************************************/

			TopicPublisher.publish(AnalyticTopicType.PRE, preEvent);

		}

		fileSource.closeStream();

	}

}
