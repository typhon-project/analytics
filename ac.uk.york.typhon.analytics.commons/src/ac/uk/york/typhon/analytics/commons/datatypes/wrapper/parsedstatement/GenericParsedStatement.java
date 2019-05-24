package ac.uk.york.typhon.analytics.commons.datatypes.wrapper.parsedstatement;


public  class GenericParsedStatement<T> implements IParsedStatement<T> {

	private T statement;

	@Override
	public T getStatement() {
		return this.statement;
	}

	@Override
	public void setStatement(T statement) {
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
