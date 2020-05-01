package ac.york.typhon.analytics.tests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import ac.york.typhon.analytics.commons.deserialization.Utilities;
import engineering.swat.typhonql.ast.ASTConversionException;
import engineering.swat.typhonql.ast.Request;
import engineering.swat.typhonql.ast.TyphonQLASTParser;

public class Deserilizer {
	
	@Test
	public void testInvertedSelectUpdate() {
		String query = "update Address a where a.@id == #3c904a88-f416-461d-849c-390dffae5fb4 set { street: \"street 420\" }";		
		Utilities utils = new Utilities();
		Request request = null;
		try {
			request = TyphonQLASTParser.parseTyphonQLRequest((query).toCharArray());
		} catch (ASTConversionException e1) {
			e1.printStackTrace();
		}
		String invertedSelect = "";
		try {
			invertedSelect = utils.createInvertedSelect(request);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String expectedInvertedSelect = "from Address a select a.@id, a.id, a.street, a.country where a.@id == #3c904a88-f416-461d-849c-390dffae5fb4";
        assertEquals(expectedInvertedSelect, invertedSelect);

	}

}
