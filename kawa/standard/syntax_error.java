package kawa.standard;

import gnu.expr.Expression;
import gnu.lists.LList;
import kawa.lang.Syntax;
import kawa.lang.Translator;

public class syntax_error extends Syntax {
  public static final syntax_error syntax_error = new syntax_error();
  
  static {
    syntax_error.setName("%syntax-error");
  }
  
  public static Expression error(Object paramObject, Object[] paramArrayOfObject) {
    // Byte code:
    //   0: new java/lang/StringBuffer
    //   3: dup
    //   4: invokespecial <init> : ()V
    //   7: astore #4
    //   9: aload_1
    //   10: arraylength
    //   11: istore_3
    //   12: aload_1
    //   13: ifnull -> 20
    //   16: iload_3
    //   17: ifne -> 52
    //   20: aload #4
    //   22: ldc 'invalid syntax'
    //   24: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   27: pop
    //   28: invokestatic getCurrent : ()Lgnu/expr/Compilation;
    //   31: checkcast kawa/lang/Translator
    //   34: astore_1
    //   35: aload_1
    //   36: ifnonnull -> 75
    //   39: new java/lang/RuntimeException
    //   42: dup
    //   43: aload #4
    //   45: invokevirtual toString : ()Ljava/lang/String;
    //   48: invokespecial <init> : (Ljava/lang/String;)V
    //   51: athrow
    //   52: iconst_0
    //   53: istore_2
    //   54: iload_2
    //   55: iload_3
    //   56: if_icmpge -> 28
    //   59: aload #4
    //   61: aload_1
    //   62: iload_2
    //   63: aaload
    //   64: invokevirtual append : (Ljava/lang/Object;)Ljava/lang/StringBuffer;
    //   67: pop
    //   68: iload_2
    //   69: iconst_1
    //   70: iadd
    //   71: istore_2
    //   72: goto -> 54
    //   75: aload_1
    //   76: aload_0
    //   77: invokevirtual pushPositionOf : (Ljava/lang/Object;)Ljava/lang/Object;
    //   80: astore_0
    //   81: aload_1
    //   82: aload #4
    //   84: invokevirtual toString : ()Ljava/lang/String;
    //   87: invokevirtual syntaxError : (Ljava/lang/String;)Lgnu/expr/Expression;
    //   90: astore #4
    //   92: aload_1
    //   93: aload_0
    //   94: invokevirtual popPositionOf : (Ljava/lang/Object;)V
    //   97: aload #4
    //   99: areturn
    //   100: astore #4
    //   102: aload_1
    //   103: aload_0
    //   104: invokevirtual popPositionOf : (Ljava/lang/Object;)V
    //   107: aload #4
    //   109: athrow
    // Exception table:
    //   from	to	target	type
    //   81	92	100	finally
  }
  
  public Expression rewrite(Object paramObject, Translator paramTranslator) {
    StringBuffer stringBuffer = new StringBuffer();
    int i;
    for (i = 0; paramObject instanceof gnu.lists.Pair; i++) {
      paramObject = paramObject;
      if (i)
        stringBuffer.append(' '); 
      stringBuffer.append(paramObject.getCar());
      paramObject = paramObject.getCdr();
    } 
    if (paramObject != LList.Empty) {
      if (i > 0)
        stringBuffer.append(' '); 
      stringBuffer.append(paramObject);
    } 
    return paramTranslator.syntaxError(stringBuffer.toString());
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/kawa/standard/syntax_error.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */