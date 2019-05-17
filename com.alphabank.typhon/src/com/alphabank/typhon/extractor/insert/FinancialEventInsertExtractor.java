package com.alphabank.typhon.extractor.insert;

import java.util.HashMap;

import com.alphabank.typhon.commons.AlphaConstants;

import net.sf.jsqlparser.JSQLParserException;

public class FinancialEventInsertExtractor extends InsertExtractor {

	private HashMap<String, String> fieldValueMap;

	public FinancialEventInsertExtractor(String query)
			throws JSQLParserException {
		super(query);
		this.fieldValueMap = populateFieldValueMap();
	}

	public String getId() {

		return fieldValueMap.get(AlphaConstants.Table.FinancialEvent.ID);
	};

	public String getAccountId() {

		return fieldValueMap.get(AlphaConstants.Table.FinancialEvent.AC_ID);
	};

	public String getDateTime() {

		return fieldValueMap.get(AlphaConstants.Table.FinancialEvent.DT);
	};

	public String getSignCodeDesc() {
		return fieldValueMap
				.get(AlphaConstants.Table.FinancialEvent.SIGN_CODE_DSC);
	};

	public String getSignCode() {
		return fieldValueMap.get(AlphaConstants.Table.FinancialEvent.SIGN_CODE);
	};

	public String getAmount() {
		return fieldValueMap.get(AlphaConstants.Table.FinancialEvent.AMT);
	};

	public String getTUNCode() {

		return fieldValueMap.get(AlphaConstants.Table.FinancialEvent.TUN_CODE);
	};

	public String getEeffectiveDateTime() {

		return fieldValueMap.get(AlphaConstants.Table.FinancialEvent.EFF_DT);
	};

	public String getEndDateTime() {

		return fieldValueMap.get(AlphaConstants.Table.FinancialEvent.END_DT);
	};

	public String getMarchentId() {
		return fieldValueMap.get(AlphaConstants.Table.FinancialEvent.MRCH_ID);
	};

	public String getMarchentName() {
		return fieldValueMap.get(AlphaConstants.Table.FinancialEvent.MRCH_NAME);
	};

	public String getMcgId() {
		return fieldValueMap.get(AlphaConstants.Table.FinancialEvent.MCG_ID);
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

	public String getISRTTimeStamp() {
		return fieldValueMap.get(AlphaConstants.Table.FinancialEvent.ISRT_TMS);
	};

	public String getSourceSTMCode() {
		return fieldValueMap
				.get(AlphaConstants.Table.FinancialEvent.SRC_STM_CODE);
	};

}
