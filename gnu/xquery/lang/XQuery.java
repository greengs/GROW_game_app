package gnu.xquery.lang;

import gnu.bytecode.ClassType;
import gnu.bytecode.ObjectType;
import gnu.bytecode.Type;
import gnu.expr.ApplicationMainSupport;
import gnu.expr.BeginExp;
import gnu.expr.Compilation;
import gnu.expr.Declaration;
import gnu.expr.Expression;
import gnu.expr.LambdaExp;
import gnu.expr.Language;
import gnu.expr.ModuleBody;
import gnu.expr.ModuleExp;
import gnu.expr.NameLookup;
import gnu.expr.QuoteExp;
import gnu.expr.ReferenceExp;
import gnu.expr.ScopeExp;
import gnu.kawa.functions.ConstantFunction0;
import gnu.kawa.reflect.ClassMethods;
import gnu.kawa.xml.XDataType;
import gnu.kawa.xml.XIntegerType;
import gnu.kawa.xml.XStringType;
import gnu.kawa.xml.XTimeType;
import gnu.lists.Consumer;
import gnu.mapping.CallContext;
import gnu.mapping.CharArrayInPort;
import gnu.mapping.Environment;
import gnu.mapping.EnvironmentKey;
import gnu.mapping.InPort;
import gnu.mapping.MethodProc;
import gnu.mapping.Namespace;
import gnu.mapping.Procedure;
import gnu.mapping.Symbol;
import gnu.mapping.WrongType;
import gnu.math.DFloNum;
import gnu.math.IntNum;
import gnu.math.Numeric;
import gnu.text.Char;
import gnu.text.Lexer;
import gnu.text.SourceMessages;
import gnu.text.SyntaxException;
import gnu.xml.XMLPrinter;
import gnu.xquery.util.BooleanValue;
import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.util.Vector;
import kawa.standard.Scheme;

public class XQuery extends Language {
  public static final String DEFAULT_ELEMENT_PREFIX;
  
  public static final String DEFAULT_FUNCTION_PREFIX = "(functions)";
  
  public static final String KAWA_FUNCTION_NAMESPACE = "http://kawa.gnu.org/";
  
  public static final String LOCAL_NAMESPACE = "http://www.w3.org/2005/xquery-local-functions";
  
  public static final int PARSE_WITH_FOCUS = 65536;
  
  public static final String QEXO_FUNCTION_NAMESPACE = "http://qexo.gnu.org/";
  
  public static final String SCHEMA_INSTANCE_NAMESPACE = "http://www.w3.org/2001/XMLSchema-instance";
  
  public static final String SCHEMA_NAMESPACE = "http://www.w3.org/2001/XMLSchema";
  
  public static final int VARIADIC_FUNCTION_NAMESPACE = -2;
  
  public static final String XHTML_NAMESPACE = "http://www.w3.org/1999/xhtml";
  
  public static final String XQUERY_FUNCTION_NAMESPACE = "http://www.w3.org/2005/xpath-functions";
  
  static boolean charIsInt;
  
  public static final Namespace[] defaultFunctionNamespacePath;
  
  static int envCounter;
  
  public static Environment extensionsEnvEnv;
  
  public static QuoteExp falseExp;
  
  public static final ConstantFunction0 falseFunction;
  
  public static final XQuery instance;
  
  public static final Namespace kawaFunctionNamespace;
  
  public static final Namespace qexoFunctionNamespace;
  
  public static QuoteExp trueExp;
  
  public static final ConstantFunction0 trueFunction;
  
  static Object[] typeMap;
  
  public static final Environment xqEnvironment;
  
  public static final Namespace xqueryFunctionNamespace = Namespace.valueOf("http://www.w3.org/2005/xpath-functions");
  
  Namespace defaultNamespace = xqueryFunctionNamespace;
  
  static {
    kawaFunctionNamespace = Namespace.valueOf("http://kawa.gnu.org/");
    qexoFunctionNamespace = Namespace.valueOf("http://qexo.gnu.org/");
    defaultFunctionNamespacePath = new Namespace[] { qexoFunctionNamespace, xqueryFunctionNamespace, Namespace.EmptyNamespace, kawaFunctionNamespace };
    charIsInt = false;
    DEFAULT_ELEMENT_PREFIX = null;
    envCounter = 0;
    extensionsEnvEnv = Environment.getInstance("http://kawa.gnu.org/");
    xqEnvironment = (Environment)Environment.make("http://www.w3.org/2005/xpath-functions");
    instance = new XQuery();
    instance.initXQuery();
    falseExp = new QuoteExp(Boolean.FALSE, (Type)XDataType.booleanType);
    trueExp = new QuoteExp(Boolean.TRUE, (Type)XDataType.booleanType);
    falseFunction = new ConstantFunction0("false", falseExp);
    trueFunction = new ConstantFunction0("true", trueExp);
    typeMap = new Object[] { 
        "string", XDataType.stringType, "untypedAtomic", XDataType.untypedAtomicType, "boolean", XDataType.booleanType, "integer", XIntegerType.integerType, "long", XIntegerType.longType, 
        "int", XIntegerType.intType, "short", XIntegerType.shortType, "byte", XIntegerType.byteType, "unsignedLong", XIntegerType.unsignedLongType, "unsignedInt", XIntegerType.unsignedIntType, 
        "unsignedShort", XIntegerType.unsignedShortType, "unsignedByte", XIntegerType.unsignedByteType, "positiveInteger", XIntegerType.positiveIntegerType, "nonPositiveInteger", XIntegerType.nonPositiveIntegerType, "negativeInteger", XIntegerType.negativeIntegerType, 
        "nonNegativeInteger", XIntegerType.nonNegativeIntegerType, "date", XTimeType.dateType, "dateTime", XTimeType.dateTimeType, "time", XTimeType.timeType, "duration", XTimeType.durationType, 
        "yearMonthDuration", XTimeType.yearMonthDurationType, "dayTimeDuration", XTimeType.dayTimeDurationType, "gYearMonth", XTimeType.gYearMonthType, "gYear", XTimeType.gYearType, "gMonthDay", XTimeType.gMonthDayType, 
        "gDay", XTimeType.gDayType, "gMonth", XTimeType.gMonthType, "decimal", XDataType.decimalType, "float", XDataType.floatType, "double", XDataType.doubleType, 
        "anyURI", XDataType.anyURIType, "hexBinary", XDataType.hexBinaryType, "base64Binary", XDataType.base64BinaryType, "NOTATION", XDataType.NotationType, "QName", "gnu.mapping.Symbol", 
        "normalizedString", XStringType.normalizedStringType, "token", XStringType.tokenType, "language", XStringType.languageType, "NMTOKEN", XStringType.NMTOKENType, "Name", XStringType.NameType, 
        "NCName", XStringType.NCNameType, "ID", XStringType.IDType, "IDREF", XStringType.IDREFType, "ENTITY", XStringType.ENTITYType, "anyAtomicType", XDataType.anyAtomicType, 
        "anySimpleType", XDataType.anySimpleType, "untyped", XDataType.untypedType, "anyType", Type.objectType };
  }
  
  public static char asChar(Object paramObject) {
    byte b;
    if (paramObject instanceof Char)
      return ((Char)paramObject).charValue(); 
    if (paramObject instanceof Numeric) {
      b = ((Numeric)paramObject).intValue();
    } else {
      b = -1;
    } 
    if (b < 0 || b > 65535)
      throw new ClassCastException("not a character value"); 
    return (char)b;
  }
  
  public static Numeric asNumber(Object paramObject) {
    return (Numeric)((paramObject instanceof Char) ? IntNum.make(((Char)paramObject).intValue()) : paramObject);
  }
  
  public static Object getExternal(Symbol paramSymbol, Object paramObject) {
    Environment environment = Environment.getCurrent();
    Object object2 = environment.get(paramSymbol, null, null);
    Object object1 = object2;
    if (object2 == null)
      object1 = environment.get(Symbol.makeWithUnknownNamespace(paramSymbol.getLocalName(), paramSymbol.getPrefix()), null, null); 
    if (object1 == null)
      throw new RuntimeException("unbound external " + paramSymbol); 
    if (paramObject == null)
      return object1; 
    if (paramObject instanceof XDataType)
      return ((XDataType)paramObject).cast(object1); 
    if (paramObject instanceof ClassType) {
      object2 = ((ClassType)paramObject).getName();
      if ("gnu.math.IntNum".equals(object2))
        return IntNum.valueOf(object1.toString()); 
      if ("gnu.math.RealNum".equals(object2))
        return DFloNum.make(Double.parseDouble(object1.toString())); 
    } 
    try {
      return ((Type)paramObject).coerceFromObject(object1);
    } catch (ClassCastException classCastException) {
      throw new WrongType(paramSymbol.toString(), -2, object1, paramObject.toString());
    } 
  }
  
  public static XQuery getInstance() {
    return instance;
  }
  
  public static Type getStandardType(String paramString) {
    int i = typeMap.length;
    while (true) {
      int j = i - 2;
      if (j >= 0) {
        i = j;
        if (typeMap[j].equals(paramString)) {
          Object object = typeMap[j + 1];
          return (object instanceof String) ? Scheme.string2Type((String)object) : (Type)object;
        } 
        continue;
      } 
      return null;
    } 
  }
  
  private void initXQuery() {
    ModuleBody.setMainPrintValues(true);
    defProcStFld("unescaped-data", "gnu.kawa.xml.MakeUnescapedData", "unescapedData");
    defProcStFld("item-at", "gnu.xquery.util.ItemAt", "itemAt");
    defProcStFld("count", "gnu.kawa.functions.CountValues", "countValues");
    define_method("sum", "gnu.xquery.util.Reduce", "sum");
    defProcStFld("avg", "gnu.xquery.util.Average", "avg");
    defProcStFld("sublist", "gnu.xquery.util.SubList", "subList");
    defProcStFld("subsequence", "gnu.xquery.util.SubList", "subList");
    define_method("empty", "gnu.xquery.util.SequenceUtils", "isEmptySequence");
    define_method("exists", "gnu.xquery.util.SequenceUtils", "exists");
    define_method("insert-before", "gnu.xquery.util.SequenceUtils", "insertBefore$X");
    define_method("remove", "gnu.xquery.util.SequenceUtils", "remove$X");
    define_method("reverse", "gnu.xquery.util.SequenceUtils", "reverse$X");
    defProcStFld("false", "gnu.xquery.lang.XQuery", "falseFunction");
    defProcStFld("true", "gnu.xquery.lang.XQuery", "trueFunction");
    defProcStFld("boolean", "gnu.xquery.util.BooleanValue", "booleanValue");
    define_method("trace", "gnu.xquery.util.Debug", "trace");
    define_method("error", "gnu.xquery.util.XQException", "error");
    defProcStFld("write-to", "gnu.kawa.xml.WriteTo", "writeTo");
    defProcStFld("write-to-if-changed", "gnu.kawa.xml.WriteTo", "writeToIfChanged");
    defProcStFld("iterator-items", "gnu.kawa.xml.IteratorItems", "iteratorItems");
    defProcStFld("list-items", "gnu.kawa.xml.ListItems", "listItems");
    define_method("node-name", "gnu.xquery.util.NodeUtils", "nodeName");
    define_method("nilled", "gnu.xquery.util.NodeUtils", "nilled");
    define_method("data", "gnu.xquery.util.NodeUtils", "data$X");
    define_method("lower-case", "gnu.xquery.util.StringUtils", "lowerCase");
    define_method("upper-case", "gnu.xquery.util.StringUtils", "upperCase");
    define_method("substring", "gnu.xquery.util.StringUtils", "substring");
    define_method("string-length", "gnu.xquery.util.StringUtils", "stringLength");
    define_method("substring-before", "gnu.xquery.util.StringUtils", "substringBefore");
    define_method("substring-after", "gnu.xquery.util.StringUtils", "substringAfter");
    define_method("translate", "gnu.xquery.util.StringUtils", "translate");
    define_method("encode-for-uri", "gnu.xquery.util.StringUtils", "encodeForUri");
    define_method("iri-to-uri", "gnu.xquery.util.StringUtils", "iriToUri");
    define_method("escape-html-uri", "gnu.xquery.util.StringUtils", "escapeHtmlUri");
    define_method("contains", "gnu.xquery.util.StringUtils", "contains");
    define_method("starts-with", "gnu.xquery.util.StringUtils", "startsWith");
    define_method("ends-with", "gnu.xquery.util.StringUtils", "endsWith");
    define_method("codepoint-equal", "gnu.xquery.util.StringUtils", "codepointEqual");
    define_method("normalize-unicode", "gnu.xquery.util.StringUtils", "normalizeUnicode");
    define_method("string-join", "gnu.xquery.util.StringUtils", "stringJoin");
    define_method("concat", "gnu.xquery.util.StringUtils", "concat$V");
    define_method("matches", "gnu.xquery.util.StringUtils", "matches");
    define_method("replace", "gnu.xquery.util.StringUtils", "replace");
    define_method("tokenize", "gnu.xquery.util.StringUtils", "tokenize$X");
    define_method("string-to-codepoints", "gnu.xquery.util.StringUtils", "stringToCodepoints$X");
    define_method("codepoints-to-string", "gnu.xquery.util.StringUtils", "codepointsToString");
    define_method("abs", "gnu.xquery.util.NumberValue", "abs");
    define_method("floor", "gnu.xquery.util.NumberValue", "floor");
    define_method("ceiling", "gnu.xquery.util.NumberValue", "ceiling");
    define_method("round", "gnu.xquery.util.NumberValue", "round");
    define_method("round-half-to-even", "gnu.xquery.util.NumberValue", "roundHalfToEven");
    define_method("QName", "gnu.xquery.util.QNameUtils", "makeQName");
    define_method("resolve-QName", "gnu.xquery.util.QNameUtils", "resolveQNameUsingElement");
    define_method("prefix-from-QName", "gnu.xquery.util.QNameUtils", "prefixFromQName");
    define_method("local-name-from-QName", "gnu.xquery.util.QNameUtils", "localNameFromQName");
    define_method("namespace-uri-from-QName", "gnu.xquery.util.QNameUtils", "namespaceURIFromQName");
    define_method("namespace-uri-for-prefix", "gnu.xquery.util.QNameUtils", "namespaceURIForPrefix");
    define_method("in-scope-prefixes", "gnu.xquery.util.NodeUtils", "inScopePrefixes$X");
    define_method("document-uri", "gnu.xquery.util.NodeUtils", "documentUri");
    define_method("years-from-duration", "gnu.xquery.util.TimeUtils", "yearsFromDuration");
    define_method("months-from-duration", "gnu.xquery.util.TimeUtils", "monthsFromDuration");
    define_method("days-from-duration", "gnu.xquery.util.TimeUtils", "daysFromDuration");
    define_method("hours-from-duration", "gnu.xquery.util.TimeUtils", "hoursFromDuration");
    define_method("minutes-from-duration", "gnu.xquery.util.TimeUtils", "minutesFromDuration");
    define_method("seconds-from-duration", "gnu.xquery.util.TimeUtils", "secondsFromDuration");
    define_method("year-from-dateTime", "gnu.xquery.util.TimeUtils", "yearFromDateTime");
    define_method("month-from-dateTime", "gnu.xquery.util.TimeUtils", "monthFromDateTime");
    define_method("day-from-dateTime", "gnu.xquery.util.TimeUtils", "dayFromDateTime");
    define_method("hours-from-dateTime", "gnu.xquery.util.TimeUtils", "hoursFromDateTime");
    define_method("minutes-from-dateTime", "gnu.xquery.util.TimeUtils", "minutesFromDateTime");
    define_method("seconds-from-dateTime", "gnu.xquery.util.TimeUtils", "secondsFromDateTime");
    define_method("timezone-from-dateTime", "gnu.xquery.util.TimeUtils", "timezoneFromDateTime");
    define_method("year-from-date", "gnu.xquery.util.TimeUtils", "yearFromDate");
    define_method("month-from-date", "gnu.xquery.util.TimeUtils", "monthFromDate");
    define_method("day-from-date", "gnu.xquery.util.TimeUtils", "dayFromDate");
    define_method("timezone-from-date", "gnu.xquery.util.TimeUtils", "timezoneFromDate");
    define_method("hours-from-time", "gnu.xquery.util.TimeUtils", "hoursFromTime");
    define_method("minutes-from-time", "gnu.xquery.util.TimeUtils", "minutesFromTime");
    define_method("seconds-from-time", "gnu.xquery.util.TimeUtils", "secondsFromTime");
    define_method("timezone-from-time", "gnu.xquery.util.TimeUtils", "timezoneFromTime");
    define_method("adjust-dateTime-to-timezone", "gnu.xquery.util.TimeUtils", "adjustDateTimeToTimezone");
    define_method("adjust-date-to-timezone", "gnu.xquery.util.TimeUtils", "adjustDateToTimezone");
    define_method("adjust-time-to-timezone", "gnu.xquery.util.TimeUtils", "adjustTimeToTimezone");
    define_method("dateTime", "gnu.xquery.util.TimeUtils", "dateTime");
    define_method("current-dateTime", "gnu.xquery.util.TimeUtils", "currentDateTime");
    define_method("current-date", "gnu.xquery.util.TimeUtils", "currentDate");
    define_method("current-time", "gnu.xquery.util.TimeUtils", "currentTime");
    define_method("implicit-timezone", "gnu.xquery.util.TimeUtils", "implicitTimezone");
    define_method("zero-or-one", "gnu.xquery.util.SequenceUtils", "zeroOrOne");
    define_method("one-or-more", "gnu.xquery.util.SequenceUtils", "oneOrMore");
    define_method("exactly-one", "gnu.xquery.util.SequenceUtils", "exactlyOne");
    defProcStFld("distinct-nodes", "gnu.kawa.xml.SortNodes", "sortNodes");
    defProcStFld("children", "gnu.kawa.xml.Children", "children");
    define_method("not", "gnu.xquery.util.BooleanValue", "not");
    this.defaultNamespace = qexoFunctionNamespace;
    defProcStFld("response-header", "gnu.kawa.servlet.HTTP");
    defProcStFld("response-content-type", "gnu.kawa.servlet.HTTP");
    defProcStFld("response-status", "gnu.kawa.servlet.HTTP");
    defProcStFld("error-response", "gnu.kawa.servlet.HTTP");
    defProcStFld("current-servlet", "gnu.kawa.servlet.HTTP");
    defProcStFld("current-servlet-context", "gnu.kawa.servlet.HTTP");
    defProcStFld("current-servlet-config", "gnu.kawa.servlet.HTTP");
    defProcStFld("servlet-context-realpath", "gnu.kawa.servlet.HTTP");
    defProcStFld("get-response", "gnu.kawa.servlet.HTTP");
    defProcStFld("get-request", "gnu.kawa.servlet.HTTP");
    defProcStFld("request-method", "gnu.kawa.servlet.HTTP");
    defProcStFld("request-uri", "gnu.kawa.servlet.HTTP");
    defProcStFld("request-url", "gnu.kawa.servlet.HTTP");
    defProcStFld("request-path-info", "gnu.kawa.servlet.HTTP");
    defProcStFld("request-path-translated", "gnu.kawa.servlet.HTTP");
    defProcStFld("request-servlet-path", "gnu.kawa.servlet.HTTP");
    defProcStFld("request-query-string", "gnu.kawa.servlet.HTTP");
    defProcStFld("request-parameter", "gnu.kawa.servlet.HTTP");
    defProcStFld("request-parameters", "gnu.kawa.servlet.HTTP");
    this.defaultNamespace = xqueryFunctionNamespace;
  }
  
  public static String makeClassName(String paramString) {
    String str = paramString.replace(File.separatorChar, '/');
    int i = str.lastIndexOf('/');
    paramString = str;
    if (i >= 0)
      paramString = str.substring(i + 1); 
    i = paramString.lastIndexOf('.');
    str = paramString;
    if (i >= 0)
      str = paramString.substring(0, i); 
    return Compilation.mangleNameIfNeeded(str);
  }
  
  public static String mangle(String paramString) {
    StringBuffer stringBuffer = new StringBuffer();
    mangle(paramString, 0, paramString.length(), stringBuffer, 'U');
    return stringBuffer.toString();
  }
  
  static void mangle(String paramString, int paramInt1, int paramInt2, StringBuffer paramStringBuffer, char paramChar) {
    // Byte code:
    //   0: bipush #80
    //   2: istore #7
    //   4: aload_3
    //   5: invokevirtual length : ()I
    //   8: istore #10
    //   10: iconst_0
    //   11: istore #8
    //   13: iload #8
    //   15: iload_2
    //   16: if_icmpge -> 297
    //   19: aload_0
    //   20: iload_1
    //   21: iload #8
    //   23: iadd
    //   24: invokevirtual charAt : (I)C
    //   27: istore #6
    //   29: iload #8
    //   31: iconst_1
    //   32: iadd
    //   33: istore #9
    //   35: iload #6
    //   37: invokestatic isUpperCase : (C)Z
    //   40: ifeq -> 148
    //   43: iload #7
    //   45: bipush #85
    //   47: if_icmpne -> 70
    //   50: iload #9
    //   52: iload_2
    //   53: if_icmpge -> 142
    //   56: aload_0
    //   57: iload_1
    //   58: iload #9
    //   60: iadd
    //   61: invokevirtual charAt : (I)C
    //   64: invokestatic isLowerCase : (C)Z
    //   67: ifeq -> 142
    //   70: iconst_1
    //   71: istore #8
    //   73: bipush #85
    //   75: istore #7
    //   77: iload #8
    //   79: ifne -> 93
    //   82: iload #6
    //   84: istore #5
    //   86: iload #4
    //   88: bipush #95
    //   90: if_icmpne -> 128
    //   93: iload #8
    //   95: ifeq -> 121
    //   98: iload #4
    //   100: bipush #95
    //   102: if_icmpne -> 121
    //   105: aload_3
    //   106: invokevirtual length : ()I
    //   109: iload #10
    //   111: if_icmple -> 121
    //   114: aload_3
    //   115: bipush #95
    //   117: invokevirtual append : (C)Ljava/lang/StringBuffer;
    //   120: pop
    //   121: iload #6
    //   123: invokestatic toUpperCase : (C)C
    //   126: istore #5
    //   128: aload_3
    //   129: iload #5
    //   131: invokevirtual append : (C)Ljava/lang/StringBuffer;
    //   134: pop
    //   135: iload #9
    //   137: istore #8
    //   139: goto -> 13
    //   142: iconst_0
    //   143: istore #8
    //   145: goto -> 73
    //   148: iload #6
    //   150: invokestatic isLowerCase : (C)Z
    //   153: ifeq -> 186
    //   156: iload #7
    //   158: bipush #76
    //   160: if_icmpne -> 170
    //   163: iload #7
    //   165: bipush #85
    //   167: if_icmpeq -> 180
    //   170: iconst_1
    //   171: istore #8
    //   173: bipush #76
    //   175: istore #7
    //   177: goto -> 77
    //   180: iconst_0
    //   181: istore #8
    //   183: goto -> 173
    //   186: iload #6
    //   188: invokestatic isLetter : (C)Z
    //   191: ifeq -> 217
    //   194: iload #7
    //   196: bipush #79
    //   198: if_icmpeq -> 211
    //   201: iconst_1
    //   202: istore #8
    //   204: bipush #79
    //   206: istore #7
    //   208: goto -> 77
    //   211: iconst_0
    //   212: istore #8
    //   214: goto -> 204
    //   217: iload #6
    //   219: invokestatic isDigit : (C)Z
    //   222: ifeq -> 248
    //   225: iload #7
    //   227: bipush #68
    //   229: if_icmpeq -> 242
    //   232: iconst_1
    //   233: istore #8
    //   235: bipush #68
    //   237: istore #7
    //   239: goto -> 77
    //   242: iconst_0
    //   243: istore #8
    //   245: goto -> 235
    //   248: iload #6
    //   250: invokestatic isJavaIdentifierPart : (C)Z
    //   253: ifeq -> 286
    //   256: iload #7
    //   258: bipush #68
    //   260: if_icmpeq -> 280
    //   263: iload #7
    //   265: bipush #77
    //   267: if_icmpeq -> 280
    //   270: iconst_1
    //   271: istore #8
    //   273: bipush #77
    //   275: istore #7
    //   277: goto -> 77
    //   280: iconst_0
    //   281: istore #8
    //   283: goto -> 273
    //   286: bipush #80
    //   288: istore #7
    //   290: iload #9
    //   292: istore #8
    //   294: goto -> 13
    //   297: return
  }
  
  public static int namespaceForFunctions(int paramInt) {
    return paramInt << 2 | 0x2;
  }
  
  public static void registerEnvironment() {
    ApplicationMainSupport.processCommandLinePropertyAssignments = true;
    Language.setDefaults(instance);
  }
  
  public Object applyWithFocus(Procedure paramProcedure, Object paramObject) throws Throwable {
    CallContext callContext = CallContext.getInstance();
    int i = callContext.startFromContext();
    try {
      applyWithFocus$X(paramProcedure, paramObject, callContext);
      return callContext.getFromContext(i);
    } catch (Throwable throwable) {
      callContext.cleanupFromContext(i);
      throw throwable;
    } 
  }
  
  public Object applyWithFocus(Procedure paramProcedure, Object paramObject, int paramInt1, int paramInt2) throws Throwable {
    CallContext callContext = CallContext.getInstance();
    int i = callContext.startFromContext();
    try {
      paramProcedure.check3(paramObject, IntNum.make(paramInt1), IntNum.make(paramInt2), callContext);
      return callContext.getFromContext(i);
    } catch (Throwable throwable) {
      callContext.cleanupFromContext(i);
      throw throwable;
    } 
  }
  
  public void applyWithFocus(Procedure paramProcedure, Object paramObject, int paramInt1, int paramInt2, Consumer paramConsumer) throws Throwable {
    CallContext callContext = CallContext.getInstance();
    paramProcedure.check3(paramObject, IntNum.make(paramInt1), IntNum.make(paramInt2), callContext);
    Consumer consumer = callContext.consumer;
    try {
      callContext.consumer = paramConsumer;
      callContext.runUntilDone();
      return;
    } finally {
      callContext.consumer = consumer;
    } 
  }
  
  public void applyWithFocus(Procedure paramProcedure, Object paramObject, Consumer paramConsumer) throws Throwable {
    CallContext callContext = CallContext.getInstance();
    Consumer consumer = callContext.consumer;
    try {
      callContext.consumer = paramConsumer;
      applyWithFocus$X(paramProcedure, paramObject, callContext);
      return;
    } finally {
      callContext.consumer = consumer;
    } 
  }
  
  public void applyWithFocus$X(Procedure paramProcedure, Object paramObject, CallContext paramCallContext) throws Throwable {
    if (paramObject instanceof gnu.mapping.Values) {
      paramObject = paramObject;
      int i = paramObject.size();
      if (i != 0) {
        int k = 0;
        IntNum intNum1 = IntNum.make(i);
        int j = 1;
        while (true) {
          paramProcedure.check3(paramObject.getPosNext(k), IntNum.make(j), intNum1, paramCallContext);
          paramCallContext.runUntilDone();
          if (j != i) {
            k = paramObject.nextPos(k);
            j++;
            continue;
          } 
          return;
        } 
      } 
      return;
    } 
    IntNum intNum = IntNum.one();
    paramProcedure.check3(paramObject, intNum, intNum, paramCallContext);
    paramCallContext.runUntilDone();
  }
  
  public void define(String paramString, Object paramObject) {
    Symbol symbol = Symbol.make(this.defaultNamespace, paramString);
    if (paramObject instanceof Procedure) {
      Object object = EnvironmentKey.FUNCTION;
    } else {
      paramString = null;
    } 
    this.environ.define(symbol, paramString, paramObject);
  }
  
  protected void define_method(String paramString1, String paramString2, String paramString3) {
    Symbol symbol = Symbol.make(this.defaultNamespace, paramString1);
    MethodProc methodProc = ClassMethods.apply((ObjectType)ClassType.make(paramString2), paramString3, false, this);
    methodProc.setSymbol(symbol);
    this.environ.define(symbol, EnvironmentKey.FUNCTION, methodProc);
  }
  
  public Procedure evalToFocusProc(Reader paramReader, SourceMessages paramSourceMessages) throws Throwable {
    InPort inPort;
    if (paramReader instanceof InPort) {
      inPort = (InPort)paramReader;
    } else {
      inPort = new InPort((Reader)inPort);
    } 
    Compilation compilation = parse(inPort, paramSourceMessages, 65537);
    CallContext callContext = CallContext.getInstance();
    int i = callContext.startFromContext();
    try {
      ModuleExp.evalModule(Environment.getCurrent(), callContext, compilation, null, null);
      return (Procedure)callContext.getFromContext(i);
    } catch (Throwable throwable) {
      callContext.cleanupFromContext(i);
      throw throwable;
    } 
  }
  
  public Procedure evalToFocusProc(String paramString) throws Throwable {
    SourceMessages sourceMessages = new SourceMessages();
    Procedure procedure = evalToFocusProc((Reader)new CharArrayInPort(paramString), sourceMessages);
    if (sourceMessages.seenErrors())
      throw new RuntimeException("invalid syntax in eval form:\n" + sourceMessages.toString(20)); 
    return procedure;
  }
  
  public Object evalWithFocus(String paramString, Object paramObject) throws Throwable {
    return applyWithFocus(evalToFocusProc(paramString), paramObject);
  }
  
  public Object evalWithFocus(String paramString, Object paramObject, int paramInt1, int paramInt2) throws Throwable {
    return applyWithFocus(evalToFocusProc(paramString), paramObject, paramInt1, paramInt2);
  }
  
  public void evalWithFocus(Reader paramReader, SourceMessages paramSourceMessages, Object paramObject, int paramInt1, int paramInt2, Consumer paramConsumer) throws Throwable {
    applyWithFocus(evalToFocusProc(paramReader, paramSourceMessages), paramObject, paramInt1, paramInt2, paramConsumer);
  }
  
  public void evalWithFocus(Reader paramReader, SourceMessages paramSourceMessages, Object paramObject, Consumer paramConsumer) throws Throwable {
    applyWithFocus(evalToFocusProc(paramReader, paramSourceMessages), paramObject, paramConsumer);
  }
  
  public void eval_with_focus$X(String paramString, Object paramObject, int paramInt1, int paramInt2, CallContext paramCallContext) throws Throwable {
    evalToFocusProc(paramString).check3(paramObject, IntNum.make(paramInt1), IntNum.make(paramInt2), paramCallContext);
  }
  
  public void eval_with_focus$X(String paramString, Object paramObject, CallContext paramCallContext) throws Throwable {
    applyWithFocus$X(evalToFocusProc(paramString), paramObject, paramCallContext);
  }
  
  public String formatType(Type paramType) {
    String str = paramType.getName();
    return "gnu.math.IntNum".equals(str) ? "xs:integer" : (("java.lang.String".equals(str) || "java.lang.CharSequence".equals(str)) ? "xs:string" : paramType.toString());
  }
  
  public Compilation getCompilation(Lexer paramLexer, SourceMessages paramSourceMessages, NameLookup paramNameLookup) {
    return new Compilation(this, paramSourceMessages, paramNameLookup);
  }
  
  public Lexer getLexer(InPort paramInPort, SourceMessages paramSourceMessages) {
    return new XQParser(paramInPort, paramSourceMessages, this);
  }
  
  public String getName() {
    return "XQuery";
  }
  
  public int getNamespaceOf(Declaration paramDeclaration) {
    if (paramDeclaration.isProcedureDecl()) {
      if (paramDeclaration.getCode() >= 0) {
        Object object;
        Expression expression = paramDeclaration.getValue();
        if (expression instanceof LambdaExp) {
          object = expression;
          return (((LambdaExp)object).min_args == ((LambdaExp)object).max_args) ? namespaceForFunctions(((LambdaExp)object).min_args) : -2;
        } 
        if (object instanceof QuoteExp) {
          object = ((QuoteExp)object).getValue();
          if (object instanceof Procedure) {
            object = object;
            int i = object.minArgs();
            if (i == object.maxArgs())
              return namespaceForFunctions(i); 
          } 
          return -2;
        } 
        if (object instanceof ReferenceExp)
          return getNamespaceOf(((ReferenceExp)object).getBinding()); 
      } 
      return -2;
    } 
    return 1;
  }
  
  public Consumer getOutputConsumer(Writer paramWriter) {
    return (Consumer)new XMLPrinter(paramWriter, false);
  }
  
  public Procedure getPrompter() {
    return (Procedure)new Prompter();
  }
  
  public Symbol getSymbol(String paramString) {
    return Symbol.make(this.defaultNamespace, paramString);
  }
  
  public Type getTypeFor(Class paramClass) {
    String str;
    if (paramClass.isPrimitive()) {
      str = paramClass.getName();
      return (Type)(str.equals("boolean") ? XDataType.booleanType : Scheme.getNamedType(str));
    } 
    if (!str.isArray()) {
      String str1 = str.getName();
      if (str1.equals("java.lang.String"))
        return (Type)XDataType.stringStringType; 
      if (str1.equals("gnu.kawa.xml.UntypedAtomic"))
        return (Type)XDataType.untypedAtomicType; 
      if (str1.equals("java.lang.Boolean"))
        return (Type)XDataType.booleanType; 
      if (str1.equals("java.lang.Float"))
        return (Type)XDataType.floatType; 
      if (str1.equals("java.lang.Double"))
        return (Type)XDataType.doubleType; 
      if (str1.equals("java.math.BigDecimal"))
        return (Type)XDataType.decimalType; 
      if (str1.equals("gnu.math.Duration"))
        return (Type)XDataType.durationType; 
      if (str1.equals("gnu.text.Path"))
        return (Type)XDataType.anyURIType; 
    } 
    return Type.make((Class)str);
  }
  
  public Type getTypeFor(String paramString) {
    String str;
    if (paramString.startsWith("xs:")) {
      str = paramString.substring(3);
    } else if (paramString.startsWith("xdt:")) {
      str = paramString.substring(4);
    } else {
      str = paramString;
    } 
    Type type = getStandardType(str);
    return (type != null) ? type : Scheme.string2Type(paramString);
  }
  
  public boolean hasNamespace(Declaration paramDeclaration, int paramInt) {
    int i = getNamespaceOf(paramDeclaration);
    return (i == paramInt || (i == -2 && (paramInt & 0x2) != 0) || (paramInt == -2 && (i & 0x2) != 0));
  }
  
  public boolean hasSeparateFunctionNamespace() {
    return true;
  }
  
  public boolean isTrue(Object paramObject) {
    return BooleanValue.booleanValue(paramObject);
  }
  
  public boolean parse(Compilation paramCompilation, int paramInt) throws IOException, SyntaxException {
    ModuleExp moduleExp = paramCompilation.mainLambda;
    Compilation.defaultCallConvention = 2;
    paramCompilation.mustCompileHere();
    XQParser xQParser = (XQParser)paramCompilation.lexer;
    if (xQParser.isInteractive()) {
      Expression expression1 = xQParser.parse(paramCompilation);
      if (expression1 == null)
        return false; 
      moduleExp.body = expression1;
      paramCompilation.pop((ScopeExp)moduleExp);
      xQResolveNames = new XQResolveNames(paramCompilation);
      xQResolveNames.functionNamespacePath = xQParser.functionNamespacePath;
      xQResolveNames.parser = xQParser;
      xQResolveNames.resolveModule(moduleExp);
      paramCompilation.setState(4);
      return true;
    } 
    if ((0x10000 & paramInt) != 0) {
      LambdaExp lambdaExp = new LambdaExp(3);
      Declaration declaration = lambdaExp.addDeclaration(XQParser.DOT_VARNAME);
      declaration.setFlag(262144L);
      declaration.noteValue(null);
      lambdaExp.addDeclaration(XQParser.POSITION_VARNAME, (Type)Type.intType);
      lambdaExp.addDeclaration(XQParser.LAST_VARNAME, (Type)Type.intType);
      paramCompilation.push((ScopeExp)lambdaExp);
      lambdaExp.body = xQParser.parse(paramCompilation);
      paramCompilation.pop((ScopeExp)lambdaExp);
      moduleExp.body = (Expression)lambdaExp;
      paramCompilation.pop((ScopeExp)moduleExp);
      xQResolveNames = new XQResolveNames(paramCompilation);
      xQResolveNames.functionNamespacePath = xQParser.functionNamespacePath;
      xQResolveNames.parser = xQParser;
      xQResolveNames.resolveModule(moduleExp);
      paramCompilation.setState(4);
      return true;
    } 
    Vector<Expression> vector = new Vector(10);
    Expression expression = moduleExp.body;
    if (expression instanceof BeginExp) {
      BeginExp beginExp = (BeginExp)expression;
      int i = beginExp.getExpressionCount();
      arrayOfExpression = beginExp.getExpressions();
      for (paramInt = 0; paramInt < i; paramInt++)
        vector.addElement(arrayOfExpression[paramInt]); 
    } else if (arrayOfExpression != null && arrayOfExpression != QuoteExp.voidExp) {
      vector.addElement((Expression)arrayOfExpression);
    } 
    while (true) {
      Expression expression1 = xQParser.parse(paramCompilation);
      if (expression1 == null) {
        if (xQParser.parseCount == 0 && !xQParser.isInteractive()) {
          xQParser.error('e', "empty module", "XPST0003");
          return false;
        } 
        break;
      } 
      vector.addElement(expression1);
    } 
    paramInt = vector.size();
    if (paramInt == 0) {
      moduleExp.body = (Expression)QuoteExp.voidExp;
      paramCompilation.pop((ScopeExp)moduleExp);
      xQResolveNames = new XQResolveNames(paramCompilation);
      xQResolveNames.functionNamespacePath = xQParser.functionNamespacePath;
      xQResolveNames.parser = xQParser;
      xQResolveNames.resolveModule(moduleExp);
      paramCompilation.setState(4);
      return true;
    } 
    if (paramInt == 1) {
      moduleExp.body = xQResolveNames.elementAt(0);
      paramCompilation.pop((ScopeExp)moduleExp);
      xQResolveNames = new XQResolveNames(paramCompilation);
      xQResolveNames.functionNamespacePath = xQParser.functionNamespacePath;
      xQResolveNames.parser = xQParser;
      xQResolveNames.resolveModule(moduleExp);
      paramCompilation.setState(4);
      return true;
    } 
    Expression[] arrayOfExpression = new Expression[paramInt];
    xQResolveNames.copyInto((Object[])arrayOfExpression);
    moduleExp.body = (Expression)new BeginExp(arrayOfExpression);
    paramCompilation.pop((ScopeExp)moduleExp);
    XQResolveNames xQResolveNames = new XQResolveNames(paramCompilation);
    xQResolveNames.functionNamespacePath = xQParser.functionNamespacePath;
    xQResolveNames.parser = xQParser;
    xQResolveNames.resolveModule(moduleExp);
    paramCompilation.setState(4);
    return true;
  }
  
  public void resolve(Compilation paramCompilation) {}
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/gnu/xquery/lang/XQuery.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */