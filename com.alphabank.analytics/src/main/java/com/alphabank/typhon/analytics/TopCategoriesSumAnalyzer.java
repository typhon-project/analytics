package com.alphabank.typhon.analytics;

import java.sql.Connection;
import java.sql.PreparedStatement;

import org.apache.flink.api.common.functions.FilterFunction;
import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.api.java.tuple.Tuple3;
import org.apache.flink.api.java.tuple.Tuple4;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.datastream.DataStreamSink;
import org.apache.flink.streaming.api.windowing.time.Time;
import java.sql.Date;

import ac.york.typhon.analytics.analyzer.IAnalyzer;
import ac.york.typhon.analytics.commons.datatypes.events.Event;

import com.alphabank.typhon.analytics.assigner.BoundedOutOfOrdernessGenerator;
import com.alphabank.typhon.dataaccess.impl.AnalyticsResultsAccessImpl;
import com.alphabank.typhon.dto.FinancialEvent;
import com.alphabank.typhon.extractor.insert.FinancialEventInsertExtractor;

public class TopCategoriesSumAnalyzer implements IAnalyzer {

	private static final long serialVersionUID = 1L;
	private static Connection connection;

	@SuppressWarnings("serial")
	@Override
	public DataStream<Event> analyze(DataStream<Event> postEvents)
			throws Exception {
		DataStreamSink<Tuple4<String, String, Double, Date>> results = postEvents
				.filter(new FilterFunction<Event>() {

					@Override
					public boolean filter(Event arg0) throws Exception {
						if (arg0.getQuery().toLowerCase()
								.contains("insert into fnc_ev")) {
							return true;
						}
						return false;
					}
				})
				.map(new MapFunction<Event, FinancialEvent>() {

					@Override
					public FinancialEvent map(Event event) throws Exception {

						FinancialEventInsertExtractor financialEventInsertExtractor = new FinancialEventInsertExtractor(
								event.getQuery());

						FinancialEvent financialEvent = new FinancialEvent(
								financialEventInsertExtractor);

						return financialEvent;

					}
				})
				.filter(new FilterFunction<FinancialEvent>() {

					@Override
					public boolean filter(FinancialEvent financialEvent)
							throws Exception {
						return financialEvent.getSignCode().equals("CREDIT");
					}
				})
				.assignTimestampsAndWatermarks(
						new BoundedOutOfOrdernessGenerator())
				.keyBy("mcgDescription")
				.timeWindow(Time.days(30))
				.sum("amount")
				.map(new MapFunction<FinancialEvent, Tuple4<String, String, Double, Date>>() {

					@Override
					public Tuple4<String, String, Double, Date> map(
							FinancialEvent financialEvent) throws Exception {
						Tuple4<String, String, Double, Date> result = new Tuple4<String, String, Double, Date>();
						String month = financialEvent.getDate().toLocalDate()
								.getMonth().toString();
						String year = Integer.toString(financialEvent.getDate()
								.toLocalDate().getYear());
						result.f0 = financialEvent.getMcgDescription();
						result.f1 = month + " " + year;
						result.f2 = financialEvent.getAmount();
						result.f3 = financialEvent.getDate();
						System.out.println(financialEvent.getMcgDescription()
								+ " " + month + " " + year + " "
								+ financialEvent.getAmount());
						return result;
					}
				})
				.map(new MapFunction<Tuple4<String, String, Double, Date>, Tuple4<String, String, Double, Date>>() {

					@Override
					public Tuple4<String, String, Double, Date> map(
							Tuple4<String, String, Double, Date> arg0)
							throws Exception {
						connection = AnalyticsResultsAccessImpl.getConnection();

						String query = "insert into TopCategoriesSumResults (category_name, month_year, sum, date)"
								+ " values (?, ?, ?, ?)";

						System.out.println(query);
						// create the mysql insert preparedstatement
						PreparedStatement preparedStmt = connection
								.prepareStatement(query);
						preparedStmt.setString(1, arg0.f0);
						preparedStmt.setString(2, arg0.f1);
						preparedStmt.setDouble(3, arg0.f2);
						preparedStmt.setDate(4, arg0.f3);
						// execute the preparedstatement
						preparedStmt.execute();

						// connection.close();
						return arg0;
					}

				}).print();

		return postEvents;
	}
}
