package com.alphabank.typhon.entity;

public class AccountEntity {

	private String id;
	private String code;
	private String sourceSTMCode;
	private String firstBeneficiaryId;
	private String firstBeneficiaryCDICode;
	private String insertionTimeSTamp;
	private String effectiveDate;
	private String endDate;

	public String getId() {
		return id;
	}

	public String getCode() {
		return code;
	}

	public String getSourceSTMCode() {
		return sourceSTMCode;
	}

	public String getFirstBeneficiaryId() {
		return firstBeneficiaryId;
	}

	public String getFirstBeneficiaryCDICode() {
		return firstBeneficiaryCDICode;
	}

	public String getInsertionTimeSTamp() {
		return insertionTimeSTamp;
	}

	public String getEffectiveDate() {
		return effectiveDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public void setSourceSTMCode(String sourceSTMCode) {
		this.sourceSTMCode = sourceSTMCode;
	}

	public void setFirstBeneficiaryId(String firstBeneficiaryId) {
		this.firstBeneficiaryId = firstBeneficiaryId;
	}

	public void setFirstBeneficiaryCDICode(String firstBeneficiaryCDICode) {
		this.firstBeneficiaryCDICode = firstBeneficiaryCDICode;
	}

	public void setInsertionTimeSTamp(String insertionTimeSTamp) {
		this.insertionTimeSTamp = insertionTimeSTamp;
	}

	public void setEffectiveDate(String effectiveDate) {
		this.effectiveDate = effectiveDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	@Override
	public String toString() {
		return "AccountEntity [id=" + id + ", code=" + code
				+ ", sourceSTMCode=" + sourceSTMCode + ", firstBeneficiaryId="
				+ firstBeneficiaryId + ", firstBeneficiaryCDICode="
				+ firstBeneficiaryCDICode + ", insertionTimeSTamp="
				+ insertionTimeSTamp + ", effectiveDate=" + effectiveDate
				+ ", endDate=" + endDate + "]";
	}

}
