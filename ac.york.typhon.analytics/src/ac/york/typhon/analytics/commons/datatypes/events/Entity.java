package ac.york.typhon.analytics.commons.datatypes.events;

public class Entity {
	
	private String UUID;
	private Entity update;

	public Entity getUpdate() {
		return update;
	}

	public void setUpdate(Entity update) {
		this.update = update;
	}

	public String getUUID() {
		return UUID;
	}

	public void setUUID(String uUID) {
		UUID = uUID;
	}

}
