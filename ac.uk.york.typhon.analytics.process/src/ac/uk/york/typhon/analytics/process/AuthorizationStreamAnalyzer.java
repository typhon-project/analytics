package ac.uk.york.typhon.analytics.process;

import java.io.Serializable;

import ac.uk.york.typhon.analytics.commons.datatypes.events.Event;

public abstract class AuthorizationStreamAnalyzer extends StreamAnalyzer implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public abstract boolean checkCondition(Event event);

	
}
