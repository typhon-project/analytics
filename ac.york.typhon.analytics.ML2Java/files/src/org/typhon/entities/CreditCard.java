package entities;

import java.util.ArrayList;
import java.util.Date;

public class CreditCard {
	private String id;
	
	public String getId() {
		return this.id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	private String number;
	
	public String getNumber() {
		return this.number;
	}
	
	public void setNumber(String number) {
		this.number = number;
	}
	private Date expiryDate;
	
	public Date getExpiryDate() {
		return this.expiryDate;
	}
	
	public void setExpiryDate(Date expiryDate) {
		this.expiryDate = expiryDate;
	}

	public String toString() { 
		String result = "";
	
		result = " id: " + id + " number: " + number + " expiryDate: " + expiryDate;
		return result;
	}
}

