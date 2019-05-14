package ac.uk.york.typhon.analytics.commons.datatypes.wrapper.parsedstatement;

import net.sf.jsqlparser.statement.Statement;
import net.sf.jsqlparser.statement.delete.Delete;

public final class DeleteParsedStatement implements IParsedStatement<Delete> {

	private Delete statement;

	@Override
	public Delete getStatement() {
		return this.statement;
	}

	@Override
	public void setStatement(Delete statement) {
		this.statement = statement;

	}

	// @Override
	// public Delete getStatement() {
	// return this.statement;
	//
	// }
	//
	// @Override
	// public void setStatement(Statement statement) {
	// this.statement = (Delete) statement;
	// }

}
