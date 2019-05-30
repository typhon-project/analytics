package com.alphabank.typhon.dataaccess.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import ac.uk.york.typhon.analytics.commons.AppConfiguration;
import ac.uk.york.typhon.analytics.commons.datatypes.commands.DMLCommand;
import ac.uk.york.typhon.analytics.commons.datatypes.commands.InsertCommand;

import com.alphabank.typhon.commons.AlphaConstants;
import com.alphabank.typhon.commons.Utils;
import com.alphabank.typhon.dao.IAccountDAO;
import com.alphabank.typhon.dao.ICustomerDAO;
import com.alphabank.typhon.dao.impl.AccountDAOImpl;
import com.alphabank.typhon.dao.impl.CustomerDetailsDAOImpl;
import com.alphabank.typhon.entity.AccountEntity;
import com.alphabank.typhon.entity.CustomerDetailsEntity;
import com.alphabank.typhon.extractor.insert.NonFinancialEventInsertExtractor;

public class AccountActivityAccessImpl {

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

	public AccountActivityAccessImpl() {

	}

	public static boolean isDormantAccount(
			NonFinancialEventInsertExtractor nonFinancialEventInsertExtractor) {

		boolean isDormant = false;
		if (nonFinancialEventInsertExtractor.getActionCode().equalsIgnoreCase(
				AlphaConstants.DormantFlags.EVENT_TYPE_CODE)
				&& nonFinancialEventInsertExtractor.getActionDesc()
						.equalsIgnoreCase(
								AlphaConstants.DormantFlags.ACTION_CODE)
		) {
			String accountCode = nonFinancialEventInsertExtractor
					.getAccountCode();

			IAccountDAO accountDAO = new AccountDAOImpl(connection);
			AccountEntity accountEntity = accountDAO
					.selectAccountByAccountCode(accountCode);
			if (accountEntity != null) {
				ICustomerDAO customerDAO = new CustomerDetailsDAOImpl(
						connection);
				CustomerDetailsEntity customerDetailsEntity = customerDAO
						.selectCustomerDetailsByCDICode(accountEntity
								.getFirstBeneficiaryId());
				if (customerDetailsEntity != null) {

					long customerAge = Utils
							.yearDifference(customerDetailsEntity
									.getBirthDateTime());
					if (customerAge > AlphaConstants.DormantFlags.CUSTOMER_AGE_LIMIT) {
						System.out.println("Customer Age :" + customerAge);
						isDormant = true;
					}
				}
			}

		}

		return isDormant;
	}

	public static String retrieveAccountNumberFromDML(DMLCommand dmlCommand) {

		// String dmlCommandName =
		// dmlCommand.getClass().getName().toUpperCase();
		// System.out.println(dmlCommand.getClass().getSimpleName().replace("Command",
		// "").toUpperCase());
		String dmlCommandName = dmlCommand.getClass().getSimpleName()
				.replace("Command", "").toUpperCase();

		String clause = "";
		String accountNumber = "";
		switch (dmlCommandName) {
		case "SELECT":
			clause = dmlCommand.getClause();
			System.out.println("SELECT clause : " + dmlCommand.getClause());
			break;
		case "INSERT":
			clause = dmlCommand.getClause();
			System.out.println("INSERT clause : " + dmlCommand.getClause());
			System.out.println("INSERT DML : " + dmlCommand);
			accountNumber = ((InsertCommand) dmlCommand).getColumnValueMap()
					.get("FNC_EV_AC_ID");
			System.out.println("accountNumber :" + accountNumber);

			break;
		case "UPDATE":
			clause = dmlCommand.getClause();
			System.out.println("UPDATE clause : " + dmlCommand.getClause());
			break;
		case "DELETE":
			clause = dmlCommand.getClause();
			System.out.println("DELETE clause : " + dmlCommand.getClause());
			// String [] clause.split("=");
			break;

		}

		return clause;
	}

	// public static boolean isDormantAccount(String accountNumber) {
	// INonFinancialEventDAO nonFinancialEventDao = new
	// NonFinancialEventDAOImpl(
	// connection);
	//
	// NonFinancialEventEntity nonFinancialEvent = nonFinancialEventDao
	// .selectNonFinancialEventByAccountCode(accountNumber);
	//
	// if (nonFinancialEvent.getEventTypeCode() ==
	// AlphaConstants.DormantFlags.EVENT_TYPE_CODE
	// && nonFinancialEvent.getActionCode() ==
	// AlphaConstants.DormantFlags.ACTION_CODE) {
	//
	// IAccountDAO accountDAO = new AccountDAOImpl(connection);
	// AccountEntity accountEntity = accountDAO
	// .selectAccountByAccountCode(accountNumber);
	//
	// if (accountEntity.getCode() == nonFinancialEvent.getAccountCode()) {
	//
	// }
	//
	// }
	// return false;
	// }

	// public List<MessageDTO> storeNewsEvent(EventEntity eventEntity) {
	//
	// List<MessageDTO> messageList = new ArrayList<MessageDTO>();
	// INonFinancialEventDAO countryCodeDAO = new CountryCodeDAOImpl(
	// connection, messageList);
	//
	// String countryId = countryCodeDAO.selectCountryIdByCode(eventEntity
	// .getActorOneGeoCountryCode());
	// if (countryId == null) {
	// countryCodeDAO.insertCountryCode(eventEntity
	// .getActorOneGeoCountryCode());
	// countryId = countryCodeDAO.selectCountryIdByCode(eventEntity
	// .getActorOneGeoCountryCode());
	//
	// }
	// eventEntity.setActorOneGeoCountryCode(countryId);
	//
	// countryId = countryCodeDAO.selectCountryIdByCode(eventEntity
	// .getActorTwoGeoCountryCode());
	// if (countryId == null) {
	// countryCodeDAO.insertCountryCode(eventEntity
	// .getActorTwoGeoCountryCode());
	// countryId = countryCodeDAO.selectCountryIdByCode(eventEntity
	// .getActorTwoGeoCountryCode());
	//
	// }
	// eventEntity.setActorTwoGeoCountryCode(countryId);
	//
	// IEventDAO eventDAO = new EventDAOImpl(connection, messageList);
	// eventDAO.insertEvent(eventEntity);
	//
	// return messageList;
	//
	// }

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
