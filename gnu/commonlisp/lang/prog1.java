package gnu.commonlisp.lang;

import gnu.expr.BeginExp;
import gnu.expr.Expression;
import gnu.expr.LetExp;
import gnu.expr.ReferenceExp;
import gnu.lists.LList;
import kawa.lang.Syntax;
import kawa.lang.Translator;

public class prog1 extends Syntax {
  public static final prog1 prog1 = new prog1("prog1", 1);
  
  public static final prog1 prog2 = new prog1("prog2", 2);
  
  int index;
  
  public prog1(String paramString, int paramInt) {
    this.index = paramInt;
    setName(paramString);
  }
  
  public Expression rewrite(Object paramObject, Translator paramTranslator) {
    int j = LList.length(paramObject);
    if (j < this.index)
      return paramTranslator.syntaxError("too few expressions in " + getName()); 
    if (this.index == 2) {
      paramObject = paramObject;
      return (Expression)new BeginExp(paramTranslator.rewrite(paramObject.getCar()), prog1.rewrite(paramObject.getCdr(), paramTranslator));
    } 
    Expression[] arrayOfExpression2 = new Expression[1];
    LetExp letExp = new LetExp(arrayOfExpression2);
    Expression[] arrayOfExpression1 = new Expression[j];
    paramObject = paramObject;
    arrayOfExpression2[0] = paramTranslator.rewrite(paramObject.getCar());
    paramObject = paramObject.getCdr();
    for (int i = 0; i < j - 1; i++) {
      paramObject = paramObject;
      arrayOfExpression1[i] = paramTranslator.rewrite(paramObject.getCar());
      paramObject = paramObject.getCdr();
    } 
    arrayOfExpression1[j - 1] = (Expression)new ReferenceExp(letExp.addDeclaration(null));
    letExp.body = BeginExp.canonicalize(arrayOfExpression1);
    paramTranslator.mustCompileHere();
    return (Expression)letExp;
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/gnu/commonlisp/lang/prog1.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */