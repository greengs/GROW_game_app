package gnu.expr;

import gnu.bytecode.ArrayType;
import gnu.bytecode.ClassType;
import gnu.bytecode.CodeAttr;
import gnu.bytecode.Field;
import gnu.bytecode.Type;
import gnu.kawa.lispexpr.ClassNamespace;
import gnu.kawa.reflect.ClassMemberLocation;
import gnu.kawa.reflect.StaticFieldLocation;
import gnu.lists.AbstractFormat;
import gnu.lists.Consumer;
import gnu.lists.Convert;
import gnu.lists.PrintConsumer;
import gnu.mapping.CallContext;
import gnu.mapping.CharArrayInPort;
import gnu.mapping.Environment;
import gnu.mapping.EnvironmentKey;
import gnu.mapping.InPort;
import gnu.mapping.Named;
import gnu.mapping.NamedLocation;
import gnu.mapping.Namespace;
import gnu.mapping.OutPort;
import gnu.mapping.Procedure;
import gnu.mapping.SimpleSymbol;
import gnu.mapping.Symbol;
import gnu.mapping.Values;
import gnu.mapping.WrappedException;
import gnu.text.Lexer;
import gnu.text.SourceMessages;
import gnu.text.SyntaxException;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.io.Writer;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import kawa.repl;

public abstract class Language {
  public static final int FUNCTION_NAMESPACE = 2;
  
  public static final int NAMESPACE_PREFIX_NAMESPACE = 4;
  
  public static final int PARSE_CURRENT_NAMES = 2;
  
  public static final int PARSE_EXPLICIT = 64;
  
  public static final int PARSE_FOR_APPLET = 16;
  
  public static final int PARSE_FOR_EVAL = 3;
  
  public static final int PARSE_FOR_SERVLET = 32;
  
  public static final int PARSE_IMMEDIATE = 1;
  
  public static final int PARSE_ONE_LINE = 4;
  
  public static final int PARSE_PROLOG = 8;
  
  public static final int VALUE_NAMESPACE = 1;
  
  protected static final InheritableThreadLocal<Language> current = new InheritableThreadLocal<Language>();
  
  static int envCounter;
  
  protected static int env_counter;
  
  protected static Language global;
  
  static String[][] languages = new String[][] { { "scheme", ".scm", ".sc", "kawa.standard.Scheme" }, { "krl", ".krl", "gnu.kawa.brl.BRL" }, { "brl", ".brl", "gnu.kawa.brl.BRL" }, { "emacs", "elisp", "emacs-lisp", ".el", "gnu.jemacs.lang.ELisp" }, { "xquery", ".xquery", ".xq", ".xql", "gnu.xquery.lang.XQuery" }, { "q2", ".q2", "gnu.q2.lang.Q2" }, { "xslt", "xsl", ".xsl", "gnu.kawa.xslt.XSLT" }, { "commonlisp", "common-lisp", "clisp", "lisp", ".lisp", ".lsp", ".cl", "gnu.commonlisp.lang.CommonLisp" } };
  
  public static boolean requirePedantic;
  
  protected Environment environ;
  
  protected Environment userEnv;
  
  static {
    env_counter = 0;
  }
  
  protected Language() {
    Convert.setInstance(KawaConvert.getInstance());
  }
  
  public static Language detect(InPort paramInPort) throws IOException {
    StringBuffer stringBuffer = new StringBuffer();
    paramInPort.mark(300);
    paramInPort.readLine(stringBuffer, 'P');
    paramInPort.reset();
    return detect(stringBuffer.toString());
  }
  
  public static Language detect(InputStream paramInputStream) throws IOException {
    if (!paramInputStream.markSupported())
      return null; 
    StringBuffer stringBuffer = new StringBuffer();
    paramInputStream.mark(200);
    while (true) {
      if (stringBuffer.length() < 200) {
        int i = paramInputStream.read();
        if (i >= 0 && i != 10 && i != 13) {
          stringBuffer.append((char)i);
          continue;
        } 
      } 
      paramInputStream.reset();
      return detect(stringBuffer.toString());
    } 
  }
  
  public static Language detect(String paramString) {
    paramString = paramString.trim();
    int i = paramString.indexOf("kawa:");
    if (i >= 0) {
      int j = i + 5;
      for (i = j; i < paramString.length() && Character.isJavaIdentifierPart(paramString.charAt(i)); i++);
      if (i > j) {
        Language language = getInstance(paramString.substring(j, i));
        if (language != null)
          return language; 
      } 
    } 
    return (paramString.indexOf("-*- scheme -*-") >= 0) ? getInstance("scheme") : ((paramString.indexOf("-*- xquery -*-") >= 0) ? getInstance("xquery") : ((paramString.indexOf("-*- emacs-lisp -*-") >= 0) ? getInstance("elisp") : ((paramString.indexOf("-*- common-lisp -*-") >= 0 || paramString.indexOf("-*- lisp -*-") >= 0) ? getInstance("common-lisp") : (((paramString.charAt(0) == '(' && paramString.charAt(1) == ':') || (paramString.length() >= 7 && paramString.substring(0, 7).equals("xquery "))) ? getInstance("xquery") : ((paramString.charAt(0) == ';' && paramString.charAt(1) == ';') ? getInstance("scheme") : null)))));
  }
  
  public static Language getDefaultLanguage() {
    Language language = current.get();
    return (language != null) ? language : global;
  }
  
  public static Language getInstance(String paramString) {
    int j = languages.length;
    int i = 0;
    while (i < j) {
      String[] arrayOfString = languages[i];
      int m = arrayOfString.length - 1;
      int k = m;
      while (true) {
        int n = k - 1;
        i++;
      } 
    } 
    return null;
  }
  
  public static Language getInstance(String paramString, Class paramClass) {
    try {
      Class[] arrayOfClass = new Class[0];
      try {
        paramString = Character.toTitleCase(paramString.charAt(0)) + paramString.substring(1).toLowerCase();
        Method method = paramClass.getDeclaredMethod("get" + paramString + "Instance", arrayOfClass);
      } catch (Exception exception) {}
      return (Language)exception.invoke(null, Values.noArgs);
    } catch (Exception null) {
      Throwable throwable;
      String str = paramClass.getName();
      if (throwable instanceof InvocationTargetException)
        throwable = ((InvocationTargetException)throwable).getTargetException(); 
      throw new WrappedException("getInstance for '" + str + "' failed", throwable);
    } 
  }
  
  public static Language getInstanceFromFilenameExtension(String paramString) {
    int i = paramString.lastIndexOf('.');
    if (i > 0) {
      Language language = getInstance(paramString.substring(i));
      if (language != null)
        return language; 
    } 
    return null;
  }
  
  public static String[][] getLanguages() {
    return languages;
  }
  
  public static void registerLanguage(String[] paramArrayOfString) {
    String[][] arrayOfString = new String[languages.length + 1][];
    System.arraycopy(languages, 0, arrayOfString, 0, languages.length);
    arrayOfString[arrayOfString.length - 1] = paramArrayOfString;
    languages = arrayOfString;
  }
  
  public static void restoreCurrent(Language paramLanguage) {
    current.set(paramLanguage);
  }
  
  public static void setCurrentLanguage(Language paramLanguage) {
    current.set(paramLanguage);
  }
  
  public static void setDefaults(Language paramLanguage) {
    // Byte code:
    //   0: ldc gnu/expr/Language
    //   2: monitorenter
    //   3: aload_0
    //   4: invokestatic setCurrentLanguage : (Lgnu/expr/Language;)V
    //   7: aload_0
    //   8: putstatic gnu/expr/Language.global : Lgnu/expr/Language;
    //   11: invokestatic getGlobal : ()Lgnu/mapping/Environment;
    //   14: invokestatic getInstance : ()Lgnu/expr/BuiltinEnvironment;
    //   17: if_acmpne -> 26
    //   20: invokestatic getCurrent : ()Lgnu/mapping/Environment;
    //   23: invokestatic setGlobal : (Lgnu/mapping/Environment;)V
    //   26: ldc gnu/expr/Language
    //   28: monitorexit
    //   29: return
    //   30: astore_0
    //   31: ldc gnu/expr/Language
    //   33: monitorexit
    //   34: aload_0
    //   35: athrow
    // Exception table:
    //   from	to	target	type
    //   3	26	30	finally
  }
  
  public static Language setSaveCurrent(Language paramLanguage) {
    Language language = current.get();
    current.set(paramLanguage);
    return language;
  }
  
  public static Type string2Type(String paramString) {
    Type type;
    if (paramString.endsWith("[]")) {
      type = string2Type(paramString.substring(0, paramString.length() - 2));
      return (Type)((type == null) ? null : ArrayType.make(type));
    } 
    return Type.isValidJavaTypeName((String)type) ? Type.getType((String)type) : null;
  }
  
  public static Type unionType(Type paramType1, Type paramType2) {
    ClassType classType1;
    ClassType classType2;
    Type type = paramType1;
    if (paramType1 == Type.toStringType)
      classType2 = Type.javalangStringType; 
    paramType1 = paramType2;
    if (paramType2 == Type.toStringType)
      classType1 = Type.javalangStringType; 
    if (classType2 != classType1) {
      if (classType2 instanceof gnu.bytecode.PrimType && classType1 instanceof gnu.bytecode.PrimType) {
        char c1 = classType2.getSignature().charAt(0);
        char c2 = classType1.getSignature().charAt(0);
        if (c1 != c2) {
          if ((c1 == 'B' || c1 == 'S' || c1 == 'I') && (c2 == 'I' || c2 == 'J'))
            return (Type)classType1; 
          if ((c2 != 'B' && c2 != 'S' && c2 != 'I') || (c1 != 'I' && c1 != 'J')) {
            if (c1 == 'F' && c2 == 'D')
              return (Type)classType1; 
            if (c2 != 'F' || c1 != 'D')
              return (Type)Type.objectType; 
          } 
        } 
        return (Type)classType2;
      } 
      return (Type)Type.objectType;
    } 
    return (Type)classType2;
  }
  
  public final Type asType(Object paramObject) {
    Type type = getTypeFor(paramObject, true);
    return (type == null) ? (Type)paramObject : type;
  }
  
  public Object booleanObject(boolean paramBoolean) {
    return paramBoolean ? Boolean.TRUE : Boolean.FALSE;
  }
  
  public Object coerceFromObject(Class paramClass, Object paramObject) {
    return getTypeFor(paramClass).coerceFromObject(paramObject);
  }
  
  public Object coerceToObject(Class paramClass, Object paramObject) {
    return getTypeFor(paramClass).coerceToObject(paramObject);
  }
  
  public Declaration declFromField(ModuleExp paramModuleExp, Object paramObject, Field paramField) {
    boolean bool2;
    String str = paramField.getName();
    Type type = paramField.getType();
    boolean bool4 = type.isSubtype((Type)Compilation.typeLocation);
    boolean bool3 = false;
    boolean bool1 = false;
    if ((paramField.getModifiers() & 0x10) != 0) {
      bool2 = true;
    } else {
      bool2 = false;
    } 
    boolean bool5 = str.endsWith("$instance");
    if (bool5) {
      paramObject = str;
    } else if (bool2 && paramObject instanceof Named) {
      paramObject = ((Named)paramObject).getSymbol();
    } else {
      bool1 = bool3;
      paramObject = str;
      if (str.startsWith("$Prvt$")) {
        bool1 = true;
        paramObject = str.substring("$Prvt$".length());
      } 
      paramObject = Compilation.demangleName((String)paramObject, true).intern();
    } 
    Object object = paramObject;
    if (paramObject instanceof String) {
      object = paramModuleExp.getNamespaceUri();
      paramObject = paramObject;
      if (object == null) {
        object = SimpleSymbol.valueOf((String)paramObject);
      } else {
        object = Symbol.make(object, (String)paramObject);
      } 
    } 
    if (bool4) {
      paramObject = Type.objectType;
    } else {
      paramObject = getTypeFor(type.getReflectClass());
    } 
    Declaration declaration = paramModuleExp.addDeclaration(object, (Type)paramObject);
    if ((paramField.getModifiers() & 0x8) != 0) {
      bool3 = true;
    } else {
      bool3 = false;
    } 
    if (bool4) {
      declaration.setIndirectBinding(true);
      if (type instanceof ClassType && ((ClassType)type).isSubclass("gnu.mapping.ThreadLocation"))
        declaration.setFlag(268435456L); 
    } else if (bool2 && type instanceof ClassType) {
      if (type.isSubtype((Type)Compilation.typeProcedure)) {
        declaration.setProcedureDecl(true);
      } else if (((ClassType)type).isSubclass("gnu.mapping.Namespace")) {
        declaration.setFlag(2097152L);
      } 
    } 
    if (bool3)
      declaration.setFlag(2048L); 
    declaration.field = paramField;
    if (bool2 && !bool4)
      declaration.setFlag(16384L); 
    if (bool5)
      declaration.setFlag(1073741824L); 
    declaration.setSimple(false);
    if (bool1)
      declaration.setFlag(524320L); 
    return declaration;
  }
  
  protected void defAliasStFld(String paramString1, String paramString2, String paramString3) {
    StaticFieldLocation.define(this.environ, getSymbol(paramString1), null, paramString2, paramString3);
  }
  
  protected void defProcStFld(String paramString1, String paramString2) {
    defProcStFld(paramString1, paramString2, Compilation.mangleNameIfNeeded(paramString1));
  }
  
  protected void defProcStFld(String paramString1, String paramString2, String paramString3) {
    Object object;
    if (hasSeparateFunctionNamespace()) {
      object = EnvironmentKey.FUNCTION;
    } else {
      object = null;
    } 
    Symbol symbol = getSymbol(paramString1);
    StaticFieldLocation.define(this.environ, symbol, object, paramString2, paramString3).setProcedure();
  }
  
  public void define(String paramString, Object paramObject) {
    Symbol symbol = getSymbol(paramString);
    this.environ.define(symbol, null, paramObject);
  }
  
  public final void defineFunction(Named paramNamed) {
    Object object1;
    Object object = paramNamed.getSymbol();
    if (object instanceof Symbol) {
      object = object;
    } else {
      object = getSymbol(object.toString());
    } 
    if (hasSeparateFunctionNamespace()) {
      object1 = EnvironmentKey.FUNCTION;
    } else {
      object1 = null;
    } 
    this.environ.define((Symbol)object, object1, paramNamed);
  }
  
  public void defineFunction(String paramString, Object paramObject) {
    Object object;
    if (hasSeparateFunctionNamespace()) {
      object = EnvironmentKey.FUNCTION;
    } else {
      object = null;
    } 
    this.environ.define(getSymbol(paramString), object, paramObject);
  }
  
  public void emitCoerceToBoolean(CodeAttr paramCodeAttr) {
    emitPushBoolean(false, paramCodeAttr);
    paramCodeAttr.emitIfNEq();
    paramCodeAttr.emitPushInt(1);
    paramCodeAttr.emitElse();
    paramCodeAttr.emitPushInt(0);
    paramCodeAttr.emitFi();
  }
  
  public void emitPushBoolean(boolean paramBoolean, CodeAttr paramCodeAttr) {
    Field field;
    if (paramBoolean) {
      field = Compilation.trueConstant;
    } else {
      field = Compilation.falseConstant;
    } 
    paramCodeAttr.emitGetStatic(field);
  }
  
  public final Object eval(InPort paramInPort) throws Throwable {
    CallContext callContext = CallContext.getInstance();
    int i = callContext.startFromContext();
    try {
      eval(paramInPort, callContext);
      return callContext.getFromContext(i);
    } catch (Throwable throwable) {
      callContext.cleanupFromContext(i);
      throw throwable;
    } 
  }
  
  public final Object eval(Reader paramReader) throws Throwable {
    if (paramReader instanceof InPort) {
      inPort = (InPort)paramReader;
      return eval(inPort);
    } 
    InPort inPort = new InPort((Reader)inPort);
    return eval(inPort);
  }
  
  public final Object eval(String paramString) throws Throwable {
    return eval((InPort)new CharArrayInPort(paramString));
  }
  
  public void eval(InPort paramInPort, CallContext paramCallContext) throws Throwable {
    SourceMessages sourceMessages = new SourceMessages();
    Language language = setSaveCurrent(this);
    try {
      Compilation compilation = parse(paramInPort, sourceMessages, 3);
      ModuleExp.evalModule(getEnvironment(), paramCallContext, compilation, (URL)null, (OutPort)null);
      restoreCurrent(language);
    } finally {
      restoreCurrent(language);
    } 
  }
  
  public void eval(Reader paramReader, Consumer paramConsumer) throws Throwable {
    if (paramReader instanceof InPort) {
      null = (InPort)paramReader;
    } else {
      null = new InPort((Reader)null);
    } 
    CallContext callContext = CallContext.getInstance();
    Consumer consumer = callContext.consumer;
    try {
      callContext.consumer = paramConsumer;
      eval(null, callContext);
      return;
    } finally {
      callContext.consumer = consumer;
    } 
  }
  
  public final void eval(Reader paramReader, Writer paramWriter) throws Throwable {
    eval(paramReader, getOutputConsumer(paramWriter));
  }
  
  public final void eval(String paramString, Consumer paramConsumer) throws Throwable {
    eval((Reader)new CharArrayInPort(paramString), paramConsumer);
  }
  
  public final void eval(String paramString, PrintConsumer paramPrintConsumer) throws Throwable {
    eval(paramString, getOutputConsumer((Writer)paramPrintConsumer));
  }
  
  public final void eval(String paramString, Writer paramWriter) throws Throwable {
    eval((Reader)new CharArrayInPort(paramString), paramWriter);
  }
  
  public String formatType(Type paramType) {
    return paramType.getName();
  }
  
  public Compilation getCompilation(Lexer paramLexer, SourceMessages paramSourceMessages, NameLookup paramNameLookup) {
    return new Compilation(this, paramSourceMessages, paramNameLookup);
  }
  
  public Object getEnvPropertyFor(Declaration paramDeclaration) {
    return (hasSeparateFunctionNamespace() && paramDeclaration.isProcedureDecl()) ? EnvironmentKey.FUNCTION : null;
  }
  
  public Object getEnvPropertyFor(Field paramField, Object paramObject) {
    return (hasSeparateFunctionNamespace() && Compilation.typeProcedure.getReflectClass().isAssignableFrom(paramField.getType())) ? EnvironmentKey.FUNCTION : null;
  }
  
  public final Environment getEnvironment() {
    return (this.userEnv != null) ? this.userEnv : Environment.getCurrent();
  }
  
  public AbstractFormat getFormat(boolean paramBoolean) {
    return null;
  }
  
  public Environment getLangEnvironment() {
    return this.environ;
  }
  
  public final Type getLangTypeFor(Type paramType) {
    Type type = paramType;
    if (paramType.isExisting()) {
      Class clazz = paramType.getReflectClass();
      type = paramType;
      if (clazz != null)
        type = getTypeFor(clazz); 
    } 
    return type;
  }
  
  public abstract Lexer getLexer(InPort paramInPort, SourceMessages paramSourceMessages);
  
  public String getName() {
    String str2 = getClass().getName();
    int i = str2.lastIndexOf('.');
    String str1 = str2;
    if (i >= 0)
      str1 = str2.substring(i + 1); 
    return str1;
  }
  
  public int getNamespaceOf(Declaration paramDeclaration) {
    return 1;
  }
  
  public final Environment getNewEnvironment() {
    StringBuilder stringBuilder = (new StringBuilder()).append("environment-");
    int i = envCounter + 1;
    envCounter = i;
    return (Environment)Environment.make(stringBuilder.append(i).toString(), this.environ);
  }
  
  public Consumer getOutputConsumer(Writer paramWriter) {
    if (paramWriter instanceof OutPort) {
      outPort = (OutPort)paramWriter;
      outPort.objectFormat = getFormat(false);
      return (Consumer)outPort;
    } 
    OutPort outPort = new OutPort((Writer)outPort);
    outPort.objectFormat = getFormat(false);
    return (Consumer)outPort;
  }
  
  public Procedure getPrompter() {
    Object object = null;
    if (hasSeparateFunctionNamespace())
      object = EnvironmentKey.FUNCTION; 
    object = getEnvironment().get(getSymbol("default-prompter"), object, null);
    return (Procedure)((object != null) ? object : new SimplePrompter());
  }
  
  public Symbol getSymbol(String paramString) {
    return this.environ.getSymbol(paramString);
  }
  
  public final Type getTypeFor(Expression paramExpression) {
    return getTypeFor(paramExpression, true);
  }
  
  public Type getTypeFor(Expression paramExpression, boolean paramBoolean) {
    Object object;
    String str = null;
    if (paramExpression instanceof QuoteExp) {
      object = ((QuoteExp)paramExpression).getValue();
      return (object instanceof Type) ? (Type)object : ((object instanceof Class) ? Type.make((Class)object) : getTypeFor(object, paramBoolean));
    } 
    if (object instanceof ReferenceExp) {
      object = object;
      Declaration declaration = Declaration.followAliases(object.getBinding());
      String str1 = object.getName();
      object = str1;
      if (declaration != null) {
        Object object2;
        Expression expression = declaration.getValue();
        if (expression instanceof QuoteExp && declaration.getFlag(16384L) && !declaration.isIndirectBinding())
          return getTypeFor(((QuoteExp)expression).getValue(), paramBoolean); 
        if (expression instanceof ClassExp || expression instanceof ModuleExp) {
          declaration.setCanRead(true);
          return (Type)((LambdaExp)expression).getClassType();
        } 
        if (declaration.isAlias() && expression instanceof QuoteExp) {
          object2 = ((QuoteExp)expression).getValue();
          object = str1;
          if (object2 instanceof gnu.mapping.Location) {
            object = object2;
            if (object.isBound())
              return getTypeFor(object.get(), paramBoolean); 
            str1 = str;
            if (object instanceof Named) {
              object = ((Named)object).getName();
            } else {
              return (Type)str1;
            } 
          } 
        } else {
          object = str1;
          if (!object2.getFlag(65536L))
            return getTypeFor(expression, paramBoolean); 
        } 
      } 
      Object object1 = getEnvironment().get(object);
      if (object1 instanceof Type)
        return (Type)object1; 
      if (object1 instanceof ClassNamespace)
        return (Type)((ClassNamespace)object1).getClassType(); 
      int i = object.length();
      object1 = str;
      if (i > 2) {
        object1 = str;
        if (object.charAt(0) == '<') {
          object1 = str;
          if (object.charAt(i - 1) == '>')
            return getTypeFor(object.substring(1, i - 1)); 
        } 
      } 
      return (Type)object1;
    } 
    if (!(object instanceof ClassExp)) {
      String str1 = str;
      return (Type)((object instanceof ModuleExp) ? ((LambdaExp)object).getClassType() : str1);
    } 
    return (Type)((LambdaExp)object).getClassType();
  }
  
  public Type getTypeFor(Class paramClass) {
    return Type.make(paramClass);
  }
  
  public final Type getTypeFor(Object paramObject, boolean paramBoolean) {
    if (paramObject instanceof Type)
      return (Type)paramObject; 
    if (paramObject instanceof Class)
      return getTypeFor((Class)paramObject); 
    if (paramBoolean && (paramObject instanceof gnu.lists.FString || paramObject instanceof String || (paramObject instanceof Symbol && ((Symbol)paramObject).hasEmptyNamespace()) || paramObject instanceof gnu.lists.CharSeq))
      return getTypeFor(paramObject.toString()); 
    if (paramObject instanceof Namespace) {
      paramObject = ((Namespace)paramObject).getName();
      if (paramObject != null && paramObject.startsWith("class:"))
        return getLangTypeFor(string2Type(paramObject.substring(6))); 
    } 
    return null;
  }
  
  public Type getTypeFor(String paramString) {
    return string2Type(paramString);
  }
  
  public boolean hasNamespace(Declaration paramDeclaration, int paramInt) {
    return ((getNamespaceOf(paramDeclaration) & paramInt) != 0);
  }
  
  public boolean hasSeparateFunctionNamespace() {
    return false;
  }
  
  public boolean isTrue(Object paramObject) {
    return (paramObject != Boolean.FALSE);
  }
  
  public void loadClass(String paramString) throws ClassNotFoundException {
    try {
      Class<?> clazz = Class.forName(paramString);
      try {
        clazz = (Class<?>)clazz.newInstance();
        ClassMemberLocation.defineAll(clazz, this, Environment.getCurrent());
        if (clazz instanceof ModuleBody)
          ((ModuleBody)clazz).run(); 
        return;
      } catch (Exception exception) {
        throw new WrappedException("cannot load " + paramString, exception);
      } 
    } catch (ClassNotFoundException classNotFoundException) {
      throw classNotFoundException;
    } 
  }
  
  public Object lookup(String paramString) {
    return this.environ.get(paramString);
  }
  
  public NamedLocation lookupBuiltin(Symbol paramSymbol, Object paramObject, int paramInt) {
    return (this.environ == null) ? null : this.environ.lookup(paramSymbol, paramObject, paramInt);
  }
  
  public Object noValue() {
    return Values.empty;
  }
  
  public final Compilation parse(InPort paramInPort, SourceMessages paramSourceMessages, int paramInt) throws IOException, SyntaxException {
    return parse(getLexer(paramInPort, paramSourceMessages), paramInt, (ModuleInfo)null);
  }
  
  public final Compilation parse(InPort paramInPort, SourceMessages paramSourceMessages, int paramInt, ModuleInfo paramModuleInfo) throws IOException, SyntaxException {
    return parse(getLexer(paramInPort, paramSourceMessages), paramInt, paramModuleInfo);
  }
  
  public final Compilation parse(InPort paramInPort, SourceMessages paramSourceMessages, ModuleInfo paramModuleInfo) throws IOException, SyntaxException {
    return parse(getLexer(paramInPort, paramSourceMessages), 8, paramModuleInfo);
  }
  
  public final Compilation parse(Lexer paramLexer, int paramInt, ModuleInfo paramModuleInfo) throws IOException, SyntaxException {
    boolean bool;
    NameLookup nameLookup;
    SourceMessages sourceMessages = paramLexer.getMessages();
    if ((paramInt & 0x2) != 0) {
      nameLookup = NameLookup.getInstance(getEnvironment(), this);
    } else {
      nameLookup = new NameLookup(this);
    } 
    if ((paramInt & 0x1) != 0) {
      bool = true;
    } else {
      bool = false;
    } 
    Compilation compilation2 = getCompilation(paramLexer, sourceMessages, nameLookup);
    if (requirePedantic)
      compilation2.pedantic = true; 
    if (!bool)
      compilation2.mustCompile = true; 
    compilation2.immediate = bool;
    compilation2.langOptions = paramInt;
    if ((paramInt & 0x40) != 0)
      compilation2.explicit = true; 
    if ((paramInt & 0x8) != 0)
      compilation2.setState(1); 
    compilation2.pushNewModule(paramLexer);
    if (paramModuleInfo != null)
      paramModuleInfo.setCompilation(compilation2); 
    if (!parse(compilation2, paramInt))
      return null; 
    Compilation compilation1 = compilation2;
    if (compilation2.getState() == 1) {
      compilation2.setState(2);
      return compilation2;
    } 
    return compilation1;
  }
  
  public abstract boolean parse(Compilation paramCompilation, int paramInt) throws IOException, SyntaxException;
  
  public void resolve(Compilation paramCompilation) {}
  
  public void runAsApplication(String[] paramArrayOfString) {
    setDefaults(this);
    repl.main(paramArrayOfString);
  }
  
  static {
    Environment.setGlobal(BuiltinEnvironment.getInstance());
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/gnu/expr/Language.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */