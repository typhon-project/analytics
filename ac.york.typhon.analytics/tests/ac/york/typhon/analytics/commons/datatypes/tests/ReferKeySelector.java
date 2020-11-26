package ac.york.typhon.analytics.commons.datatypes.tests;

import java.lang.reflect.Field;

import org.apache.flink.api.java.functions.KeySelector;

public class ReferKeySelector implements KeySelector<Refer, String>{

	String requestedKey = "";
	
	
	public ReferKeySelector(String requestedKey) {
		super();
		this.requestedKey = requestedKey;
	}

	public String getKey(Refer value) throws Exception {
		
		Field field = value.getClass().getDeclaredField(requestedKey); 
		field.setAccessible(true); 
		return field.get(value).toString();
	}
	
}