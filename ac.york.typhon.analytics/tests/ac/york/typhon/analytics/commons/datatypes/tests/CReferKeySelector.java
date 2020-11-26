package ac.york.typhon.analytics.commons.datatypes.tests;

import java.lang.reflect.Field;

import org.apache.flink.api.java.functions.KeySelector;

public class CReferKeySelector implements KeySelector<CRefer, String>{

	String requestedKey = "";
	
	
	public CReferKeySelector(String requestedKey) {
		super();
		this.requestedKey = requestedKey;
	}

	public String getKey(CRefer value) throws Exception {
		
		Field field = value.getClass().getDeclaredField(requestedKey); 
		field.setAccessible(true); 
		return field.get(value).toString();
	}
	
}