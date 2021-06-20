package kawa.standard;

import gnu.bytecode.ClassType;
import gnu.bytecode.Type;
import gnu.expr.Compilation;
import gnu.expr.Declaration;
import gnu.expr.Expression;
import gnu.expr.QuoteExp;
import gnu.expr.ScopeExp;
import gnu.kawa.reflect.Invoke;
import gnu.lists.LList;
import gnu.lists.Pair;
import java.util.Vector;
import kawa.lang.Syntax;
import kawa.lang.Translator;

public class define_member_alias extends Syntax {
  public static final define_member_alias define_member_alias = new define_member_alias();
  
  static {
    define_member_alias.setName("define-member-alias");
  }
  
  public Expression rewriteForm(Pair paramPair, Translator paramTranslator) {
    Object object = paramPair.getCdr();
    if (object instanceof Pair) {
      object = object;
      if (object.getCar() instanceof String || object.getCar() instanceof Declaration) {
        if (object.getCdr() instanceof Pair) {
          Object object1 = object.getCar();
          if (object1 instanceof Declaration) {
            object1 = ((Declaration)object1).getName();
          } else {
            object1 = object1;
          } 
          object = object.getCdr();
          Object object2 = null;
          Expression expression = paramTranslator.rewrite(object.getCar());
          Object object3 = object.getCdr();
          if (object3 == LList.Empty) {
            object = new QuoteExp(Compilation.mangleName((String)object1));
          } else {
            object = object2;
            if (object3 instanceof Pair) {
              object3 = object3;
              object = object2;
              if (object3.getCdr() == LList.Empty)
                object = paramTranslator.rewrite(object3.getCar()); 
            } 
          } 
          if (object != null)
            return (Expression)Invoke.makeInvokeStatic(ClassType.make("gnu.kawa.reflect.ClassMemberConstraint"), "define", new Expression[] { (Expression)new QuoteExp(object1), expression, (Expression)object }); 
        } 
        return paramTranslator.syntaxError("invalid syntax for " + getName());
      } 
    } 
    return paramTranslator.syntaxError("missing name in " + getName());
  }
  
  public boolean scanForDefinitions(Pair paramPair, Vector<Pair> paramVector, ScopeExp paramScopeExp, Translator paramTranslator) {
    Declaration declaration;
    if (paramPair.getCdr() instanceof Pair && !(paramTranslator.currentScope() instanceof gnu.expr.ModuleExp)) {
      Pair pair = (Pair)paramPair.getCdr();
      if (pair.getCar() instanceof String) {
        declaration = paramScopeExp.addDeclaration(pair.getCar(), (Type)Compilation.typeSymbol);
        declaration.setIndirectBinding(true);
        paramVector.addElement(Translator.makePair(paramPair, this, Translator.makePair(pair, declaration, pair.getCdr())));
        return true;
      } 
    } 
    return super.scanForDefinitions(paramPair, paramVector, (ScopeExp)declaration, paramTranslator);
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/kawa/standard/define_member_alias.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */