package queryGenerators;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import com.github.javafaker.Faker;

public class InsertCreditCardGenerator implements QueryGenerator {

	@Override
	public String generateQuery(Map<String, String> parameters) {
		Faker faker = new Faker();
		StringBuilder str = new StringBuilder();
		str.append("insert CreditCard {");
		str.append("id: \"" + parameters.get("CCUUID") + "\", ");
		str.append("number: \"" + faker.finance().creditCard() + "\", ");
		str.append("expiryDate: \"" + LocalDateTime.now().plusYears(2) + "\"");
		str.append("}");
		return str.toString();
	}

}
