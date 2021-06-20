package kawa.lib;

import gnu.expr.ModuleBody;
import gnu.expr.ModuleInfo;
import gnu.expr.ModuleMethod;
import gnu.expr.Special;
import gnu.kawa.functions.AddOp;
import gnu.kawa.functions.ApplyToArgs;
import gnu.kawa.lispexpr.LangObjType;
import gnu.lists.Consumer;
import gnu.lists.FVector;
import gnu.lists.LList;
import gnu.lists.Pair;
import gnu.lists.Sequence;
import gnu.mapping.CallContext;
import gnu.mapping.Procedure;
import gnu.mapping.SimpleSymbol;
import gnu.mapping.Values;
import gnu.mapping.WrongType;
import gnu.math.IntNum;
import gnu.math.RealNum;
import kawa.standard.Scheme;
import kawa.standard.append;

public class srfi95 extends ModuleBody {
  public static final ModuleMethod $Pcsort$Mnlist;
  
  public static final ModuleMethod $Pcsort$Mnvector;
  
  public static final ModuleMethod $Pcvector$Mnsort$Ex;
  
  public static final srfi95 $instance;
  
  static final IntNum Lit0;
  
  static final IntNum Lit1;
  
  static final SimpleSymbol Lit10;
  
  static final SimpleSymbol Lit11;
  
  static final SimpleSymbol Lit12 = (SimpleSymbol)(new SimpleSymbol("sort")).readResolve();
  
  static final IntNum Lit2;
  
  static final IntNum Lit3;
  
  static final SimpleSymbol Lit4;
  
  static final SimpleSymbol Lit5;
  
  static final SimpleSymbol Lit6;
  
  static final SimpleSymbol Lit7;
  
  static final SimpleSymbol Lit8;
  
  static final SimpleSymbol Lit9;
  
  static final ModuleMethod identity;
  
  public static final ModuleMethod merge;
  
  public static final ModuleMethod merge$Ex;
  
  public static final ModuleMethod sort;
  
  public static final ModuleMethod sort$Ex;
  
  public static final ModuleMethod sorted$Qu;
  
  public static Object $PcSortList(Object paramObject1, Object paramObject2, Object paramObject3) {
    frame0 frame0 = new frame0();
    frame0.seq = paramObject1;
    frame0.less$Qu = paramObject2;
    frame0.keyer = Special.undefined;
    if (paramObject3 != Boolean.FALSE) {
      paramObject1 = lists.car;
    } else {
      paramObject1 = identity;
    } 
    frame0.keyer = paramObject1;
    if (paramObject3 != Boolean.FALSE) {
      paramObject1 = frame0.seq;
      while (true) {
        if (lists.isNull(paramObject1)) {
          paramObject1 = frame0.seq;
          try {
            paramObject2 = paramObject1;
            frame0.seq = frame0.lambda2step(Integer.valueOf(lists.length((LList)paramObject2)));
            paramObject1 = frame0.seq;
            while (true) {
              if (lists.isNull(paramObject1))
                return frame0.seq; 
              try {
                paramObject2 = paramObject1;
                lists.setCar$Ex((Pair)paramObject2, lists.cdar.apply1(paramObject1));
                paramObject1 = lists.cdr.apply1(paramObject1);
              } catch (ClassCastException classCastException) {
                throw new WrongType(classCastException, "set-car!", 1, paramObject1);
              } 
            } 
          } catch (ClassCastException classCastException) {
            throw new WrongType(classCastException, "length", 1, paramObject1);
          } 
          break;
        } 
        try {
          paramObject2 = paramObject1;
          lists.setCar$Ex((Pair)paramObject2, lists.cons(Scheme.applyToArgs.apply2(paramObject3, lists.car.apply1(paramObject1)), lists.car.apply1(paramObject1)));
          paramObject1 = lists.cdr.apply1(paramObject1);
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "set-car!", 1, paramObject1);
        } 
      } 
    } 
    paramObject1 = frame0.seq;
    try {
      paramObject2 = paramObject1;
      return frame0.lambda2step(Integer.valueOf(lists.length((LList)paramObject2)));
    } catch (ClassCastException classCastException) {
      throw new WrongType(classCastException, "length", 1, paramObject1);
    } 
  }
  
  public static void $PcSortVector(Sequence paramSequence, Object paramObject) {
    $PcSortVector(paramSequence, paramObject, Boolean.FALSE);
  }
  
  public static void $PcSortVector(Sequence paramSequence, Object paramObject1, Object paramObject2) {
    FVector fVector = vectors.makeVector(paramSequence.size());
    paramObject1 = $PcSortList(rank$Mn1Array$To$List(paramSequence), paramObject1, paramObject2);
    IntNum intNum = Lit3;
    while (!lists.isNull(paramObject1)) {
      Object object;
      try {
        int i = ((Number)intNum).intValue();
        vectors.vectorSet$Ex(fVector, i, lists.car.apply1(paramObject1));
        paramObject1 = lists.cdr.apply1(paramObject1);
        object = AddOp.$Pl.apply2(intNum, Lit2);
      } catch (ClassCastException classCastException) {
        throw new WrongType(classCastException, "vector-set!", 1, object);
      } 
    } 
  }
  
  public static Object $PcVectorSort$Ex(Sequence paramSequence, Object paramObject1, Object paramObject2) {
    paramObject2 = $PcSortList(rank$Mn1Array$To$List(paramSequence), paramObject1, paramObject2);
    for (paramObject1 = Lit3; !lists.isNull(paramObject2); paramObject1 = AddOp.$Pl.apply2(paramObject1, Lit2)) {
      paramSequence.set(((Number)paramObject1).intValue(), lists.car.apply1(paramObject2));
      paramObject2 = lists.cdr.apply1(paramObject2);
    } 
    return paramSequence;
  }
  
  static {
    Lit11 = (SimpleSymbol)(new SimpleSymbol("%sort-vector")).readResolve();
    Lit10 = (SimpleSymbol)(new SimpleSymbol("%vector-sort!")).readResolve();
    Lit9 = (SimpleSymbol)(new SimpleSymbol("sort!")).readResolve();
    Lit8 = (SimpleSymbol)(new SimpleSymbol("%sort-list")).readResolve();
    Lit7 = (SimpleSymbol)(new SimpleSymbol("merge!")).readResolve();
    Lit6 = (SimpleSymbol)(new SimpleSymbol("merge")).readResolve();
    Lit5 = (SimpleSymbol)(new SimpleSymbol("sorted?")).readResolve();
    Lit4 = (SimpleSymbol)(new SimpleSymbol("identity")).readResolve();
    Lit3 = IntNum.make(0);
    Lit2 = IntNum.make(1);
    Lit1 = IntNum.make(2);
    Lit0 = IntNum.make(-1);
    $instance = new srfi95();
    srfi95 srfi951 = $instance;
    identity = new ModuleMethod(srfi951, 1, Lit4, 4097);
    sorted$Qu = new ModuleMethod(srfi951, 2, Lit5, 12290);
    merge = new ModuleMethod(srfi951, 4, Lit6, 16387);
    merge$Ex = new ModuleMethod(srfi951, 6, Lit7, 16387);
    $Pcsort$Mnlist = new ModuleMethod(srfi951, 8, Lit8, 12291);
    sort$Ex = new ModuleMethod(srfi951, 9, Lit9, 12290);
    $Pcvector$Mnsort$Ex = new ModuleMethod(srfi951, 11, Lit10, 12291);
    $Pcsort$Mnvector = new ModuleMethod(srfi951, 12, Lit11, 12290);
    sort = new ModuleMethod(srfi951, 14, Lit12, 12290);
    $instance.run();
  }
  
  public srfi95() {
    ModuleInfo.register(this);
  }
  
  static Object identity(Object paramObject) {
    return paramObject;
  }
  
  public static Object isSorted(Object paramObject1, Object paramObject2) {
    return isSorted(paramObject1, paramObject2, identity);
  }
  
  public static Object isSorted(Object paramObject1, Object paramObject2, Object paramObject3) {
    if (lists.isNull(paramObject1))
      return Boolean.TRUE; 
    if (paramObject1 instanceof Sequence)
      try {
        int i;
        Sequence sequence = (Sequence)paramObject1;
        int j = sequence.size() - 1;
        if (j <= 1) {
          i = 1;
        } else {
          i = 0;
        } 
        if (i)
          return i ? Boolean.TRUE : Boolean.FALSE; 
        paramObject1 = Integer.valueOf(j - 1);
        Object object1 = Scheme.applyToArgs.apply2(paramObject3, sequence.get(j));
        try {
          while (true) {
            RealNum realNum = LangObjType.coerceRealNum(paramObject1);
            boolean bool = numbers.isNegative(realNum);
            if (bool)
              return bool ? Boolean.TRUE : Boolean.FALSE; 
            ApplyToArgs applyToArgs = Scheme.applyToArgs;
            try {
              i = ((Number)paramObject1).intValue();
              Object object2 = applyToArgs.apply2(paramObject3, sequence.get(i));
              object1 = Scheme.applyToArgs.apply3(paramObject2, object2, object1);
              if (object1 != Boolean.FALSE) {
                paramObject1 = AddOp.$Pl.apply2(Lit0, paramObject1);
                object1 = object2;
                continue;
              } 
              return object1;
            } catch (ClassCastException null) {
              throw new WrongType(classCastException, "gnu.lists.Sequence.get(int)", 2, paramObject1);
            } 
          } 
        } catch (ClassCastException null) {
          throw new WrongType(classCastException, "negative?", 1, paramObject1);
        } 
      } catch (ClassCastException classCastException) {
        throw new WrongType(classCastException, "arr", -2, paramObject1);
      }  
    if (lists.isNull(lists.cdr.apply1(paramObject1)))
      return Boolean.TRUE; 
    Object object = Scheme.applyToArgs.apply2(paramObject3, lists.car.apply1(paramObject1));
    paramObject1 = lists.cdr.apply1(paramObject1);
    while (true) {
      boolean bool = lists.isNull(paramObject1);
      if (bool)
        return bool ? Boolean.TRUE : Boolean.FALSE; 
      Object object1 = Scheme.applyToArgs.apply2(paramObject3, lists.car.apply1(paramObject1));
      object = Scheme.applyToArgs.apply3(classCastException, object1, object);
      try {
        Boolean bool1 = Boolean.FALSE;
        if (object != bool1) {
          i = 1;
        } else {
          i = 0;
        } 
        int i = i + 1 & 0x1;
        if (i != 0) {
          paramObject1 = lists.cdr.apply1(paramObject1);
          object = object1;
          continue;
        } 
        return (i != 0) ? Boolean.TRUE : Boolean.FALSE;
      } catch (ClassCastException classCastException1) {
        throw new WrongType(classCastException1, "x", -2, object);
      } 
    } 
  }
  
  public static Object merge(Object paramObject1, Object paramObject2, Object paramObject3) {
    return merge(paramObject1, paramObject2, paramObject3, identity);
  }
  
  public static Object merge(Object paramObject1, Object paramObject2, Object paramObject3, Object paramObject4) {
    frame frame = new frame();
    frame.less$Qu = paramObject3;
    frame.key = paramObject4;
    return lists.isNull(paramObject1) ? paramObject2 : (lists.isNull(paramObject2) ? paramObject1 : frame.lambda1loop(lists.car.apply1(paramObject1), Scheme.applyToArgs.apply2(frame.key, lists.car.apply1(paramObject1)), lists.cdr.apply1(paramObject1), lists.car.apply1(paramObject2), Scheme.applyToArgs.apply2(frame.key, lists.car.apply1(paramObject2)), lists.cdr.apply1(paramObject2)));
  }
  
  public static Object merge$Ex(Object paramObject1, Object paramObject2, Object paramObject3) {
    return merge$Ex(paramObject1, paramObject2, paramObject3, identity);
  }
  
  public static Object merge$Ex(Object paramObject1, Object paramObject2, Object paramObject3, Object paramObject4) {
    return sort$ClMerge$Ex(paramObject1, paramObject2, paramObject3, paramObject4);
  }
  
  static Object rank$Mn1Array$To$List(Sequence paramSequence) {
    int i = paramSequence.size() - 1;
    LList lList = LList.Empty;
    while (true) {
      if (i < 0)
        return lList; 
      Pair pair = lists.cons(paramSequence.get(i), lList);
      i--;
    } 
  }
  
  public static Object sort(Sequence paramSequence, Object paramObject) {
    return sort(paramSequence, paramObject, Boolean.FALSE);
  }
  
  public static Object sort(Sequence paramSequence, Object paramObject1, Object paramObject2) {
    if (lists.isList(paramSequence))
      return $PcSortList(append.append$V(new Object[] { paramSequence, LList.Empty }, ), paramObject1, paramObject2); 
    $PcSortVector(paramSequence, paramObject1, paramObject2);
    return Values.empty;
  }
  
  static Object sort$ClMerge$Ex(Object paramObject1, Object paramObject2, Object paramObject3, Object paramObject4) {
    frame1 frame1 = new frame1();
    frame1.less$Qu = paramObject3;
    frame1.key = paramObject4;
    if (lists.isNull(paramObject1))
      return paramObject2; 
    if (lists.isNull(paramObject2))
      return paramObject1; 
    paramObject3 = Scheme.applyToArgs.apply2(frame1.key, lists.car.apply1(paramObject1));
    paramObject4 = Scheme.applyToArgs.apply2(frame1.key, lists.car.apply1(paramObject2));
    if (Scheme.applyToArgs.apply3(frame1.less$Qu, paramObject4, paramObject3) != Boolean.FALSE) {
      if (lists.isNull(lists.cdr.apply1(paramObject2)))
        try {
          paramObject3 = paramObject2;
          lists.setCdr$Ex((Pair)paramObject3, paramObject1);
          return paramObject2;
        } catch (ClassCastException classCastException1) {
          throw new WrongType(classCastException1, "set-cdr!", 1, paramObject2);
        }  
      frame1.lambda3loop(paramObject2, classCastException1, paramObject3, lists.cdr.apply1(paramObject2), Scheme.applyToArgs.apply2(frame1.key, lists.cadr.apply1(paramObject2)));
      return paramObject2;
    } 
    if (lists.isNull(lists.cdr.apply1(classCastException1)))
      try {
        paramObject3 = classCastException1;
        lists.setCdr$Ex((Pair)paramObject3, paramObject2);
        return classCastException1;
      } catch (ClassCastException classCastException2) {
        throw new WrongType(classCastException2, "set-cdr!", 1, classCastException1);
      }  
    frame1.lambda3loop(classCastException1, lists.cdr.apply1(classCastException1), Scheme.applyToArgs.apply2(frame1.key, lists.cadr.apply1(classCastException1)), classCastException2, paramObject4);
    return classCastException1;
  }
  
  public static Object sort$Ex(Sequence paramSequence, Object paramObject) {
    return sort$Ex(paramSequence, paramObject, Boolean.FALSE);
  }
  
  public static Object sort$Ex(Sequence paramSequence, Object paramObject1, Object paramObject2) {
    if (lists.isList(paramSequence)) {
      paramObject2 = $PcSortList(paramSequence, paramObject1, paramObject2);
      if (paramObject2 != paramSequence) {
        for (paramObject1 = paramObject2; lists.cdr.apply1(paramObject1) != paramSequence; paramObject1 = lists.cdr.apply1(paramObject1));
        try {
          Pair pair = (Pair)paramObject1;
          lists.setCdr$Ex(pair, paramObject2);
          Object object = lists.car.apply1(paramSequence);
          paramObject1 = lists.cdr.apply1(paramSequence);
          try {
            Pair pair1 = (Pair)paramSequence;
            lists.setCar$Ex(pair1, lists.car.apply1(paramObject2));
            try {
              pair1 = (Pair)paramSequence;
              lists.setCdr$Ex(pair1, lists.cdr.apply1(paramObject2));
              try {
                pair1 = (Pair)paramObject2;
                lists.setCar$Ex(pair1, object);
                try {
                  object = paramObject2;
                  lists.setCdr$Ex((Pair)object, paramObject1);
                  return paramSequence;
                } catch (ClassCastException null) {
                  throw new WrongType(classCastException1, "set-cdr!", 1, paramObject2);
                } 
              } catch (ClassCastException null) {
                throw new WrongType(classCastException1, "set-car!", 1, paramObject2);
              } 
            } catch (ClassCastException null) {
              throw new WrongType(classCastException2, "set-cdr!", 1, classCastException1);
            } 
          } catch (ClassCastException classCastException2) {
            throw new WrongType(classCastException2, "set-car!", 1, classCastException1);
          } 
        } catch (ClassCastException classCastException1) {
          throw new WrongType(classCastException1, "set-cdr!", 1, classCastException2);
        } 
      } 
      return classCastException1;
    } 
    return $PcVectorSort$Ex((Sequence)classCastException1, classCastException2, paramObject2);
  }
  
  public Object apply1(ModuleMethod paramModuleMethod, Object paramObject) {
    return (paramModuleMethod.selector == 1) ? identity(paramObject) : super.apply1(paramModuleMethod, paramObject);
  }
  
  public Object apply2(ModuleMethod paramModuleMethod, Object paramObject1, Object paramObject2) {
    switch (paramModuleMethod.selector) {
      default:
        return super.apply2(paramModuleMethod, paramObject1, paramObject2);
      case 2:
        return isSorted(paramObject1, paramObject2);
      case 9:
        try {
          Sequence sequence = (Sequence)paramObject1;
          return sort$Ex(sequence, paramObject2);
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "sort!", 1, paramObject1);
        } 
      case 12:
        try {
          Sequence sequence = (Sequence)paramObject1;
          $PcSortVector(sequence, paramObject2);
          return Values.empty;
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "%sort-vector", 1, paramObject1);
        } 
      case 14:
        break;
    } 
    try {
      Sequence sequence = (Sequence)paramObject1;
      return sort(sequence, paramObject2);
    } catch (ClassCastException classCastException) {
      throw new WrongType(classCastException, "sort", 1, paramObject1);
    } 
  }
  
  public Object apply3(ModuleMethod paramModuleMethod, Object paramObject1, Object paramObject2, Object paramObject3) {
    switch (paramModuleMethod.selector) {
      default:
        return super.apply3(paramModuleMethod, paramObject1, paramObject2, paramObject3);
      case 2:
        return isSorted(paramObject1, paramObject2, paramObject3);
      case 4:
        return merge(paramObject1, paramObject2, paramObject3);
      case 6:
        return merge$Ex(paramObject1, paramObject2, paramObject3);
      case 8:
        return $PcSortList(paramObject1, paramObject2, paramObject3);
      case 9:
        try {
          Sequence sequence = (Sequence)paramObject1;
          return sort$Ex(sequence, paramObject2, paramObject3);
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "sort!", 1, paramObject1);
        } 
      case 11:
        try {
          Sequence sequence = (Sequence)paramObject1;
          return $PcVectorSort$Ex(sequence, paramObject2, paramObject3);
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "%vector-sort!", 1, paramObject1);
        } 
      case 12:
        try {
          Sequence sequence = (Sequence)paramObject1;
          $PcSortVector(sequence, paramObject2, paramObject3);
          return Values.empty;
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "%sort-vector", 1, paramObject1);
        } 
      case 14:
        break;
    } 
    try {
      Sequence sequence = (Sequence)paramObject1;
      return sort(sequence, paramObject2, paramObject3);
    } catch (ClassCastException classCastException) {
      throw new WrongType(classCastException, "sort", 1, paramObject1);
    } 
  }
  
  public Object apply4(ModuleMethod paramModuleMethod, Object paramObject1, Object paramObject2, Object paramObject3, Object paramObject4) {
    switch (paramModuleMethod.selector) {
      default:
        return super.apply4(paramModuleMethod, paramObject1, paramObject2, paramObject3, paramObject4);
      case 4:
        return merge(paramObject1, paramObject2, paramObject3, paramObject4);
      case 6:
        break;
    } 
    return merge$Ex(paramObject1, paramObject2, paramObject3, paramObject4);
  }
  
  public int match1(ModuleMethod paramModuleMethod, Object paramObject, CallContext paramCallContext) {
    if (paramModuleMethod.selector == 1) {
      paramCallContext.value1 = paramObject;
      paramCallContext.proc = (Procedure)paramModuleMethod;
      paramCallContext.pc = 1;
      return 0;
    } 
    return super.match1(paramModuleMethod, paramObject, paramCallContext);
  }
  
  public int match2(ModuleMethod paramModuleMethod, Object paramObject1, Object paramObject2, CallContext paramCallContext) {
    null = -786431;
    switch (paramModuleMethod.selector) {
      default:
        return super.match2(paramModuleMethod, paramObject1, paramObject2, paramCallContext);
      case 14:
        if (paramObject1 instanceof Sequence) {
          paramCallContext.value1 = paramObject1;
          paramCallContext.value2 = paramObject2;
          paramCallContext.proc = (Procedure)paramModuleMethod;
          paramCallContext.pc = 2;
          return 0;
        } 
        return SYNTHETIC_LOCAL_VARIABLE_5;
      case 12:
        if (paramObject1 instanceof Sequence) {
          paramCallContext.value1 = paramObject1;
          paramCallContext.value2 = paramObject2;
          paramCallContext.proc = (Procedure)paramModuleMethod;
          paramCallContext.pc = 2;
          return 0;
        } 
        return SYNTHETIC_LOCAL_VARIABLE_5;
      case 9:
        if (paramObject1 instanceof Sequence) {
          paramCallContext.value1 = paramObject1;
          paramCallContext.value2 = paramObject2;
          paramCallContext.proc = (Procedure)paramModuleMethod;
          paramCallContext.pc = 2;
          return 0;
        } 
        return SYNTHETIC_LOCAL_VARIABLE_5;
      case 2:
        break;
    } 
    paramCallContext.value1 = paramObject1;
    paramCallContext.value2 = paramObject2;
    paramCallContext.proc = (Procedure)paramModuleMethod;
    paramCallContext.pc = 2;
    return 0;
  }
  
  public int match3(ModuleMethod paramModuleMethod, Object paramObject1, Object paramObject2, Object paramObject3, CallContext paramCallContext) {
    null = -786431;
    switch (paramModuleMethod.selector) {
      default:
        return super.match3(paramModuleMethod, paramObject1, paramObject2, paramObject3, paramCallContext);
      case 14:
        if (paramObject1 instanceof Sequence) {
          paramCallContext.value1 = paramObject1;
          paramCallContext.value2 = paramObject2;
          paramCallContext.value3 = paramObject3;
          paramCallContext.proc = (Procedure)paramModuleMethod;
          paramCallContext.pc = 3;
          return 0;
        } 
        return SYNTHETIC_LOCAL_VARIABLE_6;
      case 12:
        if (paramObject1 instanceof Sequence) {
          paramCallContext.value1 = paramObject1;
          paramCallContext.value2 = paramObject2;
          paramCallContext.value3 = paramObject3;
          paramCallContext.proc = (Procedure)paramModuleMethod;
          paramCallContext.pc = 3;
          return 0;
        } 
        return SYNTHETIC_LOCAL_VARIABLE_6;
      case 11:
        if (paramObject1 instanceof Sequence) {
          paramCallContext.value1 = paramObject1;
          paramCallContext.value2 = paramObject2;
          paramCallContext.value3 = paramObject3;
          paramCallContext.proc = (Procedure)paramModuleMethod;
          paramCallContext.pc = 3;
          return 0;
        } 
        return SYNTHETIC_LOCAL_VARIABLE_6;
      case 9:
        if (paramObject1 instanceof Sequence) {
          paramCallContext.value1 = paramObject1;
          paramCallContext.value2 = paramObject2;
          paramCallContext.value3 = paramObject3;
          paramCallContext.proc = (Procedure)paramModuleMethod;
          paramCallContext.pc = 3;
          return 0;
        } 
        return SYNTHETIC_LOCAL_VARIABLE_6;
      case 8:
        paramCallContext.value1 = paramObject1;
        paramCallContext.value2 = paramObject2;
        paramCallContext.value3 = paramObject3;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 3;
        return 0;
      case 6:
        paramCallContext.value1 = paramObject1;
        paramCallContext.value2 = paramObject2;
        paramCallContext.value3 = paramObject3;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 3;
        return 0;
      case 4:
        paramCallContext.value1 = paramObject1;
        paramCallContext.value2 = paramObject2;
        paramCallContext.value3 = paramObject3;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 3;
        return 0;
      case 2:
        break;
    } 
    paramCallContext.value1 = paramObject1;
    paramCallContext.value2 = paramObject2;
    paramCallContext.value3 = paramObject3;
    paramCallContext.proc = (Procedure)paramModuleMethod;
    paramCallContext.pc = 3;
    return 0;
  }
  
  public int match4(ModuleMethod paramModuleMethod, Object paramObject1, Object paramObject2, Object paramObject3, Object paramObject4, CallContext paramCallContext) {
    switch (paramModuleMethod.selector) {
      default:
        return super.match4(paramModuleMethod, paramObject1, paramObject2, paramObject3, paramObject4, paramCallContext);
      case 6:
        paramCallContext.value1 = paramObject1;
        paramCallContext.value2 = paramObject2;
        paramCallContext.value3 = paramObject3;
        paramCallContext.value4 = paramObject4;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 4;
        return 0;
      case 4:
        break;
    } 
    paramCallContext.value1 = paramObject1;
    paramCallContext.value2 = paramObject2;
    paramCallContext.value3 = paramObject3;
    paramCallContext.value4 = paramObject4;
    paramCallContext.proc = (Procedure)paramModuleMethod;
    paramCallContext.pc = 4;
    return 0;
  }
  
  public final void run(CallContext paramCallContext) {
    Consumer consumer = paramCallContext.consumer;
  }
  
  public class frame extends ModuleBody {
    Object key;
    
    Object less$Qu;
    
    public Object lambda1loop(Object param1Object1, Object param1Object2, Object param1Object3, Object param1Object4, Object param1Object5, Object param1Object6) {
      return (Scheme.applyToArgs.apply3(this.less$Qu, param1Object5, param1Object2) != Boolean.FALSE) ? (lists.isNull(param1Object6) ? lists.cons(param1Object4, lists.cons(param1Object1, param1Object3)) : lists.cons(param1Object4, lambda1loop(param1Object1, param1Object2, param1Object3, lists.car.apply1(param1Object6), Scheme.applyToArgs.apply2(this.key, lists.car.apply1(param1Object6)), lists.cdr.apply1(param1Object6)))) : (lists.isNull(param1Object3) ? lists.cons(param1Object1, lists.cons(param1Object4, param1Object6)) : lists.cons(param1Object1, lambda1loop(lists.car.apply1(param1Object3), Scheme.applyToArgs.apply2(this.key, lists.car.apply1(param1Object3)), lists.cdr.apply1(param1Object3), param1Object4, param1Object5, param1Object6)));
    }
  }
  
  public class frame0 extends ModuleBody {
    Object keyer;
    
    Object less$Qu;
    
    Object seq;
    
    public Object lambda2step(Object param1Object) {
      // Byte code:
      //   0: getstatic kawa/standard/Scheme.numGrt : Lgnu/kawa/functions/NumberCompare;
      //   3: aload_1
      //   4: getstatic kawa/lib/srfi95.Lit1 : Lgnu/math/IntNum;
      //   7: invokevirtual apply2 : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
      //   10: getstatic java/lang/Boolean.FALSE : Ljava/lang/Boolean;
      //   13: if_acmpeq -> 56
      //   16: getstatic gnu/kawa/functions/DivideOp.quotient : Lgnu/kawa/functions/DivideOp;
      //   19: aload_1
      //   20: getstatic kawa/lib/srfi95.Lit1 : Lgnu/math/IntNum;
      //   23: invokevirtual apply2 : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
      //   26: astore_2
      //   27: aload_0
      //   28: aload_2
      //   29: invokevirtual lambda2step : (Ljava/lang/Object;)Ljava/lang/Object;
      //   32: aload_0
      //   33: getstatic gnu/kawa/functions/AddOp.$Mn : Lgnu/kawa/functions/AddOp;
      //   36: aload_1
      //   37: aload_2
      //   38: invokevirtual apply2 : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
      //   41: invokevirtual lambda2step : (Ljava/lang/Object;)Ljava/lang/Object;
      //   44: aload_0
      //   45: getfield less$Qu : Ljava/lang/Object;
      //   48: aload_0
      //   49: getfield keyer : Ljava/lang/Object;
      //   52: invokestatic sort$ClMerge$Ex : (Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
      //   55: areturn
      //   56: getstatic kawa/standard/Scheme.numEqu : Lgnu/kawa/functions/NumberCompare;
      //   59: aload_1
      //   60: getstatic kawa/lib/srfi95.Lit1 : Lgnu/math/IntNum;
      //   63: invokevirtual apply2 : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
      //   66: getstatic java/lang/Boolean.FALSE : Ljava/lang/Boolean;
      //   69: if_acmpeq -> 205
      //   72: getstatic kawa/lib/lists.car : Lgnu/expr/GenericProc;
      //   75: aload_0
      //   76: getfield seq : Ljava/lang/Object;
      //   79: invokevirtual apply1 : (Ljava/lang/Object;)Ljava/lang/Object;
      //   82: astore_2
      //   83: getstatic kawa/lib/lists.cadr : Lgnu/expr/GenericProc;
      //   86: aload_0
      //   87: getfield seq : Ljava/lang/Object;
      //   90: invokevirtual apply1 : (Ljava/lang/Object;)Ljava/lang/Object;
      //   93: astore_3
      //   94: aload_0
      //   95: getfield seq : Ljava/lang/Object;
      //   98: astore_1
      //   99: aload_0
      //   100: getstatic kawa/lib/lists.cddr : Lgnu/expr/GenericProc;
      //   103: aload_0
      //   104: getfield seq : Ljava/lang/Object;
      //   107: invokevirtual apply1 : (Ljava/lang/Object;)Ljava/lang/Object;
      //   110: putfield seq : Ljava/lang/Object;
      //   113: getstatic kawa/standard/Scheme.applyToArgs : Lgnu/kawa/functions/ApplyToArgs;
      //   116: aload_0
      //   117: getfield less$Qu : Ljava/lang/Object;
      //   120: getstatic kawa/standard/Scheme.applyToArgs : Lgnu/kawa/functions/ApplyToArgs;
      //   123: aload_0
      //   124: getfield keyer : Ljava/lang/Object;
      //   127: aload_3
      //   128: invokevirtual apply2 : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
      //   131: getstatic kawa/standard/Scheme.applyToArgs : Lgnu/kawa/functions/ApplyToArgs;
      //   134: aload_0
      //   135: getfield keyer : Ljava/lang/Object;
      //   138: aload_2
      //   139: invokevirtual apply2 : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
      //   142: invokevirtual apply3 : (Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
      //   145: getstatic java/lang/Boolean.FALSE : Ljava/lang/Boolean;
      //   148: if_acmpeq -> 183
      //   151: aload_1
      //   152: checkcast gnu/lists/Pair
      //   155: astore #4
      //   157: aload #4
      //   159: aload_3
      //   160: invokestatic setCar$Ex : (Lgnu/lists/Pair;Ljava/lang/Object;)V
      //   163: getstatic kawa/lib/lists.cdr : Lgnu/expr/GenericProc;
      //   166: aload_1
      //   167: invokevirtual apply1 : (Ljava/lang/Object;)Ljava/lang/Object;
      //   170: astore_3
      //   171: aload_3
      //   172: checkcast gnu/lists/Pair
      //   175: astore #4
      //   177: aload #4
      //   179: aload_2
      //   180: invokestatic setCar$Ex : (Lgnu/lists/Pair;Ljava/lang/Object;)V
      //   183: getstatic kawa/lib/lists.cdr : Lgnu/expr/GenericProc;
      //   186: aload_1
      //   187: invokevirtual apply1 : (Ljava/lang/Object;)Ljava/lang/Object;
      //   190: astore_2
      //   191: aload_2
      //   192: checkcast gnu/lists/Pair
      //   195: astore_3
      //   196: aload_3
      //   197: getstatic gnu/lists/LList.Empty : Lgnu/lists/LList;
      //   200: invokestatic setCdr$Ex : (Lgnu/lists/Pair;Ljava/lang/Object;)V
      //   203: aload_1
      //   204: areturn
      //   205: getstatic kawa/standard/Scheme.numEqu : Lgnu/kawa/functions/NumberCompare;
      //   208: aload_1
      //   209: getstatic kawa/lib/srfi95.Lit2 : Lgnu/math/IntNum;
      //   212: invokevirtual apply2 : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
      //   215: getstatic java/lang/Boolean.FALSE : Ljava/lang/Boolean;
      //   218: if_acmpeq -> 254
      //   221: aload_0
      //   222: getfield seq : Ljava/lang/Object;
      //   225: astore_1
      //   226: aload_0
      //   227: getstatic kawa/lib/lists.cdr : Lgnu/expr/GenericProc;
      //   230: aload_0
      //   231: getfield seq : Ljava/lang/Object;
      //   234: invokevirtual apply1 : (Ljava/lang/Object;)Ljava/lang/Object;
      //   237: putfield seq : Ljava/lang/Object;
      //   240: aload_1
      //   241: checkcast gnu/lists/Pair
      //   244: astore_2
      //   245: aload_2
      //   246: getstatic gnu/lists/LList.Empty : Lgnu/lists/LList;
      //   249: invokestatic setCdr$Ex : (Lgnu/lists/Pair;Ljava/lang/Object;)V
      //   252: aload_1
      //   253: areturn
      //   254: getstatic gnu/lists/LList.Empty : Lgnu/lists/LList;
      //   257: areturn
      //   258: astore_2
      //   259: new gnu/mapping/WrongType
      //   262: dup
      //   263: aload_2
      //   264: ldc 'set-car!'
      //   266: iconst_1
      //   267: aload_1
      //   268: invokespecial <init> : (Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
      //   271: athrow
      //   272: astore_1
      //   273: new gnu/mapping/WrongType
      //   276: dup
      //   277: aload_1
      //   278: ldc 'set-car!'
      //   280: iconst_1
      //   281: aload_3
      //   282: invokespecial <init> : (Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
      //   285: athrow
      //   286: astore_1
      //   287: new gnu/mapping/WrongType
      //   290: dup
      //   291: aload_1
      //   292: ldc 'set-cdr!'
      //   294: iconst_1
      //   295: aload_2
      //   296: invokespecial <init> : (Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
      //   299: athrow
      //   300: astore_2
      //   301: new gnu/mapping/WrongType
      //   304: dup
      //   305: aload_2
      //   306: ldc 'set-cdr!'
      //   308: iconst_1
      //   309: aload_1
      //   310: invokespecial <init> : (Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
      //   313: athrow
      // Exception table:
      //   from	to	target	type
      //   151	157	258	java/lang/ClassCastException
      //   171	177	272	java/lang/ClassCastException
      //   191	196	286	java/lang/ClassCastException
      //   240	245	300	java/lang/ClassCastException
    }
  }
  
  public class frame1 extends ModuleBody {
    Object key;
    
    Object less$Qu;
    
    public Object lambda3loop(Object param1Object1, Object param1Object2, Object param1Object3, Object param1Object4, Object param1Object5) {
      if (Scheme.applyToArgs.apply3(this.less$Qu, param1Object5, param1Object3) != Boolean.FALSE)
        try {
          param1Object5 = param1Object1;
          lists.setCdr$Ex((Pair)param1Object5, param1Object4);
          if (lists.isNull(lists.cdr.apply1(param1Object4)))
            try {
              param1Object1 = param1Object4;
              lists.setCdr$Ex((Pair)param1Object1, param1Object2);
              return Values.empty;
            } catch (ClassCastException null) {
              throw new WrongType(classCastException1, "set-cdr!", 1, param1Object4);
            }  
          return lambda3loop(param1Object4, param1Object2, param1Object3, lists.cdr.apply1(param1Object4), Scheme.applyToArgs.apply2(this.key, lists.cadr.apply1(param1Object4)));
        } catch (ClassCastException classCastException2) {
          throw new WrongType(classCastException2, "set-cdr!", 1, classCastException1);
        }  
      try {
        param1Object3 = classCastException1;
        lists.setCdr$Ex((Pair)param1Object3, classCastException2);
        if (lists.isNull(lists.cdr.apply1(classCastException2)))
          try {
            Pair pair = (Pair)classCastException2;
            lists.setCdr$Ex(pair, param1Object4);
            return Values.empty;
          } catch (ClassCastException classCastException1) {
            throw new WrongType(classCastException1, "set-cdr!", 1, classCastException2);
          }  
        return lambda3loop(classCastException2, lists.cdr.apply1(classCastException2), Scheme.applyToArgs.apply2(this.key, lists.cadr.apply1(classCastException2)), param1Object4, param1Object5);
      } catch (ClassCastException classCastException) {
        throw new WrongType(classCastException, "set-cdr!", 1, classCastException1);
      } 
    }
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/kawa/lib/srfi95.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */