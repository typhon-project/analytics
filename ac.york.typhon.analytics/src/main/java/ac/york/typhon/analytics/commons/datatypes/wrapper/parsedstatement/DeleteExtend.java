package ac.york.typhon.analytics.commons.datatypes.wrapper.parsedstatement;

import net.sf.jsqlparser.statement.delete.Delete;

public class DeleteExtend  extends Delete{
	
	  public void accept(Visitor v) {
	        v.visit(this);
	    }

	    public String getFOO() {
	        return "FOO";
	    }

}
