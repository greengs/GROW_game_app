package gnu.expr;

import gnu.bytecode.ClassType;
import gnu.bytecode.CodeAttr;
import gnu.bytecode.Field;
import gnu.bytecode.Member;
import gnu.bytecode.Method;
import gnu.bytecode.ObjectType;
import gnu.bytecode.PrimType;
import gnu.bytecode.Type;
import gnu.bytecode.Variable;
import gnu.mapping.OutPort;
import java.util.Hashtable;
import java.util.Vector;

public class ClassExp extends LambdaExp {
  public static final int CLASS_SPECIFIED = 65536;
  
  public static final int HAS_SUBCLASS = 131072;
  
  public static final int INTERFACE_SPECIFIED = 32768;
  
  public static final int IS_ABSTRACT = 16384;
  
  public String classNameSpecifier;
  
  public LambdaExp clinitMethod;
  
  boolean explicitInit;
  
  public LambdaExp initMethod;
  
  ClassType instanceType;
  
  boolean partsDeclared;
  
  boolean simple;
  
  public int superClassIndex = -1;
  
  public Expression[] supers;
  
  public ClassExp() {}
  
  public ClassExp(boolean paramBoolean) {
    this.simple = paramBoolean;
    ClassType classType = new ClassType();
    this.type = classType;
    this.instanceType = classType;
  }
  
  static void getImplMethods(ClassType paramClassType, String paramString, Type[] paramArrayOfType, Vector<E> paramVector) {
    // Byte code:
    //   0: aload_0
    //   1: instanceof gnu/expr/PairClassType
    //   4: ifeq -> 89
    //   7: aload_0
    //   8: checkcast gnu/expr/PairClassType
    //   11: getfield instanceType : Lgnu/bytecode/ClassType;
    //   14: astore #5
    //   16: aload_2
    //   17: arraylength
    //   18: iconst_1
    //   19: iadd
    //   20: anewarray gnu/bytecode/Type
    //   23: astore #6
    //   25: aload #6
    //   27: iconst_0
    //   28: aload_0
    //   29: aastore
    //   30: aload_2
    //   31: iconst_0
    //   32: aload #6
    //   34: iconst_1
    //   35: aload_2
    //   36: arraylength
    //   37: invokestatic arraycopy : (Ljava/lang/Object;ILjava/lang/Object;II)V
    //   40: aload #5
    //   42: aload_1
    //   43: aload #6
    //   45: invokevirtual getDeclaredMethod : (Ljava/lang/String;[Lgnu/bytecode/Type;)Lgnu/bytecode/Method;
    //   48: astore #5
    //   50: aload #5
    //   52: ifnull -> 151
    //   55: aload_3
    //   56: invokevirtual size : ()I
    //   59: istore #4
    //   61: iload #4
    //   63: ifeq -> 82
    //   66: aload_3
    //   67: iload #4
    //   69: iconst_1
    //   70: isub
    //   71: invokevirtual elementAt : (I)Ljava/lang/Object;
    //   74: aload #5
    //   76: invokevirtual equals : (Ljava/lang/Object;)Z
    //   79: ifne -> 88
    //   82: aload_3
    //   83: aload #5
    //   85: invokevirtual addElement : (Ljava/lang/Object;)V
    //   88: return
    //   89: aload_0
    //   90: invokevirtual isInterface : ()Z
    //   93: ifeq -> 88
    //   96: aload_0
    //   97: invokevirtual getReflectClass : ()Ljava/lang/Class;
    //   100: astore #5
    //   102: aload #5
    //   104: ifnull -> 88
    //   107: new java/lang/StringBuilder
    //   110: dup
    //   111: invokespecial <init> : ()V
    //   114: aload_0
    //   115: invokevirtual getName : ()Ljava/lang/String;
    //   118: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   121: ldc '$class'
    //   123: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   126: invokevirtual toString : ()Ljava/lang/String;
    //   129: iconst_0
    //   130: aload #5
    //   132: invokevirtual getClassLoader : ()Ljava/lang/ClassLoader;
    //   135: invokestatic forName : (Ljava/lang/String;ZLjava/lang/ClassLoader;)Ljava/lang/Class;
    //   138: invokestatic make : (Ljava/lang/Class;)Lgnu/bytecode/Type;
    //   141: checkcast gnu/bytecode/ClassType
    //   144: astore #5
    //   146: goto -> 16
    //   149: astore_0
    //   150: return
    //   151: aload_0
    //   152: invokevirtual getInterfaces : ()[Lgnu/bytecode/ClassType;
    //   155: astore_0
    //   156: iconst_0
    //   157: istore #4
    //   159: iload #4
    //   161: aload_0
    //   162: arraylength
    //   163: if_icmpge -> 88
    //   166: aload_0
    //   167: iload #4
    //   169: aaload
    //   170: aload_1
    //   171: aload_2
    //   172: aload_3
    //   173: invokestatic getImplMethods : (Lgnu/bytecode/ClassType;Ljava/lang/String;[Lgnu/bytecode/Type;Ljava/util/Vector;)V
    //   176: iload #4
    //   178: iconst_1
    //   179: iadd
    //   180: istore #4
    //   182: goto -> 159
    // Exception table:
    //   from	to	target	type
    //   96	102	149	java/lang/Throwable
    //   107	146	149	java/lang/Throwable
  }
  
  static void invokeDefaultSuperConstructor(ClassType paramClassType, Compilation paramCompilation, LambdaExp paramLambdaExp) {
    CodeAttr codeAttr = paramCompilation.getCode();
    Method method = paramClassType.getDeclaredMethod("<init>", 0);
    if (method == null) {
      paramCompilation.error('e', "super class does not have a default constructor");
      return;
    } 
    codeAttr.emitPushThis();
    if (paramClassType.hasOuterLink() && paramLambdaExp instanceof ClassExp) {
      paramLambdaExp = paramLambdaExp;
      loadSuperStaticLink(((ClassExp)paramLambdaExp).supers[((ClassExp)paramLambdaExp).superClassIndex], paramClassType, paramCompilation);
    } 
    codeAttr.emitInvokeSpecial(method);
  }
  
  static void loadSuperStaticLink(Expression paramExpression, ClassType paramClassType, Compilation paramCompilation) {
    CodeAttr codeAttr = paramCompilation.getCode();
    paramExpression.compile(paramCompilation, Target.pushValue((Type)Compilation.typeClassType));
    codeAttr.emitInvokeStatic(ClassType.make("gnu.expr.PairClassType").getDeclaredMethod("extractStaticLink", 1));
    codeAttr.emitCheckcast((Type)paramClassType.getOuterLinkType());
  }
  
  public static String slotToMethodName(String paramString1, String paramString2) {
    String str = paramString2;
    if (!Compilation.isValidJavaName(paramString2))
      str = Compilation.mangleName(paramString2, false); 
    int i = str.length();
    StringBuffer stringBuffer = new StringBuffer(i + 3);
    stringBuffer.append(paramString1);
    if (i > 0) {
      stringBuffer.append(Character.toTitleCase(str.charAt(0)));
      stringBuffer.append(str.substring(1));
    } 
    return stringBuffer.toString();
  }
  
  private static void usedSuperClasses(ClassType paramClassType, Compilation paramCompilation) {
    paramCompilation.usedClass((Type)paramClassType.getSuperclass());
    ClassType[] arrayOfClassType = paramClassType.getInterfaces();
    if (arrayOfClassType != null) {
      int i = arrayOfClassType.length;
      while (true) {
        if (--i >= 0) {
          paramCompilation.usedClass((Type)arrayOfClassType[i]);
          continue;
        } 
        break;
      } 
    } 
  }
  
  public Declaration addMethod(LambdaExp paramLambdaExp, Object paramObject) {
    Declaration declaration = addDeclaration(paramObject, (Type)Compilation.typeProcedure);
    paramLambdaExp.outer = this;
    paramLambdaExp.setClassMethod(true);
    declaration.noteValue(paramLambdaExp);
    declaration.setFlag(1048576L);
    declaration.setProcedureDecl(true);
    paramLambdaExp.setSymbol(paramObject);
    return declaration;
  }
  
  public void compile(Compilation paramCompilation, Target paramTarget) {
    if (paramTarget instanceof IgnoreTarget)
      return; 
    compileMembers(paramCompilation);
    compilePushClass(paramCompilation, paramTarget);
  }
  
  public ClassType compileMembers(Compilation paramCompilation) {
    int i;
    Method[] arrayOfMethod;
    ClassType classType2;
    LambdaExp lambdaExp;
    ClassType classType1 = paramCompilation.curClass;
    Method method = paramCompilation.method;
    try {
      ClassType classType3;
      classType2 = getCompiledClassType(paramCompilation);
      paramCompilation.curClass = classType2;
      LambdaExp lambdaExp2 = outerLambda();
      ClassType classType4 = null;
      if (lambdaExp2 instanceof ClassExp) {
        classType3 = lambdaExp2.type;
      } else if (lambdaExp2 != null && !(lambdaExp2 instanceof ModuleExp)) {
        Method method1 = method;
      } else {
        classType3 = classType4;
        if (lambdaExp2 instanceof ModuleExp) {
          classType3 = classType4;
          if (this.type.getName().indexOf('$') > 0)
            classType3 = lambdaExp2.type; 
        } 
      } 
      if (classType3 != null) {
        classType2.setEnclosingMember((Member)classType3);
        if (classType3 instanceof ClassType)
          classType3.addMemberClass(classType2); 
      } 
      if (this.instanceType != classType2) {
        this.instanceType.setEnclosingMember((Member)this.type);
        this.type.addMemberClass(this.instanceType);
      } 
      usedSuperClasses(this.type, paramCompilation);
      if (this.type != this.instanceType)
        usedSuperClasses(this.instanceType, paramCompilation); 
      String str = getFileName();
      if (str != null)
        classType2.setSourceFile(str); 
      lambdaExp = paramCompilation.curLambda;
      paramCompilation.curLambda = this;
    } finally {
      paramCompilation.curClass = classType1;
      paramCompilation.method = method;
    } 
    if (!this.explicitInit && !this.instanceType.isInterface()) {
      paramCompilation.generateConstructor(this.instanceType, this);
    } else if (this.initChain != null) {
      this.initChain.reportError("unimplemented: explicit constructor cannot initialize ", paramCompilation);
    } 
    if (isAbstract()) {
      arrayOfMethod = null;
      i = 0;
    } else {
      arrayOfMethod = this.type.getAbstractMethods();
      i = arrayOfMethod.length;
    } 
    for (int j = 0;; j++) {
      if (j < i) {
        CodeAttr codeAttr;
        Method method2 = arrayOfMethod[j];
        String str = method2.getName();
        Type[] arrayOfType = method2.getParameterTypes();
        Type type = method2.getReturnType();
        Method method1 = this.instanceType.getMethod(str, arrayOfType);
        if (method1 != null && !method1.isAbstract())
          continue; 
        if (str.length() > 3 && str.charAt(2) == 't' && str.charAt(1) == 'e') {
          char c = str.charAt(0);
          if (c == 'g' || c == 's') {
            Type type1;
            if (c == 's' && type.isVoid() && arrayOfType.length == 1) {
              type1 = arrayOfType[0];
            } else if (c == 'g' && arrayOfType.length == 0) {
              type1 = type;
            } else {
              continue;
            } 
            String str1 = Character.toLowerCase(str.charAt(3)) + str.substring(4);
            Field field2 = this.instanceType.getField(str1);
            Field field1 = field2;
            if (field2 == null)
              field1 = this.instanceType.addField(str1, type1, 1); 
            codeAttr = this.instanceType.addMethod(str, 1, arrayOfType, type).startCode();
            codeAttr.emitPushThis();
            if (c == 'g') {
              codeAttr.emitGetField(field1);
            } else {
              codeAttr.emitLoad(codeAttr.getArg(1));
              codeAttr.emitPutField(field1);
            } 
            codeAttr.emitReturn();
            continue;
          } 
        } 
        Vector<Method> vector = new Vector();
        getImplMethods(this.type, str, arrayOfType, vector);
        if (vector.size() != 1) {
          String str1;
          if (vector.size() == 0) {
            str1 = "missing implementation for ";
          } else {
            str1 = "ambiguous implementation for ";
          } 
          paramCompilation.error('e', str1 + codeAttr);
        } else {
          codeAttr = this.instanceType.addMethod(str, 1, arrayOfType, type).startCode();
          for (Variable variable = codeAttr.getCurrentScope().firstVar(); variable != null; variable = variable.nextVar())
            codeAttr.emitLoad(variable); 
          codeAttr.emitInvokeStatic(vector.elementAt(0));
          codeAttr.emitReturn();
        } 
        continue;
      } 
      generateApplyMethods(paramCompilation);
      paramCompilation.curLambda = lambdaExp;
      paramCompilation.curClass = classType1;
      paramCompilation.method = method;
      return classType2;
    } 
  }
  
  public void compilePushClass(Compilation paramCompilation, Target paramTarget) {
    byte b;
    ClassType classType1 = this.type;
    CodeAttr codeAttr = paramCompilation.getCode();
    paramCompilation.loadClassRef((ObjectType)classType1);
    boolean bool = getNeedsClosureEnv();
    if (isSimple() && !bool)
      return; 
    if (isMakingClassPair() || bool) {
      if (classType1 == this.instanceType) {
        codeAttr.emitDup((Type)this.instanceType);
      } else {
        paramCompilation.loadClassRef((ObjectType)this.instanceType);
      } 
      classType1 = ClassType.make("gnu.expr.PairClassType");
      if (bool) {
        b = 3;
      } else {
        b = 2;
      } 
    } else {
      classType1 = ClassType.make("gnu.bytecode.Type");
      b = 1;
    } 
    Type[] arrayOfType = new Type[b];
    int i = b;
    if (bool) {
      getOwningLambda().loadHeapFrame(paramCompilation);
      i = b - 1;
      arrayOfType[i] = (Type)Type.pointer_type;
    } 
    ClassType classType2 = ClassType.make("java.lang.Class");
    while (true) {
      if (--i >= 0) {
        arrayOfType[i] = (Type)classType2;
        continue;
      } 
      codeAttr.emitInvokeStatic(classType1.addMethod("make", arrayOfType, (Type)classType1, 9));
      paramTarget.compileFromStack(paramCompilation, (Type)classType1);
      return;
    } 
  }
  
  public Field compileSetField(Compilation paramCompilation) {
    return (new ClassInitializer(this, paramCompilation)).field;
  }
  
  public void declareParts(Compilation paramCompilation) {
    if (!this.partsDeclared) {
      this.partsDeclared = true;
      Hashtable<Object, Object> hashtable = new Hashtable<Object, Object>();
      for (Declaration declaration = firstDecl(); declaration != null; declaration = declaration.nextDecl()) {
        if (declaration.getCanRead()) {
          short s = declaration.getAccessFlags((short)1);
          int i = s;
          if (declaration.getFlag(2048L))
            i = s | 0x8; 
          if (isMakingClassPair()) {
            i |= 0x400;
            Type type = declaration.getType().getImplementationType();
            this.type.addMethod(slotToMethodName("get", declaration.getName()), i, Type.typeArray0, type);
            ClassType classType = this.type;
            String str = slotToMethodName("set", declaration.getName());
            PrimType primType = Type.voidType;
            classType.addMethod(str, i, new Type[] { type }, (Type)primType);
          } else {
            String str = Compilation.mangleNameIfNeeded(declaration.getName());
            declaration.field = this.instanceType.addField(str, declaration.getType(), i);
            declaration.setSimple(false);
            Declaration declaration1 = (Declaration)hashtable.get(str);
            if (declaration1 != null)
              duplicateDeclarationError(declaration1, declaration, paramCompilation); 
            hashtable.put(str, declaration);
          } 
        } 
      } 
      for (LambdaExp lambdaExp = this.firstChild; lambdaExp != null; lambdaExp = lambdaExp.nextSibling) {
        if (lambdaExp.isAbstract())
          setFlag(16384); 
        if ("*init*".equals(lambdaExp.getName())) {
          this.explicitInit = true;
          if (lambdaExp.isAbstract())
            paramCompilation.error('e', "*init* method cannot be abstract", lambdaExp); 
          if (this.type instanceof PairClassType)
            paramCompilation.error('e', "'*init*' methods only supported for simple classes"); 
        } 
        lambdaExp.outer = this;
        if ((lambdaExp != this.initMethod && lambdaExp != this.clinitMethod && lambdaExp.nameDecl != null && !lambdaExp.nameDecl.getFlag(2048L)) || !isMakingClassPair())
          lambdaExp.addMethodFor(this.type, paramCompilation, (ObjectType)null); 
        if (isMakingClassPair())
          lambdaExp.addMethodFor(this.instanceType, paramCompilation, (ObjectType)this.type); 
      } 
      if (!this.explicitInit && !this.instanceType.isInterface())
        Compilation.getConstructor(this.instanceType, this); 
      if (isAbstract())
        this.instanceType.setModifiers(this.instanceType.getModifiers() | 0x400); 
      if (this.nameDecl != null) {
        this.instanceType.setModifiers(this.instanceType.getModifiers() & 0xFFFFFFFE | this.nameDecl.getAccessFlags((short)1));
        return;
      } 
    } 
  }
  
  public ClassType getClassType() {
    return this.type;
  }
  
  protected ClassType getCompiledClassType(Compilation paramCompilation) {
    return this.type;
  }
  
  public Type getType() {
    return (Type)(this.simple ? Compilation.typeClass : Compilation.typeClassType);
  }
  
  public final boolean isAbstract() {
    return getFlag(16384);
  }
  
  public boolean isMakingClassPair() {
    return (this.type != this.instanceType);
  }
  
  public boolean isSimple() {
    return this.simple;
  }
  
  protected boolean mustCompile() {
    return true;
  }
  
  public void print(OutPort paramOutPort) {
    paramOutPort.startLogicalBlock("(" + getExpClassName() + "/", ")", 2);
    Object object = getSymbol();
    if (object != null) {
      paramOutPort.print(object);
      paramOutPort.print('/');
    } 
    paramOutPort.print(this.id);
    paramOutPort.print("/fl:");
    paramOutPort.print(Integer.toHexString(this.flags));
    if (this.supers.length > 0) {
      paramOutPort.writeSpaceFill();
      paramOutPort.startLogicalBlock("supers:", "", 2);
      for (int j = 0; j < this.supers.length; j++) {
        this.supers[j].print(paramOutPort);
        paramOutPort.writeSpaceFill();
      } 
      paramOutPort.endLogicalBlock("");
    } 
    paramOutPort.print('(');
    int i = 0;
    if (this.keywords != null)
      int j = this.keywords.length; 
    for (object = firstDecl(); object != null; object = object.nextDecl()) {
      if (i)
        paramOutPort.print(' '); 
      object.printInfo(paramOutPort);
      i++;
    } 
    paramOutPort.print(") ");
    for (object = this.firstChild; object != null; object = ((LambdaExp)object).nextSibling) {
      paramOutPort.writeBreakLinear();
      object.print(paramOutPort);
    } 
    if (this.body != null) {
      paramOutPort.writeBreakLinear();
      this.body.print(paramOutPort);
    } 
    paramOutPort.endLogicalBlock(")");
  }
  
  public void setSimple(boolean paramBoolean) {
    this.simple = paramBoolean;
  }
  
  public void setTypes(Compilation paramCompilation) {
    // Byte code:
    //   0: aload_0
    //   1: getfield supers : [Lgnu/expr/Expression;
    //   4: ifnonnull -> 81
    //   7: iconst_0
    //   8: istore_3
    //   9: iload_3
    //   10: anewarray gnu/bytecode/ClassType
    //   13: astore #9
    //   15: aconst_null
    //   16: astore #7
    //   18: iconst_0
    //   19: istore #4
    //   21: iconst_0
    //   22: istore_2
    //   23: iload #4
    //   25: iload_3
    //   26: if_icmpge -> 225
    //   29: invokestatic getDefaultLanguage : ()Lgnu/expr/Language;
    //   32: aload_0
    //   33: getfield supers : [Lgnu/expr/Expression;
    //   36: iload #4
    //   38: aaload
    //   39: invokevirtual getTypeFor : (Lgnu/expr/Expression;)Lgnu/bytecode/Type;
    //   42: astore #8
    //   44: aload #8
    //   46: instanceof gnu/bytecode/ClassType
    //   49: ifne -> 90
    //   52: aload_1
    //   53: aload_0
    //   54: getfield supers : [Lgnu/expr/Expression;
    //   57: iload #4
    //   59: aaload
    //   60: invokevirtual setLine : (Lgnu/expr/Expression;)V
    //   63: aload_1
    //   64: bipush #101
    //   66: ldc_w 'invalid super type'
    //   69: invokevirtual error : (CLjava/lang/String;)V
    //   72: iload #4
    //   74: iconst_1
    //   75: iadd
    //   76: istore #4
    //   78: goto -> 23
    //   81: aload_0
    //   82: getfield supers : [Lgnu/expr/Expression;
    //   85: arraylength
    //   86: istore_3
    //   87: goto -> 9
    //   90: aload #8
    //   92: checkcast gnu/bytecode/ClassType
    //   95: astore #8
    //   97: aload #8
    //   99: invokevirtual getModifiers : ()I
    //   102: istore #5
    //   104: iload #5
    //   106: sipush #512
    //   109: iand
    //   110: ifne -> 208
    //   113: iload_2
    //   114: iload #4
    //   116: if_icmpge -> 145
    //   119: aload_1
    //   120: bipush #101
    //   122: new java/lang/StringBuilder
    //   125: dup
    //   126: invokespecial <init> : ()V
    //   129: ldc_w 'duplicate superclass for '
    //   132: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   135: aload_0
    //   136: invokevirtual append : (Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   139: invokevirtual toString : ()Ljava/lang/String;
    //   142: invokevirtual error : (CLjava/lang/String;)V
    //   145: aload #8
    //   147: astore #7
    //   149: aload_0
    //   150: iload #4
    //   152: putfield superClassIndex : I
    //   155: goto -> 72
    //   158: astore #10
    //   160: iconst_0
    //   161: istore #6
    //   163: iload #6
    //   165: istore #5
    //   167: aload_1
    //   168: ifnull -> 104
    //   171: aload_1
    //   172: bipush #101
    //   174: new java/lang/StringBuilder
    //   177: dup
    //   178: invokespecial <init> : ()V
    //   181: ldc_w 'unknown super-type '
    //   184: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   187: aload #8
    //   189: invokevirtual getName : ()Ljava/lang/String;
    //   192: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   195: invokevirtual toString : ()Ljava/lang/String;
    //   198: invokevirtual error : (CLjava/lang/String;)V
    //   201: iload #6
    //   203: istore #5
    //   205: goto -> 104
    //   208: iload_2
    //   209: iconst_1
    //   210: iadd
    //   211: istore #5
    //   213: aload #9
    //   215: iload_2
    //   216: aload #8
    //   218: aastore
    //   219: iload #5
    //   221: istore_2
    //   222: goto -> 72
    //   225: aload #7
    //   227: ifnull -> 249
    //   230: aload_0
    //   231: getfield flags : I
    //   234: ldc 32768
    //   236: iand
    //   237: ifeq -> 249
    //   240: aload_1
    //   241: bipush #101
    //   243: ldc_w 'cannot be interface since has superclass'
    //   246: invokevirtual error : (CLjava/lang/String;)V
    //   249: aload_0
    //   250: getfield simple : Z
    //   253: ifne -> 569
    //   256: aload #7
    //   258: ifnonnull -> 569
    //   261: aload_0
    //   262: getfield flags : I
    //   265: ldc 65536
    //   267: iand
    //   268: ifne -> 569
    //   271: aload_0
    //   272: ldc 131072
    //   274: invokevirtual getFlag : (I)Z
    //   277: ifne -> 297
    //   280: aload_0
    //   281: getfield nameDecl : Lgnu/expr/Declaration;
    //   284: ifnull -> 569
    //   287: aload_0
    //   288: getfield nameDecl : Lgnu/expr/Declaration;
    //   291: invokevirtual isPublic : ()Z
    //   294: ifeq -> 569
    //   297: new gnu/expr/PairClassType
    //   300: dup
    //   301: invokespecial <init> : ()V
    //   304: astore #8
    //   306: aload_0
    //   307: aload #8
    //   309: putfield type : Lgnu/bytecode/ClassType;
    //   312: aload #8
    //   314: iconst_1
    //   315: invokevirtual setInterface : (Z)V
    //   318: aload #8
    //   320: aload_0
    //   321: getfield instanceType : Lgnu/bytecode/ClassType;
    //   324: putfield instanceType : Lgnu/bytecode/ClassType;
    //   327: aload_0
    //   328: getfield type : Lgnu/bytecode/ClassType;
    //   331: astore #8
    //   333: aload_0
    //   334: getfield instanceType : Lgnu/bytecode/ClassType;
    //   337: getstatic gnu/bytecode/Type.pointer_type : Lgnu/bytecode/ClassType;
    //   340: invokevirtual setSuper : (Lgnu/bytecode/ClassType;)V
    //   343: aload_0
    //   344: getfield instanceType : Lgnu/bytecode/ClassType;
    //   347: iconst_1
    //   348: anewarray gnu/bytecode/ClassType
    //   351: dup
    //   352: iconst_0
    //   353: aload #8
    //   355: aastore
    //   356: invokevirtual setInterfaces : ([Lgnu/bytecode/ClassType;)V
    //   359: aload_0
    //   360: getfield type : Lgnu/bytecode/ClassType;
    //   363: astore #10
    //   365: aload #7
    //   367: astore #8
    //   369: aload #7
    //   371: ifnonnull -> 379
    //   374: getstatic gnu/bytecode/Type.pointer_type : Lgnu/bytecode/ClassType;
    //   377: astore #8
    //   379: aload #10
    //   381: aload #8
    //   383: invokevirtual setSuper : (Lgnu/bytecode/ClassType;)V
    //   386: iload_2
    //   387: iload_3
    //   388: if_icmpne -> 589
    //   391: aload #9
    //   393: astore #7
    //   395: aload_0
    //   396: getfield type : Lgnu/bytecode/ClassType;
    //   399: aload #7
    //   401: invokevirtual setInterfaces : ([Lgnu/bytecode/ClassType;)V
    //   404: aload_0
    //   405: getfield type : Lgnu/bytecode/ClassType;
    //   408: invokevirtual getName : ()Ljava/lang/String;
    //   411: ifnonnull -> 568
    //   414: aload_0
    //   415: getfield classNameSpecifier : Ljava/lang/String;
    //   418: ifnull -> 608
    //   421: aload_0
    //   422: getfield classNameSpecifier : Ljava/lang/String;
    //   425: astore #7
    //   427: aload #7
    //   429: ifnonnull -> 697
    //   432: new java/lang/StringBuffer
    //   435: dup
    //   436: bipush #100
    //   438: invokespecial <init> : (I)V
    //   441: astore #8
    //   443: aload_1
    //   444: invokevirtual getModule : ()Lgnu/expr/ModuleExp;
    //   447: aload_1
    //   448: invokevirtual classFor : (Lgnu/expr/Compilation;)Lgnu/bytecode/ClassType;
    //   451: pop
    //   452: aload #8
    //   454: aload_1
    //   455: getfield mainClass : Lgnu/bytecode/ClassType;
    //   458: invokevirtual getName : ()Ljava/lang/String;
    //   461: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   464: pop
    //   465: aload #8
    //   467: bipush #36
    //   469: invokevirtual append : (C)Ljava/lang/StringBuffer;
    //   472: pop
    //   473: aload #8
    //   475: invokevirtual length : ()I
    //   478: istore_3
    //   479: iconst_0
    //   480: istore_2
    //   481: aload #8
    //   483: iload_2
    //   484: invokevirtual append : (I)Ljava/lang/StringBuffer;
    //   487: pop
    //   488: aload #8
    //   490: invokevirtual toString : ()Ljava/lang/String;
    //   493: astore #7
    //   495: aload_1
    //   496: aload #7
    //   498: invokevirtual findNamedClass : (Ljava/lang/String;)Lgnu/bytecode/ClassType;
    //   501: ifnonnull -> 684
    //   504: aload_0
    //   505: getfield type : Lgnu/bytecode/ClassType;
    //   508: aload #7
    //   510: invokevirtual setName : (Ljava/lang/String;)V
    //   513: aload_1
    //   514: aload_0
    //   515: getfield type : Lgnu/bytecode/ClassType;
    //   518: invokevirtual addClass : (Lgnu/bytecode/ClassType;)V
    //   521: aload_0
    //   522: invokevirtual isMakingClassPair : ()Z
    //   525: ifeq -> 568
    //   528: aload_0
    //   529: getfield instanceType : Lgnu/bytecode/ClassType;
    //   532: new java/lang/StringBuilder
    //   535: dup
    //   536: invokespecial <init> : ()V
    //   539: aload_0
    //   540: getfield type : Lgnu/bytecode/ClassType;
    //   543: invokevirtual getName : ()Ljava/lang/String;
    //   546: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   549: ldc '$class'
    //   551: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   554: invokevirtual toString : ()Ljava/lang/String;
    //   557: invokevirtual setName : (Ljava/lang/String;)V
    //   560: aload_1
    //   561: aload_0
    //   562: getfield instanceType : Lgnu/bytecode/ClassType;
    //   565: invokevirtual addClass : (Lgnu/bytecode/ClassType;)V
    //   568: return
    //   569: aload_0
    //   570: ldc 32768
    //   572: invokevirtual getFlag : (I)Z
    //   575: ifeq -> 359
    //   578: aload_0
    //   579: getfield instanceType : Lgnu/bytecode/ClassType;
    //   582: iconst_1
    //   583: invokevirtual setInterface : (Z)V
    //   586: goto -> 359
    //   589: iload_2
    //   590: anewarray gnu/bytecode/ClassType
    //   593: astore #7
    //   595: aload #9
    //   597: iconst_0
    //   598: aload #7
    //   600: iconst_0
    //   601: iload_2
    //   602: invokestatic arraycopy : (Ljava/lang/Object;ILjava/lang/Object;II)V
    //   605: goto -> 395
    //   608: aload_0
    //   609: invokevirtual getName : ()Ljava/lang/String;
    //   612: astore #8
    //   614: aload #8
    //   616: astore #7
    //   618: aload #8
    //   620: ifnull -> 427
    //   623: aload #8
    //   625: invokevirtual length : ()I
    //   628: istore_2
    //   629: aload #8
    //   631: astore #7
    //   633: iload_2
    //   634: iconst_2
    //   635: if_icmple -> 427
    //   638: aload #8
    //   640: astore #7
    //   642: aload #8
    //   644: iconst_0
    //   645: invokevirtual charAt : (I)C
    //   648: bipush #60
    //   650: if_icmpne -> 427
    //   653: aload #8
    //   655: astore #7
    //   657: aload #8
    //   659: iload_2
    //   660: iconst_1
    //   661: isub
    //   662: invokevirtual charAt : (I)C
    //   665: bipush #62
    //   667: if_icmpne -> 427
    //   670: aload #8
    //   672: iconst_1
    //   673: iload_2
    //   674: iconst_1
    //   675: isub
    //   676: invokevirtual substring : (II)Ljava/lang/String;
    //   679: astore #7
    //   681: goto -> 427
    //   684: aload #8
    //   686: iload_3
    //   687: invokevirtual setLength : (I)V
    //   690: iload_2
    //   691: iconst_1
    //   692: iadd
    //   693: istore_2
    //   694: goto -> 481
    //   697: aload_0
    //   698: invokevirtual isSimple : ()Z
    //   701: ifeq -> 711
    //   704: aload_0
    //   705: instanceof gnu/expr/ObjectExp
    //   708: ifeq -> 722
    //   711: aload_1
    //   712: aload #7
    //   714: invokevirtual generateClassName : (Ljava/lang/String;)Ljava/lang/String;
    //   717: astore #7
    //   719: goto -> 504
    //   722: iconst_0
    //   723: istore_2
    //   724: new java/lang/StringBuffer
    //   727: dup
    //   728: bipush #100
    //   730: invokespecial <init> : (I)V
    //   733: astore #9
    //   735: aload #7
    //   737: bipush #46
    //   739: iload_2
    //   740: invokevirtual indexOf : (II)I
    //   743: istore_3
    //   744: iload_3
    //   745: ifge -> 822
    //   748: iload_2
    //   749: ifne -> 909
    //   752: aload_1
    //   753: getfield mainClass : Lgnu/bytecode/ClassType;
    //   756: ifnonnull -> 866
    //   759: aconst_null
    //   760: astore #8
    //   762: aload #8
    //   764: ifnonnull -> 878
    //   767: iconst_m1
    //   768: istore_3
    //   769: iload_3
    //   770: ifle -> 889
    //   773: aload #9
    //   775: aload #8
    //   777: iconst_0
    //   778: iload_3
    //   779: iconst_1
    //   780: iadd
    //   781: invokevirtual substring : (II)Ljava/lang/String;
    //   784: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   787: pop
    //   788: iload_2
    //   789: aload #7
    //   791: invokevirtual length : ()I
    //   794: if_icmpge -> 812
    //   797: aload #9
    //   799: aload #7
    //   801: iload_2
    //   802: invokevirtual substring : (I)Ljava/lang/String;
    //   805: invokestatic mangleNameIfNeeded : (Ljava/lang/String;)Ljava/lang/String;
    //   808: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   811: pop
    //   812: aload #9
    //   814: invokevirtual toString : ()Ljava/lang/String;
    //   817: astore #7
    //   819: goto -> 504
    //   822: aload #9
    //   824: aload #7
    //   826: iload_2
    //   827: iload_3
    //   828: invokevirtual substring : (II)Ljava/lang/String;
    //   831: invokestatic mangleNameIfNeeded : (Ljava/lang/String;)Ljava/lang/String;
    //   834: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   837: pop
    //   838: iload_3
    //   839: iconst_1
    //   840: iadd
    //   841: istore_3
    //   842: iload_3
    //   843: istore_2
    //   844: iload_3
    //   845: aload #7
    //   847: invokevirtual length : ()I
    //   850: if_icmpge -> 735
    //   853: aload #9
    //   855: bipush #46
    //   857: invokevirtual append : (C)Ljava/lang/StringBuffer;
    //   860: pop
    //   861: iload_3
    //   862: istore_2
    //   863: goto -> 735
    //   866: aload_1
    //   867: getfield mainClass : Lgnu/bytecode/ClassType;
    //   870: invokevirtual getName : ()Ljava/lang/String;
    //   873: astore #8
    //   875: goto -> 762
    //   878: aload #8
    //   880: bipush #46
    //   882: invokevirtual lastIndexOf : (I)I
    //   885: istore_3
    //   886: goto -> 769
    //   889: aload_1
    //   890: getfield classPrefix : Ljava/lang/String;
    //   893: ifnull -> 788
    //   896: aload #9
    //   898: aload_1
    //   899: getfield classPrefix : Ljava/lang/String;
    //   902: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   905: pop
    //   906: goto -> 788
    //   909: iload_2
    //   910: iconst_1
    //   911: if_icmpne -> 788
    //   914: iload_2
    //   915: aload #7
    //   917: invokevirtual length : ()I
    //   920: if_icmpge -> 788
    //   923: aload #9
    //   925: iconst_0
    //   926: invokevirtual setLength : (I)V
    //   929: aload #9
    //   931: aload_1
    //   932: getfield mainClass : Lgnu/bytecode/ClassType;
    //   935: invokevirtual getName : ()Ljava/lang/String;
    //   938: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   941: pop
    //   942: aload #9
    //   944: bipush #36
    //   946: invokevirtual append : (C)Ljava/lang/StringBuffer;
    //   949: pop
    //   950: goto -> 788
    // Exception table:
    //   from	to	target	type
    //   97	104	158	java/lang/RuntimeException
  }
  
  protected <R, D> R visit(ExpVisitor<R, D> paramExpVisitor, D paramD) {
    Compilation compilation = paramExpVisitor.getCompilation();
    if (compilation == null)
      return paramExpVisitor.visitClassExp(this, paramD); 
    ClassType classType = compilation.curClass;
    try {
      compilation.curClass = this.type;
      paramExpVisitor = (ExpVisitor<R, D>)paramExpVisitor.visitClassExp(this, paramD);
      return (R)paramExpVisitor;
    } finally {
      compilation.curClass = classType;
    } 
  }
  
  protected <R, D> void visitChildren(ExpVisitor<R, D> paramExpVisitor, D paramD) {
    LambdaExp lambdaExp = paramExpVisitor.currentLambda;
    paramExpVisitor.currentLambda = this;
    this.supers = paramExpVisitor.visitExps(this.supers, this.supers.length, paramD);
    try {
      for (LambdaExp lambdaExp1 = this.firstChild; lambdaExp1 != null && paramExpVisitor.exitValue == null; lambdaExp1 = lambdaExp1.nextSibling) {
        if (this.instanceType != null) {
          Declaration declaration = lambdaExp1.firstDecl();
          if (declaration != null && declaration.isThisParameter())
            declaration.setType((Type)this.type); 
        } 
        paramExpVisitor.visitLambdaExp(lambdaExp1, paramD);
      } 
      return;
    } finally {
      paramExpVisitor.currentLambda = lambdaExp;
    } 
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/gnu/expr/ClassExp.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */