package com.alphabank.typhon.entity;

public class NonFinancialEventEntity {
	
	private String id;
	private String accountCode;
	private String actionCode;
	private String eventTypeCode;
	public String getId() {
		return id;
	}
	public String getAccountCode() {
		return accountCode;
	}
	public String getActionCode() {
		return actionCode;
	}
	public String getEventTypeCode() {
		return eventTypeCode;
	}
	public void setId(String id) {
		this.id = id;
	}
	public void setAccountCode(String accountCode) {
		this.accountCode = accountCode;
	}
	public void setActionCode(String actionCode) {
		this.actionCode = actionCode;
	}
	public void setEventTypeCode(String eventTypeCode) {
		this.eventTypeCode = eventTypeCode;
	}
	@Override
	public String toString() {
		return "NonFinancialEvent [id=" + id + ", accountCode=" + accountCode
				+ ", actionCode=" + actionCode + ", eventTypeCode="
				+ eventTypeCode + "]";
	}

}
