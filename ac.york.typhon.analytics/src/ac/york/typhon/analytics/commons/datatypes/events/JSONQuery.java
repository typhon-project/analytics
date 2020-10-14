package ac.york.typhon.analytics.commons.datatypes.events;

import java.util.Arrays;

public class JSONQuery {

	private String query;
	private String[] parameterTypes;
	private String[] parameterNames;
	private Object[] boundRows;
	private String resolvedQuery;

	public String getQuery() {
		return query;
	}

	public void setQuery(String query) {
		this.query = query;
	}

	public String[] getParameterTypes() {
		return parameterTypes;
	}

	public void setParameterTypes(String[] parameterTypes) {
		this.parameterTypes = parameterTypes;
	}

	public String[] getParameterNames() {
		return parameterNames;
	}

	public void setParameterNames(String[] parameterNames) {
		this.parameterNames = parameterNames;
	}

	public Object[] getBoundRows() {
		return boundRows;
	}

	public void setBoundRows(Object[] boundRows) {
		this.boundRows = boundRows;
	}

	public String getResolvedQuery() {
		return resolvedQuery;
	}

	public void setResolvedQuery(String resolvedQuery) {
		this.resolvedQuery = resolvedQuery;
	}

	public String toString() {
		return "query: " + query + "\r\nparameterTypes: " + Arrays.toString(parameterTypes) + "\r\nparameterNames: "
				+ Arrays.toString(parameterNames) + "\r\nboundRows: " + Arrays.toString(boundRows)
				+ "\r\nresolvedQuery: " + resolvedQuery;
	}

}
