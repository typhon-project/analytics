package ac.uk.york.typhon.query.generator.pojo;

import java.sql.Date;
import java.sql.Timestamp;

public class ObligatoryDetails {

	public ObligatoryDetails(long oBLG_ID, String oBLG_DTL_OBLG_CDI_CODE,
			Date oBLG_BRTH_DT, String oBLG_PRIM_EMAIL_ADR, Date eFF_DT,
			Date eND_DT, Timestamp iSRT_TMS) {
		super();
		OBLG_ID = oBLG_ID;
		OBLG_DTL_OBLG_CDI_CODE = oBLG_DTL_OBLG_CDI_CODE;
		OBLG_BRTH_DT = oBLG_BRTH_DT;
		OBLG_PRIM_EMAIL_ADR = oBLG_PRIM_EMAIL_ADR;
		EFF_DT = eFF_DT;
		END_DT = eND_DT;
		ISRT_TMS = iSRT_TMS;
	}

	long OBLG_ID;
	String OBLG_DTL_OBLG_CDI_CODE;
	Date OBLG_BRTH_DT;
	String OBLG_PRIM_EMAIL_ADR;
	Date EFF_DT;
	Date END_DT;
	Timestamp ISRT_TMS;

	public long getOBLG_ID() {
		return OBLG_ID;
	}

	public void setOBLG_ID(long oBLG_ID) {
		OBLG_ID = oBLG_ID;
	}

	public String getOBLG_DTL_OBLG_CDI_CODE() {
		return OBLG_DTL_OBLG_CDI_CODE;
	}

	public void setOBLG_DTL_OBLG_CDI_CODE(String oBLG_DTL_OBLG_CDI_CODE) {
		OBLG_DTL_OBLG_CDI_CODE = oBLG_DTL_OBLG_CDI_CODE;
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

	public Timestamp getISRT_TMS() {
		return ISRT_TMS;
	}

	public void setISRT_TMS(Timestamp iSRT_TMS) {
		ISRT_TMS = iSRT_TMS;
	}

}
