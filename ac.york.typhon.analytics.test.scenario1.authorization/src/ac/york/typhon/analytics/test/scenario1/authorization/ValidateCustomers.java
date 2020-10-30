package ac.york.typhon.analytics.test.scenario1.authorization;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import ac.york.typhon.analytics.authorizer.GenericAuthorisationTask;
import ac.york.typhon.analytics.commons.datatypes.events.Event;

public class ValidateCustomers extends GenericAuthorisationTask {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7611427098194193918L;

	@Override
	public boolean checkCondition(Event event) {
		// protected region ValidateCustomersCheckConditionId on begin
		return event.getQuery().contains("insert Customer");
		// protected region ValidateCustomersCheckConditionId end
	}

	@Override
	public boolean shouldIReject(Event event) {
		// protected region ValidateCustomersShouldIRejectId on begin
		String query = event.getQuery();
		Pattern paymentsDetailsSubstring = Pattern.compile("paymentsDetails\\s*:\\s*\".*\"");
		Matcher matcher = paymentsDetailsSubstring.matcher(query);
		String paymentsDetails = "";
		if (matcher.find()) {
			paymentsDetails = matcher.group(0);
			try {
				paymentsDetails = paymentsDetails.substring(paymentsDetails.indexOf("\"") + 1);
				paymentsDetails = paymentsDetails.substring(0, paymentsDetails.lastIndexOf("\""));
			} catch (Exception e) {
			}
		}
		// very simple check to ensure credit cards are 16 digits, possibly with dashes
		// in between
		return paymentsDetails.replace("-", "").length() != 16;
		// protected region ValidateCustomersShouldIRejectId end
	}
}