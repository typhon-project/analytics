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
import typhonql.KeyVal;
import typhonql.TyphonqlPackage;
import typhonql.Update;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Update</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link typhonql.impl.UpdateImpl#getEid <em>Eid</em>}</li>
 *   <li>{@link typhonql.impl.UpdateImpl#getVid <em>Vid</em>}</li>
 *   <li>{@link typhonql.impl.UpdateImpl#getWhere <em>Where</em>}</li>
 *   <li>{@link typhonql.impl.UpdateImpl#getSet <em>Set</em>}</li>
 * </ul>
 *
 * @generated
 */
public class UpdateImpl extends QueryImpl implements Update {
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
	protected UpdateImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return TyphonqlPackage.Literals.UPDATE;
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
			eNotify(new ENotificationImpl(this, Notification.SET, TyphonqlPackage.UPDATE__EID, oldEid, eid));
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
			eNotify(new ENotificationImpl(this, Notification.SET, TyphonqlPackage.UPDATE__VID, oldVid, vid));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EList<Expr> getWhere() {
		if (where == null) {
			where = new EObjectContainmentEList<Expr>(Expr.class, this, TyphonqlPackage.UPDATE__WHERE);
		}
		return where;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EList<Expr> getSet() {
		if (set == null) {
			set = new EObjectContainmentEList<Expr>(Expr.class, this, TyphonqlPackage.UPDATE__SET);
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
			case TyphonqlPackage.UPDATE__WHERE:
				return ((InternalEList<?>)getWhere()).basicRemove(otherEnd, msgs);
			case TyphonqlPackage.UPDATE__SET:
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
			case TyphonqlPackage.UPDATE__EID:
				return getEid();
			case TyphonqlPackage.UPDATE__VID:
				return getVid();
			case TyphonqlPackage.UPDATE__WHERE:
				return getWhere();
			case TyphonqlPackage.UPDATE__SET:
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
			case TyphonqlPackage.UPDATE__EID:
				setEid((String)newValue);
				return;
			case TyphonqlPackage.UPDATE__VID:
				setVid((String)newValue);
				return;
			case TyphonqlPackage.UPDATE__WHERE:
				getWhere().clear();
				getWhere().addAll((Collection<? extends Expr>)newValue);
				return;
			case TyphonqlPackage.UPDATE__SET:
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
			case TyphonqlPackage.UPDATE__EID:
				setEid(EID_EDEFAULT);
				return;
			case TyphonqlPackage.UPDATE__VID:
				setVid(VID_EDEFAULT);
				return;
			case TyphonqlPackage.UPDATE__WHERE:
				getWhere().clear();
				return;
			case TyphonqlPackage.UPDATE__SET:
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
			case TyphonqlPackage.UPDATE__EID:
				return EID_EDEFAULT == null ? eid != null : !EID_EDEFAULT.equals(eid);
			case TyphonqlPackage.UPDATE__VID:
				return VID_EDEFAULT == null ? vid != null : !VID_EDEFAULT.equals(vid);
			case TyphonqlPackage.UPDATE__WHERE:
				return where != null && !where.isEmpty();
			case TyphonqlPackage.UPDATE__SET:
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
		result.append(" (eid: ");
		result.append(eid);
		result.append(", vid: ");
		result.append(vid);
		result.append(')');
		return result.toString();
	}

} //UpdateImpl
