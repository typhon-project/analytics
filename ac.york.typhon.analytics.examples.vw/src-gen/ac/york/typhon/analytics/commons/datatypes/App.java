package ac.york.typhon.analytics.commons.datatypes;

import java.util.ArrayList;
import java.util.Date;

import ac.york.typhon.analytics.commons.datatypes.*;
import ac.york.typhon.analytics.commons.datatypes.events.Entity;

public class App extends Entity{
	private Integer VIN;
	
	public Integer getVIN() {
		return this.VIN;
	}
	
	public void setVIN(Integer VIN) {
		this.VIN = VIN;
	}
	private String timeStamp;
	
	public String getTimeStamp() {
		return this.timeStamp;
	}
	
	public void setTimeStamp(String timeStamp) {
		this.timeStamp = timeStamp;
	}
	private Point vehicle_position;
	
	public Point getVehicle_position() {
		return this.vehicle_position;
	}
	
	public void setVehicle_position(Point vehicle_position) {
		this.vehicle_position = vehicle_position;
	}
	
	private ArrayList<ESP> esp;
	
	public ArrayList<ESP> getEsp() {
		return this.esp;
	}
	
	public void setEsp(ArrayList<ESP> esp) {
		this.esp = esp;
	}
	
	private ArrayList<WarningWeatherData> warnings;
	
	public ArrayList<WarningWeatherData> getWarnings() {
		return this.warnings;
	}
	
	public void setWarnings(ArrayList<WarningWeatherData> warnings) {
		this.warnings = warnings;
	}

	public String toString() { 
		String result = "";
	
		result = "App("+" VIN: " + VIN + " timeStamp: " + timeStamp + " vehicle_position: " + vehicle_position + " previousvalue: " + getPreviousValue() + " uuid: " + getUUID() + ")";
		return result;
	}
}

