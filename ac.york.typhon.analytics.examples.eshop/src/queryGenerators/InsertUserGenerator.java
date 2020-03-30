package queryGenerators;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import com.github.javafaker.Faker;

public class InsertUserGenerator implements QueryGenerator {

	@Override
	public String generateQuery(Map<String, String> params) {
		Faker faker = new Faker();
		StringBuilder str = new StringBuilder();
		str.append("insert User {");
		str.append("id: \"" + UUID.randomUUID().toString()  + "\", ");
		str.append("name: \"" + faker.name().fullName() + "\", ");
		str.append("paymentsDetails: [CreditCard {id: \"" + UUID.randomUUID().toString() + "\", ");
		str.append("number: \"" + faker.finance().creditCard() + "\", ");
		str.append("expiryDate: \"" + faker.date().future(730, TimeUnit.DAYS) + "\"");
		str.append("}]");
		str.append("}");
		return str.toString();
	}

}
