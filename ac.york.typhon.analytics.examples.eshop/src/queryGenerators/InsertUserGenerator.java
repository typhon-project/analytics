package queryGenerators;

import com.github.javafaker.Faker;

public class InsertUserGenerator implements QueryGenerator {

	@Override
	public String generateQuery() {
		Faker faker = new Faker();
		StringBuilder str = new StringBuilder();
		str.append("insert User {");
		str.append("name: \"" + faker.name().fullName() + "\"");
		str.append("}");
		return str.toString();
	}

}
