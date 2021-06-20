package gnu.text;

import java.io.IOException;
import java.io.Writer;
import java.math.BigInteger;
import java.text.FieldPosition;

public class IntegerFormat extends ReportFormat {
  public static final int MIN_DIGITS = 64;
  
  public static final int PAD_RIGHT = 16;
  
  public static final int SHOW_BASE = 8;
  
  public static final int SHOW_GROUPS = 1;
  
  public static final int SHOW_PLUS = 2;
  
  public static final int SHOW_SPACE = 4;
  
  public static final int UPPERCASE = 32;
  
  public int base = 10;
  
  public int commaChar = 44;
  
  public int commaInterval = 3;
  
  public int flags = 0;
  
  public int minWidth = 1;
  
  public int padChar = 32;
  
  public String convertToIntegerString(Object paramObject, int paramInt) {
    return !(paramObject instanceof Number) ? null : ((paramObject instanceof BigInteger) ? ((BigInteger)paramObject).toString(paramInt) : Long.toString(((Number)paramObject).longValue(), paramInt));
  }
  
  public int format(Object paramObject, int paramInt, Writer paramWriter, FieldPosition paramFieldPosition) throws IOException {
    // Byte code:
    //   0: aload_1
    //   1: instanceof [Ljava/lang/Object;
    //   4: ifeq -> 199
    //   7: aload_1
    //   8: checkcast [Ljava/lang/Object;
    //   11: checkcast [Ljava/lang/Object;
    //   14: astore #4
    //   16: aload_0
    //   17: getfield minWidth : I
    //   20: iconst_1
    //   21: aload #4
    //   23: iload_2
    //   24: invokestatic getParam : (II[Ljava/lang/Object;I)I
    //   27: istore #8
    //   29: iload_2
    //   30: istore #7
    //   32: aload_0
    //   33: getfield minWidth : I
    //   36: ldc -1610612736
    //   38: if_icmpne -> 46
    //   41: iload_2
    //   42: iconst_1
    //   43: iadd
    //   44: istore #7
    //   46: aload_0
    //   47: getfield padChar : I
    //   50: bipush #32
    //   52: aload #4
    //   54: iload #7
    //   56: invokestatic getParam : (IC[Ljava/lang/Object;I)C
    //   59: istore #16
    //   61: iload #7
    //   63: istore #6
    //   65: aload_0
    //   66: getfield padChar : I
    //   69: ldc -1610612736
    //   71: if_icmpne -> 80
    //   74: iload #7
    //   76: iconst_1
    //   77: iadd
    //   78: istore #6
    //   80: aload_0
    //   81: getfield commaChar : I
    //   84: bipush #44
    //   86: aload #4
    //   88: iload #6
    //   90: invokestatic getParam : (IC[Ljava/lang/Object;I)C
    //   93: istore #17
    //   95: iload #6
    //   97: istore_2
    //   98: aload_0
    //   99: getfield commaChar : I
    //   102: ldc -1610612736
    //   104: if_icmpne -> 112
    //   107: iload #6
    //   109: iconst_1
    //   110: iadd
    //   111: istore_2
    //   112: aload_0
    //   113: getfield commaInterval : I
    //   116: iconst_3
    //   117: aload #4
    //   119: iload_2
    //   120: invokestatic getParam : (II[Ljava/lang/Object;I)I
    //   123: istore #18
    //   125: iload_2
    //   126: istore #9
    //   128: aload_0
    //   129: getfield commaInterval : I
    //   132: ldc -1610612736
    //   134: if_icmpne -> 142
    //   137: iload_2
    //   138: iconst_1
    //   139: iadd
    //   140: istore #9
    //   142: aload_0
    //   143: getfield flags : I
    //   146: iconst_1
    //   147: iand
    //   148: ifeq -> 205
    //   151: iconst_1
    //   152: istore #10
    //   154: aload_0
    //   155: getfield flags : I
    //   158: bipush #16
    //   160: iand
    //   161: ifeq -> 211
    //   164: iconst_1
    //   165: istore #11
    //   167: iload #16
    //   169: bipush #48
    //   171: if_icmpne -> 217
    //   174: iconst_1
    //   175: istore #12
    //   177: aload #4
    //   179: ifnull -> 229
    //   182: iload #9
    //   184: aload #4
    //   186: arraylength
    //   187: if_icmplt -> 223
    //   190: aload_3
    //   191: ldc '#<missing format argument>'
    //   193: invokevirtual write : (Ljava/lang/String;)V
    //   196: iload #9
    //   198: ireturn
    //   199: aconst_null
    //   200: astore #4
    //   202: goto -> 16
    //   205: iconst_0
    //   206: istore #10
    //   208: goto -> 154
    //   211: iconst_0
    //   212: istore #11
    //   214: goto -> 167
    //   217: iconst_0
    //   218: istore #12
    //   220: goto -> 177
    //   223: aload #4
    //   225: iload #9
    //   227: aaload
    //   228: astore_1
    //   229: aload_0
    //   230: aload_1
    //   231: aload_0
    //   232: getfield base : I
    //   235: invokevirtual convertToIntegerString : (Ljava/lang/Object;I)Ljava/lang/String;
    //   238: astore #4
    //   240: aload #4
    //   242: ifnull -> 819
    //   245: aload #4
    //   247: iconst_0
    //   248: invokevirtual charAt : (I)C
    //   251: istore #19
    //   253: iload #19
    //   255: bipush #45
    //   257: if_icmpne -> 460
    //   260: iconst_1
    //   261: istore #14
    //   263: aload #4
    //   265: invokevirtual length : ()I
    //   268: istore #15
    //   270: iload #14
    //   272: ifeq -> 466
    //   275: iload #15
    //   277: iconst_1
    //   278: isub
    //   279: istore #6
    //   281: iload #10
    //   283: ifeq -> 473
    //   286: iload #6
    //   288: iconst_1
    //   289: isub
    //   290: iload #18
    //   292: idiv
    //   293: istore_2
    //   294: iload #6
    //   296: iload_2
    //   297: iadd
    //   298: istore_2
    //   299: iload #14
    //   301: ifne -> 317
    //   304: iload_2
    //   305: istore #7
    //   307: aload_0
    //   308: getfield flags : I
    //   311: bipush #6
    //   313: iand
    //   314: ifeq -> 322
    //   317: iload_2
    //   318: iconst_1
    //   319: iadd
    //   320: istore #7
    //   322: iload #7
    //   324: istore_2
    //   325: aload_0
    //   326: getfield flags : I
    //   329: bipush #8
    //   331: iand
    //   332: ifeq -> 349
    //   335: aload_0
    //   336: getfield base : I
    //   339: bipush #16
    //   341: if_icmpne -> 478
    //   344: iload #7
    //   346: iconst_2
    //   347: iadd
    //   348: istore_2
    //   349: iload #15
    //   351: istore #7
    //   353: iload_2
    //   354: istore #13
    //   356: aload_0
    //   357: getfield flags : I
    //   360: bipush #64
    //   362: iand
    //   363: ifeq -> 415
    //   366: iload #15
    //   368: istore #7
    //   370: iload #6
    //   372: istore #13
    //   374: iload #15
    //   376: iconst_1
    //   377: if_icmpne -> 415
    //   380: iload #15
    //   382: istore #7
    //   384: iload #6
    //   386: istore #13
    //   388: iload #19
    //   390: bipush #48
    //   392: if_icmpne -> 415
    //   395: iload #15
    //   397: istore #7
    //   399: iload #6
    //   401: istore #13
    //   403: iload #8
    //   405: ifne -> 415
    //   408: iconst_0
    //   409: istore #7
    //   411: iload #6
    //   413: istore #13
    //   415: iload #8
    //   417: istore_2
    //   418: iload #11
    //   420: ifne -> 508
    //   423: iload #8
    //   425: istore_2
    //   426: iload #12
    //   428: ifne -> 508
    //   431: iload #8
    //   433: istore #6
    //   435: iload #6
    //   437: istore_2
    //   438: iload #6
    //   440: iload #13
    //   442: if_icmple -> 508
    //   445: aload_3
    //   446: iload #16
    //   448: invokevirtual write : (I)V
    //   451: iload #6
    //   453: iconst_1
    //   454: isub
    //   455: istore #6
    //   457: goto -> 435
    //   460: iconst_0
    //   461: istore #14
    //   463: goto -> 263
    //   466: iload #15
    //   468: istore #6
    //   470: goto -> 281
    //   473: iconst_0
    //   474: istore_2
    //   475: goto -> 294
    //   478: iload #7
    //   480: istore_2
    //   481: aload_0
    //   482: getfield base : I
    //   485: bipush #8
    //   487: if_icmpne -> 349
    //   490: iload #7
    //   492: istore_2
    //   493: iload #19
    //   495: bipush #48
    //   497: if_icmpeq -> 349
    //   500: iload #7
    //   502: iconst_1
    //   503: iadd
    //   504: istore_2
    //   505: goto -> 349
    //   508: iconst_0
    //   509: istore #15
    //   511: iload #14
    //   513: ifeq -> 625
    //   516: aload_3
    //   517: bipush #45
    //   519: invokevirtual write : (I)V
    //   522: iconst_0
    //   523: iconst_1
    //   524: iadd
    //   525: istore #8
    //   527: iload #7
    //   529: iconst_1
    //   530: isub
    //   531: istore #6
    //   533: aload_0
    //   534: getfield base : I
    //   537: bipush #10
    //   539: if_icmple -> 685
    //   542: aload_0
    //   543: getfield flags : I
    //   546: bipush #32
    //   548: iand
    //   549: ifeq -> 685
    //   552: iconst_1
    //   553: istore #14
    //   555: aload_0
    //   556: getfield flags : I
    //   559: bipush #8
    //   561: iand
    //   562: ifeq -> 595
    //   565: aload_0
    //   566: getfield base : I
    //   569: bipush #16
    //   571: if_icmpne -> 698
    //   574: aload_3
    //   575: bipush #48
    //   577: invokevirtual write : (I)V
    //   580: iload #14
    //   582: ifeq -> 691
    //   585: bipush #88
    //   587: istore #7
    //   589: aload_3
    //   590: iload #7
    //   592: invokevirtual write : (I)V
    //   595: iload_2
    //   596: istore #7
    //   598: iload #12
    //   600: ifeq -> 832
    //   603: iload_2
    //   604: istore #7
    //   606: iload_2
    //   607: iload #13
    //   609: if_icmple -> 832
    //   612: aload_3
    //   613: iload #16
    //   615: invokevirtual write : (I)V
    //   618: iload_2
    //   619: iconst_1
    //   620: isub
    //   621: istore_2
    //   622: goto -> 603
    //   625: aload_0
    //   626: getfield flags : I
    //   629: iconst_2
    //   630: iand
    //   631: ifeq -> 651
    //   634: aload_3
    //   635: bipush #43
    //   637: invokevirtual write : (I)V
    //   640: iload #15
    //   642: istore #8
    //   644: iload #7
    //   646: istore #6
    //   648: goto -> 533
    //   651: iload #15
    //   653: istore #8
    //   655: iload #7
    //   657: istore #6
    //   659: aload_0
    //   660: getfield flags : I
    //   663: iconst_4
    //   664: iand
    //   665: ifeq -> 533
    //   668: aload_3
    //   669: bipush #32
    //   671: invokevirtual write : (I)V
    //   674: iload #15
    //   676: istore #8
    //   678: iload #7
    //   680: istore #6
    //   682: goto -> 533
    //   685: iconst_0
    //   686: istore #14
    //   688: goto -> 555
    //   691: bipush #120
    //   693: istore #7
    //   695: goto -> 589
    //   698: aload_0
    //   699: getfield base : I
    //   702: bipush #8
    //   704: if_icmpne -> 595
    //   707: iload #19
    //   709: bipush #48
    //   711: if_icmpeq -> 595
    //   714: aload_3
    //   715: bipush #48
    //   717: invokevirtual write : (I)V
    //   720: goto -> 595
    //   723: aload #4
    //   725: iload_2
    //   726: invokevirtual charAt : (I)C
    //   729: istore #5
    //   731: iload #5
    //   733: istore #8
    //   735: iload #14
    //   737: ifeq -> 747
    //   740: iload #5
    //   742: invokestatic toUpperCase : (C)C
    //   745: istore #8
    //   747: aload_3
    //   748: iload #8
    //   750: invokevirtual write : (I)V
    //   753: iload #6
    //   755: iconst_1
    //   756: isub
    //   757: istore #6
    //   759: iload #10
    //   761: ifeq -> 783
    //   764: iload #6
    //   766: ifle -> 783
    //   769: iload #6
    //   771: iload #18
    //   773: irem
    //   774: ifne -> 783
    //   777: aload_3
    //   778: iload #17
    //   780: invokevirtual write : (I)V
    //   783: iload_2
    //   784: iconst_1
    //   785: iadd
    //   786: istore_2
    //   787: iload #6
    //   789: ifne -> 723
    //   792: iload #11
    //   794: ifeq -> 827
    //   797: iload #7
    //   799: iload #13
    //   801: if_icmple -> 827
    //   804: aload_3
    //   805: iload #16
    //   807: invokevirtual write : (I)V
    //   810: iload #7
    //   812: iconst_1
    //   813: isub
    //   814: istore #7
    //   816: goto -> 797
    //   819: aload_3
    //   820: aload_1
    //   821: invokevirtual toString : ()Ljava/lang/String;
    //   824: invokestatic print : (Ljava/io/Writer;Ljava/lang/String;)V
    //   827: iload #9
    //   829: iconst_1
    //   830: iadd
    //   831: ireturn
    //   832: iload #8
    //   834: istore_2
    //   835: goto -> 787
  }
  
  public int format(Object[] paramArrayOfObject, int paramInt, Writer paramWriter, FieldPosition paramFieldPosition) throws IOException {
    return format(paramArrayOfObject, paramInt, paramWriter, paramFieldPosition);
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/gnu/text/IntegerFormat.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */