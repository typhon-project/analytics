package queryGenerators;

import java.util.Map;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import com.github.javafaker.Faker;

import infra.RunSimulator;
import utils.Utilities;

public class InsertOrderProductGenerator implements QueryGenerator {

	@Override
	public String generateQuery(Map<String, String> parameters) {
		Faker faker = new Faker();
		Random r = new Random();
		int productPosition = r.nextInt(RunSimulator.allProducts.size());
		String userId = parameters.get("userId");
		StringBuilder str = new StringBuilder();
		str.append("insert OrderProduct {");
		str.append("product_date: \"" + faker.date().past(10, TimeUnit.SECONDS) + "\", ");
		str.append("totalAmount: " + Utilities.getRandomNumberInRange(10, 1000) + ", ");
		str.append("products: #" + RunSimulator.allProducts.get(productPosition)+ ", ");
		// TODO: check how to get the appropriate credit card.
//		str.append("paidWith: #" + userId + ",");
		str.append("users: #" + userId + "");
		str.append("}");
		return str.toString();
	}
}
