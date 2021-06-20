package kawa;

import gnu.bytecode.ArrayClassLoader;
import gnu.bytecode.ZipLoader;
import gnu.expr.Compilation;
import gnu.expr.CompiledModule;
import gnu.expr.Language;
import gnu.expr.ModuleExp;
import gnu.expr.ModuleManager;
import gnu.lists.AbstractFormat;
import gnu.lists.Consumer;
import gnu.lists.VoidConsumer;
import gnu.mapping.CallContext;
import gnu.mapping.Environment;
import gnu.mapping.InPort;
import gnu.mapping.OutPort;
import gnu.mapping.Procedure;
import gnu.mapping.TtyInPort;
import gnu.mapping.Values;
import gnu.mapping.WrappedException;
import gnu.mapping.WrongArguments;
import gnu.text.FilePath;
import gnu.text.Lexer;
import gnu.text.Path;
import gnu.text.SourceMessages;
import gnu.text.SyntaxException;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.Writer;
import java.lang.reflect.Method;
import java.net.URL;

public class Shell {
  private static Class[] boolClasses;
  
  public static ThreadLocal currentLoadPath = new ThreadLocal();
  
  public static Object[] defaultFormatInfo;
  
  public static Method defaultFormatMethod;
  
  public static String defaultFormatName;
  
  static Object[][] formats;
  
  private static Class[] httpPrinterClasses;
  
  private static Class[] noClasses = new Class[0];
  
  private static Object portArg;
  
  private static Class[] xmlPrinterClasses;
  
  static {
    boolClasses = new Class[] { boolean.class };
    xmlPrinterClasses = new Class[] { OutPort.class, Object.class };
    httpPrinterClasses = new Class[] { OutPort.class };
    portArg = "(port)";
    formats = new Object[][] { 
        { "scheme", "gnu.kawa.functions.DisplayFormat", "getSchemeFormat", boolClasses, Boolean.FALSE }, { "readable-scheme", "gnu.kawa.functions.DisplayFormat", "getSchemeFormat", boolClasses, Boolean.TRUE }, { "elisp", "gnu.kawa.functions.DisplayFormat", "getEmacsLispFormat", boolClasses, Boolean.FALSE }, { "readable-elisp", "gnu.kawa.functions.DisplayFormat", "getEmacsLispFormat", boolClasses, Boolean.TRUE }, { "clisp", "gnu.kawa.functions.DisplayFormat", "getCommonLispFormat", boolClasses, Boolean.FALSE }, { "readable-clisp", "gnu.kawa.functions.DisplayFormat", "getCommonLispFormat", boolClasses, Boolean.TRUE }, { "commonlisp", "gnu.kawa.functions.DisplayFormat", "getCommonLispFormat", boolClasses, Boolean.FALSE }, { "readable-commonlisp", "gnu.kawa.functions.DisplayFormat", "getCommonLispFormat", boolClasses, Boolean.TRUE }, { "xml", "gnu.xml.XMLPrinter", "make", xmlPrinterClasses, portArg, null }, { "html", "gnu.xml.XMLPrinter", "make", xmlPrinterClasses, portArg, "html" }, 
        { "xhtml", "gnu.xml.XMLPrinter", "make", xmlPrinterClasses, portArg, "xhtml" }, { "cgi", "gnu.kawa.xml.HttpPrinter", "make", httpPrinterClasses, portArg }, { "ignore", "gnu.lists.VoidConsumer", "getInstance", noClasses }, { null } };
  }
  
  public static final CompiledModule checkCompiledZip(InputStream paramInputStream, Path paramPath, Environment paramEnvironment, Language paramLanguage) throws IOException {
    CompiledModule compiledModule1;
    CompiledModule compiledModule2;
    InputStream inputStream = null;
    try {
      boolean bool;
      paramInputStream.mark(5);
      if (paramInputStream.read() == 80 && paramInputStream.read() == 75 && paramInputStream.read() == 3 && paramInputStream.read() == 4) {
        bool = true;
      } else {
        bool = false;
      } 
      paramInputStream.reset();
      if (!bool)
        return (CompiledModule)inputStream; 
    } catch (IOException iOException) {
      return null;
    } 
    iOException.close();
    Environment environment = Environment.getCurrent();
    String str = paramPath.toString();
    if (paramEnvironment != environment) {
      try {
        Environment.setCurrent(paramEnvironment);
      } catch (IOException iOException1) {
        throw new WrappedException("load: " + str + " - " + iOException1.toString(), iOException1);
      } finally {
        if (paramEnvironment != environment)
          Environment.setCurrent(environment); 
      } 
      File file = ((FilePath)iOException1).toFile();
      if (!file.exists())
        throw new RuntimeException("load: " + str + " - not found"); 
      if (!file.canRead())
        throw new RuntimeException("load: " + str + " - not readable"); 
      compiledModule2 = CompiledModule.make((new ZipLoader(str)).loadAllClasses(), paramLanguage);
      compiledModule1 = compiledModule2;
      if (paramEnvironment != environment) {
        Environment.setCurrent(environment);
        return compiledModule2;
      } 
      return compiledModule1;
    } 
    if (!(compiledModule2 instanceof FilePath))
      throw new RuntimeException("load: " + compiledModule1 + " - not a file path"); 
  }
  
  static CompiledModule compileSource(InPort paramInPort, Environment paramEnvironment, URL paramURL, Language paramLanguage, SourceMessages paramSourceMessages) throws SyntaxException, IOException {
    Compilation compilation = paramLanguage.parse(paramInPort, paramSourceMessages, 1, ModuleManager.getInstance().findWithSourcePath(paramInPort.getName()));
    (CallContext.getInstance()).values = Values.noArgs;
    Object object = ModuleExp.evalModule1(paramEnvironment, compilation, paramURL, null);
    return (object == null || paramSourceMessages.seenErrors()) ? null : new CompiledModule(compilation.getModule(), object, paramLanguage);
  }
  
  public static Consumer getOutputConsumer(OutPort paramOutPort) {
    Object[] arrayOfObject = defaultFormatInfo;
    if (paramOutPort == null)
      return (Consumer)VoidConsumer.getInstance(); 
    if (arrayOfObject == null)
      return Language.getDefaultLanguage().getOutputConsumer((Writer)paramOutPort); 
    try {
      Object[] arrayOfObject1 = new Object[arrayOfObject.length - 4];
      System.arraycopy(arrayOfObject, 4, arrayOfObject1, 0, arrayOfObject1.length);
      int i = arrayOfObject1.length;
      while (true) {
        int j = i - 1;
        if (j >= 0) {
          i = j;
          if (arrayOfObject1[j] == portArg) {
            arrayOfObject1[j] = paramOutPort;
            i = j;
          } 
          continue;
        } 
        Object object = defaultFormatMethod.invoke(null, arrayOfObject1);
        if (object instanceof AbstractFormat) {
          paramOutPort.objectFormat = (AbstractFormat)object;
          return (Consumer)paramOutPort;
        } 
        return (Consumer)object;
      } 
    } catch (Throwable throwable) {
      throw new RuntimeException("cannot get output-format '" + defaultFormatName + "' - caught " + throwable);
    } 
  }
  
  public static void printError(Throwable paramThrowable, SourceMessages paramSourceMessages, OutPort paramOutPort) {
    WrongArguments wrongArguments;
    if (paramThrowable instanceof WrongArguments) {
      wrongArguments = (WrongArguments)paramThrowable;
      paramSourceMessages.printAll((PrintWriter)paramOutPort, 20);
      if (wrongArguments.usage != null)
        paramOutPort.println("usage: " + wrongArguments.usage); 
      wrongArguments.printStackTrace((PrintWriter)paramOutPort);
      return;
    } 
    if (wrongArguments instanceof ClassCastException) {
      paramSourceMessages.printAll((PrintWriter)paramOutPort, 20);
      paramOutPort.println("Invalid parameter, was: " + wrongArguments.getMessage());
      wrongArguments.printStackTrace((PrintWriter)paramOutPort);
      return;
    } 
    if (wrongArguments instanceof SyntaxException) {
      SyntaxException syntaxException = (SyntaxException)wrongArguments;
      if (syntaxException.getMessages() == paramSourceMessages) {
        syntaxException.printAll((PrintWriter)paramOutPort, 20);
        syntaxException.clear();
        return;
      } 
    } 
    paramSourceMessages.printAll((PrintWriter)paramOutPort, 20);
    wrongArguments.printStackTrace((PrintWriter)paramOutPort);
  }
  
  public static Throwable run(Language paramLanguage, Environment paramEnvironment, InPort paramInPort, Consumer paramConsumer, OutPort paramOutPort, URL paramURL, SourceMessages paramSourceMessages) {
    boolean bool;
    Language language = Language.setSaveCurrent(paramLanguage);
    Lexer lexer = paramLanguage.getLexer(paramInPort, paramSourceMessages);
    if (paramOutPort != null) {
      bool = true;
    } else {
      bool = false;
    } 
    lexer.setInteractive(bool);
    CallContext callContext = CallContext.getInstance();
    Consumer consumer = null;
    if (paramConsumer != null) {
      consumer = callContext.consumer;
      callContext.consumer = paramConsumer;
    } 
    try {
      Thread thread = Thread.currentThread();
      ClassLoader classLoader = thread.getContextClassLoader();
      if (!(classLoader instanceof ArrayClassLoader))
        thread.setContextClassLoader((ClassLoader)new ArrayClassLoader(classLoader)); 
    } catch (SecurityException securityException) {}
    label56: while (true) {
      try {
        boolean bool1;
        Compilation compilation = paramLanguage.parse(lexer, 7, null);
        if (bool) {
          bool1 = paramSourceMessages.checkErrors((PrintWriter)paramOutPort, 20);
        } else {
          if (paramSourceMessages.seenErrors())
            throw new SyntaxException(paramSourceMessages); 
          bool1 = false;
        } 
        if (compilation != null) {
          if (!bool1) {
            ModuleExp moduleExp = compilation.getModule();
            StringBuilder stringBuilder = (new StringBuilder()).append("atInteractiveLevel$");
            int i = ModuleExp.interactiveCounter + 1;
            ModuleExp.interactiveCounter = i;
            moduleExp.setName(stringBuilder.append(i).toString());
            while (true) {
              i = paramInPort.read();
              if (i >= 0 && i != 13 && i != 10)
                if (i != 32 && i != 9) {
                  paramInPort.unread();
                } else {
                  continue;
                }  
              if (ModuleExp.evalModule(paramEnvironment, callContext, compilation, paramURL, paramOutPort)) {
                if (paramConsumer instanceof Writer)
                  ((Writer)paramConsumer).flush(); 
                break;
              } 
              continue label56;
            } 
            if (i < 0)
              return null; 
          } 
          continue;
        } 
        return null;
      } catch (Throwable throwable) {
      
      } finally {
        if (paramConsumer != null)
          callContext.consumer = consumer; 
        Language.restoreCurrent(language);
      } 
      printError(throwable, paramSourceMessages, paramOutPort);
    } 
  }
  
  public static Throwable run(Language paramLanguage, Environment paramEnvironment, InPort paramInPort, OutPort paramOutPort1, OutPort paramOutPort2, SourceMessages paramSourceMessages) {
    AbstractFormat abstractFormat = null;
    if (paramOutPort1 != null)
      abstractFormat = paramOutPort1.objectFormat; 
    Consumer consumer = getOutputConsumer(paramOutPort1);
    try {
      return run(paramLanguage, paramEnvironment, paramInPort, consumer, paramOutPort2, null, paramSourceMessages);
    } finally {
      if (paramOutPort1 != null)
        paramOutPort1.objectFormat = abstractFormat; 
    } 
  }
  
  public static boolean run(Language paramLanguage, Environment paramEnvironment) {
    OutPort outPort;
    InPort inPort = InPort.inDefault();
    SourceMessages sourceMessages = new SourceMessages();
    if (inPort instanceof TtyInPort) {
      Procedure procedure = paramLanguage.getPrompter();
      if (procedure != null)
        ((TtyInPort)inPort).setPrompter(procedure); 
      outPort = OutPort.errDefault();
    } else {
      outPort = null;
    } 
    Throwable throwable = run(paramLanguage, paramEnvironment, inPort, OutPort.outDefault(), outPort, sourceMessages);
    if (throwable == null)
      return true; 
    printError(throwable, sourceMessages, OutPort.errDefault());
    return false;
  }
  
  public static boolean run(Language paramLanguage, Environment paramEnvironment, InPort paramInPort, Consumer paramConsumer, OutPort paramOutPort, URL paramURL) {
    SourceMessages sourceMessages = new SourceMessages();
    Throwable throwable = run(paramLanguage, paramEnvironment, paramInPort, paramConsumer, paramOutPort, paramURL, sourceMessages);
    if (throwable != null)
      printError(throwable, sourceMessages, paramOutPort); 
    return (throwable == null);
  }
  
  public static final boolean runFile(InputStream paramInputStream, Path paramPath, Environment paramEnvironment, boolean paramBoolean, int paramInt) throws Throwable {
    CompiledModule compiledModule;
    InputStream inputStream = paramInputStream;
    if (!(paramInputStream instanceof BufferedInputStream))
      inputStream = new BufferedInputStream(paramInputStream); 
    Language language = Language.getDefaultLanguage();
    Path path = currentLoadPath.get();
    try {
      currentLoadPath.set(paramPath);
      CompiledModule compiledModule1 = checkCompiledZip(inputStream, paramPath, paramEnvironment, language);
      compiledModule = compiledModule1;
    } finally {
      currentLoadPath.set(path);
    } 
    if (compiledModule != null)
      compiledModule.evalModule(paramEnvironment, OutPort.outDefault()); 
    currentLoadPath.set(path);
    return true;
  }
  
  public static boolean runFileOrClass(String paramString, boolean paramBoolean, int paramInt) {
    Language language = Language.getDefaultLanguage();
    try {
      InputStream inputStream;
      Path path;
      if (paramString.equals("-")) {
        path = Path.valueOf("/dev/stdin");
        inputStream = System.in;
      } else {
        path = Path.valueOf(paramString);
        inputStream = path.openInputStream();
      } 
      try {
        return runFile(inputStream, path, Environment.getCurrent(), paramBoolean, paramInt);
      } catch (Throwable throwable) {
        throwable.printStackTrace(System.err);
        return false;
      } 
    } catch (Throwable throwable) {
      try {
        Class<?> clazz = Class.forName(paramString);
        try {
          CompiledModule.make(clazz, language).evalModule(Environment.getCurrent(), OutPort.outDefault());
          return true;
        } catch (Throwable throwable1) {
          throwable1.printStackTrace();
          return false;
        } 
      } catch (Throwable throwable1) {
        System.err.println("Cannot read file " + throwable.getMessage());
        return false;
      } 
    } 
  }
  
  public static void setDefaultFormat(String paramString) {
    // Byte code:
    //   0: aload_0
    //   1: invokevirtual intern : ()Ljava/lang/String;
    //   4: astore_0
    //   5: aload_0
    //   6: putstatic kawa/Shell.defaultFormatName : Ljava/lang/String;
    //   9: iconst_0
    //   10: istore_1
    //   11: getstatic kawa/Shell.formats : [[Ljava/lang/Object;
    //   14: iload_1
    //   15: aaload
    //   16: astore_2
    //   17: aload_2
    //   18: iconst_0
    //   19: aaload
    //   20: astore_3
    //   21: aload_3
    //   22: ifnonnull -> 68
    //   25: getstatic java/lang/System.err : Ljava/io/PrintStream;
    //   28: new java/lang/StringBuilder
    //   31: dup
    //   32: invokespecial <init> : ()V
    //   35: ldc_w 'kawa: unknown output format ''
    //   38: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   41: aload_0
    //   42: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   45: ldc_w '''
    //   48: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   51: invokevirtual toString : ()Ljava/lang/String;
    //   54: invokevirtual println : (Ljava/lang/String;)V
    //   57: iconst_m1
    //   58: invokestatic exit : (I)V
    //   61: iload_1
    //   62: iconst_1
    //   63: iadd
    //   64: istore_1
    //   65: goto -> 11
    //   68: aload_3
    //   69: aload_0
    //   70: if_acmpne -> 61
    //   73: aload_2
    //   74: putstatic kawa/Shell.defaultFormatInfo : [Ljava/lang/Object;
    //   77: aload_2
    //   78: iconst_1
    //   79: aaload
    //   80: checkcast java/lang/String
    //   83: invokestatic forName : (Ljava/lang/String;)Ljava/lang/Class;
    //   86: aload_2
    //   87: iconst_2
    //   88: aaload
    //   89: checkcast java/lang/String
    //   92: aload_2
    //   93: iconst_3
    //   94: aaload
    //   95: checkcast [Ljava/lang/Class;
    //   98: checkcast [Ljava/lang/Class;
    //   101: invokevirtual getMethod : (Ljava/lang/String;[Ljava/lang/Class;)Ljava/lang/reflect/Method;
    //   104: putstatic kawa/Shell.defaultFormatMethod : Ljava/lang/reflect/Method;
    //   107: getstatic kawa/Shell.defaultFormatInfo : [Ljava/lang/Object;
    //   110: iconst_1
    //   111: aaload
    //   112: ldc 'gnu.lists.VoidConsumer'
    //   114: invokevirtual equals : (Ljava/lang/Object;)Z
    //   117: ifne -> 124
    //   120: iconst_1
    //   121: invokestatic setMainPrintValues : (Z)V
    //   124: return
    //   125: astore_2
    //   126: getstatic java/lang/System.err : Ljava/io/PrintStream;
    //   129: new java/lang/StringBuilder
    //   132: dup
    //   133: invokespecial <init> : ()V
    //   136: ldc_w 'kawa:  caught '
    //   139: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   142: aload_2
    //   143: invokevirtual append : (Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   146: ldc_w ' while looking for format ''
    //   149: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   152: aload_0
    //   153: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   156: ldc_w '''
    //   159: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   162: invokevirtual toString : ()Ljava/lang/String;
    //   165: invokevirtual println : (Ljava/lang/String;)V
    //   168: iconst_m1
    //   169: invokestatic exit : (I)V
    //   172: goto -> 107
    // Exception table:
    //   from	to	target	type
    //   77	107	125	java/lang/Throwable
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/kawa/Shell.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */