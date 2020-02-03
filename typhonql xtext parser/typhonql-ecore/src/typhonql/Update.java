/**
 */
package typhonql;

import java.lang.String;
import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Update</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link typhonql.Update#getEid <em>Eid</em>}</li>
 *   <li>{@link typhonql.Update#getVid <em>Vid</em>}</li>
 *   <li>{@link typhonql.Update#getWhere <em>Where</em>}</li>
 *   <li>{@link typhonql.Update#getSet <em>Set</em>}</li>
 * </ul>
 *
 * @see typhonql.TyphonqlPackage#getUpdate()
 * @model
 * @generated
 */
public interface Update extends Query {
	/**
	 * Returns the value of the '<em><b>Eid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Eid</em>' attribute.
	 * @see #setEid(String)
	 * @see typhonql.TyphonqlPackage#getUpdate_Eid()
	 * @model required="true"
	 * @generated
	 */
	String getEid();

	/**
	 * Sets the value of the '{@link typhonql.Update#getEid <em>Eid</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Eid</em>' attribute.
	 * @see #getEid()
	 * @generated
	 */
	void setEid(String value);

	/**
	 * Returns the value of the '<em><b>Vid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Vid</em>' attribute.
	 * @see #setVid(String)
	 * @see typhonql.TyphonqlPackage#getUpdate_Vid()
	 * @model required="true"
	 * @generated
	 */
	String getVid();

	/**
	 * Sets the value of the '{@link typhonql.Update#getVid <em>Vid</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Vid</em>' attribute.
	 * @see #getVid()
	 * @generated
	 */
	void setVid(String value);

	/**
	 * Returns the value of the '<em><b>Where</b></em>' containment reference list.
	 * The list contents are of type {@link typhonql.Expr}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Where</em>' containment reference list.
	 * @see typhonql.TyphonqlPackage#getUpdate_Where()
	 * @model containment="true"
	 * @generated
	 */
	EList<Expr> getWhere();

	/**
	 * Returns the value of the '<em><b>Set</b></em>' containment reference list.
	 * The list contents are of type {@link typhonql.Expr}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Set</em>' containment reference list.
	 * @see typhonql.TyphonqlPackage#getUpdate_Set()
	 * @model containment="true"
	 * @generated
	 */
	EList<Expr> getSet();

} // Update
