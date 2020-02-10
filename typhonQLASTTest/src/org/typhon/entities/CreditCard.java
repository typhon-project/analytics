package org.typhon.entities;

import org.typhon.entities.Entity;

import engineering.swat.typhonql.ast.DateTime.Date;

import java.util.ArrayList;

public class CreditCard extends Entity {

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
	public CreditCard(String id, String number, Date expiryDate) {
		this.id = id;
this.number = number;
this.expiryDate = expiryDate;
	}
	

}
