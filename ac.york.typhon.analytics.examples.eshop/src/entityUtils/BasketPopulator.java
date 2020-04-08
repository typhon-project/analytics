package entityUtils;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Random;

import com.github.javafaker.Faker;

import utils.ExecuteQueries;
import utils.ExecuteQueries.Utils;

public class BasketPopulator {

	public String add(String productUUID, String basketUUID, int seed) throws Exception {
		ExecuteQueries eq = new ExecuteQueries();
		ExecuteQueries.Utils utils = eq.new Utils();	
		Random random = new Random(seed);
		Faker faker = new Faker(new Random(seed));
		StringBuilder str = new StringBuilder();
		str.append("insert BasketProduct {");
		str.append("quantity: " + random.nextInt(10) + ", ");
		str.append("date_added: \"" + LocalDate.now() + "\", ");
		str.append("product: [#" + productUUID + "], ");
		str.append("basket: [#" + basketUUID + "]");
		str.append("}");
		System.out.println(str.toString());
		String basketProductId = utils.executeUpdate(str.toString()).split("\\{\"uuid\":\"")[1].split("\"\\}}")[0];
		return basketProductId;
	}

	public void remove(String basketProductId, int seed) throws Exception {
		ExecuteQueries eq = new ExecuteQueries();
		ExecuteQueries.Utils utils = eq.new Utils();	
		StringBuilder str = new StringBuilder();
		// FIXME: Is this a correct delete syntax?
		str.append("delete BasketProduct bp where ");
		str.append("bp.@id == " + basketProductId);
		System.out.println(str.toString());
		utils.executeUpdate(str.toString());
	}
}