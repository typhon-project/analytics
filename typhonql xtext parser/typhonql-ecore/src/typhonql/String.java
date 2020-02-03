/**
 */
package typhonql;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>String</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link typhonql.String#getVals <em>Vals</em>}</li>
 * </ul>
 *
 * @see typhonql.TyphonqlPackage#getString()
 * @model
 * @generated
 */
public interface String extends Expr {
	/**
	 * Returns the value of the '<em><b>Vals</b></em>' attribute list.
	 * The list contents are of type {@link java.lang.String}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Vals</em>' attribute list.
	 * @see typhonql.TyphonqlPackage#getString_Vals()
	 * @model
	 * @generated
	 */
	EList<java.lang.String> getVals();

} // String
