package ac.york.typhon.analytics.commons.deserialization;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import ac.york.typhon.analytics.commons.datatypes.Point;
import ac.york.typhon.analytics.commons.datatypes.events.Entity;
import engineering.swat.typhonql.ast.ASTConversionException;
import engineering.swat.typhonql.ast.Expr;
import engineering.swat.typhonql.ast.KeyVal;
import engineering.swat.typhonql.ast.Obj;
import engineering.swat.typhonql.ast.Request;
import engineering.swat.typhonql.ast.Segment;
import engineering.swat.typhonql.ast.TyphonQLASTParser;
import engineering.swat.typhonql.ast.XY;

public class InsertDeserializer implements Deserializer {

	// public ArrayList<String> UUIDs = new ArrayList<String>();

	@Override
	public ArrayList<Entity> deserialize(String query, String invertedSelectQuery, String resultSet,
			String invertedResultSet, Boolean resultSetNeeded, Boolean invertedResultSetNeeded) throws Exception {

		// TODO remove when this is done in the authentication chain
//		ExecuteQueries eq = new ExecuteQueries();
//		ExecuteQueries.Utils utils = eq.new Utils();
		Utilities util = new Utilities();
//		resultSet = utils.executeUpdate(query);
		//

		ArrayList<Entity> insertedEntities = new ArrayList<Entity>();
		insertedEntities = parseQuery(query, resultSet, resultSetNeeded);
		return insertedEntities;
	}

	public ArrayList<Entity> parseQuery(String query, String resultSet, boolean resultSetNeeded) throws Exception {
		ArrayList<Entity> insertedEntities = new ArrayList<Entity>();
		Request request = null;
		try {
			request = TyphonQLASTParser.parseTyphonQLRequest((query).toCharArray());
		} catch (ASTConversionException e) {
			e.printStackTrace();
		}

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

				Expr expr = kv.getValue();

				Object value = Utilities.getExprValue(expr, field);

				System.out.println(entity.getClass());
				System.out.println(value.getClass());
				System.out.println("invoking: " + entity + " : " + value);
				setter.invoke(entity, value);

			}

			if (resultSetNeeded) {
				// FIXME: Find how UUID are given in bulk inserts, if they end up being
				// supported by QL. This is not working for more than one insert.
				String insertedUUID = Utilities.getUUID(resultSet);
				entity.setUUID(insertedUUID);
			}

			insertedEntities.add(entity);
		}
		return insertedEntities;
	}

}
