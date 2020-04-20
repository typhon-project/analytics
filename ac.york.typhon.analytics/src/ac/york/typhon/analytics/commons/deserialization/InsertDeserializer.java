package ac.york.typhon.analytics.commons.deserialization;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import ac.york.typhon.analytics.commons.datatypes.Entity;
import engineering.swat.typhonql.ast.ASTConversionException;
import engineering.swat.typhonql.ast.KeyVal;
import engineering.swat.typhonql.ast.Obj;
import engineering.swat.typhonql.ast.Request;
import engineering.swat.typhonql.ast.TyphonQLASTParser;

public class InsertDeserializer implements Deserializer {

	public ArrayList<String> UUIDs = new ArrayList<String>();

	@Override
	public ArrayList<Entity> deserialize(String query, String resultSet) throws Exception {
		ArrayList<Entity> insertedEntities = new ArrayList<Entity>();
		insertedEntities = parseQuery(query, resultSet);
		return insertedEntities;
	}

	public String getUUID(String resultSet) throws IOException {
		// TODO: This might be a list
		String UUID = "";
		ObjectMapper objectMapper = new ObjectMapper();
		JsonNode root = objectMapper.readTree(resultSet);
		UUID = root.path("createdUuids").path("uuid").asText();
		return UUID;
	}

	public ArrayList<Entity> parseQuery(String query, String resultSet) throws Exception {
		ArrayList<Entity> insertedEntities = new ArrayList<Entity>();
		Request request = null;
		try {
			request = TyphonQLASTParser.parseTyphonQLRequest((query).toCharArray());
		} catch (ASTConversionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// TODO: This might be a list
		for (Obj obj : request.getStm().getObjs()) {
			String objType = obj.getEntity().yieldTree();
			Class<?> objClass = Class.forName("ac.york.typhon.analytics.commons.datatypes." + objType);
			Entity entity = (Entity) objClass.newInstance();
			ArrayList<KeyVal> keyVals = (ArrayList<KeyVal>) request.getStm().getObjs().get(0).getKeyVals();
			for (KeyVal kv : keyVals) {
				Field field = objClass.getDeclaredField(kv.getKey().yieldTree());
				field.setAccessible(true);
				Class<?> fieldTypeClass = field.getType();
				Method setter = objClass.getMethod("set" + kv.getKey().yieldTree().substring(0, 1).toUpperCase()
						+ kv.getKey().yieldTree().substring(1), fieldTypeClass);
				setter.invoke(entity, kv.getValue().yieldTree());
				
			}
			// FIXME: Find how UUID are given in buklk inserts. This is not working for more than one insert.
			String insertedUUID = getUUID(resultSet);
			entity.setUUID(insertedUUID);
			insertedEntities.add(entity);
		}
		return insertedEntities;
	}

}
