package com.alphabank.typhon.commons;

import ac.uk.york.typhon.analytics.commons.Constants;

public abstract class AlphaConstants extends Constants {

	public interface Database {
		String URL = "database.url";
		String USERNAME = "database.username";
		String PASSWORD = "database.password";

	}

	public interface DormantFlags {

		String ACTION_CODE = "7";
		String EVENT_TYPE_CODE = "OAA";
		int CUSTOMER_AGE_LIMIT = 70;
	}

	public interface Table {

		public interface FinancialEvent {
			public static String getName() {
				return FinancialEvent.class.getName();
			}

			public static String ID = "FNC_EV_ID";
			public static String AC_ID = "FNC_EV_AC_ID";
			public static String DT = "FNC_EV_DT";
			public static String SIGN_CODE_DSC = "FNC_EV_SIGN_CODE_DSC";
			public static String SIGN_CODE = "FNC_EV_SIGN_CODE";
			public static String AMT = "FNC_EV_AMT";
			public static String TUN_CODE = "FNC_EV_TUN_CODE";
			public static String EFF_DT = "EFF_DT";
			public static String END_DT = "END_DT";
			public static String MRCH_ID = "MRCH_ID";
			public static String MRCH_NAME = "MRCH_NAME";
			public static String MCG_ID = "MCG_ID";
			public static String MCG = "MCG";
			public static String MCG_DSC = "MCG_DSC";
			public static String TP_CODE = "FNC_EV_TP_CODE";
			public static String TP_DSC = "FNC_EV_TP_DSC";
			public static String ISRT_TMS = "ISRT_TMS";
			public static String SRC_STM_CODE = "FNC_EV_SRC_STM_CODE";
		}

		public interface NonFinancialEvent {
			public static String getName() {
				return NonFinancialEvent.class.getName();
			}

			// String EVENT_ID = "EVENT_ID";
			// String ACCOUNT_CODE = "ACCOUNT_CODE";
			// String ACTION_CODE = "ACTION_CODE";
			// String EVENT_TYPE_CODE = "EVENT_TYPE_CODE";

			public static String ID = "NON_FNC_EV_ID";
			public static String TUN_CODE = "NON_FNC_EV_TUN_CODE";
			public static String TP_CODE = "NON_FNC_EV_TP_CODE";
			public static String AC_ID = "NON_FNC_EV_AC_ID";
			public static String AC_CODE = "NON_FNC_EV_AC_CODE";
			public static String ACTN_CODE = "NON_FNC_EV_ACTN_CODE";
			public static String ACTN_DSC = "NON_FNC_EV_ACTN_DSC";
			public static String DT_TM = "NON_FNC_EV_DT_TM";
			public static String CDI_CODE = "NON_FNC_EV_CDI_CODE";
			public static String EFF_DT = "EFF_DT";
			public static String END_DT = "END_DT";

		}

		public interface Transactions {
			public static String getName() {
				return Transactions.class.getName();
			}
			// String FNC_EV_ID = "FNC_EV_ID";
			// String FNC_EV_AC_ID = "FNC_EV_AC_ID";
			// String FNC_EV_AMT = "FNC_EV_AMT";

		}

		public interface CustomerDetails {
			public static String getName() {
				return "oblg_dtl";
			}

			public static String ID = "OBLG_ID";
			public static String DTL_OBLG_CDI_CODE = "OBLG_DTL_OBLG_CDI_CODE";
			public static String BRTH_DT = "OBLG_BRTH_DT";
			public static String PRIM_EMAIL_ADR = "OBLG_PRIM_EMAIL_ADR";
			public static String EFF_DT = "EFF_DT";
			public static String END_DT = "END_DT";
			public static String ISRT_TMS = "ISRT_TMS";

		}

		public interface Account {
			public static String getName() {
				return "ac_gnl";
			}

			public static String AC_ID = "AC_ID";
			public static String AC_CODE = "AC_CODE";
			public static String AC_SRC_STM_CODE = "AC_SRC_STM_CODE";
			public static String AC_FRST_BENF_ID = "AC_FRST_BENF_ID";
			public static String AC_FRST_BENF_CDI_CODE = "AC_FRST_BENF_CDI_CODE";
			public static String ISRT_TMS = "ISRT_TMS";
			public static String EFF_DT = "EFF_DT";
			public static String END_DT = "END_DT";

		}
	}
}
