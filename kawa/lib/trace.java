package kawa.lib;

import gnu.expr.ModuleBody;
import gnu.expr.ModuleInfo;
import gnu.expr.ModuleMethod;
import gnu.lists.Consumer;
import gnu.lists.LList;
import gnu.lists.PairWithPosition;
import gnu.mapping.CallContext;
import gnu.mapping.Procedure;
import gnu.mapping.SimpleSymbol;
import gnu.mapping.WrongType;
import kawa.lang.Macro;
import kawa.lang.SyntaxPattern;
import kawa.lang.SyntaxRule;
import kawa.lang.SyntaxRules;

public class trace extends ModuleBody {
  public static final Macro $Pcdo$Mntrace;
  
  public static final trace $instance;
  
  static final SimpleSymbol Lit0;
  
  static final SyntaxRules Lit1;
  
  static final SimpleSymbol Lit2;
  
  static final SyntaxRules Lit3;
  
  static final SimpleSymbol Lit4;
  
  static final SyntaxRules Lit5;
  
  static final SimpleSymbol Lit6;
  
  static final SimpleSymbol Lit7 = (SimpleSymbol)(new SimpleSymbol("begin")).readResolve();
  
  public static final ModuleMethod disassemble;
  
  public static final Macro trace;
  
  public static final Macro untrace;
  
  static {
    Lit6 = (SimpleSymbol)(new SimpleSymbol("disassemble")).readResolve();
    SimpleSymbol simpleSymbol1 = (SimpleSymbol)(new SimpleSymbol("untrace")).readResolve();
    Lit4 = simpleSymbol1;
    SyntaxPattern syntaxPattern = new SyntaxPattern("\f\030\r\007\000\b\b", new Object[0], 1);
    SimpleSymbol simpleSymbol2 = Lit7;
    SimpleSymbol simpleSymbol3 = (SimpleSymbol)(new SimpleSymbol("%do-trace")).readResolve();
    Lit0 = simpleSymbol3;
    SyntaxRule syntaxRule = new SyntaxRule(syntaxPattern, "\003", "\021\030\004\b\005\021\030\f\t\003\030\024", new Object[] { simpleSymbol2, simpleSymbol3, PairWithPosition.make(Boolean.FALSE, LList.Empty, "/u2/home/jis/ai2-kawa/kawa/lib/trace.scm", 77851) }1);
    Lit5 = new SyntaxRules(new Object[] { simpleSymbol1 }, new SyntaxRule[] { syntaxRule }, 1);
    simpleSymbol1 = (SimpleSymbol)(new SimpleSymbol("trace")).readResolve();
    Lit2 = simpleSymbol1;
    syntaxRule = new SyntaxRule(new SyntaxPattern("\f\030\r\007\000\b\b", new Object[0], 1), "\003", "\021\030\004\b\005\021\030\f\t\003\030\024", new Object[] { Lit7, Lit0, PairWithPosition.make(Boolean.TRUE, LList.Empty, "/u2/home/jis/ai2-kawa/kawa/lib/trace.scm", 57371) }1);
    Lit3 = new SyntaxRules(new Object[] { simpleSymbol1 }, new SyntaxRule[] { syntaxRule }, 1);
    simpleSymbol1 = Lit0;
    syntaxRule = new SyntaxRule(new SyntaxPattern("\f\030\f\007\f\017\b", new Object[0], 2), "\001\001", "\021\030\004\t\003\b\021\030\f\021\030\024\021\030\034\t\003\b\013", new Object[] { (new SimpleSymbol("set!")).readResolve(), (new SimpleSymbol("invoke-static")).readResolve(), (new SimpleSymbol("<kawa.standard.TracedProcedure>")).readResolve(), PairWithPosition.make((new SimpleSymbol("quote")).readResolve(), PairWithPosition.make((new SimpleSymbol("doTrace")).readResolve(), LList.Empty, "/u2/home/jis/ai2-kawa/kawa/lib/trace.scm", 32806), "/u2/home/jis/ai2-kawa/kawa/lib/trace.scm", 32806) }0);
    Lit1 = new SyntaxRules(new Object[] { simpleSymbol1 }, new SyntaxRule[] { syntaxRule }, 2);
    $instance = new trace();
    $Pcdo$Mntrace = Macro.make(Lit0, (Procedure)Lit1, $instance);
    trace = Macro.make(Lit2, (Procedure)Lit3, $instance);
    untrace = Macro.make(Lit4, (Procedure)Lit5, $instance);
    disassemble = new ModuleMethod($instance, 1, Lit6, 4097);
    $instance.run();
  }
  
  public trace() {
    ModuleInfo.register(this);
  }
  
  public static Object disassemble(Procedure paramProcedure) {
    CallContext callContext = CallContext.getInstance();
    int i = callContext.startFromContext();
    try {
      return callContext.getFromContext(i);
    } finally {
      callContext.cleanupFromContext(i);
    } 
  }
  
  public Object apply1(ModuleMethod paramModuleMethod, Object paramObject) {
    if (paramModuleMethod.selector == 1)
      try {
        Procedure procedure = (Procedure)paramObject;
        return disassemble(procedure);
      } catch (ClassCastException classCastException) {
        throw new WrongType(classCastException, "disassemble", 1, paramObject);
      }  
    return super.apply1((ModuleMethod)classCastException, paramObject);
  }
  
  public int match1(ModuleMethod paramModuleMethod, Object paramObject, CallContext paramCallContext) {
    if (paramModuleMethod.selector == 1) {
      if (!(paramObject instanceof Procedure))
        return -786431; 
      paramCallContext.value1 = paramObject;
      paramCallContext.proc = (Procedure)paramModuleMethod;
      paramCallContext.pc = 1;
      return 0;
    } 
    return super.match1(paramModuleMethod, paramObject, paramCallContext);
  }
  
  public final void run(CallContext paramCallContext) {
    Consumer consumer = paramCallContext.consumer;
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/kawa/lib/trace.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */