package gnu.expr;

import gnu.bytecode.Type;
import gnu.bytecode.Variable;
import gnu.kawa.functions.AddOp;
import gnu.mapping.CallContext;
import gnu.mapping.Environment;
import gnu.mapping.EnvironmentKey;
import gnu.mapping.Location;
import gnu.mapping.OutPort;
import gnu.mapping.Symbol;
import gnu.math.IntNum;

public class SetExp extends AccessExp {
  public static final int BAD_SHORT = 65536;
  
  public static final int DEFINING_FLAG = 2;
  
  public static final int GLOBAL_FLAG = 4;
  
  public static final int HAS_VALUE = 64;
  
  public static final int PREFER_BINDING2 = 8;
  
  public static final int PROCEDURE = 16;
  
  public static final int SET_IF_UNBOUND = 32;
  
  Expression new_value;
  
  public SetExp(Declaration paramDeclaration, Expression paramExpression) {
    this.binding = paramDeclaration;
    this.symbol = paramDeclaration.getSymbol();
    this.new_value = paramExpression;
  }
  
  public SetExp(Object paramObject, Expression paramExpression) {
    this.symbol = paramObject;
    this.new_value = paramExpression;
  }
  
  public static int canUseInc(Expression paramExpression, Declaration paramDeclaration) {
    Variable variable = paramDeclaration.getVariable();
    if (paramDeclaration.isSimple() && variable.getType().getImplementationType().promote() == Type.intType && paramExpression instanceof ApplyExp) {
      paramExpression = paramExpression;
      if (paramExpression.getArgCount() == 2) {
        Object object1;
        byte b;
        Object object2 = paramExpression.getFunction().valueIfConstant();
        if (object2 == AddOp.$Pl) {
          b = 1;
        } else if (object2 == AddOp.$Mn) {
          b = -1;
        } else {
          return 65536;
        } 
        object2 = paramExpression.getArg(0);
        Expression expression = paramExpression.getArg(1);
        Object object3 = object2;
        paramExpression = expression;
        if (object2 instanceof QuoteExp) {
          object3 = object2;
          paramExpression = expression;
          if (b > 0) {
            object1 = object2;
            object3 = expression;
          } 
        } 
        if (object3 instanceof ReferenceExp) {
          object2 = object3;
          if (object2.getBinding() == paramDeclaration && !object2.getDontDereference()) {
            object1 = object1.valueIfConstant();
            if (object1 instanceof Integer) {
              int j = ((Integer)object1).intValue();
              int i = j;
              if (b < 0)
                i = -j; 
              return ((short)i == i) ? i : 65536;
            } 
            if (object1 instanceof IntNum) {
              object1 = object1;
              int i = 32767;
              int j = -'翿';
              if (b > 0) {
                j--;
              } else {
                i = 32767 + 1;
              } 
              if (IntNum.compare((IntNum)object1, j) >= 0 && IntNum.compare((IntNum)object1, i) <= 0)
                return b * object1.intValue(); 
            } 
          } 
        } 
      } 
    } 
    return 65536;
  }
  
  public static SetExp makeDefinition(Declaration paramDeclaration, Expression paramExpression) {
    SetExp setExp = new SetExp(paramDeclaration, paramExpression);
    setExp.setDefining(true);
    return setExp;
  }
  
  public static SetExp makeDefinition(Object paramObject, Expression paramExpression) {
    paramObject = new SetExp(paramObject, paramExpression);
    paramObject.setDefining(true);
    return (SetExp)paramObject;
  }
  
  public void apply(CallContext paramCallContext) throws Throwable {
    Symbol symbol;
    Location location;
    Environment environment = Environment.getCurrent();
    if (this.symbol instanceof Symbol) {
      symbol = (Symbol)this.symbol;
    } else {
      symbol = environment.getSymbol(this.symbol.toString());
    } 
    Object object2 = null;
    Language language = Language.getDefaultLanguage();
    Object object1 = object2;
    if (isFuncDef()) {
      object1 = object2;
      if (language.hasSeparateFunctionNamespace())
        object1 = EnvironmentKey.FUNCTION; 
    } 
    if (isSetIfUnbound()) {
      location = environment.getLocation(symbol, object1);
      if (!location.isBound())
        location.set(this.new_value.eval(environment)); 
      if (getHasValue())
        paramCallContext.writeValue(location); 
      return;
    } 
    object2 = this.new_value.eval(environment);
    if (this.binding != null && !(this.binding.context instanceof ModuleExp)) {
      object1 = paramCallContext.evalFrames[ScopeExp.nesting(this.binding.context)];
      if (this.binding.isIndirectBinding()) {
        if (isDefining())
          object1[this.binding.evalIndex] = Location.make((Symbol)location); 
        ((Location)object1[this.binding.evalIndex]).set(this.new_value);
      } else {
        object1[this.binding.evalIndex] = object2;
      } 
    } else if (isDefining()) {
      environment.define((Symbol)location, object1, object2);
    } else {
      environment.put((Symbol)location, object1, object2);
    } 
    if (getHasValue()) {
      paramCallContext.writeValue(object2);
      return;
    } 
  }
  
  public void compile(Compilation paramCompilation, Target paramTarget) {
    // Byte code:
    //   0: aload_0
    //   1: getfield new_value : Lgnu/expr/Expression;
    //   4: instanceof gnu/expr/LambdaExp
    //   7: ifeq -> 31
    //   10: aload_2
    //   11: instanceof gnu/expr/IgnoreTarget
    //   14: ifeq -> 31
    //   17: aload_0
    //   18: getfield new_value : Lgnu/expr/Expression;
    //   21: checkcast gnu/expr/LambdaExp
    //   24: invokevirtual getInlineOnly : ()Z
    //   27: ifeq -> 31
    //   30: return
    //   31: aload_1
    //   32: invokevirtual getCode : ()Lgnu/bytecode/CodeAttr;
    //   35: astore #19
    //   37: aload_0
    //   38: invokevirtual getHasValue : ()Z
    //   41: ifeq -> 166
    //   44: aload_2
    //   45: instanceof gnu/expr/IgnoreTarget
    //   48: ifne -> 166
    //   51: iconst_1
    //   52: istore #5
    //   54: iconst_0
    //   55: istore #4
    //   57: iconst_0
    //   58: istore #7
    //   60: iconst_0
    //   61: istore #6
    //   63: iconst_0
    //   64: istore #8
    //   66: iconst_0
    //   67: istore #9
    //   69: iconst_0
    //   70: istore #10
    //   72: iconst_0
    //   73: istore_3
    //   74: aload_0
    //   75: getfield binding : Lgnu/expr/Declaration;
    //   78: astore #12
    //   80: aload #12
    //   82: invokevirtual getValue : ()Lgnu/expr/Expression;
    //   85: astore #11
    //   87: aload #11
    //   89: instanceof gnu/expr/LambdaExp
    //   92: ifeq -> 172
    //   95: aload #12
    //   97: getfield context : Lgnu/expr/ScopeExp;
    //   100: instanceof gnu/expr/ModuleExp
    //   103: ifeq -> 172
    //   106: aload #12
    //   108: invokevirtual ignorable : ()Z
    //   111: ifne -> 172
    //   114: aload #11
    //   116: checkcast gnu/expr/LambdaExp
    //   119: invokevirtual getName : ()Ljava/lang/String;
    //   122: ifnull -> 172
    //   125: aload #11
    //   127: aload_0
    //   128: getfield new_value : Lgnu/expr/Expression;
    //   131: if_acmpne -> 172
    //   134: aload_0
    //   135: getfield new_value : Lgnu/expr/Expression;
    //   138: checkcast gnu/expr/LambdaExp
    //   141: aload_1
    //   142: invokevirtual compileSetField : (Lgnu/expr/Compilation;)Lgnu/bytecode/Field;
    //   145: pop
    //   146: iload #5
    //   148: ifeq -> 1041
    //   151: iload_3
    //   152: ifne -> 1041
    //   155: new java/lang/Error
    //   158: dup
    //   159: ldc_w 'SetExp.compile: not implemented - return value'
    //   162: invokespecial <init> : (Ljava/lang/String;)V
    //   165: athrow
    //   166: iconst_0
    //   167: istore #5
    //   169: goto -> 54
    //   172: aload #12
    //   174: invokevirtual shouldEarlyInit : ()Z
    //   177: ifne -> 188
    //   180: aload #12
    //   182: invokevirtual isAlias : ()Z
    //   185: ifeq -> 253
    //   188: aload #12
    //   190: getfield context : Lgnu/expr/ScopeExp;
    //   193: instanceof gnu/expr/ModuleExp
    //   196: ifeq -> 253
    //   199: aload_0
    //   200: invokevirtual isDefining : ()Z
    //   203: ifeq -> 253
    //   206: aload #12
    //   208: invokevirtual ignorable : ()Z
    //   211: ifne -> 253
    //   214: aload #12
    //   216: invokevirtual shouldEarlyInit : ()Z
    //   219: ifeq -> 232
    //   222: aload #12
    //   224: aload_0
    //   225: getfield new_value : Lgnu/expr/Expression;
    //   228: aload_1
    //   229: invokestatic create : (Lgnu/expr/Declaration;Lgnu/expr/Expression;Lgnu/expr/Compilation;)V
    //   232: iload #5
    //   234: ifeq -> 146
    //   237: aload #12
    //   239: aload_0
    //   240: iconst_0
    //   241: aload_1
    //   242: getstatic gnu/expr/Target.pushObject : Lgnu/expr/Target;
    //   245: invokevirtual load : (Lgnu/expr/AccessExp;ILgnu/expr/Compilation;Lgnu/expr/Target;)V
    //   248: iconst_1
    //   249: istore_3
    //   250: goto -> 146
    //   253: aload_0
    //   254: astore #11
    //   256: aload_0
    //   257: invokevirtual contextDecl : ()Lgnu/expr/Declaration;
    //   260: astore #13
    //   262: aload #11
    //   264: astore #16
    //   266: aload #12
    //   268: astore #14
    //   270: aload #13
    //   272: astore #15
    //   274: aload_0
    //   275: invokevirtual isDefining : ()Z
    //   278: ifne -> 345
    //   281: aload #11
    //   283: astore #16
    //   285: aload #12
    //   287: astore #14
    //   289: aload #13
    //   291: astore #15
    //   293: aload #12
    //   295: ifnull -> 345
    //   298: aload #11
    //   300: astore #16
    //   302: aload #12
    //   304: astore #14
    //   306: aload #13
    //   308: astore #15
    //   310: aload #12
    //   312: invokevirtual isAlias : ()Z
    //   315: ifeq -> 345
    //   318: aload #12
    //   320: invokevirtual getValue : ()Lgnu/expr/Expression;
    //   323: astore #14
    //   325: aload #14
    //   327: instanceof gnu/expr/ReferenceExp
    //   330: ifne -> 367
    //   333: aload #13
    //   335: astore #15
    //   337: aload #12
    //   339: astore #14
    //   341: aload #11
    //   343: astore #16
    //   345: aload #14
    //   347: invokevirtual ignorable : ()Z
    //   350: ifeq -> 441
    //   353: aload_0
    //   354: getfield new_value : Lgnu/expr/Expression;
    //   357: aload_1
    //   358: getstatic gnu/expr/Target.Ignore : Lgnu/expr/Target;
    //   361: invokevirtual compile : (Lgnu/expr/Compilation;Lgnu/expr/Target;)V
    //   364: goto -> 146
    //   367: aload #14
    //   369: checkcast gnu/expr/ReferenceExp
    //   372: astore #18
    //   374: aload #18
    //   376: getfield binding : Lgnu/expr/Declaration;
    //   379: astore #17
    //   381: aload #11
    //   383: astore #16
    //   385: aload #12
    //   387: astore #14
    //   389: aload #13
    //   391: astore #15
    //   393: aload #17
    //   395: ifnull -> 345
    //   398: aload #13
    //   400: ifnull -> 423
    //   403: aload #11
    //   405: astore #16
    //   407: aload #12
    //   409: astore #14
    //   411: aload #13
    //   413: astore #15
    //   415: aload #17
    //   417: invokevirtual needsContext : ()Z
    //   420: ifne -> 345
    //   423: aload #18
    //   425: invokevirtual contextDecl : ()Lgnu/expr/Declaration;
    //   428: astore #13
    //   430: aload #18
    //   432: astore #11
    //   434: aload #17
    //   436: astore #12
    //   438: goto -> 281
    //   441: aload #14
    //   443: invokevirtual isAlias : ()Z
    //   446: ifeq -> 510
    //   449: aload_0
    //   450: invokevirtual isDefining : ()Z
    //   453: ifeq -> 510
    //   456: aload #14
    //   458: aload_0
    //   459: iconst_2
    //   460: aload_1
    //   461: getstatic gnu/expr/Target.pushObject : Lgnu/expr/Target;
    //   464: invokevirtual load : (Lgnu/expr/AccessExp;ILgnu/expr/Compilation;Lgnu/expr/Target;)V
    //   467: ldc_w 'gnu.mapping.IndirectableLocation'
    //   470: invokestatic make : (Ljava/lang/String;)Lgnu/bytecode/ClassType;
    //   473: astore #11
    //   475: aload #19
    //   477: aload #11
    //   479: invokevirtual emitCheckcast : (Lgnu/bytecode/Type;)V
    //   482: aload_0
    //   483: getfield new_value : Lgnu/expr/Expression;
    //   486: aload_1
    //   487: getstatic gnu/expr/Target.pushObject : Lgnu/expr/Target;
    //   490: invokevirtual compile : (Lgnu/expr/Compilation;Lgnu/expr/Target;)V
    //   493: aload #19
    //   495: aload #11
    //   497: ldc_w 'setAlias'
    //   500: iconst_1
    //   501: invokevirtual getDeclaredMethod : (Ljava/lang/String;I)Lgnu/bytecode/Method;
    //   504: invokevirtual emitInvokeVirtual : (Lgnu/bytecode/Method;)V
    //   507: goto -> 146
    //   510: aload #14
    //   512: invokevirtual isIndirectBinding : ()Z
    //   515: ifeq -> 689
    //   518: aload #14
    //   520: aload #16
    //   522: iconst_2
    //   523: aload_1
    //   524: getstatic gnu/expr/Target.pushObject : Lgnu/expr/Target;
    //   527: invokevirtual load : (Lgnu/expr/AccessExp;ILgnu/expr/Compilation;Lgnu/expr/Target;)V
    //   530: iload #7
    //   532: istore_3
    //   533: aload_0
    //   534: invokevirtual isSetIfUnbound : ()Z
    //   537: ifeq -> 610
    //   540: iload #4
    //   542: istore_3
    //   543: iload #5
    //   545: ifeq -> 555
    //   548: aload #19
    //   550: invokevirtual emitDup : ()V
    //   553: iconst_1
    //   554: istore_3
    //   555: aload #19
    //   557: invokevirtual pushScope : ()Lgnu/bytecode/Scope;
    //   560: pop
    //   561: aload #19
    //   563: invokevirtual emitDup : ()V
    //   566: aload #19
    //   568: getstatic gnu/expr/Compilation.typeLocation : Lgnu/bytecode/ClassType;
    //   571: invokevirtual addLocal : (Lgnu/bytecode/Type;)Lgnu/bytecode/Variable;
    //   574: astore #11
    //   576: aload #19
    //   578: aload #11
    //   580: invokevirtual emitStore : (Lgnu/bytecode/Variable;)V
    //   583: aload #19
    //   585: getstatic gnu/expr/Compilation.typeLocation : Lgnu/bytecode/ClassType;
    //   588: ldc_w 'isBound'
    //   591: iconst_0
    //   592: invokevirtual getDeclaredMethod : (Ljava/lang/String;I)Lgnu/bytecode/Method;
    //   595: invokevirtual emitInvokeVirtual : (Lgnu/bytecode/Method;)V
    //   598: aload #19
    //   600: invokevirtual emitIfIntEqZero : ()V
    //   603: aload #19
    //   605: aload #11
    //   607: invokevirtual emitLoad : (Lgnu/bytecode/Variable;)V
    //   610: aload_0
    //   611: getfield new_value : Lgnu/expr/Expression;
    //   614: aload_1
    //   615: getstatic gnu/expr/Target.pushObject : Lgnu/expr/Target;
    //   618: invokevirtual compile : (Lgnu/expr/Compilation;Lgnu/expr/Target;)V
    //   621: iload_3
    //   622: istore #4
    //   624: iload #5
    //   626: ifeq -> 647
    //   629: iload_3
    //   630: istore #4
    //   632: aload_0
    //   633: invokevirtual isSetIfUnbound : ()Z
    //   636: ifne -> 647
    //   639: aload #19
    //   641: invokevirtual emitDupX : ()V
    //   644: iconst_1
    //   645: istore #4
    //   647: aload #19
    //   649: getstatic gnu/expr/Compilation.typeLocation : Lgnu/bytecode/ClassType;
    //   652: ldc_w 'set'
    //   655: iconst_1
    //   656: invokevirtual getDeclaredMethod : (Ljava/lang/String;I)Lgnu/bytecode/Method;
    //   659: invokevirtual emitInvokeVirtual : (Lgnu/bytecode/Method;)V
    //   662: iload #4
    //   664: istore_3
    //   665: aload_0
    //   666: invokevirtual isSetIfUnbound : ()Z
    //   669: ifeq -> 146
    //   672: aload #19
    //   674: invokevirtual emitFi : ()V
    //   677: aload #19
    //   679: invokevirtual popScope : ()Lgnu/bytecode/Scope;
    //   682: pop
    //   683: iload #4
    //   685: istore_3
    //   686: goto -> 146
    //   689: aload #14
    //   691: invokevirtual isSimple : ()Z
    //   694: ifeq -> 813
    //   697: aload #14
    //   699: invokevirtual getType : ()Lgnu/bytecode/Type;
    //   702: astore #13
    //   704: aload #14
    //   706: invokevirtual getVariable : ()Lgnu/bytecode/Variable;
    //   709: astore #12
    //   711: aload #12
    //   713: astore #11
    //   715: aload #12
    //   717: ifnonnull -> 729
    //   720: aload #14
    //   722: aload #19
    //   724: invokevirtual allocateVariable : (Lgnu/bytecode/CodeAttr;)Lgnu/bytecode/Variable;
    //   727: astore #11
    //   729: aload_0
    //   730: getfield new_value : Lgnu/expr/Expression;
    //   733: aload #14
    //   735: invokestatic canUseInc : (Lgnu/expr/Expression;Lgnu/expr/Declaration;)I
    //   738: istore #4
    //   740: iload #4
    //   742: ldc 65536
    //   744: if_icmpeq -> 776
    //   747: aload_1
    //   748: invokevirtual getCode : ()Lgnu/bytecode/CodeAttr;
    //   751: aload #11
    //   753: iload #4
    //   755: i2s
    //   756: invokevirtual emitInc : (Lgnu/bytecode/Variable;S)V
    //   759: iload #5
    //   761: ifeq -> 146
    //   764: aload #19
    //   766: aload #11
    //   768: invokevirtual emitLoad : (Lgnu/bytecode/Variable;)V
    //   771: iconst_1
    //   772: istore_3
    //   773: goto -> 146
    //   776: aload_0
    //   777: getfield new_value : Lgnu/expr/Expression;
    //   780: aload_1
    //   781: aload #14
    //   783: invokevirtual compile : (Lgnu/expr/Compilation;Lgnu/expr/Declaration;)V
    //   786: iload #6
    //   788: istore_3
    //   789: iload #5
    //   791: ifeq -> 803
    //   794: aload #19
    //   796: aload #13
    //   798: invokevirtual emitDup : (Lgnu/bytecode/Type;)V
    //   801: iconst_1
    //   802: istore_3
    //   803: aload #19
    //   805: aload #11
    //   807: invokevirtual emitStore : (Lgnu/bytecode/Variable;)V
    //   810: goto -> 146
    //   813: aload #14
    //   815: getfield context : Lgnu/expr/ScopeExp;
    //   818: instanceof gnu/expr/ClassExp
    //   821: ifeq -> 932
    //   824: aload #14
    //   826: getfield field : Lgnu/bytecode/Field;
    //   829: ifnonnull -> 932
    //   832: aload_0
    //   833: bipush #16
    //   835: invokevirtual getFlag : (I)Z
    //   838: ifne -> 932
    //   841: aload #14
    //   843: getfield context : Lgnu/expr/ScopeExp;
    //   846: checkcast gnu/expr/ClassExp
    //   849: invokevirtual isMakingClassPair : ()Z
    //   852: ifeq -> 932
    //   855: ldc_w 'set'
    //   858: aload #14
    //   860: invokevirtual getName : ()Ljava/lang/String;
    //   863: invokestatic slotToMethodName : (Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
    //   866: astore #12
    //   868: aload #14
    //   870: getfield context : Lgnu/expr/ScopeExp;
    //   873: checkcast gnu/expr/ClassExp
    //   876: astore #11
    //   878: aload #11
    //   880: getfield type : Lgnu/bytecode/ClassType;
    //   883: aload #12
    //   885: iconst_1
    //   886: invokevirtual getDeclaredMethod : (Ljava/lang/String;I)Lgnu/bytecode/Method;
    //   889: astore #12
    //   891: aload #11
    //   893: aload_1
    //   894: invokevirtual loadHeapFrame : (Lgnu/expr/Compilation;)V
    //   897: aload_0
    //   898: getfield new_value : Lgnu/expr/Expression;
    //   901: aload_1
    //   902: aload #14
    //   904: invokevirtual compile : (Lgnu/expr/Compilation;Lgnu/expr/Declaration;)V
    //   907: iload #8
    //   909: istore_3
    //   910: iload #5
    //   912: ifeq -> 922
    //   915: aload #19
    //   917: invokevirtual emitDupX : ()V
    //   920: iconst_1
    //   921: istore_3
    //   922: aload #19
    //   924: aload #12
    //   926: invokevirtual emitInvoke : (Lgnu/bytecode/Method;)V
    //   929: goto -> 146
    //   932: aload #14
    //   934: getfield field : Lgnu/bytecode/Field;
    //   937: astore #11
    //   939: aload #11
    //   941: invokevirtual getStaticFlag : ()Z
    //   944: ifne -> 955
    //   947: aload #14
    //   949: aload #15
    //   951: aload_1
    //   952: invokevirtual loadOwningObject : (Lgnu/expr/Declaration;Lgnu/expr/Compilation;)V
    //   955: aload #11
    //   957: invokevirtual getType : ()Lgnu/bytecode/Type;
    //   960: astore #12
    //   962: aload_0
    //   963: getfield new_value : Lgnu/expr/Expression;
    //   966: aload_1
    //   967: aload #14
    //   969: invokevirtual compile : (Lgnu/expr/Compilation;Lgnu/expr/Declaration;)V
    //   972: aload_1
    //   973: aload #11
    //   975: invokevirtual getDeclaringClass : ()Lgnu/bytecode/ClassType;
    //   978: invokevirtual usedClass : (Lgnu/bytecode/Type;)V
    //   981: aload #11
    //   983: invokevirtual getStaticFlag : ()Z
    //   986: ifeq -> 1016
    //   989: iload #9
    //   991: istore_3
    //   992: iload #5
    //   994: ifeq -> 1006
    //   997: aload #19
    //   999: aload #12
    //   1001: invokevirtual emitDup : (Lgnu/bytecode/Type;)V
    //   1004: iconst_1
    //   1005: istore_3
    //   1006: aload #19
    //   1008: aload #11
    //   1010: invokevirtual emitPutStatic : (Lgnu/bytecode/Field;)V
    //   1013: goto -> 146
    //   1016: iload #10
    //   1018: istore_3
    //   1019: iload #5
    //   1021: ifeq -> 1031
    //   1024: aload #19
    //   1026: invokevirtual emitDupX : ()V
    //   1029: iconst_1
    //   1030: istore_3
    //   1031: aload #19
    //   1033: aload #11
    //   1035: invokevirtual emitPutField : (Lgnu/bytecode/Field;)V
    //   1038: goto -> 146
    //   1041: iload #5
    //   1043: ifeq -> 1056
    //   1046: aload_2
    //   1047: aload_1
    //   1048: aload_0
    //   1049: invokevirtual getType : ()Lgnu/bytecode/Type;
    //   1052: invokevirtual compileFromStack : (Lgnu/expr/Compilation;Lgnu/bytecode/Type;)V
    //   1055: return
    //   1056: aload_1
    //   1057: getstatic gnu/mapping/Values.empty : Lgnu/mapping/Values;
    //   1060: aload_2
    //   1061: invokevirtual compileConstant : (Ljava/lang/Object;Lgnu/expr/Target;)V
    //   1064: return
  }
  
  public final boolean getHasValue() {
    return ((this.flags & 0x40) != 0);
  }
  
  public final Expression getNewValue() {
    return this.new_value;
  }
  
  public final Type getType() {
    return (Type)(!getHasValue() ? Type.voidType : ((this.binding == null) ? Type.pointer_type : this.binding.getType()));
  }
  
  public final boolean isDefining() {
    return ((this.flags & 0x2) != 0);
  }
  
  public final boolean isFuncDef() {
    return ((this.flags & 0x10) != 0);
  }
  
  public final boolean isSetIfUnbound() {
    return ((this.flags & 0x20) != 0);
  }
  
  protected boolean mustCompile() {
    return false;
  }
  
  public void print(OutPort paramOutPort) {
    String str;
    if (isDefining()) {
      str = "(Define";
    } else {
      str = "(Set";
    } 
    paramOutPort.startLogicalBlock(str, ")", 2);
    paramOutPort.writeSpaceFill();
    printLineColumn(paramOutPort);
    if (this.binding == null || this.symbol.toString() != this.binding.getName()) {
      paramOutPort.print('/');
      paramOutPort.print(this.symbol);
    } 
    if (this.binding != null) {
      paramOutPort.print('/');
      paramOutPort.print(this.binding);
    } 
    paramOutPort.writeSpaceLinear();
    this.new_value.print(paramOutPort);
    paramOutPort.endLogicalBlock(")");
  }
  
  public final void setDefining(boolean paramBoolean) {
    if (paramBoolean) {
      this.flags |= 0x2;
      return;
    } 
    this.flags &= 0xFFFFFFFD;
  }
  
  public final void setFuncDef(boolean paramBoolean) {
    if (paramBoolean) {
      this.flags |= 0x10;
      return;
    } 
    this.flags &= 0xFFFFFFEF;
  }
  
  public final void setHasValue(boolean paramBoolean) {
    if (paramBoolean) {
      this.flags |= 0x40;
      return;
    } 
    this.flags &= 0xFFFFFFBF;
  }
  
  public final void setSetIfUnbound(boolean paramBoolean) {
    if (paramBoolean) {
      this.flags |= 0x20;
      return;
    } 
    this.flags &= 0xFFFFFFDF;
  }
  
  public String toString() {
    return "SetExp[" + this.symbol + ":=" + this.new_value + ']';
  }
  
  protected <R, D> R visit(ExpVisitor<R, D> paramExpVisitor, D paramD) {
    return paramExpVisitor.visitSetExp(this, paramD);
  }
  
  protected <R, D> void visitChildren(ExpVisitor<R, D> paramExpVisitor, D paramD) {
    this.new_value = paramExpVisitor.visitAndUpdate(this.new_value, paramD);
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/gnu/expr/SetExp.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */