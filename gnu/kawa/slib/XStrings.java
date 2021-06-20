package gnu.kawa.slib;

import gnu.expr.ModuleBody;
import gnu.expr.ModuleInfo;
import gnu.expr.ModuleMethod;
import gnu.lists.Consumer;
import gnu.mapping.CallContext;
import gnu.mapping.Procedure;
import gnu.mapping.SimpleSymbol;
import gnu.mapping.Values;
import gnu.mapping.WrongType;
import gnu.math.IntNum;

public class XStrings extends ModuleBody {
  public static final XStrings $instance;
  
  static final IntNum Lit0;
  
  static final SimpleSymbol Lit1;
  
  static final SimpleSymbol Lit2 = (SimpleSymbol)(new SimpleSymbol("string-length")).readResolve();
  
  public static final ModuleMethod string$Mnlength;
  
  public static final ModuleMethod substring;
  
  static {
    Lit1 = (SimpleSymbol)(new SimpleSymbol("substring")).readResolve();
    Lit0 = IntNum.make(2147483647);
    $instance = new XStrings();
    XStrings xStrings = $instance;
    substring = new ModuleMethod(xStrings, 1, Lit1, 12290);
    string$Mnlength = new ModuleMethod(xStrings, 3, Lit2, 4097);
    $instance.run();
  }
  
  public XStrings() {
    ModuleInfo.register(this);
  }
  
  public static Object stringLength(Object paramObject) {
    return (paramObject == Values.empty) ? Values.empty : Integer.valueOf(((String)paramObject).length());
  }
  
  public static Object substring(Object paramObject1, Object paramObject2) {
    return substring(paramObject1, paramObject2, Lit0);
  }
  
  public static Object substring(Object paramObject1, Object paramObject2, Object paramObject3) {
    int i;
    if (paramObject1 == Values.empty) {
      i = 1;
    } else {
      i = 0;
    } 
    if (i) {
      if (i)
        return Values.empty; 
    } else {
      if (paramObject2 == Values.empty) {
        i = 1;
      } else {
        i = 0;
      } 
      if (i ? !i : (paramObject3 != Values.empty))
        return Values.empty; 
    } 
    try {
      String str = (String)paramObject1;
      int j = str.length();
      try {
        i = ((Number)paramObject2).intValue();
        int k = i - 1;
        try {
          i = ((Number)paramObject3).intValue();
          j -= k;
          if (i > j)
            i = j; 
          return str.substring(k, k + i);
        } catch (ClassCastException null) {
          throw new WrongType(classCastException, "len", -2, paramObject3);
        } 
      } catch (ClassCastException classCastException) {
        throw new WrongType(classCastException, "sindex", -2, paramObject2);
      } 
    } catch (ClassCastException classCastException1) {
      throw new WrongType(classCastException1, "s", -2, classCastException);
    } 
  }
  
  public Object apply1(ModuleMethod paramModuleMethod, Object paramObject) {
    return (paramModuleMethod.selector == 3) ? stringLength(paramObject) : super.apply1(paramModuleMethod, paramObject);
  }
  
  public Object apply2(ModuleMethod paramModuleMethod, Object paramObject1, Object paramObject2) {
    return (paramModuleMethod.selector == 1) ? substring(paramObject1, paramObject2) : super.apply2(paramModuleMethod, paramObject1, paramObject2);
  }
  
  public Object apply3(ModuleMethod paramModuleMethod, Object paramObject1, Object paramObject2, Object paramObject3) {
    return (paramModuleMethod.selector == 1) ? substring(paramObject1, paramObject2, paramObject3) : super.apply3(paramModuleMethod, paramObject1, paramObject2, paramObject3);
  }
  
  public int match1(ModuleMethod paramModuleMethod, Object paramObject, CallContext paramCallContext) {
    if (paramModuleMethod.selector == 3) {
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
  
  public int match3(ModuleMethod paramModuleMethod, Object paramObject1, Object paramObject2, Object paramObject3, CallContext paramCallContext) {
    if (paramModuleMethod.selector == 1) {
      paramCallContext.value1 = paramObject1;
      paramCallContext.value2 = paramObject2;
      paramCallContext.value3 = paramObject3;
      paramCallContext.proc = (Procedure)paramModuleMethod;
      paramCallContext.pc = 3;
      return 0;
    } 
    return super.match3(paramModuleMethod, paramObject1, paramObject2, paramObject3, paramCallContext);
  }
  
  public final void run(CallContext paramCallContext) {
    Consumer consumer = paramCallContext.consumer;
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/gnu/kawa/slib/XStrings.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */