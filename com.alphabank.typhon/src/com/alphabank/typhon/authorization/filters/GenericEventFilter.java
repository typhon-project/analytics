package com.alphabank.typhon.authorization.filters;

import ac.uk.york.typhon.analytics.commons.datatypes.events.Event;

public class GenericEventFilter extends EventFilter {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public boolean checkCondition(Event preEvent) {
		return true;
	}

	
}
