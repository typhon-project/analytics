package ac.york.typhon.analytics.commons.datatypes.events;

public class Entity {
	
	private String UUID;
	private Entity previousValue;

	public Entity getPreviousValue() {
		return previousValue;
	}

	public void setPreviousValue(Entity previousValue) {
		this.previousValue = previousValue;
	}

	public String getUUID() {
		return UUID;
	}

	public void setUUID(String uUID) {
		UUID = uUID;
	}

}
