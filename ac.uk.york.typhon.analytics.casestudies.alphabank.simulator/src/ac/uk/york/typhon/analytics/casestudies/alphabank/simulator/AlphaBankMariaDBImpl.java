package ac.uk.york.typhon.analytics.casestudies.alphabank.simulator;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.commons.lang3.RandomUtils;

import ac.uk.york.typhon.analytics.casestudies.alphabank.simulator.datatypes.FNC_EV;
import ac.uk.york.typhon.analytics.commons.datatypes.events.Event;
import ac.uk.york.typhon.analytics.commons.datatypes.events.PreEvent;
import ac.uk.york.typhon.analytics.commons.enums.AnalyticTopicType;
import ac.uk.york.typhon.analytics.messaging.TopicPublisher;
import org.apache.commons.lang3.RandomUtils;
import ac.uk.york.typhon.query.generator.helper.Utils;
import ac.uk.york.typhon.query.generator.source.ISource;
import ac.uk.york.typhon.query.generator.source.impl.SourceImpl;

public class AlphaBankMariaDBImpl extends SourceImpl implements ISource {

	@Override
	public void generate() throws Exception {
		Connection connection;
		try {
			connection = DriverManager.getConnection("jdbc:mariadb://localhost:3308/AlphaBank?user=thanos");
			Statement stmt = connection.createStatement();
			stmt.execute("SELECT * FROM FNC_EV ORDER BY FNC_EV_DT ASC");
			ResultSet results = stmt.getResultSet();
			String id = null;

			while (results.next()) {
				id = Utils.generateRandomId();
				
				FNC_EV fnc = new FNC_EV(results.getLong("fNC_EV_ID"), results.getLong("fNC_EV_AC_ID"), results.getDate("fNC_EV_DT"), results.getString("fNC_EV_SIGN_CODE_DSC"), 
						results.getString("fNC_EV_SIGN_CODE"), results.getBigDecimal("fNC_EV_AMT"), results.getString("fNC_EV_TUN_CODE"), 
						results.getDate("eFF_DT"), results.getDate("eND_DT"), results.getString("mRCH_ID"), results.getString("mRCH_NAME"),
						results.getLong("mCG_ID"), results.getString("mCG"), results.getString("mCG_DSC"), 
						results.getString("fNC_EV_TP_CODE"), results.getString("fNC_EV_TP_DSC"), results.getTimestamp("iSRT_TMS"),
						results.getString("fNC_EV_SRC_STM_CODE"));
						
				
				
				String query = fnc.toInsertSQLString();
				
				Thread.sleep(RandomUtils.nextLong(0, 500));

				Event preEvent = new PreEvent(id, query, "user",
						Utils.generateTimeStamp(), "dbUser");

				TopicPublisher.publish(AnalyticTopicType.PRE, preEvent);

				Thread.sleep(RandomUtils.nextLong(0, 500));
		     
		    }
			stmt.close();
			connection.close();
		} catch (SQLException e) {
			System.err.println("Connection to MariaDB failed");
			e.printStackTrace();
		}
		
	}

}
