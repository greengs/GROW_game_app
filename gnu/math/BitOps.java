package gnu.math;

public class BitOps {
  static final byte[] bit4_count = new byte[] { 
      0, 1, 1, 2, 1, 2, 2, 3, 1, 2, 
      2, 3, 2, 3, 3, 4 };
  
  public static IntNum and(IntNum paramIntNum, int paramInt) {
    if (paramIntNum.words == null)
      return IntNum.make(paramIntNum.ival & paramInt); 
    if (paramInt >= 0)
      return IntNum.make(paramIntNum.words[0] & paramInt); 
    int i = paramIntNum.ival;
    int[] arrayOfInt = new int[i];
    arrayOfInt[0] = paramIntNum.words[0] & paramInt;
    paramInt = i;
    while (true) {
      if (--paramInt > 0) {
        arrayOfInt[paramInt] = paramIntNum.words[paramInt];
        continue;
      } 
      return IntNum.make(arrayOfInt, paramIntNum.ival);
    } 
  }
  
  public static IntNum and(IntNum paramIntNum1, IntNum paramIntNum2) {
    int j;
    int k;
    if (paramIntNum2.words == null)
      return and(paramIntNum1, paramIntNum2.ival); 
    if (paramIntNum1.words == null)
      return and(paramIntNum2, paramIntNum1.ival); 
    IntNum intNum2 = paramIntNum1;
    IntNum intNum1 = paramIntNum2;
    if (paramIntNum1.ival < paramIntNum2.ival) {
      intNum1 = paramIntNum1;
      intNum2 = paramIntNum2;
    } 
    if (intNum1.isNegative()) {
      j = intNum2.ival;
    } else {
      j = intNum1.ival;
    } 
    int[] arrayOfInt = new int[j];
    int i = 0;
    while (true) {
      k = i;
      if (i < intNum1.ival) {
        arrayOfInt[i] = intNum2.words[i] & intNum1.words[i];
        i++;
        continue;
      } 
      break;
    } 
    while (k < j) {
      arrayOfInt[k] = intNum2.words[k];
      k++;
    } 
    return IntNum.make(arrayOfInt, j);
  }
  
  public static int bitCount(int paramInt) {
    int i = 0;
    while (paramInt != 0) {
      i += bit4_count[paramInt & 0xF];
      paramInt >>>= 4;
    } 
    return i;
  }
  
  public static int bitCount(IntNum paramIntNum) {
    int i;
    int j;
    int[] arrayOfInt = paramIntNum.words;
    if (arrayOfInt == null) {
      j = 1;
      i = bitCount(paramIntNum.ival);
    } else {
      j = paramIntNum.ival;
      i = bitCount(arrayOfInt, j);
    } 
    int k = i;
    if (paramIntNum.isNegative())
      k = j * 32 - i; 
    return k;
  }
  
  public static int bitCount(int[] paramArrayOfint, int paramInt) {
    boolean bool = false;
    int i = paramInt;
    paramInt = bool;
    while (true) {
      if (--i >= 0) {
        paramInt += bitCount(paramArrayOfint[i]);
        continue;
      } 
      return paramInt;
    } 
  }
  
  public static IntNum bitOp(int paramInt, IntNum paramIntNum1, IntNum paramIntNum2) {
    IntNum intNum = paramIntNum1;
    switch (paramInt) {
      default:
        intNum = new IntNum();
        setBitOp(intNum, paramInt, paramIntNum1, paramIntNum2);
        intNum = intNum.canonicalize();
      case 3:
        return intNum;
      case 0:
        return IntNum.zero();
      case 1:
        return and(paramIntNum1, paramIntNum2);
      case 5:
        return paramIntNum2;
      case 15:
        break;
    } 
    return IntNum.minusOne();
  }
  
  public static boolean bitValue(IntNum paramIntNum, int paramInt) {
    int i = paramIntNum.ival;
    if (paramIntNum.words == null)
      return (paramInt >= 32) ? (!(i >= 0)) : (!((i >> paramInt & 0x1) == 0)); 
    int j = paramInt >> 5;
    return (j >= i) ? (!(paramIntNum.words[i - 1] >= 0)) : (!((paramIntNum.words[j] >> paramInt & 0x1) == 0));
  }
  
  static int[] dataBufferFor(IntNum paramIntNum, int paramInt) {
    int[] arrayOfInt;
    int i = paramIntNum.ival;
    int j = paramInt + 1 >> 5;
    if (paramIntNum.words == null) {
      paramInt = j;
      if (j == 0)
        paramInt = 1; 
      int[] arrayOfInt1 = new int[paramInt];
      arrayOfInt1[0] = i;
      arrayOfInt = arrayOfInt1;
      if (i < 0) {
        i = 1;
        while (true) {
          arrayOfInt = arrayOfInt1;
          if (i < paramInt) {
            arrayOfInt1[i] = -1;
            i++;
            continue;
          } 
          break;
        } 
      } 
    } else {
      j = paramInt + 1 >> 5;
      if (j > i) {
        paramInt = j;
      } else {
        paramInt = i;
      } 
      int[] arrayOfInt1 = new int[paramInt];
      paramInt = i;
      while (true) {
        if (--paramInt >= 0) {
          arrayOfInt1[paramInt] = ((IntNum)arrayOfInt).words[paramInt];
          continue;
        } 
        arrayOfInt = arrayOfInt1;
        if (arrayOfInt1[i - 1] < 0)
          while (true) {
            arrayOfInt = arrayOfInt1;
            if (i < j) {
              arrayOfInt1[i] = -1;
              i++;
              continue;
            } 
            break;
          }  
        return arrayOfInt;
      } 
    } 
    return arrayOfInt;
  }
  
  public static IntNum extract(IntNum paramIntNum, int paramInt1, int paramInt2) {
    int j;
    if (paramInt2 < 32) {
      if (paramIntNum.words == null) {
        i = paramIntNum.ival;
      } else {
        i = paramIntNum.words[0];
      } 
      return IntNum.make(((-1 << paramInt2 ^ 0xFFFFFFFF) & i) >> paramInt1);
    } 
    if (paramIntNum.words == null) {
      if (paramIntNum.ival >= 0) {
        if (paramInt1 >= 31) {
          paramInt1 = 0;
          return IntNum.make(paramInt1);
        } 
        paramInt1 = paramIntNum.ival >> paramInt1;
        return IntNum.make(paramInt1);
      } 
      i = 1;
    } else {
      i = paramIntNum.ival;
    } 
    boolean bool = paramIntNum.isNegative();
    if (paramInt2 > i * 32) {
      int m = i * 32;
      j = i;
      paramInt2 = m;
      if (!bool) {
        IntNum intNum = paramIntNum;
        if (paramInt1 != 0) {
          paramInt2 = m;
          j = i;
        } else {
          return intNum;
        } 
      } 
    } else {
      j = paramInt2 + 31 >> 5;
    } 
    int i = paramInt2 - paramInt1;
    if (i < 64) {
      if (paramIntNum.words == null) {
        paramInt2 = paramIntNum.ival;
        if (paramInt1 >= 32)
          paramInt1 = 31; 
        long l1 = (paramInt2 >> paramInt1);
        return IntNum.make((-1L << i ^ 0xFFFFFFFFFFFFFFFFL) & l1);
      } 
      long l = MPN.rshift_long(paramIntNum.words, j, paramInt1);
      return IntNum.make((-1L << i ^ 0xFFFFFFFFFFFFFFFFL) & l);
    } 
    int k = paramInt1 >> 5;
    int[] arrayOfInt = new int[(paramInt2 >> 5) + 1 - k];
    if (paramIntNum.words == null) {
      if (paramInt1 >= 32) {
        paramInt1 = -1;
      } else {
        paramInt1 = paramIntNum.ival >> paramInt1;
      } 
      arrayOfInt[0] = paramInt1;
      paramInt1 = i >> 5;
      arrayOfInt[paramInt1] = arrayOfInt[paramInt1] & (-1 << i ^ 0xFFFFFFFF);
      return IntNum.make(arrayOfInt, paramInt1 + 1);
    } 
    MPN.rshift0(arrayOfInt, paramIntNum.words, k, j - k, paramInt1 & 0x1F);
    paramInt1 = i >> 5;
    arrayOfInt[paramInt1] = arrayOfInt[paramInt1] & (-1 << i ^ 0xFFFFFFFF);
    return IntNum.make(arrayOfInt, paramInt1 + 1);
  }
  
  public static IntNum ior(IntNum paramIntNum1, IntNum paramIntNum2) {
    return bitOp(7, paramIntNum1, paramIntNum2);
  }
  
  public static int lowestBitSet(int paramInt) {
    int k;
    if (paramInt == 0)
      return -1; 
    int i = 0;
    int j = paramInt;
    while (true) {
      paramInt = i;
      k = j;
      if ((j & 0xFF) == 0) {
        j >>>= 8;
        i += 8;
        continue;
      } 
      break;
    } 
    while ((k & 0x3) == 0) {
      k >>>= 2;
      paramInt += 2;
    } 
    i = paramInt;
    return ((k & 0x1) == 0) ? (paramInt + 1) : i;
  }
  
  public static int lowestBitSet(IntNum paramIntNum) {
    int[] arrayOfInt = paramIntNum.words;
    if (arrayOfInt == null)
      return lowestBitSet(paramIntNum.ival); 
    int i = paramIntNum.ival;
    while (i < 0) {
      int j = lowestBitSet(arrayOfInt[0]);
      if (j >= 0)
        return j + 0; 
    } 
    return -1;
  }
  
  public static IntNum not(IntNum paramIntNum) {
    return bitOp(12, paramIntNum, IntNum.zero());
  }
  
  public static IntNum reverseBits(IntNum paramIntNum, int paramInt1, int paramInt2) {
    int i = paramIntNum.ival;
    if (paramIntNum.words == null && paramInt2 < 63) {
      long l = i;
      while (paramInt1 < --paramInt2) {
        l = (l >> paramInt1 & 0x1L) << paramInt2 | l & ((1L << paramInt1 | 1L << paramInt2) ^ 0xFFFFFFFFFFFFFFFFL) | (l >> paramInt2 & 0x1L) << paramInt1;
        paramInt1++;
        paramInt2--;
      } 
      return IntNum.make(l);
    } 
    int[] arrayOfInt = dataBufferFor(paramIntNum, paramInt2 - 1);
    i = paramInt1;
    paramInt1 = paramInt2 - 1;
    paramInt2 = i;
    while (paramInt2 < paramInt1) {
      int j = paramInt2 >> 5;
      int k = paramInt1 >> 5;
      i = arrayOfInt[j];
      int m = i >> paramInt2 & 0x1;
      if (j == k) {
        i = m << paramInt1 | (int)(i & ((1L << paramInt2 | 1L << paramInt1) ^ 0xFFFFFFFFFFFFFFFFL)) | (i >> paramInt1 & 0x1) << paramInt2;
      } else {
        int n = arrayOfInt[k];
        i = i & (1 << (paramInt2 & 0x1F) ^ 0xFFFFFFFF) | (n >> (paramInt1 & 0x1F) & 0x1) << (paramInt2 & 0x1F);
        arrayOfInt[k] = n & (1 << (paramInt1 & 0x1F) ^ 0xFFFFFFFF) | m << (paramInt1 & 0x1F);
      } 
      arrayOfInt[j] = i;
      paramInt2++;
      paramInt1--;
    } 
    return IntNum.make(arrayOfInt, arrayOfInt.length);
  }
  
  public static void setBitOp(IntNum paramIntNum1, int paramInt, IntNum paramIntNum2, IntNum paramIntNum3) {
    // Byte code:
    //   0: aload_3
    //   1: getfield words : [I
    //   4: ifnonnull -> 202
    //   7: aload_3
    //   8: astore #12
    //   10: aload_2
    //   11: astore #11
    //   13: iload_1
    //   14: istore #6
    //   16: aload #12
    //   18: getfield words : [I
    //   21: ifnonnull -> 244
    //   24: aload #12
    //   26: getfield ival : I
    //   29: istore_1
    //   30: iconst_1
    //   31: istore #5
    //   33: aload #11
    //   35: getfield words : [I
    //   38: ifnonnull -> 262
    //   41: aload #11
    //   43: getfield ival : I
    //   46: istore #4
    //   48: iconst_1
    //   49: istore #7
    //   51: iload #7
    //   53: iconst_1
    //   54: if_icmple -> 63
    //   57: aload_0
    //   58: iload #7
    //   60: invokevirtual realloc : (I)V
    //   63: aload_0
    //   64: getfield words : [I
    //   67: astore_2
    //   68: iconst_0
    //   69: istore #9
    //   71: iload #6
    //   73: tableswitch default -> 148, 0 -> 281, 1 -> 1403, 2 -> 1394, 3 -> 449, 4 -> 1385, 5 -> 1379, 6 -> 1373, 7 -> 1364, 8 -> 1355, 9 -> 1349, 10 -> 1343, 11 -> 1334, 12 -> 1035, 13 -> 1325, 14 -> 1316
    //   148: iconst_m1
    //   149: istore #4
    //   151: iconst_0
    //   152: istore_1
    //   153: iload #9
    //   155: istore #5
    //   157: iload_1
    //   158: iconst_1
    //   159: iadd
    //   160: iload #7
    //   162: if_icmpne -> 168
    //   165: iconst_0
    //   166: istore #5
    //   168: iload #5
    //   170: tableswitch default -> 196, 0 -> 1207, 1 -> 1238, 2 -> 1276
    //   196: aload_0
    //   197: iload_1
    //   198: putfield ival : I
    //   201: return
    //   202: aload_2
    //   203: getfield words : [I
    //   206: ifnull -> 229
    //   209: iload_1
    //   210: istore #6
    //   212: aload_2
    //   213: astore #11
    //   215: aload_3
    //   216: astore #12
    //   218: aload_2
    //   219: getfield ival : I
    //   222: aload_3
    //   223: getfield ival : I
    //   226: if_icmpge -> 16
    //   229: iload_1
    //   230: invokestatic swappedOp : (I)I
    //   233: istore #6
    //   235: aload_3
    //   236: astore #11
    //   238: aload_2
    //   239: astore #12
    //   241: goto -> 16
    //   244: aload #12
    //   246: getfield words : [I
    //   249: iconst_0
    //   250: iaload
    //   251: istore_1
    //   252: aload #12
    //   254: getfield ival : I
    //   257: istore #5
    //   259: goto -> 33
    //   262: aload #11
    //   264: getfield words : [I
    //   267: iconst_0
    //   268: iaload
    //   269: istore #4
    //   271: aload #11
    //   273: getfield ival : I
    //   276: istore #7
    //   278: goto -> 51
    //   281: iconst_0
    //   282: istore #4
    //   284: iconst_0
    //   285: istore_1
    //   286: iload #9
    //   288: istore #5
    //   290: goto -> 157
    //   293: iload #6
    //   295: iconst_1
    //   296: iadd
    //   297: istore_1
    //   298: aload_2
    //   299: iload #6
    //   301: iload #10
    //   303: iastore
    //   304: aload #11
    //   306: getfield words : [I
    //   309: iload_1
    //   310: iaload
    //   311: istore #4
    //   313: aload #12
    //   315: getfield words : [I
    //   318: iload_1
    //   319: iaload
    //   320: istore #8
    //   322: iload_1
    //   323: istore #6
    //   325: iload #4
    //   327: iload #8
    //   329: iand
    //   330: istore #10
    //   332: iload #6
    //   334: iconst_1
    //   335: iadd
    //   336: iload #5
    //   338: if_icmplt -> 293
    //   341: iload #9
    //   343: istore #5
    //   345: iload #6
    //   347: istore_1
    //   348: iload #10
    //   350: istore #4
    //   352: iload #8
    //   354: ifge -> 157
    //   357: iconst_1
    //   358: istore #5
    //   360: iload #6
    //   362: istore_1
    //   363: iload #10
    //   365: istore #4
    //   367: goto -> 157
    //   370: iload #6
    //   372: iconst_1
    //   373: iadd
    //   374: istore_1
    //   375: aload_2
    //   376: iload #6
    //   378: iload #10
    //   380: iastore
    //   381: aload #11
    //   383: getfield words : [I
    //   386: iload_1
    //   387: iaload
    //   388: istore #4
    //   390: aload #12
    //   392: getfield words : [I
    //   395: iload_1
    //   396: iaload
    //   397: istore #8
    //   399: iload_1
    //   400: istore #6
    //   402: iload #4
    //   404: iload #8
    //   406: iconst_m1
    //   407: ixor
    //   408: iand
    //   409: istore #10
    //   411: iload #6
    //   413: iconst_1
    //   414: iadd
    //   415: iload #5
    //   417: if_icmplt -> 370
    //   420: iload #9
    //   422: istore #5
    //   424: iload #6
    //   426: istore_1
    //   427: iload #10
    //   429: istore #4
    //   431: iload #8
    //   433: iflt -> 157
    //   436: iconst_1
    //   437: istore #5
    //   439: iload #6
    //   441: istore_1
    //   442: iload #10
    //   444: istore #4
    //   446: goto -> 157
    //   449: iconst_1
    //   450: istore #5
    //   452: iconst_0
    //   453: istore_1
    //   454: goto -> 157
    //   457: iload #6
    //   459: iconst_1
    //   460: iadd
    //   461: istore_1
    //   462: aload_2
    //   463: iload #6
    //   465: iload #10
    //   467: iastore
    //   468: aload #11
    //   470: getfield words : [I
    //   473: iload_1
    //   474: iaload
    //   475: istore #4
    //   477: aload #12
    //   479: getfield words : [I
    //   482: iload_1
    //   483: iaload
    //   484: istore #8
    //   486: iload_1
    //   487: istore #6
    //   489: iload #4
    //   491: iconst_m1
    //   492: ixor
    //   493: iload #8
    //   495: iand
    //   496: istore #10
    //   498: iload #6
    //   500: iconst_1
    //   501: iadd
    //   502: iload #5
    //   504: if_icmplt -> 457
    //   507: iload #9
    //   509: istore #5
    //   511: iload #6
    //   513: istore_1
    //   514: iload #10
    //   516: istore #4
    //   518: iload #8
    //   520: ifge -> 157
    //   523: iconst_2
    //   524: istore #5
    //   526: iload #6
    //   528: istore_1
    //   529: iload #10
    //   531: istore #4
    //   533: goto -> 157
    //   536: iload #4
    //   538: iconst_1
    //   539: iadd
    //   540: istore_1
    //   541: aload_2
    //   542: iload #4
    //   544: iload #6
    //   546: iastore
    //   547: aload #11
    //   549: getfield words : [I
    //   552: iload_1
    //   553: iaload
    //   554: istore #4
    //   556: aload #12
    //   558: getfield words : [I
    //   561: iload_1
    //   562: iaload
    //   563: istore #6
    //   565: iload_1
    //   566: istore #4
    //   568: iload #6
    //   570: istore_1
    //   571: iload_1
    //   572: istore #6
    //   574: iload #4
    //   576: iconst_1
    //   577: iadd
    //   578: iload #5
    //   580: if_icmplt -> 536
    //   583: iload #9
    //   585: istore #5
    //   587: iload #4
    //   589: istore_1
    //   590: iload #6
    //   592: istore #4
    //   594: goto -> 157
    //   597: iload #6
    //   599: iconst_1
    //   600: iadd
    //   601: istore_1
    //   602: aload_2
    //   603: iload #6
    //   605: iload #4
    //   607: iastore
    //   608: aload #11
    //   610: getfield words : [I
    //   613: iload_1
    //   614: iaload
    //   615: istore #4
    //   617: aload #12
    //   619: getfield words : [I
    //   622: iload_1
    //   623: iaload
    //   624: istore #8
    //   626: iload_1
    //   627: istore #6
    //   629: iload #8
    //   631: istore_1
    //   632: iload #4
    //   634: iload_1
    //   635: ixor
    //   636: istore #4
    //   638: iload #6
    //   640: iconst_1
    //   641: iadd
    //   642: iload #5
    //   644: if_icmplt -> 597
    //   647: iload_1
    //   648: ifge -> 660
    //   651: iconst_2
    //   652: istore #5
    //   654: iload #6
    //   656: istore_1
    //   657: goto -> 157
    //   660: iconst_1
    //   661: istore #5
    //   663: goto -> 654
    //   666: iload #6
    //   668: iconst_1
    //   669: iadd
    //   670: istore_1
    //   671: aload_2
    //   672: iload #6
    //   674: iload #10
    //   676: iastore
    //   677: aload #11
    //   679: getfield words : [I
    //   682: iload_1
    //   683: iaload
    //   684: istore #4
    //   686: aload #12
    //   688: getfield words : [I
    //   691: iload_1
    //   692: iaload
    //   693: istore #8
    //   695: iload_1
    //   696: istore #6
    //   698: iload #4
    //   700: iload #8
    //   702: ior
    //   703: istore #10
    //   705: iload #6
    //   707: iconst_1
    //   708: iadd
    //   709: iload #5
    //   711: if_icmplt -> 666
    //   714: iload #9
    //   716: istore #5
    //   718: iload #6
    //   720: istore_1
    //   721: iload #10
    //   723: istore #4
    //   725: iload #8
    //   727: iflt -> 157
    //   730: iconst_1
    //   731: istore #5
    //   733: iload #6
    //   735: istore_1
    //   736: iload #10
    //   738: istore #4
    //   740: goto -> 157
    //   743: iload #6
    //   745: iconst_1
    //   746: iadd
    //   747: istore_1
    //   748: aload_2
    //   749: iload #6
    //   751: iload #10
    //   753: iastore
    //   754: aload #11
    //   756: getfield words : [I
    //   759: iload_1
    //   760: iaload
    //   761: istore #4
    //   763: aload #12
    //   765: getfield words : [I
    //   768: iload_1
    //   769: iaload
    //   770: istore #8
    //   772: iload_1
    //   773: istore #6
    //   775: iload #4
    //   777: iload #8
    //   779: ior
    //   780: iconst_m1
    //   781: ixor
    //   782: istore #10
    //   784: iload #6
    //   786: iconst_1
    //   787: iadd
    //   788: iload #5
    //   790: if_icmplt -> 743
    //   793: iload #9
    //   795: istore #5
    //   797: iload #6
    //   799: istore_1
    //   800: iload #10
    //   802: istore #4
    //   804: iload #8
    //   806: iflt -> 157
    //   809: iconst_2
    //   810: istore #5
    //   812: iload #6
    //   814: istore_1
    //   815: iload #10
    //   817: istore #4
    //   819: goto -> 157
    //   822: iload #6
    //   824: iconst_1
    //   825: iadd
    //   826: istore_1
    //   827: aload_2
    //   828: iload #6
    //   830: iload #4
    //   832: iastore
    //   833: aload #11
    //   835: getfield words : [I
    //   838: iload_1
    //   839: iaload
    //   840: istore #4
    //   842: aload #12
    //   844: getfield words : [I
    //   847: iload_1
    //   848: iaload
    //   849: istore #8
    //   851: iload_1
    //   852: istore #6
    //   854: iload #8
    //   856: istore_1
    //   857: iload #4
    //   859: iload_1
    //   860: ixor
    //   861: iconst_m1
    //   862: ixor
    //   863: istore #4
    //   865: iload #6
    //   867: iconst_1
    //   868: iadd
    //   869: iload #5
    //   871: if_icmplt -> 822
    //   874: iload_1
    //   875: iflt -> 887
    //   878: iconst_2
    //   879: istore #5
    //   881: iload #6
    //   883: istore_1
    //   884: goto -> 157
    //   887: iconst_1
    //   888: istore #5
    //   890: goto -> 881
    //   893: iload #4
    //   895: iconst_1
    //   896: iadd
    //   897: istore_1
    //   898: aload_2
    //   899: iload #4
    //   901: iload #6
    //   903: iastore
    //   904: aload #11
    //   906: getfield words : [I
    //   909: iload_1
    //   910: iaload
    //   911: istore #4
    //   913: aload #12
    //   915: getfield words : [I
    //   918: iload_1
    //   919: iaload
    //   920: istore #6
    //   922: iload_1
    //   923: istore #4
    //   925: iload #6
    //   927: istore_1
    //   928: iload_1
    //   929: iconst_m1
    //   930: ixor
    //   931: istore #6
    //   933: iload #4
    //   935: iconst_1
    //   936: iadd
    //   937: iload #5
    //   939: if_icmplt -> 893
    //   942: iload #9
    //   944: istore #5
    //   946: iload #4
    //   948: istore_1
    //   949: iload #6
    //   951: istore #4
    //   953: goto -> 157
    //   956: iload #6
    //   958: iconst_1
    //   959: iadd
    //   960: istore_1
    //   961: aload_2
    //   962: iload #6
    //   964: iload #10
    //   966: iastore
    //   967: aload #11
    //   969: getfield words : [I
    //   972: iload_1
    //   973: iaload
    //   974: istore #4
    //   976: aload #12
    //   978: getfield words : [I
    //   981: iload_1
    //   982: iaload
    //   983: istore #8
    //   985: iload_1
    //   986: istore #6
    //   988: iload #4
    //   990: iload #8
    //   992: iconst_m1
    //   993: ixor
    //   994: ior
    //   995: istore #10
    //   997: iload #6
    //   999: iconst_1
    //   1000: iadd
    //   1001: iload #5
    //   1003: if_icmplt -> 956
    //   1006: iload #9
    //   1008: istore #5
    //   1010: iload #6
    //   1012: istore_1
    //   1013: iload #10
    //   1015: istore #4
    //   1017: iload #8
    //   1019: ifge -> 157
    //   1022: iconst_1
    //   1023: istore #5
    //   1025: iload #6
    //   1027: istore_1
    //   1028: iload #10
    //   1030: istore #4
    //   1032: goto -> 157
    //   1035: iload #4
    //   1037: iconst_m1
    //   1038: ixor
    //   1039: istore #4
    //   1041: iconst_2
    //   1042: istore #5
    //   1044: iconst_0
    //   1045: istore_1
    //   1046: goto -> 157
    //   1049: iload #6
    //   1051: iconst_1
    //   1052: iadd
    //   1053: istore_1
    //   1054: aload_2
    //   1055: iload #6
    //   1057: iload #10
    //   1059: iastore
    //   1060: aload #11
    //   1062: getfield words : [I
    //   1065: iload_1
    //   1066: iaload
    //   1067: istore #4
    //   1069: aload #12
    //   1071: getfield words : [I
    //   1074: iload_1
    //   1075: iaload
    //   1076: istore #8
    //   1078: iload_1
    //   1079: istore #6
    //   1081: iload #4
    //   1083: iconst_m1
    //   1084: ixor
    //   1085: iload #8
    //   1087: ior
    //   1088: istore #10
    //   1090: iload #6
    //   1092: iconst_1
    //   1093: iadd
    //   1094: iload #5
    //   1096: if_icmplt -> 1049
    //   1099: iload #9
    //   1101: istore #5
    //   1103: iload #6
    //   1105: istore_1
    //   1106: iload #10
    //   1108: istore #4
    //   1110: iload #8
    //   1112: iflt -> 157
    //   1115: iconst_2
    //   1116: istore #5
    //   1118: iload #6
    //   1120: istore_1
    //   1121: iload #10
    //   1123: istore #4
    //   1125: goto -> 157
    //   1128: iload #6
    //   1130: iconst_1
    //   1131: iadd
    //   1132: istore_1
    //   1133: aload_2
    //   1134: iload #6
    //   1136: iload #10
    //   1138: iastore
    //   1139: aload #11
    //   1141: getfield words : [I
    //   1144: iload_1
    //   1145: iaload
    //   1146: istore #4
    //   1148: aload #12
    //   1150: getfield words : [I
    //   1153: iload_1
    //   1154: iaload
    //   1155: istore #8
    //   1157: iload_1
    //   1158: istore #6
    //   1160: iload #4
    //   1162: iload #8
    //   1164: iand
    //   1165: iconst_m1
    //   1166: ixor
    //   1167: istore #10
    //   1169: iload #6
    //   1171: iconst_1
    //   1172: iadd
    //   1173: iload #5
    //   1175: if_icmplt -> 1128
    //   1178: iload #9
    //   1180: istore #5
    //   1182: iload #6
    //   1184: istore_1
    //   1185: iload #10
    //   1187: istore #4
    //   1189: iload #8
    //   1191: ifge -> 157
    //   1194: iconst_2
    //   1195: istore #5
    //   1197: iload #6
    //   1199: istore_1
    //   1200: iload #10
    //   1202: istore #4
    //   1204: goto -> 157
    //   1207: iload_1
    //   1208: ifne -> 1222
    //   1211: aload_2
    //   1212: ifnonnull -> 1222
    //   1215: aload_0
    //   1216: iload #4
    //   1218: putfield ival : I
    //   1221: return
    //   1222: iload_1
    //   1223: iconst_1
    //   1224: iadd
    //   1225: istore #5
    //   1227: aload_2
    //   1228: iload_1
    //   1229: iload #4
    //   1231: iastore
    //   1232: iload #5
    //   1234: istore_1
    //   1235: goto -> 196
    //   1238: aload_2
    //   1239: iload_1
    //   1240: iload #4
    //   1242: iastore
    //   1243: iload_1
    //   1244: iconst_1
    //   1245: iadd
    //   1246: istore #4
    //   1248: iload #4
    //   1250: istore_1
    //   1251: iload #4
    //   1253: iload #7
    //   1255: if_icmpge -> 196
    //   1258: aload_2
    //   1259: iload #4
    //   1261: aload #11
    //   1263: getfield words : [I
    //   1266: iload #4
    //   1268: iaload
    //   1269: iastore
    //   1270: iload #4
    //   1272: istore_1
    //   1273: goto -> 1243
    //   1276: aload_2
    //   1277: iload_1
    //   1278: iload #4
    //   1280: iastore
    //   1281: iload_1
    //   1282: iconst_1
    //   1283: iadd
    //   1284: istore #4
    //   1286: iload #4
    //   1288: istore_1
    //   1289: iload #4
    //   1291: iload #7
    //   1293: if_icmpge -> 196
    //   1296: aload_2
    //   1297: iload #4
    //   1299: aload #11
    //   1301: getfield words : [I
    //   1304: iload #4
    //   1306: iaload
    //   1307: iconst_m1
    //   1308: ixor
    //   1309: iastore
    //   1310: iload #4
    //   1312: istore_1
    //   1313: goto -> 1281
    //   1316: iconst_0
    //   1317: istore #6
    //   1319: iload_1
    //   1320: istore #8
    //   1322: goto -> 1160
    //   1325: iconst_0
    //   1326: istore #6
    //   1328: iload_1
    //   1329: istore #8
    //   1331: goto -> 1081
    //   1334: iconst_0
    //   1335: istore #6
    //   1337: iload_1
    //   1338: istore #8
    //   1340: goto -> 988
    //   1343: iconst_0
    //   1344: istore #4
    //   1346: goto -> 928
    //   1349: iconst_0
    //   1350: istore #6
    //   1352: goto -> 857
    //   1355: iconst_0
    //   1356: istore #6
    //   1358: iload_1
    //   1359: istore #8
    //   1361: goto -> 775
    //   1364: iconst_0
    //   1365: istore #6
    //   1367: iload_1
    //   1368: istore #8
    //   1370: goto -> 698
    //   1373: iconst_0
    //   1374: istore #6
    //   1376: goto -> 632
    //   1379: iconst_0
    //   1380: istore #4
    //   1382: goto -> 571
    //   1385: iconst_0
    //   1386: istore #6
    //   1388: iload_1
    //   1389: istore #8
    //   1391: goto -> 489
    //   1394: iconst_0
    //   1395: istore #6
    //   1397: iload_1
    //   1398: istore #8
    //   1400: goto -> 402
    //   1403: iconst_0
    //   1404: istore #6
    //   1406: iload_1
    //   1407: istore #8
    //   1409: goto -> 325
  }
  
  public static IntNum setBitValue(IntNum paramIntNum, int paramInt1, int paramInt2) {
    byte b = 31;
    int i = paramInt2 & 0x1;
    int j = paramIntNum.ival;
    if (paramIntNum.words == null) {
      paramInt2 = b;
      if (paramInt1 < 31)
        paramInt2 = paramInt1; 
      if ((j >> paramInt2 & 0x1) == i)
        return paramIntNum; 
      if (paramInt1 < 63) {
        long l = j;
        return IntNum.make((1 << paramInt1) ^ l);
      } 
    } else {
      paramInt2 = paramInt1 >> 5;
      if (paramInt2 >= j) {
        if (paramIntNum.words[j - 1] < 0) {
          paramInt2 = 1;
        } else {
          paramInt2 = 0;
        } 
      } else {
        paramInt2 = paramIntNum.words[paramInt2] >> paramInt1 & 0x1;
      } 
      if (paramInt2 == i)
        return paramIntNum; 
    } 
    int[] arrayOfInt = dataBufferFor(paramIntNum, paramInt1);
    paramInt2 = paramInt1 >> 5;
    arrayOfInt[paramInt2] = 1 << (paramInt1 & 0x1F) ^ arrayOfInt[paramInt2];
    return IntNum.make(arrayOfInt, arrayOfInt.length);
  }
  
  public static int swappedOp(int paramInt) {
    return "\000\001\004\005\002\003\006\007\b\t\f\r\n\013\016\017".charAt(paramInt);
  }
  
  public static boolean test(IntNum paramIntNum, int paramInt) {
    boolean bool = false;
    if (paramIntNum.words == null)
      return ((paramIntNum.ival & paramInt) != 0); 
    if (paramInt < 0 || (paramIntNum.words[0] & paramInt) != 0)
      bool = true; 
    return bool;
  }
  
  public static boolean test(IntNum paramIntNum1, IntNum paramIntNum2) {
    if (paramIntNum2.words == null)
      return test(paramIntNum1, paramIntNum2.ival); 
    if (paramIntNum1.words == null)
      return test(paramIntNum2, paramIntNum1.ival); 
    IntNum intNum2 = paramIntNum1;
    IntNum intNum1 = paramIntNum2;
    if (paramIntNum1.ival < paramIntNum2.ival) {
      intNum1 = paramIntNum1;
      intNum2 = paramIntNum2;
    } 
    for (int i = 0; i < intNum1.ival; i++) {
      if ((intNum2.words[i] & intNum1.words[i]) != 0)
        return true; 
    } 
    return intNum1.isNegative();
  }
  
  public static IntNum xor(IntNum paramIntNum1, IntNum paramIntNum2) {
    return bitOp(6, paramIntNum1, paramIntNum2);
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/gnu/math/BitOps.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */