package ac.york.typhon.analytics.commons.deserialization;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;

import ac.york.typhon.analytics.commons.datatypes.events.Entity;
import ac.york.typhon.analytics.commons.datatypes.events.JSONQuery;
import engineering.swat.typhonql.ast.ASTConversionException;
import engineering.swat.typhonql.ast.Expr;
import engineering.swat.typhonql.ast.KeyVal;
import engineering.swat.typhonql.ast.Obj;
import engineering.swat.typhonql.ast.Request;
import engineering.swat.typhonql.ast.TyphonQLASTParser;

public class InsertDeserializer implements Deserializer {

	ClassLoader engineClassLoader;

	public InsertDeserializer(ClassLoader engineClassLoader) {
		this.engineClassLoader = engineClassLoader;
	}

	@Override
	public ArrayList<Entity> deserialize(JSONQuery query, JSONQuery invertedSelectQuery, String resultSet,
			String invertedResultSet, Boolean resultSetNeeded, Boolean invertedResultSetNeeded, int index)
			throws Exception {
		ArrayList<Entity> insertedEntities = new ArrayList<Entity>();
		insertedEntities = parseQuery(query, resultSet, resultSetNeeded, index);
		return insertedEntities;
	}

	public ArrayList<Entity> parseQuery(JSONQuery query, String resultSet, boolean resultSetNeeded, int index)
			throws Exception {
		ArrayList<Entity> insertedEntities = new ArrayList<Entity>();
		Request request = null;
		try {
			// System.out.println(query.getQuery());
			// System.out.println(query.getResolvedQuery());
			request = TyphonQLASTParser.parseTyphonQLRequest(
					(query.getResolvedQuery() != null ? query.getResolvedQuery() : query.getQuery()).toCharArray());
		} catch (ASTConversionException e) {
			e.printStackTrace();
		}

		for (Obj obj : request.getStm().getObjs()) {
			String objType = obj.getEntity().yieldTree();

			Class<?> objClass = null;
			for (String ep : Entity.ENTITYPACKAGES)
				try {
					objClass = engineClassLoader.loadClass(ep + "." + objType); // Class.forName(ep + "." + objType);
					break;
				} catch (Exception e) {
				}

			Entity entity = (Entity) objClass.newInstance();
			ArrayList<KeyVal> keyVals = (ArrayList<KeyVal>) request.getStm().getObjs().get(0).getKeyVals();
			for (KeyVal kv : keyVals) {

				// set uuid if it is manually set
				if (!kv.hasKey())
					entity.setUUID(kv.getValue().yieldTree());
				// for all other fields
				else {

					Field field = objClass.getDeclaredField(kv.getKey().yieldTree());
					field.setAccessible(true);
					Class<?> fieldTypeClass = field.getType();
					Method setter = objClass.getMethod("set" + kv.getKey().yieldTree().substring(0, 1).toUpperCase()
							+ kv.getKey().yieldTree().substring(1), fieldTypeClass);

					Expr expr = kv.getValue();

					Object value = Utilities.getExprValue(expr, field, engineClassLoader);

					// System.out.println(query.getResolvedQuery());

					// System.out.println(expr + " ::: " + field.getName()+ " ::: " +
					// entity.getClass());
					// System.out.println(value.getClass());
					//
					value = Utilities.listToSingleProxy(value, fieldTypeClass);
					//
					if (Utilities.debug)
						System.out.println("invoking: " + entity + " | " + value + " ("
								+ (value != null ? value.getClass() : "(null value)") + ")");
					//
					setter.invoke(entity, value);
				}
			}

			if (resultSetNeeded) {
				String insertedUUID = getUUID(resultSet, index);
				entity.setUUID(insertedUUID);
			}

			insertedEntities.add(entity);
		}
		return insertedEntities;
	}

	private String getUUID(String resultSet, int index) {
		resultSet = resultSet.replaceAll("\\s+","");
		String[] uuids = resultSet.replace("[", "").replace("]", "").split(",");
		return uuids[index].replace("\"", "");
	}

}
