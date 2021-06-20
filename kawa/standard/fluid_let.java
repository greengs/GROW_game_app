package kawa.standard;

import gnu.expr.Compilation;
import gnu.expr.Declaration;
import gnu.expr.Expression;
import gnu.expr.FluidLetExp;
import gnu.expr.ReferenceExp;
import gnu.expr.ScopeExp;
import gnu.lists.LList;
import gnu.lists.Pair;
import kawa.lang.Syntax;
import kawa.lang.SyntaxForm;
import kawa.lang.Translator;

public class fluid_let extends Syntax {
  public static final fluid_let fluid_let = new fluid_let();
  
  Expression defaultInit;
  
  boolean star = false;
  
  static {
    fluid_let.setName("fluid-set");
  }
  
  public fluid_let() {}
  
  public fluid_let(boolean paramBoolean, Expression paramExpression) {
    this.defaultInit = paramExpression;
  }
  
  public Expression rewrite(Object paramObject1, Object paramObject2, Translator paramTranslator) {
    int i;
    if (this.star) {
      i = 1;
    } else {
      i = LList.length(paramObject1);
    } 
    Expression[] arrayOfExpression = new Expression[i];
    FluidLetExp fluidLetExp = new FluidLetExp(arrayOfExpression);
    int j = 0;
    while (j < i) {
      Pair pair = (Pair)paramObject1;
      Object object = paramTranslator.pushPositionOf(pair);
      try {
        Object object1 = pair.getCar();
        if (object1 instanceof String || object1 instanceof gnu.mapping.Symbol) {
          paramObject1 = this.defaultInit;
        } else if (object1 instanceof Pair) {
          Pair pair1 = (Pair)object1;
          if (pair1.getCar() instanceof String || pair1.getCar() instanceof gnu.mapping.Symbol || pair1.getCar() instanceof SyntaxForm) {
            Expression expression;
            object1 = pair1.getCar();
            paramObject1 = object1;
            if (object1 instanceof SyntaxForm)
              paramObject1 = ((SyntaxForm)object1).getDatum(); 
            if (pair1.getCdr() == LList.Empty) {
              expression = this.defaultInit;
              object1 = paramObject1;
              paramObject1 = expression;
            } else {
              if (expression.getCdr() instanceof Pair) {
                object1 = expression.getCdr();
                if (object1.getCdr() != LList.Empty) {
                  paramObject1 = paramTranslator.syntaxError("bad syntax for value of " + paramObject1 + " in " + getName());
                  return (Expression)paramObject1;
                } 
              } else {
                paramObject1 = paramTranslator.syntaxError("bad syntax for value of " + paramObject1 + " in " + getName());
                return (Expression)paramObject1;
              } 
              expression = paramTranslator.rewrite(object1.getCar());
              object1 = paramObject1;
              paramObject1 = expression;
            } 
          } else {
            paramObject1 = paramTranslator.syntaxError("invalid " + getName() + " syntax");
            return (Expression)paramObject1;
          } 
        } else {
          paramObject1 = paramTranslator.syntaxError("invalid " + getName() + " syntax");
          return (Expression)paramObject1;
        } 
        Declaration declaration2 = fluidLetExp.addDeclaration(object1);
        Declaration declaration1 = paramTranslator.lexical.lookup(object1, false);
        if (declaration1 != null) {
          declaration1.maybeIndirectBinding((Compilation)paramTranslator);
          declaration2.base = declaration1;
          declaration1.setFluid(true);
          declaration1.setCanWrite(true);
        } 
        declaration2.setCanWrite(true);
        declaration2.setFluid(true);
        declaration2.setIndirectBinding(true);
        Object object2 = paramObject1;
        if (paramObject1 == null)
          object2 = new ReferenceExp(object1); 
        arrayOfExpression[j] = (Expression)object2;
        declaration2.noteValue(null);
        paramObject1 = pair.getCdr();
        paramTranslator.popPositionOf(object);
      } finally {
        paramTranslator.popPositionOf(object);
      } 
    } 
    paramTranslator.push((ScopeExp)fluidLetExp);
    if (this.star && paramObject1 != LList.Empty) {
      fluidLetExp.body = rewrite(paramObject1, paramObject2, paramTranslator);
      paramTranslator.pop((ScopeExp)fluidLetExp);
      return (Expression)fluidLetExp;
    } 
    fluidLetExp.body = paramTranslator.rewrite_body(paramObject2);
    paramTranslator.pop((ScopeExp)fluidLetExp);
    return (Expression)fluidLetExp;
  }
  
  public Expression rewrite(Object paramObject, Translator paramTranslator) {
    if (!(paramObject instanceof Pair))
      return paramTranslator.syntaxError("missing let arguments"); 
    paramObject = paramObject;
    return rewrite(paramObject.getCar(), paramObject.getCdr(), paramTranslator);
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/kawa/standard/fluid_let.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */