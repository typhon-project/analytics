package ac.york.typhon.analytics.commons.datatypes;

import java.util.ArrayList;
import java.util.Date;

import ac.york.typhon.analytics.commons.datatypes.*;
import ac.york.typhon.analytics.commons.datatypes.events.Entity;
import org.apache.flink.api.java.functions.KeySelector;

public class Order extends Entity{
	private String created;
	public String getCreated() {
		return this.created;
	}
	
	public void setCreated(String created) {
		this.created = created;
	}
	
	private ArrayList<Product> products;
	
	public ArrayList<Product> getProducts() {
		return this.products;
	}
	
	public void setProducts(ArrayList<Product> products) {
		this.products = products;
	}
	
	private Customer customer;
	
	public Customer getCustomer() {
		return this.customer;
	}
	
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public String toString() { 
		String result = "";
	
		result = "Order(" + " created: " + created + " products: " + products + " customer: " + customer + " previousvalue: " + getPreviousValue() + " uuid: " + getUUID() + ")";
		return result;
	}
	
	public static KeySelector<Order, String> getKeySelector(String keyName) {
		
		return new OrderKeySelector(keyName);
	}
}

