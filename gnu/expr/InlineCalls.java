package gnu.expr;

import gnu.bytecode.ClassType;
import gnu.bytecode.Method;
import gnu.bytecode.Type;
import gnu.kawa.reflect.CompileReflect;
import gnu.kawa.reflect.Invoke;
import gnu.kawa.util.IdentityHashTable;
import gnu.mapping.Procedure;
import gnu.mapping.Values;
import gnu.math.IntNum;
import gnu.text.SourceLocator;

public class InlineCalls extends ExpExpVisitor<Type> {
  private static Class[] inlinerMethodArgTypes;
  
  public InlineCalls(Compilation paramCompilation) {
    setContext(paramCompilation);
  }
  
  public static Integer checkIntValue(Expression paramExpression) {
    if (paramExpression instanceof QuoteExp) {
      paramExpression = paramExpression;
      Object object = paramExpression.getValue();
      if (!paramExpression.isExplicitlyTyped() && object instanceof IntNum) {
        IntNum intNum = (IntNum)object;
        if (intNum.inIntRange())
          return Integer.valueOf(intNum.intValue()); 
      } 
    } 
    return null;
  }
  
  public static Long checkLongValue(Expression paramExpression) {
    if (paramExpression instanceof QuoteExp) {
      paramExpression = paramExpression;
      Object object = paramExpression.getValue();
      if (!paramExpression.isExplicitlyTyped() && object instanceof IntNum) {
        IntNum intNum = (IntNum)object;
        if (intNum.inLongRange())
          return Long.valueOf(intNum.longValue()); 
      } 
    } 
    return null;
  }
  
  private static Class[] getInlinerMethodArgTypes() throws Exception {
    // Byte code:
    //   0: ldc gnu/expr/InlineCalls
    //   2: monitorenter
    //   3: getstatic gnu/expr/InlineCalls.inlinerMethodArgTypes : [Ljava/lang/Class;
    //   6: astore_1
    //   7: aload_1
    //   8: astore_0
    //   9: aload_1
    //   10: ifnonnull -> 54
    //   13: iconst_4
    //   14: anewarray java/lang/Class
    //   17: astore_0
    //   18: aload_0
    //   19: iconst_0
    //   20: ldc 'gnu.expr.ApplyExp'
    //   22: invokestatic forName : (Ljava/lang/String;)Ljava/lang/Class;
    //   25: aastore
    //   26: aload_0
    //   27: iconst_1
    //   28: ldc 'gnu.expr.InlineCalls'
    //   30: invokestatic forName : (Ljava/lang/String;)Ljava/lang/Class;
    //   33: aastore
    //   34: aload_0
    //   35: iconst_2
    //   36: ldc 'gnu.bytecode.Type'
    //   38: invokestatic forName : (Ljava/lang/String;)Ljava/lang/Class;
    //   41: aastore
    //   42: aload_0
    //   43: iconst_3
    //   44: ldc 'gnu.mapping.Procedure'
    //   46: invokestatic forName : (Ljava/lang/String;)Ljava/lang/Class;
    //   49: aastore
    //   50: aload_0
    //   51: putstatic gnu/expr/InlineCalls.inlinerMethodArgTypes : [Ljava/lang/Class;
    //   54: ldc gnu/expr/InlineCalls
    //   56: monitorexit
    //   57: aload_0
    //   58: areturn
    //   59: astore_0
    //   60: ldc gnu/expr/InlineCalls
    //   62: monitorexit
    //   63: aload_0
    //   64: athrow
    // Exception table:
    //   from	to	target	type
    //   3	7	59	finally
    //   13	54	59	finally
  }
  
  public static Expression inlineCall(LambdaExp paramLambdaExp, Expression[] paramArrayOfExpression, boolean paramBoolean) {
    boolean bool;
    if (paramLambdaExp.keywords != null || (paramLambdaExp.nameDecl != null && !paramBoolean))
      return null; 
    if (paramLambdaExp.max_args < 0) {
      bool = true;
    } else {
      bool = false;
    } 
    if ((paramLambdaExp.min_args == paramLambdaExp.max_args && paramLambdaExp.min_args == paramArrayOfExpression.length) || (bool && paramLambdaExp.min_args == 0)) {
      Expression[] arrayOfExpression;
      IdentityHashTable identityHashTable;
      Declaration declaration2 = null;
      int i = 0;
      if (paramBoolean) {
        IdentityHashTable identityHashTable1 = new IdentityHashTable();
        Expression[] arrayOfExpression1 = Expression.deepCopy(paramArrayOfExpression, identityHashTable1);
        arrayOfExpression = arrayOfExpression1;
        identityHashTable = identityHashTable1;
        if (arrayOfExpression1 == null) {
          arrayOfExpression = arrayOfExpression1;
          identityHashTable = identityHashTable1;
          if (paramArrayOfExpression != null)
            return null; 
        } 
      } else {
        identityHashTable = null;
        arrayOfExpression = paramArrayOfExpression;
      } 
      if (bool) {
        Expression[] arrayOfExpression1 = new Expression[paramArrayOfExpression.length + 1];
        arrayOfExpression1[0] = QuoteExp.getInstance((paramLambdaExp.firstDecl()).type);
        System.arraycopy(paramArrayOfExpression, 0, arrayOfExpression1, 1, paramArrayOfExpression.length);
        arrayOfExpression = new Expression[1];
        arrayOfExpression[0] = new ApplyExp((Procedure)Invoke.make, arrayOfExpression1);
      } 
      LetExp letExp = new LetExp(arrayOfExpression);
      Declaration declaration1 = paramLambdaExp.firstDecl();
      while (declaration1 != null) {
        Declaration declaration = declaration1.nextDecl();
        if (paramBoolean) {
          declaration2 = letExp.addDeclaration(declaration1.symbol, declaration1.type);
          if (declaration1.typeExp != null) {
            declaration2.typeExp = Expression.deepCopy(declaration1.typeExp);
            if (declaration2.typeExp == null)
              return null; 
          } 
          identityHashTable.put(declaration1, declaration2);
        } else {
          paramLambdaExp.remove(declaration2, declaration1);
          letExp.add(declaration2, declaration1);
        } 
        if (!bool && !declaration1.getCanWrite())
          declaration1.setValue(arrayOfExpression[i]); 
        declaration2 = declaration1;
        declaration1 = declaration;
        i++;
      } 
      Expression expression2 = paramLambdaExp.body;
      Expression expression1 = expression2;
      if (paramBoolean) {
        expression2 = Expression.deepCopy(expression2, identityHashTable);
        expression1 = expression2;
        if (expression2 == null) {
          expression1 = expression2;
          if (paramLambdaExp.body != null)
            return null; 
        } 
      } 
      letExp.body = expression1;
      return letExp;
    } 
    return null;
  }
  
  public static Expression inlineCalls(Expression paramExpression, Compilation paramCompilation) {
    return (new InlineCalls(paramCompilation)).visit(paramExpression, (Type)null);
  }
  
  public Expression checkType(Expression paramExpression, Type paramType) {
    String str;
    boolean bool2;
    LambdaExp lambdaExp;
    ClassType classType;
    boolean bool1 = true;
    Type type = paramExpression.getType();
    if (paramType instanceof ClassType && ((ClassType)paramType).isInterface() && type.isSubtype((Type)Compilation.typeProcedure) && !type.isSubtype(paramType)) {
      if (paramExpression instanceof LambdaExp) {
        Method method = ((ClassType)paramType).checkSingleAbstractMethod();
        if (method != null) {
          lambdaExp = (LambdaExp)paramExpression;
          ObjectExp objectExp = new ObjectExp();
          objectExp.setLocation(paramExpression);
          objectExp.supers = new Expression[] { new QuoteExp(paramType) };
          objectExp.setTypes(getCompilation());
          str = method.getName();
          objectExp.addMethod(lambdaExp, str);
          objectExp.addDeclaration(str, (Type)Compilation.typeProcedure);
          objectExp.firstChild = lambdaExp;
          objectExp.declareParts(this.comp);
          return visit(objectExp, paramType);
        } 
      } 
      bool2 = true;
    } else {
      ClassType classType1;
      LambdaExp lambdaExp1 = lambdaExp;
      if (lambdaExp == Type.toStringType)
        classType1 = Type.javalangStringType; 
      if (paramType == null || paramType.compare((Type)classType1) != -3)
        bool1 = false; 
      classType = classType1;
      bool2 = bool1;
      if (bool1) {
        classType = classType1;
        bool2 = bool1;
        if (paramType instanceof TypeValue) {
          Expression expression = ((TypeValue)paramType).convertValue((Expression)str);
          classType = classType1;
          bool2 = bool1;
          if (expression != null)
            return expression; 
        } 
      } 
    } 
    if (bool2) {
      Language language = this.comp.getLanguage();
      this.comp.error('w', "type " + language.formatType((Type)classType) + " is incompatible with required type " + language.formatType(paramType), (SourceLocator)str);
    } 
    return (Expression)str;
  }
  
  public QuoteExp fixIntValue(Expression paramExpression) {
    Integer integer = checkIntValue(paramExpression);
    return (integer != null) ? new QuoteExp(integer, this.comp.getLanguage().getTypeFor(int.class)) : null;
  }
  
  public QuoteExp fixLongValue(Expression paramExpression) {
    Long long_ = checkLongValue(paramExpression);
    return (long_ != null) ? new QuoteExp(long_, this.comp.getLanguage().getTypeFor(long.class)) : null;
  }
  
  public Expression maybeInline(ApplyExp paramApplyExp, Type paramType, Procedure paramProcedure) {
    // Byte code:
    //   0: aload_3
    //   1: monitorenter
    //   2: aload_3
    //   3: getstatic gnu/mapping/Procedure.validateApplyKey : Lgnu/mapping/Symbol;
    //   6: aconst_null
    //   7: invokevirtual getProperty : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   10: astore #6
    //   12: aload #6
    //   14: astore #5
    //   16: aload #6
    //   18: instanceof java/lang/String
    //   21: ifeq -> 133
    //   24: aload #6
    //   26: checkcast java/lang/String
    //   29: astore #6
    //   31: aload #6
    //   33: bipush #58
    //   35: invokevirtual indexOf : (I)I
    //   38: istore #4
    //   40: aconst_null
    //   41: astore #5
    //   43: iload #4
    //   45: ifle -> 92
    //   48: aload #6
    //   50: iconst_0
    //   51: iload #4
    //   53: invokevirtual substring : (II)Ljava/lang/String;
    //   56: astore #5
    //   58: aload #6
    //   60: iload #4
    //   62: iconst_1
    //   63: iadd
    //   64: invokevirtual substring : (I)Ljava/lang/String;
    //   67: astore #6
    //   69: aload #5
    //   71: iconst_1
    //   72: aload_3
    //   73: invokevirtual getClass : ()Ljava/lang/Class;
    //   76: invokevirtual getClassLoader : ()Ljava/lang/ClassLoader;
    //   79: invokestatic forName : (Ljava/lang/String;ZLjava/lang/ClassLoader;)Ljava/lang/Class;
    //   82: aload #6
    //   84: invokestatic getInlinerMethodArgTypes : ()[Ljava/lang/Class;
    //   87: invokevirtual getDeclaredMethod : (Ljava/lang/String;[Ljava/lang/Class;)Ljava/lang/reflect/Method;
    //   90: astore #5
    //   92: aload #5
    //   94: ifnonnull -> 280
    //   97: aload_0
    //   98: bipush #101
    //   100: new java/lang/StringBuilder
    //   103: dup
    //   104: invokespecial <init> : ()V
    //   107: ldc_w 'inliner property string for '
    //   110: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   113: aload_3
    //   114: invokevirtual append : (Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   117: ldc_w ' is not of the form CLASS:METHOD'
    //   120: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   123: invokevirtual toString : ()Ljava/lang/String;
    //   126: invokevirtual error : (CLjava/lang/String;)V
    //   129: aload_3
    //   130: monitorexit
    //   131: aconst_null
    //   132: areturn
    //   133: aload_3
    //   134: monitorexit
    //   135: aload #5
    //   137: ifnull -> 253
    //   140: iconst_4
    //   141: anewarray java/lang/Object
    //   144: astore #6
    //   146: aload #6
    //   148: iconst_0
    //   149: aload_1
    //   150: aastore
    //   151: aload #6
    //   153: iconst_1
    //   154: aload_0
    //   155: aastore
    //   156: aload #6
    //   158: iconst_2
    //   159: aload_2
    //   160: aastore
    //   161: aload #6
    //   163: iconst_3
    //   164: aload_3
    //   165: aastore
    //   166: aload #5
    //   168: instanceof gnu/mapping/Procedure
    //   171: ifeq -> 255
    //   174: aload #5
    //   176: checkcast gnu/mapping/Procedure
    //   179: aload #6
    //   181: invokevirtual applyN : ([Ljava/lang/Object;)Ljava/lang/Object;
    //   184: checkcast gnu/expr/Expression
    //   187: astore_1
    //   188: aload_1
    //   189: areturn
    //   190: astore_1
    //   191: aload_3
    //   192: monitorexit
    //   193: aload_1
    //   194: athrow
    //   195: astore_2
    //   196: aload_2
    //   197: astore_1
    //   198: aload_2
    //   199: instanceof java/lang/reflect/InvocationTargetException
    //   202: ifeq -> 213
    //   205: aload_2
    //   206: checkcast java/lang/reflect/InvocationTargetException
    //   209: invokevirtual getTargetException : ()Ljava/lang/Throwable;
    //   212: astore_1
    //   213: aload_0
    //   214: getfield messages : Lgnu/text/SourceMessages;
    //   217: bipush #101
    //   219: new java/lang/StringBuilder
    //   222: dup
    //   223: invokespecial <init> : ()V
    //   226: ldc_w 'caught exception in inliner for '
    //   229: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   232: aload_3
    //   233: invokevirtual append : (Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   236: ldc_w ' - '
    //   239: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   242: aload_1
    //   243: invokevirtual append : (Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   246: invokevirtual toString : ()Ljava/lang/String;
    //   249: aload_1
    //   250: invokevirtual error : (CLjava/lang/String;Ljava/lang/Throwable;)V
    //   253: aconst_null
    //   254: areturn
    //   255: aload #5
    //   257: instanceof java/lang/reflect/Method
    //   260: ifeq -> 253
    //   263: aload #5
    //   265: checkcast java/lang/reflect/Method
    //   268: aconst_null
    //   269: aload #6
    //   271: invokevirtual invoke : (Ljava/lang/Object;[Ljava/lang/Object;)Ljava/lang/Object;
    //   274: checkcast gnu/expr/Expression
    //   277: astore_1
    //   278: aload_1
    //   279: areturn
    //   280: goto -> 133
    // Exception table:
    //   from	to	target	type
    //   0	2	195	java/lang/Throwable
    //   2	12	190	finally
    //   16	40	190	finally
    //   48	92	190	finally
    //   97	131	190	finally
    //   133	135	190	finally
    //   140	146	195	java/lang/Throwable
    //   166	188	195	java/lang/Throwable
    //   191	193	190	finally
    //   193	195	195	java/lang/Throwable
    //   255	278	195	java/lang/Throwable
  }
  
  public Expression visit(Expression paramExpression, Type paramType) {
    Expression expression = paramExpression;
    if (!paramExpression.getFlag(1)) {
      paramExpression.setFlag(1);
      expression = super.visit(paramExpression, paramType);
      expression.setFlag(1);
    } 
    return checkType(expression, paramType);
  }
  
  protected Expression visitApplyExp(ApplyExp paramApplyExp, Type paramType) {
    Expression expression = paramApplyExp.func;
    if (expression instanceof LambdaExp) {
      LambdaExp lambdaExp = (LambdaExp)expression;
      Expression expression1 = inlineCall((LambdaExp)expression, paramApplyExp.args, false);
      if (expression1 != null)
        return visit(expression1, paramType); 
    } 
    expression = visit(expression, (Type)null);
    paramApplyExp.func = expression;
    return expression.validateApply(paramApplyExp, this, paramType, null);
  }
  
  public final Expression visitApplyOnly(ApplyExp paramApplyExp, Type paramType) {
    return paramApplyExp.func.validateApply(paramApplyExp, this, paramType, null);
  }
  
  protected Expression visitBeginExp(BeginExp paramBeginExp, Type paramType) {
    int j = paramBeginExp.length - 1;
    for (int i = 0; i <= j; i++) {
      Type type;
      Expression[] arrayOfExpression = paramBeginExp.exps;
      Expression expression = paramBeginExp.exps[i];
      if (i < j) {
        type = null;
      } else {
        type = paramType;
      } 
      arrayOfExpression[i] = visit(expression, type);
    } 
    return paramBeginExp;
  }
  
  protected Expression visitIfExp(IfExp paramIfExp, Type paramType) {
    Expression expression2 = paramIfExp.test.visit(this, null);
    Expression expression1 = expression2;
    if (expression2 instanceof ReferenceExp) {
      Declaration declaration = ((ReferenceExp)expression2).getBinding();
      expression1 = expression2;
      if (declaration != null) {
        Expression expression = declaration.getValue();
        expression1 = expression2;
        if (expression instanceof QuoteExp) {
          expression1 = expression2;
          if (expression != QuoteExp.undefined_exp)
            expression1 = expression; 
        } 
      } 
    } 
    paramIfExp.test = expression1;
    if (this.exitValue == null)
      paramIfExp.then_clause = visit(paramIfExp.then_clause, paramType); 
    if (this.exitValue == null && paramIfExp.else_clause != null)
      paramIfExp.else_clause = visit(paramIfExp.else_clause, paramType); 
    if (expression1 instanceof QuoteExp)
      return paramIfExp.select(this.comp.getLanguage().isTrue(((QuoteExp)expression1).getValue())); 
    IfExp ifExp = paramIfExp;
    if (expression1.getType().isVoid()) {
      boolean bool = this.comp.getLanguage().isTrue(Values.empty);
      this.comp.error('w', "void-valued condition is always " + bool);
      return new BeginExp(expression1, paramIfExp.select(bool));
    } 
    return ifExp;
  }
  
  protected Expression visitLambdaExp(LambdaExp paramLambdaExp, Type paramType) {
    Declaration declaration = paramLambdaExp.firstDecl();
    if (declaration != null && declaration.isThisParameter() && !paramLambdaExp.isClassMethod() && declaration.type == null)
      declaration.setType((Type)this.comp.mainClass); 
    return visitScopeExp(paramLambdaExp, paramType);
  }
  
  protected Expression visitLetExp(LetExp paramLetExp, Type paramType) {
    Expression expression;
    Declaration declaration = paramLetExp.firstDecl();
    int j = paramLetExp.inits.length;
    int i = 0;
    while (i < j) {
      Expression expression2 = paramLetExp.inits[i];
      boolean bool = declaration.getFlag(8192L);
      if (bool && expression2 != QuoteExp.undefined_exp) {
        expression1 = (Expression)declaration.getType();
      } else {
        expression1 = null;
      } 
      Expression expression1 = visit(expression2, (Type)expression1);
      paramLetExp.inits[i] = expression1;
      if (declaration.value == expression2) {
        declaration.value = expression1;
        if (!bool)
          declaration.setType(expression1.getType()); 
      } 
      i++;
      declaration = declaration.nextDecl();
    } 
    if (this.exitValue == null)
      paramLetExp.body = visit(paramLetExp.body, paramType); 
    if (paramLetExp.body instanceof ReferenceExp) {
      ReferenceExp referenceExp = (ReferenceExp)paramLetExp.body;
      declaration = referenceExp.getBinding();
      if (declaration != null && declaration.context == paramLetExp && !referenceExp.getDontDereference() && j == 1) {
        Expression expression1 = paramLetExp.inits[0];
        Expression expression2 = declaration.getTypeExp();
        expression = expression1;
        if (expression2 != QuoteExp.classObjectExp)
          expression = visitApplyOnly(Compilation.makeCoercion(expression1, expression2), (Type)null); 
        return expression;
      } 
    } 
    return expression;
  }
  
  protected Expression visitQuoteExp(QuoteExp paramQuoteExp, Type paramType) {
    Object object = paramQuoteExp;
    if (paramQuoteExp.getRawType() == null) {
      object = paramQuoteExp;
      if (!paramQuoteExp.isSharedConstant()) {
        Object object1 = paramQuoteExp.getValue();
        object = paramQuoteExp;
        if (object1 != null) {
          object1 = this.comp.getLanguage().getTypeFor(object1.getClass());
          object = object1;
          if (object1 == Type.toStringType)
            object = Type.javalangStringType; 
          paramQuoteExp.type = (Type)object;
          object = paramQuoteExp;
          if (paramType instanceof gnu.bytecode.PrimType) {
            char c = paramType.getSignature().charAt(0);
            if (c == 'I') {
              QuoteExp quoteExp = fixIntValue(paramQuoteExp);
            } else if (c == 'J') {
              QuoteExp quoteExp = fixLongValue(paramQuoteExp);
            } else {
              paramType = null;
            } 
            object = paramQuoteExp;
            if (paramType != null)
              object = paramType; 
          } 
        } 
      } 
    } 
    return (Expression)object;
  }
  
  protected Expression visitReferenceExp(ReferenceExp paramReferenceExp, Type paramType) {
    Declaration declaration = paramReferenceExp.getBinding();
    if (declaration != null && declaration.field == null && !declaration.getCanWrite()) {
      Expression expression = declaration.getValue();
      if (expression instanceof QuoteExp && expression != QuoteExp.undefined_exp)
        return visitQuoteExp((QuoteExp)expression, paramType); 
      if (expression instanceof ReferenceExp && !declaration.isAlias()) {
        expression = expression;
        Declaration declaration1 = expression.getBinding();
        Type type = declaration.getType();
        if (declaration1 != null && !declaration1.getCanWrite() && (type == null || type == Type.pointer_type || type == declaration1.getType()) && !expression.getDontDereference())
          return visitReferenceExp((ReferenceExp)expression, paramType); 
      } 
      if (!paramReferenceExp.isProcedureName() && (declaration.flags & 0x100080L) == 1048704L) {
        this.comp.error('e', "unimplemented: reference to method " + declaration.getName() + " as variable");
        this.comp.error('e', declaration, "here is the definition of ", "");
      } 
    } 
    return super.visitReferenceExp(paramReferenceExp, paramType);
  }
  
  protected Expression visitScopeExp(ScopeExp paramScopeExp, Type paramType) {
    paramScopeExp.visitChildren(this, null);
    visitDeclarationTypes(paramScopeExp);
    for (Declaration declaration = paramScopeExp.firstDecl(); declaration != null; declaration = declaration.nextDecl()) {
      if (declaration.type == null) {
        ClassType classType;
        Expression expression = declaration.getValue();
        declaration.type = (Type)Type.objectType;
        if (expression != null && expression != QuoteExp.undefined_exp) {
          Type type = expression.getType();
        } else {
          classType = Type.objectType;
        } 
        declaration.setType((Type)classType);
      } 
    } 
    return paramScopeExp;
  }
  
  protected Expression visitSetExp(SetExp paramSetExp, Type paramType) {
    Declaration declaration = paramSetExp.getBinding();
    super.visitSetExp(paramSetExp, paramType);
    if (!paramSetExp.isDefining() && declaration != null && (declaration.flags & 0x100080L) == 1048704L)
      this.comp.error('e', "can't assign to method " + declaration.getName(), paramSetExp); 
    if (declaration != null && declaration.getFlag(8192L) && CompileReflect.checkKnownClass(declaration.getType(), this.comp) < 0)
      declaration.setType((Type)Type.errorType); 
    return paramSetExp;
  }
  
  protected Expression visitSetExpValue(Expression paramExpression, Type paramType, Declaration paramDeclaration) {
    if (paramDeclaration == null || paramDeclaration.isAlias()) {
      paramType = null;
      return visit(paramExpression, paramType);
    } 
    paramType = paramDeclaration.type;
    return visit(paramExpression, paramType);
  }
  
  protected Expression visitTryExp(TryExp paramTryExp, Type paramType) {
    return (paramTryExp.getCatchClauses() == null && paramTryExp.getFinallyClause() == null) ? visit(paramTryExp.try_clause, paramType) : super.visitTryExp(paramTryExp, paramType);
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/gnu/expr/InlineCalls.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */