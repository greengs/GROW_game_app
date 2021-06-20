package gnu.kawa.reflect;

import gnu.bytecode.PrimType;
import gnu.bytecode.Type;
import gnu.expr.ApplyExp;
import gnu.expr.Compilation;
import gnu.expr.Expression;
import gnu.expr.InlineCalls;
import gnu.expr.LambdaExp;
import gnu.expr.Language;
import gnu.expr.QuoteExp;
import gnu.mapping.Procedure;

public class CompileReflect {
  public static int checkKnownClass(Type paramType, Compilation paramCompilation) {
    if (paramType instanceof gnu.bytecode.ClassType && paramType.isExisting())
      try {
        paramType.getReflectClass();
        return 1;
      } catch (Exception exception) {
        paramCompilation.error('e', "unknown class: " + paramType.getName());
        return -1;
      }  
    return 0;
  }
  
  public static ApplyExp inlineClassName(ApplyExp paramApplyExp, int paramInt, InlineCalls paramInlineCalls) {
    Compilation compilation = paramInlineCalls.getCompilation();
    Language language = compilation.getLanguage();
    Expression[] arrayOfExpression = paramApplyExp.getArgs();
    if (arrayOfExpression.length > paramInt) {
      Type type = language.getTypeFor(arrayOfExpression[paramInt]);
      if (type instanceof Type) {
        checkKnownClass(type, compilation);
        Expression[] arrayOfExpression1 = new Expression[arrayOfExpression.length];
        System.arraycopy(arrayOfExpression, 0, arrayOfExpression1, 0, arrayOfExpression.length);
        arrayOfExpression1[paramInt] = (Expression)new QuoteExp(type);
        ApplyExp applyExp = new ApplyExp(paramApplyExp.getFunction(), arrayOfExpression1);
        applyExp.setLine((Expression)paramApplyExp);
        return applyExp;
      } 
    } 
    return paramApplyExp;
  }
  
  public static Expression validateApplyInstanceOf(ApplyExp paramApplyExp, InlineCalls paramInlineCalls, Type paramType, Procedure paramProcedure) {
    paramApplyExp.visitArgs(paramInlineCalls);
    ApplyExp applyExp = inlineClassName(paramApplyExp, 1, paramInlineCalls);
    Expression[] arrayOfExpression = applyExp.getArgs();
    if (arrayOfExpression.length == 2) {
      Expression expression2 = arrayOfExpression[0];
      Expression expression1 = arrayOfExpression[1];
      if (expression1 instanceof QuoteExp) {
        Object object = ((QuoteExp)expression1).getValue();
        if (object instanceof Type) {
          Type type = (Type)object;
          object = type;
          if (type instanceof PrimType)
            object = ((PrimType)type).boxedType(); 
          if (expression2 instanceof QuoteExp)
            return (Expression)(object.isInstance(((QuoteExp)expression2).getValue()) ? QuoteExp.trueExp : QuoteExp.falseExp); 
          if (!expression2.side_effects()) {
            int i = object.compare(expression2.getType());
            if (i == 1 || i == 0)
              return (Expression)QuoteExp.trueExp; 
            if (i == -3)
              return (Expression)QuoteExp.falseExp; 
          } 
        } 
      } 
    } 
    return (Expression)applyExp;
  }
  
  public static Expression validateApplySlotGet(ApplyExp paramApplyExp, InlineCalls paramInlineCalls, Type paramType, Procedure paramProcedure) {
    // Byte code:
    //   0: aload_0
    //   1: aload_1
    //   2: invokevirtual visitArgs : (Lgnu/expr/InlineCalls;)V
    //   5: aload_1
    //   6: invokevirtual getCompilation : ()Lgnu/expr/Compilation;
    //   9: astore #13
    //   11: aload #13
    //   13: invokevirtual getLanguage : ()Lgnu/expr/Language;
    //   16: astore #9
    //   18: aload_3
    //   19: checkcast gnu/kawa/reflect/SlotGet
    //   22: getfield isStatic : Z
    //   25: istore #5
    //   27: aload_0
    //   28: invokevirtual getArgs : ()[Lgnu/expr/Expression;
    //   31: astore #10
    //   33: aload #10
    //   35: iconst_0
    //   36: aaload
    //   37: astore #12
    //   39: aload #10
    //   41: iconst_1
    //   42: aaload
    //   43: astore #14
    //   45: aload #14
    //   47: invokevirtual valueIfConstant : ()Ljava/lang/Object;
    //   50: astore_2
    //   51: aload_2
    //   52: instanceof java/lang/String
    //   55: ifne -> 75
    //   58: aload_2
    //   59: instanceof gnu/lists/FString
    //   62: ifne -> 75
    //   65: aload_0
    //   66: astore #8
    //   68: aload_2
    //   69: instanceof gnu/mapping/Symbol
    //   72: ifeq -> 112
    //   75: aload_2
    //   76: invokevirtual toString : ()Ljava/lang/String;
    //   79: astore #11
    //   81: iload #5
    //   83: ifeq -> 357
    //   86: aload #9
    //   88: aload #12
    //   90: invokevirtual getTypeFor : (Lgnu/expr/Expression;)Lgnu/bytecode/Type;
    //   93: astore #8
    //   95: aload #8
    //   97: aload #13
    //   99: invokestatic checkKnownClass : (Lgnu/bytecode/Type;Lgnu/expr/Compilation;)I
    //   102: istore #4
    //   104: iload #4
    //   106: ifge -> 115
    //   109: aload_0
    //   110: astore #8
    //   112: aload #8
    //   114: areturn
    //   115: ldc 'class'
    //   117: aload #11
    //   119: invokevirtual equals : (Ljava/lang/Object;)Z
    //   122: ifeq -> 165
    //   125: iload #4
    //   127: ifle -> 139
    //   130: aload #8
    //   132: invokevirtual getReflectClass : ()Ljava/lang/Class;
    //   135: invokestatic getInstance : (Ljava/lang/Object;)Lgnu/expr/QuoteExp;
    //   138: areturn
    //   139: new gnu/expr/ApplyExp
    //   142: dup
    //   143: getstatic gnu/expr/Compilation.typeType : Lgnu/bytecode/ClassType;
    //   146: ldc 'getReflectClass'
    //   148: iconst_0
    //   149: invokevirtual getDeclaredMethod : (Ljava/lang/String;I)Lgnu/bytecode/Method;
    //   152: iconst_1
    //   153: anewarray gnu/expr/Expression
    //   156: dup
    //   157: iconst_0
    //   158: aload #12
    //   160: aastore
    //   161: invokespecial <init> : (Lgnu/bytecode/Method;[Lgnu/expr/Expression;)V
    //   164: areturn
    //   165: aload #8
    //   167: astore #7
    //   169: aload_0
    //   170: astore_2
    //   171: aload #8
    //   173: ifnull -> 221
    //   176: new gnu/expr/QuoteExp
    //   179: dup
    //   180: aload #8
    //   182: invokespecial <init> : (Ljava/lang/Object;)V
    //   185: astore_2
    //   186: new gnu/expr/ApplyExp
    //   189: dup
    //   190: aload_0
    //   191: invokevirtual getFunction : ()Lgnu/expr/Expression;
    //   194: iconst_2
    //   195: anewarray gnu/expr/Expression
    //   198: dup
    //   199: iconst_0
    //   200: aload_2
    //   201: aastore
    //   202: dup
    //   203: iconst_1
    //   204: aload #14
    //   206: aastore
    //   207: invokespecial <init> : (Lgnu/expr/Expression;[Lgnu/expr/Expression;)V
    //   210: astore_2
    //   211: aload_2
    //   212: aload_0
    //   213: invokevirtual setLine : (Lgnu/expr/Expression;)Lgnu/expr/Expression;
    //   216: pop
    //   217: aload #8
    //   219: astore #7
    //   221: aload_2
    //   222: astore #8
    //   224: aload #7
    //   226: instanceof gnu/bytecode/ArrayType
    //   229: ifne -> 112
    //   232: aload #7
    //   234: instanceof gnu/bytecode/ObjectType
    //   237: ifeq -> 699
    //   240: aload #7
    //   242: checkcast gnu/bytecode/ObjectType
    //   245: astore #8
    //   247: aload #13
    //   249: getfield curClass : Lgnu/bytecode/ClassType;
    //   252: ifnull -> 369
    //   255: aload #13
    //   257: getfield curClass : Lgnu/bytecode/ClassType;
    //   260: astore_0
    //   261: aload #8
    //   263: aload #11
    //   265: aload_0
    //   266: invokestatic lookupMember : (Lgnu/bytecode/ObjectType;Ljava/lang/String;Lgnu/bytecode/ClassType;)Lgnu/bytecode/Member;
    //   269: astore #14
    //   271: aload #14
    //   273: instanceof gnu/bytecode/Field
    //   276: ifeq -> 450
    //   279: aload #14
    //   281: checkcast gnu/bytecode/Field
    //   284: astore #15
    //   286: aload #15
    //   288: invokevirtual getModifiers : ()I
    //   291: bipush #8
    //   293: iand
    //   294: ifeq -> 378
    //   297: iconst_1
    //   298: istore #4
    //   300: iload #5
    //   302: ifeq -> 384
    //   305: iload #4
    //   307: ifne -> 384
    //   310: new gnu/expr/ErrorExp
    //   313: dup
    //   314: new java/lang/StringBuilder
    //   317: dup
    //   318: invokespecial <init> : ()V
    //   321: ldc 'cannot access non-static field `'
    //   323: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   326: aload #11
    //   328: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   331: ldc '' using `'
    //   333: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   336: aload_3
    //   337: invokevirtual getName : ()Ljava/lang/String;
    //   340: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   343: bipush #39
    //   345: invokevirtual append : (C)Ljava/lang/StringBuilder;
    //   348: invokevirtual toString : ()Ljava/lang/String;
    //   351: aload #13
    //   353: invokespecial <init> : (Ljava/lang/String;Lgnu/expr/Compilation;)V
    //   356: areturn
    //   357: aload #12
    //   359: invokevirtual getType : ()Lgnu/bytecode/Type;
    //   362: astore #7
    //   364: aload_0
    //   365: astore_2
    //   366: goto -> 221
    //   369: aload #13
    //   371: getfield mainClass : Lgnu/bytecode/ClassType;
    //   374: astore_0
    //   375: goto -> 261
    //   378: iconst_0
    //   379: istore #4
    //   381: goto -> 300
    //   384: aload_0
    //   385: ifnull -> 595
    //   388: aload_0
    //   389: aload #15
    //   391: aload #8
    //   393: invokevirtual isAccessible : (Lgnu/bytecode/Member;Lgnu/bytecode/ObjectType;)Z
    //   396: ifne -> 595
    //   399: new gnu/expr/ErrorExp
    //   402: dup
    //   403: new java/lang/StringBuilder
    //   406: dup
    //   407: invokespecial <init> : ()V
    //   410: ldc 'field '
    //   412: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   415: aload #15
    //   417: invokevirtual getDeclaringClass : ()Lgnu/bytecode/ClassType;
    //   420: invokevirtual getName : ()Ljava/lang/String;
    //   423: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   426: bipush #46
    //   428: invokevirtual append : (C)Ljava/lang/StringBuilder;
    //   431: aload #11
    //   433: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   436: ldc ' is not accessible here'
    //   438: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   441: invokevirtual toString : ()Ljava/lang/String;
    //   444: aload #13
    //   446: invokespecial <init> : (Ljava/lang/String;Lgnu/expr/Compilation;)V
    //   449: areturn
    //   450: aload #14
    //   452: instanceof gnu/bytecode/Method
    //   455: ifeq -> 595
    //   458: aload #14
    //   460: checkcast gnu/bytecode/Method
    //   463: astore #15
    //   465: aload #15
    //   467: invokevirtual getDeclaringClass : ()Lgnu/bytecode/ClassType;
    //   470: astore #16
    //   472: aload #15
    //   474: invokevirtual getModifiers : ()I
    //   477: istore #4
    //   479: aload #15
    //   481: invokevirtual getStaticFlag : ()Z
    //   484: istore #6
    //   486: iload #5
    //   488: ifeq -> 543
    //   491: iload #6
    //   493: ifne -> 543
    //   496: new gnu/expr/ErrorExp
    //   499: dup
    //   500: new java/lang/StringBuilder
    //   503: dup
    //   504: invokespecial <init> : ()V
    //   507: ldc 'cannot call non-static getter method `'
    //   509: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   512: aload #11
    //   514: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   517: ldc '' using `'
    //   519: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   522: aload_3
    //   523: invokevirtual getName : ()Ljava/lang/String;
    //   526: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   529: bipush #39
    //   531: invokevirtual append : (C)Ljava/lang/StringBuilder;
    //   534: invokevirtual toString : ()Ljava/lang/String;
    //   537: aload #13
    //   539: invokespecial <init> : (Ljava/lang/String;Lgnu/expr/Compilation;)V
    //   542: areturn
    //   543: aload_0
    //   544: ifnull -> 595
    //   547: aload_0
    //   548: aload #16
    //   550: aload #8
    //   552: iload #4
    //   554: invokevirtual isAccessible : (Lgnu/bytecode/ClassType;Lgnu/bytecode/ObjectType;I)Z
    //   557: ifne -> 595
    //   560: new gnu/expr/ErrorExp
    //   563: dup
    //   564: new java/lang/StringBuilder
    //   567: dup
    //   568: invokespecial <init> : ()V
    //   571: ldc 'method '
    //   573: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   576: aload #15
    //   578: invokevirtual append : (Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   581: ldc ' is not accessible here'
    //   583: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   586: invokevirtual toString : ()Ljava/lang/String;
    //   589: aload #13
    //   591: invokespecial <init> : (Ljava/lang/String;Lgnu/expr/Compilation;)V
    //   594: areturn
    //   595: aload #14
    //   597: ifnull -> 643
    //   600: new gnu/expr/QuoteExp
    //   603: dup
    //   604: aload #14
    //   606: invokespecial <init> : (Ljava/lang/Object;)V
    //   609: astore_0
    //   610: new gnu/expr/ApplyExp
    //   613: dup
    //   614: aload_2
    //   615: invokevirtual getFunction : ()Lgnu/expr/Expression;
    //   618: iconst_2
    //   619: anewarray gnu/expr/Expression
    //   622: dup
    //   623: iconst_0
    //   624: aload #12
    //   626: aastore
    //   627: dup
    //   628: iconst_1
    //   629: aload_0
    //   630: aastore
    //   631: invokespecial <init> : (Lgnu/expr/Expression;[Lgnu/expr/Expression;)V
    //   634: astore_0
    //   635: aload_0
    //   636: aload_2
    //   637: invokevirtual setLine : (Lgnu/expr/Expression;)Lgnu/expr/Expression;
    //   640: pop
    //   641: aload_0
    //   642: areturn
    //   643: aload #7
    //   645: getstatic gnu/bytecode/Type.pointer_type : Lgnu/bytecode/ClassType;
    //   648: if_acmpeq -> 699
    //   651: aload #13
    //   653: invokevirtual warnUnknownMember : ()Z
    //   656: ifeq -> 699
    //   659: aload #13
    //   661: bipush #101
    //   663: new java/lang/StringBuilder
    //   666: dup
    //   667: invokespecial <init> : ()V
    //   670: ldc 'no slot `'
    //   672: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   675: aload #11
    //   677: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   680: ldc '' in '
    //   682: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   685: aload #8
    //   687: invokevirtual getName : ()Ljava/lang/String;
    //   690: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   693: invokevirtual toString : ()Ljava/lang/String;
    //   696: invokevirtual error : (CLjava/lang/String;)V
    //   699: aload #11
    //   701: invokestatic mangleNameIfNeeded : (Ljava/lang/String;)Ljava/lang/String;
    //   704: invokevirtual intern : ()Ljava/lang/String;
    //   707: astore_3
    //   708: ldc_w 'get'
    //   711: aload #11
    //   713: invokestatic slotToMethodName : (Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
    //   716: astore #7
    //   718: ldc_w 'is'
    //   721: aload #11
    //   723: invokestatic slotToMethodName : (Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
    //   726: astore #8
    //   728: getstatic gnu/kawa/reflect/Invoke.invokeStatic : Lgnu/kawa/reflect/Invoke;
    //   731: astore #12
    //   733: ldc_w 'gnu.kawa.reflect.SlotGet'
    //   736: invokestatic getInstance : (Ljava/lang/Object;)Lgnu/expr/QuoteExp;
    //   739: astore #13
    //   741: ldc_w 'getSlotValue'
    //   744: invokestatic getInstance : (Ljava/lang/Object;)Lgnu/expr/QuoteExp;
    //   747: astore #14
    //   749: iload #5
    //   751: ifeq -> 849
    //   754: getstatic gnu/expr/QuoteExp.trueExp : Lgnu/expr/QuoteExp;
    //   757: astore_0
    //   758: new gnu/expr/ApplyExp
    //   761: dup
    //   762: aload #12
    //   764: bipush #9
    //   766: anewarray gnu/expr/Expression
    //   769: dup
    //   770: iconst_0
    //   771: aload #13
    //   773: aastore
    //   774: dup
    //   775: iconst_1
    //   776: aload #14
    //   778: aastore
    //   779: dup
    //   780: iconst_2
    //   781: aload_0
    //   782: aastore
    //   783: dup
    //   784: iconst_3
    //   785: aload #10
    //   787: iconst_0
    //   788: aaload
    //   789: aastore
    //   790: dup
    //   791: iconst_4
    //   792: aload #11
    //   794: invokestatic getInstance : (Ljava/lang/Object;)Lgnu/expr/QuoteExp;
    //   797: aastore
    //   798: dup
    //   799: iconst_5
    //   800: aload_3
    //   801: invokestatic getInstance : (Ljava/lang/Object;)Lgnu/expr/QuoteExp;
    //   804: aastore
    //   805: dup
    //   806: bipush #6
    //   808: aload #7
    //   810: invokestatic getInstance : (Ljava/lang/Object;)Lgnu/expr/QuoteExp;
    //   813: aastore
    //   814: dup
    //   815: bipush #7
    //   817: aload #8
    //   819: invokestatic getInstance : (Ljava/lang/Object;)Lgnu/expr/QuoteExp;
    //   822: aastore
    //   823: dup
    //   824: bipush #8
    //   826: aload #9
    //   828: invokestatic getInstance : (Ljava/lang/Object;)Lgnu/expr/QuoteExp;
    //   831: aastore
    //   832: invokespecial <init> : (Lgnu/mapping/Procedure;[Lgnu/expr/Expression;)V
    //   835: astore_0
    //   836: aload_0
    //   837: aload_2
    //   838: invokevirtual setLine : (Lgnu/expr/Expression;)Lgnu/expr/Expression;
    //   841: pop
    //   842: aload_1
    //   843: aload_0
    //   844: aconst_null
    //   845: invokevirtual visitApplyOnly : (Lgnu/expr/ApplyExp;Lgnu/bytecode/Type;)Lgnu/expr/Expression;
    //   848: areturn
    //   849: getstatic gnu/expr/QuoteExp.falseExp : Lgnu/expr/QuoteExp;
    //   852: astore_0
    //   853: goto -> 758
  }
  
  public static Expression validateApplySlotSet(ApplyExp paramApplyExp, InlineCalls paramInlineCalls, Type paramType, Procedure paramProcedure) {
    paramApplyExp.visitArgs(paramInlineCalls);
    SlotSet slotSet = (SlotSet)paramProcedure;
    ApplyExp applyExp = paramApplyExp;
    if (slotSet.isStatic) {
      applyExp = paramApplyExp;
      if ((paramInlineCalls.getCompilation()).mustCompile)
        applyExp = inlineClassName(paramApplyExp, 0, paramInlineCalls); 
    } 
    if (slotSet.returnSelf && applyExp.getArgCount() == 3) {
      Type type = applyExp.getArg(0).getType();
      applyExp.setType(type);
      return (Expression)applyExp;
    } 
    PrimType primType = Type.voidType;
    applyExp.setType((Type)primType);
    return (Expression)applyExp;
  }
  
  public static Expression validateApplyTypeSwitch(ApplyExp paramApplyExp, InlineCalls paramInlineCalls, Type paramType, Procedure paramProcedure) {
    paramApplyExp.visitArgs(paramInlineCalls);
    Expression[] arrayOfExpression = paramApplyExp.getArgs();
    int i;
    for (i = 1; i < arrayOfExpression.length; i++) {
      if (arrayOfExpression[i] instanceof LambdaExp) {
        LambdaExp lambdaExp = (LambdaExp)arrayOfExpression[i];
        lambdaExp.setInlineOnly(true);
        lambdaExp.returnContinuation = (Expression)paramApplyExp;
        lambdaExp.inlineHome = paramInlineCalls.getCurrentLambda();
      } 
    } 
    return (Expression)paramApplyExp;
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/gnu/kawa/reflect/CompileReflect.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */