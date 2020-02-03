/**
 */
package typhonql;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Expr</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link typhonql.Expr#getExprs <em>Exprs</em>}</li>
 * </ul>
 *
 * @see typhonql.TyphonqlPackage#getExpr()
 * @model
 * @generated
 */
public interface Expr extends EObject {

	/**
	 * Returns the value of the '<em><b>Exprs</b></em>' containment reference list.
	 * The list contents are of type {@link typhonql.Expr}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Exprs</em>' containment reference list.
	 * @see typhonql.TyphonqlPackage#getExpr_Exprs()
	 * @model containment="true"
	 * @generated
	 */
	EList<Expr> getExprs();
} // Expr
