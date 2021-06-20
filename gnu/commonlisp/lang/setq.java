package gnu.commonlisp.lang;

import gnu.expr.Expression;
import gnu.lists.Pair;
import kawa.lang.Syntax;
import kawa.lang.Translator;

public class setq extends Syntax {
  public Expression rewriteForm(Pair paramPair, Translator paramTranslator) {
    // Byte code:
    //   0: aload_1
    //   1: invokevirtual getCdr : ()Ljava/lang/Object;
    //   4: astore #4
    //   6: aconst_null
    //   7: astore_1
    //   8: aload #4
    //   10: getstatic gnu/lists/LList.Empty : Lgnu/lists/LList;
    //   13: if_acmpeq -> 194
    //   16: aload #4
    //   18: instanceof gnu/lists/Pair
    //   21: ifne -> 33
    //   24: aload_2
    //   25: ldc 'invalid syntax for setq'
    //   27: invokevirtual syntaxError : (Ljava/lang/String;)Lgnu/expr/Expression;
    //   30: astore_3
    //   31: aload_3
    //   32: areturn
    //   33: aload #4
    //   35: checkcast gnu/lists/Pair
    //   38: astore #4
    //   40: aload #4
    //   42: invokevirtual getCar : ()Ljava/lang/Object;
    //   45: astore_3
    //   46: aload_3
    //   47: instanceof gnu/mapping/Symbol
    //   50: ifne -> 60
    //   53: aload_3
    //   54: instanceof java/lang/String
    //   57: ifeq -> 82
    //   60: aload #4
    //   62: invokevirtual getCdr : ()Ljava/lang/Object;
    //   65: astore #4
    //   67: aload #4
    //   69: instanceof gnu/lists/Pair
    //   72: ifne -> 102
    //   75: aload_2
    //   76: ldc 'wrong number of arguments for setq'
    //   78: invokevirtual syntaxError : (Ljava/lang/String;)Lgnu/expr/Expression;
    //   81: areturn
    //   82: aload_3
    //   83: getstatic gnu/commonlisp/lang/CommonLisp.FALSE : Lgnu/lists/LList;
    //   86: if_acmpne -> 95
    //   89: ldc 'nil'
    //   91: astore_3
    //   92: goto -> 60
    //   95: aload_2
    //   96: ldc 'invalid variable name in setq'
    //   98: invokevirtual syntaxError : (Ljava/lang/String;)Lgnu/expr/Expression;
    //   101: areturn
    //   102: aload #4
    //   104: checkcast gnu/lists/Pair
    //   107: astore #4
    //   109: aload_2
    //   110: aload #4
    //   112: invokevirtual getCar : ()Ljava/lang/Object;
    //   115: invokevirtual rewrite : (Ljava/lang/Object;)Lgnu/expr/Expression;
    //   118: astore #5
    //   120: aload #4
    //   122: invokevirtual getCdr : ()Ljava/lang/Object;
    //   125: astore #4
    //   127: new gnu/expr/SetExp
    //   130: dup
    //   131: aload_3
    //   132: aload #5
    //   134: invokespecial <init> : (Ljava/lang/Object;Lgnu/expr/Expression;)V
    //   137: astore #5
    //   139: aload #5
    //   141: bipush #8
    //   143: invokevirtual setFlag : (I)V
    //   146: aload #4
    //   148: getstatic gnu/lists/LList.Empty : Lgnu/lists/LList;
    //   151: if_acmpne -> 167
    //   154: aload #5
    //   156: iconst_1
    //   157: invokevirtual setHasValue : (Z)V
    //   160: aload #5
    //   162: astore_3
    //   163: aload_1
    //   164: ifnull -> 31
    //   167: aload_1
    //   168: astore_3
    //   169: aload_1
    //   170: ifnonnull -> 183
    //   173: new java/util/Vector
    //   176: dup
    //   177: bipush #10
    //   179: invokespecial <init> : (I)V
    //   182: astore_3
    //   183: aload_3
    //   184: aload #5
    //   186: invokevirtual addElement : (Ljava/lang/Object;)V
    //   189: aload_3
    //   190: astore_1
    //   191: goto -> 8
    //   194: aload_1
    //   195: ifnonnull -> 202
    //   198: getstatic gnu/commonlisp/lang/CommonLisp.nilExpr : Lgnu/expr/Expression;
    //   201: areturn
    //   202: aload_1
    //   203: invokevirtual size : ()I
    //   206: anewarray gnu/expr/Expression
    //   209: astore_2
    //   210: aload_1
    //   211: aload_2
    //   212: invokevirtual copyInto : ([Ljava/lang/Object;)V
    //   215: new gnu/expr/BeginExp
    //   218: dup
    //   219: aload_2
    //   220: invokespecial <init> : ([Lgnu/expr/Expression;)V
    //   223: areturn
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/gnu/commonlisp/lang/setq.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */