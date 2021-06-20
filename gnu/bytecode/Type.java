package gnu.bytecode;

import gnu.kawa.util.AbstractWeakHashTable;
import java.io.PrintWriter;
import java.lang.reflect.Type;
import java.util.HashMap;

public abstract class Type implements Type {
  public static final PrimType booleanType;
  
  public static final Method booleanValue_method;
  
  public static final ClassType boolean_ctype;
  
  public static final PrimType boolean_type;
  
  public static final PrimType byteType = new PrimType("byte", "B", 1, byte.class);
  
  public static final PrimType byte_type;
  
  public static final PrimType charType;
  
  public static final PrimType char_type;
  
  public static final Method clone_method;
  
  public static final PrimType doubleType;
  
  public static final Method doubleValue_method;
  
  public static final PrimType double_type;
  
  public static final ObjectType errorType;
  
  public static final PrimType floatType;
  
  public static final Method floatValue_method;
  
  public static final PrimType float_type;
  
  public static final PrimType intType;
  
  public static final Method intValue_method;
  
  public static final PrimType int_type;
  
  public static final ClassType java_lang_Class_type;
  
  public static final ClassType javalangBooleanType;
  
  public static final ClassType javalangClassType;
  
  public static final ClassType javalangNumberType;
  
  public static final ClassType javalangObjectType;
  
  public static ClassType javalangStringType;
  
  public static final ClassType javalangThrowableType;
  
  public static final PrimType longType;
  
  public static final Method longValue_method;
  
  public static final PrimType long_type;
  
  static ClassToTypeMap mapClassToType;
  
  static HashMap<String, Type> mapNameToType;
  
  public static final PrimType neverReturnsType;
  
  public static final ObjectType nullType;
  
  public static final ClassType number_type;
  
  public static final ClassType objectType;
  
  public static final ClassType pointer_type;
  
  public static final PrimType shortType = new PrimType("short", "S", 2, short.class);
  
  public static final PrimType short_type;
  
  public static final ClassType string_type;
  
  public static final ClassType throwable_type;
  
  public static final ClassType toStringType;
  
  public static final Method toString_method;
  
  public static final ClassType tostring_type;
  
  public static final Type[] typeArray0;
  
  public static final PrimType voidType;
  
  public static final PrimType void_type;
  
  ArrayType array_type;
  
  protected Class reflectClass;
  
  String signature;
  
  int size;
  
  String this_name;
  
  static {
    intType = new PrimType("int", "I", 4, int.class);
    longType = new PrimType("long", "J", 8, long.class);
    floatType = new PrimType("float", "F", 4, float.class);
    doubleType = new PrimType("double", "D", 8, double.class);
    booleanType = new PrimType("boolean", "Z", 1, boolean.class);
    charType = new PrimType("char", "C", 2, char.class);
    voidType = new PrimType("void", "V", 0, void.class);
    byte_type = byteType;
    short_type = shortType;
    int_type = intType;
    long_type = longType;
    float_type = floatType;
    double_type = doubleType;
    boolean_type = booleanType;
    char_type = charType;
    void_type = voidType;
    mapNameToType = new HashMap<String, Type>();
    mapNameToType.put("byte", byteType);
    mapNameToType.put("short", shortType);
    mapNameToType.put("int", intType);
    mapNameToType.put("long", longType);
    mapNameToType.put("float", floatType);
    mapNameToType.put("double", doubleType);
    mapNameToType.put("boolean", booleanType);
    mapNameToType.put("char", charType);
    mapNameToType.put("void", voidType);
    neverReturnsType = new PrimType(voidType);
    neverReturnsType.this_name = "(never-returns)";
    nullType = new ObjectType("(type of null)");
    errorType = new ClassType("(error type)");
    javalangStringType = ClassType.make("java.lang.String");
    toStringType = new ClassType("java.lang.String");
    javalangObjectType = ClassType.make("java.lang.Object");
    objectType = javalangObjectType;
    javalangBooleanType = ClassType.make("java.lang.Boolean");
    javalangThrowableType = ClassType.make("java.lang.Throwable");
    typeArray0 = new Type[0];
    toString_method = objectType.getDeclaredMethod("toString", 0);
    javalangNumberType = ClassType.make("java.lang.Number");
    clone_method = Method.makeCloneMethod(objectType);
    intValue_method = javalangNumberType.addMethod("intValue", typeArray0, intType, 1);
    longValue_method = javalangNumberType.addMethod("longValue", typeArray0, longType, 1);
    floatValue_method = javalangNumberType.addMethod("floatValue", typeArray0, floatType, 1);
    doubleValue_method = javalangNumberType.addMethod("doubleValue", typeArray0, doubleType, 1);
    booleanValue_method = javalangBooleanType.addMethod("booleanValue", typeArray0, booleanType, 1);
    javalangClassType = ClassType.make("java.lang.Class");
    pointer_type = javalangObjectType;
    string_type = javalangStringType;
    tostring_type = toStringType;
    java_lang_Class_type = javalangClassType;
    boolean_ctype = javalangBooleanType;
    throwable_type = javalangThrowableType;
    number_type = javalangNumberType;
  }
  
  protected Type() {}
  
  public Type(Type paramType) {
    this.this_name = paramType.this_name;
    this.signature = paramType.signature;
    this.size = paramType.size;
    this.reflectClass = paramType.reflectClass;
  }
  
  Type(String paramString1, String paramString2) {
    this.this_name = paramString1;
    this.signature = paramString2;
  }
  
  public static Type getType(String paramString) {
    synchronized (mapNameToType) {
      Type type2 = null.get(paramString);
      Type type1 = type2;
      if (type2 == null) {
        if (paramString.endsWith("[]")) {
          type1 = ArrayType.make(paramString);
        } else {
          type1 = new ClassType(paramString);
          ((ClassType)type1).flags |= 0x10;
        } 
        null.put(paramString, type1);
      } 
      return type1;
    } 
  }
  
  public static boolean isMoreSpecific(Type[] paramArrayOfType1, Type[] paramArrayOfType2) {
    if (paramArrayOfType1.length != paramArrayOfType2.length)
      return false; 
    int i = paramArrayOfType1.length;
    while (true) {
      int j = i - 1;
      if (j >= 0) {
        i = j;
        if (!paramArrayOfType1[j].isSubtype(paramArrayOfType2[j]))
          return false; 
        continue;
      } 
      return true;
    } 
  }
  
  public static boolean isValidJavaTypeName(String paramString) {
    // Byte code:
    //   0: iconst_0
    //   1: istore_2
    //   2: aload_0
    //   3: invokevirtual length : ()I
    //   6: istore_3
    //   7: iload_3
    //   8: iconst_2
    //   9: if_icmple -> 43
    //   12: aload_0
    //   13: iload_3
    //   14: iconst_1
    //   15: isub
    //   16: invokevirtual charAt : (I)C
    //   19: bipush #93
    //   21: if_icmpne -> 43
    //   24: aload_0
    //   25: iload_3
    //   26: iconst_2
    //   27: isub
    //   28: invokevirtual charAt : (I)C
    //   31: bipush #91
    //   33: if_icmpne -> 43
    //   36: iload_3
    //   37: iconst_2
    //   38: isub
    //   39: istore_3
    //   40: goto -> 7
    //   43: iconst_0
    //   44: istore #4
    //   46: iload #4
    //   48: iload_3
    //   49: if_icmpge -> 105
    //   52: aload_0
    //   53: iload #4
    //   55: invokevirtual charAt : (I)C
    //   58: istore_1
    //   59: iload_1
    //   60: bipush #46
    //   62: if_icmpne -> 80
    //   65: iload_2
    //   66: ifeq -> 103
    //   69: iconst_0
    //   70: istore_2
    //   71: iload #4
    //   73: iconst_1
    //   74: iadd
    //   75: istore #4
    //   77: goto -> 46
    //   80: iload_2
    //   81: ifeq -> 96
    //   84: iload_1
    //   85: invokestatic isJavaIdentifierPart : (C)Z
    //   88: ifeq -> 103
    //   91: iconst_1
    //   92: istore_2
    //   93: goto -> 71
    //   96: iload_1
    //   97: invokestatic isJavaIdentifierStart : (C)Z
    //   100: ifne -> 91
    //   103: iconst_0
    //   104: ireturn
    //   105: iload #4
    //   107: iload_3
    //   108: if_icmpne -> 103
    //   111: iconst_1
    //   112: ireturn
  }
  
  public static Type lookupType(String paramString) {
    synchronized (mapNameToType) {
      return null.get(paramString);
    } 
  }
  
  public static Type lowestCommonSuperType(Type paramType1, Type paramType2) {
    if (paramType1 == neverReturnsType)
      return paramType2; 
    Type type = paramType1;
    if (paramType2 != neverReturnsType) {
      if (paramType1 == null || paramType2 == null)
        return null; 
      if (paramType1 instanceof PrimType && paramType2 instanceof PrimType) {
        type = paramType1;
        if (paramType1 != paramType2) {
          paramType1 = ((PrimType)paramType1).promotedType();
          type = paramType1;
          if (paramType1 != ((PrimType)paramType2).promotedType())
            return null; 
        } 
        return type;
      } 
      if (paramType1.isSubtype(paramType2))
        return paramType2; 
      type = paramType1;
      if (!paramType2.isSubtype(paramType1)) {
        if (!(paramType1 instanceof ClassType) || !(paramType2 instanceof ClassType))
          return objectType; 
        paramType1 = paramType1;
        paramType2 = paramType2;
        return (paramType1.isInterface() || paramType2.isInterface()) ? objectType : lowestCommonSuperType(paramType1.getSuperclass(), paramType2.getSuperclass());
      } 
    } 
    return type;
  }
  
  public static Type make(Class paramClass) {
    // Byte code:
    //   0: ldc gnu/bytecode/Type
    //   2: monitorenter
    //   3: getstatic gnu/bytecode/Type.mapClassToType : Lgnu/bytecode/Type$ClassToTypeMap;
    //   6: ifnull -> 31
    //   9: getstatic gnu/bytecode/Type.mapClassToType : Lgnu/bytecode/Type$ClassToTypeMap;
    //   12: aload_0
    //   13: invokevirtual get : (Ljava/lang/Object;)Ljava/lang/Object;
    //   16: checkcast gnu/bytecode/Type
    //   19: astore_1
    //   20: aload_1
    //   21: ifnull -> 31
    //   24: aload_1
    //   25: astore_0
    //   26: ldc gnu/bytecode/Type
    //   28: monitorexit
    //   29: aload_0
    //   30: areturn
    //   31: aload_0
    //   32: invokevirtual isArray : ()Z
    //   35: ifeq -> 59
    //   38: aload_0
    //   39: invokevirtual getComponentType : ()Ljava/lang/Class;
    //   42: invokestatic make : (Ljava/lang/Class;)Lgnu/bytecode/Type;
    //   45: invokestatic make : (Lgnu/bytecode/Type;)Lgnu/bytecode/ArrayType;
    //   48: astore_1
    //   49: aload_0
    //   50: aload_1
    //   51: invokestatic registerTypeForClass : (Ljava/lang/Class;Lgnu/bytecode/Type;)V
    //   54: aload_1
    //   55: astore_0
    //   56: goto -> 26
    //   59: aload_0
    //   60: invokevirtual isPrimitive : ()Z
    //   63: ifeq -> 83
    //   66: new java/lang/Error
    //   69: dup
    //   70: ldc_w 'internal error - primitive type not found'
    //   73: invokespecial <init> : (Ljava/lang/String;)V
    //   76: athrow
    //   77: astore_0
    //   78: ldc gnu/bytecode/Type
    //   80: monitorexit
    //   81: aload_0
    //   82: athrow
    //   83: aload_0
    //   84: invokevirtual getName : ()Ljava/lang/String;
    //   87: astore #4
    //   89: getstatic gnu/bytecode/Type.mapNameToType : Ljava/util/HashMap;
    //   92: astore_3
    //   93: aload_3
    //   94: monitorenter
    //   95: aload_3
    //   96: aload #4
    //   98: invokevirtual get : (Ljava/lang/Object;)Ljava/lang/Object;
    //   101: checkcast gnu/bytecode/Type
    //   104: astore_2
    //   105: aload_2
    //   106: ifnull -> 128
    //   109: aload_2
    //   110: astore_1
    //   111: aload_2
    //   112: getfield reflectClass : Ljava/lang/Class;
    //   115: aload_0
    //   116: if_acmpeq -> 159
    //   119: aload_2
    //   120: astore_1
    //   121: aload_2
    //   122: getfield reflectClass : Ljava/lang/Class;
    //   125: ifnull -> 159
    //   128: new gnu/bytecode/ClassType
    //   131: dup
    //   132: aload #4
    //   134: invokespecial <init> : (Ljava/lang/String;)V
    //   137: astore_1
    //   138: aload_1
    //   139: aload_1
    //   140: getfield flags : I
    //   143: bipush #16
    //   145: ior
    //   146: putfield flags : I
    //   149: getstatic gnu/bytecode/Type.mapNameToType : Ljava/util/HashMap;
    //   152: aload #4
    //   154: aload_1
    //   155: invokevirtual put : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   158: pop
    //   159: aload_3
    //   160: monitorexit
    //   161: goto -> 49
    //   164: astore_0
    //   165: aload_3
    //   166: monitorexit
    //   167: aload_0
    //   168: athrow
    // Exception table:
    //   from	to	target	type
    //   3	20	77	finally
    //   31	49	77	finally
    //   49	54	77	finally
    //   59	77	77	finally
    //   83	95	77	finally
    //   95	105	164	finally
    //   111	119	164	finally
    //   121	128	164	finally
    //   128	149	164	finally
    //   149	159	164	finally
    //   159	161	164	finally
    //   165	167	164	finally
    //   167	169	77	finally
  }
  
  public static void printSignature(String paramString, int paramInt1, int paramInt2, PrintWriter paramPrintWriter) {
    if (paramInt2 != 0) {
      PrimType primType;
      char c = paramString.charAt(paramInt1);
      if (paramInt2 == 1) {
        primType = signatureToPrimitive(c);
        if (primType != null) {
          paramPrintWriter.print(primType.getName());
          return;
        } 
        return;
      } 
      if (c == '[') {
        printSignature((String)primType, paramInt1 + 1, paramInt2 - 1, paramPrintWriter);
        paramPrintWriter.print("[]");
        return;
      } 
      if (c == 'L' && paramInt2 > 2 && primType.indexOf(';', paramInt1) == paramInt2 - 1 + paramInt1) {
        paramPrintWriter.print(primType.substring(paramInt1 + 1, paramInt2 - 1 + paramInt1).replace('/', '.'));
        return;
      } 
      paramPrintWriter.append((CharSequence)primType, paramInt1, paramInt2 - paramInt1);
      return;
    } 
  }
  
  public static void registerTypeForClass(Class paramClass, Type paramType) {
    // Byte code:
    //   0: ldc gnu/bytecode/Type
    //   2: monitorenter
    //   3: getstatic gnu/bytecode/Type.mapClassToType : Lgnu/bytecode/Type$ClassToTypeMap;
    //   6: astore_3
    //   7: aload_3
    //   8: astore_2
    //   9: aload_3
    //   10: ifnonnull -> 25
    //   13: new gnu/bytecode/Type$ClassToTypeMap
    //   16: dup
    //   17: invokespecial <init> : ()V
    //   20: astore_2
    //   21: aload_2
    //   22: putstatic gnu/bytecode/Type.mapClassToType : Lgnu/bytecode/Type$ClassToTypeMap;
    //   25: aload_1
    //   26: aload_0
    //   27: putfield reflectClass : Ljava/lang/Class;
    //   30: aload_2
    //   31: aload_0
    //   32: aload_1
    //   33: invokevirtual put : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   36: pop
    //   37: ldc gnu/bytecode/Type
    //   39: monitorexit
    //   40: return
    //   41: astore_0
    //   42: ldc gnu/bytecode/Type
    //   44: monitorexit
    //   45: aload_0
    //   46: athrow
    // Exception table:
    //   from	to	target	type
    //   3	7	41	finally
    //   13	25	41	finally
    //   25	37	41	finally
  }
  
  public static int signatureLength(String paramString) {
    return signatureLength(paramString, 0);
  }
  
  public static int signatureLength(String paramString, int paramInt) {
    if (paramString.length() > paramInt) {
      char c = paramString.charAt(paramInt);
      int i = 0;
      while (c == '[') {
        i++;
        c = paramString.charAt(++paramInt);
      } 
      if (signatureToPrimitive(c) != null)
        return i + 1; 
      if (c == 'L') {
        int j = paramString.indexOf(';', paramInt);
        if (j > 0)
          return i + j + 1 - paramInt; 
      } 
    } 
    return -1;
  }
  
  public static String signatureToName(String paramString) {
    int i = paramString.length();
    if (i != 0) {
      char c = paramString.charAt(0);
      if (i == 1) {
        PrimType primType = signatureToPrimitive(c);
        if (primType != null)
          return primType.getName(); 
      } 
      if (c == '[') {
        byte b = 1;
        int j = b;
        if (1 < i) {
          j = b;
          if (paramString.charAt(1) == '[')
            j = 1 + 1; 
        } 
        paramString = signatureToName(paramString.substring(j));
        if (paramString != null) {
          StringBuffer stringBuffer = new StringBuffer(50);
          stringBuffer.append(paramString);
          while (true) {
            if (--j >= 0) {
              stringBuffer.append("[]");
              continue;
            } 
            return stringBuffer.toString();
          } 
        } 
        return null;
      } 
      if (c == 'L' && i > 2 && paramString.indexOf(';') == i - 1)
        return paramString.substring(1, i - 1).replace('/', '.'); 
    } 
    return null;
  }
  
  public static PrimType signatureToPrimitive(char paramChar) {
    switch (paramChar) {
      default:
        return null;
      case 'B':
        return byteType;
      case 'C':
        return charType;
      case 'D':
        return doubleType;
      case 'F':
        return floatType;
      case 'S':
        return shortType;
      case 'I':
        return intType;
      case 'J':
        return longType;
      case 'Z':
        return booleanType;
      case 'V':
        break;
    } 
    return voidType;
  }
  
  public static Type signatureToType(String paramString) {
    return signatureToType(paramString, 0, paramString.length());
  }
  
  public static Type signatureToType(String paramString, int paramInt1, int paramInt2) {
    if (paramInt2 != 0) {
      Type type;
      char c = paramString.charAt(paramInt1);
      if (paramInt2 == 1) {
        PrimType primType = signatureToPrimitive(c);
        if (primType != null)
          return primType; 
      } 
      if (c == '[') {
        type = signatureToType(paramString, paramInt1 + 1, paramInt2 - 1);
        return (type != null) ? ArrayType.make(type) : null;
      } 
      if (c == 'L' && paramInt2 > 2 && type.indexOf(';', paramInt1) == paramInt2 - 1 + paramInt1)
        return ClassType.make(type.substring(paramInt1 + 1, paramInt2 - 1 + paramInt1).replace('/', '.')); 
    } 
    return null;
  }
  
  protected static int swappedCompareResult(int paramInt) {
    if (paramInt == 1)
      return -1; 
    int i = paramInt;
    return (paramInt == -1) ? 1 : i;
  }
  
  public abstract Object coerceFromObject(Object paramObject);
  
  public Object coerceToObject(Object paramObject) {
    return paramObject;
  }
  
  public abstract int compare(Type paramType);
  
  public void emitCoerceFromObject(CodeAttr paramCodeAttr) {
    throw new Error("unimplemented emitCoerceFromObject for " + this);
  }
  
  public void emitCoerceToObject(CodeAttr paramCodeAttr) {}
  
  public void emitConvertFromPrimitive(Type paramType, CodeAttr paramCodeAttr) {
    paramType.emitCoerceToObject(paramCodeAttr);
  }
  
  public void emitIsInstance(CodeAttr paramCodeAttr) {
    paramCodeAttr.emitInstanceof(this);
  }
  
  public Type getImplementationType() {
    return this;
  }
  
  public final String getName() {
    return this.this_name;
  }
  
  public Type getRealType() {
    return this;
  }
  
  public Class getReflectClass() {
    return this.reflectClass;
  }
  
  public String getSignature() {
    return this.signature;
  }
  
  public final int getSize() {
    return this.size;
  }
  
  public int getSizeInWords() {
    return (this.size > 4) ? 2 : 1;
  }
  
  public int hashCode() {
    String str = toString();
    return (str == null) ? 0 : str.hashCode();
  }
  
  public boolean isExisting() {
    return true;
  }
  
  public boolean isInstance(Object paramObject) {
    return getReflectClass().isInstance(paramObject);
  }
  
  public final boolean isSubtype(Type paramType) {
    int i = compare(paramType);
    return (i == -1 || i == 0);
  }
  
  public final boolean isVoid() {
    return (this.size == 0);
  }
  
  public Type promote() {
    Type type = this;
    if (this.size < 4)
      type = intType; 
    return type;
  }
  
  protected void setName(String paramString) {
    this.this_name = paramString;
  }
  
  public void setReflectClass(Class paramClass) {
    this.reflectClass = paramClass;
  }
  
  protected void setSignature(String paramString) {
    this.signature = paramString;
  }
  
  public String toString() {
    return "Type " + getName();
  }
  
  static class ClassToTypeMap extends AbstractWeakHashTable<Class, Type> {
    protected Class getKeyFromValue(Type param1Type) {
      return param1Type.reflectClass;
    }
    
    protected boolean matches(Class param1Class1, Class param1Class2) {
      return (param1Class1 == param1Class2);
    }
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/gnu/bytecode/Type.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */