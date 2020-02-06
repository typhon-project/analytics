/*
 * generated by Xtext 2.20.0
 */
package org.xtext.typhonql.ide.contentassist.antlr;

import com.google.common.collect.ImmutableMap;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import java.util.Map;
import org.eclipse.xtext.AbstractElement;
import org.eclipse.xtext.ide.editor.contentassist.antlr.AbstractContentAssistParser;
import org.xtext.typhonql.ide.contentassist.antlr.internal.InternalTyphonQLParser;
import org.xtext.typhonql.services.TyphonQLGrammarAccess;

public class TyphonQLParser extends AbstractContentAssistParser {

	@Singleton
	public static final class NameMappings {
		
		private final Map<AbstractElement, String> mappings;
		
		@Inject
		public NameMappings(TyphonQLGrammarAccess grammarAccess) {
			ImmutableMap.Builder<AbstractElement, String> builder = ImmutableMap.builder();
			init(builder, grammarAccess);
			this.mappings = builder.build();
		}
		
		public String getRuleName(AbstractElement element) {
			return mappings.get(element);
		}
		
		private static void init(ImmutableMap.Builder<AbstractElement, String> builder, TyphonQLGrammarAccess grammarAccess) {
			builder.put(grammarAccess.getQueriesAccess().getQueriesAlternatives_0(), "rule__Queries__QueriesAlternatives_0");
			builder.put(grammarAccess.getExprAccess().getExprsAlternatives_0(), "rule__Expr__ExprsAlternatives_0");
			builder.put(grammarAccess.getVariableyAccess().getValsAlternatives_0(), "rule__Variabley__ValsAlternatives_0");
			builder.put(grammarAccess.getUpdateAccess().getGroup(), "rule__Update__Group__0");
			builder.put(grammarAccess.getUpdateAccess().getGroup_3(), "rule__Update__Group_3__0");
			builder.put(grammarAccess.getUpdateAccess().getGroup_7(), "rule__Update__Group_7__0");
			builder.put(grammarAccess.getDeleteAccess().getGroup(), "rule__Delete__Group__0");
			builder.put(grammarAccess.getDeleteAccess().getGroup_3(), "rule__Delete__Group_3__0");
			builder.put(grammarAccess.getInsertAccess().getGroup(), "rule__Insert__Group__0");
			builder.put(grammarAccess.getInsertAccess().getGroup_2(), "rule__Insert__Group_2__0");
			builder.put(grammarAccess.getObjAccess().getGroup(), "rule__Obj__Group__0");
			builder.put(grammarAccess.getObjAccess().getGroup_0(), "rule__Obj__Group_0__0");
			builder.put(grammarAccess.getObjAccess().getGroup_4(), "rule__Obj__Group_4__0");
			builder.put(grammarAccess.getQueriesAccess().getQueriesAssignment(), "rule__Queries__QueriesAssignment");
			builder.put(grammarAccess.getUpdateAccess().getEidAssignment_1(), "rule__Update__EidAssignment_1");
			builder.put(grammarAccess.getUpdateAccess().getVidAssignment_2(), "rule__Update__VidAssignment_2");
			builder.put(grammarAccess.getUpdateAccess().getWhereAssignment_3_1(), "rule__Update__WhereAssignment_3_1");
			builder.put(grammarAccess.getUpdateAccess().getSetAssignment_6(), "rule__Update__SetAssignment_6");
			builder.put(grammarAccess.getUpdateAccess().getSetAssignment_7_1(), "rule__Update__SetAssignment_7_1");
			builder.put(grammarAccess.getDeleteAccess().getEidAssignment_1(), "rule__Delete__EidAssignment_1");
			builder.put(grammarAccess.getDeleteAccess().getVidAssignment_2(), "rule__Delete__VidAssignment_2");
			builder.put(grammarAccess.getDeleteAccess().getWhereAssignment_3_1(), "rule__Delete__WhereAssignment_3_1");
			builder.put(grammarAccess.getInsertAccess().getObjectsAssignment_1(), "rule__Insert__ObjectsAssignment_1");
			builder.put(grammarAccess.getInsertAccess().getObjectsAssignment_2_1(), "rule__Insert__ObjectsAssignment_2_1");
			builder.put(grammarAccess.getObjAccess().getLabelAssignment_0_1(), "rule__Obj__LabelAssignment_0_1");
			builder.put(grammarAccess.getObjAccess().getEidAssignment_1(), "rule__Obj__EidAssignment_1");
			builder.put(grammarAccess.getObjAccess().getSetAssignment_3(), "rule__Obj__SetAssignment_3");
			builder.put(grammarAccess.getObjAccess().getSetAssignment_4_1(), "rule__Obj__SetAssignment_4_1");
			builder.put(grammarAccess.getExprAccess().getExprsAssignment(), "rule__Expr__ExprsAssignment");
			builder.put(grammarAccess.getStringyAccess().getValsAssignment(), "rule__Stringy__ValsAssignment");
			builder.put(grammarAccess.getVariableyAccess().getValsAssignment(), "rule__Variabley__ValsAssignment");
		}
	}
	
	@Inject
	private NameMappings nameMappings;

	@Inject
	private TyphonQLGrammarAccess grammarAccess;

	@Override
	protected InternalTyphonQLParser createParser() {
		InternalTyphonQLParser result = new InternalTyphonQLParser(null);
		result.setGrammarAccess(grammarAccess);
		return result;
	}

	@Override
	protected String getRuleName(AbstractElement element) {
		return nameMappings.getRuleName(element);
	}

	@Override
	protected String[] getInitialHiddenTokens() {
		return new String[] { "RULE_WS", "RULE_ML_COMMENT", "RULE_SL_COMMENT" };
	}

	public TyphonQLGrammarAccess getGrammarAccess() {
		return this.grammarAccess;
	}

	public void setGrammarAccess(TyphonQLGrammarAccess grammarAccess) {
		this.grammarAccess = grammarAccess;
	}
	
	public NameMappings getNameMappings() {
		return nameMappings;
	}
	
	public void setNameMappings(NameMappings nameMappings) {
		this.nameMappings = nameMappings;
	}
}
