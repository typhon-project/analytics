package ac.york.typhon.analytics.commons.datatypes.examples;

import java.util.ArrayList;
import java.util.Date;

import ac.york.typhon.analytics.commons.datatypes.events.Entity;

public class OrderedProduct extends Entity {
	private String id;
	
	public String getId() {
		return this.id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	private int quantity;
	
	public int getQuantity() {
		return this.quantity;
	}
	
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	private ArrayList<Product> product;
	
	public ArrayList<Product> getProduct() {
		return this.product;
	}
	
	public void setProduct(ArrayList<Product> product) {
		this.product = product;
	}

	public String toString() { 
		String result = "";
	
		result = " id: " + id + " quantity: " + quantity;
		return result;
	}
}

