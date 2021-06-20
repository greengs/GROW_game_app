package gnu.bytecode;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.Externalizable;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.io.ObjectStreamException;
import java.io.OutputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;

public class ClassType extends ObjectType implements AttrContainer, Externalizable, Member {
  public static final int JDK_1_1_VERSION = 2949123;
  
  public static final int JDK_1_2_VERSION = 3014656;
  
  public static final int JDK_1_3_VERSION = 3080192;
  
  public static final int JDK_1_4_VERSION = 3145728;
  
  public static final int JDK_1_5_VERSION = 3211264;
  
  public static final int JDK_1_6_VERSION = 3276800;
  
  public static final int JDK_1_7_VERSION = 3342336;
  
  public static final ClassType[] noClasses = new ClassType[0];
  
  int Code_name_index;
  
  int ConstantValue_name_index;
  
  int LineNumberTable_name_index;
  
  int LocalVariableTable_name_index;
  
  int access_flags;
  
  Attribute attributes;
  
  int classfileFormatVersion = 2949123;
  
  ConstantPool constants;
  
  public Method constructor;
  
  boolean emitDebugInfo = true;
  
  Member enclosingMember;
  
  Field fields;
  
  int fields_count;
  
  ClassType firstInnerClass;
  
  int[] interfaceIndexes;
  
  ClassType[] interfaces;
  
  Field last_field;
  
  Method last_method;
  
  Method methods;
  
  int methods_count;
  
  ClassType nextInnerClass;
  
  SourceDebugExtAttr sourceDbgExt;
  
  ClassType superClass;
  
  int superClassIndex = -1;
  
  int thisClassIndex;
  
  public ClassType() {}
  
  public ClassType(String paramString) {
    setName(paramString);
  }
  
  public static ClassType make(String paramString) {
    return (ClassType)Type.getType(paramString);
  }
  
  public static ClassType make(String paramString, ClassType paramClassType) {
    ClassType classType = make(paramString);
    if (classType.superClass == null)
      classType.setSuper(paramClassType); 
    return classType;
  }
  
  public static byte[] to_utf8(String paramString) {
    if (paramString == null)
      return null; 
    int k = paramString.length();
    int i = 0;
    int j;
    for (j = 0; j < k; j++) {
      char c = paramString.charAt(j);
      if (c > '\000' && c <= '') {
        i++;
      } else if (c <= '߿') {
        i += 2;
      } else {
        i += 3;
      } 
    } 
    byte[] arrayOfByte = new byte[i];
    j = 0;
    i = 0;
    while (true) {
      byte[] arrayOfByte1 = arrayOfByte;
      if (j < k) {
        char c = paramString.charAt(j);
        if (c > '\000' && c <= '') {
          int m = i + 1;
          arrayOfByte[i] = (byte)c;
          i = m;
        } else if (c <= '߿') {
          int m = i + 1;
          arrayOfByte[i] = (byte)(c >> 6 & 0x1F | 0xC0);
          arrayOfByte[m] = (byte)(c >> 0 & 0x3F | 0x80);
          i = m + 1;
        } else {
          int m = i + 1;
          arrayOfByte[i] = (byte)(c >> 12 & 0xF | 0xE0);
          int n = m + 1;
          arrayOfByte[m] = (byte)(c >> 6 & 0x3F | 0x80);
          i = n + 1;
          arrayOfByte[n] = (byte)(c >> 0 & 0x3F | 0x80);
        } 
        j++;
        continue;
      } 
      return arrayOfByte1;
    } 
  }
  
  void addEnclosingMember() {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield flags : I
    //   6: istore_1
    //   7: iload_1
    //   8: bipush #24
    //   10: iand
    //   11: bipush #16
    //   13: if_icmpeq -> 19
    //   16: aload_0
    //   17: monitorexit
    //   18: return
    //   19: aload_0
    //   20: invokevirtual getReflectClass : ()Ljava/lang/Class;
    //   23: astore_3
    //   24: aload_0
    //   25: aload_0
    //   26: getfield flags : I
    //   29: bipush #8
    //   31: ior
    //   32: putfield flags : I
    //   35: aload_3
    //   36: invokevirtual getEnclosingClass : ()Ljava/lang/Class;
    //   39: astore_2
    //   40: aload_2
    //   41: ifnull -> 16
    //   44: aload_3
    //   45: invokevirtual isMemberClass : ()Z
    //   48: ifne -> 101
    //   51: aload_3
    //   52: invokevirtual getEnclosingMethod : ()Ljava/lang/reflect/Method;
    //   55: astore #4
    //   57: aload #4
    //   59: ifnull -> 80
    //   62: aload_0
    //   63: aload_0
    //   64: aload #4
    //   66: invokevirtual addMethod : (Ljava/lang/reflect/Method;)Lgnu/bytecode/Method;
    //   69: putfield enclosingMember : Lgnu/bytecode/Member;
    //   72: goto -> 16
    //   75: astore_2
    //   76: aload_0
    //   77: monitorexit
    //   78: aload_2
    //   79: athrow
    //   80: aload_3
    //   81: invokevirtual getEnclosingConstructor : ()Ljava/lang/reflect/Constructor;
    //   84: astore_3
    //   85: aload_3
    //   86: ifnull -> 101
    //   89: aload_0
    //   90: aload_0
    //   91: aload_3
    //   92: invokevirtual addMethod : (Ljava/lang/reflect/Constructor;)Lgnu/bytecode/Method;
    //   95: putfield enclosingMember : Lgnu/bytecode/Member;
    //   98: goto -> 16
    //   101: aload_0
    //   102: aload_2
    //   103: invokestatic make : (Ljava/lang/Class;)Lgnu/bytecode/Type;
    //   106: checkcast gnu/bytecode/ClassType
    //   109: putfield enclosingMember : Lgnu/bytecode/Member;
    //   112: goto -> 16
    // Exception table:
    //   from	to	target	type
    //   2	7	75	finally
    //   19	40	75	finally
    //   44	57	75	finally
    //   62	72	75	finally
    //   80	85	75	finally
    //   89	98	75	finally
    //   101	112	75	finally
  }
  
  public Field addField() {
    return new Field(this);
  }
  
  public Field addField(String paramString) {
    Field field = new Field(this);
    field.setName(paramString);
    return field;
  }
  
  public final Field addField(String paramString, Type paramType) {
    Field field = new Field(this);
    field.setName(paramString);
    field.setType(paramType);
    return field;
  }
  
  public final Field addField(String paramString, Type paramType, int paramInt) {
    Field field = addField(paramString, paramType);
    field.flags = paramInt;
    return field;
  }
  
  public void addFields() {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: invokevirtual getReflectClass : ()Ljava/lang/Class;
    //   6: astore #4
    //   8: aload #4
    //   10: invokevirtual getDeclaredFields : ()[Ljava/lang/reflect/Field;
    //   13: astore_3
    //   14: aload_3
    //   15: arraylength
    //   16: istore_2
    //   17: iconst_0
    //   18: istore_1
    //   19: iload_1
    //   20: iload_2
    //   21: if_icmpge -> 93
    //   24: aload_3
    //   25: iload_1
    //   26: aaload
    //   27: astore #4
    //   29: ldc 'this$0'
    //   31: aload #4
    //   33: invokevirtual getName : ()Ljava/lang/String;
    //   36: invokevirtual equals : (Ljava/lang/Object;)Z
    //   39: ifeq -> 53
    //   42: aload_0
    //   43: aload_0
    //   44: getfield flags : I
    //   47: bipush #32
    //   49: ior
    //   50: putfield flags : I
    //   53: aload_0
    //   54: aload #4
    //   56: invokevirtual getName : ()Ljava/lang/String;
    //   59: aload #4
    //   61: invokevirtual getType : ()Ljava/lang/Class;
    //   64: invokestatic make : (Ljava/lang/Class;)Lgnu/bytecode/Type;
    //   67: aload #4
    //   69: invokevirtual getModifiers : ()I
    //   72: invokevirtual addField : (Ljava/lang/String;Lgnu/bytecode/Type;I)Lgnu/bytecode/Field;
    //   75: pop
    //   76: iload_1
    //   77: iconst_1
    //   78: iadd
    //   79: istore_1
    //   80: goto -> 19
    //   83: astore_3
    //   84: aload #4
    //   86: invokevirtual getFields : ()[Ljava/lang/reflect/Field;
    //   89: astore_3
    //   90: goto -> 14
    //   93: aload_0
    //   94: aload_0
    //   95: getfield flags : I
    //   98: iconst_1
    //   99: ior
    //   100: putfield flags : I
    //   103: aload_0
    //   104: monitorexit
    //   105: return
    //   106: astore_3
    //   107: aload_0
    //   108: monitorexit
    //   109: aload_3
    //   110: athrow
    // Exception table:
    //   from	to	target	type
    //   2	8	106	finally
    //   8	14	83	java/lang/SecurityException
    //   8	14	106	finally
    //   14	17	106	finally
    //   29	53	106	finally
    //   53	76	106	finally
    //   84	90	106	finally
    //   93	103	106	finally
  }
  
  public void addInterface(ClassType paramClassType) {
    int i;
    if (this.interfaces == null || this.interfaces.length == 0) {
      i = 0;
      this.interfaces = new ClassType[1];
    } else {
      i = this.interfaces.length;
      int j = i;
      while (true) {
        int k = j - 1;
        if (k >= 0) {
          j = k;
          if (this.interfaces[k] == paramClassType)
            return; 
          continue;
        } 
        ClassType[] arrayOfClassType = new ClassType[i + 1];
        System.arraycopy(this.interfaces, 0, arrayOfClassType, 0, i);
        this.interfaces = arrayOfClassType;
        this.interfaces[i] = paramClassType;
        return;
      } 
    } 
    this.interfaces[i] = paramClassType;
  }
  
  public void addMemberClass(ClassType paramClassType) {
    ClassType classType2 = null;
    for (ClassType classType1 = this.firstInnerClass; classType1 != null; classType1 = classType1.nextInnerClass) {
      if (classType1 == paramClassType)
        return; 
      classType2 = classType1;
    } 
    if (classType2 == null) {
      this.firstInnerClass = paramClassType;
      return;
    } 
    classType2.nextInnerClass = paramClassType;
  }
  
  public void addMemberClasses() {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield flags : I
    //   6: istore_1
    //   7: iload_1
    //   8: bipush #20
    //   10: iand
    //   11: bipush #16
    //   13: if_icmpeq -> 19
    //   16: aload_0
    //   17: monitorexit
    //   18: return
    //   19: aload_0
    //   20: invokevirtual getReflectClass : ()Ljava/lang/Class;
    //   23: astore_3
    //   24: aload_0
    //   25: aload_0
    //   26: getfield flags : I
    //   29: iconst_4
    //   30: ior
    //   31: putfield flags : I
    //   34: aload_3
    //   35: invokevirtual getClasses : ()[Ljava/lang/Class;
    //   38: astore_3
    //   39: aload_3
    //   40: arraylength
    //   41: istore_2
    //   42: iload_2
    //   43: ifle -> 16
    //   46: iconst_0
    //   47: istore_1
    //   48: iload_1
    //   49: iload_2
    //   50: if_icmpge -> 16
    //   53: aload_0
    //   54: aload_3
    //   55: iload_1
    //   56: aaload
    //   57: invokestatic make : (Ljava/lang/Class;)Lgnu/bytecode/Type;
    //   60: checkcast gnu/bytecode/ClassType
    //   63: invokevirtual addMemberClass : (Lgnu/bytecode/ClassType;)V
    //   66: iload_1
    //   67: iconst_1
    //   68: iadd
    //   69: istore_1
    //   70: goto -> 48
    //   73: astore_3
    //   74: aload_0
    //   75: monitorexit
    //   76: aload_3
    //   77: athrow
    // Exception table:
    //   from	to	target	type
    //   2	7	73	finally
    //   19	42	73	finally
    //   53	66	73	finally
  }
  
  Method addMethod() {
    return new Method(this, 0);
  }
  
  public Method addMethod(String paramString) {
    return addMethod(paramString, 0);
  }
  
  public Method addMethod(String paramString, int paramInt) {
    Method method = new Method(this, paramInt);
    method.setName(paramString);
    return method;
  }
  
  public Method addMethod(String paramString, int paramInt, Type[] paramArrayOfType, Type paramType) {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: aload_1
    //   4: aload_3
    //   5: invokevirtual getDeclaredMethod : (Ljava/lang/String;[Lgnu/bytecode/Type;)Lgnu/bytecode/Method;
    //   8: astore #6
    //   10: aload #6
    //   12: ifnull -> 50
    //   15: aload #4
    //   17: aload #6
    //   19: invokevirtual getReturnType : ()Lgnu/bytecode/Type;
    //   22: invokevirtual equals : (Ljava/lang/Object;)Z
    //   25: ifeq -> 50
    //   28: aload #6
    //   30: getfield access_flags : I
    //   33: istore #5
    //   35: iload #5
    //   37: iload_2
    //   38: iand
    //   39: iload_2
    //   40: if_icmpne -> 50
    //   43: aload #6
    //   45: astore_1
    //   46: aload_0
    //   47: monitorexit
    //   48: aload_1
    //   49: areturn
    //   50: aload_0
    //   51: aload_1
    //   52: iload_2
    //   53: invokevirtual addMethod : (Ljava/lang/String;I)Lgnu/bytecode/Method;
    //   56: astore_1
    //   57: aload_1
    //   58: aload_3
    //   59: putfield arg_types : [Lgnu/bytecode/Type;
    //   62: aload_1
    //   63: aload #4
    //   65: putfield return_type : Lgnu/bytecode/Type;
    //   68: goto -> 46
    //   71: astore_1
    //   72: aload_0
    //   73: monitorexit
    //   74: aload_1
    //   75: athrow
    // Exception table:
    //   from	to	target	type
    //   2	10	71	finally
    //   15	35	71	finally
    //   50	68	71	finally
  }
  
  public Method addMethod(String paramString1, String paramString2, int paramInt) {
    Method method = addMethod(paramString1, paramInt);
    method.setSignature(paramString2);
    return method;
  }
  
  public Method addMethod(String paramString, Type[] paramArrayOfType, Type paramType, int paramInt) {
    return addMethod(paramString, paramInt, paramArrayOfType, paramType);
  }
  
  public Method addMethod(Constructor paramConstructor) {
    Class[] arrayOfClass = paramConstructor.getParameterTypes();
    int j = paramConstructor.getModifiers();
    int i = arrayOfClass.length;
    Type[] arrayOfType = new Type[i];
    while (true) {
      if (--i >= 0) {
        arrayOfType[i] = Type.make(arrayOfClass[i]);
        continue;
      } 
      return addMethod("<init>", j, arrayOfType, Type.voidType);
    } 
  }
  
  public Method addMethod(Method paramMethod) {
    int j = paramMethod.getModifiers();
    Class[] arrayOfClass = paramMethod.getParameterTypes();
    int i = arrayOfClass.length;
    Type[] arrayOfType = new Type[i];
    while (true) {
      if (--i >= 0) {
        arrayOfType[i] = Type.make(arrayOfClass[i]);
        continue;
      } 
      Type type = Type.make(paramMethod.getReturnType());
      return addMethod(paramMethod.getName(), j, arrayOfType, type);
    } 
  }
  
  public void addMethods(Class paramClass) {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: aload_0
    //   4: getfield flags : I
    //   7: iconst_2
    //   8: ior
    //   9: putfield flags : I
    //   12: aload_1
    //   13: invokevirtual getDeclaredMethods : ()[Ljava/lang/reflect/Method;
    //   16: astore #4
    //   18: aload #4
    //   20: arraylength
    //   21: istore_3
    //   22: iconst_0
    //   23: istore_2
    //   24: iload_2
    //   25: iload_3
    //   26: if_icmpge -> 76
    //   29: aload #4
    //   31: iload_2
    //   32: aaload
    //   33: astore #5
    //   35: aload #5
    //   37: invokevirtual getDeclaringClass : ()Ljava/lang/Class;
    //   40: aload_1
    //   41: invokevirtual equals : (Ljava/lang/Object;)Z
    //   44: ifne -> 61
    //   47: goto -> 138
    //   50: astore #4
    //   52: aload_1
    //   53: invokevirtual getMethods : ()[Ljava/lang/reflect/Method;
    //   56: astore #4
    //   58: goto -> 18
    //   61: aload_0
    //   62: aload #5
    //   64: invokevirtual addMethod : (Ljava/lang/reflect/Method;)Lgnu/bytecode/Method;
    //   67: pop
    //   68: goto -> 138
    //   71: astore_1
    //   72: aload_0
    //   73: monitorexit
    //   74: aload_1
    //   75: athrow
    //   76: aload_1
    //   77: invokevirtual getDeclaredConstructors : ()[Ljava/lang/reflect/Constructor;
    //   80: astore #4
    //   82: aload #4
    //   84: arraylength
    //   85: istore_3
    //   86: iconst_0
    //   87: istore_2
    //   88: iload_2
    //   89: iload_3
    //   90: if_icmpge -> 135
    //   93: aload #4
    //   95: iload_2
    //   96: aaload
    //   97: astore #5
    //   99: aload #5
    //   101: invokevirtual getDeclaringClass : ()Ljava/lang/Class;
    //   104: aload_1
    //   105: invokevirtual equals : (Ljava/lang/Object;)Z
    //   108: ifne -> 125
    //   111: goto -> 145
    //   114: astore #4
    //   116: aload_1
    //   117: invokevirtual getConstructors : ()[Ljava/lang/reflect/Constructor;
    //   120: astore #4
    //   122: goto -> 82
    //   125: aload_0
    //   126: aload #5
    //   128: invokevirtual addMethod : (Ljava/lang/reflect/Constructor;)Lgnu/bytecode/Method;
    //   131: pop
    //   132: goto -> 145
    //   135: aload_0
    //   136: monitorexit
    //   137: return
    //   138: iload_2
    //   139: iconst_1
    //   140: iadd
    //   141: istore_2
    //   142: goto -> 24
    //   145: iload_2
    //   146: iconst_1
    //   147: iadd
    //   148: istore_2
    //   149: goto -> 88
    // Exception table:
    //   from	to	target	type
    //   2	12	71	finally
    //   12	18	50	java/lang/SecurityException
    //   12	18	71	finally
    //   18	22	71	finally
    //   35	47	71	finally
    //   52	58	71	finally
    //   61	68	71	finally
    //   76	82	114	java/lang/SecurityException
    //   76	82	71	finally
    //   82	86	71	finally
    //   99	111	71	finally
    //   116	122	71	finally
    //   125	132	71	finally
  }
  
  public final void addModifiers(int paramInt) {
    this.access_flags |= paramInt;
  }
  
  public Method checkSingleAbstractMethod() {
    Method[] arrayOfMethod = getAbstractMethods();
    int j = arrayOfMethod.length;
    Method method = null;
    int i = 0;
    while (true) {
      Method method1 = method;
      if (i < j) {
        method1 = arrayOfMethod[i];
        Method method2 = getMethod(method1.getName(), method1.getParameterTypes());
        if (method2 == null || method2.isAbstract()) {
          if (method != null)
            return null; 
          method = method1;
        } 
        i++;
        continue;
      } 
      return method1;
    } 
  }
  
  public void cleanupAfterCompilation() {
    for (Method method = this.methods; method != null; method = method.getNext())
      method.cleanupAfterCompilation(); 
    this.constants = null;
    this.attributes = null;
    this.sourceDbgExt = null;
  }
  
  public int compare(Type paramType) {
    byte b = -1;
    if (paramType != nullType) {
      if (!(paramType instanceof ClassType))
        return swappedCompareResult(paramType.compare(this)); 
      String str = getName();
      if (str != null && str.equals(paramType.getName()))
        return 0; 
      paramType = paramType;
      if (isSubclass((ClassType)paramType))
        return -1; 
      if (!paramType.isSubclass(this)) {
        if (this == toStringType) {
          if (paramType != Type.javalangObjectType)
            b = 1; 
          return b;
        } 
        if (paramType == toStringType)
          return (this != Type.javalangObjectType) ? -1 : 1; 
        if (isInterface()) {
          if (paramType != Type.javalangObjectType)
            b = -2; 
          return b;
        } 
        return paramType.isInterface() ? ((this != Type.javalangObjectType) ? -2 : 1) : -3;
      } 
    } 
    return 1;
  }
  
  public final int countMethods(Filter paramFilter, int paramInt) {
    Vector<Method> vector = new Vector();
    getMethods(paramFilter, paramInt, vector);
    return vector.size();
  }
  
  public void doFixups() {
    if (this.constants == null)
      this.constants = new ConstantPool(); 
    if (this.thisClassIndex == 0)
      this.thisClassIndex = (this.constants.addClass(this)).index; 
    if (this.superClass == this)
      setSuper((ClassType)null); 
    if (this.superClassIndex < 0) {
      int j;
      if (this.superClass == null) {
        j = 0;
      } else {
        j = (this.constants.addClass(this.superClass)).index;
      } 
      this.superClassIndex = j;
    } 
    if (this.interfaces != null && this.interfaceIndexes == null) {
      int k = this.interfaces.length;
      this.interfaceIndexes = new int[k];
      for (int j = 0; j < k; j++)
        this.interfaceIndexes[j] = (this.constants.addClass(this.interfaces[j])).index; 
    } 
    for (Field field = this.fields; field != null; field = field.next)
      field.assign_constants(this); 
    for (Method method = this.methods; method != null; method = method.next)
      method.assignConstants(); 
    if (this.enclosingMember instanceof Method) {
      EnclosingMethodAttr enclosingMethodAttr2 = EnclosingMethodAttr.getFirstEnclosingMethod(getAttributes());
      EnclosingMethodAttr enclosingMethodAttr1 = enclosingMethodAttr2;
      if (enclosingMethodAttr2 == null)
        enclosingMethodAttr1 = new EnclosingMethodAttr(this); 
      enclosingMethodAttr1.method = (Method)this.enclosingMember;
    } else if (this.enclosingMember instanceof ClassType) {
      this.constants.addClass((ClassType)this.enclosingMember);
    } 
    for (ClassType classType = this.firstInnerClass; classType != null; classType = classType.nextInnerClass)
      this.constants.addClass(classType); 
    InnerClassesAttr innerClassesAttr = InnerClassesAttr.getFirstInnerClasses(getAttributes());
    if (innerClassesAttr != null)
      innerClassesAttr.setSkipped(true); 
    Attribute.assignConstants(this, this);
    int i = 1;
    while (i <= this.constants.count) {
      InnerClassesAttr innerClassesAttr1;
      CpoolEntry cpoolEntry = this.constants.pool[i];
      if (!(cpoolEntry instanceof CpoolClass)) {
        innerClassesAttr1 = innerClassesAttr;
      } else {
        CpoolClass cpoolClass = (CpoolClass)innerClassesAttr1;
        innerClassesAttr1 = innerClassesAttr;
        if (cpoolClass.clas instanceof ClassType) {
          innerClassesAttr1 = innerClassesAttr;
          if (((ClassType)cpoolClass.clas).getEnclosingMember() != null) {
            innerClassesAttr1 = innerClassesAttr;
            if (innerClassesAttr == null)
              innerClassesAttr1 = new InnerClassesAttr(this); 
            innerClassesAttr1.addClass(cpoolClass, this);
          } 
        } 
      } 
      i++;
      innerClassesAttr = innerClassesAttr1;
    } 
    if (innerClassesAttr != null) {
      innerClassesAttr.setSkipped(false);
      innerClassesAttr.assignConstants(this);
    } 
  }
  
  public Method[] getAbstractMethods() {
    return getMethods(AbstractMethodFilter.instance, 2);
  }
  
  public final Attribute getAttributes() {
    return this.attributes;
  }
  
  public short getClassfileMajorVersion() {
    return (short)(this.classfileFormatVersion >> 16);
  }
  
  public short getClassfileMinorVersion() {
    return (short)(this.classfileFormatVersion & 0xFFFF);
  }
  
  public int getClassfileVersion() {
    return this.classfileFormatVersion;
  }
  
  public final CpoolEntry getConstant(int paramInt) {
    return (this.constants == null || this.constants.pool == null || paramInt > this.constants.count) ? null : this.constants.pool[paramInt];
  }
  
  public final ConstantPool getConstants() {
    return this.constants;
  }
  
  public ClassType getDeclaredClass(String paramString) {
    addMemberClasses();
    for (ClassType classType = this.firstInnerClass; classType != null; classType = classType.nextInnerClass) {
      if (paramString.equals(classType.getSimpleName()))
        return classType; 
    } 
    return null;
  }
  
  public Field getDeclaredField(String paramString) {
    for (Field field = getFields(); field != null; field = field.next) {
      if (paramString.equals(field.name))
        return field; 
    } 
    return null;
  }
  
  public Method getDeclaredMethod(String paramString, int paramInt) {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aconst_null
    //   3: astore #5
    //   5: ldc_w '<init>'
    //   8: aload_1
    //   9: invokevirtual equals : (Ljava/lang/Object;)Z
    //   12: ifeq -> 137
    //   15: aload_0
    //   16: invokevirtual hasOuterLink : ()Z
    //   19: ifeq -> 137
    //   22: iconst_1
    //   23: istore_3
    //   24: aload_0
    //   25: invokevirtual getDeclaredMethods : ()Lgnu/bytecode/Method;
    //   28: astore #4
    //   30: aload #4
    //   32: ifnull -> 160
    //   35: aload #5
    //   37: astore #6
    //   39: aload_1
    //   40: aload #4
    //   42: invokevirtual getName : ()Ljava/lang/String;
    //   45: invokevirtual equals : (Ljava/lang/Object;)Z
    //   48: ifeq -> 146
    //   51: aload #5
    //   53: astore #6
    //   55: iload_2
    //   56: iload_3
    //   57: iadd
    //   58: aload #4
    //   60: invokevirtual getParameterTypes : ()[Lgnu/bytecode/Type;
    //   63: arraylength
    //   64: if_icmpne -> 146
    //   67: aload #5
    //   69: ifnull -> 142
    //   72: new java/lang/Error
    //   75: dup
    //   76: new java/lang/StringBuilder
    //   79: dup
    //   80: invokespecial <init> : ()V
    //   83: ldc_w 'ambiguous call to getDeclaredMethod("'
    //   86: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   89: aload_1
    //   90: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   93: ldc_w '", '
    //   96: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   99: iload_2
    //   100: invokevirtual append : (I)Ljava/lang/StringBuilder;
    //   103: ldc_w ')\\n - '
    //   106: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   109: aload #5
    //   111: invokevirtual append : (Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   114: ldc_w '\\n - '
    //   117: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   120: aload #4
    //   122: invokevirtual append : (Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   125: invokevirtual toString : ()Ljava/lang/String;
    //   128: invokespecial <init> : (Ljava/lang/String;)V
    //   131: athrow
    //   132: astore_1
    //   133: aload_0
    //   134: monitorexit
    //   135: aload_1
    //   136: athrow
    //   137: iconst_0
    //   138: istore_3
    //   139: goto -> 24
    //   142: aload #4
    //   144: astore #6
    //   146: aload #4
    //   148: getfield next : Lgnu/bytecode/Method;
    //   151: astore #4
    //   153: aload #6
    //   155: astore #5
    //   157: goto -> 30
    //   160: aload_0
    //   161: monitorexit
    //   162: aload #5
    //   164: areturn
    // Exception table:
    //   from	to	target	type
    //   5	22	132	finally
    //   24	30	132	finally
    //   39	51	132	finally
    //   55	67	132	finally
    //   72	132	132	finally
    //   146	153	132	finally
  }
  
  public Method getDeclaredMethod(String paramString, Type[] paramArrayOfType) {
    byte b;
    if ("<init>".equals(paramString) && hasOuterLink()) {
      b = 1;
    } else {
      b = 0;
    } 
    for (Method method = getDeclaredMethods(); method != null; method = method.next) {
      if (paramString.equals(method.getName())) {
        Type[] arrayOfType = method.getParameterTypes();
        if (paramArrayOfType == null || (paramArrayOfType == arrayOfType && !b))
          return method; 
        int i = paramArrayOfType.length;
        if (i == arrayOfType.length - b) {
          int j;
          while (true) {
            j = i - 1;
            if (j >= 0) {
              Type type1 = arrayOfType[j + b];
              Type type2 = paramArrayOfType[j];
              i = j;
              if (type1 != type2) {
                i = j;
                if (type2 != null) {
                  i = j;
                  if (!type1.getSignature().equals(type2.getSignature()))
                    break; 
                } 
              } 
              continue;
            } 
            break;
          } 
          if (j < 0)
            return method; 
        } 
      } 
    } 
    return null;
  }
  
  public final Method getDeclaredMethods() {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield flags : I
    //   6: bipush #18
    //   8: iand
    //   9: bipush #16
    //   11: if_icmpne -> 22
    //   14: aload_0
    //   15: aload_0
    //   16: invokevirtual getReflectClass : ()Ljava/lang/Class;
    //   19: invokevirtual addMethods : (Ljava/lang/Class;)V
    //   22: aload_0
    //   23: getfield methods : Lgnu/bytecode/Method;
    //   26: astore_1
    //   27: aload_0
    //   28: monitorexit
    //   29: aload_1
    //   30: areturn
    //   31: astore_1
    //   32: aload_0
    //   33: monitorexit
    //   34: aload_1
    //   35: athrow
    // Exception table:
    //   from	to	target	type
    //   2	22	31	finally
    //   22	27	31	finally
  }
  
  public ClassType getDeclaringClass() {
    addEnclosingMember();
    return (this.enclosingMember instanceof ClassType) ? (ClassType)this.enclosingMember : null;
  }
  
  public Member getEnclosingMember() {
    addEnclosingMember();
    return this.enclosingMember;
  }
  
  public Field getField(String paramString) {
    return getField(paramString, 1);
  }
  
  public Field getField(String paramString, int paramInt) {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: astore #4
    //   5: aload #4
    //   7: aload_1
    //   8: invokevirtual getDeclaredField : (Ljava/lang/String;)Lgnu/bytecode/Field;
    //   11: astore #5
    //   13: aload #5
    //   15: ifnull -> 42
    //   18: iload_2
    //   19: iconst_m1
    //   20: if_icmpeq -> 35
    //   23: aload #5
    //   25: invokevirtual getModifiers : ()I
    //   28: istore_3
    //   29: iload_3
    //   30: iload_2
    //   31: iand
    //   32: ifeq -> 42
    //   35: aload #5
    //   37: astore_1
    //   38: aload_0
    //   39: monitorexit
    //   40: aload_1
    //   41: areturn
    //   42: aload #4
    //   44: invokevirtual getInterfaces : ()[Lgnu/bytecode/ClassType;
    //   47: astore #6
    //   49: aload #6
    //   51: ifnull -> 85
    //   54: iconst_0
    //   55: istore_3
    //   56: iload_3
    //   57: aload #6
    //   59: arraylength
    //   60: if_icmpge -> 85
    //   63: aload #6
    //   65: iload_3
    //   66: aaload
    //   67: aload_1
    //   68: iload_2
    //   69: invokevirtual getField : (Ljava/lang/String;I)Lgnu/bytecode/Field;
    //   72: astore #5
    //   74: aload #5
    //   76: ifnull -> 111
    //   79: aload #5
    //   81: astore_1
    //   82: goto -> 38
    //   85: aload #4
    //   87: invokevirtual getSuperclass : ()Lgnu/bytecode/ClassType;
    //   90: astore #5
    //   92: aload #5
    //   94: astore #4
    //   96: aload #5
    //   98: ifnonnull -> 5
    //   101: aconst_null
    //   102: astore_1
    //   103: goto -> 38
    //   106: astore_1
    //   107: aload_0
    //   108: monitorexit
    //   109: aload_1
    //   110: athrow
    //   111: iload_3
    //   112: iconst_1
    //   113: iadd
    //   114: istore_3
    //   115: goto -> 56
    // Exception table:
    //   from	to	target	type
    //   5	13	106	finally
    //   23	29	106	finally
    //   42	49	106	finally
    //   56	74	106	finally
    //   85	92	106	finally
  }
  
  public final int getFieldCount() {
    return this.fields_count;
  }
  
  public final Field getFields() {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield flags : I
    //   6: bipush #17
    //   8: iand
    //   9: bipush #16
    //   11: if_icmpne -> 18
    //   14: aload_0
    //   15: invokevirtual addFields : ()V
    //   18: aload_0
    //   19: getfield fields : Lgnu/bytecode/Field;
    //   22: astore_1
    //   23: aload_0
    //   24: monitorexit
    //   25: aload_1
    //   26: areturn
    //   27: astore_1
    //   28: aload_0
    //   29: monitorexit
    //   30: aload_1
    //   31: athrow
    // Exception table:
    //   from	to	target	type
    //   2	18	27	finally
    //   18	23	27	finally
  }
  
  public ClassType[] getInterfaces() {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield interfaces : [Lgnu/bytecode/ClassType;
    //   6: ifnonnull -> 90
    //   9: aload_0
    //   10: getfield flags : I
    //   13: bipush #16
    //   15: iand
    //   16: ifeq -> 90
    //   19: aload_0
    //   20: invokevirtual getReflectClass : ()Ljava/lang/Class;
    //   23: ifnull -> 90
    //   26: aload_0
    //   27: getfield reflectClass : Ljava/lang/Class;
    //   30: invokevirtual getInterfaces : ()[Ljava/lang/Class;
    //   33: astore #4
    //   35: aload #4
    //   37: arraylength
    //   38: istore_2
    //   39: iload_2
    //   40: ifne -> 82
    //   43: getstatic gnu/bytecode/ClassType.noClasses : [Lgnu/bytecode/ClassType;
    //   46: astore_3
    //   47: aload_0
    //   48: aload_3
    //   49: putfield interfaces : [Lgnu/bytecode/ClassType;
    //   52: iconst_0
    //   53: istore_1
    //   54: iload_1
    //   55: iload_2
    //   56: if_icmpge -> 90
    //   59: aload_0
    //   60: getfield interfaces : [Lgnu/bytecode/ClassType;
    //   63: iload_1
    //   64: aload #4
    //   66: iload_1
    //   67: aaload
    //   68: invokestatic make : (Ljava/lang/Class;)Lgnu/bytecode/Type;
    //   71: checkcast gnu/bytecode/ClassType
    //   74: aastore
    //   75: iload_1
    //   76: iconst_1
    //   77: iadd
    //   78: istore_1
    //   79: goto -> 54
    //   82: iload_2
    //   83: anewarray gnu/bytecode/ClassType
    //   86: astore_3
    //   87: goto -> 47
    //   90: aload_0
    //   91: getfield interfaces : [Lgnu/bytecode/ClassType;
    //   94: astore_3
    //   95: aload_0
    //   96: monitorexit
    //   97: aload_3
    //   98: areturn
    //   99: astore_3
    //   100: aload_0
    //   101: monitorexit
    //   102: aload_3
    //   103: athrow
    // Exception table:
    //   from	to	target	type
    //   2	39	99	finally
    //   43	47	99	finally
    //   47	52	99	finally
    //   59	75	99	finally
    //   82	87	99	finally
    //   90	95	99	finally
  }
  
  public Method[] getMatchingMethods(String paramString, Type[] paramArrayOfType, int paramInt) {
    int i = 0;
    Vector<Method> vector = new Vector(10);
    Method method = this.methods;
    while (method != null) {
      int j;
      if (!paramString.equals(method.getName())) {
        j = i;
      } else {
        j = i;
        if ((paramInt & 0x8) == (method.access_flags & 0x8)) {
          j = i;
          if ((paramInt & 0x1) <= (method.access_flags & 0x1)) {
            j = i;
            if (method.arg_types.length == paramArrayOfType.length) {
              j = i + 1;
              vector.addElement(method);
            } 
          } 
        } 
      } 
      method = method.getNext();
      i = j;
    } 
    Method[] arrayOfMethod = new Method[i];
    vector.copyInto((Object[])arrayOfMethod);
    return arrayOfMethod;
  }
  
  public Method getMethod(String paramString, Type[] paramArrayOfType) {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: astore #4
    //   5: aload #4
    //   7: aload_1
    //   8: aload_2
    //   9: invokevirtual getDeclaredMethod : (Ljava/lang/String;[Lgnu/bytecode/Type;)Lgnu/bytecode/Method;
    //   12: astore #5
    //   14: aload #5
    //   16: ifnull -> 28
    //   19: aload #5
    //   21: astore #4
    //   23: aload_0
    //   24: monitorexit
    //   25: aload #4
    //   27: areturn
    //   28: aload #4
    //   30: invokevirtual getSuperclass : ()Lgnu/bytecode/ClassType;
    //   33: astore #5
    //   35: aload #5
    //   37: astore #4
    //   39: aload #5
    //   41: ifnonnull -> 5
    //   44: aload_0
    //   45: astore #5
    //   47: aload #5
    //   49: invokevirtual getInterfaces : ()[Lgnu/bytecode/ClassType;
    //   52: astore #7
    //   54: aload #7
    //   56: ifnull -> 95
    //   59: iconst_0
    //   60: istore_3
    //   61: iload_3
    //   62: aload #7
    //   64: arraylength
    //   65: if_icmpge -> 95
    //   68: aload #7
    //   70: iload_3
    //   71: aaload
    //   72: aload_1
    //   73: aload_2
    //   74: invokevirtual getDeclaredMethod : (Ljava/lang/String;[Lgnu/bytecode/Type;)Lgnu/bytecode/Method;
    //   77: astore #6
    //   79: aload #6
    //   81: astore #4
    //   83: aload #6
    //   85: ifnonnull -> 23
    //   88: iload_3
    //   89: iconst_1
    //   90: iadd
    //   91: istore_3
    //   92: goto -> 61
    //   95: aload #5
    //   97: invokevirtual getSuperclass : ()Lgnu/bytecode/ClassType;
    //   100: astore #4
    //   102: aload #4
    //   104: astore #5
    //   106: aload #4
    //   108: ifnonnull -> 47
    //   111: aconst_null
    //   112: astore #4
    //   114: goto -> 23
    //   117: astore_1
    //   118: aload_0
    //   119: monitorexit
    //   120: aload_1
    //   121: athrow
    // Exception table:
    //   from	to	target	type
    //   5	14	117	finally
    //   28	35	117	finally
    //   47	54	117	finally
    //   61	79	117	finally
    //   95	102	117	finally
  }
  
  public Method getMethod(Method paramMethod) {
    String str = paramMethod.getName();
    Class[] arrayOfClass = paramMethod.getParameterTypes();
    Type[] arrayOfType = new Type[arrayOfClass.length];
    int i = arrayOfClass.length;
    while (true) {
      if (--i >= 0) {
        arrayOfType[i] = Type.make(arrayOfClass[i]);
        continue;
      } 
      return addMethod(str, paramMethod.getModifiers(), arrayOfType, Type.make(paramMethod.getReturnType()));
    } 
  }
  
  public final int getMethodCount() {
    return this.methods_count;
  }
  
  public int getMethods(Filter paramFilter, int paramInt, List<Method> paramList) {
    Object object;
    boolean bool = false;
    String str = null;
    ClassType classType = this;
    while (true) {
      Object object1 = object;
      if (classType != null) {
        String str1 = classType.getPackageName();
        Method method = classType.getDeclaredMethods();
        while (true) {
          method = method.getNext();
          object = object1;
        } 
        str = str1;
        if (paramInt == 0)
          return object; 
      } else {
        return object1;
      } 
      object1 = object;
      if (paramInt > 1) {
        ClassType[] arrayOfClassType = classType.getInterfaces();
        object1 = object;
        if (arrayOfClassType != null) {
          int i = 0;
          while (true) {
            object1 = object;
            if (i < arrayOfClassType.length) {
              int j = object + arrayOfClassType[i].getMethods(paramFilter, paramInt, paramList);
              i++;
              continue;
            } 
            break;
          } 
        } 
      } 
      continue;
      classType = classType.getSuperclass();
      object = SYNTHETIC_LOCAL_VARIABLE_5;
    } 
  }
  
  public int getMethods(Filter paramFilter, int paramInt1, Method[] paramArrayOfMethod, int paramInt2) {
    Vector<Method> vector = new Vector();
    getMethods(paramFilter, paramInt1, vector);
    int i = vector.size();
    for (paramInt1 = 0; paramInt1 < i; paramInt1++)
      paramArrayOfMethod[paramInt2 + paramInt1] = vector.elementAt(paramInt1); 
    return i;
  }
  
  public final Method getMethods() {
    return this.methods;
  }
  
  public Method[] getMethods(Filter paramFilter, int paramInt) {
    Vector<Method> vector = new Vector();
    getMethods(paramFilter, paramInt, vector);
    int i = vector.size();
    Method[] arrayOfMethod = new Method[i];
    for (paramInt = 0; paramInt < i; paramInt++)
      arrayOfMethod[paramInt] = vector.elementAt(paramInt); 
    return arrayOfMethod;
  }
  
  public Method[] getMethods(Filter paramFilter, boolean paramBoolean) {
    if (paramBoolean) {
      boolean bool1 = true;
      return getMethods(paramFilter, bool1);
    } 
    boolean bool = false;
    return getMethods(paramFilter, bool);
  }
  
  public final int getModifiers() {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield access_flags : I
    //   6: ifne -> 37
    //   9: aload_0
    //   10: getfield flags : I
    //   13: bipush #16
    //   15: iand
    //   16: ifeq -> 37
    //   19: aload_0
    //   20: invokevirtual getReflectClass : ()Ljava/lang/Class;
    //   23: ifnull -> 37
    //   26: aload_0
    //   27: aload_0
    //   28: getfield reflectClass : Ljava/lang/Class;
    //   31: invokevirtual getModifiers : ()I
    //   34: putfield access_flags : I
    //   37: aload_0
    //   38: getfield access_flags : I
    //   41: istore_1
    //   42: aload_0
    //   43: monitorexit
    //   44: iload_1
    //   45: ireturn
    //   46: astore_2
    //   47: aload_0
    //   48: monitorexit
    //   49: aload_2
    //   50: athrow
    // Exception table:
    //   from	to	target	type
    //   2	37	46	finally
    //   37	42	46	finally
  }
  
  public ClassType getOuterLinkType() {
    return !hasOuterLink() ? null : (ClassType)getDeclaredField("this$0").getType();
  }
  
  public String getPackageName() {
    String str = getName();
    int i = str.lastIndexOf('.');
    return (i < 0) ? "" : str.substring(0, i);
  }
  
  public String getSimpleName() {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield flags : I
    //   6: bipush #16
    //   8: iand
    //   9: ifeq -> 39
    //   12: aload_0
    //   13: invokevirtual getReflectClass : ()Ljava/lang/Class;
    //   16: astore #4
    //   18: aload #4
    //   20: ifnull -> 39
    //   23: aload_0
    //   24: getfield reflectClass : Ljava/lang/Class;
    //   27: invokevirtual getSimpleName : ()Ljava/lang/String;
    //   30: astore #5
    //   32: aload_0
    //   33: monitorexit
    //   34: aload #5
    //   36: areturn
    //   37: astore #4
    //   39: aload_0
    //   40: invokevirtual getName : ()Ljava/lang/String;
    //   43: astore #5
    //   45: aload #5
    //   47: bipush #46
    //   49: invokevirtual lastIndexOf : (I)I
    //   52: istore_1
    //   53: aload #5
    //   55: astore #4
    //   57: iload_1
    //   58: ifle -> 71
    //   61: aload #5
    //   63: iload_1
    //   64: iconst_1
    //   65: iadd
    //   66: invokevirtual substring : (I)Ljava/lang/String;
    //   69: astore #4
    //   71: aload #4
    //   73: bipush #36
    //   75: invokevirtual lastIndexOf : (I)I
    //   78: istore_1
    //   79: aload #4
    //   81: astore #5
    //   83: iload_1
    //   84: iflt -> 32
    //   87: aload #4
    //   89: invokevirtual length : ()I
    //   92: istore_2
    //   93: iload_1
    //   94: iconst_1
    //   95: iadd
    //   96: istore_1
    //   97: iload_1
    //   98: iload_2
    //   99: if_icmpge -> 128
    //   102: aload #4
    //   104: iload_1
    //   105: invokevirtual charAt : (I)C
    //   108: istore_3
    //   109: iload_3
    //   110: bipush #48
    //   112: if_icmplt -> 128
    //   115: iload_3
    //   116: bipush #57
    //   118: if_icmpgt -> 128
    //   121: iload_1
    //   122: iconst_1
    //   123: iadd
    //   124: istore_1
    //   125: goto -> 97
    //   128: aload #4
    //   130: iload_1
    //   131: invokevirtual substring : (I)Ljava/lang/String;
    //   134: astore #5
    //   136: goto -> 32
    //   139: astore #4
    //   141: aload_0
    //   142: monitorexit
    //   143: aload #4
    //   145: athrow
    // Exception table:
    //   from	to	target	type
    //   2	18	139	finally
    //   23	32	37	java/lang/Throwable
    //   23	32	139	finally
    //   39	53	139	finally
    //   61	71	139	finally
    //   71	79	139	finally
    //   87	93	139	finally
    //   102	109	139	finally
    //   128	136	139	finally
  }
  
  public final boolean getStaticFlag() {
    return ((getModifiers() & 0x8) != 0);
  }
  
  public ClassType getSuperclass() {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield superClass : Lgnu/bytecode/ClassType;
    //   6: ifnonnull -> 63
    //   9: aload_0
    //   10: invokevirtual isInterface : ()Z
    //   13: ifne -> 63
    //   16: ldc_w 'java.lang.Object'
    //   19: aload_0
    //   20: invokevirtual getName : ()Ljava/lang/String;
    //   23: invokevirtual equals : (Ljava/lang/Object;)Z
    //   26: ifne -> 63
    //   29: aload_0
    //   30: getfield flags : I
    //   33: bipush #16
    //   35: iand
    //   36: ifeq -> 63
    //   39: aload_0
    //   40: invokevirtual getReflectClass : ()Ljava/lang/Class;
    //   43: ifnull -> 63
    //   46: aload_0
    //   47: aload_0
    //   48: getfield reflectClass : Ljava/lang/Class;
    //   51: invokevirtual getSuperclass : ()Ljava/lang/Class;
    //   54: invokestatic make : (Ljava/lang/Class;)Lgnu/bytecode/Type;
    //   57: checkcast gnu/bytecode/ClassType
    //   60: putfield superClass : Lgnu/bytecode/ClassType;
    //   63: aload_0
    //   64: getfield superClass : Lgnu/bytecode/ClassType;
    //   67: astore_1
    //   68: aload_0
    //   69: monitorexit
    //   70: aload_1
    //   71: areturn
    //   72: astore_1
    //   73: aload_0
    //   74: monitorexit
    //   75: aload_1
    //   76: athrow
    // Exception table:
    //   from	to	target	type
    //   2	63	72	finally
    //   63	68	72	finally
  }
  
  public final boolean hasOuterLink() {
    getFields();
    return ((this.flags & 0x20) != 0);
  }
  
  public final boolean implementsInterface(ClassType paramClassType) {
    if (this != paramClassType) {
      ClassType classType = getSuperclass();
      if (classType == null || !classType.implementsInterface(paramClassType)) {
        ClassType[] arrayOfClassType = getInterfaces();
        if (arrayOfClassType != null) {
          int i = arrayOfClassType.length;
          while (true) {
            int j = i - 1;
            if (j >= 0) {
              i = j;
              if (arrayOfClassType[j].implementsInterface(paramClassType))
                return true; 
              continue;
            } 
            break;
          } 
        } 
        return false;
      } 
    } 
    return true;
  }
  
  public boolean isAccessible(ClassType paramClassType, ObjectType paramObjectType, int paramInt) {
    int i = paramClassType.getModifiers();
    if ((paramInt & 0x1) == 0 || (i & 0x1) == 0) {
      String str1 = getName();
      String str2 = paramClassType.getName();
      if (!str1.equals(str2)) {
        if ((paramInt & 0x2) != 0)
          return false; 
        int j = str1.lastIndexOf('.');
        if (j >= 0) {
          str1 = str1.substring(0, j);
        } else {
          str1 = "";
        } 
        j = str2.lastIndexOf('.');
        if (j >= 0) {
          str2 = str2.substring(0, j);
        } else {
          str2 = "";
        } 
        if (!str1.equals(str2)) {
          if ((i & 0x1) == 0)
            return false; 
          if ((paramInt & 0x4) == 0 || !isSubclass(paramClassType) || (paramObjectType instanceof ClassType && !((ClassType)paramObjectType).isSubclass(this)))
            return false; 
        } 
      } 
    } 
    return true;
  }
  
  public boolean isAccessible(Member paramMember, ObjectType paramObjectType) {
    if (paramMember.getStaticFlag())
      paramObjectType = null; 
    return isAccessible(paramMember.getDeclaringClass(), paramObjectType, paramMember.getModifiers());
  }
  
  public final boolean isInterface() {
    return ((getModifiers() & 0x200) != 0);
  }
  
  public final boolean isSubclass(ClassType paramClassType) {
    // Byte code:
    //   0: iconst_1
    //   1: istore_3
    //   2: aload_1
    //   3: invokevirtual isInterface : ()Z
    //   6: ifeq -> 17
    //   9: aload_0
    //   10: aload_1
    //   11: invokevirtual implementsInterface : (Lgnu/bytecode/ClassType;)Z
    //   14: istore_2
    //   15: iload_2
    //   16: ireturn
    //   17: aload_0
    //   18: getstatic gnu/bytecode/ClassType.toStringType : Lgnu/bytecode/ClassType;
    //   21: if_acmpne -> 33
    //   24: iload_3
    //   25: istore_2
    //   26: aload_1
    //   27: getstatic gnu/bytecode/ClassType.javalangStringType : Lgnu/bytecode/ClassType;
    //   30: if_acmpeq -> 15
    //   33: aload_0
    //   34: getstatic gnu/bytecode/ClassType.javalangStringType : Lgnu/bytecode/ClassType;
    //   37: if_acmpne -> 49
    //   40: iload_3
    //   41: istore_2
    //   42: aload_1
    //   43: getstatic gnu/bytecode/ClassType.toStringType : Lgnu/bytecode/ClassType;
    //   46: if_acmpeq -> 15
    //   49: aload_0
    //   50: astore #4
    //   52: aload #4
    //   54: ifnull -> 75
    //   57: iload_3
    //   58: istore_2
    //   59: aload #4
    //   61: aload_1
    //   62: if_acmpeq -> 15
    //   65: aload #4
    //   67: invokevirtual getSuperclass : ()Lgnu/bytecode/ClassType;
    //   70: astore #4
    //   72: goto -> 52
    //   75: iconst_0
    //   76: ireturn
  }
  
  public final boolean isSubclass(String paramString) {
    ClassType classType = this;
    while (true) {
      if (paramString.equals(classType.getName()))
        return true; 
      ClassType classType1 = classType.getSuperclass();
      classType = classType1;
      if (classType1 == null)
        return false; 
    } 
  }
  
  public void readExternal(ObjectInput paramObjectInput) throws IOException, ClassNotFoundException {
    setName(paramObjectInput.readUTF());
    this.flags |= 0x10;
  }
  
  public Object readResolve() throws ObjectStreamException {
    null = getName();
    synchronized (mapNameToType) {
      Type type = null.get(null);
      if (type != null)
        return type; 
      null.put(null, this);
      return this;
    } 
  }
  
  public final void setAttributes(Attribute paramAttribute) {
    this.attributes = paramAttribute;
  }
  
  public void setClassfileVersion(int paramInt) {
    this.classfileFormatVersion = paramInt;
  }
  
  public void setClassfileVersion(int paramInt1, int paramInt2) {
    this.classfileFormatVersion = (paramInt1 & 0xFFFF) * 65536 + paramInt2 * 65535;
  }
  
  public void setClassfileVersionJava5() {
    setClassfileVersion(3211264);
  }
  
  public void setEnclosingMember(Member paramMember) {
    this.enclosingMember = paramMember;
  }
  
  public final void setInterface(boolean paramBoolean) {
    if (paramBoolean) {
      this.access_flags |= 0x600;
      return;
    } 
    this.access_flags &= 0xFFFFFDFF;
  }
  
  public void setInterfaces(ClassType[] paramArrayOfClassType) {
    this.interfaces = paramArrayOfClassType;
  }
  
  public final void setModifiers(int paramInt) {
    this.access_flags = paramInt;
  }
  
  public void setName(String paramString) {
    this.this_name = paramString;
    setSignature("L" + paramString.replace('.', '/') + ";");
  }
  
  public final Field setOuterLink(ClassType paramClassType) {
    Method method1;
    Method method2;
    if ((this.flags & 0x10) != 0)
      throw new Error("setOuterLink called for existing class " + getName()); 
    Field field = getDeclaredField("this$0");
    if (field == null) {
      Field field1 = addField("this$0", paramClassType);
      this.flags |= 0x20;
      method1 = this.methods;
      while (true) {
        Field field2 = field1;
        if (method1 != null) {
          if ("<init>".equals(method1.getName())) {
            if (method1.code != null)
              throw new Error("setOuterLink called when " + method1 + " has code"); 
            Type[] arrayOfType1 = method1.arg_types;
            Type[] arrayOfType2 = new Type[arrayOfType1.length + 1];
            System.arraycopy(arrayOfType1, 0, arrayOfType2, 1, arrayOfType1.length);
            arrayOfType2[0] = paramClassType;
            method1.arg_types = arrayOfType2;
            method1.signature = null;
          } 
          method1 = method1.getNext();
          continue;
        } 
        break;
      } 
    } else {
      method2 = method1;
      if (!paramClassType.equals(method1.getType()))
        throw new Error("inconsistent setOuterLink call for " + getName()); 
    } 
    return (Field)method2;
  }
  
  public void setSourceFile(String paramString) {
    if (this.sourceDbgExt != null) {
      this.sourceDbgExt.addFile(paramString);
      if (this.sourceDbgExt.fileCount > 1)
        return; 
    } 
    String str = SourceFileAttr.fixSourceFile(paramString);
    int i = str.lastIndexOf('/');
    paramString = str;
    if (i >= 0)
      paramString = str.substring(i + 1); 
    SourceFileAttr.setSourceFile(this, paramString);
  }
  
  public void setStratum(String paramString) {
    if (this.sourceDbgExt == null)
      this.sourceDbgExt = new SourceDebugExtAttr(this); 
    this.sourceDbgExt.addStratum(paramString);
  }
  
  public void setSuper(ClassType paramClassType) {
    this.superClass = paramClassType;
  }
  
  public void setSuper(String paramString) {
    ClassType classType;
    if (paramString == null) {
      classType = Type.pointer_type;
    } else {
      classType = make((String)classType);
    } 
    setSuper(classType);
  }
  
  public String toString() {
    return "ClassType " + getName();
  }
  
  public void writeExternal(ObjectOutput paramObjectOutput) throws IOException {
    paramObjectOutput.writeUTF(getName());
  }
  
  public byte[] writeToArray() {
    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(500);
    try {
      writeToStream(byteArrayOutputStream);
      return byteArrayOutputStream.toByteArray();
    } catch (IOException iOException) {
      throw new InternalError(iOException.toString());
    } 
  }
  
  public void writeToFile() throws IOException {
    writeToFile(this.this_name.replace('.', File.separatorChar) + ".class");
  }
  
  public void writeToFile(String paramString) throws IOException {
    BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(paramString));
    writeToStream(bufferedOutputStream);
    bufferedOutputStream.close();
  }
  
  public void writeToStream(OutputStream paramOutputStream) throws IOException {
    // Byte code:
    //   0: new java/io/DataOutputStream
    //   3: dup
    //   4: aload_1
    //   5: invokespecial <init> : (Ljava/io/OutputStream;)V
    //   8: astore #4
    //   10: aload_0
    //   11: invokevirtual doFixups : ()V
    //   14: aload #4
    //   16: ldc_w -889275714
    //   19: invokevirtual writeInt : (I)V
    //   22: aload #4
    //   24: aload_0
    //   25: invokevirtual getClassfileMinorVersion : ()S
    //   28: invokevirtual writeShort : (I)V
    //   31: aload #4
    //   33: aload_0
    //   34: invokevirtual getClassfileMajorVersion : ()S
    //   37: invokevirtual writeShort : (I)V
    //   40: aload_0
    //   41: getfield constants : Lgnu/bytecode/ConstantPool;
    //   44: ifnonnull -> 126
    //   47: aload #4
    //   49: iconst_1
    //   50: invokevirtual writeShort : (I)V
    //   53: aload #4
    //   55: aload_0
    //   56: getfield access_flags : I
    //   59: invokevirtual writeShort : (I)V
    //   62: aload #4
    //   64: aload_0
    //   65: getfield thisClassIndex : I
    //   68: invokevirtual writeShort : (I)V
    //   71: aload #4
    //   73: aload_0
    //   74: getfield superClassIndex : I
    //   77: invokevirtual writeShort : (I)V
    //   80: aload_0
    //   81: getfield interfaceIndexes : [I
    //   84: ifnonnull -> 138
    //   87: aload #4
    //   89: iconst_0
    //   90: invokevirtual writeShort : (I)V
    //   93: aload #4
    //   95: aload_0
    //   96: getfield fields_count : I
    //   99: invokevirtual writeShort : (I)V
    //   102: aload_0
    //   103: getfield fields : Lgnu/bytecode/Field;
    //   106: astore_1
    //   107: aload_1
    //   108: ifnull -> 175
    //   111: aload_1
    //   112: aload #4
    //   114: aload_0
    //   115: invokevirtual write : (Ljava/io/DataOutputStream;Lgnu/bytecode/ClassType;)V
    //   118: aload_1
    //   119: getfield next : Lgnu/bytecode/Field;
    //   122: astore_1
    //   123: goto -> 107
    //   126: aload_0
    //   127: getfield constants : Lgnu/bytecode/ConstantPool;
    //   130: aload #4
    //   132: invokevirtual write : (Ljava/io/DataOutputStream;)V
    //   135: goto -> 53
    //   138: aload_0
    //   139: getfield interfaceIndexes : [I
    //   142: arraylength
    //   143: istore_3
    //   144: aload #4
    //   146: iload_3
    //   147: invokevirtual writeShort : (I)V
    //   150: iconst_0
    //   151: istore_2
    //   152: iload_2
    //   153: iload_3
    //   154: if_icmpge -> 93
    //   157: aload #4
    //   159: aload_0
    //   160: getfield interfaceIndexes : [I
    //   163: iload_2
    //   164: iaload
    //   165: invokevirtual writeShort : (I)V
    //   168: iload_2
    //   169: iconst_1
    //   170: iadd
    //   171: istore_2
    //   172: goto -> 152
    //   175: aload #4
    //   177: aload_0
    //   178: getfield methods_count : I
    //   181: invokevirtual writeShort : (I)V
    //   184: aload_0
    //   185: getfield methods : Lgnu/bytecode/Method;
    //   188: astore_1
    //   189: aload_1
    //   190: ifnull -> 208
    //   193: aload_1
    //   194: aload #4
    //   196: aload_0
    //   197: invokevirtual write : (Ljava/io/DataOutputStream;Lgnu/bytecode/ClassType;)V
    //   200: aload_1
    //   201: getfield next : Lgnu/bytecode/Method;
    //   204: astore_1
    //   205: goto -> 189
    //   208: aload_0
    //   209: aload #4
    //   211: invokestatic writeAll : (Lgnu/bytecode/AttrContainer;Ljava/io/DataOutputStream;)V
    //   214: aload_0
    //   215: aload_0
    //   216: getfield flags : I
    //   219: iconst_3
    //   220: ior
    //   221: putfield flags : I
    //   224: return
  }
  
  static class AbstractMethodFilter implements Filter {
    public static final AbstractMethodFilter instance = new AbstractMethodFilter();
    
    public boolean select(Object param1Object) {
      return ((Method)param1Object).isAbstract();
    }
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/gnu/bytecode/ClassType.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */