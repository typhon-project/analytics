package ac.york.typhon.analytics.commons.deserialization;

import java.io.IOException;

import ac.york.typhon.analytics.commons.datatypes.Entity;
import ac.york.typhon.analytics.commons.deserialization.insert.InsertDeserializer;

public class Utilities {
	
	public static void main(String[] args) throws IOException {
		InsertDeserializer id = new InsertDeserializer();
		
		ExecuteQueries eq = new ExecuteQueries();
		ExecuteQueries.Utils utils = eq.new Utils();
		
		String query = "insert Category {name: \"cat 1\"}";
		String resultSet = "";
		try {
			resultSet = utils.executeUpdate(query);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Entity insertedEntity = id.deserialize(query, resultSet).get(0);
		System.out.println(insertedEntity.getUUID());
	}
}
