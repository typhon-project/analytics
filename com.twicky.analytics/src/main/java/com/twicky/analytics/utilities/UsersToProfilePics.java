package com.twicky.analytics.utilities;

import java.util.HashMap;

public class UsersToProfilePics {
	

	
	public static String getProfilePicUrl(String user) {
		HashMap<String, String> userToProfilePicsMap = new HashMap<String, String>();
		userToProfilePicsMap.put("RemainerNow", "https://pbs.twimg.com/profile_images/1118079478650015745/tYUE9YPq_400x400.jpg");
		userToProfilePicsMap.put("SoVeryBrexit", "https://pbs.twimg.com/profile_images/1012653434531667968/OBo8neIi_400x400.jpg");
		userToProfilePicsMap.put("astro_trader", "https://pbs.twimg.com/profile_images/815281899539152898/PAU9ul-Q_400x400.jpg");
		userToProfilePicsMap.put("VermisstPfarrer", "https://pbs.twimg.com/profile_images/1045446945014263808/FyCyU_34_400x400.jpg");
		userToProfilePicsMap.put("LeaveHQ", "https://pbs.twimg.com/profile_images/660572342020653056/ouD20S-o_400x400.png");
		userToProfilePicsMap.put("deni444", "https://pbs.twimg.com/profile_images/1133377476279918595/E3VsB3wD_400x400.jpg");
		userToProfilePicsMap.put("Onmeed", "https://pbs.twimg.com/profile_images/796402265657462784/ivex8tTt_400x400.jpg");
		userToProfilePicsMap.put("SomersetBlue", "https://pbs.twimg.com/profile_images/1158496519907160064/7aXzDMxo_400x400.jpg");
		userToProfilePicsMap.put("oflynnsocial", "https://pbs.twimg.com/profile_images/1149385684/patrick_oflynn_400x400.jpg");
		userToProfilePicsMap.put("DalbidEU", "https://pbs.twimg.com/profile_images/667073520254500864/Ggy93IBC_400x400.jpg");
		userToProfilePicsMap.put("ShottsJimmie", "https://abs.twimg.com/sticky/default_profile_images/default_profile_400x400.png");
		userToProfilePicsMap.put("Liptoplap", "https://pbs.twimg.com/profile_images/1096883119813263361/LHCow_b3_400x400.jpg");
		userToProfilePicsMap.put("mamamuse", "https://pbs.twimg.com/profile_images/1122290232596918273/HqQoWWXy_400x400.png");
		userToProfilePicsMap.put("Rachel5742", "https://pbs.twimg.com/profile_images/665837547382284288/oxj7-5Vh_400x400.jpg");
		userToProfilePicsMap.put("JPLT59", "https://pbs.twimg.com/profile_images/1143918675554123777/exvliiB6_400x400.jpg");
		userToProfilePicsMap.put("Steephen612", "https://pbs.twimg.com/profile_images/1021394055081709569/74HFvgvW_400x400.jpg");
		userToProfilePicsMap.put("Eyesheavenward", "https://pbs.twimg.com/profile_images/1129824397064323073/zGHi-z72_400x400.jpg");
		userToProfilePicsMap.put("PeteNorth303", "https://pbs.twimg.com/profile_images/1021394055081709569/74HFvgvW_400x400.jpg");
		return userToProfilePicsMap.get(user);
	}

}
