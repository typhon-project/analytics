package ac.york.typhon.analytics.commons.datatypes;

import java.lang.reflect.Field;

import org.apache.flink.api.java.functions.KeySelector;

public class CommonReviewsKeySelector implements KeySelector<CommonReviews, String>{

	String requestedKey = "";
	
	
	public CommonReviewsKeySelector(String requestedKey) {
		super();
		this.requestedKey = requestedKey;
	}


	@Override
	public String getKey(CommonReviews value) throws Exception {
		
		Field field = value.getClass().getDeclaredField(requestedKey); 
		field.setAccessible(true); 
		return field.get(value).toString();
	}
	
}