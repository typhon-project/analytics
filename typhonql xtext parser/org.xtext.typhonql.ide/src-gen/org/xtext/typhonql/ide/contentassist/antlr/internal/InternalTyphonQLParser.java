package org.xtext.typhonql.ide.contentassist.antlr.internal;

import java.io.InputStream;
import org.eclipse.xtext.*;
import org.eclipse.xtext.parser.*;
import org.eclipse.xtext.parser.impl.*;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.parser.antlr.XtextTokenStream;
import org.eclipse.xtext.parser.antlr.XtextTokenStream.HiddenTokens;
import org.eclipse.xtext.ide.editor.contentassist.antlr.internal.AbstractInternalContentAssistParser;
import org.eclipse.xtext.ide.editor.contentassist.antlr.internal.DFA;
import org.xtext.typhonql.services.TyphonQLGrammarAccess;



import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
@SuppressWarnings("all")
public class InternalTyphonQLParser extends AbstractInternalContentAssistParser {
    public static final String[] tokenNames = new String[] {
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "RULE_VALUE_TERMINAL", "RULE_ID", "RULE_ANY_OTHER", "RULE_STRING", "RULE_INT", "RULE_ML_COMMENT", "RULE_SL_COMMENT", "RULE_WS", "'update'", "'set'", "'{'", "'}'", "';'", "'where'", "','", "'delete'", "'insert'", "'@'"
    };
    public static final int RULE_VALUE_TERMINAL=4;
    public static final int RULE_STRING=7;
    public static final int RULE_SL_COMMENT=10;
    public static final int T__19=19;
    public static final int T__15=15;
    public static final int T__16=16;
    public static final int T__17=17;
    public static final int T__18=18;
    public static final int T__12=12;
    public static final int T__13=13;
    public static final int T__14=14;
    public static final int EOF=-1;
    public static final int RULE_ID=5;
    public static final int RULE_WS=11;
    public static final int RULE_ANY_OTHER=6;
    public static final int RULE_INT=8;
    public static final int RULE_ML_COMMENT=9;
    public static final int T__20=20;
    public static final int T__21=21;

    // delegates
    // delegators


        public InternalTyphonQLParser(TokenStream input) {
            this(input, new RecognizerSharedState());
        }
        public InternalTyphonQLParser(TokenStream input, RecognizerSharedState state) {
            super(input, state);
             
        }
        

    public String[] getTokenNames() { return InternalTyphonQLParser.tokenNames; }
    public String getGrammarFileName() { return "InternalTyphonQL.g"; }


    	private TyphonQLGrammarAccess grammarAccess;

    	public void setGrammarAccess(TyphonQLGrammarAccess grammarAccess) {
    		this.grammarAccess = grammarAccess;
    	}

    	@Override
    	protected Grammar getGrammar() {
    		return grammarAccess.getGrammar();
    	}

    	@Override
    	protected String getValueForTokenName(String tokenName) {
    		return tokenName;
    	}



    // $ANTLR start "entryRuleQueries"
    // InternalTyphonQL.g:54:1: entryRuleQueries : ruleQueries EOF ;
    public final void entryRuleQueries() throws RecognitionException {
        try {
            // InternalTyphonQL.g:55:1: ( ruleQueries EOF )
            // InternalTyphonQL.g:56:1: ruleQueries EOF
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getQueriesRule()); 
            }
            pushFollow(FOLLOW_1);
            ruleQueries();

            state._fsp--;
            if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getQueriesRule()); 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRuleQueries"


    // $ANTLR start "ruleQueries"
    // InternalTyphonQL.g:63:1: ruleQueries : ( ( rule__Queries__QueriesAssignment )* ) ;
    public final void ruleQueries() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTyphonQL.g:67:2: ( ( ( rule__Queries__QueriesAssignment )* ) )
            // InternalTyphonQL.g:68:2: ( ( rule__Queries__QueriesAssignment )* )
            {
            // InternalTyphonQL.g:68:2: ( ( rule__Queries__QueriesAssignment )* )
            // InternalTyphonQL.g:69:3: ( rule__Queries__QueriesAssignment )*
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getQueriesAccess().getQueriesAssignment()); 
            }
            // InternalTyphonQL.g:70:3: ( rule__Queries__QueriesAssignment )*
            loop1:
            do {
                int alt1=2;
                int LA1_0 = input.LA(1);

                if ( (LA1_0==12||(LA1_0>=19 && LA1_0<=20)) ) {
                    alt1=1;
                }


                switch (alt1) {
            	case 1 :
            	    // InternalTyphonQL.g:70:4: rule__Queries__QueriesAssignment
            	    {
            	    pushFollow(FOLLOW_3);
            	    rule__Queries__QueriesAssignment();

            	    state._fsp--;
            	    if (state.failed) return ;

            	    }
            	    break;

            	default :
            	    break loop1;
                }
            } while (true);

            if ( state.backtracking==0 ) {
               after(grammarAccess.getQueriesAccess().getQueriesAssignment()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleQueries"


    // $ANTLR start "entryRuleUpdate"
    // InternalTyphonQL.g:79:1: entryRuleUpdate : ruleUpdate EOF ;
    public final void entryRuleUpdate() throws RecognitionException {
        try {
            // InternalTyphonQL.g:80:1: ( ruleUpdate EOF )
            // InternalTyphonQL.g:81:1: ruleUpdate EOF
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getUpdateRule()); 
            }
            pushFollow(FOLLOW_1);
            ruleUpdate();

            state._fsp--;
            if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getUpdateRule()); 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRuleUpdate"


    // $ANTLR start "ruleUpdate"
    // InternalTyphonQL.g:88:1: ruleUpdate : ( ( rule__Update__Group__0 ) ) ;
    public final void ruleUpdate() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTyphonQL.g:92:2: ( ( ( rule__Update__Group__0 ) ) )
            // InternalTyphonQL.g:93:2: ( ( rule__Update__Group__0 ) )
            {
            // InternalTyphonQL.g:93:2: ( ( rule__Update__Group__0 ) )
            // InternalTyphonQL.g:94:3: ( rule__Update__Group__0 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getUpdateAccess().getGroup()); 
            }
            // InternalTyphonQL.g:95:3: ( rule__Update__Group__0 )
            // InternalTyphonQL.g:95:4: rule__Update__Group__0
            {
            pushFollow(FOLLOW_2);
            rule__Update__Group__0();

            state._fsp--;
            if (state.failed) return ;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getUpdateAccess().getGroup()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleUpdate"


    // $ANTLR start "entryRuleDelete"
    // InternalTyphonQL.g:104:1: entryRuleDelete : ruleDelete EOF ;
    public final void entryRuleDelete() throws RecognitionException {
        try {
            // InternalTyphonQL.g:105:1: ( ruleDelete EOF )
            // InternalTyphonQL.g:106:1: ruleDelete EOF
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getDeleteRule()); 
            }
            pushFollow(FOLLOW_1);
            ruleDelete();

            state._fsp--;
            if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getDeleteRule()); 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRuleDelete"


    // $ANTLR start "ruleDelete"
    // InternalTyphonQL.g:113:1: ruleDelete : ( ( rule__Delete__Group__0 ) ) ;
    public final void ruleDelete() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTyphonQL.g:117:2: ( ( ( rule__Delete__Group__0 ) ) )
            // InternalTyphonQL.g:118:2: ( ( rule__Delete__Group__0 ) )
            {
            // InternalTyphonQL.g:118:2: ( ( rule__Delete__Group__0 ) )
            // InternalTyphonQL.g:119:3: ( rule__Delete__Group__0 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getDeleteAccess().getGroup()); 
            }
            // InternalTyphonQL.g:120:3: ( rule__Delete__Group__0 )
            // InternalTyphonQL.g:120:4: rule__Delete__Group__0
            {
            pushFollow(FOLLOW_2);
            rule__Delete__Group__0();

            state._fsp--;
            if (state.failed) return ;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getDeleteAccess().getGroup()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleDelete"


    // $ANTLR start "entryRuleInsert"
    // InternalTyphonQL.g:129:1: entryRuleInsert : ruleInsert EOF ;
    public final void entryRuleInsert() throws RecognitionException {
        try {
            // InternalTyphonQL.g:130:1: ( ruleInsert EOF )
            // InternalTyphonQL.g:131:1: ruleInsert EOF
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getInsertRule()); 
            }
            pushFollow(FOLLOW_1);
            ruleInsert();

            state._fsp--;
            if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getInsertRule()); 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRuleInsert"


    // $ANTLR start "ruleInsert"
    // InternalTyphonQL.g:138:1: ruleInsert : ( ( rule__Insert__Group__0 ) ) ;
    public final void ruleInsert() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTyphonQL.g:142:2: ( ( ( rule__Insert__Group__0 ) ) )
            // InternalTyphonQL.g:143:2: ( ( rule__Insert__Group__0 ) )
            {
            // InternalTyphonQL.g:143:2: ( ( rule__Insert__Group__0 ) )
            // InternalTyphonQL.g:144:3: ( rule__Insert__Group__0 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getInsertAccess().getGroup()); 
            }
            // InternalTyphonQL.g:145:3: ( rule__Insert__Group__0 )
            // InternalTyphonQL.g:145:4: rule__Insert__Group__0
            {
            pushFollow(FOLLOW_2);
            rule__Insert__Group__0();

            state._fsp--;
            if (state.failed) return ;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getInsertAccess().getGroup()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleInsert"


    // $ANTLR start "entryRuleObj"
    // InternalTyphonQL.g:154:1: entryRuleObj : ruleObj EOF ;
    public final void entryRuleObj() throws RecognitionException {
        try {
            // InternalTyphonQL.g:155:1: ( ruleObj EOF )
            // InternalTyphonQL.g:156:1: ruleObj EOF
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getObjRule()); 
            }
            pushFollow(FOLLOW_1);
            ruleObj();

            state._fsp--;
            if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getObjRule()); 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRuleObj"


    // $ANTLR start "ruleObj"
    // InternalTyphonQL.g:163:1: ruleObj : ( ( rule__Obj__Group__0 ) ) ;
    public final void ruleObj() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTyphonQL.g:167:2: ( ( ( rule__Obj__Group__0 ) ) )
            // InternalTyphonQL.g:168:2: ( ( rule__Obj__Group__0 ) )
            {
            // InternalTyphonQL.g:168:2: ( ( rule__Obj__Group__0 ) )
            // InternalTyphonQL.g:169:3: ( rule__Obj__Group__0 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getObjAccess().getGroup()); 
            }
            // InternalTyphonQL.g:170:3: ( rule__Obj__Group__0 )
            // InternalTyphonQL.g:170:4: rule__Obj__Group__0
            {
            pushFollow(FOLLOW_2);
            rule__Obj__Group__0();

            state._fsp--;
            if (state.failed) return ;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getObjAccess().getGroup()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleObj"


    // $ANTLR start "entryRuleExpr"
    // InternalTyphonQL.g:179:1: entryRuleExpr : ruleExpr EOF ;
    public final void entryRuleExpr() throws RecognitionException {
        try {
            // InternalTyphonQL.g:180:1: ( ruleExpr EOF )
            // InternalTyphonQL.g:181:1: ruleExpr EOF
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getExprRule()); 
            }
            pushFollow(FOLLOW_1);
            ruleExpr();

            state._fsp--;
            if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getExprRule()); 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRuleExpr"


    // $ANTLR start "ruleExpr"
    // InternalTyphonQL.g:188:1: ruleExpr : ( ( ( rule__Expr__ExprsAssignment ) ) ( ( rule__Expr__ExprsAssignment )* ) ) ;
    public final void ruleExpr() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTyphonQL.g:192:2: ( ( ( ( rule__Expr__ExprsAssignment ) ) ( ( rule__Expr__ExprsAssignment )* ) ) )
            // InternalTyphonQL.g:193:2: ( ( ( rule__Expr__ExprsAssignment ) ) ( ( rule__Expr__ExprsAssignment )* ) )
            {
            // InternalTyphonQL.g:193:2: ( ( ( rule__Expr__ExprsAssignment ) ) ( ( rule__Expr__ExprsAssignment )* ) )
            // InternalTyphonQL.g:194:3: ( ( rule__Expr__ExprsAssignment ) ) ( ( rule__Expr__ExprsAssignment )* )
            {
            // InternalTyphonQL.g:194:3: ( ( rule__Expr__ExprsAssignment ) )
            // InternalTyphonQL.g:195:4: ( rule__Expr__ExprsAssignment )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getExprAccess().getExprsAssignment()); 
            }
            // InternalTyphonQL.g:196:4: ( rule__Expr__ExprsAssignment )
            // InternalTyphonQL.g:196:5: rule__Expr__ExprsAssignment
            {
            pushFollow(FOLLOW_4);
            rule__Expr__ExprsAssignment();

            state._fsp--;
            if (state.failed) return ;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getExprAccess().getExprsAssignment()); 
            }

            }

            // InternalTyphonQL.g:199:3: ( ( rule__Expr__ExprsAssignment )* )
            // InternalTyphonQL.g:200:4: ( rule__Expr__ExprsAssignment )*
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getExprAccess().getExprsAssignment()); 
            }
            // InternalTyphonQL.g:201:4: ( rule__Expr__ExprsAssignment )*
            loop2:
            do {
                int alt2=2;
                int LA2_0 = input.LA(1);

                if ( ((LA2_0>=RULE_VALUE_TERMINAL && LA2_0<=RULE_STRING)||LA2_0==21) ) {
                    alt2=1;
                }


                switch (alt2) {
            	case 1 :
            	    // InternalTyphonQL.g:201:5: rule__Expr__ExprsAssignment
            	    {
            	    pushFollow(FOLLOW_4);
            	    rule__Expr__ExprsAssignment();

            	    state._fsp--;
            	    if (state.failed) return ;

            	    }
            	    break;

            	default :
            	    break loop2;
                }
            } while (true);

            if ( state.backtracking==0 ) {
               after(grammarAccess.getExprAccess().getExprsAssignment()); 
            }

            }


            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleExpr"


    // $ANTLR start "entryRuleStringy"
    // InternalTyphonQL.g:211:1: entryRuleStringy : ruleStringy EOF ;
    public final void entryRuleStringy() throws RecognitionException {
        try {
            // InternalTyphonQL.g:212:1: ( ruleStringy EOF )
            // InternalTyphonQL.g:213:1: ruleStringy EOF
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getStringyRule()); 
            }
            pushFollow(FOLLOW_1);
            ruleStringy();

            state._fsp--;
            if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getStringyRule()); 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRuleStringy"


    // $ANTLR start "ruleStringy"
    // InternalTyphonQL.g:220:1: ruleStringy : ( ( rule__Stringy__ValsAssignment ) ) ;
    public final void ruleStringy() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTyphonQL.g:224:2: ( ( ( rule__Stringy__ValsAssignment ) ) )
            // InternalTyphonQL.g:225:2: ( ( rule__Stringy__ValsAssignment ) )
            {
            // InternalTyphonQL.g:225:2: ( ( rule__Stringy__ValsAssignment ) )
            // InternalTyphonQL.g:226:3: ( rule__Stringy__ValsAssignment )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getStringyAccess().getValsAssignment()); 
            }
            // InternalTyphonQL.g:227:3: ( rule__Stringy__ValsAssignment )
            // InternalTyphonQL.g:227:4: rule__Stringy__ValsAssignment
            {
            pushFollow(FOLLOW_2);
            rule__Stringy__ValsAssignment();

            state._fsp--;
            if (state.failed) return ;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getStringyAccess().getValsAssignment()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleStringy"


    // $ANTLR start "entryRuleVariabley"
    // InternalTyphonQL.g:236:1: entryRuleVariabley : ruleVariabley EOF ;
    public final void entryRuleVariabley() throws RecognitionException {
        try {
            // InternalTyphonQL.g:237:1: ( ruleVariabley EOF )
            // InternalTyphonQL.g:238:1: ruleVariabley EOF
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getVariableyRule()); 
            }
            pushFollow(FOLLOW_1);
            ruleVariabley();

            state._fsp--;
            if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getVariableyRule()); 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRuleVariabley"


    // $ANTLR start "ruleVariabley"
    // InternalTyphonQL.g:245:1: ruleVariabley : ( ( rule__Variabley__ValsAssignment ) ) ;
    public final void ruleVariabley() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTyphonQL.g:249:2: ( ( ( rule__Variabley__ValsAssignment ) ) )
            // InternalTyphonQL.g:250:2: ( ( rule__Variabley__ValsAssignment ) )
            {
            // InternalTyphonQL.g:250:2: ( ( rule__Variabley__ValsAssignment ) )
            // InternalTyphonQL.g:251:3: ( rule__Variabley__ValsAssignment )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getVariableyAccess().getValsAssignment()); 
            }
            // InternalTyphonQL.g:252:3: ( rule__Variabley__ValsAssignment )
            // InternalTyphonQL.g:252:4: rule__Variabley__ValsAssignment
            {
            pushFollow(FOLLOW_2);
            rule__Variabley__ValsAssignment();

            state._fsp--;
            if (state.failed) return ;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getVariableyAccess().getValsAssignment()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleVariabley"


    // $ANTLR start "rule__Queries__QueriesAlternatives_0"
    // InternalTyphonQL.g:260:1: rule__Queries__QueriesAlternatives_0 : ( ( ruleInsert ) | ( ruleDelete ) | ( ruleUpdate ) );
    public final void rule__Queries__QueriesAlternatives_0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTyphonQL.g:264:1: ( ( ruleInsert ) | ( ruleDelete ) | ( ruleUpdate ) )
            int alt3=3;
            switch ( input.LA(1) ) {
            case 20:
                {
                alt3=1;
                }
                break;
            case 19:
                {
                alt3=2;
                }
                break;
            case 12:
                {
                alt3=3;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return ;}
                NoViableAltException nvae =
                    new NoViableAltException("", 3, 0, input);

                throw nvae;
            }

            switch (alt3) {
                case 1 :
                    // InternalTyphonQL.g:265:2: ( ruleInsert )
                    {
                    // InternalTyphonQL.g:265:2: ( ruleInsert )
                    // InternalTyphonQL.g:266:3: ruleInsert
                    {
                    if ( state.backtracking==0 ) {
                       before(grammarAccess.getQueriesAccess().getQueriesInsertParserRuleCall_0_0()); 
                    }
                    pushFollow(FOLLOW_2);
                    ruleInsert();

                    state._fsp--;
                    if (state.failed) return ;
                    if ( state.backtracking==0 ) {
                       after(grammarAccess.getQueriesAccess().getQueriesInsertParserRuleCall_0_0()); 
                    }

                    }


                    }
                    break;
                case 2 :
                    // InternalTyphonQL.g:271:2: ( ruleDelete )
                    {
                    // InternalTyphonQL.g:271:2: ( ruleDelete )
                    // InternalTyphonQL.g:272:3: ruleDelete
                    {
                    if ( state.backtracking==0 ) {
                       before(grammarAccess.getQueriesAccess().getQueriesDeleteParserRuleCall_0_1()); 
                    }
                    pushFollow(FOLLOW_2);
                    ruleDelete();

                    state._fsp--;
                    if (state.failed) return ;
                    if ( state.backtracking==0 ) {
                       after(grammarAccess.getQueriesAccess().getQueriesDeleteParserRuleCall_0_1()); 
                    }

                    }


                    }
                    break;
                case 3 :
                    // InternalTyphonQL.g:277:2: ( ruleUpdate )
                    {
                    // InternalTyphonQL.g:277:2: ( ruleUpdate )
                    // InternalTyphonQL.g:278:3: ruleUpdate
                    {
                    if ( state.backtracking==0 ) {
                       before(grammarAccess.getQueriesAccess().getQueriesUpdateParserRuleCall_0_2()); 
                    }
                    pushFollow(FOLLOW_2);
                    ruleUpdate();

                    state._fsp--;
                    if (state.failed) return ;
                    if ( state.backtracking==0 ) {
                       after(grammarAccess.getQueriesAccess().getQueriesUpdateParserRuleCall_0_2()); 
                    }

                    }


                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Queries__QueriesAlternatives_0"


    // $ANTLR start "rule__Expr__ExprsAlternatives_0"
    // InternalTyphonQL.g:287:1: rule__Expr__ExprsAlternatives_0 : ( ( ruleStringy ) | ( ruleVariabley ) | ( ruleObj ) );
    public final void rule__Expr__ExprsAlternatives_0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTyphonQL.g:291:1: ( ( ruleStringy ) | ( ruleVariabley ) | ( ruleObj ) )
            int alt4=3;
            switch ( input.LA(1) ) {
            case RULE_STRING:
                {
                alt4=1;
                }
                break;
            case RULE_VALUE_TERMINAL:
            case RULE_ANY_OTHER:
                {
                alt4=2;
                }
                break;
            case RULE_ID:
                {
                int LA4_3 = input.LA(2);

                if ( (LA4_3==EOF||(LA4_3>=RULE_VALUE_TERMINAL && LA4_3<=RULE_STRING)||LA4_3==13||(LA4_3>=15 && LA4_3<=16)||LA4_3==18||LA4_3==21) ) {
                    alt4=2;
                }
                else if ( (LA4_3==14) ) {
                    alt4=3;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return ;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 4, 3, input);

                    throw nvae;
                }
                }
                break;
            case 21:
                {
                alt4=3;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return ;}
                NoViableAltException nvae =
                    new NoViableAltException("", 4, 0, input);

                throw nvae;
            }

            switch (alt4) {
                case 1 :
                    // InternalTyphonQL.g:292:2: ( ruleStringy )
                    {
                    // InternalTyphonQL.g:292:2: ( ruleStringy )
                    // InternalTyphonQL.g:293:3: ruleStringy
                    {
                    if ( state.backtracking==0 ) {
                       before(grammarAccess.getExprAccess().getExprsStringyParserRuleCall_0_0()); 
                    }
                    pushFollow(FOLLOW_2);
                    ruleStringy();

                    state._fsp--;
                    if (state.failed) return ;
                    if ( state.backtracking==0 ) {
                       after(grammarAccess.getExprAccess().getExprsStringyParserRuleCall_0_0()); 
                    }

                    }


                    }
                    break;
                case 2 :
                    // InternalTyphonQL.g:298:2: ( ruleVariabley )
                    {
                    // InternalTyphonQL.g:298:2: ( ruleVariabley )
                    // InternalTyphonQL.g:299:3: ruleVariabley
                    {
                    if ( state.backtracking==0 ) {
                       before(grammarAccess.getExprAccess().getExprsVariableyParserRuleCall_0_1()); 
                    }
                    pushFollow(FOLLOW_2);
                    ruleVariabley();

                    state._fsp--;
                    if (state.failed) return ;
                    if ( state.backtracking==0 ) {
                       after(grammarAccess.getExprAccess().getExprsVariableyParserRuleCall_0_1()); 
                    }

                    }


                    }
                    break;
                case 3 :
                    // InternalTyphonQL.g:304:2: ( ruleObj )
                    {
                    // InternalTyphonQL.g:304:2: ( ruleObj )
                    // InternalTyphonQL.g:305:3: ruleObj
                    {
                    if ( state.backtracking==0 ) {
                       before(grammarAccess.getExprAccess().getExprsObjParserRuleCall_0_2()); 
                    }
                    pushFollow(FOLLOW_2);
                    ruleObj();

                    state._fsp--;
                    if (state.failed) return ;
                    if ( state.backtracking==0 ) {
                       after(grammarAccess.getExprAccess().getExprsObjParserRuleCall_0_2()); 
                    }

                    }


                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Expr__ExprsAlternatives_0"


    // $ANTLR start "rule__Variabley__ValsAlternatives_0"
    // InternalTyphonQL.g:314:1: rule__Variabley__ValsAlternatives_0 : ( ( RULE_VALUE_TERMINAL ) | ( RULE_ID ) | ( RULE_ANY_OTHER ) );
    public final void rule__Variabley__ValsAlternatives_0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTyphonQL.g:318:1: ( ( RULE_VALUE_TERMINAL ) | ( RULE_ID ) | ( RULE_ANY_OTHER ) )
            int alt5=3;
            switch ( input.LA(1) ) {
            case RULE_VALUE_TERMINAL:
                {
                alt5=1;
                }
                break;
            case RULE_ID:
                {
                alt5=2;
                }
                break;
            case RULE_ANY_OTHER:
                {
                alt5=3;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return ;}
                NoViableAltException nvae =
                    new NoViableAltException("", 5, 0, input);

                throw nvae;
            }

            switch (alt5) {
                case 1 :
                    // InternalTyphonQL.g:319:2: ( RULE_VALUE_TERMINAL )
                    {
                    // InternalTyphonQL.g:319:2: ( RULE_VALUE_TERMINAL )
                    // InternalTyphonQL.g:320:3: RULE_VALUE_TERMINAL
                    {
                    if ( state.backtracking==0 ) {
                       before(grammarAccess.getVariableyAccess().getValsVALUE_TERMINALTerminalRuleCall_0_0()); 
                    }
                    match(input,RULE_VALUE_TERMINAL,FOLLOW_2); if (state.failed) return ;
                    if ( state.backtracking==0 ) {
                       after(grammarAccess.getVariableyAccess().getValsVALUE_TERMINALTerminalRuleCall_0_0()); 
                    }

                    }


                    }
                    break;
                case 2 :
                    // InternalTyphonQL.g:325:2: ( RULE_ID )
                    {
                    // InternalTyphonQL.g:325:2: ( RULE_ID )
                    // InternalTyphonQL.g:326:3: RULE_ID
                    {
                    if ( state.backtracking==0 ) {
                       before(grammarAccess.getVariableyAccess().getValsIDTerminalRuleCall_0_1()); 
                    }
                    match(input,RULE_ID,FOLLOW_2); if (state.failed) return ;
                    if ( state.backtracking==0 ) {
                       after(grammarAccess.getVariableyAccess().getValsIDTerminalRuleCall_0_1()); 
                    }

                    }


                    }
                    break;
                case 3 :
                    // InternalTyphonQL.g:331:2: ( RULE_ANY_OTHER )
                    {
                    // InternalTyphonQL.g:331:2: ( RULE_ANY_OTHER )
                    // InternalTyphonQL.g:332:3: RULE_ANY_OTHER
                    {
                    if ( state.backtracking==0 ) {
                       before(grammarAccess.getVariableyAccess().getValsANY_OTHERTerminalRuleCall_0_2()); 
                    }
                    match(input,RULE_ANY_OTHER,FOLLOW_2); if (state.failed) return ;
                    if ( state.backtracking==0 ) {
                       after(grammarAccess.getVariableyAccess().getValsANY_OTHERTerminalRuleCall_0_2()); 
                    }

                    }


                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Variabley__ValsAlternatives_0"


    // $ANTLR start "rule__Update__Group__0"
    // InternalTyphonQL.g:341:1: rule__Update__Group__0 : rule__Update__Group__0__Impl rule__Update__Group__1 ;
    public final void rule__Update__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTyphonQL.g:345:1: ( rule__Update__Group__0__Impl rule__Update__Group__1 )
            // InternalTyphonQL.g:346:2: rule__Update__Group__0__Impl rule__Update__Group__1
            {
            pushFollow(FOLLOW_5);
            rule__Update__Group__0__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FOLLOW_2);
            rule__Update__Group__1();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Update__Group__0"


    // $ANTLR start "rule__Update__Group__0__Impl"
    // InternalTyphonQL.g:353:1: rule__Update__Group__0__Impl : ( 'update' ) ;
    public final void rule__Update__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTyphonQL.g:357:1: ( ( 'update' ) )
            // InternalTyphonQL.g:358:1: ( 'update' )
            {
            // InternalTyphonQL.g:358:1: ( 'update' )
            // InternalTyphonQL.g:359:2: 'update'
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getUpdateAccess().getUpdateKeyword_0()); 
            }
            match(input,12,FOLLOW_2); if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getUpdateAccess().getUpdateKeyword_0()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Update__Group__0__Impl"


    // $ANTLR start "rule__Update__Group__1"
    // InternalTyphonQL.g:368:1: rule__Update__Group__1 : rule__Update__Group__1__Impl rule__Update__Group__2 ;
    public final void rule__Update__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTyphonQL.g:372:1: ( rule__Update__Group__1__Impl rule__Update__Group__2 )
            // InternalTyphonQL.g:373:2: rule__Update__Group__1__Impl rule__Update__Group__2
            {
            pushFollow(FOLLOW_5);
            rule__Update__Group__1__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FOLLOW_2);
            rule__Update__Group__2();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Update__Group__1"


    // $ANTLR start "rule__Update__Group__1__Impl"
    // InternalTyphonQL.g:380:1: rule__Update__Group__1__Impl : ( ( rule__Update__EidAssignment_1 ) ) ;
    public final void rule__Update__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTyphonQL.g:384:1: ( ( ( rule__Update__EidAssignment_1 ) ) )
            // InternalTyphonQL.g:385:1: ( ( rule__Update__EidAssignment_1 ) )
            {
            // InternalTyphonQL.g:385:1: ( ( rule__Update__EidAssignment_1 ) )
            // InternalTyphonQL.g:386:2: ( rule__Update__EidAssignment_1 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getUpdateAccess().getEidAssignment_1()); 
            }
            // InternalTyphonQL.g:387:2: ( rule__Update__EidAssignment_1 )
            // InternalTyphonQL.g:387:3: rule__Update__EidAssignment_1
            {
            pushFollow(FOLLOW_2);
            rule__Update__EidAssignment_1();

            state._fsp--;
            if (state.failed) return ;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getUpdateAccess().getEidAssignment_1()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Update__Group__1__Impl"


    // $ANTLR start "rule__Update__Group__2"
    // InternalTyphonQL.g:395:1: rule__Update__Group__2 : rule__Update__Group__2__Impl rule__Update__Group__3 ;
    public final void rule__Update__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTyphonQL.g:399:1: ( rule__Update__Group__2__Impl rule__Update__Group__3 )
            // InternalTyphonQL.g:400:2: rule__Update__Group__2__Impl rule__Update__Group__3
            {
            pushFollow(FOLLOW_6);
            rule__Update__Group__2__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FOLLOW_2);
            rule__Update__Group__3();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Update__Group__2"


    // $ANTLR start "rule__Update__Group__2__Impl"
    // InternalTyphonQL.g:407:1: rule__Update__Group__2__Impl : ( ( rule__Update__VidAssignment_2 ) ) ;
    public final void rule__Update__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTyphonQL.g:411:1: ( ( ( rule__Update__VidAssignment_2 ) ) )
            // InternalTyphonQL.g:412:1: ( ( rule__Update__VidAssignment_2 ) )
            {
            // InternalTyphonQL.g:412:1: ( ( rule__Update__VidAssignment_2 ) )
            // InternalTyphonQL.g:413:2: ( rule__Update__VidAssignment_2 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getUpdateAccess().getVidAssignment_2()); 
            }
            // InternalTyphonQL.g:414:2: ( rule__Update__VidAssignment_2 )
            // InternalTyphonQL.g:414:3: rule__Update__VidAssignment_2
            {
            pushFollow(FOLLOW_2);
            rule__Update__VidAssignment_2();

            state._fsp--;
            if (state.failed) return ;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getUpdateAccess().getVidAssignment_2()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Update__Group__2__Impl"


    // $ANTLR start "rule__Update__Group__3"
    // InternalTyphonQL.g:422:1: rule__Update__Group__3 : rule__Update__Group__3__Impl rule__Update__Group__4 ;
    public final void rule__Update__Group__3() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTyphonQL.g:426:1: ( rule__Update__Group__3__Impl rule__Update__Group__4 )
            // InternalTyphonQL.g:427:2: rule__Update__Group__3__Impl rule__Update__Group__4
            {
            pushFollow(FOLLOW_6);
            rule__Update__Group__3__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FOLLOW_2);
            rule__Update__Group__4();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Update__Group__3"


    // $ANTLR start "rule__Update__Group__3__Impl"
    // InternalTyphonQL.g:434:1: rule__Update__Group__3__Impl : ( ( rule__Update__Group_3__0 )? ) ;
    public final void rule__Update__Group__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTyphonQL.g:438:1: ( ( ( rule__Update__Group_3__0 )? ) )
            // InternalTyphonQL.g:439:1: ( ( rule__Update__Group_3__0 )? )
            {
            // InternalTyphonQL.g:439:1: ( ( rule__Update__Group_3__0 )? )
            // InternalTyphonQL.g:440:2: ( rule__Update__Group_3__0 )?
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getUpdateAccess().getGroup_3()); 
            }
            // InternalTyphonQL.g:441:2: ( rule__Update__Group_3__0 )?
            int alt6=2;
            int LA6_0 = input.LA(1);

            if ( (LA6_0==17) ) {
                alt6=1;
            }
            switch (alt6) {
                case 1 :
                    // InternalTyphonQL.g:441:3: rule__Update__Group_3__0
                    {
                    pushFollow(FOLLOW_2);
                    rule__Update__Group_3__0();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getUpdateAccess().getGroup_3()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Update__Group__3__Impl"


    // $ANTLR start "rule__Update__Group__4"
    // InternalTyphonQL.g:449:1: rule__Update__Group__4 : rule__Update__Group__4__Impl rule__Update__Group__5 ;
    public final void rule__Update__Group__4() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTyphonQL.g:453:1: ( rule__Update__Group__4__Impl rule__Update__Group__5 )
            // InternalTyphonQL.g:454:2: rule__Update__Group__4__Impl rule__Update__Group__5
            {
            pushFollow(FOLLOW_7);
            rule__Update__Group__4__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FOLLOW_2);
            rule__Update__Group__5();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Update__Group__4"


    // $ANTLR start "rule__Update__Group__4__Impl"
    // InternalTyphonQL.g:461:1: rule__Update__Group__4__Impl : ( 'set' ) ;
    public final void rule__Update__Group__4__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTyphonQL.g:465:1: ( ( 'set' ) )
            // InternalTyphonQL.g:466:1: ( 'set' )
            {
            // InternalTyphonQL.g:466:1: ( 'set' )
            // InternalTyphonQL.g:467:2: 'set'
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getUpdateAccess().getSetKeyword_4()); 
            }
            match(input,13,FOLLOW_2); if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getUpdateAccess().getSetKeyword_4()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Update__Group__4__Impl"


    // $ANTLR start "rule__Update__Group__5"
    // InternalTyphonQL.g:476:1: rule__Update__Group__5 : rule__Update__Group__5__Impl rule__Update__Group__6 ;
    public final void rule__Update__Group__5() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTyphonQL.g:480:1: ( rule__Update__Group__5__Impl rule__Update__Group__6 )
            // InternalTyphonQL.g:481:2: rule__Update__Group__5__Impl rule__Update__Group__6
            {
            pushFollow(FOLLOW_8);
            rule__Update__Group__5__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FOLLOW_2);
            rule__Update__Group__6();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Update__Group__5"


    // $ANTLR start "rule__Update__Group__5__Impl"
    // InternalTyphonQL.g:488:1: rule__Update__Group__5__Impl : ( '{' ) ;
    public final void rule__Update__Group__5__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTyphonQL.g:492:1: ( ( '{' ) )
            // InternalTyphonQL.g:493:1: ( '{' )
            {
            // InternalTyphonQL.g:493:1: ( '{' )
            // InternalTyphonQL.g:494:2: '{'
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getUpdateAccess().getLeftCurlyBracketKeyword_5()); 
            }
            match(input,14,FOLLOW_2); if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getUpdateAccess().getLeftCurlyBracketKeyword_5()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Update__Group__5__Impl"


    // $ANTLR start "rule__Update__Group__6"
    // InternalTyphonQL.g:503:1: rule__Update__Group__6 : rule__Update__Group__6__Impl rule__Update__Group__7 ;
    public final void rule__Update__Group__6() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTyphonQL.g:507:1: ( rule__Update__Group__6__Impl rule__Update__Group__7 )
            // InternalTyphonQL.g:508:2: rule__Update__Group__6__Impl rule__Update__Group__7
            {
            pushFollow(FOLLOW_8);
            rule__Update__Group__6__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FOLLOW_2);
            rule__Update__Group__7();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Update__Group__6"


    // $ANTLR start "rule__Update__Group__6__Impl"
    // InternalTyphonQL.g:515:1: rule__Update__Group__6__Impl : ( ( rule__Update__SetAssignment_6 )? ) ;
    public final void rule__Update__Group__6__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTyphonQL.g:519:1: ( ( ( rule__Update__SetAssignment_6 )? ) )
            // InternalTyphonQL.g:520:1: ( ( rule__Update__SetAssignment_6 )? )
            {
            // InternalTyphonQL.g:520:1: ( ( rule__Update__SetAssignment_6 )? )
            // InternalTyphonQL.g:521:2: ( rule__Update__SetAssignment_6 )?
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getUpdateAccess().getSetAssignment_6()); 
            }
            // InternalTyphonQL.g:522:2: ( rule__Update__SetAssignment_6 )?
            int alt7=2;
            int LA7_0 = input.LA(1);

            if ( ((LA7_0>=RULE_VALUE_TERMINAL && LA7_0<=RULE_STRING)||LA7_0==21) ) {
                alt7=1;
            }
            switch (alt7) {
                case 1 :
                    // InternalTyphonQL.g:522:3: rule__Update__SetAssignment_6
                    {
                    pushFollow(FOLLOW_2);
                    rule__Update__SetAssignment_6();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getUpdateAccess().getSetAssignment_6()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Update__Group__6__Impl"


    // $ANTLR start "rule__Update__Group__7"
    // InternalTyphonQL.g:530:1: rule__Update__Group__7 : rule__Update__Group__7__Impl rule__Update__Group__8 ;
    public final void rule__Update__Group__7() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTyphonQL.g:534:1: ( rule__Update__Group__7__Impl rule__Update__Group__8 )
            // InternalTyphonQL.g:535:2: rule__Update__Group__7__Impl rule__Update__Group__8
            {
            pushFollow(FOLLOW_8);
            rule__Update__Group__7__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FOLLOW_2);
            rule__Update__Group__8();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Update__Group__7"


    // $ANTLR start "rule__Update__Group__7__Impl"
    // InternalTyphonQL.g:542:1: rule__Update__Group__7__Impl : ( ( rule__Update__Group_7__0 )* ) ;
    public final void rule__Update__Group__7__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTyphonQL.g:546:1: ( ( ( rule__Update__Group_7__0 )* ) )
            // InternalTyphonQL.g:547:1: ( ( rule__Update__Group_7__0 )* )
            {
            // InternalTyphonQL.g:547:1: ( ( rule__Update__Group_7__0 )* )
            // InternalTyphonQL.g:548:2: ( rule__Update__Group_7__0 )*
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getUpdateAccess().getGroup_7()); 
            }
            // InternalTyphonQL.g:549:2: ( rule__Update__Group_7__0 )*
            loop8:
            do {
                int alt8=2;
                int LA8_0 = input.LA(1);

                if ( (LA8_0==18) ) {
                    alt8=1;
                }


                switch (alt8) {
            	case 1 :
            	    // InternalTyphonQL.g:549:3: rule__Update__Group_7__0
            	    {
            	    pushFollow(FOLLOW_9);
            	    rule__Update__Group_7__0();

            	    state._fsp--;
            	    if (state.failed) return ;

            	    }
            	    break;

            	default :
            	    break loop8;
                }
            } while (true);

            if ( state.backtracking==0 ) {
               after(grammarAccess.getUpdateAccess().getGroup_7()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Update__Group__7__Impl"


    // $ANTLR start "rule__Update__Group__8"
    // InternalTyphonQL.g:557:1: rule__Update__Group__8 : rule__Update__Group__8__Impl rule__Update__Group__9 ;
    public final void rule__Update__Group__8() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTyphonQL.g:561:1: ( rule__Update__Group__8__Impl rule__Update__Group__9 )
            // InternalTyphonQL.g:562:2: rule__Update__Group__8__Impl rule__Update__Group__9
            {
            pushFollow(FOLLOW_10);
            rule__Update__Group__8__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FOLLOW_2);
            rule__Update__Group__9();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Update__Group__8"


    // $ANTLR start "rule__Update__Group__8__Impl"
    // InternalTyphonQL.g:569:1: rule__Update__Group__8__Impl : ( '}' ) ;
    public final void rule__Update__Group__8__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTyphonQL.g:573:1: ( ( '}' ) )
            // InternalTyphonQL.g:574:1: ( '}' )
            {
            // InternalTyphonQL.g:574:1: ( '}' )
            // InternalTyphonQL.g:575:2: '}'
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getUpdateAccess().getRightCurlyBracketKeyword_8()); 
            }
            match(input,15,FOLLOW_2); if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getUpdateAccess().getRightCurlyBracketKeyword_8()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Update__Group__8__Impl"


    // $ANTLR start "rule__Update__Group__9"
    // InternalTyphonQL.g:584:1: rule__Update__Group__9 : rule__Update__Group__9__Impl ;
    public final void rule__Update__Group__9() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTyphonQL.g:588:1: ( rule__Update__Group__9__Impl )
            // InternalTyphonQL.g:589:2: rule__Update__Group__9__Impl
            {
            pushFollow(FOLLOW_2);
            rule__Update__Group__9__Impl();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Update__Group__9"


    // $ANTLR start "rule__Update__Group__9__Impl"
    // InternalTyphonQL.g:595:1: rule__Update__Group__9__Impl : ( ';' ) ;
    public final void rule__Update__Group__9__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTyphonQL.g:599:1: ( ( ';' ) )
            // InternalTyphonQL.g:600:1: ( ';' )
            {
            // InternalTyphonQL.g:600:1: ( ';' )
            // InternalTyphonQL.g:601:2: ';'
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getUpdateAccess().getSemicolonKeyword_9()); 
            }
            match(input,16,FOLLOW_2); if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getUpdateAccess().getSemicolonKeyword_9()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Update__Group__9__Impl"


    // $ANTLR start "rule__Update__Group_3__0"
    // InternalTyphonQL.g:611:1: rule__Update__Group_3__0 : rule__Update__Group_3__0__Impl rule__Update__Group_3__1 ;
    public final void rule__Update__Group_3__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTyphonQL.g:615:1: ( rule__Update__Group_3__0__Impl rule__Update__Group_3__1 )
            // InternalTyphonQL.g:616:2: rule__Update__Group_3__0__Impl rule__Update__Group_3__1
            {
            pushFollow(FOLLOW_11);
            rule__Update__Group_3__0__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FOLLOW_2);
            rule__Update__Group_3__1();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Update__Group_3__0"


    // $ANTLR start "rule__Update__Group_3__0__Impl"
    // InternalTyphonQL.g:623:1: rule__Update__Group_3__0__Impl : ( 'where' ) ;
    public final void rule__Update__Group_3__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTyphonQL.g:627:1: ( ( 'where' ) )
            // InternalTyphonQL.g:628:1: ( 'where' )
            {
            // InternalTyphonQL.g:628:1: ( 'where' )
            // InternalTyphonQL.g:629:2: 'where'
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getUpdateAccess().getWhereKeyword_3_0()); 
            }
            match(input,17,FOLLOW_2); if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getUpdateAccess().getWhereKeyword_3_0()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Update__Group_3__0__Impl"


    // $ANTLR start "rule__Update__Group_3__1"
    // InternalTyphonQL.g:638:1: rule__Update__Group_3__1 : rule__Update__Group_3__1__Impl ;
    public final void rule__Update__Group_3__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTyphonQL.g:642:1: ( rule__Update__Group_3__1__Impl )
            // InternalTyphonQL.g:643:2: rule__Update__Group_3__1__Impl
            {
            pushFollow(FOLLOW_2);
            rule__Update__Group_3__1__Impl();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Update__Group_3__1"


    // $ANTLR start "rule__Update__Group_3__1__Impl"
    // InternalTyphonQL.g:649:1: rule__Update__Group_3__1__Impl : ( ( rule__Update__WhereAssignment_3_1 ) ) ;
    public final void rule__Update__Group_3__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTyphonQL.g:653:1: ( ( ( rule__Update__WhereAssignment_3_1 ) ) )
            // InternalTyphonQL.g:654:1: ( ( rule__Update__WhereAssignment_3_1 ) )
            {
            // InternalTyphonQL.g:654:1: ( ( rule__Update__WhereAssignment_3_1 ) )
            // InternalTyphonQL.g:655:2: ( rule__Update__WhereAssignment_3_1 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getUpdateAccess().getWhereAssignment_3_1()); 
            }
            // InternalTyphonQL.g:656:2: ( rule__Update__WhereAssignment_3_1 )
            // InternalTyphonQL.g:656:3: rule__Update__WhereAssignment_3_1
            {
            pushFollow(FOLLOW_2);
            rule__Update__WhereAssignment_3_1();

            state._fsp--;
            if (state.failed) return ;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getUpdateAccess().getWhereAssignment_3_1()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Update__Group_3__1__Impl"


    // $ANTLR start "rule__Update__Group_7__0"
    // InternalTyphonQL.g:665:1: rule__Update__Group_7__0 : rule__Update__Group_7__0__Impl rule__Update__Group_7__1 ;
    public final void rule__Update__Group_7__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTyphonQL.g:669:1: ( rule__Update__Group_7__0__Impl rule__Update__Group_7__1 )
            // InternalTyphonQL.g:670:2: rule__Update__Group_7__0__Impl rule__Update__Group_7__1
            {
            pushFollow(FOLLOW_11);
            rule__Update__Group_7__0__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FOLLOW_2);
            rule__Update__Group_7__1();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Update__Group_7__0"


    // $ANTLR start "rule__Update__Group_7__0__Impl"
    // InternalTyphonQL.g:677:1: rule__Update__Group_7__0__Impl : ( ',' ) ;
    public final void rule__Update__Group_7__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTyphonQL.g:681:1: ( ( ',' ) )
            // InternalTyphonQL.g:682:1: ( ',' )
            {
            // InternalTyphonQL.g:682:1: ( ',' )
            // InternalTyphonQL.g:683:2: ','
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getUpdateAccess().getCommaKeyword_7_0()); 
            }
            match(input,18,FOLLOW_2); if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getUpdateAccess().getCommaKeyword_7_0()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Update__Group_7__0__Impl"


    // $ANTLR start "rule__Update__Group_7__1"
    // InternalTyphonQL.g:692:1: rule__Update__Group_7__1 : rule__Update__Group_7__1__Impl ;
    public final void rule__Update__Group_7__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTyphonQL.g:696:1: ( rule__Update__Group_7__1__Impl )
            // InternalTyphonQL.g:697:2: rule__Update__Group_7__1__Impl
            {
            pushFollow(FOLLOW_2);
            rule__Update__Group_7__1__Impl();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Update__Group_7__1"


    // $ANTLR start "rule__Update__Group_7__1__Impl"
    // InternalTyphonQL.g:703:1: rule__Update__Group_7__1__Impl : ( ( rule__Update__SetAssignment_7_1 ) ) ;
    public final void rule__Update__Group_7__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTyphonQL.g:707:1: ( ( ( rule__Update__SetAssignment_7_1 ) ) )
            // InternalTyphonQL.g:708:1: ( ( rule__Update__SetAssignment_7_1 ) )
            {
            // InternalTyphonQL.g:708:1: ( ( rule__Update__SetAssignment_7_1 ) )
            // InternalTyphonQL.g:709:2: ( rule__Update__SetAssignment_7_1 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getUpdateAccess().getSetAssignment_7_1()); 
            }
            // InternalTyphonQL.g:710:2: ( rule__Update__SetAssignment_7_1 )
            // InternalTyphonQL.g:710:3: rule__Update__SetAssignment_7_1
            {
            pushFollow(FOLLOW_2);
            rule__Update__SetAssignment_7_1();

            state._fsp--;
            if (state.failed) return ;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getUpdateAccess().getSetAssignment_7_1()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Update__Group_7__1__Impl"


    // $ANTLR start "rule__Delete__Group__0"
    // InternalTyphonQL.g:719:1: rule__Delete__Group__0 : rule__Delete__Group__0__Impl rule__Delete__Group__1 ;
    public final void rule__Delete__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTyphonQL.g:723:1: ( rule__Delete__Group__0__Impl rule__Delete__Group__1 )
            // InternalTyphonQL.g:724:2: rule__Delete__Group__0__Impl rule__Delete__Group__1
            {
            pushFollow(FOLLOW_5);
            rule__Delete__Group__0__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FOLLOW_2);
            rule__Delete__Group__1();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Delete__Group__0"


    // $ANTLR start "rule__Delete__Group__0__Impl"
    // InternalTyphonQL.g:731:1: rule__Delete__Group__0__Impl : ( 'delete' ) ;
    public final void rule__Delete__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTyphonQL.g:735:1: ( ( 'delete' ) )
            // InternalTyphonQL.g:736:1: ( 'delete' )
            {
            // InternalTyphonQL.g:736:1: ( 'delete' )
            // InternalTyphonQL.g:737:2: 'delete'
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getDeleteAccess().getDeleteKeyword_0()); 
            }
            match(input,19,FOLLOW_2); if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getDeleteAccess().getDeleteKeyword_0()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Delete__Group__0__Impl"


    // $ANTLR start "rule__Delete__Group__1"
    // InternalTyphonQL.g:746:1: rule__Delete__Group__1 : rule__Delete__Group__1__Impl rule__Delete__Group__2 ;
    public final void rule__Delete__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTyphonQL.g:750:1: ( rule__Delete__Group__1__Impl rule__Delete__Group__2 )
            // InternalTyphonQL.g:751:2: rule__Delete__Group__1__Impl rule__Delete__Group__2
            {
            pushFollow(FOLLOW_5);
            rule__Delete__Group__1__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FOLLOW_2);
            rule__Delete__Group__2();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Delete__Group__1"


    // $ANTLR start "rule__Delete__Group__1__Impl"
    // InternalTyphonQL.g:758:1: rule__Delete__Group__1__Impl : ( ( rule__Delete__EidAssignment_1 ) ) ;
    public final void rule__Delete__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTyphonQL.g:762:1: ( ( ( rule__Delete__EidAssignment_1 ) ) )
            // InternalTyphonQL.g:763:1: ( ( rule__Delete__EidAssignment_1 ) )
            {
            // InternalTyphonQL.g:763:1: ( ( rule__Delete__EidAssignment_1 ) )
            // InternalTyphonQL.g:764:2: ( rule__Delete__EidAssignment_1 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getDeleteAccess().getEidAssignment_1()); 
            }
            // InternalTyphonQL.g:765:2: ( rule__Delete__EidAssignment_1 )
            // InternalTyphonQL.g:765:3: rule__Delete__EidAssignment_1
            {
            pushFollow(FOLLOW_2);
            rule__Delete__EidAssignment_1();

            state._fsp--;
            if (state.failed) return ;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getDeleteAccess().getEidAssignment_1()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Delete__Group__1__Impl"


    // $ANTLR start "rule__Delete__Group__2"
    // InternalTyphonQL.g:773:1: rule__Delete__Group__2 : rule__Delete__Group__2__Impl rule__Delete__Group__3 ;
    public final void rule__Delete__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTyphonQL.g:777:1: ( rule__Delete__Group__2__Impl rule__Delete__Group__3 )
            // InternalTyphonQL.g:778:2: rule__Delete__Group__2__Impl rule__Delete__Group__3
            {
            pushFollow(FOLLOW_12);
            rule__Delete__Group__2__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FOLLOW_2);
            rule__Delete__Group__3();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Delete__Group__2"


    // $ANTLR start "rule__Delete__Group__2__Impl"
    // InternalTyphonQL.g:785:1: rule__Delete__Group__2__Impl : ( ( rule__Delete__VidAssignment_2 ) ) ;
    public final void rule__Delete__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTyphonQL.g:789:1: ( ( ( rule__Delete__VidAssignment_2 ) ) )
            // InternalTyphonQL.g:790:1: ( ( rule__Delete__VidAssignment_2 ) )
            {
            // InternalTyphonQL.g:790:1: ( ( rule__Delete__VidAssignment_2 ) )
            // InternalTyphonQL.g:791:2: ( rule__Delete__VidAssignment_2 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getDeleteAccess().getVidAssignment_2()); 
            }
            // InternalTyphonQL.g:792:2: ( rule__Delete__VidAssignment_2 )
            // InternalTyphonQL.g:792:3: rule__Delete__VidAssignment_2
            {
            pushFollow(FOLLOW_2);
            rule__Delete__VidAssignment_2();

            state._fsp--;
            if (state.failed) return ;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getDeleteAccess().getVidAssignment_2()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Delete__Group__2__Impl"


    // $ANTLR start "rule__Delete__Group__3"
    // InternalTyphonQL.g:800:1: rule__Delete__Group__3 : rule__Delete__Group__3__Impl rule__Delete__Group__4 ;
    public final void rule__Delete__Group__3() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTyphonQL.g:804:1: ( rule__Delete__Group__3__Impl rule__Delete__Group__4 )
            // InternalTyphonQL.g:805:2: rule__Delete__Group__3__Impl rule__Delete__Group__4
            {
            pushFollow(FOLLOW_12);
            rule__Delete__Group__3__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FOLLOW_2);
            rule__Delete__Group__4();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Delete__Group__3"


    // $ANTLR start "rule__Delete__Group__3__Impl"
    // InternalTyphonQL.g:812:1: rule__Delete__Group__3__Impl : ( ( rule__Delete__Group_3__0 )? ) ;
    public final void rule__Delete__Group__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTyphonQL.g:816:1: ( ( ( rule__Delete__Group_3__0 )? ) )
            // InternalTyphonQL.g:817:1: ( ( rule__Delete__Group_3__0 )? )
            {
            // InternalTyphonQL.g:817:1: ( ( rule__Delete__Group_3__0 )? )
            // InternalTyphonQL.g:818:2: ( rule__Delete__Group_3__0 )?
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getDeleteAccess().getGroup_3()); 
            }
            // InternalTyphonQL.g:819:2: ( rule__Delete__Group_3__0 )?
            int alt9=2;
            int LA9_0 = input.LA(1);

            if ( (LA9_0==17) ) {
                alt9=1;
            }
            switch (alt9) {
                case 1 :
                    // InternalTyphonQL.g:819:3: rule__Delete__Group_3__0
                    {
                    pushFollow(FOLLOW_2);
                    rule__Delete__Group_3__0();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getDeleteAccess().getGroup_3()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Delete__Group__3__Impl"


    // $ANTLR start "rule__Delete__Group__4"
    // InternalTyphonQL.g:827:1: rule__Delete__Group__4 : rule__Delete__Group__4__Impl ;
    public final void rule__Delete__Group__4() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTyphonQL.g:831:1: ( rule__Delete__Group__4__Impl )
            // InternalTyphonQL.g:832:2: rule__Delete__Group__4__Impl
            {
            pushFollow(FOLLOW_2);
            rule__Delete__Group__4__Impl();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Delete__Group__4"


    // $ANTLR start "rule__Delete__Group__4__Impl"
    // InternalTyphonQL.g:838:1: rule__Delete__Group__4__Impl : ( ';' ) ;
    public final void rule__Delete__Group__4__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTyphonQL.g:842:1: ( ( ';' ) )
            // InternalTyphonQL.g:843:1: ( ';' )
            {
            // InternalTyphonQL.g:843:1: ( ';' )
            // InternalTyphonQL.g:844:2: ';'
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getDeleteAccess().getSemicolonKeyword_4()); 
            }
            match(input,16,FOLLOW_2); if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getDeleteAccess().getSemicolonKeyword_4()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Delete__Group__4__Impl"


    // $ANTLR start "rule__Delete__Group_3__0"
    // InternalTyphonQL.g:854:1: rule__Delete__Group_3__0 : rule__Delete__Group_3__0__Impl rule__Delete__Group_3__1 ;
    public final void rule__Delete__Group_3__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTyphonQL.g:858:1: ( rule__Delete__Group_3__0__Impl rule__Delete__Group_3__1 )
            // InternalTyphonQL.g:859:2: rule__Delete__Group_3__0__Impl rule__Delete__Group_3__1
            {
            pushFollow(FOLLOW_11);
            rule__Delete__Group_3__0__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FOLLOW_2);
            rule__Delete__Group_3__1();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Delete__Group_3__0"


    // $ANTLR start "rule__Delete__Group_3__0__Impl"
    // InternalTyphonQL.g:866:1: rule__Delete__Group_3__0__Impl : ( 'where' ) ;
    public final void rule__Delete__Group_3__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTyphonQL.g:870:1: ( ( 'where' ) )
            // InternalTyphonQL.g:871:1: ( 'where' )
            {
            // InternalTyphonQL.g:871:1: ( 'where' )
            // InternalTyphonQL.g:872:2: 'where'
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getDeleteAccess().getWhereKeyword_3_0()); 
            }
            match(input,17,FOLLOW_2); if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getDeleteAccess().getWhereKeyword_3_0()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Delete__Group_3__0__Impl"


    // $ANTLR start "rule__Delete__Group_3__1"
    // InternalTyphonQL.g:881:1: rule__Delete__Group_3__1 : rule__Delete__Group_3__1__Impl ;
    public final void rule__Delete__Group_3__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTyphonQL.g:885:1: ( rule__Delete__Group_3__1__Impl )
            // InternalTyphonQL.g:886:2: rule__Delete__Group_3__1__Impl
            {
            pushFollow(FOLLOW_2);
            rule__Delete__Group_3__1__Impl();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Delete__Group_3__1"


    // $ANTLR start "rule__Delete__Group_3__1__Impl"
    // InternalTyphonQL.g:892:1: rule__Delete__Group_3__1__Impl : ( ( rule__Delete__WhereAssignment_3_1 ) ) ;
    public final void rule__Delete__Group_3__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTyphonQL.g:896:1: ( ( ( rule__Delete__WhereAssignment_3_1 ) ) )
            // InternalTyphonQL.g:897:1: ( ( rule__Delete__WhereAssignment_3_1 ) )
            {
            // InternalTyphonQL.g:897:1: ( ( rule__Delete__WhereAssignment_3_1 ) )
            // InternalTyphonQL.g:898:2: ( rule__Delete__WhereAssignment_3_1 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getDeleteAccess().getWhereAssignment_3_1()); 
            }
            // InternalTyphonQL.g:899:2: ( rule__Delete__WhereAssignment_3_1 )
            // InternalTyphonQL.g:899:3: rule__Delete__WhereAssignment_3_1
            {
            pushFollow(FOLLOW_2);
            rule__Delete__WhereAssignment_3_1();

            state._fsp--;
            if (state.failed) return ;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getDeleteAccess().getWhereAssignment_3_1()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Delete__Group_3__1__Impl"


    // $ANTLR start "rule__Insert__Group__0"
    // InternalTyphonQL.g:908:1: rule__Insert__Group__0 : rule__Insert__Group__0__Impl rule__Insert__Group__1 ;
    public final void rule__Insert__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTyphonQL.g:912:1: ( rule__Insert__Group__0__Impl rule__Insert__Group__1 )
            // InternalTyphonQL.g:913:2: rule__Insert__Group__0__Impl rule__Insert__Group__1
            {
            pushFollow(FOLLOW_11);
            rule__Insert__Group__0__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FOLLOW_2);
            rule__Insert__Group__1();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Insert__Group__0"


    // $ANTLR start "rule__Insert__Group__0__Impl"
    // InternalTyphonQL.g:920:1: rule__Insert__Group__0__Impl : ( 'insert' ) ;
    public final void rule__Insert__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTyphonQL.g:924:1: ( ( 'insert' ) )
            // InternalTyphonQL.g:925:1: ( 'insert' )
            {
            // InternalTyphonQL.g:925:1: ( 'insert' )
            // InternalTyphonQL.g:926:2: 'insert'
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getInsertAccess().getInsertKeyword_0()); 
            }
            match(input,20,FOLLOW_2); if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getInsertAccess().getInsertKeyword_0()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Insert__Group__0__Impl"


    // $ANTLR start "rule__Insert__Group__1"
    // InternalTyphonQL.g:935:1: rule__Insert__Group__1 : rule__Insert__Group__1__Impl rule__Insert__Group__2 ;
    public final void rule__Insert__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTyphonQL.g:939:1: ( rule__Insert__Group__1__Impl rule__Insert__Group__2 )
            // InternalTyphonQL.g:940:2: rule__Insert__Group__1__Impl rule__Insert__Group__2
            {
            pushFollow(FOLLOW_13);
            rule__Insert__Group__1__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FOLLOW_2);
            rule__Insert__Group__2();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Insert__Group__1"


    // $ANTLR start "rule__Insert__Group__1__Impl"
    // InternalTyphonQL.g:947:1: rule__Insert__Group__1__Impl : ( ( rule__Insert__ObjectsAssignment_1 ) ) ;
    public final void rule__Insert__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTyphonQL.g:951:1: ( ( ( rule__Insert__ObjectsAssignment_1 ) ) )
            // InternalTyphonQL.g:952:1: ( ( rule__Insert__ObjectsAssignment_1 ) )
            {
            // InternalTyphonQL.g:952:1: ( ( rule__Insert__ObjectsAssignment_1 ) )
            // InternalTyphonQL.g:953:2: ( rule__Insert__ObjectsAssignment_1 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getInsertAccess().getObjectsAssignment_1()); 
            }
            // InternalTyphonQL.g:954:2: ( rule__Insert__ObjectsAssignment_1 )
            // InternalTyphonQL.g:954:3: rule__Insert__ObjectsAssignment_1
            {
            pushFollow(FOLLOW_2);
            rule__Insert__ObjectsAssignment_1();

            state._fsp--;
            if (state.failed) return ;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getInsertAccess().getObjectsAssignment_1()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Insert__Group__1__Impl"


    // $ANTLR start "rule__Insert__Group__2"
    // InternalTyphonQL.g:962:1: rule__Insert__Group__2 : rule__Insert__Group__2__Impl rule__Insert__Group__3 ;
    public final void rule__Insert__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTyphonQL.g:966:1: ( rule__Insert__Group__2__Impl rule__Insert__Group__3 )
            // InternalTyphonQL.g:967:2: rule__Insert__Group__2__Impl rule__Insert__Group__3
            {
            pushFollow(FOLLOW_13);
            rule__Insert__Group__2__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FOLLOW_2);
            rule__Insert__Group__3();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Insert__Group__2"


    // $ANTLR start "rule__Insert__Group__2__Impl"
    // InternalTyphonQL.g:974:1: rule__Insert__Group__2__Impl : ( ( rule__Insert__Group_2__0 )* ) ;
    public final void rule__Insert__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTyphonQL.g:978:1: ( ( ( rule__Insert__Group_2__0 )* ) )
            // InternalTyphonQL.g:979:1: ( ( rule__Insert__Group_2__0 )* )
            {
            // InternalTyphonQL.g:979:1: ( ( rule__Insert__Group_2__0 )* )
            // InternalTyphonQL.g:980:2: ( rule__Insert__Group_2__0 )*
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getInsertAccess().getGroup_2()); 
            }
            // InternalTyphonQL.g:981:2: ( rule__Insert__Group_2__0 )*
            loop10:
            do {
                int alt10=2;
                int LA10_0 = input.LA(1);

                if ( (LA10_0==18) ) {
                    alt10=1;
                }


                switch (alt10) {
            	case 1 :
            	    // InternalTyphonQL.g:981:3: rule__Insert__Group_2__0
            	    {
            	    pushFollow(FOLLOW_9);
            	    rule__Insert__Group_2__0();

            	    state._fsp--;
            	    if (state.failed) return ;

            	    }
            	    break;

            	default :
            	    break loop10;
                }
            } while (true);

            if ( state.backtracking==0 ) {
               after(grammarAccess.getInsertAccess().getGroup_2()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Insert__Group__2__Impl"


    // $ANTLR start "rule__Insert__Group__3"
    // InternalTyphonQL.g:989:1: rule__Insert__Group__3 : rule__Insert__Group__3__Impl ;
    public final void rule__Insert__Group__3() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTyphonQL.g:993:1: ( rule__Insert__Group__3__Impl )
            // InternalTyphonQL.g:994:2: rule__Insert__Group__3__Impl
            {
            pushFollow(FOLLOW_2);
            rule__Insert__Group__3__Impl();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Insert__Group__3"


    // $ANTLR start "rule__Insert__Group__3__Impl"
    // InternalTyphonQL.g:1000:1: rule__Insert__Group__3__Impl : ( ';' ) ;
    public final void rule__Insert__Group__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTyphonQL.g:1004:1: ( ( ';' ) )
            // InternalTyphonQL.g:1005:1: ( ';' )
            {
            // InternalTyphonQL.g:1005:1: ( ';' )
            // InternalTyphonQL.g:1006:2: ';'
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getInsertAccess().getSemicolonKeyword_3()); 
            }
            match(input,16,FOLLOW_2); if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getInsertAccess().getSemicolonKeyword_3()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Insert__Group__3__Impl"


    // $ANTLR start "rule__Insert__Group_2__0"
    // InternalTyphonQL.g:1016:1: rule__Insert__Group_2__0 : rule__Insert__Group_2__0__Impl rule__Insert__Group_2__1 ;
    public final void rule__Insert__Group_2__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTyphonQL.g:1020:1: ( rule__Insert__Group_2__0__Impl rule__Insert__Group_2__1 )
            // InternalTyphonQL.g:1021:2: rule__Insert__Group_2__0__Impl rule__Insert__Group_2__1
            {
            pushFollow(FOLLOW_11);
            rule__Insert__Group_2__0__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FOLLOW_2);
            rule__Insert__Group_2__1();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Insert__Group_2__0"


    // $ANTLR start "rule__Insert__Group_2__0__Impl"
    // InternalTyphonQL.g:1028:1: rule__Insert__Group_2__0__Impl : ( ',' ) ;
    public final void rule__Insert__Group_2__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTyphonQL.g:1032:1: ( ( ',' ) )
            // InternalTyphonQL.g:1033:1: ( ',' )
            {
            // InternalTyphonQL.g:1033:1: ( ',' )
            // InternalTyphonQL.g:1034:2: ','
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getInsertAccess().getCommaKeyword_2_0()); 
            }
            match(input,18,FOLLOW_2); if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getInsertAccess().getCommaKeyword_2_0()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Insert__Group_2__0__Impl"


    // $ANTLR start "rule__Insert__Group_2__1"
    // InternalTyphonQL.g:1043:1: rule__Insert__Group_2__1 : rule__Insert__Group_2__1__Impl ;
    public final void rule__Insert__Group_2__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTyphonQL.g:1047:1: ( rule__Insert__Group_2__1__Impl )
            // InternalTyphonQL.g:1048:2: rule__Insert__Group_2__1__Impl
            {
            pushFollow(FOLLOW_2);
            rule__Insert__Group_2__1__Impl();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Insert__Group_2__1"


    // $ANTLR start "rule__Insert__Group_2__1__Impl"
    // InternalTyphonQL.g:1054:1: rule__Insert__Group_2__1__Impl : ( ( rule__Insert__ObjectsAssignment_2_1 ) ) ;
    public final void rule__Insert__Group_2__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTyphonQL.g:1058:1: ( ( ( rule__Insert__ObjectsAssignment_2_1 ) ) )
            // InternalTyphonQL.g:1059:1: ( ( rule__Insert__ObjectsAssignment_2_1 ) )
            {
            // InternalTyphonQL.g:1059:1: ( ( rule__Insert__ObjectsAssignment_2_1 ) )
            // InternalTyphonQL.g:1060:2: ( rule__Insert__ObjectsAssignment_2_1 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getInsertAccess().getObjectsAssignment_2_1()); 
            }
            // InternalTyphonQL.g:1061:2: ( rule__Insert__ObjectsAssignment_2_1 )
            // InternalTyphonQL.g:1061:3: rule__Insert__ObjectsAssignment_2_1
            {
            pushFollow(FOLLOW_2);
            rule__Insert__ObjectsAssignment_2_1();

            state._fsp--;
            if (state.failed) return ;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getInsertAccess().getObjectsAssignment_2_1()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Insert__Group_2__1__Impl"


    // $ANTLR start "rule__Obj__Group__0"
    // InternalTyphonQL.g:1070:1: rule__Obj__Group__0 : rule__Obj__Group__0__Impl rule__Obj__Group__1 ;
    public final void rule__Obj__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTyphonQL.g:1074:1: ( rule__Obj__Group__0__Impl rule__Obj__Group__1 )
            // InternalTyphonQL.g:1075:2: rule__Obj__Group__0__Impl rule__Obj__Group__1
            {
            pushFollow(FOLLOW_11);
            rule__Obj__Group__0__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FOLLOW_2);
            rule__Obj__Group__1();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Obj__Group__0"


    // $ANTLR start "rule__Obj__Group__0__Impl"
    // InternalTyphonQL.g:1082:1: rule__Obj__Group__0__Impl : ( ( rule__Obj__Group_0__0 )? ) ;
    public final void rule__Obj__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTyphonQL.g:1086:1: ( ( ( rule__Obj__Group_0__0 )? ) )
            // InternalTyphonQL.g:1087:1: ( ( rule__Obj__Group_0__0 )? )
            {
            // InternalTyphonQL.g:1087:1: ( ( rule__Obj__Group_0__0 )? )
            // InternalTyphonQL.g:1088:2: ( rule__Obj__Group_0__0 )?
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getObjAccess().getGroup_0()); 
            }
            // InternalTyphonQL.g:1089:2: ( rule__Obj__Group_0__0 )?
            int alt11=2;
            int LA11_0 = input.LA(1);

            if ( (LA11_0==21) ) {
                alt11=1;
            }
            switch (alt11) {
                case 1 :
                    // InternalTyphonQL.g:1089:3: rule__Obj__Group_0__0
                    {
                    pushFollow(FOLLOW_2);
                    rule__Obj__Group_0__0();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getObjAccess().getGroup_0()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Obj__Group__0__Impl"


    // $ANTLR start "rule__Obj__Group__1"
    // InternalTyphonQL.g:1097:1: rule__Obj__Group__1 : rule__Obj__Group__1__Impl rule__Obj__Group__2 ;
    public final void rule__Obj__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTyphonQL.g:1101:1: ( rule__Obj__Group__1__Impl rule__Obj__Group__2 )
            // InternalTyphonQL.g:1102:2: rule__Obj__Group__1__Impl rule__Obj__Group__2
            {
            pushFollow(FOLLOW_7);
            rule__Obj__Group__1__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FOLLOW_2);
            rule__Obj__Group__2();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Obj__Group__1"


    // $ANTLR start "rule__Obj__Group__1__Impl"
    // InternalTyphonQL.g:1109:1: rule__Obj__Group__1__Impl : ( ( rule__Obj__EidAssignment_1 ) ) ;
    public final void rule__Obj__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTyphonQL.g:1113:1: ( ( ( rule__Obj__EidAssignment_1 ) ) )
            // InternalTyphonQL.g:1114:1: ( ( rule__Obj__EidAssignment_1 ) )
            {
            // InternalTyphonQL.g:1114:1: ( ( rule__Obj__EidAssignment_1 ) )
            // InternalTyphonQL.g:1115:2: ( rule__Obj__EidAssignment_1 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getObjAccess().getEidAssignment_1()); 
            }
            // InternalTyphonQL.g:1116:2: ( rule__Obj__EidAssignment_1 )
            // InternalTyphonQL.g:1116:3: rule__Obj__EidAssignment_1
            {
            pushFollow(FOLLOW_2);
            rule__Obj__EidAssignment_1();

            state._fsp--;
            if (state.failed) return ;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getObjAccess().getEidAssignment_1()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Obj__Group__1__Impl"


    // $ANTLR start "rule__Obj__Group__2"
    // InternalTyphonQL.g:1124:1: rule__Obj__Group__2 : rule__Obj__Group__2__Impl rule__Obj__Group__3 ;
    public final void rule__Obj__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTyphonQL.g:1128:1: ( rule__Obj__Group__2__Impl rule__Obj__Group__3 )
            // InternalTyphonQL.g:1129:2: rule__Obj__Group__2__Impl rule__Obj__Group__3
            {
            pushFollow(FOLLOW_8);
            rule__Obj__Group__2__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FOLLOW_2);
            rule__Obj__Group__3();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Obj__Group__2"


    // $ANTLR start "rule__Obj__Group__2__Impl"
    // InternalTyphonQL.g:1136:1: rule__Obj__Group__2__Impl : ( '{' ) ;
    public final void rule__Obj__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTyphonQL.g:1140:1: ( ( '{' ) )
            // InternalTyphonQL.g:1141:1: ( '{' )
            {
            // InternalTyphonQL.g:1141:1: ( '{' )
            // InternalTyphonQL.g:1142:2: '{'
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getObjAccess().getLeftCurlyBracketKeyword_2()); 
            }
            match(input,14,FOLLOW_2); if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getObjAccess().getLeftCurlyBracketKeyword_2()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Obj__Group__2__Impl"


    // $ANTLR start "rule__Obj__Group__3"
    // InternalTyphonQL.g:1151:1: rule__Obj__Group__3 : rule__Obj__Group__3__Impl rule__Obj__Group__4 ;
    public final void rule__Obj__Group__3() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTyphonQL.g:1155:1: ( rule__Obj__Group__3__Impl rule__Obj__Group__4 )
            // InternalTyphonQL.g:1156:2: rule__Obj__Group__3__Impl rule__Obj__Group__4
            {
            pushFollow(FOLLOW_8);
            rule__Obj__Group__3__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FOLLOW_2);
            rule__Obj__Group__4();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Obj__Group__3"


    // $ANTLR start "rule__Obj__Group__3__Impl"
    // InternalTyphonQL.g:1163:1: rule__Obj__Group__3__Impl : ( ( rule__Obj__SetAssignment_3 )? ) ;
    public final void rule__Obj__Group__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTyphonQL.g:1167:1: ( ( ( rule__Obj__SetAssignment_3 )? ) )
            // InternalTyphonQL.g:1168:1: ( ( rule__Obj__SetAssignment_3 )? )
            {
            // InternalTyphonQL.g:1168:1: ( ( rule__Obj__SetAssignment_3 )? )
            // InternalTyphonQL.g:1169:2: ( rule__Obj__SetAssignment_3 )?
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getObjAccess().getSetAssignment_3()); 
            }
            // InternalTyphonQL.g:1170:2: ( rule__Obj__SetAssignment_3 )?
            int alt12=2;
            int LA12_0 = input.LA(1);

            if ( ((LA12_0>=RULE_VALUE_TERMINAL && LA12_0<=RULE_STRING)||LA12_0==21) ) {
                alt12=1;
            }
            switch (alt12) {
                case 1 :
                    // InternalTyphonQL.g:1170:3: rule__Obj__SetAssignment_3
                    {
                    pushFollow(FOLLOW_2);
                    rule__Obj__SetAssignment_3();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getObjAccess().getSetAssignment_3()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Obj__Group__3__Impl"


    // $ANTLR start "rule__Obj__Group__4"
    // InternalTyphonQL.g:1178:1: rule__Obj__Group__4 : rule__Obj__Group__4__Impl rule__Obj__Group__5 ;
    public final void rule__Obj__Group__4() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTyphonQL.g:1182:1: ( rule__Obj__Group__4__Impl rule__Obj__Group__5 )
            // InternalTyphonQL.g:1183:2: rule__Obj__Group__4__Impl rule__Obj__Group__5
            {
            pushFollow(FOLLOW_8);
            rule__Obj__Group__4__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FOLLOW_2);
            rule__Obj__Group__5();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Obj__Group__4"


    // $ANTLR start "rule__Obj__Group__4__Impl"
    // InternalTyphonQL.g:1190:1: rule__Obj__Group__4__Impl : ( ( rule__Obj__Group_4__0 )* ) ;
    public final void rule__Obj__Group__4__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTyphonQL.g:1194:1: ( ( ( rule__Obj__Group_4__0 )* ) )
            // InternalTyphonQL.g:1195:1: ( ( rule__Obj__Group_4__0 )* )
            {
            // InternalTyphonQL.g:1195:1: ( ( rule__Obj__Group_4__0 )* )
            // InternalTyphonQL.g:1196:2: ( rule__Obj__Group_4__0 )*
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getObjAccess().getGroup_4()); 
            }
            // InternalTyphonQL.g:1197:2: ( rule__Obj__Group_4__0 )*
            loop13:
            do {
                int alt13=2;
                int LA13_0 = input.LA(1);

                if ( (LA13_0==18) ) {
                    alt13=1;
                }


                switch (alt13) {
            	case 1 :
            	    // InternalTyphonQL.g:1197:3: rule__Obj__Group_4__0
            	    {
            	    pushFollow(FOLLOW_9);
            	    rule__Obj__Group_4__0();

            	    state._fsp--;
            	    if (state.failed) return ;

            	    }
            	    break;

            	default :
            	    break loop13;
                }
            } while (true);

            if ( state.backtracking==0 ) {
               after(grammarAccess.getObjAccess().getGroup_4()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Obj__Group__4__Impl"


    // $ANTLR start "rule__Obj__Group__5"
    // InternalTyphonQL.g:1205:1: rule__Obj__Group__5 : rule__Obj__Group__5__Impl ;
    public final void rule__Obj__Group__5() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTyphonQL.g:1209:1: ( rule__Obj__Group__5__Impl )
            // InternalTyphonQL.g:1210:2: rule__Obj__Group__5__Impl
            {
            pushFollow(FOLLOW_2);
            rule__Obj__Group__5__Impl();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Obj__Group__5"


    // $ANTLR start "rule__Obj__Group__5__Impl"
    // InternalTyphonQL.g:1216:1: rule__Obj__Group__5__Impl : ( '}' ) ;
    public final void rule__Obj__Group__5__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTyphonQL.g:1220:1: ( ( '}' ) )
            // InternalTyphonQL.g:1221:1: ( '}' )
            {
            // InternalTyphonQL.g:1221:1: ( '}' )
            // InternalTyphonQL.g:1222:2: '}'
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getObjAccess().getRightCurlyBracketKeyword_5()); 
            }
            match(input,15,FOLLOW_2); if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getObjAccess().getRightCurlyBracketKeyword_5()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Obj__Group__5__Impl"


    // $ANTLR start "rule__Obj__Group_0__0"
    // InternalTyphonQL.g:1232:1: rule__Obj__Group_0__0 : rule__Obj__Group_0__0__Impl rule__Obj__Group_0__1 ;
    public final void rule__Obj__Group_0__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTyphonQL.g:1236:1: ( rule__Obj__Group_0__0__Impl rule__Obj__Group_0__1 )
            // InternalTyphonQL.g:1237:2: rule__Obj__Group_0__0__Impl rule__Obj__Group_0__1
            {
            pushFollow(FOLLOW_5);
            rule__Obj__Group_0__0__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FOLLOW_2);
            rule__Obj__Group_0__1();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Obj__Group_0__0"


    // $ANTLR start "rule__Obj__Group_0__0__Impl"
    // InternalTyphonQL.g:1244:1: rule__Obj__Group_0__0__Impl : ( '@' ) ;
    public final void rule__Obj__Group_0__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTyphonQL.g:1248:1: ( ( '@' ) )
            // InternalTyphonQL.g:1249:1: ( '@' )
            {
            // InternalTyphonQL.g:1249:1: ( '@' )
            // InternalTyphonQL.g:1250:2: '@'
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getObjAccess().getCommercialAtKeyword_0_0()); 
            }
            match(input,21,FOLLOW_2); if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getObjAccess().getCommercialAtKeyword_0_0()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Obj__Group_0__0__Impl"


    // $ANTLR start "rule__Obj__Group_0__1"
    // InternalTyphonQL.g:1259:1: rule__Obj__Group_0__1 : rule__Obj__Group_0__1__Impl ;
    public final void rule__Obj__Group_0__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTyphonQL.g:1263:1: ( rule__Obj__Group_0__1__Impl )
            // InternalTyphonQL.g:1264:2: rule__Obj__Group_0__1__Impl
            {
            pushFollow(FOLLOW_2);
            rule__Obj__Group_0__1__Impl();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Obj__Group_0__1"


    // $ANTLR start "rule__Obj__Group_0__1__Impl"
    // InternalTyphonQL.g:1270:1: rule__Obj__Group_0__1__Impl : ( ( rule__Obj__LabelAssignment_0_1 ) ) ;
    public final void rule__Obj__Group_0__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTyphonQL.g:1274:1: ( ( ( rule__Obj__LabelAssignment_0_1 ) ) )
            // InternalTyphonQL.g:1275:1: ( ( rule__Obj__LabelAssignment_0_1 ) )
            {
            // InternalTyphonQL.g:1275:1: ( ( rule__Obj__LabelAssignment_0_1 ) )
            // InternalTyphonQL.g:1276:2: ( rule__Obj__LabelAssignment_0_1 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getObjAccess().getLabelAssignment_0_1()); 
            }
            // InternalTyphonQL.g:1277:2: ( rule__Obj__LabelAssignment_0_1 )
            // InternalTyphonQL.g:1277:3: rule__Obj__LabelAssignment_0_1
            {
            pushFollow(FOLLOW_2);
            rule__Obj__LabelAssignment_0_1();

            state._fsp--;
            if (state.failed) return ;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getObjAccess().getLabelAssignment_0_1()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Obj__Group_0__1__Impl"


    // $ANTLR start "rule__Obj__Group_4__0"
    // InternalTyphonQL.g:1286:1: rule__Obj__Group_4__0 : rule__Obj__Group_4__0__Impl rule__Obj__Group_4__1 ;
    public final void rule__Obj__Group_4__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTyphonQL.g:1290:1: ( rule__Obj__Group_4__0__Impl rule__Obj__Group_4__1 )
            // InternalTyphonQL.g:1291:2: rule__Obj__Group_4__0__Impl rule__Obj__Group_4__1
            {
            pushFollow(FOLLOW_11);
            rule__Obj__Group_4__0__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FOLLOW_2);
            rule__Obj__Group_4__1();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Obj__Group_4__0"


    // $ANTLR start "rule__Obj__Group_4__0__Impl"
    // InternalTyphonQL.g:1298:1: rule__Obj__Group_4__0__Impl : ( ',' ) ;
    public final void rule__Obj__Group_4__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTyphonQL.g:1302:1: ( ( ',' ) )
            // InternalTyphonQL.g:1303:1: ( ',' )
            {
            // InternalTyphonQL.g:1303:1: ( ',' )
            // InternalTyphonQL.g:1304:2: ','
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getObjAccess().getCommaKeyword_4_0()); 
            }
            match(input,18,FOLLOW_2); if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getObjAccess().getCommaKeyword_4_0()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Obj__Group_4__0__Impl"


    // $ANTLR start "rule__Obj__Group_4__1"
    // InternalTyphonQL.g:1313:1: rule__Obj__Group_4__1 : rule__Obj__Group_4__1__Impl ;
    public final void rule__Obj__Group_4__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTyphonQL.g:1317:1: ( rule__Obj__Group_4__1__Impl )
            // InternalTyphonQL.g:1318:2: rule__Obj__Group_4__1__Impl
            {
            pushFollow(FOLLOW_2);
            rule__Obj__Group_4__1__Impl();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Obj__Group_4__1"


    // $ANTLR start "rule__Obj__Group_4__1__Impl"
    // InternalTyphonQL.g:1324:1: rule__Obj__Group_4__1__Impl : ( ( rule__Obj__SetAssignment_4_1 ) ) ;
    public final void rule__Obj__Group_4__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTyphonQL.g:1328:1: ( ( ( rule__Obj__SetAssignment_4_1 ) ) )
            // InternalTyphonQL.g:1329:1: ( ( rule__Obj__SetAssignment_4_1 ) )
            {
            // InternalTyphonQL.g:1329:1: ( ( rule__Obj__SetAssignment_4_1 ) )
            // InternalTyphonQL.g:1330:2: ( rule__Obj__SetAssignment_4_1 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getObjAccess().getSetAssignment_4_1()); 
            }
            // InternalTyphonQL.g:1331:2: ( rule__Obj__SetAssignment_4_1 )
            // InternalTyphonQL.g:1331:3: rule__Obj__SetAssignment_4_1
            {
            pushFollow(FOLLOW_2);
            rule__Obj__SetAssignment_4_1();

            state._fsp--;
            if (state.failed) return ;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getObjAccess().getSetAssignment_4_1()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Obj__Group_4__1__Impl"


    // $ANTLR start "rule__Queries__QueriesAssignment"
    // InternalTyphonQL.g:1340:1: rule__Queries__QueriesAssignment : ( ( rule__Queries__QueriesAlternatives_0 ) ) ;
    public final void rule__Queries__QueriesAssignment() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTyphonQL.g:1344:1: ( ( ( rule__Queries__QueriesAlternatives_0 ) ) )
            // InternalTyphonQL.g:1345:2: ( ( rule__Queries__QueriesAlternatives_0 ) )
            {
            // InternalTyphonQL.g:1345:2: ( ( rule__Queries__QueriesAlternatives_0 ) )
            // InternalTyphonQL.g:1346:3: ( rule__Queries__QueriesAlternatives_0 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getQueriesAccess().getQueriesAlternatives_0()); 
            }
            // InternalTyphonQL.g:1347:3: ( rule__Queries__QueriesAlternatives_0 )
            // InternalTyphonQL.g:1347:4: rule__Queries__QueriesAlternatives_0
            {
            pushFollow(FOLLOW_2);
            rule__Queries__QueriesAlternatives_0();

            state._fsp--;
            if (state.failed) return ;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getQueriesAccess().getQueriesAlternatives_0()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Queries__QueriesAssignment"


    // $ANTLR start "rule__Update__EidAssignment_1"
    // InternalTyphonQL.g:1355:1: rule__Update__EidAssignment_1 : ( RULE_ID ) ;
    public final void rule__Update__EidAssignment_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTyphonQL.g:1359:1: ( ( RULE_ID ) )
            // InternalTyphonQL.g:1360:2: ( RULE_ID )
            {
            // InternalTyphonQL.g:1360:2: ( RULE_ID )
            // InternalTyphonQL.g:1361:3: RULE_ID
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getUpdateAccess().getEidIDTerminalRuleCall_1_0()); 
            }
            match(input,RULE_ID,FOLLOW_2); if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getUpdateAccess().getEidIDTerminalRuleCall_1_0()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Update__EidAssignment_1"


    // $ANTLR start "rule__Update__VidAssignment_2"
    // InternalTyphonQL.g:1370:1: rule__Update__VidAssignment_2 : ( RULE_ID ) ;
    public final void rule__Update__VidAssignment_2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTyphonQL.g:1374:1: ( ( RULE_ID ) )
            // InternalTyphonQL.g:1375:2: ( RULE_ID )
            {
            // InternalTyphonQL.g:1375:2: ( RULE_ID )
            // InternalTyphonQL.g:1376:3: RULE_ID
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getUpdateAccess().getVidIDTerminalRuleCall_2_0()); 
            }
            match(input,RULE_ID,FOLLOW_2); if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getUpdateAccess().getVidIDTerminalRuleCall_2_0()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Update__VidAssignment_2"


    // $ANTLR start "rule__Update__WhereAssignment_3_1"
    // InternalTyphonQL.g:1385:1: rule__Update__WhereAssignment_3_1 : ( ruleExpr ) ;
    public final void rule__Update__WhereAssignment_3_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTyphonQL.g:1389:1: ( ( ruleExpr ) )
            // InternalTyphonQL.g:1390:2: ( ruleExpr )
            {
            // InternalTyphonQL.g:1390:2: ( ruleExpr )
            // InternalTyphonQL.g:1391:3: ruleExpr
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getUpdateAccess().getWhereExprParserRuleCall_3_1_0()); 
            }
            pushFollow(FOLLOW_2);
            ruleExpr();

            state._fsp--;
            if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getUpdateAccess().getWhereExprParserRuleCall_3_1_0()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Update__WhereAssignment_3_1"


    // $ANTLR start "rule__Update__SetAssignment_6"
    // InternalTyphonQL.g:1400:1: rule__Update__SetAssignment_6 : ( ruleExpr ) ;
    public final void rule__Update__SetAssignment_6() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTyphonQL.g:1404:1: ( ( ruleExpr ) )
            // InternalTyphonQL.g:1405:2: ( ruleExpr )
            {
            // InternalTyphonQL.g:1405:2: ( ruleExpr )
            // InternalTyphonQL.g:1406:3: ruleExpr
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getUpdateAccess().getSetExprParserRuleCall_6_0()); 
            }
            pushFollow(FOLLOW_2);
            ruleExpr();

            state._fsp--;
            if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getUpdateAccess().getSetExprParserRuleCall_6_0()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Update__SetAssignment_6"


    // $ANTLR start "rule__Update__SetAssignment_7_1"
    // InternalTyphonQL.g:1415:1: rule__Update__SetAssignment_7_1 : ( ruleExpr ) ;
    public final void rule__Update__SetAssignment_7_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTyphonQL.g:1419:1: ( ( ruleExpr ) )
            // InternalTyphonQL.g:1420:2: ( ruleExpr )
            {
            // InternalTyphonQL.g:1420:2: ( ruleExpr )
            // InternalTyphonQL.g:1421:3: ruleExpr
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getUpdateAccess().getSetExprParserRuleCall_7_1_0()); 
            }
            pushFollow(FOLLOW_2);
            ruleExpr();

            state._fsp--;
            if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getUpdateAccess().getSetExprParserRuleCall_7_1_0()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Update__SetAssignment_7_1"


    // $ANTLR start "rule__Delete__EidAssignment_1"
    // InternalTyphonQL.g:1430:1: rule__Delete__EidAssignment_1 : ( RULE_ID ) ;
    public final void rule__Delete__EidAssignment_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTyphonQL.g:1434:1: ( ( RULE_ID ) )
            // InternalTyphonQL.g:1435:2: ( RULE_ID )
            {
            // InternalTyphonQL.g:1435:2: ( RULE_ID )
            // InternalTyphonQL.g:1436:3: RULE_ID
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getDeleteAccess().getEidIDTerminalRuleCall_1_0()); 
            }
            match(input,RULE_ID,FOLLOW_2); if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getDeleteAccess().getEidIDTerminalRuleCall_1_0()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Delete__EidAssignment_1"


    // $ANTLR start "rule__Delete__VidAssignment_2"
    // InternalTyphonQL.g:1445:1: rule__Delete__VidAssignment_2 : ( RULE_ID ) ;
    public final void rule__Delete__VidAssignment_2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTyphonQL.g:1449:1: ( ( RULE_ID ) )
            // InternalTyphonQL.g:1450:2: ( RULE_ID )
            {
            // InternalTyphonQL.g:1450:2: ( RULE_ID )
            // InternalTyphonQL.g:1451:3: RULE_ID
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getDeleteAccess().getVidIDTerminalRuleCall_2_0()); 
            }
            match(input,RULE_ID,FOLLOW_2); if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getDeleteAccess().getVidIDTerminalRuleCall_2_0()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Delete__VidAssignment_2"


    // $ANTLR start "rule__Delete__WhereAssignment_3_1"
    // InternalTyphonQL.g:1460:1: rule__Delete__WhereAssignment_3_1 : ( ruleExpr ) ;
    public final void rule__Delete__WhereAssignment_3_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTyphonQL.g:1464:1: ( ( ruleExpr ) )
            // InternalTyphonQL.g:1465:2: ( ruleExpr )
            {
            // InternalTyphonQL.g:1465:2: ( ruleExpr )
            // InternalTyphonQL.g:1466:3: ruleExpr
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getDeleteAccess().getWhereExprParserRuleCall_3_1_0()); 
            }
            pushFollow(FOLLOW_2);
            ruleExpr();

            state._fsp--;
            if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getDeleteAccess().getWhereExprParserRuleCall_3_1_0()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Delete__WhereAssignment_3_1"


    // $ANTLR start "rule__Insert__ObjectsAssignment_1"
    // InternalTyphonQL.g:1475:1: rule__Insert__ObjectsAssignment_1 : ( ruleObj ) ;
    public final void rule__Insert__ObjectsAssignment_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTyphonQL.g:1479:1: ( ( ruleObj ) )
            // InternalTyphonQL.g:1480:2: ( ruleObj )
            {
            // InternalTyphonQL.g:1480:2: ( ruleObj )
            // InternalTyphonQL.g:1481:3: ruleObj
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getInsertAccess().getObjectsObjParserRuleCall_1_0()); 
            }
            pushFollow(FOLLOW_2);
            ruleObj();

            state._fsp--;
            if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getInsertAccess().getObjectsObjParserRuleCall_1_0()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Insert__ObjectsAssignment_1"


    // $ANTLR start "rule__Insert__ObjectsAssignment_2_1"
    // InternalTyphonQL.g:1490:1: rule__Insert__ObjectsAssignment_2_1 : ( ruleObj ) ;
    public final void rule__Insert__ObjectsAssignment_2_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTyphonQL.g:1494:1: ( ( ruleObj ) )
            // InternalTyphonQL.g:1495:2: ( ruleObj )
            {
            // InternalTyphonQL.g:1495:2: ( ruleObj )
            // InternalTyphonQL.g:1496:3: ruleObj
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getInsertAccess().getObjectsObjParserRuleCall_2_1_0()); 
            }
            pushFollow(FOLLOW_2);
            ruleObj();

            state._fsp--;
            if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getInsertAccess().getObjectsObjParserRuleCall_2_1_0()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Insert__ObjectsAssignment_2_1"


    // $ANTLR start "rule__Obj__LabelAssignment_0_1"
    // InternalTyphonQL.g:1505:1: rule__Obj__LabelAssignment_0_1 : ( RULE_ID ) ;
    public final void rule__Obj__LabelAssignment_0_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTyphonQL.g:1509:1: ( ( RULE_ID ) )
            // InternalTyphonQL.g:1510:2: ( RULE_ID )
            {
            // InternalTyphonQL.g:1510:2: ( RULE_ID )
            // InternalTyphonQL.g:1511:3: RULE_ID
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getObjAccess().getLabelIDTerminalRuleCall_0_1_0()); 
            }
            match(input,RULE_ID,FOLLOW_2); if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getObjAccess().getLabelIDTerminalRuleCall_0_1_0()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Obj__LabelAssignment_0_1"


    // $ANTLR start "rule__Obj__EidAssignment_1"
    // InternalTyphonQL.g:1520:1: rule__Obj__EidAssignment_1 : ( RULE_ID ) ;
    public final void rule__Obj__EidAssignment_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTyphonQL.g:1524:1: ( ( RULE_ID ) )
            // InternalTyphonQL.g:1525:2: ( RULE_ID )
            {
            // InternalTyphonQL.g:1525:2: ( RULE_ID )
            // InternalTyphonQL.g:1526:3: RULE_ID
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getObjAccess().getEidIDTerminalRuleCall_1_0()); 
            }
            match(input,RULE_ID,FOLLOW_2); if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getObjAccess().getEidIDTerminalRuleCall_1_0()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Obj__EidAssignment_1"


    // $ANTLR start "rule__Obj__SetAssignment_3"
    // InternalTyphonQL.g:1535:1: rule__Obj__SetAssignment_3 : ( ruleExpr ) ;
    public final void rule__Obj__SetAssignment_3() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTyphonQL.g:1539:1: ( ( ruleExpr ) )
            // InternalTyphonQL.g:1540:2: ( ruleExpr )
            {
            // InternalTyphonQL.g:1540:2: ( ruleExpr )
            // InternalTyphonQL.g:1541:3: ruleExpr
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getObjAccess().getSetExprParserRuleCall_3_0()); 
            }
            pushFollow(FOLLOW_2);
            ruleExpr();

            state._fsp--;
            if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getObjAccess().getSetExprParserRuleCall_3_0()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Obj__SetAssignment_3"


    // $ANTLR start "rule__Obj__SetAssignment_4_1"
    // InternalTyphonQL.g:1550:1: rule__Obj__SetAssignment_4_1 : ( ruleExpr ) ;
    public final void rule__Obj__SetAssignment_4_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTyphonQL.g:1554:1: ( ( ruleExpr ) )
            // InternalTyphonQL.g:1555:2: ( ruleExpr )
            {
            // InternalTyphonQL.g:1555:2: ( ruleExpr )
            // InternalTyphonQL.g:1556:3: ruleExpr
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getObjAccess().getSetExprParserRuleCall_4_1_0()); 
            }
            pushFollow(FOLLOW_2);
            ruleExpr();

            state._fsp--;
            if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getObjAccess().getSetExprParserRuleCall_4_1_0()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Obj__SetAssignment_4_1"


    // $ANTLR start "rule__Expr__ExprsAssignment"
    // InternalTyphonQL.g:1565:1: rule__Expr__ExprsAssignment : ( ( rule__Expr__ExprsAlternatives_0 ) ) ;
    public final void rule__Expr__ExprsAssignment() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTyphonQL.g:1569:1: ( ( ( rule__Expr__ExprsAlternatives_0 ) ) )
            // InternalTyphonQL.g:1570:2: ( ( rule__Expr__ExprsAlternatives_0 ) )
            {
            // InternalTyphonQL.g:1570:2: ( ( rule__Expr__ExprsAlternatives_0 ) )
            // InternalTyphonQL.g:1571:3: ( rule__Expr__ExprsAlternatives_0 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getExprAccess().getExprsAlternatives_0()); 
            }
            // InternalTyphonQL.g:1572:3: ( rule__Expr__ExprsAlternatives_0 )
            // InternalTyphonQL.g:1572:4: rule__Expr__ExprsAlternatives_0
            {
            pushFollow(FOLLOW_2);
            rule__Expr__ExprsAlternatives_0();

            state._fsp--;
            if (state.failed) return ;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getExprAccess().getExprsAlternatives_0()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Expr__ExprsAssignment"


    // $ANTLR start "rule__Stringy__ValsAssignment"
    // InternalTyphonQL.g:1580:1: rule__Stringy__ValsAssignment : ( RULE_STRING ) ;
    public final void rule__Stringy__ValsAssignment() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTyphonQL.g:1584:1: ( ( RULE_STRING ) )
            // InternalTyphonQL.g:1585:2: ( RULE_STRING )
            {
            // InternalTyphonQL.g:1585:2: ( RULE_STRING )
            // InternalTyphonQL.g:1586:3: RULE_STRING
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getStringyAccess().getValsSTRINGTerminalRuleCall_0()); 
            }
            match(input,RULE_STRING,FOLLOW_2); if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getStringyAccess().getValsSTRINGTerminalRuleCall_0()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Stringy__ValsAssignment"


    // $ANTLR start "rule__Variabley__ValsAssignment"
    // InternalTyphonQL.g:1595:1: rule__Variabley__ValsAssignment : ( ( rule__Variabley__ValsAlternatives_0 ) ) ;
    public final void rule__Variabley__ValsAssignment() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTyphonQL.g:1599:1: ( ( ( rule__Variabley__ValsAlternatives_0 ) ) )
            // InternalTyphonQL.g:1600:2: ( ( rule__Variabley__ValsAlternatives_0 ) )
            {
            // InternalTyphonQL.g:1600:2: ( ( rule__Variabley__ValsAlternatives_0 ) )
            // InternalTyphonQL.g:1601:3: ( rule__Variabley__ValsAlternatives_0 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getVariableyAccess().getValsAlternatives_0()); 
            }
            // InternalTyphonQL.g:1602:3: ( rule__Variabley__ValsAlternatives_0 )
            // InternalTyphonQL.g:1602:4: rule__Variabley__ValsAlternatives_0
            {
            pushFollow(FOLLOW_2);
            rule__Variabley__ValsAlternatives_0();

            state._fsp--;
            if (state.failed) return ;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getVariableyAccess().getValsAlternatives_0()); 
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Variabley__ValsAssignment"

    // Delegated rules


 

    public static final BitSet FOLLOW_1 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_2 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_3 = new BitSet(new long[]{0x0000000000181002L});
    public static final BitSet FOLLOW_4 = new BitSet(new long[]{0x00000000002000F2L});
    public static final BitSet FOLLOW_5 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_6 = new BitSet(new long[]{0x0000000000022000L});
    public static final BitSet FOLLOW_7 = new BitSet(new long[]{0x0000000000004000L});
    public static final BitSet FOLLOW_8 = new BitSet(new long[]{0x00000000002480F0L});
    public static final BitSet FOLLOW_9 = new BitSet(new long[]{0x0000000000040002L});
    public static final BitSet FOLLOW_10 = new BitSet(new long[]{0x0000000000010000L});
    public static final BitSet FOLLOW_11 = new BitSet(new long[]{0x00000000002000F0L});
    public static final BitSet FOLLOW_12 = new BitSet(new long[]{0x0000000000030000L});
    public static final BitSet FOLLOW_13 = new BitSet(new long[]{0x0000000000050000L});

}