package kawa.lang;

import gnu.expr.Declaration;
import gnu.expr.ErrorExp;
import gnu.expr.Expression;
import gnu.expr.Keyword;
import gnu.expr.LambdaExp;
import gnu.expr.QuoteExp;
import gnu.expr.ScopeExp;
import gnu.lists.Consumer;
import gnu.lists.LList;
import gnu.lists.Pair;
import gnu.lists.PairWithPosition;

public class Lambda extends Syntax {
  public static final Keyword nameKeyword = Keyword.make("name");
  
  public Expression defaultDefault = (Expression)QuoteExp.falseExp;
  
  public Object keyKeyword;
  
  public Object optionalKeyword;
  
  public Object restKeyword;
  
  private static void addParam(Declaration paramDeclaration, ScopeExp paramScopeExp, LambdaExp paramLambdaExp, Translator paramTranslator) {
    Declaration declaration = paramDeclaration;
    if (paramScopeExp != null)
      declaration = paramTranslator.makeRenamedAlias(paramDeclaration, paramScopeExp); 
    paramLambdaExp.addDeclaration(declaration);
    if (paramScopeExp != null)
      declaration.context = paramScopeExp; 
  }
  
  public void print(Consumer paramConsumer) {
    paramConsumer.write("#<builtin lambda>");
  }
  
  public Expression rewrite(Object paramObject, Translator paramTranslator) {
    if (!(paramObject instanceof Pair))
      return paramTranslator.syntaxError("missing formals in lambda"); 
    int i = paramTranslator.getMessages().getErrorCount();
    LambdaExp lambdaExp = new LambdaExp();
    paramObject = paramObject;
    Translator.setLine((Expression)lambdaExp, paramObject);
    rewrite(lambdaExp, paramObject.getCar(), paramObject.getCdr(), paramTranslator, null);
    paramObject = lambdaExp;
    return (Expression)((paramTranslator.getMessages().getErrorCount() > i) ? new ErrorExp("bad lambda expression") : paramObject);
  }
  
  public void rewrite(LambdaExp paramLambdaExp, Object paramObject1, Object paramObject2, Translator paramTranslator, TemplateScope paramTemplateScope) {
    rewriteFormals(paramLambdaExp, paramObject1, paramTranslator, paramTemplateScope);
    if (paramObject2 instanceof PairWithPosition)
      paramLambdaExp.setFile(((PairWithPosition)paramObject2).getFileName()); 
    rewriteBody(paramLambdaExp, rewriteAttrs(paramLambdaExp, paramObject2, paramTranslator), paramTranslator);
  }
  
  public Object rewriteAttrs(LambdaExp paramLambdaExp, Object paramObject, Translator paramTranslator) {
    // Byte code:
    //   0: aconst_null
    //   1: astore #11
    //   3: aconst_null
    //   4: astore #10
    //   6: iconst_0
    //   7: istore #6
    //   9: iconst_0
    //   10: istore #5
    //   12: aconst_null
    //   13: astore #8
    //   15: aload_2
    //   16: instanceof kawa/lang/SyntaxForm
    //   19: ifeq -> 39
    //   22: aload_2
    //   23: checkcast kawa/lang/SyntaxForm
    //   26: astore #8
    //   28: aload #8
    //   30: invokeinterface getDatum : ()Ljava/lang/Object;
    //   35: astore_2
    //   36: goto -> 15
    //   39: aload_2
    //   40: instanceof gnu/lists/Pair
    //   43: ifne -> 84
    //   46: iload #6
    //   48: iload #5
    //   50: ior
    //   51: istore #4
    //   53: iload #4
    //   55: ifeq -> 68
    //   58: aload_1
    //   59: getfield nameDecl : Lgnu/expr/Declaration;
    //   62: iload #4
    //   64: i2l
    //   65: invokevirtual setFlag : (J)V
    //   68: aload_2
    //   69: astore_1
    //   70: aload #8
    //   72: ifnull -> 82
    //   75: aload_2
    //   76: aload #8
    //   78: invokestatic fromDatumIfNeeded : (Ljava/lang/Object;Lkawa/lang/SyntaxForm;)Ljava/lang/Object;
    //   81: astore_1
    //   82: aload_1
    //   83: areturn
    //   84: aload_2
    //   85: checkcast gnu/lists/Pair
    //   88: astore #13
    //   90: aload #13
    //   92: invokevirtual getCar : ()Ljava/lang/Object;
    //   95: invokestatic stripSyntax : (Ljava/lang/Object;)Ljava/lang/Object;
    //   98: astore #9
    //   100: aload_3
    //   101: aload #9
    //   103: ldc '::'
    //   105: invokevirtual matches : (Ljava/lang/Object;Ljava/lang/String;)Z
    //   108: ifeq -> 152
    //   111: aconst_null
    //   112: astore #12
    //   114: aload #8
    //   116: astore #9
    //   118: aload #13
    //   120: invokevirtual getCdr : ()Ljava/lang/Object;
    //   123: astore #13
    //   125: aload #13
    //   127: instanceof kawa/lang/SyntaxForm
    //   130: ifeq -> 167
    //   133: aload #13
    //   135: checkcast kawa/lang/SyntaxForm
    //   138: astore #9
    //   140: aload #9
    //   142: invokeinterface getDatum : ()Ljava/lang/Object;
    //   147: astore #13
    //   149: goto -> 125
    //   152: aload #9
    //   154: astore #12
    //   156: aload #9
    //   158: instanceof gnu/expr/Keyword
    //   161: ifne -> 114
    //   164: goto -> 46
    //   167: aload #13
    //   169: instanceof gnu/lists/Pair
    //   172: ifeq -> 46
    //   175: aload #13
    //   177: checkcast gnu/lists/Pair
    //   180: astore #13
    //   182: aload #12
    //   184: ifnonnull -> 300
    //   187: aload_1
    //   188: invokevirtual isClassMethod : ()Z
    //   191: ifeq -> 257
    //   194: ldc '*init*'
    //   196: aload_1
    //   197: invokevirtual getName : ()Ljava/lang/String;
    //   200: invokevirtual equals : (Ljava/lang/Object;)Z
    //   203: ifeq -> 257
    //   206: aload_3
    //   207: bipush #101
    //   209: ldc 'explicit return type for '*init*' method'
    //   211: invokevirtual error : (CLjava/lang/String;)V
    //   214: aload #10
    //   216: astore #9
    //   218: iload #5
    //   220: istore #4
    //   222: aload #11
    //   224: astore_2
    //   225: iload #6
    //   227: istore #7
    //   229: aload #13
    //   231: invokevirtual getCdr : ()Ljava/lang/Object;
    //   234: astore #12
    //   236: iload #7
    //   238: istore #6
    //   240: aload_2
    //   241: astore #11
    //   243: iload #4
    //   245: istore #5
    //   247: aload #9
    //   249: astore #10
    //   251: aload #12
    //   253: astore_2
    //   254: goto -> 15
    //   257: aload_1
    //   258: new gnu/expr/LangExp
    //   261: dup
    //   262: iconst_2
    //   263: anewarray java/lang/Object
    //   266: dup
    //   267: iconst_0
    //   268: aload #13
    //   270: aastore
    //   271: dup
    //   272: iconst_1
    //   273: aload #9
    //   275: aastore
    //   276: invokespecial <init> : (Ljava/lang/Object;)V
    //   279: putfield body : Lgnu/expr/Expression;
    //   282: iload #6
    //   284: istore #7
    //   286: aload #11
    //   288: astore_2
    //   289: iload #5
    //   291: istore #4
    //   293: aload #10
    //   295: astore #9
    //   297: goto -> 229
    //   300: aload #12
    //   302: getstatic kawa/standard/object.accessKeyword : Lgnu/expr/Keyword;
    //   305: if_acmpne -> 545
    //   308: aload_3
    //   309: aload #13
    //   311: aload #9
    //   313: invokevirtual rewrite_car : (Lgnu/lists/Pair;Lkawa/lang/SyntaxForm;)Lgnu/expr/Expression;
    //   316: astore_2
    //   317: aload_2
    //   318: instanceof gnu/expr/QuoteExp
    //   321: ifeq -> 346
    //   324: aload_2
    //   325: checkcast gnu/expr/QuoteExp
    //   328: invokevirtual getValue : ()Ljava/lang/Object;
    //   331: astore_2
    //   332: aload_2
    //   333: instanceof gnu/mapping/SimpleSymbol
    //   336: ifne -> 372
    //   339: aload_2
    //   340: instanceof java/lang/CharSequence
    //   343: ifne -> 372
    //   346: aload_3
    //   347: bipush #101
    //   349: ldc 'access: value not a constant symbol or string'
    //   351: invokevirtual error : (CLjava/lang/String;)V
    //   354: iload #6
    //   356: istore #7
    //   358: aload #11
    //   360: astore_2
    //   361: iload #5
    //   363: istore #4
    //   365: aload #10
    //   367: astore #9
    //   369: goto -> 229
    //   372: aload_1
    //   373: getfield nameDecl : Lgnu/expr/Declaration;
    //   376: ifnonnull -> 405
    //   379: aload_3
    //   380: bipush #101
    //   382: ldc 'access: not allowed for anonymous function'
    //   384: invokevirtual error : (CLjava/lang/String;)V
    //   387: iload #6
    //   389: istore #7
    //   391: aload #11
    //   393: astore_2
    //   394: iload #5
    //   396: istore #4
    //   398: aload #10
    //   400: astore #9
    //   402: goto -> 229
    //   405: aload_2
    //   406: invokevirtual toString : ()Ljava/lang/String;
    //   409: astore_2
    //   410: ldc 'private'
    //   412: aload_2
    //   413: invokevirtual equals : (Ljava/lang/Object;)Z
    //   416: ifeq -> 482
    //   419: ldc 16777216
    //   421: istore #4
    //   423: aload #11
    //   425: ifnull -> 467
    //   428: aload_2
    //   429: ifnull -> 467
    //   432: aload_3
    //   433: bipush #101
    //   435: new java/lang/StringBuilder
    //   438: dup
    //   439: invokespecial <init> : ()V
    //   442: ldc 'duplicate access specifiers - '
    //   444: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   447: aload #11
    //   449: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   452: ldc ' and '
    //   454: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   457: aload_2
    //   458: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   461: invokevirtual toString : ()Ljava/lang/String;
    //   464: invokevirtual error : (CLjava/lang/String;)V
    //   467: iload #4
    //   469: istore #7
    //   471: iload #5
    //   473: istore #4
    //   475: aload #10
    //   477: astore #9
    //   479: goto -> 229
    //   482: ldc 'protected'
    //   484: aload_2
    //   485: invokevirtual equals : (Ljava/lang/Object;)Z
    //   488: ifeq -> 498
    //   491: ldc 33554432
    //   493: istore #4
    //   495: goto -> 423
    //   498: ldc 'public'
    //   500: aload_2
    //   501: invokevirtual equals : (Ljava/lang/Object;)Z
    //   504: ifeq -> 514
    //   507: ldc 67108864
    //   509: istore #4
    //   511: goto -> 423
    //   514: ldc 'package'
    //   516: aload_2
    //   517: invokevirtual equals : (Ljava/lang/Object;)Z
    //   520: ifeq -> 530
    //   523: ldc 134217728
    //   525: istore #4
    //   527: goto -> 423
    //   530: aload_3
    //   531: bipush #101
    //   533: ldc 'unknown access specifier'
    //   535: invokevirtual error : (CLjava/lang/String;)V
    //   538: iload #6
    //   540: istore #4
    //   542: goto -> 423
    //   545: aload #12
    //   547: getstatic kawa/standard/object.allocationKeyword : Lgnu/expr/Keyword;
    //   550: if_acmpne -> 770
    //   553: aload_3
    //   554: aload #13
    //   556: aload #9
    //   558: invokevirtual rewrite_car : (Lgnu/lists/Pair;Lkawa/lang/SyntaxForm;)Lgnu/expr/Expression;
    //   561: astore_2
    //   562: aload_2
    //   563: instanceof gnu/expr/QuoteExp
    //   566: ifeq -> 591
    //   569: aload_2
    //   570: checkcast gnu/expr/QuoteExp
    //   573: invokevirtual getValue : ()Ljava/lang/Object;
    //   576: astore_2
    //   577: aload_2
    //   578: instanceof gnu/mapping/SimpleSymbol
    //   581: ifne -> 617
    //   584: aload_2
    //   585: instanceof java/lang/CharSequence
    //   588: ifne -> 617
    //   591: aload_3
    //   592: bipush #101
    //   594: ldc 'allocation: value not a constant symbol or string'
    //   596: invokevirtual error : (CLjava/lang/String;)V
    //   599: iload #6
    //   601: istore #7
    //   603: aload #11
    //   605: astore_2
    //   606: iload #5
    //   608: istore #4
    //   610: aload #10
    //   612: astore #9
    //   614: goto -> 229
    //   617: aload_1
    //   618: getfield nameDecl : Lgnu/expr/Declaration;
    //   621: ifnonnull -> 650
    //   624: aload_3
    //   625: bipush #101
    //   627: ldc 'allocation: not allowed for anonymous function'
    //   629: invokevirtual error : (CLjava/lang/String;)V
    //   632: iload #6
    //   634: istore #7
    //   636: aload #11
    //   638: astore_2
    //   639: iload #5
    //   641: istore #4
    //   643: aload #10
    //   645: astore #9
    //   647: goto -> 229
    //   650: aload_2
    //   651: invokevirtual toString : ()Ljava/lang/String;
    //   654: astore #9
    //   656: ldc 'class'
    //   658: aload #9
    //   660: invokevirtual equals : (Ljava/lang/Object;)Z
    //   663: ifne -> 676
    //   666: ldc 'static'
    //   668: aload #9
    //   670: invokevirtual equals : (Ljava/lang/Object;)Z
    //   673: ifeq -> 737
    //   676: sipush #2048
    //   679: istore #4
    //   681: aload #10
    //   683: ifnull -> 727
    //   686: aload #9
    //   688: ifnull -> 727
    //   691: aload_3
    //   692: bipush #101
    //   694: new java/lang/StringBuilder
    //   697: dup
    //   698: invokespecial <init> : ()V
    //   701: ldc 'duplicate allocation specifiers - '
    //   703: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   706: aload #10
    //   708: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   711: ldc ' and '
    //   713: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   716: aload #9
    //   718: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   721: invokevirtual toString : ()Ljava/lang/String;
    //   724: invokevirtual error : (CLjava/lang/String;)V
    //   727: iload #6
    //   729: istore #7
    //   731: aload #11
    //   733: astore_2
    //   734: goto -> 229
    //   737: ldc 'instance'
    //   739: aload #9
    //   741: invokevirtual equals : (Ljava/lang/Object;)Z
    //   744: ifeq -> 755
    //   747: sipush #4096
    //   750: istore #4
    //   752: goto -> 681
    //   755: aload_3
    //   756: bipush #101
    //   758: ldc 'unknown allocation specifier'
    //   760: invokevirtual error : (CLjava/lang/String;)V
    //   763: iload #5
    //   765: istore #4
    //   767: goto -> 681
    //   770: aload #12
    //   772: getstatic kawa/standard/object.throwsKeyword : Lgnu/expr/Keyword;
    //   775: if_acmpne -> 927
    //   778: aload #13
    //   780: invokevirtual getCar : ()Ljava/lang/Object;
    //   783: astore_2
    //   784: aload_2
    //   785: invokestatic listLength : (Ljava/lang/Object;)I
    //   788: istore #7
    //   790: iload #7
    //   792: ifge -> 822
    //   795: aload_3
    //   796: bipush #101
    //   798: ldc_w 'throws: not followed by a list'
    //   801: invokevirtual error : (CLjava/lang/String;)V
    //   804: iload #6
    //   806: istore #7
    //   808: aload #11
    //   810: astore_2
    //   811: iload #5
    //   813: istore #4
    //   815: aload #10
    //   817: astore #9
    //   819: goto -> 229
    //   822: iload #7
    //   824: anewarray gnu/expr/Expression
    //   827: astore #12
    //   829: iconst_0
    //   830: istore #4
    //   832: iload #4
    //   834: iload #7
    //   836: if_icmpge -> 903
    //   839: aload_2
    //   840: instanceof kawa/lang/SyntaxForm
    //   843: ifeq -> 863
    //   846: aload_2
    //   847: checkcast kawa/lang/SyntaxForm
    //   850: astore #9
    //   852: aload #9
    //   854: invokeinterface getDatum : ()Ljava/lang/Object;
    //   859: astore_2
    //   860: goto -> 839
    //   863: aload_2
    //   864: checkcast gnu/lists/Pair
    //   867: astore_2
    //   868: aload #12
    //   870: iload #4
    //   872: aload_3
    //   873: aload_2
    //   874: aload #9
    //   876: invokevirtual rewrite_car : (Lgnu/lists/Pair;Lkawa/lang/SyntaxForm;)Lgnu/expr/Expression;
    //   879: aastore
    //   880: aload #12
    //   882: iload #4
    //   884: aaload
    //   885: aload_2
    //   886: invokestatic setLine : (Lgnu/expr/Expression;Ljava/lang/Object;)V
    //   889: aload_2
    //   890: invokevirtual getCdr : ()Ljava/lang/Object;
    //   893: astore_2
    //   894: iload #4
    //   896: iconst_1
    //   897: iadd
    //   898: istore #4
    //   900: goto -> 832
    //   903: aload_1
    //   904: aload #12
    //   906: invokevirtual setExceptions : ([Lgnu/expr/Expression;)V
    //   909: iload #6
    //   911: istore #7
    //   913: aload #11
    //   915: astore_2
    //   916: iload #5
    //   918: istore #4
    //   920: aload #10
    //   922: astore #9
    //   924: goto -> 229
    //   927: aload #12
    //   929: getstatic kawa/lang/Lambda.nameKeyword : Lgnu/expr/Keyword;
    //   932: if_acmpne -> 1001
    //   935: aload_3
    //   936: aload #13
    //   938: aload #9
    //   940: invokevirtual rewrite_car : (Lgnu/lists/Pair;Lkawa/lang/SyntaxForm;)Lgnu/expr/Expression;
    //   943: astore #12
    //   945: iload #6
    //   947: istore #7
    //   949: aload #11
    //   951: astore_2
    //   952: iload #5
    //   954: istore #4
    //   956: aload #10
    //   958: astore #9
    //   960: aload #12
    //   962: instanceof gnu/expr/QuoteExp
    //   965: ifeq -> 229
    //   968: aload_1
    //   969: aload #12
    //   971: checkcast gnu/expr/QuoteExp
    //   974: invokevirtual getValue : ()Ljava/lang/Object;
    //   977: invokevirtual toString : ()Ljava/lang/String;
    //   980: invokevirtual setName : (Ljava/lang/String;)V
    //   983: iload #6
    //   985: istore #7
    //   987: aload #11
    //   989: astore_2
    //   990: iload #5
    //   992: istore #4
    //   994: aload #10
    //   996: astore #9
    //   998: goto -> 229
    //   1001: aload_3
    //   1002: bipush #119
    //   1004: new java/lang/StringBuilder
    //   1007: dup
    //   1008: invokespecial <init> : ()V
    //   1011: ldc_w 'unknown procedure property '
    //   1014: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1017: aload #12
    //   1019: invokevirtual append : (Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   1022: invokevirtual toString : ()Ljava/lang/String;
    //   1025: invokevirtual error : (CLjava/lang/String;)V
    //   1028: iload #6
    //   1030: istore #7
    //   1032: aload #11
    //   1034: astore_2
    //   1035: iload #5
    //   1037: istore #4
    //   1039: aload #10
    //   1041: astore #9
    //   1043: goto -> 229
  }
  
  public void rewriteBody(LambdaExp paramLambdaExp, Object paramObject, Translator paramTranslator) {
    // Byte code:
    //   0: iconst_0
    //   1: istore #5
    //   3: aload_3
    //   4: getfield curMethodLambda : Lgnu/expr/LambdaExp;
    //   7: ifnonnull -> 35
    //   10: aload_1
    //   11: getfield nameDecl : Lgnu/expr/Declaration;
    //   14: ifnull -> 35
    //   17: aload_3
    //   18: invokevirtual getModule : ()Lgnu/expr/ModuleExp;
    //   21: ldc_w 131072
    //   24: invokevirtual getFlag : (I)Z
    //   27: ifeq -> 35
    //   30: aload_3
    //   31: aload_1
    //   32: putfield curMethodLambda : Lgnu/expr/LambdaExp;
    //   35: aload_3
    //   36: invokevirtual currentScope : ()Lgnu/expr/ScopeExp;
    //   39: pop
    //   40: aload_3
    //   41: aload_1
    //   42: invokevirtual pushScope : (Lgnu/expr/ScopeExp;)V
    //   45: aconst_null
    //   46: astore #12
    //   48: aload_1
    //   49: getfield keywords : [Lgnu/expr/Keyword;
    //   52: ifnonnull -> 281
    //   55: iconst_0
    //   56: istore #4
    //   58: aload_1
    //   59: getfield defaultArgs : [Lgnu/expr/Expression;
    //   62: ifnonnull -> 291
    //   65: iconst_0
    //   66: istore #4
    //   68: iconst_0
    //   69: istore #7
    //   71: iconst_0
    //   72: istore #6
    //   74: aload_1
    //   75: invokevirtual firstDecl : ()Lgnu/expr/Declaration;
    //   78: astore #10
    //   80: aload #10
    //   82: ifnull -> 304
    //   85: aload #10
    //   87: astore #11
    //   89: iload #5
    //   91: istore #8
    //   93: aload #10
    //   95: invokevirtual isAlias : ()Z
    //   98: ifeq -> 137
    //   101: aload #10
    //   103: invokestatic getOriginalRef : (Lgnu/expr/Declaration;)Lgnu/expr/ReferenceExp;
    //   106: invokevirtual getBinding : ()Lgnu/expr/Declaration;
    //   109: astore #11
    //   111: aload_1
    //   112: aload #12
    //   114: aload #11
    //   116: invokevirtual replaceFollowing : (Lgnu/expr/Declaration;Lgnu/expr/Declaration;)V
    //   119: aload #11
    //   121: aload_1
    //   122: putfield context : Lgnu/expr/ScopeExp;
    //   125: aload_3
    //   126: aload #10
    //   128: invokevirtual pushRenamedAlias : (Lgnu/expr/Declaration;)V
    //   131: iload #5
    //   133: iconst_1
    //   134: iadd
    //   135: istore #8
    //   137: aload #11
    //   139: invokevirtual getTypeExp : ()Lgnu/expr/Expression;
    //   142: astore #10
    //   144: aload #10
    //   146: instanceof gnu/expr/LangExp
    //   149: ifeq -> 172
    //   152: aload #11
    //   154: aload_3
    //   155: aload #10
    //   157: checkcast gnu/expr/LangExp
    //   160: invokevirtual getLangValue : ()Ljava/lang/Object;
    //   163: checkcast gnu/lists/Pair
    //   166: invokevirtual exp2Type : (Lgnu/lists/Pair;)Lgnu/bytecode/Type;
    //   169: invokevirtual setType : (Lgnu/bytecode/Type;)V
    //   172: aload #11
    //   174: astore #12
    //   176: iload #6
    //   178: istore #9
    //   180: iload #7
    //   182: aload_1
    //   183: getfield min_args : I
    //   186: if_icmplt -> 248
    //   189: iload #7
    //   191: aload_1
    //   192: getfield min_args : I
    //   195: iload #4
    //   197: iadd
    //   198: if_icmplt -> 224
    //   201: aload_1
    //   202: getfield max_args : I
    //   205: ifge -> 224
    //   208: iload #6
    //   210: istore #9
    //   212: iload #7
    //   214: aload_1
    //   215: getfield min_args : I
    //   218: iload #4
    //   220: iadd
    //   221: if_icmpeq -> 248
    //   224: aload_1
    //   225: getfield defaultArgs : [Lgnu/expr/Expression;
    //   228: iload #6
    //   230: aload_3
    //   231: aload_1
    //   232: getfield defaultArgs : [Lgnu/expr/Expression;
    //   235: iload #6
    //   237: aaload
    //   238: invokevirtual rewrite : (Ljava/lang/Object;)Lgnu/expr/Expression;
    //   241: aastore
    //   242: iload #6
    //   244: iconst_1
    //   245: iadd
    //   246: istore #9
    //   248: iload #7
    //   250: iconst_1
    //   251: iadd
    //   252: istore #7
    //   254: aload_3
    //   255: getfield lexical : Lgnu/expr/NameLookup;
    //   258: aload #11
    //   260: invokevirtual push : (Lgnu/expr/Declaration;)V
    //   263: aload #11
    //   265: invokevirtual nextDecl : ()Lgnu/expr/Declaration;
    //   268: astore #10
    //   270: iload #8
    //   272: istore #5
    //   274: iload #9
    //   276: istore #6
    //   278: goto -> 80
    //   281: aload_1
    //   282: getfield keywords : [Lgnu/expr/Keyword;
    //   285: arraylength
    //   286: istore #4
    //   288: goto -> 58
    //   291: aload_1
    //   292: getfield defaultArgs : [Lgnu/expr/Expression;
    //   295: arraylength
    //   296: iload #4
    //   298: isub
    //   299: istore #4
    //   301: goto -> 68
    //   304: aload_1
    //   305: invokevirtual isClassMethod : ()Z
    //   308: ifeq -> 339
    //   311: aload_1
    //   312: getfield nameDecl : Lgnu/expr/Declaration;
    //   315: ldc2_w 2048
    //   318: invokevirtual getFlag : (J)Z
    //   321: ifne -> 339
    //   324: aload_1
    //   325: aconst_null
    //   326: new gnu/expr/Declaration
    //   329: dup
    //   330: getstatic gnu/expr/ThisExp.THIS_NAME : Ljava/lang/String;
    //   333: invokespecial <init> : (Ljava/lang/Object;)V
    //   336: invokevirtual add : (Lgnu/expr/Declaration;Lgnu/expr/Declaration;)V
    //   339: aload_3
    //   340: getfield curLambda : Lgnu/expr/LambdaExp;
    //   343: astore #11
    //   345: aload_3
    //   346: aload_1
    //   347: putfield curLambda : Lgnu/expr/LambdaExp;
    //   350: aload_1
    //   351: getfield returnType : Lgnu/bytecode/Type;
    //   354: astore #10
    //   356: aload_1
    //   357: getfield body : Lgnu/expr/Expression;
    //   360: instanceof gnu/expr/LangExp
    //   363: ifeq -> 415
    //   366: aload_1
    //   367: getfield body : Lgnu/expr/Expression;
    //   370: checkcast gnu/expr/LangExp
    //   373: invokevirtual getLangValue : ()Ljava/lang/Object;
    //   376: checkcast [Ljava/lang/Object;
    //   379: checkcast [Ljava/lang/Object;
    //   382: astore #10
    //   384: aload_3
    //   385: aload #10
    //   387: iconst_0
    //   388: aaload
    //   389: checkcast gnu/lists/Pair
    //   392: aload #10
    //   394: iconst_1
    //   395: aaload
    //   396: checkcast kawa/lang/SyntaxForm
    //   399: invokevirtual rewrite_car : (Lgnu/lists/Pair;Lkawa/lang/SyntaxForm;)Lgnu/expr/Expression;
    //   402: astore #10
    //   404: aload_3
    //   405: invokevirtual getLanguage : ()Lgnu/expr/Language;
    //   408: aload #10
    //   410: invokevirtual getTypeFor : (Lgnu/expr/Expression;)Lgnu/bytecode/Type;
    //   413: astore #10
    //   415: aload_1
    //   416: aload_3
    //   417: aload_2
    //   418: invokevirtual rewrite_body : (Ljava/lang/Object;)Lgnu/expr/Expression;
    //   421: putfield body : Lgnu/expr/Expression;
    //   424: aload_3
    //   425: aload #11
    //   427: putfield curLambda : Lgnu/expr/LambdaExp;
    //   430: aload_1
    //   431: getfield body : Lgnu/expr/Expression;
    //   434: instanceof gnu/expr/BeginExp
    //   437: ifeq -> 592
    //   440: aload_1
    //   441: getfield body : Lgnu/expr/Expression;
    //   444: checkcast gnu/expr/BeginExp
    //   447: invokevirtual getExpressions : ()[Lgnu/expr/Expression;
    //   450: astore_2
    //   451: aload_2
    //   452: arraylength
    //   453: istore #4
    //   455: iload #4
    //   457: iconst_1
    //   458: if_icmple -> 592
    //   461: aload_2
    //   462: iconst_0
    //   463: aaload
    //   464: instanceof gnu/expr/ReferenceExp
    //   467: ifne -> 494
    //   470: aload_2
    //   471: iconst_0
    //   472: aaload
    //   473: invokevirtual valueIfConstant : ()Ljava/lang/Object;
    //   476: astore #11
    //   478: aload #11
    //   480: instanceof gnu/bytecode/Type
    //   483: ifne -> 494
    //   486: aload #11
    //   488: instanceof java/lang/Class
    //   491: ifeq -> 592
    //   494: aload_2
    //   495: iconst_0
    //   496: aaload
    //   497: astore #10
    //   499: iload #4
    //   501: iconst_1
    //   502: isub
    //   503: istore #4
    //   505: iload #4
    //   507: iconst_1
    //   508: if_icmpne -> 563
    //   511: aload_1
    //   512: aload_2
    //   513: iconst_1
    //   514: aaload
    //   515: putfield body : Lgnu/expr/Expression;
    //   518: aload_1
    //   519: aload #10
    //   521: aload_3
    //   522: invokevirtual getLanguage : ()Lgnu/expr/Language;
    //   525: invokevirtual setCoercedReturnValue : (Lgnu/expr/Expression;Lgnu/expr/Language;)V
    //   528: aload_3
    //   529: aload_1
    //   530: invokevirtual pop : (Lgnu/expr/ScopeExp;)V
    //   533: aload_1
    //   534: invokevirtual countDecls : ()I
    //   537: pop
    //   538: aload_3
    //   539: iload #5
    //   541: invokevirtual popRenamedAlias : (I)V
    //   544: aload_1
    //   545: invokevirtual countDecls : ()I
    //   548: pop
    //   549: aload_3
    //   550: getfield curMethodLambda : Lgnu/expr/LambdaExp;
    //   553: aload_1
    //   554: if_acmpne -> 562
    //   557: aload_3
    //   558: aconst_null
    //   559: putfield curMethodLambda : Lgnu/expr/LambdaExp;
    //   562: return
    //   563: iload #4
    //   565: anewarray gnu/expr/Expression
    //   568: astore #11
    //   570: aload_2
    //   571: iconst_1
    //   572: aload #11
    //   574: iconst_0
    //   575: iload #4
    //   577: invokestatic arraycopy : (Ljava/lang/Object;ILjava/lang/Object;II)V
    //   580: aload_1
    //   581: aload #11
    //   583: invokestatic canonicalize : ([Lgnu/expr/Expression;)Lgnu/expr/Expression;
    //   586: putfield body : Lgnu/expr/Expression;
    //   589: goto -> 518
    //   592: aload_1
    //   593: aload #10
    //   595: invokevirtual setCoercedReturnType : (Lgnu/bytecode/Type;)V
    //   598: goto -> 528
  }
  
  public Expression rewriteForm(Pair paramPair, Translator paramTranslator) {
    Expression expression = rewrite(paramPair.getCdr(), paramTranslator);
    Translator.setLine(expression, paramPair);
    return expression;
  }
  
  public void rewriteFormals(LambdaExp paramLambdaExp, Object paramObject, Translator paramTranslator, TemplateScope paramTemplateScope) {
    if (paramLambdaExp.getSymbol() == null) {
      String str = paramLambdaExp.getFileName();
      int m = paramLambdaExp.getLineNumber();
      if (str != null && m > 0)
        paramLambdaExp.setSourceLocation(str, m); 
    } 
    Object object = paramObject;
    int j = -1;
    int k = -1;
    int i = -1;
    while (true) {
      Object object2 = object;
      if (object instanceof SyntaxForm)
        object2 = ((SyntaxForm)object).getDatum(); 
      if (!(object2 instanceof Pair)) {
        if (object2 instanceof gnu.mapping.Symbol) {
          if (j >= 0 || i >= 0 || k >= 0) {
            paramTranslator.syntaxError("dotted rest-arg after " + this.optionalKeyword + ", " + this.restKeyword + ", or " + this.keyKeyword);
            continue;
          } 
          k = 1;
        } else if (object2 != LList.Empty) {
          paramTranslator.syntaxError("misformed formals in lambda");
          return;
        } 
      } else {
        object2 = object2;
        Object object4 = object2.getCar();
        object = object4;
        if (object4 instanceof SyntaxForm)
          object = ((SyntaxForm)object4).getDatum(); 
        if (object == this.optionalKeyword) {
          if (j >= 0) {
            paramTranslator.syntaxError("multiple " + this.optionalKeyword + " in parameter list");
            return;
          } 
          if (k >= 0 || i >= 0) {
            paramTranslator.syntaxError(this.optionalKeyword.toString() + " after " + this.restKeyword + " or " + this.keyKeyword);
            return;
          } 
          j = 0;
          object = object2;
        } else if (object == this.restKeyword) {
          if (k >= 0) {
            paramTranslator.syntaxError("multiple " + this.restKeyword + " in parameter list");
            return;
          } 
          if (i >= 0) {
            paramTranslator.syntaxError(this.restKeyword.toString() + " after " + this.keyKeyword);
            return;
          } 
          k = 0;
          object = object2;
        } else if (object == this.keyKeyword) {
          if (i >= 0) {
            paramTranslator.syntaxError("multiple " + this.keyKeyword + " in parameter list");
            return;
          } 
          i = 0;
          object = object2;
        } else if (paramTranslator.matches(object2.getCar(), "::") && object2.getCdr() instanceof Pair) {
          object = object2.getCdr();
        } else if (i >= 0) {
          i++;
          object = object2;
        } else if (k >= 0) {
          k++;
          object = object2;
        } else if (j >= 0) {
          j++;
          object = object2;
        } else {
          paramLambdaExp.min_args++;
          object = object2;
        } 
        continue;
      } 
      if (k > 1) {
        paramTranslator.syntaxError("multiple " + this.restKeyword + " parameters");
        return;
      } 
      int m = j;
      if (j < 0)
        m = 0; 
      j = k;
      if (k < 0)
        j = 0; 
      k = i;
      if (i < 0)
        k = 0; 
      if (j > 0) {
        paramLambdaExp.max_args = -1;
      } else {
        paramLambdaExp.max_args = paramLambdaExp.min_args + m + k * 2;
      } 
      if (m + k > 0)
        paramLambdaExp.defaultArgs = new Expression[m + k]; 
      if (k > 0)
        paramLambdaExp.keywords = new Keyword[k]; 
      object = paramObject;
      k = 0;
      i = 0;
      Object object3 = null;
      paramObject = paramTemplateScope;
      Object object1 = object;
      while (true) {
        object2 = object1;
        object1 = SYNTHETIC_LOCAL_VARIABLE_12.getCdr();
        k = j;
      } 
      break;
      object.getCdr();
      object = object.getCdr();
    } 
  }
  
  public void setKeywords(Object paramObject1, Object paramObject2, Object paramObject3) {
    this.optionalKeyword = paramObject1;
    this.restKeyword = paramObject2;
    this.keyKeyword = paramObject3;
  }
  
  public Object skipAttrs(LambdaExp paramLambdaExp, Object paramObject, Translator paramTranslator) {
    while (true) {
      if (paramObject instanceof Pair) {
        Pair pair = (Pair)paramObject;
        if (pair.getCdr() instanceof Pair) {
          Object object = pair.getCar();
          if (!paramTranslator.matches(object, "::") && !(object instanceof Keyword))
            return paramObject; 
          paramObject = ((Pair)pair.getCdr()).getCdr();
          continue;
        } 
      } 
      return paramObject;
    } 
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/kawa/lang/Lambda.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */