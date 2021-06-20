package kawa.lib;

import gnu.expr.ModuleBody;
import gnu.expr.ModuleInfo;
import gnu.expr.ModuleMethod;
import gnu.lists.Consumer;
import gnu.mapping.CallContext;
import gnu.mapping.Future;
import gnu.mapping.Procedure;
import gnu.mapping.RunnableClosure;
import gnu.mapping.SimpleSymbol;
import gnu.mapping.Values;
import gnu.mapping.WrongType;
import gnu.math.Quantity;
import kawa.lang.Macro;
import kawa.lang.SyntaxPattern;
import kawa.lang.SyntaxRule;
import kawa.lang.SyntaxRules;
import kawa.standard.sleep;

public class thread extends ModuleBody {
  public static final ModuleMethod $Prvt$$Pcmake$Mnfuture;
  
  public static final thread $instance;
  
  static final SimpleSymbol Lit0;
  
  static final SimpleSymbol Lit1;
  
  static final SyntaxRules Lit2;
  
  static final SimpleSymbol Lit3;
  
  static final SimpleSymbol Lit4 = (SimpleSymbol)(new SimpleSymbol("runnable")).readResolve();
  
  public static final Macro future;
  
  public static final ModuleMethod runnable;
  
  public static final ModuleMethod sleep;
  
  public static Future $PcMakeFuture(Procedure paramProcedure) {
    Future future = new Future(paramProcedure);
    future.start();
    return future;
  }
  
  static {
    Lit3 = (SimpleSymbol)(new SimpleSymbol("%make-future")).readResolve();
    SimpleSymbol simpleSymbol = (SimpleSymbol)(new SimpleSymbol("future")).readResolve();
    Lit1 = simpleSymbol;
    SyntaxRule syntaxRule = new SyntaxRule(new SyntaxPattern("\f\030\f\007\b", new Object[0], 1), "\001", "\021\030\004\b\021\030\f\t\020\b\003", new Object[] { Lit3, (new SimpleSymbol("lambda")).readResolve() }, 0);
    Lit2 = new SyntaxRules(new Object[] { simpleSymbol }, new SyntaxRule[] { syntaxRule }, 1);
    Lit0 = (SimpleSymbol)(new SimpleSymbol("sleep")).readResolve();
    $instance = new thread();
    thread thread1 = $instance;
    sleep = new ModuleMethod(thread1, 1, Lit0, 4097);
    future = Macro.make(Lit1, (Procedure)Lit2, $instance);
    $Prvt$$Pcmake$Mnfuture = new ModuleMethod(thread1, 2, Lit3, 4097);
    runnable = new ModuleMethod(thread1, 3, Lit4, 4097);
    $instance.run();
  }
  
  public thread() {
    ModuleInfo.register(this);
  }
  
  public static RunnableClosure runnable(Procedure paramProcedure) {
    return new RunnableClosure(paramProcedure);
  }
  
  public static void sleep(Quantity paramQuantity) {
    sleep.sleep(paramQuantity);
  }
  
  public Object apply1(ModuleMethod paramModuleMethod, Object paramObject) {
    switch (paramModuleMethod.selector) {
      default:
        return super.apply1(paramModuleMethod, paramObject);
      case 1:
        try {
          Quantity quantity = (Quantity)paramObject;
          sleep(quantity);
          return Values.empty;
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "sleep", 1, paramObject);
        } 
      case 2:
        try {
          Procedure procedure = (Procedure)paramObject;
          return $PcMakeFuture(procedure);
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "%make-future", 1, paramObject);
        } 
      case 3:
        break;
    } 
    try {
      Procedure procedure = (Procedure)paramObject;
      return runnable(procedure);
    } catch (ClassCastException classCastException) {
      throw new WrongType(classCastException, "runnable", 1, paramObject);
    } 
  }
  
  public int match1(ModuleMethod paramModuleMethod, Object paramObject, CallContext paramCallContext) {
    null = -786431;
    switch (paramModuleMethod.selector) {
      default:
        return super.match1(paramModuleMethod, paramObject, paramCallContext);
      case 3:
        if (paramObject instanceof Procedure) {
          paramCallContext.value1 = paramObject;
          paramCallContext.proc = (Procedure)paramModuleMethod;
          paramCallContext.pc = 1;
          return 0;
        } 
        return SYNTHETIC_LOCAL_VARIABLE_4;
      case 2:
        if (paramObject instanceof Procedure) {
          paramCallContext.value1 = paramObject;
          paramCallContext.proc = (Procedure)paramModuleMethod;
          paramCallContext.pc = 1;
          return 0;
        } 
        return SYNTHETIC_LOCAL_VARIABLE_4;
      case 1:
        break;
    } 
    if (paramObject instanceof Quantity) {
      paramCallContext.value1 = paramObject;
      paramCallContext.proc = (Procedure)paramModuleMethod;
      paramCallContext.pc = 1;
      return 0;
    } 
    return SYNTHETIC_LOCAL_VARIABLE_4;
  }
  
  public final void run(CallContext paramCallContext) {
    Consumer consumer = paramCallContext.consumer;
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/kawa/lib/thread.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */