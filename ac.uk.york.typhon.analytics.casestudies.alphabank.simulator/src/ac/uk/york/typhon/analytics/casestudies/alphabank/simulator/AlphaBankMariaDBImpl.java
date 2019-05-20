package ac.uk.york.typhon.analytics.casestudies.alphabank.simulator;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;

import org.apache.commons.lang3.RandomUtils;

import ac.uk.york.typhon.analytics.casestudies.alphabank.simulator.datatypes.FNC_EV;
import ac.uk.york.typhon.analytics.casestudies.alphabank.simulator.datatypes.NON_FNC_EV;
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
			connection = DriverManager.getConnection("jdbc:mariadb://localhost:3308/Alpha?user=thanos");
			
			Statement fncEvStmt = connection.createStatement();
			fncEvStmt.execute("SELECT * FROM FNC_EV ORDER BY FNC_EV_DT ASC");
			ResultSet fncEvResults = fncEvStmt.getResultSet();
			
			Statement nonFncEvStmt = connection.createStatement();
			nonFncEvStmt.execute("SELECT * FROM NON_FNC_EV ORDER BY NON_FNC_EV_DT_TM ASC");
			ResultSet nonFncEvResults = nonFncEvStmt.getResultSet();
			String id = null;
			while (fncEvResults.next()) {
				while (nonFncEvResults.next()) {
					id = Utils.generateRandomId();
					Thread.sleep(RandomUtils.nextLong(0, 1000));
					if (fncIsAfterNonFnc(fncEvResults.getDate("FNC_EV_DT"), nonFncEvResults.getTimestamp("NON_FNC_EV_DT_TM"))) {
						NON_FNC_EV nonFnc = new NON_FNC_EV(nonFncEvResults.getLong("NON_FNC_EV_ID"), nonFncEvResults.getString("NON_FNC_EV_TUN_CODE"), nonFncEvResults.getString("NON_FNC_EV_TP_CODE"), 
								nonFncEvResults.getLong("NON_FNC_EV_AC_ID"), nonFncEvResults.getString("NON_FNC_EV_AC_CODE"), nonFncEvResults.getString("NON_FNC_EV_ACTN_CODE"), nonFncEvResults.getString("NON_FNC_EV_ACTN_DSC"), 
								nonFncEvResults.getTimestamp("NON_FNC_EV_DT_TM"), nonFncEvResults.getString("NON_FNC_EV_CDI_CODE"), nonFncEvResults.getDate("EFF_DT"), 
								nonFncEvResults.getDate("END_DT"));
						String query = nonFnc.toInsertSQLString();
						Event preEvent = new PreEvent(id, query, "user",
								Utils.generateTimeStamp(), "dbUser");
						TopicPublisher.publish(AnalyticTopicType.PRE, preEvent);
						System.out.println("NON FNC Date: " + nonFncEvResults.getTimestamp("NON_FNC_EV_DT_TM"));
		
					} else {
						FNC_EV fnc = new FNC_EV(fncEvResults.getLong("fNC_EV_ID"), fncEvResults.getLong("fNC_EV_AC_ID"), fncEvResults.getDate("fNC_EV_DT"), fncEvResults.getString("fNC_EV_SIGN_CODE_DSC"), 
								fncEvResults.getString("fNC_EV_SIGN_CODE"), fncEvResults.getDouble("fNC_EV_AMT"), fncEvResults.getString("fNC_EV_TUN_CODE"), 
								fncEvResults.getDate("eFF_DT"), fncEvResults.getDate("eND_DT"), fncEvResults.getString("mRCH_ID"), fncEvResults.getString("mRCH_NAME"),
								fncEvResults.getLong("mCG_ID"), fncEvResults.getString("mCG"), fncEvResults.getString("mCG_DSC"), 
								fncEvResults.getString("fNC_EV_TP_CODE"), fncEvResults.getString("fNC_EV_TP_DSC"), fncEvResults.getTimestamp("iSRT_TMS"),
								fncEvResults.getString("fNC_EV_SRC_STM_CODE"));
						String query = fnc.toInsertSQLString();
						Event preEvent = new PreEvent(id, query, "user",
								Utils.generateTimeStamp(), "dbUser");
						TopicPublisher.publish(AnalyticTopicType.PRE, preEvent);
						// Go one step back in non fnc so when the NON FNC while loop condition will be called again, the cursor will be moved to the current one
						nonFncEvResults.previous();
						// Go back to the 1st loop
						System.out.println("FNC Date: " + fncEvResults.getDate("FNC_EV_DT"));
						break;
					}
				}
		    }
			nonFncEvStmt.close();
			fncEvStmt.close();
			connection.close();
		} catch (SQLException e) {
			System.err.println("Connection to MariaDB failed");
			e.printStackTrace();
		}
		
	}
	
	private Boolean fncIsAfterNonFnc(Date fncEvDate, Timestamp nonFncEvDateTime) {
		Timestamp fncEvDateTime = new Timestamp(fncEvDate.getTime());
		if (fncEvDateTime.after(nonFncEvDateTime)) {
			return true;
		} else {
			return false;
		}
	}

}
