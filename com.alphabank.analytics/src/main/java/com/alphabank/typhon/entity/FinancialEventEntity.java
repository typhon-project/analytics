package com.alphabank.typhon.entity;

import java.sql.Date;
import java.sql.Timestamp;

public class FinancialEventEntity {

	private long id;
	private long accountId;
	private Date date;
	private String signCodeDescription;
	private String signCode;
	private double amount;
	private String tunCode;
	private Date effectiveDate;
	private Date endDate;
	private String marchentId;
	private String marchentName;
	private long mcgId;
	private String mcg;
	private String mcgDescription;
	private String tpCode;
	private String tpDescription;
	private Timestamp insertionTimestamp;
	private String sourceSTMCode;

	

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getAccountId() {
		return accountId;
	}

	public void setAccountId(long accountId) {
		this.accountId = accountId;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getSignCodeDescription() {
		return signCodeDescription;
	}

	public void setSignCodeDescription(String signCodeDescription) {
		this.signCodeDescription = signCodeDescription;
	}

	public String getSignCode() {
		return signCode;
	}

	public void setSignCode(String signCode) {
		this.signCode = signCode;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public String getTunCode() {
		return tunCode;
	}

	public void setTunCode(String tunCode) {
		this.tunCode = tunCode;
	}

	public Date getEffectiveDate() {
		return effectiveDate;
	}

	public void setEffectiveDate(Date effectiveDate) {
		this.effectiveDate = effectiveDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getMarchentId() {
		return marchentId;
	}

	public void setMarchentId(String marchentId) {
		this.marchentId = marchentId;
	}

	public String getMarchentName() {
		return marchentName;
	}

	public void setMarchentName(String marchentName) {
		this.marchentName = marchentName;
	}

	public long getMcgId() {
		return mcgId;
	}

	public void setMcgId(long mcgId) {
		this.mcgId = mcgId;
	}

	public String getMcg() {
		return mcg;
	}

	public void setMcg(String mcg) {
		this.mcg = mcg;
	}

	public String getMcgDescription() {
		return mcgDescription;
	}

	public void setMcgDescription(String mcgDescription) {
		this.mcgDescription = mcgDescription;
	}

	public String getTpCode() {
		return tpCode;
	}

	public void setTpCode(String tpCode) {
		this.tpCode = tpCode;
	}

	public String getTpDescription() {
		return tpDescription;
	}

	public void setTpDescription(String tpDescription) {
		this.tpDescription = tpDescription;
	}

	public Timestamp getInsertionTimestamp() {
		return insertionTimestamp;
	}

	public void setInsertionTimestamp(Timestamp insertionTimestamp) {
		this.insertionTimestamp = insertionTimestamp;
	}

	public String getSourceSTMCode() {
		return sourceSTMCode;
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
				+ marchentId + ", marchentName=" + marchentName + ", mcgId="
				+ mcgId + ", mcg=" + mcg + ", mcgDescription=" + mcgDescription
				+ ", tpCode=" + tpCode + ", tpDescription=" + tpDescription
				+ ", insertionTimestamp=" + insertionTimestamp
				+ ", sourceSTMCode=" + sourceSTMCode + "]";
	}
}
