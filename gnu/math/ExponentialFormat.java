package gnu.math;

import java.text.FieldPosition;
import java.text.Format;
import java.text.ParsePosition;

public class ExponentialFormat extends Format {
  static final double LOG10 = Math.log(10.0D);
  
  public int expDigits;
  
  public char exponentChar = 'E';
  
  public boolean exponentShowSign;
  
  public int fracDigits = -1;
  
  public boolean general;
  
  public int intDigits;
  
  public char overflowChar;
  
  public char padChar;
  
  public boolean showPlus;
  
  public int width;
  
  static boolean addOne(StringBuffer paramStringBuffer, int paramInt1, int paramInt2) {
    while (true) {
      if (paramInt2 == paramInt1) {
        paramStringBuffer.insert(paramInt2, '1');
        return true;
      } 
      char c = paramStringBuffer.charAt(--paramInt2);
      if (c != '9') {
        paramStringBuffer.setCharAt(paramInt2, (char)(c + 1));
        return false;
      } 
      paramStringBuffer.setCharAt(paramInt2, '0');
    } 
  }
  
  StringBuffer format(double paramDouble, String paramString, StringBuffer paramStringBuffer, FieldPosition paramFieldPosition) {
    // Byte code:
    //   0: aload_0
    //   1: getfield intDigits : I
    //   4: istore #19
    //   6: aload_0
    //   7: getfield fracDigits : I
    //   10: istore #23
    //   12: dload_1
    //   13: dconst_0
    //   14: dcmpg
    //   15: ifge -> 224
    //   18: iconst_1
    //   19: istore #12
    //   21: dload_1
    //   22: dstore #7
    //   24: iload #12
    //   26: ifeq -> 33
    //   29: dload_1
    //   30: dneg
    //   31: dstore #7
    //   33: aload #4
    //   35: invokevirtual length : ()I
    //   38: istore #26
    //   40: iconst_1
    //   41: istore #9
    //   43: iload #12
    //   45: ifeq -> 230
    //   48: iload #9
    //   50: istore #20
    //   52: iload #23
    //   54: iflt -> 69
    //   57: aload #4
    //   59: bipush #45
    //   61: invokevirtual append : (C)Ljava/lang/StringBuffer;
    //   64: pop
    //   65: iload #9
    //   67: istore #20
    //   69: aload #4
    //   71: invokevirtual length : ()I
    //   74: istore #11
    //   76: dload #7
    //   78: invokestatic isNaN : (D)Z
    //   81: ifne -> 92
    //   84: dload #7
    //   86: invokestatic isInfinite : (D)Z
    //   89: ifeq -> 258
    //   92: iconst_1
    //   93: istore #18
    //   95: iload #23
    //   97: iflt -> 105
    //   100: iload #18
    //   102: ifeq -> 595
    //   105: aload_3
    //   106: astore #5
    //   108: aload_3
    //   109: ifnonnull -> 119
    //   112: dload #7
    //   114: invokestatic toString : (D)Ljava/lang/String;
    //   117: astore #5
    //   119: aload #5
    //   121: bipush #69
    //   123: invokevirtual indexOf : (I)I
    //   126: istore #9
    //   128: iload #9
    //   130: iflt -> 583
    //   133: aload #4
    //   135: aload #5
    //   137: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   140: pop
    //   141: iload #9
    //   143: iload #11
    //   145: iadd
    //   146: istore #15
    //   148: aload #5
    //   150: iload #15
    //   152: iconst_1
    //   153: iadd
    //   154: invokevirtual charAt : (I)C
    //   157: bipush #45
    //   159: if_icmpne -> 264
    //   162: iconst_1
    //   163: istore #13
    //   165: iconst_0
    //   166: istore #14
    //   168: iload #13
    //   170: ifeq -> 270
    //   173: iconst_2
    //   174: istore #9
    //   176: iload #15
    //   178: iload #9
    //   180: iadd
    //   181: istore #10
    //   183: iload #14
    //   185: istore #9
    //   187: iload #10
    //   189: aload #4
    //   191: invokevirtual length : ()I
    //   194: if_icmpge -> 276
    //   197: iload #9
    //   199: bipush #10
    //   201: imul
    //   202: aload #4
    //   204: iload #10
    //   206: invokevirtual charAt : (I)C
    //   209: bipush #48
    //   211: isub
    //   212: iadd
    //   213: istore #9
    //   215: iload #10
    //   217: iconst_1
    //   218: iadd
    //   219: istore #10
    //   221: goto -> 187
    //   224: iconst_0
    //   225: istore #12
    //   227: goto -> 21
    //   230: aload_0
    //   231: getfield showPlus : Z
    //   234: ifeq -> 252
    //   237: aload #4
    //   239: bipush #43
    //   241: invokevirtual append : (C)Ljava/lang/StringBuffer;
    //   244: pop
    //   245: iload #9
    //   247: istore #20
    //   249: goto -> 69
    //   252: iconst_0
    //   253: istore #20
    //   255: goto -> 69
    //   258: iconst_0
    //   259: istore #18
    //   261: goto -> 95
    //   264: iconst_0
    //   265: istore #13
    //   267: goto -> 165
    //   270: iconst_1
    //   271: istore #9
    //   273: goto -> 176
    //   276: iload #9
    //   278: istore #10
    //   280: iload #13
    //   282: ifeq -> 290
    //   285: iload #9
    //   287: ineg
    //   288: istore #10
    //   290: aload #4
    //   292: iload #15
    //   294: invokevirtual setLength : (I)V
    //   297: iload #10
    //   299: istore #9
    //   301: iload #11
    //   303: istore #13
    //   305: iload #12
    //   307: ifeq -> 316
    //   310: iload #11
    //   312: iconst_1
    //   313: iadd
    //   314: istore #13
    //   316: aload #4
    //   318: iload #13
    //   320: iconst_1
    //   321: iadd
    //   322: invokevirtual deleteCharAt : (I)Ljava/lang/StringBuffer;
    //   325: pop
    //   326: aload #4
    //   328: invokevirtual length : ()I
    //   331: iload #13
    //   333: isub
    //   334: istore #10
    //   336: iload #10
    //   338: istore #11
    //   340: iload #10
    //   342: iconst_1
    //   343: if_icmple -> 383
    //   346: iload #10
    //   348: istore #11
    //   350: aload #4
    //   352: iload #13
    //   354: iload #10
    //   356: iadd
    //   357: iconst_1
    //   358: isub
    //   359: invokevirtual charAt : (I)C
    //   362: bipush #48
    //   364: if_icmpne -> 383
    //   367: iload #10
    //   369: iconst_1
    //   370: isub
    //   371: istore #11
    //   373: aload #4
    //   375: iload #13
    //   377: iload #11
    //   379: iadd
    //   380: invokevirtual setLength : (I)V
    //   383: iload #11
    //   385: iload #9
    //   387: isub
    //   388: iconst_1
    //   389: isub
    //   390: istore #15
    //   392: iload #9
    //   394: iload #19
    //   396: iconst_1
    //   397: isub
    //   398: isub
    //   399: istore #24
    //   401: iload #24
    //   403: ifge -> 697
    //   406: iload #24
    //   408: ineg
    //   409: istore #21
    //   411: iload #21
    //   413: sipush #1000
    //   416: if_icmplt -> 704
    //   419: iconst_4
    //   420: istore #9
    //   422: iload #9
    //   424: istore #14
    //   426: aload_0
    //   427: getfield expDigits : I
    //   430: iload #9
    //   432: if_icmple -> 441
    //   435: aload_0
    //   436: getfield expDigits : I
    //   439: istore #14
    //   441: iconst_1
    //   442: istore #25
    //   444: aload_0
    //   445: getfield general : Z
    //   448: ifne -> 736
    //   451: iconst_0
    //   452: istore #10
    //   454: iload #23
    //   456: ifge -> 760
    //   459: iconst_1
    //   460: istore #22
    //   462: aload_0
    //   463: getfield general : Z
    //   466: ifne -> 486
    //   469: iload #11
    //   471: istore #12
    //   473: iload #19
    //   475: istore #16
    //   477: iload #25
    //   479: istore #17
    //   481: iload #22
    //   483: ifeq -> 555
    //   486: iload #11
    //   488: iload #15
    //   490: isub
    //   491: istore #16
    //   493: iload #23
    //   495: istore #9
    //   497: iload #22
    //   499: ifeq -> 528
    //   502: iload #16
    //   504: bipush #7
    //   506: if_icmpge -> 766
    //   509: iload #16
    //   511: istore #12
    //   513: iload #12
    //   515: istore #9
    //   517: iload #11
    //   519: iload #12
    //   521: if_icmple -> 528
    //   524: iload #11
    //   526: istore #9
    //   528: aload_0
    //   529: getfield general : Z
    //   532: ifeq -> 773
    //   535: iload #16
    //   537: iflt -> 773
    //   540: iload #9
    //   542: iload #16
    //   544: isub
    //   545: iflt -> 773
    //   548: iconst_0
    //   549: istore #17
    //   551: iload #9
    //   553: istore #12
    //   555: iload #13
    //   557: iload #12
    //   559: iadd
    //   560: istore #12
    //   562: aload #4
    //   564: invokevirtual length : ()I
    //   567: iload #12
    //   569: if_icmpge -> 880
    //   572: aload #4
    //   574: bipush #48
    //   576: invokevirtual append : (C)Ljava/lang/StringBuffer;
    //   579: pop
    //   580: goto -> 562
    //   583: aload #5
    //   585: aload #4
    //   587: invokestatic toStringScientific : (Ljava/lang/String;Ljava/lang/StringBuffer;)I
    //   590: istore #9
    //   592: goto -> 301
    //   595: iload #19
    //   597: ifle -> 679
    //   600: iconst_1
    //   601: istore #9
    //   603: iload #23
    //   605: iload #9
    //   607: iadd
    //   608: istore #10
    //   610: dload #7
    //   612: invokestatic log : (D)D
    //   615: getstatic gnu/math/ExponentialFormat.LOG10 : D
    //   618: ddiv
    //   619: ldc2_w 1000.0
    //   622: dadd
    //   623: d2i
    //   624: istore #9
    //   626: iload #9
    //   628: ldc -2147483648
    //   630: if_icmpne -> 686
    //   633: iconst_0
    //   634: istore #9
    //   636: iload #10
    //   638: iload #9
    //   640: isub
    //   641: iconst_1
    //   642: isub
    //   643: istore #15
    //   645: dload #7
    //   647: iload #15
    //   649: invokestatic toScaledInt : (DI)Lgnu/math/IntNum;
    //   652: bipush #10
    //   654: aload #4
    //   656: invokevirtual format : (ILjava/lang/StringBuffer;)V
    //   659: iload #10
    //   661: iconst_1
    //   662: isub
    //   663: iload #15
    //   665: isub
    //   666: istore #9
    //   668: iload #11
    //   670: istore #13
    //   672: iload #10
    //   674: istore #11
    //   676: goto -> 392
    //   679: iload #19
    //   681: istore #9
    //   683: goto -> 603
    //   686: iload #9
    //   688: sipush #1000
    //   691: isub
    //   692: istore #9
    //   694: goto -> 636
    //   697: iload #24
    //   699: istore #21
    //   701: goto -> 411
    //   704: iload #21
    //   706: bipush #100
    //   708: if_icmplt -> 717
    //   711: iconst_3
    //   712: istore #9
    //   714: goto -> 422
    //   717: iload #21
    //   719: bipush #10
    //   721: if_icmplt -> 730
    //   724: iconst_2
    //   725: istore #9
    //   727: goto -> 422
    //   730: iconst_1
    //   731: istore #9
    //   733: goto -> 422
    //   736: aload_0
    //   737: getfield expDigits : I
    //   740: ifle -> 754
    //   743: aload_0
    //   744: getfield expDigits : I
    //   747: iconst_2
    //   748: iadd
    //   749: istore #10
    //   751: goto -> 454
    //   754: iconst_4
    //   755: istore #10
    //   757: goto -> 454
    //   760: iconst_0
    //   761: istore #22
    //   763: goto -> 462
    //   766: bipush #7
    //   768: istore #12
    //   770: goto -> 513
    //   773: iload #11
    //   775: istore #12
    //   777: iload #19
    //   779: istore #16
    //   781: iload #25
    //   783: istore #17
    //   785: iload #22
    //   787: ifeq -> 555
    //   790: aload_0
    //   791: getfield width : I
    //   794: ifgt -> 832
    //   797: iload #9
    //   799: istore #11
    //   801: iload #11
    //   803: istore #12
    //   805: iload #19
    //   807: istore #16
    //   809: iload #25
    //   811: istore #17
    //   813: iload #11
    //   815: ifgt -> 555
    //   818: iconst_1
    //   819: istore #12
    //   821: iload #19
    //   823: istore #16
    //   825: iload #25
    //   827: istore #17
    //   829: goto -> 555
    //   832: aload_0
    //   833: getfield width : I
    //   836: iload #20
    //   838: isub
    //   839: iload #14
    //   841: isub
    //   842: iconst_3
    //   843: isub
    //   844: istore #11
    //   846: iload #11
    //   848: istore #12
    //   850: iload #19
    //   852: ifge -> 862
    //   855: iload #11
    //   857: iload #19
    //   859: isub
    //   860: istore #12
    //   862: iload #12
    //   864: istore #11
    //   866: iload #12
    //   868: iload #9
    //   870: if_icmple -> 801
    //   873: iload #9
    //   875: istore #11
    //   877: goto -> 801
    //   880: iload #12
    //   882: aload #4
    //   884: invokevirtual length : ()I
    //   887: if_icmpne -> 997
    //   890: bipush #48
    //   892: istore #9
    //   894: iload #9
    //   896: bipush #53
    //   898: if_icmplt -> 1009
    //   901: iconst_1
    //   902: istore #9
    //   904: iload #15
    //   906: istore #11
    //   908: iload #9
    //   910: ifeq -> 935
    //   913: iload #15
    //   915: istore #11
    //   917: aload #4
    //   919: iload #13
    //   921: iload #12
    //   923: invokestatic addOne : (Ljava/lang/StringBuffer;II)Z
    //   926: ifeq -> 935
    //   929: iload #15
    //   931: iconst_1
    //   932: iadd
    //   933: istore #11
    //   935: aload #4
    //   937: invokevirtual length : ()I
    //   940: pop
    //   941: aload #4
    //   943: iload #12
    //   945: invokevirtual setLength : (I)V
    //   948: iload #13
    //   950: istore #11
    //   952: iload #12
    //   954: istore #9
    //   956: iload #16
    //   958: ifge -> 1015
    //   961: iload #16
    //   963: istore #9
    //   965: iload #9
    //   967: iconst_1
    //   968: iadd
    //   969: istore #12
    //   971: iload #11
    //   973: istore #9
    //   975: iload #12
    //   977: ifgt -> 1049
    //   980: aload #4
    //   982: iload #13
    //   984: bipush #48
    //   986: invokevirtual insert : (IC)Ljava/lang/StringBuffer;
    //   989: pop
    //   990: iload #12
    //   992: istore #9
    //   994: goto -> 965
    //   997: aload #4
    //   999: iload #12
    //   1001: invokevirtual charAt : (I)C
    //   1004: istore #9
    //   1006: goto -> 894
    //   1009: iconst_0
    //   1010: istore #9
    //   1012: goto -> 904
    //   1015: iload #13
    //   1017: iload #16
    //   1019: iadd
    //   1020: iload #9
    //   1022: if_icmple -> 1042
    //   1025: aload #4
    //   1027: bipush #48
    //   1029: invokevirtual append : (C)Ljava/lang/StringBuffer;
    //   1032: pop
    //   1033: iload #9
    //   1035: iconst_1
    //   1036: iadd
    //   1037: istore #9
    //   1039: goto -> 1015
    //   1042: iload #11
    //   1044: iload #16
    //   1046: iadd
    //   1047: istore #9
    //   1049: iload #18
    //   1051: ifeq -> 1172
    //   1054: iconst_0
    //   1055: istore #17
    //   1057: iload #17
    //   1059: ifeq -> 1192
    //   1062: aload #4
    //   1064: aload_0
    //   1065: getfield exponentChar : C
    //   1068: invokevirtual append : (C)Ljava/lang/StringBuffer;
    //   1071: pop
    //   1072: aload_0
    //   1073: getfield exponentShowSign : Z
    //   1076: ifne -> 1084
    //   1079: iload #24
    //   1081: ifge -> 1101
    //   1084: iload #24
    //   1086: iflt -> 1185
    //   1089: bipush #43
    //   1091: istore #6
    //   1093: aload #4
    //   1095: iload #6
    //   1097: invokevirtual append : (C)Ljava/lang/StringBuffer;
    //   1100: pop
    //   1101: aload #4
    //   1103: invokevirtual length : ()I
    //   1106: istore #15
    //   1108: aload #4
    //   1110: iload #21
    //   1112: invokevirtual append : (I)Ljava/lang/StringBuffer;
    //   1115: pop
    //   1116: aload #4
    //   1118: invokevirtual length : ()I
    //   1121: istore #11
    //   1123: aload_0
    //   1124: getfield expDigits : I
    //   1127: iload #11
    //   1129: iload #15
    //   1131: isub
    //   1132: isub
    //   1133: istore #11
    //   1135: iload #14
    //   1137: istore #12
    //   1139: iload #11
    //   1141: ifle -> 1195
    //   1144: iload #11
    //   1146: iconst_1
    //   1147: isub
    //   1148: istore #11
    //   1150: iload #14
    //   1152: istore #12
    //   1154: iload #11
    //   1156: iflt -> 1195
    //   1159: aload #4
    //   1161: iload #15
    //   1163: bipush #48
    //   1165: invokevirtual insert : (IC)Ljava/lang/StringBuffer;
    //   1168: pop
    //   1169: goto -> 1144
    //   1172: aload #4
    //   1174: iload #9
    //   1176: bipush #46
    //   1178: invokevirtual insert : (IC)Ljava/lang/StringBuffer;
    //   1181: pop
    //   1182: goto -> 1057
    //   1185: bipush #45
    //   1187: istore #6
    //   1189: goto -> 1093
    //   1192: iconst_0
    //   1193: istore #12
    //   1195: aload #4
    //   1197: invokevirtual length : ()I
    //   1200: istore #11
    //   1202: aload_0
    //   1203: getfield width : I
    //   1206: iload #11
    //   1208: iload #26
    //   1210: isub
    //   1211: isub
    //   1212: istore #14
    //   1214: iload #14
    //   1216: istore #11
    //   1218: iload #22
    //   1220: ifeq -> 1289
    //   1223: iload #9
    //   1225: iconst_1
    //   1226: iadd
    //   1227: aload #4
    //   1229: invokevirtual length : ()I
    //   1232: if_icmpeq -> 1255
    //   1235: iload #14
    //   1237: istore #11
    //   1239: aload #4
    //   1241: iload #9
    //   1243: iconst_1
    //   1244: iadd
    //   1245: invokevirtual charAt : (I)C
    //   1248: aload_0
    //   1249: getfield exponentChar : C
    //   1252: if_icmpne -> 1289
    //   1255: aload_0
    //   1256: getfield width : I
    //   1259: ifle -> 1271
    //   1262: iload #14
    //   1264: istore #11
    //   1266: iload #14
    //   1268: ifle -> 1289
    //   1271: iload #14
    //   1273: iconst_1
    //   1274: isub
    //   1275: istore #11
    //   1277: aload #4
    //   1279: iload #9
    //   1281: iconst_1
    //   1282: iadd
    //   1283: bipush #48
    //   1285: invokevirtual insert : (IC)Ljava/lang/StringBuffer;
    //   1288: pop
    //   1289: iload #11
    //   1291: ifge -> 1301
    //   1294: aload_0
    //   1295: getfield width : I
    //   1298: ifgt -> 1448
    //   1301: iload #17
    //   1303: ifeq -> 1329
    //   1306: iload #12
    //   1308: aload_0
    //   1309: getfield expDigits : I
    //   1312: if_icmple -> 1329
    //   1315: aload_0
    //   1316: getfield expDigits : I
    //   1319: ifle -> 1329
    //   1322: aload_0
    //   1323: getfield overflowChar : C
    //   1326: ifne -> 1448
    //   1329: iload #11
    //   1331: istore #9
    //   1333: iload #16
    //   1335: ifgt -> 1370
    //   1338: iload #11
    //   1340: ifgt -> 1354
    //   1343: iload #11
    //   1345: istore #9
    //   1347: aload_0
    //   1348: getfield width : I
    //   1351: ifgt -> 1370
    //   1354: aload #4
    //   1356: iload #13
    //   1358: bipush #48
    //   1360: invokevirtual insert : (IC)Ljava/lang/StringBuffer;
    //   1363: pop
    //   1364: iload #11
    //   1366: iconst_1
    //   1367: isub
    //   1368: istore #9
    //   1370: iload #9
    //   1372: istore #11
    //   1374: iload #17
    //   1376: ifne -> 1422
    //   1379: iload #9
    //   1381: istore #11
    //   1383: aload_0
    //   1384: getfield width : I
    //   1387: ifle -> 1422
    //   1390: iload #10
    //   1392: iconst_1
    //   1393: isub
    //   1394: istore #10
    //   1396: iload #9
    //   1398: istore #11
    //   1400: iload #10
    //   1402: iflt -> 1422
    //   1405: aload #4
    //   1407: bipush #32
    //   1409: invokevirtual append : (C)Ljava/lang/StringBuffer;
    //   1412: pop
    //   1413: iload #9
    //   1415: iconst_1
    //   1416: isub
    //   1417: istore #9
    //   1419: goto -> 1390
    //   1422: iload #11
    //   1424: iconst_1
    //   1425: isub
    //   1426: istore #11
    //   1428: iload #11
    //   1430: iflt -> 1492
    //   1433: aload #4
    //   1435: iload #26
    //   1437: aload_0
    //   1438: getfield padChar : C
    //   1441: invokevirtual insert : (IC)Ljava/lang/StringBuffer;
    //   1444: pop
    //   1445: goto -> 1422
    //   1448: aload_0
    //   1449: getfield overflowChar : C
    //   1452: ifeq -> 1492
    //   1455: aload #4
    //   1457: iload #26
    //   1459: invokevirtual setLength : (I)V
    //   1462: aload_0
    //   1463: getfield width : I
    //   1466: istore #9
    //   1468: iload #9
    //   1470: iconst_1
    //   1471: isub
    //   1472: istore #9
    //   1474: iload #9
    //   1476: iflt -> 1492
    //   1479: aload #4
    //   1481: aload_0
    //   1482: getfield overflowChar : C
    //   1485: invokevirtual append : (C)Ljava/lang/StringBuffer;
    //   1488: pop
    //   1489: goto -> 1468
    //   1492: aload #4
    //   1494: areturn
  }
  
  public StringBuffer format(double paramDouble, StringBuffer paramStringBuffer, FieldPosition paramFieldPosition) {
    if (this.fracDigits < 0) {
      String str1 = Double.toString(paramDouble);
      return format(paramDouble, str1, paramStringBuffer, paramFieldPosition);
    } 
    String str = null;
    return format(paramDouble, str, paramStringBuffer, paramFieldPosition);
  }
  
  public StringBuffer format(float paramFloat, StringBuffer paramStringBuffer, FieldPosition paramFieldPosition) {
    double d = paramFloat;
    if (this.fracDigits < 0) {
      String str1 = Float.toString(paramFloat);
      return format(d, str1, paramStringBuffer, paramFieldPosition);
    } 
    String str = null;
    return format(d, str, paramStringBuffer, paramFieldPosition);
  }
  
  public StringBuffer format(long paramLong, StringBuffer paramStringBuffer, FieldPosition paramFieldPosition) {
    return format(paramLong, paramStringBuffer, paramFieldPosition);
  }
  
  public StringBuffer format(Object paramObject, StringBuffer paramStringBuffer, FieldPosition paramFieldPosition) {
    return format(((RealNum)paramObject).doubleValue(), paramStringBuffer, paramFieldPosition);
  }
  
  public Number parse(String paramString, ParsePosition paramParsePosition) {
    throw new Error("ExponentialFormat.parse - not implemented");
  }
  
  public Object parseObject(String paramString, ParsePosition paramParsePosition) {
    throw new Error("ExponentialFormat.parseObject - not implemented");
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/gnu/math/ExponentialFormat.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */