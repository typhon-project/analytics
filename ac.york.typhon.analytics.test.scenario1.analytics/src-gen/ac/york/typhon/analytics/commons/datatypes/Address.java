package ac.york.typhon.analytics.commons.datatypes;

import ac.york.typhon.analytics.commons.datatypes.events.Entity;

public class Address extends Entity{
	private String streetName;
	
	public String getStreetName() {
		return this.streetName;
	}
	
	public void setStreetName(String streetName) {
		this.streetName = streetName;
	}
	private Integer streetNumber;
	
	public Integer getStreetNumber() {
		return this.streetNumber;
	}
	
	public void setStreetNumber(Integer streetNumber) {
		this.streetNumber = streetNumber;
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
	private String country;
	
	public String getCountry() {
		return this.country;
	}
	
	public void setCountry(String country) {
		this.country = country;
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
	
		result = "Address(" + " streetName: " + streetName + " streetNumber: " + streetNumber + " zipcode: " + zipcode + " city: " + city + " country: " + country + " customer: " + customer + " previousvalue: " + getPreviousValue() + " uuid: " + getUUID() + ")";
		return result;
	}
}

