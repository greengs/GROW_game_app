package gnu.kawa.lispexpr;

import gnu.expr.Keyword;
import gnu.expr.QuoteExp;
import gnu.expr.Special;
import gnu.kawa.util.GeneralHashTable;
import gnu.lists.F32Vector;
import gnu.lists.F64Vector;
import gnu.lists.FString;
import gnu.lists.LList;
import gnu.lists.Pair;
import gnu.lists.PairWithPosition;
import gnu.lists.S16Vector;
import gnu.lists.S32Vector;
import gnu.lists.S64Vector;
import gnu.lists.S8Vector;
import gnu.lists.Sequence;
import gnu.lists.SimpleVector;
import gnu.lists.U16Vector;
import gnu.lists.U32Vector;
import gnu.lists.U64Vector;
import gnu.lists.U8Vector;
import gnu.mapping.Environment;
import gnu.mapping.InPort;
import gnu.mapping.Symbol;
import gnu.mapping.Values;
import gnu.math.IntNum;
import gnu.text.Lexer;
import gnu.text.LineBufferedReader;
import gnu.text.SourceMessages;
import gnu.text.SyntaxException;
import java.io.IOException;

public class LispReader extends Lexer {
  static final int SCM_COMPLEX = 1;
  
  public static final int SCM_NUMBERS = 1;
  
  public static final char TOKEN_ESCAPE_CHAR = '￿';
  
  protected boolean seenEscapes;
  
  GeneralHashTable<Integer, Object> sharedStructureTable;
  
  public LispReader(LineBufferedReader paramLineBufferedReader) {
    super(paramLineBufferedReader);
  }
  
  public LispReader(LineBufferedReader paramLineBufferedReader, SourceMessages paramSourceMessages) {
    super(paramLineBufferedReader, paramSourceMessages);
  }
  
  static char getReadCase() {
    try {
      char c = Environment.getCurrent().get("symbol-read-case", "P").toString().charAt(0);
      if (c != 'P') {
        if (c == 'u')
          return 'U'; 
        if (c == 'd' || c == 'l' || c == 'L')
          return 'D'; 
        if (c == 'i')
          return 'I'; 
      } 
      return c;
    } catch (Exception exception) {
      return 'P';
    } 
  }
  
  private boolean isPotentialNumber(char[] paramArrayOfchar, int paramInt1, int paramInt2) {
    boolean bool = true;
    int j = 0;
    for (int i = paramInt1; i < paramInt2; i++) {
      char c = paramArrayOfchar[i];
      if (Character.isDigit(c)) {
        j++;
      } else if (c == '-' || c == '+') {
        if (i + 1 == paramInt2)
          return false; 
      } else {
        if (c == '#')
          return true; 
        if (Character.isLetter(c) || c == '/' || c == '_' || c == '^') {
          if (i == paramInt1)
            return false; 
        } else if (c != '.') {
          return false;
        } 
      } 
    } 
    if (j <= 0)
      bool = false; 
    return bool;
  }
  
  public static Object parseNumber(CharSequence paramCharSequence, int paramInt) {
    if (paramCharSequence instanceof FString) {
      char[] arrayOfChar1 = ((FString)paramCharSequence).data;
      return parseNumber(arrayOfChar1, 0, paramCharSequence.length(), false, paramInt, 1);
    } 
    char[] arrayOfChar = paramCharSequence.toString().toCharArray();
    return parseNumber(arrayOfChar, 0, paramCharSequence.length(), false, paramInt, 1);
  }
  
  public static Object parseNumber(char[] paramArrayOfchar, int paramInt1, int paramInt2, char paramChar, int paramInt3, int paramInt4) {
    // Byte code:
    //   0: iload_1
    //   1: iload_2
    //   2: iadd
    //   3: istore #19
    //   5: iload_1
    //   6: iload #19
    //   8: if_icmplt -> 16
    //   11: ldc 'no digits'
    //   13: astore_0
    //   14: aload_0
    //   15: areturn
    //   16: iload_1
    //   17: iconst_1
    //   18: iadd
    //   19: istore #13
    //   21: aload_0
    //   22: iload_1
    //   23: caload
    //   24: istore #6
    //   26: iload #4
    //   28: istore #12
    //   30: iload #6
    //   32: bipush #35
    //   34: if_icmpne -> 394
    //   37: iload #13
    //   39: iload #19
    //   41: if_icmplt -> 47
    //   44: ldc 'no digits'
    //   46: areturn
    //   47: iload #13
    //   49: iconst_1
    //   50: iadd
    //   51: istore #4
    //   53: aload_0
    //   54: iload #13
    //   56: caload
    //   57: istore #6
    //   59: iload #6
    //   61: lookupswitch default -> 168, 66 -> 207, 68 -> 243, 69 -> 273, 73 -> 273, 79 -> 228, 88 -> 258, 98 -> 207, 100 -> 243, 101 -> 273, 105 -> 273, 111 -> 228, 120 -> 258
    //   168: iconst_0
    //   169: istore #13
    //   171: iload #6
    //   173: bipush #10
    //   175: invokestatic digit : (CI)I
    //   178: istore #14
    //   180: iload #14
    //   182: ifge -> 295
    //   185: iload #6
    //   187: bipush #82
    //   189: if_icmpeq -> 199
    //   192: iload #6
    //   194: bipush #114
    //   196: if_icmpne -> 353
    //   199: iload #12
    //   201: ifeq -> 330
    //   204: ldc 'duplicate radix specifier'
    //   206: areturn
    //   207: iload #12
    //   209: ifeq -> 215
    //   212: ldc 'duplicate radix specifier'
    //   214: areturn
    //   215: iconst_2
    //   216: istore #12
    //   218: iload #4
    //   220: iload #19
    //   222: if_icmplt -> 379
    //   225: ldc 'no digits'
    //   227: areturn
    //   228: iload #12
    //   230: ifeq -> 236
    //   233: ldc 'duplicate radix specifier'
    //   235: areturn
    //   236: bipush #8
    //   238: istore #12
    //   240: goto -> 218
    //   243: iload #12
    //   245: ifeq -> 251
    //   248: ldc 'duplicate radix specifier'
    //   250: areturn
    //   251: bipush #10
    //   253: istore #12
    //   255: goto -> 218
    //   258: iload #12
    //   260: ifeq -> 266
    //   263: ldc 'duplicate radix specifier'
    //   265: areturn
    //   266: bipush #16
    //   268: istore #12
    //   270: goto -> 218
    //   273: iload_3
    //   274: ifeq -> 289
    //   277: iload_3
    //   278: bipush #32
    //   280: if_icmpne -> 286
    //   283: ldc 'non-prefix exactness specifier'
    //   285: areturn
    //   286: ldc 'duplicate exactness specifier'
    //   288: areturn
    //   289: iload #6
    //   291: istore_3
    //   292: goto -> 218
    //   295: iload #13
    //   297: bipush #10
    //   299: imul
    //   300: iload #14
    //   302: iadd
    //   303: istore #13
    //   305: iload #4
    //   307: iload #19
    //   309: if_icmplt -> 315
    //   312: ldc 'missing letter after '#''
    //   314: areturn
    //   315: aload_0
    //   316: iload #4
    //   318: caload
    //   319: istore #6
    //   321: iload #4
    //   323: iconst_1
    //   324: iadd
    //   325: istore #4
    //   327: goto -> 171
    //   330: iload #13
    //   332: iconst_2
    //   333: if_icmplt -> 343
    //   336: iload #13
    //   338: bipush #35
    //   340: if_icmple -> 346
    //   343: ldc 'invalid radix specifier'
    //   345: areturn
    //   346: iload #13
    //   348: istore #12
    //   350: goto -> 218
    //   353: new java/lang/StringBuilder
    //   356: dup
    //   357: invokespecial <init> : ()V
    //   360: ldc 'unknown modifier '#'
    //   362: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   365: iload #6
    //   367: invokevirtual append : (C)Ljava/lang/StringBuilder;
    //   370: bipush #39
    //   372: invokevirtual append : (C)Ljava/lang/StringBuilder;
    //   375: invokevirtual toString : ()Ljava/lang/String;
    //   378: areturn
    //   379: aload_0
    //   380: iload #4
    //   382: caload
    //   383: istore #6
    //   385: iload #4
    //   387: iconst_1
    //   388: iadd
    //   389: istore #13
    //   391: goto -> 30
    //   394: iload_3
    //   395: istore #7
    //   397: iload_3
    //   398: ifne -> 405
    //   401: bipush #32
    //   403: istore #7
    //   405: iload #12
    //   407: istore #4
    //   409: iload #12
    //   411: ifne -> 428
    //   414: iload_2
    //   415: iconst_1
    //   416: isub
    //   417: istore #4
    //   419: iload #4
    //   421: ifge -> 470
    //   424: bipush #10
    //   426: istore #4
    //   428: iload #6
    //   430: bipush #45
    //   432: if_icmpne -> 491
    //   435: iconst_1
    //   436: istore #21
    //   438: iload #6
    //   440: bipush #45
    //   442: if_icmpeq -> 452
    //   445: iload #6
    //   447: bipush #43
    //   449: if_icmpne -> 497
    //   452: iconst_1
    //   453: istore #15
    //   455: iload #15
    //   457: ifeq -> 2266
    //   460: iload #13
    //   462: iload #19
    //   464: if_icmplt -> 503
    //   467: ldc 'no digits following sign'
    //   469: areturn
    //   470: iload #4
    //   472: istore_2
    //   473: aload_0
    //   474: iload_1
    //   475: iload #4
    //   477: iadd
    //   478: caload
    //   479: bipush #46
    //   481: if_icmpne -> 414
    //   484: bipush #10
    //   486: istore #4
    //   488: goto -> 428
    //   491: iconst_0
    //   492: istore #21
    //   494: goto -> 438
    //   497: iconst_0
    //   498: istore #15
    //   500: goto -> 455
    //   503: iload #13
    //   505: iconst_1
    //   506: iadd
    //   507: istore_2
    //   508: aload_0
    //   509: iload #13
    //   511: caload
    //   512: istore #6
    //   514: iload #6
    //   516: bipush #105
    //   518: if_icmpeq -> 528
    //   521: iload #6
    //   523: bipush #73
    //   525: if_icmpne -> 626
    //   528: iload_2
    //   529: iload #19
    //   531: if_icmpne -> 626
    //   534: iload_1
    //   535: iload_2
    //   536: iconst_2
    //   537: isub
    //   538: if_icmpne -> 626
    //   541: iload #5
    //   543: iconst_1
    //   544: iand
    //   545: ifeq -> 626
    //   548: aload_0
    //   549: iload_1
    //   550: caload
    //   551: istore_1
    //   552: iload_1
    //   553: bipush #43
    //   555: if_icmpeq -> 567
    //   558: iload_1
    //   559: bipush #45
    //   561: if_icmpeq -> 567
    //   564: ldc 'no digits'
    //   566: areturn
    //   567: iload #7
    //   569: bipush #105
    //   571: if_icmpeq -> 581
    //   574: iload #7
    //   576: bipush #73
    //   578: if_icmpne -> 608
    //   581: iload #21
    //   583: ifeq -> 602
    //   586: ldc2_w -1.0
    //   589: dstore #8
    //   591: new gnu/math/DComplex
    //   594: dup
    //   595: dconst_0
    //   596: dload #8
    //   598: invokespecial <init> : (DD)V
    //   601: areturn
    //   602: dconst_1
    //   603: dstore #8
    //   605: goto -> 591
    //   608: iload #21
    //   610: ifeq -> 619
    //   613: invokestatic imMinusOne : ()Lgnu/math/CComplex;
    //   616: astore_0
    //   617: aload_0
    //   618: areturn
    //   619: invokestatic imOne : ()Lgnu/math/CComplex;
    //   622: astore_0
    //   623: goto -> 617
    //   626: iconst_0
    //   627: istore #23
    //   629: iconst_m1
    //   630: istore #16
    //   632: iconst_m1
    //   633: istore #12
    //   635: iconst_m1
    //   636: istore #14
    //   638: iconst_0
    //   639: istore #22
    //   641: aconst_null
    //   642: astore #31
    //   644: lconst_0
    //   645: lstore #27
    //   647: iload_2
    //   648: istore_1
    //   649: iload #21
    //   651: istore #20
    //   653: iload #12
    //   655: istore_2
    //   656: iload #6
    //   658: iload #4
    //   660: invokestatic digit : (CI)I
    //   663: istore #13
    //   665: iload #13
    //   667: iflt -> 836
    //   670: iload #23
    //   672: ifeq -> 683
    //   675: iload #14
    //   677: ifge -> 683
    //   680: ldc 'digit after '#' in number'
    //   682: areturn
    //   683: iload_2
    //   684: istore #12
    //   686: iload_2
    //   687: ifge -> 695
    //   690: iload_1
    //   691: iconst_1
    //   692: isub
    //   693: istore #12
    //   695: iload #4
    //   697: i2l
    //   698: lload #27
    //   700: lmul
    //   701: iload #13
    //   703: i2l
    //   704: ladd
    //   705: lstore #27
    //   707: iload #14
    //   709: istore #13
    //   711: iload #12
    //   713: istore_2
    //   714: iload_1
    //   715: iload #19
    //   717: if_icmpne -> 1337
    //   720: iload #22
    //   722: istore #24
    //   724: aload #31
    //   726: astore #32
    //   728: iload #23
    //   730: istore #25
    //   732: lload #27
    //   734: lstore #29
    //   736: iload #20
    //   738: istore #26
    //   740: iload_2
    //   741: istore #12
    //   743: iconst_0
    //   744: istore_2
    //   745: iconst_0
    //   746: istore #14
    //   748: iload #12
    //   750: ifge -> 2260
    //   753: iload #14
    //   755: istore_2
    //   756: iload #15
    //   758: ifeq -> 829
    //   761: iload #14
    //   763: istore_2
    //   764: iload_1
    //   765: iconst_4
    //   766: iadd
    //   767: iload #19
    //   769: if_icmpge -> 829
    //   772: iload #14
    //   774: istore_2
    //   775: aload_0
    //   776: iload_1
    //   777: iconst_3
    //   778: iadd
    //   779: caload
    //   780: bipush #46
    //   782: if_icmpne -> 829
    //   785: iload #14
    //   787: istore_2
    //   788: aload_0
    //   789: iload_1
    //   790: iconst_4
    //   791: iadd
    //   792: caload
    //   793: bipush #48
    //   795: if_icmpne -> 829
    //   798: aload_0
    //   799: iload_1
    //   800: caload
    //   801: bipush #105
    //   803: if_icmpne -> 1353
    //   806: aload_0
    //   807: iload_1
    //   808: iconst_1
    //   809: iadd
    //   810: caload
    //   811: bipush #110
    //   813: if_icmpne -> 1353
    //   816: aload_0
    //   817: iload_1
    //   818: iconst_2
    //   819: iadd
    //   820: caload
    //   821: bipush #102
    //   823: if_icmpne -> 1353
    //   826: bipush #105
    //   828: istore_2
    //   829: iload_2
    //   830: ifne -> 1396
    //   833: ldc 'no digits'
    //   835: areturn
    //   836: iload #6
    //   838: lookupswitch default -> 944, 46 -> 978, 47 -> 1273, 68 -> 1004, 69 -> 1004, 70 -> 1004, 76 -> 1004, 83 -> 1004, 100 -> 1004, 101 -> 1004, 102 -> 1004, 108 -> 1004, 115 -> 1004
    //   944: iload_1
    //   945: iconst_1
    //   946: isub
    //   947: istore_1
    //   948: iload_2
    //   949: istore #12
    //   951: iload #20
    //   953: istore #26
    //   955: lload #27
    //   957: lstore #29
    //   959: iload #14
    //   961: istore #13
    //   963: iload #23
    //   965: istore #25
    //   967: aload #31
    //   969: astore #32
    //   971: iload #22
    //   973: istore #24
    //   975: goto -> 743
    //   978: iload #14
    //   980: iflt -> 986
    //   983: ldc 'duplicate '.' in number'
    //   985: areturn
    //   986: iload #4
    //   988: bipush #10
    //   990: if_icmpeq -> 996
    //   993: ldc ''.' in non-decimal number'
    //   995: areturn
    //   996: iload_1
    //   997: iconst_1
    //   998: isub
    //   999: istore #13
    //   1001: goto -> 714
    //   1004: iload_1
    //   1005: iload #19
    //   1007: if_icmpeq -> 1017
    //   1010: iload #4
    //   1012: bipush #10
    //   1014: if_icmpeq -> 1051
    //   1017: iload_1
    //   1018: iconst_1
    //   1019: isub
    //   1020: istore_1
    //   1021: iload_2
    //   1022: istore #12
    //   1024: iload #20
    //   1026: istore #26
    //   1028: lload #27
    //   1030: lstore #29
    //   1032: iload #14
    //   1034: istore #13
    //   1036: iload #23
    //   1038: istore #25
    //   1040: aload #31
    //   1042: astore #32
    //   1044: iload #22
    //   1046: istore #24
    //   1048: goto -> 743
    //   1051: aload_0
    //   1052: iload_1
    //   1053: caload
    //   1054: istore_3
    //   1055: iload_3
    //   1056: bipush #43
    //   1058: if_icmpeq -> 1067
    //   1061: iload_3
    //   1062: bipush #45
    //   1064: if_icmpne -> 1098
    //   1067: iload_1
    //   1068: iconst_1
    //   1069: iadd
    //   1070: istore #13
    //   1072: iload #13
    //   1074: iload #19
    //   1076: if_icmpge -> 1095
    //   1079: iload #13
    //   1081: istore #12
    //   1083: aload_0
    //   1084: iload #13
    //   1086: caload
    //   1087: bipush #10
    //   1089: invokestatic digit : (CI)I
    //   1092: ifge -> 1144
    //   1095: ldc 'missing exponent digits'
    //   1097: areturn
    //   1098: iload_1
    //   1099: istore #12
    //   1101: iload_3
    //   1102: bipush #10
    //   1104: invokestatic digit : (CI)I
    //   1107: ifge -> 1144
    //   1110: iload_1
    //   1111: iconst_1
    //   1112: isub
    //   1113: istore_1
    //   1114: iload_2
    //   1115: istore #12
    //   1117: iload #20
    //   1119: istore #26
    //   1121: lload #27
    //   1123: lstore #29
    //   1125: iload #14
    //   1127: istore #13
    //   1129: iload #23
    //   1131: istore #25
    //   1133: aload #31
    //   1135: astore #32
    //   1137: iload #22
    //   1139: istore #24
    //   1141: goto -> 743
    //   1144: iconst_m1
    //   1145: iflt -> 1151
    //   1148: ldc 'duplicate exponent'
    //   1150: areturn
    //   1151: iload #4
    //   1153: bipush #10
    //   1155: if_icmpeq -> 1161
    //   1158: ldc 'exponent in non-decimal number'
    //   1160: areturn
    //   1161: iload_2
    //   1162: ifge -> 1168
    //   1165: ldc 'mantissa with no digits'
    //   1167: areturn
    //   1168: iload_1
    //   1169: iconst_1
    //   1170: isub
    //   1171: istore #18
    //   1173: iload #12
    //   1175: iconst_1
    //   1176: iadd
    //   1177: istore #17
    //   1179: iload_2
    //   1180: istore #12
    //   1182: iload #20
    //   1184: istore #26
    //   1186: lload #27
    //   1188: lstore #29
    //   1190: iload #17
    //   1192: istore_1
    //   1193: iload #14
    //   1195: istore #13
    //   1197: iload #18
    //   1199: istore #16
    //   1201: iload #23
    //   1203: istore #25
    //   1205: aload #31
    //   1207: astore #32
    //   1209: iload #22
    //   1211: istore #24
    //   1213: iload #17
    //   1215: iload #19
    //   1217: if_icmpge -> 743
    //   1220: iload #17
    //   1222: istore #12
    //   1224: aload_0
    //   1225: iload #17
    //   1227: caload
    //   1228: bipush #10
    //   1230: invokestatic digit : (CI)I
    //   1233: ifge -> 1173
    //   1236: iload_2
    //   1237: istore #12
    //   1239: iload #20
    //   1241: istore #26
    //   1243: lload #27
    //   1245: lstore #29
    //   1247: iload #17
    //   1249: istore_1
    //   1250: iload #14
    //   1252: istore #13
    //   1254: iload #18
    //   1256: istore #16
    //   1258: iload #23
    //   1260: istore #25
    //   1262: aload #31
    //   1264: astore #32
    //   1266: iload #22
    //   1268: istore #24
    //   1270: goto -> 743
    //   1273: aload #31
    //   1275: ifnull -> 1281
    //   1278: ldc 'multiple fraction symbol '/''
    //   1280: areturn
    //   1281: iload_2
    //   1282: ifge -> 1288
    //   1285: ldc 'no digits before fraction symbol '/''
    //   1287: areturn
    //   1288: iconst_m1
    //   1289: ifge -> 1297
    //   1292: iload #14
    //   1294: iflt -> 1300
    //   1297: ldc 'fraction symbol '/' following exponent or '.''
    //   1299: areturn
    //   1300: aload_0
    //   1301: iload_2
    //   1302: iload_1
    //   1303: iload_2
    //   1304: isub
    //   1305: iload #4
    //   1307: iload #20
    //   1309: lload #27
    //   1311: invokestatic valueOf : ([CIIIZJ)Lgnu/math/IntNum;
    //   1314: astore #31
    //   1316: iconst_m1
    //   1317: istore_2
    //   1318: lconst_0
    //   1319: lstore #27
    //   1321: iconst_0
    //   1322: istore #20
    //   1324: iconst_0
    //   1325: istore #23
    //   1327: iconst_0
    //   1328: istore #22
    //   1330: iload #14
    //   1332: istore #13
    //   1334: goto -> 714
    //   1337: aload_0
    //   1338: iload_1
    //   1339: caload
    //   1340: istore #6
    //   1342: iload_1
    //   1343: iconst_1
    //   1344: iadd
    //   1345: istore_1
    //   1346: iload #13
    //   1348: istore #14
    //   1350: goto -> 656
    //   1353: iload #14
    //   1355: istore_2
    //   1356: aload_0
    //   1357: iload_1
    //   1358: caload
    //   1359: bipush #110
    //   1361: if_icmpne -> 829
    //   1364: iload #14
    //   1366: istore_2
    //   1367: aload_0
    //   1368: iload_1
    //   1369: iconst_1
    //   1370: iadd
    //   1371: caload
    //   1372: bipush #97
    //   1374: if_icmpne -> 829
    //   1377: iload #14
    //   1379: istore_2
    //   1380: aload_0
    //   1381: iload_1
    //   1382: iconst_2
    //   1383: iadd
    //   1384: caload
    //   1385: bipush #110
    //   1387: if_icmpne -> 829
    //   1390: bipush #110
    //   1392: istore_2
    //   1393: goto -> 829
    //   1396: iload_1
    //   1397: iconst_5
    //   1398: iadd
    //   1399: istore #14
    //   1401: iload #25
    //   1403: ifne -> 1411
    //   1406: iload #24
    //   1408: ifeq -> 1411
    //   1411: iload #7
    //   1413: bipush #105
    //   1415: if_icmpeq -> 1437
    //   1418: iload #7
    //   1420: bipush #73
    //   1422: if_icmpeq -> 1437
    //   1425: iload #7
    //   1427: bipush #32
    //   1429: if_icmpne -> 1575
    //   1432: iload #25
    //   1434: ifeq -> 1575
    //   1437: iconst_1
    //   1438: istore_1
    //   1439: iconst_0
    //   1440: istore #15
    //   1442: iconst_0
    //   1443: istore #17
    //   1445: iload_2
    //   1446: ifeq -> 1588
    //   1449: iload_2
    //   1450: bipush #105
    //   1452: if_icmpne -> 1580
    //   1455: ldc2_w Infinity
    //   1458: dstore #8
    //   1460: dload #8
    //   1462: dstore #10
    //   1464: iload #26
    //   1466: ifeq -> 1474
    //   1469: dload #8
    //   1471: dneg
    //   1472: dstore #10
    //   1474: new gnu/math/DFloNum
    //   1477: dup
    //   1478: dload #10
    //   1480: invokespecial <init> : (D)V
    //   1483: astore #31
    //   1485: iload #17
    //   1487: istore_1
    //   1488: iload #7
    //   1490: bipush #101
    //   1492: if_icmpeq -> 1506
    //   1495: aload #31
    //   1497: astore #32
    //   1499: iload #7
    //   1501: bipush #69
    //   1503: if_icmpne -> 1513
    //   1506: aload #31
    //   1508: invokevirtual toExact : ()Lgnu/math/RatNum;
    //   1511: astore #32
    //   1513: iload #14
    //   1515: iload #19
    //   1517: if_icmpge -> 2165
    //   1520: iload #14
    //   1522: iconst_1
    //   1523: iadd
    //   1524: istore_1
    //   1525: aload_0
    //   1526: iload #14
    //   1528: caload
    //   1529: istore_3
    //   1530: iload_3
    //   1531: bipush #64
    //   1533: if_icmpne -> 1970
    //   1536: aload_0
    //   1537: iload_1
    //   1538: iload #19
    //   1540: iload_1
    //   1541: isub
    //   1542: iload #7
    //   1544: bipush #10
    //   1546: iload #5
    //   1548: invokestatic parseNumber : ([CIICII)Ljava/lang/Object;
    //   1551: astore #31
    //   1553: aload #31
    //   1555: astore_0
    //   1556: aload #31
    //   1558: instanceof java/lang/String
    //   1561: ifne -> 14
    //   1564: aload #31
    //   1566: instanceof gnu/math/RealNum
    //   1569: ifne -> 1933
    //   1572: ldc 'invalid complex polar constant'
    //   1574: areturn
    //   1575: iconst_0
    //   1576: istore_1
    //   1577: goto -> 1439
    //   1580: ldc2_w NaN
    //   1583: dstore #8
    //   1585: goto -> 1460
    //   1588: iload #16
    //   1590: ifge -> 1598
    //   1593: iload #13
    //   1595: iflt -> 1758
    //   1598: iload #12
    //   1600: istore_2
    //   1601: iload #12
    //   1603: iload #13
    //   1605: if_icmple -> 1619
    //   1608: iload #12
    //   1610: istore_2
    //   1611: iload #13
    //   1613: iflt -> 1619
    //   1616: iload #13
    //   1618: istore_2
    //   1619: aload #32
    //   1621: ifnull -> 1627
    //   1624: ldc 'floating-point number after fraction symbol '/''
    //   1626: areturn
    //   1627: new java/lang/String
    //   1630: dup
    //   1631: aload_0
    //   1632: iload_2
    //   1633: iload #14
    //   1635: iload_2
    //   1636: isub
    //   1637: invokespecial <init> : ([CII)V
    //   1640: astore #32
    //   1642: iload #15
    //   1644: istore_1
    //   1645: aload #32
    //   1647: astore #31
    //   1649: iload #16
    //   1651: iflt -> 1723
    //   1654: aload_0
    //   1655: iload #16
    //   1657: caload
    //   1658: invokestatic toLowerCase : (C)C
    //   1661: istore #4
    //   1663: iload #4
    //   1665: istore_1
    //   1666: aload #32
    //   1668: astore #31
    //   1670: iload #4
    //   1672: bipush #101
    //   1674: if_icmpeq -> 1723
    //   1677: iload #16
    //   1679: iload_2
    //   1680: isub
    //   1681: istore_1
    //   1682: new java/lang/StringBuilder
    //   1685: dup
    //   1686: invokespecial <init> : ()V
    //   1689: aload #32
    //   1691: iconst_0
    //   1692: iload_1
    //   1693: invokevirtual substring : (II)Ljava/lang/String;
    //   1696: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1699: bipush #101
    //   1701: invokevirtual append : (C)Ljava/lang/StringBuilder;
    //   1704: aload #32
    //   1706: iload_1
    //   1707: iconst_1
    //   1708: iadd
    //   1709: invokevirtual substring : (I)Ljava/lang/String;
    //   1712: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1715: invokevirtual toString : ()Ljava/lang/String;
    //   1718: astore #31
    //   1720: iload #4
    //   1722: istore_1
    //   1723: aload #31
    //   1725: invokestatic parseDouble : (Ljava/lang/String;)D
    //   1728: dstore #10
    //   1730: dload #10
    //   1732: dstore #8
    //   1734: iload #26
    //   1736: ifeq -> 1744
    //   1739: dload #10
    //   1741: dneg
    //   1742: dstore #8
    //   1744: new gnu/math/DFloNum
    //   1747: dup
    //   1748: dload #8
    //   1750: invokespecial <init> : (D)V
    //   1753: astore #31
    //   1755: goto -> 1488
    //   1758: aload_0
    //   1759: iload #12
    //   1761: iload #14
    //   1763: iload #12
    //   1765: isub
    //   1766: iload #4
    //   1768: iload #26
    //   1770: lload #29
    //   1772: invokestatic valueOf : ([CIIIZJ)Lgnu/math/IntNum;
    //   1775: astore #31
    //   1777: aload #32
    //   1779: ifnonnull -> 1827
    //   1782: iload_1
    //   1783: ifeq -> 2254
    //   1786: aload #31
    //   1788: invokevirtual isExact : ()Z
    //   1791: ifeq -> 2254
    //   1794: iload #21
    //   1796: ifeq -> 1923
    //   1799: aload #31
    //   1801: invokevirtual isZero : ()Z
    //   1804: ifeq -> 1923
    //   1807: dconst_0
    //   1808: dstore #8
    //   1810: new gnu/math/DFloNum
    //   1813: dup
    //   1814: dload #8
    //   1816: invokespecial <init> : (D)V
    //   1819: astore #31
    //   1821: iload #17
    //   1823: istore_1
    //   1824: goto -> 1488
    //   1827: aload #31
    //   1829: invokevirtual isZero : ()Z
    //   1832: ifeq -> 1911
    //   1835: aload #32
    //   1837: invokevirtual isZero : ()Z
    //   1840: istore #20
    //   1842: iload_1
    //   1843: ifeq -> 1891
    //   1846: iload #20
    //   1848: ifeq -> 1870
    //   1851: ldc2_w NaN
    //   1854: dstore #8
    //   1856: new gnu/math/DFloNum
    //   1859: dup
    //   1860: dload #8
    //   1862: invokespecial <init> : (D)V
    //   1865: astore #31
    //   1867: goto -> 1782
    //   1870: iload #21
    //   1872: ifeq -> 1883
    //   1875: ldc2_w -Infinity
    //   1878: dstore #8
    //   1880: goto -> 1856
    //   1883: ldc2_w Infinity
    //   1886: dstore #8
    //   1888: goto -> 1856
    //   1891: iload #20
    //   1893: ifeq -> 1899
    //   1896: ldc '0/0 is undefined'
    //   1898: areturn
    //   1899: aload #32
    //   1901: aload #31
    //   1903: invokestatic make : (Lgnu/math/IntNum;Lgnu/math/IntNum;)Lgnu/math/RatNum;
    //   1906: astore #31
    //   1908: goto -> 1867
    //   1911: aload #32
    //   1913: aload #31
    //   1915: invokestatic make : (Lgnu/math/IntNum;Lgnu/math/IntNum;)Lgnu/math/RatNum;
    //   1918: astore #31
    //   1920: goto -> 1782
    //   1923: aload #31
    //   1925: invokevirtual doubleValue : ()D
    //   1928: dstore #8
    //   1930: goto -> 1810
    //   1933: aload #31
    //   1935: checkcast gnu/math/RealNum
    //   1938: astore_0
    //   1939: aload #32
    //   1941: invokevirtual isZero : ()Z
    //   1944: ifeq -> 1963
    //   1947: aload_0
    //   1948: invokevirtual isExact : ()Z
    //   1951: ifne -> 1963
    //   1954: new gnu/math/DFloNum
    //   1957: dup
    //   1958: dconst_0
    //   1959: invokespecial <init> : (D)V
    //   1962: areturn
    //   1963: aload #32
    //   1965: aload_0
    //   1966: invokestatic polar : (Lgnu/math/RealNum;Lgnu/math/RealNum;)Lgnu/math/DComplex;
    //   1969: areturn
    //   1970: iload_3
    //   1971: bipush #45
    //   1973: if_icmpeq -> 1982
    //   1976: iload_3
    //   1977: bipush #43
    //   1979: if_icmpne -> 2071
    //   1982: iload_1
    //   1983: iconst_1
    //   1984: isub
    //   1985: istore_1
    //   1986: aload_0
    //   1987: iload_1
    //   1988: iload #19
    //   1990: iload_1
    //   1991: isub
    //   1992: iload #7
    //   1994: bipush #10
    //   1996: iload #5
    //   1998: invokestatic parseNumber : ([CIICII)Ljava/lang/Object;
    //   2001: astore_0
    //   2002: aload_0
    //   2003: instanceof java/lang/String
    //   2006: ifeq -> 2011
    //   2009: aload_0
    //   2010: areturn
    //   2011: aload_0
    //   2012: instanceof gnu/math/Complex
    //   2015: ifne -> 2043
    //   2018: new java/lang/StringBuilder
    //   2021: dup
    //   2022: invokespecial <init> : ()V
    //   2025: ldc 'invalid numeric constant ('
    //   2027: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2030: aload_0
    //   2031: invokevirtual append : (Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   2034: ldc ')'
    //   2036: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2039: invokevirtual toString : ()Ljava/lang/String;
    //   2042: areturn
    //   2043: aload_0
    //   2044: checkcast gnu/math/Complex
    //   2047: astore_0
    //   2048: aload_0
    //   2049: invokevirtual re : ()Lgnu/math/RealNum;
    //   2052: invokevirtual isZero : ()Z
    //   2055: ifne -> 2061
    //   2058: ldc 'invalid numeric constant'
    //   2060: areturn
    //   2061: aload #32
    //   2063: aload_0
    //   2064: invokevirtual im : ()Lgnu/math/RealNum;
    //   2067: invokestatic make : (Lgnu/math/RealNum;Lgnu/math/RealNum;)Lgnu/math/Complex;
    //   2070: areturn
    //   2071: iconst_0
    //   2072: istore_2
    //   2073: iload_3
    //   2074: invokestatic isLetter : (C)Z
    //   2077: ifne -> 2122
    //   2080: iload_1
    //   2081: iconst_1
    //   2082: isub
    //   2083: istore_1
    //   2084: iload_2
    //   2085: istore #4
    //   2087: iload_1
    //   2088: istore_2
    //   2089: iload #4
    //   2091: iconst_1
    //   2092: if_icmpne -> 2162
    //   2095: aload_0
    //   2096: iload_2
    //   2097: iconst_1
    //   2098: isub
    //   2099: caload
    //   2100: istore_1
    //   2101: iload_1
    //   2102: bipush #105
    //   2104: if_icmpeq -> 2113
    //   2107: iload_1
    //   2108: bipush #73
    //   2110: if_icmpne -> 2162
    //   2113: iload_2
    //   2114: iload #19
    //   2116: if_icmpge -> 2153
    //   2119: ldc 'junk after imaginary suffix 'i''
    //   2121: areturn
    //   2122: iload_2
    //   2123: iconst_1
    //   2124: iadd
    //   2125: istore #5
    //   2127: iload_1
    //   2128: istore_2
    //   2129: iload #5
    //   2131: istore #4
    //   2133: iload_1
    //   2134: iload #19
    //   2136: if_icmpeq -> 2089
    //   2139: aload_0
    //   2140: iload_1
    //   2141: caload
    //   2142: istore_3
    //   2143: iload_1
    //   2144: iconst_1
    //   2145: iadd
    //   2146: istore_1
    //   2147: iload #5
    //   2149: istore_2
    //   2150: goto -> 2073
    //   2153: invokestatic zero : ()Lgnu/math/IntNum;
    //   2156: aload #32
    //   2158: invokestatic make : (Lgnu/math/RealNum;Lgnu/math/RealNum;)Lgnu/math/Complex;
    //   2161: areturn
    //   2162: ldc 'excess junk after number'
    //   2164: areturn
    //   2165: aload #32
    //   2167: instanceof gnu/math/DFloNum
    //   2170: ifeq -> 2232
    //   2173: iload_1
    //   2174: ifle -> 2232
    //   2177: iload_1
    //   2178: bipush #101
    //   2180: if_icmpeq -> 2232
    //   2183: aload #32
    //   2185: invokevirtual doubleValue : ()D
    //   2188: dstore #8
    //   2190: iload_1
    //   2191: lookupswitch default -> 2232, 100 -> 2242, 102 -> 2235, 108 -> 2248, 115 -> 2235
    //   2232: aload #32
    //   2234: areturn
    //   2235: dload #8
    //   2237: d2f
    //   2238: invokestatic valueOf : (F)Ljava/lang/Float;
    //   2241: areturn
    //   2242: dload #8
    //   2244: invokestatic valueOf : (D)Ljava/lang/Double;
    //   2247: areturn
    //   2248: dload #8
    //   2250: invokestatic valueOf : (D)Ljava/math/BigDecimal;
    //   2253: areturn
    //   2254: iload #17
    //   2256: istore_1
    //   2257: goto -> 1488
    //   2260: iload_1
    //   2261: istore #14
    //   2263: goto -> 1401
    //   2266: iload #13
    //   2268: istore_2
    //   2269: goto -> 514
  }
  
  public static Object readCharacter(LispReader paramLispReader) throws IOException, SyntaxException {
    // Byte code:
    //   0: aload_0
    //   1: invokevirtual read : ()I
    //   4: istore_1
    //   5: iload_1
    //   6: ifge -> 16
    //   9: aload_0
    //   10: ldc_w 'unexpected EOF in character literal'
    //   13: invokevirtual eofError : (Ljava/lang/String;)V
    //   16: aload_0
    //   17: getfield tokenBufferLength : I
    //   20: istore_3
    //   21: aload_0
    //   22: iload_1
    //   23: invokevirtual tokenBufferAppend : (I)V
    //   26: aload_0
    //   27: aload_0
    //   28: invokevirtual read : ()I
    //   31: bipush #68
    //   33: invokestatic getCurrent : ()Lgnu/kawa/lispexpr/ReadTable;
    //   36: invokevirtual readToken : (ICLgnu/kawa/lispexpr/ReadTable;)V
    //   39: aload_0
    //   40: getfield tokenBuffer : [C
    //   43: astore #7
    //   45: aload_0
    //   46: getfield tokenBufferLength : I
    //   49: iload_3
    //   50: isub
    //   51: istore #4
    //   53: iload #4
    //   55: iconst_1
    //   56: if_icmpne -> 67
    //   59: aload #7
    //   61: iload_3
    //   62: caload
    //   63: invokestatic make : (I)Lgnu/text/Char;
    //   66: areturn
    //   67: new java/lang/String
    //   70: dup
    //   71: aload #7
    //   73: iload_3
    //   74: iload #4
    //   76: invokespecial <init> : ([CII)V
    //   79: astore #8
    //   81: aload #8
    //   83: invokestatic nameToChar : (Ljava/lang/String;)I
    //   86: istore_1
    //   87: iload_1
    //   88: iflt -> 96
    //   91: iload_1
    //   92: invokestatic make : (I)Lgnu/text/Char;
    //   95: areturn
    //   96: aload #7
    //   98: iload_3
    //   99: caload
    //   100: istore #5
    //   102: iload #5
    //   104: bipush #120
    //   106: if_icmpeq -> 116
    //   109: iload #5
    //   111: bipush #88
    //   113: if_icmpne -> 149
    //   116: iconst_0
    //   117: istore_2
    //   118: iconst_1
    //   119: istore_1
    //   120: iload_1
    //   121: iload #4
    //   123: if_icmpne -> 131
    //   126: iload_2
    //   127: invokestatic make : (I)Lgnu/text/Char;
    //   130: areturn
    //   131: aload #7
    //   133: iload_3
    //   134: iload_1
    //   135: iadd
    //   136: caload
    //   137: bipush #16
    //   139: invokestatic digit : (CI)I
    //   142: istore #6
    //   144: iload #6
    //   146: ifge -> 174
    //   149: iload #5
    //   151: bipush #8
    //   153: invokestatic digit : (II)I
    //   156: istore_2
    //   157: iload_2
    //   158: iflt -> 214
    //   161: iconst_1
    //   162: istore_1
    //   163: iload_1
    //   164: iload #4
    //   166: if_icmpne -> 196
    //   169: iload_2
    //   170: invokestatic make : (I)Lgnu/text/Char;
    //   173: areturn
    //   174: iload_2
    //   175: bipush #16
    //   177: imul
    //   178: iload #6
    //   180: iadd
    //   181: istore_2
    //   182: iload_2
    //   183: ldc_w 1114111
    //   186: if_icmpgt -> 149
    //   189: iload_1
    //   190: iconst_1
    //   191: iadd
    //   192: istore_1
    //   193: goto -> 120
    //   196: aload #7
    //   198: iload_3
    //   199: iload_1
    //   200: iadd
    //   201: caload
    //   202: bipush #8
    //   204: invokestatic digit : (CI)I
    //   207: istore #5
    //   209: iload #5
    //   211: ifge -> 245
    //   214: aload_0
    //   215: new java/lang/StringBuilder
    //   218: dup
    //   219: invokespecial <init> : ()V
    //   222: ldc_w 'unknown character name: '
    //   225: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   228: aload #8
    //   230: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   233: invokevirtual toString : ()Ljava/lang/String;
    //   236: invokevirtual error : (Ljava/lang/String;)V
    //   239: bipush #63
    //   241: invokestatic make : (I)Lgnu/text/Char;
    //   244: areturn
    //   245: iload_2
    //   246: bipush #8
    //   248: imul
    //   249: iload #5
    //   251: iadd
    //   252: istore_2
    //   253: iload_1
    //   254: iconst_1
    //   255: iadd
    //   256: istore_1
    //   257: goto -> 163
  }
  
  public static Object readNumberWithRadix(int paramInt1, LispReader paramLispReader, int paramInt2) throws IOException, SyntaxException {
    paramInt1 = paramLispReader.tokenBufferLength - paramInt1;
    paramLispReader.readToken(paramLispReader.read(), 'P', ReadTable.getCurrent());
    int i = paramLispReader.tokenBufferLength;
    if (paramInt1 == i) {
      paramLispReader.error("missing numeric token");
      return IntNum.zero();
    } 
    Object object2 = parseNumber(paramLispReader.tokenBuffer, paramInt1, i - paramInt1, false, paramInt2, 0);
    if (object2 instanceof String) {
      paramLispReader.error((String)object2);
      return IntNum.zero();
    } 
    Object object1 = object2;
    if (object2 == null) {
      paramLispReader.error("invalid numeric constant");
      return IntNum.zero();
    } 
    return object1;
  }
  
  public static SimpleVector readSimpleVector(LispReader paramLispReader, char paramChar) throws IOException, SyntaxException {
    Object object;
    int i = 0;
    while (true) {
      int j = paramLispReader.read();
      if (j < 0)
        paramLispReader.eofError("unexpected EOF reading uniform vector"); 
      int k = Character.digit((char)j, 10);
      if (k < 0) {
        if ((i != 8 && i != 16 && i != 32 && i != 64) || (paramChar == 'F' && i < 32) || j != 40) {
          paramLispReader.error("invalid uniform vector syntax");
          return null;
        } 
      } else {
        i = i * 10 + k;
        continue;
      } 
      object = ReaderParens.readList(paramLispReader, 40, -1, 41);
      if (LList.listLength(object, false) < 0) {
        paramLispReader.error("invalid elements in uniform vector syntax");
        return null;
      } 
      break;
    } 
    Sequence sequence = (Sequence)object;
    switch (paramChar) {
      default:
        return null;
      case 'F':
        switch (i) {
          default:
            switch (i) {
              default:
                break;
              case 8:
                return (SimpleVector)new S8Vector(sequence);
              case 16:
                return (SimpleVector)new S16Vector(sequence);
              case 32:
                return (SimpleVector)new S32Vector(sequence);
              case 64:
                break;
            } 
            return (SimpleVector)new S64Vector(sequence);
          case 32:
            return (SimpleVector)new F32Vector(sequence);
          case 64:
            break;
        } 
        return (SimpleVector)new F64Vector(sequence);
      case 'S':
      
      case 'U':
        break;
    } 
    switch (i) {
      default:
        return null;
      case 8:
        return (SimpleVector)new U8Vector(sequence);
      case 16:
        return (SimpleVector)new U16Vector(sequence);
      case 32:
        return (SimpleVector)new U32Vector(sequence);
      case 64:
        break;
    } 
    return (SimpleVector)new U64Vector(sequence);
  }
  
  public static Object readSpecial(LispReader paramLispReader) throws IOException, SyntaxException {
    Values values = null;
    int j = paramLispReader.read();
    if (j < 0)
      paramLispReader.eofError("unexpected EOF in #! special form"); 
    if (j == 47 && paramLispReader.getLineNumber() == 0 && paramLispReader.getColumnNumber() == 3) {
      ReaderIgnoreRestOfLine.getInstance().read(paramLispReader, 35, 1);
      return Values.empty;
    } 
    int i = paramLispReader.tokenBufferLength;
    paramLispReader.tokenBufferAppend(j);
    paramLispReader.readToken(paramLispReader.read(), 'D', ReadTable.getCurrent());
    j = paramLispReader.tokenBufferLength;
    String str = new String(paramLispReader.tokenBuffer, i, j - i);
    if (str.equals("optional"))
      return Special.optional; 
    if (str.equals("rest"))
      return Special.rest; 
    if (str.equals("key"))
      return Special.key; 
    if (str.equals("eof"))
      return Special.eof; 
    if (str.equals("void"))
      return QuoteExp.voidExp; 
    if (str.equals("default"))
      return Special.dfault; 
    if (str.equals("undefined"))
      return Special.undefined; 
    if (str.equals("abstract"))
      return Special.abstractSpecial; 
    if (!str.equals("null")) {
      paramLispReader.error("unknown named constant #!" + str);
      return null;
    } 
    return values;
  }
  
  private static IntNum valueOf(char[] paramArrayOfchar, int paramInt1, int paramInt2, int paramInt3, boolean paramBoolean, long paramLong) {
    if (paramInt2 + paramInt3 <= 28) {
      long l = paramLong;
      if (paramBoolean)
        l = -paramLong; 
      return IntNum.make(l);
    } 
    return IntNum.valueOf(paramArrayOfchar, paramInt1, paramInt2, paramInt3, paramBoolean);
  }
  
  Object handlePostfix(Object paramObject, ReadTable paramReadTable, int paramInt1, int paramInt2) throws IOException, SyntaxException {
    Object object = paramObject;
    if (paramObject == QuoteExp.voidExp)
      object = Values.empty; 
    while (true) {
      int i = this.port.peek();
      if (i < 0 || i != paramReadTable.postfixLookupOperator)
        return object; 
      this.port.read();
      if (!validPostfixLookupStart(this.port.peek(), paramReadTable)) {
        unread();
        return object;
      } 
      i = this.port.read();
      paramObject = readValues(i, paramReadTable.lookup(i), paramReadTable);
      paramObject = LList.list2(object, LList.list2(paramReadTable.makeSymbol("quasiquote"), paramObject));
      object = PairWithPosition.make(LispLanguage.lookup_sym, paramObject, this.port.getName(), paramInt1 + 1, paramInt2 + 1);
    } 
  }
  
  protected Object makeNil() {
    return LList.Empty;
  }
  
  protected Pair makePair(Object paramObject, int paramInt1, int paramInt2) {
    return makePair(paramObject, LList.Empty, paramInt1, paramInt2);
  }
  
  protected Pair makePair(Object paramObject1, Object paramObject2, int paramInt1, int paramInt2) {
    String str = this.port.getName();
    return (Pair)((str != null && paramInt1 >= 0) ? PairWithPosition.make(paramObject1, paramObject2, str, paramInt1 + 1, paramInt2 + 1) : Pair.make(paramObject1, paramObject2));
  }
  
  protected Object readAndHandleToken(int paramInt1, int paramInt2, ReadTable paramReadTable) throws IOException, SyntaxException {
    boolean bool;
    readToken(paramInt1, getReadCase(), paramReadTable);
    int i2 = this.tokenBufferLength;
    if (!this.seenEscapes) {
      Object object = parseNumber(this.tokenBuffer, paramInt2, i2 - paramInt2, false, 0, 1);
      if (object != null && !(object instanceof String))
        return object; 
    } 
    paramInt1 = getReadCase();
    int i = paramInt1;
    if (paramInt1 == 73) {
      i = 0;
      byte b = 0;
      paramInt1 = paramInt2;
      while (paramInt1 < i2) {
        byte b1;
        int i3;
        int i4;
        char c = this.tokenBuffer[paramInt1];
        if (c == Character.MAX_VALUE) {
          i3 = paramInt1 + 1;
          i4 = i;
          b1 = b;
        } else if (Character.isLowerCase(c)) {
          b1 = b + 1;
          i3 = paramInt1;
          i4 = i;
        } else {
          i3 = paramInt1;
          b1 = b;
          i4 = i;
          if (Character.isUpperCase(c)) {
            i4 = i + 1;
            i3 = paramInt1;
            b1 = b;
          } 
        } 
        paramInt1 = i3 + 1;
        b = b1;
        i = i4;
      } 
      if (b == 0) {
        i = 68;
      } else if (i == 0) {
        i = 85;
      } else {
        i = 80;
      } 
    } 
    if (i2 >= paramInt2 + 2 && this.tokenBuffer[i2 - 1] == '}' && this.tokenBuffer[i2 - 2] != Character.MAX_VALUE && peek() == 58) {
      bool = true;
    } else {
      bool = false;
    } 
    int k = -1;
    int j = -1;
    int m = -1;
    int i1 = 0;
    int n = paramInt2;
    paramInt1 = paramInt2;
    while (true) {
      Object object;
      if (n < i2) {
        int i3;
        int i4;
        char c = this.tokenBuffer[n];
        if (c == Character.MAX_VALUE) {
          if (++n < i2) {
            char[] arrayOfChar = this.tokenBuffer;
            i3 = paramInt1 + 1;
            arrayOfChar[paramInt1] = this.tokenBuffer[n];
            i4 = j;
            paramInt1 = i3;
            i3 = i1;
          } else {
            i3 = i1;
            i4 = j;
          } 
        } else {
          char c1;
          i3 = i1;
          i4 = j;
          int i5 = m;
          if (bool)
            if (c == '{') {
              if (j < 0) {
                i4 = paramInt1;
              } else {
                i4 = j;
                if (!i1)
                  i4 = j; 
              } 
              i3 = i1 + 1;
              i5 = m;
            } else {
              i3 = i1;
              i4 = j;
              i5 = m;
              if (c == '}')
                if (--i1 < 0) {
                  i3 = i1;
                  i4 = j;
                  i5 = m;
                } else {
                  i3 = i1;
                  i4 = j;
                  i5 = m;
                  if (i1 == 0)
                    if (m < 0) {
                      i5 = paramInt1;
                      i3 = i1;
                      i4 = j;
                    } else {
                      i3 = i1;
                      i4 = j;
                      i5 = m;
                    }  
                }  
            }  
          if (i3 > 0) {
            j = k;
            c1 = c;
          } else if (c == ':') {
            if (k >= 0) {
              j = -1;
            } else {
              j = paramInt1;
            } 
            c1 = c;
          } else if (i == 85) {
            c1 = Character.toUpperCase(c);
            j = k;
          } else {
            c1 = c;
            j = k;
            if (i == 68) {
              c1 = Character.toLowerCase(c);
              j = k;
            } 
          } 
          char[] arrayOfChar = this.tokenBuffer;
          k = paramInt1 + 1;
          arrayOfChar[paramInt1] = c1;
          paramInt1 = k;
          k = j;
          m = i5;
        } 
        n++;
        i1 = i3;
        j = i4;
        continue;
      } 
      i = paramInt1 - paramInt2;
      if (j >= 0 && m > j) {
        String str1;
        if (j > 0) {
          str1 = new String(this.tokenBuffer, paramInt2, j - paramInt2);
        } else {
          str1 = null;
        } 
        paramInt1 = j + 1;
        String str2 = new String(this.tokenBuffer, paramInt1, m - paramInt1);
        read();
        paramInt1 = read();
        object = readValues(paramInt1, paramReadTable.lookup(paramInt1), paramReadTable);
        if (!(object instanceof gnu.mapping.SimpleSymbol))
          error("expected identifier in symbol after '{URI}:'"); 
        return Symbol.valueOf(object.toString(), str2, str1);
      } 
      return (((ReadTable)object).initialColonIsKeyword && k == paramInt2 && i > 1) ? Keyword.make((new String(this.tokenBuffer, ++paramInt2, paramInt1 - paramInt2)).intern()) : ((((ReadTable)object).finalColonIsKeyword && k == paramInt1 - 1 && (i > 1 || this.seenEscapes)) ? Keyword.make((new String(this.tokenBuffer, paramInt2, i - 1)).intern()) : object.makeSymbol(new String(this.tokenBuffer, paramInt2, i)));
    } 
  }
  
  public Object readCommand() throws IOException, SyntaxException {
    return readObject();
  }
  
  public int readEscape() throws IOException, SyntaxException {
    int i = read();
    if (i < 0) {
      eofError("unexpected EOF in character literal");
      return -1;
    } 
    return readEscape(i);
  }
  
  public final int readEscape(int paramInt) throws IOException, SyntaxException {
    int i = paramInt;
    switch ((char)paramInt) {
      default:
        return paramInt;
      case 'a':
        paramInt = 7;
      case 'b':
        paramInt = 8;
      case 't':
        paramInt = 9;
      case 'n':
        paramInt = 10;
      case 'v':
        paramInt = 11;
      case 'f':
        paramInt = 12;
      case 'r':
        paramInt = 13;
      case 'e':
        paramInt = 27;
      case '"':
        paramInt = 34;
      case '\\':
        paramInt = 92;
      case '\t':
      case '\n':
      case '\r':
      case ' ':
        while (true) {
          if (i < 0) {
            eofError("unexpected EOF in literal");
            return -1;
          } 
          if (i != 10)
            if (i == 13) {
              if (peek() == 10)
                skip(); 
              i = 10;
            } else if (i != 32 && i != 9) {
              unread(i);
            } else {
              i = read();
              continue;
            }  
          paramInt = i;
          if (i == 10) {
            while (true) {
              paramInt = read();
              if (paramInt < 0) {
                eofError("unexpected EOF in literal");
                return -1;
              } 
              if (paramInt != 32 && paramInt != 9) {
                unread(paramInt);
                return -2;
              } 
            } 
            break;
          } 
        } 
      case 'M':
        if (read() != 45) {
          error("Invalid escape character syntax");
          return 63;
        } 
        i = read();
        paramInt = i;
        if (i == 92)
          paramInt = readEscape(); 
        return paramInt | 0x80;
      case 'C':
        if (read() != 45) {
          error("Invalid escape character syntax");
          return 63;
        } 
      case '^':
        i = read();
        paramInt = i;
        if (i == 92)
          paramInt = readEscape(); 
        return (paramInt == 63) ? 127 : (paramInt & 0x9F);
      case '0':
      case '1':
      case '2':
      case '3':
      case '4':
      case '5':
      case '6':
      case '7':
        i = paramInt - 48;
        paramInt = 0;
        while (true) {
          int j = paramInt + 1;
          paramInt = i;
          if (j < 3) {
            int k = read();
            paramInt = Character.digit((char)k, 8);
            if (paramInt >= 0) {
              i = (i << 3) + paramInt;
              paramInt = j;
              continue;
            } 
            paramInt = i;
            if (k >= 0) {
              unread(k);
              paramInt = i;
            } 
          } 
        } 
      case 'u':
        i = 0;
        paramInt = 4;
        while (true) {
          int j = paramInt - 1;
          paramInt = i;
          if (j >= 0) {
            paramInt = read();
            if (paramInt < 0)
              eofError("premature EOF in \\u escape"); 
            paramInt = Character.digit((char)paramInt, 16);
            if (paramInt < 0)
              error("non-hex character following \\u"); 
            i = i * 16 + paramInt;
            paramInt = j;
          } 
        } 
      case 'X':
      case 'x':
        break;
    } 
    return readHexEscape();
  }
  
  public int readHexEscape() throws IOException, SyntaxException {
    int i = 0;
    while (true) {
      int j = read();
      int k = Character.digit((char)j, 16);
      if (k >= 0) {
        i = (i << 4) + k;
        continue;
      } 
      if (j != 59 && j >= 0)
        unread(j); 
      return i;
    } 
  }
  
  public final void readNestedComment(char paramChar1, char paramChar2) throws IOException, SyntaxException {
    int i;
    int j = 1;
    int k = this.port.getLineNumber();
    int m = this.port.getColumnNumber();
    do {
      int n;
      int i1 = read();
      if (i1 == 124) {
        i1 = read();
        n = i1;
        i = j;
        if (i1 == paramChar1) {
          i = j - 1;
          n = i1;
        } 
      } else {
        n = i1;
        i = j;
        if (i1 == paramChar1) {
          i1 = read();
          n = i1;
          i = j;
          if (i1 == paramChar2) {
            i = j + 1;
            n = i1;
          } 
        } 
      } 
      if (n < 0) {
        eofError("unexpected end-of-file in " + paramChar1 + paramChar2 + " comment starting here", k + 1, m - 1);
        return;
      } 
      j = i;
    } while (i > 0);
  }
  
  public Object readObject() throws IOException, SyntaxException {
    char c = ((InPort)this.port).readState;
    int i = this.tokenBufferLength;
    ((InPort)this.port).readState = ' ';
    try {
      ReadTable readTable = ReadTable.getCurrent();
      while (true) {
        Object object1;
        int j = this.port.getLineNumber();
        int k = this.port.getColumnNumber();
        int m = this.port.read();
        if (m < 0) {
          object1 = Sequence.eofValue;
          return object1;
        } 
        Object object2 = readValues(m, (ReadTable)object1);
        if (object2 != Values.empty) {
          object1 = handlePostfix(object2, (ReadTable)object1, j, k);
          return object1;
        } 
      } 
    } finally {
      this.tokenBufferLength = i;
      ((InPort)this.port).readState = c;
    } 
  }
  
  public final Object readObject(int paramInt) throws IOException, SyntaxException {
    unread(paramInt);
    return readObject();
  }
  
  void readToken(int paramInt, char paramChar, ReadTable paramReadTable) throws IOException, SyntaxException {
    // Byte code:
    //   0: iconst_0
    //   1: istore #4
    //   3: iconst_0
    //   4: istore_2
    //   5: iload_1
    //   6: istore #5
    //   8: iload_2
    //   9: istore_1
    //   10: iload #5
    //   12: ifge -> 27
    //   15: iload #4
    //   17: ifeq -> 110
    //   20: aload_0
    //   21: ldc_w 'unexpected EOF between escapes'
    //   24: invokevirtual eofError : (Ljava/lang/String;)V
    //   27: aload_3
    //   28: iload #5
    //   30: invokevirtual lookup : (I)Lgnu/kawa/lispexpr/ReadTableEntry;
    //   33: astore #8
    //   35: aload #8
    //   37: invokevirtual getKind : ()I
    //   40: istore_2
    //   41: iload_2
    //   42: ifne -> 111
    //   45: iload #4
    //   47: ifeq -> 77
    //   50: aload_0
    //   51: ldc 65535
    //   53: invokevirtual tokenBufferAppend : (I)V
    //   56: aload_0
    //   57: iload #5
    //   59: invokevirtual tokenBufferAppend : (I)V
    //   62: iload #4
    //   64: istore_2
    //   65: aload_0
    //   66: invokevirtual read : ()I
    //   69: istore #5
    //   71: iload_2
    //   72: istore #4
    //   74: goto -> 10
    //   77: iload #5
    //   79: bipush #125
    //   81: if_icmpne -> 104
    //   84: iload_1
    //   85: iconst_1
    //   86: isub
    //   87: istore_1
    //   88: iload_1
    //   89: iflt -> 104
    //   92: aload_0
    //   93: iload #5
    //   95: invokevirtual tokenBufferAppend : (I)V
    //   98: iload #4
    //   100: istore_2
    //   101: goto -> 65
    //   104: aload_0
    //   105: iload #5
    //   107: invokevirtual unread : (I)V
    //   110: return
    //   111: iload_2
    //   112: istore #6
    //   114: iload #5
    //   116: aload_3
    //   117: getfield postfixLookupOperator : C
    //   120: if_icmpne -> 172
    //   123: iload_2
    //   124: istore #6
    //   126: iload #4
    //   128: ifne -> 172
    //   131: aload_0
    //   132: getfield port : Lgnu/text/LineBufferedReader;
    //   135: invokevirtual peek : ()I
    //   138: istore #7
    //   140: iload #7
    //   142: aload_3
    //   143: getfield postfixLookupOperator : C
    //   146: if_icmpne -> 156
    //   149: aload_0
    //   150: iload #5
    //   152: invokevirtual unread : (I)V
    //   155: return
    //   156: iload_2
    //   157: istore #6
    //   159: aload_0
    //   160: iload #7
    //   162: aload_3
    //   163: invokevirtual validPostfixLookupStart : (ILgnu/kawa/lispexpr/ReadTable;)Z
    //   166: ifeq -> 172
    //   169: iconst_5
    //   170: istore #6
    //   172: iload #6
    //   174: iconst_3
    //   175: if_icmpne -> 250
    //   178: aload_0
    //   179: invokevirtual read : ()I
    //   182: istore #5
    //   184: iload #5
    //   186: ifge -> 196
    //   189: aload_0
    //   190: ldc_w 'unexpected EOF after single escape'
    //   193: invokevirtual eofError : (Ljava/lang/String;)V
    //   196: iload #5
    //   198: istore_2
    //   199: aload_3
    //   200: getfield hexEscapeAfterBackslash : Z
    //   203: ifeq -> 228
    //   206: iload #5
    //   208: bipush #120
    //   210: if_icmpeq -> 223
    //   213: iload #5
    //   215: istore_2
    //   216: iload #5
    //   218: bipush #88
    //   220: if_icmpne -> 228
    //   223: aload_0
    //   224: invokevirtual readHexEscape : ()I
    //   227: istore_2
    //   228: aload_0
    //   229: ldc 65535
    //   231: invokevirtual tokenBufferAppend : (I)V
    //   234: aload_0
    //   235: iload_2
    //   236: invokevirtual tokenBufferAppend : (I)V
    //   239: aload_0
    //   240: iconst_1
    //   241: putfield seenEscapes : Z
    //   244: iload #4
    //   246: istore_2
    //   247: goto -> 65
    //   250: iload #6
    //   252: iconst_4
    //   253: if_icmpne -> 276
    //   256: iload #4
    //   258: ifne -> 271
    //   261: iconst_1
    //   262: istore_2
    //   263: aload_0
    //   264: iconst_1
    //   265: putfield seenEscapes : Z
    //   268: goto -> 65
    //   271: iconst_0
    //   272: istore_2
    //   273: goto -> 263
    //   276: iload #4
    //   278: ifeq -> 299
    //   281: aload_0
    //   282: ldc 65535
    //   284: invokevirtual tokenBufferAppend : (I)V
    //   287: aload_0
    //   288: iload #5
    //   290: invokevirtual tokenBufferAppend : (I)V
    //   293: iload #4
    //   295: istore_2
    //   296: goto -> 65
    //   299: iload_1
    //   300: istore_2
    //   301: iload #6
    //   303: tableswitch default -> 340, 1 -> 346, 2 -> 353, 3 -> 340, 4 -> 390, 5 -> 400, 6 -> 376
    //   340: iload #4
    //   342: istore_2
    //   343: goto -> 65
    //   346: aload_0
    //   347: iload #5
    //   349: invokevirtual unread : (I)V
    //   352: return
    //   353: iload_1
    //   354: istore_2
    //   355: iload #5
    //   357: bipush #123
    //   359: if_icmpne -> 376
    //   362: iload_1
    //   363: istore_2
    //   364: aload #8
    //   366: getstatic gnu/kawa/lispexpr/ReadTableEntry.brace : Lgnu/kawa/lispexpr/ReadTableEntry;
    //   369: if_acmpne -> 376
    //   372: iload_1
    //   373: iconst_1
    //   374: iadd
    //   375: istore_2
    //   376: aload_0
    //   377: iload #5
    //   379: invokevirtual tokenBufferAppend : (I)V
    //   382: iload_2
    //   383: istore_1
    //   384: iload #4
    //   386: istore_2
    //   387: goto -> 65
    //   390: iconst_1
    //   391: istore_2
    //   392: aload_0
    //   393: iconst_1
    //   394: putfield seenEscapes : Z
    //   397: goto -> 65
    //   400: aload_0
    //   401: iload #5
    //   403: invokevirtual unread : (I)V
    //   406: return
  }
  
  public Object readValues(int paramInt, ReadTable paramReadTable) throws IOException, SyntaxException {
    return readValues(paramInt, paramReadTable.lookup(paramInt), paramReadTable);
  }
  
  public Object readValues(int paramInt, ReadTableEntry paramReadTableEntry, ReadTable paramReadTable) throws IOException, SyntaxException {
    String str;
    int i = this.tokenBufferLength;
    this.seenEscapes = false;
    switch (paramReadTableEntry.getKind()) {
      default:
        return readAndHandleToken(paramInt, i, paramReadTable);
      case 0:
        str = "invalid character #\\" + (char)paramInt;
        if (this.interactive) {
          fatal(str);
          return Values.empty;
        } 
        error(str);
        return Values.empty;
      case 1:
        return Values.empty;
      case 5:
      case 6:
        break;
    } 
    return str.read(this, paramInt, -1);
  }
  
  protected void setCdr(Object paramObject1, Object paramObject2) {
    ((Pair)paramObject1).setCdrBackdoor(paramObject2);
  }
  
  protected boolean validPostfixLookupStart(int paramInt, ReadTable paramReadTable) throws IOException {
    if (paramInt >= 0 && paramInt != 58 && paramInt != paramReadTable.postfixLookupOperator) {
      if (paramInt == 44)
        return true; 
      paramInt = paramReadTable.lookup(paramInt).getKind();
      if (paramInt == 2 || paramInt == 6 || paramInt == 4 || paramInt == 3)
        return true; 
    } 
    return false;
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/gnu/kawa/lispexpr/LispReader.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */