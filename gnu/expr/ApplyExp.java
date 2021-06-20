package gnu.expr;

import gnu.bytecode.CodeAttr;
import gnu.bytecode.Method;
import gnu.bytecode.Type;
import gnu.bytecode.Variable;
import gnu.kawa.util.IdentityHashTable;
import gnu.mapping.CallContext;
import gnu.mapping.OutPort;
import gnu.mapping.Procedure;
import gnu.mapping.PropertySet;
import gnu.text.SourceMessages;

public class ApplyExp extends Expression {
  public static final int INLINE_IF_CONSTANT = 4;
  
  public static final int MAY_CONTAIN_BACK_JUMP = 8;
  
  public static final int TAILCALL = 2;
  
  Expression[] args;
  
  LambdaExp context;
  
  Expression func;
  
  public ApplyExp nextCall;
  
  protected Type type;
  
  public ApplyExp(Method paramMethod, Expression... paramVarArgs) {
    this(new QuoteExp(new PrimProcedure(paramMethod)), paramVarArgs);
  }
  
  public ApplyExp(Expression paramExpression, Expression... paramVarArgs) {
    this.func = paramExpression;
    this.args = paramVarArgs;
  }
  
  public ApplyExp(Procedure paramProcedure, Expression... paramVarArgs) {
    this.func = new QuoteExp(paramProcedure);
    this.args = paramVarArgs;
  }
  
  public static Inlineable asInlineable(Procedure paramProcedure) {
    return (paramProcedure instanceof Inlineable) ? (Inlineable)paramProcedure : (Inlineable)Procedure.compilerKey.get((PropertySet)paramProcedure);
  }
  
  public static void compile(ApplyExp paramApplyExp, Compilation paramCompilation, Target paramTarget) {
    compile(paramApplyExp, paramCompilation, paramTarget, false);
  }
  
  static void compile(ApplyExp paramApplyExp, Compilation paramCompilation, Target paramTarget, boolean paramBoolean) {
    // Byte code:
    //   0: aload_0
    //   1: getfield args : [Lgnu/expr/Expression;
    //   4: arraylength
    //   5: istore #6
    //   7: aload_0
    //   8: getfield func : Lgnu/expr/Expression;
    //   11: astore #13
    //   13: aconst_null
    //   14: astore #12
    //   16: aconst_null
    //   17: astore #7
    //   19: aconst_null
    //   20: astore #10
    //   22: aconst_null
    //   23: astore #8
    //   25: aconst_null
    //   26: astore #9
    //   28: aconst_null
    //   29: astore #11
    //   31: aload #13
    //   33: instanceof gnu/expr/LambdaExp
    //   36: ifeq -> 130
    //   39: aload #13
    //   41: checkcast gnu/expr/LambdaExp
    //   44: astore #10
    //   46: aload #10
    //   48: astore #7
    //   50: aload #10
    //   52: invokevirtual getName : ()Ljava/lang/String;
    //   55: ifnonnull -> 1339
    //   58: aconst_null
    //   59: astore #8
    //   61: aload #10
    //   63: astore #7
    //   65: iload_3
    //   66: ifeq -> 429
    //   69: aload #8
    //   71: instanceof gnu/mapping/Procedure
    //   74: ifeq -> 429
    //   77: aload #8
    //   79: checkcast gnu/mapping/Procedure
    //   82: astore #10
    //   84: aload_2
    //   85: instanceof gnu/expr/IgnoreTarget
    //   88: ifeq -> 374
    //   91: aload #10
    //   93: invokevirtual isSideEffectFree : ()Z
    //   96: ifeq -> 374
    //   99: iconst_0
    //   100: istore #4
    //   102: iload #4
    //   104: iload #6
    //   106: if_icmpge -> 387
    //   109: aload_0
    //   110: getfield args : [Lgnu/expr/Expression;
    //   113: iload #4
    //   115: aaload
    //   116: aload_1
    //   117: aload_2
    //   118: invokevirtual compile : (Lgnu/expr/Compilation;Lgnu/expr/Target;)V
    //   121: iload #4
    //   123: iconst_1
    //   124: iadd
    //   125: istore #4
    //   127: goto -> 102
    //   130: aload #13
    //   132: instanceof gnu/expr/ReferenceExp
    //   135: ifeq -> 341
    //   138: aload #13
    //   140: checkcast gnu/expr/ReferenceExp
    //   143: astore #9
    //   145: aload #9
    //   147: invokevirtual contextDecl : ()Lgnu/expr/Declaration;
    //   150: astore #8
    //   152: aload #9
    //   154: getfield binding : Lgnu/expr/Declaration;
    //   157: astore #9
    //   159: aload #9
    //   161: ifnull -> 214
    //   164: aload #9
    //   166: invokevirtual isAlias : ()Z
    //   169: ifeq -> 214
    //   172: aload #9
    //   174: getfield value : Lgnu/expr/Expression;
    //   177: instanceof gnu/expr/ReferenceExp
    //   180: ifeq -> 214
    //   183: aload #9
    //   185: getfield value : Lgnu/expr/Expression;
    //   188: checkcast gnu/expr/ReferenceExp
    //   191: astore #10
    //   193: aload #8
    //   195: ifnonnull -> 214
    //   198: aload #9
    //   200: invokevirtual needsContext : ()Z
    //   203: ifne -> 214
    //   206: aload #10
    //   208: getfield binding : Lgnu/expr/Declaration;
    //   211: ifnonnull -> 324
    //   214: aload #11
    //   216: astore #10
    //   218: aload #9
    //   220: ldc2_w 65536
    //   223: invokevirtual getFlag : (J)Z
    //   226: ifne -> 313
    //   229: aload #9
    //   231: invokevirtual getValue : ()Lgnu/expr/Expression;
    //   234: astore #14
    //   236: aload #9
    //   238: invokevirtual getName : ()Ljava/lang/String;
    //   241: pop
    //   242: aload #12
    //   244: astore #9
    //   246: aload #14
    //   248: ifnull -> 270
    //   251: aload #12
    //   253: astore #9
    //   255: aload #14
    //   257: instanceof gnu/expr/LambdaExp
    //   260: ifeq -> 270
    //   263: aload #14
    //   265: checkcast gnu/expr/LambdaExp
    //   268: astore #9
    //   270: aload #9
    //   272: astore #7
    //   274: aload #11
    //   276: astore #10
    //   278: aload #14
    //   280: ifnull -> 313
    //   283: aload #9
    //   285: astore #7
    //   287: aload #11
    //   289: astore #10
    //   291: aload #14
    //   293: instanceof gnu/expr/QuoteExp
    //   296: ifeq -> 313
    //   299: aload #14
    //   301: checkcast gnu/expr/QuoteExp
    //   304: invokevirtual getValue : ()Ljava/lang/Object;
    //   307: astore #10
    //   309: aload #9
    //   311: astore #7
    //   313: aload #8
    //   315: astore #9
    //   317: aload #10
    //   319: astore #8
    //   321: goto -> 65
    //   324: aload #10
    //   326: getfield binding : Lgnu/expr/Declaration;
    //   329: astore #9
    //   331: aload #10
    //   333: invokevirtual contextDecl : ()Lgnu/expr/Declaration;
    //   336: astore #8
    //   338: goto -> 159
    //   341: aload #10
    //   343: astore #7
    //   345: aload #13
    //   347: instanceof gnu/expr/QuoteExp
    //   350: ifeq -> 1339
    //   353: aload #13
    //   355: checkcast gnu/expr/QuoteExp
    //   358: invokevirtual getValue : ()Ljava/lang/Object;
    //   361: astore #10
    //   363: aload #8
    //   365: astore #7
    //   367: aload #10
    //   369: astore #8
    //   371: goto -> 65
    //   374: aload #10
    //   376: aload_0
    //   377: aload_1
    //   378: aload_2
    //   379: invokestatic inlineCompile : (Lgnu/mapping/Procedure;Lgnu/expr/ApplyExp;Lgnu/expr/Compilation;Lgnu/expr/Target;)Z
    //   382: istore_3
    //   383: iload_3
    //   384: ifeq -> 429
    //   387: return
    //   388: astore_0
    //   389: aload_1
    //   390: invokevirtual getMessages : ()Lgnu/text/SourceMessages;
    //   393: bipush #101
    //   395: new java/lang/StringBuilder
    //   398: dup
    //   399: invokespecial <init> : ()V
    //   402: ldc 'caught exception in inline-compiler for '
    //   404: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   407: aload #8
    //   409: invokevirtual append : (Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   412: ldc ' - '
    //   414: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   417: aload_0
    //   418: invokevirtual append : (Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   421: invokevirtual toString : ()Ljava/lang/String;
    //   424: aload_0
    //   425: invokevirtual error : (CLjava/lang/String;Ljava/lang/Throwable;)V
    //   428: return
    //   429: aload_1
    //   430: invokevirtual getCode : ()Lgnu/bytecode/CodeAttr;
    //   433: astore #10
    //   435: aload #7
    //   437: ifnull -> 692
    //   440: aload #7
    //   442: getfield max_args : I
    //   445: iflt -> 458
    //   448: iload #6
    //   450: aload #7
    //   452: getfield max_args : I
    //   455: if_icmpgt -> 468
    //   458: iload #6
    //   460: aload #7
    //   462: getfield min_args : I
    //   465: if_icmpge -> 496
    //   468: new java/lang/Error
    //   471: dup
    //   472: new java/lang/StringBuilder
    //   475: dup
    //   476: invokespecial <init> : ()V
    //   479: ldc 'internal error - wrong number of parameters for '
    //   481: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   484: aload #7
    //   486: invokevirtual append : (Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   489: invokevirtual toString : ()Ljava/lang/String;
    //   492: invokespecial <init> : (Ljava/lang/String;)V
    //   495: athrow
    //   496: aload #7
    //   498: invokevirtual getCallConvention : ()I
    //   501: istore #4
    //   503: aload_1
    //   504: aload #7
    //   506: invokevirtual inlineOk : (Lgnu/expr/Expression;)Z
    //   509: ifeq -> 692
    //   512: iload #4
    //   514: iconst_2
    //   515: if_icmple -> 531
    //   518: iload #4
    //   520: iconst_3
    //   521: if_icmpne -> 692
    //   524: aload_0
    //   525: invokevirtual isTailCall : ()Z
    //   528: ifne -> 692
    //   531: aload #7
    //   533: iload #6
    //   535: invokevirtual getMethod : (I)Lgnu/bytecode/Method;
    //   538: astore #11
    //   540: aload #11
    //   542: ifnull -> 692
    //   545: new gnu/expr/PrimProcedure
    //   548: dup
    //   549: aload #11
    //   551: aload #7
    //   553: invokespecial <init> : (Lgnu/bytecode/Method;Lgnu/expr/LambdaExp;)V
    //   556: astore #8
    //   558: aload #11
    //   560: invokevirtual getStaticFlag : ()Z
    //   563: istore_3
    //   564: iconst_0
    //   565: istore #4
    //   567: iconst_0
    //   568: istore #5
    //   570: iload_3
    //   571: ifeq -> 582
    //   574: aload #7
    //   576: invokevirtual declareClosureEnv : ()Lgnu/bytecode/Variable;
    //   579: ifnull -> 624
    //   582: iload #5
    //   584: istore #4
    //   586: iload_3
    //   587: ifeq -> 593
    //   590: iconst_1
    //   591: istore #4
    //   593: aload_1
    //   594: getfield curLambda : Lgnu/expr/LambdaExp;
    //   597: aload #7
    //   599: if_acmpne -> 655
    //   602: aload #7
    //   604: getfield closureEnv : Lgnu/bytecode/Variable;
    //   607: ifnull -> 645
    //   610: aload #7
    //   612: getfield closureEnv : Lgnu/bytecode/Variable;
    //   615: astore #7
    //   617: aload #10
    //   619: aload #7
    //   621: invokevirtual emitLoad : (Lgnu/bytecode/Variable;)V
    //   624: iload #4
    //   626: ifeq -> 686
    //   629: getstatic gnu/bytecode/Type.voidType : Lgnu/bytecode/PrimType;
    //   632: astore #7
    //   634: aload #8
    //   636: aload #7
    //   638: aload_0
    //   639: aload_1
    //   640: aload_2
    //   641: invokevirtual compile : (Lgnu/bytecode/Type;Lgnu/expr/ApplyExp;Lgnu/expr/Compilation;Lgnu/expr/Target;)V
    //   644: return
    //   645: aload #7
    //   647: getfield thisVariable : Lgnu/bytecode/Variable;
    //   650: astore #7
    //   652: goto -> 617
    //   655: aload #9
    //   657: ifnull -> 674
    //   660: aload #9
    //   662: aconst_null
    //   663: iconst_0
    //   664: aload_1
    //   665: getstatic gnu/expr/Target.pushObject : Lgnu/expr/Target;
    //   668: invokevirtual load : (Lgnu/expr/AccessExp;ILgnu/expr/Compilation;Lgnu/expr/Target;)V
    //   671: goto -> 624
    //   674: aload #7
    //   676: invokevirtual getOwningLambda : ()Lgnu/expr/LambdaExp;
    //   679: aload_1
    //   680: invokevirtual loadHeapFrame : (Lgnu/expr/Compilation;)V
    //   683: goto -> 624
    //   686: aconst_null
    //   687: astore #7
    //   689: goto -> 634
    //   692: aload_0
    //   693: invokevirtual isTailCall : ()Z
    //   696: ifeq -> 787
    //   699: aload #7
    //   701: ifnull -> 787
    //   704: aload #7
    //   706: aload_1
    //   707: getfield curLambda : Lgnu/expr/LambdaExp;
    //   710: if_acmpne -> 787
    //   713: iconst_1
    //   714: istore #4
    //   716: aload #7
    //   718: ifnull -> 879
    //   721: aload #7
    //   723: invokevirtual getInlineOnly : ()Z
    //   726: ifeq -> 879
    //   729: iload #4
    //   731: ifne -> 879
    //   734: aload #7
    //   736: getfield min_args : I
    //   739: iload #6
    //   741: if_icmpne -> 879
    //   744: aload #7
    //   746: aload_0
    //   747: getfield args : [Lgnu/expr/Expression;
    //   750: aconst_null
    //   751: aload_1
    //   752: invokestatic pushArgs : (Lgnu/expr/LambdaExp;[Lgnu/expr/Expression;[ILgnu/expr/Compilation;)V
    //   755: aload #7
    //   757: sipush #128
    //   760: invokevirtual getFlag : (I)Z
    //   763: ifeq -> 793
    //   766: aload #10
    //   768: aload #7
    //   770: aconst_null
    //   771: iconst_0
    //   772: invokestatic popParams : (Lgnu/bytecode/CodeAttr;Lgnu/expr/LambdaExp;[IZ)V
    //   775: aload #10
    //   777: iconst_0
    //   778: aload #7
    //   780: invokevirtual getVarScope : ()Lgnu/bytecode/Scope;
    //   783: invokevirtual emitTailCall : (ZLgnu/bytecode/Scope;)V
    //   786: return
    //   787: iconst_0
    //   788: istore #4
    //   790: goto -> 716
    //   793: aload #7
    //   795: aload #7
    //   797: getfield flags : I
    //   800: sipush #128
    //   803: ior
    //   804: putfield flags : I
    //   807: aload_1
    //   808: getfield curLambda : Lgnu/expr/LambdaExp;
    //   811: astore_0
    //   812: aload_1
    //   813: aload #7
    //   815: putfield curLambda : Lgnu/expr/LambdaExp;
    //   818: aload #7
    //   820: aload_1
    //   821: invokevirtual allocChildClasses : (Lgnu/expr/Compilation;)V
    //   824: aload #7
    //   826: aload_1
    //   827: invokevirtual allocParameters : (Lgnu/expr/Compilation;)V
    //   830: aload #10
    //   832: aload #7
    //   834: aconst_null
    //   835: iconst_0
    //   836: invokestatic popParams : (Lgnu/bytecode/CodeAttr;Lgnu/expr/LambdaExp;[IZ)V
    //   839: aload #7
    //   841: aload_1
    //   842: invokevirtual enterFunction : (Lgnu/expr/Compilation;)V
    //   845: aload #7
    //   847: getfield body : Lgnu/expr/Expression;
    //   850: aload_1
    //   851: aload_2
    //   852: invokevirtual compileWithPosition : (Lgnu/expr/Compilation;Lgnu/expr/Target;)V
    //   855: aload #7
    //   857: aload_1
    //   858: invokevirtual compileEnd : (Lgnu/expr/Compilation;)V
    //   861: aload #7
    //   863: aload_1
    //   864: invokevirtual generateApplyMethods : (Lgnu/expr/Compilation;)V
    //   867: aload #10
    //   869: invokevirtual popScope : ()Lgnu/bytecode/Scope;
    //   872: pop
    //   873: aload_1
    //   874: aload_0
    //   875: putfield curLambda : Lgnu/expr/LambdaExp;
    //   878: return
    //   879: aload_1
    //   880: getfield curLambda : Lgnu/expr/LambdaExp;
    //   883: invokevirtual isHandlingTailCalls : ()Z
    //   886: ifeq -> 1116
    //   889: aload_0
    //   890: invokevirtual isTailCall : ()Z
    //   893: ifne -> 903
    //   896: aload_2
    //   897: instanceof gnu/expr/ConsumerTarget
    //   900: ifeq -> 1116
    //   903: aload_1
    //   904: getfield curLambda : Lgnu/expr/LambdaExp;
    //   907: invokevirtual getInlineOnly : ()Z
    //   910: ifne -> 1116
    //   913: getstatic gnu/expr/Compilation.typeCallContext : Lgnu/bytecode/ClassType;
    //   916: astore #7
    //   918: aload #13
    //   920: aload_1
    //   921: new gnu/expr/StackTarget
    //   924: dup
    //   925: getstatic gnu/expr/Compilation.typeProcedure : Lgnu/bytecode/ClassType;
    //   928: invokespecial <init> : (Lgnu/bytecode/Type;)V
    //   931: invokevirtual compile : (Lgnu/expr/Compilation;Lgnu/expr/Target;)V
    //   934: iload #6
    //   936: iconst_4
    //   937: if_icmpgt -> 1026
    //   940: iconst_0
    //   941: istore #4
    //   943: iload #4
    //   945: iload #6
    //   947: if_icmpge -> 973
    //   950: aload_0
    //   951: getfield args : [Lgnu/expr/Expression;
    //   954: iload #4
    //   956: aaload
    //   957: aload_1
    //   958: getstatic gnu/expr/Target.pushObject : Lgnu/expr/Target;
    //   961: invokevirtual compileWithPosition : (Lgnu/expr/Compilation;Lgnu/expr/Target;)V
    //   964: iload #4
    //   966: iconst_1
    //   967: iadd
    //   968: istore #4
    //   970: goto -> 943
    //   973: aload_1
    //   974: invokevirtual loadCallContext : ()V
    //   977: aload #10
    //   979: getstatic gnu/expr/Compilation.typeProcedure : Lgnu/bytecode/ClassType;
    //   982: new java/lang/StringBuilder
    //   985: dup
    //   986: invokespecial <init> : ()V
    //   989: ldc_w 'check'
    //   992: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   995: iload #6
    //   997: invokevirtual append : (I)Ljava/lang/StringBuilder;
    //   1000: invokevirtual toString : ()Ljava/lang/String;
    //   1003: iload #6
    //   1005: iconst_1
    //   1006: iadd
    //   1007: invokevirtual getDeclaredMethod : (Ljava/lang/String;I)Lgnu/bytecode/Method;
    //   1010: invokevirtual emitInvoke : (Lgnu/bytecode/Method;)V
    //   1013: aload_0
    //   1014: invokevirtual isTailCall : ()Z
    //   1017: ifeq -> 1056
    //   1020: aload #10
    //   1022: invokevirtual emitReturn : ()V
    //   1025: return
    //   1026: aload_0
    //   1027: getfield args : [Lgnu/expr/Expression;
    //   1030: aload_1
    //   1031: invokestatic compileToArray : ([Lgnu/expr/Expression;Lgnu/expr/Compilation;)V
    //   1034: aload_1
    //   1035: invokevirtual loadCallContext : ()V
    //   1038: aload #10
    //   1040: getstatic gnu/expr/Compilation.typeProcedure : Lgnu/bytecode/ClassType;
    //   1043: ldc_w 'checkN'
    //   1046: iconst_2
    //   1047: invokevirtual getDeclaredMethod : (Ljava/lang/String;I)Lgnu/bytecode/Method;
    //   1050: invokevirtual emitInvoke : (Lgnu/bytecode/Method;)V
    //   1053: goto -> 1013
    //   1056: aload_2
    //   1057: checkcast gnu/expr/ConsumerTarget
    //   1060: invokevirtual isContextTarget : ()Z
    //   1063: ifeq -> 1085
    //   1066: aload_1
    //   1067: invokevirtual loadCallContext : ()V
    //   1070: aload #10
    //   1072: aload #7
    //   1074: ldc_w 'runUntilDone'
    //   1077: iconst_0
    //   1078: invokevirtual getDeclaredMethod : (Ljava/lang/String;I)Lgnu/bytecode/Method;
    //   1081: invokevirtual emitInvoke : (Lgnu/bytecode/Method;)V
    //   1084: return
    //   1085: aload_1
    //   1086: invokevirtual loadCallContext : ()V
    //   1089: aload #10
    //   1091: aload_2
    //   1092: checkcast gnu/expr/ConsumerTarget
    //   1095: invokevirtual getConsumerVariable : ()Lgnu/bytecode/Variable;
    //   1098: invokevirtual emitLoad : (Lgnu/bytecode/Variable;)V
    //   1101: aload #10
    //   1103: aload #7
    //   1105: ldc_w 'runUntilValue'
    //   1108: iconst_1
    //   1109: invokevirtual getDeclaredMethod : (Ljava/lang/String;I)Lgnu/bytecode/Method;
    //   1112: invokevirtual emitInvoke : (Lgnu/bytecode/Method;)V
    //   1115: return
    //   1116: iload #4
    //   1118: ifne -> 1137
    //   1121: aload #13
    //   1123: aload_1
    //   1124: new gnu/expr/StackTarget
    //   1127: dup
    //   1128: getstatic gnu/expr/Compilation.typeProcedure : Lgnu/bytecode/ClassType;
    //   1131: invokespecial <init> : (Lgnu/bytecode/Type;)V
    //   1134: invokevirtual compile : (Lgnu/expr/Compilation;Lgnu/expr/Target;)V
    //   1137: iload #4
    //   1139: ifeq -> 1199
    //   1142: aload #7
    //   1144: getfield min_args : I
    //   1147: aload #7
    //   1149: getfield max_args : I
    //   1152: if_icmpeq -> 1194
    //   1155: iconst_1
    //   1156: istore_3
    //   1157: aconst_null
    //   1158: astore #8
    //   1160: iload_3
    //   1161: ifeq -> 1215
    //   1164: aload_0
    //   1165: getfield args : [Lgnu/expr/Expression;
    //   1168: aload_1
    //   1169: invokestatic compileToArray : ([Lgnu/expr/Expression;Lgnu/expr/Compilation;)V
    //   1172: getstatic gnu/expr/Compilation.applyNmethod : Lgnu/bytecode/Method;
    //   1175: astore_0
    //   1176: aload #10
    //   1178: invokevirtual reachableHere : ()Z
    //   1181: ifne -> 1297
    //   1184: aload_1
    //   1185: bipush #101
    //   1187: ldc_w 'unreachable code'
    //   1190: invokevirtual error : (CLjava/lang/String;)V
    //   1193: return
    //   1194: iconst_0
    //   1195: istore_3
    //   1196: goto -> 1157
    //   1199: iload #6
    //   1201: iconst_4
    //   1202: if_icmple -> 1210
    //   1205: iconst_1
    //   1206: istore_3
    //   1207: goto -> 1157
    //   1210: iconst_0
    //   1211: istore_3
    //   1212: goto -> 1157
    //   1215: iload #4
    //   1217: ifeq -> 1246
    //   1220: aload_0
    //   1221: getfield args : [Lgnu/expr/Expression;
    //   1224: arraylength
    //   1225: newarray int
    //   1227: astore #8
    //   1229: aload #7
    //   1231: aload_0
    //   1232: getfield args : [Lgnu/expr/Expression;
    //   1235: aload #8
    //   1237: aload_1
    //   1238: invokestatic pushArgs : (Lgnu/expr/LambdaExp;[Lgnu/expr/Expression;[ILgnu/expr/Compilation;)V
    //   1241: aconst_null
    //   1242: astore_0
    //   1243: goto -> 1176
    //   1246: iconst_0
    //   1247: istore #5
    //   1249: iload #5
    //   1251: iload #6
    //   1253: if_icmpge -> 1278
    //   1256: aload_0
    //   1257: getfield args : [Lgnu/expr/Expression;
    //   1260: iload #5
    //   1262: aaload
    //   1263: aload_1
    //   1264: getstatic gnu/expr/Target.pushObject : Lgnu/expr/Target;
    //   1267: invokevirtual compileWithPosition : (Lgnu/expr/Compilation;Lgnu/expr/Target;)V
    //   1270: aload #10
    //   1272: invokevirtual reachableHere : ()Z
    //   1275: ifne -> 1288
    //   1278: getstatic gnu/expr/Compilation.applymethods : [Lgnu/bytecode/Method;
    //   1281: iload #6
    //   1283: aaload
    //   1284: astore_0
    //   1285: goto -> 1176
    //   1288: iload #5
    //   1290: iconst_1
    //   1291: iadd
    //   1292: istore #5
    //   1294: goto -> 1249
    //   1297: iload #4
    //   1299: ifeq -> 1324
    //   1302: aload #10
    //   1304: aload #7
    //   1306: aload #8
    //   1308: iload_3
    //   1309: invokestatic popParams : (Lgnu/bytecode/CodeAttr;Lgnu/expr/LambdaExp;[IZ)V
    //   1312: aload #10
    //   1314: iconst_0
    //   1315: aload #7
    //   1317: invokevirtual getVarScope : ()Lgnu/bytecode/Scope;
    //   1320: invokevirtual emitTailCall : (ZLgnu/bytecode/Scope;)V
    //   1323: return
    //   1324: aload #10
    //   1326: aload_0
    //   1327: invokevirtual emitInvokeVirtual : (Lgnu/bytecode/Method;)V
    //   1330: aload_2
    //   1331: aload_1
    //   1332: getstatic gnu/bytecode/Type.pointer_type : Lgnu/bytecode/ClassType;
    //   1335: invokevirtual compileFromStack : (Lgnu/expr/Compilation;Lgnu/bytecode/Type;)V
    //   1338: return
    //   1339: aconst_null
    //   1340: astore #8
    //   1342: goto -> 65
    // Exception table:
    //   from	to	target	type
    //   374	383	388	java/lang/Throwable
  }
  
  public static void compileToArray(Expression[] paramArrayOfExpression, Compilation paramCompilation) {
    CodeAttr codeAttr = paramCompilation.getCode();
    if (paramArrayOfExpression.length == 0) {
      codeAttr.emitGetStatic(Compilation.noArgsField);
      return;
    } 
    codeAttr.emitPushInt(paramArrayOfExpression.length);
    codeAttr.emitNewArray((Type)Type.pointer_type);
    int i = 0;
    while (true) {
      if (i < paramArrayOfExpression.length) {
        Expression expression = paramArrayOfExpression[i];
        if (paramCompilation.usingCPStyle() && !(expression instanceof QuoteExp) && !(expression instanceof ReferenceExp)) {
          expression.compileWithPosition(paramCompilation, Target.pushObject);
          codeAttr.emitSwap();
          codeAttr.emitDup(1, 1);
          codeAttr.emitSwap();
          codeAttr.emitPushInt(i);
          codeAttr.emitSwap();
        } else {
          codeAttr.emitDup((Type)Compilation.objArrayType);
          codeAttr.emitPushInt(i);
          expression.compileWithPosition(paramCompilation, Target.pushObject);
        } 
        codeAttr.emitArrayStore((Type)Type.pointer_type);
        i++;
        continue;
      } 
      return;
    } 
  }
  
  static Expression derefFunc(Expression paramExpression) {
    Expression expression = paramExpression;
    if (paramExpression instanceof ReferenceExp) {
      Declaration declaration = Declaration.followAliases(((ReferenceExp)paramExpression).binding);
      expression = paramExpression;
      if (declaration != null) {
        expression = paramExpression;
        if (!declaration.getFlag(65536L))
          expression = declaration.getValue(); 
      } 
    } 
    return expression;
  }
  
  static boolean inlineCompile(Procedure paramProcedure, ApplyExp paramApplyExp, Compilation paramCompilation, Target paramTarget) throws Throwable {
    Inlineable inlineable = asInlineable(paramProcedure);
    if (inlineable == null)
      return false; 
    inlineable.compile(paramApplyExp, paramCompilation, paramTarget);
    return true;
  }
  
  private static void popParams(CodeAttr paramCodeAttr, int paramInt1, int paramInt2, int[] paramArrayOfint, Declaration paramDeclaration, Variable paramVariable) {
    if (paramInt2 > 0) {
      Variable variable;
      Declaration declaration = paramDeclaration.nextDecl();
      if (paramDeclaration.getVariable() == null) {
        variable = paramVariable;
      } else {
        variable = paramVariable.nextVar();
      } 
      popParams(paramCodeAttr, paramInt1 + 1, paramInt2 - 1, paramArrayOfint, declaration, variable);
      if (!paramDeclaration.ignorable()) {
        if (paramArrayOfint != null && paramArrayOfint[paramInt1] != 65536) {
          paramCodeAttr.emitInc(paramVariable, (short)paramArrayOfint[paramInt1]);
          return;
        } 
      } else {
        return;
      } 
    } else {
      return;
    } 
    paramCodeAttr.emitStore(paramVariable);
  }
  
  private static void popParams(CodeAttr paramCodeAttr, LambdaExp paramLambdaExp, int[] paramArrayOfint, boolean paramBoolean) {
    Variable variable2 = paramLambdaExp.getVarScope().firstVar();
    Declaration declaration = paramLambdaExp.firstDecl();
    Variable variable1 = variable2;
    if (variable2 != null) {
      variable1 = variable2;
      if (variable2.getName() == "this")
        variable1 = variable2.nextVar(); 
    } 
    variable2 = variable1;
    if (variable1 != null) {
      variable2 = variable1;
      if (variable1.getName() == "$ctx")
        variable2 = variable1.nextVar(); 
    } 
    variable1 = variable2;
    if (variable2 != null) {
      variable1 = variable2;
      if (variable2.getName() == "argsArray") {
        if (paramBoolean) {
          popParams(paramCodeAttr, 0, 1, (int[])null, declaration, variable2);
          return;
        } 
        variable1 = variable2.nextVar();
      } 
    } 
    popParams(paramCodeAttr, 0, paramLambdaExp.min_args, paramArrayOfint, declaration, variable1);
  }
  
  private static void pushArgs(LambdaExp paramLambdaExp, Expression[] paramArrayOfExpression, int[] paramArrayOfint, Compilation paramCompilation) {
    // Byte code:
    //   0: aload_0
    //   1: invokevirtual firstDecl : ()Lgnu/expr/Declaration;
    //   4: astore_0
    //   5: aload_1
    //   6: arraylength
    //   7: istore #5
    //   9: iconst_0
    //   10: istore #4
    //   12: iload #4
    //   14: iload #5
    //   16: if_icmpge -> 97
    //   19: aload_1
    //   20: iload #4
    //   22: aaload
    //   23: astore #7
    //   25: aload_0
    //   26: invokevirtual ignorable : ()Z
    //   29: ifeq -> 55
    //   32: aload #7
    //   34: aload_3
    //   35: getstatic gnu/expr/Target.Ignore : Lgnu/expr/Target;
    //   38: invokevirtual compile : (Lgnu/expr/Compilation;Lgnu/expr/Target;)V
    //   41: aload_0
    //   42: invokevirtual nextDecl : ()Lgnu/expr/Declaration;
    //   45: astore_0
    //   46: iload #4
    //   48: iconst_1
    //   49: iadd
    //   50: istore #4
    //   52: goto -> 12
    //   55: aload_2
    //   56: ifnull -> 81
    //   59: aload #7
    //   61: aload_0
    //   62: invokestatic canUseInc : (Lgnu/expr/Expression;Lgnu/expr/Declaration;)I
    //   65: istore #6
    //   67: aload_2
    //   68: iload #4
    //   70: iload #6
    //   72: iastore
    //   73: iload #6
    //   75: ldc_w 65536
    //   78: if_icmpne -> 41
    //   81: aload #7
    //   83: aload_3
    //   84: aload_0
    //   85: invokevirtual getType : ()Lgnu/bytecode/Type;
    //   88: invokestatic getInstance : (Lgnu/bytecode/Type;)Lgnu/expr/Target;
    //   91: invokevirtual compileWithPosition : (Lgnu/expr/Compilation;Lgnu/expr/Target;)V
    //   94: goto -> 41
    //   97: return
  }
  
  public void apply(CallContext paramCallContext) throws Throwable {
    Object object = this.func.eval(paramCallContext);
    int j = this.args.length;
    Object[] arrayOfObject = new Object[j];
    for (int i = 0; i < j; i++)
      arrayOfObject[i] = this.args[i].eval(paramCallContext); 
    ((Procedure)object).checkN(arrayOfObject, paramCallContext);
  }
  
  public void compile(Compilation paramCompilation, Target paramTarget) {
    compile(this, paramCompilation, paramTarget, true);
  }
  
  public Expression deepCopy(IdentityHashTable paramIdentityHashTable) {
    Expression expression = deepCopy(this.func, paramIdentityHashTable);
    Expression[] arrayOfExpression = deepCopy(this.args, paramIdentityHashTable);
    if ((expression == null && this.func != null) || (arrayOfExpression == null && this.args != null))
      return null; 
    ApplyExp applyExp = new ApplyExp(expression, arrayOfExpression);
    applyExp.flags = getFlags();
    return applyExp;
  }
  
  public Expression getArg(int paramInt) {
    return this.args[paramInt];
  }
  
  public final int getArgCount() {
    return this.args.length;
  }
  
  public final Expression[] getArgs() {
    return this.args;
  }
  
  public final Expression getFunction() {
    return this.func;
  }
  
  public final Object getFunctionValue() {
    return (this.func instanceof QuoteExp) ? ((QuoteExp)this.func).getValue() : null;
  }
  
  public final Type getType() {
    if (this.type != null)
      return this.type; 
    Object object = derefFunc(this.func);
    this.type = (Type)Type.objectType;
    if (object instanceof QuoteExp) {
      object = ((QuoteExp)object).getValue();
      if (object instanceof Procedure)
        this.type = ((Procedure)object).getReturnType(this.args); 
      return this.type;
    } 
    if (object instanceof LambdaExp)
      this.type = ((LambdaExp)object).getReturnType(); 
    return this.type;
  }
  
  public final Type getTypeRaw() {
    return this.type;
  }
  
  public final Expression inlineIfConstant(Procedure paramProcedure, InlineCalls paramInlineCalls) {
    return inlineIfConstant(paramProcedure, paramInlineCalls.getMessages());
  }
  
  public final Expression inlineIfConstant(Procedure paramProcedure, SourceMessages paramSourceMessages) {
    // Byte code:
    //   0: aload_0
    //   1: getfield args : [Lgnu/expr/Expression;
    //   4: arraylength
    //   5: istore_3
    //   6: iload_3
    //   7: anewarray java/lang/Object
    //   10: astore #6
    //   12: iload_3
    //   13: iconst_1
    //   14: isub
    //   15: istore_3
    //   16: iload_3
    //   17: iflt -> 103
    //   20: aload_0
    //   21: getfield args : [Lgnu/expr/Expression;
    //   24: iload_3
    //   25: aaload
    //   26: astore #5
    //   28: aload #5
    //   30: astore #4
    //   32: aload #5
    //   34: instanceof gnu/expr/ReferenceExp
    //   37: ifeq -> 80
    //   40: aload #5
    //   42: checkcast gnu/expr/ReferenceExp
    //   45: invokevirtual getBinding : ()Lgnu/expr/Declaration;
    //   48: astore #7
    //   50: aload #5
    //   52: astore #4
    //   54: aload #7
    //   56: ifnull -> 80
    //   59: aload #7
    //   61: invokevirtual getValue : ()Lgnu/expr/Expression;
    //   64: astore #5
    //   66: aload #5
    //   68: astore #4
    //   70: aload #5
    //   72: getstatic gnu/expr/QuoteExp.undefined_exp : Lgnu/expr/QuoteExp;
    //   75: if_acmpne -> 80
    //   78: aload_0
    //   79: areturn
    //   80: aload #4
    //   82: instanceof gnu/expr/QuoteExp
    //   85: ifeq -> 78
    //   88: aload #6
    //   90: iload_3
    //   91: aload #4
    //   93: checkcast gnu/expr/QuoteExp
    //   96: invokevirtual getValue : ()Ljava/lang/Object;
    //   99: aastore
    //   100: goto -> 12
    //   103: new gnu/expr/QuoteExp
    //   106: dup
    //   107: aload_1
    //   108: aload #6
    //   110: invokevirtual applyN : ([Ljava/lang/Object;)Ljava/lang/Object;
    //   113: aload_0
    //   114: getfield type : Lgnu/bytecode/Type;
    //   117: invokespecial <init> : (Ljava/lang/Object;Lgnu/bytecode/Type;)V
    //   120: astore #4
    //   122: aload #4
    //   124: areturn
    //   125: astore #4
    //   127: aload_2
    //   128: ifnull -> 78
    //   131: aload_2
    //   132: bipush #119
    //   134: new java/lang/StringBuilder
    //   137: dup
    //   138: invokespecial <init> : ()V
    //   141: ldc_w 'call to '
    //   144: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   147: aload_1
    //   148: invokevirtual append : (Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   151: ldc_w ' throws '
    //   154: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   157: aload #4
    //   159: invokevirtual append : (Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   162: invokevirtual toString : ()Ljava/lang/String;
    //   165: invokevirtual error : (CLjava/lang/String;)V
    //   168: aload_0
    //   169: areturn
    // Exception table:
    //   from	to	target	type
    //   103	122	125	java/lang/Throwable
  }
  
  public final boolean isTailCall() {
    return getFlag(2);
  }
  
  protected boolean mustCompile() {
    return false;
  }
  
  public void print(OutPort paramOutPort) {
    paramOutPort.startLogicalBlock("(Apply", ")", 2);
    if (isTailCall())
      paramOutPort.print(" [tailcall]"); 
    if (this.type != null && this.type != Type.pointer_type) {
      paramOutPort.print(" => ");
      paramOutPort.print(this.type);
    } 
    paramOutPort.writeSpaceFill();
    printLineColumn(paramOutPort);
    this.func.print(paramOutPort);
    for (int i = 0; i < this.args.length; i++) {
      paramOutPort.writeSpaceLinear();
      this.args[i].print(paramOutPort);
    } 
    paramOutPort.endLogicalBlock(")");
  }
  
  public void setArg(int paramInt, Expression paramExpression) {
    this.args[paramInt] = paramExpression;
  }
  
  public void setArgs(Expression[] paramArrayOfExpression) {
    this.args = paramArrayOfExpression;
  }
  
  public void setFunction(Expression paramExpression) {
    this.func = paramExpression;
  }
  
  public final void setTailCall(boolean paramBoolean) {
    setFlag(paramBoolean, 2);
  }
  
  public final void setType(Type paramType) {
    this.type = paramType;
  }
  
  public boolean side_effects() {
    Object object = derefFunc(this.func).valueIfConstant();
    if (object instanceof Procedure && ((Procedure)object).isSideEffectFree()) {
      object = this.args;
      int j = object.length;
      int i = 0;
      while (i < j) {
        if (!object[i].side_effects()) {
          i++;
          continue;
        } 
        return true;
      } 
    } else {
      return true;
    } 
    return false;
  }
  
  public String toString() {
    if (this == LambdaExp.unknownContinuation)
      return "ApplyExp[unknownContinuation]"; 
    StringBuilder stringBuilder = (new StringBuilder()).append("ApplyExp/");
    if (this.args == null) {
      boolean bool = false;
      return stringBuilder.append(bool).append('[').append(this.func).append(']').toString();
    } 
    int i = this.args.length;
    return stringBuilder.append(i).append('[').append(this.func).append(']').toString();
  }
  
  protected <R, D> R visit(ExpVisitor<R, D> paramExpVisitor, D paramD) {
    return paramExpVisitor.visitApplyExp(this, paramD);
  }
  
  public void visitArgs(InlineCalls paramInlineCalls) {
    this.args = paramInlineCalls.visitExps(this.args, this.args.length, null);
  }
  
  protected <R, D> void visitChildren(ExpVisitor<R, D> paramExpVisitor, D paramD) {
    this.func = paramExpVisitor.visitAndUpdate(this.func, paramD);
    if (paramExpVisitor.exitValue == null)
      this.args = paramExpVisitor.visitExps(this.args, this.args.length, paramD); 
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/gnu/expr/ApplyExp.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */