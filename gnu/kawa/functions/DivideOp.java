package gnu.kawa.functions;

import gnu.mapping.Procedure;
import gnu.mapping.PropertySet;

public class DivideOp extends ArithOp {
  public static final DivideOp $Sl = new DivideOp("/", 4);
  
  public static final DivideOp div;
  
  public static final DivideOp div0;
  
  public static final DivideOp idiv = new DivideOp("idiv", 7);
  
  public static final DivideOp mod;
  
  public static final DivideOp mod0;
  
  public static final DivideOp modulo;
  
  public static final DivideOp quotient = new DivideOp("quotient", 6);
  
  public static final DivideOp remainder = new DivideOp("remainder", 8);
  
  int rounding_mode;
  
  static {
    modulo = new DivideOp("modulo", 8);
    div = new DivideOp("div", 6);
    mod = new DivideOp("mod", 8);
    div0 = new DivideOp("div0", 6);
    mod0 = new DivideOp("mod0", 8);
    idiv.rounding_mode = 3;
    quotient.rounding_mode = 3;
    remainder.rounding_mode = 3;
    modulo.rounding_mode = 1;
    div.rounding_mode = 5;
    mod.rounding_mode = 5;
    div0.rounding_mode = 4;
    mod0.rounding_mode = 4;
  }
  
  public DivideOp(String paramString, int paramInt) {
    super(paramString, paramInt);
    setProperty(Procedure.validateApplyKey, "gnu.kawa.functions.CompileArith:validateApplyArithOp");
    Procedure.compilerKey.set((PropertySet)this, "*gnu.kawa.functions.CompileArith:forDiv");
  }
  
  public Object applyN(Object[] paramArrayOfObject) throws Throwable {
    // Byte code:
    //   0: aload_1
    //   1: arraylength
    //   2: istore #13
    //   4: iload #13
    //   6: ifne -> 15
    //   9: invokestatic one : ()Lgnu/math/IntNum;
    //   12: astore_1
    //   13: aload_1
    //   14: areturn
    //   15: aload_1
    //   16: iconst_0
    //   17: aaload
    //   18: checkcast java/lang/Number
    //   21: astore #19
    //   23: iload #13
    //   25: iconst_1
    //   26: if_icmpne -> 39
    //   29: aload_0
    //   30: invokestatic one : ()Lgnu/math/IntNum;
    //   33: aload #19
    //   35: invokevirtual apply2 : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   38: areturn
    //   39: aload #19
    //   41: invokestatic classifyValue : (Ljava/lang/Object;)I
    //   44: istore #9
    //   46: iconst_1
    //   47: istore #12
    //   49: iload #12
    //   51: iload #13
    //   53: if_icmpge -> 1295
    //   56: aload_1
    //   57: iload #12
    //   59: aaload
    //   60: astore #18
    //   62: aload #18
    //   64: invokestatic classifyValue : (Ljava/lang/Object;)I
    //   67: istore #10
    //   69: iload #9
    //   71: istore #8
    //   73: iload #9
    //   75: iload #10
    //   77: if_icmpge -> 84
    //   80: iload #10
    //   82: istore #8
    //   84: iload #8
    //   86: istore #9
    //   88: iload #8
    //   90: istore #11
    //   92: iload #9
    //   94: istore #10
    //   96: iload #8
    //   98: iconst_4
    //   99: if_icmpge -> 164
    //   102: aload_0
    //   103: getfield op : I
    //   106: tableswitch default -> 128, 4 -> 320, 5 -> 320
    //   128: aload_0
    //   129: getfield rounding_mode : I
    //   132: iconst_3
    //   133: if_icmpne -> 329
    //   136: iload #8
    //   138: istore #11
    //   140: iload #9
    //   142: istore #10
    //   144: iload #8
    //   146: iconst_1
    //   147: if_icmpeq -> 164
    //   150: iload #8
    //   152: iconst_2
    //   153: if_icmpne -> 329
    //   156: iload #9
    //   158: istore #10
    //   160: iload #8
    //   162: istore #11
    //   164: aload_0
    //   165: getfield op : I
    //   168: iconst_5
    //   169: if_icmpne -> 339
    //   172: iload #11
    //   174: bipush #10
    //   176: if_icmpgt -> 339
    //   179: bipush #10
    //   181: istore #10
    //   183: iload #11
    //   185: istore #8
    //   187: iload #10
    //   189: istore #9
    //   191: iload #11
    //   193: bipush #8
    //   195: if_icmpeq -> 221
    //   198: iload #11
    //   200: istore #8
    //   202: iload #10
    //   204: istore #9
    //   206: iload #11
    //   208: bipush #7
    //   210: if_icmpeq -> 221
    //   213: bipush #9
    //   215: istore #8
    //   217: iload #10
    //   219: istore #9
    //   221: iload #9
    //   223: tableswitch default -> 272, 1 -> 393, 2 -> 531, 3 -> 272, 4 -> 595, 5 -> 723, 6 -> 272, 7 -> 272, 8 -> 272, 9 -> 950
    //   272: aload #19
    //   274: invokestatic asNumeric : (Ljava/lang/Object;)Lgnu/math/Numeric;
    //   277: astore #19
    //   279: aload #18
    //   281: invokestatic asNumeric : (Ljava/lang/Object;)Lgnu/math/Numeric;
    //   284: astore #21
    //   286: aload_0
    //   287: getfield op : I
    //   290: bipush #8
    //   292: if_icmpne -> 1105
    //   295: aload #21
    //   297: invokevirtual isZero : ()Z
    //   300: ifeq -> 1105
    //   303: aload #19
    //   305: astore_1
    //   306: aload #21
    //   308: invokevirtual isExact : ()Z
    //   311: ifne -> 13
    //   314: aload #19
    //   316: invokevirtual toInexact : ()Lgnu/math/Numeric;
    //   319: areturn
    //   320: iconst_4
    //   321: istore #11
    //   323: iconst_4
    //   324: istore #10
    //   326: goto -> 164
    //   329: iconst_4
    //   330: istore #10
    //   332: iload #8
    //   334: istore #11
    //   336: goto -> 164
    //   339: iload #10
    //   341: bipush #8
    //   343: if_icmpeq -> 361
    //   346: iload #11
    //   348: istore #8
    //   350: iload #10
    //   352: istore #9
    //   354: iload #10
    //   356: bipush #7
    //   358: if_icmpne -> 221
    //   361: bipush #9
    //   363: istore #10
    //   365: iload #11
    //   367: istore #8
    //   369: iload #10
    //   371: istore #9
    //   373: aload_0
    //   374: getfield op : I
    //   377: bipush #7
    //   379: if_icmpne -> 221
    //   382: bipush #9
    //   384: istore #8
    //   386: iload #10
    //   388: istore #9
    //   390: goto -> 221
    //   393: aload #19
    //   395: invokestatic asInt : (Ljava/lang/Object;)I
    //   398: istore #10
    //   400: aload #18
    //   402: invokestatic asInt : (Ljava/lang/Object;)I
    //   405: istore #11
    //   407: aload_0
    //   408: getfield op : I
    //   411: tableswitch default -> 428, 8 -> 521
    //   428: iload #10
    //   430: iload #11
    //   432: idiv
    //   433: istore #10
    //   435: iload #10
    //   437: invokestatic valueOf : (I)Ljava/lang/Integer;
    //   440: astore #18
    //   442: aload #18
    //   444: astore #19
    //   446: iload #8
    //   448: iload #9
    //   450: if_icmpeq -> 508
    //   453: aload #18
    //   455: astore #19
    //   457: iload #8
    //   459: tableswitch default -> 504, 1 -> 1233, 2 -> 1246, 3 -> 1285, 4 -> 508, 5 -> 508, 6 -> 508, 7 -> 1259, 8 -> 1272
    //   504: aload #18
    //   506: astore #19
    //   508: iload #12
    //   510: iconst_1
    //   511: iadd
    //   512: istore #12
    //   514: iload #8
    //   516: istore #9
    //   518: goto -> 49
    //   521: iload #10
    //   523: iload #11
    //   525: irem
    //   526: istore #10
    //   528: goto -> 435
    //   531: aload #19
    //   533: invokestatic asLong : (Ljava/lang/Object;)J
    //   536: lstore #14
    //   538: aload #18
    //   540: invokestatic asLong : (Ljava/lang/Object;)J
    //   543: lstore #16
    //   545: aload_0
    //   546: getfield op : I
    //   549: tableswitch default -> 568, 8 -> 585
    //   568: lload #14
    //   570: lload #16
    //   572: ldiv
    //   573: lstore #14
    //   575: lload #14
    //   577: invokestatic valueOf : (J)Ljava/lang/Long;
    //   580: astore #18
    //   582: goto -> 442
    //   585: lload #14
    //   587: lload #16
    //   589: lrem
    //   590: lstore #14
    //   592: goto -> 575
    //   595: aload_0
    //   596: getfield op : I
    //   599: tableswitch default -> 632, 4 -> 639, 5 -> 632, 6 -> 672, 7 -> 672, 8 -> 694
    //   632: aload #19
    //   634: astore #18
    //   636: goto -> 442
    //   639: aload #19
    //   641: invokestatic asIntNum : (Ljava/lang/Object;)Lgnu/math/IntNum;
    //   644: aload #18
    //   646: invokestatic asIntNum : (Ljava/lang/Object;)Lgnu/math/IntNum;
    //   649: invokestatic make : (Lgnu/math/IntNum;Lgnu/math/IntNum;)Lgnu/math/RatNum;
    //   652: astore #18
    //   654: aload #18
    //   656: instanceof gnu/math/IntNum
    //   659: ifeq -> 716
    //   662: iconst_4
    //   663: istore #8
    //   665: iload #8
    //   667: istore #9
    //   669: goto -> 442
    //   672: aload #19
    //   674: invokestatic asIntNum : (Ljava/lang/Object;)Lgnu/math/IntNum;
    //   677: aload #18
    //   679: invokestatic asIntNum : (Ljava/lang/Object;)Lgnu/math/IntNum;
    //   682: aload_0
    //   683: invokevirtual getRoundingMode : ()I
    //   686: invokestatic quotient : (Lgnu/math/IntNum;Lgnu/math/IntNum;I)Lgnu/math/IntNum;
    //   689: astore #18
    //   691: goto -> 442
    //   694: aload #19
    //   696: invokestatic asIntNum : (Ljava/lang/Object;)Lgnu/math/IntNum;
    //   699: aload #18
    //   701: invokestatic asIntNum : (Ljava/lang/Object;)Lgnu/math/IntNum;
    //   704: aload_0
    //   705: invokevirtual getRoundingMode : ()I
    //   708: invokestatic remainder : (Lgnu/math/IntNum;Lgnu/math/IntNum;I)Lgnu/math/IntNum;
    //   711: astore #18
    //   713: goto -> 442
    //   716: bipush #6
    //   718: istore #8
    //   720: goto -> 665
    //   723: aload #19
    //   725: invokestatic asBigDecimal : (Ljava/lang/Object;)Ljava/math/BigDecimal;
    //   728: astore #20
    //   730: aload #18
    //   732: invokestatic asBigDecimal : (Ljava/lang/Object;)Ljava/math/BigDecimal;
    //   735: astore #21
    //   737: aload_0
    //   738: invokevirtual getRoundingMode : ()I
    //   741: tableswitch default -> 776, 1 -> 851, 2 -> 859, 3 -> 867, 4 -> 776, 5 -> 875
    //   776: getstatic java/math/RoundingMode.HALF_EVEN : Ljava/math/RoundingMode;
    //   779: astore #18
    //   781: new java/math/MathContext
    //   784: dup
    //   785: iconst_0
    //   786: aload #18
    //   788: invokespecial <init> : (ILjava/math/RoundingMode;)V
    //   791: astore #18
    //   793: aload_0
    //   794: getfield op : I
    //   797: tableswitch default -> 832, 4 -> 839, 5 -> 832, 6 -> 899, 7 -> 913, 8 -> 936
    //   832: aload #19
    //   834: astore #18
    //   836: goto -> 442
    //   839: aload #20
    //   841: aload #21
    //   843: invokevirtual divide : (Ljava/math/BigDecimal;)Ljava/math/BigDecimal;
    //   846: astore #18
    //   848: goto -> 442
    //   851: getstatic java/math/RoundingMode.FLOOR : Ljava/math/RoundingMode;
    //   854: astore #18
    //   856: goto -> 781
    //   859: getstatic java/math/RoundingMode.CEILING : Ljava/math/RoundingMode;
    //   862: astore #18
    //   864: goto -> 781
    //   867: getstatic java/math/RoundingMode.DOWN : Ljava/math/RoundingMode;
    //   870: astore #18
    //   872: goto -> 781
    //   875: aload #21
    //   877: invokevirtual signum : ()I
    //   880: ifge -> 891
    //   883: getstatic java/math/RoundingMode.CEILING : Ljava/math/RoundingMode;
    //   886: astore #18
    //   888: goto -> 776
    //   891: getstatic java/math/RoundingMode.FLOOR : Ljava/math/RoundingMode;
    //   894: astore #18
    //   896: goto -> 888
    //   899: aload #20
    //   901: aload #21
    //   903: aload #18
    //   905: invokevirtual divideToIntegralValue : (Ljava/math/BigDecimal;Ljava/math/MathContext;)Ljava/math/BigDecimal;
    //   908: astore #18
    //   910: goto -> 442
    //   913: aload #20
    //   915: aload #21
    //   917: aload #18
    //   919: invokevirtual divideToIntegralValue : (Ljava/math/BigDecimal;Ljava/math/MathContext;)Ljava/math/BigDecimal;
    //   922: invokevirtual toBigInteger : ()Ljava/math/BigInteger;
    //   925: astore #18
    //   927: iconst_3
    //   928: istore #9
    //   930: iconst_3
    //   931: istore #8
    //   933: goto -> 442
    //   936: aload #20
    //   938: aload #21
    //   940: aload #18
    //   942: invokevirtual remainder : (Ljava/math/BigDecimal;Ljava/math/MathContext;)Ljava/math/BigDecimal;
    //   945: astore #18
    //   947: goto -> 442
    //   950: aload #19
    //   952: invokestatic asDouble : (Ljava/lang/Object;)D
    //   955: dstore #4
    //   957: aload #18
    //   959: invokestatic asDouble : (Ljava/lang/Object;)D
    //   962: dstore #6
    //   964: aload_0
    //   965: getfield op : I
    //   968: tableswitch default -> 1004, 4 -> 1011, 5 -> 1011, 6 -> 1024, 7 -> 1044, 8 -> 1067
    //   1004: aload #19
    //   1006: astore #18
    //   1008: goto -> 442
    //   1011: dload #4
    //   1013: dload #6
    //   1015: ddiv
    //   1016: invokestatic make : (D)Lgnu/math/DFloNum;
    //   1019: astore #18
    //   1021: goto -> 442
    //   1024: dload #4
    //   1026: dload #6
    //   1028: ddiv
    //   1029: aload_0
    //   1030: invokevirtual getRoundingMode : ()I
    //   1033: invokestatic toInt : (DI)D
    //   1036: invokestatic valueOf : (D)Ljava/lang/Double;
    //   1039: astore #18
    //   1041: goto -> 442
    //   1044: dload #4
    //   1046: dload #6
    //   1048: ddiv
    //   1049: aload_0
    //   1050: invokevirtual getRoundingMode : ()I
    //   1053: invokestatic toExactInt : (DI)Lgnu/math/IntNum;
    //   1056: astore #18
    //   1058: iconst_4
    //   1059: istore #9
    //   1061: iconst_4
    //   1062: istore #8
    //   1064: goto -> 442
    //   1067: dload #4
    //   1069: dstore_2
    //   1070: dload #6
    //   1072: dconst_0
    //   1073: dcmpl
    //   1074: ifeq -> 1096
    //   1077: dload #4
    //   1079: dload #4
    //   1081: dload #6
    //   1083: ddiv
    //   1084: aload_0
    //   1085: invokevirtual getRoundingMode : ()I
    //   1088: invokestatic toInt : (DI)D
    //   1091: dload #6
    //   1093: dmul
    //   1094: dsub
    //   1095: dstore_2
    //   1096: dload_2
    //   1097: invokestatic make : (D)Lgnu/math/DFloNum;
    //   1100: astore #18
    //   1102: goto -> 442
    //   1105: aload #19
    //   1107: aload #21
    //   1109: invokevirtual div : (Ljava/lang/Object;)Lgnu/math/Numeric;
    //   1112: astore #20
    //   1114: aload #20
    //   1116: astore #18
    //   1118: aload_0
    //   1119: getfield op : I
    //   1122: bipush #8
    //   1124: if_icmpne -> 1151
    //   1127: aload #19
    //   1129: aload #20
    //   1131: checkcast gnu/math/RealNum
    //   1134: aload_0
    //   1135: invokevirtual getRoundingMode : ()I
    //   1138: invokevirtual toInt : (I)Lgnu/math/RealNum;
    //   1141: aload #21
    //   1143: invokevirtual mul : (Ljava/lang/Object;)Lgnu/math/Numeric;
    //   1146: invokevirtual sub : (Ljava/lang/Object;)Lgnu/math/Numeric;
    //   1149: astore #18
    //   1151: aload_0
    //   1152: getfield op : I
    //   1155: tableswitch default -> 1180, 5 -> 1223, 6 -> 1206, 7 -> 1183
    //   1180: goto -> 442
    //   1183: aload #18
    //   1185: checkcast gnu/math/RealNum
    //   1188: aload_0
    //   1189: getfield rounding_mode : I
    //   1192: invokevirtual toExactInt : (I)Lgnu/math/IntNum;
    //   1195: astore #18
    //   1197: iconst_4
    //   1198: istore #8
    //   1200: iconst_4
    //   1201: istore #9
    //   1203: goto -> 442
    //   1206: aload #18
    //   1208: checkcast gnu/math/RealNum
    //   1211: aload_0
    //   1212: getfield rounding_mode : I
    //   1215: invokevirtual toInt : (I)Lgnu/math/RealNum;
    //   1218: astore #18
    //   1220: goto -> 442
    //   1223: aload #18
    //   1225: invokevirtual toInexact : ()Lgnu/math/Numeric;
    //   1228: astore #18
    //   1230: goto -> 442
    //   1233: aload #18
    //   1235: invokevirtual intValue : ()I
    //   1238: invokestatic valueOf : (I)Ljava/lang/Integer;
    //   1241: astore #19
    //   1243: goto -> 508
    //   1246: aload #18
    //   1248: invokevirtual longValue : ()J
    //   1251: invokestatic valueOf : (J)Ljava/lang/Long;
    //   1254: astore #19
    //   1256: goto -> 508
    //   1259: aload #18
    //   1261: invokevirtual floatValue : ()F
    //   1264: invokestatic valueOf : (F)Ljava/lang/Float;
    //   1267: astore #19
    //   1269: goto -> 508
    //   1272: aload #18
    //   1274: invokevirtual doubleValue : ()D
    //   1277: invokestatic valueOf : (D)Ljava/lang/Double;
    //   1280: astore #19
    //   1282: goto -> 508
    //   1285: aload #18
    //   1287: invokestatic asBigInteger : (Ljava/lang/Object;)Ljava/math/BigInteger;
    //   1290: astore #19
    //   1292: goto -> 508
    //   1295: aload #19
    //   1297: areturn
  }
  
  public int getRoundingMode() {
    return this.rounding_mode;
  }
  
  public int numArgs() {
    return (this.op == 4) ? -4095 : 8194;
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/gnu/kawa/functions/DivideOp.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */