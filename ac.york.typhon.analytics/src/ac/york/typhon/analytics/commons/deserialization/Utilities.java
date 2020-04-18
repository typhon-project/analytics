package ac.york.typhon.analytics.commons.deserialization;

import java.util.ArrayList;

import ac.york.typhon.analytics.commons.datatypes.Entity;
import ac.york.typhon.analytics.commons.deserialization.insert.DeleteDeserializer;
import ac.york.typhon.analytics.commons.deserialization.insert.InsertDeserializer;
import ac.york.typhon.analytics.commons.deserialization.insert.SelectDeserializer;
import ac.york.typhon.analytics.commons.deserialization.insert.UpdateDeserializer;
import engineering.swat.typhonql.ast.ASTConversionException;
import engineering.swat.typhonql.ast.Request;
import engineering.swat.typhonql.ast.TyphonQLASTParser;

public class Utilities {
	
	public static void main(String[] args) throws Exception {
		
		ExecuteQueries eq = new ExecuteQueries();
		ExecuteQueries.Utils utils = eq.new Utils();
		
//		String query = "insert Category {name: \"cat 2\"}";
//		String query = "from OrderedProduct op select op.@id, op.quantity";
		String query = "from Category c select c.@id, c.name";
		
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
			ArrayList<Entity> insertedEntities = id.deserialize(query, resultSet);
			System.out.println(insertedEntities);
		} else if (request.hasStm() && request.getStm().isDelete()) {
			DeleteDeserializer dd = new DeleteDeserializer();
			
		} else if (request.hasStm() && request.getStm().isUpdate()) {
			UpdateDeserializer up = new UpdateDeserializer();
		} else {
			SelectDeserializer sd = new SelectDeserializer();
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
			sd.deserialize(query, resultSet);
		}
	}
}
