package ac.york.typhon.analytics.commons.deserialization;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import ac.york.typhon.analytics.commons.datatypes.Entity;
import ac.york.typhon.analytics.commons.deserialization.insert.DeleteDeserializer;
import ac.york.typhon.analytics.commons.deserialization.insert.InsertDeserializer;
import ac.york.typhon.analytics.commons.deserialization.insert.UpdateDeserializer;
import engineering.swat.typhonql.ast.ASTConversionException;
import engineering.swat.typhonql.ast.Request;
import engineering.swat.typhonql.ast.TyphonQLASTParser;

public class Utilities {
	
	public static void main(String[] args) throws Exception {
		
		ExecuteQueries eq = new ExecuteQueries();
		ExecuteQueries.Utils utils = eq.new Utils();
		
		String query = "insert Category {name: \"cat 1\"}";
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
//				resultSet = utils.executeUpdate(query);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Entity insertedEntity = id.deserialize(query, resultSet).get(0);
			System.out.println(insertedEntity.getUUID());
		} else if (request.hasStm() && request.getStm().isDelete()) {
			DeleteDeserializer dd = new DeleteDeserializer();
			
		} else if (request.hasStm() && request.getStm().isUpdate()) {
			UpdateDeserializer up = new UpdateDeserializer();
		}
	}
}
