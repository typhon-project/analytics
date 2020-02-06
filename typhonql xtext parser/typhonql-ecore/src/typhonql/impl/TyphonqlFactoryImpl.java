/**
 */
package typhonql.impl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.EFactoryImpl;

import org.eclipse.emf.ecore.plugin.EcorePlugin;

import typhonql.Delete;
import typhonql.Expr;
import typhonql.Insert;
import typhonql.Obj;
import typhonql.Queries;
import typhonql.TyphonqlFactory;
import typhonql.TyphonqlPackage;
import typhonql.Update;
import typhonql.Variable;
import typhonql.*;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class TyphonqlFactoryImpl extends EFactoryImpl implements TyphonqlFactory {
	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static TyphonqlFactory init() {
		try {
			TyphonqlFactory theTyphonqlFactory = (TyphonqlFactory)EPackage.Registry.INSTANCE.getEFactory(TyphonqlPackage.eNS_URI);
			if (theTyphonqlFactory != null) {
				return theTyphonqlFactory;
			}
		}
		catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new TyphonqlFactoryImpl();
	}

	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TyphonqlFactoryImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EObject create(EClass eClass) {
		switch (eClass.getClassifierID()) {
			case TyphonqlPackage.QUERIES: return createQueries();
			case TyphonqlPackage.INSERT: return createInsert();
			case TyphonqlPackage.DELETE: return createDelete();
			case TyphonqlPackage.UPDATE: return createUpdate();
			case TyphonqlPackage.EXPR: return createExpr();
			case TyphonqlPackage.OBJ: return createObj();
			case TyphonqlPackage.STRING: return createString();
			case TyphonqlPackage.VARIABLE: return createVariable();
			default:
				throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Queries createQueries() {
		QueriesImpl queries = new QueriesImpl();
		return queries;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Insert createInsert() {
		InsertImpl insert = new InsertImpl();
		return insert;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Delete createDelete() {
		DeleteImpl delete = new DeleteImpl();
		return delete;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Update createUpdate() {
		UpdateImpl update = new UpdateImpl();
		return update;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Expr createExpr() {
		ExprImpl expr = new ExprImpl();
		return expr;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Obj createObj() {
		ObjImpl obj = new ObjImpl();
		return obj;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public typhonql.String createString() {
		StringImpl string = new StringImpl();
		return string;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Variable createVariable() {
		VariableImpl variable = new VariableImpl();
		return variable;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public TyphonqlPackage getTyphonqlPackage() {
		return (TyphonqlPackage)getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static TyphonqlPackage getPackage() {
		return TyphonqlPackage.eINSTANCE;
	}

} //TyphonqlFactoryImpl
