package com.alphabank.typhon.dataaccess.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import ac.york.typhon.analytics.commons.AppConfiguration;

import com.alphabank.typhon.commons.AlphaConstants;

public class AnalyticsResultsAccessImpl {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static Connection connection;

	static {
		initializeConnection();
	}

	private static void initializeConnection() {
		String url = AppConfiguration.getString(AlphaConstants.Database.URL);
		String user = AppConfiguration
				.getString(AlphaConstants.Database.USERNAME);
		String password = AppConfiguration
				.getString(AlphaConstants.Database.PASSWORD);

		try {
			connection = DriverManager.getConnection(url, user, password);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public AnalyticsResultsAccessImpl() {

	}
	
	public static Connection getConnection() {
		return connection;
	}

	public void closeConnection() {
		// TODO Auto-generated method stub

		try {

			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	// public static String retrieveAccountNumberFromStatement(
	// ParsedStatement queryStatement) {
	//
	// StatementType statementType = StatementType.valueOf(queryStatement
	// .getStatement().getClass().getSimpleName().toUpperCase());
	// String accountNumber = "";
	// switch (statementType) {
	//
	// case SELECT:
	// SelectExtractor.retrieveTransactionId(queryStatement.getStatement());
	// SelectExtractor selectExtractor = (SelectExtractor) queryStatement;
	// String transactionId = selectExtractor.retrieveTransactionId();
	// accountNumber = transactionId;
	// // select the account related to this transaction from the transaction
	// table
	// break;
	// case INSERT:
	// InsertExtractor insertExtractor = (InsertExtractor) queryStatement;
	// accountNumber = insertExtractor.retrieveAccountNumber();
	// break;
	// case UPDATE:
	// UpdateExtractor updateExtractor = (UpdateExtractor) queryStatement;
	// accountNumber = updateExtractor.retrieveAccountNumber();
	// break;
	// case DELETE:
	// DeleteExtractor deleteExtractor = (DeleteExtractor) queryStatement;
	// String financialEventId = deleteExtractor.extractFinancialEventId();
	// ITransactionDAO transactionDAO = new TransactionDAOImpl(connection);
	// TransactionEntity transactionEntity =
	// transactionDAO.selectTransactionById(financialEventId);
	// System.out.println(transactionEntity);
	//
	// accountNumber = deleteExtractor.retrieveAccountNumber();
	// break;
	//
	// }
	//
	// return accountNumber;
	// }

}
