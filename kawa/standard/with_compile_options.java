package kawa.standard;

import gnu.expr.Expression;
import gnu.expr.Keyword;
import gnu.expr.ScopeExp;
import gnu.lists.LList;
import gnu.lists.Pair;
import gnu.text.Options;
import java.util.Stack;
import kawa.lang.Syntax;
import kawa.lang.SyntaxForm;
import kawa.lang.Translator;

public class with_compile_options extends Syntax {
  public static final with_compile_options with_compile_options = new with_compile_options();
  
  static {
    with_compile_options.setName("with-compile-options");
  }
  
  public static Object getOptions(Object paramObject, Stack<String> paramStack, Syntax paramSyntax, Translator paramTranslator) {
    boolean bool = false;
    Options options = paramTranslator.currentOptions;
    Pair pair = null;
    Object object = paramObject;
    paramObject = pair;
    while (true) {
      while (object instanceof SyntaxForm) {
        paramObject = object;
        object = paramObject.getDatum();
      } 
      if (object instanceof Pair) {
        pair = (Pair)object;
        Object object1 = Translator.stripSyntax(pair.getCar());
        if (object1 instanceof Keyword) {
          String str = ((Keyword)object1).getName();
          bool = true;
          Object object2 = paramTranslator.pushPositionOf(pair);
          try {
            Object object3 = pair.getCdr();
            object = paramObject;
            while (object3 instanceof SyntaxForm) {
              object = object3;
              object3 = object.getDatum();
            } 
            if (!(object3 instanceof Pair)) {
              paramTranslator.error('e', "keyword " + str + " not followed by value");
              paramObject = LList.Empty;
              return paramObject;
            } 
            paramObject = object3;
            object1 = Translator.stripSyntax(paramObject.getCar());
            object3 = paramObject.getCdr();
            Object object4 = options.getLocal(str);
            if (options.getInfo(str) == null) {
              paramTranslator.error('w', "unknown compile option: " + str);
              paramTranslator.popPositionOf(object2);
              paramObject = object;
              object = object3;
              continue;
            } 
            if (object1 instanceof gnu.lists.FString) {
              paramObject = object1.toString();
            } else {
              paramObject = object1;
              if (!(object1 instanceof Boolean)) {
                paramObject = object1;
                if (!(object1 instanceof Number)) {
                  paramObject = null;
                  paramTranslator.error('e', "invalid literal value for key " + str);
                } 
              } 
            } 
            options.set(str, paramObject, paramTranslator.getMessages());
            if (paramStack != null) {
              paramStack.push(str);
              paramStack.push(object4);
              paramStack.push(paramObject);
            } 
            paramTranslator.popPositionOf(object2);
            paramObject = object;
          } finally {
            paramTranslator.popPositionOf(object2);
          } 
          continue;
        } 
      } 
      if (!bool)
        paramTranslator.error('e', "no option keyword in " + paramSyntax.getName()); 
      return Translator.wrapSyntax(object, (SyntaxForm)paramObject);
    } 
  }
  
  public Expression rewriteForm(Pair paramPair, Translator paramTranslator) {
    // Byte code:
    //   0: aload_1
    //   1: invokevirtual getCdr : ()Ljava/lang/Object;
    //   4: astore_3
    //   5: aload_3
    //   6: instanceof gnu/lists/Pair
    //   9: ifeq -> 85
    //   12: aload_3
    //   13: checkcast gnu/lists/Pair
    //   16: astore #4
    //   18: aload #4
    //   20: invokevirtual getCar : ()Ljava/lang/Object;
    //   23: instanceof java/util/Stack
    //   26: ifeq -> 85
    //   29: aload #4
    //   31: invokevirtual getCar : ()Ljava/lang/Object;
    //   34: checkcast java/util/Stack
    //   37: astore_1
    //   38: aload #4
    //   40: invokevirtual getCdr : ()Ljava/lang/Object;
    //   43: astore_3
    //   44: aload_2
    //   45: getfield currentOptions : Lgnu/text/Options;
    //   48: aload_1
    //   49: invokevirtual pushOptionValues : (Ljava/util/Vector;)V
    //   52: aload_2
    //   53: aload_3
    //   54: invokevirtual rewrite_body : (Ljava/lang/Object;)Lgnu/expr/Expression;
    //   57: astore_3
    //   58: aload_3
    //   59: instanceof gnu/expr/BeginExp
    //   62: ifeq -> 104
    //   65: aload_3
    //   66: checkcast gnu/expr/BeginExp
    //   69: astore_3
    //   70: aload_3
    //   71: aload_1
    //   72: invokevirtual setCompileOptions : (Ljava/util/Vector;)V
    //   75: aload_2
    //   76: getfield currentOptions : Lgnu/text/Options;
    //   79: aload_1
    //   80: invokevirtual popOptionValues : (Ljava/util/Vector;)V
    //   83: aload_3
    //   84: areturn
    //   85: new java/util/Stack
    //   88: dup
    //   89: invokespecial <init> : ()V
    //   92: astore_1
    //   93: aload_3
    //   94: aload_1
    //   95: aload_0
    //   96: aload_2
    //   97: invokestatic getOptions : (Ljava/lang/Object;Ljava/util/Stack;Lkawa/lang/Syntax;Lkawa/lang/Translator;)Ljava/lang/Object;
    //   100: astore_3
    //   101: goto -> 52
    //   104: new gnu/expr/BeginExp
    //   107: dup
    //   108: iconst_1
    //   109: anewarray gnu/expr/Expression
    //   112: dup
    //   113: iconst_0
    //   114: aload_3
    //   115: aastore
    //   116: invokespecial <init> : ([Lgnu/expr/Expression;)V
    //   119: astore_3
    //   120: goto -> 70
    //   123: astore_3
    //   124: aload_2
    //   125: getfield currentOptions : Lgnu/text/Options;
    //   128: aload_1
    //   129: invokevirtual popOptionValues : (Ljava/util/Vector;)V
    //   132: aload_3
    //   133: athrow
    // Exception table:
    //   from	to	target	type
    //   52	70	123	finally
    //   70	75	123	finally
    //   104	120	123	finally
  }
  
  public void scanForm(Pair paramPair, ScopeExp paramScopeExp, Translator paramTranslator) {
    Stack stack = new Stack();
    Object object = getOptions(paramPair.getCdr(), stack, this, paramTranslator);
    if (object == LList.Empty)
      return; 
    if (object == paramPair.getCdr()) {
      paramTranslator.scanBody(object, paramScopeExp, false);
      return;
    } 
    Pair pair = new Pair(stack, paramTranslator.scanBody(object, paramScopeExp, true));
    paramTranslator.currentOptions.popOptionValues(stack);
    paramTranslator.formStack.add(Translator.makePair(paramPair, paramPair.getCar(), pair));
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/kawa/standard/with_compile_options.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */