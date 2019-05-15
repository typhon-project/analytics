package ac.uk.york.typhon.analytics.casestudies.alphabank.analyticsTasks;

import java.io.StringReader;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import org.apache.flink.api.common.functions.FilterFunction;
import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.datastream.DataStreamSink;

import ac.uk.york.typhon.analytics.casestudies.alphabank.simulator.datatypes.FNC_EV;
import ac.uk.york.typhon.analytics.commons.datatypes.events.Event;
import ac.uk.york.typhon.analytics.commons.datatypes.events.PreEvent;
import ac.uk.york.typhon.analytics.commons.enums.AnalyticTopicType;
import ac.uk.york.typhon.analytics.messaging.StreamManager;
import ac.uk.york.typhon.analytics.postEventAnalytics.PostEventAnalyticsTask;
import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.expression.operators.relational.ExpressionList;
import net.sf.jsqlparser.parser.CCJSqlParserManager;
import net.sf.jsqlparser.statement.Statement;
import net.sf.jsqlparser.statement.insert.Insert;

public class TopMerchants extends PostEventAnalyticsTask {
	
	@Override
	public DataStream<Event> analyse(DataStream<Event> postEvents) throws Exception {
		DataStreamSink<FNC_EV> results = postEvents
		.filter(new FilterFunction<Event>() {
			
			@Override
			public boolean filter(Event arg0) throws Exception {
				System.out.println(arg0);
				if (arg0.getQuery().toLowerCase().contains("insert into table fnc_ev")) {
					System.out.println("Correct");
					return true;
				}
				return false;
			}
		})
		.map(new MapFunction<Event, FNC_EV>() {

			@Override
			public FNC_EV map(Event event) throws Exception {
				CCJSqlParserManager pm = new CCJSqlParserManager();

				String query = event.getQuery();
				return createFncEvFromSQLInsertStatement(pm.parse(new StringReader(query)));
				
			}
		})
		//.keyBy("MRCH_ID")
		//.sum("FNC_EV_AMT")
		.print();
		
		return postEvents;
	}
	
	public static FNC_EV createFncEvFromSQLInsertStatement(Statement stmt) throws NumberFormatException, ParseException {
		
		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat timeFormat = new SimpleDateFormat(
	            "yyyy-MM-dd hh:mm:ss:SSS");
		
		ExpressionList expressionList = (ExpressionList) ((Insert) stmt).getItemsList();
		List<Expression> valueList = expressionList.getExpressions();
		
		long FNC_EV_ID = Long.parseLong(valueList.get(0).toString().replaceAll("'", ""));
		long FNC_EV_AC_ID = Long.parseLong(valueList.get(1).toString().replaceAll("'", ""));
		java.util.Date FNC_EV_DT_java = sdf1.parse(valueList.get(2).toString().replaceAll("'", ""));
		Date FNC_EV_DT = new java.sql.Date(FNC_EV_DT_java.getTime());
		String FNC_EV_SIGN_CODE_DSC = valueList.get(3).toString().replaceAll("'", "");
		String FNC_EV_SIGN_CODE = valueList.get(4).toString().replaceAll("'", "");
		BigDecimal FNC_EV_AMT = new BigDecimal(valueList.get(5).toString().replaceAll("'", ""));
		String FNC_EV_TUN_CODE = valueList.get(6).toString().replaceAll("'", "");
		java.util.Date EFF_DT_java = sdf1.parse(valueList.get(7).toString().replaceAll("'", ""));
		Date EFF_DT = new java.sql.Date(EFF_DT_java.getTime());
		java.util.Date END_DT_java = sdf1.parse(valueList.get(8).toString().replaceAll("'", ""));
		Date END_DT = new java.sql.Date(END_DT_java.getTime());
		String MRCH_ID = valueList.get(9).toString().replaceAll("'", "");
		String MRCH_NAME = valueList.get(10).toString().replaceAll("'", "");
		long MCG_ID = Long.parseLong(valueList.get(11).toString().replaceAll("'", ""));
		String MCG = valueList.get(12).toString().replaceAll("'", "");
		String MCG_DSC = valueList.get(13).toString().replaceAll("'", "");
		String FNC_EV_TP_CODE = valueList.get(14).toString().replaceAll("'", "");
		String FNC_EV_TP_DSC = valueList.get(15).toString().replaceAll("'", "");
		java.util.Date ISRT_TMS_java = sdf1.parse(valueList.get(16).toString().replaceAll("'", ""));
		Timestamp ISRT_TMS = new Timestamp(ISRT_TMS_java.getTime());
		String FNC_EV_SRC_STM_CODE = valueList.get(17).toString().replaceAll("'", "");
	
		
		return new FNC_EV(FNC_EV_ID, FNC_EV_AC_ID, FNC_EV_DT, FNC_EV_SIGN_CODE_DSC, FNC_EV_SIGN_CODE, FNC_EV_AMT, FNC_EV_TUN_CODE, EFF_DT, END_DT, MRCH_ID, 
				MRCH_NAME, MCG_ID, MCG, MCG_DSC, FNC_EV_TP_CODE, FNC_EV_TP_DSC, ISRT_TMS, FNC_EV_SRC_STM_CODE);
	}

}
