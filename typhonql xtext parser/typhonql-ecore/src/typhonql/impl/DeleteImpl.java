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

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;
import typhonql.Delete;
import typhonql.Expr;
import typhonql.TyphonqlPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Delete</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link typhonql.impl.DeleteImpl#getEid <em>Eid</em>}</li>
 *   <li>{@link typhonql.impl.DeleteImpl#getVid <em>Vid</em>}</li>
 *   <li>{@link typhonql.impl.DeleteImpl#getWhere <em>Where</em>}</li>
 * </ul>
 *
 * @generated
 */
public class DeleteImpl extends QueryImpl implements Delete {
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
	 * The default value of the '{@link #getVid() <em>Vid</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getVid()
	 * @generated
	 * @ordered
	 */
	protected static final String VID_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getVid() <em>Vid</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getVid()
	 * @generated
	 * @ordered
	 */
	protected String vid = VID_EDEFAULT;

	/**
	 * The cached value of the '{@link #getWhere() <em>Where</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getWhere()
	 * @generated
	 * @ordered
	 */
	protected EList<Expr> where;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected DeleteImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return TyphonqlPackage.Literals.DELETE;
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
			eNotify(new ENotificationImpl(this, Notification.SET, TyphonqlPackage.DELETE__EID, oldEid, eid));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getVid() {
		return vid;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setVid(String newVid) {
		String oldVid = vid;
		vid = newVid;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, TyphonqlPackage.DELETE__VID, oldVid, vid));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EList<Expr> getWhere() {
		if (where == null) {
			where = new EObjectContainmentEList<Expr>(Expr.class, this, TyphonqlPackage.DELETE__WHERE);
		}
		return where;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case TyphonqlPackage.DELETE__WHERE:
				return ((InternalEList<?>)getWhere()).basicRemove(otherEnd, msgs);
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
			case TyphonqlPackage.DELETE__EID:
				return getEid();
			case TyphonqlPackage.DELETE__VID:
				return getVid();
			case TyphonqlPackage.DELETE__WHERE:
				return getWhere();
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
			case TyphonqlPackage.DELETE__EID:
				setEid((String)newValue);
				return;
			case TyphonqlPackage.DELETE__VID:
				setVid((String)newValue);
				return;
			case TyphonqlPackage.DELETE__WHERE:
				getWhere().clear();
				getWhere().addAll((Collection<? extends Expr>)newValue);
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
			case TyphonqlPackage.DELETE__EID:
				setEid(EID_EDEFAULT);
				return;
			case TyphonqlPackage.DELETE__VID:
				setVid(VID_EDEFAULT);
				return;
			case TyphonqlPackage.DELETE__WHERE:
				getWhere().clear();
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
			case TyphonqlPackage.DELETE__EID:
				return EID_EDEFAULT == null ? eid != null : !EID_EDEFAULT.equals(eid);
			case TyphonqlPackage.DELETE__VID:
				return VID_EDEFAULT == null ? vid != null : !VID_EDEFAULT.equals(vid);
			case TyphonqlPackage.DELETE__WHERE:
				return where != null && !where.isEmpty();
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
		result.append(" (eid: ");
		result.append(eid);
		result.append(", vid: ");
		result.append(vid);
		result.append(')');
		return result.toString();
	}

} //DeleteImpl
