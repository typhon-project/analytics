package ac.york.typhon.analytics.commons.deserialization.insert;

import java.io.IOException;
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
		String entityName = request.getQry().getBindings().get(0).getEntity().yieldTree();
		ArrayList<ArrayList<Object>> returnedValues = getValues(resultSet);
		for (ArrayList<Object> values : returnedValues) {
			createEntity(columnNames, values, entityName);

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
	
	public void createEntity(ArrayList<String> columnNames, ArrayList<Object> values, String entityType) throws Exception {
		Class<?> objClass = Class.forName("ac.york.typhon.analytics.commons.datatypes." + entityType);
		Entity entity = (Entity) objClass.newInstance();
		HashMap<String, Object> columnNamesToValuesMap = new HashMap<String, Object>();
		for (int i = 0; i < columnNames.size(); i++) {
			System.out.println(values.get(i));
			columnNamesToValuesMap.put(columnNames.get(i), values.get(i));
		}
		System.out.println(columnNamesToValuesMap);
		System.out.println("----");
	}
	
	

}
