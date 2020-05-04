package ac.york.typhon.analytics.commons.deserialization;

import java.util.ArrayList;
import java.util.List;

import ac.york.typhon.analytics.commons.datatypes.events.Entity;
import engineering.swat.typhonql.ast.Request;
import engineering.swat.typhonql.ast.TyphonQLASTParser;


public class DeleteDeserializer implements Deserializer {

	public ArrayList<String> UUIDs = new ArrayList<String>();
	
	@Override
	public List<Entity> deserialize(String query, String invertedSelectQuery, String resultSet,
			String invertedResultSet) throws Exception {
		
		Request request = TyphonQLASTParser.parseTyphonQLRequest((query).toCharArray());
		
		SelectDeserializer sd = new SelectDeserializer();
		
		// TODO remove when this is done in the authentication chain
		ExecuteQueries eq = new ExecuteQueries();
		ExecuteQueries.Utils utils = eq.new Utils();
		Utilities util = new Utilities();
		invertedSelectQuery = util.createInvertedSelect(request);
		System.out.println(invertedSelectQuery);
		invertedResultSet = utils.executeQuery(invertedSelectQuery);
		//
		
		return sd.deserialize(invertedSelectQuery, null, invertedResultSet, null);

	}

}
