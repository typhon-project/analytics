package queryGenerators;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import com.github.javafaker.Faker;

import infra.RunSimulator;

public class SelectProductGenerator implements QueryGenerator {

	@Override
	public String generateQuery(Map<String, String> parameters) {
		int seed = Integer.parseInt(parameters.get("seed"));
		Random r = new Random(seed);
		String productUUID = RunSimulator.allProducts.get(r.nextInt(RunSimulator.allProducts.size()));
		StringBuilder str = new StringBuilder();
		str.append("from Product p select p where ");
		str.append("p.@id == #" + productUUID);
		return str.toString();
	}

}
