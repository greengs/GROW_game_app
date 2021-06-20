package kawa.lib;

import gnu.expr.ModuleBody;
import gnu.expr.ModuleInfo;
import gnu.expr.ModuleMethod;
import gnu.lists.Consumer;
import gnu.lists.LList;
import gnu.lists.Pair;
import gnu.lists.PairWithPosition;
import gnu.mapping.CallContext;
import gnu.mapping.Location;
import gnu.mapping.LocationProc;
import gnu.mapping.Procedure;
import gnu.mapping.SimpleSymbol;
import gnu.mapping.ThreadLocation;
import gnu.mapping.WrongType;
import kawa.lang.Macro;
import kawa.lang.SyntaxPattern;
import kawa.lang.SyntaxRule;
import kawa.lang.SyntaxRules;
import kawa.standard.Scheme;

public class parameters extends ModuleBody {
  public static final ModuleMethod $Prvt$as$Mnlocation$Pc;
  
  public static final Macro $Prvt$parameterize$Pc;
  
  public static final parameters $instance;
  
  static final SimpleSymbol Lit0;
  
  static final SimpleSymbol Lit1;
  
  static final SimpleSymbol Lit10;
  
  static final SimpleSymbol Lit11;
  
  static final SimpleSymbol Lit12 = (SimpleSymbol)(new SimpleSymbol("save")).readResolve();
  
  static final SimpleSymbol Lit2;
  
  static final SyntaxRules Lit3;
  
  static final SimpleSymbol Lit4;
  
  static final SyntaxRules Lit5;
  
  static final SimpleSymbol Lit6;
  
  static final SimpleSymbol Lit7;
  
  static final SimpleSymbol Lit8;
  
  static final SimpleSymbol Lit9;
  
  public static final ModuleMethod make$Mnparameter;
  
  public static final Macro parameterize;
  
  static {
    Lit11 = (SimpleSymbol)(new SimpleSymbol("quasiquote")).readResolve();
    Lit10 = (SimpleSymbol)(new SimpleSymbol("gnu.mapping.Location")).readResolve();
    Lit9 = (SimpleSymbol)(new SimpleSymbol("$lookup$")).readResolve();
    Lit8 = (SimpleSymbol)(new SimpleSymbol("v")).readResolve();
    Lit7 = (SimpleSymbol)(new SimpleSymbol("p")).readResolve();
    Lit6 = (SimpleSymbol)(new SimpleSymbol("begin")).readResolve();
    SimpleSymbol simpleSymbol1 = (SimpleSymbol)(new SimpleSymbol("parameterize")).readResolve();
    Lit4 = simpleSymbol1;
    SyntaxRule syntaxRule1 = new SyntaxRule(new SyntaxPattern("\f\030\f\b\003", new Object[0], 1), "\000", "\021\030\004\002", new Object[] { Lit6 }, 0);
    SyntaxPattern syntaxPattern2 = new SyntaxPattern("\f\030<,\f\007\f\017\b\023\033", new Object[0], 4);
    SimpleSymbol simpleSymbol2 = (SimpleSymbol)(new SimpleSymbol("parameterize%")).readResolve();
    Lit2 = simpleSymbol2;
    SyntaxRule syntaxRule3 = new SyntaxRule(syntaxPattern2, "\001\001\000\000", "\021\030\0041!\t\003\b\013\022\t\020\032", new Object[] { simpleSymbol2 }, 0);
    Lit5 = new SyntaxRules(new Object[] { simpleSymbol1 }, new SyntaxRule[] { syntaxRule1, syntaxRule3 }, 4);
    simpleSymbol1 = Lit2;
    syntaxRule1 = new SyntaxRule(new SyntaxPattern("\f\030\f\b\f\007\013", new Object[0], 2), "\001\000", "\021\030\004!\021\030\f\n\b\021\030\f\003", new Object[] { (new SimpleSymbol("try-finally")).readResolve(), Lit6 }, 0);
    SyntaxPattern syntaxPattern1 = new SyntaxPattern("\f\030<,\f\007\f\017\b\023\f\037#", new Object[0], 5);
    simpleSymbol2 = (SimpleSymbol)(new SimpleSymbol("let*")).readResolve();
    SimpleSymbol simpleSymbol3 = Lit7;
    SimpleSymbol simpleSymbol4 = (SimpleSymbol)(new SimpleSymbol("::")).readResolve();
    SimpleSymbol simpleSymbol5 = (SimpleSymbol)(new SimpleSymbol("<gnu.mapping.Location>")).readResolve();
    SimpleSymbol simpleSymbol6 = (SimpleSymbol)(new SimpleSymbol("as-location%")).readResolve();
    Lit1 = simpleSymbol6;
    SyntaxRule syntaxRule2 = new SyntaxRule(syntaxPattern1, "\001\001\000\001\000", "\021\030\004Áy\021\030\f\021\030\024\021\030\034\b\021\030$\b\003)\021\030,\b\013\0304\b\021\030<\t\022!\021\030D\033\"", new Object[] { simpleSymbol2, simpleSymbol3, simpleSymbol4, simpleSymbol5, simpleSymbol6, Lit8, PairWithPosition.make(PairWithPosition.make(Lit12, PairWithPosition.make(PairWithPosition.make(PairWithPosition.make(Lit9, Pair.make(Lit10, Pair.make(Pair.make(Lit11, Pair.make((new SimpleSymbol("setWithSave")).readResolve(), LList.Empty)), LList.Empty)), "/u2/home/jis/ai2-kawa/kawa/lib/parameters.scm", 122893), PairWithPosition.make(Lit7, PairWithPosition.make(Lit8, LList.Empty, "/u2/home/jis/ai2-kawa/kawa/lib/parameters.scm", 122928), "/u2/home/jis/ai2-kawa/kawa/lib/parameters.scm", 122926), "/u2/home/jis/ai2-kawa/kawa/lib/parameters.scm", 122892), LList.Empty, "/u2/home/jis/ai2-kawa/kawa/lib/parameters.scm", 122892), "/u2/home/jis/ai2-kawa/kawa/lib/parameters.scm", 122886), LList.Empty, "/u2/home/jis/ai2-kawa/kawa/lib/parameters.scm", 122886), Lit2, PairWithPosition.make(PairWithPosition.make(Lit9, Pair.make(Lit10, Pair.make(Pair.make(Lit11, Pair.make((new SimpleSymbol("setRestore")).readResolve(), LList.Empty)), LList.Empty)), "/u2/home/jis/ai2-kawa/kawa/lib/parameters.scm", 131083), PairWithPosition.make(Lit7, PairWithPosition.make(Lit12, LList.Empty, "/u2/home/jis/ai2-kawa/kawa/lib/parameters.scm", 131117), "/u2/home/jis/ai2-kawa/kawa/lib/parameters.scm", 131115), "/u2/home/jis/ai2-kawa/kawa/lib/parameters.scm", 131082) }0);
    Lit3 = new SyntaxRules(new Object[] { simpleSymbol1 }, new SyntaxRule[] { syntaxRule1, syntaxRule2 }, 5);
    Lit0 = (SimpleSymbol)(new SimpleSymbol("make-parameter")).readResolve();
    $instance = new parameters();
    parameters parameters1 = $instance;
    make$Mnparameter = new ModuleMethod(parameters1, 1, Lit0, 8193);
    $Prvt$as$Mnlocation$Pc = new ModuleMethod(parameters1, 3, Lit1, 4097);
    $Prvt$parameterize$Pc = Macro.make(Lit2, (Procedure)Lit3, $instance);
    parameterize = Macro.make(Lit4, (Procedure)Lit5, $instance);
    $instance.run();
  }
  
  public parameters() {
    ModuleInfo.register(this);
  }
  
  public static Location asLocation$Pc(Object paramObject) {
    // Byte code:
    //   0: aload_0
    //   1: instanceof gnu/mapping/LocationProc
    //   4: ifeq -> 41
    //   7: aload_0
    //   8: checkcast gnu/mapping/LocationProc
    //   11: astore_1
    //   12: aload_1
    //   13: invokevirtual getLocation : ()Lgnu/mapping/Location;
    //   16: astore_0
    //   17: aload_0
    //   18: astore_1
    //   19: aload_0
    //   20: instanceof gnu/mapping/ThreadLocation
    //   23: ifeq -> 36
    //   26: aload_0
    //   27: checkcast gnu/mapping/ThreadLocation
    //   30: astore_1
    //   31: aload_1
    //   32: invokevirtual getLocation : ()Lgnu/mapping/NamedLocation;
    //   35: astore_1
    //   36: aload_1
    //   37: checkcast gnu/mapping/Location
    //   40: areturn
    //   41: aload_0
    //   42: checkcast gnu/mapping/Location
    //   45: astore_0
    //   46: goto -> 17
    //   49: astore_1
    //   50: new gnu/mapping/WrongType
    //   53: dup
    //   54: aload_1
    //   55: ldc 'gnu.mapping.LocationProc.getLocation()'
    //   57: iconst_1
    //   58: aload_0
    //   59: invokespecial <init> : (Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   62: athrow
    //   63: astore_1
    //   64: new gnu/mapping/WrongType
    //   67: dup
    //   68: aload_1
    //   69: ldc 'gnu.mapping.ThreadLocation.getLocation()'
    //   71: iconst_1
    //   72: aload_0
    //   73: invokespecial <init> : (Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   76: athrow
    // Exception table:
    //   from	to	target	type
    //   7	12	49	java/lang/ClassCastException
    //   26	31	63	java/lang/ClassCastException
  }
  
  public static LocationProc makeParameter(Object paramObject) {
    return makeParameter(paramObject, null);
  }
  
  public static LocationProc makeParameter(Object paramObject1, Object paramObject2) {
    Object object = paramObject1;
    if (paramObject2 != null)
      object = Scheme.applyToArgs.apply2(paramObject2, paramObject1); 
    paramObject1 = new ThreadLocation();
    paramObject1.setGlobal(object);
    try {
      object = paramObject2;
      return new LocationProc((Location)paramObject1, (Procedure)object);
    } catch (ClassCastException classCastException) {
      throw new WrongType(classCastException, "gnu.mapping.LocationProc.<init>(gnu.mapping.Location,gnu.mapping.Procedure)", 2, paramObject2);
    } 
  }
  
  public Object apply1(ModuleMethod paramModuleMethod, Object paramObject) {
    switch (paramModuleMethod.selector) {
      default:
        return super.apply1(paramModuleMethod, paramObject);
      case 1:
        return makeParameter(paramObject);
      case 3:
        break;
    } 
    return asLocation$Pc(paramObject);
  }
  
  public Object apply2(ModuleMethod paramModuleMethod, Object paramObject1, Object paramObject2) {
    return (paramModuleMethod.selector == 1) ? makeParameter(paramObject1, paramObject2) : super.apply2(paramModuleMethod, paramObject1, paramObject2);
  }
  
  public int match1(ModuleMethod paramModuleMethod, Object paramObject, CallContext paramCallContext) {
    switch (paramModuleMethod.selector) {
      default:
        return super.match1(paramModuleMethod, paramObject, paramCallContext);
      case 3:
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
  
  public int match2(ModuleMethod paramModuleMethod, Object paramObject1, Object paramObject2, CallContext paramCallContext) {
    if (paramModuleMethod.selector == 1) {
      paramCallContext.value1 = paramObject1;
      paramCallContext.value2 = paramObject2;
      paramCallContext.proc = (Procedure)paramModuleMethod;
      paramCallContext.pc = 2;
      return 0;
    } 
    return super.match2(paramModuleMethod, paramObject1, paramObject2, paramCallContext);
  }
  
  public final void run(CallContext paramCallContext) {
    Consumer consumer = paramCallContext.consumer;
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/kawa/lib/parameters.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */