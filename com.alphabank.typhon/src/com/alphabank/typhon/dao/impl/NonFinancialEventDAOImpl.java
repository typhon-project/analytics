package com.alphabank.typhon.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.alphabank.typhon.commons.AlphaConstants;
import com.alphabank.typhon.dao.INonFinancialEventDAO;
import com.alphabank.typhon.entity.NonFinancialEventEntity;

public class NonFinancialEventDAOImpl implements INonFinancialEventDAO {

	Connection connection;

	public NonFinancialEventDAOImpl(Connection connection) {
		this.connection = connection;
	}

//	public String selectCountryIdByCode(String countryCode) {
//	
//	 StringBuilder sql = new StringBuilder("select * from "
//	 + AlphaConstants.Table.NonFinancialEvent.getName() + "  where "
//	 + AlphaConstants. .COUNTRY_CODE + " = '"
//	 + countryCode + "'");
//	
//	 this.appendMessage(sql.toString());
//	
//	 PreparedStatement ptmt = null;
//	 ResultSet resultSet = null;
//	 String countryId = null;
//	 try {
//	 ptmt = this.connection.prepareStatement(sql.toString());
//	
//	 resultSet = ptmt.executeQuery();
//	 if (resultSet != null) {
//	 while (resultSet.next()) {
//	 countryId = resultSet
//	 .getString(AlphaConstants.DatabaseCountryCodeColumnName.CODE_ID);
//	 System.out.println(countryId);
//	
//	 }
//	 }
//	 resultSet.close();
//	
//	 } catch (SQLException e) {
//	 // TODO Auto-generated catch block
//	 e.printStackTrace();
//	 }
//	 return countryId;
//	 }

	// public void insertCountryCode(String countryCode) {
	//
	// try {
	//
	// String query = " insert into "
	// + AlphaConstants.Table.NW_GEO_COUNTRY_CODE + " ("
	// + AlphaConstants.DatabaseCountryCodeColumnName.COUNTRY_CODE
	// + ")" + " values ( ? )";
	//
	// System.out.println(query);
	// // create the mysql insert preparedstatement
	// PreparedStatement preparedStmt = connection.prepareStatement(query);
	//
	// preparedStmt.setString(1, countryCode);
	// preparedStmt.execute();
	// preparedStmt.close();
	// // connection.commit();
	//
	// } catch (SQLException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// }
	//
	// }

	@Override
	public NonFinancialEventEntity selectNonFinancialEventByAccountCode(
			String accountCode) {

		StringBuilder sql = new StringBuilder("select * from "
				+ AlphaConstants.Table.NonFinancialEvent.getName() + "  where "
				+ AlphaConstants.Table.NonFinancialEvent.AC_CODE + " = '"
				+ accountCode + "'");

		PreparedStatement ptmt = null;
		ResultSet resultSet = null;

		NonFinancialEventEntity nonFinancialEventEntity = null;
		try {
			ptmt = this.connection.prepareStatement(sql.toString());

			resultSet = ptmt.executeQuery();
			if (resultSet != null) {
				nonFinancialEventEntity = new NonFinancialEventEntity();
				// while (resultSet.next()) {

				nonFinancialEventEntity
						.setId(resultSet
								.getString(AlphaConstants.Table.NonFinancialEvent.ID));

				nonFinancialEventEntity
						.setAccountCode(resultSet
								.getString(AlphaConstants.Table.NonFinancialEvent.AC_CODE));
		

				nonFinancialEventEntity
						.setActionCode(resultSet
								.getString(AlphaConstants.Table.NonFinancialEvent.ACTN_CODE));

				nonFinancialEventEntity
						.setEventTypeCode(resultSet
								.getString(AlphaConstants.Table.NonFinancialEvent.TP_CODE));

				System.out.println(nonFinancialEventEntity);

				// }
			}
			resultSet.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return nonFinancialEventEntity;
	}

}
