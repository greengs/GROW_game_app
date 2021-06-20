package kawa;

import gnu.expr.ApplicationMainSupport;
import gnu.expr.Compilation;
import gnu.expr.Language;
import gnu.expr.ModuleBody;
import gnu.expr.ModuleExp;
import gnu.expr.ModuleManager;
import gnu.kawa.servlet.HttpRequestContext;
import gnu.lists.FString;
import gnu.mapping.CharArrayInPort;
import gnu.mapping.Environment;
import gnu.mapping.InPort;
import gnu.mapping.OutPort;
import gnu.mapping.Procedure0or1;
import gnu.mapping.Values;
import gnu.text.SourceMessages;
import gnu.text.WriterManager;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Vector;

public class repl extends Procedure0or1 {
  public static String compilationTopname = null;
  
  static int defaultParseOptions = 72;
  
  public static String homeDirectory;
  
  public static boolean noConsole;
  
  static Language previousLanguage;
  
  static boolean shutdownRegistered = WriterManager.instance.registerShutdownHook();
  
  Language language;
  
  public repl(Language paramLanguage) {
    this.language = paramLanguage;
  }
  
  static void bad_option(String paramString) {
    System.err.println("kawa: bad option '" + paramString + "'");
    printOptions(System.err);
    System.exit(-1);
  }
  
  static void checkInitFile() {
    if (homeDirectory == null) {
      File file;
      Boolean bool;
      String str = null;
      homeDirectory = System.getProperty("user.home");
      if (homeDirectory != null) {
        FString fString = new FString(homeDirectory);
        if ("/".equals(System.getProperty("file.separator"))) {
          str = ".kawarc.scm";
        } else {
          str = "kawarc.scm";
        } 
        file = new File(homeDirectory, str);
      } else {
        bool = Boolean.FALSE;
      } 
      Environment.getCurrent().put("home-directory", bool);
      if (file != null && file.exists() && !Shell.runFileOrClass(file.getPath(), true, 0))
        System.exit(-1); 
    } 
  }
  
  public static void compileFiles(String[] paramArrayOfString, int paramInt1, int paramInt2) {
    // Byte code:
    //   0: invokestatic getInstance : ()Lgnu/expr/ModuleManager;
    //   3: astore #10
    //   5: iload_2
    //   6: iload_1
    //   7: isub
    //   8: anewarray gnu/expr/Compilation
    //   11: astore #7
    //   13: iload_2
    //   14: iload_1
    //   15: isub
    //   16: anewarray gnu/expr/ModuleInfo
    //   19: astore #8
    //   21: new gnu/text/SourceMessages
    //   24: dup
    //   25: invokespecial <init> : ()V
    //   28: astore #9
    //   30: iload_1
    //   31: istore_3
    //   32: iload_3
    //   33: iload_2
    //   34: if_icmpge -> 268
    //   37: aload_0
    //   38: iload_3
    //   39: aaload
    //   40: astore #11
    //   42: aload #11
    //   44: invokestatic getLanguageFromFilenameExtension : (Ljava/lang/String;)V
    //   47: invokestatic getDefaultLanguage : ()Lgnu/expr/Language;
    //   50: astore #12
    //   52: aconst_null
    //   53: astore #6
    //   55: aload #6
    //   57: astore #5
    //   59: aload #11
    //   61: invokestatic openFile : (Ljava/lang/Object;)Lgnu/mapping/InPort;
    //   64: astore #13
    //   66: aload #6
    //   68: astore #5
    //   70: aload #12
    //   72: aload #13
    //   74: aload #9
    //   76: getstatic kawa/repl.defaultParseOptions : I
    //   79: invokevirtual parse : (Lgnu/mapping/InPort;Lgnu/text/SourceMessages;I)Lgnu/expr/Compilation;
    //   82: astore #6
    //   84: aload #6
    //   86: astore #5
    //   88: getstatic kawa/repl.compilationTopname : Ljava/lang/String;
    //   91: ifnull -> 158
    //   94: aload #6
    //   96: astore #5
    //   98: new gnu/bytecode/ClassType
    //   101: dup
    //   102: getstatic kawa/repl.compilationTopname : Ljava/lang/String;
    //   105: invokestatic mangleNameIfNeeded : (Ljava/lang/String;)Ljava/lang/String;
    //   108: invokespecial <init> : (Ljava/lang/String;)V
    //   111: astore #12
    //   113: aload #6
    //   115: astore #5
    //   117: aload #6
    //   119: invokevirtual getModule : ()Lgnu/expr/ModuleExp;
    //   122: astore #13
    //   124: aload #6
    //   126: astore #5
    //   128: aload #13
    //   130: aload #12
    //   132: invokevirtual setType : (Lgnu/bytecode/ClassType;)V
    //   135: aload #6
    //   137: astore #5
    //   139: aload #13
    //   141: getstatic kawa/repl.compilationTopname : Ljava/lang/String;
    //   144: invokevirtual setName : (Ljava/lang/String;)V
    //   147: aload #6
    //   149: astore #5
    //   151: aload #6
    //   153: aload #12
    //   155: putfield mainClass : Lgnu/bytecode/ClassType;
    //   158: aload #6
    //   160: astore #5
    //   162: aload #8
    //   164: iload_3
    //   165: iload_1
    //   166: isub
    //   167: aload #10
    //   169: aload #6
    //   171: invokevirtual find : (Lgnu/expr/Compilation;)Lgnu/expr/ModuleInfo;
    //   174: aastore
    //   175: aload #7
    //   177: iload_3
    //   178: iload_1
    //   179: isub
    //   180: aload #6
    //   182: aastore
    //   183: aload #9
    //   185: invokevirtual seenErrorsOrWarnings : ()Z
    //   188: ifeq -> 239
    //   191: getstatic java/lang/System.err : Ljava/io/PrintStream;
    //   194: new java/lang/StringBuilder
    //   197: dup
    //   198: invokespecial <init> : ()V
    //   201: ldc '(compiling '
    //   203: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   206: aload #11
    //   208: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   211: bipush #41
    //   213: invokevirtual append : (C)Ljava/lang/StringBuilder;
    //   216: invokevirtual toString : ()Ljava/lang/String;
    //   219: invokevirtual println : (Ljava/lang/String;)V
    //   222: aload #9
    //   224: getstatic java/lang/System.err : Ljava/io/PrintStream;
    //   227: bipush #20
    //   229: invokevirtual checkErrors : (Ljava/io/PrintStream;I)Z
    //   232: ifeq -> 239
    //   235: iconst_1
    //   236: invokestatic exit : (I)V
    //   239: iload_3
    //   240: iconst_1
    //   241: iadd
    //   242: istore_3
    //   243: goto -> 32
    //   246: astore #12
    //   248: aload #6
    //   250: astore #5
    //   252: getstatic java/lang/System.err : Ljava/io/PrintStream;
    //   255: aload #12
    //   257: invokevirtual println : (Ljava/lang/Object;)V
    //   260: aload #6
    //   262: astore #5
    //   264: iconst_m1
    //   265: invokestatic exit : (I)V
    //   268: iload_1
    //   269: istore_3
    //   270: iload_3
    //   271: iload_2
    //   272: if_icmpge -> 464
    //   275: aload_0
    //   276: iload_3
    //   277: aaload
    //   278: astore #5
    //   280: aload #7
    //   282: iload_3
    //   283: iload_1
    //   284: isub
    //   285: aaload
    //   286: astore #6
    //   288: getstatic java/lang/System.err : Ljava/io/PrintStream;
    //   291: new java/lang/StringBuilder
    //   294: dup
    //   295: invokespecial <init> : ()V
    //   298: ldc '(compiling '
    //   300: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   303: aload #5
    //   305: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   308: ldc ' to '
    //   310: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   313: aload #6
    //   315: getfield mainClass : Lgnu/bytecode/ClassType;
    //   318: invokevirtual getName : ()Ljava/lang/String;
    //   321: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   324: bipush #41
    //   326: invokevirtual append : (C)Ljava/lang/StringBuilder;
    //   329: invokevirtual toString : ()Ljava/lang/String;
    //   332: invokevirtual println : (Ljava/lang/String;)V
    //   335: aload #8
    //   337: iload_3
    //   338: iload_1
    //   339: isub
    //   340: aaload
    //   341: bipush #14
    //   343: invokevirtual loadByStages : (I)V
    //   346: aload #9
    //   348: invokevirtual seenErrors : ()Z
    //   351: istore #4
    //   353: aload #9
    //   355: getstatic java/lang/System.err : Ljava/io/PrintStream;
    //   358: bipush #50
    //   360: invokevirtual checkErrors : (Ljava/io/PrintStream;I)Z
    //   363: pop
    //   364: iload #4
    //   366: ifeq -> 373
    //   369: iconst_m1
    //   370: invokestatic exit : (I)V
    //   373: aload #7
    //   375: iload_3
    //   376: iload_1
    //   377: isub
    //   378: aload #6
    //   380: aastore
    //   381: aload #9
    //   383: invokevirtual seenErrors : ()Z
    //   386: istore #4
    //   388: aload #9
    //   390: getstatic java/lang/System.err : Ljava/io/PrintStream;
    //   393: bipush #50
    //   395: invokevirtual checkErrors : (Ljava/io/PrintStream;I)Z
    //   398: pop
    //   399: iload #4
    //   401: ifeq -> 408
    //   404: iconst_m1
    //   405: invokestatic exit : (I)V
    //   408: iload_3
    //   409: iconst_1
    //   410: iadd
    //   411: istore_3
    //   412: goto -> 270
    //   415: astore #6
    //   417: aload #6
    //   419: instanceof gnu/text/SyntaxException
    //   422: ifeq -> 438
    //   425: aload #6
    //   427: checkcast gnu/text/SyntaxException
    //   430: invokevirtual getMessages : ()Lgnu/text/SourceMessages;
    //   433: aload #9
    //   435: if_acmpeq -> 183
    //   438: aload #6
    //   440: aload #5
    //   442: aload #11
    //   444: invokestatic internalError : (Ljava/lang/Throwable;Lgnu/expr/Compilation;Ljava/lang/Object;)V
    //   447: goto -> 183
    //   450: astore #10
    //   452: aload #10
    //   454: aload #6
    //   456: aload #5
    //   458: invokestatic internalError : (Ljava/lang/Throwable;Lgnu/expr/Compilation;Ljava/lang/Object;)V
    //   461: goto -> 408
    //   464: return
    // Exception table:
    //   from	to	target	type
    //   59	66	246	java/io/FileNotFoundException
    //   59	66	415	java/lang/Throwable
    //   70	84	415	java/lang/Throwable
    //   88	94	415	java/lang/Throwable
    //   98	113	415	java/lang/Throwable
    //   117	124	415	java/lang/Throwable
    //   128	135	415	java/lang/Throwable
    //   139	147	415	java/lang/Throwable
    //   151	158	415	java/lang/Throwable
    //   162	175	415	java/lang/Throwable
    //   252	260	415	java/lang/Throwable
    //   264	268	415	java/lang/Throwable
    //   288	364	450	java/lang/Throwable
    //   369	373	450	java/lang/Throwable
    //   381	399	450	java/lang/Throwable
    //   404	408	450	java/lang/Throwable
  }
  
  public static void getLanguage() {
    if (previousLanguage == null) {
      previousLanguage = Language.getInstance(null);
      Language.setDefaults(previousLanguage);
    } 
  }
  
  public static void getLanguageFromFilenameExtension(String paramString) {
    if (previousLanguage == null) {
      previousLanguage = Language.getInstanceFromFilenameExtension(paramString);
      if (previousLanguage != null) {
        Language.setDefaults(previousLanguage);
        return;
      } 
    } 
    getLanguage();
  }
  
  static void internalError(Throwable paramThrowable, Compilation paramCompilation, Object paramObject) {
    StringBuffer stringBuffer = new StringBuffer();
    if (paramCompilation != null) {
      String str = paramCompilation.getFileName();
      int i = paramCompilation.getLineNumber();
      if (str != null && i > 0) {
        stringBuffer.append(str);
        stringBuffer.append(':');
        stringBuffer.append(i);
        stringBuffer.append(": ");
      } 
    } 
    stringBuffer.append("internal error while compiling ");
    stringBuffer.append(paramObject);
    System.err.println(stringBuffer.toString());
    paramThrowable.printStackTrace(System.err);
    System.exit(-1);
  }
  
  public static void main(String[] paramArrayOfString) {
    try {
      int i = processArgs(paramArrayOfString, 0, paramArrayOfString.length);
      if (i < 0)
        return; 
      if (i < paramArrayOfString.length) {
        String str = paramArrayOfString[i];
        getLanguageFromFilenameExtension(str);
        setArgs(paramArrayOfString, i + 1);
        checkInitFile();
        Shell.runFileOrClass(str, false, 0);
      } else {
        getLanguage();
        setArgs(paramArrayOfString, i);
        checkInitFile();
        if (shouldUseGuiConsole()) {
          startGuiConsole();
        } else if (!Shell.run(Language.getDefaultLanguage(), Environment.getCurrent())) {
          System.exit(-1);
        } 
      } 
      return;
    } finally {
      if (!shutdownRegistered)
        OutPort.runCleanups(); 
      ModuleBody.exitDecrement();
    } 
  }
  
  public static void printOption(PrintStream paramPrintStream, String paramString1, String paramString2) {
    paramPrintStream.print(" ");
    paramPrintStream.print(paramString1);
    int j = paramString1.length();
    for (int i = 0; i < 30 - j + 1; i++)
      paramPrintStream.print(" "); 
    paramPrintStream.print(" ");
    paramPrintStream.println(paramString2);
  }
  
  public static void printOptions(PrintStream paramPrintStream) {
    paramPrintStream.println("Usage: [java kawa.repl | kawa] [options ...]");
    paramPrintStream.println();
    paramPrintStream.println(" Generic options:");
    printOption(paramPrintStream, "--help", "Show help about options");
    printOption(paramPrintStream, "--author", "Show author information");
    printOption(paramPrintStream, "--version", "Show version information");
    paramPrintStream.println();
    paramPrintStream.println(" Options");
    printOption(paramPrintStream, "-e <expr>", "Evaluate expression <expr>");
    printOption(paramPrintStream, "-c <expr>", "Same as -e, but make sure ~/.kawarc.scm is run first");
    printOption(paramPrintStream, "-f <filename>", "File to interpret");
    printOption(paramPrintStream, "-s| --", "Start reading commands interactively from console");
    printOption(paramPrintStream, "-w", "Launch the interpreter in a GUI window");
    printOption(paramPrintStream, "--server <port>", "Start a server accepting telnet connections on <port>");
    printOption(paramPrintStream, "--debug-dump-zip", "Compiled interactive expressions to a zip archive");
    printOption(paramPrintStream, "--debug-print-expr", "Print generated internal expressions");
    printOption(paramPrintStream, "--debug-print-final-expr", "Print expression after any optimizations");
    printOption(paramPrintStream, "--debug-error-prints-stack-trace", "Print stack trace with errors");
    printOption(paramPrintStream, "--debug-warning-prints-stack-trace", "Print stack trace with warnings");
    printOption(paramPrintStream, "--[no-]full-tailcalls", "(Don't) use full tail-calls");
    printOption(paramPrintStream, "-C <filename> ...", "Compile named files to Java class files");
    printOption(paramPrintStream, "--output-format <format>", "Use <format> when printing top-level output");
    printOption(paramPrintStream, "--<language>", "Select source language, one of:");
    String[][] arrayOfString = Language.getLanguages();
    int i;
    for (i = 0; i < arrayOfString.length; i++) {
      paramPrintStream.print("   ");
      String[] arrayOfString1 = arrayOfString[i];
      int k = arrayOfString1.length;
      for (int j = 0; j < k - 1; j++)
        paramPrintStream.print(arrayOfString1[j] + " "); 
      if (i == 0)
        paramPrintStream.print("[default]"); 
      paramPrintStream.println();
    } 
    paramPrintStream.println(" Compilation options, must be specified before -C");
    printOption(paramPrintStream, "-d <dirname>", "Directory to place .class files in");
    printOption(paramPrintStream, "-P <prefix>", "Prefix to prepand to class names");
    printOption(paramPrintStream, "-T <topname>", "name to give to top-level class");
    printOption(paramPrintStream, "--main", "Generate an application, with a main method");
    printOption(paramPrintStream, "--applet", "Generate an applet");
    printOption(paramPrintStream, "--servlet", "Generate a servlet");
    printOption(paramPrintStream, "--module-static", "Top-level definitions are by default static");
    ArrayList<String> arrayList = Compilation.options.keys();
    for (i = 0; i < arrayList.size(); i++) {
      String str = arrayList.get(i);
      printOption(paramPrintStream, "--" + str, Compilation.options.getDoc(str));
    } 
    paramPrintStream.println();
    paramPrintStream.println("For more information go to:  http://www.gnu.org/software/kawa/");
  }
  
  public static int processArgs(String[] paramArrayOfString, int paramInt1, int paramInt2) {
    boolean bool = false;
    int i = paramInt1;
    while (true) {
      boolean bool1;
      if (i < paramInt2) {
        IOException iOException1 = iOException[i];
        if (iOException1.equals("-c") || iOException1.equals("-e")) {
          paramInt1 = i + 1;
          if (paramInt1 == paramInt2)
            bad_option((String)iOException1); 
          getLanguage();
          setArgs((String[])iOException, paramInt1 + 1);
          if (iOException1.equals("-c"))
            checkInitFile(); 
          Language language = Language.getDefaultLanguage();
          SourceMessages sourceMessages = new SourceMessages();
          Throwable throwable = Shell.run(language, Environment.getCurrent(), (InPort)new CharArrayInPort((String)iOException[paramInt1]), OutPort.outDefault(), (OutPort)null, sourceMessages);
          if (throwable != null) {
            Shell.printError(throwable, sourceMessages, OutPort.errDefault());
            System.exit(-1);
          } 
          bool1 = true;
        } else if (iOException1.equals("-f")) {
          paramInt1 = i + 1;
          if (paramInt1 == paramInt2)
            bad_option((String)iOException1); 
          IOException iOException2 = iOException[paramInt1];
          getLanguageFromFilenameExtension((String)iOException2);
          setArgs((String[])iOException, paramInt1 + 1);
          checkInitFile();
          if (!Shell.runFileOrClass((String)iOException2, true, 0))
            System.exit(-1); 
          bool1 = true;
        } else {
          StringBuffer stringBuffer;
          if (iOException1.startsWith("--script")) {
            String str = iOException1.substring(8);
            i++;
            bool = false;
            paramInt1 = bool;
            bool1 = i;
            if (str.length() > 0)
              try {
                paramInt1 = Integer.parseInt(str);
                bool1 = i;
              } catch (Throwable throwable1) {
                bool1 = paramInt2;
                paramInt1 = bool;
              }  
            if (bool1 == paramInt2)
              bad_option((String)iOException1); 
            IOException iOException2 = iOException[bool1];
            getLanguageFromFilenameExtension((String)iOException2);
            setArgs((String[])iOException, bool1 + 1);
            checkInitFile();
            if (!Shell.runFileOrClass((String)iOException2, true, paramInt1))
              System.exit(-1); 
            return -1;
          } 
          if (iOException1.equals("\\")) {
            if (++i == paramInt2)
              bad_option((String)iOException1); 
            IOException iOException2 = iOException[i];
            SourceMessages sourceMessages = new SourceMessages();
            try {
              BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream((String)iOException2));
              paramInt2 = bufferedInputStream.read();
              if (paramInt2 == 35) {
                stringBuffer = new StringBuffer(100);
                Vector vector = new Vector(10);
                bool1 = false;
                while (true) {
                  paramInt1 = bool1;
                  if (paramInt2 != 10) {
                    paramInt1 = bool1;
                    if (paramInt2 != 13) {
                      paramInt1 = bool1;
                      if (paramInt2 >= 0) {
                        paramInt2 = bufferedInputStream.read();
                        continue;
                      } 
                    } 
                  } 
                  break;
                } 
                while (true) {
                  bool1 = bufferedInputStream.read();
                  paramInt1 = 0;
                } 
              } 
              continue;
            } catch (Throwable throwable) {
              Shell.printError(throwable, sourceMessages, OutPort.errDefault());
              System.exit(1);
              continue;
            } 
          } 
          if (stringBuffer.equals("-s") || stringBuffer.equals("--")) {
            getLanguage();
            setArgs((String[])throwable, i + 1);
            checkInitFile();
            Shell.run(Language.getDefaultLanguage(), Environment.getCurrent());
            return -1;
          } 
          if (stringBuffer.equals("-w")) {
            paramInt1 = i + 1;
            getLanguage();
            setArgs((String[])throwable, paramInt1);
            checkInitFile();
            startGuiConsole();
            bool1 = true;
          } else if (stringBuffer.equals("-d")) {
            paramInt1 = i + 1;
            if (paramInt1 == paramInt2)
              bad_option((String)stringBuffer); 
            ModuleManager.getInstance().setCompilationDirectory((String)throwable[paramInt1]);
            bool1 = bool;
          } else if (stringBuffer.equals("--target") || stringBuffer.equals("target")) {
            paramInt1 = i + 1;
            if (paramInt1 == paramInt2)
              bad_option((String)stringBuffer); 
            Throwable throwable1 = throwable[paramInt1];
            if (throwable1.equals("7"))
              Compilation.defaultClassFileVersion = 3342336; 
            if (throwable1.equals("6") || throwable1.equals("1.6")) {
              Compilation.defaultClassFileVersion = 3276800;
              bool1 = bool;
            } else if (throwable1.equals("5") || throwable1.equals("1.5")) {
              Compilation.defaultClassFileVersion = 3211264;
              bool1 = bool;
            } else if (throwable1.equals("1.4")) {
              Compilation.defaultClassFileVersion = 3145728;
              bool1 = bool;
            } else if (throwable1.equals("1.3")) {
              Compilation.defaultClassFileVersion = 3080192;
              bool1 = bool;
            } else if (throwable1.equals("1.2")) {
              Compilation.defaultClassFileVersion = 3014656;
              bool1 = bool;
            } else if (throwable1.equals("1.1")) {
              Compilation.defaultClassFileVersion = 2949123;
              bool1 = bool;
            } else {
              bad_option((String)throwable1);
              bool1 = bool;
            } 
          } else if (stringBuffer.equals("-P")) {
            paramInt1 = i + 1;
            if (paramInt1 == paramInt2)
              bad_option((String)stringBuffer); 
            Compilation.classPrefixDefault = (String)throwable[paramInt1];
            bool1 = bool;
          } else if (stringBuffer.equals("-T")) {
            paramInt1 = i + 1;
            if (paramInt1 == paramInt2)
              bad_option((String)stringBuffer); 
            compilationTopname = (String)throwable[paramInt1];
            bool1 = bool;
          } else {
            if (stringBuffer.equals("-C")) {
              paramInt1 = i + 1;
              if (paramInt1 == paramInt2)
                bad_option((String)stringBuffer); 
              compileFiles((String[])throwable, paramInt1, paramInt2);
              return -1;
            } 
            if (stringBuffer.equals("--output-format") || stringBuffer.equals("--format")) {
              paramInt1 = i + 1;
              if (paramInt1 == paramInt2)
                bad_option((String)stringBuffer); 
              Shell.setDefaultFormat((String)throwable[paramInt1]);
              bool1 = bool;
            } else {
              if (stringBuffer.equals("--connect")) {
                if (++i == paramInt2)
                  bad_option((String)stringBuffer); 
                if (throwable[i].equals("-")) {
                  paramInt1 = 0;
                } else {
                  try {
                    paramInt1 = Integer.parseInt((String)throwable[i]);
                  } catch (NumberFormatException numberFormatException) {
                    bad_option("--connect port#");
                    paramInt1 = -1;
                  } 
                } 
                continue;
              } 
              if (stringBuffer.equals("--server")) {
                getLanguage();
                paramInt1 = i + 1;
                if (paramInt1 == paramInt2)
                  bad_option((String)stringBuffer); 
                if (throwable[paramInt1].equals("-")) {
                  paramInt1 = 0;
                } else {
                  try {
                    paramInt1 = Integer.parseInt((String)throwable[paramInt1]);
                  } catch (NumberFormatException numberFormatException) {
                    bad_option("--server port#");
                    paramInt1 = -1;
                  } 
                } 
                try {
                  ServerSocket serverSocket = new ServerSocket(paramInt1);
                  paramInt1 = serverSocket.getLocalPort();
                  System.err.println("Listening on port " + paramInt1);
                  while (true) {
                    System.err.print("waiting ... ");
                    System.err.flush();
                    Socket socket = serverSocket.accept();
                    System.err.println("got connection from " + socket.getInetAddress() + " port:" + socket.getPort());
                    TelnetRepl.serve(Language.getDefaultLanguage(), socket);
                  } 
                } catch (IOException iOException2) {
                  throw new Error(iOException2.toString());
                } 
              } 
              if (stringBuffer.equals("--http-auto-handler")) {
                paramInt1 = i + 2;
                if (paramInt1 >= paramInt2)
                  bad_option((String)stringBuffer); 
                System.err.println("kawa: HttpServer classes not found");
                System.exit(-1);
                bool1 = bool;
              } else if (stringBuffer.equals("--http-start")) {
                paramInt1 = i + 1;
                if (paramInt1 >= paramInt2)
                  bad_option("missing httpd port argument"); 
                System.err.println("kawa: HttpServer classes not found");
                System.exit(-1);
                bool1 = bool;
              } else if (stringBuffer.equals("--main")) {
                Compilation.generateMainDefault = true;
                bool1 = bool;
                paramInt1 = i;
              } else if (stringBuffer.equals("--applet")) {
                defaultParseOptions |= 0x10;
                bool1 = bool;
                paramInt1 = i;
              } else if (stringBuffer.equals("--servlet")) {
                defaultParseOptions |= 0x20;
                HttpRequestContext.importServletDefinitions = 2;
                bool1 = bool;
                paramInt1 = i;
              } else if (stringBuffer.equals("--debug-dump-zip")) {
                ModuleExp.dumpZipPrefix = "kawa-zip-dump-";
                bool1 = bool;
                paramInt1 = i;
              } else if (stringBuffer.equals("--debug-print-expr")) {
                Compilation.debugPrintExpr = true;
                bool1 = bool;
                paramInt1 = i;
              } else if (stringBuffer.equals("--debug-print-final-expr")) {
                Compilation.debugPrintFinalExpr = true;
                bool1 = bool;
                paramInt1 = i;
              } else if (stringBuffer.equals("--debug-error-prints-stack-trace")) {
                SourceMessages.debugStackTraceOnError = true;
                bool1 = bool;
                paramInt1 = i;
              } else if (stringBuffer.equals("--debug-warning-prints-stack-trace")) {
                SourceMessages.debugStackTraceOnWarning = true;
                bool1 = bool;
                paramInt1 = i;
              } else if (stringBuffer.equals("--module-nonstatic") || stringBuffer.equals("--no-module-static")) {
                Compilation.moduleStatic = -1;
                bool1 = bool;
                paramInt1 = i;
              } else if (stringBuffer.equals("--module-static")) {
                Compilation.moduleStatic = 1;
                bool1 = bool;
                paramInt1 = i;
              } else if (stringBuffer.equals("--module-static-run")) {
                Compilation.moduleStatic = 2;
                bool1 = bool;
                paramInt1 = i;
              } else if (stringBuffer.equals("--no-inline") || stringBuffer.equals("--inline=none")) {
                Compilation.inlineOk = false;
                bool1 = bool;
                paramInt1 = i;
              } else if (stringBuffer.equals("--no-console")) {
                noConsole = true;
                bool1 = bool;
                paramInt1 = i;
              } else if (stringBuffer.equals("--inline")) {
                Compilation.inlineOk = true;
                bool1 = bool;
                paramInt1 = i;
              } else if (stringBuffer.equals("--cps")) {
                Compilation.defaultCallConvention = 4;
                bool1 = bool;
                paramInt1 = i;
              } else if (stringBuffer.equals("--full-tailcalls")) {
                Compilation.defaultCallConvention = 3;
                bool1 = bool;
                paramInt1 = i;
              } else if (stringBuffer.equals("--no-full-tailcalls")) {
                Compilation.defaultCallConvention = 1;
                bool1 = bool;
                paramInt1 = i;
              } else if (stringBuffer.equals("--pedantic")) {
                Language.requirePedantic = true;
                bool1 = bool;
                paramInt1 = i;
              } else if (stringBuffer.equals("--help")) {
                printOptions(System.out);
                System.exit(0);
                bool1 = bool;
                paramInt1 = i;
              } else if (stringBuffer.equals("--author")) {
                System.out.println("Per Bothner <per@bothner.com>");
                System.exit(0);
                bool1 = bool;
                paramInt1 = i;
              } else if (stringBuffer.equals("--version")) {
                System.out.print("Kawa ");
                System.out.print(Version.getVersion());
                System.out.println();
                System.out.println("Copyright (C) 2009 Per Bothner");
                bool1 = true;
                paramInt1 = i;
              } else if (stringBuffer.length() > 0 && stringBuffer.charAt(0) == '-') {
                String str;
                StringBuffer stringBuffer2 = stringBuffer;
                StringBuffer stringBuffer1 = stringBuffer2;
                if (stringBuffer2.length() > 2) {
                  stringBuffer1 = stringBuffer2;
                  if (stringBuffer2.charAt(0) == '-') {
                    if (stringBuffer2.charAt(1) == '-') {
                      paramInt1 = 2;
                    } else {
                      paramInt1 = 1;
                    } 
                    str = stringBuffer2.substring(paramInt1);
                  } 
                } 
                Language language = Language.getInstance(str);
                if (language != null) {
                  if (previousLanguage == null) {
                    Language.setDefaults(language);
                  } else {
                    Language.setCurrentLanguage(language);
                  } 
                  previousLanguage = language;
                  bool1 = bool;
                  paramInt1 = i;
                } else {
                  boolean bool2;
                  paramInt1 = str.indexOf("=");
                  if (paramInt1 < 0) {
                    language = null;
                  } else {
                    str1 = str.substring(paramInt1 + 1);
                    str = str.substring(0, paramInt1);
                  } 
                  if (str.startsWith("no-") && str.length() > 3) {
                    bool2 = true;
                  } else {
                    bool2 = false;
                  } 
                  String str3 = str;
                  String str2 = str1;
                  if (str1 == null) {
                    str3 = str;
                    str2 = str1;
                    if (bool2) {
                      str2 = "no";
                      str3 = str.substring(3);
                    } 
                  } 
                  String str1 = Compilation.options.set(str3, str2);
                  bool1 = bool;
                  paramInt1 = i;
                  if (str1 != null) {
                    str = str1;
                    if (bool2) {
                      str = str1;
                      if (str1 == "unknown option name")
                        str = "both '--no-' prefix and '=" + str2 + "' specified"; 
                    } 
                    if (str == "unknown option name") {
                      bad_option((String)stringBuffer);
                      bool1 = bool;
                      paramInt1 = i;
                    } else {
                      System.err.println("kawa: bad option '" + stringBuffer + "': " + str);
                      System.exit(-1);
                      bool1 = bool;
                      paramInt1 = i;
                    } 
                  } 
                } 
              } else {
                bool1 = bool;
                paramInt1 = i;
                if (!ApplicationMainSupport.processSetProperty((String)stringBuffer))
                  break; 
              } 
            } 
          } 
        } 
      } else {
        break;
      } 
      i = paramInt1 + 1;
      bool = bool1;
      try {
        Telnet telnet = new Telnet(new Socket(InetAddress.getByName(null), paramInt1), true);
        TelnetInputStream telnetInputStream = telnet.getInputStream();
        PrintStream printStream = new PrintStream(telnet.getOutputStream(), true);
        System.setIn(telnetInputStream);
        System.setOut(printStream);
        System.setErr(printStream);
        boolean bool1 = bool;
        paramInt1 = i;
        i = paramInt1 + 1;
        bool = bool1;
      } catch (IOException iOException) {
        iOException.printStackTrace(System.err);
        throw new Error(iOException.toString());
      } 
    } 
    return bool ? -1 : i;
  }
  
  public static void setArgs(String[] paramArrayOfString, int paramInt) {
    ApplicationMainSupport.setArgs(paramArrayOfString, paramInt);
  }
  
  public static boolean shouldUseGuiConsole() {
    if (!noConsole)
      try {
        Object object = Class.forName("java.lang.System").getMethod("console", new Class[0]).invoke(new Object[0], new Object[0]);
        return !(object != null);
      } catch (Throwable throwable) {
        return false;
      }  
    return true;
  }
  
  private static void startGuiConsole() {
    try {
      Class.forName("kawa.GuiConsole").newInstance();
      return;
    } catch (Exception exception) {
      System.err.println("failed to create Kawa window: " + exception);
      System.exit(-1);
      return;
    } 
  }
  
  public Object apply0() {
    Shell.run(this.language, Environment.getCurrent());
    return Values.empty;
  }
  
  public Object apply1(Object paramObject) {
    Shell.run(this.language, (Environment)paramObject);
    return Values.empty;
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/kawa/repl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */