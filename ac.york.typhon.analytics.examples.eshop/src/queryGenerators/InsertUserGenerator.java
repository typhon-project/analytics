package queryGenerators;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.Properties;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import com.github.javafaker.Faker;

public class InsertUserGenerator implements QueryGenerator {

	@Override
	public String generateQuery(Map<String, String> params) {
		int seed = Integer.parseInt(params.get("seed"));
		Faker faker = new Faker(new Random(seed));
		StringBuilder str = new StringBuilder();
		str.append("insert User {");
		str.append("id: \"" + UUID.randomUUID().toString() + "\", ");
		str.append("name: \"" + faker.name().fullName() + "\", ");
		str.append("paymentsDetails: [CreditCard {id: \"" + UUID.randomUUID().toString() + "\", ");
		str.append("number: \"" + faker.finance().creditCard() + "\", ");
		str.append("expiryDate: \"" + faker.date().future(730, TimeUnit.DAYS) + "\"");
		str.append("}]");
		str.append("}");
		return str.toString();
	}

}
