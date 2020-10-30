package ac.york.typhon.analytics.test.scenario1.authorization;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import ac.york.typhon.analytics.authorizer.GenericAuthorisationTask;
import ac.york.typhon.analytics.commons.datatypes.events.Event;

public class ValidateFeedbacks extends GenericAuthorisationTask {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1966056506304239745L;

	@Override
	public boolean checkCondition(Event event) {
		// protected region ValidateFeedbacksCheckConditionId on begin
		return event.getQuery().contains("insert Feedback");
		// protected region ValidateFeedbacksCheckConditionId end
	}

	@Override
	public boolean shouldIReject(Event event) {
		// protected region ValidateReviewsShouldIRejectId on begin
		String query = event.getQuery();
		Pattern contentSubstring = Pattern.compile("content\\s*:\\s*\".*\"");
		Matcher matcher = contentSubstring.matcher(query);
		String content = "";
		if (matcher.find()) {
			content = matcher.group(0);
			try {
				content = content.substring(content.indexOf("\"") + 1);
				content = content.substring(0, content.lastIndexOf("\""));
			} catch (Exception e) {
			}
		}
		return content.length() < 10;
		// protected region ValidateReviewsShouldIRejectId end
	}

	public static void main(String[] a) {
		Pattern contentSubstring = Pattern.compile("content\\s*:\\s*\".*\"");
		Matcher matcher = contentSubstring.matcher("content : \"papa \"  kia\"");
		String content = "";
		if (matcher.find()) {
			content = matcher.group(0);
			try {
				content = content.substring(content.indexOf("\"") + 1);
				content = content.substring(0, content.lastIndexOf("\""));
			} catch (Exception e) {
			}
			System.out.println(content);
		}
	}
}