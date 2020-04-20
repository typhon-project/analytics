package ac.york.typhon.analytics.commons.deserialization;

import java.util.ArrayList;

import ac.york.typhon.analytics.commons.datatypes.Entity;
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
		
//		String query = "insert Category {name: \"cat 2\"}";
//		String query = "from OrderedProduct op select op.@id, op.quantity";
//		String query = "from Category c select c.@id, c.name";
		String query = "delete Category c where c.@id == #c72af8e0-7db3-403c-ab4d-8356d2b26399";

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
			// TODO: Implement this (only query invertor I believe and maybe store UUID)
			DeleteDeserializer dd = new DeleteDeserializer();
			dd.deserialize(query, resultSet);
			
		} else if (request.hasStm() && request.getStm().isUpdate()) {
			// TODO: Implement this (re use insert code - maybee rename to upsert - but also an inverted select)
			UpdateDeserializer up = new UpdateDeserializer();
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
			if (request.hasStm() && request.getQry().getBindings().size()>1 ) {
				// TODO: This is a "join". We need to implement a "View" with slots
			} else {
				SelectDeserializer sd = new SelectDeserializer();
				sd.deserialize(query, resultSet);
			}
			
		}
	}
	
	public String createInvertedSelect(Request request) {
		Statement stm = request.getStm();
		String dmlCommand = "";
		if (request.getStm() instanceof Update || request.getStm() instanceof Delete) {
			String eid = stm.getBinding().getEntity().getString();
			String vid = stm.getBinding().getVar().getString();
			// FIXME: QL does not support * at the moment. This needs to be set to the fields of the entity.
			dmlCommand += "from " + eid + " " + vid + " select * ";

			if (stm.hasWhere()) {
				Where where = stm.getWhere();
				dmlCommand += where.yieldTree();
			}
		}
		return dmlCommand;
	}
}
