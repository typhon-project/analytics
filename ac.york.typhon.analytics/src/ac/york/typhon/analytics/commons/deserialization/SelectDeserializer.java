package ac.york.typhon.analytics.commons.deserialization;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import org.omg.CORBA.portable.CustomValue;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import ac.york.typhon.analytics.commons.datatypes.events.Entity;
import ac.york.typhon.analytics.commons.datatypes.events.JSONQuery;
import ac.york.typhon.analytics.commons.datatypes.events.Slot;
import ac.york.typhon.analytics.commons.datatypes.events.View;
import engineering.swat.typhonql.ast.Binding;
import engineering.swat.typhonql.ast.Request;
import engineering.swat.typhonql.ast.TyphonQLASTParser;

public class SelectDeserializer implements Deserializer {

	public ArrayList<String> UUIDs = new ArrayList<String>();

	ClassLoader engineClassLoader;

	public SelectDeserializer(ClassLoader engineClassLoader) {
		this.engineClassLoader = engineClassLoader;
	}

	@Override
	public List<Entity> deserialize(JSONQuery query, JSONQuery invertedSelectQuery, String resultSet,
			String invertedResultSet, Boolean resultSetNeeded, Boolean invertedResultSetNeeded, int index)
			throws Exception {

		if (resultSetNeeded) {

			ArrayList<String> columnNames = getColunmNames(resultSet);
			Request request = TyphonQLASTParser.parseTyphonQLRequest((query.getQuery()).toCharArray());

			List<Binding> bindings = request.getQry().getBindings();
			if (bindings.size() > 1) {

				List<Slot> slots = new LinkedList<>();

				ArrayList<ArrayList<Object>> returnedValues = getValues(resultSet);

				HashMap<String, String> bindingsCache = new HashMap<String, String>();
				for (Binding b : bindings) {
					String entityName = b.getEntity().yieldTree();
					String VId = "";
					if (b.hasVar()) {
						VId = b.getVar().yieldTree();
					}
					if (VId.length() > 0)
						bindingsCache.put(VId, entityName);
				}

				// keep a cache of any possible custom datatype objects needed
				HashMap<String, Object> customDataTypes = new HashMap<String, Object>();

				if (returnedValues != null)
					for (ArrayList<Object> values : returnedValues) {

						//
						for (int i = 0; i < columnNames.size(); i++) {

							String[] split = columnNames.get(i).split("\\.");
							// System.out.println(Arrays.toString(split));
							String feildVId = split[0];
							String fieldNameWithoutVId = split[1];

							// current field is of the entity being iterated upon
							if (bindingsCache.containsKey(feildVId)) {

								Class<?> objClass = null;
								for (String ep : Entity.ENTITYPACKAGES)
									try {
										objClass = engineClassLoader.loadClass(ep + "." + bindingsCache.get(feildVId)); // Class.forName(ep
										break;
									} catch (Exception e) {
									}

								// for custom datatypes assume they are in the form of
								// customdatatypename$customdatatypeattributename
								if (fieldNameWithoutVId.contains("$")) {
									String[] customdatatype = fieldNameWithoutVId.split("\\$");
									String customreferencename = customdatatype[0];
									String customfield = customdatatype[1];
									Class<?> customtype = objClass.getDeclaredField(customreferencename).getType();

									if (!customDataTypes.containsKey(customreferencename)) {
										Object cobject = customtype.newInstance();
										customDataTypes.put(customreferencename, cobject);
									}
									//
									Object customobject = customDataTypes.get(customreferencename);

									//
									Field currentfield = customtype.getDeclaredField(customfield);
									currentfield.setAccessible(true);

									Class<?> currentfieldTypeClass = currentfield.getType();
									String parsedField = customfield.substring(0, 1).toUpperCase()
											+ customfield.substring(1);
									Method setter = customtype.getMethod("set" + parsedField, currentfieldTypeClass);
									Object value = values.get(i);
									setter.invoke(customobject,
											Utilities.getExprValue((String) value, currentfieldTypeClass));

								}

								else {

									Field field;
									if (fieldNameWithoutVId.contains("@id")) {
										fieldNameWithoutVId = "UUID";
										field = objClass.getSuperclass().getDeclaredField(fieldNameWithoutVId);
									}

									// System.out.println("<"+fieldNameWithoutVId);
									else
										field = objClass.getDeclaredField(fieldNameWithoutVId);

									field.setAccessible(true);

									// System.out.println("trying to invoke: " + entity + " " + values.get(i));
									Object value = values.get(i);

									// System.out.println("reference found: " + parsedField + " " + value);
									if (value instanceof String && !fieldNameWithoutVId.equals("UUID")) {
										String valueString = ((String) value);
										if (valueString.matches("([a-f0-9]{8}(-[a-f0-9]{4}){4}[a-f0-9]{8})")) {
											//
											Entity proxy = (Entity) field.getType().newInstance();
											proxy.setProxy(true);
											proxy.setUUID(valueString);
											value = proxy;
										}
									}
									//
									slots.add(
											new Slot(field.getDeclaringClass().getName(), fieldNameWithoutVId, value));

								}
							}
						}

						//

					}

				for (String referencename : customDataTypes.keySet()) {
					Object customvalue = customDataTypes.get(referencename);
					slots.add(new Slot(customvalue.getClass().getName(), referencename, customvalue));
				}

				return Collections.singletonList(new View(slots));

			} else {

				String entityName = request.getQry().getBindings().get(0).getEntity().yieldTree();
				String VId = "";
				if (request.getQry().getBindings().get(0).hasVar()) {
					VId = request.getQry().getBindings().get(0).getVar().yieldTree();
				}
				ArrayList<ArrayList<Object>> returnedValues = getValues(resultSet);

				ArrayList<Entity> ret = new ArrayList<Entity>();
				if (returnedValues != null)
					for (ArrayList<Object> values : returnedValues) {
						Entity e = createEntity(columnNames, values, entityName, VId);
						// System.out.println(e);
						ret.add(e);
					}

				return ret;

			}

		}
		// no result set asked to be returned so select is unable to return any
		// entities
		return new LinkedList<Entity>();
	}

	public ArrayList<String> getColunmNames(String resultSet) throws IOException {
		ObjectMapper objectMapper = new ObjectMapper();
		JsonNode root = objectMapper.readTree(resultSet);
		return new ObjectMapper().convertValue(root.path("columnNames"), ArrayList.class);
	}

	public ArrayList<ArrayList<Object>> getValues(String resultSet) throws IOException {
		ObjectMapper objectMapper = new ObjectMapper();
		JsonNode root = objectMapper.readTree(resultSet);
		return new ObjectMapper().convertValue(root.path("values"), ArrayList.class);
	}

	public Entity createEntity(ArrayList<String> columnNames, ArrayList<Object> values, String entityType, String VId)
			throws Exception {

		Class<?> objClass = null;
		for (String ep : Entity.ENTITYPACKAGES)
			try {
				objClass = engineClassLoader.loadClass(ep + "." + entityType);
				break;
			} catch (Exception e) {
			}

		Entity entity = (Entity) objClass.newInstance();

		// keep a cache of any possible custom datatype objects needed
		HashMap<String, Object> customDataTypes = new HashMap<String, Object>();

		for (int i = 0; i < columnNames.size(); i++) {

			String vidWithDot = VId + "\\.";
			String[] split = columnNames.get(i).split(vidWithDot);
			// System.out.println(Arrays.toString(split));
			String fieldNameWithoutVId = split[1];

			// for custom datatypes assume they are in the form of
			// customdatatypename$customdatatypeattributename
			if (fieldNameWithoutVId.contains("$")) {
				String[] customdatatype = fieldNameWithoutVId.split("\\$");
				String customreferencename = customdatatype[0];
				String customfield = customdatatype[1];
				Class<?> customtype = objClass.getDeclaredField(customreferencename).getType();

				if (!customDataTypes.containsKey(customreferencename)) {
					Object cobject = customtype.newInstance();
					customDataTypes.put(customreferencename, cobject);
				}
				//
				Object customobject = customDataTypes.get(customreferencename);

				String parsedField = customreferencename.substring(0, 1).toUpperCase()
						+ customreferencename.substring(1);
				Method setter = objClass.getMethod("set" + parsedField, customtype);
				setter.invoke(entity, customobject);

				//
				Field currentfield = customtype.getDeclaredField(customfield);
				currentfield.setAccessible(true);

				Class<?> currentfieldTypeClass = currentfield.getType();
				parsedField = customfield.substring(0, 1).toUpperCase() + customfield.substring(1);
				setter = customtype.getMethod("set" + parsedField, currentfieldTypeClass);
				Object value = values.get(i);
				setter.invoke(customobject, Utilities.getExprValue((String) value, currentfieldTypeClass));

			}

			else {

				Field field;
				if (fieldNameWithoutVId.contains("@id")) {
					fieldNameWithoutVId = "UUID";
					field = objClass.getSuperclass().getDeclaredField(fieldNameWithoutVId);
				} else {
					// System.out.println("<"+fieldNameWithoutVId);
					field = objClass.getDeclaredField(fieldNameWithoutVId);
				}

				field.setAccessible(true);
				Class<?> fieldTypeClass = field.getType();
				String parsedField = fieldNameWithoutVId.substring(0, 1).toUpperCase()
						+ fieldNameWithoutVId.substring(1);
				Method setter = objClass.getMethod("set" + parsedField, fieldTypeClass);

				// System.out.println("trying to invoke: " + entity + " " + values.get(i));

				Object value = values.get(i);

				// System.out.println("reference found: " + parsedField + " " + value);
				if (!fieldNameWithoutVId.equals("UUID")) {

					String valueString = null;

					if (value != null)
						valueString = value.toString();

					if (value instanceof ArrayList) {

						if (valueString.startsWith("[") && valueString.endsWith("]")) {

							valueString = valueString.replace("[", "").replace("]", "").replace(" ", "");

							String[] possibleProxies = null;

							try {
								possibleProxies = valueString.split(",");
							} catch (Exception e) {
								e.printStackTrace();
							}

							if (possibleProxies == null) {
								possibleProxies = new String[1];
								possibleProxies[0] = valueString;
							}

							LinkedList<Entity> proxies = new LinkedList<Entity>();
							for (String p : possibleProxies) {
								if (p.matches("([a-f0-9]{8}(-[a-f0-9]{4}){3}-[a-f0-9]{12})")) {
									//
									Entity proxy = (Entity) field.getType().newInstance();
									proxy.setProxy(true);
									proxy.setUUID(p);
									proxies.add(proxy);
									value = proxies;
								}
							}
						}
					}

					if (value instanceof String) {

						if (valueString.matches("([a-f0-9]{8}(-[a-f0-9]{4}){3}-[a-f0-9]{12})")) {
							//
							// check for lists giving back single values instead
							Class<?> collectiontype = null;
							if (field.getType().newInstance() instanceof Iterable<?>) {
								for (String ep : Entity.ENTITYPACKAGES)
									try {
										collectiontype = engineClassLoader.loadClass(
												ep + "." + ((Class) ((ParameterizedType) field.getGenericType())
														.getActualTypeArguments()[0]).getSimpleName());
										break;
									} catch (Exception e) {
									}
							}
							//
							Entity proxy;
							if (collectiontype == null)
								proxy = (Entity) field.getType().newInstance();
							else
								proxy = (Entity) collectiontype.newInstance();
							proxy.setProxy(true);
							proxy.setUUID(valueString);
							value = proxy;
							if (collectiontype != null) {
								ArrayList<Entity> ret = new ArrayList<Entity>();
								ret.add((Entity) value);
								value = ret;
							}

						} else
							value = Utilities.getExprValue((String) value, fieldTypeClass);

					}

				}

				value = Utilities.listToSingleProxy(value, fieldTypeClass);

				if (Utilities.debug)
					System.out.println("invoking: " + entity + " | " + value + " ("
							+ (value != null ? value.getClass() : "(null value)") + ")");

				setter.invoke(entity, value);
			}
		}

		return entity;
	}

}
