package gnu.kawa.slib;

import gnu.expr.ApplyExp;
import gnu.expr.BeginExp;
import gnu.expr.Compilation;
import gnu.expr.Declaration;
import gnu.expr.Expression;
import gnu.expr.IfExp;
import gnu.expr.Keyword;
import gnu.expr.LambdaExp;
import gnu.expr.Language;
import gnu.expr.LetExp;
import gnu.expr.ModuleBody;
import gnu.expr.ModuleInfo;
import gnu.expr.ModuleMethod;
import gnu.expr.NameLookup;
import gnu.expr.QuoteExp;
import gnu.expr.ReferenceExp;
import gnu.expr.SetExp;
import gnu.expr.Special;
import gnu.kawa.functions.AddOp;
import gnu.kawa.functions.Format;
import gnu.kawa.lispexpr.LangObjType;
import gnu.lists.Consumer;
import gnu.lists.EofClass;
import gnu.lists.LList;
import gnu.lists.Pair;
import gnu.lists.PairWithPosition;
import gnu.mapping.CallContext;
import gnu.mapping.Environment;
import gnu.mapping.Procedure;
import gnu.mapping.SimpleSymbol;
import gnu.mapping.WrongType;
import gnu.math.IntNum;
import gnu.math.Numeric;
import gnu.text.SourceMessages;
import kawa.lang.Macro;
import kawa.lang.Quote;
import kawa.lang.SyntaxPattern;
import kawa.lang.SyntaxRule;
import kawa.lang.SyntaxRules;
import kawa.lang.Translator;
import kawa.lib.lists;
import kawa.lib.misc;
import kawa.standard.Scheme;

public class syntaxutils extends ModuleBody {
  public static final Macro $Prvt$$Ex;
  
  public static final Macro $Prvt$typecase$Pc;
  
  public static final syntaxutils $instance;
  
  static final Keyword Lit0;
  
  static final PairWithPosition Lit1;
  
  static final PairWithPosition Lit10;
  
  static final PairWithPosition Lit11;
  
  static final PairWithPosition Lit12;
  
  static final SimpleSymbol Lit13;
  
  static final SyntaxRules Lit14;
  
  static final SimpleSymbol Lit15;
  
  static final SyntaxRules Lit16;
  
  static final SimpleSymbol Lit17;
  
  static final SimpleSymbol Lit18;
  
  static final SimpleSymbol Lit19;
  
  static final Keyword Lit2;
  
  static final SimpleSymbol Lit20;
  
  static final SimpleSymbol Lit21;
  
  static final SimpleSymbol Lit22;
  
  static final SimpleSymbol Lit23;
  
  static final SimpleSymbol Lit24;
  
  static final SimpleSymbol Lit25;
  
  static final SimpleSymbol Lit26 = (SimpleSymbol)(new SimpleSymbol("lambda")).readResolve();
  
  static final PairWithPosition Lit3;
  
  static final PairWithPosition Lit4;
  
  static final PairWithPosition Lit5;
  
  static final PairWithPosition Lit6;
  
  static final IntNum Lit7;
  
  static final IntNum Lit8;
  
  static final PairWithPosition Lit9;
  
  public static final ModuleMethod expand;
  
  static {
    Lit25 = (SimpleSymbol)(new SimpleSymbol("as")).readResolve();
    Lit24 = (SimpleSymbol)(new SimpleSymbol("else")).readResolve();
    Lit23 = (SimpleSymbol)(new SimpleSymbol("let")).readResolve();
    Lit22 = (SimpleSymbol)(new SimpleSymbol("cond")).readResolve();
    Lit21 = (SimpleSymbol)(new SimpleSymbol("begin")).readResolve();
    Lit20 = (SimpleSymbol)(new SimpleSymbol("or")).readResolve();
    Lit19 = (SimpleSymbol)(new SimpleSymbol("quote")).readResolve();
    Lit18 = (SimpleSymbol)(new SimpleSymbol("eql")).readResolve();
    Lit17 = (SimpleSymbol)(new SimpleSymbol("expand")).readResolve();
    SimpleSymbol simpleSymbol1 = (SimpleSymbol)(new SimpleSymbol("!")).readResolve();
    Lit15 = simpleSymbol1;
    SyntaxRule syntaxRule1 = new SyntaxRule(new SyntaxPattern("\f\030\f\007\f\017\r\027\020\b\b", new Object[0], 3), "\001\001\003", "\021\030\004\t\013)\021\030\f\b\003\b\025\023", new Object[] { (new SimpleSymbol("invoke")).readResolve(), Lit19 }, 1);
    Lit16 = new SyntaxRules(new Object[] { simpleSymbol1 }, new SyntaxRule[] { syntaxRule1 }, 3);
    simpleSymbol1 = (SimpleSymbol)(new SimpleSymbol("typecase%")).readResolve();
    Lit13 = simpleSymbol1;
    SimpleSymbol simpleSymbol2 = Lit18;
    SimpleSymbol simpleSymbol3 = Lit20;
    SyntaxRule syntaxRule2 = new SyntaxRule(new SyntaxPattern("\f\030\f\007<\f\002\r\017\b\b\b\r\027\020\b\b", new Object[] { Boolean.TRUE }, 3), "\001\003\003", "\021\030\004\b\r\013", new Object[] { Lit21 }, 1);
    SyntaxRule syntaxRule3 = new SyntaxRule(new SyntaxPattern("\f\030\f\007\\,\f\002\f\017\b\r\027\020\b\b\r\037\030\b\b", new Object[] { Lit18 }, 4), "\001\001\003\003", "\021\030\004yY\021\030\f\t\003\b\021\030\024\b\013\b\025\023\b\021\030\034\b\021\030$\t\003\b\035\033", new Object[] { Lit22, (new SimpleSymbol("eqv?")).readResolve(), Lit19, Lit24, Lit13 }, 1);
    SyntaxRule syntaxRule4 = new SyntaxRule(new SyntaxPattern("\f\030\f\007\\,\f\002\f\017\b\r\027\020\b\b\r\037\030\b\b", new Object[] { Lit20 }, 4), "\001\001\003\003", "\021\030\004\t\003)\t\013\b\025\023\b\035\033", new Object[] { Lit13 }, 1);
    SyntaxRule syntaxRule5 = new SyntaxRule(new SyntaxPattern("\f\030\f\007l<\f\002\r\017\b\b\b\r\027\020\b\b\r\037\030\b\b", new Object[] { Lit20 }, 4), "\001\003\003\003", "\021\030\004\b\021\030\f\b\021\030\024\021\b\003\b\021\030\034\b\025\023\b\021\030$\t\003I\r\t\013\b\021\030\f\b\003\b\021\030,\b\021\030$\t\003\b\035\033", new Object[] { Lit23, (new SimpleSymbol("f")).readResolve(), Lit26, Lit21, Lit13, Boolean.TRUE }, 1);
    SyntaxRule syntaxRule6 = new SyntaxRule(new SyntaxPattern("\f\030\f\007<\f\017\r\027\020\b\b\r\037\030\b\b", new Object[0], 4), "\001\001\003\003", "\021\030\004ñ9\021\030\f\t\003\b\013\b\021\030\024Q\b\t\003\021\030\034\t\013\b\003\b\021\030$\b\025\023\b\021\030,\b\021\0304\t\003\b\035\033", new Object[] { Lit22, (new SimpleSymbol("instance?")).readResolve(), Lit23, (new SimpleSymbol("::")).readResolve(), Lit21, Lit24, Lit13 }, 1);
    SyntaxRule syntaxRule7 = new SyntaxRule(new SyntaxPattern("\f\030\f\007\b", new Object[0], 1), "\001", "\021\030\004\021\030\f\t\003\b\021\030\024\021\030\034\b\021\030$\021\030,\b\003", new Object[] { (new SimpleSymbol("error")).readResolve(), "typecase% failed", Lit15, (new SimpleSymbol("getClass")).readResolve(), Lit25, (new SimpleSymbol("<object>")).readResolve() }, 0);
    Lit14 = new SyntaxRules(new Object[] { simpleSymbol1, simpleSymbol2, simpleSymbol3 }, new SyntaxRule[] { syntaxRule2, syntaxRule3, syntaxRule4, syntaxRule5, syntaxRule6, syntaxRule7 }, 4);
    Lit12 = PairWithPosition.make((new SimpleSymbol(":")).readResolve(), LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/syntaxutils.scm", 634896);
    Lit11 = PairWithPosition.make(Lit25, LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/syntaxutils.scm", 626704);
    Lit10 = PairWithPosition.make(Lit19, LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/syntaxutils.scm", 552972);
    Lit9 = PairWithPosition.make(Lit23, LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/syntaxutils.scm", 479236);
    Lit8 = IntNum.make(1);
    Lit7 = IntNum.make(0);
    Lit6 = PairWithPosition.make((new SimpleSymbol("if")).readResolve(), LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/syntaxutils.scm", 417799);
    Lit5 = PairWithPosition.make(Lit21, LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/syntaxutils.scm", 409627);
    Lit4 = PairWithPosition.make(Lit26, LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/syntaxutils.scm", 376839);
    Lit3 = PairWithPosition.make((new SimpleSymbol("set")).readResolve(), LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/syntaxutils.scm", 368647);
    Lit2 = Keyword.make("lang");
    Lit1 = PairWithPosition.make(Lit21, LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/syntaxutils.scm", 278557);
    Lit0 = Keyword.make("env");
    $instance = new syntaxutils();
    $Prvt$typecase$Pc = Macro.make(Lit13, (Procedure)Lit14, $instance);
    $Prvt$$Ex = Macro.make(Lit15, (Procedure)Lit16, $instance);
    expand = new ModuleMethod($instance, 1, Lit17, -4095);
    $instance.run();
  }
  
  public syntaxutils() {
    ModuleInfo.register(this);
  }
  
  public static Object expand$V(Object paramObject, Object[] paramArrayOfObject) {
    Object object2 = Keyword.searchForKeyword(paramArrayOfObject, 0, Lit0);
    Object object1 = object2;
    if (object2 == Special.dfault)
      object1 = misc.interactionEnvironment(); 
    return unrewrite(rewriteForm$V(Quote.append$V(new Object[] { Lit1, Quote.consX$V(new Object[] { paramObject, LList.Empty }, ) }, ), new Object[] { Lit0, object1 }));
  }
  
  static Expression rewriteForm$V(Object paramObject, Object[] paramArrayOfObject) {
    Object object3 = Keyword.searchForKeyword(paramArrayOfObject, 0, Lit2);
    Object object2 = object3;
    if (object3 == Special.dfault)
      object2 = Language.getDefaultLanguage(); 
    object3 = Keyword.searchForKeyword(paramArrayOfObject, 0, Lit0);
    Object object1 = object3;
    if (object3 == Special.dfault)
      object1 = misc.interactionEnvironment(); 
    try {
      object3 = object1;
      try {
        object1 = object2;
        object1 = NameLookup.getInstance((Environment)object3, (Language)object1);
        object3 = new SourceMessages();
        try {
          Language language = (Language)object2;
          object2 = new Translator(language, (SourceMessages)object3, (NameLookup)object1);
          object2.pushNewModule(null);
          object1 = Compilation.setSaveCurrent((Compilation)object2);
          try {
            paramObject = object2.rewrite(paramObject);
            return (Expression)paramObject;
          } finally {
            Compilation.restoreCurrent((Compilation)object1);
          } 
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "kawa.lang.Translator.<init>(gnu.expr.Language,gnu.text.SourceMessages,gnu.expr.NameLookup)", 1, object2);
        } 
      } catch (ClassCastException classCastException) {
        throw new WrongType(classCastException, "gnu.expr.NameLookup.getInstance(gnu.mapping.Environment,gnu.expr.Language)", 2, object2);
      } 
    } catch (ClassCastException classCastException) {
      throw new WrongType(classCastException, "gnu.expr.NameLookup.getInstance(gnu.mapping.Environment,gnu.expr.Language)", 1, object1);
    } 
  }
  
  static Object unrewrite(Expression paramExpression) {
    Declaration declaration1;
    frame frame = new frame();
    if (paramExpression instanceof LetExp)
      try {
        LetExp letExp = (LetExp)paramExpression;
        return unrewriteLet(letExp);
      } catch (ClassCastException null) {
        throw new WrongType(classCastException2, "exp", -2, paramExpression);
      }  
    if (paramExpression instanceof QuoteExp)
      try {
        QuoteExp quoteExp = (QuoteExp)paramExpression;
        return unrewriteQuote(quoteExp);
      } catch (ClassCastException null) {
        throw new WrongType(classCastException2, "exp", -2, paramExpression);
      }  
    if (paramExpression instanceof SetExp)
      try {
        SetExp setExp = (SetExp)paramExpression;
        return Quote.append$V(new Object[] { Lit3, Quote.consX$V(new Object[] { setExp.getSymbol(), Quote.consX$V(new Object[] { unrewrite(setExp.getNewValue()), LList.Empty }) }) });
      } catch (ClassCastException classCastException2) {
        throw new WrongType(classCastException2, "exp", -2, paramExpression);
      }  
    if (paramExpression instanceof LambdaExp)
      try {
        LambdaExp lambdaExp = (LambdaExp)paramExpression;
        PairWithPosition pairWithPosition = Lit4;
        ((frame)classCastException2).pack = LList.Empty;
        for (declaration1 = lambdaExp.firstDecl(); declaration1 != null; declaration1 = declaration1.nextDecl())
          ((frame)classCastException2).pack = (LList)lists.cons(declaration1.getSymbol(), ((frame)classCastException2).pack); 
        return Quote.append$V(new Object[] { pairWithPosition, Quote.consX$V(new Object[] { lists.reverse$Ex(((frame)classCastException2).pack), Quote.consX$V(new Object[] { unrewrite(lambdaExp.body), LList.Empty }) }) });
      } catch (ClassCastException null) {
        throw new WrongType(classCastException1, "exp", -2, declaration1);
      }  
    if (declaration1 instanceof ReferenceExp)
      try {
        ReferenceExp referenceExp = (ReferenceExp)declaration1;
        return referenceExp.getSymbol();
      } catch (ClassCastException null) {
        throw new WrongType(classCastException1, "exp", -2, declaration1);
      }  
    if (declaration1 instanceof ApplyExp)
      try {
        ApplyExp applyExp = (ApplyExp)declaration1;
        return unrewriteApply(applyExp);
      } catch (ClassCastException null) {
        throw new WrongType(classCastException1, "exp", -2, declaration1);
      }  
    if (declaration1 instanceof BeginExp)
      try {
        BeginExp beginExp = (BeginExp)declaration1;
        return Quote.append$V(new Object[] { Lit5, unrewrite$St(beginExp.getExpressions()) });
      } catch (ClassCastException null) {
        throw new WrongType(classCastException1, "exp", -2, declaration1);
      }  
    Declaration declaration2 = declaration1;
    if (declaration1 instanceof IfExp) {
      Pair pair;
      try {
        LList lList;
        IfExp ifExp = (IfExp)declaration1;
        PairWithPosition pairWithPosition = Lit6;
        Object object1 = unrewrite(ifExp.getTest());
        Object object2 = unrewrite(ifExp.getThenClause());
        Expression expression = ifExp.getElseClause();
        if (expression == null) {
          lList = LList.Empty;
          return Quote.append$V(new Object[] { pairWithPosition, Quote.consX$V(new Object[] { object1, Quote.consX$V(new Object[] { object2, Quote.append$V(new Object[] { lList, LList.Empty }) }) }) });
        } 
        pair = LList.list1(unrewrite((Expression)lList));
        return Quote.append$V(new Object[] { pairWithPosition, Quote.consX$V(new Object[] { object1, Quote.consX$V(new Object[] { object2, Quote.append$V(new Object[] { pair, LList.Empty }) }) }) });
      } catch (ClassCastException classCastException1) {
        throw new WrongType(classCastException1, "exp", -2, pair);
      } 
    } 
    return classCastException1;
  }
  
  static Object unrewrite$St(Expression[] paramArrayOfExpression) {
    frame0 frame0 = new frame0();
    frame0.pack = LList.Empty;
    int i = paramArrayOfExpression.length;
    IntNum intNum = Lit7;
    while (Scheme.numEqu.apply2(intNum, Integer.valueOf(i)) == Boolean.FALSE) {
      frame0.pack = (LList)lists.cons(unrewrite(paramArrayOfExpression[((Number)intNum).intValue()]), frame0.pack);
      Object object = AddOp.$Pl.apply2(intNum, Lit8);
    } 
    return lists.reverse$Ex(frame0.pack);
  }
  
  static Object unrewriteApply(ApplyExp paramApplyExp) {
    // Byte code:
    //   0: aload_0
    //   1: invokevirtual getFunction : ()Lgnu/expr/Expression;
    //   4: astore_3
    //   5: aload_0
    //   6: invokevirtual getArgs : ()[Lgnu/expr/Expression;
    //   9: invokestatic unrewrite$St : ([Lgnu/expr/Expression;)Ljava/lang/Object;
    //   12: astore #4
    //   14: aload_3
    //   15: instanceof gnu/expr/ReferenceExp
    //   18: ifeq -> 111
    //   21: aload_3
    //   22: checkcast gnu/expr/ReferenceExp
    //   25: astore_2
    //   26: aload_2
    //   27: invokevirtual getBinding : ()Lgnu/expr/Declaration;
    //   30: astore_2
    //   31: ldc_w 'kawa.standard.Scheme'
    //   34: ldc_w 'applyToArgs'
    //   37: invokestatic getDeclarationFromStatic : (Ljava/lang/String;Ljava/lang/String;)Lgnu/expr/Declaration;
    //   40: astore #5
    //   42: aload_0
    //   43: invokevirtual getFunctionValue : ()Ljava/lang/Object;
    //   46: astore_0
    //   47: aload_2
    //   48: ifnonnull -> 116
    //   51: iconst_1
    //   52: istore_1
    //   53: iload_1
    //   54: iconst_1
    //   55: iadd
    //   56: iconst_1
    //   57: iand
    //   58: istore_1
    //   59: iload_1
    //   60: ifeq -> 165
    //   63: aload #5
    //   65: ifnonnull -> 121
    //   68: iconst_1
    //   69: istore_1
    //   70: iload_1
    //   71: iconst_1
    //   72: iadd
    //   73: iconst_1
    //   74: iand
    //   75: istore_1
    //   76: iload_1
    //   77: ifeq -> 126
    //   80: iconst_0
    //   81: aload_2
    //   82: ldc_w 'field'
    //   85: ldc_w 'field'
    //   88: ldc_w 'getField'
    //   91: ldc_w 'isField'
    //   94: getstatic kawa/standard/Scheme.instance : Lkawa/standard/Scheme;
    //   97: invokestatic getSlotValue : (ZLjava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lgnu/expr/Language;)Ljava/lang/Object;
    //   100: aload #5
    //   102: getfield field : Lgnu/bytecode/Field;
    //   105: if_acmpne -> 130
    //   108: aload #4
    //   110: areturn
    //   111: aconst_null
    //   112: astore_2
    //   113: goto -> 31
    //   116: iconst_0
    //   117: istore_1
    //   118: goto -> 53
    //   121: iconst_0
    //   122: istore_1
    //   123: goto -> 70
    //   126: iload_1
    //   127: ifne -> 108
    //   130: aload_0
    //   131: instanceof gnu/kawa/functions/Convert
    //   134: ifeq -> 172
    //   137: iconst_2
    //   138: anewarray java/lang/Object
    //   141: dup
    //   142: iconst_0
    //   143: getstatic gnu/kawa/slib/syntaxutils.Lit11 : Lgnu/lists/PairWithPosition;
    //   146: aastore
    //   147: dup
    //   148: iconst_1
    //   149: aload #4
    //   151: aastore
    //   152: invokestatic append$V : ([Ljava/lang/Object;)Ljava/lang/Object;
    //   155: astore_0
    //   156: aload_0
    //   157: getstatic java/lang/Boolean.FALSE : Ljava/lang/Boolean;
    //   160: if_acmpeq -> 208
    //   163: aload_0
    //   164: areturn
    //   165: iload_1
    //   166: ifeq -> 130
    //   169: aload #4
    //   171: areturn
    //   172: aload_0
    //   173: instanceof gnu/kawa/functions/GetNamedPart
    //   176: ifeq -> 201
    //   179: iconst_2
    //   180: anewarray java/lang/Object
    //   183: dup
    //   184: iconst_0
    //   185: getstatic gnu/kawa/slib/syntaxutils.Lit12 : Lgnu/lists/PairWithPosition;
    //   188: aastore
    //   189: dup
    //   190: iconst_1
    //   191: aload #4
    //   193: aastore
    //   194: invokestatic append$V : ([Ljava/lang/Object;)Ljava/lang/Object;
    //   197: astore_0
    //   198: goto -> 156
    //   201: getstatic java/lang/Boolean.FALSE : Ljava/lang/Boolean;
    //   204: astore_0
    //   205: goto -> 156
    //   208: iconst_2
    //   209: anewarray java/lang/Object
    //   212: dup
    //   213: iconst_0
    //   214: aload_3
    //   215: invokestatic unrewrite : (Lgnu/expr/Expression;)Ljava/lang/Object;
    //   218: aastore
    //   219: dup
    //   220: iconst_1
    //   221: aload #4
    //   223: aastore
    //   224: invokestatic consX$V : ([Ljava/lang/Object;)Ljava/lang/Object;
    //   227: areturn
    //   228: astore_0
    //   229: new gnu/mapping/WrongType
    //   232: dup
    //   233: aload_0
    //   234: ldc_w 'fun'
    //   237: bipush #-2
    //   239: aload_3
    //   240: invokespecial <init> : (Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   243: athrow
    // Exception table:
    //   from	to	target	type
    //   21	26	228	java/lang/ClassCastException
  }
  
  static Object unrewriteLet(LetExp paramLetExp) {
    frame1 frame1 = new frame1();
    PairWithPosition pairWithPosition = Lit9;
    frame1.pack = LList.Empty;
    Declaration declaration = paramLetExp.firstDecl();
    IntNum intNum = Lit7;
    while (declaration != null) {
      frame1.pack = (LList)lists.cons(LList.list2(declaration.getSymbol(), unrewrite(paramLetExp.inits[((Number)intNum).intValue()])), frame1.pack);
      declaration = declaration.nextDecl();
      Object object = AddOp.$Pl.apply2(intNum, Lit8);
    } 
    return Quote.append$V(new Object[] { pairWithPosition, Quote.consX$V(new Object[] { lists.reverse$Ex(frame1.pack), Quote.consX$V(new Object[] { unrewrite(paramLetExp.body), LList.Empty }) }) });
  }
  
  static Object unrewriteQuote(QuoteExp paramQuoteExp) {
    Object object2 = paramQuoteExp.getValue();
    if (Numeric.asNumericOrNull(object2) != null)
      try {
        return LangObjType.coerceNumeric(object2);
      } catch (ClassCastException classCastException) {
        throw new WrongType(classCastException, "val", -2, object2);
      }  
    if (object2 instanceof Boolean)
      try {
        boolean bool;
        null = Boolean.FALSE;
        if (object2 != null) {
          bool = true;
        } else {
          bool = false;
        } 
        return bool ? Boolean.TRUE : Boolean.FALSE;
      } catch (ClassCastException classCastException) {
        throw new WrongType(classCastException, "val", -2, object2);
      }  
    if (object2 instanceof gnu.text.Char)
      try {
        return object2;
      } catch (ClassCastException classCastException) {
        throw new WrongType(classCastException, "val", -2, object2);
      }  
    if (object2 instanceof Keyword)
      try {
        return object2;
      } catch (ClassCastException classCastException) {
        throw new WrongType(classCastException, "val", -2, object2);
      }  
    if (object2 instanceof CharSequence)
      try {
        return object2;
      } catch (ClassCastException classCastException) {
        throw new WrongType(classCastException, "val", -2, object2);
      }  
    object1 = object2;
    if (object2 != Special.undefined) {
      object1 = object2;
      if (object2 != EofClass.eofValue) {
        if (object2 instanceof gnu.bytecode.Type)
          try {
            object1 = object2;
            object1 = object1.getName();
            return misc.string$To$Symbol(Format.formatToString(0, new Object[] { "<~a>", object1 }));
          } catch (ClassCastException classCastException) {
            throw new WrongType(classCastException, "val", -2, object2);
          }  
        if (object2 instanceof Class) {
          try {
            object1 = object2;
            object1 = object1.getName();
          } catch (ClassCastException object1) {
            throw new WrongType(object1, "val", -2, object2);
          } 
          return misc.string$To$Symbol(Format.formatToString(0, new Object[] { "<~a>", object1 }));
        } 
        return Quote.append$V(new Object[] { Lit10, Quote.consX$V(new Object[] { object2, LList.Empty }) });
      } 
    } 
    return object1;
  }
  
  public Object applyN(ModuleMethod paramModuleMethod, Object[] paramArrayOfObject) {
    Object object;
    if (paramModuleMethod.selector == 1) {
      object = paramArrayOfObject[0];
      int i = paramArrayOfObject.length - 1;
      Object[] arrayOfObject = new Object[i];
      while (true) {
        if (--i < 0)
          return expand$V(object, arrayOfObject); 
        arrayOfObject[i] = paramArrayOfObject[i + 1];
      } 
    } 
    return super.applyN((ModuleMethod)object, paramArrayOfObject);
  }
  
  public int matchN(ModuleMethod paramModuleMethod, Object[] paramArrayOfObject, CallContext paramCallContext) {
    if (paramModuleMethod.selector == 1) {
      paramCallContext.values = paramArrayOfObject;
      paramCallContext.proc = (Procedure)paramModuleMethod;
      paramCallContext.pc = 5;
      return 0;
    } 
    return super.matchN(paramModuleMethod, paramArrayOfObject, paramCallContext);
  }
  
  public final void run(CallContext paramCallContext) {
    Consumer consumer = paramCallContext.consumer;
  }
  
  public class frame extends ModuleBody {
    LList pack;
  }
  
  public class frame0 extends ModuleBody {
    LList pack;
  }
  
  public class frame1 extends ModuleBody {
    LList pack;
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/gnu/kawa/slib/syntaxutils.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */