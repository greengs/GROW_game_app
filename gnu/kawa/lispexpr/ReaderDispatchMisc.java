package gnu.kawa.lispexpr;

import gnu.expr.Keyword;
import gnu.kawa.reflect.Invoke;
import gnu.kawa.util.GeneralHashTable;
import gnu.lists.LList;
import gnu.lists.Pair;
import gnu.mapping.InPort;
import gnu.mapping.Procedure;
import gnu.mapping.Values;
import gnu.text.Lexer;
import gnu.text.LineBufferedReader;
import gnu.text.SyntaxException;
import java.io.IOException;
import java.util.regex.Pattern;

public class ReaderDispatchMisc extends ReadTableEntry {
  private static ReaderDispatchMisc instance = new ReaderDispatchMisc();
  
  protected int code = -1;
  
  public ReaderDispatchMisc() {}
  
  public ReaderDispatchMisc(int paramInt) {}
  
  public static ReaderDispatchMisc getInstance() {
    return instance;
  }
  
  public static Pattern readRegex(Lexer paramLexer, int paramInt1, int paramInt2) throws IOException, SyntaxException {
    // Byte code:
    //   0: aload_0
    //   1: getfield tokenBufferLength : I
    //   4: istore #6
    //   6: aload_0
    //   7: invokevirtual getPort : ()Lgnu/text/LineBufferedReader;
    //   10: astore #7
    //   12: iconst_0
    //   13: istore_3
    //   14: iconst_0
    //   15: istore #5
    //   17: aload #7
    //   19: instanceof gnu/mapping/InPort
    //   22: ifeq -> 44
    //   25: aload #7
    //   27: checkcast gnu/mapping/InPort
    //   30: getfield readState : C
    //   33: istore_3
    //   34: aload #7
    //   36: checkcast gnu/mapping/InPort
    //   39: bipush #47
    //   41: putfield readState : C
    //   44: aload #7
    //   46: invokevirtual read : ()I
    //   49: istore_2
    //   50: iload_2
    //   51: ifge -> 60
    //   54: aload_0
    //   55: ldc 'unexpected EOF in regex literal'
    //   57: invokevirtual eofError : (Ljava/lang/String;)V
    //   60: iload_2
    //   61: iload_1
    //   62: if_icmpne -> 145
    //   65: new java/lang/String
    //   68: dup
    //   69: aload_0
    //   70: getfield tokenBuffer : [C
    //   73: iload #6
    //   75: aload_0
    //   76: getfield tokenBufferLength : I
    //   79: iload #6
    //   81: isub
    //   82: invokespecial <init> : ([CII)V
    //   85: astore #8
    //   87: iload #5
    //   89: istore_1
    //   90: aload_0
    //   91: invokevirtual peek : ()I
    //   94: istore_2
    //   95: iload_2
    //   96: bipush #105
    //   98: if_icmpeq -> 327
    //   101: iload_2
    //   102: bipush #73
    //   104: if_icmpne -> 335
    //   107: goto -> 327
    //   110: aload_0
    //   111: invokevirtual skip : ()V
    //   114: goto -> 90
    //   117: astore #8
    //   119: aload_0
    //   120: iload #6
    //   122: putfield tokenBufferLength : I
    //   125: aload #7
    //   127: instanceof gnu/mapping/InPort
    //   130: ifeq -> 142
    //   133: aload #7
    //   135: checkcast gnu/mapping/InPort
    //   138: iload_3
    //   139: putfield readState : C
    //   142: aload #8
    //   144: athrow
    //   145: iload_2
    //   146: istore #4
    //   148: iload_2
    //   149: bipush #92
    //   151: if_icmpne -> 245
    //   154: aload #7
    //   156: invokevirtual read : ()I
    //   159: istore #4
    //   161: iload #4
    //   163: bipush #32
    //   165: if_icmpeq -> 192
    //   168: iload #4
    //   170: bipush #9
    //   172: if_icmpeq -> 192
    //   175: iload #4
    //   177: bipush #13
    //   179: if_icmpeq -> 192
    //   182: iload #4
    //   184: istore_2
    //   185: iload #4
    //   187: bipush #10
    //   189: if_icmpne -> 218
    //   192: iload #4
    //   194: istore_2
    //   195: aload_0
    //   196: instanceof gnu/kawa/lispexpr/LispReader
    //   199: ifeq -> 218
    //   202: aload_0
    //   203: checkcast gnu/kawa/lispexpr/LispReader
    //   206: iload #4
    //   208: invokevirtual readEscape : (I)I
    //   211: istore_2
    //   212: iload_2
    //   213: bipush #-2
    //   215: if_icmpeq -> 44
    //   218: iload_2
    //   219: ifge -> 228
    //   222: aload_0
    //   223: ldc 'unexpected EOF in regex literal'
    //   225: invokevirtual eofError : (Ljava/lang/String;)V
    //   228: iload_2
    //   229: istore #4
    //   231: iload_2
    //   232: iload_1
    //   233: if_icmpeq -> 245
    //   236: aload_0
    //   237: bipush #92
    //   239: invokevirtual tokenBufferAppend : (I)V
    //   242: iload_2
    //   243: istore #4
    //   245: aload_0
    //   246: iload #4
    //   248: invokevirtual tokenBufferAppend : (I)V
    //   251: goto -> 44
    //   254: iload_2
    //   255: invokestatic isLetter : (I)Z
    //   258: ifeq -> 293
    //   261: aload_0
    //   262: new java/lang/StringBuilder
    //   265: dup
    //   266: invokespecial <init> : ()V
    //   269: ldc 'unrecognized regex option ''
    //   271: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   274: iload_2
    //   275: i2c
    //   276: invokevirtual append : (C)Ljava/lang/StringBuilder;
    //   279: bipush #39
    //   281: invokevirtual append : (C)Ljava/lang/StringBuilder;
    //   284: invokevirtual toString : ()Ljava/lang/String;
    //   287: invokevirtual error : (Ljava/lang/String;)V
    //   290: goto -> 110
    //   293: aload #8
    //   295: iload_1
    //   296: invokestatic compile : (Ljava/lang/String;I)Ljava/util/regex/Pattern;
    //   299: astore #8
    //   301: aload_0
    //   302: iload #6
    //   304: putfield tokenBufferLength : I
    //   307: aload #7
    //   309: instanceof gnu/mapping/InPort
    //   312: ifeq -> 324
    //   315: aload #7
    //   317: checkcast gnu/mapping/InPort
    //   320: iload_3
    //   321: putfield readState : C
    //   324: aload #8
    //   326: areturn
    //   327: iload_1
    //   328: bipush #66
    //   330: ior
    //   331: istore_1
    //   332: goto -> 110
    //   335: iload_2
    //   336: bipush #115
    //   338: if_icmpeq -> 347
    //   341: iload_2
    //   342: bipush #83
    //   344: if_icmpne -> 355
    //   347: iload_1
    //   348: bipush #32
    //   350: ior
    //   351: istore_1
    //   352: goto -> 110
    //   355: iload_2
    //   356: bipush #109
    //   358: if_icmpeq -> 367
    //   361: iload_2
    //   362: bipush #77
    //   364: if_icmpne -> 254
    //   367: iload_1
    //   368: bipush #8
    //   370: ior
    //   371: istore_1
    //   372: goto -> 110
    // Exception table:
    //   from	to	target	type
    //   44	50	117	finally
    //   54	60	117	finally
    //   65	87	117	finally
    //   90	95	117	finally
    //   110	114	117	finally
    //   154	161	117	finally
    //   195	212	117	finally
    //   222	228	117	finally
    //   236	242	117	finally
    //   245	251	117	finally
    //   254	290	117	finally
    //   293	301	117	finally
  }
  
  public Object read(Lexer paramLexer, int paramInt1, int paramInt2) throws IOException, SyntaxException {
    String str;
    LineBufferedReader lineBufferedReader;
    GeneralHashTable<Integer, Object> generalHashTable;
    Object<Integer, Object> object1;
    Object<Integer, Object> object2;
    null = (LispReader)paramLexer;
    boolean bool = false;
    char c = Character.MIN_VALUE;
    if (this.code >= 0)
      paramInt1 = this.code; 
    switch (paramInt1) {
      default:
        paramLexer.error("An invalid #-construct was read.");
        return Values.empty;
      case 58:
        paramInt1 = null.tokenBufferLength;
        null.readToken(null.read(), 'P', ReadTable.getCurrent());
        paramInt2 = null.tokenBufferLength;
        str = new String(null.tokenBuffer, paramInt1, paramInt2 - paramInt1);
        null.tokenBufferLength = paramInt1;
        return Keyword.make(str.intern());
      case 92:
        return LispReader.readCharacter(null);
      case 33:
        return LispReader.readSpecial(null);
      case 84:
        return Boolean.TRUE;
      case 70:
        return Character.isDigit((char)str.peek()) ? LispReader.readSimpleVector(null, 'F') : Boolean.FALSE;
      case 83:
      case 85:
        return LispReader.readSimpleVector(null, (char)paramInt1);
      case 82:
        paramInt1 = paramInt2;
        if (paramInt2 > 36) {
          str.error("the radix " + paramInt2 + " is too big (max is 36)");
          paramInt1 = 36;
        } 
        return LispReader.readNumberWithRadix(0, null, paramInt1);
      case 88:
        return LispReader.readNumberWithRadix(0, null, 16);
      case 68:
        return LispReader.readNumberWithRadix(0, null, 10);
      case 79:
        return LispReader.readNumberWithRadix(0, null, 8);
      case 66:
        return LispReader.readNumberWithRadix(0, null, 2);
      case 69:
      case 73:
        null.tokenBufferAppend(35);
        null.tokenBufferAppend(paramInt1);
        return LispReader.readNumberWithRadix(2, null, 0);
      case 47:
        return readRegex((Lexer)str, paramInt1, paramInt2);
      case 124:
        lineBufferedReader = null.getPort();
        if (lineBufferedReader instanceof InPort) {
          c = ((InPort)lineBufferedReader).readState;
          ((InPort)lineBufferedReader).readState = '|';
        } 
        try {
          null.readNestedComment('#', '|');
          return Values.empty;
        } finally {
          if (lineBufferedReader instanceof InPort)
            ((InPort)lineBufferedReader).readState = c; 
        } 
      case 59:
        lineBufferedReader = SYNTHETIC_LOCAL_VARIABLE_7.getPort();
        c = bool;
        if (lineBufferedReader instanceof InPort) {
          c = ((InPort)lineBufferedReader).readState;
          ((InPort)lineBufferedReader).readState = ';';
        } 
        try {
          SYNTHETIC_LOCAL_VARIABLE_7.readObject();
          return Values.empty;
        } finally {
          if (lineBufferedReader instanceof InPort)
            ((InPort)lineBufferedReader).readState = c; 
        } 
      case 44:
        if (SYNTHETIC_LOCAL_VARIABLE_7.getPort().peek() == 40) {
          Object object = SYNTHETIC_LOCAL_VARIABLE_7.readObject();
          paramInt1 = LList.listLength(object, false);
          if (paramInt1 > 0 && ((Pair)object).getCar() instanceof gnu.mapping.Symbol) {
            String str1 = ((Pair)object).getCar().toString();
            Object object3 = ReadTable.getCurrent().getReaderCtor(str1);
            if (object3 == null) {
              lineBufferedReader.error("unknown reader constructor " + str1);
              return Boolean.FALSE;
            } 
            if (!(object3 instanceof Procedure) && !(object3 instanceof gnu.bytecode.Type)) {
              lineBufferedReader.error("reader constructor must be procedure or type name");
              return Boolean.FALSE;
            } 
            int i = paramInt1 - 1;
            if (object3 instanceof gnu.bytecode.Type) {
              paramInt1 = 1;
            } else {
              paramInt1 = 0;
            } 
            Object[] arrayOfObject = new Object[paramInt1 + i];
            object = ((Pair)object).getCdr();
            for (paramInt2 = 0; paramInt2 < i; paramInt2++) {
              object = object;
              arrayOfObject[paramInt1 + paramInt2] = object.getCar();
              object = object.getCdr();
            } 
            if (paramInt1 > 0) {
              arrayOfObject[0] = object3;
              try {
                return Invoke.make.applyN(arrayOfObject);
              } catch (Throwable throwable) {
                lineBufferedReader.error("caught " + throwable + " applying reader constructor " + str1);
              } 
              return Boolean.FALSE;
            } 
            return ((Procedure)object3).applyN(arrayOfObject);
          } 
        } 
        lineBufferedReader.error("a non-empty list starting with a symbol must follow #,");
        return Boolean.FALSE;
      case 61:
        object2 = (Object<Integer, Object>)SYNTHETIC_LOCAL_VARIABLE_7.readObject();
        object1 = object2;
        if (lineBufferedReader instanceof LispReader) {
          LispReader lispReader = (LispReader)lineBufferedReader;
          object1 = (Object<Integer, Object>)lispReader.sharedStructureTable;
          Object<Integer, Object> object = object1;
          if (object1 == null) {
            generalHashTable = new GeneralHashTable();
            lispReader.sharedStructureTable = generalHashTable;
          } 
          generalHashTable.put(Integer.valueOf(paramInt2), object2);
          return object2;
        } 
        return object1;
      case 35:
        break;
    } 
    if (generalHashTable instanceof LispReader) {
      GeneralHashTable<Integer, Object> generalHashTable1 = ((LispReader)generalHashTable).sharedStructureTable;
      if (generalHashTable1 != null) {
        object2 = (Object<Integer, Object>)generalHashTable1.get(Integer.valueOf(paramInt2), generalHashTable);
        Object<Integer, Object> object = object2;
        if (object2 == generalHashTable) {
          generalHashTable.error("an unrecognized #n# back-reference was read");
          return Values.empty;
        } 
        return object;
      } 
    } 
    generalHashTable.error("an unrecognized #n# back-reference was read");
    return Values.empty;
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/gnu/kawa/lispexpr/ReaderDispatchMisc.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */