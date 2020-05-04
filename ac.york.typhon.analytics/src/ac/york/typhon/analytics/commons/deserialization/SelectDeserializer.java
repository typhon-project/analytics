package ac.york.typhon.analytics.commons.deserialization;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import ac.york.typhon.analytics.commons.datatypes.events.Entity;
import ac.york.typhon.analytics.commons.datatypes.events.Slot;
import ac.york.typhon.analytics.commons.datatypes.events.View;
import engineering.swat.typhonql.ast.Binding;
import engineering.swat.typhonql.ast.Request;
import engineering.swat.typhonql.ast.TyphonQLASTParser;

public class SelectDeserializer implements Deserializer {

	public ArrayList<String> UUIDs = new ArrayList<String>();

	@Override
	public List<Entity> deserialize(String query, String invertedSelectQuery, String resultSet,
			String invertedResultSet) throws Exception {

		// TODO remove when this is done by atb
		ExecuteQueries eq = new ExecuteQueries();
		ExecuteQueries.Utils utils = eq.new Utils();
		if (resultSet == null)
			resultSet = utils.executeQuery(query);
		//

		ArrayList<String> columnNames = getColunmNames(resultSet);
		Request request = TyphonQLASTParser.parseTyphonQLRequest((query).toCharArray());

		List<Binding> bindings = request.getQry().getBindings();
		if (bindings.size() > 1) {

			List<Slot> slots = new LinkedList<>();

			// XXX factor out common code
			for (Binding b : bindings) {

				String entityName = b.getEntity().yieldTree();
				String VId = "";
				if (b.hasVar()) {
					VId = b.getVar().yieldTree();
				}
				ArrayList<ArrayList<Object>> returnedValues = getValues(resultSet);

				if (returnedValues != null)
					for (ArrayList<Object> values : returnedValues) {

						//
						for (int i = 0; i < columnNames.size(); i++) {

							String[] split = columnNames.get(i).split("\\.");
							// System.out.println(Arrays.toString(split));
							String feildVId = split[0];
							String fieldNameWithoutVId = split[1];

							// current field is of the entity being iterated upon
							if (VId.equals(feildVId)) {

								Class<?> objClass = Class
										.forName("ac.york.typhon.analytics.commons.datatypes." + entityName);

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
								slots.add(new Slot(field.getDeclaringClass().getName(), fieldNameWithoutVId, value));

							}
						}

						//

					}

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
		Class<?> objClass = Class.forName("ac.york.typhon.analytics.commons.datatypes." + entityType);
		Entity entity = (Entity) objClass.newInstance();

		for (int i = 0; i < columnNames.size(); i++) {

			String vidWithDot = VId + "\\.";
			String[] split = columnNames.get(i).split(vidWithDot);
			// System.out.println(Arrays.toString(split));
			String fieldNameWithoutVId = split[1];

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
			String parsedField = fieldNameWithoutVId.substring(0, 1).toUpperCase() + fieldNameWithoutVId.substring(1);
			Method setter = objClass.getMethod("set" + parsedField, fieldTypeClass);

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

			//System.out.println("invoking: " + entity + " | " + value);
			setter.invoke(entity, value);

		}
		return entity;
	}

}
