package gnu.commonlisp.lang;

import gnu.expr.Declaration;
import gnu.expr.Expression;
import gnu.expr.LambdaExp;
import gnu.expr.ScopeExp;
import gnu.expr.SetExp;
import gnu.lists.Pair;
import gnu.text.SourceLocator;
import java.util.Vector;
import kawa.lang.Lambda;
import kawa.lang.Syntax;
import kawa.lang.Translator;

public class defun extends Syntax {
  Lambda lambdaSyntax;
  
  public defun(Lambda paramLambda) {
    this.lambdaSyntax = paramLambda;
  }
  
  public Expression rewriteForm(Pair paramPair, Translator paramTranslator) {
    Object object1;
    Object object2 = paramPair.getCdr();
    paramPair = null;
    Declaration declaration = null;
    if (object2 instanceof Pair) {
      Object object3;
      object2 = object2;
      Object object4 = object2.getCar();
      if (object4 instanceof gnu.mapping.Symbol || object4 instanceof String) {
        object3 = object4.toString();
      } else if (object4 instanceof Declaration) {
        declaration = (Declaration)object4;
        object3 = declaration.getSymbol();
      } 
      if (object3 != null && object2.getCdr() instanceof Pair) {
        object4 = object2.getCdr();
        object2 = new LambdaExp();
        this.lambdaSyntax.rewrite((LambdaExp)object2, object4.getCar(), object4.getCdr(), paramTranslator, null);
        object2.setSymbol(object3);
        if (object4 instanceof gnu.lists.PairWithPosition)
          object2.setLocation((SourceLocator)object4); 
        object1 = object2;
        object2 = new SetExp(object3, (Expression)object1);
        object2.setDefining(true);
        object2.setFuncDef(true);
        if (declaration != null) {
          object2.setBinding(declaration);
          object3 = object1;
          if (declaration.context instanceof gnu.expr.ModuleExp) {
            object3 = object1;
            if (declaration.getCanWrite())
              object3 = null; 
          } 
          declaration.noteValue((Expression)object3);
        } 
        return (Expression)object2;
      } 
    } 
    return object1.syntaxError("invalid syntax for " + getName());
  }
  
  public boolean scanForDefinitions(Pair paramPair, Vector<Pair> paramVector, ScopeExp paramScopeExp, Translator paramTranslator) {
    Declaration declaration;
    if (paramPair.getCdr() instanceof Pair) {
      Pair pair = (Pair)paramPair.getCdr();
      if (pair.getCar() instanceof String || pair.getCar() instanceof gnu.mapping.Symbol) {
        Object object = pair.getCar();
        Declaration declaration1 = paramScopeExp.lookup(object);
        if (declaration1 == null) {
          declaration = new Declaration(object);
          declaration.setProcedureDecl(true);
          paramScopeExp.addDeclaration(declaration);
        } else {
          declaration.error('w', "duplicate declaration for `" + object + "'");
          declaration = declaration1;
        } 
        if (paramScopeExp instanceof gnu.expr.ModuleExp)
          declaration.setCanRead(true); 
        paramVector.addElement(Translator.makePair(paramPair, this, Translator.makePair(pair, declaration, pair.getCdr())));
        return true;
      } 
    } 
    return super.scanForDefinitions(paramPair, paramVector, paramScopeExp, (Translator)declaration);
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/gnu/commonlisp/lang/defun.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */