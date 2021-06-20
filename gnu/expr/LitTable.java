package gnu.expr;

import gnu.bytecode.ArrayType;
import gnu.bytecode.ClassType;
import gnu.bytecode.CodeAttr;
import gnu.bytecode.Method;
import gnu.bytecode.ObjectType;
import gnu.bytecode.Type;
import gnu.lists.FString;
import gnu.mapping.Table2D;
import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectOutput;
import java.lang.reflect.Array;
import java.util.IdentityHashMap;

public class LitTable implements ObjectOutput {
  static Table2D staticTable = new Table2D(100);
  
  Compilation comp;
  
  IdentityHashMap literalTable = new IdentityHashMap<Object, Object>(100);
  
  Literal literalsChain;
  
  int literalsCount;
  
  ClassType mainClass;
  
  int stackPointer;
  
  Type[] typeStack = new Type[20];
  
  Object[] valueStack = new Object[20];
  
  public LitTable(Compilation paramCompilation) {
    this.comp = paramCompilation;
    this.mainClass = paramCompilation.mainClass;
  }
  
  private void store(Literal paramLiteral, boolean paramBoolean, CodeAttr paramCodeAttr) {
    if (paramLiteral.field != null) {
      if (!paramBoolean)
        paramCodeAttr.emitDup(paramLiteral.type); 
      paramCodeAttr.emitPutStatic(paramLiteral.field);
    } 
    paramLiteral.flags |= 0x8;
  }
  
  public void close() {}
  
  public void emit() throws IOException {
    Literal literal;
    for (literal = this.literalsChain; literal != null; literal = literal.next)
      writeObject(literal.value); 
    for (literal = this.literalsChain; literal != null; literal = literal.next)
      emit(literal, true); 
    this.literalTable = null;
    this.literalsCount = 0;
  }
  
  void emit(Literal paramLiteral, boolean paramBoolean) {
    boolean bool1;
    boolean bool5;
    CodeAttr codeAttr = this.comp.getCode();
    if (paramLiteral.value == null) {
      if (!paramBoolean)
        codeAttr.emitPushNull(); 
      return;
    } 
    if (paramLiteral.value instanceof String) {
      if (!paramBoolean) {
        codeAttr.emitPushString(paramLiteral.value.toString());
        return;
      } 
      return;
    } 
    if ((paramLiteral.flags & 0x8) != 0) {
      if (!paramBoolean) {
        codeAttr.emitGetStatic(paramLiteral.field);
        return;
      } 
      return;
    } 
    if (paramLiteral.value instanceof Object[]) {
      int i = paramLiteral.argValues.length;
      Type type = ((ArrayType)paramLiteral.type).getComponentType();
      codeAttr.emitPushInt(i);
      codeAttr.emitNewArray(type);
      store(paramLiteral, paramBoolean, codeAttr);
      bool1 = false;
      while (true) {
        if (bool1 < i) {
          Literal literal = (Literal)paramLiteral.argValues[bool1];
          if (literal.value != null) {
            codeAttr.emitDup(type);
            codeAttr.emitPushInt(bool1);
            emit(literal, false);
            codeAttr.emitArrayStore(type);
          } 
          bool1++;
          continue;
        } 
        return;
      } 
    } 
    if (paramLiteral.type instanceof ArrayType) {
      codeAttr.emitPushPrimArray(paramLiteral.value, (ArrayType)paramLiteral.type);
      store(paramLiteral, paramBoolean, codeAttr);
      return;
    } 
    if (paramLiteral.value instanceof Class) {
      String str;
      Class clazz = (Class)paramLiteral.value;
      if (clazz.isPrimitive()) {
        String str1 = clazz.getName();
        str = str1;
        if (str1.equals("int"))
          str = "integer"; 
        codeAttr.emitGetStatic(ClassType.make("java.lang." + Character.toUpperCase(str.charAt(0)) + str.substring(1)).getDeclaredField("TYPE"));
      } else {
        this.comp.loadClassRef((ObjectType)Type.make((Class)str));
      } 
      store(paramLiteral, paramBoolean, codeAttr);
      return;
    } 
    if (paramLiteral.value instanceof ClassType && !((ClassType)paramLiteral.value).isExisting()) {
      this.comp.loadClassRef((ObjectType)paramLiteral.value);
      Method method4 = Compilation.typeType.getDeclaredMethod("valueOf", 1);
      Method method3 = method4;
      if (method4 == null)
        method3 = Compilation.typeType.getDeclaredMethod("make", 1); 
      codeAttr.emitInvokeStatic(method3);
      codeAttr.emitCheckcast((Type)Compilation.typeClassType);
      store(paramLiteral, paramBoolean, codeAttr);
      return;
    } 
    ClassType classType = (ClassType)paramLiteral.type;
    if ((paramLiteral.flags & 0x4) != 0) {
      bool1 = true;
    } else {
      bool1 = false;
    } 
    Method method1 = null;
    Method method2 = null;
    boolean bool2 = false;
    boolean bool4 = false;
    boolean bool3 = bool1;
    if (!bool1) {
      boolean bool;
      method1 = method2;
      if (!(paramLiteral.value instanceof gnu.mapping.Symbol))
        method1 = getMethod(classType, "valueOf", paramLiteral, true); 
      method2 = method1;
      if (method1 == null) {
        method2 = method1;
        if (!(paramLiteral.value instanceof gnu.mapping.Values)) {
          String str = "make";
          if (paramLiteral.value instanceof java.util.regex.Pattern)
            str = "compile"; 
          method2 = getMethod(classType, str, paramLiteral, true);
        } 
      } 
      if (method2 != null) {
        bool = true;
      } else {
        bool = bool4;
        if (paramLiteral.argTypes.length > 0) {
          method2 = getMethod(classType, "<init>", paramLiteral, false);
          bool = bool4;
        } 
      } 
      bool2 = bool;
      method1 = method2;
      bool3 = bool1;
      if (method2 == null) {
        bool3 = true;
        method1 = method2;
        bool2 = bool;
      } 
    } 
    if (bool3)
      method1 = getMethod(classType, "set", paramLiteral, false); 
    if (method1 == null && paramLiteral.argTypes.length > 0)
      error("no method to construct " + paramLiteral.type); 
    if (bool2) {
      putArgs(paramLiteral, codeAttr);
      codeAttr.emitInvokeStatic(method1);
    } else if (bool3) {
      codeAttr.emitNew(classType);
      codeAttr.emitDup((Type)classType);
      codeAttr.emitInvokeSpecial(classType.getDeclaredMethod("<init>", 0));
    } else {
      codeAttr.emitNew(classType);
      codeAttr.emitDup((Type)classType);
      putArgs(paramLiteral, codeAttr);
      codeAttr.emitInvokeSpecial(method1);
    } 
    if (bool2 || paramLiteral.value instanceof gnu.mapping.Values) {
      method2 = null;
    } else {
      method2 = classType.getDeclaredMethod("readResolve", 0);
    } 
    if (method2 != null) {
      codeAttr.emitInvokeVirtual(method2);
      classType.emitCoerceFromObject(codeAttr);
    } 
    if (paramBoolean && (!bool3 || method1 == null)) {
      bool5 = true;
    } else {
      bool5 = false;
    } 
    store(paramLiteral, bool5, codeAttr);
    if (bool3 && method1 != null) {
      if (!paramBoolean)
        codeAttr.emitDup((Type)classType); 
      putArgs(paramLiteral, codeAttr);
      codeAttr.emitInvokeVirtual(method1);
      return;
    } 
  }
  
  void error(String paramString) {
    throw new Error(paramString);
  }
  
  public Literal findLiteral(Object paramObject) {
    // Byte code:
    //   0: aload_1
    //   1: ifnonnull -> 12
    //   4: getstatic gnu/expr/Literal.nullLiteral : Lgnu/expr/Literal;
    //   7: astore #4
    //   9: aload #4
    //   11: areturn
    //   12: aload_0
    //   13: getfield literalTable : Ljava/util/IdentityHashMap;
    //   16: aload_1
    //   17: invokevirtual get : (Ljava/lang/Object;)Ljava/lang/Object;
    //   20: checkcast gnu/expr/Literal
    //   23: astore #5
    //   25: aload #5
    //   27: astore #4
    //   29: aload #5
    //   31: ifnonnull -> 9
    //   34: aload_0
    //   35: getfield comp : Lgnu/expr/Compilation;
    //   38: getfield immediate : Z
    //   41: ifeq -> 54
    //   44: new gnu/expr/Literal
    //   47: dup
    //   48: aload_1
    //   49: aload_0
    //   50: invokespecial <init> : (Ljava/lang/Object;Lgnu/expr/LitTable;)V
    //   53: areturn
    //   54: aload_1
    //   55: invokevirtual getClass : ()Ljava/lang/Class;
    //   58: astore #6
    //   60: aload #6
    //   62: invokestatic make : (Ljava/lang/Class;)Lgnu/bytecode/Type;
    //   65: astore #10
    //   67: getstatic gnu/expr/LitTable.staticTable : Lgnu/mapping/Table2D;
    //   70: astore #9
    //   72: aload #9
    //   74: monitorenter
    //   75: getstatic gnu/expr/LitTable.staticTable : Lgnu/mapping/Table2D;
    //   78: aload_1
    //   79: aconst_null
    //   80: aconst_null
    //   81: invokevirtual get : (Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   84: checkcast gnu/expr/Literal
    //   87: astore #4
    //   89: aload #4
    //   91: ifnull -> 107
    //   94: aload #4
    //   96: astore #5
    //   98: aload #4
    //   100: getfield value : Ljava/lang/Object;
    //   103: aload_1
    //   104: if_acmpeq -> 350
    //   107: aload #4
    //   109: astore #5
    //   111: aload #10
    //   113: instanceof gnu/bytecode/ClassType
    //   116: ifeq -> 350
    //   119: aload #10
    //   121: checkcast gnu/bytecode/ClassType
    //   124: astore #7
    //   126: aload #4
    //   128: astore #5
    //   130: getstatic gnu/expr/LitTable.staticTable : Lgnu/mapping/Table2D;
    //   133: aload #6
    //   135: getstatic java/lang/Boolean.TRUE : Ljava/lang/Boolean;
    //   138: aconst_null
    //   139: invokevirtual get : (Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   142: ifnonnull -> 350
    //   145: getstatic gnu/expr/LitTable.staticTable : Lgnu/mapping/Table2D;
    //   148: aload #6
    //   150: getstatic java/lang/Boolean.TRUE : Ljava/lang/Boolean;
    //   153: aload #6
    //   155: invokevirtual put : (Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   158: pop
    //   159: aload #7
    //   161: invokevirtual getFields : ()Lgnu/bytecode/Field;
    //   164: astore #5
    //   166: aload #5
    //   168: ifnull -> 334
    //   171: aload #5
    //   173: invokevirtual getModifiers : ()I
    //   176: istore_2
    //   177: aload #4
    //   179: astore #7
    //   181: iload_2
    //   182: bipush #25
    //   184: iand
    //   185: bipush #25
    //   187: if_icmpne -> 226
    //   190: aload #5
    //   192: invokevirtual getReflectField : ()Ljava/lang/reflect/Field;
    //   195: aconst_null
    //   196: invokevirtual get : (Ljava/lang/Object;)Ljava/lang/Object;
    //   199: astore #11
    //   201: aload #4
    //   203: astore #7
    //   205: aload #11
    //   207: ifnull -> 226
    //   210: aload #6
    //   212: aload #11
    //   214: invokevirtual isInstance : (Ljava/lang/Object;)Z
    //   217: istore_3
    //   218: iload_3
    //   219: ifne -> 240
    //   222: aload #4
    //   224: astore #7
    //   226: aload #5
    //   228: invokevirtual getNext : ()Lgnu/bytecode/Field;
    //   231: astore #5
    //   233: aload #7
    //   235: astore #4
    //   237: goto -> 166
    //   240: new gnu/expr/Literal
    //   243: dup
    //   244: aload #11
    //   246: aload #5
    //   248: aload_0
    //   249: invokespecial <init> : (Ljava/lang/Object;Lgnu/bytecode/Field;Lgnu/expr/LitTable;)V
    //   252: astore #8
    //   254: getstatic gnu/expr/LitTable.staticTable : Lgnu/mapping/Table2D;
    //   257: aload #11
    //   259: aconst_null
    //   260: aload #8
    //   262: invokevirtual put : (Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   265: pop
    //   266: aload #4
    //   268: astore #7
    //   270: aload_1
    //   271: aload #11
    //   273: if_acmpne -> 226
    //   276: aload #8
    //   278: astore #7
    //   280: goto -> 226
    //   283: astore #7
    //   285: aload_0
    //   286: new java/lang/StringBuilder
    //   289: dup
    //   290: invokespecial <init> : ()V
    //   293: ldc_w 'caught '
    //   296: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   299: aload #7
    //   301: invokevirtual append : (Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   304: ldc_w ' getting static field '
    //   307: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   310: aload #5
    //   312: invokevirtual append : (Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   315: invokevirtual toString : ()Ljava/lang/String;
    //   318: invokevirtual error : (Ljava/lang/String;)V
    //   321: aload #4
    //   323: astore #7
    //   325: goto -> 226
    //   328: astore_1
    //   329: aload #9
    //   331: monitorexit
    //   332: aload_1
    //   333: athrow
    //   334: aload #6
    //   336: invokevirtual getSuperclass : ()Ljava/lang/Class;
    //   339: astore #6
    //   341: aload #6
    //   343: ifnonnull -> 372
    //   346: aload #4
    //   348: astore #5
    //   350: aload #9
    //   352: monitorexit
    //   353: aload #5
    //   355: ifnull -> 385
    //   358: aload_0
    //   359: getfield literalTable : Ljava/util/IdentityHashMap;
    //   362: aload_1
    //   363: aload #5
    //   365: invokevirtual put : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   368: pop
    //   369: aload #5
    //   371: areturn
    //   372: aload #6
    //   374: invokestatic make : (Ljava/lang/Class;)Lgnu/bytecode/Type;
    //   377: checkcast gnu/bytecode/ClassType
    //   380: astore #7
    //   382: goto -> 126
    //   385: new gnu/expr/Literal
    //   388: dup
    //   389: aload_1
    //   390: aload #10
    //   392: aload_0
    //   393: invokespecial <init> : (Ljava/lang/Object;Lgnu/bytecode/Type;Lgnu/expr/LitTable;)V
    //   396: areturn
    // Exception table:
    //   from	to	target	type
    //   75	89	328	finally
    //   98	107	328	finally
    //   111	119	328	finally
    //   119	126	328	finally
    //   130	166	328	finally
    //   171	177	328	finally
    //   190	201	283	java/lang/Throwable
    //   190	201	328	finally
    //   210	218	283	java/lang/Throwable
    //   210	218	328	finally
    //   226	233	328	finally
    //   240	266	283	java/lang/Throwable
    //   240	266	328	finally
    //   285	321	328	finally
    //   329	332	328	finally
    //   334	341	328	finally
    //   350	353	328	finally
    //   372	382	328	finally
  }
  
  public void flush() {}
  
  Method getMethod(ClassType paramClassType, String paramString, Literal paramLiteral, boolean paramBoolean) {
    Object[] arrayOfObject;
    Type[] arrayOfType = paramLiteral.argTypes;
    Method method = paramClassType.getDeclaredMethods();
    int j = arrayOfType.length;
    String str = null;
    long l = 0L;
    int i = 0;
    Object object = null;
    while (method != null) {
      if (!paramString.equals(method.getName())) {
        long l2 = l;
        String str2 = str;
        Object object2 = object;
        int m = i;
        continue;
      } 
      int k = i;
      Object object1 = object;
      String str1 = str;
      long l1 = l;
      if (paramBoolean == method.getStaticFlag()) {
        long l2 = 0L;
        Type[] arrayOfType1 = method.getParameterTypes();
        int m = 0;
        for (int n = 0;; n++)
          m++; 
      } 
      continue;
    } 
    if (i)
      return null; 
    paramString = str;
    if (l != 0L) {
      arrayOfObject = new Object[object.length];
      Type[] arrayOfType1 = new Type[object.length];
      int k = 0;
      for (i = 0;; i++) {
        if (k == j) {
          paramLiteral.argValues = arrayOfObject;
          paramLiteral.argTypes = arrayOfType1;
          return (Method)str;
        } 
        ArrayType arrayType = (ArrayType)object[i];
        if (((1 << i) & l) == 0L) {
          arrayOfObject[i] = paramLiteral.argValues[k];
          arrayOfType1[i] = paramLiteral.argTypes[k];
        } else {
          int n = ((Number)paramLiteral.argValues[k]).intValue();
          paramBoolean = paramClassType.getName().equals("gnu.math.IntNum");
          int m = n;
          if (paramBoolean)
            m = n + Integer.MIN_VALUE; 
          Type type = ((ArrayType)arrayType).getComponentType();
          arrayOfType1[i] = (Type)arrayType;
          arrayOfObject[i] = Array.newInstance(type.getReflectClass(), m);
          Object[] arrayOfObject1 = paramLiteral.argValues;
          if (paramBoolean) {
            int[] arrayOfInt = (int[])arrayOfObject[i];
            for (n = m; n > 0; n--)
              arrayOfInt[m - n] = ((Integer)arrayOfObject1[k + n]).intValue(); 
          } else {
            n = m;
            while (true) {
              if (--n >= 0) {
                Array.set(arrayOfObject[i], n, arrayOfObject1[k + 1 + n]);
                continue;
              } 
              break;
            } 
          } 
          Literal literal = new Literal(arrayOfObject[i], (Type)arrayType);
          if (type instanceof ObjectType)
            literal.argValues = (Object[])arrayOfObject[i]; 
          arrayOfObject[i] = literal;
          k += m;
        } 
        k++;
      } 
    } 
    return (Method)arrayOfObject;
  }
  
  void push(Object paramObject, Type paramType) {
    if (this.stackPointer >= this.valueStack.length) {
      Object[] arrayOfObject = new Object[this.valueStack.length * 2];
      Type[] arrayOfType = new Type[this.typeStack.length * 2];
      System.arraycopy(this.valueStack, 0, arrayOfObject, 0, this.stackPointer);
      System.arraycopy(this.typeStack, 0, arrayOfType, 0, this.stackPointer);
      this.valueStack = arrayOfObject;
      this.typeStack = arrayOfType;
    } 
    this.valueStack[this.stackPointer] = paramObject;
    this.typeStack[this.stackPointer] = paramType;
    this.stackPointer++;
  }
  
  void putArgs(Literal paramLiteral, CodeAttr paramCodeAttr) {
    Type[] arrayOfType = paramLiteral.argTypes;
    int j = arrayOfType.length;
    for (int i = 0; i < j; i++) {
      Object object = paramLiteral.argValues[i];
      if (object instanceof Literal) {
        emit((Literal)object, false);
      } else {
        this.comp.compileConstant(object, new StackTarget(arrayOfType[i]));
      } 
    } 
  }
  
  public void write(int paramInt) throws IOException {
    error("cannot handle call to write(int) when externalizing literal");
  }
  
  public void write(byte[] paramArrayOfbyte) throws IOException {
    error("cannot handle call to write(byte[]) when externalizing literal");
  }
  
  public void write(byte[] paramArrayOfbyte, int paramInt1, int paramInt2) throws IOException {
    error("cannot handle call to write(byte[],int,int) when externalizing literal");
  }
  
  public void writeBoolean(boolean paramBoolean) {
    push(new Boolean(paramBoolean), (Type)Type.booleanType);
  }
  
  public void writeByte(int paramInt) {
    push(new Byte((byte)paramInt), (Type)Type.byteType);
  }
  
  public void writeBytes(String paramString) throws IOException {
    error("cannot handle call to writeBytes(String) when externalizing literal");
  }
  
  public void writeChar(int paramInt) {
    push(new Character((char)paramInt), (Type)Type.charType);
  }
  
  public void writeChars(String paramString) {
    push(paramString, (Type)Type.string_type);
  }
  
  public void writeDouble(double paramDouble) {
    push(new Double(paramDouble), (Type)Type.doubleType);
  }
  
  public void writeFloat(float paramFloat) {
    push(new Float(paramFloat), (Type)Type.floatType);
  }
  
  public void writeInt(int paramInt) {
    push(new Integer(paramInt), (Type)Type.intType);
  }
  
  public void writeLong(long paramLong) {
    push(new Long(paramLong), (Type)Type.longType);
  }
  
  public void writeObject(Object paramObject) throws IOException {
    Literal literal = findLiteral(paramObject);
    if ((literal.flags & 0x3) != 0) {
      if (literal.field == null && paramObject != null && !(paramObject instanceof String))
        literal.assign(this); 
      if ((literal.flags & 0x2) == 0)
        literal.flags |= 0x4; 
    } else {
      literal.flags |= 0x1;
      int j = this.stackPointer;
      if (paramObject instanceof FString && ((FString)paramObject).size() < 65535) {
        push(paramObject.toString(), (Type)Type.string_type);
      } else if (paramObject instanceof Externalizable) {
        ((Externalizable)paramObject).writeExternal(this);
      } else {
        if (paramObject instanceof Object[]) {
          paramObject = paramObject;
          for (int k = 0;; k = this.stackPointer - j) {
            if (k < paramObject.length) {
              writeObject(paramObject[k]);
              k++;
              continue;
            } 
          } 
        } 
        if (paramObject != null && !(paramObject instanceof String) && !(literal.type instanceof ArrayType))
          if (paramObject instanceof java.math.BigInteger) {
            writeChars(paramObject.toString());
          } else if (paramObject instanceof java.math.BigDecimal) {
            paramObject = paramObject;
            writeObject(paramObject.unscaledValue());
            writeInt(paramObject.scale());
          } else if (paramObject instanceof Integer) {
            push(paramObject, (Type)Type.intType);
          } else if (paramObject instanceof Short) {
            push(paramObject, (Type)Type.shortType);
          } else if (paramObject instanceof Byte) {
            push(paramObject, (Type)Type.byteType);
          } else if (paramObject instanceof Long) {
            push(paramObject, (Type)Type.longType);
          } else if (paramObject instanceof Double) {
            push(paramObject, (Type)Type.doubleType);
          } else if (paramObject instanceof Float) {
            push(paramObject, (Type)Type.floatType);
          } else if (paramObject instanceof Character) {
            push(paramObject, (Type)Type.charType);
          } else if (paramObject instanceof Class) {
            push(paramObject, (Type)Type.java_lang_Class_type);
          } else if (paramObject instanceof java.util.regex.Pattern) {
            paramObject = paramObject;
            push(paramObject.pattern(), (Type)Type.string_type);
            push(Integer.valueOf(paramObject.flags()), (Type)Type.intType);
          } else {
            error(paramObject.getClass().getName() + " does not implement Externalizable");
          }  
      } 
      int i = this.stackPointer - j;
    } 
    push(literal, literal.type);
  }
  
  public void writeShort(int paramInt) {
    push(new Short((short)paramInt), (Type)Type.shortType);
  }
  
  public void writeUTF(String paramString) {
    push(paramString, (Type)Type.string_type);
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/gnu/expr/LitTable.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */