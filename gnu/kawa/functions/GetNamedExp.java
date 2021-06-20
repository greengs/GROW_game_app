package gnu.kawa.functions;

import gnu.bytecode.Type;
import gnu.expr.ApplyExp;
import gnu.expr.Compilation;
import gnu.expr.Declaration;
import gnu.expr.Expression;
import gnu.expr.InlineCalls;
import gnu.expr.PrimProcedure;
import gnu.expr.ReferenceExp;
import gnu.mapping.CallContext;
import gnu.mapping.Environment;
import gnu.mapping.Location;
import gnu.mapping.Procedure;
import gnu.mapping.Symbol;

class GetNamedExp extends ApplyExp {
  static final Declaration castDecl;
  
  static final Declaration fieldDecl = Declaration.getDeclarationFromStatic("gnu.kawa.reflect.SlotGet", "field");
  
  static final Declaration instanceOfDecl;
  
  static final Declaration invokeDecl;
  
  static final Declaration invokeStaticDecl;
  
  static final Declaration makeDecl;
  
  static final Declaration staticFieldDecl = Declaration.getDeclarationFromStatic("gnu.kawa.reflect.SlotGet", "staticField");
  
  public String combinedName;
  
  char kind;
  
  PrimProcedure[] methods;
  
  static {
    makeDecl = Declaration.getDeclarationFromStatic("gnu.kawa.reflect.Invoke", "make");
    invokeDecl = Declaration.getDeclarationFromStatic("gnu.kawa.reflect.Invoke", "invoke");
    invokeStaticDecl = Declaration.getDeclarationFromStatic("gnu.kawa.reflect.Invoke", "invokeStatic");
    instanceOfDecl = Declaration.getDeclarationFromStatic("kawa.standard.Scheme", "instanceOf");
    castDecl = Declaration.getDeclarationFromStatic("gnu.kawa.functions.Convert", "as");
  }
  
  public GetNamedExp(Expression[] paramArrayOfExpression) {
    super((Procedure)GetNamedPart.getNamedPart, paramArrayOfExpression);
  }
  
  public void apply(CallContext paramCallContext) throws Throwable {
    if (this.combinedName != null) {
      Environment environment = Environment.getCurrent();
      Symbol symbol = environment.getSymbol(this.combinedName);
      String str = Location.UNBOUND;
      Object object = environment.get(symbol, null, str);
      if (object != str) {
        paramCallContext.writeValue(object);
        return;
      } 
    } 
    super.apply(paramCallContext);
  }
  
  protected GetNamedExp setProcedureKind(char paramChar) {
    this.type = (Type)Compilation.typeProcedure;
    this.kind = paramChar;
    return this;
  }
  
  public boolean side_effects() {
    return (this.kind == 'S' || this.kind == 'N' || this.kind == 'C' || this.kind == 'I') ? false : ((this.kind == 'M') ? getArgs()[0].side_effects() : true);
  }
  
  public Expression validateApply(ApplyExp paramApplyExp, InlineCalls paramInlineCalls, Type paramType, Declaration paramDeclaration) {
    Expression[] arrayOfExpression5;
    ApplyExp applyExp5;
    Expression[] arrayOfExpression4;
    ApplyExp applyExp4;
    Expression[] arrayOfExpression3;
    ApplyExp applyExp3;
    Expression[] arrayOfExpression2;
    ApplyExp applyExp2;
    Expression[] arrayOfExpression7 = getArgs();
    Expression expression = arrayOfExpression7[0];
    Expression[] arrayOfExpression6 = paramApplyExp.getArgs();
    switch (this.kind) {
      default:
        return (Expression)paramApplyExp;
      case 'M':
        declaration = invokeDecl;
        arrayOfExpression5 = new Expression[arrayOfExpression6.length + 2];
        arrayOfExpression5[0] = arrayOfExpression7[0];
        arrayOfExpression5[1] = arrayOfExpression7[1];
        System.arraycopy(arrayOfExpression6, 0, arrayOfExpression5, 2, arrayOfExpression6.length);
        applyExp5 = new ApplyExp((Expression)new ReferenceExp(declaration), arrayOfExpression5);
        applyExp5.setLine((Expression)paramApplyExp);
        return paramInlineCalls.visit((Expression)applyExp5, paramType);
      case 'N':
        declaration = makeDecl;
        arrayOfExpression4 = new Expression[arrayOfExpression6.length + 1];
        System.arraycopy(arrayOfExpression6, 0, arrayOfExpression4, 1, arrayOfExpression6.length);
        arrayOfExpression4[0] = expression;
        applyExp4 = new ApplyExp((Expression)new ReferenceExp(declaration), arrayOfExpression4);
        applyExp4.setLine((Expression)paramApplyExp);
        return paramInlineCalls.visit((Expression)applyExp4, paramType);
      case 'I':
        declaration = instanceOfDecl;
        arrayOfExpression3 = new Expression[arrayOfExpression6.length + 1];
        System.arraycopy(arrayOfExpression6, 1, arrayOfExpression3, 2, arrayOfExpression6.length - 1);
        arrayOfExpression3[0] = arrayOfExpression6[0];
        arrayOfExpression3[1] = expression;
        applyExp3 = new ApplyExp((Expression)new ReferenceExp(declaration), arrayOfExpression3);
        applyExp3.setLine((Expression)paramApplyExp);
        return paramInlineCalls.visit((Expression)applyExp3, paramType);
      case 'C':
        declaration = castDecl;
        arrayOfExpression2 = new Expression[arrayOfExpression6.length + 1];
        System.arraycopy(arrayOfExpression6, 1, arrayOfExpression2, 2, arrayOfExpression6.length - 1);
        arrayOfExpression2[0] = expression;
        arrayOfExpression2[1] = arrayOfExpression6[0];
        applyExp2 = new ApplyExp((Expression)new ReferenceExp(declaration), arrayOfExpression2);
        applyExp2.setLine((Expression)paramApplyExp);
        return paramInlineCalls.visit((Expression)applyExp2, paramType);
      case 'S':
        break;
    } 
    Declaration declaration = invokeStaticDecl;
    Expression[] arrayOfExpression1 = new Expression[arrayOfExpression6.length + 2];
    arrayOfExpression1[0] = expression;
    arrayOfExpression1[1] = arrayOfExpression7[1];
    System.arraycopy(arrayOfExpression6, 0, arrayOfExpression1, 2, arrayOfExpression6.length);
    ApplyExp applyExp1 = new ApplyExp((Expression)new ReferenceExp(declaration), arrayOfExpression1);
    applyExp1.setLine((Expression)paramApplyExp);
    return paramInlineCalls.visit((Expression)applyExp1, paramType);
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/gnu/kawa/functions/GetNamedExp.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */