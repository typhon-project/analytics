package ac.york.typhon.analytics.commons.datatypes;

import java.util.ArrayList;
import java.util.Date;

import ac.york.typhon.analytics.commons.datatypes.*;
import ac.york.typhon.analytics.commons.datatypes.events.Entity;

public class ESP extends Entity{
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
	private Boolean esp_edl;
	
	public Boolean getEsp_edl() {
		return this.esp_edl;
	}
	
	public void setEsp_edl(Boolean esp_edl) {
		this.esp_edl = esp_edl;
	}
	private Boolean esp_idd;
	
	public Boolean getEsp_idd() {
		return this.esp_idd;
	}
	
	public void setEsp_idd(Boolean esp_idd) {
		this.esp_idd = esp_idd;
	}
	private Boolean esp_abs;
	
	public Boolean getEsp_abs() {
		return this.esp_abs;
	}
	
	public void setEsp_abs(Boolean esp_abs) {
		this.esp_abs = esp_abs;
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
	
		result = "ESP("+" timeStamp: " + timeStamp + " vehicle_position: " + vehicle_position + " esp_edl: " + esp_edl + " esp_idd: " + esp_idd + " esp_abs: " + esp_abs + " previousvalue: " + getPreviousValue() + " uuid: " + getUUID() + ")";
		return result;
	}
}

