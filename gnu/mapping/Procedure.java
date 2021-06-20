package gnu.mapping;

import gnu.bytecode.Type;
import gnu.expr.Expression;

public abstract class Procedure extends PropertySet {
  public static final LazyPropertyKey<?> compilerKey;
  
  private static final Symbol setterKey = Namespace.EmptyNamespace.getSymbol("setter");
  
  private static final String sourceLocationKey = "source-location";
  
  public static final Symbol validateApplyKey = Namespace.EmptyNamespace.getSymbol("validate-apply");
  
  static {
    compilerKey = new LazyPropertyKey("compiler");
  }
  
  public Procedure() {}
  
  public Procedure(String paramString) {
    setName(paramString);
  }
  
  public static void apply(Procedure paramProcedure, CallContext paramCallContext) throws Throwable {
    Object object;
    int i = paramCallContext.count;
    if (paramCallContext.where == 0 && i != 0) {
      object = paramProcedure.applyN(paramCallContext.values);
    } else {
      switch (i) {
        default:
          object = object.applyN(paramCallContext.getArgs());
          paramCallContext.writeValue(object);
          return;
        case 0:
          object = object.apply0();
          paramCallContext.writeValue(object);
          return;
        case 1:
          object = object.apply1(paramCallContext.getNextArg());
          paramCallContext.writeValue(object);
          return;
        case 2:
          object = object.apply2(paramCallContext.getNextArg(), paramCallContext.getNextArg());
          paramCallContext.writeValue(object);
          return;
        case 3:
          object = object.apply3(paramCallContext.getNextArg(), paramCallContext.getNextArg(), paramCallContext.getNextArg());
          paramCallContext.writeValue(object);
          return;
        case 4:
          break;
      } 
      object = object.apply4(paramCallContext.getNextArg(), paramCallContext.getNextArg(), paramCallContext.getNextArg(), paramCallContext.getNextArg());
    } 
    paramCallContext.writeValue(object);
  }
  
  public static void checkArgCount(Procedure paramProcedure, int paramInt) {
    int i = paramProcedure.numArgs();
    if (paramInt < minArgs(i) || (i >= 0 && paramInt > maxArgs(i)))
      throw new WrongArguments(paramProcedure, paramInt); 
  }
  
  public static int maxArgs(int paramInt) {
    return paramInt >> 12;
  }
  
  public static int minArgs(int paramInt) {
    return paramInt & 0xFFF;
  }
  
  public void apply(CallContext paramCallContext) throws Throwable {
    apply(this, paramCallContext);
  }
  
  public abstract Object apply0() throws Throwable;
  
  public abstract Object apply1(Object paramObject) throws Throwable;
  
  public abstract Object apply2(Object paramObject1, Object paramObject2) throws Throwable;
  
  public abstract Object apply3(Object paramObject1, Object paramObject2, Object paramObject3) throws Throwable;
  
  public abstract Object apply4(Object paramObject1, Object paramObject2, Object paramObject3, Object paramObject4) throws Throwable;
  
  public abstract Object applyN(Object[] paramArrayOfObject) throws Throwable;
  
  public void check0(CallContext paramCallContext) {
    int i = match0(paramCallContext);
    if (i != 0)
      throw MethodProc.matchFailAsException(i, this, ProcedureN.noArgs); 
  }
  
  public void check1(Object paramObject, CallContext paramCallContext) {
    int i = match1(paramObject, paramCallContext);
    if (i != 0)
      throw MethodProc.matchFailAsException(i, this, new Object[] { paramObject }); 
  }
  
  public void check2(Object paramObject1, Object paramObject2, CallContext paramCallContext) {
    int i = match2(paramObject1, paramObject2, paramCallContext);
    if (i != 0)
      throw MethodProc.matchFailAsException(i, this, new Object[] { paramObject1, paramObject2 }); 
  }
  
  public void check3(Object paramObject1, Object paramObject2, Object paramObject3, CallContext paramCallContext) {
    int i = match3(paramObject1, paramObject2, paramObject3, paramCallContext);
    if (i != 0)
      throw MethodProc.matchFailAsException(i, this, new Object[] { paramObject1, paramObject2, paramObject3 }); 
  }
  
  public void check4(Object paramObject1, Object paramObject2, Object paramObject3, Object paramObject4, CallContext paramCallContext) {
    int i = match4(paramObject1, paramObject2, paramObject3, paramObject4, paramCallContext);
    if (i != 0)
      throw MethodProc.matchFailAsException(i, this, new Object[] { paramObject1, paramObject2, paramObject3, paramObject4 }); 
  }
  
  public void checkN(Object[] paramArrayOfObject, CallContext paramCallContext) {
    int i = matchN(paramArrayOfObject, paramCallContext);
    if (i != 0)
      throw MethodProc.matchFailAsException(i, this, paramArrayOfObject); 
  }
  
  public Type getReturnType(Expression[] paramArrayOfExpression) {
    return (Type)Type.objectType;
  }
  
  public Procedure getSetter() {
    if (!(this instanceof HasSetter)) {
      Object object = getProperty(setterKey, null);
      if (object instanceof Procedure)
        return (Procedure)object; 
      throw new RuntimeException("procedure '" + getName() + "' has no setter");
    } 
    int i = numArgs();
    return (i == 0) ? new Setter0(this) : ((i == 4097) ? new Setter1(this) : new Setter(this));
  }
  
  public String getSourceLocation() {
    Object object = getProperty("source-location", null);
    return (object == null) ? null : object.toString();
  }
  
  public boolean isSideEffectFree() {
    return false;
  }
  
  public int match0(CallContext paramCallContext) {
    int i = numArgs();
    int j = minArgs(i);
    if (j > 0)
      return 0xFFF10000 | j; 
    if (i < 0)
      return matchN(ProcedureN.noArgs, paramCallContext); 
    paramCallContext.count = 0;
    paramCallContext.where = 0;
    paramCallContext.next = 0;
    paramCallContext.proc = this;
    return 0;
  }
  
  public int match1(Object paramObject, CallContext paramCallContext) {
    int i = numArgs();
    int j = minArgs(i);
    if (j > 1)
      return 0xFFF10000 | j; 
    if (i >= 0) {
      i = maxArgs(i);
      if (i < 1)
        return 0xFFF20000 | i; 
      paramCallContext.value1 = paramObject;
      paramCallContext.count = 1;
      paramCallContext.where = 1;
      paramCallContext.next = 0;
      paramCallContext.proc = this;
      return 0;
    } 
    return matchN(new Object[] { paramObject }, paramCallContext);
  }
  
  public int match2(Object paramObject1, Object paramObject2, CallContext paramCallContext) {
    int i = numArgs();
    int j = minArgs(i);
    if (j > 2)
      return 0xFFF10000 | j; 
    if (i >= 0) {
      i = maxArgs(i);
      if (i < 2)
        return 0xFFF20000 | i; 
      paramCallContext.value1 = paramObject1;
      paramCallContext.value2 = paramObject2;
      paramCallContext.count = 2;
      paramCallContext.where = 33;
      paramCallContext.next = 0;
      paramCallContext.proc = this;
      return 0;
    } 
    return matchN(new Object[] { paramObject1, paramObject2 }, paramCallContext);
  }
  
  public int match3(Object paramObject1, Object paramObject2, Object paramObject3, CallContext paramCallContext) {
    int i = numArgs();
    int j = minArgs(i);
    if (j > 3)
      return 0xFFF10000 | j; 
    if (i >= 0) {
      i = maxArgs(i);
      if (i < 3)
        return 0xFFF20000 | i; 
      paramCallContext.value1 = paramObject1;
      paramCallContext.value2 = paramObject2;
      paramCallContext.value3 = paramObject3;
      paramCallContext.count = 3;
      paramCallContext.where = 801;
      paramCallContext.next = 0;
      paramCallContext.proc = this;
      return 0;
    } 
    return matchN(new Object[] { paramObject1, paramObject2, paramObject3 }, paramCallContext);
  }
  
  public int match4(Object paramObject1, Object paramObject2, Object paramObject3, Object paramObject4, CallContext paramCallContext) {
    int i = numArgs();
    int j = minArgs(i);
    if (j > 4)
      return 0xFFF10000 | j; 
    if (i >= 0) {
      i = maxArgs(i);
      if (i < 4)
        return 0xFFF20000 | i; 
      paramCallContext.value1 = paramObject1;
      paramCallContext.value2 = paramObject2;
      paramCallContext.value3 = paramObject3;
      paramCallContext.value4 = paramObject4;
      paramCallContext.count = 4;
      paramCallContext.where = 17185;
      paramCallContext.next = 0;
      paramCallContext.proc = this;
      return 0;
    } 
    return matchN(new Object[] { paramObject1, paramObject2, paramObject3, paramObject4 }, paramCallContext);
  }
  
  public int matchN(Object[] paramArrayOfObject, CallContext paramCallContext) {
    int i = numArgs();
    int j = minArgs(i);
    if (paramArrayOfObject.length < j)
      return 0xFFF10000 | j; 
    if (i >= 0) {
      switch (paramArrayOfObject.length) {
        default:
          i = maxArgs(i);
          if (paramArrayOfObject.length > i)
            return 0xFFF20000 | i; 
          paramCallContext.values = paramArrayOfObject;
          paramCallContext.count = paramArrayOfObject.length;
          paramCallContext.where = 0;
          paramCallContext.next = 0;
          paramCallContext.proc = this;
          return 0;
        case 0:
          return match0(paramCallContext);
        case 1:
          return match1(paramArrayOfObject[0], paramCallContext);
        case 2:
          return match2(paramArrayOfObject[0], paramArrayOfObject[1], paramCallContext);
        case 3:
          return match3(paramArrayOfObject[0], paramArrayOfObject[1], paramArrayOfObject[2], paramCallContext);
        case 4:
          break;
      } 
      return match4(paramArrayOfObject[0], paramArrayOfObject[1], paramArrayOfObject[2], paramArrayOfObject[3], paramCallContext);
    } 
    paramCallContext.values = paramArrayOfObject;
    paramCallContext.count = paramArrayOfObject.length;
    paramCallContext.where = 0;
    paramCallContext.next = 0;
    paramCallContext.proc = this;
    return 0;
  }
  
  public final int maxArgs() {
    return maxArgs(numArgs());
  }
  
  public final int minArgs() {
    return minArgs(numArgs());
  }
  
  public int numArgs() {
    return -4096;
  }
  
  public void set0(Object paramObject) throws Throwable {
    getSetter().apply1(paramObject);
  }
  
  public void set1(Object paramObject1, Object paramObject2) throws Throwable {
    getSetter().apply2(paramObject1, paramObject2);
  }
  
  public void setN(Object[] paramArrayOfObject) throws Throwable {
    getSetter().applyN(paramArrayOfObject);
  }
  
  public void setSetter(Procedure paramProcedure) {
    if (this instanceof HasSetter)
      throw new RuntimeException("procedure '" + getName() + "' has builtin setter - cannot be modified"); 
    setProperty(setterKey, paramProcedure);
  }
  
  public void setSourceLocation(String paramString, int paramInt) {
    setProperty("source-location", paramString + ":" + paramInt);
  }
  
  public String toString() {
    StringBuffer stringBuffer = new StringBuffer();
    stringBuffer.append("#<procedure ");
    String str2 = getName();
    String str1 = str2;
    if (str2 == null)
      str1 = getSourceLocation(); 
    str2 = str1;
    if (str1 == null)
      str2 = getClass().getName(); 
    stringBuffer.append(str2);
    stringBuffer.append('>');
    return stringBuffer.toString();
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/gnu/mapping/Procedure.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */