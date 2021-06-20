package gnu.kawa.reflect;

import gnu.bytecode.ClassType;
import gnu.bytecode.CodeAttr;
import gnu.bytecode.Field;
import gnu.bytecode.Member;
import gnu.bytecode.Method;
import gnu.bytecode.ObjectType;
import gnu.bytecode.Type;
import gnu.expr.ApplyExp;
import gnu.expr.ClassExp;
import gnu.expr.Compilation;
import gnu.expr.Expression;
import gnu.expr.Inlineable;
import gnu.expr.Language;
import gnu.expr.QuoteExp;
import gnu.expr.Target;
import gnu.kawa.lispexpr.LangPrimType;
import gnu.mapping.HasSetter;
import gnu.mapping.Procedure;
import gnu.mapping.Procedure2;
import gnu.mapping.WrongArguments;
import gnu.mapping.WrongType;

public class SlotGet extends Procedure2 implements HasSetter, Inlineable {
  public static final SlotGet field;
  
  static Class[] noClasses = new Class[0];
  
  public static final SlotGet slotRef;
  
  public static final SlotGet staticField;
  
  boolean isStatic;
  
  Procedure setter;
  
  static {
    field = new SlotGet("field", false, (Procedure)SlotSet.set$Mnfield$Ex);
    slotRef = new SlotGet("slot-ref", false, (Procedure)SlotSet.set$Mnfield$Ex);
    staticField = new SlotGet("static-field", true, (Procedure)SlotSet.set$Mnstatic$Mnfield$Ex);
  }
  
  public SlotGet(String paramString, boolean paramBoolean) {
    super(paramString);
    this.isStatic = paramBoolean;
    setProperty(Procedure.validateApplyKey, "gnu.kawa.reflect.CompileReflect:validateApplySlotGet");
  }
  
  public SlotGet(String paramString, boolean paramBoolean, Procedure paramProcedure) {
    this(paramString, paramBoolean);
    this.setter = paramProcedure;
  }
  
  static Class coerceToClass(Object paramObject) {
    if (paramObject instanceof Class)
      return (Class)paramObject; 
    if (paramObject instanceof Type)
      return ((Type)paramObject).getReflectClass(); 
    throw new RuntimeException("argument is neither Class nor Type");
  }
  
  public static Object field(Object paramObject, String paramString) {
    return field.apply2(paramObject, paramString);
  }
  
  public static Object getSlotValue(boolean paramBoolean, Object paramObject, String paramString1, String paramString2, String paramString3, String paramString4, Language paramLanguage) {
    // Byte code:
    //   0: iload_0
    //   1: ifeq -> 36
    //   4: aload_1
    //   5: invokestatic coerceToClass : (Ljava/lang/Object;)Ljava/lang/Class;
    //   8: astore #9
    //   10: aload_3
    //   11: ldc 'length'
    //   13: if_acmpne -> 45
    //   16: aload #9
    //   18: invokevirtual isArray : ()Z
    //   21: ifeq -> 45
    //   24: aload_1
    //   25: invokestatic getLength : (Ljava/lang/Object;)I
    //   28: invokestatic valueOf : (I)Ljava/lang/Integer;
    //   31: astore #10
    //   33: aload #10
    //   35: areturn
    //   36: aload_1
    //   37: invokevirtual getClass : ()Ljava/lang/Class;
    //   40: astore #9
    //   42: goto -> 10
    //   45: aload #9
    //   47: astore #10
    //   49: aload_3
    //   50: ldc 'class'
    //   52: if_acmpeq -> 33
    //   55: iconst_0
    //   56: istore #8
    //   58: iload #8
    //   60: istore #7
    //   62: aload_3
    //   63: ifnull -> 164
    //   66: aload #9
    //   68: aload_3
    //   69: invokevirtual getField : (Ljava/lang/String;)Ljava/lang/reflect/Field;
    //   72: astore #10
    //   74: iload #8
    //   76: istore #7
    //   78: aload #10
    //   80: ifnull -> 164
    //   83: iload_0
    //   84: ifeq -> 138
    //   87: aload #10
    //   89: invokevirtual getModifiers : ()I
    //   92: bipush #8
    //   94: iand
    //   95: ifne -> 138
    //   98: new java/lang/RuntimeException
    //   101: dup
    //   102: new java/lang/StringBuilder
    //   105: dup
    //   106: invokespecial <init> : ()V
    //   109: ldc 'cannot access non-static field ''
    //   111: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   114: aload_3
    //   115: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   118: bipush #39
    //   120: invokevirtual append : (C)Ljava/lang/StringBuilder;
    //   123: invokevirtual toString : ()Ljava/lang/String;
    //   126: invokespecial <init> : (Ljava/lang/String;)V
    //   129: athrow
    //   130: astore #10
    //   132: aconst_null
    //   133: astore #10
    //   135: goto -> 74
    //   138: aload #6
    //   140: aload #10
    //   142: invokevirtual getType : ()Ljava/lang/Class;
    //   145: aload #10
    //   147: aload_1
    //   148: invokevirtual get : (Ljava/lang/Object;)Ljava/lang/Object;
    //   151: invokevirtual coerceToObject : (Ljava/lang/Class;Ljava/lang/Object;)Ljava/lang/Object;
    //   154: astore #10
    //   156: aload #10
    //   158: areturn
    //   159: astore #10
    //   161: iconst_1
    //   162: istore #7
    //   164: aload #4
    //   166: ifnull -> 262
    //   169: aload #9
    //   171: aload #4
    //   173: getstatic gnu/kawa/reflect/SlotGet.noClasses : [Ljava/lang/Class;
    //   176: invokevirtual getMethod : (Ljava/lang/String;[Ljava/lang/Class;)Ljava/lang/reflect/Method;
    //   179: astore #10
    //   181: aload #10
    //   183: astore_2
    //   184: aload #4
    //   186: astore #5
    //   188: aload_2
    //   189: astore #4
    //   191: iload_0
    //   192: ifeq -> 310
    //   195: aload #4
    //   197: invokevirtual getModifiers : ()I
    //   200: bipush #8
    //   202: iand
    //   203: ifne -> 310
    //   206: new java/lang/RuntimeException
    //   209: dup
    //   210: new java/lang/StringBuilder
    //   213: dup
    //   214: invokespecial <init> : ()V
    //   217: ldc 'cannot call non-static getter method ''
    //   219: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   222: aload #5
    //   224: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   227: bipush #39
    //   229: invokevirtual append : (C)Ljava/lang/StringBuilder;
    //   232: invokevirtual toString : ()Ljava/lang/String;
    //   235: invokespecial <init> : (Ljava/lang/String;)V
    //   238: athrow
    //   239: astore_1
    //   240: aload_1
    //   241: invokevirtual getTargetException : ()Ljava/lang/Throwable;
    //   244: invokestatic wrapIfNeeded : (Ljava/lang/Throwable;)Ljava/lang/RuntimeException;
    //   247: athrow
    //   248: astore #10
    //   250: aload #10
    //   252: invokevirtual printStackTrace : ()V
    //   255: iload #8
    //   257: istore #7
    //   259: goto -> 164
    //   262: ldc 'get'
    //   264: aload_2
    //   265: invokestatic slotToMethodName : (Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
    //   268: astore #4
    //   270: goto -> 169
    //   273: astore #4
    //   275: aload #5
    //   277: ifnull -> 300
    //   280: aload #5
    //   282: astore_2
    //   283: aload #9
    //   285: aload_2
    //   286: getstatic gnu/kawa/reflect/SlotGet.noClasses : [Ljava/lang/Class;
    //   289: invokevirtual getMethod : (Ljava/lang/String;[Ljava/lang/Class;)Ljava/lang/reflect/Method;
    //   292: astore #4
    //   294: aload_2
    //   295: astore #5
    //   297: goto -> 191
    //   300: ldc 'is'
    //   302: aload_2
    //   303: invokestatic slotToMethodName : (Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
    //   306: astore_2
    //   307: goto -> 283
    //   310: aload #4
    //   312: aload_1
    //   313: getstatic gnu/mapping/Values.noArgs : [Ljava/lang/Object;
    //   316: invokevirtual invoke : (Ljava/lang/Object;[Ljava/lang/Object;)Ljava/lang/Object;
    //   319: astore_1
    //   320: aload #6
    //   322: aload #4
    //   324: invokevirtual getReturnType : ()Ljava/lang/Class;
    //   327: aload_1
    //   328: invokevirtual coerceToObject : (Ljava/lang/Class;Ljava/lang/Object;)Ljava/lang/Object;
    //   331: astore_1
    //   332: aload_1
    //   333: areturn
    //   334: astore_1
    //   335: iconst_1
    //   336: istore #7
    //   338: iload #7
    //   340: ifeq -> 370
    //   343: new java/lang/RuntimeException
    //   346: dup
    //   347: new java/lang/StringBuilder
    //   350: dup
    //   351: invokespecial <init> : ()V
    //   354: ldc 'illegal access for field '
    //   356: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   359: aload_3
    //   360: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   363: invokevirtual toString : ()Ljava/lang/String;
    //   366: invokespecial <init> : (Ljava/lang/String;)V
    //   369: athrow
    //   370: new java/lang/RuntimeException
    //   373: dup
    //   374: new java/lang/StringBuilder
    //   377: dup
    //   378: invokespecial <init> : ()V
    //   381: ldc 'no such field '
    //   383: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   386: aload_3
    //   387: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   390: ldc ' in '
    //   392: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   395: aload #9
    //   397: invokevirtual getName : ()Ljava/lang/String;
    //   400: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   403: invokevirtual toString : ()Ljava/lang/String;
    //   406: invokespecial <init> : (Ljava/lang/String;)V
    //   409: athrow
    //   410: astore_1
    //   411: goto -> 338
    // Exception table:
    //   from	to	target	type
    //   66	74	130	java/lang/Exception
    //   138	156	159	java/lang/IllegalAccessException
    //   138	156	248	java/lang/Exception
    //   169	181	273	java/lang/Exception
    //   169	181	239	java/lang/reflect/InvocationTargetException
    //   169	181	334	java/lang/IllegalAccessException
    //   169	181	410	java/lang/NoSuchMethodException
    //   195	239	239	java/lang/reflect/InvocationTargetException
    //   195	239	334	java/lang/IllegalAccessException
    //   195	239	410	java/lang/NoSuchMethodException
    //   262	270	273	java/lang/Exception
    //   262	270	239	java/lang/reflect/InvocationTargetException
    //   262	270	334	java/lang/IllegalAccessException
    //   262	270	410	java/lang/NoSuchMethodException
    //   283	294	239	java/lang/reflect/InvocationTargetException
    //   283	294	334	java/lang/IllegalAccessException
    //   283	294	410	java/lang/NoSuchMethodException
    //   300	307	239	java/lang/reflect/InvocationTargetException
    //   300	307	334	java/lang/IllegalAccessException
    //   300	307	410	java/lang/NoSuchMethodException
    //   310	332	239	java/lang/reflect/InvocationTargetException
    //   310	332	334	java/lang/IllegalAccessException
    //   310	332	410	java/lang/NoSuchMethodException
  }
  
  public static Member lookupMember(ObjectType paramObjectType, String paramString, ClassType paramClassType) {
    Field field = paramObjectType.getField(Compilation.mangleNameIfNeeded(paramString), -1);
    if (field != null) {
      ClassType classType = paramClassType;
      if (paramClassType == null)
        classType = Type.pointer_type; 
      if (classType.isAccessible((Member)field, paramObjectType))
        return (Member)field; 
    } 
    Method method = paramObjectType.getMethod(ClassExp.slotToMethodName("get", paramString), Type.typeArray0);
    return (Member)((method != null) ? method : field);
  }
  
  public static ApplyExp makeGetField(Expression paramExpression, String paramString) {
    QuoteExp quoteExp = new QuoteExp(paramString);
    return new ApplyExp((Procedure)field, new Expression[] { paramExpression, (Expression)quoteExp });
  }
  
  public static Object staticField(Object paramObject, String paramString) {
    return staticField.apply2(paramObject, paramString);
  }
  
  public Object apply2(Object paramObject1, Object paramObject2) {
    String str = null;
    Object object3 = null;
    Object object = null;
    Object object2 = null;
    if (paramObject2 instanceof Field) {
      paramObject2 = ((Field)paramObject2).getName();
      str = Compilation.demangleName((String)paramObject2, true);
    } else if (paramObject2 instanceof Method) {
      Object object4;
      paramObject2 = ((Method)paramObject2).getName();
      object3 = Compilation.demangleName((String)paramObject2, false);
      if (paramObject2.startsWith("get")) {
        object2 = object;
        object4 = paramObject2;
      } else {
        object4 = str;
        object2 = object;
        if (paramObject2.startsWith("is")) {
          object4 = str;
          object2 = paramObject2;
        } 
      } 
      paramObject2 = null;
      str = (String)object3;
      object3 = object4;
    } else if (paramObject2 instanceof gnu.mapping.SimpleSymbol || paramObject2 instanceof CharSequence) {
      str = paramObject2.toString();
      paramObject2 = Compilation.mangleNameIfNeeded(str);
    } else {
      throw new WrongType(this, 2, paramObject2, "string");
    } 
    if ("class".equals(paramObject2)) {
      String str1 = "class";
      return getSlotValue(this.isStatic, paramObject1, str, str1, (String)object3, (String)object2, Language.getDefaultLanguage());
    } 
    Object object1 = paramObject2;
    if ("length".equals(paramObject2))
      object1 = "length"; 
    return getSlotValue(this.isStatic, paramObject1, str, (String)object1, (String)object3, (String)object2, Language.getDefaultLanguage());
  }
  
  public void compile(ApplyExp paramApplyExp, Compilation paramCompilation, Target paramTarget) {
    Target target;
    Type type;
    Method method;
    Expression expression2;
    Expression[] arrayOfExpression = paramApplyExp.getArgs();
    Expression expression1 = arrayOfExpression[0];
    Expression expression3 = arrayOfExpression[1];
    Language language = paramCompilation.getLanguage();
    if (this.isStatic) {
      type = language.getTypeFor(expression1);
    } else {
      type = type.getType();
    } 
    CodeAttr codeAttr = paramCompilation.getCode();
    if (type instanceof ObjectType && expression3 instanceof QuoteExp) {
      ObjectType objectType = (ObjectType)type;
      Object object = ((QuoteExp)expression3).getValue();
      if (object instanceof Field) {
        boolean bool;
        Field field = (Field)object;
        if ((field.getModifiers() & 0x8) != 0) {
          bool = true;
        } else {
          bool = false;
        } 
        expression2 = arrayOfExpression[0];
        if (bool) {
          target = Target.Ignore;
        } else {
          target = Target.pushValue((Type)objectType);
        } 
        expression2.compile(paramCompilation, target);
        if (bool) {
          if (!false)
            codeAttr.emitGetStatic(field); 
        } else {
          codeAttr.emitGetField(field);
        } 
        paramTarget.compileFromStack(paramCompilation, language.getLangTypeFor(field.getType()));
        return;
      } 
      if (object instanceof Method) {
        method = (Method)object;
        method.getModifiers();
        boolean bool = method.getStaticFlag();
        Expression expression = expression2[0];
        if (bool) {
          target = Target.Ignore;
        } else {
          target = Target.pushValue((Type)objectType);
        } 
        expression.compile(paramCompilation, target);
        if (bool) {
          codeAttr.emitInvokeStatic(method);
        } else {
          codeAttr.emitInvoke(method);
        } 
        paramTarget.compileFromStack(paramCompilation, method.getReturnType());
        return;
      } 
    } 
    String str = ClassMethods.checkName(expression3);
    if (method instanceof gnu.bytecode.ArrayType && "length".equals(str) && !this.isStatic) {
      expression2[0].compile(paramCompilation, Target.pushValue((Type)method));
      codeAttr.emitArrayLength();
      paramTarget.compileFromStack(paramCompilation, (Type)LangPrimType.intType);
      return;
    } 
    ApplyExp.compile((ApplyExp)target, paramCompilation, paramTarget);
  }
  
  public Type getReturnType(Expression[] paramArrayOfExpression) {
    if (paramArrayOfExpression.length == 2) {
      Expression expression2 = paramArrayOfExpression[0];
      Expression expression1 = paramArrayOfExpression[1];
      if (expression1 instanceof QuoteExp) {
        Object object = ((QuoteExp)expression1).getValue();
        if (object instanceof Field)
          return ((Field)object).getType(); 
        if (object instanceof Method)
          return ((Method)object).getReturnType(); 
        if (!this.isStatic && expression2.getType() instanceof gnu.bytecode.ArrayType && "length".equals(ClassMethods.checkName(expression1, true)))
          return (Type)LangPrimType.intType; 
      } 
    } 
    return (Type)Type.pointer_type;
  }
  
  public Procedure getSetter() {
    return (this.setter == null) ? super.getSetter() : this.setter;
  }
  
  public void set2(Object paramObject1, Object paramObject2, Object paramObject3) {
    SlotSet.apply(this.isStatic, paramObject1, paramObject2, paramObject3);
  }
  
  public void setN(Object[] paramArrayOfObject) {
    int i = paramArrayOfObject.length;
    if (i != 3)
      throw new WrongArguments(getSetter(), i); 
    set2(paramArrayOfObject[0], paramArrayOfObject[1], paramArrayOfObject[2]);
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/gnu/kawa/reflect/SlotGet.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */