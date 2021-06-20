package gnu.kawa.slib;

import gnu.expr.ModuleBody;
import gnu.expr.ModuleInfo;
import gnu.expr.ModuleMethod;
import gnu.lists.Consumer;
import gnu.mapping.CallContext;
import gnu.mapping.Procedure;
import gnu.mapping.SimpleSymbol;
import gnu.math.IntNum;
import kawa.lib.ports;

public class pp extends ModuleBody {
  public static final pp $instance;
  
  static final IntNum Lit0;
  
  static final SimpleSymbol Lit1 = (SimpleSymbol)(new SimpleSymbol("pretty-print")).readResolve();
  
  public static final ModuleMethod pretty$Mnprint;
  
  static {
    Lit0 = IntNum.make(79);
    $instance = new pp();
    pretty$Mnprint = new ModuleMethod($instance, 2, Lit1, 8193);
    $instance.run();
  }
  
  public pp() {
    ModuleInfo.register(this);
  }
  
  public static Object prettyPrint(Object paramObject) {
    return prettyPrint(paramObject, ports.current$Mnoutput$Mnport.apply0());
  }
  
  public static Object prettyPrint(Object paramObject1, Object paramObject2) {
    frame frame = new frame();
    frame.port = paramObject2;
    return genwrite.genericWrite(paramObject1, Boolean.FALSE, Lit0, frame.lambda$Fn1);
  }
  
  public Object apply1(ModuleMethod paramModuleMethod, Object paramObject) {
    return (paramModuleMethod.selector == 2) ? prettyPrint(paramObject) : super.apply1(paramModuleMethod, paramObject);
  }
  
  public Object apply2(ModuleMethod paramModuleMethod, Object paramObject1, Object paramObject2) {
    return (paramModuleMethod.selector == 2) ? prettyPrint(paramObject1, paramObject2) : super.apply2(paramModuleMethod, paramObject1, paramObject2);
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
    if (paramModuleMethod.selector == 2) {
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
  
  public class frame extends ModuleBody {
    final ModuleMethod lambda$Fn1;
    
    Object port;
    
    public frame() {
      ModuleMethod moduleMethod = new ModuleMethod(this, 1, null, 4097);
      moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/pp.scm:9");
      this.lambda$Fn1 = moduleMethod;
    }
    
    public Object apply1(ModuleMethod param1ModuleMethod, Object param1Object) {
      return (param1ModuleMethod.selector == 1) ? lambda1(param1Object) : super.apply1(param1ModuleMethod, param1Object);
    }
    
    Boolean lambda1(Object param1Object) {
      ports.display(param1Object, this.port);
      return Boolean.TRUE;
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
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/gnu/kawa/slib/pp.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */