package queryGenerators;

import java.util.concurrent.TimeUnit;

import com.github.javafaker.Faker;

public class InsertCreditCardGenerator implements QueryGenerator {

	@Override
	public String generateQuery() {
		Faker faker = new Faker();
		StringBuilder str = new StringBuilder();
		str.append("insert CreditCard {");
		str.append("number: \"" + faker.finance().creditCard() + "\", ");
		//FIXME: Check if date should be inside quotes or not
		str.append("expiryDate: \"" + faker.date().future(730, TimeUnit.DAYS) + "\"");
		str.append("}");
		return str.toString();
	}

}
