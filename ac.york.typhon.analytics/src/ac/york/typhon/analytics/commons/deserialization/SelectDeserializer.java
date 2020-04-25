package ac.york.typhon.analytics.commons.deserialization;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import ac.york.typhon.analytics.commons.datatypes.events.Entity;
import engineering.swat.typhonql.ast.Request;
import engineering.swat.typhonql.ast.TyphonQLASTParser;

public class SelectDeserializer implements Deserializer {

	public ArrayList<String> UUIDs = new ArrayList<String>();

	@Override
	public ArrayList<Entity> deserialize(String query, String invertedSelectQuery, String resultSet,
			String invertedResultSet) throws Exception {
		
		// TODO remove when this is done in the authentication chain
		ExecuteQueries eq = new ExecuteQueries();
		ExecuteQueries.Utils utils = eq.new Utils();
		Utilities util = new Utilities();
		if(resultSet == null)
			resultSet = utils.executeQuery(query);
		//		
		
		ArrayList<String> columnNames = getColunmNames(resultSet);
		Request request = TyphonQLASTParser.parseTyphonQLRequest((query).toCharArray());
		// XXX: This is a list, we assume one entity only.
		String entityName = request.getQry().getBindings().get(0).getEntity().yieldTree();
		String VId = "";
		if (request.getQry().getBindings().get(0).hasVar()) {
			VId = request.getQry().getBindings().get(0).getVar().yieldTree();
		}
		ArrayList<ArrayList<Object>> returnedValues = getValues(resultSet);

		ArrayList<Entity> ret = new ArrayList<Entity>();
		for (ArrayList<Object> values : returnedValues) {
			Entity e = createEntity(columnNames, values, entityName, VId);
			//System.out.println(e);
			ret.add(e);
		}
		return ret;
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

	public Entity createEntity(ArrayList<String> columnNames, ArrayList<Object> values, String entityType, String VId)
			throws Exception {
		Class<?> objClass = Class.forName("ac.york.typhon.analytics.commons.datatypes." + entityType);
		Entity entity = (Entity) objClass.newInstance();

		for (int i = 0; i < columnNames.size(); i++) {

			String vidWithDot = VId + "\\.";
			String[] split = columnNames.get(i).split(vidWithDot);
			//System.out.println(Arrays.toString(split));
			String fieldNameWithoutVId = split[1];

			Field field;
			if (fieldNameWithoutVId.contains("@id")) {
				fieldNameWithoutVId = "UUID";
				field = objClass.getSuperclass().getDeclaredField(fieldNameWithoutVId);
			} else {
				//System.out.println("<"+fieldNameWithoutVId);
				field = objClass.getDeclaredField(fieldNameWithoutVId);
			}

			field.setAccessible(true);
			Class<?> fieldTypeClass = field.getType();
			Method setter = objClass.getMethod(
					"set" + fieldNameWithoutVId.substring(0, 1).toUpperCase() + fieldNameWithoutVId.substring(1),
					fieldTypeClass);
			setter.invoke(entity, values.get(i));

		}
		return entity;
	}

}
