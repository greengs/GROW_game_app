package kawa.lib;

import gnu.bytecode.ClassType;
import gnu.bytecode.Type;
import gnu.expr.Compilation;
import gnu.expr.ModuleBody;
import gnu.expr.ModuleInfo;
import gnu.expr.ModuleMethod;
import gnu.kawa.lispexpr.LangObjType;
import gnu.lists.Consumer;
import gnu.lists.LList;
import gnu.lists.PairWithPosition;
import gnu.mapping.CallContext;
import gnu.mapping.Procedure;
import gnu.mapping.SimpleSymbol;
import gnu.mapping.WrongType;
import gnu.math.IntNum;
import kawa.lang.GetFieldProc;
import kawa.lang.Macro;
import kawa.lang.Record;
import kawa.lang.RecordConstructor;
import kawa.lang.SetFieldProc;
import kawa.lang.SyntaxPattern;
import kawa.lang.SyntaxRule;
import kawa.lang.SyntaxRules;
import kawa.lang.SyntaxTemplate;
import kawa.lang.TemplateScope;
import kawa.standard.syntax_case;

public class reflection extends ModuleBody {
  public static final reflection $instance;
  
  static final SimpleSymbol Lit0;
  
  static final SyntaxPattern Lit1;
  
  static final SimpleSymbol Lit10;
  
  static final SimpleSymbol Lit11;
  
  static final SimpleSymbol Lit12;
  
  static final SimpleSymbol Lit13;
  
  static final SimpleSymbol Lit14;
  
  static final SyntaxRules Lit15;
  
  static final SimpleSymbol Lit16;
  
  static final SyntaxRules Lit17;
  
  static final SimpleSymbol Lit18;
  
  static final SyntaxRules Lit19;
  
  static final SyntaxTemplate Lit2;
  
  static final SimpleSymbol Lit20;
  
  static final SyntaxRules Lit21;
  
  static final SimpleSymbol Lit22;
  
  static final SyntaxRules Lit23;
  
  static final SimpleSymbol Lit24;
  
  static final SyntaxRules Lit25;
  
  static final SimpleSymbol Lit26;
  
  static final SyntaxRules Lit27;
  
  static final SimpleSymbol Lit28;
  
  static final SyntaxRules Lit29;
  
  static final SyntaxPattern Lit3;
  
  static final SimpleSymbol Lit30;
  
  static final SimpleSymbol Lit31;
  
  static final SimpleSymbol Lit32;
  
  static final IntNum Lit33;
  
  static final IntNum Lit34 = IntNum.make(1);
  
  static final SyntaxTemplate Lit4;
  
  static final SimpleSymbol Lit5;
  
  static final SimpleSymbol Lit6;
  
  static final SimpleSymbol Lit7;
  
  static final SimpleSymbol Lit8;
  
  static final SimpleSymbol Lit9;
  
  public static final ModuleMethod make$Mnrecord$Mntype;
  
  public static final Macro primitive$Mnarray$Mnget;
  
  public static final Macro primitive$Mnarray$Mnlength;
  
  public static final Macro primitive$Mnarray$Mnnew;
  
  public static final Macro primitive$Mnarray$Mnset;
  
  public static final Macro primitive$Mnconstructor;
  
  public static final Macro primitive$Mnget$Mnfield;
  
  public static final Macro primitive$Mnget$Mnstatic;
  
  public static final Macro primitive$Mnset$Mnfield;
  
  public static final Macro primitive$Mnset$Mnstatic;
  
  public static final ModuleMethod record$Mnaccessor;
  
  public static final ModuleMethod record$Mnconstructor;
  
  public static final ModuleMethod record$Mnmodifier;
  
  public static final ModuleMethod record$Mnpredicate;
  
  public static final ModuleMethod record$Mntype$Mndescriptor;
  
  public static final ModuleMethod record$Mntype$Mnfield$Mnnames;
  
  public static final ModuleMethod record$Mntype$Mnname;
  
  public static final ModuleMethod record$Qu;
  
  public static final ModuleMethod subtype$Qu;
  
  static {
    Lit33 = IntNum.make(9);
    Lit32 = (SimpleSymbol)(new SimpleSymbol("make")).readResolve();
    Lit31 = (SimpleSymbol)(new SimpleSymbol("constant-fold")).readResolve();
    Lit30 = (SimpleSymbol)(new SimpleSymbol("subtype?")).readResolve();
    SimpleSymbol simpleSymbol = (SimpleSymbol)(new SimpleSymbol("primitive-set-static")).readResolve();
    Lit28 = simpleSymbol;
    SyntaxRule syntaxRule = new SyntaxRule(new SyntaxPattern("\f\030\f\007\f\017\f\027\b", new Object[0], 3), "\001\001\001", "\021\030\004\021\030\f\021\030\024\t\003\t\013\t\023\030\034", new Object[] { Lit31, Lit32, (new SimpleSymbol("<gnu.kawa.reflect.StaticSet>")).readResolve(), PairWithPosition.make(Lit33, LList.Empty, "/u2/home/jis/ai2-kawa/kawa/lib/reflection.scm", 454679) }0);
    Lit29 = new SyntaxRules(new Object[] { simpleSymbol }, new SyntaxRule[] { syntaxRule }, 3);
    simpleSymbol = (SimpleSymbol)(new SimpleSymbol("primitive-get-static")).readResolve();
    Lit26 = simpleSymbol;
    syntaxRule = new SyntaxRule(new SyntaxPattern("\f\030\f\007\f\017\f\027\b", new Object[0], 3), "\001\001\001", "\021\030\004\021\030\f\021\030\024\t\003\t\013\t\023\030\034", new Object[] { Lit31, Lit32, (new SimpleSymbol("<gnu.kawa.reflect.StaticGet>")).readResolve(), PairWithPosition.make(Lit33, LList.Empty, "/u2/home/jis/ai2-kawa/kawa/lib/reflection.scm", 430103) }0);
    Lit27 = new SyntaxRules(new Object[] { simpleSymbol }, new SyntaxRule[] { syntaxRule }, 3);
    simpleSymbol = (SimpleSymbol)(new SimpleSymbol("primitive-set-field")).readResolve();
    Lit24 = simpleSymbol;
    syntaxRule = new SyntaxRule(new SyntaxPattern("\f\030\f\007\f\017\f\027\b", new Object[0], 3), "\001\001\001", "\021\030\004\021\030\f\021\030\024\t\003\t\013\t\023\030\034", new Object[] { Lit31, Lit32, (new SimpleSymbol("<kawa.lang.SetFieldProc>")).readResolve(), PairWithPosition.make(Lit34, LList.Empty, "/u2/home/jis/ai2-kawa/kawa/lib/reflection.scm", 401431) }0);
    Lit25 = new SyntaxRules(new Object[] { simpleSymbol }, new SyntaxRule[] { syntaxRule }, 3);
    simpleSymbol = (SimpleSymbol)(new SimpleSymbol("primitive-get-field")).readResolve();
    Lit22 = simpleSymbol;
    syntaxRule = new SyntaxRule(new SyntaxPattern("\f\030\f\007\f\017\f\027\b", new Object[0], 3), "\001\001\001", "\021\030\004\021\030\f\021\030\024\t\003\t\013\t\023\030\034", new Object[] { Lit31, Lit32, (new SimpleSymbol("<kawa.lang.GetFieldProc>")).readResolve(), PairWithPosition.make(Lit34, LList.Empty, "/u2/home/jis/ai2-kawa/kawa/lib/reflection.scm", 376855) }0);
    Lit23 = new SyntaxRules(new Object[] { simpleSymbol }, new SyntaxRule[] { syntaxRule }, 3);
    simpleSymbol = (SimpleSymbol)(new SimpleSymbol("primitive-array-length")).readResolve();
    Lit20 = simpleSymbol;
    syntaxRule = new SyntaxRule(new SyntaxPattern("\f\030\f\007\b", new Object[0], 1), "\001", "\021\030\004\021\030\f\021\030\024\b\003", new Object[] { Lit31, Lit32, (new SimpleSymbol("<gnu.kawa.reflect.ArrayLength>")).readResolve() }, 0);
    Lit21 = new SyntaxRules(new Object[] { simpleSymbol }, new SyntaxRule[] { syntaxRule }, 1);
    simpleSymbol = (SimpleSymbol)(new SimpleSymbol("primitive-array-get")).readResolve();
    Lit18 = simpleSymbol;
    syntaxRule = new SyntaxRule(new SyntaxPattern("\f\030\f\007\b", new Object[0], 1), "\001", "\021\030\004\021\030\f\021\030\024\b\003", new Object[] { Lit31, Lit32, (new SimpleSymbol("<gnu.kawa.reflect.ArrayGet>")).readResolve() }, 0);
    Lit19 = new SyntaxRules(new Object[] { simpleSymbol }, new SyntaxRule[] { syntaxRule }, 1);
    simpleSymbol = (SimpleSymbol)(new SimpleSymbol("primitive-array-set")).readResolve();
    Lit16 = simpleSymbol;
    syntaxRule = new SyntaxRule(new SyntaxPattern("\f\030\f\007\b", new Object[0], 1), "\001", "\021\030\004\021\030\f\021\030\024\b\003", new Object[] { Lit31, Lit32, (new SimpleSymbol("<gnu.kawa.reflect.ArraySet>")).readResolve() }, 0);
    Lit17 = new SyntaxRules(new Object[] { simpleSymbol }, new SyntaxRule[] { syntaxRule }, 1);
    simpleSymbol = (SimpleSymbol)(new SimpleSymbol("primitive-array-new")).readResolve();
    Lit14 = simpleSymbol;
    syntaxRule = new SyntaxRule(new SyntaxPattern("\f\030\f\007\b", new Object[0], 1), "\001", "\021\030\004\021\030\f\021\030\024\b\003", new Object[] { Lit31, Lit32, (new SimpleSymbol("<gnu.kawa.reflect.ArrayNew>")).readResolve() }, 0);
    Lit15 = new SyntaxRules(new Object[] { simpleSymbol }, new SyntaxRule[] { syntaxRule }, 1);
    Lit13 = (SimpleSymbol)(new SimpleSymbol("record-type-field-names")).readResolve();
    Lit12 = (SimpleSymbol)(new SimpleSymbol("record-type-name")).readResolve();
    Lit11 = (SimpleSymbol)(new SimpleSymbol("record-type-descriptor")).readResolve();
    Lit10 = (SimpleSymbol)(new SimpleSymbol("record-predicate")).readResolve();
    Lit9 = (SimpleSymbol)(new SimpleSymbol("record?")).readResolve();
    Lit8 = (SimpleSymbol)(new SimpleSymbol("record-modifier")).readResolve();
    Lit7 = (SimpleSymbol)(new SimpleSymbol("record-accessor")).readResolve();
    Lit6 = (SimpleSymbol)(new SimpleSymbol("record-constructor")).readResolve();
    Lit5 = (SimpleSymbol)(new SimpleSymbol("make-record-type")).readResolve();
    Lit4 = new SyntaxTemplate("\001\001\003\003", "\021\030\004\031\b\035\033\021\030\f\t\013\b\021\030\024\t\013\b\025\021\030\034\t\023\b\033", new Object[] { (new SimpleSymbol("lambda")).readResolve(), (new SimpleSymbol("::")).readResolve(), Lit32, (new SimpleSymbol("as")).readResolve() }, 1);
    Lit3 = new SyntaxPattern("\r\037\030\b\b", new Object[0], 4);
    Lit2 = new SyntaxTemplate("\001\001\003", "\b\025\023", new Object[0], 1);
    Lit1 = new SyntaxPattern("\f\007\f\017,\r\027\020\b\b\b", new Object[0], 3);
    Lit0 = (SimpleSymbol)(new SimpleSymbol("primitive-constructor")).readResolve();
    $instance = new reflection();
    simpleSymbol = Lit0;
    reflection reflection1 = $instance;
    primitive$Mnconstructor = Macro.make(simpleSymbol, (Procedure)new ModuleMethod(reflection1, 2, null, 4097), $instance);
    make$Mnrecord$Mntype = new ModuleMethod(reflection1, 3, Lit5, 8194);
    record$Mnconstructor = new ModuleMethod(reflection1, 4, Lit6, 8193);
    record$Mnaccessor = new ModuleMethod(reflection1, 6, Lit7, 8194);
    record$Mnmodifier = new ModuleMethod(reflection1, 7, Lit8, 8194);
    record$Qu = new ModuleMethod(reflection1, 8, Lit9, 4097);
    record$Mnpredicate = new ModuleMethod(reflection1, 9, Lit10, 4097);
    record$Mntype$Mndescriptor = new ModuleMethod(reflection1, 10, Lit11, 4097);
    record$Mntype$Mnname = new ModuleMethod(reflection1, 11, Lit12, 4097);
    record$Mntype$Mnfield$Mnnames = new ModuleMethod(reflection1, 12, Lit13, 4097);
    primitive$Mnarray$Mnnew = Macro.make(Lit14, (Procedure)Lit15, $instance);
    primitive$Mnarray$Mnset = Macro.make(Lit16, (Procedure)Lit17, $instance);
    primitive$Mnarray$Mnget = Macro.make(Lit18, (Procedure)Lit19, $instance);
    primitive$Mnarray$Mnlength = Macro.make(Lit20, (Procedure)Lit21, $instance);
    primitive$Mnget$Mnfield = Macro.make(Lit22, (Procedure)Lit23, $instance);
    primitive$Mnset$Mnfield = Macro.make(Lit24, (Procedure)Lit25, $instance);
    primitive$Mnget$Mnstatic = Macro.make(Lit26, (Procedure)Lit27, $instance);
    primitive$Mnset$Mnstatic = Macro.make(Lit28, (Procedure)Lit29, $instance);
    subtype$Qu = new ModuleMethod(reflection1, 13, Lit30, 8194);
    $instance.run();
  }
  
  public reflection() {
    ModuleInfo.register(this);
  }
  
  public static boolean isRecord(Object paramObject) {
    return paramObject instanceof Record;
  }
  
  public static boolean isSubtype(Type paramType1, Type paramType2) {
    return paramType1.isSubtype(paramType2);
  }
  
  static Object lambda2(Object paramObject) {
    Object[] arrayOfObject = SyntaxPattern.allocVars(3, null);
    if (Lit1.match(paramObject, arrayOfObject, 0)) {
      paramObject = TemplateScope.make();
      paramObject = std_syntax.generateTemporaries(Lit2.execute(arrayOfObject, (TemplateScope)paramObject));
      arrayOfObject = SyntaxPattern.allocVars(4, arrayOfObject);
      if (Lit3.match(paramObject, arrayOfObject, 0)) {
        paramObject = TemplateScope.make();
        return Lit4.execute(arrayOfObject, (TemplateScope)paramObject);
      } 
      return syntax_case.error("syntax-case", paramObject);
    } 
    return syntax_case.error("syntax-case", paramObject);
  }
  
  public static ClassType makeRecordType(String paramString, LList paramLList) {
    return Record.makeRecordType(paramString, paramLList);
  }
  
  public static GetFieldProc recordAccessor(ClassType paramClassType, String paramString) {
    return new GetFieldProc(paramClassType, paramString);
  }
  
  public static RecordConstructor recordConstructor(ClassType paramClassType) {
    return recordConstructor(paramClassType, null);
  }
  
  public static RecordConstructor recordConstructor(ClassType paramClassType, Object paramObject) {
    return new RecordConstructor(paramClassType, paramObject);
  }
  
  public static SetFieldProc recordModifier(ClassType paramClassType, String paramString) {
    return new SetFieldProc(paramClassType, paramString);
  }
  
  public static Procedure recordPredicate(Object paramObject) {
    frame frame = new frame();
    frame.rtype = paramObject;
    return (Procedure)frame.lambda$Fn1;
  }
  
  public static Type recordTypeDescriptor(Object paramObject) {
    return Type.make(paramObject.getClass());
  }
  
  public static LList recordTypeFieldNames(Object paramObject) {
    try {
      ClassType classType = LangObjType.coerceToClassType(paramObject);
      return Record.typeFieldNames(classType);
    } catch (ClassCastException classCastException) {
      throw new WrongType(classCastException, "kawa.lang.Record.typeFieldNames(class-type)", 1, paramObject);
    } 
  }
  
  public static String recordTypeName(ClassType paramClassType) {
    return Compilation.demangleName(paramClassType.getName(), true);
  }
  
  public Object apply1(ModuleMethod paramModuleMethod, Object paramObject) {
    switch (paramModuleMethod.selector) {
      default:
        return super.apply1(paramModuleMethod, paramObject);
      case 4:
        try {
          ClassType classType = LangObjType.coerceToClassType(paramObject);
          return recordConstructor(classType);
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "record-constructor", 1, paramObject);
        } 
      case 8:
        return isRecord(paramObject) ? Boolean.TRUE : Boolean.FALSE;
      case 9:
        return recordPredicate(paramObject);
      case 10:
        return recordTypeDescriptor(paramObject);
      case 11:
        try {
          ClassType classType = LangObjType.coerceToClassType(paramObject);
          return recordTypeName(classType);
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "record-type-name", 1, paramObject);
        } 
      case 12:
        return recordTypeFieldNames(paramObject);
      case 2:
        break;
    } 
    return lambda2(paramObject);
  }
  
  public Object apply2(ModuleMethod paramModuleMethod, Object paramObject1, Object paramObject2) {
    String str1;
    String str2 = null;
    ClassType classType = null;
    ModuleMethod moduleMethod = null;
    switch (paramModuleMethod.selector) {
      default:
        return super.apply2(paramModuleMethod, paramObject1, paramObject2);
      case 3:
        if (paramObject1 == null) {
          paramModuleMethod = moduleMethod;
        } else {
          str1 = paramObject1.toString();
        } 
        try {
          paramObject1 = paramObject2;
          return makeRecordType(str1, (LList)paramObject1);
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "make-record-type", 2, paramObject2);
        } 
      case 4:
        try {
          ClassType classType1 = LangObjType.coerceToClassType(paramObject1);
          return recordConstructor(classType1, paramObject2);
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "record-constructor", 1, paramObject1);
        } 
      case 6:
        try {
          classType = LangObjType.coerceToClassType(paramObject1);
          if (paramObject2 == null) {
            str1 = str2;
            return recordAccessor(classType, str1);
          } 
          str1 = paramObject2.toString();
          return recordAccessor(classType, str1);
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "record-accessor", 1, paramObject1);
        } 
      case 7:
        try {
          ClassType classType1 = LangObjType.coerceToClassType(paramObject1);
          if (paramObject2 == null) {
            ClassType classType2 = classType;
            return recordModifier(classType1, (String)classType2);
          } 
          str1 = paramObject2.toString();
          return recordModifier(classType1, str1);
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "record-modifier", 1, paramObject1);
        } 
      case 13:
        break;
    } 
    try {
      Type type = LangObjType.coerceToType(paramObject1);
      try {
        paramObject1 = LangObjType.coerceToType(paramObject2);
        return isSubtype(type, (Type)paramObject1) ? Boolean.TRUE : Boolean.FALSE;
      } catch (ClassCastException classCastException) {
        throw new WrongType(classCastException, "subtype?", 2, paramObject2);
      } 
    } catch (ClassCastException classCastException) {
      throw new WrongType(classCastException, "subtype?", 1, paramObject1);
    } 
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
      case 12:
        paramCallContext.value1 = paramObject;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 1;
        return 0;
      case 11:
        if (LangObjType.coerceToClassTypeOrNull(paramObject) != null) {
          paramCallContext.value1 = paramObject;
          paramCallContext.proc = (Procedure)paramModuleMethod;
          paramCallContext.pc = 1;
          return 0;
        } 
        return -786431;
      case 10:
        paramCallContext.value1 = paramObject;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 1;
        return 0;
      case 9:
        paramCallContext.value1 = paramObject;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 1;
        return 0;
      case 8:
        paramCallContext.value1 = paramObject;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 1;
        return 0;
      case 4:
        break;
    } 
    if (LangObjType.coerceToClassTypeOrNull(paramObject) != null) {
      paramCallContext.value1 = paramObject;
      paramCallContext.proc = (Procedure)paramModuleMethod;
      paramCallContext.pc = 1;
      return 0;
    } 
    return -786431;
  }
  
  public int match2(ModuleMethod paramModuleMethod, Object paramObject1, Object paramObject2, CallContext paramCallContext) {
    switch (paramModuleMethod.selector) {
      default:
        return super.match2(paramModuleMethod, paramObject1, paramObject2, paramCallContext);
      case 13:
        if (LangObjType.coerceToTypeOrNull(paramObject1) != null) {
          paramCallContext.value1 = paramObject1;
          if (LangObjType.coerceToTypeOrNull(paramObject2) != null) {
            paramCallContext.value2 = paramObject2;
            paramCallContext.proc = (Procedure)paramModuleMethod;
            paramCallContext.pc = 2;
            return 0;
          } 
        } else {
          return -786431;
        } 
        return -786430;
      case 7:
        if (LangObjType.coerceToClassTypeOrNull(paramObject1) != null) {
          paramCallContext.value1 = paramObject1;
          paramCallContext.value2 = paramObject2;
          paramCallContext.proc = (Procedure)paramModuleMethod;
          paramCallContext.pc = 2;
          return 0;
        } 
        return -786431;
      case 6:
        if (LangObjType.coerceToClassTypeOrNull(paramObject1) != null) {
          paramCallContext.value1 = paramObject1;
          paramCallContext.value2 = paramObject2;
          paramCallContext.proc = (Procedure)paramModuleMethod;
          paramCallContext.pc = 2;
          return 0;
        } 
        return -786431;
      case 4:
        if (LangObjType.coerceToClassTypeOrNull(paramObject1) != null) {
          paramCallContext.value1 = paramObject1;
          paramCallContext.value2 = paramObject2;
          paramCallContext.proc = (Procedure)paramModuleMethod;
          paramCallContext.pc = 2;
          return 0;
        } 
        return -786431;
      case 3:
        break;
    } 
    paramCallContext.value1 = paramObject1;
    if (paramObject2 instanceof LList) {
      paramCallContext.value2 = paramObject2;
      paramCallContext.proc = (Procedure)paramModuleMethod;
      paramCallContext.pc = 2;
      return 0;
    } 
    return -786430;
  }
  
  public final void run(CallContext paramCallContext) {
    Consumer consumer = paramCallContext.consumer;
  }
  
  public class frame extends ModuleBody {
    final ModuleMethod lambda$Fn1;
    
    Object rtype;
    
    public frame() {
      ModuleMethod moduleMethod = new ModuleMethod(this, 1, null, 4097);
      moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/kawa/lib/reflection.scm:30");
      this.lambda$Fn1 = moduleMethod;
    }
    
    public Object apply1(ModuleMethod param1ModuleMethod, Object param1Object) {
      return (param1ModuleMethod.selector == 1) ? (lambda1(param1Object) ? Boolean.TRUE : Boolean.FALSE) : super.apply1(param1ModuleMethod, param1Object);
    }
    
    boolean lambda1(Object param1Object) {
      Object object = this.rtype;
      try {
        Type type = (Type)object;
        return type.isInstance(param1Object);
      } catch (ClassCastException classCastException) {
        throw new WrongType(classCastException, "gnu.bytecode.Type.isInstance(java.lang.Object)", 1, object);
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


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/kawa/lib/reflection.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */