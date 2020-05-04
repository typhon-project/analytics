package ac.york.typhon.analytics.commons.datatypes.events;

import java.util.List;
import java.util.stream.Collectors;

/**
 * NB: this class will have all of the Entity fields null as it does not
 * represent a single element but a complex View
 * 
 * @author kb634
 *
 */
public class View extends Entity {

	private List<Slot> slots;

	public View(List<Slot> slots) {
		this.slots = slots;
	}

	public List<String> getSlotTypeNames() {
		return slots.stream().map(s -> s.getType()).collect(Collectors.toList());
	}

	public List<String> getSlotFieldNames() {
		return slots.stream().map(s -> s.getFieldName()).collect(Collectors.toList());
	}

	public Object getValue(String type, String fieldName) {
		Object ret = null;
		for (Slot s : slots)
			if (s.getType().equals(type) && s.getFieldName().equals(fieldName)) {
				if (ret != null)
					System.err.println("warning, this view in inconsistent, more than one slot found with type: " + type
							+ " and fieldName: " + fieldName + " (returning the trailing element)");
				ret = s;
			}
		return ret;
	}

	public List<Slot> getSlots(String key) {
		return slots;
	}

	public String toString() {

		return "View:(" + slots+")";

	}

}
