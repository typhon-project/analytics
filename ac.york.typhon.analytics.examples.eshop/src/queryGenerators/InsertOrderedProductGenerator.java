package queryGenerators;

import java.util.Map;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import com.github.javafaker.Faker;

import infra.RunSimulator;
import utils.Utilities;

public class InsertOrderedProductGenerator implements QueryGenerator {

	@Override
	public String generateQuery(Map<String, String> parameters) {
		Faker faker = new Faker();
		String productId = "";
		String userId = parameters.get("userId");
		Random r = new Random();
		if (!parameters.containsKey("productId")) {
			
			productId = RunSimulator.allProducts.get(r.nextInt(RunSimulator.allProducts.size()));
		} else {
			productId = parameters.get("productId");
		}
		String seed = parameters.get("seed");
		StringBuilder str = new StringBuilder();
		str.append("insert OrderedProduct {");
		str.append("id: \"" + seed + "\", ");
		str.append("quantity: \"" + r.nextInt(5) + "\", ");
		str.append("product: [#" + productId + "], ");
		str.append("users: [#" + userId + "]");
		str.append("}");
		return str.toString();
	}
}
