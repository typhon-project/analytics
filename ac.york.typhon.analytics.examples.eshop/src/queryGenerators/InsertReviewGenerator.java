package queryGenerators;

import java.util.Map;
import java.util.Random;

import com.github.javafaker.Faker;

import infra.RunSimulator;

public class InsertReviewGenerator implements QueryGenerator {

	@Override
	public String generateQuery(Map<String, String> parameters) {
		Random r = new Random();
		String randomProductId =  RunSimulator.allProducts.get(r.nextInt(RunSimulator.allProducts.size()));
		Faker faker = new Faker();
		StringBuilder str = new StringBuilder();
		str.append("insert Review {");
		str.append("content: \"" + faker.lorem().paragraph() + "\", ");
		str.append("product: #" + randomProductId);
		str.append("}");
		return str.toString();
	}
}
