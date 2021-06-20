package gnu.kawa.functions;

import gnu.bytecode.ClassType;
import gnu.bytecode.ObjectType;
import gnu.bytecode.Type;
import gnu.expr.ApplyExp;
import gnu.expr.Compilation;
import gnu.expr.Declaration;
import gnu.expr.Expression;
import gnu.expr.InlineCalls;
import gnu.expr.QuoteExp;
import gnu.expr.ReferenceExp;
import gnu.kawa.reflect.Invoke;
import gnu.kawa.reflect.SlotGet;
import gnu.kawa.reflect.SlotSet;
import gnu.mapping.Environment;
import gnu.mapping.EnvironmentKey;
import gnu.mapping.Namespace;
import gnu.mapping.Procedure;
import gnu.mapping.Symbol;
import kawa.lang.Translator;

public class CompileNamedPart {
  static final ClassType typeHasNamedParts = ClassType.make("gnu.mapping.HasNamedParts");
  
  public static String combineName(Expression paramExpression1, Expression paramExpression2) {
    Object object = paramExpression2.valueIfConstant();
    if (object instanceof gnu.mapping.SimpleSymbol) {
      if (paramExpression1 instanceof ReferenceExp) {
        String str2 = ((ReferenceExp)paramExpression1).getSimpleName();
        String str1 = str2;
        if (str2 != null)
          return (str1 + ':' + object).intern(); 
      } 
      if (paramExpression1 instanceof GetNamedExp) {
        String str = ((GetNamedExp)paramExpression1).combinedName;
        if (str != null)
          return (str + ':' + object).intern(); 
      } 
    } 
    return null;
  }
  
  public static Expression makeExp(Type paramType, String paramString) {
    return makeExp((Expression)new QuoteExp(paramType), (Expression)new QuoteExp(paramString));
  }
  
  public static Expression makeExp(Expression paramExpression1, Expression paramExpression2) {
    String str = combineName(paramExpression1, paramExpression2);
    Environment environment = Environment.getCurrent();
    if (str != null) {
      Translator translator = (Translator)Compilation.getCurrent();
      Symbol symbol = Namespace.EmptyNamespace.getSymbol(str);
      Declaration declaration = translator.lexical.lookup(symbol, false);
      if (!Declaration.isUnknown(declaration))
        return (Expression)new ReferenceExp(declaration); 
      if (symbol != null && environment.isBound(symbol, null))
        return (Expression)new ReferenceExp(str); 
    } 
    Expression expression = paramExpression1;
    if (paramExpression1 instanceof ReferenceExp) {
      ReferenceExp referenceExp = (ReferenceExp)paramExpression1;
      expression = paramExpression1;
      if (referenceExp.isUnknown()) {
        Object object = referenceExp.getSymbol();
        if (object instanceof Symbol) {
          object = object;
        } else {
          object = environment.getSymbol(object.toString());
        } 
        expression = paramExpression1;
        if (environment.get((EnvironmentKey)object, null) == null) {
          object = referenceExp.getName();
          try {
            QuoteExp quoteExp = QuoteExp.getInstance(Type.make(ClassType.getContextClass((String)object)));
          } catch (Throwable throwable) {
            expression = paramExpression1;
          } 
        } 
      } 
    } 
    GetNamedExp getNamedExp = new GetNamedExp(new Expression[] { expression, paramExpression2 });
    getNamedExp.combinedName = str;
    return (Expression)getNamedExp;
  }
  
  public static Expression makeExp(Expression paramExpression, String paramString) {
    return makeExp(paramExpression, (Expression)new QuoteExp(paramString));
  }
  
  public static Expression makeGetNamedInstancePartExp(Expression paramExpression) {
    if (paramExpression instanceof QuoteExp) {
      Object object = ((QuoteExp)paramExpression).getValue();
      if (object instanceof gnu.mapping.SimpleSymbol)
        return (Expression)QuoteExp.getInstance(new GetNamedInstancePart(object.toString())); 
    } 
    QuoteExp quoteExp = new QuoteExp(ClassType.make("gnu.kawa.functions.GetNamedInstancePart"));
    return (Expression)new ApplyExp((Procedure)Invoke.make, new Expression[] { (Expression)quoteExp, paramExpression });
  }
  
  public static Expression validateGetNamedInstancePart(ApplyExp paramApplyExp, InlineCalls paramInlineCalls, Type paramType, Procedure paramProcedure) {
    SlotGet slotGet;
    paramApplyExp.visitArgs(paramInlineCalls);
    Expression[] arrayOfExpression1 = paramApplyExp.getArgs();
    GetNamedInstancePart getNamedInstancePart = (GetNamedInstancePart)paramProcedure;
    if (getNamedInstancePart.isField) {
      Expression[] arrayOfExpression = new Expression[2];
      arrayOfExpression[0] = arrayOfExpression1[0];
      arrayOfExpression[1] = (Expression)new QuoteExp(getNamedInstancePart.pname);
      slotGet = SlotGet.field;
      return paramInlineCalls.visitApplyOnly(new ApplyExp((Procedure)slotGet, arrayOfExpression), paramType);
    } 
    int i = slotGet.length;
    Expression[] arrayOfExpression2 = new Expression[i + 1];
    arrayOfExpression2[0] = (Expression)slotGet[0];
    arrayOfExpression2[1] = (Expression)new QuoteExp(getNamedInstancePart.pname);
    System.arraycopy(slotGet, 1, arrayOfExpression2, 2, i - 1);
    Invoke invoke = Invoke.invoke;
    return paramInlineCalls.visitApplyOnly(new ApplyExp((Procedure)invoke, arrayOfExpression2), paramType);
  }
  
  public static Expression validateGetNamedPart(ApplyExp paramApplyExp, InlineCalls paramInlineCalls, Type paramType, Procedure paramProcedure) {
    // Byte code:
    //   0: aload_0
    //   1: aload_1
    //   2: invokevirtual visitArgs : (Lgnu/expr/InlineCalls;)V
    //   5: aload_0
    //   6: invokevirtual getArgs : ()[Lgnu/expr/Expression;
    //   9: astore #5
    //   11: aload #5
    //   13: arraylength
    //   14: iconst_2
    //   15: if_icmpne -> 35
    //   18: aload #5
    //   20: iconst_1
    //   21: aaload
    //   22: instanceof gnu/expr/QuoteExp
    //   25: ifeq -> 35
    //   28: aload_0
    //   29: instanceof gnu/kawa/functions/GetNamedExp
    //   32: ifne -> 37
    //   35: aload_0
    //   36: areturn
    //   37: aload #5
    //   39: iconst_0
    //   40: aaload
    //   41: astore_3
    //   42: aconst_null
    //   43: astore #4
    //   45: aload_3
    //   46: instanceof gnu/expr/ReferenceExp
    //   49: ifeq -> 86
    //   52: aload_3
    //   53: checkcast gnu/expr/ReferenceExp
    //   56: astore #4
    //   58: ldc '*'
    //   60: aload #4
    //   62: invokevirtual getName : ()Ljava/lang/String;
    //   65: invokevirtual equals : (Ljava/lang/Object;)Z
    //   68: ifeq -> 79
    //   71: aload #5
    //   73: iconst_1
    //   74: aaload
    //   75: invokestatic makeGetNamedInstancePartExp : (Lgnu/expr/Expression;)Lgnu/expr/Expression;
    //   78: areturn
    //   79: aload #4
    //   81: invokevirtual getBinding : ()Lgnu/expr/Declaration;
    //   84: astore #4
    //   86: aload #5
    //   88: iconst_1
    //   89: aaload
    //   90: checkcast gnu/expr/QuoteExp
    //   93: invokevirtual getValue : ()Ljava/lang/Object;
    //   96: invokevirtual toString : ()Ljava/lang/String;
    //   99: astore #6
    //   101: aload_3
    //   102: invokevirtual getType : ()Lgnu/bytecode/Type;
    //   105: astore #7
    //   107: aload_3
    //   108: getstatic gnu/expr/QuoteExp.nullExp : Lgnu/expr/QuoteExp;
    //   111: if_acmpne -> 174
    //   114: aload_1
    //   115: invokevirtual getCompilation : ()Lgnu/expr/Compilation;
    //   118: astore #8
    //   120: aload #8
    //   122: invokevirtual getLanguage : ()Lgnu/expr/Language;
    //   125: astore #9
    //   127: aload #9
    //   129: aload_3
    //   130: iconst_0
    //   131: invokevirtual getTypeFor : (Lgnu/expr/Expression;Z)Lgnu/bytecode/Type;
    //   134: astore #10
    //   136: aload #8
    //   138: ifnonnull -> 177
    //   141: aconst_null
    //   142: astore_3
    //   143: aload_0
    //   144: checkcast gnu/kawa/functions/GetNamedExp
    //   147: astore #11
    //   149: aload #10
    //   151: ifnull -> 268
    //   154: aload #6
    //   156: ldc '<>'
    //   158: invokevirtual equals : (Ljava/lang/Object;)Z
    //   161: ifeq -> 203
    //   164: new gnu/expr/QuoteExp
    //   167: dup
    //   168: aload #10
    //   170: invokespecial <init> : (Ljava/lang/Object;)V
    //   173: areturn
    //   174: goto -> 114
    //   177: aload #8
    //   179: getfield curClass : Lgnu/bytecode/ClassType;
    //   182: ifnull -> 194
    //   185: aload #8
    //   187: getfield curClass : Lgnu/bytecode/ClassType;
    //   190: astore_3
    //   191: goto -> 143
    //   194: aload #8
    //   196: getfield mainClass : Lgnu/bytecode/ClassType;
    //   199: astore_3
    //   200: goto -> 143
    //   203: aload #10
    //   205: instanceof gnu/bytecode/ObjectType
    //   208: ifeq -> 268
    //   211: aload #6
    //   213: ldc_w 'new'
    //   216: invokevirtual equals : (Ljava/lang/Object;)Z
    //   219: ifeq -> 230
    //   222: aload #11
    //   224: bipush #78
    //   226: invokevirtual setProcedureKind : (C)Lgnu/kawa/functions/GetNamedExp;
    //   229: areturn
    //   230: aload #6
    //   232: ldc_w 'instance?'
    //   235: invokevirtual equals : (Ljava/lang/Object;)Z
    //   238: ifeq -> 249
    //   241: aload #11
    //   243: bipush #73
    //   245: invokevirtual setProcedureKind : (C)Lgnu/kawa/functions/GetNamedExp;
    //   248: areturn
    //   249: aload #6
    //   251: ldc_w '@'
    //   254: invokevirtual equals : (Ljava/lang/Object;)Z
    //   257: ifeq -> 268
    //   260: aload #11
    //   262: bipush #67
    //   264: invokevirtual setProcedureKind : (C)Lgnu/kawa/functions/GetNamedExp;
    //   267: areturn
    //   268: aload #10
    //   270: instanceof gnu/bytecode/ObjectType
    //   273: ifeq -> 394
    //   276: aload #6
    //   278: invokevirtual length : ()I
    //   281: iconst_1
    //   282: if_icmple -> 317
    //   285: aload #6
    //   287: iconst_0
    //   288: invokevirtual charAt : (I)C
    //   291: bipush #46
    //   293: if_icmpne -> 317
    //   296: new gnu/expr/QuoteExp
    //   299: dup
    //   300: new gnu/kawa/functions/NamedPart
    //   303: dup
    //   304: aload #10
    //   306: aload #6
    //   308: bipush #68
    //   310: invokespecial <init> : (Ljava/lang/Object;Ljava/lang/Object;C)V
    //   313: invokespecial <init> : (Ljava/lang/Object;)V
    //   316: areturn
    //   317: aload #10
    //   319: aload #8
    //   321: invokestatic checkKnownClass : (Lgnu/bytecode/Type;Lgnu/expr/Compilation;)I
    //   324: iflt -> 35
    //   327: aload #10
    //   329: checkcast gnu/bytecode/ObjectType
    //   332: aload #6
    //   334: invokestatic mangleName : (Ljava/lang/String;)Ljava/lang/String;
    //   337: iconst_0
    //   338: aload_3
    //   339: aload #9
    //   341: invokestatic getMethods : (Lgnu/bytecode/ObjectType;Ljava/lang/String;CLgnu/bytecode/ClassType;Lgnu/expr/Language;)[Lgnu/expr/PrimProcedure;
    //   344: astore_3
    //   345: aload_3
    //   346: ifnull -> 368
    //   349: aload_3
    //   350: arraylength
    //   351: ifle -> 368
    //   354: aload #11
    //   356: aload_3
    //   357: putfield methods : [Lgnu/expr/PrimProcedure;
    //   360: aload #11
    //   362: bipush #83
    //   364: invokevirtual setProcedureKind : (C)Lgnu/kawa/functions/GetNamedExp;
    //   367: areturn
    //   368: new gnu/expr/ApplyExp
    //   371: dup
    //   372: getstatic gnu/kawa/reflect/SlotGet.staticField : Lgnu/kawa/reflect/SlotGet;
    //   375: aload #5
    //   377: invokespecial <init> : (Lgnu/mapping/Procedure;[Lgnu/expr/Expression;)V
    //   380: astore_3
    //   381: aload_3
    //   382: aload_0
    //   383: invokevirtual setLine : (Lgnu/expr/Expression;)Lgnu/expr/Expression;
    //   386: pop
    //   387: aload_1
    //   388: aload_3
    //   389: aload_2
    //   390: invokevirtual visitApplyOnly : (Lgnu/expr/ApplyExp;Lgnu/bytecode/Type;)Lgnu/expr/Expression;
    //   393: areturn
    //   394: aload #10
    //   396: ifnull -> 399
    //   399: aload #7
    //   401: getstatic gnu/expr/Compilation.typeClassType : Lgnu/bytecode/ClassType;
    //   404: invokevirtual isSubtype : (Lgnu/bytecode/Type;)Z
    //   407: ifne -> 35
    //   410: aload #7
    //   412: getstatic gnu/bytecode/Type.javalangClassType : Lgnu/bytecode/ClassType;
    //   415: invokevirtual isSubtype : (Lgnu/bytecode/Type;)Z
    //   418: ifne -> 35
    //   421: aload #7
    //   423: instanceof gnu/bytecode/ObjectType
    //   426: ifeq -> 637
    //   429: aload #7
    //   431: checkcast gnu/bytecode/ObjectType
    //   434: astore #10
    //   436: aload #10
    //   438: aload #6
    //   440: invokestatic mangleName : (Ljava/lang/String;)Ljava/lang/String;
    //   443: bipush #86
    //   445: aload_3
    //   446: aload #9
    //   448: invokestatic getMethods : (Lgnu/bytecode/ObjectType;Ljava/lang/String;CLgnu/bytecode/ClassType;Lgnu/expr/Language;)[Lgnu/expr/PrimProcedure;
    //   451: astore #9
    //   453: aload #9
    //   455: ifnull -> 479
    //   458: aload #9
    //   460: arraylength
    //   461: ifle -> 479
    //   464: aload #11
    //   466: aload #9
    //   468: putfield methods : [Lgnu/expr/PrimProcedure;
    //   471: aload #11
    //   473: bipush #77
    //   475: invokevirtual setProcedureKind : (C)Lgnu/kawa/functions/GetNamedExp;
    //   478: areturn
    //   479: aload #7
    //   481: getstatic gnu/kawa/functions/CompileNamedPart.typeHasNamedParts : Lgnu/bytecode/ClassType;
    //   484: invokevirtual isSubtype : (Lgnu/bytecode/Type;)Z
    //   487: ifeq -> 581
    //   490: aload #4
    //   492: ifnull -> 536
    //   495: aload #4
    //   497: invokestatic followAliases : (Lgnu/expr/Declaration;)Lgnu/expr/Declaration;
    //   500: invokevirtual getConstantValue : ()Ljava/lang/Object;
    //   503: astore_1
    //   504: aload_1
    //   505: ifnull -> 536
    //   508: aload_1
    //   509: checkcast gnu/mapping/HasNamedParts
    //   512: astore_1
    //   513: aload_1
    //   514: aload #6
    //   516: invokeinterface isConstant : (Ljava/lang/String;)Z
    //   521: ifeq -> 536
    //   524: aload_1
    //   525: aload #6
    //   527: invokeinterface get : (Ljava/lang/String;)Ljava/lang/Object;
    //   532: invokestatic getInstance : (Ljava/lang/Object;)Lgnu/expr/QuoteExp;
    //   535: areturn
    //   536: aload #5
    //   538: iconst_0
    //   539: aaload
    //   540: astore_1
    //   541: aload #6
    //   543: invokestatic getInstance : (Ljava/lang/Object;)Lgnu/expr/QuoteExp;
    //   546: astore_2
    //   547: new gnu/expr/ApplyExp
    //   550: dup
    //   551: getstatic gnu/kawa/functions/CompileNamedPart.typeHasNamedParts : Lgnu/bytecode/ClassType;
    //   554: ldc_w 'get'
    //   557: iconst_1
    //   558: invokevirtual getDeclaredMethod : (Ljava/lang/String;I)Lgnu/bytecode/Method;
    //   561: iconst_2
    //   562: anewarray gnu/expr/Expression
    //   565: dup
    //   566: iconst_0
    //   567: aload_1
    //   568: aastore
    //   569: dup
    //   570: iconst_1
    //   571: aload_2
    //   572: aastore
    //   573: invokespecial <init> : (Lgnu/bytecode/Method;[Lgnu/expr/Expression;)V
    //   576: aload_0
    //   577: invokevirtual setLine : (Lgnu/expr/Expression;)Lgnu/expr/Expression;
    //   580: areturn
    //   581: aload #10
    //   583: aload #6
    //   585: aload_3
    //   586: invokestatic lookupMember : (Lgnu/bytecode/ObjectType;Ljava/lang/String;Lgnu/bytecode/ClassType;)Lgnu/bytecode/Member;
    //   589: ifnonnull -> 611
    //   592: aload #6
    //   594: ldc_w 'length'
    //   597: invokevirtual equals : (Ljava/lang/Object;)Z
    //   600: ifeq -> 637
    //   603: aload #7
    //   605: instanceof gnu/bytecode/ArrayType
    //   608: ifeq -> 637
    //   611: new gnu/expr/ApplyExp
    //   614: dup
    //   615: getstatic gnu/kawa/reflect/SlotGet.field : Lgnu/kawa/reflect/SlotGet;
    //   618: aload #5
    //   620: invokespecial <init> : (Lgnu/mapping/Procedure;[Lgnu/expr/Expression;)V
    //   623: astore_3
    //   624: aload_3
    //   625: aload_0
    //   626: invokevirtual setLine : (Lgnu/expr/Expression;)Lgnu/expr/Expression;
    //   629: pop
    //   630: aload_1
    //   631: aload_3
    //   632: aload_2
    //   633: invokevirtual visitApplyOnly : (Lgnu/expr/ApplyExp;Lgnu/bytecode/Type;)Lgnu/expr/Expression;
    //   636: areturn
    //   637: aload #8
    //   639: invokevirtual warnUnknownMember : ()Z
    //   642: ifeq -> 35
    //   645: aload #8
    //   647: bipush #119
    //   649: new java/lang/StringBuilder
    //   652: dup
    //   653: invokespecial <init> : ()V
    //   656: ldc_w 'no known slot ''
    //   659: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   662: aload #6
    //   664: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   667: ldc_w '' in '
    //   670: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   673: aload #7
    //   675: invokevirtual getName : ()Ljava/lang/String;
    //   678: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   681: invokevirtual toString : ()Ljava/lang/String;
    //   684: invokevirtual error : (CLjava/lang/String;)V
    //   687: aload_0
    //   688: areturn
  }
  
  public static Expression validateNamedPart(ApplyExp paramApplyExp, InlineCalls paramInlineCalls, Type paramType, Procedure paramProcedure) {
    ApplyExp applyExp2;
    paramApplyExp.visitArgs(paramInlineCalls);
    Expression[] arrayOfExpression2 = paramApplyExp.getArgs();
    NamedPart namedPart = (NamedPart)paramProcedure;
    switch (namedPart.kind) {
      default:
        return (Expression)paramApplyExp;
      case 'D':
        break;
    } 
    String str = namedPart.member.toString().substring(1);
    Expression[] arrayOfExpression1 = new Expression[2];
    arrayOfExpression1[1] = (Expression)QuoteExp.getInstance(str);
    if (arrayOfExpression2.length > 0) {
      arrayOfExpression1[0] = (Expression)Compilation.makeCoercion(arrayOfExpression2[0], (Expression)new QuoteExp(namedPart.container));
      SlotGet slotGet1 = SlotGet.field;
      applyExp2 = new ApplyExp((Procedure)slotGet1, arrayOfExpression1);
      applyExp2.setLine((Expression)paramApplyExp);
      return paramInlineCalls.visitApplyOnly(applyExp2, paramType);
    } 
    arrayOfExpression1[0] = (Expression)QuoteExp.getInstance(((NamedPart)applyExp2).container);
    SlotGet slotGet = SlotGet.staticField;
    ApplyExp applyExp1 = new ApplyExp((Procedure)slotGet, arrayOfExpression1);
    applyExp1.setLine((Expression)paramApplyExp);
    return paramInlineCalls.visitApplyOnly(applyExp1, paramType);
  }
  
  public static Expression validateNamedPartSetter(ApplyExp paramApplyExp, InlineCalls paramInlineCalls, Type paramType, Procedure paramProcedure) {
    Expression expression;
    paramApplyExp.visitArgs(paramInlineCalls);
    NamedPart namedPart = (NamedPart)((NamedPartSetter)paramProcedure).getGetter();
    ApplyExp applyExp = paramApplyExp;
    if (namedPart.kind == 'D') {
      SlotSet slotSet;
      Expression[] arrayOfExpression = new Expression[3];
      arrayOfExpression[1] = (Expression)QuoteExp.getInstance(namedPart.member.toString().substring(1));
      arrayOfExpression[2] = paramApplyExp.getArgs()[0];
      if (paramApplyExp.getArgCount() == 1) {
        arrayOfExpression[0] = (Expression)QuoteExp.getInstance(namedPart.container);
        slotSet = SlotSet.set$Mnstatic$Mnfield$Ex;
      } else {
        applyExp = paramApplyExp;
        if (paramApplyExp.getArgCount() == 2) {
          arrayOfExpression[0] = (Expression)Compilation.makeCoercion(paramApplyExp.getArgs()[0], (Expression)new QuoteExp(namedPart.container));
          slotSet = SlotSet.set$Mnfield$Ex;
        } else {
          return (Expression)slotSet;
        } 
      } 
      ApplyExp applyExp1 = new ApplyExp((Procedure)slotSet, arrayOfExpression);
      applyExp1.setLine((Expression)paramApplyExp);
      expression = paramInlineCalls.visitApplyOnly(applyExp1, paramType);
    } 
    return expression;
  }
  
  public static Expression validateSetNamedInstancePart(ApplyExp paramApplyExp, InlineCalls paramInlineCalls, Type paramType, Procedure paramProcedure) {
    paramApplyExp.visitArgs(paramInlineCalls);
    Expression[] arrayOfExpression = paramApplyExp.getArgs();
    String str = ((SetNamedInstancePart)paramProcedure).pname;
    Expression expression2 = arrayOfExpression[0];
    QuoteExp quoteExp = new QuoteExp(str);
    Expression expression1 = arrayOfExpression[1];
    return paramInlineCalls.visitApplyOnly(new ApplyExp((Procedure)SlotSet.set$Mnfield$Ex, new Expression[] { expression2, (Expression)quoteExp, expression1 }), paramType);
  }
  
  public static Expression validateSetNamedPart(ApplyExp paramApplyExp, InlineCalls paramInlineCalls, Type paramType, Procedure paramProcedure) {
    ApplyExp applyExp;
    ClassType classType;
    paramApplyExp.visitArgs(paramInlineCalls);
    Expression[] arrayOfExpression = paramApplyExp.getArgs();
    if (arrayOfExpression.length != 3 || !(arrayOfExpression[1] instanceof QuoteExp))
      return (Expression)paramApplyExp; 
    Expression expression = arrayOfExpression[0];
    String str = ((QuoteExp)arrayOfExpression[1]).getValue().toString();
    Type type2 = expression.getType();
    Compilation compilation = paramInlineCalls.getCompilation();
    Type type1 = compilation.getLanguage().getTypeFor(expression);
    if (compilation == null) {
      expression = null;
    } else if (compilation.curClass != null) {
      classType = compilation.curClass;
    } else {
      classType = compilation.mainClass;
    } 
    if (type1 instanceof ClassType) {
      applyExp = new ApplyExp((Procedure)SlotSet.set$Mnstatic$Mnfield$Ex, arrayOfExpression);
    } else {
      applyExp = paramApplyExp;
      if (type2 instanceof ClassType) {
        applyExp = paramApplyExp;
        if (SlotSet.lookupMember((ObjectType)type2, str, classType) != null)
          applyExp = new ApplyExp((Procedure)SlotSet.set$Mnfield$Ex, arrayOfExpression); 
      } 
    } 
    if (applyExp != paramApplyExp)
      applyExp.setLine((Expression)paramApplyExp); 
    applyExp.setType((Type)Type.voidType);
    return (Expression)applyExp;
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/gnu/kawa/functions/CompileNamedPart.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */