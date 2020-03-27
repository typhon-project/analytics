package queryGenerators;

import com.github.javafaker.Faker;

public class InsertProductGenerator implements QueryGenerator {

	@Override
	public String generateQuery() {
		Faker faker = new Faker();
		StringBuilder str = new StringBuilder();
		str.append("insert Product {");
		str.append("name: \"" + faker.commerce().productName() + "\", ");
		str.append("description: \"" + faker.lorem().paragraph() + "\"");
		str.append("}");
		return str.toString();
	}

}
