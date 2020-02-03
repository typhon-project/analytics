/**
 */
package typhonql;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Insert</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link typhonql.Insert#getObjects <em>Objects</em>}</li>
 * </ul>
 *
 * @see typhonql.TyphonqlPackage#getInsert()
 * @model
 * @generated
 */
public interface Insert extends Query {
	/**
	 * Returns the value of the '<em><b>Objects</b></em>' containment reference list.
	 * The list contents are of type {@link typhonql.Obj}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Objects</em>' containment reference list.
	 * @see typhonql.TyphonqlPackage#getInsert_Objects()
	 * @model containment="true"
	 * @generated
	 */
	EList<Obj> getObjects();

} // Insert
