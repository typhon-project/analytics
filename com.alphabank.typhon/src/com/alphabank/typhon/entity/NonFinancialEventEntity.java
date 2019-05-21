package com.alphabank.typhon.entity;

import java.sql.Date;
import java.sql.Timestamp;

public class NonFinancialEventEntity {

	private long id;
	private String tunCode;
	private String tpCode;
	private long accountId;
	private String accountCode;
	private String actionCode;
	private String actionDescription;
	private Timestamp dateTime;
	private String cdiCode;
	private Date effectiveDate;
	private Date endDate;

	public long getId() {
		return id;
	}

	public String getTunCode() {
		return tunCode;
	}

	public String getTpCode() {
		return tpCode;
	}

	public long getAccountId() {
		return accountId;
	}

	public String getAccountCode() {
		return accountCode;
	}

	public String getActionCode() {
		return actionCode;
	}

	public String getActionDescription() {
		return actionDescription;
	}

	public Timestamp getDateTime() {
		return dateTime;
	}

	public String getCdiCode() {
		return cdiCode;
	}

	public Date getEffectiveDate() {
		return effectiveDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setId(long id) {
		this.id = id;
	}

	public void setTunCode(String tunCode) {
		this.tunCode = tunCode;
	}

	public void setTpCode(String tpCode) {
		this.tpCode = tpCode;
	}

	public void setAccountId(long accountId) {
		this.accountId = accountId;
	}

	public void setAccountCode(String accountCode) {
		this.accountCode = accountCode;
	}

	public void setActionCode(String actionCode) {
		this.actionCode = actionCode;
	}

	public void setActionDescription(String actionDescription) {
		this.actionDescription = actionDescription;
	}

	public void setDateTime(Timestamp dateTime) {
		this.dateTime = dateTime;
	}

	public void setCdiCode(String cdiCode) {
		this.cdiCode = cdiCode;
	}

	public void setEffectiveDate(Date effectiveDate) {
		this.effectiveDate = effectiveDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	@Override
	public String toString() {
		return "NonFinancialEventEntity [id=" + id + ", tunCode=" + tunCode
				+ ", tpCode=" + tpCode + ", accountId=" + accountId
				+ ", accountCode=" + accountCode + ", actionCode=" + actionCode
				+ ", actionDescription=" + actionDescription + ", dateTime="
				+ dateTime + ", cdiCode=" + cdiCode + ", effectiveDate="
				+ effectiveDate + ", endDate=" + endDate + "]";
	}

}
