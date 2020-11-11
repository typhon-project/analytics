package ac.york.typhon.analytics.commons.deserialization;

import java.util.ArrayList;
import java.util.List;

import ac.york.typhon.analytics.commons.datatypes.events.Entity;
import ac.york.typhon.analytics.commons.datatypes.events.JSONQuery;
import engineering.swat.typhonql.ast.Request;
import engineering.swat.typhonql.ast.TyphonQLASTParser;

public class DeleteDeserializer implements Deserializer {

	public ArrayList<String> UUIDs = new ArrayList<String>();

	ClassLoader engineClassLoader;

	public DeleteDeserializer(ClassLoader engineClassLoader) {
		this.engineClassLoader = engineClassLoader;
	}
	
	@Override
	public List<Entity> deserialize(JSONQuery query, JSONQuery invertedSelectQuery, String resultSet,
			String invertedResultSet, Boolean resultSetNeeded, Boolean invertedResultSetNeeded) throws Exception {

		//Request request = TyphonQLASTParser.parseTyphonQLRequest((query.getQuery()).toCharArray());

		SelectDeserializer sd = new SelectDeserializer(engineClassLoader);
		return sd.deserialize(invertedSelectQuery, null, invertedResultSet, null, invertedResultSetNeeded, null);

	}

}
