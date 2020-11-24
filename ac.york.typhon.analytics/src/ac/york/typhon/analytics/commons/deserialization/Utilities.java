package ac.york.typhon.analytics.commons.deserialization;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import ac.york.typhon.analytics.commons.datatypes.Point;
import ac.york.typhon.analytics.commons.datatypes.Polygon;
import ac.york.typhon.analytics.commons.datatypes.events.Entity;
import engineering.swat.typhonql.ast.Expr;
import engineering.swat.typhonql.ast.Obj;
import engineering.swat.typhonql.ast.PlaceHolderOrUUID;
import engineering.swat.typhonql.ast.Request;
import engineering.swat.typhonql.ast.Segment;
import engineering.swat.typhonql.ast.Statement;
import engineering.swat.typhonql.ast.Statement.Delete;
import engineering.swat.typhonql.ast.Statement.Update;
import engineering.swat.typhonql.ast.Where;
import engineering.swat.typhonql.ast.XY;

public class Utilities {

	public static boolean debug = false;

	public String createInvertedSelect(Request request) throws Exception {
		String dmlCommand = "";
		if (request.hasStm() && (request.getStm() instanceof Update || request.getStm() instanceof Delete)) {
			Statement stm = request.getStm();
			String eid = stm.getBinding().getEntity().getString();
			String vid = stm.getBinding().getVar().getString();
			String select = vid + ".@id, " + vid;
			dmlCommand += "from " + eid + " " + vid + " select " + select + " ";

			if (stm.hasWhere()) {
				Where where = stm.getWhere();
				dmlCommand += where.yieldTree();
			}
			dmlCommand = "{ \"query\" : \"" + dmlCommand + "\" }";
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
		} else if (expr.hasIntValue()) {
			if(field.getType().getName().equals("java.lang.Long"))
				value = Long.parseLong(expr.getIntValue().getString());
			else
				value = Integer.parseInt(expr.getIntValue().getString());
		}else if (expr.hasObjValue()) {
			value = expr.getObjValue();
			if (Utilities.debug)
				System.out.println(value);
			// System.out.println(expr.getObjValue().yieldTree());
			// throw new UnsupportedOperationException("Deserializer does not support
			// ObjValue");

			Obj objValue = (Obj) value;
			// TODO any way to get any more info on this?
			System.out.println(objValue.yieldTree());

			//

			Entity proxy = (Entity) field.getType().newInstance();
			proxy.setProxy(true);
			// proxy.setUUID(valueString);

			//

			value = proxy;

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
			value = Float.parseFloat(expr.getRealValue().getString());
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
			if (Utilities.debug)
				System.out.println(valueString);

			// multi-valued references given as a single uuid...
			if (field.getType().newInstance() instanceof Iterable) {
				ArrayList<Entity> proxies = new ArrayList<Entity>();
				Entity proxy = (Entity) ((Class<?>) ((ParameterizedType) field.getGenericType())
						.getActualTypeArguments()[0]).newInstance();
				proxy.setProxy(true);
				proxy.setUUID(valueString);
				proxies.add(proxy);
				value = proxies;
			}
			//
			else {
				Entity proxy = (Entity) field.getType().newInstance();
				proxy.setProxy(true);
				proxy.setUUID(valueString);
				value = proxy;
			}
			//

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

	public static Object getExprValue(String value, Class<?> fieldTypeClass) throws Exception {
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
			//
			// return as is for date and datetime
		} else if (fieldTypeClass.getName().equals("ac.york.typhon.analytics.commons.datatypes.Point")
				&& value.startsWith("POINT (")) {
			int i = value.indexOf("POINT (") + 7;
			int j = value.lastIndexOf(" ");
			int k = value.indexOf(")");
			// System.out.println(i + " " + j + " " + k);
			String x = value.substring(i, j);
			String y = value.substring(j, k);
			Point p = new Point(Double.parseDouble(x), Double.parseDouble(y));
			// System.out.println(">POINT: " + p);
			rvalue = p;
		} else if (fieldTypeClass.getName().equals("ac.york.typhon.analytics.commons.datatypes.Polygon")
				&& value.startsWith("POLYGON (")) {
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

		} else if (fieldTypeClass.getName().equals("java.lang.Long")) {
			rvalue = Long.parseLong(value);
		} else if (fieldTypeClass.getName().equals("java.lang.Integer")) {
			rvalue = Integer.parseInt(value);
		} else if (fieldTypeClass.getName().equals("java.lang.Float")) {
			rvalue = Float.parseFloat(value);
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
