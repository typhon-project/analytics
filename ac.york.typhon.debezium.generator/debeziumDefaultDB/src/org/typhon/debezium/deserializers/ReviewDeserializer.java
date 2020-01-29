package org.typhon.debezium.deserializers;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.typhon.debezium.entities.ReviewDML;
import org.typhon.debezium.entities.ReviewDelete;
import org.typhon.debezium.entities.ReviewInsert;
import org.typhon.debezium.entities.ReviewUpdate;


public class ReviewDeserializer {

	public ReviewDML deserialize(String jsonString) throws JsonParseException, JsonMappingException, IOException {
		ObjectMapper objectMapper = new ObjectMapper();
		ReviewDML ReviewDMLObject = null;
		JsonNode jsonNode = objectMapper.readValue(jsonString, JsonNode.class);
		if(jsonNode.path("payload").path("op").asText().equalsIgnoreCase("c")) {
			// Insert
			ReviewDMLObject = new ReviewInsert();
			((ReviewInsert) ReviewDMLObject).setId(jsonNode.path("payload").path("after").path("Review.id").asText());
			((ReviewInsert) ReviewDMLObject).setDescription(jsonNode.path("payload").path("after").path("Review.description").asText());
			return ReviewDMLObject;
		} else if (jsonNode.path("payload").path("op").asText().equalsIgnoreCase("d")) {
			// Delete
			ReviewDMLObject = new ReviewDelete();
			((ReviewDelete) ReviewDMLObject).setId(jsonNode.path("payload").path("before").path("Review.id").asText());
			((ReviewDelete) ReviewDMLObject).setDescription(jsonNode.path("payload").path("before").path("Review.description").asText());
		} else if (jsonNode.path("payload").path("op").asText().equalsIgnoreCase("u")) {
			// Update
			ReviewDMLObject = new ReviewUpdate();
			((ReviewUpdate) ReviewDMLObject).setIdBefore(jsonNode.path("payload").path("before").path("Review.id").asText());
			((ReviewUpdate) ReviewDMLObject).setIdAfter(jsonNode.path("payload").path("after").path("Review.id").asText());
			((ReviewUpdate) ReviewDMLObject).setDescriptionBefore(jsonNode.path("payload").path("before").path("Review.description").asText());
			((ReviewUpdate) ReviewDMLObject).setDescriptionAfter(jsonNode.path("payload").path("after").path("Review.description").asText());
		}
		return ReviewDMLObject;
	}
}