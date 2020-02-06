import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Test;

import engineering.swat.typhonql.ast.ASTConversionException;
import engineering.swat.typhonql.ast.Expr;
import engineering.swat.typhonql.ast.Expr.Lst;
import engineering.swat.typhonql.ast.KeyVal;
import engineering.swat.typhonql.ast.NullASTVisitor;
import engineering.swat.typhonql.ast.Obj;
import engineering.swat.typhonql.ast.Obj.Literal;
import engineering.swat.typhonql.ast.Request;
import engineering.swat.typhonql.ast.Statement.Insert;
import engineering.swat.typhonql.ast.TopDownASTVisitor;
import engineering.swat.typhonql.ast.TyphonQLASTParser;

public class ASTVisitorTests {
	
	public static void main(String[] args) throws Exception {
		new ASTVisitorTests().testRegularVisitor();
	}
	
	
	public void testRegularVisitor() throws Exception {
		Request ast = parseAST();
		if (ast.getStm() instanceof Insert) {
			String dmlCommand = "insert"; 
			for (Obj obj : ast.getStm().getObjs()) {
				String entityName = obj.getEntity().getString();
				dmlCommand += " " + entityName + " { ";
				
				for (KeyVal keyval : obj.getKeyVals()) {
					String attrName = keyval.getKey().getString();
					String expr = keyval.getValue().toString();
					dmlCommand += attrName + " : " + expr;
				}
				dmlCommand += " }";
				System.out.println(dmlCommand);
			}
			
		}
		System.out.println(ast.getStm().getObjs().get(0).getEntity());
		

		String productName = ast.getStm().accept(new NullASTVisitor<String>() {
			@Override
			public String visitStatementInsert(Insert x) {
				for (Obj o : x.getObjs()) {
					for (KeyVal keyVal : o.getKeyVals()) {
                        if (keyVal.getKey().getString().equals("products")) {
                            return keyVal.getValue().accept(this);
                        }
					}
				}
				return null;
			}
			
			@Override
			public String visitExprLst(Lst x) {
				for (Obj obj : x.getEntries()) {
					String result = obj.accept(this);
					if (result != null) {
						return result;
					}
				}
				return null;
			}
			
			@Override
			public String visitObjLiteral(Literal x) {
				if (x.getEntity().getString().equals("Product")) {
					for (KeyVal keyVal : x.getKeyVals()) {
						if (keyVal.getKey().getString().equals("name") && keyVal.getValue().isStr()) {
							return keyVal.getValue().getStrValue().getString();
						}
					}
				}
				return null;
			}
		});
		System.out.println("\"TV\"" + productName);
	}
	
	@Test
	void testName() throws Exception {
		Request ast = parseAST();
		Set<String> ints = new HashSet<>();
		List<Integer> listSize = new ArrayList<>();

		ast.accept(new TopDownASTVisitor() {
			@Override
			public Void visitExprLst(Lst x) {
				listSize.add(x.getEntries().size());
				// call super method to continue visiting entries of this list
				// else return null
				return super.visitExprLst(x);
			}
			
			@Override
			public Void visitIntLexical(engineering.swat.typhonql.ast.Int.Lexical x) {
				ints.add(x.toString());
				return null;
			}
			
		});
		//assertEquals(Collections.singleton("32"), ints);
	//t	assertEquals(Collections.singletonList(1), listSize);
	}
	
	

	private Request parseAST() throws ASTConversionException {
		return TyphonQLASTParser.parseTyphonQLRequest(("insert Order { "
				+ "totalAmount: 32, "
				+ "products: [Product { name: \"TV\" } ]"
				+ "}").toCharArray());
	}

}