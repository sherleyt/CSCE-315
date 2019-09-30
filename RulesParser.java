// Generated from Rules.g4 by ANTLR 4.7.2
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class RulesParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.7.2", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		T__9=10, T__10=11, T__11=12, T__12=13, T__13=14, T__14=15, T__15=16, T__16=17, 
		T__17=18, T__18=19, T__19=20, T__20=21, T__21=22, T__22=23, T__23=24, 
		T__24=25, T__25=26, T__26=27, OP=28, ALPHA=29, DIGIT=30, INTEGER=31, IDENTIFIER=32, 
		STRING_LITERAL=33, WS=34, LOGICAL_OR=35, LOGICAL_AND=36;
	public static final int
		RULE_literal = 0, RULE_relation_name = 1, RULE_attribute_name = 2, RULE_operand = 3, 
		RULE_type = 4, RULE_attribute_list = 5, RULE_typed_attribute_list = 6, 
		RULE_open_cmd = 7, RULE_close_cmd = 8, RULE_write_cmd = 9, RULE_exit_cmd = 10, 
		RULE_condition = 11, RULE_comparison = 12, RULE_expr = 13, RULE_atomic_expr = 14, 
		RULE_selection = 15, RULE_projection = 16, RULE_renaming = 17, RULE_union = 18, 
		RULE_difference = 19, RULE_product = 20, RULE_natural_join = 21, RULE_show_cmd = 22, 
		RULE_create_cmd = 23, RULE_update_cmd = 24, RULE_insert_cmd = 25, RULE_delete_cmd = 26, 
		RULE_command = 27, RULE_query = 28, RULE_program = 29;
	private static String[] makeRuleNames() {
		return new String[] {
			"literal", "relation_name", "attribute_name", "operand", "type", "attribute_list", 
			"typed_attribute_list", "open_cmd", "close_cmd", "write_cmd", "exit_cmd", 
			"condition", "comparison", "expr", "atomic_expr", "selection", "projection", 
			"renaming", "union", "difference", "product", "natural_join", "show_cmd", 
			"create_cmd", "update_cmd", "insert_cmd", "delete_cmd", "command", "query", 
			"program"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'VARCHAR'", "'('", "')'", "','", "'OPEN'", "'CLOSE'", "'WRITE'", 
			"'EXIT'", "'select'", "'project'", "'rename'", "'+'", "'-'", "'*'", "'&'", 
			"'SHOW'", "'CREATE TABLE'", "'PRIMARY KEY'", "'UPDATE'", "'SET'", "'='", 
			"'WHERE'", "'INSERT INTO'", "'VALUES FROM'", "'VALUES FROM RELATION'", 
			"'DELETE FROM'", "'<-'", null, null, null, null, null, null, null, "'||'", 
			"'&&'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, "OP", "ALPHA", "DIGIT", "INTEGER", "IDENTIFIER", 
			"STRING_LITERAL", "WS", "LOGICAL_OR", "LOGICAL_AND"
		};
	}
	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}

	@Override
	public String getGrammarFileName() { return "Rules.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public RulesParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	public static class LiteralContext extends ParserRuleContext {
		public TerminalNode STRING_LITERAL() { return getToken(RulesParser.STRING_LITERAL, 0); }
		public TerminalNode INTEGER() { return getToken(RulesParser.INTEGER, 0); }
		public LiteralContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_literal; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RulesListener ) ((RulesListener)listener).enterLiteral(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RulesListener ) ((RulesListener)listener).exitLiteral(this);
		}
	}

	public final LiteralContext literal() throws RecognitionException {
		LiteralContext _localctx = new LiteralContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_literal);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(60);
			_la = _input.LA(1);
			if ( !(_la==INTEGER || _la==STRING_LITERAL) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Relation_nameContext extends ParserRuleContext {
		public TerminalNode IDENTIFIER() { return getToken(RulesParser.IDENTIFIER, 0); }
		public Relation_nameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_relation_name; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RulesListener ) ((RulesListener)listener).enterRelation_name(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RulesListener ) ((RulesListener)listener).exitRelation_name(this);
		}
	}

	public final Relation_nameContext relation_name() throws RecognitionException {
		Relation_nameContext _localctx = new Relation_nameContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_relation_name);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(62);
			match(IDENTIFIER);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Attribute_nameContext extends ParserRuleContext {
		public TerminalNode IDENTIFIER() { return getToken(RulesParser.IDENTIFIER, 0); }
		public Attribute_nameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_attribute_name; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RulesListener ) ((RulesListener)listener).enterAttribute_name(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RulesListener ) ((RulesListener)listener).exitAttribute_name(this);
		}
	}

	public final Attribute_nameContext attribute_name() throws RecognitionException {
		Attribute_nameContext _localctx = new Attribute_nameContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_attribute_name);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(64);
			match(IDENTIFIER);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class OperandContext extends ParserRuleContext {
		public Attribute_nameContext attribute_name() {
			return getRuleContext(Attribute_nameContext.class,0);
		}
		public LiteralContext literal() {
			return getRuleContext(LiteralContext.class,0);
		}
		public OperandContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_operand; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RulesListener ) ((RulesListener)listener).enterOperand(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RulesListener ) ((RulesListener)listener).exitOperand(this);
		}
	}

	public final OperandContext operand() throws RecognitionException {
		OperandContext _localctx = new OperandContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_operand);
		try {
			setState(68);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case IDENTIFIER:
				enterOuterAlt(_localctx, 1);
				{
				setState(66);
				attribute_name();
				}
				break;
			case INTEGER:
			case STRING_LITERAL:
				enterOuterAlt(_localctx, 2);
				{
				setState(67);
				literal();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class TypeContext extends ParserRuleContext {
		public TerminalNode INTEGER() { return getToken(RulesParser.INTEGER, 0); }
		public TypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_type; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RulesListener ) ((RulesListener)listener).enterType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RulesListener ) ((RulesListener)listener).exitType(this);
		}
	}

	public final TypeContext type() throws RecognitionException {
		TypeContext _localctx = new TypeContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_type);
		try {
			setState(75);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__0:
				enterOuterAlt(_localctx, 1);
				{
				setState(70);
				match(T__0);
				setState(71);
				match(T__1);
				setState(72);
				match(INTEGER);
				setState(73);
				match(T__2);
				}
				break;
			case INTEGER:
				enterOuterAlt(_localctx, 2);
				{
				setState(74);
				match(INTEGER);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Attribute_listContext extends ParserRuleContext {
		public List<Attribute_nameContext> attribute_name() {
			return getRuleContexts(Attribute_nameContext.class);
		}
		public Attribute_nameContext attribute_name(int i) {
			return getRuleContext(Attribute_nameContext.class,i);
		}
		public Attribute_listContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_attribute_list; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RulesListener ) ((RulesListener)listener).enterAttribute_list(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RulesListener ) ((RulesListener)listener).exitAttribute_list(this);
		}
	}

	public final Attribute_listContext attribute_list() throws RecognitionException {
		Attribute_listContext _localctx = new Attribute_listContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_attribute_list);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(77);
			attribute_name();
			setState(82);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__3) {
				{
				{
				setState(78);
				match(T__3);
				setState(79);
				attribute_name();
				}
				}
				setState(84);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Typed_attribute_listContext extends ParserRuleContext {
		public List<Attribute_nameContext> attribute_name() {
			return getRuleContexts(Attribute_nameContext.class);
		}
		public Attribute_nameContext attribute_name(int i) {
			return getRuleContext(Attribute_nameContext.class,i);
		}
		public List<TypeContext> type() {
			return getRuleContexts(TypeContext.class);
		}
		public TypeContext type(int i) {
			return getRuleContext(TypeContext.class,i);
		}
		public Typed_attribute_listContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_typed_attribute_list; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RulesListener ) ((RulesListener)listener).enterTyped_attribute_list(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RulesListener ) ((RulesListener)listener).exitTyped_attribute_list(this);
		}
	}

	public final Typed_attribute_listContext typed_attribute_list() throws RecognitionException {
		Typed_attribute_listContext _localctx = new Typed_attribute_listContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_typed_attribute_list);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(85);
			attribute_name();
			setState(86);
			type();
			setState(93);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__3) {
				{
				{
				setState(87);
				match(T__3);
				setState(88);
				attribute_name();
				setState(89);
				type();
				}
				}
				setState(95);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Open_cmdContext extends ParserRuleContext {
		public Relation_nameContext relation_name() {
			return getRuleContext(Relation_nameContext.class,0);
		}
		public Open_cmdContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_open_cmd; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RulesListener ) ((RulesListener)listener).enterOpen_cmd(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RulesListener ) ((RulesListener)listener).exitOpen_cmd(this);
		}
	}

	public final Open_cmdContext open_cmd() throws RecognitionException {
		Open_cmdContext _localctx = new Open_cmdContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_open_cmd);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(96);
			match(T__4);
			setState(97);
			relation_name();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Close_cmdContext extends ParserRuleContext {
		public Relation_nameContext relation_name() {
			return getRuleContext(Relation_nameContext.class,0);
		}
		public Close_cmdContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_close_cmd; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RulesListener ) ((RulesListener)listener).enterClose_cmd(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RulesListener ) ((RulesListener)listener).exitClose_cmd(this);
		}
	}

	public final Close_cmdContext close_cmd() throws RecognitionException {
		Close_cmdContext _localctx = new Close_cmdContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_close_cmd);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(99);
			match(T__5);
			setState(100);
			relation_name();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Write_cmdContext extends ParserRuleContext {
		public Relation_nameContext relation_name() {
			return getRuleContext(Relation_nameContext.class,0);
		}
		public Write_cmdContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_write_cmd; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RulesListener ) ((RulesListener)listener).enterWrite_cmd(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RulesListener ) ((RulesListener)listener).exitWrite_cmd(this);
		}
	}

	public final Write_cmdContext write_cmd() throws RecognitionException {
		Write_cmdContext _localctx = new Write_cmdContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_write_cmd);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(102);
			match(T__6);
			setState(103);
			relation_name();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Exit_cmdContext extends ParserRuleContext {
		public Exit_cmdContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_exit_cmd; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RulesListener ) ((RulesListener)listener).enterExit_cmd(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RulesListener ) ((RulesListener)listener).exitExit_cmd(this);
		}
	}

	public final Exit_cmdContext exit_cmd() throws RecognitionException {
		Exit_cmdContext _localctx = new Exit_cmdContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_exit_cmd);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(105);
			match(T__7);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ConditionContext extends ParserRuleContext {
		public ComparisonContext comparison() {
			return getRuleContext(ComparisonContext.class,0);
		}
		public List<ConditionContext> condition() {
			return getRuleContexts(ConditionContext.class);
		}
		public ConditionContext condition(int i) {
			return getRuleContext(ConditionContext.class,i);
		}
		public TerminalNode LOGICAL_AND() { return getToken(RulesParser.LOGICAL_AND, 0); }
		public TerminalNode LOGICAL_OR() { return getToken(RulesParser.LOGICAL_OR, 0); }
		public ConditionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_condition; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RulesListener ) ((RulesListener)listener).enterCondition(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RulesListener ) ((RulesListener)listener).exitCondition(this);
		}
	}

	public final ConditionContext condition() throws RecognitionException {
		return condition(0);
	}

	private ConditionContext condition(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		ConditionContext _localctx = new ConditionContext(_ctx, _parentState);
		ConditionContext _prevctx = _localctx;
		int _startState = 22;
		enterRecursionRule(_localctx, 22, RULE_condition, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			{
			setState(108);
			comparison();
			}
			_ctx.stop = _input.LT(-1);
			setState(118);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,5,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(116);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,4,_ctx) ) {
					case 1:
						{
						_localctx = new ConditionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_condition);
						setState(110);
						if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
						setState(111);
						match(LOGICAL_AND);
						setState(112);
						condition(3);
						}
						break;
					case 2:
						{
						_localctx = new ConditionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_condition);
						setState(113);
						if (!(precpred(_ctx, 1))) throw new FailedPredicateException(this, "precpred(_ctx, 1)");
						setState(114);
						match(LOGICAL_OR);
						setState(115);
						condition(2);
						}
						break;
					}
					} 
				}
				setState(120);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,5,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public static class ComparisonContext extends ParserRuleContext {
		public List<OperandContext> operand() {
			return getRuleContexts(OperandContext.class);
		}
		public OperandContext operand(int i) {
			return getRuleContext(OperandContext.class,i);
		}
		public TerminalNode OP() { return getToken(RulesParser.OP, 0); }
		public ComparisonContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_comparison; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RulesListener ) ((RulesListener)listener).enterComparison(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RulesListener ) ((RulesListener)listener).exitComparison(this);
		}
	}

	public final ComparisonContext comparison() throws RecognitionException {
		ComparisonContext _localctx = new ComparisonContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_comparison);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(121);
			operand();
			setState(124);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,6,_ctx) ) {
			case 1:
				{
				setState(122);
				match(OP);
				setState(123);
				operand();
				}
				break;
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ExprContext extends ParserRuleContext {
		public Atomic_exprContext atomic_expr() {
			return getRuleContext(Atomic_exprContext.class,0);
		}
		public SelectionContext selection() {
			return getRuleContext(SelectionContext.class,0);
		}
		public ProjectionContext projection() {
			return getRuleContext(ProjectionContext.class,0);
		}
		public RenamingContext renaming() {
			return getRuleContext(RenamingContext.class,0);
		}
		public UnionContext union() {
			return getRuleContext(UnionContext.class,0);
		}
		public DifferenceContext difference() {
			return getRuleContext(DifferenceContext.class,0);
		}
		public ProductContext product() {
			return getRuleContext(ProductContext.class,0);
		}
		public Natural_joinContext natural_join() {
			return getRuleContext(Natural_joinContext.class,0);
		}
		public ExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RulesListener ) ((RulesListener)listener).enterExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RulesListener ) ((RulesListener)listener).exitExpr(this);
		}
	}

	public final ExprContext expr() throws RecognitionException {
		ExprContext _localctx = new ExprContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_expr);
		try {
			setState(134);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,7,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(126);
				atomic_expr();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(127);
				selection();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(128);
				projection();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(129);
				renaming();
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(130);
				union();
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(131);
				difference();
				}
				break;
			case 7:
				enterOuterAlt(_localctx, 7);
				{
				setState(132);
				product();
				}
				break;
			case 8:
				enterOuterAlt(_localctx, 8);
				{
				setState(133);
				natural_join();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Atomic_exprContext extends ParserRuleContext {
		public Relation_nameContext relation_name() {
			return getRuleContext(Relation_nameContext.class,0);
		}
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public Atomic_exprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_atomic_expr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RulesListener ) ((RulesListener)listener).enterAtomic_expr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RulesListener ) ((RulesListener)listener).exitAtomic_expr(this);
		}
	}

	public final Atomic_exprContext atomic_expr() throws RecognitionException {
		Atomic_exprContext _localctx = new Atomic_exprContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_atomic_expr);
		try {
			setState(141);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case IDENTIFIER:
				enterOuterAlt(_localctx, 1);
				{
				setState(136);
				relation_name();
				}
				break;
			case T__1:
				enterOuterAlt(_localctx, 2);
				{
				setState(137);
				match(T__1);
				setState(138);
				expr();
				setState(139);
				match(T__2);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class SelectionContext extends ParserRuleContext {
		public Atomic_exprContext atomic_expr() {
			return getRuleContext(Atomic_exprContext.class,0);
		}
		public List<ConditionContext> condition() {
			return getRuleContexts(ConditionContext.class);
		}
		public ConditionContext condition(int i) {
			return getRuleContext(ConditionContext.class,i);
		}
		public List<TerminalNode> LOGICAL_OR() { return getTokens(RulesParser.LOGICAL_OR); }
		public TerminalNode LOGICAL_OR(int i) {
			return getToken(RulesParser.LOGICAL_OR, i);
		}
		public SelectionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_selection; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RulesListener ) ((RulesListener)listener).enterSelection(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RulesListener ) ((RulesListener)listener).exitSelection(this);
		}
	}

	public final SelectionContext selection() throws RecognitionException {
		SelectionContext _localctx = new SelectionContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_selection);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(143);
			match(T__8);
			setState(144);
			match(T__1);
			setState(153);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << INTEGER) | (1L << IDENTIFIER) | (1L << STRING_LITERAL))) != 0)) {
				{
				{
				setState(145);
				condition(0);
				setState(146);
				match(T__1);
				setState(147);
				match(LOGICAL_OR);
				setState(148);
				condition(0);
				setState(149);
				match(T__2);
				}
				}
				setState(155);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(156);
			match(T__2);
			setState(157);
			atomic_expr();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ProjectionContext extends ParserRuleContext {
		public Attribute_listContext attribute_list() {
			return getRuleContext(Attribute_listContext.class,0);
		}
		public Atomic_exprContext atomic_expr() {
			return getRuleContext(Atomic_exprContext.class,0);
		}
		public ProjectionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_projection; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RulesListener ) ((RulesListener)listener).enterProjection(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RulesListener ) ((RulesListener)listener).exitProjection(this);
		}
	}

	public final ProjectionContext projection() throws RecognitionException {
		ProjectionContext _localctx = new ProjectionContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_projection);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(159);
			match(T__9);
			setState(160);
			match(T__1);
			setState(161);
			attribute_list();
			setState(162);
			match(T__2);
			setState(163);
			atomic_expr();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class RenamingContext extends ParserRuleContext {
		public Attribute_listContext attribute_list() {
			return getRuleContext(Attribute_listContext.class,0);
		}
		public Atomic_exprContext atomic_expr() {
			return getRuleContext(Atomic_exprContext.class,0);
		}
		public RenamingContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_renaming; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RulesListener ) ((RulesListener)listener).enterRenaming(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RulesListener ) ((RulesListener)listener).exitRenaming(this);
		}
	}

	public final RenamingContext renaming() throws RecognitionException {
		RenamingContext _localctx = new RenamingContext(_ctx, getState());
		enterRule(_localctx, 34, RULE_renaming);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(165);
			match(T__10);
			setState(166);
			match(T__1);
			setState(167);
			attribute_list();
			setState(168);
			match(T__2);
			setState(169);
			atomic_expr();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class UnionContext extends ParserRuleContext {
		public List<Atomic_exprContext> atomic_expr() {
			return getRuleContexts(Atomic_exprContext.class);
		}
		public Atomic_exprContext atomic_expr(int i) {
			return getRuleContext(Atomic_exprContext.class,i);
		}
		public UnionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_union; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RulesListener ) ((RulesListener)listener).enterUnion(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RulesListener ) ((RulesListener)listener).exitUnion(this);
		}
	}

	public final UnionContext union() throws RecognitionException {
		UnionContext _localctx = new UnionContext(_ctx, getState());
		enterRule(_localctx, 36, RULE_union);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(171);
			atomic_expr();
			setState(172);
			match(T__11);
			setState(173);
			atomic_expr();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class DifferenceContext extends ParserRuleContext {
		public List<Atomic_exprContext> atomic_expr() {
			return getRuleContexts(Atomic_exprContext.class);
		}
		public Atomic_exprContext atomic_expr(int i) {
			return getRuleContext(Atomic_exprContext.class,i);
		}
		public DifferenceContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_difference; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RulesListener ) ((RulesListener)listener).enterDifference(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RulesListener ) ((RulesListener)listener).exitDifference(this);
		}
	}

	public final DifferenceContext difference() throws RecognitionException {
		DifferenceContext _localctx = new DifferenceContext(_ctx, getState());
		enterRule(_localctx, 38, RULE_difference);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(175);
			atomic_expr();
			setState(176);
			match(T__12);
			setState(177);
			atomic_expr();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ProductContext extends ParserRuleContext {
		public List<Atomic_exprContext> atomic_expr() {
			return getRuleContexts(Atomic_exprContext.class);
		}
		public Atomic_exprContext atomic_expr(int i) {
			return getRuleContext(Atomic_exprContext.class,i);
		}
		public ProductContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_product; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RulesListener ) ((RulesListener)listener).enterProduct(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RulesListener ) ((RulesListener)listener).exitProduct(this);
		}
	}

	public final ProductContext product() throws RecognitionException {
		ProductContext _localctx = new ProductContext(_ctx, getState());
		enterRule(_localctx, 40, RULE_product);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(179);
			atomic_expr();
			setState(180);
			match(T__13);
			setState(181);
			atomic_expr();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Natural_joinContext extends ParserRuleContext {
		public List<Atomic_exprContext> atomic_expr() {
			return getRuleContexts(Atomic_exprContext.class);
		}
		public Atomic_exprContext atomic_expr(int i) {
			return getRuleContext(Atomic_exprContext.class,i);
		}
		public Natural_joinContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_natural_join; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RulesListener ) ((RulesListener)listener).enterNatural_join(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RulesListener ) ((RulesListener)listener).exitNatural_join(this);
		}
	}

	public final Natural_joinContext natural_join() throws RecognitionException {
		Natural_joinContext _localctx = new Natural_joinContext(_ctx, getState());
		enterRule(_localctx, 42, RULE_natural_join);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(183);
			atomic_expr();
			setState(184);
			match(T__14);
			setState(185);
			atomic_expr();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Show_cmdContext extends ParserRuleContext {
		public Atomic_exprContext atomic_expr() {
			return getRuleContext(Atomic_exprContext.class,0);
		}
		public Show_cmdContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_show_cmd; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RulesListener ) ((RulesListener)listener).enterShow_cmd(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RulesListener ) ((RulesListener)listener).exitShow_cmd(this);
		}
	}

	public final Show_cmdContext show_cmd() throws RecognitionException {
		Show_cmdContext _localctx = new Show_cmdContext(_ctx, getState());
		enterRule(_localctx, 44, RULE_show_cmd);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(187);
			match(T__15);
			setState(188);
			atomic_expr();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Create_cmdContext extends ParserRuleContext {
		public Relation_nameContext relation_name() {
			return getRuleContext(Relation_nameContext.class,0);
		}
		public Typed_attribute_listContext typed_attribute_list() {
			return getRuleContext(Typed_attribute_listContext.class,0);
		}
		public Attribute_listContext attribute_list() {
			return getRuleContext(Attribute_listContext.class,0);
		}
		public Create_cmdContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_create_cmd; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RulesListener ) ((RulesListener)listener).enterCreate_cmd(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RulesListener ) ((RulesListener)listener).exitCreate_cmd(this);
		}
	}

	public final Create_cmdContext create_cmd() throws RecognitionException {
		Create_cmdContext _localctx = new Create_cmdContext(_ctx, getState());
		enterRule(_localctx, 46, RULE_create_cmd);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(190);
			match(T__16);
			setState(191);
			relation_name();
			setState(192);
			match(T__1);
			setState(193);
			typed_attribute_list();
			setState(194);
			match(T__2);
			setState(195);
			match(T__17);
			setState(196);
			match(T__1);
			setState(197);
			attribute_list();
			setState(198);
			match(T__2);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Update_cmdContext extends ParserRuleContext {
		public Relation_nameContext relation_name() {
			return getRuleContext(Relation_nameContext.class,0);
		}
		public List<Attribute_nameContext> attribute_name() {
			return getRuleContexts(Attribute_nameContext.class);
		}
		public Attribute_nameContext attribute_name(int i) {
			return getRuleContext(Attribute_nameContext.class,i);
		}
		public List<LiteralContext> literal() {
			return getRuleContexts(LiteralContext.class);
		}
		public LiteralContext literal(int i) {
			return getRuleContext(LiteralContext.class,i);
		}
		public ConditionContext condition() {
			return getRuleContext(ConditionContext.class,0);
		}
		public Update_cmdContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_update_cmd; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RulesListener ) ((RulesListener)listener).enterUpdate_cmd(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RulesListener ) ((RulesListener)listener).exitUpdate_cmd(this);
		}
	}

	public final Update_cmdContext update_cmd() throws RecognitionException {
		Update_cmdContext _localctx = new Update_cmdContext(_ctx, getState());
		enterRule(_localctx, 48, RULE_update_cmd);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(200);
			match(T__18);
			setState(201);
			relation_name();
			setState(202);
			match(T__19);
			setState(203);
			attribute_name();
			setState(204);
			match(T__20);
			setState(205);
			literal();
			setState(213);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__3) {
				{
				{
				setState(206);
				match(T__3);
				setState(207);
				attribute_name();
				setState(208);
				match(T__20);
				setState(209);
				literal();
				}
				}
				setState(215);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(216);
			match(T__21);
			setState(217);
			condition(0);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Insert_cmdContext extends ParserRuleContext {
		public Relation_nameContext relation_name() {
			return getRuleContext(Relation_nameContext.class,0);
		}
		public List<LiteralContext> literal() {
			return getRuleContexts(LiteralContext.class);
		}
		public LiteralContext literal(int i) {
			return getRuleContext(LiteralContext.class,i);
		}
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public Insert_cmdContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_insert_cmd; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RulesListener ) ((RulesListener)listener).enterInsert_cmd(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RulesListener ) ((RulesListener)listener).exitInsert_cmd(this);
		}
	}

	public final Insert_cmdContext insert_cmd() throws RecognitionException {
		Insert_cmdContext _localctx = new Insert_cmdContext(_ctx, getState());
		enterRule(_localctx, 50, RULE_insert_cmd);
		int _la;
		try {
			setState(238);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,12,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(219);
				match(T__22);
				setState(220);
				relation_name();
				setState(221);
				match(T__23);
				setState(222);
				match(T__1);
				setState(223);
				literal();
				setState(228);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__3) {
					{
					{
					setState(224);
					match(T__3);
					setState(225);
					literal();
					}
					}
					setState(230);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(231);
				match(T__2);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(233);
				match(T__22);
				setState(234);
				relation_name();
				setState(235);
				match(T__24);
				setState(236);
				expr();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Delete_cmdContext extends ParserRuleContext {
		public Relation_nameContext relation_name() {
			return getRuleContext(Relation_nameContext.class,0);
		}
		public ConditionContext condition() {
			return getRuleContext(ConditionContext.class,0);
		}
		public Delete_cmdContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_delete_cmd; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RulesListener ) ((RulesListener)listener).enterDelete_cmd(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RulesListener ) ((RulesListener)listener).exitDelete_cmd(this);
		}
	}

	public final Delete_cmdContext delete_cmd() throws RecognitionException {
		Delete_cmdContext _localctx = new Delete_cmdContext(_ctx, getState());
		enterRule(_localctx, 52, RULE_delete_cmd);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(240);
			match(T__25);
			setState(241);
			relation_name();
			setState(242);
			match(T__21);
			setState(243);
			condition(0);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class CommandContext extends ParserRuleContext {
		public Open_cmdContext open_cmd() {
			return getRuleContext(Open_cmdContext.class,0);
		}
		public Close_cmdContext close_cmd() {
			return getRuleContext(Close_cmdContext.class,0);
		}
		public Write_cmdContext write_cmd() {
			return getRuleContext(Write_cmdContext.class,0);
		}
		public Exit_cmdContext exit_cmd() {
			return getRuleContext(Exit_cmdContext.class,0);
		}
		public Show_cmdContext show_cmd() {
			return getRuleContext(Show_cmdContext.class,0);
		}
		public Create_cmdContext create_cmd() {
			return getRuleContext(Create_cmdContext.class,0);
		}
		public Update_cmdContext update_cmd() {
			return getRuleContext(Update_cmdContext.class,0);
		}
		public Insert_cmdContext insert_cmd() {
			return getRuleContext(Insert_cmdContext.class,0);
		}
		public Delete_cmdContext delete_cmd() {
			return getRuleContext(Delete_cmdContext.class,0);
		}
		public CommandContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_command; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RulesListener ) ((RulesListener)listener).enterCommand(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RulesListener ) ((RulesListener)listener).exitCommand(this);
		}
	}

	public final CommandContext command() throws RecognitionException {
		CommandContext _localctx = new CommandContext(_ctx, getState());
		enterRule(_localctx, 54, RULE_command);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(254);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__4:
				{
				setState(245);
				open_cmd();
				}
				break;
			case T__5:
				{
				setState(246);
				close_cmd();
				}
				break;
			case T__6:
				{
				setState(247);
				write_cmd();
				}
				break;
			case T__7:
				{
				setState(248);
				exit_cmd();
				}
				break;
			case T__15:
				{
				setState(249);
				show_cmd();
				}
				break;
			case T__16:
				{
				setState(250);
				create_cmd();
				}
				break;
			case T__18:
				{
				setState(251);
				update_cmd();
				}
				break;
			case T__22:
				{
				setState(252);
				insert_cmd();
				}
				break;
			case T__25:
				{
				setState(253);
				delete_cmd();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class QueryContext extends ParserRuleContext {
		public Relation_nameContext relation_name() {
			return getRuleContext(Relation_nameContext.class,0);
		}
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public QueryContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_query; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RulesListener ) ((RulesListener)listener).enterQuery(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RulesListener ) ((RulesListener)listener).exitQuery(this);
		}
	}

	public final QueryContext query() throws RecognitionException {
		QueryContext _localctx = new QueryContext(_ctx, getState());
		enterRule(_localctx, 56, RULE_query);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(256);
			relation_name();
			setState(257);
			match(T__26);
			setState(258);
			expr();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ProgramContext extends ParserRuleContext {
		public List<QueryContext> query() {
			return getRuleContexts(QueryContext.class);
		}
		public QueryContext query(int i) {
			return getRuleContext(QueryContext.class,i);
		}
		public List<CommandContext> command() {
			return getRuleContexts(CommandContext.class);
		}
		public CommandContext command(int i) {
			return getRuleContext(CommandContext.class,i);
		}
		public ProgramContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_program; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RulesListener ) ((RulesListener)listener).enterProgram(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RulesListener ) ((RulesListener)listener).exitProgram(this);
		}
	}

	public final ProgramContext program() throws RecognitionException {
		ProgramContext _localctx = new ProgramContext(_ctx, getState());
		enterRule(_localctx, 58, RULE_program);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(264);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__4) | (1L << T__5) | (1L << T__6) | (1L << T__7) | (1L << T__15) | (1L << T__16) | (1L << T__18) | (1L << T__22) | (1L << T__25) | (1L << IDENTIFIER))) != 0)) {
				{
				setState(262);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case IDENTIFIER:
					{
					setState(260);
					query();
					}
					break;
				case T__4:
				case T__5:
				case T__6:
				case T__7:
				case T__15:
				case T__16:
				case T__18:
				case T__22:
				case T__25:
					{
					setState(261);
					command();
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				setState(266);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public boolean sempred(RuleContext _localctx, int ruleIndex, int predIndex) {
		switch (ruleIndex) {
		case 11:
			return condition_sempred((ConditionContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean condition_sempred(ConditionContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0:
			return precpred(_ctx, 2);
		case 1:
			return precpred(_ctx, 1);
		}
		return true;
	}

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3&\u010e\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\3\2\3\2\3"+
		"\3\3\3\3\4\3\4\3\5\3\5\5\5G\n\5\3\6\3\6\3\6\3\6\3\6\5\6N\n\6\3\7\3\7\3"+
		"\7\7\7S\n\7\f\7\16\7V\13\7\3\b\3\b\3\b\3\b\3\b\3\b\7\b^\n\b\f\b\16\ba"+
		"\13\b\3\t\3\t\3\t\3\n\3\n\3\n\3\13\3\13\3\13\3\f\3\f\3\r\3\r\3\r\3\r\3"+
		"\r\3\r\3\r\3\r\3\r\7\rw\n\r\f\r\16\rz\13\r\3\16\3\16\3\16\5\16\177\n\16"+
		"\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\5\17\u0089\n\17\3\20\3\20\3\20"+
		"\3\20\3\20\5\20\u0090\n\20\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\7\21"+
		"\u009a\n\21\f\21\16\21\u009d\13\21\3\21\3\21\3\21\3\22\3\22\3\22\3\22"+
		"\3\22\3\22\3\23\3\23\3\23\3\23\3\23\3\23\3\24\3\24\3\24\3\24\3\25\3\25"+
		"\3\25\3\25\3\26\3\26\3\26\3\26\3\27\3\27\3\27\3\27\3\30\3\30\3\30\3\31"+
		"\3\31\3\31\3\31\3\31\3\31\3\31\3\31\3\31\3\31\3\32\3\32\3\32\3\32\3\32"+
		"\3\32\3\32\3\32\3\32\3\32\3\32\7\32\u00d6\n\32\f\32\16\32\u00d9\13\32"+
		"\3\32\3\32\3\32\3\33\3\33\3\33\3\33\3\33\3\33\3\33\7\33\u00e5\n\33\f\33"+
		"\16\33\u00e8\13\33\3\33\3\33\3\33\3\33\3\33\3\33\3\33\5\33\u00f1\n\33"+
		"\3\34\3\34\3\34\3\34\3\34\3\35\3\35\3\35\3\35\3\35\3\35\3\35\3\35\3\35"+
		"\5\35\u0101\n\35\3\36\3\36\3\36\3\36\3\37\3\37\7\37\u0109\n\37\f\37\16"+
		"\37\u010c\13\37\3\37\2\3\30 \2\4\6\b\n\f\16\20\22\24\26\30\32\34\36 \""+
		"$&(*,.\60\62\64\668:<\2\3\4\2!!##\2\u010c\2>\3\2\2\2\4@\3\2\2\2\6B\3\2"+
		"\2\2\bF\3\2\2\2\nM\3\2\2\2\fO\3\2\2\2\16W\3\2\2\2\20b\3\2\2\2\22e\3\2"+
		"\2\2\24h\3\2\2\2\26k\3\2\2\2\30m\3\2\2\2\32{\3\2\2\2\34\u0088\3\2\2\2"+
		"\36\u008f\3\2\2\2 \u0091\3\2\2\2\"\u00a1\3\2\2\2$\u00a7\3\2\2\2&\u00ad"+
		"\3\2\2\2(\u00b1\3\2\2\2*\u00b5\3\2\2\2,\u00b9\3\2\2\2.\u00bd\3\2\2\2\60"+
		"\u00c0\3\2\2\2\62\u00ca\3\2\2\2\64\u00f0\3\2\2\2\66\u00f2\3\2\2\28\u0100"+
		"\3\2\2\2:\u0102\3\2\2\2<\u010a\3\2\2\2>?\t\2\2\2?\3\3\2\2\2@A\7\"\2\2"+
		"A\5\3\2\2\2BC\7\"\2\2C\7\3\2\2\2DG\5\6\4\2EG\5\2\2\2FD\3\2\2\2FE\3\2\2"+
		"\2G\t\3\2\2\2HI\7\3\2\2IJ\7\4\2\2JK\7!\2\2KN\7\5\2\2LN\7!\2\2MH\3\2\2"+
		"\2ML\3\2\2\2N\13\3\2\2\2OT\5\6\4\2PQ\7\6\2\2QS\5\6\4\2RP\3\2\2\2SV\3\2"+
		"\2\2TR\3\2\2\2TU\3\2\2\2U\r\3\2\2\2VT\3\2\2\2WX\5\6\4\2X_\5\n\6\2YZ\7"+
		"\6\2\2Z[\5\6\4\2[\\\5\n\6\2\\^\3\2\2\2]Y\3\2\2\2^a\3\2\2\2_]\3\2\2\2_"+
		"`\3\2\2\2`\17\3\2\2\2a_\3\2\2\2bc\7\7\2\2cd\5\4\3\2d\21\3\2\2\2ef\7\b"+
		"\2\2fg\5\4\3\2g\23\3\2\2\2hi\7\t\2\2ij\5\4\3\2j\25\3\2\2\2kl\7\n\2\2l"+
		"\27\3\2\2\2mn\b\r\1\2no\5\32\16\2ox\3\2\2\2pq\f\4\2\2qr\7&\2\2rw\5\30"+
		"\r\5st\f\3\2\2tu\7%\2\2uw\5\30\r\4vp\3\2\2\2vs\3\2\2\2wz\3\2\2\2xv\3\2"+
		"\2\2xy\3\2\2\2y\31\3\2\2\2zx\3\2\2\2{~\5\b\5\2|}\7\36\2\2}\177\5\b\5\2"+
		"~|\3\2\2\2~\177\3\2\2\2\177\33\3\2\2\2\u0080\u0089\5\36\20\2\u0081\u0089"+
		"\5 \21\2\u0082\u0089\5\"\22\2\u0083\u0089\5$\23\2\u0084\u0089\5&\24\2"+
		"\u0085\u0089\5(\25\2\u0086\u0089\5*\26\2\u0087\u0089\5,\27\2\u0088\u0080"+
		"\3\2\2\2\u0088\u0081\3\2\2\2\u0088\u0082\3\2\2\2\u0088\u0083\3\2\2\2\u0088"+
		"\u0084\3\2\2\2\u0088\u0085\3\2\2\2\u0088\u0086\3\2\2\2\u0088\u0087\3\2"+
		"\2\2\u0089\35\3\2\2\2\u008a\u0090\5\4\3\2\u008b\u008c\7\4\2\2\u008c\u008d"+
		"\5\34\17\2\u008d\u008e\7\5\2\2\u008e\u0090\3\2\2\2\u008f\u008a\3\2\2\2"+
		"\u008f\u008b\3\2\2\2\u0090\37\3\2\2\2\u0091\u0092\7\13\2\2\u0092\u009b"+
		"\7\4\2\2\u0093\u0094\5\30\r\2\u0094\u0095\7\4\2\2\u0095\u0096\7%\2\2\u0096"+
		"\u0097\5\30\r\2\u0097\u0098\7\5\2\2\u0098\u009a\3\2\2\2\u0099\u0093\3"+
		"\2\2\2\u009a\u009d\3\2\2\2\u009b\u0099\3\2\2\2\u009b\u009c\3\2\2\2\u009c"+
		"\u009e\3\2\2\2\u009d\u009b\3\2\2\2\u009e\u009f\7\5\2\2\u009f\u00a0\5\36"+
		"\20\2\u00a0!\3\2\2\2\u00a1\u00a2\7\f\2\2\u00a2\u00a3\7\4\2\2\u00a3\u00a4"+
		"\5\f\7\2\u00a4\u00a5\7\5\2\2\u00a5\u00a6\5\36\20\2\u00a6#\3\2\2\2\u00a7"+
		"\u00a8\7\r\2\2\u00a8\u00a9\7\4\2\2\u00a9\u00aa\5\f\7\2\u00aa\u00ab\7\5"+
		"\2\2\u00ab\u00ac\5\36\20\2\u00ac%\3\2\2\2\u00ad\u00ae\5\36\20\2\u00ae"+
		"\u00af\7\16\2\2\u00af\u00b0\5\36\20\2\u00b0\'\3\2\2\2\u00b1\u00b2\5\36"+
		"\20\2\u00b2\u00b3\7\17\2\2\u00b3\u00b4\5\36\20\2\u00b4)\3\2\2\2\u00b5"+
		"\u00b6\5\36\20\2\u00b6\u00b7\7\20\2\2\u00b7\u00b8\5\36\20\2\u00b8+\3\2"+
		"\2\2\u00b9\u00ba\5\36\20\2\u00ba\u00bb\7\21\2\2\u00bb\u00bc\5\36\20\2"+
		"\u00bc-\3\2\2\2\u00bd\u00be\7\22\2\2\u00be\u00bf\5\36\20\2\u00bf/\3\2"+
		"\2\2\u00c0\u00c1\7\23\2\2\u00c1\u00c2\5\4\3\2\u00c2\u00c3\7\4\2\2\u00c3"+
		"\u00c4\5\16\b\2\u00c4\u00c5\7\5\2\2\u00c5\u00c6\7\24\2\2\u00c6\u00c7\7"+
		"\4\2\2\u00c7\u00c8\5\f\7\2\u00c8\u00c9\7\5\2\2\u00c9\61\3\2\2\2\u00ca"+
		"\u00cb\7\25\2\2\u00cb\u00cc\5\4\3\2\u00cc\u00cd\7\26\2\2\u00cd\u00ce\5"+
		"\6\4\2\u00ce\u00cf\7\27\2\2\u00cf\u00d7\5\2\2\2\u00d0\u00d1\7\6\2\2\u00d1"+
		"\u00d2\5\6\4\2\u00d2\u00d3\7\27\2\2\u00d3\u00d4\5\2\2\2\u00d4\u00d6\3"+
		"\2\2\2\u00d5\u00d0\3\2\2\2\u00d6\u00d9\3\2\2\2\u00d7\u00d5\3\2\2\2\u00d7"+
		"\u00d8\3\2\2\2\u00d8\u00da\3\2\2\2\u00d9\u00d7\3\2\2\2\u00da\u00db\7\30"+
		"\2\2\u00db\u00dc\5\30\r\2\u00dc\63\3\2\2\2\u00dd\u00de\7\31\2\2\u00de"+
		"\u00df\5\4\3\2\u00df\u00e0\7\32\2\2\u00e0\u00e1\7\4\2\2\u00e1\u00e6\5"+
		"\2\2\2\u00e2\u00e3\7\6\2\2\u00e3\u00e5\5\2\2\2\u00e4\u00e2\3\2\2\2\u00e5"+
		"\u00e8\3\2\2\2\u00e6\u00e4\3\2\2\2\u00e6\u00e7\3\2\2\2\u00e7\u00e9\3\2"+
		"\2\2\u00e8\u00e6\3\2\2\2\u00e9\u00ea\7\5\2\2\u00ea\u00f1\3\2\2\2\u00eb"+
		"\u00ec\7\31\2\2\u00ec\u00ed\5\4\3\2\u00ed\u00ee\7\33\2\2\u00ee\u00ef\5"+
		"\34\17\2\u00ef\u00f1\3\2\2\2\u00f0\u00dd\3\2\2\2\u00f0\u00eb\3\2\2\2\u00f1"+
		"\65\3\2\2\2\u00f2\u00f3\7\34\2\2\u00f3\u00f4\5\4\3\2\u00f4\u00f5\7\30"+
		"\2\2\u00f5\u00f6\5\30\r\2\u00f6\67\3\2\2\2\u00f7\u0101\5\20\t\2\u00f8"+
		"\u0101\5\22\n\2\u00f9\u0101\5\24\13\2\u00fa\u0101\5\26\f\2\u00fb\u0101"+
		"\5.\30\2\u00fc\u0101\5\60\31\2\u00fd\u0101\5\62\32\2\u00fe\u0101\5\64"+
		"\33\2\u00ff\u0101\5\66\34\2\u0100\u00f7\3\2\2\2\u0100\u00f8\3\2\2\2\u0100"+
		"\u00f9\3\2\2\2\u0100\u00fa\3\2\2\2\u0100\u00fb\3\2\2\2\u0100\u00fc\3\2"+
		"\2\2\u0100\u00fd\3\2\2\2\u0100\u00fe\3\2\2\2\u0100\u00ff\3\2\2\2\u0101"+
		"9\3\2\2\2\u0102\u0103\5\4\3\2\u0103\u0104\7\35\2\2\u0104\u0105\5\34\17"+
		"\2\u0105;\3\2\2\2\u0106\u0109\5:\36\2\u0107\u0109\58\35\2\u0108\u0106"+
		"\3\2\2\2\u0108\u0107\3\2\2\2\u0109\u010c\3\2\2\2\u010a\u0108\3\2\2\2\u010a"+
		"\u010b\3\2\2\2\u010b=\3\2\2\2\u010c\u010a\3\2\2\2\22FMT_vx~\u0088\u008f"+
		"\u009b\u00d7\u00e6\u00f0\u0100\u0108\u010a";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}