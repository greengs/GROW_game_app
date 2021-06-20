package kawa.standard;

import gnu.expr.Declaration;
import gnu.expr.Expression;
import gnu.expr.LetExp;
import gnu.expr.ScopeExp;
import gnu.lists.LList;
import gnu.lists.Pair;
import java.util.Stack;
import kawa.lang.Syntax;
import kawa.lang.SyntaxForm;
import kawa.lang.Translator;

public class let extends Syntax {
  public static final let let = new let();
  
  static {
    let.setName("let");
  }
  
  public Expression rewrite(Object paramObject, Translator paramTranslator) {
    if (!(paramObject instanceof Pair))
      return paramTranslator.syntaxError("missing let arguments"); 
    Object object1 = paramObject;
    Object object2 = object1.getCar();
    Object object3 = object1.getCdr();
    int k = Translator.listLength(object2);
    if (k < 0)
      return paramTranslator.syntaxError("bindings not a proper list"); 
    Expression[] arrayOfExpression = new Expression[k];
    LetExp letExp = new LetExp(arrayOfExpression);
    object1 = null;
    int i = 0;
    SyntaxForm syntaxForm = null;
    int j = 0;
    while (j < k) {
      while (object2 instanceof SyntaxForm) {
        syntaxForm = (SyntaxForm)object2;
        object2 = syntaxForm.getDatum();
      } 
      Pair pair3 = (Pair)object2;
      Object object5 = pair3.getCar();
      object2 = syntaxForm;
      Object object4 = object5;
      if (object5 instanceof SyntaxForm) {
        object2 = object5;
        object4 = object2.getDatum();
      } 
      if (!(object4 instanceof Pair))
        return paramTranslator.syntaxError("let binding is not a pair:" + object4); 
      Pair pair1 = (Pair)object4;
      object5 = pair1.getCar();
      if (object5 instanceof SyntaxForm) {
        object4 = object5;
        object5 = object4.getDatum();
        object4 = object4.getScope();
      } else if (object2 == null) {
        object4 = null;
      } else {
        object4 = object2.getScope();
      } 
      Object object8 = paramTranslator.namespaceResolve(object5);
      if (!(object8 instanceof gnu.mapping.Symbol))
        return paramTranslator.syntaxError("variable " + object8 + " in let binding is not a symbol: " + paramObject); 
      Declaration declaration = letExp.addDeclaration(object8);
      declaration.setFlag(262144L);
      object5 = object1;
      int m = i;
      if (object4 != null) {
        object5 = paramTranslator.makeRenamedAlias(declaration, (ScopeExp)object4);
        object4 = object1;
        if (object1 == null)
          object4 = new Stack(); 
        object4.push(object5);
        m = i + 1;
        object5 = object4;
      } 
      object4 = pair1.getCdr();
      object1 = object2;
      for (object2 = object4; object2 instanceof SyntaxForm; object2 = object1.getDatum())
        object1 = object2; 
      if (!(object2 instanceof Pair))
        return paramTranslator.syntaxError("let has no value for '" + object8 + "'"); 
      Pair pair2 = (Pair)object2;
      for (object4 = pair2.getCdr(); object4 instanceof SyntaxForm; object4 = object1.getDatum())
        object1 = object4; 
      object2 = pair2;
      Object object7 = object4;
      Object object6 = object1;
      if (paramTranslator.matches(pair2.getCar(), "::")) {
        if (object4 instanceof Pair) {
          pair2 = (Pair)object4;
          if (pair2.getCdr() == LList.Empty)
            return paramTranslator.syntaxError("missing type after '::' in let"); 
        } else {
          return paramTranslator.syntaxError("missing type after '::' in let");
        } 
        object2 = pair2.getCdr();
        object4 = object1;
        object1 = object2;
        while (true) {
          object2 = pair2;
          object7 = object1;
          object6 = object4;
          if (object1 instanceof SyntaxForm) {
            object4 = object1;
            object1 = object4.getDatum();
            continue;
          } 
          break;
        } 
      } 
      if (object7 != LList.Empty)
        if (object7 instanceof Pair) {
          declaration.setType(paramTranslator.exp2Type((Pair)object2));
          declaration.setFlag(8192L);
          object2 = object7;
        } else {
          return paramTranslator.syntaxError("let binding for '" + object8 + "' is improper list");
        }  
      arrayOfExpression[j] = paramTranslator.rewrite_car((Pair)object2, (SyntaxForm)object6);
      if (object2.getCdr() != LList.Empty)
        return paramTranslator.syntaxError("junk after declaration of " + object8); 
      declaration.noteValue(arrayOfExpression[j]);
      object2 = pair3.getCdr();
      j++;
      object1 = object5;
      i = m;
    } 
    j = i;
    while (true) {
      if (--j >= 0) {
        paramTranslator.pushRenamedAlias(object1.pop());
        continue;
      } 
      paramTranslator.push((ScopeExp)letExp);
      letExp.body = paramTranslator.rewrite_body(object3);
      paramTranslator.pop((ScopeExp)letExp);
      paramTranslator.popRenamedAlias(i);
      return (Expression)letExp;
    } 
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/kawa/standard/let.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */