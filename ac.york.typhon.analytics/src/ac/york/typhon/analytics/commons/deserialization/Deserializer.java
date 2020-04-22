package ac.york.typhon.analytics.commons.deserialization;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

import ac.york.typhon.analytics.commons.datatypes.events.Entity;

public interface Deserializer {
	
	public ArrayList<Entity> deserialize(String query, String resultSet) throws Exception;

	
}
