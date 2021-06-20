package kawa.standard;

import gnu.expr.Declaration;
import gnu.expr.Expression;
import gnu.expr.ScopeExp;
import gnu.lists.Pair;
import java.util.Vector;
import kawa.lang.Syntax;
import kawa.lang.Translator;

public class define_variable extends Syntax {
  public static final define_variable define_variable = new define_variable();
  
  static {
    define_variable.setName("define-variable");
  }
  
  public Expression rewriteForm(Pair paramPair, Translator paramTranslator) {
    // Byte code:
    //   0: aload_1
    //   1: invokevirtual getCdr : ()Ljava/lang/Object;
    //   4: astore #6
    //   6: aconst_null
    //   7: astore #4
    //   9: aconst_null
    //   10: astore #5
    //   12: aload #5
    //   14: astore_1
    //   15: aload #4
    //   17: astore_3
    //   18: aload #6
    //   20: instanceof gnu/lists/Pair
    //   23: ifeq -> 148
    //   26: aload #6
    //   28: checkcast gnu/lists/Pair
    //   31: astore #6
    //   33: aload #6
    //   35: invokevirtual getCar : ()Ljava/lang/Object;
    //   38: astore #7
    //   40: aload #7
    //   42: instanceof java/lang/String
    //   45: ifne -> 56
    //   48: aload #7
    //   50: instanceof gnu/mapping/Symbol
    //   53: ifeq -> 85
    //   56: aload_2
    //   57: new java/lang/StringBuilder
    //   60: dup
    //   61: invokespecial <init> : ()V
    //   64: aload_0
    //   65: invokevirtual getName : ()Ljava/lang/String;
    //   68: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   71: ldc ' is only allowed in a <body>'
    //   73: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   76: invokevirtual toString : ()Ljava/lang/String;
    //   79: invokevirtual syntaxError : (Ljava/lang/String;)Lgnu/expr/Expression;
    //   82: astore_2
    //   83: aload_2
    //   84: areturn
    //   85: aload #5
    //   87: astore_1
    //   88: aload #4
    //   90: astore_3
    //   91: aload #7
    //   93: instanceof gnu/expr/Declaration
    //   96: ifeq -> 148
    //   99: aload #6
    //   101: invokevirtual getCar : ()Ljava/lang/Object;
    //   104: checkcast gnu/expr/Declaration
    //   107: astore_1
    //   108: aload #6
    //   110: invokevirtual getCdr : ()Ljava/lang/Object;
    //   113: astore #5
    //   115: aload #5
    //   117: instanceof gnu/lists/Pair
    //   120: ifeq -> 179
    //   123: aload #5
    //   125: checkcast gnu/lists/Pair
    //   128: astore_3
    //   129: aload_3
    //   130: invokevirtual getCdr : ()Ljava/lang/Object;
    //   133: getstatic gnu/lists/LList.Empty : Lgnu/lists/LList;
    //   136: if_acmpne -> 179
    //   139: aload_2
    //   140: aload_3
    //   141: invokevirtual getCar : ()Ljava/lang/Object;
    //   144: invokevirtual rewrite : (Ljava/lang/Object;)Lgnu/expr/Expression;
    //   147: astore_3
    //   148: aload_1
    //   149: ifnonnull -> 198
    //   152: aload_2
    //   153: new java/lang/StringBuilder
    //   156: dup
    //   157: invokespecial <init> : ()V
    //   160: ldc 'invalid syntax for '
    //   162: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   165: aload_0
    //   166: invokevirtual getName : ()Ljava/lang/String;
    //   169: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   172: invokevirtual toString : ()Ljava/lang/String;
    //   175: invokevirtual syntaxError : (Ljava/lang/String;)Lgnu/expr/Expression;
    //   178: areturn
    //   179: aload #4
    //   181: astore_3
    //   182: aload #5
    //   184: getstatic gnu/lists/LList.Empty : Lgnu/lists/LList;
    //   187: if_acmpeq -> 148
    //   190: aconst_null
    //   191: astore_1
    //   192: aload #4
    //   194: astore_3
    //   195: goto -> 148
    //   198: aload_3
    //   199: ifnonnull -> 206
    //   202: getstatic gnu/expr/QuoteExp.voidExp : Lgnu/expr/QuoteExp;
    //   205: areturn
    //   206: new gnu/expr/SetExp
    //   209: dup
    //   210: aload_1
    //   211: aload_3
    //   212: invokespecial <init> : (Lgnu/expr/Declaration;Lgnu/expr/Expression;)V
    //   215: astore #4
    //   217: aload #4
    //   219: iconst_1
    //   220: invokevirtual setDefining : (Z)V
    //   223: aload #4
    //   225: iconst_1
    //   226: invokevirtual setSetIfUnbound : (Z)V
    //   229: aload #4
    //   231: astore_2
    //   232: aload_1
    //   233: ifnull -> 83
    //   236: aload #4
    //   238: aload_1
    //   239: invokevirtual setBinding : (Lgnu/expr/Declaration;)V
    //   242: aload_3
    //   243: astore_2
    //   244: aload_1
    //   245: getfield context : Lgnu/expr/ScopeExp;
    //   248: instanceof gnu/expr/ModuleExp
    //   251: ifeq -> 265
    //   254: aload_3
    //   255: astore_2
    //   256: aload_1
    //   257: invokevirtual getCanWrite : ()Z
    //   260: ifeq -> 265
    //   263: aconst_null
    //   264: astore_2
    //   265: aload_1
    //   266: aload_2
    //   267: invokevirtual noteValue : (Lgnu/expr/Expression;)V
    //   270: aload #4
    //   272: areturn
  }
  
  public boolean scanForDefinitions(Pair paramPair, Vector<Pair> paramVector, ScopeExp paramScopeExp, Translator paramTranslator) {
    if (!(paramPair.getCdr() instanceof Pair))
      return super.scanForDefinitions(paramPair, paramVector, paramScopeExp, paramTranslator); 
    Pair pair2 = (Pair)paramPair.getCdr();
    Object object = pair2.getCar();
    if (!(object instanceof String)) {
      Pair pair = paramPair;
      if (object instanceof gnu.mapping.Symbol) {
        if (paramScopeExp.lookup(object) != null)
          paramTranslator.error('e', "duplicate declaration for '" + object + "'"); 
        declaration = paramScopeExp.addDeclaration(object);
        paramTranslator.push(declaration);
        declaration.setSimple(false);
        declaration.setPrivate(true);
        declaration.setFlag(268697600L);
        declaration.setCanRead(true);
        declaration.setCanWrite(true);
        declaration.setIndirectBinding(true);
        pair = Translator.makePair(paramPair, this, Translator.makePair(pair2, declaration, pair2.getCdr()));
        paramVector.addElement(pair);
        return true;
      } 
      paramVector.addElement(pair);
      return true;
    } 
    if (declaration.lookup(object) != null)
      paramTranslator.error('e', "duplicate declaration for '" + object + "'"); 
    Declaration declaration = declaration.addDeclaration(object);
    paramTranslator.push(declaration);
    declaration.setSimple(false);
    declaration.setPrivate(true);
    declaration.setFlag(268697600L);
    declaration.setCanRead(true);
    declaration.setCanWrite(true);
    declaration.setIndirectBinding(true);
    Pair pair1 = Translator.makePair(paramPair, this, Translator.makePair(pair2, declaration, pair2.getCdr()));
    paramVector.addElement(pair1);
    return true;
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/kawa/standard/define_variable.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */