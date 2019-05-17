package com.alphabank.typhon.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.alphabank.typhon.commons.AlphaConstants;
import com.alphabank.typhon.dao.ITransactionDAO;
import com.alphabank.typhon.entity.NonFinancialEventEntity;
import com.alphabank.typhon.entity.TransactionEntity;

public class TransactionDAOImpl implements ITransactionDAO {

	Connection connection;

	public TransactionDAOImpl() {
	}

	public TransactionDAOImpl(Connection connection) {
		this.connection = connection;
	}

	@Override
	public TransactionEntity selectTransactionById(String transactionId) {
		StringBuilder sql = null;
//		StringBuilder sql = new StringBuilder("select * from "
//				+ AlphaConstants.Table.Transactions.getName() + "  where "
//				+ AlphaConstants.Table.Transactions.FNC_EV_ID + " = '"
//				+ transactionId + "'");

		PreparedStatement ptmt = null;
		ResultSet resultSet = null;

		TransactionEntity entity = null;
		try {
			ptmt = this.connection.prepareStatement(sql.toString());

			resultSet = ptmt.executeQuery();
			if (resultSet != null) {
				entity = new TransactionEntity();
				// while (resultSet.next()) {

//				entity.setId(resultSet
//						.getString(AlphaConstants.Table.Transactions.FNC_EV_ID));
//
//				entity.setAccountId(resultSet
//						.getString(AlphaConstants.Table.Transactions.FNC_EV_AC_ID));
//
//				entity.setAmount(resultSet
//						.getString(AlphaConstants.Table.Transactions.FNC_EV_AMT));

				System.out.println(entity);

				// }
			}
			resultSet.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return entity;

	}
}
