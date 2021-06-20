package gnu.math;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.io.ObjectStreamException;
import java.math.BigDecimal;
import java.math.BigInteger;

public class IntNum extends RatNum implements Externalizable {
  static final int maxFixNum = 1024;
  
  static final int minFixNum = -100;
  
  static final int numFixNum = 1125;
  
  static final IntNum[] smallFixNums = new IntNum[1125];
  
  public int ival;
  
  public int[] words;
  
  static {
    int i = 1125;
    while (true) {
      if (--i >= 0) {
        smallFixNums[i] = new IntNum(i - 100);
        continue;
      } 
      break;
    } 
  }
  
  public IntNum() {}
  
  public IntNum(int paramInt) {
    this.ival = paramInt;
  }
  
  public static IntNum abs(IntNum paramIntNum) {
    IntNum intNum = paramIntNum;
    if (paramIntNum.isNegative())
      intNum = neg(paramIntNum); 
    return intNum;
  }
  
  public static final IntNum add(int paramInt1, int paramInt2) {
    return make(paramInt1 + paramInt2);
  }
  
  public static IntNum add(IntNum paramIntNum, int paramInt) {
    if (paramIntNum.words == null)
      return add(paramIntNum.ival, paramInt); 
    IntNum intNum = new IntNum(0);
    intNum.setAdd(paramIntNum, paramInt);
    return intNum.canonicalize();
  }
  
  public static IntNum add(IntNum paramIntNum1, IntNum paramIntNum2) {
    return add(paramIntNum1, paramIntNum2, 1);
  }
  
  public static IntNum add(IntNum paramIntNum1, IntNum paramIntNum2, int paramInt) {
    long l1;
    if (paramIntNum1.words == null && paramIntNum2.words == null)
      return make(paramInt * paramIntNum2.ival + paramIntNum1.ival); 
    IntNum intNum1 = paramIntNum2;
    if (paramInt != 1)
      if (paramInt == -1) {
        intNum1 = neg(paramIntNum2);
      } else {
        intNum1 = times(paramIntNum2, make(paramInt));
      }  
    if (paramIntNum1.words == null)
      return add(intNum1, paramIntNum1.ival); 
    if (intNum1.words == null)
      return add(paramIntNum1, intNum1.ival); 
    IntNum intNum2 = paramIntNum1;
    paramIntNum2 = intNum1;
    if (intNum1.ival > paramIntNum1.ival) {
      paramIntNum2 = paramIntNum1;
      intNum2 = intNum1;
    } 
    paramIntNum1 = alloc(intNum2.ival + 1);
    paramInt = paramIntNum2.ival;
    long l2 = MPN.add_n(paramIntNum1.words, intNum2.words, paramIntNum2.words, paramInt);
    if (paramIntNum2.words[paramInt - 1] < 0) {
      l1 = 4294967295L;
    } else {
      l1 = 0L;
    } 
    while (paramInt < intNum2.ival) {
      l2 += (intNum2.words[paramInt] & 0xFFFFFFFFL) + l1;
      paramIntNum1.words[paramInt] = (int)l2;
      l2 >>>= 32L;
      paramInt++;
    } 
    long l3 = l1;
    if (intNum2.words[paramInt - 1] < 0)
      l3 = l1 - 1L; 
    paramIntNum1.words[paramInt] = (int)(l2 + l3);
    paramIntNum1.ival = paramInt + 1;
    return paramIntNum1.canonicalize();
  }
  
  public static IntNum alloc(int paramInt) {
    if (paramInt <= 1)
      return new IntNum(); 
    IntNum intNum = new IntNum();
    intNum.words = new int[paramInt];
    return intNum;
  }
  
  public static IntNum asIntNumOrNull(Object paramObject) {
    return (paramObject instanceof IntNum) ? (IntNum)paramObject : ((paramObject instanceof BigInteger) ? valueOf(paramObject.toString(), 10) : ((paramObject instanceof Number && (paramObject instanceof Integer || paramObject instanceof Long || paramObject instanceof Short || paramObject instanceof Byte)) ? make(((Number)paramObject).longValue()) : null));
  }
  
  public static int compare(IntNum paramIntNum, long paramLong) {
    long l;
    if (paramIntNum.words == null) {
      l = paramIntNum.ival;
    } else {
      int i;
      boolean bool1;
      boolean bool2 = paramIntNum.isNegative();
      if (paramLong < 0L) {
        bool1 = true;
      } else {
        bool1 = false;
      } 
      if (bool2 != bool1)
        return !bool2 ? 1 : -1; 
      if (paramIntNum.words == null) {
        i = 1;
      } else {
        i = paramIntNum.ival;
      } 
      if (i == 1) {
        l = paramIntNum.words[0];
      } else if (i == 2) {
        l = paramIntNum.longValue();
      } else {
        return !bool2 ? 1 : -1;
      } 
    } 
    return (l >= paramLong) ? ((l > paramLong) ? 1 : 0) : -1;
  }
  
  public static int compare(IntNum paramIntNum1, IntNum paramIntNum2) {
    int i;
    int j;
    boolean bool1 = false;
    boolean bool = true;
    if (paramIntNum1.words == null && paramIntNum2.words == null)
      return (paramIntNum1.ival >= paramIntNum2.ival) ? ((paramIntNum1.ival > paramIntNum2.ival) ? 1 : 0) : -1; 
    boolean bool2 = paramIntNum1.isNegative();
    if (bool2 != paramIntNum2.isNegative())
      return !bool2 ? 1 : -1; 
    if (paramIntNum1.words == null) {
      i = 1;
    } else {
      i = paramIntNum1.ival;
    } 
    if (paramIntNum2.words == null) {
      j = 1;
    } else {
      j = paramIntNum2.ival;
    } 
    if (i != j) {
      if (i > j)
        bool1 = true; 
      return (bool1 != bool2) ? bool : -1;
    } 
    return MPN.cmp(paramIntNum1.words, paramIntNum2.words, i);
  }
  
  public static void divide(long paramLong1, long paramLong2, IntNum paramIntNum1, IntNum paramIntNum2, int paramInt) {
    // Byte code:
    //   0: iload #6
    //   2: istore #7
    //   4: iload #6
    //   6: iconst_5
    //   7: if_icmpne -> 19
    //   10: lload_2
    //   11: lconst_0
    //   12: lcmp
    //   13: ifge -> 54
    //   16: iconst_2
    //   17: istore #7
    //   19: lload_0
    //   20: lconst_0
    //   21: lcmp
    //   22: ifge -> 109
    //   25: iconst_1
    //   26: istore #6
    //   28: lload_0
    //   29: ldc2_w -9223372036854775808
    //   32: lcmp
    //   33: ifne -> 60
    //   36: lload_0
    //   37: invokestatic make : (J)Lgnu/math/IntNum;
    //   40: lload_2
    //   41: invokestatic make : (J)Lgnu/math/IntNum;
    //   44: aload #4
    //   46: aload #5
    //   48: iload #7
    //   50: invokestatic divide : (Lgnu/math/IntNum;Lgnu/math/IntNum;Lgnu/math/IntNum;Lgnu/math/IntNum;I)V
    //   53: return
    //   54: iconst_1
    //   55: istore #7
    //   57: goto -> 19
    //   60: lload_0
    //   61: lneg
    //   62: lstore_0
    //   63: lload_2
    //   64: lconst_0
    //   65: lcmp
    //   66: ifge -> 292
    //   69: iconst_1
    //   70: istore #8
    //   72: lload_2
    //   73: ldc2_w -9223372036854775808
    //   76: lcmp
    //   77: ifne -> 133
    //   80: iload #7
    //   82: iconst_3
    //   83: if_icmpne -> 115
    //   86: aload #4
    //   88: ifnull -> 97
    //   91: aload #4
    //   93: iconst_0
    //   94: invokevirtual set : (I)V
    //   97: aload #5
    //   99: ifnull -> 53
    //   102: aload #5
    //   104: lload_0
    //   105: invokevirtual set : (J)V
    //   108: return
    //   109: iconst_0
    //   110: istore #6
    //   112: goto -> 63
    //   115: lload_0
    //   116: invokestatic make : (J)Lgnu/math/IntNum;
    //   119: lload_2
    //   120: invokestatic make : (J)Lgnu/math/IntNum;
    //   123: aload #4
    //   125: aload #5
    //   127: iload #7
    //   129: invokestatic divide : (Lgnu/math/IntNum;Lgnu/math/IntNum;Lgnu/math/IntNum;Lgnu/math/IntNum;I)V
    //   132: return
    //   133: lload_2
    //   134: lneg
    //   135: lstore_2
    //   136: lload_0
    //   137: lload_2
    //   138: ldiv
    //   139: lstore #11
    //   141: lload_0
    //   142: lload_2
    //   143: lrem
    //   144: lstore #13
    //   146: iload #6
    //   148: iload #8
    //   150: ixor
    //   151: istore #10
    //   153: iconst_0
    //   154: istore #9
    //   156: iload #9
    //   158: istore #8
    //   160: lload #13
    //   162: lconst_0
    //   163: lcmp
    //   164: ifeq -> 208
    //   167: iload #9
    //   169: istore #8
    //   171: iload #7
    //   173: tableswitch default -> 204, 1 -> 298, 2 -> 298, 3 -> 208, 4 -> 330
    //   204: iload #9
    //   206: istore #8
    //   208: aload #4
    //   210: ifnull -> 245
    //   213: lload #11
    //   215: lstore_0
    //   216: iload #8
    //   218: ifeq -> 226
    //   221: lload #11
    //   223: lconst_1
    //   224: ladd
    //   225: lstore_0
    //   226: lload_0
    //   227: lstore #11
    //   229: iload #10
    //   231: ifeq -> 238
    //   234: lload_0
    //   235: lneg
    //   236: lstore #11
    //   238: aload #4
    //   240: lload #11
    //   242: invokevirtual set : (J)V
    //   245: aload #5
    //   247: ifnull -> 53
    //   250: lload #13
    //   252: lstore_0
    //   253: iload #6
    //   255: istore #7
    //   257: iload #8
    //   259: ifeq -> 275
    //   262: lload_2
    //   263: lload #13
    //   265: lsub
    //   266: lstore_0
    //   267: iload #6
    //   269: ifne -> 356
    //   272: iconst_1
    //   273: istore #7
    //   275: lload_0
    //   276: lstore_2
    //   277: iload #7
    //   279: ifeq -> 285
    //   282: lload_0
    //   283: lneg
    //   284: lstore_2
    //   285: aload #5
    //   287: lload_2
    //   288: invokevirtual set : (J)V
    //   291: return
    //   292: iconst_0
    //   293: istore #8
    //   295: goto -> 136
    //   298: iload #7
    //   300: iconst_1
    //   301: if_icmpne -> 324
    //   304: iconst_1
    //   305: istore #7
    //   307: iload #9
    //   309: istore #8
    //   311: iload #10
    //   313: iload #7
    //   315: if_icmpne -> 208
    //   318: iconst_1
    //   319: istore #8
    //   321: goto -> 208
    //   324: iconst_0
    //   325: istore #7
    //   327: goto -> 307
    //   330: lload #13
    //   332: lload_2
    //   333: lconst_1
    //   334: lload #11
    //   336: land
    //   337: lsub
    //   338: iconst_1
    //   339: lshr
    //   340: lcmp
    //   341: ifle -> 350
    //   344: iconst_1
    //   345: istore #8
    //   347: goto -> 208
    //   350: iconst_0
    //   351: istore #8
    //   353: goto -> 347
    //   356: iconst_0
    //   357: istore #7
    //   359: goto -> 275
  }
  
  public static void divide(IntNum paramIntNum1, IntNum paramIntNum2, IntNum paramIntNum3, IntNum paramIntNum4, int paramInt) {
    // Byte code:
    //   0: aload_0
    //   1: getfield words : [I
    //   4: ifnull -> 15
    //   7: aload_0
    //   8: getfield ival : I
    //   11: iconst_2
    //   12: if_icmpgt -> 72
    //   15: aload_1
    //   16: getfield words : [I
    //   19: ifnull -> 30
    //   22: aload_1
    //   23: getfield ival : I
    //   26: iconst_2
    //   27: if_icmpgt -> 72
    //   30: aload_0
    //   31: invokevirtual longValue : ()J
    //   34: lstore #12
    //   36: aload_1
    //   37: invokevirtual longValue : ()J
    //   40: lstore #14
    //   42: lload #12
    //   44: ldc2_w -9223372036854775808
    //   47: lcmp
    //   48: ifeq -> 72
    //   51: lload #14
    //   53: ldc2_w -9223372036854775808
    //   56: lcmp
    //   57: ifeq -> 72
    //   60: lload #12
    //   62: lload #14
    //   64: aload_2
    //   65: aload_3
    //   66: iload #4
    //   68: invokestatic divide : (JJLgnu/math/IntNum;Lgnu/math/IntNum;I)V
    //   71: return
    //   72: aload_0
    //   73: invokevirtual isNegative : ()Z
    //   76: istore #16
    //   78: aload_1
    //   79: invokevirtual isNegative : ()Z
    //   82: istore #17
    //   84: iload #16
    //   86: iload #17
    //   88: ixor
    //   89: istore #11
    //   91: aload_1
    //   92: getfield words : [I
    //   95: ifnonnull -> 138
    //   98: iconst_1
    //   99: istore #7
    //   101: iload #7
    //   103: newarray int
    //   105: astore #20
    //   107: aload_1
    //   108: aload #20
    //   110: invokevirtual getAbsolute : ([I)V
    //   113: iload #7
    //   115: iconst_1
    //   116: if_icmple -> 147
    //   119: aload #20
    //   121: iload #7
    //   123: iconst_1
    //   124: isub
    //   125: iaload
    //   126: ifne -> 147
    //   129: iload #7
    //   131: iconst_1
    //   132: isub
    //   133: istore #7
    //   135: goto -> 113
    //   138: aload_1
    //   139: getfield ival : I
    //   142: istore #7
    //   144: goto -> 101
    //   147: aload_0
    //   148: getfield words : [I
    //   151: ifnonnull -> 196
    //   154: iconst_1
    //   155: istore #5
    //   157: iload #5
    //   159: iconst_2
    //   160: iadd
    //   161: newarray int
    //   163: astore #19
    //   165: aload_0
    //   166: aload #19
    //   168: invokevirtual getAbsolute : ([I)V
    //   171: iload #5
    //   173: iconst_1
    //   174: if_icmple -> 205
    //   177: aload #19
    //   179: iload #5
    //   181: iconst_1
    //   182: isub
    //   183: iaload
    //   184: ifne -> 205
    //   187: iload #5
    //   189: iconst_1
    //   190: isub
    //   191: istore #5
    //   193: goto -> 171
    //   196: aload_0
    //   197: getfield ival : I
    //   200: istore #5
    //   202: goto -> 157
    //   205: aload #19
    //   207: iload #5
    //   209: aload #20
    //   211: iload #7
    //   213: invokestatic cmp : ([II[II)I
    //   216: istore #6
    //   218: iload #6
    //   220: ifge -> 262
    //   223: aload #20
    //   225: astore #18
    //   227: aload #19
    //   229: astore_0
    //   230: iconst_1
    //   231: istore #6
    //   233: aload #18
    //   235: iconst_0
    //   236: iconst_0
    //   237: iastore
    //   238: iload #5
    //   240: iconst_1
    //   241: if_icmple -> 515
    //   244: aload_0
    //   245: iload #5
    //   247: iconst_1
    //   248: isub
    //   249: iaload
    //   250: ifne -> 515
    //   253: iload #5
    //   255: iconst_1
    //   256: isub
    //   257: istore #5
    //   259: goto -> 238
    //   262: iload #6
    //   264: ifne -> 293
    //   267: aload #19
    //   269: iconst_0
    //   270: iconst_1
    //   271: iastore
    //   272: iconst_1
    //   273: istore #6
    //   275: aload #20
    //   277: iconst_0
    //   278: iconst_0
    //   279: iastore
    //   280: iconst_1
    //   281: istore #5
    //   283: aload #19
    //   285: astore #18
    //   287: aload #20
    //   289: astore_0
    //   290: goto -> 238
    //   293: iload #7
    //   295: iconst_1
    //   296: if_icmpne -> 337
    //   299: iload #5
    //   301: istore #6
    //   303: iconst_1
    //   304: istore #7
    //   306: aload #20
    //   308: iconst_0
    //   309: aload #19
    //   311: aload #19
    //   313: iload #5
    //   315: aload #20
    //   317: iconst_0
    //   318: iaload
    //   319: invokestatic divmod_1 : ([I[III)I
    //   322: iastore
    //   323: iload #7
    //   325: istore #5
    //   327: aload #19
    //   329: astore #18
    //   331: aload #20
    //   333: astore_0
    //   334: goto -> 238
    //   337: aload #20
    //   339: iload #7
    //   341: iconst_1
    //   342: isub
    //   343: iaload
    //   344: invokestatic count_leading_zeros : (I)I
    //   347: istore #9
    //   349: iload #5
    //   351: istore #6
    //   353: iload #9
    //   355: ifeq -> 394
    //   358: aload #20
    //   360: iconst_0
    //   361: aload #20
    //   363: iload #7
    //   365: iload #9
    //   367: invokestatic lshift : ([II[III)I
    //   370: pop
    //   371: aload #19
    //   373: iload #5
    //   375: aload #19
    //   377: iconst_0
    //   378: aload #19
    //   380: iload #5
    //   382: iload #9
    //   384: invokestatic lshift : ([II[III)I
    //   387: iastore
    //   388: iload #5
    //   390: iconst_1
    //   391: iadd
    //   392: istore #6
    //   394: iload #6
    //   396: iload #7
    //   398: if_icmpne -> 936
    //   401: iload #6
    //   403: iconst_1
    //   404: iadd
    //   405: istore #5
    //   407: aload #19
    //   409: iload #6
    //   411: iconst_0
    //   412: iastore
    //   413: aload #19
    //   415: iload #5
    //   417: aload #20
    //   419: iload #7
    //   421: invokestatic divide : ([II[II)V
    //   424: iload #7
    //   426: istore #8
    //   428: aload #20
    //   430: aload #19
    //   432: iconst_0
    //   433: iload #8
    //   435: iload #9
    //   437: invokestatic rshift0 : ([I[IIII)V
    //   440: iload #5
    //   442: iconst_1
    //   443: iadd
    //   444: iload #7
    //   446: isub
    //   447: istore #10
    //   449: iload #10
    //   451: istore #6
    //   453: iload #8
    //   455: istore #5
    //   457: aload #19
    //   459: astore #18
    //   461: aload #20
    //   463: astore_0
    //   464: aload_2
    //   465: ifnull -> 238
    //   468: iconst_0
    //   469: istore #9
    //   471: iload #10
    //   473: istore #6
    //   475: iload #8
    //   477: istore #5
    //   479: aload #19
    //   481: astore #18
    //   483: aload #20
    //   485: astore_0
    //   486: iload #9
    //   488: iload #10
    //   490: if_icmpge -> 238
    //   493: aload #19
    //   495: iload #9
    //   497: aload #19
    //   499: iload #9
    //   501: iload #7
    //   503: iadd
    //   504: iaload
    //   505: iastore
    //   506: iload #9
    //   508: iconst_1
    //   509: iadd
    //   510: istore #9
    //   512: goto -> 471
    //   515: iload #5
    //   517: istore #8
    //   519: aload_0
    //   520: iload #5
    //   522: iconst_1
    //   523: isub
    //   524: iaload
    //   525: ifge -> 539
    //   528: aload_0
    //   529: iload #5
    //   531: iconst_0
    //   532: iastore
    //   533: iload #5
    //   535: iconst_1
    //   536: iadd
    //   537: istore #8
    //   539: iconst_0
    //   540: istore #9
    //   542: iload #8
    //   544: iconst_1
    //   545: if_icmpgt -> 558
    //   548: iload #9
    //   550: istore #5
    //   552: aload_0
    //   553: iconst_0
    //   554: iaload
    //   555: ifeq -> 616
    //   558: iload #4
    //   560: istore #7
    //   562: iload #4
    //   564: iconst_5
    //   565: if_icmpne -> 576
    //   568: iload #17
    //   570: ifeq -> 725
    //   573: iconst_2
    //   574: istore #7
    //   576: iload #9
    //   578: istore #5
    //   580: iload #7
    //   582: tableswitch default -> 612, 1 -> 731, 2 -> 731, 3 -> 616, 4 -> 763
    //   612: iload #9
    //   614: istore #5
    //   616: aload_2
    //   617: ifnull -> 668
    //   620: iload #6
    //   622: istore #4
    //   624: aload #18
    //   626: iload #6
    //   628: iconst_1
    //   629: isub
    //   630: iaload
    //   631: ifge -> 646
    //   634: aload #18
    //   636: iload #6
    //   638: iconst_0
    //   639: iastore
    //   640: iload #6
    //   642: iconst_1
    //   643: iadd
    //   644: istore #4
    //   646: aload_2
    //   647: aload #18
    //   649: iload #4
    //   651: invokevirtual set : ([II)V
    //   654: iload #11
    //   656: ifeq -> 869
    //   659: iload #5
    //   661: ifeq -> 862
    //   664: aload_2
    //   665: invokevirtual setInvert : ()V
    //   668: aload_3
    //   669: ifnull -> 71
    //   672: aload_3
    //   673: aload_0
    //   674: iload #8
    //   676: invokevirtual set : ([II)V
    //   679: iload #5
    //   681: ifeq -> 926
    //   684: aload_1
    //   685: getfield words : [I
    //   688: ifnonnull -> 895
    //   691: aload_3
    //   692: astore_2
    //   693: iload #17
    //   695: ifeq -> 882
    //   698: aload_0
    //   699: iconst_0
    //   700: iaload
    //   701: aload_1
    //   702: getfield ival : I
    //   705: iadd
    //   706: istore #4
    //   708: aload_2
    //   709: iload #4
    //   711: invokevirtual set : (I)V
    //   714: iload #16
    //   716: ifeq -> 920
    //   719: aload_3
    //   720: aload_2
    //   721: invokevirtual setNegative : (Lgnu/math/IntNum;)V
    //   724: return
    //   725: iconst_1
    //   726: istore #7
    //   728: goto -> 576
    //   731: iload #7
    //   733: iconst_1
    //   734: if_icmpne -> 757
    //   737: iconst_1
    //   738: istore #4
    //   740: iload #9
    //   742: istore #5
    //   744: iload #11
    //   746: iload #4
    //   748: if_icmpne -> 616
    //   751: iconst_1
    //   752: istore #5
    //   754: goto -> 616
    //   757: iconst_0
    //   758: istore #4
    //   760: goto -> 740
    //   763: aload_3
    //   764: ifnonnull -> 850
    //   767: new gnu/math/IntNum
    //   770: dup
    //   771: invokespecial <init> : ()V
    //   774: astore #19
    //   776: aload #19
    //   778: aload_0
    //   779: iload #8
    //   781: invokevirtual set : ([II)V
    //   784: aload #19
    //   786: iconst_1
    //   787: invokestatic shift : (Lgnu/math/IntNum;I)Lgnu/math/IntNum;
    //   790: astore #19
    //   792: iload #17
    //   794: ifeq -> 802
    //   797: aload #19
    //   799: invokevirtual setNegative : ()V
    //   802: aload #19
    //   804: aload_1
    //   805: invokestatic compare : (Lgnu/math/IntNum;Lgnu/math/IntNum;)I
    //   808: istore #5
    //   810: iload #5
    //   812: istore #4
    //   814: iload #17
    //   816: ifeq -> 824
    //   819: iload #5
    //   821: ineg
    //   822: istore #4
    //   824: iload #4
    //   826: iconst_1
    //   827: if_icmpeq -> 844
    //   830: iload #4
    //   832: ifne -> 856
    //   835: aload #18
    //   837: iconst_0
    //   838: iaload
    //   839: iconst_1
    //   840: iand
    //   841: ifeq -> 856
    //   844: iconst_1
    //   845: istore #5
    //   847: goto -> 616
    //   850: aload_3
    //   851: astore #19
    //   853: goto -> 776
    //   856: iconst_0
    //   857: istore #5
    //   859: goto -> 847
    //   862: aload_2
    //   863: invokevirtual setNegative : ()V
    //   866: goto -> 668
    //   869: iload #5
    //   871: ifeq -> 668
    //   874: aload_2
    //   875: iconst_1
    //   876: invokevirtual setAdd : (I)V
    //   879: goto -> 668
    //   882: aload_0
    //   883: iconst_0
    //   884: iaload
    //   885: aload_1
    //   886: getfield ival : I
    //   889: isub
    //   890: istore #4
    //   892: goto -> 708
    //   895: iload #17
    //   897: ifeq -> 914
    //   900: iconst_1
    //   901: istore #4
    //   903: aload_3
    //   904: aload_1
    //   905: iload #4
    //   907: invokestatic add : (Lgnu/math/IntNum;Lgnu/math/IntNum;I)Lgnu/math/IntNum;
    //   910: astore_2
    //   911: goto -> 714
    //   914: iconst_m1
    //   915: istore #4
    //   917: goto -> 903
    //   920: aload_3
    //   921: aload_2
    //   922: invokevirtual set : (Lgnu/math/IntNum;)V
    //   925: return
    //   926: iload #16
    //   928: ifeq -> 71
    //   931: aload_3
    //   932: invokevirtual setNegative : ()V
    //   935: return
    //   936: iload #6
    //   938: istore #5
    //   940: goto -> 413
  }
  
  public static boolean equals(IntNum paramIntNum1, IntNum paramIntNum2) {
    if (paramIntNum1.words == null && paramIntNum2.words == null)
      return !(paramIntNum1.ival != paramIntNum2.ival); 
    if (paramIntNum1.words == null || paramIntNum2.words == null || paramIntNum1.ival != paramIntNum2.ival)
      return false; 
    int i = paramIntNum1.ival;
    while (true) {
      int j = i - 1;
      if (j >= 0) {
        i = j;
        if (paramIntNum1.words[j] != paramIntNum2.words[j])
          return false; 
        continue;
      } 
      return true;
    } 
  }
  
  public static final int gcd(int paramInt1, int paramInt2) {
    int j = paramInt1;
    int i = paramInt2;
    if (paramInt2 > paramInt1) {
      i = paramInt1;
      j = paramInt2;
    } 
    while (true) {
      if (i == 0)
        return j; 
      if (i == 1)
        return i; 
      paramInt1 = j % i;
      j = i;
      i = paramInt1;
    } 
  }
  
  public static IntNum gcd(IntNum paramIntNum1, IntNum paramIntNum2) {
    int j = paramIntNum1.ival;
    int k = paramIntNum2.ival;
    int i = j;
    if (paramIntNum1.words == null) {
      if (j == 0)
        return abs(paramIntNum2); 
      if (paramIntNum2.words == null && j != Integer.MIN_VALUE && k != Integer.MIN_VALUE) {
        i = j;
        if (j < 0)
          i = -j; 
        j = k;
        if (k < 0)
          j = -k; 
        return make(gcd(i, j));
      } 
      i = 1;
    } 
    j = k;
    if (paramIntNum2.words == null) {
      if (k == 0)
        return abs(paramIntNum1); 
      j = 1;
    } 
    if (i <= j)
      i = j; 
    int[] arrayOfInt1 = new int[i];
    int[] arrayOfInt2 = new int[i];
    paramIntNum1.getAbsolute(arrayOfInt1);
    paramIntNum2.getAbsolute(arrayOfInt2);
    j = MPN.gcd(arrayOfInt1, arrayOfInt2, i);
    paramIntNum1 = new IntNum(0);
    i = j;
    if (arrayOfInt1[j - 1] < 0) {
      arrayOfInt1[j] = 0;
      i = j + 1;
    } 
    paramIntNum1.ival = i;
    paramIntNum1.words = arrayOfInt1;
    return paramIntNum1.canonicalize();
  }
  
  public static int intValue(Object paramObject) {
    paramObject = paramObject;
    if (((IntNum)paramObject).words != null)
      throw new ClassCastException("integer too large"); 
    return ((IntNum)paramObject).ival;
  }
  
  public static IntNum lcm(IntNum paramIntNum1, IntNum paramIntNum2) {
    if (paramIntNum1.isZero() || paramIntNum2.isZero())
      return zero(); 
    paramIntNum1 = abs(paramIntNum1);
    paramIntNum2 = abs(paramIntNum2);
    IntNum intNum = new IntNum();
    divide(times(paramIntNum1, paramIntNum2), gcd(paramIntNum1, paramIntNum2), intNum, (IntNum)null, 3);
    return intNum.canonicalize();
  }
  
  public static IntNum make(int paramInt) {
    return (paramInt >= -100 && paramInt <= 1024) ? smallFixNums[paramInt + 100] : new IntNum(paramInt);
  }
  
  public static IntNum make(long paramLong) {
    if (paramLong >= -100L && paramLong <= 1024L)
      return smallFixNums[(int)paramLong + 100]; 
    int i = (int)paramLong;
    if (i == paramLong)
      return new IntNum(i); 
    IntNum intNum = alloc(2);
    intNum.ival = 2;
    intNum.words[0] = i;
    intNum.words[1] = (int)(paramLong >> 32L);
    return intNum;
  }
  
  public static IntNum make(int[] paramArrayOfint) {
    return make(paramArrayOfint, paramArrayOfint.length);
  }
  
  public static IntNum make(int[] paramArrayOfint, int paramInt) {
    if (paramArrayOfint == null)
      return make(paramInt); 
    paramInt = wordsNeeded(paramArrayOfint, paramInt);
    if (paramInt <= 1)
      return (paramInt == 0) ? zero() : make(paramArrayOfint[0]); 
    IntNum intNum = new IntNum();
    intNum.words = paramArrayOfint;
    intNum.ival = paramInt;
    return intNum;
  }
  
  public static IntNum makeU(long paramLong) {
    if (paramLong >= 0L)
      return make(paramLong); 
    IntNum intNum = alloc(3);
    intNum.ival = 3;
    intNum.words[0] = (int)paramLong;
    intNum.words[1] = (int)(paramLong >> 32L);
    intNum.words[2] = 0;
    return intNum;
  }
  
  public static IntNum minusOne() {
    return smallFixNums[99];
  }
  
  public static IntNum modulo(IntNum paramIntNum1, IntNum paramIntNum2) {
    return remainder(paramIntNum1, paramIntNum2, 1);
  }
  
  public static IntNum neg(IntNum paramIntNum) {
    if (paramIntNum.words == null && paramIntNum.ival != Integer.MIN_VALUE)
      return make(-paramIntNum.ival); 
    IntNum intNum = new IntNum(0);
    intNum.setNegative(paramIntNum);
    return intNum.canonicalize();
  }
  
  public static boolean negate(int[] paramArrayOfint1, int[] paramArrayOfint2, int paramInt) {
    boolean bool;
    long l = 1L;
    if (paramArrayOfint2[paramInt - 1] < 0) {
      bool = true;
    } else {
      bool = false;
    } 
    int i;
    for (i = 0; i < paramInt; i++) {
      l += (paramArrayOfint2[i] ^ 0xFFFFFFFF) & 0xFFFFFFFFL;
      paramArrayOfint1[i] = (int)l;
      l >>= 32L;
    } 
    return (bool && paramArrayOfint1[paramInt - 1] < 0);
  }
  
  public static final IntNum one() {
    return smallFixNums[101];
  }
  
  public static IntNum power(IntNum paramIntNum, int paramInt) {
    int[] arrayOfInt;
    if (paramInt <= 0) {
      if (paramInt == 0)
        return one(); 
      throw new Error("negative exponent");
    } 
    IntNum intNum = paramIntNum;
    if (!paramIntNum.isZero()) {
      int i;
      boolean bool;
      if (paramIntNum.words == null) {
        i = 1;
      } else {
        i = paramIntNum.ival;
      } 
      int j = (paramIntNum.intLength() * paramInt >> 5) + i * 2;
      if (paramIntNum.isNegative() && (paramInt & 0x1) != 0) {
        bool = true;
      } else {
        bool = false;
      } 
      arrayOfInt = new int[j];
      int[] arrayOfInt2 = new int[j];
      int[] arrayOfInt3 = new int[j];
      paramIntNum.getAbsolute(arrayOfInt);
      int m = 1;
      arrayOfInt2[0] = 1;
      j = paramInt;
      int[] arrayOfInt1 = arrayOfInt3;
      int k = i;
      label42: while (true) {
        paramInt = m;
        int[] arrayOfInt4 = arrayOfInt2;
        arrayOfInt3 = arrayOfInt1;
        if ((j & 0x1) != 0) {
          MPN.mul(arrayOfInt1, arrayOfInt, k, arrayOfInt2, m);
          i = m + k;
          while (true) {
            paramInt = i;
            arrayOfInt4 = arrayOfInt1;
            arrayOfInt3 = arrayOfInt2;
            if (arrayOfInt1[i - 1] == 0) {
              i--;
              continue;
            } 
            break;
          } 
        } 
        int n = j >> 1;
        if (n == 0) {
          i = paramInt;
          if (arrayOfInt4[paramInt - 1] < 0)
            i = paramInt + 1; 
          if (bool)
            negate(arrayOfInt4, arrayOfInt4, i); 
          return make(arrayOfInt4, i);
        } 
        MPN.mul(arrayOfInt3, arrayOfInt, k, arrayOfInt, k);
        int[] arrayOfInt5 = arrayOfInt;
        i = k * 2;
        while (true) {
          k = i;
          arrayOfInt = arrayOfInt3;
          m = paramInt;
          arrayOfInt2 = arrayOfInt4;
          arrayOfInt1 = arrayOfInt5;
          j = n;
          if (arrayOfInt3[i - 1] == 0) {
            i--;
            continue;
          } 
          continue label42;
        } 
        break;
      } 
    } 
    return (IntNum)arrayOfInt;
  }
  
  public static IntNum quotient(IntNum paramIntNum1, IntNum paramIntNum2) {
    return quotient(paramIntNum1, paramIntNum2, 3);
  }
  
  public static IntNum quotient(IntNum paramIntNum1, IntNum paramIntNum2, int paramInt) {
    IntNum intNum = new IntNum();
    divide(paramIntNum1, paramIntNum2, intNum, (IntNum)null, paramInt);
    return intNum.canonicalize();
  }
  
  public static IntNum remainder(IntNum paramIntNum1, IntNum paramIntNum2) {
    return remainder(paramIntNum1, paramIntNum2, 3);
  }
  
  public static IntNum remainder(IntNum paramIntNum1, IntNum paramIntNum2, int paramInt) {
    if (paramIntNum2.isZero())
      return paramIntNum1; 
    IntNum intNum = new IntNum();
    divide(paramIntNum1, paramIntNum2, (IntNum)null, intNum, paramInt);
    return intNum.canonicalize();
  }
  
  public static int shift(int paramInt1, int paramInt2) {
    if (paramInt2 < 32) {
      if (paramInt2 >= 0)
        return paramInt1 << paramInt2; 
      paramInt2 = -paramInt2;
      return (paramInt2 >= 32) ? ((paramInt1 < 0) ? -1 : 0) : (paramInt1 >> paramInt2);
    } 
    return 0;
  }
  
  public static long shift(long paramLong, int paramInt) {
    if (paramInt < 32) {
      if (paramInt >= 0)
        return paramLong << paramInt; 
      paramInt = -paramInt;
      return (paramInt >= 32) ? ((paramLong < 0L) ? -1L : 0L) : (paramLong >> paramInt);
    } 
    return 0L;
  }
  
  public static IntNum shift(IntNum paramIntNum, int paramInt) {
    boolean bool = false;
    if (paramIntNum.words == null) {
      if (paramInt <= 0) {
        if (paramInt > -32) {
          paramInt = paramIntNum.ival >> -paramInt;
        } else {
          paramInt = bool;
          if (paramIntNum.ival < 0)
            paramInt = -1; 
        } 
        return make(paramInt);
      } 
      if (paramInt < 32)
        return make(paramIntNum.ival << paramInt); 
    } 
    IntNum intNum = paramIntNum;
    if (paramInt != 0) {
      intNum = new IntNum(0);
      intNum.setShift(paramIntNum, paramInt);
      return intNum.canonicalize();
    } 
    return intNum;
  }
  
  public static IntNum sub(IntNum paramIntNum1, IntNum paramIntNum2) {
    return add(paramIntNum1, paramIntNum2, -1);
  }
  
  public static final IntNum ten() {
    return smallFixNums[110];
  }
  
  public static final IntNum times(int paramInt1, int paramInt2) {
    return make(paramInt1 * paramInt2);
  }
  
  public static final IntNum times(IntNum paramIntNum, int paramInt) {
    int[] arrayOfInt;
    if (paramInt == 0)
      return zero(); 
    IntNum intNum = paramIntNum;
    if (paramInt != 1) {
      int[] arrayOfInt1;
      boolean bool1;
      arrayOfInt = paramIntNum.words;
      int j = paramIntNum.ival;
      if (arrayOfInt == null)
        return make(j * paramInt); 
      IntNum intNum1 = alloc(j + 1);
      if (arrayOfInt[j - 1] < 0) {
        bool1 = true;
        negate(intNum1.words, arrayOfInt, j);
        arrayOfInt1 = intNum1.words;
      } else {
        bool1 = false;
        arrayOfInt1 = arrayOfInt;
      } 
      boolean bool2 = bool1;
      int i = paramInt;
      if (paramInt < 0) {
        if (!bool1) {
          bool1 = true;
        } else {
          bool1 = false;
        } 
        i = -paramInt;
        bool2 = bool1;
      } 
      intNum1.words[j] = MPN.mul_1(intNum1.words, arrayOfInt1, j, i);
      intNum1.ival = j + 1;
      if (bool2)
        intNum1.setNegative(); 
      return intNum1.canonicalize();
    } 
    return (IntNum)arrayOfInt;
  }
  
  public static final IntNum times(IntNum paramIntNum1, IntNum paramIntNum2) {
    int[] arrayOfInt1;
    int[] arrayOfInt2;
    boolean bool;
    if (paramIntNum2.words == null)
      return times(paramIntNum1, paramIntNum2.ival); 
    if (paramIntNum1.words == null)
      return times(paramIntNum2, paramIntNum1.ival); 
    int i = paramIntNum1.ival;
    int j = paramIntNum2.ival;
    if (paramIntNum1.isNegative()) {
      bool = true;
      int[] arrayOfInt = new int[i];
      negate(arrayOfInt, paramIntNum1.words, i);
      arrayOfInt1 = arrayOfInt;
    } else {
      bool = false;
      arrayOfInt1 = ((IntNum)arrayOfInt1).words;
    } 
    if (paramIntNum2.isNegative()) {
      if (!bool) {
        bool = true;
      } else {
        bool = false;
      } 
      int[] arrayOfInt = new int[j];
      negate(arrayOfInt, paramIntNum2.words, j);
      arrayOfInt2 = arrayOfInt;
    } else {
      arrayOfInt2 = ((IntNum)arrayOfInt2).words;
    } 
    int m = i;
    int[] arrayOfInt4 = arrayOfInt1;
    int k = j;
    int[] arrayOfInt3 = arrayOfInt2;
    if (i < j) {
      arrayOfInt3 = arrayOfInt1;
      k = i;
      arrayOfInt4 = arrayOfInt2;
      m = j;
    } 
    IntNum intNum = alloc(m + k);
    MPN.mul(intNum.words, arrayOfInt4, m, arrayOfInt3, k);
    intNum.ival = m + k;
    if (bool)
      intNum.setNegative(); 
    return intNum.canonicalize();
  }
  
  public static IntNum valueOf(String paramString) throws NumberFormatException {
    return valueOf(paramString, 10);
  }
  
  public static IntNum valueOf(String paramString, int paramInt) throws NumberFormatException {
    int k = paramString.length();
    if (k + paramInt <= 28) {
      String str = paramString;
      if (k > 1) {
        str = paramString;
        if (paramString.charAt(0) == '+') {
          str = paramString;
          if (Character.digit(paramString.charAt(1), paramInt) >= 0)
            str = paramString.substring(1); 
        } 
      } 
      return make(Long.parseLong(str, paramInt));
    } 
    byte[] arrayOfByte = new byte[k];
    boolean bool = false;
    int j = 0;
    int i = 0;
    while (j < k) {
      char c = paramString.charAt(j);
      if (c == '-' && j == 0) {
        bool = true;
      } else if ((c != '+' || j != 0) && c != '_' && (i || (c != ' ' && c != '\t'))) {
        int n = Character.digit(c, paramInt);
        if (n < 0)
          throw new NumberFormatException("For input string: \"" + paramString + '"'); 
        int m = i + 1;
        arrayOfByte[i] = (byte)n;
        i = m;
      } 
      j++;
    } 
    return valueOf(arrayOfByte, i, bool, paramInt);
  }
  
  public static IntNum valueOf(byte[] paramArrayOfbyte, int paramInt1, boolean paramBoolean, int paramInt2) {
    int[] arrayOfInt = new int[paramInt1 / MPN.chars_per_word(paramInt2) + 1];
    paramInt2 = MPN.set_str(arrayOfInt, paramArrayOfbyte, paramInt1, paramInt2);
    if (paramInt2 == 0)
      return zero(); 
    paramInt1 = paramInt2;
    if (arrayOfInt[paramInt2 - 1] < 0) {
      arrayOfInt[paramInt2] = 0;
      paramInt1 = paramInt2 + 1;
    } 
    if (paramBoolean)
      negate(arrayOfInt, arrayOfInt, paramInt1); 
    return make(arrayOfInt, paramInt1);
  }
  
  public static IntNum valueOf(char[] paramArrayOfchar, int paramInt1, int paramInt2, int paramInt3, boolean paramBoolean) {
    byte[] arrayOfByte = new byte[paramInt2];
    int j = 0;
    int i = 0;
    while (true) {
      if (j < paramInt2) {
        char c = paramArrayOfchar[paramInt1 + j];
        if (c == '-') {
          paramBoolean = true;
        } else if (c != '_' && (i || (c != ' ' && c != '\t'))) {
          int m = Character.digit(c, paramInt3);
          if (m < 0)
            return valueOf(arrayOfByte, i, paramBoolean, paramInt3); 
          int k = i + 1;
          arrayOfByte[i] = (byte)m;
          i = k;
        } 
        j++;
        continue;
      } 
      return valueOf(arrayOfByte, i, paramBoolean, paramInt3);
    } 
  }
  
  public static int wordsNeeded(int[] paramArrayOfint, int paramInt) {
    int j;
    int i = paramInt;
    paramInt = i;
    if (i > 0) {
      paramInt = i - 1;
      int k = paramArrayOfint[paramInt];
      i = paramInt;
      j = k;
      if (k == -1) {
        i = paramInt;
        while (true) {
          paramInt = i;
          if (i > 0) {
            j = paramArrayOfint[i - 1];
            paramInt = i;
            if (j < 0) {
              paramInt = i - 1;
              i = paramInt;
              if (j != -1)
                break; 
              continue;
            } 
          } 
          break;
        } 
        return paramInt + 1;
      } 
    } else {
      return paramInt + 1;
    } 
    while (true) {
      paramInt = i;
      if (j == 0) {
        paramInt = i;
        if (i > 0) {
          j = paramArrayOfint[i - 1];
          paramInt = i;
          if (j >= 0) {
            i--;
            continue;
          } 
        } 
      } 
      return paramInt + 1;
    } 
  }
  
  public static final IntNum zero() {
    return smallFixNums[100];
  }
  
  public Numeric add(Object paramObject, int paramInt) {
    if (paramObject instanceof IntNum)
      return add(this, (IntNum)paramObject, paramInt); 
    if (!(paramObject instanceof Numeric))
      throw new IllegalArgumentException(); 
    return ((Numeric)paramObject).addReversed(this, paramInt);
  }
  
  public BigDecimal asBigDecimal() {
    return (this.words == null) ? new BigDecimal(this.ival) : ((this.ival <= 2) ? BigDecimal.valueOf(longValue()) : new BigDecimal(toString()));
  }
  
  public BigInteger asBigInteger() {
    return (this.words == null || this.ival <= 2) ? BigInteger.valueOf(longValue()) : new BigInteger(toString());
  }
  
  public IntNum canonicalize() {
    if (this.words != null) {
      int i = wordsNeeded(this.words, this.ival);
      this.ival = i;
      if (i <= 1) {
        if (this.ival == 1)
          this.ival = this.words[0]; 
        this.words = null;
      } 
    } 
    IntNum intNum = this;
    if (this.words == null) {
      intNum = this;
      if (this.ival >= -100) {
        intNum = this;
        if (this.ival <= 1024)
          intNum = smallFixNums[this.ival + 100]; 
      } 
    } 
    return intNum;
  }
  
  boolean checkBits(int paramInt) {
    boolean bool = true;
    if (paramInt > 0) {
      if (this.words == null)
        return (paramInt > 31 || (this.ival & (1 << paramInt) - 1) != 0); 
      int i;
      for (i = 0; i < paramInt >> 5; i++) {
        if (this.words[i] != 0)
          return true; 
      } 
      if ((paramInt & 0x1F) == 0 || (this.words[i] & (1 << (paramInt & 0x1F)) - 1) == 0)
        bool = false; 
      return bool;
    } 
    return false;
  }
  
  public int compare(Object paramObject) {
    return (paramObject instanceof IntNum) ? compare(this, (IntNum)paramObject) : ((RealNum)paramObject).compareReversed(this);
  }
  
  public final IntNum denominator() {
    return one();
  }
  
  public Numeric div(Object paramObject) {
    if (paramObject instanceof RatNum) {
      paramObject = paramObject;
      return RatNum.make(times(this, paramObject.denominator()), paramObject.numerator());
    } 
    if (!(paramObject instanceof Numeric))
      throw new IllegalArgumentException(); 
    return ((Numeric)paramObject).divReversed(this);
  }
  
  public double doubleValue() {
    return (this.words == null) ? this.ival : ((this.ival <= 2) ? longValue() : (isNegative() ? neg(this).roundToDouble(0, true, false) : roundToDouble(0, false, false)));
  }
  
  public boolean equals(Object paramObject) {
    return (paramObject == null || !(paramObject instanceof IntNum)) ? false : equals(this, (IntNum)paramObject);
  }
  
  public void format(int paramInt, StringBuffer paramStringBuffer) {
    if (paramInt == 10) {
      if (this.words == null) {
        paramStringBuffer.append(this.ival);
        return;
      } 
      if (this.ival <= 2) {
        paramStringBuffer.append(longValue());
        return;
      } 
    } 
    paramStringBuffer.append(toString(paramInt));
  }
  
  public void format(int paramInt, StringBuilder paramStringBuilder) {
    int i;
    int[] arrayOfInt;
    if (this.words == null) {
      if (paramInt == 10) {
        paramStringBuilder.append(this.ival);
        return;
      } 
      paramStringBuilder.append(Integer.toString(this.ival, paramInt));
      return;
    } 
    if (this.ival <= 2) {
      long l = longValue();
      if (paramInt == 10) {
        paramStringBuilder.append(l);
        return;
      } 
      paramStringBuilder.append(Long.toString(l, paramInt));
      return;
    } 
    boolean bool = isNegative();
    if (bool || paramInt != 16) {
      arrayOfInt = new int[this.ival];
      getAbsolute(arrayOfInt);
    } else {
      arrayOfInt = this.words;
    } 
    int j = this.ival;
    if (paramInt == 16) {
      if (bool)
        paramStringBuilder.append('-'); 
      int i1 = paramStringBuilder.length();
      paramInt = j;
      label70: while (true) {
        j = paramInt - 1;
        if (j >= 0) {
          Object object;
          int i2 = arrayOfInt[j];
          paramInt = 8;
          while (true) {
            int i3 = object - 1;
            i = j;
            if (i3 >= 0) {
              int i4 = i2 >> i3 * 4 & 0xF;
              if (i4 <= 0) {
                i = i3;
                if (paramStringBuilder.length() > i1)
                  continue; 
                continue;
              } 
              continue;
            } 
            continue label70;
            paramStringBuilder.append(Character.forDigit(SYNTHETIC_LOCAL_VARIABLE_8, 16));
            object = SYNTHETIC_LOCAL_VARIABLE_5;
          } 
          break;
        } 
        return;
      } 
    } 
    int k = MPN.chars_per_word(i);
    int m = i;
    int n = k;
    while (true) {
      if (--n > 0) {
        m *= i;
        continue;
      } 
      int i1 = paramStringBuilder.length();
      label73: while (true) {
        int i3 = MPN.divmod_1(arrayOfInt, arrayOfInt, j, m);
        for (n = j; n > 0 && arrayOfInt[n - 1] == 0; n--);
        int i2 = k;
        j = i3;
        while (true) {
          i3 = i2 - 1;
          if (i3 < 0 || (n == 0 && j == 0)) {
            j = n;
            if (n == 0) {
              if (bool)
                paramStringBuilder.append('-'); 
              i = paramStringBuilder.length() - 1;
              j = i1;
              while (true) {
                if (j < i) {
                  char c = paramStringBuilder.charAt(j);
                  paramStringBuilder.setCharAt(j, paramStringBuilder.charAt(i));
                  paramStringBuilder.setCharAt(i, c);
                  j++;
                  i--;
                  continue;
                } 
                return;
              } 
              break;
            } 
            continue label73;
          } 
          if (j < 0) {
            i2 = (int)((j & 0xFFFFFFFFFFFFFFFFL) % i);
            j /= i;
          } else {
            i2 = j % i;
            j /= i;
          } 
          paramStringBuilder.append(Character.forDigit(i2, i));
          i2 = i3;
        } 
        break;
      } 
      break;
    } 
  }
  
  public void getAbsolute(int[] paramArrayOfint) {
    // Byte code:
    //   0: aload_0
    //   1: getfield words : [I
    //   4: ifnonnull -> 50
    //   7: iconst_1
    //   8: istore_2
    //   9: aload_1
    //   10: iconst_0
    //   11: aload_0
    //   12: getfield ival : I
    //   15: iastore
    //   16: aload_1
    //   17: iload_2
    //   18: iconst_1
    //   19: isub
    //   20: iaload
    //   21: ifge -> 31
    //   24: aload_1
    //   25: aload_1
    //   26: iload_2
    //   27: invokestatic negate : ([I[II)Z
    //   30: pop
    //   31: aload_1
    //   32: arraylength
    //   33: istore_3
    //   34: iload_3
    //   35: iconst_1
    //   36: isub
    //   37: istore_3
    //   38: iload_3
    //   39: iload_2
    //   40: if_icmple -> 86
    //   43: aload_1
    //   44: iload_3
    //   45: iconst_0
    //   46: iastore
    //   47: goto -> 34
    //   50: aload_0
    //   51: getfield ival : I
    //   54: istore_3
    //   55: iload_3
    //   56: istore_2
    //   57: iload_2
    //   58: iconst_1
    //   59: isub
    //   60: istore #4
    //   62: iload_3
    //   63: istore_2
    //   64: iload #4
    //   66: iflt -> 16
    //   69: aload_1
    //   70: iload #4
    //   72: aload_0
    //   73: getfield words : [I
    //   76: iload #4
    //   78: iaload
    //   79: iastore
    //   80: iload #4
    //   82: istore_2
    //   83: goto -> 57
    //   86: return
  }
  
  public int hashCode() {
    return (this.words == null) ? this.ival : (this.words[0] + this.words[this.ival - 1]);
  }
  
  public boolean inIntRange() {
    return inRange(-2147483648L, 2147483647L);
  }
  
  public boolean inLongRange() {
    return inRange(Long.MIN_VALUE, Long.MAX_VALUE);
  }
  
  public boolean inRange(long paramLong1, long paramLong2) {
    return (compare(this, paramLong1) >= 0 && compare(this, paramLong2) <= 0);
  }
  
  public int intLength() {
    return (this.words == null) ? MPN.intLength(this.ival) : MPN.intLength(this.words, this.ival);
  }
  
  public int intValue() {
    return (this.words == null) ? this.ival : this.words[0];
  }
  
  public final boolean isMinusOne() {
    return (this.words == null && this.ival == -1);
  }
  
  public final boolean isNegative() {
    int i;
    if (this.words == null) {
      i = this.ival;
    } else {
      i = this.words[this.ival - 1];
    } 
    return (i < 0);
  }
  
  public final boolean isOdd() {
    int i;
    boolean bool = false;
    if (this.words == null) {
      i = this.ival;
    } else {
      i = this.words[0];
    } 
    if ((i & 0x1) != 0)
      bool = true; 
    return bool;
  }
  
  public final boolean isOne() {
    return (this.words == null && this.ival == 1);
  }
  
  public final boolean isZero() {
    return (this.words == null && this.ival == 0);
  }
  
  public long longValue() {
    return (this.words == null) ? this.ival : ((this.ival == 1) ? this.words[0] : ((this.words[1] << 32L) + (this.words[0] & 0xFFFFFFFFL)));
  }
  
  public Numeric mul(Object paramObject) {
    if (paramObject instanceof IntNum)
      return times(this, (IntNum)paramObject); 
    if (!(paramObject instanceof Numeric))
      throw new IllegalArgumentException(); 
    return ((Numeric)paramObject).mulReversed(this);
  }
  
  public Numeric neg() {
    return neg(this);
  }
  
  public final IntNum numerator() {
    return this;
  }
  
  public Numeric power(IntNum paramIntNum) {
    return !isOne() ? (isMinusOne() ? (!paramIntNum.isOdd() ? one() : this) : ((paramIntNum.words == null && paramIntNum.ival >= 0) ? power(this, paramIntNum.ival) : (isZero() ? (paramIntNum.isNegative() ? RatNum.infinity(-1) : this) : super.power(paramIntNum)))) : this;
  }
  
  public void readExternal(ObjectInput paramObjectInput) throws IOException, ClassNotFoundException {
    int j = paramObjectInput.readInt();
    int i = j;
    if (j <= -1073741824) {
      i = j & Integer.MAX_VALUE;
      if (i == 1) {
        i = paramObjectInput.readInt();
      } else {
        int[] arrayOfInt = new int[i];
        j = i;
        while (true) {
          if (--j >= 0) {
            arrayOfInt[j] = paramObjectInput.readInt();
            continue;
          } 
          this.words = arrayOfInt;
          // Byte code: goto -> 34
        } 
      } 
    } 
    this.ival = i;
  }
  
  public Object readResolve() throws ObjectStreamException {
    return canonicalize();
  }
  
  public void realloc(int paramInt) {
    if (paramInt == 0) {
      if (this.words != null) {
        if (this.ival > 0)
          this.ival = this.words[0]; 
        this.words = null;
      } 
      return;
    } 
    if (this.words == null || this.words.length < paramInt || this.words.length > paramInt + 2) {
      int[] arrayOfInt = new int[paramInt];
      if (this.words == null) {
        arrayOfInt[0] = this.ival;
        this.ival = 1;
      } else {
        if (paramInt < this.ival)
          this.ival = paramInt; 
        System.arraycopy(this.words, 0, arrayOfInt, 0, this.ival);
      } 
      this.words = arrayOfInt;
      return;
    } 
  }
  
  public double roundToDouble(int paramInt, boolean paramBoolean1, boolean paramBoolean2) {
    // Byte code:
    //   0: aload_0
    //   1: invokevirtual intLength : ()I
    //   4: istore #6
    //   6: iload_1
    //   7: iload #6
    //   9: iconst_1
    //   10: isub
    //   11: iadd
    //   12: istore #5
    //   14: iload #5
    //   16: sipush #-1075
    //   19: if_icmpge -> 30
    //   22: iload_2
    //   23: ifeq -> 28
    //   26: dconst_0
    //   27: dreturn
    //   28: dconst_0
    //   29: dreturn
    //   30: iload #5
    //   32: sipush #1023
    //   35: if_icmple -> 50
    //   38: iload_2
    //   39: ifeq -> 46
    //   42: ldc2_w -Infinity
    //   45: dreturn
    //   46: ldc2_w Infinity
    //   49: dreturn
    //   50: iload #5
    //   52: sipush #-1022
    //   55: if_icmplt -> 136
    //   58: bipush #53
    //   60: istore #4
    //   62: iload #6
    //   64: iload #4
    //   66: iconst_1
    //   67: iadd
    //   68: isub
    //   69: istore #7
    //   71: iload #7
    //   73: ifle -> 168
    //   76: aload_0
    //   77: getfield words : [I
    //   80: ifnonnull -> 150
    //   83: aload_0
    //   84: getfield ival : I
    //   87: iload #7
    //   89: ishr
    //   90: i2l
    //   91: lstore #8
    //   93: iload #5
    //   95: sipush #1023
    //   98: if_icmpne -> 197
    //   101: lload #8
    //   103: iconst_1
    //   104: lshr
    //   105: ldc2_w 9007199254740991
    //   108: lcmp
    //   109: ifne -> 197
    //   112: iload_3
    //   113: ifne -> 128
    //   116: aload_0
    //   117: iload #6
    //   119: iload #4
    //   121: isub
    //   122: invokevirtual checkBits : (I)Z
    //   125: ifeq -> 185
    //   128: iload_2
    //   129: ifeq -> 181
    //   132: ldc2_w -Infinity
    //   135: dreturn
    //   136: iload #5
    //   138: bipush #53
    //   140: iadd
    //   141: sipush #1022
    //   144: iadd
    //   145: istore #4
    //   147: goto -> 62
    //   150: aload_0
    //   151: getfield words : [I
    //   154: aload_0
    //   155: getfield ival : I
    //   158: iload #7
    //   160: invokestatic rshift_long : ([III)J
    //   163: lstore #8
    //   165: goto -> 93
    //   168: aload_0
    //   169: invokevirtual longValue : ()J
    //   172: iload #7
    //   174: ineg
    //   175: lshl
    //   176: lstore #8
    //   178: goto -> 93
    //   181: ldc2_w Infinity
    //   184: dreturn
    //   185: iload_2
    //   186: ifeq -> 193
    //   189: ldc2_w -1.7976931348623157E308
    //   192: dreturn
    //   193: ldc2_w 1.7976931348623157E308
    //   196: dreturn
    //   197: lload #8
    //   199: lstore #10
    //   201: iload #5
    //   203: istore_1
    //   204: lconst_1
    //   205: lload #8
    //   207: land
    //   208: lconst_1
    //   209: lcmp
    //   210: ifne -> 276
    //   213: ldc2_w 2
    //   216: lload #8
    //   218: land
    //   219: ldc2_w 2
    //   222: lcmp
    //   223: ifeq -> 246
    //   226: iload_3
    //   227: ifne -> 246
    //   230: lload #8
    //   232: lstore #10
    //   234: iload #5
    //   236: istore_1
    //   237: aload_0
    //   238: iload #7
    //   240: invokevirtual checkBits : (I)Z
    //   243: ifeq -> 276
    //   246: lload #8
    //   248: ldc2_w 2
    //   251: ladd
    //   252: lstore #8
    //   254: ldc2_w 18014398509481984
    //   257: lload #8
    //   259: land
    //   260: lconst_0
    //   261: lcmp
    //   262: ifeq -> 316
    //   265: iload #5
    //   267: iconst_1
    //   268: iadd
    //   269: istore_1
    //   270: lload #8
    //   272: iconst_1
    //   273: lshr
    //   274: lstore #10
    //   276: iload_2
    //   277: ifeq -> 360
    //   280: ldc2_w -9223372036854775808
    //   283: lstore #8
    //   285: iload_1
    //   286: sipush #1023
    //   289: iadd
    //   290: istore_1
    //   291: iload_1
    //   292: ifgt -> 366
    //   295: lconst_0
    //   296: lstore #12
    //   298: lload #8
    //   300: lload #12
    //   302: lor
    //   303: lload #10
    //   305: iconst_1
    //   306: lshr
    //   307: ldc2_w -4503599627370497
    //   310: land
    //   311: lor
    //   312: invokestatic longBitsToDouble : (J)D
    //   315: dreturn
    //   316: lload #8
    //   318: lstore #10
    //   320: iload #5
    //   322: istore_1
    //   323: iload #4
    //   325: bipush #52
    //   327: if_icmpne -> 276
    //   330: lload #8
    //   332: lstore #10
    //   334: iload #5
    //   336: istore_1
    //   337: ldc2_w 9007199254740992
    //   340: lload #8
    //   342: land
    //   343: lconst_0
    //   344: lcmp
    //   345: ifeq -> 276
    //   348: iload #5
    //   350: iconst_1
    //   351: iadd
    //   352: istore_1
    //   353: lload #8
    //   355: lstore #10
    //   357: goto -> 276
    //   360: lconst_0
    //   361: lstore #8
    //   363: goto -> 285
    //   366: iload_1
    //   367: i2l
    //   368: bipush #52
    //   370: lshl
    //   371: lstore #12
    //   373: goto -> 298
  }
  
  public final void set(int paramInt) {
    this.words = null;
    this.ival = paramInt;
  }
  
  public final void set(long paramLong) {
    int i = (int)paramLong;
    if (i == paramLong) {
      this.ival = i;
      this.words = null;
      return;
    } 
    realloc(2);
    this.words[0] = i;
    this.words[1] = (int)(paramLong >> 32L);
    this.ival = 2;
  }
  
  public final void set(IntNum paramIntNum) {
    if (paramIntNum.words == null) {
      set(paramIntNum.ival);
      return;
    } 
    if (this != paramIntNum) {
      realloc(paramIntNum.ival);
      System.arraycopy(paramIntNum.words, 0, this.words, 0, paramIntNum.ival);
      this.ival = paramIntNum.ival;
      return;
    } 
  }
  
  public final void set(int[] paramArrayOfint, int paramInt) {
    this.ival = paramInt;
    this.words = paramArrayOfint;
  }
  
  public final void setAdd(int paramInt) {
    setAdd(this, paramInt);
  }
  
  public void setAdd(IntNum paramIntNum, int paramInt) {
    if (paramIntNum.words == null) {
      set(paramIntNum.ival + paramInt);
      return;
    } 
    int i = paramIntNum.ival;
    realloc(i + 1);
    long l1 = paramInt;
    for (paramInt = 0; paramInt < i; paramInt++) {
      l1 += paramIntNum.words[paramInt] & 0xFFFFFFFFL;
      this.words[paramInt] = (int)l1;
      l1 >>= 32L;
    } 
    long l2 = l1;
    if (paramIntNum.words[i - 1] < 0)
      l2 = l1 - 1L; 
    this.words[i] = (int)l2;
    this.ival = wordsNeeded(this.words, i + 1);
  }
  
  void setInvert() {
    if (this.words == null) {
      this.ival ^= 0xFFFFFFFF;
      return;
    } 
    int i = this.ival;
    while (true) {
      if (--i >= 0) {
        this.words[i] = this.words[i] ^ 0xFFFFFFFF;
        continue;
      } 
      return;
    } 
  }
  
  public final void setNegative() {
    setNegative(this);
  }
  
  public void setNegative(IntNum paramIntNum) {
    int j = paramIntNum.ival;
    if (paramIntNum.words == null) {
      if (j == Integer.MIN_VALUE) {
        set(-(j));
        return;
      } 
      set(-j);
      return;
    } 
    realloc(j + 1);
    int i = j;
    if (negate(this.words, paramIntNum.words, j)) {
      this.words[j] = 0;
      i = j + 1;
    } 
    this.ival = i;
  }
  
  void setShift(IntNum paramIntNum, int paramInt) {
    if (paramInt > 0) {
      setShiftLeft(paramIntNum, paramInt);
      return;
    } 
    setShiftRight(paramIntNum, -paramInt);
  }
  
  void setShiftLeft(IntNum paramIntNum, int paramInt) {
    int[] arrayOfInt;
    int i;
    if (paramIntNum.words == null) {
      if (paramInt < 32) {
        set(paramIntNum.ival << paramInt);
        return;
      } 
      int[] arrayOfInt1 = new int[1];
      arrayOfInt1[0] = paramIntNum.ival;
      i = 1;
      arrayOfInt = arrayOfInt1;
    } else {
      int[] arrayOfInt1 = ((IntNum)arrayOfInt).words;
      i = ((IntNum)arrayOfInt).ival;
      arrayOfInt = arrayOfInt1;
    } 
    int j = paramInt >> 5;
    int m = paramInt & 0x1F;
    int k = i + j;
    if (m == 0) {
      realloc(k);
      while (true) {
        i--;
        paramInt = k;
        if (i >= 0) {
          this.words[i + j] = arrayOfInt[i];
          continue;
        } 
        break;
      } 
    } else {
      paramInt = k + 1;
      realloc(paramInt);
      i = MPN.lshift(this.words, j, arrayOfInt, i, m);
      k = 32 - m;
      this.words[paramInt - 1] = i << k >> k;
    } 
    this.ival = paramInt;
    paramInt = j;
    while (true) {
      if (--paramInt >= 0) {
        this.words[paramInt] = 0;
        continue;
      } 
      return;
    } 
  }
  
  void setShiftRight(IntNum paramIntNum, int paramInt) {
    int i = -1;
    if (paramIntNum.words == null) {
      if (paramInt < 32) {
        paramInt = paramIntNum.ival >> paramInt;
      } else {
        paramInt = i;
        if (paramIntNum.ival >= 0)
          paramInt = 0; 
      } 
      set(paramInt);
      return;
    } 
    if (paramInt == 0) {
      set(paramIntNum);
      return;
    } 
    boolean bool = paramIntNum.isNegative();
    int j = paramInt >> 5;
    paramInt &= 0x1F;
    int k = paramIntNum.ival - j;
    if (k <= 0) {
      if (!bool)
        i = 0; 
      set(i);
      return;
    } 
    if (this.words == null || this.words.length < k)
      realloc(k); 
    MPN.rshift0(this.words, paramIntNum.words, j, k, paramInt);
    this.ival = k;
    if (bool) {
      int[] arrayOfInt = this.words;
      i = k - 1;
      arrayOfInt[i] = arrayOfInt[i] | -2 << 31 - paramInt;
      return;
    } 
  }
  
  public int sign() {
    int i = this.ival;
    int[] arrayOfInt = this.words;
    if (arrayOfInt == null)
      return (i <= 0) ? ((i < 0) ? -1 : 0) : 1; 
    int j = arrayOfInt[--i];
    if (j <= 0) {
      if (j < 0)
        return -1; 
      while (true) {
        if (i == 0)
          return 0; 
        j = i - 1;
        i = j;
        if (arrayOfInt[j] != 0)
          return 1; 
      } 
    } 
    return 1;
  }
  
  public IntNum toExactInt(int paramInt) {
    return this;
  }
  
  public RealNum toInt(int paramInt) {
    return this;
  }
  
  public String toString(int paramInt) {
    if (this.words == null)
      return Integer.toString(this.ival, paramInt); 
    if (this.ival <= 2)
      return Long.toString(longValue(), paramInt); 
    StringBuilder stringBuilder = new StringBuilder(this.ival * (MPN.chars_per_word(paramInt) + 1));
    format(paramInt, stringBuilder);
    return stringBuilder.toString();
  }
  
  public void writeExternal(ObjectOutput paramObjectOutput) throws IOException {
    int i;
    boolean bool = false;
    if (this.words == null) {
      i = 1;
    } else {
      i = wordsNeeded(this.words, this.ival);
    } 
    if (i <= 1) {
      if (this.words == null) {
        i = this.ival;
      } else {
        i = bool;
        if (this.words.length != 0)
          i = this.words[0]; 
      } 
      if (i >= -1073741824) {
        paramObjectOutput.writeInt(i);
        return;
      } 
      paramObjectOutput.writeInt(-2147483647);
      paramObjectOutput.writeInt(i);
      return;
    } 
    paramObjectOutput.writeInt(Integer.MIN_VALUE | i);
    while (true) {
      if (--i >= 0) {
        paramObjectOutput.writeInt(this.words[i]);
        continue;
      } 
      return;
    } 
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/gnu/math/IntNum.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */