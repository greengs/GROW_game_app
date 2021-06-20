package gnu.expr;

import gnu.bytecode.ArrayClassLoader;
import gnu.bytecode.ArrayType;
import gnu.bytecode.ClassType;
import gnu.bytecode.CodeAttr;
import gnu.bytecode.Field;
import gnu.bytecode.Label;
import gnu.bytecode.Method;
import gnu.bytecode.ObjectType;
import gnu.bytecode.PrimType;
import gnu.bytecode.SwitchState;
import gnu.bytecode.Type;
import gnu.bytecode.Variable;
import gnu.kawa.functions.Convert;
import gnu.mapping.Environment;
import gnu.mapping.EnvironmentKey;
import gnu.mapping.OutPort;
import gnu.mapping.Procedure;
import gnu.mapping.Symbol;
import gnu.mapping.WrappedException;
import gnu.text.Lexer;
import gnu.text.Options;
import gnu.text.SourceLocator;
import gnu.text.SourceMessages;
import gnu.text.SyntaxException;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Stack;
import java.util.Vector;
import java.util.jar.JarOutputStream;
import java.util.zip.CRC32;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Compilation implements SourceLocator {
  public static final int BODY_PARSED = 4;
  
  public static final int CALL_WITH_CONSUMER = 2;
  
  public static final int CALL_WITH_CONTINUATIONS = 4;
  
  public static final int CALL_WITH_RETURN = 1;
  
  public static final int CALL_WITH_TAILCALLS = 3;
  
  public static final int CALL_WITH_UNSPECIFIED = 0;
  
  public static final int CLASS_WRITTEN = 14;
  
  public static final int COMPILED = 12;
  
  public static final int COMPILE_SETUP = 10;
  
  public static final int ERROR_SEEN = 100;
  
  public static final int MODULE_NONSTATIC = -1;
  
  public static final int MODULE_STATIC = 1;
  
  public static final int MODULE_STATIC_DEFAULT = 0;
  
  public static final int MODULE_STATIC_RUN = 2;
  
  public static final int PROLOG_PARSED = 2;
  
  public static final int PROLOG_PARSING = 1;
  
  public static final int RESOLVED = 6;
  
  public static final int WALKED = 8;
  
  public static Type[] apply0args;
  
  public static Method apply0method;
  
  public static Type[] apply1args;
  
  public static Method apply1method;
  
  public static Type[] apply2args;
  
  public static Method apply2method;
  
  public static Method apply3method;
  
  public static Method apply4method;
  
  private static Type[] applyCpsArgs;
  
  public static Method applyCpsMethod;
  
  public static Type[] applyNargs;
  
  public static Method applyNmethod;
  
  public static Method[] applymethods;
  
  public static Field argsCallContextField;
  
  private static Compilation chainUninitialized;
  
  static Method checkArgCountMethod;
  
  public static String classPrefixDefault;
  
  private static final ThreadLocal<Compilation> current;
  
  public static boolean debugPrintExpr = false;
  
  public static boolean debugPrintFinalExpr;
  
  public static int defaultCallConvention;
  
  public static int defaultClassFileVersion;
  
  public static boolean emitSourceDebugExtAttr;
  
  public static final Field falseConstant;
  
  public static boolean generateMainDefault;
  
  public static Method getCallContextInstanceMethod;
  
  public static Method getCurrentEnvironmentMethod;
  
  public static final Method getLocation1EnvironmentMethod;
  
  public static final Method getLocation2EnvironmentMethod;
  
  public static final Method getLocationMethod;
  
  public static final Method getProcedureBindingMethod;
  
  public static final Method getSymbolProcedureMethod;
  
  public static final Method getSymbolValueMethod;
  
  public static boolean inlineOk;
  
  public static final Type[] int1Args;
  
  public static ClassType javaStringType;
  
  static Method makeListMethod;
  
  public static int moduleStatic;
  
  public static Field noArgsField;
  
  public static final ArrayType objArrayType;
  
  public static Options options = new Options();
  
  public static Field pcCallContextField;
  
  public static Field procCallContextField;
  
  public static ClassType scmBooleanType;
  
  public static ClassType scmKeywordType;
  
  public static ClassType scmListType;
  
  public static ClassType scmSequenceType;
  
  static final Method setNameMethod;
  
  public static final Type[] string1Arg;
  
  public static final Type[] sym1Arg;
  
  public static final Field trueConstant;
  
  public static ClassType typeApplet;
  
  public static ClassType typeCallContext;
  
  public static ClassType typeClass;
  
  public static ClassType typeClassType;
  
  public static final ClassType typeConsumer;
  
  public static ClassType typeEnvironment;
  
  public static ClassType typeLanguage;
  
  public static ClassType typeLocation;
  
  public static ClassType typeMethodProc;
  
  public static ClassType typeModuleBody;
  
  public static ClassType typeModuleMethod;
  
  public static ClassType typeModuleWithContext;
  
  public static ClassType typeObject;
  
  public static ClassType typeObjectType;
  
  public static ClassType typePair;
  
  public static ClassType typeProcedure;
  
  public static ClassType typeProcedure0;
  
  public static ClassType typeProcedure1;
  
  public static ClassType typeProcedure2;
  
  public static ClassType typeProcedure3;
  
  public static ClassType typeProcedure4;
  
  public static ClassType[] typeProcedureArray;
  
  public static ClassType typeProcedureN;
  
  public static ClassType typeRunnable;
  
  public static ClassType typeServlet;
  
  public static ClassType typeString;
  
  public static ClassType typeSymbol;
  
  public static ClassType typeType;
  
  public static ClassType typeValues;
  
  public static Options.OptionInfo warnAsError;
  
  public static Options.OptionInfo warnInvokeUnknownMethod;
  
  public static Options.OptionInfo warnUndefinedVariable = options.add("warn-undefined-variable", 1, Boolean.TRUE, "warn if no compiler-visible binding for a variable");
  
  public static Options.OptionInfo warnUnknownMember = options.add("warn-unknown-member", 1, Boolean.TRUE, "warn if referencing an unknown method or field");
  
  Variable callContextVar;
  
  Variable callContextVarForInit;
  
  public String classPrefix = classPrefixDefault;
  
  ClassType[] classes;
  
  Initializer clinitChain;
  
  Method clinitMethod;
  
  public ClassType curClass;
  
  public LambdaExp curLambda;
  
  public Options currentOptions = new Options(options);
  
  protected ScopeExp current_scope;
  
  public boolean explicit;
  
  public Stack<Expression> exprStack;
  
  Method forNameHelper;
  
  SwitchState fswitch;
  
  Field fswitchIndex;
  
  public boolean generateMain = generateMainDefault;
  
  public boolean immediate;
  
  private int keyUninitialized;
  
  int langOptions;
  
  protected Language language;
  
  public Lexer lexer;
  
  public NameLookup lexical;
  
  LitTable litTable;
  
  ArrayClassLoader loader;
  
  int localFieldIndex;
  
  public ClassType mainClass;
  
  public ModuleExp mainLambda;
  
  int maxSelectorValue;
  
  protected SourceMessages messages;
  
  public Method method;
  
  int method_counter;
  
  public ModuleInfo minfo;
  
  public ClassType moduleClass;
  
  Field moduleInstanceMainField;
  
  Variable moduleInstanceVar;
  
  public boolean mustCompile = ModuleExp.alwaysCompile;
  
  private Compilation nextUninitialized;
  
  int numClasses;
  
  boolean pedantic;
  
  public Stack<Object> pendingImports;
  
  private int state;
  
  public Variable thisDecl;
  
  static {
    warnInvokeUnknownMethod = options.add("warn-invoke-unknown-method", 1, warnUnknownMember, "warn if invoke calls an unknown method (subsumed by warn-unknown-member)");
    warnAsError = options.add("warn-as-error", 1, Boolean.FALSE, "Make all warnings into errors");
    defaultClassFileVersion = 3211264;
    moduleStatic = 0;
    typeObject = Type.objectType;
    scmBooleanType = ClassType.make("java.lang.Boolean");
    typeString = ClassType.make("java.lang.String");
    javaStringType = typeString;
    scmKeywordType = ClassType.make("gnu.expr.Keyword");
    scmSequenceType = ClassType.make("gnu.lists.Sequence");
    scmListType = ClassType.make("gnu.lists.LList");
    typePair = ClassType.make("gnu.lists.Pair");
    objArrayType = ArrayType.make((Type)typeObject);
    typeRunnable = ClassType.make("java.lang.Runnable");
    typeType = ClassType.make("gnu.bytecode.Type");
    typeObjectType = ClassType.make("gnu.bytecode.ObjectType");
    typeClass = Type.javalangClassType;
    typeClassType = ClassType.make("gnu.bytecode.ClassType");
    typeProcedure = ClassType.make("gnu.mapping.Procedure");
    typeLanguage = ClassType.make("gnu.expr.Language");
    typeEnvironment = ClassType.make("gnu.mapping.Environment");
    typeLocation = ClassType.make("gnu.mapping.Location");
    typeSymbol = ClassType.make("gnu.mapping.Symbol");
    getSymbolValueMethod = typeLanguage.getDeclaredMethod("getSymbolValue", 1);
    getSymbolProcedureMethod = typeLanguage.getDeclaredMethod("getSymbolProcedure", 1);
    getLocationMethod = typeLocation.addMethod("get", Type.typeArray0, (Type)Type.objectType, 1);
    getProcedureBindingMethod = typeSymbol.addMethod("getProcedure", Type.typeArray0, (Type)typeProcedure, 1);
    trueConstant = scmBooleanType.getDeclaredField("TRUE");
    falseConstant = scmBooleanType.getDeclaredField("FALSE");
    setNameMethod = typeProcedure.getDeclaredMethod("setName", 1);
    int1Args = new Type[] { (Type)Type.intType };
    string1Arg = new Type[] { (Type)javaStringType };
    sym1Arg = string1Arg;
    getLocation1EnvironmentMethod = typeEnvironment.getDeclaredMethod("getLocation", 1);
    ClassType classType2 = typeSymbol;
    ClassType classType4 = Type.objectType;
    ClassType classType5 = typeEnvironment;
    ClassType classType6 = typeLocation;
    getLocation2EnvironmentMethod = classType5.addMethod("getLocation", new Type[] { (Type)classType2, (Type)classType4 }, (Type)classType6, 17);
    ArrayType arrayType = objArrayType;
    PrimType primType2 = Type.intType;
    classType5 = scmListType;
    classType6 = scmListType;
    makeListMethod = classType5.addMethod("makeList", new Type[] { (Type)arrayType, (Type)primType2 }, (Type)classType6, 9);
    getCurrentEnvironmentMethod = typeEnvironment.addMethod("getCurrent", Type.typeArray0, (Type)typeEnvironment, 9);
    apply0args = Type.typeArray0;
    apply1args = new Type[] { (Type)typeObject };
    apply2args = new Type[] { (Type)typeObject, (Type)typeObject };
    applyNargs = new Type[] { (Type)objArrayType };
    apply0method = typeProcedure.addMethod("apply0", apply0args, (Type)typeObject, 17);
    apply1method = typeProcedure.addMethod("apply1", apply1args, (Type)typeObject, 1);
    apply2method = typeProcedure.addMethod("apply2", apply2args, (Type)typeObject, 1);
    ClassType classType1 = typeObject;
    ClassType classType3 = typeObject;
    classType5 = typeObject;
    classType6 = typeProcedure;
    ClassType classType7 = typeObject;
    apply3method = classType6.addMethod("apply3", new Type[] { (Type)classType1, (Type)classType3, (Type)classType5 }, (Type)classType7, 1);
    classType1 = typeObject;
    classType3 = typeObject;
    classType5 = typeObject;
    classType6 = typeObject;
    classType7 = typeProcedure;
    ClassType classType8 = typeObject;
    apply4method = classType7.addMethod("apply4", new Type[] { (Type)classType1, (Type)classType3, (Type)classType5, (Type)classType6 }, (Type)classType8, 1);
    applyNmethod = typeProcedure.addMethod("applyN", applyNargs, (Type)typeObject, 1);
    classType1 = typeProcedure;
    PrimType primType1 = Type.intType;
    classType5 = typeProcedure;
    PrimType primType3 = Type.voidType;
    checkArgCountMethod = classType5.addMethod("checkArgCount", new Type[] { (Type)classType1, (Type)primType1 }, (Type)primType3, 9);
    applymethods = new Method[] { apply0method, apply1method, apply2method, apply3method, apply4method, applyNmethod };
    typeProcedure0 = ClassType.make("gnu.mapping.Procedure0");
    typeProcedure1 = ClassType.make("gnu.mapping.Procedure1");
    typeProcedure2 = ClassType.make("gnu.mapping.Procedure2");
    typeProcedure3 = ClassType.make("gnu.mapping.Procedure3");
    typeProcedure4 = ClassType.make("gnu.mapping.Procedure4");
    typeProcedureN = ClassType.make("gnu.mapping.ProcedureN");
    typeModuleBody = ClassType.make("gnu.expr.ModuleBody");
    typeModuleWithContext = ClassType.make("gnu.expr.ModuleWithContext");
    typeApplet = ClassType.make("java.applet.Applet");
    typeServlet = ClassType.make("gnu.kawa.servlet.KawaServlet");
    typeCallContext = ClassType.make("gnu.mapping.CallContext");
    typeConsumer = ClassType.make("gnu.lists.Consumer");
    getCallContextInstanceMethod = typeCallContext.getDeclaredMethod("getInstance", 0);
    typeValues = ClassType.make("gnu.mapping.Values");
    noArgsField = typeValues.getDeclaredField("noArgs");
    pcCallContextField = typeCallContext.getDeclaredField("pc");
    typeMethodProc = ClassType.make("gnu.mapping.MethodProc");
    typeModuleMethod = ClassType.make("gnu.expr.ModuleMethod");
    argsCallContextField = typeCallContext.getDeclaredField("values");
    procCallContextField = typeCallContext.getDeclaredField("proc");
    applyCpsArgs = new Type[] { (Type)typeCallContext };
    applyCpsMethod = typeProcedure.addMethod("apply", applyCpsArgs, (Type)Type.voidType, 1);
    typeProcedureArray = new ClassType[] { typeProcedure0, typeProcedure1, typeProcedure2, typeProcedure3, typeProcedure4 };
    generateMainDefault = false;
    inlineOk = true;
    classPrefixDefault = "";
    emitSourceDebugExtAttr = true;
    current = new InheritableThreadLocal<Compilation>();
  }
  
  public Compilation(Language paramLanguage, SourceMessages paramSourceMessages, NameLookup paramNameLookup) {
    this.language = paramLanguage;
    this.messages = paramSourceMessages;
    this.lexical = paramNameLookup;
  }
  
  private void checkLoop() {
    if (((LambdaExp)this.current_scope).getName() != "%do%loop")
      throw new Error("internal error - bad loop state"); 
  }
  
  public static char demangle2(char paramChar1, char paramChar2) {
    char c = '%';
    switch (paramChar1 << 16 | paramChar2) {
      default:
        c = '￿';
      case 5046371:
      case 5242979:
        return c;
      case 4259949:
        return '&';
      case 4259956:
        return '@';
      case 4391020:
        return ':';
      case 4391021:
        return ',';
      case 4456561:
        return '"';
      case 4456564:
        return '.';
      case 4522097:
        return '=';
      case 4522104:
        return '!';
      case 4653170:
        return '>';
      case 4980802:
        return '[';
      case 4980803:
        return '{';
      case 4980816:
        return '(';
      case 4980851:
        return '<';
      case 5046382:
        return '-';
      case 5111917:
        return '#';
      case 5242988:
        return '+';
      case 5308533:
        return '?';
      case 5374018:
        return ']';
      case 5374019:
        return '}';
      case 5374032:
        return ')';
      case 5439555:
        return ';';
      case 5439596:
        return '/';
      case 5439601:
        return '\\';
      case 5439604:
        return '*';
      case 5505132:
        return '~';
      case 5570672:
        return '^';
      case 5636162:
        break;
    } 
    return '|';
  }
  
  public static String demangleName(String paramString) {
    return demangleName(paramString, false);
  }
  
  public static String demangleName(String paramString, boolean paramBoolean) {
    // Byte code:
    //   0: new java/lang/StringBuffer
    //   3: dup
    //   4: invokespecial <init> : ()V
    //   7: astore #15
    //   9: aload_0
    //   10: invokevirtual length : ()I
    //   13: istore #14
    //   15: iconst_0
    //   16: istore #6
    //   18: iconst_0
    //   19: istore #11
    //   21: iconst_0
    //   22: istore #8
    //   24: iconst_0
    //   25: istore #7
    //   27: iload #7
    //   29: iload #14
    //   31: if_icmpge -> 451
    //   34: aload_0
    //   35: iload #7
    //   37: invokevirtual charAt : (I)C
    //   40: istore_3
    //   41: iload_3
    //   42: istore_2
    //   43: iload #8
    //   45: istore #9
    //   47: iload #8
    //   49: ifeq -> 70
    //   52: iload_3
    //   53: istore_2
    //   54: iload #8
    //   56: istore #9
    //   58: iload_1
    //   59: ifne -> 70
    //   62: iload_3
    //   63: invokestatic toLowerCase : (C)C
    //   66: istore_2
    //   67: iconst_0
    //   68: istore #9
    //   70: iload_1
    //   71: ifne -> 199
    //   74: iload_2
    //   75: bipush #105
    //   77: if_icmpne -> 199
    //   80: iload #7
    //   82: ifne -> 199
    //   85: iload #14
    //   87: iconst_2
    //   88: if_icmple -> 199
    //   91: aload_0
    //   92: iload #7
    //   94: iconst_1
    //   95: iadd
    //   96: invokevirtual charAt : (I)C
    //   99: bipush #115
    //   101: if_icmpne -> 199
    //   104: aload_0
    //   105: iload #7
    //   107: iconst_2
    //   108: iadd
    //   109: invokevirtual charAt : (I)C
    //   112: istore_3
    //   113: iload_3
    //   114: invokestatic isLowerCase : (C)Z
    //   117: ifne -> 199
    //   120: iconst_1
    //   121: istore #10
    //   123: iconst_1
    //   124: istore #12
    //   126: iload #7
    //   128: iconst_1
    //   129: iadd
    //   130: istore #13
    //   132: iload_3
    //   133: invokestatic isUpperCase : (C)Z
    //   136: ifne -> 162
    //   139: iload #9
    //   141: istore #8
    //   143: iload #13
    //   145: istore #7
    //   147: iload #10
    //   149: istore #6
    //   151: iload #12
    //   153: istore #11
    //   155: iload_3
    //   156: invokestatic isTitleCase : (C)Z
    //   159: ifeq -> 190
    //   162: aload #15
    //   164: iload_3
    //   165: invokestatic toLowerCase : (C)C
    //   168: invokevirtual append : (C)Ljava/lang/StringBuffer;
    //   171: pop
    //   172: iload #13
    //   174: iconst_1
    //   175: iadd
    //   176: istore #7
    //   178: iload #12
    //   180: istore #11
    //   182: iload #10
    //   184: istore #6
    //   186: iload #9
    //   188: istore #8
    //   190: iload #7
    //   192: iconst_1
    //   193: iadd
    //   194: istore #7
    //   196: goto -> 27
    //   199: iload_2
    //   200: bipush #36
    //   202: if_icmpne -> 355
    //   205: iload #7
    //   207: iconst_2
    //   208: iadd
    //   209: iload #14
    //   211: if_icmpge -> 355
    //   214: aload_0
    //   215: iload #7
    //   217: iconst_1
    //   218: iadd
    //   219: invokevirtual charAt : (I)C
    //   222: istore #4
    //   224: aload_0
    //   225: iload #7
    //   227: iconst_2
    //   228: iadd
    //   229: invokevirtual charAt : (I)C
    //   232: istore #5
    //   234: iload #4
    //   236: iload #5
    //   238: invokestatic demangle2 : (CC)C
    //   241: istore_3
    //   242: iload_3
    //   243: ldc_w 65535
    //   246: if_icmpeq -> 271
    //   249: aload #15
    //   251: iload_3
    //   252: invokevirtual append : (C)Ljava/lang/StringBuffer;
    //   255: pop
    //   256: iload #7
    //   258: iconst_2
    //   259: iadd
    //   260: istore #7
    //   262: iconst_1
    //   263: istore #6
    //   265: iconst_1
    //   266: istore #8
    //   268: goto -> 190
    //   271: iload_2
    //   272: istore_3
    //   273: iload #6
    //   275: istore #10
    //   277: iload #4
    //   279: bipush #84
    //   281: if_icmpne -> 433
    //   284: iload_2
    //   285: istore_3
    //   286: iload #6
    //   288: istore #10
    //   290: iload #5
    //   292: bipush #111
    //   294: if_icmpne -> 433
    //   297: iload_2
    //   298: istore_3
    //   299: iload #6
    //   301: istore #10
    //   303: iload #7
    //   305: iconst_3
    //   306: iadd
    //   307: iload #14
    //   309: if_icmpge -> 433
    //   312: iload_2
    //   313: istore_3
    //   314: iload #6
    //   316: istore #10
    //   318: aload_0
    //   319: iload #7
    //   321: iconst_3
    //   322: iadd
    //   323: invokevirtual charAt : (I)C
    //   326: bipush #36
    //   328: if_icmpne -> 433
    //   331: aload #15
    //   333: ldc_w '->'
    //   336: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   339: pop
    //   340: iload #7
    //   342: iconst_3
    //   343: iadd
    //   344: istore #7
    //   346: iconst_1
    //   347: istore #6
    //   349: iconst_1
    //   350: istore #8
    //   352: goto -> 190
    //   355: iload_2
    //   356: istore_3
    //   357: iload #6
    //   359: istore #10
    //   361: iload_1
    //   362: ifne -> 433
    //   365: iload_2
    //   366: istore_3
    //   367: iload #6
    //   369: istore #10
    //   371: iload #7
    //   373: iconst_1
    //   374: if_icmple -> 433
    //   377: iload_2
    //   378: invokestatic isUpperCase : (C)Z
    //   381: ifne -> 397
    //   384: iload_2
    //   385: istore_3
    //   386: iload #6
    //   388: istore #10
    //   390: iload_2
    //   391: invokestatic isTitleCase : (C)Z
    //   394: ifeq -> 433
    //   397: iload_2
    //   398: istore_3
    //   399: iload #6
    //   401: istore #10
    //   403: aload_0
    //   404: iload #7
    //   406: iconst_1
    //   407: isub
    //   408: invokevirtual charAt : (I)C
    //   411: invokestatic isLowerCase : (C)Z
    //   414: ifeq -> 433
    //   417: aload #15
    //   419: bipush #45
    //   421: invokevirtual append : (C)Ljava/lang/StringBuffer;
    //   424: pop
    //   425: iconst_1
    //   426: istore #10
    //   428: iload_2
    //   429: invokestatic toLowerCase : (C)C
    //   432: istore_3
    //   433: aload #15
    //   435: iload_3
    //   436: invokevirtual append : (C)Ljava/lang/StringBuffer;
    //   439: pop
    //   440: iload #9
    //   442: istore #8
    //   444: iload #10
    //   446: istore #6
    //   448: goto -> 190
    //   451: iload #11
    //   453: ifeq -> 464
    //   456: aload #15
    //   458: bipush #63
    //   460: invokevirtual append : (C)Ljava/lang/StringBuffer;
    //   463: pop
    //   464: iload #6
    //   466: ifeq -> 475
    //   469: aload #15
    //   471: invokevirtual toString : ()Ljava/lang/String;
    //   474: astore_0
    //   475: aload_0
    //   476: areturn
  }
  
  private void dumpInitializers(Initializer paramInitializer) {
    for (paramInitializer = Initializer.reverse(paramInitializer); paramInitializer != null; paramInitializer = paramInitializer.next)
      paramInitializer.emit(this); 
  }
  
  public static Compilation findForImmediateLiterals(int paramInt) {
    // Byte code:
    //   0: ldc gnu/expr/Compilation
    //   2: monitorenter
    //   3: aconst_null
    //   4: astore_2
    //   5: getstatic gnu/expr/Compilation.chainUninitialized : Lgnu/expr/Compilation;
    //   8: astore_1
    //   9: aload_1
    //   10: getfield nextUninitialized : Lgnu/expr/Compilation;
    //   13: astore_3
    //   14: aload_1
    //   15: getfield keyUninitialized : I
    //   18: iload_0
    //   19: if_icmpne -> 54
    //   22: aload_2
    //   23: ifnonnull -> 40
    //   26: aload_3
    //   27: putstatic gnu/expr/Compilation.chainUninitialized : Lgnu/expr/Compilation;
    //   30: aload_1
    //   31: aconst_null
    //   32: putfield nextUninitialized : Lgnu/expr/Compilation;
    //   35: ldc gnu/expr/Compilation
    //   37: monitorexit
    //   38: aload_1
    //   39: areturn
    //   40: aload_2
    //   41: aload_3
    //   42: putfield nextUninitialized : Lgnu/expr/Compilation;
    //   45: goto -> 30
    //   48: astore_1
    //   49: ldc gnu/expr/Compilation
    //   51: monitorexit
    //   52: aload_1
    //   53: athrow
    //   54: aload_1
    //   55: astore_2
    //   56: aload_3
    //   57: astore_1
    //   58: goto -> 9
    // Exception table:
    //   from	to	target	type
    //   5	9	48	finally
    //   9	22	48	finally
    //   26	30	48	finally
    //   30	35	48	finally
    //   40	45	48	finally
  }
  
  public static final Method getConstructor(ClassType paramClassType, LambdaExp paramLambdaExp) {
    Method method = paramClassType.getDeclaredMethod("<init>", 0);
    if (method != null)
      return method; 
    if (paramLambdaExp instanceof ClassExp && paramLambdaExp.staticLinkField != null) {
      Type[] arrayOfType2 = new Type[1];
      arrayOfType2[0] = paramLambdaExp.staticLinkField.getType();
      Type[] arrayOfType1 = arrayOfType2;
      return paramClassType.addMethod("<init>", 1, arrayOfType1, (Type)Type.voidType);
    } 
    Type[] arrayOfType = apply0args;
    return paramClassType.addMethod("<init>", 1, arrayOfType, (Type)Type.voidType);
  }
  
  public static Compilation getCurrent() {
    return current.get();
  }
  
  public static boolean isValidJavaName(String paramString) {
    int i = paramString.length();
    if (i == 0 || !Character.isJavaIdentifierStart(paramString.charAt(0)))
      return false; 
    while (true) {
      int j = i - 1;
      if (j > 0) {
        i = j;
        if (!Character.isJavaIdentifierPart(paramString.charAt(j)))
          return false; 
        continue;
      } 
      return true;
    } 
  }
  
  public static ApplyExp makeCoercion(Expression paramExpression1, Expression paramExpression2) {
    return new ApplyExp(new QuoteExp(Convert.getInstance()), new Expression[] { paramExpression2, paramExpression1 });
  }
  
  public static Expression makeCoercion(Expression paramExpression, Type paramType) {
    return makeCoercion(paramExpression, new QuoteExp(paramType));
  }
  
  public static String mangleName(String paramString) {
    return mangleName(paramString, -1);
  }
  
  public static String mangleName(String paramString, int paramInt) {
    boolean bool;
    if (paramInt >= 0) {
      bool = true;
    } else {
      bool = false;
    } 
    int k = paramString.length();
    if (k == 6 && paramString.equals("*init*"))
      return "<init>"; 
    StringBuffer stringBuffer = new StringBuffer(k);
    int j = 0;
    int i = 0;
    while (true) {
      int m;
      if (i < k) {
        char c1 = paramString.charAt(i);
        char c = c1;
        m = j;
        if (j) {
          c = Character.toTitleCase(c1);
          m = 0;
        } 
        if (Character.isDigit(c)) {
          if (i == 0)
            stringBuffer.append("$N"); 
          stringBuffer.append(c);
        } else if (Character.isLetter(c) || c == '_') {
          stringBuffer.append(c);
        } else if (c == '$') {
          String str;
          if (paramInt > 1) {
            str = "$$";
          } else {
            str = "$";
          } 
          stringBuffer.append(str);
        } else {
          switch (c) {
            default:
              stringBuffer.append('$');
              stringBuffer.append(Character.forDigit(c >> 12 & 0xF, 16));
              stringBuffer.append(Character.forDigit(c >> 8 & 0xF, 16));
              stringBuffer.append(Character.forDigit(c >> 4 & 0xF, 16));
              stringBuffer.append(Character.forDigit(c & 0xF, 16));
              j = i;
              i = j;
            case '+':
              stringBuffer.append("$Pl");
              j = i;
              i = j;
            case '-':
              if (bool) {
                stringBuffer.append("$Mn");
                j = i;
              } else {
                if (i + 1 < k) {
                  c = paramString.charAt(i + 1);
                } else {
                  c = Character.MIN_VALUE;
                } 
                if (c == '>') {
                  stringBuffer.append("$To$");
                  j = i + 1;
                } else {
                  j = i;
                  if (!Character.isLowerCase(c)) {
                    stringBuffer.append("$Mn");
                    j = i;
                  } 
                } 
              } 
              i = j;
            case '*':
              stringBuffer.append("$St");
              j = i;
              i = j;
            case '/':
              stringBuffer.append("$Sl");
              j = i;
              i = j;
            case '=':
              stringBuffer.append("$Eq");
              j = i;
              i = j;
            case '<':
              stringBuffer.append("$Ls");
              j = i;
              i = j;
            case '>':
              stringBuffer.append("$Gr");
              j = i;
              i = j;
            case '@':
              stringBuffer.append("$At");
              j = i;
              i = j;
            case '~':
              stringBuffer.append("$Tl");
              j = i;
              i = j;
            case '%':
              stringBuffer.append("$Pc");
              j = i;
              i = j;
            case '.':
              stringBuffer.append("$Dt");
              j = i;
              i = j;
            case ',':
              stringBuffer.append("$Cm");
              j = i;
              i = j;
            case '(':
              stringBuffer.append("$LP");
              j = i;
              i = j;
            case ')':
              stringBuffer.append("$RP");
              j = i;
              i = j;
            case '[':
              stringBuffer.append("$LB");
              j = i;
              i = j;
            case ']':
              stringBuffer.append("$RB");
              j = i;
              i = j;
            case '{':
              stringBuffer.append("$LC");
              j = i;
              i = j;
            case '}':
              stringBuffer.append("$RC");
              j = i;
              i = j;
            case '\'':
              stringBuffer.append("$Sq");
              j = i;
              i = j;
            case '"':
              stringBuffer.append("$Dq");
              j = i;
              i = j;
            case '&':
              stringBuffer.append("$Am");
              j = i;
              i = j;
            case '#':
              stringBuffer.append("$Nm");
              j = i;
              i = j;
            case '?':
              if (stringBuffer.length() > 0) {
                c = stringBuffer.charAt(0);
              } else {
                c = Character.MIN_VALUE;
              } 
              if (!bool && i + 1 == k && Character.isLowerCase(c)) {
                stringBuffer.setCharAt(0, Character.toTitleCase(c));
                stringBuffer.insert(0, "is");
                j = i;
              } else {
                stringBuffer.append("$Qu");
                j = i;
              } 
              i = j;
            case '!':
              stringBuffer.append("$Ex");
              j = i;
              i = j;
            case ':':
              stringBuffer.append("$Cl");
              j = i;
              i = j;
            case ';':
              stringBuffer.append("$SC");
              j = i;
              i = j;
            case '^':
              stringBuffer.append("$Up");
              j = i;
              i = j;
            case '|':
              break;
          } 
          stringBuffer.append("$VB");
          j = i;
          i = j;
        } 
      } else {
        break;
      } 
      i++;
      j = m;
    } 
    String str2 = stringBuffer.toString();
    String str1 = paramString;
    return !str2.equals(paramString) ? str2 : str1;
  }
  
  public static String mangleName(String paramString, boolean paramBoolean) {
    if (paramBoolean) {
      boolean bool = true;
      return mangleName(paramString, bool);
    } 
    byte b = -1;
    return mangleName(paramString, b);
  }
  
  public static String mangleNameIfNeeded(String paramString) {
    return (paramString == null || isValidJavaName(paramString)) ? paramString : mangleName(paramString, 0);
  }
  
  public static String mangleURI(String paramString) {
    // Byte code:
    //   0: aload_0
    //   1: bipush #47
    //   3: invokevirtual indexOf : (I)I
    //   6: iflt -> 39
    //   9: iconst_1
    //   10: istore_2
    //   11: aload_0
    //   12: invokevirtual length : ()I
    //   15: istore_3
    //   16: iload_3
    //   17: bipush #6
    //   19: if_icmple -> 44
    //   22: aload_0
    //   23: ldc_w 'class:'
    //   26: invokevirtual startsWith : (Ljava/lang/String;)Z
    //   29: ifeq -> 44
    //   32: aload_0
    //   33: bipush #6
    //   35: invokevirtual substring : (I)Ljava/lang/String;
    //   38: areturn
    //   39: iconst_0
    //   40: istore_2
    //   41: goto -> 11
    //   44: iload_3
    //   45: iconst_5
    //   46: if_icmple -> 203
    //   49: aload_0
    //   50: iconst_4
    //   51: invokevirtual charAt : (I)C
    //   54: bipush #58
    //   56: if_icmpne -> 203
    //   59: aload_0
    //   60: iconst_0
    //   61: iconst_4
    //   62: invokevirtual substring : (II)Ljava/lang/String;
    //   65: ldc_w 'http'
    //   68: invokevirtual equalsIgnoreCase : (Ljava/lang/String;)Z
    //   71: ifeq -> 203
    //   74: aload_0
    //   75: iconst_5
    //   76: invokevirtual substring : (I)Ljava/lang/String;
    //   79: astore #10
    //   81: iload_3
    //   82: iconst_5
    //   83: isub
    //   84: istore_1
    //   85: iconst_1
    //   86: istore #4
    //   88: iconst_0
    //   89: istore #5
    //   91: new java/lang/StringBuffer
    //   94: dup
    //   95: invokespecial <init> : ()V
    //   98: astore #12
    //   100: aload #10
    //   102: bipush #47
    //   104: iload #5
    //   106: invokevirtual indexOf : (II)I
    //   109: istore #8
    //   111: iload #8
    //   113: ifge -> 274
    //   116: iload_1
    //   117: istore_2
    //   118: aload #12
    //   120: invokevirtual length : ()I
    //   123: ifne -> 280
    //   126: iconst_1
    //   127: istore #6
    //   129: iload #6
    //   131: ifeq -> 286
    //   134: iload #4
    //   136: ifeq -> 286
    //   139: aload #10
    //   141: iload #5
    //   143: iload_2
    //   144: invokevirtual substring : (II)Ljava/lang/String;
    //   147: astore #11
    //   149: aload #11
    //   151: astore_0
    //   152: iload_2
    //   153: iload #5
    //   155: isub
    //   156: iconst_4
    //   157: if_icmple -> 181
    //   160: aload #11
    //   162: astore_0
    //   163: aload #11
    //   165: ldc_w 'www.'
    //   168: invokevirtual startsWith : (Ljava/lang/String;)Z
    //   171: ifeq -> 181
    //   174: aload #11
    //   176: iconst_4
    //   177: invokevirtual substring : (I)Ljava/lang/String;
    //   180: astore_0
    //   181: aload_0
    //   182: aload #12
    //   184: invokestatic putURLWords : (Ljava/lang/String;Ljava/lang/StringBuffer;)V
    //   187: aload #10
    //   189: astore_0
    //   190: iload_1
    //   191: istore_3
    //   192: iload #8
    //   194: ifge -> 441
    //   197: aload #12
    //   199: invokevirtual toString : ()Ljava/lang/String;
    //   202: areturn
    //   203: iload_2
    //   204: istore #4
    //   206: iload_3
    //   207: istore_1
    //   208: aload_0
    //   209: astore #10
    //   211: iload_3
    //   212: iconst_4
    //   213: if_icmple -> 88
    //   216: iload_2
    //   217: istore #4
    //   219: iload_3
    //   220: istore_1
    //   221: aload_0
    //   222: astore #10
    //   224: aload_0
    //   225: iconst_3
    //   226: invokevirtual charAt : (I)C
    //   229: bipush #58
    //   231: if_icmpne -> 88
    //   234: iload_2
    //   235: istore #4
    //   237: iload_3
    //   238: istore_1
    //   239: aload_0
    //   240: astore #10
    //   242: aload_0
    //   243: iconst_0
    //   244: iconst_3
    //   245: invokevirtual substring : (II)Ljava/lang/String;
    //   248: ldc_w 'uri'
    //   251: invokevirtual equalsIgnoreCase : (Ljava/lang/String;)Z
    //   254: ifeq -> 88
    //   257: aload_0
    //   258: iconst_4
    //   259: invokevirtual substring : (I)Ljava/lang/String;
    //   262: astore #10
    //   264: iload_3
    //   265: iconst_4
    //   266: isub
    //   267: istore_1
    //   268: iload_2
    //   269: istore #4
    //   271: goto -> 88
    //   274: iload #8
    //   276: istore_2
    //   277: goto -> 118
    //   280: iconst_0
    //   281: istore #6
    //   283: goto -> 129
    //   286: iload_1
    //   287: istore_3
    //   288: aload #10
    //   290: astore_0
    //   291: iload #5
    //   293: iload_2
    //   294: if_icmpeq -> 192
    //   297: iload #6
    //   299: ifne -> 310
    //   302: aload #12
    //   304: bipush #46
    //   306: invokevirtual append : (C)Ljava/lang/StringBuffer;
    //   309: pop
    //   310: iload_2
    //   311: istore #7
    //   313: iload_1
    //   314: istore_3
    //   315: aload #10
    //   317: astore_0
    //   318: iload_2
    //   319: iload_1
    //   320: if_icmpne -> 424
    //   323: aload #10
    //   325: bipush #46
    //   327: iload_1
    //   328: invokevirtual lastIndexOf : (II)I
    //   331: istore #9
    //   333: iload_2
    //   334: istore #7
    //   336: iload_1
    //   337: istore_3
    //   338: aload #10
    //   340: astore_0
    //   341: iload #9
    //   343: iload #5
    //   345: iconst_1
    //   346: iadd
    //   347: if_icmple -> 424
    //   350: iload_2
    //   351: istore #7
    //   353: iload_1
    //   354: istore_3
    //   355: aload #10
    //   357: astore_0
    //   358: iload #6
    //   360: ifne -> 424
    //   363: iload_1
    //   364: iload #9
    //   366: isub
    //   367: istore #6
    //   369: iload #6
    //   371: iconst_4
    //   372: if_icmple -> 408
    //   375: iload_2
    //   376: istore #7
    //   378: iload_1
    //   379: istore_3
    //   380: aload #10
    //   382: astore_0
    //   383: iload #6
    //   385: iconst_5
    //   386: if_icmpne -> 424
    //   389: iload_2
    //   390: istore #7
    //   392: iload_1
    //   393: istore_3
    //   394: aload #10
    //   396: astore_0
    //   397: aload #10
    //   399: ldc_w 'html'
    //   402: invokevirtual endsWith : (Ljava/lang/String;)Z
    //   405: ifeq -> 424
    //   408: iload_1
    //   409: iload #6
    //   411: isub
    //   412: istore_3
    //   413: iload_3
    //   414: istore #7
    //   416: aload #10
    //   418: iconst_0
    //   419: iload_3
    //   420: invokevirtual substring : (II)Ljava/lang/String;
    //   423: astore_0
    //   424: aload #12
    //   426: aload_0
    //   427: iload #5
    //   429: iload #7
    //   431: invokevirtual substring : (II)Ljava/lang/String;
    //   434: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   437: pop
    //   438: goto -> 192
    //   441: iload #8
    //   443: iconst_1
    //   444: iadd
    //   445: istore #5
    //   447: iload_3
    //   448: istore_1
    //   449: aload_0
    //   450: astore #10
    //   452: goto -> 100
  }
  
  private static void putURLWords(String paramString, StringBuffer paramStringBuffer) {
    int i = paramString.indexOf('.');
    String str = paramString;
    if (i > 0) {
      putURLWords(paramString.substring(i + 1), paramStringBuffer);
      paramStringBuffer.append('.');
      str = paramString.substring(0, i);
    } 
    paramStringBuffer.append(str);
  }
  
  private void registerClass(ClassType paramClassType) {
    if (this.classes == null) {
      this.classes = new ClassType[20];
    } else if (this.numClasses >= this.classes.length) {
      ClassType[] arrayOfClassType1 = new ClassType[this.classes.length * 2];
      System.arraycopy(this.classes, 0, arrayOfClassType1, 0, this.numClasses);
      this.classes = arrayOfClassType1;
    } 
    if (paramClassType.isInterface()) {
      i = 1;
    } else {
      i = 33;
    } 
    paramClassType.addModifiers(i);
    ClassType classType = paramClassType;
    if (paramClassType == this.mainClass) {
      classType = paramClassType;
      if (this.numClasses > 0) {
        classType = this.classes[0];
        this.classes[0] = this.mainClass;
      } 
    } 
    ClassType[] arrayOfClassType = this.classes;
    int i = this.numClasses;
    this.numClasses = i + 1;
    arrayOfClassType[i] = classType;
  }
  
  public static int registerForImmediateLiterals(Compilation paramCompilation) {
    // Byte code:
    //   0: ldc gnu/expr/Compilation
    //   2: monitorenter
    //   3: iconst_0
    //   4: istore_1
    //   5: getstatic gnu/expr/Compilation.chainUninitialized : Lgnu/expr/Compilation;
    //   8: astore_3
    //   9: aload_3
    //   10: ifnull -> 40
    //   13: iload_1
    //   14: istore_2
    //   15: iload_1
    //   16: aload_3
    //   17: getfield keyUninitialized : I
    //   20: if_icmpgt -> 30
    //   23: aload_3
    //   24: getfield keyUninitialized : I
    //   27: iconst_1
    //   28: iadd
    //   29: istore_2
    //   30: aload_3
    //   31: getfield nextUninitialized : Lgnu/expr/Compilation;
    //   34: astore_3
    //   35: iload_2
    //   36: istore_1
    //   37: goto -> 9
    //   40: aload_0
    //   41: iload_1
    //   42: putfield keyUninitialized : I
    //   45: aload_0
    //   46: getstatic gnu/expr/Compilation.chainUninitialized : Lgnu/expr/Compilation;
    //   49: putfield nextUninitialized : Lgnu/expr/Compilation;
    //   52: aload_0
    //   53: putstatic gnu/expr/Compilation.chainUninitialized : Lgnu/expr/Compilation;
    //   56: ldc gnu/expr/Compilation
    //   58: monitorexit
    //   59: iload_1
    //   60: ireturn
    //   61: astore_0
    //   62: ldc gnu/expr/Compilation
    //   64: monitorexit
    //   65: aload_0
    //   66: athrow
    // Exception table:
    //   from	to	target	type
    //   5	9	61	finally
    //   15	30	61	finally
    //   30	35	61	finally
    //   40	56	61	finally
  }
  
  public static void restoreCurrent(Compilation paramCompilation) {
    current.set(paramCompilation);
  }
  
  public static void setCurrent(Compilation paramCompilation) {
    current.set(paramCompilation);
  }
  
  public static Compilation setSaveCurrent(Compilation paramCompilation) {
    Compilation compilation = current.get();
    current.set(paramCompilation);
    return compilation;
  }
  
  public static void setupLiterals(int paramInt) {
    Compilation compilation = findForImmediateLiterals(paramInt);
    try {
      Class clazz = compilation.loader.loadClass(compilation.mainClass.getName());
      for (Literal literal = compilation.litTable.literalsChain; literal != null; literal = literal.next)
        clazz.getDeclaredField(literal.field.getName()).set(null, literal.value); 
      compilation.litTable = null;
      return;
    } catch (Throwable throwable) {
      throw new WrappedException("internal error", throwable);
    } 
  }
  
  private Method startClassInit() {
    this.method = this.curClass.addMethod("<clinit>", apply0args, (Type)Type.voidType, 9);
    CodeAttr codeAttr = this.method.startCode();
    if (this.generateMain || generatingApplet() || generatingServlet()) {
      Method method = ((ClassType)Type.make(getLanguage().getClass())).getDeclaredMethod("registerEnvironment", 0);
      if (method != null)
        codeAttr.emitInvokeStatic(method); 
    } 
    return this.method;
  }
  
  private void varArgsToArray(LambdaExp paramLambdaExp, int paramInt, Variable paramVariable1, Type paramType, Variable paramVariable2) {
    boolean bool;
    CodeAttr codeAttr = getCode();
    Type type = ((ArrayType)paramType).getComponentType();
    if (!"java.lang.Object".equals(type.getName())) {
      bool = true;
    } else {
      bool = false;
    } 
    if (paramVariable2 != null && !bool) {
      codeAttr.emitLoad(paramVariable2);
      codeAttr.emitPushInt(paramInt);
      codeAttr.emitInvokeVirtual(typeCallContext.getDeclaredMethod("getRestArgsArray", 1));
      return;
    } 
    if (paramInt == 0 && !bool) {
      codeAttr.emitLoad(codeAttr.getArg(2));
      return;
    } 
    codeAttr.pushScope();
    Variable variable = paramVariable1;
    if (paramVariable1 == null) {
      variable = codeAttr.addLocal((Type)Type.intType);
      if (paramVariable2 != null) {
        codeAttr.emitLoad(paramVariable2);
        codeAttr.emitInvoke(typeCallContext.getDeclaredMethod("getArgCount", 0));
      } else {
        codeAttr.emitLoad(codeAttr.getArg(2));
        codeAttr.emitArrayLength();
      } 
      if (paramInt != 0) {
        codeAttr.emitPushInt(paramInt);
        codeAttr.emitSub(Type.intType);
      } 
      codeAttr.emitStore(variable);
    } 
    codeAttr.emitLoad(variable);
    codeAttr.emitNewArray(type.getImplementationType());
    Label label1 = new Label(codeAttr);
    Label label2 = new Label(codeAttr);
    label2.setTypes(codeAttr);
    codeAttr.emitGoto(label1);
    label2.define(codeAttr);
    codeAttr.emitDup(1);
    codeAttr.emitLoad(variable);
    if (paramVariable2 != null) {
      codeAttr.emitLoad(paramVariable2);
    } else {
      codeAttr.emitLoad(codeAttr.getArg(2));
    } 
    codeAttr.emitLoad(variable);
    if (paramInt != 0) {
      codeAttr.emitPushInt(paramInt);
      codeAttr.emitAdd(Type.intType);
    } 
    if (paramVariable2 != null) {
      codeAttr.emitInvokeVirtual(typeCallContext.getDeclaredMethod("getArgAsObject", 1));
    } else {
      codeAttr.emitArrayLoad((Type)Type.objectType);
    } 
    if (bool)
      CheckedTarget.emitCheckedCoerce(this, paramLambdaExp, paramLambdaExp.getName(), 0, type, null); 
    codeAttr.emitArrayStore(type);
    label1.define(codeAttr);
    codeAttr.emitInc(variable, (short)-1);
    codeAttr.emitLoad(variable);
    codeAttr.emitGotoIfIntGeZero(label2);
    codeAttr.popScope();
  }
  
  public void addClass(ClassType paramClassType) {
    if (this.mainLambda.filename != null) {
      if (emitSourceDebugExtAttr)
        paramClassType.setStratum(getLanguage().getName()); 
      paramClassType.setSourceFile(this.mainLambda.filename);
    } 
    registerClass(paramClassType);
    paramClassType.setClassfileVersion(defaultClassFileVersion);
  }
  
  public void addMainClass(ModuleExp paramModuleExp) {
    this.mainClass = paramModuleExp.classFor(this);
    ClassType classType3 = this.mainClass;
    ClassType[] arrayOfClassType = paramModuleExp.getInterfaces();
    if (arrayOfClassType != null)
      classType3.setInterfaces(arrayOfClassType); 
    ClassType classType2 = paramModuleExp.getSuperType();
    ClassType classType1 = classType2;
    if (classType2 == null)
      if (generatingApplet()) {
        classType1 = typeApplet;
      } else if (generatingServlet()) {
        classType1 = typeServlet;
      } else {
        classType1 = getModuleType();
      }  
    if (makeRunnable())
      classType3.addInterface(typeRunnable); 
    classType3.setSuper(classType1);
    paramModuleExp.type = classType3;
    addClass(classType3);
    getConstructor(this.mainClass, paramModuleExp);
  }
  
  public Field allocLocalField(Type paramType, String paramString) {
    String str = paramString;
    if (paramString == null) {
      StringBuilder stringBuilder = (new StringBuilder()).append("tmp_");
      int i = this.localFieldIndex + 1;
      this.localFieldIndex = i;
      str = stringBuilder.append(i).toString();
    } 
    return this.curClass.addField(str, paramType, 0);
  }
  
  void callInitMethods(ClassType paramClassType, Vector<ClassType> paramVector) {
    // Byte code:
    //   0: aload_1
    //   1: ifnonnull -> 5
    //   4: return
    //   5: aload_1
    //   6: invokevirtual getName : ()Ljava/lang/String;
    //   9: astore #5
    //   11: ldc_w 'java.lang.Object'
    //   14: aload #5
    //   16: invokevirtual equals : (Ljava/lang/Object;)Z
    //   19: ifne -> 4
    //   22: aload_2
    //   23: invokevirtual size : ()I
    //   26: istore_3
    //   27: iload_3
    //   28: iconst_1
    //   29: isub
    //   30: istore #4
    //   32: iload #4
    //   34: iflt -> 58
    //   37: iload #4
    //   39: istore_3
    //   40: aload_2
    //   41: iload #4
    //   43: invokevirtual elementAt : (I)Ljava/lang/Object;
    //   46: checkcast gnu/bytecode/ClassType
    //   49: invokevirtual getName : ()Ljava/lang/String;
    //   52: aload #5
    //   54: if_acmpne -> 27
    //   57: return
    //   58: aload_2
    //   59: aload_1
    //   60: invokevirtual addElement : (Ljava/lang/Object;)V
    //   63: aload_1
    //   64: invokevirtual getInterfaces : ()[Lgnu/bytecode/ClassType;
    //   67: astore #5
    //   69: aload #5
    //   71: ifnull -> 103
    //   74: aload #5
    //   76: arraylength
    //   77: istore #4
    //   79: iconst_0
    //   80: istore_3
    //   81: iload_3
    //   82: iload #4
    //   84: if_icmpge -> 103
    //   87: aload_0
    //   88: aload #5
    //   90: iload_3
    //   91: aaload
    //   92: aload_2
    //   93: invokevirtual callInitMethods : (Lgnu/bytecode/ClassType;Ljava/util/Vector;)V
    //   96: iload_3
    //   97: iconst_1
    //   98: iadd
    //   99: istore_3
    //   100: goto -> 81
    //   103: iconst_1
    //   104: istore_3
    //   105: aload_1
    //   106: invokevirtual isInterface : ()Z
    //   109: ifeq -> 193
    //   112: aload_1
    //   113: instanceof gnu/expr/PairClassType
    //   116: ifeq -> 155
    //   119: aload_1
    //   120: checkcast gnu/expr/PairClassType
    //   123: getfield instanceType : Lgnu/bytecode/ClassType;
    //   126: astore_1
    //   127: aload_1
    //   128: ldc_w '$finit$'
    //   131: iload_3
    //   132: invokevirtual getDeclaredMethod : (Ljava/lang/String;I)Lgnu/bytecode/Method;
    //   135: astore_1
    //   136: aload_1
    //   137: ifnull -> 4
    //   140: aload_0
    //   141: invokevirtual getCode : ()Lgnu/bytecode/CodeAttr;
    //   144: astore_2
    //   145: aload_2
    //   146: invokevirtual emitPushThis : ()V
    //   149: aload_2
    //   150: aload_1
    //   151: invokevirtual emitInvoke : (Lgnu/bytecode/Method;)V
    //   154: return
    //   155: new java/lang/StringBuilder
    //   158: dup
    //   159: invokespecial <init> : ()V
    //   162: aload_1
    //   163: invokevirtual getName : ()Ljava/lang/String;
    //   166: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   169: ldc_w '$class'
    //   172: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   175: invokevirtual toString : ()Ljava/lang/String;
    //   178: invokestatic forName : (Ljava/lang/String;)Ljava/lang/Class;
    //   181: invokestatic make : (Ljava/lang/Class;)Lgnu/bytecode/Type;
    //   184: checkcast gnu/bytecode/ClassType
    //   187: astore_1
    //   188: goto -> 127
    //   191: astore_1
    //   192: return
    //   193: iconst_0
    //   194: istore_3
    //   195: goto -> 127
    // Exception table:
    //   from	to	target	type
    //   155	188	191	java/lang/Throwable
  }
  
  public void cleanupAfterCompilation() {
    for (int i = 0; i < this.numClasses; i++)
      this.classes[i].cleanupAfterCompilation(); 
    this.classes = null;
    this.minfo.comp = null;
    if (this.minfo.exp != null)
      this.minfo.exp.body = null; 
    this.mainLambda.body = null;
    this.mainLambda = null;
    if (!this.immediate)
      this.litTable = null; 
  }
  
  public void compileConstant(Object paramObject) {
    CodeAttr codeAttr = getCode();
    if (paramObject == null) {
      codeAttr.emitPushNull();
      return;
    } 
    if (paramObject instanceof String && !this.immediate) {
      codeAttr.emitPushString((String)paramObject);
      return;
    } 
    codeAttr.emitGetStatic(compileConstantToField(paramObject));
  }
  
  public void compileConstant(Object paramObject, Target paramTarget) {
    // Byte code:
    //   0: aload_2
    //   1: instanceof gnu/expr/IgnoreTarget
    //   4: ifeq -> 8
    //   7: return
    //   8: aload_1
    //   9: instanceof gnu/mapping/Values
    //   12: ifeq -> 60
    //   15: aload_1
    //   16: checkcast gnu/mapping/Values
    //   19: invokevirtual getValues : ()[Ljava/lang/Object;
    //   22: astore #5
    //   24: aload #5
    //   26: arraylength
    //   27: istore #4
    //   29: aload_2
    //   30: instanceof gnu/expr/ConsumerTarget
    //   33: ifeq -> 60
    //   36: iconst_0
    //   37: istore_3
    //   38: iload_3
    //   39: iload #4
    //   41: if_icmpge -> 7
    //   44: aload_0
    //   45: aload #5
    //   47: iload_3
    //   48: aaload
    //   49: aload_2
    //   50: invokevirtual compileConstant : (Ljava/lang/Object;Lgnu/expr/Target;)V
    //   53: iload_3
    //   54: iconst_1
    //   55: iadd
    //   56: istore_3
    //   57: goto -> 38
    //   60: aload_2
    //   61: instanceof gnu/expr/ConditionalTarget
    //   64: ifeq -> 110
    //   67: aload_2
    //   68: checkcast gnu/expr/ConditionalTarget
    //   71: astore #5
    //   73: aload_0
    //   74: invokevirtual getCode : ()Lgnu/bytecode/CodeAttr;
    //   77: astore_2
    //   78: aload_0
    //   79: invokevirtual getLanguage : ()Lgnu/expr/Language;
    //   82: aload_1
    //   83: invokevirtual isTrue : (Ljava/lang/Object;)Z
    //   86: ifeq -> 101
    //   89: aload #5
    //   91: getfield ifTrue : Lgnu/bytecode/Label;
    //   94: astore_1
    //   95: aload_2
    //   96: aload_1
    //   97: invokevirtual emitGoto : (Lgnu/bytecode/Label;)V
    //   100: return
    //   101: aload #5
    //   103: getfield ifFalse : Lgnu/bytecode/Label;
    //   106: astore_1
    //   107: goto -> 95
    //   110: aload_1
    //   111: astore #5
    //   113: aload_2
    //   114: instanceof gnu/expr/StackTarget
    //   117: ifeq -> 398
    //   120: aload_2
    //   121: checkcast gnu/expr/StackTarget
    //   124: invokevirtual getType : ()Lgnu/bytecode/Type;
    //   127: astore #6
    //   129: aload #6
    //   131: instanceof gnu/bytecode/PrimType
    //   134: ifeq -> 263
    //   137: aload #6
    //   139: invokevirtual getSignature : ()Ljava/lang/String;
    //   142: astore #7
    //   144: aload_0
    //   145: invokevirtual getCode : ()Lgnu/bytecode/CodeAttr;
    //   148: astore #5
    //   150: aload #7
    //   152: ifnull -> 538
    //   155: aload #7
    //   157: invokevirtual length : ()I
    //   160: iconst_1
    //   161: if_icmpeq -> 287
    //   164: goto -> 538
    //   167: aload_1
    //   168: instanceof java/lang/Number
    //   171: ifeq -> 240
    //   174: aload_1
    //   175: checkcast java/lang/Number
    //   178: astore #7
    //   180: iload_3
    //   181: lookupswitch default -> 544, 66 -> 319, 68 -> 352, 70 -> 341, 73 -> 297, 74 -> 330, 83 -> 308
    //   240: iload_3
    //   241: bipush #67
    //   243: if_icmpne -> 363
    //   246: aload #5
    //   248: aload #6
    //   250: checkcast gnu/bytecode/PrimType
    //   253: aload_1
    //   254: invokevirtual charValue : (Ljava/lang/Object;)C
    //   257: invokevirtual emitPushInt : (I)V
    //   260: return
    //   261: astore #5
    //   263: aload #6
    //   265: getstatic gnu/expr/Compilation.typeClass : Lgnu/bytecode/ClassType;
    //   268: if_acmpne -> 390
    //   271: aload_1
    //   272: instanceof gnu/bytecode/ClassType
    //   275: ifeq -> 390
    //   278: aload_0
    //   279: aload_1
    //   280: checkcast gnu/bytecode/ClassType
    //   283: invokevirtual loadClassRef : (Lgnu/bytecode/ObjectType;)V
    //   286: return
    //   287: aload #7
    //   289: iconst_0
    //   290: invokevirtual charAt : (I)C
    //   293: istore_3
    //   294: goto -> 167
    //   297: aload #5
    //   299: aload #7
    //   301: invokevirtual intValue : ()I
    //   304: invokevirtual emitPushInt : (I)V
    //   307: return
    //   308: aload #5
    //   310: aload #7
    //   312: invokevirtual shortValue : ()S
    //   315: invokevirtual emitPushInt : (I)V
    //   318: return
    //   319: aload #5
    //   321: aload #7
    //   323: invokevirtual byteValue : ()B
    //   326: invokevirtual emitPushInt : (I)V
    //   329: return
    //   330: aload #5
    //   332: aload #7
    //   334: invokevirtual longValue : ()J
    //   337: invokevirtual emitPushLong : (J)V
    //   340: return
    //   341: aload #5
    //   343: aload #7
    //   345: invokevirtual floatValue : ()F
    //   348: invokevirtual emitPushFloat : (F)V
    //   351: return
    //   352: aload #5
    //   354: aload #7
    //   356: invokevirtual doubleValue : ()D
    //   359: invokevirtual emitPushDouble : (D)V
    //   362: return
    //   363: iload_3
    //   364: bipush #90
    //   366: if_icmpne -> 263
    //   369: aload_1
    //   370: invokestatic booleanValue : (Ljava/lang/Object;)Z
    //   373: ifeq -> 385
    //   376: iconst_1
    //   377: istore_3
    //   378: aload #5
    //   380: iload_3
    //   381: invokevirtual emitPushInt : (I)V
    //   384: return
    //   385: iconst_0
    //   386: istore_3
    //   387: goto -> 378
    //   390: aload #6
    //   392: aload_1
    //   393: invokevirtual coerceFromObject : (Ljava/lang/Object;)Ljava/lang/Object;
    //   396: astore #5
    //   398: aload_0
    //   399: aload #5
    //   401: invokevirtual compileConstant : (Ljava/lang/Object;)V
    //   404: aload #5
    //   406: ifnonnull -> 526
    //   409: aload_2
    //   410: invokevirtual getType : ()Lgnu/bytecode/Type;
    //   413: astore_1
    //   414: aload_2
    //   415: aload_0
    //   416: aload_1
    //   417: invokevirtual compileFromStack : (Lgnu/expr/Compilation;Lgnu/bytecode/Type;)V
    //   420: return
    //   421: astore #5
    //   423: new java/lang/StringBuffer
    //   426: dup
    //   427: invokespecial <init> : ()V
    //   430: astore #5
    //   432: aload_1
    //   433: getstatic gnu/mapping/Values.empty : Lgnu/mapping/Values;
    //   436: if_acmpne -> 476
    //   439: aload #5
    //   441: ldc_w 'cannot convert void to '
    //   444: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   447: pop
    //   448: aload #5
    //   450: aload #6
    //   452: invokevirtual getName : ()Ljava/lang/String;
    //   455: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   458: pop
    //   459: aload_0
    //   460: bipush #119
    //   462: aload #5
    //   464: invokevirtual toString : ()Ljava/lang/String;
    //   467: invokevirtual error : (CLjava/lang/String;)V
    //   470: aload_1
    //   471: astore #5
    //   473: goto -> 398
    //   476: aload #5
    //   478: ldc_w 'cannot convert literal (of type '
    //   481: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   484: pop
    //   485: aload_1
    //   486: ifnonnull -> 510
    //   489: aload #5
    //   491: ldc_w '<null>'
    //   494: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   497: pop
    //   498: aload #5
    //   500: ldc_w ') to '
    //   503: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   506: pop
    //   507: goto -> 448
    //   510: aload #5
    //   512: aload_1
    //   513: invokevirtual getClass : ()Ljava/lang/Class;
    //   516: invokevirtual getName : ()Ljava/lang/String;
    //   519: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   522: pop
    //   523: goto -> 498
    //   526: aload #5
    //   528: invokevirtual getClass : ()Ljava/lang/Class;
    //   531: invokestatic make : (Ljava/lang/Class;)Lgnu/bytecode/Type;
    //   534: astore_1
    //   535: goto -> 414
    //   538: bipush #32
    //   540: istore_3
    //   541: goto -> 167
    //   544: goto -> 240
    // Exception table:
    //   from	to	target	type
    //   137	150	261	java/lang/ClassCastException
    //   155	164	261	java/lang/ClassCastException
    //   167	180	261	java/lang/ClassCastException
    //   246	260	261	java/lang/ClassCastException
    //   287	294	261	java/lang/ClassCastException
    //   297	307	261	java/lang/ClassCastException
    //   308	318	261	java/lang/ClassCastException
    //   319	329	261	java/lang/ClassCastException
    //   330	340	261	java/lang/ClassCastException
    //   341	351	261	java/lang/ClassCastException
    //   352	362	261	java/lang/ClassCastException
    //   369	376	261	java/lang/ClassCastException
    //   378	384	261	java/lang/ClassCastException
    //   390	398	421	java/lang/Exception
  }
  
  public Field compileConstantToField(Object paramObject) {
    paramObject = this.litTable.findLiteral(paramObject);
    if (((Literal)paramObject).field == null)
      paramObject.assign(this.litTable); 
    return ((Literal)paramObject).field;
  }
  
  public void compileToArchive(ModuleExp paramModuleExp, String paramString) throws IOException {
    ZipOutputStream zipOutputStream;
    if (paramString.endsWith(".zip")) {
      i = 0;
    } else if (paramString.endsWith(".jar")) {
      i = 1;
    } else {
      paramString = paramString + ".zip";
      i = 0;
    } 
    process(12);
    File file = new File(paramString);
    if (file.exists())
      file.delete(); 
    if (i) {
      zipOutputStream = new JarOutputStream(new FileOutputStream(file));
    } else {
      zipOutputStream = new ZipOutputStream(new FileOutputStream((File)zipOutputStream));
    } 
    byte[][] arrayOfByte = new byte[this.numClasses][];
    CRC32 cRC32 = new CRC32();
    int i;
    for (i = 0; i < this.numClasses; i++) {
      ClassType classType = this.classes[i];
      arrayOfByte[i] = classType.writeToArray();
      ZipEntry zipEntry = new ZipEntry(classType.getName().replace('.', '/') + ".class");
      zipEntry.setSize((arrayOfByte[i]).length);
      cRC32.reset();
      cRC32.update(arrayOfByte[i], 0, (arrayOfByte[i]).length);
      zipEntry.setCrc(cRC32.getValue());
      zipOutputStream.putNextEntry(zipEntry);
      zipOutputStream.write(arrayOfByte[i]);
    } 
    zipOutputStream.close();
  }
  
  public LambdaExp currentLambda() {
    return this.current_scope.currentLambda();
  }
  
  public ModuleExp currentModule() {
    return this.current_scope.currentModule();
  }
  
  public ScopeExp currentScope() {
    return this.current_scope;
  }
  
  public void error(char paramChar, Declaration paramDeclaration, String paramString1, String paramString2) {
    error(paramChar, paramString1 + paramDeclaration.getName() + paramString2, (String)null, paramDeclaration);
  }
  
  public void error(char paramChar, String paramString) {
    char c = paramChar;
    if (paramChar == 'w') {
      c = paramChar;
      if (warnAsError())
        c = 'e'; 
    } 
    this.messages.error(c, this, paramString);
  }
  
  public void error(char paramChar, String paramString, SourceLocator paramSourceLocator) {
    // Byte code:
    //   0: aload_3
    //   1: invokeinterface getFileName : ()Ljava/lang/String;
    //   6: astore #8
    //   8: aload_3
    //   9: invokeinterface getLineNumber : ()I
    //   14: istore #7
    //   16: aload_3
    //   17: invokeinterface getColumnNumber : ()I
    //   22: istore #6
    //   24: aload #8
    //   26: ifnull -> 41
    //   29: aload #8
    //   31: astore_3
    //   32: iload #7
    //   34: istore #5
    //   36: iload #7
    //   38: ifgt -> 58
    //   41: aload_0
    //   42: invokevirtual getFileName : ()Ljava/lang/String;
    //   45: astore_3
    //   46: aload_0
    //   47: invokevirtual getLineNumber : ()I
    //   50: istore #5
    //   52: aload_0
    //   53: invokevirtual getColumnNumber : ()I
    //   56: istore #6
    //   58: iload_1
    //   59: istore #4
    //   61: iload_1
    //   62: bipush #119
    //   64: if_icmpne -> 81
    //   67: iload_1
    //   68: istore #4
    //   70: aload_0
    //   71: invokevirtual warnAsError : ()Z
    //   74: ifeq -> 81
    //   77: bipush #101
    //   79: istore #4
    //   81: aload_0
    //   82: getfield messages : Lgnu/text/SourceMessages;
    //   85: iload #4
    //   87: aload_3
    //   88: iload #5
    //   90: iload #6
    //   92: aload_2
    //   93: invokevirtual error : (CLjava/lang/String;IILjava/lang/String;)V
    //   96: return
  }
  
  public void error(char paramChar, String paramString1, String paramString2, Declaration paramDeclaration) {
    char c = paramChar;
    if (paramChar == 'w') {
      c = paramChar;
      if (warnAsError())
        c = 'e'; 
    } 
    String str = getFileName();
    int i = getLineNumber();
    int j = getColumnNumber();
    int k = paramDeclaration.getLineNumber();
    if (k > 0) {
      str = paramDeclaration.getFileName();
      i = k;
      j = paramDeclaration.getColumnNumber();
    } 
    this.messages.error(c, str, i, j, paramString1, paramString2);
  }
  
  public ClassType findNamedClass(String paramString) {
    for (int i = 0; i < this.numClasses; i++) {
      if (paramString.equals(this.classes[i].getName()))
        return this.classes[i]; 
    } 
    return null;
  }
  
  public void freeLocalField(Field paramField) {}
  
  public void generateApplyMethodsWithContext(LambdaExp paramLambdaExp) {
    int i;
    if (paramLambdaExp.applyMethods == null) {
      i = 0;
    } else {
      i = paramLambdaExp.applyMethods.size();
    } 
    if (i == 0)
      return; 
    ClassType classType3 = this.curClass;
    this.curClass = paramLambdaExp.getHeapFrameType();
    if (!this.curClass.getSuperclass().isSubtype((Type)typeModuleWithContext))
      this.curClass = this.moduleClass; 
    ClassType classType1 = typeModuleMethod;
    Method method = this.method;
    classType1 = typeCallContext;
    ClassType classType2 = this.curClass;
    PrimType primType = Type.voidType;
    this.method = classType2.addMethod("apply", new Type[] { (Type)classType1 }, (Type)primType, 1);
    CodeAttr codeAttr = this.method.startCode();
    Variable variable = codeAttr.getArg(1);
    codeAttr.emitLoad(variable);
    codeAttr.emitGetField(pcCallContextField);
    SwitchState switchState = codeAttr.startSwitch();
    int j;
    for (j = 0; j < i; j++) {
      LambdaExp lambdaExp = paramLambdaExp.applyMethods.elementAt(j);
      Method[] arrayOfMethod = lambdaExp.primMethods;
      int m = arrayOfMethod.length;
      int k;
      for (k = 0; k < m; k++) {
        boolean bool;
        byte b;
        Variable variable1;
        if (k == m - 1 && (lambdaExp.max_args < 0 || lambdaExp.max_args >= lambdaExp.min_args + m)) {
          bool = true;
        } else {
          bool = false;
        } 
        switchState.addCase(lambdaExp.getSelectorValue(this) + k, codeAttr);
        SourceLocator sourceLocator = this.messages.swapSourceLocator(lambdaExp);
        int n = lambdaExp.getLineNumber();
        if (n > 0)
          codeAttr.putLineNumber(lambdaExp.getFileName(), n); 
        Method method1 = arrayOfMethod[k];
        Type[] arrayOfType = method1.getParameterTypes();
        int i3 = lambdaExp.min_args + k;
        classType1 = null;
        int i1 = 0;
        classType2 = classType1;
        if (k > 4) {
          classType2 = classType1;
          if (m > 1) {
            variable1 = codeAttr.addLocal((Type)Type.intType);
            codeAttr.emitLoad(variable);
            codeAttr.emitInvoke(typeCallContext.getDeclaredMethod("getArgCount", 0));
            if (lambdaExp.min_args != 0) {
              codeAttr.emitPushInt(lambdaExp.min_args);
              codeAttr.emitSub(Type.intType);
            } 
            codeAttr.emitStore(variable1);
          } 
        } 
        if (method1.getStaticFlag()) {
          n = 0;
        } else {
          n = 1;
        } 
        if (bool) {
          b = 2;
        } else {
          b = 1;
        } 
        if (b + i3 < arrayOfType.length) {
          b = 1;
        } else {
          b = 0;
        } 
        if (n + b > 0) {
          codeAttr.emitPushThis();
          if (this.curClass == this.moduleClass && this.mainClass != this.moduleClass)
            codeAttr.emitGetField(this.moduleInstanceMainField); 
        } 
        Declaration declaration2 = lambdaExp.firstDecl();
        Declaration declaration1 = declaration2;
        if (declaration2 != null) {
          declaration1 = declaration2;
          if (declaration2.isThisParameter())
            declaration1 = declaration2.nextDecl(); 
        } 
        int i2 = 0;
        n = i1;
        i1 = i2;
        while (i1 < i3) {
          i2 = n;
          if (variable1 != null) {
            i2 = n;
            if (i1 >= lambdaExp.min_args) {
              codeAttr.emitLoad(variable1);
              codeAttr.emitIfIntLEqZero();
              codeAttr.emitLoad(variable);
              codeAttr.emitInvoke(arrayOfMethod[i1 - lambdaExp.min_args]);
              codeAttr.emitElse();
              i2 = n + 1;
              codeAttr.emitInc(variable1, (short)-1);
            } 
          } 
          codeAttr.emitLoad(variable);
          if (i1 <= 4 && !bool && lambdaExp.max_args <= 4) {
            codeAttr.emitGetField(typeCallContext.getDeclaredField("value" + (i1 + 1)));
          } else {
            codeAttr.emitGetField(typeCallContext.getDeclaredField("values"));
            codeAttr.emitPushInt(i1);
            codeAttr.emitArrayLoad((Type)Type.objectType);
          } 
          Type type = declaration1.getType();
          if (type != Type.objectType) {
            SourceLocator sourceLocator1 = this.messages.swapSourceLocator(declaration1);
            CheckedTarget.emitCheckedCoerce(this, lambdaExp, i1 + 1, type);
            this.messages.swapSourceLocator(sourceLocator1);
          } 
          declaration1 = declaration1.nextDecl();
          i1++;
          n = i2;
        } 
        if (bool) {
          Type type = arrayOfType[b + i3];
          if (type instanceof ArrayType) {
            varArgsToArray(lambdaExp, i3, variable1, type, variable);
          } else if ("gnu.lists.LList".equals(type.getName())) {
            codeAttr.emitLoad(variable);
            codeAttr.emitPushInt(i3);
            codeAttr.emitInvokeVirtual(typeCallContext.getDeclaredMethod("getRestArgsList", 1));
          } else if (type == typeCallContext) {
            codeAttr.emitLoad(variable);
          } else {
            throw new RuntimeException("unsupported #!rest type:" + type);
          } 
        } 
        codeAttr.emitLoad(variable);
        codeAttr.emitInvoke(method1);
        while (true) {
          if (--n >= 0) {
            codeAttr.emitFi();
            continue;
          } 
          if (defaultCallConvention < 2)
            Target.pushObject.compileFromStack(this, lambdaExp.getReturnType()); 
          break;
        } 
        this.messages.swapSourceLocator(sourceLocator);
        codeAttr.emitReturn();
      } 
    } 
    switchState.addDefault(codeAttr);
    codeAttr.emitInvokeStatic(typeModuleMethod.getDeclaredMethod("applyError", 0));
    codeAttr.emitReturn();
    switchState.finish(codeAttr);
    this.method = method;
    this.curClass = classType3;
  }
  
  public void generateApplyMethodsWithoutContext(LambdaExp paramLambdaExp) {
    int i;
    int j;
    if (paramLambdaExp.applyMethods == null) {
      j = 0;
    } else {
      j = paramLambdaExp.applyMethods.size();
    } 
    if (j == 0)
      return; 
    ClassType classType1 = this.curClass;
    this.curClass = paramLambdaExp.getHeapFrameType();
    ClassType classType2 = typeModuleMethod;
    if (!this.curClass.getSuperclass().isSubtype((Type)typeModuleBody))
      this.curClass = this.moduleClass; 
    Method method = this.method;
    CodeAttr codeAttr = null;
    if (defaultCallConvention >= 2) {
      i = 5;
    } else {
      i = 0;
    } 
    while (i < 6) {
      Object object;
      boolean bool = false;
      SwitchState switchState = null;
      String str = null;
      Type[] arrayOfType = null;
      int k = 0;
      while (true) {
        k++;
        object = SYNTHETIC_LOCAL_VARIABLE_3;
      } 
      if (object != null) {
        switchState.addDefault(codeAttr);
        if (defaultCallConvention >= 2) {
          codeAttr.emitInvokeStatic(typeModuleMethod.getDeclaredMethod("applyError", 0));
        } else {
          int m;
          if (i > 4) {
            m = 2;
          } else {
            m = i + 1;
          } 
          int n;
          for (n = 0; n < m + 1; n++)
            codeAttr.emitLoad(codeAttr.getArg(n)); 
          codeAttr.emitInvokeSpecial(typeModuleBody.getDeclaredMethod(str, arrayOfType));
        } 
        codeAttr.emitReturn();
        switchState.finish(codeAttr);
      } 
      continue;
      i++;
    } 
    this.method = method;
    this.curClass = classType1;
  }
  
  void generateBytecode() {
    // Byte code:
    //   0: aload_0
    //   1: invokevirtual getModule : ()Lgnu/expr/ModuleExp;
    //   4: astore #8
    //   6: getstatic gnu/expr/Compilation.debugPrintFinalExpr : Z
    //   9: ifeq -> 87
    //   12: invokestatic errDefault : ()Lgnu/mapping/OutPort;
    //   15: astore #5
    //   17: aload #5
    //   19: new java/lang/StringBuilder
    //   22: dup
    //   23: invokespecial <init> : ()V
    //   26: ldc_w '[Compiling final '
    //   29: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   32: aload #8
    //   34: invokevirtual getName : ()Ljava/lang/String;
    //   37: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   40: ldc_w ' to '
    //   43: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   46: aload_0
    //   47: getfield mainClass : Lgnu/bytecode/ClassType;
    //   50: invokevirtual getName : ()Ljava/lang/String;
    //   53: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   56: ldc_w ':'
    //   59: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   62: invokevirtual toString : ()Ljava/lang/String;
    //   65: invokevirtual println : (Ljava/lang/String;)V
    //   68: aload #8
    //   70: aload #5
    //   72: invokevirtual print : (Lgnu/mapping/OutPort;)V
    //   75: aload #5
    //   77: bipush #93
    //   79: invokevirtual println : (C)V
    //   82: aload #5
    //   84: invokevirtual flush : ()V
    //   87: aload_0
    //   88: invokevirtual getModuleType : ()Lgnu/bytecode/ClassType;
    //   91: astore #5
    //   93: aload_0
    //   94: getfield mainClass : Lgnu/bytecode/ClassType;
    //   97: invokevirtual getSuperclass : ()Lgnu/bytecode/ClassType;
    //   100: aload #5
    //   102: invokevirtual isSubtype : (Lgnu/bytecode/Type;)Z
    //   105: ifeq -> 580
    //   108: aload_0
    //   109: aload_0
    //   110: getfield mainClass : Lgnu/bytecode/ClassType;
    //   113: putfield moduleClass : Lgnu/bytecode/ClassType;
    //   116: aload_0
    //   117: aload #8
    //   119: getfield type : Lgnu/bytecode/ClassType;
    //   122: putfield curClass : Lgnu/bytecode/ClassType;
    //   125: aload_0
    //   126: getfield curLambda : Lgnu/expr/LambdaExp;
    //   129: astore #9
    //   131: aload_0
    //   132: aload #8
    //   134: putfield curLambda : Lgnu/expr/LambdaExp;
    //   137: aload #8
    //   139: invokevirtual isHandlingTailCalls : ()Z
    //   142: ifeq -> 627
    //   145: iconst_1
    //   146: anewarray gnu/bytecode/Type
    //   149: astore #5
    //   151: aload #5
    //   153: iconst_0
    //   154: getstatic gnu/expr/Compilation.typeCallContext : Lgnu/bytecode/ClassType;
    //   157: aastore
    //   158: aload #8
    //   160: getfield heapFrame : Lgnu/bytecode/Variable;
    //   163: astore #10
    //   165: aload #8
    //   167: invokevirtual isStatic : ()Z
    //   170: istore #4
    //   172: aload_0
    //   173: aload_0
    //   174: getfield curClass : Lgnu/bytecode/ClassType;
    //   177: ldc_w 'run'
    //   180: aload #5
    //   182: getstatic gnu/bytecode/Type.voidType : Lgnu/bytecode/PrimType;
    //   185: bipush #17
    //   187: invokevirtual addMethod : (Ljava/lang/String;[Lgnu/bytecode/Type;Lgnu/bytecode/Type;I)Lgnu/bytecode/Method;
    //   190: putfield method : Lgnu/bytecode/Method;
    //   193: aload_0
    //   194: getfield method : Lgnu/bytecode/Method;
    //   197: invokevirtual initCode : ()V
    //   200: aload_0
    //   201: invokevirtual getCode : ()Lgnu/bytecode/CodeAttr;
    //   204: astore #6
    //   206: aload_0
    //   207: getfield method : Lgnu/bytecode/Method;
    //   210: invokevirtual getStaticFlag : ()Z
    //   213: ifeq -> 706
    //   216: aconst_null
    //   217: astore #5
    //   219: aload_0
    //   220: aload #5
    //   222: putfield thisDecl : Lgnu/bytecode/Variable;
    //   225: aload #8
    //   227: aload #8
    //   229: getfield thisVariable : Lgnu/bytecode/Variable;
    //   232: putfield closureEnv : Lgnu/bytecode/Variable;
    //   235: aload #8
    //   237: invokevirtual isStatic : ()Z
    //   240: ifeq -> 721
    //   243: aconst_null
    //   244: astore #5
    //   246: aload #8
    //   248: aload #5
    //   250: putfield heapFrame : Lgnu/bytecode/Variable;
    //   253: aload #8
    //   255: aload_0
    //   256: invokevirtual allocChildClasses : (Lgnu/expr/Compilation;)V
    //   259: aload #8
    //   261: invokevirtual isHandlingTailCalls : ()Z
    //   264: ifne -> 274
    //   267: aload_0
    //   268: invokevirtual usingCPStyle : ()Z
    //   271: ifeq -> 315
    //   274: aload_0
    //   275: new gnu/bytecode/Variable
    //   278: dup
    //   279: ldc_w '$ctx'
    //   282: getstatic gnu/expr/Compilation.typeCallContext : Lgnu/bytecode/ClassType;
    //   285: invokespecial <init> : (Ljava/lang/String;Lgnu/bytecode/Type;)V
    //   288: putfield callContextVar : Lgnu/bytecode/Variable;
    //   291: aload #8
    //   293: invokevirtual getVarScope : ()Lgnu/bytecode/Scope;
    //   296: aload_0
    //   297: getfield thisDecl : Lgnu/bytecode/Variable;
    //   300: aload_0
    //   301: getfield callContextVar : Lgnu/bytecode/Variable;
    //   304: invokevirtual addVariableAfter : (Lgnu/bytecode/Variable;Lgnu/bytecode/Variable;)V
    //   307: aload_0
    //   308: getfield callContextVar : Lgnu/bytecode/Variable;
    //   311: iconst_1
    //   312: invokevirtual setParameter : (Z)V
    //   315: aload #8
    //   317: invokevirtual getLineNumber : ()I
    //   320: istore_2
    //   321: iload_2
    //   322: ifle -> 336
    //   325: aload #6
    //   327: aload #8
    //   329: invokevirtual getFileName : ()Ljava/lang/String;
    //   332: iload_2
    //   333: invokevirtual putLineNumber : (Ljava/lang/String;I)V
    //   336: aload #8
    //   338: aload_0
    //   339: invokevirtual allocParameters : (Lgnu/expr/Compilation;)V
    //   342: aload #8
    //   344: aload_0
    //   345: invokevirtual enterFunction : (Lgnu/expr/Compilation;)V
    //   348: aload_0
    //   349: invokevirtual usingCPStyle : ()Z
    //   352: ifeq -> 387
    //   355: aload_0
    //   356: invokevirtual loadCallContext : ()V
    //   359: aload #6
    //   361: getstatic gnu/expr/Compilation.pcCallContextField : Lgnu/bytecode/Field;
    //   364: invokevirtual emitGetField : (Lgnu/bytecode/Field;)V
    //   367: aload_0
    //   368: aload #6
    //   370: invokevirtual startSwitch : ()Lgnu/bytecode/SwitchState;
    //   373: putfield fswitch : Lgnu/bytecode/SwitchState;
    //   376: aload_0
    //   377: getfield fswitch : Lgnu/bytecode/SwitchState;
    //   380: iconst_0
    //   381: aload #6
    //   383: invokevirtual addCase : (ILgnu/bytecode/CodeAttr;)Z
    //   386: pop
    //   387: aload #8
    //   389: aload_0
    //   390: invokevirtual compileBody : (Lgnu/expr/Compilation;)V
    //   393: aload #8
    //   395: aload_0
    //   396: invokevirtual compileEnd : (Lgnu/expr/Compilation;)V
    //   399: aconst_null
    //   400: astore #6
    //   402: aconst_null
    //   403: astore #5
    //   405: aconst_null
    //   406: astore #7
    //   408: aload_0
    //   409: getfield curClass : Lgnu/bytecode/ClassType;
    //   412: aload_0
    //   413: getfield mainClass : Lgnu/bytecode/ClassType;
    //   416: if_acmpne -> 1051
    //   419: aload_0
    //   420: getfield method : Lgnu/bytecode/Method;
    //   423: astore #11
    //   425: aload_0
    //   426: getfield callContextVar : Lgnu/bytecode/Variable;
    //   429: astore #12
    //   431: aload_0
    //   432: aconst_null
    //   433: putfield callContextVar : Lgnu/bytecode/Variable;
    //   436: aload_0
    //   437: invokespecial startClassInit : ()Lgnu/bytecode/Method;
    //   440: astore #7
    //   442: aload_0
    //   443: aload #7
    //   445: putfield clinitMethod : Lgnu/bytecode/Method;
    //   448: aload_0
    //   449: invokevirtual getCode : ()Lgnu/bytecode/CodeAttr;
    //   452: astore #13
    //   454: new gnu/bytecode/Label
    //   457: dup
    //   458: aload #13
    //   460: invokespecial <init> : (Lgnu/bytecode/CodeAttr;)V
    //   463: astore #6
    //   465: new gnu/bytecode/Label
    //   468: dup
    //   469: aload #13
    //   471: invokespecial <init> : (Lgnu/bytecode/CodeAttr;)V
    //   474: astore #5
    //   476: aload #13
    //   478: aload #5
    //   480: aload #6
    //   482: invokevirtual fixupChain : (Lgnu/bytecode/Label;Lgnu/bytecode/Label;)V
    //   485: iload #4
    //   487: ifeq -> 555
    //   490: aload_0
    //   491: aload #8
    //   493: invokevirtual generateConstructor : (Lgnu/expr/LambdaExp;)V
    //   496: aload #13
    //   498: aload_0
    //   499: getfield moduleClass : Lgnu/bytecode/ClassType;
    //   502: invokevirtual emitNew : (Lgnu/bytecode/ClassType;)V
    //   505: aload #13
    //   507: aload_0
    //   508: getfield moduleClass : Lgnu/bytecode/ClassType;
    //   511: invokevirtual emitDup : (Lgnu/bytecode/Type;)V
    //   514: aload #13
    //   516: aload_0
    //   517: getfield moduleClass : Lgnu/bytecode/ClassType;
    //   520: getfield constructor : Lgnu/bytecode/Method;
    //   523: invokevirtual emitInvokeSpecial : (Lgnu/bytecode/Method;)V
    //   526: aload_0
    //   527: aload_0
    //   528: getfield moduleClass : Lgnu/bytecode/ClassType;
    //   531: ldc_w '$instance'
    //   534: aload_0
    //   535: getfield moduleClass : Lgnu/bytecode/ClassType;
    //   538: bipush #25
    //   540: invokevirtual addField : (Ljava/lang/String;Lgnu/bytecode/Type;I)Lgnu/bytecode/Field;
    //   543: putfield moduleInstanceMainField : Lgnu/bytecode/Field;
    //   546: aload #13
    //   548: aload_0
    //   549: getfield moduleInstanceMainField : Lgnu/bytecode/Field;
    //   552: invokevirtual emitPutStatic : (Lgnu/bytecode/Field;)V
    //   555: aload_0
    //   556: getfield clinitChain : Lgnu/expr/Initializer;
    //   559: astore #14
    //   561: aload #14
    //   563: ifnull -> 731
    //   566: aload_0
    //   567: aconst_null
    //   568: putfield clinitChain : Lgnu/expr/Initializer;
    //   571: aload_0
    //   572: aload #14
    //   574: invokespecial dumpInitializers : (Lgnu/expr/Initializer;)V
    //   577: goto -> 555
    //   580: aload_0
    //   581: new gnu/bytecode/ClassType
    //   584: dup
    //   585: aload_0
    //   586: ldc_w 'frame'
    //   589: invokevirtual generateClassName : (Ljava/lang/String;)Ljava/lang/String;
    //   592: invokespecial <init> : (Ljava/lang/String;)V
    //   595: putfield moduleClass : Lgnu/bytecode/ClassType;
    //   598: aload_0
    //   599: getfield moduleClass : Lgnu/bytecode/ClassType;
    //   602: aload #5
    //   604: invokevirtual setSuper : (Lgnu/bytecode/ClassType;)V
    //   607: aload_0
    //   608: aload_0
    //   609: getfield moduleClass : Lgnu/bytecode/ClassType;
    //   612: invokevirtual addClass : (Lgnu/bytecode/ClassType;)V
    //   615: aload_0
    //   616: aload_0
    //   617: getfield moduleClass : Lgnu/bytecode/ClassType;
    //   620: aconst_null
    //   621: invokevirtual generateConstructor : (Lgnu/bytecode/ClassType;Lgnu/expr/LambdaExp;)V
    //   624: goto -> 116
    //   627: aload #8
    //   629: getfield min_args : I
    //   632: aload #8
    //   634: getfield max_args : I
    //   637: if_icmpne -> 649
    //   640: aload #8
    //   642: getfield min_args : I
    //   645: iconst_4
    //   646: if_icmple -> 672
    //   649: iconst_1
    //   650: anewarray gnu/bytecode/Type
    //   653: astore #5
    //   655: aload #5
    //   657: iconst_0
    //   658: new gnu/bytecode/ArrayType
    //   661: dup
    //   662: getstatic gnu/expr/Compilation.typeObject : Lgnu/bytecode/ClassType;
    //   665: invokespecial <init> : (Lgnu/bytecode/Type;)V
    //   668: aastore
    //   669: goto -> 158
    //   672: aload #8
    //   674: getfield min_args : I
    //   677: istore_2
    //   678: iload_2
    //   679: anewarray gnu/bytecode/Type
    //   682: astore #6
    //   684: iload_2
    //   685: iconst_1
    //   686: isub
    //   687: istore_2
    //   688: aload #6
    //   690: astore #5
    //   692: iload_2
    //   693: iflt -> 158
    //   696: aload #6
    //   698: iload_2
    //   699: getstatic gnu/expr/Compilation.typeObject : Lgnu/bytecode/ClassType;
    //   702: aastore
    //   703: goto -> 684
    //   706: aload #8
    //   708: aload #8
    //   710: getfield type : Lgnu/bytecode/ClassType;
    //   713: invokevirtual declareThis : (Lgnu/bytecode/ClassType;)Lgnu/bytecode/Variable;
    //   716: astore #5
    //   718: goto -> 219
    //   721: aload #8
    //   723: getfield thisVariable : Lgnu/bytecode/Variable;
    //   726: astore #5
    //   728: goto -> 246
    //   731: aload #8
    //   733: invokevirtual staticInitRun : ()Z
    //   736: ifeq -> 763
    //   739: aload #13
    //   741: aload_0
    //   742: getfield moduleInstanceMainField : Lgnu/bytecode/Field;
    //   745: invokevirtual emitGetStatic : (Lgnu/bytecode/Field;)V
    //   748: aload #13
    //   750: getstatic gnu/expr/Compilation.typeModuleBody : Lgnu/bytecode/ClassType;
    //   753: ldc_w 'run'
    //   756: iconst_0
    //   757: invokevirtual getDeclaredMethod : (Ljava/lang/String;I)Lgnu/bytecode/Method;
    //   760: invokevirtual emitInvoke : (Lgnu/bytecode/Method;)V
    //   763: aload #13
    //   765: invokevirtual emitReturn : ()V
    //   768: aload_0
    //   769: getfield moduleClass : Lgnu/bytecode/ClassType;
    //   772: aload_0
    //   773: getfield mainClass : Lgnu/bytecode/ClassType;
    //   776: if_acmpeq -> 1039
    //   779: iload #4
    //   781: ifne -> 1039
    //   784: aload_0
    //   785: getfield generateMain : Z
    //   788: ifne -> 1039
    //   791: aload_0
    //   792: getfield immediate : Z
    //   795: ifne -> 1039
    //   798: aload_0
    //   799: aload_0
    //   800: getfield curClass : Lgnu/bytecode/ClassType;
    //   803: ldc_w 'run'
    //   806: iconst_1
    //   807: getstatic gnu/bytecode/Type.typeArray0 : [Lgnu/bytecode/Type;
    //   810: getstatic gnu/bytecode/Type.voidType : Lgnu/bytecode/PrimType;
    //   813: invokevirtual addMethod : (Ljava/lang/String;I[Lgnu/bytecode/Type;Lgnu/bytecode/Type;)Lgnu/bytecode/Method;
    //   816: putfield method : Lgnu/bytecode/Method;
    //   819: aload_0
    //   820: getfield method : Lgnu/bytecode/Method;
    //   823: invokevirtual startCode : ()Lgnu/bytecode/CodeAttr;
    //   826: astore #13
    //   828: aload #13
    //   830: getstatic gnu/expr/Compilation.typeCallContext : Lgnu/bytecode/ClassType;
    //   833: invokevirtual addLocal : (Lgnu/bytecode/Type;)Lgnu/bytecode/Variable;
    //   836: astore #14
    //   838: aload #13
    //   840: getstatic gnu/expr/Compilation.typeConsumer : Lgnu/bytecode/ClassType;
    //   843: invokevirtual addLocal : (Lgnu/bytecode/Type;)Lgnu/bytecode/Variable;
    //   846: astore #15
    //   848: aload #13
    //   850: getstatic gnu/bytecode/Type.javalangThrowableType : Lgnu/bytecode/ClassType;
    //   853: invokevirtual addLocal : (Lgnu/bytecode/Type;)Lgnu/bytecode/Variable;
    //   856: astore #16
    //   858: aload #13
    //   860: getstatic gnu/expr/Compilation.getCallContextInstanceMethod : Lgnu/bytecode/Method;
    //   863: invokevirtual emitInvokeStatic : (Lgnu/bytecode/Method;)V
    //   866: aload #13
    //   868: aload #14
    //   870: invokevirtual emitStore : (Lgnu/bytecode/Variable;)V
    //   873: getstatic gnu/expr/Compilation.typeCallContext : Lgnu/bytecode/ClassType;
    //   876: ldc_w 'consumer'
    //   879: invokevirtual getDeclaredField : (Ljava/lang/String;)Lgnu/bytecode/Field;
    //   882: astore #17
    //   884: aload #13
    //   886: aload #14
    //   888: invokevirtual emitLoad : (Lgnu/bytecode/Variable;)V
    //   891: aload #13
    //   893: aload #17
    //   895: invokevirtual emitGetField : (Lgnu/bytecode/Field;)V
    //   898: aload #13
    //   900: aload #15
    //   902: invokevirtual emitStore : (Lgnu/bytecode/Variable;)V
    //   905: aload #13
    //   907: aload #14
    //   909: invokevirtual emitLoad : (Lgnu/bytecode/Variable;)V
    //   912: aload #13
    //   914: ldc_w 'gnu.lists.VoidConsumer'
    //   917: invokestatic make : (Ljava/lang/String;)Lgnu/bytecode/ClassType;
    //   920: ldc_w 'instance'
    //   923: invokevirtual getDeclaredField : (Ljava/lang/String;)Lgnu/bytecode/Field;
    //   926: invokevirtual emitGetStatic : (Lgnu/bytecode/Field;)V
    //   929: aload #13
    //   931: aload #17
    //   933: invokevirtual emitPutField : (Lgnu/bytecode/Field;)V
    //   936: aload #13
    //   938: iconst_0
    //   939: getstatic gnu/bytecode/Type.voidType : Lgnu/bytecode/PrimType;
    //   942: invokevirtual emitTryStart : (ZLgnu/bytecode/Type;)V
    //   945: aload #13
    //   947: invokevirtual emitPushThis : ()V
    //   950: aload #13
    //   952: aload #14
    //   954: invokevirtual emitLoad : (Lgnu/bytecode/Variable;)V
    //   957: aload #13
    //   959: aload #11
    //   961: invokevirtual emitInvokeVirtual : (Lgnu/bytecode/Method;)V
    //   964: aload #13
    //   966: invokevirtual emitPushNull : ()V
    //   969: aload #13
    //   971: aload #16
    //   973: invokevirtual emitStore : (Lgnu/bytecode/Variable;)V
    //   976: aload #13
    //   978: invokevirtual emitTryEnd : ()V
    //   981: aload #13
    //   983: aload #16
    //   985: invokevirtual emitCatchStart : (Lgnu/bytecode/Variable;)V
    //   988: aload #13
    //   990: invokevirtual emitCatchEnd : ()V
    //   993: aload #13
    //   995: invokevirtual emitTryCatchEnd : ()V
    //   998: aload #13
    //   1000: aload #14
    //   1002: invokevirtual emitLoad : (Lgnu/bytecode/Variable;)V
    //   1005: aload #13
    //   1007: aload #16
    //   1009: invokevirtual emitLoad : (Lgnu/bytecode/Variable;)V
    //   1012: aload #13
    //   1014: aload #15
    //   1016: invokevirtual emitLoad : (Lgnu/bytecode/Variable;)V
    //   1019: aload #13
    //   1021: getstatic gnu/expr/Compilation.typeModuleBody : Lgnu/bytecode/ClassType;
    //   1024: ldc_w 'runCleanup'
    //   1027: iconst_3
    //   1028: invokevirtual getDeclaredMethod : (Ljava/lang/String;I)Lgnu/bytecode/Method;
    //   1031: invokevirtual emitInvokeStatic : (Lgnu/bytecode/Method;)V
    //   1034: aload #13
    //   1036: invokevirtual emitReturn : ()V
    //   1039: aload_0
    //   1040: aload #11
    //   1042: putfield method : Lgnu/bytecode/Method;
    //   1045: aload_0
    //   1046: aload #12
    //   1048: putfield callContextVar : Lgnu/bytecode/Variable;
    //   1051: aload #8
    //   1053: aload_0
    //   1054: invokevirtual generateApplyMethods : (Lgnu/expr/Compilation;)V
    //   1057: aload_0
    //   1058: aload #9
    //   1060: putfield curLambda : Lgnu/expr/LambdaExp;
    //   1063: aload #8
    //   1065: aload #10
    //   1067: putfield heapFrame : Lgnu/bytecode/Variable;
    //   1070: aload_0
    //   1071: invokevirtual usingCPStyle : ()Z
    //   1074: ifeq -> 1092
    //   1077: aload_0
    //   1078: invokevirtual getCode : ()Lgnu/bytecode/CodeAttr;
    //   1081: astore #8
    //   1083: aload_0
    //   1084: getfield fswitch : Lgnu/bytecode/SwitchState;
    //   1087: aload #8
    //   1089: invokevirtual finish : (Lgnu/bytecode/CodeAttr;)V
    //   1092: aload #6
    //   1094: ifnonnull -> 1104
    //   1097: aload_0
    //   1098: getfield callContextVar : Lgnu/bytecode/Variable;
    //   1101: ifnull -> 1203
    //   1104: aload_0
    //   1105: aload #7
    //   1107: putfield method : Lgnu/bytecode/Method;
    //   1110: aload_0
    //   1111: invokevirtual getCode : ()Lgnu/bytecode/CodeAttr;
    //   1114: astore #7
    //   1116: new gnu/bytecode/Label
    //   1119: dup
    //   1120: aload #7
    //   1122: invokespecial <init> : (Lgnu/bytecode/CodeAttr;)V
    //   1125: astore #8
    //   1127: aload #7
    //   1129: aload #6
    //   1131: aload #8
    //   1133: invokevirtual fixupChain : (Lgnu/bytecode/Label;Lgnu/bytecode/Label;)V
    //   1136: aload_0
    //   1137: getfield callContextVarForInit : Lgnu/bytecode/Variable;
    //   1140: ifnull -> 1160
    //   1143: aload #7
    //   1145: getstatic gnu/expr/Compilation.getCallContextInstanceMethod : Lgnu/bytecode/Method;
    //   1148: invokevirtual emitInvokeStatic : (Lgnu/bytecode/Method;)V
    //   1151: aload #7
    //   1153: aload_0
    //   1154: getfield callContextVarForInit : Lgnu/bytecode/Variable;
    //   1157: invokevirtual emitStore : (Lgnu/bytecode/Variable;)V
    //   1160: aload_0
    //   1161: getfield immediate : Z
    //   1164: ifeq -> 1820
    //   1167: aload #7
    //   1169: aload_0
    //   1170: invokestatic registerForImmediateLiterals : (Lgnu/expr/Compilation;)I
    //   1173: invokevirtual emitPushInt : (I)V
    //   1176: aload #7
    //   1178: ldc_w 'gnu.expr.Compilation'
    //   1181: invokestatic make : (Ljava/lang/String;)Lgnu/bytecode/ClassType;
    //   1184: ldc_w 'setupLiterals'
    //   1187: iconst_1
    //   1188: invokevirtual getDeclaredMethod : (Ljava/lang/String;I)Lgnu/bytecode/Method;
    //   1191: invokevirtual emitInvokeStatic : (Lgnu/bytecode/Method;)V
    //   1194: aload #7
    //   1196: aload #8
    //   1198: aload #5
    //   1200: invokevirtual fixupChain : (Lgnu/bytecode/Label;Lgnu/bytecode/Label;)V
    //   1203: aload_0
    //   1204: getfield generateMain : Z
    //   1207: ifeq -> 1375
    //   1210: aload_0
    //   1211: getfield curClass : Lgnu/bytecode/ClassType;
    //   1214: aload_0
    //   1215: getfield mainClass : Lgnu/bytecode/ClassType;
    //   1218: if_acmpne -> 1375
    //   1221: new gnu/bytecode/ArrayType
    //   1224: dup
    //   1225: getstatic gnu/expr/Compilation.javaStringType : Lgnu/bytecode/ClassType;
    //   1228: invokespecial <init> : (Lgnu/bytecode/Type;)V
    //   1231: astore #5
    //   1233: aload_0
    //   1234: getfield curClass : Lgnu/bytecode/ClassType;
    //   1237: astore #6
    //   1239: getstatic gnu/bytecode/Type.voidType : Lgnu/bytecode/PrimType;
    //   1242: astore #7
    //   1244: aload_0
    //   1245: aload #6
    //   1247: ldc_w 'main'
    //   1250: bipush #9
    //   1252: iconst_1
    //   1253: anewarray gnu/bytecode/Type
    //   1256: dup
    //   1257: iconst_0
    //   1258: aload #5
    //   1260: aastore
    //   1261: aload #7
    //   1263: invokevirtual addMethod : (Ljava/lang/String;I[Lgnu/bytecode/Type;Lgnu/bytecode/Type;)Lgnu/bytecode/Method;
    //   1266: putfield method : Lgnu/bytecode/Method;
    //   1269: aload_0
    //   1270: getfield method : Lgnu/bytecode/Method;
    //   1273: invokevirtual startCode : ()Lgnu/bytecode/CodeAttr;
    //   1276: astore #5
    //   1278: getstatic kawa/Shell.defaultFormatName : Ljava/lang/String;
    //   1281: ifnull -> 1310
    //   1284: aload #5
    //   1286: getstatic kawa/Shell.defaultFormatName : Ljava/lang/String;
    //   1289: invokevirtual emitPushString : (Ljava/lang/String;)V
    //   1292: aload #5
    //   1294: ldc_w 'kawa.Shell'
    //   1297: invokestatic make : (Ljava/lang/String;)Lgnu/bytecode/ClassType;
    //   1300: ldc_w 'setDefaultFormat'
    //   1303: iconst_1
    //   1304: invokevirtual getDeclaredMethod : (Ljava/lang/String;I)Lgnu/bytecode/Method;
    //   1307: invokevirtual emitInvokeStatic : (Lgnu/bytecode/Method;)V
    //   1310: aload #5
    //   1312: aload #5
    //   1314: iconst_0
    //   1315: invokevirtual getArg : (I)Lgnu/bytecode/Variable;
    //   1318: invokevirtual emitLoad : (Lgnu/bytecode/Variable;)V
    //   1321: aload #5
    //   1323: ldc_w 'gnu.expr.ApplicationMainSupport'
    //   1326: invokestatic make : (Ljava/lang/String;)Lgnu/bytecode/ClassType;
    //   1329: ldc_w 'processArgs'
    //   1332: iconst_1
    //   1333: invokevirtual getDeclaredMethod : (Ljava/lang/String;I)Lgnu/bytecode/Method;
    //   1336: invokevirtual emitInvokeStatic : (Lgnu/bytecode/Method;)V
    //   1339: aload_0
    //   1340: getfield moduleInstanceMainField : Lgnu/bytecode/Field;
    //   1343: ifnull -> 1862
    //   1346: aload #5
    //   1348: aload_0
    //   1349: getfield moduleInstanceMainField : Lgnu/bytecode/Field;
    //   1352: invokevirtual emitGetStatic : (Lgnu/bytecode/Field;)V
    //   1355: aload #5
    //   1357: getstatic gnu/expr/Compilation.typeModuleBody : Lgnu/bytecode/ClassType;
    //   1360: ldc_w 'runAsMain'
    //   1363: iconst_0
    //   1364: invokevirtual getDeclaredMethod : (Ljava/lang/String;I)Lgnu/bytecode/Method;
    //   1367: invokevirtual emitInvokeVirtual : (Lgnu/bytecode/Method;)V
    //   1370: aload #5
    //   1372: invokevirtual emitReturn : ()V
    //   1375: aload_0
    //   1376: getfield minfo : Lgnu/expr/ModuleInfo;
    //   1379: ifnull -> 2011
    //   1382: aload_0
    //   1383: getfield minfo : Lgnu/expr/ModuleInfo;
    //   1386: invokevirtual getNamespaceUri : ()Ljava/lang/String;
    //   1389: ifnull -> 2011
    //   1392: invokestatic getInstance : ()Lgnu/expr/ModuleManager;
    //   1395: astore #9
    //   1397: aload_0
    //   1398: getfield mainClass : Lgnu/bytecode/ClassType;
    //   1401: invokevirtual getName : ()Ljava/lang/String;
    //   1404: astore #5
    //   1406: aload #5
    //   1408: bipush #46
    //   1410: invokevirtual lastIndexOf : (I)I
    //   1413: istore_2
    //   1414: iload_2
    //   1415: ifge -> 1895
    //   1418: ldc_w ''
    //   1421: astore #5
    //   1423: new gnu/bytecode/ClassType
    //   1426: dup
    //   1427: new java/lang/StringBuilder
    //   1430: dup
    //   1431: invokespecial <init> : ()V
    //   1434: aload #5
    //   1436: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1439: ldc_w '$ModulesMap$'
    //   1442: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1445: invokevirtual toString : ()Ljava/lang/String;
    //   1448: invokespecial <init> : (Ljava/lang/String;)V
    //   1451: astore #6
    //   1453: ldc_w 'gnu.expr.ModuleSet'
    //   1456: invokestatic make : (Ljava/lang/String;)Lgnu/bytecode/ClassType;
    //   1459: astore #7
    //   1461: aload #6
    //   1463: aload #7
    //   1465: invokevirtual setSuper : (Lgnu/bytecode/ClassType;)V
    //   1468: aload_0
    //   1469: aload #6
    //   1471: invokespecial registerClass : (Lgnu/bytecode/ClassType;)V
    //   1474: aload_0
    //   1475: aload #6
    //   1477: ldc_w '<init>'
    //   1480: iconst_1
    //   1481: getstatic gnu/expr/Compilation.apply0args : [Lgnu/bytecode/Type;
    //   1484: getstatic gnu/bytecode/Type.voidType : Lgnu/bytecode/PrimType;
    //   1487: invokevirtual addMethod : (Ljava/lang/String;I[Lgnu/bytecode/Type;Lgnu/bytecode/Type;)Lgnu/bytecode/Method;
    //   1490: putfield method : Lgnu/bytecode/Method;
    //   1493: aload #7
    //   1495: ldc_w '<init>'
    //   1498: iconst_1
    //   1499: getstatic gnu/expr/Compilation.apply0args : [Lgnu/bytecode/Type;
    //   1502: getstatic gnu/bytecode/Type.voidType : Lgnu/bytecode/PrimType;
    //   1505: invokevirtual addMethod : (Ljava/lang/String;I[Lgnu/bytecode/Type;Lgnu/bytecode/Type;)Lgnu/bytecode/Method;
    //   1508: astore #7
    //   1510: aload_0
    //   1511: getfield method : Lgnu/bytecode/Method;
    //   1514: invokevirtual startCode : ()Lgnu/bytecode/CodeAttr;
    //   1517: astore #8
    //   1519: aload #8
    //   1521: invokevirtual emitPushThis : ()V
    //   1524: aload #8
    //   1526: aload #7
    //   1528: invokevirtual emitInvokeSpecial : (Lgnu/bytecode/Method;)V
    //   1531: aload #8
    //   1533: invokevirtual emitReturn : ()V
    //   1536: ldc_w 'gnu.expr.ModuleManager'
    //   1539: invokestatic make : (Ljava/lang/String;)Lgnu/bytecode/ClassType;
    //   1542: astore #7
    //   1544: getstatic gnu/bytecode/Type.voidType : Lgnu/bytecode/PrimType;
    //   1547: astore #8
    //   1549: aload_0
    //   1550: aload #6
    //   1552: ldc_w 'register'
    //   1555: iconst_1
    //   1556: anewarray gnu/bytecode/Type
    //   1559: dup
    //   1560: iconst_0
    //   1561: aload #7
    //   1563: aastore
    //   1564: aload #8
    //   1566: iconst_1
    //   1567: invokevirtual addMethod : (Ljava/lang/String;[Lgnu/bytecode/Type;Lgnu/bytecode/Type;I)Lgnu/bytecode/Method;
    //   1570: putfield method : Lgnu/bytecode/Method;
    //   1573: aload_0
    //   1574: getfield method : Lgnu/bytecode/Method;
    //   1577: invokevirtual startCode : ()Lgnu/bytecode/CodeAttr;
    //   1580: astore #10
    //   1582: aload #7
    //   1584: ldc_w 'register'
    //   1587: iconst_3
    //   1588: invokevirtual getDeclaredMethod : (Ljava/lang/String;I)Lgnu/bytecode/Method;
    //   1591: astore #11
    //   1593: aload #9
    //   1595: getfield numModules : I
    //   1598: istore_2
    //   1599: iload_2
    //   1600: iconst_1
    //   1601: isub
    //   1602: istore_3
    //   1603: iload_3
    //   1604: iflt -> 2006
    //   1607: aload #9
    //   1609: getfield modules : [Lgnu/expr/ModuleInfo;
    //   1612: iload_3
    //   1613: aaload
    //   1614: astore #13
    //   1616: aload #13
    //   1618: invokevirtual getClassName : ()Ljava/lang/String;
    //   1621: astore #6
    //   1623: iload_3
    //   1624: istore_2
    //   1625: aload #6
    //   1627: ifnull -> 1599
    //   1630: iload_3
    //   1631: istore_2
    //   1632: aload #6
    //   1634: aload #5
    //   1636: invokevirtual startsWith : (Ljava/lang/String;)Z
    //   1639: ifeq -> 1599
    //   1642: aload #13
    //   1644: getfield sourcePath : Ljava/lang/String;
    //   1647: astore #7
    //   1649: aload #13
    //   1651: invokevirtual getNamespaceUri : ()Ljava/lang/String;
    //   1654: astore #12
    //   1656: aload #10
    //   1658: aload #10
    //   1660: iconst_1
    //   1661: invokevirtual getArg : (I)Lgnu/bytecode/Variable;
    //   1664: invokevirtual emitLoad : (Lgnu/bytecode/Variable;)V
    //   1667: aload_0
    //   1668: aload #6
    //   1670: invokevirtual compileConstant : (Ljava/lang/Object;)V
    //   1673: aload #7
    //   1675: astore #6
    //   1677: aload #7
    //   1679: invokestatic valueOf : (Ljava/lang/Object;)Lgnu/text/Path;
    //   1682: invokevirtual isAbsolute : ()Z
    //   1685: ifne -> 1796
    //   1688: getstatic java/io/File.separatorChar : C
    //   1691: istore_1
    //   1692: aload #9
    //   1694: invokevirtual getCompilationDirectory : ()Ljava/lang/String;
    //   1697: astore #6
    //   1699: new java/lang/StringBuilder
    //   1702: dup
    //   1703: invokespecial <init> : ()V
    //   1706: aload #6
    //   1708: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1711: aload #5
    //   1713: bipush #46
    //   1715: iload_1
    //   1716: invokevirtual replace : (CC)Ljava/lang/String;
    //   1719: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1722: invokevirtual toString : ()Ljava/lang/String;
    //   1725: invokestatic toURL : (Ljava/lang/String;)Ljava/net/URL;
    //   1728: invokevirtual toString : ()Ljava/lang/String;
    //   1731: astore #8
    //   1733: aload #8
    //   1735: invokevirtual length : ()I
    //   1738: istore_2
    //   1739: aload #8
    //   1741: astore #6
    //   1743: iload_2
    //   1744: ifle -> 1784
    //   1747: aload #8
    //   1749: astore #6
    //   1751: aload #8
    //   1753: iload_2
    //   1754: iconst_1
    //   1755: isub
    //   1756: invokevirtual charAt : (I)C
    //   1759: iload_1
    //   1760: if_icmpeq -> 1784
    //   1763: new java/lang/StringBuilder
    //   1766: dup
    //   1767: invokespecial <init> : ()V
    //   1770: aload #8
    //   1772: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1775: iload_1
    //   1776: invokevirtual append : (C)Ljava/lang/StringBuilder;
    //   1779: invokevirtual toString : ()Ljava/lang/String;
    //   1782: astore #6
    //   1784: aload #13
    //   1786: invokevirtual getSourceAbsPathname : ()Ljava/lang/String;
    //   1789: aload #6
    //   1791: invokestatic relativize : (Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
    //   1794: astore #6
    //   1796: aload_0
    //   1797: aload #6
    //   1799: invokevirtual compileConstant : (Ljava/lang/Object;)V
    //   1802: aload_0
    //   1803: aload #12
    //   1805: invokevirtual compileConstant : (Ljava/lang/Object;)V
    //   1808: aload #10
    //   1810: aload #11
    //   1812: invokevirtual emitInvokeVirtual : (Lgnu/bytecode/Method;)V
    //   1815: iload_3
    //   1816: istore_2
    //   1817: goto -> 1599
    //   1820: aload_0
    //   1821: getfield litTable : Lgnu/expr/LitTable;
    //   1824: invokevirtual emit : ()V
    //   1827: goto -> 1194
    //   1830: astore #6
    //   1832: aload_0
    //   1833: bipush #101
    //   1835: new java/lang/StringBuilder
    //   1838: dup
    //   1839: invokespecial <init> : ()V
    //   1842: ldc_w 'Literals: Internal error:'
    //   1845: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1848: aload #6
    //   1850: invokevirtual append : (Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   1853: invokevirtual toString : ()Ljava/lang/String;
    //   1856: invokevirtual error : (CLjava/lang/String;)V
    //   1859: goto -> 1194
    //   1862: aload #5
    //   1864: aload_0
    //   1865: getfield curClass : Lgnu/bytecode/ClassType;
    //   1868: invokevirtual emitNew : (Lgnu/bytecode/ClassType;)V
    //   1871: aload #5
    //   1873: aload_0
    //   1874: getfield curClass : Lgnu/bytecode/ClassType;
    //   1877: invokevirtual emitDup : (Lgnu/bytecode/Type;)V
    //   1880: aload #5
    //   1882: aload_0
    //   1883: getfield curClass : Lgnu/bytecode/ClassType;
    //   1886: getfield constructor : Lgnu/bytecode/Method;
    //   1889: invokevirtual emitInvokeSpecial : (Lgnu/bytecode/Method;)V
    //   1892: goto -> 1355
    //   1895: aload #5
    //   1897: iconst_0
    //   1898: iload_2
    //   1899: invokevirtual substring : (II)Ljava/lang/String;
    //   1902: astore #6
    //   1904: aload #9
    //   1906: aload #6
    //   1908: invokevirtual loadPackageInfo : (Ljava/lang/String;)V
    //   1911: aload #5
    //   1913: iconst_0
    //   1914: iload_2
    //   1915: iconst_1
    //   1916: iadd
    //   1917: invokevirtual substring : (II)Ljava/lang/String;
    //   1920: astore #5
    //   1922: goto -> 1423
    //   1925: astore #7
    //   1927: aload_0
    //   1928: bipush #101
    //   1930: new java/lang/StringBuilder
    //   1933: dup
    //   1934: invokespecial <init> : ()V
    //   1937: ldc_w 'error loading map for '
    //   1940: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1943: aload #6
    //   1945: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1948: ldc_w ' - '
    //   1951: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1954: aload #7
    //   1956: invokevirtual append : (Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   1959: invokevirtual toString : ()Ljava/lang/String;
    //   1962: invokevirtual error : (CLjava/lang/String;)V
    //   1965: goto -> 1911
    //   1968: astore #5
    //   1970: new gnu/mapping/WrappedException
    //   1973: dup
    //   1974: new java/lang/StringBuilder
    //   1977: dup
    //   1978: invokespecial <init> : ()V
    //   1981: ldc_w 'exception while fixing up ''
    //   1984: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1987: aload #7
    //   1989: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1992: bipush #39
    //   1994: invokevirtual append : (C)Ljava/lang/StringBuilder;
    //   1997: invokevirtual toString : ()Ljava/lang/String;
    //   2000: aload #5
    //   2002: invokespecial <init> : (Ljava/lang/String;Ljava/lang/Throwable;)V
    //   2005: athrow
    //   2006: aload #10
    //   2008: invokevirtual emitReturn : ()V
    //   2011: return
    //   2012: astore #6
    //   2014: goto -> 1911
    // Exception table:
    //   from	to	target	type
    //   1160	1194	1830	java/lang/Throwable
    //   1688	1739	1968	java/lang/Throwable
    //   1751	1784	1968	java/lang/Throwable
    //   1784	1796	1968	java/lang/Throwable
    //   1820	1827	1830	java/lang/Throwable
    //   1904	1911	2012	java/lang/ClassNotFoundException
    //   1904	1911	1925	java/lang/Throwable
  }
  
  public String generateClassName(String paramString) {
    String str = mangleName(paramString, true);
    if (this.mainClass != null) {
      paramString = this.mainClass.getName() + '$' + str;
    } else {
      paramString = str;
      if (this.classPrefix != null)
        paramString = this.classPrefix + str; 
    } 
    if (findNamedClass(paramString) == null)
      return paramString; 
    for (int i = 0;; i++) {
      str = paramString + i;
      if (findNamedClass(str) == null)
        return str; 
    } 
  }
  
  public final void generateConstructor(ClassType paramClassType, LambdaExp paramLambdaExp) {
    Method method1 = this.method;
    Variable variable = this.callContextVar;
    this.callContextVar = null;
    ClassType classType = this.curClass;
    this.curClass = paramClassType;
    Method method2 = getConstructor(paramClassType, paramLambdaExp);
    paramClassType.constructor = method2;
    this.method = method2;
    CodeAttr codeAttr = method2.startCode();
    if (paramLambdaExp instanceof ClassExp && paramLambdaExp.staticLinkField != null) {
      codeAttr.emitPushThis();
      codeAttr.emitLoad(codeAttr.getCurrentScope().getVariable(1));
      codeAttr.emitPutField(paramLambdaExp.staticLinkField);
    } 
    ClassExp.invokeDefaultSuperConstructor(paramClassType.getSuperclass(), this, paramLambdaExp);
    if (this.curClass == this.mainClass && this.minfo != null && this.minfo.sourcePath != null) {
      codeAttr.emitPushThis();
      codeAttr.emitInvokeStatic(ClassType.make("gnu.expr.ModuleInfo").getDeclaredMethod("register", 1));
    } 
    if (paramLambdaExp != null && paramLambdaExp.initChain != null) {
      LambdaExp lambdaExp = this.curLambda;
      this.curLambda = new LambdaExp();
      this.curLambda.closureEnv = codeAttr.getArg(0);
      this.curLambda.outer = lambdaExp;
      while (true) {
        Initializer initializer = paramLambdaExp.initChain;
        if (initializer != null) {
          paramLambdaExp.initChain = null;
          dumpInitializers(initializer);
          continue;
        } 
        this.curLambda = lambdaExp;
        break;
      } 
    } 
    if (paramLambdaExp instanceof ClassExp)
      callInitMethods(((ClassExp)paramLambdaExp).getCompiledClassType(this), new Vector<ClassType>(10)); 
    codeAttr.emitReturn();
    this.method = method1;
    this.curClass = classType;
    this.callContextVar = variable;
  }
  
  public final void generateConstructor(LambdaExp paramLambdaExp) {
    generateConstructor(paramLambdaExp.getHeapFrameType(), paramLambdaExp);
  }
  
  public void generateMatchMethods(LambdaExp paramLambdaExp) {
    int i;
    if (paramLambdaExp.applyMethods == null) {
      i = 0;
    } else {
      i = paramLambdaExp.applyMethods.size();
    } 
    if (i == 0)
      return; 
    Method method = this.method;
    ClassType classType1 = this.curClass;
    ClassType classType2 = typeModuleMethod;
    this.curClass = paramLambdaExp.getHeapFrameType();
    if (!this.curClass.getSuperclass().isSubtype((Type)typeModuleBody))
      this.curClass = this.moduleClass; 
    CodeAttr codeAttr = null;
    for (int j = 0; j <= 5; j++) {
      Object object;
      boolean bool = false;
      Object object3 = null;
      Object object2 = null;
      Object object1 = null;
      int k = i;
      while (true) {
        int m = object - 1;
        codeAttr.emitPutField(pcCallContextField);
        codeAttr.emitPushInt(0);
        codeAttr.emitReturn();
        object = SYNTHETIC_LOCAL_VARIABLE_7;
        object4 = SYNTHETIC_LOCAL_VARIABLE_6;
      } 
      continue;
    } 
    this.method = method;
    this.curClass = classType1;
  }
  
  public boolean generatingApplet() {
    return ((this.langOptions & 0x10) != 0);
  }
  
  public boolean generatingServlet() {
    return ((this.langOptions & 0x20) != 0);
  }
  
  public final boolean getBooleanOption(String paramString) {
    return this.currentOptions.getBoolean(paramString);
  }
  
  public final boolean getBooleanOption(String paramString, boolean paramBoolean) {
    return this.currentOptions.getBoolean(paramString, paramBoolean);
  }
  
  public final CodeAttr getCode() {
    return this.method.getCode();
  }
  
  public final int getColumnNumber() {
    return this.messages.getColumnNumber();
  }
  
  public final Method getConstructor(LambdaExp paramLambdaExp) {
    return getConstructor(paramLambdaExp.getHeapFrameType(), paramLambdaExp);
  }
  
  public final String getFileName() {
    return this.messages.getFileName();
  }
  
  public Method getForNameHelper() {
    if (this.forNameHelper == null) {
      Method method = this.method;
      this.method = this.curClass.addMethod("class$", 9, string1Arg, (Type)typeClass);
      this.forNameHelper = this.method;
      CodeAttr codeAttr = this.method.startCode();
      codeAttr.emitLoad(codeAttr.getArg(0));
      codeAttr.emitPushInt(0);
      codeAttr.emitPushString(this.mainClass.getName());
      codeAttr.emitInvokeStatic(typeClass.getDeclaredMethod("forName", 1));
      codeAttr.emitInvokeVirtual(typeClass.getDeclaredMethod("getClassLoader", 0));
      codeAttr.emitInvokeStatic(typeClass.getDeclaredMethod("forName", 3));
      codeAttr.emitReturn();
      this.method = method;
    } 
    return this.forNameHelper;
  }
  
  public Language getLanguage() {
    return this.language;
  }
  
  public final int getLineNumber() {
    return this.messages.getLineNumber();
  }
  
  public SourceMessages getMessages() {
    return this.messages;
  }
  
  public final ModuleExp getModule() {
    return this.mainLambda;
  }
  
  public final ClassType getModuleType() {
    return (defaultCallConvention >= 2) ? typeModuleWithContext : typeModuleBody;
  }
  
  public String getPublicId() {
    return this.messages.getPublicId();
  }
  
  public int getState() {
    return this.state;
  }
  
  public String getSystemId() {
    return this.messages.getSystemId();
  }
  
  public boolean inlineOk(Expression paramExpression) {
    if (paramExpression instanceof LambdaExp) {
      paramExpression = paramExpression;
      Declaration declaration = ((LambdaExp)paramExpression).nameDecl;
      if (declaration == null || declaration.getSymbol() == null || !(declaration.context instanceof ModuleExp))
        return true; 
      if (this.immediate && declaration.isPublic() && !paramExpression.getFlag(2048) && (this.curLambda == null || paramExpression.topLevel() != this.curLambda.topLevel()))
        return false; 
    } 
    return inlineOk;
  }
  
  public boolean inlineOk(Procedure paramProcedure) {
    return (this.immediate && paramProcedure instanceof ModuleMethod && ((ModuleMethod)paramProcedure).module.getClass().getClassLoader() instanceof ArrayClassLoader) ? false : inlineOk;
  }
  
  public boolean isPedantic() {
    return this.pedantic;
  }
  
  public boolean isStableSourceLocation() {
    return false;
  }
  
  public boolean isStatic() {
    return this.mainLambda.isStatic();
  }
  
  public LetExp letDone(Expression paramExpression) {
    LetExp letExp = (LetExp)this.current_scope;
    letExp.body = paramExpression;
    pop(letExp);
    return letExp;
  }
  
  public void letEnter() {
    LetExp letExp = (LetExp)this.current_scope;
    Expression[] arrayOfExpression = new Expression[letExp.countDecls()];
    Declaration declaration = letExp.firstDecl();
    for (int i = 0; declaration != null; i++) {
      arrayOfExpression[i] = declaration.getValue();
      declaration = declaration.nextDecl();
    } 
    letExp.inits = arrayOfExpression;
    this.lexical.push(letExp);
  }
  
  public void letStart() {
    pushScope(new LetExp(null));
  }
  
  public Declaration letVariable(Object paramObject, Type paramType, Expression paramExpression) {
    paramObject = ((LetExp)this.current_scope).addDeclaration(paramObject, paramType);
    paramObject.noteValue(paramExpression);
    return (Declaration)paramObject;
  }
  
  public final void loadCallContext() {
    CodeAttr codeAttr = getCode();
    if (this.callContextVar != null && !this.callContextVar.dead()) {
      codeAttr.emitLoad(this.callContextVar);
      return;
    } 
    if (this.method == this.clinitMethod) {
      this.callContextVar = new Variable("$ctx", (Type)typeCallContext);
      this.callContextVar.reserveLocal(codeAttr.getMaxLocals(), codeAttr);
      codeAttr.emitLoad(this.callContextVar);
      this.callContextVarForInit = this.callContextVar;
      return;
    } 
    codeAttr.emitInvokeStatic(getCallContextInstanceMethod);
    codeAttr.emitDup();
    this.callContextVar = new Variable("$ctx", (Type)typeCallContext);
    codeAttr.getCurrentScope().addVariable(codeAttr, this.callContextVar);
    codeAttr.emitStore(this.callContextVar);
  }
  
  public void loadClassRef(ObjectType paramObjectType) {
    String str;
    CodeAttr codeAttr = getCode();
    if (this.curClass.getClassfileVersion() >= 3211264) {
      codeAttr.emitPushClass(paramObjectType);
      return;
    } 
    if (paramObjectType == this.mainClass && this.mainLambda.isStatic() && this.moduleInstanceMainField != null) {
      codeAttr.emitGetStatic(this.moduleInstanceMainField);
      codeAttr.emitInvokeVirtual(Type.objectType.getDeclaredMethod("getClass", 0));
      return;
    } 
    if (paramObjectType instanceof ClassType) {
      str = paramObjectType.getName();
    } else {
      str = str.getInternalName().replace('/', '.');
    } 
    codeAttr.emitPushString(str);
    codeAttr.emitInvokeStatic(getForNameHelper());
  }
  
  public Declaration lookup(Object paramObject, int paramInt) {
    return this.lexical.lookup(paramObject, paramInt);
  }
  
  public void loopBody(Expression paramExpression) {
    ((LambdaExp)this.current_scope).body = paramExpression;
  }
  
  public void loopCond(Expression paramExpression) {
    checkLoop();
    this.exprStack.push(paramExpression);
  }
  
  public void loopEnter() {
    checkLoop();
    LambdaExp lambdaExp = (LambdaExp)this.current_scope;
    int i = lambdaExp.min_args;
    lambdaExp.max_args = i;
    Expression[] arrayOfExpression = new Expression[i];
    while (true) {
      if (--i >= 0) {
        arrayOfExpression[i] = this.exprStack.pop();
        continue;
      } 
      LetExp letExp = (LetExp)lambdaExp.outer;
      letExp.setBody(new ApplyExp(new ReferenceExp(letExp.firstDecl()), arrayOfExpression));
      this.lexical.push(lambdaExp);
      return;
    } 
  }
  
  public Expression loopRepeat() {
    return loopRepeat(Expression.noExpressions);
  }
  
  public Expression loopRepeat(Expression paramExpression) {
    return loopRepeat(new Expression[] { paramExpression });
  }
  
  public Expression loopRepeat(Expression[] paramArrayOfExpression) {
    LambdaExp lambdaExp = (LambdaExp)this.current_scope;
    ScopeExp scopeExp = lambdaExp.outer;
    Declaration declaration = scopeExp.firstDecl();
    Expression expression = this.exprStack.pop();
    ApplyExp applyExp = new ApplyExp(new ReferenceExp(declaration), paramArrayOfExpression);
    lambdaExp.body = new IfExp(expression, new BeginExp(lambdaExp.body, applyExp), QuoteExp.voidExp);
    this.lexical.pop(lambdaExp);
    this.current_scope = scopeExp.outer;
    return scopeExp;
  }
  
  public void loopStart() {
    LambdaExp lambdaExp = new LambdaExp();
    LetExp letExp = new LetExp(new Expression[] { lambdaExp });
    letExp.addDeclaration("%do%loop").noteValue(lambdaExp);
    lambdaExp.setName("%do%loop");
    letExp.outer = this.current_scope;
    lambdaExp.outer = letExp;
    this.current_scope = lambdaExp;
  }
  
  public Declaration loopVariable(Object paramObject, Type paramType, Expression paramExpression) {
    checkLoop();
    LambdaExp lambdaExp = (LambdaExp)this.current_scope;
    paramObject = lambdaExp.addDeclaration(paramObject, paramType);
    if (this.exprStack == null)
      this.exprStack = new Stack<Expression>(); 
    this.exprStack.push(paramExpression);
    lambdaExp.min_args++;
    return (Declaration)paramObject;
  }
  
  public boolean makeRunnable() {
    return (!generatingServlet() && !generatingApplet() && !getModule().staticInitRun());
  }
  
  public void mustCompileHere() {
    if (!this.mustCompile && !ModuleExp.compilerAvailable) {
      error('w', "this expression claimed that it must be compiled, but compiler is unavailable");
      return;
    } 
    this.mustCompile = true;
  }
  
  public void outputClass(String paramString) throws IOException {
    char c = File.separatorChar;
    for (int i = 0; i < this.numClasses; i++) {
      ClassType classType = this.classes[i];
      String str1 = paramString + classType.getName().replace('.', c) + ".class";
      String str2 = (new File(str1)).getParent();
      if (str2 != null)
        (new File(str2)).mkdirs(); 
      classType.writeToFile(str1);
    } 
    this.minfo.cleanupAfterCompilation();
  }
  
  public Expression parse(Object paramObject) {
    throw new Error("unimeplemented parse");
  }
  
  public final void pop() {
    pop(this.current_scope);
  }
  
  public void pop(ScopeExp paramScopeExp) {
    this.lexical.pop(paramScopeExp);
    this.current_scope = paramScopeExp.outer;
  }
  
  public void process(int paramInt) {
    byte b2 = 10;
    byte b3 = 8;
    byte b4 = 6;
    byte b1 = 100;
    Compilation compilation = setSaveCurrent(this);
    try {
      ModuleExp moduleExp = getModule();
      if (paramInt >= 4 && getState() < 3) {
        byte b;
        setState(3);
        this.language.parse(this, 0);
        this.lexer.close();
        this.lexer = null;
        if (this.messages.seenErrors()) {
          b = 100;
        } else {
          b = 4;
        } 
        setState(b);
        Stack<Object> stack = this.pendingImports;
        if (stack != null)
          return; 
      } 
      if (paramInt >= 6 && getState() < 6) {
        addMainClass(moduleExp);
        this.language.resolve(this);
        byte b = b4;
        if (this.messages.seenErrors())
          b = 100; 
        setState(b);
      } 
      if (!this.explicit && !this.immediate && this.minfo.checkCurrent(ModuleManager.getInstance(), System.currentTimeMillis())) {
        this.minfo.cleanupAfterCompilation();
        setState(14);
      } 
      if (paramInt >= 8 && getState() < 8) {
        walkModule(moduleExp);
        byte b = b3;
        if (this.messages.seenErrors())
          b = 100; 
        setState(b);
      } 
      if (paramInt >= 10 && getState() < 10) {
        this.litTable = new LitTable(this);
        moduleExp.setCanRead(true);
        FindCapturedVars.findCapturedVars(moduleExp, this);
        moduleExp.allocFields(this);
        moduleExp.allocChildMethods(this);
        byte b = b2;
        if (this.messages.seenErrors())
          b = 100; 
        setState(b);
      } 
      if (paramInt >= 12 && getState() < 12) {
        byte b;
        if (this.immediate)
          this.loader = new ArrayClassLoader(ObjectType.getContextClassLoader()); 
        generateBytecode();
        if (this.messages.seenErrors()) {
          b = b1;
        } else {
          b = 12;
        } 
        setState(b);
      } 
      if (paramInt >= 14 && getState() < 14) {
        outputClass(ModuleManager.getInstance().getCompilationDirectory());
        setState(14);
      } 
      return;
    } catch (SyntaxException syntaxException) {
      setState(100);
      if (syntaxException.getMessages() != getMessages())
        throw new RuntimeException("confussing syntax error: " + syntaxException); 
      return;
    } catch (IOException iOException) {
      iOException.printStackTrace();
      error('f', "caught " + iOException);
      setState(100);
      return;
    } finally {
      restoreCurrent(compilation);
    } 
  }
  
  public void push(Declaration paramDeclaration) {
    this.lexical.push(paramDeclaration);
  }
  
  public void push(ScopeExp paramScopeExp) {
    pushScope(paramScopeExp);
    this.lexical.push(paramScopeExp);
  }
  
  void pushChain(ScopeExp paramScopeExp1, ScopeExp paramScopeExp2) {
    if (paramScopeExp1 != paramScopeExp2) {
      pushChain(paramScopeExp1.outer, paramScopeExp2);
      pushScope(paramScopeExp1);
      this.lexical.push(paramScopeExp1);
    } 
  }
  
  public ModuleExp pushNewModule(Lexer paramLexer) {
    this.lexer = paramLexer;
    return pushNewModule(paramLexer.getName());
  }
  
  public ModuleExp pushNewModule(String paramString) {
    ModuleExp moduleExp = new ModuleExp();
    if (paramString != null)
      moduleExp.setFile(paramString); 
    if (generatingApplet() || generatingServlet())
      moduleExp.setFlag(131072); 
    if (this.immediate) {
      moduleExp.setFlag(1048576);
      (new ModuleInfo()).setCompilation(this);
    } 
    this.mainLambda = moduleExp;
    push(moduleExp);
    return moduleExp;
  }
  
  public void pushPendingImport(ModuleInfo paramModuleInfo, ScopeExp paramScopeExp, int paramInt) {
    if (this.pendingImports == null)
      this.pendingImports = new Stack(); 
    this.pendingImports.push(paramModuleInfo);
    this.pendingImports.push(paramScopeExp);
    ReferenceExp referenceExp = new ReferenceExp(null);
    referenceExp.setLine(this);
    this.pendingImports.push(referenceExp);
    this.pendingImports.push(Integer.valueOf(paramInt));
  }
  
  public final void pushScope(ScopeExp paramScopeExp) {
    if (!this.mustCompile && (paramScopeExp.mustCompile() || (ModuleExp.compilerAvailable && paramScopeExp instanceof LambdaExp && !(paramScopeExp instanceof ModuleExp))))
      mustCompileHere(); 
    paramScopeExp.outer = this.current_scope;
    this.current_scope = paramScopeExp;
  }
  
  public Object resolve(Object paramObject, boolean paramBoolean) {
    Environment environment = Environment.getCurrent();
    if (paramObject instanceof String) {
      paramObject = environment.defaultNamespace().lookup((String)paramObject);
    } else {
      paramObject = paramObject;
    } 
    return (paramObject == null) ? null : ((paramBoolean && getLanguage().hasSeparateFunctionNamespace()) ? environment.getFunction((Symbol)paramObject, null) : environment.get((EnvironmentKey)paramObject, null));
  }
  
  public void setColumn(int paramInt) {
    this.messages.setColumn(paramInt);
  }
  
  public void setCurrentScope(ScopeExp paramScopeExp) {
    ScopeExp scopeExp2;
    int j = ScopeExp.nesting(paramScopeExp);
    int i;
    for (i = ScopeExp.nesting(this.current_scope); i > j; i--)
      pop(this.current_scope); 
    ScopeExp scopeExp1 = paramScopeExp;
    while (true) {
      scopeExp2 = scopeExp1;
      if (j > i) {
        scopeExp1 = scopeExp1.outer;
        j--;
        continue;
      } 
      break;
    } 
    while (scopeExp2 != this.current_scope) {
      pop(this.current_scope);
      scopeExp2 = scopeExp2.outer;
    } 
    pushChain(paramScopeExp, scopeExp2);
  }
  
  public void setFile(String paramString) {
    this.messages.setFile(paramString);
  }
  
  public void setLine(int paramInt) {
    this.messages.setLine(paramInt);
  }
  
  public final void setLine(Expression paramExpression) {
    this.messages.setLocation(paramExpression);
  }
  
  public void setLine(Object paramObject) {
    if (paramObject instanceof SourceLocator)
      this.messages.setLocation((SourceLocator)paramObject); 
  }
  
  public void setLine(String paramString, int paramInt1, int paramInt2) {
    this.messages.setLine(paramString, paramInt1, paramInt2);
  }
  
  public final void setLocation(SourceLocator paramSourceLocator) {
    this.messages.setLocation(paramSourceLocator);
  }
  
  public void setMessages(SourceMessages paramSourceMessages) {
    this.messages = paramSourceMessages;
  }
  
  public void setModule(ModuleExp paramModuleExp) {
    this.mainLambda = paramModuleExp;
  }
  
  public void setSharedModuleDefs(boolean paramBoolean) {
    if (paramBoolean) {
      this.langOptions |= 0x2;
      return;
    } 
    this.langOptions &= 0xFFFFFFFD;
  }
  
  public void setState(int paramInt) {
    this.state = paramInt;
  }
  
  public boolean sharedModuleDefs() {
    return ((this.langOptions & 0x2) != 0);
  }
  
  public Expression syntaxError(String paramString) {
    error('e', paramString);
    return new ErrorExp(paramString);
  }
  
  public String toString() {
    return "<compilation " + this.mainLambda + ">";
  }
  
  public void usedClass(Type paramType) {
    while (paramType instanceof ArrayType)
      paramType = ((ArrayType)paramType).getComponentType(); 
    if (this.immediate && paramType instanceof ClassType)
      this.loader.addClass((ClassType)paramType); 
  }
  
  public boolean usingCPStyle() {
    return (defaultCallConvention == 4);
  }
  
  public boolean usingTailCalls() {
    return (defaultCallConvention >= 3);
  }
  
  public void walkModule(ModuleExp paramModuleExp) {
    if (debugPrintExpr) {
      OutPort outPort = OutPort.errDefault();
      outPort.println("[Module:" + paramModuleExp.getName());
      paramModuleExp.print(outPort);
      outPort.println(']');
      outPort.flush();
    } 
    InlineCalls.inlineCalls(paramModuleExp, this);
    PushApply.pushApply(paramModuleExp);
    ChainLambdas.chainLambdas(paramModuleExp, this);
    FindTailCalls.findTailCalls(paramModuleExp, this);
  }
  
  public boolean warnAsError() {
    return this.currentOptions.getBoolean(warnAsError);
  }
  
  public boolean warnInvokeUnknownMethod() {
    return this.currentOptions.getBoolean(warnInvokeUnknownMethod);
  }
  
  public boolean warnUndefinedVariable() {
    return this.currentOptions.getBoolean(warnUndefinedVariable);
  }
  
  public boolean warnUnknownMember() {
    return this.currentOptions.getBoolean(warnUnknownMember);
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/gnu/expr/Compilation.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */