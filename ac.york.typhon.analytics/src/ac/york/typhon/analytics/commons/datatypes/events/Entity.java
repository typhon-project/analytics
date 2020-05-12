package ac.york.typhon.analytics.commons.datatypes.events;

public class Entity {
	
	/**
	 * A proxy Entity will only contain a UUID field set.
	 */
	private boolean isProxy = false;
	
	private String UUID;
	private Entity previousValue;
	private String database;

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

	public String getDb() {
		return database;
	}

	public void setDb(String database) {
		this.database = database;
	}

	public boolean isProxy() {
		return isProxy;
	}

	public void setProxy(boolean isProxy) {
		this.isProxy = isProxy;
	}

}
