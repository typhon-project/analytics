/*
 * generated by Xtext 2.20.0
 */
package org.xtext.typhonql.serializer;

import com.google.inject.Inject;
import java.util.Set;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.xtext.Action;
import org.eclipse.xtext.Parameter;
import org.eclipse.xtext.ParserRule;
import org.eclipse.xtext.serializer.ISerializationContext;
import org.eclipse.xtext.serializer.sequencer.AbstractDelegatingSemanticSequencer;
import org.xtext.typhonql.services.TyphonQLGrammarAccess;
import typhonql.Delete;
import typhonql.Expr;
import typhonql.Insert;
import typhonql.Obj;
import typhonql.Queries;
import typhonql.TyphonqlPackage;
import typhonql.Update;

@SuppressWarnings("all")
public class TyphonQLSemanticSequencer extends AbstractDelegatingSemanticSequencer {

	@Inject
	private TyphonQLGrammarAccess grammarAccess;
	
	@Override
	public void sequence(ISerializationContext context, EObject semanticObject) {
		EPackage epackage = semanticObject.eClass().getEPackage();
		ParserRule rule = context.getParserRule();
		Action action = context.getAssignedAction();
		Set<Parameter> parameters = context.getEnabledBooleanParameters();
		if (epackage == TyphonqlPackage.eINSTANCE)
			switch (semanticObject.eClass().getClassifierID()) {
			case TyphonqlPackage.DELETE:
				sequence_Delete(context, (Delete) semanticObject); 
				return; 
			case TyphonqlPackage.EXPR:
				sequence_Expr(context, (Expr) semanticObject); 
				return; 
			case TyphonqlPackage.INSERT:
				sequence_Insert(context, (Insert) semanticObject); 
				return; 
			case TyphonqlPackage.OBJ:
				sequence_Obj(context, (Obj) semanticObject); 
				return; 
			case TyphonqlPackage.QUERIES:
				sequence_Queries(context, (Queries) semanticObject); 
				return; 
			case TyphonqlPackage.STRING:
				sequence_Stringy(context, (typhonql.String) semanticObject); 
				return; 
			case TyphonqlPackage.UPDATE:
				sequence_Update(context, (Update) semanticObject); 
				return; 
			}
		if (errorAcceptor != null)
			errorAcceptor.accept(diagnosticProvider.createInvalidContextOrTypeDiagnostic(semanticObject, context));
	}
	
	/**
	 * Contexts:
	 *     Delete returns Delete
	 *
	 * Constraint:
	 *     (eid=ID vid=ID where+=Expr?)
	 */
	protected void sequence_Delete(ISerializationContext context, Delete semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Contexts:
	 *     Expr returns Expr
	 *
	 * Constraint:
	 *     (exprs+=Stringy | exprs+=Obj)+
	 */
	protected void sequence_Expr(ISerializationContext context, Expr semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Contexts:
	 *     Insert returns Insert
	 *
	 * Constraint:
	 *     (objects+=Obj objects+=Obj*)
	 */
	protected void sequence_Insert(ISerializationContext context, Insert semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Contexts:
	 *     Obj returns Obj
	 *
	 * Constraint:
	 *     (label=ID? eid=ID set+=Expr? set+=Expr*)
	 */
	protected void sequence_Obj(ISerializationContext context, Obj semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Contexts:
	 *     Queries returns Queries
	 *
	 * Constraint:
	 *     (queries+=Insert | queries+=Delete | queries+=Update)+
	 */
	protected void sequence_Queries(ISerializationContext context, Queries semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Contexts:
	 *     Stringy returns String
	 *
	 * Constraint:
	 *     (vals+=VALUE_TERMINAL | vals+=STRING | vals+=ID | vals+=ANY_OTHER)
	 */
	protected void sequence_Stringy(ISerializationContext context, typhonql.String semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Contexts:
	 *     Update returns Update
	 *
	 * Constraint:
	 *     (eid=ID vid=ID where+=Expr? set+=Expr? set+=Expr*)
	 */
	protected void sequence_Update(ISerializationContext context, Update semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
}
