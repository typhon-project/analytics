package ac.uk.york.typhon.analytics.commons.datatypes.wrapper;

import ac.uk.york.typhon.analytics.commons.datatypes.wrapper.parsedstatement.DeleteGenericParsedStatement;
import ac.uk.york.typhon.analytics.commons.datatypes.wrapper.parsedstatement.IParsedStatement;

public class ParsedStatementFactory {
	
	
	  public static <T> IParsedStatement<T> getHandler() {
//	        return new GenericParsedStatement<T>();
		  return new DeleteGenericParsedStatement<T>();
	      }

//	enum ValidHandler {
//
//		Delete {
//			@Override
//			IParsedStatement<Delete> make() {
//				return new DeleteParsedStatement();
//			}
//		},
//		Insert {
//			@Override
//			IParsedStatement<Insert> make() {
//				return new InsertParsedStatement();
//			}
//		},
//		Select {
//			@Override
//			IParsedStatement<Select> make() {
//				return new SelectParsedStatement();
//			}
//		},
//		Update {
//			@Override
//			IParsedStatement<Update> make() {
//				return new UpdateParsedStatement();
//			}
//		}
//		
//		
//		
//		;
//
//		abstract <T> IParsedStatement<T> make();
//	}
//
//	
//	
//	public static <T> IParsedStatement<T> getHandler(Class<T> clazz) {
//		if (clazz == Delete.class) {
//			return ValidHandler.Delete.make();
//		}
//		if (clazz == Insert.class) {
//			return ValidHandler.Insert.make();
//		}
//		if (clazz == Select.class) {
//			return ValidHandler.Select.make();
//		}
//		if (clazz == Update.class) {
//			return ValidHandler.Update.make();
//		}
//		return null;
//	}
	
	
//	private static Map<StatementType, Class<? extends ParsedStatement>> statementTypeWrapperClassMap = new HashMap<StatementType, Class<? extends ParsedStatement>>();
//
//	static {
//		registerParsedStatements();
//	}
//
//	public static void registerParsedStatements() {
//
//		ParsedStatementFactory.register(StatementType.DELETE,
//				DeleteParsedStatement.class);
//		ParsedStatementFactory.register(StatementType.INSERT,
//				InsertParsedStatement.class);
//		ParsedStatementFactory.register(StatementType.SELECT,
//				SelectParsedStatement.class);
//		ParsedStatementFactory.register(StatementType.UPDATE,
//				UpdateParsedStatement.class);
//
//	}
//
//	public static void register(StatementType statementType,
//			Class<? extends ParsedStatement> statementWrapperClass) {
//		statementTypeWrapperClassMap.put(statementType, statementWrapperClass);
//
//	}
//
//	private static Class<? extends ParsedStatement> retrieveParsedStatementClass(
//			Statement statement) throws InstantiationException,
//			IllegalAccessException {
//
//		String parsedStatmentName = statement.getClass().getSimpleName()
//				.toUpperCase();
//		Class<? extends ParsedStatement> parsedStatementClass = statementTypeWrapperClassMap
//				.get(StatementType.valueOf(parsedStatmentName));
//		return parsedStatementClass;
//
//	}
//
//	public static <U> void doIt(U object) {
//
//	}
//
//	public static ParsedStatement getInstance(String query)
//			throws InstantiationException, IllegalAccessException {
//		ParsedStatement parsedStatement = null;
//		Statement statement;
//		try {
//			statement = CCJSqlParserUtil.parse(query);
//
//			parsedStatement = retrieveParsedStatementClass(statement)
//					.newInstance();
//			parsedStatement.setStatement(statement);
//
//			// switch (parsedStatmentName) {
//			// case "INSERT":
//			// // InsertQueryStatement(statement);
//			// queryStatement = StatementImplementationRegistrar
//			// .getInstance(StatementType .INSERT);
//			//
//			// break;
//			// case "SELECT":
//			//
//			// queryStatement = new SelectQueryStatement(statement);
//			//
//			// break;
//			// case "UPDATE":
//			//
//			// queryStatement = new UpdateQueryStatement(statement);
//			//
//			// break;
//			// case "DELETE":
//			//
//			// queryStatement = new DeleteQueryStatement(statement);
//			//
//			// break;
//			//
//			// }
//
//		} catch (JSQLParserException e) {
//
//			e.printStackTrace();
//		}
//		return parsedStatement;
//	}

}
