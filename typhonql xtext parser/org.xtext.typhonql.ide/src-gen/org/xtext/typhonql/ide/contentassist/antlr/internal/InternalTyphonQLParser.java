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
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "RULE_VALUE_TERMINAL", "RULE_STRING", "RULE_ID", "RULE_ANY_OTHER", "RULE_INT", "RULE_ML_COMMENT", "RULE_SL_COMMENT", "RULE_WS", "'update'", "'set'", "'{'", "'}'", "';'", "'where'", "','", "'delete'", "'insert'", "'@'"
    };
    public static final int RULE_VALUE_TERMINAL=4;
    public static final int RULE_STRING=5;
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
    public static final int RULE_ID=6;
    public static final int RULE_WS=11;
    public static final int RULE_ANY_OTHER=7;
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

                if ( ((LA2_0>=RULE_VALUE_TERMINAL && LA2_0<=RULE_ANY_OTHER)||LA2_0==21) ) {
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


    // $ANTLR start "rule__Queries__QueriesAlternatives_0"
    // InternalTyphonQL.g:235:1: rule__Queries__QueriesAlternatives_0 : ( ( ruleInsert ) | ( ruleDelete ) | ( ruleUpdate ) );
    public final void rule__Queries__QueriesAlternatives_0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTyphonQL.g:239:1: ( ( ruleInsert ) | ( ruleDelete ) | ( ruleUpdate ) )
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
                    // InternalTyphonQL.g:240:2: ( ruleInsert )
                    {
                    // InternalTyphonQL.g:240:2: ( ruleInsert )
                    // InternalTyphonQL.g:241:3: ruleInsert
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
                    // InternalTyphonQL.g:246:2: ( ruleDelete )
                    {
                    // InternalTyphonQL.g:246:2: ( ruleDelete )
                    // InternalTyphonQL.g:247:3: ruleDelete
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
                    // InternalTyphonQL.g:252:2: ( ruleUpdate )
                    {
                    // InternalTyphonQL.g:252:2: ( ruleUpdate )
                    // InternalTyphonQL.g:253:3: ruleUpdate
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
    // InternalTyphonQL.g:262:1: rule__Expr__ExprsAlternatives_0 : ( ( ruleStringy ) | ( ruleObj ) );
    public final void rule__Expr__ExprsAlternatives_0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTyphonQL.g:266:1: ( ( ruleStringy ) | ( ruleObj ) )
            int alt4=2;
            switch ( input.LA(1) ) {
            case RULE_VALUE_TERMINAL:
            case RULE_STRING:
            case RULE_ANY_OTHER:
                {
                alt4=1;
                }
                break;
            case RULE_ID:
                {
                int LA4_2 = input.LA(2);

                if ( (LA4_2==14) ) {
                    alt4=2;
                }
                else if ( (LA4_2==EOF||(LA4_2>=RULE_VALUE_TERMINAL && LA4_2<=RULE_ANY_OTHER)||LA4_2==13||(LA4_2>=15 && LA4_2<=16)||LA4_2==18||LA4_2==21) ) {
                    alt4=1;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return ;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 4, 2, input);

                    throw nvae;
                }
                }
                break;
            case 21:
                {
                alt4=2;
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
                    // InternalTyphonQL.g:267:2: ( ruleStringy )
                    {
                    // InternalTyphonQL.g:267:2: ( ruleStringy )
                    // InternalTyphonQL.g:268:3: ruleStringy
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
                    // InternalTyphonQL.g:273:2: ( ruleObj )
                    {
                    // InternalTyphonQL.g:273:2: ( ruleObj )
                    // InternalTyphonQL.g:274:3: ruleObj
                    {
                    if ( state.backtracking==0 ) {
                       before(grammarAccess.getExprAccess().getExprsObjParserRuleCall_0_1()); 
                    }
                    pushFollow(FOLLOW_2);
                    ruleObj();

                    state._fsp--;
                    if (state.failed) return ;
                    if ( state.backtracking==0 ) {
                       after(grammarAccess.getExprAccess().getExprsObjParserRuleCall_0_1()); 
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


    // $ANTLR start "rule__Stringy__ValsAlternatives_0"
    // InternalTyphonQL.g:283:1: rule__Stringy__ValsAlternatives_0 : ( ( RULE_VALUE_TERMINAL ) | ( RULE_STRING ) | ( RULE_ID ) | ( RULE_ANY_OTHER ) );
    public final void rule__Stringy__ValsAlternatives_0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTyphonQL.g:287:1: ( ( RULE_VALUE_TERMINAL ) | ( RULE_STRING ) | ( RULE_ID ) | ( RULE_ANY_OTHER ) )
            int alt5=4;
            switch ( input.LA(1) ) {
            case RULE_VALUE_TERMINAL:
                {
                alt5=1;
                }
                break;
            case RULE_STRING:
                {
                alt5=2;
                }
                break;
            case RULE_ID:
                {
                alt5=3;
                }
                break;
            case RULE_ANY_OTHER:
                {
                alt5=4;
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
                    // InternalTyphonQL.g:288:2: ( RULE_VALUE_TERMINAL )
                    {
                    // InternalTyphonQL.g:288:2: ( RULE_VALUE_TERMINAL )
                    // InternalTyphonQL.g:289:3: RULE_VALUE_TERMINAL
                    {
                    if ( state.backtracking==0 ) {
                       before(grammarAccess.getStringyAccess().getValsVALUE_TERMINALTerminalRuleCall_0_0()); 
                    }
                    match(input,RULE_VALUE_TERMINAL,FOLLOW_2); if (state.failed) return ;
                    if ( state.backtracking==0 ) {
                       after(grammarAccess.getStringyAccess().getValsVALUE_TERMINALTerminalRuleCall_0_0()); 
                    }

                    }


                    }
                    break;
                case 2 :
                    // InternalTyphonQL.g:294:2: ( RULE_STRING )
                    {
                    // InternalTyphonQL.g:294:2: ( RULE_STRING )
                    // InternalTyphonQL.g:295:3: RULE_STRING
                    {
                    if ( state.backtracking==0 ) {
                       before(grammarAccess.getStringyAccess().getValsSTRINGTerminalRuleCall_0_1()); 
                    }
                    match(input,RULE_STRING,FOLLOW_2); if (state.failed) return ;
                    if ( state.backtracking==0 ) {
                       after(grammarAccess.getStringyAccess().getValsSTRINGTerminalRuleCall_0_1()); 
                    }

                    }


                    }
                    break;
                case 3 :
                    // InternalTyphonQL.g:300:2: ( RULE_ID )
                    {
                    // InternalTyphonQL.g:300:2: ( RULE_ID )
                    // InternalTyphonQL.g:301:3: RULE_ID
                    {
                    if ( state.backtracking==0 ) {
                       before(grammarAccess.getStringyAccess().getValsIDTerminalRuleCall_0_2()); 
                    }
                    match(input,RULE_ID,FOLLOW_2); if (state.failed) return ;
                    if ( state.backtracking==0 ) {
                       after(grammarAccess.getStringyAccess().getValsIDTerminalRuleCall_0_2()); 
                    }

                    }


                    }
                    break;
                case 4 :
                    // InternalTyphonQL.g:306:2: ( RULE_ANY_OTHER )
                    {
                    // InternalTyphonQL.g:306:2: ( RULE_ANY_OTHER )
                    // InternalTyphonQL.g:307:3: RULE_ANY_OTHER
                    {
                    if ( state.backtracking==0 ) {
                       before(grammarAccess.getStringyAccess().getValsANY_OTHERTerminalRuleCall_0_3()); 
                    }
                    match(input,RULE_ANY_OTHER,FOLLOW_2); if (state.failed) return ;
                    if ( state.backtracking==0 ) {
                       after(grammarAccess.getStringyAccess().getValsANY_OTHERTerminalRuleCall_0_3()); 
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
    // $ANTLR end "rule__Stringy__ValsAlternatives_0"


    // $ANTLR start "rule__Update__Group__0"
    // InternalTyphonQL.g:316:1: rule__Update__Group__0 : rule__Update__Group__0__Impl rule__Update__Group__1 ;
    public final void rule__Update__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTyphonQL.g:320:1: ( rule__Update__Group__0__Impl rule__Update__Group__1 )
            // InternalTyphonQL.g:321:2: rule__Update__Group__0__Impl rule__Update__Group__1
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
    // InternalTyphonQL.g:328:1: rule__Update__Group__0__Impl : ( 'update' ) ;
    public final void rule__Update__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTyphonQL.g:332:1: ( ( 'update' ) )
            // InternalTyphonQL.g:333:1: ( 'update' )
            {
            // InternalTyphonQL.g:333:1: ( 'update' )
            // InternalTyphonQL.g:334:2: 'update'
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
    // InternalTyphonQL.g:343:1: rule__Update__Group__1 : rule__Update__Group__1__Impl rule__Update__Group__2 ;
    public final void rule__Update__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTyphonQL.g:347:1: ( rule__Update__Group__1__Impl rule__Update__Group__2 )
            // InternalTyphonQL.g:348:2: rule__Update__Group__1__Impl rule__Update__Group__2
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
    // InternalTyphonQL.g:355:1: rule__Update__Group__1__Impl : ( ( rule__Update__EidAssignment_1 ) ) ;
    public final void rule__Update__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTyphonQL.g:359:1: ( ( ( rule__Update__EidAssignment_1 ) ) )
            // InternalTyphonQL.g:360:1: ( ( rule__Update__EidAssignment_1 ) )
            {
            // InternalTyphonQL.g:360:1: ( ( rule__Update__EidAssignment_1 ) )
            // InternalTyphonQL.g:361:2: ( rule__Update__EidAssignment_1 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getUpdateAccess().getEidAssignment_1()); 
            }
            // InternalTyphonQL.g:362:2: ( rule__Update__EidAssignment_1 )
            // InternalTyphonQL.g:362:3: rule__Update__EidAssignment_1
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
    // InternalTyphonQL.g:370:1: rule__Update__Group__2 : rule__Update__Group__2__Impl rule__Update__Group__3 ;
    public final void rule__Update__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTyphonQL.g:374:1: ( rule__Update__Group__2__Impl rule__Update__Group__3 )
            // InternalTyphonQL.g:375:2: rule__Update__Group__2__Impl rule__Update__Group__3
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
    // InternalTyphonQL.g:382:1: rule__Update__Group__2__Impl : ( ( rule__Update__VidAssignment_2 ) ) ;
    public final void rule__Update__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTyphonQL.g:386:1: ( ( ( rule__Update__VidAssignment_2 ) ) )
            // InternalTyphonQL.g:387:1: ( ( rule__Update__VidAssignment_2 ) )
            {
            // InternalTyphonQL.g:387:1: ( ( rule__Update__VidAssignment_2 ) )
            // InternalTyphonQL.g:388:2: ( rule__Update__VidAssignment_2 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getUpdateAccess().getVidAssignment_2()); 
            }
            // InternalTyphonQL.g:389:2: ( rule__Update__VidAssignment_2 )
            // InternalTyphonQL.g:389:3: rule__Update__VidAssignment_2
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
    // InternalTyphonQL.g:397:1: rule__Update__Group__3 : rule__Update__Group__3__Impl rule__Update__Group__4 ;
    public final void rule__Update__Group__3() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTyphonQL.g:401:1: ( rule__Update__Group__3__Impl rule__Update__Group__4 )
            // InternalTyphonQL.g:402:2: rule__Update__Group__3__Impl rule__Update__Group__4
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
    // InternalTyphonQL.g:409:1: rule__Update__Group__3__Impl : ( ( rule__Update__Group_3__0 )? ) ;
    public final void rule__Update__Group__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTyphonQL.g:413:1: ( ( ( rule__Update__Group_3__0 )? ) )
            // InternalTyphonQL.g:414:1: ( ( rule__Update__Group_3__0 )? )
            {
            // InternalTyphonQL.g:414:1: ( ( rule__Update__Group_3__0 )? )
            // InternalTyphonQL.g:415:2: ( rule__Update__Group_3__0 )?
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getUpdateAccess().getGroup_3()); 
            }
            // InternalTyphonQL.g:416:2: ( rule__Update__Group_3__0 )?
            int alt6=2;
            int LA6_0 = input.LA(1);

            if ( (LA6_0==17) ) {
                alt6=1;
            }
            switch (alt6) {
                case 1 :
                    // InternalTyphonQL.g:416:3: rule__Update__Group_3__0
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
    // InternalTyphonQL.g:424:1: rule__Update__Group__4 : rule__Update__Group__4__Impl rule__Update__Group__5 ;
    public final void rule__Update__Group__4() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTyphonQL.g:428:1: ( rule__Update__Group__4__Impl rule__Update__Group__5 )
            // InternalTyphonQL.g:429:2: rule__Update__Group__4__Impl rule__Update__Group__5
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
    // InternalTyphonQL.g:436:1: rule__Update__Group__4__Impl : ( 'set' ) ;
    public final void rule__Update__Group__4__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTyphonQL.g:440:1: ( ( 'set' ) )
            // InternalTyphonQL.g:441:1: ( 'set' )
            {
            // InternalTyphonQL.g:441:1: ( 'set' )
            // InternalTyphonQL.g:442:2: 'set'
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
    // InternalTyphonQL.g:451:1: rule__Update__Group__5 : rule__Update__Group__5__Impl rule__Update__Group__6 ;
    public final void rule__Update__Group__5() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTyphonQL.g:455:1: ( rule__Update__Group__5__Impl rule__Update__Group__6 )
            // InternalTyphonQL.g:456:2: rule__Update__Group__5__Impl rule__Update__Group__6
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
    // InternalTyphonQL.g:463:1: rule__Update__Group__5__Impl : ( '{' ) ;
    public final void rule__Update__Group__5__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTyphonQL.g:467:1: ( ( '{' ) )
            // InternalTyphonQL.g:468:1: ( '{' )
            {
            // InternalTyphonQL.g:468:1: ( '{' )
            // InternalTyphonQL.g:469:2: '{'
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
    // InternalTyphonQL.g:478:1: rule__Update__Group__6 : rule__Update__Group__6__Impl rule__Update__Group__7 ;
    public final void rule__Update__Group__6() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTyphonQL.g:482:1: ( rule__Update__Group__6__Impl rule__Update__Group__7 )
            // InternalTyphonQL.g:483:2: rule__Update__Group__6__Impl rule__Update__Group__7
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
    // InternalTyphonQL.g:490:1: rule__Update__Group__6__Impl : ( ( rule__Update__SetAssignment_6 )? ) ;
    public final void rule__Update__Group__6__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTyphonQL.g:494:1: ( ( ( rule__Update__SetAssignment_6 )? ) )
            // InternalTyphonQL.g:495:1: ( ( rule__Update__SetAssignment_6 )? )
            {
            // InternalTyphonQL.g:495:1: ( ( rule__Update__SetAssignment_6 )? )
            // InternalTyphonQL.g:496:2: ( rule__Update__SetAssignment_6 )?
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getUpdateAccess().getSetAssignment_6()); 
            }
            // InternalTyphonQL.g:497:2: ( rule__Update__SetAssignment_6 )?
            int alt7=2;
            int LA7_0 = input.LA(1);

            if ( ((LA7_0>=RULE_VALUE_TERMINAL && LA7_0<=RULE_ANY_OTHER)||LA7_0==21) ) {
                alt7=1;
            }
            switch (alt7) {
                case 1 :
                    // InternalTyphonQL.g:497:3: rule__Update__SetAssignment_6
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
    // InternalTyphonQL.g:505:1: rule__Update__Group__7 : rule__Update__Group__7__Impl rule__Update__Group__8 ;
    public final void rule__Update__Group__7() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTyphonQL.g:509:1: ( rule__Update__Group__7__Impl rule__Update__Group__8 )
            // InternalTyphonQL.g:510:2: rule__Update__Group__7__Impl rule__Update__Group__8
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
    // InternalTyphonQL.g:517:1: rule__Update__Group__7__Impl : ( ( rule__Update__Group_7__0 )* ) ;
    public final void rule__Update__Group__7__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTyphonQL.g:521:1: ( ( ( rule__Update__Group_7__0 )* ) )
            // InternalTyphonQL.g:522:1: ( ( rule__Update__Group_7__0 )* )
            {
            // InternalTyphonQL.g:522:1: ( ( rule__Update__Group_7__0 )* )
            // InternalTyphonQL.g:523:2: ( rule__Update__Group_7__0 )*
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getUpdateAccess().getGroup_7()); 
            }
            // InternalTyphonQL.g:524:2: ( rule__Update__Group_7__0 )*
            loop8:
            do {
                int alt8=2;
                int LA8_0 = input.LA(1);

                if ( (LA8_0==18) ) {
                    alt8=1;
                }


                switch (alt8) {
            	case 1 :
            	    // InternalTyphonQL.g:524:3: rule__Update__Group_7__0
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
    // InternalTyphonQL.g:532:1: rule__Update__Group__8 : rule__Update__Group__8__Impl rule__Update__Group__9 ;
    public final void rule__Update__Group__8() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTyphonQL.g:536:1: ( rule__Update__Group__8__Impl rule__Update__Group__9 )
            // InternalTyphonQL.g:537:2: rule__Update__Group__8__Impl rule__Update__Group__9
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
    // InternalTyphonQL.g:544:1: rule__Update__Group__8__Impl : ( '}' ) ;
    public final void rule__Update__Group__8__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTyphonQL.g:548:1: ( ( '}' ) )
            // InternalTyphonQL.g:549:1: ( '}' )
            {
            // InternalTyphonQL.g:549:1: ( '}' )
            // InternalTyphonQL.g:550:2: '}'
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
    // InternalTyphonQL.g:559:1: rule__Update__Group__9 : rule__Update__Group__9__Impl ;
    public final void rule__Update__Group__9() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTyphonQL.g:563:1: ( rule__Update__Group__9__Impl )
            // InternalTyphonQL.g:564:2: rule__Update__Group__9__Impl
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
    // InternalTyphonQL.g:570:1: rule__Update__Group__9__Impl : ( ';' ) ;
    public final void rule__Update__Group__9__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTyphonQL.g:574:1: ( ( ';' ) )
            // InternalTyphonQL.g:575:1: ( ';' )
            {
            // InternalTyphonQL.g:575:1: ( ';' )
            // InternalTyphonQL.g:576:2: ';'
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
    // InternalTyphonQL.g:586:1: rule__Update__Group_3__0 : rule__Update__Group_3__0__Impl rule__Update__Group_3__1 ;
    public final void rule__Update__Group_3__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTyphonQL.g:590:1: ( rule__Update__Group_3__0__Impl rule__Update__Group_3__1 )
            // InternalTyphonQL.g:591:2: rule__Update__Group_3__0__Impl rule__Update__Group_3__1
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
    // InternalTyphonQL.g:598:1: rule__Update__Group_3__0__Impl : ( 'where' ) ;
    public final void rule__Update__Group_3__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTyphonQL.g:602:1: ( ( 'where' ) )
            // InternalTyphonQL.g:603:1: ( 'where' )
            {
            // InternalTyphonQL.g:603:1: ( 'where' )
            // InternalTyphonQL.g:604:2: 'where'
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
    // InternalTyphonQL.g:613:1: rule__Update__Group_3__1 : rule__Update__Group_3__1__Impl ;
    public final void rule__Update__Group_3__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTyphonQL.g:617:1: ( rule__Update__Group_3__1__Impl )
            // InternalTyphonQL.g:618:2: rule__Update__Group_3__1__Impl
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
    // InternalTyphonQL.g:624:1: rule__Update__Group_3__1__Impl : ( ( rule__Update__WhereAssignment_3_1 ) ) ;
    public final void rule__Update__Group_3__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTyphonQL.g:628:1: ( ( ( rule__Update__WhereAssignment_3_1 ) ) )
            // InternalTyphonQL.g:629:1: ( ( rule__Update__WhereAssignment_3_1 ) )
            {
            // InternalTyphonQL.g:629:1: ( ( rule__Update__WhereAssignment_3_1 ) )
            // InternalTyphonQL.g:630:2: ( rule__Update__WhereAssignment_3_1 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getUpdateAccess().getWhereAssignment_3_1()); 
            }
            // InternalTyphonQL.g:631:2: ( rule__Update__WhereAssignment_3_1 )
            // InternalTyphonQL.g:631:3: rule__Update__WhereAssignment_3_1
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
    // InternalTyphonQL.g:640:1: rule__Update__Group_7__0 : rule__Update__Group_7__0__Impl rule__Update__Group_7__1 ;
    public final void rule__Update__Group_7__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTyphonQL.g:644:1: ( rule__Update__Group_7__0__Impl rule__Update__Group_7__1 )
            // InternalTyphonQL.g:645:2: rule__Update__Group_7__0__Impl rule__Update__Group_7__1
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
    // InternalTyphonQL.g:652:1: rule__Update__Group_7__0__Impl : ( ',' ) ;
    public final void rule__Update__Group_7__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTyphonQL.g:656:1: ( ( ',' ) )
            // InternalTyphonQL.g:657:1: ( ',' )
            {
            // InternalTyphonQL.g:657:1: ( ',' )
            // InternalTyphonQL.g:658:2: ','
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
    // InternalTyphonQL.g:667:1: rule__Update__Group_7__1 : rule__Update__Group_7__1__Impl ;
    public final void rule__Update__Group_7__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTyphonQL.g:671:1: ( rule__Update__Group_7__1__Impl )
            // InternalTyphonQL.g:672:2: rule__Update__Group_7__1__Impl
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
    // InternalTyphonQL.g:678:1: rule__Update__Group_7__1__Impl : ( ( rule__Update__SetAssignment_7_1 ) ) ;
    public final void rule__Update__Group_7__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTyphonQL.g:682:1: ( ( ( rule__Update__SetAssignment_7_1 ) ) )
            // InternalTyphonQL.g:683:1: ( ( rule__Update__SetAssignment_7_1 ) )
            {
            // InternalTyphonQL.g:683:1: ( ( rule__Update__SetAssignment_7_1 ) )
            // InternalTyphonQL.g:684:2: ( rule__Update__SetAssignment_7_1 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getUpdateAccess().getSetAssignment_7_1()); 
            }
            // InternalTyphonQL.g:685:2: ( rule__Update__SetAssignment_7_1 )
            // InternalTyphonQL.g:685:3: rule__Update__SetAssignment_7_1
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
    // InternalTyphonQL.g:694:1: rule__Delete__Group__0 : rule__Delete__Group__0__Impl rule__Delete__Group__1 ;
    public final void rule__Delete__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTyphonQL.g:698:1: ( rule__Delete__Group__0__Impl rule__Delete__Group__1 )
            // InternalTyphonQL.g:699:2: rule__Delete__Group__0__Impl rule__Delete__Group__1
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
    // InternalTyphonQL.g:706:1: rule__Delete__Group__0__Impl : ( 'delete' ) ;
    public final void rule__Delete__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTyphonQL.g:710:1: ( ( 'delete' ) )
            // InternalTyphonQL.g:711:1: ( 'delete' )
            {
            // InternalTyphonQL.g:711:1: ( 'delete' )
            // InternalTyphonQL.g:712:2: 'delete'
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
    // InternalTyphonQL.g:721:1: rule__Delete__Group__1 : rule__Delete__Group__1__Impl rule__Delete__Group__2 ;
    public final void rule__Delete__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTyphonQL.g:725:1: ( rule__Delete__Group__1__Impl rule__Delete__Group__2 )
            // InternalTyphonQL.g:726:2: rule__Delete__Group__1__Impl rule__Delete__Group__2
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
    // InternalTyphonQL.g:733:1: rule__Delete__Group__1__Impl : ( ( rule__Delete__EidAssignment_1 ) ) ;
    public final void rule__Delete__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTyphonQL.g:737:1: ( ( ( rule__Delete__EidAssignment_1 ) ) )
            // InternalTyphonQL.g:738:1: ( ( rule__Delete__EidAssignment_1 ) )
            {
            // InternalTyphonQL.g:738:1: ( ( rule__Delete__EidAssignment_1 ) )
            // InternalTyphonQL.g:739:2: ( rule__Delete__EidAssignment_1 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getDeleteAccess().getEidAssignment_1()); 
            }
            // InternalTyphonQL.g:740:2: ( rule__Delete__EidAssignment_1 )
            // InternalTyphonQL.g:740:3: rule__Delete__EidAssignment_1
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
    // InternalTyphonQL.g:748:1: rule__Delete__Group__2 : rule__Delete__Group__2__Impl rule__Delete__Group__3 ;
    public final void rule__Delete__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTyphonQL.g:752:1: ( rule__Delete__Group__2__Impl rule__Delete__Group__3 )
            // InternalTyphonQL.g:753:2: rule__Delete__Group__2__Impl rule__Delete__Group__3
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
    // InternalTyphonQL.g:760:1: rule__Delete__Group__2__Impl : ( ( rule__Delete__VidAssignment_2 ) ) ;
    public final void rule__Delete__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTyphonQL.g:764:1: ( ( ( rule__Delete__VidAssignment_2 ) ) )
            // InternalTyphonQL.g:765:1: ( ( rule__Delete__VidAssignment_2 ) )
            {
            // InternalTyphonQL.g:765:1: ( ( rule__Delete__VidAssignment_2 ) )
            // InternalTyphonQL.g:766:2: ( rule__Delete__VidAssignment_2 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getDeleteAccess().getVidAssignment_2()); 
            }
            // InternalTyphonQL.g:767:2: ( rule__Delete__VidAssignment_2 )
            // InternalTyphonQL.g:767:3: rule__Delete__VidAssignment_2
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
    // InternalTyphonQL.g:775:1: rule__Delete__Group__3 : rule__Delete__Group__3__Impl rule__Delete__Group__4 ;
    public final void rule__Delete__Group__3() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTyphonQL.g:779:1: ( rule__Delete__Group__3__Impl rule__Delete__Group__4 )
            // InternalTyphonQL.g:780:2: rule__Delete__Group__3__Impl rule__Delete__Group__4
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
    // InternalTyphonQL.g:787:1: rule__Delete__Group__3__Impl : ( ( rule__Delete__Group_3__0 )? ) ;
    public final void rule__Delete__Group__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTyphonQL.g:791:1: ( ( ( rule__Delete__Group_3__0 )? ) )
            // InternalTyphonQL.g:792:1: ( ( rule__Delete__Group_3__0 )? )
            {
            // InternalTyphonQL.g:792:1: ( ( rule__Delete__Group_3__0 )? )
            // InternalTyphonQL.g:793:2: ( rule__Delete__Group_3__0 )?
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getDeleteAccess().getGroup_3()); 
            }
            // InternalTyphonQL.g:794:2: ( rule__Delete__Group_3__0 )?
            int alt9=2;
            int LA9_0 = input.LA(1);

            if ( (LA9_0==17) ) {
                alt9=1;
            }
            switch (alt9) {
                case 1 :
                    // InternalTyphonQL.g:794:3: rule__Delete__Group_3__0
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
    // InternalTyphonQL.g:802:1: rule__Delete__Group__4 : rule__Delete__Group__4__Impl ;
    public final void rule__Delete__Group__4() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTyphonQL.g:806:1: ( rule__Delete__Group__4__Impl )
            // InternalTyphonQL.g:807:2: rule__Delete__Group__4__Impl
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
    // InternalTyphonQL.g:813:1: rule__Delete__Group__4__Impl : ( ';' ) ;
    public final void rule__Delete__Group__4__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTyphonQL.g:817:1: ( ( ';' ) )
            // InternalTyphonQL.g:818:1: ( ';' )
            {
            // InternalTyphonQL.g:818:1: ( ';' )
            // InternalTyphonQL.g:819:2: ';'
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
    // InternalTyphonQL.g:829:1: rule__Delete__Group_3__0 : rule__Delete__Group_3__0__Impl rule__Delete__Group_3__1 ;
    public final void rule__Delete__Group_3__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTyphonQL.g:833:1: ( rule__Delete__Group_3__0__Impl rule__Delete__Group_3__1 )
            // InternalTyphonQL.g:834:2: rule__Delete__Group_3__0__Impl rule__Delete__Group_3__1
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
    // InternalTyphonQL.g:841:1: rule__Delete__Group_3__0__Impl : ( 'where' ) ;
    public final void rule__Delete__Group_3__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTyphonQL.g:845:1: ( ( 'where' ) )
            // InternalTyphonQL.g:846:1: ( 'where' )
            {
            // InternalTyphonQL.g:846:1: ( 'where' )
            // InternalTyphonQL.g:847:2: 'where'
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
    // InternalTyphonQL.g:856:1: rule__Delete__Group_3__1 : rule__Delete__Group_3__1__Impl ;
    public final void rule__Delete__Group_3__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTyphonQL.g:860:1: ( rule__Delete__Group_3__1__Impl )
            // InternalTyphonQL.g:861:2: rule__Delete__Group_3__1__Impl
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
    // InternalTyphonQL.g:867:1: rule__Delete__Group_3__1__Impl : ( ( rule__Delete__WhereAssignment_3_1 ) ) ;
    public final void rule__Delete__Group_3__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTyphonQL.g:871:1: ( ( ( rule__Delete__WhereAssignment_3_1 ) ) )
            // InternalTyphonQL.g:872:1: ( ( rule__Delete__WhereAssignment_3_1 ) )
            {
            // InternalTyphonQL.g:872:1: ( ( rule__Delete__WhereAssignment_3_1 ) )
            // InternalTyphonQL.g:873:2: ( rule__Delete__WhereAssignment_3_1 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getDeleteAccess().getWhereAssignment_3_1()); 
            }
            // InternalTyphonQL.g:874:2: ( rule__Delete__WhereAssignment_3_1 )
            // InternalTyphonQL.g:874:3: rule__Delete__WhereAssignment_3_1
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
    // InternalTyphonQL.g:883:1: rule__Insert__Group__0 : rule__Insert__Group__0__Impl rule__Insert__Group__1 ;
    public final void rule__Insert__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTyphonQL.g:887:1: ( rule__Insert__Group__0__Impl rule__Insert__Group__1 )
            // InternalTyphonQL.g:888:2: rule__Insert__Group__0__Impl rule__Insert__Group__1
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
    // InternalTyphonQL.g:895:1: rule__Insert__Group__0__Impl : ( 'insert' ) ;
    public final void rule__Insert__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTyphonQL.g:899:1: ( ( 'insert' ) )
            // InternalTyphonQL.g:900:1: ( 'insert' )
            {
            // InternalTyphonQL.g:900:1: ( 'insert' )
            // InternalTyphonQL.g:901:2: 'insert'
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
    // InternalTyphonQL.g:910:1: rule__Insert__Group__1 : rule__Insert__Group__1__Impl rule__Insert__Group__2 ;
    public final void rule__Insert__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTyphonQL.g:914:1: ( rule__Insert__Group__1__Impl rule__Insert__Group__2 )
            // InternalTyphonQL.g:915:2: rule__Insert__Group__1__Impl rule__Insert__Group__2
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
    // InternalTyphonQL.g:922:1: rule__Insert__Group__1__Impl : ( ( rule__Insert__ObjectsAssignment_1 ) ) ;
    public final void rule__Insert__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTyphonQL.g:926:1: ( ( ( rule__Insert__ObjectsAssignment_1 ) ) )
            // InternalTyphonQL.g:927:1: ( ( rule__Insert__ObjectsAssignment_1 ) )
            {
            // InternalTyphonQL.g:927:1: ( ( rule__Insert__ObjectsAssignment_1 ) )
            // InternalTyphonQL.g:928:2: ( rule__Insert__ObjectsAssignment_1 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getInsertAccess().getObjectsAssignment_1()); 
            }
            // InternalTyphonQL.g:929:2: ( rule__Insert__ObjectsAssignment_1 )
            // InternalTyphonQL.g:929:3: rule__Insert__ObjectsAssignment_1
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
    // InternalTyphonQL.g:937:1: rule__Insert__Group__2 : rule__Insert__Group__2__Impl rule__Insert__Group__3 ;
    public final void rule__Insert__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTyphonQL.g:941:1: ( rule__Insert__Group__2__Impl rule__Insert__Group__3 )
            // InternalTyphonQL.g:942:2: rule__Insert__Group__2__Impl rule__Insert__Group__3
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
    // InternalTyphonQL.g:949:1: rule__Insert__Group__2__Impl : ( ( rule__Insert__Group_2__0 )* ) ;
    public final void rule__Insert__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTyphonQL.g:953:1: ( ( ( rule__Insert__Group_2__0 )* ) )
            // InternalTyphonQL.g:954:1: ( ( rule__Insert__Group_2__0 )* )
            {
            // InternalTyphonQL.g:954:1: ( ( rule__Insert__Group_2__0 )* )
            // InternalTyphonQL.g:955:2: ( rule__Insert__Group_2__0 )*
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getInsertAccess().getGroup_2()); 
            }
            // InternalTyphonQL.g:956:2: ( rule__Insert__Group_2__0 )*
            loop10:
            do {
                int alt10=2;
                int LA10_0 = input.LA(1);

                if ( (LA10_0==18) ) {
                    alt10=1;
                }


                switch (alt10) {
            	case 1 :
            	    // InternalTyphonQL.g:956:3: rule__Insert__Group_2__0
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
    // InternalTyphonQL.g:964:1: rule__Insert__Group__3 : rule__Insert__Group__3__Impl ;
    public final void rule__Insert__Group__3() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTyphonQL.g:968:1: ( rule__Insert__Group__3__Impl )
            // InternalTyphonQL.g:969:2: rule__Insert__Group__3__Impl
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
    // InternalTyphonQL.g:975:1: rule__Insert__Group__3__Impl : ( ';' ) ;
    public final void rule__Insert__Group__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTyphonQL.g:979:1: ( ( ';' ) )
            // InternalTyphonQL.g:980:1: ( ';' )
            {
            // InternalTyphonQL.g:980:1: ( ';' )
            // InternalTyphonQL.g:981:2: ';'
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
    // InternalTyphonQL.g:991:1: rule__Insert__Group_2__0 : rule__Insert__Group_2__0__Impl rule__Insert__Group_2__1 ;
    public final void rule__Insert__Group_2__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTyphonQL.g:995:1: ( rule__Insert__Group_2__0__Impl rule__Insert__Group_2__1 )
            // InternalTyphonQL.g:996:2: rule__Insert__Group_2__0__Impl rule__Insert__Group_2__1
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
    // InternalTyphonQL.g:1003:1: rule__Insert__Group_2__0__Impl : ( ',' ) ;
    public final void rule__Insert__Group_2__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTyphonQL.g:1007:1: ( ( ',' ) )
            // InternalTyphonQL.g:1008:1: ( ',' )
            {
            // InternalTyphonQL.g:1008:1: ( ',' )
            // InternalTyphonQL.g:1009:2: ','
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
    // InternalTyphonQL.g:1018:1: rule__Insert__Group_2__1 : rule__Insert__Group_2__1__Impl ;
    public final void rule__Insert__Group_2__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTyphonQL.g:1022:1: ( rule__Insert__Group_2__1__Impl )
            // InternalTyphonQL.g:1023:2: rule__Insert__Group_2__1__Impl
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
    // InternalTyphonQL.g:1029:1: rule__Insert__Group_2__1__Impl : ( ( rule__Insert__ObjectsAssignment_2_1 ) ) ;
    public final void rule__Insert__Group_2__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTyphonQL.g:1033:1: ( ( ( rule__Insert__ObjectsAssignment_2_1 ) ) )
            // InternalTyphonQL.g:1034:1: ( ( rule__Insert__ObjectsAssignment_2_1 ) )
            {
            // InternalTyphonQL.g:1034:1: ( ( rule__Insert__ObjectsAssignment_2_1 ) )
            // InternalTyphonQL.g:1035:2: ( rule__Insert__ObjectsAssignment_2_1 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getInsertAccess().getObjectsAssignment_2_1()); 
            }
            // InternalTyphonQL.g:1036:2: ( rule__Insert__ObjectsAssignment_2_1 )
            // InternalTyphonQL.g:1036:3: rule__Insert__ObjectsAssignment_2_1
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
    // InternalTyphonQL.g:1045:1: rule__Obj__Group__0 : rule__Obj__Group__0__Impl rule__Obj__Group__1 ;
    public final void rule__Obj__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTyphonQL.g:1049:1: ( rule__Obj__Group__0__Impl rule__Obj__Group__1 )
            // InternalTyphonQL.g:1050:2: rule__Obj__Group__0__Impl rule__Obj__Group__1
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
    // InternalTyphonQL.g:1057:1: rule__Obj__Group__0__Impl : ( ( rule__Obj__Group_0__0 )? ) ;
    public final void rule__Obj__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTyphonQL.g:1061:1: ( ( ( rule__Obj__Group_0__0 )? ) )
            // InternalTyphonQL.g:1062:1: ( ( rule__Obj__Group_0__0 )? )
            {
            // InternalTyphonQL.g:1062:1: ( ( rule__Obj__Group_0__0 )? )
            // InternalTyphonQL.g:1063:2: ( rule__Obj__Group_0__0 )?
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getObjAccess().getGroup_0()); 
            }
            // InternalTyphonQL.g:1064:2: ( rule__Obj__Group_0__0 )?
            int alt11=2;
            int LA11_0 = input.LA(1);

            if ( (LA11_0==21) ) {
                alt11=1;
            }
            switch (alt11) {
                case 1 :
                    // InternalTyphonQL.g:1064:3: rule__Obj__Group_0__0
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
    // InternalTyphonQL.g:1072:1: rule__Obj__Group__1 : rule__Obj__Group__1__Impl rule__Obj__Group__2 ;
    public final void rule__Obj__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTyphonQL.g:1076:1: ( rule__Obj__Group__1__Impl rule__Obj__Group__2 )
            // InternalTyphonQL.g:1077:2: rule__Obj__Group__1__Impl rule__Obj__Group__2
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
    // InternalTyphonQL.g:1084:1: rule__Obj__Group__1__Impl : ( ( rule__Obj__EidAssignment_1 ) ) ;
    public final void rule__Obj__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTyphonQL.g:1088:1: ( ( ( rule__Obj__EidAssignment_1 ) ) )
            // InternalTyphonQL.g:1089:1: ( ( rule__Obj__EidAssignment_1 ) )
            {
            // InternalTyphonQL.g:1089:1: ( ( rule__Obj__EidAssignment_1 ) )
            // InternalTyphonQL.g:1090:2: ( rule__Obj__EidAssignment_1 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getObjAccess().getEidAssignment_1()); 
            }
            // InternalTyphonQL.g:1091:2: ( rule__Obj__EidAssignment_1 )
            // InternalTyphonQL.g:1091:3: rule__Obj__EidAssignment_1
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
    // InternalTyphonQL.g:1099:1: rule__Obj__Group__2 : rule__Obj__Group__2__Impl rule__Obj__Group__3 ;
    public final void rule__Obj__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTyphonQL.g:1103:1: ( rule__Obj__Group__2__Impl rule__Obj__Group__3 )
            // InternalTyphonQL.g:1104:2: rule__Obj__Group__2__Impl rule__Obj__Group__3
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
    // InternalTyphonQL.g:1111:1: rule__Obj__Group__2__Impl : ( '{' ) ;
    public final void rule__Obj__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTyphonQL.g:1115:1: ( ( '{' ) )
            // InternalTyphonQL.g:1116:1: ( '{' )
            {
            // InternalTyphonQL.g:1116:1: ( '{' )
            // InternalTyphonQL.g:1117:2: '{'
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
    // InternalTyphonQL.g:1126:1: rule__Obj__Group__3 : rule__Obj__Group__3__Impl rule__Obj__Group__4 ;
    public final void rule__Obj__Group__3() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTyphonQL.g:1130:1: ( rule__Obj__Group__3__Impl rule__Obj__Group__4 )
            // InternalTyphonQL.g:1131:2: rule__Obj__Group__3__Impl rule__Obj__Group__4
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
    // InternalTyphonQL.g:1138:1: rule__Obj__Group__3__Impl : ( ( rule__Obj__SetAssignment_3 )? ) ;
    public final void rule__Obj__Group__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTyphonQL.g:1142:1: ( ( ( rule__Obj__SetAssignment_3 )? ) )
            // InternalTyphonQL.g:1143:1: ( ( rule__Obj__SetAssignment_3 )? )
            {
            // InternalTyphonQL.g:1143:1: ( ( rule__Obj__SetAssignment_3 )? )
            // InternalTyphonQL.g:1144:2: ( rule__Obj__SetAssignment_3 )?
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getObjAccess().getSetAssignment_3()); 
            }
            // InternalTyphonQL.g:1145:2: ( rule__Obj__SetAssignment_3 )?
            int alt12=2;
            int LA12_0 = input.LA(1);

            if ( ((LA12_0>=RULE_VALUE_TERMINAL && LA12_0<=RULE_ANY_OTHER)||LA12_0==21) ) {
                alt12=1;
            }
            switch (alt12) {
                case 1 :
                    // InternalTyphonQL.g:1145:3: rule__Obj__SetAssignment_3
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
    // InternalTyphonQL.g:1153:1: rule__Obj__Group__4 : rule__Obj__Group__4__Impl rule__Obj__Group__5 ;
    public final void rule__Obj__Group__4() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTyphonQL.g:1157:1: ( rule__Obj__Group__4__Impl rule__Obj__Group__5 )
            // InternalTyphonQL.g:1158:2: rule__Obj__Group__4__Impl rule__Obj__Group__5
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
    // InternalTyphonQL.g:1165:1: rule__Obj__Group__4__Impl : ( ( rule__Obj__Group_4__0 )* ) ;
    public final void rule__Obj__Group__4__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTyphonQL.g:1169:1: ( ( ( rule__Obj__Group_4__0 )* ) )
            // InternalTyphonQL.g:1170:1: ( ( rule__Obj__Group_4__0 )* )
            {
            // InternalTyphonQL.g:1170:1: ( ( rule__Obj__Group_4__0 )* )
            // InternalTyphonQL.g:1171:2: ( rule__Obj__Group_4__0 )*
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getObjAccess().getGroup_4()); 
            }
            // InternalTyphonQL.g:1172:2: ( rule__Obj__Group_4__0 )*
            loop13:
            do {
                int alt13=2;
                int LA13_0 = input.LA(1);

                if ( (LA13_0==18) ) {
                    alt13=1;
                }


                switch (alt13) {
            	case 1 :
            	    // InternalTyphonQL.g:1172:3: rule__Obj__Group_4__0
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
    // InternalTyphonQL.g:1180:1: rule__Obj__Group__5 : rule__Obj__Group__5__Impl ;
    public final void rule__Obj__Group__5() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTyphonQL.g:1184:1: ( rule__Obj__Group__5__Impl )
            // InternalTyphonQL.g:1185:2: rule__Obj__Group__5__Impl
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
    // InternalTyphonQL.g:1191:1: rule__Obj__Group__5__Impl : ( '}' ) ;
    public final void rule__Obj__Group__5__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTyphonQL.g:1195:1: ( ( '}' ) )
            // InternalTyphonQL.g:1196:1: ( '}' )
            {
            // InternalTyphonQL.g:1196:1: ( '}' )
            // InternalTyphonQL.g:1197:2: '}'
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
    // InternalTyphonQL.g:1207:1: rule__Obj__Group_0__0 : rule__Obj__Group_0__0__Impl rule__Obj__Group_0__1 ;
    public final void rule__Obj__Group_0__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTyphonQL.g:1211:1: ( rule__Obj__Group_0__0__Impl rule__Obj__Group_0__1 )
            // InternalTyphonQL.g:1212:2: rule__Obj__Group_0__0__Impl rule__Obj__Group_0__1
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
    // InternalTyphonQL.g:1219:1: rule__Obj__Group_0__0__Impl : ( '@' ) ;
    public final void rule__Obj__Group_0__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTyphonQL.g:1223:1: ( ( '@' ) )
            // InternalTyphonQL.g:1224:1: ( '@' )
            {
            // InternalTyphonQL.g:1224:1: ( '@' )
            // InternalTyphonQL.g:1225:2: '@'
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
    // InternalTyphonQL.g:1234:1: rule__Obj__Group_0__1 : rule__Obj__Group_0__1__Impl ;
    public final void rule__Obj__Group_0__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTyphonQL.g:1238:1: ( rule__Obj__Group_0__1__Impl )
            // InternalTyphonQL.g:1239:2: rule__Obj__Group_0__1__Impl
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
    // InternalTyphonQL.g:1245:1: rule__Obj__Group_0__1__Impl : ( ( rule__Obj__LabelAssignment_0_1 ) ) ;
    public final void rule__Obj__Group_0__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTyphonQL.g:1249:1: ( ( ( rule__Obj__LabelAssignment_0_1 ) ) )
            // InternalTyphonQL.g:1250:1: ( ( rule__Obj__LabelAssignment_0_1 ) )
            {
            // InternalTyphonQL.g:1250:1: ( ( rule__Obj__LabelAssignment_0_1 ) )
            // InternalTyphonQL.g:1251:2: ( rule__Obj__LabelAssignment_0_1 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getObjAccess().getLabelAssignment_0_1()); 
            }
            // InternalTyphonQL.g:1252:2: ( rule__Obj__LabelAssignment_0_1 )
            // InternalTyphonQL.g:1252:3: rule__Obj__LabelAssignment_0_1
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
    // InternalTyphonQL.g:1261:1: rule__Obj__Group_4__0 : rule__Obj__Group_4__0__Impl rule__Obj__Group_4__1 ;
    public final void rule__Obj__Group_4__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTyphonQL.g:1265:1: ( rule__Obj__Group_4__0__Impl rule__Obj__Group_4__1 )
            // InternalTyphonQL.g:1266:2: rule__Obj__Group_4__0__Impl rule__Obj__Group_4__1
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
    // InternalTyphonQL.g:1273:1: rule__Obj__Group_4__0__Impl : ( ',' ) ;
    public final void rule__Obj__Group_4__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTyphonQL.g:1277:1: ( ( ',' ) )
            // InternalTyphonQL.g:1278:1: ( ',' )
            {
            // InternalTyphonQL.g:1278:1: ( ',' )
            // InternalTyphonQL.g:1279:2: ','
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
    // InternalTyphonQL.g:1288:1: rule__Obj__Group_4__1 : rule__Obj__Group_4__1__Impl ;
    public final void rule__Obj__Group_4__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTyphonQL.g:1292:1: ( rule__Obj__Group_4__1__Impl )
            // InternalTyphonQL.g:1293:2: rule__Obj__Group_4__1__Impl
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
    // InternalTyphonQL.g:1299:1: rule__Obj__Group_4__1__Impl : ( ( rule__Obj__SetAssignment_4_1 ) ) ;
    public final void rule__Obj__Group_4__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTyphonQL.g:1303:1: ( ( ( rule__Obj__SetAssignment_4_1 ) ) )
            // InternalTyphonQL.g:1304:1: ( ( rule__Obj__SetAssignment_4_1 ) )
            {
            // InternalTyphonQL.g:1304:1: ( ( rule__Obj__SetAssignment_4_1 ) )
            // InternalTyphonQL.g:1305:2: ( rule__Obj__SetAssignment_4_1 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getObjAccess().getSetAssignment_4_1()); 
            }
            // InternalTyphonQL.g:1306:2: ( rule__Obj__SetAssignment_4_1 )
            // InternalTyphonQL.g:1306:3: rule__Obj__SetAssignment_4_1
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
    // InternalTyphonQL.g:1315:1: rule__Queries__QueriesAssignment : ( ( rule__Queries__QueriesAlternatives_0 ) ) ;
    public final void rule__Queries__QueriesAssignment() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTyphonQL.g:1319:1: ( ( ( rule__Queries__QueriesAlternatives_0 ) ) )
            // InternalTyphonQL.g:1320:2: ( ( rule__Queries__QueriesAlternatives_0 ) )
            {
            // InternalTyphonQL.g:1320:2: ( ( rule__Queries__QueriesAlternatives_0 ) )
            // InternalTyphonQL.g:1321:3: ( rule__Queries__QueriesAlternatives_0 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getQueriesAccess().getQueriesAlternatives_0()); 
            }
            // InternalTyphonQL.g:1322:3: ( rule__Queries__QueriesAlternatives_0 )
            // InternalTyphonQL.g:1322:4: rule__Queries__QueriesAlternatives_0
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
    // InternalTyphonQL.g:1330:1: rule__Update__EidAssignment_1 : ( RULE_ID ) ;
    public final void rule__Update__EidAssignment_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTyphonQL.g:1334:1: ( ( RULE_ID ) )
            // InternalTyphonQL.g:1335:2: ( RULE_ID )
            {
            // InternalTyphonQL.g:1335:2: ( RULE_ID )
            // InternalTyphonQL.g:1336:3: RULE_ID
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
    // InternalTyphonQL.g:1345:1: rule__Update__VidAssignment_2 : ( RULE_ID ) ;
    public final void rule__Update__VidAssignment_2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTyphonQL.g:1349:1: ( ( RULE_ID ) )
            // InternalTyphonQL.g:1350:2: ( RULE_ID )
            {
            // InternalTyphonQL.g:1350:2: ( RULE_ID )
            // InternalTyphonQL.g:1351:3: RULE_ID
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
    // InternalTyphonQL.g:1360:1: rule__Update__WhereAssignment_3_1 : ( ruleExpr ) ;
    public final void rule__Update__WhereAssignment_3_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTyphonQL.g:1364:1: ( ( ruleExpr ) )
            // InternalTyphonQL.g:1365:2: ( ruleExpr )
            {
            // InternalTyphonQL.g:1365:2: ( ruleExpr )
            // InternalTyphonQL.g:1366:3: ruleExpr
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
    // InternalTyphonQL.g:1375:1: rule__Update__SetAssignment_6 : ( ruleExpr ) ;
    public final void rule__Update__SetAssignment_6() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTyphonQL.g:1379:1: ( ( ruleExpr ) )
            // InternalTyphonQL.g:1380:2: ( ruleExpr )
            {
            // InternalTyphonQL.g:1380:2: ( ruleExpr )
            // InternalTyphonQL.g:1381:3: ruleExpr
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
    // InternalTyphonQL.g:1390:1: rule__Update__SetAssignment_7_1 : ( ruleExpr ) ;
    public final void rule__Update__SetAssignment_7_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTyphonQL.g:1394:1: ( ( ruleExpr ) )
            // InternalTyphonQL.g:1395:2: ( ruleExpr )
            {
            // InternalTyphonQL.g:1395:2: ( ruleExpr )
            // InternalTyphonQL.g:1396:3: ruleExpr
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
    // InternalTyphonQL.g:1405:1: rule__Delete__EidAssignment_1 : ( RULE_ID ) ;
    public final void rule__Delete__EidAssignment_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTyphonQL.g:1409:1: ( ( RULE_ID ) )
            // InternalTyphonQL.g:1410:2: ( RULE_ID )
            {
            // InternalTyphonQL.g:1410:2: ( RULE_ID )
            // InternalTyphonQL.g:1411:3: RULE_ID
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
    // InternalTyphonQL.g:1420:1: rule__Delete__VidAssignment_2 : ( RULE_ID ) ;
    public final void rule__Delete__VidAssignment_2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTyphonQL.g:1424:1: ( ( RULE_ID ) )
            // InternalTyphonQL.g:1425:2: ( RULE_ID )
            {
            // InternalTyphonQL.g:1425:2: ( RULE_ID )
            // InternalTyphonQL.g:1426:3: RULE_ID
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
    // InternalTyphonQL.g:1435:1: rule__Delete__WhereAssignment_3_1 : ( ruleExpr ) ;
    public final void rule__Delete__WhereAssignment_3_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTyphonQL.g:1439:1: ( ( ruleExpr ) )
            // InternalTyphonQL.g:1440:2: ( ruleExpr )
            {
            // InternalTyphonQL.g:1440:2: ( ruleExpr )
            // InternalTyphonQL.g:1441:3: ruleExpr
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
    // InternalTyphonQL.g:1450:1: rule__Insert__ObjectsAssignment_1 : ( ruleObj ) ;
    public final void rule__Insert__ObjectsAssignment_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTyphonQL.g:1454:1: ( ( ruleObj ) )
            // InternalTyphonQL.g:1455:2: ( ruleObj )
            {
            // InternalTyphonQL.g:1455:2: ( ruleObj )
            // InternalTyphonQL.g:1456:3: ruleObj
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
    // InternalTyphonQL.g:1465:1: rule__Insert__ObjectsAssignment_2_1 : ( ruleObj ) ;
    public final void rule__Insert__ObjectsAssignment_2_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTyphonQL.g:1469:1: ( ( ruleObj ) )
            // InternalTyphonQL.g:1470:2: ( ruleObj )
            {
            // InternalTyphonQL.g:1470:2: ( ruleObj )
            // InternalTyphonQL.g:1471:3: ruleObj
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
    // InternalTyphonQL.g:1480:1: rule__Obj__LabelAssignment_0_1 : ( RULE_ID ) ;
    public final void rule__Obj__LabelAssignment_0_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTyphonQL.g:1484:1: ( ( RULE_ID ) )
            // InternalTyphonQL.g:1485:2: ( RULE_ID )
            {
            // InternalTyphonQL.g:1485:2: ( RULE_ID )
            // InternalTyphonQL.g:1486:3: RULE_ID
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
    // InternalTyphonQL.g:1495:1: rule__Obj__EidAssignment_1 : ( RULE_ID ) ;
    public final void rule__Obj__EidAssignment_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTyphonQL.g:1499:1: ( ( RULE_ID ) )
            // InternalTyphonQL.g:1500:2: ( RULE_ID )
            {
            // InternalTyphonQL.g:1500:2: ( RULE_ID )
            // InternalTyphonQL.g:1501:3: RULE_ID
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
    // InternalTyphonQL.g:1510:1: rule__Obj__SetAssignment_3 : ( ruleExpr ) ;
    public final void rule__Obj__SetAssignment_3() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTyphonQL.g:1514:1: ( ( ruleExpr ) )
            // InternalTyphonQL.g:1515:2: ( ruleExpr )
            {
            // InternalTyphonQL.g:1515:2: ( ruleExpr )
            // InternalTyphonQL.g:1516:3: ruleExpr
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
    // InternalTyphonQL.g:1525:1: rule__Obj__SetAssignment_4_1 : ( ruleExpr ) ;
    public final void rule__Obj__SetAssignment_4_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTyphonQL.g:1529:1: ( ( ruleExpr ) )
            // InternalTyphonQL.g:1530:2: ( ruleExpr )
            {
            // InternalTyphonQL.g:1530:2: ( ruleExpr )
            // InternalTyphonQL.g:1531:3: ruleExpr
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
    // InternalTyphonQL.g:1540:1: rule__Expr__ExprsAssignment : ( ( rule__Expr__ExprsAlternatives_0 ) ) ;
    public final void rule__Expr__ExprsAssignment() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTyphonQL.g:1544:1: ( ( ( rule__Expr__ExprsAlternatives_0 ) ) )
            // InternalTyphonQL.g:1545:2: ( ( rule__Expr__ExprsAlternatives_0 ) )
            {
            // InternalTyphonQL.g:1545:2: ( ( rule__Expr__ExprsAlternatives_0 ) )
            // InternalTyphonQL.g:1546:3: ( rule__Expr__ExprsAlternatives_0 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getExprAccess().getExprsAlternatives_0()); 
            }
            // InternalTyphonQL.g:1547:3: ( rule__Expr__ExprsAlternatives_0 )
            // InternalTyphonQL.g:1547:4: rule__Expr__ExprsAlternatives_0
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
    // InternalTyphonQL.g:1555:1: rule__Stringy__ValsAssignment : ( ( rule__Stringy__ValsAlternatives_0 ) ) ;
    public final void rule__Stringy__ValsAssignment() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTyphonQL.g:1559:1: ( ( ( rule__Stringy__ValsAlternatives_0 ) ) )
            // InternalTyphonQL.g:1560:2: ( ( rule__Stringy__ValsAlternatives_0 ) )
            {
            // InternalTyphonQL.g:1560:2: ( ( rule__Stringy__ValsAlternatives_0 ) )
            // InternalTyphonQL.g:1561:3: ( rule__Stringy__ValsAlternatives_0 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getStringyAccess().getValsAlternatives_0()); 
            }
            // InternalTyphonQL.g:1562:3: ( rule__Stringy__ValsAlternatives_0 )
            // InternalTyphonQL.g:1562:4: rule__Stringy__ValsAlternatives_0
            {
            pushFollow(FOLLOW_2);
            rule__Stringy__ValsAlternatives_0();

            state._fsp--;
            if (state.failed) return ;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getStringyAccess().getValsAlternatives_0()); 
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

    // Delegated rules


 

    public static final BitSet FOLLOW_1 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_2 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_3 = new BitSet(new long[]{0x0000000000181002L});
    public static final BitSet FOLLOW_4 = new BitSet(new long[]{0x00000000002000F2L});
    public static final BitSet FOLLOW_5 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_6 = new BitSet(new long[]{0x0000000000022000L});
    public static final BitSet FOLLOW_7 = new BitSet(new long[]{0x0000000000004000L});
    public static final BitSet FOLLOW_8 = new BitSet(new long[]{0x00000000002480F0L});
    public static final BitSet FOLLOW_9 = new BitSet(new long[]{0x0000000000040002L});
    public static final BitSet FOLLOW_10 = new BitSet(new long[]{0x0000000000010000L});
    public static final BitSet FOLLOW_11 = new BitSet(new long[]{0x00000000002000F0L});
    public static final BitSet FOLLOW_12 = new BitSet(new long[]{0x0000000000030000L});
    public static final BitSet FOLLOW_13 = new BitSet(new long[]{0x0000000000050000L});

}