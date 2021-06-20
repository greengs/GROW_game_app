package gnu.expr;

import gnu.bytecode.ClassType;
import gnu.mapping.WrappedException;
import gnu.text.Path;
import java.io.File;
import java.net.URL;

public class ModuleManager {
  public static final long LAST_MODIFIED_CACHE_TIME = 1000L;
  
  static ModuleManager instance = new ModuleManager();
  
  private String compilationDirectory = "";
  
  public long lastModifiedCacheTime = 1000L;
  
  ModuleInfo[] modules;
  
  int numModules;
  
  ModuleSet packageInfoChain;
  
  private void add(ModuleInfo paramModuleInfo) {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield modules : [Lgnu/expr/ModuleInfo;
    //   6: ifnonnull -> 42
    //   9: aload_0
    //   10: bipush #10
    //   12: anewarray gnu/expr/ModuleInfo
    //   15: putfield modules : [Lgnu/expr/ModuleInfo;
    //   18: aload_0
    //   19: getfield modules : [Lgnu/expr/ModuleInfo;
    //   22: astore_3
    //   23: aload_0
    //   24: getfield numModules : I
    //   27: istore_2
    //   28: aload_0
    //   29: iload_2
    //   30: iconst_1
    //   31: iadd
    //   32: putfield numModules : I
    //   35: aload_3
    //   36: iload_2
    //   37: aload_1
    //   38: aastore
    //   39: aload_0
    //   40: monitorexit
    //   41: return
    //   42: aload_0
    //   43: getfield numModules : I
    //   46: aload_0
    //   47: getfield modules : [Lgnu/expr/ModuleInfo;
    //   50: arraylength
    //   51: if_icmpne -> 18
    //   54: aload_0
    //   55: getfield numModules : I
    //   58: iconst_2
    //   59: imul
    //   60: anewarray gnu/expr/ModuleInfo
    //   63: astore_3
    //   64: aload_0
    //   65: getfield modules : [Lgnu/expr/ModuleInfo;
    //   68: iconst_0
    //   69: aload_3
    //   70: iconst_0
    //   71: aload_0
    //   72: getfield numModules : I
    //   75: invokestatic arraycopy : (Ljava/lang/Object;ILjava/lang/Object;II)V
    //   78: aload_0
    //   79: aload_3
    //   80: putfield modules : [Lgnu/expr/ModuleInfo;
    //   83: goto -> 18
    //   86: astore_1
    //   87: aload_0
    //   88: monitorexit
    //   89: aload_1
    //   90: athrow
    // Exception table:
    //   from	to	target	type
    //   2	18	86	finally
    //   18	35	86	finally
    //   42	83	86	finally
  }
  
  public static ModuleInfo findWithClass(Class paramClass) {
    // Byte code:
    //   0: ldc gnu/expr/ModuleManager
    //   2: monitorenter
    //   3: getstatic gnu/expr/ModuleInfo.mapClassToInfo : Lgnu/expr/ModuleInfo$ClassToInfoMap;
    //   6: aload_0
    //   7: invokevirtual get : (Ljava/lang/Object;)Ljava/lang/Object;
    //   10: checkcast gnu/expr/ModuleInfo
    //   13: astore_2
    //   14: aload_2
    //   15: astore_1
    //   16: aload_2
    //   17: ifnonnull -> 33
    //   20: new gnu/expr/ModuleInfo
    //   23: dup
    //   24: invokespecial <init> : ()V
    //   27: astore_1
    //   28: aload_1
    //   29: aload_0
    //   30: invokevirtual setModuleClass : (Ljava/lang/Class;)V
    //   33: ldc gnu/expr/ModuleManager
    //   35: monitorexit
    //   36: aload_1
    //   37: areturn
    //   38: astore_0
    //   39: ldc gnu/expr/ModuleManager
    //   41: monitorexit
    //   42: aload_0
    //   43: athrow
    // Exception table:
    //   from	to	target	type
    //   3	14	38	finally
    //   20	33	38	finally
  }
  
  public static ModuleManager getInstance() {
    return instance;
  }
  
  private ModuleInfo searchWithAbsSourcePath(String paramString) {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield numModules : I
    //   6: istore_2
    //   7: iload_2
    //   8: iconst_1
    //   9: isub
    //   10: istore_2
    //   11: iload_2
    //   12: iflt -> 44
    //   15: aload_0
    //   16: getfield modules : [Lgnu/expr/ModuleInfo;
    //   19: iload_2
    //   20: aaload
    //   21: astore #4
    //   23: aload_1
    //   24: aload #4
    //   26: invokevirtual getSourceAbsPathname : ()Ljava/lang/String;
    //   29: invokevirtual equals : (Ljava/lang/Object;)Z
    //   32: istore_3
    //   33: iload_3
    //   34: ifeq -> 7
    //   37: aload #4
    //   39: astore_1
    //   40: aload_0
    //   41: monitorexit
    //   42: aload_1
    //   43: areturn
    //   44: aconst_null
    //   45: astore_1
    //   46: goto -> 40
    //   49: astore_1
    //   50: aload_0
    //   51: monitorexit
    //   52: aload_1
    //   53: athrow
    // Exception table:
    //   from	to	target	type
    //   2	7	49	finally
    //   15	33	49	finally
  }
  
  public void clear() {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield packageInfoChain : Lgnu/expr/ModuleSet;
    //   6: astore_1
    //   7: aload_1
    //   8: ifnull -> 26
    //   11: aload_1
    //   12: getfield next : Lgnu/expr/ModuleSet;
    //   15: astore_2
    //   16: aload_1
    //   17: aconst_null
    //   18: putfield next : Lgnu/expr/ModuleSet;
    //   21: aload_2
    //   22: astore_1
    //   23: goto -> 7
    //   26: aload_0
    //   27: aconst_null
    //   28: putfield packageInfoChain : Lgnu/expr/ModuleSet;
    //   31: aload_0
    //   32: aconst_null
    //   33: putfield modules : [Lgnu/expr/ModuleInfo;
    //   36: aload_0
    //   37: iconst_0
    //   38: putfield numModules : I
    //   41: aload_0
    //   42: monitorexit
    //   43: return
    //   44: astore_1
    //   45: aload_0
    //   46: monitorexit
    //   47: aload_1
    //   48: athrow
    // Exception table:
    //   from	to	target	type
    //   2	7	44	finally
    //   11	21	44	finally
    //   26	41	44	finally
  }
  
  public ModuleInfo find(Compilation paramCompilation) {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_1
    //   3: invokevirtual getModule : ()Lgnu/expr/ModuleExp;
    //   6: astore_2
    //   7: aload_2
    //   8: aload_1
    //   9: invokevirtual classFor : (Lgnu/expr/Compilation;)Lgnu/bytecode/ClassType;
    //   12: astore_3
    //   13: aload_2
    //   14: invokevirtual getFileName : ()Ljava/lang/String;
    //   17: astore #4
    //   19: aload_0
    //   20: aload #4
    //   22: invokestatic absPath : (Ljava/lang/String;)Lgnu/text/Path;
    //   25: aload #4
    //   27: invokevirtual findWithSourcePath : (Lgnu/text/Path;Ljava/lang/String;)Lgnu/expr/ModuleInfo;
    //   30: astore #4
    //   32: aload #4
    //   34: aload_3
    //   35: invokevirtual getName : ()Ljava/lang/String;
    //   38: invokevirtual setClassName : (Ljava/lang/String;)V
    //   41: aload #4
    //   43: aload_2
    //   44: putfield exp : Lgnu/expr/ModuleExp;
    //   47: aload_1
    //   48: aload #4
    //   50: putfield minfo : Lgnu/expr/ModuleInfo;
    //   53: aload #4
    //   55: aload_1
    //   56: putfield comp : Lgnu/expr/Compilation;
    //   59: aload_0
    //   60: monitorexit
    //   61: aload #4
    //   63: areturn
    //   64: astore_1
    //   65: aload_0
    //   66: monitorexit
    //   67: aload_1
    //   68: athrow
    // Exception table:
    //   from	to	target	type
    //   2	59	64	finally
  }
  
  public ModuleInfo findWithClassName(String paramString) {
    ModuleInfo moduleInfo = searchWithClassName(paramString);
    if (moduleInfo != null)
      return moduleInfo; 
    try {
      return findWithClass(ClassType.getContextClass(paramString));
    } catch (Throwable throwable) {
      throw WrappedException.wrapIfNeeded(throwable);
    } 
  }
  
  public ModuleInfo findWithSourcePath(Path paramPath, String paramString) {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_1
    //   3: invokevirtual toString : ()Ljava/lang/String;
    //   6: astore #5
    //   8: aload_0
    //   9: aload #5
    //   11: invokespecial searchWithAbsSourcePath : (Ljava/lang/String;)Lgnu/expr/ModuleInfo;
    //   14: astore #4
    //   16: aload #4
    //   18: astore_3
    //   19: aload #4
    //   21: ifnonnull -> 53
    //   24: new gnu/expr/ModuleInfo
    //   27: dup
    //   28: invokespecial <init> : ()V
    //   31: astore_3
    //   32: aload_3
    //   33: aload_2
    //   34: putfield sourcePath : Ljava/lang/String;
    //   37: aload_3
    //   38: aload_1
    //   39: putfield sourceAbsPath : Lgnu/text/Path;
    //   42: aload_3
    //   43: aload #5
    //   45: putfield sourceAbsPathname : Ljava/lang/String;
    //   48: aload_0
    //   49: aload_3
    //   50: invokespecial add : (Lgnu/expr/ModuleInfo;)V
    //   53: aload_0
    //   54: monitorexit
    //   55: aload_3
    //   56: areturn
    //   57: astore_1
    //   58: aload_0
    //   59: monitorexit
    //   60: aload_1
    //   61: athrow
    // Exception table:
    //   from	to	target	type
    //   2	16	57	finally
    //   24	53	57	finally
  }
  
  public ModuleInfo findWithSourcePath(String paramString) {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: aload_1
    //   4: invokestatic absPath : (Ljava/lang/String;)Lgnu/text/Path;
    //   7: aload_1
    //   8: invokevirtual findWithSourcePath : (Lgnu/text/Path;Ljava/lang/String;)Lgnu/expr/ModuleInfo;
    //   11: astore_1
    //   12: aload_0
    //   13: monitorexit
    //   14: aload_1
    //   15: areturn
    //   16: astore_1
    //   17: aload_0
    //   18: monitorexit
    //   19: aload_1
    //   20: athrow
    // Exception table:
    //   from	to	target	type
    //   2	12	16	finally
  }
  
  public ModuleInfo findWithURL(URL paramURL) {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: aload_1
    //   4: invokestatic valueOf : (Ljava/net/URL;)Lgnu/text/URLPath;
    //   7: aload_1
    //   8: invokevirtual toExternalForm : ()Ljava/lang/String;
    //   11: invokevirtual findWithSourcePath : (Lgnu/text/Path;Ljava/lang/String;)Lgnu/expr/ModuleInfo;
    //   14: astore_1
    //   15: aload_0
    //   16: monitorexit
    //   17: aload_1
    //   18: areturn
    //   19: astore_1
    //   20: aload_0
    //   21: monitorexit
    //   22: aload_1
    //   23: athrow
    // Exception table:
    //   from	to	target	type
    //   2	15	19	finally
  }
  
  public String getCompilationDirectory() {
    return this.compilationDirectory;
  }
  
  public ModuleInfo getModule(int paramInt) {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield numModules : I
    //   6: istore_2
    //   7: iload_1
    //   8: iload_2
    //   9: if_icmplt -> 18
    //   12: aconst_null
    //   13: astore_3
    //   14: aload_0
    //   15: monitorexit
    //   16: aload_3
    //   17: areturn
    //   18: aload_0
    //   19: getfield modules : [Lgnu/expr/ModuleInfo;
    //   22: iload_1
    //   23: aaload
    //   24: astore_3
    //   25: goto -> 14
    //   28: astore_3
    //   29: aload_0
    //   30: monitorexit
    //   31: aload_3
    //   32: athrow
    // Exception table:
    //   from	to	target	type
    //   2	7	28	finally
    //   18	25	28	finally
  }
  
  public void loadPackageInfo(String paramString) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: new java/lang/StringBuilder
    //   5: dup
    //   6: invokespecial <init> : ()V
    //   9: aload_1
    //   10: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   13: ldc '.'
    //   15: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   18: ldc '$ModulesMap$'
    //   20: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   23: invokevirtual toString : ()Ljava/lang/String;
    //   26: astore_2
    //   27: aload_0
    //   28: getfield packageInfoChain : Lgnu/expr/ModuleSet;
    //   31: astore_1
    //   32: aload_1
    //   33: ifnull -> 58
    //   36: aload_1
    //   37: invokevirtual getClass : ()Ljava/lang/Class;
    //   40: invokevirtual getName : ()Ljava/lang/String;
    //   43: aload_2
    //   44: invokevirtual equals : (Ljava/lang/Object;)Z
    //   47: ifeq -> 50
    //   50: aload_1
    //   51: getfield next : Lgnu/expr/ModuleSet;
    //   54: astore_1
    //   55: goto -> 32
    //   58: aload_2
    //   59: invokestatic forName : (Ljava/lang/String;)Ljava/lang/Class;
    //   62: invokevirtual newInstance : ()Ljava/lang/Object;
    //   65: checkcast gnu/expr/ModuleSet
    //   68: astore_1
    //   69: aload_1
    //   70: aload_0
    //   71: getfield packageInfoChain : Lgnu/expr/ModuleSet;
    //   74: putfield next : Lgnu/expr/ModuleSet;
    //   77: aload_0
    //   78: aload_1
    //   79: putfield packageInfoChain : Lgnu/expr/ModuleSet;
    //   82: aload_1
    //   83: aload_0
    //   84: invokevirtual register : (Lgnu/expr/ModuleManager;)V
    //   87: aload_0
    //   88: monitorexit
    //   89: return
    //   90: astore_1
    //   91: aload_0
    //   92: monitorexit
    //   93: aload_1
    //   94: athrow
    // Exception table:
    //   from	to	target	type
    //   2	32	90	finally
    //   36	50	90	finally
    //   50	55	90	finally
    //   58	87	90	finally
  }
  
  public void register(String paramString1, String paramString2, String paramString3) {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: aload_1
    //   4: invokevirtual searchWithClassName : (Ljava/lang/String;)Lgnu/expr/ModuleInfo;
    //   7: astore #4
    //   9: aload #4
    //   11: ifnull -> 17
    //   14: aload_0
    //   15: monitorexit
    //   16: return
    //   17: aload_2
    //   18: invokestatic valueOf : (Ljava/lang/Object;)Lgnu/text/Path;
    //   21: astore #5
    //   23: aload #5
    //   25: invokevirtual getCanonical : ()Lgnu/text/Path;
    //   28: invokevirtual toString : ()Ljava/lang/String;
    //   31: astore #6
    //   33: aload_0
    //   34: aload #6
    //   36: invokespecial searchWithAbsSourcePath : (Ljava/lang/String;)Lgnu/expr/ModuleInfo;
    //   39: ifnonnull -> 14
    //   42: new gnu/expr/ModuleInfo
    //   45: dup
    //   46: invokespecial <init> : ()V
    //   49: astore #4
    //   51: aload #5
    //   53: invokevirtual isAbsolute : ()Z
    //   56: ifeq -> 105
    //   59: aload #4
    //   61: aload #5
    //   63: putfield sourceAbsPath : Lgnu/text/Path;
    //   66: aload #4
    //   68: aload #6
    //   70: putfield sourceAbsPathname : Ljava/lang/String;
    //   73: aload #4
    //   75: aload_1
    //   76: invokevirtual setClassName : (Ljava/lang/String;)V
    //   79: aload #4
    //   81: aload_2
    //   82: putfield sourcePath : Ljava/lang/String;
    //   85: aload #4
    //   87: aload_3
    //   88: putfield uri : Ljava/lang/String;
    //   91: aload_0
    //   92: aload #4
    //   94: invokespecial add : (Lgnu/expr/ModuleInfo;)V
    //   97: goto -> 14
    //   100: astore_1
    //   101: aload_0
    //   102: monitorexit
    //   103: aload_1
    //   104: athrow
    //   105: aload_0
    //   106: getfield packageInfoChain : Lgnu/expr/ModuleSet;
    //   109: invokevirtual getClass : ()Ljava/lang/Class;
    //   112: astore #5
    //   114: new java/lang/StringBuilder
    //   117: dup
    //   118: invokespecial <init> : ()V
    //   121: aload #5
    //   123: invokevirtual getName : ()Ljava/lang/String;
    //   126: bipush #46
    //   128: bipush #47
    //   130: invokevirtual replace : (CC)Ljava/lang/String;
    //   133: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   136: ldc '.class'
    //   138: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   141: invokevirtual toString : ()Ljava/lang/String;
    //   144: astore #6
    //   146: aload #5
    //   148: invokevirtual getClassLoader : ()Ljava/lang/ClassLoader;
    //   151: aload #6
    //   153: invokevirtual getResource : (Ljava/lang/String;)Ljava/net/URL;
    //   156: invokestatic valueOf : (Ljava/net/URL;)Lgnu/text/URLPath;
    //   159: aload_2
    //   160: invokevirtual resolve : (Ljava/lang/String;)Lgnu/text/Path;
    //   163: astore #5
    //   165: aload #4
    //   167: aload #5
    //   169: putfield sourceAbsPath : Lgnu/text/Path;
    //   172: aload #4
    //   174: aload #5
    //   176: invokevirtual toString : ()Ljava/lang/String;
    //   179: putfield sourceAbsPathname : Ljava/lang/String;
    //   182: goto -> 73
    //   185: astore_1
    //   186: goto -> 14
    // Exception table:
    //   from	to	target	type
    //   2	9	100	finally
    //   17	73	100	finally
    //   73	97	100	finally
    //   105	182	185	java/lang/Throwable
    //   105	182	100	finally
  }
  
  public ModuleInfo searchWithClassName(String paramString) {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield numModules : I
    //   6: istore_2
    //   7: iload_2
    //   8: iconst_1
    //   9: isub
    //   10: istore_2
    //   11: iload_2
    //   12: iflt -> 44
    //   15: aload_0
    //   16: getfield modules : [Lgnu/expr/ModuleInfo;
    //   19: iload_2
    //   20: aaload
    //   21: astore #4
    //   23: aload_1
    //   24: aload #4
    //   26: invokevirtual getClassName : ()Ljava/lang/String;
    //   29: invokevirtual equals : (Ljava/lang/Object;)Z
    //   32: istore_3
    //   33: iload_3
    //   34: ifeq -> 7
    //   37: aload #4
    //   39: astore_1
    //   40: aload_0
    //   41: monitorexit
    //   42: aload_1
    //   43: areturn
    //   44: aconst_null
    //   45: astore_1
    //   46: goto -> 40
    //   49: astore_1
    //   50: aload_0
    //   51: monitorexit
    //   52: aload_1
    //   53: athrow
    // Exception table:
    //   from	to	target	type
    //   2	7	49	finally
    //   15	33	49	finally
  }
  
  public void setCompilationDirectory(String paramString) {
    String str = paramString;
    if (paramString == null)
      str = ""; 
    int i = str.length();
    paramString = str;
    if (i > 0) {
      char c = File.separatorChar;
      paramString = str;
      if (str.charAt(i - 1) != c)
        paramString = str + c; 
    } 
    this.compilationDirectory = paramString;
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/gnu/expr/ModuleManager.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */