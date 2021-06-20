package gnu.expr;

import gnu.bytecode.ClassType;
import gnu.bytecode.CodeAttr;
import gnu.bytecode.Field;
import gnu.bytecode.Method;
import gnu.bytecode.Type;
import gnu.bytecode.Variable;
import gnu.mapping.Location;
import gnu.mapping.Named;
import gnu.mapping.Namespace;
import gnu.mapping.OutPort;
import gnu.mapping.Symbol;
import gnu.mapping.WrappedException;
import gnu.text.SourceLocator;

public class Declaration implements SourceLocator {
  static final int CAN_CALL = 4;
  
  static final int CAN_READ = 2;
  
  static final int CAN_WRITE = 8;
  
  public static final long CLASS_ACCESS_FLAGS = 25820135424L;
  
  public static final int EARLY_INIT = 536870912;
  
  public static final long ENUM_ACCESS = 8589934592L;
  
  public static final int EXPORT_SPECIFIED = 1024;
  
  public static final int EXTERNAL_ACCESS = 524288;
  
  public static final long FIELD_ACCESS_FLAGS = 32463912960L;
  
  public static final int FIELD_OR_METHOD = 1048576;
  
  public static final long FINAL_ACCESS = 17179869184L;
  
  static final int INDIRECT_BINDING = 1;
  
  public static final int IS_ALIAS = 256;
  
  public static final int IS_CONSTANT = 16384;
  
  public static final int IS_DYNAMIC = 268435456;
  
  static final int IS_FLUID = 16;
  
  public static final int IS_IMPORTED = 131072;
  
  public static final int IS_NAMESPACE_PREFIX = 2097152;
  
  static final int IS_SIMPLE = 64;
  
  public static final int IS_SINGLE_VALUE = 262144;
  
  public static final int IS_SYNTAX = 32768;
  
  public static final int IS_UNKNOWN = 65536;
  
  public static final long METHOD_ACCESS_FLAGS = 17431527424L;
  
  public static final int MODULE_REFERENCE = 1073741824;
  
  public static final int NONSTATIC_SPECIFIED = 4096;
  
  public static final int NOT_DEFINING = 512;
  
  public static final int PACKAGE_ACCESS = 134217728;
  
  static final int PRIVATE = 32;
  
  public static final int PRIVATE_ACCESS = 16777216;
  
  public static final String PRIVATE_PREFIX = "$Prvt$";
  
  public static final int PRIVATE_SPECIFIED = 16777216;
  
  static final int PROCEDURE = 128;
  
  public static final int PROTECTED_ACCESS = 33554432;
  
  public static final int PUBLIC_ACCESS = 67108864;
  
  public static final int STATIC_SPECIFIED = 2048;
  
  public static final long TRANSIENT_ACCESS = 4294967296L;
  
  public static final int TYPE_SPECIFIED = 8192;
  
  static final String UNKNOWN_PREFIX = "loc$";
  
  public static final long VOLATILE_ACCESS = 2147483648L;
  
  static int counter;
  
  public Declaration base;
  
  public ScopeExp context;
  
  int evalIndex;
  
  public Field field;
  
  String filename;
  
  public ApplyExp firstCall;
  
  protected long flags;
  
  protected int id;
  
  Method makeLocationMethod;
  
  Declaration next;
  
  Declaration nextCapturedVar;
  
  int position;
  
  Object symbol;
  
  protected Type type;
  
  protected Expression typeExp;
  
  protected Expression value;
  
  Variable var;
  
  protected Declaration() {
    int i = counter + 1;
    counter = i;
    this.id = i;
    this.value = QuoteExp.undefined_exp;
    this.flags = 64L;
    this.makeLocationMethod = null;
  }
  
  public Declaration(Variable paramVariable) {
    this(paramVariable.getName(), paramVariable.getType());
    this.var = paramVariable;
  }
  
  public Declaration(Object paramObject) {
    int i = counter + 1;
    counter = i;
    this.id = i;
    this.value = QuoteExp.undefined_exp;
    this.flags = 64L;
    this.makeLocationMethod = null;
    setName(paramObject);
  }
  
  public Declaration(Object paramObject, Field paramField) {
    this(paramObject, paramField.getType());
    this.field = paramField;
    setSimple(false);
  }
  
  public Declaration(Object paramObject, Type paramType) {
    int i = counter + 1;
    counter = i;
    this.id = i;
    this.value = QuoteExp.undefined_exp;
    this.flags = 64L;
    this.makeLocationMethod = null;
    setName(paramObject);
    setType(paramType);
  }
  
  public static Declaration followAliases(Declaration paramDeclaration) {
    while (true) {
      if (paramDeclaration != null && paramDeclaration.isAlias()) {
        Expression expression = paramDeclaration.getValue();
        if (expression instanceof ReferenceExp) {
          Declaration declaration = ((ReferenceExp)expression).binding;
          if (declaration != null) {
            paramDeclaration = declaration;
            continue;
          } 
        } 
      } 
      return paramDeclaration;
    } 
  }
  
  public static Declaration getDeclaration(Named paramNamed) {
    return getDeclaration(paramNamed, paramNamed.getName());
  }
  
  public static Declaration getDeclaration(Object paramObject, String paramString) {
    Field field2 = null;
    Field field1 = field2;
    if (paramString != null) {
      Class clazz = PrimProcedure.getProcedureClass(paramObject);
      field1 = field2;
      if (clazz != null)
        field1 = ((ClassType)Type.make(clazz)).getDeclaredField(Compilation.mangleNameIfNeeded(paramString)); 
    } 
    if (field1 != null) {
      int i = field1.getModifiers();
      if ((i & 0x8) != 0) {
        Declaration declaration = new Declaration(paramString, field1);
        declaration.noteValue(new QuoteExp(paramObject));
        if ((i & 0x10) != 0)
          declaration.setFlag(16384L); 
        return declaration;
      } 
    } 
    return null;
  }
  
  public static Declaration getDeclarationFromStatic(String paramString1, String paramString2) {
    Declaration declaration = new Declaration(paramString2, ClassType.make(paramString1).getDeclaredField(paramString2));
    declaration.setFlag(18432L);
    return declaration;
  }
  
  public static Declaration getDeclarationValueFromStatic(String paramString1, String paramString2, String paramString3) {
    try {
      Object object = Class.forName(paramString1).getDeclaredField(paramString2).get(null);
      Declaration declaration = new Declaration(paramString3, ClassType.make(paramString1).getDeclaredField(paramString2));
      declaration.noteValue(new QuoteExp(object));
      declaration.setFlag(18432L);
      return declaration;
    } catch (Exception exception) {
      throw new WrappedException(exception);
    } 
  }
  
  public static final boolean isUnknown(Declaration paramDeclaration) {
    return (paramDeclaration == null || paramDeclaration.getFlag(65536L));
  }
  
  public final Variable allocateVariable(CodeAttr paramCodeAttr) {
    Variable variable;
    String str;
    Type type;
    if (!isSimple() || this.var == null) {
      str = null;
      if (this.symbol != null)
        str = Compilation.mangleNameIfNeeded(getName()); 
      if (isAlias() && getValue() instanceof ReferenceExp) {
        Declaration declaration = followAliases(this);
        if (declaration == null) {
          declaration = null;
        } else {
          variable = declaration.var;
        } 
        this.var = variable;
        return this.var;
      } 
    } else {
      return this.var;
    } 
    if (isIndirectBinding()) {
      ClassType classType = Compilation.typeLocation;
    } else {
      type = getType().getImplementationType();
    } 
    this.var = this.context.getVarScope().addVariable((CodeAttr)variable, type, str);
    return this.var;
  }
  
  public void compileStore(Compilation paramCompilation) {
    CodeAttr codeAttr = paramCompilation.getCode();
    if (isSimple()) {
      codeAttr.emitStore(getVariable());
      return;
    } 
    if (!this.field.getStaticFlag()) {
      loadOwningObject(null, paramCompilation);
      codeAttr.emitSwap();
      codeAttr.emitPutField(this.field);
      return;
    } 
    codeAttr.emitPutStatic(this.field);
  }
  
  public short getAccessFlags(short paramShort) {
    if (getFlag(251658240L)) {
      paramShort = 0;
      if (getFlag(16777216L))
        paramShort = (short)2; 
      short s1 = paramShort;
      if (getFlag(33554432L))
        s1 = (short)(paramShort | 0x4); 
      paramShort = s1;
      if (getFlag(67108864L))
        paramShort = (short)(s1 | 0x1); 
    } 
    short s = paramShort;
    if (getFlag(2147483648L))
      s = (short)(paramShort | 0x40); 
    paramShort = s;
    if (getFlag(4294967296L))
      paramShort = (short)(s | 0x80); 
    s = paramShort;
    if (getFlag(8589934592L))
      s = (short)(paramShort | 0x4000); 
    paramShort = s;
    if (getFlag(17179869184L))
      paramShort = (short)(s | 0x10); 
    return paramShort;
  }
  
  public final boolean getCanCall() {
    return ((this.flags & 0x4L) != 0L);
  }
  
  public final boolean getCanRead() {
    return ((this.flags & 0x2L) != 0L);
  }
  
  public final boolean getCanWrite() {
    return ((this.flags & 0x8L) != 0L);
  }
  
  public int getCode() {
    return this.id;
  }
  
  public final int getColumnNumber() {
    int j = this.position & 0xFFF;
    int i = j;
    if (j == 0)
      i = -1; 
    return i;
  }
  
  public final Object getConstantValue() {
    Expression expression = getValue();
    return (!(expression instanceof QuoteExp) || expression == QuoteExp.undefined_exp) ? null : ((QuoteExp)expression).getValue();
  }
  
  public final ScopeExp getContext() {
    return this.context;
  }
  
  public final String getFileName() {
    return this.filename;
  }
  
  public final boolean getFlag(long paramLong) {
    return ((this.flags & paramLong) != 0L);
  }
  
  public final int getLineNumber() {
    int j = this.position >> 12;
    int i = j;
    if (j == 0)
      i = -1; 
    return i;
  }
  
  public final String getName() {
    return (this.symbol == null) ? null : ((this.symbol instanceof Symbol) ? ((Symbol)this.symbol).getName() : this.symbol.toString());
  }
  
  public String getPublicId() {
    return null;
  }
  
  public final Object getSymbol() {
    return this.symbol;
  }
  
  public String getSystemId() {
    return this.filename;
  }
  
  public final Type getType() {
    if (this.type == null)
      setType((Type)Type.objectType); 
    return this.type;
  }
  
  public final Expression getTypeExp() {
    if (this.typeExp == null)
      setType((Type)Type.objectType); 
    return this.typeExp;
  }
  
  public final Expression getValue() {
    if (this.value == QuoteExp.undefined_exp) {
      if (this.field != null && (this.field.getModifiers() & 0x18) == 24 && !isIndirectBinding())
        try {
          this.value = new QuoteExp(this.field.getReflectField().get(null));
        } catch (Throwable throwable) {} 
      return this.value;
    } 
    if (this.value instanceof QuoteExp && getFlag(8192L) && this.value.getType() != this.type)
      try {
        Object object = ((QuoteExp)this.value).getValue();
        Type type = getType();
        this.value = new QuoteExp(type.coerceFromObject(object), type);
      } catch (Throwable throwable) {} 
    return this.value;
  }
  
  public Variable getVariable() {
    return this.var;
  }
  
  public final boolean hasConstantValue() {
    Expression expression = getValue();
    return (expression instanceof QuoteExp && expression != QuoteExp.undefined_exp);
  }
  
  public boolean ignorable() {
    if (!getCanRead() && !isPublic() && (!getCanWrite() || !getFlag(65536L))) {
      if (!getCanCall())
        return true; 
      Expression expression = getValue();
      if (expression != null && expression instanceof LambdaExp) {
        expression = expression;
        if (!expression.isHandlingTailCalls() || expression.getInlineOnly())
          return true; 
      } 
    } 
    return false;
  }
  
  public final boolean isAlias() {
    return ((this.flags & 0x100L) != 0L);
  }
  
  public boolean isCompiletimeConstant() {
    return (getFlag(16384L) && hasConstantValue());
  }
  
  public final boolean isFluid() {
    return ((this.flags & 0x10L) != 0L);
  }
  
  public final boolean isIndirectBinding() {
    return ((this.flags & 0x1L) != 0L);
  }
  
  public final boolean isLexical() {
    return ((this.flags & 0x10010010L) == 0L);
  }
  
  public final boolean isNamespaceDecl() {
    return ((this.flags & 0x200000L) != 0L);
  }
  
  public final boolean isPrivate() {
    return ((this.flags & 0x20L) != 0L);
  }
  
  public final boolean isProcedureDecl() {
    return ((this.flags & 0x80L) != 0L);
  }
  
  public final boolean isPublic() {
    return (this.context instanceof ModuleExp && (this.flags & 0x20L) == 0L);
  }
  
  public final boolean isSimple() {
    return ((this.flags & 0x40L) != 0L);
  }
  
  public boolean isStableSourceLocation() {
    return true;
  }
  
  public boolean isStatic() {
    boolean bool2 = true;
    if (this.field != null)
      return this.field.getStaticFlag(); 
    boolean bool1 = bool2;
    if (!getFlag(2048L)) {
      bool1 = bool2;
      if (!isCompiletimeConstant()) {
        if (getFlag(4096L))
          return false; 
        LambdaExp lambdaExp = this.context.currentLambda();
        if (lambdaExp instanceof ModuleExp) {
          bool1 = bool2;
          return !((ModuleExp)lambdaExp).isStatic() ? false : bool1;
        } 
        return false;
      } 
    } 
    return bool1;
  }
  
  public final boolean isThisParameter() {
    return (this.symbol == ThisExp.THIS_NAME);
  }
  
  public void load(AccessExp paramAccessExp, int paramInt, Compilation paramCompilation, Target paramTarget) {
    // Byte code:
    //   0: aload #4
    //   2: instanceof gnu/expr/IgnoreTarget
    //   5: ifeq -> 9
    //   8: return
    //   9: aload_1
    //   10: ifnonnull -> 93
    //   13: aconst_null
    //   14: astore #8
    //   16: aload_0
    //   17: invokevirtual isAlias : ()Z
    //   20: ifeq -> 102
    //   23: aload_0
    //   24: getfield value : Lgnu/expr/Expression;
    //   27: instanceof gnu/expr/ReferenceExp
    //   30: ifeq -> 102
    //   33: aload_0
    //   34: getfield value : Lgnu/expr/Expression;
    //   37: checkcast gnu/expr/ReferenceExp
    //   40: astore #9
    //   42: aload #9
    //   44: getfield binding : Lgnu/expr/Declaration;
    //   47: astore #10
    //   49: aload #10
    //   51: ifnull -> 102
    //   54: iload_2
    //   55: iconst_2
    //   56: iand
    //   57: ifeq -> 68
    //   60: aload #10
    //   62: invokevirtual isIndirectBinding : ()Z
    //   65: ifeq -> 102
    //   68: aload #8
    //   70: ifnull -> 81
    //   73: aload #10
    //   75: invokevirtual needsContext : ()Z
    //   78: ifne -> 102
    //   81: aload #10
    //   83: aload #9
    //   85: iload_2
    //   86: aload_3
    //   87: aload #4
    //   89: invokevirtual load : (Lgnu/expr/AccessExp;ILgnu/expr/Compilation;Lgnu/expr/Target;)V
    //   92: return
    //   93: aload_1
    //   94: invokevirtual contextDecl : ()Lgnu/expr/Declaration;
    //   97: astore #8
    //   99: goto -> 16
    //   102: aload_0
    //   103: invokevirtual isFluid : ()Z
    //   106: ifeq -> 132
    //   109: aload_0
    //   110: getfield context : Lgnu/expr/ScopeExp;
    //   113: instanceof gnu/expr/FluidLetExp
    //   116: ifeq -> 132
    //   119: aload_0
    //   120: getfield base : Lgnu/expr/Declaration;
    //   123: aload_1
    //   124: iload_2
    //   125: aload_3
    //   126: aload #4
    //   128: invokevirtual load : (Lgnu/expr/AccessExp;ILgnu/expr/Compilation;Lgnu/expr/Target;)V
    //   131: return
    //   132: aload_3
    //   133: invokevirtual getCode : ()Lgnu/bytecode/CodeAttr;
    //   136: astore #12
    //   138: aload_0
    //   139: invokevirtual getType : ()Lgnu/bytecode/Type;
    //   142: astore #10
    //   144: aload_0
    //   145: invokevirtual isIndirectBinding : ()Z
    //   148: ifne -> 337
    //   151: iload_2
    //   152: iconst_2
    //   153: iand
    //   154: ifeq -> 337
    //   157: aload_0
    //   158: getfield field : Lgnu/bytecode/Field;
    //   161: ifnonnull -> 192
    //   164: new java/lang/Error
    //   167: dup
    //   168: new java/lang/StringBuilder
    //   171: dup
    //   172: invokespecial <init> : ()V
    //   175: ldc_w 'internal error: cannot take location of '
    //   178: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   181: aload_0
    //   182: invokevirtual append : (Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   185: invokevirtual toString : ()Ljava/lang/String;
    //   188: invokespecial <init> : (Ljava/lang/String;)V
    //   191: athrow
    //   192: aload_3
    //   193: getfield immediate : Z
    //   196: istore #7
    //   198: aload_0
    //   199: getfield field : Lgnu/bytecode/Field;
    //   202: invokevirtual getStaticFlag : ()Z
    //   205: ifeq -> 266
    //   208: ldc_w 'gnu.kawa.reflect.StaticFieldLocation'
    //   211: invokestatic make : (Ljava/lang/String;)Lgnu/bytecode/ClassType;
    //   214: astore_1
    //   215: iload #7
    //   217: ifeq -> 261
    //   220: iconst_1
    //   221: istore_2
    //   222: aload_1
    //   223: ldc_w 'make'
    //   226: iload_2
    //   227: invokevirtual getDeclaredMethod : (Ljava/lang/String;I)Lgnu/bytecode/Method;
    //   230: astore #8
    //   232: iload #7
    //   234: ifeq -> 309
    //   237: aload_3
    //   238: aload_0
    //   239: invokevirtual compileConstant : (Ljava/lang/Object;)V
    //   242: aload #12
    //   244: aload #8
    //   246: invokevirtual emitInvokeStatic : (Lgnu/bytecode/Method;)V
    //   249: aload_1
    //   250: astore #8
    //   252: aload #4
    //   254: aload_3
    //   255: aload #8
    //   257: invokevirtual compileFromStack : (Lgnu/expr/Compilation;Lgnu/bytecode/Type;)V
    //   260: return
    //   261: iconst_2
    //   262: istore_2
    //   263: goto -> 222
    //   266: ldc_w 'gnu.kawa.reflect.FieldLocation'
    //   269: invokestatic make : (Ljava/lang/String;)Lgnu/bytecode/ClassType;
    //   272: astore_1
    //   273: iload #7
    //   275: ifeq -> 304
    //   278: iconst_2
    //   279: istore_2
    //   280: aload_1
    //   281: ldc_w 'make'
    //   284: iload_2
    //   285: invokevirtual getDeclaredMethod : (Ljava/lang/String;I)Lgnu/bytecode/Method;
    //   288: astore #9
    //   290: aload_0
    //   291: aload #8
    //   293: aload_3
    //   294: invokevirtual loadOwningObject : (Lgnu/expr/Declaration;Lgnu/expr/Compilation;)V
    //   297: aload #9
    //   299: astore #8
    //   301: goto -> 232
    //   304: iconst_3
    //   305: istore_2
    //   306: goto -> 280
    //   309: aload_3
    //   310: aload_0
    //   311: getfield field : Lgnu/bytecode/Field;
    //   314: invokevirtual getDeclaringClass : ()Lgnu/bytecode/ClassType;
    //   317: invokevirtual getName : ()Ljava/lang/String;
    //   320: invokevirtual compileConstant : (Ljava/lang/Object;)V
    //   323: aload_3
    //   324: aload_0
    //   325: getfield field : Lgnu/bytecode/Field;
    //   328: invokevirtual getName : ()Ljava/lang/String;
    //   331: invokevirtual compileConstant : (Ljava/lang/Object;)V
    //   334: goto -> 242
    //   337: aload_0
    //   338: getfield field : Lgnu/bytecode/Field;
    //   341: ifnull -> 636
    //   344: aload_3
    //   345: aload_0
    //   346: getfield field : Lgnu/bytecode/Field;
    //   349: invokevirtual getDeclaringClass : ()Lgnu/bytecode/ClassType;
    //   352: invokevirtual usedClass : (Lgnu/bytecode/Type;)V
    //   355: aload_3
    //   356: aload_0
    //   357: getfield field : Lgnu/bytecode/Field;
    //   360: invokevirtual getType : ()Lgnu/bytecode/Type;
    //   363: invokevirtual usedClass : (Lgnu/bytecode/Type;)V
    //   366: aload_0
    //   367: getfield field : Lgnu/bytecode/Field;
    //   370: invokevirtual getStaticFlag : ()Z
    //   373: ifne -> 624
    //   376: aload_0
    //   377: aload #8
    //   379: aload_3
    //   380: invokevirtual loadOwningObject : (Lgnu/expr/Declaration;Lgnu/expr/Compilation;)V
    //   383: aload #12
    //   385: aload_0
    //   386: getfield field : Lgnu/bytecode/Field;
    //   389: invokevirtual emitGetField : (Lgnu/bytecode/Field;)V
    //   392: aload #10
    //   394: astore #8
    //   396: aload_0
    //   397: invokevirtual isIndirectBinding : ()Z
    //   400: ifeq -> 252
    //   403: aload #10
    //   405: astore #8
    //   407: iload_2
    //   408: iconst_2
    //   409: iand
    //   410: ifne -> 252
    //   413: aload_1
    //   414: ifnull -> 967
    //   417: aload_1
    //   418: invokevirtual getFileName : ()Ljava/lang/String;
    //   421: astore #8
    //   423: aload #8
    //   425: ifnull -> 967
    //   428: aload_1
    //   429: invokevirtual getLineNumber : ()I
    //   432: istore #5
    //   434: iload #5
    //   436: ifle -> 967
    //   439: ldc_w 'gnu.mapping.UnboundLocationException'
    //   442: invokestatic make : (Ljava/lang/String;)Lgnu/bytecode/ClassType;
    //   445: astore #9
    //   447: aload #12
    //   449: invokevirtual isInTry : ()Z
    //   452: istore #7
    //   454: aload_1
    //   455: invokevirtual getColumnNumber : ()I
    //   458: istore #6
    //   460: new gnu/bytecode/Label
    //   463: dup
    //   464: aload #12
    //   466: invokespecial <init> : (Lgnu/bytecode/CodeAttr;)V
    //   469: astore_1
    //   470: aload_1
    //   471: aload #12
    //   473: invokevirtual define : (Lgnu/bytecode/CodeAttr;)V
    //   476: aload #12
    //   478: getstatic gnu/expr/Compilation.getLocationMethod : Lgnu/bytecode/Method;
    //   481: invokevirtual emitInvokeVirtual : (Lgnu/bytecode/Method;)V
    //   484: new gnu/bytecode/Label
    //   487: dup
    //   488: aload #12
    //   490: invokespecial <init> : (Lgnu/bytecode/CodeAttr;)V
    //   493: astore #10
    //   495: aload #10
    //   497: aload #12
    //   499: invokevirtual define : (Lgnu/bytecode/CodeAttr;)V
    //   502: new gnu/bytecode/Label
    //   505: dup
    //   506: aload #12
    //   508: invokespecial <init> : (Lgnu/bytecode/CodeAttr;)V
    //   511: astore #11
    //   513: aload #11
    //   515: aload #12
    //   517: invokevirtual setTypes : (Lgnu/bytecode/CodeAttr;)V
    //   520: iload #7
    //   522: ifeq -> 950
    //   525: aload #12
    //   527: aload #11
    //   529: invokevirtual emitGoto : (Lgnu/bytecode/Label;)V
    //   532: iconst_0
    //   533: istore_2
    //   534: iload #7
    //   536: ifne -> 547
    //   539: aload #12
    //   541: aload #11
    //   543: invokevirtual beginFragment : (Lgnu/bytecode/Label;)I
    //   546: istore_2
    //   547: aload #12
    //   549: aload_1
    //   550: aload #10
    //   552: aload #9
    //   554: invokevirtual addHandler : (Lgnu/bytecode/Label;Lgnu/bytecode/Label;Lgnu/bytecode/ClassType;)V
    //   557: aload #12
    //   559: aload #9
    //   561: invokevirtual emitDup : (Lgnu/bytecode/Type;)V
    //   564: aload #12
    //   566: aload #8
    //   568: invokevirtual emitPushString : (Ljava/lang/String;)V
    //   571: aload #12
    //   573: iload #5
    //   575: invokevirtual emitPushInt : (I)V
    //   578: aload #12
    //   580: iload #6
    //   582: invokevirtual emitPushInt : (I)V
    //   585: aload #12
    //   587: aload #9
    //   589: ldc_w 'setLine'
    //   592: iconst_3
    //   593: invokevirtual getDeclaredMethod : (Ljava/lang/String;I)Lgnu/bytecode/Method;
    //   596: invokevirtual emitInvokeVirtual : (Lgnu/bytecode/Method;)V
    //   599: aload #12
    //   601: invokevirtual emitThrow : ()V
    //   604: iload #7
    //   606: ifeq -> 958
    //   609: aload #11
    //   611: aload #12
    //   613: invokevirtual define : (Lgnu/bytecode/CodeAttr;)V
    //   616: getstatic gnu/bytecode/Type.pointer_type : Lgnu/bytecode/ClassType;
    //   619: astore #8
    //   621: goto -> 252
    //   624: aload #12
    //   626: aload_0
    //   627: getfield field : Lgnu/bytecode/Field;
    //   630: invokevirtual emitGetStatic : (Lgnu/bytecode/Field;)V
    //   633: goto -> 392
    //   636: aload_0
    //   637: invokevirtual isIndirectBinding : ()Z
    //   640: ifeq -> 753
    //   643: aload_3
    //   644: getfield immediate : Z
    //   647: ifeq -> 753
    //   650: aload_0
    //   651: invokevirtual getVariable : ()Lgnu/bytecode/Variable;
    //   654: ifnonnull -> 753
    //   657: invokestatic getCurrent : ()Lgnu/mapping/Environment;
    //   660: astore #13
    //   662: aload_0
    //   663: getfield symbol : Ljava/lang/Object;
    //   666: instanceof gnu/mapping/Symbol
    //   669: ifeq -> 736
    //   672: aload_0
    //   673: getfield symbol : Ljava/lang/Object;
    //   676: checkcast gnu/mapping/Symbol
    //   679: astore #8
    //   681: aconst_null
    //   682: astore #11
    //   684: aload #11
    //   686: astore #9
    //   688: aload_0
    //   689: invokevirtual isProcedureDecl : ()Z
    //   692: ifeq -> 714
    //   695: aload #11
    //   697: astore #9
    //   699: aload_3
    //   700: invokevirtual getLanguage : ()Lgnu/expr/Language;
    //   703: invokevirtual hasSeparateFunctionNamespace : ()Z
    //   706: ifeq -> 714
    //   709: getstatic gnu/mapping/EnvironmentKey.FUNCTION : Ljava/lang/Object;
    //   712: astore #9
    //   714: aload_3
    //   715: aload #13
    //   717: aload #8
    //   719: aload #9
    //   721: invokevirtual getLocation : (Lgnu/mapping/Symbol;Ljava/lang/Object;)Lgnu/mapping/Location;
    //   724: getstatic gnu/expr/Compilation.typeLocation : Lgnu/bytecode/ClassType;
    //   727: invokestatic pushValue : (Lgnu/bytecode/Type;)Lgnu/expr/Target;
    //   730: invokevirtual compileConstant : (Ljava/lang/Object;Lgnu/expr/Target;)V
    //   733: goto -> 392
    //   736: aload #13
    //   738: aload_0
    //   739: getfield symbol : Ljava/lang/Object;
    //   742: invokevirtual toString : ()Ljava/lang/String;
    //   745: invokevirtual getSymbol : (Ljava/lang/String;)Lgnu/mapping/Symbol;
    //   748: astore #8
    //   750: goto -> 681
    //   753: aload_3
    //   754: getfield immediate : Z
    //   757: ifeq -> 780
    //   760: aload_0
    //   761: invokevirtual getConstantValue : ()Ljava/lang/Object;
    //   764: astore #8
    //   766: aload #8
    //   768: ifnull -> 780
    //   771: aload_3
    //   772: aload #8
    //   774: aload #4
    //   776: invokevirtual compileConstant : (Ljava/lang/Object;Lgnu/expr/Target;)V
    //   779: return
    //   780: aload_0
    //   781: getfield value : Lgnu/expr/Expression;
    //   784: getstatic gnu/expr/QuoteExp.undefined_exp : Lgnu/expr/QuoteExp;
    //   787: if_acmpeq -> 834
    //   790: aload_0
    //   791: invokevirtual ignorable : ()Z
    //   794: ifeq -> 834
    //   797: aload_0
    //   798: getfield value : Lgnu/expr/Expression;
    //   801: instanceof gnu/expr/LambdaExp
    //   804: ifeq -> 823
    //   807: aload_0
    //   808: getfield value : Lgnu/expr/Expression;
    //   811: checkcast gnu/expr/LambdaExp
    //   814: getfield outer : Lgnu/expr/ScopeExp;
    //   817: instanceof gnu/expr/ModuleExp
    //   820: ifne -> 834
    //   823: aload_0
    //   824: getfield value : Lgnu/expr/Expression;
    //   827: aload_3
    //   828: aload #4
    //   830: invokevirtual compile : (Lgnu/expr/Compilation;Lgnu/expr/Target;)V
    //   833: return
    //   834: aload_0
    //   835: invokevirtual getVariable : ()Lgnu/bytecode/Variable;
    //   838: astore #9
    //   840: aload_0
    //   841: getfield context : Lgnu/expr/ScopeExp;
    //   844: instanceof gnu/expr/ClassExp
    //   847: ifeq -> 923
    //   850: aload #9
    //   852: ifnonnull -> 923
    //   855: aload_0
    //   856: ldc2_w 128
    //   859: invokevirtual getFlag : (J)Z
    //   862: ifne -> 923
    //   865: aload_0
    //   866: getfield context : Lgnu/expr/ScopeExp;
    //   869: checkcast gnu/expr/ClassExp
    //   872: astore #8
    //   874: aload #8
    //   876: invokevirtual isMakingClassPair : ()Z
    //   879: ifeq -> 923
    //   882: ldc_w 'get'
    //   885: aload_0
    //   886: invokevirtual getName : ()Ljava/lang/String;
    //   889: invokestatic slotToMethodName : (Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
    //   892: astore #9
    //   894: aload #8
    //   896: getfield type : Lgnu/bytecode/ClassType;
    //   899: aload #9
    //   901: iconst_0
    //   902: invokevirtual getDeclaredMethod : (Ljava/lang/String;I)Lgnu/bytecode/Method;
    //   905: astore #9
    //   907: aload #8
    //   909: aload_3
    //   910: invokevirtual loadHeapFrame : (Lgnu/expr/Compilation;)V
    //   913: aload #12
    //   915: aload #9
    //   917: invokevirtual emitInvoke : (Lgnu/bytecode/Method;)V
    //   920: goto -> 392
    //   923: aload #9
    //   925: astore #8
    //   927: aload #9
    //   929: ifnonnull -> 940
    //   932: aload_0
    //   933: aload #12
    //   935: invokevirtual allocateVariable : (Lgnu/bytecode/CodeAttr;)Lgnu/bytecode/Variable;
    //   938: astore #8
    //   940: aload #12
    //   942: aload #8
    //   944: invokevirtual emitLoad : (Lgnu/bytecode/Variable;)V
    //   947: goto -> 392
    //   950: aload #12
    //   952: invokevirtual setUnreachable : ()V
    //   955: goto -> 532
    //   958: aload #12
    //   960: iload_2
    //   961: invokevirtual endFragment : (I)V
    //   964: goto -> 616
    //   967: aload #12
    //   969: getstatic gnu/expr/Compilation.getLocationMethod : Lgnu/bytecode/Method;
    //   972: invokevirtual emitInvokeVirtual : (Lgnu/bytecode/Method;)V
    //   975: goto -> 616
  }
  
  void loadOwningObject(Declaration paramDeclaration, Compilation paramCompilation) {
    Declaration declaration = paramDeclaration;
    if (paramDeclaration == null)
      declaration = this.base; 
    if (declaration != null) {
      declaration.load(null, 0, paramCompilation, Target.pushObject);
      return;
    } 
    getContext().currentLambda().loadHeapFrame(paramCompilation);
  }
  
  public void makeField(ClassType paramClassType, Compilation paramCompilation, Expression paramExpression) {
    // Byte code:
    //   0: aload_0
    //   1: invokevirtual needsExternalAccess : ()Z
    //   4: istore #7
    //   6: iconst_0
    //   7: istore #5
    //   9: aload_0
    //   10: ldc2_w 16384
    //   13: invokevirtual getFlag : (J)Z
    //   16: istore #8
    //   18: aload_0
    //   19: ldc2_w 8192
    //   22: invokevirtual getFlag : (J)Z
    //   25: istore #9
    //   27: aload_2
    //   28: getfield immediate : Z
    //   31: ifeq -> 59
    //   34: aload_0
    //   35: getfield context : Lgnu/expr/ScopeExp;
    //   38: instanceof gnu/expr/ModuleExp
    //   41: ifeq -> 59
    //   44: iload #8
    //   46: ifne -> 59
    //   49: iload #9
    //   51: ifne -> 59
    //   54: aload_0
    //   55: iconst_1
    //   56: invokevirtual setIndirectBinding : (Z)V
    //   59: aload_0
    //   60: invokevirtual isPublic : ()Z
    //   63: ifne -> 78
    //   66: iload #7
    //   68: ifne -> 78
    //   71: aload_2
    //   72: getfield immediate : Z
    //   75: ifeq -> 83
    //   78: iconst_0
    //   79: iconst_1
    //   80: ior
    //   81: istore #5
    //   83: aload_0
    //   84: invokevirtual isStatic : ()Z
    //   87: ifne -> 139
    //   90: aload_0
    //   91: ldc2_w 268501008
    //   94: invokevirtual getFlag : (J)Z
    //   97: ifeq -> 114
    //   100: aload_0
    //   101: invokevirtual isIndirectBinding : ()Z
    //   104: ifeq -> 114
    //   107: aload_0
    //   108: invokevirtual isAlias : ()Z
    //   111: ifeq -> 139
    //   114: iload #5
    //   116: istore #4
    //   118: aload_3
    //   119: instanceof gnu/expr/ClassExp
    //   122: ifeq -> 146
    //   125: iload #5
    //   127: istore #4
    //   129: aload_3
    //   130: checkcast gnu/expr/LambdaExp
    //   133: invokevirtual getNeedsClosureEnv : ()Z
    //   136: ifne -> 146
    //   139: iload #5
    //   141: bipush #8
    //   143: ior
    //   144: istore #4
    //   146: aload_0
    //   147: invokevirtual isIndirectBinding : ()Z
    //   150: ifne -> 200
    //   153: iload #4
    //   155: istore #5
    //   157: iload #8
    //   159: ifeq -> 231
    //   162: aload_0
    //   163: invokevirtual shouldEarlyInit : ()Z
    //   166: ifne -> 200
    //   169: iload #4
    //   171: istore #5
    //   173: aload_0
    //   174: getfield context : Lgnu/expr/ScopeExp;
    //   177: instanceof gnu/expr/ModuleExp
    //   180: ifeq -> 231
    //   183: iload #4
    //   185: istore #5
    //   187: aload_0
    //   188: getfield context : Lgnu/expr/ScopeExp;
    //   191: checkcast gnu/expr/ModuleExp
    //   194: invokevirtual staticInitRun : ()Z
    //   197: ifeq -> 231
    //   200: aload_0
    //   201: getfield context : Lgnu/expr/ScopeExp;
    //   204: instanceof gnu/expr/ClassExp
    //   207: ifne -> 224
    //   210: iload #4
    //   212: istore #5
    //   214: aload_0
    //   215: getfield context : Lgnu/expr/ScopeExp;
    //   218: instanceof gnu/expr/ModuleExp
    //   221: ifeq -> 231
    //   224: iload #4
    //   226: bipush #16
    //   228: ior
    //   229: istore #5
    //   231: aload_0
    //   232: invokevirtual getType : ()Lgnu/bytecode/Type;
    //   235: invokevirtual getImplementationType : ()Lgnu/bytecode/Type;
    //   238: astore #10
    //   240: aload #10
    //   242: astore #12
    //   244: aload_0
    //   245: invokevirtual isIndirectBinding : ()Z
    //   248: ifeq -> 271
    //   251: aload #10
    //   253: astore #12
    //   255: aload #10
    //   257: getstatic gnu/expr/Compilation.typeLocation : Lgnu/bytecode/ClassType;
    //   260: invokevirtual isSubtype : (Lgnu/bytecode/Type;)Z
    //   263: ifne -> 271
    //   266: getstatic gnu/expr/Compilation.typeLocation : Lgnu/bytecode/ClassType;
    //   269: astore #12
    //   271: aload_0
    //   272: invokevirtual ignorable : ()Z
    //   275: ifne -> 548
    //   278: aload_0
    //   279: invokevirtual getName : ()Ljava/lang/String;
    //   282: astore #10
    //   284: aload #10
    //   286: ifnonnull -> 362
    //   289: ldc_w '$unnamed$0'
    //   292: astore #11
    //   294: ldc_w '$unnamed$0'
    //   297: invokevirtual length : ()I
    //   300: iconst_2
    //   301: isub
    //   302: istore #4
    //   304: iconst_0
    //   305: istore #6
    //   307: aload_1
    //   308: aload #11
    //   310: invokevirtual getDeclaredField : (Ljava/lang/String;)Lgnu/bytecode/Field;
    //   313: ifnull -> 460
    //   316: new java/lang/StringBuilder
    //   319: dup
    //   320: invokespecial <init> : ()V
    //   323: aload #11
    //   325: iconst_0
    //   326: iload #4
    //   328: invokevirtual substring : (II)Ljava/lang/String;
    //   331: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   334: bipush #36
    //   336: invokevirtual append : (C)Ljava/lang/StringBuilder;
    //   339: astore #10
    //   341: iload #6
    //   343: iconst_1
    //   344: iadd
    //   345: istore #6
    //   347: aload #10
    //   349: iload #6
    //   351: invokevirtual append : (I)Ljava/lang/StringBuilder;
    //   354: invokevirtual toString : ()Ljava/lang/String;
    //   357: astore #11
    //   359: goto -> 307
    //   362: aload #10
    //   364: invokestatic mangleNameIfNeeded : (Ljava/lang/String;)Ljava/lang/String;
    //   367: astore #11
    //   369: aload #11
    //   371: astore #10
    //   373: aload_0
    //   374: ldc2_w 65536
    //   377: invokevirtual getFlag : (J)Z
    //   380: ifeq -> 405
    //   383: new java/lang/StringBuilder
    //   386: dup
    //   387: invokespecial <init> : ()V
    //   390: ldc 'loc$'
    //   392: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   395: aload #11
    //   397: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   400: invokevirtual toString : ()Ljava/lang/String;
    //   403: astore #10
    //   405: aload #10
    //   407: astore #11
    //   409: iload #7
    //   411: ifeq -> 450
    //   414: aload #10
    //   416: astore #11
    //   418: aload_0
    //   419: ldc2_w 1073741824
    //   422: invokevirtual getFlag : (J)Z
    //   425: ifne -> 450
    //   428: new java/lang/StringBuilder
    //   431: dup
    //   432: invokespecial <init> : ()V
    //   435: ldc '$Prvt$'
    //   437: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   440: aload #10
    //   442: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   445: invokevirtual toString : ()Ljava/lang/String;
    //   448: astore #11
    //   450: aload #11
    //   452: invokevirtual length : ()I
    //   455: istore #4
    //   457: goto -> 304
    //   460: aload_0
    //   461: aload_1
    //   462: aload #11
    //   464: aload #12
    //   466: iload #5
    //   468: invokevirtual addField : (Ljava/lang/String;Lgnu/bytecode/Type;I)Lgnu/bytecode/Field;
    //   471: putfield field : Lgnu/bytecode/Field;
    //   474: aload_3
    //   475: instanceof gnu/expr/QuoteExp
    //   478: ifeq -> 548
    //   481: aload_3
    //   482: checkcast gnu/expr/QuoteExp
    //   485: invokevirtual getValue : ()Ljava/lang/Object;
    //   488: astore #10
    //   490: aload_0
    //   491: getfield field : Lgnu/bytecode/Field;
    //   494: invokevirtual getStaticFlag : ()Z
    //   497: ifeq -> 580
    //   500: aload #10
    //   502: invokevirtual getClass : ()Ljava/lang/Class;
    //   505: invokevirtual getName : ()Ljava/lang/String;
    //   508: aload #12
    //   510: invokevirtual getName : ()Ljava/lang/String;
    //   513: invokevirtual equals : (Ljava/lang/Object;)Z
    //   516: ifeq -> 580
    //   519: aload_2
    //   520: getfield litTable : Lgnu/expr/LitTable;
    //   523: aload #10
    //   525: invokevirtual findLiteral : (Ljava/lang/Object;)Lgnu/expr/Literal;
    //   528: astore_1
    //   529: aload_1
    //   530: getfield field : Lgnu/bytecode/Field;
    //   533: ifnonnull -> 548
    //   536: aload_1
    //   537: aload_0
    //   538: getfield field : Lgnu/bytecode/Field;
    //   541: aload_2
    //   542: getfield litTable : Lgnu/expr/LitTable;
    //   545: invokevirtual assign : (Lgnu/bytecode/Field;Lgnu/expr/LitTable;)V
    //   548: aload_0
    //   549: invokevirtual shouldEarlyInit : ()Z
    //   552: ifne -> 579
    //   555: aload_0
    //   556: invokevirtual isIndirectBinding : ()Z
    //   559: ifne -> 573
    //   562: aload_3
    //   563: ifnull -> 579
    //   566: aload_3
    //   567: instanceof gnu/expr/ClassExp
    //   570: ifne -> 579
    //   573: aload_0
    //   574: aload_3
    //   575: aload_2
    //   576: invokestatic create : (Lgnu/expr/Declaration;Lgnu/expr/Expression;Lgnu/expr/Compilation;)V
    //   579: return
    //   580: aload #12
    //   582: instanceof gnu/bytecode/PrimType
    //   585: ifne -> 602
    //   588: ldc_w 'java.lang.String'
    //   591: aload #12
    //   593: invokevirtual getName : ()Ljava/lang/String;
    //   596: invokevirtual equals : (Ljava/lang/Object;)Z
    //   599: ifeq -> 548
    //   602: aload #10
    //   604: astore_2
    //   605: aload #10
    //   607: instanceof gnu/text/Char
    //   610: ifeq -> 625
    //   613: aload #10
    //   615: checkcast gnu/text/Char
    //   618: invokevirtual intValue : ()I
    //   621: invokestatic make : (I)Lgnu/math/IntNum;
    //   624: astore_2
    //   625: aload_0
    //   626: getfield field : Lgnu/bytecode/Field;
    //   629: aload_2
    //   630: aload_1
    //   631: invokevirtual setConstantValue : (Ljava/lang/Object;Lgnu/bytecode/ClassType;)V
    //   634: return
  }
  
  public void makeField(Compilation paramCompilation, Expression paramExpression) {
    setSimple(false);
    makeField(paramCompilation.mainClass, paramCompilation, paramExpression);
  }
  
  Location makeIndirectLocationFor() {
    if (this.symbol instanceof Symbol) {
      Symbol symbol1 = (Symbol)this.symbol;
      return (Location)Location.make(symbol1);
    } 
    Symbol symbol = Namespace.EmptyNamespace.getSymbol(this.symbol.toString().intern());
    return (Location)Location.make(symbol);
  }
  
  public void maybeIndirectBinding(Compilation paramCompilation) {
    if ((isLexical() && !(this.context instanceof ModuleExp)) || this.context == paramCompilation.mainLambda)
      setIndirectBinding(true); 
  }
  
  public final boolean needsContext() {
    return (this.base == null && this.field != null && !this.field.getStaticFlag());
  }
  
  public final boolean needsExternalAccess() {
    return ((this.flags & 0x80020L) == 524320L || (this.flags & 0x200020L) == 2097184L);
  }
  
  public boolean needsInit() {
    return (!ignorable() && (this.value != QuoteExp.nullExp || this.base == null));
  }
  
  public final Declaration nextDecl() {
    return this.next;
  }
  
  public void noteValue(Expression paramExpression) {
    if (this.value == QuoteExp.undefined_exp) {
      if (paramExpression instanceof LambdaExp)
        ((LambdaExp)paramExpression).nameDecl = this; 
      this.value = paramExpression;
      return;
    } 
    if (this.value != paramExpression) {
      if (this.value instanceof LambdaExp)
        ((LambdaExp)this.value).nameDecl = null; 
      this.value = null;
      return;
    } 
  }
  
  public void printInfo(OutPort paramOutPort) {
    StringBuffer stringBuffer = new StringBuffer();
    printInfo(stringBuffer);
    paramOutPort.print(stringBuffer.toString());
  }
  
  public void printInfo(StringBuffer paramStringBuffer) {
    paramStringBuffer.append(this.symbol);
    paramStringBuffer.append('/');
    paramStringBuffer.append(this.id);
    paramStringBuffer.append("/fl:");
    paramStringBuffer.append(Long.toHexString(this.flags));
    if (ignorable())
      paramStringBuffer.append("(ignorable)"); 
    Expression expression = this.typeExp;
    Type type = getType();
    if (expression != null && !(expression instanceof QuoteExp)) {
      paramStringBuffer.append("::");
      paramStringBuffer.append(expression);
    } else if (this.type != null && type != Type.pointer_type) {
      paramStringBuffer.append("::");
      paramStringBuffer.append(type.getName());
    } 
    if (this.base != null) {
      paramStringBuffer.append("(base:#");
      paramStringBuffer.append(this.base.id);
      paramStringBuffer.append(')');
    } 
  }
  
  public void pushIndirectBinding(Compilation paramCompilation) {
    CodeAttr codeAttr = paramCompilation.getCode();
    codeAttr.emitPushString(getName());
    if (this.makeLocationMethod == null) {
      ClassType classType1 = Type.pointer_type;
      ClassType classType2 = Type.string_type;
      ClassType classType3 = Compilation.typeLocation;
      ClassType classType4 = Compilation.typeLocation;
      this.makeLocationMethod = classType3.addMethod("make", new Type[] { (Type)classType1, (Type)classType2 }, (Type)classType4, 9);
    } 
    codeAttr.emitInvokeStatic(this.makeLocationMethod);
  }
  
  public final void setAlias(boolean paramBoolean) {
    setFlag(paramBoolean, 256L);
  }
  
  public final void setCanCall() {
    setFlag(true, 4L);
    if (this.base != null)
      this.base.setCanRead(); 
  }
  
  public final void setCanCall(boolean paramBoolean) {
    setFlag(paramBoolean, 4L);
  }
  
  public final void setCanRead() {
    setFlag(true, 2L);
    if (this.base != null)
      this.base.setCanRead(); 
  }
  
  public final void setCanRead(boolean paramBoolean) {
    setFlag(paramBoolean, 2L);
  }
  
  public final void setCanWrite() {
    this.flags |= 0x8L;
    if (this.base != null)
      this.base.setCanRead(); 
  }
  
  public final void setCanWrite(boolean paramBoolean) {
    if (paramBoolean) {
      this.flags |= 0x8L;
      return;
    } 
    this.flags &= 0xFFFFFFFFFFFFFFF7L;
  }
  
  public void setCode(int paramInt) {
    if (paramInt >= 0)
      throw new Error("code must be negative"); 
    this.id = paramInt;
  }
  
  public final void setFile(String paramString) {
    this.filename = paramString;
  }
  
  public final void setFlag(long paramLong) {
    this.flags |= paramLong;
  }
  
  public final void setFlag(boolean paramBoolean, long paramLong) {
    if (paramBoolean) {
      this.flags |= paramLong;
      return;
    } 
    this.flags &= 0xFFFFFFFFFFFFFFFFL ^ paramLong;
  }
  
  public final void setFluid(boolean paramBoolean) {
    setFlag(paramBoolean, 16L);
  }
  
  public final void setIndirectBinding(boolean paramBoolean) {
    setFlag(paramBoolean, 1L);
  }
  
  public final void setLine(int paramInt) {
    setLine(paramInt, 0);
  }
  
  public final void setLine(int paramInt1, int paramInt2) {
    int i = paramInt1;
    if (paramInt1 < 0)
      i = 0; 
    paramInt1 = paramInt2;
    if (paramInt2 < 0)
      paramInt1 = 0; 
    this.position = (i << 12) + paramInt1;
  }
  
  public final void setLocation(SourceLocator paramSourceLocator) {
    this.filename = paramSourceLocator.getFileName();
    setLine(paramSourceLocator.getLineNumber(), paramSourceLocator.getColumnNumber());
  }
  
  public final void setName(Object paramObject) {
    this.symbol = paramObject;
  }
  
  public final void setNext(Declaration paramDeclaration) {
    this.next = paramDeclaration;
  }
  
  public final void setPrivate(boolean paramBoolean) {
    setFlag(paramBoolean, 32L);
  }
  
  public final void setProcedureDecl(boolean paramBoolean) {
    setFlag(paramBoolean, 128L);
  }
  
  public final void setSimple(boolean paramBoolean) {
    setFlag(paramBoolean, 64L);
    if (this.var != null && !this.var.isParameter())
      this.var.setSimple(paramBoolean); 
  }
  
  public final void setSymbol(Object paramObject) {
    this.symbol = paramObject;
  }
  
  public final void setSyntax() {
    setSimple(false);
    setFlag(536920064L);
  }
  
  public final void setType(Type paramType) {
    this.type = paramType;
    if (this.var != null)
      this.var.setType(paramType); 
    this.typeExp = QuoteExp.getInstance(paramType);
  }
  
  public final void setTypeExp(Expression paramExpression) {
    Type type1;
    ClassType classType;
    this.typeExp = paramExpression;
    if (paramExpression instanceof TypeValue) {
      type1 = ((TypeValue)paramExpression).getImplementationType();
    } else {
      type1 = Language.getDefaultLanguage().getTypeFor((Expression)type1, false);
    } 
    Type type2 = type1;
    if (type1 == null)
      classType = Type.pointer_type; 
    this.type = (Type)classType;
    if (this.var != null)
      this.var.setType((Type)classType); 
  }
  
  public final void setValue(Expression paramExpression) {
    this.value = paramExpression;
  }
  
  boolean shouldEarlyInit() {
    return (getFlag(536870912L) || isCompiletimeConstant());
  }
  
  public String toString() {
    return "Declaration[" + this.symbol + '/' + this.id + ']';
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/gnu/expr/Declaration.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */