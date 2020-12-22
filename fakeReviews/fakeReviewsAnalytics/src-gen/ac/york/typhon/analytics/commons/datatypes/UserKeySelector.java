package ac.york.typhon.analytics.commons.datatypes;

import java.lang.reflect.Field;

import org.apache.flink.api.java.functions.KeySelector;

public class UserKeySelector implements KeySelector<User, String>{

	String requestedKey = "";
	
	
	public UserKeySelector(String requestedKey) {
		super();
		this.requestedKey = requestedKey;
	}


	@Override
	public String getKey(User value) throws Exception {
		
		Field field = value.getClass().getDeclaredField(requestedKey); 
		field.setAccessible(true); 
		return field.get(value).toString();
	}
	
}