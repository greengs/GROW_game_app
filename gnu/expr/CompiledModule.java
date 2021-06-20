package gnu.expr;

import gnu.lists.AbstractFormat;
import gnu.lists.Consumer;
import gnu.lists.VoidConsumer;
import gnu.mapping.CallContext;
import gnu.mapping.Environment;
import gnu.mapping.OutPort;
import java.io.Writer;
import kawa.Shell;

public class CompiledModule {
  Object cookie;
  
  Language language;
  
  ModuleExp mexp;
  
  public CompiledModule(ModuleExp paramModuleExp, Object paramObject, Language paramLanguage) {
    this.mexp = paramModuleExp;
    this.cookie = paramObject;
    this.language = paramLanguage;
  }
  
  public static CompiledModule make(Class paramClass, Language paramLanguage) {
    return new CompiledModule(null, paramClass, paramLanguage);
  }
  
  public void evalModule(Environment paramEnvironment, CallContext paramCallContext) throws Throwable {
    Language language = Language.setSaveCurrent(this.language);
    Environment environment = Environment.setSaveCurrent(paramEnvironment);
    try {
      ModuleExp.evalModule2(paramEnvironment, paramCallContext, this.language, this.mexp, this.cookie);
      return;
    } finally {
      Language.restoreCurrent(language);
      Environment.restoreCurrent(environment);
    } 
  }
  
  public void evalModule(Environment paramEnvironment, OutPort paramOutPort) throws Throwable {
    VoidConsumer voidConsumer;
    CallContext callContext = CallContext.getInstance();
    Consumer consumer = callContext.consumer;
    boolean bool = ModuleBody.getMainPrintValues();
    AbstractFormat abstractFormat = paramOutPort.objectFormat;
    if (bool) {
      Consumer consumer1 = Shell.getOutputConsumer(paramOutPort);
    } else {
      voidConsumer = new VoidConsumer();
    } 
    callContext.consumer = (Consumer)voidConsumer;
    try {
      evalModule(paramEnvironment, callContext);
      return;
    } finally {
      if (callContext.consumer instanceof Writer)
        ((Writer)callContext.consumer).flush(); 
      callContext.consumer = consumer;
      paramOutPort.objectFormat = abstractFormat;
    } 
  }
  
  public Object evalToResultValue(Environment paramEnvironment, CallContext paramCallContext) throws Throwable {
    int i = paramCallContext.startFromContext();
    try {
      evalModule(paramEnvironment, paramCallContext);
      return paramCallContext.getFromContext(i);
    } catch (Throwable throwable) {
      paramCallContext.cleanupFromContext(i);
      throw throwable;
    } 
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/gnu/expr/CompiledModule.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */