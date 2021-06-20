package gnu.math;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

public class Duration extends Quantity implements Externalizable {
  int months;
  
  int nanos;
  
  long seconds;
  
  public Unit unit;
  
  public static Duration add(Duration paramDuration1, Duration paramDuration2, int paramInt) {
    long l1 = paramDuration1.months;
    long l2 = paramInt;
    long l3 = paramDuration2.months;
    long l4 = paramDuration1.seconds * 1000000000L + paramDuration1.nanos + paramInt * (paramDuration2.seconds * 1000000000L + paramDuration2.nanos);
    Duration duration = new Duration();
    duration.months = (int)(l1 + l2 * l3);
    duration.seconds = (int)(l4 / 1000000000L);
    duration.nanos = (int)(l4 % 1000000000L);
    if (paramDuration1.unit != paramDuration2.unit || paramDuration1.unit == Unit.duration)
      throw new ArithmeticException("cannot add these duration types"); 
    duration.unit = paramDuration1.unit;
    return duration;
  }
  
  static void appendNanoSeconds(int paramInt, StringBuffer paramStringBuffer) {
    if (paramInt == 0)
      return; 
    paramStringBuffer.append('.');
    int i = paramStringBuffer.length();
    paramStringBuffer.append(paramInt);
    paramInt = i + 9 - paramStringBuffer.length();
    while (true) {
      if (--paramInt >= 0) {
        paramStringBuffer.insert(i, '0');
        continue;
      } 
      paramInt = i + 9;
      while (true) {
        i = paramInt - 1;
        paramInt = i;
        if (paramStringBuffer.charAt(i) != '0') {
          paramStringBuffer.setLength(i + 1);
          return;
        } 
      } 
      break;
    } 
  }
  
  public static int compare(Duration paramDuration1, Duration paramDuration2) {
    long l1 = paramDuration1.months - paramDuration2.months;
    long l2 = paramDuration1.seconds * 1000000000L + paramDuration1.nanos - paramDuration2.seconds * 1000000000L + paramDuration2.nanos;
    return (l1 >= 0L || l2 > 0L) ? ((l1 > 0L && l2 >= 0L) ? 1 : ((l1 == 0L) ? ((l2 >= 0L) ? ((l2 > 0L) ? 1 : 0) : -1) : -2)) : -1;
  }
  
  public static double div(Duration paramDuration1, Duration paramDuration2) {
    int i = paramDuration1.months;
    int j = paramDuration2.months;
    double d1 = paramDuration1.seconds + paramDuration1.nanos * 1.0E-9D;
    double d2 = paramDuration2.seconds + paramDuration1.nanos * 1.0E-9D;
    if (j == 0 && d2 == 0.0D)
      throw new ArithmeticException("divide duration by zero"); 
    if (j == 0) {
      if (i == 0)
        return d1 / d2; 
    } else if (d2 == 0.0D && d1 == 0.0D) {
      return i / j;
    } 
    throw new ArithmeticException("divide of incompatible durations");
  }
  
  public static boolean equals(Duration paramDuration1, Duration paramDuration2) {
    return (paramDuration1.months == paramDuration2.months && paramDuration1.seconds == paramDuration2.seconds && paramDuration1.nanos == paramDuration2.nanos);
  }
  
  public static Duration make(int paramInt1, long paramLong, int paramInt2, Unit paramUnit) {
    Duration duration = new Duration();
    duration.months = paramInt1;
    duration.seconds = paramLong;
    duration.nanos = paramInt2;
    duration.unit = paramUnit;
    return duration;
  }
  
  public static Duration makeMinutes(int paramInt) {
    Duration duration = new Duration();
    duration.unit = Unit.second;
    duration.seconds = (paramInt * 60);
    return duration;
  }
  
  public static Duration makeMonths(int paramInt) {
    Duration duration = new Duration();
    duration.unit = Unit.month;
    duration.months = paramInt;
    return duration;
  }
  
  public static Duration parse(String paramString, Unit paramUnit) {
    Duration duration = valueOf(paramString, paramUnit);
    if (duration == null)
      throw new IllegalArgumentException("not a valid " + paramUnit.getName() + " duration: '" + paramString + "'"); 
    return duration;
  }
  
  public static Duration parseDayTimeDuration(String paramString) {
    return parse(paramString, Unit.second);
  }
  
  public static Duration parseDuration(String paramString) {
    return parse(paramString, Unit.duration);
  }
  
  public static Duration parseYearMonthDuration(String paramString) {
    return parse(paramString, Unit.month);
  }
  
  private static long scanPart(String paramString, int paramInt) {
    long l3 = -1L;
    int i = paramInt;
    long l2 = -1L;
    int j = paramString.length();
    while (i < j) {
      long l;
      char c = paramString.charAt(i);
      i++;
      int k = Character.digit(c, 10);
      if (k < 0)
        return (l2 < 0L) ? (paramInt << 16) : (l2 << 32L | (i << 16) | c); 
      if (l2 < 0L) {
        l = k;
      } else {
        l = 10L * l2 + k;
      } 
      l2 = l;
      if (l > 2147483647L)
        return -1L; 
    } 
    long l1 = l3;
    return (l2 < 0L) ? (paramInt << 16) : l1;
  }
  
  public static Duration times(Duration paramDuration, double paramDouble) {
    if (paramDuration.unit == Unit.duration)
      throw new IllegalArgumentException("cannot multiply general duration"); 
    double d = paramDuration.months * paramDouble;
    if (Double.isInfinite(d) || Double.isNaN(d))
      throw new ArithmeticException("overflow/NaN when multiplying a duration"); 
    paramDouble = (paramDuration.seconds * 1000000000L + paramDuration.nanos) * paramDouble;
    Duration duration = new Duration();
    duration.months = (int)Math.floor(0.5D + d);
    duration.seconds = (int)(paramDouble / 1.0E9D);
    duration.nanos = (int)(paramDouble % 1.0E9D);
    duration.unit = paramDuration.unit;
    return duration;
  }
  
  public static Duration valueOf(String paramString, Unit paramUnit) {
    // Byte code:
    //   0: aload_0
    //   1: invokevirtual trim : ()Ljava/lang/String;
    //   4: astore_0
    //   5: iconst_0
    //   6: istore_3
    //   7: aload_0
    //   8: invokevirtual length : ()I
    //   11: istore #11
    //   13: iload #11
    //   15: ifge -> 55
    //   18: aload_0
    //   19: iconst_0
    //   20: invokevirtual charAt : (I)C
    //   23: bipush #45
    //   25: if_icmpne -> 55
    //   28: iconst_1
    //   29: istore #8
    //   31: iconst_0
    //   32: iconst_1
    //   33: iadd
    //   34: istore_3
    //   35: iload_3
    //   36: iconst_1
    //   37: iadd
    //   38: iload #11
    //   40: if_icmpge -> 53
    //   43: aload_0
    //   44: iload_3
    //   45: invokevirtual charAt : (I)C
    //   48: bipush #80
    //   50: if_icmpeq -> 61
    //   53: aconst_null
    //   54: areturn
    //   55: iconst_0
    //   56: istore #8
    //   58: goto -> 35
    //   61: iconst_0
    //   62: istore #5
    //   64: iconst_0
    //   65: istore #9
    //   67: iconst_0
    //   68: istore #10
    //   70: lconst_0
    //   71: lstore #18
    //   73: aload_0
    //   74: iload_3
    //   75: iconst_1
    //   76: iadd
    //   77: invokestatic scanPart : (Ljava/lang/String;I)J
    //   80: lstore #14
    //   82: lload #14
    //   84: l2i
    //   85: bipush #16
    //   87: ishr
    //   88: istore #6
    //   90: lload #14
    //   92: l2i
    //   93: i2c
    //   94: istore #4
    //   96: aload_1
    //   97: getstatic gnu/math/Unit.second : Lgnu/math/NamedUnit;
    //   100: if_acmpne -> 119
    //   103: iload #4
    //   105: bipush #89
    //   107: if_icmpeq -> 117
    //   110: iload #4
    //   112: bipush #77
    //   114: if_icmpne -> 119
    //   117: aconst_null
    //   118: areturn
    //   119: iload #4
    //   121: istore_3
    //   122: lload #14
    //   124: lstore #12
    //   126: iload #4
    //   128: bipush #89
    //   130: if_icmpne -> 165
    //   133: lload #14
    //   135: bipush #32
    //   137: lshr
    //   138: l2i
    //   139: bipush #12
    //   141: imul
    //   142: istore #5
    //   144: lload #14
    //   146: l2i
    //   147: bipush #16
    //   149: ishr
    //   150: istore #6
    //   152: aload_0
    //   153: iload #6
    //   155: invokestatic scanPart : (Ljava/lang/String;I)J
    //   158: lstore #12
    //   160: lload #12
    //   162: l2i
    //   163: i2c
    //   164: istore_3
    //   165: iload_3
    //   166: istore #7
    //   168: iload #5
    //   170: istore #4
    //   172: lload #12
    //   174: lstore #14
    //   176: iload_3
    //   177: bipush #77
    //   179: if_icmpne -> 216
    //   182: iload #5
    //   184: i2l
    //   185: lload #12
    //   187: bipush #32
    //   189: lshr
    //   190: ladd
    //   191: l2i
    //   192: istore #4
    //   194: lload #12
    //   196: l2i
    //   197: bipush #16
    //   199: ishr
    //   200: istore #6
    //   202: aload_0
    //   203: iload #6
    //   205: invokestatic scanPart : (Ljava/lang/String;I)J
    //   208: lstore #14
    //   210: lload #14
    //   212: l2i
    //   213: i2c
    //   214: istore #7
    //   216: aload_1
    //   217: getstatic gnu/math/Unit.month : Lgnu/math/NamedUnit;
    //   220: if_acmpne -> 232
    //   223: iload #6
    //   225: iload #11
    //   227: if_icmpeq -> 232
    //   230: aconst_null
    //   231: areturn
    //   232: lload #14
    //   234: lstore #16
    //   236: lload #18
    //   238: lstore #12
    //   240: iload #7
    //   242: bipush #68
    //   244: if_icmpne -> 285
    //   247: aload_1
    //   248: getstatic gnu/math/Unit.month : Lgnu/math/NamedUnit;
    //   251: if_acmpne -> 256
    //   254: aconst_null
    //   255: areturn
    //   256: ldc2_w 86400
    //   259: lload #14
    //   261: bipush #32
    //   263: lshr
    //   264: l2i
    //   265: i2l
    //   266: lmul
    //   267: lstore #12
    //   269: lload #14
    //   271: l2i
    //   272: bipush #16
    //   274: ishr
    //   275: istore #6
    //   277: aload_0
    //   278: iload #6
    //   280: invokestatic scanPart : (Ljava/lang/String;I)J
    //   283: lstore #16
    //   285: lload #16
    //   287: iload #6
    //   289: bipush #16
    //   291: ishl
    //   292: i2l
    //   293: lcmp
    //   294: ifeq -> 299
    //   297: aconst_null
    //   298: areturn
    //   299: iload #6
    //   301: iload #11
    //   303: if_icmpne -> 323
    //   306: iload #6
    //   308: istore #7
    //   310: iload #10
    //   312: istore #5
    //   314: iload #7
    //   316: iload #11
    //   318: if_icmpeq -> 743
    //   321: aconst_null
    //   322: areturn
    //   323: aload_0
    //   324: iload #6
    //   326: invokevirtual charAt : (I)C
    //   329: bipush #84
    //   331: if_icmpne -> 347
    //   334: iload #6
    //   336: iconst_1
    //   337: iadd
    //   338: istore #5
    //   340: iload #5
    //   342: iload #11
    //   344: if_icmpne -> 349
    //   347: aconst_null
    //   348: areturn
    //   349: aload_1
    //   350: getstatic gnu/math/Unit.month : Lgnu/math/NamedUnit;
    //   353: if_acmpne -> 358
    //   356: aconst_null
    //   357: areturn
    //   358: aload_0
    //   359: iload #5
    //   361: invokestatic scanPart : (Ljava/lang/String;I)J
    //   364: lstore #18
    //   366: lload #18
    //   368: l2i
    //   369: i2c
    //   370: istore #6
    //   372: iload #6
    //   374: istore_3
    //   375: lload #18
    //   377: lstore #14
    //   379: lload #12
    //   381: lstore #16
    //   383: iload #6
    //   385: bipush #72
    //   387: if_icmpne -> 427
    //   390: lload #12
    //   392: lload #18
    //   394: bipush #32
    //   396: lshr
    //   397: l2i
    //   398: sipush #3600
    //   401: imul
    //   402: i2l
    //   403: ladd
    //   404: lstore #16
    //   406: lload #18
    //   408: l2i
    //   409: bipush #16
    //   411: ishr
    //   412: istore #5
    //   414: aload_0
    //   415: iload #5
    //   417: invokestatic scanPart : (Ljava/lang/String;I)J
    //   420: lstore #14
    //   422: lload #14
    //   424: l2i
    //   425: i2c
    //   426: istore_3
    //   427: iload_3
    //   428: istore #6
    //   430: lload #14
    //   432: lstore #18
    //   434: lload #16
    //   436: lstore #12
    //   438: iload_3
    //   439: bipush #77
    //   441: if_icmpne -> 481
    //   444: lload #16
    //   446: lload #14
    //   448: bipush #32
    //   450: lshr
    //   451: l2i
    //   452: bipush #60
    //   454: imul
    //   455: i2l
    //   456: ladd
    //   457: lstore #12
    //   459: lload #14
    //   461: l2i
    //   462: bipush #16
    //   464: ishr
    //   465: istore #5
    //   467: aload_0
    //   468: iload #5
    //   470: invokestatic scanPart : (Ljava/lang/String;I)J
    //   473: lstore #18
    //   475: lload #18
    //   477: l2i
    //   478: i2c
    //   479: istore #6
    //   481: iload #6
    //   483: bipush #83
    //   485: if_icmpeq -> 502
    //   488: iload #5
    //   490: istore_3
    //   491: lload #12
    //   493: lstore #14
    //   495: iload #6
    //   497: bipush #46
    //   499: if_icmpne -> 521
    //   502: lload #12
    //   504: lload #18
    //   506: bipush #32
    //   508: lshr
    //   509: l2i
    //   510: i2l
    //   511: ladd
    //   512: lstore #14
    //   514: lload #18
    //   516: l2i
    //   517: bipush #16
    //   519: ishr
    //   520: istore_3
    //   521: iload #10
    //   523: istore #5
    //   525: iload_3
    //   526: istore #7
    //   528: lload #14
    //   530: lstore #12
    //   532: iload #6
    //   534: bipush #46
    //   536: if_icmpne -> 314
    //   539: iload #10
    //   541: istore #5
    //   543: iload_3
    //   544: istore #7
    //   546: lload #14
    //   548: lstore #12
    //   550: iload_3
    //   551: iconst_1
    //   552: iadd
    //   553: iload #11
    //   555: if_icmpge -> 314
    //   558: iload #10
    //   560: istore #5
    //   562: iload_3
    //   563: istore #7
    //   565: lload #14
    //   567: lstore #12
    //   569: aload_0
    //   570: iload_3
    //   571: invokevirtual charAt : (I)C
    //   574: bipush #10
    //   576: invokestatic digit : (CI)I
    //   579: iflt -> 314
    //   582: iconst_0
    //   583: istore #5
    //   585: iload_3
    //   586: istore #7
    //   588: iload #5
    //   590: istore_3
    //   591: iload #9
    //   593: istore #5
    //   595: iload #6
    //   597: istore #9
    //   599: iload #7
    //   601: iload #11
    //   603: if_icmpge -> 805
    //   606: iload #7
    //   608: iconst_1
    //   609: iadd
    //   610: istore #9
    //   612: aload_0
    //   613: iload #7
    //   615: invokevirtual charAt : (I)C
    //   618: istore_2
    //   619: iload_2
    //   620: bipush #10
    //   622: invokestatic digit : (CI)I
    //   625: istore #7
    //   627: iload #7
    //   629: ifge -> 664
    //   632: iload_3
    //   633: istore #6
    //   635: iload #9
    //   637: istore_3
    //   638: iload_2
    //   639: istore #9
    //   641: iload #6
    //   643: bipush #9
    //   645: if_icmpge -> 727
    //   648: iload #5
    //   650: bipush #10
    //   652: imul
    //   653: istore #5
    //   655: iload #6
    //   657: iconst_1
    //   658: iadd
    //   659: istore #6
    //   661: goto -> 641
    //   664: iload_3
    //   665: bipush #9
    //   667: if_icmpge -> 698
    //   670: iload #5
    //   672: bipush #10
    //   674: imul
    //   675: iload #7
    //   677: iadd
    //   678: istore #6
    //   680: iload_3
    //   681: iconst_1
    //   682: iadd
    //   683: istore_3
    //   684: iload #9
    //   686: istore #7
    //   688: iload_2
    //   689: istore #9
    //   691: iload #6
    //   693: istore #5
    //   695: goto -> 599
    //   698: iload #5
    //   700: istore #6
    //   702: iload_3
    //   703: bipush #9
    //   705: if_icmpne -> 680
    //   708: iload #5
    //   710: istore #6
    //   712: iload #7
    //   714: iconst_5
    //   715: if_icmplt -> 680
    //   718: iload #5
    //   720: iconst_1
    //   721: iadd
    //   722: istore #6
    //   724: goto -> 680
    //   727: iload_3
    //   728: istore #7
    //   730: lload #14
    //   732: lstore #12
    //   734: iload #9
    //   736: bipush #83
    //   738: if_icmpeq -> 314
    //   741: aconst_null
    //   742: areturn
    //   743: new gnu/math/Duration
    //   746: dup
    //   747: invokespecial <init> : ()V
    //   750: astore_0
    //   751: iload #4
    //   753: istore #6
    //   755: iload #5
    //   757: istore_3
    //   758: lload #12
    //   760: lstore #14
    //   762: iload #8
    //   764: ifeq -> 781
    //   767: iload #4
    //   769: ineg
    //   770: istore #6
    //   772: lload #12
    //   774: lneg
    //   775: lstore #14
    //   777: iload #5
    //   779: ineg
    //   780: istore_3
    //   781: aload_0
    //   782: iload #6
    //   784: putfield months : I
    //   787: aload_0
    //   788: lload #14
    //   790: putfield seconds : J
    //   793: aload_0
    //   794: iload_3
    //   795: putfield nanos : I
    //   798: aload_0
    //   799: aload_1
    //   800: putfield unit : Lgnu/math/Unit;
    //   803: aload_0
    //   804: areturn
    //   805: iload_3
    //   806: istore #6
    //   808: iload #7
    //   810: istore_3
    //   811: goto -> 641
  }
  
  public Numeric add(Object paramObject, int paramInt) {
    if (paramObject instanceof Duration)
      return add(this, (Duration)paramObject, paramInt); 
    if (paramObject instanceof DateTime && paramInt == 1)
      return DateTime.add((DateTime)paramObject, this, 1); 
    throw new IllegalArgumentException();
  }
  
  public int compare(Object paramObject) {
    if (paramObject instanceof Duration)
      return compare(this, (Duration)paramObject); 
    throw new IllegalArgumentException();
  }
  
  public Numeric div(Object paramObject) {
    if (paramObject instanceof RealNum) {
      double d = ((RealNum)paramObject).doubleValue();
      if (d == 0.0D || Double.isNaN(d))
        throw new ArithmeticException("divide of duration by 0 or NaN"); 
      return times(this, 1.0D / d);
    } 
    return (paramObject instanceof Duration) ? new DFloNum(div(this, (Duration)paramObject)) : ((Numeric)paramObject).divReversed(this);
  }
  
  public boolean equals(Object paramObject) {
    return (paramObject == null || !(paramObject instanceof Duration)) ? false : equals(this, (Duration)paramObject);
  }
  
  public int getDays() {
    return (int)(this.seconds / 86400L);
  }
  
  public int getHours() {
    return (int)(this.seconds / 3600L % 24L);
  }
  
  public int getMinutes() {
    return (int)(this.seconds / 60L % 60L);
  }
  
  public int getMonths() {
    return this.months % 12;
  }
  
  public long getNanoSeconds() {
    return this.seconds * 1000000000L + this.nanos;
  }
  
  public int getNanoSecondsOnly() {
    return this.nanos;
  }
  
  public int getSecondsOnly() {
    return (int)(this.seconds % 60L);
  }
  
  public long getTotalMinutes() {
    return this.seconds / 60L;
  }
  
  public int getTotalMonths() {
    return this.months;
  }
  
  public long getTotalSeconds() {
    return this.seconds;
  }
  
  public int getYears() {
    return this.months / 12;
  }
  
  public int hashCode() {
    return this.months ^ (int)this.seconds ^ this.nanos;
  }
  
  public boolean isExact() {
    return false;
  }
  
  public boolean isZero() {
    return (this.months == 0 && this.seconds == 0L && this.nanos == 0);
  }
  
  public Numeric mul(Object paramObject) {
    return (paramObject instanceof RealNum) ? times(this, ((RealNum)paramObject).doubleValue()) : ((Numeric)paramObject).mulReversed(this);
  }
  
  public Numeric mulReversed(Numeric paramNumeric) {
    if (!(paramNumeric instanceof RealNum))
      throw new IllegalArgumentException(); 
    return times(this, ((RealNum)paramNumeric).doubleValue());
  }
  
  public Complex number() {
    throw new Error("number needs to be implemented!");
  }
  
  public void readExternal(ObjectInput paramObjectInput) throws IOException, ClassNotFoundException {
    this.months = paramObjectInput.readInt();
    this.seconds = paramObjectInput.readLong();
    this.nanos = paramObjectInput.readInt();
    this.unit = (Unit)paramObjectInput.readObject();
  }
  
  public String toString() {
    StringBuffer stringBuffer = new StringBuffer();
    int n = this.months;
    long l1 = this.seconds;
    int m = this.nanos;
    if (n < 0 || l1 < 0L || m < 0) {
      k = 1;
    } else {
      k = 0;
    } 
    int i = n;
    int j = m;
    long l2 = l1;
    if (k) {
      i = -n;
      l2 = -l1;
      j = -m;
      stringBuffer.append('-');
    } 
    stringBuffer.append('P');
    m = i / 12;
    int k = i;
    if (m != 0) {
      stringBuffer.append(m);
      stringBuffer.append('Y');
      k = i - m * 12;
    } 
    if (k != 0) {
      stringBuffer.append(k);
      stringBuffer.append('M');
    } 
    long l3 = l2 / 86400L;
    l1 = l2;
    if (l3 != 0L) {
      stringBuffer.append(l3);
      stringBuffer.append('D');
      l1 = l2 - 86400L * l3;
    } 
    if (l1 != 0L || j != 0) {
      stringBuffer.append('T');
      l3 = l1 / 3600L;
      l2 = l1;
      if (l3 != 0L) {
        stringBuffer.append(l3);
        stringBuffer.append('H');
        l2 = l1 - 3600L * l3;
      } 
      l3 = l2 / 60L;
      l1 = l2;
      if (l3 != 0L) {
        stringBuffer.append(l3);
        stringBuffer.append('M');
        l1 = l2 - 60L * l3;
      } 
      if (l1 != 0L || j != 0) {
        stringBuffer.append(l1);
        appendNanoSeconds(j, stringBuffer);
        stringBuffer.append('S');
      } 
      return stringBuffer.toString();
    } 
    if (stringBuffer.length() == 1) {
      String str;
      if (this.unit == Unit.month) {
        str = "0M";
      } else {
        str = "T0S";
      } 
      stringBuffer.append(str);
    } 
    return stringBuffer.toString();
  }
  
  public Unit unit() {
    return this.unit;
  }
  
  public void writeExternal(ObjectOutput paramObjectOutput) throws IOException {
    paramObjectOutput.writeInt(this.months);
    paramObjectOutput.writeLong(this.seconds);
    paramObjectOutput.writeInt(this.nanos);
    paramObjectOutput.writeObject(this.unit);
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/gnu/math/Duration.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */