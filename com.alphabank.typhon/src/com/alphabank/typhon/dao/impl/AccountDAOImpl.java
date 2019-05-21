package com.alphabank.typhon.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.alphabank.typhon.commons.AlphaConstants;
import com.alphabank.typhon.dao.IAccountDAO;
import com.alphabank.typhon.entity.AccountEntity;

public class AccountDAOImpl implements IAccountDAO {
	Connection connection;

	public AccountDAOImpl(Connection connection) {
		this.connection = connection;
	}

	@Override
	public AccountEntity selectAccountByAccountCode(String accountCode) {

		StringBuilder sql = new StringBuilder("select * from "
				+ AlphaConstants.Table.Account.getName() + "  where "
				+ AlphaConstants.Table.Account.AC_CODE + " = '" + accountCode
				+ "'");

		PreparedStatement ptmt = null;
		ResultSet resultSet = null;

		AccountEntity entity = null;
		try {
			ptmt = this.connection.prepareStatement(sql.toString());

			resultSet = ptmt.executeQuery();
			
			if (resultSet != null && resultSet.isBeforeFirst()) {
				resultSet.next();
				entity = new AccountEntity();
				// while (resultSet.next()) {
				System.out.println("Account Found");
				entity.setCode(resultSet
						.getString(AlphaConstants.Table.Account.AC_CODE));
				entity.setEffectiveDate(resultSet
						.getString(AlphaConstants.Table.Account.EFF_DT));
				entity.setEndDate(resultSet
						.getString(AlphaConstants.Table.Account.END_DT));
				entity.setFirstBeneficiaryCDICode(resultSet
						.getString(AlphaConstants.Table.Account.AC_FRST_BENF_CDI_CODE));
				entity.setFirstBeneficiaryId(resultSet
						.getString(AlphaConstants.Table.Account.AC_FRST_BENF_ID));
				entity.setId(resultSet
						.getString(AlphaConstants.Table.Account.AC_ID));
				entity.setInsertionTimeSTamp(resultSet
						.getString(AlphaConstants.Table.Account.ISRT_TMS));
				entity.setSourceSTMCode(resultSet
						.getString(AlphaConstants.Table.Account.AC_SRC_STM_CODE));

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
