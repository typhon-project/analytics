package ac.york.typhon.analytics.commons.datatypes;

import java.util.ArrayList;
import ac.york.typhon.analytics.commons.datatypes.events.Entity;

public class BasketItem extends Entity{
	private String id;
	
	public String getId() {
		return this.id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	private String product_date;
	
	public String getProduct_date() {
		return this.product_date;
	}
	
	public void setProduct_date(String product_date) {
		this.product_date = product_date;
	}
	private Integer totalAmount;
	
	public Integer getTotalAmount() {
		return this.totalAmount;
	}
	
	public void setTotalAmount(Integer totalAmount) {
		this.totalAmount = totalAmount;
	}
	private String paidWith;
	
	public String getPaidWith() {
		return this.paidWith;
	}
	
	public void setPaidWith(String paidWith) {
		this.paidWith = paidWith;
	}
	
	private ArrayList<Item> items;
	
	public ArrayList<Item> getItems() {
		return this.items;
	}
	
	public void setItems(ArrayList<Item> items) {
		this.items = items;
	}
	
	private Customer customers;
	
	public Customer getCustomers() {
		return this.customers;
	}
	
	public void setCustomers(Customer customers) {
		this.customers = customers;
	}

	public String toString() { 
		String result = "";
	
		result = "BasketItem(" + " id: " + id + " product_date: " + product_date + " totalAmount: " + totalAmount + " paidWith: " + paidWith + " items: " + items + " customers: " + customers + " previousvalue: " + getPreviousValue() + " uuid: " + getUUID() + ")";
		return result;
	}
}

