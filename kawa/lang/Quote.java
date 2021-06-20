package kawa.lang;

import gnu.bytecode.ClassType;
import gnu.bytecode.Method;
import gnu.expr.ApplyExp;
import gnu.expr.Compilation;
import gnu.expr.Expression;
import gnu.expr.QuoteExp;
import gnu.lists.LList;
import gnu.lists.Pair;
import gnu.mapping.Namespace;
import gnu.mapping.Symbol;
import java.util.IdentityHashMap;

public class Quote extends Syntax {
  private static final Object CYCLE;
  
  protected static final int QUOTE_DEPTH = -1;
  
  private static final Object WORKING;
  
  static final Method appendMethod;
  
  static final Method consXMethod;
  
  static final Method makePairMethod;
  
  static final Method makeVectorMethod;
  
  public static final Quote plainQuote = new Quote("quote", false);
  
  public static final Quote quasiQuote = new Quote("quasiquote", true);
  
  static final ClassType quoteType;
  
  static final Method vectorAppendMethod;
  
  protected boolean isQuasi;
  
  static {
    WORKING = new String("(working)");
    CYCLE = new String("(cycle)");
    vectorAppendMethod = ClassType.make("kawa.standard.vector_append").getDeclaredMethod("apply$V", 1);
    quoteType = ClassType.make("kawa.lang.Quote");
    consXMethod = quoteType.getDeclaredMethod("consX$V", 1);
    appendMethod = quoteType.getDeclaredMethod("append$V", 1);
    makePairMethod = Compilation.typePair.getDeclaredMethod("make", 2);
    makeVectorMethod = ClassType.make("gnu.lists.FVector").getDeclaredMethod("make", 1);
  }
  
  public Quote(String paramString, boolean paramBoolean) {
    super(paramString);
    this.isQuasi = paramBoolean;
  }
  
  public static Object append$V(Object[] paramArrayOfObject) {
    int i = paramArrayOfObject.length;
    if (i == 0)
      return LList.Empty; 
    Object object = paramArrayOfObject[i - 1];
    i--;
    label34: while (true) {
      i--;
      Object object1 = object;
      if (i >= 0) {
        Object object2 = paramArrayOfObject[i];
        Pair pair = null;
        SyntaxForm syntaxForm = null;
        object1 = null;
        while (true) {
          while (object2 instanceof SyntaxForm) {
            syntaxForm = (SyntaxForm)object2;
            object2 = syntaxForm.getDatum();
          } 
          if (object2 == LList.Empty) {
            if (pair != null) {
              pair.setCdr(object);
            } else {
              object1 = object;
            } 
            object = object1;
            continue label34;
          } 
          Pair pair1 = (Pair)object2;
          Object object4 = pair1.getCar();
          object2 = object4;
          if (syntaxForm != null) {
            object2 = object4;
            if (!(object4 instanceof SyntaxForm))
              object2 = SyntaxForms.makeForm(object4, syntaxForm.getScope()); 
          } 
          object2 = new Pair(object2, null);
          if (pair == null) {
            object1 = object2;
          } else {
            pair.setCdr(object2);
          } 
          object4 = pair1.getCdr();
          Object object3 = object2;
          object2 = object4;
        } 
        break;
      } 
      return object1;
    } 
  }
  
  public static Object consX$V(Object[] paramArrayOfObject) {
    return LList.consX(paramArrayOfObject);
  }
  
  private static ApplyExp makeInvokeMakeVector(Expression[] paramArrayOfExpression) {
    return new ApplyExp(makeVectorMethod, paramArrayOfExpression);
  }
  
  public static Symbol makeSymbol(Namespace paramNamespace, Object paramObject) {
    if (paramObject instanceof CharSequence) {
      paramObject = ((CharSequence)paramObject).toString();
      return paramNamespace.getSymbol(paramObject.intern());
    } 
    paramObject = paramObject;
    return paramNamespace.getSymbol(paramObject.intern());
  }
  
  public static Object quote(Object paramObject) {
    return plainQuote.expand(paramObject, -1, (Translator)Compilation.getCurrent());
  }
  
  public static Object quote(Object paramObject, Translator paramTranslator) {
    return plainQuote.expand(paramObject, -1, paramTranslator);
  }
  
  protected Expression coerceExpression(Object paramObject, Translator paramTranslator) {
    return (paramObject instanceof Expression) ? (Expression)paramObject : leaf(paramObject, paramTranslator);
  }
  
  Object expand(Object paramObject1, int paramInt, SyntaxForm paramSyntaxForm, Object paramObject2, Translator paramTranslator) {
    // Byte code:
    //   0: aload #4
    //   2: checkcast java/util/IdentityHashMap
    //   5: astore #12
    //   7: aload #12
    //   9: aload_1
    //   10: invokevirtual get : (Ljava/lang/Object;)Ljava/lang/Object;
    //   13: astore #11
    //   15: aload #11
    //   17: getstatic kawa/lang/Quote.WORKING : Ljava/lang/Object;
    //   20: if_acmpne -> 36
    //   23: aload #12
    //   25: aload_1
    //   26: getstatic kawa/lang/Quote.CYCLE : Ljava/lang/Object;
    //   29: invokevirtual put : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   32: pop
    //   33: aload #11
    //   35: areturn
    //   36: aload #11
    //   38: getstatic kawa/lang/Quote.CYCLE : Ljava/lang/Object;
    //   41: if_acmpeq -> 33
    //   44: aload #11
    //   46: ifnonnull -> 33
    //   49: aload_1
    //   50: instanceof gnu/lists/Pair
    //   53: ifeq -> 107
    //   56: aload_0
    //   57: aload_1
    //   58: checkcast gnu/lists/Pair
    //   61: iload_2
    //   62: aload_3
    //   63: aload #4
    //   65: aload #5
    //   67: invokevirtual expand_pair : (Lgnu/lists/Pair;ILkawa/lang/SyntaxForm;Ljava/lang/Object;Lkawa/lang/Translator;)Ljava/lang/Object;
    //   70: astore_3
    //   71: aload_1
    //   72: aload_3
    //   73: if_acmpeq -> 97
    //   76: aload #12
    //   78: aload_1
    //   79: invokevirtual get : (Ljava/lang/Object;)Ljava/lang/Object;
    //   82: getstatic kawa/lang/Quote.CYCLE : Ljava/lang/Object;
    //   85: if_acmpne -> 97
    //   88: aload #5
    //   90: bipush #101
    //   92: ldc 'cycle in non-literal data'
    //   94: invokevirtual error : (CLjava/lang/String;)V
    //   97: aload #12
    //   99: aload_1
    //   100: aload_3
    //   101: invokevirtual put : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   104: pop
    //   105: aload_3
    //   106: areturn
    //   107: aload_1
    //   108: instanceof kawa/lang/SyntaxForm
    //   111: ifeq -> 139
    //   114: aload_1
    //   115: checkcast kawa/lang/SyntaxForm
    //   118: astore_3
    //   119: aload_0
    //   120: aload_3
    //   121: invokeinterface getDatum : ()Ljava/lang/Object;
    //   126: iload_2
    //   127: aload_3
    //   128: aload #4
    //   130: aload #5
    //   132: invokevirtual expand : (Ljava/lang/Object;ILkawa/lang/SyntaxForm;Ljava/lang/Object;Lkawa/lang/Translator;)Ljava/lang/Object;
    //   135: astore_3
    //   136: goto -> 71
    //   139: aload_1
    //   140: instanceof gnu/lists/FVector
    //   143: ifeq -> 626
    //   146: aload_1
    //   147: checkcast gnu/lists/FVector
    //   150: astore #11
    //   152: aload #11
    //   154: invokevirtual size : ()I
    //   157: istore #10
    //   159: iload #10
    //   161: anewarray java/lang/Object
    //   164: astore #13
    //   166: iload #10
    //   168: newarray byte
    //   170: astore #14
    //   172: iconst_0
    //   173: istore #6
    //   175: iconst_0
    //   176: istore #7
    //   178: iload #7
    //   180: iload #10
    //   182: if_icmpge -> 445
    //   185: aload #11
    //   187: iload #7
    //   189: invokevirtual get : (I)Ljava/lang/Object;
    //   192: astore #16
    //   194: iload_2
    //   195: istore #9
    //   197: iload #9
    //   199: istore #8
    //   201: aload #16
    //   203: instanceof gnu/lists/Pair
    //   206: ifeq -> 379
    //   209: iload #9
    //   211: istore #8
    //   213: iload_2
    //   214: iconst_m1
    //   215: if_icmple -> 379
    //   218: aload #16
    //   220: checkcast gnu/lists/Pair
    //   223: astore #15
    //   225: iload #9
    //   227: istore #8
    //   229: aload #5
    //   231: aload #15
    //   233: invokevirtual getCar : ()Ljava/lang/Object;
    //   236: aload_3
    //   237: ldc 'unquote-splicing'
    //   239: invokevirtual matches : (Ljava/lang/Object;Lkawa/lang/SyntaxForm;Ljava/lang/String;)Z
    //   242: ifeq -> 379
    //   245: iload #9
    //   247: iconst_1
    //   248: isub
    //   249: istore #9
    //   251: iload #9
    //   253: istore #8
    //   255: iload #9
    //   257: ifne -> 379
    //   260: aload #15
    //   262: invokevirtual getCdr : ()Ljava/lang/Object;
    //   265: instanceof gnu/lists/Pair
    //   268: ifeq -> 292
    //   271: aload #15
    //   273: invokevirtual getCdr : ()Ljava/lang/Object;
    //   276: checkcast gnu/lists/Pair
    //   279: astore #16
    //   281: aload #16
    //   283: invokevirtual getCdr : ()Ljava/lang/Object;
    //   286: getstatic gnu/lists/LList.Empty : Lgnu/lists/LList;
    //   289: if_acmpeq -> 326
    //   292: aload #5
    //   294: new java/lang/StringBuilder
    //   297: dup
    //   298: invokespecial <init> : ()V
    //   301: ldc 'invalid used of '
    //   303: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   306: aload #15
    //   308: invokevirtual getCar : ()Ljava/lang/Object;
    //   311: invokevirtual append : (Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   314: ldc ' in quasiquote template'
    //   316: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   319: invokevirtual toString : ()Ljava/lang/String;
    //   322: invokevirtual syntaxError : (Ljava/lang/String;)Lgnu/expr/Expression;
    //   325: areturn
    //   326: aload #13
    //   328: iload #7
    //   330: aload #5
    //   332: aload #16
    //   334: aload_3
    //   335: invokevirtual rewrite_car : (Lgnu/lists/Pair;Lkawa/lang/SyntaxForm;)Lgnu/expr/Expression;
    //   338: aastore
    //   339: aload #14
    //   341: iload #7
    //   343: iconst_3
    //   344: bastore
    //   345: iload #6
    //   347: istore #8
    //   349: aload #14
    //   351: iload #7
    //   353: baload
    //   354: iload #6
    //   356: if_icmple -> 366
    //   359: aload #14
    //   361: iload #7
    //   363: baload
    //   364: istore #8
    //   366: iload #7
    //   368: iconst_1
    //   369: iadd
    //   370: istore #7
    //   372: iload #8
    //   374: istore #6
    //   376: goto -> 178
    //   379: aload #13
    //   381: iload #7
    //   383: aload_0
    //   384: aload #16
    //   386: iload #8
    //   388: aload_3
    //   389: aload #4
    //   391: aload #5
    //   393: invokevirtual expand : (Ljava/lang/Object;ILkawa/lang/SyntaxForm;Ljava/lang/Object;Lkawa/lang/Translator;)Ljava/lang/Object;
    //   396: aastore
    //   397: aload #13
    //   399: iload #7
    //   401: aaload
    //   402: aload #16
    //   404: if_acmpne -> 416
    //   407: aload #14
    //   409: iload #7
    //   411: iconst_0
    //   412: bastore
    //   413: goto -> 345
    //   416: aload #13
    //   418: iload #7
    //   420: aaload
    //   421: instanceof gnu/expr/Expression
    //   424: ifeq -> 436
    //   427: aload #14
    //   429: iload #7
    //   431: iconst_2
    //   432: bastore
    //   433: goto -> 345
    //   436: aload #14
    //   438: iload #7
    //   440: iconst_1
    //   441: bastore
    //   442: goto -> 345
    //   445: iload #6
    //   447: ifne -> 456
    //   450: aload #11
    //   452: astore_3
    //   453: goto -> 71
    //   456: iload #6
    //   458: iconst_1
    //   459: if_icmpne -> 475
    //   462: new gnu/lists/FVector
    //   465: dup
    //   466: aload #13
    //   468: invokespecial <init> : ([Ljava/lang/Object;)V
    //   471: astore_3
    //   472: goto -> 453
    //   475: iload #10
    //   477: anewarray gnu/expr/Expression
    //   480: astore_3
    //   481: iconst_0
    //   482: istore_2
    //   483: iload_2
    //   484: iload #10
    //   486: if_icmpge -> 597
    //   489: aload #14
    //   491: iload_2
    //   492: baload
    //   493: iconst_3
    //   494: if_icmpne -> 514
    //   497: aload_3
    //   498: iload_2
    //   499: aload #13
    //   501: iload_2
    //   502: aaload
    //   503: checkcast gnu/expr/Expression
    //   506: aastore
    //   507: iload_2
    //   508: iconst_1
    //   509: iadd
    //   510: istore_2
    //   511: goto -> 483
    //   514: iload #6
    //   516: iconst_3
    //   517: if_icmpge -> 536
    //   520: aload_3
    //   521: iload_2
    //   522: aload_0
    //   523: aload #13
    //   525: iload_2
    //   526: aaload
    //   527: aload #5
    //   529: invokevirtual coerceExpression : (Ljava/lang/Object;Lkawa/lang/Translator;)Lgnu/expr/Expression;
    //   532: aastore
    //   533: goto -> 507
    //   536: aload #14
    //   538: iload_2
    //   539: baload
    //   540: iconst_2
    //   541: if_icmpge -> 574
    //   544: aload_3
    //   545: iload_2
    //   546: aload_0
    //   547: new gnu/lists/FVector
    //   550: dup
    //   551: iconst_1
    //   552: anewarray java/lang/Object
    //   555: dup
    //   556: iconst_0
    //   557: aload #13
    //   559: iload_2
    //   560: aaload
    //   561: aastore
    //   562: invokespecial <init> : ([Ljava/lang/Object;)V
    //   565: aload #5
    //   567: invokevirtual leaf : (Ljava/lang/Object;Lkawa/lang/Translator;)Lgnu/expr/Expression;
    //   570: aastore
    //   571: goto -> 507
    //   574: aload_3
    //   575: iload_2
    //   576: iconst_1
    //   577: anewarray gnu/expr/Expression
    //   580: dup
    //   581: iconst_0
    //   582: aload #13
    //   584: iload_2
    //   585: aaload
    //   586: checkcast gnu/expr/Expression
    //   589: aastore
    //   590: invokestatic makeInvokeMakeVector : ([Lgnu/expr/Expression;)Lgnu/expr/ApplyExp;
    //   593: aastore
    //   594: goto -> 507
    //   597: iload #6
    //   599: iconst_3
    //   600: if_icmpge -> 611
    //   603: aload_3
    //   604: invokestatic makeInvokeMakeVector : ([Lgnu/expr/Expression;)Lgnu/expr/ApplyExp;
    //   607: astore_3
    //   608: goto -> 453
    //   611: new gnu/expr/ApplyExp
    //   614: dup
    //   615: getstatic kawa/lang/Quote.vectorAppendMethod : Lgnu/bytecode/Method;
    //   618: aload_3
    //   619: invokespecial <init> : (Lgnu/bytecode/Method;[Lgnu/expr/Expression;)V
    //   622: astore_3
    //   623: goto -> 453
    //   626: aload_1
    //   627: astore_3
    //   628: goto -> 71
  }
  
  protected Object expand(Object paramObject, int paramInt, Translator paramTranslator) {
    return expand(paramObject, paramInt, null, new IdentityHashMap<Object, Object>(), paramTranslator);
  }
  
  protected boolean expandColonForms() {
    return true;
  }
  
  Object expand_pair(Pair paramPair, int paramInt, SyntaxForm paramSyntaxForm, Object paramObject, Translator paramTranslator) {
    // Byte code:
    //   0: aload_1
    //   1: astore #8
    //   3: aload_0
    //   4: invokevirtual expandColonForms : ()Z
    //   7: ifeq -> 362
    //   10: aload #8
    //   12: aload_1
    //   13: if_acmpne -> 362
    //   16: aload #5
    //   18: aload #8
    //   20: invokevirtual getCar : ()Ljava/lang/Object;
    //   23: aload_3
    //   24: getstatic gnu/kawa/lispexpr/LispLanguage.lookup_sym : Lgnu/mapping/Symbol;
    //   27: invokevirtual matches : (Ljava/lang/Object;Lkawa/lang/SyntaxForm;Lgnu/mapping/Symbol;)Z
    //   30: ifeq -> 362
    //   33: aload #8
    //   35: invokevirtual getCdr : ()Ljava/lang/Object;
    //   38: instanceof gnu/lists/Pair
    //   41: ifeq -> 362
    //   44: aload #8
    //   46: invokevirtual getCdr : ()Ljava/lang/Object;
    //   49: checkcast gnu/lists/Pair
    //   52: astore #9
    //   54: aload #9
    //   56: instanceof gnu/lists/Pair
    //   59: ifeq -> 362
    //   62: aload #9
    //   64: invokevirtual getCdr : ()Ljava/lang/Object;
    //   67: checkcast gnu/lists/Pair
    //   70: astore #10
    //   72: aload #10
    //   74: instanceof gnu/lists/Pair
    //   77: ifeq -> 362
    //   80: aload #10
    //   82: invokevirtual getCdr : ()Ljava/lang/Object;
    //   85: getstatic gnu/lists/LList.Empty : Lgnu/lists/LList;
    //   88: if_acmpne -> 362
    //   91: aload #5
    //   93: aload #9
    //   95: iconst_0
    //   96: invokevirtual rewrite_car : (Lgnu/lists/Pair;Z)Lgnu/expr/Expression;
    //   99: astore #4
    //   101: aload #5
    //   103: aload #10
    //   105: iconst_0
    //   106: invokevirtual rewrite_car : (Lgnu/lists/Pair;Z)Lgnu/expr/Expression;
    //   109: astore #10
    //   111: aload #5
    //   113: aload #4
    //   115: invokevirtual namespaceResolvePrefix : (Lgnu/expr/Expression;)Lgnu/mapping/Namespace;
    //   118: astore #11
    //   120: aload #5
    //   122: aload #11
    //   124: aload #10
    //   126: invokevirtual namespaceResolve : (Lgnu/mapping/Namespace;Lgnu/expr/Expression;)Lgnu/mapping/Symbol;
    //   129: astore_3
    //   130: aload_3
    //   131: ifnull -> 146
    //   134: aload #8
    //   136: astore #4
    //   138: aload_1
    //   139: aload #4
    //   141: if_acmpne -> 1048
    //   144: aload_3
    //   145: areturn
    //   146: aload #11
    //   148: ifnull -> 198
    //   151: iload_2
    //   152: iconst_1
    //   153: if_icmpne -> 198
    //   156: new gnu/expr/ApplyExp
    //   159: dup
    //   160: getstatic kawa/lang/Quote.quoteType : Lgnu/bytecode/ClassType;
    //   163: ldc_w 'makeSymbol'
    //   166: iconst_2
    //   167: invokevirtual getDeclaredMethod : (Ljava/lang/String;I)Lgnu/bytecode/Method;
    //   170: iconst_2
    //   171: anewarray gnu/expr/Expression
    //   174: dup
    //   175: iconst_0
    //   176: aload #11
    //   178: invokestatic getInstance : (Ljava/lang/Object;)Lgnu/expr/QuoteExp;
    //   181: aastore
    //   182: dup
    //   183: iconst_1
    //   184: aload #10
    //   186: aastore
    //   187: invokespecial <init> : (Lgnu/bytecode/Method;[Lgnu/expr/Expression;)V
    //   190: astore_3
    //   191: aload #8
    //   193: astore #4
    //   195: goto -> 138
    //   198: aload #4
    //   200: instanceof gnu/expr/ReferenceExp
    //   203: ifeq -> 270
    //   206: aload #10
    //   208: instanceof gnu/expr/QuoteExp
    //   211: ifeq -> 270
    //   214: aload #5
    //   216: invokevirtual getGlobalEnvironment : ()Lgnu/mapping/Environment;
    //   219: new java/lang/StringBuilder
    //   222: dup
    //   223: invokespecial <init> : ()V
    //   226: aload #4
    //   228: checkcast gnu/expr/ReferenceExp
    //   231: invokevirtual getName : ()Ljava/lang/String;
    //   234: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   237: bipush #58
    //   239: invokevirtual append : (C)Ljava/lang/StringBuilder;
    //   242: aload #10
    //   244: checkcast gnu/expr/QuoteExp
    //   247: invokevirtual getValue : ()Ljava/lang/Object;
    //   250: invokevirtual toString : ()Ljava/lang/String;
    //   253: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   256: invokevirtual toString : ()Ljava/lang/String;
    //   259: invokevirtual getSymbol : (Ljava/lang/String;)Lgnu/mapping/Symbol;
    //   262: astore_3
    //   263: aload #8
    //   265: astore #4
    //   267: goto -> 138
    //   270: aload #4
    //   272: aload #10
    //   274: invokestatic combineName : (Lgnu/expr/Expression;Lgnu/expr/Expression;)Ljava/lang/String;
    //   277: astore #4
    //   279: aload #4
    //   281: ifnull -> 302
    //   284: aload #5
    //   286: invokevirtual getGlobalEnvironment : ()Lgnu/mapping/Environment;
    //   289: aload #4
    //   291: invokevirtual getSymbol : (Ljava/lang/String;)Lgnu/mapping/Symbol;
    //   294: astore_3
    //   295: aload #8
    //   297: astore #4
    //   299: goto -> 138
    //   302: aload #5
    //   304: aload #8
    //   306: invokevirtual pushPositionOf : (Ljava/lang/Object;)Ljava/lang/Object;
    //   309: astore #4
    //   311: aload #5
    //   313: bipush #101
    //   315: new java/lang/StringBuilder
    //   318: dup
    //   319: invokespecial <init> : ()V
    //   322: ldc_w '''
    //   325: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   328: aload #9
    //   330: invokevirtual getCar : ()Ljava/lang/Object;
    //   333: invokevirtual append : (Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   336: ldc_w '' is not a valid prefix'
    //   339: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   342: invokevirtual toString : ()Ljava/lang/String;
    //   345: invokevirtual error : (CLjava/lang/String;)V
    //   348: aload #5
    //   350: aload #4
    //   352: invokevirtual popPositionOf : (Ljava/lang/Object;)V
    //   355: aload #8
    //   357: astore #4
    //   359: goto -> 138
    //   362: iload_2
    //   363: ifge -> 419
    //   366: iload_2
    //   367: iconst_1
    //   368: if_icmpne -> 880
    //   371: aload #8
    //   373: invokevirtual getCar : ()Ljava/lang/Object;
    //   376: instanceof gnu/lists/Pair
    //   379: ifeq -> 880
    //   382: aload #8
    //   384: invokevirtual getCar : ()Ljava/lang/Object;
    //   387: astore #10
    //   389: aload_3
    //   390: astore #9
    //   392: aload #10
    //   394: instanceof kawa/lang/SyntaxForm
    //   397: ifeq -> 604
    //   400: aload #10
    //   402: checkcast kawa/lang/SyntaxForm
    //   405: astore #9
    //   407: aload #9
    //   409: invokeinterface getDatum : ()Ljava/lang/Object;
    //   414: astore #10
    //   416: goto -> 392
    //   419: aload #5
    //   421: aload #8
    //   423: invokevirtual getCar : ()Ljava/lang/Object;
    //   426: aload_3
    //   427: ldc 'quasiquote'
    //   429: invokevirtual matches : (Ljava/lang/Object;Lkawa/lang/SyntaxForm;Ljava/lang/String;)Z
    //   432: ifeq -> 442
    //   435: iload_2
    //   436: iconst_1
    //   437: iadd
    //   438: istore_2
    //   439: goto -> 366
    //   442: aload #5
    //   444: aload #8
    //   446: invokevirtual getCar : ()Ljava/lang/Object;
    //   449: aload_3
    //   450: ldc_w 'unquote'
    //   453: invokevirtual matches : (Ljava/lang/Object;Lkawa/lang/SyntaxForm;Ljava/lang/String;)Z
    //   456: ifeq -> 554
    //   459: iload_2
    //   460: iconst_1
    //   461: isub
    //   462: istore #6
    //   464: aload #8
    //   466: invokevirtual getCdr : ()Ljava/lang/Object;
    //   469: instanceof gnu/lists/Pair
    //   472: ifeq -> 496
    //   475: aload #8
    //   477: invokevirtual getCdr : ()Ljava/lang/Object;
    //   480: checkcast gnu/lists/Pair
    //   483: astore #9
    //   485: aload #9
    //   487: invokevirtual getCdr : ()Ljava/lang/Object;
    //   490: getstatic gnu/lists/LList.Empty : Lgnu/lists/LList;
    //   493: if_acmpeq -> 530
    //   496: aload #5
    //   498: new java/lang/StringBuilder
    //   501: dup
    //   502: invokespecial <init> : ()V
    //   505: ldc 'invalid used of '
    //   507: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   510: aload #8
    //   512: invokevirtual getCar : ()Ljava/lang/Object;
    //   515: invokevirtual append : (Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   518: ldc ' in quasiquote template'
    //   520: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   523: invokevirtual toString : ()Ljava/lang/String;
    //   526: invokevirtual syntaxError : (Ljava/lang/String;)Lgnu/expr/Expression;
    //   529: areturn
    //   530: iload #6
    //   532: istore_2
    //   533: iload #6
    //   535: ifne -> 366
    //   538: aload #5
    //   540: aload #9
    //   542: aload_3
    //   543: invokevirtual rewrite_car : (Lgnu/lists/Pair;Lkawa/lang/SyntaxForm;)Lgnu/expr/Expression;
    //   546: astore_3
    //   547: aload #8
    //   549: astore #4
    //   551: goto -> 138
    //   554: aload #5
    //   556: aload #8
    //   558: invokevirtual getCar : ()Ljava/lang/Object;
    //   561: aload_3
    //   562: ldc 'unquote-splicing'
    //   564: invokevirtual matches : (Ljava/lang/Object;Lkawa/lang/SyntaxForm;Ljava/lang/String;)Z
    //   567: ifeq -> 366
    //   570: aload #5
    //   572: new java/lang/StringBuilder
    //   575: dup
    //   576: invokespecial <init> : ()V
    //   579: ldc 'invalid used of '
    //   581: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   584: aload #8
    //   586: invokevirtual getCar : ()Ljava/lang/Object;
    //   589: invokevirtual append : (Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   592: ldc ' in quasiquote template'
    //   594: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   597: invokevirtual toString : ()Ljava/lang/String;
    //   600: invokevirtual syntaxError : (Ljava/lang/String;)Lgnu/expr/Expression;
    //   603: areturn
    //   604: iconst_m1
    //   605: istore #7
    //   607: iload #7
    //   609: istore #6
    //   611: aload #10
    //   613: instanceof gnu/lists/Pair
    //   616: ifeq -> 647
    //   619: aload #10
    //   621: checkcast gnu/lists/Pair
    //   624: invokevirtual getCar : ()Ljava/lang/Object;
    //   627: astore #11
    //   629: aload #5
    //   631: aload #11
    //   633: aload #9
    //   635: ldc_w 'unquote'
    //   638: invokevirtual matches : (Ljava/lang/Object;Lkawa/lang/SyntaxForm;Ljava/lang/String;)Z
    //   641: ifeq -> 802
    //   644: iconst_0
    //   645: istore #6
    //   647: iload #6
    //   649: iflt -> 880
    //   652: aload #10
    //   654: checkcast gnu/lists/Pair
    //   657: invokevirtual getCdr : ()Ljava/lang/Object;
    //   660: astore #11
    //   662: new java/util/Vector
    //   665: dup
    //   666: invokespecial <init> : ()V
    //   669: astore #12
    //   671: aload #9
    //   673: astore #10
    //   675: aload #11
    //   677: astore #9
    //   679: aload #9
    //   681: astore #11
    //   683: aload #9
    //   685: instanceof kawa/lang/SyntaxForm
    //   688: ifeq -> 707
    //   691: aload #9
    //   693: checkcast kawa/lang/SyntaxForm
    //   696: astore #10
    //   698: aload #10
    //   700: invokeinterface getDatum : ()Ljava/lang/Object;
    //   705: astore #11
    //   707: aload #11
    //   709: getstatic gnu/lists/LList.Empty : Lgnu/lists/LList;
    //   712: if_acmpne -> 826
    //   715: aload #12
    //   717: invokevirtual size : ()I
    //   720: iconst_1
    //   721: iadd
    //   722: istore_2
    //   723: aload_0
    //   724: aload #8
    //   726: invokevirtual getCdr : ()Ljava/lang/Object;
    //   729: iconst_1
    //   730: aload_3
    //   731: aload #4
    //   733: aload #5
    //   735: invokevirtual expand : (Ljava/lang/Object;ILkawa/lang/SyntaxForm;Ljava/lang/Object;Lkawa/lang/Translator;)Ljava/lang/Object;
    //   738: astore #4
    //   740: aload #4
    //   742: astore_3
    //   743: iload_2
    //   744: iconst_1
    //   745: if_icmple -> 795
    //   748: iload_2
    //   749: anewarray gnu/expr/Expression
    //   752: astore #9
    //   754: aload #12
    //   756: aload #9
    //   758: invokevirtual copyInto : ([Ljava/lang/Object;)V
    //   761: aload #9
    //   763: iload_2
    //   764: iconst_1
    //   765: isub
    //   766: aload_0
    //   767: aload #4
    //   769: aload #5
    //   771: invokevirtual coerceExpression : (Ljava/lang/Object;Lkawa/lang/Translator;)Lgnu/expr/Expression;
    //   774: aastore
    //   775: iload #6
    //   777: ifne -> 873
    //   780: getstatic kawa/lang/Quote.consXMethod : Lgnu/bytecode/Method;
    //   783: astore_3
    //   784: new gnu/expr/ApplyExp
    //   787: dup
    //   788: aload_3
    //   789: aload #9
    //   791: invokespecial <init> : (Lgnu/bytecode/Method;[Lgnu/expr/Expression;)V
    //   794: astore_3
    //   795: aload #8
    //   797: astore #4
    //   799: goto -> 138
    //   802: iload #7
    //   804: istore #6
    //   806: aload #5
    //   808: aload #11
    //   810: aload #9
    //   812: ldc 'unquote-splicing'
    //   814: invokevirtual matches : (Ljava/lang/Object;Lkawa/lang/SyntaxForm;Ljava/lang/String;)Z
    //   817: ifeq -> 647
    //   820: iconst_1
    //   821: istore #6
    //   823: goto -> 647
    //   826: aload #11
    //   828: instanceof gnu/lists/Pair
    //   831: ifeq -> 864
    //   834: aload #12
    //   836: aload #5
    //   838: aload #11
    //   840: checkcast gnu/lists/Pair
    //   843: aload #10
    //   845: invokevirtual rewrite_car : (Lgnu/lists/Pair;Lkawa/lang/SyntaxForm;)Lgnu/expr/Expression;
    //   848: invokevirtual addElement : (Ljava/lang/Object;)V
    //   851: aload #11
    //   853: checkcast gnu/lists/Pair
    //   856: invokevirtual getCdr : ()Ljava/lang/Object;
    //   859: astore #9
    //   861: goto -> 679
    //   864: aload #5
    //   866: ldc_w 'improper list argument to unquote'
    //   869: invokevirtual syntaxError : (Ljava/lang/String;)Lgnu/expr/Expression;
    //   872: areturn
    //   873: getstatic kawa/lang/Quote.appendMethod : Lgnu/bytecode/Method;
    //   876: astore_3
    //   877: goto -> 784
    //   880: aload_0
    //   881: aload #8
    //   883: invokevirtual getCar : ()Ljava/lang/Object;
    //   886: iload_2
    //   887: aload_3
    //   888: aload #4
    //   890: aload #5
    //   892: invokevirtual expand : (Ljava/lang/Object;ILkawa/lang/SyntaxForm;Ljava/lang/Object;Lkawa/lang/Translator;)Ljava/lang/Object;
    //   895: astore #9
    //   897: aload #9
    //   899: aload #8
    //   901: invokevirtual getCar : ()Ljava/lang/Object;
    //   904: if_acmpne -> 952
    //   907: aload #8
    //   909: invokevirtual getCdr : ()Ljava/lang/Object;
    //   912: astore #8
    //   914: aload #8
    //   916: instanceof gnu/lists/Pair
    //   919: ifeq -> 932
    //   922: aload #8
    //   924: checkcast gnu/lists/Pair
    //   927: astore #8
    //   929: goto -> 3
    //   932: aload_0
    //   933: aload #8
    //   935: iload_2
    //   936: aload_3
    //   937: aload #4
    //   939: aload #5
    //   941: invokevirtual expand : (Ljava/lang/Object;ILkawa/lang/SyntaxForm;Ljava/lang/Object;Lkawa/lang/Translator;)Ljava/lang/Object;
    //   944: astore_3
    //   945: aload #8
    //   947: astore #4
    //   949: goto -> 138
    //   952: aload_0
    //   953: aload #8
    //   955: invokevirtual getCdr : ()Ljava/lang/Object;
    //   958: iload_2
    //   959: aload_3
    //   960: aload #4
    //   962: aload #5
    //   964: invokevirtual expand : (Ljava/lang/Object;ILkawa/lang/SyntaxForm;Ljava/lang/Object;Lkawa/lang/Translator;)Ljava/lang/Object;
    //   967: astore_3
    //   968: aload #9
    //   970: instanceof gnu/expr/Expression
    //   973: ifne -> 983
    //   976: aload_3
    //   977: instanceof gnu/expr/Expression
    //   980: ifeq -> 1032
    //   983: aload_0
    //   984: aload #9
    //   986: aload #5
    //   988: invokevirtual coerceExpression : (Ljava/lang/Object;Lkawa/lang/Translator;)Lgnu/expr/Expression;
    //   991: astore #4
    //   993: aload_0
    //   994: aload_3
    //   995: aload #5
    //   997: invokevirtual coerceExpression : (Ljava/lang/Object;Lkawa/lang/Translator;)Lgnu/expr/Expression;
    //   1000: astore_3
    //   1001: new gnu/expr/ApplyExp
    //   1004: dup
    //   1005: getstatic kawa/lang/Quote.makePairMethod : Lgnu/bytecode/Method;
    //   1008: iconst_2
    //   1009: anewarray gnu/expr/Expression
    //   1012: dup
    //   1013: iconst_0
    //   1014: aload #4
    //   1016: aastore
    //   1017: dup
    //   1018: iconst_1
    //   1019: aload_3
    //   1020: aastore
    //   1021: invokespecial <init> : (Lgnu/bytecode/Method;[Lgnu/expr/Expression;)V
    //   1024: astore_3
    //   1025: aload #8
    //   1027: astore #4
    //   1029: goto -> 138
    //   1032: aload #8
    //   1034: aload #9
    //   1036: aload_3
    //   1037: invokestatic makePair : (Lgnu/lists/Pair;Ljava/lang/Object;Ljava/lang/Object;)Lgnu/lists/Pair;
    //   1040: astore_3
    //   1041: aload #8
    //   1043: astore #4
    //   1045: goto -> 138
    //   1048: aload_1
    //   1049: astore #9
    //   1051: bipush #20
    //   1053: anewarray gnu/lists/Pair
    //   1056: astore #10
    //   1058: iconst_0
    //   1059: istore_2
    //   1060: aload #10
    //   1062: astore #8
    //   1064: iload_2
    //   1065: aload #10
    //   1067: arraylength
    //   1068: if_icmplt -> 1089
    //   1071: iload_2
    //   1072: iconst_2
    //   1073: imul
    //   1074: anewarray gnu/lists/Pair
    //   1077: astore #8
    //   1079: aload #10
    //   1081: iconst_0
    //   1082: aload #8
    //   1084: iconst_0
    //   1085: iload_2
    //   1086: invokestatic arraycopy : (Ljava/lang/Object;ILjava/lang/Object;II)V
    //   1089: iload_2
    //   1090: iconst_1
    //   1091: iadd
    //   1092: istore #6
    //   1094: aload #8
    //   1096: iload_2
    //   1097: aload #9
    //   1099: aastore
    //   1100: aload #9
    //   1102: invokevirtual getCdr : ()Ljava/lang/Object;
    //   1105: aload #4
    //   1107: if_acmpne -> 1157
    //   1110: aload_3
    //   1111: instanceof gnu/expr/Expression
    //   1114: ifeq -> 1177
    //   1117: getstatic gnu/lists/LList.Empty : Lgnu/lists/LList;
    //   1120: astore #4
    //   1122: iload #6
    //   1124: iconst_1
    //   1125: isub
    //   1126: istore #6
    //   1128: iload #6
    //   1130: iflt -> 1183
    //   1133: aload #8
    //   1135: iload #6
    //   1137: aaload
    //   1138: astore #9
    //   1140: aload #9
    //   1142: aload #9
    //   1144: invokevirtual getCar : ()Ljava/lang/Object;
    //   1147: aload #4
    //   1149: invokestatic makePair : (Lgnu/lists/Pair;Ljava/lang/Object;Ljava/lang/Object;)Lgnu/lists/Pair;
    //   1152: astore #4
    //   1154: goto -> 1122
    //   1157: aload #9
    //   1159: invokevirtual getCdr : ()Ljava/lang/Object;
    //   1162: checkcast gnu/lists/Pair
    //   1165: astore #9
    //   1167: iload #6
    //   1169: istore_2
    //   1170: aload #8
    //   1172: astore #10
    //   1174: goto -> 1060
    //   1177: aload_3
    //   1178: astore #4
    //   1180: goto -> 1122
    //   1183: aload_3
    //   1184: instanceof gnu/expr/Expression
    //   1187: ifeq -> 1262
    //   1190: iconst_2
    //   1191: anewarray gnu/expr/Expression
    //   1194: astore #8
    //   1196: aload #8
    //   1198: iconst_1
    //   1199: aload_3
    //   1200: checkcast gnu/expr/Expression
    //   1203: aastore
    //   1204: iload #6
    //   1206: iconst_1
    //   1207: if_icmpne -> 1237
    //   1210: aload #8
    //   1212: iconst_0
    //   1213: aload_0
    //   1214: aload_1
    //   1215: invokevirtual getCar : ()Ljava/lang/Object;
    //   1218: aload #5
    //   1220: invokevirtual leaf : (Ljava/lang/Object;Lkawa/lang/Translator;)Lgnu/expr/Expression;
    //   1223: aastore
    //   1224: new gnu/expr/ApplyExp
    //   1227: dup
    //   1228: getstatic kawa/lang/Quote.makePairMethod : Lgnu/bytecode/Method;
    //   1231: aload #8
    //   1233: invokespecial <init> : (Lgnu/bytecode/Method;[Lgnu/expr/Expression;)V
    //   1236: areturn
    //   1237: aload #8
    //   1239: iconst_0
    //   1240: aload_0
    //   1241: aload #4
    //   1243: aload #5
    //   1245: invokevirtual leaf : (Ljava/lang/Object;Lkawa/lang/Translator;)Lgnu/expr/Expression;
    //   1248: aastore
    //   1249: new gnu/expr/ApplyExp
    //   1252: dup
    //   1253: getstatic kawa/lang/Quote.appendMethod : Lgnu/bytecode/Method;
    //   1256: aload #8
    //   1258: invokespecial <init> : (Lgnu/bytecode/Method;[Lgnu/expr/Expression;)V
    //   1261: areturn
    //   1262: aload #4
    //   1264: areturn
  }
  
  protected Expression leaf(Object paramObject, Translator paramTranslator) {
    return (Expression)new QuoteExp(paramObject);
  }
  
  public Expression rewrite(Object paramObject, Translator paramTranslator) {
    if (paramObject instanceof Pair) {
      paramObject = paramObject;
      if (paramObject.getCdr() != LList.Empty)
        return paramTranslator.syntaxError("wrong number of arguments to quote"); 
    } else {
      return paramTranslator.syntaxError("wrong number of arguments to quote");
    } 
    paramObject = paramObject.getCar();
    if (this.isQuasi) {
      boolean bool = true;
      return coerceExpression(expand(paramObject, bool, paramTranslator), paramTranslator);
    } 
    byte b = -1;
    return coerceExpression(expand(paramObject, b, paramTranslator), paramTranslator);
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/kawa/lang/Quote.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */