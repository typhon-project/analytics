package ac.york.typhon.analytics.commons.deserialization;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import ac.york.typhon.analytics.commons.datatypes.Point;
import ac.york.typhon.analytics.commons.datatypes.Polygon;
import ac.york.typhon.analytics.commons.datatypes.events.Entity;
import engineering.swat.typhonql.ast.Custom;
import engineering.swat.typhonql.ast.Expr;
import engineering.swat.typhonql.ast.Expr.Int;
import engineering.swat.typhonql.ast.Expr.Real;
import engineering.swat.typhonql.ast.KeyVal;
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

	public static Object getExprValue(Expr expr, Field field, ClassLoader engineClassLoader) throws Exception {
		Object value = null;
		if (expr.hasBlobPointerValue()) {
			value = expr.getBlobPointerValue().getString();
		} else if (expr.hasBoolValue())
			value = Boolean.parseBoolean(expr.getBoolValue().yieldTree());
		else if (expr.hasCustomValue()) {

			Custom custom = expr.getCustomValue();

			Object ce = field.getType().newInstance();
			value = ce;

			String objType = custom.getTyp().yieldTree();
			if (Utilities.debug)
				System.out.println("objtype: " + objType);

			Class<?> objClass = null;
			for (String ep : Entity.ENTITYPACKAGES)
				try {
					objClass = engineClassLoader.loadClass(ep + "." + objType); // Class.forName(ep + "." + objType);
					break;
				} catch (Exception e) {
				}

			if (custom.hasKeyVals()) {

				for (KeyVal kv : custom.getKeyVals()) {

					Field f = objClass.getDeclaredField(kv.getKey().yieldTree());
					if (Utilities.debug)
						System.out.println("f: " + f);
					f.setAccessible(true);
					Class<?> fieldTypeClass = f.getType();
					Method setter = objClass.getMethod("set" + kv.getKey().yieldTree().substring(0, 1).toUpperCase()
							+ kv.getKey().yieldTree().substring(1), fieldTypeClass);

					Expr expr2 = kv.getValue();

					Object value2 = Utilities.getExprValue(expr2, f, engineClassLoader);

					if (Utilities.debug)
						System.out.println("invoking2: " + ce + " | " + value2 + " ("
								+ (value2 != null ? value2.getClass() : "(null value)") + ")");
					//
					setter.invoke(ce, value2);

				}

			}
		} else if (expr.hasDtValue()) {
			if (expr.getDtValue().hasDate())
				value = expr.getDtValue().getDate().getString();
			else if (expr.getDtValue().hasDateTime())
				value = expr.getDtValue().getDateTime().getString();
//			SimpleDateFormat sdf = new SimpleDateFormat();
//			Date date = sdf.parse(value.toString());
//			value = date;
		} else if (expr.hasIntValue()) {
			if (field.getType().getName().equals("java.lang.Long"))
				value = Long.parseLong(expr.getIntValue().getString());
			else
				value = Integer.parseInt(expr.getIntValue().getString());
		} else if (expr.hasObjValue()) {
			value = expr.getObjValue();
			if (Utilities.debug)
				System.out.println(value);
			// System.out.println(expr.getObjValue().yieldTree());
			// throw new UnsupportedOperationException("Deserializer does not support
			// ObjValue");

			Obj objValue = (Obj) value;
			// TODO any way to get any more info on this?
			System.out.println("OBJVALUE: " + objValue.yieldTree());

			//

			Entity objentity;

			if (field.getType().newInstance() instanceof Iterable) {
				objentity = (Entity) ((Class<?>) ((ParameterizedType) field.getGenericType())
						.getActualTypeArguments()[0]).newInstance();
				ArrayList<Entity> objs = new ArrayList<Entity>();
				objs.add(objentity);
				value = objs;
			} else {
				objentity = (Entity) field.getType().newInstance();
				value = objentity;
			}

			for (KeyVal kv : objValue.getKeyVals()) {

				// set uuid if it is manually set
				if (!kv.hasKey())
					objentity.setUUID(kv.getValue().yieldTree());
				// for all other fields
				else {

					Field field2 = objentity.getClass().getDeclaredField(kv.getKey().yieldTree());
					field2.setAccessible(true);
					Class<?> fieldTypeClass = field2.getType();
					Method setter = objentity.getClass()
							.getMethod("set" + kv.getKey().yieldTree().substring(0, 1).toUpperCase()
									+ kv.getKey().yieldTree().substring(1), fieldTypeClass);

					Expr expr2 = kv.getValue();

					Object value2 = Utilities.getExprValue(expr2, field2, engineClassLoader);

					// System.out.println(query.getResolvedQuery());

					// System.out.println(expr + " ::: " + field.getName()+ " ::: " +
					// entity.getClass());
					// System.out.println(value.getClass());
					//
					value2 = Utilities.listToSingleProxy(value2, fieldTypeClass);
					//
					if (Utilities.debug)
						System.out.println("invoking: " + objentity + " | " + value2 + " ("
								+ (value2 != null ? value2.getClass() : "(null value)") + ")");
					//
					setter.invoke(objentity, value2);
				}
			}

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
		else if (expr.isNeg()) {
			Expr neg = expr.getArg();
			if (neg instanceof Int) {
				if (field.getType().getName().equals("java.lang.Long"))
					value = Long.parseLong("-" + neg.yieldTree());
				else
					value = Integer.parseInt("-" + neg.yieldTree());
			} else if (neg instanceof Real)
				value = Float.parseFloat("-" + neg.yieldTree());
			else {
				throw new UnsupportedOperationException(
						"Deserializer does not support negative values that are not Int or Real");
			}
		} else if (expr.hasStrValue()) {
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
			//
			// System.err.println(valueString);
			// remove the # as normally such values do not include it
			if (valueString.startsWith("#"))
				valueString = valueString.substring(1);
			//

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
