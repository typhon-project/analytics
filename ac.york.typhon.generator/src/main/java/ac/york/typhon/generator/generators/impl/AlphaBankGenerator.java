package ac.york.typhon.generator.generators.impl;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import org.apache.commons.lang3.RandomUtils;

import ac.york.typhon.analytics.commons.AppConfiguration;
import ac.york.typhon.analytics.commons.datatypes.events.Event;
import ac.york.typhon.analytics.commons.datatypes.events.PreEvent;
import ac.york.typhon.analytics.commons.enums.AnalyticTopicType;
import ac.york.typhon.analytics.streaming.TopicPublisher;
import ac.york.typhon.generator.commons.AlphaConstants;
import ac.york.typhon.generator.generators.IGenerator;
import ac.york.typhon.generator.helper.Utils;
import ac.york.typhon.generator.pojo.FinancialEvent;
import ac.york.typhon.generator.pojo.NonFinancialEvent;
import ac.york.typhon.generator.source.IDatabaseSource;
import ac.york.typhon.generator.source.impl.DatabaseSourceImpl;



public class AlphaBankGenerator implements IGenerator {
	private static String url;
	private static String username;
	private static String password;

	static {
		loadConfiguration();
	}

	private static void loadConfiguration() {
		url = AppConfiguration.getString(AlphaConstants.Database.URL);
		username = AppConfiguration.getString(AlphaConstants.Database.USERNAME);
		password = AppConfiguration.getString(AlphaConstants.Database.PASSWORD);

	}

	public static void main(String[] args) {
		AlphaBankGenerator generator = new AlphaBankGenerator();
		generator.generate();

	}

	@Override
	public void generate() {

		try {

			IDatabaseSource source = new DatabaseSourceImpl(url, username,
					password);

			ResultSet fncEvResults = source
					.executeStatement("SELECT * FROM FNC_EV ORDER BY FNC_EV_DT ASC");
			ResultSet nonFncEvResults = source
					.executeStatement("SELECT * FROM NON_FNC_EV ORDER BY NON_FNC_EV_DT_TM ASC");

			String id = null;
			while (fncEvResults.next()) {
				while (nonFncEvResults.next()) {
					id = Utils.generateRandomId();
					Thread.sleep(RandomUtils.nextLong(0, 1000));
					if (isFinancialAfterNonFinancial(
							fncEvResults
									.getDate(AlphaConstants.Table.FinancialEvent.DT),
							nonFncEvResults
									.getTimestamp(AlphaConstants.Table.NonFinancialEvent.DT_TM))) {

						NonFinancialEvent nonFnc = new NonFinancialEvent(
								nonFncEvResults);
						String query = nonFnc.toInsert();
						System.out.println(query);
						Event preEvent = new PreEvent(id, query, "user",
								Utils.generateTimeStamp(), "dbUser");
						TopicPublisher.publish(AnalyticTopicType.PRE, preEvent);
						// System.out
						// .println("NON FNC Date: "
						// + nonFncEvResults
						// .getTimestamp(AlphaConstants.Table.NonFinancialEvent.DT_TM));

					} else {
						FinancialEvent fnc = new FinancialEvent(fncEvResults);
						// String query = fnc.toInsertSQLString();
						String query = fnc.toInsert();
						System.out.println(query);
						Event preEvent = new PreEvent(id, query, "user",
								Utils.generateTimeStamp(), "dbUser");
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

}
