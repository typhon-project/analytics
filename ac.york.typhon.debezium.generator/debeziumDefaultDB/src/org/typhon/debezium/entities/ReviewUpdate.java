package org.typhon.debezium.entities;

import java.util.ArrayList;
import org.typhon.debezium.entities.ReviewDML;

public class ReviewUpdate extends ReviewDML {


	private String idBefore;
	private String idAfter;
	
	public String getIdBefore() {
		return this.idBefore;
	}
	
	public String getIdAfter() {
		return this.idAfter;
	}
	
	public void setIdBefore(String idBefore) {
		this.idBefore = idBefore;
	}
	
	public void setIdAfter(String idAfter) {
		this.idAfter = idAfter;
	}

	private String descriptionBefore;
	private String descriptionAfter;
	
	public String getDescriptionBefore() {
		return this.descriptionBefore;
	}
	
	public String getDescriptionAfter() {
		return this.descriptionAfter;
	}
	
	public void setDescriptionBefore(String descriptionBefore) {
		this.descriptionBefore = descriptionBefore;
	}
	
	public void setDescriptionAfter(String descriptionAfter) {
		this.descriptionAfter = descriptionAfter;
	}

	public String toString() {
		return "[" + "id before: " + idBefore + " " +  "id after: " + idAfter + " " + "description before: " + descriptionBefore + " " +  "description after: " + descriptionAfter + " " + "]";
	}	
}