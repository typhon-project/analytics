package ac.york.typhon.generator.generators.impl;

import java.sql.Timestamp;
import java.text.ParseException;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.lang3.RandomUtils;

import ac.york.typhon.analytics.commons.AppConfiguration;
import ac.york.typhon.analytics.commons.datatypes.events.Event;
import ac.york.typhon.analytics.commons.datatypes.events.PreEvent;
import ac.york.typhon.generator.generators.impl.CSVGeneratorImpl;
import ac.york.typhon.generator.helper.GeneratorConstants;
import ac.york.typhon.generator.helper.GeneratorConstants.ResourceHeader;
import ac.york.typhon.generator.helper.Utils;
import ac.york.typhon.generator.source.IFileSource;
import ac.york.typhon.generator.source.impl.LocalFileSouceImpl;
import ac.york.typhon.generator.source.impl.RemoteFileSourceImpl;

public class SkyServerCSVGeneratorImpl extends CSVGeneratorImpl {

	public SkyServerCSVGeneratorImpl(IFileSource.Host host) {

		super(host);
		super.csvFormat = CSVFormat.EXCEL.withHeader();

	}

	@Override
	public PreEvent populatePreEvent(CSVRecord record) {
		String id = record.get("seq");
		String user = record.get("requestor");
		String dbUser = record.get("dbname");

		String year = record.get("yy");
		String month = record.get("mm");
		String day = record.get("dd");

		String hour = record.get("hh");
		String minutes = record.get("mi");
		String seconds = record.get("ss");

		String dateStr = year + "-" + month + "-" + day + " " + hour + ":"
				+ minutes + ":" + seconds;

		Timestamp timestamp = null;
		try {
			timestamp = Utils.convertStringToTimeStamp(dateStr);
		} catch (ParseException e) {

			e.printStackTrace();
		}
		String query = record.get("statement");

		PreEvent preEvent = new PreEvent(id, query, user, timestamp, dbUser);

		return preEvent;
	}

}
