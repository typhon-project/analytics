package ac.uk.york.typhon.analytics.casestudies.alphabank.simulator.datatypes;

import java.math.BigDecimal;
import java.sql.Date;

public class FNC_EV {
	
	public FNC_EV(long fNC_EV_ID, long fNC_EV_AC_ID, Date fNC_EV_DT, String fNC_EV_SIGN_CODE,
			String fNC_EV_SIGN_CODE_DSC, BigDecimal fNC_EV_AMT, String fNC_EV_AC_SRC_STM_CODE, String mRCH_NAME,
			String mCG_DSC, Date eFF_DT, Date eND_DT) {
		super();
		FNC_EV_ID = fNC_EV_ID;
		FNC_EV_AC_ID = fNC_EV_AC_ID;
		FNC_EV_DT = fNC_EV_DT;
		FNC_EV_SIGN_CODE = fNC_EV_SIGN_CODE;
		FNC_EV_SIGN_CODE_DSC = fNC_EV_SIGN_CODE_DSC;
		FNC_EV_AMT = fNC_EV_AMT;
		FNC_EV_AC_SRC_STM_CODE = fNC_EV_AC_SRC_STM_CODE;
		MRCH_NAME = mRCH_NAME;
		MCG_DSC = mCG_DSC;
		EFF_DT = eFF_DT;
		END_DT = eND_DT;
	}
	long FNC_EV_ID;
	long FNC_EV_AC_ID;
	Date FNC_EV_DT;
	String FNC_EV_SIGN_CODE;
	String FNC_EV_SIGN_CODE_DSC;
	BigDecimal FNC_EV_AMT;
	String FNC_EV_AC_SRC_STM_CODE;
	String MRCH_NAME;
	String MCG_DSC;
	Date EFF_DT;
	Date END_DT;
	
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
	public String getFNC_EV_SIGN_CODE() {
		return FNC_EV_SIGN_CODE;
	}
	public void setFNC_EV_SIGN_CODE(String fNC_EV_SIGN_CODE) {
		FNC_EV_SIGN_CODE = fNC_EV_SIGN_CODE;
	}
	public String getFNC_EV_SIGN_CODE_DSC() {
		return FNC_EV_SIGN_CODE_DSC;
	}
	public void setFNC_EV_SIGN_CODE_DSC(String fNC_EV_SIGN_CODE_DSC) {
		FNC_EV_SIGN_CODE_DSC = fNC_EV_SIGN_CODE_DSC;
	}
	public BigDecimal getFNC_EV_AMT() {
		return FNC_EV_AMT;
	}
	public void setFNC_EV_AMT(BigDecimal fNC_EV_AMT) {
		FNC_EV_AMT = fNC_EV_AMT;
	}
	public String getFNC_EV_AC_SRC_STM_CODE() {
		return FNC_EV_AC_SRC_STM_CODE;
	}
	public void setFNC_EV_AC_SRC_STM_CODE(String fNC_EV_AC_SRC_STM_CODE) {
		FNC_EV_AC_SRC_STM_CODE = fNC_EV_AC_SRC_STM_CODE;
	}
	public String getMRCH_NAME() {
		return MRCH_NAME;
	}
	public void setMRCH_NAME(String mRCH_NAME) {
		MRCH_NAME = mRCH_NAME;
	}
	public String getMCG_DSC() {
		return MCG_DSC;
	}
	public void setMCG_DSC(String mCG_DSC) {
		MCG_DSC = mCG_DSC;
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
	public String toInsertSQLString() {
		String sql = "INSERT INTO TABLE FNC_EV VALUES('" + this.FNC_EV_ID + "','" + this.FNC_EV_AC_ID + "','" + this.FNC_EV_DT + "','" + this.FNC_EV_SIGN_CODE 
				+ "','" + this.FNC_EV_SIGN_CODE_DSC + "','" + this.FNC_EV_AMT + "','" + this.FNC_EV_AC_SRC_STM_CODE + "','" + this.MRCH_NAME + "','" + this.MCG_DSC
				+ "','" + this.EFF_DT + "','" + this.END_DT + "')";
		return sql;
	}
}
