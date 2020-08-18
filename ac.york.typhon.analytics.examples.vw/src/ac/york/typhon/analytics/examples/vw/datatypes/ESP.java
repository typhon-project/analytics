package ac.york.typhon.analytics.examples.vw.datatypes;

import java.util.ArrayList;
import java.util.Date;

public class ESP {
	// FIXME: VW ML model does not have VIN for ESP but a reference you get it from a reference to VehicleMetadata.
	// Their simulator/generator though has VIN as part of the ESP datastructure. 
	// In scenario 1 VIM is not needed. Thus, I leave the value empty. VW needs to most probably change their generator
	// To produce data consistent to their final ML model.
	int VIN;
	String timestamp;
	// FIXME: I use ElasticSearch's GeoPoint as a datatype here, until I got an update from ML/QL on which is the appropriate one.
	GeoPoint vehicle_position;
	boolean esp_edl;
	// FIXME: VW's simulator/generator doesn't produce vehicle metadata as part of the ESP json object. I leave this empty as Scenario 1 does not need this.
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
	public GeoPoint getVehicle_position() {
		return vehicle_position;
	}
	public void setVehicle_position(GeoPoint vehicle_position) {
		this.vehicle_position = vehicle_position;
	}
	
	public String toString() {
		return "VIN: " + VIN + " Timestamp: " + timestamp + " Position: " + vehicle_position + " EDL: " + esp_edl + " ABS: " + esp_abs + " IDDL: " + esp_iddl + " Metadata: " + metadata;
	}

}
