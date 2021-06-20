package kawa.standard;

import gnu.expr.Compilation;
import gnu.expr.Expression;
import gnu.expr.ModuleInfo;
import gnu.expr.ModuleManager;
import gnu.expr.ScopeExp;
import gnu.lists.Pair;
import gnu.mapping.Procedure;
import gnu.text.Path;
import java.util.Hashtable;
import java.util.Vector;
import kawa.lang.Syntax;
import kawa.lang.Translator;

public class require extends Syntax {
  private static final String SLIB_PREFIX = "gnu.kawa.slib.";
  
  static Hashtable featureMap;
  
  public static final require require = new require();
  
  static {
    require.setName("require");
    featureMap = new Hashtable<Object, Object>();
    map("generic-write", "gnu.kawa.slib.genwrite");
    map("pretty-print", "gnu.kawa.slib.pp");
    map("pprint-file", "gnu.kawa.slib.ppfile");
    map("printf", "gnu.kawa.slib.printf");
    map("xml", "gnu.kawa.slib.XML");
    map("readtable", "gnu.kawa.slib.readtable");
    map("srfi-10", "gnu.kawa.slib.readtable");
    map("http", "gnu.kawa.servlet.HTTP");
    map("servlets", "gnu.kawa.servlet.servlets");
    map("srfi-1", "gnu.kawa.slib.srfi1");
    map("list-lib", "gnu.kawa.slib.srfi1");
    map("srfi-2", "gnu.kawa.slib.srfi2");
    map("and-let*", "gnu.kawa.slib.srfi2");
    map("srfi-13", "gnu.kawa.slib.srfi13");
    map("string-lib", "gnu.kawa.slib.srfi13");
    map("srfi-34", "gnu.kawa.slib.srfi34");
    map("srfi-35", "gnu.kawa.slib.conditions");
    map("condition", "gnu.kawa.slib.conditions");
    map("conditions", "gnu.kawa.slib.conditions");
    map("srfi-37", "gnu.kawa.slib.srfi37");
    map("args-fold", "gnu.kawa.slib.srfi37");
    map("srfi-64", "gnu.kawa.slib.testing");
    map("testing", "gnu.kawa.slib.testing");
    map("srfi-69", "gnu.kawa.slib.srfi69");
    map("hash-table", "gnu.kawa.slib.srfi69");
    map("basic-hash-tables", "gnu.kawa.slib.srfi69");
    map("srfi-95", "kawa.lib.srfi95");
    map("sorting-and-merging", "kawa.lib.srfi95");
    map("regex", "kawa.lib.kawa.regex");
    map("pregexp", "gnu.kawa.slib.pregexp");
    map("gui", "gnu.kawa.slib.gui");
    map("swing-gui", "gnu.kawa.slib.swing");
    map("android-defs", "gnu.kawa.android.defs");
    map("syntax-utils", "gnu.kawa.slib.syntaxutils");
  }
  
  public static Object find(String paramString) {
    return ModuleManager.getInstance().findWithClassName(paramString).getInstance();
  }
  
  public static boolean importDefinitions(String paramString, ModuleInfo paramModuleInfo, Procedure paramProcedure, Vector paramVector, ScopeExp paramScopeExp, Compilation paramCompilation) {
    // Byte code:
    //   0: invokestatic getInstance : ()Lgnu/expr/ModuleManager;
    //   3: astore #12
    //   5: aload_1
    //   6: invokevirtual getState : ()I
    //   9: iconst_1
    //   10: iand
    //   11: ifne -> 109
    //   14: aload_1
    //   15: invokevirtual getCompilation : ()Lgnu/expr/Compilation;
    //   18: ifnonnull -> 109
    //   21: aload_1
    //   22: aload #12
    //   24: invokestatic currentTimeMillis : ()J
    //   27: invokevirtual checkCurrent : (Lgnu/expr/ModuleManager;J)Z
    //   30: ifne -> 109
    //   33: aload #5
    //   35: invokevirtual getMessages : ()Lgnu/text/SourceMessages;
    //   38: astore #12
    //   40: invokestatic getDefaultLanguage : ()Lgnu/expr/Language;
    //   43: astore #13
    //   45: aload_1
    //   46: invokevirtual getSourceAbsPath : ()Lgnu/text/Path;
    //   49: invokestatic openFile : (Ljava/lang/Object;)Lgnu/mapping/InPort;
    //   52: astore #14
    //   54: aload_1
    //   55: invokevirtual clearClass : ()V
    //   58: aload_1
    //   59: aload_0
    //   60: invokevirtual setClassName : (Ljava/lang/String;)V
    //   63: bipush #8
    //   65: istore #6
    //   67: aload #5
    //   69: getfield immediate : Z
    //   72: ifeq -> 81
    //   75: bipush #8
    //   77: iconst_1
    //   78: ior
    //   79: istore #6
    //   81: aload #13
    //   83: aload #14
    //   85: aload #12
    //   87: iload #6
    //   89: aload_1
    //   90: invokevirtual parse : (Lgnu/mapping/InPort;Lgnu/text/SourceMessages;ILgnu/expr/ModuleInfo;)Lgnu/expr/Compilation;
    //   93: astore_0
    //   94: aload_1
    //   95: aload_0
    //   96: invokevirtual getModule : ()Lgnu/expr/ModuleExp;
    //   99: aload_0
    //   100: invokevirtual classFor : (Lgnu/expr/Compilation;)Lgnu/bytecode/ClassType;
    //   103: invokevirtual getName : ()Ljava/lang/String;
    //   106: invokevirtual setClassName : (Ljava/lang/String;)V
    //   109: aload #5
    //   111: getfield minfo : Lgnu/expr/ModuleInfo;
    //   114: ifnull -> 270
    //   117: aload #5
    //   119: invokevirtual getState : ()I
    //   122: iconst_4
    //   123: if_icmpge -> 270
    //   126: aload #5
    //   128: getfield minfo : Lgnu/expr/ModuleInfo;
    //   131: aload_1
    //   132: invokevirtual addDependency : (Lgnu/expr/ModuleInfo;)V
    //   135: aload_1
    //   136: bipush #12
    //   138: invokevirtual loadEager : (I)Z
    //   141: ifne -> 270
    //   144: aload_1
    //   145: invokevirtual getState : ()I
    //   148: bipush #6
    //   150: if_icmpge -> 270
    //   153: aload #5
    //   155: aload_1
    //   156: aload #4
    //   158: aload_3
    //   159: invokevirtual size : ()I
    //   162: invokevirtual pushPendingImport : (Lgnu/expr/ModuleInfo;Lgnu/expr/ScopeExp;I)V
    //   165: iconst_1
    //   166: ireturn
    //   167: astore_0
    //   168: aload #5
    //   170: bipush #101
    //   172: new java/lang/StringBuilder
    //   175: dup
    //   176: invokespecial <init> : ()V
    //   179: ldc_w 'not found: '
    //   182: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   185: aload_0
    //   186: invokevirtual getMessage : ()Ljava/lang/String;
    //   189: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   192: invokevirtual toString : ()Ljava/lang/String;
    //   195: invokevirtual error : (CLjava/lang/String;)V
    //   198: iconst_0
    //   199: ireturn
    //   200: astore_0
    //   201: aload #5
    //   203: bipush #101
    //   205: new java/lang/StringBuilder
    //   208: dup
    //   209: invokespecial <init> : ()V
    //   212: ldc_w 'caught '
    //   215: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   218: aload_0
    //   219: invokevirtual append : (Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   222: invokevirtual toString : ()Ljava/lang/String;
    //   225: invokevirtual error : (CLjava/lang/String;)V
    //   228: iconst_0
    //   229: ireturn
    //   230: astore_0
    //   231: aload_0
    //   232: invokevirtual getMessages : ()Lgnu/text/SourceMessages;
    //   235: aload #12
    //   237: if_acmpeq -> 268
    //   240: new java/lang/RuntimeException
    //   243: dup
    //   244: new java/lang/StringBuilder
    //   247: dup
    //   248: invokespecial <init> : ()V
    //   251: ldc_w 'confussing syntax error: '
    //   254: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   257: aload_0
    //   258: invokevirtual append : (Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   261: invokevirtual toString : ()Ljava/lang/String;
    //   264: invokespecial <init> : (Ljava/lang/String;)V
    //   267: athrow
    //   268: iconst_0
    //   269: ireturn
    //   270: aload_1
    //   271: invokevirtual getClassType : ()Lgnu/bytecode/ClassType;
    //   274: astore #19
    //   276: aload #19
    //   278: invokevirtual getName : ()Ljava/lang/String;
    //   281: astore #21
    //   283: aload #5
    //   285: invokevirtual sharedModuleDefs : ()Z
    //   288: istore #10
    //   290: aload_1
    //   291: invokevirtual getState : ()I
    //   294: bipush #6
    //   296: if_icmpge -> 428
    //   299: aload_1
    //   300: invokevirtual getCompilation : ()Lgnu/expr/Compilation;
    //   303: invokevirtual makeRunnable : ()Z
    //   306: istore #9
    //   308: aconst_null
    //   309: astore_0
    //   310: ldc_w 'kawa.standard.require'
    //   313: invokestatic make : (Ljava/lang/String;)Lgnu/bytecode/ClassType;
    //   316: ldc_w 'find'
    //   319: iconst_1
    //   320: anewarray gnu/expr/Expression
    //   323: dup
    //   324: iconst_0
    //   325: new gnu/expr/QuoteExp
    //   328: dup
    //   329: aload #21
    //   331: invokespecial <init> : (Ljava/lang/Object;)V
    //   334: aastore
    //   335: invokestatic makeInvokeStatic : (Lgnu/bytecode/ClassType;Ljava/lang/String;[Lgnu/expr/Expression;)Lgnu/expr/ApplyExp;
    //   338: astore #17
    //   340: aconst_null
    //   341: astore #12
    //   343: aload #5
    //   345: invokevirtual getLanguage : ()Lgnu/expr/Language;
    //   348: astore #22
    //   350: aload #17
    //   352: aload #5
    //   354: invokevirtual setLine : (Lgnu/expr/Compilation;)V
    //   357: aload_3
    //   358: invokevirtual size : ()I
    //   361: istore #8
    //   363: aload_1
    //   364: invokevirtual setupModuleExp : ()Lgnu/expr/ModuleExp;
    //   367: astore_1
    //   368: new java/util/Vector
    //   371: dup
    //   372: invokespecial <init> : ()V
    //   375: astore #20
    //   377: aload_1
    //   378: invokevirtual firstDecl : ()Lgnu/expr/Declaration;
    //   381: astore #13
    //   383: aload #13
    //   385: ifnull -> 1149
    //   388: aload #13
    //   390: invokevirtual isPrivate : ()Z
    //   393: ifeq -> 441
    //   396: aload #12
    //   398: astore #16
    //   400: iload #8
    //   402: istore #7
    //   404: aload_0
    //   405: astore #14
    //   407: aload #13
    //   409: invokevirtual nextDecl : ()Lgnu/expr/Declaration;
    //   412: astore #13
    //   414: aload #14
    //   416: astore_0
    //   417: iload #7
    //   419: istore #8
    //   421: aload #16
    //   423: astore #12
    //   425: goto -> 383
    //   428: aload #19
    //   430: getstatic gnu/expr/Compilation.typeRunnable : Lgnu/bytecode/ClassType;
    //   433: invokevirtual isSubtype : (Lgnu/bytecode/Type;)Z
    //   436: istore #9
    //   438: goto -> 308
    //   441: aload #13
    //   443: invokevirtual getSymbol : ()Ljava/lang/Object;
    //   446: checkcast gnu/mapping/Symbol
    //   449: astore_1
    //   450: aload_1
    //   451: astore #15
    //   453: aload_2
    //   454: ifnull -> 542
    //   457: aload_2
    //   458: aload_1
    //   459: invokevirtual apply1 : (Ljava/lang/Object;)Ljava/lang/Object;
    //   462: astore_1
    //   463: aload_0
    //   464: astore #14
    //   466: iload #8
    //   468: istore #7
    //   470: aload #12
    //   472: astore #16
    //   474: aload_1
    //   475: ifnull -> 407
    //   478: aload_1
    //   479: instanceof gnu/mapping/Symbol
    //   482: ifne -> 536
    //   485: aload #5
    //   487: bipush #101
    //   489: new java/lang/StringBuilder
    //   492: dup
    //   493: invokespecial <init> : ()V
    //   496: ldc_w 'internal error - import name mapper returned non-symbol: '
    //   499: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   502: aload_1
    //   503: invokevirtual getClass : ()Ljava/lang/Class;
    //   506: invokevirtual getName : ()Ljava/lang/String;
    //   509: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   512: invokevirtual toString : ()Ljava/lang/String;
    //   515: invokevirtual error : (CLjava/lang/String;)V
    //   518: aload_0
    //   519: astore #14
    //   521: iload #8
    //   523: istore #7
    //   525: aload #12
    //   527: astore #16
    //   529: goto -> 407
    //   532: astore_1
    //   533: goto -> 463
    //   536: aload_1
    //   537: checkcast gnu/mapping/Symbol
    //   540: astore #15
    //   542: aload #13
    //   544: ldc2_w 2048
    //   547: invokevirtual getFlag : (J)Z
    //   550: istore #11
    //   552: aload_0
    //   553: astore_1
    //   554: iload #8
    //   556: istore #6
    //   558: iload #11
    //   560: ifne -> 695
    //   563: aload_0
    //   564: astore_1
    //   565: iload #8
    //   567: istore #6
    //   569: aload_0
    //   570: ifnonnull -> 695
    //   573: new gnu/expr/Declaration
    //   576: dup
    //   577: new java/lang/StringBuilder
    //   580: dup
    //   581: invokespecial <init> : ()V
    //   584: aload #21
    //   586: bipush #46
    //   588: bipush #36
    //   590: invokevirtual replace : (CC)Ljava/lang/String;
    //   593: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   596: ldc_w '$instance'
    //   599: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   602: invokevirtual toString : ()Ljava/lang/String;
    //   605: invokestatic valueOf : (Ljava/lang/String;)Lgnu/mapping/SimpleSymbol;
    //   608: aload #19
    //   610: invokespecial <init> : (Ljava/lang/Object;Lgnu/bytecode/Type;)V
    //   613: astore_1
    //   614: aload_1
    //   615: iconst_1
    //   616: invokevirtual setPrivate : (Z)V
    //   619: aload_1
    //   620: ldc2_w 1073758208
    //   623: invokevirtual setFlag : (J)V
    //   626: aload #4
    //   628: aload_1
    //   629: invokevirtual addDeclaration : (Lgnu/expr/Declaration;)V
    //   632: aload_1
    //   633: aload #17
    //   635: invokevirtual noteValue : (Lgnu/expr/Expression;)V
    //   638: new gnu/expr/SetExp
    //   641: dup
    //   642: aload_1
    //   643: aload #17
    //   645: invokespecial <init> : (Lgnu/expr/Declaration;Lgnu/expr/Expression;)V
    //   648: astore_0
    //   649: aload_0
    //   650: aload #5
    //   652: invokevirtual setLine : (Lgnu/expr/Compilation;)V
    //   655: aload_0
    //   656: iconst_1
    //   657: invokevirtual setDefining : (Z)V
    //   660: aload_3
    //   661: aload_0
    //   662: invokevirtual addElement : (Ljava/lang/Object;)V
    //   665: aload_3
    //   666: invokevirtual size : ()I
    //   669: istore #6
    //   671: aload_1
    //   672: ldc2_w 536870912
    //   675: invokevirtual setFlag : (J)V
    //   678: iload #9
    //   680: ifeq -> 688
    //   683: aload_1
    //   684: iconst_0
    //   685: invokevirtual setSimple : (Z)V
    //   688: aload_1
    //   689: ldc2_w 8192
    //   692: invokevirtual setFlag : (J)V
    //   695: aload #13
    //   697: getfield field : Lgnu/bytecode/Field;
    //   700: ifnull -> 737
    //   703: aload #13
    //   705: getfield field : Lgnu/bytecode/Field;
    //   708: invokevirtual getName : ()Ljava/lang/String;
    //   711: ldc_w '$instance'
    //   714: invokevirtual equals : (Ljava/lang/Object;)Z
    //   717: ifeq -> 737
    //   720: aload #13
    //   722: getfield field : Lgnu/bytecode/Field;
    //   725: astore #16
    //   727: aload_1
    //   728: astore #14
    //   730: iload #6
    //   732: istore #7
    //   734: goto -> 407
    //   737: aload #13
    //   739: getfield field : Lgnu/bytecode/Field;
    //   742: ifnull -> 1021
    //   745: aload #13
    //   747: getfield field : Lgnu/bytecode/Field;
    //   750: invokevirtual getName : ()Ljava/lang/String;
    //   753: ldc_w '$instance'
    //   756: invokevirtual endsWith : (Ljava/lang/String;)Z
    //   759: ifeq -> 1021
    //   762: iconst_1
    //   763: istore #8
    //   765: aload #4
    //   767: aload #15
    //   769: aload #22
    //   771: aload #22
    //   773: aload #13
    //   775: invokevirtual getNamespaceOf : (Lgnu/expr/Declaration;)I
    //   778: invokevirtual lookup : (Ljava/lang/Object;Lgnu/expr/Language;I)Lgnu/expr/Declaration;
    //   781: astore #18
    //   783: iload #8
    //   785: ifeq -> 1027
    //   788: aload_1
    //   789: astore #14
    //   791: iload #6
    //   793: istore #7
    //   795: aload #12
    //   797: astore #16
    //   799: aload #18
    //   801: ifnonnull -> 407
    //   804: aload #4
    //   806: aload #15
    //   808: invokevirtual addDeclaration : (Ljava/lang/Object;)Lgnu/expr/Declaration;
    //   811: astore_0
    //   812: aload_0
    //   813: ldc2_w 1073758208
    //   816: invokevirtual setFlag : (J)V
    //   819: aload_0
    //   820: aload #13
    //   822: invokevirtual getType : ()Lgnu/bytecode/Type;
    //   825: invokevirtual setType : (Lgnu/bytecode/Type;)V
    //   828: aload_0
    //   829: ldc2_w 8192
    //   832: invokevirtual setFlag : (J)V
    //   835: aload_0
    //   836: aload #5
    //   838: invokevirtual setLocation : (Lgnu/text/SourceLocator;)V
    //   841: new gnu/expr/ReferenceExp
    //   844: dup
    //   845: aload #13
    //   847: invokespecial <init> : (Lgnu/expr/Declaration;)V
    //   850: astore #14
    //   852: aload #14
    //   854: aload_1
    //   855: invokevirtual setContextDecl : (Lgnu/expr/Declaration;)V
    //   858: iload #8
    //   860: ifne -> 879
    //   863: aload #14
    //   865: iconst_1
    //   866: invokevirtual setDontDereference : (Z)V
    //   869: iload #10
    //   871: ifne -> 879
    //   874: aload_0
    //   875: iconst_1
    //   876: invokevirtual setPrivate : (Z)V
    //   879: aload_0
    //   880: ldc2_w 16384
    //   883: invokevirtual setFlag : (J)V
    //   886: aload #13
    //   888: ldc2_w 32768
    //   891: invokevirtual getFlag : (J)Z
    //   894: ifeq -> 904
    //   897: aload_0
    //   898: ldc2_w 32768
    //   901: invokevirtual setFlag : (J)V
    //   904: aload #13
    //   906: invokevirtual isProcedureDecl : ()Z
    //   909: ifeq -> 917
    //   912: aload_0
    //   913: iconst_1
    //   914: invokevirtual setProcedureDecl : (Z)V
    //   917: iload #11
    //   919: ifeq -> 929
    //   922: aload_0
    //   923: ldc2_w 2048
    //   926: invokevirtual setFlag : (J)V
    //   929: new gnu/expr/SetExp
    //   932: dup
    //   933: aload_0
    //   934: aload #14
    //   936: invokespecial <init> : (Lgnu/expr/Declaration;Lgnu/expr/Expression;)V
    //   939: astore #15
    //   941: aload_0
    //   942: ldc2_w 536870912
    //   945: invokevirtual setFlag : (J)V
    //   948: aload #15
    //   950: iconst_1
    //   951: invokevirtual setDefining : (Z)V
    //   954: iload #8
    //   956: ifeq -> 1140
    //   959: aload_3
    //   960: aload #15
    //   962: iload #6
    //   964: invokevirtual insertElementAt : (Ljava/lang/Object;I)V
    //   967: iload #6
    //   969: iconst_1
    //   970: iadd
    //   971: istore #6
    //   973: aload #20
    //   975: aload_0
    //   976: invokevirtual add : (Ljava/lang/Object;)Z
    //   979: pop
    //   980: aload #20
    //   982: aload #13
    //   984: invokevirtual add : (Ljava/lang/Object;)Z
    //   987: pop
    //   988: aload_0
    //   989: aload #14
    //   991: invokevirtual noteValue : (Lgnu/expr/Expression;)V
    //   994: aload_0
    //   995: ldc2_w 131072
    //   998: invokevirtual setFlag : (J)V
    //   1001: aload #5
    //   1003: aload_0
    //   1004: invokevirtual push : (Lgnu/expr/Declaration;)V
    //   1007: aload_1
    //   1008: astore #14
    //   1010: iload #6
    //   1012: istore #7
    //   1014: aload #12
    //   1016: astore #16
    //   1018: goto -> 407
    //   1021: iconst_0
    //   1022: istore #8
    //   1024: goto -> 765
    //   1027: aload #18
    //   1029: ifnull -> 1067
    //   1032: aload #18
    //   1034: ldc2_w 512
    //   1037: invokevirtual getFlag : (J)Z
    //   1040: ifne -> 1067
    //   1043: aload_1
    //   1044: astore #14
    //   1046: iload #6
    //   1048: istore #7
    //   1050: aload #12
    //   1052: astore #16
    //   1054: aload #18
    //   1056: invokestatic followAliases : (Lgnu/expr/Declaration;)Lgnu/expr/Declaration;
    //   1059: aload #13
    //   1061: invokestatic followAliases : (Lgnu/expr/Declaration;)Lgnu/expr/Declaration;
    //   1064: if_acmpeq -> 407
    //   1067: aload #18
    //   1069: ifnull -> 1108
    //   1072: aload #18
    //   1074: ldc2_w 66048
    //   1077: invokevirtual getFlag : (J)Z
    //   1080: ifeq -> 1108
    //   1083: aload #18
    //   1085: iconst_0
    //   1086: ldc2_w 66048
    //   1089: invokevirtual setFlag : (ZJ)V
    //   1092: aload #18
    //   1094: astore_0
    //   1095: aload_0
    //   1096: iconst_1
    //   1097: invokevirtual setAlias : (Z)V
    //   1100: aload_0
    //   1101: iconst_1
    //   1102: invokevirtual setIndirectBinding : (Z)V
    //   1105: goto -> 835
    //   1108: aload #4
    //   1110: aload #15
    //   1112: invokevirtual addDeclaration : (Ljava/lang/Object;)Lgnu/expr/Declaration;
    //   1115: astore #14
    //   1117: aload #14
    //   1119: astore_0
    //   1120: aload #18
    //   1122: ifnull -> 1095
    //   1125: aload #18
    //   1127: aload #14
    //   1129: aload #5
    //   1131: invokestatic duplicateDeclarationError : (Lgnu/expr/Declaration;Lgnu/expr/Declaration;Lgnu/expr/Compilation;)V
    //   1134: aload #14
    //   1136: astore_0
    //   1137: goto -> 1095
    //   1140: aload_3
    //   1141: aload #15
    //   1143: invokevirtual addElement : (Ljava/lang/Object;)V
    //   1146: goto -> 973
    //   1149: aload #20
    //   1151: invokevirtual size : ()I
    //   1154: istore #7
    //   1156: iconst_0
    //   1157: istore #6
    //   1159: iload #6
    //   1161: iload #7
    //   1163: if_icmpge -> 1306
    //   1166: aload #20
    //   1168: iload #6
    //   1170: invokevirtual elementAt : (I)Ljava/lang/Object;
    //   1173: checkcast gnu/expr/Declaration
    //   1176: astore_2
    //   1177: aload #20
    //   1179: iload #6
    //   1181: iconst_1
    //   1182: iadd
    //   1183: invokevirtual elementAt : (I)Ljava/lang/Object;
    //   1186: checkcast gnu/expr/Declaration
    //   1189: astore #13
    //   1191: aload #13
    //   1193: invokevirtual getValue : ()Lgnu/expr/Expression;
    //   1196: astore_1
    //   1197: aload #13
    //   1199: invokevirtual isIndirectBinding : ()Z
    //   1202: ifeq -> 1297
    //   1205: aload_1
    //   1206: instanceof gnu/expr/ReferenceExp
    //   1209: ifeq -> 1297
    //   1212: aload_2
    //   1213: invokevirtual getValue : ()Lgnu/expr/Expression;
    //   1216: checkcast gnu/expr/ReferenceExp
    //   1219: astore_2
    //   1220: aload_1
    //   1221: checkcast gnu/expr/ReferenceExp
    //   1224: invokevirtual getBinding : ()Lgnu/expr/Declaration;
    //   1227: astore_1
    //   1228: aload_2
    //   1229: aload_1
    //   1230: invokevirtual setBinding : (Lgnu/expr/Declaration;)V
    //   1233: aload_1
    //   1234: invokevirtual needsContext : ()Z
    //   1237: ifeq -> 1297
    //   1240: aload #4
    //   1242: new java/lang/StringBuilder
    //   1245: dup
    //   1246: invokespecial <init> : ()V
    //   1249: aload_1
    //   1250: getfield field : Lgnu/bytecode/Field;
    //   1253: invokevirtual getDeclaringClass : ()Lgnu/bytecode/ClassType;
    //   1256: invokevirtual getName : ()Ljava/lang/String;
    //   1259: bipush #46
    //   1261: bipush #36
    //   1263: invokevirtual replace : (CC)Ljava/lang/String;
    //   1266: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1269: ldc_w '$instance'
    //   1272: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1275: invokevirtual toString : ()Ljava/lang/String;
    //   1278: invokestatic valueOf : (Ljava/lang/String;)Lgnu/mapping/SimpleSymbol;
    //   1281: invokevirtual lookup : (Ljava/lang/Object;)Lgnu/expr/Declaration;
    //   1284: astore_1
    //   1285: aload_1
    //   1286: ldc2_w 1024
    //   1289: invokevirtual setFlag : (J)V
    //   1292: aload_2
    //   1293: aload_1
    //   1294: invokevirtual setContextDecl : (Lgnu/expr/Declaration;)V
    //   1297: iload #6
    //   1299: iconst_2
    //   1300: iadd
    //   1301: istore #6
    //   1303: goto -> 1159
    //   1306: iload #9
    //   1308: ifeq -> 1363
    //   1311: getstatic gnu/expr/Compilation.typeRunnable : Lgnu/bytecode/ClassType;
    //   1314: ldc_w 'run'
    //   1317: iconst_0
    //   1318: invokevirtual getDeclaredMethod : (Ljava/lang/String;I)Lgnu/bytecode/Method;
    //   1321: astore_1
    //   1322: aload_0
    //   1323: ifnull -> 1365
    //   1326: new gnu/expr/ReferenceExp
    //   1329: dup
    //   1330: aload_0
    //   1331: invokespecial <init> : (Lgnu/expr/Declaration;)V
    //   1334: astore_0
    //   1335: new gnu/expr/ApplyExp
    //   1338: dup
    //   1339: aload_1
    //   1340: iconst_1
    //   1341: anewarray gnu/expr/Expression
    //   1344: dup
    //   1345: iconst_0
    //   1346: aload_0
    //   1347: aastore
    //   1348: invokespecial <init> : (Lgnu/bytecode/Method;[Lgnu/expr/Expression;)V
    //   1351: astore_0
    //   1352: aload_0
    //   1353: aload #5
    //   1355: invokevirtual setLine : (Lgnu/expr/Compilation;)V
    //   1358: aload_3
    //   1359: aload_0
    //   1360: invokevirtual addElement : (Ljava/lang/Object;)V
    //   1363: iconst_1
    //   1364: ireturn
    //   1365: aload #17
    //   1367: astore_0
    //   1368: aload #12
    //   1370: ifnull -> 1335
    //   1373: new gnu/expr/QuoteExp
    //   1376: dup
    //   1377: aload #19
    //   1379: invokespecial <init> : (Ljava/lang/Object;)V
    //   1382: astore_0
    //   1383: new gnu/expr/QuoteExp
    //   1386: dup
    //   1387: ldc_w '$instance'
    //   1390: invokespecial <init> : (Ljava/lang/Object;)V
    //   1393: astore_2
    //   1394: new gnu/expr/ApplyExp
    //   1397: dup
    //   1398: getstatic gnu/kawa/reflect/SlotGet.staticField : Lgnu/kawa/reflect/SlotGet;
    //   1401: iconst_2
    //   1402: anewarray gnu/expr/Expression
    //   1405: dup
    //   1406: iconst_0
    //   1407: aload_0
    //   1408: aastore
    //   1409: dup
    //   1410: iconst_1
    //   1411: aload_2
    //   1412: aastore
    //   1413: invokespecial <init> : (Lgnu/mapping/Procedure;[Lgnu/expr/Expression;)V
    //   1416: astore_0
    //   1417: goto -> 1335
    // Exception table:
    //   from	to	target	type
    //   45	63	167	java/io/FileNotFoundException
    //   45	63	200	java/io/IOException
    //   45	63	230	gnu/text/SyntaxException
    //   67	75	167	java/io/FileNotFoundException
    //   67	75	200	java/io/IOException
    //   67	75	230	gnu/text/SyntaxException
    //   81	94	167	java/io/FileNotFoundException
    //   81	94	200	java/io/IOException
    //   81	94	230	gnu/text/SyntaxException
    //   457	463	532	java/lang/Throwable
  }
  
  public static ModuleInfo lookupModuleFromSourcePath(String paramString, ScopeExp paramScopeExp) {
    ModuleManager moduleManager = ModuleManager.getInstance();
    String str2 = paramScopeExp.getFileName();
    String str1 = paramString;
    if (str2 != null)
      str1 = Path.valueOf(str2).resolve(paramString).toString(); 
    return moduleManager.findWithSourcePath(str1);
  }
  
  static void map(String paramString1, String paramString2) {
    featureMap.put(paramString1, paramString2);
  }
  
  public static String mapFeature(String paramString) {
    return (String)featureMap.get(paramString);
  }
  
  public Expression rewriteForm(Pair paramPair, Translator paramTranslator) {
    return null;
  }
  
  public boolean scanForDefinitions(Pair paramPair, Vector paramVector, ScopeExp paramScopeExp, Translator paramTranslator) {
    // Byte code:
    //   0: aload #4
    //   2: invokevirtual getState : ()I
    //   5: iconst_1
    //   6: if_icmpne -> 23
    //   9: aload #4
    //   11: iconst_2
    //   12: invokevirtual setState : (I)V
    //   15: aload #4
    //   17: aload_1
    //   18: putfield pendingForm : Ljava/lang/Object;
    //   21: iconst_1
    //   22: ireturn
    //   23: aload_1
    //   24: invokevirtual getCdr : ()Ljava/lang/Object;
    //   27: checkcast gnu/lists/Pair
    //   30: astore #6
    //   32: aload #6
    //   34: invokevirtual getCar : ()Ljava/lang/Object;
    //   37: astore #7
    //   39: aconst_null
    //   40: astore #5
    //   42: aload #7
    //   44: instanceof gnu/lists/Pair
    //   47: ifeq -> 203
    //   50: aload #7
    //   52: checkcast gnu/lists/Pair
    //   55: astore_1
    //   56: aload #4
    //   58: aload_1
    //   59: invokevirtual getCar : ()Ljava/lang/Object;
    //   62: ldc_w 'quote'
    //   65: invokevirtual matches : (Ljava/lang/Object;Ljava/lang/String;)Z
    //   68: ifeq -> 203
    //   71: aload_1
    //   72: invokevirtual getCdr : ()Ljava/lang/Object;
    //   75: astore_1
    //   76: aload_1
    //   77: instanceof gnu/lists/Pair
    //   80: ifeq -> 108
    //   83: aload_1
    //   84: checkcast gnu/lists/Pair
    //   87: astore_1
    //   88: aload_1
    //   89: invokevirtual getCdr : ()Ljava/lang/Object;
    //   92: getstatic gnu/lists/LList.Empty : Lgnu/lists/LList;
    //   95: if_acmpne -> 108
    //   98: aload_1
    //   99: invokevirtual getCar : ()Ljava/lang/Object;
    //   102: instanceof gnu/mapping/Symbol
    //   105: ifne -> 120
    //   108: aload #4
    //   110: bipush #101
    //   112: ldc_w 'invalid quoted symbol for 'require''
    //   115: invokevirtual error : (CLjava/lang/String;)V
    //   118: iconst_0
    //   119: ireturn
    //   120: aload_1
    //   121: invokevirtual getCar : ()Ljava/lang/Object;
    //   124: invokevirtual toString : ()Ljava/lang/String;
    //   127: invokestatic mapFeature : (Ljava/lang/String;)Ljava/lang/String;
    //   130: astore #5
    //   132: aload #5
    //   134: ifnonnull -> 175
    //   137: aload #4
    //   139: bipush #101
    //   141: new java/lang/StringBuilder
    //   144: dup
    //   145: invokespecial <init> : ()V
    //   148: ldc_w 'unknown feature name ''
    //   151: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   154: aload_1
    //   155: invokevirtual getCar : ()Ljava/lang/Object;
    //   158: invokevirtual append : (Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   161: ldc_w '' for 'require''
    //   164: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   167: invokevirtual toString : ()Ljava/lang/String;
    //   170: invokevirtual error : (CLjava/lang/String;)V
    //   173: iconst_0
    //   174: ireturn
    //   175: aload #5
    //   177: checkcast java/lang/String
    //   180: invokestatic make : (Ljava/lang/String;)Lgnu/bytecode/ClassType;
    //   183: astore_1
    //   184: aload_1
    //   185: instanceof gnu/bytecode/ClassType
    //   188: ifne -> 424
    //   191: aload #4
    //   193: bipush #101
    //   195: ldc_w 'invalid specifier for 'require''
    //   198: invokevirtual error : (CLjava/lang/String;)V
    //   201: iconst_0
    //   202: ireturn
    //   203: aload #7
    //   205: instanceof java/lang/CharSequence
    //   208: ifeq -> 270
    //   211: aload #7
    //   213: invokevirtual toString : ()Ljava/lang/String;
    //   216: astore_1
    //   217: aload_1
    //   218: aload_3
    //   219: invokestatic lookupModuleFromSourcePath : (Ljava/lang/String;Lgnu/expr/ScopeExp;)Lgnu/expr/ModuleInfo;
    //   222: astore #5
    //   224: aload #5
    //   226: ifnonnull -> 258
    //   229: aload #4
    //   231: bipush #101
    //   233: new java/lang/StringBuilder
    //   236: dup
    //   237: invokespecial <init> : ()V
    //   240: ldc_w 'malformed URL: '
    //   243: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   246: aload_1
    //   247: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   250: invokevirtual toString : ()Ljava/lang/String;
    //   253: invokevirtual error : (CLjava/lang/String;)V
    //   256: iconst_0
    //   257: ireturn
    //   258: aconst_null
    //   259: aload #5
    //   261: aconst_null
    //   262: aload_2
    //   263: aload_3
    //   264: aload #4
    //   266: invokestatic importDefinitions : (Ljava/lang/String;Lgnu/expr/ModuleInfo;Lgnu/mapping/Procedure;Ljava/util/Vector;Lgnu/expr/ScopeExp;Lgnu/expr/Compilation;)Z
    //   269: ireturn
    //   270: aload #5
    //   272: astore_1
    //   273: aload #7
    //   275: instanceof gnu/mapping/Symbol
    //   278: ifeq -> 184
    //   281: aload #5
    //   283: astore_1
    //   284: aload #4
    //   286: aload #7
    //   288: invokevirtual selfEvaluatingSymbol : (Ljava/lang/Object;)Z
    //   291: ifne -> 184
    //   294: aload #4
    //   296: invokevirtual getLanguage : ()Lgnu/expr/Language;
    //   299: aload #4
    //   301: aload #7
    //   303: iconst_0
    //   304: invokevirtual rewrite : (Ljava/lang/Object;Z)Lgnu/expr/Expression;
    //   307: invokevirtual getTypeFor : (Lgnu/expr/Expression;)Lgnu/bytecode/Type;
    //   310: astore #5
    //   312: aload #5
    //   314: astore_1
    //   315: aload #5
    //   317: instanceof gnu/bytecode/ClassType
    //   320: ifeq -> 184
    //   323: aload #5
    //   325: astore_1
    //   326: aload #6
    //   328: invokevirtual getCdr : ()Ljava/lang/Object;
    //   331: instanceof gnu/lists/Pair
    //   334: ifeq -> 184
    //   337: aload #6
    //   339: invokevirtual getCdr : ()Ljava/lang/Object;
    //   342: checkcast gnu/lists/Pair
    //   345: invokevirtual getCar : ()Ljava/lang/Object;
    //   348: astore #6
    //   350: aload #5
    //   352: astore_1
    //   353: aload #6
    //   355: instanceof java/lang/CharSequence
    //   358: ifeq -> 184
    //   361: aload #6
    //   363: invokevirtual toString : ()Ljava/lang/String;
    //   366: astore_1
    //   367: aload_1
    //   368: aload_3
    //   369: invokestatic lookupModuleFromSourcePath : (Ljava/lang/String;Lgnu/expr/ScopeExp;)Lgnu/expr/ModuleInfo;
    //   372: astore #6
    //   374: aload #6
    //   376: ifnonnull -> 408
    //   379: aload #4
    //   381: bipush #101
    //   383: new java/lang/StringBuilder
    //   386: dup
    //   387: invokespecial <init> : ()V
    //   390: ldc_w 'malformed URL: '
    //   393: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   396: aload_1
    //   397: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   400: invokevirtual toString : ()Ljava/lang/String;
    //   403: invokevirtual error : (CLjava/lang/String;)V
    //   406: iconst_0
    //   407: ireturn
    //   408: aload #5
    //   410: invokevirtual getName : ()Ljava/lang/String;
    //   413: aload #6
    //   415: aconst_null
    //   416: aload_2
    //   417: aload_3
    //   418: aload #4
    //   420: invokestatic importDefinitions : (Ljava/lang/String;Lgnu/expr/ModuleInfo;Lgnu/mapping/Procedure;Ljava/util/Vector;Lgnu/expr/ScopeExp;Lgnu/expr/Compilation;)Z
    //   423: ireturn
    //   424: aload_1
    //   425: checkcast gnu/bytecode/ClassType
    //   428: invokestatic find : (Lgnu/bytecode/ClassType;)Lgnu/expr/ModuleInfo;
    //   431: astore #5
    //   433: aconst_null
    //   434: aload #5
    //   436: aconst_null
    //   437: aload_2
    //   438: aload_3
    //   439: aload #4
    //   441: invokestatic importDefinitions : (Ljava/lang/String;Lgnu/expr/ModuleInfo;Lgnu/mapping/Procedure;Ljava/util/Vector;Lgnu/expr/ScopeExp;Lgnu/expr/Compilation;)Z
    //   444: pop
    //   445: iconst_1
    //   446: ireturn
    //   447: astore_2
    //   448: aload #4
    //   450: bipush #101
    //   452: new java/lang/StringBuilder
    //   455: dup
    //   456: invokespecial <init> : ()V
    //   459: ldc_w 'unknown class '
    //   462: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   465: aload_1
    //   466: invokevirtual getName : ()Ljava/lang/String;
    //   469: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   472: invokevirtual toString : ()Ljava/lang/String;
    //   475: invokevirtual error : (CLjava/lang/String;)V
    //   478: iconst_0
    //   479: ireturn
    // Exception table:
    //   from	to	target	type
    //   424	433	447	java/lang/Exception
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/kawa/standard/require.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */