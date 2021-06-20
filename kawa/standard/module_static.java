package kawa.standard;

import gnu.expr.Expression;
import gnu.expr.ScopeExp;
import gnu.lists.Pair;
import java.util.Vector;
import kawa.lang.Syntax;
import kawa.lang.Translator;

public class module_static extends Syntax {
  public static final module_static module_static = new module_static();
  
  static {
    module_static.setName("module-static");
  }
  
  public Expression rewriteForm(Pair paramPair, Translator paramTranslator) {
    return null;
  }
  
  public boolean scanForDefinitions(Pair paramPair, Vector paramVector, ScopeExp paramScopeExp, Translator paramTranslator) {
    // Byte code:
    //   0: aload_1
    //   1: invokevirtual getCdr : ()Ljava/lang/Object;
    //   4: astore_1
    //   5: aload_3
    //   6: instanceof gnu/expr/ModuleExp
    //   9: ifne -> 48
    //   12: aload #4
    //   14: bipush #101
    //   16: new java/lang/StringBuilder
    //   19: dup
    //   20: invokespecial <init> : ()V
    //   23: ldc '''
    //   25: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   28: aload_0
    //   29: invokevirtual getName : ()Ljava/lang/String;
    //   32: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   35: ldc '' not at module level'
    //   37: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   40: invokevirtual toString : ()Ljava/lang/String;
    //   43: invokevirtual error : (CLjava/lang/String;)V
    //   46: iconst_1
    //   47: ireturn
    //   48: aload_3
    //   49: checkcast gnu/expr/ModuleExp
    //   52: astore_2
    //   53: aload_1
    //   54: instanceof gnu/lists/Pair
    //   57: ifeq -> 143
    //   60: aload_1
    //   61: checkcast gnu/lists/Pair
    //   64: astore #5
    //   66: aload #5
    //   68: invokevirtual getCdr : ()Ljava/lang/Object;
    //   71: getstatic gnu/lists/LList.Empty : Lgnu/lists/LList;
    //   74: if_acmpne -> 143
    //   77: aload #5
    //   79: invokevirtual getCar : ()Ljava/lang/Object;
    //   82: instanceof java/lang/Boolean
    //   85: ifeq -> 143
    //   88: aload #5
    //   90: invokevirtual getCar : ()Ljava/lang/Object;
    //   93: getstatic java/lang/Boolean.FALSE : Ljava/lang/Boolean;
    //   96: if_acmpne -> 134
    //   99: aload_2
    //   100: ldc 65536
    //   102: invokevirtual setFlag : (I)V
    //   105: aload_2
    //   106: ldc 65536
    //   108: invokevirtual getFlag : (I)Z
    //   111: ifeq -> 46
    //   114: aload_2
    //   115: ldc 32768
    //   117: invokevirtual getFlag : (I)Z
    //   120: ifeq -> 46
    //   123: aload #4
    //   125: bipush #101
    //   127: ldc 'inconsistent module-static specifiers'
    //   129: invokevirtual error : (CLjava/lang/String;)V
    //   132: iconst_1
    //   133: ireturn
    //   134: aload_2
    //   135: ldc 32768
    //   137: invokevirtual setFlag : (I)V
    //   140: goto -> 105
    //   143: aload_1
    //   144: instanceof gnu/lists/Pair
    //   147: ifeq -> 292
    //   150: aload_1
    //   151: checkcast gnu/lists/Pair
    //   154: astore #5
    //   156: aload #5
    //   158: invokevirtual getCdr : ()Ljava/lang/Object;
    //   161: getstatic gnu/lists/LList.Empty : Lgnu/lists/LList;
    //   164: if_acmpne -> 292
    //   167: aload #5
    //   169: invokevirtual getCar : ()Ljava/lang/Object;
    //   172: instanceof gnu/lists/Pair
    //   175: ifeq -> 292
    //   178: aload #5
    //   180: invokevirtual getCar : ()Ljava/lang/Object;
    //   183: checkcast gnu/lists/Pair
    //   186: astore #5
    //   188: aload #4
    //   190: aload #5
    //   192: invokevirtual getCar : ()Ljava/lang/Object;
    //   195: ldc 'quote'
    //   197: invokevirtual matches : (Ljava/lang/Object;Ljava/lang/String;)Z
    //   200: ifeq -> 292
    //   203: aload #5
    //   205: invokevirtual getCdr : ()Ljava/lang/Object;
    //   208: checkcast gnu/lists/Pair
    //   211: astore_1
    //   212: aload_1
    //   213: getstatic gnu/lists/LList.Empty : Lgnu/lists/LList;
    //   216: if_acmpeq -> 256
    //   219: aload_1
    //   220: invokevirtual getCar : ()Ljava/lang/Object;
    //   223: instanceof gnu/mapping/SimpleSymbol
    //   226: ifeq -> 256
    //   229: aload_1
    //   230: invokevirtual getCar : ()Ljava/lang/Object;
    //   233: invokevirtual toString : ()Ljava/lang/String;
    //   236: ldc 'init-run'
    //   238: if_acmpne -> 256
    //   241: aload_2
    //   242: ldc 32768
    //   244: invokevirtual setFlag : (I)V
    //   247: aload_2
    //   248: ldc 262144
    //   250: invokevirtual setFlag : (I)V
    //   253: goto -> 105
    //   256: aload #4
    //   258: bipush #101
    //   260: new java/lang/StringBuilder
    //   263: dup
    //   264: invokespecial <init> : ()V
    //   267: ldc 'invalid quoted symbol for ''
    //   269: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   272: aload_0
    //   273: invokevirtual getName : ()Ljava/lang/String;
    //   276: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   279: bipush #39
    //   281: invokevirtual append : (C)Ljava/lang/StringBuilder;
    //   284: invokevirtual toString : ()Ljava/lang/String;
    //   287: invokevirtual error : (CLjava/lang/String;)V
    //   290: iconst_0
    //   291: ireturn
    //   292: aload_2
    //   293: ldc 65536
    //   295: invokevirtual setFlag : (I)V
    //   298: aload_1
    //   299: getstatic gnu/lists/LList.Empty : Lgnu/lists/LList;
    //   302: if_acmpeq -> 105
    //   305: aload_1
    //   306: instanceof gnu/lists/Pair
    //   309: ifeq -> 327
    //   312: aload_1
    //   313: checkcast gnu/lists/Pair
    //   316: astore_1
    //   317: aload_1
    //   318: invokevirtual getCar : ()Ljava/lang/Object;
    //   321: instanceof gnu/mapping/Symbol
    //   324: ifne -> 363
    //   327: aload #4
    //   329: bipush #101
    //   331: new java/lang/StringBuilder
    //   334: dup
    //   335: invokespecial <init> : ()V
    //   338: ldc 'invalid syntax in ''
    //   340: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   343: aload_0
    //   344: invokevirtual getName : ()Ljava/lang/String;
    //   347: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   350: bipush #39
    //   352: invokevirtual append : (C)Ljava/lang/StringBuilder;
    //   355: invokevirtual toString : ()Ljava/lang/String;
    //   358: invokevirtual error : (CLjava/lang/String;)V
    //   361: iconst_0
    //   362: ireturn
    //   363: aload_3
    //   364: aload_1
    //   365: invokevirtual getCar : ()Ljava/lang/Object;
    //   368: checkcast gnu/mapping/Symbol
    //   371: invokevirtual getNoDefine : (Ljava/lang/Object;)Lgnu/expr/Declaration;
    //   374: astore #5
    //   376: aload #5
    //   378: ldc2_w 512
    //   381: invokevirtual getFlag : (J)Z
    //   384: ifeq -> 393
    //   387: aload #5
    //   389: aload_1
    //   390: invokestatic setLine : (Lgnu/expr/Declaration;Ljava/lang/Object;)V
    //   393: aload #5
    //   395: ldc2_w 2048
    //   398: invokevirtual setFlag : (J)V
    //   401: aload_1
    //   402: invokevirtual getCdr : ()Ljava/lang/Object;
    //   405: astore_1
    //   406: goto -> 298
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/kawa/standard/module_static.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */