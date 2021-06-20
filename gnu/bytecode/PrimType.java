package gnu.bytecode;

public class PrimType extends Type {
  private static final String numberHierarchy = "A:java.lang.Byte;B:java.lang.Short;C:java.lang.Integer;D:java.lang.Long;E:gnu.math.IntNum;E:java.gnu.math.BitInteger;G:gnu.math.RatNum;H:java.lang.Float;I:java.lang.Double;I:gnu.math.DFloNum;J:gnu.math.RealNum;K:gnu.math.Complex;L:gnu.math.Quantity;K:gnu.math.Numeric;N:java.lang.Number;";
  
  protected PrimType(PrimType paramPrimType) {
    super(paramPrimType.this_name, paramPrimType.signature);
    this.size = paramPrimType.size;
    this.reflectClass = paramPrimType.reflectClass;
  }
  
  public PrimType(String paramString1, String paramString2, int paramInt, Class paramClass) {
    super(paramString1, paramString2);
    this.size = paramInt;
    this.reflectClass = paramClass;
    Type.registerTypeForClass(paramClass, this);
  }
  
  public static boolean booleanValue(Object paramObject) {
    return (!(paramObject instanceof Boolean) || ((Boolean)paramObject).booleanValue());
  }
  
  public static int compare(PrimType paramPrimType1, PrimType paramPrimType2) {
    byte b2 = -3;
    byte b3 = -1;
    char c1 = paramPrimType1.signature.charAt(0);
    char c2 = paramPrimType2.signature.charAt(0);
    if (c1 == c2)
      return 0; 
    if (c1 == 'V')
      return 1; 
    byte b1 = b3;
    if (c2 != 'V') {
      if (c1 == 'Z' || c2 == 'Z')
        return -3; 
      if (c1 == 'C') {
        b1 = b3;
        return (paramPrimType2.size <= 2) ? -3 : b1;
      } 
      if (c2 == 'C') {
        b1 = b2;
        if (paramPrimType1.size > 2)
          b1 = 1; 
        return b1;
      } 
      if (c1 == 'D')
        return 1; 
      b1 = b3;
      if (c2 != 'D') {
        if (c1 == 'F')
          return 1; 
        b1 = b3;
        if (c2 != 'F') {
          if (c1 == 'J')
            return 1; 
          b1 = b3;
          if (c2 != 'J') {
            if (c1 == 'I')
              return 1; 
            b1 = b3;
            if (c2 != 'I') {
              if (c1 == 'S')
                return 1; 
              b1 = b3;
              if (c2 != 'S')
                return -3; 
            } 
          } 
        } 
      } 
    } 
    return b1;
  }
  
  private static char findInHierarchy(String paramString) {
    int i = "A:java.lang.Byte;B:java.lang.Short;C:java.lang.Integer;D:java.lang.Long;E:gnu.math.IntNum;E:java.gnu.math.BitInteger;G:gnu.math.RatNum;H:java.lang.Float;I:java.lang.Double;I:gnu.math.DFloNum;J:gnu.math.RealNum;K:gnu.math.Complex;L:gnu.math.Quantity;K:gnu.math.Numeric;N:java.lang.Number;".indexOf(paramString) - 2;
    return (i < 0) ? Character.MIN_VALUE : "A:java.lang.Byte;B:java.lang.Short;C:java.lang.Integer;D:java.lang.Long;E:gnu.math.IntNum;E:java.gnu.math.BitInteger;G:gnu.math.RatNum;H:java.lang.Float;I:java.lang.Double;I:gnu.math.DFloNum;J:gnu.math.RealNum;K:gnu.math.Complex;L:gnu.math.Quantity;K:gnu.math.Numeric;N:java.lang.Number;".charAt(i);
  }
  
  public ClassType boxedType() {
    switch (getSignature().charAt(0)) {
      default:
        str = null;
        return ClassType.make(str);
      case 'Z':
        str = "java.lang.Boolean";
        return ClassType.make(str);
      case 'C':
        str = "java.lang.Character";
        return ClassType.make(str);
      case 'B':
        str = "java.lang.Byte";
        return ClassType.make(str);
      case 'S':
        str = "java.lang.Short";
        return ClassType.make(str);
      case 'I':
        str = "java.lang.Integer";
        return ClassType.make(str);
      case 'J':
        str = "java.lang.Long";
        return ClassType.make(str);
      case 'F':
        str = "java.lang.Float";
        return ClassType.make(str);
      case 'D':
        break;
    } 
    String str = "java.lang.Double";
    return ClassType.make(str);
  }
  
  public char charValue(Object paramObject) {
    return ((Character)paramObject).charValue();
  }
  
  public Object coerceFromObject(Object paramObject) {
    char c;
    if (paramObject.getClass() == this.reflectClass)
      return paramObject; 
    if (this.signature == null || this.signature.length() != 1) {
      c = ' ';
    } else {
      c = this.signature.charAt(0);
    } 
    switch (c) {
      default:
        throw new ClassCastException("don't know how to coerce " + paramObject.getClass().getName() + " to " + getName());
      case 'B':
        return Byte.valueOf(((Number)paramObject).byteValue());
      case 'S':
        return Short.valueOf(((Number)paramObject).shortValue());
      case 'I':
        return Integer.valueOf(((Number)paramObject).intValue());
      case 'J':
        return Long.valueOf(((Number)paramObject).longValue());
      case 'F':
        return Float.valueOf(((Number)paramObject).floatValue());
      case 'D':
        return Double.valueOf(((Number)paramObject).doubleValue());
      case 'Z':
        break;
    } 
    return Boolean.valueOf(((Boolean)paramObject).booleanValue());
  }
  
  public int compare(Type paramType) {
    // Byte code:
    //   0: iconst_0
    //   1: istore #4
    //   3: aload_1
    //   4: instanceof gnu/bytecode/PrimType
    //   7: ifeq -> 38
    //   10: aload_1
    //   11: invokevirtual getImplementationType : ()Lgnu/bytecode/Type;
    //   14: aload_1
    //   15: if_acmpeq -> 29
    //   18: aload_1
    //   19: aload_0
    //   20: invokevirtual compare : (Lgnu/bytecode/Type;)I
    //   23: invokestatic swappedCompareResult : (I)I
    //   26: istore_3
    //   27: iload_3
    //   28: ireturn
    //   29: aload_0
    //   30: aload_1
    //   31: checkcast gnu/bytecode/PrimType
    //   34: invokestatic compare : (Lgnu/bytecode/PrimType;Lgnu/bytecode/PrimType;)I
    //   37: ireturn
    //   38: aload_1
    //   39: instanceof gnu/bytecode/ClassType
    //   42: ifne -> 64
    //   45: aload_1
    //   46: instanceof gnu/bytecode/ArrayType
    //   49: ifeq -> 55
    //   52: bipush #-3
    //   54: ireturn
    //   55: aload_1
    //   56: aload_0
    //   57: invokevirtual compare : (Lgnu/bytecode/Type;)I
    //   60: invokestatic swappedCompareResult : (I)I
    //   63: ireturn
    //   64: aload_0
    //   65: getfield signature : Ljava/lang/String;
    //   68: iconst_0
    //   69: invokevirtual charAt : (I)C
    //   72: istore_3
    //   73: aload_1
    //   74: invokevirtual getName : ()Ljava/lang/String;
    //   77: astore #6
    //   79: aload #6
    //   81: ifnonnull -> 86
    //   84: iconst_m1
    //   85: ireturn
    //   86: iconst_0
    //   87: istore_2
    //   88: iload_3
    //   89: lookupswitch default -> 172, 66 -> 232, 67 -> 220, 68 -> 262, 70 -> 256, 73 -> 244, 74 -> 250, 83 -> 238, 86 -> 205, 90 -> 207
    //   172: iload_2
    //   173: ifeq -> 270
    //   176: aload #6
    //   178: invokestatic findInHierarchy : (Ljava/lang/String;)C
    //   181: istore #5
    //   183: iload #5
    //   185: ifeq -> 270
    //   188: iload #4
    //   190: istore_3
    //   191: iload #5
    //   193: iload_2
    //   194: if_icmpeq -> 27
    //   197: iload #5
    //   199: iload_2
    //   200: if_icmpge -> 268
    //   203: iconst_1
    //   204: ireturn
    //   205: iconst_1
    //   206: ireturn
    //   207: iload #4
    //   209: istore_3
    //   210: aload #6
    //   212: ldc 'java.lang.Boolean'
    //   214: invokevirtual equals : (Ljava/lang/Object;)Z
    //   217: ifne -> 27
    //   220: aload #6
    //   222: ldc 'java.lang.Character'
    //   224: invokevirtual equals : (Ljava/lang/Object;)Z
    //   227: ifeq -> 172
    //   230: iconst_0
    //   231: ireturn
    //   232: bipush #65
    //   234: istore_2
    //   235: goto -> 172
    //   238: bipush #66
    //   240: istore_2
    //   241: goto -> 172
    //   244: bipush #67
    //   246: istore_2
    //   247: goto -> 172
    //   250: bipush #68
    //   252: istore_2
    //   253: goto -> 172
    //   256: bipush #72
    //   258: istore_2
    //   259: goto -> 172
    //   262: bipush #73
    //   264: istore_2
    //   265: goto -> 172
    //   268: iconst_m1
    //   269: ireturn
    //   270: aload #6
    //   272: ldc 'java.lang.Object'
    //   274: invokevirtual equals : (Ljava/lang/Object;)Z
    //   277: ifne -> 287
    //   280: aload_1
    //   281: getstatic gnu/bytecode/PrimType.toStringType : Lgnu/bytecode/ClassType;
    //   284: if_acmpne -> 289
    //   287: iconst_m1
    //   288: ireturn
    //   289: bipush #-3
    //   291: ireturn
  }
  
  public void emitCoerceFromObject(CodeAttr paramCodeAttr) {
    char c;
    if (this.signature == null || this.signature.length() != 1) {
      c = ' ';
    } else {
      c = this.signature.charAt(0);
    } 
    if (c == 'Z') {
      paramCodeAttr.emitCheckcast(javalangBooleanType);
      paramCodeAttr.emitInvokeVirtual(booleanValue_method);
      return;
    } 
    if (c == 'V') {
      paramCodeAttr.emitPop(1);
      return;
    } 
    paramCodeAttr.emitCheckcast(javalangNumberType);
    if (c == 'I' || c == 'S' || c == 'B') {
      paramCodeAttr.emitInvokeVirtual(intValue_method);
      return;
    } 
    if (c == 'J') {
      paramCodeAttr.emitInvokeVirtual(longValue_method);
      return;
    } 
    if (c == 'D') {
      paramCodeAttr.emitInvokeVirtual(doubleValue_method);
      return;
    } 
    if (c == 'F') {
      paramCodeAttr.emitInvokeVirtual(floatValue_method);
      return;
    } 
    super.emitCoerceFromObject(paramCodeAttr);
  }
  
  public void emitCoerceToObject(CodeAttr paramCodeAttr) {
    Method method;
    char c = getSignature().charAt(0);
    ClassType classType = boxedType();
    if (c == 'Z') {
      paramCodeAttr.emitIfIntNotZero();
      paramCodeAttr.emitGetStatic(classType.getDeclaredField("TRUE"));
      paramCodeAttr.emitElse();
      paramCodeAttr.emitGetStatic(classType.getDeclaredField("FALSE"));
      paramCodeAttr.emitFi();
      return;
    } 
    Type[] arrayOfType = new Type[1];
    arrayOfType[0] = this;
    if ((paramCodeAttr.getMethod().getDeclaringClass()).classfileFormatVersion >= 3211264) {
      method = classType.getDeclaredMethod("valueOf", arrayOfType);
    } else {
      method = classType.getDeclaredMethod("<init>", (Type[])method);
      paramCodeAttr.emitNew(classType);
      paramCodeAttr.emitDupX();
      paramCodeAttr.emitSwap();
    } 
    paramCodeAttr.emitInvoke(method);
  }
  
  public void emitIsInstance(CodeAttr paramCodeAttr) {
    char c;
    if (this.signature == null || this.signature.length() != 1) {
      c = ' ';
    } else {
      c = this.signature.charAt(0);
    } 
    if (c == 'Z') {
      javalangBooleanType.emitIsInstance(paramCodeAttr);
      return;
    } 
    if (c == 'V') {
      paramCodeAttr.emitPop(1);
      paramCodeAttr.emitPushInt(1);
      return;
    } 
    javalangNumberType.emitIsInstance(paramCodeAttr);
  }
  
  public Type promotedType() {
    switch (this.signature.charAt(0)) {
      default:
        return getImplementationType();
      case 'B':
      case 'C':
      case 'I':
      case 'S':
      case 'Z':
        break;
    } 
    return Type.intType;
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/gnu/bytecode/PrimType.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */