package entityCreator;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import com.github.javafaker.Faker;

import utils.ExecuteQueries;
import utils.ExecuteQueries.Utils;

public class UserCreator {

	public String create(int seed) throws Exception {
		ExecuteQueries eq = new ExecuteQueries();
		ExecuteQueries.Utils utils = eq.new Utils();	
		String addressId = createAddress(seed);
		String creditCardId = createCreditCard(seed);
		String basketId = createBasket(seed);
		Faker faker = new Faker(new Random(seed));
		StringBuilder str = new StringBuilder();
		str.append("insert User {");
		str.append("id: \"" + seed + "\", ");
		str.append("name: \"" + faker.name().fullName() + "\", ");
		str.append("address: [#" + addressId + "], ");
		str.append("paymentsDetails: [#" + creditCardId + "], ");
		str.append("basket: [#" + basketId + "]");
		str.append("}");
		System.out.println(str.toString());
		String productId = utils.executeUpdate(str.toString()).split("\\{\"uuid\":\"")[1].split("\"\\}}")[0];
		return productId;
	}

	public String createAddress(int seed) throws Exception {

		ExecuteQueries eq = new ExecuteQueries();
		ExecuteQueries.Utils utils = eq.new Utils();
		
		Faker faker = new Faker(new Random(seed));
		StringBuilder str = new StringBuilder();
		str.append("insert Address {");
		str.append("id: \"" + seed + "\", ");
		str.append("street: \"" + faker.address().streetAddress() + "\", ");
		str.append("country: \"" + faker.address().country() + "\"");
		str.append("}");
		String addressId = utils.executeUpdate(str.toString()).split("\\{\"uuid\":\"")[1].split("\"\\}}")[0];

		return addressId;
	}
	
	public String createCreditCard(int seed) throws Exception {

		ExecuteQueries eq = new ExecuteQueries();
		ExecuteQueries.Utils utils = eq.new Utils();
		
		Faker faker = new Faker(new Random(seed));
		StringBuilder str = new StringBuilder();
		str.append("insert CreditCard {");
		str.append("id: \"" + seed + "\", ");
		str.append("number: \"" + faker.finance().creditCard() + "\", ");
		str.append("expiryDate: \"" + LocalDateTime.now().plusYears(2) + "\"");
		str.append("}");
		String creditCardId = utils.executeUpdate(str.toString()).split("\\{\"uuid\":\"")[1].split("\"\\}}")[0];

		return creditCardId;
	}
	
	public String createBasket(int seed) throws Exception {

		ExecuteQueries eq = new ExecuteQueries();
		ExecuteQueries.Utils utils = eq.new Utils();
		
		StringBuilder str = new StringBuilder();
		str.append("insert Basket {");
		str.append("id: \"" + seed + "\"");
		str.append("}");
		String basketId = utils.executeUpdate(str.toString()).split("\\{\"uuid\":\"")[1].split("\"\\}}")[0];

		return basketId;
	}
}