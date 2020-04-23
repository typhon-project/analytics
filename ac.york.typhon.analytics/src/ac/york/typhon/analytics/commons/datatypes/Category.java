package ac.york.typhon.analytics.commons.datatypes;

import java.util.ArrayList;
import java.util.Date;

import ac.york.typhon.analytics.commons.datatypes.events.Entity;

public class Category extends Entity{
	private String id;
	
	public String getId() {
		return this.id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	private String name;
	
	public String getName() {
		return this.name;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public String toString() { 
		String result = "Category:( ";
		result += "UUID " + getUUID() + " previousValue " + getPreviousValue();
		result += " id: " + id + " name: " + name;
		result += " )";
		return result;
	}
}

