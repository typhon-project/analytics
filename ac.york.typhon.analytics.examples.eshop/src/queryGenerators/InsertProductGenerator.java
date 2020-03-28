package queryGenerators;

import java.util.Map;

import com.github.javafaker.Faker;

public class InsertProductGenerator implements QueryGenerator {

	@Override
	public String generateQuery(Map<String, String> parameters) {
		Faker faker = new Faker();
		StringBuilder str = new StringBuilder();
		str.append("insert Product {");
		str.append("name: \"" + faker.commerce().productName() + "\", ");
		str.append("description: \"" + faker.lorem().paragraph() + "\"");
		str.append("}");
		return str.toString();
	}

}
