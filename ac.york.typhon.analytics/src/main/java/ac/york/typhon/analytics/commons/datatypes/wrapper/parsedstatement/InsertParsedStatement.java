package ac.york.typhon.analytics.commons.datatypes.wrapper.parsedstatement;

import net.sf.jsqlparser.statement.insert.Insert;

public final class InsertParsedStatement implements IParsedStatement<Insert> {

	private Insert statement;

	@Override
	public Insert getStatement() {
		return this.statement;
	}

	@Override
	public void setStatement(Insert statement) {
		this.statement = statement;

	}

	// @Override
	// public Insert getStatement() {
	// return this.statement;
	//
	// }
	//
	// @Override
	// public void setStatement(Statement statement) {
	// this.statement = (Insert) statement;
	// }
	//
	// @Override
	// public void setStatement(Insert statement) {
	// // TODO Auto-generated method stub
	//
	// }

}
