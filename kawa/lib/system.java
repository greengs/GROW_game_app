package kawa.lib;

import gnu.expr.ApplicationMainSupport;
import gnu.expr.Compilation;
import gnu.expr.ModuleBody;
import gnu.expr.ModuleInfo;
import gnu.expr.ModuleMethod;
import gnu.kawa.functions.AddOp;
import gnu.kawa.functions.IsEqual;
import gnu.lists.Consumer;
import gnu.lists.FVector;
import gnu.lists.LList;
import gnu.lists.Pair;
import gnu.mapping.CallContext;
import gnu.mapping.Procedure;
import gnu.mapping.SimpleSymbol;
import gnu.mapping.Values;
import gnu.mapping.WrongType;
import gnu.math.IntNum;
import gnu.text.SourceMessages;
import gnu.text.SyntaxException;
import java.io.InputStream;
import java.util.StringTokenizer;
import kawa.lang.CompileFile;
import kawa.lang.NamedException;
import kawa.standard.Scheme;

public class system extends ModuleBody {
  public static final system $instance;
  
  static final IntNum Lit0;
  
  static final IntNum Lit1;
  
  static final SimpleSymbol Lit10;
  
  static final SimpleSymbol Lit11 = (SimpleSymbol)(new SimpleSymbol("process-command-line-assignments")).readResolve();
  
  static final SimpleSymbol Lit2;
  
  static final SimpleSymbol Lit3;
  
  static final SimpleSymbol Lit4;
  
  static final SimpleSymbol Lit5;
  
  static final SimpleSymbol Lit6;
  
  static final SimpleSymbol Lit7;
  
  static final SimpleSymbol Lit8;
  
  static final SimpleSymbol Lit9;
  
  public static final ModuleMethod catch;
  
  public static Procedure command$Mnparse;
  
  public static final ModuleMethod compile$Mnfile;
  
  public static final ModuleMethod convert$Mnlist$Mnto$Mnstring$Mnarray;
  
  public static final ModuleMethod convert$Mnvector$Mnto$Mnstring$Mnarray;
  
  public static final ModuleMethod make$Mnprocess;
  
  public static final ModuleMethod open$Mninput$Mnpipe;
  
  public static final ModuleMethod process$Mncommand$Mnline$Mnassignments;
  
  public static final ModuleMethod system;
  
  public static final ModuleMethod tokenize$Mnstring$Mnto$Mnstring$Mnarray;
  
  public static final ModuleMethod tokenize$Mnstring$Mnusing$Mnshell;
  
  static {
    Lit10 = (SimpleSymbol)(new SimpleSymbol("catch")).readResolve();
    Lit9 = (SimpleSymbol)(new SimpleSymbol("compile-file")).readResolve();
    Lit8 = (SimpleSymbol)(new SimpleSymbol("tokenize-string-using-shell")).readResolve();
    Lit7 = (SimpleSymbol)(new SimpleSymbol("tokenize-string-to-string-array")).readResolve();
    Lit6 = (SimpleSymbol)(new SimpleSymbol("convert-list-to-string-array")).readResolve();
    Lit5 = (SimpleSymbol)(new SimpleSymbol("convert-vector-to-string-array")).readResolve();
    Lit4 = (SimpleSymbol)(new SimpleSymbol("system")).readResolve();
    Lit3 = (SimpleSymbol)(new SimpleSymbol("open-input-pipe")).readResolve();
    Lit2 = (SimpleSymbol)(new SimpleSymbol("make-process")).readResolve();
    Lit1 = IntNum.make(1);
    Lit0 = IntNum.make(0);
    $instance = new system();
    system system1 = $instance;
    make$Mnprocess = new ModuleMethod(system1, 1, Lit2, 8194);
    open$Mninput$Mnpipe = new ModuleMethod(system1, 2, Lit3, 4097);
    system = new ModuleMethod(system1, 3, Lit4, 4097);
    convert$Mnvector$Mnto$Mnstring$Mnarray = new ModuleMethod(system1, 4, Lit5, 4097);
    convert$Mnlist$Mnto$Mnstring$Mnarray = new ModuleMethod(system1, 5, Lit6, 4097);
    tokenize$Mnstring$Mnto$Mnstring$Mnarray = new ModuleMethod(system1, 6, Lit7, 4097);
    tokenize$Mnstring$Mnusing$Mnshell = new ModuleMethod(system1, 7, Lit8, 4097);
    compile$Mnfile = new ModuleMethod(system1, 8, Lit9, 8194);
    catch = new ModuleMethod(system1, 9, Lit10, 12291);
    process$Mncommand$Mnline$Mnassignments = new ModuleMethod(system1, 10, Lit11, 0);
    $instance.run();
  }
  
  public system() {
    ModuleInfo.register(this);
  }
  
  public static Object catch(Object paramObject, Procedure paramProcedure1, Procedure paramProcedure2) {
    try {
      return paramProcedure1.apply0();
    } catch (NamedException namedException) {
      return namedException.applyHandler(paramObject, paramProcedure2);
    } 
  }
  
  public static void compileFile(CharSequence paramCharSequence, String paramString) {
    SourceMessages sourceMessages = new SourceMessages();
    Compilation compilation = CompileFile.read(paramCharSequence.toString(), sourceMessages);
    compilation.explicit = true;
    if (sourceMessages.seenErrors())
      throw (Throwable)new SyntaxException(sourceMessages); 
    compilation.compileToArchive(compilation.getModule(), paramString);
    if (sourceMessages.seenErrors())
      throw (Throwable)new SyntaxException(sourceMessages); 
  }
  
  public static Object convertListToStringArray(Object paramObject) {
    try {
      LList lList = (LList)paramObject;
      String[] arrayOfString = new String[lists.length(lList)];
      int i = 0;
      while (true) {
        if (lists.isNull(paramObject))
          return arrayOfString; 
        try {
          Pair pair = (Pair)paramObject;
          paramObject = pair.getCar();
          if (paramObject == null) {
            paramObject = null;
          } else {
            paramObject = paramObject.toString();
          } 
          arrayOfString[i] = (String)paramObject;
          paramObject = pair.getCdr();
          i++;
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "pp", -2, paramObject);
        } 
      } 
    } catch (ClassCastException classCastException) {
      throw new WrongType(classCastException, "length", 1, paramObject);
    } 
  }
  
  public static Object convertVectorToStringArray(Object paramObject) {
    String[] arrayOfString;
    try {
      FVector fVector = (FVector)paramObject;
      int i = vectors.vectorLength(fVector);
      arrayOfString = new String[i];
      IntNum intNum = Lit0;
      while (Scheme.numEqu.apply2(intNum, Integer.valueOf(i)) == Boolean.FALSE) {
        int j = ((Number)intNum).intValue();
        try {
          Object object;
          FVector fVector1 = (FVector)paramObject;
          try {
            int k = ((Number)intNum).intValue();
            Object object1 = vectors.vectorRef(fVector1, k);
            if (object1 == null) {
              object1 = null;
            } else {
              object1 = object1.toString();
            } 
            arrayOfString[j] = (String)object1;
            object = AddOp.$Pl.apply2(intNum, Lit1);
          } catch (ClassCastException classCastException) {
            throw new WrongType(classCastException, "vector-ref", 2, object);
          } 
        } catch (ClassCastException classCastException1) {
          throw new WrongType(classCastException1, "vector-ref", 1, classCastException);
        } 
      } 
    } catch (ClassCastException classCastException1) {
      throw new WrongType(classCastException1, "vector-length", 1, classCastException);
    } 
    return arrayOfString;
  }
  
  public static Process makeProcess(Object paramObject1, Object paramObject2) {
    if (vectors.isVector(paramObject1)) {
      paramObject1 = convertVectorToStringArray(paramObject1);
    } else if (lists.isList(paramObject1)) {
      paramObject1 = convertListToStringArray(paramObject1);
    } else if (strings.isString(paramObject1)) {
      paramObject1 = command$Mnparse.apply1(paramObject1);
    } else if (!(paramObject1 instanceof String[])) {
      paramObject1 = misc.error$V("invalid arguments to make-process", new Object[0]);
    } 
    Runtime runtime = Runtime.getRuntime();
    try {
      String[] arrayOfString = (String[])paramObject1;
      try {
        paramObject1 = paramObject2;
        return runtime.exec(arrayOfString, (String[])paramObject1);
      } catch (ClassCastException classCastException) {
        throw new WrongType(classCastException, "java.lang.Runtime.exec(java.lang.String[],java.lang.String[])", 3, paramObject2);
      } 
    } catch (ClassCastException classCastException1) {
      throw new WrongType(classCastException1, "java.lang.Runtime.exec(java.lang.String[],java.lang.String[])", 2, classCastException);
    } 
  }
  
  public static InputStream openInputPipe(Object paramObject) {
    return makeProcess(paramObject, null).getInputStream();
  }
  
  public static void processCommandLineAssignments() {
    ApplicationMainSupport.processSetProperties();
  }
  
  public static int system(Object paramObject) {
    return makeProcess(paramObject, null).waitFor();
  }
  
  public static Object tokenizeStringToStringArray(String paramString) {
    Object object;
    StringTokenizer stringTokenizer = new StringTokenizer(paramString);
    LList lList = LList.Empty;
    while (stringTokenizer.hasMoreTokens())
      object = lists.cons(stringTokenizer.nextToken(), lList); 
    try {
      LList lList1 = (LList)object;
      int i = lists.length(lList1);
      String[] arrayOfString = new String[i];
      i--;
      while (true) {
        if (lists.isNull(object))
          return arrayOfString; 
        try {
          Pair pair = (Pair)object;
          object = pair.getCar();
          if (object == null) {
            object = null;
          } else {
            object = object.toString();
          } 
          arrayOfString[i] = (String)object;
          object = pair.getCdr();
          i--;
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "pp", -2, object);
        } 
      } 
    } catch (ClassCastException classCastException) {
      throw new WrongType(classCastException, "length", 1, object);
    } 
  }
  
  public static String[] tokenizeStringUsingShell(Object paramObject) {
    if (paramObject == null) {
      paramObject = null;
      return new String[] { "/bin/sh", "-c", (String)paramObject };
    } 
    paramObject = paramObject.toString();
    return new String[] { "/bin/sh", "-c", (String)paramObject };
  }
  
  public Object apply0(ModuleMethod paramModuleMethod) {
    if (paramModuleMethod.selector == 10) {
      processCommandLineAssignments();
      return Values.empty;
    } 
    return super.apply0(paramModuleMethod);
  }
  
  public Object apply1(ModuleMethod paramModuleMethod, Object paramObject) {
    String str;
    switch (paramModuleMethod.selector) {
      default:
        return super.apply1(paramModuleMethod, paramObject);
      case 2:
        return openInputPipe(paramObject);
      case 3:
        return Integer.valueOf(system(paramObject));
      case 4:
        return convertVectorToStringArray(paramObject);
      case 5:
        return convertListToStringArray(paramObject);
      case 6:
        if (paramObject == null) {
          paramModuleMethod = null;
          return tokenizeStringToStringArray((String)paramModuleMethod);
        } 
        str = paramObject.toString();
        return tokenizeStringToStringArray(str);
      case 7:
        break;
    } 
    return tokenizeStringUsingShell(paramObject);
  }
  
  public Object apply2(ModuleMethod paramModuleMethod, Object paramObject1, Object paramObject2) {
    switch (paramModuleMethod.selector) {
      default:
        return super.apply2(paramModuleMethod, paramObject1, paramObject2);
      case 1:
        return makeProcess(paramObject1, paramObject2);
      case 8:
        break;
    } 
    try {
      CharSequence charSequence = (CharSequence)paramObject1;
      if (paramObject2 == null) {
        paramModuleMethod = null;
        compileFile(charSequence, (String)paramModuleMethod);
        return Values.empty;
      } 
      String str = paramObject2.toString();
      compileFile(charSequence, str);
      return Values.empty;
    } catch (ClassCastException classCastException) {
      throw new WrongType(classCastException, "compile-file", 1, paramObject1);
    } 
  }
  
  public Object apply3(ModuleMethod paramModuleMethod, Object paramObject1, Object paramObject2, Object paramObject3) {
    if (paramModuleMethod.selector == 9)
      try {
        Procedure procedure = (Procedure)paramObject2;
        try {
          paramObject2 = paramObject3;
          return catch(paramObject1, procedure, (Procedure)paramObject2);
        } catch (ClassCastException null) {
          throw new WrongType(classCastException, "catch", 3, paramObject3);
        } 
      } catch (ClassCastException classCastException) {
        throw new WrongType(classCastException, "catch", 2, paramObject2);
      }  
    return super.apply3((ModuleMethod)classCastException, paramObject1, paramObject2, paramObject3);
  }
  
  public int match0(ModuleMethod paramModuleMethod, CallContext paramCallContext) {
    if (paramModuleMethod.selector == 10) {
      paramCallContext.proc = (Procedure)paramModuleMethod;
      paramCallContext.pc = 0;
      return 0;
    } 
    return super.match0(paramModuleMethod, paramCallContext);
  }
  
  public int match1(ModuleMethod paramModuleMethod, Object paramObject, CallContext paramCallContext) {
    switch (paramModuleMethod.selector) {
      default:
        return super.match1(paramModuleMethod, paramObject, paramCallContext);
      case 7:
        paramCallContext.value1 = paramObject;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 1;
        return 0;
      case 6:
        paramCallContext.value1 = paramObject;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 1;
        return 0;
      case 5:
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
    switch (paramModuleMethod.selector) {
      default:
        return super.match2(paramModuleMethod, paramObject1, paramObject2, paramCallContext);
      case 8:
        if (paramObject1 instanceof CharSequence) {
          paramCallContext.value1 = paramObject1;
          paramCallContext.value2 = paramObject2;
          paramCallContext.proc = (Procedure)paramModuleMethod;
          paramCallContext.pc = 2;
          return 0;
        } 
        return -786431;
      case 1:
        break;
    } 
    paramCallContext.value1 = paramObject1;
    paramCallContext.value2 = paramObject2;
    paramCallContext.proc = (Procedure)paramModuleMethod;
    paramCallContext.pc = 2;
    return 0;
  }
  
  public int match3(ModuleMethod paramModuleMethod, Object paramObject1, Object paramObject2, Object paramObject3, CallContext paramCallContext) {
    if (paramModuleMethod.selector == 9) {
      paramCallContext.value1 = paramObject1;
      if (!(paramObject2 instanceof Procedure))
        return -786430; 
      paramCallContext.value2 = paramObject2;
      if (!(paramObject3 instanceof Procedure))
        return -786429; 
      paramCallContext.value3 = paramObject3;
      paramCallContext.proc = (Procedure)paramModuleMethod;
      paramCallContext.pc = 3;
      return 0;
    } 
    return super.match3(paramModuleMethod, paramObject1, paramObject2, paramObject3, paramCallContext);
  }
  
  public final void run(CallContext paramCallContext) {
    ModuleMethod moduleMethod;
    Consumer consumer = paramCallContext.consumer;
    if (IsEqual.apply(System.getProperty("file.separator"), "/")) {
      moduleMethod = tokenize$Mnstring$Mnusing$Mnshell;
    } else {
      moduleMethod = tokenize$Mnstring$Mnto$Mnstring$Mnarray;
    } 
    command$Mnparse = (Procedure)moduleMethod;
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/kawa/lib/system.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */