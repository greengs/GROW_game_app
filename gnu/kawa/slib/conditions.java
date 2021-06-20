package gnu.kawa.slib;

import gnu.expr.ModuleBody;
import gnu.expr.ModuleInfo;
import gnu.expr.ModuleMethod;
import gnu.kawa.functions.Apply;
import gnu.kawa.functions.IsEq;
import gnu.lists.Consumer;
import gnu.lists.LList;
import gnu.lists.Pair;
import gnu.lists.PairWithPosition;
import gnu.mapping.CallContext;
import gnu.mapping.Procedure;
import gnu.mapping.SimpleSymbol;
import gnu.mapping.Symbol;
import gnu.mapping.Values;
import gnu.mapping.WrongType;
import kawa.lang.Macro;
import kawa.lang.SyntaxPattern;
import kawa.lang.SyntaxRule;
import kawa.lang.SyntaxRules;
import kawa.lib.lists;
import kawa.lib.misc;
import kawa.standard.Scheme;
import kawa.standard.append;

public class conditions extends ModuleBody {
  public static Object $Amcondition;
  
  public static Object $Amerror;
  
  public static Object $Ammessage;
  
  public static Object $Amserious;
  
  static final Class $Lscondition$Mntype$Gr;
  
  public static final Class $Prvt$$Lscondition$Gr;
  
  public static final ModuleMethod $Prvt$type$Mnfield$Mnalist$Mn$Grcondition;
  
  public static final conditions $instance;
  
  static final SimpleSymbol Lit0;
  
  static final SimpleSymbol Lit1;
  
  static final SimpleSymbol Lit10;
  
  static final SimpleSymbol Lit11;
  
  static final SimpleSymbol Lit12;
  
  static final SimpleSymbol Lit13;
  
  static final SyntaxRules Lit14;
  
  static final SimpleSymbol Lit15;
  
  static final SimpleSymbol Lit16;
  
  static final SimpleSymbol Lit17;
  
  static final SimpleSymbol Lit18;
  
  static final SyntaxRules Lit19;
  
  static final PairWithPosition Lit2;
  
  static final SimpleSymbol Lit20;
  
  static final SimpleSymbol Lit21;
  
  static final SimpleSymbol Lit22 = (SimpleSymbol)(new SimpleSymbol("thing")).readResolve();
  
  static final SimpleSymbol Lit3;
  
  static final SimpleSymbol Lit4;
  
  static final SimpleSymbol Lit5;
  
  static final SimpleSymbol Lit6;
  
  static final SimpleSymbol Lit7;
  
  static final SimpleSymbol Lit8;
  
  static final SyntaxRules Lit9;
  
  public static final Macro condition;
  
  public static final ModuleMethod condition$Mnhas$Mntype$Qu;
  
  public static final ModuleMethod condition$Mnref;
  
  static final Macro condition$Mntype$Mnfield$Mnalist;
  
  public static final ModuleMethod condition$Mntype$Qu;
  
  public static final ModuleMethod condition$Qu;
  
  public static final Macro define$Mncondition$Mntype;
  
  public static final ModuleMethod extract$Mncondition;
  
  public static final ModuleMethod make$Mncompound$Mncondition;
  
  public static final ModuleMethod make$Mncondition;
  
  public static final ModuleMethod make$Mncondition$Mntype;
  
  static {
    Lit21 = (SimpleSymbol)(new SimpleSymbol("quote")).readResolve();
    Lit20 = (SimpleSymbol)(new SimpleSymbol("type-field-alist->condition")).readResolve();
    SimpleSymbol simpleSymbol1 = (SimpleSymbol)(new SimpleSymbol("condition")).readResolve();
    Lit18 = simpleSymbol1;
    SyntaxRule syntaxRule2 = new SyntaxRule(new SyntaxPattern("\f\030]\f\007-\f\017\f\027\b\b\020\b\000\030\b", new Object[0], 3), "\003\005\005", "\021\030\004\b\021\030\f\b\005\021\030\024\t\003\b\021\030\f\b\r\021\030\024)\021\030\034\b\013\b\023", new Object[] { Lit20, (new SimpleSymbol("list")).readResolve(), (new SimpleSymbol("cons")).readResolve(), Lit21 }, 2);
    Lit19 = new SyntaxRules(new Object[] { simpleSymbol1 }, new SyntaxRule[] { syntaxRule2 }, 3);
    Lit17 = (SimpleSymbol)(new SimpleSymbol("extract-condition")).readResolve();
    Lit16 = (SimpleSymbol)(new SimpleSymbol("make-compound-condition")).readResolve();
    Lit15 = (SimpleSymbol)(new SimpleSymbol("condition-ref")).readResolve();
    simpleSymbol1 = (SimpleSymbol)(new SimpleSymbol("condition-type-field-alist")).readResolve();
    Lit13 = simpleSymbol1;
    syntaxRule2 = new SyntaxRule(new SyntaxPattern("\f\030\f\007\b", new Object[0], 1), "\001", "\021\030\004\b\021\030\f\021\030\024\b\003", new Object[] { PairWithPosition.make((new SimpleSymbol("$lookup$")).readResolve(), Pair.make((new SimpleSymbol("*")).readResolve(), Pair.make(Pair.make((new SimpleSymbol("quasiquote")).readResolve(), Pair.make((new SimpleSymbol(".type-field-alist")).readResolve(), LList.Empty)), LList.Empty)), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/conditions.scm", 581639), (new SimpleSymbol("as")).readResolve(), (new SimpleSymbol("<condition>")).readResolve() }0);
    Lit14 = new SyntaxRules(new Object[] { simpleSymbol1 }, new SyntaxRule[] { syntaxRule2 }, 1);
    Lit12 = (SimpleSymbol)(new SimpleSymbol("condition-has-type?")).readResolve();
    Lit11 = (SimpleSymbol)(new SimpleSymbol("make-condition")).readResolve();
    Lit10 = (SimpleSymbol)(new SimpleSymbol("condition?")).readResolve();
    simpleSymbol1 = (SimpleSymbol)(new SimpleSymbol("define-condition-type")).readResolve();
    Lit8 = simpleSymbol1;
    SyntaxPattern syntaxPattern = new SyntaxPattern("\f\030\f\007\f\017\f\027-\f\037\f'\b\030\020\b", new Object[0], 5);
    SimpleSymbol simpleSymbol2 = (SimpleSymbol)(new SimpleSymbol("begin")).readResolve();
    SimpleSymbol simpleSymbol3 = (SimpleSymbol)(new SimpleSymbol("define")).readResolve();
    SimpleSymbol simpleSymbol4 = (SimpleSymbol)(new SimpleSymbol("make-condition-type")).readResolve();
    Lit7 = simpleSymbol4;
    SyntaxRule syntaxRule1 = new SyntaxRule(syntaxPattern, "\001\001\001\003\003", "\021\030\004É\021\030\f\t\003\b\021\030\024)\021\030\034\b\003\t\013\b\021\030\034\b\b\035\033Á\021\030\f!\t\023\030$\b\021\030,\021\0304\b\021\030<\021\030D\b\003\b%\021\030\f!\t#\030L\b\021\030TA\021\030\\\021\030d\b\003\b\021\030\034\b\033", new Object[] { 
          simpleSymbol2, simpleSymbol3, simpleSymbol4, Lit21, PairWithPosition.make(Lit22, LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/conditions.scm", 327708), (new SimpleSymbol("and")).readResolve(), PairWithPosition.make(Lit10, PairWithPosition.make(Lit22, LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/conditions.scm", 331803), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/conditions.scm", 331791), Lit12, Lit22, PairWithPosition.make(Lit18, LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/conditions.scm", 339996), 
          Lit15, Lit17, Lit18 }1);
    Lit9 = new SyntaxRules(new Object[] { simpleSymbol1 }, new SyntaxRule[] { syntaxRule1 }, 5);
    Lit6 = (SimpleSymbol)(new SimpleSymbol("condition-type?")).readResolve();
    Lit5 = (SimpleSymbol)(new SimpleSymbol("message")).readResolve();
    Lit4 = (SimpleSymbol)(new SimpleSymbol("&error")).readResolve();
    Lit3 = (SimpleSymbol)(new SimpleSymbol("&serious")).readResolve();
    Lit2 = PairWithPosition.make(Lit5, LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/conditions.scm", 925699);
    Lit1 = (SimpleSymbol)(new SimpleSymbol("&message")).readResolve();
    Lit0 = (SimpleSymbol)(new SimpleSymbol("&condition")).readResolve();
    $instance = new conditions();
    $Lscondition$Mntype$Gr = ntype.class;
    conditions conditions1 = $instance;
    condition$Mntype$Qu = new ModuleMethod(conditions1, 2, Lit6, 4097);
    make$Mncondition$Mntype = new ModuleMethod(conditions1, 3, Lit7, 12291);
    define$Mncondition$Mntype = Macro.make(Lit8, (Procedure)Lit9, $instance);
    $Prvt$$Lscondition$Gr = condition.class;
    condition$Qu = new ModuleMethod(conditions1, 4, Lit10, 4097);
    make$Mncondition = new ModuleMethod(conditions1, 5, Lit11, -4095);
    condition$Mnhas$Mntype$Qu = new ModuleMethod(conditions1, 6, Lit12, 8194);
    condition$Mntype$Mnfield$Mnalist = Macro.make(Lit13, (Procedure)Lit14, $instance);
    condition$Mnref = new ModuleMethod(conditions1, 7, Lit15, 8194);
    make$Mncompound$Mncondition = new ModuleMethod(conditions1, 8, Lit16, -4095);
    extract$Mncondition = new ModuleMethod(conditions1, 9, Lit17, 8194);
    condition = Macro.make(Lit18, (Procedure)Lit19, $instance);
    $Prvt$type$Mnfield$Mnalist$Mn$Grcondition = new ModuleMethod(conditions1, 10, Lit20, 4097);
    $instance.run();
  }
  
  public conditions() {
    ModuleInfo.register(this);
  }
  
  static Object checkConditionTypeFieldAlist(Object paramObject) {
    Object object = paramObject;
    label39: while (true) {
      if (!lists.isNull(object)) {
        Object object2 = lists.car.apply1(object);
        Object object1 = lists.car.apply1(object2);
        try {
          ntype ntype = (ntype)object1;
          object1 = lists.cdr.apply1(object2);
          object2 = LList.Empty;
          while (true) {
            if (object1 == LList.Empty) {
              object1 = LList.reverseInPlace(object2);
              object2 = ntype.all$Mnfields;
              object1 = srfi1.lsetDifference$V((Procedure)Scheme.isEq, object2, new Object[] { object1 });
              label36: while (true) {
                if (object1 == LList.Empty) {
                  object = lists.cdr.apply1(object);
                  continue label39;
                } 
                try {
                  object2 = object1;
                  Object object4 = object2.getCar();
                  Object object3 = conditionTypeFieldSupertype(ntype, object4);
                  object1 = paramObject;
                  while (true) {
                    Object object5 = lists.car.apply1(lists.car.apply1(object1));
                    try {
                      ntype ntype1 = (ntype)object5;
                      try {
                        object5 = object3;
                        boolean bool = isConditionSubtype(ntype1, (ntype)object5);
                        if (bool) {
                          if (!bool)
                            misc.error$V("missing field in condition construction", new Object[] { ntype, object4 }); 
                          object1 = object2.getCdr();
                          continue label36;
                        } 
                        object1 = lists.cdr.apply1(object1);
                      } catch (ClassCastException classCastException) {
                        throw new WrongType(classCastException, "condition-subtype?", 1, object3);
                      } 
                    } catch (ClassCastException classCastException) {
                      throw new WrongType(classCastException, "condition-subtype?", 0, object5);
                    } 
                  } 
                } catch (ClassCastException classCastException) {
                  throw new WrongType(classCastException, "arg0", -2, object1);
                } 
                break;
              } 
              break;
            } 
            try {
              Pair pair = (Pair)object1;
              object1 = pair.getCdr();
              object2 = Pair.make(lists.car.apply1(pair.getCar()), object2);
            } catch (ClassCastException classCastException) {
              throw new WrongType(classCastException, "arg0", -2, object1);
            } 
          } 
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "type", -2, object1);
        } 
        break;
      } 
      return Values.empty;
    } 
  }
  
  static Object conditionMessage(Object paramObject) {
    try {
      condition condition = (condition)paramObject;
      paramObject = $Ammessage;
      try {
        ntype ntype = (ntype)paramObject;
        return conditionRef(extractCondition(condition, ntype), Lit5);
      } catch (ClassCastException classCastException) {
        throw new WrongType(classCastException, "extract-condition", 1, paramObject);
      } 
    } catch (ClassCastException classCastException) {
      throw new WrongType(classCastException, "extract-condition", 0, paramObject);
    } 
  }
  
  public static Object conditionRef(condition paramcondition, Object paramObject) {
    return typeFieldAlistRef(paramcondition.type$Mnfield$Mnalist, paramObject);
  }
  
  static Object conditionTypeFieldSupertype(ntype paramntype, Object paramObject) {
    while (true) {
      if (paramntype == Boolean.FALSE)
        return Boolean.FALSE; 
      ntype ntype1 = paramntype;
      if (lists.memq(paramObject, paramntype.fields) == Boolean.FALSE) {
        paramntype = (ntype)paramntype.supertype;
        continue;
      } 
      return ntype1;
    } 
  }
  
  static Object conditionTypes(Object paramObject) {
    paramObject = ((condition)paramObject).type$Mnfield$Mnalist;
    LList lList = LList.Empty;
    while (true) {
      if (paramObject == LList.Empty)
        return LList.reverseInPlace(lList); 
      try {
        Pair pair2 = (Pair)paramObject;
        paramObject = pair2.getCdr();
        Pair pair1 = Pair.make(lists.car.apply1(pair2.getCar()), lList);
      } catch (ClassCastException classCastException) {
        throw new WrongType(classCastException, "arg0", -2, paramObject);
      } 
    } 
  }
  
  public static condition extractCondition(condition paramcondition, ntype paramntype) {
    frame frame = new frame();
    frame.type = paramntype;
    Object object2 = srfi1.find(frame.lambda$Fn1, paramcondition.type$Mnfield$Mnalist);
    if (object2 == Boolean.FALSE)
      misc.error$V("extract-condition: invalid condition type", new Object[] { paramcondition, frame.type }); 
    ntype ntype1 = frame.type;
    Object object1 = frame.type.all$Mnfields;
    LList lList = LList.Empty;
    while (true) {
      if (object1 == LList.Empty)
        return new condition(LList.list1(lists.cons(ntype1, LList.reverseInPlace(lList)))); 
      try {
        Pair pair2 = (Pair)object1;
        object1 = pair2.getCdr();
        Pair pair1 = Pair.make(lists.assq(pair2.getCar(), lists.cdr.apply1(object2)), lList);
      } catch (ClassCastException classCastException) {
        throw new WrongType(classCastException, "arg0", -2, object1);
      } 
    } 
  }
  
  public static boolean isCondition(Object paramObject) {
    return paramObject instanceof condition;
  }
  
  public static boolean isConditionHasType(Object paramObject, ntype paramntype) {
    paramObject = conditionTypes(paramObject);
    while (true) {
      Object object = lists.car.apply1(paramObject);
      try {
        ntype ntype1 = (ntype)object;
        boolean bool = isConditionSubtype(ntype1, paramntype);
        if (bool)
          return bool; 
        paramObject = lists.cdr.apply1(paramObject);
      } catch (ClassCastException classCastException) {
        throw new WrongType(classCastException, "condition-subtype?", 0, object);
      } 
    } 
  }
  
  static boolean isConditionSubtype(ntype paramntype1, ntype paramntype2) {
    while (true) {
      if (paramntype1 == Boolean.FALSE)
        return false; 
      if (paramntype1 == paramntype2)
        return true; 
      paramntype1 = (ntype)paramntype1.supertype;
    } 
  }
  
  public static boolean isConditionType(Object paramObject) {
    return paramObject instanceof ntype;
  }
  
  static boolean isError(Object paramObject) {
    boolean bool2 = isCondition(paramObject);
    boolean bool1 = bool2;
    if (bool2) {
      Object object = $Amerror;
      try {
        ntype ntype = (ntype)object;
        return isConditionHasType(paramObject, ntype);
      } catch (ClassCastException classCastException) {
        throw new WrongType(classCastException, "condition-has-type?", 1, object);
      } 
    } 
    return bool1;
  }
  
  static boolean isMessageCondition(Object paramObject) {
    boolean bool2 = isCondition(paramObject);
    boolean bool1 = bool2;
    if (bool2) {
      Object object = $Ammessage;
      try {
        ntype ntype = (ntype)object;
        return isConditionHasType(paramObject, ntype);
      } catch (ClassCastException classCastException) {
        throw new WrongType(classCastException, "condition-has-type?", 1, object);
      } 
    } 
    return bool1;
  }
  
  static boolean isSeriousCondition(Object paramObject) {
    boolean bool2 = isCondition(paramObject);
    boolean bool1 = bool2;
    if (bool2) {
      Object object = $Amserious;
      try {
        ntype ntype = (ntype)object;
        return isConditionHasType(paramObject, ntype);
      } catch (ClassCastException classCastException) {
        throw new WrongType(classCastException, "condition-has-type?", 1, object);
      } 
    } 
    return bool1;
  }
  
  public static Object lambda1label(Object paramObject) {
    return lists.isNull(paramObject) ? LList.Empty : lists.cons(lists.cons(lists.car.apply1(paramObject), lists.cadr.apply1(paramObject)), lambda1label(lists.cddr.apply1(paramObject)));
  }
  
  public static condition makeCompoundCondition$V(Object paramObject, Object[] paramArrayOfObject) {
    LList lList = LList.makeList(paramArrayOfObject, 0);
    Apply apply = Scheme.apply;
    append append = append.append;
    paramObject = lists.cons(paramObject, lList);
    lList = LList.Empty;
    while (true) {
      if (paramObject == LList.Empty)
        return new condition(apply.apply2(append, LList.reverseInPlace(lList))); 
      try {
        Pair pair2 = (Pair)paramObject;
        paramObject = pair2.getCdr();
        Pair pair1 = Pair.make(Scheme.applyToArgs.apply2(condition$Mntype$Mnfield$Mnalist, pair2.getCar()), lList);
      } catch (ClassCastException classCastException) {
        throw new WrongType(classCastException, "arg0", -2, paramObject);
      } 
    } 
  }
  
  public static condition makeCondition$V(ntype paramntype, Object[] paramArrayOfObject) {
    Object object2 = lambda1label(LList.makeList(paramArrayOfObject, 0));
    IsEq isEq = Scheme.isEq;
    Object object3 = paramntype.all$Mnfields;
    LList lList = LList.Empty;
    Object object1 = object2;
    while (true) {
      if (object1 == LList.Empty) {
        if (srfi1.lset$Eq$V((Procedure)isEq, new Object[] { object3, LList.reverseInPlace(lList) }) == Boolean.FALSE)
          misc.error$V("condition fields don't match condition type", new Object[0]); 
        return new condition(LList.list1(lists.cons(paramntype, object2)));
      } 
      try {
        Pair pair2 = (Pair)object1;
        object1 = pair2.getCdr();
        Pair pair1 = Pair.make(lists.car.apply1(pair2.getCar()), lList);
      } catch (ClassCastException classCastException) {
        throw new WrongType(classCastException, "arg0", -2, object1);
      } 
    } 
  }
  
  public static ntype makeConditionType(Symbol paramSymbol, ntype paramntype, Object paramObject) {
    if (!lists.isNull(srfi1.lsetIntersection$V((Procedure)Scheme.isEq, paramntype.all$Mnfields, new Object[] { paramObject })))
      misc.error$V("duplicate field name", new Object[0]); 
    return new ntype(paramSymbol, paramntype, paramObject, append.append$V(new Object[] { paramntype.all$Mnfields, paramObject }));
  }
  
  public static condition typeFieldAlist$To$Condition(Object paramObject) {
    LList lList = LList.Empty;
    Object object = paramObject;
    label20: while (true) {
      if (object == LList.Empty)
        return new condition(LList.reverseInPlace(lList)); 
      try {
        Pair pair = (Pair)object;
        Object object2 = pair.getCdr();
        Object object3 = pair.getCar();
        Object object4 = lists.car.apply1(object3);
        Object object1 = ((ntype)lists.car.apply1(object3)).all$Mnfields;
        object = LList.Empty;
        while (true) {
          if (object1 == LList.Empty) {
            Pair pair1 = Pair.make(lists.cons(object4, LList.reverseInPlace(object)), lList);
            object = object2;
            continue label20;
          } 
          try {
            Pair pair1 = (Pair)object1;
            Object object5 = pair1.getCdr();
            Object object6 = pair1.getCar();
            object1 = lists.assq(object6, lists.cdr.apply1(object3));
            if (object1 == Boolean.FALSE)
              object1 = lists.cons(object6, typeFieldAlistRef(paramObject, object6)); 
            object = Pair.make(object1, object);
            object1 = object5;
          } catch (ClassCastException classCastException) {
            throw new WrongType(classCastException, "arg0", -2, object1);
          } 
        } 
      } catch (ClassCastException classCastException) {
        throw new WrongType(classCastException, "arg0", -2, object);
      } 
      break;
    } 
  }
  
  static Object typeFieldAlistRef(Object paramObject1, Object paramObject2) {
    while (true) {
      if (lists.isNull(paramObject1))
        return misc.error$V("type-field-alist-ref: field not found", new Object[] { paramObject1, paramObject2 }); 
      Object object = lists.assq(paramObject2, lists.cdr.apply1(lists.car.apply1(paramObject1)));
      if (object != Boolean.FALSE)
        return lists.cdr.apply1(object); 
      paramObject1 = lists.cdr.apply1(paramObject1);
    } 
  }
  
  public Object apply1(ModuleMethod paramModuleMethod, Object paramObject) {
    switch (paramModuleMethod.selector) {
      default:
        return super.apply1(paramModuleMethod, paramObject);
      case 2:
        return isConditionType(paramObject) ? Boolean.TRUE : Boolean.FALSE;
      case 4:
        return isCondition(paramObject) ? Boolean.TRUE : Boolean.FALSE;
      case 10:
        break;
    } 
    return typeFieldAlist$To$Condition(paramObject);
  }
  
  public Object apply2(ModuleMethod paramModuleMethod, Object paramObject1, Object paramObject2) {
    switch (paramModuleMethod.selector) {
      default:
        return super.apply2(paramModuleMethod, paramObject1, paramObject2);
      case 6:
        try {
          ntype ntype = (ntype)paramObject2;
          return isConditionHasType(paramObject1, ntype) ? Boolean.TRUE : Boolean.FALSE;
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "condition-has-type?", 2, paramObject2);
        } 
      case 7:
        try {
          condition condition = (condition)paramObject1;
          return conditionRef(condition, paramObject2);
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "condition-ref", 1, paramObject1);
        } 
      case 9:
        break;
    } 
    try {
      condition condition = (condition)paramObject1;
      try {
        paramObject1 = paramObject2;
        return extractCondition(condition, (ntype)paramObject1);
      } catch (ClassCastException classCastException) {
        throw new WrongType(classCastException, "extract-condition", 2, paramObject2);
      } 
    } catch (ClassCastException classCastException) {
      throw new WrongType(classCastException, "extract-condition", 1, paramObject1);
    } 
  }
  
  public Object apply3(ModuleMethod paramModuleMethod, Object paramObject1, Object paramObject2, Object paramObject3) {
    if (paramModuleMethod.selector == 3)
      try {
        Symbol symbol = (Symbol)paramObject1;
        try {
          paramObject1 = paramObject2;
          return makeConditionType(symbol, (ntype)paramObject1, paramObject3);
        } catch (ClassCastException null) {
          throw new WrongType(classCastException, "make-condition-type", 2, paramObject2);
        } 
      } catch (ClassCastException classCastException) {
        throw new WrongType(classCastException, "make-condition-type", 1, paramObject1);
      }  
    return super.apply3((ModuleMethod)classCastException, paramObject1, paramObject2, paramObject3);
  }
  
  public Object applyN(ModuleMethod paramModuleMethod, Object[] paramArrayOfObject) {
    switch (paramModuleMethod.selector) {
      default:
        return super.applyN(paramModuleMethod, paramArrayOfObject);
      case 5:
        object = paramArrayOfObject[0];
        try {
          ntype ntype = (ntype)object;
          int j = paramArrayOfObject.length - 1;
          object = new Object[j];
          while (true) {
            if (--j < 0)
              return makeCondition$V(ntype, (Object[])object); 
            object[j] = paramArrayOfObject[j + 1];
          } 
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "make-condition", 1, object);
        } 
      case 8:
        break;
    } 
    Object object = classCastException[0];
    int i = classCastException.length - 1;
    Object[] arrayOfObject = new Object[i];
    while (true) {
      if (--i < 0)
        return makeCompoundCondition$V(object, arrayOfObject); 
      arrayOfObject[i] = classCastException[i + 1];
    } 
  }
  
  public int match1(ModuleMethod paramModuleMethod, Object paramObject, CallContext paramCallContext) {
    switch (paramModuleMethod.selector) {
      default:
        return super.match1(paramModuleMethod, paramObject, paramCallContext);
      case 10:
        paramCallContext.value1 = paramObject;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 1;
        return 0;
      case 4:
        paramCallContext.value1 = paramObject;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 1;
        return 0;
      case 2:
        break;
    } 
    paramCallContext.value1 = paramObject;
    paramCallContext.proc = (Procedure)paramModuleMethod;
    paramCallContext.pc = 1;
    return 0;
  }
  
  public int match2(ModuleMethod paramModuleMethod, Object paramObject1, Object paramObject2, CallContext paramCallContext) {
    null = -786431;
    switch (paramModuleMethod.selector) {
      default:
        return super.match2(paramModuleMethod, paramObject1, paramObject2, paramCallContext);
      case 9:
        if (paramObject1 instanceof condition) {
          paramCallContext.value1 = paramObject1;
          if (!(paramObject2 instanceof ntype))
            return -786430; 
          paramCallContext.value2 = paramObject2;
          paramCallContext.proc = (Procedure)paramModuleMethod;
          paramCallContext.pc = 2;
          return 0;
        } 
        return SYNTHETIC_LOCAL_VARIABLE_5;
      case 7:
        if (paramObject1 instanceof condition) {
          paramCallContext.value1 = paramObject1;
          paramCallContext.value2 = paramObject2;
          paramCallContext.proc = (Procedure)paramModuleMethod;
          paramCallContext.pc = 2;
          return 0;
        } 
        return SYNTHETIC_LOCAL_VARIABLE_5;
      case 6:
        break;
    } 
    paramCallContext.value1 = paramObject1;
    if (!(paramObject2 instanceof ntype))
      return -786430; 
    paramCallContext.value2 = paramObject2;
    paramCallContext.proc = (Procedure)paramModuleMethod;
    paramCallContext.pc = 2;
    return 0;
  }
  
  public int match3(ModuleMethod paramModuleMethod, Object paramObject1, Object paramObject2, Object paramObject3, CallContext paramCallContext) {
    if (paramModuleMethod.selector == 3) {
      if (!(paramObject1 instanceof Symbol))
        return -786431; 
      paramCallContext.value1 = paramObject1;
      if (!(paramObject2 instanceof ntype))
        return -786430; 
      paramCallContext.value2 = paramObject2;
      paramCallContext.value3 = paramObject3;
      paramCallContext.proc = (Procedure)paramModuleMethod;
      paramCallContext.pc = 3;
      return 0;
    } 
    return super.match3(paramModuleMethod, paramObject1, paramObject2, paramObject3, paramCallContext);
  }
  
  public int matchN(ModuleMethod paramModuleMethod, Object[] paramArrayOfObject, CallContext paramCallContext) {
    switch (paramModuleMethod.selector) {
      default:
        return super.matchN(paramModuleMethod, paramArrayOfObject, paramCallContext);
      case 8:
        paramCallContext.values = paramArrayOfObject;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 5;
        return 0;
      case 5:
        break;
    } 
    paramCallContext.values = paramArrayOfObject;
    paramCallContext.proc = (Procedure)paramModuleMethod;
    paramCallContext.pc = 5;
    return 0;
  }
  
  public final void run(CallContext paramCallContext) {
    Consumer consumer = paramCallContext.consumer;
    $Amcondition = new ntype(Lit0, Boolean.FALSE, LList.Empty, LList.Empty);
    SimpleSymbol simpleSymbol = Lit1;
    Object object = $Amcondition;
    try {
      ntype ntype = (ntype)object;
      $Ammessage = makeConditionType((Symbol)simpleSymbol, ntype, Lit2);
      simpleSymbol = Lit3;
      object = $Amcondition;
      try {
        ntype = (ntype)object;
        $Amserious = makeConditionType((Symbol)simpleSymbol, ntype, LList.Empty);
        simpleSymbol = Lit4;
        object = $Amserious;
        try {
          ntype = (ntype)object;
          $Amerror = makeConditionType((Symbol)simpleSymbol, ntype, LList.Empty);
          return;
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "make-condition-type", 1, object);
        } 
      } catch (ClassCastException classCastException) {
        throw new WrongType(classCastException, "make-condition-type", 1, object);
      } 
    } catch (ClassCastException classCastException) {
      throw new WrongType(classCastException, "make-condition-type", 1, object);
    } 
  }
  
  public class ntype {
    public Object all$Mnfields;
    
    public Object fields;
    
    public Object name;
    
    public Object supertype;
    
    public ntype(conditions this$0, Object param1Object1, Object param1Object2, Object param1Object3) {
      this.name = this$0;
      this.supertype = param1Object1;
      this.fields = param1Object2;
      this.all$Mnfields = param1Object3;
    }
    
    public String toString() {
      StringBuffer stringBuffer = new StringBuffer("#<condition-type ");
      stringBuffer.append(this.name);
      stringBuffer.append(">");
      return stringBuffer.toString();
    }
  }
  
  public class frame extends ModuleBody {
    final ModuleMethod lambda$Fn1;
    
    conditions.ntype type;
    
    public frame() {
      ModuleMethod moduleMethod = new ModuleMethod(this, 1, null, 4097);
      moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/conditions.scm:166");
      this.lambda$Fn1 = moduleMethod;
    }
    
    public Object apply1(ModuleMethod param1ModuleMethod, Object param1Object) {
      return (param1ModuleMethod.selector == 1) ? (lambda2(param1Object) ? Boolean.TRUE : Boolean.FALSE) : super.apply1(param1ModuleMethod, param1Object);
    }
    
    boolean lambda2(Object param1Object) {
      param1Object = lists.car.apply1(param1Object);
      try {
        conditions.ntype ntype1 = (conditions.ntype)param1Object;
        return conditions.isConditionSubtype(ntype1, this.type);
      } catch (ClassCastException classCastException) {
        throw new WrongType(classCastException, "condition-subtype?", 0, param1Object);
      } 
    }
    
    public int match1(ModuleMethod param1ModuleMethod, Object param1Object, CallContext param1CallContext) {
      if (param1ModuleMethod.selector == 1) {
        param1CallContext.value1 = param1Object;
        param1CallContext.proc = (Procedure)param1ModuleMethod;
        param1CallContext.pc = 1;
        return 0;
      } 
      return super.match1(param1ModuleMethod, param1Object, param1CallContext);
    }
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/gnu/kawa/slib/conditions.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */