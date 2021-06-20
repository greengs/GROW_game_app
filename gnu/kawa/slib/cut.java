package gnu.kawa.slib;

import gnu.expr.ModuleBody;
import gnu.expr.ModuleInfo;
import gnu.lists.Consumer;
import gnu.lists.LList;
import gnu.lists.PairWithPosition;
import gnu.mapping.CallContext;
import gnu.mapping.Procedure;
import gnu.mapping.SimpleSymbol;
import kawa.lang.Macro;
import kawa.lang.SyntaxPattern;
import kawa.lang.SyntaxRule;
import kawa.lang.SyntaxRules;

public class cut extends ModuleBody {
  public static final Macro $Prvt$srfi$Mn26$Mninternal$Mncut;
  
  public static final Macro $Prvt$srfi$Mn26$Mninternal$Mncute;
  
  public static final cut $instance;
  
  static final SimpleSymbol Lit0;
  
  static final SyntaxRules Lit1;
  
  static final SimpleSymbol Lit10;
  
  static final SimpleSymbol Lit11;
  
  static final SimpleSymbol Lit12;
  
  static final SimpleSymbol Lit13;
  
  static final SimpleSymbol Lit14 = (SimpleSymbol)(new SimpleSymbol("rest-slot")).readResolve();
  
  static final SimpleSymbol Lit2;
  
  static final SyntaxRules Lit3;
  
  static final SimpleSymbol Lit4;
  
  static final SyntaxRules Lit5;
  
  static final SimpleSymbol Lit6;
  
  static final SyntaxRules Lit7;
  
  static final SimpleSymbol Lit8;
  
  static final SimpleSymbol Lit9;
  
  public static final Macro cut;
  
  public static final Macro cute;
  
  static {
    Lit13 = (SimpleSymbol)(new SimpleSymbol("apply")).readResolve();
    Lit12 = (SimpleSymbol)(new SimpleSymbol("<>")).readResolve();
    Lit11 = (SimpleSymbol)(new SimpleSymbol("x")).readResolve();
    Lit10 = (SimpleSymbol)(new SimpleSymbol("lambda")).readResolve();
    Lit9 = (SimpleSymbol)(new SimpleSymbol("let")).readResolve();
    Lit8 = (SimpleSymbol)(new SimpleSymbol("<...>")).readResolve();
    SimpleSymbol simpleSymbol1 = (SimpleSymbol)(new SimpleSymbol("cute")).readResolve();
    Lit6 = simpleSymbol1;
    SyntaxPattern syntaxPattern2 = new SyntaxPattern("\f\030\003", new Object[0], 1);
    SimpleSymbol simpleSymbol3 = (SimpleSymbol)(new SimpleSymbol("srfi-26-internal-cute")).readResolve();
    Lit2 = simpleSymbol3;
    SyntaxRule syntaxRule2 = new SyntaxRule(syntaxPattern2, "\000", "\021\030\004\t\020\t\020\t\020\002", new Object[] { simpleSymbol3 }, 0);
    Lit7 = new SyntaxRules(new Object[] { simpleSymbol1 }, new SyntaxRule[] { syntaxRule2 }, 1);
    simpleSymbol1 = (SimpleSymbol)(new SimpleSymbol("cut")).readResolve();
    Lit4 = simpleSymbol1;
    SyntaxPattern syntaxPattern1 = new SyntaxPattern("\f\030\003", new Object[0], 1);
    simpleSymbol3 = (SimpleSymbol)(new SimpleSymbol("srfi-26-internal-cut")).readResolve();
    Lit0 = simpleSymbol3;
    SyntaxRule syntaxRule1 = new SyntaxRule(syntaxPattern1, "\000", "\021\030\004\t\020\t\020\002", new Object[] { simpleSymbol3 }, 0);
    Lit5 = new SyntaxRules(new Object[] { simpleSymbol1 }, new SyntaxRule[] { syntaxRule1 }, 1);
    simpleSymbol1 = Lit2;
    SimpleSymbol simpleSymbol2 = Lit12;
    simpleSymbol3 = Lit8;
    SyntaxRule syntaxRule3 = new SyntaxRule(new SyntaxPattern("\f\030,\r\007\000\b\b\f\017<\f\027\r\037\030\b\b\b", new Object[0], 4), "\003\001\001\003", "\021\030\004\t\013\b\021\030\f\031\b\005\003\b\t\023\b\035\033", new Object[] { Lit9, Lit10 }, 1);
    SyntaxRule syntaxRule4 = new SyntaxRule(new SyntaxPattern("\f\030,\r\007\000\b\b\f\017<\f\027\r\037\030\b\b\f\002\b", new Object[] { Lit8 }, 4), "\003\001\001\003", "\021\030\004\t\013\b\021\030\f)\021\005\003\030\024\b\021\030\034\t\023\021\035\033\030$", new Object[] { Lit9, Lit10, Lit11, Lit13, PairWithPosition.make(Lit11, LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/cut.scm", 356424) }1);
    SyntaxRule syntaxRule5 = new SyntaxRule(new SyntaxPattern("\f\030,\r\007\000\b\b\f\017,\r\027\020\b\b\f\002\033", new Object[] { Lit12 }, 4), "\003\001\003\000", "\021\030\004)\021\005\003\030\f\t\013)\021\025\023\030\024\032", new Object[] { Lit2, PairWithPosition.make(Lit11, LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/cut.scm", 380950), PairWithPosition.make(Lit11, LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/cut.scm", 380987) }1);
    SyntaxRule syntaxRule6 = new SyntaxRule(new SyntaxPattern("\f\030\f\007\f\017,\r\027\020\b\b\f\037#", new Object[0], 5), "\001\001\003\001\000", "\021\030\004\t\0039)\021\030\f\b\033\013)\021\025\023\030\024\"", new Object[] { Lit2, Lit11, PairWithPosition.make(Lit11, LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/cut.scm", 401465) }1);
    Lit3 = new SyntaxRules(new Object[] { simpleSymbol1, simpleSymbol2, simpleSymbol3 }, new SyntaxRule[] { syntaxRule3, syntaxRule4, syntaxRule5, syntaxRule6 }, 5);
    simpleSymbol1 = Lit0;
    simpleSymbol2 = Lit12;
    simpleSymbol3 = Lit8;
    syntaxRule3 = new SyntaxRule(new SyntaxPattern("\f\030,\r\007\000\b\b<\f\017\r\027\020\b\b\b", new Object[0], 3), "\003\001\003", "\021\030\004\031\b\005\003\b)\021\030\f\b\013\b\025\023", new Object[] { Lit10, (new SimpleSymbol("begin")).readResolve() }, 1);
    syntaxRule4 = new SyntaxRule(new SyntaxPattern("\f\030,\r\007\000\b\b<\f\017\r\027\020\b\b\f\002\b", new Object[] { Lit8 }, 3), "\003\001\003", "\021\030\004)\021\005\003\030\f\b\021\030\024\t\013\021\025\023\030\034", new Object[] { Lit10, Lit14, Lit13, PairWithPosition.make(Lit14, LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/cut.scm", 249918) }1);
    syntaxRule5 = new SyntaxRule(new SyntaxPattern("\f\030,\r\007\000\b\b,\r\017\b\b\b\f\002\023", new Object[] { Lit12 }, 3), "\003\003\000", "\021\030\004)\021\005\003\030\f)\021\r\013\030\024\022", new Object[] { Lit0, PairWithPosition.make(Lit11, LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/cut.scm", 266283), PairWithPosition.make(Lit11, LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/cut.scm", 266300) }1);
    syntaxRule6 = new SyntaxRule(new SyntaxPattern("\f\030,\r\007\000\b\b,\r\017\b\b\b\f\027\033", new Object[0], 4), "\003\003\001\000", "\021\030\004\031\b\005\003)\021\r\013\b\023\032", new Object[] { Lit0 }, 1);
    Lit1 = new SyntaxRules(new Object[] { simpleSymbol1, simpleSymbol2, simpleSymbol3 }, new SyntaxRule[] { syntaxRule3, syntaxRule4, syntaxRule5, syntaxRule6 }, 4);
    $instance = new cut();
    $Prvt$srfi$Mn26$Mninternal$Mncut = Macro.make(Lit0, (Procedure)Lit1, $instance);
    $Prvt$srfi$Mn26$Mninternal$Mncute = Macro.make(Lit2, (Procedure)Lit3, $instance);
    cut = Macro.make(Lit4, (Procedure)Lit5, $instance);
    cute = Macro.make(Lit6, (Procedure)Lit7, $instance);
    $instance.run();
  }
  
  public cut() {
    ModuleInfo.register(this);
  }
  
  public final void run(CallContext paramCallContext) {
    Consumer consumer = paramCallContext.consumer;
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/gnu/kawa/slib/cut.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */