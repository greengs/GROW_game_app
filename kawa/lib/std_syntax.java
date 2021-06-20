package kawa.lib;

import gnu.expr.Language;
import gnu.expr.ModuleBody;
import gnu.expr.ModuleInfo;
import gnu.expr.ModuleMethod;
import gnu.expr.QuoteExp;
import gnu.expr.Symbols;
import gnu.kawa.functions.AddOp;
import gnu.kawa.reflect.StaticFieldLocation;
import gnu.lists.Consumer;
import gnu.lists.LList;
import gnu.lists.Pair;
import gnu.lists.PairWithPosition;
import gnu.mapping.CallContext;
import gnu.mapping.Location;
import gnu.mapping.Procedure;
import gnu.mapping.SimpleSymbol;
import gnu.mapping.Values;
import gnu.mapping.WrongType;
import gnu.math.IntNum;
import kawa.lang.Eval;
import kawa.lang.Macro;
import kawa.lang.Quote;
import kawa.lang.SyntaxForm;
import kawa.lang.SyntaxForms;
import kawa.lang.SyntaxPattern;
import kawa.lang.SyntaxRule;
import kawa.lang.SyntaxRules;
import kawa.lang.SyntaxTemplate;
import kawa.lang.TemplateScope;
import kawa.lang.Translator;
import kawa.standard.Scheme;
import kawa.standard.syntax_case;

public class std_syntax extends ModuleBody {
  public static final Macro $Prvt$$Pccase;
  
  public static final Macro $Prvt$$Pccase$Mnmatch;
  
  public static final Macro $Prvt$$Pcdo$Mninit;
  
  public static final Macro $Prvt$$Pcdo$Mnlambda1;
  
  public static final Macro $Prvt$$Pcdo$Mnlambda2;
  
  public static final Macro $Prvt$$Pcdo$Mnstep;
  
  public static final Macro $Prvt$$Pclet$Mninit;
  
  public static final Macro $Prvt$$Pclet$Mnlambda1;
  
  public static final Macro $Prvt$$Pclet$Mnlambda2;
  
  public static final Location $Prvt$define;
  
  public static final Location $Prvt$define$Mnconstant;
  
  public static final Location $Prvt$if;
  
  public static final Location $Prvt$letrec;
  
  public static final std_syntax $instance;
  
  static final IntNum Lit0;
  
  static final IntNum Lit1;
  
  static final SimpleSymbol Lit10;
  
  static final SyntaxPattern Lit11;
  
  static final SyntaxPattern Lit12;
  
  static final SyntaxTemplate Lit13;
  
  static final SyntaxPattern Lit14;
  
  static final SyntaxTemplate Lit15;
  
  static final SimpleSymbol Lit16;
  
  static final SyntaxPattern Lit17;
  
  static final SyntaxPattern Lit18;
  
  static final SyntaxTemplate Lit19;
  
  static final SimpleSymbol Lit2;
  
  static final SyntaxPattern Lit20;
  
  static final SyntaxTemplate Lit21;
  
  static final SimpleSymbol Lit22;
  
  static final SyntaxRules Lit23;
  
  static final SimpleSymbol Lit24;
  
  static final SyntaxRules Lit25;
  
  static final SimpleSymbol Lit26;
  
  static final SyntaxRules Lit27;
  
  static final SimpleSymbol Lit28;
  
  static final SyntaxRules Lit29;
  
  static final SyntaxRules Lit3;
  
  static final SimpleSymbol Lit30;
  
  static final SyntaxRules Lit31;
  
  static final SimpleSymbol Lit32;
  
  static final SyntaxRules Lit33;
  
  static final SimpleSymbol Lit34;
  
  static final SyntaxRules Lit35;
  
  static final SimpleSymbol Lit36;
  
  static final SyntaxRules Lit37;
  
  static final SimpleSymbol Lit38;
  
  static final SyntaxRules Lit39;
  
  static final SimpleSymbol Lit4;
  
  static final SimpleSymbol Lit40;
  
  static final SyntaxRules Lit41;
  
  static final SimpleSymbol Lit42;
  
  static final SyntaxRules Lit43;
  
  static final SimpleSymbol Lit44;
  
  static final SyntaxRules Lit45;
  
  static final SimpleSymbol Lit46;
  
  static final SimpleSymbol Lit47;
  
  static final SimpleSymbol Lit48;
  
  static final SimpleSymbol Lit49;
  
  static final SyntaxRules Lit5;
  
  static final SimpleSymbol Lit50;
  
  static final SimpleSymbol Lit51;
  
  static final SimpleSymbol Lit52;
  
  static final SimpleSymbol Lit53;
  
  static final SimpleSymbol Lit54;
  
  static final SyntaxPattern Lit55;
  
  static final SimpleSymbol Lit56;
  
  static final SyntaxTemplate Lit57;
  
  static final SyntaxTemplate Lit58;
  
  static final SimpleSymbol Lit59;
  
  static final SimpleSymbol Lit6;
  
  static final SyntaxRules Lit60;
  
  static final SimpleSymbol Lit61;
  
  static final SyntaxRules Lit62;
  
  static final SimpleSymbol Lit63;
  
  static final SimpleSymbol Lit64;
  
  static final SimpleSymbol Lit65;
  
  static final SimpleSymbol Lit66;
  
  static final SimpleSymbol Lit67;
  
  static final SimpleSymbol Lit68;
  
  static final SimpleSymbol Lit69;
  
  static final SyntaxRules Lit7;
  
  static final SimpleSymbol Lit70;
  
  static final SimpleSymbol Lit71;
  
  static final SimpleSymbol Lit72;
  
  static final SimpleSymbol Lit73;
  
  static final SimpleSymbol Lit74;
  
  static final SimpleSymbol Lit75;
  
  static final SimpleSymbol Lit76;
  
  static final SimpleSymbol Lit77 = (SimpleSymbol)(new SimpleSymbol("temp")).readResolve();
  
  static final SimpleSymbol Lit8;
  
  static final SyntaxRules Lit9;
  
  public static final Macro and;
  
  public static final Macro begin$Mnfor$Mnsyntax;
  
  public static final Macro case;
  
  public static final Macro cond;
  
  public static final ModuleMethod datum$Mn$Grsyntax$Mnobject;
  
  public static final Macro define$Mnfor$Mnsyntax;
  
  public static final Macro define$Mnprocedure;
  
  public static final Macro delay;
  
  public static final Macro do;
  
  public static final ModuleMethod free$Mnidentifier$Eq$Qu;
  
  public static final ModuleMethod generate$Mntemporaries;
  
  public static final ModuleMethod identifier$Qu;
  
  public static final Macro let;
  
  public static final Macro let$St;
  
  public static final Macro or;
  
  public static final ModuleMethod syntax$Mncolumn;
  
  public static final ModuleMethod syntax$Mnline;
  
  public static final ModuleMethod syntax$Mnobject$Mn$Grdatum;
  
  public static final ModuleMethod syntax$Mnsource;
  
  public static final Macro with$Mnsyntax;
  
  static {
    Lit76 = (SimpleSymbol)(new SimpleSymbol("=>")).readResolve();
    Lit75 = (SimpleSymbol)(new SimpleSymbol("else")).readResolve();
    Lit74 = (SimpleSymbol)(new SimpleSymbol("eqv?")).readResolve();
    Lit73 = (SimpleSymbol)(new SimpleSymbol("x")).readResolve();
    Lit72 = (SimpleSymbol)(new SimpleSymbol("if")).readResolve();
    Lit71 = (SimpleSymbol)(new SimpleSymbol("letrec")).readResolve();
    Lit70 = (SimpleSymbol)(new SimpleSymbol("%let")).readResolve();
    Lit69 = (SimpleSymbol)(new SimpleSymbol("%syntax-error")).readResolve();
    Lit68 = (SimpleSymbol)(new SimpleSymbol("lambda")).readResolve();
    Lit67 = (SimpleSymbol)(new SimpleSymbol("make")).readResolve();
    Lit66 = (SimpleSymbol)(new SimpleSymbol("quote")).readResolve();
    Lit65 = (SimpleSymbol)(new SimpleSymbol("<gnu.expr.GenericProc>")).readResolve();
    Lit64 = (SimpleSymbol)(new SimpleSymbol("::")).readResolve();
    Lit63 = (SimpleSymbol)(new SimpleSymbol("syntax-case")).readResolve();
    SimpleSymbol simpleSymbol1 = (SimpleSymbol)(new SimpleSymbol("with-syntax")).readResolve();
    Lit61 = simpleSymbol1;
    SyntaxPattern syntaxPattern2 = new SyntaxPattern("\f\030\f\b\f\007\r\017\b\b\b", new Object[0], 2);
    SimpleSymbol simpleSymbol11 = (SimpleSymbol)(new SimpleSymbol("begin")).readResolve();
    Lit56 = simpleSymbol11;
    SyntaxRule syntaxRule8 = new SyntaxRule(syntaxPattern2, "\001\003", "\021\030\004\t\003\b\r\013", new Object[] { simpleSymbol11 }, 1);
    SyntaxRule syntaxRule11 = new SyntaxRule(new SyntaxPattern("\f\030<,\f\007\f\017\b\b\f\027\r\037\030\b\b", new Object[0], 4), "\001\001\001\003", "\021\030\004\t\013\t\020\b\t\003\b\021\030\f\t\023\b\035\033", new Object[] { Lit63, Lit56 }, 1);
    SyntaxRule syntaxRule14 = new SyntaxRule(new SyntaxPattern("\f\030L-\f\007\f\017\b\000\020\b\f\027\r\037\030\b\b", new Object[0], 4), "\003\003\001\003", "\021\030\0041\021\030\f\b\r\013\t\020\b\031\b\005\003\b\021\030\024\t\023\b\035\033", new Object[] { Lit63, (new SimpleSymbol("list")).readResolve(), Lit56 }, 1);
    Lit62 = new SyntaxRules(new Object[] { simpleSymbol1 }, new SyntaxRule[] { syntaxRule8, syntaxRule11, syntaxRule14 }, 4);
    simpleSymbol1 = (SimpleSymbol)(new SimpleSymbol("define-for-syntax")).readResolve();
    Lit59 = simpleSymbol1;
    SyntaxPattern syntaxPattern1 = new SyntaxPattern("\f\030\003", new Object[0], 1);
    SimpleSymbol simpleSymbol10 = (SimpleSymbol)(new SimpleSymbol("begin-for-syntax")).readResolve();
    Lit54 = simpleSymbol10;
    SyntaxRule syntaxRule7 = new SyntaxRule(syntaxPattern1, "\000", "\021\030\004\b\021\030\f\002", new Object[] { simpleSymbol10, (new SimpleSymbol("define")).readResolve() }, 0);
    Lit60 = new SyntaxRules(new Object[] { simpleSymbol1 }, new SyntaxRule[] { syntaxRule7 }, 1);
    Lit58 = new SyntaxTemplate("\001\000", "\030\004", new Object[] { Values.empty }, 0);
    Lit57 = new SyntaxTemplate("\001\000", "\n", new Object[0], 0);
    Lit55 = new SyntaxPattern("\f\007\013", new Object[0], 2);
    Lit53 = (SimpleSymbol)(new SimpleSymbol("syntax-column")).readResolve();
    Lit52 = (SimpleSymbol)(new SimpleSymbol("syntax-line")).readResolve();
    Lit51 = (SimpleSymbol)(new SimpleSymbol("syntax-source")).readResolve();
    Lit50 = (SimpleSymbol)(new SimpleSymbol("free-identifier=?")).readResolve();
    Lit49 = (SimpleSymbol)(new SimpleSymbol("identifier?")).readResolve();
    Lit48 = (SimpleSymbol)(new SimpleSymbol("generate-temporaries")).readResolve();
    Lit47 = (SimpleSymbol)(new SimpleSymbol("datum->syntax-object")).readResolve();
    Lit46 = (SimpleSymbol)(new SimpleSymbol("syntax-object->datum")).readResolve();
    simpleSymbol1 = (SimpleSymbol)(new SimpleSymbol("define-procedure")).readResolve();
    Lit44 = simpleSymbol1;
    SimpleSymbol simpleSymbol8 = Lit64;
    simpleSymbol10 = Lit65;
    syntaxRule14 = new SyntaxRule(new SyntaxPattern("\f\030\f\007\r\017\b\b\b", new Object[0], 2), "\001\003", "\021\030\004Á\021\030\f\t\003\021\030\024\021\030\034\b\021\030$\021\030\034\b\021\030,\b\003\b\021\0304\t\003\021\030<\b\021\030D\b\r\013", new Object[] { Lit56, (new SimpleSymbol("define-constant")).readResolve(), Lit64, Lit65, Lit67, Lit66, (new SimpleSymbol("invoke")).readResolve(), PairWithPosition.make(Lit66, PairWithPosition.make((new SimpleSymbol("setProperties")).readResolve(), LList.Empty, "/u2/home/jis/ai2-kawa/kawa/lib/std_syntax.scm", 1024020), "/u2/home/jis/ai2-kawa/kawa/lib/std_syntax.scm", 1024020), (new SimpleSymbol("java.lang.Object[]")).readResolve() }1);
    Lit45 = new SyntaxRules(new Object[] { simpleSymbol1, simpleSymbol8, simpleSymbol10 }, new SyntaxRule[] { syntaxRule14 }, 2);
    simpleSymbol1 = (SimpleSymbol)(new SimpleSymbol("delay")).readResolve();
    Lit42 = simpleSymbol1;
    SyntaxRule syntaxRule6 = new SyntaxRule(new SyntaxPattern("\f\030\f\007\b", new Object[0], 1), "\001", "\021\030\004\021\030\f\b\021\030\024\t\020\b\003", new Object[] { Lit67, (new SimpleSymbol("<kawa.lang.Promise>")).readResolve(), Lit68 }, 0);
    Lit43 = new SyntaxRules(new Object[] { simpleSymbol1 }, new SyntaxRule[] { syntaxRule6 }, 1);
    simpleSymbol1 = (SimpleSymbol)(new SimpleSymbol("do")).readResolve();
    Lit40 = simpleSymbol1;
    SimpleSymbol simpleSymbol7 = Lit64;
    SyntaxPattern syntaxPattern4 = new SyntaxPattern("\f\030,\r\007\000\b\b\034\f\017\023\r\037\030\b\b", new Object[0], 4);
    SimpleSymbol simpleSymbol13 = Lit71;
    SimpleSymbol simpleSymbol15 = (SimpleSymbol)(new SimpleSymbol("%do%loop")).readResolve();
    SimpleSymbol simpleSymbol17 = (SimpleSymbol)(new SimpleSymbol("%do-lambda1")).readResolve();
    Lit36 = simpleSymbol17;
    SimpleSymbol simpleSymbol18 = Lit72;
    SimpleSymbol simpleSymbol19 = (SimpleSymbol)(new SimpleSymbol("not")).readResolve();
    SimpleSymbol simpleSymbol20 = Lit56;
    SimpleSymbol simpleSymbol21 = (SimpleSymbol)(new SimpleSymbol("%do-step")).readResolve();
    Lit32 = simpleSymbol21;
    Values values = Values.empty;
    SimpleSymbol simpleSymbol22 = (SimpleSymbol)(new SimpleSymbol("%do-init")).readResolve();
    Lit34 = simpleSymbol22;
    SyntaxRule syntaxRule10 = new SyntaxRule(syntaxPattern4, "\003\001\000\003", "\021\030\004Ɖ\b\021\030\f\b\021\030\024\031\b\005\003\t\020\b\021\030\034)\021\030$\b\013\021\030,\021\035\033\b\021\030\f\b\005\021\0304\003\b\021\030,\021\030<\022\b\021\030\f\b\005\021\030D\b\003", new Object[] { simpleSymbol13, simpleSymbol15, simpleSymbol17, simpleSymbol18, simpleSymbol19, simpleSymbol20, simpleSymbol21, values, simpleSymbol22 }, 1);
    Lit41 = new SyntaxRules(new Object[] { simpleSymbol1, simpleSymbol7 }, new SyntaxRule[] { syntaxRule10 }, 4);
    simpleSymbol1 = (SimpleSymbol)(new SimpleSymbol("%do-lambda2")).readResolve();
    Lit38 = simpleSymbol1;
    SyntaxRule syntaxRule5 = new SyntaxRule(new SyntaxPattern("\f\030\034\f\007\013\f\027\f\037\b", new Object[0], 4), "\001\000\001\001", "\021\030\004\t\n\031\t\003\023\b\033", new Object[] { Lit38 }, 0);
    syntaxRule10 = new SyntaxRule(new SyntaxPattern("\f\030\f\b\f\007\f\017\b", new Object[0], 2), "\001\001", "\021\030\004\t\003\b\013", new Object[] { Lit68 }, 0);
    Lit39 = new SyntaxRules(new Object[] { simpleSymbol1 }, new SyntaxRule[] { syntaxRule5, syntaxRule10 }, 4);
    simpleSymbol1 = Lit36;
    SimpleSymbol simpleSymbol6 = Lit64;
    syntaxRule10 = new SyntaxRule(new SyntaxPattern("\f\030l\\\f\007\f\002\f\017\f\027\f\037\b#\f/\f7\b", new Object[] { Lit64 }, 7), "\001\001\001\001\000\001\001", "\021\030\004\t\"I9\t\003\021\030\f\b\013+\b3", new Object[] { Lit36, Lit64 }, 0);
    SyntaxRule syntaxRule13 = new SyntaxRule(new SyntaxPattern("\f\030\\L\f\007\f\002\f\017\f\027\b\033\f'\f/\b", new Object[] { Lit64 }, 6), "\001\001\001\000\001\001", "\021\030\004\t\032I9\t\003\021\030\f\b\013#\b+", new Object[] { Lit36, Lit64 }, 0);
    SyntaxRule syntaxRule16 = new SyntaxRule(new SyntaxPattern("\f\030L<\f\007\f\017\f\027\b\033\f'\f/\b", new Object[0], 6), "\001\001\001\000\001\001", "\021\030\004\t\032\031\t\003#\b+", new Object[] { Lit36 }, 0);
    SyntaxRule syntaxRule18 = new SyntaxRule(new SyntaxPattern("\f\030<,\f\007\f\017\b\023\f\037\f'\b", new Object[0], 5), "\001\001\000\001\001", "\021\030\004\t\022\031\t\003\033\b#", new Object[] { Lit36 }, 0);
    SyntaxRule syntaxRule19 = new SyntaxRule(new SyntaxPattern("\f\030\f\b\f\007\f\017\b", new Object[0], 2), "\001\001", "\021\030\004\t\003\t\020\b\013", new Object[] { Lit38 }, 0);
    Lit37 = new SyntaxRules(new Object[] { simpleSymbol1, simpleSymbol6 }, new SyntaxRule[] { syntaxRule10, syntaxRule13, syntaxRule16, syntaxRule18, syntaxRule19 }, 7);
    simpleSymbol1 = Lit34;
    simpleSymbol6 = Lit64;
    syntaxRule10 = new SyntaxRule(new SyntaxPattern("\f\030\\\f\007\f\002\f\017\f\027\f\037\b\b", new Object[] { Lit64 }, 4), "\001\001\001\001", "\023", new Object[0], 0);
    syntaxRule13 = new SyntaxRule(new SyntaxPattern("\f\030L\f\007\f\002\f\017\f\027\b\b", new Object[] { Lit64 }, 3), "\001\001\001", "\023", new Object[0], 0);
    syntaxRule16 = new SyntaxRule(new SyntaxPattern("\f\030<\f\007\f\017\f\027\b\b", new Object[0], 3), "\001\001\001", "\013", new Object[0], 0);
    syntaxRule18 = new SyntaxRule(new SyntaxPattern("\f\030,\f\007\f\017\b\b", new Object[0], 2), "\001\001", "\013", new Object[0], 0);
    syntaxRule19 = new SyntaxRule(new SyntaxPattern("\f\030<\f\007\f\017\f\027\b\b", new Object[0], 3), "\001\001\001", "\023", new Object[0], 0);
    SyntaxRule syntaxRule20 = new SyntaxRule(new SyntaxPattern("\f\030\034\f\007\b\b", new Object[0], 1), "\001", "\030\004", new Object[] { PairWithPosition.make(Lit69, PairWithPosition.make("do binding with no value", LList.Empty, "/u2/home/jis/ai2-kawa/kawa/lib/std_syntax.scm", 794643), "/u2/home/jis/ai2-kawa/kawa/lib/std_syntax.scm", 794628) }0);
    SyntaxRule syntaxRule21 = new SyntaxRule(new SyntaxPattern("\f\030L\f\007\f\017\f\027\f\037\b\b", new Object[0], 4), "\001\001\001\001", "\030\004", new Object[] { PairWithPosition.make(Lit69, PairWithPosition.make("do binding must have syntax: (var [:: type] init [step])", LList.Empty, "/u2/home/jis/ai2-kawa/kawa/lib/std_syntax.scm", 806917), "/u2/home/jis/ai2-kawa/kawa/lib/std_syntax.scm", 802820) }0);
    Lit35 = new SyntaxRules(new Object[] { simpleSymbol1, simpleSymbol6 }, new SyntaxRule[] { syntaxRule10, syntaxRule13, syntaxRule16, syntaxRule18, syntaxRule19, syntaxRule20, syntaxRule21 }, 4);
    simpleSymbol1 = Lit32;
    simpleSymbol6 = Lit64;
    syntaxRule10 = new SyntaxRule(new SyntaxPattern("\f\030\f\007\f\002\f\017\f\027\f\037\b", new Object[] { Lit64 }, 4), "\001\001\001\001", "\033", new Object[0], 0);
    syntaxRule13 = new SyntaxRule(new SyntaxPattern("\f\030\f\007\f\002\f\017\f\027\b", new Object[] { Lit64 }, 3), "\001\001\001", "\003", new Object[0], 0);
    syntaxRule16 = new SyntaxRule(new SyntaxPattern("\f\030\f\007\f\017\f\027\b", new Object[0], 3), "\001\001\001", "\023", new Object[0], 0);
    syntaxRule18 = new SyntaxRule(new SyntaxPattern("\f\030\f\007\f\017\b", new Object[0], 2), "\001\001", "\003", new Object[0], 0);
    Lit33 = new SyntaxRules(new Object[] { simpleSymbol1, simpleSymbol6 }, new SyntaxRule[] { syntaxRule10, syntaxRule13, syntaxRule16, syntaxRule18 }, 4);
    simpleSymbol1 = (SimpleSymbol)(new SimpleSymbol("let*")).readResolve();
    Lit30 = simpleSymbol1;
    SyntaxRule syntaxRule4 = new SyntaxRule(new SyntaxPattern("\f\030\f\b\003", new Object[0], 1), "\000", "\021\030\004\t\020\002", new Object[] { Lit70 }, 0);
    syntaxRule10 = new SyntaxRule(new SyntaxPattern("\f\030\034\f\007\b\013", new Object[0], 2), "\001\000", "\021\030\004\021\b\003\n", new Object[] { Lit70 }, 0);
    syntaxRule13 = new SyntaxRule(new SyntaxPattern("\f\030\034\f\007\013\023", new Object[0], 3), "\001\000\000", "\021\030\004\021\b\003\b\021\030\f\t\n\022", new Object[] { Lit70, Lit30 }, 0);
    syntaxRule16 = new SyntaxRule(new SyntaxPattern("\f\030\f\007\013", new Object[0], 2), "\001\000", "\030\004", new Object[] { PairWithPosition.make(Lit69, PairWithPosition.make("invalid bindings list in let*", LList.Empty, "/u2/home/jis/ai2-kawa/kawa/lib/std_syntax.scm", 679943), "/u2/home/jis/ai2-kawa/kawa/lib/std_syntax.scm", 675846) }0);
    syntaxRule18 = new SyntaxRule(new SyntaxPattern("\f\030\003", new Object[0], 1), "\000", "\030\004", new Object[] { PairWithPosition.make(Lit69, PairWithPosition.make("missing bindings list in let*", LList.Empty, "/u2/home/jis/ai2-kawa/kawa/lib/std_syntax.scm", 692231), "/u2/home/jis/ai2-kawa/kawa/lib/std_syntax.scm", 688134) }0);
    Lit31 = new SyntaxRules(new Object[] { simpleSymbol1 }, new SyntaxRule[] { syntaxRule4, syntaxRule10, syntaxRule13, syntaxRule16, syntaxRule18 }, 3);
    simpleSymbol1 = (SimpleSymbol)(new SimpleSymbol("let")).readResolve();
    Lit28 = simpleSymbol1;
    syntaxRule4 = new SyntaxRule(new SyntaxPattern("\f\030,\r\007\000\b\b\013", new Object[0], 2), "\003\000", "\021\030\004\031\b\005\003\n", new Object[] { Lit70 }, 1);
    SyntaxPattern syntaxPattern3 = new SyntaxPattern("\f\030\f\007,\r\017\b\b\b\023", new Object[0], 3);
    SimpleSymbol simpleSymbol12 = Lit71;
    SimpleSymbol simpleSymbol14 = (SimpleSymbol)(new SimpleSymbol("%let-lambda1")).readResolve();
    Lit22 = simpleSymbol14;
    SimpleSymbol simpleSymbol16 = (SimpleSymbol)(new SimpleSymbol("%let-init")).readResolve();
    Lit26 = simpleSymbol16;
    SyntaxRule syntaxRule9 = new SyntaxRule(syntaxPattern3, "\001\003\000", "©\021\030\004y\b\t\003\b\021\030\f\031\b\r\013\t\020\b\022\b\003\b\r\021\030\024\b\013", new Object[] { simpleSymbol12, simpleSymbol14, simpleSymbol16 }, 1);
    Lit29 = new SyntaxRules(new Object[] { simpleSymbol1 }, new SyntaxRule[] { syntaxRule4, syntaxRule9 }, 3);
    simpleSymbol1 = Lit26;
    SimpleSymbol simpleSymbol5 = Lit64;
    syntaxRule9 = new SyntaxRule(new SyntaxPattern("\f\030,\f\007\f\017\b\b", new Object[0], 2), "\001\001", "\013", new Object[0], 0);
    SyntaxRule syntaxRule12 = new SyntaxRule(new SyntaxPattern("\f\030L\f\007\f\002\f\017\f\027\b\b", new Object[] { Lit64 }, 3), "\001\001\001", "\023", new Object[0], 0);
    SyntaxRule syntaxRule15 = new SyntaxRule(new SyntaxPattern("\f\030<\f\007\f\017\f\027\b\b", new Object[0], 3), "\001\001\001", "\023", new Object[0], 0);
    SyntaxRule syntaxRule17 = new SyntaxRule(new SyntaxPattern("\f\030\034\f\007\b\b", new Object[0], 1), "\001", "\030\004", new Object[] { PairWithPosition.make(Lit69, PairWithPosition.make("let binding with no value", LList.Empty, "/u2/home/jis/ai2-kawa/kawa/lib/std_syntax.scm", 552979), "/u2/home/jis/ai2-kawa/kawa/lib/std_syntax.scm", 552964) }0);
    syntaxRule19 = new SyntaxRule(new SyntaxPattern("\f\030L\f\007\f\017\f\027\f\037\b\b", new Object[0], 4), "\001\001\001\001", "\030\004", new Object[] { PairWithPosition.make(Lit69, PairWithPosition.make("let binding must have syntax: (var [type] init)", LList.Empty, "/u2/home/jis/ai2-kawa/kawa/lib/std_syntax.scm", 565253), "/u2/home/jis/ai2-kawa/kawa/lib/std_syntax.scm", 561156) }0);
    Lit27 = new SyntaxRules(new Object[] { simpleSymbol1, simpleSymbol5 }, new SyntaxRule[] { syntaxRule9, syntaxRule12, syntaxRule15, syntaxRule17, syntaxRule19 }, 4);
    simpleSymbol1 = (SimpleSymbol)(new SimpleSymbol("%let-lambda2")).readResolve();
    Lit24 = simpleSymbol1;
    SyntaxRule syntaxRule3 = new SyntaxRule(new SyntaxPattern("\f\030\034\f\007\013\f\027\f\037\b", new Object[0], 4), "\001\000\001\001", "\021\030\004\t\n\031\t\003\023\b\033", new Object[] { Lit24 }, 0);
    syntaxRule9 = new SyntaxRule(new SyntaxPattern("\f\030\f\b\f\007\f\017\b", new Object[0], 2), "\001\001", "\021\030\004\t\003\013", new Object[] { Lit68 }, 0);
    Lit25 = new SyntaxRules(new Object[] { simpleSymbol1 }, new SyntaxRule[] { syntaxRule3, syntaxRule9 }, 4);
    simpleSymbol1 = Lit22;
    SimpleSymbol simpleSymbol4 = Lit64;
    syntaxRule9 = new SyntaxRule(new SyntaxPattern("\f\030L<\f\007\f\017\f\027\b\033\f'\f/\b", new Object[0], 6), "\001\001\001\000\001\001", "\021\030\004\t\0321!\t\003\b\013#\b+", new Object[] { Lit22 }, 0);
    syntaxRule12 = new SyntaxRule(new SyntaxPattern("\f\030\\L\f\007\f\002\f\017\f\027\b\033\f'\f/\b", new Object[] { Lit64 }, 6), "\001\001\001\000\001\001", "\021\030\004\t\0321!\t\003\b\013#\b+", new Object[] { Lit22 }, 0);
    syntaxRule15 = new SyntaxRule(new SyntaxPattern("\f\030<,\f\007\f\017\b\023\f\037\f'\b", new Object[0], 5), "\001\001\000\001\001", "\021\030\004\t\022\031\t\003\033\b#", new Object[] { Lit22 }, 0);
    syntaxRule17 = new SyntaxRule(new SyntaxPattern("\f\030\f\b\f\007\f\017\b", new Object[0], 2), "\001\001", "\021\030\004\t\003\t\020\b\013", new Object[] { Lit24 }, 0);
    Lit23 = new SyntaxRules(new Object[] { simpleSymbol1, simpleSymbol4 }, new SyntaxRule[] { syntaxRule9, syntaxRule12, syntaxRule15, syntaxRule17 }, 6);
    Lit21 = new SyntaxTemplate("\001\001\003", "\021\030\0041\b\021\030\f\b\013\b\021\030\024\021\030\f\021\030\f\b\t\003\b\025\023", new Object[] { Lit70, Lit73, Lit72 }, 1);
    Lit20 = new SyntaxPattern("\f\007\f\017\r\027\020\b\b", new Object[0], 3);
    Lit19 = new SyntaxTemplate("\001\001", "\013", new Object[0], 0);
    Lit18 = new SyntaxPattern("\f\007\f\017\b", new Object[0], 2);
    Lit17 = new SyntaxPattern("\f\007\b", new Object[0], 1);
    Lit16 = (SimpleSymbol)(new SimpleSymbol("or")).readResolve();
    Lit15 = new SyntaxTemplate("\001\001\003", "\021\030\0041\b\021\030\f\b\013\b\021\030\024\021\030\f)\t\003\b\025\023\030\034", new Object[] { Lit70, Lit73, Lit72, PairWithPosition.make(Lit73, LList.Empty, "/u2/home/jis/ai2-kawa/kawa/lib/std_syntax.scm", 385052) }1);
    Lit14 = new SyntaxPattern("\f\007\f\017\r\027\020\b\b", new Object[0], 3);
    Lit13 = new SyntaxTemplate("\001\001", "\013", new Object[0], 0);
    Lit12 = new SyntaxPattern("\f\007\f\017\b", new Object[0], 2);
    Lit11 = new SyntaxPattern("\f\007\b", new Object[0], 1);
    Lit10 = (SimpleSymbol)(new SimpleSymbol("and")).readResolve();
    simpleSymbol1 = (SimpleSymbol)(new SimpleSymbol("%case-match")).readResolve();
    Lit8 = simpleSymbol1;
    SyntaxRule syntaxRule2 = new SyntaxRule(new SyntaxPattern("\f\030\f\007\f\017\b", new Object[0], 2), "\001\001", "\021\030\004\t\003\b\021\030\f\b\013", new Object[] { Lit74, Lit66 }, 0);
    syntaxRule9 = new SyntaxRule(new SyntaxPattern("\f\030\f\007\f\017\r\027\020\b\b", new Object[0], 3), "\001\001\003", "\021\030\004Y\021\030\f\t\003\b\021\030\024\b\013\b\021\030\034\t\003\b\025\023", new Object[] { Lit16, Lit74, Lit66, Lit8 }, 1);
    Lit9 = new SyntaxRules(new Object[] { simpleSymbol1 }, new SyntaxRule[] { syntaxRule2, syntaxRule9 }, 3);
    simpleSymbol1 = (SimpleSymbol)(new SimpleSymbol("%case")).readResolve();
    Lit6 = simpleSymbol1;
    SimpleSymbol simpleSymbol3 = Lit75;
    syntaxRule9 = new SyntaxRule(new SyntaxPattern("\f\030\f\007<\f\002\r\017\b\b\b\b", new Object[] { Lit75 }, 2), "\001\003", "\021\030\004\b\r\013", new Object[] { Lit56 }, 1);
    syntaxRule12 = new SyntaxRule(new SyntaxPattern("\f\030\f\007<\f\002\r\017\b\b\b\023", new Object[] { Lit75 }, 3), "\001\003\000", "\030\004", new Object[] { PairWithPosition.make(Lit69, PairWithPosition.make("junk following else (in case)", LList.Empty, "/u2/home/jis/ai2-kawa/kawa/lib/std_syntax.scm", 241674), "/u2/home/jis/ai2-kawa/kawa/lib/std_syntax.scm", 237577) }0);
    syntaxRule15 = new SyntaxRule(new SyntaxPattern("\f\030\f\007\\,\r\017\b\b\b\r\027\020\b\b\b", new Object[0], 3), "\001\003\003", "\021\030\004A\021\030\f\t\003\b\r\013\b\021\030\024\b\025\023", new Object[] { Lit72, Lit8, Lit56 }, 1);
    syntaxRule17 = new SyntaxRule(new SyntaxPattern("\f\030\f\007\\,\r\017\b\b\b\r\027\020\b\b\f\037\r' \b\b", new Object[0], 5), "\001\003\003\001\003", "\021\030\004A\021\030\f\t\003\b\r\0131\021\030\024\b\025\023\b\021\030\034\t\003\t\033\b%#", new Object[] { Lit72, Lit8, Lit56, Lit6 }, 1);
    Lit7 = new SyntaxRules(new Object[] { simpleSymbol1, simpleSymbol3 }, new SyntaxRule[] { syntaxRule9, syntaxRule12, syntaxRule15, syntaxRule17 }, 5);
    simpleSymbol1 = (SimpleSymbol)(new SimpleSymbol("case")).readResolve();
    Lit4 = simpleSymbol1;
    SyntaxRule syntaxRule1 = new SyntaxRule(new SyntaxPattern("\f\030\f\007\r\017\b\b\b", new Object[0], 2), "\001\003", "\021\030\0041\b\021\030\f\b\003\b\021\030\024\021\030\f\b\r\013", new Object[] { Lit70, (new SimpleSymbol("tmp")).readResolve(), Lit6 }, 1);
    Lit5 = new SyntaxRules(new Object[] { simpleSymbol1 }, new SyntaxRule[] { syntaxRule1 }, 2);
    simpleSymbol1 = (SimpleSymbol)(new SimpleSymbol("cond")).readResolve();
    Lit2 = simpleSymbol1;
    SimpleSymbol simpleSymbol2 = Lit75;
    SimpleSymbol simpleSymbol9 = Lit76;
    syntaxRule12 = new SyntaxRule(new SyntaxPattern("\f\030L\f\002\f\007\r\017\b\b\b\b", new Object[] { Lit75 }, 2), "\001\003", "\021\030\004\t\003\b\r\013", new Object[] { Lit56 }, 1);
    syntaxRule15 = new SyntaxRule(new SyntaxPattern("\f\030L\f\002\f\007\r\017\b\b\b\r\027\020\b\b", new Object[] { Lit75 }, 3), "\001\003\003", "\030\004", new Object[] { PairWithPosition.make(Lit69, PairWithPosition.make("else clause must be last clause of cond", LList.Empty, "/u2/home/jis/ai2-kawa/kawa/lib/std_syntax.scm", 86035), "/u2/home/jis/ai2-kawa/kawa/lib/std_syntax.scm", 86020) }0);
    syntaxRule17 = new SyntaxRule(new SyntaxPattern("\f\030<\f\007\f\002\f\017\b\b", new Object[] { Lit76 }, 2), "\001\001", "\021\030\0041\b\021\030\f\b\003\b\021\030\024\021\030\f\b\t\013\030\034", new Object[] { Lit70, Lit77, Lit72, PairWithPosition.make(Lit77, LList.Empty, "/u2/home/jis/ai2-kawa/kawa/lib/std_syntax.scm", 102423) }0);
    syntaxRule19 = new SyntaxRule(new SyntaxPattern("\f\030<\f\007\f\002\f\017\b\f\027\r\037\030\b\b", new Object[] { Lit76 }, 4), "\001\001\001\003", "\021\030\0041\b\021\030\f\b\003\b\021\030\024\021\030\f!\t\013\030\034\b\021\030$\t\023\b\035\033", new Object[] { Lit70, Lit77, Lit72, PairWithPosition.make(Lit77, LList.Empty, "/u2/home/jis/ai2-kawa/kawa/lib/std_syntax.scm", 122898), Lit2 }1);
    syntaxRule20 = new SyntaxRule(new SyntaxPattern("\f\030\034\f\007\b\b", new Object[0], 1), "\001", "\003", new Object[0], 0);
    syntaxRule21 = new SyntaxRule(new SyntaxPattern("\f\030\034\f\007\b\f\017\r\027\020\b\b", new Object[0], 3), "\001\001\003", "\021\030\004\t\003\b\021\030\f\t\013\b\025\023", new Object[] { Lit16, Lit2 }, 1);
    SyntaxRule syntaxRule22 = new SyntaxRule(new SyntaxPattern("\f\030L\f\007\f\017\r\027\020\b\b\b", new Object[0], 3), "\001\001\003", "\021\030\004\t\003\b\021\030\f\t\013\b\025\023", new Object[] { Lit72, Lit56 }, 1);
    SyntaxRule syntaxRule23 = new SyntaxRule(new SyntaxPattern("\f\030L\f\007\f\017\r\027\020\b\b\f\037\r' \b\b", new Object[0], 5), "\001\001\003\001\003", "\021\030\004\t\003A\021\030\f\t\013\b\025\023\b\021\030\024\t\033\b%#", new Object[] { Lit72, Lit56, Lit2 }, 1);
    Lit3 = new SyntaxRules(new Object[] { simpleSymbol1, simpleSymbol2, simpleSymbol9 }, new SyntaxRule[] { syntaxRule12, syntaxRule15, syntaxRule17, syntaxRule19, syntaxRule20, syntaxRule21, syntaxRule22, syntaxRule23 }, 5);
    Lit1 = IntNum.make(1);
    Lit0 = IntNum.make(0);
    $instance = new std_syntax();
    $Prvt$define = (Location)StaticFieldLocation.make("kawa.lib.prim_syntax", "define");
    $Prvt$define$Mnconstant = (Location)StaticFieldLocation.make("kawa.lib.prim_syntax", "define$Mnconstant");
    $Prvt$if = (Location)StaticFieldLocation.make("kawa.lib.prim_syntax", "if");
    $Prvt$letrec = (Location)StaticFieldLocation.make("kawa.lib.prim_syntax", "letrec");
    cond = Macro.make(Lit2, (Procedure)Lit3, $instance);
    case = Macro.make(Lit4, (Procedure)Lit5, $instance);
    $Prvt$$Pccase = Macro.make(Lit6, (Procedure)Lit7, $instance);
    $Prvt$$Pccase$Mnmatch = Macro.make(Lit8, (Procedure)Lit9, $instance);
    simpleSymbol2 = Lit10;
    std_syntax std_syntax1 = $instance;
    and = Macro.make(simpleSymbol2, (Procedure)new ModuleMethod(std_syntax1, 1, null, 4097), $instance);
    or = Macro.make(Lit16, (Procedure)new ModuleMethod(std_syntax1, 2, null, 4097), $instance);
    $Prvt$$Pclet$Mnlambda1 = Macro.make(Lit22, (Procedure)Lit23, $instance);
    $Prvt$$Pclet$Mnlambda2 = Macro.make(Lit24, (Procedure)Lit25, $instance);
    $Prvt$$Pclet$Mninit = Macro.make(Lit26, (Procedure)Lit27, $instance);
    let = Macro.make(Lit28, (Procedure)Lit29, $instance);
    let$St = Macro.make(Lit30, (Procedure)Lit31, $instance);
    $Prvt$$Pcdo$Mnstep = Macro.make(Lit32, (Procedure)Lit33, $instance);
    $Prvt$$Pcdo$Mninit = Macro.make(Lit34, (Procedure)Lit35, $instance);
    $Prvt$$Pcdo$Mnlambda1 = Macro.make(Lit36, (Procedure)Lit37, $instance);
    $Prvt$$Pcdo$Mnlambda2 = Macro.make(Lit38, (Procedure)Lit39, $instance);
    do = Macro.make(Lit40, (Procedure)Lit41, $instance);
    delay = Macro.make(Lit42, (Procedure)Lit43, $instance);
    define$Mnprocedure = Macro.make(Lit44, (Procedure)Lit45, $instance);
    syntax$Mnobject$Mn$Grdatum = new ModuleMethod(std_syntax1, 3, Lit46, 4097);
    datum$Mn$Grsyntax$Mnobject = new ModuleMethod(std_syntax1, 4, Lit47, 8194);
    generate$Mntemporaries = new ModuleMethod(std_syntax1, 5, Lit48, 4097);
    identifier$Qu = new ModuleMethod(std_syntax1, 6, Lit49, 4097);
    free$Mnidentifier$Eq$Qu = new ModuleMethod(std_syntax1, 7, Lit50, 8194);
    syntax$Mnsource = new ModuleMethod(std_syntax1, 8, Lit51, 4097);
    syntax$Mnline = new ModuleMethod(std_syntax1, 9, Lit52, 4097);
    syntax$Mncolumn = new ModuleMethod(std_syntax1, 10, Lit53, 4097);
    simpleSymbol2 = Lit54;
    ModuleMethod moduleMethod = new ModuleMethod(std_syntax1, 11, null, 4097);
    moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/kawa/lib/std_syntax.scm:298");
    begin$Mnfor$Mnsyntax = Macro.make(simpleSymbol2, (Procedure)moduleMethod, $instance);
    define$Mnfor$Mnsyntax = Macro.make(Lit59, (Procedure)Lit60, $instance);
    with$Mnsyntax = Macro.make(Lit61, (Procedure)Lit62, $instance);
    $instance.run();
  }
  
  public std_syntax() {
    ModuleInfo.register(this);
  }
  
  public static Object datum$To$SyntaxObject(Object paramObject1, Object paramObject2) {
    return SyntaxForms.makeWithTemplate(paramObject1, paramObject2);
  }
  
  public static Object generateTemporaries(Object paramObject) {
    Integer integer = Integer.valueOf(Translator.listLength(paramObject));
    LList lList = LList.Empty;
    while (true) {
      if (Scheme.numEqu.apply2(integer, Lit0) != Boolean.FALSE)
        return lList; 
      Object object = AddOp.$Mn.apply2(integer, Lit1);
      Pair pair = new Pair(datum$To$SyntaxObject(paramObject, Symbols.gentemp()), lList);
    } 
  }
  
  public static boolean isFreeIdentifier$Eq(Object paramObject1, Object paramObject2) {
    try {
      SyntaxForm syntaxForm = (SyntaxForm)paramObject1;
      try {
        paramObject1 = paramObject2;
        return SyntaxForms.freeIdentifierEquals(syntaxForm, (SyntaxForm)paramObject1);
      } catch (ClassCastException classCastException) {
        throw new WrongType(classCastException, "kawa.lang.SyntaxForms.freeIdentifierEquals(kawa.lang.SyntaxForm,kawa.lang.SyntaxForm)", 2, paramObject2);
      } 
    } catch (ClassCastException classCastException1) {
      throw new WrongType(classCastException1, "kawa.lang.SyntaxForms.freeIdentifierEquals(kawa.lang.SyntaxForm,kawa.lang.SyntaxForm)", 1, classCastException);
    } 
  }
  
  public static boolean isIdentifier(Object paramObject) {
    boolean bool = paramObject instanceof gnu.mapping.Symbol;
    if (!bool) {
      boolean bool1 = paramObject instanceof SyntaxForm;
      bool = bool1;
      if (bool1)
        try {
          SyntaxForm syntaxForm = (SyntaxForm)paramObject;
          return SyntaxForms.isIdentifier(syntaxForm);
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "kawa.lang.SyntaxForms.isIdentifier(kawa.lang.SyntaxForm)", 1, paramObject);
        }  
    } 
    return bool;
  }
  
  static Object lambda1(Object paramObject) {
    Object[] arrayOfObject = SyntaxPattern.allocVars(3, null);
    if (Lit11.match(paramObject, arrayOfObject, 0))
      return new QuoteExp(Language.getDefaultLanguage().booleanObject(true)); 
    if (Lit12.match(paramObject, arrayOfObject, 0)) {
      paramObject = TemplateScope.make();
      return Lit13.execute(arrayOfObject, (TemplateScope)paramObject);
    } 
    if (Lit14.match(paramObject, arrayOfObject, 0)) {
      paramObject = TemplateScope.make();
      return Lit15.execute(arrayOfObject, (TemplateScope)paramObject);
    } 
    return syntax_case.error("syntax-case", paramObject);
  }
  
  static Object lambda2(Object paramObject) {
    Object[] arrayOfObject = SyntaxPattern.allocVars(3, null);
    if (Lit17.match(paramObject, arrayOfObject, 0))
      return new QuoteExp(Language.getDefaultLanguage().booleanObject(false)); 
    if (Lit18.match(paramObject, arrayOfObject, 0)) {
      paramObject = TemplateScope.make();
      return Lit19.execute(arrayOfObject, (TemplateScope)paramObject);
    } 
    if (Lit20.match(paramObject, arrayOfObject, 0)) {
      paramObject = TemplateScope.make();
      return Lit21.execute(arrayOfObject, (TemplateScope)paramObject);
    } 
    return syntax_case.error("syntax-case", paramObject);
  }
  
  static Object lambda3(Object paramObject) {
    Object[] arrayOfObject = SyntaxPattern.allocVars(2, null);
    Eval eval = Eval.eval;
    SimpleSymbol simpleSymbol = Lit56;
    TemplateScope templateScope = TemplateScope.make();
    if (Lit55.match(paramObject, arrayOfObject, 0) && eval.apply1(syntaxObject$To$Datum(new Pair(simpleSymbol, Lit57.execute(arrayOfObject, templateScope)))) != Boolean.FALSE) {
      paramObject = TemplateScope.make();
      return Lit58.execute(arrayOfObject, (TemplateScope)paramObject);
    } 
    return syntax_case.error("syntax-case", paramObject);
  }
  
  public static Object syntaxColumn(Object paramObject) {
    return (paramObject instanceof SyntaxForm) ? syntaxLine(((SyntaxForm)paramObject).getDatum()) : ((paramObject instanceof PairWithPosition) ? Integer.valueOf(((PairWithPosition)paramObject).getColumnNumber() + 0) : Boolean.FALSE);
  }
  
  public static Object syntaxLine(Object paramObject) {
    return (paramObject instanceof SyntaxForm) ? syntaxLine(((SyntaxForm)paramObject).getDatum()) : ((paramObject instanceof PairWithPosition) ? Integer.valueOf(((PairWithPosition)paramObject).getLineNumber()) : Boolean.FALSE);
  }
  
  public static Object syntaxObject$To$Datum(Object paramObject) {
    return Quote.quote(paramObject);
  }
  
  public static Object syntaxSource(Object paramObject) {
    if (paramObject instanceof SyntaxForm)
      return syntaxSource(((SyntaxForm)paramObject).getDatum()); 
    if (paramObject instanceof PairWithPosition) {
      String str = ((PairWithPosition)paramObject).getFileName();
      paramObject = str;
      return (str == null) ? Boolean.FALSE : paramObject;
    } 
    return Boolean.FALSE;
  }
  
  public Object apply1(ModuleMethod paramModuleMethod, Object paramObject) {
    switch (paramModuleMethod.selector) {
      default:
        return super.apply1(paramModuleMethod, paramObject);
      case 3:
        return syntaxObject$To$Datum(paramObject);
      case 5:
        return generateTemporaries(paramObject);
      case 6:
        return isIdentifier(paramObject) ? Boolean.TRUE : Boolean.FALSE;
      case 8:
        return syntaxSource(paramObject);
      case 9:
        return syntaxLine(paramObject);
      case 10:
        return syntaxColumn(paramObject);
      case 1:
        return lambda1(paramObject);
      case 2:
        return lambda2(paramObject);
      case 11:
        break;
    } 
    return lambda3(paramObject);
  }
  
  public Object apply2(ModuleMethod paramModuleMethod, Object paramObject1, Object paramObject2) {
    switch (paramModuleMethod.selector) {
      default:
        return super.apply2(paramModuleMethod, paramObject1, paramObject2);
      case 4:
        return datum$To$SyntaxObject(paramObject1, paramObject2);
      case 7:
        break;
    } 
    return isFreeIdentifier$Eq(paramObject1, paramObject2) ? Boolean.TRUE : Boolean.FALSE;
  }
  
  public int match1(ModuleMethod paramModuleMethod, Object paramObject, CallContext paramCallContext) {
    switch (paramModuleMethod.selector) {
      default:
        return super.match1(paramModuleMethod, paramObject, paramCallContext);
      case 11:
        paramCallContext.value1 = paramObject;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 1;
        return 0;
      case 2:
        paramCallContext.value1 = paramObject;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 1;
        return 0;
      case 1:
        paramCallContext.value1 = paramObject;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 1;
        return 0;
      case 10:
        paramCallContext.value1 = paramObject;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 1;
        return 0;
      case 9:
        paramCallContext.value1 = paramObject;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 1;
        return 0;
      case 8:
        paramCallContext.value1 = paramObject;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 1;
        return 0;
      case 6:
        paramCallContext.value1 = paramObject;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 1;
        return 0;
      case 5:
        paramCallContext.value1 = paramObject;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 1;
        return 0;
      case 3:
        break;
    } 
    paramCallContext.value1 = paramObject;
    paramCallContext.proc = (Procedure)paramModuleMethod;
    paramCallContext.pc = 1;
    return 0;
  }
  
  public int match2(ModuleMethod paramModuleMethod, Object paramObject1, Object paramObject2, CallContext paramCallContext) {
    switch (paramModuleMethod.selector) {
      default:
        return super.match2(paramModuleMethod, paramObject1, paramObject2, paramCallContext);
      case 7:
        paramCallContext.value1 = paramObject1;
        paramCallContext.value2 = paramObject2;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 2;
        return 0;
      case 4:
        break;
    } 
    paramCallContext.value1 = paramObject1;
    paramCallContext.value2 = paramObject2;
    paramCallContext.proc = (Procedure)paramModuleMethod;
    paramCallContext.pc = 2;
    return 0;
  }
  
  public final void run(CallContext paramCallContext) {
    Consumer consumer = paramCallContext.consumer;
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/kawa/lib/std_syntax.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */