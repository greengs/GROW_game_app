package gnu.mapping;

import gnu.bytecode.ArrayType;
import gnu.bytecode.Type;

public abstract class MethodProc extends ProcedureN {
  public static final int NO_MATCH = -1;
  
  public static final int NO_MATCH_AMBIGUOUS = -851968;
  
  public static final int NO_MATCH_BAD_TYPE = -786432;
  
  public static final int NO_MATCH_TOO_FEW_ARGS = -983040;
  
  public static final int NO_MATCH_TOO_MANY_ARGS = -917504;
  
  static final Type[] unknownArgTypes = new Type[] { (Type)Type.pointer_type };
  
  protected Object argTypes;
  
  public static RuntimeException matchFailAsException(int paramInt, Procedure paramProcedure, Object[] paramArrayOfObject) {
    short s = (short)paramInt;
    if ((paramInt & 0xFFFF0000) != -786432)
      return new WrongArguments(paramProcedure, paramArrayOfObject.length); 
    if (s > 0) {
      Object object = paramArrayOfObject[s - 1];
      return new WrongType(paramProcedure, s, object);
    } 
    paramArrayOfObject = null;
    return new WrongType(paramProcedure, s, paramArrayOfObject);
  }
  
  public static int mostSpecific(MethodProc[] paramArrayOfMethodProc, int paramInt) {
    // Byte code:
    //   0: iload_1
    //   1: iconst_1
    //   2: if_icmpgt -> 11
    //   5: iload_1
    //   6: iconst_1
    //   7: isub
    //   8: istore_2
    //   9: iload_2
    //   10: ireturn
    //   11: aload_0
    //   12: iconst_0
    //   13: aaload
    //   14: astore #7
    //   16: aconst_null
    //   17: astore #6
    //   19: iconst_1
    //   20: istore_3
    //   21: iconst_0
    //   22: istore_2
    //   23: iload_3
    //   24: iload_1
    //   25: if_icmpge -> 202
    //   28: aload_0
    //   29: iload_3
    //   30: aaload
    //   31: astore #8
    //   33: aload #7
    //   35: ifnull -> 119
    //   38: aload #7
    //   40: aload #8
    //   42: invokestatic mostSpecific : (Lgnu/mapping/MethodProc;Lgnu/mapping/MethodProc;)Lgnu/mapping/MethodProc;
    //   45: astore #5
    //   47: aload #5
    //   49: ifnonnull -> 103
    //   52: aload #6
    //   54: astore #5
    //   56: aload #6
    //   58: ifnonnull -> 67
    //   61: iload_1
    //   62: anewarray gnu/mapping/MethodProc
    //   65: astore #5
    //   67: aload #5
    //   69: iconst_0
    //   70: aload #7
    //   72: aastore
    //   73: aload #5
    //   75: iconst_1
    //   76: aload #8
    //   78: aastore
    //   79: iconst_2
    //   80: istore_2
    //   81: aconst_null
    //   82: astore #7
    //   84: aload #5
    //   86: astore #6
    //   88: aload #7
    //   90: astore #5
    //   92: iload_3
    //   93: iconst_1
    //   94: iadd
    //   95: istore_3
    //   96: aload #5
    //   98: astore #7
    //   100: goto -> 23
    //   103: aload #5
    //   105: aload #8
    //   107: if_acmpne -> 209
    //   110: aload #8
    //   112: astore #5
    //   114: iload_3
    //   115: istore_2
    //   116: goto -> 92
    //   119: iconst_0
    //   120: istore #4
    //   122: iload #4
    //   124: iload_2
    //   125: if_icmpge -> 193
    //   128: aload #6
    //   130: iload #4
    //   132: aaload
    //   133: astore #5
    //   135: aload #5
    //   137: aload #8
    //   139: invokestatic mostSpecific : (Lgnu/mapping/MethodProc;Lgnu/mapping/MethodProc;)Lgnu/mapping/MethodProc;
    //   142: astore #9
    //   144: aload #9
    //   146: aload #5
    //   148: if_acmpne -> 158
    //   151: aload #7
    //   153: astore #5
    //   155: goto -> 92
    //   158: aload #9
    //   160: ifnonnull -> 184
    //   163: iload_2
    //   164: iconst_1
    //   165: iadd
    //   166: istore #4
    //   168: aload #6
    //   170: iload_2
    //   171: aload #8
    //   173: aastore
    //   174: aload #7
    //   176: astore #5
    //   178: iload #4
    //   180: istore_2
    //   181: goto -> 92
    //   184: iload #4
    //   186: iconst_1
    //   187: iadd
    //   188: istore #4
    //   190: goto -> 122
    //   193: aload #8
    //   195: astore #5
    //   197: iload_3
    //   198: istore_2
    //   199: goto -> 92
    //   202: aload #7
    //   204: ifnonnull -> 9
    //   207: iconst_m1
    //   208: ireturn
    //   209: aload #7
    //   211: astore #5
    //   213: goto -> 92
  }
  
  public static MethodProc mostSpecific(MethodProc paramMethodProc1, MethodProc paramMethodProc2) {
    int i = 0;
    int k = 0;
    int n = 0;
    int i1 = paramMethodProc1.minArgs();
    int i2 = paramMethodProc2.minArgs();
    int i4 = paramMethodProc1.maxArgs();
    int i3 = paramMethodProc2.maxArgs();
    if ((i4 >= 0 && i4 < i2) || (i3 >= 0 && i3 < i1))
      return null; 
    int m = paramMethodProc1.numParameters();
    int j = paramMethodProc2.numParameters();
    if (m <= j)
      m = j; 
    j = n;
    if (i4 != i3) {
      if (i4 < 0)
        k = 1; 
      i = k;
      j = n;
      if (i3 < 0) {
        j = 1;
        i = k;
      } 
    } 
    if (i1 < i2) {
      k = 1;
    } else {
      k = i;
      if (i1 > i2) {
        j = 1;
        k = i;
      } 
    } 
    for (i = 0; i < m; i++) {
      n = paramMethodProc1.getParameterType(i).compare(paramMethodProc2.getParameterType(i));
      if (n == -1) {
        j = 1;
        if (k != 0)
          return null; 
      } 
      if (n == 1) {
        k = 1;
        if (j != 0)
          return null; 
      } 
    } 
    return (j == 0) ? ((k != 0) ? paramMethodProc2 : null) : paramMethodProc1;
  }
  
  public Object applyN(Object[] paramArrayOfObject) throws Throwable {
    checkArgCount(this, paramArrayOfObject.length);
    CallContext callContext = CallContext.getInstance();
    checkN(paramArrayOfObject, callContext);
    return callContext.runUntilValue();
  }
  
  public Type getParameterType(int paramInt) {
    if (!(this.argTypes instanceof Type[]))
      resolveParameterTypes(); 
    Type[] arrayOfType = (Type[])this.argTypes;
    if (paramInt < arrayOfType.length && (paramInt < arrayOfType.length - 1 || maxArgs() >= 0))
      return arrayOfType[paramInt]; 
    if (maxArgs() < 0) {
      Type type = arrayOfType[arrayOfType.length - 1];
      if (type instanceof ArrayType)
        return ((ArrayType)type).getComponentType(); 
    } 
    return (Type)Type.objectType;
  }
  
  public int isApplicable(Type[] paramArrayOfType) {
    int j = paramArrayOfType.length;
    int i = numArgs();
    if (j < (i & 0xFFF) || (i >= 0 && j > i >> 12))
      return -1; 
    i = 1;
    while (true) {
      int k = j - 1;
      j = i;
      if (k >= 0) {
        int m = getParameterType(k).compare(paramArrayOfType[k]);
        if (m == -3)
          return -1; 
        j = k;
        if (m < 0) {
          i = 0;
          j = k;
        } 
        continue;
      } 
      return j;
    } 
  }
  
  public int numParameters() {
    int i = numArgs();
    int j = i >> 12;
    return (j >= 0) ? j : ((i & 0xFFF) + 1);
  }
  
  protected void resolveParameterTypes() {
    this.argTypes = unknownArgTypes;
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/gnu/mapping/MethodProc.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */