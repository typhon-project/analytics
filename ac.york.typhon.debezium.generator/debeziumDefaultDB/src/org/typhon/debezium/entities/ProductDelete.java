package org.typhon.debezium.entities;

import java.util.ArrayList;
import org.typhon.debezium.entities.ProductDML;

public class ProductDelete extends ProductDML {


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

	private String description;
	
	public String getDescription() {
		return this.description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}

	public String toString() {
		return "[" + "id: " + id + " " + "name: " + name + " " + "description: " + description + " " + "]";
	}	
}