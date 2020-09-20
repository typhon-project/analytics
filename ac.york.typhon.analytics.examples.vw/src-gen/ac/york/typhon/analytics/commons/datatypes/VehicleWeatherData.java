package ac.york.typhon.analytics.commons.datatypes;

import java.util.ArrayList;
import java.util.Date;

import ac.york.typhon.analytics.commons.datatypes.*;
import ac.york.typhon.analytics.commons.datatypes.events.Entity;

public class VehicleWeatherData extends Entity{
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
	private Double temperature;
	
	public Double getTemperature() {
		return this.temperature;
	}
	
	public void setTemperature(Double temperature) {
		this.temperature = temperature;
	}
	private Double rain_Intensity;
	
	public Double getRain_Intensity() {
		return this.rain_Intensity;
	}
	
	public void setRain_Intensity(Double rain_Intensity) {
		this.rain_Intensity = rain_Intensity;
	}
	private Integer solar_Intensity;
	
	public Integer getSolar_Intensity() {
		return this.solar_Intensity;
	}
	
	public void setSolar_Intensity(Integer solar_Intensity) {
		this.solar_Intensity = solar_Intensity;
	}
	
	private VehMeta metadata;
	
	public VehMeta getMetadata() {
		return this.metadata;
	}
	
	public void setMetadata(VehMeta metadata) {
		this.metadata = metadata;
	}

	public String toString() { 
		String result = "";
	
		result = "VehicleWeatherData("+" timeStamp: " + timeStamp + " vehicle_position: " + vehicle_position + " temperature: " + temperature + " rain_Intensity: " + rain_Intensity + " solar_Intensity: " + solar_Intensity + " previousvalue: " + getPreviousValue() + " uuid: " + getUUID() + ")";
		return result;
	}
}

