package queryGenerators;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import com.github.javafaker.Faker;

public class InsertAddressGenerator implements QueryGenerator {

	@Override
	public String generateQuery(Map<String, String> parameters) {
		int seed = Integer.parseInt(parameters.get("seed"));
		System.out.println(seed);
		Faker faker = new Faker(new Random(seed));
		StringBuilder str = new StringBuilder();
		str.append("insert Address {");
		str.append("id: \"" + parameters.get("AUUID") + "\", ");
		str.append("street: \"" + faker.address().streetAddress() + "\", ");
		str.append("country: \"" + faker.address().country() + "\"");
		str.append("}");
		return str.toString();
	}

}
