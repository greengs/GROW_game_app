package kawa.standard;

import gnu.bytecode.ClassType;
import gnu.bytecode.Type;
import gnu.expr.Declaration;
import gnu.expr.Expression;
import gnu.expr.ScopeExp;
import gnu.expr.SetExp;
import gnu.lists.LList;
import gnu.lists.Pair;
import java.util.Vector;
import kawa.lang.Syntax;
import kawa.lang.SyntaxForm;
import kawa.lang.Translator;

public class define_alias extends Syntax {
  public static final define_alias define_alias = new define_alias();
  
  static {
    define_alias.setName("define-alias");
  }
  
  public Expression rewrite(Object paramObject, Translator paramTranslator) {
    return paramTranslator.syntaxError("define-alias is only allowed in a <body>");
  }
  
  public boolean scanForDefinitions(Pair paramPair, Vector<Object> paramVector, ScopeExp paramScopeExp, Translator paramTranslator) {
    SyntaxForm syntaxForm;
    Object object = paramPair.getCdr();
    paramPair = null;
    while (object instanceof SyntaxForm) {
      syntaxForm = (SyntaxForm)object;
      object = syntaxForm.getDatum();
    } 
    if (object instanceof Pair) {
      Pair pair = (Pair)object;
      SyntaxForm syntaxForm1 = syntaxForm;
      for (object = pair.getCar(); object instanceof SyntaxForm; object = syntaxForm1.getDatum())
        syntaxForm1 = (SyntaxForm)object; 
      Object object1;
      for (object1 = pair.getCdr(); object1 instanceof SyntaxForm; object1 = syntaxForm.getDatum())
        syntaxForm = (SyntaxForm)object1; 
      if ((object instanceof String || object instanceof gnu.mapping.Symbol) && object1 instanceof Pair) {
        object1 = object1;
        if (object1.getCdr() == LList.Empty) {
          Declaration declaration = paramTranslator.define(object, syntaxForm1, paramScopeExp);
          declaration.setIndirectBinding(true);
          declaration.setAlias(true);
          Expression expression = paramTranslator.rewrite_car((Pair)object1, syntaxForm);
          if (expression instanceof gnu.expr.ReferenceExp) {
            object = expression;
            Declaration declaration1 = Declaration.followAliases(object.getBinding());
            if (declaration1 != null) {
              Expression expression1 = declaration1.getValue();
              if (expression1 instanceof gnu.expr.ClassExp || expression1 instanceof gnu.expr.ModuleExp) {
                declaration.setIndirectBinding(false);
                declaration.setFlag(16384L);
                paramTranslator.mustCompileHere();
                paramTranslator.push(declaration);
                object = new SetExp(declaration, expression);
                paramTranslator.setLineOf((Expression)object);
                declaration.noteValue(expression);
                object.setDefining(true);
                paramVector.addElement(object);
                return true;
              } 
            } 
            object.setDontDereference(true);
            paramTranslator.mustCompileHere();
            paramTranslator.push(declaration);
            object = new SetExp(declaration, expression);
            paramTranslator.setLineOf((Expression)object);
            declaration.noteValue(expression);
            object.setDefining(true);
            paramVector.addElement(object);
            return true;
          } 
          if (expression instanceof gnu.expr.QuoteExp) {
            declaration.setIndirectBinding(false);
            declaration.setFlag(16384L);
            paramTranslator.mustCompileHere();
            paramTranslator.push(declaration);
            object = new SetExp(declaration, expression);
            paramTranslator.setLineOf((Expression)object);
            declaration.noteValue(expression);
            object.setDefining(true);
            paramVector.addElement(object);
            return true;
          } 
          expression = location.rewrite(expression, paramTranslator);
          declaration.setType((Type)ClassType.make("gnu.mapping.Location"));
          paramTranslator.mustCompileHere();
          paramTranslator.push(declaration);
          object = new SetExp(declaration, expression);
          paramTranslator.setLineOf((Expression)object);
          declaration.noteValue(expression);
          object.setDefining(true);
          paramVector.addElement(object);
          return true;
        } 
      } 
    } 
    paramTranslator.error('e', "invalid syntax for define-alias");
    return false;
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/kawa/standard/define_alias.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */