package queryGenerators;

import com.github.javafaker.Faker;

public class InsertCommentGenerator implements QueryGenerator {

	@Override
	public String generateQuery() {
		Faker faker = new Faker();
		StringBuilder str = new StringBuilder();
		str.append("insert Comment {");
		str.append("content: \"" + faker.lorem().paragraph() + "\"");
		str.append("}");
		return str.toString();
	}
}
