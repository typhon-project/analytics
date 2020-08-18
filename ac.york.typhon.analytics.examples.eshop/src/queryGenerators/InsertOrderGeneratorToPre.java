package queryGenerators;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.glassfish.jersey.model.Parameter.ParamAnnotationHelper;

import com.github.javafaker.Faker;

import infra.RunSimulator;
import utils.Utilities;

public class InsertOrderGeneratorToPre implements QueryGenerator {

	@Override
	public String generateQuery(Map<String, String> parameters) {
//		Faker faker = new Faker();
		Random r = new Random();

		String seed = parameters.get("seed");
		StringBuilder str = new StringBuilder();
		str.append("insert Order {");
		str.append("id: \"" + seed + "\", ");
//		str.append("order_date: \"" + faker.date().future(3, TimeUnit.SECONDS) + "\", ");
		str.append("order_date: \"" + new Date() + "\", ");
		str.append("totalAmount: \"" + r.nextInt(1000) + "\", ");
		// FIXME: list of users is not correct
		str.append("orderedProducts: \"" + parameters.get("orderedProducts") + "\", ");
		str.append("users: [#" + parameters.get("userId") + "], ");
		// TODO adds new creditcard here with probability of being wrong for the
		// experiment
		str.append("paidWith: " + createCreditCardTQLStatement());
		//
		str.append("}");
		return str.toString();
	}

	private String createCreditCardTQLStatement() {
		StringBuilder str = new StringBuilder();
		str.append("CreditCard {");

//		Faker faker = new Faker(new Random());

		str.append("id: \"" + 1000 + "\", ");
		// number incorrect 50%
//		String ccn = faker.finance().creditCard();
		String ccn = "4595-2311-8902-2423";
//		int n = faker.number().randomDigit() + 1; // int == 1 to 10
		int n = (int) (Math.random()*10) + 1; // int == 1 to 10

//		if (faker.number().randomDigit() < 5)
		if ((int) (Math.random()*10) < 5)
			
			ccn = ccn.substring(0, ccn.length() - n);
		str.append("number: \"" + ccn + "\", ");
		// expiry date in the past: 50%
		LocalDateTime ltd = LocalDateTime.now();
//		if (faker.number().randomDigit() > 4)
		if ((int) (Math.random()*10) > 4)
			ltd = ltd.plusYears(1);
		else
			ltd = ltd.minusYears(1);
		str.append("expiryDate: \"" + ltd + "\"");
		str.append("}");
		return str.toString();
	}

}
