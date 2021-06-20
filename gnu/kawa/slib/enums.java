package gnu.kawa.slib;

import gnu.expr.Keyword;
import gnu.expr.ModuleBody;
import gnu.expr.ModuleInfo;
import gnu.expr.ModuleMethod;
import gnu.kawa.functions.Apply;
import gnu.lists.Consumer;
import gnu.lists.LList;
import gnu.lists.Pair;
import gnu.lists.PairWithPosition;
import gnu.mapping.CallContext;
import gnu.mapping.Procedure;
import gnu.mapping.SimpleSymbol;
import gnu.mapping.Symbol;
import gnu.mapping.WrongType;
import kawa.lang.Macro;
import kawa.lang.Quote;
import kawa.lang.SyntaxPattern;
import kawa.lang.SyntaxTemplate;
import kawa.lang.TemplateScope;
import kawa.lib.lists;
import kawa.lib.misc;
import kawa.lib.prim_syntax;
import kawa.lib.std_syntax;
import kawa.lib.strings;
import kawa.standard.Scheme;
import kawa.standard.syntax_case;

public class enums extends ModuleBody {
  public static final Macro $Prvt$$Pcdefine$Mnenum;
  
  public static final enums $instance;
  
  static final PairWithPosition Lit0;
  
  static final PairWithPosition Lit1;
  
  static final PairWithPosition Lit10;
  
  static final SimpleSymbol Lit11;
  
  static final SyntaxPattern Lit12;
  
  static final SyntaxTemplate Lit13;
  
  static final SyntaxTemplate Lit14;
  
  static final SyntaxPattern Lit15;
  
  static final SyntaxTemplate Lit16;
  
  static final SyntaxPattern Lit17;
  
  static final SyntaxPattern Lit18;
  
  static final SyntaxPattern Lit19;
  
  static final PairWithPosition Lit2;
  
  static final SyntaxTemplate Lit20;
  
  static final SimpleSymbol Lit21;
  
  static final SyntaxPattern Lit22;
  
  static final SyntaxTemplate Lit23;
  
  static final SimpleSymbol Lit24;
  
  static final SyntaxTemplate Lit25;
  
  static final SyntaxTemplate Lit26;
  
  static final SyntaxTemplate Lit27;
  
  static final SyntaxTemplate Lit28;
  
  static final SyntaxTemplate Lit29;
  
  static final PairWithPosition Lit3;
  
  static final SyntaxTemplate Lit30;
  
  static final SyntaxTemplate Lit31;
  
  static final SyntaxTemplate Lit32;
  
  static final SyntaxTemplate Lit33;
  
  static final SyntaxTemplate Lit34;
  
  static final SyntaxTemplate Lit35;
  
  static final SyntaxTemplate Lit36;
  
  static final SyntaxTemplate Lit37;
  
  static final SyntaxTemplate Lit38;
  
  static final SyntaxTemplate Lit39;
  
  static final PairWithPosition Lit4;
  
  static final SimpleSymbol Lit40;
  
  static final SimpleSymbol Lit41;
  
  static final SimpleSymbol Lit42;
  
  static final SimpleSymbol Lit43;
  
  static final SimpleSymbol Lit44;
  
  static final SimpleSymbol Lit45;
  
  static final Keyword Lit46;
  
  static final SimpleSymbol Lit47;
  
  static final Keyword Lit48;
  
  static final SimpleSymbol Lit49;
  
  static final PairWithPosition Lit5;
  
  static final SimpleSymbol Lit50;
  
  static final SimpleSymbol Lit51;
  
  static final SimpleSymbol Lit52;
  
  static final SimpleSymbol Lit53 = (SimpleSymbol)(new SimpleSymbol("final")).readResolve();
  
  static final PairWithPosition Lit6;
  
  static final PairWithPosition Lit7;
  
  static final PairWithPosition Lit8;
  
  static final PairWithPosition Lit9;
  
  public static final Macro define$Mnenum;
  
  static {
    Lit52 = (SimpleSymbol)(new SimpleSymbol("enum")).readResolve();
    Lit51 = (SimpleSymbol)(new SimpleSymbol("num")).readResolve();
    Lit50 = (SimpleSymbol)(new SimpleSymbol("str")).readResolve();
    Lit49 = (SimpleSymbol)(new SimpleSymbol("*init*")).readResolve();
    Lit48 = Keyword.make("access");
    Lit47 = (SimpleSymbol)(new SimpleSymbol("String")).readResolve();
    Lit46 = Keyword.make("allocation");
    Lit45 = (SimpleSymbol)(new SimpleSymbol("static")).readResolve();
    Lit44 = (SimpleSymbol)(new SimpleSymbol("java.lang.Enum")).readResolve();
    Lit43 = (SimpleSymbol)(new SimpleSymbol("quote")).readResolve();
    Lit42 = (SimpleSymbol)(new SimpleSymbol("::")).readResolve();
    Lit41 = (SimpleSymbol)(new SimpleSymbol("s")).readResolve();
    Lit40 = (SimpleSymbol)(new SimpleSymbol("valueOf")).readResolve();
    Lit39 = new SyntaxTemplate("\001\001\001\003\003", "\020", new Object[0], 0);
    Lit38 = new SyntaxTemplate("\001\001\001\003\003", "\020", new Object[0], 0);
    Lit37 = new SyntaxTemplate("\001\001\001\003\003", "\030\004", new Object[] { PairWithPosition.make(Lit41, LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/enums.scm", 290882) }0);
    Lit36 = new SyntaxTemplate("\001\001\001\003\003", "\030\004", new Object[] { PairWithPosition.make((new SimpleSymbol("$lookup$")).readResolve(), Pair.make(Lit44, Pair.make(Pair.make((new SimpleSymbol("quasiquote")).readResolve(), Pair.make(Lit40, LList.Empty)), LList.Empty)), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/enums.scm", 290823) }0);
    Lit35 = new SyntaxTemplate("\001\001\001\003\003", "\030\004", new Object[] { PairWithPosition.make(Lit43, PairWithPosition.make(Lit45, LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/enums.scm", 286739), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/enums.scm", 286739) }0);
    Lit34 = new SyntaxTemplate("\001\001\001\003\003", "\030\004", new Object[] { PairWithPosition.make(Lit46, LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/enums.scm", 286726) }0);
    Lit33 = new SyntaxTemplate("\001\001\001\003\003", "\030\004", new Object[] { PairWithPosition.make(Lit42, LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/enums.scm", 282649) }0);
    Lit32 = new SyntaxTemplate("\001\001\001\003\003", "\030\004", new Object[] { PairWithPosition.make(Lit40, PairWithPosition.make(Lit41, PairWithPosition.make(Lit42, PairWithPosition.make(Lit47, LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/enums.scm", 282642), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/enums.scm", 282640), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/enums.scm", 282639), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/enums.scm", 282630) }0);
    Lit31 = new SyntaxTemplate("\001\001\001\003\003", "\030\004", new Object[] { PairWithPosition.make(Lit43, PairWithPosition.make(PairWithPosition.make(Lit52, PairWithPosition.make(Lit53, LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/enums.scm", 266284), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/enums.scm", 266278), LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/enums.scm", 266278), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/enums.scm", 266278) }0);
    Lit30 = new SyntaxTemplate("\001\001\001\003\003", "\030\004", new Object[] { PairWithPosition.make(Lit48, LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/enums.scm", 266269) }0);
    Lit29 = new SyntaxTemplate("\001\001\001\003\003", "\030\004", new Object[] { PairWithPosition.make(Lit44, LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/enums.scm", 266252) }0);
    Lit28 = new SyntaxTemplate("\001\001\001\003\003", "\030\004", new Object[] { PairWithPosition.make((new SimpleSymbol("define-simple-class")).readResolve(), LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/enums.scm", 262154) }0);
    Lit27 = new SyntaxTemplate("\001\001\001\003\003", "\b%#", new Object[0], 1);
    Lit26 = new SyntaxTemplate("\001\001\001\003\003", "\023", new Object[0], 0);
    Lit25 = new SyntaxTemplate("\001\001\001\003\003", "\b\035\033", new Object[0], 1);
    Lit24 = (SimpleSymbol)(new SimpleSymbol("[]")).readResolve();
    Lit23 = new SyntaxTemplate("\001\001\001\003\003", "\013", new Object[0], 0);
    Lit22 = new SyntaxPattern("\f\007\f\017\f\027,\r\037\030\b\b\r' \b\b", new Object[0], 5);
    Lit21 = (SimpleSymbol)(new SimpleSymbol("%define-enum")).readResolve();
    SimpleSymbol simpleSymbol = (SimpleSymbol)(new SimpleSymbol("define-enum")).readResolve();
    Lit11 = simpleSymbol;
    Lit20 = new SyntaxTemplate("\001\001\003", "\021\030\004\021\030\f\t\013\t\020\b\025\023", new Object[] { simpleSymbol, "findkeywords" }, 1);
    Lit19 = new SyntaxPattern("\f\007\f\017\r\027\020\b\b", new Object[0], 3);
    Lit18 = new SyntaxPattern("\f\007\f\017\b", new Object[0], 2);
    Lit17 = new SyntaxPattern("\f\007\b", new Object[0], 1);
    Lit16 = new SyntaxTemplate("\001\001\003\003", "\021\030\004\t\013\031\b\025\023\b\035\033", new Object[] { Lit21 }, 1);
    Lit15 = new SyntaxPattern("\f\007\f\002\f\017,\r\027\020\b\b\r\037\030\b\b", new Object[] { "findkeywords" }, 4);
    Lit14 = new SyntaxTemplate("\001\001\003\001\001\003", "\021\030\004\021\030\f\t\0139\t\033\t#\b\025\023\b-+", new Object[] { Lit11, "findkeywords" }, 1);
    Lit13 = new SyntaxTemplate("\001\001\003\001\001\003", "\033", new Object[0], 0);
    Lit12 = new SyntaxPattern("\f\007\f\002\f\017,\r\027\020\b\b\f\037\f'\r/(\b\b", new Object[] { "findkeywords" }, 6);
    Lit10 = PairWithPosition.make(Lit43, PairWithPosition.make(Lit45, LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/enums.scm", 127013), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/enums.scm", 127013);
    Lit9 = PairWithPosition.make(Lit46, LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/enums.scm", 127000);
    Lit8 = PairWithPosition.make(Lit42, LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/enums.scm", 126990);
    Lit7 = PairWithPosition.make((new SimpleSymbol("values")).readResolve(), LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/enums.scm", 126981);
    Lit6 = PairWithPosition.make(PairWithPosition.make(Lit49, PairWithPosition.make(PairWithPosition.make(Lit50, PairWithPosition.make(Lit42, PairWithPosition.make(Lit47, LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/enums.scm", 90133), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/enums.scm", 90130), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/enums.scm", 90125), PairWithPosition.make(PairWithPosition.make(Lit51, PairWithPosition.make(Lit42, PairWithPosition.make((new SimpleSymbol("int")).readResolve(), LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/enums.scm", 90149), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/enums.scm", 90146), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/enums.scm", 90141), LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/enums.scm", 90141), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/enums.scm", 90125), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/enums.scm", 90117), PairWithPosition.make(Lit48, PairWithPosition.make(PairWithPosition.make(Lit43, PairWithPosition.make((new SimpleSymbol("private")).readResolve(), LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/enums.scm", 94222), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/enums.scm", 94222), PairWithPosition.make(PairWithPosition.make((new SimpleSymbol("invoke-special")).readResolve(), PairWithPosition.make(Lit44, PairWithPosition.make(PairWithPosition.make((new SimpleSymbol("this")).readResolve(), LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/enums.scm", 98340), PairWithPosition.make(PairWithPosition.make(Lit43, PairWithPosition.make(Lit49, LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/enums.scm", 98348), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/enums.scm", 98348), PairWithPosition.make(Lit50, PairWithPosition.make(Lit51, LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/enums.scm", 98359), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/enums.scm", 98355), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/enums.scm", 98347), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/enums.scm", 98340), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/enums.scm", 98325), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/enums.scm", 98309), LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/enums.scm", 98309), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/enums.scm", 94221), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/enums.scm", 94213), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/enums.scm", 90116);
    Lit5 = PairWithPosition.make(Keyword.make("init"), LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/enums.scm", 73741);
    Lit4 = PairWithPosition.make(Lit43, PairWithPosition.make(PairWithPosition.make(Lit52, PairWithPosition.make(Lit53, LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/enums.scm", 69680), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/enums.scm", 69674), LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/enums.scm", 69674), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/enums.scm", 69674);
    Lit3 = PairWithPosition.make(Lit48, LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/enums.scm", 69665);
    Lit2 = PairWithPosition.make(Lit43, PairWithPosition.make(Lit45, LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/enums.scm", 69658), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/enums.scm", 69658);
    Lit1 = PairWithPosition.make(Lit46, LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/enums.scm", 69645);
    Lit0 = PairWithPosition.make(Lit42, LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/enums.scm", 65549);
    $instance = new enums();
    simpleSymbol = Lit11;
    enums enums1 = $instance;
    define$Mnenum = Macro.make(simpleSymbol, (Procedure)new ModuleMethod(enums1, 1, null, 4097), $instance);
    $Prvt$$Pcdefine$Mnenum = Macro.make(Lit21, (Procedure)new ModuleMethod(enums1, 2, null, 4097), $instance);
    $instance.run();
  }
  
  public enums() {
    ModuleInfo.register(this);
  }
  
  static Object lambda1(Object paramObject) {
    Object[] arrayOfObject = SyntaxPattern.allocVars(6, null);
    if (Lit12.match(paramObject, arrayOfObject, 0)) {
      TemplateScope templateScope = TemplateScope.make();
      if (std_syntax.isIdentifier(Lit13.execute(arrayOfObject, templateScope))) {
        paramObject = TemplateScope.make();
        return Lit14.execute(arrayOfObject, (TemplateScope)paramObject);
      } 
    } 
    if (Lit15.match(paramObject, arrayOfObject, 0)) {
      paramObject = TemplateScope.make();
      return Lit16.execute(arrayOfObject, (TemplateScope)paramObject);
    } 
    if (Lit17.match(paramObject, arrayOfObject, 0)) {
      if ("no enum type name given" instanceof Object[]) {
        arrayOfObject = (Object[])"no enum type name given";
        return prim_syntax.syntaxError(paramObject, arrayOfObject);
      } 
      arrayOfObject = new Object[] { "no enum type name given" };
      return prim_syntax.syntaxError(paramObject, arrayOfObject);
    } 
    if (Lit18.match(paramObject, arrayOfObject, 0)) {
      if ("no enum constants given" instanceof Object[]) {
        arrayOfObject = (Object[])"no enum constants given";
        return prim_syntax.syntaxError(paramObject, arrayOfObject);
      } 
      arrayOfObject = new Object[] { "no enum constants given" };
      return prim_syntax.syntaxError(paramObject, arrayOfObject);
    } 
    if (Lit19.match(paramObject, arrayOfObject, 0)) {
      paramObject = TemplateScope.make();
      return Lit20.execute(arrayOfObject, (TemplateScope)paramObject);
    } 
    return syntax_case.error("syntax-case", paramObject);
  }
  
  static Object lambda2(Object paramObject) {
    Object[] arrayOfObject = SyntaxPattern.allocVars(5, null);
    if (Lit22.match(paramObject, arrayOfObject, 0)) {
      TemplateScope templateScope = TemplateScope.make();
      Object object = Lit23.execute(arrayOfObject, templateScope);
      try {
        Symbol symbol = (Symbol)object;
        SimpleSymbol simpleSymbol = symbolAppend$V(new Object[] { symbol, Lit24 });
        object = TemplateScope.make();
        object = Lit25.execute(arrayOfObject, (TemplateScope)object);
        try {
          LList lList = (LList)object;
          lists.length(lList);
          object = mapNames(symbol, lList, 0);
          PairWithPosition pairWithPosition = makeInit();
          Pair pair = makeValues((Symbol)simpleSymbol, lList);
          TemplateScope templateScope1 = TemplateScope.make();
          Object object1 = Lit26.execute(arrayOfObject, templateScope1);
          try {
            LList lList1 = (LList)object1;
            object1 = TemplateScope.make();
            object1 = Lit27.execute(arrayOfObject, (TemplateScope)object1);
            try {
              LList lList2 = (LList)object1;
              object1 = TemplateScope.make();
              return Quote.append$V(new Object[] { Lit28.execute(arrayOfObject, (TemplateScope)object1), Quote.consX$V(new Object[] { std_syntax.datum$To$SyntaxObject(paramObject, symbol), Pair.make(Lit29.execute(arrayOfObject, (TemplateScope)object1), Quote.append$V(new Object[] { Lit30.execute(arrayOfObject, (TemplateScope)object1), Pair.make(Lit31.execute(arrayOfObject, (TemplateScope)object1), Quote.append$V(new Object[] { std_syntax.datum$To$SyntaxObject(paramObject, lList1), Quote.consX$V(new Object[] { std_syntax.datum$To$SyntaxObject(paramObject, pairWithPosition), Quote.consX$V(new Object[] { std_syntax.datum$To$SyntaxObject(paramObject, pair), Pair.make(Pair.make(Lit32.execute(arrayOfObject, (TemplateScope)object1), Quote.append$V(new Object[] { Lit33.execute(arrayOfObject, (TemplateScope)object1), Quote.consX$V(new Object[] { std_syntax.datum$To$SyntaxObject(paramObject, symbol), Quote.append$V(new Object[] { Lit34.execute(arrayOfObject, (TemplateScope)object1), Pair.make(Lit35.execute(arrayOfObject, (TemplateScope)object1), Pair.make(Pair.make(Lit36.execute(arrayOfObject, (TemplateScope)object1), Quote.consX$V(new Object[] { std_syntax.datum$To$SyntaxObject(paramObject, symbol), Lit37.execute(arrayOfObject, (TemplateScope)object1) })), Lit38.execute(arrayOfObject, (TemplateScope)object1))) }) }) })), Quote.append$V(new Object[] { std_syntax.datum$To$SyntaxObject(paramObject, object), Quote.append$V(new Object[] { std_syntax.datum$To$SyntaxObject(paramObject, lList2), Lit39.execute(arrayOfObject, (TemplateScope)object1) }) })) }) }) })) })) }) });
            } catch (ClassCastException null) {
              throw new WrongType(classCastException, "other-defs", -2, object1);
            } 
          } catch (ClassCastException null) {
            throw new WrongType(classCastException, "opts", -2, object1);
          } 
        } catch (ClassCastException null) {
          throw new WrongType(classCastException, "e-names", -2, object);
        } 
      } catch (ClassCastException classCastException) {
        throw new WrongType(classCastException, "t-name", -2, object);
      } 
    } 
    return syntax_case.error("syntax-case", classCastException);
  }
  
  static Object makeFieldDesc(Symbol paramSymbol1, Symbol paramSymbol2, int paramInt) {
    return Quote.consX$V(new Object[] { paramSymbol2, Quote.append$V(new Object[] { Lit0, Quote.consX$V(new Object[] { paramSymbol1, Quote.append$V(new Object[] { Lit1, Pair.make(Lit2, Quote.append$V(new Object[] { Lit3, Pair.make(Lit4, Quote.append$V(new Object[] { Lit5, Pair.make(Quote.consX$V(new Object[] { paramSymbol1, Quote.consX$V(new Object[] { misc.symbol$To$String(paramSymbol2), Quote.consX$V(new Object[] { Integer.valueOf(paramInt), LList.Empty }, ) }, ) }, ), LList.Empty) })) })) }) }) }) });
  }
  
  static PairWithPosition makeInit() {
    return Lit6;
  }
  
  static Pair makeValues(Symbol paramSymbol, LList paramLList) {
    return Pair.make(Lit7, Quote.append$V(new Object[] { Lit8, Quote.consX$V(new Object[] { paramSymbol, Quote.append$V(new Object[] { Lit9, Pair.make(Lit10, Pair.make(Quote.consX$V(new Object[] { paramSymbol, Quote.append$V(new Object[] { paramLList, LList.Empty }, ) }, ), LList.Empty)) }) }) }));
  }
  
  static LList mapNames(Symbol paramSymbol, LList paramLList, int paramInt) {
    if (lists.isNull(paramLList))
      return LList.Empty; 
    Object object = lists.car.apply1(paramLList);
    try {
      Symbol symbol = (Symbol)object;
      object = makeFieldDesc(paramSymbol, symbol, paramInt);
      Object object1 = lists.cdr.apply1(paramLList);
      try {
        LList lList = (LList)object1;
        return (LList)lists.cons(object, mapNames(paramSymbol, lList, paramInt + 1));
      } catch (ClassCastException classCastException) {
        throw new WrongType(classCastException, "map-names", 1, object1);
      } 
    } catch (ClassCastException classCastException) {
      throw new WrongType(classCastException, "make-field-desc", 1, object);
    } 
  }
  
  static SimpleSymbol symbolAppend$V(Object[] paramArrayOfObject) {
    LList lList2 = LList.makeList(paramArrayOfObject, 0);
    Apply apply = Scheme.apply;
    ModuleMethod moduleMethod = strings.string$Mnappend;
    LList lList1 = LList.Empty;
    while (true) {
      Object object1;
      Object object2;
      if (lList2 == LList.Empty) {
        object1 = apply.apply2(moduleMethod, LList.reverseInPlace(lList1));
        try {
          CharSequence charSequence = (CharSequence)object1;
          return misc.string$To$Symbol(charSequence);
        } catch (ClassCastException null) {
          throw new WrongType(object2, "string->symbol", 1, object1);
        } 
      } 
      try {
        Pair pair = (Pair)object2;
        object2 = pair.getCdr();
        Object object = pair.getCar();
        try {
          Symbol symbol = (Symbol)object;
          object1 = Pair.make(misc.symbol$To$String(symbol), object1);
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "symbol->string", 1, object);
        } 
      } catch (ClassCastException classCastException) {
        throw new WrongType(classCastException, "arg0", -2, object2);
      } 
    } 
  }
  
  public Object apply1(ModuleMethod paramModuleMethod, Object paramObject) {
    switch (paramModuleMethod.selector) {
      default:
        return super.apply1(paramModuleMethod, paramObject);
      case 1:
        return lambda1(paramObject);
      case 2:
        break;
    } 
    return lambda2(paramObject);
  }
  
  public int match1(ModuleMethod paramModuleMethod, Object paramObject, CallContext paramCallContext) {
    switch (paramModuleMethod.selector) {
      default:
        return super.match1(paramModuleMethod, paramObject, paramCallContext);
      case 2:
        paramCallContext.value1 = paramObject;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 1;
        return 0;
      case 1:
        break;
    } 
    paramCallContext.value1 = paramObject;
    paramCallContext.proc = (Procedure)paramModuleMethod;
    paramCallContext.pc = 1;
    return 0;
  }
  
  public final void run(CallContext paramCallContext) {
    Consumer consumer = paramCallContext.consumer;
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/gnu/kawa/slib/enums.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */