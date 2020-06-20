package entities;

import java.util.ArrayList;
import java.util.Date;

public class BasketProduct {
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
	private Date date_added;
	
	public Date getDate_added() {
		return this.date_added;
	}
	
	public void setDate_added(Date date_added) {
		this.date_added = date_added;
	}
	
	private ArrayList<Product> product;
	
	public ArrayList<Product> getProduct() {
		return this.product;
	}
	
	public void setProduct(ArrayList<Product> product) {
		this.product = product;
	}
	
	private ArrayList<Basket> basket;
	
	public ArrayList<Basket> getBasket() {
		return this.basket;
	}
	
	public void setBasket(ArrayList<Basket> basket) {
		this.basket = basket;
	}

	public String toString() { 
		String result = "";
	
		result = " id: " + id + " quantity: " + quantity + " date_added: " + date_added;
		return result;
	}
}

