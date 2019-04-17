package ac.uk.york.typhon.analytics.casestudies.alphabank.simulator.datatypes;

import java.sql.Date;

public class AC_GNL {
	
	public AC_GNL(String aC_CODE, long aC_FRST_BENF_ID, long aC_ID, String aC_SRC_STM_CODE, Date eFF_DT, Date eND_DT) {
		super();
		AC_CODE = aC_CODE;
		AC_FRST_BENF_ID = aC_FRST_BENF_ID;
		AC_ID = aC_ID;
		AC_SRC_STM_CODE = aC_SRC_STM_CODE;
		EFF_DT = eFF_DT;
		END_DT = eND_DT;
	}
	
	String AC_CODE;
	long AC_FRST_BENF_ID;
	long AC_ID;
	String AC_SRC_STM_CODE;
	Date EFF_DT;
	Date END_DT;
	
	public String getAC_CODE() {
		return AC_CODE;
	}
	public void setAC_CODE(String aC_CODE) {
		AC_CODE = aC_CODE;
	}
	public long getAC_FRST_BENF_ID() {
		return AC_FRST_BENF_ID;
	}
	public void setAC_FRST_BENF_ID(long aC_FRST_BENF_ID) {
		AC_FRST_BENF_ID = aC_FRST_BENF_ID;
	}
	public long getAC_ID() {
		return AC_ID;
	}
	public void setAC_ID(long aC_ID) {
		AC_ID = aC_ID;
	}
	public String getAC_SRC_STM_CODE() {
		return AC_SRC_STM_CODE;
	}
	public void setAC_SRC_STM_CODE(String aC_SRC_STM_CODE) {
		AC_SRC_STM_CODE = aC_SRC_STM_CODE;
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
