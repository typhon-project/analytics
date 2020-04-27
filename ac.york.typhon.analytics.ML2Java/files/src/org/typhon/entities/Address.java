package entities;

import java.util.ArrayList;
import java.util.Date;

public class Address {
	private String id;
	
	public String getId() {
		return this.id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	private String street;
	
	public String getStreet() {
		return this.street;
	}
	
	public void setStreet(String street) {
		this.street = street;
	}
	private String country;
	
	public String getCountry() {
		return this.country;
	}
	
	public void setCountry(String country) {
		this.country = country;
	}

	public String toString() { 
		String result = "";
	
		result = " id: " + id + " street: " + street + " country: " + country;
		return result;
	}
}

