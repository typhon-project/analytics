package queryGenerators;

import java.util.Map;
import java.util.Random;

import com.github.javafaker.Faker;

import infra.RunSimulator;

public class InsertReviewGenerator implements QueryGenerator {

	@Override
	public String generateQuery(Map<String, String> parameters) {
		String productId = "";
		if (!parameters.containsKey("productId")) {
			Random r = new Random();
			productId =  RunSimulator.allProducts.get(r.nextInt(RunSimulator.allProducts.size()));
		} else {
			productId =  parameters.get("productId");
		}
		Faker faker = new Faker();
		StringBuilder str = new StringBuilder();
		str.append("insert Review {");
		str.append("content: \"" + faker.lorem().paragraph() + "\", ");
		str.append("product: #" + productId);
		str.append("}");
		return str.toString();
	}
}
