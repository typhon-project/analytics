package entityUtils;

import java.util.ArrayList;
import java.util.Random;
import java.util.UUID;

import com.github.javafaker.Faker;

import utils.ExecuteQueries;

public class ProductCreator {

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
//		String productId = utils.executeUpdate(str.toString()).split("\\{\"uuid\":\"")[1].split("\"\\}}")[0];
		utils.createAndPublishPostEvent(str.toString());
		String productId = UUID.randomUUID().toString();
		return productId;
	}

	public static ArrayList<String> createCategories() throws Exception {
		
		System.out.println("Started Category Creation");
		ExecuteQueries eq = new ExecuteQueries();
		ExecuteQueries.Utils utils = eq.new Utils();
		Random r = new Random();

		ArrayList<String> categoryIds = new ArrayList<String>();
		ArrayList<String> possibleCategories = new ArrayList<String>();
		possibleCategories.add("Computers");
		possibleCategories.add("Groceries");
		possibleCategories.add("Health");
		possibleCategories.add("Clothing");
		possibleCategories.add("Kitchen");
		possibleCategories.add("Bathroom");
		possibleCategories.add("Furniture");

		
		for (int i = 0; i < possibleCategories.size(); i++) {
			String category = possibleCategories.get(i);
			StringBuilder str = new StringBuilder();
			str.append("insert Category {");
			str.append("id: \"" + i + "\", ");
			str.append("name: \"" + category + "\"");
			str.append("}");
//			String categoryId = utils.executeUpdate(str.toString()).split("\\{\"uuid\":\"")[1].split("\"\\}}")[0];
			utils.createAndPublishPostEvent(str.toString());
			String categoryId = UUID.randomUUID().toString();
			categoryIds.add(categoryId);
		}
		System.out.println("Finished Category Creation");
		return categoryIds;
	}
}