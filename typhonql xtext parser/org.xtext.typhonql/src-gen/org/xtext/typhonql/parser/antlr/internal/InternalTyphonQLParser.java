package org.xtext.typhonql.parser.antlr.internal;

import org.eclipse.xtext.*;
import org.eclipse.xtext.parser.*;
import org.eclipse.xtext.parser.impl.*;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.parser.antlr.AbstractInternalAntlrParser;
import org.eclipse.xtext.parser.antlr.XtextTokenStream;
import org.eclipse.xtext.parser.antlr.XtextTokenStream.HiddenTokens;
import org.eclipse.xtext.parser.antlr.AntlrDatatypeRuleToken;
import org.xtext.typhonql.services.TyphonQLGrammarAccess;



import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
@SuppressWarnings("all")
public class InternalTyphonQLParser extends AbstractInternalAntlrParser {
    public static final String[] tokenNames = new String[] {
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "RULE_ID", "RULE_VALUE_TERMINAL", "RULE_STRING", "RULE_ANY_OTHER", "RULE_INT", "RULE_ML_COMMENT", "RULE_SL_COMMENT", "RULE_WS", "'update'", "'where'", "'set'", "'{'", "','", "'}'", "';'", "'delete'", "'insert'", "'@'"
    };
    public static final int RULE_VALUE_TERMINAL=5;
    public static final int RULE_STRING=6;
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
    public static final int RULE_ID=4;
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



    /*
      This grammar contains a lot of empty actions to work around a bug in ANTLR.
      Otherwise the ANTLR tool will create synpreds that cannot be compiled in some rare cases.
    */

     	private TyphonQLGrammarAccess grammarAccess;

        public InternalTyphonQLParser(TokenStream input, TyphonQLGrammarAccess grammarAccess) {
            this(input);
            this.grammarAccess = grammarAccess;
            registerRules(grammarAccess.getGrammar());
        }

        @Override
        protected String getFirstRuleName() {
        	return "Queries";
       	}

       	@Override
       	protected TyphonQLGrammarAccess getGrammarAccess() {
       		return grammarAccess;
       	}




    // $ANTLR start "entryRuleQueries"
    // InternalTyphonQL.g:70:1: entryRuleQueries returns [EObject current=null] : iv_ruleQueries= ruleQueries EOF ;
    public final EObject entryRuleQueries() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleQueries = null;


        try {
            // InternalTyphonQL.g:70:48: (iv_ruleQueries= ruleQueries EOF )
            // InternalTyphonQL.g:71:2: iv_ruleQueries= ruleQueries EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getQueriesRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleQueries=ruleQueries();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleQueries; 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return current;

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleQueries"


    // $ANTLR start "ruleQueries"
    // InternalTyphonQL.g:77:1: ruleQueries returns [EObject current=null] : ( ( (lv_queries_0_1= ruleInsert | lv_queries_0_2= ruleDelete | lv_queries_0_3= ruleUpdate ) ) )* ;
    public final EObject ruleQueries() throws RecognitionException {
        EObject current = null;

        EObject lv_queries_0_1 = null;

        EObject lv_queries_0_2 = null;

        EObject lv_queries_0_3 = null;



        	enterRule();

        try {
            // InternalTyphonQL.g:83:2: ( ( ( (lv_queries_0_1= ruleInsert | lv_queries_0_2= ruleDelete | lv_queries_0_3= ruleUpdate ) ) )* )
            // InternalTyphonQL.g:84:2: ( ( (lv_queries_0_1= ruleInsert | lv_queries_0_2= ruleDelete | lv_queries_0_3= ruleUpdate ) ) )*
            {
            // InternalTyphonQL.g:84:2: ( ( (lv_queries_0_1= ruleInsert | lv_queries_0_2= ruleDelete | lv_queries_0_3= ruleUpdate ) ) )*
            loop2:
            do {
                int alt2=2;
                int LA2_0 = input.LA(1);

                if ( (LA2_0==12||(LA2_0>=19 && LA2_0<=20)) ) {
                    alt2=1;
                }


                switch (alt2) {
            	case 1 :
            	    // InternalTyphonQL.g:85:3: ( (lv_queries_0_1= ruleInsert | lv_queries_0_2= ruleDelete | lv_queries_0_3= ruleUpdate ) )
            	    {
            	    // InternalTyphonQL.g:85:3: ( (lv_queries_0_1= ruleInsert | lv_queries_0_2= ruleDelete | lv_queries_0_3= ruleUpdate ) )
            	    // InternalTyphonQL.g:86:4: (lv_queries_0_1= ruleInsert | lv_queries_0_2= ruleDelete | lv_queries_0_3= ruleUpdate )
            	    {
            	    // InternalTyphonQL.g:86:4: (lv_queries_0_1= ruleInsert | lv_queries_0_2= ruleDelete | lv_queries_0_3= ruleUpdate )
            	    int alt1=3;
            	    switch ( input.LA(1) ) {
            	    case 20:
            	        {
            	        alt1=1;
            	        }
            	        break;
            	    case 19:
            	        {
            	        alt1=2;
            	        }
            	        break;
            	    case 12:
            	        {
            	        alt1=3;
            	        }
            	        break;
            	    default:
            	        if (state.backtracking>0) {state.failed=true; return current;}
            	        NoViableAltException nvae =
            	            new NoViableAltException("", 1, 0, input);

            	        throw nvae;
            	    }

            	    switch (alt1) {
            	        case 1 :
            	            // InternalTyphonQL.g:87:5: lv_queries_0_1= ruleInsert
            	            {
            	            if ( state.backtracking==0 ) {

            	              					newCompositeNode(grammarAccess.getQueriesAccess().getQueriesInsertParserRuleCall_0_0());
            	              				
            	            }
            	            pushFollow(FOLLOW_3);
            	            lv_queries_0_1=ruleInsert();

            	            state._fsp--;
            	            if (state.failed) return current;
            	            if ( state.backtracking==0 ) {

            	              					if (current==null) {
            	              						current = createModelElementForParent(grammarAccess.getQueriesRule());
            	              					}
            	              					add(
            	              						current,
            	              						"queries",
            	              						lv_queries_0_1,
            	              						"org.xtext.typhonql.TyphonQL.Insert");
            	              					afterParserOrEnumRuleCall();
            	              				
            	            }

            	            }
            	            break;
            	        case 2 :
            	            // InternalTyphonQL.g:103:5: lv_queries_0_2= ruleDelete
            	            {
            	            if ( state.backtracking==0 ) {

            	              					newCompositeNode(grammarAccess.getQueriesAccess().getQueriesDeleteParserRuleCall_0_1());
            	              				
            	            }
            	            pushFollow(FOLLOW_3);
            	            lv_queries_0_2=ruleDelete();

            	            state._fsp--;
            	            if (state.failed) return current;
            	            if ( state.backtracking==0 ) {

            	              					if (current==null) {
            	              						current = createModelElementForParent(grammarAccess.getQueriesRule());
            	              					}
            	              					add(
            	              						current,
            	              						"queries",
            	              						lv_queries_0_2,
            	              						"org.xtext.typhonql.TyphonQL.Delete");
            	              					afterParserOrEnumRuleCall();
            	              				
            	            }

            	            }
            	            break;
            	        case 3 :
            	            // InternalTyphonQL.g:119:5: lv_queries_0_3= ruleUpdate
            	            {
            	            if ( state.backtracking==0 ) {

            	              					newCompositeNode(grammarAccess.getQueriesAccess().getQueriesUpdateParserRuleCall_0_2());
            	              				
            	            }
            	            pushFollow(FOLLOW_3);
            	            lv_queries_0_3=ruleUpdate();

            	            state._fsp--;
            	            if (state.failed) return current;
            	            if ( state.backtracking==0 ) {

            	              					if (current==null) {
            	              						current = createModelElementForParent(grammarAccess.getQueriesRule());
            	              					}
            	              					add(
            	              						current,
            	              						"queries",
            	              						lv_queries_0_3,
            	              						"org.xtext.typhonql.TyphonQL.Update");
            	              					afterParserOrEnumRuleCall();
            	              				
            	            }

            	            }
            	            break;

            	    }


            	    }


            	    }
            	    break;

            	default :
            	    break loop2;
                }
            } while (true);


            }

            if ( state.backtracking==0 ) {

              	leaveRule();

            }
        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleQueries"


    // $ANTLR start "entryRuleUpdate"
    // InternalTyphonQL.g:140:1: entryRuleUpdate returns [EObject current=null] : iv_ruleUpdate= ruleUpdate EOF ;
    public final EObject entryRuleUpdate() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleUpdate = null;


        try {
            // InternalTyphonQL.g:140:47: (iv_ruleUpdate= ruleUpdate EOF )
            // InternalTyphonQL.g:141:2: iv_ruleUpdate= ruleUpdate EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getUpdateRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleUpdate=ruleUpdate();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleUpdate; 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return current;

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleUpdate"


    // $ANTLR start "ruleUpdate"
    // InternalTyphonQL.g:147:1: ruleUpdate returns [EObject current=null] : (otherlv_0= 'update' ( (lv_eid_1_0= RULE_ID ) ) ( (lv_vid_2_0= RULE_ID ) ) (otherlv_3= 'where' ( (lv_where_4_0= ruleExpr ) ) )? otherlv_5= 'set' otherlv_6= '{' ( (lv_set_7_0= ruleExpr ) )? (otherlv_8= ',' ( (lv_set_9_0= ruleExpr ) ) )* otherlv_10= '}' otherlv_11= ';' ) ;
    public final EObject ruleUpdate() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token lv_eid_1_0=null;
        Token lv_vid_2_0=null;
        Token otherlv_3=null;
        Token otherlv_5=null;
        Token otherlv_6=null;
        Token otherlv_8=null;
        Token otherlv_10=null;
        Token otherlv_11=null;
        EObject lv_where_4_0 = null;

        EObject lv_set_7_0 = null;

        EObject lv_set_9_0 = null;



        	enterRule();

        try {
            // InternalTyphonQL.g:153:2: ( (otherlv_0= 'update' ( (lv_eid_1_0= RULE_ID ) ) ( (lv_vid_2_0= RULE_ID ) ) (otherlv_3= 'where' ( (lv_where_4_0= ruleExpr ) ) )? otherlv_5= 'set' otherlv_6= '{' ( (lv_set_7_0= ruleExpr ) )? (otherlv_8= ',' ( (lv_set_9_0= ruleExpr ) ) )* otherlv_10= '}' otherlv_11= ';' ) )
            // InternalTyphonQL.g:154:2: (otherlv_0= 'update' ( (lv_eid_1_0= RULE_ID ) ) ( (lv_vid_2_0= RULE_ID ) ) (otherlv_3= 'where' ( (lv_where_4_0= ruleExpr ) ) )? otherlv_5= 'set' otherlv_6= '{' ( (lv_set_7_0= ruleExpr ) )? (otherlv_8= ',' ( (lv_set_9_0= ruleExpr ) ) )* otherlv_10= '}' otherlv_11= ';' )
            {
            // InternalTyphonQL.g:154:2: (otherlv_0= 'update' ( (lv_eid_1_0= RULE_ID ) ) ( (lv_vid_2_0= RULE_ID ) ) (otherlv_3= 'where' ( (lv_where_4_0= ruleExpr ) ) )? otherlv_5= 'set' otherlv_6= '{' ( (lv_set_7_0= ruleExpr ) )? (otherlv_8= ',' ( (lv_set_9_0= ruleExpr ) ) )* otherlv_10= '}' otherlv_11= ';' )
            // InternalTyphonQL.g:155:3: otherlv_0= 'update' ( (lv_eid_1_0= RULE_ID ) ) ( (lv_vid_2_0= RULE_ID ) ) (otherlv_3= 'where' ( (lv_where_4_0= ruleExpr ) ) )? otherlv_5= 'set' otherlv_6= '{' ( (lv_set_7_0= ruleExpr ) )? (otherlv_8= ',' ( (lv_set_9_0= ruleExpr ) ) )* otherlv_10= '}' otherlv_11= ';'
            {
            otherlv_0=(Token)match(input,12,FOLLOW_4); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(otherlv_0, grammarAccess.getUpdateAccess().getUpdateKeyword_0());
              		
            }
            // InternalTyphonQL.g:159:3: ( (lv_eid_1_0= RULE_ID ) )
            // InternalTyphonQL.g:160:4: (lv_eid_1_0= RULE_ID )
            {
            // InternalTyphonQL.g:160:4: (lv_eid_1_0= RULE_ID )
            // InternalTyphonQL.g:161:5: lv_eid_1_0= RULE_ID
            {
            lv_eid_1_0=(Token)match(input,RULE_ID,FOLLOW_4); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              					newLeafNode(lv_eid_1_0, grammarAccess.getUpdateAccess().getEidIDTerminalRuleCall_1_0());
              				
            }
            if ( state.backtracking==0 ) {

              					if (current==null) {
              						current = createModelElement(grammarAccess.getUpdateRule());
              					}
              					setWithLastConsumed(
              						current,
              						"eid",
              						lv_eid_1_0,
              						"org.eclipse.xtext.common.Terminals.ID");
              				
            }

            }


            }

            // InternalTyphonQL.g:177:3: ( (lv_vid_2_0= RULE_ID ) )
            // InternalTyphonQL.g:178:4: (lv_vid_2_0= RULE_ID )
            {
            // InternalTyphonQL.g:178:4: (lv_vid_2_0= RULE_ID )
            // InternalTyphonQL.g:179:5: lv_vid_2_0= RULE_ID
            {
            lv_vid_2_0=(Token)match(input,RULE_ID,FOLLOW_5); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              					newLeafNode(lv_vid_2_0, grammarAccess.getUpdateAccess().getVidIDTerminalRuleCall_2_0());
              				
            }
            if ( state.backtracking==0 ) {

              					if (current==null) {
              						current = createModelElement(grammarAccess.getUpdateRule());
              					}
              					setWithLastConsumed(
              						current,
              						"vid",
              						lv_vid_2_0,
              						"org.eclipse.xtext.common.Terminals.ID");
              				
            }

            }


            }

            // InternalTyphonQL.g:195:3: (otherlv_3= 'where' ( (lv_where_4_0= ruleExpr ) ) )?
            int alt3=2;
            int LA3_0 = input.LA(1);

            if ( (LA3_0==13) ) {
                alt3=1;
            }
            switch (alt3) {
                case 1 :
                    // InternalTyphonQL.g:196:4: otherlv_3= 'where' ( (lv_where_4_0= ruleExpr ) )
                    {
                    otherlv_3=(Token)match(input,13,FOLLOW_6); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      				newLeafNode(otherlv_3, grammarAccess.getUpdateAccess().getWhereKeyword_3_0());
                      			
                    }
                    // InternalTyphonQL.g:200:4: ( (lv_where_4_0= ruleExpr ) )
                    // InternalTyphonQL.g:201:5: (lv_where_4_0= ruleExpr )
                    {
                    // InternalTyphonQL.g:201:5: (lv_where_4_0= ruleExpr )
                    // InternalTyphonQL.g:202:6: lv_where_4_0= ruleExpr
                    {
                    if ( state.backtracking==0 ) {

                      						newCompositeNode(grammarAccess.getUpdateAccess().getWhereExprParserRuleCall_3_1_0());
                      					
                    }
                    pushFollow(FOLLOW_7);
                    lv_where_4_0=ruleExpr();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      						if (current==null) {
                      							current = createModelElementForParent(grammarAccess.getUpdateRule());
                      						}
                      						add(
                      							current,
                      							"where",
                      							lv_where_4_0,
                      							"org.xtext.typhonql.TyphonQL.Expr");
                      						afterParserOrEnumRuleCall();
                      					
                    }

                    }


                    }


                    }
                    break;

            }

            otherlv_5=(Token)match(input,14,FOLLOW_8); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(otherlv_5, grammarAccess.getUpdateAccess().getSetKeyword_4());
              		
            }
            otherlv_6=(Token)match(input,15,FOLLOW_9); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(otherlv_6, grammarAccess.getUpdateAccess().getLeftCurlyBracketKeyword_5());
              		
            }
            // InternalTyphonQL.g:228:3: ( (lv_set_7_0= ruleExpr ) )?
            int alt4=2;
            int LA4_0 = input.LA(1);

            if ( ((LA4_0>=RULE_ID && LA4_0<=RULE_ANY_OTHER)||LA4_0==21) ) {
                alt4=1;
            }
            switch (alt4) {
                case 1 :
                    // InternalTyphonQL.g:229:4: (lv_set_7_0= ruleExpr )
                    {
                    // InternalTyphonQL.g:229:4: (lv_set_7_0= ruleExpr )
                    // InternalTyphonQL.g:230:5: lv_set_7_0= ruleExpr
                    {
                    if ( state.backtracking==0 ) {

                      					newCompositeNode(grammarAccess.getUpdateAccess().getSetExprParserRuleCall_6_0());
                      				
                    }
                    pushFollow(FOLLOW_10);
                    lv_set_7_0=ruleExpr();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      					if (current==null) {
                      						current = createModelElementForParent(grammarAccess.getUpdateRule());
                      					}
                      					add(
                      						current,
                      						"set",
                      						lv_set_7_0,
                      						"org.xtext.typhonql.TyphonQL.Expr");
                      					afterParserOrEnumRuleCall();
                      				
                    }

                    }


                    }
                    break;

            }

            // InternalTyphonQL.g:247:3: (otherlv_8= ',' ( (lv_set_9_0= ruleExpr ) ) )*
            loop5:
            do {
                int alt5=2;
                int LA5_0 = input.LA(1);

                if ( (LA5_0==16) ) {
                    alt5=1;
                }


                switch (alt5) {
            	case 1 :
            	    // InternalTyphonQL.g:248:4: otherlv_8= ',' ( (lv_set_9_0= ruleExpr ) )
            	    {
            	    otherlv_8=(Token)match(input,16,FOLLOW_6); if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	      				newLeafNode(otherlv_8, grammarAccess.getUpdateAccess().getCommaKeyword_7_0());
            	      			
            	    }
            	    // InternalTyphonQL.g:252:4: ( (lv_set_9_0= ruleExpr ) )
            	    // InternalTyphonQL.g:253:5: (lv_set_9_0= ruleExpr )
            	    {
            	    // InternalTyphonQL.g:253:5: (lv_set_9_0= ruleExpr )
            	    // InternalTyphonQL.g:254:6: lv_set_9_0= ruleExpr
            	    {
            	    if ( state.backtracking==0 ) {

            	      						newCompositeNode(grammarAccess.getUpdateAccess().getSetExprParserRuleCall_7_1_0());
            	      					
            	    }
            	    pushFollow(FOLLOW_10);
            	    lv_set_9_0=ruleExpr();

            	    state._fsp--;
            	    if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	      						if (current==null) {
            	      							current = createModelElementForParent(grammarAccess.getUpdateRule());
            	      						}
            	      						add(
            	      							current,
            	      							"set",
            	      							lv_set_9_0,
            	      							"org.xtext.typhonql.TyphonQL.Expr");
            	      						afterParserOrEnumRuleCall();
            	      					
            	    }

            	    }


            	    }


            	    }
            	    break;

            	default :
            	    break loop5;
                }
            } while (true);

            otherlv_10=(Token)match(input,17,FOLLOW_11); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(otherlv_10, grammarAccess.getUpdateAccess().getRightCurlyBracketKeyword_8());
              		
            }
            otherlv_11=(Token)match(input,18,FOLLOW_2); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(otherlv_11, grammarAccess.getUpdateAccess().getSemicolonKeyword_9());
              		
            }

            }


            }

            if ( state.backtracking==0 ) {

              	leaveRule();

            }
        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleUpdate"


    // $ANTLR start "entryRuleDelete"
    // InternalTyphonQL.g:284:1: entryRuleDelete returns [EObject current=null] : iv_ruleDelete= ruleDelete EOF ;
    public final EObject entryRuleDelete() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleDelete = null;


        try {
            // InternalTyphonQL.g:284:47: (iv_ruleDelete= ruleDelete EOF )
            // InternalTyphonQL.g:285:2: iv_ruleDelete= ruleDelete EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getDeleteRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleDelete=ruleDelete();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleDelete; 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return current;

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleDelete"


    // $ANTLR start "ruleDelete"
    // InternalTyphonQL.g:291:1: ruleDelete returns [EObject current=null] : (otherlv_0= 'delete' ( (lv_eid_1_0= RULE_ID ) ) ( (lv_vid_2_0= RULE_ID ) ) (otherlv_3= 'where' ( (lv_where_4_0= ruleExpr ) ) )? otherlv_5= ';' ) ;
    public final EObject ruleDelete() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token lv_eid_1_0=null;
        Token lv_vid_2_0=null;
        Token otherlv_3=null;
        Token otherlv_5=null;
        EObject lv_where_4_0 = null;



        	enterRule();

        try {
            // InternalTyphonQL.g:297:2: ( (otherlv_0= 'delete' ( (lv_eid_1_0= RULE_ID ) ) ( (lv_vid_2_0= RULE_ID ) ) (otherlv_3= 'where' ( (lv_where_4_0= ruleExpr ) ) )? otherlv_5= ';' ) )
            // InternalTyphonQL.g:298:2: (otherlv_0= 'delete' ( (lv_eid_1_0= RULE_ID ) ) ( (lv_vid_2_0= RULE_ID ) ) (otherlv_3= 'where' ( (lv_where_4_0= ruleExpr ) ) )? otherlv_5= ';' )
            {
            // InternalTyphonQL.g:298:2: (otherlv_0= 'delete' ( (lv_eid_1_0= RULE_ID ) ) ( (lv_vid_2_0= RULE_ID ) ) (otherlv_3= 'where' ( (lv_where_4_0= ruleExpr ) ) )? otherlv_5= ';' )
            // InternalTyphonQL.g:299:3: otherlv_0= 'delete' ( (lv_eid_1_0= RULE_ID ) ) ( (lv_vid_2_0= RULE_ID ) ) (otherlv_3= 'where' ( (lv_where_4_0= ruleExpr ) ) )? otherlv_5= ';'
            {
            otherlv_0=(Token)match(input,19,FOLLOW_4); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(otherlv_0, grammarAccess.getDeleteAccess().getDeleteKeyword_0());
              		
            }
            // InternalTyphonQL.g:303:3: ( (lv_eid_1_0= RULE_ID ) )
            // InternalTyphonQL.g:304:4: (lv_eid_1_0= RULE_ID )
            {
            // InternalTyphonQL.g:304:4: (lv_eid_1_0= RULE_ID )
            // InternalTyphonQL.g:305:5: lv_eid_1_0= RULE_ID
            {
            lv_eid_1_0=(Token)match(input,RULE_ID,FOLLOW_4); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              					newLeafNode(lv_eid_1_0, grammarAccess.getDeleteAccess().getEidIDTerminalRuleCall_1_0());
              				
            }
            if ( state.backtracking==0 ) {

              					if (current==null) {
              						current = createModelElement(grammarAccess.getDeleteRule());
              					}
              					setWithLastConsumed(
              						current,
              						"eid",
              						lv_eid_1_0,
              						"org.eclipse.xtext.common.Terminals.ID");
              				
            }

            }


            }

            // InternalTyphonQL.g:321:3: ( (lv_vid_2_0= RULE_ID ) )
            // InternalTyphonQL.g:322:4: (lv_vid_2_0= RULE_ID )
            {
            // InternalTyphonQL.g:322:4: (lv_vid_2_0= RULE_ID )
            // InternalTyphonQL.g:323:5: lv_vid_2_0= RULE_ID
            {
            lv_vid_2_0=(Token)match(input,RULE_ID,FOLLOW_12); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              					newLeafNode(lv_vid_2_0, grammarAccess.getDeleteAccess().getVidIDTerminalRuleCall_2_0());
              				
            }
            if ( state.backtracking==0 ) {

              					if (current==null) {
              						current = createModelElement(grammarAccess.getDeleteRule());
              					}
              					setWithLastConsumed(
              						current,
              						"vid",
              						lv_vid_2_0,
              						"org.eclipse.xtext.common.Terminals.ID");
              				
            }

            }


            }

            // InternalTyphonQL.g:339:3: (otherlv_3= 'where' ( (lv_where_4_0= ruleExpr ) ) )?
            int alt6=2;
            int LA6_0 = input.LA(1);

            if ( (LA6_0==13) ) {
                alt6=1;
            }
            switch (alt6) {
                case 1 :
                    // InternalTyphonQL.g:340:4: otherlv_3= 'where' ( (lv_where_4_0= ruleExpr ) )
                    {
                    otherlv_3=(Token)match(input,13,FOLLOW_6); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      				newLeafNode(otherlv_3, grammarAccess.getDeleteAccess().getWhereKeyword_3_0());
                      			
                    }
                    // InternalTyphonQL.g:344:4: ( (lv_where_4_0= ruleExpr ) )
                    // InternalTyphonQL.g:345:5: (lv_where_4_0= ruleExpr )
                    {
                    // InternalTyphonQL.g:345:5: (lv_where_4_0= ruleExpr )
                    // InternalTyphonQL.g:346:6: lv_where_4_0= ruleExpr
                    {
                    if ( state.backtracking==0 ) {

                      						newCompositeNode(grammarAccess.getDeleteAccess().getWhereExprParserRuleCall_3_1_0());
                      					
                    }
                    pushFollow(FOLLOW_11);
                    lv_where_4_0=ruleExpr();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      						if (current==null) {
                      							current = createModelElementForParent(grammarAccess.getDeleteRule());
                      						}
                      						add(
                      							current,
                      							"where",
                      							lv_where_4_0,
                      							"org.xtext.typhonql.TyphonQL.Expr");
                      						afterParserOrEnumRuleCall();
                      					
                    }

                    }


                    }


                    }
                    break;

            }

            otherlv_5=(Token)match(input,18,FOLLOW_2); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(otherlv_5, grammarAccess.getDeleteAccess().getSemicolonKeyword_4());
              		
            }

            }


            }

            if ( state.backtracking==0 ) {

              	leaveRule();

            }
        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleDelete"


    // $ANTLR start "entryRuleInsert"
    // InternalTyphonQL.g:372:1: entryRuleInsert returns [EObject current=null] : iv_ruleInsert= ruleInsert EOF ;
    public final EObject entryRuleInsert() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleInsert = null;


        try {
            // InternalTyphonQL.g:372:47: (iv_ruleInsert= ruleInsert EOF )
            // InternalTyphonQL.g:373:2: iv_ruleInsert= ruleInsert EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getInsertRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleInsert=ruleInsert();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleInsert; 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return current;

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleInsert"


    // $ANTLR start "ruleInsert"
    // InternalTyphonQL.g:379:1: ruleInsert returns [EObject current=null] : (otherlv_0= 'insert' ( (lv_objects_1_0= ruleObj ) ) (otherlv_2= ',' ( (lv_objects_3_0= ruleObj ) ) )* otherlv_4= ';' ) ;
    public final EObject ruleInsert() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_2=null;
        Token otherlv_4=null;
        EObject lv_objects_1_0 = null;

        EObject lv_objects_3_0 = null;



        	enterRule();

        try {
            // InternalTyphonQL.g:385:2: ( (otherlv_0= 'insert' ( (lv_objects_1_0= ruleObj ) ) (otherlv_2= ',' ( (lv_objects_3_0= ruleObj ) ) )* otherlv_4= ';' ) )
            // InternalTyphonQL.g:386:2: (otherlv_0= 'insert' ( (lv_objects_1_0= ruleObj ) ) (otherlv_2= ',' ( (lv_objects_3_0= ruleObj ) ) )* otherlv_4= ';' )
            {
            // InternalTyphonQL.g:386:2: (otherlv_0= 'insert' ( (lv_objects_1_0= ruleObj ) ) (otherlv_2= ',' ( (lv_objects_3_0= ruleObj ) ) )* otherlv_4= ';' )
            // InternalTyphonQL.g:387:3: otherlv_0= 'insert' ( (lv_objects_1_0= ruleObj ) ) (otherlv_2= ',' ( (lv_objects_3_0= ruleObj ) ) )* otherlv_4= ';'
            {
            otherlv_0=(Token)match(input,20,FOLLOW_6); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(otherlv_0, grammarAccess.getInsertAccess().getInsertKeyword_0());
              		
            }
            // InternalTyphonQL.g:391:3: ( (lv_objects_1_0= ruleObj ) )
            // InternalTyphonQL.g:392:4: (lv_objects_1_0= ruleObj )
            {
            // InternalTyphonQL.g:392:4: (lv_objects_1_0= ruleObj )
            // InternalTyphonQL.g:393:5: lv_objects_1_0= ruleObj
            {
            if ( state.backtracking==0 ) {

              					newCompositeNode(grammarAccess.getInsertAccess().getObjectsObjParserRuleCall_1_0());
              				
            }
            pushFollow(FOLLOW_13);
            lv_objects_1_0=ruleObj();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              					if (current==null) {
              						current = createModelElementForParent(grammarAccess.getInsertRule());
              					}
              					add(
              						current,
              						"objects",
              						lv_objects_1_0,
              						"org.xtext.typhonql.TyphonQL.Obj");
              					afterParserOrEnumRuleCall();
              				
            }

            }


            }

            // InternalTyphonQL.g:410:3: (otherlv_2= ',' ( (lv_objects_3_0= ruleObj ) ) )*
            loop7:
            do {
                int alt7=2;
                int LA7_0 = input.LA(1);

                if ( (LA7_0==16) ) {
                    alt7=1;
                }


                switch (alt7) {
            	case 1 :
            	    // InternalTyphonQL.g:411:4: otherlv_2= ',' ( (lv_objects_3_0= ruleObj ) )
            	    {
            	    otherlv_2=(Token)match(input,16,FOLLOW_6); if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	      				newLeafNode(otherlv_2, grammarAccess.getInsertAccess().getCommaKeyword_2_0());
            	      			
            	    }
            	    // InternalTyphonQL.g:415:4: ( (lv_objects_3_0= ruleObj ) )
            	    // InternalTyphonQL.g:416:5: (lv_objects_3_0= ruleObj )
            	    {
            	    // InternalTyphonQL.g:416:5: (lv_objects_3_0= ruleObj )
            	    // InternalTyphonQL.g:417:6: lv_objects_3_0= ruleObj
            	    {
            	    if ( state.backtracking==0 ) {

            	      						newCompositeNode(grammarAccess.getInsertAccess().getObjectsObjParserRuleCall_2_1_0());
            	      					
            	    }
            	    pushFollow(FOLLOW_13);
            	    lv_objects_3_0=ruleObj();

            	    state._fsp--;
            	    if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	      						if (current==null) {
            	      							current = createModelElementForParent(grammarAccess.getInsertRule());
            	      						}
            	      						add(
            	      							current,
            	      							"objects",
            	      							lv_objects_3_0,
            	      							"org.xtext.typhonql.TyphonQL.Obj");
            	      						afterParserOrEnumRuleCall();
            	      					
            	    }

            	    }


            	    }


            	    }
            	    break;

            	default :
            	    break loop7;
                }
            } while (true);

            otherlv_4=(Token)match(input,18,FOLLOW_2); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(otherlv_4, grammarAccess.getInsertAccess().getSemicolonKeyword_3());
              		
            }

            }


            }

            if ( state.backtracking==0 ) {

              	leaveRule();

            }
        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleInsert"


    // $ANTLR start "entryRuleObj"
    // InternalTyphonQL.g:443:1: entryRuleObj returns [EObject current=null] : iv_ruleObj= ruleObj EOF ;
    public final EObject entryRuleObj() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleObj = null;


        try {
            // InternalTyphonQL.g:443:44: (iv_ruleObj= ruleObj EOF )
            // InternalTyphonQL.g:444:2: iv_ruleObj= ruleObj EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getObjRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleObj=ruleObj();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleObj; 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return current;

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleObj"


    // $ANTLR start "ruleObj"
    // InternalTyphonQL.g:450:1: ruleObj returns [EObject current=null] : ( (otherlv_0= '@' ( (lv_label_1_0= RULE_ID ) ) )? ( (lv_eid_2_0= RULE_ID ) ) otherlv_3= '{' ( (lv_set_4_0= ruleExpr ) )? (otherlv_5= ',' ( (lv_set_6_0= ruleExpr ) ) )* otherlv_7= '}' ) ;
    public final EObject ruleObj() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token lv_label_1_0=null;
        Token lv_eid_2_0=null;
        Token otherlv_3=null;
        Token otherlv_5=null;
        Token otherlv_7=null;
        EObject lv_set_4_0 = null;

        EObject lv_set_6_0 = null;



        	enterRule();

        try {
            // InternalTyphonQL.g:456:2: ( ( (otherlv_0= '@' ( (lv_label_1_0= RULE_ID ) ) )? ( (lv_eid_2_0= RULE_ID ) ) otherlv_3= '{' ( (lv_set_4_0= ruleExpr ) )? (otherlv_5= ',' ( (lv_set_6_0= ruleExpr ) ) )* otherlv_7= '}' ) )
            // InternalTyphonQL.g:457:2: ( (otherlv_0= '@' ( (lv_label_1_0= RULE_ID ) ) )? ( (lv_eid_2_0= RULE_ID ) ) otherlv_3= '{' ( (lv_set_4_0= ruleExpr ) )? (otherlv_5= ',' ( (lv_set_6_0= ruleExpr ) ) )* otherlv_7= '}' )
            {
            // InternalTyphonQL.g:457:2: ( (otherlv_0= '@' ( (lv_label_1_0= RULE_ID ) ) )? ( (lv_eid_2_0= RULE_ID ) ) otherlv_3= '{' ( (lv_set_4_0= ruleExpr ) )? (otherlv_5= ',' ( (lv_set_6_0= ruleExpr ) ) )* otherlv_7= '}' )
            // InternalTyphonQL.g:458:3: (otherlv_0= '@' ( (lv_label_1_0= RULE_ID ) ) )? ( (lv_eid_2_0= RULE_ID ) ) otherlv_3= '{' ( (lv_set_4_0= ruleExpr ) )? (otherlv_5= ',' ( (lv_set_6_0= ruleExpr ) ) )* otherlv_7= '}'
            {
            // InternalTyphonQL.g:458:3: (otherlv_0= '@' ( (lv_label_1_0= RULE_ID ) ) )?
            int alt8=2;
            int LA8_0 = input.LA(1);

            if ( (LA8_0==21) ) {
                alt8=1;
            }
            switch (alt8) {
                case 1 :
                    // InternalTyphonQL.g:459:4: otherlv_0= '@' ( (lv_label_1_0= RULE_ID ) )
                    {
                    otherlv_0=(Token)match(input,21,FOLLOW_4); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      				newLeafNode(otherlv_0, grammarAccess.getObjAccess().getCommercialAtKeyword_0_0());
                      			
                    }
                    // InternalTyphonQL.g:463:4: ( (lv_label_1_0= RULE_ID ) )
                    // InternalTyphonQL.g:464:5: (lv_label_1_0= RULE_ID )
                    {
                    // InternalTyphonQL.g:464:5: (lv_label_1_0= RULE_ID )
                    // InternalTyphonQL.g:465:6: lv_label_1_0= RULE_ID
                    {
                    lv_label_1_0=(Token)match(input,RULE_ID,FOLLOW_4); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      						newLeafNode(lv_label_1_0, grammarAccess.getObjAccess().getLabelIDTerminalRuleCall_0_1_0());
                      					
                    }
                    if ( state.backtracking==0 ) {

                      						if (current==null) {
                      							current = createModelElement(grammarAccess.getObjRule());
                      						}
                      						setWithLastConsumed(
                      							current,
                      							"label",
                      							lv_label_1_0,
                      							"org.eclipse.xtext.common.Terminals.ID");
                      					
                    }

                    }


                    }


                    }
                    break;

            }

            // InternalTyphonQL.g:482:3: ( (lv_eid_2_0= RULE_ID ) )
            // InternalTyphonQL.g:483:4: (lv_eid_2_0= RULE_ID )
            {
            // InternalTyphonQL.g:483:4: (lv_eid_2_0= RULE_ID )
            // InternalTyphonQL.g:484:5: lv_eid_2_0= RULE_ID
            {
            lv_eid_2_0=(Token)match(input,RULE_ID,FOLLOW_8); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              					newLeafNode(lv_eid_2_0, grammarAccess.getObjAccess().getEidIDTerminalRuleCall_1_0());
              				
            }
            if ( state.backtracking==0 ) {

              					if (current==null) {
              						current = createModelElement(grammarAccess.getObjRule());
              					}
              					setWithLastConsumed(
              						current,
              						"eid",
              						lv_eid_2_0,
              						"org.eclipse.xtext.common.Terminals.ID");
              				
            }

            }


            }

            otherlv_3=(Token)match(input,15,FOLLOW_9); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(otherlv_3, grammarAccess.getObjAccess().getLeftCurlyBracketKeyword_2());
              		
            }
            // InternalTyphonQL.g:504:3: ( (lv_set_4_0= ruleExpr ) )?
            int alt9=2;
            int LA9_0 = input.LA(1);

            if ( ((LA9_0>=RULE_ID && LA9_0<=RULE_ANY_OTHER)||LA9_0==21) ) {
                alt9=1;
            }
            switch (alt9) {
                case 1 :
                    // InternalTyphonQL.g:505:4: (lv_set_4_0= ruleExpr )
                    {
                    // InternalTyphonQL.g:505:4: (lv_set_4_0= ruleExpr )
                    // InternalTyphonQL.g:506:5: lv_set_4_0= ruleExpr
                    {
                    if ( state.backtracking==0 ) {

                      					newCompositeNode(grammarAccess.getObjAccess().getSetExprParserRuleCall_3_0());
                      				
                    }
                    pushFollow(FOLLOW_10);
                    lv_set_4_0=ruleExpr();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      					if (current==null) {
                      						current = createModelElementForParent(grammarAccess.getObjRule());
                      					}
                      					add(
                      						current,
                      						"set",
                      						lv_set_4_0,
                      						"org.xtext.typhonql.TyphonQL.Expr");
                      					afterParserOrEnumRuleCall();
                      				
                    }

                    }


                    }
                    break;

            }

            // InternalTyphonQL.g:523:3: (otherlv_5= ',' ( (lv_set_6_0= ruleExpr ) ) )*
            loop10:
            do {
                int alt10=2;
                int LA10_0 = input.LA(1);

                if ( (LA10_0==16) ) {
                    alt10=1;
                }


                switch (alt10) {
            	case 1 :
            	    // InternalTyphonQL.g:524:4: otherlv_5= ',' ( (lv_set_6_0= ruleExpr ) )
            	    {
            	    otherlv_5=(Token)match(input,16,FOLLOW_6); if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	      				newLeafNode(otherlv_5, grammarAccess.getObjAccess().getCommaKeyword_4_0());
            	      			
            	    }
            	    // InternalTyphonQL.g:528:4: ( (lv_set_6_0= ruleExpr ) )
            	    // InternalTyphonQL.g:529:5: (lv_set_6_0= ruleExpr )
            	    {
            	    // InternalTyphonQL.g:529:5: (lv_set_6_0= ruleExpr )
            	    // InternalTyphonQL.g:530:6: lv_set_6_0= ruleExpr
            	    {
            	    if ( state.backtracking==0 ) {

            	      						newCompositeNode(grammarAccess.getObjAccess().getSetExprParserRuleCall_4_1_0());
            	      					
            	    }
            	    pushFollow(FOLLOW_10);
            	    lv_set_6_0=ruleExpr();

            	    state._fsp--;
            	    if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	      						if (current==null) {
            	      							current = createModelElementForParent(grammarAccess.getObjRule());
            	      						}
            	      						add(
            	      							current,
            	      							"set",
            	      							lv_set_6_0,
            	      							"org.xtext.typhonql.TyphonQL.Expr");
            	      						afterParserOrEnumRuleCall();
            	      					
            	    }

            	    }


            	    }


            	    }
            	    break;

            	default :
            	    break loop10;
                }
            } while (true);

            otherlv_7=(Token)match(input,17,FOLLOW_2); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(otherlv_7, grammarAccess.getObjAccess().getRightCurlyBracketKeyword_5());
              		
            }

            }


            }

            if ( state.backtracking==0 ) {

              	leaveRule();

            }
        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleObj"


    // $ANTLR start "entryRuleExpr"
    // InternalTyphonQL.g:556:1: entryRuleExpr returns [EObject current=null] : iv_ruleExpr= ruleExpr EOF ;
    public final EObject entryRuleExpr() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleExpr = null;


        try {
            // InternalTyphonQL.g:556:45: (iv_ruleExpr= ruleExpr EOF )
            // InternalTyphonQL.g:557:2: iv_ruleExpr= ruleExpr EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getExprRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleExpr=ruleExpr();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleExpr; 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return current;

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleExpr"


    // $ANTLR start "ruleExpr"
    // InternalTyphonQL.g:563:1: ruleExpr returns [EObject current=null] : ( ( (lv_exprs_0_1= ruleStringy | lv_exprs_0_2= ruleObj ) ) )+ ;
    public final EObject ruleExpr() throws RecognitionException {
        EObject current = null;

        EObject lv_exprs_0_1 = null;

        EObject lv_exprs_0_2 = null;



        	enterRule();

        try {
            // InternalTyphonQL.g:569:2: ( ( ( (lv_exprs_0_1= ruleStringy | lv_exprs_0_2= ruleObj ) ) )+ )
            // InternalTyphonQL.g:570:2: ( ( (lv_exprs_0_1= ruleStringy | lv_exprs_0_2= ruleObj ) ) )+
            {
            // InternalTyphonQL.g:570:2: ( ( (lv_exprs_0_1= ruleStringy | lv_exprs_0_2= ruleObj ) ) )+
            int cnt12=0;
            loop12:
            do {
                int alt12=2;
                int LA12_0 = input.LA(1);

                if ( ((LA12_0>=RULE_ID && LA12_0<=RULE_ANY_OTHER)||LA12_0==21) ) {
                    alt12=1;
                }


                switch (alt12) {
            	case 1 :
            	    // InternalTyphonQL.g:571:3: ( (lv_exprs_0_1= ruleStringy | lv_exprs_0_2= ruleObj ) )
            	    {
            	    // InternalTyphonQL.g:571:3: ( (lv_exprs_0_1= ruleStringy | lv_exprs_0_2= ruleObj ) )
            	    // InternalTyphonQL.g:572:4: (lv_exprs_0_1= ruleStringy | lv_exprs_0_2= ruleObj )
            	    {
            	    // InternalTyphonQL.g:572:4: (lv_exprs_0_1= ruleStringy | lv_exprs_0_2= ruleObj )
            	    int alt11=2;
            	    switch ( input.LA(1) ) {
            	    case RULE_VALUE_TERMINAL:
            	    case RULE_STRING:
            	    case RULE_ANY_OTHER:
            	        {
            	        alt11=1;
            	        }
            	        break;
            	    case RULE_ID:
            	        {
            	        int LA11_2 = input.LA(2);

            	        if ( (LA11_2==EOF||(LA11_2>=RULE_ID && LA11_2<=RULE_ANY_OTHER)||LA11_2==14||(LA11_2>=16 && LA11_2<=18)||LA11_2==21) ) {
            	            alt11=1;
            	        }
            	        else if ( (LA11_2==15) ) {
            	            alt11=2;
            	        }
            	        else {
            	            if (state.backtracking>0) {state.failed=true; return current;}
            	            NoViableAltException nvae =
            	                new NoViableAltException("", 11, 2, input);

            	            throw nvae;
            	        }
            	        }
            	        break;
            	    case 21:
            	        {
            	        alt11=2;
            	        }
            	        break;
            	    default:
            	        if (state.backtracking>0) {state.failed=true; return current;}
            	        NoViableAltException nvae =
            	            new NoViableAltException("", 11, 0, input);

            	        throw nvae;
            	    }

            	    switch (alt11) {
            	        case 1 :
            	            // InternalTyphonQL.g:573:5: lv_exprs_0_1= ruleStringy
            	            {
            	            if ( state.backtracking==0 ) {

            	              					newCompositeNode(grammarAccess.getExprAccess().getExprsStringyParserRuleCall_0_0());
            	              				
            	            }
            	            pushFollow(FOLLOW_14);
            	            lv_exprs_0_1=ruleStringy();

            	            state._fsp--;
            	            if (state.failed) return current;
            	            if ( state.backtracking==0 ) {

            	              					if (current==null) {
            	              						current = createModelElementForParent(grammarAccess.getExprRule());
            	              					}
            	              					add(
            	              						current,
            	              						"exprs",
            	              						lv_exprs_0_1,
            	              						"org.xtext.typhonql.TyphonQL.Stringy");
            	              					afterParserOrEnumRuleCall();
            	              				
            	            }

            	            }
            	            break;
            	        case 2 :
            	            // InternalTyphonQL.g:589:5: lv_exprs_0_2= ruleObj
            	            {
            	            if ( state.backtracking==0 ) {

            	              					newCompositeNode(grammarAccess.getExprAccess().getExprsObjParserRuleCall_0_1());
            	              				
            	            }
            	            pushFollow(FOLLOW_14);
            	            lv_exprs_0_2=ruleObj();

            	            state._fsp--;
            	            if (state.failed) return current;
            	            if ( state.backtracking==0 ) {

            	              					if (current==null) {
            	              						current = createModelElementForParent(grammarAccess.getExprRule());
            	              					}
            	              					add(
            	              						current,
            	              						"exprs",
            	              						lv_exprs_0_2,
            	              						"org.xtext.typhonql.TyphonQL.Obj");
            	              					afterParserOrEnumRuleCall();
            	              				
            	            }

            	            }
            	            break;

            	    }


            	    }


            	    }
            	    break;

            	default :
            	    if ( cnt12 >= 1 ) break loop12;
            	    if (state.backtracking>0) {state.failed=true; return current;}
                        EarlyExitException eee =
                            new EarlyExitException(12, input);
                        throw eee;
                }
                cnt12++;
            } while (true);


            }

            if ( state.backtracking==0 ) {

              	leaveRule();

            }
        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleExpr"


    // $ANTLR start "entryRuleStringy"
    // InternalTyphonQL.g:610:1: entryRuleStringy returns [EObject current=null] : iv_ruleStringy= ruleStringy EOF ;
    public final EObject entryRuleStringy() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleStringy = null;


        try {
            // InternalTyphonQL.g:610:48: (iv_ruleStringy= ruleStringy EOF )
            // InternalTyphonQL.g:611:2: iv_ruleStringy= ruleStringy EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getStringyRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleStringy=ruleStringy();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleStringy; 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return current;

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleStringy"


    // $ANTLR start "ruleStringy"
    // InternalTyphonQL.g:617:1: ruleStringy returns [EObject current=null] : ( ( (lv_vals_0_1= RULE_VALUE_TERMINAL | lv_vals_0_2= RULE_STRING | lv_vals_0_3= RULE_ID | lv_vals_0_4= RULE_ANY_OTHER ) ) ) ;
    public final EObject ruleStringy() throws RecognitionException {
        EObject current = null;

        Token lv_vals_0_1=null;
        Token lv_vals_0_2=null;
        Token lv_vals_0_3=null;
        Token lv_vals_0_4=null;


        	enterRule();

        try {
            // InternalTyphonQL.g:623:2: ( ( ( (lv_vals_0_1= RULE_VALUE_TERMINAL | lv_vals_0_2= RULE_STRING | lv_vals_0_3= RULE_ID | lv_vals_0_4= RULE_ANY_OTHER ) ) ) )
            // InternalTyphonQL.g:624:2: ( ( (lv_vals_0_1= RULE_VALUE_TERMINAL | lv_vals_0_2= RULE_STRING | lv_vals_0_3= RULE_ID | lv_vals_0_4= RULE_ANY_OTHER ) ) )
            {
            // InternalTyphonQL.g:624:2: ( ( (lv_vals_0_1= RULE_VALUE_TERMINAL | lv_vals_0_2= RULE_STRING | lv_vals_0_3= RULE_ID | lv_vals_0_4= RULE_ANY_OTHER ) ) )
            // InternalTyphonQL.g:625:3: ( (lv_vals_0_1= RULE_VALUE_TERMINAL | lv_vals_0_2= RULE_STRING | lv_vals_0_3= RULE_ID | lv_vals_0_4= RULE_ANY_OTHER ) )
            {
            // InternalTyphonQL.g:625:3: ( (lv_vals_0_1= RULE_VALUE_TERMINAL | lv_vals_0_2= RULE_STRING | lv_vals_0_3= RULE_ID | lv_vals_0_4= RULE_ANY_OTHER ) )
            // InternalTyphonQL.g:626:4: (lv_vals_0_1= RULE_VALUE_TERMINAL | lv_vals_0_2= RULE_STRING | lv_vals_0_3= RULE_ID | lv_vals_0_4= RULE_ANY_OTHER )
            {
            // InternalTyphonQL.g:626:4: (lv_vals_0_1= RULE_VALUE_TERMINAL | lv_vals_0_2= RULE_STRING | lv_vals_0_3= RULE_ID | lv_vals_0_4= RULE_ANY_OTHER )
            int alt13=4;
            switch ( input.LA(1) ) {
            case RULE_VALUE_TERMINAL:
                {
                alt13=1;
                }
                break;
            case RULE_STRING:
                {
                alt13=2;
                }
                break;
            case RULE_ID:
                {
                alt13=3;
                }
                break;
            case RULE_ANY_OTHER:
                {
                alt13=4;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return current;}
                NoViableAltException nvae =
                    new NoViableAltException("", 13, 0, input);

                throw nvae;
            }

            switch (alt13) {
                case 1 :
                    // InternalTyphonQL.g:627:5: lv_vals_0_1= RULE_VALUE_TERMINAL
                    {
                    lv_vals_0_1=(Token)match(input,RULE_VALUE_TERMINAL,FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      					newLeafNode(lv_vals_0_1, grammarAccess.getStringyAccess().getValsVALUE_TERMINALTerminalRuleCall_0_0());
                      				
                    }
                    if ( state.backtracking==0 ) {

                      					if (current==null) {
                      						current = createModelElement(grammarAccess.getStringyRule());
                      					}
                      					addWithLastConsumed(
                      						current,
                      						"vals",
                      						lv_vals_0_1,
                      						"org.xtext.typhonql.TyphonQL.VALUE_TERMINAL");
                      				
                    }

                    }
                    break;
                case 2 :
                    // InternalTyphonQL.g:642:5: lv_vals_0_2= RULE_STRING
                    {
                    lv_vals_0_2=(Token)match(input,RULE_STRING,FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      					newLeafNode(lv_vals_0_2, grammarAccess.getStringyAccess().getValsSTRINGTerminalRuleCall_0_1());
                      				
                    }
                    if ( state.backtracking==0 ) {

                      					if (current==null) {
                      						current = createModelElement(grammarAccess.getStringyRule());
                      					}
                      					addWithLastConsumed(
                      						current,
                      						"vals",
                      						lv_vals_0_2,
                      						"org.eclipse.xtext.common.Terminals.STRING");
                      				
                    }

                    }
                    break;
                case 3 :
                    // InternalTyphonQL.g:657:5: lv_vals_0_3= RULE_ID
                    {
                    lv_vals_0_3=(Token)match(input,RULE_ID,FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      					newLeafNode(lv_vals_0_3, grammarAccess.getStringyAccess().getValsIDTerminalRuleCall_0_2());
                      				
                    }
                    if ( state.backtracking==0 ) {

                      					if (current==null) {
                      						current = createModelElement(grammarAccess.getStringyRule());
                      					}
                      					addWithLastConsumed(
                      						current,
                      						"vals",
                      						lv_vals_0_3,
                      						"org.eclipse.xtext.common.Terminals.ID");
                      				
                    }

                    }
                    break;
                case 4 :
                    // InternalTyphonQL.g:672:5: lv_vals_0_4= RULE_ANY_OTHER
                    {
                    lv_vals_0_4=(Token)match(input,RULE_ANY_OTHER,FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      					newLeafNode(lv_vals_0_4, grammarAccess.getStringyAccess().getValsANY_OTHERTerminalRuleCall_0_3());
                      				
                    }
                    if ( state.backtracking==0 ) {

                      					if (current==null) {
                      						current = createModelElement(grammarAccess.getStringyRule());
                      					}
                      					addWithLastConsumed(
                      						current,
                      						"vals",
                      						lv_vals_0_4,
                      						"org.eclipse.xtext.common.Terminals.ANY_OTHER");
                      				
                    }

                    }
                    break;

            }


            }


            }


            }

            if ( state.backtracking==0 ) {

              	leaveRule();

            }
        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleStringy"

    // Delegated rules


 

    public static final BitSet FOLLOW_1 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_2 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_3 = new BitSet(new long[]{0x0000000000181002L});
    public static final BitSet FOLLOW_4 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_5 = new BitSet(new long[]{0x0000000000006000L});
    public static final BitSet FOLLOW_6 = new BitSet(new long[]{0x00000000002000F0L});
    public static final BitSet FOLLOW_7 = new BitSet(new long[]{0x0000000000004000L});
    public static final BitSet FOLLOW_8 = new BitSet(new long[]{0x0000000000008000L});
    public static final BitSet FOLLOW_9 = new BitSet(new long[]{0x00000000002300F0L});
    public static final BitSet FOLLOW_10 = new BitSet(new long[]{0x0000000000030000L});
    public static final BitSet FOLLOW_11 = new BitSet(new long[]{0x0000000000040000L});
    public static final BitSet FOLLOW_12 = new BitSet(new long[]{0x0000000000042000L});
    public static final BitSet FOLLOW_13 = new BitSet(new long[]{0x0000000000050000L});
    public static final BitSet FOLLOW_14 = new BitSet(new long[]{0x00000000002000F2L});

}