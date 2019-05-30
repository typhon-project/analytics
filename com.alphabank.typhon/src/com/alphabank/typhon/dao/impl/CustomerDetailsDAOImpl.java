package com.alphabank.typhon.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.alphabank.typhon.commons.AlphaConstants;
import com.alphabank.typhon.dao.ICustomerDAO;
import com.alphabank.typhon.entity.CustomerDetailsEntity;

public class CustomerDetailsDAOImpl implements ICustomerDAO {
	Connection connection;

	public CustomerDetailsDAOImpl(Connection connection) {
		this.connection = connection;
	}

	@Override
	public CustomerDetailsEntity selectCustomerDetailsByCDICode(String cdiCode) {

		StringBuilder sql = new StringBuilder("select * from "
				+ AlphaConstants.Table.CustomerDetails.getName() + "  where "
				+ AlphaConstants.Table.CustomerDetails.DTL_OBLG_CDI_CODE + " = '" + cdiCode + "'");

		PreparedStatement ptmt = null;
		ResultSet resultSet = null;

		CustomerDetailsEntity entity = null;
		try {
			ptmt = this.connection.prepareStatement(sql.toString());

			resultSet = ptmt.executeQuery();
			if (resultSet != null && resultSet.isBeforeFirst() ) {
				resultSet.next();
				
				entity = new CustomerDetailsEntity();
				// while (resultSet.next()) {

				entity.setBirthDateTime(resultSet
						.getString(AlphaConstants.Table.CustomerDetails.BRTH_DT));
				entity.setDtlOblgCDICode(resultSet
						.getString(AlphaConstants.Table.CustomerDetails.DTL_OBLG_CDI_CODE));
				entity.setEffectiveDateTime(resultSet
						.getString(AlphaConstants.Table.CustomerDetails.EFF_DT));
				entity.setEndDateTime(resultSet
						.getString(AlphaConstants.Table.CustomerDetails.END_DT));
				entity.setId(resultSet
						.getString(AlphaConstants.Table.CustomerDetails.ID));
				entity.setInsertionTimeStamp(resultSet
						.getString(AlphaConstants.Table.CustomerDetails.ISRT_TMS));
				entity.setPrimaryEmailAddress(resultSet
						.getString(AlphaConstants.Table.CustomerDetails.PRIM_EMAIL_ADR));

//				System.out.println(entity);

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
