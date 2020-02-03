/**
 */
package typhonql.impl;

import java.lang.String;
import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

import org.eclipse.emf.ecore.util.EDataTypeUniqueEList;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.EObjectResolvingEList;
import org.eclipse.emf.ecore.util.InternalEList;
import typhonql.Expr;
import typhonql.Obj;
import typhonql.TyphonqlPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Obj</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link typhonql.impl.ObjImpl#getLabel <em>Label</em>}</li>
 *   <li>{@link typhonql.impl.ObjImpl#getEid <em>Eid</em>}</li>
 *   <li>{@link typhonql.impl.ObjImpl#getSet <em>Set</em>}</li>
 * </ul>
 *
 * @generated
 */
public class ObjImpl extends ExprImpl implements Obj {
	/**
	 * The default value of the '{@link #getLabel() <em>Label</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLabel()
	 * @generated
	 * @ordered
	 */
	protected static final String LABEL_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getLabel() <em>Label</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLabel()
	 * @generated
	 * @ordered
	 */
	protected String label = LABEL_EDEFAULT;

	/**
	 * The default value of the '{@link #getEid() <em>Eid</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getEid()
	 * @generated
	 * @ordered
	 */
	protected static final String EID_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getEid() <em>Eid</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getEid()
	 * @generated
	 * @ordered
	 */
	protected String eid = EID_EDEFAULT;

	/**
	 * The cached value of the '{@link #getSet() <em>Set</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSet()
	 * @generated
	 * @ordered
	 */
	protected EList<Expr> set;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ObjImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return TyphonqlPackage.Literals.OBJ;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getLabel() {
		return label;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setLabel(String newLabel) {
		String oldLabel = label;
		label = newLabel;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, TyphonqlPackage.OBJ__LABEL, oldLabel, label));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getEid() {
		return eid;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setEid(String newEid) {
		String oldEid = eid;
		eid = newEid;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, TyphonqlPackage.OBJ__EID, oldEid, eid));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EList<Expr> getSet() {
		if (set == null) {
			set = new EObjectContainmentEList<Expr>(Expr.class, this, TyphonqlPackage.OBJ__SET);
		}
		return set;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case TyphonqlPackage.OBJ__SET:
				return ((InternalEList<?>)getSet()).basicRemove(otherEnd, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case TyphonqlPackage.OBJ__LABEL:
				return getLabel();
			case TyphonqlPackage.OBJ__EID:
				return getEid();
			case TyphonqlPackage.OBJ__SET:
				return getSet();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case TyphonqlPackage.OBJ__LABEL:
				setLabel((String)newValue);
				return;
			case TyphonqlPackage.OBJ__EID:
				setEid((String)newValue);
				return;
			case TyphonqlPackage.OBJ__SET:
				getSet().clear();
				getSet().addAll((Collection<? extends Expr>)newValue);
				return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
			case TyphonqlPackage.OBJ__LABEL:
				setLabel(LABEL_EDEFAULT);
				return;
			case TyphonqlPackage.OBJ__EID:
				setEid(EID_EDEFAULT);
				return;
			case TyphonqlPackage.OBJ__SET:
				getSet().clear();
				return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
			case TyphonqlPackage.OBJ__LABEL:
				return LABEL_EDEFAULT == null ? label != null : !LABEL_EDEFAULT.equals(label);
			case TyphonqlPackage.OBJ__EID:
				return EID_EDEFAULT == null ? eid != null : !EID_EDEFAULT.equals(eid);
			case TyphonqlPackage.OBJ__SET:
				return set != null && !set.isEmpty();
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuilder result = new StringBuilder(super.toString());
		result.append(" (label: ");
		result.append(label);
		result.append(", eid: ");
		result.append(eid);
		result.append(')');
		return result.toString();
	}

} //ObjImpl
