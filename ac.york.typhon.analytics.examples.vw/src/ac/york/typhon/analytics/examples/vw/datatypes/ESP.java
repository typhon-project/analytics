package ac.york.typhon.analytics.examples.vw.datatypes;

import java.util.ArrayList;
import java.util.Date;

public class ESP {
	//FIXME: Some example VINs are out of bound for int. Thus I changed this to long but in ML is int.
	int VIN;
	String timestamp;
	String vehicle_position;
	boolean esp_edl;
	ArrayList<VehicleMetadata> metadata;
	
	public ArrayList<VehicleMetadata> getMetadata() {
		return metadata;
	}
	public void setMetadata(ArrayList<VehicleMetadata> metadata) {
		this.metadata = metadata;
	}
	public int getVIN() {
		return VIN;
	}
	public void setVIN(int vIN) {
		VIN = vIN;
	}
	public boolean isEsp_edl() {
		return esp_edl;
	}
	public void setEsp_edl(boolean esp_edl) {
		this.esp_edl = esp_edl;
	}
	public boolean isEsp_iddl() {
		return esp_iddl;
	}
	public void setEsp_iddl(boolean esp_iddl) {
		this.esp_iddl = esp_iddl;
	}
	public boolean isEsp_abs() {
		return esp_abs;
	}
	public void setEsp_abs(boolean esp_abs) {
		this.esp_abs = esp_abs;
	}

	boolean esp_iddl;
	boolean esp_abs;

	public String getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}
	public String getVehicle_position() {
		return vehicle_position;
	}
	public void setVehicle_position(String vehicle_position) {
		this.vehicle_position = vehicle_position;
	}
	
	public String toString() {
		return "VIN: " + VIN + " Timestamp: " + timestamp + " Position: " + vehicle_position + " EDL: " + esp_edl + " ABS: " + esp_abs + " IDDL: " + esp_iddl + " Metadata: " + metadata;
	}

}
