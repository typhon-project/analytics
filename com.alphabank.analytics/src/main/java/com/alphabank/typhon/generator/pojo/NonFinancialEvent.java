package com.alphabank.typhon.generator.pojo;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import ac.york.typhon.generator.commons.AlphaConstants;

public class NonFinancialEvent implements IPOJO {

	long NON_FNC_EV_ID;
	String NON_FNC_EV_TUN_CODE;
	String NON_FNC_EV_TP_CODE;
	long NON_FNC_EV_AC_ID;
	String NON_FNC_EV_AC_CODE;
	String NON_FNC_EV_ACTN_CODE;
	String NON_FNC_EV_ACTN_DSC;
	Timestamp NON_FNC_EV_DT_TM;
	String NON_FNC_EV_CDI_CODE;
	Date EFF_DT;
	Date END_DT;

	public NonFinancialEvent() {
	}

	public NonFinancialEvent(ResultSet resultSet) {

		populate(resultSet);

	}

	@Override
	public void populate(ResultSet resultSet) {
		try {
			NON_FNC_EV_ID = resultSet
					.getLong(AlphaConstants.Table.NonFinancialEvent.ID);
			NON_FNC_EV_TUN_CODE = resultSet
					.getString(AlphaConstants.Table.NonFinancialEvent.TUN_CODE);
			NON_FNC_EV_TP_CODE = resultSet
					.getString(AlphaConstants.Table.NonFinancialEvent.TP_CODE);
			NON_FNC_EV_AC_ID = resultSet
					.getLong(AlphaConstants.Table.NonFinancialEvent.AC_ID);
			NON_FNC_EV_AC_CODE = resultSet
					.getString(AlphaConstants.Table.NonFinancialEvent.AC_CODE);
			NON_FNC_EV_ACTN_CODE = resultSet
					.getString(AlphaConstants.Table.NonFinancialEvent.ACTN_CODE);
			NON_FNC_EV_ACTN_DSC = resultSet
					.getString(AlphaConstants.Table.NonFinancialEvent.ACTN_DSC);
			NON_FNC_EV_DT_TM = resultSet
					.getTimestamp(AlphaConstants.Table.NonFinancialEvent.DT_TM);
			NON_FNC_EV_CDI_CODE = resultSet
					.getString(AlphaConstants.Table.NonFinancialEvent.CDI_CODE);
			EFF_DT = resultSet
					.getDate(AlphaConstants.Table.NonFinancialEvent.EFF_DT);
			END_DT = resultSet
					.getDate(AlphaConstants.Table.NonFinancialEvent.END_DT);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	// public NonFinancialEvent(long nON_FNC_EV_ID, String nON_FNC_EV_TUN_CODE,
	// String nON_FNC_EV_TP_CODE, long nON_FNC_EV_AC_ID,
	// String nON_FNC_EV_AC_CODE, String nON_FNC_EV_ACTN_CODE,
	// String nON_FNC_EV_ACTN_DSC, Timestamp nON_FNC_EV_DT_TM,
	// String nON_FNC_EV_CDI_CODE, Date eFF_DT, Date eND_DT) {
	// super();
	// NON_FNC_EV_ID = nON_FNC_EV_ID;
	// NON_FNC_EV_TUN_CODE = nON_FNC_EV_TUN_CODE;
	// NON_FNC_EV_TP_CODE = nON_FNC_EV_TP_CODE;
	// NON_FNC_EV_AC_ID = nON_FNC_EV_AC_ID;
	// NON_FNC_EV_AC_CODE = nON_FNC_EV_AC_CODE;
	// NON_FNC_EV_ACTN_CODE = nON_FNC_EV_ACTN_CODE;
	// NON_FNC_EV_ACTN_DSC = nON_FNC_EV_ACTN_DSC;
	// NON_FNC_EV_DT_TM = nON_FNC_EV_DT_TM;
	// NON_FNC_EV_CDI_CODE = nON_FNC_EV_CDI_CODE;
	// EFF_DT = eFF_DT;
	// END_DT = eND_DT;
	// }

	public long getNON_FNC_EV_ID() {
		return NON_FNC_EV_ID;
	}

	public void setNON_FNC_EV_ID(long nON_FNC_EV_ID) {
		NON_FNC_EV_ID = nON_FNC_EV_ID;
	}

	public String getNON_FNC_EV_TUN_CODE() {
		return NON_FNC_EV_TUN_CODE;
	}

	public void setNON_FNC_EV_TUN_CODE(String nON_FNC_EV_TUN_CODE) {
		NON_FNC_EV_TUN_CODE = nON_FNC_EV_TUN_CODE;
	}

	public String getNON_FNC_EV_TP_CODE() {
		return NON_FNC_EV_TP_CODE;
	}

	public void setNON_FNC_EV_TP_CODE(String nON_FNC_EV_TP_CODE) {
		NON_FNC_EV_TP_CODE = nON_FNC_EV_TP_CODE;
	}

	public long getNON_FNC_EV_AC_ID() {
		return NON_FNC_EV_AC_ID;
	}

	public void setNON_FNC_EV_AC_ID(long nON_FNC_EV_AC_ID) {
		NON_FNC_EV_AC_ID = nON_FNC_EV_AC_ID;
	}

	public String getNON_FNC_EV_AC_CODE() {
		return NON_FNC_EV_AC_CODE;
	}

	public void setNON_FNC_EV_AC_CODE(String nON_FNC_EV_AC_CODE) {
		NON_FNC_EV_AC_CODE = nON_FNC_EV_AC_CODE;
	}

	public String getNON_FNC_EV_ACTN_CODE() {
		return NON_FNC_EV_ACTN_CODE;
	}

	public void setNON_FNC_EV_ACTN_CODE(String nON_FNC_EV_ACTN_CODE) {
		NON_FNC_EV_ACTN_CODE = nON_FNC_EV_ACTN_CODE;
	}

	public String getNON_FNC_EV_ACTN_DSC() {
		return NON_FNC_EV_ACTN_DSC;
	}

	public void setNON_FNC_EV_ACTN_DSC(String nON_FNC_EV_ACTN_DSC) {
		NON_FNC_EV_ACTN_DSC = nON_FNC_EV_ACTN_DSC;
	}

	public Timestamp getNON_FNC_EV_DT_TM() {
		return NON_FNC_EV_DT_TM;
	}

	public void setNON_FNC_EV_DT_TM(Timestamp nON_FNC_EV_DT_TM) {
		NON_FNC_EV_DT_TM = nON_FNC_EV_DT_TM;
	}

	public String getNON_FNC_EV_CDI_CODE() {
		return NON_FNC_EV_CDI_CODE;
	}

	public void setNON_FNC_EV_CDI_CODE(String nON_FNC_EV_CDI_CODE) {
		NON_FNC_EV_CDI_CODE = nON_FNC_EV_CDI_CODE;
	}

	public Date getEFF_DT() {
		return EFF_DT;
	}

	public void setEFF_DT(Date eFF_DT) {
		EFF_DT = eFF_DT;
	}

	public Date getEND_DT() {
		return END_DT;
	}

	public void setEND_DT(Date eND_DT) {
		END_DT = eND_DT;
	}

	public String rertieveColumnNameString() {

		String columnString = AlphaConstants.Table.NonFinancialEvent.AC_CODE
				+ "," + AlphaConstants.Table.NonFinancialEvent.AC_ID + ","
				+ AlphaConstants.Table.NonFinancialEvent.ACTN_CODE + ","
				+ AlphaConstants.Table.NonFinancialEvent.ACTN_DSC + ","
				+ AlphaConstants.Table.NonFinancialEvent.CDI_CODE + ","
				+ AlphaConstants.Table.NonFinancialEvent.DT_TM + ","
				+ AlphaConstants.Table.NonFinancialEvent.EFF_DT + ","
				+ AlphaConstants.Table.NonFinancialEvent.END_DT + ","
				+ AlphaConstants.Table.NonFinancialEvent.ID + ","
				+ AlphaConstants.Table.NonFinancialEvent.TP_CODE + ","
				+ AlphaConstants.Table.NonFinancialEvent.TUN_CODE;

		// String columnString =
		// "NON_FNC_EV_AC_CODE, NON_FNC_EV_AC_ID , NON_FNC_EV_ACTN_CODE,NON_FNC_EV_ACTN_DSC,"
		// +
		// "NON_FNC_EV_CDI_CODE,NON_FNC_EV_DT_TM,EFF_DT,END_DT,NON_FNC_EV_ID,NON_FNC_EV_TP_CODE,NON_FNC_EV_TUN_CODE";

		return columnString;
	}

	public String retriveValuesString() {

		String valuesString = "'" + this.NON_FNC_EV_AC_CODE + "','"
				+ this.NON_FNC_EV_AC_ID + "','" + this.NON_FNC_EV_ACTN_CODE
				+ "','" + this.NON_FNC_EV_ACTN_DSC + "','"
				+ this.NON_FNC_EV_CDI_CODE + "','" + this.NON_FNC_EV_DT_TM
				+ "','" + this.EFF_DT + "','" + this.END_DT + "','"
				+ this.NON_FNC_EV_ID + "','" + this.NON_FNC_EV_TP_CODE + "','"
				+ this.NON_FNC_EV_TUN_CODE + "'";

		return valuesString;
	}

	public String retriveValuesStringWithoutSingleQuotesForNumbers() {

		String valuesString = "'" + this.NON_FNC_EV_AC_CODE + "',"
				+ this.NON_FNC_EV_AC_ID + ",'" + this.NON_FNC_EV_ACTN_CODE
				+ "','" + this.NON_FNC_EV_ACTN_DSC + "','"
				+ this.NON_FNC_EV_CDI_CODE + "','" + this.NON_FNC_EV_DT_TM
				+ "','" + this.EFF_DT + "','" + this.END_DT + "',"
				+ this.NON_FNC_EV_ID + ",'" + this.NON_FNC_EV_TP_CODE + "','"
				+ this.NON_FNC_EV_TUN_CODE + "'";

		return valuesString;
	}

	public String toInsertSQLString() {
		String sql = "INSERT INTO TABLE NON_FNC_EV VALUES('"
				+ this.NON_FNC_EV_ID + "','" + this.NON_FNC_EV_TUN_CODE + "','"
				+ this.NON_FNC_EV_TP_CODE + "','" + this.NON_FNC_EV_AC_ID
				+ "','" + this.NON_FNC_EV_AC_CODE + "','"
				+ this.NON_FNC_EV_ACTN_CODE + "','" + this.NON_FNC_EV_ACTN_DSC
				+ "','" + this.NON_FNC_EV_DT_TM + "','"
				+ this.NON_FNC_EV_CDI_CODE + "','" + this.EFF_DT + "','"
				+ this.END_DT + "')";
		return sql;
	}

	@Override
	public String toInsert() {
		String sql = "INSERT into NON_FNC_EV ("
				+ this.rertieveColumnNameString() + ")" + " values ("
				+ this.retriveValuesString() + ")";

		return sql;
	}
}
