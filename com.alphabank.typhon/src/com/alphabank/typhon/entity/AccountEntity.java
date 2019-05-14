package com.alphabank.typhon.entity;

public class AccountEntity {

	private String id;
	private String code;
	private String firstBeneficiary;

	public String getId() {
		return id;
	}

	public String getCode() {
		return code;
	}

	public String getFirstBeneficiary() {
		return firstBeneficiary;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public void setFirstBeneficiary(String firstBeneficiary) {
		this.firstBeneficiary = firstBeneficiary;
	}

	@Override
	public String toString() {
		return "AccountEntity [id=" + id + ", code=" + code
				+ ", firstBeneficiary=" + firstBeneficiary + "]";
	}

}
