package gnu.xml;

import gnu.lists.Consumer;
import gnu.text.LineBufferedReader;
import gnu.text.LineInputStreamReader;
import gnu.text.Path;
import gnu.text.SourceMessages;
import java.io.IOException;
import java.io.InputStream;

public class XMLParser {
  private static final int ATTRIBUTE_SEEN_EQ_STATE = 11;
  
  private static final int ATTRIBUTE_SEEN_NAME_STATE = 8;
  
  static final String BAD_ENCODING_SYNTAX = "bad 'encoding' declaration";
  
  static final String BAD_STANDALONE_SYNTAX = "bad 'standalone' declaration";
  
  private static final int BEGIN_ELEMENT_STATE = 2;
  
  private static final int DOCTYPE_NAME_SEEN_STATE = 16;
  
  private static final int DOCTYPE_SEEN_STATE = 13;
  
  private static final int END_ELEMENT_STATE = 4;
  
  private static final int EXPECT_NAME_MODIFIER = 1;
  
  private static final int EXPECT_RIGHT_STATE = 27;
  
  private static final int INIT_LEFT_QUEST_STATE = 30;
  
  private static final int INIT_LEFT_STATE = 34;
  
  private static final int INIT_STATE = 0;
  
  private static final int INIT_TEXT_STATE = 31;
  
  private static final int INVALID_VERSION_DECL = 35;
  
  private static final int MAYBE_ATTRIBUTE_STATE = 10;
  
  private static final int PREV_WAS_CR_STATE = 28;
  
  private static final int SAW_AMP_SHARP_STATE = 26;
  
  private static final int SAW_AMP_STATE = 25;
  
  private static final int SAW_ENTITY_REF = 6;
  
  private static final int SAW_EOF_ERROR = 37;
  
  private static final int SAW_ERROR = 36;
  
  private static final int SAW_LEFT_EXCL_MINUS_STATE = 22;
  
  private static final int SAW_LEFT_EXCL_STATE = 20;
  
  private static final int SAW_LEFT_QUEST_STATE = 21;
  
  private static final int SAW_LEFT_SLASH_STATE = 19;
  
  private static final int SAW_LEFT_STATE = 14;
  
  private static final int SKIP_SPACES_MODIFIER = 2;
  
  private static final int TEXT_STATE = 1;
  
  public static LineInputStreamReader XMLStreamReader(InputStream paramInputStream) throws IOException {
    // Byte code:
    //   0: iconst_m1
    //   1: istore_3
    //   2: new gnu/text/LineInputStreamReader
    //   5: dup
    //   6: aload_0
    //   7: invokespecial <init> : (Ljava/io/InputStream;)V
    //   10: astore #7
    //   12: aload #7
    //   14: invokevirtual getByte : ()I
    //   17: istore #4
    //   19: iload #4
    //   21: ifge -> 76
    //   24: iconst_m1
    //   25: istore_1
    //   26: iload_1
    //   27: ifge -> 85
    //   30: iconst_m1
    //   31: istore_2
    //   32: iload #4
    //   34: sipush #239
    //   37: if_icmpne -> 94
    //   40: iload_1
    //   41: sipush #187
    //   44: if_icmpne -> 94
    //   47: iload_2
    //   48: sipush #191
    //   51: if_icmpne -> 94
    //   54: aload #7
    //   56: iconst_3
    //   57: invokevirtual resetStart : (I)V
    //   60: aload #7
    //   62: ldc 'UTF-8'
    //   64: invokevirtual setCharset : (Ljava/lang/String;)V
    //   67: aload #7
    //   69: iconst_0
    //   70: invokevirtual setKeepFullLines : (Z)V
    //   73: aload #7
    //   75: areturn
    //   76: aload #7
    //   78: invokevirtual getByte : ()I
    //   81: istore_1
    //   82: goto -> 26
    //   85: aload #7
    //   87: invokevirtual getByte : ()I
    //   90: istore_2
    //   91: goto -> 32
    //   94: iload #4
    //   96: sipush #255
    //   99: if_icmpne -> 129
    //   102: iload_1
    //   103: sipush #254
    //   106: if_icmpne -> 129
    //   109: iload_2
    //   110: ifeq -> 129
    //   113: aload #7
    //   115: iconst_2
    //   116: invokevirtual resetStart : (I)V
    //   119: aload #7
    //   121: ldc 'UTF-16LE'
    //   123: invokevirtual setCharset : (Ljava/lang/String;)V
    //   126: goto -> 67
    //   129: iload #4
    //   131: sipush #254
    //   134: if_icmpne -> 164
    //   137: iload_1
    //   138: sipush #255
    //   141: if_icmpne -> 164
    //   144: iload_2
    //   145: ifeq -> 164
    //   148: aload #7
    //   150: iconst_2
    //   151: invokevirtual resetStart : (I)V
    //   154: aload #7
    //   156: ldc 'UTF-16BE'
    //   158: invokevirtual setCharset : (Ljava/lang/String;)V
    //   161: goto -> 67
    //   164: iload_2
    //   165: ifge -> 205
    //   168: iload #4
    //   170: bipush #76
    //   172: if_icmpne -> 214
    //   175: iload_1
    //   176: bipush #111
    //   178: if_icmpne -> 214
    //   181: iload_2
    //   182: sipush #167
    //   185: if_icmpne -> 214
    //   188: iload_3
    //   189: sipush #148
    //   192: if_icmpne -> 214
    //   195: new java/lang/RuntimeException
    //   198: dup
    //   199: ldc 'XMLParser: EBCDIC encodings not supported'
    //   201: invokespecial <init> : (Ljava/lang/String;)V
    //   204: athrow
    //   205: aload #7
    //   207: invokevirtual getByte : ()I
    //   210: istore_3
    //   211: goto -> 168
    //   214: aload #7
    //   216: iconst_0
    //   217: invokevirtual resetStart : (I)V
    //   220: iload #4
    //   222: bipush #60
    //   224: if_icmpne -> 259
    //   227: iload_1
    //   228: bipush #63
    //   230: if_icmpne -> 245
    //   233: iload_2
    //   234: bipush #120
    //   236: if_icmpne -> 245
    //   239: iload_3
    //   240: bipush #109
    //   242: if_icmpeq -> 280
    //   245: iload_1
    //   246: ifne -> 259
    //   249: iload_2
    //   250: bipush #63
    //   252: if_icmpne -> 259
    //   255: iload_3
    //   256: ifeq -> 280
    //   259: iload #4
    //   261: ifne -> 418
    //   264: iload_1
    //   265: bipush #60
    //   267: if_icmpne -> 418
    //   270: iload_2
    //   271: ifne -> 418
    //   274: iload_3
    //   275: bipush #63
    //   277: if_icmpne -> 418
    //   280: aload #7
    //   282: getfield buffer : [C
    //   285: astore #6
    //   287: aload #6
    //   289: astore_0
    //   290: aload #6
    //   292: ifnonnull -> 307
    //   295: sipush #8192
    //   298: newarray char
    //   300: astore_0
    //   301: aload #7
    //   303: aload_0
    //   304: putfield buffer : [C
    //   307: iconst_0
    //   308: istore_2
    //   309: iconst_0
    //   310: istore #4
    //   312: aload #7
    //   314: invokevirtual getByte : ()I
    //   317: istore #5
    //   319: iload #5
    //   321: ifeq -> 312
    //   324: iload #5
    //   326: ifge -> 344
    //   329: aload #7
    //   331: iconst_0
    //   332: putfield pos : I
    //   335: aload #7
    //   337: iload_2
    //   338: putfield limit : I
    //   341: goto -> 67
    //   344: iload_2
    //   345: iconst_1
    //   346: iadd
    //   347: istore_3
    //   348: aload_0
    //   349: iload_2
    //   350: iload #5
    //   352: sipush #255
    //   355: iand
    //   356: i2c
    //   357: castore
    //   358: iload #4
    //   360: ifne -> 403
    //   363: iload #5
    //   365: bipush #62
    //   367: if_icmpne -> 375
    //   370: iload_3
    //   371: istore_2
    //   372: goto -> 329
    //   375: iload #5
    //   377: bipush #39
    //   379: if_icmpeq -> 392
    //   382: iload #4
    //   384: istore_1
    //   385: iload #5
    //   387: bipush #34
    //   389: if_icmpne -> 395
    //   392: iload #5
    //   394: istore_1
    //   395: iload_3
    //   396: istore_2
    //   397: iload_1
    //   398: istore #4
    //   400: goto -> 312
    //   403: iload #4
    //   405: istore_1
    //   406: iload #5
    //   408: iload #4
    //   410: if_icmpne -> 395
    //   413: iconst_0
    //   414: istore_1
    //   415: goto -> 395
    //   418: aload #7
    //   420: ldc 'UTF-8'
    //   422: invokevirtual setCharset : (Ljava/lang/String;)V
    //   425: goto -> 67
  }
  
  public static void parse(LineBufferedReader paramLineBufferedReader, SourceMessages paramSourceMessages, Consumer paramConsumer) throws IOException {
    XMLFilter xMLFilter = new XMLFilter(paramConsumer);
    xMLFilter.setMessages(paramSourceMessages);
    xMLFilter.setSourceLocator(paramLineBufferedReader);
    xMLFilter.startDocument();
    Path path = paramLineBufferedReader.getPath();
    if (path != null)
      xMLFilter.writeDocumentUri(path); 
    parse(paramLineBufferedReader, xMLFilter);
    xMLFilter.endDocument();
  }
  
  public static void parse(LineBufferedReader paramLineBufferedReader, SourceMessages paramSourceMessages, XMLFilter paramXMLFilter) throws IOException {
    paramXMLFilter.setMessages(paramSourceMessages);
    paramXMLFilter.setSourceLocator(paramLineBufferedReader);
    paramXMLFilter.startDocument();
    Path path = paramLineBufferedReader.getPath();
    if (path != null)
      paramXMLFilter.writeDocumentUri(path); 
    parse(paramLineBufferedReader, paramXMLFilter);
    paramXMLFilter.endDocument();
    paramLineBufferedReader.close();
  }
  
  public static void parse(LineBufferedReader paramLineBufferedReader, XMLFilter paramXMLFilter) {
    Object object1;
    Object object2;
    Object object3;
    Object object4;
    Object object5;
    char[] arrayOfChar = paramLineBufferedReader.buffer;
    int i = paramLineBufferedReader.pos;
    int k = paramLineBufferedReader.limit;
    byte b1 = 0;
    byte b3 = 60;
    byte b2 = 14;
    char c = ' ';
    boolean bool = false;
    byte b = -1;
    Object object6 = null;
    int j = k;
    label591: while (true) {
      Object object7;
      int m;
      Object object8;
      int n;
      Object object10;
      int i3;
      Object object9;
      int i2;
      int i4;
      Object object11;
      int i5;
      Object object12;
      int i6;
      Object object14;
      boolean bool1;
      Object object13;
      char c1;
      byte b4;
      char c2;
      int i7;
      Object object15;
      byte b5;
      Object object16 = object6;
      int i1 = i;
      switch (b1) {
        default:
          object14 = object5;
          i1 = b1;
          object16 = object6;
          object15 = object4;
          object12 = object1;
          object11 = object3;
          object10 = object2;
          continue;
        case 0:
          i1 = 31;
          object10 = object2;
          object11 = object3;
          object12 = object1;
          object15 = object4;
          object16 = object6;
          object14 = object5;
          continue;
        case 31:
          if (c == '<') {
            i1 = 34;
            object10 = object2;
            object11 = object3;
            object12 = object1;
            object15 = object4;
            object16 = object6;
            object14 = object5;
            continue;
          } 
          b1 = 1;
          continue;
        case 34:
          if (c == '?') {
            int i8 = i;
            i1 = 33;
            object11 = object3;
            object12 = object1;
            object15 = object4;
            object16 = object6;
            object14 = object5;
            continue;
          } 
          b1 = 14;
          continue;
        case 35:
          object8 = object1;
          object16 = "invalid xml version specifier";
        case 36:
          paramLineBufferedReader.pos = object8;
          paramXMLFilter.error('e', (String)object16);
          while (true) {
            if (object8 >= k)
              return; 
            i = object8 + 1;
            c = arrayOfChar[object8];
            int i8 = i;
            if (c == '>') {
              i8 = 1;
              object10 = object2;
              object11 = object3;
              object12 = object1;
              object15 = object4;
              object14 = object5;
            } else {
            
            } 
          } 
          break;
        case 37:
          paramLineBufferedReader.pos = i;
          paramXMLFilter.error('f', "unexpected end-of-file");
          return;
        case 1:
          i3 = i - 1;
          for (n = i;; n = ++i) {
            paramLineBufferedReader.incrLineNumber(1, i);
            i3 = i;
          } 
          break;
        case 28:
          bool1 = true;
          i3 = 1;
          if (c == '\n') {
            b1 = 1;
          } else {
            b1 = 0;
          } 
          if (c == '') {
            n = 1;
          } else {
            n = 0;
          } 
          if ((n | b1) != 0) {
            paramLineBufferedReader.incrLineNumber(1, i);
            object9 = object2;
            object11 = object3;
            object12 = object1;
            object15 = object4;
            object16 = object6;
            n = bool1;
            Object object = object5;
            continue;
          } 
          paramLineBufferedReader.incrLineNumber(1, i - 1);
          object7 = object9;
          continue;
        case 12:
        case 15:
        case 23:
        case 29:
        case 32:
          if (c != ' ') {
            if (c == '\t') {
              object9 = object2;
              object11 = object3;
              object12 = object1;
              object15 = object4;
              object16 = object6;
              Object object17 = object7;
              Object object18 = object5;
              continue;
            } 
            if (c == '\n' || c == '\r' || c == '' || c == ' ') {
              paramLineBufferedReader.incrLineNumber(1, i);
              object9 = object2;
              object11 = object3;
              object12 = object1;
              object15 = object4;
              object16 = object6;
              Object object17 = object7;
              Object object18 = object5;
              continue;
            } 
            m = object7 - 2;
            continue;
          } 
        case 3:
        case 5:
        case 7:
        case 9:
        case 17:
        case 24:
        case 33:
          i7 = object2 + 1;
          i6 = i;
          while (true) {
            if ((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z') || c == '_' || c == ':' || (c >= 'À' && (c <= '˿' || (c >= 'Ͱ' && ((c <= '῿' && c != ';') || (c >= '‌' && (c <= '‍' || (c >= '⁰' && c <= '↏') || (c >= 'Ⰰ' && c <= '⿯') || (c >= '、' && c <= '퟿') || (c >= '豈' && c <= '�'))))))) || (i6 > i7 && c >= '0' && c <= '9') || c == '.' || c == '-' || c == '·' || (c > '̀' && (c <= 'ͯ' || (c >= '‿' && c <= '⁀')))) {
              object9 = object2;
              int i8 = i7;
              object12 = object1;
              object15 = object4;
              object16 = object6;
              i = i6;
              n = m;
              Object object = object5;
              if (i6 < k) {
                c = arrayOfChar[i6];
                i6++;
              } 
            } else {
              m--;
              n = i6 - i7;
              if (n == 0) {
                if (m == 8) {
                  object6 = "missing or invalid attribute name";
                  continue;
                } 
                if (m != 2) {
                  if (m == 4)
                    continue; 
                } else {
                  object6 = "missing or invalid element name";
                  m = 36;
                  i = i6;
                  i6 = n;
                } 
                object6 = "missing or invalid name";
              } else {
                i = i6;
                i6 = n;
                continue;
              } 
              m = 36;
              i = i6;
              i6 = n;
            } 
          } 
          break;
        case 25:
          if (c == '#') {
            n = 26;
            int i8 = i;
            boolean bool2 = false;
            boolean bool3 = false;
            object15 = object4;
            object16 = object6;
            Object object = object5;
            continue label591;
          } 
          i4 = i - 1;
          m = 7;
          continue;
        case 6:
          paramLineBufferedReader.pos = i;
          if (c != ';')
            paramXMLFilter.error('w', "missing ';'"); 
          paramXMLFilter.emitEntityReference(arrayOfChar, i4, i6);
          i2 = k;
          n = 1;
          i5 = i6;
          object12 = object1;
          object15 = object4;
          object16 = object6;
          object13 = object5;
          continue label591;
        case 14:
          if (c == '/') {
            n = 19;
            i2 = i4;
            i5 = i6;
            object12 = object1;
            object15 = object4;
            object16 = object6;
            object13 = object5;
            continue label591;
          } 
          if (c == '?') {
            i2 = i;
            n = 24;
            i5 = i6;
            object12 = object1;
            object15 = object4;
            object16 = object6;
            object13 = object5;
            continue label591;
          } 
          if (c == '!') {
            n = 20;
            i2 = i;
            i5 = i6;
            object12 = object1;
            object15 = object4;
            object16 = object6;
            object13 = object5;
            continue label591;
          } 
          i4 = i - 1;
          m = 3;
          continue;
        case 2:
          paramLineBufferedReader.pos = i - i6;
          paramXMLFilter.emitStartElement(arrayOfChar, i4, i6);
          m = 12;
          i4 = k;
          continue;
        case 21:
        case 30:
        
        case 13:
          m = 17;
          i4 = i - 1;
          continue;
        case 16:
        
        case 10:
          c1 = '<';
          b4 = 14;
          if (c == '/') {
            paramLineBufferedReader.pos = i;
            paramXMLFilter.emitEndAttributes();
            paramXMLFilter.emitEndElement(null, 0, 0);
            n = 27;
            i2 = i4;
            i5 = i6;
            object12 = object1;
            byte b6 = b4;
            object16 = object6;
            continue label591;
          } 
          if (c == '>') {
            paramLineBufferedReader.pos = i;
            paramXMLFilter.emitEndAttributes();
            n = 1;
            i2 = i4;
            i5 = i6;
            object12 = object1;
            byte b6 = b4;
            object16 = object6;
            continue label591;
          } 
          i4 = i - 1;
          m = 9;
          c2 = c1;
          continue;
        case 8:
          if (c != ' ' && c != '\t' && c != '\r' && c != '\n' && c != '') {
            if (c == ' ') {
              i2 = i4;
              i5 = i6;
              object12 = object1;
              byte b6 = b4;
              object16 = object6;
              n = m;
              c1 = c2;
              continue label591;
            } 
            paramLineBufferedReader.pos = i - i6;
            paramXMLFilter.emitStartAttribute(arrayOfChar, i4, i6);
            i2 = k;
            if (c == '=') {
              n = 11;
              i5 = i6;
              object12 = object1;
              byte b6 = b4;
              object16 = object6;
              c1 = c2;
              continue label591;
            } 
            paramXMLFilter.emitEndAttributes();
            object6 = "missing or misplaced '=' after attribute name";
            m = 36;
            i4 = i2;
            continue;
          } 
        case 11:
          if (c == '\'' || c == '"') {
            c1 = c;
            byte b6 = 12;
            n = 1;
            i2 = i4;
            i5 = i6;
            object12 = object1;
            object16 = object6;
            continue label591;
          } 
          if (c != ' ' && c != '\t' && c != '\r' && c != '\n' && c != '') {
            if (c == ' ') {
              i2 = i4;
              i5 = i6;
              object12 = object1;
              byte b6 = b4;
              object16 = object6;
              n = m;
              c1 = c2;
              continue label591;
            } 
            object6 = "missing or unquoted attribute value";
            m = 36;
            continue;
          } 
        case 19:
          i4 = i - 1;
          m = 5;
          continue;
        case 4:
          paramLineBufferedReader.pos = i;
          paramXMLFilter.emitEndElement(arrayOfChar, i4, i6);
          i4 = k;
          m = 29;
          continue;
        case 27:
          if (c != '>') {
            object6 = "missing '>'";
            m = 36;
            continue;
          } 
          n = 1;
          i2 = i4;
          i5 = i6;
          object12 = object1;
          b5 = b4;
          object16 = object6;
          c1 = c2;
          continue label591;
        case 20:
          i5 = i6;
          i6 = i;
          while (true) {
            c = arrayOfChar[i6];
            i6++;
            i5 = i7;
          } 
          break;
        case 26:
          i5 = i6;
          i6 = i;
          while (true) {
            c = arrayOfChar[i6];
            i6++;
            i5 = i7;
          } 
          break;
      } 
      continue;
      b1 = 37;
      object2 = SYNTHETIC_LOCAL_VARIABLE_9;
      object3 = SYNTHETIC_LOCAL_VARIABLE_12;
      object1 = SYNTHETIC_LOCAL_VARIABLE_13;
      object4 = SYNTHETIC_LOCAL_VARIABLE_19;
      object6 = SYNTHETIC_LOCAL_VARIABLE_21;
      object5 = SYNTHETIC_LOCAL_VARIABLE_15;
    } 
  }
  
  public static void parse(InputStream paramInputStream, Object paramObject, SourceMessages paramSourceMessages, Consumer paramConsumer) throws IOException {
    LineInputStreamReader lineInputStreamReader = XMLStreamReader(paramInputStream);
    if (paramObject != null)
      lineInputStreamReader.setName(paramObject); 
    parse((LineBufferedReader)lineInputStreamReader, paramSourceMessages, paramConsumer);
    lineInputStreamReader.close();
  }
  
  public static void parse(Object paramObject, SourceMessages paramSourceMessages, Consumer paramConsumer) throws IOException {
    parse(Path.openInputStream(paramObject), paramObject, paramSourceMessages, paramConsumer);
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/gnu/xml/XMLParser.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */