package com.twicky.generator;

import java.sql.Timestamp;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

import ac.york.typhon.analytics.commons.datatypes.events.PreEvent;
import ac.york.typhon.generator.generators.impl.CSVGeneratorImpl;
import ac.york.typhon.generator.helper.GeneratorConstants.ResourceHeader;
import ac.york.typhon.generator.helper.Utils;
import ac.york.typhon.generator.source.IFileSource;

public class TwickyCSVGeneratorImpl extends CSVGeneratorImpl {

	public TwickyCSVGeneratorImpl(IFileSource.Host fileHost) {
		super(fileHost);
		super.csvFormat = CSVFormat.ORACLE.withHeader(
				ResourceHeader.EVENT_TIME, ResourceHeader.USER_HOST,
				ResourceHeader.THREAD_ID, ResourceHeader.SERVER_ID,
				ResourceHeader.COMMAND_TYPE, ResourceHeader.ARGUMENT);

	}

	@Override
	public PreEvent populatePreEvent(CSVRecord record) {

		String id = Utils.generateRandomId();
		String query = record.get(ResourceHeader.ARGUMENT);
		Timestamp timestamp = Timestamp.valueOf(record
				.get(ResourceHeader.EVENT_TIME));
		// Timestamp timestamp = Utils.generateTimeStamp();
		String user = record.get(ResourceHeader.USER_HOST);
		PreEvent preEvent = new PreEvent(id, query, user, timestamp, "dbUser");
		return preEvent;
	}

}
