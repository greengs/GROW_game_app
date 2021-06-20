package gnu.kawa.functions;

import gnu.lists.AbstractFormat;
import gnu.lists.Array;
import gnu.lists.Consumer;
import gnu.lists.LList;
import gnu.lists.Pair;
import gnu.mapping.OutPort;
import gnu.mapping.Symbol;
import gnu.mapping.ThreadLocation;
import gnu.math.IntNum;
import gnu.text.Char;
import java.util.regex.Pattern;

public class DisplayFormat extends AbstractFormat {
  public static final ThreadLocation outBase = new ThreadLocation("out-base");
  
  public static final ThreadLocation outRadix = new ThreadLocation("out-radix");
  
  static Pattern r5rsIdentifierMinusInteriorColons = Pattern.compile("(([a-zA-Z]|[!$%&*/:<=>?^_~])([a-zA-Z]|[!$%&*/<=>?^_~]|[0-9]|([-+.@]))*[:]?)|([-+]|[.][.][.])");
  
  char language;
  
  boolean readable;
  
  public DisplayFormat(boolean paramBoolean, char paramChar) {
    this.readable = paramBoolean;
    this.language = paramChar;
  }
  
  public static DisplayFormat getCommonLispFormat(boolean paramBoolean) {
    return new DisplayFormat(paramBoolean, 'C');
  }
  
  public static DisplayFormat getEmacsLispFormat(boolean paramBoolean) {
    return new DisplayFormat(paramBoolean, 'E');
  }
  
  public static DisplayFormat getSchemeFormat(boolean paramBoolean) {
    return new DisplayFormat(paramBoolean, 'S');
  }
  
  public boolean getReadableOutput() {
    return this.readable;
  }
  
  int write(Array paramArray, int paramInt1, int paramInt2, Consumer paramConsumer) {
    String str;
    int j = paramArray.rank();
    int i = 0;
    boolean bool = false;
    if (paramInt2 > 0) {
      str = "(";
    } else if (j == 1) {
      str = "#(";
    } else {
      str = "#" + j + "a(";
    } 
    if (paramConsumer instanceof OutPort) {
      ((OutPort)paramConsumer).startLogicalBlock(str, false, ")");
    } else {
      write(str, paramConsumer);
    } 
    if (j > 0) {
      int m = paramArray.getSize(paramInt2);
      int n = paramInt2 + 1;
      paramInt2 = 0;
      int k = paramInt1;
      paramInt1 = bool;
      while (true) {
        i = paramInt1;
        if (paramInt2 < m) {
          if (paramInt2 > 0) {
            write(" ", paramConsumer);
            if (paramConsumer instanceof OutPort)
              ((OutPort)paramConsumer).writeBreakFill(); 
          } 
          if (n == j) {
            writeObject(paramArray.getRowMajor(k), paramConsumer);
            i = 1;
          } else {
            i = write(paramArray, k, n, paramConsumer);
          } 
          k += i;
          paramInt1 += i;
          paramInt2++;
          continue;
        } 
        break;
      } 
    } 
    if (paramConsumer instanceof OutPort) {
      ((OutPort)paramConsumer).endLogicalBlock(")");
      return i;
    } 
    write(")", paramConsumer);
    return i;
  }
  
  public void write(int paramInt, Consumer paramConsumer) {
    if (!getReadableOutput()) {
      Char.print(paramInt, paramConsumer);
      return;
    } 
    if (this.language == 'E' && paramInt > 32) {
      paramConsumer.write(63);
      Char.print(paramInt, paramConsumer);
      return;
    } 
    write(Char.toScmReadableString(paramInt), paramConsumer);
  }
  
  public void writeBoolean(boolean paramBoolean, Consumer paramConsumer) {
    String str;
    if (this.language == 'S') {
      if (paramBoolean) {
        str = "#t";
      } else {
        str = "#f";
      } 
    } else if (paramBoolean) {
      str = "t";
    } else {
      str = "nil";
    } 
    write(str, paramConsumer);
  }
  
  public void writeList(LList paramLList, OutPort paramOutPort) {
    Object object = paramLList;
    paramOutPort.startLogicalBlock("(", false, ")");
    while (object instanceof Pair) {
      if (object != paramLList)
        paramOutPort.writeSpaceFill(); 
      Pair pair = (Pair)object;
      writeObject(pair.getCar(), (Consumer)paramOutPort);
      object = pair.getCdr();
    } 
    if (object != LList.Empty) {
      paramOutPort.writeSpaceFill();
      paramOutPort.write(". ");
      writeObject(LList.checkNonList(object), (Consumer)paramOutPort);
    } 
    paramOutPort.endLogicalBlock(")");
  }
  
  public void writeObject(Object paramObject, Consumer paramConsumer) {
    // Byte code:
    //   0: iconst_0
    //   1: istore #4
    //   3: iload #4
    //   5: istore_3
    //   6: aload_2
    //   7: instanceof gnu/mapping/OutPort
    //   10: ifeq -> 79
    //   13: iload #4
    //   15: istore_3
    //   16: aload_1
    //   17: instanceof gnu/kawa/xml/UntypedAtomic
    //   20: ifne -> 79
    //   23: iload #4
    //   25: istore_3
    //   26: aload_1
    //   27: instanceof gnu/mapping/Values
    //   30: ifne -> 79
    //   33: aload_0
    //   34: invokevirtual getReadableOutput : ()Z
    //   37: ifne -> 70
    //   40: iload #4
    //   42: istore_3
    //   43: aload_1
    //   44: instanceof gnu/text/Char
    //   47: ifne -> 79
    //   50: iload #4
    //   52: istore_3
    //   53: aload_1
    //   54: instanceof java/lang/CharSequence
    //   57: ifne -> 79
    //   60: iload #4
    //   62: istore_3
    //   63: aload_1
    //   64: instanceof java/lang/Character
    //   67: ifne -> 79
    //   70: aload_2
    //   71: checkcast gnu/mapping/OutPort
    //   74: invokevirtual writeWordStart : ()V
    //   77: iconst_1
    //   78: istore_3
    //   79: aload_0
    //   80: aload_1
    //   81: aload_2
    //   82: invokevirtual writeObjectRaw : (Ljava/lang/Object;Lgnu/lists/Consumer;)V
    //   85: iload_3
    //   86: ifeq -> 96
    //   89: aload_2
    //   90: checkcast gnu/mapping/OutPort
    //   93: invokevirtual writeWordEnd : ()V
    //   96: return
  }
  
  public void writeObjectRaw(Object paramObject, Consumer paramConsumer) {
    // Byte code:
    //   0: aload_1
    //   1: instanceof java/lang/Boolean
    //   4: ifeq -> 20
    //   7: aload_0
    //   8: aload_1
    //   9: checkcast java/lang/Boolean
    //   12: invokevirtual booleanValue : ()Z
    //   15: aload_2
    //   16: invokevirtual writeBoolean : (ZLgnu/lists/Consumer;)V
    //   19: return
    //   20: aload_1
    //   21: instanceof gnu/text/Char
    //   24: ifeq -> 40
    //   27: aload_0
    //   28: aload_1
    //   29: checkcast gnu/text/Char
    //   32: invokevirtual intValue : ()I
    //   35: aload_2
    //   36: invokevirtual write : (ILgnu/lists/Consumer;)V
    //   39: return
    //   40: aload_1
    //   41: instanceof java/lang/Character
    //   44: ifeq -> 60
    //   47: aload_0
    //   48: aload_1
    //   49: checkcast java/lang/Character
    //   52: invokevirtual charValue : ()C
    //   55: aload_2
    //   56: invokevirtual write : (ILgnu/lists/Consumer;)V
    //   59: return
    //   60: aload_1
    //   61: instanceof gnu/mapping/Symbol
    //   64: ifeq -> 110
    //   67: aload_1
    //   68: checkcast gnu/mapping/Symbol
    //   71: astore_1
    //   72: aload_1
    //   73: invokevirtual getNamespace : ()Lgnu/mapping/Namespace;
    //   76: getstatic gnu/kawa/xml/XmlNamespace.HTML : Lgnu/kawa/xml/XmlNamespace;
    //   79: if_acmpne -> 99
    //   82: aload_0
    //   83: ldc 'html:'
    //   85: aload_2
    //   86: invokevirtual write : (Ljava/lang/String;Lgnu/lists/Consumer;)V
    //   89: aload_0
    //   90: aload_1
    //   91: invokevirtual getLocalPart : ()Ljava/lang/String;
    //   94: aload_2
    //   95: invokevirtual write : (Ljava/lang/String;Lgnu/lists/Consumer;)V
    //   98: return
    //   99: aload_0
    //   100: aload_1
    //   101: aload_2
    //   102: aload_0
    //   103: getfield readable : Z
    //   106: invokevirtual writeSymbol : (Lgnu/mapping/Symbol;Lgnu/lists/Consumer;Z)V
    //   109: return
    //   110: aload_1
    //   111: instanceof java/net/URI
    //   114: ifeq -> 159
    //   117: aload_0
    //   118: invokevirtual getReadableOutput : ()Z
    //   121: ifeq -> 159
    //   124: aload_2
    //   125: instanceof java/io/PrintWriter
    //   128: ifeq -> 159
    //   131: aload_0
    //   132: ldc '#,(URI '
    //   134: aload_2
    //   135: invokevirtual write : (Ljava/lang/String;Lgnu/lists/Consumer;)V
    //   138: aload_1
    //   139: invokevirtual toString : ()Ljava/lang/String;
    //   142: aload_2
    //   143: checkcast java/io/PrintWriter
    //   146: iconst_1
    //   147: invokestatic printQuoted : (Ljava/lang/CharSequence;Ljava/io/PrintWriter;I)V
    //   150: aload_2
    //   151: bipush #41
    //   153: invokeinterface write : (I)V
    //   158: return
    //   159: aload_1
    //   160: instanceof java/lang/CharSequence
    //   163: ifeq -> 280
    //   166: aload_1
    //   167: checkcast java/lang/CharSequence
    //   170: astore #6
    //   172: aload_0
    //   173: invokevirtual getReadableOutput : ()Z
    //   176: ifeq -> 197
    //   179: aload_2
    //   180: instanceof java/io/PrintWriter
    //   183: ifeq -> 197
    //   186: aload #6
    //   188: aload_2
    //   189: checkcast java/io/PrintWriter
    //   192: iconst_1
    //   193: invokestatic printQuoted : (Ljava/lang/CharSequence;Ljava/io/PrintWriter;I)V
    //   196: return
    //   197: aload_1
    //   198: instanceof java/lang/String
    //   201: ifeq -> 215
    //   204: aload_2
    //   205: aload_1
    //   206: checkcast java/lang/String
    //   209: invokeinterface write : (Ljava/lang/String;)V
    //   214: return
    //   215: aload_1
    //   216: instanceof gnu/lists/CharSeq
    //   219: ifeq -> 242
    //   222: aload_1
    //   223: checkcast gnu/lists/CharSeq
    //   226: astore_1
    //   227: aload_1
    //   228: iconst_0
    //   229: aload_1
    //   230: invokeinterface size : ()I
    //   235: aload_2
    //   236: invokeinterface consume : (IILgnu/lists/Consumer;)V
    //   241: return
    //   242: aload #6
    //   244: invokeinterface length : ()I
    //   249: istore #4
    //   251: iconst_0
    //   252: istore_3
    //   253: iload_3
    //   254: iload #4
    //   256: if_icmpge -> 19
    //   259: aload_2
    //   260: aload #6
    //   262: iload_3
    //   263: invokeinterface charAt : (I)C
    //   268: invokeinterface write : (I)V
    //   273: iload_3
    //   274: iconst_1
    //   275: iadd
    //   276: istore_3
    //   277: goto -> 253
    //   280: aload_1
    //   281: instanceof gnu/lists/LList
    //   284: ifeq -> 307
    //   287: aload_2
    //   288: instanceof gnu/mapping/OutPort
    //   291: ifeq -> 307
    //   294: aload_0
    //   295: aload_1
    //   296: checkcast gnu/lists/LList
    //   299: aload_2
    //   300: checkcast gnu/mapping/OutPort
    //   303: invokevirtual writeList : (Lgnu/lists/LList;Lgnu/mapping/OutPort;)V
    //   306: return
    //   307: aload_1
    //   308: instanceof gnu/lists/SimpleVector
    //   311: ifeq -> 490
    //   314: aload_1
    //   315: checkcast gnu/lists/SimpleVector
    //   318: astore #7
    //   320: aload #7
    //   322: invokevirtual getTag : ()Ljava/lang/String;
    //   325: astore_1
    //   326: aload_0
    //   327: getfield language : C
    //   330: bipush #69
    //   332: if_icmpne -> 424
    //   335: ldc_w '['
    //   338: astore_1
    //   339: ldc_w ']'
    //   342: astore #6
    //   344: aload_2
    //   345: instanceof gnu/mapping/OutPort
    //   348: ifeq -> 466
    //   351: aload_2
    //   352: checkcast gnu/mapping/OutPort
    //   355: aload_1
    //   356: iconst_0
    //   357: aload #6
    //   359: invokevirtual startLogicalBlock : (Ljava/lang/String;ZLjava/lang/String;)V
    //   362: aload #7
    //   364: invokevirtual size : ()I
    //   367: istore #4
    //   369: iconst_0
    //   370: istore_3
    //   371: iload_3
    //   372: iload #4
    //   374: iconst_1
    //   375: ishl
    //   376: if_icmpge -> 407
    //   379: iload_3
    //   380: ifle -> 397
    //   383: aload_2
    //   384: instanceof gnu/mapping/OutPort
    //   387: ifeq -> 397
    //   390: aload_2
    //   391: checkcast gnu/mapping/OutPort
    //   394: invokevirtual writeSpaceFill : ()V
    //   397: aload #7
    //   399: iload_3
    //   400: aload_2
    //   401: invokevirtual consumeNext : (ILgnu/lists/Consumer;)Z
    //   404: ifne -> 475
    //   407: aload_2
    //   408: instanceof gnu/mapping/OutPort
    //   411: ifeq -> 482
    //   414: aload_2
    //   415: checkcast gnu/mapping/OutPort
    //   418: aload #6
    //   420: invokevirtual endLogicalBlock : (Ljava/lang/String;)V
    //   423: return
    //   424: aload_1
    //   425: ifnonnull -> 438
    //   428: ldc '#('
    //   430: astore_1
    //   431: ldc ')'
    //   433: astore #6
    //   435: goto -> 344
    //   438: new java/lang/StringBuilder
    //   441: dup
    //   442: invokespecial <init> : ()V
    //   445: ldc '#'
    //   447: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   450: aload_1
    //   451: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   454: ldc '('
    //   456: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   459: invokevirtual toString : ()Ljava/lang/String;
    //   462: astore_1
    //   463: goto -> 431
    //   466: aload_0
    //   467: aload_1
    //   468: aload_2
    //   469: invokevirtual write : (Ljava/lang/String;Lgnu/lists/Consumer;)V
    //   472: goto -> 362
    //   475: iload_3
    //   476: iconst_2
    //   477: iadd
    //   478: istore_3
    //   479: goto -> 371
    //   482: aload_0
    //   483: aload #6
    //   485: aload_2
    //   486: invokevirtual write : (Ljava/lang/String;Lgnu/lists/Consumer;)V
    //   489: return
    //   490: aload_1
    //   491: instanceof gnu/lists/Array
    //   494: ifeq -> 510
    //   497: aload_0
    //   498: aload_1
    //   499: checkcast gnu/lists/Array
    //   502: iconst_0
    //   503: iconst_0
    //   504: aload_2
    //   505: invokevirtual write : (Lgnu/lists/Array;IILgnu/lists/Consumer;)I
    //   508: pop
    //   509: return
    //   510: aload_1
    //   511: instanceof gnu/kawa/xml/KNode
    //   514: ifeq -> 574
    //   517: aload_0
    //   518: invokevirtual getReadableOutput : ()Z
    //   521: ifeq -> 531
    //   524: aload_0
    //   525: ldc '#'
    //   527: aload_2
    //   528: invokevirtual write : (Ljava/lang/String;Lgnu/lists/Consumer;)V
    //   531: aload_2
    //   532: instanceof java/io/Writer
    //   535: ifeq -> 562
    //   538: aload_2
    //   539: checkcast java/io/Writer
    //   542: astore_2
    //   543: new gnu/xml/XMLPrinter
    //   546: dup
    //   547: aload_2
    //   548: invokespecial <init> : (Ljava/io/Writer;)V
    //   551: astore_2
    //   552: aload_2
    //   553: aload_1
    //   554: invokevirtual writeObject : (Ljava/lang/Object;)V
    //   557: aload_2
    //   558: invokevirtual closeThis : ()V
    //   561: return
    //   562: new gnu/lists/ConsumerWriter
    //   565: dup
    //   566: aload_2
    //   567: invokespecial <init> : (Lgnu/lists/Consumer;)V
    //   570: astore_2
    //   571: goto -> 543
    //   574: aload_1
    //   575: getstatic gnu/mapping/Values.empty : Lgnu/mapping/Values;
    //   578: if_acmpne -> 597
    //   581: aload_0
    //   582: invokevirtual getReadableOutput : ()Z
    //   585: ifeq -> 597
    //   588: aload_0
    //   589: ldc_w '#!void'
    //   592: aload_2
    //   593: invokevirtual write : (Ljava/lang/String;Lgnu/lists/Consumer;)V
    //   596: return
    //   597: aload_1
    //   598: instanceof gnu/lists/Consumable
    //   601: ifeq -> 615
    //   604: aload_1
    //   605: checkcast gnu/lists/Consumable
    //   608: aload_2
    //   609: invokeinterface consume : (Lgnu/lists/Consumer;)V
    //   614: return
    //   615: aload_1
    //   616: instanceof gnu/text/Printable
    //   619: ifeq -> 633
    //   622: aload_1
    //   623: checkcast gnu/text/Printable
    //   626: aload_2
    //   627: invokeinterface print : (Lgnu/lists/Consumer;)V
    //   632: return
    //   633: aload_1
    //   634: instanceof gnu/math/RatNum
    //   637: ifeq -> 879
    //   640: bipush #10
    //   642: istore_3
    //   643: iconst_0
    //   644: istore #5
    //   646: getstatic gnu/kawa/functions/DisplayFormat.outBase : Lgnu/mapping/ThreadLocation;
    //   649: aconst_null
    //   650: invokevirtual get : (Ljava/lang/Object;)Ljava/lang/Object;
    //   653: astore #6
    //   655: getstatic gnu/kawa/functions/DisplayFormat.outRadix : Lgnu/mapping/ThreadLocation;
    //   658: aconst_null
    //   659: invokevirtual get : (Ljava/lang/Object;)Ljava/lang/Object;
    //   662: astore #7
    //   664: iload #5
    //   666: istore #4
    //   668: aload #7
    //   670: ifnull -> 702
    //   673: aload #7
    //   675: getstatic java/lang/Boolean.TRUE : Ljava/lang/Boolean;
    //   678: if_acmpeq -> 699
    //   681: iload #5
    //   683: istore #4
    //   685: ldc_w 'yes'
    //   688: aload #7
    //   690: invokevirtual toString : ()Ljava/lang/String;
    //   693: invokevirtual equals : (Ljava/lang/Object;)Z
    //   696: ifeq -> 702
    //   699: iconst_1
    //   700: istore #4
    //   702: aload #6
    //   704: instanceof java/lang/Number
    //   707: ifeq -> 782
    //   710: aload #6
    //   712: checkcast gnu/math/IntNum
    //   715: invokevirtual intValue : ()I
    //   718: istore_3
    //   719: aload_1
    //   720: checkcast gnu/math/RatNum
    //   723: iload_3
    //   724: invokevirtual toString : (I)Ljava/lang/String;
    //   727: astore #7
    //   729: iload #4
    //   731: ifeq -> 748
    //   734: iload_3
    //   735: bipush #16
    //   737: if_icmpne -> 799
    //   740: aload_0
    //   741: ldc_w '#x'
    //   744: aload_2
    //   745: invokevirtual write : (Ljava/lang/String;Lgnu/lists/Consumer;)V
    //   748: aload_0
    //   749: aload #7
    //   751: aload_2
    //   752: invokevirtual write : (Ljava/lang/String;Lgnu/lists/Consumer;)V
    //   755: iload #4
    //   757: ifeq -> 19
    //   760: iload_3
    //   761: bipush #10
    //   763: if_icmpne -> 19
    //   766: aload_1
    //   767: instanceof gnu/math/IntNum
    //   770: ifeq -> 19
    //   773: aload_0
    //   774: ldc_w '.'
    //   777: aload_2
    //   778: invokevirtual write : (Ljava/lang/String;Lgnu/lists/Consumer;)V
    //   781: return
    //   782: aload #6
    //   784: ifnull -> 719
    //   787: aload #6
    //   789: invokevirtual toString : ()Ljava/lang/String;
    //   792: invokestatic parseInt : (Ljava/lang/String;)I
    //   795: istore_3
    //   796: goto -> 719
    //   799: iload_3
    //   800: bipush #8
    //   802: if_icmpne -> 816
    //   805: aload_0
    //   806: ldc_w '#o'
    //   809: aload_2
    //   810: invokevirtual write : (Ljava/lang/String;Lgnu/lists/Consumer;)V
    //   813: goto -> 748
    //   816: iload_3
    //   817: iconst_2
    //   818: if_icmpne -> 832
    //   821: aload_0
    //   822: ldc_w '#b'
    //   825: aload_2
    //   826: invokevirtual write : (Ljava/lang/String;Lgnu/lists/Consumer;)V
    //   829: goto -> 748
    //   832: iload_3
    //   833: bipush #10
    //   835: if_icmpne -> 845
    //   838: aload_1
    //   839: instanceof gnu/math/IntNum
    //   842: ifne -> 748
    //   845: aload_0
    //   846: new java/lang/StringBuilder
    //   849: dup
    //   850: invokespecial <init> : ()V
    //   853: ldc '#'
    //   855: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   858: aload #6
    //   860: invokevirtual append : (Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   863: ldc_w 'r'
    //   866: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   869: invokevirtual toString : ()Ljava/lang/String;
    //   872: aload_2
    //   873: invokevirtual write : (Ljava/lang/String;Lgnu/lists/Consumer;)V
    //   876: goto -> 748
    //   879: aload_1
    //   880: instanceof java/lang/Enum
    //   883: ifeq -> 926
    //   886: aload_0
    //   887: invokevirtual getReadableOutput : ()Z
    //   890: ifeq -> 926
    //   893: aload_0
    //   894: aload_1
    //   895: invokevirtual getClass : ()Ljava/lang/Class;
    //   898: invokevirtual getName : ()Ljava/lang/String;
    //   901: aload_2
    //   902: invokevirtual write : (Ljava/lang/String;Lgnu/lists/Consumer;)V
    //   905: aload_0
    //   906: ldc_w ':'
    //   909: aload_2
    //   910: invokevirtual write : (Ljava/lang/String;Lgnu/lists/Consumer;)V
    //   913: aload_0
    //   914: aload_1
    //   915: checkcast java/lang/Enum
    //   918: invokevirtual name : ()Ljava/lang/String;
    //   921: aload_2
    //   922: invokevirtual write : (Ljava/lang/String;Lgnu/lists/Consumer;)V
    //   925: return
    //   926: aload_1
    //   927: ifnonnull -> 945
    //   930: aconst_null
    //   931: astore_1
    //   932: aload_1
    //   933: ifnonnull -> 1078
    //   936: aload_0
    //   937: ldc_w '#!null'
    //   940: aload_2
    //   941: invokevirtual write : (Ljava/lang/String;Lgnu/lists/Consumer;)V
    //   944: return
    //   945: aload_1
    //   946: invokevirtual getClass : ()Ljava/lang/Class;
    //   949: invokevirtual isArray : ()Z
    //   952: ifeq -> 1070
    //   955: aload_1
    //   956: invokestatic getLength : (Ljava/lang/Object;)I
    //   959: istore #4
    //   961: aload_2
    //   962: instanceof gnu/mapping/OutPort
    //   965: ifeq -> 1032
    //   968: aload_2
    //   969: checkcast gnu/mapping/OutPort
    //   972: ldc_w '['
    //   975: iconst_0
    //   976: ldc_w ']'
    //   979: invokevirtual startLogicalBlock : (Ljava/lang/String;ZLjava/lang/String;)V
    //   982: iconst_0
    //   983: istore_3
    //   984: iload_3
    //   985: iload #4
    //   987: if_icmpge -> 1043
    //   990: iload_3
    //   991: ifle -> 1015
    //   994: aload_0
    //   995: ldc ' '
    //   997: aload_2
    //   998: invokevirtual write : (Ljava/lang/String;Lgnu/lists/Consumer;)V
    //   1001: aload_2
    //   1002: instanceof gnu/mapping/OutPort
    //   1005: ifeq -> 1015
    //   1008: aload_2
    //   1009: checkcast gnu/mapping/OutPort
    //   1012: invokevirtual writeBreakFill : ()V
    //   1015: aload_0
    //   1016: aload_1
    //   1017: iload_3
    //   1018: invokestatic get : (Ljava/lang/Object;I)Ljava/lang/Object;
    //   1021: aload_2
    //   1022: invokevirtual writeObject : (Ljava/lang/Object;Lgnu/lists/Consumer;)V
    //   1025: iload_3
    //   1026: iconst_1
    //   1027: iadd
    //   1028: istore_3
    //   1029: goto -> 984
    //   1032: aload_0
    //   1033: ldc_w '['
    //   1036: aload_2
    //   1037: invokevirtual write : (Ljava/lang/String;Lgnu/lists/Consumer;)V
    //   1040: goto -> 982
    //   1043: aload_2
    //   1044: instanceof gnu/mapping/OutPort
    //   1047: ifeq -> 1061
    //   1050: aload_2
    //   1051: checkcast gnu/mapping/OutPort
    //   1054: ldc_w ']'
    //   1057: invokevirtual endLogicalBlock : (Ljava/lang/String;)V
    //   1060: return
    //   1061: aload_0
    //   1062: ldc_w ']'
    //   1065: aload_2
    //   1066: invokevirtual write : (Ljava/lang/String;Lgnu/lists/Consumer;)V
    //   1069: return
    //   1070: aload_1
    //   1071: invokevirtual toString : ()Ljava/lang/String;
    //   1074: astore_1
    //   1075: goto -> 932
    //   1078: aload_0
    //   1079: aload_1
    //   1080: aload_2
    //   1081: invokevirtual write : (Ljava/lang/String;Lgnu/lists/Consumer;)V
    //   1084: return
  }
  
  void writeSymbol(Symbol paramSymbol, Consumer paramConsumer, boolean paramBoolean) {
    // Byte code:
    //   0: iconst_1
    //   1: istore #5
    //   3: aload_1
    //   4: invokevirtual getPrefix : ()Ljava/lang/String;
    //   7: astore #9
    //   9: aload_1
    //   10: invokevirtual getNamespace : ()Lgnu/mapping/Namespace;
    //   13: astore #10
    //   15: aload #10
    //   17: ifnonnull -> 117
    //   20: aconst_null
    //   21: astore #8
    //   23: aload #8
    //   25: ifnull -> 127
    //   28: aload #8
    //   30: invokevirtual length : ()I
    //   33: ifle -> 127
    //   36: iconst_1
    //   37: istore #4
    //   39: aload #9
    //   41: ifnull -> 133
    //   44: aload #9
    //   46: invokevirtual length : ()I
    //   49: ifle -> 133
    //   52: iconst_0
    //   53: istore #7
    //   55: aload #10
    //   57: getstatic gnu/expr/Keyword.keywordNamespace : Lgnu/mapping/Namespace;
    //   60: if_acmpne -> 145
    //   63: aload_0
    //   64: getfield language : C
    //   67: bipush #67
    //   69: if_icmpeq -> 81
    //   72: aload_0
    //   73: getfield language : C
    //   76: bipush #69
    //   78: if_icmpne -> 139
    //   81: aload_2
    //   82: bipush #58
    //   84: invokeinterface write : (I)V
    //   89: iload #7
    //   91: istore #6
    //   93: aload_0
    //   94: aload_1
    //   95: invokevirtual getName : ()Ljava/lang/String;
    //   98: aload_2
    //   99: iload_3
    //   100: invokevirtual writeSymbol : (Ljava/lang/String;Lgnu/lists/Consumer;Z)V
    //   103: iload #6
    //   105: ifeq -> 116
    //   108: aload_2
    //   109: bipush #58
    //   111: invokeinterface write : (I)V
    //   116: return
    //   117: aload #10
    //   119: invokevirtual getName : ()Ljava/lang/String;
    //   122: astore #8
    //   124: goto -> 23
    //   127: iconst_0
    //   128: istore #4
    //   130: goto -> 39
    //   133: iconst_0
    //   134: istore #5
    //   136: goto -> 52
    //   139: iconst_1
    //   140: istore #6
    //   142: goto -> 93
    //   145: iload #5
    //   147: ifne -> 159
    //   150: iload #7
    //   152: istore #6
    //   154: iload #4
    //   156: ifeq -> 93
    //   159: iload #5
    //   161: ifeq -> 172
    //   164: aload_0
    //   165: aload #9
    //   167: aload_2
    //   168: iload_3
    //   169: invokevirtual writeSymbol : (Ljava/lang/String;Lgnu/lists/Consumer;Z)V
    //   172: iload #4
    //   174: ifeq -> 210
    //   177: iload_3
    //   178: ifne -> 186
    //   181: iload #5
    //   183: ifne -> 210
    //   186: aload_2
    //   187: bipush #123
    //   189: invokeinterface write : (I)V
    //   194: aload_2
    //   195: aload #8
    //   197: invokeinterface write : (Ljava/lang/String;)V
    //   202: aload_2
    //   203: bipush #125
    //   205: invokeinterface write : (I)V
    //   210: aload_2
    //   211: bipush #58
    //   213: invokeinterface write : (I)V
    //   218: iload #7
    //   220: istore #6
    //   222: goto -> 93
  }
  
  void writeSymbol(String paramString, Consumer paramConsumer, boolean paramBoolean) {
    if (paramBoolean && !r5rsIdentifierMinusInteriorColons.matcher(paramString).matches()) {
      int j = paramString.length();
      if (j == 0) {
        write("||", paramConsumer);
        return;
      } 
      boolean bool = false;
      int i = 0;
      while (i < j) {
        boolean bool1;
        char c = paramString.charAt(i);
        if (c == '|') {
          String str;
          if (bool) {
            str = "|\\";
          } else {
            str = "\\";
          } 
          write(str, paramConsumer);
          bool1 = false;
        } else {
          bool1 = bool;
          if (!bool) {
            paramConsumer.write(124);
            bool1 = true;
          } 
        } 
        paramConsumer.write(c);
        i++;
        bool = bool1;
      } 
      if (bool) {
        paramConsumer.write(124);
        return;
      } 
      return;
    } 
    write(paramString, paramConsumer);
  }
  
  static {
    outBase.setGlobal(IntNum.ten());
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/gnu/kawa/functions/DisplayFormat.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */