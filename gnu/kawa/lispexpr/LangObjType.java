package gnu.kawa.lispexpr;

import gnu.bytecode.ClassType;
import gnu.bytecode.CodeAttr;
import gnu.bytecode.Field;
import gnu.bytecode.Filter;
import gnu.bytecode.Method;
import gnu.bytecode.ObjectType;
import gnu.bytecode.Type;
import gnu.bytecode.Variable;
import gnu.expr.ApplyExp;
import gnu.expr.Compilation;
import gnu.expr.Declaration;
import gnu.expr.Expression;
import gnu.expr.Language;
import gnu.expr.PrimProcedure;
import gnu.expr.Target;
import gnu.expr.TypeValue;
import gnu.kawa.functions.MakeList;
import gnu.kawa.reflect.InstanceOf;
import gnu.mapping.Procedure;
import gnu.mapping.WrongType;
import gnu.math.DFloNum;
import gnu.math.IntNum;
import gnu.math.Numeric;
import gnu.math.RatNum;
import gnu.math.RealNum;
import gnu.text.FilePath;
import gnu.text.Path;
import gnu.text.URIPath;
import java.util.List;

public class LangObjType extends ObjectType implements TypeValue {
  private static final int CLASSTYPE_TYPE_CODE = 6;
  
  private static final int CLASS_TYPE_CODE = 4;
  
  private static final int DFLONUM_TYPE_CODE = 15;
  
  private static final int FILEPATH_TYPE_CODE = 2;
  
  private static final int INTEGER_TYPE_CODE = 7;
  
  private static final int LIST_TYPE_CODE = 11;
  
  private static final int NUMERIC_TYPE_CODE = 10;
  
  private static final int PATH_TYPE_CODE = 1;
  
  private static final int RATIONAL_TYPE_CODE = 8;
  
  private static final int REAL_TYPE_CODE = 9;
  
  private static final int REGEX_TYPE_CODE = 14;
  
  private static final int STRING_TYPE_CODE = 13;
  
  private static final int TYPE_TYPE_CODE = 5;
  
  public static final LangObjType URIType;
  
  private static final int URI_TYPE_CODE = 3;
  
  static final String VARARGS_SUFFIX = "";
  
  private static final int VECTOR_TYPE_CODE = 12;
  
  public static final LangObjType dflonumType;
  
  public static final LangObjType filepathType;
  
  public static final LangObjType integerType;
  
  public static final LangObjType listType;
  
  static PrimProcedure makeFilepathProc;
  
  static PrimProcedure makePathProc;
  
  static PrimProcedure makeURIProc;
  
  public static final LangObjType numericType;
  
  public static final LangObjType pathType = new LangObjType("path", "gnu.text.Path", 1);
  
  public static final LangObjType rationalType;
  
  public static final LangObjType realType;
  
  public static final LangObjType regexType;
  
  public static final LangObjType stringType;
  
  static final ClassType typeArithmetic;
  
  public static final LangObjType typeClass;
  
  public static final LangObjType typeClassType;
  
  public static final ClassType typeLangObjType;
  
  public static final LangObjType typeType;
  
  public static final LangObjType vectorType;
  
  ClassType implementationType;
  
  final int typeCode;
  
  static {
    filepathType = new LangObjType("filepath", "gnu.text.FilePath", 2);
    URIType = new LangObjType("URI", "gnu.text.URIPath", 3);
    typeClass = new LangObjType("class", "java.lang.Class", 4);
    typeType = new LangObjType("type", "gnu.bytecode.Type", 5);
    typeClassType = new LangObjType("class-type", "gnu.bytecode.ClassType", 6);
    numericType = new LangObjType("number", "gnu.math.Numeric", 10);
    realType = new LangObjType("real", "gnu.math.RealNum", 9);
    rationalType = new LangObjType("rational", "gnu.math.RatNum", 8);
    integerType = new LangObjType("integer", "gnu.math.IntNum", 7);
    dflonumType = new LangObjType("DFloNum", "gnu.math.DFloNum", 15);
    vectorType = new LangObjType("vector", "gnu.lists.FVector", 12);
    regexType = new LangObjType("regex", "java.util.regex.Pattern", 14);
    stringType = new LangObjType("string", "java.lang.CharSequence", 13);
    listType = new LangObjType("list", "gnu.lists.LList", 11);
    typeArithmetic = ClassType.make("gnu.kawa.functions.Arithmetic");
    makePathProc = new PrimProcedure("gnu.text.Path", "valueOf", 1);
    makeFilepathProc = new PrimProcedure("gnu.text.FilePath", "makeFilePath", 1);
    makeURIProc = new PrimProcedure("gnu.text.URIPath", "makeURI", 1);
    typeLangObjType = ClassType.make("gnu.kawa.lispexpr.LangObjType");
  }
  
  LangObjType(String paramString1, String paramString2, int paramInt) {
    super(paramString1);
    this.implementationType = ClassType.make(paramString2);
    this.typeCode = paramInt;
    setSignature(this.implementationType.getSignature());
  }
  
  public static DFloNum coerceDFloNum(Object paramObject) {
    DFloNum dFloNum = DFloNum.asDFloNumOrNull(paramObject);
    if (dFloNum == null && paramObject != null)
      throw new WrongType(-4, paramObject, dflonumType); 
    return dFloNum;
  }
  
  public static IntNum coerceIntNum(Object paramObject) {
    IntNum intNum = IntNum.asIntNumOrNull(paramObject);
    if (intNum == null && paramObject != null)
      throw new WrongType(-4, paramObject, integerType); 
    return intNum;
  }
  
  public static Numeric coerceNumeric(Object paramObject) {
    Numeric numeric = Numeric.asNumericOrNull(paramObject);
    if (numeric == null && paramObject != null)
      throw new WrongType(-4, paramObject, numericType); 
    return numeric;
  }
  
  public static RatNum coerceRatNum(Object paramObject) {
    RatNum ratNum = RatNum.asRatNumOrNull(paramObject);
    if (ratNum == null && paramObject != null)
      throw new WrongType(-4, paramObject, rationalType); 
    return ratNum;
  }
  
  public static RealNum coerceRealNum(Object paramObject) {
    RealNum realNum = RealNum.asRealNumOrNull(paramObject);
    if (realNum == null && paramObject != null)
      throw new WrongType(-4, paramObject, realType); 
    return realNum;
  }
  
  public static Class coerceToClass(Object paramObject) {
    Class clazz = coerceToClassOrNull(paramObject);
    if (clazz == null && paramObject != null)
      throw new ClassCastException("cannot cast " + paramObject + " to type"); 
    return clazz;
  }
  
  public static Class coerceToClassOrNull(Object paramObject) {
    return (paramObject instanceof Class) ? (Class)paramObject : ((paramObject instanceof Type && paramObject instanceof ClassType && !(paramObject instanceof gnu.expr.PairClassType)) ? ((ClassType)paramObject).getReflectClass() : null);
  }
  
  public static ClassType coerceToClassType(Object paramObject) {
    ClassType classType = coerceToClassTypeOrNull(paramObject);
    if (classType == null && paramObject != null)
      throw new ClassCastException("cannot cast " + paramObject + " to class-type"); 
    return classType;
  }
  
  public static ClassType coerceToClassTypeOrNull(Object paramObject) {
    if (paramObject instanceof ClassType)
      return (ClassType)paramObject; 
    if (paramObject instanceof Class) {
      paramObject = Language.getDefaultLanguage().getTypeFor((Class)paramObject);
      if (paramObject instanceof ClassType)
        return (ClassType)paramObject; 
    } 
    return null;
  }
  
  public static Type coerceToType(Object paramObject) {
    Type type = coerceToTypeOrNull(paramObject);
    if (type == null && paramObject != null)
      throw new ClassCastException("cannot cast " + paramObject + " to type"); 
    return type;
  }
  
  public static Type coerceToTypeOrNull(Object paramObject) {
    return (paramObject instanceof Type) ? (Type)paramObject : ((paramObject instanceof Class) ? Language.getDefaultLanguage().getTypeFor((Class)paramObject) : null);
  }
  
  public Object coerceFromObject(Object paramObject) {
    switch (this.typeCode) {
      default:
        return super.coerceFromObject(paramObject);
      case 1:
        return Path.valueOf(paramObject);
      case 2:
        return FilePath.makeFilePath(paramObject);
      case 3:
        return URIPath.makeURI(paramObject);
      case 4:
        return coerceToClass(paramObject);
      case 6:
        return coerceToClassType(paramObject);
      case 5:
        return coerceToType(paramObject);
      case 10:
        return coerceNumeric(paramObject);
      case 9:
        return coerceRealNum(paramObject);
      case 8:
        return coerceRatNum(paramObject);
      case 7:
        return coerceIntNum(paramObject);
      case 15:
        break;
    } 
    return coerceDFloNum(paramObject);
  }
  
  Method coercionMethod() {
    switch (this.typeCode) {
      default:
        return ((PrimProcedure)getConstructor()).getMethod();
      case 4:
        return typeLangObjType.getDeclaredMethod("coerceToClass", 1);
      case 6:
        return typeLangObjType.getDeclaredMethod("coerceToClassType", 1);
      case 5:
        return typeLangObjType.getDeclaredMethod("coerceToType", 1);
      case 10:
        return typeLangObjType.getDeclaredMethod("coerceNumeric", 1);
      case 9:
        return typeLangObjType.getDeclaredMethod("coerceRealNum", 1);
      case 8:
        return typeLangObjType.getDeclaredMethod("coerceRatNum", 1);
      case 7:
        return typeLangObjType.getDeclaredMethod("coerceIntNum", 1);
      case 15:
        return typeLangObjType.getDeclaredMethod("coerceDFloNum", 1);
      case 11:
      case 12:
      case 13:
      case 14:
        break;
    } 
    return null;
  }
  
  Method coercionOrNullMethod() {
    ClassType classType = this.implementationType;
    switch (this.typeCode) {
      default:
        return null;
      case 1:
        str = "coerceToPathOrNull";
        return classType.getDeclaredMethod(str, 1);
      case 2:
        str = "coerceToFilePathOrNull";
        return classType.getDeclaredMethod(str, 1);
      case 3:
        str = "coerceToURIPathOrNull";
        return classType.getDeclaredMethod(str, 1);
      case 4:
        classType = typeLangObjType;
        str = "coerceToClassOrNull";
        return classType.getDeclaredMethod(str, 1);
      case 6:
        classType = typeLangObjType;
        str = "coerceToClassTypeOrNull";
        return classType.getDeclaredMethod(str, 1);
      case 5:
        classType = typeLangObjType;
        str = "coerceToTypeOrNull";
        return classType.getDeclaredMethod(str, 1);
      case 10:
        classType = this.implementationType;
        str = "asNumericOrNull";
        return classType.getDeclaredMethod(str, 1);
      case 15:
        classType = this.implementationType;
        str = "asDFloNumOrNull";
        return classType.getDeclaredMethod(str, 1);
      case 9:
        classType = this.implementationType;
        str = "asRealNumOrNull";
        return classType.getDeclaredMethod(str, 1);
      case 8:
        classType = this.implementationType;
        str = "asRatNumOrNull";
        return classType.getDeclaredMethod(str, 1);
      case 7:
        break;
    } 
    classType = this.implementationType;
    String str = "asIntNumOrNull";
    return classType.getDeclaredMethod(str, 1);
  }
  
  public int compare(Type paramType) {
    byte b1;
    byte b2 = -1;
    switch (this.typeCode) {
      default:
        return getImplementationType().compare(paramType.getImplementationType());
      case 4:
        b1 = b2;
        if (paramType != typeType) {
          b1 = b2;
          if (paramType != typeClassType) {
            b1 = b2;
            if (paramType != typeType.implementationType) {
              if (paramType == typeClassType.implementationType)
                return -1; 
            } else {
              return b1;
            } 
          } else {
            return b1;
          } 
        } else {
          return b1;
        } 
      case 5:
        if (paramType == typeClass || paramType == typeClassType || paramType == typeClass.implementationType || paramType == typeClassType.implementationType)
          return 1; 
      case 6:
        if (paramType == typeClass || paramType == typeClass.implementationType)
          return 1; 
        b1 = b2;
        if (paramType != typeType) {
          if (paramType == typeClass.implementationType)
            return -1; 
        } else {
          return b1;
        } 
      case 7:
        if (paramType instanceof gnu.bytecode.PrimType) {
          switch (paramType.getSignature().charAt(0)) {
            default:
              break;
            case 'B':
            case 'I':
            case 'J':
            case 'S':
              break;
          } 
          return 1;
        } 
        break;
      case 9:
      case 15:
        break;
    } 
    if (paramType instanceof gnu.bytecode.PrimType) {
      switch (paramType.getSignature().charAt(0)) {
        default:
        
        case 'D':
        case 'F':
          break;
      } 
      return 1;
    } 
  }
  
  public Expression convertValue(Expression paramExpression) {
    if (this.typeCode != 7 && this.typeCode != 10 && this.typeCode != 9 && this.typeCode != 8 && this.typeCode != 15) {
      Method method = coercionMethod();
      if (method != null) {
        ApplyExp applyExp = new ApplyExp(method, new Expression[] { paramExpression });
        applyExp.setType((Type)this);
        return (Expression)applyExp;
      } 
    } 
    return null;
  }
  
  public void emitCoerceFromObject(CodeAttr paramCodeAttr) {
    switch (this.typeCode) {
      default:
        paramCodeAttr.emitInvoke(coercionMethod());
        return;
      case 11:
      case 12:
      case 13:
      case 14:
        break;
    } 
    paramCodeAttr.emitCheckcast((Type)this.implementationType);
  }
  
  public void emitConvertFromPrimitive(Type paramType, CodeAttr paramCodeAttr) {
    // Byte code:
    //   0: aconst_null
    //   1: astore #7
    //   3: aconst_null
    //   4: astore #8
    //   6: aload #7
    //   8: astore_3
    //   9: aload #8
    //   11: astore #4
    //   13: aload_1
    //   14: astore #6
    //   16: aload_0
    //   17: getfield typeCode : I
    //   20: tableswitch default -> 72, 7 -> 212, 8 -> 212, 9 -> 212, 10 -> 212, 11 -> 82, 12 -> 82, 13 -> 82, 14 -> 82, 15 -> 111
    //   72: aload_1
    //   73: astore #6
    //   75: aload #8
    //   77: astore #4
    //   79: aload #7
    //   81: astore_3
    //   82: aload #4
    //   84: ifnull -> 372
    //   87: aload_2
    //   88: aload #4
    //   90: invokestatic make : (Ljava/lang/String;)Lgnu/bytecode/ClassType;
    //   93: ldc_w 'make'
    //   96: iconst_1
    //   97: anewarray gnu/bytecode/Type
    //   100: dup
    //   101: iconst_0
    //   102: aload_3
    //   103: aastore
    //   104: invokevirtual getDeclaredMethod : (Ljava/lang/String;[Lgnu/bytecode/Type;)Lgnu/bytecode/Method;
    //   107: invokevirtual emitInvokeStatic : (Lgnu/bytecode/Method;)V
    //   110: return
    //   111: aload #7
    //   113: astore_3
    //   114: aload #8
    //   116: astore #4
    //   118: aload_1
    //   119: astore #6
    //   121: aload_1
    //   122: instanceof gnu/bytecode/PrimType
    //   125: ifeq -> 82
    //   128: aload_1
    //   129: getstatic gnu/bytecode/Type.intType : Lgnu/bytecode/PrimType;
    //   132: if_acmpeq -> 166
    //   135: aload_1
    //   136: getstatic gnu/bytecode/Type.byteType : Lgnu/bytecode/PrimType;
    //   139: if_acmpeq -> 166
    //   142: aload_1
    //   143: getstatic gnu/bytecode/Type.shortType : Lgnu/bytecode/PrimType;
    //   146: if_acmpeq -> 166
    //   149: aload_1
    //   150: getstatic gnu/bytecode/Type.longType : Lgnu/bytecode/PrimType;
    //   153: if_acmpeq -> 166
    //   156: aload_1
    //   157: astore #5
    //   159: aload_1
    //   160: getstatic gnu/bytecode/Type.floatType : Lgnu/bytecode/PrimType;
    //   163: if_acmpne -> 179
    //   166: aload_2
    //   167: aload_1
    //   168: getstatic gnu/bytecode/Type.doubleType : Lgnu/bytecode/PrimType;
    //   171: invokevirtual emitConvert : (Lgnu/bytecode/Type;Lgnu/bytecode/Type;)V
    //   174: getstatic gnu/bytecode/Type.doubleType : Lgnu/bytecode/PrimType;
    //   177: astore #5
    //   179: aload #7
    //   181: astore_3
    //   182: aload #8
    //   184: astore #4
    //   186: aload #5
    //   188: astore #6
    //   190: aload #5
    //   192: getstatic gnu/bytecode/Type.doubleType : Lgnu/bytecode/PrimType;
    //   195: if_acmpne -> 82
    //   198: ldc 'gnu.math.DFloNum'
    //   200: astore #4
    //   202: aload #5
    //   204: astore_3
    //   205: aload #5
    //   207: astore #6
    //   209: goto -> 82
    //   212: aload #7
    //   214: astore_3
    //   215: aload #8
    //   217: astore #4
    //   219: aload_1
    //   220: astore #6
    //   222: aload_1
    //   223: instanceof gnu/bytecode/PrimType
    //   226: ifeq -> 82
    //   229: aload_1
    //   230: getstatic gnu/bytecode/Type.intType : Lgnu/bytecode/PrimType;
    //   233: if_acmpeq -> 250
    //   236: aload_1
    //   237: getstatic gnu/bytecode/Type.byteType : Lgnu/bytecode/PrimType;
    //   240: if_acmpeq -> 250
    //   243: aload_1
    //   244: getstatic gnu/bytecode/Type.shortType : Lgnu/bytecode/PrimType;
    //   247: if_acmpne -> 264
    //   250: ldc 'gnu.math.IntNum'
    //   252: astore #4
    //   254: getstatic gnu/bytecode/Type.int_type : Lgnu/bytecode/PrimType;
    //   257: astore_3
    //   258: aload_1
    //   259: astore #6
    //   261: goto -> 82
    //   264: aload_1
    //   265: getstatic gnu/bytecode/Type.longType : Lgnu/bytecode/PrimType;
    //   268: if_acmpne -> 285
    //   271: ldc 'gnu.math.IntNum'
    //   273: astore #4
    //   275: getstatic gnu/bytecode/Type.long_type : Lgnu/bytecode/PrimType;
    //   278: astore_3
    //   279: aload_1
    //   280: astore #6
    //   282: goto -> 82
    //   285: aload_0
    //   286: getfield typeCode : I
    //   289: bipush #9
    //   291: if_icmpeq -> 313
    //   294: aload #7
    //   296: astore_3
    //   297: aload #8
    //   299: astore #4
    //   301: aload_1
    //   302: astore #6
    //   304: aload_0
    //   305: getfield typeCode : I
    //   308: bipush #10
    //   310: if_icmpne -> 82
    //   313: aload_1
    //   314: astore #5
    //   316: aload_1
    //   317: getstatic gnu/bytecode/Type.floatType : Lgnu/bytecode/PrimType;
    //   320: if_acmpne -> 338
    //   323: aload_2
    //   324: getstatic gnu/bytecode/Type.float_type : Lgnu/bytecode/PrimType;
    //   327: getstatic gnu/bytecode/Type.double_type : Lgnu/bytecode/PrimType;
    //   330: invokevirtual emitConvert : (Lgnu/bytecode/Type;Lgnu/bytecode/Type;)V
    //   333: getstatic gnu/bytecode/Type.doubleType : Lgnu/bytecode/PrimType;
    //   336: astore #5
    //   338: aload #7
    //   340: astore_3
    //   341: aload #8
    //   343: astore #4
    //   345: aload #5
    //   347: astore #6
    //   349: aload #5
    //   351: getstatic gnu/bytecode/Type.doubleType : Lgnu/bytecode/PrimType;
    //   354: if_acmpne -> 82
    //   357: ldc 'gnu.math.DFloNum'
    //   359: astore #4
    //   361: getstatic gnu/bytecode/Type.doubleType : Lgnu/bytecode/PrimType;
    //   364: astore_3
    //   365: aload #5
    //   367: astore #6
    //   369: goto -> 82
    //   372: aload_0
    //   373: aload #6
    //   375: aload_2
    //   376: invokespecial emitConvertFromPrimitive : (Lgnu/bytecode/Type;Lgnu/bytecode/CodeAttr;)V
    //   379: return
  }
  
  public void emitIsInstance(Variable paramVariable, Compilation paramCompilation, Target paramTarget) {
    switch (this.typeCode) {
      default:
        InstanceOf.emitIsInstance(this, paramVariable, paramCompilation, paramTarget);
        return;
      case 11:
      case 12:
      case 13:
      case 14:
        break;
    } 
    this.implementationType.emitIsInstance(paramCompilation.getCode());
    paramTarget.compileFromStack(paramCompilation, paramCompilation.getLanguage().getTypeFor(boolean.class));
  }
  
  public void emitTestIf(Variable paramVariable, Declaration paramDeclaration, Compilation paramCompilation) {
    CodeAttr codeAttr = paramCompilation.getCode();
    if (paramVariable != null)
      codeAttr.emitLoad(paramVariable); 
    Method method = coercionOrNullMethod();
    if (method != null)
      codeAttr.emitInvokeStatic(method); 
    if (paramDeclaration != null) {
      codeAttr.emitDup();
      paramDeclaration.compileStore(paramCompilation);
    } 
    if (method != null) {
      codeAttr.emitIfNotNull();
      return;
    } 
    this.implementationType.emitIsInstance(codeAttr);
    codeAttr.emitIfIntNotZero();
  }
  
  public Procedure getConstructor() {
    switch (this.typeCode) {
      default:
        return null;
      case 1:
        return (Procedure)makePathProc;
      case 2:
        return (Procedure)makeFilepathProc;
      case 3:
        return (Procedure)makeURIProc;
      case 12:
        return (Procedure)new PrimProcedure("gnu.lists.FVector", "make", 1);
      case 11:
        return (Procedure)MakeList.list;
      case 13:
        return (Procedure)new PrimProcedure("kawa.lib.strings", "$make$string$", 1);
      case 14:
        break;
    } 
    return (Procedure)new PrimProcedure("java.util.regex.Pattern", "compile", 1);
  }
  
  public Method getDeclaredMethod(String paramString, int paramInt) {
    return this.implementationType.getDeclaredMethod(paramString, paramInt);
  }
  
  public Field getField(String paramString, int paramInt) {
    return this.implementationType.getField(paramString, paramInt);
  }
  
  public Type getImplementationType() {
    return (Type)this.implementationType;
  }
  
  public Method getMethod(String paramString, Type[] paramArrayOfType) {
    return this.implementationType.getMethod(paramString, paramArrayOfType);
  }
  
  public int getMethods(Filter paramFilter, int paramInt, List<Method> paramList) {
    return this.implementationType.getMethods(paramFilter, paramInt, paramList);
  }
  
  public Type getRealType() {
    return (Type)this.implementationType;
  }
  
  public Class getReflectClass() {
    return this.implementationType.getReflectClass();
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/gnu/kawa/lispexpr/LangObjType.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */