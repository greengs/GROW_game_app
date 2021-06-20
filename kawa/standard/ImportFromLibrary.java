package kawa.standard;

import gnu.expr.Expression;
import gnu.expr.ScopeExp;
import gnu.lists.Pair;
import java.util.Vector;
import kawa.lang.Syntax;
import kawa.lang.Translator;

public class ImportFromLibrary extends Syntax {
  private static final String BUILTIN = "";
  
  private static final String MISSING;
  
  static final String[][] SRFI97Map;
  
  public static final ImportFromLibrary instance = new ImportFromLibrary();
  
  public String[] classPrefixPath = new String[] { "", "kawa.lib." };
  
  static {
    MISSING = null;
    String[] arrayOfString1 = { "1", "lists", "gnu.kawa.slib.srfi1" };
    String[] arrayOfString2 = { "2", "and-let*", "gnu.kawa.slib.srfi2" };
    String[] arrayOfString3 = { "5", "let", MISSING };
    String[] arrayOfString4 = { "6", "basic-string-ports", "" };
    String[] arrayOfString5 = { "8", "receive", "" };
    String[] arrayOfString6 = { "9", "records", "" };
    String[] arrayOfString7 = { "14", "char-sets", MISSING };
    String[] arrayOfString8 = { "18", "multithreading", MISSING };
    String str1 = MISSING;
    String[] arrayOfString9 = { "21", "real-time-multithreading", MISSING };
    String[] arrayOfString10 = { "26", "cut", "" };
    String[] arrayOfString11 = { "27", "random-bits", MISSING };
    String[] arrayOfString12 = { "28", "basic-format-strings", "" };
    String[] arrayOfString13 = { "29", "localization", MISSING };
    String str2 = MISSING;
    String str3 = MISSING;
    String[] arrayOfString14 = { "41", "streams", MISSING };
    String str4 = MISSING;
    String str5 = MISSING;
    String str6 = MISSING;
    String str7 = MISSING;
    String str8 = MISSING;
    String str9 = MISSING;
    String[] arrayOfString15 = { "48", "intermediate-format-strings", MISSING };
    String str10 = MISSING;
    String str11 = MISSING;
    String str12 = MISSING;
    String[] arrayOfString16 = { "59", "vicinities", MISSING };
    String str13 = MISSING;
    String[] arrayOfString17 = { "61", "cond", MISSING };
    String[] arrayOfString18 = { "63", "arrays", MISSING };
    String str14 = MISSING;
    String str15 = MISSING;
    String str16 = MISSING;
    String[] arrayOfString19 = { "74", "blobs", MISSING };
    String str17 = MISSING;
    String[] arrayOfString20 = { "86", "mu-and-nu", MISSING };
    String[] arrayOfString21 = { "87", "case", MISSING };
    String[] arrayOfString22 = { "95", "sorting-and-merging", "kawa.lib.srfi95" };
    SRFI97Map = new String[][] { 
        arrayOfString1, arrayOfString2, arrayOfString3, arrayOfString4, arrayOfString5, arrayOfString6, { "11", "let-values", "" }, { "13", "strings", "gnu.kawa.slib.srfi13" }, arrayOfString7, { "16", "case-lambda", "" }, 
        { "17", "generalized-set!", "" }, arrayOfString8, { "19", "time", str1 }, arrayOfString9, { "23", "error", "" }, { "25", "multi-dimensional-arrays", "" }, arrayOfString10, arrayOfString11, arrayOfString12, arrayOfString13, 
        { "31", "rec", str2 }, { "38", "with-shared-structure", str3 }, { "39", "parameters", "" }, arrayOfString14, { "42", "eager-comprehensions", str4 }, { "43", "vectors", str5 }, { "44", "collections", str6 }, { "45", "lazy", str7 }, { "46", "syntax-rules", str8 }, { "47", "arrays", str9 }, 
        arrayOfString15, { "51", "rest-values", str10 }, { "54", "cat", str11 }, { "57", "records", str12 }, arrayOfString16, { "60", "integer-bits", str13 }, arrayOfString17, arrayOfString18, { "64", "testing", "gnu.kawa.slib.testing" }, { "66", "octet-vectors", str14 }, 
        { "67", "compare-procedures", str15 }, { "69", "basic-hash-tables", "gnu.kawa.slib.srfi69" }, { "71", "let", str16 }, arrayOfString19, { "78", "lightweight-testing", str17 }, arrayOfString20, arrayOfString21, arrayOfString22 };
  }
  
  public Expression rewriteForm(Pair paramPair, Translator paramTranslator) {
    return null;
  }
  
  public boolean scanForDefinitions(Pair paramPair, Vector paramVector, ScopeExp paramScopeExp, Translator paramTranslator) {
    // Byte code:
    //   0: aconst_null
    //   1: astore #8
    //   3: aload_1
    //   4: invokevirtual getCdr : ()Ljava/lang/Object;
    //   7: astore_1
    //   8: aload_1
    //   9: instanceof gnu/lists/Pair
    //   12: ifne -> 17
    //   15: iconst_0
    //   16: ireturn
    //   17: aload_1
    //   18: checkcast gnu/lists/Pair
    //   21: astore_1
    //   22: aload_1
    //   23: invokevirtual getCar : ()Ljava/lang/Object;
    //   26: astore #9
    //   28: aload #9
    //   30: iconst_0
    //   31: invokestatic listLength : (Ljava/lang/Object;Z)I
    //   34: ifgt -> 49
    //   37: aload #4
    //   39: bipush #101
    //   41: ldc_w 'expected <library reference> which must be a list'
    //   44: invokevirtual error : (CLjava/lang/String;)V
    //   47: iconst_0
    //   48: ireturn
    //   49: aload_1
    //   50: invokevirtual getCdr : ()Ljava/lang/Object;
    //   53: astore_1
    //   54: aload #8
    //   56: astore #7
    //   58: aload_1
    //   59: instanceof gnu/lists/Pair
    //   62: ifeq -> 94
    //   65: aload #8
    //   67: astore #7
    //   69: aload_1
    //   70: checkcast gnu/lists/Pair
    //   73: invokevirtual getCar : ()Ljava/lang/Object;
    //   76: instanceof gnu/mapping/Procedure
    //   79: ifeq -> 94
    //   82: aload_1
    //   83: checkcast gnu/lists/Pair
    //   86: invokevirtual getCar : ()Ljava/lang/Object;
    //   89: checkcast gnu/mapping/Procedure
    //   92: astore #7
    //   94: aconst_null
    //   95: astore_1
    //   96: aconst_null
    //   97: astore #8
    //   99: new java/lang/StringBuffer
    //   102: dup
    //   103: invokespecial <init> : ()V
    //   106: astore #11
    //   108: aload #9
    //   110: instanceof gnu/lists/Pair
    //   113: ifeq -> 278
    //   116: aload #9
    //   118: checkcast gnu/lists/Pair
    //   121: astore #9
    //   123: aload #9
    //   125: invokevirtual getCar : ()Ljava/lang/Object;
    //   128: astore #10
    //   130: aload #9
    //   132: invokevirtual getCdr : ()Ljava/lang/Object;
    //   135: astore #9
    //   137: aload #10
    //   139: instanceof gnu/lists/Pair
    //   142: ifeq -> 209
    //   145: aload_1
    //   146: ifnull -> 176
    //   149: aload #4
    //   151: bipush #101
    //   153: new java/lang/StringBuilder
    //   156: dup
    //   157: invokespecial <init> : ()V
    //   160: ldc_w 'duplicate version reference - was '
    //   163: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   166: aload_1
    //   167: invokevirtual append : (Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   170: invokevirtual toString : ()Ljava/lang/String;
    //   173: invokevirtual error : (CLjava/lang/String;)V
    //   176: aload #10
    //   178: astore_1
    //   179: getstatic java/lang/System.err : Ljava/io/PrintStream;
    //   182: new java/lang/StringBuilder
    //   185: dup
    //   186: invokespecial <init> : ()V
    //   189: ldc_w 'import version '
    //   192: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   195: aload #10
    //   197: invokevirtual append : (Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   200: invokevirtual toString : ()Ljava/lang/String;
    //   203: invokevirtual println : (Ljava/lang/String;)V
    //   206: goto -> 108
    //   209: aload #10
    //   211: instanceof java/lang/String
    //   214: ifeq -> 245
    //   217: aload #9
    //   219: instanceof gnu/lists/Pair
    //   222: ifeq -> 235
    //   225: aload #4
    //   227: bipush #101
    //   229: ldc_w 'source specifier must be last elemnt in library reference'
    //   232: invokevirtual error : (CLjava/lang/String;)V
    //   235: aload #10
    //   237: checkcast java/lang/String
    //   240: astore #8
    //   242: goto -> 206
    //   245: aload #11
    //   247: invokevirtual length : ()I
    //   250: ifle -> 261
    //   253: aload #11
    //   255: bipush #46
    //   257: invokevirtual append : (C)Ljava/lang/StringBuffer;
    //   260: pop
    //   261: aload #11
    //   263: aload #10
    //   265: invokevirtual toString : ()Ljava/lang/String;
    //   268: invokestatic mangleNameIfNeeded : (Ljava/lang/String;)Ljava/lang/String;
    //   271: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   274: pop
    //   275: goto -> 206
    //   278: aconst_null
    //   279: astore_1
    //   280: aload #8
    //   282: ifnull -> 331
    //   285: aload #8
    //   287: aload_3
    //   288: invokestatic lookupModuleFromSourcePath : (Ljava/lang/String;Lgnu/expr/ScopeExp;)Lgnu/expr/ModuleInfo;
    //   291: astore #9
    //   293: aload #9
    //   295: astore_1
    //   296: aload #9
    //   298: ifnonnull -> 331
    //   301: aload #4
    //   303: bipush #101
    //   305: new java/lang/StringBuilder
    //   308: dup
    //   309: invokespecial <init> : ()V
    //   312: ldc_w 'malformed URL: '
    //   315: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   318: aload #8
    //   320: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   323: invokevirtual toString : ()Ljava/lang/String;
    //   326: invokevirtual error : (CLjava/lang/String;)V
    //   329: iconst_0
    //   330: ireturn
    //   331: aload #11
    //   333: invokevirtual toString : ()Ljava/lang/String;
    //   336: astore #9
    //   338: aload #9
    //   340: astore #8
    //   342: aload #9
    //   344: ldc_w 'srfi.'
    //   347: invokevirtual startsWith : (Ljava/lang/String;)Z
    //   350: ifeq -> 710
    //   353: aload #9
    //   355: iconst_5
    //   356: invokevirtual substring : (I)Ljava/lang/String;
    //   359: invokestatic demangleName : (Ljava/lang/String;)Ljava/lang/String;
    //   362: astore #11
    //   364: aload #11
    //   366: bipush #46
    //   368: invokevirtual indexOf : (I)I
    //   371: istore #5
    //   373: iload #5
    //   375: ifge -> 449
    //   378: aconst_null
    //   379: astore #8
    //   381: aload #11
    //   383: invokevirtual length : ()I
    //   386: istore #5
    //   388: aconst_null
    //   389: astore #10
    //   391: iload #5
    //   393: iconst_2
    //   394: if_icmpge -> 412
    //   397: aload #10
    //   399: astore #9
    //   401: aload #11
    //   403: iconst_0
    //   404: invokevirtual charAt : (I)C
    //   407: bipush #58
    //   409: if_icmpne -> 432
    //   412: iconst_1
    //   413: istore #6
    //   415: iload #6
    //   417: iload #5
    //   419: if_icmpne -> 463
    //   422: aload #11
    //   424: iconst_1
    //   425: iload #5
    //   427: invokevirtual substring : (II)Ljava/lang/String;
    //   430: astore #9
    //   432: aload #9
    //   434: ifnonnull -> 491
    //   437: aload #4
    //   439: bipush #101
    //   441: ldc_w 'SRFI library reference must have the form: (srfi :NNN [name])'
    //   444: invokevirtual error : (CLjava/lang/String;)V
    //   447: iconst_0
    //   448: ireturn
    //   449: aload #11
    //   451: iload #5
    //   453: iconst_1
    //   454: iadd
    //   455: invokevirtual substring : (I)Ljava/lang/String;
    //   458: astore #8
    //   460: goto -> 388
    //   463: aload #10
    //   465: astore #9
    //   467: aload #11
    //   469: iload #6
    //   471: invokevirtual charAt : (I)C
    //   474: bipush #10
    //   476: invokestatic digit : (CI)I
    //   479: iflt -> 432
    //   482: iload #6
    //   484: iconst_1
    //   485: iadd
    //   486: istore #6
    //   488: goto -> 415
    //   491: getstatic kawa/standard/ImportFromLibrary.SRFI97Map : [[Ljava/lang/String;
    //   494: arraylength
    //   495: istore #5
    //   497: iload #5
    //   499: iconst_1
    //   500: isub
    //   501: istore #6
    //   503: iload #6
    //   505: ifge -> 544
    //   508: aload #4
    //   510: bipush #101
    //   512: new java/lang/StringBuilder
    //   515: dup
    //   516: invokespecial <init> : ()V
    //   519: ldc_w 'unknown SRFI number ''
    //   522: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   525: aload #9
    //   527: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   530: ldc_w '' in SRFI library reference'
    //   533: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   536: invokevirtual toString : ()Ljava/lang/String;
    //   539: invokevirtual error : (CLjava/lang/String;)V
    //   542: iconst_0
    //   543: ireturn
    //   544: iload #6
    //   546: istore #5
    //   548: getstatic kawa/standard/ImportFromLibrary.SRFI97Map : [[Ljava/lang/String;
    //   551: iload #6
    //   553: aaload
    //   554: iconst_0
    //   555: aaload
    //   556: aload #9
    //   558: invokevirtual equals : (Ljava/lang/Object;)Z
    //   561: ifeq -> 497
    //   564: getstatic kawa/standard/ImportFromLibrary.SRFI97Map : [[Ljava/lang/String;
    //   567: iload #6
    //   569: aaload
    //   570: iconst_1
    //   571: aaload
    //   572: astore #11
    //   574: getstatic kawa/standard/ImportFromLibrary.SRFI97Map : [[Ljava/lang/String;
    //   577: iload #6
    //   579: aaload
    //   580: iconst_2
    //   581: aaload
    //   582: astore #10
    //   584: aload #8
    //   586: ifnull -> 643
    //   589: aload #8
    //   591: aload #11
    //   593: invokevirtual equals : (Ljava/lang/Object;)Z
    //   596: ifne -> 643
    //   599: aload #4
    //   601: bipush #119
    //   603: new java/lang/StringBuilder
    //   606: dup
    //   607: invokespecial <init> : ()V
    //   610: ldc_w 'the name of SRFI '
    //   613: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   616: aload #9
    //   618: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   621: ldc_w ' should be ''
    //   624: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   627: aload #11
    //   629: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   632: bipush #39
    //   634: invokevirtual append : (C)Ljava/lang/StringBuilder;
    //   637: invokevirtual toString : ()Ljava/lang/String;
    //   640: invokevirtual error : (CLjava/lang/String;)V
    //   643: aload #10
    //   645: ldc ''
    //   647: if_acmpne -> 652
    //   650: iconst_1
    //   651: ireturn
    //   652: aload #10
    //   654: getstatic kawa/standard/ImportFromLibrary.MISSING : Ljava/lang/String;
    //   657: if_acmpne -> 706
    //   660: aload #4
    //   662: bipush #101
    //   664: new java/lang/StringBuilder
    //   667: dup
    //   668: invokespecial <init> : ()V
    //   671: ldc_w 'sorry - Kawa does not support SRFI '
    //   674: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   677: aload #9
    //   679: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   682: ldc_w ' ('
    //   685: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   688: aload #11
    //   690: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   693: bipush #41
    //   695: invokevirtual append : (C)Ljava/lang/StringBuilder;
    //   698: invokevirtual toString : ()Ljava/lang/String;
    //   701: invokevirtual error : (CLjava/lang/String;)V
    //   704: iconst_0
    //   705: ireturn
    //   706: aload #10
    //   708: astore #8
    //   710: aload_0
    //   711: getfield classPrefixPath : [Ljava/lang/String;
    //   714: arraylength
    //   715: istore #6
    //   717: iconst_0
    //   718: istore #5
    //   720: iload #5
    //   722: iload #6
    //   724: if_icmpge -> 781
    //   727: new java/lang/StringBuilder
    //   730: dup
    //   731: invokespecial <init> : ()V
    //   734: aload_0
    //   735: getfield classPrefixPath : [Ljava/lang/String;
    //   738: iload #5
    //   740: aaload
    //   741: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   744: aload #8
    //   746: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   749: invokevirtual toString : ()Ljava/lang/String;
    //   752: astore #9
    //   754: invokestatic getInstance : ()Lgnu/expr/ModuleManager;
    //   757: aload #9
    //   759: invokevirtual findWithClassName : (Ljava/lang/String;)Lgnu/expr/ModuleInfo;
    //   762: astore #9
    //   764: aload #9
    //   766: astore_1
    //   767: iload #5
    //   769: iconst_1
    //   770: iadd
    //   771: istore #5
    //   773: goto -> 720
    //   776: astore #9
    //   778: goto -> 767
    //   781: aload_1
    //   782: ifnonnull -> 815
    //   785: aload #4
    //   787: bipush #101
    //   789: new java/lang/StringBuilder
    //   792: dup
    //   793: invokespecial <init> : ()V
    //   796: ldc_w 'unknown class '
    //   799: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   802: aload #8
    //   804: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   807: invokevirtual toString : ()Ljava/lang/String;
    //   810: invokevirtual error : (CLjava/lang/String;)V
    //   813: iconst_0
    //   814: ireturn
    //   815: aconst_null
    //   816: aload_1
    //   817: aload #7
    //   819: aload_2
    //   820: aload_3
    //   821: aload #4
    //   823: invokestatic importDefinitions : (Ljava/lang/String;Lgnu/expr/ModuleInfo;Lgnu/mapping/Procedure;Ljava/util/Vector;Lgnu/expr/ScopeExp;Lgnu/expr/Compilation;)Z
    //   826: pop
    //   827: iconst_1
    //   828: ireturn
    // Exception table:
    //   from	to	target	type
    //   754	764	776	java/lang/Exception
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/kawa/standard/ImportFromLibrary.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */