package entities;

import java.util.ArrayList;
import java.util.Date;

public class OrderedProduct {
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
	
	private Product product;
	
	public Product getProduct() {
		return this.product;
	}
	
	public void setProduct(Product product) {
		this.product = product;
	}

	public String toString() { 
		String result = "";
	
		result = " id: " + id + " quantity: " + quantity;
		return result;
	}
}

