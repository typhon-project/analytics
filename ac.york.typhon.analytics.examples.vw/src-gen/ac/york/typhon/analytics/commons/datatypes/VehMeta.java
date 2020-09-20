package ac.york.typhon.analytics.commons.datatypes;

import java.util.ArrayList;
import java.util.Date;

import ac.york.typhon.analytics.commons.datatypes.*;
import ac.york.typhon.analytics.commons.datatypes.events.Entity;

public class VehMeta extends Entity{
	private Integer VIN;
	
	public Integer getVIN() {
		return this.VIN;
	}
	
	public void setVIN(Integer VIN) {
		this.VIN = VIN;
	}
	private String brand;
	
	public String getBrand() {
		return this.brand;
	}
	
	public void setBrand(String brand) {
		this.brand = brand;
	}
	private String model;
	
	public String getModel() {
		return this.model;
	}
	
	public void setModel(String model) {
		this.model = model;
	}
	private Integer constr_year;
	
	public Integer getConstr_year() {
		return this.constr_year;
	}
	
	public void setConstr_year(Integer constr_year) {
		this.constr_year = constr_year;
	}
	private String color;
	
	public String getColor() {
		return this.color;
	}
	
	public void setColor(String color) {
		this.color = color;
	}
	private Integer t_sensor_h;
	
	public Integer getT_sensor_h() {
		return this.t_sensor_h;
	}
	
	public void setT_sensor_h(Integer t_sensor_h) {
		this.t_sensor_h = t_sensor_h;
	}
	private String engine_type;
	
	public String getEngine_type() {
		return this.engine_type;
	}
	
	public void setEngine_type(String engine_type) {
		this.engine_type = engine_type;
	}
	
	private ArrayList<VehicleWeatherData> vehicle_weather_data;
	
	public ArrayList<VehicleWeatherData> getVehicle_weather_data() {
		return this.vehicle_weather_data;
	}
	
	public void setVehicle_weather_data(ArrayList<VehicleWeatherData> vehicle_weather_data) {
		this.vehicle_weather_data = vehicle_weather_data;
	}
	
	private ArrayList<ESP> vehicle_esp_data;
	
	public ArrayList<ESP> getVehicle_esp_data() {
		return this.vehicle_esp_data;
	}
	
	public void setVehicle_esp_data(ArrayList<ESP> vehicle_esp_data) {
		this.vehicle_esp_data = vehicle_esp_data;
	}

	public String toString() { 
		String result = "";
	
		result = "VehMeta("+" VIN: " + VIN + " brand: " + brand + " model: " + model + " constr_year: " + constr_year + " color: " + color + " t_sensor_h: " + t_sensor_h + " engine_type: " + engine_type + " previousvalue: " + getPreviousValue() + " uuid: " + getUUID() + ")";
		return result;
	}
}

