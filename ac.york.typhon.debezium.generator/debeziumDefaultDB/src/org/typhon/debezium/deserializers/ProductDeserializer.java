package org.typhon.debezium.deserializers;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.typhon.debezium.entities.ProductDML;
import org.typhon.debezium.entities.ProductDelete;
import org.typhon.debezium.entities.ProductInsert;
import org.typhon.debezium.entities.ProductUpdate;


public class ProductDeserializer {

	public ProductDML deserialize(String jsonString) throws JsonParseException, JsonMappingException, IOException {
		ObjectMapper objectMapper = new ObjectMapper();
		ProductDML ProductDMLObject = null;
		JsonNode jsonNode = objectMapper.readValue(jsonString, JsonNode.class);
		if(jsonNode.path("payload").path("op").asText().equalsIgnoreCase("c")) {
			// Insert
			ProductDMLObject = new ProductInsert();
			((ProductInsert) ProductDMLObject).setId(jsonNode.path("payload").path("after").path("Product.id").asText());
			((ProductInsert) ProductDMLObject).setName(jsonNode.path("payload").path("after").path("Product.name").asText());
			((ProductInsert) ProductDMLObject).setDescription(jsonNode.path("payload").path("after").path("Product.description").asText());
			return ProductDMLObject;
		} else if (jsonNode.path("payload").path("op").asText().equalsIgnoreCase("d")) {
			// Delete
			ProductDMLObject = new ProductDelete();
			((ProductDelete) ProductDMLObject).setId(jsonNode.path("payload").path("before").path("Product.id").asText());
			((ProductDelete) ProductDMLObject).setName(jsonNode.path("payload").path("before").path("Product.name").asText());
			((ProductDelete) ProductDMLObject).setDescription(jsonNode.path("payload").path("before").path("Product.description").asText());
		} else if (jsonNode.path("payload").path("op").asText().equalsIgnoreCase("u")) {
			// Update
			ProductDMLObject = new ProductUpdate();
			((ProductUpdate) ProductDMLObject).setIdBefore(jsonNode.path("payload").path("before").path("Product.id").asText());
			((ProductUpdate) ProductDMLObject).setIdAfter(jsonNode.path("payload").path("after").path("Product.id").asText());
			((ProductUpdate) ProductDMLObject).setNameBefore(jsonNode.path("payload").path("before").path("Product.name").asText());
			((ProductUpdate) ProductDMLObject).setNameAfter(jsonNode.path("payload").path("after").path("Product.name").asText());
			((ProductUpdate) ProductDMLObject).setDescriptionBefore(jsonNode.path("payload").path("before").path("Product.description").asText());
			((ProductUpdate) ProductDMLObject).setDescriptionAfter(jsonNode.path("payload").path("after").path("Product.description").asText());
		}
		return ProductDMLObject;
	}
}