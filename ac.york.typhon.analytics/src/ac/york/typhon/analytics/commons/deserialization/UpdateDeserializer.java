package ac.york.typhon.analytics.commons.deserialization;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import ac.york.typhon.analytics.commons.datatypes.events.Entity;
import ac.york.typhon.analytics.commons.datatypes.events.JSONQuery;
import engineering.swat.typhonql.ast.ASTConversionException;
import engineering.swat.typhonql.ast.KeyVal;
import engineering.swat.typhonql.ast.Request;
import engineering.swat.typhonql.ast.TyphonQLASTParser;

public class UpdateDeserializer implements Deserializer {

	ClassLoader engineClassLoader;

	public UpdateDeserializer(ClassLoader engineClassLoader) {
		this.engineClassLoader = engineClassLoader;
	}
	
	@Override
	public List<Entity> deserialize(JSONQuery query, JSONQuery invertedSelectQuery, String resultSet,
			String invertedResultSet, Boolean resultSetNeeded, Boolean invertedResultSetNeeded, int index) throws Exception {

		if(Utilities.debug) {
			System.out.println(query);
			System.out.println(invertedSelectQuery);
			System.out.println(resultSet);
			System.out.println(invertedResultSet);
			System.out.println(resultSetNeeded);
			System.out.println(invertedResultSetNeeded);
		}
		
		SelectDeserializer sd = new SelectDeserializer(engineClassLoader);
		List<Entity> originalEntities = sd.deserialize(invertedSelectQuery, (JSONQuery)null, invertedResultSet, null,
				invertedResultSetNeeded, null, index);

		Entity updatedEntity = parseQuery(query.getQuery());

		return createUpdatesFromOriginalEntities(originalEntities, updatedEntity);
	}

	private List<Entity> createUpdatesFromOriginalEntities(List<Entity> originalEntities, Entity updatedEntity)
			throws CloneNotSupportedException {

		ArrayList<Entity> updatedEntities = new ArrayList<>();
		//
		for (int i = 0; i < originalEntities.size(); i++) {
			Entity updated = updatedEntity.clone();
			Entity original = originalEntities.get(i);
			//
			updated.setPreviousValue(original);
			updated.setUUID(original.getUUID());
			updatedEntities.add(updated);
		}
		//
		return updatedEntities;
	}

	public Entity parseQuery(String query) throws Exception {
		Request request = null;
		try {
			request = TyphonQLASTParser.parseTyphonQLRequest((query).toCharArray());
		} catch (ASTConversionException e) {
			e.printStackTrace();
		}
		String objType = request.getStm().getBinding().getEntity().yieldTree();

		Class<?> objClass = null;
		for (String ep : Entity.ENTITYPACKAGES)
			try {
				objClass = engineClassLoader.loadClass(ep + "." + objType); //Class.forName(ep + "." + objType);
				break;
			} catch (Exception e) {
			}
		
		Entity entity = (Entity) objClass.newInstance();
		ArrayList<KeyVal> keyVals = (ArrayList<KeyVal>) request.getStm().getKeyVals();
		for (KeyVal kv : keyVals) {
			Field field = objClass.getDeclaredField(kv.getKey().yieldTree());
			field.setAccessible(true);
			Class<?> fieldTypeClass = field.getType();
			Method setter = objClass.getMethod("set" + kv.getKey().yieldTree().substring(0, 1).toUpperCase()
					+ kv.getKey().yieldTree().substring(1), fieldTypeClass);

			Object value = Utilities.getExprValue(kv.getValue(), field, engineClassLoader);

			value = Utilities.listToSingleProxy(value, fieldTypeClass);

			if(Utilities.debug)
				System.out.println("invoking: " + entity + " | " + value + " ("	+ (value != null ? value.getClass() : "(null value)") + ")");

			setter.invoke(entity, value);

		}
		return entity;
	}

}
