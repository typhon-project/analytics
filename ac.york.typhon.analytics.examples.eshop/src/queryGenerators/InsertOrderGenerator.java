package queryGenerators;

import java.util.Map;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.glassfish.jersey.model.Parameter.ParamAnnotationHelper;

import com.github.javafaker.Faker;

import infra.RunSimulator;
import utils.Utilities;

public class InsertOrderGenerator implements QueryGenerator {

	@Override
	public String generateQuery(Map<String, String> parameters) {
		Faker faker = new Faker();
		Random r = new Random();

		//FIXME: Find credit card number 
		String creditCard = "creditCardNumHere";
		String seed = parameters.get("seed");
		StringBuilder str = new StringBuilder();
		str.append("insert Order {");
		str.append("id: \"" + seed + "\", ");
		str.append("order_date: \"" + faker.date().future(3, TimeUnit.SECONDS) + "\", ");
		str.append("totalAmount: \"" + r.nextInt(1000) + "\", ");
		//FIXME: list of users is not correct
		str.append("orderedProducts: \"" + parameters.get("orderedProducts") + "\", ");
		str.append("users: [#" + parameters.get("userId") + ", ");
		str.append("paidWith: #" + creditCard + ", ");
		str.append("}");
		return str.toString();
	}
}
