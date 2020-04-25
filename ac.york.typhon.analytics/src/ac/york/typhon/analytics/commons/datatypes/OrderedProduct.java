package ac.york.typhon.analytics.commons.datatypes;

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
	
	private Product product;
	
	public Product getProduct() {
		return this.product;
	}
	
	public void setProduct(Product product) {
		this.product = product;
	}

	public String toString() { 
		String result = "Category:( ";
		result += "UUID " + getUUID() + " previousValue " + getPreviousValue();
		result += "db: " + getDb() + " isProxy: " + isProxy();
		result += " id: " + id + " quantity: " + quantity;
		result += " )";
		return result;
	}
}

