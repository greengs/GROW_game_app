package kawa.standard;

import gnu.expr.ScopeExp;
import gnu.lists.Pair;
import kawa.lang.Syntax;
import kawa.lang.Translator;

public class module_name extends Syntax {
  public static final module_name module_name = new module_name();
  
  static {
    module_name.setName("module-name");
  }
  
  public void scanForm(Pair paramPair, ScopeExp paramScopeExp, Translator paramTranslator) {
    // Byte code:
    //   0: aload_1
    //   1: invokevirtual getCdr : ()Ljava/lang/Object;
    //   4: astore_1
    //   5: aconst_null
    //   6: astore #5
    //   8: aload_1
    //   9: instanceof kawa/lang/SyntaxForm
    //   12: ifeq -> 32
    //   15: aload_1
    //   16: checkcast kawa/lang/SyntaxForm
    //   19: astore #5
    //   21: aload #5
    //   23: invokeinterface getDatum : ()Ljava/lang/Object;
    //   28: astore_1
    //   29: goto -> 8
    //   32: aload_1
    //   33: instanceof gnu/lists/Pair
    //   36: ifeq -> 75
    //   39: aload_1
    //   40: checkcast gnu/lists/Pair
    //   43: invokevirtual getCar : ()Ljava/lang/Object;
    //   46: astore_1
    //   47: aload #5
    //   49: astore #6
    //   51: aload_1
    //   52: instanceof kawa/lang/SyntaxForm
    //   55: ifeq -> 84
    //   58: aload_1
    //   59: checkcast kawa/lang/SyntaxForm
    //   62: astore #6
    //   64: aload #6
    //   66: invokeinterface getDatum : ()Ljava/lang/Object;
    //   71: astore_1
    //   72: goto -> 51
    //   75: aconst_null
    //   76: astore_1
    //   77: aload #5
    //   79: astore #6
    //   81: goto -> 51
    //   84: aconst_null
    //   85: astore #8
    //   87: aconst_null
    //   88: astore #7
    //   90: aconst_null
    //   91: astore #5
    //   93: aload_1
    //   94: instanceof gnu/lists/Pair
    //   97: ifeq -> 200
    //   100: aload_1
    //   101: checkcast gnu/lists/Pair
    //   104: astore #9
    //   106: aload #9
    //   108: invokevirtual getCar : ()Ljava/lang/Object;
    //   111: ldc 'quote'
    //   113: if_acmpne -> 200
    //   116: aload #9
    //   118: invokevirtual getCdr : ()Ljava/lang/Object;
    //   121: astore_1
    //   122: aload_1
    //   123: instanceof gnu/lists/Pair
    //   126: ifeq -> 154
    //   129: aload_1
    //   130: checkcast gnu/lists/Pair
    //   133: astore_1
    //   134: aload_1
    //   135: invokevirtual getCdr : ()Ljava/lang/Object;
    //   138: getstatic gnu/lists/LList.Empty : Lgnu/lists/LList;
    //   141: if_acmpne -> 154
    //   144: aload_1
    //   145: invokevirtual getCar : ()Ljava/lang/Object;
    //   148: instanceof java/lang/String
    //   151: ifne -> 182
    //   154: ldc 'invalid quoted symbol for 'module-name''
    //   156: astore_2
    //   157: aload #8
    //   159: astore_1
    //   160: aload #5
    //   162: astore #6
    //   164: aload_2
    //   165: ifnull -> 336
    //   168: aload_3
    //   169: getfield formStack : Ljava/util/Stack;
    //   172: aload_3
    //   173: aload_2
    //   174: invokevirtual syntaxError : (Ljava/lang/String;)Lgnu/expr/Expression;
    //   177: invokevirtual add : (Ljava/lang/Object;)Z
    //   180: pop
    //   181: return
    //   182: aload_1
    //   183: invokevirtual getCar : ()Ljava/lang/Object;
    //   186: checkcast java/lang/String
    //   189: astore_1
    //   190: aload #5
    //   192: astore #6
    //   194: aload #7
    //   196: astore_2
    //   197: goto -> 164
    //   200: aload_1
    //   201: instanceof gnu/lists/FString
    //   204: ifne -> 214
    //   207: aload_1
    //   208: instanceof java/lang/String
    //   211: ifeq -> 229
    //   214: aload_1
    //   215: invokevirtual toString : ()Ljava/lang/String;
    //   218: astore_1
    //   219: aload #5
    //   221: astore #6
    //   223: aload #7
    //   225: astore_2
    //   226: goto -> 164
    //   229: aload_1
    //   230: instanceof gnu/mapping/Symbol
    //   233: ifeq -> 323
    //   236: aload_1
    //   237: invokevirtual toString : ()Ljava/lang/String;
    //   240: astore #8
    //   242: aload #8
    //   244: invokevirtual length : ()I
    //   247: istore #4
    //   249: aload #8
    //   251: astore #5
    //   253: iload #4
    //   255: iconst_2
    //   256: if_icmple -> 304
    //   259: aload #8
    //   261: astore #5
    //   263: aload #8
    //   265: iconst_0
    //   266: invokevirtual charAt : (I)C
    //   269: bipush #60
    //   271: if_icmpne -> 304
    //   274: aload #8
    //   276: astore #5
    //   278: aload #8
    //   280: iload #4
    //   282: iconst_1
    //   283: isub
    //   284: invokevirtual charAt : (I)C
    //   287: bipush #62
    //   289: if_icmpne -> 304
    //   292: aload #8
    //   294: iconst_1
    //   295: iload #4
    //   297: iconst_1
    //   298: isub
    //   299: invokevirtual substring : (II)Ljava/lang/String;
    //   302: astore #5
    //   304: aload_3
    //   305: aload_1
    //   306: aload #6
    //   308: aload_2
    //   309: invokevirtual define : (Ljava/lang/Object;Lkawa/lang/SyntaxForm;Lgnu/expr/ScopeExp;)Lgnu/expr/Declaration;
    //   312: astore #6
    //   314: aload #7
    //   316: astore_2
    //   317: aload #5
    //   319: astore_1
    //   320: goto -> 164
    //   323: ldc 'un-implemented expression in module-name'
    //   325: astore_2
    //   326: aload #5
    //   328: astore #6
    //   330: aload #8
    //   332: astore_1
    //   333: goto -> 164
    //   336: aload_1
    //   337: bipush #46
    //   339: invokevirtual lastIndexOf : (I)I
    //   342: istore #4
    //   344: aload_1
    //   345: astore #5
    //   347: iload #4
    //   349: iflt -> 477
    //   352: aload_3
    //   353: aload_1
    //   354: iconst_0
    //   355: iload #4
    //   357: iconst_1
    //   358: iadd
    //   359: invokevirtual substring : (II)Ljava/lang/String;
    //   362: putfield classPrefix : Ljava/lang/String;
    //   365: aload_1
    //   366: astore_2
    //   367: aload #5
    //   369: astore_1
    //   370: aload_3
    //   371: invokevirtual getModule : ()Lgnu/expr/ModuleExp;
    //   374: astore #5
    //   376: aload_3
    //   377: getfield mainClass : Lgnu/bytecode/ClassType;
    //   380: ifnonnull -> 527
    //   383: aload_3
    //   384: new gnu/bytecode/ClassType
    //   387: dup
    //   388: aload_1
    //   389: invokespecial <init> : (Ljava/lang/String;)V
    //   392: putfield mainClass : Lgnu/bytecode/ClassType;
    //   395: aload #5
    //   397: aload_3
    //   398: getfield mainClass : Lgnu/bytecode/ClassType;
    //   401: invokevirtual setType : (Lgnu/bytecode/ClassType;)V
    //   404: aload #5
    //   406: aload_2
    //   407: invokevirtual setName : (Ljava/lang/String;)V
    //   410: aload #6
    //   412: ifnull -> 472
    //   415: aload #6
    //   417: new gnu/expr/QuoteExp
    //   420: dup
    //   421: aload_3
    //   422: getfield mainClass : Lgnu/bytecode/ClassType;
    //   425: getstatic gnu/expr/Compilation.typeClass : Lgnu/bytecode/ClassType;
    //   428: invokespecial <init> : (Ljava/lang/Object;Lgnu/bytecode/Type;)V
    //   431: invokevirtual noteValue : (Lgnu/expr/Expression;)V
    //   434: aload #6
    //   436: ldc2_w 16793600
    //   439: invokevirtual setFlag : (J)V
    //   442: aload #5
    //   444: getfield outer : Lgnu/expr/ScopeExp;
    //   447: ifnonnull -> 458
    //   450: aload #6
    //   452: ldc2_w 2048
    //   455: invokevirtual setFlag : (J)V
    //   458: aload #6
    //   460: iconst_1
    //   461: invokevirtual setPrivate : (Z)V
    //   464: aload #6
    //   466: getstatic gnu/expr/Compilation.typeClass : Lgnu/bytecode/ClassType;
    //   469: invokevirtual setType : (Lgnu/bytecode/Type;)V
    //   472: aload_3
    //   473: invokevirtual mustCompileHere : ()V
    //   476: return
    //   477: new java/lang/StringBuilder
    //   480: dup
    //   481: invokespecial <init> : ()V
    //   484: aload_3
    //   485: getfield classPrefix : Ljava/lang/String;
    //   488: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   491: aload_1
    //   492: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   495: invokevirtual toString : ()Ljava/lang/String;
    //   498: astore_2
    //   499: new java/lang/StringBuilder
    //   502: dup
    //   503: invokespecial <init> : ()V
    //   506: aload_3
    //   507: getfield classPrefix : Ljava/lang/String;
    //   510: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   513: aload_2
    //   514: invokestatic mangleName : (Ljava/lang/String;)Ljava/lang/String;
    //   517: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   520: invokevirtual toString : ()Ljava/lang/String;
    //   523: astore_1
    //   524: goto -> 370
    //   527: aload_3
    //   528: getfield mainClass : Lgnu/bytecode/ClassType;
    //   531: invokevirtual getName : ()Ljava/lang/String;
    //   534: astore #7
    //   536: aload #7
    //   538: ifnonnull -> 552
    //   541: aload_3
    //   542: getfield mainClass : Lgnu/bytecode/ClassType;
    //   545: aload_1
    //   546: invokevirtual setName : (Ljava/lang/String;)V
    //   549: goto -> 395
    //   552: aload #7
    //   554: aload_1
    //   555: invokevirtual equals : (Ljava/lang/Object;)Z
    //   558: ifne -> 395
    //   561: aload_3
    //   562: new java/lang/StringBuilder
    //   565: dup
    //   566: invokespecial <init> : ()V
    //   569: ldc 'duplicate module-name: old name: '
    //   571: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   574: aload #7
    //   576: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   579: invokevirtual toString : ()Ljava/lang/String;
    //   582: invokevirtual syntaxError : (Ljava/lang/String;)Lgnu/expr/Expression;
    //   585: pop
    //   586: goto -> 395
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/kawa/standard/module_name.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */