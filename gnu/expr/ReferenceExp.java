package gnu.expr;

import gnu.bytecode.Type;
import gnu.kawa.util.IdentityHashTable;
import gnu.mapping.CallContext;
import gnu.mapping.Environment;
import gnu.mapping.OutPort;
import gnu.mapping.Symbol;

public class ReferenceExp extends AccessExp {
  public static final int DONT_DEREFERENCE = 2;
  
  public static final int PREFER_BINDING2 = 8;
  
  public static final int PROCEDURE_NAME = 4;
  
  public static final int TYPE_NAME = 16;
  
  static int counter;
  
  int id;
  
  public ReferenceExp(Declaration paramDeclaration) {
    this(paramDeclaration.getSymbol(), paramDeclaration);
  }
  
  public ReferenceExp(Object paramObject) {
    int i = counter + 1;
    counter = i;
    this.id = i;
    this.symbol = paramObject;
  }
  
  public ReferenceExp(Object paramObject, Declaration paramDeclaration) {
    int i = counter + 1;
    counter = i;
    this.id = i;
    this.symbol = paramObject;
    this.binding = paramDeclaration;
  }
  
  public void apply(CallContext paramCallContext) throws Throwable {
    // Byte code:
    //   0: aconst_null
    //   1: astore #4
    //   3: aload_0
    //   4: getfield binding : Lgnu/expr/Declaration;
    //   7: ifnull -> 147
    //   10: aload_0
    //   11: getfield binding : Lgnu/expr/Declaration;
    //   14: invokevirtual isAlias : ()Z
    //   17: ifeq -> 147
    //   20: aload_0
    //   21: invokevirtual getDontDereference : ()Z
    //   24: ifne -> 147
    //   27: aload_0
    //   28: getfield binding : Lgnu/expr/Declaration;
    //   31: getfield value : Lgnu/expr/Expression;
    //   34: instanceof gnu/expr/ReferenceExp
    //   37: ifeq -> 147
    //   40: aload_0
    //   41: getfield binding : Lgnu/expr/Declaration;
    //   44: getfield value : Lgnu/expr/Expression;
    //   47: checkcast gnu/expr/ReferenceExp
    //   50: astore_2
    //   51: aload_2
    //   52: invokevirtual getDontDereference : ()Z
    //   55: ifeq -> 100
    //   58: aload_2
    //   59: getfield binding : Lgnu/expr/Declaration;
    //   62: ifnull -> 100
    //   65: aload_2
    //   66: getfield binding : Lgnu/expr/Declaration;
    //   69: invokevirtual getValue : ()Lgnu/expr/Expression;
    //   72: astore_2
    //   73: aload_2
    //   74: instanceof gnu/expr/QuoteExp
    //   77: ifne -> 94
    //   80: aload_2
    //   81: instanceof gnu/expr/ReferenceExp
    //   84: ifne -> 94
    //   87: aload_2
    //   88: instanceof gnu/expr/LambdaExp
    //   91: ifeq -> 100
    //   94: aload_2
    //   95: aload_1
    //   96: invokevirtual apply : (Lgnu/mapping/CallContext;)V
    //   99: return
    //   100: aload_0
    //   101: getfield binding : Lgnu/expr/Declaration;
    //   104: getfield value : Lgnu/expr/Expression;
    //   107: aload_1
    //   108: invokevirtual eval : (Lgnu/mapping/CallContext;)Ljava/lang/Object;
    //   111: astore_2
    //   112: aload_2
    //   113: astore_3
    //   114: aload_0
    //   115: invokevirtual getDontDereference : ()Z
    //   118: ifne -> 141
    //   121: aload_2
    //   122: astore_3
    //   123: aload_0
    //   124: getfield binding : Lgnu/expr/Declaration;
    //   127: invokevirtual isIndirectBinding : ()Z
    //   130: ifeq -> 141
    //   133: aload_2
    //   134: checkcast gnu/mapping/Location
    //   137: invokevirtual get : ()Ljava/lang/Object;
    //   140: astore_3
    //   141: aload_1
    //   142: aload_3
    //   143: invokevirtual writeValue : (Ljava/lang/Object;)V
    //   146: return
    //   147: aload_0
    //   148: getfield binding : Lgnu/expr/Declaration;
    //   151: ifnull -> 301
    //   154: aload_0
    //   155: getfield binding : Lgnu/expr/Declaration;
    //   158: getfield field : Lgnu/bytecode/Field;
    //   161: ifnull -> 301
    //   164: aload_0
    //   165: getfield binding : Lgnu/expr/Declaration;
    //   168: getfield field : Lgnu/bytecode/Field;
    //   171: invokevirtual getDeclaringClass : ()Lgnu/bytecode/ClassType;
    //   174: invokevirtual isExisting : ()Z
    //   177: ifeq -> 301
    //   180: aload_0
    //   181: invokevirtual getDontDereference : ()Z
    //   184: ifeq -> 197
    //   187: aload_0
    //   188: getfield binding : Lgnu/expr/Declaration;
    //   191: invokevirtual isIndirectBinding : ()Z
    //   194: ifeq -> 301
    //   197: aload_0
    //   198: getfield binding : Lgnu/expr/Declaration;
    //   201: getfield field : Lgnu/bytecode/Field;
    //   204: invokevirtual getStaticFlag : ()Z
    //   207: ifeq -> 230
    //   210: aconst_null
    //   211: astore_2
    //   212: aload_0
    //   213: getfield binding : Lgnu/expr/Declaration;
    //   216: getfield field : Lgnu/bytecode/Field;
    //   219: invokevirtual getReflectField : ()Ljava/lang/reflect/Field;
    //   222: aload_2
    //   223: invokevirtual get : (Ljava/lang/Object;)Ljava/lang/Object;
    //   226: astore_2
    //   227: goto -> 112
    //   230: aload_0
    //   231: invokevirtual contextDecl : ()Lgnu/expr/Declaration;
    //   234: invokevirtual getValue : ()Lgnu/expr/Expression;
    //   237: aload_1
    //   238: invokevirtual eval : (Lgnu/mapping/CallContext;)Ljava/lang/Object;
    //   241: astore_2
    //   242: goto -> 212
    //   245: astore_1
    //   246: new gnu/mapping/UnboundLocationException
    //   249: dup
    //   250: new java/lang/StringBuilder
    //   253: dup
    //   254: invokespecial <init> : ()V
    //   257: ldc 'exception evaluating '
    //   259: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   262: aload_0
    //   263: getfield symbol : Ljava/lang/Object;
    //   266: invokevirtual append : (Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   269: ldc ' from '
    //   271: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   274: aload_0
    //   275: getfield binding : Lgnu/expr/Declaration;
    //   278: getfield field : Lgnu/bytecode/Field;
    //   281: invokevirtual append : (Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   284: ldc ' - '
    //   286: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   289: aload_1
    //   290: invokevirtual append : (Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   293: invokevirtual toString : ()Ljava/lang/String;
    //   296: aload_0
    //   297: invokespecial <init> : (Ljava/lang/Object;Lgnu/text/SourceLocator;)V
    //   300: athrow
    //   301: aload_0
    //   302: getfield binding : Lgnu/expr/Declaration;
    //   305: ifnull -> 379
    //   308: aload_0
    //   309: getfield binding : Lgnu/expr/Declaration;
    //   312: getfield value : Lgnu/expr/Expression;
    //   315: instanceof gnu/expr/QuoteExp
    //   318: ifne -> 334
    //   321: aload_0
    //   322: getfield binding : Lgnu/expr/Declaration;
    //   325: getfield value : Lgnu/expr/Expression;
    //   328: instanceof gnu/expr/LambdaExp
    //   331: ifeq -> 379
    //   334: aload_0
    //   335: getfield binding : Lgnu/expr/Declaration;
    //   338: getfield value : Lgnu/expr/Expression;
    //   341: getstatic gnu/expr/QuoteExp.undefined_exp : Lgnu/expr/QuoteExp;
    //   344: if_acmpeq -> 379
    //   347: aload_0
    //   348: invokevirtual getDontDereference : ()Z
    //   351: ifeq -> 364
    //   354: aload_0
    //   355: getfield binding : Lgnu/expr/Declaration;
    //   358: invokevirtual isIndirectBinding : ()Z
    //   361: ifeq -> 379
    //   364: aload_0
    //   365: getfield binding : Lgnu/expr/Declaration;
    //   368: getfield value : Lgnu/expr/Expression;
    //   371: aload_1
    //   372: invokevirtual eval : (Lgnu/mapping/CallContext;)Ljava/lang/Object;
    //   375: astore_2
    //   376: goto -> 112
    //   379: aload_0
    //   380: getfield binding : Lgnu/expr/Declaration;
    //   383: ifnull -> 409
    //   386: aload_0
    //   387: getfield binding : Lgnu/expr/Declaration;
    //   390: getfield context : Lgnu/expr/ScopeExp;
    //   393: instanceof gnu/expr/ModuleExp
    //   396: ifeq -> 531
    //   399: aload_0
    //   400: getfield binding : Lgnu/expr/Declaration;
    //   403: invokevirtual isPrivate : ()Z
    //   406: ifne -> 531
    //   409: invokestatic getCurrent : ()Lgnu/mapping/Environment;
    //   412: astore #5
    //   414: aload_0
    //   415: getfield symbol : Ljava/lang/Object;
    //   418: instanceof gnu/mapping/Symbol
    //   421: ifeq -> 479
    //   424: aload_0
    //   425: getfield symbol : Ljava/lang/Object;
    //   428: checkcast gnu/mapping/Symbol
    //   431: astore_2
    //   432: aload #4
    //   434: astore_3
    //   435: aload_0
    //   436: bipush #8
    //   438: invokevirtual getFlag : (I)Z
    //   441: ifeq -> 458
    //   444: aload #4
    //   446: astore_3
    //   447: aload_0
    //   448: invokevirtual isProcedureName : ()Z
    //   451: ifeq -> 458
    //   454: getstatic gnu/mapping/EnvironmentKey.FUNCTION : Ljava/lang/Object;
    //   457: astore_3
    //   458: aload_0
    //   459: invokevirtual getDontDereference : ()Z
    //   462: ifeq -> 495
    //   465: aload #5
    //   467: aload_2
    //   468: aload_3
    //   469: invokevirtual getLocation : (Lgnu/mapping/Symbol;Ljava/lang/Object;)Lgnu/mapping/Location;
    //   472: astore_3
    //   473: aload_1
    //   474: aload_3
    //   475: invokevirtual writeValue : (Ljava/lang/Object;)V
    //   478: return
    //   479: aload #5
    //   481: aload_0
    //   482: getfield symbol : Ljava/lang/Object;
    //   485: invokevirtual toString : ()Ljava/lang/String;
    //   488: invokevirtual getSymbol : (Ljava/lang/String;)Lgnu/mapping/Symbol;
    //   491: astore_2
    //   492: goto -> 432
    //   495: getstatic gnu/mapping/Location.UNBOUND : Ljava/lang/String;
    //   498: astore #6
    //   500: aload #5
    //   502: aload_2
    //   503: aload_3
    //   504: aload #6
    //   506: invokevirtual get : (Lgnu/mapping/Symbol;Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   509: astore #4
    //   511: aload #4
    //   513: astore_3
    //   514: aload #4
    //   516: aload #6
    //   518: if_acmpne -> 473
    //   521: new gnu/mapping/UnboundLocationException
    //   524: dup
    //   525: aload_2
    //   526: aload_0
    //   527: invokespecial <init> : (Ljava/lang/Object;Lgnu/text/SourceLocator;)V
    //   530: athrow
    //   531: aload_1
    //   532: getfield evalFrames : [[Ljava/lang/Object;
    //   535: aload_0
    //   536: getfield binding : Lgnu/expr/Declaration;
    //   539: getfield context : Lgnu/expr/ScopeExp;
    //   542: invokestatic nesting : (Lgnu/expr/ScopeExp;)I
    //   545: aaload
    //   546: aload_0
    //   547: getfield binding : Lgnu/expr/Declaration;
    //   550: getfield evalIndex : I
    //   553: aaload
    //   554: astore_2
    //   555: goto -> 112
    // Exception table:
    //   from	to	target	type
    //   197	210	245	java/lang/Exception
    //   212	227	245	java/lang/Exception
    //   230	242	245	java/lang/Exception
  }
  
  public void compile(Compilation paramCompilation, Target paramTarget) {
    if (!(paramTarget instanceof ConsumerTarget) || !((ConsumerTarget)paramTarget).compileWrite(this, paramCompilation))
      this.binding.load(this, this.flags, paramCompilation, paramTarget); 
  }
  
  protected Expression deepCopy(IdentityHashTable paramIdentityHashTable) {
    Declaration declaration = (Declaration)paramIdentityHashTable.get(this.binding, this.binding);
    ReferenceExp referenceExp = new ReferenceExp(paramIdentityHashTable.get(this.symbol, this.symbol), declaration);
    referenceExp.flags = getFlags();
    return referenceExp;
  }
  
  public final boolean getDontDereference() {
    return ((this.flags & 0x2) != 0);
  }
  
  public Type getType() {
    // Byte code:
    //   0: aload_0
    //   1: getfield binding : Lgnu/expr/Declaration;
    //   4: astore_1
    //   5: aload_1
    //   6: ifnull -> 16
    //   9: aload_1
    //   10: invokevirtual isFluid : ()Z
    //   13: ifeq -> 22
    //   16: getstatic gnu/bytecode/Type.pointer_type : Lgnu/bytecode/ClassType;
    //   19: astore_2
    //   20: aload_2
    //   21: areturn
    //   22: aload_0
    //   23: invokevirtual getDontDereference : ()Z
    //   26: ifeq -> 33
    //   29: getstatic gnu/expr/Compilation.typeLocation : Lgnu/bytecode/ClassType;
    //   32: areturn
    //   33: aload_1
    //   34: invokestatic followAliases : (Lgnu/expr/Declaration;)Lgnu/expr/Declaration;
    //   37: astore_3
    //   38: aload_3
    //   39: invokevirtual getType : ()Lgnu/bytecode/Type;
    //   42: astore_2
    //   43: aload_2
    //   44: ifnull -> 56
    //   47: aload_2
    //   48: astore_1
    //   49: aload_2
    //   50: getstatic gnu/bytecode/Type.pointer_type : Lgnu/bytecode/ClassType;
    //   53: if_acmpne -> 100
    //   56: aload_3
    //   57: invokevirtual getValue : ()Lgnu/expr/Expression;
    //   60: astore #4
    //   62: aload_2
    //   63: astore_1
    //   64: aload #4
    //   66: ifnull -> 100
    //   69: aload_2
    //   70: astore_1
    //   71: aload #4
    //   73: getstatic gnu/expr/QuoteExp.undefined_exp : Lgnu/expr/QuoteExp;
    //   76: if_acmpeq -> 100
    //   79: aload_3
    //   80: getfield value : Lgnu/expr/Expression;
    //   83: astore_2
    //   84: aload_3
    //   85: aconst_null
    //   86: putfield value : Lgnu/expr/Expression;
    //   89: aload #4
    //   91: invokevirtual getType : ()Lgnu/bytecode/Type;
    //   94: astore_1
    //   95: aload_3
    //   96: aload_2
    //   97: putfield value : Lgnu/expr/Expression;
    //   100: aload_1
    //   101: astore_2
    //   102: aload_1
    //   103: getstatic gnu/bytecode/Type.toStringType : Lgnu/bytecode/ClassType;
    //   106: if_acmpne -> 20
    //   109: getstatic gnu/bytecode/Type.javalangStringType : Lgnu/bytecode/ClassType;
    //   112: areturn
  }
  
  public final boolean isProcedureName() {
    return ((this.flags & 0x4) != 0);
  }
  
  public boolean isSingleValue() {
    return (this.binding != null && this.binding.getFlag(262144L)) ? true : super.isSingleValue();
  }
  
  public final boolean isUnknown() {
    return Declaration.isUnknown(this.binding);
  }
  
  protected boolean mustCompile() {
    return false;
  }
  
  public void print(OutPort paramOutPort) {
    paramOutPort.print("(Ref/");
    paramOutPort.print(this.id);
    if (this.symbol != null && (this.binding == null || this.symbol.toString() != this.binding.getName())) {
      paramOutPort.print('/');
      paramOutPort.print(this.symbol);
    } 
    if (this.binding != null) {
      paramOutPort.print('/');
      paramOutPort.print(this.binding);
    } 
    paramOutPort.print(")");
  }
  
  public final void setDontDereference(boolean paramBoolean) {
    setFlag(paramBoolean, 2);
  }
  
  public final void setProcedureName(boolean paramBoolean) {
    setFlag(paramBoolean, 4);
  }
  
  public boolean side_effects() {
    return (this.binding == null || !this.binding.isLexical());
  }
  
  public String toString() {
    return "RefExp/" + this.symbol + '/' + this.id + '/';
  }
  
  public Expression validateApply(ApplyExp paramApplyExp, InlineCalls paramInlineCalls, Type paramType, Declaration paramDeclaration) {
    paramDeclaration = this.binding;
    if (paramDeclaration != null && !paramDeclaration.getFlag(65536L)) {
      paramDeclaration = Declaration.followAliases(paramDeclaration);
      if (!paramDeclaration.isIndirectBinding()) {
        Expression expression = paramDeclaration.getValue();
        if (expression != null)
          return expression.validateApply(paramApplyExp, paramInlineCalls, paramType, paramDeclaration); 
      } 
    } else if (getSymbol() instanceof Symbol) {
      Symbol symbol = (Symbol)getSymbol();
      Object object = Environment.getCurrent().getFunction(symbol, null);
      if (object instanceof gnu.mapping.Procedure)
        return (new QuoteExp(object)).validateApply(paramApplyExp, paramInlineCalls, paramType, null); 
    } 
    paramApplyExp.visitArgs(paramInlineCalls);
    return paramApplyExp;
  }
  
  public final Object valueIfConstant() {
    if (this.binding != null) {
      Expression expression = this.binding.getValue();
      if (expression != null)
        return expression.valueIfConstant(); 
    } 
    return null;
  }
  
  protected <R, D> R visit(ExpVisitor<R, D> paramExpVisitor, D paramD) {
    return paramExpVisitor.visitReferenceExp(this, paramD);
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/gnu/expr/ReferenceExp.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */