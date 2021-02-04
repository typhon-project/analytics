package ac.york.typhon.analytics.commons.datatypes;

import java.util.ArrayList;
import java.util.Date;

import ac.york.typhon.analytics.commons.datatypes.*;

public class address implements Cloneable{
	private String street;
	
	public String getStreet() {
		return this.street;
	}
	
	public void setStreet(String street) {
		this.street = street;
	}
	private String number;
	
	public String getNumber() {
		return this.number;
	}
	
	public void setNumber(String number) {
		this.number = number;
	}
	private String zipcode;
	
	public String getZipcode() {
		return this.zipcode;
	}
	
	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}
	private String city;
	
	public String getCity() {
		return this.city;
	}
	
	public void setCity(String city) {
		this.city = city;
	}

	public String toString() { 
		String result = "";
	
		result = "address(" + " street: " + street + " number: " + number + " zipcode: " + zipcode + " city: " + city + ")";
		return result;
	}

}

