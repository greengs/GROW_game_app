package gnu.kawa.slib;

import gnu.expr.ModuleBody;
import gnu.expr.ModuleInfo;
import gnu.expr.ModuleMethod;
import gnu.lists.Consumer;
import gnu.lists.LList;
import gnu.lists.PairWithPosition;
import gnu.mapping.CallContext;
import gnu.mapping.Procedure;
import gnu.mapping.SimpleSymbol;
import gnu.mapping.Values;
import kawa.lang.Macro;
import kawa.lang.SyntaxPattern;
import kawa.lang.SyntaxRule;
import kawa.lang.SyntaxRules;
import kawa.standard.Scheme;

public class srfi34 extends ModuleBody {
  public static final Class $Prvt$$Lsraise$Mnobject$Mnexception$Gr;
  
  public static final Macro $Prvt$guard$Mnaux;
  
  public static final srfi34 $instance;
  
  static final SimpleSymbol Lit0;
  
  static final SimpleSymbol Lit1;
  
  static final SimpleSymbol Lit10;
  
  static final SimpleSymbol Lit11;
  
  static final SimpleSymbol Lit12;
  
  static final SimpleSymbol Lit13 = (SimpleSymbol)(new SimpleSymbol("<raise-object-exception>")).readResolve();
  
  static final SimpleSymbol Lit2;
  
  static final SyntaxRules Lit3;
  
  static final SimpleSymbol Lit4;
  
  static final SyntaxRules Lit5;
  
  static final SimpleSymbol Lit6;
  
  static final SimpleSymbol Lit7;
  
  static final SimpleSymbol Lit8;
  
  static final SimpleSymbol Lit9;
  
  public static final Macro guard;
  
  public static final ModuleMethod raise;
  
  public static final ModuleMethod with$Mnexception$Mnhandler;
  
  static {
    Lit12 = (SimpleSymbol)(new SimpleSymbol("ex")).readResolve();
    Lit11 = (SimpleSymbol)(new SimpleSymbol("begin")).readResolve();
    Lit10 = (SimpleSymbol)(new SimpleSymbol("if")).readResolve();
    Lit9 = (SimpleSymbol)(new SimpleSymbol("let")).readResolve();
    Lit8 = (SimpleSymbol)(new SimpleSymbol("temp")).readResolve();
    Lit7 = (SimpleSymbol)(new SimpleSymbol("=>")).readResolve();
    Lit6 = (SimpleSymbol)(new SimpleSymbol("else")).readResolve();
    SimpleSymbol simpleSymbol1 = (SimpleSymbol)(new SimpleSymbol("guard-aux")).readResolve();
    Lit4 = simpleSymbol1;
    SimpleSymbol simpleSymbol2 = Lit6;
    SimpleSymbol simpleSymbol3 = Lit7;
    SyntaxRule syntaxRule2 = new SyntaxRule(new SyntaxPattern("\f\030\f\007L\f\002\f\017\r\027\020\b\b\b", new Object[] { Lit6 }, 3), "\001\001\003", "\021\030\004\t\013\b\025\023", new Object[] { Lit11 }, 1);
    SyntaxRule syntaxRule3 = new SyntaxRule(new SyntaxPattern("\f\030\f\007<\f\017\f\002\f\027\b\b", new Object[] { Lit7 }, 3), "\001\001\001", "\021\030\0041\b\021\030\f\b\013\b\021\030\024\021\030\f!\t\023\030\034\b\003", new Object[] { Lit9, Lit8, Lit10, PairWithPosition.make(Lit8, LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi34.scm", 274452) }0);
    SyntaxRule syntaxRule4 = new SyntaxRule(new SyntaxPattern("\f\030\f\007<\f\017\f\002\f\027\b\f\037\r' \b\b", new Object[] { Lit7 }, 5), "\001\001\001\001\003", "\021\030\0041\b\021\030\f\b\013\b\021\030\024\021\030\f!\t\023\030\034\b\021\030$\t\003\t\033\b%#", new Object[] { Lit9, Lit8, Lit10, PairWithPosition.make(Lit8, LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi34.scm", 294932), Lit4 }1);
    SyntaxRule syntaxRule5 = new SyntaxRule(new SyntaxPattern("\f\030\f\007\034\f\017\b\b", new Object[0], 2), "\001\001", "\013", new Object[0], 0);
    SyntaxRule syntaxRule6 = new SyntaxRule(new SyntaxPattern("\f\030\f\007\034\f\017\b\f\027\r\037\030\b\b", new Object[0], 4), "\001\001\001\003", "\021\030\0041\b\021\030\f\b\013\b\021\030\024\021\030\f\021\030\f\b\021\030\034\t\003\t\023\b\035\033", new Object[] { Lit9, Lit8, Lit10, Lit4 }, 1);
    SyntaxRule syntaxRule7 = new SyntaxRule(new SyntaxPattern("\f\030\f\007L\f\017\f\027\r\037\030\b\b\b", new Object[0], 4), "\001\001\001\003", "\021\030\004\t\013A\021\030\f\t\023\b\035\033\b\003", new Object[] { Lit10, Lit11 }, 1);
    SyntaxRule syntaxRule8 = new SyntaxRule(new SyntaxPattern("\f\030\f\007L\f\017\f\027\r\037\030\b\b\f'\r/(\b\b", new Object[0], 6), "\001\001\001\003\001\003", "\021\030\004\t\013A\021\030\f\t\023\b\035\033\b\021\030\024\t\003\t#\b-+", new Object[] { Lit10, Lit11, Lit4 }, 1);
    Lit5 = new SyntaxRules(new Object[] { simpleSymbol1, simpleSymbol2, simpleSymbol3 }, new SyntaxRule[] { syntaxRule2, syntaxRule3, syntaxRule4, syntaxRule5, syntaxRule6, syntaxRule7, syntaxRule8 }, 6);
    simpleSymbol1 = (SimpleSymbol)(new SimpleSymbol("guard")).readResolve();
    Lit2 = simpleSymbol1;
    SyntaxRule syntaxRule1 = new SyntaxRule(new SyntaxPattern("\f\030\034\f\007\013\023", new Object[0], 3), "\001\000\000", "\021\030\004!\021\030\f\022\b\021\030\024\021\030\034\b\021\030$)\b\t\003\030,\b\021\0304\021\030<\n", new Object[] { (new SimpleSymbol("try-catch")).readResolve(), Lit11, Lit12, (new SimpleSymbol("<java.lang.Throwable>")).readResolve(), Lit9, PairWithPosition.make(PairWithPosition.make(Lit10, PairWithPosition.make(PairWithPosition.make((new SimpleSymbol("instance?")).readResolve(), PairWithPosition.make(Lit12, PairWithPosition.make(Lit13, LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi34.scm", 110614), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi34.scm", 110611), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi34.scm", 110600), PairWithPosition.make(PairWithPosition.make((new SimpleSymbol("field")).readResolve(), PairWithPosition.make(PairWithPosition.make((new SimpleSymbol("as")).readResolve(), PairWithPosition.make(Lit13, PairWithPosition.make(Lit12, LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi34.scm", 114732), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi34.scm", 114707), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi34.scm", 114703), PairWithPosition.make(PairWithPosition.make((new SimpleSymbol("quote")).readResolve(), PairWithPosition.make((new SimpleSymbol("value")).readResolve(), LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi34.scm", 114737), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi34.scm", 114737), LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi34.scm", 114736), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi34.scm", 114703), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi34.scm", 114696), PairWithPosition.make(Lit12, LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi34.scm", 118792), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi34.scm", 114696), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi34.scm", 110600), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi34.scm", 110596), LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi34.scm", 110596), Lit4, PairWithPosition.make((new SimpleSymbol("primitive-throw")).readResolve(), PairWithPosition.make(Lit12, LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi34.scm", 122914), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi34.scm", 122897) }0);
    Lit3 = new SyntaxRules(new Object[] { simpleSymbol1 }, new SyntaxRule[] { syntaxRule1 }, 3);
    Lit1 = (SimpleSymbol)(new SimpleSymbol("raise")).readResolve();
    Lit0 = (SimpleSymbol)(new SimpleSymbol("with-exception-handler")).readResolve();
    $instance = new srfi34();
    $Prvt$$Lsraise$Mnobject$Mnexception$Gr = nobject$Mnexception.class;
    srfi34 srfi341 = $instance;
    with$Mnexception$Mnhandler = new ModuleMethod(srfi341, 1, Lit0, 8194);
    raise = new ModuleMethod(srfi341, 2, Lit1, 4097);
    guard = Macro.make(Lit2, (Procedure)Lit3, $instance);
    $Prvt$guard$Mnaux = Macro.make(Lit4, (Procedure)Lit5, $instance);
    $instance.run();
  }
  
  public srfi34() {
    ModuleInfo.register(this);
  }
  
  public static void raise(Object paramObject) {
    throw new nobject$Mnexception(paramObject);
  }
  
  public static Object withExceptionHandler(Object paramObject1, Object paramObject2) {
    try {
      return Scheme.applyToArgs.apply1(paramObject2);
    } catch (nobject$Mnexception nobject$Mnexception) {
      return Scheme.applyToArgs.apply2(paramObject1, nobject$Mnexception.value);
    } catch (Throwable throwable) {
      return Scheme.applyToArgs.apply2(paramObject1, throwable);
    } 
  }
  
  public Object apply1(ModuleMethod paramModuleMethod, Object paramObject) {
    if (paramModuleMethod.selector == 2) {
      raise(paramObject);
      return Values.empty;
    } 
    return super.apply1(paramModuleMethod, paramObject);
  }
  
  public Object apply2(ModuleMethod paramModuleMethod, Object paramObject1, Object paramObject2) {
    return (paramModuleMethod.selector == 1) ? withExceptionHandler(paramObject1, paramObject2) : super.apply2(paramModuleMethod, paramObject1, paramObject2);
  }
  
  public int match1(ModuleMethod paramModuleMethod, Object paramObject, CallContext paramCallContext) {
    if (paramModuleMethod.selector == 2) {
      paramCallContext.value1 = paramObject;
      paramCallContext.proc = (Procedure)paramModuleMethod;
      paramCallContext.pc = 1;
      return 0;
    } 
    return super.match1(paramModuleMethod, paramObject, paramCallContext);
  }
  
  public int match2(ModuleMethod paramModuleMethod, Object paramObject1, Object paramObject2, CallContext paramCallContext) {
    if (paramModuleMethod.selector == 1) {
      paramCallContext.value1 = paramObject1;
      paramCallContext.value2 = paramObject2;
      paramCallContext.proc = (Procedure)paramModuleMethod;
      paramCallContext.pc = 2;
      return 0;
    } 
    return super.match2(paramModuleMethod, paramObject1, paramObject2, paramCallContext);
  }
  
  public final void run(CallContext paramCallContext) {
    Consumer consumer = paramCallContext.consumer;
  }
  
  public class nobject$Mnexception extends Throwable {
    public Object value;
    
    public nobject$Mnexception(srfi34 this$0) {
      this.value = this$0;
    }
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/gnu/kawa/slib/srfi34.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */