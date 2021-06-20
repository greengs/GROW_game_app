package gnu.commonlisp.lang;

import gnu.expr.Declaration;
import gnu.expr.Expression;
import gnu.expr.QuoteExp;
import gnu.expr.ScopeExp;
import gnu.expr.SetExp;
import gnu.lists.LList;
import gnu.lists.Pair;
import java.util.Vector;
import kawa.lang.Syntax;
import kawa.lang.Translator;

public class defvar extends Syntax {
  boolean force;
  
  public defvar(boolean paramBoolean) {
    this.force = paramBoolean;
  }
  
  public Expression rewriteForm(Pair paramPair, Translator paramTranslator) {
    Expression expression;
    Object object2 = paramPair.getCdr();
    Pair pair4 = null;
    Pair pair3 = null;
    Declaration declaration2 = null;
    Declaration declaration1 = declaration2;
    paramPair = pair4;
    Pair pair2 = pair3;
    if (object2 instanceof Pair) {
      object2 = object2;
      declaration1 = declaration2;
      paramPair = pair4;
      pair2 = pair3;
      if (object2.getCar() instanceof Declaration) {
        Declaration declaration = (Declaration)object2.getCar();
        object1 = declaration.getSymbol();
        if (object2.getCdr() instanceof Pair) {
          pair3 = (Pair)object2.getCdr();
          Expression expression1 = paramTranslator.rewrite(pair3.getCar());
          declaration1 = declaration;
          if (pair3.getCdr() != LList.Empty);
        } else {
          declaration1 = declaration;
          pair2 = pair3;
          if (object2.getCdr() != LList.Empty) {
            object1 = null;
            declaration1 = declaration;
            pair2 = pair3;
          } 
        } 
      } 
    } 
    if (object1 == null)
      return paramTranslator.syntaxError("invalid syntax for " + getName()); 
    Pair pair1 = pair2;
    if (pair2 == null)
      if (this.force) {
        expression = CommonLisp.nilExpr;
      } else {
        return (Expression)new QuoteExp(object1);
      }  
    SetExp setExp = new SetExp(object1, expression);
    if (!this.force)
      setExp.setSetIfUnbound(true); 
    setExp.setDefining(true);
    Object object1 = setExp;
    if (declaration1 != null) {
      setExp.setBinding(declaration1);
      object1 = expression;
      if (declaration1.context instanceof gnu.expr.ModuleExp) {
        object1 = expression;
        if (declaration1.getCanWrite())
          object1 = null; 
      } 
      declaration1.noteValue((Expression)object1);
      return (Expression)setExp;
    } 
    return (Expression)object1;
  }
  
  public boolean scanForDefinitions(Pair paramPair, Vector<Pair> paramVector, ScopeExp paramScopeExp, Translator paramTranslator) {
    // Byte code:
    //   0: aload_1
    //   1: invokevirtual getCdr : ()Ljava/lang/Object;
    //   4: instanceof gnu/lists/Pair
    //   7: ifne -> 20
    //   10: aload_0
    //   11: aload_1
    //   12: aload_2
    //   13: aload_3
    //   14: aload #4
    //   16: invokespecial scanForDefinitions : (Lgnu/lists/Pair;Ljava/util/Vector;Lgnu/expr/ScopeExp;Lkawa/lang/Translator;)Z
    //   19: ireturn
    //   20: aload_1
    //   21: invokevirtual getCdr : ()Ljava/lang/Object;
    //   24: checkcast gnu/lists/Pair
    //   27: astore #6
    //   29: aload #6
    //   31: invokevirtual getCar : ()Ljava/lang/Object;
    //   34: astore #7
    //   36: aload #7
    //   38: instanceof java/lang/String
    //   41: ifne -> 55
    //   44: aload_1
    //   45: astore #5
    //   47: aload #7
    //   49: instanceof gnu/mapping/Symbol
    //   52: ifeq -> 136
    //   55: aload_3
    //   56: aload #7
    //   58: invokevirtual lookup : (Ljava/lang/Object;)Lgnu/expr/Declaration;
    //   61: astore #5
    //   63: aload #5
    //   65: ifnonnull -> 144
    //   68: new gnu/expr/Declaration
    //   71: dup
    //   72: aload #7
    //   74: invokespecial <init> : (Ljava/lang/Object;)V
    //   77: astore #4
    //   79: aload #4
    //   81: ldc2_w 268435456
    //   84: invokevirtual setFlag : (J)V
    //   87: aload_3
    //   88: aload #4
    //   90: invokevirtual addDeclaration : (Lgnu/expr/Declaration;)V
    //   93: aload_1
    //   94: aload_0
    //   95: aload #6
    //   97: aload #4
    //   99: aload #6
    //   101: invokevirtual getCdr : ()Ljava/lang/Object;
    //   104: invokestatic makePair : (Lgnu/lists/Pair;Ljava/lang/Object;Ljava/lang/Object;)Lgnu/lists/Pair;
    //   107: invokestatic makePair : (Lgnu/lists/Pair;Ljava/lang/Object;Ljava/lang/Object;)Lgnu/lists/Pair;
    //   110: astore_1
    //   111: aload_1
    //   112: astore #5
    //   114: aload_3
    //   115: instanceof gnu/expr/ModuleExp
    //   118: ifeq -> 136
    //   121: aload #4
    //   123: iconst_1
    //   124: invokevirtual setCanRead : (Z)V
    //   127: aload #4
    //   129: iconst_1
    //   130: invokevirtual setCanWrite : (Z)V
    //   133: aload_1
    //   134: astore #5
    //   136: aload_2
    //   137: aload #5
    //   139: invokevirtual addElement : (Ljava/lang/Object;)V
    //   142: iconst_1
    //   143: ireturn
    //   144: aload #4
    //   146: bipush #119
    //   148: new java/lang/StringBuilder
    //   151: dup
    //   152: invokespecial <init> : ()V
    //   155: ldc 'duplicate declaration for `'
    //   157: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   160: aload #7
    //   162: invokevirtual append : (Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   165: ldc '''
    //   167: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   170: invokevirtual toString : ()Ljava/lang/String;
    //   173: invokevirtual error : (CLjava/lang/String;)V
    //   176: aload #5
    //   178: astore #4
    //   180: goto -> 93
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/gnu/commonlisp/lang/defvar.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */