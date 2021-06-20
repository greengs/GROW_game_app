package kawa.lib;

import gnu.expr.GenericProc;
import gnu.expr.Keyword;
import gnu.expr.ModuleBody;
import gnu.expr.ModuleInfo;
import gnu.expr.ModuleMethod;
import gnu.expr.Symbols;
import gnu.kawa.lispexpr.LispLanguage;
import gnu.kawa.xml.KNode;
import gnu.lists.Consumer;
import gnu.lists.LList;
import gnu.lists.Pair;
import gnu.mapping.CallContext;
import gnu.mapping.Environment;
import gnu.mapping.Namespace;
import gnu.mapping.Procedure;
import gnu.mapping.SimpleSymbol;
import gnu.mapping.Symbol;
import gnu.mapping.Values;
import gnu.mapping.WrongType;
import gnu.math.IntNum;
import gnu.text.Path;
import kawa.Version;
import kawa.lang.Promise;
import kawa.standard.Scheme;
import kawa.standard.throw_name;

public class misc extends ModuleBody {
  public static final misc $instance;
  
  static final IntNum Lit0;
  
  static final IntNum Lit1;
  
  static final SimpleSymbol Lit10;
  
  static final SimpleSymbol Lit11;
  
  static final SimpleSymbol Lit12;
  
  static final SimpleSymbol Lit13;
  
  static final SimpleSymbol Lit14;
  
  static final SimpleSymbol Lit15;
  
  static final SimpleSymbol Lit16;
  
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
  
  static final SimpleSymbol Lit26;
  
  static final SimpleSymbol Lit27;
  
  static final SimpleSymbol Lit28 = (SimpleSymbol)(new SimpleSymbol("add-procedure-properties")).readResolve();
  
  static final SimpleSymbol Lit3;
  
  static final SimpleSymbol Lit4;
  
  static final SimpleSymbol Lit5;
  
  static final SimpleSymbol Lit6;
  
  static final SimpleSymbol Lit7;
  
  static final SimpleSymbol Lit8;
  
  static final SimpleSymbol Lit9;
  
  public static final ModuleMethod add$Mnprocedure$Mnproperties;
  
  public static final ModuleMethod base$Mnuri;
  
  public static final ModuleMethod boolean$Qu;
  
  public static final ModuleMethod dynamic$Mnwind;
  
  public static final ModuleMethod environment$Mnbound$Qu;
  
  public static final ModuleMethod error;
  
  public static final ModuleMethod force;
  
  public static final ModuleMethod gentemp;
  
  public static final ModuleMethod interaction$Mnenvironment;
  
  static final ModuleMethod lambda$Fn1;
  
  static final ModuleMethod lambda$Fn2;
  
  public static final ModuleMethod namespace$Mnprefix;
  
  public static final ModuleMethod namespace$Mnuri;
  
  public static final ModuleMethod null$Mnenvironment;
  
  public static final GenericProc procedure$Mnproperty;
  
  static final ModuleMethod procedure$Mnproperty$Fn3;
  
  public static final ModuleMethod procedure$Qu;
  
  public static final ModuleMethod scheme$Mnimplementation$Mnversion;
  
  public static final ModuleMethod scheme$Mnreport$Mnenvironment;
  
  public static final ModuleMethod set$Mnprocedure$Mnproperty$Ex;
  
  public static final ModuleMethod string$Mn$Grsymbol;
  
  public static final GenericProc symbol$Eq$Qu;
  
  public static final ModuleMethod symbol$Mn$Grstring;
  
  public static final ModuleMethod symbol$Mnlocal$Mnname;
  
  public static final ModuleMethod symbol$Mnnamespace;
  
  public static final ModuleMethod symbol$Mnnamespace$Mnuri;
  
  public static final ModuleMethod symbol$Mnprefix;
  
  public static final ModuleMethod symbol$Qu;
  
  public static final ModuleMethod values;
  
  static {
    Lit27 = (SimpleSymbol)(new SimpleSymbol("gentemp")).readResolve();
    Lit26 = (SimpleSymbol)(new SimpleSymbol("base-uri")).readResolve();
    Lit25 = (SimpleSymbol)(new SimpleSymbol("error")).readResolve();
    Lit24 = (SimpleSymbol)(new SimpleSymbol("force")).readResolve();
    Lit23 = (SimpleSymbol)(new SimpleSymbol("dynamic-wind")).readResolve();
    Lit22 = (SimpleSymbol)(new SimpleSymbol("procedure-property")).readResolve();
    Lit21 = (SimpleSymbol)(new SimpleSymbol("set-procedure-property!")).readResolve();
    Lit20 = (SimpleSymbol)(new SimpleSymbol("scheme-implementation-version")).readResolve();
    Lit19 = (SimpleSymbol)(new SimpleSymbol("interaction-environment")).readResolve();
    Lit18 = (SimpleSymbol)(new SimpleSymbol("scheme-report-environment")).readResolve();
    Lit17 = (SimpleSymbol)(new SimpleSymbol("null-environment")).readResolve();
    Lit16 = (SimpleSymbol)(new SimpleSymbol("environment-bound?")).readResolve();
    Lit15 = (SimpleSymbol)(new SimpleSymbol("values")).readResolve();
    Lit14 = (SimpleSymbol)(new SimpleSymbol("procedure?")).readResolve();
    Lit13 = (SimpleSymbol)(new SimpleSymbol("string->symbol")).readResolve();
    Lit12 = (SimpleSymbol)(new SimpleSymbol("namespace-prefix")).readResolve();
    Lit11 = (SimpleSymbol)(new SimpleSymbol("namespace-uri")).readResolve();
    Lit10 = (SimpleSymbol)(new SimpleSymbol("symbol-prefix")).readResolve();
    Lit9 = (SimpleSymbol)(new SimpleSymbol("symbol-namespace-uri")).readResolve();
    Lit8 = (SimpleSymbol)(new SimpleSymbol("symbol-namespace")).readResolve();
    Lit7 = (SimpleSymbol)(new SimpleSymbol("symbol-local-name")).readResolve();
    Lit6 = (SimpleSymbol)(new SimpleSymbol("symbol->string")).readResolve();
    Lit5 = (SimpleSymbol)(new SimpleSymbol("symbol?")).readResolve();
    Lit4 = (SimpleSymbol)(new SimpleSymbol("boolean?")).readResolve();
    Lit3 = (SimpleSymbol)(new SimpleSymbol("misc-error")).readResolve();
    Lit2 = Keyword.make("setter");
    Lit1 = IntNum.make(5);
    Lit0 = IntNum.make(4);
    $instance = new misc();
    misc misc1 = $instance;
    boolean$Qu = new ModuleMethod(misc1, 3, Lit4, 4097);
    symbol$Qu = new ModuleMethod(misc1, 4, Lit5, 4097);
    symbol$Mn$Grstring = new ModuleMethod(misc1, 5, Lit6, 4097);
    ModuleMethod moduleMethod = new ModuleMethod(misc1, 6, null, 8194);
    moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/kawa/lib/misc.scm:25");
    lambda$Fn1 = moduleMethod;
    moduleMethod = new ModuleMethod(misc1, 7, null, -4094);
    moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/kawa/lib/misc.scm:27");
    lambda$Fn2 = moduleMethod;
    symbol$Mnlocal$Mnname = new ModuleMethod(misc1, 8, Lit7, 4097);
    symbol$Mnnamespace = new ModuleMethod(misc1, 9, Lit8, 4097);
    symbol$Mnnamespace$Mnuri = new ModuleMethod(misc1, 10, Lit9, 4097);
    symbol$Mnprefix = new ModuleMethod(misc1, 11, Lit10, 4097);
    namespace$Mnuri = new ModuleMethod(misc1, 12, Lit11, 4097);
    namespace$Mnprefix = new ModuleMethod(misc1, 13, Lit12, 4097);
    string$Mn$Grsymbol = new ModuleMethod(misc1, 14, Lit13, 4097);
    procedure$Qu = new ModuleMethod(misc1, 15, Lit14, 4097);
    values = new ModuleMethod(misc1, 16, Lit15, -4096);
    environment$Mnbound$Qu = new ModuleMethod(misc1, 17, Lit16, 8194);
    null$Mnenvironment = new ModuleMethod(misc1, 18, Lit17, 4096);
    scheme$Mnreport$Mnenvironment = new ModuleMethod(misc1, 20, Lit18, 4097);
    interaction$Mnenvironment = new ModuleMethod(misc1, 21, Lit19, 0);
    scheme$Mnimplementation$Mnversion = new ModuleMethod(misc1, 22, Lit20, 0);
    set$Mnprocedure$Mnproperty$Ex = new ModuleMethod(misc1, 23, Lit21, 12291);
    procedure$Mnproperty$Fn3 = new ModuleMethod(misc1, 24, Lit22, 12290);
    dynamic$Mnwind = new ModuleMethod(misc1, 26, Lit23, 12291);
    force = new ModuleMethod(misc1, 27, Lit24, 4097);
    error = new ModuleMethod(misc1, 28, Lit25, -4095);
    base$Mnuri = new ModuleMethod(misc1, 29, Lit26, 4096);
    gentemp = new ModuleMethod(misc1, 31, Lit27, 0);
    add$Mnprocedure$Mnproperties = new ModuleMethod(misc1, 32, Lit28, -4095);
    $instance.run();
  }
  
  public misc() {
    ModuleInfo.register(this);
  }
  
  public static void addProcedureProperties(GenericProc paramGenericProc, Object... paramVarArgs) {
    paramGenericProc.setProperties(paramVarArgs);
  }
  
  public static Object baseUri() {
    return baseUri(null);
  }
  
  public static Object baseUri(Object paramObject) {
    if (paramObject == null) {
      paramObject = Path.currentPath();
    } else {
      paramObject = ((KNode)paramObject).baseURI();
    } 
    Object object = paramObject;
    if (paramObject == Values.empty)
      object = Boolean.FALSE; 
    return object;
  }
  
  public static Object dynamicWind(Object paramObject1, Object paramObject2, Object paramObject3) {
    Scheme.applyToArgs.apply1(paramObject1);
    try {
      paramObject1 = Scheme.applyToArgs.apply1(paramObject2);
      return paramObject1;
    } finally {
      Scheme.applyToArgs.apply1(paramObject3);
    } 
  }
  
  public static Object error$V(Object paramObject, Object[] paramArrayOfObject) {
    frame frame = new frame();
    frame.msg = paramObject;
    LList lList = LList.makeList(paramArrayOfObject, 0);
    frame.msg = ports.callWithOutputString((Procedure)frame.lambda$Fn4);
    paramObject = LList.Empty;
    while (true) {
      Object object;
      if (lList == LList.Empty) {
        paramObject = LList.reverseInPlace(paramObject);
        return Scheme.apply.apply4(throw_name.throwName, Lit3, frame.msg, paramObject);
      } 
      try {
        Pair pair = (Pair)lList;
        object = pair.getCdr();
        Object object1 = pair.getCar();
        frame0 frame0 = new frame0();
        frame0.arg = object1;
        paramObject = Pair.make(ports.callWithOutputString((Procedure)frame0.lambda$Fn5), paramObject);
      } catch (ClassCastException classCastException) {
        throw new WrongType(classCastException, "arg0", -2, object);
      } 
    } 
  }
  
  public static Object force(Object paramObject) {
    return Promise.force(paramObject);
  }
  
  public static Symbol gentemp() {
    return (Symbol)Symbols.gentemp();
  }
  
  public static Environment interactionEnvironment() {
    return Environment.user();
  }
  
  public static boolean isBoolean(Object paramObject) {
    boolean bool;
    if (paramObject == Boolean.TRUE) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool ? bool : ((paramObject == Boolean.FALSE));
  }
  
  public static boolean isEnvironmentBound(Environment paramEnvironment, Object paramObject) {
    return paramEnvironment.isBound(LispLanguage.langSymbolToSymbol(paramObject));
  }
  
  public static boolean isProcedure(Object paramObject) {
    boolean bool = paramObject instanceof Procedure;
    return bool ? bool : (paramObject instanceof gnu.kawa.lispexpr.LangObjType);
  }
  
  public static boolean isSymbol(Object paramObject) {
    return paramObject instanceof Symbol;
  }
  
  static boolean lambda1(Symbol paramSymbol1, Symbol paramSymbol2) {
    return Symbol.equals(paramSymbol1, paramSymbol2);
  }
  
  static boolean lambda2$V(Symbol paramSymbol1, Symbol paramSymbol2, Object[] paramArrayOfObject) {
    boolean bool = false;
    LList lList = LList.makeList(paramArrayOfObject, 0);
    boolean bool1 = Symbol.equals(paramSymbol1, paramSymbol2);
    if (bool1) {
      if (Scheme.apply.apply3(symbol$Eq$Qu, paramSymbol2, lList) != Boolean.FALSE)
        bool = true; 
      return bool;
    } 
    return bool1;
  }
  
  public static CharSequence namespacePrefix(Namespace paramNamespace) {
    return paramNamespace.getPrefix();
  }
  
  public static CharSequence namespaceUri(Namespace paramNamespace) {
    return paramNamespace.getName();
  }
  
  public static Environment nullEnvironment() {
    return nullEnvironment(Boolean.FALSE);
  }
  
  public static Environment nullEnvironment(Object paramObject) {
    return Scheme.nullEnvironment;
  }
  
  public static Object procedureProperty(Procedure paramProcedure, Object paramObject) {
    return procedureProperty(paramProcedure, paramObject, Boolean.FALSE);
  }
  
  public static Object procedureProperty(Procedure paramProcedure, Object paramObject1, Object paramObject2) {
    return paramProcedure.getProperty(paramObject1, paramObject2);
  }
  
  public static String schemeImplementationVersion() {
    return Version.getVersion();
  }
  
  public static Object schemeReportEnvironment(Object paramObject) {
    return (Scheme.isEqv.apply2(paramObject, Lit0) != Boolean.FALSE) ? Scheme.r4Environment : ((Scheme.isEqv.apply2(paramObject, Lit1) != Boolean.FALSE) ? Scheme.r5Environment : error$V("scheme-report-environment version must be 4 or 5", new Object[0]));
  }
  
  public static void setProcedureProperty$Ex(Procedure paramProcedure, Object paramObject1, Object paramObject2) {
    paramProcedure.setProperty(paramObject1, paramObject2);
  }
  
  public static SimpleSymbol string$To$Symbol(CharSequence paramCharSequence) {
    return SimpleSymbol.valueOf(paramCharSequence.toString());
  }
  
  public static String symbol$To$String(Symbol paramSymbol) {
    return paramSymbol.toString();
  }
  
  public static String symbolLocalName(Symbol paramSymbol) {
    return paramSymbol.getLocalPart();
  }
  
  public static Namespace symbolNamespace(Symbol paramSymbol) {
    return paramSymbol.getNamespace();
  }
  
  public static String symbolNamespaceUri(Symbol paramSymbol) {
    return paramSymbol.getNamespaceURI();
  }
  
  public static String symbolPrefix(Symbol paramSymbol) {
    return paramSymbol.getPrefix();
  }
  
  public static Object values(Object... paramVarArgs) {
    return Values.make(paramVarArgs);
  }
  
  public Object apply0(ModuleMethod paramModuleMethod) {
    switch (paramModuleMethod.selector) {
      default:
        return super.apply0(paramModuleMethod);
      case 18:
        return nullEnvironment();
      case 21:
        return interactionEnvironment();
      case 22:
        return schemeImplementationVersion();
      case 29:
        return baseUri();
      case 31:
        break;
    } 
    return gentemp();
  }
  
  public Object apply1(ModuleMethod paramModuleMethod, Object paramObject) {
    switch (paramModuleMethod.selector) {
      default:
        return super.apply1(paramModuleMethod, paramObject);
      case 3:
        return isBoolean(paramObject) ? Boolean.TRUE : Boolean.FALSE;
      case 4:
        return isSymbol(paramObject) ? Boolean.TRUE : Boolean.FALSE;
      case 5:
        try {
          Symbol symbol = (Symbol)paramObject;
          return symbol$To$String(symbol);
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "symbol->string", 1, paramObject);
        } 
      case 8:
        try {
          Symbol symbol = (Symbol)paramObject;
          return symbolLocalName(symbol);
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "symbol-local-name", 1, paramObject);
        } 
      case 9:
        try {
          Symbol symbol = (Symbol)paramObject;
          return symbolNamespace(symbol);
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "symbol-namespace", 1, paramObject);
        } 
      case 10:
        try {
          Symbol symbol = (Symbol)paramObject;
          return symbolNamespaceUri(symbol);
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "symbol-namespace-uri", 1, paramObject);
        } 
      case 11:
        try {
          Symbol symbol = (Symbol)paramObject;
          return symbolPrefix(symbol);
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "symbol-prefix", 1, paramObject);
        } 
      case 12:
        try {
          Namespace namespace = (Namespace)paramObject;
          return namespaceUri(namespace);
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "namespace-uri", 1, paramObject);
        } 
      case 13:
        try {
          Namespace namespace = (Namespace)paramObject;
          return namespacePrefix(namespace);
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "namespace-prefix", 1, paramObject);
        } 
      case 14:
        try {
          CharSequence charSequence = (CharSequence)paramObject;
          return string$To$Symbol(charSequence);
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "string->symbol", 1, paramObject);
        } 
      case 15:
        return isProcedure(paramObject) ? Boolean.TRUE : Boolean.FALSE;
      case 18:
        return nullEnvironment(paramObject);
      case 20:
        return schemeReportEnvironment(paramObject);
      case 27:
        return force(paramObject);
      case 29:
        break;
    } 
    return baseUri(paramObject);
  }
  
  public Object apply2(ModuleMethod paramModuleMethod, Object paramObject1, Object paramObject2) {
    switch (paramModuleMethod.selector) {
      default:
        return super.apply2(paramModuleMethod, paramObject1, paramObject2);
      case 6:
        try {
          Symbol symbol = (Symbol)paramObject1;
          try {
            paramObject1 = paramObject2;
            return lambda1(symbol, (Symbol)paramObject1) ? Boolean.TRUE : Boolean.FALSE;
          } catch (ClassCastException classCastException) {
            throw new WrongType(classCastException, "lambda", 2, paramObject2);
          } 
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "lambda", 1, paramObject1);
        } 
      case 17:
        try {
          Environment environment = (Environment)paramObject1;
          return isEnvironmentBound(environment, paramObject2) ? Boolean.TRUE : Boolean.FALSE;
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "environment-bound?", 1, paramObject1);
        } 
      case 24:
        break;
    } 
    try {
      Procedure procedure = (Procedure)paramObject1;
      return procedureProperty(procedure, paramObject2);
    } catch (ClassCastException classCastException) {
      throw new WrongType(classCastException, "procedure-property", 1, paramObject1);
    } 
  }
  
  public Object apply3(ModuleMethod paramModuleMethod, Object paramObject1, Object paramObject2, Object paramObject3) {
    switch (paramModuleMethod.selector) {
      default:
        return super.apply3(paramModuleMethod, paramObject1, paramObject2, paramObject3);
      case 23:
        try {
          Procedure procedure = (Procedure)paramObject1;
          setProcedureProperty$Ex(procedure, paramObject2, paramObject3);
          return Values.empty;
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "set-procedure-property!", 1, paramObject1);
        } 
      case 24:
        try {
          Procedure procedure = (Procedure)paramObject1;
          return procedureProperty(procedure, paramObject2, paramObject3);
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "procedure-property", 1, paramObject1);
        } 
      case 26:
        break;
    } 
    return dynamicWind(paramObject1, paramObject2, paramObject3);
  }
  
  public Object applyN(ModuleMethod paramModuleMethod, Object[] paramArrayOfObject) {
    int i;
    Object object2;
    switch (paramModuleMethod.selector) {
      default:
        return super.applyN(paramModuleMethod, paramArrayOfObject);
      case 7:
        object2 = paramArrayOfObject[0];
        try {
          Symbol symbol = (Symbol)object2;
          object2 = paramArrayOfObject[1];
          try {
            Symbol symbol1 = (Symbol)object2;
            int j = paramArrayOfObject.length - 2;
            object2 = new Object[j];
            while (true) {
              if (--j < 0) {
                if (lambda2$V(symbol, symbol1, (Object[])object2))
                  return Boolean.TRUE; 
              } else {
                object2[j] = paramArrayOfObject[j + 2];
                continue;
              } 
              return Boolean.FALSE;
            } 
          } catch (ClassCastException classCastException) {
            throw new WrongType(classCastException, "lambda", 2, object2);
          } 
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "lambda", 1, object2);
        } 
      case 16:
        return values(paramArrayOfObject);
      case 28:
        object1 = paramArrayOfObject[0];
        i = paramArrayOfObject.length - 1;
        object2 = new Object[i];
        while (true) {
          if (--i < 0)
            return error$V(object1, (Object[])object2); 
          object2[i] = paramArrayOfObject[i + 1];
        } 
      case 32:
        break;
    } 
    Object object1 = paramArrayOfObject[0];
    try {
      object2 = object1;
      i = paramArrayOfObject.length - 1;
      object1 = new Object[i];
      while (true) {
        if (--i < 0) {
          addProcedureProperties((GenericProc)object2, (Object[])object1);
          return Values.empty;
        } 
        object1[i] = paramArrayOfObject[i + 1];
      } 
    } catch (ClassCastException classCastException) {
      throw new WrongType(classCastException, "add-procedure-properties", 1, object1);
    } 
  }
  
  public int match0(ModuleMethod paramModuleMethod, CallContext paramCallContext) {
    switch (paramModuleMethod.selector) {
      default:
        return super.match0(paramModuleMethod, paramCallContext);
      case 31:
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 0;
        return 0;
      case 29:
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 0;
        return 0;
      case 22:
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 0;
        return 0;
      case 21:
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 0;
        return 0;
      case 18:
        break;
    } 
    paramCallContext.proc = (Procedure)paramModuleMethod;
    paramCallContext.pc = 0;
    return 0;
  }
  
  public int match1(ModuleMethod paramModuleMethod, Object paramObject, CallContext paramCallContext) {
    switch (paramModuleMethod.selector) {
      default:
        return super.match1(paramModuleMethod, paramObject, paramCallContext);
      case 29:
        paramCallContext.value1 = paramObject;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 1;
        return 0;
      case 27:
        paramCallContext.value1 = paramObject;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 1;
        return 0;
      case 20:
        paramCallContext.value1 = paramObject;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 1;
        return 0;
      case 18:
        paramCallContext.value1 = paramObject;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 1;
        return 0;
      case 15:
        paramCallContext.value1 = paramObject;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 1;
        return 0;
      case 14:
        if (paramObject instanceof CharSequence) {
          paramCallContext.value1 = paramObject;
          paramCallContext.proc = (Procedure)paramModuleMethod;
          paramCallContext.pc = 1;
          return 0;
        } 
        return -786431;
      case 13:
        if (!(paramObject instanceof Namespace))
          return -786431; 
        paramCallContext.value1 = paramObject;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 1;
        return 0;
      case 12:
        if (!(paramObject instanceof Namespace))
          return -786431; 
        paramCallContext.value1 = paramObject;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 1;
        return 0;
      case 11:
        if (!(paramObject instanceof Symbol))
          return -786431; 
        paramCallContext.value1 = paramObject;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 1;
        return 0;
      case 10:
        if (!(paramObject instanceof Symbol))
          return -786431; 
        paramCallContext.value1 = paramObject;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 1;
        return 0;
      case 9:
        if (!(paramObject instanceof Symbol))
          return -786431; 
        paramCallContext.value1 = paramObject;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 1;
        return 0;
      case 8:
        if (!(paramObject instanceof Symbol))
          return -786431; 
        paramCallContext.value1 = paramObject;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 1;
        return 0;
      case 5:
        if (!(paramObject instanceof Symbol))
          return -786431; 
        paramCallContext.value1 = paramObject;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 1;
        return 0;
      case 4:
        paramCallContext.value1 = paramObject;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 1;
        return 0;
      case 3:
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
      case 24:
        if (paramObject1 instanceof Procedure) {
          paramCallContext.value1 = paramObject1;
          paramCallContext.value2 = paramObject2;
          paramCallContext.proc = (Procedure)paramModuleMethod;
          paramCallContext.pc = 2;
          return 0;
        } 
        return SYNTHETIC_LOCAL_VARIABLE_5;
      case 17:
        if (paramObject1 instanceof Environment) {
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
    if (paramObject1 instanceof Symbol) {
      paramCallContext.value1 = paramObject1;
      if (!(paramObject2 instanceof Symbol))
        return -786430; 
      paramCallContext.value2 = paramObject2;
      paramCallContext.proc = (Procedure)paramModuleMethod;
      paramCallContext.pc = 2;
      return 0;
    } 
    return SYNTHETIC_LOCAL_VARIABLE_5;
  }
  
  public int match3(ModuleMethod paramModuleMethod, Object paramObject1, Object paramObject2, Object paramObject3, CallContext paramCallContext) {
    switch (paramModuleMethod.selector) {
      default:
        return super.match3(paramModuleMethod, paramObject1, paramObject2, paramObject3, paramCallContext);
      case 26:
        paramCallContext.value1 = paramObject1;
        paramCallContext.value2 = paramObject2;
        paramCallContext.value3 = paramObject3;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 3;
        return 0;
      case 24:
        if (!(paramObject1 instanceof Procedure))
          return -786431; 
        paramCallContext.value1 = paramObject1;
        paramCallContext.value2 = paramObject2;
        paramCallContext.value3 = paramObject3;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 3;
        return 0;
      case 23:
        break;
    } 
    if (!(paramObject1 instanceof Procedure))
      return -786431; 
    paramCallContext.value1 = paramObject1;
    paramCallContext.value2 = paramObject2;
    paramCallContext.value3 = paramObject3;
    paramCallContext.proc = (Procedure)paramModuleMethod;
    paramCallContext.pc = 3;
    return 0;
  }
  
  public int matchN(ModuleMethod paramModuleMethod, Object[] paramArrayOfObject, CallContext paramCallContext) {
    switch (paramModuleMethod.selector) {
      default:
        return super.matchN(paramModuleMethod, paramArrayOfObject, paramCallContext);
      case 32:
        paramCallContext.values = paramArrayOfObject;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 5;
        return 0;
      case 28:
        paramCallContext.values = paramArrayOfObject;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 5;
        return 0;
      case 16:
        paramCallContext.values = paramArrayOfObject;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 5;
        return 0;
      case 7:
        break;
    } 
    paramCallContext.values = paramArrayOfObject;
    paramCallContext.proc = (Procedure)paramModuleMethod;
    paramCallContext.pc = 5;
    return 0;
  }
  
  public final void run(CallContext paramCallContext) {
    Consumer consumer = paramCallContext.consumer;
    symbol$Eq$Qu = new GenericProc("symbol=?");
    symbol$Eq$Qu.setProperties(new Object[] { lambda$Fn1, lambda$Fn2 });
    procedure$Mnproperty = new GenericProc("procedure-property");
    GenericProc genericProc = procedure$Mnproperty;
    Keyword keyword = Lit2;
    ModuleMethod moduleMethod1 = set$Mnprocedure$Mnproperty$Ex;
    ModuleMethod moduleMethod2 = procedure$Mnproperty$Fn3;
    genericProc.setProperties(new Object[] { keyword, moduleMethod1, procedure$Mnproperty$Fn3 });
  }
  
  public class frame extends ModuleBody {
    final ModuleMethod lambda$Fn4;
    
    Object msg;
    
    public frame() {
      ModuleMethod moduleMethod = new ModuleMethod(this, 2, null, 4097);
      moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/kawa/lib/misc.scm:104");
      this.lambda$Fn4 = moduleMethod;
    }
    
    public Object apply1(ModuleMethod param1ModuleMethod, Object param1Object) {
      if (param1ModuleMethod.selector == 2) {
        lambda3(param1Object);
        return Values.empty;
      } 
      return super.apply1(param1ModuleMethod, param1Object);
    }
    
    void lambda3(Object param1Object) {
      ports.display(this.msg, param1Object);
    }
    
    public int match1(ModuleMethod param1ModuleMethod, Object param1Object, CallContext param1CallContext) {
      if (param1ModuleMethod.selector == 2) {
        param1CallContext.value1 = param1Object;
        param1CallContext.proc = (Procedure)param1ModuleMethod;
        param1CallContext.pc = 1;
        return 0;
      } 
      return super.match1(param1ModuleMethod, param1Object, param1CallContext);
    }
  }
  
  public class frame0 extends ModuleBody {
    Object arg;
    
    final ModuleMethod lambda$Fn5;
    
    public frame0() {
      ModuleMethod moduleMethod = new ModuleMethod(this, 1, null, 4097);
      moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/kawa/lib/misc.scm:107");
      this.lambda$Fn5 = moduleMethod;
    }
    
    public Object apply1(ModuleMethod param1ModuleMethod, Object param1Object) {
      if (param1ModuleMethod.selector == 1) {
        lambda4(param1Object);
        return Values.empty;
      } 
      return super.apply1(param1ModuleMethod, param1Object);
    }
    
    void lambda4(Object param1Object) {
      ports.write(this.arg, param1Object);
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


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/kawa/lib/misc.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */