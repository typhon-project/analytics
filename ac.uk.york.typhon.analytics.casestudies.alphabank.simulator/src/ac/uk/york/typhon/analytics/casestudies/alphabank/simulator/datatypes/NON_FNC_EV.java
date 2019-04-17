package ac.uk.york.typhon.analytics.casestudies.alphabank.simulator.datatypes;

import java.sql.Date;

public class NON_FNC_EV {
	
	public NON_FNC_EV(long nON_FNC_EV_ID, String nON_FNC_EV_ACTN_CODE, String nON_FNC_EV_ACTN_DSC,
			String nON_FNC_EV_AC_CODE, long nON_FNC_EV_DT_TM, Date eFF_DT, Date eND_DT) {
		super();
		NON_FNC_EV_ID = nON_FNC_EV_ID;
		NON_FNC_EV_ACTN_CODE = nON_FNC_EV_ACTN_CODE;
		NON_FNC_EV_ACTN_DSC = nON_FNC_EV_ACTN_DSC;
		NON_FNC_EV_AC_CODE = nON_FNC_EV_AC_CODE;
		NON_FNC_EV_DT_TM = nON_FNC_EV_DT_TM;
		EFF_DT = eFF_DT;
		END_DT = eND_DT;
	}
	
	long NON_FNC_EV_ID;
	String NON_FNC_EV_ACTN_CODE;
	String NON_FNC_EV_ACTN_DSC;
	String NON_FNC_EV_AC_CODE;
	long NON_FNC_EV_DT_TM;
	Date EFF_DT;
	Date END_DT;
	
	public long getNON_FNC_EV_ID() {
		return NON_FNC_EV_ID;
	}
	public void setNON_FNC_EV_ID(long nON_FNC_EV_ID) {
		NON_FNC_EV_ID = nON_FNC_EV_ID;
	}
	public String getNON_FNC_EV_ACTN_CODE() {
		return NON_FNC_EV_ACTN_CODE;
	}
	public void setNON_FNC_EV_ACTN_CODE(String nON_FNC_EV_ACTN_CODE) {
		NON_FNC_EV_ACTN_CODE = nON_FNC_EV_ACTN_CODE;
	}
	public String getNON_FNC_EV_ACTN_DSC() {
		return NON_FNC_EV_ACTN_DSC;
	}
	public void setNON_FNC_EV_ACTN_DSC(String nON_FNC_EV_ACTN_DSC) {
		NON_FNC_EV_ACTN_DSC = nON_FNC_EV_ACTN_DSC;
	}
	public String getNON_FNC_EV_AC_CODE() {
		return NON_FNC_EV_AC_CODE;
	}
	public void setNON_FNC_EV_AC_CODE(String nON_FNC_EV_AC_CODE) {
		NON_FNC_EV_AC_CODE = nON_FNC_EV_AC_CODE;
	}
	public long getNON_FNC_EV_DT_TM() {
		return NON_FNC_EV_DT_TM;
	}
	public void setNON_FNC_EV_DT_TM(long nON_FNC_EV_DT_TM) {
		NON_FNC_EV_DT_TM = nON_FNC_EV_DT_TM;
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
