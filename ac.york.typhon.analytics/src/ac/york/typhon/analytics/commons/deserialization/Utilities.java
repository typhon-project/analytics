package ac.york.typhon.analytics.commons.deserialization;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import ac.york.typhon.analytics.commons.datatypes.Point;
import ac.york.typhon.analytics.commons.datatypes.Polygon;
import ac.york.typhon.analytics.commons.datatypes.events.Entity;
import engineering.swat.typhonql.ast.Expr;
import engineering.swat.typhonql.ast.PlaceHolderOrUUID;
import engineering.swat.typhonql.ast.Request;
import engineering.swat.typhonql.ast.Segment;
import engineering.swat.typhonql.ast.Statement;
import engineering.swat.typhonql.ast.Statement.Delete;
import engineering.swat.typhonql.ast.Statement.Update;
import engineering.swat.typhonql.ast.Where;
import engineering.swat.typhonql.ast.XY;

public class Utilities {

	// XXX needs update to jsonquery to be usable
	/*
	 * public static void main(String[] args) throws Exception {
	 * 
	 * ExecuteQueries eq = new ExecuteQueries(); ExecuteQueries.Utils utils = eq.new
	 * Utils();
	 * 
	 * // String query =
	 * "update Address a where a.@id == #3c904a88-f416-461d-849c-390dffae5fb4 set { street: \"street 420\" }"
	 * ; // String query =
	 * "insert Category {name: \"cat 1\"}, Category {name: \"cat 2\"}"; // String
	 * query = "from Category c select c.@id, c.name"; // String query =
	 * "from Category c select c.name where c.name == \"cat 1\""; String query =
	 * "from Product p select p.@id, p.id, p.name, p.description, p.category where p.@id == #84e73cf1-dbb4-4b0c-8120-cdbe5c83634a"
	 * ;
	 * 
	 * // String query =
	 * "delete Category c where c.@id == #92763cf5-cc23-4dbc-af4d-e98d2004c84e"; //
	 * String query =
	 * "update Category c where c.@id == #93010045-fbbc-4f12-9c0b-3b1038f415f4 set {name: \"test 2\"}"
	 * ;
	 * 
	 * // String query =
	 * "insert Product {name: \"Rustic\", category: [#c72af8e0-7db3-403c-ab4d-8356d2b26399]}"
	 * ; // String query = "from Product p select p.@id, p.category";
	 * 
	 * // String query =
	 * "insert OrderedProduct {quantity: 5, product: [#9d6eee34-66d7-4edb-9771-18fe6f7b9215]}"
	 * ; // String query = "from OrderedProduct op select op.@id, op.quantity";
	 * 
	 * String resultSet = ""; Request request = null; try { request =
	 * TyphonQLASTParser.parseTyphonQLRequest((query).toCharArray()); } catch
	 * (ASTConversionException e1) { e1.printStackTrace(); }
	 * 
	 * if (request.hasStm() && request.getStm().isInsert()) { InsertDeserializer id
	 * = new InsertDeserializer(); try { resultSet = utils.executeUpdate(query); }
	 * catch (Exception e) { e.printStackTrace(); } ArrayList<Entity>
	 * insertedEntities = id.deserialize(new JSONQuery(), null, resultSet, null,
	 * true, true); System.out.println(insertedEntities); } else if
	 * (request.hasStm() && request.getStm().isDelete()) { DeleteDeserializer dd =
	 * new DeleteDeserializer(); // This actually creates the inverted select and
	 * deserialises it. It does do any // deserialisation // on the delete
	 * statement, as this is not needed. dd.deserialize(new JSONQuery(), null,
	 * resultSet, null, true, true); // Execute delete. utils.executeQuery(query);
	 * 
	 * } else if (request.hasStm() && request.getStm().isUpdate()) { // Implement
	 * this (re use insert code - maybee rename to upsert - but also // an inverted
	 * select) UpdateDeserializer up = new UpdateDeserializer();
	 * System.out.println(up.deserialize(query, null, resultSet, null, true, true));
	 * } else { // It is a Select. resultSet = utils.executeQuery(query); //
	 * resultSet = "{\n" + // "  \"columnNames\": [\n" + // "    \"op.@id\",\n" + //
	 * "    \"op.quantity\"\n" + // "  ],\n" + // "  \"values\": [\n" + // "    [\n"
	 * + // "      \"188e3758-6949-4bbd-b9f6-646cba02b772\",\n" + // "      5\n" +
	 * // "    ]\n" + // "  ]\n" + // "}"; if (request.hasStm() &&
	 * request.getQry().getBindings().size() > 1) { // This is a "join". We need to
	 * implement a "View" with slots -- done for impl } else { SelectDeserializer sd
	 * = new SelectDeserializer(); sd.deserialize(query, null, resultSet, null,
	 * true, true); }
	 * 
	 * } }
	 */

	public static boolean debug = false;

	public String createInvertedSelect(Request request) throws Exception {
		String dmlCommand = "";
		if (request.hasStm() && (request.getStm() instanceof Update || request.getStm() instanceof Delete)) {
			Statement stm = request.getStm();
			String eid = stm.getBinding().getEntity().getString();
			String vid = stm.getBinding().getVar().getString();

			Class<?> objClass = null;
			for (String ep : Entity.ENTITYPACKAGES)
				try {
					objClass = Class.forName(ep + "." + eid);
					break;
				} catch (Exception e) {
				}

			Entity entity = (Entity) objClass.newInstance();
			Field[] allFields = objClass.getDeclaredFields();
			ArrayList<String> selectFieldsWithVids = new ArrayList<String>();
			for (Field field : allFields) {
				selectFieldsWithVids.add(vid + "." + field.getName());
			}
			String select = vid + ".@id, " + String.join(", ", selectFieldsWithVids);
			dmlCommand += "from " + eid + " " + vid + " select " + select + " ";

			if (stm.hasWhere()) {
				Where where = stm.getWhere();
				dmlCommand += where.yieldTree();
			}
		}
		return dmlCommand;
	}

	public static String getUUID(String resultSet) throws IOException {
		// TODO: This might be a list if bulk inserts are possible
		String UUID = "";
		ObjectMapper objectMapper = new ObjectMapper();
		JsonNode root = objectMapper.readTree(resultSet);

		UUID = null;
		try {
			UUID = root.path("createdUuids").path("uuid").asText();
		} catch (Exception e) {
			// this call did not have createdUuids set!
		}
		return UUID;
	}

	public static Object getExprValue(Expr expr, Field field) throws Exception {
		Object value = null;
		if (expr.hasBlobPointerValue()) {
			value = expr.getBlobPointerValue().getString();
		} else if (expr.hasBoolValue())
			value = Boolean.parseBoolean(expr.getBoolValue().yieldTree());
		else if (expr.hasCustomValue()) {
			value = expr.getCustomValue();
			// TODO need to somehow get custom types defined in ml
			System.out.println(expr.getCustomValue().yieldTree());
			throw new UnsupportedOperationException("Deserializer does not support CustomValue");
		} else if (expr.hasDtValue()) {
			if (expr.getDtValue().hasDate())
				value = expr.getDtValue().getDate().getString();
			else if (expr.getDtValue().hasDateTime())
				value = expr.getDtValue().getDateTime().getString();
//			SimpleDateFormat sdf = new SimpleDateFormat();
//			Date date = sdf.parse(value.toString());
//			value = date;
		} else if (expr.hasIntValue())
			value = Integer.parseInt(expr.getIntValue().getString());
		else if (expr.hasObjValue()) {
			value = expr.getObjValue();
			// TODO unsupported by ql so far
			System.out.println(expr.getObjValue().yieldTree());
			throw new UnsupportedOperationException("Deserializer does not support ObjValue");
		} else if (expr.hasPointValue()) {
			engineering.swat.typhonql.ast.Point point = expr.getPointValue();
			XY co = point.getCoordinate();
			Point p = new Point(Double.parseDouble(co.getX().yieldTree()), Double.parseDouble(co.getY().yieldTree()));
			// System.out.println(p);
			value = p;
		} else if (expr.hasPolygonValue()) {

			engineering.swat.typhonql.ast.Polygon poly = expr.getPolygonValue();
			List<Segment> segments = poly.getSegments();

			Polygon p = new Polygon();

			for (Segment s : segments) {
				for (XY co : s.getPoints()) {
					Point po = new Point(Double.parseDouble(co.getX().yieldTree()),
							Double.parseDouble(co.getY().yieldTree()));
					p.addPoint(po);
				}
			}

			value = p;

		} else if (expr.hasRealValue())
			value = Double.parseDouble(expr.getRealValue().getString());
		else if (expr.hasStrValue()) {
			String svalue = expr.getStrValue().getString();
			// remove initial and trailing quotes as they are kept from the new json parsing
			if (svalue.startsWith("\""))
				;
			svalue = svalue.substring(1);
			if (svalue.endsWith("\""))
				;
			svalue = svalue.substring(0, svalue.length() - 1);
			//
			value = svalue;
		} else if (expr.hasUuidValue()) {

			String valueString = expr.getUuidValue().getString();
			if(Utilities.debug)
				System.out.println(valueString);

			//

			Entity proxy = (Entity) field.getType().newInstance();
			proxy.setProxy(true);
			proxy.setUUID(valueString);

			//

			value = proxy;
		} else if (expr.hasRefs()) {
			// multi valued refs
			List<PlaceHolderOrUUID> refIds = expr.getRefs();
			ArrayList<Entity> proxies = new ArrayList<Entity>();
			for (PlaceHolderOrUUID valueString : refIds) {
				Entity proxy = (Entity) ((Class<?>) ((ParameterizedType) field.getGenericType())
						.getActualTypeArguments()[0]).newInstance();
				proxy.setProxy(true);
				proxy.setUUID(valueString.getString());
				proxies.add(proxy);
			}
			value = proxies;
		} else if (expr.hasPh()) {
			value = expr.getPh();
		} else {
			// System.out.println(expr.hasRefs());
			System.out.println(expr);
			throw new UnsupportedOperationException("Deserializer does not support this type of value");
		} //
		return value;
	}

	private static final String[] patterns = { "yyyy-MM-dd", "yyyy-MM-dd'T'HH:mm:ss'Z'" };

	private static boolean isDateOrDateTime(String s) {
		for (String pattern : patterns) {
			try {
				// System.out.println("parsing: " + s + " with: " + pattern);
				new SimpleDateFormat(pattern).parse(s);
				return true;
			} catch (ParseException e) {
			}
		}
		return false;
	}

	public static Object getExprValue(String value) {
		Object rvalue = value;
		// TODO: converts the json values into objects where possible
		if (value.startsWith("TODO CUSTOM")) {
			// value = expr.getCustomValue();
			// TODO need to somehow get custom types defined in ml
			// System.out.println(expr.getCustomValue().yieldTree());
			throw new UnsupportedOperationException("Deserializer does not support CustomValue");
		} else if (value.startsWith("TODO OBJ")) {
			// value = expr.getObjValue();
			// TODO unsupported by ql so far
			// System.out.println(expr.getObjValue().yieldTree());
			throw new UnsupportedOperationException("Deserializer does not support ObjValue");
		} else if (isDateOrDateTime(value)) {
			// return as is for date and datetime
		} else if (value.startsWith("POINT (")) {
			int i = value.indexOf("POINT (") + 7;
			int j = value.lastIndexOf(" ");
			int k = value.indexOf(")");
			// System.out.println(i + " " + j + " " + k);
			String x = value.substring(i, j);
			String y = value.substring(j, k);
			Point p = new Point(Double.parseDouble(x), Double.parseDouble(y));
			// System.out.println(">POINT: " + p);
			rvalue = p;
		} else if (value.startsWith("POLYGON (")) {
			// System.out.println(">polygon found: " + value);

			int i = value.indexOf("POLYGON (") + 9;
			int j = value.lastIndexOf(")");

			String segmentString = value.substring(i, j);

			// System.out.println(segmentString);

			String[] segments;
			try {
				// TOOD: test multiple segments
				segments = segmentString.split(")(");
			} catch (Exception e) {
				segments = new String[1];
				segments[0] = segmentString.replace("(", "").replace(")", "");
			}

			Polygon p = new Polygon();

			for (String s : segments) {
				// System.out.println(s);
				String[] points = s.split(", ");
				for (String co : points) {
					String[] point = co.split(" ");
					Point po = new Point(Double.parseDouble(point[0]), Double.parseDouble(point[1]));
					p.addPoint(po);
				}
			}

			rvalue = p;

		}
		//

		return rvalue;
	}

	protected static Object listToSingleProxy(Object value, Class<?> fieldTypeClass) {

		if (value instanceof List && fieldTypeClass != ArrayList.class && ((List) value).size() <= 1
				&& ((List) value).get(0) instanceof Entity && ((Entity) ((List) value).get(0)).isProxy()) {

			// System.out.println(">"+value);
			if (((List<Entity>) value).size() == 0)
				return null;
			return ((List<Entity>) value).get(0);

		}

		return value;
	}

}
