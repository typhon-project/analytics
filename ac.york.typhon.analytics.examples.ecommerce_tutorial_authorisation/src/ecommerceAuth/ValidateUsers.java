package ecommerceAuth;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import ac.york.typhon.analytics.authorizer.GenericAuthorisationTask;
import ac.york.typhon.analytics.commons.datatypes.events.Event;

public class ValidateUsers extends GenericAuthorisationTask {

	@Override
	public boolean checkCondition(Event event) {
		// protected region ValidateUsersCheckConditionId on begin
		return event.getQuery().contains("insert User");
		// protected region ValidateUsersCheckConditionId end
	}

	@Override
	public boolean shouldIReject(Event event) {
		// protected region ValidateUsersShouldIRejectId on begin
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
		// very simple check to ensure creditcards are 16 digits, possibly with dashes in between
		return paymentsDetails.replace("-", "").length() != 16;
		// protected region ValidateUsersShouldIRejectId end
	}
}