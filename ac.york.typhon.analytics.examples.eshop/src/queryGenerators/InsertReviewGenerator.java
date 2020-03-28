package queryGenerators;

import java.util.Map;

import com.github.javafaker.Faker;

public abstract class InsertReviewGenerator implements QueryGenerator {

	@Override
	public String generateQuery(Map<String, String> parameters) {
		Faker faker = new Faker();
		StringBuilder str = new StringBuilder();
		str.append("insert Review {");
		str.append("content: \"" + faker.lorem().paragraph() + "\",");
		// TODO: Find a way to add a product id here
//		str.append("product: \"" + faker.lorem().paragraph() + "\"");
		str.append("}");
		return str.toString();
	}
}
