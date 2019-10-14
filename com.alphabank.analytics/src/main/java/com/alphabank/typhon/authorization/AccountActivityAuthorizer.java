package com.alphabank.typhon.authorization;

import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.streaming.api.datastream.DataStream;

import ac.york.typhon.analytics.commons.datatypes.events.Event;
import ac.york.typhon.analytics.commons.datatypes.events.PreEvent;
import ac.york.typhon.analytics.commons.enums.AnalyticTopicType;
import ac.york.typhon.analytics.commons.enums.StatementType;
import ac.york.typhon.analytics.streaming.StreamManager;

import com.alphabank.typhon.commons.AlphaEnum;
import com.alphabank.typhon.extractor.insert.NonFinancialEventInsertExtractor;

/**
 * Checks for dormant account activity
 * 
 * @author Admin
 *
 */
public class AccountActivityAuthorizer {

	public static void main(String[] args) throws Exception {

		// ExtractorManager.registerParsedStatements();

		DataStream<Event> dataStream = StreamManager.initializeSource(
				AnalyticTopicType.PRE, PreEvent.class);

		// dataStream.print(); //print the data stream as received

		dataStream = dataStream.map(new MapFunction<Event, Event>() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public Event map(Event event) throws Exception {

				String query = event.getQuery();
				StatementType statmemtType = event.retrieveStatementType();
				switch (statmemtType) {

				case SELECT:

//					SelectExtractorNonFinancialEvent selectExtractorNonFinancialEvent = new SelectExtractorNonFinancialEvent(
//							query);

					// select the account related to this transaction from the
					// transaction table
					break;
				case INSERT:
				
					NonFinancialEventInsertExtractor insertExtractorNonFinancialEvent = new NonFinancialEventInsertExtractor(
							query);
					
					insertExtractorNonFinancialEvent.getAccountCode();
					break;
				case UPDATE:
//					UpdateExtractorNonFinancialEvent updateExtractorNonFinancialEvent = new UpdateExtractorNonFinancialEvent(
//							query);

					break;
				case DELETE:
//					DeleteExtractorNonFinancialEvent deleteExtractorNonFinancialEvent = new DeleteExtractorNonFinancialEvent(
//							query);

					break;

				}

			

			

				//
				// HashMap<Class<? extends Statement>, Object> map = new
				// HashMap<Class<? extends Statement>, Object>();
				//
				//
				//
				// String query = event.getQuery();
				// Statement statement = CCJSqlParserUtil.parse(query);
				// map.put(statement.getClass(), value)
				// if(statement instanceof Delete){
				// IParsedStatement<Delete> parsed =
				// ParsedStatementFactory.getHandler();
				//
				// Delete delete = parsed.getStatement();
				// }
				//
				// IParsedStatement<Delete> parsed =
				// ParsedStatementFactory.getHandler();
				//
				// ParsedStatement parsedStatement = ParsedStatementFactory
				// .getInstance<SelectParsedStatement>(query);

				// System.out.println(
				// ((InsertQueryStatement)queryStatement).retieveParsedQuery());
				// String accountNumber =
				// event.retrieveParsedQueryObject(event.getQuery()) ;
				// //getQuery() getDmlCommand().getClause();
				// System.out.println( statment.getClass().getSimpleName() +
				// " STATMENT from map : "+ statment);
				// IAccountActivityAccess accountActivityAccess = new
				// AccountActivityAccessImpl();
				// String accountNumber =
				// AccountActivityAccessImpl.retrieveAccountNumberFromStatement(parsedStatement);
				// System.out.println(accountNumber);
				// ((PreEvent) event).setAuthenticated(accountActivityAccess
				// .isDormantAccount(accountNumber));
				System.out.println(event);
				return event;
			}
		}).returns(Event.class);
		StreamManager.initializeSink(AlphaEnum.AUTHORIZATION, dataStream);

		// ExampleEventAuthTask1 ex1 = new ExampleEventAuthTask1();
		//
		// SplitStream<Event> split = dataStream
		// .split(new OutputSelector<Event>() {
		//
		// @Override
		// public Iterable<String> select(Event event) {
		// List<String> output = new ArrayList<String>();
		// if (ex1.checkCondition(event)) {
		// output.add("ex1");
		// } else {
		// output.add("other");
		// }
		// return output;
		// }
		// });
		//
		// DataStream<Event> ex1Statements = split.select("ex1");
		// DataStream<Event> ex1StatementsAnalysis = ex1.analyse(ex1Statements);
		// StreamManager.initializeSink(ExternalTopicType.AUTHORIZATION,
		// ex1StatementsAnalysis);

		StreamManager.startExecutionEnvironment(AnalyticTopicType.PRE);
	}
}
