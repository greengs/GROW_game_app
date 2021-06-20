package gnu.expr;

import gnu.bytecode.ArrayType;
import gnu.bytecode.ClassType;
import gnu.bytecode.CodeAttr;
import gnu.bytecode.Field;
import gnu.bytecode.Filter;
import gnu.bytecode.Member;
import gnu.bytecode.Method;
import gnu.bytecode.ObjectType;
import gnu.bytecode.PrimType;
import gnu.bytecode.Type;
import gnu.bytecode.Variable;
import gnu.lists.LList;
import gnu.mapping.CallContext;
import gnu.mapping.OutPort;
import gnu.mapping.Procedure;
import gnu.mapping.Values;
import gnu.mapping.WrappedException;
import java.util.Set;
import java.util.Vector;

public class LambdaExp extends ScopeExp {
  public static final int ATTEMPT_INLINE = 4096;
  
  static final int CANNOT_INLINE = 32;
  
  static final int CAN_CALL = 4;
  
  static final int CAN_READ = 2;
  
  static final int CLASS_METHOD = 64;
  
  static final int DEFAULT_CAPTURES_ARG = 512;
  
  static final int IMPORTS_LEX_VARS = 8;
  
  static final int INLINE_ONLY = 8192;
  
  static final int METHODS_COMPILED = 128;
  
  static final int NEEDS_STATIC_LINK = 16;
  
  protected static final int NEXT_AVAIL_FLAG = 16384;
  
  public static final int NO_FIELD = 256;
  
  public static final int OVERLOADABLE_FIELD = 2048;
  
  public static final int SEQUENCE_RESULT = 1024;
  
  static Method searchForKeywordMethod3;
  
  static Method searchForKeywordMethod4;
  
  static final ApplyExp unknownContinuation = new ApplyExp((Expression)null, (Expression[])null);
  
  Vector applyMethods;
  
  Variable argsArray;
  
  public Expression body;
  
  Declaration capturedVars;
  
  Variable closureEnv;
  
  public Field closureEnvField;
  
  public Expression[] defaultArgs;
  
  private Declaration firstArgsArrayArg;
  
  public LambdaExp firstChild;
  
  Variable heapFrame;
  
  Initializer initChain;
  
  public LambdaExp inlineHome;
  
  public Keyword[] keywords;
  
  public int max_args;
  
  public int min_args;
  
  public Declaration nameDecl;
  
  public LambdaExp nextSibling;
  
  Method[] primBodyMethods;
  
  Method[] primMethods;
  
  Object[] properties;
  
  public Expression returnContinuation;
  
  public Type returnType;
  
  int selectorValue;
  
  public Field staticLinkField;
  
  Set<LambdaExp> tailCallers;
  
  Procedure thisValue;
  
  Variable thisVariable;
  
  Expression[] throwsSpecification;
  
  ClassType type = Compilation.typeProcedure;
  
  public LambdaExp() {}
  
  public LambdaExp(int paramInt) {
    this.min_args = paramInt;
    this.max_args = paramInt;
  }
  
  public LambdaExp(Expression paramExpression) {
    this.body = paramExpression;
  }
  
  final void addApplyMethod(Compilation paramCompilation, Field paramField) {
    // Byte code:
    //   0: aload_0
    //   1: astore #4
    //   3: aload #4
    //   5: astore_3
    //   6: aload_2
    //   7: ifnull -> 52
    //   10: aload #4
    //   12: astore_3
    //   13: aload_2
    //   14: invokevirtual getStaticFlag : ()Z
    //   17: ifeq -> 52
    //   20: aload_1
    //   21: invokevirtual getModule : ()Lgnu/expr/ModuleExp;
    //   24: astore_2
    //   25: aload_2
    //   26: getfield applyMethods : Ljava/util/Vector;
    //   29: ifnonnull -> 43
    //   32: aload_2
    //   33: new java/util/Vector
    //   36: dup
    //   37: invokespecial <init> : ()V
    //   40: putfield applyMethods : Ljava/util/Vector;
    //   43: aload_2
    //   44: getfield applyMethods : Ljava/util/Vector;
    //   47: aload_0
    //   48: invokevirtual addElement : (Ljava/lang/Object;)V
    //   51: return
    //   52: aload_3
    //   53: invokevirtual outerLambda : ()Lgnu/expr/LambdaExp;
    //   56: astore #4
    //   58: aload #4
    //   60: instanceof gnu/expr/ModuleExp
    //   63: ifne -> 77
    //   66: aload #4
    //   68: astore_3
    //   69: aload #4
    //   71: getfield heapFrame : Lgnu/bytecode/Variable;
    //   74: ifnull -> 52
    //   77: aload #4
    //   79: astore_2
    //   80: aload #4
    //   82: invokevirtual getHeapFrameType : ()Lgnu/bytecode/ClassType;
    //   85: invokevirtual getSuperclass : ()Lgnu/bytecode/ClassType;
    //   88: getstatic gnu/expr/Compilation.typeModuleBody : Lgnu/bytecode/ClassType;
    //   91: invokevirtual isSubtype : (Lgnu/bytecode/Type;)Z
    //   94: ifne -> 25
    //   97: aload_1
    //   98: invokevirtual getModule : ()Lgnu/expr/ModuleExp;
    //   101: astore_2
    //   102: goto -> 25
  }
  
  void addMethodFor(ClassType paramClassType, Compilation paramCompilation, ObjectType paramObjectType) {
    // Byte code:
    //   0: aload_0
    //   1: invokevirtual getName : ()Ljava/lang/String;
    //   4: astore #18
    //   6: aload_0
    //   7: invokevirtual outerLambda : ()Lgnu/expr/LambdaExp;
    //   10: astore #23
    //   12: aload_0
    //   13: getfield keywords : [Lgnu/expr/Keyword;
    //   16: ifnonnull -> 594
    //   19: iconst_0
    //   20: istore #10
    //   22: aload_0
    //   23: getfield defaultArgs : [Lgnu/expr/Expression;
    //   26: ifnonnull -> 604
    //   29: iconst_0
    //   30: istore #8
    //   32: aload_0
    //   33: getfield flags : I
    //   36: sipush #512
    //   39: iand
    //   40: ifeq -> 617
    //   43: iconst_0
    //   44: istore #11
    //   46: aload_0
    //   47: getfield max_args : I
    //   50: iflt -> 67
    //   53: aload_0
    //   54: getfield min_args : I
    //   57: iload #11
    //   59: iadd
    //   60: aload_0
    //   61: getfield max_args : I
    //   64: if_icmpge -> 624
    //   67: iconst_1
    //   68: istore #12
    //   70: iload #11
    //   72: iconst_1
    //   73: iadd
    //   74: anewarray gnu/bytecode/Method
    //   77: astore #24
    //   79: aload_0
    //   80: aload #24
    //   82: putfield primBodyMethods : [Lgnu/bytecode/Method;
    //   85: aload_0
    //   86: getfield primMethods : [Lgnu/bytecode/Method;
    //   89: ifnonnull -> 98
    //   92: aload_0
    //   93: aload #24
    //   95: putfield primMethods : [Lgnu/bytecode/Method;
    //   98: iconst_0
    //   99: istore #9
    //   101: aload_0
    //   102: getfield nameDecl : Lgnu/expr/Declaration;
    //   105: ifnull -> 630
    //   108: aload_0
    //   109: getfield nameDecl : Lgnu/expr/Declaration;
    //   112: ldc2_w 4096
    //   115: invokevirtual getFlag : (J)Z
    //   118: ifeq -> 630
    //   121: iconst_0
    //   122: istore #5
    //   124: new java/lang/StringBuffer
    //   127: dup
    //   128: bipush #60
    //   130: invokespecial <init> : (I)V
    //   133: astore #25
    //   135: iload #5
    //   137: ifeq -> 824
    //   140: bipush #8
    //   142: istore #7
    //   144: iload #7
    //   146: istore #6
    //   148: aload_0
    //   149: getfield nameDecl : Lgnu/expr/Declaration;
    //   152: ifnull -> 171
    //   155: aload_0
    //   156: getfield nameDecl : Lgnu/expr/Declaration;
    //   159: invokevirtual needsExternalAccess : ()Z
    //   162: ifeq -> 830
    //   165: iload #7
    //   167: iconst_1
    //   168: ior
    //   169: istore #6
    //   171: aload #23
    //   173: invokevirtual isModuleBody : ()Z
    //   176: ifne -> 187
    //   179: aload #23
    //   181: instanceof gnu/expr/ClassExp
    //   184: ifeq -> 192
    //   187: aload #18
    //   189: ifnonnull -> 222
    //   192: aload #25
    //   194: ldc 'lambda'
    //   196: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   199: pop
    //   200: aload_2
    //   201: getfield method_counter : I
    //   204: iconst_1
    //   205: iadd
    //   206: istore #7
    //   208: aload_2
    //   209: iload #7
    //   211: putfield method_counter : I
    //   214: aload #25
    //   216: iload #7
    //   218: invokevirtual append : (I)Ljava/lang/StringBuffer;
    //   221: pop
    //   222: iload #9
    //   224: bipush #67
    //   226: if_icmpne -> 881
    //   229: aload #25
    //   231: ldc '<clinit>'
    //   233: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   236: pop
    //   237: aload_0
    //   238: sipush #1024
    //   241: invokevirtual getFlag : (I)Z
    //   244: ifeq -> 255
    //   247: aload #25
    //   249: ldc '$C'
    //   251: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   254: pop
    //   255: aload_0
    //   256: invokevirtual getCallConvention : ()I
    //   259: iconst_2
    //   260: if_icmplt -> 902
    //   263: iload #9
    //   265: ifne -> 902
    //   268: iconst_1
    //   269: istore #13
    //   271: iload #6
    //   273: istore #7
    //   275: iload #9
    //   277: ifeq -> 294
    //   280: iload #5
    //   282: ifeq -> 908
    //   285: iload #6
    //   287: bipush #-3
    //   289: iand
    //   290: iconst_1
    //   291: iadd
    //   292: istore #7
    //   294: aload_1
    //   295: invokevirtual isInterface : ()Z
    //   298: ifne -> 312
    //   301: iload #7
    //   303: istore #5
    //   305: aload_0
    //   306: invokevirtual isAbstract : ()Z
    //   309: ifeq -> 320
    //   312: iload #7
    //   314: sipush #1024
    //   317: ior
    //   318: istore #5
    //   320: aload_0
    //   321: invokevirtual isClassMethod : ()Z
    //   324: ifeq -> 370
    //   327: aload #23
    //   329: instanceof gnu/expr/ClassExp
    //   332: ifeq -> 370
    //   335: aload_0
    //   336: getfield min_args : I
    //   339: aload_0
    //   340: getfield max_args : I
    //   343: if_icmpne -> 370
    //   346: aconst_null
    //   347: astore #19
    //   349: iconst_0
    //   350: istore #6
    //   352: aload_0
    //   353: invokevirtual firstDecl : ()Lgnu/expr/Declaration;
    //   356: astore #20
    //   358: aload #20
    //   360: ifnonnull -> 919
    //   363: aload_0
    //   364: getfield returnType : Lgnu/bytecode/Type;
    //   367: ifnull -> 976
    //   370: aload_0
    //   371: sipush #1024
    //   374: invokevirtual getFlag : (I)Z
    //   377: ifne -> 388
    //   380: aload_0
    //   381: invokevirtual getCallConvention : ()I
    //   384: iconst_2
    //   385: if_icmplt -> 1125
    //   388: getstatic gnu/bytecode/Type.voidType : Lgnu/bytecode/PrimType;
    //   391: astore #21
    //   393: aload_3
    //   394: ifnull -> 1137
    //   397: aload_3
    //   398: aload_1
    //   399: if_acmpeq -> 1137
    //   402: iconst_1
    //   403: istore #6
    //   405: iconst_0
    //   406: istore #14
    //   408: iload #14
    //   410: istore #7
    //   412: aload_0
    //   413: invokevirtual getCallConvention : ()I
    //   416: iconst_2
    //   417: if_icmplt -> 432
    //   420: iload #14
    //   422: istore #7
    //   424: iload #9
    //   426: ifne -> 432
    //   429: iconst_1
    //   430: istore #7
    //   432: aload #25
    //   434: invokevirtual length : ()I
    //   437: istore #17
    //   439: iconst_0
    //   440: istore #9
    //   442: iload #9
    //   444: iload #11
    //   446: if_icmpgt -> 1883
    //   449: aload #25
    //   451: iload #17
    //   453: invokevirtual setLength : (I)V
    //   456: aload_0
    //   457: getfield min_args : I
    //   460: iload #9
    //   462: iadd
    //   463: istore #16
    //   465: iload #16
    //   467: istore #14
    //   469: iload #14
    //   471: istore #15
    //   473: iload #9
    //   475: iload #11
    //   477: if_icmpne -> 495
    //   480: iload #14
    //   482: istore #15
    //   484: iload #12
    //   486: ifeq -> 495
    //   489: iload #14
    //   491: iconst_1
    //   492: iadd
    //   493: istore #15
    //   495: iload #6
    //   497: iload #15
    //   499: iadd
    //   500: iload #7
    //   502: iadd
    //   503: anewarray gnu/bytecode/Type
    //   506: astore #22
    //   508: iload #6
    //   510: ifle -> 518
    //   513: aload #22
    //   515: iconst_0
    //   516: aload_3
    //   517: aastore
    //   518: aload_0
    //   519: invokevirtual firstDecl : ()Lgnu/expr/Declaration;
    //   522: astore #19
    //   524: aload #19
    //   526: astore #18
    //   528: aload #19
    //   530: ifnull -> 552
    //   533: aload #19
    //   535: astore #18
    //   537: aload #19
    //   539: invokevirtual isThisParameter : ()Z
    //   542: ifeq -> 552
    //   545: aload #19
    //   547: invokevirtual nextDecl : ()Lgnu/expr/Declaration;
    //   550: astore #18
    //   552: iconst_0
    //   553: istore #14
    //   555: iload #14
    //   557: iload #16
    //   559: if_icmpge -> 1143
    //   562: aload #22
    //   564: iload #6
    //   566: iload #14
    //   568: iadd
    //   569: aload #18
    //   571: invokevirtual getType : ()Lgnu/bytecode/Type;
    //   574: invokevirtual getImplementationType : ()Lgnu/bytecode/Type;
    //   577: aastore
    //   578: aload #18
    //   580: invokevirtual nextDecl : ()Lgnu/expr/Declaration;
    //   583: astore #18
    //   585: iload #14
    //   587: iconst_1
    //   588: iadd
    //   589: istore #14
    //   591: goto -> 555
    //   594: aload_0
    //   595: getfield keywords : [Lgnu/expr/Keyword;
    //   598: arraylength
    //   599: istore #10
    //   601: goto -> 22
    //   604: aload_0
    //   605: getfield defaultArgs : [Lgnu/expr/Expression;
    //   608: arraylength
    //   609: iload #10
    //   611: isub
    //   612: istore #8
    //   614: goto -> 32
    //   617: iload #8
    //   619: istore #11
    //   621: goto -> 46
    //   624: iconst_0
    //   625: istore #12
    //   627: goto -> 70
    //   630: aload_0
    //   631: getfield nameDecl : Lgnu/expr/Declaration;
    //   634: ifnull -> 656
    //   637: aload_0
    //   638: getfield nameDecl : Lgnu/expr/Declaration;
    //   641: ldc2_w 2048
    //   644: invokevirtual getFlag : (J)Z
    //   647: ifeq -> 656
    //   650: iconst_1
    //   651: istore #5
    //   653: goto -> 124
    //   656: aload_0
    //   657: invokevirtual isClassMethod : ()Z
    //   660: ifeq -> 740
    //   663: aload #23
    //   665: instanceof gnu/expr/ClassExp
    //   668: ifeq -> 734
    //   671: aload #23
    //   673: checkcast gnu/expr/ClassExp
    //   676: astore #19
    //   678: aload #19
    //   680: invokevirtual isMakingClassPair : ()Z
    //   683: ifeq -> 709
    //   686: aload_3
    //   687: ifnull -> 709
    //   690: iconst_1
    //   691: istore #5
    //   693: aload_0
    //   694: aload #19
    //   696: getfield initMethod : Lgnu/expr/LambdaExp;
    //   699: if_acmpne -> 715
    //   702: bipush #73
    //   704: istore #9
    //   706: goto -> 124
    //   709: iconst_0
    //   710: istore #5
    //   712: goto -> 693
    //   715: aload_0
    //   716: aload #19
    //   718: getfield clinitMethod : Lgnu/expr/LambdaExp;
    //   721: if_acmpne -> 124
    //   724: bipush #67
    //   726: istore #9
    //   728: iconst_1
    //   729: istore #5
    //   731: goto -> 124
    //   734: iconst_0
    //   735: istore #5
    //   737: goto -> 124
    //   740: aload_0
    //   741: getfield thisVariable : Lgnu/bytecode/Variable;
    //   744: ifnonnull -> 752
    //   747: aload_3
    //   748: aload_1
    //   749: if_acmpne -> 758
    //   752: iconst_0
    //   753: istore #5
    //   755: goto -> 124
    //   758: aload_0
    //   759: getfield nameDecl : Lgnu/expr/Declaration;
    //   762: ifnull -> 818
    //   765: aload_0
    //   766: getfield nameDecl : Lgnu/expr/Declaration;
    //   769: getfield context : Lgnu/expr/ScopeExp;
    //   772: instanceof gnu/expr/ModuleExp
    //   775: ifeq -> 818
    //   778: aload_0
    //   779: getfield nameDecl : Lgnu/expr/Declaration;
    //   782: getfield context : Lgnu/expr/ScopeExp;
    //   785: checkcast gnu/expr/ModuleExp
    //   788: astore #19
    //   790: aload #19
    //   792: invokevirtual getSuperType : ()Lgnu/bytecode/ClassType;
    //   795: ifnonnull -> 812
    //   798: aload #19
    //   800: invokevirtual getInterfaces : ()[Lgnu/bytecode/ClassType;
    //   803: ifnonnull -> 812
    //   806: iconst_1
    //   807: istore #5
    //   809: goto -> 124
    //   812: iconst_0
    //   813: istore #5
    //   815: goto -> 809
    //   818: iconst_1
    //   819: istore #5
    //   821: goto -> 124
    //   824: iconst_0
    //   825: istore #7
    //   827: goto -> 144
    //   830: aload_0
    //   831: getfield nameDecl : Lgnu/expr/Declaration;
    //   834: invokevirtual isPrivate : ()Z
    //   837: ifeq -> 875
    //   840: iconst_0
    //   841: istore #4
    //   843: iload #4
    //   845: istore #6
    //   847: aload_0
    //   848: invokevirtual isClassMethod : ()Z
    //   851: ifeq -> 865
    //   854: aload_0
    //   855: getfield nameDecl : Lgnu/expr/Declaration;
    //   858: iload #4
    //   860: invokevirtual getAccessFlags : (S)S
    //   863: istore #6
    //   865: iload #7
    //   867: iload #6
    //   869: ior
    //   870: istore #6
    //   872: goto -> 171
    //   875: iconst_1
    //   876: istore #4
    //   878: goto -> 843
    //   881: aload_0
    //   882: invokevirtual getSymbol : ()Ljava/lang/Object;
    //   885: ifnull -> 237
    //   888: aload #25
    //   890: aload #18
    //   892: invokestatic mangleName : (Ljava/lang/String;)Ljava/lang/String;
    //   895: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   898: pop
    //   899: goto -> 237
    //   902: iconst_0
    //   903: istore #13
    //   905: goto -> 271
    //   908: iload #6
    //   910: iconst_2
    //   911: iand
    //   912: iconst_2
    //   913: iadd
    //   914: istore #7
    //   916: goto -> 294
    //   919: aload #20
    //   921: invokevirtual isThisParameter : ()Z
    //   924: ifeq -> 957
    //   927: iload #6
    //   929: iconst_1
    //   930: isub
    //   931: istore #7
    //   933: aload #19
    //   935: astore #18
    //   937: aload #20
    //   939: invokevirtual nextDecl : ()Lgnu/expr/Declaration;
    //   942: astore #20
    //   944: iload #7
    //   946: iconst_1
    //   947: iadd
    //   948: istore #6
    //   950: aload #18
    //   952: astore #19
    //   954: goto -> 358
    //   957: iload #6
    //   959: istore #7
    //   961: aload #19
    //   963: astore #18
    //   965: aload #20
    //   967: ldc2_w 8192
    //   970: invokevirtual getFlag : (J)Z
    //   973: ifne -> 937
    //   976: aload #19
    //   978: astore #18
    //   980: aload #19
    //   982: ifnonnull -> 1005
    //   985: aload_1
    //   986: new gnu/expr/LambdaExp$1
    //   989: dup
    //   990: aload_0
    //   991: aload #25
    //   993: invokevirtual toString : ()Ljava/lang/String;
    //   996: invokespecial <init> : (Lgnu/expr/LambdaExp;Ljava/lang/String;)V
    //   999: iconst_2
    //   1000: invokevirtual getMethods : (Lgnu/bytecode/Filter;I)[Lgnu/bytecode/Method;
    //   1003: astore #18
    //   1005: aconst_null
    //   1006: astore #21
    //   1008: aload #18
    //   1010: arraylength
    //   1011: istore #7
    //   1013: iload #7
    //   1015: iconst_1
    //   1016: isub
    //   1017: istore #7
    //   1019: iload #7
    //   1021: iflt -> 1087
    //   1024: aload #18
    //   1026: iload #7
    //   1028: aaload
    //   1029: astore #19
    //   1031: aload #20
    //   1033: ifnonnull -> 1055
    //   1036: aload #19
    //   1038: invokevirtual getReturnType : ()Lgnu/bytecode/Type;
    //   1041: astore #19
    //   1043: aload #21
    //   1045: ifnonnull -> 1068
    //   1048: aload #19
    //   1050: astore #21
    //   1052: goto -> 1013
    //   1055: aload #19
    //   1057: invokevirtual getParameterTypes : ()[Lgnu/bytecode/Type;
    //   1060: iload #6
    //   1062: aaload
    //   1063: astore #19
    //   1065: goto -> 1043
    //   1068: aload #19
    //   1070: aload #21
    //   1072: if_acmpeq -> 1013
    //   1075: iload #6
    //   1077: istore #7
    //   1079: aload #20
    //   1081: ifnonnull -> 937
    //   1084: goto -> 370
    //   1087: aload #21
    //   1089: ifnull -> 1104
    //   1092: aload #20
    //   1094: ifnull -> 1116
    //   1097: aload #20
    //   1099: aload #21
    //   1101: invokevirtual setType : (Lgnu/bytecode/Type;)V
    //   1104: iload #6
    //   1106: istore #7
    //   1108: aload #20
    //   1110: ifnonnull -> 937
    //   1113: goto -> 370
    //   1116: aload_0
    //   1117: aload #21
    //   1119: invokevirtual setCoercedReturnType : (Lgnu/bytecode/Type;)V
    //   1122: goto -> 1104
    //   1125: aload_0
    //   1126: invokevirtual getReturnType : ()Lgnu/bytecode/Type;
    //   1129: invokevirtual getImplementationType : ()Lgnu/bytecode/Type;
    //   1132: astore #21
    //   1134: goto -> 393
    //   1137: iconst_0
    //   1138: istore #6
    //   1140: goto -> 405
    //   1143: iload #7
    //   1145: ifeq -> 1159
    //   1148: aload #22
    //   1150: aload #22
    //   1152: arraylength
    //   1153: iconst_1
    //   1154: isub
    //   1155: getstatic gnu/expr/Compilation.typeCallContext : Lgnu/bytecode/ClassType;
    //   1158: aastore
    //   1159: iload #5
    //   1161: istore #14
    //   1163: iload #16
    //   1165: iload #15
    //   1167: if_icmpge -> 1312
    //   1170: aload #18
    //   1172: invokevirtual getType : ()Lgnu/bytecode/Type;
    //   1175: astore #20
    //   1177: aload #20
    //   1179: invokevirtual getName : ()Ljava/lang/String;
    //   1182: astore #26
    //   1184: aload_1
    //   1185: invokevirtual getClassfileVersion : ()I
    //   1188: ldc_w 3211264
    //   1191: if_icmplt -> 1435
    //   1194: aload #20
    //   1196: instanceof gnu/bytecode/ArrayType
    //   1199: ifeq -> 1435
    //   1202: iload #5
    //   1204: sipush #128
    //   1207: ior
    //   1208: istore #5
    //   1210: iload #10
    //   1212: ifgt -> 1249
    //   1215: iload #11
    //   1217: iload #8
    //   1219: if_icmplt -> 1249
    //   1222: aload #20
    //   1224: astore #19
    //   1226: ldc_w 'gnu.lists.LList'
    //   1229: aload #26
    //   1231: invokevirtual equals : (Ljava/lang/Object;)Z
    //   1234: ifne -> 1279
    //   1237: aload #20
    //   1239: astore #19
    //   1241: aload #20
    //   1243: instanceof gnu/bytecode/ArrayType
    //   1246: ifne -> 1279
    //   1249: getstatic gnu/expr/Compilation.objArrayType : Lgnu/bytecode/ArrayType;
    //   1252: astore #19
    //   1254: aload_0
    //   1255: new gnu/bytecode/Variable
    //   1258: dup
    //   1259: ldc_w 'argsArray'
    //   1262: getstatic gnu/expr/Compilation.objArrayType : Lgnu/bytecode/ArrayType;
    //   1265: invokespecial <init> : (Ljava/lang/String;Lgnu/bytecode/Type;)V
    //   1268: putfield argsArray : Lgnu/bytecode/Variable;
    //   1271: aload_0
    //   1272: getfield argsArray : Lgnu/bytecode/Variable;
    //   1275: iconst_1
    //   1276: invokevirtual setParameter : (Z)V
    //   1279: aload_0
    //   1280: aload #18
    //   1282: putfield firstArgsArrayArg : Lgnu/expr/Declaration;
    //   1285: aload #22
    //   1287: arraylength
    //   1288: istore #15
    //   1290: iload #13
    //   1292: ifeq -> 1447
    //   1295: iconst_2
    //   1296: istore #14
    //   1298: aload #22
    //   1300: iload #15
    //   1302: iload #14
    //   1304: isub
    //   1305: aload #19
    //   1307: aastore
    //   1308: iload #5
    //   1310: istore #14
    //   1312: iload #13
    //   1314: ifeq -> 1326
    //   1317: aload #25
    //   1319: ldc_w '$X'
    //   1322: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   1325: pop
    //   1326: aload #23
    //   1328: instanceof gnu/expr/ClassExp
    //   1331: ifne -> 1356
    //   1334: aload #23
    //   1336: instanceof gnu/expr/ModuleExp
    //   1339: ifeq -> 1453
    //   1342: aload #23
    //   1344: checkcast gnu/expr/ModuleExp
    //   1347: ldc_w 131072
    //   1350: invokevirtual getFlag : (I)Z
    //   1353: ifeq -> 1453
    //   1356: iconst_1
    //   1357: istore #5
    //   1359: aload #25
    //   1361: invokevirtual toString : ()Ljava/lang/String;
    //   1364: astore #18
    //   1366: iconst_0
    //   1367: istore #15
    //   1369: aload #25
    //   1371: invokevirtual length : ()I
    //   1374: istore #16
    //   1376: aload_1
    //   1377: astore #19
    //   1379: aload #19
    //   1381: ifnull -> 1464
    //   1384: aload #19
    //   1386: aload #18
    //   1388: aload #22
    //   1390: invokevirtual getDeclaredMethod : (Ljava/lang/String;[Lgnu/bytecode/Type;)Lgnu/bytecode/Method;
    //   1393: ifnull -> 1459
    //   1396: aload #25
    //   1398: iload #16
    //   1400: invokevirtual setLength : (I)V
    //   1403: aload #25
    //   1405: bipush #36
    //   1407: invokevirtual append : (C)Ljava/lang/StringBuffer;
    //   1410: pop
    //   1411: iload #15
    //   1413: iconst_1
    //   1414: iadd
    //   1415: istore #15
    //   1417: aload #25
    //   1419: iload #15
    //   1421: invokevirtual append : (I)Ljava/lang/StringBuffer;
    //   1424: pop
    //   1425: aload #25
    //   1427: invokevirtual toString : ()Ljava/lang/String;
    //   1430: astore #18
    //   1432: goto -> 1376
    //   1435: aload #25
    //   1437: ldc_w '$V'
    //   1440: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   1443: pop
    //   1444: goto -> 1210
    //   1447: iconst_1
    //   1448: istore #14
    //   1450: goto -> 1298
    //   1453: iconst_0
    //   1454: istore #5
    //   1456: goto -> 1359
    //   1459: iload #5
    //   1461: ifeq -> 1658
    //   1464: aload_1
    //   1465: aload #18
    //   1467: aload #22
    //   1469: aload #21
    //   1471: iload #14
    //   1473: invokevirtual addMethod : (Ljava/lang/String;[Lgnu/bytecode/Type;Lgnu/bytecode/Type;I)Lgnu/bytecode/Method;
    //   1476: astore #26
    //   1478: aload #24
    //   1480: iload #9
    //   1482: aload #26
    //   1484: aastore
    //   1485: aload_0
    //   1486: getfield throwsSpecification : [Lgnu/expr/Expression;
    //   1489: ifnull -> 1870
    //   1492: aload_0
    //   1493: getfield throwsSpecification : [Lgnu/expr/Expression;
    //   1496: arraylength
    //   1497: ifle -> 1870
    //   1500: aload_0
    //   1501: getfield throwsSpecification : [Lgnu/expr/Expression;
    //   1504: arraylength
    //   1505: istore #15
    //   1507: iload #15
    //   1509: anewarray gnu/bytecode/ClassType
    //   1512: astore #27
    //   1514: iconst_0
    //   1515: istore #5
    //   1517: iload #5
    //   1519: iload #15
    //   1521: if_icmpge -> 1856
    //   1524: aconst_null
    //   1525: astore #20
    //   1527: aconst_null
    //   1528: astore #19
    //   1530: aload_0
    //   1531: getfield throwsSpecification : [Lgnu/expr/Expression;
    //   1534: iload #5
    //   1536: aaload
    //   1537: astore #28
    //   1539: aconst_null
    //   1540: astore #22
    //   1542: aload #28
    //   1544: instanceof gnu/expr/ReferenceExp
    //   1547: ifeq -> 1732
    //   1550: aload #28
    //   1552: checkcast gnu/expr/ReferenceExp
    //   1555: astore #20
    //   1557: aload #20
    //   1559: invokevirtual getBinding : ()Lgnu/expr/Declaration;
    //   1562: astore #18
    //   1564: aload #18
    //   1566: ifnull -> 1703
    //   1569: aload #18
    //   1571: invokevirtual getValue : ()Lgnu/expr/Expression;
    //   1574: astore #20
    //   1576: aload #20
    //   1578: instanceof gnu/expr/ClassExp
    //   1581: ifeq -> 1668
    //   1584: aload #20
    //   1586: checkcast gnu/expr/ClassExp
    //   1589: aload_2
    //   1590: invokevirtual getCompiledClassType : (Lgnu/expr/Compilation;)Lgnu/bytecode/ClassType;
    //   1593: astore #19
    //   1595: aload #22
    //   1597: astore #18
    //   1599: aload #18
    //   1601: astore #20
    //   1603: aload #19
    //   1605: ifnonnull -> 1622
    //   1608: aload #18
    //   1610: astore #20
    //   1612: aload #18
    //   1614: ifnonnull -> 1622
    //   1617: ldc_w 'invalid throws specification'
    //   1620: astore #20
    //   1622: aload #20
    //   1624: ifnull -> 1642
    //   1627: aload_2
    //   1628: bipush #101
    //   1630: aload #20
    //   1632: aload #28
    //   1634: invokevirtual error : (CLjava/lang/String;Lgnu/text/SourceLocator;)V
    //   1637: getstatic gnu/bytecode/Type.javalangThrowableType : Lgnu/bytecode/ClassType;
    //   1640: astore #19
    //   1642: aload #27
    //   1644: iload #5
    //   1646: aload #19
    //   1648: aastore
    //   1649: iload #5
    //   1651: iconst_1
    //   1652: iadd
    //   1653: istore #5
    //   1655: goto -> 1517
    //   1658: aload #19
    //   1660: invokevirtual getSuperclass : ()Lgnu/bytecode/ClassType;
    //   1663: astore #19
    //   1665: goto -> 1379
    //   1668: new java/lang/StringBuilder
    //   1671: dup
    //   1672: invokespecial <init> : ()V
    //   1675: ldc_w 'throws specification '
    //   1678: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1681: aload #18
    //   1683: invokevirtual getName : ()Ljava/lang/String;
    //   1686: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1689: ldc_w ' has non-class lexical binding'
    //   1692: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1695: invokevirtual toString : ()Ljava/lang/String;
    //   1698: astore #18
    //   1700: goto -> 1599
    //   1703: new java/lang/StringBuilder
    //   1706: dup
    //   1707: invokespecial <init> : ()V
    //   1710: ldc_w 'unknown class '
    //   1713: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1716: aload #20
    //   1718: invokevirtual getName : ()Ljava/lang/String;
    //   1721: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1724: invokevirtual toString : ()Ljava/lang/String;
    //   1727: astore #18
    //   1729: goto -> 1599
    //   1732: aload #22
    //   1734: astore #18
    //   1736: aload #28
    //   1738: instanceof gnu/expr/QuoteExp
    //   1741: ifeq -> 1599
    //   1744: aload #28
    //   1746: checkcast gnu/expr/QuoteExp
    //   1749: invokevirtual getValue : ()Ljava/lang/Object;
    //   1752: astore #19
    //   1754: aload #19
    //   1756: astore #18
    //   1758: aload #19
    //   1760: instanceof java/lang/Class
    //   1763: ifeq -> 1776
    //   1766: aload #19
    //   1768: checkcast java/lang/Class
    //   1771: invokestatic make : (Ljava/lang/Class;)Lgnu/bytecode/Type;
    //   1774: astore #18
    //   1776: aload #18
    //   1778: instanceof gnu/bytecode/ClassType
    //   1781: ifeq -> 1791
    //   1784: aload #18
    //   1786: checkcast gnu/bytecode/ClassType
    //   1789: astore #20
    //   1791: aload #20
    //   1793: astore #19
    //   1795: aload #22
    //   1797: astore #18
    //   1799: aload #20
    //   1801: ifnull -> 1599
    //   1804: aload #20
    //   1806: astore #19
    //   1808: aload #22
    //   1810: astore #18
    //   1812: aload #20
    //   1814: getstatic gnu/bytecode/Type.javalangThrowableType : Lgnu/bytecode/ClassType;
    //   1817: invokevirtual isSubtype : (Lgnu/bytecode/Type;)Z
    //   1820: ifne -> 1599
    //   1823: new java/lang/StringBuilder
    //   1826: dup
    //   1827: invokespecial <init> : ()V
    //   1830: aload #20
    //   1832: invokevirtual getName : ()Ljava/lang/String;
    //   1835: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1838: ldc_w ' does not extend Throwable'
    //   1841: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1844: invokevirtual toString : ()Ljava/lang/String;
    //   1847: astore #18
    //   1849: aload #20
    //   1851: astore #19
    //   1853: goto -> 1599
    //   1856: new gnu/bytecode/ExceptionsAttr
    //   1859: dup
    //   1860: aload #26
    //   1862: invokespecial <init> : (Lgnu/bytecode/Method;)V
    //   1865: aload #27
    //   1867: invokevirtual setExceptions : ([Lgnu/bytecode/ClassType;)V
    //   1870: iload #9
    //   1872: iconst_1
    //   1873: iadd
    //   1874: istore #9
    //   1876: iload #14
    //   1878: istore #5
    //   1880: goto -> 442
    //   1883: return
  }
  
  void addMethodFor(Compilation paramCompilation, ObjectType paramObjectType) {
    ClassType classType;
    ScopeExp scopeExp = this;
    while (scopeExp != null && !(scopeExp instanceof ClassExp))
      scopeExp = scopeExp.outer; 
    if (scopeExp != null) {
      classType = ((ClassExp)scopeExp).instanceType;
    } else {
      classType = getOwningLambda().getHeapFrameType();
    } 
    addMethodFor(classType, paramCompilation, paramObjectType);
  }
  
  public void allocChildClasses(Compilation paramCompilation) {
    Method method = getMainMethod();
    if (method != null && !method.getStaticFlag())
      declareThis(method.getDeclaringClass()); 
    for (Declaration declaration = firstDecl();; declaration = declaration.nextDecl()) {
      if (declaration == this.firstArgsArrayArg && this.argsArray != null)
        getVarScope().addVariable(this.argsArray); 
      if (!getInlineOnly() && getCallConvention() >= 2 && ((this.firstArgsArrayArg == null) ? (declaration == null) : ((this.argsArray != null) ? (declaration == this.firstArgsArrayArg) : (declaration == this.firstArgsArrayArg.nextDecl()))))
        getVarScope().addVariable(null, (Type)Compilation.typeCallContext, "$ctx").setParameter(true); 
      if (declaration == null) {
        declareClosureEnv();
        allocFrame(paramCompilation);
        allocChildMethods(paramCompilation);
        return;
      } 
      if (declaration.var == null && (!getInlineOnly() || !declaration.ignorable()))
        if (declaration.isSimple() && !declaration.isIndirectBinding()) {
          declaration.allocateVariable(null);
        } else {
          String str = Compilation.mangleName(declaration.getName()).intern();
          Type type = declaration.getType().getImplementationType();
          Variable variable = getVarScope().addVariable(null, type, str);
          declaration.var = variable;
          variable.setParameter(true);
        }  
    } 
  }
  
  void allocChildMethods(Compilation paramCompilation) {
    for (LambdaExp lambdaExp = this.firstChild; lambdaExp != null; lambdaExp = lambdaExp.nextSibling) {
      if (!lambdaExp.isClassGenerated() && !lambdaExp.getInlineOnly() && lambdaExp.nameDecl != null)
        lambdaExp.allocMethod(this, paramCompilation); 
      if (lambdaExp instanceof ClassExp) {
        ClassExp classExp = (ClassExp)lambdaExp;
        if (classExp.getNeedsClosureEnv()) {
          ClassType classType;
          if (this instanceof ModuleExp || this instanceof ClassExp) {
            classType = (ClassType)getType();
          } else {
            Variable variable;
            if (this.heapFrame != null) {
              variable = this.heapFrame;
            } else {
              variable = this.closureEnv;
            } 
            classType = (ClassType)variable.getType();
          } 
          Field field = classExp.instanceType.setOuterLink(classType);
          classExp.staticLinkField = field;
          classExp.closureEnvField = field;
        } 
      } 
    } 
  }
  
  Field allocFieldFor(Compilation paramCompilation) {
    // Byte code:
    //   0: aload_0
    //   1: getfield nameDecl : Lgnu/expr/Declaration;
    //   4: ifnull -> 27
    //   7: aload_0
    //   8: getfield nameDecl : Lgnu/expr/Declaration;
    //   11: getfield field : Lgnu/bytecode/Field;
    //   14: ifnull -> 27
    //   17: aload_0
    //   18: getfield nameDecl : Lgnu/expr/Declaration;
    //   21: getfield field : Lgnu/bytecode/Field;
    //   24: astore_1
    //   25: aload_1
    //   26: areturn
    //   27: aload_0
    //   28: invokevirtual getNeedsClosureEnv : ()Z
    //   31: istore #4
    //   33: iload #4
    //   35: ifeq -> 298
    //   38: aload_0
    //   39: invokevirtual getOwningLambda : ()Lgnu/expr/LambdaExp;
    //   42: invokevirtual getHeapFrameType : ()Lgnu/bytecode/ClassType;
    //   45: astore #7
    //   47: aload_0
    //   48: invokevirtual getName : ()Ljava/lang/String;
    //   51: astore #5
    //   53: aload #5
    //   55: ifnonnull -> 307
    //   58: ldc 'lambda'
    //   60: astore #6
    //   62: bipush #16
    //   64: istore_2
    //   65: aload_0
    //   66: getfield nameDecl : Lgnu/expr/Declaration;
    //   69: ifnull -> 322
    //   72: aload_0
    //   73: getfield nameDecl : Lgnu/expr/Declaration;
    //   76: getfield context : Lgnu/expr/ScopeExp;
    //   79: instanceof gnu/expr/ModuleExp
    //   82: ifeq -> 322
    //   85: aload_0
    //   86: getfield nameDecl : Lgnu/expr/Declaration;
    //   89: invokevirtual needsExternalAccess : ()Z
    //   92: istore #4
    //   94: aload #6
    //   96: astore #5
    //   98: iload #4
    //   100: ifeq -> 126
    //   103: new java/lang/StringBuilder
    //   106: dup
    //   107: invokespecial <init> : ()V
    //   110: ldc_w '$Prvt$'
    //   113: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   116: aload #6
    //   118: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   121: invokevirtual toString : ()Ljava/lang/String;
    //   124: astore #5
    //   126: aload_0
    //   127: getfield nameDecl : Lgnu/expr/Declaration;
    //   130: ldc2_w 2048
    //   133: invokevirtual getFlag : (J)Z
    //   136: ifeq -> 168
    //   139: bipush #16
    //   141: bipush #8
    //   143: ior
    //   144: istore_3
    //   145: iload_3
    //   146: istore_2
    //   147: aload_0
    //   148: getfield nameDecl : Lgnu/expr/Declaration;
    //   151: getfield context : Lgnu/expr/ScopeExp;
    //   154: checkcast gnu/expr/ModuleExp
    //   157: invokevirtual isStatic : ()Z
    //   160: ifne -> 168
    //   163: iload_3
    //   164: bipush #-17
    //   166: iand
    //   167: istore_2
    //   168: aload_0
    //   169: getfield nameDecl : Lgnu/expr/Declaration;
    //   172: invokevirtual isPrivate : ()Z
    //   175: ifeq -> 192
    //   178: iload #4
    //   180: ifne -> 192
    //   183: iload_2
    //   184: istore_3
    //   185: aload_1
    //   186: getfield immediate : Z
    //   189: ifeq -> 196
    //   192: iload_2
    //   193: iconst_1
    //   194: ior
    //   195: istore_3
    //   196: iload_3
    //   197: istore_2
    //   198: aload #5
    //   200: astore_1
    //   201: aload_0
    //   202: getfield flags : I
    //   205: sipush #2048
    //   208: iand
    //   209: ifeq -> 264
    //   212: aload_0
    //   213: getfield min_args : I
    //   216: aload_0
    //   217: getfield max_args : I
    //   220: if_icmpne -> 317
    //   223: aload_0
    //   224: getfield min_args : I
    //   227: istore_2
    //   228: new java/lang/StringBuilder
    //   231: dup
    //   232: invokespecial <init> : ()V
    //   235: aload #5
    //   237: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   240: bipush #36
    //   242: invokevirtual append : (C)Ljava/lang/StringBuilder;
    //   245: iload_2
    //   246: invokevirtual append : (I)Ljava/lang/StringBuilder;
    //   249: invokevirtual toString : ()Ljava/lang/String;
    //   252: astore_1
    //   253: aload #7
    //   255: aload_1
    //   256: invokevirtual getDeclaredField : (Ljava/lang/String;)Lgnu/bytecode/Field;
    //   259: ifnonnull -> 385
    //   262: iload_3
    //   263: istore_2
    //   264: aload #7
    //   266: aload_1
    //   267: getstatic gnu/expr/Compilation.typeModuleMethod : Lgnu/bytecode/ClassType;
    //   270: iload_2
    //   271: invokevirtual addField : (Ljava/lang/String;Lgnu/bytecode/Type;I)Lgnu/bytecode/Field;
    //   274: astore #5
    //   276: aload #5
    //   278: astore_1
    //   279: aload_0
    //   280: getfield nameDecl : Lgnu/expr/Declaration;
    //   283: ifnull -> 25
    //   286: aload_0
    //   287: getfield nameDecl : Lgnu/expr/Declaration;
    //   290: aload #5
    //   292: putfield field : Lgnu/bytecode/Field;
    //   295: aload #5
    //   297: areturn
    //   298: aload_1
    //   299: getfield mainClass : Lgnu/bytecode/ClassType;
    //   302: astore #7
    //   304: goto -> 47
    //   307: aload #5
    //   309: invokestatic mangleNameIfNeeded : (Ljava/lang/String;)Ljava/lang/String;
    //   312: astore #6
    //   314: goto -> 62
    //   317: iconst_1
    //   318: istore_2
    //   319: goto -> 228
    //   322: new java/lang/StringBuilder
    //   325: dup
    //   326: invokespecial <init> : ()V
    //   329: aload #6
    //   331: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   334: ldc_w '$Fn'
    //   337: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   340: astore #5
    //   342: aload_1
    //   343: getfield localFieldIndex : I
    //   346: iconst_1
    //   347: iadd
    //   348: istore_3
    //   349: aload_1
    //   350: iload_3
    //   351: putfield localFieldIndex : I
    //   354: aload #5
    //   356: iload_3
    //   357: invokevirtual append : (I)Ljava/lang/StringBuilder;
    //   360: invokevirtual toString : ()Ljava/lang/String;
    //   363: astore #5
    //   365: aload #5
    //   367: astore_1
    //   368: iload #4
    //   370: ifne -> 264
    //   373: bipush #16
    //   375: bipush #8
    //   377: ior
    //   378: istore_2
    //   379: aload #5
    //   381: astore_1
    //   382: goto -> 264
    //   385: iload_2
    //   386: iconst_1
    //   387: iadd
    //   388: istore_2
    //   389: goto -> 228
  }
  
  public void allocFrame(Compilation paramCompilation) {
    if (this.heapFrame != null) {
      ClassType classType;
      if (this instanceof ModuleExp || this instanceof ClassExp) {
        classType = getCompiledClassType(paramCompilation);
      } else {
        ClassType classType1 = new ClassType(classType.generateClassName("frame"));
        classType1.setSuper(classType.getModuleType());
        classType.addClass(classType1);
        classType = classType1;
      } 
      this.heapFrame.setType((Type)classType);
    } 
  }
  
  void allocMethod(LambdaExp paramLambdaExp, Compilation paramCompilation) {
    ClassType classType;
    if (!getNeedsClosureEnv()) {
      paramLambdaExp = null;
    } else if (paramLambdaExp instanceof ClassExp || paramLambdaExp instanceof ModuleExp) {
      classType = paramLambdaExp.getCompiledClassType(paramCompilation);
    } else {
      LambdaExp lambdaExp;
      while (((LambdaExp)classType).heapFrame == null)
        lambdaExp = classType.outerLambda(); 
      classType = (ClassType)lambdaExp.heapFrame.getType();
    } 
    addMethodFor(paramCompilation, (ObjectType)classType);
  }
  
  void allocParameters(Compilation paramCompilation) {
    CodeAttr codeAttr = paramCompilation.getCode();
    codeAttr.locals.enterScope(getVarScope());
    int i = getLineNumber();
    if (i > 0)
      codeAttr.putLineNumber(getFileName(), i); 
    if (this.heapFrame != null)
      this.heapFrame.allocateLocal(codeAttr); 
  }
  
  public void apply(CallContext paramCallContext) throws Throwable {
    setIndexes();
    paramCallContext.writeValue(new Closure(this, paramCallContext));
  }
  
  public void capture(Declaration paramDeclaration) {
    if (paramDeclaration.isSimple()) {
      if (this.capturedVars == null && !paramDeclaration.isStatic() && !(this instanceof ModuleExp) && !(this instanceof ClassExp))
        this.heapFrame = new Variable("heapFrame"); 
      paramDeclaration.setSimple(false);
      if (!paramDeclaration.isPublic()) {
        paramDeclaration.nextCapturedVar = this.capturedVars;
        this.capturedVars = paramDeclaration;
      } 
    } 
  }
  
  public void compile(Compilation paramCompilation, Target paramTarget) {
    if (paramTarget instanceof IgnoreTarget)
      return; 
    CodeAttr codeAttr = paramCompilation.getCode();
    LambdaExp lambdaExp = outerLambda();
    ClassType classType = Compilation.typeModuleMethod;
    if ((this.flags & 0x100) != 0 || (paramCompilation.immediate && lambdaExp instanceof ModuleExp)) {
      if (this.primMethods == null)
        allocMethod(outerLambda(), paramCompilation); 
      compileAsMethod(paramCompilation);
      addApplyMethod(paramCompilation, (Field)null);
      ProcInitializer.emitLoadModuleMethod(this, paramCompilation);
    } else {
      Field field = compileSetField(paramCompilation);
      if (field.getStaticFlag()) {
        codeAttr.emitGetStatic(field);
      } else {
        Variable variable;
        lambdaExp = paramCompilation.curLambda;
        if (lambdaExp.heapFrame != null) {
          variable = lambdaExp.heapFrame;
        } else {
          variable = ((LambdaExp)variable).closureEnv;
        } 
        codeAttr.emitLoad(variable);
        codeAttr.emitGetField(field);
      } 
    } 
    paramTarget.compileFromStack(paramCompilation, (Type)classType);
  }
  
  void compileAsMethod(Compilation paramCompilation) {
    if ((this.flags & 0x80) == 0 && !isAbstract()) {
      this.flags |= 0x80;
      if (this.primMethods != null) {
        boolean bool;
        Method method = paramCompilation.method;
        LambdaExp lambdaExp = paramCompilation.curLambda;
        paramCompilation.curLambda = this;
        boolean bool1 = this.primMethods[0].getStaticFlag();
        int j = this.primMethods.length - 1;
        Type type = restArgType();
        long[] arrayOfLong = null;
        if (j > 0) {
          long[] arrayOfLong1 = new long[this.min_args + j];
          int k = 0;
          Declaration declaration = firstDecl();
          while (true) {
            arrayOfLong = arrayOfLong1;
            if (k < this.min_args + j) {
              arrayOfLong1[k] = declaration.flags;
              declaration = declaration.nextDecl();
              k++;
              continue;
            } 
            break;
          } 
        } 
        if (getCallConvention() >= 2) {
          bool = true;
        } else {
          bool = false;
        } 
        for (int i = 0; i <= j; i++) {
          paramCompilation.method = this.primMethods[i];
          if (i < j) {
            boolean bool2;
            Variable variable3;
            CodeAttr codeAttr = paramCompilation.method.startCode();
            int k;
            for (k = i + 1; k < j && this.defaultArgs[k] instanceof QuoteExp; k++);
            if (k == j && type != null) {
              bool2 = true;
            } else {
              bool2 = false;
            } 
            Variable variable4 = paramCompilation.callContextVar;
            Variable variable2 = codeAttr.getArg(0);
            Variable variable1 = variable2;
            if (!bool1) {
              codeAttr.emitPushThis();
              if (getNeedsClosureEnv())
                this.closureEnv = variable2; 
              variable1 = codeAttr.getArg(1);
            } 
            Declaration declaration = firstDecl();
            int m = 0;
            while (m < this.min_args + i) {
              declaration.flags |= 0x40L;
              declaration.var = variable1;
              codeAttr.emitLoad(variable1);
              variable1 = variable1.nextVar();
              m++;
              declaration = declaration.nextDecl();
            } 
            if (bool) {
              variable3 = variable1;
            } else {
              variable3 = null;
            } 
            paramCompilation.callContextVar = variable3;
            m = i;
            while (m < k) {
              Target target = StackTarget.getInstance(declaration.getType());
              this.defaultArgs[m].compile(paramCompilation, target);
              m++;
              declaration = declaration.nextDecl();
            } 
            if (bool2) {
              QuoteExp quoteExp;
              String str = type.getName();
              if ("gnu.lists.LList".equals(str)) {
                quoteExp = new QuoteExp(LList.Empty);
              } else if ("java.lang.Object[]".equals(quoteExp)) {
                quoteExp = new QuoteExp(Values.noArgs);
              } else {
                throw new Error("unimplemented #!rest type " + quoteExp);
              } 
              quoteExp.compile(paramCompilation, type);
            } 
            if (bool)
              codeAttr.emitLoad(variable1); 
            if (bool1) {
              codeAttr.emitInvokeStatic(this.primMethods[k]);
            } else {
              codeAttr.emitInvokeVirtual(this.primMethods[k]);
            } 
            codeAttr.emitReturn();
            this.closureEnv = null;
            paramCompilation.callContextVar = variable4;
          } else {
            if (arrayOfLong != null) {
              int k = 0;
              Declaration declaration = firstDecl();
              while (k < this.min_args + j) {
                declaration.flags = arrayOfLong[k];
                declaration.var = null;
                declaration = declaration.nextDecl();
                k++;
              } 
            } 
            paramCompilation.method.initCode();
            allocChildClasses(paramCompilation);
            allocParameters(paramCompilation);
            enterFunction(paramCompilation);
            compileBody(paramCompilation);
            compileEnd(paramCompilation);
            generateApplyMethods(paramCompilation);
          } 
        } 
        paramCompilation.method = method;
        paramCompilation.curLambda = lambdaExp;
        return;
      } 
    } 
  }
  
  public void compileBody(Compilation paramCompilation) {
    Target target;
    Variable variable = paramCompilation.callContextVar;
    paramCompilation.callContextVar = null;
    if (getCallConvention() >= 2) {
      Variable variable1 = getVarScope().lookup("$ctx");
      if (variable1 != null && variable1.getType() == Compilation.typeCallContext)
        paramCompilation.callContextVar = variable1; 
      target = ConsumerTarget.makeContextTarget(paramCompilation);
    } else {
      target = Target.pushValue(getReturnType());
    } 
    Expression expression2 = this.body;
    Expression expression1 = this;
    if (this.body.getLineNumber() > 0)
      expression1 = this.body; 
    expression2.compileWithPosition(paramCompilation, target, expression1);
    paramCompilation.callContextVar = variable;
  }
  
  public void compileEnd(Compilation paramCompilation) {
    CodeAttr codeAttr = paramCompilation.getCode();
    if (!getInlineOnly()) {
      if (paramCompilation.method.reachableHere() && (Compilation.defaultCallConvention < 3 || isModuleBody() || isClassMethod() || isHandlingTailCalls()))
        codeAttr.emitReturn(); 
      popScope(codeAttr);
      codeAttr.popScope();
    } 
    for (LambdaExp lambdaExp = this.firstChild; lambdaExp != null; lambdaExp = lambdaExp.nextSibling) {
      if (!lambdaExp.getCanRead() && !lambdaExp.getInlineOnly())
        lambdaExp.compileAsMethod(paramCompilation); 
    } 
    if (this.heapFrame != null)
      paramCompilation.generateConstructor(this); 
  }
  
  public Field compileSetField(Compilation paramCompilation) {
    if (this.primMethods == null)
      allocMethod(outerLambda(), paramCompilation); 
    Field field = allocFieldFor(paramCompilation);
    if (paramCompilation.usingCPStyle()) {
      compile(paramCompilation, (Type)Type.objectType);
      return (new ProcInitializer(this, paramCompilation, field)).field;
    } 
    compileAsMethod(paramCompilation);
    addApplyMethod(paramCompilation, field);
    return (new ProcInitializer(this, paramCompilation, field)).field;
  }
  
  public Variable declareClosureEnv() {
    LambdaExp lambdaExp;
    Method method;
    Variable variable;
    if (this.closureEnv == null && getNeedsClosureEnv()) {
      LambdaExp lambdaExp1 = outerLambda();
      lambdaExp = lambdaExp1;
      if (lambdaExp1 instanceof ClassExp)
        lambdaExp = lambdaExp1.outerLambda(); 
      if (lambdaExp.heapFrame != null) {
        variable = lambdaExp.heapFrame;
      } else {
        variable = lambdaExp.closureEnv;
      } 
      if (isClassMethod() && !"*init*".equals(getName())) {
        this.closureEnv = declareThis(this.type);
        return this.closureEnv;
      } 
    } else {
      return this.closureEnv;
    } 
    if (lambdaExp.heapFrame == null && !lambdaExp.getNeedsStaticLink() && !(lambdaExp instanceof ModuleExp)) {
      this.closureEnv = null;
      return this.closureEnv;
    } 
    if (!isClassGenerated() && !getInlineOnly()) {
      method = getMainMethod();
      boolean bool = "*init*".equals(getName());
      if (!method.getStaticFlag() && !bool) {
        this.closureEnv = declareThis(method.getDeclaringClass());
        return this.closureEnv;
      } 
      this.closureEnv = new Variable("closureEnv", method.getParameterTypes()[0]);
      if (bool) {
        Variable variable1 = declareThis(method.getDeclaringClass());
      } else {
        method = null;
      } 
      getVarScope().addVariableAfter((Variable)method, this.closureEnv);
      this.closureEnv.setParameter(true);
      return this.closureEnv;
    } 
    if (inlinedIn((LambdaExp)method)) {
      this.closureEnv = variable;
      return this.closureEnv;
    } 
    this.closureEnv = new Variable("closureEnv", variable.getType());
    getVarScope().addVariable(this.closureEnv);
    return this.closureEnv;
  }
  
  public Variable declareThis(ClassType paramClassType) {
    if (this.thisVariable == null) {
      this.thisVariable = new Variable("this");
      getVarScope().addVariableAfter(null, this.thisVariable);
      this.thisVariable.setParameter(true);
    } 
    if (this.thisVariable.getType() == null)
      this.thisVariable.setType((Type)paramClassType); 
    if (this.decls != null && this.decls.isThisParameter())
      this.decls.var = this.thisVariable; 
    return this.thisVariable;
  }
  
  void enterFunction(Compilation paramCompilation) {
    int m;
    CodeAttr codeAttr = paramCompilation.getCode();
    getVarScope().noteStartFunction(codeAttr);
    if (this.closureEnv != null && !this.closureEnv.isParameter() && !paramCompilation.usingCPStyle())
      if (!getInlineOnly()) {
        codeAttr.emitPushThis();
        Field field2 = this.closureEnvField;
        Field field1 = field2;
        if (field2 == null)
          field1 = (outerLambda()).closureEnvField; 
        codeAttr.emitGetField(field1);
        codeAttr.emitStore(this.closureEnv);
      } else if (!inlinedIn(outerLambda())) {
        outerLambda().loadHeapFrame(paramCompilation);
        codeAttr.emitStore(this.closureEnv);
      }  
    if (!paramCompilation.usingCPStyle()) {
      ClassType classType;
      if (this.heapFrame == null) {
        classType = currentModule().getCompiledClassType(paramCompilation);
      } else {
        classType = (ClassType)this.heapFrame.getType();
      } 
      for (Declaration declaration1 = this.capturedVars; declaration1 != null; declaration1 = declaration1.nextCapturedVar) {
        if (declaration1.field == null)
          declaration1.makeField(classType, paramCompilation, null); 
      } 
    } 
    if (this.heapFrame != null && !paramCompilation.usingCPStyle()) {
      ClassType classType = (ClassType)this.heapFrame.getType();
      if (this.closureEnv != null && !(this instanceof ModuleExp))
        this.staticLinkField = classType.addField("staticLink", this.closureEnv.getType()); 
      if (!(this instanceof ModuleExp) && !(this instanceof ClassExp)) {
        classType.setEnclosingMember((Member)paramCompilation.method);
        codeAttr.emitNew(classType);
        codeAttr.emitDup((Type)classType);
        codeAttr.emitInvokeSpecial(Compilation.getConstructor(classType, this));
        if (this.staticLinkField != null) {
          codeAttr.emitDup((Type)classType);
          codeAttr.emitLoad(this.closureEnv);
          codeAttr.emitPutField(this.staticLinkField);
        } 
        codeAttr.emitStore(this.heapFrame);
      } 
    } 
    Variable variable1 = this.argsArray;
    Variable variable2 = variable1;
    if (this.min_args == this.max_args) {
      variable2 = variable1;
      if (this.primMethods == null) {
        variable2 = variable1;
        if (getCallConvention() < 2)
          variable2 = null; 
      } 
    } 
    int k = 0;
    if (this.keywords == null) {
      i = 0;
    } else {
      i = this.keywords.length;
    } 
    if (this.defaultArgs == null) {
      m = 0;
    } else {
      m = this.defaultArgs.length - i;
    } 
    if (this instanceof ModuleExp)
      return; 
    int n = -1;
    int i1 = 0;
    getMainMethod();
    Variable variable3 = paramCompilation.callContextVar;
    Declaration declaration = firstDecl();
    int i = 0;
    int j = 0;
    while (declaration != null) {
      if (getCallConvention() < 2) {
        variable1 = null;
      } else {
        variable1 = getVarScope().lookup("$ctx");
      } 
      paramCompilation.callContextVar = variable1;
      int i2 = i1;
      int i3 = n;
      if (declaration == this.firstArgsArrayArg) {
        i2 = i1;
        i3 = n;
        if (variable2 != null)
          if (this.primMethods != null) {
            i3 = k;
            i2 = i3 - this.min_args;
          } else {
            i3 = 0;
            i2 = 0;
          }  
      } 
      if (i3 >= 0 || !declaration.isSimple() || declaration.isIndirectBinding()) {
        ClassType classType;
        Type type = declaration.getType();
        if (i3 >= 0) {
          classType = Type.objectType;
        } else {
          Type type1 = type;
        } 
        if (!declaration.isSimple())
          declaration.loadOwningObject(null, paramCompilation); 
        if (i3 < 0) {
          codeAttr.emitLoad(declaration.getVariable());
        } else if (k < this.min_args) {
          codeAttr.emitLoad(variable2);
          codeAttr.emitPushInt(k);
          codeAttr.emitArrayLoad((Type)Type.objectType);
        } else if (k < this.min_args + m) {
          codeAttr.emitPushInt(k - i3);
          codeAttr.emitLoad(variable2);
          codeAttr.emitArrayLength();
          codeAttr.emitIfIntLt();
          codeAttr.emitLoad(variable2);
          codeAttr.emitPushInt(k - i3);
          codeAttr.emitArrayLoad();
          codeAttr.emitElse();
          Expression[] arrayOfExpression = this.defaultArgs;
          n = j + 1;
          arrayOfExpression[i2 + j].compile(paramCompilation, type);
          codeAttr.emitFi();
          j = n;
        } else if (this.max_args < 0 && k == this.min_args + m) {
          codeAttr.emitLoad(variable2);
          codeAttr.emitPushInt(k - i3);
          codeAttr.emitInvokeStatic(Compilation.makeListMethod);
          classType = Compilation.scmListType;
        } else {
          codeAttr.emitLoad(variable2);
          codeAttr.emitPushInt(this.min_args + m - i3);
          Keyword[] arrayOfKeyword = this.keywords;
          n = i + 1;
          paramCompilation.compileConstant(arrayOfKeyword[i]);
          Expression[] arrayOfExpression = this.defaultArgs;
          i1 = j + 1;
          Expression expression = arrayOfExpression[i2 + j];
          if (expression instanceof QuoteExp) {
            if (searchForKeywordMethod4 == null) {
              ArrayType arrayType = Compilation.objArrayType;
              PrimType primType = Type.intType;
              ClassType classType1 = Type.objectType;
              ClassType classType2 = Type.objectType;
              ClassType classType3 = Compilation.scmKeywordType;
              ClassType classType4 = Type.objectType;
              searchForKeywordMethod4 = classType3.addMethod("searchForKeyword", new Type[] { (Type)arrayType, (Type)primType, (Type)classType1, (Type)classType2 }, (Type)classType4, 9);
            } 
            expression.compile(paramCompilation, type);
            codeAttr.emitInvokeStatic(searchForKeywordMethod4);
            i = n;
            j = i1;
          } else {
            if (searchForKeywordMethod3 == null) {
              ArrayType arrayType = Compilation.objArrayType;
              PrimType primType = Type.intType;
              ClassType classType1 = Type.objectType;
              ClassType classType2 = Compilation.scmKeywordType;
              ClassType classType3 = Type.objectType;
              searchForKeywordMethod3 = classType2.addMethod("searchForKeyword", new Type[] { (Type)arrayType, (Type)primType, (Type)classType1 }, (Type)classType3, 9);
            } 
            codeAttr.emitInvokeStatic(searchForKeywordMethod3);
            codeAttr.emitDup(1);
            paramCompilation.compileConstant(Special.dfault);
            codeAttr.emitIfEq();
            codeAttr.emitPop(1);
            expression.compile(paramCompilation, type);
            codeAttr.emitFi();
            i = n;
            j = i1;
          } 
        } 
        if (type != classType)
          CheckedTarget.emitCheckedCoerce(paramCompilation, this, k + 1, type); 
        if (declaration.isIndirectBinding())
          declaration.pushIndirectBinding(paramCompilation); 
        if (declaration.isSimple()) {
          Variable variable = declaration.getVariable();
          if (declaration.isIndirectBinding())
            variable.setType((Type)Compilation.typeLocation); 
          codeAttr.emitStore(variable);
        } else {
          codeAttr.emitPutField(declaration.field);
        } 
      } 
      k++;
      declaration = declaration.nextDecl();
      i1 = i2;
      n = i3;
    } 
    paramCompilation.callContextVar = variable3;
  }
  
  Object evalDefaultArg(int paramInt, CallContext paramCallContext) {
    try {
      return this.defaultArgs[paramInt].eval(paramCallContext);
    } catch (Throwable throwable) {
      throw new WrappedException("error evaluating default argument", throwable);
    } 
  }
  
  public void generateApplyMethods(Compilation paramCompilation) {
    paramCompilation.generateMatchMethods(this);
    if (Compilation.defaultCallConvention >= 2) {
      paramCompilation.generateApplyMethodsWithContext(this);
      return;
    } 
    paramCompilation.generateApplyMethodsWithoutContext(this);
  }
  
  Declaration getArg(int paramInt) {
    for (Declaration declaration = firstDecl();; declaration = declaration.nextDecl()) {
      if (declaration == null)
        throw new Error("internal error - getArg"); 
      if (paramInt == 0)
        return declaration; 
      paramInt--;
    } 
  }
  
  public int getCallConvention() {
    int i = 2;
    if (isModuleBody()) {
      if (Compilation.defaultCallConvention >= 2)
        i = Compilation.defaultCallConvention; 
      return i;
    } 
    return isClassMethod() ? 1 : ((Compilation.defaultCallConvention != 0) ? Compilation.defaultCallConvention : 1);
  }
  
  public LambdaExp getCaller() {
    return this.inlineHome;
  }
  
  public final boolean getCanCall() {
    return ((this.flags & 0x4) != 0);
  }
  
  public final boolean getCanRead() {
    return ((this.flags & 0x2) != 0);
  }
  
  public ClassType getClassType() {
    return this.type;
  }
  
  protected ClassType getCompiledClassType(Compilation paramCompilation) {
    if (this.type == Compilation.typeProcedure)
      throw new Error("internal error: getCompiledClassType"); 
    return this.type;
  }
  
  protected final String getExpClassName() {
    String str2 = getClass().getName();
    int i = str2.lastIndexOf('.');
    String str1 = str2;
    if (i >= 0)
      str1 = str2.substring(i + 1); 
    return str1;
  }
  
  public ClassType getHeapFrameType() {
    return (this instanceof ModuleExp || this instanceof ClassExp) ? (ClassType)getType() : (ClassType)this.heapFrame.getType();
  }
  
  public final boolean getImportsLexVars() {
    return ((this.flags & 0x8) != 0);
  }
  
  public final boolean getInlineOnly() {
    return ((this.flags & 0x2000) != 0);
  }
  
  public final Method getMainMethod() {
    Method[] arrayOfMethod = this.primBodyMethods;
    return (arrayOfMethod == null) ? null : arrayOfMethod[arrayOfMethod.length - 1];
  }
  
  public final Method getMethod(int paramInt) {
    if (this.primMethods != null && (this.max_args < 0 || paramInt <= this.max_args)) {
      paramInt -= this.min_args;
      if (paramInt >= 0) {
        int i = this.primMethods.length;
        Method[] arrayOfMethod = this.primMethods;
        if (paramInt >= i)
          paramInt = i - 1; 
        return arrayOfMethod[paramInt];
      } 
    } 
    return null;
  }
  
  public final boolean getNeedsClosureEnv() {
    return ((this.flags & 0x18) != 0);
  }
  
  public final boolean getNeedsStaticLink() {
    return ((this.flags & 0x10) != 0);
  }
  
  public LambdaExp getOwningLambda() {
    for (ScopeExp scopeExp = this.outer;; scopeExp = scopeExp.outer) {
      if (scopeExp == null)
        return null; 
      if (scopeExp instanceof ModuleExp || (scopeExp instanceof ClassExp && getNeedsClosureEnv()) || (scopeExp instanceof LambdaExp && ((LambdaExp)scopeExp).heapFrame != null))
        return (LambdaExp)scopeExp; 
    } 
  }
  
  public Object getProperty(Object paramObject1, Object paramObject2) {
    Object object = paramObject2;
    if (this.properties != null) {
      int i = this.properties.length;
      while (true) {
        int j = i - 2;
        object = paramObject2;
        if (j >= 0) {
          i = j;
          if (this.properties[j] == paramObject1) {
            object = this.properties[j + 1];
            break;
          } 
          continue;
        } 
        break;
      } 
    } 
    return object;
  }
  
  public final Type getReturnType() {
    if (this.returnType == null) {
      this.returnType = (Type)Type.objectType;
      if (this.body != null && !isAbstract())
        this.returnType = this.body.getType(); 
    } 
    return this.returnType;
  }
  
  int getSelectorValue(Compilation paramCompilation) {
    int j = this.selectorValue;
    int i = j;
    if (j == 0) {
      i = paramCompilation.maxSelectorValue;
      paramCompilation.maxSelectorValue = this.primMethods.length + i;
      this.selectorValue = ++i;
    } 
    return i;
  }
  
  public Type getType() {
    return (Type)this.type;
  }
  
  public int incomingArgs() {
    return (this.min_args == this.max_args && this.max_args <= 4 && this.max_args > 0) ? this.max_args : 1;
  }
  
  boolean inlinedIn(LambdaExp paramLambdaExp) {
    for (LambdaExp lambdaExp = this; lambdaExp.getInlineOnly(); lambdaExp = lambdaExp.getCaller()) {
      if (lambdaExp == paramLambdaExp)
        return true; 
    } 
    return false;
  }
  
  public boolean isAbstract() {
    return (this.body == QuoteExp.abstractExp);
  }
  
  public final boolean isClassGenerated() {
    return (isModuleBody() || this instanceof ClassExp);
  }
  
  public final boolean isClassMethod() {
    return ((this.flags & 0x40) != 0);
  }
  
  public final boolean isHandlingTailCalls() {
    return (isModuleBody() || (Compilation.defaultCallConvention >= 3 && !isClassMethod()));
  }
  
  public final boolean isModuleBody() {
    return this instanceof ModuleExp;
  }
  
  public void loadHeapFrame(Compilation paramCompilation) {
    ClassType classType;
    LambdaExp lambdaExp;
    for (lambdaExp = paramCompilation.curLambda; lambdaExp != this && lambdaExp.getInlineOnly(); lambdaExp = lambdaExp.getCaller());
    CodeAttr codeAttr = paramCompilation.getCode();
    if (lambdaExp.heapFrame != null && this == lambdaExp) {
      codeAttr.emitLoad(lambdaExp.heapFrame);
      return;
    } 
    if (lambdaExp.closureEnv != null) {
      codeAttr.emitLoad(lambdaExp.closureEnv);
      classType = (ClassType)lambdaExp.closureEnv.getType();
    } else {
      codeAttr.emitPushThis();
      classType = ((Compilation)classType).curClass;
    } 
    while (true) {
      if (lambdaExp != this) {
        Field field = lambdaExp.staticLinkField;
        ClassType classType1 = classType;
        if (field != null) {
          classType1 = classType;
          if (field.getDeclaringClass() == classType) {
            codeAttr.emitGetField(field);
            classType1 = (ClassType)field.getType();
          } 
        } 
        lambdaExp = lambdaExp.outerLambda();
        classType = classType1;
        continue;
      } 
      return;
    } 
  }
  
  protected boolean mustCompile() {
    if (this.keywords != null && this.keywords.length > 0)
      return true; 
    if (this.defaultArgs != null) {
      int i = this.defaultArgs.length;
      while (true) {
        int j = i - 1;
        if (j >= 0) {
          Expression expression = this.defaultArgs[j];
          i = j;
          if (expression != null) {
            i = j;
            if (!(expression instanceof QuoteExp))
              return true; 
          } 
          continue;
        } 
        break;
      } 
    } 
    return false;
  }
  
  public LambdaExp outerLambda() {
    return (this.outer == null) ? null : this.outer.currentLambda();
  }
  
  public LambdaExp outerLambdaNotInline() {
    LambdaExp lambdaExp = this;
    while (true) {
      ScopeExp scopeExp = lambdaExp.outer;
      if (scopeExp != null) {
        ScopeExp scopeExp1 = scopeExp;
        if (scopeExp instanceof LambdaExp) {
          LambdaExp lambdaExp1 = (LambdaExp)scopeExp;
          scopeExp1 = scopeExp;
          if (!lambdaExp1.getInlineOnly())
            return lambdaExp1; 
        } 
        continue;
      } 
      return null;
    } 
  }
  
  public void print(OutPort paramOutPort) {
    int i;
    int k;
    paramOutPort.startLogicalBlock("(Lambda/", ")", 2);
    Object object = getSymbol();
    if (object != null) {
      paramOutPort.print(object);
      paramOutPort.print('/');
    } 
    paramOutPort.print(this.id);
    paramOutPort.print('/');
    paramOutPort.print("fl:");
    paramOutPort.print(Integer.toHexString(this.flags));
    paramOutPort.writeSpaceFill();
    printLineColumn(paramOutPort);
    paramOutPort.startLogicalBlock("(", false, ")");
    Expression[] arrayOfExpression = null;
    int j = 0;
    if (this.keywords == null) {
      i = 0;
    } else {
      i = this.keywords.length;
    } 
    if (this.defaultArgs == null) {
      k = 0;
    } else {
      k = this.defaultArgs.length - i;
    } 
    Declaration declaration = firstDecl();
    if (declaration != null && declaration.isThisParameter()) {
      j = -1;
      i = 0;
    } else {
      i = 0;
    } 
    while (declaration != null) {
      Expression expression;
      if (j < this.min_args) {
        object = null;
      } else if (j < this.min_args + k) {
        object = Special.optional;
      } else if (this.max_args < 0 && j == this.min_args + k) {
        object = Special.rest;
      } else {
        object = Special.key;
      } 
      if (declaration != firstDecl())
        paramOutPort.writeSpaceFill(); 
      if (object != arrayOfExpression) {
        paramOutPort.print(object);
        paramOutPort.writeSpaceFill();
      } 
      arrayOfExpression = null;
      if (object == Special.optional || object == Special.key) {
        arrayOfExpression = this.defaultArgs;
        int m = i + 1;
        expression = arrayOfExpression[i];
        i = m;
      } 
      if (expression != null)
        paramOutPort.print('('); 
      declaration.printInfo(paramOutPort);
      if (expression != null && expression != QuoteExp.falseExp) {
        paramOutPort.print(' ');
        expression.print(paramOutPort);
        paramOutPort.print(')');
      } 
      j++;
      declaration = declaration.nextDecl();
      Object object1 = object;
    } 
    paramOutPort.endLogicalBlock(")");
    paramOutPort.writeSpaceLinear();
    if (this.body == null) {
      paramOutPort.print("<null body>");
    } else {
      this.body.print(paramOutPort);
    } 
    paramOutPort.endLogicalBlock(")");
  }
  
  public final Type restArgType() {
    if (this.min_args != this.max_args) {
      if (this.primMethods == null)
        throw new Error("internal error - restArgType"); 
      Method[] arrayOfMethod = this.primMethods;
      if (this.max_args < 0 || arrayOfMethod.length <= this.max_args - this.min_args) {
        Method method = arrayOfMethod[arrayOfMethod.length - 1];
        Type[] arrayOfType = method.getParameterTypes();
        int j = arrayOfType.length - 1;
        int i = j;
        if (method.getName().endsWith("$X"))
          i = j - 1; 
        return arrayOfType[i];
      } 
    } 
    return null;
  }
  
  void setCallersNeedStaticLink() {
    LambdaExp lambdaExp = outerLambda();
    for (ApplyExp applyExp = this.nameDecl.firstCall; applyExp != null; applyExp = applyExp.nextCall) {
      for (LambdaExp lambdaExp1 = applyExp.context; lambdaExp1 != lambdaExp && !(lambdaExp1 instanceof ModuleExp); lambdaExp1 = lambdaExp1.outerLambda())
        lambdaExp1.setNeedsStaticLink(); 
    } 
  }
  
  public final void setCanCall(boolean paramBoolean) {
    if (paramBoolean) {
      this.flags |= 0x4;
      return;
    } 
    this.flags &= 0xFFFFFFFB;
  }
  
  public final void setCanRead(boolean paramBoolean) {
    if (paramBoolean) {
      this.flags |= 0x2;
      return;
    } 
    this.flags &= 0xFFFFFFFD;
  }
  
  public final void setClassMethod(boolean paramBoolean) {
    if (paramBoolean) {
      this.flags |= 0x40;
      return;
    } 
    this.flags &= 0xFFFFFFBF;
  }
  
  public final void setCoercedReturnType(Type paramType) {
    this.returnType = paramType;
    if (paramType != null && paramType != Type.objectType && paramType != Type.voidType && this.body != QuoteExp.abstractExp) {
      Expression expression = this.body;
      this.body = Compilation.makeCoercion(expression, paramType);
      this.body.setLine(expression);
    } 
  }
  
  public final void setCoercedReturnValue(Expression paramExpression, Language paramLanguage) {
    if (!isAbstract()) {
      Expression expression = this.body;
      this.body = Compilation.makeCoercion(expression, paramExpression);
      this.body.setLine(expression);
    } 
    Type type = paramLanguage.getTypeFor(paramExpression);
    if (type != null)
      setReturnType(type); 
  }
  
  public void setExceptions(Expression[] paramArrayOfExpression) {
    this.throwsSpecification = paramArrayOfExpression;
  }
  
  public final void setImportsLexVars() {
    int i = this.flags;
    this.flags |= 0x8;
    if ((i & 0x8) == 0 && this.nameDecl != null)
      setCallersNeedStaticLink(); 
  }
  
  public final void setImportsLexVars(boolean paramBoolean) {
    if (paramBoolean) {
      this.flags |= 0x8;
      return;
    } 
    this.flags &= 0xFFFFFFF7;
  }
  
  public final void setInlineOnly(boolean paramBoolean) {
    setFlag(paramBoolean, 8192);
  }
  
  public final void setNeedsStaticLink() {
    int i = this.flags;
    this.flags |= 0x10;
    if ((i & 0x10) == 0 && this.nameDecl != null)
      setCallersNeedStaticLink(); 
  }
  
  public final void setNeedsStaticLink(boolean paramBoolean) {
    if (paramBoolean) {
      this.flags |= 0x10;
      return;
    } 
    this.flags &= 0xFFFFFFEF;
  }
  
  public void setProperty(Object paramObject1, Object paramObject2) {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: aload_0
    //   4: getfield properties : [Ljava/lang/Object;
    //   7: aload_1
    //   8: aload_2
    //   9: invokestatic setProperty : ([Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;)[Ljava/lang/Object;
    //   12: putfield properties : [Ljava/lang/Object;
    //   15: aload_0
    //   16: monitorexit
    //   17: return
    //   18: astore_1
    //   19: aload_0
    //   20: monitorexit
    //   21: aload_1
    //   22: athrow
    // Exception table:
    //   from	to	target	type
    //   2	15	18	finally
  }
  
  public final void setReturnType(Type paramType) {
    this.returnType = paramType;
  }
  
  public void setType(ClassType paramClassType) {
    this.type = paramClassType;
  }
  
  public boolean side_effects() {
    return false;
  }
  
  public String toString() {
    String str2 = getExpClassName() + ':' + getSymbol() + '/' + this.id + '/';
    int j = getLineNumber();
    int i = j;
    if (j <= 0) {
      i = j;
      if (this.body != null)
        i = this.body.getLineNumber(); 
    } 
    String str1 = str2;
    if (i > 0)
      str1 = str2 + "l:" + i; 
    return str1;
  }
  
  public Expression validateApply(ApplyExp paramApplyExp, InlineCalls paramInlineCalls, Type paramType, Declaration paramDeclaration) {
    // Byte code:
    //   0: aload_1
    //   1: invokevirtual getArgs : ()[Lgnu/expr/Expression;
    //   4: astore #4
    //   6: aload_0
    //   7: getfield flags : I
    //   10: sipush #4096
    //   13: iand
    //   14: ifeq -> 41
    //   17: aload_0
    //   18: aload #4
    //   20: iconst_1
    //   21: invokestatic inlineCall : (Lgnu/expr/LambdaExp;[Lgnu/expr/Expression;Z)Lgnu/expr/Expression;
    //   24: astore #4
    //   26: aload #4
    //   28: ifnull -> 41
    //   31: aload_2
    //   32: aload #4
    //   34: aload_3
    //   35: invokevirtual visit : (Lgnu/expr/Expression;Lgnu/bytecode/Type;)Lgnu/expr/Expression;
    //   38: astore_3
    //   39: aload_3
    //   40: areturn
    //   41: aload_1
    //   42: aload_2
    //   43: invokevirtual visitArgs : (Lgnu/expr/InlineCalls;)V
    //   46: aload_1
    //   47: getfield args : [Lgnu/expr/Expression;
    //   50: arraylength
    //   51: istore #5
    //   53: aload_0
    //   54: invokevirtual getName : ()Ljava/lang/String;
    //   57: aload_0
    //   58: getfield min_args : I
    //   61: aload_0
    //   62: getfield max_args : I
    //   65: iload #5
    //   67: invokestatic checkArgCount : (Ljava/lang/String;III)Ljava/lang/String;
    //   70: astore_3
    //   71: aload_3
    //   72: ifnull -> 81
    //   75: aload_2
    //   76: aload_3
    //   77: invokevirtual noteError : (Ljava/lang/String;)Lgnu/expr/Expression;
    //   80: areturn
    //   81: aload_0
    //   82: invokevirtual getCallConvention : ()I
    //   85: istore #6
    //   87: aload_1
    //   88: astore_3
    //   89: aload_2
    //   90: invokevirtual getCompilation : ()Lgnu/expr/Compilation;
    //   93: aload_0
    //   94: invokevirtual inlineOk : (Lgnu/expr/Expression;)Z
    //   97: ifeq -> 39
    //   100: aload_1
    //   101: astore_3
    //   102: aload_0
    //   103: invokevirtual isClassMethod : ()Z
    //   106: ifeq -> 39
    //   109: iload #6
    //   111: iconst_2
    //   112: if_icmple -> 123
    //   115: aload_1
    //   116: astore_3
    //   117: iload #6
    //   119: iconst_3
    //   120: if_icmpne -> 39
    //   123: aload_0
    //   124: iload #5
    //   126: invokevirtual getMethod : (I)Lgnu/bytecode/Method;
    //   129: astore #4
    //   131: aload_1
    //   132: astore_3
    //   133: aload #4
    //   135: ifnull -> 39
    //   138: aload_0
    //   139: getfield nameDecl : Lgnu/expr/Declaration;
    //   142: invokevirtual isStatic : ()Z
    //   145: istore #7
    //   147: iload #7
    //   149: ifne -> 175
    //   152: aload_0
    //   153: getfield outer : Lgnu/expr/ScopeExp;
    //   156: instanceof gnu/expr/ClassExp
    //   159: ifeq -> 175
    //   162: aload_0
    //   163: getfield outer : Lgnu/expr/ScopeExp;
    //   166: checkcast gnu/expr/ClassExp
    //   169: invokevirtual isMakingClassPair : ()Z
    //   172: ifeq -> 175
    //   175: new gnu/expr/PrimProcedure
    //   178: dup
    //   179: aload #4
    //   181: aload_0
    //   182: invokespecial <init> : (Lgnu/bytecode/Method;Lgnu/expr/LambdaExp;)V
    //   185: astore #4
    //   187: iload #7
    //   189: ifeq -> 212
    //   192: aload_1
    //   193: getfield args : [Lgnu/expr/Expression;
    //   196: astore_2
    //   197: new gnu/expr/ApplyExp
    //   200: dup
    //   201: aload #4
    //   203: aload_2
    //   204: invokespecial <init> : (Lgnu/mapping/Procedure;[Lgnu/expr/Expression;)V
    //   207: aload_1
    //   208: invokevirtual setLine : (Lgnu/expr/Expression;)Lgnu/expr/Expression;
    //   211: areturn
    //   212: aload_2
    //   213: invokevirtual getCurrentLambda : ()Lgnu/expr/LambdaExp;
    //   216: astore_3
    //   217: aload_3
    //   218: ifnonnull -> 246
    //   221: aload_2
    //   222: new java/lang/StringBuilder
    //   225: dup
    //   226: invokespecial <init> : ()V
    //   229: ldc_w 'internal error: missing '
    //   232: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   235: aload_0
    //   236: invokevirtual append : (Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   239: invokevirtual toString : ()Ljava/lang/String;
    //   242: invokevirtual noteError : (Ljava/lang/String;)Lgnu/expr/Expression;
    //   245: areturn
    //   246: aload_3
    //   247: getfield outer : Lgnu/expr/ScopeExp;
    //   250: aload_0
    //   251: getfield outer : Lgnu/expr/ScopeExp;
    //   254: if_acmpne -> 317
    //   257: aload_3
    //   258: invokevirtual firstDecl : ()Lgnu/expr/Declaration;
    //   261: astore #8
    //   263: aload #8
    //   265: ifnull -> 276
    //   268: aload #8
    //   270: invokevirtual isThisParameter : ()Z
    //   273: ifne -> 325
    //   276: aload_2
    //   277: new java/lang/StringBuilder
    //   280: dup
    //   281: invokespecial <init> : ()V
    //   284: ldc_w 'calling non-static method '
    //   287: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   290: aload_0
    //   291: invokevirtual getName : ()Ljava/lang/String;
    //   294: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   297: ldc_w ' from static method '
    //   300: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   303: aload_3
    //   304: invokevirtual getName : ()Ljava/lang/String;
    //   307: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   310: invokevirtual toString : ()Ljava/lang/String;
    //   313: invokevirtual noteError : (Ljava/lang/String;)Lgnu/expr/Expression;
    //   316: areturn
    //   317: aload_3
    //   318: invokevirtual outerLambda : ()Lgnu/expr/LambdaExp;
    //   321: astore_3
    //   322: goto -> 217
    //   325: aload_1
    //   326: invokevirtual getArgCount : ()I
    //   329: istore #5
    //   331: iload #5
    //   333: iconst_1
    //   334: iadd
    //   335: anewarray gnu/expr/Expression
    //   338: astore_2
    //   339: aload_1
    //   340: invokevirtual getArgs : ()[Lgnu/expr/Expression;
    //   343: iconst_0
    //   344: aload_2
    //   345: iconst_1
    //   346: iload #5
    //   348: invokestatic arraycopy : (Ljava/lang/Object;ILjava/lang/Object;II)V
    //   351: aload_2
    //   352: iconst_0
    //   353: new gnu/expr/ThisExp
    //   356: dup
    //   357: aload #8
    //   359: invokespecial <init> : (Lgnu/expr/Declaration;)V
    //   362: aastore
    //   363: goto -> 197
  }
  
  public final boolean variable_args() {
    return (this.max_args < 0);
  }
  
  protected <R, D> R visit(ExpVisitor<R, D> paramExpVisitor, D paramD) {
    LambdaExp lambdaExp;
    Compilation compilation = paramExpVisitor.getCompilation();
    if (compilation == null) {
      lambdaExp = null;
    } else {
      lambdaExp = compilation.curLambda;
      compilation.curLambda = this;
    } 
    try {
      paramExpVisitor = (ExpVisitor<R, D>)paramExpVisitor.visitLambdaExp(this, paramD);
      return (R)paramExpVisitor;
    } finally {
      if (compilation != null)
        compilation.curLambda = lambdaExp; 
    } 
  }
  
  protected <R, D> void visitChildren(ExpVisitor<R, D> paramExpVisitor, D paramD) {
    visitChildrenOnly(paramExpVisitor, paramD);
    visitProperties(paramExpVisitor, paramD);
  }
  
  protected final <R, D> void visitChildrenOnly(ExpVisitor<R, D> paramExpVisitor, D paramD) {
    LambdaExp lambdaExp = paramExpVisitor.currentLambda;
    paramExpVisitor.currentLambda = this;
    try {
      this.throwsSpecification = paramExpVisitor.visitExps(this.throwsSpecification, paramD);
      paramExpVisitor.visitDefaultArgs(this, paramD);
      if (paramExpVisitor.exitValue == null && this.body != null)
        this.body = paramExpVisitor.update(this.body, paramExpVisitor.visit(this.body, paramD)); 
      return;
    } finally {
      paramExpVisitor.currentLambda = lambdaExp;
    } 
  }
  
  protected final <R, D> void visitProperties(ExpVisitor<R, D> paramExpVisitor, D paramD) {
    if (this.properties != null) {
      int j = this.properties.length;
      for (int i = 1; i < j; i += 2) {
        Object object = this.properties[i];
        if (object instanceof Expression)
          this.properties[i] = paramExpVisitor.visitAndUpdate((Expression)object, paramD); 
      } 
    } 
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/gnu/expr/LambdaExp.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */