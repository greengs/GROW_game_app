package gnu.kawa.util;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Hashtable;
import java.util.StringTokenizer;

public class PreProcess {
  static final String JAVA4_FEATURES = "+JAVA2 +use:java.util.IdentityHashMap +use:java.lang.CharSequence +use:java.lang.Throwable.getCause +use:java.net.URI +use:java.util.regex +SAX2 +use:java.nio";
  
  static final String JAVA5_FEATURES = "+JAVA5 +JAVA2 +use:java.util.IdentityHashMap +use:java.lang.CharSequence +use:java.lang.Throwable.getCause +use:java.net.URI +use:java.util.regex +SAX2 +use:java.nio +use:org.w3c.dom.Node +use:javax.xml.transform +JAXP-1.3 -JAXP-QName";
  
  static final String NO_JAVA4_FEATURES = "-JAVA5 -use:java.util.IdentityHashMap -use:java.lang.CharSequence -use:java.lang.Throwable.getCause -use:java.net.URI -use:java.util.regex -use:org.w3c.dom.Node -JAXP-1.3 -use:javax.xml.transform -JAVA5 -JAVA6 -JAVA6COMPAT5 -JAXP-QName -use:java.text.Normalizer -SAX2 -use:java.nio -Android";
  
  static final String NO_JAVA6_FEATURES = "-JAVA6 -JAVA7 -use:java.dyn -use:java.text.Normalizer";
  
  static String[] version_features = new String[] { 
      "java1", "-JAVA2 -JAVA5 -use:java.util.IdentityHashMap -use:java.lang.CharSequence -use:java.lang.Throwable.getCause -use:java.net.URI -use:java.util.regex -use:org.w3c.dom.Node -JAXP-1.3 -use:javax.xml.transform -JAVA5 -JAVA6 -JAVA6COMPAT5 -JAXP-QName -use:java.text.Normalizer -SAX2 -use:java.nio -Android -JAVA6 -JAVA7 -use:java.dyn -use:java.text.Normalizer", "java2", "+JAVA2 -JAVA5 -use:java.util.IdentityHashMap -use:java.lang.CharSequence -use:java.lang.Throwable.getCause -use:java.net.URI -use:java.util.regex -use:org.w3c.dom.Node -JAXP-1.3 -use:javax.xml.transform -JAVA5 -JAVA6 -JAVA6COMPAT5 -JAXP-QName -use:java.text.Normalizer -SAX2 -use:java.nio -Android -JAVA6 -JAVA7 -use:java.dyn -use:java.text.Normalizer", "java4", "-JAVA5 +JAVA2 +use:java.util.IdentityHashMap +use:java.lang.CharSequence +use:java.lang.Throwable.getCause +use:java.net.URI +use:java.util.regex +SAX2 +use:java.nio -use:org.w3c.dom.Node -JAXP-1.3 -use:javax.xml.transform -JAXP-QName -JAVA6COMPAT5 -Android -JAVA6 -JAVA7 -use:java.dyn -use:java.text.Normalizer", "java4x", "-JAVA5 +JAVA2 +use:java.util.IdentityHashMap +use:java.lang.CharSequence +use:java.lang.Throwable.getCause +use:java.net.URI +use:java.util.regex +SAX2 +use:java.nio +use:org.w3c.dom.Node +JAXP-1.3 +use:javax.xml.transform -JAXP-QName -JAVA6COMPAT5 -Android -JAVA6 -JAVA7 -use:java.dyn -use:java.text.Normalizer", "java5", "+JAVA5 +JAVA2 +use:java.util.IdentityHashMap +use:java.lang.CharSequence +use:java.lang.Throwable.getCause +use:java.net.URI +use:java.util.regex +SAX2 +use:java.nio +use:org.w3c.dom.Node +use:javax.xml.transform +JAXP-1.3 -JAXP-QName -JAVA6COMPAT5 -Android -JAVA6 -JAVA7 -use:java.dyn -use:java.text.Normalizer", 
      "java6compat5", "+JAVA5 +JAVA2 +use:java.util.IdentityHashMap +use:java.lang.CharSequence +use:java.lang.Throwable.getCause +use:java.net.URI +use:java.util.regex +SAX2 +use:java.nio +use:org.w3c.dom.Node +use:javax.xml.transform +JAXP-1.3 -JAXP-QName -JAVA6 -JAVA7 +JAVA6COMPAT5 +use:java.text.Normalizer -use:java.dyn -Android", "java6", "+JAVA5 +JAVA2 +use:java.util.IdentityHashMap +use:java.lang.CharSequence +use:java.lang.Throwable.getCause +use:java.net.URI +use:java.util.regex +SAX2 +use:java.nio +use:org.w3c.dom.Node +use:javax.xml.transform +JAXP-1.3 -JAXP-QName +JAVA6 -JAVA7 -JAVA6COMPAT5 +use:java.text.Normalizer -use:java.dyn -Android", "java7", "+JAVA5 +JAVA2 +use:java.util.IdentityHashMap +use:java.lang.CharSequence +use:java.lang.Throwable.getCause +use:java.net.URI +use:java.util.regex +SAX2 +use:java.nio +use:org.w3c.dom.Node +use:javax.xml.transform +JAXP-1.3 -JAXP-QName +JAVA6 +JAVA7 -JAVA6COMPAT5 +use:java.text.Normalizer +use:java.dyn -Android", "android", "+JAVA5 +JAVA2 +use:java.util.IdentityHashMap +use:java.lang.CharSequence +use:java.lang.Throwable.getCause +use:java.net.URI +use:java.util.regex +SAX2 +use:java.nio +use:org.w3c.dom.Node +JAXP-1.3 -JAXP-QName -use:javax.xml.transform -JAVA6 -JAVA6COMPAT5 +Android -JAVA6 -JAVA7 -use:java.dyn -use:java.text.Normalizer" };
  
  String filename;
  
  Hashtable keywords = new Hashtable<Object, Object>();
  
  int lineno;
  
  byte[] resultBuffer;
  
  int resultLength;
  
  public static void main(String[] paramArrayOfString) {
    PreProcess preProcess = new PreProcess();
    preProcess.keywords.put("true", Boolean.TRUE);
    preProcess.keywords.put("false", Boolean.FALSE);
    for (int i = 0; i < paramArrayOfString.length; i++)
      preProcess.handleArg(paramArrayOfString[i]); 
  }
  
  void error(String paramString) {
    System.err.println(this.filename + ':' + this.lineno + ": " + paramString);
    System.exit(-1);
  }
  
  public void filter(String paramString) throws Throwable {
    if (filter(paramString, new BufferedInputStream(new FileInputStream(paramString)))) {
      FileOutputStream fileOutputStream = new FileOutputStream(paramString);
      fileOutputStream.write(this.resultBuffer, 0, this.resultLength);
      fileOutputStream.close();
      System.err.println("Pre-processed " + paramString);
    } 
  }
  
  public boolean filter(String paramString, BufferedInputStream paramBufferedInputStream) throws Throwable {
    // Byte code:
    //   0: aload_0
    //   1: aload_1
    //   2: putfield filename : Ljava/lang/String;
    //   5: iconst_0
    //   6: istore #16
    //   8: sipush #2000
    //   11: newarray byte
    //   13: astore #20
    //   15: iconst_0
    //   16: istore #4
    //   18: iconst_0
    //   19: istore #12
    //   21: iconst_m1
    //   22: istore #6
    //   24: iconst_0
    //   25: istore #15
    //   27: aload_0
    //   28: iconst_1
    //   29: putfield lineno : I
    //   32: iconst_m1
    //   33: istore #11
    //   35: iconst_0
    //   36: istore_3
    //   37: iconst_0
    //   38: istore #10
    //   40: iconst_0
    //   41: istore #9
    //   43: aconst_null
    //   44: astore #21
    //   46: iconst_0
    //   47: istore #7
    //   49: aload_2
    //   50: invokevirtual read : ()I
    //   53: istore #13
    //   55: iload #13
    //   57: ifge -> 114
    //   60: aload #20
    //   62: astore #19
    //   64: iload #10
    //   66: ifeq -> 99
    //   69: aload_0
    //   70: iload #15
    //   72: putfield lineno : I
    //   75: aload_0
    //   76: new java/lang/StringBuilder
    //   79: dup
    //   80: invokespecial <init> : ()V
    //   83: ldc 'unterminated '
    //   85: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   88: aload #21
    //   90: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   93: invokevirtual toString : ()Ljava/lang/String;
    //   96: invokevirtual error : (Ljava/lang/String;)V
    //   99: aload_0
    //   100: aload #19
    //   102: putfield resultBuffer : [B
    //   105: aload_0
    //   106: iload #4
    //   108: putfield resultLength : I
    //   111: iload #16
    //   113: ireturn
    //   114: aload #20
    //   116: astore #19
    //   118: iload #4
    //   120: bipush #10
    //   122: iadd
    //   123: aload #20
    //   125: arraylength
    //   126: if_icmplt -> 148
    //   129: iload #4
    //   131: iconst_2
    //   132: imul
    //   133: newarray byte
    //   135: astore #19
    //   137: aload #20
    //   139: iconst_0
    //   140: aload #19
    //   142: iconst_0
    //   143: iload #4
    //   145: invokestatic arraycopy : (Ljava/lang/Object;ILjava/lang/Object;II)V
    //   148: iload #13
    //   150: bipush #10
    //   152: if_icmpne -> 193
    //   155: iload #4
    //   157: ifle -> 193
    //   160: aload #19
    //   162: iload #4
    //   164: iconst_1
    //   165: isub
    //   166: baload
    //   167: bipush #13
    //   169: if_icmpne -> 193
    //   172: aload #19
    //   174: iload #4
    //   176: iload #13
    //   178: i2b
    //   179: bastore
    //   180: iload #4
    //   182: iconst_1
    //   183: iadd
    //   184: istore #4
    //   186: aload #19
    //   188: astore #20
    //   190: goto -> 49
    //   193: iload #11
    //   195: iflt -> 1732
    //   198: iload #6
    //   200: ifge -> 1732
    //   203: iload #7
    //   205: ifgt -> 1732
    //   208: iload #13
    //   210: bipush #13
    //   212: if_icmpeq -> 1732
    //   215: iload #13
    //   217: bipush #10
    //   219: if_icmpeq -> 1732
    //   222: iload #11
    //   224: iload_3
    //   225: if_icmpeq -> 242
    //   228: iload #13
    //   230: bipush #32
    //   232: if_icmpeq -> 1732
    //   235: iload #13
    //   237: bipush #9
    //   239: if_icmpeq -> 1732
    //   242: iload #13
    //   244: bipush #47
    //   246: if_icmpne -> 528
    //   249: aload_2
    //   250: bipush #100
    //   252: invokevirtual mark : (I)V
    //   255: aload_2
    //   256: invokevirtual read : ()I
    //   259: istore #5
    //   261: iload #5
    //   263: bipush #47
    //   265: if_icmpne -> 476
    //   268: iconst_0
    //   269: istore #5
    //   271: aload_2
    //   272: invokevirtual reset : ()V
    //   275: iload #5
    //   277: ifeq -> 1732
    //   280: iload #4
    //   282: iconst_1
    //   283: iadd
    //   284: istore #5
    //   286: aload #19
    //   288: iload #4
    //   290: bipush #47
    //   292: bastore
    //   293: iload #5
    //   295: iconst_1
    //   296: iadd
    //   297: istore #7
    //   299: aload #19
    //   301: iload #5
    //   303: bipush #47
    //   305: bastore
    //   306: iload #7
    //   308: iconst_1
    //   309: iadd
    //   310: istore #4
    //   312: aload #19
    //   314: iload #7
    //   316: bipush #32
    //   318: bastore
    //   319: iconst_1
    //   320: istore #5
    //   322: iconst_1
    //   323: istore #16
    //   325: iload #13
    //   327: istore #7
    //   329: iload #16
    //   331: istore #17
    //   333: iload #5
    //   335: istore #14
    //   337: iload #6
    //   339: istore #8
    //   341: iload #13
    //   343: bipush #32
    //   345: if_icmpeq -> 1721
    //   348: iload #13
    //   350: istore #7
    //   352: iload #16
    //   354: istore #17
    //   356: iload #5
    //   358: istore #14
    //   360: iload #6
    //   362: istore #8
    //   364: iload #13
    //   366: bipush #9
    //   368: if_icmpeq -> 1721
    //   371: iload #13
    //   373: istore #7
    //   375: iload #16
    //   377: istore #17
    //   379: iload #5
    //   381: istore #14
    //   383: iload #6
    //   385: istore #8
    //   387: iload #6
    //   389: ifge -> 1721
    //   392: iload #4
    //   394: istore #6
    //   396: iload #13
    //   398: istore #7
    //   400: iload #16
    //   402: istore #17
    //   404: iload #5
    //   406: istore #14
    //   408: iload #6
    //   410: istore #8
    //   412: iload #10
    //   414: ifle -> 1721
    //   417: iload #13
    //   419: istore #7
    //   421: iload #16
    //   423: istore #17
    //   425: iload #5
    //   427: istore #14
    //   429: iload #6
    //   431: istore #8
    //   433: iload #11
    //   435: iload_3
    //   436: if_icmpeq -> 1721
    //   439: iload #13
    //   441: istore #7
    //   443: iload #16
    //   445: istore #17
    //   447: iload #5
    //   449: istore #14
    //   451: iload #6
    //   453: istore #8
    //   455: iload #13
    //   457: bipush #47
    //   459: if_icmpne -> 1721
    //   462: aload_2
    //   463: invokevirtual read : ()I
    //   466: istore #13
    //   468: iload #13
    //   470: ifge -> 534
    //   473: goto -> 64
    //   476: iload #5
    //   478: bipush #42
    //   480: if_icmpne -> 522
    //   483: aload_2
    //   484: invokevirtual read : ()I
    //   487: istore #5
    //   489: iload #5
    //   491: bipush #32
    //   493: if_icmpeq -> 483
    //   496: iload #5
    //   498: bipush #9
    //   500: if_icmpeq -> 483
    //   503: iload #5
    //   505: bipush #35
    //   507: if_icmpeq -> 516
    //   510: iconst_1
    //   511: istore #5
    //   513: goto -> 271
    //   516: iconst_0
    //   517: istore #5
    //   519: goto -> 513
    //   522: iconst_1
    //   523: istore #5
    //   525: goto -> 271
    //   528: iconst_1
    //   529: istore #5
    //   531: goto -> 275
    //   534: iload #13
    //   536: bipush #47
    //   538: if_icmpeq -> 695
    //   541: iload #4
    //   543: iconst_1
    //   544: iadd
    //   545: istore #7
    //   547: aload #19
    //   549: iload #4
    //   551: bipush #47
    //   553: bastore
    //   554: iload #7
    //   556: istore #4
    //   558: iload #6
    //   560: istore #8
    //   562: iload #16
    //   564: istore #17
    //   566: aload #19
    //   568: iload #4
    //   570: iload #13
    //   572: i2b
    //   573: bastore
    //   574: iload #4
    //   576: iconst_1
    //   577: iadd
    //   578: istore #14
    //   580: iload #13
    //   582: bipush #13
    //   584: if_icmpeq -> 594
    //   587: iload #13
    //   589: bipush #10
    //   591: if_icmpne -> 1651
    //   594: iconst_m1
    //   595: istore #5
    //   597: iconst_0
    //   598: istore #7
    //   600: iload #12
    //   602: istore #4
    //   604: iload #5
    //   606: istore #12
    //   608: iload #4
    //   610: iload #14
    //   612: iconst_1
    //   613: isub
    //   614: if_icmpge -> 787
    //   617: iload #12
    //   619: istore #6
    //   621: iload #7
    //   623: istore #5
    //   625: aload #19
    //   627: iload #4
    //   629: baload
    //   630: bipush #32
    //   632: if_icmpeq -> 678
    //   635: iload #12
    //   637: istore #6
    //   639: iload #7
    //   641: istore #5
    //   643: aload #19
    //   645: iload #4
    //   647: baload
    //   648: bipush #9
    //   650: if_icmpeq -> 678
    //   653: iload #4
    //   655: istore #7
    //   657: iload #12
    //   659: istore #6
    //   661: iload #7
    //   663: istore #5
    //   665: iload #12
    //   667: ifge -> 678
    //   670: iload #4
    //   672: istore #6
    //   674: iload #7
    //   676: istore #5
    //   678: iload #4
    //   680: iconst_1
    //   681: iadd
    //   682: istore #4
    //   684: iload #6
    //   686: istore #12
    //   688: iload #5
    //   690: istore #7
    //   692: goto -> 608
    //   695: aload_2
    //   696: invokevirtual read : ()I
    //   699: istore #13
    //   701: iload #13
    //   703: ifge -> 709
    //   706: goto -> 64
    //   709: iconst_m1
    //   710: istore #5
    //   712: iconst_1
    //   713: istore #18
    //   715: iconst_1
    //   716: istore #16
    //   718: iload #13
    //   720: istore #7
    //   722: iload #18
    //   724: istore #17
    //   726: iload #5
    //   728: istore #14
    //   730: iload #6
    //   732: istore #8
    //   734: iload #13
    //   736: bipush #32
    //   738: if_icmpne -> 1721
    //   741: aload_2
    //   742: invokevirtual read : ()I
    //   745: istore #13
    //   747: iload #13
    //   749: bipush #32
    //   751: if_icmpeq -> 777
    //   754: iload #13
    //   756: istore #7
    //   758: iload #18
    //   760: istore #17
    //   762: iload #5
    //   764: istore #14
    //   766: iload #6
    //   768: istore #8
    //   770: iload #13
    //   772: bipush #9
    //   774: if_icmpne -> 1721
    //   777: iconst_m1
    //   778: istore #8
    //   780: iload #16
    //   782: istore #17
    //   784: goto -> 566
    //   787: aload #21
    //   789: astore #22
    //   791: iload #15
    //   793: istore #6
    //   795: iload #11
    //   797: istore #4
    //   799: iload #10
    //   801: istore #8
    //   803: iload #9
    //   805: istore #5
    //   807: iload #7
    //   809: iload #12
    //   811: isub
    //   812: iconst_4
    //   813: if_icmplt -> 1215
    //   816: aload #21
    //   818: astore #22
    //   820: iload #15
    //   822: istore #6
    //   824: iload #11
    //   826: istore #4
    //   828: iload #10
    //   830: istore #8
    //   832: iload #9
    //   834: istore #5
    //   836: aload #19
    //   838: iload #12
    //   840: baload
    //   841: bipush #47
    //   843: if_icmpne -> 1215
    //   846: aload #21
    //   848: astore #22
    //   850: iload #15
    //   852: istore #6
    //   854: iload #11
    //   856: istore #4
    //   858: iload #10
    //   860: istore #8
    //   862: iload #9
    //   864: istore #5
    //   866: aload #19
    //   868: iload #12
    //   870: iconst_1
    //   871: iadd
    //   872: baload
    //   873: bipush #42
    //   875: if_icmpne -> 1215
    //   878: aload #21
    //   880: astore #22
    //   882: iload #15
    //   884: istore #6
    //   886: iload #11
    //   888: istore #4
    //   890: iload #10
    //   892: istore #8
    //   894: iload #9
    //   896: istore #5
    //   898: aload #19
    //   900: iload #7
    //   902: iconst_1
    //   903: isub
    //   904: baload
    //   905: bipush #42
    //   907: if_icmpne -> 1215
    //   910: aload #21
    //   912: astore #22
    //   914: iload #15
    //   916: istore #6
    //   918: iload #11
    //   920: istore #4
    //   922: iload #10
    //   924: istore #8
    //   926: iload #9
    //   928: istore #5
    //   930: aload #19
    //   932: iload #7
    //   934: baload
    //   935: bipush #47
    //   937: if_icmpne -> 1215
    //   940: iload #12
    //   942: iconst_2
    //   943: iadd
    //   944: istore #12
    //   946: iload #12
    //   948: iload #7
    //   950: if_icmpge -> 972
    //   953: aload #19
    //   955: iload #12
    //   957: baload
    //   958: bipush #32
    //   960: if_icmpne -> 972
    //   963: iload #12
    //   965: iconst_1
    //   966: iadd
    //   967: istore #12
    //   969: goto -> 946
    //   972: iload #7
    //   974: iconst_2
    //   975: isub
    //   976: istore #7
    //   978: iload #7
    //   980: iload #12
    //   982: if_icmple -> 1004
    //   985: aload #19
    //   987: iload #7
    //   989: baload
    //   990: bipush #32
    //   992: if_icmpne -> 1004
    //   995: iload #7
    //   997: iconst_1
    //   998: isub
    //   999: istore #7
    //   1001: goto -> 978
    //   1004: aload #21
    //   1006: astore #22
    //   1008: iload #15
    //   1010: istore #6
    //   1012: iload #11
    //   1014: istore #4
    //   1016: iload #10
    //   1018: istore #8
    //   1020: iload #9
    //   1022: istore #5
    //   1024: aload #19
    //   1026: iload #12
    //   1028: baload
    //   1029: bipush #35
    //   1031: if_icmpne -> 1215
    //   1034: new java/lang/String
    //   1037: dup
    //   1038: aload #19
    //   1040: iload #12
    //   1042: iload #7
    //   1044: iload #12
    //   1046: isub
    //   1047: iconst_1
    //   1048: iadd
    //   1049: ldc 'ISO-8859-1'
    //   1051: invokespecial <init> : ([BIILjava/lang/String;)V
    //   1054: astore #23
    //   1056: aload #23
    //   1058: bipush #32
    //   1060: invokevirtual indexOf : (I)I
    //   1063: istore #4
    //   1065: aload_0
    //   1066: getfield lineno : I
    //   1069: istore #13
    //   1071: iload #4
    //   1073: ifle -> 1276
    //   1076: aload #23
    //   1078: iconst_0
    //   1079: iload #4
    //   1081: invokevirtual substring : (II)Ljava/lang/String;
    //   1084: astore #20
    //   1086: aload #23
    //   1088: iload #4
    //   1090: invokevirtual substring : (I)Ljava/lang/String;
    //   1093: invokevirtual trim : ()Ljava/lang/String;
    //   1096: astore #22
    //   1098: aload_0
    //   1099: getfield keywords : Ljava/util/Hashtable;
    //   1102: aload #22
    //   1104: invokevirtual get : (Ljava/lang/Object;)Ljava/lang/Object;
    //   1107: astore #21
    //   1109: ldc '#ifdef'
    //   1111: aload #20
    //   1113: invokevirtual equals : (Ljava/lang/Object;)Z
    //   1116: ifne -> 1129
    //   1119: ldc '#ifndef'
    //   1121: aload #20
    //   1123: invokevirtual equals : (Ljava/lang/Object;)Z
    //   1126: ifeq -> 1376
    //   1129: aload #21
    //   1131: astore #23
    //   1133: aload #21
    //   1135: ifnonnull -> 1185
    //   1138: getstatic java/lang/System.err : Ljava/io/PrintStream;
    //   1141: new java/lang/StringBuilder
    //   1144: dup
    //   1145: invokespecial <init> : ()V
    //   1148: aload_1
    //   1149: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1152: ldc ':'
    //   1154: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1157: aload_0
    //   1158: getfield lineno : I
    //   1161: invokevirtual append : (I)Ljava/lang/StringBuilder;
    //   1164: ldc ': warning - undefined keyword: '
    //   1166: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1169: aload #22
    //   1171: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1174: invokevirtual toString : ()Ljava/lang/String;
    //   1177: invokevirtual println : (Ljava/lang/String;)V
    //   1180: getstatic java/lang/Boolean.FALSE : Ljava/lang/Boolean;
    //   1183: astore #23
    //   1185: iload #10
    //   1187: iconst_1
    //   1188: iadd
    //   1189: istore #7
    //   1191: iload #9
    //   1193: ifle -> 1290
    //   1196: iload #9
    //   1198: istore #5
    //   1200: iload #7
    //   1202: istore #8
    //   1204: iload_3
    //   1205: istore #4
    //   1207: iload #13
    //   1209: istore #6
    //   1211: aload #20
    //   1213: astore #22
    //   1215: iload #14
    //   1217: istore #12
    //   1219: iconst_m1
    //   1220: istore #9
    //   1222: iconst_0
    //   1223: istore_3
    //   1224: aload_0
    //   1225: aload_0
    //   1226: getfield lineno : I
    //   1229: iconst_1
    //   1230: iadd
    //   1231: putfield lineno : I
    //   1234: iconst_0
    //   1235: istore #7
    //   1237: aload #19
    //   1239: astore #20
    //   1241: iload #17
    //   1243: istore #16
    //   1245: aload #22
    //   1247: astore #21
    //   1249: iload #6
    //   1251: istore #15
    //   1253: iload #4
    //   1255: istore #11
    //   1257: iload #9
    //   1259: istore #6
    //   1261: iload #14
    //   1263: istore #4
    //   1265: iload #8
    //   1267: istore #10
    //   1269: iload #5
    //   1271: istore #9
    //   1273: goto -> 49
    //   1276: aload #23
    //   1278: astore #20
    //   1280: ldc ''
    //   1282: astore #22
    //   1284: aconst_null
    //   1285: astore #21
    //   1287: goto -> 1109
    //   1290: aload #20
    //   1292: iconst_3
    //   1293: invokevirtual charAt : (I)C
    //   1296: bipush #110
    //   1298: if_icmpne -> 1364
    //   1301: iconst_1
    //   1302: istore #10
    //   1304: aload #23
    //   1306: getstatic java/lang/Boolean.FALSE : Ljava/lang/Boolean;
    //   1309: if_acmpne -> 1370
    //   1312: iconst_1
    //   1313: istore #12
    //   1315: aload #20
    //   1317: astore #22
    //   1319: iload #13
    //   1321: istore #6
    //   1323: iload #11
    //   1325: istore #4
    //   1327: iload #7
    //   1329: istore #8
    //   1331: iload #9
    //   1333: istore #5
    //   1335: iload #10
    //   1337: iload #12
    //   1339: if_icmpeq -> 1215
    //   1342: iload #7
    //   1344: istore #5
    //   1346: aload #20
    //   1348: astore #22
    //   1350: iload #13
    //   1352: istore #6
    //   1354: iload_3
    //   1355: istore #4
    //   1357: iload #7
    //   1359: istore #8
    //   1361: goto -> 1215
    //   1364: iconst_0
    //   1365: istore #10
    //   1367: goto -> 1304
    //   1370: iconst_0
    //   1371: istore #12
    //   1373: goto -> 1315
    //   1376: ldc '#else'
    //   1378: aload #20
    //   1380: invokevirtual equals : (Ljava/lang/Object;)Z
    //   1383: ifeq -> 1512
    //   1386: iload #10
    //   1388: ifne -> 1438
    //   1391: aload_0
    //   1392: new java/lang/StringBuilder
    //   1395: dup
    //   1396: invokespecial <init> : ()V
    //   1399: ldc 'unexpected '
    //   1401: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1404: aload #20
    //   1406: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1409: invokevirtual toString : ()Ljava/lang/String;
    //   1412: invokevirtual error : (Ljava/lang/String;)V
    //   1415: aload #20
    //   1417: astore #22
    //   1419: iload #13
    //   1421: istore #6
    //   1423: iload #11
    //   1425: istore #4
    //   1427: iload #10
    //   1429: istore #8
    //   1431: iload #9
    //   1433: istore #5
    //   1435: goto -> 1215
    //   1438: iload #10
    //   1440: iload #9
    //   1442: if_icmpne -> 1466
    //   1445: iconst_m1
    //   1446: istore #4
    //   1448: iconst_0
    //   1449: istore #5
    //   1451: aload #20
    //   1453: astore #22
    //   1455: iload #13
    //   1457: istore #6
    //   1459: iload #10
    //   1461: istore #8
    //   1463: goto -> 1215
    //   1466: aload #20
    //   1468: astore #22
    //   1470: iload #13
    //   1472: istore #6
    //   1474: iload_3
    //   1475: istore #4
    //   1477: iload #10
    //   1479: istore #8
    //   1481: iload #9
    //   1483: istore #5
    //   1485: iload #9
    //   1487: ifne -> 1215
    //   1490: iload #10
    //   1492: istore #5
    //   1494: aload #20
    //   1496: astore #22
    //   1498: iload #13
    //   1500: istore #6
    //   1502: iload_3
    //   1503: istore #4
    //   1505: iload #10
    //   1507: istore #8
    //   1509: goto -> 1215
    //   1512: ldc '#endif'
    //   1514: aload #20
    //   1516: invokevirtual equals : (Ljava/lang/Object;)Z
    //   1519: ifeq -> 1604
    //   1522: iload #10
    //   1524: ifne -> 1551
    //   1527: aload_0
    //   1528: new java/lang/StringBuilder
    //   1531: dup
    //   1532: invokespecial <init> : ()V
    //   1535: ldc 'unexpected '
    //   1537: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1540: aload #20
    //   1542: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1545: invokevirtual toString : ()Ljava/lang/String;
    //   1548: invokevirtual error : (Ljava/lang/String;)V
    //   1551: iload #10
    //   1553: iload #9
    //   1555: if_icmpne -> 1581
    //   1558: iconst_0
    //   1559: istore #5
    //   1561: iconst_m1
    //   1562: istore #4
    //   1564: iload #10
    //   1566: iconst_1
    //   1567: isub
    //   1568: istore #8
    //   1570: aload #20
    //   1572: astore #22
    //   1574: iload #13
    //   1576: istore #6
    //   1578: goto -> 1215
    //   1581: iload #11
    //   1583: istore #4
    //   1585: iload #9
    //   1587: istore #5
    //   1589: iload #9
    //   1591: ifle -> 1564
    //   1594: iload_3
    //   1595: istore #4
    //   1597: iload #9
    //   1599: istore #5
    //   1601: goto -> 1564
    //   1604: aload_0
    //   1605: new java/lang/StringBuilder
    //   1608: dup
    //   1609: invokespecial <init> : ()V
    //   1612: ldc 'unknown command: '
    //   1614: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1617: aload #23
    //   1619: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1622: invokevirtual toString : ()Ljava/lang/String;
    //   1625: invokevirtual error : (Ljava/lang/String;)V
    //   1628: aload #20
    //   1630: astore #22
    //   1632: iload #13
    //   1634: istore #6
    //   1636: iload #11
    //   1638: istore #4
    //   1640: iload #10
    //   1642: istore #8
    //   1644: iload #9
    //   1646: istore #5
    //   1648: goto -> 1215
    //   1651: aload #19
    //   1653: astore #20
    //   1655: iload #17
    //   1657: istore #16
    //   1659: iload #5
    //   1661: istore #7
    //   1663: iload #8
    //   1665: istore #6
    //   1667: iload #14
    //   1669: istore #4
    //   1671: iload #8
    //   1673: ifge -> 49
    //   1676: iload #13
    //   1678: bipush #9
    //   1680: if_icmpne -> 1714
    //   1683: iload_3
    //   1684: bipush #8
    //   1686: iadd
    //   1687: bipush #-8
    //   1689: iand
    //   1690: istore_3
    //   1691: aload #19
    //   1693: astore #20
    //   1695: iload #17
    //   1697: istore #16
    //   1699: iload #5
    //   1701: istore #7
    //   1703: iload #8
    //   1705: istore #6
    //   1707: iload #14
    //   1709: istore #4
    //   1711: goto -> 49
    //   1714: iload_3
    //   1715: iconst_1
    //   1716: iadd
    //   1717: istore_3
    //   1718: goto -> 1691
    //   1721: iload #7
    //   1723: istore #13
    //   1725: iload #14
    //   1727: istore #5
    //   1729: goto -> 566
    //   1732: iload #7
    //   1734: istore #5
    //   1736: goto -> 325
  }
  
  void handleArg(String paramString) {
    StringTokenizer stringTokenizer;
    int i = 1;
    if (paramString.charAt(0) == '%') {
      paramString = paramString.substring(1);
      for (i = 0;; i += 2) {
        if (i >= version_features.length) {
          System.err.println("Unknown version: " + paramString);
          System.exit(-1);
        } 
        if (paramString.equals(version_features[i])) {
          String str = version_features[i + 1];
          System.err.println("(variant " + paramString + " maps to: " + str + ")");
          stringTokenizer = new StringTokenizer(str);
          while (stringTokenizer.hasMoreTokens())
            handleArg(stringTokenizer.nextToken()); 
          break;
        } 
      } 
    } else {
      Boolean bool;
      if (stringTokenizer.charAt(0) == '+') {
        this.keywords.put(stringTokenizer.substring(1), Boolean.TRUE);
        return;
      } 
      if (stringTokenizer.charAt(0) == '-') {
        int j = stringTokenizer.indexOf('=');
        if (j > 1) {
          if (stringTokenizer.charAt(1) == '-')
            i = 2; 
          String str1 = stringTokenizer.substring(i, j);
          String str2 = stringTokenizer.substring(j + 1);
          Boolean bool1 = Boolean.FALSE;
          if (str2.equalsIgnoreCase("true")) {
            bool = Boolean.TRUE;
          } else {
            bool = bool1;
            if (!str2.equalsIgnoreCase("false")) {
              System.err.println("invalid value " + str2 + " for " + str1);
              System.exit(-1);
              bool = bool1;
            } 
          } 
          this.keywords.put(str1, bool);
          return;
        } 
        this.keywords.put(bool.substring(1), Boolean.FALSE);
        return;
      } 
      try {
        filter((String)bool);
        return;
      } catch (Throwable throwable) {
        System.err.println("caught " + throwable);
        throwable.printStackTrace();
        System.exit(-1);
        return;
      } 
    } 
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/gnu/kawa/util/PreProcess.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */