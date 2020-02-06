/**
 */
package typhonql;

import java.lang.String;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Variable</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link typhonql.Variable#getVals <em>Vals</em>}</li>
 * </ul>
 *
 * @see typhonql.TyphonqlPackage#getVariable()
 * @model
 * @generated
 */
public interface Variable extends Expr {
	/**
	 * Returns the value of the '<em><b>Vals</b></em>' attribute list.
	 * The list contents are of type {@link java.lang.String}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Vals</em>' attribute list.
	 * @see typhonql.TyphonqlPackage#getVariable_Vals()
	 * @model
	 * @generated
	 */
	EList<String> getVals();

} // Variable
