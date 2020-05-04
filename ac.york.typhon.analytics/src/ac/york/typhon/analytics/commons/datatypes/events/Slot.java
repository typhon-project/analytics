package ac.york.typhon.analytics.commons.datatypes.events;

public class Slot {

	private String type;
	private String fieldName;
	private Object value;

	public Slot(String type, String fieldName, Object value) {
		this.type = type;
		this.fieldName = fieldName;
		this.value = value;
	}

	public String getType() {
		return type;
	}

	public String getFieldName() {
		return fieldName;
	}

	public Object getValue() {
		return value;
	}

	public String toString() {
		return "[Slot:[type:" + type + "][fieldName:" + fieldName + "][value:" + value + "]]";
	}

}
