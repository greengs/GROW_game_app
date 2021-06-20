package kawa.standard;

import gnu.bytecode.Type;
import gnu.expr.BeginExp;
import gnu.expr.ClassExp;
import gnu.expr.Compilation;
import gnu.expr.Declaration;
import gnu.expr.Expression;
import gnu.expr.Keyword;
import gnu.expr.LambdaExp;
import gnu.expr.ObjectExp;
import gnu.expr.QuoteExp;
import gnu.expr.ScopeExp;
import gnu.expr.SetExp;
import gnu.expr.ThisExp;
import gnu.lists.FString;
import gnu.lists.LList;
import gnu.lists.Pair;
import gnu.mapping.Namespace;
import gnu.mapping.Symbol;
import gnu.text.SourceLocator;
import java.util.Vector;
import kawa.lang.Lambda;
import kawa.lang.Syntax;
import kawa.lang.SyntaxForm;
import kawa.lang.Translator;

public class object extends Syntax {
  public static final Keyword accessKeyword;
  
  public static final Keyword allocationKeyword;
  
  public static final Keyword classNameKeyword;
  
  static final Symbol coloncolon;
  
  static final Keyword initKeyword;
  
  static final Keyword init_formKeyword;
  
  static final Keyword init_keywordKeyword;
  
  static final Keyword init_valueKeyword;
  
  static final Keyword initformKeyword;
  
  public static final Keyword interfaceKeyword;
  
  public static final object objectSyntax = new object(SchemeCompilation.lambda);
  
  public static final Keyword throwsKeyword;
  
  static final Keyword typeKeyword;
  
  Lambda lambda;
  
  static {
    objectSyntax.setName("object");
    accessKeyword = Keyword.make("access");
    classNameKeyword = Keyword.make("class-name");
    interfaceKeyword = Keyword.make("interface");
    throwsKeyword = Keyword.make("throws");
    typeKeyword = Keyword.make("type");
    allocationKeyword = Keyword.make("allocation");
    initKeyword = Keyword.make("init");
    initformKeyword = Keyword.make("initform");
    init_formKeyword = Keyword.make("init-form");
    init_valueKeyword = Keyword.make("init-value");
    init_keywordKeyword = Keyword.make("init-keyword");
    coloncolon = Namespace.EmptyNamespace.getSymbol("::");
  }
  
  public object(Lambda paramLambda) {
    this.lambda = paramLambda;
  }
  
  static long addAccessFlags(Object paramObject, long paramLong1, long paramLong2, String paramString, Translator paramTranslator) {
    long l = matchAccess(paramObject, paramTranslator);
    if (l == 0L) {
      paramTranslator.error('e', "unknown access specifier " + paramObject);
      return paramLong1 | l;
    } 
    if (((0xFFFFFFFFFFFFFFFFL ^ paramLong2) & l) != 0L) {
      paramTranslator.error('e', "invalid " + paramString + " access specifier " + paramObject);
      return paramLong1 | l;
    } 
    if ((paramLong1 & l) != 0L)
      paramTranslator.error('w', "duplicate " + paramString + " access specifiers " + paramObject); 
    return paramLong1 | l;
  }
  
  static long matchAccess(Object paramObject, Translator paramTranslator) {
    while (paramObject instanceof SyntaxForm)
      paramObject = ((SyntaxForm)paramObject).getDatum(); 
    Object object1 = paramObject;
    if (paramObject instanceof Pair) {
      object1 = paramObject;
      paramObject = paramTranslator.matchQuoted((Pair)paramObject);
      object1 = paramObject;
      if (paramObject instanceof Pair)
        return matchAccess2((Pair)paramObject, paramTranslator); 
    } 
    return matchAccess1(object1, paramTranslator);
  }
  
  private static long matchAccess1(Object paramObject, Translator paramTranslator) {
    Object object1;
    if (paramObject instanceof Keyword) {
      object1 = ((Keyword)paramObject).getName();
    } else if (paramObject instanceof FString) {
      object1 = ((FString)paramObject).toString();
    } else {
      object1 = paramObject;
      if (paramObject instanceof gnu.mapping.SimpleSymbol)
        object1 = paramObject.toString(); 
    } 
    return "private".equals(object1) ? 16777216L : ("protected".equals(object1) ? 33554432L : ("public".equals(object1) ? 67108864L : ("package".equals(object1) ? 134217728L : ("volatile".equals(object1) ? 2147483648L : ("transient".equals(object1) ? 4294967296L : ("enum".equals(object1) ? 8589934592L : ("final".equals(object1) ? 17179869184L : 0L)))))));
  }
  
  private static long matchAccess2(Pair paramPair, Translator paramTranslator) {
    long l = matchAccess1(paramPair.getCar(), paramTranslator);
    Object object1 = paramPair.getCdr();
    if (object1 == LList.Empty || l == 0L)
      return l; 
    if (object1 instanceof Pair) {
      long l1 = matchAccess2((Pair)object1, paramTranslator);
      if (l1 != 0L)
        return l | l1; 
    } 
    return 0L;
  }
  
  static boolean matches(Object paramObject, String paramString, Translator paramTranslator) {
    boolean bool = false;
    if (paramObject instanceof Keyword) {
      paramObject = ((Keyword)paramObject).getName();
    } else if (paramObject instanceof FString) {
      paramObject = ((FString)paramObject).toString();
    } else {
      boolean bool1 = bool;
      if (paramObject instanceof Pair) {
        paramObject = paramTranslator.matchQuoted((Pair)paramObject);
        bool1 = bool;
        if (paramObject instanceof gnu.mapping.SimpleSymbol) {
          paramObject = paramObject.toString();
        } else {
          return bool1;
        } 
      } else {
        return bool1;
      } 
    } 
    if (paramString != null) {
      boolean bool1 = bool;
      return paramString.equals(paramObject) ? true : bool1;
    } 
    return true;
  }
  
  private static void rewriteInit(Object paramObject, ClassExp paramClassExp, Pair paramPair, Translator paramTranslator, SyntaxForm paramSyntaxForm) {
    boolean bool;
    LambdaExp lambdaExp2;
    if (paramObject instanceof Declaration) {
      bool = ((Declaration)paramObject).getFlag(2048L);
    } else if (paramObject == Boolean.TRUE) {
      bool = true;
    } else {
      bool = false;
    } 
    if (bool) {
      lambdaExp2 = paramClassExp.clinitMethod;
    } else {
      lambdaExp2 = paramClassExp.initMethod;
    } 
    LambdaExp lambdaExp3 = lambdaExp2;
    if (lambdaExp2 == null) {
      lambdaExp3 = new LambdaExp((Expression)new BeginExp());
      lambdaExp3.setClassMethod(true);
      lambdaExp3.setReturnType((Type)Type.voidType);
      if (bool) {
        lambdaExp3.setName("$clinit$");
        paramClassExp.clinitMethod = lambdaExp3;
      } else {
        lambdaExp3.setName("$finit$");
        paramClassExp.initMethod = lambdaExp3;
        lambdaExp3.add(null, new Declaration(ThisExp.THIS_NAME));
      } 
      lambdaExp3.nextSibling = paramClassExp.firstChild;
      paramClassExp.firstChild = lambdaExp3;
    } 
    paramTranslator.push((ScopeExp)lambdaExp3);
    LambdaExp lambdaExp1 = paramTranslator.curMethodLambda;
    paramTranslator.curMethodLambda = lambdaExp3;
    Expression expression = paramTranslator.rewrite_car(paramPair, paramSyntaxForm);
    if (paramObject instanceof Declaration) {
      Declaration declaration = (Declaration)paramObject;
      paramObject = new SetExp(declaration, expression);
      paramObject.setLocation((SourceLocator)declaration);
      declaration.noteValue(null);
    } else {
      paramObject = Compilation.makeCoercion(expression, (Expression)new QuoteExp(Type.voidType));
    } 
    ((BeginExp)lambdaExp3.body).add((Expression)paramObject);
    paramTranslator.curMethodLambda = lambdaExp1;
    paramTranslator.pop((ScopeExp)lambdaExp3);
  }
  
  public void rewriteClassDef(Object[] paramArrayOfObject, Translator paramTranslator) {
    // Byte code:
    //   0: aload_1
    //   1: iconst_0
    //   2: aaload
    //   3: checkcast gnu/expr/ClassExp
    //   6: astore #17
    //   8: aload_1
    //   9: iconst_1
    //   10: aaload
    //   11: astore #6
    //   13: aload_1
    //   14: iconst_2
    //   15: aaload
    //   16: checkcast java/util/Vector
    //   19: astore #18
    //   21: aload_1
    //   22: iconst_3
    //   23: aaload
    //   24: checkcast gnu/expr/LambdaExp
    //   27: astore #12
    //   29: aload_1
    //   30: iconst_4
    //   31: aaload
    //   32: astore #7
    //   34: aload_1
    //   35: iconst_5
    //   36: aaload
    //   37: astore #9
    //   39: aload #17
    //   41: aload #12
    //   43: putfield firstChild : Lgnu/expr/LambdaExp;
    //   46: aload #7
    //   48: invokestatic listLength : (Ljava/lang/Object;)I
    //   51: istore #4
    //   53: iload #4
    //   55: istore_3
    //   56: iload #4
    //   58: ifge -> 72
    //   61: aload_2
    //   62: bipush #101
    //   64: ldc_w 'object superclass specification not a list'
    //   67: invokevirtual error : (CLjava/lang/String;)V
    //   70: iconst_0
    //   71: istore_3
    //   72: iload_3
    //   73: anewarray gnu/expr/Expression
    //   76: astore #8
    //   78: iconst_0
    //   79: istore #4
    //   81: aload #7
    //   83: astore_1
    //   84: iload #4
    //   86: iload_3
    //   87: if_icmpge -> 198
    //   90: aload_1
    //   91: instanceof kawa/lang/SyntaxForm
    //   94: ifeq -> 110
    //   97: aload_1
    //   98: checkcast kawa/lang/SyntaxForm
    //   101: invokeinterface getDatum : ()Ljava/lang/Object;
    //   106: astore_1
    //   107: goto -> 90
    //   110: aload_1
    //   111: checkcast gnu/lists/Pair
    //   114: astore_1
    //   115: aload #8
    //   117: iload #4
    //   119: aload_2
    //   120: aload_1
    //   121: iconst_0
    //   122: invokevirtual rewrite_car : (Lgnu/lists/Pair;Z)Lgnu/expr/Expression;
    //   125: aastore
    //   126: aload #8
    //   128: iload #4
    //   130: aaload
    //   131: instanceof gnu/expr/ReferenceExp
    //   134: ifeq -> 184
    //   137: aload #8
    //   139: iload #4
    //   141: aaload
    //   142: checkcast gnu/expr/ReferenceExp
    //   145: invokevirtual getBinding : ()Lgnu/expr/Declaration;
    //   148: invokestatic followAliases : (Lgnu/expr/Declaration;)Lgnu/expr/Declaration;
    //   151: astore #7
    //   153: aload #7
    //   155: ifnull -> 184
    //   158: aload #7
    //   160: invokevirtual getValue : ()Lgnu/expr/Expression;
    //   163: astore #7
    //   165: aload #7
    //   167: instanceof gnu/expr/ClassExp
    //   170: ifeq -> 184
    //   173: aload #7
    //   175: checkcast gnu/expr/ClassExp
    //   178: ldc_w 131072
    //   181: invokevirtual setFlag : (I)V
    //   184: aload_1
    //   185: invokevirtual getCdr : ()Ljava/lang/Object;
    //   188: astore_1
    //   189: iload #4
    //   191: iconst_1
    //   192: iadd
    //   193: istore #4
    //   195: goto -> 84
    //   198: aload #9
    //   200: ifnull -> 242
    //   203: aload_2
    //   204: aload #9
    //   206: checkcast gnu/lists/Pair
    //   209: iconst_0
    //   210: invokevirtual rewrite_car : (Lgnu/lists/Pair;Z)Lgnu/expr/Expression;
    //   213: invokevirtual valueIfConstant : ()Ljava/lang/Object;
    //   216: astore_1
    //   217: aload_1
    //   218: instanceof java/lang/CharSequence
    //   221: ifeq -> 307
    //   224: aload_1
    //   225: invokevirtual toString : ()Ljava/lang/String;
    //   228: astore_1
    //   229: aload_1
    //   230: invokevirtual length : ()I
    //   233: ifle -> 307
    //   236: aload #17
    //   238: aload_1
    //   239: putfield classNameSpecifier : Ljava/lang/String;
    //   242: aload #17
    //   244: aload #8
    //   246: putfield supers : [Lgnu/expr/Expression;
    //   249: aload #17
    //   251: aload_2
    //   252: invokevirtual setTypes : (Lgnu/expr/Compilation;)V
    //   255: aload #18
    //   257: invokevirtual size : ()I
    //   260: istore #4
    //   262: iconst_0
    //   263: istore_3
    //   264: iload_3
    //   265: iload #4
    //   267: if_icmpge -> 331
    //   270: aload #18
    //   272: iload_3
    //   273: iconst_1
    //   274: iadd
    //   275: invokevirtual elementAt : (I)Ljava/lang/Object;
    //   278: astore_1
    //   279: aload_1
    //   280: ifnull -> 300
    //   283: aload #18
    //   285: iload_3
    //   286: invokevirtual elementAt : (I)Ljava/lang/Object;
    //   289: aload #17
    //   291: aload_1
    //   292: checkcast gnu/lists/Pair
    //   295: aload_2
    //   296: aconst_null
    //   297: invokestatic rewriteInit : (Ljava/lang/Object;Lgnu/expr/ClassExp;Lgnu/lists/Pair;Lkawa/lang/Translator;Lkawa/lang/SyntaxForm;)V
    //   300: iload_3
    //   301: iconst_2
    //   302: iadd
    //   303: istore_3
    //   304: goto -> 264
    //   307: aload_2
    //   308: aload #9
    //   310: invokevirtual pushPositionOf : (Ljava/lang/Object;)Ljava/lang/Object;
    //   313: astore_1
    //   314: aload_2
    //   315: bipush #101
    //   317: ldc_w 'class-name specifier must be a non-empty string literal'
    //   320: invokevirtual error : (CLjava/lang/String;)V
    //   323: aload_2
    //   324: aload_1
    //   325: invokevirtual popPositionOf : (Ljava/lang/Object;)V
    //   328: goto -> 242
    //   331: aload_2
    //   332: aload #17
    //   334: invokevirtual push : (Lgnu/expr/ScopeExp;)V
    //   337: iconst_0
    //   338: istore_3
    //   339: aconst_null
    //   340: astore #9
    //   342: aload #6
    //   344: astore_1
    //   345: aload_1
    //   346: getstatic gnu/lists/LList.Empty : Lgnu/lists/LList;
    //   349: if_acmpeq -> 1184
    //   352: aload_1
    //   353: instanceof kawa/lang/SyntaxForm
    //   356: ifeq -> 376
    //   359: aload_1
    //   360: checkcast kawa/lang/SyntaxForm
    //   363: astore #9
    //   365: aload #9
    //   367: invokeinterface getDatum : ()Ljava/lang/Object;
    //   372: astore_1
    //   373: goto -> 352
    //   376: aload_1
    //   377: checkcast gnu/lists/Pair
    //   380: astore #7
    //   382: aload_2
    //   383: aload #7
    //   385: invokevirtual pushPositionOf : (Ljava/lang/Object;)Ljava/lang/Object;
    //   388: astore #19
    //   390: aload #7
    //   392: invokevirtual getCar : ()Ljava/lang/Object;
    //   395: astore #6
    //   397: aload #9
    //   399: astore_1
    //   400: aload #6
    //   402: instanceof kawa/lang/SyntaxForm
    //   405: ifeq -> 425
    //   408: aload #6
    //   410: checkcast kawa/lang/SyntaxForm
    //   413: astore_1
    //   414: aload_1
    //   415: invokeinterface getDatum : ()Ljava/lang/Object;
    //   420: astore #6
    //   422: goto -> 400
    //   425: aload #7
    //   427: invokevirtual getCdr : ()Ljava/lang/Object;
    //   430: astore #14
    //   432: aload #6
    //   434: instanceof gnu/expr/Keyword
    //   437: ifeq -> 466
    //   440: aload #14
    //   442: instanceof gnu/lists/Pair
    //   445: ifeq -> 466
    //   448: aload #14
    //   450: checkcast gnu/lists/Pair
    //   453: invokevirtual getCdr : ()Ljava/lang/Object;
    //   456: astore_1
    //   457: aload_2
    //   458: aload #19
    //   460: invokevirtual popPositionOf : (Ljava/lang/Object;)V
    //   463: goto -> 345
    //   466: aload #6
    //   468: checkcast gnu/lists/Pair
    //   471: astore #8
    //   473: aload #8
    //   475: invokevirtual getCar : ()Ljava/lang/Object;
    //   478: astore #7
    //   480: aload_1
    //   481: astore #6
    //   483: aload #7
    //   485: instanceof kawa/lang/SyntaxForm
    //   488: ifeq -> 510
    //   491: aload #7
    //   493: checkcast kawa/lang/SyntaxForm
    //   496: astore #6
    //   498: aload #6
    //   500: invokeinterface getDatum : ()Ljava/lang/Object;
    //   505: astore #7
    //   507: goto -> 483
    //   510: aload #7
    //   512: instanceof java/lang/String
    //   515: ifne -> 534
    //   518: aload #7
    //   520: instanceof gnu/mapping/Symbol
    //   523: ifne -> 534
    //   526: aload #7
    //   528: instanceof gnu/expr/Keyword
    //   531: ifeq -> 999
    //   534: aconst_null
    //   535: astore #13
    //   537: iconst_0
    //   538: istore #4
    //   540: aload #7
    //   542: instanceof gnu/expr/Keyword
    //   545: ifeq -> 588
    //   548: aload #8
    //   550: astore #6
    //   552: goto -> 1237
    //   555: aload #6
    //   557: getstatic gnu/lists/LList.Empty : Lgnu/lists/LList;
    //   560: if_acmpeq -> 882
    //   563: aload #6
    //   565: instanceof kawa/lang/SyntaxForm
    //   568: ifeq -> 598
    //   571: aload #6
    //   573: checkcast kawa/lang/SyntaxForm
    //   576: astore_1
    //   577: aload_1
    //   578: invokeinterface getDatum : ()Ljava/lang/Object;
    //   583: astore #6
    //   585: goto -> 563
    //   588: aload #8
    //   590: invokevirtual getCdr : ()Ljava/lang/Object;
    //   593: astore #6
    //   595: goto -> 1237
    //   598: aload #6
    //   600: checkcast gnu/lists/Pair
    //   603: astore #6
    //   605: aload #6
    //   607: invokevirtual getCar : ()Ljava/lang/Object;
    //   610: astore #10
    //   612: aload #10
    //   614: instanceof kawa/lang/SyntaxForm
    //   617: ifeq -> 635
    //   620: aload #10
    //   622: checkcast kawa/lang/SyntaxForm
    //   625: invokeinterface getDatum : ()Ljava/lang/Object;
    //   630: astore #10
    //   632: goto -> 612
    //   635: aload_2
    //   636: aload #6
    //   638: invokevirtual pushPositionOf : (Ljava/lang/Object;)Ljava/lang/Object;
    //   641: astore #20
    //   643: aload #6
    //   645: invokevirtual getCdr : ()Ljava/lang/Object;
    //   648: astore #11
    //   650: aload #10
    //   652: getstatic kawa/standard/object.coloncolon : Lgnu/mapping/Symbol;
    //   655: if_acmpeq -> 666
    //   658: aload #10
    //   660: instanceof gnu/expr/Keyword
    //   663: ifeq -> 789
    //   666: aload #11
    //   668: instanceof gnu/lists/Pair
    //   671: ifeq -> 789
    //   674: iload #4
    //   676: iconst_1
    //   677: iadd
    //   678: istore #5
    //   680: aload #11
    //   682: checkcast gnu/lists/Pair
    //   685: astore #16
    //   687: aload #16
    //   689: invokevirtual getCar : ()Ljava/lang/Object;
    //   692: astore #11
    //   694: aload #16
    //   696: invokevirtual getCdr : ()Ljava/lang/Object;
    //   699: astore #15
    //   701: aload #10
    //   703: getstatic kawa/standard/object.coloncolon : Lgnu/mapping/Symbol;
    //   706: if_acmpeq -> 1246
    //   709: aload #10
    //   711: getstatic kawa/standard/object.typeKeyword : Lgnu/expr/Keyword;
    //   714: if_acmpne -> 742
    //   717: goto -> 1246
    //   720: aload_2
    //   721: aload #20
    //   723: invokevirtual popPositionOf : (Ljava/lang/Object;)V
    //   726: aload #11
    //   728: astore #13
    //   730: goto -> 555
    //   733: astore_1
    //   734: aload_2
    //   735: aload #19
    //   737: invokevirtual popPositionOf : (Ljava/lang/Object;)V
    //   740: aload_1
    //   741: athrow
    //   742: aload #10
    //   744: getstatic kawa/standard/object.initKeyword : Lgnu/expr/Keyword;
    //   747: if_acmpeq -> 1257
    //   750: aload #10
    //   752: getstatic kawa/standard/object.initformKeyword : Lgnu/expr/Keyword;
    //   755: if_acmpeq -> 1257
    //   758: aload #10
    //   760: getstatic kawa/standard/object.init_formKeyword : Lgnu/expr/Keyword;
    //   763: if_acmpeq -> 1257
    //   766: aload #15
    //   768: astore #6
    //   770: iload #5
    //   772: istore #4
    //   774: aload #13
    //   776: astore #11
    //   778: aload #10
    //   780: getstatic kawa/standard/object.init_valueKeyword : Lgnu/expr/Keyword;
    //   783: if_acmpne -> 720
    //   786: goto -> 1257
    //   789: aload #11
    //   791: getstatic gnu/lists/LList.Empty : Lgnu/lists/LList;
    //   794: if_acmpne -> 820
    //   797: aload #7
    //   799: ifnonnull -> 820
    //   802: aload #6
    //   804: astore #7
    //   806: aload_1
    //   807: astore #8
    //   809: aload #11
    //   811: astore #6
    //   813: aload #13
    //   815: astore #11
    //   817: goto -> 720
    //   820: aload #11
    //   822: instanceof gnu/lists/Pair
    //   825: ifeq -> 882
    //   828: iload #4
    //   830: ifne -> 882
    //   833: aload #7
    //   835: ifnonnull -> 882
    //   838: aload #13
    //   840: ifnonnull -> 882
    //   843: aload #11
    //   845: checkcast gnu/lists/Pair
    //   848: astore #6
    //   850: aload #6
    //   852: invokevirtual getCdr : ()Ljava/lang/Object;
    //   855: getstatic gnu/lists/LList.Empty : Lgnu/lists/LList;
    //   858: if_acmpne -> 882
    //   861: aload #6
    //   863: astore #7
    //   865: aload_1
    //   866: astore #8
    //   868: aload #6
    //   870: invokevirtual getCdr : ()Ljava/lang/Object;
    //   873: astore #6
    //   875: aload #10
    //   877: astore #11
    //   879: goto -> 720
    //   882: aload #12
    //   884: astore_1
    //   885: iload_3
    //   886: istore #4
    //   888: aload #7
    //   890: ifnull -> 965
    //   893: iload_3
    //   894: iconst_1
    //   895: iadd
    //   896: istore #5
    //   898: aload #18
    //   900: iload_3
    //   901: invokevirtual elementAt : (I)Ljava/lang/Object;
    //   904: astore #6
    //   906: aload #6
    //   908: instanceof gnu/expr/Declaration
    //   911: ifeq -> 983
    //   914: aload #6
    //   916: checkcast gnu/expr/Declaration
    //   919: ldc2_w 2048
    //   922: invokevirtual getFlag : (J)Z
    //   925: pop
    //   926: iload #5
    //   928: iconst_1
    //   929: iadd
    //   930: istore_3
    //   931: aload #12
    //   933: astore_1
    //   934: iload_3
    //   935: istore #4
    //   937: aload #18
    //   939: iload #5
    //   941: invokevirtual elementAt : (I)Ljava/lang/Object;
    //   944: ifnonnull -> 965
    //   947: aload #6
    //   949: aload #17
    //   951: aload #7
    //   953: aload_2
    //   954: aload #8
    //   956: invokestatic rewriteInit : (Ljava/lang/Object;Lgnu/expr/ClassExp;Lgnu/lists/Pair;Lkawa/lang/Translator;Lkawa/lang/SyntaxForm;)V
    //   959: iload_3
    //   960: istore #4
    //   962: aload #12
    //   964: astore_1
    //   965: aload_2
    //   966: aload #19
    //   968: invokevirtual popPositionOf : (Ljava/lang/Object;)V
    //   971: aload_1
    //   972: astore #12
    //   974: iload #4
    //   976: istore_3
    //   977: aload #14
    //   979: astore_1
    //   980: goto -> 345
    //   983: getstatic java/lang/Boolean.TRUE : Ljava/lang/Boolean;
    //   986: astore_1
    //   987: aload #6
    //   989: aload_1
    //   990: if_acmpne -> 996
    //   993: goto -> 926
    //   996: goto -> 926
    //   999: aload #7
    //   1001: instanceof gnu/lists/Pair
    //   1004: ifeq -> 1167
    //   1007: aload_2
    //   1008: invokevirtual currentScope : ()Lgnu/expr/ScopeExp;
    //   1011: astore #10
    //   1013: aload_1
    //   1014: ifnull -> 1027
    //   1017: aload_2
    //   1018: aload_1
    //   1019: invokeinterface getScope : ()Lkawa/lang/TemplateScope;
    //   1024: invokevirtual setCurrentScope : (Lgnu/expr/ScopeExp;)V
    //   1027: ldc_w '*init*'
    //   1030: aload #12
    //   1032: invokevirtual getName : ()Ljava/lang/String;
    //   1035: invokevirtual equals : (Ljava/lang/Object;)Z
    //   1038: ifeq -> 1049
    //   1041: aload #12
    //   1043: getstatic gnu/bytecode/Type.voidType : Lgnu/bytecode/PrimType;
    //   1046: invokevirtual setReturnType : (Lgnu/bytecode/Type;)V
    //   1049: aload #12
    //   1051: aload #8
    //   1053: invokestatic setLine : (Lgnu/expr/Expression;Ljava/lang/Object;)V
    //   1056: aload_2
    //   1057: getfield curMethodLambda : Lgnu/expr/LambdaExp;
    //   1060: astore #11
    //   1062: aload_2
    //   1063: aload #12
    //   1065: putfield curMethodLambda : Lgnu/expr/LambdaExp;
    //   1068: aload_0
    //   1069: getfield lambda : Lkawa/lang/Lambda;
    //   1072: astore #13
    //   1074: aload #7
    //   1076: checkcast gnu/lists/Pair
    //   1079: invokevirtual getCdr : ()Ljava/lang/Object;
    //   1082: astore #7
    //   1084: aload #8
    //   1086: invokevirtual getCdr : ()Ljava/lang/Object;
    //   1089: astore #8
    //   1091: aload #6
    //   1093: ifnull -> 1279
    //   1096: aload_1
    //   1097: ifnull -> 1116
    //   1100: aload #6
    //   1102: invokeinterface getScope : ()Lkawa/lang/TemplateScope;
    //   1107: aload_1
    //   1108: invokeinterface getScope : ()Lkawa/lang/TemplateScope;
    //   1113: if_acmpeq -> 1279
    //   1116: aload #6
    //   1118: invokeinterface getScope : ()Lkawa/lang/TemplateScope;
    //   1123: astore #6
    //   1125: aload #13
    //   1127: aload #12
    //   1129: aload #7
    //   1131: aload #8
    //   1133: aload_2
    //   1134: aload #6
    //   1136: invokevirtual rewrite : (Lgnu/expr/LambdaExp;Ljava/lang/Object;Ljava/lang/Object;Lkawa/lang/Translator;Lkawa/lang/TemplateScope;)V
    //   1139: aload_2
    //   1140: aload #11
    //   1142: putfield curMethodLambda : Lgnu/expr/LambdaExp;
    //   1145: aload_1
    //   1146: ifnull -> 1155
    //   1149: aload_2
    //   1150: aload #10
    //   1152: invokevirtual setCurrentScope : (Lgnu/expr/ScopeExp;)V
    //   1155: aload #12
    //   1157: getfield nextSibling : Lgnu/expr/LambdaExp;
    //   1160: astore_1
    //   1161: iload_3
    //   1162: istore #4
    //   1164: goto -> 965
    //   1167: aload_2
    //   1168: ldc_w 'invalid field/method definition'
    //   1171: invokevirtual syntaxError : (Ljava/lang/String;)Lgnu/expr/Expression;
    //   1174: pop
    //   1175: aload #12
    //   1177: astore_1
    //   1178: iload_3
    //   1179: istore #4
    //   1181: goto -> 965
    //   1184: aload #17
    //   1186: getfield initMethod : Lgnu/expr/LambdaExp;
    //   1189: ifnull -> 1202
    //   1192: aload #17
    //   1194: getfield initMethod : Lgnu/expr/LambdaExp;
    //   1197: aload #17
    //   1199: putfield outer : Lgnu/expr/ScopeExp;
    //   1202: aload #17
    //   1204: getfield clinitMethod : Lgnu/expr/LambdaExp;
    //   1207: ifnull -> 1220
    //   1210: aload #17
    //   1212: getfield clinitMethod : Lgnu/expr/LambdaExp;
    //   1215: aload #17
    //   1217: putfield outer : Lgnu/expr/ScopeExp;
    //   1220: aload_2
    //   1221: aload #17
    //   1223: invokevirtual pop : (Lgnu/expr/ScopeExp;)V
    //   1226: aload #17
    //   1228: aload_2
    //   1229: invokevirtual declareParts : (Lgnu/expr/Compilation;)V
    //   1232: return
    //   1233: astore_1
    //   1234: goto -> 734
    //   1237: aconst_null
    //   1238: astore #7
    //   1240: aconst_null
    //   1241: astore #8
    //   1243: goto -> 555
    //   1246: aload #15
    //   1248: astore #6
    //   1250: iload #5
    //   1252: istore #4
    //   1254: goto -> 720
    //   1257: aload #16
    //   1259: astore #7
    //   1261: aload_1
    //   1262: astore #8
    //   1264: aload #15
    //   1266: astore #6
    //   1268: iload #5
    //   1270: istore #4
    //   1272: aload #13
    //   1274: astore #11
    //   1276: goto -> 720
    //   1279: aconst_null
    //   1280: astore #6
    //   1282: goto -> 1125
    // Exception table:
    //   from	to	target	type
    //   425	457	733	finally
    //   466	480	733	finally
    //   483	507	733	finally
    //   510	534	733	finally
    //   540	548	733	finally
    //   555	563	733	finally
    //   563	585	733	finally
    //   588	595	733	finally
    //   598	612	733	finally
    //   612	632	733	finally
    //   635	666	733	finally
    //   666	674	733	finally
    //   680	717	733	finally
    //   720	726	733	finally
    //   742	766	733	finally
    //   778	786	733	finally
    //   789	797	733	finally
    //   820	828	733	finally
    //   843	861	733	finally
    //   868	875	733	finally
    //   898	926	1233	finally
    //   937	959	733	finally
    //   983	987	1233	finally
    //   999	1013	733	finally
    //   1017	1027	733	finally
    //   1027	1049	733	finally
    //   1049	1091	733	finally
    //   1100	1116	733	finally
    //   1116	1125	733	finally
    //   1125	1145	733	finally
    //   1149	1155	733	finally
    //   1155	1161	733	finally
    //   1167	1175	733	finally
  }
  
  public Expression rewriteForm(Pair paramPair, Translator paramTranslator) {
    if (!(paramPair.getCdr() instanceof Pair))
      return paramTranslator.syntaxError("missing superclass specification in object"); 
    Pair pair = (Pair)paramPair.getCdr();
    ObjectExp objectExp2 = new ObjectExp();
    paramPair = pair;
    if (pair.getCar() instanceof FString) {
      if (!(pair.getCdr() instanceof Pair))
        return paramTranslator.syntaxError("missing superclass specification after object class name"); 
      paramPair = (Pair)pair.getCdr();
    } 
    Object[] arrayOfObject = scanClassDef(paramPair, (ClassExp)objectExp2, paramTranslator);
    ObjectExp objectExp1 = objectExp2;
    if (arrayOfObject != null) {
      rewriteClassDef(arrayOfObject, paramTranslator);
      return (Expression)objectExp2;
    } 
    return (Expression)objectExp1;
  }
  
  public Object[] scanClassDef(Pair paramPair, ClassExp paramClassExp, Translator paramTranslator) {
    StringBuilder stringBuilder;
    paramTranslator.mustCompileHere();
    Object object6 = paramPair.getCar();
    Object object5 = paramPair.getCdr();
    Object object3 = null;
    Object object2 = null;
    Object object4 = null;
    long l = 0L;
    Vector<Object> vector = new Vector(20);
    for (Object object1 = object5; object1 != LList.Empty; object1 = object8) {
      while (object1 instanceof SyntaxForm)
        object1 = ((SyntaxForm)object1).getDatum(); 
      if (!(object1 instanceof Pair)) {
        paramTranslator.error('e', "object member not a list");
        return null;
      } 
      Pair pair = (Pair)object1;
      Object object7;
      for (object7 = pair.getCar(); object7 instanceof SyntaxForm; object7 = ((SyntaxForm)object7).getDatum());
      object1 = pair.getCdr();
      Object object10 = paramTranslator.pushPositionOf(pair);
      Object object8 = object1;
      if (object7 instanceof Keyword) {
        while (object1 instanceof SyntaxForm)
          object1 = ((SyntaxForm)object1).getDatum(); 
        object8 = object1;
        if (object1 instanceof Pair) {
          if (object7 == interfaceKeyword) {
            if (((Pair)object1).getCar() == Boolean.FALSE) {
              paramClassExp.setFlag(65536);
            } else {
              paramClassExp.setFlag(32768);
            } 
            object1 = ((Pair)object1).getCdr();
            paramTranslator.popPositionOf(object10);
            continue;
          } 
          if (object7 == classNameKeyword) {
            if (object3 != null)
              paramTranslator.error('e', "duplicate class-name specifiers"); 
            object3 = object1;
            object1 = ((Pair)object1).getCdr();
            paramTranslator.popPositionOf(object10);
            continue;
          } 
          object8 = object1;
          if (object7 == accessKeyword) {
            object7 = paramTranslator.pushPositionOf(object1);
            l = addAccessFlags(((Pair)object1).getCar(), l, 25820135424L, "class", paramTranslator);
            if (paramClassExp.nameDecl == null)
              paramTranslator.error('e', "access specifier for anonymous class"); 
            paramTranslator.popPositionOf(object7);
            object1 = ((Pair)object1).getCdr();
            paramTranslator.popPositionOf(object10);
            continue;
          } 
        } 
      } 
      if (!(object7 instanceof Pair)) {
        paramTranslator.error('e', "object member not a list");
        return null;
      } 
      object1 = object7;
      Object object9;
      for (object9 = object1.getCar(); object9 instanceof SyntaxForm; object9 = ((SyntaxForm)object9).getDatum());
      if (object9 instanceof String || object9 instanceof Symbol || object9 instanceof Keyword) {
        Object object11;
        boolean bool1;
        Object object12;
        Object object13;
        Declaration declaration;
        Object object14 = null;
        boolean bool3 = false;
        long l1 = 0L;
        if (object9 instanceof Keyword) {
          declaration = null;
        } else {
          declaration = paramClassExp.addDeclaration(object9);
          declaration.setSimple(false);
          declaration.setFlag(1048576L);
          Translator.setLine(declaration, object1);
          object1 = object1.getCdr();
        } 
        int i = 0;
        boolean bool2 = false;
        Object object15 = null;
        while (true) {
          object7 = object1;
          if (object1 != LList.Empty) {
            while (object1 instanceof SyntaxForm)
              object1 = ((SyntaxForm)object1).getDatum(); 
            object1 = object1;
            Object object17;
            for (object17 = object1.getCar(); object17 instanceof SyntaxForm; object17 = ((SyntaxForm)object17).getDatum());
            Object object18 = paramTranslator.pushPositionOf(object1);
            Object object16 = object1.getCdr();
            if ((object17 == coloncolon || object17 instanceof Keyword) && object16 instanceof Pair) {
              int j = i + 1;
              Pair pair1 = (Pair)object16;
              Object object20 = pair1.getCar();
              Object object19 = pair1.getCdr();
              if (object17 == coloncolon || object17 == typeKeyword) {
                object16 = pair1;
                Object object21 = object11;
                i = j;
                object7 = object15;
                object1 = object19;
                Object object23 = object13;
                Object object22 = object12;
              } else if (object17 == allocationKeyword) {
                if (object12 != null)
                  paramTranslator.error('e', "duplicate allocation: specification"); 
                if (matches(object20, "class", paramTranslator) || matches(object20, "static", paramTranslator)) {
                  char c = 'ࠀ';
                  Object object22 = object13;
                  object1 = object19;
                  object7 = object15;
                  i = j;
                  Object object21 = object11;
                  object16 = object14;
                } else if (matches(object20, "instance", paramTranslator)) {
                  char c = 'က';
                  Object object22 = object13;
                  object1 = object19;
                  object7 = object15;
                  i = j;
                  Object object21 = object11;
                  object16 = object14;
                } else {
                  paramTranslator.error('e', "unknown allocation kind '" + object20 + "'");
                  Object object22 = object12;
                  Object object23 = object13;
                  object1 = object19;
                  object7 = object15;
                  i = j;
                  Object object21 = object11;
                  object16 = object14;
                } 
              } else if (object17 == initKeyword || object17 == initformKeyword || object17 == init_formKeyword || object17 == init_valueKeyword) {
                if (object11 != null)
                  paramTranslator.error('e', "duplicate initialization"); 
                bool1 = true;
                Object object21 = object12;
                Object object22 = object13;
                object1 = object19;
                object7 = object15;
                i = j;
                boolean bool = bool1;
                object16 = object14;
                if (object17 != initKeyword) {
                  object7 = pair1;
                  object21 = object12;
                  object22 = object13;
                  object1 = object19;
                  i = j;
                  bool = bool1;
                  object16 = object14;
                } 
              } else if (object17 == init_keywordKeyword) {
                if (!(object20 instanceof Keyword)) {
                  paramTranslator.error('e', "invalid 'init-keyword' - not a keyword");
                  Object object21 = object12;
                  Object object22 = object13;
                  object1 = object19;
                  object7 = object15;
                  i = j;
                  boolean bool = bool1;
                  object16 = object14;
                } else {
                  Object object21 = object12;
                  Object object22 = object13;
                  object1 = object19;
                  object7 = object15;
                  i = j;
                  boolean bool = bool1;
                  object16 = object14;
                  if (((Keyword)object20).getName() != object9.toString()) {
                    paramTranslator.error('w', "init-keyword option ignored");
                    object21 = object12;
                    object22 = object13;
                    object1 = object19;
                    object7 = object15;
                    i = j;
                    bool = bool1;
                    object16 = object14;
                  } 
                } 
              } else if (object17 == accessKeyword) {
                object1 = paramTranslator.pushPositionOf(pair1);
                long l2 = addAccessFlags(object20, object13, 32463912960L, "field", paramTranslator);
                paramTranslator.popPositionOf(object1);
                Object object21 = object12;
                object1 = object19;
                object7 = object15;
                i = j;
                boolean bool = bool1;
                object16 = object14;
              } else {
                paramTranslator.error('w', "unknown slot keyword '" + object17 + "'");
                Object object21 = object12;
                Object object22 = object13;
                object1 = object19;
                object7 = object15;
                i = j;
                boolean bool = bool1;
                object16 = object14;
              } 
              continue;
            } 
            if (object16 == LList.Empty && !bool1) {
              object7 = object1;
              boolean bool = true;
              Object object19 = object12;
              Object object20 = object13;
              object1 = object16;
              object16 = object14;
              continue;
            } 
            if (object16 instanceof Pair && i == 0 && !bool1 && object14 == null) {
              object17 = object16;
              if (object17.getCdr() == LList.Empty) {
                object16 = object1;
                object7 = object17;
                object1 = object17.getCdr();
                boolean bool = true;
                Object object19 = object12;
                Object object20 = object13;
                continue;
              } 
            } 
            object7 = null;
          } 
          break;
          paramTranslator.popPositionOf(SYNTHETIC_LOCAL_VARIABLE_33);
          object12 = SYNTHETIC_LOCAL_VARIABLE_5;
          object13 = SYNTHETIC_LOCAL_VARIABLE_14;
          object15 = object7;
          object11 = SYNTHETIC_LOCAL_VARIABLE_4;
          object14 = SYNTHETIC_LOCAL_VARIABLE_20;
        } 
        if (object7 != LList.Empty) {
          stringBuilder = (new StringBuilder()).append("invalid argument list for slot '").append(object9).append('\'').append(" args:");
          if (object7 == null) {
            object1 = "null";
            paramTranslator.error('e', stringBuilder.append((String)object1).toString());
            return null;
          } 
          object1 = object7.getClass().getName();
          paramTranslator.error('e', stringBuilder.append((String)object1).toString());
          return null;
        } 
        if (bool1) {
          boolean bool;
          if (object12 == 'ࠀ') {
            bool = true;
          } else {
            bool = false;
          } 
          if (declaration != null) {
            object1 = declaration;
          } else if (bool) {
            object1 = Boolean.TRUE;
          } else {
            object1 = Boolean.FALSE;
          } 
          vector.addElement(object1);
          vector.addElement(object15);
        } 
        if (declaration == null) {
          if (!bool1) {
            paramTranslator.error('e', "missing field name");
            return null;
          } 
        } else {
          if (object14 != null)
            declaration.setType(paramTranslator.exp2Type((Pair)object14)); 
          if (object12 != null)
            declaration.setFlag(object12); 
          if (object13 != 0L)
            declaration.setFlag(object13); 
          declaration.setCanRead(true);
          declaration.setCanWrite(true);
        } 
      } else if (object9 instanceof Pair) {
        object7 = object9;
        Object object11 = object7.getCar();
        if (!(object11 instanceof String) && !(object11 instanceof Symbol)) {
          paramTranslator.error('e', "missing method name");
          return null;
        } 
        object1 = new LambdaExp();
        Translator.setLine(stringBuilder.addMethod((LambdaExp)object1, object11), object7);
        if (object4 == null) {
          object2 = object1;
        } else {
          ((LambdaExp)object4).nextSibling = (LambdaExp)object1;
        } 
        object4 = object1;
      } else {
        paramTranslator.error('e', "invalid field/method definition");
      } 
      paramTranslator.popPositionOf(object10);
    } 
    if (l != 0L)
      ((ClassExp)stringBuilder).nameDecl.setFlag(l); 
    return new Object[] { stringBuilder, object5, vector, object2, object6, object3 };
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/kawa/standard/object.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */