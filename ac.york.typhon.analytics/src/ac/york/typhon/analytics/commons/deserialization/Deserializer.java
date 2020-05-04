package ac.york.typhon.analytics.commons.deserialization;

import java.util.List;

import ac.york.typhon.analytics.commons.datatypes.events.Entity;

public interface Deserializer {

	public List<Entity> deserialize(String query, String invertedSelectQuery, String resultSet,
			String invertedResultSet) throws Exception;

}
