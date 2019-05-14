package ac.uk.york.typhon.analytics.commons.datatypes.events;

import ac.uk.york.typhon.analytics.commons.datatypes.commands.DMLCommand;
import ac.uk.york.typhon.analytics.commons.datatypes.commands.DeleteCommand;
import ac.uk.york.typhon.analytics.commons.datatypes.commands.InsertCommand;
import ac.uk.york.typhon.analytics.commons.datatypes.commands.SelectCommand;
import ac.uk.york.typhon.analytics.commons.datatypes.commands.UpdateCommand;
import ac.uk.york.typhon.analytics.commons.enums.StatementType;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

public abstract class Event {

	protected String id;
	protected String query;

	public Event() {

	}

	public Event(String id, String query) {

		this.id = id;
		this.query = query;
	}

	public StatementType getStatementType() {

		// TODO use Regex instead of split
		return StatementType.valueOf(query.split(" ")[0].toUpperCase());

	}

	public String getId() {
		return id;
	}

	public String getQuery() {
		return query;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setQuery(String query) {
		this.query = query;
	}

	@Override
	public String toString() {
		return "Event [id=" + id + ", query=" + query + "]";
	}

}
