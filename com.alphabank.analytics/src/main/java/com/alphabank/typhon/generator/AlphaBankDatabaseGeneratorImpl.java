package com.alphabank.typhon.generator;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import org.apache.commons.lang3.RandomUtils;

import ac.york.typhon.analytics.commons.datatypes.events.PreEvent;
import ac.york.typhon.analytics.commons.enums.AnalyticTopicType;
import ac.york.typhon.analytics.streaming.TopicPublisher;
import ac.york.typhon.generator.commons.AlphaConstants;
import ac.york.typhon.generator.generators.impl.DatabaseGeneratorImpl;
import ac.york.typhon.generator.helper.Utils;

import com.alphabank.typhon.generator.pojo.FinancialEvent;
import com.alphabank.typhon.generator.pojo.IPOJO;
import com.alphabank.typhon.generator.pojo.NonFinancialEvent;

public class AlphaBankDatabaseGeneratorImpl extends DatabaseGeneratorImpl {

	private Class<? extends IPOJO> pojoClass;

	@Override
	public void generate() {

		try {

			ResultSet fncEvResults = source
					.executeStatement("SELECT * FROM FNC_EV ORDER BY FNC_EV_DT ASC");
			ResultSet nonFncEvResults = source
					.executeStatement("SELECT * FROM NON_FNC_EV ORDER BY NON_FNC_EV_DT_TM ASC");

			while (fncEvResults.next()) {
				while (nonFncEvResults.next()) {

					Thread.sleep(RandomUtils.nextLong(0, 1000));

					Date financialDate = fncEvResults
							.getDate(AlphaConstants.Table.FinancialEvent.DT);
					Timestamp nonFinancialDate = nonFncEvResults
							.getTimestamp(AlphaConstants.Table.NonFinancialEvent.DT_TM);

					if (isFinancialAfterNonFinancial(financialDate,
							nonFinancialDate)) {

						pojoClass = NonFinancialEvent.class;
						PreEvent preEvent = populatePreEvent(nonFncEvResults);
						// System.out.println(preEvent);
						TopicPublisher.publish(AnalyticTopicType.PRE, preEvent);
						// System.out
						// .println("NON FNC Date: "
						// + nonFncEvResults
						// .getTimestamp(AlphaConstants.Table.NonFinancialEvent.DT_TM));

					} else {

						// FinancialEvent fnc = new
						// FinancialEvent(fncEvResults);
						// PreEvent preEvent = populatePreEvent(fnc.toInsert());
						pojoClass = FinancialEvent.class;
						PreEvent preEvent = populatePreEvent(fncEvResults);
						// System.out.println(preEvent);
						TopicPublisher.publish(AnalyticTopicType.PRE, preEvent);
						// Go one step back in non fnc so when the NON FNC while
						// loop condition will be called again, the cursor will
						// be moved to the current one
						nonFncEvResults.previous();
						// Go back to the 1st loop
						// System.out
						// .println("FNC Timestamp: "
						// + fncEvResults
						// .getDate(AlphaConstants.Table.FinancialEvent.ISRT_TMS));
						break;
					}
				}
			}
			source.closeConnection();

		} catch (SQLException | InterruptedException e) {
			System.err.println("Connection to MariaDB failed");
			e.printStackTrace();
		}

	}

	private Boolean isFinancialAfterNonFinancial(Date financialEventDate,
			Timestamp nonFinancialEventDateTime) {
		Timestamp fncEvDateTime = new Timestamp(financialEventDate.getTime());
		if (fncEvDateTime.after(nonFinancialEventDateTime)) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public PreEvent populatePreEvent(ResultSet resultSet) {
		PreEvent preEvent = null;

		try {
			String id = Utils.generateRandomId();
			IPOJO pojo = pojoClass.newInstance();
			pojo.populate(resultSet);
			String query = pojo.toInsert();
			preEvent = new PreEvent(id, query, "user",
					Utils.generateTimeStamp(), "dbUser");
		} catch (InstantiationException | IllegalAccessException e) {

			e.printStackTrace();
		}
		return preEvent;
	}

}
