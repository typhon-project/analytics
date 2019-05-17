package com.alphabank.typhon.extractor.insert;

import java.util.HashMap;

import net.sf.jsqlparser.JSQLParserException;

import com.alphabank.typhon.commons.AlphaConstants;

public class NonFinancialEventInsertExtractor extends InsertExtractor {

	private HashMap<String, String> fieldValueMap;

	public NonFinancialEventInsertExtractor(String query)
			throws JSQLParserException {
		super(query);

		this.fieldValueMap = populateFieldValueMap();
	}

	public String getEventId() {
		return fieldValueMap.get(AlphaConstants.Table.NonFinancialEvent.ID);

	}

	public String getTunCode() {
		return fieldValueMap
				.get(AlphaConstants.Table.NonFinancialEvent.TUN_CODE);

	}

	public String getTPCode() {
		return fieldValueMap
				.get(AlphaConstants.Table.NonFinancialEvent.TP_CODE);
	}

	public String getAcountId() {
		return fieldValueMap.get(AlphaConstants.Table.NonFinancialEvent.AC_ID);
	}

	public String getAccountCode() {

		return fieldValueMap
				.get(AlphaConstants.Table.NonFinancialEvent.AC_CODE);

	}

	public String getActionCode() {
		return fieldValueMap
				.get(AlphaConstants.Table.NonFinancialEvent.ACTN_CODE);
	}

	public String getActionDesc() {
		return fieldValueMap
				.get(AlphaConstants.Table.NonFinancialEvent.ACTN_DSC);
	}

	public String getDateTime() {
		return fieldValueMap.get(AlphaConstants.Table.NonFinancialEvent.DT_TM);
	}

	public String getCDICode() {
		return fieldValueMap
				.get(AlphaConstants.Table.NonFinancialEvent.CDI_CODE);
	}

	public String getEffectiveDateTime() {
		return fieldValueMap.get(AlphaConstants.Table.NonFinancialEvent.EFF_DT);
	}

	public String getEndDateTime() {
		return fieldValueMap.get(AlphaConstants.Table.NonFinancialEvent.END_DT);
	}

	@Override
	public String toString() {
		return "NonFinancialEventInsertExtractor [fieldValueMap="
				+ fieldValueMap + ", parsedStatement=" + parsedStatement + "]";
	}
}
