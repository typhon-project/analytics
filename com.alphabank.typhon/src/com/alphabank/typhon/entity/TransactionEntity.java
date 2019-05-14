package com.alphabank.typhon.entity;

public class TransactionEntity {

	private String id;
	private String accountId;
	private String amount;
	public String getId() {
		return id;
	}
	public String getAccountId() {
		return accountId;
	}
	public String getAmount() {
		return amount;
	}
	public void setId(String id) {
		this.id = id;
	}
	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	@Override
	public String toString() {
		return "TransactionEntity [id=" + id + ", accountId=" + accountId
				+ ", amount=" + amount + "]";
	}

	

}
