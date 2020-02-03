/**
 */
package typhonql;

import java.lang.String;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

/**
 * <!-- begin-user-doc -->
 * The <b>Package</b> for the model.
 * It contains accessors for the meta objects to represent
 * <ul>
 *   <li>each class,</li>
 *   <li>each feature of each class,</li>
 *   <li>each enum,</li>
 *   <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * @see typhonql.TyphonqlFactory
 * @model kind="package"
 * @generated
 */
public interface TyphonqlPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "typhonql";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "typhonql";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "typhonql";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	TyphonqlPackage eINSTANCE = typhonql.impl.TyphonqlPackageImpl.init();

	/**
	 * The meta object id for the '{@link typhonql.impl.QueriesImpl <em>Queries</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see typhonql.impl.QueriesImpl
	 * @see typhonql.impl.TyphonqlPackageImpl#getQueries()
	 * @generated
	 */
	int QUERIES = 0;

	/**
	 * The feature id for the '<em><b>Queries</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int QUERIES__QUERIES = 0;

	/**
	 * The number of structural features of the '<em>Queries</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int QUERIES_FEATURE_COUNT = 1;

	/**
	 * The meta object id for the '{@link typhonql.impl.QueryImpl <em>Query</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see typhonql.impl.QueryImpl
	 * @see typhonql.impl.TyphonqlPackageImpl#getQuery()
	 * @generated
	 */
	int QUERY = 1;

	/**
	 * The number of structural features of the '<em>Query</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int QUERY_FEATURE_COUNT = 0;

	/**
	 * The meta object id for the '{@link typhonql.impl.InsertImpl <em>Insert</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see typhonql.impl.InsertImpl
	 * @see typhonql.impl.TyphonqlPackageImpl#getInsert()
	 * @generated
	 */
	int INSERT = 2;

	/**
	 * The feature id for the '<em><b>Objects</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INSERT__OBJECTS = QUERY_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Insert</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INSERT_FEATURE_COUNT = QUERY_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link typhonql.impl.DeleteImpl <em>Delete</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see typhonql.impl.DeleteImpl
	 * @see typhonql.impl.TyphonqlPackageImpl#getDelete()
	 * @generated
	 */
	int DELETE = 3;

	/**
	 * The feature id for the '<em><b>Eid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DELETE__EID = QUERY_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Vid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DELETE__VID = QUERY_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Where</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DELETE__WHERE = QUERY_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the '<em>Delete</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DELETE_FEATURE_COUNT = QUERY_FEATURE_COUNT + 3;

	/**
	 * The meta object id for the '{@link typhonql.impl.UpdateImpl <em>Update</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see typhonql.impl.UpdateImpl
	 * @see typhonql.impl.TyphonqlPackageImpl#getUpdate()
	 * @generated
	 */
	int UPDATE = 4;

	/**
	 * The feature id for the '<em><b>Eid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UPDATE__EID = QUERY_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Vid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UPDATE__VID = QUERY_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Where</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UPDATE__WHERE = QUERY_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Set</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UPDATE__SET = QUERY_FEATURE_COUNT + 3;

	/**
	 * The number of structural features of the '<em>Update</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UPDATE_FEATURE_COUNT = QUERY_FEATURE_COUNT + 4;

	/**
	 * The meta object id for the '{@link typhonql.impl.ExprImpl <em>Expr</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see typhonql.impl.ExprImpl
	 * @see typhonql.impl.TyphonqlPackageImpl#getExpr()
	 * @generated
	 */
	int EXPR = 5;

	/**
	 * The feature id for the '<em><b>Exprs</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXPR__EXPRS = 0;

	/**
	 * The number of structural features of the '<em>Expr</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXPR_FEATURE_COUNT = 1;

	/**
	 * The meta object id for the '{@link typhonql.impl.ObjImpl <em>Obj</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see typhonql.impl.ObjImpl
	 * @see typhonql.impl.TyphonqlPackageImpl#getObj()
	 * @generated
	 */
	int OBJ = 6;

	/**
	 * The feature id for the '<em><b>Exprs</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OBJ__EXPRS = EXPR__EXPRS;

	/**
	 * The feature id for the '<em><b>Label</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OBJ__LABEL = EXPR_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Eid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OBJ__EID = EXPR_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Set</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OBJ__SET = EXPR_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the '<em>Obj</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OBJ_FEATURE_COUNT = EXPR_FEATURE_COUNT + 3;

	/**
	 * The meta object id for the '{@link typhonql.impl.StringImpl <em>String</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see typhonql.impl.StringImpl
	 * @see typhonql.impl.TyphonqlPackageImpl#getString()
	 * @generated
	 */
	int STRING = 7;

	/**
	 * The feature id for the '<em><b>Exprs</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STRING__EXPRS = EXPR__EXPRS;

	/**
	 * The feature id for the '<em><b>Vals</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STRING__VALS = EXPR_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>String</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STRING_FEATURE_COUNT = EXPR_FEATURE_COUNT + 1;

	/**
	 * Returns the meta object for class '{@link typhonql.Queries <em>Queries</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Queries</em>'.
	 * @see typhonql.Queries
	 * @generated
	 */
	EClass getQueries();

	/**
	 * Returns the meta object for the containment reference list '{@link typhonql.Queries#getQueries <em>Queries</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Queries</em>'.
	 * @see typhonql.Queries#getQueries()
	 * @see #getQueries()
	 * @generated
	 */
	EReference getQueries_Queries();

	/**
	 * Returns the meta object for class '{@link typhonql.Query <em>Query</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Query</em>'.
	 * @see typhonql.Query
	 * @generated
	 */
	EClass getQuery();

	/**
	 * Returns the meta object for class '{@link typhonql.Insert <em>Insert</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Insert</em>'.
	 * @see typhonql.Insert
	 * @generated
	 */
	EClass getInsert();

	/**
	 * Returns the meta object for the containment reference list '{@link typhonql.Insert#getObjects <em>Objects</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Objects</em>'.
	 * @see typhonql.Insert#getObjects()
	 * @see #getInsert()
	 * @generated
	 */
	EReference getInsert_Objects();

	/**
	 * Returns the meta object for class '{@link typhonql.Delete <em>Delete</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Delete</em>'.
	 * @see typhonql.Delete
	 * @generated
	 */
	EClass getDelete();

	/**
	 * Returns the meta object for the attribute '{@link typhonql.Delete#getEid <em>Eid</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Eid</em>'.
	 * @see typhonql.Delete#getEid()
	 * @see #getDelete()
	 * @generated
	 */
	EAttribute getDelete_Eid();

	/**
	 * Returns the meta object for the attribute '{@link typhonql.Delete#getVid <em>Vid</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Vid</em>'.
	 * @see typhonql.Delete#getVid()
	 * @see #getDelete()
	 * @generated
	 */
	EAttribute getDelete_Vid();

	/**
	 * Returns the meta object for the containment reference list '{@link typhonql.Delete#getWhere <em>Where</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Where</em>'.
	 * @see typhonql.Delete#getWhere()
	 * @see #getDelete()
	 * @generated
	 */
	EReference getDelete_Where();

	/**
	 * Returns the meta object for class '{@link typhonql.Update <em>Update</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Update</em>'.
	 * @see typhonql.Update
	 * @generated
	 */
	EClass getUpdate();

	/**
	 * Returns the meta object for the attribute '{@link typhonql.Update#getEid <em>Eid</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Eid</em>'.
	 * @see typhonql.Update#getEid()
	 * @see #getUpdate()
	 * @generated
	 */
	EAttribute getUpdate_Eid();

	/**
	 * Returns the meta object for the attribute '{@link typhonql.Update#getVid <em>Vid</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Vid</em>'.
	 * @see typhonql.Update#getVid()
	 * @see #getUpdate()
	 * @generated
	 */
	EAttribute getUpdate_Vid();

	/**
	 * Returns the meta object for the containment reference list '{@link typhonql.Update#getWhere <em>Where</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Where</em>'.
	 * @see typhonql.Update#getWhere()
	 * @see #getUpdate()
	 * @generated
	 */
	EReference getUpdate_Where();

	/**
	 * Returns the meta object for the containment reference list '{@link typhonql.Update#getSet <em>Set</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Set</em>'.
	 * @see typhonql.Update#getSet()
	 * @see #getUpdate()
	 * @generated
	 */
	EReference getUpdate_Set();

	/**
	 * Returns the meta object for class '{@link typhonql.Expr <em>Expr</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Expr</em>'.
	 * @see typhonql.Expr
	 * @generated
	 */
	EClass getExpr();

	/**
	 * Returns the meta object for the containment reference list '{@link typhonql.Expr#getExprs <em>Exprs</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Exprs</em>'.
	 * @see typhonql.Expr#getExprs()
	 * @see #getExpr()
	 * @generated
	 */
	EReference getExpr_Exprs();

	/**
	 * Returns the meta object for class '{@link typhonql.Obj <em>Obj</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Obj</em>'.
	 * @see typhonql.Obj
	 * @generated
	 */
	EClass getObj();

	/**
	 * Returns the meta object for the attribute '{@link typhonql.Obj#getLabel <em>Label</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Label</em>'.
	 * @see typhonql.Obj#getLabel()
	 * @see #getObj()
	 * @generated
	 */
	EAttribute getObj_Label();

	/**
	 * Returns the meta object for the attribute '{@link typhonql.Obj#getEid <em>Eid</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Eid</em>'.
	 * @see typhonql.Obj#getEid()
	 * @see #getObj()
	 * @generated
	 */
	EAttribute getObj_Eid();

	/**
	 * Returns the meta object for the containment reference list '{@link typhonql.Obj#getSet <em>Set</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Set</em>'.
	 * @see typhonql.Obj#getSet()
	 * @see #getObj()
	 * @generated
	 */
	EReference getObj_Set();

	/**
	 * Returns the meta object for class '{@link typhonql.String <em>String</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>String</em>'.
	 * @see typhonql.String
	 * @generated
	 */
	EClass getString();

	/**
	 * Returns the meta object for the attribute list '{@link typhonql.String#getVals <em>Vals</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Vals</em>'.
	 * @see typhonql.String#getVals()
	 * @see #getString()
	 * @generated
	 */
	EAttribute getString_Vals();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	TyphonqlFactory getTyphonqlFactory();

	/**
	 * <!-- begin-user-doc -->
	 * Defines literals for the meta objects that represent
	 * <ul>
	 *   <li>each class,</li>
	 *   <li>each feature of each class,</li>
	 *   <li>each enum,</li>
	 *   <li>and each data type</li>
	 * </ul>
	 * <!-- end-user-doc -->
	 * @generated
	 */
	interface Literals {
		/**
		 * The meta object literal for the '{@link typhonql.impl.QueriesImpl <em>Queries</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see typhonql.impl.QueriesImpl
		 * @see typhonql.impl.TyphonqlPackageImpl#getQueries()
		 * @generated
		 */
		EClass QUERIES = eINSTANCE.getQueries();

		/**
		 * The meta object literal for the '<em><b>Queries</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference QUERIES__QUERIES = eINSTANCE.getQueries_Queries();

		/**
		 * The meta object literal for the '{@link typhonql.impl.QueryImpl <em>Query</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see typhonql.impl.QueryImpl
		 * @see typhonql.impl.TyphonqlPackageImpl#getQuery()
		 * @generated
		 */
		EClass QUERY = eINSTANCE.getQuery();

		/**
		 * The meta object literal for the '{@link typhonql.impl.InsertImpl <em>Insert</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see typhonql.impl.InsertImpl
		 * @see typhonql.impl.TyphonqlPackageImpl#getInsert()
		 * @generated
		 */
		EClass INSERT = eINSTANCE.getInsert();

		/**
		 * The meta object literal for the '<em><b>Objects</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference INSERT__OBJECTS = eINSTANCE.getInsert_Objects();

		/**
		 * The meta object literal for the '{@link typhonql.impl.DeleteImpl <em>Delete</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see typhonql.impl.DeleteImpl
		 * @see typhonql.impl.TyphonqlPackageImpl#getDelete()
		 * @generated
		 */
		EClass DELETE = eINSTANCE.getDelete();

		/**
		 * The meta object literal for the '<em><b>Eid</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DELETE__EID = eINSTANCE.getDelete_Eid();

		/**
		 * The meta object literal for the '<em><b>Vid</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DELETE__VID = eINSTANCE.getDelete_Vid();

		/**
		 * The meta object literal for the '<em><b>Where</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DELETE__WHERE = eINSTANCE.getDelete_Where();

		/**
		 * The meta object literal for the '{@link typhonql.impl.UpdateImpl <em>Update</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see typhonql.impl.UpdateImpl
		 * @see typhonql.impl.TyphonqlPackageImpl#getUpdate()
		 * @generated
		 */
		EClass UPDATE = eINSTANCE.getUpdate();

		/**
		 * The meta object literal for the '<em><b>Eid</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute UPDATE__EID = eINSTANCE.getUpdate_Eid();

		/**
		 * The meta object literal for the '<em><b>Vid</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute UPDATE__VID = eINSTANCE.getUpdate_Vid();

		/**
		 * The meta object literal for the '<em><b>Where</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference UPDATE__WHERE = eINSTANCE.getUpdate_Where();

		/**
		 * The meta object literal for the '<em><b>Set</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference UPDATE__SET = eINSTANCE.getUpdate_Set();

		/**
		 * The meta object literal for the '{@link typhonql.impl.ExprImpl <em>Expr</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see typhonql.impl.ExprImpl
		 * @see typhonql.impl.TyphonqlPackageImpl#getExpr()
		 * @generated
		 */
		EClass EXPR = eINSTANCE.getExpr();

		/**
		 * The meta object literal for the '<em><b>Exprs</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference EXPR__EXPRS = eINSTANCE.getExpr_Exprs();

		/**
		 * The meta object literal for the '{@link typhonql.impl.ObjImpl <em>Obj</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see typhonql.impl.ObjImpl
		 * @see typhonql.impl.TyphonqlPackageImpl#getObj()
		 * @generated
		 */
		EClass OBJ = eINSTANCE.getObj();

		/**
		 * The meta object literal for the '<em><b>Label</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute OBJ__LABEL = eINSTANCE.getObj_Label();

		/**
		 * The meta object literal for the '<em><b>Eid</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute OBJ__EID = eINSTANCE.getObj_Eid();

		/**
		 * The meta object literal for the '<em><b>Set</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference OBJ__SET = eINSTANCE.getObj_Set();

		/**
		 * The meta object literal for the '{@link typhonql.impl.StringImpl <em>String</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see typhonql.impl.StringImpl
		 * @see typhonql.impl.TyphonqlPackageImpl#getString()
		 * @generated
		 */
		EClass STRING = eINSTANCE.getString();

		/**
		 * The meta object literal for the '<em><b>Vals</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute STRING__VALS = eINSTANCE.getString_Vals();

	}

} //TyphonqlPackage
