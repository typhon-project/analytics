package entities;

import java.util.ArrayList;
import java.util.Date;

public class Basket {
	private String id;
	
	public String getId() {
		return this.id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	private ArrayList<BasketProduct> items;
	
	public ArrayList<BasketProduct> getItems() {
		return this.items;
	}
	
	public void setItems(ArrayList<BasketProduct> items) {
		this.items = items;
	}
	
	private ArrayList<User> user;
	
	public ArrayList<User> getUser() {
		return this.user;
	}
	
	public void setUser(ArrayList<User> user) {
		this.user = user;
	}

	public String toString() { 
		String result = "";
	
		result = " id: " + id;
		return result;
	}
}

