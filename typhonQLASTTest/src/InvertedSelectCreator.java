import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import engineering.swat.typhonql.ast.ASTConversionException;
import engineering.swat.typhonql.ast.Expr;
import engineering.swat.typhonql.ast.KeyVal;
import engineering.swat.typhonql.ast.Obj;
import engineering.swat.typhonql.ast.Request;
import engineering.swat.typhonql.ast.Statement;
import engineering.swat.typhonql.ast.Statement.Delete;
import engineering.swat.typhonql.ast.Statement.Update;
import engineering.swat.typhonql.ast.TyphonQLASTParser;
import engineering.swat.typhonql.ast.Where;

public class InvertedSelectCreator {

	public static void main(String[] a) throws Exception {

		Request r = new InvertedSelectCreator()
				.createRequest("update User u where u.name == \"alice\" set { name: \"bob\" }");
		new InvertedSelectCreator().createInvertedSelect(r);
//		System.out.println("");
//		r = new InvertedSelectCreator().createRequest(
//				"update Product p where p.name == \"TV\" && p.review == Review { name : \"NO\"} set { name: \"TVV\"}");
//		new InvertedSelectCreator().createInvertedSelect(r);
//		System.out.println("");
//		r = new InvertedSelectCreator().createRequest(
//				"delete Product p where p.name == \"TV\" && p.review == Review { name : \"NO\"}");
//		new InvertedSelectCreator().createInvertedSelect(r);
//		System.out.println("");
//		r = new InvertedSelectCreator().createRequest("update Order o where\n"
//				+ "	o.totalAmount == 32 && o.products == [ Product { name: \"TV\" } ] set { name: \"TVV\"}");
//		new InvertedSelectCreator().createInvertedSelect(r);
//		System.out.println("");
//		r = new InvertedSelectCreator().createRequest("update Order o where\n"
//				+ "		o.products == [ Product { name: \"TV\", reviews: [ Review { }, Review { name: \"Crash\" } ] } ] set { name: \"TVV\"}");
//		new InvertedSelectCreator().createInvertedSelect(r);
//		System.out.println("");
//		r = new InvertedSelectCreator().createRequest("update Order o where totalAmount == 23 && paidWith == @cc CreditCard { number: \"12345678\" } set { name: \"TVV\"}");
//		new InvertedSelectCreator().createInvertedSelect(r);
//		
		
	}

	public Request createRequest(String request) throws ASTConversionException {
		return TyphonQLASTParser.parseTyphonQLRequest((request).toCharArray());
	}
	
	public String createInvertedSelect(Request request) {
		Statement stm = request.getStm();
		String dmlCommand = "";
		System.out.println(request.yieldTree());
		if (request.getStm() instanceof Update || request.getStm() instanceof Delete) {
			String eid = stm.getBinding().getEntity().getString();
			String vid = stm.getBinding().getVar().getString();
			dmlCommand += "from " +  eid + " " + vid + " select * ";

			if (stm.hasWhere()) {
				Where where = stm.getWhere();
				dmlCommand += where.yieldTree();
			}
		}
		if (request.getStm() instanceof Update) {
			//TODO: If an update statement, we need to parse the SET for the updated values. An inverted select might not work as
			//		the clause might contain values that have been updated.
		}
		System.out.println(dmlCommand);
		return dmlCommand;
	}

	/*
	 * update User u where u.name == "alice" set { name: "bob" }
	 * 
	 */
	public void createStatementFromRequest(Request request) {
		Statement stm = request.getStm();
		String dmlCommand = "";
		if (request.getStm() instanceof Update || request.getStm() instanceof Delete) {
			dmlCommand += (request.getStm() instanceof Update) ? "update" : "delete";
			String eid = stm.getBinding().getEntity().getString();
			String vid = stm.getBinding().getVar().getString();
			dmlCommand += " " + eid + " " + vid;

			if (stm.hasWhere()) {

				dmlCommand += " where";

				Where where = stm.getWhere();
				List<Expr> exprs = where.getClauses();
				for (Expr e : exprs) {
					dmlCommand += " " + flatten(e);
				}

			}

		}
		System.out.println(dmlCommand);
	}

	public String flatten(Expr e) {
		// System.out.println(toString(e));
		String ret = "";
		if (e.hasLhs()) {
			ret += flatten(e.getLhs());
		}
		ret += toString(e);
		if (e.hasRhs()) {
			ret += flatten(e.getRhs());
		}
		return ret;
	}

	public String toString(Expr e) {
		if (e.isStr())
			return e.getStrValue().getString();
		else if (e.isVar())
			return e.getVar().getString();
		else if (e.isInt())
			return e.getIntValue().getString();
		else if (e.isBool())
			return e.getBoolValue().getString();
		else if (e.isReal())
			return e.getRealValue().getString();
		else if (e.isDt())
			return e.getDtValue().getDateTime().getString();// TODO see if this works
		//
		else if (e.isAttr()) {
			String ret = "";
			if (e.hasVar())
				ret += e.getVar() + ".";
			ret += String.join(", ", e.getAttrs().stream().map(a -> a.getString()).collect(Collectors.toList()));
			return ret;
			//
		} else if (e.isCall())
			return "UNSUPPORTED";
		else if (e.isCustom())
			return "CUSTOM";
		//
		else if (e.isKey())
			return e.getName().getString();// TODO see if this works
		else if (e.isUuid())
			return e.getUuidValue().getString();
		//
		else if (e.isAdd()) {
			return " + ";
		} else if (e.isAnd()) {
			return " && ";
		} else if (e.isDiv()) {
			return " / ";
		} else if (e.isEq()) {
			return " == ";
		} else if (e.isGeq()) {
			return " >= ";
		} else if (e.isGt()) {
			return " > ";
		} else if (e.isIn()) {
			return " in ";
		} else if (e.isLeq()) {
			return " <= ";
		} else if (e.isLike()) {
			return " like ";
		} else if (e.isLt()) {
			return " < ";
		} else if (e.isMul()) {
			return " * ";
		} else if (e.isNeg()) {
			return " -";
		} else if (e.isNeq()) {
			return " != ";
		} else if (e.isNot()) {
			return " !";
		} else if (e.isNull()) {
			return " null ";
		} else if (e.isOr()) {
			return " || ";
		} else if (e.isPos()) {
			return " +";
		} else if (e.isSub()) {
			return " - ";
		} else if (e.isObj()) {
			return flattenObj(e.getObjValue());
		} else if (e.isLst()) {
			List<String> oList = new ArrayList<String>();
			for (Obj o : e.getEntries())
				oList.add(flattenObj(o));
			return "[ " + String.join(", ", oList) + " ]";
		} else
			return "ERROR";

	}

	private String flattenObj(Obj o) {
		String ret = "";
		String label = o.getLabelOpt() == null ? "" : o.getLabelOpt().getString();
		String eid = o.getEntity().getString();
		ret += (label == "" ? "" : label + " ") + "" + eid + " { " + getKeyVals(o) + " }";
		return ret;
	}

	private String getKeyVals(Obj o) {
		List<String> oList = new ArrayList<String>();
		for (KeyVal keyval : o.getKeyVals()) {
			String attrName = keyval.getKey().getString();
			String expr = flatten(keyval.getValue());
			String keyVal = attrName + " : " + expr;
			oList.add(keyVal);
		}
		return String.join(", ", oList);
	}

}