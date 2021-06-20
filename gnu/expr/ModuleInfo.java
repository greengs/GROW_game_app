package gnu.expr;

import gnu.bytecode.ClassType;
import gnu.kawa.reflect.FieldLocation;
import gnu.kawa.util.AbstractWeakHashTable;
import gnu.text.Path;

public class ModuleInfo {
  static ClassToInfoMap mapClassToInfo = new ClassToInfoMap();
  
  private String className;
  
  Compilation comp;
  
  ModuleInfo[] dependencies;
  
  ModuleExp exp;
  
  public long lastCheckedTime;
  
  public long lastModifiedTime;
  
  Class moduleClass;
  
  int numDependencies;
  
  Path sourceAbsPath;
  
  String sourceAbsPathname;
  
  public String sourcePath;
  
  String uri;
  
  public static Path absPath(String paramString) {
    return Path.valueOf(paramString).getCanonical();
  }
  
  public static ModuleInfo find(ClassType paramClassType) {
    if (paramClassType.isExisting())
      try {
        return ModuleManager.findWithClass(paramClassType.getReflectClass());
      } catch (Exception exception) {} 
    return ModuleManager.getInstance().findWithClassName(paramClassType.getName());
  }
  
  public static ModuleInfo findFromInstance(Object paramObject) {
    return ModuleContext.getContext().findFromInstance(paramObject);
  }
  
  static void makeDeclInModule2(ModuleExp paramModuleExp, Declaration paramDeclaration) {
    Object object = paramDeclaration.getConstantValue();
    if (object instanceof FieldLocation) {
      FieldLocation fieldLocation = (FieldLocation)object;
      Declaration declaration = fieldLocation.getDeclaration();
      object = new ReferenceExp(declaration);
      paramDeclaration.setAlias(true);
      object.setDontDereference(true);
      paramDeclaration.setValue((Expression)object);
      if (declaration.isProcedureDecl())
        paramDeclaration.setProcedureDecl(true); 
      if (declaration.getFlag(32768L))
        paramDeclaration.setSyntax(); 
      if (!paramDeclaration.getFlag(2048L)) {
        String str = fieldLocation.getDeclaringClass().getName();
        for (Declaration declaration1 = paramModuleExp.firstDecl();; declaration1 = declaration1.nextDecl()) {
          if (declaration1 != null) {
            if (str.equals(declaration1.getType().getName()) && declaration1.getFlag(1073741824L)) {
              object.setContextDecl(declaration1);
              return;
            } 
          } else {
            return;
          } 
        } 
      } 
    } 
  }
  
  public static void register(Object paramObject) {
    ModuleContext.getContext().setInstance(paramObject);
  }
  
  public void addDependency(ModuleInfo paramModuleInfo) {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield dependencies : [Lgnu/expr/ModuleInfo;
    //   6: ifnonnull -> 42
    //   9: aload_0
    //   10: bipush #8
    //   12: anewarray gnu/expr/ModuleInfo
    //   15: putfield dependencies : [Lgnu/expr/ModuleInfo;
    //   18: aload_0
    //   19: getfield dependencies : [Lgnu/expr/ModuleInfo;
    //   22: astore_3
    //   23: aload_0
    //   24: getfield numDependencies : I
    //   27: istore_2
    //   28: aload_0
    //   29: iload_2
    //   30: iconst_1
    //   31: iadd
    //   32: putfield numDependencies : I
    //   35: aload_3
    //   36: iload_2
    //   37: aload_1
    //   38: aastore
    //   39: aload_0
    //   40: monitorexit
    //   41: return
    //   42: aload_0
    //   43: getfield numDependencies : I
    //   46: aload_0
    //   47: getfield dependencies : [Lgnu/expr/ModuleInfo;
    //   50: arraylength
    //   51: if_icmpne -> 18
    //   54: aload_0
    //   55: getfield numDependencies : I
    //   58: iconst_2
    //   59: imul
    //   60: anewarray gnu/expr/ModuleInfo
    //   63: astore_3
    //   64: aload_0
    //   65: getfield dependencies : [Lgnu/expr/ModuleInfo;
    //   68: iconst_0
    //   69: aload_3
    //   70: iconst_0
    //   71: aload_0
    //   72: getfield numDependencies : I
    //   75: invokestatic arraycopy : (Ljava/lang/Object;ILjava/lang/Object;II)V
    //   78: aload_0
    //   79: aload_3
    //   80: putfield dependencies : [Lgnu/expr/ModuleInfo;
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
  
  public boolean checkCurrent(ModuleManager paramModuleManager, long paramLong) {
    // Byte code:
    //   0: aload_0
    //   1: getfield sourceAbsPath : Lgnu/text/Path;
    //   4: ifnonnull -> 9
    //   7: iconst_1
    //   8: ireturn
    //   9: aload_0
    //   10: getfield lastCheckedTime : J
    //   13: aload_1
    //   14: getfield lastModifiedCacheTime : J
    //   17: ladd
    //   18: lload_2
    //   19: lcmp
    //   20: iflt -> 34
    //   23: aload_0
    //   24: getfield moduleClass : Ljava/lang/Class;
    //   27: ifnull -> 32
    //   30: iconst_1
    //   31: ireturn
    //   32: iconst_0
    //   33: ireturn
    //   34: aload_0
    //   35: getfield sourceAbsPath : Lgnu/text/Path;
    //   38: invokevirtual getLastModified : ()J
    //   41: lstore #10
    //   43: aload_0
    //   44: getfield lastModifiedTime : J
    //   47: lstore #8
    //   49: aload_0
    //   50: lload #10
    //   52: putfield lastModifiedTime : J
    //   55: aload_0
    //   56: lload_2
    //   57: putfield lastCheckedTime : J
    //   60: aload_0
    //   61: getfield className : Ljava/lang/String;
    //   64: ifnonnull -> 69
    //   67: iconst_0
    //   68: ireturn
    //   69: aload_0
    //   70: getfield moduleClass : Ljava/lang/Class;
    //   73: ifnonnull -> 87
    //   76: aload_0
    //   77: aload_0
    //   78: getfield className : Ljava/lang/String;
    //   81: invokestatic getContextClass : (Ljava/lang/String;)Ljava/lang/Class;
    //   84: putfield moduleClass : Ljava/lang/Class;
    //   87: lload #8
    //   89: lstore #6
    //   91: lload #8
    //   93: lconst_0
    //   94: lcmp
    //   95: ifne -> 226
    //   98: lload #8
    //   100: lstore #6
    //   102: aload_0
    //   103: getfield moduleClass : Ljava/lang/Class;
    //   106: ifnull -> 226
    //   109: aload_0
    //   110: getfield className : Ljava/lang/String;
    //   113: astore #13
    //   115: aload #13
    //   117: bipush #46
    //   119: invokevirtual lastIndexOf : (I)I
    //   122: istore #4
    //   124: aload #13
    //   126: astore #12
    //   128: iload #4
    //   130: iflt -> 144
    //   133: aload #13
    //   135: iload #4
    //   137: iconst_1
    //   138: iadd
    //   139: invokevirtual substring : (I)Ljava/lang/String;
    //   142: astore #12
    //   144: new java/lang/StringBuilder
    //   147: dup
    //   148: invokespecial <init> : ()V
    //   151: aload #12
    //   153: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   156: ldc '.class'
    //   158: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   161: invokevirtual toString : ()Ljava/lang/String;
    //   164: astore #12
    //   166: aload_0
    //   167: getfield moduleClass : Ljava/lang/Class;
    //   170: aload #12
    //   172: invokevirtual getResource : (Ljava/lang/String;)Ljava/net/URL;
    //   175: astore #13
    //   177: aload #13
    //   179: astore #12
    //   181: lload #8
    //   183: lstore #6
    //   185: aload #13
    //   187: ifnull -> 204
    //   190: aload #13
    //   192: invokevirtual openConnection : ()Ljava/net/URLConnection;
    //   195: invokevirtual getLastModified : ()J
    //   198: lstore #6
    //   200: aload #13
    //   202: astore #12
    //   204: aload #12
    //   206: ifnonnull -> 226
    //   209: iconst_1
    //   210: ireturn
    //   211: astore_1
    //   212: iconst_0
    //   213: ireturn
    //   214: astore #12
    //   216: aconst_null
    //   217: astore #12
    //   219: lload #8
    //   221: lstore #6
    //   223: goto -> 204
    //   226: lload #10
    //   228: lload #6
    //   230: lcmp
    //   231: ifle -> 241
    //   234: aload_0
    //   235: aconst_null
    //   236: putfield moduleClass : Ljava/lang/Class;
    //   239: iconst_0
    //   240: ireturn
    //   241: aload_0
    //   242: getfield numDependencies : I
    //   245: istore #4
    //   247: iload #4
    //   249: iconst_1
    //   250: isub
    //   251: istore #5
    //   253: iload #5
    //   255: iflt -> 300
    //   258: aload_0
    //   259: getfield dependencies : [Lgnu/expr/ModuleInfo;
    //   262: iload #5
    //   264: aaload
    //   265: astore #12
    //   267: iload #5
    //   269: istore #4
    //   271: aload #12
    //   273: getfield comp : Lgnu/expr/Compilation;
    //   276: ifnonnull -> 247
    //   279: iload #5
    //   281: istore #4
    //   283: aload #12
    //   285: aload_1
    //   286: lload_2
    //   287: invokevirtual checkCurrent : (Lgnu/expr/ModuleManager;J)Z
    //   290: ifne -> 247
    //   293: aload_0
    //   294: aconst_null
    //   295: putfield moduleClass : Ljava/lang/Class;
    //   298: iconst_0
    //   299: ireturn
    //   300: iconst_1
    //   301: ireturn
    // Exception table:
    //   from	to	target	type
    //   76	87	211	java/lang/ClassNotFoundException
    //   190	200	214	java/io/IOException
  }
  
  public void cleanupAfterCompilation() {
    if (this.comp != null)
      this.comp.cleanupAfterCompilation(); 
  }
  
  public void clearClass() {
    this.moduleClass = null;
    this.numDependencies = 0;
    this.dependencies = null;
  }
  
  public String getClassName() {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield className : Ljava/lang/String;
    //   6: ifnonnull -> 27
    //   9: aload_0
    //   10: getfield moduleClass : Ljava/lang/Class;
    //   13: ifnull -> 36
    //   16: aload_0
    //   17: aload_0
    //   18: getfield moduleClass : Ljava/lang/Class;
    //   21: invokevirtual getName : ()Ljava/lang/String;
    //   24: putfield className : Ljava/lang/String;
    //   27: aload_0
    //   28: getfield className : Ljava/lang/String;
    //   31: astore_1
    //   32: aload_0
    //   33: monitorexit
    //   34: aload_1
    //   35: areturn
    //   36: aload_0
    //   37: getfield comp : Lgnu/expr/Compilation;
    //   40: ifnull -> 27
    //   43: aload_0
    //   44: getfield comp : Lgnu/expr/Compilation;
    //   47: getfield mainClass : Lgnu/bytecode/ClassType;
    //   50: ifnull -> 27
    //   53: aload_0
    //   54: aload_0
    //   55: getfield comp : Lgnu/expr/Compilation;
    //   58: getfield mainClass : Lgnu/bytecode/ClassType;
    //   61: invokevirtual getName : ()Ljava/lang/String;
    //   64: putfield className : Ljava/lang/String;
    //   67: goto -> 27
    //   70: astore_1
    //   71: aload_0
    //   72: monitorexit
    //   73: aload_1
    //   74: athrow
    // Exception table:
    //   from	to	target	type
    //   2	27	70	finally
    //   27	32	70	finally
    //   36	67	70	finally
  }
  
  public ClassType getClassType() {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield moduleClass : Ljava/lang/Class;
    //   6: ifnull -> 24
    //   9: aload_0
    //   10: getfield moduleClass : Ljava/lang/Class;
    //   13: invokestatic make : (Ljava/lang/Class;)Lgnu/bytecode/Type;
    //   16: checkcast gnu/bytecode/ClassType
    //   19: astore_1
    //   20: aload_0
    //   21: monitorexit
    //   22: aload_1
    //   23: areturn
    //   24: aload_0
    //   25: getfield comp : Lgnu/expr/Compilation;
    //   28: ifnull -> 52
    //   31: aload_0
    //   32: getfield comp : Lgnu/expr/Compilation;
    //   35: getfield mainClass : Lgnu/bytecode/ClassType;
    //   38: ifnull -> 52
    //   41: aload_0
    //   42: getfield comp : Lgnu/expr/Compilation;
    //   45: getfield mainClass : Lgnu/bytecode/ClassType;
    //   48: astore_1
    //   49: goto -> 20
    //   52: aload_0
    //   53: getfield className : Ljava/lang/String;
    //   56: invokestatic make : (Ljava/lang/String;)Lgnu/bytecode/ClassType;
    //   59: astore_1
    //   60: goto -> 20
    //   63: astore_1
    //   64: aload_0
    //   65: monitorexit
    //   66: aload_1
    //   67: athrow
    // Exception table:
    //   from	to	target	type
    //   2	20	63	finally
    //   24	49	63	finally
    //   52	60	63	finally
  }
  
  public Compilation getCompilation() {
    return this.comp;
  }
  
  public Object getInstance() {
    return ModuleContext.getContext().findInstance(this);
  }
  
  public Class getModuleClass() throws ClassNotFoundException {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield moduleClass : Ljava/lang/Class;
    //   6: astore_1
    //   7: aload_1
    //   8: ifnull -> 15
    //   11: aload_0
    //   12: monitorexit
    //   13: aload_1
    //   14: areturn
    //   15: aload_0
    //   16: getfield className : Ljava/lang/String;
    //   19: invokestatic getContextClass : (Ljava/lang/String;)Ljava/lang/Class;
    //   22: astore_1
    //   23: aload_0
    //   24: aload_1
    //   25: putfield moduleClass : Ljava/lang/Class;
    //   28: goto -> 11
    //   31: astore_1
    //   32: aload_0
    //   33: monitorexit
    //   34: aload_1
    //   35: athrow
    // Exception table:
    //   from	to	target	type
    //   2	7	31	finally
    //   15	28	31	finally
  }
  
  public Class getModuleClassRaw() {
    return this.moduleClass;
  }
  
  public ModuleExp getModuleExp() {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield exp : Lgnu/expr/ModuleExp;
    //   6: astore_2
    //   7: aload_2
    //   8: astore_1
    //   9: aload_2
    //   10: ifnonnull -> 83
    //   13: aload_0
    //   14: getfield comp : Lgnu/expr/Compilation;
    //   17: ifnull -> 32
    //   20: aload_0
    //   21: getfield comp : Lgnu/expr/Compilation;
    //   24: getfield mainLambda : Lgnu/expr/ModuleExp;
    //   27: astore_1
    //   28: aload_0
    //   29: monitorexit
    //   30: aload_1
    //   31: areturn
    //   32: aload_0
    //   33: getfield className : Ljava/lang/String;
    //   36: invokestatic make : (Ljava/lang/String;)Lgnu/bytecode/ClassType;
    //   39: astore_2
    //   40: new gnu/expr/ModuleExp
    //   43: dup
    //   44: invokespecial <init> : ()V
    //   47: astore_1
    //   48: aload_1
    //   49: aload_2
    //   50: putfield type : Lgnu/bytecode/ClassType;
    //   53: aload_1
    //   54: aload_2
    //   55: invokevirtual getName : ()Ljava/lang/String;
    //   58: invokevirtual setName : (Ljava/lang/String;)V
    //   61: aload_1
    //   62: aload_1
    //   63: getfield flags : I
    //   66: ldc_w 524288
    //   69: ior
    //   70: putfield flags : I
    //   73: aload_1
    //   74: aload_0
    //   75: putfield info : Lgnu/expr/ModuleInfo;
    //   78: aload_0
    //   79: aload_1
    //   80: putfield exp : Lgnu/expr/ModuleExp;
    //   83: goto -> 28
    //   86: astore_1
    //   87: aload_0
    //   88: monitorexit
    //   89: aload_1
    //   90: athrow
    // Exception table:
    //   from	to	target	type
    //   2	7	86	finally
    //   13	28	86	finally
    //   32	83	86	finally
  }
  
  public String getNamespaceUri() {
    return this.uri;
  }
  
  public Object getRunInstance() {
    Object object = getInstance();
    if (object instanceof Runnable)
      ((Runnable)object).run(); 
    return object;
  }
  
  public Path getSourceAbsPath() {
    return this.sourceAbsPath;
  }
  
  public String getSourceAbsPathname() {
    String str2 = this.sourceAbsPathname;
    String str1 = str2;
    if (str2 == null) {
      str1 = str2;
      if (this.sourceAbsPath != null) {
        str1 = this.sourceAbsPath.toString();
        this.sourceAbsPathname = str1;
      } 
    } 
    return str1;
  }
  
  public int getState() {
    return (this.comp == null) ? 14 : this.comp.getState();
  }
  
  public void loadByStages(int paramInt) {
    if (getState() + 1 < paramInt) {
      loadByStages(paramInt - 2);
      int i = getState();
      if (i < paramInt) {
        this.comp.setState(i + 1);
        int j = this.numDependencies;
        for (i = 0; i < j; i++)
          this.dependencies[i].loadByStages(paramInt); 
        i = getState();
        if (i < paramInt) {
          this.comp.setState(i & 0xFFFFFFFE);
          this.comp.process(paramInt);
          return;
        } 
      } 
    } 
  }
  
  public boolean loadEager(int paramInt) {
    boolean bool = true;
    if (this.comp != null || this.className == null) {
      int i = getState();
      if (i >= paramInt)
        return true; 
      if ((i & 0x1) == 0) {
        this.comp.setState(i + 1);
        int k = this.numDependencies;
        for (int j = 0; j < k; j++) {
          if (!this.dependencies[j].loadEager(paramInt)) {
            if (getState() == i + 1) {
              this.comp.setState(i);
              return false;
            } 
            return false;
          } 
        } 
        if (getState() == i + 1)
          this.comp.setState(i); 
        this.comp.process(paramInt);
        if (getState() != paramInt)
          bool = false; 
        return bool;
      } 
    } 
    return false;
  }
  
  public void setClassName(String paramString) {
    this.className = paramString;
  }
  
  public void setCompilation(Compilation paramCompilation) {
    paramCompilation.minfo = this;
    this.comp = paramCompilation;
    ModuleExp moduleExp = paramCompilation.mainLambda;
    this.exp = moduleExp;
    if (moduleExp != null) {
      String str = moduleExp.getFileName();
      this.sourcePath = str;
      this.sourceAbsPath = absPath(str);
    } 
  }
  
  public void setModuleClass(Class paramClass) {
    this.moduleClass = paramClass;
    this.className = paramClass.getName();
    mapClassToInfo.put(paramClass, this);
  }
  
  public void setNamespaceUri(String paramString) {
    this.uri = paramString;
  }
  
  public void setSourceAbsPath(Path paramPath) {
    this.sourceAbsPath = paramPath;
    this.sourceAbsPathname = null;
  }
  
  public ModuleExp setupModuleExp() {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: invokevirtual getModuleExp : ()Lgnu/expr/ModuleExp;
    //   6: astore #6
    //   8: aload #6
    //   10: getfield flags : I
    //   13: istore_1
    //   14: iload_1
    //   15: ldc_w 524288
    //   18: iand
    //   19: ifne -> 27
    //   22: aload_0
    //   23: monitorexit
    //   24: aload #6
    //   26: areturn
    //   27: aload #6
    //   29: iconst_0
    //   30: ldc_w 524288
    //   33: invokevirtual setFlag : (ZI)V
    //   36: aload_0
    //   37: getfield moduleClass : Ljava/lang/Class;
    //   40: ifnull -> 102
    //   43: aload_0
    //   44: getfield moduleClass : Ljava/lang/Class;
    //   47: astore #4
    //   49: aload #4
    //   51: invokestatic make : (Ljava/lang/Class;)Lgnu/bytecode/Type;
    //   54: checkcast gnu/bytecode/ClassType
    //   57: astore_2
    //   58: aconst_null
    //   59: astore_3
    //   60: invokestatic getDefaultLanguage : ()Lgnu/expr/Language;
    //   63: astore #7
    //   65: aload_2
    //   66: invokevirtual getFields : ()Lgnu/bytecode/Field;
    //   69: astore #5
    //   71: aload #5
    //   73: ifnull -> 227
    //   76: aload #5
    //   78: invokevirtual getFlags : ()I
    //   81: istore_1
    //   82: iload_1
    //   83: iconst_1
    //   84: iand
    //   85: ifne -> 119
    //   88: aload_3
    //   89: astore_2
    //   90: aload #5
    //   92: invokevirtual getNext : ()Lgnu/bytecode/Field;
    //   95: astore #5
    //   97: aload_2
    //   98: astore_3
    //   99: goto -> 71
    //   102: aload_0
    //   103: getfield className : Ljava/lang/String;
    //   106: invokestatic make : (Ljava/lang/String;)Lgnu/bytecode/ClassType;
    //   109: astore_2
    //   110: aload_2
    //   111: invokevirtual getReflectClass : ()Ljava/lang/Class;
    //   114: astore #4
    //   116: goto -> 58
    //   119: aload_3
    //   120: astore_2
    //   121: iload_1
    //   122: bipush #8
    //   124: iand
    //   125: ifne -> 139
    //   128: aload_3
    //   129: astore_2
    //   130: aload_3
    //   131: ifnonnull -> 139
    //   134: aload_0
    //   135: invokevirtual getInstance : ()Ljava/lang/Object;
    //   138: astore_2
    //   139: aload #4
    //   141: aload #5
    //   143: invokevirtual getName : ()Ljava/lang/String;
    //   146: invokevirtual getField : (Ljava/lang/String;)Ljava/lang/reflect/Field;
    //   149: aload_2
    //   150: invokevirtual get : (Ljava/lang/Object;)Ljava/lang/Object;
    //   153: astore_3
    //   154: aload #7
    //   156: aload #6
    //   158: aload_3
    //   159: aload #5
    //   161: invokevirtual declFromField : (Lgnu/expr/ModuleExp;Ljava/lang/Object;Lgnu/bytecode/Field;)Lgnu/expr/Declaration;
    //   164: astore #8
    //   166: iload_1
    //   167: bipush #16
    //   169: iand
    //   170: ifeq -> 218
    //   173: aload_3
    //   174: instanceof gnu/mapping/Location
    //   177: ifeq -> 187
    //   180: aload_3
    //   181: instanceof gnu/kawa/reflect/FieldLocation
    //   184: ifeq -> 218
    //   187: aload #8
    //   189: new gnu/expr/QuoteExp
    //   192: dup
    //   193: aload_3
    //   194: invokespecial <init> : (Ljava/lang/Object;)V
    //   197: invokevirtual noteValue : (Lgnu/expr/Expression;)V
    //   200: goto -> 90
    //   203: astore_2
    //   204: new gnu/mapping/WrappedException
    //   207: dup
    //   208: aload_2
    //   209: invokespecial <init> : (Ljava/lang/Throwable;)V
    //   212: athrow
    //   213: astore_2
    //   214: aload_0
    //   215: monitorexit
    //   216: aload_2
    //   217: athrow
    //   218: aload #8
    //   220: aconst_null
    //   221: invokevirtual noteValue : (Lgnu/expr/Expression;)V
    //   224: goto -> 90
    //   227: aload #6
    //   229: invokevirtual firstDecl : ()Lgnu/expr/Declaration;
    //   232: astore_2
    //   233: aload_2
    //   234: ifnull -> 22
    //   237: aload #6
    //   239: aload_2
    //   240: invokestatic makeDeclInModule2 : (Lgnu/expr/ModuleExp;Lgnu/expr/Declaration;)V
    //   243: aload_2
    //   244: invokevirtual nextDecl : ()Lgnu/expr/Declaration;
    //   247: astore_2
    //   248: goto -> 233
    // Exception table:
    //   from	to	target	type
    //   2	14	213	finally
    //   27	58	213	finally
    //   60	71	213	finally
    //   76	82	213	finally
    //   90	97	213	finally
    //   102	116	213	finally
    //   134	139	203	java/lang/Exception
    //   134	139	213	finally
    //   139	166	203	java/lang/Exception
    //   139	166	213	finally
    //   173	187	203	java/lang/Exception
    //   173	187	213	finally
    //   187	200	203	java/lang/Exception
    //   187	200	213	finally
    //   204	213	213	finally
    //   218	224	203	java/lang/Exception
    //   218	224	213	finally
    //   227	233	213	finally
    //   237	248	213	finally
  }
  
  public String toString() {
    StringBuffer stringBuffer = new StringBuffer();
    stringBuffer.append("ModuleInfo[");
    if (this.moduleClass != null) {
      stringBuffer.append("class: ");
      stringBuffer.append(this.moduleClass);
      stringBuffer.append(']');
      return stringBuffer.toString();
    } 
    if (this.className != null) {
      stringBuffer.append("class-name: ");
      stringBuffer.append(this.className);
    } 
    stringBuffer.append(']');
    return stringBuffer.toString();
  }
  
  static class ClassToInfoMap extends AbstractWeakHashTable<Class, ModuleInfo> {
    protected Class getKeyFromValue(ModuleInfo param1ModuleInfo) {
      return param1ModuleInfo.moduleClass;
    }
    
    protected boolean matches(Class param1Class1, Class param1Class2) {
      return (param1Class1 == param1Class2);
    }
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/gnu/expr/ModuleInfo.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */