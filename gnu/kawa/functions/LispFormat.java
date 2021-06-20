package gnu.kawa.functions;

import gnu.lists.Sequence;
import gnu.text.CompoundFormat;
import java.text.Format;
import java.text.ParseException;
import java.util.Vector;

public class LispFormat extends CompoundFormat {
  public static final String paramFromCount = "<from count>";
  
  public static final String paramFromList = "<from list>";
  
  public static final String paramUnspecified = "<unspecified>";
  
  public LispFormat(String paramString) throws ParseException {
    this(paramString.toCharArray());
  }
  
  public LispFormat(char[] paramArrayOfchar) throws ParseException {
    this(paramArrayOfchar, 0, paramArrayOfchar.length);
  }
  
  public LispFormat(char[] paramArrayOfchar, int paramInt1, int paramInt2) throws ParseException {
    // Byte code:
    //   0: aload_0
    //   1: aconst_null
    //   2: iconst_0
    //   3: invokespecial <init> : ([Ljava/text/Format;I)V
    //   6: iconst_m1
    //   7: istore #7
    //   9: iconst_0
    //   10: istore #6
    //   12: new java/lang/StringBuffer
    //   15: dup
    //   16: bipush #100
    //   18: invokespecial <init> : (I)V
    //   21: astore #18
    //   23: new java/util/Stack
    //   26: dup
    //   27: invokespecial <init> : ()V
    //   30: astore #19
    //   32: iload_2
    //   33: iload_3
    //   34: iadd
    //   35: istore #10
    //   37: iload_2
    //   38: istore #5
    //   40: iload #7
    //   42: istore_2
    //   43: iload #6
    //   45: istore_3
    //   46: iload #5
    //   48: iload #10
    //   50: if_icmpge -> 62
    //   53: aload_1
    //   54: iload #5
    //   56: caload
    //   57: bipush #126
    //   59: if_icmpne -> 91
    //   62: aload #18
    //   64: invokevirtual length : ()I
    //   67: ifle -> 91
    //   70: aload #19
    //   72: new gnu/text/LiteralFormat
    //   75: dup
    //   76: aload #18
    //   78: invokespecial <init> : (Ljava/lang/StringBuffer;)V
    //   81: invokevirtual push : (Ljava/lang/Object;)Ljava/lang/Object;
    //   84: pop
    //   85: aload #18
    //   87: iconst_0
    //   88: invokevirtual setLength : (I)V
    //   91: iload #5
    //   93: iload #10
    //   95: if_icmplt -> 113
    //   98: iload #5
    //   100: iload #10
    //   102: if_icmple -> 2948
    //   105: new java/lang/IndexOutOfBoundsException
    //   108: dup
    //   109: invokespecial <init> : ()V
    //   112: athrow
    //   113: iload #5
    //   115: iconst_1
    //   116: iadd
    //   117: istore #7
    //   119: aload_1
    //   120: iload #5
    //   122: caload
    //   123: istore #4
    //   125: iload #4
    //   127: bipush #126
    //   129: if_icmpeq -> 147
    //   132: aload #18
    //   134: iload #4
    //   136: invokevirtual append : (C)Ljava/lang/StringBuffer;
    //   139: pop
    //   140: iload #7
    //   142: istore #5
    //   144: goto -> 46
    //   147: aload #19
    //   149: invokevirtual size : ()I
    //   152: istore #6
    //   154: iload #7
    //   156: iconst_1
    //   157: iadd
    //   158: istore #5
    //   160: aload_1
    //   161: iload #7
    //   163: caload
    //   164: istore #4
    //   166: iload #4
    //   168: bipush #35
    //   170: if_icmpne -> 235
    //   173: aload #19
    //   175: ldc '<from count>'
    //   177: invokevirtual push : (Ljava/lang/Object;)Ljava/lang/Object;
    //   180: pop
    //   181: aload_1
    //   182: iload #5
    //   184: caload
    //   185: istore #4
    //   187: iload #5
    //   189: iconst_1
    //   190: iadd
    //   191: istore #5
    //   193: iload #4
    //   195: bipush #44
    //   197: if_icmpeq -> 497
    //   200: iconst_0
    //   201: istore #16
    //   203: iconst_0
    //   204: istore #15
    //   206: iload #5
    //   208: istore #7
    //   210: iload #4
    //   212: bipush #58
    //   214: if_icmpne -> 512
    //   217: iconst_1
    //   218: istore #16
    //   220: aload_1
    //   221: iload #7
    //   223: caload
    //   224: istore #4
    //   226: iload #7
    //   228: iconst_1
    //   229: iadd
    //   230: istore #7
    //   232: goto -> 210
    //   235: iload #4
    //   237: bipush #118
    //   239: if_icmpeq -> 249
    //   242: iload #4
    //   244: bipush #86
    //   246: if_icmpne -> 272
    //   249: aload #19
    //   251: ldc '<from list>'
    //   253: invokevirtual push : (Ljava/lang/Object;)Ljava/lang/Object;
    //   256: pop
    //   257: aload_1
    //   258: iload #5
    //   260: caload
    //   261: istore #4
    //   263: iload #5
    //   265: iconst_1
    //   266: iadd
    //   267: istore #5
    //   269: goto -> 193
    //   272: iload #4
    //   274: bipush #45
    //   276: if_icmpeq -> 289
    //   279: iload #4
    //   281: bipush #10
    //   283: invokestatic digit : (CI)I
    //   286: iflt -> 438
    //   289: iload #4
    //   291: bipush #45
    //   293: if_icmpne -> 387
    //   296: iconst_1
    //   297: istore #15
    //   299: iload #15
    //   301: ifeq -> 3008
    //   304: iload #5
    //   306: iconst_1
    //   307: iadd
    //   308: istore #7
    //   310: aload_1
    //   311: iload #5
    //   313: caload
    //   314: istore #4
    //   316: iload #7
    //   318: istore #5
    //   320: iconst_0
    //   321: istore #7
    //   323: iload #5
    //   325: istore #8
    //   327: iload #4
    //   329: bipush #10
    //   331: invokestatic digit : (CI)I
    //   334: istore #9
    //   336: iload #9
    //   338: ifge -> 393
    //   341: iload #8
    //   343: iload #5
    //   345: isub
    //   346: bipush #8
    //   348: if_icmpge -> 418
    //   351: iload #7
    //   353: istore #5
    //   355: iload #15
    //   357: ifeq -> 365
    //   360: iload #7
    //   362: ineg
    //   363: istore #5
    //   365: iload #5
    //   367: invokestatic make : (I)Lgnu/math/IntNum;
    //   370: astore #17
    //   372: aload #19
    //   374: aload #17
    //   376: invokevirtual push : (Ljava/lang/Object;)Ljava/lang/Object;
    //   379: pop
    //   380: iload #8
    //   382: istore #5
    //   384: goto -> 193
    //   387: iconst_0
    //   388: istore #15
    //   390: goto -> 299
    //   393: iload #7
    //   395: bipush #10
    //   397: imul
    //   398: iload #9
    //   400: iadd
    //   401: istore #7
    //   403: aload_1
    //   404: iload #8
    //   406: caload
    //   407: istore #4
    //   409: iload #8
    //   411: iconst_1
    //   412: iadd
    //   413: istore #8
    //   415: goto -> 327
    //   418: aload_1
    //   419: iload #5
    //   421: iload #8
    //   423: iload #5
    //   425: isub
    //   426: bipush #10
    //   428: iload #15
    //   430: invokestatic valueOf : ([CIIIZ)Lgnu/math/IntNum;
    //   433: astore #17
    //   435: goto -> 372
    //   438: iload #4
    //   440: bipush #39
    //   442: if_icmpne -> 479
    //   445: iload #5
    //   447: iconst_1
    //   448: iadd
    //   449: istore #7
    //   451: aload #19
    //   453: aload_1
    //   454: iload #5
    //   456: caload
    //   457: invokestatic make : (I)Lgnu/text/Char;
    //   460: invokevirtual push : (Ljava/lang/Object;)Ljava/lang/Object;
    //   463: pop
    //   464: iload #7
    //   466: iconst_1
    //   467: iadd
    //   468: istore #5
    //   470: aload_1
    //   471: iload #7
    //   473: caload
    //   474: istore #4
    //   476: goto -> 193
    //   479: iload #4
    //   481: bipush #44
    //   483: if_icmpne -> 3005
    //   486: aload #19
    //   488: ldc '<unspecified>'
    //   490: invokevirtual push : (Ljava/lang/Object;)Ljava/lang/Object;
    //   493: pop
    //   494: goto -> 193
    //   497: aload_1
    //   498: iload #5
    //   500: caload
    //   501: istore #4
    //   503: iload #5
    //   505: iconst_1
    //   506: iadd
    //   507: istore #5
    //   509: goto -> 166
    //   512: iload #4
    //   514: bipush #64
    //   516: if_icmpne -> 525
    //   519: iconst_1
    //   520: istore #15
    //   522: goto -> 220
    //   525: iload #4
    //   527: invokestatic toUpperCase : (C)C
    //   530: istore #4
    //   532: aload #19
    //   534: invokevirtual size : ()I
    //   537: iload #6
    //   539: isub
    //   540: istore #5
    //   542: iload #4
    //   544: lookupswitch default -> 844, 10 -> 2559, 33 -> 2617, 36 -> 1069, 37 -> 2912, 38 -> 2664, 40 -> 1415, 41 -> 1502, 42 -> 1392, 59 -> 2268, 60 -> 1760, 62 -> 1862, 63 -> 1571, 65 -> 1240, 66 -> 874, 67 -> 1356, 68 -> 874, 69 -> 1069, 70 -> 1069, 71 -> 1069, 73 -> 2683, 79 -> 874, 80 -> 1057, 82 -> 874, 83 -> 1240, 84 -> 2625, 87 -> 1240, 88 -> 874, 89 -> 1240, 91 -> 2160, 93 -> 2411, 94 -> 2522, 95 -> 2719, 123 -> 1602, 124 -> 2833, 125 -> 1672, 126 -> 2813
    //   844: new java/text/ParseException
    //   847: dup
    //   848: new java/lang/StringBuilder
    //   851: dup
    //   852: invokespecial <init> : ()V
    //   855: ldc 'unrecognized format specifier ~'
    //   857: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   860: iload #4
    //   862: invokevirtual append : (C)Ljava/lang/StringBuilder;
    //   865: invokevirtual toString : ()Ljava/lang/String;
    //   868: iload #7
    //   870: invokespecial <init> : (Ljava/lang/String;I)V
    //   873: athrow
    //   874: iload #6
    //   876: istore #8
    //   878: iload #4
    //   880: bipush #82
    //   882: if_icmpne -> 1009
    //   885: aload #19
    //   887: iload #8
    //   889: invokestatic getParam : (Ljava/util/Vector;I)I
    //   892: istore #5
    //   894: iload #8
    //   896: iconst_1
    //   897: iadd
    //   898: istore #8
    //   900: aload #19
    //   902: iload #8
    //   904: invokestatic getParam : (Ljava/util/Vector;I)I
    //   907: istore #11
    //   909: aload #19
    //   911: iload #8
    //   913: iconst_1
    //   914: iadd
    //   915: invokestatic getParam : (Ljava/util/Vector;I)I
    //   918: istore #12
    //   920: aload #19
    //   922: iload #8
    //   924: iconst_2
    //   925: iadd
    //   926: invokestatic getParam : (Ljava/util/Vector;I)I
    //   929: istore #13
    //   931: aload #19
    //   933: iload #8
    //   935: iconst_3
    //   936: iadd
    //   937: invokestatic getParam : (Ljava/util/Vector;I)I
    //   940: istore #14
    //   942: iconst_0
    //   943: istore #8
    //   945: iload #16
    //   947: ifeq -> 955
    //   950: iconst_0
    //   951: iconst_1
    //   952: ior
    //   953: istore #8
    //   955: iload #8
    //   957: istore #9
    //   959: iload #15
    //   961: ifeq -> 970
    //   964: iload #8
    //   966: iconst_2
    //   967: ior
    //   968: istore #9
    //   970: iload #5
    //   972: iload #11
    //   974: iload #12
    //   976: iload #13
    //   978: iload #14
    //   980: iload #9
    //   982: invokestatic getInstance : (IIIIII)Ljava/text/Format;
    //   985: astore #17
    //   987: aload #19
    //   989: iload #6
    //   991: invokevirtual setSize : (I)V
    //   994: aload #19
    //   996: aload #17
    //   998: invokevirtual push : (Ljava/lang/Object;)Ljava/lang/Object;
    //   1001: pop
    //   1002: iload #7
    //   1004: istore #5
    //   1006: goto -> 46
    //   1009: iload #4
    //   1011: bipush #68
    //   1013: if_icmpne -> 1023
    //   1016: bipush #10
    //   1018: istore #5
    //   1020: goto -> 900
    //   1023: iload #4
    //   1025: bipush #79
    //   1027: if_icmpne -> 1037
    //   1030: bipush #8
    //   1032: istore #5
    //   1034: goto -> 900
    //   1037: iload #4
    //   1039: bipush #88
    //   1041: if_icmpne -> 1051
    //   1044: bipush #16
    //   1046: istore #5
    //   1048: goto -> 900
    //   1051: iconst_2
    //   1052: istore #5
    //   1054: goto -> 900
    //   1057: iload #16
    //   1059: iload #15
    //   1061: invokestatic getInstance : (ZZ)Lgnu/kawa/functions/LispPluralFormat;
    //   1064: astore #17
    //   1066: goto -> 987
    //   1069: new gnu/kawa/functions/LispRealFormat
    //   1072: dup
    //   1073: invokespecial <init> : ()V
    //   1076: astore #17
    //   1078: aload #17
    //   1080: iload #4
    //   1082: putfield op : C
    //   1085: aload #17
    //   1087: aload #19
    //   1089: iload #6
    //   1091: invokestatic getParam : (Ljava/util/Vector;I)I
    //   1094: putfield arg1 : I
    //   1097: aload #17
    //   1099: aload #19
    //   1101: iload #6
    //   1103: iconst_1
    //   1104: iadd
    //   1105: invokestatic getParam : (Ljava/util/Vector;I)I
    //   1108: putfield arg2 : I
    //   1111: aload #17
    //   1113: aload #19
    //   1115: iload #6
    //   1117: iconst_2
    //   1118: iadd
    //   1119: invokestatic getParam : (Ljava/util/Vector;I)I
    //   1122: putfield arg3 : I
    //   1125: aload #17
    //   1127: aload #19
    //   1129: iload #6
    //   1131: iconst_3
    //   1132: iadd
    //   1133: invokestatic getParam : (Ljava/util/Vector;I)I
    //   1136: putfield arg4 : I
    //   1139: iload #4
    //   1141: bipush #36
    //   1143: if_icmpeq -> 1203
    //   1146: aload #17
    //   1148: aload #19
    //   1150: iload #6
    //   1152: iconst_4
    //   1153: iadd
    //   1154: invokestatic getParam : (Ljava/util/Vector;I)I
    //   1157: putfield arg5 : I
    //   1160: iload #4
    //   1162: bipush #69
    //   1164: if_icmpeq -> 1174
    //   1167: iload #4
    //   1169: bipush #71
    //   1171: if_icmpne -> 1203
    //   1174: aload #17
    //   1176: aload #19
    //   1178: iload #6
    //   1180: iconst_5
    //   1181: iadd
    //   1182: invokestatic getParam : (Ljava/util/Vector;I)I
    //   1185: putfield arg6 : I
    //   1188: aload #17
    //   1190: aload #19
    //   1192: iload #6
    //   1194: bipush #6
    //   1196: iadd
    //   1197: invokestatic getParam : (Ljava/util/Vector;I)I
    //   1200: putfield arg7 : I
    //   1203: aload #17
    //   1205: iload #15
    //   1207: putfield showPlus : Z
    //   1210: aload #17
    //   1212: iload #16
    //   1214: putfield internalPad : Z
    //   1217: aload #17
    //   1219: getfield argsUsed : I
    //   1222: ifne -> 1237
    //   1225: aload #17
    //   1227: aconst_null
    //   1228: iconst_0
    //   1229: invokevirtual resolve : ([Ljava/lang/Object;I)Ljava/text/Format;
    //   1232: astore #17
    //   1234: goto -> 987
    //   1237: goto -> 987
    //   1240: iload #4
    //   1242: bipush #65
    //   1244: if_icmpeq -> 1343
    //   1247: iconst_1
    //   1248: istore #16
    //   1250: iload #16
    //   1252: invokestatic getInstance : (Z)Lgnu/kawa/functions/ObjectFormat;
    //   1255: astore #17
    //   1257: iload #5
    //   1259: ifle -> 3002
    //   1262: aload #19
    //   1264: iload #6
    //   1266: invokestatic getParam : (Ljava/util/Vector;I)I
    //   1269: istore #8
    //   1271: aload #19
    //   1273: iload #6
    //   1275: iconst_1
    //   1276: iadd
    //   1277: invokestatic getParam : (Ljava/util/Vector;I)I
    //   1280: istore #9
    //   1282: aload #19
    //   1284: iload #6
    //   1286: iconst_2
    //   1287: iadd
    //   1288: invokestatic getParam : (Ljava/util/Vector;I)I
    //   1291: istore #11
    //   1293: aload #19
    //   1295: iload #6
    //   1297: iconst_3
    //   1298: iadd
    //   1299: invokestatic getParam : (Ljava/util/Vector;I)I
    //   1302: istore #12
    //   1304: aload #17
    //   1306: checkcast gnu/text/ReportFormat
    //   1309: astore #17
    //   1311: iload #15
    //   1313: ifeq -> 1349
    //   1316: iconst_0
    //   1317: istore #5
    //   1319: new gnu/kawa/functions/LispObjectFormat
    //   1322: dup
    //   1323: aload #17
    //   1325: iload #8
    //   1327: iload #9
    //   1329: iload #11
    //   1331: iload #12
    //   1333: iload #5
    //   1335: invokespecial <init> : (Lgnu/text/ReportFormat;IIIII)V
    //   1338: astore #17
    //   1340: goto -> 987
    //   1343: iconst_0
    //   1344: istore #16
    //   1346: goto -> 1250
    //   1349: bipush #100
    //   1351: istore #5
    //   1353: goto -> 1319
    //   1356: iload #5
    //   1358: ifle -> 1385
    //   1361: aload #19
    //   1363: iload #6
    //   1365: invokestatic getParam : (Ljava/util/Vector;I)I
    //   1368: istore #5
    //   1370: iload #5
    //   1372: iconst_1
    //   1373: iload #15
    //   1375: iload #16
    //   1377: invokestatic getInstance : (IIZZ)Lgnu/kawa/functions/LispCharacterFormat;
    //   1380: astore #17
    //   1382: goto -> 987
    //   1385: ldc -1610612736
    //   1387: istore #5
    //   1389: goto -> 1370
    //   1392: new gnu/kawa/functions/LispRepositionFormat
    //   1395: dup
    //   1396: aload #19
    //   1398: iload #6
    //   1400: invokestatic getParam : (Ljava/util/Vector;I)I
    //   1403: iload #16
    //   1405: iload #15
    //   1407: invokespecial <init> : (IZZ)V
    //   1410: astore #17
    //   1412: goto -> 987
    //   1415: iload #16
    //   1417: ifeq -> 1483
    //   1420: iload #15
    //   1422: ifeq -> 1476
    //   1425: bipush #85
    //   1427: istore #4
    //   1429: new gnu/text/CaseConvertFormat
    //   1432: dup
    //   1433: aconst_null
    //   1434: iload #4
    //   1436: invokespecial <init> : (Ljava/text/Format;C)V
    //   1439: astore #17
    //   1441: aload #19
    //   1443: iload #6
    //   1445: invokevirtual setSize : (I)V
    //   1448: aload #19
    //   1450: aload #17
    //   1452: invokevirtual push : (Ljava/lang/Object;)Ljava/lang/Object;
    //   1455: pop
    //   1456: aload #19
    //   1458: iload_2
    //   1459: invokestatic make : (I)Lgnu/math/IntNum;
    //   1462: invokevirtual push : (Ljava/lang/Object;)Ljava/lang/Object;
    //   1465: pop
    //   1466: iload #6
    //   1468: istore_2
    //   1469: iload #7
    //   1471: istore #5
    //   1473: goto -> 46
    //   1476: bipush #67
    //   1478: istore #4
    //   1480: goto -> 1429
    //   1483: iload #15
    //   1485: ifeq -> 1495
    //   1488: bipush #84
    //   1490: istore #4
    //   1492: goto -> 1429
    //   1495: bipush #76
    //   1497: istore #4
    //   1499: goto -> 1429
    //   1502: iload_2
    //   1503: iflt -> 1518
    //   1506: aload #19
    //   1508: iload_2
    //   1509: invokevirtual elementAt : (I)Ljava/lang/Object;
    //   1512: instanceof gnu/text/CaseConvertFormat
    //   1515: ifne -> 1530
    //   1518: new java/text/ParseException
    //   1521: dup
    //   1522: ldc 'saw ~) without matching ~('
    //   1524: iload #7
    //   1526: invokespecial <init> : (Ljava/lang/String;I)V
    //   1529: athrow
    //   1530: aload #19
    //   1532: iload_2
    //   1533: invokevirtual elementAt : (I)Ljava/lang/Object;
    //   1536: checkcast gnu/text/CaseConvertFormat
    //   1539: aload #19
    //   1541: iload_2
    //   1542: iconst_2
    //   1543: iadd
    //   1544: iload #6
    //   1546: invokestatic popFormats : (Ljava/util/Vector;II)Ljava/text/Format;
    //   1549: invokevirtual setBaseFormat : (Ljava/text/Format;)V
    //   1552: aload #19
    //   1554: invokevirtual pop : ()Ljava/lang/Object;
    //   1557: checkcast gnu/math/IntNum
    //   1560: invokevirtual intValue : ()I
    //   1563: istore_2
    //   1564: iload #7
    //   1566: istore #5
    //   1568: goto -> 46
    //   1571: new gnu/kawa/functions/LispIterationFormat
    //   1574: dup
    //   1575: invokespecial <init> : ()V
    //   1578: astore #17
    //   1580: aload #17
    //   1582: iload #15
    //   1584: putfield seenAt : Z
    //   1587: aload #17
    //   1589: iconst_1
    //   1590: putfield maxIterations : I
    //   1593: aload #17
    //   1595: iconst_1
    //   1596: putfield atLeastOnce : Z
    //   1599: goto -> 987
    //   1602: new gnu/kawa/functions/LispIterationFormat
    //   1605: dup
    //   1606: invokespecial <init> : ()V
    //   1609: astore #17
    //   1611: aload #17
    //   1613: iload #15
    //   1615: putfield seenAt : Z
    //   1618: aload #17
    //   1620: iload #16
    //   1622: putfield seenColon : Z
    //   1625: aload #17
    //   1627: aload #19
    //   1629: iload #6
    //   1631: invokestatic getParam : (Ljava/util/Vector;I)I
    //   1634: putfield maxIterations : I
    //   1637: aload #19
    //   1639: iload #6
    //   1641: invokevirtual setSize : (I)V
    //   1644: aload #19
    //   1646: aload #17
    //   1648: invokevirtual push : (Ljava/lang/Object;)Ljava/lang/Object;
    //   1651: pop
    //   1652: aload #19
    //   1654: iload_2
    //   1655: invokestatic make : (I)Lgnu/math/IntNum;
    //   1658: invokevirtual push : (Ljava/lang/Object;)Ljava/lang/Object;
    //   1661: pop
    //   1662: iload #6
    //   1664: istore_2
    //   1665: iload #7
    //   1667: istore #5
    //   1669: goto -> 46
    //   1672: iload_2
    //   1673: iflt -> 1688
    //   1676: aload #19
    //   1678: iload_2
    //   1679: invokevirtual elementAt : (I)Ljava/lang/Object;
    //   1682: instanceof gnu/kawa/functions/LispIterationFormat
    //   1685: ifne -> 1700
    //   1688: new java/text/ParseException
    //   1691: dup
    //   1692: ldc 'saw ~} without matching ~{'
    //   1694: iload #7
    //   1696: invokespecial <init> : (Ljava/lang/String;I)V
    //   1699: athrow
    //   1700: aload #19
    //   1702: iload_2
    //   1703: invokevirtual elementAt : (I)Ljava/lang/Object;
    //   1706: checkcast gnu/kawa/functions/LispIterationFormat
    //   1709: astore #17
    //   1711: aload #17
    //   1713: iload #16
    //   1715: putfield atLeastOnce : Z
    //   1718: iload #6
    //   1720: iload_2
    //   1721: iconst_2
    //   1722: iadd
    //   1723: if_icmple -> 1741
    //   1726: aload #17
    //   1728: aload #19
    //   1730: iload_2
    //   1731: iconst_2
    //   1732: iadd
    //   1733: iload #6
    //   1735: invokestatic popFormats : (Ljava/util/Vector;II)Ljava/text/Format;
    //   1738: putfield body : Ljava/text/Format;
    //   1741: aload #19
    //   1743: invokevirtual pop : ()Ljava/lang/Object;
    //   1746: checkcast gnu/math/IntNum
    //   1749: invokevirtual intValue : ()I
    //   1752: istore_2
    //   1753: iload #7
    //   1755: istore #5
    //   1757: goto -> 46
    //   1760: new gnu/kawa/functions/LispPrettyFormat
    //   1763: dup
    //   1764: invokespecial <init> : ()V
    //   1767: astore #17
    //   1769: aload #17
    //   1771: iload #15
    //   1773: putfield seenAt : Z
    //   1776: iload #16
    //   1778: ifeq -> 1843
    //   1781: aload #17
    //   1783: ldc '('
    //   1785: putfield prefix : Ljava/lang/String;
    //   1788: aload #17
    //   1790: ldc_w ')'
    //   1793: putfield suffix : Ljava/lang/String;
    //   1796: aload #19
    //   1798: iload #6
    //   1800: invokevirtual setSize : (I)V
    //   1803: aload #19
    //   1805: aload #17
    //   1807: invokevirtual push : (Ljava/lang/Object;)Ljava/lang/Object;
    //   1810: pop
    //   1811: aload #19
    //   1813: iload_2
    //   1814: invokestatic make : (I)Lgnu/math/IntNum;
    //   1817: invokevirtual push : (Ljava/lang/Object;)Ljava/lang/Object;
    //   1820: pop
    //   1821: aload #19
    //   1823: iload_3
    //   1824: invokestatic make : (I)Lgnu/math/IntNum;
    //   1827: invokevirtual push : (Ljava/lang/Object;)Ljava/lang/Object;
    //   1830: pop
    //   1831: iload #6
    //   1833: istore_2
    //   1834: iconst_0
    //   1835: istore_3
    //   1836: iload #7
    //   1838: istore #5
    //   1840: goto -> 46
    //   1843: aload #17
    //   1845: ldc_w ''
    //   1848: putfield prefix : Ljava/lang/String;
    //   1851: aload #17
    //   1853: ldc_w ''
    //   1856: putfield suffix : Ljava/lang/String;
    //   1859: goto -> 1796
    //   1862: iload_2
    //   1863: iflt -> 1878
    //   1866: aload #19
    //   1868: iload_2
    //   1869: invokevirtual elementAt : (I)Ljava/lang/Object;
    //   1872: instanceof gnu/kawa/functions/LispPrettyFormat
    //   1875: ifne -> 1891
    //   1878: new java/text/ParseException
    //   1881: dup
    //   1882: ldc_w 'saw ~> without matching ~<'
    //   1885: iload #7
    //   1887: invokespecial <init> : (Ljava/lang/String;I)V
    //   1890: athrow
    //   1891: aload #19
    //   1893: aload #19
    //   1895: iload_2
    //   1896: iconst_3
    //   1897: iadd
    //   1898: iload_3
    //   1899: iadd
    //   1900: iload #6
    //   1902: invokestatic popFormats : (Ljava/util/Vector;II)Ljava/text/Format;
    //   1905: invokevirtual push : (Ljava/lang/Object;)Ljava/lang/Object;
    //   1908: pop
    //   1909: aload #19
    //   1911: iload_2
    //   1912: invokevirtual elementAt : (I)Ljava/lang/Object;
    //   1915: checkcast gnu/kawa/functions/LispPrettyFormat
    //   1918: astore #17
    //   1920: aload #17
    //   1922: aload #19
    //   1924: iload_2
    //   1925: iconst_3
    //   1926: iadd
    //   1927: aload #19
    //   1929: invokevirtual size : ()I
    //   1932: invokestatic getFormats : (Ljava/util/Vector;II)[Ljava/text/Format;
    //   1935: putfield segments : [Ljava/text/Format;
    //   1938: aload #19
    //   1940: iload_2
    //   1941: iconst_3
    //   1942: iadd
    //   1943: invokevirtual setSize : (I)V
    //   1946: aload #19
    //   1948: invokevirtual pop : ()Ljava/lang/Object;
    //   1951: checkcast gnu/math/IntNum
    //   1954: invokevirtual intValue : ()I
    //   1957: pop
    //   1958: aload #19
    //   1960: invokevirtual pop : ()Ljava/lang/Object;
    //   1963: checkcast gnu/math/IntNum
    //   1966: invokevirtual intValue : ()I
    //   1969: istore #6
    //   1971: iload #16
    //   1973: ifeq -> 2147
    //   1976: aload #17
    //   1978: getfield segments : [Ljava/text/Format;
    //   1981: arraylength
    //   1982: istore #8
    //   1984: iload #8
    //   1986: iconst_3
    //   1987: if_icmple -> 2003
    //   1990: new java/text/ParseException
    //   1993: dup
    //   1994: ldc_w 'too many segments in Logical Block format'
    //   1997: iload #7
    //   1999: invokespecial <init> : (Ljava/lang/String;I)V
    //   2002: athrow
    //   2003: iload #8
    //   2005: iconst_2
    //   2006: if_icmplt -> 2104
    //   2009: aload #17
    //   2011: getfield segments : [Ljava/text/Format;
    //   2014: iconst_0
    //   2015: aaload
    //   2016: instanceof gnu/text/LiteralFormat
    //   2019: ifne -> 2035
    //   2022: new java/text/ParseException
    //   2025: dup
    //   2026: ldc_w 'prefix segment is not literal'
    //   2029: iload #7
    //   2031: invokespecial <init> : (Ljava/lang/String;I)V
    //   2034: athrow
    //   2035: aload #17
    //   2037: aload #17
    //   2039: getfield segments : [Ljava/text/Format;
    //   2042: iconst_0
    //   2043: aaload
    //   2044: checkcast gnu/text/LiteralFormat
    //   2047: invokevirtual content : ()Ljava/lang/String;
    //   2050: putfield prefix : Ljava/lang/String;
    //   2053: aload #17
    //   2055: aload #17
    //   2057: getfield segments : [Ljava/text/Format;
    //   2060: iconst_1
    //   2061: aaload
    //   2062: putfield body : Ljava/text/Format;
    //   2065: iload #7
    //   2067: istore #5
    //   2069: iload #6
    //   2071: istore_2
    //   2072: iload #8
    //   2074: iconst_3
    //   2075: if_icmplt -> 46
    //   2078: aload #17
    //   2080: getfield segments : [Ljava/text/Format;
    //   2083: iconst_2
    //   2084: aaload
    //   2085: instanceof gnu/text/LiteralFormat
    //   2088: ifne -> 2119
    //   2091: new java/text/ParseException
    //   2094: dup
    //   2095: ldc_w 'suffix segment is not literal'
    //   2098: iload #7
    //   2100: invokespecial <init> : (Ljava/lang/String;I)V
    //   2103: athrow
    //   2104: aload #17
    //   2106: aload #17
    //   2108: getfield segments : [Ljava/text/Format;
    //   2111: iconst_0
    //   2112: aaload
    //   2113: putfield body : Ljava/text/Format;
    //   2116: goto -> 2065
    //   2119: aload #17
    //   2121: aload #17
    //   2123: getfield segments : [Ljava/text/Format;
    //   2126: iconst_2
    //   2127: aaload
    //   2128: checkcast gnu/text/LiteralFormat
    //   2131: invokevirtual content : ()Ljava/lang/String;
    //   2134: putfield suffix : Ljava/lang/String;
    //   2137: iload #7
    //   2139: istore #5
    //   2141: iload #6
    //   2143: istore_2
    //   2144: goto -> 46
    //   2147: new java/text/ParseException
    //   2150: dup
    //   2151: ldc_w 'not implemented: justfication i.e. ~<...~>'
    //   2154: iload #7
    //   2156: invokespecial <init> : (Ljava/lang/String;I)V
    //   2159: athrow
    //   2160: new gnu/kawa/functions/LispChoiceFormat
    //   2163: dup
    //   2164: invokespecial <init> : ()V
    //   2167: astore #17
    //   2169: aload #17
    //   2171: aload #19
    //   2173: iload #6
    //   2175: invokestatic getParam : (Ljava/util/Vector;I)I
    //   2178: putfield param : I
    //   2181: aload #17
    //   2183: getfield param : I
    //   2186: ldc_w -1073741824
    //   2189: if_icmpne -> 2199
    //   2192: aload #17
    //   2194: ldc -1610612736
    //   2196: putfield param : I
    //   2199: iload #16
    //   2201: ifeq -> 2210
    //   2204: aload #17
    //   2206: iconst_1
    //   2207: putfield testBoolean : Z
    //   2210: iload #15
    //   2212: ifeq -> 2221
    //   2215: aload #17
    //   2217: iconst_1
    //   2218: putfield skipIfFalse : Z
    //   2221: aload #19
    //   2223: iload #6
    //   2225: invokevirtual setSize : (I)V
    //   2228: aload #19
    //   2230: aload #17
    //   2232: invokevirtual push : (Ljava/lang/Object;)Ljava/lang/Object;
    //   2235: pop
    //   2236: aload #19
    //   2238: iload_2
    //   2239: invokestatic make : (I)Lgnu/math/IntNum;
    //   2242: invokevirtual push : (Ljava/lang/Object;)Ljava/lang/Object;
    //   2245: pop
    //   2246: aload #19
    //   2248: iload_3
    //   2249: invokestatic make : (I)Lgnu/math/IntNum;
    //   2252: invokevirtual push : (Ljava/lang/Object;)Ljava/lang/Object;
    //   2255: pop
    //   2256: iload #6
    //   2258: istore_2
    //   2259: iconst_0
    //   2260: istore_3
    //   2261: iload #7
    //   2263: istore #5
    //   2265: goto -> 46
    //   2268: iload_2
    //   2269: iflt -> 2398
    //   2272: aload #19
    //   2274: iload_2
    //   2275: invokevirtual elementAt : (I)Ljava/lang/Object;
    //   2278: instanceof gnu/kawa/functions/LispChoiceFormat
    //   2281: ifeq -> 2335
    //   2284: aload #19
    //   2286: iload_2
    //   2287: invokevirtual elementAt : (I)Ljava/lang/Object;
    //   2290: checkcast gnu/kawa/functions/LispChoiceFormat
    //   2293: astore #17
    //   2295: iload #16
    //   2297: ifeq -> 2306
    //   2300: aload #17
    //   2302: iconst_1
    //   2303: putfield lastIsDefault : Z
    //   2306: aload #19
    //   2308: aload #19
    //   2310: iload_2
    //   2311: iconst_3
    //   2312: iadd
    //   2313: iload_3
    //   2314: iadd
    //   2315: iload #6
    //   2317: invokestatic popFormats : (Ljava/util/Vector;II)Ljava/text/Format;
    //   2320: invokevirtual push : (Ljava/lang/Object;)Ljava/lang/Object;
    //   2323: pop
    //   2324: iload_3
    //   2325: iconst_1
    //   2326: iadd
    //   2327: istore_3
    //   2328: iload #7
    //   2330: istore #5
    //   2332: goto -> 46
    //   2335: aload #19
    //   2337: iload_2
    //   2338: invokevirtual elementAt : (I)Ljava/lang/Object;
    //   2341: instanceof gnu/kawa/functions/LispPrettyFormat
    //   2344: ifeq -> 2398
    //   2347: aload #19
    //   2349: iload_2
    //   2350: invokevirtual elementAt : (I)Ljava/lang/Object;
    //   2353: checkcast gnu/kawa/functions/LispPrettyFormat
    //   2356: astore #17
    //   2358: iload #15
    //   2360: ifeq -> 2369
    //   2363: aload #17
    //   2365: iconst_1
    //   2366: putfield perLine : Z
    //   2369: aload #19
    //   2371: aload #19
    //   2373: iload_2
    //   2374: iconst_3
    //   2375: iadd
    //   2376: iload_3
    //   2377: iadd
    //   2378: iload #6
    //   2380: invokestatic popFormats : (Ljava/util/Vector;II)Ljava/text/Format;
    //   2383: invokevirtual push : (Ljava/lang/Object;)Ljava/lang/Object;
    //   2386: pop
    //   2387: iload_3
    //   2388: iconst_1
    //   2389: iadd
    //   2390: istore_3
    //   2391: iload #7
    //   2393: istore #5
    //   2395: goto -> 46
    //   2398: new java/text/ParseException
    //   2401: dup
    //   2402: ldc_w 'saw ~; without matching ~[ or ~<'
    //   2405: iload #7
    //   2407: invokespecial <init> : (Ljava/lang/String;I)V
    //   2410: athrow
    //   2411: iload_2
    //   2412: iflt -> 2427
    //   2415: aload #19
    //   2417: iload_2
    //   2418: invokevirtual elementAt : (I)Ljava/lang/Object;
    //   2421: instanceof gnu/kawa/functions/LispChoiceFormat
    //   2424: ifne -> 2440
    //   2427: new java/text/ParseException
    //   2430: dup
    //   2431: ldc_w 'saw ~] without matching ~['
    //   2434: iload #7
    //   2436: invokespecial <init> : (Ljava/lang/String;I)V
    //   2439: athrow
    //   2440: aload #19
    //   2442: aload #19
    //   2444: iload_2
    //   2445: iconst_3
    //   2446: iadd
    //   2447: iload_3
    //   2448: iadd
    //   2449: iload #6
    //   2451: invokestatic popFormats : (Ljava/util/Vector;II)Ljava/text/Format;
    //   2454: invokevirtual push : (Ljava/lang/Object;)Ljava/lang/Object;
    //   2457: pop
    //   2458: aload #19
    //   2460: iload_2
    //   2461: invokevirtual elementAt : (I)Ljava/lang/Object;
    //   2464: checkcast gnu/kawa/functions/LispChoiceFormat
    //   2467: aload #19
    //   2469: iload_2
    //   2470: iconst_3
    //   2471: iadd
    //   2472: aload #19
    //   2474: invokevirtual size : ()I
    //   2477: invokestatic getFormats : (Ljava/util/Vector;II)[Ljava/text/Format;
    //   2480: putfield choices : [Ljava/text/Format;
    //   2483: aload #19
    //   2485: iload_2
    //   2486: iconst_3
    //   2487: iadd
    //   2488: invokevirtual setSize : (I)V
    //   2491: aload #19
    //   2493: invokevirtual pop : ()Ljava/lang/Object;
    //   2496: checkcast gnu/math/IntNum
    //   2499: invokevirtual intValue : ()I
    //   2502: istore_3
    //   2503: aload #19
    //   2505: invokevirtual pop : ()Ljava/lang/Object;
    //   2508: checkcast gnu/math/IntNum
    //   2511: invokevirtual intValue : ()I
    //   2514: istore_2
    //   2515: iload #7
    //   2517: istore #5
    //   2519: goto -> 46
    //   2522: new gnu/kawa/functions/LispEscapeFormat
    //   2525: dup
    //   2526: aload #19
    //   2528: iload #6
    //   2530: invokestatic getParam : (Ljava/util/Vector;I)I
    //   2533: aload #19
    //   2535: iload #6
    //   2537: iconst_1
    //   2538: iadd
    //   2539: invokestatic getParam : (Ljava/util/Vector;I)I
    //   2542: aload #19
    //   2544: iload #6
    //   2546: iconst_2
    //   2547: iadd
    //   2548: invokestatic getParam : (Ljava/util/Vector;I)I
    //   2551: invokespecial <init> : (III)V
    //   2554: astore #17
    //   2556: goto -> 987
    //   2559: iload #15
    //   2561: ifeq -> 2572
    //   2564: aload #18
    //   2566: iload #4
    //   2568: invokevirtual append : (C)Ljava/lang/StringBuffer;
    //   2571: pop
    //   2572: iload #7
    //   2574: istore #5
    //   2576: iload #16
    //   2578: ifne -> 46
    //   2581: iload #7
    //   2583: istore #5
    //   2585: iload #7
    //   2587: iload #10
    //   2589: if_icmpge -> 46
    //   2592: iload #7
    //   2594: iconst_1
    //   2595: iadd
    //   2596: istore #5
    //   2598: aload_1
    //   2599: iload #7
    //   2601: caload
    //   2602: invokestatic isWhitespace : (C)Z
    //   2605: ifne -> 2995
    //   2608: iload #5
    //   2610: iconst_1
    //   2611: isub
    //   2612: istore #5
    //   2614: goto -> 46
    //   2617: invokestatic getInstance : ()Lgnu/text/FlushFormat;
    //   2620: astore #17
    //   2622: goto -> 987
    //   2625: new gnu/kawa/functions/LispTabulateFormat
    //   2628: dup
    //   2629: aload #19
    //   2631: iload #6
    //   2633: invokestatic getParam : (Ljava/util/Vector;I)I
    //   2636: aload #19
    //   2638: iload #6
    //   2640: iconst_1
    //   2641: iadd
    //   2642: invokestatic getParam : (Ljava/util/Vector;I)I
    //   2645: aload #19
    //   2647: iload #6
    //   2649: iconst_2
    //   2650: iadd
    //   2651: invokestatic getParam : (Ljava/util/Vector;I)I
    //   2654: iload #15
    //   2656: invokespecial <init> : (IIIZ)V
    //   2659: astore #17
    //   2661: goto -> 987
    //   2664: new gnu/kawa/functions/LispFreshlineFormat
    //   2667: dup
    //   2668: aload #19
    //   2670: iload #6
    //   2672: invokestatic getParam : (Ljava/util/Vector;I)I
    //   2675: invokespecial <init> : (I)V
    //   2678: astore #17
    //   2680: goto -> 987
    //   2683: aload #19
    //   2685: iload #6
    //   2687: invokestatic getParam : (Ljava/util/Vector;I)I
    //   2690: istore #8
    //   2692: iload #8
    //   2694: istore #5
    //   2696: iload #8
    //   2698: ldc_w -1073741824
    //   2701: if_icmpne -> 2707
    //   2704: iconst_0
    //   2705: istore #5
    //   2707: iload #5
    //   2709: iload #16
    //   2711: invokestatic getInstance : (IZ)Lgnu/kawa/functions/LispIndentFormat;
    //   2714: astore #17
    //   2716: goto -> 987
    //   2719: aload #19
    //   2721: iload #6
    //   2723: invokestatic getParam : (Ljava/util/Vector;I)I
    //   2726: istore #5
    //   2728: iload #5
    //   2730: istore #8
    //   2732: iload #5
    //   2734: ldc_w -1073741824
    //   2737: if_icmpne -> 2743
    //   2740: iconst_1
    //   2741: istore #8
    //   2743: iload #16
    //   2745: ifeq -> 2779
    //   2748: iload #15
    //   2750: ifeq -> 2779
    //   2753: iload #15
    //   2755: ifeq -> 2782
    //   2758: iload #16
    //   2760: ifeq -> 2782
    //   2763: bipush #82
    //   2765: istore #5
    //   2767: iload #8
    //   2769: iload #5
    //   2771: invokestatic getInstance : (II)Lgnu/kawa/functions/LispNewlineFormat;
    //   2774: astore #17
    //   2776: goto -> 987
    //   2779: goto -> 2753
    //   2782: iload #15
    //   2784: ifeq -> 2794
    //   2787: bipush #77
    //   2789: istore #5
    //   2791: goto -> 2767
    //   2794: iload #16
    //   2796: ifeq -> 2806
    //   2799: bipush #70
    //   2801: istore #5
    //   2803: goto -> 2767
    //   2806: bipush #78
    //   2808: istore #5
    //   2810: goto -> 2767
    //   2813: iload #5
    //   2815: ifne -> 2833
    //   2818: aload #18
    //   2820: iload #4
    //   2822: invokevirtual append : (C)Ljava/lang/StringBuffer;
    //   2825: pop
    //   2826: iload #7
    //   2828: istore #5
    //   2830: goto -> 46
    //   2833: aload #19
    //   2835: iload #6
    //   2837: invokestatic getParam : (Ljava/util/Vector;I)I
    //   2840: istore #5
    //   2842: iload #5
    //   2844: istore #8
    //   2846: iload #5
    //   2848: ldc_w -1073741824
    //   2851: if_icmpne -> 2857
    //   2854: iconst_1
    //   2855: istore #8
    //   2857: aload #19
    //   2859: iload #6
    //   2861: iconst_1
    //   2862: iadd
    //   2863: invokestatic getParam : (Ljava/util/Vector;I)I
    //   2866: istore #9
    //   2868: iload #9
    //   2870: istore #5
    //   2872: iload #9
    //   2874: ldc_w -1073741824
    //   2877: if_icmpne -> 2891
    //   2880: iload #4
    //   2882: bipush #124
    //   2884: if_icmpne -> 2905
    //   2887: bipush #12
    //   2889: istore #5
    //   2891: iload #5
    //   2893: iload #8
    //   2895: iconst_0
    //   2896: iconst_0
    //   2897: invokestatic getInstance : (IIZZ)Lgnu/kawa/functions/LispCharacterFormat;
    //   2900: astore #17
    //   2902: goto -> 987
    //   2905: bipush #126
    //   2907: istore #5
    //   2909: goto -> 2891
    //   2912: aload #19
    //   2914: iload #6
    //   2916: invokestatic getParam : (Ljava/util/Vector;I)I
    //   2919: istore #8
    //   2921: iload #8
    //   2923: istore #5
    //   2925: iload #8
    //   2927: ldc_w -1073741824
    //   2930: if_icmpne -> 2936
    //   2933: iconst_1
    //   2934: istore #5
    //   2936: iload #5
    //   2938: bipush #76
    //   2940: invokestatic getInstance : (II)Lgnu/kawa/functions/LispNewlineFormat;
    //   2943: astore #17
    //   2945: goto -> 987
    //   2948: iload_2
    //   2949: iflt -> 2965
    //   2952: new java/text/ParseException
    //   2955: dup
    //   2956: ldc_w 'missing ~] or ~}'
    //   2959: iload #5
    //   2961: invokespecial <init> : (Ljava/lang/String;I)V
    //   2964: athrow
    //   2965: aload_0
    //   2966: aload #19
    //   2968: invokevirtual size : ()I
    //   2971: putfield length : I
    //   2974: aload_0
    //   2975: aload_0
    //   2976: getfield length : I
    //   2979: anewarray java/text/Format
    //   2982: putfield formats : [Ljava/text/Format;
    //   2985: aload #19
    //   2987: aload_0
    //   2988: getfield formats : [Ljava/text/Format;
    //   2991: invokevirtual copyInto : ([Ljava/lang/Object;)V
    //   2994: return
    //   2995: iload #5
    //   2997: istore #7
    //   2999: goto -> 2581
    //   3002: goto -> 987
    //   3005: goto -> 200
    //   3008: goto -> 320
  }
  
  public static Object[] asArray(Object paramObject) {
    if (paramObject instanceof Object[])
      return (Object[])paramObject; 
    if (!(paramObject instanceof Sequence))
      return null; 
    int j = ((Sequence)paramObject).size();
    Object[] arrayOfObject = new Object[j];
    int i;
    for (i = 0; paramObject instanceof gnu.lists.Pair; i++) {
      paramObject = paramObject;
      arrayOfObject[i] = paramObject.getCar();
      paramObject = paramObject.getCdr();
    } 
    if (i < j) {
      if (!(paramObject instanceof Sequence))
        return null; 
      paramObject = paramObject;
      for (int k = i; k < j; k++)
        arrayOfObject[k] = paramObject.get(i + k); 
    } 
    return arrayOfObject;
  }
  
  static Format[] getFormats(Vector<Format> paramVector, int paramInt1, int paramInt2) {
    Format[] arrayOfFormat = new Format[paramInt2 - paramInt1];
    for (int i = paramInt1; i < paramInt2; i++)
      arrayOfFormat[i - paramInt1] = paramVector.elementAt(i); 
    return arrayOfFormat;
  }
  
  public static int getParam(Vector<Vector> paramVector, int paramInt) {
    if (paramInt < paramVector.size()) {
      paramVector = paramVector.elementAt(paramInt);
      if (paramVector == "<from list>")
        return -1610612736; 
      if (paramVector == "<from count>")
        return -1342177280; 
      if (paramVector != "<unspecified>")
        return getParam(paramVector, -1073741824); 
    } 
    return -1073741824;
  }
  
  static Format popFormats(Vector<Format> paramVector, int paramInt1, int paramInt2) {
    if (paramInt2 == paramInt1 + 1) {
      Format format = paramVector.elementAt(paramInt1);
      paramVector.setSize(paramInt1);
      return format;
    } 
    CompoundFormat compoundFormat = new CompoundFormat(getFormats(paramVector, paramInt1, paramInt2));
    paramVector.setSize(paramInt1);
    return (Format)compoundFormat;
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/gnu/kawa/functions/LispFormat.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */