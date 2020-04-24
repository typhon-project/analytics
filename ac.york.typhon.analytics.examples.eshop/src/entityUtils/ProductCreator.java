package entityUtils;

import java.util.ArrayList;
import java.util.Random;
import java.util.UUID;

import com.github.javafaker.Faker;

import infra.RunSimulator;
import utils.ExecuteQueries;

public class ProductCreator implements Runnable {

	int seed;
	ArrayList<String> categoryIds;

	public ProductCreator(int seed, ArrayList<String> categoryIds) {
		this.seed = seed;
		this.categoryIds = categoryIds;
	}

	@Override
	public void run() {
		try {
			create(seed, categoryIds);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public String create(int seed, ArrayList<String> categoryIds) throws Exception {
		ExecuteQueries eq = new ExecuteQueries();
		ExecuteQueries.Utils utils = eq.new Utils();
		Random random = new Random(seed);
		int position = random.nextInt(categoryIds.size());
		String selectedId = categoryIds.get(position);
		Faker faker = new Faker(new Random(seed));
		StringBuilder str = new StringBuilder();
		str.append("insert Product {");
		str.append("name: \"" + faker.commerce().productName() + "\", ");
		str.append("description: \"" + faker.lorem().paragraph() + "\", ");
		str.append("category: [#" + selectedId + "]");
		str.append("}");
		String productId = utils.executeUpdate(str.toString()).split("\\{\"uuid\":\"")[1].split("\"\\}}")[0];
//		utils.createAndPublishPostEvent(str.toString());
//		String productId = UUID.randomUUID().toString();
		RunSimulator.allProducts.add(productId);
		return productId;
	}

	
}