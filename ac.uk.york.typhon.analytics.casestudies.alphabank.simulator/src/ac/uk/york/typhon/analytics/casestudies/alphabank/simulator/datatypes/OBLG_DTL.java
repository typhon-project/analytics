package ac.uk.york.typhon.analytics.casestudies.alphabank.simulator.datatypes;

import java.sql.Date;

public class OBLG_DTL {
	
	public OBLG_DTL(long oBLG_ID, Date oBLG_BRTH_DT, String oBLG_PRIM_EMAIL_ADR, Date eFF_DT, Date eND_DT) {
		super();
		OBLG_ID = oBLG_ID;
		OBLG_BRTH_DT = oBLG_BRTH_DT;
		OBLG_PRIM_EMAIL_ADR = oBLG_PRIM_EMAIL_ADR;
		EFF_DT = eFF_DT;
		END_DT = eND_DT;
	}
	
	long OBLG_ID;
	Date OBLG_BRTH_DT;
	String OBLG_PRIM_EMAIL_ADR;
	Date EFF_DT;
	Date END_DT;
	
	public long getOBLG_ID() {
		return OBLG_ID;
	}
	public void setOBLG_ID(long oBLG_ID) {
		OBLG_ID = oBLG_ID;
	}
	public Date getOBLG_BRTH_DT() {
		return OBLG_BRTH_DT;
	}
	public void setOBLG_BRTH_DT(Date oBLG_BRTH_DT) {
		OBLG_BRTH_DT = oBLG_BRTH_DT;
	}
	public String getOBLG_PRIM_EMAIL_ADR() {
		return OBLG_PRIM_EMAIL_ADR;
	}
	public void setOBLG_PRIM_EMAIL_ADR(String oBLG_PRIM_EMAIL_ADR) {
		OBLG_PRIM_EMAIL_ADR = oBLG_PRIM_EMAIL_ADR;
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
