package gnu.expr;

import gnu.bytecode.Type;
import gnu.kawa.functions.AppendValues;
import java.util.HashSet;

public class FindTailCalls extends ExpExpVisitor<Expression> {
  public static void findTailCalls(Expression paramExpression, Compilation paramCompilation) {
    FindTailCalls findTailCalls = new FindTailCalls();
    findTailCalls.setContext(paramCompilation);
    findTailCalls.visit(paramExpression, paramExpression);
  }
  
  public void postVisitDecls(ScopeExp paramScopeExp) {
    for (Declaration declaration = paramScopeExp.firstDecl(); declaration != null; declaration = declaration.nextDecl()) {
      Expression expression = declaration.getValue();
      if (expression instanceof LambdaExp) {
        LambdaExp lambdaExp = (LambdaExp)expression;
        if (declaration.getCanRead())
          lambdaExp.setCanRead(true); 
        if (declaration.getCanCall())
          lambdaExp.setCanCall(true); 
      } 
      if (declaration.getFlag(1024L) && expression instanceof ReferenceExp) {
        Declaration declaration1 = ((ReferenceExp)expression).contextDecl();
        if (declaration1 != null && declaration1.isPrivate())
          declaration1.setFlag(524288L); 
      } 
    } 
  }
  
  protected Expression visitApplyExp(ApplyExp paramApplyExp, Expression paramExpression) {
    boolean bool;
    Compilation compilation1;
    if (paramExpression == this.currentLambda.body) {
      bool = true;
    } else {
      bool = false;
    } 
    if (bool)
      paramApplyExp.setTailCall(true); 
    paramApplyExp.context = this.currentLambda;
    Compilation compilation2 = null;
    if (paramApplyExp.func instanceof ReferenceExp) {
      Declaration declaration = Declaration.followAliases(((ReferenceExp)paramApplyExp.func).binding);
      compilation1 = compilation2;
      if (declaration != null) {
        if (!declaration.getFlag(2048L)) {
          paramApplyExp.nextCall = declaration.firstCall;
          declaration.firstCall = paramApplyExp;
        } 
        compilation1 = getCompilation();
        declaration.setCanCall();
        if (!compilation1.mustCompile)
          declaration.setCanRead(); 
        Expression expression = declaration.getValue();
        compilation1 = compilation2;
        if (expression instanceof LambdaExp)
          LambdaExp lambdaExp = (LambdaExp)expression; 
      } 
    } else if (paramApplyExp.func instanceof LambdaExp && !(paramApplyExp.func instanceof ClassExp)) {
      LambdaExp lambdaExp = (LambdaExp)paramApplyExp.func;
      visitLambdaExp(lambdaExp, false);
      lambdaExp.setCanCall(true);
    } else if (paramApplyExp.func instanceof QuoteExp && ((QuoteExp)paramApplyExp.func).getValue() == AppendValues.appendValues) {
      compilation1 = compilation2;
    } else {
      paramApplyExp.func = visitExpression(paramApplyExp.func, paramApplyExp.func);
      compilation1 = compilation2;
    } 
    if (compilation1 != null && ((LambdaExp)compilation1).returnContinuation != paramExpression && (compilation1 != this.currentLambda || !bool)) {
      if (bool) {
        if (((LambdaExp)compilation1).tailCallers == null)
          ((LambdaExp)compilation1).tailCallers = new HashSet<LambdaExp>(); 
        ((LambdaExp)compilation1).tailCallers.add(this.currentLambda);
        paramApplyExp.args = visitExps(paramApplyExp.args);
        return paramApplyExp;
      } 
      if (((LambdaExp)compilation1).returnContinuation == null) {
        ((LambdaExp)compilation1).returnContinuation = paramExpression;
        ((LambdaExp)compilation1).inlineHome = this.currentLambda;
        paramApplyExp.args = visitExps(paramApplyExp.args);
        return paramApplyExp;
      } 
      ((LambdaExp)compilation1).returnContinuation = LambdaExp.unknownContinuation;
      ((LambdaExp)compilation1).inlineHome = null;
    } 
    paramApplyExp.args = visitExps(paramApplyExp.args);
    return paramApplyExp;
  }
  
  protected Expression visitBeginExp(BeginExp paramBeginExp, Expression paramExpression) {
    int j = paramBeginExp.length - 1;
    for (int i = 0; i <= j; i++) {
      Expression expression1;
      Expression[] arrayOfExpression = paramBeginExp.exps;
      Expression expression2 = paramBeginExp.exps[i];
      if (i == j) {
        expression1 = paramExpression;
      } else {
        expression1 = paramBeginExp.exps[i];
      } 
      arrayOfExpression[i] = expression2.<Expression, Expression>visit(this, expression1);
    } 
    return paramBeginExp;
  }
  
  protected Expression visitBlockExp(BlockExp paramBlockExp, Expression paramExpression) {
    paramBlockExp.body = paramBlockExp.body.<Expression, Expression>visit(this, paramExpression);
    if (paramBlockExp.exitBody != null)
      paramBlockExp.exitBody = paramBlockExp.exitBody.<Expression, Expression>visit(this, paramBlockExp.exitBody); 
    return paramBlockExp;
  }
  
  protected Expression visitClassExp(ClassExp paramClassExp, Expression paramExpression) {
    LambdaExp lambdaExp = this.currentLambda;
    this.currentLambda = paramClassExp;
    try {
      for (paramExpression = paramClassExp.firstChild; paramExpression != null && this.exitValue == null; paramExpression = ((LambdaExp)paramExpression).nextSibling)
        visitLambdaExp((LambdaExp)paramExpression, false); 
      return paramClassExp;
    } finally {
      this.currentLambda = lambdaExp;
    } 
  }
  
  protected Expression visitExpression(Expression paramExpression1, Expression paramExpression2) {
    return super.visitExpression(paramExpression1, paramExpression1);
  }
  
  public Expression[] visitExps(Expression[] paramArrayOfExpression) {
    int j = paramArrayOfExpression.length;
    for (int i = 0; i < j; i++) {
      Expression expression = paramArrayOfExpression[i];
      paramArrayOfExpression[i] = visit(expression, expression);
    } 
    return paramArrayOfExpression;
  }
  
  protected Expression visitFluidLetExp(FluidLetExp paramFluidLetExp, Expression paramExpression) {
    for (Declaration declaration = paramFluidLetExp.firstDecl(); declaration != null; declaration = declaration.nextDecl()) {
      declaration.setCanRead(true);
      if (declaration.base != null)
        declaration.base.setCanRead(true); 
    } 
    visitLetDecls(paramFluidLetExp);
    paramFluidLetExp.body = paramFluidLetExp.body.<Expression, Expression>visit(this, paramFluidLetExp.body);
    postVisitDecls(paramFluidLetExp);
    return paramFluidLetExp;
  }
  
  protected Expression visitIfExp(IfExp paramIfExp, Expression paramExpression) {
    paramIfExp.test = paramIfExp.test.<Expression, Expression>visit(this, paramIfExp.test);
    paramIfExp.then_clause = paramIfExp.then_clause.<Expression, Expression>visit(this, paramExpression);
    Expression expression = paramIfExp.else_clause;
    if (expression != null)
      paramIfExp.else_clause = expression.<Expression, Expression>visit(this, paramExpression); 
    return paramIfExp;
  }
  
  protected Expression visitLambdaExp(LambdaExp paramLambdaExp, Expression paramExpression) {
    visitLambdaExp(paramLambdaExp, true);
    return paramLambdaExp;
  }
  
  final void visitLambdaExp(LambdaExp paramLambdaExp, boolean paramBoolean) {
    LambdaExp lambdaExp = this.currentLambda;
    this.currentLambda = paramLambdaExp;
    if (paramBoolean)
      paramLambdaExp.setCanRead(true); 
    try {
      if (paramLambdaExp.defaultArgs != null)
        paramLambdaExp.defaultArgs = visitExps(paramLambdaExp.defaultArgs); 
      if (this.exitValue == null && paramLambdaExp.body != null) {
        Expression expression1;
        Expression expression2 = paramLambdaExp.body;
        if (paramLambdaExp.getInlineOnly()) {
          expression1 = paramLambdaExp;
        } else {
          expression1 = paramLambdaExp.body;
        } 
        paramLambdaExp.body = expression2.<Expression, Expression>visit(this, expression1);
      } 
      this.currentLambda = lambdaExp;
      return;
    } finally {
      this.currentLambda = lambdaExp;
    } 
  }
  
  void visitLetDecls(LetExp paramLetExp) {
    // Byte code:
    //   0: aload_1
    //   1: invokevirtual firstDecl : ()Lgnu/expr/Declaration;
    //   4: astore #4
    //   6: aload_1
    //   7: getfield inits : [Lgnu/expr/Expression;
    //   10: arraylength
    //   11: istore_3
    //   12: iconst_0
    //   13: istore_2
    //   14: iload_2
    //   15: iload_3
    //   16: if_icmpge -> 109
    //   19: aload_0
    //   20: aload #4
    //   22: aload_1
    //   23: getfield inits : [Lgnu/expr/Expression;
    //   26: iload_2
    //   27: aaload
    //   28: invokevirtual visitSetExp : (Lgnu/expr/Declaration;Lgnu/expr/Expression;)Lgnu/expr/Expression;
    //   31: astore #6
    //   33: aload #6
    //   35: astore #5
    //   37: aload #6
    //   39: getstatic gnu/expr/QuoteExp.undefined_exp : Lgnu/expr/QuoteExp;
    //   42: if_acmpne -> 87
    //   45: aload #4
    //   47: invokevirtual getValue : ()Lgnu/expr/Expression;
    //   50: astore #7
    //   52: aload #7
    //   54: instanceof gnu/expr/LambdaExp
    //   57: ifne -> 83
    //   60: aload #6
    //   62: astore #5
    //   64: aload #7
    //   66: aload #6
    //   68: if_acmpeq -> 87
    //   71: aload #6
    //   73: astore #5
    //   75: aload #7
    //   77: instanceof gnu/expr/QuoteExp
    //   80: ifeq -> 87
    //   83: aload #7
    //   85: astore #5
    //   87: aload_1
    //   88: getfield inits : [Lgnu/expr/Expression;
    //   91: iload_2
    //   92: aload #5
    //   94: aastore
    //   95: iload_2
    //   96: iconst_1
    //   97: iadd
    //   98: istore_2
    //   99: aload #4
    //   101: invokevirtual nextDecl : ()Lgnu/expr/Declaration;
    //   104: astore #4
    //   106: goto -> 14
    //   109: return
  }
  
  protected Expression visitLetExp(LetExp paramLetExp, Expression paramExpression) {
    visitLetDecls(paramLetExp);
    paramLetExp.body = paramLetExp.body.<Expression, Expression>visit(this, paramExpression);
    postVisitDecls(paramLetExp);
    return paramLetExp;
  }
  
  protected Expression visitReferenceExp(ReferenceExp paramReferenceExp, Expression paramExpression) {
    Declaration declaration1 = Declaration.followAliases(paramReferenceExp.binding);
    if (declaration1 != null) {
      Type type = declaration1.type;
      if (type != null && type.isVoid())
        return QuoteExp.voidExp; 
      declaration1.setCanRead(true);
    } 
    Declaration declaration2 = paramReferenceExp.contextDecl();
    ReferenceExp referenceExp = paramReferenceExp;
    if (declaration2 != null) {
      declaration2.setCanRead(true);
      return paramReferenceExp;
    } 
    return referenceExp;
  }
  
  final Expression visitSetExp(Declaration paramDeclaration, Expression paramExpression) {
    if (paramDeclaration != null && paramDeclaration.getValue() == paramExpression && paramExpression instanceof LambdaExp && !(paramExpression instanceof ClassExp) && !paramDeclaration.isPublic()) {
      LambdaExp lambdaExp = (LambdaExp)paramExpression;
      visitLambdaExp(lambdaExp, false);
      return lambdaExp;
    } 
    return paramExpression.<Expression, Expression>visit(this, paramExpression);
  }
  
  protected Expression visitSetExp(SetExp paramSetExp, Expression paramExpression) {
    Declaration declaration2 = paramSetExp.binding;
    Declaration declaration1 = declaration2;
    if (declaration2 != null) {
      declaration1 = declaration2;
      if (declaration2.isAlias()) {
        if (paramSetExp.isDefining()) {
          paramSetExp.new_value = paramSetExp.new_value.<Expression, Expression>visit(this, paramSetExp.new_value);
          return paramSetExp;
        } 
        declaration1 = Declaration.followAliases(declaration2);
      } 
    } 
    declaration2 = paramSetExp.contextDecl();
    if (declaration2 != null)
      declaration2.setCanRead(true); 
    Expression expression = visitSetExp(declaration1, paramSetExp.new_value);
    if (declaration1 != null && declaration1.context instanceof LetExp && expression == declaration1.getValue() && (expression instanceof LambdaExp || expression instanceof QuoteExp))
      return QuoteExp.voidExp; 
    paramSetExp.new_value = expression;
    return paramSetExp;
  }
  
  protected Expression visitSynchronizedExp(SynchronizedExp paramSynchronizedExp, Expression paramExpression) {
    paramSynchronizedExp.object = paramSynchronizedExp.object.<Expression, Expression>visit(this, paramSynchronizedExp.object);
    paramSynchronizedExp.body = paramSynchronizedExp.body.<Expression, Expression>visit(this, paramSynchronizedExp.body);
    return paramSynchronizedExp;
  }
  
  protected Expression visitTryExp(TryExp paramTryExp, Expression paramExpression) {
    if (paramTryExp.finally_clause == null) {
      expression = paramExpression;
    } else {
      expression = paramTryExp.try_clause;
    } 
    paramTryExp.try_clause = paramTryExp.try_clause.<Expression, Expression>visit(this, expression);
    for (Expression expression = paramTryExp.catch_clauses; this.exitValue == null && expression != null; expression = expression.getNext()) {
      Expression expression1;
      if (paramTryExp.finally_clause == null) {
        expression1 = paramExpression;
      } else {
        expression1 = ((CatchClause)expression).body;
      } 
      ((CatchClause)expression).body = ((CatchClause)expression).body.<Expression, Expression>visit(this, expression1);
    } 
    paramExpression = paramTryExp.finally_clause;
    if (paramExpression != null)
      paramTryExp.finally_clause = paramExpression.<Expression, Expression>visit(this, paramExpression); 
    return paramTryExp;
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/gnu/expr/FindTailCalls.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */