package ac.york.typhon.analytics.tests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import ac.york.typhon.analytics.commons.datatypes.Address;
import ac.york.typhon.analytics.commons.deserialization.InsertDeserializer;
import ac.york.typhon.analytics.commons.deserialization.SelectDeserializer;
import ac.york.typhon.analytics.commons.deserialization.Utilities;
import engineering.swat.typhonql.ast.ASTConversionException;
import engineering.swat.typhonql.ast.Request;
import engineering.swat.typhonql.ast.TyphonQLASTParser;

public class Deserilizer {
	
	@Test
	public void testInvertedSelectUpdate() {
		String query = "update Address a where a.@id == #3c904a88-f416-461d-849c-390dffae5fb4 set { street: \"street 420\" }";		
		Utilities utils = new Utilities();
		Request request = null;
		try {
			request = TyphonQLASTParser.parseTyphonQLRequest((query).toCharArray());
		} catch (ASTConversionException e1) {
			e1.printStackTrace();
		}
		String invertedSelect = "";
		try {
			invertedSelect = utils.createInvertedSelect(request);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String expectedInvertedSelect = "from Address a select a.@id, a.id, a.street, a.country where a.@id == #3c904a88-f416-461d-849c-390dffae5fb4";
        assertEquals(expectedInvertedSelect, invertedSelect);
	}
	
	@Test
	public void testInvertedSelectDelete() {
		String query = "delete Category c where c.@id == #92763cf5-cc23-4dbc-af4d-e98d2004c84e";		
		Utilities utils = new Utilities();
		Request request = null;
		try {
			request = TyphonQLASTParser.parseTyphonQLRequest((query).toCharArray());
		} catch (ASTConversionException e1) {
			e1.printStackTrace();
		}
		String invertedSelect = "";
		try {
			invertedSelect = utils.createInvertedSelect(request);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String expectedInvertedSelect = "from Category c select c.@id, c.id, c.name where c.@id == #92763cf5-cc23-4dbc-af4d-e98d2004c84e";
        assertEquals(expectedInvertedSelect, invertedSelect);
	}
	
	@Test
	public void testDeserilisedSelectResultSetSingle() {
		String query = "from Address a select a.@id, a.id, a.street, a.country where a.@id == #b49d44dc-e2b8-456c-a832-3d0acc2e7ff4";
		String resultSet = "{\n" + 
				"  \"columnNames\": [\n" + 
				"    \"a.@id\",\n" + 
				"    \"a.id\",\n" + 
				"    \"a.street\",\n" + 
				"    \"a.country\"\n" + 
				"  ],\n" + 
				"  \"values\": [\n" + 
				"    [\n" + 
				"      \"b49d44dc-e2b8-456c-a832-3d0acc2e7ff4\",\n" + 
				"      \"a1\",\n" + 
				"      \"street 1\",\n" + 
				"      \"country 1\"\n" + 
				"    ]\n" + 
				"  ]\n" + 
				"}";
		SelectDeserializer sd = new SelectDeserializer();
		Address address = null;
		try {
			address = (Address) sd.deserialize(query, null, resultSet, null).get(0);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertEquals("b49d44dc-e2b8-456c-a832-3d0acc2e7ff4", address.getUUID());
        assertEquals("a1", address.getId());
        assertEquals("street 1", address.getStreet());
        assertEquals("country 1", address.getCountry());
	}
	
	@Test
	public void testDeserilisedSelectResultSetMultiple() {
		String query = "from Address a select a.@id, a.id, a.street, a.country";
		String resultSet = "{\n" + 
				"  \"columnNames\": [\n" + 
				"    \"a.@id\",\n" + 
				"    \"a.id\",\n" + 
				"    \"a.street\",\n" + 
				"    \"a.country\"\n" + 
				"  ],\n" + 
				"  \"values\": [\n" + 
				"    [\n" + 
				"      \"35fe9ab6-24f0-492c-98fb-feae87ca324c\",\n" + 
				"      \"a1\",\n" + 
				"      \"street 1\",\n" + 
				"      \"country 1\"\n" + 
				"    ],\n" + 
				"    [\n" + 
				"      \"50a29db2-061a-4015-8d10-f65991d72d5b\",\n" + 
				"      \"a2\",\n" + 
				"      \"street 2\",\n" + 
				"      \"country 2\"\n" + 
				"    ]\n" + 
				"  ]\n" + 
				"}";
		SelectDeserializer sd = new SelectDeserializer();
		Address address1 = null;
		Address address2 = null;
		try {
			address1 = (Address) sd.deserialize(query, null, resultSet, null).get(0);
			address2 = (Address) sd.deserialize(query, null, resultSet, null).get(1);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertEquals("35fe9ab6-24f0-492c-98fb-feae87ca324c", address1.getUUID());
        assertEquals("a1", address1.getId());
        assertEquals("street 1", address1.getStreet());
        assertEquals("country 1", address1.getCountry());
		assertEquals("50a29db2-061a-4015-8d10-f65991d72d5b", address2.getUUID());
		assertEquals("a2", address2.getId());
	    assertEquals("street 2", address2.getStreet());
	    assertEquals("country 2", address2.getCountry());
	}
	
	
	// This test will fail until TYPHON integrates deserilizer as in the current implementation we query the DB
	@Test
	public void testDeserilisedInsertParsingSingle() {
		String query = "insert Address {id: \"a1\", street: \"street 1\", country: \"country 1\"}";
		String resultSet = "{\n" + 
				"  \"affectedEntities\": -1,\n" + 
				"  \"createdUuids\": {\n" + 
				"    \"uuid\": \"b49d44dc-e2b8-456c-a832-3d0acc2e7ff4\"\n" + 
				"  }\n" + 
				"}";
		InsertDeserializer id = new InsertDeserializer();	
		Address address = null;
		try {
			address = (Address) id.deserialize(query, null, resultSet, null).get(0);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        assertEquals("b49d44dc-e2b8-456c-a832-3d0acc2e7ff4", address.getUUID());
        assertEquals("a1", address.getId());
        assertEquals("street 1", address.getStreet());
        assertEquals("country 1", address.getCountry());
	}
	
}
