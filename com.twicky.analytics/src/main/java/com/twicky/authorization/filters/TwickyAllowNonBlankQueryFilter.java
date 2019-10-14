package com.twicky.authorization.filters;

import org.apache.commons.lang3.StringUtils;

import ac.york.typhon.analytics.commons.datatypes.events.Event;

public class TwickyAllowNonBlankQueryFilter extends EventFilter {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public boolean checkCondition(Event preEvent) {

		String query = preEvent.getQuery().toLowerCase();
		if (StringUtils.isNotBlank(query)) {
			return true;
		} else {
			return false;
		}

	}

}
