package kawa.lib;

import gnu.expr.Language;
import gnu.expr.ModuleBody;
import gnu.expr.ModuleInfo;
import gnu.expr.ModuleMethod;
import gnu.lists.Consumer;
import gnu.mapping.CallContext;
import gnu.mapping.Environment;
import gnu.mapping.Procedure;
import gnu.mapping.SimpleSymbol;
import gnu.mapping.Values;
import gnu.mapping.WrongType;
import kawa.GuiConsole;
import kawa.standard.Scheme;

public class windows extends ModuleBody {
  public static final windows $instance;
  
  static final SimpleSymbol Lit0 = (SimpleSymbol)(new SimpleSymbol("scheme-window")).readResolve();
  
  public static final ModuleMethod scheme$Mnwindow;
  
  static {
    $instance = new windows();
    scheme$Mnwindow = new ModuleMethod($instance, 1, Lit0, 4096);
    $instance.run();
  }
  
  public windows() {
    ModuleInfo.register(this);
  }
  
  public static void schemeWindow() {
    schemeWindow(Boolean.FALSE);
  }
  
  public static void schemeWindow(Object paramObject) {
    Environment environment;
    Scheme scheme = Scheme.getInstance();
    if (paramObject != Boolean.FALSE) {
      environment = misc.interactionEnvironment();
    } else {
      environment = scheme.getNewEnvironment();
    } 
    try {
      boolean bool;
      Boolean bool1 = Boolean.FALSE;
      if (paramObject != bool1) {
        bool = true;
      } else {
        bool = false;
      } 
      new GuiConsole((Language)scheme, environment, bool);
      return;
    } catch (ClassCastException classCastException) {
      throw new WrongType(classCastException, "kawa.GuiConsole.<init>(gnu.expr.Language,gnu.mapping.Environment,boolean)", 3, paramObject);
    } 
  }
  
  public Object apply0(ModuleMethod paramModuleMethod) {
    if (paramModuleMethod.selector == 1) {
      schemeWindow();
      return Values.empty;
    } 
    return super.apply0(paramModuleMethod);
  }
  
  public Object apply1(ModuleMethod paramModuleMethod, Object paramObject) {
    if (paramModuleMethod.selector == 1) {
      schemeWindow(paramObject);
      return Values.empty;
    } 
    return super.apply1(paramModuleMethod, paramObject);
  }
  
  public int match0(ModuleMethod paramModuleMethod, CallContext paramCallContext) {
    if (paramModuleMethod.selector == 1) {
      paramCallContext.proc = (Procedure)paramModuleMethod;
      paramCallContext.pc = 0;
      return 0;
    } 
    return super.match0(paramModuleMethod, paramCallContext);
  }
  
  public int match1(ModuleMethod paramModuleMethod, Object paramObject, CallContext paramCallContext) {
    if (paramModuleMethod.selector == 1) {
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


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/kawa/lib/windows.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */