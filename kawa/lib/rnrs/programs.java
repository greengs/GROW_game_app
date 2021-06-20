package kawa.lib.rnrs;

import gnu.expr.ApplicationMainSupport;
import gnu.expr.ModuleBody;
import gnu.expr.ModuleInfo;
import gnu.expr.ModuleMethod;
import gnu.lists.Consumer;
import gnu.lists.LList;
import gnu.mapping.CallContext;
import gnu.mapping.OutPort;
import gnu.mapping.Procedure;
import gnu.mapping.SimpleSymbol;
import gnu.mapping.Values;
import gnu.mapping.WrongType;
import gnu.math.IntNum;
import kawa.lib.lists;
import kawa.lib.numbers;

public class programs extends ModuleBody {
  public static final programs $instance;
  
  static final IntNum Lit0;
  
  static final SimpleSymbol Lit1;
  
  static final SimpleSymbol Lit2 = (SimpleSymbol)(new SimpleSymbol("exit")).readResolve();
  
  public static final ModuleMethod command$Mnline;
  
  public static final ModuleMethod exit;
  
  static {
    Lit1 = (SimpleSymbol)(new SimpleSymbol("command-line")).readResolve();
    Lit0 = IntNum.make(0);
    $instance = new programs();
    programs programs1 = $instance;
    command$Mnline = new ModuleMethod(programs1, 1, Lit1, 0);
    exit = new ModuleMethod(programs1, 2, Lit2, 4096);
    $instance.run();
  }
  
  public programs() {
    ModuleInfo.register(this);
  }
  
  public static LList commandLine() {
    return (LList)lists.cons("kawa", LList.makeList((Object[])ApplicationMainSupport.commandLineArgArray, 0));
  }
  
  public static void exit() {
    exit(Lit0);
  }
  
  public static void exit(Object paramObject) {
    byte b;
    OutPort.runCleanups();
    if (numbers.isInteger(paramObject))
      try {
        b = ((Number)paramObject).intValue();
        System.exit(b);
        return;
      } catch (ClassCastException classCastException) {
        throw new WrongType(classCastException, "status", -2, paramObject);
      }  
    if (paramObject != Boolean.FALSE) {
      b = 0;
    } else {
      b = -1;
    } 
    System.exit(b);
  }
  
  public Object apply0(ModuleMethod paramModuleMethod) {
    switch (paramModuleMethod.selector) {
      default:
        return super.apply0(paramModuleMethod);
      case 1:
        return commandLine();
      case 2:
        break;
    } 
    exit();
    return Values.empty;
  }
  
  public Object apply1(ModuleMethod paramModuleMethod, Object paramObject) {
    if (paramModuleMethod.selector == 2) {
      exit(paramObject);
      return Values.empty;
    } 
    return super.apply1(paramModuleMethod, paramObject);
  }
  
  public int match0(ModuleMethod paramModuleMethod, CallContext paramCallContext) {
    switch (paramModuleMethod.selector) {
      default:
        return super.match0(paramModuleMethod, paramCallContext);
      case 2:
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 0;
        return 0;
      case 1:
        break;
    } 
    paramCallContext.proc = (Procedure)paramModuleMethod;
    paramCallContext.pc = 0;
    return 0;
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
  
  public final void run(CallContext paramCallContext) {
    Consumer consumer = paramCallContext.consumer;
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/kawa/lib/rnrs/programs.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */