package ac.york.typhon.analytics.commons.deserialization.insert;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import ac.york.typhon.analytics.commons.datatypes.Entity;
import ac.york.typhon.analytics.commons.deserialization.Deserializer;
import engineering.swat.typhonql.ast.ASTConversionException;
import engineering.swat.typhonql.ast.Request;
import engineering.swat.typhonql.ast.TyphonQLASTParser;


public class SelectDeserializer implements Deserializer {

	public ArrayList<String> UUIDs = new ArrayList<String>();
	
	@Override
	public ArrayList<Entity> deserialize(String query, String resultSet) throws Exception {
		ArrayList<String> columnNames = getColunmNames(resultSet);
		Request request = TyphonQLASTParser.parseTyphonQLRequest((query).toCharArray());
		// FIXME: This is a list, we assume one entity only.
		// FIXME: This might be a "join". Then we need to implement a "View" with slots.
		String entityName = request.getQry().getBindings().get(0).getEntity().yieldTree();
		String VId = "";
		if (request.getQry().getBindings().get(0).hasVar()) {
			VId = request.getQry().getBindings().get(0).getVar().yieldTree();
		}
		ArrayList<ArrayList<Object>> returnedValues = getValues(resultSet);
		for (ArrayList<Object> values : returnedValues) {
			System.out.println(createEntity(columnNames, values, entityName, VId));

		}
		return null;
	}

	

	public ArrayList<String> getColunmNames(String resultSet) throws IOException {
		ObjectMapper objectMapper = new ObjectMapper();
		JsonNode root = objectMapper.readTree(resultSet);
		return new ObjectMapper().convertValue(root.path("columnNames"), ArrayList.class);
	}
	
	public ArrayList<ArrayList<Object>> getValues(String resultSet) throws IOException {
		ObjectMapper objectMapper = new ObjectMapper();
		JsonNode root = objectMapper.readTree(resultSet);
		return new ObjectMapper().convertValue(root.path("values"), ArrayList.class);
	}
	
	public Entity createEntity(ArrayList<String> columnNames, ArrayList<Object> values, String entityType, String VId) throws Exception {
		Class<?> objClass = Class.forName("ac.york.typhon.analytics.commons.datatypes." + entityType);
		Entity entity = (Entity) objClass.newInstance();
		for (int i = 0; i < columnNames.size(); i++) {
			String fieldNameWithoutVId = columnNames.get(i).split(VId + ".")[1];
			// Remove @
			if (fieldNameWithoutVId.contains("@id")) {
				// FIXME: This implies that "id" field exists. It should though be UUID of the entity. Need to find a way to get super field.
				fieldNameWithoutVId = fieldNameWithoutVId.substring(1, fieldNameWithoutVId.length()); 
//				fieldNameWithoutVId = "UUID";
			}
			Field field = objClass.getDeclaredField(fieldNameWithoutVId);
			field.setAccessible(true);
			Class<?> fieldTypeClass = field.getType();
			Method setter = objClass.getMethod("set" + fieldNameWithoutVId.substring(0, 1).toUpperCase()
					+ fieldNameWithoutVId.substring(1), fieldTypeClass);
			setter.invoke(entity, values.get(i));
		}
		return entity;
	}
	
	

}
