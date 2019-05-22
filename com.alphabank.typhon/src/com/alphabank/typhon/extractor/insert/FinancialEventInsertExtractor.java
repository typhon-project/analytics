package com.alphabank.typhon.extractor.insert;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.ParseException;
import java.util.HashMap;

import com.alphabank.typhon.commons.AlphaConstants;
import com.alphabank.typhon.commons.Utils;

import net.sf.jsqlparser.JSQLParserException;

public class FinancialEventInsertExtractor extends InsertExtractor {

	private HashMap<String, String> fieldValueMap;

	public FinancialEventInsertExtractor(String query)
			throws JSQLParserException {
		super(query);
		this.fieldValueMap = populateFieldValueMap();
	}

	public long getId() {

		return Long.parseLong(fieldValueMap
				.get(AlphaConstants.Table.FinancialEvent.ID));
	};

	public long getAccountId() {

		return Long.parseLong(fieldValueMap
				.get(AlphaConstants.Table.FinancialEvent.AC_ID));
	};

	public Date getDateTime() {

		return Date.valueOf(fieldValueMap
				.get(AlphaConstants.Table.FinancialEvent.DT));
	};

	public String getSignCodeDesc() {
		return fieldValueMap
				.get(AlphaConstants.Table.FinancialEvent.SIGN_CODE_DSC);
	};

	public String getSignCode() {
		return fieldValueMap.get(AlphaConstants.Table.FinancialEvent.SIGN_CODE);
	};

	public double getAmount() {
		return Double.parseDouble(fieldValueMap
				.get(AlphaConstants.Table.FinancialEvent.AMT));
	};

	public String getTUNCode() {

		return fieldValueMap.get(AlphaConstants.Table.FinancialEvent.TUN_CODE);
	};

	public Date getEffectiveDateTime() {

		return Date.valueOf(fieldValueMap
				.get(AlphaConstants.Table.FinancialEvent.EFF_DT));
	};

	public Date getEndDateTime() {

		return Date.valueOf(fieldValueMap
				.get(AlphaConstants.Table.FinancialEvent.END_DT));
	};

	public String getMarchentId() {
		return fieldValueMap.get(AlphaConstants.Table.FinancialEvent.MRCH_ID);
	};

	public String getMarchentName() {
		return fieldValueMap.get(AlphaConstants.Table.FinancialEvent.MRCH_NAME);
	};

	public long getMcgId() {
		return Long.parseLong(fieldValueMap
				.get(AlphaConstants.Table.FinancialEvent.MCG_ID));
	};

	public String getMcg() {
		return fieldValueMap.get(AlphaConstants.Table.FinancialEvent.MCG);
	};

	public String getMcgDesc() {
		return fieldValueMap.get(AlphaConstants.Table.FinancialEvent.MCG_DSC);
	};

	public String getTPCode() {
		return fieldValueMap.get(AlphaConstants.Table.FinancialEvent.TP_CODE);
	};

	public String getTPDesc() {
		return fieldValueMap.get(AlphaConstants.Table.FinancialEvent.TP_DSC);
	};

	public Timestamp getISRTTimeStamp() throws ParseException {
		System.out.println("FN Timestamp: " +  fieldValueMap
				.get(AlphaConstants.Table.FinancialEvent.ISRT_TMS));
		return Utils.parseTimestamp(fieldValueMap
				.get(AlphaConstants.Table.FinancialEvent.ISRT_TMS));
	};

	public String getSourceSTMCode() {
		return fieldValueMap
				.get(AlphaConstants.Table.FinancialEvent.SRC_STM_CODE);
	};

}
