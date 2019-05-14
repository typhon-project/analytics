package ac.uk.york.typhon.analytics.commons.datatypes.wrapper.parsedstatement;

import net.sf.jsqlparser.statement.Statement;

public interface IParsedStatementDelet<T> {

//	void handle(T obj);

	public Statement getStatement();

	public void setStatement(T statement);

}


