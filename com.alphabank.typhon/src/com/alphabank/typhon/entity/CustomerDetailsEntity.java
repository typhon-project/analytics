package com.alphabank.typhon.entity;

public class CustomerDetailsEntity {

	private String id;
	private String dtlOblgCDICode;
	private String birthDateTime;
	private String primaryEmailAddress;
	private String effectiveDateTime;
	private String endDateTime;
	private String insertionTimeStamp;

	public String getId() {
		return id;
	}

	public String getDtlOblgCDICode() {
		return dtlOblgCDICode;
	}

	public String getBirthDateTime() {
		return birthDateTime;
	}

	public String getPrimaryEmailAddress() {
		return primaryEmailAddress;
	}

	public String getEffectiveDateTime() {
		return effectiveDateTime;
	}

	public String getEndDateTime() {
		return endDateTime;
	}

	public String getInsertionTimeStamp() {
		return insertionTimeStamp;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setDtlOblgCDICode(String dtlOblgCDICode) {
		this.dtlOblgCDICode = dtlOblgCDICode;
	}

	public void setBirthDateTime(String birthDateTime) {
		this.birthDateTime = birthDateTime;
	}

	public void setPrimaryEmailAddress(String primaryEmailAddress) {
		this.primaryEmailAddress = primaryEmailAddress;
	}

	public void setEffectiveDateTime(String effectiveDateTime) {
		this.effectiveDateTime = effectiveDateTime;
	}

	public void setEndDateTime(String endDateTime) {
		this.endDateTime = endDateTime;
	}

	public void setInsertionTimeStamp(String insertionTimeStamp) {
		this.insertionTimeStamp = insertionTimeStamp;
	}

	@Override
	public String toString() {
		return "CustomerDetailsEntity [id=" + id + ", dtlOblgCDICode="
				+ dtlOblgCDICode + ", birthDateTime=" + birthDateTime
				+ ", primaryEmailAddress=" + primaryEmailAddress
				+ ", effectiveDateTime=" + effectiveDateTime + ", endDateTime="
				+ endDateTime + ", insertionTimeStamp=" + insertionTimeStamp
				+ "]";
	}

}
