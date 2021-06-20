package gnu.xml;

import gnu.expr.Keyword;
import gnu.kawa.xml.XmlNamespace;
import gnu.lists.AbstractSequence;
import gnu.lists.Consumable;
import gnu.lists.Consumer;
import gnu.lists.PositionConsumer;
import gnu.lists.SeqPosition;
import gnu.lists.UnescapedData;
import gnu.lists.XConsumer;
import gnu.mapping.OutPort;
import gnu.mapping.ThreadLocation;
import gnu.math.RealNum;
import gnu.text.Char;
import gnu.text.Path;
import gnu.text.PrettyWriter;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.math.BigDecimal;

public class XMLPrinter extends OutPort implements PositionConsumer, XConsumer {
  private static final int COMMENT = -5;
  
  private static final int ELEMENT_END = -4;
  
  private static final int ELEMENT_START = -3;
  
  static final String HtmlEmptyTags = "/area/base/basefont/br/col/frame/hr/img/input/isindex/link/meta/para/";
  
  private static final int KEYWORD = -6;
  
  private static final int PROC_INST = -7;
  
  private static final int WORD = -2;
  
  public static final ThreadLocation doctypePublic;
  
  public static final ThreadLocation doctypeSystem = new ThreadLocation("doctype-system");
  
  public static final ThreadLocation indentLoc;
  
  boolean canonicalize = true;
  
  public boolean canonicalizeCDATA;
  
  Object[] elementNameStack = new Object[20];
  
  int elementNesting;
  
  public boolean escapeNonAscii = true;
  
  public boolean escapeText = true;
  
  boolean inAttribute = false;
  
  int inComment;
  
  boolean inDocument;
  
  boolean inStartTag = false;
  
  public boolean indentAttributes;
  
  boolean isHtml = false;
  
  boolean isHtmlOrXhtml = false;
  
  NamespaceBinding namespaceBindings = NamespaceBinding.predefinedXML;
  
  NamespaceBinding[] namespaceSaveStack = new NamespaceBinding[20];
  
  boolean needXMLdecl = false;
  
  int prev = 32;
  
  public int printIndent = -1;
  
  boolean printXMLdecl = false;
  
  char savedHighSurrogate;
  
  public boolean strict;
  
  Object style;
  
  boolean undeclareNamespaces = false;
  
  public int useEmptyElementTag = 2;
  
  static {
    doctypePublic = new ThreadLocation("doctype-public");
    indentLoc = new ThreadLocation("xml-indent");
  }
  
  public XMLPrinter(OutPort paramOutPort, boolean paramBoolean) {
    super(paramOutPort, paramBoolean);
  }
  
  public XMLPrinter(OutputStream paramOutputStream) {
    super(new OutputStreamWriter(paramOutputStream), false, false);
  }
  
  public XMLPrinter(OutputStream paramOutputStream, Path paramPath) {
    super(new OutputStreamWriter(paramOutputStream), true, false, paramPath);
  }
  
  public XMLPrinter(OutputStream paramOutputStream, boolean paramBoolean) {
    super(new OutputStreamWriter(paramOutputStream), true, paramBoolean);
  }
  
  public XMLPrinter(Writer paramWriter) {
    super(paramWriter);
  }
  
  public XMLPrinter(Writer paramWriter, boolean paramBoolean) {
    super(paramWriter, paramBoolean);
  }
  
  static String formatDecimal(String paramString) {
    if (paramString.indexOf('.') >= 0) {
      int j = paramString.length();
      int i = j;
      while (true) {
        int k = i - 1;
        char c = paramString.charAt(k);
        i = k;
        if (c != '0') {
          i = k;
          if (c != '.')
            i = k + 1; 
          if (i == j)
            break; 
          return paramString.substring(0, i);
        } 
      } 
    } 
    return paramString;
  }
  
  public static String formatDecimal(BigDecimal paramBigDecimal) {
    return formatDecimal(paramBigDecimal.toPlainString());
  }
  
  public static String formatDouble(double paramDouble) {
    double d;
    boolean bool;
    if (Double.isNaN(paramDouble))
      return "NaN"; 
    if (paramDouble < 0.0D) {
      bool = true;
    } else {
      bool = false;
    } 
    if (Double.isInfinite(paramDouble))
      return bool ? "-INF" : "INF"; 
    if (bool) {
      d = -paramDouble;
    } else {
      d = paramDouble;
    } 
    String str = Double.toString(paramDouble);
    return ((d >= 1000000.0D || d < 1.0E-6D) && d != 0.0D) ? RealNum.toStringScientific(str) : formatDecimal(RealNum.toStringDecimal(str));
  }
  
  public static String formatFloat(float paramFloat) {
    float f;
    boolean bool;
    if (Float.isNaN(paramFloat))
      return "NaN"; 
    if (paramFloat < 0.0F) {
      bool = true;
    } else {
      bool = false;
    } 
    if (Float.isInfinite(paramFloat))
      return bool ? "-INF" : "INF"; 
    if (bool) {
      f = -paramFloat;
    } else {
      f = paramFloat;
    } 
    String str = Float.toString(paramFloat);
    return ((f >= 1000000.0F || f < 1.0E-6D) && f != 0.0D) ? RealNum.toStringScientific(str) : formatDecimal(RealNum.toStringDecimal(str));
  }
  
  public static boolean isHtmlEmptyElementTag(String paramString) {
    int i = "/area/base/basefont/br/col/frame/hr/img/input/isindex/link/meta/para/".indexOf(paramString);
    return (i > 0 && "/area/base/basefont/br/col/frame/hr/img/input/isindex/link/meta/para/".charAt(i - 1) == '/' && "/area/base/basefont/br/col/frame/hr/img/input/isindex/link/meta/para/".charAt(paramString.length() + i) == '/');
  }
  
  public static XMLPrinter make(OutPort paramOutPort, Object paramObject) {
    paramOutPort = new XMLPrinter(paramOutPort, true);
    paramOutPort.setStyle(paramObject);
    return (XMLPrinter)paramOutPort;
  }
  
  private void startWord() {
    closeTag();
    writeWordStart();
  }
  
  public static String toString(Object paramObject) {
    StringWriter stringWriter = new StringWriter();
    (new XMLPrinter(stringWriter)).writeObject(paramObject);
    return stringWriter.toString();
  }
  
  public void beginComment() {
    closeTag();
    if (this.printIndent >= 0 && (this.prev == -3 || this.prev == -4 || this.prev == -5)) {
      byte b;
      if (this.printIndent > 0) {
        b = 82;
      } else {
        b = 78;
      } 
      writeBreak(b);
    } 
    this.bout.write("<!--");
    this.inComment = 1;
  }
  
  public void beginEntity(Object paramObject) {}
  
  public void closeTag() {
    if (this.inStartTag && !this.inAttribute) {
      if (this.printIndent >= 0 && this.indentAttributes)
        endLogicalBlock(""); 
      this.bout.write(62);
      this.inStartTag = false;
      this.prev = -3;
      return;
    } 
    if (this.needXMLdecl) {
      this.bout.write("<?xml version=\"1.0\"?>\n");
      if (this.printIndent >= 0)
        startLogicalBlock("", "", 2); 
      this.needXMLdecl = false;
      this.prev = 62;
      return;
    } 
  }
  
  public void consume(SeqPosition paramSeqPosition) {
    paramSeqPosition.sequence.consumeNext(paramSeqPosition.ipos, (Consumer)this);
  }
  
  public void endAttribute() {
    if (this.inAttribute) {
      if (this.prev != -6) {
        this.bout.write(34);
        this.inAttribute = false;
      } 
      this.prev = 32;
    } 
  }
  
  public void endComment() {
    this.bout.write("-->");
    this.prev = -5;
    this.inComment = 0;
  }
  
  public void endDocument() {
    this.inDocument = false;
    if (this.printIndent >= 0)
      endLogicalBlock(""); 
    freshLine();
  }
  
  public void endElement() {
    // Byte code:
    //   0: aload_0
    //   1: getfield useEmptyElementTag : I
    //   4: ifne -> 11
    //   7: aload_0
    //   8: invokevirtual closeTag : ()V
    //   11: aload_0
    //   12: getfield elementNameStack : [Ljava/lang/Object;
    //   15: aload_0
    //   16: getfield elementNesting : I
    //   19: iconst_1
    //   20: isub
    //   21: aaload
    //   22: astore #5
    //   24: aload_0
    //   25: aload #5
    //   27: invokevirtual getHtmlTag : (Ljava/lang/Object;)Ljava/lang/String;
    //   30: astore #4
    //   32: aload_0
    //   33: getfield inStartTag : Z
    //   36: ifeq -> 390
    //   39: aload_0
    //   40: getfield printIndent : I
    //   43: iflt -> 60
    //   46: aload_0
    //   47: getfield indentAttributes : Z
    //   50: ifeq -> 60
    //   53: aload_0
    //   54: ldc_w ''
    //   57: invokevirtual endLogicalBlock : (Ljava/lang/String;)V
    //   60: aconst_null
    //   61: astore_3
    //   62: aload #4
    //   64: ifnull -> 317
    //   67: aload #4
    //   69: invokestatic isHtmlEmptyElementTag : (Ljava/lang/String;)Z
    //   72: ifeq -> 317
    //   75: iconst_1
    //   76: istore_1
    //   77: aload_0
    //   78: getfield useEmptyElementTag : I
    //   81: ifeq -> 97
    //   84: aload_3
    //   85: astore_2
    //   86: aload #4
    //   88: ifnull -> 179
    //   91: aload_3
    //   92: astore_2
    //   93: iload_1
    //   94: ifne -> 179
    //   97: aload_3
    //   98: astore_2
    //   99: aload #5
    //   101: instanceof gnu/mapping/Symbol
    //   104: ifeq -> 179
    //   107: aload #5
    //   109: checkcast gnu/mapping/Symbol
    //   112: astore #6
    //   114: aload #6
    //   116: invokevirtual getPrefix : ()Ljava/lang/String;
    //   119: astore_2
    //   120: aload #6
    //   122: invokevirtual getNamespaceURI : ()Ljava/lang/String;
    //   125: astore #5
    //   127: aload #6
    //   129: invokevirtual getLocalName : ()Ljava/lang/String;
    //   132: astore #6
    //   134: aload_2
    //   135: ldc_w ''
    //   138: if_acmpeq -> 322
    //   141: new java/lang/StringBuilder
    //   144: dup
    //   145: invokespecial <init> : ()V
    //   148: ldc_w '></'
    //   151: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   154: aload_2
    //   155: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   158: ldc_w ':'
    //   161: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   164: aload #6
    //   166: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   169: ldc_w '>'
    //   172: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   175: invokevirtual toString : ()Ljava/lang/String;
    //   178: astore_2
    //   179: aload_2
    //   180: astore_3
    //   181: aload_2
    //   182: ifnonnull -> 200
    //   185: iload_1
    //   186: ifeq -> 368
    //   189: aload_0
    //   190: getfield isHtml : Z
    //   193: ifeq -> 368
    //   196: ldc_w '>'
    //   199: astore_3
    //   200: aload_0
    //   201: getfield bout : Lgnu/text/PrettyWriter;
    //   204: aload_3
    //   205: invokevirtual write : (Ljava/lang/String;)V
    //   208: aload_0
    //   209: iconst_0
    //   210: putfield inStartTag : Z
    //   213: aload_0
    //   214: getfield printIndent : I
    //   217: iflt -> 227
    //   220: aload_0
    //   221: ldc_w ''
    //   224: invokevirtual endLogicalBlock : (Ljava/lang/String;)V
    //   227: aload_0
    //   228: bipush #-4
    //   230: putfield prev : I
    //   233: aload #4
    //   235: ifnull -> 272
    //   238: aload_0
    //   239: getfield escapeText : Z
    //   242: ifne -> 272
    //   245: ldc_w 'script'
    //   248: aload #4
    //   250: invokevirtual equals : (Ljava/lang/Object;)Z
    //   253: ifne -> 267
    //   256: ldc_w 'style'
    //   259: aload #4
    //   261: invokevirtual equals : (Ljava/lang/Object;)Z
    //   264: ifeq -> 272
    //   267: aload_0
    //   268: iconst_1
    //   269: putfield escapeText : Z
    //   272: aload_0
    //   273: getfield namespaceSaveStack : [Lgnu/xml/NamespaceBinding;
    //   276: astore_2
    //   277: aload_0
    //   278: getfield elementNesting : I
    //   281: iconst_1
    //   282: isub
    //   283: istore_1
    //   284: aload_0
    //   285: iload_1
    //   286: putfield elementNesting : I
    //   289: aload_0
    //   290: aload_2
    //   291: iload_1
    //   292: aaload
    //   293: putfield namespaceBindings : Lgnu/xml/NamespaceBinding;
    //   296: aload_0
    //   297: getfield namespaceSaveStack : [Lgnu/xml/NamespaceBinding;
    //   300: aload_0
    //   301: getfield elementNesting : I
    //   304: aconst_null
    //   305: aastore
    //   306: aload_0
    //   307: getfield elementNameStack : [Ljava/lang/Object;
    //   310: aload_0
    //   311: getfield elementNesting : I
    //   314: aconst_null
    //   315: aastore
    //   316: return
    //   317: iconst_0
    //   318: istore_1
    //   319: goto -> 77
    //   322: aload #5
    //   324: ldc_w ''
    //   327: if_acmpeq -> 337
    //   330: aload_3
    //   331: astore_2
    //   332: aload #5
    //   334: ifnonnull -> 179
    //   337: new java/lang/StringBuilder
    //   340: dup
    //   341: invokespecial <init> : ()V
    //   344: ldc_w '></'
    //   347: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   350: aload #6
    //   352: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   355: ldc_w '>'
    //   358: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   361: invokevirtual toString : ()Ljava/lang/String;
    //   364: astore_2
    //   365: goto -> 179
    //   368: aload_0
    //   369: getfield useEmptyElementTag : I
    //   372: iconst_2
    //   373: if_icmpne -> 383
    //   376: ldc_w ' />'
    //   379: astore_3
    //   380: goto -> 200
    //   383: ldc_w '/>'
    //   386: astore_3
    //   387: goto -> 200
    //   390: aload_0
    //   391: getfield printIndent : I
    //   394: iflt -> 427
    //   397: aload_0
    //   398: iconst_0
    //   399: iconst_0
    //   400: invokevirtual setIndentation : (IZ)V
    //   403: aload_0
    //   404: getfield prev : I
    //   407: bipush #-4
    //   409: if_icmpne -> 427
    //   412: aload_0
    //   413: getfield printIndent : I
    //   416: ifle -> 456
    //   419: bipush #82
    //   421: istore_1
    //   422: aload_0
    //   423: iload_1
    //   424: invokevirtual writeBreak : (I)V
    //   427: aload_0
    //   428: getfield bout : Lgnu/text/PrettyWriter;
    //   431: ldc_w '</'
    //   434: invokevirtual write : (Ljava/lang/String;)V
    //   437: aload_0
    //   438: aload #5
    //   440: invokevirtual writeQName : (Ljava/lang/Object;)V
    //   443: aload_0
    //   444: getfield bout : Lgnu/text/PrettyWriter;
    //   447: ldc_w '>'
    //   450: invokevirtual write : (Ljava/lang/String;)V
    //   453: goto -> 213
    //   456: bipush #78
    //   458: istore_1
    //   459: goto -> 422
  }
  
  public void endEntity() {}
  
  protected void endNumber() {
    writeWordEnd();
  }
  
  public void error(String paramString1, String paramString2) {
    throw new RuntimeException("serialization error: " + paramString1 + " [" + paramString2 + ']');
  }
  
  protected String getHtmlTag(Object paramObject) {
    if (paramObject instanceof gnu.mapping.Symbol) {
      paramObject = paramObject;
      String str = paramObject.getNamespaceURI();
      if (str == "http://www.w3.org/1999/xhtml" || (this.isHtmlOrXhtml && str == ""))
        return paramObject.getLocalPart(); 
    } else if (this.isHtmlOrXhtml) {
      return paramObject.toString();
    } 
    return null;
  }
  
  public boolean ignoring() {
    return false;
  }
  
  boolean mustHexEscape(int paramInt) {
    return ((paramInt >= 127 && (paramInt <= 159 || this.escapeNonAscii)) || paramInt == 8232 || (paramInt < 32 && (this.inAttribute || (paramInt != 9 && paramInt != 10))));
  }
  
  public void print(Object paramObject) {
    Object object;
    if (paramObject instanceof BigDecimal) {
      object = formatDecimal((BigDecimal)paramObject);
    } else if (paramObject instanceof Double || paramObject instanceof gnu.math.DFloNum) {
      object = formatDouble(((Number)paramObject).doubleValue());
    } else {
      object = paramObject;
      if (paramObject instanceof Float)
        object = formatFloat(((Float)paramObject).floatValue()); 
    } 
    if (object == null) {
      paramObject = "(null)";
    } else {
      paramObject = object.toString();
    } 
    write((String)paramObject);
  }
  
  void setIndentMode() {
    String str = null;
    Object object = indentLoc.get(null);
    if (object != null)
      str = object.toString(); 
    if (str == null) {
      this.printIndent = -1;
      return;
    } 
    if (str.equals("pretty")) {
      this.printIndent = 0;
      return;
    } 
    if (str.equals("always") || str.equals("yes")) {
      this.printIndent = 1;
      return;
    } 
    this.printIndent = -1;
  }
  
  public void setPrintXMLdecl(boolean paramBoolean) {
    this.printXMLdecl = paramBoolean;
  }
  
  public void setStyle(Object paramObject) {
    boolean bool;
    this.style = paramObject;
    if (this.canonicalize) {
      bool = false;
    } else {
      bool = true;
    } 
    this.useEmptyElementTag = bool;
    if ("html".equals(paramObject)) {
      this.isHtml = true;
      this.isHtmlOrXhtml = true;
      this.useEmptyElementTag = 2;
      if (this.namespaceBindings == NamespaceBinding.predefinedXML)
        this.namespaceBindings = XmlNamespace.HTML_BINDINGS; 
    } else if (this.namespaceBindings == XmlNamespace.HTML_BINDINGS) {
      this.namespaceBindings = NamespaceBinding.predefinedXML;
    } 
    if ("xhtml".equals(paramObject)) {
      this.isHtmlOrXhtml = true;
      this.useEmptyElementTag = 2;
    } 
    if ("plain".equals(paramObject))
      this.escapeText = false; 
  }
  
  public void startAttribute(Object paramObject) {
    if (!this.inStartTag && this.strict)
      error("attribute not in element", "SENR0001"); 
    if (this.inAttribute)
      this.bout.write(34); 
    this.inAttribute = true;
    this.bout.write(32);
    if (this.printIndent >= 0)
      writeBreakFill(); 
    this.bout.write(paramObject.toString());
    this.bout.write("=\"");
    this.prev = 32;
  }
  
  public void startDocument() {
    if (this.printXMLdecl)
      this.needXMLdecl = true; 
    setIndentMode();
    this.inDocument = true;
    if (this.printIndent >= 0 && !this.needXMLdecl)
      startLogicalBlock("", "", 2); 
  }
  
  public void startElement(Object paramObject) {
    // Byte code:
    //   0: aload_0
    //   1: invokevirtual closeTag : ()V
    //   4: aload_0
    //   5: getfield elementNesting : I
    //   8: ifne -> 169
    //   11: aload_0
    //   12: getfield inDocument : Z
    //   15: ifne -> 22
    //   18: aload_0
    //   19: invokevirtual setIndentMode : ()V
    //   22: aload_0
    //   23: getfield prev : I
    //   26: bipush #-7
    //   28: if_icmpne -> 37
    //   31: aload_0
    //   32: bipush #10
    //   34: invokevirtual write : (I)V
    //   37: getstatic gnu/xml/XMLPrinter.doctypeSystem : Lgnu/mapping/ThreadLocation;
    //   40: aconst_null
    //   41: invokevirtual get : (Ljava/lang/Object;)Ljava/lang/Object;
    //   44: astore #6
    //   46: aload #6
    //   48: ifnull -> 169
    //   51: aload #6
    //   53: invokevirtual toString : ()Ljava/lang/String;
    //   56: astore #7
    //   58: aload #7
    //   60: invokevirtual length : ()I
    //   63: ifle -> 169
    //   66: getstatic gnu/xml/XMLPrinter.doctypePublic : Lgnu/mapping/ThreadLocation;
    //   69: aconst_null
    //   70: invokevirtual get : (Ljava/lang/Object;)Ljava/lang/Object;
    //   73: astore #6
    //   75: aload_0
    //   76: getfield bout : Lgnu/text/PrettyWriter;
    //   79: ldc_w '<!DOCTYPE '
    //   82: invokevirtual write : (Ljava/lang/String;)V
    //   85: aload_0
    //   86: getfield bout : Lgnu/text/PrettyWriter;
    //   89: aload_1
    //   90: invokevirtual toString : ()Ljava/lang/String;
    //   93: invokevirtual write : (Ljava/lang/String;)V
    //   96: aload #6
    //   98: ifnonnull -> 419
    //   101: aconst_null
    //   102: astore #6
    //   104: aload #6
    //   106: ifnull -> 429
    //   109: aload #6
    //   111: invokevirtual length : ()I
    //   114: ifle -> 429
    //   117: aload_0
    //   118: getfield bout : Lgnu/text/PrettyWriter;
    //   121: ldc_w ' PUBLIC "'
    //   124: invokevirtual write : (Ljava/lang/String;)V
    //   127: aload_0
    //   128: getfield bout : Lgnu/text/PrettyWriter;
    //   131: aload #6
    //   133: invokevirtual write : (Ljava/lang/String;)V
    //   136: aload_0
    //   137: getfield bout : Lgnu/text/PrettyWriter;
    //   140: ldc_w '" "'
    //   143: invokevirtual write : (Ljava/lang/String;)V
    //   146: aload_0
    //   147: getfield bout : Lgnu/text/PrettyWriter;
    //   150: aload #7
    //   152: invokevirtual write : (Ljava/lang/String;)V
    //   155: aload_0
    //   156: getfield bout : Lgnu/text/PrettyWriter;
    //   159: ldc_w '">'
    //   162: invokevirtual write : (Ljava/lang/String;)V
    //   165: aload_0
    //   166: invokevirtual println : ()V
    //   169: aload_0
    //   170: getfield printIndent : I
    //   173: iflt -> 229
    //   176: aload_0
    //   177: getfield prev : I
    //   180: bipush #-3
    //   182: if_icmpeq -> 203
    //   185: aload_0
    //   186: getfield prev : I
    //   189: bipush #-4
    //   191: if_icmpeq -> 203
    //   194: aload_0
    //   195: getfield prev : I
    //   198: bipush #-5
    //   200: if_icmpne -> 218
    //   203: aload_0
    //   204: getfield printIndent : I
    //   207: ifle -> 442
    //   210: bipush #82
    //   212: istore_2
    //   213: aload_0
    //   214: iload_2
    //   215: invokevirtual writeBreak : (I)V
    //   218: aload_0
    //   219: ldc_w ''
    //   222: ldc_w ''
    //   225: iconst_2
    //   226: invokevirtual startLogicalBlock : (Ljava/lang/String;Ljava/lang/String;I)V
    //   229: aload_0
    //   230: getfield bout : Lgnu/text/PrettyWriter;
    //   233: bipush #60
    //   235: invokevirtual write : (I)V
    //   238: aload_0
    //   239: aload_1
    //   240: invokevirtual writeQName : (Ljava/lang/Object;)V
    //   243: aload_0
    //   244: getfield printIndent : I
    //   247: iflt -> 268
    //   250: aload_0
    //   251: getfield indentAttributes : Z
    //   254: ifeq -> 268
    //   257: aload_0
    //   258: ldc_w ''
    //   261: ldc_w ''
    //   264: iconst_2
    //   265: invokevirtual startLogicalBlock : (Ljava/lang/String;Ljava/lang/String;I)V
    //   268: aload_0
    //   269: getfield elementNameStack : [Ljava/lang/Object;
    //   272: aload_0
    //   273: getfield elementNesting : I
    //   276: aload_1
    //   277: aastore
    //   278: aload_0
    //   279: getfield namespaceSaveStack : [Lgnu/xml/NamespaceBinding;
    //   282: astore #6
    //   284: aload_0
    //   285: getfield elementNesting : I
    //   288: istore_2
    //   289: aload_0
    //   290: iload_2
    //   291: iconst_1
    //   292: iadd
    //   293: putfield elementNesting : I
    //   296: aload #6
    //   298: iload_2
    //   299: aload_0
    //   300: getfield namespaceBindings : Lgnu/xml/NamespaceBinding;
    //   303: aastore
    //   304: aload_1
    //   305: instanceof gnu/xml/XName
    //   308: ifeq -> 801
    //   311: aload_1
    //   312: checkcast gnu/xml/XName
    //   315: getfield namespaceNodes : Lgnu/xml/NamespaceBinding;
    //   318: astore #7
    //   320: aload #7
    //   322: aload_0
    //   323: getfield namespaceBindings : Lgnu/xml/NamespaceBinding;
    //   326: invokestatic commonAncestor : (Lgnu/xml/NamespaceBinding;Lgnu/xml/NamespaceBinding;)Lgnu/xml/NamespaceBinding;
    //   329: astore #8
    //   331: aload #7
    //   333: ifnonnull -> 448
    //   336: iconst_0
    //   337: istore_2
    //   338: iload_2
    //   339: anewarray gnu/xml/NamespaceBinding
    //   342: astore #9
    //   344: iconst_0
    //   345: istore_2
    //   346: aload_0
    //   347: getfield canonicalize : Z
    //   350: istore #5
    //   352: aload #7
    //   354: astore #6
    //   356: aload #6
    //   358: aload #8
    //   360: if_acmpeq -> 530
    //   363: iload_2
    //   364: istore_3
    //   365: aload #6
    //   367: invokevirtual getUri : ()Ljava/lang/String;
    //   370: pop
    //   371: aload #6
    //   373: invokevirtual getPrefix : ()Ljava/lang/String;
    //   376: astore #10
    //   378: iload_3
    //   379: iconst_1
    //   380: isub
    //   381: istore #4
    //   383: iload #4
    //   385: iflt -> 472
    //   388: aload #9
    //   390: iload #4
    //   392: aaload
    //   393: astore #11
    //   395: aload #11
    //   397: invokevirtual getPrefix : ()Ljava/lang/String;
    //   400: astore #12
    //   402: aload #10
    //   404: aload #12
    //   406: if_acmpne -> 459
    //   409: aload #6
    //   411: getfield next : Lgnu/xml/NamespaceBinding;
    //   414: astore #6
    //   416: goto -> 356
    //   419: aload #6
    //   421: invokevirtual toString : ()Ljava/lang/String;
    //   424: astore #6
    //   426: goto -> 104
    //   429: aload_0
    //   430: getfield bout : Lgnu/text/PrettyWriter;
    //   433: ldc_w ' SYSTEM "'
    //   436: invokevirtual write : (Ljava/lang/String;)V
    //   439: goto -> 146
    //   442: bipush #78
    //   444: istore_2
    //   445: goto -> 213
    //   448: aload #7
    //   450: aload #8
    //   452: invokevirtual count : (Lgnu/xml/NamespaceBinding;)I
    //   455: istore_2
    //   456: goto -> 338
    //   459: iload #4
    //   461: istore_3
    //   462: iload #5
    //   464: ifeq -> 378
    //   467: aload #10
    //   469: ifnonnull -> 495
    //   472: iload #5
    //   474: ifeq -> 525
    //   477: iload #4
    //   479: iconst_1
    //   480: iadd
    //   481: istore_3
    //   482: aload #9
    //   484: iload_3
    //   485: aload #6
    //   487: aastore
    //   488: iload_2
    //   489: iconst_1
    //   490: iadd
    //   491: istore_2
    //   492: goto -> 409
    //   495: aload #12
    //   497: ifnull -> 510
    //   500: aload #10
    //   502: aload #12
    //   504: invokevirtual compareTo : (Ljava/lang/String;)I
    //   507: ifle -> 472
    //   510: aload #9
    //   512: iload #4
    //   514: iconst_1
    //   515: iadd
    //   516: aload #11
    //   518: aastore
    //   519: iload #4
    //   521: istore_3
    //   522: goto -> 378
    //   525: iload_2
    //   526: istore_3
    //   527: goto -> 482
    //   530: iload_2
    //   531: iconst_1
    //   532: isub
    //   533: istore_3
    //   534: iload_3
    //   535: iflt -> 684
    //   538: aload #9
    //   540: iload_3
    //   541: aaload
    //   542: astore #10
    //   544: aload #10
    //   546: getfield prefix : Ljava/lang/String;
    //   549: astore #6
    //   551: aload #10
    //   553: getfield uri : Ljava/lang/String;
    //   556: astore #10
    //   558: iload_3
    //   559: istore_2
    //   560: aload #10
    //   562: aload_0
    //   563: getfield namespaceBindings : Lgnu/xml/NamespaceBinding;
    //   566: aload #6
    //   568: invokevirtual resolve : (Ljava/lang/String;)Ljava/lang/String;
    //   571: if_acmpeq -> 530
    //   574: aload #10
    //   576: ifnonnull -> 593
    //   579: aload #6
    //   581: ifnull -> 593
    //   584: iload_3
    //   585: istore_2
    //   586: aload_0
    //   587: getfield undeclareNamespaces : Z
    //   590: ifeq -> 530
    //   593: aload_0
    //   594: getfield bout : Lgnu/text/PrettyWriter;
    //   597: bipush #32
    //   599: invokevirtual write : (I)V
    //   602: aload #6
    //   604: ifnonnull -> 662
    //   607: aload_0
    //   608: getfield bout : Lgnu/text/PrettyWriter;
    //   611: ldc_w 'xmlns'
    //   614: invokevirtual write : (Ljava/lang/String;)V
    //   617: aload_0
    //   618: getfield bout : Lgnu/text/PrettyWriter;
    //   621: ldc_w '="'
    //   624: invokevirtual write : (Ljava/lang/String;)V
    //   627: aload_0
    //   628: iconst_1
    //   629: putfield inAttribute : Z
    //   632: aload #10
    //   634: ifnull -> 643
    //   637: aload_0
    //   638: aload #10
    //   640: invokevirtual write : (Ljava/lang/String;)V
    //   643: aload_0
    //   644: iconst_0
    //   645: putfield inAttribute : Z
    //   648: aload_0
    //   649: getfield bout : Lgnu/text/PrettyWriter;
    //   652: bipush #34
    //   654: invokevirtual write : (I)V
    //   657: iload_3
    //   658: istore_2
    //   659: goto -> 530
    //   662: aload_0
    //   663: getfield bout : Lgnu/text/PrettyWriter;
    //   666: ldc_w 'xmlns:'
    //   669: invokevirtual write : (Ljava/lang/String;)V
    //   672: aload_0
    //   673: getfield bout : Lgnu/text/PrettyWriter;
    //   676: aload #6
    //   678: invokevirtual write : (Ljava/lang/String;)V
    //   681: goto -> 617
    //   684: aload_0
    //   685: getfield undeclareNamespaces : Z
    //   688: ifeq -> 795
    //   691: aload_0
    //   692: getfield namespaceBindings : Lgnu/xml/NamespaceBinding;
    //   695: astore #6
    //   697: aload #6
    //   699: aload #8
    //   701: if_acmpeq -> 795
    //   704: aload #6
    //   706: getfield prefix : Ljava/lang/String;
    //   709: astore #9
    //   711: aload #6
    //   713: getfield uri : Ljava/lang/String;
    //   716: ifnull -> 763
    //   719: aload #7
    //   721: aload #9
    //   723: invokevirtual resolve : (Ljava/lang/String;)Ljava/lang/String;
    //   726: ifnonnull -> 763
    //   729: aload_0
    //   730: getfield bout : Lgnu/text/PrettyWriter;
    //   733: bipush #32
    //   735: invokevirtual write : (I)V
    //   738: aload #9
    //   740: ifnonnull -> 773
    //   743: aload_0
    //   744: getfield bout : Lgnu/text/PrettyWriter;
    //   747: ldc_w 'xmlns'
    //   750: invokevirtual write : (Ljava/lang/String;)V
    //   753: aload_0
    //   754: getfield bout : Lgnu/text/PrettyWriter;
    //   757: ldc_w '=""'
    //   760: invokevirtual write : (Ljava/lang/String;)V
    //   763: aload #6
    //   765: getfield next : Lgnu/xml/NamespaceBinding;
    //   768: astore #6
    //   770: goto -> 697
    //   773: aload_0
    //   774: getfield bout : Lgnu/text/PrettyWriter;
    //   777: ldc_w 'xmlns:'
    //   780: invokevirtual write : (Ljava/lang/String;)V
    //   783: aload_0
    //   784: getfield bout : Lgnu/text/PrettyWriter;
    //   787: aload #9
    //   789: invokevirtual write : (Ljava/lang/String;)V
    //   792: goto -> 753
    //   795: aload_0
    //   796: aload #7
    //   798: putfield namespaceBindings : Lgnu/xml/NamespaceBinding;
    //   801: aload_0
    //   802: getfield elementNesting : I
    //   805: aload_0
    //   806: getfield namespaceSaveStack : [Lgnu/xml/NamespaceBinding;
    //   809: arraylength
    //   810: if_icmplt -> 877
    //   813: aload_0
    //   814: getfield elementNesting : I
    //   817: iconst_2
    //   818: imul
    //   819: anewarray gnu/xml/NamespaceBinding
    //   822: astore #6
    //   824: aload_0
    //   825: getfield namespaceSaveStack : [Lgnu/xml/NamespaceBinding;
    //   828: iconst_0
    //   829: aload #6
    //   831: iconst_0
    //   832: aload_0
    //   833: getfield elementNesting : I
    //   836: invokestatic arraycopy : (Ljava/lang/Object;ILjava/lang/Object;II)V
    //   839: aload_0
    //   840: aload #6
    //   842: putfield namespaceSaveStack : [Lgnu/xml/NamespaceBinding;
    //   845: aload_0
    //   846: getfield elementNesting : I
    //   849: iconst_2
    //   850: imul
    //   851: anewarray java/lang/Object
    //   854: astore #6
    //   856: aload_0
    //   857: getfield elementNameStack : [Ljava/lang/Object;
    //   860: iconst_0
    //   861: aload #6
    //   863: iconst_0
    //   864: aload_0
    //   865: getfield elementNesting : I
    //   868: invokestatic arraycopy : (Ljava/lang/Object;ILjava/lang/Object;II)V
    //   871: aload_0
    //   872: aload #6
    //   874: putfield elementNameStack : [Ljava/lang/Object;
    //   877: aload_0
    //   878: iconst_1
    //   879: putfield inStartTag : Z
    //   882: aload_0
    //   883: aload_1
    //   884: invokevirtual getHtmlTag : (Ljava/lang/Object;)Ljava/lang/String;
    //   887: astore_1
    //   888: ldc_w 'script'
    //   891: aload_1
    //   892: invokevirtual equals : (Ljava/lang/Object;)Z
    //   895: ifne -> 908
    //   898: ldc_w 'style'
    //   901: aload_1
    //   902: invokevirtual equals : (Ljava/lang/Object;)Z
    //   905: ifeq -> 913
    //   908: aload_0
    //   909: iconst_0
    //   910: putfield escapeText : Z
    //   913: return
  }
  
  protected void startNumber() {
    startWord();
  }
  
  public void write(int paramInt) {
    closeTag();
    if (this.printIndent >= 0 && (paramInt == 13 || paramInt == 10)) {
      if (paramInt != 10 || this.prev != 13)
        writeBreak(82); 
      if (this.inComment > 0)
        this.inComment = 1; 
      return;
    } 
    if (!this.escapeText) {
      this.bout.write(paramInt);
      this.prev = paramInt;
      return;
    } 
    if (this.inComment > 0) {
      if (paramInt == 45) {
        if (this.inComment == 1) {
          this.inComment = 2;
        } else {
          this.bout.write(32);
        } 
      } else {
        this.inComment = 1;
      } 
      super.write(paramInt);
      return;
    } 
    this.prev = 59;
    if (paramInt == 60 && (!this.isHtml || !this.inAttribute)) {
      this.bout.write("&lt;");
      return;
    } 
    if (paramInt == 62) {
      this.bout.write("&gt;");
      return;
    } 
    if (paramInt == 38) {
      this.bout.write("&amp;");
      return;
    } 
    if (paramInt == 34 && this.inAttribute) {
      this.bout.write("&quot;");
      return;
    } 
    if (mustHexEscape(paramInt)) {
      int i = paramInt;
      int j = i;
      if (paramInt >= 55296) {
        if (paramInt < 56320) {
          this.savedHighSurrogate = (char)paramInt;
          return;
        } 
        j = i;
        if (paramInt < 57344) {
          j = (this.savedHighSurrogate - 55296) * 1024 + i - 56320 + 65536;
          this.savedHighSurrogate = Character.MIN_VALUE;
        } 
      } 
      this.bout.write("&#x" + Integer.toHexString(j).toUpperCase() + ";");
      return;
    } 
    this.bout.write(paramInt);
    this.prev = paramInt;
  }
  
  public void write(String paramString, int paramInt1, int paramInt2) {
    if (paramInt2 > 0) {
      closeTag();
      int j = paramInt1 + paramInt2;
      int i = 0;
      paramInt2 = paramInt1;
      paramInt1 = i;
      while (paramInt2 < j) {
        i = paramInt2 + 1;
        paramInt2 = paramString.charAt(paramInt2);
        if (mustHexEscape(paramInt2) || ((this.inComment > 0) ? (paramInt2 == 45 || this.inComment == 2) : (paramInt2 == 60 || paramInt2 == 62 || paramInt2 == 38 || (this.inAttribute && (paramInt2 == 34 || paramInt2 < 32))))) {
          if (paramInt1 > 0)
            this.bout.write(paramString, i - 1 - paramInt1, paramInt1); 
          write(paramInt2);
          paramInt1 = 0;
        } else {
          paramInt1++;
        } 
        paramInt2 = i;
      } 
      if (paramInt1 > 0)
        this.bout.write(paramString, j - paramInt1, paramInt1); 
    } 
    this.prev = 45;
  }
  
  public void write(char[] paramArrayOfchar, int paramInt1, int paramInt2) {
    if (paramInt2 > 0) {
      closeTag();
      int j = paramInt1 + paramInt2;
      int i = 0;
      paramInt2 = paramInt1;
      paramInt1 = i;
      while (paramInt2 < j) {
        i = paramInt2 + 1;
        paramInt2 = paramArrayOfchar[paramInt2];
        if (mustHexEscape(paramInt2) || ((this.inComment > 0) ? (paramInt2 == 45 || this.inComment == 2) : (paramInt2 == 60 || paramInt2 == 62 || paramInt2 == 38 || (this.inAttribute && (paramInt2 == 34 || paramInt2 < 32))))) {
          if (paramInt1 > 0)
            this.bout.write(paramArrayOfchar, i - 1 - paramInt1, paramInt1); 
          write(paramInt2);
          paramInt1 = 0;
        } else {
          paramInt1++;
        } 
        paramInt2 = i;
      } 
      if (paramInt1 > 0)
        this.bout.write(paramArrayOfchar, j - paramInt1, paramInt1); 
    } 
    this.prev = 45;
  }
  
  public void writeBaseUri(Object paramObject) {}
  
  public void writeBoolean(boolean paramBoolean) {
    startWord();
    print(paramBoolean);
    writeWordEnd();
  }
  
  public void writeCDATA(char[] paramArrayOfchar, int paramInt1, int paramInt2) {
    if (this.canonicalizeCDATA) {
      write(paramArrayOfchar, paramInt1, paramInt2);
      return;
    } 
    closeTag();
    this.bout.write("<![CDATA[");
    int m = paramInt1 + paramInt2;
    int j = paramInt1;
    int i = paramInt2;
    int k = paramInt1;
    paramInt1 = j;
    while (paramInt1 < m - 2) {
      int n = paramInt1;
      j = k;
      paramInt2 = i;
      if (paramArrayOfchar[paramInt1] == ']') {
        n = paramInt1;
        j = k;
        paramInt2 = i;
        if (paramArrayOfchar[paramInt1 + 1] == ']') {
          n = paramInt1;
          j = k;
          paramInt2 = i;
          if (paramArrayOfchar[paramInt1 + 2] == '>') {
            if (paramInt1 > k)
              this.bout.write(paramArrayOfchar, k, paramInt1 - k); 
            print("]]]><![CDATA[]>");
            j = paramInt1 + 3;
            paramInt2 = m - j;
            n = paramInt1 + 2;
          } 
        } 
      } 
      paramInt1 = n + 1;
      k = j;
      i = paramInt2;
    } 
    this.bout.write(paramArrayOfchar, k, i);
    this.bout.write("]]>");
    this.prev = 62;
  }
  
  public void writeComment(String paramString) {
    beginComment();
    write(paramString);
    endComment();
  }
  
  public void writeComment(char[] paramArrayOfchar, int paramInt1, int paramInt2) {
    beginComment();
    write(paramArrayOfchar, paramInt1, paramInt2);
    endComment();
  }
  
  public void writeDouble(double paramDouble) {
    startWord();
    this.bout.write(formatDouble(paramDouble));
  }
  
  public void writeFloat(float paramFloat) {
    startWord();
    this.bout.write(formatFloat(paramFloat));
  }
  
  public void writeObject(Object paramObject) {
    if (paramObject instanceof SeqPosition) {
      this.bout.clearWordEnd();
      paramObject = paramObject;
      ((SeqPosition)paramObject).sequence.consumeNext(((SeqPosition)paramObject).ipos, (Consumer)this);
      if (((SeqPosition)paramObject).sequence instanceof NodeTree)
        this.prev = 45; 
      return;
    } 
    if (paramObject instanceof Consumable && !(paramObject instanceof UnescapedData)) {
      ((Consumable)paramObject).consume((Consumer)this);
      return;
    } 
    if (paramObject instanceof Keyword) {
      startAttribute(((Keyword)paramObject).getName());
      this.prev = -6;
      return;
    } 
    closeTag();
    if (paramObject instanceof UnescapedData) {
      this.bout.clearWordEnd();
      this.bout.write(((UnescapedData)paramObject).getData());
      this.prev = 45;
      return;
    } 
    if (paramObject instanceof Char) {
      Char.print(((Char)paramObject).intValue(), (Consumer)this);
      return;
    } 
    startWord();
    this.prev = 32;
    print(paramObject);
    writeWordEnd();
    this.prev = -2;
  }
  
  public void writePosition(AbstractSequence paramAbstractSequence, int paramInt) {
    paramAbstractSequence.consumeNext(paramInt, (Consumer)this);
  }
  
  public void writeProcessingInstruction(String paramString, char[] paramArrayOfchar, int paramInt1, int paramInt2) {
    if ("xml".equals(paramString))
      this.needXMLdecl = false; 
    closeTag();
    this.bout.write("<?");
    print(paramString);
    print(' ');
    this.bout.write(paramArrayOfchar, paramInt1, paramInt2);
    this.bout.write("?>");
    this.prev = -7;
  }
  
  protected void writeQName(Object paramObject) {
    if (paramObject instanceof gnu.mapping.Symbol) {
      paramObject = paramObject;
      String str = paramObject.getPrefix();
      if (str != null && str.length() > 0) {
        this.bout.write(str);
        this.bout.write(58);
      } 
      this.bout.write(paramObject.getLocalPart());
      return;
    } 
    PrettyWriter prettyWriter = this.bout;
    if (paramObject == null) {
      paramObject = "{null name}";
    } else {
      paramObject = paramObject;
    } 
    prettyWriter.write((String)paramObject);
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/gnu/xml/XMLPrinter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */