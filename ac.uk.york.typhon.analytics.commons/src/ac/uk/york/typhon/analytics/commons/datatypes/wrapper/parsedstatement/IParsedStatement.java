package ac.uk.york.typhon.analytics.commons.datatypes.wrapper.parsedstatement;

import net.sf.jsqlparser.statement.Statement;



public interface IParsedStatement<T> {

//	void handle(T obj);

	public T getStatement();

	public void setStatement(T statement);

}




//public abstract class ParsedStatement {
//
//	
//	public abstract Statement getStatement() ;
//
//	public abstract void setStatement(Statement statement) ;
//
//
//
//}
