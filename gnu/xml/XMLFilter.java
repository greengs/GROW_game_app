package gnu.xml;

import gnu.expr.Keyword;
import gnu.kawa.sax.ContentConsumer;
import gnu.lists.AbstractSequence;
import gnu.lists.Consumer;
import gnu.lists.PositionConsumer;
import gnu.lists.SeqPosition;
import gnu.lists.TreeList;
import gnu.lists.XConsumer;
import gnu.mapping.Symbol;
import gnu.text.Char;
import gnu.text.LineBufferedReader;
import gnu.text.SourceLocator;
import gnu.text.SourceMessages;
import java.io.IOException;
import java.util.List;
import org.xml.sax.AttributeList;
import org.xml.sax.Attributes;
import org.xml.sax.ContentHandler;
import org.xml.sax.DocumentHandler;
import org.xml.sax.Locator;
import org.xml.sax.SAXException;

public class XMLFilter implements DocumentHandler, ContentHandler, SourceLocator, XConsumer, PositionConsumer {
  public static final int COPY_NAMESPACES_INHERIT = 2;
  
  public static final int COPY_NAMESPACES_PRESERVE = 1;
  
  private static final int SAW_KEYWORD = 1;
  
  private static final int SAW_WORD = 2;
  
  int attrCount = -1;
  
  String attrLocalName;
  
  String attrPrefix;
  
  Consumer base;
  
  public transient int copyNamespacesMode = 1;
  
  String currentNamespacePrefix;
  
  protected int ignoringLevel;
  
  LineBufferedReader in;
  
  boolean inStartTag;
  
  SourceLocator locator;
  
  MappingInfo[] mappingTable = new MappingInfo[128];
  
  int mappingTableMask = this.mappingTable.length - 1;
  
  private SourceMessages messages;
  
  boolean mismatchReported;
  
  NamespaceBinding namespaceBindings;
  
  public boolean namespacePrefixes = false;
  
  protected int nesting;
  
  public Consumer out;
  
  int previous = 0;
  
  int[] startIndexes = null;
  
  protected int stringizingElementNesting = -1;
  
  protected int stringizingLevel;
  
  TreeList tlist;
  
  Object[] workStack;
  
  public XMLFilter(Consumer paramConsumer) {
    this.base = paramConsumer;
    this.out = paramConsumer;
    if (paramConsumer instanceof NodeTree) {
      this.tlist = (NodeTree)paramConsumer;
    } else {
      this.tlist = new TreeList();
    } 
    this.namespaceBindings = NamespaceBinding.predefinedXML;
  }
  
  public static String duplicateAttributeMessage(Symbol paramSymbol, Object paramObject) {
    StringBuffer stringBuffer = new StringBuffer("duplicate attribute: ");
    String str = paramSymbol.getNamespaceURI();
    if (str != null && str.length() > 0) {
      stringBuffer.append('{');
      stringBuffer.append('}');
      stringBuffer.append(str);
    } 
    stringBuffer.append(paramSymbol.getLocalPart());
    if (paramObject != null) {
      stringBuffer.append(" in <");
      stringBuffer.append(paramObject);
      stringBuffer.append('>');
    } 
    return stringBuffer.toString();
  }
  
  private void ensureSpaceInStartIndexes(int paramInt) {
    if (this.startIndexes == null) {
      this.startIndexes = new int[20];
      return;
    } 
    if (paramInt >= this.startIndexes.length) {
      int[] arrayOfInt = new int[this.startIndexes.length * 2];
      System.arraycopy(this.startIndexes, 0, arrayOfInt, 0, paramInt);
      this.startIndexes = arrayOfInt;
      return;
    } 
  }
  
  private void ensureSpaceInWorkStack(int paramInt) {
    if (this.workStack == null) {
      this.workStack = new Object[20];
      return;
    } 
    if (paramInt >= this.workStack.length) {
      Object[] arrayOfObject = new Object[this.workStack.length * 2];
      System.arraycopy(this.workStack, 0, arrayOfObject, 0, paramInt);
      this.workStack = arrayOfObject;
      return;
    } 
  }
  
  private NamespaceBinding mergeHelper(NamespaceBinding paramNamespaceBinding1, NamespaceBinding paramNamespaceBinding2) {
    if (paramNamespaceBinding2 == NamespaceBinding.predefinedXML)
      return paramNamespaceBinding1; 
    NamespaceBinding namespaceBinding = mergeHelper(paramNamespaceBinding1, paramNamespaceBinding2.next);
    String str3 = paramNamespaceBinding2.uri;
    paramNamespaceBinding1 = namespaceBinding;
    if (namespaceBinding == null) {
      if (str3 == null)
        return namespaceBinding; 
      paramNamespaceBinding1 = NamespaceBinding.predefinedXML;
    } 
    String str1 = paramNamespaceBinding2.prefix;
    String str2 = paramNamespaceBinding1.resolve(str1);
    return ((str2 == null) ? (str3 == null) : str2.equals(str3)) ? paramNamespaceBinding1 : findNamespaceBinding(str1, str3, paramNamespaceBinding1);
  }
  
  private String resolve(String paramString, boolean paramBoolean) {
    if (paramBoolean && paramString == null)
      return ""; 
    String str2 = this.namespaceBindings.resolve(paramString);
    String str1 = str2;
    if (str2 == null) {
      if (paramString != null)
        error('e', "unknown namespace prefix '" + paramString + '\''); 
      return "";
    } 
    return str1;
  }
  
  private boolean startAttributeCommon() {
    if (this.stringizingElementNesting >= 0)
      this.ignoringLevel++; 
    int i = this.stringizingLevel;
    this.stringizingLevel = i + 1;
    if (i > 0)
      return false; 
    if (this.attrCount < 0)
      this.attrCount = 0; 
    ensureSpaceInWorkStack(this.nesting + this.attrCount);
    ensureSpaceInStartIndexes(this.attrCount);
    this.startIndexes[this.attrCount] = this.tlist.gapStart;
    this.attrCount++;
    return true;
  }
  
  public XMLFilter append(char paramChar) {
    write(paramChar);
    return this;
  }
  
  public XMLFilter append(CharSequence paramCharSequence) {
    CharSequence charSequence = paramCharSequence;
    if (paramCharSequence == null)
      charSequence = "null"; 
    append(charSequence, 0, charSequence.length());
    return this;
  }
  
  public XMLFilter append(CharSequence paramCharSequence, int paramInt1, int paramInt2) {
    CharSequence charSequence = paramCharSequence;
    if (paramCharSequence == null)
      charSequence = "null"; 
    write(charSequence, paramInt1, paramInt2 - paramInt1);
    return this;
  }
  
  public void beginEntity(Object paramObject) {
    if (this.base instanceof XConsumer)
      ((XConsumer)this.base).beginEntity(paramObject); 
  }
  
  public void characters(char[] paramArrayOfchar, int paramInt1, int paramInt2) throws SAXException {
    write(paramArrayOfchar, paramInt1, paramInt2);
  }
  
  protected void checkValidComment(char[] paramArrayOfchar, int paramInt1, int paramInt2) {
    int i = 1;
    while (true) {
      int j = paramInt2 - 1;
      if (j >= 0) {
        if (paramArrayOfchar[paramInt1 + j] == '-') {
          paramInt2 = 1;
        } else {
          paramInt2 = 0;
        } 
        if (i && paramInt2 != 0) {
          error('e', "consecutive or final hyphen in XML comment");
          return;
        } 
      } else {
        return;
      } 
      i = paramInt2;
      paramInt2 = j;
    } 
  }
  
  protected boolean checkWriteAtomic() {
    this.previous = 0;
    if (this.ignoringLevel > 0)
      return false; 
    closeStartTag();
    return true;
  }
  
  void closeStartTag() {
    NamespaceBinding namespaceBinding2;
    if (this.attrCount < 0 || this.stringizingLevel > 0)
      return; 
    this.inStartTag = false;
    this.previous = 0;
    if (this.attrLocalName != null)
      endAttribute(); 
    if (this.nesting == 0) {
      namespaceBinding2 = NamespaceBinding.predefinedXML;
    } else {
      namespaceBinding2 = (NamespaceBinding)this.workStack[this.nesting - 2];
    } 
    NamespaceBinding namespaceBinding1 = this.namespaceBindings;
    int i = 0;
    while (i <= this.attrCount) {
      Object object = this.workStack[this.nesting + i - 1];
      NamespaceBinding namespaceBinding = namespaceBinding1;
      if (object instanceof Symbol) {
        Symbol symbol = (Symbol)object;
        String str1 = symbol.getPrefix();
        String str2 = str1;
        if (str1 == "")
          str2 = null; 
        str1 = symbol.getNamespaceURI();
        object = str1;
        if (str1 == "")
          object = null; 
        if (i > 0 && str2 == null && object == null) {
          NamespaceBinding namespaceBinding4 = namespaceBinding1;
          continue;
        } 
        boolean bool = false;
        NamespaceBinding namespaceBinding3 = namespaceBinding1;
        while (true)
          namespaceBinding3 = namespaceBinding3.next; 
      } 
      continue;
    } 
    for (int j = 0;; j++) {
      String str;
      if (j <= this.attrCount) {
        Object object = this.workStack[this.nesting + j - 1];
        int m = 0;
        i = 0;
        if (object instanceof MappingInfo || this.out == this.tlist) {
          if (object instanceof MappingInfo) {
            mappingInfo = (MappingInfo)object;
            String str2 = mappingInfo.prefix;
            String str1 = mappingInfo.local;
            if (j > 0 && ((str2 == null && str1 == "xmlns") || str2 == "xmlns")) {
              i = 1;
              str = "(namespace-node)";
            } else {
              boolean bool;
              if (j > 0) {
                bool = true;
              } else {
                bool = false;
              } 
              str = resolve(str2, bool);
            } 
          } else {
            Symbol symbol = (Symbol)object;
            mappingInfo = lookupTag(symbol);
            String str2 = mappingInfo.prefix;
            String str1 = mappingInfo.local;
            str = symbol.getNamespaceURI();
          } 
          m = mappingInfo.tagHash;
          int n = m & this.mappingTableMask;
          MappingInfo mappingInfo = this.mappingTable[n];
          while (true)
            mappingInfo = mappingInfo.nextInBucket; 
        } else {
          Symbol symbol = (Symbol)object;
          str = symbol.getNamespaceURI();
          String str1 = symbol.getLocalName();
          namespaceBinding2 = null;
          i = m;
          continue;
        } 
      } else {
        break;
      } 
      this.out.startAttribute(str);
      int k = this.startIndexes[j - 1];
      if (j < this.attrCount) {
        i = this.startIndexes[j];
      } else {
        i = this.tlist.gapStart;
      } 
      this.tlist.consumeIRange(k + 5, i - 1, this.out);
      this.out.endAttribute();
    } 
    if (this.out instanceof ContentConsumer)
      ((ContentConsumer)this.out).endStartTag(); 
    for (i = 1; i <= this.attrCount; i++)
      this.workStack[this.nesting + i - 1] = null; 
    if (this.out != this.tlist) {
      this.base = this.out;
      this.tlist.clear();
    } 
    this.attrCount = -1;
  }
  
  public void commentFromParser(char[] paramArrayOfchar, int paramInt1, int paramInt2) {
    if (this.stringizingLevel == 0) {
      closeStartTag();
      if (this.base instanceof XConsumer)
        ((XConsumer)this.base).writeComment(paramArrayOfchar, paramInt1, paramInt2); 
      return;
    } 
    if (this.stringizingElementNesting < 0) {
      this.base.write(paramArrayOfchar, paramInt1, paramInt2);
      return;
    } 
  }
  
  public void consume(SeqPosition paramSeqPosition) {
    writePosition(paramSeqPosition.sequence, paramSeqPosition.ipos);
  }
  
  public void emitCharacterReference(int paramInt1, char[] paramArrayOfchar, int paramInt2, int paramInt3) {
    if (paramInt1 >= 65536) {
      Char.print(paramInt1, (Consumer)this);
      return;
    } 
    write(paramInt1);
  }
  
  public void emitDoctypeDecl(char[] paramArrayOfchar, int paramInt1, int paramInt2, int paramInt3, int paramInt4) {}
  
  public void emitEndAttributes() {
    if (this.attrLocalName != null)
      endAttribute(); 
    closeStartTag();
  }
  
  public void emitEndElement(char[] paramArrayOfchar, int paramInt1, int paramInt2) {
    if (this.attrLocalName != null) {
      error('e', "unclosed attribute");
      endAttribute();
    } 
    if (!inElement()) {
      error('e', "unmatched end element");
      return;
    } 
    if (paramArrayOfchar != null) {
      MappingInfo mappingInfo = lookupTag(paramArrayOfchar, paramInt1, paramInt2);
      Object object = this.workStack[this.nesting - 1];
      if (object instanceof MappingInfo && !this.mismatchReported) {
        object = object;
        if (mappingInfo.local != ((MappingInfo)object).local || mappingInfo.prefix != ((MappingInfo)object).prefix) {
          StringBuffer stringBuffer = new StringBuffer("</");
          stringBuffer.append(paramArrayOfchar, paramInt1, paramInt2);
          stringBuffer.append("> matching <");
          String str = ((MappingInfo)object).prefix;
          if (str != null) {
            stringBuffer.append(str);
            stringBuffer.append(':');
          } 
          stringBuffer.append(((MappingInfo)object).local);
          stringBuffer.append('>');
          error('e', stringBuffer.toString());
          this.mismatchReported = true;
        } 
      } 
    } 
    closeStartTag();
    if (this.nesting > 0) {
      endElement();
      return;
    } 
  }
  
  public void emitEntityReference(char[] paramArrayOfchar, int paramInt1, int paramInt2) {
    byte b1;
    char c = paramArrayOfchar[paramInt1];
    byte b2 = 63;
    if (paramInt2 == 2 && paramArrayOfchar[paramInt1 + 1] == 't') {
      if (c == 'l') {
        b1 = 60;
      } else {
        b1 = b2;
        if (c == 'g')
          b1 = 62; 
      } 
    } else if (paramInt2 == 3) {
      b1 = b2;
      if (c == 'a') {
        b1 = b2;
        if (paramArrayOfchar[paramInt1 + 1] == 'm') {
          b1 = b2;
          if (paramArrayOfchar[paramInt1 + 2] == 'p')
            b1 = 38; 
        } 
      } 
    } else {
      b1 = b2;
      if (paramInt2 == 4) {
        paramInt2 = paramArrayOfchar[paramInt1 + 1];
        char c1 = paramArrayOfchar[paramInt1 + 2];
        paramInt1 = paramArrayOfchar[paramInt1 + 3];
        if (c == 'q' && paramInt2 == 117 && c1 == 'o' && paramInt1 == 116) {
          b1 = 34;
        } else {
          b1 = b2;
          if (c == 'a') {
            b1 = b2;
            if (paramInt2 == 112) {
              b1 = b2;
              if (c1 == 'o') {
                b1 = b2;
                if (paramInt1 == 115)
                  b1 = 39; 
              } 
            } 
          } 
        } 
      } 
    } 
    write(b1);
  }
  
  public void emitStartAttribute(char[] paramArrayOfchar, int paramInt1, int paramInt2) {
    if (this.attrLocalName != null)
      endAttribute(); 
    if (startAttributeCommon()) {
      MappingInfo mappingInfo = lookupTag(paramArrayOfchar, paramInt1, paramInt2);
      this.workStack[this.nesting + this.attrCount - 1] = mappingInfo;
      String str1 = mappingInfo.prefix;
      String str2 = mappingInfo.local;
      this.attrLocalName = str2;
      this.attrPrefix = str1;
      if (str1 != null) {
        if (str1 == "xmlns")
          this.currentNamespacePrefix = str2; 
      } else if (str2 == "xmlns" && str1 == null) {
        this.currentNamespacePrefix = "";
      } 
      if (this.currentNamespacePrefix == null || this.namespacePrefixes) {
        this.tlist.startAttribute(0);
        return;
      } 
    } 
  }
  
  public void emitStartElement(char[] paramArrayOfchar, int paramInt1, int paramInt2) {
    closeStartTag();
    MappingInfo mappingInfo = lookupTag(paramArrayOfchar, paramInt1, paramInt2);
    startElementCommon();
    ensureSpaceInWorkStack(this.nesting - 1);
    this.workStack[this.nesting - 1] = mappingInfo;
  }
  
  public void endAttribute() {
    // Byte code:
    //   0: aload_0
    //   1: getfield attrLocalName : Ljava/lang/String;
    //   4: ifnonnull -> 8
    //   7: return
    //   8: aload_0
    //   9: getfield previous : I
    //   12: iconst_1
    //   13: if_icmpne -> 22
    //   16: aload_0
    //   17: iconst_0
    //   18: putfield previous : I
    //   21: return
    //   22: aload_0
    //   23: getfield stringizingElementNesting : I
    //   26: iflt -> 39
    //   29: aload_0
    //   30: aload_0
    //   31: getfield ignoringLevel : I
    //   34: iconst_1
    //   35: isub
    //   36: putfield ignoringLevel : I
    //   39: aload_0
    //   40: getfield stringizingLevel : I
    //   43: iconst_1
    //   44: isub
    //   45: istore_1
    //   46: aload_0
    //   47: iload_1
    //   48: putfield stringizingLevel : I
    //   51: iload_1
    //   52: ifne -> 7
    //   55: aload_0
    //   56: getfield attrLocalName : Ljava/lang/String;
    //   59: ldc_w 'id'
    //   62: if_acmpne -> 115
    //   65: aload_0
    //   66: getfield attrPrefix : Ljava/lang/String;
    //   69: ldc_w 'xml'
    //   72: if_acmpne -> 115
    //   75: aload_0
    //   76: getfield startIndexes : [I
    //   79: aload_0
    //   80: getfield attrCount : I
    //   83: iconst_1
    //   84: isub
    //   85: iaload
    //   86: iconst_5
    //   87: iadd
    //   88: istore_2
    //   89: aload_0
    //   90: getfield tlist : Lgnu/lists/TreeList;
    //   93: getfield gapStart : I
    //   96: istore #4
    //   98: aload_0
    //   99: getfield tlist : Lgnu/lists/TreeList;
    //   102: getfield data : [C
    //   105: astore #10
    //   107: iload_2
    //   108: istore_1
    //   109: iload_1
    //   110: iload #4
    //   112: if_icmplt -> 345
    //   115: aload_0
    //   116: aconst_null
    //   117: putfield attrLocalName : Ljava/lang/String;
    //   120: aload_0
    //   121: aconst_null
    //   122: putfield attrPrefix : Ljava/lang/String;
    //   125: aload_0
    //   126: getfield currentNamespacePrefix : Ljava/lang/String;
    //   129: ifnull -> 139
    //   132: aload_0
    //   133: getfield namespacePrefixes : Z
    //   136: ifeq -> 146
    //   139: aload_0
    //   140: getfield tlist : Lgnu/lists/TreeList;
    //   143: invokevirtual endAttribute : ()V
    //   146: aload_0
    //   147: getfield currentNamespacePrefix : Ljava/lang/String;
    //   150: ifnull -> 7
    //   153: aload_0
    //   154: getfield startIndexes : [I
    //   157: aload_0
    //   158: getfield attrCount : I
    //   161: iconst_1
    //   162: isub
    //   163: iaload
    //   164: istore #7
    //   166: iload #7
    //   168: istore_1
    //   169: aload_0
    //   170: getfield tlist : Lgnu/lists/TreeList;
    //   173: getfield gapStart : I
    //   176: istore #9
    //   178: iload #9
    //   180: iload_1
    //   181: isub
    //   182: istore #8
    //   184: aload_0
    //   185: getfield tlist : Lgnu/lists/TreeList;
    //   188: getfield data : [C
    //   191: astore #11
    //   193: iconst_0
    //   194: istore_2
    //   195: iload_1
    //   196: istore_3
    //   197: aload #11
    //   199: astore #10
    //   201: iload_1
    //   202: istore #6
    //   204: iload #8
    //   206: istore #5
    //   208: iload_2
    //   209: istore #4
    //   211: iload_3
    //   212: iload #9
    //   214: if_icmpge -> 293
    //   217: aload #11
    //   219: iload_3
    //   220: caload
    //   221: istore #4
    //   223: ldc_w 65535
    //   226: iload #4
    //   228: iand
    //   229: ldc_w 40959
    //   232: if_icmple -> 457
    //   235: new java/lang/StringBuffer
    //   238: dup
    //   239: invokespecial <init> : ()V
    //   242: astore #11
    //   244: aload_0
    //   245: getfield tlist : Lgnu/lists/TreeList;
    //   248: iload_1
    //   249: iload #9
    //   251: aload #11
    //   253: invokevirtual stringValue : (IILjava/lang/StringBuffer;)V
    //   256: aload #11
    //   258: invokevirtual hashCode : ()I
    //   261: istore #4
    //   263: iconst_0
    //   264: istore #6
    //   266: aload #11
    //   268: invokevirtual length : ()I
    //   271: istore #5
    //   273: aload #11
    //   275: invokevirtual length : ()I
    //   278: newarray char
    //   280: astore #10
    //   282: aload #11
    //   284: iconst_0
    //   285: iload #5
    //   287: aload #10
    //   289: iconst_0
    //   290: invokevirtual getChars : (II[CI)V
    //   293: aload_0
    //   294: getfield tlist : Lgnu/lists/TreeList;
    //   297: iload #7
    //   299: putfield gapStart : I
    //   302: aload_0
    //   303: getfield currentNamespacePrefix : Ljava/lang/String;
    //   306: ldc ''
    //   308: if_acmpne -> 472
    //   311: aconst_null
    //   312: astore #11
    //   314: aload_0
    //   315: aload_0
    //   316: aload #11
    //   318: aload #10
    //   320: iload #6
    //   322: iload #5
    //   324: iload #4
    //   326: aload_0
    //   327: getfield namespaceBindings : Lgnu/xml/NamespaceBinding;
    //   330: invokevirtual lookupNamespaceBinding : (Ljava/lang/String;[CIIILgnu/xml/NamespaceBinding;)Lgnu/xml/MappingInfo;
    //   333: getfield namespaces : Lgnu/xml/NamespaceBinding;
    //   336: putfield namespaceBindings : Lgnu/xml/NamespaceBinding;
    //   339: aload_0
    //   340: aconst_null
    //   341: putfield currentNamespacePrefix : Ljava/lang/String;
    //   344: return
    //   345: iload_1
    //   346: iconst_1
    //   347: iadd
    //   348: istore_3
    //   349: aload #10
    //   351: iload_1
    //   352: caload
    //   353: istore_1
    //   354: ldc_w 65535
    //   357: iload_1
    //   358: iand
    //   359: ldc_w 40959
    //   362: if_icmpgt -> 404
    //   365: iload_1
    //   366: bipush #9
    //   368: if_icmpeq -> 404
    //   371: iload_1
    //   372: bipush #13
    //   374: if_icmpeq -> 404
    //   377: iload_1
    //   378: bipush #10
    //   380: if_icmpeq -> 404
    //   383: iload_1
    //   384: bipush #32
    //   386: if_icmpne -> 452
    //   389: iload_3
    //   390: iload #4
    //   392: if_icmpeq -> 404
    //   395: aload #10
    //   397: iload_3
    //   398: caload
    //   399: bipush #32
    //   401: if_icmpne -> 452
    //   404: new java/lang/StringBuffer
    //   407: dup
    //   408: invokespecial <init> : ()V
    //   411: astore #10
    //   413: aload_0
    //   414: getfield tlist : Lgnu/lists/TreeList;
    //   417: iload_2
    //   418: iload #4
    //   420: aload #10
    //   422: invokevirtual stringValue : (IILjava/lang/StringBuffer;)V
    //   425: aload_0
    //   426: getfield tlist : Lgnu/lists/TreeList;
    //   429: iload_2
    //   430: putfield gapStart : I
    //   433: aload_0
    //   434: getfield tlist : Lgnu/lists/TreeList;
    //   437: aload #10
    //   439: invokevirtual toString : ()Ljava/lang/String;
    //   442: iconst_1
    //   443: invokestatic replaceWhitespace : (Ljava/lang/String;Z)Ljava/lang/String;
    //   446: invokevirtual write : (Ljava/lang/String;)V
    //   449: goto -> 115
    //   452: iload_3
    //   453: istore_1
    //   454: goto -> 109
    //   457: iload_2
    //   458: bipush #31
    //   460: imul
    //   461: iload #4
    //   463: iadd
    //   464: istore_2
    //   465: iload_3
    //   466: iconst_1
    //   467: iadd
    //   468: istore_3
    //   469: goto -> 197
    //   472: aload_0
    //   473: getfield currentNamespacePrefix : Ljava/lang/String;
    //   476: astore #11
    //   478: goto -> 314
  }
  
  public void endDocument() {
    if (this.stringizingLevel > 0) {
      writeJoiner();
      return;
    } 
    this.nesting -= 2;
    this.namespaceBindings = (NamespaceBinding)this.workStack[this.nesting];
    this.workStack[this.nesting] = null;
    this.workStack[this.nesting + 1] = null;
    if (this.nesting == 0) {
      this.base.endDocument();
      return;
    } 
    writeJoiner();
  }
  
  public void endElement() {
    closeStartTag();
    this.nesting -= 2;
    this.previous = 0;
    if (this.stringizingLevel == 0) {
      this.namespaceBindings = (NamespaceBinding)this.workStack[this.nesting];
      this.workStack[this.nesting] = null;
      this.workStack[this.nesting + 1] = null;
      this.base.endElement();
      return;
    } 
    if (this.stringizingElementNesting == this.nesting) {
      this.stringizingElementNesting = -1;
      this.previous = 2;
      return;
    } 
  }
  
  public void endElement(String paramString) throws SAXException {
    endElement();
  }
  
  public void endElement(String paramString1, String paramString2, String paramString3) {
    endElement();
  }
  
  public void endEntity() {
    if (this.base instanceof XConsumer)
      ((XConsumer)this.base).endEntity(); 
  }
  
  public void endPrefixMapping(String paramString) {
    this.namespaceBindings = this.namespaceBindings.getNext();
  }
  
  public void error(char paramChar, String paramString) {
    if (this.messages == null)
      throw new RuntimeException(paramString); 
    if (this.locator != null) {
      this.messages.error(paramChar, this.locator, paramString);
      return;
    } 
    this.messages.error(paramChar, paramString);
  }
  
  public NamespaceBinding findNamespaceBinding(String paramString1, String paramString2, NamespaceBinding paramNamespaceBinding) {
    if (paramString2 == null) {
      i = 0;
    } else {
      i = paramString2.hashCode();
    } 
    int j = i;
    if (paramString1 != null)
      j = i ^ paramString1.hashCode(); 
    int i = j & this.mappingTableMask;
    MappingInfo mappingInfo = this.mappingTable[i];
    while (true) {
      String str;
      if (mappingInfo == null) {
        MappingInfo mappingInfo2 = new MappingInfo();
        mappingInfo2.nextInBucket = this.mappingTable[i];
        this.mappingTable[i] = mappingInfo2;
        mappingInfo2.tagHash = j;
        mappingInfo2.prefix = paramString1;
        mappingInfo2.local = paramString2;
        mappingInfo2.uri = paramString2;
        str = paramString2;
        if (paramString2 == "")
          str = null; 
        mappingInfo2.namespaces = new NamespaceBinding(paramString1, str, paramNamespaceBinding);
        return mappingInfo2.namespaces;
      } 
      if (((MappingInfo)str).tagHash == j && ((MappingInfo)str).prefix == paramString1) {
        NamespaceBinding namespaceBinding = ((MappingInfo)str).namespaces;
        if (namespaceBinding != null && namespaceBinding.getNext() == this.namespaceBindings && namespaceBinding.getPrefix() == paramString1 && ((MappingInfo)str).uri == paramString2)
          return ((MappingInfo)str).namespaces; 
      } 
      MappingInfo mappingInfo1 = ((MappingInfo)str).nextInBucket;
    } 
  }
  
  public int getColumnNumber() {
    if (this.in != null) {
      int i = this.in.getColumnNumber();
      if (i > 0)
        return i; 
    } 
    return -1;
  }
  
  public String getFileName() {
    return (this.in == null) ? null : this.in.getName();
  }
  
  public int getLineNumber() {
    if (this.in != null) {
      int i = this.in.getLineNumber();
      if (i >= 0)
        return i + 1; 
    } 
    return -1;
  }
  
  public String getPublicId() {
    return null;
  }
  
  public String getSystemId() {
    return (this.in == null) ? null : this.in.getName();
  }
  
  public void ignorableWhitespace(char[] paramArrayOfchar, int paramInt1, int paramInt2) throws SAXException {
    write(paramArrayOfchar, paramInt1, paramInt2);
  }
  
  public boolean ignoring() {
    return (this.ignoringLevel > 0);
  }
  
  final boolean inElement() {
    int i;
    for (i = this.nesting; i > 0 && this.workStack[i - 1] == null; i -= 2);
    return (i != 0);
  }
  
  public boolean isStableSourceLocation() {
    return false;
  }
  
  public void linefeedFromParser() {
    if (inElement() && checkWriteAtomic())
      this.base.write(10); 
  }
  
  public MappingInfo lookupNamespaceBinding(String paramString, char[] paramArrayOfchar, int paramInt1, int paramInt2, int paramInt3, NamespaceBinding paramNamespaceBinding) {
    if (paramString != null)
      paramInt3 = paramString.hashCode() ^ paramInt3; 
    int i = paramInt3 & this.mappingTableMask;
    MappingInfo mappingInfo = this.mappingTable[i];
    while (true) {
      String str1;
      String str2;
      if (mappingInfo == null) {
        MappingInfo mappingInfo2 = new MappingInfo();
        mappingInfo2.nextInBucket = this.mappingTable[i];
        this.mappingTable[i] = mappingInfo2;
        str2 = (new String(paramArrayOfchar, paramInt1, paramInt2)).intern();
        mappingInfo2.tagHash = paramInt3;
        mappingInfo2.prefix = paramString;
        mappingInfo2.local = str2;
        mappingInfo2.uri = str2;
        str1 = str2;
        if (str2 == "")
          str1 = null; 
        mappingInfo2.namespaces = new NamespaceBinding(paramString, str1, paramNamespaceBinding);
        return mappingInfo2;
      } 
      if (((MappingInfo)str2).tagHash == paramInt3 && ((MappingInfo)str2).prefix == paramString) {
        NamespaceBinding namespaceBinding = ((MappingInfo)str2).namespaces;
        if (namespaceBinding != null && namespaceBinding.getNext() == this.namespaceBindings && namespaceBinding.getPrefix() == paramString && MappingInfo.equals(((MappingInfo)str2).uri, (char[])str1, paramInt1, paramInt2))
          return (MappingInfo)str2; 
      } 
      MappingInfo mappingInfo1 = ((MappingInfo)str2).nextInBucket;
    } 
  }
  
  MappingInfo lookupTag(Symbol paramSymbol) {
    String str3 = paramSymbol.getLocalPart();
    String str2 = paramSymbol.getPrefix();
    String str1 = str2;
    if (str2 == "")
      str1 = null; 
    String str4 = paramSymbol.getNamespaceURI();
    int i = MappingInfo.hash(str1, str3);
    int j = i & this.mappingTableMask;
    MappingInfo mappingInfo2 = this.mappingTable[j];
    for (MappingInfo mappingInfo1 = mappingInfo2;; mappingInfo1 = mappingInfo1.nextInBucket) {
      if (mappingInfo1 == null) {
        mappingInfo1 = new MappingInfo();
        mappingInfo1.qname = paramSymbol;
        mappingInfo1.prefix = str1;
        mappingInfo1.uri = str4;
        mappingInfo1.local = str3;
        mappingInfo1.tagHash = i;
        mappingInfo1.nextInBucket = mappingInfo2;
        this.mappingTable[j] = mappingInfo2;
        return mappingInfo1;
      } 
      if (paramSymbol == mappingInfo1.qname)
        return mappingInfo1; 
      if (str3 == mappingInfo1.local && mappingInfo1.qname == null && (str4 == mappingInfo1.uri || mappingInfo1.uri == null) && str1 == mappingInfo1.prefix) {
        mappingInfo1.uri = str4;
        mappingInfo1.qname = paramSymbol;
        return mappingInfo1;
      } 
    } 
  }
  
  MappingInfo lookupTag(char[] paramArrayOfchar, int paramInt1, int paramInt2) {
    int j = 0;
    int m = 0;
    int k = -1;
    int i;
    for (i = 0; i < paramInt2; i++) {
      char c = paramArrayOfchar[paramInt1 + i];
      if (c == ':' && k < 0) {
        k = i;
        c = Character.MIN_VALUE;
        m = j;
        j = c;
      } else {
        j = j * 31 + c;
      } 
    } 
    j ^= m;
    i = j & this.mappingTableMask;
    MappingInfo mappingInfo2 = this.mappingTable[i];
    for (MappingInfo mappingInfo1 = mappingInfo2;; mappingInfo1 = mappingInfo1.nextInBucket) {
      if (mappingInfo1 == null) {
        mappingInfo1 = new MappingInfo();
        mappingInfo1.tagHash = j;
        if (k >= 0) {
          mappingInfo1.prefix = (new String(paramArrayOfchar, paramInt1, k)).intern();
          j = k + 1;
          mappingInfo1.local = (new String(paramArrayOfchar, paramInt1 + j, paramInt2 - j)).intern();
          mappingInfo1.nextInBucket = mappingInfo2;
          this.mappingTable[i] = mappingInfo2;
          return mappingInfo1;
        } 
        mappingInfo1.prefix = null;
        mappingInfo1.local = (new String(paramArrayOfchar, paramInt1, paramInt2)).intern();
        mappingInfo1.nextInBucket = mappingInfo2;
        this.mappingTable[i] = mappingInfo2;
        return mappingInfo1;
      } 
      if (j == mappingInfo1.tagHash && mappingInfo1.match(paramArrayOfchar, paramInt1, paramInt2))
        return mappingInfo1; 
    } 
  }
  
  public void processingInstruction(String paramString1, String paramString2) {
    char[] arrayOfChar = paramString2.toCharArray();
    processingInstructionCommon(paramString1, arrayOfChar, 0, arrayOfChar.length);
  }
  
  void processingInstructionCommon(String paramString, char[] paramArrayOfchar, int paramInt1, int paramInt2) {
    if (this.stringizingLevel == 0) {
      closeStartTag();
      if (this.base instanceof XConsumer)
        ((XConsumer)this.base).writeProcessingInstruction(paramString, paramArrayOfchar, paramInt1, paramInt2); 
      return;
    } 
    if (this.stringizingElementNesting < 0) {
      this.base.write(paramArrayOfchar, paramInt1, paramInt2);
      return;
    } 
  }
  
  public void processingInstructionFromParser(char[] paramArrayOfchar, int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
    if (paramInt2 == 3 && !inElement() && paramArrayOfchar[paramInt1] == 'x' && paramArrayOfchar[paramInt1 + 1] == 'm' && paramArrayOfchar[paramInt1 + 2] == 'l')
      return; 
    processingInstructionCommon(new String(paramArrayOfchar, paramInt1, paramInt2), paramArrayOfchar, paramInt3, paramInt4);
  }
  
  public void setDocumentLocator(Locator paramLocator) {
    if (paramLocator instanceof SourceLocator)
      this.locator = (SourceLocator)paramLocator; 
  }
  
  public void setMessages(SourceMessages paramSourceMessages) {
    this.messages = paramSourceMessages;
  }
  
  public void setSourceLocator(LineBufferedReader paramLineBufferedReader) {
    this.in = paramLineBufferedReader;
    this.locator = this;
  }
  
  public void setSourceLocator(SourceLocator paramSourceLocator) {
    this.locator = paramSourceLocator;
  }
  
  public void skippedEntity(String paramString) {}
  
  public void startAttribute(Object paramObject) {
    this.previous = 0;
    if (paramObject instanceof Symbol) {
      Symbol symbol = (Symbol)paramObject;
      String str1 = symbol.getLocalPart();
      this.attrLocalName = str1;
      this.attrPrefix = symbol.getPrefix();
      String str2 = symbol.getNamespaceURI();
      if (str2 == "http://www.w3.org/2000/xmlns/" || (str2 == "" && str1 == "xmlns"))
        error('e', "arttribute name cannot be 'xmlns' or in xmlns namespace"); 
    } 
    if (this.nesting == 2 && this.workStack[1] == null)
      error('e', "attribute not allowed at document level"); 
    if (this.attrCount < 0 && this.nesting > 0)
      error('e', "attribute '" + paramObject + "' follows non-attribute content"); 
    if (!startAttributeCommon())
      return; 
    this.workStack[this.nesting + this.attrCount - 1] = paramObject;
    if (this.nesting == 0) {
      this.base.startAttribute(paramObject);
      return;
    } 
    this.tlist.startAttribute(0);
  }
  
  public void startDocument() {
    closeStartTag();
    if (this.stringizingLevel > 0) {
      writeJoiner();
      return;
    } 
    if (this.nesting == 0) {
      this.base.startDocument();
    } else {
      writeJoiner();
    } 
    ensureSpaceInWorkStack(this.nesting);
    this.workStack[this.nesting] = this.namespaceBindings;
    this.workStack[this.nesting + 1] = null;
    this.nesting += 2;
  }
  
  public void startElement(Object paramObject) {
    startElementCommon();
    if (this.stringizingLevel == 0) {
      ensureSpaceInWorkStack(this.nesting - 1);
      this.workStack[this.nesting - 1] = paramObject;
      if (this.copyNamespacesMode == 0) {
        this.namespaceBindings = NamespaceBinding.predefinedXML;
        return;
      } 
    } else {
      return;
    } 
    if (this.copyNamespacesMode == 1 || this.nesting == 2) {
      if (paramObject instanceof XName) {
        paramObject = ((XName)paramObject).getNamespaceNodes();
      } else {
        paramObject = NamespaceBinding.predefinedXML;
      } 
      this.namespaceBindings = (NamespaceBinding)paramObject;
      return;
    } 
    int i = 2;
    while (true) {
      NamespaceBinding namespaceBinding;
      if (i == this.nesting) {
        namespaceBinding = null;
      } else if (this.workStack[i + 1] != null) {
        namespaceBinding = (NamespaceBinding)this.workStack[i];
      } else {
        i += 2;
        continue;
      } 
      if (namespaceBinding == null) {
        if (paramObject instanceof XName) {
          paramObject = ((XName)paramObject).getNamespaceNodes();
        } else {
          paramObject = NamespaceBinding.predefinedXML;
        } 
        this.namespaceBindings = (NamespaceBinding)paramObject;
        return;
      } 
      if (this.copyNamespacesMode == 2) {
        this.namespaceBindings = namespaceBinding;
        return;
      } 
      if (paramObject instanceof XName) {
        paramObject = ((XName)paramObject).getNamespaceNodes();
        if (NamespaceBinding.commonAncestor(namespaceBinding, (NamespaceBinding)paramObject) == namespaceBinding) {
          this.namespaceBindings = (NamespaceBinding)paramObject;
          return;
        } 
        this.namespaceBindings = mergeHelper(namespaceBinding, (NamespaceBinding)paramObject);
        return;
      } 
      this.namespaceBindings = namespaceBinding;
      return;
    } 
  }
  
  public void startElement(String paramString1, String paramString2, String paramString3, Attributes paramAttributes) {
    startElement(Symbol.make(paramString1, paramString2));
    int j = paramAttributes.getLength();
    int i;
    for (i = 0; i < j; i++) {
      startAttribute(Symbol.make(paramAttributes.getURI(i), paramAttributes.getLocalName(i)));
      write(paramAttributes.getValue(i));
      endAttribute();
    } 
  }
  
  public void startElement(String paramString, AttributeList paramAttributeList) {
    startElement(paramString.intern());
    int j = paramAttributeList.getLength();
    for (int i = 0; i < j; i++) {
      paramString = paramAttributeList.getName(i).intern();
      paramAttributeList.getType(i);
      String str = paramAttributeList.getValue(i);
      startAttribute(paramString);
      write(str);
      endAttribute();
    } 
  }
  
  protected void startElementCommon() {
    closeStartTag();
    if (this.stringizingLevel == 0) {
      ensureSpaceInWorkStack(this.nesting);
      this.workStack[this.nesting] = this.namespaceBindings;
      this.tlist.startElement(0);
      this.base = (Consumer)this.tlist;
      this.attrCount = 0;
    } else {
      if (this.previous == 2 && this.stringizingElementNesting < 0)
        write(32); 
      this.previous = 0;
      if (this.stringizingElementNesting < 0)
        this.stringizingElementNesting = this.nesting; 
    } 
    this.nesting += 2;
  }
  
  public void startPrefixMapping(String paramString1, String paramString2) {
    this.namespaceBindings = findNamespaceBinding(paramString1.intern(), paramString2.intern(), this.namespaceBindings);
  }
  
  public void textFromParser(char[] paramArrayOfchar, int paramInt1, int paramInt2) {
    if (!inElement()) {
      int i = 0;
      while (true) {
        if (i != paramInt2) {
          if (!Character.isWhitespace(paramArrayOfchar[paramInt1 + i])) {
            error('e', "text at document level");
            return;
          } 
          i++;
          continue;
        } 
        return;
      } 
    } 
    if (paramInt2 > 0 && checkWriteAtomic()) {
      this.base.write(paramArrayOfchar, paramInt1, paramInt2);
      return;
    } 
  }
  
  public void write(int paramInt) {
    if (checkWriteAtomic())
      this.base.write(paramInt); 
  }
  
  public void write(CharSequence paramCharSequence, int paramInt1, int paramInt2) {
    if (paramInt2 == 0) {
      writeJoiner();
      return;
    } 
    if (checkWriteAtomic()) {
      this.base.write(paramCharSequence, paramInt1, paramInt2);
      return;
    } 
  }
  
  public void write(String paramString) {
    write(paramString, 0, paramString.length());
  }
  
  public void write(char[] paramArrayOfchar, int paramInt1, int paramInt2) {
    if (paramInt2 == 0) {
      writeJoiner();
      return;
    } 
    if (checkWriteAtomic()) {
      this.base.write(paramArrayOfchar, paramInt1, paramInt2);
      return;
    } 
  }
  
  public void writeBoolean(boolean paramBoolean) {
    if (checkWriteAtomic())
      this.base.writeBoolean(paramBoolean); 
  }
  
  public void writeCDATA(char[] paramArrayOfchar, int paramInt1, int paramInt2) {
    if (checkWriteAtomic()) {
      if (this.base instanceof XConsumer) {
        ((XConsumer)this.base).writeCDATA(paramArrayOfchar, paramInt1, paramInt2);
        return;
      } 
    } else {
      return;
    } 
    write(paramArrayOfchar, paramInt1, paramInt2);
  }
  
  public void writeComment(char[] paramArrayOfchar, int paramInt1, int paramInt2) {
    checkValidComment(paramArrayOfchar, paramInt1, paramInt2);
    commentFromParser(paramArrayOfchar, paramInt1, paramInt2);
  }
  
  public void writeDocumentUri(Object paramObject) {
    if (this.nesting == 2 && this.base instanceof TreeList)
      ((TreeList)this.base).writeDocumentUri(paramObject); 
  }
  
  public void writeDouble(double paramDouble) {
    if (checkWriteAtomic())
      this.base.writeDouble(paramDouble); 
  }
  
  public void writeFloat(float paramFloat) {
    if (checkWriteAtomic())
      this.base.writeFloat(paramFloat); 
  }
  
  public void writeInt(int paramInt) {
    if (checkWriteAtomic())
      this.base.writeInt(paramInt); 
  }
  
  protected void writeJoiner() {
    this.previous = 0;
    if (this.ignoringLevel == 0)
      ((TreeList)this.base).writeJoiner(); 
  }
  
  public void writeLong(long paramLong) {
    if (checkWriteAtomic())
      this.base.writeLong(paramLong); 
  }
  
  public void writeObject(Object paramObject) {
    if (this.ignoringLevel <= 0) {
      if (paramObject instanceof SeqPosition) {
        paramObject = paramObject;
        writePosition(((SeqPosition)paramObject).sequence, paramObject.getPos());
        return;
      } 
      if (paramObject instanceof TreeList) {
        ((TreeList)paramObject).consume((Consumer)this);
        return;
      } 
      if (paramObject instanceof List && !(paramObject instanceof gnu.lists.CharSeq)) {
        paramObject = ((List)paramObject).iterator();
        int i = 0;
        while (true) {
          if (paramObject.hasNext()) {
            writeObject(paramObject.next());
            i++;
            continue;
          } 
          return;
        } 
      } 
      if (paramObject instanceof Keyword) {
        startAttribute(((Keyword)paramObject).asSymbol());
        this.previous = 1;
        return;
      } 
      closeStartTag();
      if (paramObject instanceof gnu.lists.UnescapedData) {
        this.base.writeObject(paramObject);
        this.previous = 0;
        return;
      } 
      if (this.previous == 2)
        write(32); 
      TextUtils.textValue(paramObject, (Consumer)this);
      this.previous = 2;
      return;
    } 
  }
  
  public void writePosition(AbstractSequence paramAbstractSequence, int paramInt) {
    if (this.ignoringLevel <= 0) {
      if (this.stringizingLevel > 0 && this.previous == 2) {
        if (this.stringizingElementNesting < 0)
          write(32); 
        this.previous = 0;
      } 
      paramAbstractSequence.consumeNext(paramInt, (Consumer)this);
      if (this.stringizingLevel > 0 && this.stringizingElementNesting < 0) {
        this.previous = 2;
        return;
      } 
    } 
  }
  
  public void writeProcessingInstruction(String paramString, char[] paramArrayOfchar, int paramInt1, int paramInt2) {
    paramString = TextUtils.replaceWhitespace(paramString, true);
    int i = paramInt1 + paramInt2;
    label22: while (true) {
      int j = i - 1;
      if (j >= paramInt1) {
        char c = paramArrayOfchar[j];
        while (true) {
          i = j;
          if (c == '>') {
            int k = j - 1;
            i = k;
            if (k >= paramInt1) {
              i = paramArrayOfchar[k];
              int m = i;
              j = k;
              if (i == 63) {
                error('e', "'?>' is not allowed in a processing-instruction");
                i = k;
              } 
              continue;
            } 
            continue label22;
          } 
          continue label22;
        } 
        break;
      } 
      if ("xml".equalsIgnoreCase(paramString))
        error('e', "processing-instruction target may not be 'xml' (ignoring case)"); 
      if (!XName.isNCName(paramString))
        error('e', "processing-instruction target '" + paramString + "' is not a valid Name"); 
      processingInstructionCommon(paramString, paramArrayOfchar, paramInt1, paramInt2);
      return;
    } 
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/gnu/xml/XMLFilter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */