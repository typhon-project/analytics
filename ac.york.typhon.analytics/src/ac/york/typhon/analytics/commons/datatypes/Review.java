package ac.york.typhon.analytics.commons.datatypes;

import ac.york.typhon.analytics.commons.datatypes.events.Entity;

public class Review extends Entity{
	//XXX NB EMPTY to avoid having to resolve all of its references for testing
	public String toString() { 
		
		String result = "Entity[!PROXY-ONLY!]:( ";
		result += "UUID " + getUUID() + " previousValue " + getPreviousValue();
		result += "db: " + getDb() + " isProxy: " + isProxy();
		result += " )";
		return result;

	}
}

