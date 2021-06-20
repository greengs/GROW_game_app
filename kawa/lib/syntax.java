package kawa.lib;

import gnu.expr.Expression;
import gnu.expr.ModuleBody;
import gnu.expr.ModuleInfo;
import gnu.expr.ModuleMethod;
import gnu.expr.SynchronizedExp;
import gnu.expr.TryExp;
import gnu.kawa.reflect.StaticFieldLocation;
import gnu.lists.Consumer;
import gnu.lists.LList;
import gnu.lists.Pair;
import gnu.lists.PairWithPosition;
import gnu.mapping.CallContext;
import gnu.mapping.Location;
import gnu.mapping.Procedure;
import gnu.mapping.SimpleSymbol;
import gnu.math.IntNum;
import kawa.lang.Macro;
import kawa.lang.Quote;
import kawa.lang.SyntaxForms;
import kawa.lang.SyntaxPattern;
import kawa.lang.SyntaxRule;
import kawa.lang.SyntaxRules;
import kawa.lang.SyntaxTemplate;
import kawa.lang.TemplateScope;
import kawa.lang.Translator;
import kawa.standard.IfFeature;
import kawa.standard.Scheme;
import kawa.standard.syntax_case;

public class syntax extends ModuleBody {
  public static final Macro $Pccond$Mnexpand;
  
  public static final Macro $Pcimport;
  
  public static final Location $Prvt$and;
  
  public static final Location $Prvt$define$Mnconstant;
  
  public static final Location $Prvt$define$Mnsyntax;
  
  public static final Location $Prvt$if;
  
  public static final Location $Prvt$let;
  
  public static final Location $Prvt$or;
  
  public static final Location $Prvt$try$Mncatch;
  
  public static final syntax $instance;
  
  static final SyntaxPattern Lit0;
  
  static final SyntaxTemplate Lit1;
  
  static final SyntaxPattern Lit10;
  
  static final SimpleSymbol Lit100;
  
  static final SyntaxRules Lit101;
  
  static final SimpleSymbol Lit102;
  
  static final SimpleSymbol Lit103;
  
  static final SimpleSymbol Lit104;
  
  static final SimpleSymbol Lit105;
  
  static final SimpleSymbol Lit106;
  
  static final SimpleSymbol Lit107;
  
  static final SimpleSymbol Lit108;
  
  static final SimpleSymbol Lit109;
  
  static final SimpleSymbol Lit11;
  
  static final SimpleSymbol Lit110;
  
  static final SimpleSymbol Lit111;
  
  static final SimpleSymbol Lit112;
  
  static final SimpleSymbol Lit113;
  
  static final SimpleSymbol Lit114;
  
  static final SimpleSymbol Lit115;
  
  static final SimpleSymbol Lit116;
  
  static final SimpleSymbol Lit117;
  
  static final SimpleSymbol Lit118;
  
  static final SimpleSymbol Lit119;
  
  static final SyntaxRules Lit12;
  
  static final SimpleSymbol Lit120;
  
  static final SimpleSymbol Lit121;
  
  static final SimpleSymbol Lit122;
  
  static final SimpleSymbol Lit123 = (SimpleSymbol)(new SimpleSymbol("%define-macro")).readResolve();
  
  static final SimpleSymbol Lit13;
  
  static final SyntaxRules Lit14;
  
  static final SimpleSymbol Lit15;
  
  static final SyntaxRules Lit16;
  
  static final SimpleSymbol Lit17;
  
  static final SyntaxRules Lit18;
  
  static final SimpleSymbol Lit19;
  
  static final SyntaxTemplate Lit2;
  
  static final SyntaxRules Lit20;
  
  static final SimpleSymbol Lit21;
  
  static final SyntaxPattern Lit22;
  
  static final SyntaxTemplate Lit23;
  
  static final SyntaxTemplate Lit24;
  
  static final SimpleSymbol Lit25;
  
  static final SyntaxPattern Lit26;
  
  static final SyntaxTemplate Lit27;
  
  static final SyntaxTemplate Lit28;
  
  static final SimpleSymbol Lit29;
  
  static final SyntaxPattern Lit3;
  
  static final SimpleSymbol Lit30;
  
  static final SimpleSymbol Lit31;
  
  static final SimpleSymbol Lit32;
  
  static final SimpleSymbol Lit33;
  
  static final SimpleSymbol Lit34;
  
  static final SimpleSymbol Lit35;
  
  static final SimpleSymbol Lit36;
  
  static final SyntaxRules Lit37;
  
  static final SimpleSymbol Lit38;
  
  static final SyntaxPattern Lit39;
  
  static final SyntaxPattern Lit4;
  
  static final SyntaxTemplate Lit40;
  
  static final SyntaxTemplate Lit41;
  
  static final SyntaxTemplate Lit42;
  
  static final SyntaxTemplate Lit43;
  
  static final SyntaxTemplate Lit44;
  
  static final SyntaxTemplate Lit45;
  
  static final SyntaxPattern Lit46;
  
  static final SyntaxTemplate Lit47;
  
  static final SyntaxTemplate Lit48;
  
  static final SyntaxTemplate Lit49;
  
  static final SyntaxPattern Lit5;
  
  static final SyntaxTemplate Lit50;
  
  static final SyntaxTemplate Lit51;
  
  static final SyntaxTemplate Lit52;
  
  static final SyntaxPattern Lit53;
  
  static final SyntaxTemplate Lit54;
  
  static final SyntaxTemplate Lit55;
  
  static final SyntaxTemplate Lit56;
  
  static final SyntaxTemplate Lit57;
  
  static final SyntaxTemplate Lit58;
  
  static final SyntaxTemplate Lit59;
  
  static final SyntaxTemplate Lit6;
  
  static final SyntaxPattern Lit60;
  
  static final SyntaxTemplate Lit61;
  
  static final SyntaxTemplate Lit62;
  
  static final SyntaxTemplate Lit63;
  
  static final SyntaxTemplate Lit64;
  
  static final SyntaxPattern Lit65;
  
  static final SyntaxTemplate Lit66;
  
  static final SyntaxPattern Lit67;
  
  static final SyntaxTemplate Lit68;
  
  static final SyntaxTemplate Lit69;
  
  static final SyntaxTemplate Lit7;
  
  static final SyntaxTemplate Lit70;
  
  static final SyntaxTemplate Lit71;
  
  static final SyntaxPattern Lit72;
  
  static final SyntaxTemplate Lit73;
  
  static final SyntaxTemplate Lit74;
  
  static final SyntaxTemplate Lit75;
  
  static final SyntaxTemplate Lit76;
  
  static final SimpleSymbol Lit77;
  
  static final SyntaxRules Lit78;
  
  static final SimpleSymbol Lit79;
  
  static final SyntaxTemplate Lit8;
  
  static final SyntaxRules Lit80;
  
  static final SimpleSymbol Lit81;
  
  static final SyntaxPattern Lit82;
  
  static final SyntaxTemplate Lit83;
  
  static final SyntaxTemplate Lit84;
  
  static final SyntaxPattern Lit85;
  
  static final SyntaxTemplate Lit86;
  
  static final SyntaxTemplate Lit87;
  
  static final SyntaxPattern Lit88;
  
  static final SyntaxPattern Lit89;
  
  static final SyntaxPattern Lit9;
  
  static final SyntaxTemplate Lit90;
  
  static final SimpleSymbol Lit91;
  
  static final SyntaxRules Lit92;
  
  static final SimpleSymbol Lit93;
  
  static final SyntaxPattern Lit94;
  
  static final SyntaxTemplate Lit95;
  
  static final SyntaxTemplate Lit96;
  
  static final SyntaxTemplate Lit97;
  
  static final SimpleSymbol Lit98;
  
  static final SyntaxRules Lit99;
  
  public static final Macro case$Mnlambda;
  
  public static final Macro cond$Mnexpand;
  
  public static final Macro define$Mnalias$Mnparameter;
  
  public static final Macro define$Mnmacro;
  
  public static final Macro define$Mnsyntax$Mncase;
  
  public static final Macro defmacro;
  
  public static final ModuleMethod identifier$Mnlist$Qu;
  
  public static final ModuleMethod identifier$Mnpair$Mnlist$Qu;
  
  public static final Macro import;
  
  public static final ModuleMethod import$Mnhandle$Mnexcept;
  
  public static final ModuleMethod import$Mnhandle$Mnonly;
  
  public static final ModuleMethod import$Mnhandle$Mnprefix;
  
  public static final ModuleMethod import$Mnhandle$Mnrename;
  
  public static final ModuleMethod import$Mnmapper;
  
  public static final Macro let$Mnvalues;
  
  public static final Macro let$St$Mnvalues;
  
  public static final Macro receive;
  
  public static final Macro synchronized;
  
  public static final Macro try$Mnfinally;
  
  public static final Macro unless;
  
  public static final Macro when;
  
  static {
    Lit122 = (SimpleSymbol)(new SimpleSymbol("form")).readResolve();
    Lit121 = (SimpleSymbol)(new SimpleSymbol("if")).readResolve();
    Lit120 = (SimpleSymbol)(new SimpleSymbol("prefix")).readResolve();
    Lit119 = (SimpleSymbol)(new SimpleSymbol("instance")).readResolve();
    Lit118 = (SimpleSymbol)(new SimpleSymbol("kawa.standard.ImportFromLibrary")).readResolve();
    Lit117 = (SimpleSymbol)(new SimpleSymbol("x")).readResolve();
    Lit116 = (SimpleSymbol)(new SimpleSymbol("call-with-values")).readResolve();
    Lit115 = (SimpleSymbol)(new SimpleSymbol("let")).readResolve();
    Lit114 = (SimpleSymbol)(new SimpleSymbol("not")).readResolve();
    Lit113 = (SimpleSymbol)(new SimpleSymbol("or")).readResolve();
    Lit112 = (SimpleSymbol)(new SimpleSymbol("and")).readResolve();
    Lit111 = (SimpleSymbol)(new SimpleSymbol("else")).readResolve();
    Lit110 = (SimpleSymbol)(new SimpleSymbol("begin")).readResolve();
    Lit109 = (SimpleSymbol)(new SimpleSymbol("lambda")).readResolve();
    Lit108 = (SimpleSymbol)(new SimpleSymbol("quote")).readResolve();
    Lit107 = (SimpleSymbol)(new SimpleSymbol("wt")).readResolve();
    Lit106 = (SimpleSymbol)(new SimpleSymbol("as")).readResolve();
    Lit105 = (SimpleSymbol)(new SimpleSymbol("arg")).readResolve();
    Lit104 = (SimpleSymbol)(new SimpleSymbol("quasiquote")).readResolve();
    Lit103 = (SimpleSymbol)(new SimpleSymbol("gnu.mapping.LocationProc")).readResolve();
    Lit102 = (SimpleSymbol)(new SimpleSymbol("$lookup$")).readResolve();
    SimpleSymbol simpleSymbol1 = (SimpleSymbol)(new SimpleSymbol("define-alias-parameter")).readResolve();
    Lit100 = simpleSymbol1;
    SyntaxRule syntaxRule2 = new SyntaxRule(new SyntaxPattern("\f\030\f\007\f\017\f\027\b", new Object[0], 3), "\001\001\001", "\021\030\004¹\021\030\f\t\003\021\030\024\021\030\034\b\021\030$)\021\030,\b\003\b\023\b\021\0304\t\003\b\021\030<\021\030D\b\021\030L9\021\030T\t\013\030\\\b\021\030d\021\030l\b\021\030ty\b\021\030|\b\021\030\021\030d\t\003\030A\021\030\021\030\b\013\030¤", new Object[] { 
          Lit110, (new SimpleSymbol("define-constant")).readResolve(), (new SimpleSymbol("::")).readResolve(), (new SimpleSymbol("<gnu.mapping.LocationProc>")).readResolve(), PairWithPosition.make(Lit102, Pair.make(Lit103, Pair.make(Pair.make(Lit104, Pair.make((new SimpleSymbol("makeNamed")).readResolve(), LList.Empty)), LList.Empty)), "/u2/home/jis/ai2-kawa/kawa/lib/syntax.scm", 1069060), Lit108, PairWithPosition.make(Lit102, Pair.make(Lit103, Pair.make(Pair.make(Lit104, Pair.make((new SimpleSymbol("pushConverter")).readResolve(), LList.Empty)), LList.Empty)), "/u2/home/jis/ai2-kawa/kawa/lib/syntax.scm", 1073161), Lit109, PairWithPosition.make(Lit105, LList.Empty, "/u2/home/jis/ai2-kawa/kawa/lib/syntax.scm", 1081354), (new SimpleSymbol("try-catch")).readResolve(), 
          Lit106, PairWithPosition.make(Lit105, LList.Empty, "/u2/home/jis/ai2-kawa/kawa/lib/syntax.scm", 1089550), (new SimpleSymbol("ex")).readResolve(), (new SimpleSymbol("<java.lang.ClassCastException>")).readResolve(), Lit115, Lit107, PairWithPosition.make(Lit102, Pair.make((new SimpleSymbol("gnu.mapping.WrongType")).readResolve(), Pair.make(Pair.make(Lit104, Pair.make((new SimpleSymbol("make")).readResolve(), LList.Empty)), LList.Empty)), "/u2/home/jis/ai2-kawa/kawa/lib/syntax.scm", 1097748), PairWithPosition.make(PairWithPosition.make(Lit106, PairWithPosition.make((new SimpleSymbol("<int>")).readResolve(), PairWithPosition.make(IntNum.make(1), LList.Empty, "/u2/home/jis/ai2-kawa/kawa/lib/syntax.scm", 1101846), "/u2/home/jis/ai2-kawa/kawa/lib/syntax.scm", 1101840), "/u2/home/jis/ai2-kawa/kawa/lib/syntax.scm", 1101836), PairWithPosition.make(Lit105, LList.Empty, "/u2/home/jis/ai2-kawa/kawa/lib/syntax.scm", 1101849), "/u2/home/jis/ai2-kawa/kawa/lib/syntax.scm", 1101836), (new SimpleSymbol("set!")).readResolve(), PairWithPosition.make((new SimpleSymbol("field")).readResolve(), PairWithPosition.make(Lit107, PairWithPosition.make(PairWithPosition.make(Lit108, PairWithPosition.make((new SimpleSymbol("expectedType")).readResolve(), LList.Empty, "/u2/home/jis/ai2-kawa/kawa/lib/syntax.scm", 1105941), "/u2/home/jis/ai2-kawa/kawa/lib/syntax.scm", 1105941), LList.Empty, "/u2/home/jis/ai2-kawa/kawa/lib/syntax.scm", 1105940), "/u2/home/jis/ai2-kawa/kawa/lib/syntax.scm", 1105937), "/u2/home/jis/ai2-kawa/kawa/lib/syntax.scm", 1105930), 
          PairWithPosition.make(PairWithPosition.make((new SimpleSymbol("primitive-throw")).readResolve(), PairWithPosition.make(Lit107, LList.Empty, "/u2/home/jis/ai2-kawa/kawa/lib/syntax.scm", 1110037), "/u2/home/jis/ai2-kawa/kawa/lib/syntax.scm", 1110020), LList.Empty, "/u2/home/jis/ai2-kawa/kawa/lib/syntax.scm", 1110020) }0);
    Lit101 = new SyntaxRules(new Object[] { simpleSymbol1 }, new SyntaxRule[] { syntaxRule2 }, 3);
    simpleSymbol1 = (SimpleSymbol)(new SimpleSymbol("receive")).readResolve();
    Lit98 = simpleSymbol1;
    syntaxRule2 = new SyntaxRule(new SyntaxPattern("\f\030\f\007\f\017\r\027\020\b\b", new Object[0], 3), "\001\001\003", "\021\030\0049\021\030\f\t\020\b\013\b\021\030\f\t\003\b\025\023", new Object[] { Lit116, Lit109 }, 1);
    Lit99 = new SyntaxRules(new Object[] { simpleSymbol1 }, new SyntaxRule[] { syntaxRule2 }, 3);
    simpleSymbol1 = (SimpleSymbol)(new SimpleSymbol("cond-expand")).readResolve();
    Lit91 = simpleSymbol1;
    Lit97 = new SyntaxTemplate("\001\001\000\000", "\021\030\004\032", new Object[] { simpleSymbol1 }, 0);
    Lit96 = new SyntaxTemplate("\001\001\000\000", "\021\030\004\022", new Object[] { Lit110 }, 0);
    Lit95 = new SyntaxTemplate("\001\001\000\000", "\013", new Object[0], 0);
    Lit94 = new SyntaxPattern("\f\007\034\f\017\023\033", new Object[0], 4);
    Lit93 = (SimpleSymbol)(new SimpleSymbol("%cond-expand")).readResolve();
    simpleSymbol1 = Lit91;
    SimpleSymbol simpleSymbol3 = Lit112;
    SimpleSymbol simpleSymbol4 = Lit113;
    SimpleSymbol simpleSymbol5 = Lit114;
    SimpleSymbol simpleSymbol6 = Lit111;
    SyntaxRule syntaxRule6 = new SyntaxRule(new SyntaxPattern("\f\030\b", new Object[0], 0), "", "\030\004", new Object[] { PairWithPosition.make((new SimpleSymbol("%syntax-error")).readResolve(), PairWithPosition.make("Unfulfilled cond-expand", LList.Empty, "/u2/home/jis/ai2-kawa/kawa/lib/syntax.scm", 802851), "/u2/home/jis/ai2-kawa/kawa/lib/syntax.scm", 802836) }0);
    SyntaxRule syntaxRule7 = new SyntaxRule(new SyntaxPattern("\f\030<\f\002\r\007\000\b\b\b", new Object[] { Lit111 }, 1), "\003", "\021\030\004\b\005\003", new Object[] { Lit110 }, 1);
    SyntaxRule syntaxRule8 = new SyntaxRule(new SyntaxPattern("\f\030L\034\f\002\b\r\007\000\b\b\r\017\b\b\b", new Object[] { Lit112 }, 2), "\003\003", "\021\030\004\b\005\003", new Object[] { Lit110 }, 1);
    SyntaxRule syntaxRule9 = new SyntaxRule(new SyntaxPattern("\f\030|L\f\002\f\007\r\017\b\b\b\r\027\020\b\b\r\037\030\b\b", new Object[] { Lit112 }, 4), "\001\003\003\003", "\021\030\004¡\t\003\b\021\030\004Q1\021\030\f\b\r\013\b\025\023\b\035\033\b\035\033", new Object[] { Lit91, Lit112 }, 1);
    SyntaxRule syntaxRule10 = new SyntaxRule(new SyntaxPattern("\f\030L\034\f\002\b\r\007\000\b\b\r\017\b\b\b", new Object[] { Lit113 }, 2), "\003\003", "\021\030\004\b\r\013", new Object[] { Lit91 }, 1);
    SyntaxRule syntaxRule11 = new SyntaxRule(new SyntaxPattern("\f\030|L\f\002\f\007\r\017\b\b\b\r\027\020\b\b\r\037\030\b\b", new Object[] { Lit113 }, 4), "\001\003\003\003", "\021\030\004I\t\003\b\021\030\f\b\025\023\b\021\030\024\b\021\030\004Q1\021\030\034\b\r\013\b\025\023\b\035\033", new Object[] { Lit91, Lit110, Lit111, Lit113 }, 1);
    SyntaxRule syntaxRule12 = new SyntaxRule(new SyntaxPattern("\f\030\\,\f\002\f\007\b\r\017\b\b\b\r\027\020\b\b", new Object[] { Lit114 }, 3), "\001\003\003", "\021\030\004I\t\003\b\021\030\004\b\025\023\b\021\030\f\b\r\013", new Object[] { Lit91, Lit111 }, 1);
    SyntaxRule syntaxRule13 = new SyntaxRule(new SyntaxPattern("\f\030\034\f\007\013\023", new Object[0], 3), "\001\000\000", "\021\030\004\031\t\003\n\022", new Object[] { Lit93 }, 0);
    Lit92 = new SyntaxRules(new Object[] { simpleSymbol1, simpleSymbol3, simpleSymbol4, simpleSymbol5, simpleSymbol6 }, new SyntaxRule[] { syntaxRule6, syntaxRule7, syntaxRule8, syntaxRule9, syntaxRule10, syntaxRule11, syntaxRule12, syntaxRule13 }, 4);
    Lit90 = new SyntaxTemplate("\001\000\000", "\022", new Object[0], 0);
    Lit89 = new SyntaxPattern("\023", new Object[0], 3);
    Lit88 = new SyntaxPattern("\b", new Object[0], 2);
    Lit87 = new SyntaxTemplate("\001\000\001\000\000", "\"", new Object[0], 0);
    Lit86 = new SyntaxTemplate("\001\000\001\000\000", "\021\030\004\t\023\032", new Object[] { Lit109 }, 0);
    Lit85 = new SyntaxPattern("\034\f\027\033#", new Object[0], 5);
    Lit84 = new SyntaxTemplate("\001\000", "\n", new Object[0], 0);
    Lit83 = new SyntaxTemplate("\001\000", "\030\004", new Object[] { PairWithPosition.make(Lit102, Pair.make((new SimpleSymbol("gnu.expr.GenericProc")).readResolve(), Pair.make(Pair.make(Lit104, Pair.make((new SimpleSymbol("makeWithoutSorting")).readResolve(), LList.Empty)), LList.Empty)), "/u2/home/jis/ai2-kawa/kawa/lib/syntax.scm", 651273) }0);
    Lit82 = new SyntaxPattern("\f\007\013", new Object[0], 2);
    Lit81 = (SimpleSymbol)(new SimpleSymbol("case-lambda")).readResolve();
    simpleSymbol1 = (SimpleSymbol)(new SimpleSymbol("let*-values")).readResolve();
    Lit79 = simpleSymbol1;
    SyntaxRule syntaxRule1 = new SyntaxRule(new SyntaxPattern("\f\030\f\b\f\007\r\017\b\b\b", new Object[0], 2), "\001\003", "\021\030\004\t\003\b\r\013", new Object[] { Lit110 }, 1);
    SyntaxPattern syntaxPattern = new SyntaxPattern("\f\030<\f\007\r\017\b\b\b\f\027\r\037\030\b\b", new Object[0], 4);
    simpleSymbol5 = (SimpleSymbol)(new SimpleSymbol("let-values")).readResolve();
    Lit77 = simpleSymbol5;
    SyntaxRule syntaxRule3 = new SyntaxRule(syntaxPattern, "\001\003\001\003", "\021\030\004\021\b\003\b\021\030\f\031\b\r\013\t\023\b\035\033", new Object[] { simpleSymbol5, Lit79 }, 1);
    Lit80 = new SyntaxRules(new Object[] { simpleSymbol1 }, new SyntaxRule[] { syntaxRule1, syntaxRule3 }, 4);
    simpleSymbol1 = Lit77;
    syntaxRule1 = new SyntaxRule(new SyntaxPattern("\f\030,\r\007\000\b\b\f\017\r\027\020\b\b", new Object[0], 3), "\003\001\003", "\021\030\004\021\030\f\031\b\005\003\t\020\b\021\030\024\t\013\b\025\023", new Object[] { Lit77, "bind", Lit110 }, 1);
    syntaxRule3 = new SyntaxRule(new SyntaxPattern("\f\030\f\002\f\b\f\007\f\017\b", new Object[] { "bind" }, 2), "\001\001", "\021\030\004\t\003\b\013", new Object[] { Lit115 }, 0);
    SyntaxRule syntaxRule4 = new SyntaxRule(new SyntaxPattern("\f\030\f\002\\,\f\007\f\017\b\r\027\020\b\b\f\037\f'\b", new Object[] { "bind" }, 5), "\001\001\003\001\001", "\021\030\004\021\030\f\t\003\t\013\t\020\031\b\025\023\t\033\b#", new Object[] { Lit77, "mktmp" }, 1);
    SyntaxRule syntaxRule5 = new SyntaxRule(new SyntaxPattern("\f\030\f\002\f\b\f\007\f\017\f\027\f\037\f'\b", new Object[] { "mktmp" }, 5), "\001\001\001\001\001", "\021\030\0049\021\030\f\t\020\b\003\b\021\030\f\t\013\b\021\030\024\021\030\034\t\023\t\033\b#", new Object[] { Lit116, Lit109, Lit77, "bind" }, 0);
    syntaxRule6 = new SyntaxRule(new SyntaxPattern("\f\030\f\002\034\f\007\013\f\027,\r\037\030\b\b\f',\r/(\b\b\f7\b", new Object[] { "mktmp" }, 7), "\001\000\001\003\001\003\001", "\021\030\004\021\030\f\t\n\t\023)\021\035\033\030\024\t#A\021-+\b\t\003\030\034\b3", new Object[] { Lit77, "mktmp", PairWithPosition.make(Lit117, LList.Empty, "/u2/home/jis/ai2-kawa/kawa/lib/syntax.scm", 569387), PairWithPosition.make(Lit117, LList.Empty, "/u2/home/jis/ai2-kawa/kawa/lib/syntax.scm", 569414) }1);
    syntaxRule7 = new SyntaxRule(new SyntaxPattern("\f\030\f\002\f\007\f\017,\r\027\020\b\b\f\037,\r' \b\b\f/\b", new Object[] { "mktmp" }, 6), "\001\001\003\001\003\001", "\021\030\0049\021\030\f\t\020\b\013\b\021\030\f)\021\025\023\030\024\b\021\030\034\021\030$\t\033A\021%#\b\t\003\030,\b+", new Object[] { Lit116, Lit109, Lit117, Lit77, "bind", PairWithPosition.make(Lit117, LList.Empty, "/u2/home/jis/ai2-kawa/kawa/lib/syntax.scm", 593973) }1);
    Lit78 = new SyntaxRules(new Object[] { simpleSymbol1 }, new SyntaxRule[] { syntaxRule1, syntaxRule3, syntaxRule4, syntaxRule5, syntaxRule6, syntaxRule7 }, 7);
    Lit76 = new SyntaxTemplate("\001\001\001", "\020", new Object[0], 0);
    Lit75 = new SyntaxTemplate("\001\001\001", "\023", new Object[0], 0);
    Lit74 = new SyntaxTemplate("\001\001\001", "\b\013", new Object[0], 0);
    Lit73 = new SyntaxTemplate("\001\001\001", "\030\004", new Object[] { PairWithPosition.make(Lit102, Pair.make(Lit118, Pair.make(Pair.make(Lit104, Pair.make(Lit119, LList.Empty)), LList.Empty)), "/u2/home/jis/ai2-kawa/kawa/lib/syntax.scm", 466951) }0);
    Lit72 = new SyntaxPattern("\f\007\f\017\f\027\b", new Object[0], 3);
    Lit71 = new SyntaxTemplate("\001\001\001", "\020", new Object[0], 0);
    Lit70 = new SyntaxTemplate("\001\001\001", "\023", new Object[0], 0);
    Lit69 = new SyntaxTemplate("\001\001\001", "\b\013", new Object[0], 0);
    Lit68 = new SyntaxTemplate("\001\001\001", "\030\004", new Object[] { PairWithPosition.make(Lit102, Pair.make(Lit118, Pair.make(Pair.make(Lit104, Pair.make(Lit119, LList.Empty)), LList.Empty)), "/u2/home/jis/ai2-kawa/kawa/lib/syntax.scm", 458759) }0);
    Lit67 = new SyntaxPattern("\f\007,\f\002\f\017\b\f\027\b", new Object[] { (new SimpleSymbol("library")).readResolve() }, 3);
    Lit66 = new SyntaxTemplate("\001\001\000\001", "\022", new Object[0], 0);
    Lit65 = new SyntaxPattern("\f\007,\f\002\f\017\023\f\037\b", new Object[] { Lit120 }, 4);
    Lit64 = new SyntaxTemplate("\001\001\001\001", "\020", new Object[0], 0);
    Lit63 = new SyntaxTemplate("\001\001\001\001", "\033", new Object[0], 0);
    Lit62 = new SyntaxTemplate("\001\001\001\001", "\023", new Object[0], 0);
    simpleSymbol1 = (SimpleSymbol)(new SimpleSymbol("%import")).readResolve();
    Lit38 = simpleSymbol1;
    Lit61 = new SyntaxTemplate("\001\001\001\001", "\021\030\004\b\013", new Object[] { simpleSymbol1 }, 0);
    Lit60 = new SyntaxPattern("\f\007<\f\002\f\017\f\027\b\f\037\b", new Object[] { Lit120 }, 4);
    Lit59 = new SyntaxTemplate("\001\001\000\001", "\022", new Object[0], 0);
    Lit58 = new SyntaxTemplate("\001\001\000\001", "\020", new Object[0], 0);
    Lit57 = new SyntaxTemplate("\001\001\000\001", "\033", new Object[0], 0);
    Lit56 = new SyntaxTemplate("\001\001\000\001", "\022", new Object[0], 0);
    Lit55 = new SyntaxTemplate("\001\001\000\001", "\021\030\004\b\013", new Object[] { Lit38 }, 0);
    Lit54 = new SyntaxTemplate("\001\001\000\001", "\022", new Object[0], 0);
    Lit53 = new SyntaxPattern("\f\007,\f\002\f\017\023\f\037\b", new Object[] { (new SimpleSymbol("except")).readResolve() }, 4);
    Lit52 = new SyntaxTemplate("\001\001\000\001", "\022", new Object[0], 0);
    Lit51 = new SyntaxTemplate("\001\001\000\001", "\020", new Object[0], 0);
    Lit50 = new SyntaxTemplate("\001\001\000\001", "\033", new Object[0], 0);
    Lit49 = new SyntaxTemplate("\001\001\000\001", "\022", new Object[0], 0);
    Lit48 = new SyntaxTemplate("\001\001\000\001", "\021\030\004\b\013", new Object[] { Lit38 }, 0);
    Lit47 = new SyntaxTemplate("\001\001\000\001", "\022", new Object[0], 0);
    Lit46 = new SyntaxPattern("\f\007,\f\002\f\017\023\f\037\b", new Object[] { (new SimpleSymbol("only")).readResolve() }, 4);
    Lit45 = new SyntaxTemplate("\001\001\000\001", "\030\004", new Object[] { (new SimpleSymbol("rest")).readResolve() }, 0);
    Lit44 = new SyntaxTemplate("\001\001\000\001", "\020", new Object[0], 0);
    Lit43 = new SyntaxTemplate("\001\001\000\001", "\033", new Object[0], 0);
    Lit42 = new SyntaxTemplate("\001\001\000\001", "\022", new Object[0], 0);
    Lit41 = new SyntaxTemplate("\001\001\000\001", "\021\030\004\b\013", new Object[] { Lit38 }, 0);
    Lit40 = new SyntaxTemplate("\001\001\000\001", "\022", new Object[0], 0);
    Lit39 = new SyntaxPattern("\f\007,\f\002\f\017\023\f\037\b", new Object[] { (new SimpleSymbol("rename")).readResolve() }, 4);
    simpleSymbol1 = (SimpleSymbol)(new SimpleSymbol("import")).readResolve();
    Lit36 = simpleSymbol1;
    syntaxRule1 = new SyntaxRule(new SyntaxPattern("\f\030\r\007\000\b\b", new Object[0], 1), "\003", "\021\030\004\b\005\021\030\f\t\003\030\024", new Object[] { Lit110, Lit38, PairWithPosition.make(LList.Empty, LList.Empty, "/u2/home/jis/ai2-kawa/kawa/lib/syntax.scm", 376866) }1);
    Lit37 = new SyntaxRules(new Object[] { simpleSymbol1 }, new SyntaxRule[] { syntaxRule1 }, 1);
    Lit35 = (SimpleSymbol)(new SimpleSymbol("import-mapper")).readResolve();
    Lit34 = (SimpleSymbol)(new SimpleSymbol("import-handle-rename")).readResolve();
    Lit33 = (SimpleSymbol)(new SimpleSymbol("import-handle-prefix")).readResolve();
    Lit32 = (SimpleSymbol)(new SimpleSymbol("import-handle-except")).readResolve();
    Lit31 = (SimpleSymbol)(new SimpleSymbol("import-handle-only")).readResolve();
    Lit30 = (SimpleSymbol)(new SimpleSymbol("identifier-pair-list?")).readResolve();
    Lit29 = (SimpleSymbol)(new SimpleSymbol("identifier-list?")).readResolve();
    Lit28 = new SyntaxTemplate("\001\001\000", "\022", new Object[0], 0);
    Lit27 = new SyntaxTemplate("\001\001\000", "\013", new Object[0], 0);
    Lit26 = new SyntaxPattern("\f\007\f\017\023", new Object[0], 3);
    Lit25 = (SimpleSymbol)(new SimpleSymbol("synchronized")).readResolve();
    Lit24 = new SyntaxTemplate("\001\001\001", "\023", new Object[0], 0);
    Lit23 = new SyntaxTemplate("\001\001\001", "\013", new Object[0], 0);
    Lit22 = new SyntaxPattern("\f\007\f\017\f\027\b", new Object[0], 3);
    Lit21 = (SimpleSymbol)(new SimpleSymbol("try-finally")).readResolve();
    simpleSymbol1 = (SimpleSymbol)(new SimpleSymbol("when")).readResolve();
    Lit17 = simpleSymbol1;
    syntaxRule1 = new SyntaxRule(new SyntaxPattern("\f\030\f\007\r\017\b\b\b", new Object[0], 2), "\001\003", "\021\030\004)\021\030\f\b\003\b\021\030\024\b\r\013", new Object[] { Lit121, Lit114, Lit110 }, 1);
    Lit20 = new SyntaxRules(new Object[] { simpleSymbol1 }, new SyntaxRule[] { syntaxRule1 }, 2);
    Lit19 = (SimpleSymbol)(new SimpleSymbol("unless")).readResolve();
    simpleSymbol1 = Lit17;
    syntaxRule1 = new SyntaxRule(new SyntaxPattern("\f\030\f\007\r\017\b\b\b", new Object[0], 2), "\001\003", "\021\030\004\t\003\b\021\030\f\b\r\013", new Object[] { Lit121, Lit110 }, 1);
    Lit18 = new SyntaxRules(new Object[] { simpleSymbol1 }, new SyntaxRule[] { syntaxRule1 }, 2);
    simpleSymbol1 = (SimpleSymbol)(new SimpleSymbol("define-syntax-case")).readResolve();
    Lit15 = simpleSymbol1;
    syntaxRule1 = new SyntaxRule(new SyntaxPattern("\f\030\f\007\f\017\023", new Object[0], 3), "\001\001\000", "\021\030\004\t\003\b\021\030\f\021\030\024\b\021\030\034\021\030$\t\013\022", new Object[] { (new SimpleSymbol("define-syntax")).readResolve(), Lit109, PairWithPosition.make(Lit122, LList.Empty, "/u2/home/jis/ai2-kawa/kawa/lib/syntax.scm", 90129), (new SimpleSymbol("syntax-case")).readResolve(), Lit122 }0);
    Lit16 = new SyntaxRules(new Object[] { simpleSymbol1 }, new SyntaxRule[] { syntaxRule1 }, 3);
    simpleSymbol1 = (SimpleSymbol)(new SimpleSymbol("define-macro")).readResolve();
    Lit13 = simpleSymbol1;
    syntaxRule1 = new SyntaxRule(new SyntaxPattern("\f\030\034\f\007\013\023", new Object[0], 3), "\001\000\000", "\021\030\004\t\003\b\021\030\f\t\n\022", new Object[] { Lit123, Lit109 }, 0);
    syntaxRule3 = new SyntaxRule(new SyntaxPattern("\f\030\f\007\f\017\b", new Object[0], 2), "\001\001", "\021\030\004\t\003\b\013", new Object[] { Lit123 }, 0);
    Lit14 = new SyntaxRules(new Object[] { simpleSymbol1 }, new SyntaxRule[] { syntaxRule1, syntaxRule3 }, 3);
    simpleSymbol1 = (SimpleSymbol)(new SimpleSymbol("defmacro")).readResolve();
    Lit11 = simpleSymbol1;
    syntaxRule1 = new SyntaxRule(new SyntaxPattern("\f\030\f\007\f\017\023", new Object[0], 3), "\001\001\000", "\021\030\004\t\003\b\021\030\f\t\013\022", new Object[] { Lit123, Lit109 }, 0);
    Lit12 = new SyntaxRules(new Object[] { simpleSymbol1 }, new SyntaxRule[] { syntaxRule1 }, 3);
    Lit10 = new SyntaxPattern("\003", new Object[0], 1);
    Lit9 = new SyntaxPattern("\b", new Object[0], 0);
    Lit8 = new SyntaxTemplate("\001\001\000", "\022", new Object[0], 0);
    Lit7 = new SyntaxTemplate("\001\001\000", "\013", new Object[0], 0);
    Lit6 = new SyntaxTemplate("\001\001\000", "\003", new Object[0], 0);
    Lit5 = new SyntaxPattern(",\f\007\f\017\b\023", new Object[0], 3);
    Lit4 = new SyntaxPattern("\003", new Object[0], 1);
    Lit3 = new SyntaxPattern("\b", new Object[0], 0);
    Lit2 = new SyntaxTemplate("\001\000", "\n", new Object[0], 0);
    Lit1 = new SyntaxTemplate("\001\000", "\003", new Object[0], 0);
    Lit0 = new SyntaxPattern("\f\007\013", new Object[0], 2);
    $instance = new syntax();
    $Prvt$define$Mnsyntax = (Location)StaticFieldLocation.make("kawa.lib.prim_syntax", "define$Mnsyntax");
    $Prvt$define$Mnconstant = (Location)StaticFieldLocation.make("kawa.lib.prim_syntax", "define$Mnconstant");
    $Prvt$if = (Location)StaticFieldLocation.make("kawa.lib.prim_syntax", "if");
    $Prvt$try$Mncatch = (Location)StaticFieldLocation.make("kawa.lib.prim_syntax", "try$Mncatch");
    $Prvt$and = (Location)StaticFieldLocation.make("kawa.lib.std_syntax", "and");
    $Prvt$or = (Location)StaticFieldLocation.make("kawa.lib.std_syntax", "or");
    $Prvt$let = (Location)StaticFieldLocation.make("kawa.lib.std_syntax", "let");
    defmacro = Macro.make(Lit11, (Procedure)Lit12, $instance);
    define$Mnmacro = Macro.make(Lit13, (Procedure)Lit14, $instance);
    define$Mnsyntax$Mncase = Macro.make(Lit15, (Procedure)Lit16, $instance);
    when = Macro.make(Lit17, (Procedure)Lit18, $instance);
    unless = Macro.make(Lit19, (Procedure)Lit20, $instance);
    SimpleSymbol simpleSymbol2 = Lit21;
    syntax syntax1 = $instance;
    try$Mnfinally = Macro.make(simpleSymbol2, (Procedure)new ModuleMethod(syntax1, 2, null, 4097), $instance);
    synchronized = Macro.make(Lit25, (Procedure)new ModuleMethod(syntax1, 3, null, 4097), $instance);
    identifier$Mnlist$Qu = new ModuleMethod(syntax1, 4, Lit29, 4097);
    identifier$Mnpair$Mnlist$Qu = new ModuleMethod(syntax1, 5, Lit30, 4097);
    import$Mnhandle$Mnonly = new ModuleMethod(syntax1, 6, Lit31, 8194);
    import$Mnhandle$Mnexcept = new ModuleMethod(syntax1, 7, Lit32, 8194);
    import$Mnhandle$Mnprefix = new ModuleMethod(syntax1, 8, Lit33, 8194);
    import$Mnhandle$Mnrename = new ModuleMethod(syntax1, 9, Lit34, 8194);
    import$Mnmapper = new ModuleMethod(syntax1, 10, Lit35, 4097);
    import = Macro.make(Lit36, (Procedure)Lit37, $instance);
    $Pcimport = Macro.make(Lit38, (Procedure)new ModuleMethod(syntax1, 11, null, 4097), $instance);
    let$Mnvalues = Macro.make(Lit77, (Procedure)Lit78, $instance);
    let$St$Mnvalues = Macro.make(Lit79, (Procedure)Lit80, $instance);
    case$Mnlambda = Macro.make(Lit81, (Procedure)new ModuleMethod(syntax1, 12, null, 4097), $instance);
    cond$Mnexpand = Macro.make(Lit91, (Procedure)Lit92, $instance);
    simpleSymbol2 = Lit93;
    ModuleMethod moduleMethod = new ModuleMethod(syntax1, 13, null, 4097);
    moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/kawa/lib/syntax.scm:227");
    $Pccond$Mnexpand = Macro.make(simpleSymbol2, (Procedure)moduleMethod, $instance);
    receive = Macro.make(Lit98, (Procedure)Lit99, $instance);
    define$Mnalias$Mnparameter = Macro.make(Lit100, (Procedure)Lit101, $instance);
    $instance.run();
  }
  
  public syntax() {
    ModuleInfo.register(this);
  }
  
  public static Object importHandleExcept(Object paramObject1, Object paramObject2) {
    Object object = paramObject1;
    if (lists.memq(paramObject1, paramObject2) != Boolean.FALSE)
      object = null; 
    return object;
  }
  
  public static Object importHandleOnly(Object paramObject1, Object paramObject2) {
    return (lists.memq(paramObject1, paramObject2) != Boolean.FALSE) ? paramObject1 : null;
  }
  
  public static Object importHandlePrefix(Object paramObject1, Object paramObject2) {
    if (paramObject1 == null);
    return null;
  }
  
  public static Object importHandleRename(Object paramObject1, Object paramObject2) {
    return lists.isPair(paramObject2) ? ((paramObject1 == lists.caar.apply1(paramObject2)) ? lists.cadar.apply1(paramObject2) : importHandleRename(paramObject1, lists.cdr.apply1(paramObject2))) : paramObject1;
  }
  
  public static Procedure importMapper(Object paramObject) {
    frame frame = new frame();
    frame.list = paramObject;
    return (Procedure)frame.lambda$Fn1;
  }
  
  public static boolean isIdentifierList(Object paramObject) {
    boolean bool2;
    if (Translator.listLength(paramObject) >= 0) {
      bool2 = true;
    } else {
      bool2 = false;
    } 
    boolean bool1 = bool2;
    if (bool2)
      while (true) {
        Object[] arrayOfObject = SyntaxPattern.allocVars(2, null);
        if (Lit0.match(paramObject, arrayOfObject, 0)) {
          paramObject = TemplateScope.make();
          bool2 = std_syntax.isIdentifier(Lit1.execute(arrayOfObject, (TemplateScope)paramObject));
          bool1 = bool2;
          if (bool2) {
            paramObject = TemplateScope.make();
            paramObject = Lit2.execute(arrayOfObject, (TemplateScope)paramObject);
            continue;
          } 
          break;
        } 
        if (Lit3.match(paramObject, arrayOfObject, 0)) {
          bool1 = true;
          break;
        } 
        return Lit4.match(paramObject, arrayOfObject, 0) ? false : ((syntax_case.error("syntax-case", paramObject) != Boolean.FALSE));
      }  
    return bool1;
  }
  
  public static boolean isIdentifierPairList(Object paramObject) {
    boolean bool2;
    if (Translator.listLength(paramObject) >= 0) {
      bool2 = true;
    } else {
      bool2 = false;
    } 
    boolean bool1 = bool2;
    if (bool2)
      while (true) {
        Object[] arrayOfObject = SyntaxPattern.allocVars(3, null);
        if (Lit5.match(paramObject, arrayOfObject, 0)) {
          paramObject = TemplateScope.make();
          bool2 = std_syntax.isIdentifier(Lit6.execute(arrayOfObject, (TemplateScope)paramObject));
          bool1 = bool2;
          if (bool2) {
            paramObject = TemplateScope.make();
            bool2 = std_syntax.isIdentifier(Lit7.execute(arrayOfObject, (TemplateScope)paramObject));
            bool1 = bool2;
            if (bool2) {
              paramObject = TemplateScope.make();
              paramObject = Lit8.execute(arrayOfObject, (TemplateScope)paramObject);
              continue;
            } 
          } 
          break;
        } 
        if (Lit9.match(paramObject, arrayOfObject, 0)) {
          bool1 = true;
          break;
        } 
        return Lit10.match(paramObject, arrayOfObject, 0) ? false : ((syntax_case.error("syntax-case", paramObject) != Boolean.FALSE));
      }  
    return bool1;
  }
  
  static Object lambda2(Object paramObject) {
    Object[] arrayOfObject = SyntaxPattern.allocVars(3, null);
    if (Lit22.match(paramObject, arrayOfObject, 0)) {
      paramObject = TemplateScope.make();
      paramObject = SyntaxForms.rewrite(Lit23.execute(arrayOfObject, (TemplateScope)paramObject));
      TemplateScope templateScope = TemplateScope.make();
      return new TryExp((Expression)paramObject, SyntaxForms.rewrite(Lit24.execute(arrayOfObject, templateScope)));
    } 
    return syntax_case.error("syntax-case", paramObject);
  }
  
  static Object lambda3(Object paramObject) {
    Object[] arrayOfObject = SyntaxPattern.allocVars(3, null);
    if (Lit26.match(paramObject, arrayOfObject, 0)) {
      paramObject = TemplateScope.make();
      paramObject = SyntaxForms.rewrite(Lit27.execute(arrayOfObject, (TemplateScope)paramObject));
      TemplateScope templateScope = TemplateScope.make();
      return new SynchronizedExp((Expression)paramObject, SyntaxForms.rewriteBody(Lit28.execute(arrayOfObject, templateScope)));
    } 
    return syntax_case.error("syntax-case", paramObject);
  }
  
  static Object lambda4(Object paramObject) {
    Object object = SyntaxPattern.allocVars(4, null);
    if (Lit39.match(paramObject, (Object[])object, 0)) {
      paramObject = TemplateScope.make();
      if (isIdentifierPairList(Lit40.execute((Object[])object, (TemplateScope)paramObject))) {
        paramObject = TemplateScope.make();
        return Quote.append$V(new Object[] { Lit41.execute((Object[])object, (TemplateScope)paramObject), Quote.consX$V(new Object[] { lists.cons(lists.cons(import$Mnhandle$Mnrename, Lit42.execute((Object[])object, (TemplateScope)paramObject)), Lit43.execute((Object[])object, (TemplateScope)paramObject)), Lit44.execute((Object[])object, (TemplateScope)paramObject) }) });
      } 
      paramObject = TemplateScope.make();
      object = Lit45.execute((Object[])object, (TemplateScope)paramObject);
      if ("invalid 'rename' clause in import" instanceof Object[]) {
        paramObject = "invalid 'rename' clause in import";
        return prim_syntax.syntaxError(object, (Object[])paramObject);
      } 
      paramObject = new Object[] { "invalid 'rename' clause in import" };
      return prim_syntax.syntaxError(object, (Object[])paramObject);
    } 
    if (Lit46.match(paramObject, (Object[])object, 0)) {
      paramObject = TemplateScope.make();
      if (isIdentifierList(Lit47.execute((Object[])object, (TemplateScope)paramObject))) {
        paramObject = TemplateScope.make();
        return Quote.append$V(new Object[] { Lit48.execute((Object[])object, (TemplateScope)paramObject), Quote.consX$V(new Object[] { lists.cons(lists.cons(import$Mnhandle$Mnonly, Lit49.execute((Object[])object, (TemplateScope)paramObject)), Lit50.execute((Object[])object, (TemplateScope)paramObject)), Lit51.execute((Object[])object, (TemplateScope)paramObject) }) });
      } 
      paramObject = TemplateScope.make();
      object = Lit52.execute((Object[])object, (TemplateScope)paramObject);
      if ("invalid 'only' identifier list" instanceof Object[]) {
        paramObject = "invalid 'only' identifier list";
        return prim_syntax.syntaxError(object, (Object[])paramObject);
      } 
      paramObject = new Object[] { "invalid 'only' identifier list" };
      return prim_syntax.syntaxError(object, (Object[])paramObject);
    } 
    if (Lit53.match(paramObject, (Object[])object, 0)) {
      paramObject = TemplateScope.make();
      if (isIdentifierList(Lit54.execute((Object[])object, (TemplateScope)paramObject))) {
        paramObject = TemplateScope.make();
        return Quote.append$V(new Object[] { Lit55.execute((Object[])object, (TemplateScope)paramObject), Quote.consX$V(new Object[] { lists.cons(lists.cons(import$Mnhandle$Mnexcept, Lit56.execute((Object[])object, (TemplateScope)paramObject)), Lit57.execute((Object[])object, (TemplateScope)paramObject)), Lit58.execute((Object[])object, (TemplateScope)paramObject) }) });
      } 
      paramObject = TemplateScope.make();
      object = Lit59.execute((Object[])object, (TemplateScope)paramObject);
      if ("invalid 'except' identifier list" instanceof Object[]) {
        paramObject = "invalid 'except' identifier list";
        return prim_syntax.syntaxError(object, (Object[])paramObject);
      } 
      paramObject = new Object[] { "invalid 'except' identifier list" };
      return prim_syntax.syntaxError(object, (Object[])paramObject);
    } 
    if (Lit60.match(paramObject, (Object[])object, 0)) {
      paramObject = TemplateScope.make();
      return Quote.append$V(new Object[] { Lit61.execute((Object[])object, (TemplateScope)paramObject), Quote.consX$V(new Object[] { lists.cons(lists.cons(import$Mnhandle$Mnprefix, Lit62.execute((Object[])object, (TemplateScope)paramObject)), Lit63.execute((Object[])object, (TemplateScope)paramObject)), Lit64.execute((Object[])object, (TemplateScope)paramObject) }) });
    } 
    if (Lit65.match(paramObject, (Object[])object, 0)) {
      paramObject = TemplateScope.make();
      object = Lit66.execute((Object[])object, (TemplateScope)paramObject);
      if ("invalid prefix clause in import" instanceof Object[]) {
        paramObject = "invalid prefix clause in import";
        return prim_syntax.syntaxError(object, (Object[])paramObject);
      } 
      paramObject = new Object[] { "invalid prefix clause in import" };
      return prim_syntax.syntaxError(object, (Object[])paramObject);
    } 
    if (Lit67.match(paramObject, (Object[])object, 0)) {
      paramObject = TemplateScope.make();
      return Pair.make(Lit68.execute((Object[])object, (TemplateScope)paramObject), Quote.append$V(new Object[] { Lit69.execute((Object[])object, (TemplateScope)paramObject), Quote.consX$V(new Object[] { importMapper(std_syntax.syntaxObject$To$Datum(Lit70.execute((Object[])object, (TemplateScope)paramObject))), Lit71.execute((Object[])object, (TemplateScope)paramObject) }) }));
    } 
    if (Lit72.match(paramObject, (Object[])object, 0)) {
      paramObject = TemplateScope.make();
      return Pair.make(Lit73.execute((Object[])object, (TemplateScope)paramObject), Quote.append$V(new Object[] { Lit74.execute((Object[])object, (TemplateScope)paramObject), Quote.consX$V(new Object[] { importMapper(std_syntax.syntaxObject$To$Datum(Lit75.execute((Object[])object, (TemplateScope)paramObject))), Lit76.execute((Object[])object, (TemplateScope)paramObject) }) }));
    } 
    return syntax_case.error("syntax-case", paramObject);
  }
  
  static Object lambda5(Object paramObject) {
    frame0 frame0 = new frame0();
    frame0.$unnamed$1 = SyntaxPattern.allocVars(2, null);
    if (Lit82.match(paramObject, frame0.$unnamed$1, 0)) {
      frame0.$unnamed$0 = TemplateScope.make();
      return Pair.make(Lit83.execute(frame0.$unnamed$1, frame0.$unnamed$0), frame0.lambda6loop(Lit84.execute(frame0.$unnamed$1, frame0.$unnamed$0)));
    } 
    return syntax_case.error("syntax-case", paramObject);
  }
  
  static Object lambda7(Object paramObject) {
    Object[] arrayOfObject = SyntaxPattern.allocVars(4, null);
    if (Lit94.match(paramObject, arrayOfObject, 0)) {
      paramObject = TemplateScope.make();
      if (IfFeature.testFeature(Lit95.execute(arrayOfObject, (TemplateScope)paramObject))) {
        paramObject = TemplateScope.make();
        return Lit96.execute(arrayOfObject, (TemplateScope)paramObject);
      } 
      paramObject = TemplateScope.make();
      return Lit97.execute(arrayOfObject, (TemplateScope)paramObject);
    } 
    return syntax_case.error("syntax-case", paramObject);
  }
  
  public Object apply1(ModuleMethod paramModuleMethod, Object paramObject) {
    switch (paramModuleMethod.selector) {
      default:
        return super.apply1(paramModuleMethod, paramObject);
      case 4:
        return isIdentifierList(paramObject) ? Boolean.TRUE : Boolean.FALSE;
      case 5:
        return isIdentifierPairList(paramObject) ? Boolean.TRUE : Boolean.FALSE;
      case 10:
        return importMapper(paramObject);
      case 2:
        return lambda2(paramObject);
      case 3:
        return lambda3(paramObject);
      case 11:
        return lambda4(paramObject);
      case 12:
        return lambda5(paramObject);
      case 13:
        break;
    } 
    return lambda7(paramObject);
  }
  
  public Object apply2(ModuleMethod paramModuleMethod, Object paramObject1, Object paramObject2) {
    switch (paramModuleMethod.selector) {
      default:
        return super.apply2(paramModuleMethod, paramObject1, paramObject2);
      case 6:
        return importHandleOnly(paramObject1, paramObject2);
      case 7:
        return importHandleExcept(paramObject1, paramObject2);
      case 8:
        return importHandlePrefix(paramObject1, paramObject2);
      case 9:
        break;
    } 
    return importHandleRename(paramObject1, paramObject2);
  }
  
  public int match1(ModuleMethod paramModuleMethod, Object paramObject, CallContext paramCallContext) {
    switch (paramModuleMethod.selector) {
      default:
        return super.match1(paramModuleMethod, paramObject, paramCallContext);
      case 13:
        paramCallContext.value1 = paramObject;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 1;
        return 0;
      case 12:
        paramCallContext.value1 = paramObject;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 1;
        return 0;
      case 11:
        paramCallContext.value1 = paramObject;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 1;
        return 0;
      case 3:
        paramCallContext.value1 = paramObject;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 1;
        return 0;
      case 2:
        paramCallContext.value1 = paramObject;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 1;
        return 0;
      case 10:
        paramCallContext.value1 = paramObject;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 1;
        return 0;
      case 5:
        paramCallContext.value1 = paramObject;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 1;
        return 0;
      case 4:
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
      case 9:
        paramCallContext.value1 = paramObject1;
        paramCallContext.value2 = paramObject2;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 2;
        return 0;
      case 8:
        paramCallContext.value1 = paramObject1;
        paramCallContext.value2 = paramObject2;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 2;
        return 0;
      case 7:
        paramCallContext.value1 = paramObject1;
        paramCallContext.value2 = paramObject2;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 2;
        return 0;
      case 6:
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
  
  public class frame extends ModuleBody {
    final ModuleMethod lambda$Fn1;
    
    Object list;
    
    public frame() {
      ModuleMethod moduleMethod = new ModuleMethod(this, 1, null, 4097);
      moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/kawa/lib/syntax.scm:83");
      this.lambda$Fn1 = moduleMethod;
    }
    
    public Object apply1(ModuleMethod param1ModuleMethod, Object param1Object) {
      return (param1ModuleMethod.selector == 1) ? lambda1(param1Object) : super.apply1(param1ModuleMethod, param1Object);
    }
    
    Object lambda1(Object param1Object) {
      Object object2 = this.list;
      Object object1 = param1Object;
      for (param1Object = object2;; param1Object = object2) {
        boolean bool;
        if (object1 == null) {
          bool = true;
        } else {
          bool = false;
        } 
        if (bool ? bool : lists.isNull(param1Object))
          return object1; 
        object2 = lists.cdr.apply1(param1Object);
        object1 = Scheme.applyToArgs.apply3(lists.caar.apply1(param1Object), object1, lists.cdar.apply1(param1Object));
      } 
    }
    
    public int match1(ModuleMethod param1ModuleMethod, Object param1Object, CallContext param1CallContext) {
      if (param1ModuleMethod.selector == 1) {
        param1CallContext.value1 = param1Object;
        param1CallContext.proc = (Procedure)param1ModuleMethod;
        param1CallContext.pc = 1;
        return 0;
      } 
      return super.match1(param1ModuleMethod, param1Object, param1CallContext);
    }
  }
  
  public class frame0 extends ModuleBody {
    TemplateScope $unnamed$0;
    
    Object[] $unnamed$1;
    
    public Object lambda6loop(Object param1Object) {
      Object[] arrayOfObject = SyntaxPattern.allocVars(5, this.$unnamed$1);
      if (syntax.Lit85.match(param1Object, arrayOfObject, 0))
        return Pair.make(syntax.Lit86.execute(arrayOfObject, this.$unnamed$0), lambda6loop(syntax.Lit87.execute(arrayOfObject, this.$unnamed$0))); 
      if (syntax.Lit88.match(param1Object, arrayOfObject, 0))
        return LList.Empty; 
      if (syntax.Lit89.match(param1Object, arrayOfObject, 0)) {
        Object object = syntax.Lit90.execute(arrayOfObject, this.$unnamed$0);
        if ("invalid case-lambda clause" instanceof Object[]) {
          param1Object = "invalid case-lambda clause";
          return LList.list1(prim_syntax.syntaxError(object, (Object[])param1Object));
        } 
        param1Object = new Object[] { "invalid case-lambda clause" };
        return LList.list1(prim_syntax.syntaxError(object, (Object[])param1Object));
      } 
      return syntax_case.error("syntax-case", param1Object);
    }
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/kawa/lib/syntax.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */