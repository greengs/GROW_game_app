package kawa.standard;

import gnu.bytecode.ArrayType;
import gnu.bytecode.ClassType;
import gnu.bytecode.Type;
import gnu.expr.ApplyExp;
import gnu.expr.Compilation;
import gnu.expr.Declaration;
import gnu.expr.Expression;
import gnu.expr.Language;
import gnu.expr.ReferenceExp;
import gnu.kawa.functions.Apply;
import gnu.kawa.functions.ApplyToArgs;
import gnu.kawa.functions.DisplayFormat;
import gnu.kawa.functions.IsEq;
import gnu.kawa.functions.IsEqual;
import gnu.kawa.functions.IsEqv;
import gnu.kawa.functions.Map;
import gnu.kawa.functions.Not;
import gnu.kawa.functions.NumberCompare;
import gnu.kawa.functions.NumberPredicate;
import gnu.kawa.lispexpr.LangObjType;
import gnu.kawa.lispexpr.LangPrimType;
import gnu.kawa.lispexpr.LispLanguage;
import gnu.kawa.lispexpr.LispReader;
import gnu.kawa.lispexpr.ReadTable;
import gnu.kawa.lispexpr.ReaderDispatch;
import gnu.kawa.lispexpr.ReaderDispatchMisc;
import gnu.kawa.lispexpr.ReaderParens;
import gnu.kawa.lispexpr.ReaderQuote;
import gnu.kawa.reflect.InstanceOf;
import gnu.kawa.servlet.HttpRequestContext;
import gnu.lists.AbstractFormat;
import gnu.mapping.CharArrayInPort;
import gnu.mapping.Environment;
import gnu.mapping.InPort;
import gnu.mapping.Location;
import gnu.mapping.Namespace;
import gnu.mapping.SimpleEnvironment;
import gnu.mapping.Symbol;
import gnu.mapping.WrappedException;
import gnu.text.SourceMessages;
import gnu.text.SyntaxException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import kawa.lang.Eval;
import kawa.lang.Translator;

public class Scheme extends LispLanguage {
  public static final Apply apply;
  
  static final Declaration applyFieldDecl;
  
  public static final ApplyToArgs applyToArgs;
  
  public static LangPrimType booleanType;
  
  public static final AbstractFormat displayFormat;
  
  public static final Map forEach;
  
  public static final Scheme instance;
  
  public static final InstanceOf instanceOf;
  
  public static final IsEq isEq;
  
  public static final IsEqual isEqual;
  
  public static final IsEqv isEqv;
  
  public static final NumberPredicate isEven;
  
  public static final NumberPredicate isOdd;
  
  protected static final SimpleEnvironment kawaEnvironment;
  
  public static final Map map;
  
  public static final Not not;
  
  public static final Environment nullEnvironment = (Environment)Environment.make("null-environment");
  
  public static final NumberCompare numEqu;
  
  public static final NumberCompare numGEq;
  
  public static final NumberCompare numGrt;
  
  public static final NumberCompare numLEq;
  
  public static final NumberCompare numLss;
  
  public static final Environment r4Environment = (Environment)Environment.make("r4rs-environment", nullEnvironment);
  
  public static final Environment r5Environment = (Environment)Environment.make("r5rs-environment", r4Environment);
  
  static HashMap<Type, String> typeToStringMap;
  
  static HashMap<String, Type> types;
  
  public static final Namespace unitNamespace;
  
  public static final AbstractFormat writeFormat;
  
  static {
    kawaEnvironment = (SimpleEnvironment)Environment.make("kawa-environment", r5Environment);
    instance = new Scheme((Environment)kawaEnvironment);
    instanceOf = new InstanceOf((Language)instance, "instance?");
    not = new Not((Language)instance, "not");
    applyToArgs = new ApplyToArgs("apply-to-args", (Language)instance);
    applyFieldDecl = Declaration.getDeclarationFromStatic("kawa.standard.Scheme", "applyToArgs");
    apply = new Apply("apply", applyToArgs);
    isEq = new IsEq((Language)instance, "eq?");
    isEqv = new IsEqv((Language)instance, "eqv?", isEq);
    isEqual = new IsEqual((Language)instance, "equal?");
    map = new Map(true, applyToArgs, applyFieldDecl, isEq);
    forEach = new Map(false, applyToArgs, applyFieldDecl, isEq);
    numEqu = NumberCompare.make((Language)instance, "=", 8);
    numGrt = NumberCompare.make((Language)instance, ">", 16);
    numGEq = NumberCompare.make((Language)instance, ">=", 24);
    numLss = NumberCompare.make((Language)instance, "<", 4);
    numLEq = NumberCompare.make((Language)instance, "<=", 12);
    isOdd = new NumberPredicate((Language)instance, "odd?", 1);
    isEven = new NumberPredicate((Language)instance, "even?", 2);
    instance.initScheme();
    int i = HttpRequestContext.importServletDefinitions;
    if (i > 0)
      try {
        String str;
        Scheme scheme = instance;
        if (i > 1) {
          str = "gnu.kawa.servlet.servlets";
        } else {
          str = "gnu.kawa.servlet.HTTP";
        } 
        scheme.loadClass(str);
      } catch (Throwable throwable) {} 
    writeFormat = (AbstractFormat)new DisplayFormat(true, 'S');
    displayFormat = (AbstractFormat)new DisplayFormat(false, 'S');
    unitNamespace = Namespace.valueOf("http://kawa.gnu.org/unit", "unit");
  }
  
  public Scheme() {
    this.userEnv = getNewEnvironment();
  }
  
  protected Scheme(Environment paramEnvironment) {}
  
  public static Environment builtin() {
    return (Environment)kawaEnvironment;
  }
  
  public static Object eval(InPort paramInPort, Environment paramEnvironment) {
    SourceMessages sourceMessages = new SourceMessages();
    try {
      null = ReaderParens.readList((LispReader)Language.getDefaultLanguage().getLexer(paramInPort, sourceMessages), 0, 1, -1);
      if (sourceMessages.seenErrors())
        throw new SyntaxException(sourceMessages); 
      return Eval.evalBody(null, paramEnvironment, sourceMessages);
    } catch (SyntaxException syntaxException) {
      throw new RuntimeException("eval: errors while compiling:\n" + syntaxException.getMessages().toString(20));
    } catch (IOException iOException) {
      throw new RuntimeException("eval: I/O exception: " + iOException.toString());
    } catch (RuntimeException runtimeException) {
      throw runtimeException;
    } catch (Error error) {
      throw error;
    } catch (Throwable throwable) {
      throw new WrappedException(throwable);
    } 
  }
  
  public static Object eval(Object paramObject, Environment paramEnvironment) {
    try {
      return Eval.eval(paramObject, paramEnvironment);
    } catch (RuntimeException runtimeException) {
      throw runtimeException;
    } catch (Error error) {
      throw error;
    } catch (Throwable throwable) {
      throw new WrappedException(throwable);
    } 
  }
  
  public static Object eval(String paramString, Environment paramEnvironment) {
    return eval((InPort)new CharArrayInPort(paramString), paramEnvironment);
  }
  
  public static Type exp2Type(Expression paramExpression) {
    return getInstance().getTypeFor(paramExpression);
  }
  
  public static Scheme getInstance() {
    return instance;
  }
  
  public static Type getNamedType(String paramString) {
    // Byte code:
    //   0: invokestatic getTypeMap : ()Ljava/util/HashMap;
    //   3: pop
    //   4: getstatic kawa/standard/Scheme.types : Ljava/util/HashMap;
    //   7: aload_0
    //   8: invokevirtual get : (Ljava/lang/Object;)Ljava/lang/Object;
    //   11: checkcast gnu/bytecode/Type
    //   14: astore_3
    //   15: aload_3
    //   16: astore_2
    //   17: aload_3
    //   18: ifnonnull -> 149
    //   21: aload_0
    //   22: ldc_w 'elisp:'
    //   25: invokevirtual startsWith : (Ljava/lang/String;)Z
    //   28: ifne -> 43
    //   31: aload_3
    //   32: astore_2
    //   33: aload_0
    //   34: ldc_w 'clisp:'
    //   37: invokevirtual startsWith : (Ljava/lang/String;)Z
    //   40: ifeq -> 149
    //   43: aload_0
    //   44: bipush #58
    //   46: invokevirtual indexOf : (I)I
    //   49: istore_1
    //   50: aload_0
    //   51: iload_1
    //   52: iconst_1
    //   53: iadd
    //   54: invokevirtual substring : (I)Ljava/lang/String;
    //   57: invokestatic getNamedType : (Ljava/lang/String;)Lgnu/bytecode/Type;
    //   60: invokevirtual getReflectClass : ()Ljava/lang/Class;
    //   63: astore_2
    //   64: aload_0
    //   65: iconst_0
    //   66: iload_1
    //   67: invokevirtual substring : (II)Ljava/lang/String;
    //   70: astore_3
    //   71: aload_3
    //   72: invokestatic getInstance : (Ljava/lang/String;)Lgnu/expr/Language;
    //   75: astore #4
    //   77: aload #4
    //   79: ifnonnull -> 125
    //   82: new java/lang/RuntimeException
    //   85: dup
    //   86: new java/lang/StringBuilder
    //   89: dup
    //   90: invokespecial <init> : ()V
    //   93: ldc_w 'unknown type ''
    //   96: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   99: aload_0
    //   100: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   103: ldc_w '' - unknown language ''
    //   106: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   109: aload_3
    //   110: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   113: bipush #39
    //   115: invokevirtual append : (C)Ljava/lang/StringBuilder;
    //   118: invokevirtual toString : ()Ljava/lang/String;
    //   121: invokespecial <init> : (Ljava/lang/String;)V
    //   124: athrow
    //   125: aload #4
    //   127: aload_2
    //   128: invokevirtual getTypeFor : (Ljava/lang/Class;)Lgnu/bytecode/Type;
    //   131: astore_3
    //   132: aload_3
    //   133: astore_2
    //   134: aload_3
    //   135: ifnull -> 149
    //   138: getstatic kawa/standard/Scheme.types : Ljava/util/HashMap;
    //   141: aload_0
    //   142: aload_3
    //   143: invokevirtual put : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   146: pop
    //   147: aload_3
    //   148: astore_2
    //   149: aload_2
    //   150: areturn
  }
  
  static HashMap<String, Type> getTypeMap() {
    // Byte code:
    //   0: ldc kawa/standard/Scheme
    //   2: monitorenter
    //   3: getstatic kawa/standard/Scheme.types : Ljava/util/HashMap;
    //   6: ifnonnull -> 782
    //   9: new gnu/kawa/lispexpr/LangPrimType
    //   12: dup
    //   13: getstatic gnu/bytecode/Type.booleanType : Lgnu/bytecode/PrimType;
    //   16: invokestatic getInstance : ()Lkawa/standard/Scheme;
    //   19: invokespecial <init> : (Lgnu/bytecode/PrimType;Lgnu/expr/Language;)V
    //   22: putstatic kawa/standard/Scheme.booleanType : Lgnu/kawa/lispexpr/LangPrimType;
    //   25: new java/util/HashMap
    //   28: dup
    //   29: invokespecial <init> : ()V
    //   32: putstatic kawa/standard/Scheme.types : Ljava/util/HashMap;
    //   35: getstatic kawa/standard/Scheme.types : Ljava/util/HashMap;
    //   38: ldc_w 'void'
    //   41: getstatic gnu/kawa/lispexpr/LangPrimType.voidType : Lgnu/kawa/lispexpr/LangPrimType;
    //   44: invokevirtual put : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   47: pop
    //   48: getstatic kawa/standard/Scheme.types : Ljava/util/HashMap;
    //   51: ldc_w 'int'
    //   54: getstatic gnu/kawa/lispexpr/LangPrimType.intType : Lgnu/bytecode/PrimType;
    //   57: invokevirtual put : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   60: pop
    //   61: getstatic kawa/standard/Scheme.types : Ljava/util/HashMap;
    //   64: ldc_w 'char'
    //   67: getstatic gnu/kawa/lispexpr/LangPrimType.charType : Lgnu/kawa/lispexpr/LangPrimType;
    //   70: invokevirtual put : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   73: pop
    //   74: getstatic kawa/standard/Scheme.types : Ljava/util/HashMap;
    //   77: ldc_w 'boolean'
    //   80: getstatic kawa/standard/Scheme.booleanType : Lgnu/kawa/lispexpr/LangPrimType;
    //   83: invokevirtual put : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   86: pop
    //   87: getstatic kawa/standard/Scheme.types : Ljava/util/HashMap;
    //   90: ldc_w 'byte'
    //   93: getstatic gnu/kawa/lispexpr/LangPrimType.byteType : Lgnu/bytecode/PrimType;
    //   96: invokevirtual put : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   99: pop
    //   100: getstatic kawa/standard/Scheme.types : Ljava/util/HashMap;
    //   103: ldc_w 'short'
    //   106: getstatic gnu/kawa/lispexpr/LangPrimType.shortType : Lgnu/bytecode/PrimType;
    //   109: invokevirtual put : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   112: pop
    //   113: getstatic kawa/standard/Scheme.types : Ljava/util/HashMap;
    //   116: ldc_w 'long'
    //   119: getstatic gnu/kawa/lispexpr/LangPrimType.longType : Lgnu/bytecode/PrimType;
    //   122: invokevirtual put : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   125: pop
    //   126: getstatic kawa/standard/Scheme.types : Ljava/util/HashMap;
    //   129: ldc_w 'float'
    //   132: getstatic gnu/kawa/lispexpr/LangPrimType.floatType : Lgnu/bytecode/PrimType;
    //   135: invokevirtual put : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   138: pop
    //   139: getstatic kawa/standard/Scheme.types : Ljava/util/HashMap;
    //   142: ldc_w 'double'
    //   145: getstatic gnu/kawa/lispexpr/LangPrimType.doubleType : Lgnu/bytecode/PrimType;
    //   148: invokevirtual put : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   151: pop
    //   152: getstatic kawa/standard/Scheme.types : Ljava/util/HashMap;
    //   155: ldc_w 'never-returns'
    //   158: getstatic gnu/bytecode/Type.neverReturnsType : Lgnu/bytecode/PrimType;
    //   161: invokevirtual put : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   164: pop
    //   165: getstatic kawa/standard/Scheme.types : Ljava/util/HashMap;
    //   168: ldc_w 'Object'
    //   171: getstatic gnu/bytecode/Type.objectType : Lgnu/bytecode/ClassType;
    //   174: invokevirtual put : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   177: pop
    //   178: getstatic kawa/standard/Scheme.types : Ljava/util/HashMap;
    //   181: ldc_w 'String'
    //   184: getstatic gnu/bytecode/Type.toStringType : Lgnu/bytecode/ClassType;
    //   187: invokevirtual put : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   190: pop
    //   191: getstatic kawa/standard/Scheme.types : Ljava/util/HashMap;
    //   194: ldc_w 'object'
    //   197: getstatic gnu/bytecode/Type.objectType : Lgnu/bytecode/ClassType;
    //   200: invokevirtual put : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   203: pop
    //   204: getstatic kawa/standard/Scheme.types : Ljava/util/HashMap;
    //   207: ldc_w 'number'
    //   210: getstatic gnu/kawa/lispexpr/LangObjType.numericType : Lgnu/kawa/lispexpr/LangObjType;
    //   213: invokevirtual put : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   216: pop
    //   217: getstatic kawa/standard/Scheme.types : Ljava/util/HashMap;
    //   220: ldc_w 'quantity'
    //   223: ldc_w 'gnu.math.Quantity'
    //   226: invokestatic make : (Ljava/lang/String;)Lgnu/bytecode/ClassType;
    //   229: invokevirtual put : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   232: pop
    //   233: getstatic kawa/standard/Scheme.types : Ljava/util/HashMap;
    //   236: ldc_w 'complex'
    //   239: ldc_w 'gnu.math.Complex'
    //   242: invokestatic make : (Ljava/lang/String;)Lgnu/bytecode/ClassType;
    //   245: invokevirtual put : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   248: pop
    //   249: getstatic kawa/standard/Scheme.types : Ljava/util/HashMap;
    //   252: ldc_w 'real'
    //   255: getstatic gnu/kawa/lispexpr/LangObjType.realType : Lgnu/kawa/lispexpr/LangObjType;
    //   258: invokevirtual put : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   261: pop
    //   262: getstatic kawa/standard/Scheme.types : Ljava/util/HashMap;
    //   265: ldc_w 'rational'
    //   268: getstatic gnu/kawa/lispexpr/LangObjType.rationalType : Lgnu/kawa/lispexpr/LangObjType;
    //   271: invokevirtual put : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   274: pop
    //   275: getstatic kawa/standard/Scheme.types : Ljava/util/HashMap;
    //   278: ldc_w 'integer'
    //   281: getstatic gnu/kawa/lispexpr/LangObjType.integerType : Lgnu/kawa/lispexpr/LangObjType;
    //   284: invokevirtual put : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   287: pop
    //   288: getstatic kawa/standard/Scheme.types : Ljava/util/HashMap;
    //   291: ldc_w 'symbol'
    //   294: ldc_w 'gnu.mapping.Symbol'
    //   297: invokestatic make : (Ljava/lang/String;)Lgnu/bytecode/ClassType;
    //   300: invokevirtual put : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   303: pop
    //   304: getstatic kawa/standard/Scheme.types : Ljava/util/HashMap;
    //   307: ldc_w 'namespace'
    //   310: ldc_w 'gnu.mapping.Namespace'
    //   313: invokestatic make : (Ljava/lang/String;)Lgnu/bytecode/ClassType;
    //   316: invokevirtual put : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   319: pop
    //   320: getstatic kawa/standard/Scheme.types : Ljava/util/HashMap;
    //   323: ldc_w 'keyword'
    //   326: ldc_w 'gnu.expr.Keyword'
    //   329: invokestatic make : (Ljava/lang/String;)Lgnu/bytecode/ClassType;
    //   332: invokevirtual put : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   335: pop
    //   336: getstatic kawa/standard/Scheme.types : Ljava/util/HashMap;
    //   339: ldc_w 'pair'
    //   342: ldc_w 'gnu.lists.Pair'
    //   345: invokestatic make : (Ljava/lang/String;)Lgnu/bytecode/ClassType;
    //   348: invokevirtual put : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   351: pop
    //   352: getstatic kawa/standard/Scheme.types : Ljava/util/HashMap;
    //   355: ldc_w 'pair-with-position'
    //   358: ldc_w 'gnu.lists.PairWithPosition'
    //   361: invokestatic make : (Ljava/lang/String;)Lgnu/bytecode/ClassType;
    //   364: invokevirtual put : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   367: pop
    //   368: getstatic kawa/standard/Scheme.types : Ljava/util/HashMap;
    //   371: ldc_w 'constant-string'
    //   374: ldc_w 'java.lang.String'
    //   377: invokestatic make : (Ljava/lang/String;)Lgnu/bytecode/ClassType;
    //   380: invokevirtual put : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   383: pop
    //   384: getstatic kawa/standard/Scheme.types : Ljava/util/HashMap;
    //   387: ldc_w 'abstract-string'
    //   390: ldc_w 'gnu.lists.CharSeq'
    //   393: invokestatic make : (Ljava/lang/String;)Lgnu/bytecode/ClassType;
    //   396: invokevirtual put : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   399: pop
    //   400: getstatic kawa/standard/Scheme.types : Ljava/util/HashMap;
    //   403: ldc_w 'character'
    //   406: ldc_w 'gnu.text.Char'
    //   409: invokestatic make : (Ljava/lang/String;)Lgnu/bytecode/ClassType;
    //   412: invokevirtual put : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   415: pop
    //   416: getstatic kawa/standard/Scheme.types : Ljava/util/HashMap;
    //   419: ldc_w 'vector'
    //   422: getstatic gnu/kawa/lispexpr/LangObjType.vectorType : Lgnu/kawa/lispexpr/LangObjType;
    //   425: invokevirtual put : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   428: pop
    //   429: getstatic kawa/standard/Scheme.types : Ljava/util/HashMap;
    //   432: ldc_w 'string'
    //   435: getstatic gnu/kawa/lispexpr/LangObjType.stringType : Lgnu/kawa/lispexpr/LangObjType;
    //   438: invokevirtual put : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   441: pop
    //   442: getstatic kawa/standard/Scheme.types : Ljava/util/HashMap;
    //   445: ldc_w 'list'
    //   448: getstatic gnu/kawa/lispexpr/LangObjType.listType : Lgnu/kawa/lispexpr/LangObjType;
    //   451: invokevirtual put : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   454: pop
    //   455: getstatic kawa/standard/Scheme.types : Ljava/util/HashMap;
    //   458: ldc_w 'function'
    //   461: ldc_w 'gnu.mapping.Procedure'
    //   464: invokestatic make : (Ljava/lang/String;)Lgnu/bytecode/ClassType;
    //   467: invokevirtual put : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   470: pop
    //   471: getstatic kawa/standard/Scheme.types : Ljava/util/HashMap;
    //   474: ldc_w 'procedure'
    //   477: ldc_w 'gnu.mapping.Procedure'
    //   480: invokestatic make : (Ljava/lang/String;)Lgnu/bytecode/ClassType;
    //   483: invokevirtual put : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   486: pop
    //   487: getstatic kawa/standard/Scheme.types : Ljava/util/HashMap;
    //   490: ldc_w 'input-port'
    //   493: ldc_w 'gnu.mapping.InPort'
    //   496: invokestatic make : (Ljava/lang/String;)Lgnu/bytecode/ClassType;
    //   499: invokevirtual put : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   502: pop
    //   503: getstatic kawa/standard/Scheme.types : Ljava/util/HashMap;
    //   506: ldc_w 'output-port'
    //   509: ldc_w 'gnu.mapping.OutPort'
    //   512: invokestatic make : (Ljava/lang/String;)Lgnu/bytecode/ClassType;
    //   515: invokevirtual put : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   518: pop
    //   519: getstatic kawa/standard/Scheme.types : Ljava/util/HashMap;
    //   522: ldc_w 'string-output-port'
    //   525: ldc_w 'gnu.mapping.CharArrayOutPort'
    //   528: invokestatic make : (Ljava/lang/String;)Lgnu/bytecode/ClassType;
    //   531: invokevirtual put : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   534: pop
    //   535: getstatic kawa/standard/Scheme.types : Ljava/util/HashMap;
    //   538: ldc_w 'record'
    //   541: ldc_w 'kawa.lang.Record'
    //   544: invokestatic make : (Ljava/lang/String;)Lgnu/bytecode/ClassType;
    //   547: invokevirtual put : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   550: pop
    //   551: getstatic kawa/standard/Scheme.types : Ljava/util/HashMap;
    //   554: ldc_w 'type'
    //   557: getstatic gnu/kawa/lispexpr/LangObjType.typeType : Lgnu/kawa/lispexpr/LangObjType;
    //   560: invokevirtual put : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   563: pop
    //   564: getstatic kawa/standard/Scheme.types : Ljava/util/HashMap;
    //   567: ldc_w 'class-type'
    //   570: getstatic gnu/kawa/lispexpr/LangObjType.typeClassType : Lgnu/kawa/lispexpr/LangObjType;
    //   573: invokevirtual put : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   576: pop
    //   577: getstatic kawa/standard/Scheme.types : Ljava/util/HashMap;
    //   580: ldc_w 'class'
    //   583: getstatic gnu/kawa/lispexpr/LangObjType.typeClass : Lgnu/kawa/lispexpr/LangObjType;
    //   586: invokevirtual put : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   589: pop
    //   590: getstatic kawa/standard/Scheme.types : Ljava/util/HashMap;
    //   593: ldc_w 's8vector'
    //   596: ldc_w 'gnu.lists.S8Vector'
    //   599: invokestatic make : (Ljava/lang/String;)Lgnu/bytecode/ClassType;
    //   602: invokevirtual put : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   605: pop
    //   606: getstatic kawa/standard/Scheme.types : Ljava/util/HashMap;
    //   609: ldc_w 'u8vector'
    //   612: ldc_w 'gnu.lists.U8Vector'
    //   615: invokestatic make : (Ljava/lang/String;)Lgnu/bytecode/ClassType;
    //   618: invokevirtual put : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   621: pop
    //   622: getstatic kawa/standard/Scheme.types : Ljava/util/HashMap;
    //   625: ldc_w 's16vector'
    //   628: ldc_w 'gnu.lists.S16Vector'
    //   631: invokestatic make : (Ljava/lang/String;)Lgnu/bytecode/ClassType;
    //   634: invokevirtual put : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   637: pop
    //   638: getstatic kawa/standard/Scheme.types : Ljava/util/HashMap;
    //   641: ldc_w 'u16vector'
    //   644: ldc_w 'gnu.lists.U16Vector'
    //   647: invokestatic make : (Ljava/lang/String;)Lgnu/bytecode/ClassType;
    //   650: invokevirtual put : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   653: pop
    //   654: getstatic kawa/standard/Scheme.types : Ljava/util/HashMap;
    //   657: ldc_w 's32vector'
    //   660: ldc_w 'gnu.lists.S32Vector'
    //   663: invokestatic make : (Ljava/lang/String;)Lgnu/bytecode/ClassType;
    //   666: invokevirtual put : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   669: pop
    //   670: getstatic kawa/standard/Scheme.types : Ljava/util/HashMap;
    //   673: ldc_w 'u32vector'
    //   676: ldc_w 'gnu.lists.U32Vector'
    //   679: invokestatic make : (Ljava/lang/String;)Lgnu/bytecode/ClassType;
    //   682: invokevirtual put : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   685: pop
    //   686: getstatic kawa/standard/Scheme.types : Ljava/util/HashMap;
    //   689: ldc_w 's64vector'
    //   692: ldc_w 'gnu.lists.S64Vector'
    //   695: invokestatic make : (Ljava/lang/String;)Lgnu/bytecode/ClassType;
    //   698: invokevirtual put : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   701: pop
    //   702: getstatic kawa/standard/Scheme.types : Ljava/util/HashMap;
    //   705: ldc_w 'u64vector'
    //   708: ldc_w 'gnu.lists.U64Vector'
    //   711: invokestatic make : (Ljava/lang/String;)Lgnu/bytecode/ClassType;
    //   714: invokevirtual put : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   717: pop
    //   718: getstatic kawa/standard/Scheme.types : Ljava/util/HashMap;
    //   721: ldc_w 'f32vector'
    //   724: ldc_w 'gnu.lists.F32Vector'
    //   727: invokestatic make : (Ljava/lang/String;)Lgnu/bytecode/ClassType;
    //   730: invokevirtual put : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   733: pop
    //   734: getstatic kawa/standard/Scheme.types : Ljava/util/HashMap;
    //   737: ldc_w 'f64vector'
    //   740: ldc_w 'gnu.lists.F64Vector'
    //   743: invokestatic make : (Ljava/lang/String;)Lgnu/bytecode/ClassType;
    //   746: invokevirtual put : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   749: pop
    //   750: getstatic kawa/standard/Scheme.types : Ljava/util/HashMap;
    //   753: ldc_w 'document'
    //   756: ldc_w 'gnu.kawa.xml.KDocument'
    //   759: invokestatic make : (Ljava/lang/String;)Lgnu/bytecode/ClassType;
    //   762: invokevirtual put : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   765: pop
    //   766: getstatic kawa/standard/Scheme.types : Ljava/util/HashMap;
    //   769: ldc_w 'readtable'
    //   772: ldc_w 'gnu.kawa.lispexpr.ReadTable'
    //   775: invokestatic make : (Ljava/lang/String;)Lgnu/bytecode/ClassType;
    //   778: invokevirtual put : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   781: pop
    //   782: getstatic kawa/standard/Scheme.types : Ljava/util/HashMap;
    //   785: astore_0
    //   786: ldc kawa/standard/Scheme
    //   788: monitorexit
    //   789: aload_0
    //   790: areturn
    //   791: astore_0
    //   792: ldc kawa/standard/Scheme
    //   794: monitorexit
    //   795: aload_0
    //   796: athrow
    // Exception table:
    //   from	to	target	type
    //   3	782	791	finally
    //   782	786	791	finally
  }
  
  public static Type getTypeValue(Expression paramExpression) {
    return getInstance().getTypeFor(paramExpression);
  }
  
  private void initScheme() {
    this.environ = nullEnvironment;
    this.environ.addLocation(LispLanguage.lookup_sym, null, (Location)getNamedPartLocation);
    defSntxStFld("lambda", "kawa.standard.SchemeCompilation", "lambda");
    defSntxStFld("quote", "kawa.lang.Quote", "plainQuote");
    defSntxStFld("%define", "kawa.standard.define", "defineRaw");
    defSntxStFld("define", "kawa.lib.prim_syntax");
    defSntxStFld("if", "kawa.lib.prim_syntax");
    defSntxStFld("set!", "kawa.standard.set_b", "set");
    defSntxStFld("cond", "kawa.lib.std_syntax");
    defSntxStFld("case", "kawa.lib.std_syntax");
    defSntxStFld("and", "kawa.lib.std_syntax");
    defSntxStFld("or", "kawa.lib.std_syntax");
    defSntxStFld("%let", "kawa.standard.let", "let");
    defSntxStFld("let", "kawa.lib.std_syntax");
    defSntxStFld("let*", "kawa.lib.std_syntax");
    defSntxStFld("letrec", "kawa.lib.prim_syntax");
    defSntxStFld("begin", "kawa.standard.begin", "begin");
    defSntxStFld("do", "kawa.lib.std_syntax");
    defSntxStFld("delay", "kawa.lib.std_syntax");
    defProcStFld("%make-promise", "kawa.lib.std_syntax");
    defSntxStFld("quasiquote", "kawa.lang.Quote", "quasiQuote");
    defSntxStFld("define-syntax", "kawa.lib.prim_syntax");
    defSntxStFld("let-syntax", "kawa.standard.let_syntax", "let_syntax");
    defSntxStFld("letrec-syntax", "kawa.standard.let_syntax", "letrec_syntax");
    defSntxStFld("syntax-rules", "kawa.standard.syntax_rules", "syntax_rules");
    nullEnvironment.setLocked();
    this.environ = r4Environment;
    defProcStFld("not", "kawa.standard.Scheme");
    defProcStFld("boolean?", "kawa.lib.misc");
    defProcStFld("eq?", "kawa.standard.Scheme", "isEq");
    defProcStFld("eqv?", "kawa.standard.Scheme", "isEqv");
    defProcStFld("equal?", "kawa.standard.Scheme", "isEqual");
    defProcStFld("pair?", "kawa.lib.lists");
    defProcStFld("cons", "kawa.lib.lists");
    defProcStFld("car", "kawa.lib.lists");
    defProcStFld("cdr", "kawa.lib.lists");
    defProcStFld("set-car!", "kawa.lib.lists");
    defProcStFld("set-cdr!", "kawa.lib.lists");
    defProcStFld("caar", "kawa.lib.lists");
    defProcStFld("cadr", "kawa.lib.lists");
    defProcStFld("cdar", "kawa.lib.lists");
    defProcStFld("cddr", "kawa.lib.lists");
    defProcStFld("caaar", "kawa.lib.lists");
    defProcStFld("caadr", "kawa.lib.lists");
    defProcStFld("cadar", "kawa.lib.lists");
    defProcStFld("caddr", "kawa.lib.lists");
    defProcStFld("cdaar", "kawa.lib.lists");
    defProcStFld("cdadr", "kawa.lib.lists");
    defProcStFld("cddar", "kawa.lib.lists");
    defProcStFld("cdddr", "kawa.lib.lists");
    defProcStFld("caaaar", "kawa.lib.lists");
    defProcStFld("caaadr", "kawa.lib.lists");
    defProcStFld("caadar", "kawa.lib.lists");
    defProcStFld("caaddr", "kawa.lib.lists");
    defProcStFld("cadaar", "kawa.lib.lists");
    defProcStFld("cadadr", "kawa.lib.lists");
    defProcStFld("caddar", "kawa.lib.lists");
    defProcStFld("cadddr", "kawa.lib.lists");
    defProcStFld("cdaaar", "kawa.lib.lists");
    defProcStFld("cdaadr", "kawa.lib.lists");
    defProcStFld("cdadar", "kawa.lib.lists");
    defProcStFld("cdaddr", "kawa.lib.lists");
    defProcStFld("cddaar", "kawa.lib.lists");
    defProcStFld("cddadr", "kawa.lib.lists");
    defProcStFld("cdddar", "kawa.lib.lists");
    defProcStFld("cddddr", "kawa.lib.lists");
    defProcStFld("null?", "kawa.lib.lists");
    defProcStFld("list?", "kawa.lib.lists");
    defProcStFld("length", "kawa.lib.lists");
    defProcStFld("append", "kawa.standard.append", "append");
    defProcStFld("reverse", "kawa.lib.lists");
    defProcStFld("reverse!", "kawa.lib.lists");
    defProcStFld("list-tail", "kawa.lib.lists");
    defProcStFld("list-ref", "kawa.lib.lists");
    defProcStFld("memq", "kawa.lib.lists");
    defProcStFld("memv", "kawa.lib.lists");
    defProcStFld("member", "kawa.lib.lists");
    defProcStFld("assq", "kawa.lib.lists");
    defProcStFld("assv", "kawa.lib.lists");
    defProcStFld("assoc", "kawa.lib.lists");
    defProcStFld("symbol?", "kawa.lib.misc");
    defProcStFld("symbol->string", "kawa.lib.misc");
    defProcStFld("string->symbol", "kawa.lib.misc");
    defProcStFld("symbol=?", "kawa.lib.misc");
    defProcStFld("symbol-local-name", "kawa.lib.misc");
    defProcStFld("symbol-namespace", "kawa.lib.misc");
    defProcStFld("symbol-namespace-uri", "kawa.lib.misc");
    defProcStFld("symbol-prefix", "kawa.lib.misc");
    defProcStFld("namespace-uri", "kawa.lib.misc");
    defProcStFld("namespace-prefix", "kawa.lib.misc");
    defProcStFld("number?", "kawa.lib.numbers");
    defProcStFld("quantity?", "kawa.lib.numbers");
    defProcStFld("complex?", "kawa.lib.numbers");
    defProcStFld("real?", "kawa.lib.numbers");
    defProcStFld("rational?", "kawa.lib.numbers");
    defProcStFld("integer?", "kawa.lib.numbers");
    defProcStFld("exact?", "kawa.lib.numbers");
    defProcStFld("inexact?", "kawa.lib.numbers");
    defProcStFld("=", "kawa.standard.Scheme", "numEqu");
    defProcStFld("<", "kawa.standard.Scheme", "numLss");
    defProcStFld(">", "kawa.standard.Scheme", "numGrt");
    defProcStFld("<=", "kawa.standard.Scheme", "numLEq");
    defProcStFld(">=", "kawa.standard.Scheme", "numGEq");
    defProcStFld("zero?", "kawa.lib.numbers");
    defProcStFld("positive?", "kawa.lib.numbers");
    defProcStFld("negative?", "kawa.lib.numbers");
    defProcStFld("odd?", "kawa.standard.Scheme", "isOdd");
    defProcStFld("even?", "kawa.standard.Scheme", "isEven");
    defProcStFld("max", "kawa.lib.numbers");
    defProcStFld("min", "kawa.lib.numbers");
    defProcStFld("+", "gnu.kawa.functions.AddOp", "$Pl");
    defProcStFld("-", "gnu.kawa.functions.AddOp", "$Mn");
    defProcStFld("*", "gnu.kawa.functions.MultiplyOp", "$St");
    defProcStFld("/", "gnu.kawa.functions.DivideOp", "$Sl");
    defProcStFld("abs", "kawa.lib.numbers");
    defProcStFld("quotient", "gnu.kawa.functions.DivideOp", "quotient");
    defProcStFld("remainder", "gnu.kawa.functions.DivideOp", "remainder");
    defProcStFld("modulo", "gnu.kawa.functions.DivideOp", "modulo");
    defProcStFld("div", "gnu.kawa.functions.DivideOp", "div");
    defProcStFld("mod", "gnu.kawa.functions.DivideOp", "mod");
    defProcStFld("div0", "gnu.kawa.functions.DivideOp", "div0");
    defProcStFld("mod0", "gnu.kawa.functions.DivideOp", "mod0");
    defProcStFld("div-and-mod", "kawa.lib.numbers");
    defProcStFld("div0-and-mod0", "kawa.lib.numbers");
    defProcStFld("gcd", "kawa.lib.numbers");
    defProcStFld("lcm", "kawa.lib.numbers");
    defProcStFld("numerator", "kawa.lib.numbers");
    defProcStFld("denominator", "kawa.lib.numbers");
    defProcStFld("floor", "kawa.lib.numbers");
    defProcStFld("ceiling", "kawa.lib.numbers");
    defProcStFld("truncate", "kawa.lib.numbers");
    defProcStFld("round", "kawa.lib.numbers");
    defProcStFld("rationalize", "kawa.lib.numbers");
    defProcStFld("exp", "kawa.lib.numbers");
    defProcStFld("log", "kawa.lib.numbers");
    defProcStFld("sin", "kawa.lib.numbers");
    defProcStFld("cos", "kawa.lib.numbers");
    defProcStFld("tan", "kawa.lib.numbers");
    defProcStFld("asin", "kawa.lib.numbers");
    defProcStFld("acos", "kawa.lib.numbers");
    defProcStFld("atan", "kawa.lib.numbers");
    defProcStFld("sqrt", "kawa.lib.numbers");
    defProcStFld("expt", "kawa.standard.expt");
    defProcStFld("make-rectangular", "kawa.lib.numbers");
    defProcStFld("make-polar", "kawa.lib.numbers");
    defProcStFld("real-part", "kawa.lib.numbers");
    defProcStFld("imag-part", "kawa.lib.numbers");
    defProcStFld("magnitude", "kawa.lib.numbers");
    defProcStFld("angle", "kawa.lib.numbers");
    defProcStFld("inexact", "kawa.lib.numbers");
    defProcStFld("exact", "kawa.lib.numbers");
    defProcStFld("exact->inexact", "kawa.lib.numbers");
    defProcStFld("inexact->exact", "kawa.lib.numbers");
    defProcStFld("number->string", "kawa.lib.numbers");
    defProcStFld("string->number", "kawa.lib.numbers");
    defProcStFld("char?", "kawa.lib.characters");
    defProcStFld("char=?", "kawa.lib.characters");
    defProcStFld("char<?", "kawa.lib.characters");
    defProcStFld("char>?", "kawa.lib.characters");
    defProcStFld("char<=?", "kawa.lib.characters");
    defProcStFld("char>=?", "kawa.lib.characters");
    defProcStFld("char-ci=?", "kawa.lib.rnrs.unicode");
    defProcStFld("char-ci<?", "kawa.lib.rnrs.unicode");
    defProcStFld("char-ci>?", "kawa.lib.rnrs.unicode");
    defProcStFld("char-ci<=?", "kawa.lib.rnrs.unicode");
    defProcStFld("char-ci>=?", "kawa.lib.rnrs.unicode");
    defProcStFld("char-alphabetic?", "kawa.lib.rnrs.unicode");
    defProcStFld("char-numeric?", "kawa.lib.rnrs.unicode");
    defProcStFld("char-whitespace?", "kawa.lib.rnrs.unicode");
    defProcStFld("char-upper-case?", "kawa.lib.rnrs.unicode");
    defProcStFld("char-lower-case?", "kawa.lib.rnrs.unicode");
    defProcStFld("char-title-case?", "kawa.lib.rnrs.unicode");
    defProcStFld("char->integer", "kawa.lib.characters");
    defProcStFld("integer->char", "kawa.lib.characters");
    defProcStFld("char-upcase", "kawa.lib.rnrs.unicode");
    defProcStFld("char-downcase", "kawa.lib.rnrs.unicode");
    defProcStFld("char-titlecase", "kawa.lib.rnrs.unicode");
    defProcStFld("char-foldcase", "kawa.lib.rnrs.unicode");
    defProcStFld("char-general-category", "kawa.lib.rnrs.unicode");
    defProcStFld("string?", "kawa.lib.strings");
    defProcStFld("make-string", "kawa.lib.strings");
    defProcStFld("string-length", "kawa.lib.strings");
    defProcStFld("string-ref", "kawa.lib.strings");
    defProcStFld("string-set!", "kawa.lib.strings");
    defProcStFld("string=?", "kawa.lib.strings");
    defProcStFld("string<?", "kawa.lib.strings");
    defProcStFld("string>?", "kawa.lib.strings");
    defProcStFld("string<=?", "kawa.lib.strings");
    defProcStFld("string>=?", "kawa.lib.strings");
    defProcStFld("string-ci=?", "kawa.lib.rnrs.unicode");
    defProcStFld("string-ci<?", "kawa.lib.rnrs.unicode");
    defProcStFld("string-ci>?", "kawa.lib.rnrs.unicode");
    defProcStFld("string-ci<=?", "kawa.lib.rnrs.unicode");
    defProcStFld("string-ci>=?", "kawa.lib.rnrs.unicode");
    defProcStFld("string-normalize-nfd", "kawa.lib.rnrs.unicode");
    defProcStFld("string-normalize-nfkd", "kawa.lib.rnrs.unicode");
    defProcStFld("string-normalize-nfc", "kawa.lib.rnrs.unicode");
    defProcStFld("string-normalize-nfkc", "kawa.lib.rnrs.unicode");
    defProcStFld("substring", "kawa.lib.strings");
    defProcStFld("string-append", "kawa.lib.strings");
    defProcStFld("string-append/shared", "kawa.lib.strings");
    defProcStFld("string->list", "kawa.lib.strings");
    defProcStFld("list->string", "kawa.lib.strings");
    defProcStFld("string-copy", "kawa.lib.strings");
    defProcStFld("string-fill!", "kawa.lib.strings");
    defProcStFld("vector?", "kawa.lib.vectors");
    defProcStFld("make-vector", "kawa.lib.vectors");
    defProcStFld("vector-length", "kawa.lib.vectors");
    defProcStFld("vector-ref", "kawa.lib.vectors");
    defProcStFld("vector-set!", "kawa.lib.vectors");
    defProcStFld("list->vector", "kawa.lib.vectors");
    defProcStFld("vector->list", "kawa.lib.vectors");
    defProcStFld("vector-fill!", "kawa.lib.vectors");
    defProcStFld("vector-append", "kawa.standard.vector_append", "vectorAppend");
    defProcStFld("values-append", "gnu.kawa.functions.AppendValues", "appendValues");
    defProcStFld("procedure?", "kawa.lib.misc");
    defProcStFld("apply", "kawa.standard.Scheme", "apply");
    defProcStFld("map", "kawa.standard.Scheme", "map");
    defProcStFld("for-each", "kawa.standard.Scheme", "forEach");
    defProcStFld("call-with-current-continuation", "gnu.kawa.functions.CallCC", "callcc");
    defProcStFld("call/cc", "kawa.standard.callcc", "callcc");
    defProcStFld("force", "kawa.lib.misc");
    defProcStFld("call-with-input-file", "kawa.lib.ports");
    defProcStFld("call-with-output-file", "kawa.lib.ports");
    defProcStFld("input-port?", "kawa.lib.ports");
    defProcStFld("output-port?", "kawa.lib.ports");
    defProcStFld("current-input-port", "kawa.lib.ports");
    defProcStFld("current-output-port", "kawa.lib.ports");
    defProcStFld("with-input-from-file", "kawa.lib.ports");
    defProcStFld("with-output-to-file", "kawa.lib.ports");
    defProcStFld("open-input-file", "kawa.lib.ports");
    defProcStFld("open-output-file", "kawa.lib.ports");
    defProcStFld("close-input-port", "kawa.lib.ports");
    defProcStFld("close-output-port", "kawa.lib.ports");
    defProcStFld("read", "kawa.lib.ports");
    defProcStFld("read-line", "kawa.lib.ports");
    defProcStFld("read-char", "kawa.standard.readchar", "readChar");
    defProcStFld("peek-char", "kawa.standard.readchar", "peekChar");
    defProcStFld("eof-object?", "kawa.lib.ports");
    defProcStFld("char-ready?", "kawa.lib.ports");
    defProcStFld("write", "kawa.lib.ports");
    defProcStFld("display", "kawa.lib.ports");
    defProcStFld("print-as-xml", "gnu.xquery.lang.XQuery", "writeFormat");
    defProcStFld("write-char", "kawa.lib.ports");
    defProcStFld("newline", "kawa.lib.ports");
    defProcStFld("load", "kawa.standard.load", "load");
    defProcStFld("load-relative", "kawa.standard.load", "loadRelative");
    defProcStFld("transcript-off", "kawa.lib.ports");
    defProcStFld("transcript-on", "kawa.lib.ports");
    defProcStFld("call-with-input-string", "kawa.lib.ports");
    defProcStFld("open-input-string", "kawa.lib.ports");
    defProcStFld("open-output-string", "kawa.lib.ports");
    defProcStFld("get-output-string", "kawa.lib.ports");
    defProcStFld("call-with-output-string", "kawa.lib.ports");
    defProcStFld("force-output", "kawa.lib.ports");
    defProcStFld("port-line", "kawa.lib.ports");
    defProcStFld("set-port-line!", "kawa.lib.ports");
    defProcStFld("port-column", "kawa.lib.ports");
    defProcStFld("current-error-port", "kawa.lib.ports");
    defProcStFld("input-port-line-number", "kawa.lib.ports");
    defProcStFld("set-input-port-line-number!", "kawa.lib.ports");
    defProcStFld("input-port-column-number", "kawa.lib.ports");
    defProcStFld("input-port-read-state", "kawa.lib.ports");
    defProcStFld("default-prompter", "kawa.lib.ports");
    defProcStFld("input-port-prompter", "kawa.lib.ports");
    defProcStFld("set-input-port-prompter!", "kawa.lib.ports");
    defProcStFld("base-uri", "kawa.lib.misc");
    defProcStFld("%syntax-error", "kawa.standard.syntax_error", "syntax_error");
    defProcStFld("syntax-error", "kawa.lib.prim_syntax");
    r4Environment.setLocked();
    this.environ = r5Environment;
    defProcStFld("values", "kawa.lib.misc");
    defProcStFld("call-with-values", "kawa.standard.call_with_values", "callWithValues");
    defSntxStFld("let-values", "kawa.lib.syntax");
    defSntxStFld("let*-values", "kawa.lib.syntax");
    defSntxStFld("case-lambda", "kawa.lib.syntax");
    defSntxStFld("receive", "kawa.lib.syntax");
    defProcStFld("eval", "kawa.lang.Eval");
    defProcStFld("repl", "kawa.standard.SchemeCompilation", "repl");
    defProcStFld("scheme-report-environment", "kawa.lib.misc");
    defProcStFld("null-environment", "kawa.lib.misc");
    defProcStFld("interaction-environment", "kawa.lib.misc");
    defProcStFld("dynamic-wind", "kawa.lib.misc");
    r5Environment.setLocked();
    this.environ = (Environment)kawaEnvironment;
    defSntxStFld("define-private", "kawa.lib.prim_syntax");
    defSntxStFld("define-constant", "kawa.lib.prim_syntax");
    defSntxStFld("define-autoload", "kawa.standard.define_autoload", "define_autoload");
    defSntxStFld("define-autoloads-from-file", "kawa.standard.define_autoload", "define_autoloads_from_file");
    defProcStFld("exit", "kawa.lib.rnrs.programs");
    defProcStFld("command-line", "kawa.lib.rnrs.programs");
    defProcStFld("bitwise-arithmetic-shift", "gnu.kawa.functions.BitwiseOp", "ashift");
    defProcStFld("arithmetic-shift", "gnu.kawa.functions.BitwiseOp", "ashift");
    defProcStFld("ash", "gnu.kawa.functions.BitwiseOp", "ashift");
    defProcStFld("bitwise-arithmetic-shift-left", "gnu.kawa.functions.BitwiseOp", "ashiftl");
    defProcStFld("bitwise-arithmetic-shift-right", "gnu.kawa.functions.BitwiseOp", "ashiftr");
    defProcStFld("bitwise-and", "gnu.kawa.functions.BitwiseOp", "and");
    defProcStFld("logand", "gnu.kawa.functions.BitwiseOp", "and");
    defProcStFld("bitwise-ior", "gnu.kawa.functions.BitwiseOp", "ior");
    defProcStFld("logior", "gnu.kawa.functions.BitwiseOp", "ior");
    defProcStFld("bitwise-xor", "gnu.kawa.functions.BitwiseOp", "xor");
    defProcStFld("logxor", "gnu.kawa.functions.BitwiseOp", "xor");
    defProcStFld("bitwise-if", "kawa.lib.numbers");
    defProcStFld("bitwise-not", "gnu.kawa.functions.BitwiseOp", "not");
    defProcStFld("lognot", "gnu.kawa.functions.BitwiseOp", "not");
    defProcStFld("logop", "kawa.lib.numbers");
    defProcStFld("bitwise-bit-set?", "kawa.lib.numbers");
    defProcStFld("logbit?", "kawa.lib.numbers", Compilation.mangleNameIfNeeded("bitwise-bit-set?"));
    defProcStFld("logtest", "kawa.lib.numbers");
    defProcStFld("bitwise-bit-count", "kawa.lib.numbers");
    defProcStFld("logcount", "kawa.lib.numbers");
    defProcStFld("bitwise-copy-bit", "kawa.lib.numbers");
    defProcStFld("bitwise-copy-bit-field", "kawa.lib.numbers");
    defProcStFld("bitwise-bit-field", "kawa.lib.numbers");
    defProcStFld("bit-extract", "kawa.lib.numbers", Compilation.mangleNameIfNeeded("bitwise-bit-field"));
    defProcStFld("bitwise-length", "kawa.lib.numbers");
    defProcStFld("integer-length", "kawa.lib.numbers", "bitwise$Mnlength");
    defProcStFld("bitwise-first-bit-set", "kawa.lib.numbers");
    defProcStFld("bitwise-rotate-bit-field", "kawa.lib.numbers");
    defProcStFld("bitwise-reverse-bit-field", "kawa.lib.numbers");
    defProcStFld("string-upcase!", "kawa.lib.strings");
    defProcStFld("string-downcase!", "kawa.lib.strings");
    defProcStFld("string-capitalize!", "kawa.lib.strings");
    defProcStFld("string-upcase", "kawa.lib.rnrs.unicode");
    defProcStFld("string-downcase", "kawa.lib.rnrs.unicode");
    defProcStFld("string-titlecase", "kawa.lib.rnrs.unicode");
    defProcStFld("string-foldcase", "kawa.lib.rnrs.unicode");
    defProcStFld("string-capitalize", "kawa.lib.strings");
    defSntxStFld("primitive-virtual-method", "kawa.standard.prim_method", "virtual_method");
    defSntxStFld("primitive-static-method", "kawa.standard.prim_method", "static_method");
    defSntxStFld("primitive-interface-method", "kawa.standard.prim_method", "interface_method");
    defSntxStFld("primitive-constructor", "kawa.lib.reflection");
    defSntxStFld("primitive-op1", "kawa.standard.prim_method", "op1");
    defSntxStFld("primitive-get-field", "kawa.lib.reflection");
    defSntxStFld("primitive-set-field", "kawa.lib.reflection");
    defSntxStFld("primitive-get-static", "kawa.lib.reflection");
    defSntxStFld("primitive-set-static", "kawa.lib.reflection");
    defSntxStFld("primitive-array-new", "kawa.lib.reflection");
    defSntxStFld("primitive-array-get", "kawa.lib.reflection");
    defSntxStFld("primitive-array-set", "kawa.lib.reflection");
    defSntxStFld("primitive-array-length", "kawa.lib.reflection");
    defProcStFld("subtype?", "kawa.lib.reflection");
    defProcStFld("primitive-throw", "kawa.standard.prim_throw", "primitiveThrow");
    defSntxStFld("try-finally", "kawa.lib.syntax");
    defSntxStFld("try-catch", "kawa.lib.prim_syntax");
    defProcStFld("throw", "kawa.standard.throw_name", "throwName");
    defProcStFld("catch", "kawa.lib.system");
    defProcStFld("error", "kawa.lib.misc");
    defProcStFld("as", "gnu.kawa.functions.Convert", "as");
    defProcStFld("instance?", "kawa.standard.Scheme", "instanceOf");
    defSntxStFld("synchronized", "kawa.lib.syntax");
    defSntxStFld("object", "kawa.standard.object", "objectSyntax");
    defSntxStFld("define-class", "kawa.standard.define_class", "define_class");
    defSntxStFld("define-simple-class", "kawa.standard.define_class", "define_simple_class");
    defSntxStFld("this", "kawa.standard.thisRef", "thisSyntax");
    defProcStFld("make", "gnu.kawa.reflect.Invoke", "make");
    defProcStFld("slot-ref", "gnu.kawa.reflect.SlotGet", "slotRef");
    defProcStFld("slot-set!", "gnu.kawa.reflect.SlotSet", "set$Mnfield$Ex");
    defProcStFld("field", "gnu.kawa.reflect.SlotGet");
    defProcStFld("class-methods", "gnu.kawa.reflect.ClassMethods", "classMethods");
    defProcStFld("static-field", "gnu.kawa.reflect.SlotGet", "staticField");
    defProcStFld("invoke", "gnu.kawa.reflect.Invoke", "invoke");
    defProcStFld("invoke-static", "gnu.kawa.reflect.Invoke", "invokeStatic");
    defProcStFld("invoke-special", "gnu.kawa.reflect.Invoke", "invokeSpecial");
    defSntxStFld("define-macro", "kawa.lib.syntax");
    defSntxStFld("%define-macro", "kawa.standard.define_syntax", "define_macro");
    defSntxStFld("define-syntax-case", "kawa.lib.syntax");
    defSntxStFld("syntax-case", "kawa.standard.syntax_case", "syntax_case");
    defSntxStFld("%define-syntax", "kawa.standard.define_syntax", "define_syntax");
    defSntxStFld("syntax", "kawa.standard.syntax", "syntax");
    defSntxStFld("quasisyntax", "kawa.standard.syntax", "quasiSyntax");
    defProcStFld("syntax-object->datum", "kawa.lib.std_syntax");
    defProcStFld("datum->syntax-object", "kawa.lib.std_syntax");
    defProcStFld("syntax->expression", "kawa.lib.prim_syntax");
    defProcStFld("syntax-body->expression", "kawa.lib.prim_syntax");
    defProcStFld("generate-temporaries", "kawa.lib.std_syntax");
    defSntxStFld("with-syntax", "kawa.lib.std_syntax");
    defProcStFld("identifier?", "kawa.lib.std_syntax");
    defProcStFld("free-identifier=?", "kawa.lib.std_syntax");
    defProcStFld("syntax-source", "kawa.lib.std_syntax");
    defProcStFld("syntax-line", "kawa.lib.std_syntax");
    defProcStFld("syntax-column", "kawa.lib.std_syntax");
    defSntxStFld("begin-for-syntax", "kawa.lib.std_syntax");
    defSntxStFld("define-for-syntax", "kawa.lib.std_syntax");
    defSntxStFld("include", "kawa.lib.misc_syntax");
    defSntxStFld("include-relative", "kawa.lib.misc_syntax");
    defProcStFld("file-exists?", "kawa.lib.files");
    defProcStFld("file-directory?", "kawa.lib.files");
    defProcStFld("file-readable?", "kawa.lib.files");
    defProcStFld("file-writable?", "kawa.lib.files");
    defProcStFld("delete-file", "kawa.lib.files");
    defProcStFld("system-tmpdir", "kawa.lib.files");
    defProcStFld("make-temporary-file", "kawa.lib.files");
    defProcStFld("rename-file", "kawa.lib.files");
    defProcStFld("copy-file", "kawa.lib.files");
    defProcStFld("create-directory", "kawa.lib.files");
    defProcStFld("->pathname", "kawa.lib.files");
    define("port-char-encoding", Boolean.TRUE);
    define("symbol-read-case", "P");
    defProcStFld("system", "kawa.lib.system");
    defProcStFld("make-process", "kawa.lib.system");
    defProcStFld("tokenize-string-to-string-array", "kawa.lib.system");
    defProcStFld("tokenize-string-using-shell", "kawa.lib.system");
    defProcStFld("command-parse", "kawa.lib.system");
    defProcStFld("process-command-line-assignments", "kawa.lib.system");
    defProcStFld("record-accessor", "kawa.lib.reflection");
    defProcStFld("record-modifier", "kawa.lib.reflection");
    defProcStFld("record-predicate", "kawa.lib.reflection");
    defProcStFld("record-constructor", "kawa.lib.reflection");
    defProcStFld("make-record-type", "kawa.lib.reflection");
    defProcStFld("record-type-descriptor", "kawa.lib.reflection");
    defProcStFld("record-type-name", "kawa.lib.reflection");
    defProcStFld("record-type-field-names", "kawa.lib.reflection");
    defProcStFld("record?", "kawa.lib.reflection");
    defSntxStFld("define-record-type", "gnu.kawa.slib.DefineRecordType");
    defSntxStFld("when", "kawa.lib.syntax");
    defSntxStFld("unless", "kawa.lib.syntax");
    defSntxStFld("fluid-let", "kawa.standard.fluid_let", "fluid_let");
    defSntxStFld("constant-fold", "kawa.standard.constant_fold", "constant_fold");
    defProcStFld("make-parameter", "kawa.lib.parameters");
    defSntxStFld("parameterize", "kawa.lib.parameters");
    defProcStFld("compile-file", "kawa.lib.system");
    defProcStFld("environment-bound?", "kawa.lib.misc");
    defProcStFld("scheme-implementation-version", "kawa.lib.misc");
    defProcStFld("scheme-window", "kawa.lib.windows");
    defSntxStFld("define-procedure", "kawa.lib.std_syntax");
    defProcStFld("add-procedure-properties", "kawa.lib.misc");
    defProcStFld("make-procedure", "gnu.kawa.functions.MakeProcedure", "makeProcedure");
    defProcStFld("procedure-property", "kawa.lib.misc");
    defProcStFld("set-procedure-property!", "kawa.lib.misc");
    defSntxStFld("provide", "kawa.lib.misc_syntax");
    defSntxStFld("test-begin", "kawa.lib.misc_syntax");
    defProcStFld("quantity->number", "kawa.lib.numbers");
    defProcStFld("quantity->unit", "kawa.lib.numbers");
    defProcStFld("make-quantity", "kawa.lib.numbers");
    defSntxStFld("define-namespace", "gnu.kawa.lispexpr.DefineNamespace", "define_namespace");
    defSntxStFld("define-xml-namespace", "gnu.kawa.lispexpr.DefineNamespace", "define_xml_namespace");
    defSntxStFld("define-private-namespace", "gnu.kawa.lispexpr.DefineNamespace", "define_private_namespace");
    defSntxStFld("define-unit", "kawa.standard.define_unit", "define_unit");
    defSntxStFld("define-base-unit", "kawa.standard.define_unit", "define_base_unit");
    defProcStFld("duration", "kawa.lib.numbers");
    defProcStFld("gentemp", "kawa.lib.misc");
    defSntxStFld("defmacro", "kawa.lib.syntax");
    defProcStFld("setter", "gnu.kawa.functions.Setter", "setter");
    defSntxStFld("resource-url", "kawa.lib.misc_syntax");
    defSntxStFld("module-uri", "kawa.lib.misc_syntax");
    defSntxStFld("future", "kawa.lib.thread");
    defProcStFld("sleep", "kawa.lib.thread");
    defProcStFld("runnable", "kawa.lib.thread");
    defSntxStFld("trace", "kawa.lib.trace");
    defSntxStFld("untrace", "kawa.lib.trace");
    defSntxStFld("disassemble", "kawa.lib.trace");
    defProcStFld("format", "gnu.kawa.functions.Format");
    defProcStFld("parse-format", "gnu.kawa.functions.ParseFormat", "parseFormat");
    defProcStFld("make-element", "gnu.kawa.xml.MakeElement", "makeElement");
    defProcStFld("make-attribute", "gnu.kawa.xml.MakeAttribute", "makeAttribute");
    defProcStFld("map-values", "gnu.kawa.functions.ValuesMap", "valuesMap");
    defProcStFld("children", "gnu.kawa.xml.Children", "children");
    defProcStFld("attributes", "gnu.kawa.xml.Attributes");
    defProcStFld("unescaped-data", "gnu.kawa.xml.MakeUnescapedData", "unescapedData");
    defProcStFld("keyword?", "kawa.lib.keywords");
    defProcStFld("keyword->string", "kawa.lib.keywords");
    defProcStFld("string->keyword", "kawa.lib.keywords");
    defSntxStFld("location", "kawa.standard.location", "location");
    defSntxStFld("define-alias", "kawa.standard.define_alias", "define_alias");
    defSntxStFld("define-variable", "kawa.standard.define_variable", "define_variable");
    defSntxStFld("define-member-alias", "kawa.standard.define_member_alias", "define_member_alias");
    defSntxStFld("define-enum", "gnu.kawa.slib.enums");
    defSntxStFld("import", "kawa.lib.syntax");
    defSntxStFld("require", "kawa.standard.require", "require");
    defSntxStFld("module-name", "kawa.standard.module_name", "module_name");
    defSntxStFld("module-extends", "kawa.standard.module_extends", "module_extends");
    defSntxStFld("module-implements", "kawa.standard.module_implements", "module_implements");
    defSntxStFld("module-static", "kawa.standard.module_static", "module_static");
    defSntxStFld("module-export", "kawa.standard.export", "module_export");
    defSntxStFld("export", "kawa.standard.export", "export");
    defSntxStFld("module-compile-options", "kawa.standard.module_compile_options", "module_compile_options");
    defSntxStFld("with-compile-options", "kawa.standard.with_compile_options", "with_compile_options");
    defProcStFld("array?", "kawa.lib.arrays");
    defProcStFld("array-rank", "kawa.lib.arrays");
    defProcStFld("make-array", "kawa.lib.arrays");
    defProcStFld("array", "kawa.lib.arrays");
    defProcStFld("array-start", "kawa.lib.arrays");
    defProcStFld("array-end", "kawa.lib.arrays");
    defProcStFld("shape", "kawa.lib.arrays");
    defProcStFld("array-ref", "gnu.kawa.functions.ArrayRef", "arrayRef");
    defProcStFld("array-set!", "gnu.kawa.functions.ArraySet", "arraySet");
    defProcStFld("share-array", "kawa.lib.arrays");
    defProcStFld("s8vector?", "kawa.lib.uniform");
    defProcStFld("make-s8vector", "kawa.lib.uniform");
    defProcStFld("s8vector", "kawa.lib.uniform");
    defProcStFld("s8vector-length", "kawa.lib.uniform");
    defProcStFld("s8vector-ref", "kawa.lib.uniform");
    defProcStFld("s8vector-set!", "kawa.lib.uniform");
    defProcStFld("s8vector->list", "kawa.lib.uniform");
    defProcStFld("list->s8vector", "kawa.lib.uniform");
    defProcStFld("u8vector?", "kawa.lib.uniform");
    defProcStFld("make-u8vector", "kawa.lib.uniform");
    defProcStFld("u8vector", "kawa.lib.uniform");
    defProcStFld("u8vector-length", "kawa.lib.uniform");
    defProcStFld("u8vector-ref", "kawa.lib.uniform");
    defProcStFld("u8vector-set!", "kawa.lib.uniform");
    defProcStFld("u8vector->list", "kawa.lib.uniform");
    defProcStFld("list->u8vector", "kawa.lib.uniform");
    defProcStFld("s16vector?", "kawa.lib.uniform");
    defProcStFld("make-s16vector", "kawa.lib.uniform");
    defProcStFld("s16vector", "kawa.lib.uniform");
    defProcStFld("s16vector-length", "kawa.lib.uniform");
    defProcStFld("s16vector-ref", "kawa.lib.uniform");
    defProcStFld("s16vector-set!", "kawa.lib.uniform");
    defProcStFld("s16vector->list", "kawa.lib.uniform");
    defProcStFld("list->s16vector", "kawa.lib.uniform");
    defProcStFld("u16vector?", "kawa.lib.uniform");
    defProcStFld("make-u16vector", "kawa.lib.uniform");
    defProcStFld("u16vector", "kawa.lib.uniform");
    defProcStFld("u16vector-length", "kawa.lib.uniform");
    defProcStFld("u16vector-ref", "kawa.lib.uniform");
    defProcStFld("u16vector-set!", "kawa.lib.uniform");
    defProcStFld("u16vector->list", "kawa.lib.uniform");
    defProcStFld("list->u16vector", "kawa.lib.uniform");
    defProcStFld("s32vector?", "kawa.lib.uniform");
    defProcStFld("make-s32vector", "kawa.lib.uniform");
    defProcStFld("s32vector", "kawa.lib.uniform");
    defProcStFld("s32vector-length", "kawa.lib.uniform");
    defProcStFld("s32vector-ref", "kawa.lib.uniform");
    defProcStFld("s32vector-set!", "kawa.lib.uniform");
    defProcStFld("s32vector->list", "kawa.lib.uniform");
    defProcStFld("list->s32vector", "kawa.lib.uniform");
    defProcStFld("u32vector?", "kawa.lib.uniform");
    defProcStFld("make-u32vector", "kawa.lib.uniform");
    defProcStFld("u32vector", "kawa.lib.uniform");
    defProcStFld("u32vector-length", "kawa.lib.uniform");
    defProcStFld("u32vector-ref", "kawa.lib.uniform");
    defProcStFld("u32vector-set!", "kawa.lib.uniform");
    defProcStFld("u32vector->list", "kawa.lib.uniform");
    defProcStFld("list->u32vector", "kawa.lib.uniform");
    defProcStFld("s64vector?", "kawa.lib.uniform");
    defProcStFld("make-s64vector", "kawa.lib.uniform");
    defProcStFld("s64vector", "kawa.lib.uniform");
    defProcStFld("s64vector-length", "kawa.lib.uniform");
    defProcStFld("s64vector-ref", "kawa.lib.uniform");
    defProcStFld("s64vector-set!", "kawa.lib.uniform");
    defProcStFld("s64vector->list", "kawa.lib.uniform");
    defProcStFld("list->s64vector", "kawa.lib.uniform");
    defProcStFld("u64vector?", "kawa.lib.uniform");
    defProcStFld("make-u64vector", "kawa.lib.uniform");
    defProcStFld("u64vector", "kawa.lib.uniform");
    defProcStFld("u64vector-length", "kawa.lib.uniform");
    defProcStFld("u64vector-ref", "kawa.lib.uniform");
    defProcStFld("u64vector-set!", "kawa.lib.uniform");
    defProcStFld("u64vector->list", "kawa.lib.uniform");
    defProcStFld("list->u64vector", "kawa.lib.uniform");
    defProcStFld("f32vector?", "kawa.lib.uniform");
    defProcStFld("make-f32vector", "kawa.lib.uniform");
    defProcStFld("f32vector", "kawa.lib.uniform");
    defProcStFld("f32vector-length", "kawa.lib.uniform");
    defProcStFld("f32vector-ref", "kawa.lib.uniform");
    defProcStFld("f32vector-set!", "kawa.lib.uniform");
    defProcStFld("f32vector->list", "kawa.lib.uniform");
    defProcStFld("list->f32vector", "kawa.lib.uniform");
    defProcStFld("f64vector?", "kawa.lib.uniform");
    defProcStFld("make-f64vector", "kawa.lib.uniform");
    defProcStFld("f64vector", "kawa.lib.uniform");
    defProcStFld("f64vector-length", "kawa.lib.uniform");
    defProcStFld("f64vector-ref", "kawa.lib.uniform");
    defProcStFld("f64vector-set!", "kawa.lib.uniform");
    defProcStFld("f64vector->list", "kawa.lib.uniform");
    defProcStFld("list->f64vector", "kawa.lib.uniform");
    defSntxStFld("cut", "gnu.kawa.slib.cut");
    defSntxStFld("cute", "gnu.kawa.slib.cut");
    defSntxStFld("cond-expand", "kawa.lib.syntax");
    defSntxStFld("%cond-expand", "kawa.lib.syntax");
    defAliasStFld("*print-base*", "gnu.kawa.functions.DisplayFormat", "outBase");
    defAliasStFld("*print-radix*", "gnu.kawa.functions.DisplayFormat", "outRadix");
    defAliasStFld("*print-right-margin*", "gnu.text.PrettyWriter", "lineLengthLoc");
    defAliasStFld("*print-miser-width*", "gnu.text.PrettyWriter", "miserWidthLoc");
    defAliasStFld("html", "gnu.kawa.xml.XmlNamespace", "HTML");
    defAliasStFld("unit", "kawa.standard.Scheme", "unitNamespace");
    defAliasStFld("path", "gnu.kawa.lispexpr.LangObjType", "pathType");
    defAliasStFld("filepath", "gnu.kawa.lispexpr.LangObjType", "filepathType");
    defAliasStFld("URI", "gnu.kawa.lispexpr.LangObjType", "URIType");
    defProcStFld("resolve-uri", "kawa.lib.files");
    defAliasStFld("vector", "gnu.kawa.lispexpr.LangObjType", "vectorType");
    defAliasStFld("string", "gnu.kawa.lispexpr.LangObjType", "stringType");
    defAliasStFld("list", "gnu.kawa.lispexpr.LangObjType", "listType");
    defAliasStFld("regex", "gnu.kawa.lispexpr.LangObjType", "regexType");
    defProcStFld("path?", "kawa.lib.files");
    defProcStFld("filepath?", "kawa.lib.files");
    defProcStFld("URI?", "kawa.lib.files");
    defProcStFld("absolute-path?", "kawa.lib.files");
    defProcStFld("path-scheme", "kawa.lib.files");
    defProcStFld("path-authority", "kawa.lib.files");
    defProcStFld("path-user-info", "kawa.lib.files");
    defProcStFld("path-host", "kawa.lib.files");
    defProcStFld("path-port", "kawa.lib.files");
    defProcStFld("path-file", "kawa.lib.files");
    defProcStFld("path-parent", "kawa.lib.files");
    defProcStFld("path-directory", "kawa.lib.files");
    defProcStFld("path-last", "kawa.lib.files");
    defProcStFld("path-extension", "kawa.lib.files");
    defProcStFld("path-fragment", "kawa.lib.files");
    defProcStFld("path-query", "kawa.lib.files");
    kawaEnvironment.setLocked();
  }
  
  public static void registerEnvironment() {
    Language.setDefaults((Language)getInstance());
  }
  
  public static Type string2Type(String paramString) {
    if (paramString.endsWith("[]")) {
      Type type1 = string2Type(paramString.substring(0, paramString.length() - 2));
      type = type1;
      if (type1 != null)
        ArrayType arrayType = ArrayType.make(type1); 
    } else {
      type = getNamedType(paramString);
    } 
    if (type != null)
      return type; 
    Type type = Language.string2Type(paramString);
    if (type != null)
      types.put(paramString, type); 
    return type;
  }
  
  public Symbol asSymbol(String paramString) {
    return Namespace.EmptyNamespace.getSymbol(paramString);
  }
  
  public Expression checkDefaultBinding(Symbol paramSymbol, Translator paramTranslator) {
    // Byte code:
    //   0: aload_1
    //   1: invokevirtual getNamespace : ()Lgnu/mapping/Namespace;
    //   4: astore #19
    //   6: aload_1
    //   7: invokevirtual getLocalPart : ()Ljava/lang/String;
    //   10: astore #16
    //   12: aload #19
    //   14: instanceof gnu/kawa/xml/XmlNamespace
    //   17: ifeq -> 34
    //   20: aload #19
    //   22: checkcast gnu/kawa/xml/XmlNamespace
    //   25: aload #16
    //   27: invokevirtual get : (Ljava/lang/String;)Ljava/lang/Object;
    //   30: invokestatic getInstance : (Ljava/lang/Object;)Lgnu/expr/QuoteExp;
    //   33: areturn
    //   34: aload #19
    //   36: invokevirtual getName : ()Ljava/lang/String;
    //   39: getstatic kawa/standard/Scheme.unitNamespace : Lgnu/mapping/Namespace;
    //   42: invokevirtual getName : ()Ljava/lang/String;
    //   45: if_acmpne -> 66
    //   48: aload #16
    //   50: invokestatic lookup : (Ljava/lang/String;)Lgnu/math/NamedUnit;
    //   53: astore #17
    //   55: aload #17
    //   57: ifnull -> 66
    //   60: aload #17
    //   62: invokestatic getInstance : (Ljava/lang/Object;)Lgnu/expr/QuoteExp;
    //   65: areturn
    //   66: aload_1
    //   67: invokevirtual toString : ()Ljava/lang/String;
    //   70: astore #17
    //   72: aload #17
    //   74: invokevirtual length : ()I
    //   77: istore #15
    //   79: iload #15
    //   81: ifne -> 86
    //   84: aconst_null
    //   85: areturn
    //   86: iload #15
    //   88: iconst_1
    //   89: if_icmple -> 267
    //   92: aload #17
    //   94: iload #15
    //   96: iconst_1
    //   97: isub
    //   98: invokevirtual charAt : (I)C
    //   101: bipush #63
    //   103: if_icmpne -> 267
    //   106: aload #16
    //   108: invokevirtual length : ()I
    //   111: istore #6
    //   113: iload #6
    //   115: iconst_1
    //   116: if_icmple -> 267
    //   119: aload_2
    //   120: aload #19
    //   122: aload #16
    //   124: iconst_0
    //   125: iload #6
    //   127: iconst_1
    //   128: isub
    //   129: invokevirtual substring : (II)Ljava/lang/String;
    //   132: invokevirtual intern : ()Ljava/lang/String;
    //   135: invokevirtual getSymbol : (Ljava/lang/String;)Lgnu/mapping/Symbol;
    //   138: iconst_0
    //   139: invokevirtual rewrite : (Ljava/lang/Object;Z)Lgnu/expr/Expression;
    //   142: astore #18
    //   144: aload #18
    //   146: instanceof gnu/expr/ReferenceExp
    //   149: ifeq -> 249
    //   152: aload #18
    //   154: checkcast gnu/expr/ReferenceExp
    //   157: invokevirtual getBinding : ()Lgnu/expr/Declaration;
    //   160: astore #20
    //   162: aload #20
    //   164: ifnull -> 182
    //   167: aload #18
    //   169: astore #16
    //   171: aload #20
    //   173: ldc2_w 65536
    //   176: invokevirtual getFlag : (J)Z
    //   179: ifeq -> 185
    //   182: aconst_null
    //   183: astore #16
    //   185: aload #16
    //   187: ifnull -> 267
    //   190: new gnu/expr/LambdaExp
    //   193: dup
    //   194: iconst_1
    //   195: invokespecial <init> : (I)V
    //   198: astore_2
    //   199: aload_2
    //   200: aload_1
    //   201: invokevirtual setSymbol : (Ljava/lang/Object;)V
    //   204: aload_2
    //   205: aconst_null
    //   206: checkcast java/lang/Object
    //   209: invokevirtual addDeclaration : (Ljava/lang/Object;)Lgnu/expr/Declaration;
    //   212: astore_1
    //   213: aload_2
    //   214: new gnu/expr/ApplyExp
    //   217: dup
    //   218: getstatic kawa/standard/Scheme.instanceOf : Lgnu/kawa/reflect/InstanceOf;
    //   221: iconst_2
    //   222: anewarray gnu/expr/Expression
    //   225: dup
    //   226: iconst_0
    //   227: new gnu/expr/ReferenceExp
    //   230: dup
    //   231: aload_1
    //   232: invokespecial <init> : (Lgnu/expr/Declaration;)V
    //   235: aastore
    //   236: dup
    //   237: iconst_1
    //   238: aload #16
    //   240: aastore
    //   241: invokespecial <init> : (Lgnu/mapping/Procedure;[Lgnu/expr/Expression;)V
    //   244: putfield body : Lgnu/expr/Expression;
    //   247: aload_2
    //   248: areturn
    //   249: aload #18
    //   251: astore #16
    //   253: aload #18
    //   255: instanceof gnu/expr/QuoteExp
    //   258: ifne -> 185
    //   261: aconst_null
    //   262: astore #16
    //   264: goto -> 185
    //   267: aload #17
    //   269: iconst_0
    //   270: invokevirtual charAt : (I)C
    //   273: istore #5
    //   275: iload #5
    //   277: bipush #45
    //   279: if_icmpeq -> 299
    //   282: iload #5
    //   284: bipush #43
    //   286: if_icmpeq -> 299
    //   289: iload #5
    //   291: bipush #10
    //   293: invokestatic digit : (CI)I
    //   296: iflt -> 598
    //   299: iconst_0
    //   300: istore #6
    //   302: iconst_0
    //   303: istore #7
    //   305: iload #7
    //   307: iload #15
    //   309: if_icmpge -> 516
    //   312: aload #17
    //   314: iload #7
    //   316: invokevirtual charAt : (I)C
    //   319: istore_3
    //   320: iload_3
    //   321: bipush #10
    //   323: invokestatic digit : (CI)I
    //   326: iflt -> 365
    //   329: iload #6
    //   331: iconst_3
    //   332: if_icmpge -> 347
    //   335: iconst_2
    //   336: istore #6
    //   338: iload #7
    //   340: iconst_1
    //   341: iadd
    //   342: istore #7
    //   344: goto -> 305
    //   347: iload #6
    //   349: iconst_5
    //   350: if_icmpge -> 359
    //   353: iconst_4
    //   354: istore #6
    //   356: goto -> 338
    //   359: iconst_5
    //   360: istore #6
    //   362: goto -> 338
    //   365: iload_3
    //   366: bipush #43
    //   368: if_icmpeq -> 377
    //   371: iload_3
    //   372: bipush #45
    //   374: if_icmpne -> 388
    //   377: iload #6
    //   379: ifne -> 388
    //   382: iconst_1
    //   383: istore #6
    //   385: goto -> 338
    //   388: iload_3
    //   389: bipush #46
    //   391: if_icmpne -> 406
    //   394: iload #6
    //   396: iconst_3
    //   397: if_icmpge -> 406
    //   400: iconst_3
    //   401: istore #6
    //   403: goto -> 338
    //   406: iload_3
    //   407: bipush #101
    //   409: if_icmpeq -> 418
    //   412: iload_3
    //   413: bipush #69
    //   415: if_icmpne -> 516
    //   418: iload #6
    //   420: iconst_2
    //   421: if_icmpeq -> 430
    //   424: iload #6
    //   426: iconst_4
    //   427: if_icmpne -> 516
    //   430: iload #7
    //   432: iconst_1
    //   433: iadd
    //   434: iload #15
    //   436: if_icmpge -> 516
    //   439: iload #7
    //   441: iconst_1
    //   442: iadd
    //   443: istore #9
    //   445: aload #17
    //   447: iload #9
    //   449: invokevirtual charAt : (I)C
    //   452: istore #4
    //   454: iload #4
    //   456: bipush #45
    //   458: if_icmpeq -> 475
    //   461: iload #9
    //   463: istore #8
    //   465: iload #4
    //   467: istore_3
    //   468: iload #4
    //   470: bipush #43
    //   472: if_icmpne -> 507
    //   475: iload #9
    //   477: iconst_1
    //   478: iadd
    //   479: istore #9
    //   481: iload #9
    //   483: istore #8
    //   485: iload #4
    //   487: istore_3
    //   488: iload #9
    //   490: iload #15
    //   492: if_icmpge -> 507
    //   495: aload #17
    //   497: iload #9
    //   499: invokevirtual charAt : (I)C
    //   502: istore_3
    //   503: iload #9
    //   505: istore #8
    //   507: iload_3
    //   508: bipush #10
    //   510: invokestatic digit : (CI)I
    //   513: ifge -> 695
    //   516: iload #7
    //   518: iload #15
    //   520: if_icmpge -> 598
    //   523: iload #6
    //   525: iconst_1
    //   526: if_icmple -> 598
    //   529: new gnu/math/DFloNum
    //   532: dup
    //   533: aload #17
    //   535: iconst_0
    //   536: iload #7
    //   538: invokevirtual substring : (II)Ljava/lang/String;
    //   541: invokespecial <init> : (Ljava/lang/String;)V
    //   544: astore #18
    //   546: iconst_0
    //   547: istore #8
    //   549: new java/util/Vector
    //   552: dup
    //   553: invokespecial <init> : ()V
    //   556: astore #20
    //   558: iload #7
    //   560: istore #6
    //   562: iload #6
    //   564: iload #15
    //   566: if_icmpge -> 1112
    //   569: iload #6
    //   571: iconst_1
    //   572: iadd
    //   573: istore #9
    //   575: aload #17
    //   577: iload #6
    //   579: invokevirtual charAt : (I)C
    //   582: istore #4
    //   584: iload #4
    //   586: bipush #42
    //   588: if_icmpne -> 913
    //   591: iload #9
    //   593: iload #15
    //   595: if_icmpne -> 707
    //   598: iload #15
    //   600: iconst_2
    //   601: if_icmple -> 1300
    //   604: iload #5
    //   606: bipush #60
    //   608: if_icmpne -> 1300
    //   611: aload #17
    //   613: iload #15
    //   615: iconst_1
    //   616: isub
    //   617: invokevirtual charAt : (I)C
    //   620: bipush #62
    //   622: if_icmpne -> 1300
    //   625: aload #17
    //   627: iconst_1
    //   628: iload #15
    //   630: iconst_1
    //   631: isub
    //   632: invokevirtual substring : (II)Ljava/lang/String;
    //   635: astore_1
    //   636: iload #15
    //   638: iconst_2
    //   639: isub
    //   640: istore #7
    //   642: iconst_1
    //   643: istore #8
    //   645: iconst_0
    //   646: istore #6
    //   648: iload #7
    //   650: iconst_2
    //   651: if_icmple -> 1313
    //   654: aload_1
    //   655: iload #7
    //   657: iconst_2
    //   658: isub
    //   659: invokevirtual charAt : (I)C
    //   662: bipush #91
    //   664: if_icmpne -> 1313
    //   667: aload_1
    //   668: iload #7
    //   670: iconst_1
    //   671: isub
    //   672: invokevirtual charAt : (I)C
    //   675: bipush #93
    //   677: if_icmpne -> 1313
    //   680: iload #7
    //   682: iconst_2
    //   683: isub
    //   684: istore #7
    //   686: iload #6
    //   688: iconst_1
    //   689: iadd
    //   690: istore #6
    //   692: goto -> 648
    //   695: iconst_5
    //   696: istore #6
    //   698: iload #8
    //   700: iconst_1
    //   701: iadd
    //   702: istore #7
    //   704: goto -> 338
    //   707: aload #17
    //   709: iload #9
    //   711: invokevirtual charAt : (I)C
    //   714: istore_3
    //   715: iload #9
    //   717: iconst_1
    //   718: iadd
    //   719: istore #6
    //   721: iload #8
    //   723: istore #7
    //   725: iload #6
    //   727: iconst_1
    //   728: isub
    //   729: istore #9
    //   731: iload_3
    //   732: invokestatic isLetter : (C)Z
    //   735: ifne -> 963
    //   738: iload #6
    //   740: iconst_1
    //   741: isub
    //   742: istore #8
    //   744: iload #8
    //   746: iload #9
    //   748: if_icmpeq -> 598
    //   751: aload #20
    //   753: aload #17
    //   755: iload #9
    //   757: iload #8
    //   759: invokevirtual substring : (II)Ljava/lang/String;
    //   762: invokevirtual addElement : (Ljava/lang/Object;)V
    //   765: iconst_0
    //   766: istore #8
    //   768: iload_3
    //   769: bipush #94
    //   771: if_icmpne -> 1576
    //   774: iconst_1
    //   775: istore #8
    //   777: iload #6
    //   779: iload #15
    //   781: if_icmpeq -> 598
    //   784: iload #6
    //   786: iconst_1
    //   787: iadd
    //   788: istore #9
    //   790: aload #17
    //   792: iload #6
    //   794: invokevirtual charAt : (I)C
    //   797: istore_3
    //   798: iload #9
    //   800: istore #6
    //   802: iload #7
    //   804: istore #12
    //   806: iload_3
    //   807: bipush #43
    //   809: if_icmpne -> 997
    //   812: iconst_1
    //   813: istore #8
    //   815: iload #6
    //   817: iload #15
    //   819: if_icmpeq -> 598
    //   822: iload #6
    //   824: iconst_1
    //   825: iadd
    //   826: istore #9
    //   828: aload #17
    //   830: iload #6
    //   832: invokevirtual charAt : (I)C
    //   835: istore_3
    //   836: iload #9
    //   838: istore #6
    //   840: iconst_0
    //   841: istore #11
    //   843: iconst_0
    //   844: istore #10
    //   846: iload #6
    //   848: istore #9
    //   850: iload_3
    //   851: bipush #10
    //   853: invokestatic digit : (CI)I
    //   856: istore #6
    //   858: iload #6
    //   860: ifgt -> 1052
    //   863: iload #9
    //   865: iconst_1
    //   866: isub
    //   867: istore #6
    //   869: iload #11
    //   871: ifne -> 882
    //   874: iconst_1
    //   875: istore #10
    //   877: iload #8
    //   879: ifne -> 598
    //   882: iload #10
    //   884: istore #8
    //   886: iload #12
    //   888: ifeq -> 896
    //   891: iload #10
    //   893: ineg
    //   894: istore #8
    //   896: aload #20
    //   898: iload #8
    //   900: invokestatic make : (I)Lgnu/math/IntNum;
    //   903: invokevirtual addElement : (Ljava/lang/Object;)V
    //   906: iload #7
    //   908: istore #8
    //   910: goto -> 562
    //   913: iload #4
    //   915: istore_3
    //   916: iload #8
    //   918: istore #7
    //   920: iload #9
    //   922: istore #6
    //   924: iload #4
    //   926: bipush #47
    //   928: if_icmpne -> 725
    //   931: iload #9
    //   933: iload #15
    //   935: if_icmpeq -> 598
    //   938: iload #8
    //   940: ifne -> 598
    //   943: iconst_1
    //   944: istore #7
    //   946: aload #17
    //   948: iload #9
    //   950: invokevirtual charAt : (I)C
    //   953: istore_3
    //   954: iload #9
    //   956: iconst_1
    //   957: iadd
    //   958: istore #6
    //   960: goto -> 725
    //   963: iload #6
    //   965: iload #15
    //   967: if_icmpne -> 980
    //   970: iload #6
    //   972: istore #8
    //   974: bipush #49
    //   976: istore_3
    //   977: goto -> 751
    //   980: aload #17
    //   982: iload #6
    //   984: invokevirtual charAt : (I)C
    //   987: istore_3
    //   988: iload #6
    //   990: iconst_1
    //   991: iadd
    //   992: istore #6
    //   994: goto -> 731
    //   997: iload_3
    //   998: bipush #45
    //   1000: if_icmpne -> 1573
    //   1003: iconst_1
    //   1004: istore #8
    //   1006: iload #6
    //   1008: iload #15
    //   1010: if_icmpeq -> 598
    //   1013: iload #6
    //   1015: iconst_1
    //   1016: iadd
    //   1017: istore #10
    //   1019: aload #17
    //   1021: iload #6
    //   1023: invokevirtual charAt : (I)C
    //   1026: istore_3
    //   1027: iload #12
    //   1029: ifne -> 1046
    //   1032: iconst_1
    //   1033: istore #9
    //   1035: iload #10
    //   1037: istore #6
    //   1039: iload #9
    //   1041: istore #12
    //   1043: goto -> 840
    //   1046: iconst_0
    //   1047: istore #9
    //   1049: goto -> 1035
    //   1052: iload #10
    //   1054: bipush #10
    //   1056: imul
    //   1057: iload #6
    //   1059: iadd
    //   1060: istore #13
    //   1062: iload #11
    //   1064: iconst_1
    //   1065: iadd
    //   1066: istore #14
    //   1068: iload #13
    //   1070: istore #10
    //   1072: iload #9
    //   1074: istore #6
    //   1076: iload #14
    //   1078: istore #11
    //   1080: iload #9
    //   1082: iload #15
    //   1084: if_icmpeq -> 869
    //   1087: aload #17
    //   1089: iload #9
    //   1091: invokevirtual charAt : (I)C
    //   1094: istore_3
    //   1095: iload #9
    //   1097: iconst_1
    //   1098: iadd
    //   1099: istore #9
    //   1101: iload #13
    //   1103: istore #10
    //   1105: iload #14
    //   1107: istore #11
    //   1109: goto -> 850
    //   1112: iload #6
    //   1114: iload #15
    //   1116: if_icmpne -> 598
    //   1119: aload #20
    //   1121: invokevirtual size : ()I
    //   1124: iconst_1
    //   1125: ishr
    //   1126: istore #7
    //   1128: iload #7
    //   1130: anewarray gnu/expr/Expression
    //   1133: astore #17
    //   1135: iconst_0
    //   1136: istore #6
    //   1138: iload #6
    //   1140: iload #7
    //   1142: if_icmpge -> 1246
    //   1145: aload #20
    //   1147: iload #6
    //   1149: iconst_2
    //   1150: imul
    //   1151: invokevirtual elementAt : (I)Ljava/lang/Object;
    //   1154: checkcast java/lang/String
    //   1157: astore_1
    //   1158: aload_2
    //   1159: getstatic kawa/standard/Scheme.unitNamespace : Lgnu/mapping/Namespace;
    //   1162: aload_1
    //   1163: invokevirtual intern : ()Ljava/lang/String;
    //   1166: invokevirtual getSymbol : (Ljava/lang/String;)Lgnu/mapping/Symbol;
    //   1169: invokevirtual rewrite : (Ljava/lang/Object;)Lgnu/expr/Expression;
    //   1172: astore #16
    //   1174: aload #20
    //   1176: iload #6
    //   1178: iconst_2
    //   1179: imul
    //   1180: iconst_1
    //   1181: iadd
    //   1182: invokevirtual elementAt : (I)Ljava/lang/Object;
    //   1185: checkcast gnu/math/IntNum
    //   1188: astore #19
    //   1190: aload #16
    //   1192: astore_1
    //   1193: aload #19
    //   1195: invokevirtual longValue : ()J
    //   1198: lconst_1
    //   1199: lcmp
    //   1200: ifeq -> 1231
    //   1203: new gnu/expr/ApplyExp
    //   1206: dup
    //   1207: getstatic kawa/standard/expt.expt : Lkawa/standard/expt;
    //   1210: iconst_2
    //   1211: anewarray gnu/expr/Expression
    //   1214: dup
    //   1215: iconst_0
    //   1216: aload #16
    //   1218: aastore
    //   1219: dup
    //   1220: iconst_1
    //   1221: aload #19
    //   1223: invokestatic getInstance : (Ljava/lang/Object;)Lgnu/expr/QuoteExp;
    //   1226: aastore
    //   1227: invokespecial <init> : (Lgnu/mapping/Procedure;[Lgnu/expr/Expression;)V
    //   1230: astore_1
    //   1231: aload #17
    //   1233: iload #6
    //   1235: aload_1
    //   1236: aastore
    //   1237: iload #6
    //   1239: iconst_1
    //   1240: iadd
    //   1241: istore #6
    //   1243: goto -> 1138
    //   1246: iload #7
    //   1248: iconst_1
    //   1249: if_icmpne -> 1284
    //   1252: aload #17
    //   1254: iconst_0
    //   1255: aaload
    //   1256: astore_1
    //   1257: new gnu/expr/ApplyExp
    //   1260: dup
    //   1261: getstatic gnu/kawa/functions/MultiplyOp.$St : Lgnu/kawa/functions/MultiplyOp;
    //   1264: iconst_2
    //   1265: anewarray gnu/expr/Expression
    //   1268: dup
    //   1269: iconst_0
    //   1270: aload #18
    //   1272: invokestatic getInstance : (Ljava/lang/Object;)Lgnu/expr/QuoteExp;
    //   1275: aastore
    //   1276: dup
    //   1277: iconst_1
    //   1278: aload_1
    //   1279: aastore
    //   1280: invokespecial <init> : (Lgnu/mapping/Procedure;[Lgnu/expr/Expression;)V
    //   1283: areturn
    //   1284: new gnu/expr/ApplyExp
    //   1287: dup
    //   1288: getstatic gnu/kawa/functions/MultiplyOp.$St : Lgnu/kawa/functions/MultiplyOp;
    //   1291: aload #17
    //   1293: invokespecial <init> : (Lgnu/mapping/Procedure;[Lgnu/expr/Expression;)V
    //   1296: astore_1
    //   1297: goto -> 1257
    //   1300: iconst_0
    //   1301: istore #8
    //   1303: iload #15
    //   1305: istore #7
    //   1307: aload #17
    //   1309: astore_1
    //   1310: goto -> 645
    //   1313: aload_1
    //   1314: astore #16
    //   1316: iload #6
    //   1318: ifeq -> 1330
    //   1321: aload_1
    //   1322: iconst_0
    //   1323: iload #7
    //   1325: invokevirtual substring : (II)Ljava/lang/String;
    //   1328: astore #16
    //   1330: aload #16
    //   1332: invokestatic getNamedType : (Ljava/lang/String;)Lgnu/bytecode/Type;
    //   1335: astore #18
    //   1337: aload #18
    //   1339: astore #17
    //   1341: iload #6
    //   1343: ifle -> 1579
    //   1346: iload #8
    //   1348: ifeq -> 1360
    //   1351: aload #18
    //   1353: astore #17
    //   1355: aload #18
    //   1357: ifnonnull -> 1579
    //   1360: aload_2
    //   1361: aload #19
    //   1363: aload #16
    //   1365: invokevirtual intern : ()Ljava/lang/String;
    //   1368: invokevirtual getSymbol : (Ljava/lang/String;)Lgnu/mapping/Symbol;
    //   1371: iconst_0
    //   1372: invokevirtual rewrite : (Ljava/lang/Object;Z)Lgnu/expr/Expression;
    //   1375: aload_2
    //   1376: invokestatic inlineCalls : (Lgnu/expr/Expression;Lgnu/expr/Compilation;)Lgnu/expr/Expression;
    //   1379: astore #19
    //   1381: aload #18
    //   1383: astore #17
    //   1385: aload #19
    //   1387: instanceof gnu/expr/ErrorExp
    //   1390: ifne -> 1579
    //   1393: aload_2
    //   1394: invokevirtual getLanguage : ()Lgnu/expr/Language;
    //   1397: aload #19
    //   1399: invokevirtual getTypeFor : (Lgnu/expr/Expression;)Lgnu/bytecode/Type;
    //   1402: astore #17
    //   1404: goto -> 1579
    //   1407: iload #6
    //   1409: iconst_1
    //   1410: isub
    //   1411: istore #6
    //   1413: iload #6
    //   1415: iflt -> 1428
    //   1418: aload #17
    //   1420: invokestatic make : (Lgnu/bytecode/Type;)Lgnu/bytecode/ArrayType;
    //   1423: astore #17
    //   1425: goto -> 1407
    //   1428: aload #17
    //   1430: invokestatic getInstance : (Ljava/lang/Object;)Lgnu/expr/QuoteExp;
    //   1433: areturn
    //   1434: aload #16
    //   1436: invokestatic lookupType : (Ljava/lang/String;)Lgnu/bytecode/Type;
    //   1439: astore #17
    //   1441: aload #17
    //   1443: instanceof gnu/bytecode/PrimType
    //   1446: ifeq -> 1491
    //   1449: aload #17
    //   1451: invokevirtual getReflectClass : ()Ljava/lang/Class;
    //   1454: astore_2
    //   1455: aload_2
    //   1456: ifnull -> 1571
    //   1459: aload_2
    //   1460: astore #16
    //   1462: iload #6
    //   1464: ifle -> 1547
    //   1467: aload_2
    //   1468: invokestatic make : (Ljava/lang/Class;)Lgnu/bytecode/Type;
    //   1471: astore_2
    //   1472: iload #6
    //   1474: iconst_1
    //   1475: isub
    //   1476: istore #6
    //   1478: iload #6
    //   1480: iflt -> 1541
    //   1483: aload_2
    //   1484: invokestatic make : (Lgnu/bytecode/Type;)Lgnu/bytecode/ArrayType;
    //   1487: astore_2
    //   1488: goto -> 1472
    //   1491: aload #16
    //   1493: astore #17
    //   1495: aload #16
    //   1497: bipush #46
    //   1499: invokevirtual indexOf : (I)I
    //   1502: ifge -> 1532
    //   1505: new java/lang/StringBuilder
    //   1508: dup
    //   1509: invokespecial <init> : ()V
    //   1512: aload_2
    //   1513: getfield classPrefix : Ljava/lang/String;
    //   1516: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1519: aload #16
    //   1521: invokestatic mangleNameIfNeeded : (Ljava/lang/String;)Ljava/lang/String;
    //   1524: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1527: invokevirtual toString : ()Ljava/lang/String;
    //   1530: astore #17
    //   1532: aload #17
    //   1534: invokestatic getContextClass : (Ljava/lang/String;)Ljava/lang/Class;
    //   1537: astore_2
    //   1538: goto -> 1455
    //   1541: aload_2
    //   1542: invokevirtual getReflectClass : ()Ljava/lang/Class;
    //   1545: astore #16
    //   1547: aload #16
    //   1549: invokestatic getInstance : (Ljava/lang/Object;)Lgnu/expr/QuoteExp;
    //   1552: astore_2
    //   1553: aload_2
    //   1554: areturn
    //   1555: astore_2
    //   1556: aload_1
    //   1557: invokestatic getContextPackage : (Ljava/lang/String;)Ljava/lang/Package;
    //   1560: astore_1
    //   1561: aload_1
    //   1562: ifnull -> 1571
    //   1565: aload_1
    //   1566: invokestatic getInstance : (Ljava/lang/Object;)Lgnu/expr/QuoteExp;
    //   1569: areturn
    //   1570: astore_1
    //   1571: aconst_null
    //   1572: areturn
    //   1573: goto -> 840
    //   1576: goto -> 802
    //   1579: aload #17
    //   1581: ifnull -> 1434
    //   1584: goto -> 1407
    // Exception table:
    //   from	to	target	type
    //   1330	1337	1555	java/lang/ClassNotFoundException
    //   1330	1337	1570	java/lang/Throwable
    //   1360	1381	1555	java/lang/ClassNotFoundException
    //   1360	1381	1570	java/lang/Throwable
    //   1385	1404	1555	java/lang/ClassNotFoundException
    //   1385	1404	1570	java/lang/Throwable
    //   1418	1425	1555	java/lang/ClassNotFoundException
    //   1418	1425	1570	java/lang/Throwable
    //   1428	1434	1555	java/lang/ClassNotFoundException
    //   1428	1434	1570	java/lang/Throwable
    //   1434	1455	1555	java/lang/ClassNotFoundException
    //   1434	1455	1570	java/lang/Throwable
    //   1467	1472	1555	java/lang/ClassNotFoundException
    //   1467	1472	1570	java/lang/Throwable
    //   1483	1488	1555	java/lang/ClassNotFoundException
    //   1483	1488	1570	java/lang/Throwable
    //   1495	1532	1555	java/lang/ClassNotFoundException
    //   1495	1532	1570	java/lang/Throwable
    //   1532	1538	1555	java/lang/ClassNotFoundException
    //   1532	1538	1570	java/lang/Throwable
    //   1541	1547	1555	java/lang/ClassNotFoundException
    //   1541	1547	1570	java/lang/Throwable
    //   1547	1553	1555	java/lang/ClassNotFoundException
    //   1547	1553	1570	java/lang/Throwable
  }
  
  public ReadTable createReadTable() {
    ReadTable readTable = ReadTable.createInitial();
    readTable.postfixLookupOperator = ':';
    ReaderDispatch readerDispatch = (ReaderDispatch)readTable.lookup(35);
    readerDispatch.set(39, new ReaderQuote(asSymbol("syntax")));
    readerDispatch.set(96, new ReaderQuote(asSymbol("quasisyntax")));
    readerDispatch.set(44, ReaderDispatchMisc.getInstance());
    readTable.putReaderCtorFld("path", "gnu.kawa.lispexpr.LangObjType", "pathType");
    readTable.putReaderCtorFld("filepath", "gnu.kawa.lispexpr.LangObjType", "filepathType");
    readTable.putReaderCtorFld("URI", "gnu.kawa.lispexpr.LangObjType", "URIType");
    readTable.putReaderCtor("symbol", (Type)ClassType.make("gnu.mapping.Symbol"));
    readTable.putReaderCtor("namespace", (Type)ClassType.make("gnu.mapping.Namespace"));
    readTable.putReaderCtorFld("duration", "kawa.lib.numbers", "duration");
    readTable.setFinalColonIsKeyword(true);
    return readTable;
  }
  
  public String formatType(Type paramType) {
    if (typeToStringMap == null) {
      typeToStringMap = new HashMap<Type, String>();
      for (Map.Entry<String, Type> entry : getTypeMap().entrySet()) {
        String str1 = (String)entry.getKey();
        Type type1 = (Type)entry.getValue();
        typeToStringMap.put(type1, str1);
        Type type2 = type1.getImplementationType();
        if (type2 != type1)
          typeToStringMap.put(type2, str1); 
      } 
    } 
    String str = typeToStringMap.get(paramType);
    return (str != null) ? str : super.formatType(paramType);
  }
  
  public AbstractFormat getFormat(boolean paramBoolean) {
    return paramBoolean ? writeFormat : displayFormat;
  }
  
  public String getName() {
    return "Scheme";
  }
  
  public int getNamespaceOf(Declaration paramDeclaration) {
    return 3;
  }
  
  public Type getTypeFor(Class paramClass) {
    String str = paramClass.getName();
    return (Type)(paramClass.isPrimitive() ? getNamedType(str) : ("java.lang.String".equals(str) ? Type.toStringType : ("gnu.math.IntNum".equals(str) ? LangObjType.integerType : ("gnu.math.DFloNum".equals(str) ? LangObjType.dflonumType : ("gnu.math.RatNum".equals(str) ? LangObjType.rationalType : ("gnu.math.RealNum".equals(str) ? LangObjType.realType : ("gnu.math.Numeric".equals(str) ? LangObjType.numericType : ("gnu.lists.FVector".equals(str) ? LangObjType.vectorType : ("gnu.lists.LList".equals(str) ? LangObjType.listType : ("gnu.text.Path".equals(str) ? LangObjType.pathType : ("gnu.text.URIPath".equals(str) ? LangObjType.URIType : ("gnu.text.FilePath".equals(str) ? LangObjType.filepathType : ("java.lang.Class".equals(str) ? LangObjType.typeClass : ("gnu.bytecode.Type".equals(str) ? LangObjType.typeType : ("gnu.bytecode.ClassType".equals(str) ? LangObjType.typeClassType : Type.make(paramClass))))))))))))))));
  }
  
  public Type getTypeFor(String paramString) {
    return string2Type(paramString);
  }
  
  public Expression makeApply(Expression paramExpression, Expression[] paramArrayOfExpression) {
    Expression[] arrayOfExpression = new Expression[paramArrayOfExpression.length + 1];
    arrayOfExpression[0] = paramExpression;
    System.arraycopy(paramArrayOfExpression, 0, arrayOfExpression, 1, paramArrayOfExpression.length);
    return (Expression)new ApplyExp((Expression)new ReferenceExp(applyFieldDecl), arrayOfExpression);
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/kawa/standard/Scheme.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */