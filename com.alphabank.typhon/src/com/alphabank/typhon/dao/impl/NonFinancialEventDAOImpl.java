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
						.setAccountCode(resultSet
								.getString(AlphaConstants.Table.NonFinancialEvent.AC_CODE));
				nonFinancialEventEntity.setAccountId(resultSet
						.getLong(AlphaConstants.Table.NonFinancialEvent.AC_ID));
				nonFinancialEventEntity
						.setActionCode(resultSet
								.getString(AlphaConstants.Table.NonFinancialEvent.ACTN_CODE));
				nonFinancialEventEntity
						.setActionDescription(resultSet
								.getString(AlphaConstants.Table.NonFinancialEvent.ACTN_DSC));
				nonFinancialEventEntity
						.setCdiCode(resultSet
								.getString(AlphaConstants.Table.NonFinancialEvent.CDI_CODE));
				nonFinancialEventEntity
						.setDateTime(resultSet
								.getTimestamp(AlphaConstants.Table.NonFinancialEvent.DT_TM));
				nonFinancialEventEntity
						.setEffectiveDate(resultSet
								.getDate(AlphaConstants.Table.NonFinancialEvent.EFF_DT));
				nonFinancialEventEntity
						.setEndDate(resultSet
								.getDate(AlphaConstants.Table.NonFinancialEvent.END_DT));
				nonFinancialEventEntity.setId(resultSet
						.getLong(AlphaConstants.Table.NonFinancialEvent.ID));
				nonFinancialEventEntity
						.setTpCode(resultSet
								.getString(AlphaConstants.Table.NonFinancialEvent.TP_CODE));
				nonFinancialEventEntity
						.setTunCode(resultSet
								.getString(AlphaConstants.Table.NonFinancialEvent.TUN_CODE));

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
