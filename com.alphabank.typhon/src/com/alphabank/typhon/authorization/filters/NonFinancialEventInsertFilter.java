package com.alphabank.typhon.authorization.filters;

import ac.uk.york.typhon.analytics.commons.datatypes.events.Event;

public class NonFinancialEventInsertFilter extends EventFilter {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public boolean checkCondition(Event preEvent) {

		String query = preEvent.getQuery().toLowerCase();
		if (query.contains("insert") && query.contains("non_fnc_ev")) {
			return true;
		} else {
			return false;
		}
	}

}
