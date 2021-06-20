package gnu.kawa.lispexpr;

import gnu.expr.Compilation;
import gnu.expr.PrimProcedure;
import gnu.kawa.xml.MakeAttribute;
import gnu.lists.LList;
import gnu.lists.Pair;
import gnu.lists.PairWithPosition;
import gnu.mapping.Namespace;
import gnu.mapping.Symbol;
import gnu.mapping.Values;
import gnu.text.Lexer;
import gnu.text.LineBufferedReader;
import gnu.text.SourceLocator;
import gnu.text.SyntaxException;
import gnu.xml.XName;
import java.io.IOException;

public class ReaderXmlElement extends ReadTableEntry {
  static final String DEFAULT_ELEMENT_NAMESPACE = "[default-element-namespace]";
  
  public static void namedEntity(LispReader paramLispReader, String paramString) {
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
      paramLispReader.error("unknown enity reference: '" + paramString + "'");
    } 
    paramLispReader.tokenBufferAppend(b);
  }
  
  public static Pair quote(Object paramObject) {
    return LList.list2(Namespace.EmptyNamespace.getSymbol("quote"), paramObject);
  }
  
  static void readCharRef(LispReader paramLispReader) throws IOException, SyntaxException {
    byte b;
    int i = paramLispReader.read();
    if (i == 120) {
      b = 16;
      i = paramLispReader.read();
    } else {
      b = 10;
    } 
    int j = 0;
    while (true) {
      if (i >= 0) {
        int k = Character.digit((char)i, b);
        if (k >= 0 && j < 134217728) {
          j = j * b + k;
          i = paramLispReader.read();
          continue;
        } 
      } 
      if (i != 59) {
        paramLispReader.unread(i);
        paramLispReader.error("invalid character reference");
        return;
      } 
      if ((j > 0 && j <= 55295) || (j >= 57344 && j <= 65533) || (j >= 65536 && j <= 1114111)) {
        paramLispReader.tokenBufferAppend(j);
        return;
      } 
      paramLispReader.error("invalid character value " + j);
      return;
    } 
  }
  
  public static Pair readContent(LispReader paramLispReader, char paramChar, Pair paramPair) throws IOException, SyntaxException {
    paramLispReader.tokenBufferLength = 0;
    boolean bool = false;
    Pair pair = paramPair;
    while (true) {
      int i;
      int j;
      Pair pair1;
      while (true) {
        Object object4 = null;
        paramPair = null;
        Object object3 = null;
        Object object5 = null;
        Object object2 = null;
        Object object1 = null;
        Object object6 = null;
        pair1 = null;
        i = paramLispReader.getLineNumber();
        j = paramLispReader.getColumnNumber();
        int k = paramLispReader.read();
        break;
      } 
      if (paramPair != null) {
        PairWithPosition pairWithPosition = PairWithPosition.make(paramPair, paramLispReader.makeNil(), null, i + 1, j);
        pair1.setCdrBackdoor(pairWithPosition);
        Object object = SYNTHETIC_LOCAL_VARIABLE_3;
      } 
    } 
  }
  
  public static Object readElementConstructor(LispReader paramLispReader, int paramInt) throws IOException, SyntaxException {
    String str;
    PairWithPosition pairWithPosition1;
    int i = paramLispReader.getLineNumber() + 1;
    int j = paramLispReader.getColumnNumber() - 2;
    Object object = readQNameExpression(paramLispReader, paramInt, true);
    if (paramLispReader.tokenBufferLength == 0) {
      str = null;
    } else {
      str = paramLispReader.tokenBufferString();
    } 
    PairWithPosition pairWithPosition3 = PairWithPosition.make(object, LList.Empty, paramLispReader.getName(), i, j);
    PairWithPosition pairWithPosition2 = pairWithPosition3;
    LList lList = LList.Empty;
    while (true) {
      String str1;
      int k = 0;
      paramInt = paramLispReader.readUnicodeChar();
      while (paramInt >= 0 && Character.isWhitespace(paramInt)) {
        paramInt = paramLispReader.read();
        k = 1;
      } 
      if (paramInt < 0 || paramInt == 62 || paramInt == 47) {
        int n = 0;
        k = n;
        int m = paramInt;
        if (paramInt == 47) {
          m = paramLispReader.read();
          if (m == 62) {
            k = 1;
          } else {
            paramLispReader.unread(m);
            k = n;
          } 
        } 
        if (!k) {
          if (m != 62) {
            paramLispReader.error("missing '>' after start element");
            return Boolean.FALSE;
          } 
          readContent(paramLispReader, '<', (Pair)pairWithPosition2);
          paramInt = paramLispReader.readUnicodeChar();
          k = paramInt;
          if (XName.isNameStart(paramInt)) {
            paramLispReader.tokenBufferLength = 0;
            k = paramInt;
            while (true) {
              paramLispReader.tokenBufferAppend(k);
              paramInt = paramLispReader.readUnicodeChar();
              k = paramInt;
              if (!XName.isNamePart(paramInt)) {
                k = paramInt;
                if (paramInt != 58) {
                  object = paramLispReader.tokenBufferString();
                  if (str == null || !object.equals(str)) {
                    str1 = paramLispReader.getName();
                    k = paramLispReader.getLineNumber();
                    m = paramLispReader.getColumnNumber();
                    n = paramLispReader.tokenBufferLength;
                    if (str == null) {
                      object = "computed start tag closed by '</" + object + ">'";
                    } else {
                      object = "'<" + str + ">' closed by '</" + object + ">'";
                    } 
                    paramLispReader.error('e', str1, k + 1, m - n, (String)object);
                  } 
                  paramLispReader.tokenBufferLength = 0;
                  k = paramInt;
                  break;
                } 
              } 
            } 
          } 
          if (skipSpace(paramLispReader, k) != 62)
            paramLispReader.error("missing '>' after end element"); 
        } 
        break;
      } 
      if (k == 0)
        paramLispReader.error("missing space before attribute"); 
      Object object2 = readQNameExpression(paramLispReader, paramInt, false);
      paramLispReader.getLineNumber();
      paramLispReader.getColumnNumber();
      paramInt = paramLispReader.tokenBufferLength;
      PairWithPosition pairWithPosition = null;
      object = pairWithPosition;
      if (paramLispReader.tokenBufferLength >= 5) {
        object = pairWithPosition;
        if (paramLispReader.tokenBuffer[0] == 'x') {
          object = pairWithPosition;
          if (paramLispReader.tokenBuffer[1] == 'm') {
            object = pairWithPosition;
            if (paramLispReader.tokenBuffer[2] == 'l') {
              object = pairWithPosition;
              if (paramLispReader.tokenBuffer[3] == 'n') {
                object = pairWithPosition;
                if (paramLispReader.tokenBuffer[4] == 's')
                  if (paramLispReader.tokenBufferLength == 5) {
                    object = "";
                  } else {
                    object = pairWithPosition;
                    if (paramLispReader.tokenBuffer[5] == ':')
                      object = new String(paramLispReader.tokenBuffer, 6, paramLispReader.tokenBufferLength - 6); 
                  }  
              } 
            } 
          } 
        } 
      } 
      if (skipSpace(paramLispReader, 32) != 61)
        paramLispReader.error("missing '=' after attribute"); 
      paramInt = skipSpace(paramLispReader, 32);
      pairWithPosition = PairWithPosition.make(MakeAttribute.makeAttribute, LList.Empty, paramLispReader.getName(), i, j);
      object2 = PairWithPosition.make(object2, LList.Empty, paramLispReader.getName(), i, j);
      paramLispReader.setCdr(pairWithPosition, object2);
      readContent(paramLispReader, (char)paramInt, (Pair)object2);
      if (object != null) {
        pairWithPosition1 = new PairWithPosition((SourceLocator)object2, Pair.make(object, object2.getCdr()), lList);
        continue;
      } 
      object = PairWithPosition.make(pairWithPosition, paramLispReader.makeNil(), null, -1, -1);
      str1.setCdrBackdoor(object);
      Object object1 = object;
    } 
    object = LList.reverseInPlace(pairWithPosition1);
    return PairWithPosition.make(MakeXmlElement.makeXml, Pair.make(object, pairWithPosition3), paramLispReader.getName(), i, j);
  }
  
  static Object readEntity(LispReader paramLispReader, int paramInt) throws IOException, SyntaxException {
    int i = paramLispReader.tokenBufferLength;
    while (true) {
      if (paramInt >= 0) {
        char c = (char)paramInt;
        if (XName.isNamePart(c)) {
          paramLispReader.tokenBufferAppend(c);
          paramInt = paramLispReader.read();
          continue;
        } 
      } 
      if (paramInt != 59) {
        paramLispReader.unread(paramInt);
        paramLispReader.error("invalid entity reference");
        return "?";
      } 
      String str = new String(paramLispReader.tokenBuffer, i, paramLispReader.tokenBufferLength - i);
      paramLispReader.tokenBufferLength = i;
      namedEntity(paramLispReader, str);
      return null;
    } 
  }
  
  static Object readEscapedExpression(LispReader paramLispReader, int paramInt) throws IOException, SyntaxException {
    if (paramInt == 40) {
      paramLispReader.unread(paramInt);
      return paramLispReader.readObject();
    } 
    LineBufferedReader lineBufferedReader = paramLispReader.getPort();
    char c = paramLispReader.pushNesting('{');
    paramInt = lineBufferedReader.getLineNumber();
    int i = lineBufferedReader.getColumnNumber();
    try {
      Pair pair2 = paramLispReader.makePair(new PrimProcedure(Compilation.typeValues.getDeclaredMethod("values", 1)), paramInt, i);
      Pair pair1 = pair2;
      ReadTable readTable = ReadTable.getCurrent();
      while (true) {
        Object object;
        int j = lineBufferedReader.getLineNumber();
        int k = lineBufferedReader.getColumnNumber();
        int m = lineBufferedReader.read();
        if (m == 125) {
          paramLispReader.tokenBufferLength = 0;
          if (pair1 == pair2.getCdr()) {
            object = pair1.getCar();
            return object;
          } 
        } else {
          if (m < 0)
            paramLispReader.eofError("unexpected EOF in list starting here", paramInt + 1, i); 
          Object object1 = paramLispReader.readValues(m, readTable.lookup(m), readTable);
          if (object1 != Values.empty) {
            object1 = paramLispReader.makePair(paramLispReader.handlePostfix(object1, readTable, j, k), j, k);
            paramLispReader.setCdr(object, object1);
            object = object1;
          } 
          continue;
        } 
        return pair2;
      } 
    } finally {
      paramLispReader.popNesting(c);
    } 
  }
  
  public static Object readQNameExpression(LispReader paramLispReader, int paramInt, boolean paramBoolean) throws IOException, SyntaxException {
    paramLispReader.getName();
    int i = paramLispReader.getLineNumber();
    int j = paramLispReader.getColumnNumber();
    paramLispReader.tokenBufferLength = 0;
    if (XName.isNameStart(paramInt)) {
      int m = -1;
      int k = paramInt;
      paramInt = m;
      while (true) {
        paramLispReader.tokenBufferAppend(k);
        m = paramLispReader.readUnicodeChar();
        if (m == 58 && paramInt < 0) {
          paramInt = paramLispReader.tokenBufferLength;
          k = m;
          continue;
        } 
        k = m;
        if (!XName.isNamePart(m)) {
          paramLispReader.unread(m);
          if (paramInt >= 0 || paramBoolean) {
            k = paramLispReader.tokenBufferLength;
            String str2 = (new String(paramLispReader.tokenBuffer, paramInt + 1, k - paramInt - 1)).intern();
            if (paramInt < 0) {
              String str = "[default-element-namespace]";
              Symbol symbol1 = Namespace.EmptyNamespace.getSymbol(str);
              return new Pair(ResolveNamespace.resolveQName, PairWithPosition.make(symbol1, new Pair(str2, LList.Empty), paramLispReader.getName(), i + 1, j));
            } 
            String str1 = (new String(paramLispReader.tokenBuffer, 0, paramInt)).intern();
            Symbol symbol = Namespace.EmptyNamespace.getSymbol(str1);
            return new Pair(ResolveNamespace.resolveQName, PairWithPosition.make(symbol, new Pair(str2, LList.Empty), paramLispReader.getName(), i + 1, j));
          } 
          return quote(Namespace.getDefaultSymbol(paramLispReader.tokenBufferString().intern()));
        } 
      } 
    } 
    if (paramInt == 123 || paramInt == 40)
      return readEscapedExpression(paramLispReader, paramInt); 
    paramLispReader.error("missing element name");
    return null;
  }
  
  static Object readXMLConstructor(LispReader paramLispReader, int paramInt, boolean paramBoolean) throws IOException, SyntaxException {
    // Byte code:
    //   0: aload_0
    //   1: invokevirtual getLineNumber : ()I
    //   4: iconst_1
    //   5: iadd
    //   6: istore #5
    //   8: aload_0
    //   9: invokevirtual getColumnNumber : ()I
    //   12: iconst_2
    //   13: isub
    //   14: istore #6
    //   16: iload_1
    //   17: bipush #33
    //   19: if_icmpne -> 289
    //   22: aload_0
    //   23: invokevirtual read : ()I
    //   26: istore_1
    //   27: iload_1
    //   28: istore #4
    //   30: iload_1
    //   31: bipush #45
    //   33: if_icmpne -> 94
    //   36: aload_0
    //   37: invokevirtual peek : ()I
    //   40: istore_1
    //   41: iload_1
    //   42: istore #4
    //   44: iload_1
    //   45: bipush #45
    //   47: if_icmpne -> 94
    //   50: aload_0
    //   51: invokevirtual skip : ()V
    //   54: aload_0
    //   55: ldc_w '-->'
    //   58: invokevirtual readDelimited : (Ljava/lang/String;)Z
    //   61: ifne -> 81
    //   64: aload_0
    //   65: bipush #102
    //   67: aload_0
    //   68: invokevirtual getName : ()Ljava/lang/String;
    //   71: iload #5
    //   73: iload #6
    //   75: ldc_w 'unexpected end-of-file in XML comment starting here - expected "-->"'
    //   78: invokevirtual error : (CLjava/lang/String;IILjava/lang/String;)V
    //   81: aload_0
    //   82: invokevirtual tokenBufferString : ()Ljava/lang/String;
    //   85: astore_0
    //   86: getstatic gnu/kawa/xml/CommentConstructor.commentConstructor : Lgnu/kawa/xml/CommentConstructor;
    //   89: aload_0
    //   90: invokestatic list2 : (Ljava/lang/Object;Ljava/lang/Object;)Lgnu/lists/Pair;
    //   93: areturn
    //   94: iload #4
    //   96: istore_1
    //   97: iload #4
    //   99: bipush #91
    //   101: if_icmpne -> 240
    //   104: aload_0
    //   105: invokevirtual read : ()I
    //   108: istore #4
    //   110: iload #4
    //   112: istore_1
    //   113: iload #4
    //   115: bipush #67
    //   117: if_icmpne -> 240
    //   120: aload_0
    //   121: invokevirtual read : ()I
    //   124: istore #4
    //   126: iload #4
    //   128: istore_1
    //   129: iload #4
    //   131: bipush #68
    //   133: if_icmpne -> 240
    //   136: aload_0
    //   137: invokevirtual read : ()I
    //   140: istore #4
    //   142: iload #4
    //   144: istore_1
    //   145: iload #4
    //   147: bipush #65
    //   149: if_icmpne -> 240
    //   152: aload_0
    //   153: invokevirtual read : ()I
    //   156: istore #4
    //   158: iload #4
    //   160: istore_1
    //   161: iload #4
    //   163: bipush #84
    //   165: if_icmpne -> 240
    //   168: aload_0
    //   169: invokevirtual read : ()I
    //   172: istore #4
    //   174: iload #4
    //   176: istore_1
    //   177: iload #4
    //   179: bipush #65
    //   181: if_icmpne -> 240
    //   184: aload_0
    //   185: invokevirtual read : ()I
    //   188: istore #4
    //   190: iload #4
    //   192: istore_1
    //   193: iload #4
    //   195: bipush #91
    //   197: if_icmpne -> 240
    //   200: aload_0
    //   201: ldc_w ']]>'
    //   204: invokevirtual readDelimited : (Ljava/lang/String;)Z
    //   207: ifne -> 227
    //   210: aload_0
    //   211: bipush #102
    //   213: aload_0
    //   214: invokevirtual getName : ()Ljava/lang/String;
    //   217: iload #5
    //   219: iload #6
    //   221: ldc_w 'unexpected end-of-file in CDATA starting here - expected "]]>"'
    //   224: invokevirtual error : (CLjava/lang/String;IILjava/lang/String;)V
    //   227: aload_0
    //   228: invokevirtual tokenBufferString : ()Ljava/lang/String;
    //   231: astore_0
    //   232: getstatic gnu/kawa/xml/MakeCDATA.makeCDATA : Lgnu/kawa/xml/MakeCDATA;
    //   235: aload_0
    //   236: invokestatic list2 : (Ljava/lang/Object;Ljava/lang/Object;)Lgnu/lists/Pair;
    //   239: areturn
    //   240: aload_0
    //   241: bipush #102
    //   243: aload_0
    //   244: invokevirtual getName : ()Ljava/lang/String;
    //   247: iload #5
    //   249: iload #6
    //   251: ldc_w ''<!' must be followed by '--' or '[CDATA[''
    //   254: invokevirtual error : (CLjava/lang/String;IILjava/lang/String;)V
    //   257: iload_1
    //   258: iflt -> 287
    //   261: iload_1
    //   262: bipush #62
    //   264: if_icmpeq -> 287
    //   267: iload_1
    //   268: bipush #10
    //   270: if_icmpeq -> 287
    //   273: iload_1
    //   274: bipush #13
    //   276: if_icmpeq -> 287
    //   279: aload_0
    //   280: invokevirtual read : ()I
    //   283: istore_1
    //   284: goto -> 257
    //   287: aconst_null
    //   288: areturn
    //   289: iload_1
    //   290: bipush #63
    //   292: if_icmpne -> 471
    //   295: aload_0
    //   296: invokevirtual readUnicodeChar : ()I
    //   299: istore #4
    //   301: iload #4
    //   303: iflt -> 317
    //   306: iload #4
    //   308: istore_1
    //   309: iload #4
    //   311: invokestatic isNameStart : (I)Z
    //   314: ifne -> 327
    //   317: aload_0
    //   318: ldc_w 'missing target after '<?''
    //   321: invokevirtual error : (Ljava/lang/String;)V
    //   324: iload #4
    //   326: istore_1
    //   327: aload_0
    //   328: iload_1
    //   329: invokevirtual tokenBufferAppend : (I)V
    //   332: aload_0
    //   333: invokevirtual readUnicodeChar : ()I
    //   336: istore #4
    //   338: iload #4
    //   340: istore_1
    //   341: iload #4
    //   343: invokestatic isNamePart : (I)Z
    //   346: ifne -> 327
    //   349: aload_0
    //   350: invokevirtual tokenBufferString : ()Ljava/lang/String;
    //   353: astore #7
    //   355: iconst_0
    //   356: istore_1
    //   357: iload #4
    //   359: iflt -> 383
    //   362: iload #4
    //   364: invokestatic isWhitespace : (I)Z
    //   367: ifeq -> 383
    //   370: iload_1
    //   371: iconst_1
    //   372: iadd
    //   373: istore_1
    //   374: aload_0
    //   375: invokevirtual read : ()I
    //   378: istore #4
    //   380: goto -> 357
    //   383: aload_0
    //   384: iload #4
    //   386: invokevirtual unread : (I)V
    //   389: aload_0
    //   390: bipush #63
    //   392: invokevirtual pushNesting : (C)C
    //   395: istore_3
    //   396: aload_0
    //   397: ldc_w '?>'
    //   400: invokevirtual readDelimited : (Ljava/lang/String;)Z
    //   403: ifne -> 423
    //   406: aload_0
    //   407: bipush #102
    //   409: aload_0
    //   410: invokevirtual getName : ()Ljava/lang/String;
    //   413: iload #5
    //   415: iload #6
    //   417: ldc_w 'unexpected end-of-file looking for "?>"'
    //   420: invokevirtual error : (CLjava/lang/String;IILjava/lang/String;)V
    //   423: aload_0
    //   424: iload_3
    //   425: invokevirtual popNesting : (C)V
    //   428: iload_1
    //   429: ifne -> 446
    //   432: aload_0
    //   433: getfield tokenBufferLength : I
    //   436: ifle -> 446
    //   439: aload_0
    //   440: ldc_w 'target must be followed by space or '?>''
    //   443: invokevirtual error : (Ljava/lang/String;)V
    //   446: aload_0
    //   447: invokevirtual tokenBufferString : ()Ljava/lang/String;
    //   450: astore_0
    //   451: getstatic gnu/kawa/xml/MakeProcInst.makeProcInst : Lgnu/kawa/xml/MakeProcInst;
    //   454: aload #7
    //   456: aload_0
    //   457: invokestatic list3 : (Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;)Lgnu/lists/Pair;
    //   460: areturn
    //   461: astore #7
    //   463: aload_0
    //   464: iload_3
    //   465: invokevirtual popNesting : (C)V
    //   468: aload #7
    //   470: athrow
    //   471: aload_0
    //   472: iload_1
    //   473: invokestatic readElementConstructor : (Lgnu/kawa/lispexpr/LispReader;I)Ljava/lang/Object;
    //   476: areturn
    // Exception table:
    //   from	to	target	type
    //   396	423	461	finally
  }
  
  static int skipSpace(LispReader paramLispReader, int paramInt) throws IOException, SyntaxException {
    while (true) {
      if (paramInt < 0 || !Character.isWhitespace(paramInt))
        return paramInt; 
      paramInt = paramLispReader.readUnicodeChar();
    } 
  }
  
  public Object read(Lexer paramLexer, int paramInt1, int paramInt2) throws IOException, SyntaxException {
    paramLexer = paramLexer;
    return readXMLConstructor((LispReader)paramLexer, paramLexer.readUnicodeChar(), false);
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/gnu/kawa/lispexpr/ReaderXmlElement.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */