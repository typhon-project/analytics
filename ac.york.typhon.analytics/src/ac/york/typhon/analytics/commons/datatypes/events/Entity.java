package ac.york.typhon.analytics.commons.datatypes.events;

import java.util.LinkedList;
import java.util.List;

public class Entity implements Cloneable {

	public static final List<String> ENTITYPACKAGES = new LinkedList<String>() {
		{
			add("ac.york.typhon.analytics.commons.datatypes");
		}
	};

	/**
	 * A proxy Entity will only contain a UUID field set.
	 */
	private boolean isProxy = false;

	private String UUID;
	private Entity previousValue;
	private String database;

	@Override
	public Entity clone() throws CloneNotSupportedException {
		return (Entity) super.clone();
	}

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
