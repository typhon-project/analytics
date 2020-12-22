package ac.york.typhon.analytics.commons.datatypes;

import java.lang.reflect.Field;

import org.apache.flink.api.java.functions.KeySelector;

public class AddressKeySelector implements KeySelector<Address, String>{

	String requestedKey = "";

	public AddressKeySelector(String requestedKey) {
		super();
		this.requestedKey = requestedKey;
	}


	@Override
	public String getKey(Address value) throws Exception {
		
		Field field = value.getClass().getDeclaredField(requestedKey); 
		field.setAccessible(true); 
		return field.get(value).toString();
	}
	
}