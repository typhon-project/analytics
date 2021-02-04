package finalreview.auth;

import java.util.List;

import ac.york.typhon.analytics.authorizer.GenericAuthorisationTask;
import ac.york.typhon.analytics.commons.datatypes.Customer;
import ac.york.typhon.analytics.commons.datatypes.events.DeserializedPostEvent;
import ac.york.typhon.analytics.commons.datatypes.events.Entity;
import ac.york.typhon.analytics.commons.datatypes.events.Event;
import ac.york.typhon.analytics.commons.datatypes.events.PostEvent;
import ac.york.typhon.analytics.commons.datatypes.events.PreEvent;
import ac.york.typhon.analytics.commons.enums.ITopicType;
import ac.york.typhon.analytics.streaming.TopicPublisher;

public class EnableResultSet extends GenericAuthorisationTask {

	@Override
	public boolean checkCondition(Event event) {
		// protected region EnableResultSetCheckConditionId on begin
		return true;
		// protected region EnableResultSetCheckConditionId end
	}

	public static final String blacklisted = "Eve";
	private String uUIDofBlacklisted = null;

	@Override
	public boolean shouldIReject(Event event) {
		System.out.println(event);
		// protected region EnableResultSetShouldIRejectId on begin
		((PreEvent) event).setResultSetNeeded(true);
		// ((PreEvent) event).setInvertedNeeded(true);
		String query = ((PreEvent) event).getQuery();
		if (
		// uUIDofBlacklisted == null &&
		query.toLowerCase().contains("insert review")) {
			try {
				String potentialuuid = query.substring(query.indexOf("author: #") + 9, query.indexOf(","));
				System.out.println(potentialuuid);
				List<Entity> uuidlist = new PolyStoreUtils(ClassLoader.getSystemClassLoader()).queryPolyStore(
						"from Customer c select c.@id, c.name where c.@id == #" + potentialuuid, false, false);
				if (!uuidlist.isEmpty() && ((Customer) uuidlist.get(0)).getName().equalsIgnoreCase(blacklisted)) {
					uUIDofBlacklisted = uuidlist.get(0).getUUID();
					System.out.println("found uuid of " + blacklisted + " :: " + uUIDofBlacklisted);
				}
			} catch (Exception e) {
				System.err.println("ERROR RETRIEVING UUID OF BLACKLISTED");
				e.printStackTrace();
			}
		}

		if (uUIDofBlacklisted != null && query.toLowerCase().contains("insert review")
				&& query.toLowerCase().contains(uUIDofBlacklisted)) {
			return true;
		} else
			return false;
		// protected region EnableResultSetShouldIRejectId end
	}

}