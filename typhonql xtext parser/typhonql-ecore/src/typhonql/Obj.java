/**
 */
package typhonql;

import java.lang.String;
import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Obj</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link typhonql.Obj#getLabel <em>Label</em>}</li>
 *   <li>{@link typhonql.Obj#getEid <em>Eid</em>}</li>
 *   <li>{@link typhonql.Obj#getSet <em>Set</em>}</li>
 * </ul>
 *
 * @see typhonql.TyphonqlPackage#getObj()
 * @model
 * @generated
 */
public interface Obj extends Expr {
	/**
	 * Returns the value of the '<em><b>Label</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Label</em>' attribute.
	 * @see #setLabel(String)
	 * @see typhonql.TyphonqlPackage#getObj_Label()
	 * @model
	 * @generated
	 */
	String getLabel();

	/**
	 * Sets the value of the '{@link typhonql.Obj#getLabel <em>Label</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Label</em>' attribute.
	 * @see #getLabel()
	 * @generated
	 */
	void setLabel(String value);

	/**
	 * Returns the value of the '<em><b>Eid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Eid</em>' attribute.
	 * @see #setEid(String)
	 * @see typhonql.TyphonqlPackage#getObj_Eid()
	 * @model required="true"
	 * @generated
	 */
	String getEid();

	/**
	 * Sets the value of the '{@link typhonql.Obj#getEid <em>Eid</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Eid</em>' attribute.
	 * @see #getEid()
	 * @generated
	 */
	void setEid(String value);

	/**
	 * Returns the value of the '<em><b>Set</b></em>' containment reference list.
	 * The list contents are of type {@link typhonql.Expr}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Set</em>' containment reference list.
	 * @see typhonql.TyphonqlPackage#getObj_Set()
	 * @model containment="true"
	 * @generated
	 */
	EList<Expr> getSet();

} // Obj
