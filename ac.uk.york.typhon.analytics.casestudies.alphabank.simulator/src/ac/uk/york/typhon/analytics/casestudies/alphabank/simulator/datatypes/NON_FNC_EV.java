package ac.uk.york.typhon.analytics.casestudies.alphabank.simulator.datatypes;

import java.sql.Date;
import java.sql.Timestamp;

public class NON_FNC_EV {
	
	
	public NON_FNC_EV(long nON_FNC_EV_ID, String nON_FNC_EV_TUN_CODE, String nON_FNC_EV_TP_CODE, long nON_FNC_EV_AC_ID,
			String nON_FNC_EV_AC_CODE, String nON_FNC_EV_ACTN_CODE, String nON_FNC_EV_ACTN_DSC, Timestamp nON_FNC_EV_DT_TM,
			String nON_FNC_EV_CDI_CODE, Date eFF_DT, Date eND_DT) {
		super();
		NON_FNC_EV_ID = nON_FNC_EV_ID;
		NON_FNC_EV_TUN_CODE = nON_FNC_EV_TUN_CODE;
		NON_FNC_EV_TP_CODE = nON_FNC_EV_TP_CODE;
		NON_FNC_EV_AC_ID = nON_FNC_EV_AC_ID;
		NON_FNC_EV_AC_CODE = nON_FNC_EV_AC_CODE;
		NON_FNC_EV_ACTN_CODE = nON_FNC_EV_ACTN_CODE;
		NON_FNC_EV_ACTN_DSC = nON_FNC_EV_ACTN_DSC;
		NON_FNC_EV_DT_TM = nON_FNC_EV_DT_TM;
		NON_FNC_EV_CDI_CODE = nON_FNC_EV_CDI_CODE;
		EFF_DT = eFF_DT;
		END_DT = eND_DT;
	}
	
	long NON_FNC_EV_ID;
	String NON_FNC_EV_TUN_CODE;
	String NON_FNC_EV_TP_CODE;
	long NON_FNC_EV_AC_ID;
	String NON_FNC_EV_AC_CODE;
	String NON_FNC_EV_ACTN_CODE;
	String NON_FNC_EV_ACTN_DSC;
	Timestamp NON_FNC_EV_DT_TM;
	String NON_FNC_EV_CDI_CODE;
	Date EFF_DT;
	Date END_DT;
	
	public long getNON_FNC_EV_ID() {
		return NON_FNC_EV_ID;
	}
	public void setNON_FNC_EV_ID(long nON_FNC_EV_ID) {
		NON_FNC_EV_ID = nON_FNC_EV_ID;
	}
	public String getNON_FNC_EV_TUN_CODE() {
		return NON_FNC_EV_TUN_CODE;
	}
	public void setNON_FNC_EV_TUN_CODE(String nON_FNC_EV_TUN_CODE) {
		NON_FNC_EV_TUN_CODE = nON_FNC_EV_TUN_CODE;
	}
	public String getNON_FNC_EV_TP_CODE() {
		return NON_FNC_EV_TP_CODE;
	}
	public void setNON_FNC_EV_TP_CODE(String nON_FNC_EV_TP_CODE) {
		NON_FNC_EV_TP_CODE = nON_FNC_EV_TP_CODE;
	}
	public long getNON_FNC_EV_AC_ID() {
		return NON_FNC_EV_AC_ID;
	}
	public void setNON_FNC_EV_AC_ID(long nON_FNC_EV_AC_ID) {
		NON_FNC_EV_AC_ID = nON_FNC_EV_AC_ID;
	}
	public String getNON_FNC_EV_AC_CODE() {
		return NON_FNC_EV_AC_CODE;
	}
	public void setNON_FNC_EV_AC_CODE(String nON_FNC_EV_AC_CODE) {
		NON_FNC_EV_AC_CODE = nON_FNC_EV_AC_CODE;
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
	public Timestamp getNON_FNC_EV_DT_TM() {
		return NON_FNC_EV_DT_TM;
	}
	public void setNON_FNC_EV_DT_TM(Timestamp nON_FNC_EV_DT_TM) {
		NON_FNC_EV_DT_TM = nON_FNC_EV_DT_TM;
	}
	public String getNON_FNC_EV_CDI_CODE() {
		return NON_FNC_EV_CDI_CODE;
	}
	public void setNON_FNC_EV_CDI_CODE(String nON_FNC_EV_CDI_CODE) {
		NON_FNC_EV_CDI_CODE = nON_FNC_EV_CDI_CODE;
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
