package ac.york.typhon.analytics.commons.deserialization;

import java.lang.reflect.Field;
import java.util.ArrayList;

import ac.york.typhon.analytics.commons.datatypes.events.Entity;
import engineering.swat.typhonql.ast.ASTConversionException;
import engineering.swat.typhonql.ast.Request;
import engineering.swat.typhonql.ast.Statement;
import engineering.swat.typhonql.ast.Statement.Delete;
import engineering.swat.typhonql.ast.Statement.Update;
import engineering.swat.typhonql.ast.TyphonQLASTParser;
import engineering.swat.typhonql.ast.Where;

public class Utilities {

	public static void main(String[] args) throws Exception {

		ExecuteQueries eq = new ExecuteQueries();
		ExecuteQueries.Utils utils = eq.new Utils();

		String query = "insert Category {name: \"cat 1\"}";		
//		String query = "insert Category {name: \"cat 2\"}";
//		String query = "from Category c select c.@id, c.name";
//		String query = "from Category c select c.name where c.name == \"cat 1\"";
		
//		String query = "delete Category c where c.@id == #92763cf5-cc23-4dbc-af4d-e98d2004c84e";
//		String query = "update Category c where c.@id == #93010045-fbbc-4f12-9c0b-3b1038f415f4 set {name: \"test 2\"}";
		
//		String query = "insert Product {name: \"Rustic\", category: [#c72af8e0-7db3-403c-ab4d-8356d2b26399]}";
//		String query = "from Product p select p.@id, p.category";		
		
//		String query = "insert OrderedProduct {quantity: 5, product: [#9d6eee34-66d7-4edb-9771-18fe6f7b9215]}";		
//		String query = "from OrderedProduct op select op.@id, op.quantity";
		
		String resultSet = "";
		Request request = null;
		try {
			request = TyphonQLASTParser.parseTyphonQLRequest((query).toCharArray());
		} catch (ASTConversionException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		if (request.hasStm() && request.getStm().isInsert()) {
			InsertDeserializer id = new InsertDeserializer();
			try {
				resultSet = utils.executeUpdate(query);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			ArrayList<Entity> insertedEntities = id.deserialize(query, null, resultSet, null);
			System.out.println(insertedEntities);
		} else if (request.hasStm() && request.getStm().isDelete()) {
			DeleteDeserializer dd = new DeleteDeserializer();
			// This actually creates the inverted select and deserialises it. It does do any deserialisation
			// on the delete statement, as this is not needed.
			dd.deserialize(query, null, resultSet, null);
			// Execute delete.
			utils.executeQuery(query);

		} else if (request.hasStm() && request.getStm().isUpdate()) {
			// TODO: Implement this (re use insert code - maybee rename to upsert - but also
			// an inverted select)
			UpdateDeserializer up = new UpdateDeserializer();
			up.deserialize(query, null, resultSet, null);
		} else {
			// It is a Select.
			resultSet = utils.executeQuery(query);
//			resultSet = "{\n" + 
//					"  \"columnNames\": [\n" + 
//					"    \"op.@id\",\n" + 
//					"    \"op.quantity\"\n" + 
//					"  ],\n" + 
//					"  \"values\": [\n" + 
//					"    [\n" + 
//					"      \"188e3758-6949-4bbd-b9f6-646cba02b772\",\n" + 
//					"      5\n" + 
//					"    ]\n" + 
//					"  ]\n" + 
//					"}";
			if (request.hasStm() && request.getQry().getBindings().size() > 1) {
				// TODO: This is a "join". We need to implement a "View" with slots
			} else {
				SelectDeserializer sd = new SelectDeserializer();
				sd.deserialize(query, null, resultSet, null);
			}

		}
	}

	public String createInvertedSelect(Request request) throws Exception {
		Statement stm = request.getStm();
		String dmlCommand = "";
		if (request.getStm() instanceof Update || request.getStm() instanceof Delete) {
			String eid = stm.getBinding().getEntity().getString();
			String vid = stm.getBinding().getVar().getString();
			Class<?> objClass = Class.forName("ac.york.typhon.analytics.commons.datatypes." + eid);
			Entity entity = (Entity) objClass.newInstance();
			Field[] allFields = objClass.getDeclaredFields();
			ArrayList<String> selectFieldsWithVids = new ArrayList<String>();
			for (Field field : allFields) {
				selectFieldsWithVids.add(vid + "." + field.getName());
			}
			String select = String.join(", ", selectFieldsWithVids);
			dmlCommand += "from " + eid + " " + vid + " select " + select + " ";

			if (stm.hasWhere()) {
				Where where = stm.getWhere();
				dmlCommand += where.yieldTree();
			}
		}
		return dmlCommand;
	}
}
