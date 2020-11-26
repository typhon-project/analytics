package ac.york.typhon.analytics.commons.datatypes.tests;

import java.lang.reflect.Field;

import org.apache.flink.api.java.functions.KeySelector;

public class DatatypesKeySelector implements KeySelector<Datatypes, String>{

	String requestedKey = "";
	
	
	public DatatypesKeySelector(String requestedKey) {
		super();
		this.requestedKey = requestedKey;
	}

	public String getKey(Datatypes value) throws Exception {
		
		Field field = value.getClass().getDeclaredField(requestedKey); 
		field.setAccessible(true); 
		return field.get(value).toString();
	}
	
}