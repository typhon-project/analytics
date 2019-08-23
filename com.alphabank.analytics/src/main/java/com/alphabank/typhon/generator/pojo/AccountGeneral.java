package com.alphabank.typhon.generator.pojo;

import java.sql.Date;
import java.sql.Timestamp;

public class AccountGeneral {
	
	public AccountGeneral(long aC_ID, String aC_CODE, String aC_SRC_STM_CODE, long aC_FRST_BENF_ID,
			String aC_FRST_BENF_CDI_CODE, Timestamp iSRT_TMS, Date eFF_DT, Date eND_DT) {
		super();
		AC_ID = aC_ID;
		AC_CODE = aC_CODE;
		AC_SRC_STM_CODE = aC_SRC_STM_CODE;
		AC_FRST_BENF_ID = aC_FRST_BENF_ID;
		AC_FRST_BENF_CDI_CODE = aC_FRST_BENF_CDI_CODE;
		ISRT_TMS = iSRT_TMS;
		EFF_DT = eFF_DT;
		END_DT = eND_DT;
	}
	
	long AC_ID;
	String AC_CODE;
	String AC_SRC_STM_CODE;
	long AC_FRST_BENF_ID;
	String AC_FRST_BENF_CDI_CODE;
	Timestamp ISRT_TMS;
	Date EFF_DT;
	Date END_DT;
	
	public long getAC_ID() {
		return AC_ID;
	}
	public void setAC_ID(long aC_ID) {
		AC_ID = aC_ID;
	}
	public String getAC_CODE() {
		return AC_CODE;
	}
	public void setAC_CODE(String aC_CODE) {
		AC_CODE = aC_CODE;
	}
	public String getAC_SRC_STM_CODE() {
		return AC_SRC_STM_CODE;
	}
	public void setAC_SRC_STM_CODE(String aC_SRC_STM_CODE) {
		AC_SRC_STM_CODE = aC_SRC_STM_CODE;
	}
	public long getAC_FRST_BENF_ID() {
		return AC_FRST_BENF_ID;
	}
	public void setAC_FRST_BENF_ID(long aC_FRST_BENF_ID) {
		AC_FRST_BENF_ID = aC_FRST_BENF_ID;
	}
	public String getAC_FRST_BENF_CDI_CODE() {
		return AC_FRST_BENF_CDI_CODE;
	}
	public void setAC_FRST_BENF_CDI_CODE(String aC_FRST_BENF_CDI_CODE) {
		AC_FRST_BENF_CDI_CODE = aC_FRST_BENF_CDI_CODE;
	}
	public Timestamp getISRT_TMS() {
		return ISRT_TMS;
	}
	public void setISRT_TMS(Timestamp iSRT_TMS) {
		ISRT_TMS = iSRT_TMS;
	}
	public Date getEFF_DT() {
		return EFF_DT;
	}
	public void setEFF_DT(Date eFF_DT) {
		EFF_DT = eFF_DT;
	}
	public Date getEND_DT() {
		return END_DT;
	}
	public void setEND_DT(Date eND_DT) {
		END_DT = eND_DT;
	}
	
}
