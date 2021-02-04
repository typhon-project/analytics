package ac.york.typhon.analytics.commons.datatypes;

import java.util.ArrayList;
import java.util.Date;

import ac.york.typhon.analytics.commons.datatypes.*;
import ac.york.typhon.analytics.commons.datatypes.events.Entity;
import org.apache.flink.api.java.functions.KeySelector;

public class Customer extends Entity{
	private String name;
	public String getName() {
		return this.name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	private Integer age;
	public Integer getAge() {
		return this.age;
	}
	
	public void setAge(Integer age) {
		this.age = age;
	}
	private String payment;
	public String getPayment() {
		return this.payment;
	}
	
	public void setPayment(String payment) {
		this.payment = payment;
	}
	private address address;
	public address getAddress() {
		return this.address;
	} 	public void setAddress(address address) {
		this.address = address;
	} 	
	private ArrayList<Order> orders;
	
	public ArrayList<Order> getOrders() {
		return this.orders;
	}
	
	public void setOrders(ArrayList<Order> orders) {
		this.orders = orders;
	}
	
	private CommonReviews COMMONREVIEWSSOURCE;
	
	public CommonReviews getCOMMONREVIEWSSOURCE() {
		return this.COMMONREVIEWSSOURCE;
	}
	
	public void setCOMMONREVIEWSSOURCE(CommonReviews COMMONREVIEWSSOURCE) {
		this.COMMONREVIEWSSOURCE = COMMONREVIEWSSOURCE;
	}
	
	private CommonReviews COMMONREVIEWSTARGET;
	
	public CommonReviews getCOMMONREVIEWSTARGET() {
		return this.COMMONREVIEWSTARGET;
	}
	
	public void setCOMMONREVIEWSTARGET(CommonReviews COMMONREVIEWSTARGET) {
		this.COMMONREVIEWSTARGET = COMMONREVIEWSTARGET;
	}

	public String toString() { 
		String result = "";
	
		result = "Customer(" + " name: " + name + " age: " + age + " payment: " + payment + " address: " + address + " orders: " + orders + " COMMONREVIEWSSOURCE: " + COMMONREVIEWSSOURCE + " COMMONREVIEWSTARGET: " + COMMONREVIEWSTARGET + " previousvalue: " + getPreviousValue() + " uuid: " + getUUID() + ")";
		return result;
	}
	
	public static KeySelector<Customer, String> getKeySelector(String keyName) {
		
		return new CustomerKeySelector(keyName);
	}
}

