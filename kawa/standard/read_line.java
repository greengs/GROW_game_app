package kawa.standard;

import gnu.text.LineBufferedReader;
import java.io.IOException;

public class read_line {
  public static Object apply(LineBufferedReader paramLineBufferedReader, String paramString) throws IOException {
    // Byte code:
    //   0: aload_0
    //   1: invokevirtual read : ()I
    //   4: ifge -> 13
    //   7: getstatic gnu/expr/Special.eof : Ljava/lang/Object;
    //   10: astore_0
    //   11: aload_0
    //   12: areturn
    //   13: aload_0
    //   14: getfield pos : I
    //   17: iconst_1
    //   18: isub
    //   19: istore #7
    //   21: aload_0
    //   22: getfield limit : I
    //   25: istore #8
    //   27: aload_0
    //   28: getfield buffer : [C
    //   31: astore #10
    //   33: iconst_m1
    //   34: istore #5
    //   36: iload #7
    //   38: istore_3
    //   39: iload_3
    //   40: iload #8
    //   42: if_icmpge -> 210
    //   45: iload_3
    //   46: iconst_1
    //   47: iadd
    //   48: istore #4
    //   50: aload #10
    //   52: iload_3
    //   53: caload
    //   54: istore #9
    //   56: iload #9
    //   58: bipush #13
    //   60: if_icmpeq -> 70
    //   63: iload #9
    //   65: bipush #10
    //   67: if_icmpne -> 447
    //   70: iload #4
    //   72: iconst_1
    //   73: isub
    //   74: istore #4
    //   76: aload_1
    //   77: ldc 'trim'
    //   79: if_acmpeq -> 88
    //   82: aload_1
    //   83: ldc 'peek'
    //   85: if_acmpne -> 168
    //   88: iload #5
    //   90: istore_3
    //   91: aload_1
    //   92: ldc 'peek'
    //   94: if_acmpne -> 99
    //   97: iconst_0
    //   98: istore_3
    //   99: iload #9
    //   101: bipush #10
    //   103: if_icmpne -> 133
    //   106: iconst_1
    //   107: istore_3
    //   108: aload_0
    //   109: iload #4
    //   111: iload_3
    //   112: iadd
    //   113: putfield pos : I
    //   116: new gnu/lists/FString
    //   119: dup
    //   120: aload #10
    //   122: iload #7
    //   124: iload #4
    //   126: iload #7
    //   128: isub
    //   129: invokespecial <init> : ([CII)V
    //   132: areturn
    //   133: iload #4
    //   135: istore #6
    //   137: iload #4
    //   139: iconst_1
    //   140: iadd
    //   141: iload #8
    //   143: if_icmpge -> 216
    //   146: aload #10
    //   148: iload #4
    //   150: iconst_1
    //   151: iadd
    //   152: caload
    //   153: bipush #10
    //   155: if_icmpne -> 163
    //   158: iconst_2
    //   159: istore_3
    //   160: goto -> 108
    //   163: iconst_1
    //   164: istore_3
    //   165: goto -> 160
    //   168: iload #5
    //   170: istore_3
    //   171: iload #4
    //   173: istore #6
    //   175: aload_1
    //   176: ldc 'concat'
    //   178: if_acmpne -> 216
    //   181: iload #5
    //   183: istore_3
    //   184: iload #4
    //   186: istore #6
    //   188: iload #9
    //   190: bipush #10
    //   192: if_icmpne -> 216
    //   195: iload #4
    //   197: iconst_1
    //   198: iadd
    //   199: istore #4
    //   201: aload_0
    //   202: iload #4
    //   204: putfield pos : I
    //   207: goto -> 116
    //   210: iload_3
    //   211: istore #6
    //   213: iload #5
    //   215: istore_3
    //   216: new java/lang/StringBuffer
    //   219: dup
    //   220: bipush #100
    //   222: invokespecial <init> : (I)V
    //   225: astore #11
    //   227: iload #6
    //   229: iload #7
    //   231: if_icmple -> 249
    //   234: aload #11
    //   236: aload #10
    //   238: iload #7
    //   240: iload #6
    //   242: iload #7
    //   244: isub
    //   245: invokevirtual append : ([CII)Ljava/lang/StringBuffer;
    //   248: pop
    //   249: aload_0
    //   250: iload #6
    //   252: putfield pos : I
    //   255: aload_1
    //   256: ldc 'peek'
    //   258: if_acmpne -> 356
    //   261: bipush #80
    //   263: istore_2
    //   264: aload_0
    //   265: aload #11
    //   267: iload_2
    //   268: invokevirtual readLine : (Ljava/lang/StringBuffer;C)V
    //   271: aload #11
    //   273: invokevirtual length : ()I
    //   276: istore #5
    //   278: iload #5
    //   280: istore #4
    //   282: aload_1
    //   283: ldc 'split'
    //   285: if_acmpne -> 299
    //   288: iload #5
    //   290: ifne -> 380
    //   293: iconst_0
    //   294: istore_3
    //   295: iload #5
    //   297: istore #4
    //   299: new gnu/lists/FString
    //   302: dup
    //   303: aload #11
    //   305: iconst_0
    //   306: iload #4
    //   308: invokespecial <init> : (Ljava/lang/StringBuffer;II)V
    //   311: astore #10
    //   313: aload #10
    //   315: astore_0
    //   316: aload_1
    //   317: ldc 'split'
    //   319: if_acmpne -> 11
    //   322: new gnu/mapping/Values
    //   325: dup
    //   326: iconst_2
    //   327: anewarray java/lang/Object
    //   330: dup
    //   331: iconst_0
    //   332: aload #10
    //   334: aastore
    //   335: dup
    //   336: iconst_1
    //   337: new gnu/lists/FString
    //   340: dup
    //   341: aload #11
    //   343: iload #4
    //   345: iload_3
    //   346: isub
    //   347: iload_3
    //   348: invokespecial <init> : (Ljava/lang/StringBuffer;II)V
    //   351: aastore
    //   352: invokespecial <init> : ([Ljava/lang/Object;)V
    //   355: areturn
    //   356: aload_1
    //   357: ldc 'concat'
    //   359: if_acmpeq -> 368
    //   362: aload_1
    //   363: ldc 'split'
    //   365: if_acmpne -> 374
    //   368: bipush #65
    //   370: istore_2
    //   371: goto -> 264
    //   374: bipush #73
    //   376: istore_2
    //   377: goto -> 264
    //   380: aload #11
    //   382: iload #5
    //   384: iconst_1
    //   385: isub
    //   386: invokevirtual charAt : (I)C
    //   389: istore_3
    //   390: iload_3
    //   391: bipush #13
    //   393: if_icmpne -> 407
    //   396: iconst_1
    //   397: istore_3
    //   398: iload #5
    //   400: iload_3
    //   401: isub
    //   402: istore #4
    //   404: goto -> 299
    //   407: iload_3
    //   408: bipush #10
    //   410: if_icmpeq -> 418
    //   413: iconst_0
    //   414: istore_3
    //   415: goto -> 398
    //   418: iload_3
    //   419: iconst_2
    //   420: if_icmple -> 442
    //   423: aload #11
    //   425: iload #5
    //   427: iconst_2
    //   428: isub
    //   429: invokevirtual charAt : (I)C
    //   432: bipush #13
    //   434: if_icmpne -> 442
    //   437: iconst_2
    //   438: istore_3
    //   439: goto -> 398
    //   442: iconst_1
    //   443: istore_3
    //   444: goto -> 398
    //   447: iload #4
    //   449: istore_3
    //   450: goto -> 39
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/kawa/standard/read_line.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */