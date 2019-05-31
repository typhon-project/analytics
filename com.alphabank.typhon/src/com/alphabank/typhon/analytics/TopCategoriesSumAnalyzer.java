package com.alphabank.typhon.analytics;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.apache.flink.api.common.functions.FilterFunction;
import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.api.java.tuple.Tuple3;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.datastream.DataStreamSink;
import org.apache.flink.streaming.api.windowing.time.Time;

import ac.uk.york.typhon.analytics.commons.AppConfiguration;
import ac.uk.york.typhon.analytics.commons.datatypes.events.Event;
import ac.uk.york.typhon.analytics.process.StreamAnalyzer;

import com.alphabank.typhon.analytics.assigner.BoundedOutOfOrdernessGenerator;
import com.alphabank.typhon.commons.AlphaConstants;
import com.alphabank.typhon.dataaccess.impl.AnalyticsResultsAccessImpl;
import com.alphabank.typhon.dto.FinancialEvent;
import com.alphabank.typhon.extractor.insert.FinancialEventInsertExtractor;

public class TopCategoriesSumAnalyzer extends StreamAnalyzer {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static Connection connection;

	@Override
	public DataStream<Event> analyse(DataStream<Event> postEvents) throws Exception {
		DataStreamSink<Tuple3<String, String, Double>> results = postEvents.filter(new FilterFunction<Event>() {

			@Override
			public boolean filter(Event arg0) throws Exception {
				if (arg0.getQuery().toLowerCase().contains("insert into fnc_ev")) {
					return true;
				}
				return false;
			}
		}).map(new MapFunction<Event, FinancialEvent>() {

			@Override
			public FinancialEvent map(Event event) throws Exception {

				FinancialEventInsertExtractor financialEventInsertExtractor = new FinancialEventInsertExtractor(
						event.getQuery());

				FinancialEvent financialEvent = new FinancialEvent(financialEventInsertExtractor);

				// CCJSqlParserManager pm = new CCJSqlParserManager();
				//
				// String query = event.getQuery();
				// return createFncEvFromSQLInsertStatement(pm
				// .parse(new StringReader(query)));

				return financialEvent;

			}
		}).filter(new FilterFunction<FinancialEvent>() {

			@Override
			public boolean filter(FinancialEvent financialEvent) throws Exception {
				return financialEvent.getSignCode().equals("CREDIT");
			}
		}).assignTimestampsAndWatermarks(new BoundedOutOfOrdernessGenerator()).keyBy("mcgDescription")
				.timeWindow(Time.days(30)).sum("amount")
				.map(new MapFunction<FinancialEvent, Tuple3<String, String, Double>>() {

					@Override
					public Tuple3<String, String, Double> map(FinancialEvent financialEvent) throws Exception {
						Tuple3<String, String, Double> result = new Tuple3<String, String, Double>();
						String month = financialEvent.getDate().toLocalDate().getMonth().toString();
						String year = Integer.toString(financialEvent.getDate().toLocalDate().getYear());
						result.f0 = financialEvent.getMcgDescription();
						result.f1 = month + " " + year;
						result.f2 = financialEvent.getAmount();
						System.out.println(financialEvent.getMcgDescription() + " " + month + " " + year + " "
								+ financialEvent.getAmount());
						return result;
					}
				}).map(new MapFunction<Tuple3<String, String, Double>, Tuple3<String, String, Double>>() {

					@Override
					public Tuple3<String, String, Double> map(Tuple3<String, String, Double> arg0) throws Exception {
						connection = AnalyticsResultsAccessImpl.getConnection();

						String query = "insert into TopCategoriesSumResults (category_name, month_year, sum)"
								+ " values (?, ?, ?)";

						System.out.println(query);
						// create the mysql insert preparedstatement
						PreparedStatement preparedStmt = connection.prepareStatement(query);
						preparedStmt.setString(1, arg0.f0);
						preparedStmt.setString(2, arg0.f1);
						preparedStmt.setDouble(3, arg0.f2);

						// execute the preparedstatement
						preparedStmt.execute();

						// connection.close();
						return arg0;
					}

				}).print();

		return postEvents;
	}

}

// public static FNC_EV createFncEvFromSQLInsertStatement(Statement stmt)
// throws NumberFormatException, ParseException {
//
// SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
// SimpleDateFormat timeFormat = new SimpleDateFormat(
// "yyyy-MM-dd hh:mm:ss:SSS");
//
// ExpressionList expressionList = (ExpressionList) ((Insert) stmt)
// .getItemsList();
// List<Expression> valueList = expressionList.getExpressions();
//
// long FNC_EV_ID = Long.parseLong(valueList.get(0).toString()
// .replaceAll("'", ""));
// long FNC_EV_AC_ID = Long.parseLong(valueList.get(1).toString()
// .replaceAll("'", ""));
// java.util.Date FNC_EV_DT_java = sdf1.parse(valueList.get(2).toString()
// .replaceAll("'", ""));
// Date FNC_EV_DT = new java.sql.Date(FNC_EV_DT_java.getTime());
// String FNC_EV_SIGN_CODE_DSC = valueList.get(3).toString()
// .replaceAll("'", "");
// String FNC_EV_SIGN_CODE = valueList.get(4).toString()
// .replaceAll("'", "");
// double FNC_EV_AMT = new BigDecimal(valueList.get(5).toString()
// .replaceAll("'", "")).doubleValue();
// String FNC_EV_TUN_CODE = valueList.get(6).toString()
// .replaceAll("'", "");
// java.util.Date EFF_DT_java = sdf1.parse(valueList.get(7).toString()
// .replaceAll("'", ""));
// Date EFF_DT = new java.sql.Date(EFF_DT_java.getTime());
// java.util.Date END_DT_java = sdf1.parse(valueList.get(8).toString()
// .replaceAll("'", ""));
// Date END_DT = new java.sql.Date(END_DT_java.getTime());
// String MRCH_ID = valueList.get(9).toString().replaceAll("'", "");
// String MRCH_NAME = valueList.get(10).toString().replaceAll("'", "");
// long MCG_ID = Long.parseLong(valueList.get(11).toString()
// .replaceAll("'", ""));
// String MCG = valueList.get(12).toString().replaceAll("'", "");
// String MCG_DSC = valueList.get(13).toString().replaceAll("'", "");
// String FNC_EV_TP_CODE = valueList.get(14).toString()
// .replaceAll("'", "");
// String FNC_EV_TP_DSC = valueList.get(15).toString().replaceAll("'", "");
// java.util.Date ISRT_TMS_java = sdf1.parse(valueList.get(16).toString()
// .replaceAll("'", ""));
// Timestamp ISRT_TMS = new Timestamp(ISRT_TMS_java.getTime());
// String FNC_EV_SRC_STM_CODE = valueList.get(17).toString()
// .replaceAll("'", "");
//
// return new FNC_EV(FNC_EV_ID, FNC_EV_AC_ID, FNC_EV_DT,
// FNC_EV_SIGN_CODE_DSC, FNC_EV_SIGN_CODE, FNC_EV_AMT,
// FNC_EV_TUN_CODE, EFF_DT, END_DT, MRCH_ID, MRCH_NAME, MCG_ID,
// MCG, MCG_DSC, FNC_EV_TP_CODE, FNC_EV_TP_DSC, ISRT_TMS,
// FNC_EV_SRC_STM_CODE);
// }