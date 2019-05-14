package ac.uk.york.typhon.analytics.commons.datatypes.wrapper.parsedstatement;

import net.sf.jsqlparser.statement.Statement;
import net.sf.jsqlparser.statement.delete.Delete;

public final class DeleteGenericParsedStatement<T> extends GenericParsedStatement<T>  implements IParsedStatement<T> {

	private T statement;

//	@Override
//	public T getStatement() {
//		return this.statement;
//	}
//
//	@Override
//	public void setStatement(T statement) {
//		this.statement = statement;
//
//	}

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
