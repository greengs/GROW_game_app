package gnu.kawa.functions;

import gnu.text.ReportFormat;
import java.io.IOException;
import java.io.Writer;
import java.text.FieldPosition;
import java.text.Format;

class LispRealFormat extends ReportFormat {
  int arg1;
  
  int arg2;
  
  int arg3;
  
  int arg4;
  
  int arg5;
  
  int arg6;
  
  int arg7;
  
  int argsUsed;
  
  boolean internalPad;
  
  char op;
  
  boolean showPlus;
  
  LispRealFormat() {
    boolean bool;
    if (this.arg1 == -1342177280 || this.arg2 == -1342177280 || this.arg3 == -1342177280 || this.arg4 == -1342177280 || this.arg5 == -1342177280 || this.arg6 == -1342177280 || this.arg7 == -1342177280) {
      bool = true;
    } else {
      bool = false;
    } 
    this.argsUsed = bool;
    if (this.arg1 == -1610612736)
      this.argsUsed += 2; 
    if (this.arg2 == -1610612736)
      this.argsUsed += 2; 
    if (this.arg3 == -1610612736)
      this.argsUsed += 2; 
    if (this.arg4 == -1610612736)
      this.argsUsed += 2; 
    if (this.arg5 == -1610612736)
      this.argsUsed += 2; 
    if (this.arg6 == -1610612736)
      this.argsUsed += 2; 
    if (this.arg7 == -1610612736)
      this.argsUsed += 2; 
  }
  
  public int format(Object[] paramArrayOfObject, int paramInt, Writer paramWriter, FieldPosition paramFieldPosition) throws IOException {
    StringBuffer stringBuffer = new StringBuffer(100);
    Format format = resolve(paramArrayOfObject, paramInt);
    paramInt += this.argsUsed >> 1;
    format.format(paramArrayOfObject[paramInt], stringBuffer, paramFieldPosition);
    paramWriter.write(stringBuffer.toString());
    return paramInt + 1;
  }
  
  public Format resolve(Object[] paramArrayOfObject, int paramInt) {
    // Byte code:
    //   0: aload_0
    //   1: getfield op : C
    //   4: bipush #36
    //   6: if_icmpne -> 176
    //   9: new gnu/math/FixedRealFormat
    //   12: dup
    //   13: invokespecial <init> : ()V
    //   16: astore #9
    //   18: aload_0
    //   19: getfield arg1 : I
    //   22: iconst_2
    //   23: aload_1
    //   24: iload_2
    //   25: invokestatic getParam : (II[Ljava/lang/Object;I)I
    //   28: istore #5
    //   30: iload_2
    //   31: istore #4
    //   33: aload_0
    //   34: getfield arg1 : I
    //   37: ldc -1610612736
    //   39: if_icmpne -> 47
    //   42: iload_2
    //   43: iconst_1
    //   44: iadd
    //   45: istore #4
    //   47: aload_0
    //   48: getfield arg2 : I
    //   51: iconst_1
    //   52: aload_1
    //   53: iload #4
    //   55: invokestatic getParam : (II[Ljava/lang/Object;I)I
    //   58: istore #6
    //   60: iload #4
    //   62: istore_2
    //   63: aload_0
    //   64: getfield arg2 : I
    //   67: ldc -1610612736
    //   69: if_icmpne -> 77
    //   72: iload #4
    //   74: iconst_1
    //   75: iadd
    //   76: istore_2
    //   77: aload_0
    //   78: getfield arg3 : I
    //   81: iconst_0
    //   82: aload_1
    //   83: iload_2
    //   84: invokestatic getParam : (II[Ljava/lang/Object;I)I
    //   87: istore #7
    //   89: iload_2
    //   90: istore #4
    //   92: aload_0
    //   93: getfield arg3 : I
    //   96: ldc -1610612736
    //   98: if_icmpne -> 106
    //   101: iload_2
    //   102: iconst_1
    //   103: iadd
    //   104: istore #4
    //   106: aload_0
    //   107: getfield arg4 : I
    //   110: bipush #32
    //   112: aload_1
    //   113: iload #4
    //   115: invokestatic getParam : (IC[Ljava/lang/Object;I)C
    //   118: istore_3
    //   119: aload_0
    //   120: getfield arg4 : I
    //   123: ldc -1610612736
    //   125: if_icmpne -> 128
    //   128: aload #9
    //   130: iload #5
    //   132: invokevirtual setMaximumFractionDigits : (I)V
    //   135: aload #9
    //   137: iload #6
    //   139: invokevirtual setMinimumIntegerDigits : (I)V
    //   142: aload #9
    //   144: iload #7
    //   146: putfield width : I
    //   149: aload #9
    //   151: iload_3
    //   152: putfield padChar : C
    //   155: aload #9
    //   157: aload_0
    //   158: getfield internalPad : Z
    //   161: putfield internalPad : Z
    //   164: aload #9
    //   166: aload_0
    //   167: getfield showPlus : Z
    //   170: putfield showPlus : Z
    //   173: aload #9
    //   175: areturn
    //   176: aload_0
    //   177: getfield op : C
    //   180: bipush #70
    //   182: if_icmpne -> 390
    //   185: new gnu/math/FixedRealFormat
    //   188: dup
    //   189: invokespecial <init> : ()V
    //   192: astore #9
    //   194: aload_0
    //   195: getfield arg1 : I
    //   198: iconst_0
    //   199: aload_1
    //   200: iload_2
    //   201: invokestatic getParam : (II[Ljava/lang/Object;I)I
    //   204: istore #5
    //   206: iload_2
    //   207: istore #4
    //   209: aload_0
    //   210: getfield arg1 : I
    //   213: ldc -1610612736
    //   215: if_icmpne -> 223
    //   218: iload_2
    //   219: iconst_1
    //   220: iadd
    //   221: istore #4
    //   223: aload_0
    //   224: getfield arg2 : I
    //   227: iconst_m1
    //   228: aload_1
    //   229: iload #4
    //   231: invokestatic getParam : (II[Ljava/lang/Object;I)I
    //   234: istore #6
    //   236: iload #4
    //   238: istore_2
    //   239: aload_0
    //   240: getfield arg2 : I
    //   243: ldc -1610612736
    //   245: if_icmpne -> 253
    //   248: iload #4
    //   250: iconst_1
    //   251: iadd
    //   252: istore_2
    //   253: aload_0
    //   254: getfield arg3 : I
    //   257: iconst_0
    //   258: aload_1
    //   259: iload_2
    //   260: invokestatic getParam : (II[Ljava/lang/Object;I)I
    //   263: istore #7
    //   265: iload_2
    //   266: istore #4
    //   268: aload_0
    //   269: getfield arg3 : I
    //   272: ldc -1610612736
    //   274: if_icmpne -> 282
    //   277: iload_2
    //   278: iconst_1
    //   279: iadd
    //   280: istore #4
    //   282: aload #9
    //   284: aload_0
    //   285: getfield arg4 : I
    //   288: iconst_0
    //   289: aload_1
    //   290: iload #4
    //   292: invokestatic getParam : (IC[Ljava/lang/Object;I)C
    //   295: putfield overflowChar : C
    //   298: iload #4
    //   300: istore_2
    //   301: aload_0
    //   302: getfield arg4 : I
    //   305: ldc -1610612736
    //   307: if_icmpne -> 315
    //   310: iload #4
    //   312: iconst_1
    //   313: iadd
    //   314: istore_2
    //   315: aload_0
    //   316: getfield arg5 : I
    //   319: bipush #32
    //   321: aload_1
    //   322: iload_2
    //   323: invokestatic getParam : (IC[Ljava/lang/Object;I)C
    //   326: istore_3
    //   327: aload_0
    //   328: getfield arg5 : I
    //   331: ldc -1610612736
    //   333: if_icmpne -> 336
    //   336: aload #9
    //   338: iload #6
    //   340: invokevirtual setMaximumFractionDigits : (I)V
    //   343: aload #9
    //   345: iconst_0
    //   346: invokevirtual setMinimumIntegerDigits : (I)V
    //   349: aload #9
    //   351: iload #5
    //   353: putfield width : I
    //   356: aload #9
    //   358: iload #7
    //   360: putfield scale : I
    //   363: aload #9
    //   365: iload_3
    //   366: putfield padChar : C
    //   369: aload #9
    //   371: aload_0
    //   372: getfield internalPad : Z
    //   375: putfield internalPad : Z
    //   378: aload #9
    //   380: aload_0
    //   381: getfield showPlus : Z
    //   384: putfield showPlus : Z
    //   387: aload #9
    //   389: areturn
    //   390: new gnu/math/ExponentialFormat
    //   393: dup
    //   394: invokespecial <init> : ()V
    //   397: astore #9
    //   399: aload #9
    //   401: iconst_1
    //   402: putfield exponentShowSign : Z
    //   405: aload #9
    //   407: aload_0
    //   408: getfield arg1 : I
    //   411: iconst_0
    //   412: aload_1
    //   413: iload_2
    //   414: invokestatic getParam : (II[Ljava/lang/Object;I)I
    //   417: putfield width : I
    //   420: iload_2
    //   421: istore #4
    //   423: aload_0
    //   424: getfield arg1 : I
    //   427: ldc -1610612736
    //   429: if_icmpne -> 437
    //   432: iload_2
    //   433: iconst_1
    //   434: iadd
    //   435: istore #4
    //   437: aload #9
    //   439: aload_0
    //   440: getfield arg2 : I
    //   443: iconst_m1
    //   444: aload_1
    //   445: iload #4
    //   447: invokestatic getParam : (II[Ljava/lang/Object;I)I
    //   450: putfield fracDigits : I
    //   453: iload #4
    //   455: istore_2
    //   456: aload_0
    //   457: getfield arg2 : I
    //   460: ldc -1610612736
    //   462: if_icmpne -> 470
    //   465: iload #4
    //   467: iconst_1
    //   468: iadd
    //   469: istore_2
    //   470: aload #9
    //   472: aload_0
    //   473: getfield arg3 : I
    //   476: iconst_0
    //   477: aload_1
    //   478: iload_2
    //   479: invokestatic getParam : (II[Ljava/lang/Object;I)I
    //   482: putfield expDigits : I
    //   485: iload_2
    //   486: istore #4
    //   488: aload_0
    //   489: getfield arg3 : I
    //   492: ldc -1610612736
    //   494: if_icmpne -> 502
    //   497: iload_2
    //   498: iconst_1
    //   499: iadd
    //   500: istore #4
    //   502: aload #9
    //   504: aload_0
    //   505: getfield arg4 : I
    //   508: iconst_1
    //   509: aload_1
    //   510: iload #4
    //   512: invokestatic getParam : (II[Ljava/lang/Object;I)I
    //   515: putfield intDigits : I
    //   518: iload #4
    //   520: istore_2
    //   521: aload_0
    //   522: getfield arg4 : I
    //   525: ldc -1610612736
    //   527: if_icmpne -> 535
    //   530: iload #4
    //   532: iconst_1
    //   533: iadd
    //   534: istore_2
    //   535: aload #9
    //   537: aload_0
    //   538: getfield arg5 : I
    //   541: iconst_0
    //   542: aload_1
    //   543: iload_2
    //   544: invokestatic getParam : (IC[Ljava/lang/Object;I)C
    //   547: putfield overflowChar : C
    //   550: iload_2
    //   551: istore #4
    //   553: aload_0
    //   554: getfield arg5 : I
    //   557: ldc -1610612736
    //   559: if_icmpne -> 567
    //   562: iload_2
    //   563: iconst_1
    //   564: iadd
    //   565: istore #4
    //   567: aload #9
    //   569: aload_0
    //   570: getfield arg6 : I
    //   573: bipush #32
    //   575: aload_1
    //   576: iload #4
    //   578: invokestatic getParam : (IC[Ljava/lang/Object;I)C
    //   581: putfield padChar : C
    //   584: iload #4
    //   586: istore_2
    //   587: aload_0
    //   588: getfield arg6 : I
    //   591: ldc -1610612736
    //   593: if_icmpne -> 601
    //   596: iload #4
    //   598: iconst_1
    //   599: iadd
    //   600: istore_2
    //   601: aload #9
    //   603: aload_0
    //   604: getfield arg7 : I
    //   607: bipush #69
    //   609: aload_1
    //   610: iload_2
    //   611: invokestatic getParam : (IC[Ljava/lang/Object;I)C
    //   614: putfield exponentChar : C
    //   617: aload_0
    //   618: getfield arg7 : I
    //   621: ldc -1610612736
    //   623: if_icmpne -> 626
    //   626: aload_0
    //   627: getfield op : C
    //   630: bipush #71
    //   632: if_icmpne -> 657
    //   635: iconst_1
    //   636: istore #8
    //   638: aload #9
    //   640: iload #8
    //   642: putfield general : Z
    //   645: aload #9
    //   647: aload_0
    //   648: getfield showPlus : Z
    //   651: putfield showPlus : Z
    //   654: aload #9
    //   656: areturn
    //   657: iconst_0
    //   658: istore #8
    //   660: goto -> 638
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/gnu/kawa/functions/LispRealFormat.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */