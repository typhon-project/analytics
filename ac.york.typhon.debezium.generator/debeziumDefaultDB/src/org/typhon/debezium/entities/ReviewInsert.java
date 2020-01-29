package org.typhon.debezium.entities;

import java.util.ArrayList;
import org.typhon.debezium.entities.ReviewDML;

public class ReviewInsert extends ReviewDML {


	private String id;
	
	public String getId() {
		return this.id;
	}
	
	public void setId(String id) {
		this.id = id;
	}

	private String description;
	
	public String getDescription() {
		return this.description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}


	public String toString() {
		return "[" + "id: " + id + " " + "description: " + description + " " + "]";	
	}
}