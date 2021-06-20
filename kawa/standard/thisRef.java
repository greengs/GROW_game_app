package kawa.standard;

import gnu.expr.Expression;
import gnu.lists.Pair;
import kawa.lang.Syntax;
import kawa.lang.Translator;

public class thisRef extends Syntax {
  public static final thisRef thisSyntax = new thisRef();
  
  static {
    thisSyntax.setName("this");
  }
  
  public Expression rewriteForm(Pair paramPair, Translator paramTranslator) {
    // Byte code:
    //   0: aload_1
    //   1: invokevirtual getCdr : ()Ljava/lang/Object;
    //   4: getstatic gnu/lists/LList.Empty : Lgnu/lists/LList;
    //   7: if_acmpne -> 131
    //   10: aload_2
    //   11: getfield curMethodLambda : Lgnu/expr/LambdaExp;
    //   14: astore #4
    //   16: aload #4
    //   18: ifnonnull -> 68
    //   21: aconst_null
    //   22: astore_1
    //   23: aload_1
    //   24: ifnull -> 36
    //   27: aload_1
    //   28: astore_3
    //   29: aload_1
    //   30: invokevirtual isThisParameter : ()Z
    //   33: ifne -> 59
    //   36: aconst_null
    //   37: astore_3
    //   38: aload #4
    //   40: ifnull -> 51
    //   43: aload #4
    //   45: getfield nameDecl : Lgnu/expr/Declaration;
    //   48: ifnonnull -> 77
    //   51: aload_2
    //   52: bipush #101
    //   54: ldc 'use of 'this' not in a named method'
    //   56: invokevirtual error : (CLjava/lang/String;)V
    //   59: new gnu/expr/ThisExp
    //   62: dup
    //   63: aload_3
    //   64: invokespecial <init> : (Lgnu/expr/Declaration;)V
    //   67: areturn
    //   68: aload #4
    //   70: invokevirtual firstDecl : ()Lgnu/expr/Declaration;
    //   73: astore_1
    //   74: goto -> 23
    //   77: aload #4
    //   79: getfield nameDecl : Lgnu/expr/Declaration;
    //   82: invokevirtual isStatic : ()Z
    //   85: ifeq -> 99
    //   88: aload_2
    //   89: bipush #101
    //   91: ldc 'use of 'this' in a static method'
    //   93: invokevirtual error : (CLjava/lang/String;)V
    //   96: goto -> 59
    //   99: new gnu/expr/Declaration
    //   102: dup
    //   103: getstatic gnu/expr/ThisExp.THIS_NAME : Ljava/lang/String;
    //   106: invokespecial <init> : (Ljava/lang/Object;)V
    //   109: astore_3
    //   110: aload #4
    //   112: aconst_null
    //   113: aload_3
    //   114: invokevirtual add : (Lgnu/expr/Declaration;Lgnu/expr/Declaration;)V
    //   117: aload #4
    //   119: getfield nameDecl : Lgnu/expr/Declaration;
    //   122: ldc2_w 4096
    //   125: invokevirtual setFlag : (J)V
    //   128: goto -> 59
    //   131: aload_2
    //   132: ldc 'this with parameter not implemented'
    //   134: invokevirtual syntaxError : (Ljava/lang/String;)Lgnu/expr/Expression;
    //   137: areturn
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/kawa/standard/thisRef.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */