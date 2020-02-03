/**
 */
package typhonql;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Queries</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link typhonql.Queries#getQueries <em>Queries</em>}</li>
 * </ul>
 *
 * @see typhonql.TyphonqlPackage#getQueries()
 * @model
 * @generated
 */
public interface Queries extends EObject {
	/**
	 * Returns the value of the '<em><b>Queries</b></em>' containment reference list.
	 * The list contents are of type {@link typhonql.Query}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Queries</em>' containment reference list.
	 * @see typhonql.TyphonqlPackage#getQueries_Queries()
	 * @model containment="true"
	 * @generated
	 */
	EList<Query> getQueries();

} // Queries
