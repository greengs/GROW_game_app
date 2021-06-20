package gnu.xquery.lang;

import gnu.bytecode.ClassType;
import gnu.bytecode.Method;
import gnu.bytecode.Type;
import gnu.expr.ApplyExp;
import gnu.expr.BeginExp;
import gnu.expr.Compilation;
import gnu.expr.Declaration;
import gnu.expr.ErrorExp;
import gnu.expr.Expression;
import gnu.expr.LambdaExp;
import gnu.expr.ModuleExp;
import gnu.expr.PrimProcedure;
import gnu.expr.QuoteExp;
import gnu.expr.ReferenceExp;
import gnu.expr.ResolveNames;
import gnu.expr.ScopeExp;
import gnu.expr.SetExp;
import gnu.kawa.functions.GetModuleClass;
import gnu.kawa.reflect.SingletonType;
import gnu.kawa.xml.MakeAttribute;
import gnu.kawa.xml.MakeElement;
import gnu.kawa.xml.NodeType;
import gnu.kawa.xml.XDataType;
import gnu.mapping.Namespace;
import gnu.mapping.Procedure;
import gnu.mapping.Symbol;
import gnu.mapping.WrongArguments;
import gnu.text.SourceLocator;
import gnu.xml.NamespaceBinding;
import gnu.xml.XMLFilter;
import gnu.xquery.util.NamedCollator;
import gnu.xquery.util.QNameUtils;

public class XQResolveNames extends ResolveNames {
  public static final int BASE_URI_BUILTIN = -11;
  
  public static final int CASTABLE_AS_BUILTIN = -34;
  
  public static final int CAST_AS_BUILTIN = -33;
  
  public static final int COLLECTION_BUILTIN = -8;
  
  public static final int COMPARE_BUILTIN = -4;
  
  public static final int DEEP_EQUAL_BUILTIN = -25;
  
  public static final int DEFAULT_COLLATION_BUILTIN = -29;
  
  public static final int DISTINCT_VALUES_BUILTIN = -5;
  
  public static final int DOC_AVAILABLE_BUILTIN = -10;
  
  public static final int DOC_BUILTIN = -9;
  
  public static final int HANDLE_EXTENSION_BUILTIN = -3;
  
  public static final int IDREF_BUILTIN = -31;
  
  public static final int ID_BUILTIN = -30;
  
  public static final int INDEX_OF_BUILTIN = -15;
  
  public static final int LANG_BUILTIN = -23;
  
  public static final int LAST_BUILTIN = -1;
  
  public static final int LOCAL_NAME_BUILTIN = -6;
  
  public static final int MAX_BUILTIN = -27;
  
  public static final int MIN_BUILTIN = -26;
  
  public static final int NAMESPACE_URI_BUILTIN = -7;
  
  public static final int NAME_BUILTIN = -24;
  
  public static final int NORMALIZE_SPACE_BUILTIN = -17;
  
  public static final int NUMBER_BUILTIN = -28;
  
  public static final int POSITION_BUILTIN = -2;
  
  public static final int RESOLVE_PREFIX_BUILTIN = -13;
  
  public static final int RESOLVE_URI_BUILTIN = -12;
  
  public static final int ROOT_BUILTIN = -32;
  
  public static final int STATIC_BASE_URI_BUILTIN = -14;
  
  public static final int STRING_BUILTIN = -16;
  
  public static final int UNORDERED_BUILTIN = -18;
  
  public static final int XS_QNAME_BUILTIN = -35;
  
  public static final int XS_QNAME_IGNORE_DEFAULT_BUILTIN = -36;
  
  public static final Declaration castAsDecl;
  
  public static final Declaration castableAsDecl;
  
  public static final Declaration handleExtensionDecl = makeBuiltin("(extension)", -3);
  
  public static final Declaration lastDecl;
  
  public static final Declaration resolvePrefixDecl;
  
  public static final Declaration staticBaseUriDecl;
  
  public static final Declaration xsQNameDecl;
  
  public static final Declaration xsQNameIgnoreDefaultDecl;
  
  public Namespace[] functionNamespacePath = XQuery.defaultFunctionNamespacePath;
  
  private Declaration moduleDecl;
  
  public XQParser parser;
  
  static {
    castAsDecl = makeBuiltin("(cast as)", -33);
    castableAsDecl = makeBuiltin("(castable as)", -34);
    lastDecl = makeBuiltin("last", -1);
    xsQNameDecl = makeBuiltin(Symbol.make("http://www.w3.org/2001/XMLSchema", "QName"), -35);
    xsQNameIgnoreDefaultDecl = makeBuiltin(Symbol.make("http://www.w3.org/2001/XMLSchema", "(QName-ignore-default)"), -36);
    staticBaseUriDecl = makeBuiltin("static-base-uri", -14);
    resolvePrefixDecl = makeBuiltin(Symbol.make("http://www.w3.org/2001/XMLSchema", "(resolve-prefix)"), -13);
  }
  
  public XQResolveNames() {
    this((Compilation)null);
  }
  
  public XQResolveNames(Compilation paramCompilation) {
    super(paramCompilation);
    this.lookup.push(lastDecl);
    this.lookup.push(xsQNameDecl);
    this.lookup.push(staticBaseUriDecl);
    pushBuiltin("position", -2);
    pushBuiltin("compare", -4);
    pushBuiltin("distinct-values", -5);
    pushBuiltin("local-name", -6);
    pushBuiltin("name", -24);
    pushBuiltin("namespace-uri", -7);
    pushBuiltin("root", -32);
    pushBuiltin("base-uri", -11);
    pushBuiltin("lang", -23);
    pushBuiltin("resolve-uri", -12);
    pushBuiltin("collection", -8);
    pushBuiltin("doc", -9);
    pushBuiltin("document", -9);
    pushBuiltin("doc-available", -10);
    pushBuiltin("index-of", -15);
    pushBuiltin("string", -16);
    pushBuiltin("normalize-space", -17);
    pushBuiltin("unordered", -18);
    pushBuiltin("deep-equal", -25);
    pushBuiltin("min", -26);
    pushBuiltin("max", -27);
    pushBuiltin("number", -28);
    pushBuiltin("default-collation", -29);
    pushBuiltin("id", -30);
    pushBuiltin("idref", -31);
  }
  
  private Expression checkArgCount(Expression[] paramArrayOfExpression, Declaration paramDeclaration, int paramInt1, int paramInt2) {
    String str = WrongArguments.checkArgCount("fn:" + paramDeclaration.getName(), paramInt1, paramInt2, paramArrayOfExpression.length);
    return (str == null) ? null : getCompilation().syntaxError(str);
  }
  
  public static Declaration makeBuiltin(Symbol paramSymbol, int paramInt) {
    Declaration declaration = new Declaration(paramSymbol);
    declaration.setProcedureDecl(true);
    declaration.setCode(paramInt);
    return declaration;
  }
  
  public static Declaration makeBuiltin(String paramString, int paramInt) {
    return makeBuiltin(Symbol.make("http://www.w3.org/2005/xpath-functions", paramString, "fn"), paramInt);
  }
  
  static NamespaceBinding maybeAddNamespace(Symbol paramSymbol, boolean paramBoolean, NamespaceBinding paramNamespaceBinding) {
    if (paramSymbol != null) {
      String str2 = paramSymbol.getPrefix();
      String str3 = paramSymbol.getNamespaceURI();
      String str1 = str2;
      if (str2 == "")
        str1 = null; 
      str2 = str3;
      if (str3 == "")
        str2 = null; 
      if (!paramBoolean || str1 != null || str2 != null)
        return NamespaceBinding.maybeAdd(str1, str2, paramNamespaceBinding); 
    } 
    return paramNamespaceBinding;
  }
  
  static Declaration procToDecl(Object paramObject1, Object paramObject2) {
    paramObject1 = new Declaration(paramObject1);
    paramObject1.setProcedureDecl(true);
    paramObject1.noteValue((Expression)new QuoteExp(paramObject2));
    paramObject1.setFlag(16384L);
    return (Declaration)paramObject1;
  }
  
  private Expression visitStatements(Expression paramExpression) {
    if (paramExpression instanceof BeginExp) {
      BeginExp beginExp = (BeginExp)paramExpression;
      Expression[] arrayOfExpression = beginExp.getExpressions();
      int j = beginExp.getExpressionCount();
      int i = 0;
      while (true) {
        Expression expression = paramExpression;
        if (i < j) {
          arrayOfExpression[i] = visitStatements(arrayOfExpression[i]);
          i++;
          continue;
        } 
        break;
      } 
    } else {
      Declaration declaration;
      if (paramExpression instanceof SetExp) {
        Declaration declaration1 = this.moduleDecl;
        SetExp setExp = (SetExp)paramExpression;
        Expression expression = visitSetExp(setExp, (Void)null);
        declaration = declaration1;
        if (setExp.isDefining()) {
          declaration = declaration1;
          if (setExp.getBinding() == declaration1) {
            if (!declaration1.isProcedureDecl())
              push(declaration1); 
            declaration = declaration1.nextDecl();
          } 
        } 
        this.moduleDecl = declaration;
        return expression;
      } 
      return (Expression)visit((Expression)declaration, null);
    } 
    return (Expression)SYNTHETIC_LOCAL_VARIABLE_4;
  }
  
  public Expression checkPragma(Symbol paramSymbol, Expression paramExpression) {
    return null;
  }
  
  Declaration flookup(Symbol paramSymbol) {
    // Byte code:
    //   0: getstatic gnu/xquery/lang/XQuery.xqEnvironment : Lgnu/mapping/Environment;
    //   3: aload_1
    //   4: getstatic gnu/mapping/EnvironmentKey.FUNCTION : Ljava/lang/Object;
    //   7: invokevirtual lookup : (Lgnu/mapping/Symbol;Ljava/lang/Object;)Lgnu/mapping/Location;
    //   10: astore_2
    //   11: aload_2
    //   12: ifnonnull -> 19
    //   15: aconst_null
    //   16: astore_2
    //   17: aload_2
    //   18: areturn
    //   19: aload_2
    //   20: invokevirtual getBase : ()Lgnu/mapping/Location;
    //   23: astore #4
    //   25: aload #4
    //   27: instanceof gnu/kawa/reflect/StaticFieldLocation
    //   30: ifeq -> 48
    //   33: aload #4
    //   35: checkcast gnu/kawa/reflect/StaticFieldLocation
    //   38: invokevirtual getDeclaration : ()Lgnu/expr/Declaration;
    //   41: astore_3
    //   42: aload_3
    //   43: astore_2
    //   44: aload_3
    //   45: ifnonnull -> 17
    //   48: aload #4
    //   50: aconst_null
    //   51: invokevirtual get : (Ljava/lang/Object;)Ljava/lang/Object;
    //   54: astore_2
    //   55: aload_2
    //   56: ifnull -> 65
    //   59: aload_1
    //   60: aload_2
    //   61: invokestatic procToDecl : (Ljava/lang/Object;Ljava/lang/Object;)Lgnu/expr/Declaration;
    //   64: areturn
    //   65: aconst_null
    //   66: areturn
  }
  
  Expression getBaseUriExpr() {
    Compilation compilation = getCompilation();
    String str = this.parser.getStaticBaseUri();
    return (Expression)((str != null) ? QuoteExp.getInstance(str) : GetModuleClass.getModuleClassURI(compilation));
  }
  
  Expression getCollator(Expression[] paramArrayOfExpression, int paramInt) {
    if (paramArrayOfExpression != null && paramArrayOfExpression.length > paramInt)
      return (Expression)new ApplyExp(ClassType.make("gnu.xquery.util.NamedCollator").getDeclaredMethod("find", 1), new Expression[] { paramArrayOfExpression[paramInt] }); 
    NamedCollator namedCollator = this.parser.defaultCollator;
    return (Expression)((namedCollator == null) ? QuoteExp.nullExp : new QuoteExp(namedCollator));
  }
  
  void push(Declaration paramDeclaration) {
    Compilation compilation = getCompilation();
    Object object2 = paramDeclaration.getSymbol();
    boolean bool = paramDeclaration.isProcedureDecl();
    Object object1 = object2;
    if (object2 instanceof String) {
      if (paramDeclaration.getLineNumber() > 0 && compilation != null) {
        String str = compilation.getFileName();
        int i = compilation.getLineNumber();
        int j = compilation.getColumnNumber();
        compilation.setLocation((SourceLocator)paramDeclaration);
        object1 = this.parser.namespaceResolve((String)object2, bool);
        compilation.setLine(str, i, j);
      } else {
        object1 = this.parser.namespaceResolve((String)object2, bool);
      } 
      if (object1 == null)
        return; 
      paramDeclaration.setName(object1);
    } 
    object2 = this.lookup.lookup(object1, XQuery.instance.getNamespaceOf(paramDeclaration));
    if (object2 != null)
      if (paramDeclaration.context == ((Declaration)object2).context) {
        ScopeExp.duplicateDeclarationError((Declaration)object2, paramDeclaration, compilation);
      } else if (XQParser.warnHidePreviousDeclaration && (!(object1 instanceof Symbol) || ((Symbol)object1).getNamespace() != null)) {
        compilation.error('w', paramDeclaration, "declaration ", " hides previous declaration");
      }  
    this.lookup.push(paramDeclaration);
  }
  
  protected void push(ScopeExp paramScopeExp) {
    for (Declaration declaration = paramScopeExp.firstDecl(); declaration != null; declaration = declaration.nextDecl())
      push(declaration); 
  }
  
  void pushBuiltin(String paramString, int paramInt) {
    this.lookup.push(makeBuiltin(paramString, paramInt));
  }
  
  public void resolveModule(ModuleExp paramModuleExp) {
    this.currentLambda = (LambdaExp)paramModuleExp;
    for (Declaration declaration2 = paramModuleExp.firstDecl(); declaration2 != null; declaration2 = declaration2.nextDecl()) {
      if (declaration2.isProcedureDecl())
        push(declaration2); 
    } 
    this.moduleDecl = paramModuleExp.firstDecl();
    paramModuleExp.body = visitStatements(paramModuleExp.body);
    for (Declaration declaration1 = paramModuleExp.firstDecl(); declaration1 != null; declaration1 = declaration1.nextDecl()) {
      if (declaration1.getSymbol() != null)
        this.lookup.removeSubsumed(declaration1); 
    } 
  }
  
  protected Expression visitApplyExp(ApplyExp paramApplyExp, Void paramVoid) {
    Expression expression = paramApplyExp.getFunction();
    NamespaceBinding namespaceBinding = this.parser.constructorNamespaces;
    Object object = paramApplyExp.getFunctionValue();
    if (object instanceof MakeElement) {
      object = object;
      NamespaceBinding namespaceBinding1 = NamespaceBinding.nconc(object.getNamespaceNodes(), namespaceBinding);
      object.setNamespaceNodes(namespaceBinding1);
      this.parser.constructorNamespaces = namespaceBinding1;
    } 
    if (expression instanceof ReferenceExp) {
      expression = visitReferenceExp((ReferenceExp)expression, paramApplyExp);
    } else {
      expression = (Expression)visit(expression, paramVoid);
    } 
    paramApplyExp.setFunction(expression);
    visitExps(paramApplyExp.getArgs(), paramVoid);
    this.parser.constructorNamespaces = namespaceBinding;
    expression = paramApplyExp.getFunction();
    if (expression instanceof ReferenceExp) {
      Declaration declaration = ((ReferenceExp)expression).getBinding();
      if (declaration != null) {
        int i = declaration.getCode();
        if (i < 0) {
          Symbol symbol;
          Object object1;
          Object object2;
          int j;
          Object object3;
          Object object4;
          Expression[] arrayOfExpression;
          switch (i) {
            default:
              object2 = paramApplyExp.getFunctionValue();
              if (object2 instanceof Type) {
                object2 = paramApplyExp.getArgs();
                if (object2.length != 1) {
                  this.messages.error('e', "type constructor requires a single argument");
                  return (Expression)paramApplyExp;
                } 
                return (Expression)new ApplyExp(XQParser.makeFunctionExp("gnu.xquery.util.CastAs", "castAs"), new Expression[] { paramApplyExp.getFunction(), (Expression)object2[0] });
              } 
              break;
            case -2:
            case -1:
              if (i == -1) {
                symbol = XQParser.LAST_VARNAME;
              } else {
                symbol = XQParser.POSITION_VARNAME;
              } 
              object2 = this.lookup.lookup(symbol, false);
              if (object2 == null) {
                error('e', "undefined context for " + symbol.getName());
                return (Expression)new ReferenceExp(symbol, (Declaration)object2);
              } 
              object2.setCanRead(true);
              return (Expression)new ReferenceExp(symbol, (Declaration)object2);
            case -34:
            case -33:
              arrayOfExpression = symbol.getArgs();
              if (i == -33) {
                j = 0;
              } else {
                j = 1;
              } 
              object = arrayOfExpression[j];
              object3 = object;
              object4 = object3;
              if (object instanceof ApplyExp) {
                ApplyExp applyExp = (ApplyExp)object;
                object4 = object3;
                if (applyExp.getFunction().valueIfConstant() == XQParser.proc_OccurrenceType_getInstance)
                  object4 = applyExp.getArg(0); 
              } 
              object4 = object4.valueIfConstant();
              object3 = null;
              if (object4 == SingletonType.getInstance()) {
                object3 = "type to 'cast as' or 'castable as' must be atomic";
              } else if (object4 == XDataType.anyAtomicType) {
                object3 = "type to 'cast as' or 'castable as' cannot be anyAtomicType";
              } else if (object4 == XDataType.anySimpleType) {
                object3 = "type to 'cast as' or 'castable as' cannot be anySimpleType";
              } else if (object4 == XDataType.untypedType) {
                object3 = "type to 'cast as' or 'castable as' cannot be untyped";
              } else if (object4 == XDataType.NotationType) {
                object3 = "type to 'cast as' or 'castable as' cannot be NOTATION";
              } 
              if (object3 != null)
                this.messages.error('e', (SourceLocator)object, (String)object3, "XPST0080"); 
              if (object4 == Compilation.typeSymbol && !(object instanceof ApplyExp)) {
                j = 1;
              } else {
                j = 0;
              } 
              if (i == -33) {
                if (j)
                  return visitApplyExp(XQParser.castQName(arrayOfExpression[1], true), (Void)object2); 
                object2 = XQParser.makeFunctionExp("gnu.xquery.util.CastAs", "castAs");
                return (new ApplyExp((Expression)object2, arrayOfExpression)).setLine((Expression)symbol);
              } 
              if (j && arrayOfExpression[0] instanceof QuoteExp) {
                object1 = ((QuoteExp)arrayOfExpression[0]).getValue();
                try {
                  QNameUtils.resolveQName(object1, this.parser.constructorNamespaces, this.parser.prologNamespaces);
                  return (Expression)XQuery.trueExp;
                } catch (RuntimeException null) {
                  return (Expression)XQuery.falseExp;
                } 
              } 
              object2 = XQParser.makeFunctionExp("gnu.xquery.lang.XQParser", "castableAs");
              return (new ApplyExp((Expression)object2, arrayOfExpression)).setLine((Expression)object1);
            case -36:
            case -35:
              object4 = object1.getArgs();
              object2 = checkArgCount((Expression[])object4, (Declaration)object3, 1, 1);
              object1 = object2;
              if (object2 == null) {
                object2 = this.parser.constructorNamespaces;
                object1 = object2;
                if (i == -36)
                  object1 = new NamespaceBinding(null, "", (NamespaceBinding)object2); 
                if (object4[0] instanceof QuoteExp)
                  try {
                    return (Expression)new QuoteExp(QNameUtils.resolveQName(((QuoteExp)object4[0]).getValue(), (NamespaceBinding)object1, this.parser.prologNamespaces));
                  } catch (RuntimeException runtimeException) {
                    return getCompilation().syntaxError(runtimeException.getMessage());
                  }  
                object2 = object4[0];
                QuoteExp quoteExp = new QuoteExp(runtimeException);
                object3 = new QuoteExp(this.parser.prologNamespaces);
                object1 = new ApplyExp(ClassType.make("gnu.xquery.util.QNameUtils").getDeclaredMethod("resolveQName", 3), new Expression[] { (Expression)object2, (Expression)quoteExp, (Expression)object3 });
                object1.setFlag(4);
                return (Expression)object1;
              } 
              return (Expression)object1;
            case -13:
              object4 = object1.getArgs();
              object2 = checkArgCount((Expression[])object4, (Declaration)object3, 1, 1);
              object1 = object2;
              if (object2 == null) {
                if (object4[0] instanceof QuoteExp) {
                  object1 = ((QuoteExp)object4[0]).getValue();
                  if (object1 == null) {
                    object1 = null;
                  } else {
                    object1 = object1.toString();
                  } 
                  object2 = QNameUtils.lookupPrefix((String)object1, this.parser.constructorNamespaces, this.parser.prologNamespaces);
                  return (Expression)((object2 == null) ? getCompilation().syntaxError("unknown namespace prefix '" + object1 + "'") : new QuoteExp(object2));
                } 
                object1 = object4[0];
                object2 = new QuoteExp(this.parser.constructorNamespaces);
                object3 = new QuoteExp(this.parser.prologNamespaces);
                object1 = new ApplyExp((Procedure)new PrimProcedure(ClassType.make("gnu.xquery.util.QNameUtils").getDeclaredMethod("resolvePrefix", 3)), new Expression[] { (Expression)object1, (Expression)object2, (Expression)object3 });
                object1.setFlag(4);
                return (Expression)object1;
              } 
              return (Expression)object1;
            case -6:
              return withContext(ClassType.make("gnu.xquery.util.NodeUtils").getDeclaredMethod("localName", 1), object1.getArgs(), "fn:local-name", 0);
            case -24:
              return withContext(ClassType.make("gnu.xquery.util.NodeUtils").getDeclaredMethod("name", 1), object1.getArgs(), "fn:name", 0);
            case -28:
              return withContext(ClassType.make("gnu.xquery.util.NumberValue").getDeclaredMethod("numberValue", 1), object1.getArgs(), "fn:number", 0);
            case -32:
              return withContext(ClassType.make("gnu.xquery.util.NodeUtils").getDeclaredMethod("root", 1), object1.getArgs(), "fn:root", 0);
            case -11:
              return withContext(ClassType.make("gnu.xquery.util.NodeUtils").getDeclaredMethod("baseUri", 1), object1.getArgs(), "fn:base-uri", 0);
            case -23:
              return withContext(ClassType.make("gnu.xquery.util.NodeUtils").getDeclaredMethod("lang", 2), object1.getArgs(), "fn:lang", 1);
            case -30:
              return withContext(ClassType.make("gnu.xquery.util.NodeUtils").getDeclaredMethod("id$X", 3), object1.getArgs(), "fn:id", 1);
            case -31:
              return withContext(ClassType.make("gnu.xquery.util.NodeUtils").getDeclaredMethod("idref", 2), object1.getArgs(), "fn:idref", 1);
            case -14:
              object2 = checkArgCount(object1.getArgs(), (Declaration)object3, 0, 0);
              object1 = object2;
              return (Expression)((object2 == null) ? getBaseUriExpr() : object1);
            case -7:
              return withContext(ClassType.make("gnu.xquery.util.NodeUtils").getDeclaredMethod("namespaceURI", 1), object1.getArgs(), "fn:namespace-uri", 0);
            case -17:
              return withContext(ClassType.make("gnu.xquery.util.StringUtils").getDeclaredMethod("normalizeSpace", 1), object1.getArgs(), "fn:normalize-space", 0);
            case -18:
              object4 = object1.getArgs();
              object2 = checkArgCount((Expression[])object4, (Declaration)object3, 1, 1);
              object1 = object2;
              return (Expression)((object2 == null) ? object4[0] : object1);
            case -4:
              return withCollator(ClassType.make("gnu.xquery.util.StringUtils").getDeclaredMethod("compare", 3), object1.getArgs(), "fn:compare", 2);
            case -16:
              return withContext(ClassType.make("gnu.xml.TextUtils").getDeclaredMethod("asString", 1), object1.getArgs(), "fn:string", 0);
            case -15:
              return withCollator(ClassType.make("gnu.xquery.util.SequenceUtils").getDeclaredMethod("indexOf$X", 4), object1.getArgs(), "fn:index-of", 2);
            case -8:
              object = object1.getArgs();
              object4 = ClassType.make("gnu.xquery.util.NodeUtils").getDeclaredMethod("collection", 2);
              object2 = checkArgCount((Expression[])object, (Declaration)object3, 0, 1);
              object1 = object2;
              if (object2 == null) {
                object2 = getBaseUriExpr();
                if (object.length > 0) {
                  object1 = object[0];
                  object1 = new ApplyExp((Method)object4, new Expression[] { (Expression)object1, (Expression)object2 });
                  object1.setType((Type)NodeType.documentNodeTest);
                  return (Expression)object1;
                } 
                object1 = QuoteExp.voidExp;
                object1 = new ApplyExp((Method)object4, new Expression[] { (Expression)object1, (Expression)object2 });
                object1.setType((Type)NodeType.documentNodeTest);
                return (Expression)object1;
              } 
              return (Expression)object1;
            case -10:
            case -9:
              object4 = object1.getArgs();
              object = ClassType.make("gnu.xquery.util.NodeUtils");
              if (i == -9) {
                object2 = "docCached";
                object1 = object2;
                if (XQParser.warnOldVersion) {
                  object1 = object2;
                  if ("document".equals(object3.getName())) {
                    getCompilation().error('w', "replace 'document' by 'doc'");
                    object1 = object2;
                  } 
                } 
              } else {
                object1 = "availableCached";
              } 
              object = object.getDeclaredMethod((String)object1, 2);
              object2 = checkArgCount((Expression[])object4, (Declaration)object3, 1, 1);
              object1 = object2;
              if (object2 == null) {
                object1 = getBaseUriExpr();
                object1 = new ApplyExp((Method)object, new Expression[] { (Expression)object4[0], (Expression)object1 });
                if (i == -9) {
                  object1.setType((Type)NodeType.documentNodeTest);
                  return (Expression)object1;
                } 
                object1.setType((Type)XDataType.booleanType);
                return (Expression)object1;
              } 
              return (Expression)object1;
            case -12:
              object4 = object1.getArgs();
              object2 = checkArgCount((Expression[])object4, (Declaration)object3, 1, 2);
              object1 = object2;
              if (object2 == null) {
                object1 = new Expression[2];
                object1[0] = object4[0];
                if (object4.length == 1) {
                  object1[1] = getBaseUriExpr();
                  return (Expression)new ApplyExp(ClassType.make("gnu.xquery.util.QNameUtils").getDeclaredMethod("resolveURI", 2), (Expression[])object1);
                } 
                object1[1] = object4[1];
                return (Expression)new ApplyExp(ClassType.make("gnu.xquery.util.QNameUtils").getDeclaredMethod("resolveURI", 2), (Expression[])object1);
              } 
              return (Expression)object1;
            case -5:
              return withCollator(ClassType.make("gnu.xquery.util.DistinctValues").getDeclaredMethod("distinctValues$X", 3), object1.getArgs(), "fn:distinct-values", 1);
            case -25:
              return withCollator(ClassType.make("gnu.xquery.util.SequenceUtils").getDeclaredMethod("deepEqual", 3), object1.getArgs(), "fn:deep-equal", 2);
            case -26:
              return withCollator(ClassType.make("gnu.xquery.util.MinMax").getDeclaredMethod("min", 2), object1.getArgs(), "fn:min", 1);
            case -27:
              return withCollator(ClassType.make("gnu.xquery.util.MinMax").getDeclaredMethod("max", 2), object1.getArgs(), "fn:max", 1);
            case -29:
              object2 = checkArgCount(object1.getArgs(), (Declaration)object3, 0, 0);
              object1 = object2;
              if (object2 == null) {
                object1 = this.parser.defaultCollator;
                if (object1 != null) {
                  object1 = object1.getName();
                  return (Expression)QuoteExp.getInstance(object1);
                } 
                object1 = "http://www.w3.org/2005/xpath-functions/collation/codepoint";
                return (Expression)QuoteExp.getInstance(object1);
              } 
              return (Expression)object1;
            case -3:
              object2 = getCompilation();
              object1 = object1.getArgs();
              for (j = 0; j < object1.length - 1; j += 2) {
                object3 = ((QuoteExp)object1[j]).getValue();
                object3 = this.parser.namespaceResolve((String)object3, false);
                if (object3 != null)
                  if (object3.getNamespaceURI().length() == 0) {
                    object2.error('e', "pragma name cannot be in the empty namespace");
                  } else {
                    object3 = checkPragma((Symbol)object3, (Expression)object1[j + 1]);
                    if (object3 != null)
                      return (Expression)object3; 
                  }  
              } 
              if (j < object1.length)
                return (Expression)object1[object1.length - 1]; 
              getMessages().error('e', "no recognized pragma or default in extension expression", "XQST0079");
              return (Expression)new ErrorExp("no recognized pragma or default in extension expression");
          } 
          if (object2 instanceof MakeElement) {
            object4 = object2;
            object = object4.getNamespaceNodes();
            object3 = ((MakeElement)object4).tag;
            object2 = object3;
            if (object3 == null)
              object2 = MakeElement.getTagName((ApplyExp)object1); 
            object2 = maybeAddNamespace((Symbol)object2, false, (NamespaceBinding)object);
            object = object1.getArgs();
            Symbol[] arrayOfSymbol = new Symbol[object.length];
            i = 0;
            j = 0;
            label211: while (j < object.length) {
              Object object5 = object[j];
              int k = i;
              object3 = object2;
              if (object5 instanceof ApplyExp) {
                object5 = object5;
                k = i;
                object3 = object2;
                if (object5.getFunction() == MakeAttribute.makeAttributeExp) {
                  Symbol symbol1 = MakeElement.getTagName((ApplyExp)object5);
                  k = i;
                  object3 = object2;
                  if (symbol1 != null)
                    for (k = 0;; k++) {
                      if (k == i) {
                        arrayOfSymbol[i] = symbol1;
                        object3 = maybeAddNamespace(symbol1, true, (NamespaceBinding)object2);
                        k = i + 1;
                        j++;
                        i = k;
                        object2 = object3;
                        continue label211;
                      } 
                      if (symbol1.equals(arrayOfSymbol[k])) {
                        getCompilation().setLine((Expression)object5);
                        object3 = MakeElement.getTagName((ApplyExp)object1);
                        if (object3 == null) {
                          object3 = null;
                        } else {
                          object3 = object3.toString();
                        } 
                        this.messages.error('e', XMLFilter.duplicateAttributeMessage(symbol1, object3), "XQST0040");
                      } 
                    }  
                  continue;
                } 
                continue;
              } 
              continue;
            } 
            if (object2 != null)
              object4.setNamespaceNodes((NamespaceBinding)object2); 
          } 
          return (Expression)object1;
        } 
      } 
    } 
  }
  
  protected Expression visitReferenceExp(ReferenceExp paramReferenceExp, ApplyExp paramApplyExp) {
    // Byte code:
    //   0: aload_1
    //   1: invokevirtual getBinding : ()Lgnu/expr/Declaration;
    //   4: ifnonnull -> 61
    //   7: aload_1
    //   8: invokevirtual getSymbol : ()Ljava/lang/Object;
    //   11: astore #10
    //   13: aload_1
    //   14: invokevirtual isProcedureName : ()Z
    //   17: istore #5
    //   19: aload_1
    //   20: bipush #16
    //   22: invokevirtual getFlag : (I)Z
    //   25: istore #6
    //   27: aload_2
    //   28: ifnonnull -> 63
    //   31: iconst_1
    //   32: istore_3
    //   33: aload_0
    //   34: getfield lookup : Lgnu/expr/NameLookup;
    //   37: aload #10
    //   39: iload_3
    //   40: invokevirtual lookup : (Ljava/lang/Object;I)Lgnu/expr/Declaration;
    //   43: astore #7
    //   45: aload #7
    //   47: ifnull -> 74
    //   50: aload #7
    //   52: ifnull -> 523
    //   55: aload_1
    //   56: aload #7
    //   58: invokevirtual setBinding : (Lgnu/expr/Declaration;)V
    //   61: aload_1
    //   62: areturn
    //   63: aload_2
    //   64: invokevirtual getArgCount : ()I
    //   67: invokestatic namespaceForFunctions : (I)I
    //   70: istore_3
    //   71: goto -> 33
    //   74: aload #10
    //   76: instanceof gnu/mapping/Symbol
    //   79: ifeq -> 168
    //   82: aload #10
    //   84: checkcast gnu/mapping/Symbol
    //   87: astore_2
    //   88: ldc_w ''
    //   91: aload_2
    //   92: invokevirtual getNamespaceURI : ()Ljava/lang/String;
    //   95: invokevirtual equals : (Ljava/lang/Object;)Z
    //   98: ifeq -> 168
    //   101: aload_2
    //   102: invokevirtual getLocalName : ()Ljava/lang/String;
    //   105: astore_2
    //   106: ldc_w 'request'
    //   109: aload_2
    //   110: invokevirtual equals : (Ljava/lang/Object;)Z
    //   113: ifeq -> 146
    //   116: ldc_w 'getCurrentRequest'
    //   119: astore_2
    //   120: aload_2
    //   121: ifnull -> 50
    //   124: new gnu/expr/ApplyExp
    //   127: dup
    //   128: ldc_w 'gnu.kawa.servlet.ServletRequestContext'
    //   131: invokestatic make : (Ljava/lang/String;)Lgnu/bytecode/ClassType;
    //   134: aload_2
    //   135: iconst_0
    //   136: invokevirtual getDeclaredMethod : (Ljava/lang/String;I)Lgnu/bytecode/Method;
    //   139: getstatic gnu/expr/Expression.noExpressions : [Lgnu/expr/Expression;
    //   142: invokespecial <init> : (Lgnu/bytecode/Method;[Lgnu/expr/Expression;)V
    //   145: areturn
    //   146: ldc_w 'response'
    //   149: aload_2
    //   150: invokevirtual equals : (Ljava/lang/Object;)Z
    //   153: ifeq -> 163
    //   156: ldc_w 'getCurrentResponse'
    //   159: astore_2
    //   160: goto -> 120
    //   163: aconst_null
    //   164: astore_2
    //   165: goto -> 120
    //   168: aload #10
    //   170: instanceof gnu/mapping/Symbol
    //   173: ifeq -> 190
    //   176: aload_0
    //   177: aload #10
    //   179: checkcast gnu/mapping/Symbol
    //   182: invokevirtual flookup : (Lgnu/mapping/Symbol;)Lgnu/expr/Declaration;
    //   185: astore #7
    //   187: goto -> 50
    //   190: aload #10
    //   192: checkcast java/lang/String
    //   195: astore #9
    //   197: aload #7
    //   199: astore_2
    //   200: aload #9
    //   202: astore #8
    //   204: aload #9
    //   206: bipush #58
    //   208: invokevirtual indexOf : (I)I
    //   211: ifge -> 286
    //   214: aload #9
    //   216: invokevirtual intern : ()Ljava/lang/String;
    //   219: astore #9
    //   221: aload #7
    //   223: astore_2
    //   224: aload #9
    //   226: astore #8
    //   228: iload #5
    //   230: ifeq -> 286
    //   233: iconst_0
    //   234: istore #4
    //   236: aload #7
    //   238: astore_2
    //   239: aload #9
    //   241: astore #8
    //   243: iload #4
    //   245: aload_0
    //   246: getfield functionNamespacePath : [Lgnu/mapping/Namespace;
    //   249: arraylength
    //   250: if_icmpge -> 286
    //   253: aload_0
    //   254: getfield functionNamespacePath : [Lgnu/mapping/Namespace;
    //   257: iload #4
    //   259: aaload
    //   260: aload #9
    //   262: invokevirtual getSymbol : (Ljava/lang/String;)Lgnu/mapping/Symbol;
    //   265: astore #7
    //   267: aload_0
    //   268: getfield lookup : Lgnu/expr/NameLookup;
    //   271: aload #7
    //   273: iload_3
    //   274: invokevirtual lookup : (Ljava/lang/Object;I)Lgnu/expr/Declaration;
    //   277: astore_2
    //   278: aload_2
    //   279: ifnull -> 391
    //   282: aload #9
    //   284: astore #8
    //   286: aload_2
    //   287: astore #7
    //   289: aload_2
    //   290: ifnonnull -> 50
    //   293: aload_0
    //   294: getfield parser : Lgnu/xquery/lang/XQParser;
    //   297: aload #8
    //   299: iload #5
    //   301: invokevirtual namespaceResolve : (Ljava/lang/String;Z)Lgnu/mapping/Symbol;
    //   304: astore #8
    //   306: aload_2
    //   307: astore #7
    //   309: aload #8
    //   311: ifnull -> 50
    //   314: aload_0
    //   315: getfield lookup : Lgnu/expr/NameLookup;
    //   318: aload #8
    //   320: iload_3
    //   321: invokevirtual lookup : (Ljava/lang/Object;I)Lgnu/expr/Declaration;
    //   324: astore_2
    //   325: aload_2
    //   326: astore #7
    //   328: aload_2
    //   329: ifnonnull -> 50
    //   332: iload #5
    //   334: ifne -> 345
    //   337: aload_2
    //   338: astore #7
    //   340: iload #6
    //   342: ifeq -> 50
    //   345: aload #8
    //   347: invokevirtual getNamespaceURI : ()Ljava/lang/String;
    //   350: astore #9
    //   352: aconst_null
    //   353: astore #7
    //   355: ldc 'http://www.w3.org/2001/XMLSchema'
    //   357: aload #9
    //   359: invokevirtual equals : (Ljava/lang/Object;)Z
    //   362: ifeq -> 423
    //   365: aload #8
    //   367: invokevirtual getName : ()Ljava/lang/String;
    //   370: invokestatic getStandardType : (Ljava/lang/String;)Lgnu/bytecode/Type;
    //   373: astore_2
    //   374: aload_2
    //   375: ifnull -> 467
    //   378: new gnu/expr/QuoteExp
    //   381: dup
    //   382: aload_2
    //   383: invokespecial <init> : (Ljava/lang/Object;)V
    //   386: aload_1
    //   387: invokevirtual setLine : (Lgnu/expr/Expression;)Lgnu/expr/Expression;
    //   390: areturn
    //   391: aload_0
    //   392: aload #7
    //   394: invokevirtual flookup : (Lgnu/mapping/Symbol;)Lgnu/expr/Declaration;
    //   397: astore #7
    //   399: aload #7
    //   401: astore_2
    //   402: aload #9
    //   404: astore #8
    //   406: aload #7
    //   408: ifnonnull -> 286
    //   411: iload #4
    //   413: iconst_1
    //   414: iadd
    //   415: istore #4
    //   417: aload #7
    //   419: astore_2
    //   420: goto -> 239
    //   423: aload #7
    //   425: astore_2
    //   426: iload #6
    //   428: ifeq -> 374
    //   431: aload #7
    //   433: astore_2
    //   434: aload #9
    //   436: ldc_w ''
    //   439: if_acmpne -> 374
    //   442: aload #7
    //   444: astore_2
    //   445: aload_0
    //   446: invokevirtual getCompilation : ()Lgnu/expr/Compilation;
    //   449: invokevirtual isPedantic : ()Z
    //   452: ifne -> 374
    //   455: aload #8
    //   457: invokevirtual getName : ()Ljava/lang/String;
    //   460: invokestatic string2Type : (Ljava/lang/String;)Lgnu/bytecode/Type;
    //   463: astore_2
    //   464: goto -> 374
    //   467: aload #9
    //   469: ifnull -> 512
    //   472: aload #9
    //   474: invokevirtual length : ()I
    //   477: bipush #6
    //   479: if_icmple -> 512
    //   482: aload #9
    //   484: ldc_w 'class:'
    //   487: invokevirtual startsWith : (Ljava/lang/String;)Z
    //   490: ifeq -> 512
    //   493: aload #9
    //   495: bipush #6
    //   497: invokevirtual substring : (I)Ljava/lang/String;
    //   500: invokestatic make : (Ljava/lang/String;)Lgnu/bytecode/ClassType;
    //   503: aload #8
    //   505: invokevirtual getName : ()Ljava/lang/String;
    //   508: invokestatic makeExp : (Lgnu/bytecode/Type;Ljava/lang/String;)Lgnu/expr/Expression;
    //   511: areturn
    //   512: aload_0
    //   513: aload #8
    //   515: invokevirtual flookup : (Lgnu/mapping/Symbol;)Lgnu/expr/Declaration;
    //   518: astore #7
    //   520: goto -> 50
    //   523: iload #5
    //   525: ifeq -> 557
    //   528: aload_0
    //   529: bipush #101
    //   531: new java/lang/StringBuilder
    //   534: dup
    //   535: invokespecial <init> : ()V
    //   538: ldc_w 'unknown function '
    //   541: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   544: aload #10
    //   546: invokevirtual append : (Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   549: invokevirtual toString : ()Ljava/lang/String;
    //   552: invokevirtual error : (CLjava/lang/String;)V
    //   555: aload_1
    //   556: areturn
    //   557: iload #6
    //   559: ifeq -> 598
    //   562: aload_0
    //   563: getfield messages : Lgnu/text/SourceMessages;
    //   566: bipush #101
    //   568: aload_1
    //   569: new java/lang/StringBuilder
    //   572: dup
    //   573: invokespecial <init> : ()V
    //   576: ldc_w 'unknown type '
    //   579: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   582: aload #10
    //   584: invokevirtual append : (Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   587: invokevirtual toString : ()Ljava/lang/String;
    //   590: ldc_w 'XPST0051'
    //   593: invokevirtual error : (CLgnu/text/SourceLocator;Ljava/lang/String;Ljava/lang/String;)V
    //   596: aload_1
    //   597: areturn
    //   598: aload_0
    //   599: getfield messages : Lgnu/text/SourceMessages;
    //   602: bipush #101
    //   604: aload_1
    //   605: new java/lang/StringBuilder
    //   608: dup
    //   609: invokespecial <init> : ()V
    //   612: ldc_w 'unknown variable $'
    //   615: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   618: aload #10
    //   620: invokevirtual append : (Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   623: invokevirtual toString : ()Ljava/lang/String;
    //   626: ldc_w 'XPST0008'
    //   629: invokevirtual error : (CLgnu/text/SourceLocator;Ljava/lang/String;Ljava/lang/String;)V
    //   632: aload_1
    //   633: areturn
  }
  
  protected Expression visitReferenceExp(ReferenceExp paramReferenceExp, Void paramVoid) {
    return visitReferenceExp(paramReferenceExp, (ApplyExp)null);
  }
  
  protected Expression visitSetExp(SetExp paramSetExp, Void paramVoid) {
    Expression expression = super.visitSetExp(paramSetExp, paramVoid);
    Declaration declaration = paramSetExp.getBinding();
    if (declaration != null && !(getCompilation()).immediate) {
      Object object = declaration.getSymbol();
      if (object instanceof Symbol && "http://www.w3.org/2005/xquery-local-functions".equals(((Symbol)object).getNamespaceURI())) {
        Expression expression1 = paramSetExp.getNewValue();
        if (!(expression1 instanceof ApplyExp) || ((ApplyExp)expression1).getFunction() != XQParser.getExternalFunction) {
          declaration.setFlag(16777216L);
          declaration.setPrivate(true);
        } 
      } 
    } 
    return expression;
  }
  
  Expression withCollator(Method paramMethod, Expression[] paramArrayOfExpression, String paramString, int paramInt) {
    return withCollator((Expression)new QuoteExp(new PrimProcedure(paramMethod)), paramArrayOfExpression, paramString, paramInt);
  }
  
  Expression withCollator(Expression paramExpression, Expression[] paramArrayOfExpression, String paramString, int paramInt) {
    paramString = WrongArguments.checkArgCount(paramString, paramInt, paramInt + 1, paramArrayOfExpression.length);
    if (paramString != null)
      return getCompilation().syntaxError(paramString); 
    Expression[] arrayOfExpression = new Expression[paramInt + 1];
    System.arraycopy(paramArrayOfExpression, 0, arrayOfExpression, 0, paramInt);
    arrayOfExpression[paramInt] = getCollator(paramArrayOfExpression, paramInt);
    return (Expression)new ApplyExp(paramExpression, arrayOfExpression);
  }
  
  Expression withContext(Method paramMethod, Expression[] paramArrayOfExpression, String paramString, int paramInt) {
    String str1;
    String str2 = WrongArguments.checkArgCount(paramString, paramInt, paramInt + 1, paramArrayOfExpression.length);
    if (str2 != null)
      return getCompilation().syntaxError(str2); 
    Expression[] arrayOfExpression = paramArrayOfExpression;
    if (paramArrayOfExpression.length == paramInt) {
      arrayOfExpression = new Expression[paramInt + 1];
      System.arraycopy(paramArrayOfExpression, 0, arrayOfExpression, 0, paramInt);
      Declaration declaration = this.lookup.lookup(XQParser.DOT_VARNAME, false);
      if (declaration == null) {
        str1 = "undefined context for " + paramString;
        this.messages.error('e', str1, "XPDY0002");
        return (Expression)new ErrorExp(str1);
      } 
      arrayOfExpression[paramInt] = (Expression)new ReferenceExp(declaration);
    } 
    return (Expression)new ApplyExp((Method)str1, arrayOfExpression);
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/gnu/xquery/lang/XQResolveNames.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */