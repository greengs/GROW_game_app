package kawa.standard;

import gnu.expr.Declaration;
import gnu.expr.Expression;
import gnu.expr.QuoteExp;
import gnu.expr.ReferenceExp;
import gnu.mapping.Environment;
import kawa.lang.Syntax;
import kawa.lang.Translator;

public class constant_fold extends Syntax {
  public static final constant_fold constant_fold = new constant_fold();
  
  static {
    constant_fold.setName("constant-fold");
  }
  
  static Object checkConstant(Expression paramExpression, Translator paramTranslator) {
    Declaration declaration;
    paramTranslator = null;
    if (paramExpression instanceof QuoteExp)
      return ((QuoteExp)paramExpression).getValue(); 
    if (paramExpression instanceof ReferenceExp) {
      ReferenceExp referenceExp = (ReferenceExp)paramExpression;
      declaration = referenceExp.getBinding();
      return (declaration == null || declaration.getFlag(65536L)) ? Environment.user().get(referenceExp.getName(), null) : Declaration.followAliases(declaration).getConstantValue();
    } 
    return declaration;
  }
  
  public Expression rewrite(Object paramObject, Translator paramTranslator) {
    // Byte code:
    //   0: aload_2
    //   1: aload_1
    //   2: invokevirtual rewrite : (Ljava/lang/Object;)Lgnu/expr/Expression;
    //   5: astore_1
    //   6: aload_1
    //   7: instanceof gnu/expr/ApplyExp
    //   10: ifne -> 15
    //   13: aload_1
    //   14: areturn
    //   15: aload_1
    //   16: checkcast gnu/expr/ApplyExp
    //   19: astore #5
    //   21: aload #5
    //   23: invokevirtual getFunction : ()Lgnu/expr/Expression;
    //   26: aload_2
    //   27: invokestatic checkConstant : (Lgnu/expr/Expression;Lkawa/lang/Translator;)Ljava/lang/Object;
    //   30: astore #4
    //   32: aload #4
    //   34: instanceof gnu/mapping/Procedure
    //   37: ifne -> 42
    //   40: aload_1
    //   41: areturn
    //   42: aload #5
    //   44: invokevirtual getArgs : ()[Lgnu/expr/Expression;
    //   47: astore #5
    //   49: aload #5
    //   51: arraylength
    //   52: istore_3
    //   53: iload_3
    //   54: anewarray java/lang/Object
    //   57: astore #6
    //   59: iload_3
    //   60: iconst_1
    //   61: isub
    //   62: istore_3
    //   63: iload_3
    //   64: iflt -> 93
    //   67: aload #5
    //   69: iload_3
    //   70: aaload
    //   71: aload_2
    //   72: invokestatic checkConstant : (Lgnu/expr/Expression;Lkawa/lang/Translator;)Ljava/lang/Object;
    //   75: astore #7
    //   77: aload #7
    //   79: ifnonnull -> 84
    //   82: aload_1
    //   83: areturn
    //   84: aload #6
    //   86: iload_3
    //   87: aload #7
    //   89: aastore
    //   90: goto -> 59
    //   93: new gnu/expr/QuoteExp
    //   96: dup
    //   97: aload #4
    //   99: checkcast gnu/mapping/Procedure
    //   102: aload #6
    //   104: invokevirtual applyN : ([Ljava/lang/Object;)Ljava/lang/Object;
    //   107: invokespecial <init> : (Ljava/lang/Object;)V
    //   110: astore_1
    //   111: aload_1
    //   112: areturn
    //   113: astore_1
    //   114: aload_2
    //   115: ldc 'caught exception in constant-fold:'
    //   117: invokevirtual syntaxError : (Ljava/lang/String;)Lgnu/expr/Expression;
    //   120: astore #4
    //   122: aload_2
    //   123: aload_1
    //   124: invokevirtual toString : ()Ljava/lang/String;
    //   127: invokevirtual syntaxError : (Ljava/lang/String;)Lgnu/expr/Expression;
    //   130: pop
    //   131: aload #4
    //   133: areturn
    // Exception table:
    //   from	to	target	type
    //   93	111	113	java/lang/Throwable
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/kawa/standard/constant_fold.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */