package com.alphabank.typhon.dto;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.ParseException;

import com.alphabank.typhon.extractor.insert.FinancialEventInsertExtractor;

public class FinancialEvent {

	private long id; 
	private long accountId;
	private Date date;
	private String signCodeDescription;
	private String signCode;
	private double amount;
	private String tunCode;
	private Date effectiveDate;
	private Date endDate;
	private String merchantId;
	private String merchantName;
	private long mcgId;
	private String mcg;
	private String mcgDescription;
	private String tpCode;
	private String tpDescription;
	private Timestamp insertionTimestamp;
	private String sourceSTMCode;

	public FinancialEvent() {

	}

	public FinancialEvent(FinancialEventInsertExtractor extractor) throws ParseException {
		this.accountId = extractor.getAccountId();
		this.amount = extractor.getAmount();
		this.date = extractor.getDateTime();
		this.effectiveDate = extractor.getEffectiveDateTime();
		this.endDate = extractor.getEndDateTime();
		this.id = extractor.getId();
		this.insertionTimestamp = extractor.getISRTTimeStamp();
		this.merchantId = extractor.getMerchantId();
		this.merchantName = extractor.getMerchantName();
		this.mcg = extractor.getMcg();
		this.mcgDescription = extractor.getMcgDesc();
		this.mcgId = extractor.getMcgId();
		this.signCode = extractor.getSignCode();
		this.signCodeDescription = extractor.getSignCodeDesc();
		this.sourceSTMCode = extractor.getSourceSTMCode();
		this.tpCode = extractor.getTPCode();
		this.tpDescription = extractor.getTPDesc();
		this.tunCode = extractor.getTUNCode();

	}

	public long getId() {
		return id;
	}

	public long getAccountId() {
		return accountId;
	}

	public Date getDate() {
		return date;
	}

	public String getSignCodeDescription() {
		return signCodeDescription;
	}

	public String getSignCode() {
		return signCode;
	}

	public double getAmount() {
		return amount;
	}

	public String getTunCode() {
		return tunCode;
	}

	public Date getEffectiveDate() {
		return effectiveDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public String getMerchantId() {
		return merchantId;
	}

	public String getMerchantName() {
		return merchantName;
	}

	public long getMcgId() {
		return mcgId;
	}

	public String getMcg() {
		return mcg;
	}

	public String getMcgDescription() {
		return mcgDescription;
	}

	public String getTpCode() {
		return tpCode;
	}

	public String getTpDescription() {
		return tpDescription;
	}

	public Timestamp getInsertionTimestamp() {
		return insertionTimestamp;
	}

	public String getSourceSTMCode() {
		return sourceSTMCode;
	}

	public void setId(long id) {
		this.id = id;
	}

	public void setAccountId(long accountId) {
		this.accountId = accountId;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public void setSignCodeDescription(String signCodeDescription) {
		this.signCodeDescription = signCodeDescription;
	}

	public void setSignCode(String signCode) {
		this.signCode = signCode;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public void setTunCode(String tunCode) {
		this.tunCode = tunCode;
	}

	public void setEffectiveDate(Date effectiveDate) {
		this.effectiveDate = effectiveDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public void setMerchantId(String merchantId) {
		this.merchantId = merchantId;
	}

	public void setMerchantName(String merchantName) {
		this.merchantName = merchantName;
	}

	public void setMcgId(long mcgId) {
		this.mcgId = mcgId;
	}

	public void setMcg(String mcg) {
		this.mcg = mcg;
	}

	public void setMcgDescription(String mcgDescription) {
		this.mcgDescription = mcgDescription;
	}

	public void setTpCode(String tpCode) {
		this.tpCode = tpCode;
	}

	public void setTpDescription(String tpDescription) {
		this.tpDescription = tpDescription;
	}

	public void setInsertionTimestamp(Timestamp insertionTimestamp) {
		this.insertionTimestamp = insertionTimestamp;
	}

	public void setSourceSTMCode(String sourceSTMCode) {
		this.sourceSTMCode = sourceSTMCode;
	}

	@Override
	public String toString() {
		return "FinancialEvent [id=" + id + ", accountId=" + accountId
				+ ", date=" + date + ", signCodeDescription="
				+ signCodeDescription + ", signCode=" + signCode + ", amount="
				+ amount + ", tunCode=" + tunCode + ", effectiveDate="
				+ effectiveDate + ", endDate=" + endDate + ", marchentId="
				+ merchantId + ", marchentName=" + merchantName + ", mcgId="
				+ mcgId + ", mcg=" + mcg + ", mcgDescription=" + mcgDescription
				+ ", tpCode=" + tpCode + ", tpDescription=" + tpDescription
				+ ", insertionTimestamp=" + insertionTimestamp
				+ ", sourceSTMCode=" + sourceSTMCode + "]";
	}

}
