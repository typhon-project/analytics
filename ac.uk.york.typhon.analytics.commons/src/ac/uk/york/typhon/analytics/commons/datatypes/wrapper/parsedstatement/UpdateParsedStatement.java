package ac.uk.york.typhon.analytics.commons.datatypes.wrapper.parsedstatement;

import net.sf.jsqlparser.statement.update.Update;

public final class UpdateParsedStatement implements IParsedStatement<Update> {

	private Update statement;

	@Override
	public Update getStatement() {
		return this.statement;
	}

	@Override
	public void setStatement(Update statement) {
		this.statement = statement;

	}

	// @Override
	// public Update getStatement() {
	// return this.statement;
	//
	// }
	//
	// @Override
	// public void setStatement(Statement statement) {
	// this.statement = (Update) statement;
	// }

}
