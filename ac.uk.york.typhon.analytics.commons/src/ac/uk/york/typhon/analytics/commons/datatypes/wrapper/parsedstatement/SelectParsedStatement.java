package ac.uk.york.typhon.analytics.commons.datatypes.wrapper.parsedstatement;

import net.sf.jsqlparser.statement.Statement;
import net.sf.jsqlparser.statement.select.Select;

public final class SelectParsedStatement implements IParsedStatement<Select> {

	private Select statement;

	@Override
	public Select getStatement() {
		return this.statement;
	}

	@Override
	public void setStatement(Select statement) {
		this.statement = (Select) statement;
		
	}
	

//	@Override
//	public Select getStatement() {
//		return this.statement;
//
//	}
//	
//	@Override
//	public void setStatement(Statement statement) {
//		this.statement = (Select) statement;
//	}
}
