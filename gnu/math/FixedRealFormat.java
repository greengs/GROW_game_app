package gnu.math;

import java.text.FieldPosition;
import java.text.Format;
import java.text.ParsePosition;

public class FixedRealFormat extends Format {
  private int d;
  
  private int i;
  
  public boolean internalPad;
  
  public char overflowChar;
  
  public char padChar;
  
  public int scale;
  
  public boolean showPlus;
  
  public int width;
  
  private void format(StringBuffer paramStringBuffer, FieldPosition paramFieldPosition, int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5) {
    // Byte code:
    //   0: aload_0
    //   1: invokevirtual getMinimumIntegerDigits : ()I
    //   4: istore #8
    //   6: iload #4
    //   8: iflt -> 106
    //   11: iload #4
    //   13: iload #8
    //   15: if_icmple -> 106
    //   18: iconst_0
    //   19: istore #9
    //   21: iload #9
    //   23: istore #8
    //   25: iload #4
    //   27: iload #9
    //   29: iadd
    //   30: ifgt -> 64
    //   33: aload_0
    //   34: getfield width : I
    //   37: ifle -> 58
    //   40: iload #9
    //   42: istore #8
    //   44: aload_0
    //   45: getfield width : I
    //   48: iload #5
    //   50: iconst_1
    //   51: iadd
    //   52: iload #6
    //   54: iadd
    //   55: if_icmple -> 64
    //   58: iload #9
    //   60: iconst_1
    //   61: iadd
    //   62: istore #8
    //   64: aload_0
    //   65: getfield width : I
    //   68: iload #6
    //   70: iload_3
    //   71: iadd
    //   72: iload #8
    //   74: iadd
    //   75: iconst_1
    //   76: iadd
    //   77: isub
    //   78: istore #9
    //   80: iload #8
    //   82: iconst_1
    //   83: isub
    //   84: istore #8
    //   86: iload #8
    //   88: iflt -> 116
    //   91: aload_1
    //   92: iload #7
    //   94: iload #6
    //   96: iadd
    //   97: bipush #48
    //   99: invokevirtual insert : (IC)Ljava/lang/StringBuffer;
    //   102: pop
    //   103: goto -> 80
    //   106: iload #8
    //   108: iload #4
    //   110: isub
    //   111: istore #9
    //   113: goto -> 21
    //   116: iload #9
    //   118: iflt -> 180
    //   121: iload #7
    //   123: istore_3
    //   124: iload #9
    //   126: istore #4
    //   128: aload_0
    //   129: getfield internalPad : Z
    //   132: ifeq -> 156
    //   135: iload #7
    //   137: istore_3
    //   138: iload #9
    //   140: istore #4
    //   142: iload #6
    //   144: ifle -> 156
    //   147: iload #7
    //   149: iconst_1
    //   150: iadd
    //   151: istore_3
    //   152: iload #9
    //   154: istore #4
    //   156: iload #4
    //   158: iconst_1
    //   159: isub
    //   160: istore #4
    //   162: iload #4
    //   164: iflt -> 229
    //   167: aload_1
    //   168: iload_3
    //   169: aload_0
    //   170: getfield padChar : C
    //   173: invokevirtual insert : (IC)Ljava/lang/StringBuffer;
    //   176: pop
    //   177: goto -> 156
    //   180: aload_0
    //   181: getfield overflowChar : C
    //   184: ifeq -> 229
    //   187: aload_1
    //   188: iload #7
    //   190: invokevirtual setLength : (I)V
    //   193: aload_0
    //   194: aload_0
    //   195: getfield width : I
    //   198: putfield i : I
    //   201: aload_0
    //   202: getfield i : I
    //   205: iconst_1
    //   206: isub
    //   207: istore_3
    //   208: aload_0
    //   209: iload_3
    //   210: putfield i : I
    //   213: iload_3
    //   214: iflt -> 243
    //   217: aload_1
    //   218: aload_0
    //   219: getfield overflowChar : C
    //   222: invokevirtual append : (C)Ljava/lang/StringBuffer;
    //   225: pop
    //   226: goto -> 201
    //   229: aload_1
    //   230: aload_1
    //   231: invokevirtual length : ()I
    //   234: iload #5
    //   236: isub
    //   237: bipush #46
    //   239: invokevirtual insert : (IC)Ljava/lang/StringBuffer;
    //   242: pop
    //   243: return
  }
  
  public StringBuffer format(double paramDouble, StringBuffer paramStringBuffer, FieldPosition paramFieldPosition) {
    // Byte code:
    //   0: dload_1
    //   1: invokestatic isNaN : (D)Z
    //   4: ifne -> 14
    //   7: dload_1
    //   8: invokestatic isInfinite : (D)Z
    //   11: ifeq -> 20
    //   14: aload_3
    //   15: dload_1
    //   16: invokevirtual append : (D)Ljava/lang/StringBuffer;
    //   19: areturn
    //   20: aload_0
    //   21: invokevirtual getMaximumFractionDigits : ()I
    //   24: iflt -> 40
    //   27: aload_0
    //   28: dload_1
    //   29: invokestatic toExact : (D)Lgnu/math/RatNum;
    //   32: aload_3
    //   33: aload #4
    //   35: invokevirtual format : (Lgnu/math/RealNum;Ljava/lang/StringBuffer;Ljava/text/FieldPosition;)V
    //   38: aload_3
    //   39: areturn
    //   40: dload_1
    //   41: dconst_0
    //   42: dcmpg
    //   43: ifge -> 276
    //   46: iconst_1
    //   47: istore #9
    //   49: dload_1
    //   50: dneg
    //   51: dstore_1
    //   52: aload_3
    //   53: invokevirtual length : ()I
    //   56: istore #15
    //   58: iconst_1
    //   59: istore #10
    //   61: iload #9
    //   63: ifeq -> 282
    //   66: aload_3
    //   67: bipush #45
    //   69: invokevirtual append : (C)Ljava/lang/StringBuffer;
    //   72: pop
    //   73: dload_1
    //   74: invokestatic toString : (D)Ljava/lang/String;
    //   77: astore #18
    //   79: aload_0
    //   80: getfield scale : I
    //   83: istore #6
    //   85: aload #18
    //   87: bipush #69
    //   89: invokevirtual indexOf : (I)I
    //   92: istore #8
    //   94: iload #6
    //   96: istore #5
    //   98: aload #18
    //   100: astore #17
    //   102: iload #8
    //   104: iflt -> 160
    //   107: iload #8
    //   109: iconst_1
    //   110: iadd
    //   111: istore #7
    //   113: iload #7
    //   115: istore #5
    //   117: aload #18
    //   119: iload #7
    //   121: invokevirtual charAt : (I)C
    //   124: bipush #43
    //   126: if_icmpne -> 135
    //   129: iload #7
    //   131: iconst_1
    //   132: iadd
    //   133: istore #5
    //   135: iload #6
    //   137: aload #18
    //   139: iload #5
    //   141: invokevirtual substring : (I)Ljava/lang/String;
    //   144: invokestatic parseInt : (Ljava/lang/String;)I
    //   147: iadd
    //   148: istore #5
    //   150: aload #18
    //   152: iconst_0
    //   153: iload #8
    //   155: invokevirtual substring : (II)Ljava/lang/String;
    //   158: astore #17
    //   160: aload #17
    //   162: bipush #46
    //   164: invokevirtual indexOf : (I)I
    //   167: istore #6
    //   169: aload #17
    //   171: invokevirtual length : ()I
    //   174: istore #8
    //   176: iload #5
    //   178: istore #7
    //   180: aload #17
    //   182: astore #18
    //   184: iload #6
    //   186: iflt -> 236
    //   189: iload #5
    //   191: iload #8
    //   193: iload #6
    //   195: isub
    //   196: iconst_1
    //   197: isub
    //   198: isub
    //   199: istore #7
    //   201: new java/lang/StringBuilder
    //   204: dup
    //   205: invokespecial <init> : ()V
    //   208: aload #17
    //   210: iconst_0
    //   211: iload #6
    //   213: invokevirtual substring : (II)Ljava/lang/String;
    //   216: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   219: aload #17
    //   221: iload #6
    //   223: iconst_1
    //   224: iadd
    //   225: invokevirtual substring : (I)Ljava/lang/String;
    //   228: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   231: invokevirtual toString : ()Ljava/lang/String;
    //   234: astore #18
    //   236: aload #18
    //   238: invokevirtual length : ()I
    //   241: istore #8
    //   243: iconst_0
    //   244: istore #6
    //   246: iload #6
    //   248: iload #8
    //   250: iconst_1
    //   251: isub
    //   252: if_icmpge -> 305
    //   255: aload #18
    //   257: iload #6
    //   259: invokevirtual charAt : (I)C
    //   262: bipush #48
    //   264: if_icmpne -> 305
    //   267: iload #6
    //   269: iconst_1
    //   270: iadd
    //   271: istore #6
    //   273: goto -> 246
    //   276: iconst_0
    //   277: istore #9
    //   279: goto -> 52
    //   282: aload_0
    //   283: getfield showPlus : Z
    //   286: ifeq -> 299
    //   289: aload_3
    //   290: bipush #43
    //   292: invokevirtual append : (C)Ljava/lang/StringBuffer;
    //   295: pop
    //   296: goto -> 73
    //   299: iconst_0
    //   300: istore #10
    //   302: goto -> 73
    //   305: iload #8
    //   307: istore #5
    //   309: aload #18
    //   311: astore #17
    //   313: iload #6
    //   315: ifle -> 334
    //   318: aload #18
    //   320: iload #6
    //   322: invokevirtual substring : (I)Ljava/lang/String;
    //   325: astore #17
    //   327: iload #8
    //   329: iload #6
    //   331: isub
    //   332: istore #5
    //   334: iload #5
    //   336: iload #7
    //   338: iadd
    //   339: istore #6
    //   341: aload_0
    //   342: getfield width : I
    //   345: ifle -> 435
    //   348: iload #6
    //   350: ifge -> 375
    //   353: aload_3
    //   354: bipush #48
    //   356: invokevirtual append : (C)Ljava/lang/StringBuffer;
    //   359: pop
    //   360: iload #6
    //   362: iconst_1
    //   363: iadd
    //   364: istore #6
    //   366: iload #5
    //   368: iconst_1
    //   369: iadd
    //   370: istore #5
    //   372: goto -> 348
    //   375: aload_0
    //   376: getfield width : I
    //   379: iload #10
    //   381: isub
    //   382: iconst_1
    //   383: isub
    //   384: iload #6
    //   386: isub
    //   387: istore #8
    //   389: iload #8
    //   391: istore #11
    //   393: iload #8
    //   395: ifge -> 401
    //   398: iconst_0
    //   399: istore #11
    //   401: aload_3
    //   402: aload #17
    //   404: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   407: pop
    //   408: iload #7
    //   410: ifle -> 463
    //   413: aload_3
    //   414: bipush #48
    //   416: invokevirtual append : (C)Ljava/lang/StringBuffer;
    //   419: pop
    //   420: iload #7
    //   422: iconst_1
    //   423: isub
    //   424: istore #7
    //   426: iload #5
    //   428: iconst_1
    //   429: iadd
    //   430: istore #5
    //   432: goto -> 408
    //   435: iload #5
    //   437: bipush #16
    //   439: if_icmple -> 456
    //   442: bipush #16
    //   444: istore #8
    //   446: iload #8
    //   448: iload #6
    //   450: isub
    //   451: istore #8
    //   453: goto -> 389
    //   456: iload #5
    //   458: istore #8
    //   460: goto -> 446
    //   463: iload #15
    //   465: iload #10
    //   467: iadd
    //   468: istore #16
    //   470: iload #16
    //   472: iload #6
    //   474: iadd
    //   475: iload #11
    //   477: iadd
    //   478: istore #5
    //   480: aload_3
    //   481: invokevirtual length : ()I
    //   484: istore #7
    //   486: iload #5
    //   488: iload #7
    //   490: if_icmplt -> 556
    //   493: iload #7
    //   495: istore #5
    //   497: bipush #48
    //   499: istore #7
    //   501: iload #7
    //   503: bipush #53
    //   505: if_icmplt -> 567
    //   508: iconst_1
    //   509: istore #11
    //   511: iload #11
    //   513: ifeq -> 573
    //   516: bipush #57
    //   518: istore #7
    //   520: iload #5
    //   522: istore #12
    //   524: iload #12
    //   526: iload #16
    //   528: iload #6
    //   530: iadd
    //   531: if_icmple -> 584
    //   534: aload_3
    //   535: iload #12
    //   537: iconst_1
    //   538: isub
    //   539: invokevirtual charAt : (I)C
    //   542: iload #7
    //   544: if_icmpne -> 584
    //   547: iload #12
    //   549: iconst_1
    //   550: isub
    //   551: istore #12
    //   553: goto -> 524
    //   556: aload_3
    //   557: iload #5
    //   559: invokevirtual charAt : (I)C
    //   562: istore #7
    //   564: goto -> 501
    //   567: iconst_0
    //   568: istore #11
    //   570: goto -> 511
    //   573: bipush #48
    //   575: istore #7
    //   577: iload #5
    //   579: istore #12
    //   581: goto -> 524
    //   584: iload #12
    //   586: iload #16
    //   588: isub
    //   589: istore #13
    //   591: iload #13
    //   593: iload #6
    //   595: isub
    //   596: istore #14
    //   598: iload #13
    //   600: istore #7
    //   602: iload #6
    //   604: istore #8
    //   606: iload #14
    //   608: istore #5
    //   610: iload #11
    //   612: ifeq -> 651
    //   615: iload #13
    //   617: istore #7
    //   619: iload #6
    //   621: istore #8
    //   623: iload #14
    //   625: istore #5
    //   627: aload_3
    //   628: iload #16
    //   630: iload #12
    //   632: invokestatic addOne : (Ljava/lang/StringBuffer;II)Z
    //   635: ifeq -> 651
    //   638: iload #6
    //   640: iconst_1
    //   641: iadd
    //   642: istore #8
    //   644: iconst_0
    //   645: istore #5
    //   647: iload #8
    //   649: istore #7
    //   651: iload #7
    //   653: istore #11
    //   655: iload #5
    //   657: istore #6
    //   659: iload #5
    //   661: ifne -> 714
    //   664: aload_0
    //   665: getfield width : I
    //   668: ifle -> 693
    //   671: iload #7
    //   673: istore #11
    //   675: iload #5
    //   677: istore #6
    //   679: iload #10
    //   681: iload #8
    //   683: iadd
    //   684: iconst_1
    //   685: iadd
    //   686: aload_0
    //   687: getfield width : I
    //   690: if_icmpge -> 714
    //   693: iconst_1
    //   694: istore #6
    //   696: iload #7
    //   698: iconst_1
    //   699: iadd
    //   700: istore #11
    //   702: aload_3
    //   703: iload #16
    //   705: iload #8
    //   707: iadd
    //   708: bipush #48
    //   710: invokevirtual insert : (IC)Ljava/lang/StringBuffer;
    //   713: pop
    //   714: aload_3
    //   715: iload #16
    //   717: iload #11
    //   719: iadd
    //   720: invokevirtual setLength : (I)V
    //   723: iload #9
    //   725: ifeq -> 750
    //   728: iconst_1
    //   729: istore #5
    //   731: aload_0
    //   732: aload_3
    //   733: aload #4
    //   735: iload #11
    //   737: iload #8
    //   739: iload #6
    //   741: iload #5
    //   743: iload #15
    //   745: invokespecial format : (Ljava/lang/StringBuffer;Ljava/text/FieldPosition;IIIII)V
    //   748: aload_3
    //   749: areturn
    //   750: iconst_0
    //   751: istore #5
    //   753: goto -> 731
  }
  
  public StringBuffer format(long paramLong, StringBuffer paramStringBuffer, FieldPosition paramFieldPosition) {
    format(IntNum.make(paramLong), paramStringBuffer, paramFieldPosition);
    return paramStringBuffer;
  }
  
  public StringBuffer format(Object paramObject, StringBuffer paramStringBuffer, FieldPosition paramFieldPosition) {
    RealNum realNum2 = RealNum.asRealNumOrNull(paramObject);
    RealNum realNum1 = realNum2;
    if (realNum2 == null) {
      if (paramObject instanceof Complex) {
        paramObject = paramObject.toString();
        int i = this.width - paramObject.length();
        while (true) {
          if (--i >= 0) {
            paramStringBuffer.append(' ');
            continue;
          } 
          paramStringBuffer.append((String)paramObject);
          return paramStringBuffer;
        } 
      } 
      realNum1 = (RealNum)paramObject;
    } 
    return format(realNum1.doubleValue(), paramStringBuffer, paramFieldPosition);
  }
  
  public void format(RealNum paramRealNum, StringBuffer paramStringBuffer, FieldPosition paramFieldPosition) {
    String str;
    if (paramRealNum instanceof RatNum) {
      int i = getMaximumFractionDigits();
      if (i >= 0) {
        RatNum ratNum = (RatNum)paramRealNum;
        boolean bool1 = ratNum.isNegative();
        paramRealNum = ratNum;
        if (bool1)
          paramRealNum = ratNum.rneg(); 
        int j = paramStringBuffer.length();
        boolean bool = true;
        if (bool1) {
          paramStringBuffer.append('-');
        } else if (this.showPlus) {
          paramStringBuffer.append('+');
        } else {
          bool = false;
        } 
        str = RealNum.toScaledInt((RatNum)paramRealNum, this.scale + i).toString();
        paramStringBuffer.append(str);
        int k = str.length();
        format(paramStringBuffer, paramFieldPosition, k, k - i, i, bool, j);
        return;
      } 
    } 
    format(str.doubleValue(), paramStringBuffer, paramFieldPosition);
  }
  
  public int getMaximumFractionDigits() {
    return this.d;
  }
  
  public int getMinimumIntegerDigits() {
    return this.i;
  }
  
  public Number parse(String paramString, ParsePosition paramParsePosition) {
    throw new Error("RealFixedFormat.parse - not implemented");
  }
  
  public Object parseObject(String paramString, ParsePosition paramParsePosition) {
    throw new Error("RealFixedFormat.parseObject - not implemented");
  }
  
  public void setMaximumFractionDigits(int paramInt) {
    this.d = paramInt;
  }
  
  public void setMinimumIntegerDigits(int paramInt) {
    this.i = paramInt;
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/gnu/math/FixedRealFormat.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */