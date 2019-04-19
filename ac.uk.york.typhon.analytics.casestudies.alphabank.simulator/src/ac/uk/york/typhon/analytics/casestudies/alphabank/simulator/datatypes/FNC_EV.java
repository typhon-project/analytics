package ac.uk.york.typhon.analytics.casestudies.alphabank.simulator.datatypes;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;

public class FNC_EV {
	
	public FNC_EV(long fNC_EV_ID, long fNC_EV_AC_ID, Date fNC_EV_DT, String fNC_EV_SIGN_CODE_DSC,
			String fNC_EV_SIGN_CODE, BigDecimal fNC_EV_AMT, String fNC_EV_TUN_CODE, Date eFF_DT, Date eND_DT,
			String mRCH_ID, String mRCH_NAME, long mCG_ID, String mCG, String mCG_DSC, String fNC_EV_TP_CODE,
			String fNC_EV_TP_DSC, Timestamp iSRT_TMS, String fNC_EV_SRC_STM_CODE) {
		super();
		FNC_EV_ID = fNC_EV_ID;
		FNC_EV_AC_ID = fNC_EV_AC_ID;
		FNC_EV_DT = fNC_EV_DT;
		FNC_EV_SIGN_CODE_DSC = fNC_EV_SIGN_CODE_DSC;
		FNC_EV_SIGN_CODE = fNC_EV_SIGN_CODE;
		FNC_EV_AMT = fNC_EV_AMT;
		FNC_EV_TUN_CODE = fNC_EV_TUN_CODE;
		EFF_DT = eFF_DT;
		END_DT = eND_DT;
		MRCH_ID = mRCH_ID;
		MRCH_NAME = mRCH_NAME;
		MCG_ID = mCG_ID;
		MCG = mCG;
		MCG_DSC = mCG_DSC;
		FNC_EV_TP_CODE = fNC_EV_TP_CODE;
		FNC_EV_TP_DSC = fNC_EV_TP_DSC;
		ISRT_TMS = iSRT_TMS;
		FNC_EV_SRC_STM_CODE = fNC_EV_SRC_STM_CODE;
	}

	long FNC_EV_ID;
	long FNC_EV_AC_ID;
	Date FNC_EV_DT;
	String FNC_EV_SIGN_CODE_DSC;
	String FNC_EV_SIGN_CODE;
	BigDecimal FNC_EV_AMT;
	String FNC_EV_TUN_CODE;
	Date EFF_DT;
	Date END_DT;
	String MRCH_ID;
	String MRCH_NAME;//
	long MCG_ID;
	String MCG;
	String MCG_DSC;
	String FNC_EV_TP_CODE;
	String FNC_EV_TP_DSC;
	Timestamp ISRT_TMS;
	String FNC_EV_SRC_STM_CODE;

	public long getFNC_EV_ID() {
		return FNC_EV_ID;
	}
	
	public void setFNC_EV_ID(long fNC_EV_ID) {
		FNC_EV_ID = fNC_EV_ID;
	}

	public long getFNC_EV_AC_ID() {
		return FNC_EV_AC_ID;
	}

	public void setFNC_EV_AC_ID(long fNC_EV_AC_ID) {
		FNC_EV_AC_ID = fNC_EV_AC_ID;
	}

	public Date getFNC_EV_DT() {
		return FNC_EV_DT;
	}

	public void setFNC_EV_DT(Date fNC_EV_DT) {
		FNC_EV_DT = fNC_EV_DT;
	}

	public String getFNC_EV_SIGN_CODE_DSC() {
		return FNC_EV_SIGN_CODE_DSC;
	}

	public void setFNC_EV_SIGN_CODE_DSC(String fNC_EV_SIGN_CODE_DSC) {
		FNC_EV_SIGN_CODE_DSC = fNC_EV_SIGN_CODE_DSC;
	}

	public String getFNC_EV_SIGN_CODE() {
		return FNC_EV_SIGN_CODE;
	}

	public void setFNC_EV_SIGN_CODE(String fNC_EV_SIGN_CODE) {
		FNC_EV_SIGN_CODE = fNC_EV_SIGN_CODE;
	}

	public BigDecimal getFNC_EV_AMT() {
		return FNC_EV_AMT;
	}

	public void setFNC_EV_AMT(BigDecimal fNC_EV_AMT) {
		FNC_EV_AMT = fNC_EV_AMT;
	}

	public String getFNC_EV_TUN_CODE() {
		return FNC_EV_TUN_CODE;
	}

	public void setFNC_EV_TUN_CODE(String fNC_EV_TUN_CODE) {
		FNC_EV_TUN_CODE = fNC_EV_TUN_CODE;
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

	public String getMRCH_ID() {
		return MRCH_ID;
	}

	public void setMRCH_ID(String mRCH_ID) {
		MRCH_ID = mRCH_ID;
	}

	public String getMRCH_NAME() {
		return MRCH_NAME;
	}

	public void setMRCH_NAME(String mRCH_NAME) {
		MRCH_NAME = mRCH_NAME;
	}

	public long getMCG_ID() {
		return MCG_ID;
	}

	public void setMCG_ID(long mCG_ID) {
		MCG_ID = mCG_ID;
	}

	public String getMCG() {
		return MCG;
	}

	public void setMCG(String mCG) {
		MCG = mCG;
	}

	public String getMCG_DSC() {
		return MCG_DSC;
	}

	public void setMCG_DSC(String mCG_DSC) {
		MCG_DSC = mCG_DSC;
	}

	public String getFNC_EV_TP_CODE() {
		return FNC_EV_TP_CODE;
	}

	public void setFNC_EV_TP_CODE(String fNC_EV_TP_CODE) {
		FNC_EV_TP_CODE = fNC_EV_TP_CODE;
	}

	public String getFNC_EV_TP_DSC() {
		return FNC_EV_TP_DSC;
	}

	public void setFNC_EV_TP_DSC(String fNC_EV_TP_DSC) {
		FNC_EV_TP_DSC = fNC_EV_TP_DSC;
	}

	public Timestamp getISRT_TMS() {
		return ISRT_TMS;
	}

	public void setISRT_TMS(Timestamp iSRT_TMS) {
		ISRT_TMS = iSRT_TMS;
	}

	public String getFNC_EV_SRC_STM_CODE() {
		return FNC_EV_SRC_STM_CODE;
	}

	public void setFNC_EV_SRC_STM_CODE(String fNC_EV_SRC_STM_CODE) {
		FNC_EV_SRC_STM_CODE = fNC_EV_SRC_STM_CODE;
	}
	
	public String toInsertSQLString() {
		String sql = "INSERT INTO TABLE FNC_EV VALUES('" + this.FNC_EV_ID + "','" + this.FNC_EV_AC_ID + "','" + this.FNC_EV_DT + "','" + this.FNC_EV_SIGN_CODE_DSC 
				+ "','" + this.FNC_EV_SIGN_CODE + "','" + this.FNC_EV_AMT + "','" + this.FNC_EV_TUN_CODE + "','" + this.EFF_DT + "','" + this.END_DT
				+ "','" + this.MRCH_ID + "','" + this.MRCH_NAME + "','" + this.MCG_ID + "','" + this.MCG + "','" + this.MCG_DSC + "','" + this.FNC_EV_TP_CODE 
				+ "','" + this.FNC_EV_TP_DSC + "','" + this.ISRT_TMS + this.FNC_EV_SRC_STM_CODE + "','" + "')";
		return sql;
	}
	
}
