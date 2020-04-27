package ac.york.typhon.analytics.commons.datatypes;

import ac.york.typhon.analytics.commons.datatypes.events.Entity;

//TODO make generator excend entity class
public class Address extends Entity{
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
		//XXX update generator for nicer output (from base-class, uuid etc)
		String result = "Address:( ";
		result += "UUID: " + getUUID() + " previousValue: " + getPreviousValue();
		result += "db: " + getDb() + " isProxy: " + isProxy();
		result += " id: " + id + " street: " + street + " country: " + country;
		result += " )";
		return result;
	}
}

