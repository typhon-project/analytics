package ac.york.typhon.analytics.tests.deserializer;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.databind.ObjectMapper;

import ac.york.typhon.analytics.commons.datatypes.events.Entity;
import ac.york.typhon.analytics.commons.datatypes.events.JSONQuery;
import ac.york.typhon.analytics.commons.datatypes.examples.Address;
import ac.york.typhon.analytics.commons.deserialization.DeserializationMapper;
import ac.york.typhon.analytics.commons.deserialization.InsertDeserializer;
import ac.york.typhon.analytics.commons.deserialization.SelectDeserializer;
import ac.york.typhon.analytics.commons.deserialization.UpdateDeserializer;
import ac.york.typhon.analytics.commons.deserialization.Utilities;
import engineering.swat.typhonql.ast.Request;
import engineering.swat.typhonql.ast.TyphonQLASTParser;

public class Deserilizer {

	@BeforeEach
	public void setup() {
		Entity.ENTITYPACKAGES.add("ac.york.typhon.analytics.commons.datatypes.tests");
		Entity.ENTITYPACKAGES.add("ac.york.typhon.analytics.commons.datatypes.examples");
	}

	@Test
	public void testInsertSimple() throws Exception {
		String query = "{\r\n"
				+ "\"query\" : \"insert Datatypes {s:\\\"text\\\", i: 11, b: true, bi: 111, f: 1.0, d: $2020-10-30$, dt: $2020-10-30T14:29:01Z$, p: #point(23.4 43.35), pol: #polygon((0.1 1.0, 2.0 0.0, 3.0 0.0, 0.1 1.0)), ref: #276d282b-d8ae-446c-a6c4-55d374a791fa, mref: [#276d282b-d8ae-446c-a6c4-55d374a791fa, #de8b3541-0e56-4172-8097-cdf19270764b], cref: CRefer {r: 3}}\"\r\n"
				+ "}";
		JSONQuery jsonquery = new ObjectMapper().readValue(query, JSONQuery.class);
		String resultSet = "[\r\n" + "  \"e4e9b05a-6277-4295-8557-5f1463418783\"\r\n" + "]";

		InsertDeserializer id = new InsertDeserializer(ClassLoader.getSystemClassLoader());
		List<Entity> ret = id.deserialize(jsonquery, null, resultSet, null, true, true, 0);
		System.out.println(ret);

		String result = ret.toString();
		String expected = "[Datatypes( s: text i: 11 b: true bi: 111 f: 1.0 d: $2020-10-30$ dt: $2020-10-30T14:29:01Z$ p: Point: 23.4, 43.35 pol: Polygon: [Point: 0.1, 1.0, Point: 2.0, 0.0, Point: 3.0, 0.0, Point: 0.1, 1.0] ref: Refer( r: null previousvalue: null uuid: #276d282b-d8ae-446c-a6c4-55d374a791fa) mref: [Refer( r: null previousvalue: null uuid: #276d282b-d8ae-446c-a6c4-55d374a791fa), Refer( r: null previousvalue: null uuid: #de8b3541-0e56-4172-8097-cdf19270764b)] cref: CRefer( r: null previousvalue: null uuid: null) previousvalue: null uuid: )]";
		assertEquals(expected, result);
	}

	@Test
	public void testInsertPrepared() throws Exception {
		String query = "{\r\n"
				+ "	\"query\" : \"insert Datatypes{s:??s, i: ??i, pol: #polygon((0.1 1.0, 2.0 0.0, 3.0 0.0, 0.1 1.0)), p: #point(23.4 43.35), ref: #276d282b-d8ae-446c-a6c4-55d374a791fa, dt: ??dt, d: ??d, mref: ??mref, cref: CRefer {r: 4} }\",\r\n"
				+ "	\"parameterTypes\" : [\"string\",\"int\",\"datetime\",\"date\",\"uuid\"],\r\n"
				+ "	\"parameterNames\" : [\"s\",\"i\",\"dt\",\"d\",\"mref\"],\r\n"
				+ "	\"boundRows\" : [[\"text1\",\"11\",\"2020-10-26T16:00:05.100Z\",\"2020-10-26\",\"276d282b-d8ae-446c-a6c4-55d374a791fa\"],[\"text2\",\"12\",\"2020-10-26T16:00:06Z\",\"2020-10-27\",\"de8b3541-0e56-4172-8097-cdf19270764b\"]]\r\n"
				+ "}";
		JSONQuery jsonquery = new ObjectMapper().readValue(query, JSONQuery.class);
		String resultSet = "[\r\n" + "  \"4036280e-1d4b-474e-85a3-0d42ee62b6e8\",\r\n"
				+ "  \"1db9a441-661c-46f9-8fed-c1fbfd95be63\"\r\n" + "]";

		String result = "";
		for (int i = 0; i < (jsonquery.getBoundRows() == null ? 1 : jsonquery.getBoundRows().length); i++) {
			jsonquery = new ObjectMapper().readValue(query, JSONQuery.class);
			jsonquery.setResolvedQuery(DeserializationMapper.resolveQuery(jsonquery, i));
			InsertDeserializer id = new InsertDeserializer(ClassLoader.getSystemClassLoader());
			List<Entity> ret = id.deserialize(jsonquery, null, resultSet, null, true, true, 0);
			result += ret;
		}
		System.out.println(result);
		String expected = "[Datatypes( s: text1 i: 11 b: null bi: null f: null d: $2020-10-26$ dt: $2020-10-26T16:00:05Z$ p: Point: 23.4, 43.35 pol: Polygon: [Point: 0.1, 1.0, Point: 2.0, 0.0, Point: 3.0, 0.0, Point: 0.1, 1.0] ref: Refer( r: null previousvalue: null uuid: #276d282b-d8ae-446c-a6c4-55d374a791fa) mref: [Refer( r: null previousvalue: null uuid: #276d282b-d8ae-446c-a6c4-55d374a791fa)] cref: CRefer( r: null previousvalue: null uuid: null) previousvalue: null uuid: )][Datatypes( s: text2 i: 12 b: null bi: null f: null d: $2020-10-27$ dt: $2020-10-26T16:00:06Z$ p: Point: 23.4, 43.35 pol: Polygon: [Point: 0.1, 1.0, Point: 2.0, 0.0, Point: 3.0, 0.0, Point: 0.1, 1.0] ref: Refer( r: null previousvalue: null uuid: #276d282b-d8ae-446c-a6c4-55d374a791fa) mref: [Refer( r: null previousvalue: null uuid: #de8b3541-0e56-4172-8097-cdf19270764b)] cref: CRefer( r: null previousvalue: null uuid: null) previousvalue: null uuid: )]";
		assertEquals(expected, result);
	}

	// FIXME needs fix of containments in SQL
	@Test
	public void testSelect() throws Exception {
		String query = "{\r\n" + "		\"query\" : \"from Datatypes x select x.@id, x\"\r\n" + "}\r\n" + "";
		JSONQuery jsonquery = new ObjectMapper().readValue(query, JSONQuery.class);
		Request request = TyphonQLASTParser.parseTyphonQLRequest((jsonquery.getQuery()).toCharArray());

		String result = "";
		String expected = "?";
		assertEquals(expected, result);
	}

	// FIXME needs fix of containments in SQL
	@Test
	public void testUpdate() throws Exception {
		String query = "{\r\n" + "\"query\" : \"update Datatypes x set { i: 12 }\"\r\n" + "}";
		JSONQuery jsonquery = new ObjectMapper().readValue(query, JSONQuery.class);
		Request request = TyphonQLASTParser.parseTyphonQLRequest((jsonquery.getQuery()).toCharArray());

		UpdateDeserializer ud = new UpdateDeserializer(ClassLoader.getSystemClassLoader());
		String isq = "{ \"query\" : \"from Datatypes a select a.@id, a\" }";
		JSONQuery isjq = new ObjectMapper().readValue(isq, JSONQuery.class);
		String irs = "";
		List<Entity> ret = ud.deserialize(jsonquery, isjq, null, irs, true, true, 0);
		System.out.println(ret);
		String result = "";
		String expected = "?";
		assertEquals(expected, result);
	}

	// FIXME needs fix of containments in SQL
	@Test
	public void testDelete() throws Exception {
		String query = "{\r\n" + "\"query\" : \"delete Datatypes x\"\r\n" + "}";
		JSONQuery jsonquery = new ObjectMapper().readValue(query, JSONQuery.class);
		Request request = TyphonQLASTParser.parseTyphonQLRequest((jsonquery.getQuery()).toCharArray());

		String result = "";
		String expected = "?";
		assertEquals(expected, result);
	}

	//

	@Test
	public void testInvertedSelectUpdate() throws Exception {
		String query = "{ \"query\" : \"update Address a where a.@id == #3c904a88-f416-461d-849c-390dffae5fb4 set { street: \\\"street 420\\\" }\" }";
		JSONQuery jsonquery = new ObjectMapper().readValue(query, JSONQuery.class);
		Utilities utils = new Utilities();
		Request request = null;
		request = TyphonQLASTParser.parseTyphonQLRequest((jsonquery.getQuery()).toCharArray());
		String invertedSelect = "";
		invertedSelect = utils.createInvertedSelect(request);
		String expectedInvertedSelect = "{ \"query\" : \"from Address a select a.@id, a where a.@id == #3c904a88-f416-461d-849c-390dffae5fb4\" }";
		assertEquals(expectedInvertedSelect, invertedSelect);
	}

	@Test
	public void testInvertedSelectDelete() throws Exception {
		String query = "{ \"query\" : \"delete Category c where c.@id == #92763cf5-cc23-4dbc-af4d-e98d2004c84e\" }";
		JSONQuery jsonquery = new ObjectMapper().readValue(query, JSONQuery.class);
		Utilities utils = new Utilities();
		Request request = null;
		request = TyphonQLASTParser.parseTyphonQLRequest((jsonquery.getQuery()).toCharArray());
		String invertedSelect = "";
		invertedSelect = utils.createInvertedSelect(request);
		String expectedInvertedSelect = "{ \"query\" : \"from Category c select c.@id, c where c.@id == #92763cf5-cc23-4dbc-af4d-e98d2004c84e\" }";
		assertEquals(expectedInvertedSelect, invertedSelect);
	}

	// TODO deprecated by testSelect
	@Test
	public void testDeserilisedSelectResultSetSingle() throws Exception {
		String q = "{\"query\" : \"from Address a select a.@id, a.id, a.street, a.country where a.@id == #b49d44dc-e2b8-456c-a832-3d0acc2e7ff4\"}";
		JSONQuery query = new ObjectMapper().readValue(q, JSONQuery.class);
		String resultSet = "{\n" + "  \"columnNames\": [\n" + "    \"a.@id\",\n" + "    \"a.id\",\n"
				+ "    \"a.street\",\n" + "    \"a.country\"\n" + "  ],\n" + "  \"values\": [\n" + "    [\n"
				+ "      \"b49d44dc-e2b8-456c-a832-3d0acc2e7ff4\",\n" + "      \"a1\",\n" + "      \"street 1\",\n"
				+ "      \"country 1\"\n" + "    ]\n" + "  ]\n" + "}";
		SelectDeserializer sd = new SelectDeserializer(ClassLoader.getSystemClassLoader());
		Address address = null;
		address = (Address) sd.deserialize(query, null, resultSet, null, true, null, 0).get(0);
		assertEquals("b49d44dc-e2b8-456c-a832-3d0acc2e7ff4", address.getUUID());
		assertEquals("a1", address.getId());
		assertEquals("street 1", address.getStreet());
		assertEquals("country 1", address.getCountry());
	}

	//
	@Test
	public void testDeserilisedSelectResultSetMultiple() throws Exception {
		String q = "{\"query\" : \"from Address a select a.@id, a.id, a.street, a.country\"}";
		JSONQuery query = new ObjectMapper().readValue(q, JSONQuery.class);
		String resultSet = "{\n" + "  \"columnNames\": [\n" + "    \"a.@id\",\n" + "    \"a.id\",\n"
				+ "    \"a.street\",\n" + "    \"a.country\"\n" + "  ],\n" + "  \"values\": [\n" + "    [\n"
				+ "      \"35fe9ab6-24f0-492c-98fb-feae87ca324c\",\n" + "      \"a1\",\n" + "      \"street 1\",\n"
				+ "      \"country 1\"\n" + "    ],\n" + "    [\n" + "      \"50a29db2-061a-4015-8d10-f65991d72d5b\",\n"
				+ "      \"a2\",\n" + "      \"street 2\",\n" + "      \"country 2\"\n" + "    ]\n" + "  ]\n" + "}";
		SelectDeserializer sd = new SelectDeserializer(ClassLoader.getSystemClassLoader());
		Address address1 = null;
		Address address2 = null;
		address1 = (Address) sd.deserialize(query, null, resultSet, null, true, null, 0).get(0);
		address2 = (Address) sd.deserialize(query, null, resultSet, null, true, null, 0).get(1);
		assertEquals("35fe9ab6-24f0-492c-98fb-feae87ca324c", address1.getUUID());
		assertEquals("a1", address1.getId());
		assertEquals("street 1", address1.getStreet());
		assertEquals("country 1", address1.getCountry());
		assertEquals("50a29db2-061a-4015-8d10-f65991d72d5b", address2.getUUID());
		assertEquals("a2", address2.getId());
		assertEquals("street 2", address2.getStreet());
		assertEquals("country 2", address2.getCountry());
	}

	// deprecated by testInsertSimple
	@Test
	public void testDeserilisedInsertParsingSingle() throws Exception {
		String q = "{" + "\"query\" : "
				+ "\"insert Address {id: \\\"a1\\\", street: \\\"street 1\\\", country: \\\"country 1\\\"}\"" + "}";
		JSONQuery query = new ObjectMapper().readValue(q, JSONQuery.class);
		String resultSet = "{\n" + "  \"affectedEntities\": -1,\n" + "  \"createdUuids\": {\n"
				+ "    \"uuid\": \"b49d44dc-e2b8-456c-a832-3d0acc2e7ff4\"\n" + "  }\n" + "}";
		InsertDeserializer id = new InsertDeserializer(ClassLoader.getSystemClassLoader());
		Address address = null;
		address = (Address) id.deserialize(query, null, resultSet, null, true, null, 0).get(0);
		System.out.println(address);
		assertEquals("b49d44dc-e2b8-456c-a832-3d0acc2e7ff4", address.getUUID());
		assertEquals("a1", address.getId());
		assertEquals("street 1", address.getStreet());
		assertEquals("country 1", address.getCountry());
	}

}
