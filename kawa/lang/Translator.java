package kawa.lang;

import gnu.bytecode.Type;
import gnu.expr.ApplyExp;
import gnu.expr.Compilation;
import gnu.expr.Declaration;
import gnu.expr.ErrorExp;
import gnu.expr.Expression;
import gnu.expr.InlineCalls;
import gnu.expr.LambdaExp;
import gnu.expr.Language;
import gnu.expr.LetExp;
import gnu.expr.ModuleExp;
import gnu.expr.ModuleInfo;
import gnu.expr.NameLookup;
import gnu.expr.QuoteExp;
import gnu.expr.ReferenceExp;
import gnu.expr.ScopeExp;
import gnu.expr.Special;
import gnu.kawa.functions.AppendValues;
import gnu.kawa.functions.CompileNamedPart;
import gnu.kawa.lispexpr.LispLanguage;
import gnu.kawa.reflect.StaticFieldLocation;
import gnu.lists.LList;
import gnu.lists.Pair;
import gnu.lists.PairWithPosition;
import gnu.mapping.Environment;
import gnu.mapping.EnvironmentKey;
import gnu.mapping.Namespace;
import gnu.mapping.Procedure;
import gnu.mapping.Symbol;
import gnu.mapping.Values;
import gnu.text.SourceLocator;
import gnu.text.SourceMessages;
import gnu.xml.NamespaceBinding;
import java.util.Stack;
import java.util.Vector;
import kawa.standard.begin;
import kawa.standard.require;

public class Translator extends Compilation {
  private static Expression errorExp;
  
  public static final Declaration getNamedPartDecl = Declaration.getDeclarationFromStatic("gnu.kawa.functions.GetNamedPart", "getNamedPart");
  
  public LambdaExp curMethodLambda;
  
  public Macro currentMacroDefinition;
  
  Syntax currentSyntax;
  
  private Environment env = Environment.getCurrent();
  
  public int firstForm;
  
  public Stack formStack = new Stack();
  
  Declaration macroContext;
  
  public Declaration matchArray;
  
  Vector notedAccess;
  
  public PatternScope patternScope;
  
  public Object pendingForm;
  
  PairWithPosition positionPair;
  
  Stack renamedAliasStack;
  
  public Declaration templateScopeDecl;
  
  public NamespaceBinding xmlElementNamespaces = NamespaceBinding.predefinedXML;
  
  static {
    LispLanguage.getNamedPartLocation.setDeclaration(getNamedPartDecl);
    errorExp = (Expression)new ErrorExp("unknown syntax error");
  }
  
  public Translator(Language paramLanguage, SourceMessages paramSourceMessages, NameLookup paramNameLookup) {
    super(paramLanguage, paramSourceMessages, paramNameLookup);
  }
  
  static ReferenceExp getOriginalRef(Declaration paramDeclaration) {
    if (paramDeclaration != null && paramDeclaration.isAlias() && !paramDeclaration.isIndirectBinding()) {
      Expression expression = paramDeclaration.getValue();
      if (expression instanceof ReferenceExp)
        return (ReferenceExp)expression; 
    } 
    return null;
  }
  
  public static int listLength(Object paramObject) {
    int i = 0;
    Object object1 = paramObject;
    Object object2 = paramObject;
    paramObject = object1;
    object1 = object2;
    while (true) {
      object2 = paramObject;
      if (object1 instanceof SyntaxForm) {
        object1 = ((SyntaxForm)object1).getDatum();
        continue;
      } 
      while (object2 instanceof SyntaxForm)
        object2 = ((SyntaxForm)object2).getDatum(); 
      if (object1 == LList.Empty)
        return i; 
      if (!(object1 instanceof Pair))
        return -1 - i; 
      i++;
      for (paramObject = ((Pair)object1).getCdr(); paramObject instanceof SyntaxForm; paramObject = ((SyntaxForm)paramObject).getDatum());
      if (paramObject == LList.Empty)
        return i; 
      if (!(paramObject instanceof Pair))
        return -1 - i; 
      object2 = ((Pair)object2).getCdr();
      Object object = ((Pair)paramObject).getCdr();
      i++;
      object1 = object;
      paramObject = object2;
      if (object == object2)
        return Integer.MIN_VALUE; 
    } 
  }
  
  private Expression makeBody(int paramInt, ScopeExp paramScopeExp) {
    int j = this.formStack.size() - paramInt;
    if (j == 0)
      return (Expression)QuoteExp.voidExp; 
    if (j == 1)
      return this.formStack.pop(); 
    Expression[] arrayOfExpression = new Expression[j];
    for (int i = 0; i < j; i++)
      arrayOfExpression[i] = this.formStack.elementAt(paramInt + i); 
    this.formStack.setSize(paramInt);
    return (Expression)((paramScopeExp instanceof ModuleExp) ? new ApplyExp((Procedure)AppendValues.appendValues, arrayOfExpression) : ((LispLanguage)getLanguage()).makeBody(arrayOfExpression));
  }
  
  public static Pair makePair(Pair paramPair, Object paramObject1, Object paramObject2) {
    return (Pair)((paramPair instanceof PairWithPosition) ? new PairWithPosition((SourceLocator)paramPair, paramObject1, paramObject2) : new Pair(paramObject1, paramObject2));
  }
  
  private void rewriteBody(LList paramLList) {
    while (paramLList != LList.Empty) {
      null = (Pair)paramLList;
      Object object = pushPositionOf(null);
      try {
        rewriteInBody(null.getCar());
        popPositionOf(object);
      } finally {
        popPositionOf(object);
      } 
    } 
  }
  
  public static Object safeCar(Object paramObject) {
    while (paramObject instanceof SyntaxForm)
      paramObject = ((SyntaxForm)paramObject).getDatum(); 
    return !(paramObject instanceof Pair) ? null : stripSyntax(((Pair)paramObject).getCar());
  }
  
  public static Object safeCdr(Object paramObject) {
    while (paramObject instanceof SyntaxForm)
      paramObject = ((SyntaxForm)paramObject).getDatum(); 
    return !(paramObject instanceof Pair) ? null : stripSyntax(((Pair)paramObject).getCdr());
  }
  
  public static void setLine(Declaration paramDeclaration, Object paramObject) {
    if (paramObject instanceof SourceLocator)
      paramDeclaration.setLocation((SourceLocator)paramObject); 
  }
  
  public static void setLine(Expression paramExpression, Object paramObject) {
    if (paramObject instanceof SourceLocator)
      paramExpression.setLocation((SourceLocator)paramObject); 
  }
  
  public static Object stripSyntax(Object paramObject) {
    while (paramObject instanceof SyntaxForm)
      paramObject = ((SyntaxForm)paramObject).getDatum(); 
    return paramObject;
  }
  
  static void vectorReverse(Vector<Object> paramVector, int paramInt1, int paramInt2) {
    int i = paramInt2 / 2;
    int j = paramInt1 + paramInt2 - 1;
    for (paramInt2 = 0; paramInt2 < i; paramInt2++) {
      Object object = paramVector.elementAt(paramInt1 + paramInt2);
      paramVector.setElementAt(paramVector.elementAt(j - paramInt2), paramInt1 + paramInt2);
      paramVector.setElementAt(object, j - paramInt2);
    } 
  }
  
  public static Object wrapSyntax(Object paramObject, SyntaxForm paramSyntaxForm) {
    return (paramSyntaxForm == null || paramObject instanceof Expression) ? paramObject : SyntaxForms.fromDatumIfNeeded(paramObject, paramSyntaxForm);
  }
  
  Expression apply_rewrite(Syntax paramSyntax, Pair paramPair) {
    Expression expression = errorExp;
    Syntax syntax = this.currentSyntax;
    this.currentSyntax = paramSyntax;
    try {
      return paramSyntax.rewriteForm(paramPair, this);
    } finally {
      this.currentSyntax = syntax;
    } 
  }
  
  Syntax check_if_Syntax(Declaration paramDeclaration) {
    Declaration declaration = Declaration.followAliases(paramDeclaration);
    Throwable throwable = null;
    Expression expression = declaration.getValue();
    if (expression != null && declaration.getFlag(32768L)) {
      try {
        if (paramDeclaration.getValue() instanceof ReferenceExp) {
          declaration = ((ReferenceExp)paramDeclaration.getValue()).contextDecl();
          if (declaration != null) {
            this.macroContext = declaration;
          } else if (this.current_scope instanceof TemplateScope) {
            this.macroContext = ((TemplateScope)this.current_scope).macroContext;
          } 
        } else if (this.current_scope instanceof TemplateScope) {
          this.macroContext = ((TemplateScope)this.current_scope).macroContext;
        } 
        Object object = expression.eval(this.env);
        if (object instanceof Syntax)
          return (Syntax)object; 
      } catch (Throwable throwable1) {
        throwable1.printStackTrace();
        error('e', "unable to evaluate macro for " + paramDeclaration.getSymbol());
        throwable1 = throwable;
        if (throwable1 instanceof Syntax)
          return (Syntax)throwable1; 
      } 
    } else {
      Object object = throwable;
      if (paramDeclaration.getFlag(32768L)) {
        object = throwable;
        if (!paramDeclaration.needsContext())
          object = StaticFieldLocation.make(paramDeclaration).get(null); 
      } 
      if (object instanceof Syntax)
        return (Syntax)object; 
    } 
    return null;
  }
  
  public Declaration define(Object paramObject, SyntaxForm paramSyntaxForm, ScopeExp paramScopeExp) {
    boolean bool;
    Object object;
    if (paramSyntaxForm != null && paramSyntaxForm.getScope() != currentScope()) {
      bool = true;
    } else {
      bool = false;
    } 
    if (bool) {
      object = new String(paramObject.toString());
    } else {
      object = paramObject;
    } 
    Declaration declaration = paramScopeExp.getDefine(object, 'w', this);
    if (bool) {
      paramObject = makeRenamedAlias(paramObject, declaration, (ScopeExp)paramSyntaxForm.getScope());
      paramSyntaxForm.getScope().addDeclaration((Declaration)paramObject);
    } 
    push(declaration);
    return declaration;
  }
  
  public Type exp2Type(Pair paramPair) {
    Object object = pushPositionOf(paramPair);
    try {
      Expression expression = InlineCalls.inlineCalls(rewrite_car(paramPair, false), this);
      boolean bool = expression instanceof ErrorExp;
      if (bool)
        return null; 
      Type type2 = getLanguage().getTypeFor(expression);
      Type type1 = type2;
      if (type2 == null)
        try {
          Object object1 = expression.eval(this.env);
          if (object1 instanceof Class) {
            type1 = Type.make((Class)object1);
          } else {
            type1 = type2;
            if (object1 instanceof Type)
              type1 = (Type)object1; 
          } 
        } catch (Throwable throwable) {
          type1 = type2;
        }  
    } finally {
      popPositionOf(object);
    } 
    popPositionOf(object);
    return (Type)paramPair;
  }
  
  public void finishModule(ModuleExp paramModuleExp) {
    boolean bool = paramModuleExp.isStatic();
    for (Declaration declaration = paramModuleExp.firstDecl(); declaration != null; declaration = declaration.nextDecl()) {
      if (declaration.getFlag(512L)) {
        String str;
        if (declaration.getFlag(1024L)) {
          str = "' exported but never defined";
        } else if (declaration.getFlag(2048L)) {
          str = "' declared static but never defined";
        } else {
          str = "' declared but never defined";
        } 
        error('e', declaration, "'", str);
      } 
      if (paramModuleExp.getFlag(16384) || (this.generateMain && !this.immediate))
        if (declaration.getFlag(1024L)) {
          if (declaration.isPrivate()) {
            if (declaration.getFlag(16777216L))
              error('e', declaration, "'", "' is declared both private and exported"); 
            declaration.setPrivate(false);
          } 
        } else {
          declaration.setPrivate(true);
        }  
      if (bool) {
        declaration.setFlag(2048L);
      } else if ((paramModuleExp.getFlag(65536) && !declaration.getFlag(2048L)) || Compilation.moduleStatic < 0 || paramModuleExp.getFlag(131072)) {
        declaration.setFlag(4096L);
      } 
    } 
  }
  
  public Syntax getCurrentSyntax() {
    return this.currentSyntax;
  }
  
  public final Environment getGlobalEnvironment() {
    return this.env;
  }
  
  public Declaration lookup(Object paramObject, int paramInt) {
    Declaration declaration = this.lexical.lookup(paramObject, paramInt);
    return (declaration != null && getLanguage().hasNamespace(declaration, paramInt)) ? declaration : currentModule().lookup(paramObject, getLanguage(), paramInt);
  }
  
  public Declaration lookupGlobal(Object paramObject) {
    return lookupGlobal(paramObject, -1);
  }
  
  public Declaration lookupGlobal(Object paramObject, int paramInt) {
    ModuleExp moduleExp = currentModule();
    Declaration declaration2 = moduleExp.lookup(paramObject, getLanguage(), paramInt);
    Declaration declaration1 = declaration2;
    if (declaration2 == null) {
      declaration1 = moduleExp.getNoDefine(paramObject);
      declaration1.setIndirectBinding(true);
    } 
    return declaration1;
  }
  
  public Declaration makeRenamedAlias(Declaration paramDeclaration, ScopeExp paramScopeExp) {
    return (paramScopeExp == null) ? paramDeclaration : makeRenamedAlias(paramDeclaration.getSymbol(), paramDeclaration, paramScopeExp);
  }
  
  public Declaration makeRenamedAlias(Object paramObject, Declaration paramDeclaration, ScopeExp paramScopeExp) {
    paramObject = new Declaration(paramObject);
    paramObject.setAlias(true);
    paramObject.setPrivate(true);
    ((Declaration)paramObject).context = paramScopeExp;
    ReferenceExp referenceExp = new ReferenceExp(paramDeclaration);
    referenceExp.setDontDereference(true);
    paramObject.noteValue((Expression)referenceExp);
    return (Declaration)paramObject;
  }
  
  public Object matchQuoted(Pair paramPair) {
    if (matches(paramPair.getCar(), "quote") && paramPair.getCdr() instanceof Pair) {
      paramPair = (Pair)paramPair.getCdr();
      if (paramPair.getCdr() == LList.Empty)
        return paramPair.getCar(); 
    } 
    return null;
  }
  
  public final boolean matches(Object paramObject, String paramString) {
    return matches(paramObject, (SyntaxForm)null, paramString);
  }
  
  public boolean matches(Object paramObject, SyntaxForm paramSyntaxForm, Symbol paramSymbol) {
    if (paramSyntaxForm != null);
    Object object = paramObject;
    if (paramObject instanceof SyntaxForm)
      object = ((SyntaxForm)paramObject).getDatum(); 
    paramObject = object;
    if (object instanceof gnu.mapping.SimpleSymbol) {
      paramObject = object;
      if (!selfEvaluatingSymbol(object)) {
        ReferenceExp referenceExp = getOriginalRef(this.lexical.lookup(object, -1));
        paramObject = object;
        if (referenceExp != null)
          paramObject = referenceExp.getSymbol(); 
      } 
    } 
    return (paramObject == paramSymbol);
  }
  
  public boolean matches(Object paramObject, SyntaxForm paramSyntaxForm, String paramString) {
    if (paramSyntaxForm != null);
    Object object = paramObject;
    if (paramObject instanceof SyntaxForm)
      object = ((SyntaxForm)paramObject).getDatum(); 
    paramObject = object;
    if (object instanceof gnu.mapping.SimpleSymbol) {
      paramObject = object;
      if (!selfEvaluatingSymbol(object)) {
        ReferenceExp referenceExp = getOriginalRef(this.lexical.lookup(object, -1));
        paramObject = object;
        if (referenceExp != null)
          paramObject = referenceExp.getSymbol(); 
      } 
    } 
    return (paramObject instanceof gnu.mapping.SimpleSymbol && ((Symbol)paramObject).getLocalPart() == paramString);
  }
  
  public Symbol namespaceResolve(Expression paramExpression1, Expression paramExpression2) {
    return namespaceResolve(namespaceResolvePrefix(paramExpression1), paramExpression2);
  }
  
  public Symbol namespaceResolve(Namespace paramNamespace, Expression paramExpression) {
    return (paramNamespace != null && paramExpression instanceof QuoteExp) ? paramNamespace.getSymbol(((QuoteExp)paramExpression).getValue().toString().intern()) : null;
  }
  
  public Object namespaceResolve(Object paramObject) {
    if (!(paramObject instanceof gnu.mapping.SimpleSymbol) && paramObject instanceof Pair) {
      Pair pair = (Pair)paramObject;
      if (safeCar(pair) == LispLanguage.lookup_sym && pair.getCdr() instanceof Pair) {
        Pair pair1 = (Pair)pair.getCdr();
        if (pair1.getCdr() instanceof Pair) {
          Expression expression1 = rewrite(pair1.getCar());
          Expression expression2 = rewrite(((Pair)pair1.getCdr()).getCar());
          Symbol symbol = namespaceResolve(expression1, expression2);
          if (symbol != null)
            return symbol; 
          String str = CompileNamedPart.combineName(expression1, expression2);
          if (str != null)
            return Namespace.EmptyNamespace.getSymbol(str); 
        } 
      } 
    } 
    return paramObject;
  }
  
  public Namespace namespaceResolvePrefix(Expression paramExpression) {
    if (paramExpression instanceof ReferenceExp) {
      ReferenceExp referenceExp = (ReferenceExp)paramExpression;
      Declaration declaration = referenceExp.getBinding();
      if (declaration == null || declaration.getFlag(65536L)) {
        Object object = referenceExp.getSymbol();
        if (object instanceof Symbol) {
          object = object;
        } else {
          object = this.env.getSymbol(object.toString());
        } 
        object = this.env.get((EnvironmentKey)object, null);
      } else if (declaration.isNamespaceDecl()) {
        Object object = declaration.getConstantValue();
      } else {
        referenceExp = null;
      } 
      if (referenceExp instanceof Namespace) {
        Namespace namespace2 = (Namespace)referenceExp;
        String str = namespace2.getName();
        Namespace namespace1 = namespace2;
        if (str != null) {
          namespace1 = namespace2;
          if (str.startsWith("class:"))
            namespace1 = null; 
        } 
        return namespace1;
      } 
    } 
    return null;
  }
  
  public void noteAccess(Object paramObject, ScopeExp paramScopeExp) {
    if (this.notedAccess == null)
      this.notedAccess = new Vector(); 
    this.notedAccess.addElement(paramObject);
    this.notedAccess.addElement(paramScopeExp);
  }
  
  public Expression parse(Object paramObject) {
    return rewrite(paramObject);
  }
  
  public Object popForms(int paramInt) {
    int j = this.formStack.size();
    if (j == paramInt)
      return Values.empty; 
    if (j == paramInt + 1) {
      Object object = this.formStack.elementAt(paramInt);
      this.formStack.setSize(paramInt);
      return object;
    } 
    Values values = new Values();
    int i = paramInt;
    while (true) {
      if (i < j) {
        values.writeObject(this.formStack.elementAt(i));
        i++;
        continue;
      } 
      this.formStack.setSize(paramInt);
      return values;
    } 
  }
  
  public void popPositionOf(Object paramObject) {
    if (paramObject != null) {
      setLine(paramObject);
      this.positionPair = (PairWithPosition)paramObject;
      if (this.positionPair.getCar() == Special.eof) {
        this.positionPair = (PairWithPosition)this.positionPair.getCdr();
        return;
      } 
    } 
  }
  
  public void popRenamedAlias(int paramInt) {
    while (true) {
      int i = paramInt - 1;
      if (i >= 0) {
        ScopeExp scopeExp = this.renamedAliasStack.pop();
        Declaration declaration = this.renamedAliasStack.pop();
        getOriginalRef(declaration).getBinding().setSymbol(declaration.getSymbol());
        scopeExp.remove(declaration);
        declaration = this.renamedAliasStack.pop();
        paramInt = i;
        if (declaration != null) {
          scopeExp.addDeclaration(declaration);
          paramInt = i;
        } 
        continue;
      } 
      break;
    } 
  }
  
  public void processAccesses() {
    if (this.notedAccess != null) {
      int j = this.notedAccess.size();
      ScopeExp scopeExp = this.current_scope;
      for (int i = 0; i < j; i += 2) {
        Declaration declaration = (Declaration)this.notedAccess.elementAt(i);
        ScopeExp scopeExp1 = this.notedAccess.elementAt(i + 1);
        if (this.current_scope != scopeExp1)
          setCurrentScope(scopeExp1); 
        declaration = this.lexical.lookup(declaration, -1);
        if (declaration != null && !declaration.getFlag(65536L)) {
          declaration.getContext().currentLambda().capture(declaration);
          declaration.setCanRead(true);
          declaration.setSimple(false);
          declaration.setFlag(524288L);
        } 
      } 
      if (this.current_scope != scopeExp) {
        setCurrentScope(scopeExp);
        return;
      } 
    } 
  }
  
  public Object pushPositionOf(Object paramObject) {
    Object object = paramObject;
    if (paramObject instanceof SyntaxForm)
      object = ((SyntaxForm)paramObject).getDatum(); 
    if (!(object instanceof PairWithPosition))
      return null; 
    PairWithPosition pairWithPosition = (PairWithPosition)object;
    if (this.positionPair == null || this.positionPair.getFileName() != getFileName() || this.positionPair.getLineNumber() != getLineNumber() || this.positionPair.getColumnNumber() != getColumnNumber()) {
      paramObject = new PairWithPosition((SourceLocator)this, Special.eof, this.positionPair);
      setLine(object);
      this.positionPair = pairWithPosition;
      return paramObject;
    } 
    paramObject = this.positionPair;
    setLine(object);
    this.positionPair = pairWithPosition;
    return paramObject;
  }
  
  public void pushRenamedAlias(Declaration paramDeclaration) {
    Declaration declaration = getOriginalRef(paramDeclaration).getBinding();
    ScopeExp scopeExp = paramDeclaration.context;
    declaration.setSymbol(null);
    declaration = scopeExp.lookup(declaration.getSymbol());
    if (declaration != null)
      scopeExp.remove(declaration); 
    scopeExp.addDeclaration(paramDeclaration);
    if (this.renamedAliasStack == null)
      this.renamedAliasStack = new Stack(); 
    this.renamedAliasStack.push(declaration);
    this.renamedAliasStack.push(paramDeclaration);
    this.renamedAliasStack.push(scopeExp);
  }
  
  public void resolveModule(ModuleExp paramModuleExp) {
    int i;
    if (this.pendingImports == null) {
      i = 0;
    } else {
      i = this.pendingImports.size();
    } 
    int j = 0;
    while (j < i) {
      Stack<ModuleInfo> stack = this.pendingImports;
      int k = j + 1;
      ModuleInfo moduleInfo = stack.elementAt(j);
      Stack<ScopeExp> stack1 = this.pendingImports;
      j = k + 1;
      ScopeExp scopeExp = stack1.elementAt(k);
      Stack<Expression> stack2 = this.pendingImports;
      int m = j + 1;
      Expression expression = stack2.elementAt(j);
      Stack<Integer> stack3 = this.pendingImports;
      k = m + 1;
      Integer integer = stack3.elementAt(m);
      j = k;
      if (paramModuleExp == scopeExp) {
        ReferenceExp referenceExp = new ReferenceExp(null);
        referenceExp.setLine(this);
        setLine(expression);
        j = this.formStack.size();
        require.importDefinitions(null, moduleInfo, null, this.formStack, scopeExp, this);
        m = integer.intValue();
        if (integer.intValue() != j) {
          int n = this.formStack.size();
          vectorReverse(this.formStack, m, j - m);
          vectorReverse(this.formStack, j, n - j);
          vectorReverse(this.formStack, m, n - m);
        } 
        setLine((Expression)referenceExp);
        j = k;
      } 
    } 
    this.pendingImports = null;
    processAccesses();
    setModule(paramModuleExp);
    Compilation compilation = Compilation.setSaveCurrent(this);
    try {
      rewriteInBody(popForms(this.firstForm));
      paramModuleExp.body = makeBody(this.firstForm, (ScopeExp)paramModuleExp);
      if (!this.immediate)
        this.lexical.pop((ScopeExp)paramModuleExp); 
      return;
    } finally {
      Compilation.restoreCurrent(compilation);
    } 
  }
  
  public Expression rewrite(Object paramObject) {
    return rewrite(paramObject, false);
  }
  
  public Expression rewrite(Object paramObject, boolean paramBoolean) {
    // Byte code:
    //   0: aload_1
    //   1: instanceof kawa/lang/SyntaxForm
    //   4: ifeq -> 61
    //   7: aload_1
    //   8: checkcast kawa/lang/SyntaxForm
    //   11: astore #8
    //   13: aload_0
    //   14: getfield current_scope : Lgnu/expr/ScopeExp;
    //   17: astore_1
    //   18: aload_0
    //   19: aload #8
    //   21: invokeinterface getScope : ()Lkawa/lang/TemplateScope;
    //   26: invokevirtual setCurrentScope : (Lgnu/expr/ScopeExp;)V
    //   29: aload_0
    //   30: aload #8
    //   32: invokeinterface getDatum : ()Ljava/lang/Object;
    //   37: iload_2
    //   38: invokevirtual rewrite : (Ljava/lang/Object;Z)Lgnu/expr/Expression;
    //   41: astore #8
    //   43: aload_0
    //   44: aload_1
    //   45: invokevirtual setCurrentScope : (Lgnu/expr/ScopeExp;)V
    //   48: aload #8
    //   50: areturn
    //   51: astore #8
    //   53: aload_0
    //   54: aload_1
    //   55: invokevirtual setCurrentScope : (Lgnu/expr/ScopeExp;)V
    //   58: aload #8
    //   60: athrow
    //   61: aload_1
    //   62: instanceof gnu/lists/PairWithPosition
    //   65: ifeq -> 79
    //   68: aload_0
    //   69: aload_1
    //   70: iload_2
    //   71: aload_1
    //   72: checkcast gnu/lists/PairWithPosition
    //   75: invokevirtual rewrite_with_position : (Ljava/lang/Object;ZLgnu/lists/PairWithPosition;)Lgnu/expr/Expression;
    //   78: areturn
    //   79: aload_1
    //   80: instanceof gnu/lists/Pair
    //   83: ifeq -> 96
    //   86: aload_0
    //   87: aload_1
    //   88: checkcast gnu/lists/Pair
    //   91: iload_2
    //   92: invokevirtual rewrite_pair : (Lgnu/lists/Pair;Z)Lgnu/expr/Expression;
    //   95: areturn
    //   96: aload_1
    //   97: instanceof gnu/mapping/Symbol
    //   100: ifeq -> 1096
    //   103: aload_0
    //   104: aload_1
    //   105: invokevirtual selfEvaluatingSymbol : (Ljava/lang/Object;)Z
    //   108: ifne -> 1096
    //   111: aload_0
    //   112: getfield lexical : Lgnu/expr/NameLookup;
    //   115: aload_1
    //   116: iload_2
    //   117: invokevirtual lookup : (Ljava/lang/Object;Z)Lgnu/expr/Declaration;
    //   120: astore #9
    //   122: aconst_null
    //   123: astore #14
    //   125: aconst_null
    //   126: astore #13
    //   128: aload_0
    //   129: getfield current_scope : Lgnu/expr/ScopeExp;
    //   132: astore #8
    //   134: aload #9
    //   136: ifnonnull -> 370
    //   139: iconst_m1
    //   140: istore #4
    //   142: aload_1
    //   143: instanceof gnu/mapping/Symbol
    //   146: ifeq -> 383
    //   149: aload_1
    //   150: checkcast gnu/mapping/Symbol
    //   153: invokevirtual hasEmptyNamespace : ()Z
    //   156: ifeq -> 383
    //   159: aload_1
    //   160: invokevirtual toString : ()Ljava/lang/String;
    //   163: astore #10
    //   165: aload #8
    //   167: ifnull -> 213
    //   170: aload #8
    //   172: instanceof gnu/expr/LambdaExp
    //   175: ifeq -> 492
    //   178: aload #8
    //   180: getfield outer : Lgnu/expr/ScopeExp;
    //   183: instanceof gnu/expr/ClassExp
    //   186: ifeq -> 492
    //   189: aload #8
    //   191: checkcast gnu/expr/LambdaExp
    //   194: invokevirtual isClassMethod : ()Z
    //   197: ifeq -> 492
    //   200: iload #4
    //   202: aload #8
    //   204: getfield outer : Lgnu/expr/ScopeExp;
    //   207: invokestatic nesting : (Lgnu/expr/ScopeExp;)I
    //   210: if_icmplt -> 392
    //   213: aload #9
    //   215: ifnull -> 564
    //   218: aload #9
    //   220: invokevirtual getSymbol : ()Ljava/lang/Object;
    //   223: astore #11
    //   225: aconst_null
    //   226: astore #12
    //   228: aload #9
    //   230: invokestatic getOriginalRef : (Lgnu/expr/Declaration;)Lgnu/expr/ReferenceExp;
    //   233: astore #15
    //   235: aload #11
    //   237: astore_1
    //   238: aload #12
    //   240: astore #8
    //   242: aload #15
    //   244: ifnull -> 284
    //   247: aload #15
    //   249: invokevirtual getBinding : ()Lgnu/expr/Declaration;
    //   252: astore #10
    //   254: aload #10
    //   256: astore #9
    //   258: aload #11
    //   260: astore_1
    //   261: aload #12
    //   263: astore #8
    //   265: aload #10
    //   267: ifnonnull -> 284
    //   270: aload #15
    //   272: invokevirtual getSymbol : ()Ljava/lang/Object;
    //   275: astore #8
    //   277: aload #8
    //   279: astore_1
    //   280: aload #10
    //   282: astore #9
    //   284: aload #8
    //   286: astore #11
    //   288: aload_1
    //   289: astore #10
    //   291: aload #11
    //   293: checkcast gnu/mapping/Symbol
    //   296: astore #15
    //   298: aload_0
    //   299: invokevirtual getLanguage : ()Lgnu/expr/Language;
    //   302: invokevirtual hasSeparateFunctionNamespace : ()Z
    //   305: istore #6
    //   307: aload #9
    //   309: ifnull -> 676
    //   312: aload_0
    //   313: getfield current_scope : Lgnu/expr/ScopeExp;
    //   316: instanceof kawa/lang/TemplateScope
    //   319: ifeq -> 573
    //   322: aload #9
    //   324: invokevirtual needsContext : ()Z
    //   327: ifeq -> 573
    //   330: aload_0
    //   331: getfield current_scope : Lgnu/expr/ScopeExp;
    //   334: checkcast kawa/lang/TemplateScope
    //   337: getfield macroContext : Lgnu/expr/Declaration;
    //   340: astore #8
    //   342: aload #9
    //   344: astore_1
    //   345: aload_1
    //   346: ifnull -> 1057
    //   349: iload_2
    //   350: ifne -> 1013
    //   353: aload_1
    //   354: invokevirtual getConstantValue : ()Ljava/lang/Object;
    //   357: instanceof kawa/standard/object
    //   360: ifeq -> 1013
    //   363: ldc_w java/lang/Object
    //   366: invokestatic getInstance : (Ljava/lang/Object;)Lgnu/expr/QuoteExp;
    //   369: areturn
    //   370: aload #9
    //   372: getfield context : Lgnu/expr/ScopeExp;
    //   375: invokestatic nesting : (Lgnu/expr/ScopeExp;)I
    //   378: istore #4
    //   380: goto -> 142
    //   383: aconst_null
    //   384: astore #10
    //   386: aconst_null
    //   387: astore #8
    //   389: goto -> 165
    //   392: aload #8
    //   394: checkcast gnu/expr/LambdaExp
    //   397: astore #11
    //   399: aload #8
    //   401: getfield outer : Lgnu/expr/ScopeExp;
    //   404: checkcast gnu/expr/ClassExp
    //   407: astore #12
    //   409: aload #12
    //   411: invokevirtual getClassType : ()Lgnu/bytecode/ClassType;
    //   414: astore #15
    //   416: aload #15
    //   418: aload #10
    //   420: aload #15
    //   422: invokestatic lookupMember : (Lgnu/bytecode/ObjectType;Ljava/lang/String;Lgnu/bytecode/ClassType;)Lgnu/bytecode/Member;
    //   425: astore #16
    //   427: aload #11
    //   429: aload #12
    //   431: getfield clinitMethod : Lgnu/expr/LambdaExp;
    //   434: if_acmpeq -> 458
    //   437: aload #11
    //   439: aload #12
    //   441: getfield initMethod : Lgnu/expr/LambdaExp;
    //   444: if_acmpeq -> 502
    //   447: aload #11
    //   449: getfield nameDecl : Lgnu/expr/Declaration;
    //   452: invokevirtual isStatic : ()Z
    //   455: ifeq -> 502
    //   458: iconst_1
    //   459: istore #5
    //   461: aload #16
    //   463: ifnonnull -> 514
    //   466: iload #5
    //   468: ifeq -> 508
    //   471: bipush #83
    //   473: istore_3
    //   474: aload #15
    //   476: aload #10
    //   478: iload_3
    //   479: aload #15
    //   481: aload_0
    //   482: getfield language : Lgnu/expr/Language;
    //   485: invokestatic getMethods : (Lgnu/bytecode/ObjectType;Ljava/lang/String;CLgnu/bytecode/ClassType;Lgnu/expr/Language;)[Lgnu/expr/PrimProcedure;
    //   488: arraylength
    //   489: ifne -> 514
    //   492: aload #8
    //   494: getfield outer : Lgnu/expr/ScopeExp;
    //   497: astore #8
    //   499: goto -> 165
    //   502: iconst_0
    //   503: istore #5
    //   505: goto -> 461
    //   508: bipush #86
    //   510: istore_3
    //   511: goto -> 474
    //   514: iload #5
    //   516: ifeq -> 548
    //   519: new gnu/expr/ReferenceExp
    //   522: dup
    //   523: aload #11
    //   525: getfield outer : Lgnu/expr/ScopeExp;
    //   528: checkcast gnu/expr/ClassExp
    //   531: getfield nameDecl : Lgnu/expr/Declaration;
    //   534: invokespecial <init> : (Lgnu/expr/Declaration;)V
    //   537: astore_1
    //   538: aload_1
    //   539: aload #10
    //   541: invokestatic getInstance : (Ljava/lang/Object;)Lgnu/expr/QuoteExp;
    //   544: invokestatic makeExp : (Lgnu/expr/Expression;Lgnu/expr/Expression;)Lgnu/expr/Expression;
    //   547: areturn
    //   548: new gnu/expr/ThisExp
    //   551: dup
    //   552: aload #11
    //   554: invokevirtual firstDecl : ()Lgnu/expr/Declaration;
    //   557: invokespecial <init> : (Lgnu/expr/Declaration;)V
    //   560: astore_1
    //   561: goto -> 538
    //   564: aload_1
    //   565: astore #10
    //   567: aload_1
    //   568: astore #11
    //   570: goto -> 291
    //   573: aload #13
    //   575: astore #8
    //   577: aload #9
    //   579: astore_1
    //   580: aload #9
    //   582: ldc2_w 1048576
    //   585: invokevirtual getFlag : (J)Z
    //   588: ifeq -> 345
    //   591: aload #13
    //   593: astore #8
    //   595: aload #9
    //   597: astore_1
    //   598: aload #9
    //   600: invokevirtual isStatic : ()Z
    //   603: ifne -> 345
    //   606: aload_0
    //   607: invokevirtual currentScope : ()Lgnu/expr/ScopeExp;
    //   610: astore_1
    //   611: aload_1
    //   612: ifnonnull -> 644
    //   615: new java/lang/Error
    //   618: dup
    //   619: new java/lang/StringBuilder
    //   622: dup
    //   623: invokespecial <init> : ()V
    //   626: ldc_w 'internal error: missing '
    //   629: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   632: aload #9
    //   634: invokevirtual append : (Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   637: invokevirtual toString : ()Ljava/lang/String;
    //   640: invokespecial <init> : (Ljava/lang/String;)V
    //   643: athrow
    //   644: aload_1
    //   645: getfield outer : Lgnu/expr/ScopeExp;
    //   648: aload #9
    //   650: getfield context : Lgnu/expr/ScopeExp;
    //   653: if_acmpne -> 668
    //   656: aload_1
    //   657: invokevirtual firstDecl : ()Lgnu/expr/Declaration;
    //   660: astore #8
    //   662: aload #9
    //   664: astore_1
    //   665: goto -> 345
    //   668: aload_1
    //   669: getfield outer : Lgnu/expr/ScopeExp;
    //   672: astore_1
    //   673: goto -> 611
    //   676: aload_0
    //   677: getfield env : Lgnu/mapping/Environment;
    //   680: astore #8
    //   682: iload_2
    //   683: ifeq -> 1169
    //   686: iload #6
    //   688: ifeq -> 1169
    //   691: getstatic gnu/mapping/EnvironmentKey.FUNCTION : Ljava/lang/Object;
    //   694: astore_1
    //   695: aload #8
    //   697: aload #15
    //   699: aload_1
    //   700: invokevirtual lookup : (Lgnu/mapping/Symbol;Ljava/lang/Object;)Lgnu/mapping/Location;
    //   703: astore_1
    //   704: aload_1
    //   705: astore #12
    //   707: aload_1
    //   708: ifnull -> 717
    //   711: aload_1
    //   712: invokevirtual getBase : ()Lgnu/mapping/Location;
    //   715: astore #12
    //   717: aload #12
    //   719: instanceof gnu/kawa/reflect/FieldLocation
    //   722: ifeq -> 963
    //   725: aload #12
    //   727: checkcast gnu/kawa/reflect/FieldLocation
    //   730: astore #12
    //   732: aload #12
    //   734: invokevirtual getDeclaration : ()Lgnu/expr/Declaration;
    //   737: astore #9
    //   739: aload_0
    //   740: aconst_null
    //   741: invokevirtual inlineOk : (Lgnu/expr/Expression;)Z
    //   744: ifne -> 789
    //   747: aload #9
    //   749: getstatic kawa/lang/Translator.getNamedPartDecl : Lgnu/expr/Declaration;
    //   752: if_acmpeq -> 789
    //   755: ldc_w 'objectSyntax'
    //   758: aload #12
    //   760: invokevirtual getMemberName : ()Ljava/lang/String;
    //   763: invokevirtual equals : (Ljava/lang/Object;)Z
    //   766: ifeq -> 1160
    //   769: ldc_w 'kawa.standard.object'
    //   772: aload #12
    //   774: invokevirtual getDeclaringClass : ()Lgnu/bytecode/ClassType;
    //   777: invokevirtual getName : ()Ljava/lang/String;
    //   780: invokevirtual equals : (Ljava/lang/Object;)Z
    //   783: ifne -> 789
    //   786: goto -> 1160
    //   789: aload_0
    //   790: getfield immediate : Z
    //   793: ifeq -> 847
    //   796: aload #13
    //   798: astore #8
    //   800: aload #9
    //   802: astore_1
    //   803: aload #9
    //   805: invokevirtual isStatic : ()Z
    //   808: ifne -> 345
    //   811: new gnu/expr/Declaration
    //   814: dup
    //   815: ldc_w '(module-instance)'
    //   818: invokespecial <init> : (Ljava/lang/Object;)V
    //   821: astore_1
    //   822: aload_1
    //   823: new gnu/expr/QuoteExp
    //   826: dup
    //   827: aload #12
    //   829: invokevirtual getInstance : ()Ljava/lang/Object;
    //   832: invokespecial <init> : (Ljava/lang/Object;)V
    //   835: invokevirtual setValue : (Lgnu/expr/Expression;)V
    //   838: aload_1
    //   839: astore #8
    //   841: aload #9
    //   843: astore_1
    //   844: goto -> 345
    //   847: aload #9
    //   849: invokevirtual isStatic : ()Z
    //   852: ifeq -> 904
    //   855: aload #12
    //   857: invokevirtual getRClass : ()Ljava/lang/Class;
    //   860: astore_1
    //   861: aload_1
    //   862: ifnull -> 895
    //   865: aload_1
    //   866: invokevirtual getClassLoader : ()Ljava/lang/ClassLoader;
    //   869: astore_1
    //   870: aload_1
    //   871: instanceof gnu/bytecode/ZipLoader
    //   874: ifne -> 895
    //   877: aload_1
    //   878: instanceof gnu/bytecode/ArrayClassLoader
    //   881: istore #7
    //   883: aload #13
    //   885: astore #8
    //   887: aload #9
    //   889: astore_1
    //   890: iload #7
    //   892: ifeq -> 345
    //   895: aconst_null
    //   896: astore_1
    //   897: aload #13
    //   899: astore #8
    //   901: goto -> 345
    //   904: aconst_null
    //   905: astore_1
    //   906: aload #13
    //   908: astore #8
    //   910: goto -> 345
    //   913: astore_1
    //   914: aload #14
    //   916: astore #8
    //   918: aload_0
    //   919: bipush #101
    //   921: new java/lang/StringBuilder
    //   924: dup
    //   925: invokespecial <init> : ()V
    //   928: ldc_w 'exception loading ''
    //   931: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   934: aload #11
    //   936: invokevirtual append : (Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   939: ldc_w '' - '
    //   942: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   945: aload_1
    //   946: invokevirtual getMessage : ()Ljava/lang/String;
    //   949: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   952: invokevirtual toString : ()Ljava/lang/String;
    //   955: invokevirtual error : (CLjava/lang/String;)V
    //   958: aconst_null
    //   959: astore_1
    //   960: goto -> 345
    //   963: aload #12
    //   965: ifnull -> 983
    //   968: aload #13
    //   970: astore #8
    //   972: aload #9
    //   974: astore_1
    //   975: aload #12
    //   977: invokevirtual isBound : ()Z
    //   980: ifne -> 345
    //   983: aload_0
    //   984: invokevirtual getLanguage : ()Lgnu/expr/Language;
    //   987: checkcast gnu/kawa/lispexpr/LispLanguage
    //   990: aload #15
    //   992: aload_0
    //   993: invokevirtual checkDefaultBinding : (Lgnu/mapping/Symbol;Lkawa/lang/Translator;)Lgnu/expr/Expression;
    //   996: astore #11
    //   998: aload #13
    //   1000: astore #8
    //   1002: aload #9
    //   1004: astore_1
    //   1005: aload #11
    //   1007: ifnull -> 345
    //   1010: aload #11
    //   1012: areturn
    //   1013: aload_1
    //   1014: invokevirtual getContext : ()Lgnu/expr/ScopeExp;
    //   1017: instanceof kawa/lang/PatternScope
    //   1020: ifeq -> 1057
    //   1023: aload_0
    //   1024: new java/lang/StringBuilder
    //   1027: dup
    //   1028: invokespecial <init> : ()V
    //   1031: ldc_w 'reference to pattern variable '
    //   1034: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1037: aload_1
    //   1038: invokevirtual getName : ()Ljava/lang/String;
    //   1041: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1044: ldc_w ' outside syntax template'
    //   1047: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1050: invokevirtual toString : ()Ljava/lang/String;
    //   1053: invokevirtual syntaxError : (Ljava/lang/String;)Lgnu/expr/Expression;
    //   1056: areturn
    //   1057: new gnu/expr/ReferenceExp
    //   1060: dup
    //   1061: aload #10
    //   1063: aload_1
    //   1064: invokespecial <init> : (Ljava/lang/Object;Lgnu/expr/Declaration;)V
    //   1067: astore_1
    //   1068: aload_1
    //   1069: aload #8
    //   1071: invokevirtual setContextDecl : (Lgnu/expr/Declaration;)V
    //   1074: aload_1
    //   1075: aload_0
    //   1076: invokevirtual setLine : (Lgnu/expr/Compilation;)V
    //   1079: iload_2
    //   1080: ifeq -> 1094
    //   1083: iload #6
    //   1085: ifeq -> 1094
    //   1088: aload_1
    //   1089: bipush #8
    //   1091: invokevirtual setFlag : (I)V
    //   1094: aload_1
    //   1095: areturn
    //   1096: aload_1
    //   1097: instanceof gnu/expr/LangExp
    //   1100: ifeq -> 1116
    //   1103: aload_0
    //   1104: aload_1
    //   1105: checkcast gnu/expr/LangExp
    //   1108: invokevirtual getLangValue : ()Ljava/lang/Object;
    //   1111: iload_2
    //   1112: invokevirtual rewrite : (Ljava/lang/Object;Z)Lgnu/expr/Expression;
    //   1115: areturn
    //   1116: aload_1
    //   1117: instanceof gnu/expr/Expression
    //   1120: ifeq -> 1128
    //   1123: aload_1
    //   1124: checkcast gnu/expr/Expression
    //   1127: areturn
    //   1128: aload_1
    //   1129: getstatic gnu/expr/Special.abstractSpecial : Lgnu/expr/Special;
    //   1132: if_acmpne -> 1139
    //   1135: getstatic gnu/expr/QuoteExp.abstractExp : Lgnu/expr/QuoteExp;
    //   1138: areturn
    //   1139: aload_1
    //   1140: aload_0
    //   1141: invokestatic quote : (Ljava/lang/Object;Lkawa/lang/Translator;)Ljava/lang/Object;
    //   1144: aload_0
    //   1145: invokestatic getInstance : (Ljava/lang/Object;Lgnu/text/SourceLocator;)Lgnu/expr/QuoteExp;
    //   1148: areturn
    //   1149: astore #9
    //   1151: aload_1
    //   1152: astore #8
    //   1154: aload #9
    //   1156: astore_1
    //   1157: goto -> 918
    //   1160: aconst_null
    //   1161: astore_1
    //   1162: aload #13
    //   1164: astore #8
    //   1166: goto -> 345
    //   1169: aconst_null
    //   1170: astore_1
    //   1171: goto -> 695
    // Exception table:
    //   from	to	target	type
    //   18	43	51	finally
    //   732	786	913	java/lang/Throwable
    //   789	796	913	java/lang/Throwable
    //   803	822	913	java/lang/Throwable
    //   822	838	1149	java/lang/Throwable
    //   847	861	913	java/lang/Throwable
    //   865	883	913	java/lang/Throwable
  }
  
  public void rewriteInBody(Object paramObject) {
    if (paramObject instanceof SyntaxForm) {
      null = (SyntaxForm)paramObject;
      paramObject = this.current_scope;
      try {
        setCurrentScope((ScopeExp)null.getScope());
        rewriteInBody(null.getDatum());
        return;
      } finally {
        setCurrentScope((ScopeExp)paramObject);
      } 
    } 
    if (paramObject instanceof Values) {
      paramObject = ((Values)paramObject).getValues();
      int i = 0;
      while (true) {
        if (i < paramObject.length) {
          rewriteInBody(paramObject[i]);
          i++;
          continue;
        } 
        return;
      } 
    } 
    this.formStack.add(rewrite(paramObject, false));
  }
  
  public Expression rewrite_body(Object paramObject) {
    int i;
    Object object = pushPositionOf(paramObject);
    LetExp letExp = new LetExp(null);
    int j = this.formStack.size();
    letExp.outer = this.current_scope;
    this.current_scope = (ScopeExp)letExp;
    try {
      paramObject = scanBody(paramObject, (ScopeExp)letExp, true);
      if (paramObject.isEmpty())
        this.formStack.add(syntaxError("body with no expressions")); 
    } finally {
      pop((ScopeExp)letExp);
      popPositionOf(object);
    } 
    rewriteBody((LList)paramObject);
    paramObject = makeBody(j, (ScopeExp)null);
    setLineOf((Expression)paramObject);
    if (i == 0) {
      pop((ScopeExp)letExp);
      popPositionOf(object);
      return (Expression)paramObject;
    } 
    letExp.body = (Expression)paramObject;
    setLineOf((Expression)letExp);
    pop((ScopeExp)letExp);
    popPositionOf(object);
    return (Expression)letExp;
  }
  
  public final Expression rewrite_car(Pair paramPair, SyntaxForm paramSyntaxForm) {
    if (paramSyntaxForm == null || paramSyntaxForm.getScope() == this.current_scope || paramPair.getCar() instanceof SyntaxForm)
      return rewrite_car(paramPair, false); 
    ScopeExp scopeExp = this.current_scope;
    try {
      setCurrentScope((ScopeExp)paramSyntaxForm.getScope());
      return rewrite_car(paramPair, false);
    } finally {
      setCurrentScope(scopeExp);
    } 
  }
  
  public final Expression rewrite_car(Pair paramPair, boolean paramBoolean) {
    Object object = paramPair.getCar();
    return (paramPair instanceof PairWithPosition) ? rewrite_with_position(object, paramBoolean, (PairWithPosition)paramPair) : rewrite(object, paramBoolean);
  }
  
  public Expression rewrite_pair(Pair paramPair, boolean paramBoolean) {
    // Byte code:
    //   0: aload_0
    //   1: aload_1
    //   2: iconst_1
    //   3: invokevirtual rewrite_car : (Lgnu/lists/Pair;Z)Lgnu/expr/Expression;
    //   6: astore #9
    //   8: aload #9
    //   10: instanceof gnu/expr/QuoteExp
    //   13: ifeq -> 42
    //   16: aload #9
    //   18: invokevirtual valueIfConstant : ()Ljava/lang/Object;
    //   21: astore #7
    //   23: aload #7
    //   25: instanceof kawa/lang/Syntax
    //   28: ifeq -> 42
    //   31: aload_0
    //   32: aload #7
    //   34: checkcast kawa/lang/Syntax
    //   37: aload_1
    //   38: invokevirtual apply_rewrite : (Lkawa/lang/Syntax;Lgnu/lists/Pair;)Lgnu/expr/Expression;
    //   41: areturn
    //   42: aload #9
    //   44: instanceof gnu/expr/ReferenceExp
    //   47: ifeq -> 225
    //   50: aload #9
    //   52: checkcast gnu/expr/ReferenceExp
    //   55: astore #10
    //   57: aload #10
    //   59: invokevirtual getBinding : ()Lgnu/expr/Declaration;
    //   62: astore #8
    //   64: aload #8
    //   66: ifnonnull -> 277
    //   69: aload #10
    //   71: invokevirtual getSymbol : ()Ljava/lang/Object;
    //   74: astore #7
    //   76: aload #7
    //   78: instanceof gnu/mapping/Symbol
    //   81: ifeq -> 158
    //   84: aload_0
    //   85: aload #7
    //   87: invokevirtual selfEvaluatingSymbol : (Ljava/lang/Object;)Z
    //   90: ifne -> 158
    //   93: aload #7
    //   95: checkcast gnu/mapping/Symbol
    //   98: astore #7
    //   100: aload #7
    //   102: invokevirtual getName : ()Ljava/lang/String;
    //   105: pop
    //   106: aload_0
    //   107: getfield env : Lgnu/mapping/Environment;
    //   110: astore #11
    //   112: aload_0
    //   113: invokevirtual getLanguage : ()Lgnu/expr/Language;
    //   116: invokevirtual hasSeparateFunctionNamespace : ()Z
    //   119: ifeq -> 179
    //   122: getstatic gnu/mapping/EnvironmentKey.FUNCTION : Ljava/lang/Object;
    //   125: astore #8
    //   127: aload #11
    //   129: aload #7
    //   131: aload #8
    //   133: aconst_null
    //   134: invokevirtual get : (Lgnu/mapping/Symbol;Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   137: astore #7
    //   139: aload #7
    //   141: instanceof kawa/lang/Syntax
    //   144: ifeq -> 185
    //   147: aload_0
    //   148: aload #7
    //   150: checkcast kawa/lang/Syntax
    //   153: aload_1
    //   154: invokevirtual apply_rewrite : (Lkawa/lang/Syntax;Lgnu/lists/Pair;)Lgnu/expr/Expression;
    //   157: areturn
    //   158: aload #7
    //   160: invokevirtual toString : ()Ljava/lang/String;
    //   163: astore #7
    //   165: aload_0
    //   166: getfield env : Lgnu/mapping/Environment;
    //   169: aload #7
    //   171: invokevirtual getSymbol : (Ljava/lang/String;)Lgnu/mapping/Symbol;
    //   174: astore #7
    //   176: goto -> 106
    //   179: aconst_null
    //   180: astore #8
    //   182: goto -> 127
    //   185: aload #7
    //   187: instanceof kawa/lang/AutoloadProcedure
    //   190: ifeq -> 202
    //   193: aload #7
    //   195: checkcast kawa/lang/AutoloadProcedure
    //   198: invokevirtual getLoaded : ()Lgnu/mapping/Procedure;
    //   201: pop
    //   202: aload #10
    //   204: iconst_1
    //   205: invokevirtual setProcedureName : (Z)V
    //   208: aload_0
    //   209: invokevirtual getLanguage : ()Lgnu/expr/Language;
    //   212: invokevirtual hasSeparateFunctionNamespace : ()Z
    //   215: ifeq -> 225
    //   218: aload #9
    //   220: bipush #8
    //   222: invokevirtual setFlag : (I)V
    //   225: aload_1
    //   226: invokevirtual getCdr : ()Ljava/lang/Object;
    //   229: astore #7
    //   231: aload #7
    //   233: invokestatic listLength : (Ljava/lang/Object;)I
    //   236: istore #4
    //   238: iload #4
    //   240: iconst_m1
    //   241: if_icmpne -> 312
    //   244: aload_0
    //   245: new java/lang/StringBuilder
    //   248: dup
    //   249: invokespecial <init> : ()V
    //   252: ldc_w 'circular list is not allowed after '
    //   255: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   258: aload_1
    //   259: invokevirtual getCar : ()Ljava/lang/Object;
    //   262: invokevirtual append : (Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   265: invokevirtual toString : ()Ljava/lang/String;
    //   268: invokevirtual syntaxError : (Ljava/lang/String;)Lgnu/expr/Expression;
    //   271: areturn
    //   272: astore #7
    //   274: goto -> 202
    //   277: aload_0
    //   278: getfield macroContext : Lgnu/expr/Declaration;
    //   281: astore #7
    //   283: aload_0
    //   284: aload #8
    //   286: invokevirtual check_if_Syntax : (Lgnu/expr/Declaration;)Lkawa/lang/Syntax;
    //   289: astore #8
    //   291: aload #8
    //   293: ifnull -> 202
    //   296: aload_0
    //   297: aload #8
    //   299: aload_1
    //   300: invokevirtual apply_rewrite : (Lkawa/lang/Syntax;Lgnu/lists/Pair;)Lgnu/expr/Expression;
    //   303: astore_1
    //   304: aload_0
    //   305: aload #7
    //   307: putfield macroContext : Lgnu/expr/Declaration;
    //   310: aload_1
    //   311: areturn
    //   312: iload #4
    //   314: ifge -> 356
    //   317: aload_0
    //   318: new java/lang/StringBuilder
    //   321: dup
    //   322: invokespecial <init> : ()V
    //   325: ldc_w 'dotted list ['
    //   328: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   331: aload #7
    //   333: invokevirtual append : (Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   336: ldc_w '] is not allowed after '
    //   339: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   342: aload_1
    //   343: invokevirtual getCar : ()Ljava/lang/Object;
    //   346: invokevirtual append : (Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   349: invokevirtual toString : ()Ljava/lang/String;
    //   352: invokevirtual syntaxError : (Ljava/lang/String;)Lgnu/expr/Expression;
    //   355: areturn
    //   356: iconst_0
    //   357: istore #5
    //   359: new java/util/Stack
    //   362: dup
    //   363: invokespecial <init> : ()V
    //   366: astore #8
    //   368: aload_0
    //   369: getfield current_scope : Lgnu/expr/ScopeExp;
    //   372: astore #10
    //   374: iconst_0
    //   375: istore_3
    //   376: aload #7
    //   378: astore_1
    //   379: iload_3
    //   380: iload #4
    //   382: if_icmpge -> 573
    //   385: aload_1
    //   386: astore #7
    //   388: aload_1
    //   389: instanceof kawa/lang/SyntaxForm
    //   392: ifeq -> 418
    //   395: aload_1
    //   396: checkcast kawa/lang/SyntaxForm
    //   399: astore_1
    //   400: aload_1
    //   401: invokeinterface getDatum : ()Ljava/lang/Object;
    //   406: astore #7
    //   408: aload_0
    //   409: aload_1
    //   410: invokeinterface getScope : ()Lkawa/lang/TemplateScope;
    //   415: invokevirtual setCurrentScope : (Lgnu/expr/ScopeExp;)V
    //   418: aload #7
    //   420: checkcast gnu/lists/Pair
    //   423: astore #11
    //   425: aload_0
    //   426: aload #11
    //   428: iconst_0
    //   429: invokevirtual rewrite_car : (Lgnu/lists/Pair;Z)Lgnu/expr/Expression;
    //   432: astore #7
    //   434: iload_3
    //   435: iconst_1
    //   436: iadd
    //   437: istore_3
    //   438: aload #7
    //   440: astore_1
    //   441: iload #5
    //   443: istore #6
    //   445: iload #5
    //   447: ifeq -> 493
    //   450: iload_3
    //   451: iconst_1
    //   452: iand
    //   453: ifne -> 512
    //   456: aload #8
    //   458: invokevirtual pop : ()Ljava/lang/Object;
    //   461: checkcast gnu/expr/Expression
    //   464: astore_1
    //   465: new gnu/expr/ApplyExp
    //   468: dup
    //   469: getstatic gnu/kawa/xml/MakeAttribute.makeAttribute : Lgnu/kawa/xml/MakeAttribute;
    //   472: iconst_2
    //   473: anewarray gnu/expr/Expression
    //   476: dup
    //   477: iconst_0
    //   478: aload_1
    //   479: aastore
    //   480: dup
    //   481: iconst_1
    //   482: aload #7
    //   484: aastore
    //   485: invokespecial <init> : (Lgnu/mapping/Procedure;[Lgnu/expr/Expression;)V
    //   488: astore_1
    //   489: iload #5
    //   491: istore #6
    //   493: aload #8
    //   495: aload_1
    //   496: invokevirtual addElement : (Ljava/lang/Object;)V
    //   499: aload #11
    //   501: invokevirtual getCdr : ()Ljava/lang/Object;
    //   504: astore_1
    //   505: iload #6
    //   507: istore #5
    //   509: goto -> 379
    //   512: aload #7
    //   514: instanceof gnu/expr/QuoteExp
    //   517: ifeq -> 564
    //   520: aload #7
    //   522: checkcast gnu/expr/QuoteExp
    //   525: invokevirtual getValue : ()Ljava/lang/Object;
    //   528: astore_1
    //   529: aload_1
    //   530: instanceof gnu/expr/Keyword
    //   533: ifeq -> 564
    //   536: iload_3
    //   537: iload #4
    //   539: if_icmpge -> 564
    //   542: new gnu/expr/QuoteExp
    //   545: dup
    //   546: aload_1
    //   547: checkcast gnu/expr/Keyword
    //   550: invokevirtual asSymbol : ()Lgnu/mapping/Symbol;
    //   553: invokespecial <init> : (Ljava/lang/Object;)V
    //   556: astore_1
    //   557: iload #5
    //   559: istore #6
    //   561: goto -> 493
    //   564: iconst_0
    //   565: istore #6
    //   567: aload #7
    //   569: astore_1
    //   570: goto -> 493
    //   573: aload #8
    //   575: invokevirtual size : ()I
    //   578: anewarray gnu/expr/Expression
    //   581: astore_1
    //   582: aload #8
    //   584: aload_1
    //   585: invokevirtual copyInto : ([Ljava/lang/Object;)V
    //   588: aload #10
    //   590: aload_0
    //   591: getfield current_scope : Lgnu/expr/ScopeExp;
    //   594: if_acmpeq -> 603
    //   597: aload_0
    //   598: aload #10
    //   600: invokevirtual setCurrentScope : (Lgnu/expr/ScopeExp;)V
    //   603: aload #9
    //   605: instanceof gnu/expr/ReferenceExp
    //   608: ifeq -> 663
    //   611: aload #9
    //   613: checkcast gnu/expr/ReferenceExp
    //   616: invokevirtual getBinding : ()Lgnu/expr/Declaration;
    //   619: getstatic kawa/lang/Translator.getNamedPartDecl : Lgnu/expr/Declaration;
    //   622: if_acmpne -> 663
    //   625: aload_1
    //   626: iconst_0
    //   627: aaload
    //   628: astore #7
    //   630: aload_1
    //   631: iconst_1
    //   632: aaload
    //   633: astore_1
    //   634: aload_0
    //   635: aload #7
    //   637: aload_1
    //   638: invokevirtual namespaceResolve : (Lgnu/expr/Expression;Lgnu/expr/Expression;)Lgnu/mapping/Symbol;
    //   641: astore #8
    //   643: aload #8
    //   645: ifnull -> 656
    //   648: aload_0
    //   649: aload #8
    //   651: iload_2
    //   652: invokevirtual rewrite : (Ljava/lang/Object;Z)Lgnu/expr/Expression;
    //   655: areturn
    //   656: aload #7
    //   658: aload_1
    //   659: invokestatic makeExp : (Lgnu/expr/Expression;Lgnu/expr/Expression;)Lgnu/expr/Expression;
    //   662: areturn
    //   663: aload_0
    //   664: invokevirtual getLanguage : ()Lgnu/expr/Language;
    //   667: checkcast gnu/kawa/lispexpr/LispLanguage
    //   670: aload #9
    //   672: aload_1
    //   673: invokevirtual makeApply : (Lgnu/expr/Expression;[Lgnu/expr/Expression;)Lgnu/expr/Expression;
    //   676: areturn
    // Exception table:
    //   from	to	target	type
    //   193	202	272	java/lang/RuntimeException
  }
  
  public Expression rewrite_with_position(Object paramObject, boolean paramBoolean, PairWithPosition paramPairWithPosition) {
    Object object = pushPositionOf(paramPairWithPosition);
    if (paramObject == paramPairWithPosition)
      try {
        paramObject = rewrite_pair((Pair)paramPairWithPosition, paramBoolean);
        setLineOf((Expression)paramObject);
        return (Expression)paramObject;
      } finally {
        popPositionOf(object);
      }  
    paramObject = rewrite(paramObject, paramBoolean);
    setLineOf((Expression)paramObject);
    popPositionOf(object);
    return (Expression)paramObject;
  }
  
  public LList scanBody(Object paramObject, ScopeExp paramScopeExp, boolean paramBoolean) {
    if (paramBoolean) {
      object1 = LList.Empty;
    } else {
      object1 = null;
    } 
    Object object3 = null;
    Object object2 = paramObject;
    paramObject = object1;
    Object object1 = object3;
    while (true) {
      LList lList;
      object3 = paramObject;
      if (object2 != LList.Empty) {
        if (object2 instanceof SyntaxForm) {
          object3 = object2;
          object2 = this.current_scope;
          try {
            setCurrentScope((ScopeExp)object3.getScope());
            int i = this.formStack.size();
            lList = scanBody(object3.getDatum(), paramScopeExp, paramBoolean);
            if (paramBoolean) {
              object3 = SyntaxForms.fromDatumIfNeeded(lList, (SyntaxForm)object3);
              if (object1 == null)
                return (LList)object3; 
              object1.setCdrBackdoor(object3);
              return (LList)paramObject;
            } 
            this.formStack.add(wrapSyntax(popForms(i), (SyntaxForm)object3));
            return null;
          } finally {
            setCurrentScope((ScopeExp)object2);
          } 
        } 
      } else {
        return (LList)object3;
      } 
      if (object2 instanceof Pair) {
        Pair pair = (Pair)object2;
        int i = this.formStack.size();
        scanForm(pair.getCar(), (ScopeExp)lList);
        if (getState() == 2) {
          paramObject = pair;
          if (pair.getCar() != this.pendingForm)
            paramObject = makePair(pair, this.pendingForm, pair.getCdr()); 
          this.pendingForm = new Pair(begin.begin, paramObject);
          return LList.Empty;
        } 
        int j = this.formStack.size();
        object3 = object1;
        object2 = paramObject;
        if (paramBoolean) {
          for (int k = i; k < j; k++) {
            object2 = makePair(pair, this.formStack.elementAt(k), LList.Empty);
            if (object1 == null) {
              paramObject = object2;
            } else {
              object1.setCdrBackdoor(object2);
            } 
            object1 = object2;
          } 
          this.formStack.setSize(i);
          object2 = paramObject;
          object3 = object1;
        } 
        Object object = pair.getCdr();
        object1 = object3;
        paramObject = object2;
        object2 = object;
        continue;
      } 
      this.formStack.add(syntaxError("body is not a proper list"));
      return (LList)paramObject;
    } 
  }
  
  public void scanForm(Object paramObject, ScopeExp paramScopeExp) {
    // Byte code:
    //   0: aload_1
    //   1: instanceof kawa/lang/SyntaxForm
    //   4: ifeq -> 81
    //   7: aload_1
    //   8: checkcast kawa/lang/SyntaxForm
    //   11: astore #6
    //   13: aload_0
    //   14: invokevirtual currentScope : ()Lgnu/expr/ScopeExp;
    //   17: astore_1
    //   18: aload_0
    //   19: aload #6
    //   21: invokeinterface getScope : ()Lkawa/lang/TemplateScope;
    //   26: invokevirtual setCurrentScope : (Lgnu/expr/ScopeExp;)V
    //   29: aload_0
    //   30: getfield formStack : Ljava/util/Stack;
    //   33: invokevirtual size : ()I
    //   36: istore_3
    //   37: aload_0
    //   38: aload #6
    //   40: invokeinterface getDatum : ()Ljava/lang/Object;
    //   45: aload_2
    //   46: invokevirtual scanForm : (Ljava/lang/Object;Lgnu/expr/ScopeExp;)V
    //   49: aload_0
    //   50: getfield formStack : Ljava/util/Stack;
    //   53: aload_0
    //   54: iload_3
    //   55: invokevirtual popForms : (I)Ljava/lang/Object;
    //   58: aload #6
    //   60: invokestatic wrapSyntax : (Ljava/lang/Object;Lkawa/lang/SyntaxForm;)Ljava/lang/Object;
    //   63: invokevirtual add : (Ljava/lang/Object;)Z
    //   66: pop
    //   67: aload_0
    //   68: aload_1
    //   69: invokevirtual setCurrentScope : (Lgnu/expr/ScopeExp;)V
    //   72: return
    //   73: astore_2
    //   74: aload_0
    //   75: aload_1
    //   76: invokevirtual setCurrentScope : (Lgnu/expr/ScopeExp;)V
    //   79: aload_2
    //   80: athrow
    //   81: aload_1
    //   82: astore #8
    //   84: aload_1
    //   85: instanceof gnu/mapping/Values
    //   88: ifeq -> 103
    //   91: aload_1
    //   92: getstatic gnu/mapping/Values.empty : Lgnu/mapping/Values;
    //   95: if_acmpne -> 521
    //   98: getstatic gnu/expr/QuoteExp.voidExp : Lgnu/expr/QuoteExp;
    //   101: astore #8
    //   103: aload #8
    //   105: instanceof gnu/lists/Pair
    //   108: ifeq -> 665
    //   111: aload #8
    //   113: checkcast gnu/lists/Pair
    //   116: astore #11
    //   118: aload_0
    //   119: getfield macroContext : Lgnu/expr/Declaration;
    //   122: astore #10
    //   124: aconst_null
    //   125: astore #9
    //   127: aload_0
    //   128: getfield current_scope : Lgnu/expr/ScopeExp;
    //   131: astore #12
    //   133: aload_0
    //   134: aload #8
    //   136: invokevirtual pushPositionOf : (Ljava/lang/Object;)Ljava/lang/Object;
    //   139: astore #13
    //   141: aload #8
    //   143: instanceof gnu/text/SourceLocator
    //   146: ifeq -> 165
    //   149: aload_2
    //   150: invokevirtual getLineNumber : ()I
    //   153: ifge -> 165
    //   156: aload_2
    //   157: aload #8
    //   159: checkcast gnu/text/SourceLocator
    //   162: invokevirtual setLocation : (Lgnu/text/SourceLocator;)V
    //   165: aload #11
    //   167: invokevirtual getCar : ()Ljava/lang/Object;
    //   170: astore_1
    //   171: aload_1
    //   172: astore #6
    //   174: aload_1
    //   175: instanceof kawa/lang/SyntaxForm
    //   178: ifeq -> 208
    //   181: aload #11
    //   183: invokevirtual getCar : ()Ljava/lang/Object;
    //   186: checkcast kawa/lang/SyntaxForm
    //   189: astore_1
    //   190: aload_0
    //   191: aload_1
    //   192: invokeinterface getScope : ()Lkawa/lang/TemplateScope;
    //   197: invokevirtual setCurrentScope : (Lgnu/expr/ScopeExp;)V
    //   200: aload_1
    //   201: invokeinterface getDatum : ()Ljava/lang/Object;
    //   206: astore #6
    //   208: aload #6
    //   210: astore_1
    //   211: aload #9
    //   213: astore #7
    //   215: aload #6
    //   217: instanceof gnu/lists/Pair
    //   220: ifeq -> 391
    //   223: aload #6
    //   225: checkcast gnu/lists/Pair
    //   228: astore #14
    //   230: aload #6
    //   232: astore_1
    //   233: aload #9
    //   235: astore #7
    //   237: aload #14
    //   239: invokevirtual getCar : ()Ljava/lang/Object;
    //   242: getstatic gnu/kawa/lispexpr/LispLanguage.lookup_sym : Lgnu/mapping/Symbol;
    //   245: if_acmpne -> 391
    //   248: aload #6
    //   250: astore_1
    //   251: aload #9
    //   253: astore #7
    //   255: aload #14
    //   257: invokevirtual getCdr : ()Ljava/lang/Object;
    //   260: instanceof gnu/lists/Pair
    //   263: ifeq -> 391
    //   266: aload #14
    //   268: invokevirtual getCdr : ()Ljava/lang/Object;
    //   271: checkcast gnu/lists/Pair
    //   274: astore #14
    //   276: aload #6
    //   278: astore_1
    //   279: aload #9
    //   281: astore #7
    //   283: aload #14
    //   285: invokevirtual getCdr : ()Ljava/lang/Object;
    //   288: instanceof gnu/lists/Pair
    //   291: ifeq -> 391
    //   294: aload_0
    //   295: aload #14
    //   297: invokevirtual getCar : ()Ljava/lang/Object;
    //   300: invokevirtual rewrite : (Ljava/lang/Object;)Lgnu/expr/Expression;
    //   303: astore_1
    //   304: aload_0
    //   305: aload #14
    //   307: invokevirtual getCdr : ()Ljava/lang/Object;
    //   310: checkcast gnu/lists/Pair
    //   313: invokevirtual getCar : ()Ljava/lang/Object;
    //   316: invokevirtual rewrite : (Ljava/lang/Object;)Lgnu/expr/Expression;
    //   319: astore #6
    //   321: aload_1
    //   322: invokevirtual valueIfConstant : ()Ljava/lang/Object;
    //   325: astore #7
    //   327: aload #6
    //   329: invokevirtual valueIfConstant : ()Ljava/lang/Object;
    //   332: astore #14
    //   334: aload #7
    //   336: instanceof java/lang/Class
    //   339: ifeq -> 562
    //   342: aload #14
    //   344: instanceof gnu/mapping/Symbol
    //   347: istore #5
    //   349: iload #5
    //   351: ifeq -> 562
    //   354: aload #7
    //   356: aload #14
    //   358: checkcast gnu/mapping/Symbol
    //   361: invokestatic getNamedPart : (Ljava/lang/Object;Lgnu/mapping/Symbol;)Ljava/lang/Object;
    //   364: astore #6
    //   366: aload #6
    //   368: astore_1
    //   369: aload #9
    //   371: astore #7
    //   373: aload #6
    //   375: instanceof kawa/lang/Syntax
    //   378: ifeq -> 391
    //   381: aload #6
    //   383: checkcast kawa/lang/Syntax
    //   386: astore #7
    //   388: aload #6
    //   390: astore_1
    //   391: aload_1
    //   392: instanceof gnu/mapping/Symbol
    //   395: ifeq -> 604
    //   398: aload_0
    //   399: aload_1
    //   400: invokevirtual selfEvaluatingSymbol : (Ljava/lang/Object;)Z
    //   403: ifne -> 604
    //   406: aload_0
    //   407: aload_1
    //   408: iconst_1
    //   409: invokevirtual rewrite : (Ljava/lang/Object;Z)Lgnu/expr/Expression;
    //   412: astore #9
    //   414: aload #7
    //   416: astore #6
    //   418: aload #9
    //   420: instanceof gnu/expr/ReferenceExp
    //   423: ifeq -> 449
    //   426: aload #9
    //   428: checkcast gnu/expr/ReferenceExp
    //   431: invokevirtual getBinding : ()Lgnu/expr/Declaration;
    //   434: astore #6
    //   436: aload #6
    //   438: ifnull -> 577
    //   441: aload_0
    //   442: aload #6
    //   444: invokevirtual check_if_Syntax : (Lgnu/expr/Declaration;)Lkawa/lang/Syntax;
    //   447: astore #6
    //   449: aload #12
    //   451: aload_0
    //   452: getfield current_scope : Lgnu/expr/ScopeExp;
    //   455: if_acmpeq -> 464
    //   458: aload_0
    //   459: aload #12
    //   461: invokevirtual setCurrentScope : (Lgnu/expr/ScopeExp;)V
    //   464: aload_0
    //   465: aload #13
    //   467: invokevirtual popPositionOf : (Ljava/lang/Object;)V
    //   470: aload #6
    //   472: ifnull -> 665
    //   475: aload_0
    //   476: invokevirtual getFileName : ()Ljava/lang/String;
    //   479: astore_1
    //   480: aload_0
    //   481: invokevirtual getLineNumber : ()I
    //   484: istore_3
    //   485: aload_0
    //   486: invokevirtual getColumnNumber : ()I
    //   489: istore #4
    //   491: aload_0
    //   492: aload #11
    //   494: invokevirtual setLine : (Ljava/lang/Object;)V
    //   497: aload #6
    //   499: aload #11
    //   501: aload_2
    //   502: aload_0
    //   503: invokevirtual scanForm : (Lgnu/lists/Pair;Lgnu/expr/ScopeExp;Lkawa/lang/Translator;)V
    //   506: aload_0
    //   507: aload #10
    //   509: putfield macroContext : Lgnu/expr/Declaration;
    //   512: aload_0
    //   513: aload_1
    //   514: iload_3
    //   515: iload #4
    //   517: invokevirtual setLine : (Ljava/lang/String;II)V
    //   520: return
    //   521: aload_1
    //   522: checkcast gnu/mapping/Values
    //   525: invokevirtual getValues : ()[Ljava/lang/Object;
    //   528: astore_1
    //   529: iconst_0
    //   530: istore_3
    //   531: iload_3
    //   532: aload_1
    //   533: arraylength
    //   534: if_icmpge -> 72
    //   537: aload_0
    //   538: aload_1
    //   539: iload_3
    //   540: aaload
    //   541: aload_2
    //   542: invokevirtual scanForm : (Ljava/lang/Object;Lgnu/expr/ScopeExp;)V
    //   545: iload_3
    //   546: iconst_1
    //   547: iadd
    //   548: istore_3
    //   549: goto -> 531
    //   552: astore_1
    //   553: aconst_null
    //   554: astore_1
    //   555: aload #9
    //   557: astore #7
    //   559: goto -> 391
    //   562: aload_0
    //   563: aload_1
    //   564: aload #6
    //   566: invokevirtual namespaceResolve : (Lgnu/expr/Expression;Lgnu/expr/Expression;)Lgnu/mapping/Symbol;
    //   569: astore_1
    //   570: aload #9
    //   572: astore #7
    //   574: goto -> 391
    //   577: aload_0
    //   578: aload_1
    //   579: iconst_1
    //   580: invokevirtual resolve : (Ljava/lang/Object;Z)Ljava/lang/Object;
    //   583: astore_1
    //   584: aload #7
    //   586: astore #6
    //   588: aload_1
    //   589: instanceof kawa/lang/Syntax
    //   592: ifeq -> 449
    //   595: aload_1
    //   596: checkcast kawa/lang/Syntax
    //   599: astore #6
    //   601: goto -> 449
    //   604: aload #7
    //   606: astore #6
    //   608: aload_1
    //   609: getstatic kawa/standard/begin.begin : Lkawa/standard/begin;
    //   612: if_acmpne -> 449
    //   615: aload_1
    //   616: checkcast kawa/lang/Syntax
    //   619: astore #6
    //   621: goto -> 449
    //   624: astore_1
    //   625: aload #12
    //   627: aload_0
    //   628: getfield current_scope : Lgnu/expr/ScopeExp;
    //   631: if_acmpeq -> 640
    //   634: aload_0
    //   635: aload #12
    //   637: invokevirtual setCurrentScope : (Lgnu/expr/ScopeExp;)V
    //   640: aload_0
    //   641: aload #13
    //   643: invokevirtual popPositionOf : (Ljava/lang/Object;)V
    //   646: aload_1
    //   647: athrow
    //   648: astore_2
    //   649: aload_0
    //   650: aload #10
    //   652: putfield macroContext : Lgnu/expr/Declaration;
    //   655: aload_0
    //   656: aload_1
    //   657: iload_3
    //   658: iload #4
    //   660: invokevirtual setLine : (Ljava/lang/String;II)V
    //   663: aload_2
    //   664: athrow
    //   665: aload_0
    //   666: getfield formStack : Ljava/util/Stack;
    //   669: aload #8
    //   671: invokevirtual add : (Ljava/lang/Object;)Z
    //   674: pop
    //   675: return
    // Exception table:
    //   from	to	target	type
    //   18	67	73	finally
    //   165	171	624	finally
    //   174	208	624	finally
    //   215	230	624	finally
    //   237	248	624	finally
    //   255	276	624	finally
    //   283	349	624	finally
    //   354	366	552	java/lang/Throwable
    //   354	366	624	finally
    //   373	388	552	java/lang/Throwable
    //   373	388	624	finally
    //   391	414	624	finally
    //   418	436	624	finally
    //   441	449	624	finally
    //   491	506	648	finally
    //   562	570	624	finally
    //   577	584	624	finally
    //   588	601	624	finally
    //   608	621	624	finally
  }
  
  public final boolean selfEvaluatingSymbol(Object paramObject) {
    return ((LispLanguage)getLanguage()).selfEvaluatingSymbol(paramObject);
  }
  
  public void setLineOf(Expression paramExpression) {
    if (paramExpression instanceof QuoteExp)
      return; 
    paramExpression.setLocation((SourceLocator)this);
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/kawa/lang/Translator.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */