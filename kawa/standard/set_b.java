package kawa.standard;

import gnu.expr.ApplyExp;
import gnu.expr.Declaration;
import gnu.expr.Expression;
import gnu.expr.ReferenceExp;
import gnu.expr.SetExp;
import gnu.kawa.functions.CompilationHelpers;
import gnu.lists.LList;
import gnu.lists.Pair;
import kawa.lang.Syntax;
import kawa.lang.SyntaxForm;
import kawa.lang.Translator;

public class set_b extends Syntax {
  public static final set_b set = new set_b();
  
  static {
    set.setName("set!");
  }
  
  public Expression rewriteForm(Pair paramPair, Translator paramTranslator) {
    SyntaxForm syntaxForm;
    Expression[] arrayOfExpression;
    Object object2 = paramPair.getCdr();
    paramPair = null;
    while (object2 instanceof SyntaxForm) {
      syntaxForm = (SyntaxForm)object2;
      object2 = syntaxForm.getDatum();
    } 
    if (!(object2 instanceof Pair))
      return paramTranslator.syntaxError("missing name"); 
    object2 = object2;
    Expression expression2 = paramTranslator.rewrite_car((Pair)object2, (SyntaxForm)paramPair);
    for (object2 = object2.getCdr(); object2 instanceof SyntaxForm; object2 = syntaxForm.getDatum())
      syntaxForm = (SyntaxForm)object2; 
    if (object2 instanceof Pair) {
      object2 = object2;
      if (object2.getCdr() != LList.Empty)
        return paramTranslator.syntaxError("missing or extra arguments to set!"); 
    } else {
      return paramTranslator.syntaxError("missing or extra arguments to set!");
    } 
    Expression expression1 = paramTranslator.rewrite_car((Pair)object2, syntaxForm);
    if (expression2 instanceof ApplyExp) {
      ApplyExp applyExp = (ApplyExp)expression2;
      object2 = applyExp.getArgs();
      int j = object2.length;
      boolean bool2 = false;
      Expression expression = applyExp.getFunction();
      Object object = expression;
      int i = j;
      boolean bool1 = bool2;
      if (object2.length > 0) {
        object = expression;
        i = j;
        bool1 = bool2;
        if (expression instanceof ReferenceExp) {
          object = expression;
          i = j;
          bool1 = bool2;
          if (((ReferenceExp)expression).getBinding() == Scheme.applyFieldDecl) {
            bool1 = true;
            i = j - 1;
            object = object2[0];
          } 
        } 
      } 
      arrayOfExpression = new Expression[i + 1];
      System.arraycopy(object2, bool1, arrayOfExpression, 0, i);
      arrayOfExpression[i] = expression1;
      return (Expression)new ApplyExp((Expression)new ApplyExp((Expression)new ReferenceExp(CompilationHelpers.setterDecl), new Expression[] { (Expression)object }), arrayOfExpression);
    } 
    if (!(expression2 instanceof ReferenceExp))
      return arrayOfExpression.syntaxError("first set! argument is not a variable name"); 
    ReferenceExp referenceExp = (ReferenceExp)expression2;
    Declaration declaration = referenceExp.getBinding();
    object2 = new SetExp(referenceExp.getSymbol(), expression1);
    object2.setContextDecl(referenceExp.contextDecl());
    Object object1 = object2;
    if (declaration != null) {
      declaration.setCanWrite(true);
      object2.setBinding(declaration);
      declaration = Declaration.followAliases(declaration);
      if (declaration != null)
        declaration.noteValue(expression1); 
      if (declaration.getFlag(16384L))
        return arrayOfExpression.syntaxError("constant variable " + declaration.getName() + " is set!"); 
      object1 = object2;
      if (declaration.context != ((Translator)arrayOfExpression).mainLambda) {
        object1 = object2;
        if (declaration.context instanceof gnu.expr.ModuleExp) {
          object1 = object2;
          if (!declaration.getFlag(268435456L)) {
            object1 = object2;
            if (!declaration.context.getFlag(1048576)) {
              arrayOfExpression.error('w', declaration, "imported variable ", " is set!");
              return (Expression)object2;
            } 
          } 
        } 
      } 
    } 
    return (Expression)object1;
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/kawa/standard/set_b.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */