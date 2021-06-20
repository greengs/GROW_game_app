package gnu.xquery.lang;

import gnu.bytecode.ClassType;
import gnu.bytecode.Type;
import gnu.expr.ApplyExp;
import gnu.expr.BeginExp;
import gnu.expr.Compilation;
import gnu.expr.Declaration;
import gnu.expr.ErrorExp;
import gnu.expr.Expression;
import gnu.expr.IfExp;
import gnu.expr.LambdaExp;
import gnu.expr.LetExp;
import gnu.expr.PrimProcedure;
import gnu.expr.QuoteExp;
import gnu.expr.ReferenceExp;
import gnu.expr.ScopeExp;
import gnu.kawa.functions.Convert;
import gnu.kawa.lispexpr.LangPrimType;
import gnu.kawa.reflect.InstanceOf;
import gnu.kawa.reflect.OccurrenceType;
import gnu.kawa.reflect.SingletonType;
import gnu.kawa.xml.DescendantOrSelfAxis;
import gnu.kawa.xml.ElementType;
import gnu.kawa.xml.MakeAttribute;
import gnu.kawa.xml.MakeElement;
import gnu.kawa.xml.MakeWithBaseUri;
import gnu.kawa.xml.NodeType;
import gnu.kawa.xml.ParentAxis;
import gnu.kawa.xml.ProcessingInstructionType;
import gnu.kawa.xml.XDataType;
import gnu.lists.NodePredicate;
import gnu.mapping.InPort;
import gnu.mapping.Namespace;
import gnu.mapping.Procedure;
import gnu.mapping.Symbol;
import gnu.mapping.WrappedException;
import gnu.math.IntNum;
import gnu.text.Lexer;
import gnu.text.LineBufferedReader;
import gnu.text.Path;
import gnu.text.SourceError;
import gnu.text.SourceMessages;
import gnu.text.SyntaxException;
import gnu.text.URIPath;
import gnu.xml.NamespaceBinding;
import gnu.xml.TextUtils;
import gnu.xml.XName;
import gnu.xquery.util.CastableAs;
import gnu.xquery.util.NamedCollator;
import gnu.xquery.util.RelativeStep;
import gnu.xquery.util.ValuesFilter;
import java.io.IOException;
import java.util.Vector;

public class XQParser extends Lexer {
  static final int ARROW_TOKEN = 82;
  
  static final int ATTRIBUTE_TOKEN = 252;
  
  static final int AXIS_ANCESTOR = 0;
  
  static final int AXIS_ANCESTOR_OR_SELF = 1;
  
  static final int AXIS_ATTRIBUTE = 2;
  
  static final int AXIS_CHILD = 3;
  
  static final int AXIS_DESCENDANT = 4;
  
  static final int AXIS_DESCENDANT_OR_SELF = 5;
  
  static final int AXIS_FOLLOWING = 6;
  
  static final int AXIS_FOLLOWING_SIBLING = 7;
  
  static final int AXIS_NAMESPACE = 8;
  
  static final int AXIS_PARENT = 9;
  
  static final int AXIS_PRECEDING = 10;
  
  static final int AXIS_PRECEDING_SIBLING = 11;
  
  static final int AXIS_SELF = 12;
  
  static final int CASE_DOLLAR_TOKEN = 247;
  
  static final int COLON_COLON_TOKEN = 88;
  
  static final int COLON_EQUAL_TOKEN = 76;
  
  static final int COMMENT_TOKEN = 254;
  
  static final int COUNT_OP_AXIS = 13;
  
  static final char DECIMAL_TOKEN = '1';
  
  static final int DECLARE_BASE_URI_TOKEN = 66;
  
  static final int DECLARE_BOUNDARY_SPACE_TOKEN = 83;
  
  static final int DECLARE_CONSTRUCTION_TOKEN = 75;
  
  static final int DECLARE_COPY_NAMESPACES_TOKEN = 76;
  
  static final int DECLARE_FUNCTION_TOKEN = 80;
  
  static final int DECLARE_NAMESPACE_TOKEN = 78;
  
  static final int DECLARE_OPTION_TOKEN = 111;
  
  static final int DECLARE_ORDERING_TOKEN = 85;
  
  static final int DECLARE_VARIABLE_TOKEN = 86;
  
  static final int DEFAULT_COLLATION_TOKEN = 71;
  
  static final int DEFAULT_ELEMENT_TOKEN = 69;
  
  static final int DEFAULT_FUNCTION_TOKEN = 79;
  
  static final int DEFAULT_ORDER_TOKEN = 72;
  
  static final int DEFINE_QNAME_TOKEN = 87;
  
  static final int DOCUMENT_TOKEN = 256;
  
  static final int DOTDOT_TOKEN = 51;
  
  static final Symbol DOT_VARNAME;
  
  static final char DOUBLE_TOKEN = '2';
  
  static final int ELEMENT_TOKEN = 251;
  
  static final int EOF_TOKEN = -1;
  
  static final int EOL_TOKEN = 10;
  
  static final int EVERY_DOLLAR_TOKEN = 246;
  
  static final int FNAME_TOKEN = 70;
  
  static final int FOR_DOLLAR_TOKEN = 243;
  
  static final int IF_LPAREN_TOKEN = 241;
  
  static final int IMPORT_MODULE_TOKEN = 73;
  
  static final int IMPORT_SCHEMA_TOKEN = 84;
  
  static final char INTEGER_TOKEN = '0';
  
  static final Symbol LAST_VARNAME;
  
  static final int LET_DOLLAR_TOKEN = 244;
  
  static final int MODULE_NAMESPACE_TOKEN = 77;
  
  static final int NCNAME_COLON_TOKEN = 67;
  
  static final int NCNAME_TOKEN = 65;
  
  static final int OP_ADD = 413;
  
  static final int OP_AND = 401;
  
  static final int OP_ATTRIBUTE = 236;
  
  static final int OP_AXIS_FIRST = 100;
  
  static final int OP_BASE = 400;
  
  static final int OP_CASTABLE_AS = 424;
  
  static final int OP_CAST_AS = 425;
  
  static final int OP_COMMENT = 232;
  
  static final int OP_DIV = 416;
  
  static final int OP_DOCUMENT = 234;
  
  static final int OP_ELEMENT = 235;
  
  static final int OP_EMPTY_SEQUENCE = 238;
  
  static final int OP_EQ = 426;
  
  static final int OP_EQU = 402;
  
  static final int OP_EXCEPT = 421;
  
  static final int OP_GE = 431;
  
  static final int OP_GEQ = 407;
  
  static final int OP_GRT = 405;
  
  static final int OP_GRTGRT = 410;
  
  static final int OP_GT = 430;
  
  static final int OP_IDIV = 417;
  
  static final int OP_INSTANCEOF = 422;
  
  static final int OP_INTERSECT = 420;
  
  static final int OP_IS = 408;
  
  static final int OP_ISNOT = 409;
  
  static final int OP_ITEM = 237;
  
  static final int OP_LE = 429;
  
  static final int OP_LEQ = 406;
  
  static final int OP_LSS = 404;
  
  static final int OP_LSSLSS = 411;
  
  static final int OP_LT = 428;
  
  static final int OP_MOD = 418;
  
  static final int OP_MUL = 415;
  
  static final int OP_NE = 427;
  
  static final int OP_NEQ = 403;
  
  static final int OP_NODE = 230;
  
  static final int OP_OR = 400;
  
  static final int OP_PI = 233;
  
  static final int OP_RANGE_TO = 412;
  
  static final int OP_SCHEMA_ATTRIBUTE = 239;
  
  static final int OP_SCHEMA_ELEMENT = 240;
  
  static final int OP_SUB = 414;
  
  static final int OP_TEXT = 231;
  
  static final int OP_TREAT_AS = 423;
  
  static final int OP_UNION = 419;
  
  static final int OP_WHERE = 196;
  
  static final int ORDERED_LBRACE_TOKEN = 249;
  
  static final int PI_TOKEN = 255;
  
  static final Symbol POSITION_VARNAME;
  
  static final int PRAGMA_START_TOKEN = 197;
  
  static final int QNAME_TOKEN = 81;
  
  static final int SLASHSLASH_TOKEN = 68;
  
  static final int SOME_DOLLAR_TOKEN = 245;
  
  static final int STRING_TOKEN = 34;
  
  static final int TEXT_TOKEN = 253;
  
  static final int TYPESWITCH_LPAREN_TOKEN = 242;
  
  static final int UNORDERED_LBRACE_TOKEN = 250;
  
  static final int VALIDATE_LBRACE_TOKEN = 248;
  
  static final int XQUERY_VERSION_TOKEN = 89;
  
  public static final String[] axisNames;
  
  static NamespaceBinding builtinNamespaces;
  
  public static final CastableAs castableAs;
  
  public static final QuoteExp getExternalFunction;
  
  public static final InstanceOf instanceOf;
  
  static final Expression makeCDATA;
  
  public static QuoteExp makeChildAxisStep;
  
  public static QuoteExp makeDescendantAxisStep;
  
  public static Expression makeText;
  
  static PrimProcedure proc_OccurrenceType_getInstance;
  
  public static final Convert treatAs;
  
  public static boolean warnHidePreviousDeclaration;
  
  public static boolean warnOldVersion = true;
  
  Path baseURI = null;
  
  boolean baseURIDeclarationSeen;
  
  boolean boundarySpaceDeclarationSeen;
  
  boolean boundarySpacePreserve;
  
  int commentCount;
  
  Compilation comp;
  
  boolean constructionModeDeclarationSeen;
  
  boolean constructionModeStrip;
  
  NamespaceBinding constructorNamespaces = NamespaceBinding.predefinedXML;
  
  boolean copyNamespacesDeclarationSeen;
  
  int copyNamespacesMode = 3;
  
  int curColumn;
  
  int curLine;
  
  int curToken;
  
  Object curValue;
  
  NamedCollator defaultCollator = null;
  
  String defaultElementNamespace = "";
  
  char defaultEmptyOrder = 'L';
  
  boolean emptyOrderDeclarationSeen;
  
  int enclosedExpressionsSeen;
  
  String errorIfComment;
  
  Declaration[] flworDecls;
  
  int flworDeclsCount;
  
  int flworDeclsFirst;
  
  public Namespace[] functionNamespacePath = XQuery.defaultFunctionNamespacePath;
  
  XQuery interpreter;
  
  String libraryModuleNamespace;
  
  boolean orderingModeSeen;
  
  boolean orderingModeUnordered;
  
  int parseContext;
  
  int parseCount;
  
  NamespaceBinding prologNamespaces;
  
  private int saveToken;
  
  private Object saveValue;
  
  boolean seenDeclaration;
  
  int seenLast;
  
  int seenPosition;
  
  private boolean warnedOldStyleKindTest;
  
  static {
    warnHidePreviousDeclaration = false;
    DOT_VARNAME = Symbol.makeUninterned("$dot$");
    POSITION_VARNAME = Symbol.makeUninterned("$position$");
    LAST_VARNAME = Symbol.makeUninterned("$last$");
    instanceOf = new InstanceOf(XQuery.getInstance(), "instance");
    castableAs = CastableAs.castableAs;
    treatAs = Convert.as;
    proc_OccurrenceType_getInstance = new PrimProcedure(ClassType.make("gnu.kawa.reflect.OccurrenceType").getDeclaredMethod("getInstance", 3));
    makeChildAxisStep = QuoteExp.getInstance(new PrimProcedure("gnu.kawa.xml.ChildAxis", "make", 1));
    makeDescendantAxisStep = QuoteExp.getInstance(new PrimProcedure("gnu.kawa.xml.DescendantAxis", "make", 1));
    makeText = makeFunctionExp("gnu.kawa.xml.MakeText", "makeText");
    makeCDATA = makeFunctionExp("gnu.kawa.xml.MakeCDATA", "makeCDATA");
    builtinNamespaces = new NamespaceBinding("local", "http://www.w3.org/2005/xquery-local-functions", new NamespaceBinding("qexo", "http://qexo.gnu.org/", new NamespaceBinding("kawa", "http://kawa.gnu.org/", new NamespaceBinding("html", "http://www.w3.org/1999/xhtml", new NamespaceBinding("fn", "http://www.w3.org/2005/xpath-functions", new NamespaceBinding("xsi", "http://www.w3.org/2001/XMLSchema-instance", new NamespaceBinding("xs", "http://www.w3.org/2001/XMLSchema", new NamespaceBinding("xml", "http://www.w3.org/XML/1998/namespace", NamespaceBinding.predefinedXML))))))));
    getExternalFunction = QuoteExp.getInstance(new PrimProcedure("gnu.xquery.lang.XQuery", "getExternal", 2));
    axisNames = new String[13];
    axisNames[0] = "ancestor";
    axisNames[1] = "ancestor-or-self";
    axisNames[2] = "attribute";
    axisNames[3] = "child";
    axisNames[4] = "descendant";
    axisNames[5] = "descendant-or-self";
    axisNames[6] = "following";
    axisNames[7] = "following-sibling";
    axisNames[8] = "namespace";
    axisNames[9] = "parent";
    axisNames[10] = "preceding";
    axisNames[11] = "preceding-sibling";
    axisNames[12] = "self";
  }
  
  public XQParser(InPort paramInPort, SourceMessages paramSourceMessages, XQuery paramXQuery) {
    super((LineBufferedReader)paramInPort, paramSourceMessages);
    this.interpreter = paramXQuery;
    this.nesting = 1;
    this.prologNamespaces = builtinNamespaces;
  }
  
  public static Expression booleanValue(Expression paramExpression) {
    return (Expression)new ApplyExp(makeFunctionExp("gnu.xquery.util.BooleanValue", "booleanValue"), new Expression[] { paramExpression });
  }
  
  static ApplyExp castQName(Expression paramExpression, boolean paramBoolean) {
    if (paramBoolean) {
      Declaration declaration1 = XQResolveNames.xsQNameDecl;
      return new ApplyExp((Expression)new ReferenceExp(declaration1), new Expression[] { paramExpression });
    } 
    Declaration declaration = XQResolveNames.xsQNameIgnoreDefaultDecl;
    return new ApplyExp((Expression)new ReferenceExp(declaration), new Expression[] { paramExpression });
  }
  
  static Path fixupStaticBaseUri(Path paramPath) {
    URIPath uRIPath;
    Path path = paramPath.getAbsolute();
    paramPath = path;
    if (path instanceof gnu.text.FilePath)
      uRIPath = URIPath.valueOf(path.toURI()); 
    return (Path)uRIPath;
  }
  
  private boolean lookingAt(String paramString1, String paramString2) throws IOException, SyntaxException {
    if (!paramString1.equals(this.curValue))
      return false; 
    int i = 0;
    int j = paramString2.length();
    while (true) {
      int m = read();
      if (i == j) {
        if (m < 0)
          return true; 
        if (!XName.isNamePart((char)m)) {
          unread();
          return true;
        } 
        int n = i + 1;
        this.port.skip(-n);
        return false;
      } 
      int k = i;
      if (m >= 0) {
        k = i + 1;
        if (m == paramString2.charAt(i)) {
          i = k;
          continue;
        } 
      } 
      this.port.skip(-k);
      return false;
    } 
  }
  
  static Expression makeBinary(Expression paramExpression1, Expression paramExpression2, Expression paramExpression3) {
    return (Expression)new ApplyExp(paramExpression1, new Expression[] { paramExpression2, paramExpression3 });
  }
  
  static Expression makeExprSequence(Expression paramExpression1, Expression paramExpression2) {
    return makeBinary(makeFunctionExp("gnu.kawa.functions.AppendValues", "appendValues"), paramExpression1, paramExpression2);
  }
  
  public static Expression makeFunctionExp(String paramString1, String paramString2) {
    return makeFunctionExp(paramString1, Compilation.mangleNameIfNeeded(paramString2), paramString2);
  }
  
  public static Expression makeFunctionExp(String paramString1, String paramString2, String paramString3) {
    return (Expression)new ReferenceExp(paramString3, Declaration.getDeclarationValueFromStatic(paramString1, paramString2, paramString3));
  }
  
  static Expression makeNamedNodeType(boolean paramBoolean, Expression paramExpression1, Expression paramExpression2) {
    String str;
    Expression[] arrayOfExpression = new Expression[2];
    if (paramBoolean) {
      str = "gnu.kawa.xml.AttributeType";
    } else {
      str = "gnu.kawa.xml.ElementType";
    } 
    ApplyExp applyExp = new ApplyExp(ClassType.make(str).getDeclaredMethod("make", 1), new Expression[] { paramExpression1 });
    applyExp.setFlag(4);
    return (Expression)((paramExpression2 == null) ? applyExp : new BeginExp(paramExpression2, (Expression)applyExp));
  }
  
  private void parseSimpleKindType() throws IOException, SyntaxException {
    getRawToken();
    if (this.curToken == 41) {
      getRawToken();
      return;
    } 
    error("expected ')'");
  }
  
  private static final int priority(int paramInt) {
    switch (paramInt) {
      default:
        return 0;
      case 400:
        return 1;
      case 401:
        return 2;
      case 402:
      case 403:
      case 404:
      case 405:
      case 406:
      case 407:
      case 408:
      case 409:
      case 410:
      case 411:
      case 426:
      case 427:
      case 428:
      case 429:
      case 430:
      case 431:
        return 3;
      case 412:
        return 4;
      case 413:
      case 414:
        return 5;
      case 415:
      case 416:
      case 417:
      case 418:
        return 6;
      case 419:
        return 7;
      case 420:
      case 421:
        return 8;
      case 422:
        return 9;
      case 423:
        return 10;
      case 424:
        return 11;
      case 425:
        break;
    } 
    return 12;
  }
  
  private int setToken(int paramInt1, int paramInt2) {
    this.curToken = paramInt1;
    this.curLine = this.port.getLineNumber() + 1;
    this.curColumn = this.port.getColumnNumber() + 1 - paramInt2;
    return paramInt1;
  }
  
  private void warnOldStyleKindTest() {
    if (this.warnedOldStyleKindTest)
      return; 
    error('w', "old-style KindTest - first one here");
    this.warnedOldStyleKindTest = true;
  }
  
  public void appendNamedEntity(String paramString) {
    paramString = paramString.intern();
    byte b = 63;
    if (paramString == "lt") {
      b = 60;
    } else if (paramString == "gt") {
      b = 62;
    } else if (paramString == "amp") {
      b = 38;
    } else if (paramString == "quot") {
      b = 34;
    } else if (paramString == "apos") {
      b = 39;
    } else {
      error("unknown enity reference: '" + paramString + "'");
    } 
    tokenBufferAppend(b);
  }
  
  void checkAllowedNamespaceDeclaration(String paramString1, String paramString2, boolean paramBoolean) {
    boolean bool = "xml".equals(paramString1);
    if ("http://www.w3.org/XML/1998/namespace".equals(paramString2)) {
      if (!bool || !paramBoolean)
        error('e', "namespace uri cannot be the same as the prefined xml namespace", "XQST0070"); 
      return;
    } 
    if (bool || "xmlns".equals(paramString1)) {
      error('e', "namespace prefix cannot be 'xml' or 'xmlns'", "XQST0070");
      return;
    } 
  }
  
  void checkSeparator(char paramChar) {
    if (XName.isNameStart(paramChar))
      error('e', "missing separator", "XPST0003"); 
  }
  
  public Expression declError(String paramString) throws IOException, SyntaxException {
    if (this.interactive)
      return syntaxError(paramString); 
    error(paramString);
    while (true) {
      if (this.curToken == 59 || this.curToken == -1)
        return (Expression)new ErrorExp(paramString); 
      getRawToken();
    } 
  }
  
  public void eofError(String paramString) throws SyntaxException {
    fatal(paramString, "XPST0003");
  }
  
  public void error(char paramChar, String paramString) {
    error(paramChar, paramString, (String)null);
  }
  
  public void error(char paramChar, String paramString1, String paramString2) {
    SourceMessages sourceMessages = getMessages();
    SourceError sourceError = new SourceError(paramChar, this.port.getName(), this.curLine, this.curColumn, paramString1);
    sourceError.code = paramString2;
    sourceMessages.error(sourceError);
  }
  
  public void fatal(String paramString1, String paramString2) throws SyntaxException {
    SourceMessages sourceMessages = getMessages();
    SourceError sourceError = new SourceError('f', this.port.getName(), this.curLine, this.curColumn, paramString1);
    sourceError.code = paramString2;
    sourceMessages.error(sourceError);
    throw new SyntaxException(sourceMessages);
  }
  
  int getAxis() {
    int i;
    String str = (new String(this.tokenBuffer, 0, this.tokenBufferLength)).intern();
    int j = 13;
    while (true) {
      i = j - 1;
      if (i >= 0) {
        j = i;
        if (axisNames[i] == str)
          break; 
        continue;
      } 
      break;
    } 
    if (i >= 0) {
      j = i;
      if (i == 8) {
        error('e', "unknown axis name '" + str + '\'', "XPST0003");
        j = 3;
        return (char)(j + 100);
      } 
      return (char)(j + 100);
    } 
    error('e', "unknown axis name '" + str + '\'', "XPST0003");
    j = 3;
    return (char)(j + 100);
  }
  
  public void getDelimited(String paramString) throws IOException, SyntaxException {
    if (!readDelimited(paramString))
      eofError("unexpected end-of-file looking for '" + paramString + '\''); 
  }
  
  int getRawToken() throws IOException, SyntaxException {
    // Byte code:
    //   0: iconst_1
    //   1: istore #4
    //   3: aload_0
    //   4: invokevirtual readUnicodeChar : ()I
    //   7: istore #5
    //   9: iload #5
    //   11: ifge -> 21
    //   14: aload_0
    //   15: iconst_m1
    //   16: iconst_0
    //   17: invokespecial setToken : (II)I
    //   20: ireturn
    //   21: iload #5
    //   23: bipush #10
    //   25: if_icmpeq -> 35
    //   28: iload #5
    //   30: bipush #13
    //   32: if_icmpne -> 50
    //   35: aload_0
    //   36: getfield nesting : I
    //   39: ifgt -> 3
    //   42: aload_0
    //   43: bipush #10
    //   45: iconst_0
    //   46: invokespecial setToken : (II)I
    //   49: ireturn
    //   50: iload #5
    //   52: bipush #40
    //   54: if_icmpne -> 99
    //   57: aload_0
    //   58: bipush #58
    //   60: invokevirtual checkNext : (C)Z
    //   63: ifeq -> 73
    //   66: aload_0
    //   67: invokevirtual skipComment : ()V
    //   70: goto -> 3
    //   73: aload_0
    //   74: bipush #35
    //   76: invokevirtual checkNext : (C)Z
    //   79: ifeq -> 91
    //   82: aload_0
    //   83: sipush #197
    //   86: iconst_2
    //   87: invokespecial setToken : (II)I
    //   90: ireturn
    //   91: aload_0
    //   92: bipush #40
    //   94: iconst_1
    //   95: invokespecial setToken : (II)I
    //   98: ireturn
    //   99: iload #5
    //   101: bipush #123
    //   103: if_icmpne -> 155
    //   106: aload_0
    //   107: bipush #45
    //   109: invokevirtual checkNext : (C)Z
    //   112: ifne -> 123
    //   115: aload_0
    //   116: bipush #123
    //   118: iconst_1
    //   119: invokespecial setToken : (II)I
    //   122: ireturn
    //   123: aload_0
    //   124: invokevirtual read : ()I
    //   127: bipush #45
    //   129: if_icmpeq -> 148
    //   132: aload_0
    //   133: invokevirtual unread : ()V
    //   136: aload_0
    //   137: invokevirtual unread : ()V
    //   140: aload_0
    //   141: bipush #123
    //   143: iconst_1
    //   144: invokespecial setToken : (II)I
    //   147: ireturn
    //   148: aload_0
    //   149: invokevirtual skipOldComment : ()V
    //   152: goto -> 3
    //   155: iload #5
    //   157: bipush #32
    //   159: if_icmpeq -> 3
    //   162: iload #5
    //   164: bipush #9
    //   166: if_icmpeq -> 3
    //   169: aload_0
    //   170: iconst_0
    //   171: putfield tokenBufferLength : I
    //   174: aload_0
    //   175: aload_0
    //   176: getfield port : Lgnu/text/LineBufferedReader;
    //   179: invokevirtual getLineNumber : ()I
    //   182: iconst_1
    //   183: iadd
    //   184: putfield curLine : I
    //   187: aload_0
    //   188: aload_0
    //   189: getfield port : Lgnu/text/LineBufferedReader;
    //   192: invokevirtual getColumnNumber : ()I
    //   195: putfield curColumn : I
    //   198: iload #5
    //   200: i2c
    //   201: istore_1
    //   202: iload_1
    //   203: istore_3
    //   204: iload_1
    //   205: lookupswitch default -> 384, 33 -> 592, 34 -> 721, 36 -> 525, 39 -> 721, 41 -> 525, 42 -> 571, 43 -> 578, 44 -> 525, 45 -> 585, 47 -> 610, 58 -> 532, 59 -> 525, 60 -> 682, 61 -> 627, 62 -> 643, 63 -> 525, 64 -> 525, 91 -> 525, 93 -> 525, 124 -> 564, 125 -> 525
    //   384: iload_1
    //   385: invokestatic isDigit : (C)Z
    //   388: ifne -> 408
    //   391: iload_1
    //   392: bipush #46
    //   394: if_icmpne -> 928
    //   397: aload_0
    //   398: invokevirtual peek : ()I
    //   401: i2c
    //   402: invokestatic isDigit : (C)Z
    //   405: ifeq -> 928
    //   408: iload_1
    //   409: bipush #46
    //   411: if_icmpne -> 802
    //   414: iload #4
    //   416: istore_3
    //   417: iload_1
    //   418: istore #4
    //   420: aload_0
    //   421: iload #4
    //   423: invokevirtual tokenBufferAppend : (I)V
    //   426: aload_0
    //   427: invokevirtual read : ()I
    //   430: istore #5
    //   432: iload #5
    //   434: ifge -> 810
    //   437: iload #5
    //   439: bipush #101
    //   441: if_icmpeq -> 451
    //   444: iload #5
    //   446: bipush #69
    //   448: if_icmpne -> 886
    //   451: aload_0
    //   452: iload #5
    //   454: i2c
    //   455: invokevirtual tokenBufferAppend : (I)V
    //   458: aload_0
    //   459: invokevirtual read : ()I
    //   462: istore #4
    //   464: iload #4
    //   466: bipush #43
    //   468: if_icmpeq -> 481
    //   471: iload #4
    //   473: istore_3
    //   474: iload #4
    //   476: bipush #45
    //   478: if_icmpne -> 492
    //   481: aload_0
    //   482: iload #4
    //   484: invokevirtual tokenBufferAppend : (I)V
    //   487: aload_0
    //   488: invokevirtual read : ()I
    //   491: istore_3
    //   492: iconst_0
    //   493: istore #5
    //   495: iload_3
    //   496: istore #4
    //   498: iload #5
    //   500: istore_3
    //   501: iload #4
    //   503: ifge -> 845
    //   506: iload_3
    //   507: ifne -> 522
    //   510: aload_0
    //   511: bipush #101
    //   513: ldc_w 'no digits following exponent'
    //   516: ldc_w 'XPST0003'
    //   519: invokevirtual error : (CLjava/lang/String;Ljava/lang/String;)V
    //   522: bipush #50
    //   524: istore_3
    //   525: aload_0
    //   526: iload_3
    //   527: putfield curToken : I
    //   530: iload_3
    //   531: ireturn
    //   532: aload_0
    //   533: bipush #61
    //   535: invokevirtual checkNext : (C)Z
    //   538: ifeq -> 547
    //   541: bipush #76
    //   543: istore_3
    //   544: goto -> 525
    //   547: iload_1
    //   548: istore_3
    //   549: aload_0
    //   550: bipush #58
    //   552: invokevirtual checkNext : (C)Z
    //   555: ifeq -> 525
    //   558: bipush #88
    //   560: istore_3
    //   561: goto -> 525
    //   564: sipush #419
    //   567: istore_3
    //   568: goto -> 525
    //   571: sipush #415
    //   574: istore_3
    //   575: goto -> 525
    //   578: sipush #413
    //   581: istore_3
    //   582: goto -> 525
    //   585: sipush #414
    //   588: istore_3
    //   589: goto -> 525
    //   592: iload_1
    //   593: istore_3
    //   594: aload_0
    //   595: bipush #61
    //   597: invokevirtual checkNext : (C)Z
    //   600: ifeq -> 525
    //   603: sipush #403
    //   606: istore_3
    //   607: goto -> 525
    //   610: iload_1
    //   611: istore_3
    //   612: aload_0
    //   613: bipush #47
    //   615: invokevirtual checkNext : (C)Z
    //   618: ifeq -> 525
    //   621: bipush #68
    //   623: istore_3
    //   624: goto -> 525
    //   627: aload_0
    //   628: bipush #62
    //   630: invokevirtual checkNext : (C)Z
    //   633: ifeq -> 636
    //   636: sipush #402
    //   639: istore_3
    //   640: goto -> 525
    //   643: aload_0
    //   644: bipush #61
    //   646: invokevirtual checkNext : (C)Z
    //   649: ifeq -> 659
    //   652: sipush #407
    //   655: istore_3
    //   656: goto -> 525
    //   659: aload_0
    //   660: bipush #62
    //   662: invokevirtual checkNext : (C)Z
    //   665: ifeq -> 675
    //   668: sipush #410
    //   671: istore_3
    //   672: goto -> 656
    //   675: sipush #405
    //   678: istore_3
    //   679: goto -> 656
    //   682: aload_0
    //   683: bipush #61
    //   685: invokevirtual checkNext : (C)Z
    //   688: ifeq -> 698
    //   691: sipush #406
    //   694: istore_3
    //   695: goto -> 525
    //   698: aload_0
    //   699: bipush #60
    //   701: invokevirtual checkNext : (C)Z
    //   704: ifeq -> 714
    //   707: sipush #411
    //   710: istore_3
    //   711: goto -> 695
    //   714: sipush #404
    //   717: istore_3
    //   718: goto -> 695
    //   721: aload_0
    //   722: iload #5
    //   724: i2c
    //   725: invokevirtual pushNesting : (C)C
    //   728: istore_2
    //   729: aload_0
    //   730: invokevirtual readUnicodeChar : ()I
    //   733: istore #4
    //   735: iload #4
    //   737: ifge -> 747
    //   740: aload_0
    //   741: ldc_w 'unexpected end-of-file in string starting here'
    //   744: invokevirtual eofError : (Ljava/lang/String;)V
    //   747: iload #4
    //   749: bipush #38
    //   751: if_icmpne -> 761
    //   754: aload_0
    //   755: invokevirtual parseEntityOrCharRef : ()V
    //   758: goto -> 729
    //   761: iload #4
    //   763: istore_3
    //   764: iload_1
    //   765: iload #4
    //   767: if_icmpne -> 794
    //   770: iload_1
    //   771: aload_0
    //   772: invokevirtual peek : ()I
    //   775: if_icmpeq -> 789
    //   778: aload_0
    //   779: iload_2
    //   780: invokevirtual popNesting : (C)V
    //   783: bipush #34
    //   785: istore_3
    //   786: goto -> 525
    //   789: aload_0
    //   790: invokevirtual read : ()I
    //   793: istore_3
    //   794: aload_0
    //   795: iload_3
    //   796: invokevirtual tokenBufferAppend : (I)V
    //   799: goto -> 729
    //   802: iconst_0
    //   803: istore_3
    //   804: iload_1
    //   805: istore #4
    //   807: goto -> 420
    //   810: iload #5
    //   812: i2c
    //   813: istore_1
    //   814: iload_1
    //   815: bipush #46
    //   817: if_icmpne -> 832
    //   820: iload_3
    //   821: ifne -> 437
    //   824: iconst_1
    //   825: istore_3
    //   826: iload_1
    //   827: istore #4
    //   829: goto -> 420
    //   832: iload_1
    //   833: istore #4
    //   835: iload_1
    //   836: invokestatic isDigit : (C)Z
    //   839: ifne -> 420
    //   842: goto -> 437
    //   845: iload #4
    //   847: i2c
    //   848: istore_1
    //   849: iload_1
    //   850: invokestatic isDigit : (C)Z
    //   853: ifne -> 868
    //   856: aload_0
    //   857: iload_1
    //   858: invokevirtual checkSeparator : (C)V
    //   861: aload_0
    //   862: invokevirtual unread : ()V
    //   865: goto -> 506
    //   868: aload_0
    //   869: iload_1
    //   870: invokevirtual tokenBufferAppend : (I)V
    //   873: aload_0
    //   874: invokevirtual read : ()I
    //   877: istore #4
    //   879: iload_3
    //   880: iconst_1
    //   881: iadd
    //   882: istore_3
    //   883: goto -> 501
    //   886: iload_3
    //   887: ifeq -> 921
    //   890: bipush #49
    //   892: istore #4
    //   894: iload #4
    //   896: istore_3
    //   897: iload #5
    //   899: iflt -> 525
    //   902: aload_0
    //   903: iload #5
    //   905: i2c
    //   906: invokevirtual checkSeparator : (C)V
    //   909: aload_0
    //   910: iload #5
    //   912: invokevirtual unread : (I)V
    //   915: iload #4
    //   917: istore_3
    //   918: goto -> 525
    //   921: bipush #48
    //   923: istore #4
    //   925: goto -> 894
    //   928: iload_1
    //   929: bipush #46
    //   931: if_icmpne -> 951
    //   934: iload_1
    //   935: istore_3
    //   936: aload_0
    //   937: bipush #46
    //   939: invokevirtual checkNext : (C)Z
    //   942: ifeq -> 525
    //   945: bipush #51
    //   947: istore_3
    //   948: goto -> 525
    //   951: iload_1
    //   952: invokestatic isNameStart : (I)Z
    //   955: ifeq -> 1108
    //   958: iload_1
    //   959: istore_3
    //   960: aload_0
    //   961: iload_3
    //   962: invokevirtual tokenBufferAppend : (I)V
    //   965: aload_0
    //   966: invokevirtual read : ()I
    //   969: istore #4
    //   971: iload #4
    //   973: i2c
    //   974: istore #5
    //   976: iload #5
    //   978: istore_3
    //   979: iload #5
    //   981: invokestatic isNamePart : (I)Z
    //   984: ifne -> 960
    //   987: iload #4
    //   989: ifge -> 998
    //   992: bipush #65
    //   994: istore_3
    //   995: goto -> 525
    //   998: iload #4
    //   1000: bipush #58
    //   1002: if_icmpeq -> 1017
    //   1005: bipush #65
    //   1007: istore_3
    //   1008: aload_0
    //   1009: iload #4
    //   1011: invokevirtual unread : (I)V
    //   1014: goto -> 525
    //   1017: aload_0
    //   1018: invokevirtual read : ()I
    //   1021: istore #4
    //   1023: iload #4
    //   1025: ifge -> 1035
    //   1028: aload_0
    //   1029: ldc_w 'unexpected end-of-file after NAME ':''
    //   1032: invokevirtual eofError : (Ljava/lang/String;)V
    //   1035: iload #4
    //   1037: i2c
    //   1038: istore_3
    //   1039: iload_3
    //   1040: invokestatic isNameStart : (I)Z
    //   1043: ifeq -> 1085
    //   1046: aload_0
    //   1047: bipush #58
    //   1049: invokevirtual tokenBufferAppend : (I)V
    //   1052: aload_0
    //   1053: iload_3
    //   1054: invokevirtual tokenBufferAppend : (I)V
    //   1057: aload_0
    //   1058: invokevirtual read : ()I
    //   1061: istore #4
    //   1063: iload #4
    //   1065: i2c
    //   1066: istore #5
    //   1068: iload #5
    //   1070: istore_3
    //   1071: iload #5
    //   1073: invokestatic isNamePart : (I)Z
    //   1076: ifne -> 1052
    //   1079: bipush #81
    //   1081: istore_3
    //   1082: goto -> 1008
    //   1085: iload_3
    //   1086: bipush #61
    //   1088: if_icmpne -> 1102
    //   1091: aload_0
    //   1092: iload_3
    //   1093: invokevirtual unread : (I)V
    //   1096: bipush #65
    //   1098: istore_3
    //   1099: goto -> 1008
    //   1102: bipush #67
    //   1104: istore_3
    //   1105: goto -> 1008
    //   1108: iload_1
    //   1109: bipush #32
    //   1111: if_icmplt -> 1155
    //   1114: iload_1
    //   1115: bipush #127
    //   1117: if_icmpge -> 1155
    //   1120: aload_0
    //   1121: new java/lang/StringBuilder
    //   1124: dup
    //   1125: invokespecial <init> : ()V
    //   1128: ldc_w 'invalid character ''
    //   1131: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1134: iload_1
    //   1135: invokevirtual append : (C)Ljava/lang/StringBuilder;
    //   1138: bipush #39
    //   1140: invokevirtual append : (C)Ljava/lang/StringBuilder;
    //   1143: invokevirtual toString : ()Ljava/lang/String;
    //   1146: invokevirtual syntaxError : (Ljava/lang/String;)Lgnu/expr/Expression;
    //   1149: pop
    //   1150: iload_1
    //   1151: istore_3
    //   1152: goto -> 525
    //   1155: aload_0
    //   1156: new java/lang/StringBuilder
    //   1159: dup
    //   1160: invokespecial <init> : ()V
    //   1163: ldc_w 'invalid character '\u'
    //   1166: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1169: iload_1
    //   1170: invokestatic toHexString : (I)Ljava/lang/String;
    //   1173: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1176: bipush #39
    //   1178: invokevirtual append : (C)Ljava/lang/StringBuilder;
    //   1181: invokevirtual toString : ()Ljava/lang/String;
    //   1184: invokevirtual syntaxError : (Ljava/lang/String;)Lgnu/expr/Expression;
    //   1187: pop
    //   1188: iload_1
    //   1189: istore_3
    //   1190: goto -> 525
  }
  
  public String getStaticBaseUri() {
    // Byte code:
    //   0: aload_0
    //   1: getfield baseURI : Lgnu/text/Path;
    //   4: astore_1
    //   5: aload_1
    //   6: astore_2
    //   7: aload_1
    //   8: ifnonnull -> 120
    //   11: invokestatic getCurrent : ()Lgnu/mapping/Environment;
    //   14: ldc_w ''
    //   17: ldc_w 'base-uri'
    //   20: invokestatic make : (Ljava/lang/Object;Ljava/lang/String;)Lgnu/mapping/Symbol;
    //   23: aconst_null
    //   24: aconst_null
    //   25: invokevirtual get : (Lgnu/mapping/Symbol;Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   28: astore_3
    //   29: aload_1
    //   30: astore_2
    //   31: aload_3
    //   32: ifnull -> 44
    //   35: aload_3
    //   36: instanceof gnu/text/Path
    //   39: ifeq -> 125
    //   42: aload_1
    //   43: astore_2
    //   44: aload_2
    //   45: astore_1
    //   46: aload_2
    //   47: ifnonnull -> 100
    //   50: aload_0
    //   51: invokevirtual getPort : ()Lgnu/text/LineBufferedReader;
    //   54: astore_3
    //   55: aload_2
    //   56: astore_1
    //   57: aload_3
    //   58: ifnull -> 100
    //   61: aload_3
    //   62: invokevirtual getPath : ()Lgnu/text/Path;
    //   65: astore_2
    //   66: aload_2
    //   67: astore_1
    //   68: aload_2
    //   69: instanceof gnu/text/FilePath
    //   72: ifeq -> 100
    //   75: aload_2
    //   76: invokevirtual exists : ()Z
    //   79: ifeq -> 98
    //   82: aload_3
    //   83: instanceof gnu/mapping/TtyInPort
    //   86: ifne -> 98
    //   89: aload_2
    //   90: astore_1
    //   91: aload_3
    //   92: instanceof gnu/mapping/CharArrayInPort
    //   95: ifeq -> 100
    //   98: aconst_null
    //   99: astore_1
    //   100: aload_1
    //   101: astore_2
    //   102: aload_1
    //   103: ifnonnull -> 110
    //   106: invokestatic currentPath : ()Lgnu/text/Path;
    //   109: astore_2
    //   110: aload_2
    //   111: invokestatic fixupStaticBaseUri : (Lgnu/text/Path;)Lgnu/text/Path;
    //   114: astore_2
    //   115: aload_0
    //   116: aload_2
    //   117: putfield baseURI : Lgnu/text/Path;
    //   120: aload_2
    //   121: invokevirtual toString : ()Ljava/lang/String;
    //   124: areturn
    //   125: aload_3
    //   126: invokevirtual toString : ()Ljava/lang/String;
    //   129: invokestatic valueOf : (Ljava/lang/String;)Lgnu/text/URIPath;
    //   132: astore_2
    //   133: goto -> 44
  }
  
  public void handleOption(Symbol paramSymbol, String paramString) {}
  
  Expression makeBinary(int paramInt, Expression paramExpression1, Expression paramExpression2) throws IOException, SyntaxException {
    switch (paramInt) {
      default:
        return syntaxError("unimplemented binary op: " + paramInt);
      case 413:
        expression = makeFunctionExp("gnu.xquery.util.ArithOp", "add", "+");
        return makeBinary(expression, paramExpression1, paramExpression2);
      case 414:
        expression = makeFunctionExp("gnu.xquery.util.ArithOp", "sub", "-");
        return makeBinary(expression, paramExpression1, paramExpression2);
      case 415:
        expression = makeFunctionExp("gnu.xquery.util.ArithOp", "mul", "*");
        return makeBinary(expression, paramExpression1, paramExpression2);
      case 416:
        expression = makeFunctionExp("gnu.xquery.util.ArithOp", "div", "div");
        return makeBinary(expression, paramExpression1, paramExpression2);
      case 417:
        expression = makeFunctionExp("gnu.xquery.util.ArithOp", "idiv", "idiv");
        return makeBinary(expression, paramExpression1, paramExpression2);
      case 418:
        expression = makeFunctionExp("gnu.xquery.util.ArithOp", "mod", "mod");
        return makeBinary(expression, paramExpression1, paramExpression2);
      case 426:
        expression = makeFunctionExp("gnu.xquery.util.Compare", "valEq", "eq");
        return makeBinary(expression, paramExpression1, paramExpression2);
      case 427:
        expression = makeFunctionExp("gnu.xquery.util.Compare", "valNe", "ne");
        return makeBinary(expression, paramExpression1, paramExpression2);
      case 428:
        expression = makeFunctionExp("gnu.xquery.util.Compare", "valLt", "lt");
        return makeBinary(expression, paramExpression1, paramExpression2);
      case 429:
        expression = makeFunctionExp("gnu.xquery.util.Compare", "valLe", "le");
        return makeBinary(expression, paramExpression1, paramExpression2);
      case 430:
        expression = makeFunctionExp("gnu.xquery.util.Compare", "valGt", "gt");
        return makeBinary(expression, paramExpression1, paramExpression2);
      case 431:
        expression = makeFunctionExp("gnu.xquery.util.Compare", "valGe", "ge");
        return makeBinary(expression, paramExpression1, paramExpression2);
      case 402:
        expression = makeFunctionExp("gnu.xquery.util.Compare", "=");
        return makeBinary(expression, paramExpression1, paramExpression2);
      case 403:
        expression = makeFunctionExp("gnu.xquery.util.Compare", "!=");
        return makeBinary(expression, paramExpression1, paramExpression2);
      case 404:
        expression = makeFunctionExp("gnu.xquery.util.Compare", "<");
        return makeBinary(expression, paramExpression1, paramExpression2);
      case 406:
        expression = makeFunctionExp("gnu.xquery.util.Compare", "<=");
        return makeBinary(expression, paramExpression1, paramExpression2);
      case 405:
        expression = makeFunctionExp("gnu.xquery.util.Compare", ">");
        return makeBinary(expression, paramExpression1, paramExpression2);
      case 407:
        expression = makeFunctionExp("gnu.xquery.util.Compare", ">=");
        return makeBinary(expression, paramExpression1, paramExpression2);
      case 408:
        expression = makeFunctionExp("gnu.kawa.xml.NodeCompare", "$Eq", "is");
        return makeBinary(expression, paramExpression1, paramExpression2);
      case 409:
        expression = makeFunctionExp("gnu.kawa.xml.NodeCompare", "$Ne", "isnot");
        return makeBinary(expression, paramExpression1, paramExpression2);
      case 410:
        expression = makeFunctionExp("gnu.kawa.xml.NodeCompare", "$Gr", ">>");
        return makeBinary(expression, paramExpression1, paramExpression2);
      case 411:
        expression = makeFunctionExp("gnu.kawa.xml.NodeCompare", "$Ls", "<<");
        return makeBinary(expression, paramExpression1, paramExpression2);
      case 412:
        expression = makeFunctionExp("gnu.xquery.util.IntegerRange", "integerRange");
        return makeBinary(expression, paramExpression1, paramExpression2);
      case 419:
        expression = makeFunctionExp("gnu.kawa.xml.UnionNodes", "unionNodes");
        return makeBinary(expression, paramExpression1, paramExpression2);
      case 420:
        expression = makeFunctionExp("gnu.kawa.xml.IntersectNodes", "intersectNodes");
        return makeBinary(expression, paramExpression1, paramExpression2);
      case 421:
        break;
    } 
    Expression expression = makeFunctionExp("gnu.kawa.xml.IntersectNodes", "exceptNodes");
    return makeBinary(expression, paramExpression1, paramExpression2);
  }
  
  public void mark() throws IOException {
    super.mark();
    this.saveToken = this.curToken;
    this.saveValue = this.curValue;
  }
  
  public boolean match(String paramString) {
    if (this.curToken == 65) {
      int i = paramString.length();
      if (this.tokenBufferLength == i)
        while (true) {
          int j = i - 1;
          if (j >= 0) {
            i = j;
            if (paramString.charAt(j) != this.tokenBuffer[j])
              return false; 
            continue;
          } 
          return true;
        }  
    } 
    return false;
  }
  
  boolean match(String paramString1, String paramString2, boolean paramBoolean) throws IOException, SyntaxException {
    if (match(paramString1)) {
      mark();
      getRawToken();
      if (match(paramString2)) {
        reset();
        getRawToken();
        return true;
      } 
      reset();
      if (paramBoolean) {
        error('e', "'" + paramString1 + "' must be followed by '" + paramString2 + "'", "XPST0003");
        return true;
      } 
    } 
    return false;
  }
  
  public void maybeSetLine(Declaration paramDeclaration, int paramInt1, int paramInt2) {
    String str = getName();
    if (str != null) {
      paramDeclaration.setFile(str);
      paramDeclaration.setLine(paramInt1, paramInt2);
    } 
  }
  
  public void maybeSetLine(Expression paramExpression, int paramInt1, int paramInt2) {
    String str = getName();
    if (str != null && paramExpression.getFileName() == null && !(paramExpression instanceof QuoteExp)) {
      paramExpression.setFile(str);
      paramExpression.setLine(paramInt1, paramInt2);
    } 
  }
  
  protected Symbol namespaceResolve(String paramString, boolean paramBoolean) {
    // Byte code:
    //   0: aload_1
    //   1: bipush #58
    //   3: invokevirtual indexOf : (I)I
    //   6: istore_3
    //   7: iload_3
    //   8: iflt -> 121
    //   11: aload_1
    //   12: iconst_0
    //   13: iload_3
    //   14: invokevirtual substring : (II)Ljava/lang/String;
    //   17: invokevirtual intern : ()Ljava/lang/String;
    //   20: astore #5
    //   22: aload #5
    //   24: aload_0
    //   25: getfield constructorNamespaces : Lgnu/xml/NamespaceBinding;
    //   28: aload_0
    //   29: getfield prologNamespaces : Lgnu/xml/NamespaceBinding;
    //   32: invokestatic lookupPrefix : (Ljava/lang/String;Lgnu/xml/NamespaceBinding;Lgnu/xml/NamespaceBinding;)Ljava/lang/String;
    //   35: astore #4
    //   37: aload #4
    //   39: astore #6
    //   41: aload #4
    //   43: ifnonnull -> 108
    //   46: iload_3
    //   47: ifge -> 141
    //   50: ldc_w ''
    //   53: astore #4
    //   55: aload #4
    //   57: astore #6
    //   59: aload #4
    //   61: ifnonnull -> 108
    //   64: aload_0
    //   65: invokevirtual getMessages : ()Lgnu/text/SourceMessages;
    //   68: bipush #101
    //   70: new java/lang/StringBuilder
    //   73: dup
    //   74: invokespecial <init> : ()V
    //   77: ldc_w 'unknown namespace prefix ''
    //   80: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   83: aload #5
    //   85: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   88: ldc_w '''
    //   91: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   94: invokevirtual toString : ()Ljava/lang/String;
    //   97: ldc_w 'XPST0081'
    //   100: invokevirtual error : (CLjava/lang/String;Ljava/lang/String;)V
    //   103: ldc_w '(unknown namespace)'
    //   106: astore #6
    //   108: iload_3
    //   109: ifge -> 191
    //   112: aload #6
    //   114: aload_1
    //   115: aload #5
    //   117: invokestatic make : (Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Lgnu/mapping/Symbol;
    //   120: areturn
    //   121: iload_2
    //   122: ifeq -> 133
    //   125: ldc_w '(functions)'
    //   128: astore #5
    //   130: goto -> 22
    //   133: getstatic gnu/xquery/lang/XQuery.DEFAULT_ELEMENT_PREFIX : Ljava/lang/String;
    //   136: astore #5
    //   138: goto -> 22
    //   141: aload_0
    //   142: getfield comp : Lgnu/expr/Compilation;
    //   145: invokevirtual isPedantic : ()Z
    //   148: ifne -> 55
    //   151: aload #5
    //   153: invokestatic forName : (Ljava/lang/String;)Ljava/lang/Class;
    //   156: pop
    //   157: new java/lang/StringBuilder
    //   160: dup
    //   161: invokespecial <init> : ()V
    //   164: ldc_w 'class:'
    //   167: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   170: aload #5
    //   172: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   175: invokevirtual toString : ()Ljava/lang/String;
    //   178: astore #4
    //   180: goto -> 55
    //   183: astore #4
    //   185: aconst_null
    //   186: astore #4
    //   188: goto -> 55
    //   191: aload_1
    //   192: iload_3
    //   193: iconst_1
    //   194: iadd
    //   195: invokevirtual substring : (I)Ljava/lang/String;
    //   198: astore_1
    //   199: goto -> 112
    // Exception table:
    //   from	to	target	type
    //   151	180	183	java/lang/Exception
  }
  
  public Expression parse(Compilation paramCompilation) throws IOException, SyntaxException {
    // Byte code:
    //   0: aload_0
    //   1: aload_1
    //   2: putfield comp : Lgnu/expr/Compilation;
    //   5: aload_0
    //   6: invokevirtual skipSpace : ()I
    //   9: istore_3
    //   10: iload_3
    //   11: ifge -> 18
    //   14: aconst_null
    //   15: astore_1
    //   16: aload_1
    //   17: areturn
    //   18: aload_0
    //   19: aload_0
    //   20: getfield parseCount : I
    //   23: iconst_1
    //   24: iadd
    //   25: putfield parseCount : I
    //   28: aload_0
    //   29: iload_3
    //   30: invokevirtual unread : (I)V
    //   33: aload_0
    //   34: invokevirtual getLineNumber : ()I
    //   37: iconst_1
    //   38: iadd
    //   39: istore #5
    //   41: aload_0
    //   42: invokevirtual getColumnNumber : ()I
    //   45: iconst_1
    //   46: iadd
    //   47: istore #6
    //   49: iload_3
    //   50: bipush #35
    //   52: if_icmpne -> 138
    //   55: iload #5
    //   57: iconst_1
    //   58: if_icmpne -> 138
    //   61: iload #6
    //   63: iconst_1
    //   64: if_icmpne -> 138
    //   67: aload_0
    //   68: invokevirtual read : ()I
    //   71: pop
    //   72: aload_0
    //   73: invokevirtual read : ()I
    //   76: istore #4
    //   78: iload #4
    //   80: istore_3
    //   81: iload #4
    //   83: bipush #33
    //   85: if_icmpne -> 107
    //   88: aload_0
    //   89: invokevirtual read : ()I
    //   92: istore #4
    //   94: iload #4
    //   96: istore_3
    //   97: iload #4
    //   99: bipush #47
    //   101: if_icmpeq -> 114
    //   104: iload #4
    //   106: istore_3
    //   107: aload_0
    //   108: ldc_w ''#' is only allowed in initial '#!/PROGRAM''
    //   111: invokevirtual error : (Ljava/lang/String;)V
    //   114: iload_3
    //   115: bipush #13
    //   117: if_icmpeq -> 138
    //   120: iload_3
    //   121: bipush #10
    //   123: if_icmpeq -> 138
    //   126: iload_3
    //   127: iflt -> 138
    //   130: aload_0
    //   131: invokevirtual read : ()I
    //   134: istore_3
    //   135: goto -> 114
    //   138: aload_0
    //   139: invokevirtual getRawToken : ()I
    //   142: iconst_m1
    //   143: if_icmpne -> 148
    //   146: aconst_null
    //   147: areturn
    //   148: aload_0
    //   149: invokevirtual peekOperand : ()I
    //   152: pop
    //   153: aload_0
    //   154: getfield curToken : I
    //   157: bipush #65
    //   159: if_icmpne -> 199
    //   162: ldc_w 'namespace'
    //   165: aload_0
    //   166: getfield curValue : Ljava/lang/Object;
    //   169: checkcast java/lang/String
    //   172: invokevirtual equals : (Ljava/lang/Object;)Z
    //   175: ifeq -> 199
    //   178: getstatic gnu/xquery/lang/XQParser.warnOldVersion : Z
    //   181: ifeq -> 193
    //   184: aload_0
    //   185: bipush #119
    //   187: ldc_w 'use 'declare namespace' instead of 'namespace''
    //   190: invokevirtual error : (CLjava/lang/String;)V
    //   193: aload_0
    //   194: bipush #78
    //   196: putfield curToken : I
    //   199: aload_0
    //   200: getfield curToken : I
    //   203: lookupswitch default -> 356, 66 -> 3487, 69 -> 2127, 71 -> 2000, 72 -> 2780, 73 -> 1390, 75 -> 2522, 76 -> 2606, 77 -> 954, 78 -> 954, 79 -> 2127, 80 -> 471, 83 -> 2391, 84 -> 1380, 85 -> 3050, 86 -> 539, 87 -> 414, 89 -> 3134, 111 -> 2896
    //   356: aload_0
    //   357: iconst_m1
    //   358: iconst_1
    //   359: invokevirtual parseExprSequence : (IZ)Lgnu/expr/Expression;
    //   362: astore #8
    //   364: aload_0
    //   365: getfield curToken : I
    //   368: bipush #10
    //   370: if_icmpne -> 379
    //   373: aload_0
    //   374: bipush #10
    //   376: invokevirtual unread : (I)V
    //   379: aload_0
    //   380: aload #8
    //   382: iload #5
    //   384: iload #6
    //   386: invokevirtual maybeSetLine : (Lgnu/expr/Expression;II)V
    //   389: aload #8
    //   391: astore_1
    //   392: aload_0
    //   393: getfield libraryModuleNamespace : Ljava/lang/String;
    //   396: ifnull -> 16
    //   399: aload_0
    //   400: bipush #101
    //   402: ldc_w 'query body in a library module'
    //   405: ldc_w 'XPST0003'
    //   408: invokevirtual error : (CLjava/lang/String;Ljava/lang/String;)V
    //   411: aload #8
    //   413: areturn
    //   414: aload_0
    //   415: invokevirtual getLineNumber : ()I
    //   418: istore_3
    //   419: aload_0
    //   420: invokevirtual getColumnNumber : ()I
    //   423: istore #4
    //   425: aload_0
    //   426: ldc_w 'unexpected end-of-file after 'define QName''
    //   429: invokevirtual peekNonSpace : (Ljava/lang/String;)I
    //   432: bipush #40
    //   434: if_icmpne -> 463
    //   437: aload_0
    //   438: ldc_w ''missing 'function' after 'define''
    //   441: invokevirtual syntaxError : (Ljava/lang/String;)Lgnu/expr/Expression;
    //   444: pop
    //   445: aload_0
    //   446: bipush #65
    //   448: putfield curToken : I
    //   451: aload_0
    //   452: iload_3
    //   453: iconst_1
    //   454: iadd
    //   455: iload #4
    //   457: iconst_1
    //   458: iadd
    //   459: invokevirtual parseFunctionDefinition : (II)Lgnu/expr/Expression;
    //   462: areturn
    //   463: aload_0
    //   464: ldc_w 'missing keyword after 'define''
    //   467: invokevirtual syntaxError : (Ljava/lang/String;)Lgnu/expr/Expression;
    //   470: areturn
    //   471: aload_0
    //   472: invokevirtual getLineNumber : ()I
    //   475: istore_3
    //   476: aload_0
    //   477: invokevirtual getColumnNumber : ()I
    //   480: istore #4
    //   482: aload_0
    //   483: invokevirtual getRawToken : ()I
    //   486: pop
    //   487: aload_0
    //   488: ldc_w 'unexpected end-of-file after 'define function''
    //   491: invokevirtual peekNonSpace : (Ljava/lang/String;)I
    //   494: pop
    //   495: aload_0
    //   496: bipush #100
    //   498: invokevirtual pushNesting : (C)C
    //   501: istore_2
    //   502: aload_0
    //   503: iload_3
    //   504: iconst_1
    //   505: iadd
    //   506: iload #4
    //   508: iconst_1
    //   509: iadd
    //   510: invokevirtual parseFunctionDefinition : (II)Lgnu/expr/Expression;
    //   513: astore_1
    //   514: aload_0
    //   515: iload_2
    //   516: invokevirtual popNesting : (C)V
    //   519: aload_0
    //   520: invokevirtual parseSeparator : ()V
    //   523: aload_0
    //   524: aload_1
    //   525: iload #5
    //   527: iload #6
    //   529: invokevirtual maybeSetLine : (Lgnu/expr/Expression;II)V
    //   532: aload_0
    //   533: iconst_1
    //   534: putfield seenDeclaration : Z
    //   537: aload_1
    //   538: areturn
    //   539: aload_0
    //   540: invokevirtual getRawToken : ()I
    //   543: pop
    //   544: aload_0
    //   545: invokevirtual parseVariableDeclaration : ()Lgnu/expr/Declaration;
    //   548: astore #11
    //   550: aload #11
    //   552: ifnonnull -> 563
    //   555: aload_0
    //   556: ldc_w 'missing Variable'
    //   559: invokevirtual syntaxError : (Ljava/lang/String;)Lgnu/expr/Expression;
    //   562: areturn
    //   563: aload #11
    //   565: invokevirtual getSymbol : ()Ljava/lang/Object;
    //   568: astore #8
    //   570: aload #8
    //   572: instanceof java/lang/String
    //   575: ifeq -> 615
    //   578: aload_0
    //   579: invokevirtual getMessages : ()Lgnu/text/SourceMessages;
    //   582: aload_0
    //   583: getfield port : Lgnu/text/LineBufferedReader;
    //   586: invokevirtual getName : ()Ljava/lang/String;
    //   589: aload_0
    //   590: getfield curLine : I
    //   593: aload_0
    //   594: getfield curColumn : I
    //   597: invokevirtual setLine : (Ljava/lang/String;II)V
    //   600: aload #11
    //   602: aload_0
    //   603: aload #8
    //   605: checkcast java/lang/String
    //   608: iconst_0
    //   609: invokevirtual namespaceResolve : (Ljava/lang/String;Z)Lgnu/mapping/Symbol;
    //   612: invokevirtual setSymbol : (Ljava/lang/Object;)V
    //   615: aload_0
    //   616: getfield libraryModuleNamespace : Ljava/lang/String;
    //   619: ifnull -> 674
    //   622: aload #11
    //   624: invokevirtual getSymbol : ()Ljava/lang/Object;
    //   627: checkcast gnu/mapping/Symbol
    //   630: invokevirtual getNamespaceURI : ()Ljava/lang/String;
    //   633: astore #8
    //   635: aload #8
    //   637: aload_0
    //   638: getfield libraryModuleNamespace : Ljava/lang/String;
    //   641: if_acmpeq -> 674
    //   644: ldc_w 'http://www.w3.org/2005/xquery-local-functions'
    //   647: aload #8
    //   649: invokevirtual equals : (Ljava/lang/Object;)Z
    //   652: ifeq -> 662
    //   655: aload_1
    //   656: invokevirtual isPedantic : ()Z
    //   659: ifeq -> 674
    //   662: aload_0
    //   663: bipush #101
    //   665: ldc_w 'variable not in namespace of library module'
    //   668: ldc_w 'XQST0048'
    //   671: invokevirtual error : (CLjava/lang/String;Ljava/lang/String;)V
    //   674: aload_1
    //   675: invokevirtual currentScope : ()Lgnu/expr/ScopeExp;
    //   678: aload #11
    //   680: invokevirtual addDeclaration : (Lgnu/expr/Declaration;)V
    //   683: aload_0
    //   684: invokevirtual getRawToken : ()I
    //   687: pop
    //   688: aload_0
    //   689: invokevirtual parseOptionalTypeDeclaration : ()Lgnu/expr/Expression;
    //   692: astore #9
    //   694: aload #11
    //   696: iconst_1
    //   697: invokevirtual setCanRead : (Z)V
    //   700: aload #11
    //   702: ldc2_w 16384
    //   705: invokevirtual setFlag : (J)V
    //   708: iconst_0
    //   709: istore_3
    //   710: aload_0
    //   711: getfield curToken : I
    //   714: sipush #402
    //   717: if_icmpeq -> 729
    //   720: aload_0
    //   721: getfield curToken : I
    //   724: bipush #76
    //   726: if_icmpne -> 753
    //   729: aload_0
    //   730: getfield curToken : I
    //   733: sipush #402
    //   736: if_icmpne -> 746
    //   739: aload_0
    //   740: ldc_w 'declare variable contains '=' instead of ':=''
    //   743: invokevirtual error : (Ljava/lang/String;)V
    //   746: aload_0
    //   747: invokevirtual getRawToken : ()I
    //   750: pop
    //   751: iconst_1
    //   752: istore_3
    //   753: aload_0
    //   754: getfield curToken : I
    //   757: bipush #123
    //   759: if_icmpne -> 825
    //   762: aload_0
    //   763: ldc_w 'obsolete '{' in variable declaration'
    //   766: invokevirtual warnOldVersion : (Ljava/lang/String;)V
    //   769: aload_0
    //   770: invokevirtual parseEnclosedExpr : ()Lgnu/expr/Expression;
    //   773: astore_1
    //   774: aload_0
    //   775: invokevirtual parseSeparator : ()V
    //   778: aload_1
    //   779: astore #8
    //   781: aload #9
    //   783: ifnull -> 794
    //   786: aload_1
    //   787: aload #9
    //   789: invokestatic makeCoercion : (Lgnu/expr/Expression;Lgnu/expr/Expression;)Lgnu/expr/ApplyExp;
    //   792: astore #8
    //   794: aload #11
    //   796: aload #8
    //   798: invokevirtual noteValue : (Lgnu/expr/Expression;)V
    //   801: aload #11
    //   803: aload #8
    //   805: invokestatic makeDefinition : (Lgnu/expr/Declaration;Lgnu/expr/Expression;)Lgnu/expr/SetExp;
    //   808: astore_1
    //   809: aload_0
    //   810: aload_1
    //   811: iload #5
    //   813: iload #6
    //   815: invokevirtual maybeSetLine : (Lgnu/expr/Expression;II)V
    //   818: aload_0
    //   819: iconst_1
    //   820: putfield seenDeclaration : Z
    //   823: aload_1
    //   824: areturn
    //   825: aload_0
    //   826: ldc_w 'external'
    //   829: invokevirtual match : (Ljava/lang/String;)Z
    //   832: ifeq -> 913
    //   835: new gnu/expr/QuoteExp
    //   838: dup
    //   839: aload #11
    //   841: invokevirtual getSymbol : ()Ljava/lang/Object;
    //   844: invokespecial <init> : (Ljava/lang/Object;)V
    //   847: iconst_0
    //   848: invokestatic castQName : (Lgnu/expr/Expression;Z)Lgnu/expr/ApplyExp;
    //   851: astore #8
    //   853: aload #9
    //   855: ifnonnull -> 907
    //   858: getstatic gnu/expr/QuoteExp.nullExp : Lgnu/expr/QuoteExp;
    //   861: astore_1
    //   862: new gnu/expr/ApplyExp
    //   865: dup
    //   866: getstatic gnu/xquery/lang/XQParser.getExternalFunction : Lgnu/expr/QuoteExp;
    //   869: iconst_2
    //   870: anewarray gnu/expr/Expression
    //   873: dup
    //   874: iconst_0
    //   875: aload #8
    //   877: aastore
    //   878: dup
    //   879: iconst_1
    //   880: aload_1
    //   881: aastore
    //   882: invokespecial <init> : (Lgnu/expr/Expression;[Lgnu/expr/Expression;)V
    //   885: astore_1
    //   886: aload_0
    //   887: aload_1
    //   888: aload_0
    //   889: getfield curLine : I
    //   892: aload_0
    //   893: getfield curColumn : I
    //   896: invokevirtual maybeSetLine : (Lgnu/expr/Expression;II)V
    //   899: aload_0
    //   900: invokevirtual getRawToken : ()I
    //   903: pop
    //   904: goto -> 778
    //   907: aload #9
    //   909: astore_1
    //   910: goto -> 862
    //   913: aload_0
    //   914: invokevirtual parseExpr : ()Lgnu/expr/Expression;
    //   917: astore #10
    //   919: aconst_null
    //   920: astore #8
    //   922: iload_3
    //   923: ifeq -> 931
    //   926: aload #10
    //   928: ifnonnull -> 940
    //   931: aload_0
    //   932: ldc_w 'expected ':= init' or 'external''
    //   935: invokevirtual syntaxError : (Ljava/lang/String;)Lgnu/expr/Expression;
    //   938: astore #8
    //   940: aload #10
    //   942: astore_1
    //   943: aload #10
    //   945: ifnonnull -> 778
    //   948: aload #8
    //   950: astore_1
    //   951: goto -> 778
    //   954: aload_0
    //   955: getfield curToken : I
    //   958: istore_3
    //   959: iload_3
    //   960: bipush #77
    //   962: if_icmpne -> 1039
    //   965: aload_0
    //   966: getfield libraryModuleNamespace : Ljava/lang/String;
    //   969: ifnull -> 1039
    //   972: aload_0
    //   973: bipush #101
    //   975: ldc_w 'duplicate module declaration'
    //   978: invokevirtual error : (CLjava/lang/String;)V
    //   981: aload_0
    //   982: getfield nesting : I
    //   985: ifeq -> 1065
    //   988: iconst_1
    //   989: istore #7
    //   991: aload_0
    //   992: iload #7
    //   994: invokevirtual skipSpace : (Z)I
    //   997: istore #4
    //   999: iload #4
    //   1001: iflt -> 1380
    //   1004: aload_0
    //   1005: invokevirtual unread : ()V
    //   1008: iload #4
    //   1010: i2c
    //   1011: invokestatic isNameStart : (I)Z
    //   1014: ifeq -> 1380
    //   1017: aload_0
    //   1018: invokevirtual getRawToken : ()I
    //   1021: pop
    //   1022: aload_0
    //   1023: getfield curToken : I
    //   1026: bipush #65
    //   1028: if_icmpeq -> 1071
    //   1031: aload_0
    //   1032: ldc_w 'missing namespace prefix'
    //   1035: invokevirtual syntaxError : (Ljava/lang/String;)Lgnu/expr/Expression;
    //   1038: areturn
    //   1039: aload_0
    //   1040: getfield seenDeclaration : Z
    //   1043: ifeq -> 981
    //   1046: aload_0
    //   1047: getfield interactive : Z
    //   1050: ifne -> 981
    //   1053: aload_0
    //   1054: bipush #101
    //   1056: ldc_w 'namespace declared after function/variable/option'
    //   1059: invokevirtual error : (CLjava/lang/String;)V
    //   1062: goto -> 981
    //   1065: iconst_0
    //   1066: istore #7
    //   1068: goto -> 991
    //   1071: new java/lang/String
    //   1074: dup
    //   1075: aload_0
    //   1076: getfield tokenBuffer : [C
    //   1079: iconst_0
    //   1080: aload_0
    //   1081: getfield tokenBufferLength : I
    //   1084: invokespecial <init> : ([CII)V
    //   1087: astore #8
    //   1089: aload_0
    //   1090: invokevirtual getRawToken : ()I
    //   1093: pop
    //   1094: aload_0
    //   1095: getfield curToken : I
    //   1098: sipush #402
    //   1101: if_icmpeq -> 1112
    //   1104: aload_0
    //   1105: ldc_w 'missing '=' in namespace declaration'
    //   1108: invokevirtual syntaxError : (Ljava/lang/String;)Lgnu/expr/Expression;
    //   1111: areturn
    //   1112: aload_0
    //   1113: invokevirtual getRawToken : ()I
    //   1116: pop
    //   1117: aload_0
    //   1118: getfield curToken : I
    //   1121: bipush #34
    //   1123: if_icmpeq -> 1134
    //   1126: aload_0
    //   1127: ldc_w 'missing uri in namespace declaration'
    //   1130: invokevirtual syntaxError : (Ljava/lang/String;)Lgnu/expr/Expression;
    //   1133: areturn
    //   1134: new java/lang/String
    //   1137: dup
    //   1138: aload_0
    //   1139: getfield tokenBuffer : [C
    //   1142: iconst_0
    //   1143: aload_0
    //   1144: getfield tokenBufferLength : I
    //   1147: invokespecial <init> : ([CII)V
    //   1150: invokevirtual intern : ()Ljava/lang/String;
    //   1153: astore #9
    //   1155: aload #8
    //   1157: invokevirtual intern : ()Ljava/lang/String;
    //   1160: astore #10
    //   1162: aload_0
    //   1163: getfield prologNamespaces : Lgnu/xml/NamespaceBinding;
    //   1166: astore #8
    //   1168: aload #8
    //   1170: getstatic gnu/xquery/lang/XQParser.builtinNamespaces : Lgnu/xml/NamespaceBinding;
    //   1173: if_acmpeq -> 1222
    //   1176: aload #8
    //   1178: invokevirtual getPrefix : ()Ljava/lang/String;
    //   1181: aload #10
    //   1183: if_acmpne -> 1360
    //   1186: aload_0
    //   1187: bipush #101
    //   1189: new java/lang/StringBuilder
    //   1192: dup
    //   1193: invokespecial <init> : ()V
    //   1196: ldc_w 'duplicate declarations for the same namespace prefix ''
    //   1199: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1202: aload #10
    //   1204: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1207: ldc_w '''
    //   1210: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1213: invokevirtual toString : ()Ljava/lang/String;
    //   1216: ldc_w 'XQST0033'
    //   1219: invokevirtual error : (CLjava/lang/String;Ljava/lang/String;)V
    //   1222: aload_0
    //   1223: aload #10
    //   1225: aload #9
    //   1227: invokevirtual pushNamespace : (Ljava/lang/String;Ljava/lang/String;)V
    //   1230: aload_0
    //   1231: aload #10
    //   1233: aload #9
    //   1235: iconst_0
    //   1236: invokevirtual checkAllowedNamespaceDeclaration : (Ljava/lang/String;Ljava/lang/String;Z)V
    //   1239: aload_0
    //   1240: invokevirtual parseSeparator : ()V
    //   1243: iload_3
    //   1244: bipush #77
    //   1246: if_icmpne -> 1376
    //   1249: aload_1
    //   1250: invokevirtual getModule : ()Lgnu/expr/ModuleExp;
    //   1253: astore #8
    //   1255: new java/lang/StringBuilder
    //   1258: dup
    //   1259: invokespecial <init> : ()V
    //   1262: aload #9
    //   1264: invokestatic mangleURI : (Ljava/lang/String;)Ljava/lang/String;
    //   1267: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1270: bipush #46
    //   1272: invokevirtual append : (C)Ljava/lang/StringBuilder;
    //   1275: aload #8
    //   1277: invokevirtual getFileName : ()Ljava/lang/String;
    //   1280: invokestatic makeClassName : (Ljava/lang/String;)Ljava/lang/String;
    //   1283: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1286: invokevirtual toString : ()Ljava/lang/String;
    //   1289: astore #10
    //   1291: aload #8
    //   1293: aload #10
    //   1295: invokevirtual setName : (Ljava/lang/String;)V
    //   1298: aload_1
    //   1299: new gnu/bytecode/ClassType
    //   1302: dup
    //   1303: aload #10
    //   1305: invokespecial <init> : (Ljava/lang/String;)V
    //   1308: putfield mainClass : Lgnu/bytecode/ClassType;
    //   1311: aload #8
    //   1313: aload_1
    //   1314: getfield mainClass : Lgnu/bytecode/ClassType;
    //   1317: invokevirtual setType : (Lgnu/bytecode/ClassType;)V
    //   1320: invokestatic getInstance : ()Lgnu/expr/ModuleManager;
    //   1323: aload_1
    //   1324: invokevirtual find : (Lgnu/expr/Compilation;)Lgnu/expr/ModuleInfo;
    //   1327: aload #9
    //   1329: invokevirtual setNamespaceUri : (Ljava/lang/String;)V
    //   1332: aload #8
    //   1334: aload_1
    //   1335: getfield mainClass : Lgnu/bytecode/ClassType;
    //   1338: invokevirtual setType : (Lgnu/bytecode/ClassType;)V
    //   1341: aload #9
    //   1343: invokevirtual length : ()I
    //   1346: ifne -> 1370
    //   1349: aload_0
    //   1350: ldc_w 'zero-length module namespace'
    //   1353: ldc_w 'XQST0088'
    //   1356: invokevirtual syntaxError : (Ljava/lang/String;Ljava/lang/String;)Lgnu/expr/Expression;
    //   1359: areturn
    //   1360: aload #8
    //   1362: invokevirtual getNext : ()Lgnu/xml/NamespaceBinding;
    //   1365: astore #8
    //   1367: goto -> 1168
    //   1370: aload_0
    //   1371: aload #9
    //   1373: putfield libraryModuleNamespace : Ljava/lang/String;
    //   1376: getstatic gnu/expr/QuoteExp.voidExp : Lgnu/expr/QuoteExp;
    //   1379: areturn
    //   1380: aload_0
    //   1381: ldc_w ''import schema' not implemented'
    //   1384: ldc_w 'XQST0009'
    //   1387: invokevirtual fatal : (Ljava/lang/String;Ljava/lang/String;)V
    //   1390: aload_0
    //   1391: invokevirtual getRawToken : ()I
    //   1394: pop
    //   1395: aconst_null
    //   1396: astore #8
    //   1398: aload_0
    //   1399: ldc_w 'namespace'
    //   1402: invokevirtual match : (Ljava/lang/String;)Z
    //   1405: ifeq -> 1476
    //   1408: aload_0
    //   1409: invokevirtual getRawToken : ()I
    //   1412: pop
    //   1413: aload_0
    //   1414: getfield curToken : I
    //   1417: bipush #65
    //   1419: if_icmpeq -> 1430
    //   1422: aload_0
    //   1423: ldc_w 'missing namespace prefix'
    //   1426: invokevirtual syntaxError : (Ljava/lang/String;)Lgnu/expr/Expression;
    //   1429: areturn
    //   1430: new java/lang/String
    //   1433: dup
    //   1434: aload_0
    //   1435: getfield tokenBuffer : [C
    //   1438: iconst_0
    //   1439: aload_0
    //   1440: getfield tokenBufferLength : I
    //   1443: invokespecial <init> : ([CII)V
    //   1446: astore #8
    //   1448: aload_0
    //   1449: invokevirtual getRawToken : ()I
    //   1452: pop
    //   1453: aload_0
    //   1454: getfield curToken : I
    //   1457: sipush #402
    //   1460: if_icmpeq -> 1471
    //   1463: aload_0
    //   1464: ldc_w 'missing '=' in namespace declaration'
    //   1467: invokevirtual syntaxError : (Ljava/lang/String;)Lgnu/expr/Expression;
    //   1470: areturn
    //   1471: aload_0
    //   1472: invokevirtual getRawToken : ()I
    //   1475: pop
    //   1476: aload_0
    //   1477: getfield curToken : I
    //   1480: bipush #34
    //   1482: if_icmpeq -> 1493
    //   1485: aload_0
    //   1486: ldc_w 'missing uri in namespace declaration'
    //   1489: invokevirtual syntaxError : (Ljava/lang/String;)Lgnu/expr/Expression;
    //   1492: areturn
    //   1493: aload_0
    //   1494: getfield tokenBufferLength : I
    //   1497: ifne -> 1511
    //   1500: aload_0
    //   1501: ldc_w 'zero-length target namespace'
    //   1504: ldc_w 'XQST0088'
    //   1507: invokevirtual syntaxError : (Ljava/lang/String;Ljava/lang/String;)Lgnu/expr/Expression;
    //   1510: areturn
    //   1511: new java/lang/String
    //   1514: dup
    //   1515: aload_0
    //   1516: getfield tokenBuffer : [C
    //   1519: iconst_0
    //   1520: aload_0
    //   1521: getfield tokenBufferLength : I
    //   1524: invokespecial <init> : ([CII)V
    //   1527: invokevirtual intern : ()Ljava/lang/String;
    //   1530: astore #9
    //   1532: aload #8
    //   1534: ifnull -> 1557
    //   1537: aload_0
    //   1538: aload #8
    //   1540: aload #9
    //   1542: iconst_0
    //   1543: invokevirtual checkAllowedNamespaceDeclaration : (Ljava/lang/String;Ljava/lang/String;Z)V
    //   1546: aload_0
    //   1547: aload #8
    //   1549: invokevirtual intern : ()Ljava/lang/String;
    //   1552: aload #9
    //   1554: invokevirtual pushNamespace : (Ljava/lang/String;Ljava/lang/String;)V
    //   1557: aload_0
    //   1558: invokevirtual getRawToken : ()I
    //   1561: pop
    //   1562: invokestatic getInstance : ()Lgnu/expr/ModuleManager;
    //   1565: aload_1
    //   1566: invokevirtual find : (Lgnu/expr/Compilation;)Lgnu/expr/ModuleInfo;
    //   1569: pop
    //   1570: aload_1
    //   1571: invokevirtual getModule : ()Lgnu/expr/ModuleExp;
    //   1574: astore #10
    //   1576: new java/util/Vector
    //   1579: dup
    //   1580: invokespecial <init> : ()V
    //   1583: astore #8
    //   1585: aload #9
    //   1587: invokestatic mangleURI : (Ljava/lang/String;)Ljava/lang/String;
    //   1590: astore #12
    //   1592: aload_1
    //   1593: aload_0
    //   1594: getfield port : Lgnu/text/LineBufferedReader;
    //   1597: invokevirtual getName : ()Ljava/lang/String;
    //   1600: iload #5
    //   1602: iload #6
    //   1604: invokevirtual setLine : (Ljava/lang/String;II)V
    //   1607: aload_0
    //   1608: ldc_w 'at'
    //   1611: invokevirtual match : (Ljava/lang/String;)Z
    //   1614: ifeq -> 1833
    //   1617: aload_0
    //   1618: invokevirtual getRawToken : ()I
    //   1621: pop
    //   1622: aload_0
    //   1623: getfield curToken : I
    //   1626: bipush #34
    //   1628: if_icmpeq -> 1639
    //   1631: aload_0
    //   1632: ldc_w 'missing module location'
    //   1635: invokevirtual syntaxError : (Ljava/lang/String;)Lgnu/expr/Expression;
    //   1638: areturn
    //   1639: new java/lang/String
    //   1642: dup
    //   1643: aload_0
    //   1644: getfield tokenBuffer : [C
    //   1647: iconst_0
    //   1648: aload_0
    //   1649: getfield tokenBufferLength : I
    //   1652: invokespecial <init> : ([CII)V
    //   1655: astore #11
    //   1657: new java/lang/StringBuilder
    //   1660: dup
    //   1661: invokespecial <init> : ()V
    //   1664: aload #9
    //   1666: invokestatic mangleURI : (Ljava/lang/String;)Ljava/lang/String;
    //   1669: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1672: bipush #46
    //   1674: invokevirtual append : (C)Ljava/lang/StringBuilder;
    //   1677: aload #11
    //   1679: invokestatic makeClassName : (Ljava/lang/String;)Ljava/lang/String;
    //   1682: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1685: invokevirtual toString : ()Ljava/lang/String;
    //   1688: astore #12
    //   1690: aload #11
    //   1692: aload #10
    //   1694: invokestatic lookupModuleFromSourcePath : (Ljava/lang/String;Lgnu/expr/ScopeExp;)Lgnu/expr/ModuleInfo;
    //   1697: astore #13
    //   1699: aload #13
    //   1701: ifnonnull -> 1731
    //   1704: aload_1
    //   1705: bipush #101
    //   1707: new java/lang/StringBuilder
    //   1710: dup
    //   1711: invokespecial <init> : ()V
    //   1714: ldc_w 'malformed URL: '
    //   1717: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1720: aload #11
    //   1722: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1725: invokevirtual toString : ()Ljava/lang/String;
    //   1728: invokevirtual error : (CLjava/lang/String;)V
    //   1731: aload #12
    //   1733: aload #13
    //   1735: aconst_null
    //   1736: aload #8
    //   1738: aload #10
    //   1740: aload_1
    //   1741: invokestatic importDefinitions : (Ljava/lang/String;Lgnu/expr/ModuleInfo;Lgnu/mapping/Procedure;Ljava/util/Vector;Lgnu/expr/ScopeExp;Lgnu/expr/Compilation;)Z
    //   1744: pop
    //   1745: aload_0
    //   1746: getfield nesting : I
    //   1749: ifeq -> 1827
    //   1752: iconst_1
    //   1753: istore #7
    //   1755: aload_0
    //   1756: iload #7
    //   1758: invokevirtual skipSpace : (Z)I
    //   1761: istore_3
    //   1762: iload_3
    //   1763: bipush #44
    //   1765: if_icmpeq -> 1617
    //   1768: aload_0
    //   1769: iload_3
    //   1770: invokevirtual unread : (I)V
    //   1773: aload_0
    //   1774: invokevirtual parseSeparator : ()V
    //   1777: aload_1
    //   1778: getfield pendingImports : Ljava/util/Stack;
    //   1781: ifnull -> 1806
    //   1784: aload_1
    //   1785: getfield pendingImports : Ljava/util/Stack;
    //   1788: invokevirtual size : ()I
    //   1791: ifle -> 1806
    //   1794: aload_0
    //   1795: bipush #101
    //   1797: ldc_w 'module import forms a cycle'
    //   1800: ldc_w 'XQST0073'
    //   1803: invokevirtual error : (CLjava/lang/String;Ljava/lang/String;)V
    //   1806: aload #8
    //   1808: invokevirtual size : ()I
    //   1811: anewarray gnu/expr/Expression
    //   1814: astore_1
    //   1815: aload #8
    //   1817: aload_1
    //   1818: invokevirtual toArray : ([Ljava/lang/Object;)[Ljava/lang/Object;
    //   1821: pop
    //   1822: aload_1
    //   1823: invokestatic canonicalize : ([Lgnu/expr/Expression;)Lgnu/expr/Expression;
    //   1826: areturn
    //   1827: iconst_0
    //   1828: istore #7
    //   1830: goto -> 1755
    //   1833: invokestatic getInstance : ()Lgnu/expr/ModuleManager;
    //   1836: astore #11
    //   1838: iconst_0
    //   1839: istore #4
    //   1841: aload #11
    //   1843: aload #12
    //   1845: invokevirtual loadPackageInfo : (Ljava/lang/String;)V
    //   1848: iconst_0
    //   1849: istore_3
    //   1850: aload #11
    //   1852: iload_3
    //   1853: invokevirtual getModule : (I)Lgnu/expr/ModuleInfo;
    //   1856: astore #12
    //   1858: aload #12
    //   1860: ifnonnull -> 1954
    //   1863: iload #4
    //   1865: ifne -> 1895
    //   1868: aload_0
    //   1869: bipush #101
    //   1871: new java/lang/StringBuilder
    //   1874: dup
    //   1875: invokespecial <init> : ()V
    //   1878: ldc_w 'no module found for '
    //   1881: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1884: aload #9
    //   1886: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1889: invokevirtual toString : ()Ljava/lang/String;
    //   1892: invokevirtual error : (CLjava/lang/String;)V
    //   1895: aload_0
    //   1896: getfield curToken : I
    //   1899: bipush #59
    //   1901: if_icmpeq -> 1777
    //   1904: aload_0
    //   1905: invokevirtual parseSeparator : ()V
    //   1908: goto -> 1777
    //   1911: astore #12
    //   1913: aload_0
    //   1914: bipush #101
    //   1916: new java/lang/StringBuilder
    //   1919: dup
    //   1920: invokespecial <init> : ()V
    //   1923: ldc_w 'error loading map for '
    //   1926: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1929: aload #9
    //   1931: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1934: ldc_w ' - '
    //   1937: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1940: aload #12
    //   1942: invokevirtual append : (Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   1945: invokevirtual toString : ()Ljava/lang/String;
    //   1948: invokevirtual error : (CLjava/lang/String;)V
    //   1951: goto -> 1848
    //   1954: aload #9
    //   1956: aload #12
    //   1958: invokevirtual getNamespaceUri : ()Ljava/lang/String;
    //   1961: invokevirtual equals : (Ljava/lang/Object;)Z
    //   1964: ifne -> 1974
    //   1967: iload_3
    //   1968: iconst_1
    //   1969: iadd
    //   1970: istore_3
    //   1971: goto -> 1850
    //   1974: iload #4
    //   1976: iconst_1
    //   1977: iadd
    //   1978: istore #4
    //   1980: aload #12
    //   1982: invokevirtual getClassName : ()Ljava/lang/String;
    //   1985: aload #12
    //   1987: aconst_null
    //   1988: aload #8
    //   1990: aload #10
    //   1992: aload_1
    //   1993: invokestatic importDefinitions : (Ljava/lang/String;Lgnu/expr/ModuleInfo;Lgnu/mapping/Procedure;Ljava/util/Vector;Lgnu/expr/ScopeExp;Lgnu/expr/Compilation;)Z
    //   1996: pop
    //   1997: goto -> 1967
    //   2000: aload_0
    //   2001: getfield defaultCollator : Lgnu/xquery/util/NamedCollator;
    //   2004: ifnull -> 2026
    //   2007: aload_0
    //   2008: getfield interactive : Z
    //   2011: ifne -> 2026
    //   2014: aload_0
    //   2015: bipush #101
    //   2017: ldc_w 'duplicate default collation declaration'
    //   2020: ldc_w 'XQST0038'
    //   2023: invokevirtual error : (CLjava/lang/String;Ljava/lang/String;)V
    //   2026: aload_0
    //   2027: invokevirtual parseURILiteral : ()Ljava/lang/Object;
    //   2030: astore_1
    //   2031: aload_1
    //   2032: instanceof gnu/expr/Expression
    //   2035: ifeq -> 2043
    //   2038: aload_1
    //   2039: checkcast gnu/expr/Expression
    //   2042: areturn
    //   2043: aload_1
    //   2044: checkcast java/lang/String
    //   2047: astore #8
    //   2049: aload #8
    //   2051: astore_1
    //   2052: aload_0
    //   2053: aload #8
    //   2055: invokevirtual resolveAgainstBaseUri : (Ljava/lang/String;)Ljava/lang/String;
    //   2058: astore #8
    //   2060: aload #8
    //   2062: astore_1
    //   2063: aload_0
    //   2064: aload #8
    //   2066: invokestatic make : (Ljava/lang/String;)Lgnu/xquery/util/NamedCollator;
    //   2069: putfield defaultCollator : Lgnu/xquery/util/NamedCollator;
    //   2072: aload_0
    //   2073: invokevirtual parseSeparator : ()V
    //   2076: getstatic gnu/expr/QuoteExp.voidExp : Lgnu/expr/QuoteExp;
    //   2079: areturn
    //   2080: astore #8
    //   2082: aload_0
    //   2083: getstatic gnu/xquery/util/NamedCollator.codepointCollation : Lgnu/xquery/util/NamedCollator;
    //   2086: putfield defaultCollator : Lgnu/xquery/util/NamedCollator;
    //   2089: aload_0
    //   2090: bipush #101
    //   2092: new java/lang/StringBuilder
    //   2095: dup
    //   2096: invokespecial <init> : ()V
    //   2099: ldc_w 'unknown collation ''
    //   2102: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2105: aload_1
    //   2106: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2109: ldc_w '''
    //   2112: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2115: invokevirtual toString : ()Ljava/lang/String;
    //   2118: ldc_w 'XQST0038'
    //   2121: invokevirtual error : (CLjava/lang/String;Ljava/lang/String;)V
    //   2124: goto -> 2072
    //   2127: aload_0
    //   2128: getfield curToken : I
    //   2131: bipush #79
    //   2133: if_icmpne -> 2240
    //   2136: iconst_1
    //   2137: istore_3
    //   2138: iload_3
    //   2139: ifeq -> 2245
    //   2142: ldc_w '(functions)'
    //   2145: astore_1
    //   2146: aload_0
    //   2147: getfield prologNamespaces : Lgnu/xml/NamespaceBinding;
    //   2150: aload_1
    //   2151: getstatic gnu/xquery/lang/XQParser.builtinNamespaces : Lgnu/xml/NamespaceBinding;
    //   2154: invokevirtual resolve : (Ljava/lang/String;Lgnu/xml/NamespaceBinding;)Ljava/lang/String;
    //   2157: ifnull -> 2252
    //   2160: aload_0
    //   2161: bipush #101
    //   2163: ldc_w 'duplicate default namespace declaration'
    //   2166: ldc_w 'XQST0066'
    //   2169: invokevirtual error : (CLjava/lang/String;Ljava/lang/String;)V
    //   2172: aload_0
    //   2173: invokevirtual getRawToken : ()I
    //   2176: pop
    //   2177: aload_0
    //   2178: ldc_w 'namespace'
    //   2181: invokevirtual match : (Ljava/lang/String;)Z
    //   2184: ifeq -> 2278
    //   2187: aload_0
    //   2188: invokevirtual getRawToken : ()I
    //   2191: pop
    //   2192: aload_0
    //   2193: getfield curToken : I
    //   2196: sipush #402
    //   2199: if_icmpeq -> 2211
    //   2202: aload_0
    //   2203: getfield curToken : I
    //   2206: bipush #76
    //   2208: if_icmpne -> 2223
    //   2211: aload_0
    //   2212: ldc_w 'extra '=' in default namespace declaration'
    //   2215: invokevirtual warnOldVersion : (Ljava/lang/String;)V
    //   2218: aload_0
    //   2219: invokevirtual getRawToken : ()I
    //   2222: pop
    //   2223: aload_0
    //   2224: getfield curToken : I
    //   2227: bipush #34
    //   2229: if_icmpeq -> 2315
    //   2232: aload_0
    //   2233: ldc_w 'missing namespace uri'
    //   2236: invokevirtual declError : (Ljava/lang/String;)Lgnu/expr/Expression;
    //   2239: areturn
    //   2240: iconst_0
    //   2241: istore_3
    //   2242: goto -> 2138
    //   2245: getstatic gnu/xquery/lang/XQuery.DEFAULT_ELEMENT_PREFIX : Ljava/lang/String;
    //   2248: astore_1
    //   2249: goto -> 2146
    //   2252: aload_0
    //   2253: getfield seenDeclaration : Z
    //   2256: ifeq -> 2172
    //   2259: aload_0
    //   2260: getfield interactive : Z
    //   2263: ifne -> 2172
    //   2266: aload_0
    //   2267: bipush #101
    //   2269: ldc_w 'default namespace declared after function/variable/option'
    //   2272: invokevirtual error : (CLjava/lang/String;)V
    //   2275: goto -> 2172
    //   2278: aload_0
    //   2279: getfield curToken : I
    //   2282: bipush #34
    //   2284: if_icmpeq -> 2305
    //   2287: aload_0
    //   2288: getfield curToken : I
    //   2291: sipush #402
    //   2294: if_icmpeq -> 2305
    //   2297: aload_0
    //   2298: ldc_w 'expected 'namespace' keyword'
    //   2301: invokevirtual declError : (Ljava/lang/String;)Lgnu/expr/Expression;
    //   2304: areturn
    //   2305: aload_0
    //   2306: ldc_w 'expected 'namespace' keyword'
    //   2309: invokevirtual warnOldVersion : (Ljava/lang/String;)V
    //   2312: goto -> 2192
    //   2315: new java/lang/String
    //   2318: dup
    //   2319: aload_0
    //   2320: getfield tokenBuffer : [C
    //   2323: iconst_0
    //   2324: aload_0
    //   2325: getfield tokenBufferLength : I
    //   2328: invokespecial <init> : ([CII)V
    //   2331: invokevirtual intern : ()Ljava/lang/String;
    //   2334: astore #8
    //   2336: iload_3
    //   2337: ifeq -> 2382
    //   2340: aload_0
    //   2341: iconst_1
    //   2342: anewarray gnu/mapping/Namespace
    //   2345: putfield functionNamespacePath : [Lgnu/mapping/Namespace;
    //   2348: aload_0
    //   2349: getfield functionNamespacePath : [Lgnu/mapping/Namespace;
    //   2352: iconst_0
    //   2353: aload #8
    //   2355: invokestatic valueOf : (Ljava/lang/String;)Lgnu/mapping/Namespace;
    //   2358: aastore
    //   2359: aload_0
    //   2360: aload_1
    //   2361: aload #8
    //   2363: invokevirtual pushNamespace : (Ljava/lang/String;Ljava/lang/String;)V
    //   2366: aload_0
    //   2367: aload_1
    //   2368: aload #8
    //   2370: iconst_0
    //   2371: invokevirtual checkAllowedNamespaceDeclaration : (Ljava/lang/String;Ljava/lang/String;Z)V
    //   2374: aload_0
    //   2375: invokevirtual parseSeparator : ()V
    //   2378: getstatic gnu/expr/QuoteExp.voidExp : Lgnu/expr/QuoteExp;
    //   2381: areturn
    //   2382: aload_0
    //   2383: aload #8
    //   2385: putfield defaultElementNamespace : Ljava/lang/String;
    //   2388: goto -> 2359
    //   2391: aload_0
    //   2392: invokevirtual getRawToken : ()I
    //   2395: pop
    //   2396: aload_0
    //   2397: getfield curToken : I
    //   2400: sipush #402
    //   2403: if_icmpne -> 2418
    //   2406: aload_0
    //   2407: ldc_w 'obsolate '=' in boundary-space declaration'
    //   2410: invokevirtual warnOldVersion : (Ljava/lang/String;)V
    //   2413: aload_0
    //   2414: invokevirtual getRawToken : ()I
    //   2417: pop
    //   2418: aload_0
    //   2419: getfield boundarySpaceDeclarationSeen : Z
    //   2422: ifeq -> 2443
    //   2425: aload_0
    //   2426: getfield interactive : Z
    //   2429: ifne -> 2443
    //   2432: aload_0
    //   2433: ldc_w 'duplicate 'declare boundary-space' seen'
    //   2436: ldc_w 'XQST0068'
    //   2439: invokevirtual syntaxError : (Ljava/lang/String;Ljava/lang/String;)Lgnu/expr/Expression;
    //   2442: pop
    //   2443: aload_0
    //   2444: iconst_1
    //   2445: putfield boundarySpaceDeclarationSeen : Z
    //   2448: aload_0
    //   2449: ldc_w 'preserve'
    //   2452: invokevirtual match : (Ljava/lang/String;)Z
    //   2455: ifeq -> 2471
    //   2458: aload_0
    //   2459: iconst_1
    //   2460: putfield boundarySpacePreserve : Z
    //   2463: aload_0
    //   2464: invokevirtual parseSeparator : ()V
    //   2467: getstatic gnu/expr/QuoteExp.voidExp : Lgnu/expr/QuoteExp;
    //   2470: areturn
    //   2471: aload_0
    //   2472: ldc_w 'strip'
    //   2475: invokevirtual match : (Ljava/lang/String;)Z
    //   2478: ifeq -> 2489
    //   2481: aload_0
    //   2482: iconst_0
    //   2483: putfield boundarySpacePreserve : Z
    //   2486: goto -> 2463
    //   2489: aload_0
    //   2490: ldc_w 'skip'
    //   2493: invokevirtual match : (Ljava/lang/String;)Z
    //   2496: ifeq -> 2514
    //   2499: aload_0
    //   2500: ldc_w 'update: declare boundary-space skip -> strip'
    //   2503: invokevirtual warnOldVersion : (Ljava/lang/String;)V
    //   2506: aload_0
    //   2507: iconst_0
    //   2508: putfield boundarySpacePreserve : Z
    //   2511: goto -> 2463
    //   2514: aload_0
    //   2515: ldc_w 'boundary-space declaration must be preserve or strip'
    //   2518: invokevirtual syntaxError : (Ljava/lang/String;)Lgnu/expr/Expression;
    //   2521: areturn
    //   2522: aload_0
    //   2523: invokevirtual getRawToken : ()I
    //   2526: pop
    //   2527: aload_0
    //   2528: getfield constructionModeDeclarationSeen : Z
    //   2531: ifeq -> 2552
    //   2534: aload_0
    //   2535: getfield interactive : Z
    //   2538: ifne -> 2552
    //   2541: aload_0
    //   2542: ldc_w 'duplicate 'declare construction' seen'
    //   2545: ldc_w 'XQST0067'
    //   2548: invokevirtual syntaxError : (Ljava/lang/String;Ljava/lang/String;)Lgnu/expr/Expression;
    //   2551: pop
    //   2552: aload_0
    //   2553: iconst_1
    //   2554: putfield constructionModeDeclarationSeen : Z
    //   2557: aload_0
    //   2558: ldc_w 'strip'
    //   2561: invokevirtual match : (Ljava/lang/String;)Z
    //   2564: ifeq -> 2580
    //   2567: aload_0
    //   2568: iconst_1
    //   2569: putfield constructionModeStrip : Z
    //   2572: aload_0
    //   2573: invokevirtual parseSeparator : ()V
    //   2576: getstatic gnu/expr/QuoteExp.voidExp : Lgnu/expr/QuoteExp;
    //   2579: areturn
    //   2580: aload_0
    //   2581: ldc_w 'preserve'
    //   2584: invokevirtual match : (Ljava/lang/String;)Z
    //   2587: ifeq -> 2598
    //   2590: aload_0
    //   2591: iconst_0
    //   2592: putfield constructionModeStrip : Z
    //   2595: goto -> 2572
    //   2598: aload_0
    //   2599: ldc_w 'construction declaration must be strip or preserve'
    //   2602: invokevirtual syntaxError : (Ljava/lang/String;)Lgnu/expr/Expression;
    //   2605: areturn
    //   2606: aload_0
    //   2607: invokevirtual getRawToken : ()I
    //   2610: pop
    //   2611: aload_0
    //   2612: getfield copyNamespacesDeclarationSeen : Z
    //   2615: ifeq -> 2636
    //   2618: aload_0
    //   2619: getfield interactive : Z
    //   2622: ifne -> 2636
    //   2625: aload_0
    //   2626: ldc_w 'duplicate 'declare copy-namespaces' seen'
    //   2629: ldc_w 'XQST0055'
    //   2632: invokevirtual syntaxError : (Ljava/lang/String;Ljava/lang/String;)Lgnu/expr/Expression;
    //   2635: pop
    //   2636: aload_0
    //   2637: iconst_1
    //   2638: putfield copyNamespacesDeclarationSeen : Z
    //   2641: aload_0
    //   2642: ldc_w 'preserve'
    //   2645: invokevirtual match : (Ljava/lang/String;)Z
    //   2648: ifeq -> 2683
    //   2651: aload_0
    //   2652: aload_0
    //   2653: getfield copyNamespacesMode : I
    //   2656: iconst_1
    //   2657: ior
    //   2658: putfield copyNamespacesMode : I
    //   2661: aload_0
    //   2662: invokevirtual getRawToken : ()I
    //   2665: pop
    //   2666: aload_0
    //   2667: getfield curToken : I
    //   2670: bipush #44
    //   2672: if_icmpeq -> 2715
    //   2675: aload_0
    //   2676: ldc_w 'missing ',' in copy-namespaces declaration'
    //   2679: invokevirtual syntaxError : (Ljava/lang/String;)Lgnu/expr/Expression;
    //   2682: areturn
    //   2683: aload_0
    //   2684: ldc_w 'no-preserve'
    //   2687: invokevirtual match : (Ljava/lang/String;)Z
    //   2690: ifeq -> 2707
    //   2693: aload_0
    //   2694: aload_0
    //   2695: getfield copyNamespacesMode : I
    //   2698: bipush #-2
    //   2700: iand
    //   2701: putfield copyNamespacesMode : I
    //   2704: goto -> 2661
    //   2707: aload_0
    //   2708: ldc_w 'expected 'preserve' or 'no-preserve' after 'declare copy-namespaces''
    //   2711: invokevirtual syntaxError : (Ljava/lang/String;)Lgnu/expr/Expression;
    //   2714: areturn
    //   2715: aload_0
    //   2716: invokevirtual getRawToken : ()I
    //   2719: pop
    //   2720: aload_0
    //   2721: ldc_w 'inherit'
    //   2724: invokevirtual match : (Ljava/lang/String;)Z
    //   2727: ifeq -> 2748
    //   2730: aload_0
    //   2731: aload_0
    //   2732: getfield copyNamespacesMode : I
    //   2735: iconst_2
    //   2736: ior
    //   2737: putfield copyNamespacesMode : I
    //   2740: aload_0
    //   2741: invokevirtual parseSeparator : ()V
    //   2744: getstatic gnu/expr/QuoteExp.voidExp : Lgnu/expr/QuoteExp;
    //   2747: areturn
    //   2748: aload_0
    //   2749: ldc_w 'no-inherit'
    //   2752: invokevirtual match : (Ljava/lang/String;)Z
    //   2755: ifeq -> 2772
    //   2758: aload_0
    //   2759: aload_0
    //   2760: getfield copyNamespacesMode : I
    //   2763: bipush #-3
    //   2765: iand
    //   2766: putfield copyNamespacesMode : I
    //   2769: goto -> 2740
    //   2772: aload_0
    //   2773: ldc_w 'expected 'inherit' or 'no-inherit' in copy-namespaces declaration'
    //   2776: invokevirtual syntaxError : (Ljava/lang/String;)Lgnu/expr/Expression;
    //   2779: areturn
    //   2780: aload_0
    //   2781: invokevirtual getRawToken : ()I
    //   2784: pop
    //   2785: aload_0
    //   2786: ldc_w 'empty'
    //   2789: invokevirtual match : (Ljava/lang/String;)Z
    //   2792: istore #7
    //   2794: aload_0
    //   2795: getfield emptyOrderDeclarationSeen : Z
    //   2798: ifeq -> 2819
    //   2801: aload_0
    //   2802: getfield interactive : Z
    //   2805: ifne -> 2819
    //   2808: aload_0
    //   2809: ldc_w 'duplicate 'declare default empty order' seen'
    //   2812: ldc_w 'XQST0069'
    //   2815: invokevirtual syntaxError : (Ljava/lang/String;Ljava/lang/String;)Lgnu/expr/Expression;
    //   2818: pop
    //   2819: aload_0
    //   2820: iconst_1
    //   2821: putfield emptyOrderDeclarationSeen : Z
    //   2824: iload #7
    //   2826: ifeq -> 2858
    //   2829: aload_0
    //   2830: invokevirtual getRawToken : ()I
    //   2833: pop
    //   2834: aload_0
    //   2835: ldc_w 'greatest'
    //   2838: invokevirtual match : (Ljava/lang/String;)Z
    //   2841: ifeq -> 2869
    //   2844: aload_0
    //   2845: bipush #71
    //   2847: putfield defaultEmptyOrder : C
    //   2850: aload_0
    //   2851: invokevirtual parseSeparator : ()V
    //   2854: getstatic gnu/expr/QuoteExp.voidExp : Lgnu/expr/QuoteExp;
    //   2857: areturn
    //   2858: aload_0
    //   2859: ldc_w 'expected 'empty greatest' or 'empty least''
    //   2862: invokevirtual syntaxError : (Ljava/lang/String;)Lgnu/expr/Expression;
    //   2865: pop
    //   2866: goto -> 2834
    //   2869: aload_0
    //   2870: ldc_w 'least'
    //   2873: invokevirtual match : (Ljava/lang/String;)Z
    //   2876: ifeq -> 2888
    //   2879: aload_0
    //   2880: bipush #76
    //   2882: putfield defaultEmptyOrder : C
    //   2885: goto -> 2850
    //   2888: aload_0
    //   2889: ldc_w 'expected 'empty greatest' or 'empty least''
    //   2892: invokevirtual syntaxError : (Ljava/lang/String;)Lgnu/expr/Expression;
    //   2895: areturn
    //   2896: aload_0
    //   2897: invokevirtual getRawToken : ()I
    //   2900: pop
    //   2901: aload_0
    //   2902: getfield curToken : I
    //   2905: bipush #81
    //   2907: if_icmpeq -> 2931
    //   2910: aload_0
    //   2911: ldc_w 'expected QName after 'declare option''
    //   2914: invokevirtual syntaxError : (Ljava/lang/String;)Lgnu/expr/Expression;
    //   2917: pop
    //   2918: aload_0
    //   2919: invokevirtual parseSeparator : ()V
    //   2922: aload_0
    //   2923: iconst_1
    //   2924: putfield seenDeclaration : Z
    //   2927: getstatic gnu/expr/QuoteExp.voidExp : Lgnu/expr/QuoteExp;
    //   2930: areturn
    //   2931: new java/lang/String
    //   2934: dup
    //   2935: aload_0
    //   2936: getfield tokenBuffer : [C
    //   2939: iconst_0
    //   2940: aload_0
    //   2941: getfield tokenBufferLength : I
    //   2944: invokespecial <init> : ([CII)V
    //   2947: astore_1
    //   2948: aload_0
    //   2949: invokevirtual getMessages : ()Lgnu/text/SourceMessages;
    //   2952: aload_0
    //   2953: getfield port : Lgnu/text/LineBufferedReader;
    //   2956: invokevirtual getName : ()Ljava/lang/String;
    //   2959: aload_0
    //   2960: getfield curLine : I
    //   2963: aload_0
    //   2964: getfield curColumn : I
    //   2967: invokevirtual setLine : (Ljava/lang/String;II)V
    //   2970: aload_0
    //   2971: aload_1
    //   2972: iconst_0
    //   2973: invokevirtual namespaceResolve : (Ljava/lang/String;Z)Lgnu/mapping/Symbol;
    //   2976: astore #8
    //   2978: aload_0
    //   2979: invokevirtual getRawToken : ()I
    //   2982: pop
    //   2983: aload_0
    //   2984: getfield curToken : I
    //   2987: bipush #34
    //   2989: if_icmpeq -> 3025
    //   2992: aload_0
    //   2993: new java/lang/StringBuilder
    //   2996: dup
    //   2997: invokespecial <init> : ()V
    //   3000: ldc_w 'expected string literal after 'declare option '
    //   3003: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   3006: aload_1
    //   3007: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   3010: bipush #39
    //   3012: invokevirtual append : (C)Ljava/lang/StringBuilder;
    //   3015: invokevirtual toString : ()Ljava/lang/String;
    //   3018: invokevirtual syntaxError : (Ljava/lang/String;)Lgnu/expr/Expression;
    //   3021: pop
    //   3022: goto -> 2918
    //   3025: aload_0
    //   3026: aload #8
    //   3028: new java/lang/String
    //   3031: dup
    //   3032: aload_0
    //   3033: getfield tokenBuffer : [C
    //   3036: iconst_0
    //   3037: aload_0
    //   3038: getfield tokenBufferLength : I
    //   3041: invokespecial <init> : ([CII)V
    //   3044: invokevirtual handleOption : (Lgnu/mapping/Symbol;Ljava/lang/String;)V
    //   3047: goto -> 2918
    //   3050: aload_0
    //   3051: getfield orderingModeSeen : Z
    //   3054: ifeq -> 3075
    //   3057: aload_0
    //   3058: getfield interactive : Z
    //   3061: ifne -> 3075
    //   3064: aload_0
    //   3065: ldc_w 'duplicate 'declare ordering' seen'
    //   3068: ldc_w 'XQST0065'
    //   3071: invokevirtual syntaxError : (Ljava/lang/String;Ljava/lang/String;)Lgnu/expr/Expression;
    //   3074: pop
    //   3075: aload_0
    //   3076: iconst_1
    //   3077: putfield orderingModeSeen : Z
    //   3080: aload_0
    //   3081: invokevirtual getRawToken : ()I
    //   3084: pop
    //   3085: aload_0
    //   3086: ldc_w 'ordered'
    //   3089: invokevirtual match : (Ljava/lang/String;)Z
    //   3092: ifeq -> 3108
    //   3095: aload_0
    //   3096: iconst_0
    //   3097: putfield orderingModeUnordered : Z
    //   3100: aload_0
    //   3101: invokevirtual parseSeparator : ()V
    //   3104: getstatic gnu/expr/QuoteExp.voidExp : Lgnu/expr/QuoteExp;
    //   3107: areturn
    //   3108: aload_0
    //   3109: ldc_w 'unordered'
    //   3112: invokevirtual match : (Ljava/lang/String;)Z
    //   3115: ifeq -> 3126
    //   3118: aload_0
    //   3119: iconst_1
    //   3120: putfield orderingModeUnordered : Z
    //   3123: goto -> 3100
    //   3126: aload_0
    //   3127: ldc_w 'ordering declaration must be ordered or unordered'
    //   3130: invokevirtual syntaxError : (Ljava/lang/String;)Lgnu/expr/Expression;
    //   3133: areturn
    //   3134: aload_0
    //   3135: getfield parseCount : I
    //   3138: iconst_1
    //   3139: if_icmpeq -> 3258
    //   3142: aload_0
    //   3143: bipush #101
    //   3145: ldc_w ''xquery version' does not start module'
    //   3148: invokevirtual error : (CLjava/lang/String;)V
    //   3151: aload_0
    //   3152: invokevirtual getRawToken : ()I
    //   3155: pop
    //   3156: aload_0
    //   3157: getfield curToken : I
    //   3160: bipush #34
    //   3162: if_icmpne -> 3277
    //   3165: new java/lang/String
    //   3168: dup
    //   3169: aload_0
    //   3170: getfield tokenBuffer : [C
    //   3173: iconst_0
    //   3174: aload_0
    //   3175: getfield tokenBufferLength : I
    //   3178: invokespecial <init> : ([CII)V
    //   3181: astore_1
    //   3182: aload_1
    //   3183: ldc_w '1.0'
    //   3186: invokevirtual equals : (Ljava/lang/Object;)Z
    //   3189: ifne -> 3221
    //   3192: aload_0
    //   3193: bipush #101
    //   3195: new java/lang/StringBuilder
    //   3198: dup
    //   3199: invokespecial <init> : ()V
    //   3202: ldc_w 'unrecognized xquery version '
    //   3205: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   3208: aload_1
    //   3209: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   3212: invokevirtual toString : ()Ljava/lang/String;
    //   3215: ldc_w 'XQST0031'
    //   3218: invokevirtual error : (CLjava/lang/String;Ljava/lang/String;)V
    //   3221: aload_0
    //   3222: invokevirtual getRawToken : ()I
    //   3225: pop
    //   3226: aload_0
    //   3227: ldc_w 'encoding'
    //   3230: invokevirtual match : (Ljava/lang/String;)Z
    //   3233: ifeq -> 3466
    //   3236: aload_0
    //   3237: invokevirtual getRawToken : ()I
    //   3240: pop
    //   3241: aload_0
    //   3242: getfield curToken : I
    //   3245: bipush #34
    //   3247: if_icmpeq -> 3285
    //   3250: aload_0
    //   3251: ldc_w 'invalid encoding specification'
    //   3254: invokevirtual syntaxError : (Ljava/lang/String;)Lgnu/expr/Expression;
    //   3257: areturn
    //   3258: aload_0
    //   3259: getfield commentCount : I
    //   3262: ifle -> 3151
    //   3265: aload_0
    //   3266: bipush #119
    //   3268: ldc_w 'comments should not precede 'xquery version''
    //   3271: invokevirtual error : (CLjava/lang/String;)V
    //   3274: goto -> 3151
    //   3277: aload_0
    //   3278: ldc_w 'missing version string after 'xquery version''
    //   3281: invokevirtual syntaxError : (Ljava/lang/String;)Lgnu/expr/Expression;
    //   3284: areturn
    //   3285: new java/lang/String
    //   3288: dup
    //   3289: aload_0
    //   3290: getfield tokenBuffer : [C
    //   3293: iconst_0
    //   3294: aload_0
    //   3295: getfield tokenBufferLength : I
    //   3298: invokespecial <init> : ([CII)V
    //   3301: pop
    //   3302: aload_0
    //   3303: getfield tokenBufferLength : I
    //   3306: istore #4
    //   3308: iload #4
    //   3310: ifne -> 3440
    //   3313: iconst_1
    //   3314: istore_3
    //   3315: iload #4
    //   3317: iconst_1
    //   3318: isub
    //   3319: istore #5
    //   3321: iload #5
    //   3323: iflt -> 3445
    //   3326: iload_3
    //   3327: ifne -> 3445
    //   3330: aload_0
    //   3331: getfield tokenBuffer : [C
    //   3334: iload #5
    //   3336: caload
    //   3337: istore #6
    //   3339: iload #6
    //   3341: bipush #65
    //   3343: if_icmplt -> 3357
    //   3346: iload #5
    //   3348: istore #4
    //   3350: iload #6
    //   3352: bipush #90
    //   3354: if_icmple -> 3315
    //   3357: iload #6
    //   3359: bipush #97
    //   3361: if_icmplt -> 3375
    //   3364: iload #5
    //   3366: istore #4
    //   3368: iload #6
    //   3370: bipush #122
    //   3372: if_icmple -> 3315
    //   3375: iload #5
    //   3377: ifeq -> 3431
    //   3380: iload #6
    //   3382: bipush #48
    //   3384: if_icmplt -> 3398
    //   3387: iload #5
    //   3389: istore #4
    //   3391: iload #6
    //   3393: bipush #57
    //   3395: if_icmple -> 3315
    //   3398: iload #5
    //   3400: istore #4
    //   3402: iload #6
    //   3404: bipush #46
    //   3406: if_icmpeq -> 3315
    //   3409: iload #5
    //   3411: istore #4
    //   3413: iload #6
    //   3415: bipush #95
    //   3417: if_icmpeq -> 3315
    //   3420: iload #5
    //   3422: istore #4
    //   3424: iload #6
    //   3426: bipush #45
    //   3428: if_icmpeq -> 3315
    //   3431: iconst_1
    //   3432: istore_3
    //   3433: iload #5
    //   3435: istore #4
    //   3437: goto -> 3315
    //   3440: iconst_0
    //   3441: istore_3
    //   3442: goto -> 3315
    //   3445: iload_3
    //   3446: ifeq -> 3461
    //   3449: aload_0
    //   3450: bipush #101
    //   3452: ldc_w 'invalid encoding name syntax'
    //   3455: ldc_w 'XQST0087'
    //   3458: invokevirtual error : (CLjava/lang/String;Ljava/lang/String;)V
    //   3461: aload_0
    //   3462: invokevirtual getRawToken : ()I
    //   3465: pop
    //   3466: aload_0
    //   3467: getfield curToken : I
    //   3470: bipush #59
    //   3472: if_icmpeq -> 3483
    //   3475: aload_0
    //   3476: ldc_w 'missing ';''
    //   3479: invokevirtual syntaxError : (Ljava/lang/String;)Lgnu/expr/Expression;
    //   3482: pop
    //   3483: getstatic gnu/expr/QuoteExp.voidExp : Lgnu/expr/QuoteExp;
    //   3486: areturn
    //   3487: aload_0
    //   3488: getfield baseURIDeclarationSeen : Z
    //   3491: ifeq -> 3512
    //   3494: aload_0
    //   3495: getfield interactive : Z
    //   3498: ifne -> 3512
    //   3501: aload_0
    //   3502: ldc_w 'duplicate 'declare base-uri' seen'
    //   3505: ldc_w 'XQST0032'
    //   3508: invokevirtual syntaxError : (Ljava/lang/String;Ljava/lang/String;)Lgnu/expr/Expression;
    //   3511: pop
    //   3512: aload_0
    //   3513: iconst_1
    //   3514: putfield baseURIDeclarationSeen : Z
    //   3517: aload_0
    //   3518: invokevirtual parseURILiteral : ()Ljava/lang/Object;
    //   3521: astore_1
    //   3522: aload_1
    //   3523: instanceof gnu/expr/Expression
    //   3526: ifeq -> 3534
    //   3529: aload_1
    //   3530: checkcast gnu/expr/Expression
    //   3533: areturn
    //   3534: aload_0
    //   3535: invokevirtual parseSeparator : ()V
    //   3538: aload_0
    //   3539: aload_1
    //   3540: checkcast java/lang/String
    //   3543: invokevirtual setStaticBaseUri : (Ljava/lang/String;)V
    //   3546: getstatic gnu/expr/QuoteExp.voidExp : Lgnu/expr/QuoteExp;
    //   3549: areturn
    //   3550: astore #12
    //   3552: goto -> 1848
    // Exception table:
    //   from	to	target	type
    //   1841	1848	3550	java/lang/ClassNotFoundException
    //   1841	1848	1911	java/lang/Throwable
    //   2052	2060	2080	java/lang/Exception
    //   2063	2072	2080	java/lang/Exception
  }
  
  Expression parseBinaryExpr(int paramInt) throws IOException, SyntaxException {
    Expression expression = parseUnaryExpr();
    while (true) {
      Expression expression1;
      Expression expression2;
      int i = peekOperator();
      if (i == 10 || (i == 404 && peek() == 47))
        return expression; 
      int j = priority(i);
      if (j >= paramInt) {
        char c = pushNesting('%');
        getRawToken();
        popNesting(c);
        if (i >= 422 && i <= 425) {
          ReferenceExp referenceExp2;
          ApplyExp applyExp3;
          Expression expression3;
          ApplyExp applyExp2;
          ReferenceExp referenceExp1;
          ApplyExp applyExp1;
          if (i == 425 || i == 424)
            this.parseContext = 67; 
          Expression expression4 = parseDataType();
          this.parseContext = 0;
          Expression[] arrayOfExpression = new Expression[2];
          switch (i) {
            default:
              arrayOfExpression[0] = expression4;
              arrayOfExpression[1] = expression;
              referenceExp2 = new ReferenceExp(XQResolveNames.castAsDecl);
              applyExp3 = new ApplyExp((Expression)referenceExp2, arrayOfExpression);
              continue;
            case 422:
              arrayOfExpression[0] = (Expression)applyExp3;
              arrayOfExpression[1] = expression4;
              expression3 = makeFunctionExp("gnu.xquery.lang.XQParser", "instanceOf");
              applyExp2 = new ApplyExp(expression3, arrayOfExpression);
              continue;
            case 424:
              arrayOfExpression[0] = (Expression)applyExp2;
              arrayOfExpression[1] = expression4;
              referenceExp1 = new ReferenceExp(XQResolveNames.castableAsDecl);
              applyExp1 = new ApplyExp((Expression)referenceExp1, arrayOfExpression);
              continue;
            case 423:
              break;
          } 
          arrayOfExpression[0] = expression4;
          arrayOfExpression[1] = (Expression)applyExp1;
          expression1 = makeFunctionExp("gnu.xquery.lang.XQParser", "treatAs");
        } else {
          ApplyExp applyExp1;
          IfExp ifExp;
          if (i == 422) {
            Expression expression3 = parseDataType();
            applyExp1 = new ApplyExp(makeFunctionExp("gnu.xquery.lang.XQParser", "instanceOf"), new Expression[] { expression1, expression3 });
            continue;
          } 
          expression2 = parseBinaryExpr(j + 1);
          if (i == 401) {
            ifExp = new IfExp(booleanValue((Expression)applyExp1), booleanValue(expression2), (Expression)XQuery.falseExp);
            continue;
          } 
          if (i == 400) {
            ifExp = new IfExp(booleanValue((Expression)ifExp), (Expression)XQuery.trueExp, booleanValue(expression2));
            continue;
          } 
          expression1 = makeBinary(i, (Expression)ifExp, expression2);
          continue;
        } 
      } else {
        return expression1;
      } 
      ApplyExp applyExp = new ApplyExp(expression1, (Expression[])expression2);
    } 
  }
  
  void parseContent(char paramChar, Vector paramVector) throws IOException, SyntaxException {
    // Byte code:
    //   0: aload_0
    //   1: iconst_0
    //   2: putfield tokenBufferLength : I
    //   5: aload_2
    //   6: invokevirtual size : ()I
    //   9: iconst_1
    //   10: isub
    //   11: istore #4
    //   13: aload_0
    //   14: getfield boundarySpacePreserve : Z
    //   17: ifne -> 140
    //   20: iload_1
    //   21: bipush #60
    //   23: if_icmpne -> 140
    //   26: iconst_1
    //   27: istore #9
    //   29: iload #9
    //   31: istore #8
    //   33: aload_0
    //   34: invokevirtual read : ()I
    //   37: istore #5
    //   39: iload #5
    //   41: iload_1
    //   42: if_icmpne -> 307
    //   45: iload_1
    //   46: bipush #60
    //   48: if_icmpne -> 291
    //   51: aload_0
    //   52: invokevirtual read : ()I
    //   55: istore_3
    //   56: aconst_null
    //   57: astore #11
    //   59: aload_0
    //   60: getfield tokenBufferLength : I
    //   63: ifle -> 112
    //   66: new gnu/expr/QuoteExp
    //   69: dup
    //   70: new java/lang/String
    //   73: dup
    //   74: aload_0
    //   75: getfield tokenBuffer : [C
    //   78: iconst_0
    //   79: aload_0
    //   80: getfield tokenBufferLength : I
    //   83: invokespecial <init> : ([CII)V
    //   86: invokespecial <init> : (Ljava/lang/Object;)V
    //   89: astore #11
    //   91: new gnu/expr/ApplyExp
    //   94: dup
    //   95: getstatic gnu/xquery/lang/XQParser.makeText : Lgnu/expr/Expression;
    //   98: iconst_1
    //   99: anewarray gnu/expr/Expression
    //   102: dup
    //   103: iconst_0
    //   104: aload #11
    //   106: aastore
    //   107: invokespecial <init> : (Lgnu/expr/Expression;[Lgnu/expr/Expression;)V
    //   110: astore #11
    //   112: aload_0
    //   113: iconst_0
    //   114: putfield tokenBufferLength : I
    //   117: iload_3
    //   118: bipush #47
    //   120: if_icmpne -> 146
    //   123: aload #11
    //   125: ifnull -> 139
    //   128: iload #8
    //   130: ifne -> 139
    //   133: aload_2
    //   134: aload #11
    //   136: invokevirtual addElement : (Ljava/lang/Object;)V
    //   139: return
    //   140: iconst_0
    //   141: istore #9
    //   143: goto -> 29
    //   146: aload_0
    //   147: iload_3
    //   148: iconst_1
    //   149: invokevirtual parseXMLConstructor : (IZ)Lgnu/expr/Expression;
    //   152: astore #12
    //   154: iconst_0
    //   155: istore #6
    //   157: iconst_0
    //   158: istore #7
    //   160: iload #7
    //   162: istore_3
    //   163: iload #6
    //   165: istore #5
    //   167: aload #12
    //   169: instanceof gnu/expr/ApplyExp
    //   172: ifeq -> 232
    //   175: aload #12
    //   177: checkcast gnu/expr/ApplyExp
    //   180: astore #13
    //   182: iload #7
    //   184: istore_3
    //   185: iload #6
    //   187: istore #5
    //   189: aload #13
    //   191: invokevirtual getFunction : ()Lgnu/expr/Expression;
    //   194: getstatic gnu/xquery/lang/XQParser.makeCDATA : Lgnu/expr/Expression;
    //   197: if_acmpne -> 232
    //   200: iconst_1
    //   201: istore #5
    //   203: aload #13
    //   205: iconst_0
    //   206: invokevirtual getArg : (I)Lgnu/expr/Expression;
    //   209: invokevirtual valueIfConstant : ()Ljava/lang/Object;
    //   212: checkcast java/lang/String
    //   215: astore #13
    //   217: aload #13
    //   219: ifnull -> 279
    //   222: aload #13
    //   224: invokevirtual length : ()I
    //   227: ifne -> 279
    //   230: iconst_1
    //   231: istore_3
    //   232: aload #11
    //   234: ifnull -> 253
    //   237: iload #8
    //   239: ifeq -> 247
    //   242: iload #5
    //   244: ifeq -> 253
    //   247: aload_2
    //   248: aload #11
    //   250: invokevirtual addElement : (Ljava/lang/Object;)V
    //   253: iload #5
    //   255: ifeq -> 284
    //   258: iconst_0
    //   259: istore #8
    //   261: iload_3
    //   262: ifne -> 271
    //   265: aload_2
    //   266: aload #12
    //   268: invokevirtual addElement : (Ljava/lang/Object;)V
    //   271: aload_0
    //   272: iconst_0
    //   273: putfield tokenBufferLength : I
    //   276: goto -> 33
    //   279: iconst_0
    //   280: istore_3
    //   281: goto -> 232
    //   284: iload #9
    //   286: istore #8
    //   288: goto -> 261
    //   291: aload_0
    //   292: iload_1
    //   293: invokevirtual checkNext : (C)Z
    //   296: ifeq -> 307
    //   299: aload_0
    //   300: iload_1
    //   301: invokevirtual tokenBufferAppend : (I)V
    //   304: goto -> 33
    //   307: iload #5
    //   309: iload_1
    //   310: if_icmpeq -> 325
    //   313: iload #5
    //   315: iflt -> 325
    //   318: iload #5
    //   320: bipush #123
    //   322: if_icmpne -> 515
    //   325: iload #5
    //   327: bipush #123
    //   329: if_icmpne -> 355
    //   332: aload_0
    //   333: invokevirtual read : ()I
    //   336: istore_3
    //   337: iload_3
    //   338: bipush #123
    //   340: if_icmpne -> 360
    //   343: aload_0
    //   344: bipush #123
    //   346: invokevirtual tokenBufferAppend : (I)V
    //   349: iconst_0
    //   350: istore #8
    //   352: goto -> 33
    //   355: iconst_m1
    //   356: istore_3
    //   357: goto -> 337
    //   360: aload_0
    //   361: getfield tokenBufferLength : I
    //   364: ifle -> 450
    //   367: iload #8
    //   369: ifne -> 450
    //   372: new java/lang/String
    //   375: dup
    //   376: aload_0
    //   377: getfield tokenBuffer : [C
    //   380: iconst_0
    //   381: aload_0
    //   382: getfield tokenBufferLength : I
    //   385: invokespecial <init> : ([CII)V
    //   388: astore #11
    //   390: new gnu/expr/QuoteExp
    //   393: dup
    //   394: aload #11
    //   396: invokespecial <init> : (Ljava/lang/Object;)V
    //   399: astore #11
    //   401: aload_2
    //   402: new gnu/expr/ApplyExp
    //   405: dup
    //   406: getstatic gnu/xquery/lang/XQParser.makeText : Lgnu/expr/Expression;
    //   409: iconst_1
    //   410: anewarray gnu/expr/Expression
    //   413: dup
    //   414: iconst_0
    //   415: aload #11
    //   417: aastore
    //   418: invokespecial <init> : (Lgnu/expr/Expression;[Lgnu/expr/Expression;)V
    //   421: invokevirtual addElement : (Ljava/lang/Object;)V
    //   424: aload_0
    //   425: iconst_0
    //   426: putfield tokenBufferLength : I
    //   429: iload #5
    //   431: iload_1
    //   432: if_icmpeq -> 139
    //   435: iload #5
    //   437: ifge -> 474
    //   440: aload_0
    //   441: ldc_w 'unexpected end-of-file'
    //   444: invokevirtual eofError : (Ljava/lang/String;)V
    //   447: goto -> 33
    //   450: iload #5
    //   452: bipush #123
    //   454: if_icmpne -> 424
    //   457: iload #4
    //   459: aload_2
    //   460: invokevirtual size : ()I
    //   463: if_icmpne -> 424
    //   466: ldc_w ''
    //   469: astore #11
    //   471: goto -> 390
    //   474: aload_0
    //   475: iload_3
    //   476: invokevirtual unread : (I)V
    //   479: aload_0
    //   480: aload_0
    //   481: getfield enclosedExpressionsSeen : I
    //   484: iconst_1
    //   485: iadd
    //   486: putfield enclosedExpressionsSeen : I
    //   489: aload_2
    //   490: aload_0
    //   491: invokevirtual parseEnclosedExpr : ()Lgnu/expr/Expression;
    //   494: invokevirtual addElement : (Ljava/lang/Object;)V
    //   497: aload_0
    //   498: iconst_0
    //   499: putfield tokenBufferLength : I
    //   502: aload_2
    //   503: invokevirtual size : ()I
    //   506: istore #4
    //   508: iload #9
    //   510: istore #8
    //   512: goto -> 33
    //   515: iload #5
    //   517: bipush #125
    //   519: if_icmpne -> 560
    //   522: aload_0
    //   523: invokevirtual read : ()I
    //   526: istore_3
    //   527: iload_3
    //   528: bipush #125
    //   530: if_icmpne -> 545
    //   533: aload_0
    //   534: bipush #125
    //   536: invokevirtual tokenBufferAppend : (I)V
    //   539: iconst_0
    //   540: istore #8
    //   542: goto -> 33
    //   545: aload_0
    //   546: ldc_w 'unexpected '}' in element content'
    //   549: invokevirtual error : (Ljava/lang/String;)V
    //   552: aload_0
    //   553: iload_3
    //   554: invokevirtual unread : (I)V
    //   557: goto -> 33
    //   560: iload #5
    //   562: bipush #38
    //   564: if_icmpne -> 577
    //   567: aload_0
    //   568: invokevirtual parseEntityOrCharRef : ()V
    //   571: iconst_0
    //   572: istore #8
    //   574: goto -> 33
    //   577: iload #5
    //   579: istore_3
    //   580: iload_1
    //   581: bipush #60
    //   583: if_icmpeq -> 613
    //   586: iload #5
    //   588: bipush #9
    //   590: if_icmpeq -> 610
    //   593: iload #5
    //   595: bipush #10
    //   597: if_icmpeq -> 610
    //   600: iload #5
    //   602: istore_3
    //   603: iload #5
    //   605: bipush #13
    //   607: if_icmpne -> 613
    //   610: bipush #32
    //   612: istore_3
    //   613: iload_3
    //   614: bipush #60
    //   616: if_icmpne -> 628
    //   619: aload_0
    //   620: bipush #101
    //   622: ldc_w ''<' must be quoted in a direct attribute value'
    //   625: invokevirtual error : (CLjava/lang/String;)V
    //   628: iload #8
    //   630: istore #10
    //   632: iload #8
    //   634: ifeq -> 644
    //   637: iload_3
    //   638: i2c
    //   639: invokestatic isWhitespace : (C)Z
    //   642: istore #10
    //   644: aload_0
    //   645: iload_3
    //   646: i2c
    //   647: invokevirtual tokenBufferAppend : (I)V
    //   650: iload #10
    //   652: istore #8
    //   654: goto -> 33
  }
  
  public Expression parseDataType() throws IOException, SyntaxException {
    boolean bool1;
    boolean bool2;
    QuoteExp quoteExp;
    ApplyExp applyExp;
    Expression expression = parseItemType();
    if (expression == null) {
      if (this.curToken != 238)
        return syntaxError("bad syntax - expected DataType"); 
      parseSimpleKindType();
      if (this.curToken == 63 || this.curToken == 413 || this.curToken == 415) {
        getRawToken();
        return syntaxError("occurrence-indicator meaningless after empty-sequence()");
      } 
      quoteExp = QuoteExp.getInstance(OccurrenceType.emptySequenceType);
      bool2 = false;
      bool1 = false;
    } else if (this.curToken == 63) {
      bool2 = false;
      bool1 = true;
    } else if (this.curToken == 413) {
      bool2 = true;
      bool1 = true;
    } else if (this.curToken == 415) {
      bool2 = false;
      bool1 = true;
    } else {
      bool2 = true;
      bool1 = true;
    } 
    if (this.parseContext == 67 && bool1 != true)
      return syntaxError("type to 'cast as' or 'castable as' must be a 'SingleType'"); 
    if (bool2 != bool1) {
      getRawToken();
      QuoteExp quoteExp1 = QuoteExp.getInstance(IntNum.make(bool2));
      QuoteExp quoteExp2 = QuoteExp.getInstance(IntNum.make(bool1));
      applyExp = new ApplyExp((Procedure)proc_OccurrenceType_getInstance, new Expression[] { (Expression)quoteExp, (Expression)quoteExp1, (Expression)quoteExp2 });
      applyExp.setFlag(4);
      return (Expression)applyExp;
    } 
    return (Expression)applyExp;
  }
  
  Expression parseElementConstructor() throws IOException, SyntaxException {
    NamespaceBinding namespaceBinding;
    String str2 = new String(this.tokenBuffer, 0, this.tokenBufferLength);
    Vector<ApplyExp> vector = new Vector();
    vector.addElement(castQName((Expression)new QuoteExp(str2), true));
    this.errorIfComment = "comment not allowed in element start tag";
    String str1 = null;
    label99: while (true) {
      int j = 0;
      int i = read();
      while (i >= 0 && Character.isWhitespace((char)i)) {
        i = read();
        j = 1;
      } 
      if (i >= 0 && i != 62 && i != 47) {
        if (!j)
          syntaxError("missing space before attribute"); 
        unread(i);
        getRawToken();
        j = vector.size();
        if (this.curToken == 65 || this.curToken == 81) {
          String str4;
          String str5 = new String(this.tokenBuffer, 0, this.tokenBufferLength);
          int m = getLineNumber();
          int n = getColumnNumber();
          int i1 = this.tokenBufferLength;
          String str3 = null;
          if (this.curToken == 65) {
            if (str5.equals("xmlns"))
              str3 = ""; 
          } else if (str5.startsWith("xmlns:")) {
            str3 = str5.substring(6).intern();
          } 
          if (str3 != null) {
            str4 = null;
          } else {
            QuoteExp quoteExp = MakeAttribute.makeAttributeExp;
          } 
          vector.addElement(castQName((Expression)new QuoteExp(str5), false));
          if (skipSpace() != 61) {
            this.errorIfComment = null;
            return syntaxError("missing '=' after attribute");
          } 
          i = skipSpace();
          int i2 = this.enclosedExpressionsSeen;
          if (i == 123) {
            warnOldVersion("enclosed attribute value expression should be quoted");
            vector.addElement(parseEnclosedExpr());
          } else {
            parseContent((char)i, vector);
          } 
          i = vector.size() - j;
          if (str3 != null) {
            str4 = "";
            if (i == 1) {
              str4 = "";
            } else if (this.enclosedExpressionsSeen > i2) {
              syntaxError("enclosed expression not allowed in namespace declaration");
            } else {
              Expression expression;
              str5 = (String)vector.elementAt(j + 1);
              str4 = str5;
              if (str5 instanceof ApplyExp) {
                ApplyExp applyExp = (ApplyExp)str5;
                str4 = str5;
                if (applyExp.getFunction() == makeText)
                  expression = applyExp.getArg(0); 
              } 
              str4 = ((QuoteExp)expression).getValue().toString().intern();
            } 
            vector.setSize(j);
            checkAllowedNamespaceDeclaration(str3, str4, true);
            str5 = str3;
            if (str3 == "")
              str5 = null; 
            str3 = str1;
            while (true) {
              if (str3 != null)
                if (str3.getPrefix() == str5) {
                  if (str5 == null) {
                    str3 = "duplicate default namespace declaration";
                  } else {
                    str3 = "duplicate namespace prefix '" + str5 + '\'';
                  } 
                  error('e', str3, "XQST0071");
                } else {
                  NamespaceBinding namespaceBinding1 = str3.getNext();
                  continue;
                }  
              str3 = str4;
              if (str4 == "")
                str3 = null; 
              namespaceBinding = new NamespaceBinding(str5, str3, (NamespaceBinding)str1);
              continue label99;
            } 
          } 
          Expression[] arrayOfExpression1 = new Expression[i];
          while (true) {
            if (--i >= 0) {
              arrayOfExpression1[i] = (Expression)vector.elementAt(j + i);
              continue;
            } 
            vector.setSize(j);
            ApplyExp applyExp = new ApplyExp((Expression)str4, arrayOfExpression1);
            maybeSetLine((Expression)applyExp, m + 1, n + 1 - i1);
            vector.addElement(applyExp);
          } 
        } 
      } 
      this.errorIfComment = null;
      boolean bool = false;
      int k = i;
      j = bool;
      if (i == 47) {
        k = read();
        if (k == 62) {
          j = 1;
        } else {
          unread(k);
          j = bool;
        } 
      } 
      if (j == 0) {
        if (k != 62)
          return syntaxError("missing '>' after start element"); 
        parseContent('<', vector);
        j = peek();
        i = j;
        if (j >= 0) {
          if (!XName.isNameStart((char)j))
            return syntaxError("invalid tag syntax after '</'"); 
          getRawToken();
          String str = new String(this.tokenBuffer, 0, this.tokenBufferLength);
          if (!str.equals(str2))
            return syntaxError("'<" + str2 + ">' closed by '</" + str + ">'"); 
          this.errorIfComment = "comment not allowed in element end tag";
          i = skipSpace();
          this.errorIfComment = null;
        } 
        if (i != 62)
          return syntaxError("missing '>' after end element"); 
      } 
      break;
    } 
    Expression[] arrayOfExpression = new Expression[vector.size()];
    vector.copyInto((Object[])arrayOfExpression);
    MakeElement makeElement = new MakeElement();
    makeElement.copyNamespacesMode = this.copyNamespacesMode;
    makeElement.setNamespaceNodes(namespaceBinding);
    return (Expression)new ApplyExp((Expression)new QuoteExp(makeElement), arrayOfExpression);
  }
  
  Expression parseEnclosedExpr() throws IOException, SyntaxException {
    String str = this.errorIfComment;
    this.errorIfComment = null;
    char c = pushNesting('{');
    peekNonSpace("unexpected end-of-file after '{'");
    int i = getLineNumber();
    int j = getColumnNumber();
    getRawToken();
    Expression expression = parseExpr();
    while (true) {
      if (this.curToken != 125) {
        if (this.curToken == -1 || this.curToken == 41 || this.curToken == 93) {
          expression = syntaxError("missing '}'");
          maybeSetLine(expression, i + 1, j + 1);
          popNesting(c);
          this.errorIfComment = str;
          return expression;
        } 
        if (this.curToken != 44) {
          expression = syntaxError("missing '}' or ','");
        } else {
          getRawToken();
        } 
        expression = makeExprSequence(expression, parseExpr());
        continue;
      } 
      maybeSetLine(expression, i + 1, j + 1);
      popNesting(c);
      this.errorIfComment = str;
      return expression;
    } 
  }
  
  void parseEntityOrCharRef() throws IOException, SyntaxException {
    int i = read();
    if (i == 35) {
      byte b;
      i = read();
      if (i == 120) {
        b = 16;
        i = read();
      } else {
        b = 10;
      } 
      int k = 0;
      while (true) {
        if (i >= 0) {
          int m = Character.digit((char)i, b);
          if (m >= 0 && k < 134217728) {
            k = k * b + m;
            i = read();
            continue;
          } 
        } 
        if (i != 59) {
          unread();
          error("invalid character reference");
          return;
        } 
        if ((k > 0 && k <= 55295) || (k >= 57344 && k <= 65533) || (k >= 65536 && k <= 1114111)) {
          tokenBufferAppend(k);
          return;
        } 
        error('e', "invalid character value " + k, "XQST0090");
        return;
      } 
    } 
    int j = this.tokenBufferLength;
    while (true) {
      if (i >= 0) {
        char c = (char)i;
        if (XName.isNamePart(c)) {
          tokenBufferAppend(c);
          i = read();
          continue;
        } 
      } 
      if (i != 59) {
        unread();
        error("invalid entity reference");
        return;
      } 
      String str = new String(this.tokenBuffer, j, this.tokenBufferLength - j);
      this.tokenBufferLength = j;
      appendNamedEntity(str);
      return;
    } 
  }
  
  Expression parseExpr() throws IOException, SyntaxException {
    return parseExprSingle();
  }
  
  Expression parseExprSequence(int paramInt, boolean paramBoolean) throws IOException, SyntaxException {
    // Byte code:
    //   0: aload_0
    //   1: getfield curToken : I
    //   4: iload_1
    //   5: if_icmpeq -> 16
    //   8: aload_0
    //   9: getfield curToken : I
    //   12: iconst_m1
    //   13: if_icmpne -> 36
    //   16: iload_2
    //   17: ifne -> 28
    //   20: aload_0
    //   21: ldc_w 'missing expression'
    //   24: invokevirtual syntaxError : (Ljava/lang/String;)Lgnu/expr/Expression;
    //   27: pop
    //   28: getstatic gnu/expr/QuoteExp.voidExp : Lgnu/expr/QuoteExp;
    //   31: astore #4
    //   33: aload #4
    //   35: areturn
    //   36: aconst_null
    //   37: astore_3
    //   38: aload_0
    //   39: invokevirtual parseExprSingle : ()Lgnu/expr/Expression;
    //   42: astore #4
    //   44: aload_3
    //   45: ifnonnull -> 117
    //   48: aload #4
    //   50: astore_3
    //   51: aload_3
    //   52: astore #4
    //   54: aload_0
    //   55: getfield curToken : I
    //   58: iload_1
    //   59: if_icmpeq -> 33
    //   62: aload_3
    //   63: astore #4
    //   65: aload_0
    //   66: getfield curToken : I
    //   69: iconst_m1
    //   70: if_icmpeq -> 33
    //   73: aload_0
    //   74: getfield nesting : I
    //   77: ifne -> 92
    //   80: aload_3
    //   81: astore #4
    //   83: aload_0
    //   84: getfield curToken : I
    //   87: bipush #10
    //   89: if_icmpeq -> 33
    //   92: aload_0
    //   93: getfield curToken : I
    //   96: bipush #44
    //   98: if_icmpeq -> 134
    //   101: iload_1
    //   102: bipush #41
    //   104: if_icmpne -> 127
    //   107: ldc_w 'expected ')''
    //   110: astore_3
    //   111: aload_0
    //   112: aload_3
    //   113: invokevirtual syntaxError : (Ljava/lang/String;)Lgnu/expr/Expression;
    //   116: areturn
    //   117: aload_3
    //   118: aload #4
    //   120: invokestatic makeExprSequence : (Lgnu/expr/Expression;Lgnu/expr/Expression;)Lgnu/expr/Expression;
    //   123: astore_3
    //   124: goto -> 51
    //   127: ldc_w 'confused by syntax error'
    //   130: astore_3
    //   131: goto -> 111
    //   134: aload_0
    //   135: invokevirtual getRawToken : ()I
    //   138: pop
    //   139: goto -> 38
  }
  
  final Expression parseExprSingle() throws IOException, SyntaxException {
    int i = this.curLine;
    i = this.curColumn;
    peekOperand();
    switch (this.curToken) {
      default:
        return parseBinaryExpr(priority(400));
      case 241:
        return parseIfExpr();
      case 242:
        return parseTypeSwitch();
      case 243:
        return parseFLWRExpression(true);
      case 244:
        return parseFLWRExpression(false);
      case 245:
        return parseQuantifiedExpr(false);
      case 246:
        break;
    } 
    return parseQuantifiedExpr(true);
  }
  
  public Expression parseFLWRExpression(boolean paramBoolean) throws IOException, SyntaxException {
    // Byte code:
    //   0: aload_0
    //   1: getfield flworDeclsFirst : I
    //   4: istore #5
    //   6: aload_0
    //   7: aload_0
    //   8: getfield flworDeclsCount : I
    //   11: putfield flworDeclsFirst : I
    //   14: aload_0
    //   15: iload_1
    //   16: invokevirtual parseFLWRInner : (Z)Lgnu/expr/Expression;
    //   19: astore #9
    //   21: aload_0
    //   22: ldc_w 'order'
    //   25: invokevirtual match : (Ljava/lang/String;)Z
    //   28: ifeq -> 688
    //   31: iload_1
    //   32: ifeq -> 140
    //   35: bipush #102
    //   37: istore_2
    //   38: aload_0
    //   39: iload_2
    //   40: invokevirtual pushNesting : (C)C
    //   43: istore #4
    //   45: aload_0
    //   46: invokevirtual getRawToken : ()I
    //   49: pop
    //   50: aload_0
    //   51: ldc_w 'by'
    //   54: invokevirtual match : (Ljava/lang/String;)Z
    //   57: ifeq -> 146
    //   60: aload_0
    //   61: invokevirtual getRawToken : ()I
    //   64: pop
    //   65: new java/util/Stack
    //   68: dup
    //   69: invokespecial <init> : ()V
    //   72: astore #10
    //   74: iconst_0
    //   75: istore #6
    //   77: aload_0
    //   78: getfield defaultEmptyOrder : C
    //   81: istore_3
    //   82: new gnu/expr/LambdaExp
    //   85: dup
    //   86: aload_0
    //   87: getfield flworDeclsCount : I
    //   90: aload_0
    //   91: getfield flworDeclsFirst : I
    //   94: isub
    //   95: invokespecial <init> : (I)V
    //   98: astore #7
    //   100: aload_0
    //   101: getfield flworDeclsFirst : I
    //   104: istore #5
    //   106: iload #5
    //   108: aload_0
    //   109: getfield flworDeclsCount : I
    //   112: if_icmpge -> 156
    //   115: aload #7
    //   117: aload_0
    //   118: getfield flworDecls : [Lgnu/expr/Declaration;
    //   121: iload #5
    //   123: aaload
    //   124: invokevirtual getSymbol : ()Ljava/lang/Object;
    //   127: invokevirtual addDeclaration : (Ljava/lang/Object;)Lgnu/expr/Declaration;
    //   130: pop
    //   131: iload #5
    //   133: iconst_1
    //   134: iadd
    //   135: istore #5
    //   137: goto -> 106
    //   140: bipush #108
    //   142: istore_2
    //   143: goto -> 38
    //   146: aload_0
    //   147: ldc_w 'missing 'by' following 'order''
    //   150: invokevirtual error : (Ljava/lang/String;)V
    //   153: goto -> 65
    //   156: aload_0
    //   157: getfield comp : Lgnu/expr/Compilation;
    //   160: aload #7
    //   162: invokevirtual push : (Lgnu/expr/ScopeExp;)V
    //   165: aload #7
    //   167: aload_0
    //   168: invokevirtual parseExprSingle : ()Lgnu/expr/Expression;
    //   171: putfield body : Lgnu/expr/Expression;
    //   174: aload_0
    //   175: getfield comp : Lgnu/expr/Compilation;
    //   178: aload #7
    //   180: invokevirtual pop : (Lgnu/expr/ScopeExp;)V
    //   183: aload #10
    //   185: aload #7
    //   187: invokevirtual push : (Ljava/lang/Object;)Ljava/lang/Object;
    //   190: pop
    //   191: aload_0
    //   192: ldc_w 'ascending'
    //   195: invokevirtual match : (Ljava/lang/String;)Z
    //   198: ifeq -> 390
    //   201: aload_0
    //   202: invokevirtual getRawToken : ()I
    //   205: pop
    //   206: iload #6
    //   208: istore #5
    //   210: iload_3
    //   211: istore_2
    //   212: aload_0
    //   213: ldc_w 'empty'
    //   216: invokevirtual match : (Ljava/lang/String;)Z
    //   219: ifeq -> 245
    //   222: aload_0
    //   223: invokevirtual getRawToken : ()I
    //   226: pop
    //   227: aload_0
    //   228: ldc_w 'greatest'
    //   231: invokevirtual match : (Ljava/lang/String;)Z
    //   234: ifeq -> 415
    //   237: aload_0
    //   238: invokevirtual getRawToken : ()I
    //   241: pop
    //   242: bipush #71
    //   244: istore_2
    //   245: new java/lang/StringBuilder
    //   248: dup
    //   249: invokespecial <init> : ()V
    //   252: astore #8
    //   254: iload #5
    //   256: ifeq -> 448
    //   259: ldc_w 'D'
    //   262: astore #7
    //   264: aload #10
    //   266: new gnu/expr/QuoteExp
    //   269: dup
    //   270: aload #8
    //   272: aload #7
    //   274: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   277: iload_2
    //   278: invokevirtual append : (C)Ljava/lang/StringBuilder;
    //   281: invokevirtual toString : ()Ljava/lang/String;
    //   284: invokespecial <init> : (Ljava/lang/Object;)V
    //   287: invokevirtual push : (Ljava/lang/Object;)Ljava/lang/Object;
    //   290: pop
    //   291: aload_0
    //   292: getfield defaultCollator : Lgnu/xquery/util/NamedCollator;
    //   295: astore #8
    //   297: aload #8
    //   299: astore #7
    //   301: aload_0
    //   302: ldc_w 'collation'
    //   305: invokevirtual match : (Ljava/lang/String;)Z
    //   308: ifeq -> 348
    //   311: aload_0
    //   312: invokevirtual parseURILiteral : ()Ljava/lang/Object;
    //   315: astore #11
    //   317: aload #8
    //   319: astore #7
    //   321: aload #11
    //   323: instanceof java/lang/String
    //   326: ifeq -> 343
    //   329: aload_0
    //   330: aload #11
    //   332: checkcast java/lang/String
    //   335: invokevirtual resolveAgainstBaseUri : (Ljava/lang/String;)Ljava/lang/String;
    //   338: invokestatic make : (Ljava/lang/String;)Lgnu/xquery/util/NamedCollator;
    //   341: astore #7
    //   343: aload_0
    //   344: invokevirtual getRawToken : ()I
    //   347: pop
    //   348: aload #10
    //   350: new gnu/expr/QuoteExp
    //   353: dup
    //   354: aload #7
    //   356: invokespecial <init> : (Ljava/lang/Object;)V
    //   359: invokevirtual push : (Ljava/lang/Object;)Ljava/lang/Object;
    //   362: pop
    //   363: aload_0
    //   364: getfield curToken : I
    //   367: bipush #44
    //   369: if_icmpeq -> 501
    //   372: aload_0
    //   373: ldc_w 'return'
    //   376: invokevirtual match : (Ljava/lang/String;)Z
    //   379: ifne -> 509
    //   382: aload_0
    //   383: ldc_w 'expected 'return' clause'
    //   386: invokevirtual syntaxError : (Ljava/lang/String;)Lgnu/expr/Expression;
    //   389: areturn
    //   390: iload #6
    //   392: istore #5
    //   394: aload_0
    //   395: ldc_w 'descending'
    //   398: invokevirtual match : (Ljava/lang/String;)Z
    //   401: ifeq -> 210
    //   404: aload_0
    //   405: invokevirtual getRawToken : ()I
    //   408: pop
    //   409: iconst_1
    //   410: istore #5
    //   412: goto -> 210
    //   415: aload_0
    //   416: ldc_w 'least'
    //   419: invokevirtual match : (Ljava/lang/String;)Z
    //   422: ifeq -> 436
    //   425: aload_0
    //   426: invokevirtual getRawToken : ()I
    //   429: pop
    //   430: bipush #76
    //   432: istore_2
    //   433: goto -> 245
    //   436: aload_0
    //   437: ldc_w ''empty' sequence order must be 'greatest' or 'least''
    //   440: invokevirtual error : (Ljava/lang/String;)V
    //   443: iload_3
    //   444: istore_2
    //   445: goto -> 245
    //   448: ldc_w 'A'
    //   451: astore #7
    //   453: goto -> 264
    //   456: astore #7
    //   458: aload_0
    //   459: bipush #101
    //   461: new java/lang/StringBuilder
    //   464: dup
    //   465: invokespecial <init> : ()V
    //   468: ldc_w 'unknown collation ''
    //   471: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   474: aload #11
    //   476: invokevirtual append : (Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   479: ldc_w '''
    //   482: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   485: invokevirtual toString : ()Ljava/lang/String;
    //   488: ldc_w 'XQST0076'
    //   491: invokevirtual error : (CLjava/lang/String;Ljava/lang/String;)V
    //   494: aload #8
    //   496: astore #7
    //   498: goto -> 343
    //   501: aload_0
    //   502: invokevirtual getRawToken : ()I
    //   505: pop
    //   506: goto -> 74
    //   509: aload_0
    //   510: invokevirtual getRawToken : ()I
    //   513: pop
    //   514: new gnu/expr/LambdaExp
    //   517: dup
    //   518: aload_0
    //   519: getfield flworDeclsCount : I
    //   522: aload_0
    //   523: getfield flworDeclsFirst : I
    //   526: isub
    //   527: invokespecial <init> : (I)V
    //   530: astore #7
    //   532: aload_0
    //   533: getfield flworDeclsFirst : I
    //   536: istore #5
    //   538: iload #5
    //   540: aload_0
    //   541: getfield flworDeclsCount : I
    //   544: if_icmpge -> 572
    //   547: aload #7
    //   549: aload_0
    //   550: getfield flworDecls : [Lgnu/expr/Declaration;
    //   553: iload #5
    //   555: aaload
    //   556: invokevirtual getSymbol : ()Ljava/lang/Object;
    //   559: invokevirtual addDeclaration : (Ljava/lang/Object;)Lgnu/expr/Declaration;
    //   562: pop
    //   563: iload #5
    //   565: iconst_1
    //   566: iadd
    //   567: istore #5
    //   569: goto -> 538
    //   572: aload_0
    //   573: iload #4
    //   575: invokevirtual popNesting : (C)V
    //   578: aload_0
    //   579: getfield comp : Lgnu/expr/Compilation;
    //   582: aload #7
    //   584: invokevirtual push : (Lgnu/expr/ScopeExp;)V
    //   587: aload #7
    //   589: aload_0
    //   590: invokevirtual parseExprSingle : ()Lgnu/expr/Expression;
    //   593: putfield body : Lgnu/expr/Expression;
    //   596: aload_0
    //   597: getfield comp : Lgnu/expr/Compilation;
    //   600: aload #7
    //   602: invokevirtual pop : (Lgnu/expr/ScopeExp;)V
    //   605: aload #10
    //   607: invokevirtual size : ()I
    //   610: istore #6
    //   612: iload #6
    //   614: iconst_2
    //   615: iadd
    //   616: anewarray gnu/expr/Expression
    //   619: astore #8
    //   621: aload #8
    //   623: iconst_0
    //   624: aload #9
    //   626: aastore
    //   627: aload #8
    //   629: iconst_1
    //   630: aload #7
    //   632: aastore
    //   633: iconst_0
    //   634: istore #5
    //   636: iload #5
    //   638: iload #6
    //   640: if_icmpge -> 669
    //   643: aload #8
    //   645: iload #5
    //   647: iconst_2
    //   648: iadd
    //   649: aload #10
    //   651: iload #5
    //   653: invokevirtual elementAt : (I)Ljava/lang/Object;
    //   656: checkcast gnu/expr/Expression
    //   659: aastore
    //   660: iload #5
    //   662: iconst_1
    //   663: iadd
    //   664: istore #5
    //   666: goto -> 636
    //   669: new gnu/expr/ApplyExp
    //   672: dup
    //   673: ldc_w 'gnu.xquery.util.OrderedMap'
    //   676: ldc_w 'orderedMap'
    //   679: invokestatic makeFunctionExp : (Ljava/lang/String;Ljava/lang/String;)Lgnu/expr/Expression;
    //   682: aload #8
    //   684: invokespecial <init> : (Lgnu/expr/Expression;[Lgnu/expr/Expression;)V
    //   687: areturn
    //   688: aload_0
    //   689: aload_0
    //   690: getfield flworDeclsFirst : I
    //   693: putfield flworDeclsCount : I
    //   696: aload_0
    //   697: iload #5
    //   699: putfield flworDeclsFirst : I
    //   702: aload #9
    //   704: areturn
    // Exception table:
    //   from	to	target	type
    //   329	343	456	java/lang/Exception
  }
  
  public Expression parseFLWRInner(boolean paramBoolean) throws IOException, SyntaxException {
    LambdaExp lambdaExp1;
    Expression expression1;
    String str;
    LetExp letExp;
    if (paramBoolean) {
      c = 'f';
    } else {
      c = 'l';
    } 
    char c = pushNesting(c);
    this.curToken = 36;
    Declaration declaration2 = parseVariableDeclaration();
    if (declaration2 == null)
      return syntaxError("missing Variable - saw " + tokenString()); 
    if (this.flworDecls == null) {
      this.flworDecls = new Declaration[8];
    } else if (this.flworDeclsCount >= this.flworDecls.length) {
      Declaration[] arrayOfDeclaration1 = new Declaration[this.flworDeclsCount * 2];
      System.arraycopy(this.flworDecls, 0, arrayOfDeclaration1, 0, this.flworDeclsCount);
      this.flworDecls = arrayOfDeclaration1;
    } 
    Declaration[] arrayOfDeclaration = this.flworDecls;
    int i = this.flworDeclsCount;
    this.flworDeclsCount = i + 1;
    arrayOfDeclaration[i] = declaration2;
    getRawToken();
    Expression expression2 = parseOptionalTypeDeclaration();
    Expression[] arrayOfExpression = new Expression[1];
    arrayOfDeclaration = null;
    LambdaExp lambdaExp2 = null;
    Declaration declaration1 = null;
    if (paramBoolean) {
      boolean bool = match("at");
      if (bool) {
        i = 2;
      } else {
        i = 1;
      } 
      lambdaExp2 = new LambdaExp(i);
      if (bool) {
        getRawToken();
        if (this.curToken == 36) {
          declaration1 = parseVariableDeclaration();
          getRawToken();
        } 
        Declaration declaration = declaration1;
        if (declaration1 == null) {
          syntaxError("missing Variable after 'at'");
          declaration = declaration1;
        } 
      } 
      if (match("in")) {
        getRawToken();
      } else {
        if (this.curToken == 76)
          getRawToken(); 
        syntaxError("missing 'in' in 'for' clause");
      } 
    } else {
      if (this.curToken == 76) {
        getRawToken();
      } else {
        if (match("in"))
          getRawToken(); 
        syntaxError("missing ':=' in 'let' clause");
      } 
      LetExp letExp1 = new LetExp(arrayOfExpression);
      lambdaExp1 = lambdaExp2;
      letExp = letExp1;
    } 
    arrayOfExpression[0] = parseExprSingle();
    if (expression2 != null && !paramBoolean)
      arrayOfExpression[0] = (Expression)Compilation.makeCoercion(arrayOfExpression[0], expression2); 
    popNesting(c);
    this.comp.push((ScopeExp)letExp);
    letExp.addDeclaration(declaration2);
    if (expression2 != null)
      declaration2.setTypeExp(expression2); 
    if (paramBoolean) {
      declaration2.noteValue(null);
      declaration2.setFlag(262144L);
    } 
    if (lambdaExp1 != null) {
      letExp.addDeclaration((Declaration)lambdaExp1);
      lambdaExp1.setType((Type)LangPrimType.intType);
      lambdaExp1.noteValue(null);
      lambdaExp1.setFlag(262144L);
    } 
    if (this.curToken == 44) {
      getRawToken();
      if (this.curToken != 36)
        return syntaxError("missing $NAME after ','"); 
      expression1 = parseFLWRInner(paramBoolean);
    } else if (match("for")) {
      getRawToken();
      if (this.curToken != 36)
        return syntaxError("missing $NAME after 'for'"); 
      expression1 = parseFLWRInner(true);
    } else if (match("let")) {
      getRawToken();
      if (this.curToken != 36)
        return syntaxError("missing $NAME after 'let'"); 
      expression1 = parseFLWRInner(false);
    } else {
      Expression expression;
      c = pushNesting('w');
      if (this.curToken == 196) {
        getRawToken();
        expression1 = parseExprSingle();
      } else if (match("where")) {
        expression1 = parseExprSingle();
      } else {
        lambdaExp1 = null;
      } 
      popNesting(c);
      if (match("stable"))
        getRawToken(); 
      boolean bool1 = match("return");
      boolean bool2 = match("order");
      if (!bool1 && !bool2 && !match("let") && !match("for"))
        return syntaxError("missing 'return' clause"); 
      if (!bool2)
        peekNonSpace("unexpected eof-of-file after 'return'"); 
      int j = getLineNumber();
      int k = getColumnNumber();
      if (bool1)
        getRawToken(); 
      if (bool2) {
        int m = this.flworDeclsCount - this.flworDeclsFirst;
        Expression[] arrayOfExpression1 = new Expression[m];
        for (i = 0; i < m; i++)
          arrayOfExpression1[i] = (Expression)new ReferenceExp(this.flworDecls[this.flworDeclsFirst + i]); 
        ApplyExp applyExp = new ApplyExp((Procedure)new PrimProcedure("gnu.xquery.util.OrderedMap", "makeTuple$V", 1), arrayOfExpression1);
      } else {
        expression = parseExprSingle();
      } 
      if (lambdaExp1 != null) {
        IfExp ifExp = new IfExp(booleanValue((Expression)lambdaExp1), expression, (Expression)QuoteExp.voidExp);
      } else {
        expression1 = expression;
      } 
      maybeSetLine(expression1, j + 1, k + 1);
    } 
    this.comp.pop((ScopeExp)letExp);
    if (paramBoolean) {
      LambdaExp lambdaExp = (LambdaExp)letExp;
      lambdaExp.body = expression1;
      Expression expression = arrayOfExpression[0];
      if (lambdaExp.min_args == 1) {
        String str1 = "valuesMap";
        return (Expression)new ApplyExp(makeFunctionExp("gnu.kawa.functions.ValuesMap", str1), new Expression[] { (Expression)letExp, expression });
      } 
      str = "valuesMapWithPos";
      return (Expression)new ApplyExp(makeFunctionExp("gnu.kawa.functions.ValuesMap", str), new Expression[] { (Expression)letExp, expression });
    } 
    letExp.setBody((Expression)str);
    return (Expression)letExp;
  }
  
  public Expression parseFunctionDefinition(int paramInt1, int paramInt2) throws IOException, SyntaxException {
    // Byte code:
    //   0: aload_0
    //   1: getfield curToken : I
    //   4: bipush #81
    //   6: if_icmpeq -> 26
    //   9: aload_0
    //   10: getfield curToken : I
    //   13: bipush #65
    //   15: if_icmpeq -> 26
    //   18: aload_0
    //   19: ldc_w 'missing function name'
    //   22: invokevirtual syntaxError : (Ljava/lang/String;)Lgnu/expr/Expression;
    //   25: areturn
    //   26: new java/lang/String
    //   29: dup
    //   30: aload_0
    //   31: getfield tokenBuffer : [C
    //   34: iconst_0
    //   35: aload_0
    //   36: getfield tokenBufferLength : I
    //   39: invokespecial <init> : ([CII)V
    //   42: astore #5
    //   44: aload_0
    //   45: invokevirtual getMessages : ()Lgnu/text/SourceMessages;
    //   48: aload_0
    //   49: getfield port : Lgnu/text/LineBufferedReader;
    //   52: invokevirtual getName : ()Ljava/lang/String;
    //   55: aload_0
    //   56: getfield curLine : I
    //   59: aload_0
    //   60: getfield curColumn : I
    //   63: invokevirtual setLine : (Ljava/lang/String;II)V
    //   66: aload_0
    //   67: aload #5
    //   69: iconst_1
    //   70: invokevirtual namespaceResolve : (Ljava/lang/String;Z)Lgnu/mapping/Symbol;
    //   73: astore #6
    //   75: aload #6
    //   77: invokevirtual getNamespaceURI : ()Ljava/lang/String;
    //   80: astore #4
    //   82: aload #4
    //   84: ldc_w 'http://www.w3.org/XML/1998/namespace'
    //   87: if_acmpeq -> 114
    //   90: aload #4
    //   92: ldc_w 'http://www.w3.org/2001/XMLSchema'
    //   95: if_acmpeq -> 114
    //   98: aload #4
    //   100: ldc_w 'http://www.w3.org/2001/XMLSchema-instance'
    //   103: if_acmpeq -> 114
    //   106: aload #4
    //   108: ldc_w 'http://www.w3.org/2005/xpath-functions'
    //   111: if_acmpne -> 191
    //   114: aload_0
    //   115: bipush #101
    //   117: new java/lang/StringBuilder
    //   120: dup
    //   121: invokespecial <init> : ()V
    //   124: ldc_w 'cannot declare function in standard namespace ''
    //   127: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   130: aload #4
    //   132: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   135: bipush #39
    //   137: invokevirtual append : (C)Ljava/lang/StringBuilder;
    //   140: invokevirtual toString : ()Ljava/lang/String;
    //   143: ldc_w 'XQST0045'
    //   146: invokevirtual error : (CLjava/lang/String;Ljava/lang/String;)V
    //   149: aload_0
    //   150: invokevirtual getRawToken : ()I
    //   153: pop
    //   154: aload_0
    //   155: getfield curToken : I
    //   158: bipush #40
    //   160: if_icmpeq -> 284
    //   163: aload_0
    //   164: new java/lang/StringBuilder
    //   167: dup
    //   168: invokespecial <init> : ()V
    //   171: ldc_w 'missing parameter list:'
    //   174: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   177: aload_0
    //   178: getfield curToken : I
    //   181: invokevirtual append : (I)Ljava/lang/StringBuilder;
    //   184: invokevirtual toString : ()Ljava/lang/String;
    //   187: invokevirtual syntaxError : (Ljava/lang/String;)Lgnu/expr/Expression;
    //   190: areturn
    //   191: aload #4
    //   193: ldc_w ''
    //   196: if_acmpne -> 232
    //   199: aload_0
    //   200: getfield comp : Lgnu/expr/Compilation;
    //   203: invokevirtual isPedantic : ()Z
    //   206: ifeq -> 226
    //   209: bipush #101
    //   211: istore_3
    //   212: aload_0
    //   213: iload_3
    //   214: ldc_w 'cannot declare function in empty namespace'
    //   217: ldc_w 'XQST0060'
    //   220: invokevirtual error : (CLjava/lang/String;Ljava/lang/String;)V
    //   223: goto -> 149
    //   226: bipush #119
    //   228: istore_3
    //   229: goto -> 212
    //   232: aload_0
    //   233: getfield libraryModuleNamespace : Ljava/lang/String;
    //   236: ifnull -> 149
    //   239: aload #4
    //   241: aload_0
    //   242: getfield libraryModuleNamespace : Ljava/lang/String;
    //   245: if_acmpeq -> 149
    //   248: ldc_w 'http://www.w3.org/2005/xquery-local-functions'
    //   251: aload #4
    //   253: invokevirtual equals : (Ljava/lang/Object;)Z
    //   256: ifeq -> 269
    //   259: aload_0
    //   260: getfield comp : Lgnu/expr/Compilation;
    //   263: invokevirtual isPedantic : ()Z
    //   266: ifeq -> 149
    //   269: aload_0
    //   270: bipush #101
    //   272: ldc_w 'function not in namespace of library module'
    //   275: ldc_w 'XQST0048'
    //   278: invokevirtual error : (CLjava/lang/String;Ljava/lang/String;)V
    //   281: goto -> 149
    //   284: aload_0
    //   285: invokevirtual getRawToken : ()I
    //   288: pop
    //   289: new gnu/expr/LambdaExp
    //   292: dup
    //   293: invokespecial <init> : ()V
    //   296: astore #4
    //   298: aload_0
    //   299: aload #4
    //   301: iload_1
    //   302: iload_2
    //   303: invokevirtual maybeSetLine : (Lgnu/expr/Expression;II)V
    //   306: aload #4
    //   308: aload #5
    //   310: invokevirtual setName : (Ljava/lang/String;)V
    //   313: aload_0
    //   314: getfield comp : Lgnu/expr/Compilation;
    //   317: invokevirtual currentScope : ()Lgnu/expr/ScopeExp;
    //   320: aload #6
    //   322: invokevirtual addDeclaration : (Ljava/lang/Object;)Lgnu/expr/Declaration;
    //   325: astore #5
    //   327: aload_0
    //   328: getfield comp : Lgnu/expr/Compilation;
    //   331: invokevirtual isStatic : ()Z
    //   334: ifeq -> 345
    //   337: aload #5
    //   339: ldc2_w 2048
    //   342: invokevirtual setFlag : (J)V
    //   345: aload #4
    //   347: sipush #2048
    //   350: invokevirtual setFlag : (I)V
    //   353: aload #5
    //   355: iconst_1
    //   356: invokevirtual setCanRead : (Z)V
    //   359: aload #5
    //   361: iconst_1
    //   362: invokevirtual setProcedureDecl : (Z)V
    //   365: aload_0
    //   366: aload #5
    //   368: iload_1
    //   369: iload_2
    //   370: invokevirtual maybeSetLine : (Lgnu/expr/Declaration;II)V
    //   373: aload_0
    //   374: getfield comp : Lgnu/expr/Compilation;
    //   377: aload #4
    //   379: invokevirtual push : (Lgnu/expr/ScopeExp;)V
    //   382: aload_0
    //   383: getfield curToken : I
    //   386: bipush #41
    //   388: if_icmpeq -> 418
    //   391: aload_0
    //   392: invokevirtual parseVariableDeclaration : ()Lgnu/expr/Declaration;
    //   395: astore #6
    //   397: aload #6
    //   399: ifnonnull -> 492
    //   402: aload_0
    //   403: ldc_w 'missing parameter name'
    //   406: invokevirtual error : (Ljava/lang/String;)V
    //   409: aload_0
    //   410: getfield curToken : I
    //   413: bipush #41
    //   415: if_icmpne -> 540
    //   418: aload_0
    //   419: invokevirtual getRawToken : ()I
    //   422: pop
    //   423: aload_0
    //   424: invokevirtual parseOptionalTypeDeclaration : ()Lgnu/expr/Expression;
    //   427: astore #6
    //   429: aload #4
    //   431: aload_0
    //   432: invokevirtual parseEnclosedExpr : ()Lgnu/expr/Expression;
    //   435: putfield body : Lgnu/expr/Expression;
    //   438: aload_0
    //   439: getfield comp : Lgnu/expr/Compilation;
    //   442: aload #4
    //   444: invokevirtual pop : (Lgnu/expr/ScopeExp;)V
    //   447: aload #6
    //   449: ifnull -> 463
    //   452: aload #4
    //   454: aload #6
    //   456: aload_0
    //   457: getfield interpreter : Lgnu/xquery/lang/XQuery;
    //   460: invokevirtual setCoercedReturnValue : (Lgnu/expr/Expression;Lgnu/expr/Language;)V
    //   463: new gnu/expr/SetExp
    //   466: dup
    //   467: aload #5
    //   469: aload #4
    //   471: invokespecial <init> : (Lgnu/expr/Declaration;Lgnu/expr/Expression;)V
    //   474: astore #6
    //   476: aload #6
    //   478: iconst_1
    //   479: invokevirtual setDefining : (Z)V
    //   482: aload #5
    //   484: aload #4
    //   486: invokevirtual noteValue : (Lgnu/expr/Expression;)V
    //   489: aload #6
    //   491: areturn
    //   492: aload #4
    //   494: aload #6
    //   496: invokevirtual addDeclaration : (Lgnu/expr/Declaration;)V
    //   499: aload_0
    //   500: invokevirtual getRawToken : ()I
    //   503: pop
    //   504: aload #4
    //   506: aload #4
    //   508: getfield min_args : I
    //   511: iconst_1
    //   512: iadd
    //   513: putfield min_args : I
    //   516: aload #4
    //   518: aload #4
    //   520: getfield max_args : I
    //   523: iconst_1
    //   524: iadd
    //   525: putfield max_args : I
    //   528: aload #6
    //   530: aload_0
    //   531: invokevirtual parseOptionalTypeDeclaration : ()Lgnu/expr/Expression;
    //   534: invokevirtual setTypeExp : (Lgnu/expr/Expression;)V
    //   537: goto -> 409
    //   540: aload_0
    //   541: getfield curToken : I
    //   544: bipush #44
    //   546: if_icmpeq -> 612
    //   549: aload_0
    //   550: ldc_w 'missing ',' in parameter list'
    //   553: invokevirtual syntaxError : (Ljava/lang/String;)Lgnu/expr/Expression;
    //   556: astore #6
    //   558: aload_0
    //   559: invokevirtual getRawToken : ()I
    //   562: pop
    //   563: aload_0
    //   564: getfield curToken : I
    //   567: iflt -> 588
    //   570: aload_0
    //   571: getfield curToken : I
    //   574: bipush #59
    //   576: if_icmpeq -> 588
    //   579: aload_0
    //   580: getfield curToken : I
    //   583: bipush #59
    //   585: if_icmpne -> 591
    //   588: aload #6
    //   590: areturn
    //   591: aload_0
    //   592: getfield curToken : I
    //   595: bipush #41
    //   597: if_icmpeq -> 418
    //   600: aload_0
    //   601: getfield curToken : I
    //   604: bipush #44
    //   606: if_icmpne -> 558
    //   609: goto -> 391
    //   612: aload_0
    //   613: invokevirtual getRawToken : ()I
    //   616: pop
    //   617: goto -> 391
  }
  
  public Expression parseIfExpr() throws IOException, SyntaxException {
    char c1 = pushNesting('i');
    getRawToken();
    char c2 = pushNesting('(');
    Expression expression1 = parseExprSequence(41, false);
    popNesting(c2);
    if (this.curToken == -1)
      eofError("missing ')' - unexpected end-of-file"); 
    getRawToken();
    if (!match("then")) {
      syntaxError("missing 'then'");
    } else {
      getRawToken();
    } 
    Expression expression2 = parseExpr();
    if (!match("else")) {
      syntaxError("missing 'else'");
      popNesting(c1);
      Expression expression = parseExpr();
      return (Expression)new IfExp(booleanValue(expression1), expression2, expression);
    } 
    getRawToken();
    popNesting(c1);
    Expression expression3 = parseExpr();
    return (Expression)new IfExp(booleanValue(expression1), expression2, expression3);
  }
  
  Expression parseIntersectExceptExpr() throws IOException, SyntaxException {
    for (Expression expression = parsePathExpr();; expression = makeBinary(i, expression, parsePathExpr())) {
      int i = peekOperator();
      if (i != 420 && i != 421)
        return expression; 
      getRawToken();
    } 
  }
  
  public Expression parseItemType() throws IOException, SyntaxException {
    ReferenceExp referenceExp;
    peekOperand();
    Expression expression = parseMaybeKindTest();
    if (expression != null) {
      XDataType xDataType;
      if (this.parseContext == 67) {
        xDataType = XDataType.anyAtomicType;
      } else {
        return (Expression)xDataType;
      } 
    } else if (this.curToken == 237) {
      parseSimpleKindType();
      SingletonType singletonType = SingletonType.getInstance();
    } else {
      if (this.curToken == 65 || this.curToken == 81) {
        referenceExp = new ReferenceExp(new String(this.tokenBuffer, 0, this.tokenBufferLength));
        referenceExp.setFlag(16);
        maybeSetLine((Expression)referenceExp, this.curLine, this.curColumn);
        getRawToken();
        return (Expression)referenceExp;
      } 
      return null;
    } 
    return (Expression)QuoteExp.getInstance(referenceExp);
  }
  
  public Expression parseMaybeKindTest() throws IOException, SyntaxException {
    String str;
    boolean bool = false;
    switch (this.curToken) {
      default:
        return null;
      case 235:
      case 236:
        if (this.curToken == 236)
          bool = true; 
        return parseNamedNodeType(bool);
      case 231:
        parseSimpleKindType();
        nodeType = NodeType.textNodeTest;
        return (Expression)QuoteExp.getInstance(nodeType);
      case 232:
        parseSimpleKindType();
        nodeType = NodeType.commentNodeTest;
        return (Expression)QuoteExp.getInstance(nodeType);
      case 234:
        parseSimpleKindType();
        nodeType = NodeType.documentNodeTest;
        return (Expression)QuoteExp.getInstance(nodeType);
      case 230:
        parseSimpleKindType();
        nodeType = NodeType.anyNodeTest;
        return (Expression)QuoteExp.getInstance(nodeType);
      case 233:
        break;
    } 
    getRawToken();
    NodeType nodeType = null;
    if (this.curToken == 65 || this.curToken == 34) {
      str = new String(this.tokenBuffer, 0, this.tokenBufferLength);
      getRawToken();
    } 
    if (this.curToken == 41) {
      getRawToken();
    } else {
      error("expected ')'");
    } 
    ProcessingInstructionType processingInstructionType = ProcessingInstructionType.getInstance(str);
    return (Expression)QuoteExp.getInstance(processingInstructionType);
  }
  
  Expression parseMaybePrimaryExpr() throws IOException, SyntaxException {
    // Byte code:
    //   0: aload_0
    //   1: getfield curLine : I
    //   4: istore_2
    //   5: aload_0
    //   6: getfield curColumn : I
    //   9: istore_3
    //   10: aload_0
    //   11: invokevirtual peekOperand : ()I
    //   14: istore #4
    //   16: iload #4
    //   18: lookupswitch default -> 172, 34 -> 719, 36 -> 879, 40 -> 178, 48 -> 750, 49 -> 777, 50 -> 777, 70 -> 926, 123 -> 521, 197 -> 192, 249 -> 1659, 250 -> 1659, 251 -> 1083, 252 -> 1083, 253 -> 1083, 254 -> 1083, 255 -> 1083, 256 -> 1083, 404 -> 538
    //   172: aconst_null
    //   173: astore #7
    //   175: aload #7
    //   177: areturn
    //   178: aload_0
    //   179: invokevirtual parseParenExpr : ()Lgnu/expr/Expression;
    //   182: astore #7
    //   184: aload_0
    //   185: invokevirtual getRawToken : ()I
    //   188: pop
    //   189: aload #7
    //   191: areturn
    //   192: new java/util/Stack
    //   195: dup
    //   196: invokespecial <init> : ()V
    //   199: astore #8
    //   201: aload_0
    //   202: invokevirtual getRawToken : ()I
    //   205: pop
    //   206: aload_0
    //   207: getfield curToken : I
    //   210: bipush #81
    //   212: if_icmpeq -> 347
    //   215: aload_0
    //   216: getfield curToken : I
    //   219: bipush #65
    //   221: if_icmpeq -> 347
    //   224: aload_0
    //   225: ldc_w 'missing pragma name'
    //   228: invokevirtual syntaxError : (Ljava/lang/String;)Lgnu/expr/Expression;
    //   231: astore #7
    //   233: aload #8
    //   235: aload #7
    //   237: invokevirtual push : (Ljava/lang/Object;)Ljava/lang/Object;
    //   240: pop
    //   241: new java/lang/StringBuffer
    //   244: dup
    //   245: invokespecial <init> : ()V
    //   248: astore #7
    //   250: iconst_m1
    //   251: istore_3
    //   252: aload_0
    //   253: invokevirtual read : ()I
    //   256: istore #5
    //   258: iload_3
    //   259: iconst_1
    //   260: iadd
    //   261: istore_2
    //   262: iload #5
    //   264: istore_3
    //   265: iload_2
    //   266: istore #4
    //   268: iload #5
    //   270: iflt -> 290
    //   273: iload_2
    //   274: istore_3
    //   275: iload #5
    //   277: i2c
    //   278: invokestatic isWhitespace : (C)Z
    //   281: ifne -> 252
    //   284: iload_2
    //   285: istore #4
    //   287: iload #5
    //   289: istore_3
    //   290: iload_3
    //   291: bipush #35
    //   293: if_icmpne -> 305
    //   296: aload_0
    //   297: invokevirtual peek : ()I
    //   300: bipush #41
    //   302: if_icmpeq -> 371
    //   305: iload_3
    //   306: ifge -> 316
    //   309: aload_0
    //   310: ldc_w 'pragma ended by end-of-file'
    //   313: invokevirtual eofError : (Ljava/lang/String;)V
    //   316: iload #4
    //   318: ifne -> 328
    //   321: aload_0
    //   322: ldc_w 'missing space between pragma and extension content'
    //   325: invokevirtual error : (Ljava/lang/String;)V
    //   328: iconst_1
    //   329: istore #4
    //   331: aload #7
    //   333: iload_3
    //   334: i2c
    //   335: invokevirtual append : (C)Ljava/lang/StringBuffer;
    //   338: pop
    //   339: aload_0
    //   340: invokevirtual read : ()I
    //   343: istore_3
    //   344: goto -> 290
    //   347: new java/lang/String
    //   350: dup
    //   351: aload_0
    //   352: getfield tokenBuffer : [C
    //   355: iconst_0
    //   356: aload_0
    //   357: getfield tokenBufferLength : I
    //   360: invokespecial <init> : ([CII)V
    //   363: invokestatic getInstance : (Ljava/lang/Object;)Lgnu/expr/QuoteExp;
    //   366: astore #7
    //   368: goto -> 233
    //   371: aload_0
    //   372: invokevirtual read : ()I
    //   375: pop
    //   376: aload #8
    //   378: aload #7
    //   380: invokevirtual toString : ()Ljava/lang/String;
    //   383: invokestatic getInstance : (Ljava/lang/Object;)Lgnu/expr/QuoteExp;
    //   386: invokevirtual push : (Ljava/lang/Object;)Ljava/lang/Object;
    //   389: pop
    //   390: aload_0
    //   391: invokevirtual getRawToken : ()I
    //   394: pop
    //   395: aload_0
    //   396: getfield curToken : I
    //   399: sipush #197
    //   402: if_icmpeq -> 201
    //   405: aload_0
    //   406: getfield curToken : I
    //   409: bipush #123
    //   411: if_icmpne -> 509
    //   414: aload_0
    //   415: invokevirtual getRawToken : ()I
    //   418: pop
    //   419: aload_0
    //   420: getfield curToken : I
    //   423: bipush #125
    //   425: if_icmpeq -> 468
    //   428: aload_0
    //   429: bipush #123
    //   431: invokevirtual pushNesting : (C)C
    //   434: istore_1
    //   435: aload #8
    //   437: aload_0
    //   438: bipush #125
    //   440: iconst_0
    //   441: invokevirtual parseExprSequence : (IZ)Lgnu/expr/Expression;
    //   444: invokevirtual push : (Ljava/lang/Object;)Ljava/lang/Object;
    //   447: pop
    //   448: aload_0
    //   449: iload_1
    //   450: invokevirtual popNesting : (C)V
    //   453: aload_0
    //   454: getfield curToken : I
    //   457: iconst_m1
    //   458: if_icmpne -> 468
    //   461: aload_0
    //   462: ldc_w 'missing '}' - unexpected end-of-file'
    //   465: invokevirtual eofError : (Ljava/lang/String;)V
    //   468: aload #8
    //   470: invokevirtual size : ()I
    //   473: anewarray gnu/expr/Expression
    //   476: astore #7
    //   478: aload #8
    //   480: aload #7
    //   482: invokevirtual copyInto : ([Ljava/lang/Object;)V
    //   485: new gnu/expr/ApplyExp
    //   488: dup
    //   489: new gnu/expr/ReferenceExp
    //   492: dup
    //   493: getstatic gnu/xquery/lang/XQResolveNames.handleExtensionDecl : Lgnu/expr/Declaration;
    //   496: invokespecial <init> : (Lgnu/expr/Declaration;)V
    //   499: aload #7
    //   501: invokespecial <init> : (Lgnu/expr/Expression;[Lgnu/expr/Expression;)V
    //   504: astore #7
    //   506: goto -> 184
    //   509: aload_0
    //   510: ldc_w 'missing '{' after pragma'
    //   513: invokevirtual syntaxError : (Ljava/lang/String;)Lgnu/expr/Expression;
    //   516: astore #7
    //   518: goto -> 184
    //   521: aload_0
    //   522: ldc_w 'saw unexpected '{' - assume you meant '(''
    //   525: invokevirtual syntaxError : (Ljava/lang/String;)Lgnu/expr/Expression;
    //   528: astore #7
    //   530: aload_0
    //   531: invokevirtual parseEnclosedExpr : ()Lgnu/expr/Expression;
    //   534: pop
    //   535: goto -> 184
    //   538: aload_0
    //   539: invokevirtual read : ()I
    //   542: istore #4
    //   544: iload #4
    //   546: bipush #47
    //   548: if_icmpne -> 699
    //   551: aload_0
    //   552: invokevirtual getRawToken : ()I
    //   555: pop
    //   556: aload_0
    //   557: getfield curToken : I
    //   560: bipush #65
    //   562: if_icmpeq -> 583
    //   565: aload_0
    //   566: getfield curToken : I
    //   569: bipush #81
    //   571: if_icmpeq -> 583
    //   574: aload_0
    //   575: getfield curToken : I
    //   578: bipush #67
    //   580: if_icmpne -> 691
    //   583: new java/lang/StringBuilder
    //   586: dup
    //   587: invokespecial <init> : ()V
    //   590: ldc_w 'saw end tag '</'
    //   593: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   596: new java/lang/String
    //   599: dup
    //   600: aload_0
    //   601: getfield tokenBuffer : [C
    //   604: iconst_0
    //   605: aload_0
    //   606: getfield tokenBufferLength : I
    //   609: invokespecial <init> : ([CII)V
    //   612: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   615: ldc_w '>' not in an element constructor'
    //   618: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   621: invokevirtual toString : ()Ljava/lang/String;
    //   624: astore #7
    //   626: aload_0
    //   627: iload_2
    //   628: putfield curLine : I
    //   631: aload_0
    //   632: iload_3
    //   633: putfield curColumn : I
    //   636: aload_0
    //   637: aload #7
    //   639: invokevirtual syntaxError : (Ljava/lang/String;)Lgnu/expr/Expression;
    //   642: astore #8
    //   644: aload #8
    //   646: astore #7
    //   648: aload_0
    //   649: getfield curToken : I
    //   652: sipush #405
    //   655: if_icmpeq -> 175
    //   658: aload #8
    //   660: astore #7
    //   662: aload_0
    //   663: getfield curToken : I
    //   666: iconst_m1
    //   667: if_icmpeq -> 175
    //   670: aload #8
    //   672: astore #7
    //   674: aload_0
    //   675: getfield curToken : I
    //   678: bipush #10
    //   680: if_icmpeq -> 175
    //   683: aload_0
    //   684: invokevirtual getRawToken : ()I
    //   687: pop
    //   688: goto -> 644
    //   691: ldc_w 'saw end tag '</' not in an element constructor'
    //   694: astore #7
    //   696: goto -> 626
    //   699: aload_0
    //   700: iload #4
    //   702: iconst_0
    //   703: invokevirtual parseXMLConstructor : (IZ)Lgnu/expr/Expression;
    //   706: astore #7
    //   708: aload_0
    //   709: aload #7
    //   711: iload_2
    //   712: iload_3
    //   713: invokevirtual maybeSetLine : (Lgnu/expr/Expression;II)V
    //   716: goto -> 184
    //   719: new gnu/expr/QuoteExp
    //   722: dup
    //   723: new java/lang/String
    //   726: dup
    //   727: aload_0
    //   728: getfield tokenBuffer : [C
    //   731: iconst_0
    //   732: aload_0
    //   733: getfield tokenBufferLength : I
    //   736: invokespecial <init> : ([CII)V
    //   739: invokevirtual intern : ()Ljava/lang/String;
    //   742: invokespecial <init> : (Ljava/lang/Object;)V
    //   745: astore #7
    //   747: goto -> 184
    //   750: new gnu/expr/QuoteExp
    //   753: dup
    //   754: aload_0
    //   755: getfield tokenBuffer : [C
    //   758: iconst_0
    //   759: aload_0
    //   760: getfield tokenBufferLength : I
    //   763: bipush #10
    //   765: iconst_0
    //   766: invokestatic valueOf : ([CIIIZ)Lgnu/math/IntNum;
    //   769: invokespecial <init> : (Ljava/lang/Object;)V
    //   772: astore #7
    //   774: goto -> 184
    //   777: new java/lang/String
    //   780: dup
    //   781: aload_0
    //   782: getfield tokenBuffer : [C
    //   785: iconst_0
    //   786: aload_0
    //   787: getfield tokenBufferLength : I
    //   790: invokespecial <init> : ([CII)V
    //   793: astore #8
    //   795: iload #4
    //   797: bipush #49
    //   799: if_icmpne -> 827
    //   802: new java/math/BigDecimal
    //   805: dup
    //   806: aload #8
    //   808: invokespecial <init> : (Ljava/lang/String;)V
    //   811: astore #7
    //   813: new gnu/expr/QuoteExp
    //   816: dup
    //   817: aload #7
    //   819: invokespecial <init> : (Ljava/lang/Object;)V
    //   822: astore #7
    //   824: goto -> 184
    //   827: new java/lang/Double
    //   830: dup
    //   831: aload #8
    //   833: invokespecial <init> : (Ljava/lang/String;)V
    //   836: astore #7
    //   838: goto -> 813
    //   841: astore #7
    //   843: aload_0
    //   844: new java/lang/StringBuilder
    //   847: dup
    //   848: invokespecial <init> : ()V
    //   851: ldc_w 'invalid decimal literal: ''
    //   854: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   857: aload #8
    //   859: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   862: ldc_w '''
    //   865: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   868: invokevirtual toString : ()Ljava/lang/String;
    //   871: invokevirtual syntaxError : (Ljava/lang/String;)Lgnu/expr/Expression;
    //   874: astore #7
    //   876: goto -> 184
    //   879: aload_0
    //   880: invokevirtual parseVariable : ()Ljava/lang/Object;
    //   883: astore #7
    //   885: aload #7
    //   887: ifnonnull -> 898
    //   890: aload_0
    //   891: ldc_w 'missing Variable'
    //   894: invokevirtual syntaxError : (Ljava/lang/String;)Lgnu/expr/Expression;
    //   897: areturn
    //   898: new gnu/expr/ReferenceExp
    //   901: dup
    //   902: aload #7
    //   904: invokespecial <init> : (Ljava/lang/Object;)V
    //   907: astore #7
    //   909: aload_0
    //   910: aload #7
    //   912: aload_0
    //   913: getfield curLine : I
    //   916: aload_0
    //   917: getfield curColumn : I
    //   920: invokevirtual maybeSetLine : (Lgnu/expr/Expression;II)V
    //   923: goto -> 184
    //   926: new java/lang/String
    //   929: dup
    //   930: aload_0
    //   931: getfield tokenBuffer : [C
    //   934: iconst_0
    //   935: aload_0
    //   936: getfield tokenBufferLength : I
    //   939: invokespecial <init> : ([CII)V
    //   942: astore #8
    //   944: aload_0
    //   945: bipush #40
    //   947: invokevirtual pushNesting : (C)C
    //   950: istore_1
    //   951: aload_0
    //   952: invokevirtual getRawToken : ()I
    //   955: pop
    //   956: new java/util/Vector
    //   959: dup
    //   960: bipush #10
    //   962: invokespecial <init> : (I)V
    //   965: astore #9
    //   967: aload_0
    //   968: getfield curToken : I
    //   971: bipush #41
    //   973: if_icmpeq -> 994
    //   976: aload #9
    //   978: aload_0
    //   979: invokevirtual parseExpr : ()Lgnu/expr/Expression;
    //   982: invokevirtual addElement : (Ljava/lang/Object;)V
    //   985: aload_0
    //   986: getfield curToken : I
    //   989: bipush #41
    //   991: if_icmpne -> 1058
    //   994: aload #9
    //   996: invokevirtual size : ()I
    //   999: anewarray gnu/expr/Expression
    //   1002: astore #7
    //   1004: aload #9
    //   1006: aload #7
    //   1008: invokevirtual copyInto : ([Ljava/lang/Object;)V
    //   1011: new gnu/expr/ReferenceExp
    //   1014: dup
    //   1015: aload #8
    //   1017: aconst_null
    //   1018: invokespecial <init> : (Ljava/lang/Object;Lgnu/expr/Declaration;)V
    //   1021: astore #8
    //   1023: aload #8
    //   1025: iconst_1
    //   1026: invokevirtual setProcedureName : (Z)V
    //   1029: new gnu/expr/ApplyExp
    //   1032: dup
    //   1033: aload #8
    //   1035: aload #7
    //   1037: invokespecial <init> : (Lgnu/expr/Expression;[Lgnu/expr/Expression;)V
    //   1040: astore #7
    //   1042: aload_0
    //   1043: aload #7
    //   1045: iload_2
    //   1046: iload_3
    //   1047: invokevirtual maybeSetLine : (Lgnu/expr/Expression;II)V
    //   1050: aload_0
    //   1051: iload_1
    //   1052: invokevirtual popNesting : (C)V
    //   1055: goto -> 184
    //   1058: aload_0
    //   1059: getfield curToken : I
    //   1062: bipush #44
    //   1064: if_icmpeq -> 1075
    //   1067: aload_0
    //   1068: ldc_w 'missing ')' after function call'
    //   1071: invokevirtual syntaxError : (Ljava/lang/String;)Lgnu/expr/Expression;
    //   1074: areturn
    //   1075: aload_0
    //   1076: invokevirtual getRawToken : ()I
    //   1079: pop
    //   1080: goto -> 976
    //   1083: aload_0
    //   1084: invokevirtual getRawToken : ()I
    //   1087: pop
    //   1088: new java/util/Vector
    //   1091: dup
    //   1092: invokespecial <init> : ()V
    //   1095: astore #9
    //   1097: iload #4
    //   1099: sipush #251
    //   1102: if_icmpeq -> 1113
    //   1105: iload #4
    //   1107: sipush #252
    //   1110: if_icmpne -> 1293
    //   1113: aload_0
    //   1114: getfield curToken : I
    //   1117: bipush #65
    //   1119: if_icmpeq -> 1131
    //   1122: aload_0
    //   1123: getfield curToken : I
    //   1126: bipush #81
    //   1128: if_icmpne -> 1253
    //   1131: iload #4
    //   1133: sipush #251
    //   1136: if_icmpeq -> 1247
    //   1139: iconst_1
    //   1140: istore #6
    //   1142: aload_0
    //   1143: iload #6
    //   1145: invokevirtual parseNameTest : (Z)Lgnu/expr/Expression;
    //   1148: astore #7
    //   1150: iload #4
    //   1152: sipush #251
    //   1155: if_icmpne -> 1279
    //   1158: iconst_1
    //   1159: istore #6
    //   1161: aload #9
    //   1163: aload #7
    //   1165: iload #6
    //   1167: invokestatic castQName : (Lgnu/expr/Expression;Z)Lgnu/expr/ApplyExp;
    //   1170: invokevirtual addElement : (Ljava/lang/Object;)V
    //   1173: iload #4
    //   1175: sipush #251
    //   1178: if_icmpne -> 1285
    //   1181: new gnu/kawa/xml/MakeElement
    //   1184: dup
    //   1185: invokespecial <init> : ()V
    //   1188: astore #7
    //   1190: aload #7
    //   1192: aload_0
    //   1193: getfield copyNamespacesMode : I
    //   1196: putfield copyNamespacesMode : I
    //   1199: new gnu/expr/QuoteExp
    //   1202: dup
    //   1203: aload #7
    //   1205: invokespecial <init> : (Ljava/lang/Object;)V
    //   1208: astore #7
    //   1210: aload_0
    //   1211: invokevirtual getRawToken : ()I
    //   1214: pop
    //   1215: aload_0
    //   1216: bipush #123
    //   1218: invokevirtual pushNesting : (C)C
    //   1221: istore_1
    //   1222: aload_0
    //   1223: ldc_w 'unexpected end-of-file after '{''
    //   1226: invokevirtual peekNonSpace : (Ljava/lang/String;)I
    //   1229: pop
    //   1230: aload_0
    //   1231: getfield curToken : I
    //   1234: bipush #123
    //   1236: if_icmpeq -> 1465
    //   1239: aload_0
    //   1240: ldc_w 'missing '{''
    //   1243: invokevirtual syntaxError : (Ljava/lang/String;)Lgnu/expr/Expression;
    //   1246: areturn
    //   1247: iconst_0
    //   1248: istore #6
    //   1250: goto -> 1142
    //   1253: aload_0
    //   1254: getfield curToken : I
    //   1257: bipush #123
    //   1259: if_icmpne -> 1271
    //   1262: aload_0
    //   1263: invokevirtual parseEnclosedExpr : ()Lgnu/expr/Expression;
    //   1266: astore #7
    //   1268: goto -> 1150
    //   1271: aload_0
    //   1272: ldc_w 'missing element/attribute name'
    //   1275: invokevirtual syntaxError : (Ljava/lang/String;)Lgnu/expr/Expression;
    //   1278: areturn
    //   1279: iconst_0
    //   1280: istore #6
    //   1282: goto -> 1161
    //   1285: getstatic gnu/kawa/xml/MakeAttribute.makeAttributeExp : Lgnu/expr/QuoteExp;
    //   1288: astore #7
    //   1290: goto -> 1210
    //   1293: iload #4
    //   1295: sipush #256
    //   1298: if_icmpne -> 1315
    //   1301: ldc_w 'gnu.kawa.xml.DocumentConstructor'
    //   1304: ldc_w 'documentConstructor'
    //   1307: invokestatic makeFunctionExp : (Ljava/lang/String;Ljava/lang/String;)Lgnu/expr/Expression;
    //   1310: astore #7
    //   1312: goto -> 1215
    //   1315: iload #4
    //   1317: sipush #254
    //   1320: if_icmpne -> 1337
    //   1323: ldc_w 'gnu.kawa.xml.CommentConstructor'
    //   1326: ldc_w 'commentConstructor'
    //   1329: invokestatic makeFunctionExp : (Ljava/lang/String;Ljava/lang/String;)Lgnu/expr/Expression;
    //   1332: astore #7
    //   1334: goto -> 1215
    //   1337: iload #4
    //   1339: sipush #255
    //   1342: if_icmpne -> 1451
    //   1345: aload_0
    //   1346: getfield curToken : I
    //   1349: bipush #65
    //   1351: if_icmpne -> 1408
    //   1354: new gnu/expr/QuoteExp
    //   1357: dup
    //   1358: new java/lang/String
    //   1361: dup
    //   1362: aload_0
    //   1363: getfield tokenBuffer : [C
    //   1366: iconst_0
    //   1367: aload_0
    //   1368: getfield tokenBufferLength : I
    //   1371: invokespecial <init> : ([CII)V
    //   1374: invokevirtual intern : ()Ljava/lang/String;
    //   1377: invokespecial <init> : (Ljava/lang/Object;)V
    //   1380: astore #7
    //   1382: aload #9
    //   1384: aload #7
    //   1386: invokevirtual addElement : (Ljava/lang/Object;)V
    //   1389: ldc_w 'gnu.kawa.xml.MakeProcInst'
    //   1392: ldc_w 'makeProcInst'
    //   1395: invokestatic makeFunctionExp : (Ljava/lang/String;Ljava/lang/String;)Lgnu/expr/Expression;
    //   1398: astore #7
    //   1400: aload_0
    //   1401: invokevirtual getRawToken : ()I
    //   1404: pop
    //   1405: goto -> 1215
    //   1408: aload_0
    //   1409: getfield curToken : I
    //   1412: bipush #123
    //   1414: if_icmpne -> 1426
    //   1417: aload_0
    //   1418: invokevirtual parseEnclosedExpr : ()Lgnu/expr/Expression;
    //   1421: astore #7
    //   1423: goto -> 1382
    //   1426: aload_0
    //   1427: ldc_w 'expected NCName or '{' after 'processing-instruction''
    //   1430: invokevirtual syntaxError : (Ljava/lang/String;)Lgnu/expr/Expression;
    //   1433: astore #8
    //   1435: aload #8
    //   1437: astore #7
    //   1439: aload_0
    //   1440: getfield curToken : I
    //   1443: bipush #81
    //   1445: if_icmpeq -> 1382
    //   1448: aload #8
    //   1450: areturn
    //   1451: ldc_w 'gnu.kawa.xml.MakeText'
    //   1454: ldc_w 'makeText'
    //   1457: invokestatic makeFunctionExp : (Ljava/lang/String;Ljava/lang/String;)Lgnu/expr/Expression;
    //   1460: astore #7
    //   1462: goto -> 1215
    //   1465: aload_0
    //   1466: invokevirtual getRawToken : ()I
    //   1469: pop
    //   1470: iload #4
    //   1472: sipush #253
    //   1475: if_icmpeq -> 1494
    //   1478: iload #4
    //   1480: sipush #254
    //   1483: if_icmpeq -> 1494
    //   1486: iload #4
    //   1488: sipush #255
    //   1491: if_icmpne -> 1546
    //   1494: iload #4
    //   1496: sipush #255
    //   1499: if_icmpne -> 1540
    //   1502: iconst_1
    //   1503: istore #6
    //   1505: aload #9
    //   1507: aload_0
    //   1508: bipush #125
    //   1510: iload #6
    //   1512: invokevirtual parseExprSequence : (IZ)Lgnu/expr/Expression;
    //   1515: invokevirtual addElement : (Ljava/lang/Object;)V
    //   1518: aload_0
    //   1519: iload_1
    //   1520: invokevirtual popNesting : (C)V
    //   1523: aload_0
    //   1524: getfield curToken : I
    //   1527: bipush #125
    //   1529: if_icmpeq -> 1590
    //   1532: aload_0
    //   1533: ldc_w 'missing '}''
    //   1536: invokevirtual syntaxError : (Ljava/lang/String;)Lgnu/expr/Expression;
    //   1539: areturn
    //   1540: iconst_0
    //   1541: istore #6
    //   1543: goto -> 1505
    //   1546: aload_0
    //   1547: getfield curToken : I
    //   1550: bipush #125
    //   1552: if_icmpeq -> 1518
    //   1555: aload #9
    //   1557: aload_0
    //   1558: invokevirtual parseExpr : ()Lgnu/expr/Expression;
    //   1561: invokevirtual addElement : (Ljava/lang/Object;)V
    //   1564: aload_0
    //   1565: getfield curToken : I
    //   1568: bipush #44
    //   1570: if_icmpne -> 1518
    //   1573: aload_0
    //   1574: invokevirtual getRawToken : ()I
    //   1577: pop
    //   1578: aload #9
    //   1580: aload_0
    //   1581: invokevirtual parseExpr : ()Lgnu/expr/Expression;
    //   1584: invokevirtual addElement : (Ljava/lang/Object;)V
    //   1587: goto -> 1564
    //   1590: aload #9
    //   1592: invokevirtual size : ()I
    //   1595: anewarray gnu/expr/Expression
    //   1598: astore #8
    //   1600: aload #9
    //   1602: aload #8
    //   1604: invokevirtual copyInto : ([Ljava/lang/Object;)V
    //   1607: new gnu/expr/ApplyExp
    //   1610: dup
    //   1611: aload #7
    //   1613: aload #8
    //   1615: invokespecial <init> : (Lgnu/expr/Expression;[Lgnu/expr/Expression;)V
    //   1618: astore #8
    //   1620: aload_0
    //   1621: aload #8
    //   1623: iload_2
    //   1624: iload_3
    //   1625: invokevirtual maybeSetLine : (Lgnu/expr/Expression;II)V
    //   1628: iload #4
    //   1630: sipush #256
    //   1633: if_icmpeq -> 1648
    //   1636: aload #8
    //   1638: astore #7
    //   1640: iload #4
    //   1642: sipush #251
    //   1645: if_icmpne -> 184
    //   1648: aload_0
    //   1649: aload #8
    //   1651: invokevirtual wrapWithBaseUri : (Lgnu/expr/Expression;)Lgnu/expr/Expression;
    //   1654: astore #7
    //   1656: goto -> 184
    //   1659: aload_0
    //   1660: invokevirtual getRawToken : ()I
    //   1663: pop
    //   1664: aload_0
    //   1665: bipush #125
    //   1667: iconst_0
    //   1668: invokevirtual parseExprSequence : (IZ)Lgnu/expr/Expression;
    //   1671: astore #7
    //   1673: goto -> 184
    // Exception table:
    //   from	to	target	type
    //   802	813	841	java/lang/Throwable
    //   813	824	841	java/lang/Throwable
    //   827	838	841	java/lang/Throwable
  }
  
  Expression parseNameTest(boolean paramBoolean) throws IOException, SyntaxException {
    // Byte code:
    //   0: aconst_null
    //   1: astore #4
    //   3: aconst_null
    //   4: astore #5
    //   6: aload_0
    //   7: getfield curToken : I
    //   10: bipush #81
    //   12: if_icmpne -> 206
    //   15: aload_0
    //   16: getfield tokenBufferLength : I
    //   19: istore_2
    //   20: aload_0
    //   21: getfield tokenBuffer : [C
    //   24: astore #4
    //   26: iload_2
    //   27: iconst_1
    //   28: isub
    //   29: istore_3
    //   30: iload_3
    //   31: istore_2
    //   32: aload #4
    //   34: iload_3
    //   35: caload
    //   36: bipush #58
    //   38: if_icmpne -> 20
    //   41: new java/lang/String
    //   44: dup
    //   45: aload_0
    //   46: getfield tokenBuffer : [C
    //   49: iconst_0
    //   50: iload_3
    //   51: invokespecial <init> : ([CII)V
    //   54: astore #5
    //   56: iload_3
    //   57: iconst_1
    //   58: iadd
    //   59: istore_2
    //   60: new java/lang/String
    //   63: dup
    //   64: aload_0
    //   65: getfield tokenBuffer : [C
    //   68: iload_2
    //   69: aload_0
    //   70: getfield tokenBufferLength : I
    //   73: iload_2
    //   74: isub
    //   75: invokespecial <init> : ([CII)V
    //   78: astore #4
    //   80: aload #5
    //   82: astore #6
    //   84: aload #5
    //   86: ifnull -> 96
    //   89: aload #5
    //   91: invokevirtual intern : ()Ljava/lang/String;
    //   94: astore #6
    //   96: new gnu/expr/ApplyExp
    //   99: dup
    //   100: new gnu/expr/ReferenceExp
    //   103: dup
    //   104: getstatic gnu/xquery/lang/XQResolveNames.resolvePrefixDecl : Lgnu/expr/Declaration;
    //   107: invokespecial <init> : (Lgnu/expr/Declaration;)V
    //   110: iconst_1
    //   111: anewarray gnu/expr/Expression
    //   114: dup
    //   115: iconst_0
    //   116: aload #6
    //   118: invokestatic getInstance : (Ljava/lang/Object;)Lgnu/expr/QuoteExp;
    //   121: aastore
    //   122: invokespecial <init> : (Lgnu/expr/Expression;[Lgnu/expr/Expression;)V
    //   125: astore #5
    //   127: aload #4
    //   129: ifnonnull -> 469
    //   132: ldc_w ''
    //   135: astore #4
    //   137: new gnu/expr/QuoteExp
    //   140: dup
    //   141: aload #4
    //   143: invokespecial <init> : (Ljava/lang/Object;)V
    //   146: astore #4
    //   148: new gnu/expr/QuoteExp
    //   151: dup
    //   152: aload #6
    //   154: invokespecial <init> : (Ljava/lang/Object;)V
    //   157: astore #6
    //   159: new gnu/expr/ApplyExp
    //   162: dup
    //   163: getstatic gnu/expr/Compilation.typeSymbol : Lgnu/bytecode/ClassType;
    //   166: ldc_w 'make'
    //   169: iconst_3
    //   170: invokevirtual getDeclaredMethod : (Ljava/lang/String;I)Lgnu/bytecode/Method;
    //   173: iconst_3
    //   174: anewarray gnu/expr/Expression
    //   177: dup
    //   178: iconst_0
    //   179: aload #5
    //   181: aastore
    //   182: dup
    //   183: iconst_1
    //   184: aload #4
    //   186: aastore
    //   187: dup
    //   188: iconst_2
    //   189: aload #6
    //   191: aastore
    //   192: invokespecial <init> : (Lgnu/bytecode/Method;[Lgnu/expr/Expression;)V
    //   195: astore #4
    //   197: aload #4
    //   199: iconst_4
    //   200: invokevirtual setFlag : (I)V
    //   203: aload #4
    //   205: areturn
    //   206: aload_0
    //   207: getfield curToken : I
    //   210: sipush #415
    //   213: if_icmpne -> 361
    //   216: aload_0
    //   217: invokevirtual read : ()I
    //   220: istore_2
    //   221: ldc_w ''
    //   224: astore #5
    //   226: iload_2
    //   227: bipush #58
    //   229: if_icmpeq -> 255
    //   232: aload_0
    //   233: iload_2
    //   234: invokevirtual unread : (I)V
    //   237: aload #5
    //   239: astore #4
    //   241: new gnu/mapping/Symbol
    //   244: dup
    //   245: aconst_null
    //   246: aload #4
    //   248: invokespecial <init> : (Lgnu/mapping/Namespace;Ljava/lang/String;)V
    //   251: invokestatic getInstance : (Ljava/lang/Object;)Lgnu/expr/QuoteExp;
    //   254: areturn
    //   255: aload_0
    //   256: invokevirtual read : ()I
    //   259: istore_2
    //   260: iload_2
    //   261: ifge -> 271
    //   264: aload_0
    //   265: ldc_w 'unexpected end-of-file after '*:''
    //   268: invokevirtual eofError : (Ljava/lang/String;)V
    //   271: iload_2
    //   272: i2c
    //   273: invokestatic isNameStart : (I)Z
    //   276: ifeq -> 336
    //   279: aload_0
    //   280: invokevirtual unread : ()V
    //   283: aload_0
    //   284: invokevirtual getRawToken : ()I
    //   287: pop
    //   288: aload_0
    //   289: getfield curToken : I
    //   292: bipush #65
    //   294: if_icmpeq -> 312
    //   297: aload_0
    //   298: ldc_w 'invalid name test'
    //   301: invokevirtual syntaxError : (Ljava/lang/String;)Lgnu/expr/Expression;
    //   304: pop
    //   305: aload #5
    //   307: astore #4
    //   309: goto -> 241
    //   312: new java/lang/String
    //   315: dup
    //   316: aload_0
    //   317: getfield tokenBuffer : [C
    //   320: iconst_0
    //   321: aload_0
    //   322: getfield tokenBufferLength : I
    //   325: invokespecial <init> : ([CII)V
    //   328: invokevirtual intern : ()Ljava/lang/String;
    //   331: astore #4
    //   333: goto -> 241
    //   336: aload #5
    //   338: astore #4
    //   340: iload_2
    //   341: bipush #42
    //   343: if_icmpeq -> 241
    //   346: aload_0
    //   347: ldc_w 'missing local-name after '*:''
    //   350: invokevirtual syntaxError : (Ljava/lang/String;)Lgnu/expr/Expression;
    //   353: pop
    //   354: aload #5
    //   356: astore #4
    //   358: goto -> 241
    //   361: aload_0
    //   362: getfield curToken : I
    //   365: bipush #65
    //   367: if_icmpne -> 417
    //   370: new java/lang/String
    //   373: dup
    //   374: aload_0
    //   375: getfield tokenBuffer : [C
    //   378: iconst_0
    //   379: aload_0
    //   380: getfield tokenBufferLength : I
    //   383: invokespecial <init> : ([CII)V
    //   386: astore #4
    //   388: iload_1
    //   389: ifeq -> 411
    //   392: new gnu/expr/QuoteExp
    //   395: dup
    //   396: getstatic gnu/mapping/Namespace.EmptyNamespace : Lgnu/mapping/Namespace;
    //   399: aload #4
    //   401: invokevirtual intern : ()Ljava/lang/String;
    //   404: invokevirtual getSymbol : (Ljava/lang/String;)Lgnu/mapping/Symbol;
    //   407: invokespecial <init> : (Ljava/lang/Object;)V
    //   410: areturn
    //   411: aconst_null
    //   412: astore #5
    //   414: goto -> 80
    //   417: aload_0
    //   418: getfield curToken : I
    //   421: bipush #67
    //   423: if_icmpne -> 80
    //   426: new java/lang/String
    //   429: dup
    //   430: aload_0
    //   431: getfield tokenBuffer : [C
    //   434: iconst_0
    //   435: aload_0
    //   436: getfield tokenBufferLength : I
    //   439: invokespecial <init> : ([CII)V
    //   442: astore #5
    //   444: aload_0
    //   445: invokevirtual read : ()I
    //   448: bipush #42
    //   450: if_icmpeq -> 461
    //   453: aload_0
    //   454: ldc_w 'invalid characters after 'NCName:''
    //   457: invokevirtual syntaxError : (Ljava/lang/String;)Lgnu/expr/Expression;
    //   460: pop
    //   461: ldc_w ''
    //   464: astore #4
    //   466: goto -> 80
    //   469: goto -> 137
  }
  
  public Expression parseNamedNodeType(boolean paramBoolean) throws IOException, SyntaxException {
    QuoteExp quoteExp;
    Expression expression2 = null;
    Expression expression1 = null;
    getRawToken();
    if (this.curToken == 41) {
      quoteExp = QuoteExp.getInstance(ElementType.MATCH_ANY_QNAME);
      getRawToken();
      return makeNamedNodeType(paramBoolean, (Expression)quoteExp, expression1);
    } 
    if (this.curToken == 81 || this.curToken == 65) {
      Expression expression = parseNameTest(paramBoolean);
    } else {
      if (this.curToken != 415)
        syntaxError("expected QName or *"); 
      quoteExp = QuoteExp.getInstance(ElementType.MATCH_ANY_QNAME);
    } 
    getRawToken();
    expression1 = expression2;
    if (this.curToken == 44) {
      getRawToken();
      if (this.curToken == 81 || this.curToken == 65) {
        expression1 = parseDataType();
      } else {
        syntaxError("expected QName");
        expression1 = expression2;
      } 
    } 
    if (this.curToken == 41) {
      getRawToken();
      return makeNamedNodeType(paramBoolean, (Expression)quoteExp, expression1);
    } 
    error("expected ')' after element");
    return makeNamedNodeType(paramBoolean, (Expression)quoteExp, expression1);
  }
  
  Expression parseNodeTest(int paramInt) throws IOException, SyntaxException {
    String str10;
    QuoteExp quoteExp10;
    ApplyExp applyExp10;
    String str9;
    QuoteExp quoteExp9;
    ApplyExp applyExp9;
    String str8;
    QuoteExp quoteExp8;
    ApplyExp applyExp8;
    String str7;
    QuoteExp quoteExp7;
    ApplyExp applyExp7;
    String str6;
    QuoteExp quoteExp6;
    ApplyExp applyExp6;
    String str5;
    QuoteExp quoteExp5;
    ApplyExp applyExp5;
    String str4;
    QuoteExp quoteExp4;
    ApplyExp applyExp4;
    String str3;
    QuoteExp quoteExp3;
    ApplyExp applyExp3;
    String str2;
    QuoteExp quoteExp2;
    ApplyExp applyExp2;
    Expression expression2;
    ReferenceExp referenceExp;
    peekOperand();
    Expression[] arrayOfExpression = new Expression[1];
    Expression expression1 = parseMaybeKindTest();
    if (expression1 != null) {
      arrayOfExpression[0] = expression1;
    } else if (this.curToken == 65 || this.curToken == 81 || this.curToken == 67 || this.curToken == 415) {
      boolean bool1;
      boolean bool2;
      if (paramInt == 2) {
        bool1 = true;
      } else {
        bool1 = false;
      } 
      if (paramInt == 2) {
        bool2 = true;
      } else {
        bool2 = false;
      } 
      arrayOfExpression[0] = makeNamedNodeType(bool1, parseNameTest(bool2), (Expression)null);
    } else {
      return (paramInt >= 0) ? syntaxError("unsupported axis '" + axisNames[paramInt] + "::'") : null;
    } 
    Declaration declaration = this.comp.lexical.lookup(DOT_VARNAME, false);
    if (declaration == null) {
      expression2 = syntaxError("node test when context item is undefined", "XPDY0002");
    } else {
      referenceExp = new ReferenceExp(DOT_VARNAME, (Declaration)expression2);
    } 
    if (expression1 == null)
      getRawToken(); 
    if (paramInt == 3 || paramInt == -1) {
      QuoteExp quoteExp = makeChildAxisStep;
      ApplyExp applyExp = new ApplyExp((Expression)quoteExp, arrayOfExpression);
      applyExp.setFlag(4);
      return (Expression)new ApplyExp((Expression)applyExp, new Expression[] { (Expression)referenceExp });
    } 
    if (paramInt == 4) {
      QuoteExp quoteExp = makeDescendantAxisStep;
      ApplyExp applyExp = new ApplyExp((Expression)quoteExp, arrayOfExpression);
      applyExp.setFlag(4);
      return (Expression)new ApplyExp((Expression)applyExp, new Expression[] { (Expression)referenceExp });
    } 
    switch (paramInt) {
      default:
        throw new Error();
      case 5:
        str10 = "DescendantOrSelf";
        quoteExp10 = QuoteExp.getInstance(new PrimProcedure("gnu.kawa.xml." + str10 + "Axis", "make", 1));
        applyExp10 = new ApplyExp((Expression)quoteExp10, arrayOfExpression);
        applyExp10.setFlag(4);
        return (Expression)new ApplyExp((Expression)applyExp10, new Expression[] { (Expression)referenceExp });
      case 12:
        str9 = "Self";
        quoteExp9 = QuoteExp.getInstance(new PrimProcedure("gnu.kawa.xml." + str9 + "Axis", "make", 1));
        applyExp9 = new ApplyExp((Expression)quoteExp9, arrayOfExpression);
        applyExp9.setFlag(4);
        return (Expression)new ApplyExp((Expression)applyExp9, new Expression[] { (Expression)referenceExp });
      case 9:
        str8 = "Parent";
        quoteExp8 = QuoteExp.getInstance(new PrimProcedure("gnu.kawa.xml." + str8 + "Axis", "make", 1));
        applyExp8 = new ApplyExp((Expression)quoteExp8, arrayOfExpression);
        applyExp8.setFlag(4);
        return (Expression)new ApplyExp((Expression)applyExp8, new Expression[] { (Expression)referenceExp });
      case 0:
        str7 = "Ancestor";
        quoteExp7 = QuoteExp.getInstance(new PrimProcedure("gnu.kawa.xml." + str7 + "Axis", "make", 1));
        applyExp7 = new ApplyExp((Expression)quoteExp7, arrayOfExpression);
        applyExp7.setFlag(4);
        return (Expression)new ApplyExp((Expression)applyExp7, new Expression[] { (Expression)referenceExp });
      case 1:
        str6 = "AncestorOrSelf";
        quoteExp6 = QuoteExp.getInstance(new PrimProcedure("gnu.kawa.xml." + str6 + "Axis", "make", 1));
        applyExp6 = new ApplyExp((Expression)quoteExp6, arrayOfExpression);
        applyExp6.setFlag(4);
        return (Expression)new ApplyExp((Expression)applyExp6, new Expression[] { (Expression)referenceExp });
      case 6:
        str5 = "Following";
        quoteExp5 = QuoteExp.getInstance(new PrimProcedure("gnu.kawa.xml." + str5 + "Axis", "make", 1));
        applyExp5 = new ApplyExp((Expression)quoteExp5, arrayOfExpression);
        applyExp5.setFlag(4);
        return (Expression)new ApplyExp((Expression)applyExp5, new Expression[] { (Expression)referenceExp });
      case 7:
        str4 = "FollowingSibling";
        quoteExp4 = QuoteExp.getInstance(new PrimProcedure("gnu.kawa.xml." + str4 + "Axis", "make", 1));
        applyExp4 = new ApplyExp((Expression)quoteExp4, arrayOfExpression);
        applyExp4.setFlag(4);
        return (Expression)new ApplyExp((Expression)applyExp4, new Expression[] { (Expression)referenceExp });
      case 10:
        str3 = "Preceding";
        quoteExp3 = QuoteExp.getInstance(new PrimProcedure("gnu.kawa.xml." + str3 + "Axis", "make", 1));
        applyExp3 = new ApplyExp((Expression)quoteExp3, arrayOfExpression);
        applyExp3.setFlag(4);
        return (Expression)new ApplyExp((Expression)applyExp3, new Expression[] { (Expression)referenceExp });
      case 11:
        str2 = "PrecedingSibling";
        quoteExp2 = QuoteExp.getInstance(new PrimProcedure("gnu.kawa.xml." + str2 + "Axis", "make", 1));
        applyExp2 = new ApplyExp((Expression)quoteExp2, arrayOfExpression);
        applyExp2.setFlag(4);
        return (Expression)new ApplyExp((Expression)applyExp2, new Expression[] { (Expression)referenceExp });
      case 2:
        break;
    } 
    String str1 = "Attribute";
    QuoteExp quoteExp1 = QuoteExp.getInstance(new PrimProcedure("gnu.kawa.xml." + str1 + "Axis", "make", 1));
    ApplyExp applyExp1 = new ApplyExp((Expression)quoteExp1, arrayOfExpression);
    applyExp1.setFlag(4);
    return (Expression)new ApplyExp((Expression)applyExp1, new Expression[] { (Expression)referenceExp });
  }
  
  public Expression parseOptionalTypeDeclaration() throws IOException, SyntaxException {
    if (!match("as"))
      return null; 
    getRawToken();
    return parseDataType();
  }
  
  Expression parseParenExpr() throws IOException, SyntaxException {
    getRawToken();
    char c = pushNesting('(');
    Expression expression = parseExprSequence(41, true);
    popNesting(c);
    if (this.curToken == -1)
      eofError("missing ')' - unexpected end-of-file"); 
    return expression;
  }
  
  Expression parsePathExpr() throws IOException, SyntaxException {
    Expression expression;
    boolean bool = true;
    if (this.curToken == 47 || this.curToken == 68) {
      Expression expression1;
      ReferenceExp referenceExp;
      Declaration declaration = this.comp.lexical.lookup(DOT_VARNAME, false);
      if (declaration == null) {
        expression1 = syntaxError("context item is undefined", "XPDY0002");
      } else {
        referenceExp = new ReferenceExp(DOT_VARNAME, (Declaration)expression1);
      } 
      ApplyExp applyExp = new ApplyExp(ClassType.make("gnu.xquery.util.NodeUtils").getDeclaredMethod("rootDocument", 1), new Expression[] { (Expression)referenceExp });
      if (this.nesting == 0)
        bool = false; 
      int i = skipSpace(bool);
      unread(i);
      if (i >= 0 && i != 41) {
        ApplyExp applyExp1 = applyExp;
        if (i == 125) {
          getRawToken();
          return (Expression)applyExp;
        } 
      } else {
        getRawToken();
        return (Expression)applyExp;
      } 
    } else {
      expression = parseStepExpr();
    } 
    return parseRelativePathExpr(expression);
  }
  
  Expression parsePrimaryExpr() throws IOException, SyntaxException {
    Expression expression = parseMaybePrimaryExpr();
    if (expression == null) {
      expression = syntaxError("missing expression");
      if (this.curToken != -1)
        getRawToken(); 
      return expression;
    } 
    return expression;
  }
  
  public Expression parseQuantifiedExpr(boolean paramBoolean) throws IOException, SyntaxException {
    Expression expression1;
    if (paramBoolean) {
      c = 'e';
    } else {
      c = 's';
    } 
    char c = pushNesting(c);
    this.curToken = 36;
    Declaration declaration = parseVariableDeclaration();
    if (declaration == null)
      return syntaxError("missing Variable token:" + this.curToken); 
    getRawToken();
    LambdaExp lambdaExp = new LambdaExp(1);
    lambdaExp.addDeclaration(declaration);
    declaration.noteValue(null);
    declaration.setFlag(262144L);
    declaration.setTypeExp(parseOptionalTypeDeclaration());
    if (match("in")) {
      getRawToken();
    } else {
      if (this.curToken == 76)
        getRawToken(); 
      syntaxError("missing 'in' in QuantifiedExpr");
    } 
    Expression expression2 = parseExprSingle();
    popNesting(c);
    this.comp.push((ScopeExp)lambdaExp);
    if (this.curToken == 44) {
      getRawToken();
      if (this.curToken != 36)
        return syntaxError("missing $NAME after ','"); 
      expression1 = parseQuantifiedExpr(paramBoolean);
    } else {
      boolean bool = match("satisfies");
      if (!bool && !match("every") && !match("some"))
        return syntaxError("missing 'satisfies' clause"); 
      peekNonSpace("unexpected eof-of-file after 'satisfies'");
      int i = getLineNumber();
      int j = getColumnNumber();
      if (bool)
        getRawToken(); 
      expression1 = parseExprSingle();
      maybeSetLine(expression1, i + 1, j + 1);
    } 
    this.comp.pop((ScopeExp)lambdaExp);
    lambdaExp.body = expression1;
    (new Expression[1])[0] = expression2;
    expression2 = (new Expression[1])[0];
    if (paramBoolean) {
      String str1 = "every";
      return (Expression)new ApplyExp(makeFunctionExp("gnu.xquery.util.ValuesEvery", str1), new Expression[] { (Expression)lambdaExp, expression2 });
    } 
    String str = "some";
    return (Expression)new ApplyExp(makeFunctionExp("gnu.xquery.util.ValuesEvery", str), new Expression[] { (Expression)lambdaExp, expression2 });
  }
  
  Expression parseRelativePathExpr(Expression paramExpression) throws IOException, SyntaxException {
    ReferenceExp referenceExp = null;
    while (true) {
      ApplyExp applyExp;
      if (this.curToken == 47 || this.curToken == 68) {
        boolean bool;
        Expression expression;
        if (this.curToken == 68) {
          bool = true;
        } else {
          bool = false;
        } 
        LambdaExp lambdaExp = new LambdaExp(3);
        Declaration declaration = lambdaExp.addDeclaration(DOT_VARNAME);
        declaration.setFlag(262144L);
        declaration.setType((Type)NodeType.anyNodeTest);
        declaration.noteValue(null);
        lambdaExp.addDeclaration(POSITION_VARNAME, (Type)LangPrimType.intType);
        lambdaExp.addDeclaration(LAST_VARNAME, (Type)LangPrimType.intType);
        this.comp.push((ScopeExp)lambdaExp);
        if (bool) {
          this.curToken = 47;
          referenceExp = new ReferenceExp(DOT_VARNAME, declaration);
          lambdaExp.body = (Expression)new ApplyExp((Procedure)DescendantOrSelfAxis.anyNode, new Expression[] { (Expression)referenceExp });
          expression = paramExpression;
        } else {
          getRawToken();
          Expression expression2 = parseStepExpr();
          Expression expression1 = paramExpression;
          if (expression != null) {
            expression1 = paramExpression;
            if (expression2 instanceof ApplyExp) {
              Expression expression3 = ((ApplyExp)expression2).getFunction();
              expression1 = paramExpression;
              if (expression3 instanceof ApplyExp) {
                ApplyExp applyExp1 = (ApplyExp)expression3;
                expression1 = paramExpression;
                if (applyExp1.getFunction() == makeChildAxisStep) {
                  applyExp1.setFunction((Expression)makeDescendantAxisStep);
                  expression1 = expression;
                } 
              } 
            } 
          } 
          lambdaExp.body = expression2;
          expression = null;
          paramExpression = expression1;
        } 
        this.comp.pop((ScopeExp)lambdaExp);
        applyExp = new ApplyExp((Procedure)RelativeStep.relativeStep, new Expression[] { paramExpression, (Expression)lambdaExp });
        continue;
      } 
      return (Expression)applyExp;
    } 
  }
  
  void parseSeparator() throws IOException, SyntaxException {
    boolean bool;
    int i = this.port.getLineNumber();
    int j = this.port.getColumnNumber();
    if (this.nesting != 0) {
      bool = true;
    } else {
      bool = false;
    } 
    int k = skipSpace(bool);
    if (k != 59) {
      if (warnOldVersion && k != 10) {
        this.curLine = i + 1;
        this.curColumn = j + 1;
        error('w', "missing ';' after declaration");
      } 
      if (k >= 0) {
        unread(k);
        return;
      } 
    } 
  }
  
  Expression parseStepExpr() throws IOException, SyntaxException {
    if (this.curToken == 46 || this.curToken == 51) {
      byte b;
      Expression expression1;
      ReferenceExp referenceExp1;
      ApplyExp applyExp;
      if (this.curToken == 46) {
        b = 12;
      } else {
        b = 9;
      } 
      getRawToken();
      Declaration declaration = this.comp.lexical.lookup(DOT_VARNAME, false);
      if (declaration == null) {
        expression1 = syntaxError("context item is undefined", "XPDY0002");
      } else {
        referenceExp1 = new ReferenceExp(DOT_VARNAME, (Declaration)expression1);
      } 
      ReferenceExp referenceExp2 = referenceExp1;
      if (b == 9)
        applyExp = new ApplyExp((Procedure)ParentAxis.make((NodePredicate)NodeType.anyNodeTest), new Expression[] { (Expression)referenceExp1 }); 
      if (b == 12)
        b = -1; 
      return parseStepQualifiers((Expression)applyExp, b);
    } 
    int i = peekOperand() - 100;
    if (i >= 0 && i < 13) {
      getRawToken();
      Expression expression1 = parseNodeTest(i);
      return parseStepQualifiers(expression1, i);
    } 
    if (this.curToken == 64) {
      getRawToken();
      i = 2;
      Expression expression1 = parseNodeTest(2);
      return parseStepQualifiers(expression1, i);
    } 
    if (this.curToken == 236) {
      i = 2;
      Expression expression1 = parseNodeTest(2);
      return parseStepQualifiers(expression1, i);
    } 
    Expression expression = parseNodeTest(-1);
    if (expression != null) {
      i = 3;
      return parseStepQualifiers(expression, i);
    } 
    i = -1;
    expression = parsePrimaryExpr();
    return parseStepQualifiers(expression, i);
  }
  
  Expression parseStepQualifiers(Expression paramExpression, int paramInt) throws IOException, SyntaxException {
    ApplyExp applyExp;
    Expression expression = paramExpression;
    while (this.curToken == 91) {
      ValuesFilter valuesFilter;
      int i = getLineNumber() + 1;
      int j = getColumnNumber() + 1;
      int k = this.seenPosition;
      k = this.seenLast;
      getRawToken();
      LambdaExp lambdaExp = new LambdaExp(3);
      maybeSetLine((Expression)lambdaExp, i, j);
      Declaration declaration = lambdaExp.addDeclaration(DOT_VARNAME);
      if (paramInt >= 0) {
        declaration.setType((Type)NodeType.anyNodeTest);
      } else {
        declaration.setType((Type)SingletonType.getInstance());
      } 
      lambdaExp.addDeclaration(POSITION_VARNAME, (Type)Type.intType);
      lambdaExp.addDeclaration(LAST_VARNAME, (Type)Type.intType);
      this.comp.push((ScopeExp)lambdaExp);
      declaration.noteValue(null);
      Expression expression1 = parseExprSequence(93, false);
      if (this.curToken == -1)
        eofError("missing ']' - unexpected end-of-file"); 
      if (paramInt < 0) {
        valuesFilter = ValuesFilter.exprFilter;
      } else if (paramInt == 0 || paramInt == 1 || paramInt == 9 || paramInt == 10 || paramInt == 11) {
        valuesFilter = ValuesFilter.reverseFilter;
      } else {
        valuesFilter = ValuesFilter.forwardFilter;
      } 
      maybeSetLine(expression1, i, j);
      this.comp.pop((ScopeExp)lambdaExp);
      lambdaExp.body = expression1;
      getRawToken();
      applyExp = new ApplyExp((Procedure)valuesFilter, new Expression[] { expression, (Expression)lambdaExp });
    } 
    return (Expression)applyExp;
  }
  
  Expression parseTypeSwitch() throws IOException, SyntaxException {
    byte b = 101;
    char c = pushNesting('t');
    Expression expression = parseParenExpr();
    getRawToken();
    Vector<Expression> vector = new Vector();
    vector.addElement(expression);
    while (match("case")) {
      Declaration declaration;
      pushNesting('c');
      getRawToken();
      if (this.curToken == 36) {
        declaration = parseVariableDeclaration();
        if (declaration == null)
          return syntaxError("missing Variable after '$'"); 
        getRawToken();
        if (match("as")) {
          getRawToken();
        } else {
          error('e', "missing 'as'");
        } 
      } else {
        declaration = new Declaration("(arg)");
      } 
      declaration.setTypeExp(parseDataType());
      popNesting('t');
      LambdaExp lambdaExp = new LambdaExp(1);
      lambdaExp.addDeclaration(declaration);
      if (match("return")) {
        getRawToken();
      } else {
        error("missing 'return' after 'case'");
      } 
      this.comp.push((ScopeExp)lambdaExp);
      pushNesting('r');
      lambdaExp.body = parseExpr();
      popNesting('t');
      this.comp.pop((ScopeExp)lambdaExp);
      vector.addElement(lambdaExp);
    } 
    if (match("default")) {
      Declaration declaration;
      LambdaExp lambdaExp = new LambdaExp(1);
      getRawToken();
      if (this.curToken == 36) {
        declaration = parseVariableDeclaration();
        if (declaration == null)
          return syntaxError("missing Variable after '$'"); 
        getRawToken();
      } else {
        declaration = new Declaration("(arg)");
      } 
      lambdaExp.addDeclaration(declaration);
      if (match("return")) {
        getRawToken();
      } else {
        error("missing 'return' after 'default'");
      } 
      this.comp.push((ScopeExp)lambdaExp);
      lambdaExp.body = parseExpr();
      this.comp.pop((ScopeExp)lambdaExp);
      vector.addElement(lambdaExp);
      popNesting(c);
      Expression[] arrayOfExpression1 = new Expression[vector.size()];
      vector.copyInto((Object[])arrayOfExpression1);
      return (Expression)new ApplyExp(makeFunctionExp("gnu.kawa.reflect.TypeSwitch", "typeSwitch"), arrayOfExpression1);
    } 
    if (!this.comp.isPedantic())
      b = 119; 
    error(b, "no 'default' clause in 'typeswitch'", "XPST0003");
    popNesting(c);
    Expression[] arrayOfExpression = new Expression[vector.size()];
    vector.copyInto((Object[])arrayOfExpression);
    return (Expression)new ApplyExp(makeFunctionExp("gnu.kawa.reflect.TypeSwitch", "typeSwitch"), arrayOfExpression);
  }
  
  Object parseURILiteral() throws IOException, SyntaxException {
    getRawToken();
    return (this.curToken != 34) ? declError("expected a URILiteral") : TextUtils.replaceWhitespace(new String(this.tokenBuffer, 0, this.tokenBufferLength), true);
  }
  
  Expression parseUnaryExpr() throws IOException, SyntaxException {
    if (this.curToken == 414 || this.curToken == 413) {
      String str1;
      int i = this.curToken;
      getRawToken();
      Expression expression = parseUnaryExpr();
      if (i == 413) {
        str1 = "plus";
      } else {
        str1 = "minus";
      } 
      if (i == 413) {
        String str = "+";
        return (Expression)new ApplyExp(makeFunctionExp("gnu.xquery.util.ArithOp", str1, str), new Expression[] { expression });
      } 
      String str2 = "-";
      return (Expression)new ApplyExp(makeFunctionExp("gnu.xquery.util.ArithOp", str1, str2), new Expression[] { expression });
    } 
    return parseUnionExpr();
  }
  
  Expression parseUnionExpr() throws IOException, SyntaxException {
    for (Expression expression = parseIntersectExceptExpr();; expression = makeBinary(i, expression, parseIntersectExceptExpr())) {
      int i = peekOperator();
      if (i != 419)
        return expression; 
      getRawToken();
    } 
  }
  
  public Object parseVariable() throws IOException, SyntaxException {
    if (this.curToken == 36) {
      getRawToken();
    } else {
      syntaxError("missing '$' before variable name");
    } 
    String str = new String(this.tokenBuffer, 0, this.tokenBufferLength);
    return (this.curToken == 81) ? str : ((this.curToken == 65) ? Namespace.EmptyNamespace.getSymbol(str.intern()) : null);
  }
  
  public Declaration parseVariableDeclaration() throws IOException, SyntaxException {
    Object object = parseVariable();
    if (object == null)
      return null; 
    object = new Declaration(object);
    maybeSetLine((Declaration)object, getLineNumber() + 1, getColumnNumber() + 1 - this.tokenBufferLength);
    return (Declaration)object;
  }
  
  Expression parseXMLConstructor(int paramInt, boolean paramBoolean) throws IOException, SyntaxException {
    if (paramInt == 33) {
      paramInt = read();
      if (paramInt == 45 && peek() == 45) {
        skip();
        getDelimited("-->");
        boolean bool = false;
        paramInt = this.tokenBufferLength;
        int i = 1;
        while (true) {
          int j = paramInt - 1;
          paramInt = bool;
          if (j >= 0) {
            if (this.tokenBuffer[j] == '-') {
              paramInt = 1;
            } else {
              paramInt = 0;
            } 
            if (i && paramInt != 0) {
              paramInt = 1;
            } else {
              i = paramInt;
              paramInt = j;
              continue;
            } 
          } 
          if (paramInt != 0)
            return syntaxError("consecutive or final hyphen in XML comment"); 
          QuoteExp quoteExp = new QuoteExp(new String(this.tokenBuffer, 0, this.tokenBufferLength));
          return (Expression)new ApplyExp(makeFunctionExp("gnu.kawa.xml.CommentConstructor", "commentConstructor"), new Expression[] { (Expression)quoteExp });
        } 
      } 
      if (paramInt == 91 && read() == 67 && read() == 68 && read() == 65 && read() == 84 && read() == 65 && read() == 91) {
        if (!paramBoolean)
          error('e', "CDATA section must be in element content"); 
        getDelimited("]]>");
        QuoteExp quoteExp = new QuoteExp(new String(this.tokenBuffer, 0, this.tokenBufferLength));
        return (Expression)new ApplyExp(makeCDATA, new Expression[] { (Expression)quoteExp });
      } 
      return syntaxError("'<!' must be followed by '--' or '[CDATA['");
    } 
    if (paramInt == 63) {
      paramInt = peek();
      if (paramInt < 0 || !XName.isNameStart((char)paramInt) || getRawToken() != 65)
        syntaxError("missing target after '<?'"); 
      String str = new String(this.tokenBuffer, 0, this.tokenBufferLength);
      paramInt = 0;
      while (true) {
        int i = read();
        if (i >= 0)
          if (!Character.isWhitespace((char)i)) {
            unread();
          } else {
            paramInt++;
            continue;
          }  
        getDelimited("?>");
        if (paramInt == 0 && this.tokenBufferLength > 0)
          syntaxError("target must be followed by space or '?>'"); 
        String str1 = new String(this.tokenBuffer, 0, this.tokenBufferLength);
        QuoteExp quoteExp2 = new QuoteExp(str);
        QuoteExp quoteExp1 = new QuoteExp(str1);
        return (Expression)new ApplyExp(makeFunctionExp("gnu.kawa.xml.MakeProcInst", "makeProcInst"), new Expression[] { (Expression)quoteExp2, (Expression)quoteExp1 });
      } 
    } 
    if (paramInt < 0 || !XName.isNameStart((char)paramInt))
      return syntaxError("expected QName after '<'"); 
    unread(paramInt);
    getRawToken();
    char c = pushNesting('<');
    Expression expression2 = parseElementConstructor();
    Expression expression1 = expression2;
    if (!paramBoolean)
      expression1 = wrapWithBaseUri(expression2); 
    popNesting(c);
    return expression1;
  }
  
  final int peekNonSpace(String paramString) throws IOException, SyntaxException {
    int i = skipSpace();
    if (i < 0)
      eofError(paramString); 
    unread(i);
    return i;
  }
  
  int peekOperand() throws IOException, SyntaxException {
    int i;
    while (this.curToken == 10)
      getRawToken(); 
    if (this.curToken == 65 || this.curToken == 81) {
      boolean bool;
      if (this.nesting != 0) {
        bool = true;
      } else {
        bool = false;
      } 
      i = skipSpace(bool);
      switch (this.tokenBuffer[0]) {
        default:
          if (i == 40 && peek() != 58) {
            this.curToken = 70;
            return 70;
          } 
          break;
        case 'a':
          if (match("attribute")) {
            if (i == 40) {
              this.curToken = 236;
              return 236;
            } 
            if (i == 123 || XName.isNameStart((char)i)) {
              unread();
              this.curToken = 252;
              return 252;
            } 
          } 
        case 'c':
          if (match("comment")) {
            if (i == 40) {
              this.curToken = 232;
              return 232;
            } 
            if (i == 123) {
              unread();
              this.curToken = 254;
              return 254;
            } 
          } 
        case 'd':
          if (i == 123 && match("document")) {
            unread();
            this.curToken = 256;
            return 256;
          } 
          if (i == 40 && match("document-node")) {
            this.curToken = 234;
            return 234;
          } 
        case 'e':
          if (match("element")) {
            if (i == 40) {
              this.curToken = 235;
              return 235;
            } 
            if (i == 123 || XName.isNameStart((char)i)) {
              unread();
              this.curToken = 251;
              return 251;
            } 
          } else {
            if (i == 40 && match("empty-sequence")) {
              this.curToken = 238;
              return 238;
            } 
            if (i == 36 && match("every")) {
              this.curToken = 246;
              return 246;
            } 
          } 
        case 'f':
          if (i == 36 && match("for")) {
            this.curToken = 243;
            return 243;
          } 
        case 'i':
          if (i == 40 && match("if")) {
            this.curToken = 241;
            return 241;
          } 
          if (i == 40 && match("item")) {
            this.curToken = 237;
            return 237;
          } 
        case 'l':
          if (i == 36 && match("let")) {
            this.curToken = 244;
            return 244;
          } 
        case 'n':
          if (i == 40 && match("node")) {
            this.curToken = 230;
            return 230;
          } 
        case 'o':
          if (i == 123 && match("ordered")) {
            this.curToken = 249;
            return 249;
          } 
        case 'p':
          if (match("processing-instruction")) {
            if (i == 40) {
              this.curToken = 233;
              return 233;
            } 
            if (i == 123 || XName.isNameStart((char)i)) {
              unread();
              this.curToken = 255;
              return 255;
            } 
          } 
        case 's':
          if (i == 36 && match("some")) {
            this.curToken = 245;
            return 245;
          } 
          if (i == 40 && match("schema-attribute")) {
            this.curToken = 239;
            return 239;
          } 
          if (i == 40 && match("schema-element")) {
            this.curToken = 240;
            return 240;
          } 
        case 't':
          if (match("text")) {
            if (i == 40) {
              this.curToken = 231;
              return 231;
            } 
            if (i == 123) {
              unread();
              this.curToken = 253;
              return 253;
            } 
          } 
          if (i == 40 && match("typeswitch")) {
            this.curToken = 242;
            return 242;
          } 
        case 'u':
          if (i == 123 && match("unordered")) {
            this.curToken = 250;
            return 250;
          } 
        case 'v':
          if (i == 123 && match("validate")) {
            this.curToken = 248;
            return 248;
          } 
      } 
      if (i == 58 && peek() == 58) {
        i = getAxis();
        this.curToken = i;
        return i;
      } 
      this.curValue = new String(this.tokenBuffer, 0, this.tokenBufferLength);
      switch (i) {
        default:
          if (i >= 0) {
            unread();
            if (XName.isNameStart((char)i) && this.curValue.equals("define")) {
              getRawToken();
              this.curToken = 87;
            } 
          } 
          return this.curToken;
        case 98:
          if (lookingAt("declare", "ase-uri")) {
            this.curToken = 66;
            return 66;
          } 
          if (lookingAt("declare", "oundary-space")) {
            this.curToken = 83;
            return 83;
          } 
        case 99:
          if (lookingAt("declare", "onstruction")) {
            this.curToken = 75;
            return 75;
          } 
          if (lookingAt("declare", "opy-namespaces")) {
            this.curToken = 76;
            return 76;
          } 
        case 100:
          if (lookingAt("declare", "efault")) {
            getRawToken();
            if (match("function")) {
              this.curToken = 79;
              return 79;
            } 
            if (match("element")) {
              this.curToken = 69;
              return 69;
            } 
            if (match("collation")) {
              this.curToken = 71;
              return 71;
            } 
            if (match("order")) {
              this.curToken = 72;
              return 72;
            } 
            error("unrecognized/unimplemented 'declare default'");
            skipToSemicolon();
            return peekOperand();
          } 
        case 101:
          if (lookingAt("default", "lement")) {
            warnOldVersion("replace 'default element' by 'declare default element namespace'");
            this.curToken = 69;
            return 69;
          } 
        case 102:
          if (lookingAt("declare", "unction")) {
            this.curToken = 80;
            return 80;
          } 
          if (lookingAt("define", "unction")) {
            warnOldVersion("replace 'define function' by 'declare function'");
            this.curToken = 80;
            return 80;
          } 
          if (lookingAt("default", "unction")) {
            warnOldVersion("replace 'default function' by 'declare default function namespace'");
            this.curToken = 79;
            return 79;
          } 
        case 109:
          if (lookingAt("import", "odule")) {
            this.curToken = 73;
            return 73;
          } 
        case 110:
          if (lookingAt("declare", "amespace")) {
            this.curToken = 78;
            return 78;
          } 
          if (lookingAt("default", "amespace")) {
            warnOldVersion("replace 'default namespace' by 'declare default element namespace'");
            this.curToken = 69;
            return 69;
          } 
          if (lookingAt("module", "amespace")) {
            this.curToken = 77;
            return 77;
          } 
        case 111:
          if (lookingAt("declare", "rdering")) {
            this.curToken = 85;
            return 85;
          } 
          if (lookingAt("declare", "ption")) {
            this.curToken = 111;
            return 111;
          } 
        case 115:
          if (lookingAt("import", "chema")) {
            this.curToken = 84;
            return 84;
          } 
        case 118:
          if (lookingAt("declare", "ariable")) {
            this.curToken = 86;
            return 86;
          } 
          if (lookingAt("define", "ariable")) {
            warnOldVersion("replace 'define variable' by 'declare variable'");
            this.curToken = 86;
            return 86;
          } 
          if (lookingAt("xquery", "ersion")) {
            this.curToken = 89;
            return 89;
          } 
        case 120:
          break;
      } 
      if (lookingAt("declare", "mlspace")) {
        warnOldVersion("replace 'define xmlspace' by 'declare boundary-space'");
        this.curToken = 83;
        return 83;
      } 
    } 
    if (this.curToken == 67) {
      i = read();
      if (i == 58) {
        this.curToken = getAxis();
        return this.curToken;
      } 
    } else {
      return this.curToken;
    } 
    unread(i);
    return this.curToken;
  }
  
  int peekOperator() throws IOException, SyntaxException {
    while (this.curToken == 10) {
      if (this.nesting == 0)
        return 10; 
      getRawToken();
    } 
    if (this.curToken == 65) {
      char c1;
      char c2;
      char c3;
      switch (this.tokenBufferLength) {
        default:
          return this.curToken;
        case 2:
          c1 = this.tokenBuffer[0];
          c2 = this.tokenBuffer[1];
          if (c1 == 'o' && c2 == 'r')
            this.curToken = 400; 
          if (c1 == 't' && c2 == 'o')
            this.curToken = 412; 
          if (c1 == 'i' && c2 == 's')
            this.curToken = 408; 
          if (c1 == 'e' && c2 == 'q')
            this.curToken = 426; 
          if (c1 == 'n' && c2 == 'e')
            this.curToken = 427; 
          if (c1 == 'g') {
            if (c2 == 'e')
              this.curToken = 431; 
            if (c2 == 't')
              this.curToken = 430; 
          } 
          if (c1 == 'l') {
            if (c2 == 'e')
              this.curToken = 429; 
            if (c2 == 't')
              this.curToken = 428; 
          } 
        case 3:
          c1 = this.tokenBuffer[0];
          c2 = this.tokenBuffer[1];
          c3 = this.tokenBuffer[2];
          if (c1 == 'a')
            if (c2 == 'n' && c3 == 'd')
              this.curToken = 401;  
          if (c1 == 'm') {
            if (c2 == 'u' && c3 == 'l')
              this.curToken = 415; 
            if (c2 == 'o' && c3 == 'd')
              this.curToken = 418; 
          } 
          if (c1 == 'd' && c2 == 'i' && c3 == 'v')
            this.curToken = 416; 
        case 4:
          if (match("idiv"))
            this.curToken = 417; 
          if (match("cast", "as", true))
            this.curToken = 425; 
        case 5:
          if (match("where"))
            this.curToken = 196; 
          if (match("isnot"))
            this.curToken = 409; 
          if (match("union"))
            this.curToken = 419; 
          if (match("treat", "as", true))
            this.curToken = 423; 
        case 6:
          if (match("except"))
            this.curToken = 421; 
        case 8:
          if (match("instance", "of", true))
            this.curToken = 422; 
          if (match("castable", "as", true))
            this.curToken = 424; 
        case 9:
          if (match("intersect"))
            this.curToken = 420; 
        case 10:
          break;
      } 
      if (match("instanceof")) {
        warnOldVersion("use 'instanceof of' (two words) instead of 'instanceof'");
        this.curToken = 422;
      } 
    } 
  }
  
  void pushNamespace(String paramString1, String paramString2) {
    String str = paramString2;
    if (paramString2.length() == 0)
      str = null; 
    this.prologNamespaces = new NamespaceBinding(paramString1, str, this.prologNamespaces);
  }
  
  public Object readObject() throws IOException, SyntaxException {
    return parse((Compilation)null);
  }
  
  public void reset() throws IOException {
    this.curToken = this.saveToken;
    this.curValue = this.saveValue;
    super.reset();
  }
  
  public String resolveAgainstBaseUri(String paramString) {
    return Path.uriSchemeSpecified(paramString) ? paramString : Path.valueOf(getStaticBaseUri()).resolve(paramString).toString();
  }
  
  public void setInteractive(boolean paramBoolean) {
    if (this.interactive != paramBoolean)
      if (paramBoolean) {
        this.nesting--;
      } else {
        this.nesting++;
      }  
    this.interactive = paramBoolean;
  }
  
  public void setStaticBaseUri(String paramString) {
    try {
      this.baseURI = fixupStaticBaseUri((Path)URIPath.valueOf(paramString));
      return;
    } catch (Throwable throwable2) {
      Throwable throwable1 = throwable2;
      if (throwable2 instanceof WrappedException)
        throwable1 = ((WrappedException)throwable2).getCause(); 
      error('e', "invalid URI: " + throwable1.getMessage());
      return;
    } 
  }
  
  final void skipComment() throws IOException, SyntaxException {
    this.commentCount++;
    int j = getLineNumber() + 1;
    int k = getColumnNumber() - 1;
    if (this.errorIfComment != null) {
      this.curLine = j;
      this.curColumn = k;
      error('e', this.errorIfComment);
    } 
    int i = 0;
    byte b = 0;
    char c = pushNesting(':');
    while (true) {
      byte b1;
      int m;
      int n = read();
      if (n == 58) {
        m = n;
        b1 = b;
        if (i == 40) {
          b1 = b + 1;
          m = 0;
        } 
      } else if (n == 41 && i == 58) {
        if (b == 0) {
          popNesting(c);
          return;
        } 
        b1 = b - 1;
        m = n;
      } else {
        m = n;
        b1 = b;
        if (n < 0) {
          this.curLine = j;
          this.curColumn = k;
          eofError("non-terminated comment starting here");
          m = n;
          b1 = b;
        } 
      } 
      b = b1;
      i = m;
    } 
  }
  
  final void skipOldComment() throws IOException, SyntaxException {
    int i = 0;
    int j = getLineNumber();
    int k = getColumnNumber();
    warnOldVersion("use (: :) instead of old-style comment {-- --}");
    while (true) {
      int m = read();
      if (m == 45) {
        i++;
        continue;
      } 
      if (m == 125 && i >= 2)
        return; 
      if (m < 0) {
        this.curLine = j + 1;
        this.curColumn = k - 2;
        eofError("non-terminated comment starting here");
        continue;
      } 
      i = 0;
    } 
  }
  
  final int skipSpace() throws IOException, SyntaxException {
    return skipSpace(true);
  }
  
  final int skipSpace(boolean paramBoolean) throws IOException, SyntaxException {
    while (true) {
      int i = read();
      if (i == 40) {
        if (!checkNext(':'))
          return 40; 
        skipComment();
        continue;
      } 
      if (i == 123) {
        i = read();
        if (i != 45) {
          unread(i);
          return 123;
        } 
        i = read();
        if (i != 45) {
          unread(i);
          unread(45);
          return 123;
        } 
        skipOldComment();
        continue;
      } 
      if (paramBoolean ? (i < 0 || !Character.isWhitespace((char)i)) : (i != 32 && i != 9))
        return i; 
    } 
  }
  
  final void skipToSemicolon() throws IOException {
    int i;
    do {
      i = read();
    } while (i >= 0 && i != 59);
  }
  
  public Expression syntaxError(String paramString) throws IOException, SyntaxException {
    return syntaxError(paramString, "XPST0003");
  }
  
  public Expression syntaxError(String paramString1, String paramString2) throws IOException, SyntaxException {
    // Byte code:
    //   0: aload_0
    //   1: bipush #101
    //   3: aload_1
    //   4: aload_2
    //   5: invokevirtual error : (CLjava/lang/String;Ljava/lang/String;)V
    //   8: aload_0
    //   9: getfield interactive : Z
    //   12: ifeq -> 83
    //   15: aload_0
    //   16: iconst_0
    //   17: putfield curToken : I
    //   20: aload_0
    //   21: aconst_null
    //   22: putfield curValue : Ljava/lang/Object;
    //   25: aload_0
    //   26: iconst_0
    //   27: putfield nesting : I
    //   30: aload_0
    //   31: invokevirtual getPort : ()Lgnu/text/LineBufferedReader;
    //   34: checkcast gnu/mapping/InPort
    //   37: bipush #10
    //   39: putfield readState : C
    //   42: aload_0
    //   43: invokevirtual read : ()I
    //   46: istore_3
    //   47: iload_3
    //   48: ifge -> 63
    //   51: new gnu/text/SyntaxException
    //   54: dup
    //   55: aload_0
    //   56: invokevirtual getMessages : ()Lgnu/text/SourceMessages;
    //   59: invokespecial <init> : (Lgnu/text/SourceMessages;)V
    //   62: athrow
    //   63: iload_3
    //   64: bipush #13
    //   66: if_icmpeq -> 75
    //   69: iload_3
    //   70: bipush #10
    //   72: if_icmpne -> 42
    //   75: aload_0
    //   76: iload_3
    //   77: invokevirtual unread : (I)V
    //   80: goto -> 51
    //   83: new gnu/expr/ErrorExp
    //   86: dup
    //   87: aload_1
    //   88: invokespecial <init> : (Ljava/lang/String;)V
    //   91: areturn
  }
  
  String tokenString() {
    int i;
    StringBuffer stringBuffer;
    switch (this.curToken) {
      default:
        if (this.curToken >= 100 && this.curToken - 100 < 13)
          return axisNames[this.curToken - 100] + "::-axis(" + this.curToken + ")"; 
        break;
      case 34:
        stringBuffer = new StringBuffer();
        stringBuffer.append('"');
        for (i = 0; i < this.tokenBufferLength; i++) {
          char c = this.tokenBuffer[i];
          if (c == '"')
            stringBuffer.append('"'); 
          stringBuffer.append(c);
        } 
        stringBuffer.append('"');
        return stringBuffer.toString();
      case 70:
        return new String(this.tokenBuffer, 0, this.tokenBufferLength) + " + '('";
      case 65:
      case 81:
        return new String(this.tokenBuffer, 0, this.tokenBufferLength);
      case -1:
        return "<EOF>";
    } 
    return (this.curToken >= 32 && this.curToken < 127) ? (Integer.toString(this.curToken) + "='" + (char)this.curToken + "'") : Integer.toString(this.curToken);
  }
  
  void warnOldVersion(String paramString) {
    if (warnOldVersion || this.comp.isPedantic()) {
      byte b;
      if (this.comp.isPedantic()) {
        b = 101;
      } else {
        b = 119;
      } 
      error(b, paramString);
    } 
  }
  
  Expression wrapWithBaseUri(Expression paramExpression) {
    return (getStaticBaseUri() == null) ? paramExpression : (new ApplyExp((Procedure)MakeWithBaseUri.makeWithBaseUri, new Expression[] { (Expression)new ApplyExp((Expression)new ReferenceExp(XQResolveNames.staticBaseUriDecl), Expression.noExpressions), paramExpression })).setLine(paramExpression);
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/gnu/xquery/lang/XQParser.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */