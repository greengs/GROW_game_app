package kawa.standard;

import gnu.expr.Declaration;
import gnu.expr.Expression;
import gnu.expr.LambdaExp;
import gnu.expr.LetExp;
import gnu.expr.QuoteExp;
import gnu.expr.ScopeExp;
import gnu.lists.LList;
import gnu.lists.Pair;
import java.util.Stack;
import kawa.lang.Macro;
import kawa.lang.Syntax;
import kawa.lang.SyntaxForm;
import kawa.lang.Translator;

public class let_syntax extends Syntax {
  public static final let_syntax let_syntax = new let_syntax(false, "let-syntax");
  
  public static final let_syntax letrec_syntax = new let_syntax(true, "letrec-syntax");
  
  boolean recursive;
  
  public let_syntax(boolean paramBoolean, String paramString) {
    super(paramString);
    this.recursive = paramBoolean;
  }
  
  private void push(LetExp paramLetExp, Translator paramTranslator, Stack<Declaration> paramStack) {
    paramTranslator.push((ScopeExp)paramLetExp);
    if (paramStack != null) {
      int i = paramStack.size();
      while (true) {
        if (--i >= 0) {
          paramTranslator.pushRenamedAlias(paramStack.pop());
          continue;
        } 
        break;
      } 
    } 
  }
  
  public Expression rewrite(Object paramObject, Translator paramTranslator) {
    if (!(paramObject instanceof Pair))
      return paramTranslator.syntaxError("missing let-syntax arguments"); 
    Object object1 = paramObject;
    paramObject = object1.getCar();
    Object object2 = object1.getCdr();
    int k = Translator.listLength(paramObject);
    if (k < 0)
      return paramTranslator.syntaxError("bindings not a proper list"); 
    object1 = null;
    int i = 0;
    Expression[] arrayOfExpression = new Expression[k];
    Declaration[] arrayOfDeclaration = new Declaration[k];
    Macro[] arrayOfMacro = new Macro[k];
    Pair[] arrayOfPair = new Pair[k];
    SyntaxForm[] arrayOfSyntaxForm = new SyntaxForm[k];
    LetExp letExp = new LetExp(arrayOfExpression);
    SyntaxForm syntaxForm = null;
    int j = 0;
    while (j < k) {
      Object object3;
      for (object3 = paramObject; object3 instanceof SyntaxForm; object3 = syntaxForm.getDatum())
        syntaxForm = (SyntaxForm)object3; 
      paramObject = syntaxForm;
      Pair pair = (Pair)object3;
      Object object4 = pair.getCar();
      object3 = object4;
      if (object4 instanceof SyntaxForm) {
        paramObject = object4;
        object3 = paramObject.getDatum();
      } 
      if (!(object3 instanceof Pair))
        return paramTranslator.syntaxError(getName() + " binding is not a pair"); 
      object3 = object3;
      Object object5 = object3.getCar();
      object4 = paramObject;
      while (object5 instanceof SyntaxForm) {
        object4 = object5;
        object5 = object4.getDatum();
      } 
      if (!(object5 instanceof String) && !(object5 instanceof gnu.mapping.Symbol))
        return paramTranslator.syntaxError("variable in " + getName() + " binding is not a symbol"); 
      Object object6 = object3.getCdr();
      object3 = paramObject;
      while (object6 instanceof SyntaxForm) {
        object3 = object6;
        object6 = object3.getDatum();
      } 
      if (!(object6 instanceof Pair))
        return paramTranslator.syntaxError(getName() + " has no value for '" + object5 + "'"); 
      paramObject = object6;
      if (paramObject.getCdr() != LList.Empty)
        return paramTranslator.syntaxError("let binding for '" + object5 + "' is improper list"); 
      object5 = new Declaration(object5);
      object6 = Macro.make((Declaration)object5);
      arrayOfMacro[j] = (Macro)object6;
      arrayOfPair[j] = (Pair)paramObject;
      arrayOfSyntaxForm[j] = (SyntaxForm)object3;
      letExp.addDeclaration((Declaration)object5);
      if (object4 == null) {
        paramObject = null;
      } else {
        paramObject = object4.getScope();
      } 
      object4 = object1;
      int m = i;
      if (paramObject != null) {
        object4 = paramTranslator.makeRenamedAlias((Declaration)object5, (ScopeExp)paramObject);
        paramObject = object1;
        if (object1 == null)
          paramObject = new Stack(); 
        paramObject.push(object4);
        m = i + 1;
        object4 = paramObject;
      } 
      if (object3 != null) {
        paramObject = object3.getScope();
      } else if (this.recursive) {
        paramObject = letExp;
      } else {
        paramObject = paramTranslator.currentScope();
      } 
      object6.setCapturedScope((ScopeExp)paramObject);
      arrayOfDeclaration[j] = (Declaration)object5;
      arrayOfExpression[j] = (Expression)QuoteExp.nullExp;
      paramObject = pair.getCdr();
      j++;
      object1 = object4;
      i = m;
    } 
    if (this.recursive)
      push(letExp, paramTranslator, (Stack)object1); 
    paramObject = paramTranslator.currentMacroDefinition;
    for (j = 0; j < k; j++) {
      Macro macro = arrayOfMacro[j];
      paramTranslator.currentMacroDefinition = macro;
      Expression expression = paramTranslator.rewrite_car(arrayOfPair[j], arrayOfSyntaxForm[j]);
      arrayOfExpression[j] = expression;
      Declaration declaration = arrayOfDeclaration[j];
      macro.expander = expression;
      declaration.noteValue((Expression)new QuoteExp(macro));
      if (expression instanceof LambdaExp) {
        LambdaExp lambdaExp = (LambdaExp)expression;
        lambdaExp.nameDecl = declaration;
        lambdaExp.setSymbol(declaration.getSymbol());
      } 
    } 
    paramTranslator.currentMacroDefinition = (Macro)paramObject;
    if (!this.recursive)
      push(letExp, paramTranslator, (Stack)object1); 
    paramObject = paramTranslator.rewrite_body(object2);
    paramTranslator.pop((ScopeExp)letExp);
    paramTranslator.popRenamedAlias(i);
    return (Expression)paramObject;
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/kawa/standard/let_syntax.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */