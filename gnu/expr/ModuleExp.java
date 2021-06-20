package gnu.expr;

import gnu.bytecode.ArrayClassLoader;
import gnu.bytecode.ClassType;
import gnu.mapping.CallContext;
import gnu.mapping.Environment;
import gnu.mapping.OutPort;
import gnu.mapping.WrappedException;
import gnu.text.Path;
import gnu.text.SourceMessages;
import gnu.text.SyntaxException;
import java.io.Externalizable;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.net.URL;
import java.util.zip.CRC32;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class ModuleExp extends LambdaExp implements Externalizable {
  public static final int EXPORT_SPECIFIED = 16384;
  
  public static final int IMMEDIATE = 1048576;
  
  public static final int LAZY_DECLARATIONS = 524288;
  
  public static final int NONSTATIC_SPECIFIED = 65536;
  
  public static final int STATIC_RUN_SPECIFIED = 262144;
  
  public static final int STATIC_SPECIFIED = 32768;
  
  public static final int SUPERTYPE_SPECIFIED = 131072;
  
  public static boolean alwaysCompile;
  
  public static boolean compilerAvailable = true;
  
  public static String dumpZipPrefix;
  
  public static int interactiveCounter;
  
  static int lastZipCounter;
  
  public static boolean neverCompile;
  
  ModuleInfo info;
  
  ClassType[] interfaces;
  
  ClassType superType;
  
  static {
    alwaysCompile = compilerAvailable;
    neverCompile = false;
  }
  
  public static final boolean evalModule(Environment paramEnvironment, CallContext paramCallContext, Compilation paramCompilation, URL paramURL, OutPort paramOutPort) throws Throwable {
    ModuleExp moduleExp = paramCompilation.getModule();
    Language language = paramCompilation.getLanguage();
    Object object = evalModule1(paramEnvironment, paramCompilation, paramURL, paramOutPort);
    if (object == null)
      return false; 
    evalModule2(paramEnvironment, paramCallContext, language, moduleExp, object);
    return true;
  }
  
  public static final Object evalModule1(Environment paramEnvironment, Compilation paramCompilation, URL paramURL, OutPort paramOutPort) throws SyntaxException {
    // Byte code:
    //   0: aload_1
    //   1: invokevirtual getModule : ()Lgnu/expr/ModuleExp;
    //   4: astore #12
    //   6: aload #12
    //   8: aload_1
    //   9: getfield minfo : Lgnu/expr/ModuleInfo;
    //   12: putfield info : Lgnu/expr/ModuleInfo;
    //   15: aload_0
    //   16: invokestatic setSaveCurrent : (Lgnu/mapping/Environment;)Lgnu/mapping/Environment;
    //   19: astore #10
    //   21: aload_1
    //   22: invokestatic setSaveCurrent : (Lgnu/expr/Compilation;)Lgnu/expr/Compilation;
    //   25: astore #11
    //   27: aload_1
    //   28: invokevirtual getMessages : ()Lgnu/text/SourceMessages;
    //   31: astore #13
    //   33: aconst_null
    //   34: astore #8
    //   36: aconst_null
    //   37: astore #6
    //   39: aconst_null
    //   40: astore #9
    //   42: getstatic gnu/expr/ModuleExp.alwaysCompile : Z
    //   45: ifeq -> 64
    //   48: getstatic gnu/expr/ModuleExp.neverCompile : Z
    //   51: ifeq -> 64
    //   54: new java/lang/RuntimeException
    //   57: dup
    //   58: ldc 'alwaysCompile and neverCompile are both true!'
    //   60: invokespecial <init> : (Ljava/lang/String;)V
    //   63: athrow
    //   64: getstatic gnu/expr/ModuleExp.neverCompile : Z
    //   67: ifeq -> 75
    //   70: aload_1
    //   71: iconst_0
    //   72: putfield mustCompile : Z
    //   75: aload #8
    //   77: astore #5
    //   79: aload #9
    //   81: astore_0
    //   82: aload_1
    //   83: bipush #6
    //   85: invokevirtual process : (I)V
    //   88: aload #8
    //   90: astore #5
    //   92: aload #9
    //   94: astore_0
    //   95: aload_1
    //   96: getfield minfo : Lgnu/expr/ModuleInfo;
    //   99: bipush #8
    //   101: invokevirtual loadByStages : (I)V
    //   104: aload_3
    //   105: ifnull -> 156
    //   108: aload #8
    //   110: astore #5
    //   112: aload #9
    //   114: astore_0
    //   115: aload #13
    //   117: aload_3
    //   118: bipush #20
    //   120: invokevirtual checkErrors : (Ljava/io/PrintWriter;I)Z
    //   123: istore #4
    //   125: iload #4
    //   127: ifeq -> 171
    //   130: aload #10
    //   132: invokestatic restoreCurrent : (Lgnu/mapping/Environment;)V
    //   135: aload #11
    //   137: invokestatic restoreCurrent : (Lgnu/expr/Compilation;)V
    //   140: iconst_0
    //   141: ifeq -> 152
    //   144: new java/lang/NullPointerException
    //   147: dup
    //   148: invokespecial <init> : ()V
    //   151: athrow
    //   152: aconst_null
    //   153: astore_0
    //   154: aload_0
    //   155: areturn
    //   156: aload #8
    //   158: astore #5
    //   160: aload #9
    //   162: astore_0
    //   163: aload #13
    //   165: invokevirtual seenErrors : ()Z
    //   168: ifne -> 130
    //   171: aload #8
    //   173: astore #5
    //   175: aload #9
    //   177: astore_0
    //   178: aload_1
    //   179: getfield mustCompile : Z
    //   182: ifne -> 313
    //   185: aload #8
    //   187: astore #5
    //   189: aload #9
    //   191: astore_0
    //   192: getstatic gnu/expr/Compilation.debugPrintFinalExpr : Z
    //   195: ifeq -> 278
    //   198: aload_3
    //   199: ifnull -> 278
    //   202: aload #8
    //   204: astore #5
    //   206: aload #9
    //   208: astore_0
    //   209: aload_3
    //   210: new java/lang/StringBuilder
    //   213: dup
    //   214: invokespecial <init> : ()V
    //   217: ldc '[Evaluating final module "'
    //   219: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   222: aload #12
    //   224: invokevirtual getName : ()Ljava/lang/String;
    //   227: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   230: ldc '":'
    //   232: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   235: invokevirtual toString : ()Ljava/lang/String;
    //   238: invokevirtual println : (Ljava/lang/String;)V
    //   241: aload #8
    //   243: astore #5
    //   245: aload #9
    //   247: astore_0
    //   248: aload #12
    //   250: aload_3
    //   251: invokevirtual print : (Lgnu/mapping/OutPort;)V
    //   254: aload #8
    //   256: astore #5
    //   258: aload #9
    //   260: astore_0
    //   261: aload_3
    //   262: bipush #93
    //   264: invokevirtual println : (C)V
    //   267: aload #8
    //   269: astore #5
    //   271: aload #9
    //   273: astore_0
    //   274: aload_3
    //   275: invokevirtual flush : ()V
    //   278: aload #8
    //   280: astore #5
    //   282: aload #9
    //   284: astore_0
    //   285: getstatic java/lang/Boolean.TRUE : Ljava/lang/Boolean;
    //   288: astore_1
    //   289: aload #10
    //   291: invokestatic restoreCurrent : (Lgnu/mapping/Environment;)V
    //   294: aload #11
    //   296: invokestatic restoreCurrent : (Lgnu/expr/Compilation;)V
    //   299: aload_1
    //   300: astore_0
    //   301: iconst_0
    //   302: ifeq -> 154
    //   305: new java/lang/NullPointerException
    //   308: dup
    //   309: invokespecial <init> : ()V
    //   312: athrow
    //   313: aload #8
    //   315: astore #5
    //   317: aload #9
    //   319: astore_0
    //   320: aload_1
    //   321: aload_2
    //   322: invokestatic evalToClass : (Lgnu/expr/Compilation;Ljava/net/URL;)Ljava/lang/Class;
    //   325: astore #7
    //   327: aload #7
    //   329: ifnonnull -> 356
    //   332: aload #10
    //   334: invokestatic restoreCurrent : (Lgnu/mapping/Environment;)V
    //   337: aload #11
    //   339: invokestatic restoreCurrent : (Lgnu/expr/Compilation;)V
    //   342: iconst_0
    //   343: ifeq -> 354
    //   346: new java/lang/NullPointerException
    //   349: dup
    //   350: invokespecial <init> : ()V
    //   353: athrow
    //   354: aconst_null
    //   355: areturn
    //   356: aload #6
    //   358: astore_1
    //   359: aload #8
    //   361: astore #5
    //   363: aload #9
    //   365: astore_0
    //   366: invokestatic currentThread : ()Ljava/lang/Thread;
    //   369: astore_2
    //   370: aload #6
    //   372: astore_1
    //   373: aload #8
    //   375: astore #5
    //   377: aload_2
    //   378: astore_0
    //   379: aload_2
    //   380: invokevirtual getContextClassLoader : ()Ljava/lang/ClassLoader;
    //   383: astore #6
    //   385: aload #6
    //   387: astore_1
    //   388: aload #6
    //   390: astore #5
    //   392: aload_2
    //   393: astore_0
    //   394: aload_2
    //   395: aload #7
    //   397: invokevirtual getClassLoader : ()Ljava/lang/ClassLoader;
    //   400: invokevirtual setContextClassLoader : (Ljava/lang/ClassLoader;)V
    //   403: aload #6
    //   405: astore_1
    //   406: aload_1
    //   407: astore #5
    //   409: aload_2
    //   410: astore_0
    //   411: aload #12
    //   413: aconst_null
    //   414: putfield body : Lgnu/expr/Expression;
    //   417: aload_1
    //   418: astore #5
    //   420: aload_2
    //   421: astore_0
    //   422: aload #12
    //   424: aconst_null
    //   425: putfield thisVariable : Lgnu/bytecode/Variable;
    //   428: aload_3
    //   429: ifnull -> 482
    //   432: aload_1
    //   433: astore #5
    //   435: aload_2
    //   436: astore_0
    //   437: aload #13
    //   439: aload_3
    //   440: bipush #20
    //   442: invokevirtual checkErrors : (Ljava/io/PrintWriter;I)Z
    //   445: ifeq -> 499
    //   448: iconst_0
    //   449: invokestatic valueOf : (Z)Ljava/lang/Boolean;
    //   452: astore_3
    //   453: aload #10
    //   455: invokestatic restoreCurrent : (Lgnu/mapping/Environment;)V
    //   458: aload #11
    //   460: invokestatic restoreCurrent : (Lgnu/expr/Compilation;)V
    //   463: aload_3
    //   464: astore_0
    //   465: aload_2
    //   466: ifnull -> 154
    //   469: aload_2
    //   470: aload_1
    //   471: invokevirtual setContextClassLoader : (Ljava/lang/ClassLoader;)V
    //   474: aload_3
    //   475: areturn
    //   476: astore_0
    //   477: aconst_null
    //   478: astore_2
    //   479: goto -> 406
    //   482: aload_1
    //   483: astore #5
    //   485: aload_2
    //   486: astore_0
    //   487: aload #13
    //   489: invokevirtual seenErrors : ()Z
    //   492: istore #4
    //   494: iload #4
    //   496: ifne -> 448
    //   499: aload #10
    //   501: invokestatic restoreCurrent : (Lgnu/mapping/Environment;)V
    //   504: aload #11
    //   506: invokestatic restoreCurrent : (Lgnu/expr/Compilation;)V
    //   509: aload #7
    //   511: astore_0
    //   512: aload_2
    //   513: ifnull -> 154
    //   516: aload_2
    //   517: aload_1
    //   518: invokevirtual setContextClassLoader : (Ljava/lang/ClassLoader;)V
    //   521: aload #7
    //   523: areturn
    //   524: astore_1
    //   525: aload #10
    //   527: invokestatic restoreCurrent : (Lgnu/mapping/Environment;)V
    //   530: aload #11
    //   532: invokestatic restoreCurrent : (Lgnu/expr/Compilation;)V
    //   535: aload_0
    //   536: ifnull -> 545
    //   539: aload_0
    //   540: aload #5
    //   542: invokevirtual setContextClassLoader : (Ljava/lang/ClassLoader;)V
    //   545: aload_1
    //   546: athrow
    // Exception table:
    //   from	to	target	type
    //   82	88	524	finally
    //   95	104	524	finally
    //   115	125	524	finally
    //   163	171	524	finally
    //   178	185	524	finally
    //   192	198	524	finally
    //   209	241	524	finally
    //   248	254	524	finally
    //   261	267	524	finally
    //   274	278	524	finally
    //   285	289	524	finally
    //   320	327	524	finally
    //   366	370	476	java/lang/Throwable
    //   366	370	524	finally
    //   379	385	476	java/lang/Throwable
    //   379	385	524	finally
    //   394	403	476	java/lang/Throwable
    //   394	403	524	finally
    //   411	417	524	finally
    //   422	428	524	finally
    //   437	448	524	finally
    //   487	494	524	finally
  }
  
  public static final void evalModule2(Environment paramEnvironment, CallContext paramCallContext, Language paramLanguage, ModuleExp paramModuleExp, Object paramObject) throws Throwable {
    // Byte code:
    //   0: aload_0
    //   1: invokestatic setSaveCurrent : (Lgnu/mapping/Environment;)Lgnu/mapping/Environment;
    //   4: astore #7
    //   6: aload #4
    //   8: getstatic java/lang/Boolean.TRUE : Ljava/lang/Boolean;
    //   11: if_acmpne -> 44
    //   14: aload_3
    //   15: getfield body : Lgnu/expr/Expression;
    //   18: aload_1
    //   19: invokevirtual apply : (Lgnu/mapping/CallContext;)V
    //   22: aload_1
    //   23: invokevirtual runUntilDone : ()V
    //   26: aload #7
    //   28: invokestatic restoreCurrent : (Lgnu/mapping/Environment;)V
    //   31: iconst_0
    //   32: ifeq -> 43
    //   35: new java/lang/NullPointerException
    //   38: dup
    //   39: invokespecial <init> : ()V
    //   42: athrow
    //   43: return
    //   44: aload #4
    //   46: astore #5
    //   48: aload #4
    //   50: instanceof java/lang/Class
    //   53: ifeq -> 69
    //   56: invokestatic getContext : ()Lgnu/expr/ModuleContext;
    //   59: aload #4
    //   61: checkcast java/lang/Class
    //   64: invokevirtual findInstance : (Ljava/lang/Class;)Ljava/lang/Object;
    //   67: astore #5
    //   69: aload #5
    //   71: instanceof java/lang/Runnable
    //   74: ifeq -> 112
    //   77: aload #5
    //   79: instanceof gnu/expr/ModuleBody
    //   82: ifeq -> 146
    //   85: aload #5
    //   87: checkcast gnu/expr/ModuleBody
    //   90: astore #4
    //   92: aload #4
    //   94: getfield runDone : Z
    //   97: ifne -> 112
    //   100: aload #4
    //   102: iconst_1
    //   103: putfield runDone : Z
    //   106: aload #4
    //   108: aload_1
    //   109: invokevirtual run : (Lgnu/mapping/CallContext;)V
    //   112: aload_3
    //   113: ifnonnull -> 159
    //   116: aload #5
    //   118: aload_2
    //   119: aload_0
    //   120: invokestatic defineAll : (Ljava/lang/Object;Lgnu/expr/Language;Lgnu/mapping/Environment;)V
    //   123: goto -> 22
    //   126: astore_0
    //   127: aload #7
    //   129: invokestatic restoreCurrent : (Lgnu/mapping/Environment;)V
    //   132: iconst_0
    //   133: ifeq -> 144
    //   136: new java/lang/NullPointerException
    //   139: dup
    //   140: invokespecial <init> : ()V
    //   143: athrow
    //   144: aload_0
    //   145: athrow
    //   146: aload #5
    //   148: checkcast java/lang/Runnable
    //   151: invokeinterface run : ()V
    //   156: goto -> 112
    //   159: aload_3
    //   160: invokevirtual firstDecl : ()Lgnu/expr/Declaration;
    //   163: astore_3
    //   164: aload_3
    //   165: ifnull -> 22
    //   168: aload_3
    //   169: invokevirtual getSymbol : ()Ljava/lang/Object;
    //   172: astore #4
    //   174: aload_3
    //   175: invokevirtual isPrivate : ()Z
    //   178: ifne -> 186
    //   181: aload #4
    //   183: ifnonnull -> 194
    //   186: aload_3
    //   187: invokevirtual nextDecl : ()Lgnu/expr/Declaration;
    //   190: astore_3
    //   191: goto -> 164
    //   194: aload_3
    //   195: getfield field : Lgnu/bytecode/Field;
    //   198: astore #5
    //   200: aload #4
    //   202: instanceof gnu/mapping/Symbol
    //   205: ifeq -> 291
    //   208: aload #4
    //   210: checkcast gnu/mapping/Symbol
    //   213: astore #4
    //   215: aload_2
    //   216: aload_3
    //   217: invokevirtual getEnvPropertyFor : (Lgnu/expr/Declaration;)Ljava/lang/Object;
    //   220: astore #8
    //   222: aload_3
    //   223: invokevirtual getValue : ()Lgnu/expr/Expression;
    //   226: astore #9
    //   228: aload_3
    //   229: getfield field : Lgnu/bytecode/Field;
    //   232: invokevirtual getModifiers : ()I
    //   235: bipush #16
    //   237: iand
    //   238: ifeq -> 390
    //   241: aload #9
    //   243: instanceof gnu/expr/QuoteExp
    //   246: ifeq -> 310
    //   249: aload #9
    //   251: getstatic gnu/expr/QuoteExp.undefined_exp : Lgnu/expr/QuoteExp;
    //   254: if_acmpeq -> 310
    //   257: aload #9
    //   259: checkcast gnu/expr/QuoteExp
    //   262: invokevirtual getValue : ()Ljava/lang/Object;
    //   265: astore #5
    //   267: aload_3
    //   268: invokevirtual isIndirectBinding : ()Z
    //   271: ifeq -> 377
    //   274: aload_0
    //   275: aload #4
    //   277: aload #8
    //   279: aload #5
    //   281: checkcast gnu/mapping/Location
    //   284: invokevirtual addLocation : (Lgnu/mapping/Symbol;Ljava/lang/Object;Lgnu/mapping/Location;)Lgnu/mapping/NamedLocation;
    //   287: pop
    //   288: goto -> 186
    //   291: ldc_w ''
    //   294: aload #4
    //   296: invokevirtual toString : ()Ljava/lang/String;
    //   299: invokevirtual intern : ()Ljava/lang/String;
    //   302: invokestatic make : (Ljava/lang/Object;Ljava/lang/String;)Lgnu/mapping/Symbol;
    //   305: astore #4
    //   307: goto -> 215
    //   310: aload_3
    //   311: getfield field : Lgnu/bytecode/Field;
    //   314: invokevirtual getReflectField : ()Ljava/lang/reflect/Field;
    //   317: aconst_null
    //   318: invokevirtual get : (Ljava/lang/Object;)Ljava/lang/Object;
    //   321: astore #6
    //   323: aload_3
    //   324: invokevirtual isIndirectBinding : ()Z
    //   327: ifne -> 346
    //   330: aload_3
    //   331: aload #6
    //   333: invokestatic getInstance : (Ljava/lang/Object;)Lgnu/expr/QuoteExp;
    //   336: invokevirtual setValue : (Lgnu/expr/Expression;)V
    //   339: aload #6
    //   341: astore #5
    //   343: goto -> 267
    //   346: aload_3
    //   347: invokevirtual isAlias : ()Z
    //   350: ifeq -> 365
    //   353: aload #6
    //   355: astore #5
    //   357: aload #9
    //   359: instanceof gnu/expr/ReferenceExp
    //   362: ifne -> 267
    //   365: aload_3
    //   366: aconst_null
    //   367: invokevirtual setValue : (Lgnu/expr/Expression;)V
    //   370: aload #6
    //   372: astore #5
    //   374: goto -> 267
    //   377: aload_0
    //   378: aload #4
    //   380: aload #8
    //   382: aload #5
    //   384: invokevirtual define : (Lgnu/mapping/Symbol;Ljava/lang/Object;Ljava/lang/Object;)V
    //   387: goto -> 186
    //   390: new gnu/kawa/reflect/StaticFieldLocation
    //   393: dup
    //   394: aload #5
    //   396: invokevirtual getDeclaringClass : ()Lgnu/bytecode/ClassType;
    //   399: aload #5
    //   401: invokevirtual getName : ()Ljava/lang/String;
    //   404: invokespecial <init> : (Lgnu/bytecode/ClassType;Ljava/lang/String;)V
    //   407: astore #5
    //   409: aload #5
    //   411: aload_3
    //   412: invokevirtual setDeclaration : (Lgnu/expr/Declaration;)V
    //   415: aload_0
    //   416: aload #4
    //   418: aload #8
    //   420: aload #5
    //   422: invokevirtual addLocation : (Lgnu/mapping/Symbol;Ljava/lang/Object;Lgnu/mapping/Location;)Lgnu/mapping/NamedLocation;
    //   425: pop
    //   426: aload_3
    //   427: aconst_null
    //   428: invokevirtual setValue : (Lgnu/expr/Expression;)V
    //   431: goto -> 186
    // Exception table:
    //   from	to	target	type
    //   6	22	126	finally
    //   22	26	126	finally
    //   48	69	126	finally
    //   69	112	126	finally
    //   116	123	126	finally
    //   146	156	126	finally
    //   159	164	126	finally
    //   168	181	126	finally
    //   186	191	126	finally
    //   194	215	126	finally
    //   215	267	126	finally
    //   267	288	126	finally
    //   291	307	126	finally
    //   310	339	126	finally
    //   346	353	126	finally
    //   357	365	126	finally
    //   365	370	126	finally
    //   377	387	126	finally
    //   390	431	126	finally
  }
  
  public static Class evalToClass(Compilation paramCompilation, URL paramURL) throws SyntaxException {
    ArrayClassLoader arrayClassLoader2;
    paramCompilation.getModule();
    SourceMessages sourceMessages = paramCompilation.getMessages();
    try {
      paramCompilation.minfo.loadByStages(12);
      if (sourceMessages.seenErrors())
        return null; 
      arrayClassLoader2 = paramCompilation.loader;
      URL uRL = paramURL;
      if (paramURL == null)
        uRL = Path.currentPath().toURL(); 
      arrayClassLoader2.setResourceContext(uRL);
      paramURL = null;
      if (dumpZipPrefix != null) {
        StringBuffer stringBuffer = new StringBuffer(dumpZipPrefix);
        lastZipCounter++;
        if (interactiveCounter > lastZipCounter)
          lastZipCounter = interactiveCounter; 
        stringBuffer.append(lastZipCounter);
        stringBuffer.append(".zip");
        ZipOutputStream zipOutputStream = new ZipOutputStream(new FileOutputStream(stringBuffer.toString()));
      } 
    } catch (IOException iOException) {
      throw new WrappedException("I/O error in lambda eval", iOException);
    } catch (ClassNotFoundException classNotFoundException) {
      throw new WrappedException("class not found in lambda eval", classNotFoundException);
    } catch (Throwable throwable) {
      classNotFoundException.getMessages().error('f', "internal compile error - caught " + throwable, throwable);
      throw new SyntaxException(sourceMessages);
    } 
    int i;
    for (i = 0;; i++) {
      if (i < ((Compilation)classNotFoundException).numClasses) {
        ClassType classType = ((Compilation)classNotFoundException).classes[i];
        String str = classType.getName();
        byte[] arrayOfByte = classType.writeToArray();
        arrayClassLoader2.addClass(str, arrayOfByte);
        if (throwable != null) {
          ZipEntry zipEntry = new ZipEntry(str.replace('.', '/') + ".class");
          zipEntry.setSize(arrayOfByte.length);
          CRC32 cRC32 = new CRC32();
          cRC32.update(arrayOfByte);
          zipEntry.setCrc(cRC32.getValue());
          zipEntry.setMethod(0);
          throwable.putNextEntry(zipEntry);
          throwable.write(arrayOfByte);
        } 
      } else {
        if (throwable != null)
          throwable.close(); 
        break;
      } 
    } 
    throwable = null;
    ArrayClassLoader arrayClassLoader1;
    for (arrayClassLoader1 = arrayClassLoader2; arrayClassLoader1.getParent() instanceof ArrayClassLoader; arrayClassLoader1 = (ArrayClassLoader)arrayClassLoader1.getParent());
    i = 0;
    while (true) {
      Class clazz2;
      if (i < ((Compilation)classNotFoundException).numClasses) {
        ClassType classType = ((Compilation)classNotFoundException).classes[i];
        Class clazz = arrayClassLoader2.loadClass(classType.getName());
        classType.setReflectClass(clazz);
        classType.setExisting(true);
        if (i == 0) {
          clazz2 = clazz;
        } else {
          Throwable throwable1 = throwable;
          if (arrayClassLoader1 != arrayClassLoader2) {
            arrayClassLoader1.addClass(clazz);
            throwable1 = throwable;
          } 
        } 
      } else {
        ModuleInfo moduleInfo = ((Compilation)classNotFoundException).minfo;
        moduleInfo.setModuleClass((Class)throwable);
        classNotFoundException.cleanupAfterCompilation();
        int j = moduleInfo.numDependencies;
        for (i = 0; i < j; i++) {
          ModuleInfo moduleInfo1 = moduleInfo.dependencies[i];
          clazz2 = moduleInfo1.getModuleClassRaw();
          Class clazz = clazz2;
          if (clazz2 == null)
            clazz = evalToClass(moduleInfo1.comp, (URL)null); 
          ((Compilation)classNotFoundException).loader.addClass(clazz);
        } 
        return (Class)throwable;
      } 
      i++;
      Class clazz1 = clazz2;
    } 
  }
  
  public static void mustAlwaysCompile() {
    alwaysCompile = true;
    neverCompile = false;
  }
  
  public static void mustNeverCompile() {
    alwaysCompile = false;
    neverCompile = true;
    compilerAvailable = false;
  }
  
  public void allocChildClasses(Compilation paramCompilation) {
    declareClosureEnv();
    if (!paramCompilation.usingCPStyle())
      return; 
    allocFrame(paramCompilation);
  }
  
  void allocFields(Compilation paramCompilation) {
    Declaration declaration;
    for (declaration = firstDecl(); declaration != null; declaration = declaration.nextDecl()) {
      if ((!declaration.isSimple() || declaration.isPublic()) && declaration.field == null && declaration.getFlag(65536L) && declaration.getFlag(6L))
        declaration.makeField(paramCompilation, null); 
    } 
    for (declaration = firstDecl(); declaration != null; declaration = declaration.nextDecl()) {
      if (declaration.field == null) {
        Expression expression = declaration.getValue();
        if ((!declaration.isSimple() || declaration.isPublic() || declaration.isNamespaceDecl() || (declaration.getFlag(16384L) && declaration.getFlag(6L))) && !declaration.getFlag(65536L))
          if (expression instanceof LambdaExp && !(expression instanceof ModuleExp) && !(expression instanceof ClassExp)) {
            ((LambdaExp)expression).allocFieldFor(paramCompilation);
          } else {
            Expression expression1 = expression;
            if (!declaration.shouldEarlyInit())
              if (declaration.isAlias()) {
                expression1 = expression;
              } else {
                expression1 = null;
              }  
            declaration.makeField(paramCompilation, expression1);
          }  
      } 
    } 
  }
  
  public ClassType classFor(Compilation paramCompilation) {
    // Byte code:
    //   0: aload_0
    //   1: getfield type : Lgnu/bytecode/ClassType;
    //   4: ifnull -> 26
    //   7: aload_0
    //   8: getfield type : Lgnu/bytecode/ClassType;
    //   11: getstatic gnu/expr/Compilation.typeProcedure : Lgnu/bytecode/ClassType;
    //   14: if_acmpeq -> 26
    //   17: aload_0
    //   18: getfield type : Lgnu/bytecode/ClassType;
    //   21: astore #4
    //   23: aload #4
    //   25: areturn
    //   26: aload_0
    //   27: invokevirtual getFileName : ()Ljava/lang/String;
    //   30: astore #4
    //   32: aload_0
    //   33: invokevirtual getName : ()Ljava/lang/String;
    //   36: astore_3
    //   37: aconst_null
    //   38: astore #5
    //   40: aload_3
    //   41: ifnull -> 214
    //   44: aload #5
    //   46: astore #4
    //   48: aload_0
    //   49: invokevirtual getName : ()Ljava/lang/String;
    //   52: ifnonnull -> 60
    //   55: aload_0
    //   56: aload_3
    //   57: invokevirtual setName : (Ljava/lang/String;)V
    //   60: aload_3
    //   61: invokestatic mangleNameIfNeeded : (Ljava/lang/String;)Ljava/lang/String;
    //   64: astore #5
    //   66: aload_1
    //   67: getfield classPrefix : Ljava/lang/String;
    //   70: invokevirtual length : ()I
    //   73: ifne -> 383
    //   76: aload #4
    //   78: ifnull -> 383
    //   81: aload #4
    //   83: invokevirtual isAbsolute : ()Z
    //   86: ifne -> 383
    //   89: aload #4
    //   91: invokevirtual getParent : ()Lgnu/text/Path;
    //   94: astore_3
    //   95: aload_3
    //   96: ifnull -> 383
    //   99: aload_3
    //   100: invokevirtual toString : ()Ljava/lang/String;
    //   103: astore_3
    //   104: aload_3
    //   105: invokevirtual length : ()I
    //   108: ifle -> 383
    //   111: aload_3
    //   112: ldc_w '..'
    //   115: invokevirtual indexOf : (Ljava/lang/String;)I
    //   118: ifge -> 383
    //   121: aload_3
    //   122: ldc_w 'file.separator'
    //   125: invokestatic getProperty : (Ljava/lang/String;)Ljava/lang/String;
    //   128: ldc_w '/'
    //   131: invokevirtual replaceAll : (Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
    //   134: astore #4
    //   136: aload #4
    //   138: astore_3
    //   139: aload #4
    //   141: ldc_w './'
    //   144: invokevirtual startsWith : (Ljava/lang/String;)Z
    //   147: ifeq -> 157
    //   150: aload #4
    //   152: iconst_2
    //   153: invokevirtual substring : (I)Ljava/lang/String;
    //   156: astore_3
    //   157: aload_3
    //   158: ldc_w '.'
    //   161: invokevirtual equals : (Ljava/lang/Object;)Z
    //   164: ifeq -> 351
    //   167: aload #5
    //   169: astore_3
    //   170: new gnu/bytecode/ClassType
    //   173: dup
    //   174: aload_3
    //   175: invokespecial <init> : (Ljava/lang/String;)V
    //   178: astore #5
    //   180: aload_0
    //   181: aload #5
    //   183: invokevirtual setType : (Lgnu/bytecode/ClassType;)V
    //   186: aload #5
    //   188: astore #4
    //   190: aload_1
    //   191: getfield mainLambda : Lgnu/expr/ModuleExp;
    //   194: aload_0
    //   195: if_acmpne -> 23
    //   198: aload_1
    //   199: getfield mainClass : Lgnu/bytecode/ClassType;
    //   202: ifnonnull -> 409
    //   205: aload_1
    //   206: aload #5
    //   208: putfield mainClass : Lgnu/bytecode/ClassType;
    //   211: aload #5
    //   213: areturn
    //   214: aload #4
    //   216: ifnonnull -> 248
    //   219: aload_0
    //   220: invokevirtual getName : ()Ljava/lang/String;
    //   223: astore #6
    //   225: aload #6
    //   227: astore_3
    //   228: aload #5
    //   230: astore #4
    //   232: aload #6
    //   234: ifnonnull -> 48
    //   237: ldc_w '$unnamed_input_file$'
    //   240: astore_3
    //   241: aload #5
    //   243: astore #4
    //   245: goto -> 48
    //   248: aload_0
    //   249: getfield filename : Ljava/lang/String;
    //   252: ldc_w '-'
    //   255: invokevirtual equals : (Ljava/lang/Object;)Z
    //   258: ifne -> 274
    //   261: aload_0
    //   262: getfield filename : Ljava/lang/String;
    //   265: ldc_w '/dev/stdin'
    //   268: invokevirtual equals : (Ljava/lang/Object;)Z
    //   271: ifeq -> 303
    //   274: aload_0
    //   275: invokevirtual getName : ()Ljava/lang/String;
    //   278: astore #6
    //   280: aload #6
    //   282: astore_3
    //   283: aload #5
    //   285: astore #4
    //   287: aload #6
    //   289: ifnonnull -> 48
    //   292: ldc_w '$stdin$'
    //   295: astore_3
    //   296: aload #5
    //   298: astore #4
    //   300: goto -> 48
    //   303: aload #4
    //   305: invokestatic valueOf : (Ljava/lang/Object;)Lgnu/text/Path;
    //   308: astore #5
    //   310: aload #5
    //   312: invokevirtual getLast : ()Ljava/lang/String;
    //   315: astore #6
    //   317: aload #6
    //   319: bipush #46
    //   321: invokevirtual lastIndexOf : (I)I
    //   324: istore_2
    //   325: aload #6
    //   327: astore_3
    //   328: aload #5
    //   330: astore #4
    //   332: iload_2
    //   333: ifle -> 48
    //   336: aload #6
    //   338: iconst_0
    //   339: iload_2
    //   340: invokevirtual substring : (II)Ljava/lang/String;
    //   343: astore_3
    //   344: aload #5
    //   346: astore #4
    //   348: goto -> 48
    //   351: new java/lang/StringBuilder
    //   354: dup
    //   355: invokespecial <init> : ()V
    //   358: aload_3
    //   359: invokestatic mangleURI : (Ljava/lang/String;)Ljava/lang/String;
    //   362: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   365: ldc_w '.'
    //   368: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   371: aload #5
    //   373: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   376: invokevirtual toString : ()Ljava/lang/String;
    //   379: astore_3
    //   380: goto -> 170
    //   383: new java/lang/StringBuilder
    //   386: dup
    //   387: invokespecial <init> : ()V
    //   390: aload_1
    //   391: getfield classPrefix : Ljava/lang/String;
    //   394: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   397: aload #5
    //   399: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   402: invokevirtual toString : ()Ljava/lang/String;
    //   405: astore_3
    //   406: goto -> 170
    //   409: aload #5
    //   411: astore #4
    //   413: aload_3
    //   414: aload_1
    //   415: getfield mainClass : Lgnu/bytecode/ClassType;
    //   418: invokevirtual getName : ()Ljava/lang/String;
    //   421: invokevirtual equals : (Ljava/lang/Object;)Z
    //   424: ifne -> 23
    //   427: aload_1
    //   428: bipush #101
    //   430: new java/lang/StringBuilder
    //   433: dup
    //   434: invokespecial <init> : ()V
    //   437: ldc_w 'inconsistent main class name: '
    //   440: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   443: aload_3
    //   444: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   447: ldc_w ' - old name: '
    //   450: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   453: aload_1
    //   454: getfield mainClass : Lgnu/bytecode/ClassType;
    //   457: invokevirtual getName : ()Ljava/lang/String;
    //   460: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   463: invokevirtual toString : ()Ljava/lang/String;
    //   466: invokevirtual error : (CLjava/lang/String;)V
    //   469: aload #5
    //   471: areturn
  }
  
  public Declaration firstDecl() {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: ldc 524288
    //   5: invokevirtual getFlag : (I)Z
    //   8: ifeq -> 19
    //   11: aload_0
    //   12: getfield info : Lgnu/expr/ModuleInfo;
    //   15: invokevirtual setupModuleExp : ()Lgnu/expr/ModuleExp;
    //   18: pop
    //   19: aload_0
    //   20: monitorexit
    //   21: aload_0
    //   22: getfield decls : Lgnu/expr/Declaration;
    //   25: areturn
    //   26: astore_1
    //   27: aload_0
    //   28: monitorexit
    //   29: aload_1
    //   30: athrow
    // Exception table:
    //   from	to	target	type
    //   2	19	26	finally
    //   19	21	26	finally
    //   27	29	26	finally
  }
  
  public final ClassType[] getInterfaces() {
    return this.interfaces;
  }
  
  public String getNamespaceUri() {
    return this.info.uri;
  }
  
  public final ClassType getSuperType() {
    return this.superType;
  }
  
  public final boolean isStatic() {
    return (getFlag(32768) || ((Compilation.moduleStatic >= 0 || getFlag(1048576)) && !getFlag(131072) && !getFlag(65536)));
  }
  
  public void print(OutPort paramOutPort) {
    paramOutPort.startLogicalBlock("(Module/", ")", 2);
    Object object = getSymbol();
    if (object != null) {
      paramOutPort.print(object);
      paramOutPort.print('/');
    } 
    paramOutPort.print(this.id);
    paramOutPort.print('/');
    paramOutPort.writeSpaceFill();
    paramOutPort.startLogicalBlock("(", false, ")");
    object = firstDecl();
    if (object != null) {
      paramOutPort.print("Declarations:");
      while (object != null) {
        paramOutPort.writeSpaceFill();
        object.printInfo(paramOutPort);
        object = object.nextDecl();
      } 
    } 
    paramOutPort.endLogicalBlock(")");
    paramOutPort.writeSpaceLinear();
    if (this.body == null) {
      paramOutPort.print("<null body>");
    } else {
      this.body.print(paramOutPort);
    } 
    paramOutPort.endLogicalBlock(")");
  }
  
  public void readExternal(ObjectInput paramObjectInput) throws IOException, ClassNotFoundException {
    Object object = paramObjectInput.readObject();
    if (object instanceof ClassType) {
      this.type = (ClassType)object;
      setName(this.type.getName());
    } else {
      setName((String)object);
    } 
    this.flags |= 0x80000;
  }
  
  public final void setInterfaces(ClassType[] paramArrayOfClassType) {
    this.interfaces = paramArrayOfClassType;
  }
  
  public final void setSuperType(ClassType paramClassType) {
    this.superType = paramClassType;
  }
  
  public boolean staticInitRun() {
    return (isStatic() && (getFlag(262144) || Compilation.moduleStatic == 2));
  }
  
  protected <R, D> R visit(ExpVisitor<R, D> paramExpVisitor, D paramD) {
    return paramExpVisitor.visitModuleExp(this, paramD);
  }
  
  public void writeExternal(ObjectOutput paramObjectOutput) throws IOException {
    String str1 = null;
    if (this.type != null && this.type != Compilation.typeProcedure && !this.type.isExisting()) {
      paramObjectOutput.writeObject(this.type);
      return;
    } 
    if (!false)
      str1 = getName(); 
    String str2 = str1;
    if (str1 == null)
      str2 = getFileName(); 
    paramObjectOutput.writeObject(str2);
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/gnu/expr/ModuleExp.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */