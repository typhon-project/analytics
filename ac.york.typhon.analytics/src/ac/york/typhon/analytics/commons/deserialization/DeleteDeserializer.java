package ac.york.typhon.analytics.commons.deserialization;

import java.io.IOException;
import java.util.ArrayList;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import ac.york.typhon.analytics.commons.datatypes.Entity;
import engineering.swat.typhonql.ast.ASTConversionException;
import engineering.swat.typhonql.ast.Request;
import engineering.swat.typhonql.ast.TyphonQLASTParser;


public class DeleteDeserializer implements Deserializer {

	public ArrayList<String> UUIDs = new ArrayList<String>();
	
	@Override
	public ArrayList<Entity> deserialize(String query, String resultSet) throws Exception {
		Utilities util = new Utilities();
		Request request = TyphonQLASTParser.parseTyphonQLRequest((query).toCharArray());
		String invertedSelect = util.createInvertedSelect(request);
		System.out.println(invertedSelect);
//		String deletedUUID = getUUID(resultSet);
		return null;
	}

	

	public String getUUID(String resultSet) throws IOException {
		//TODO: This might be a list
		String UUID = "";
		ObjectMapper objectMapper = new ObjectMapper();
		JsonNode root = objectMapper.readTree(resultSet);
		UUID = root.path("createdUuids").path("uuid").asText();
		return UUID;
	}

}
