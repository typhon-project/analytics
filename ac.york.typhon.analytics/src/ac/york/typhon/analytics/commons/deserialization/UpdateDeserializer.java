package ac.york.typhon.analytics.commons.deserialization;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import ac.york.typhon.analytics.commons.datatypes.events.Entity;
import ac.york.typhon.analytics.commons.deserialization.ExecuteQueries.Utils;
import engineering.swat.typhonql.ast.ASTConversionException;
import engineering.swat.typhonql.ast.KeyVal;
import engineering.swat.typhonql.ast.Obj;
import engineering.swat.typhonql.ast.Request;
import engineering.swat.typhonql.ast.TyphonQLASTParser;

public class UpdateDeserializer implements Deserializer {

	public ArrayList<String> UUIDs = new ArrayList<String>();

	@Override
	public ArrayList<Entity> deserialize(String query, String resultSet) throws Exception {
		ExecuteQueries eq = new ExecuteQueries();
		ExecuteQueries.Utils utils = eq.new Utils();
		Utilities util = new Utilities();
		Request request = TyphonQLASTParser.parseTyphonQLRequest((query).toCharArray());
		String invertedSelect = util.createInvertedSelect(request);
		System.out.println(invertedSelect);
		SelectDeserializer sd = new SelectDeserializer();
		String invertedResultsSet = utils.executeQuery(invertedSelect);
		sd.deserialize(invertedSelect, invertedResultsSet);

		ArrayList<Entity> updatedEntities = new ArrayList<Entity>();
		updatedEntities = parseQuery(query, resultSet);
		// TODO: Get UUID
//		String deletedUUID = getUUID(resultSet);
		System.out.println(updatedEntities);
		// FIXME: This should return something
		return null;
	}

	public ArrayList<Entity> parseQuery(String query, String resultSet) throws Exception {
		ArrayList<Entity> updatedEntities = new ArrayList<Entity>();
		Request request = null;
		try {
			request = TyphonQLASTParser.parseTyphonQLRequest((query).toCharArray());
		} catch (ASTConversionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String objType = request.getStm().getBinding().getEntity().yieldTree();
		Class<?> objClass = Class.forName("ac.york.typhon.analytics.commons.datatypes." + objType);
		Entity entity = (Entity) objClass.newInstance();
		ArrayList<KeyVal> keyVals = (ArrayList<KeyVal>) request.getStm().getKeyVals();
		for (KeyVal kv : keyVals) {
			Field field = objClass.getDeclaredField(kv.getKey().yieldTree());
			field.setAccessible(true);
			Class<?> fieldTypeClass = field.getType();
			Method setter = objClass.getMethod("set" + kv.getKey().yieldTree().substring(0, 1).toUpperCase()
					+ kv.getKey().yieldTree().substring(1), fieldTypeClass);
			setter.invoke(entity, kv.getValue().yieldTree());

		}
		// FIXME: Find how UUID are given in bulk inserts. This is not working for more
		// than one insert.
//		String insertedUUID = getUUID(resultSet);
//		entity.setUUID(insertedUUID);
		updatedEntities.add(entity);
		return updatedEntities;
	}

	public String getUUID(String resultSet) throws IOException {
		// TODO: This might be a list
		String UUID = "";
		ObjectMapper objectMapper = new ObjectMapper();
		JsonNode root = objectMapper.readTree(resultSet);
		UUID = root.path("createdUuids").path("uuid").asText();
		return UUID;
	}

}
