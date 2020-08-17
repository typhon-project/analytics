package ac.york.typhon.analytics.commons.deserialization;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import ac.york.typhon.analytics.commons.datatypes.events.Entity;
import engineering.swat.typhonql.ast.ASTConversionException;
import engineering.swat.typhonql.ast.KeyVal;
import engineering.swat.typhonql.ast.Request;
import engineering.swat.typhonql.ast.TyphonQLASTParser;

public class UpdateDeserializer implements Deserializer {

	// public ArrayList<String> UUIDs = new ArrayList<String>();

	@Override
	public List<Entity> deserialize(String query, String invertedSelectQuery, String resultSet,
			String invertedResultSet, Boolean resultSetNeeded, Boolean invertedResultSetNeeded) throws Exception {

		SelectDeserializer sd = new SelectDeserializer();
		List<Entity> originalEntities = sd.deserialize(invertedSelectQuery, null, invertedResultSet, null,
				invertedResultSetNeeded, null);

		List<Entity> updatedEntities = new ArrayList<Entity>();
		updatedEntities = parseQuery(query);

		return createUpdatesFromOriginalEntities(originalEntities, updatedEntities);
	}

	private List<Entity> createUpdatesFromOriginalEntities(List<Entity> originalEntities,
			List<Entity> updatedEntities) {
		//
		for (int i = 0; i < originalEntities.size(); i++) {
			Entity original = originalEntities.get(i);
			Entity updated = updatedEntities.get(i);
			updated.setPreviousValue(original);
			updated.setUUID(original.getUUID());
		}
		//
		return updatedEntities;
	}

	public ArrayList<Entity> parseQuery(String query) throws Exception {
		ArrayList<Entity> updatedEntities = new ArrayList<Entity>();
		Request request = null;
		try {
			request = TyphonQLASTParser.parseTyphonQLRequest((query).toCharArray());
		} catch (ASTConversionException e) {
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

			Object value = Utilities.getExprValue(kv.getValue(), field);

			setter.invoke(entity, value);

		}
		updatedEntities.add(entity);
		return updatedEntities;
	}

}
