package queryGenerators;

import java.util.Map;

public interface QueryGenerator {
	
	public String generateQuery(Map<String, String> parameters);

}
