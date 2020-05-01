package ac.york.typhon.analytics.commons.deserialization;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;

import ac.york.typhon.analytics.commons.datatypes.events.Entity;
import engineering.swat.typhonql.ast.ASTConversionException;
import engineering.swat.typhonql.ast.KeyVal;
import engineering.swat.typhonql.ast.Obj;
import engineering.swat.typhonql.ast.Request;
import engineering.swat.typhonql.ast.TyphonQLASTParser;

public class InsertDeserializer implements Deserializer {

	//public ArrayList<String> UUIDs = new ArrayList<String>();

	@Override
	public ArrayList<Entity> deserialize(String query, String invertedSelectQuery, String resultSet,
			String invertedResultSet) throws Exception {
		
		// FIXME: String values are returned including quotes (e.g., ""a1"" instead of "a1")
		// TODO remove when this is done in the authentication chain
		ExecuteQueries eq = new ExecuteQueries();
		ExecuteQueries.Utils utils = eq.new Utils();
		Utilities util = new Utilities();
		resultSet = utils.executeUpdate(query);
		//		
				
		ArrayList<Entity> insertedEntities = new ArrayList<Entity>();
		insertedEntities = parseQuery(query, resultSet);
		return insertedEntities;
	}

	public ArrayList<Entity> parseQuery(String query, String resultSet) throws Exception {
		ArrayList<Entity> insertedEntities = new ArrayList<Entity>();
		Request request = null;
		try {
			request = TyphonQLASTParser.parseTyphonQLRequest((query).toCharArray());
		} catch (ASTConversionException e) {
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
			// FIXME: Find how UUID are given in bulk inserts. This is not working for more than one insert.
			String insertedUUID = Utilities.getUUID(resultSet);
			entity.setUUID(insertedUUID);
			insertedEntities.add(entity);
		}
		return insertedEntities;
	}

}
