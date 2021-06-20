package gnu.expr;

import gnu.bytecode.PrimType;
import gnu.bytecode.Type;
import gnu.mapping.CallContext;
import gnu.mapping.OutPort;

public class IfExp extends Expression {
  Expression else_clause;
  
  Expression test;
  
  Expression then_clause;
  
  public IfExp(Expression paramExpression1, Expression paramExpression2, Expression paramExpression3) {
    this.test = paramExpression1;
    this.then_clause = paramExpression2;
    this.else_clause = paramExpression3;
  }
  
  public static void compile(Expression paramExpression1, Expression paramExpression2, Expression paramExpression3, Compilation paramCompilation, Target paramTarget) {
    // Byte code:
    //   0: aload_3
    //   1: invokevirtual getLanguage : ()Lgnu/expr/Language;
    //   4: astore #10
    //   6: aload_3
    //   7: invokevirtual getCode : ()Lgnu/bytecode/CodeAttr;
    //   10: astore #9
    //   12: aconst_null
    //   13: astore #8
    //   15: aload #4
    //   17: instanceof gnu/expr/ConditionalTarget
    //   20: ifeq -> 237
    //   23: aload_2
    //   24: instanceof gnu/expr/QuoteExp
    //   27: ifeq -> 237
    //   30: iconst_1
    //   31: istore #5
    //   33: aload #10
    //   35: aload_2
    //   36: checkcast gnu/expr/QuoteExp
    //   39: invokevirtual getValue : ()Ljava/lang/Object;
    //   42: invokevirtual isTrue : (Ljava/lang/Object;)Z
    //   45: ifeq -> 224
    //   48: aload #4
    //   50: checkcast gnu/expr/ConditionalTarget
    //   53: getfield ifTrue : Lgnu/bytecode/Label;
    //   56: astore #7
    //   58: aload #7
    //   60: astore #8
    //   62: aload #7
    //   64: ifnonnull -> 78
    //   67: new gnu/bytecode/Label
    //   70: dup
    //   71: aload #9
    //   73: invokespecial <init> : (Lgnu/bytecode/CodeAttr;)V
    //   76: astore #8
    //   78: aload_0
    //   79: aload_1
    //   80: if_acmpne -> 324
    //   83: aload #4
    //   85: instanceof gnu/expr/ConditionalTarget
    //   88: ifeq -> 324
    //   91: aload_1
    //   92: instanceof gnu/expr/ReferenceExp
    //   95: ifeq -> 324
    //   98: iconst_1
    //   99: istore #6
    //   101: aload #4
    //   103: checkcast gnu/expr/ConditionalTarget
    //   106: getfield ifTrue : Lgnu/bytecode/Label;
    //   109: astore #7
    //   111: new gnu/expr/ConditionalTarget
    //   114: dup
    //   115: aload #7
    //   117: aload #8
    //   119: aload #10
    //   121: invokespecial <init> : (Lgnu/bytecode/Label;Lgnu/bytecode/Label;Lgnu/expr/Language;)V
    //   124: astore #10
    //   126: iload #6
    //   128: ifeq -> 137
    //   131: aload #10
    //   133: iconst_0
    //   134: putfield trueBranchComesFirst : Z
    //   137: aload_0
    //   138: aload_3
    //   139: aload #10
    //   141: invokevirtual compile : (Lgnu/expr/Compilation;Lgnu/expr/Target;)V
    //   144: aload #9
    //   146: invokevirtual emitIfThen : ()V
    //   149: iload #6
    //   151: ifne -> 178
    //   154: aload #7
    //   156: aload #9
    //   158: invokevirtual define : (Lgnu/bytecode/CodeAttr;)V
    //   161: aload_3
    //   162: getfield callContextVar : Lgnu/bytecode/Variable;
    //   165: astore_0
    //   166: aload_1
    //   167: aload_3
    //   168: aload #4
    //   170: invokevirtual compileWithPosition : (Lgnu/expr/Compilation;Lgnu/expr/Target;)V
    //   173: aload_3
    //   174: aload_0
    //   175: putfield callContextVar : Lgnu/bytecode/Variable;
    //   178: iload #5
    //   180: ifne -> 351
    //   183: aload #9
    //   185: invokevirtual emitElse : ()V
    //   188: aload #8
    //   190: aload #9
    //   192: invokevirtual define : (Lgnu/bytecode/CodeAttr;)V
    //   195: aload_3
    //   196: getfield callContextVar : Lgnu/bytecode/Variable;
    //   199: astore_0
    //   200: aload_2
    //   201: ifnonnull -> 341
    //   204: aload_3
    //   205: getstatic gnu/mapping/Values.empty : Lgnu/mapping/Values;
    //   208: aload #4
    //   210: invokevirtual compileConstant : (Ljava/lang/Object;Lgnu/expr/Target;)V
    //   213: aload_3
    //   214: aload_0
    //   215: putfield callContextVar : Lgnu/bytecode/Variable;
    //   218: aload #9
    //   220: invokevirtual emitFi : ()V
    //   223: return
    //   224: aload #4
    //   226: checkcast gnu/expr/ConditionalTarget
    //   229: getfield ifFalse : Lgnu/bytecode/Label;
    //   232: astore #7
    //   234: goto -> 58
    //   237: aload #8
    //   239: astore #7
    //   241: aload_2
    //   242: instanceof gnu/expr/ExitExp
    //   245: ifeq -> 318
    //   248: aload #8
    //   250: astore #7
    //   252: aload_2
    //   253: checkcast gnu/expr/ExitExp
    //   256: getfield result : Lgnu/expr/Expression;
    //   259: instanceof gnu/expr/QuoteExp
    //   262: ifeq -> 318
    //   265: aload_2
    //   266: checkcast gnu/expr/ExitExp
    //   269: getfield block : Lgnu/expr/BlockExp;
    //   272: astore #11
    //   274: aload #8
    //   276: astore #7
    //   278: aload #11
    //   280: getfield exitTarget : Lgnu/expr/Target;
    //   283: instanceof gnu/expr/IgnoreTarget
    //   286: ifeq -> 318
    //   289: aload #11
    //   291: getfield exitableBlock : Lgnu/bytecode/ExitableBlock;
    //   294: invokevirtual exitIsGoto : ()Lgnu/bytecode/Label;
    //   297: astore #8
    //   299: aload #8
    //   301: astore #7
    //   303: aload #8
    //   305: ifnull -> 318
    //   308: iconst_1
    //   309: istore #5
    //   311: aload #8
    //   313: astore #7
    //   315: goto -> 58
    //   318: iconst_0
    //   319: istore #5
    //   321: goto -> 58
    //   324: iconst_0
    //   325: istore #6
    //   327: new gnu/bytecode/Label
    //   330: dup
    //   331: aload #9
    //   333: invokespecial <init> : (Lgnu/bytecode/CodeAttr;)V
    //   336: astore #7
    //   338: goto -> 111
    //   341: aload_2
    //   342: aload_3
    //   343: aload #4
    //   345: invokevirtual compileWithPosition : (Lgnu/expr/Compilation;Lgnu/expr/Target;)V
    //   348: goto -> 213
    //   351: aload #9
    //   353: invokevirtual setUnreachable : ()V
    //   356: goto -> 218
  }
  
  public void apply(CallContext paramCallContext) throws Throwable {
    if (getLanguage().isTrue(this.test.eval(paramCallContext))) {
      this.then_clause.apply(paramCallContext);
      return;
    } 
    if (this.else_clause != null) {
      this.else_clause.apply(paramCallContext);
      return;
    } 
  }
  
  public void compile(Compilation paramCompilation, Target paramTarget) {
    Expression expression1;
    Expression expression2 = this.test;
    Expression expression3 = this.then_clause;
    if (this.else_clause == null) {
      expression1 = QuoteExp.voidExp;
    } else {
      expression1 = this.else_clause;
    } 
    compile(expression2, expression3, expression1, paramCompilation, paramTarget);
  }
  
  public Expression getElseClause() {
    return this.else_clause;
  }
  
  protected final Language getLanguage() {
    return Language.getDefaultLanguage();
  }
  
  public Expression getTest() {
    return this.test;
  }
  
  public Expression getThenClause() {
    return this.then_clause;
  }
  
  public Type getType() {
    Type type2 = this.then_clause.getType();
    if (this.else_clause == null) {
      PrimType primType = Type.voidType;
      return Language.unionType(type2, (Type)primType);
    } 
    Type type1 = this.else_clause.getType();
    return Language.unionType(type2, type1);
  }
  
  protected boolean mustCompile() {
    return false;
  }
  
  public void print(OutPort paramOutPort) {
    paramOutPort.startLogicalBlock("(If ", false, ")");
    paramOutPort.setIndentation(-2, false);
    this.test.print(paramOutPort);
    paramOutPort.writeSpaceLinear();
    this.then_clause.print(paramOutPort);
    if (this.else_clause != null) {
      paramOutPort.writeSpaceLinear();
      this.else_clause.print(paramOutPort);
    } 
    paramOutPort.endLogicalBlock(")");
  }
  
  Expression select(boolean paramBoolean) {
    return paramBoolean ? this.then_clause : ((this.else_clause == null) ? QuoteExp.voidExp : this.else_clause);
  }
  
  protected <R, D> R visit(ExpVisitor<R, D> paramExpVisitor, D paramD) {
    return paramExpVisitor.visitIfExp(this, paramD);
  }
  
  protected <R, D> void visitChildren(ExpVisitor<R, D> paramExpVisitor, D paramD) {
    this.test = paramExpVisitor.visitAndUpdate(this.test, paramD);
    if (paramExpVisitor.exitValue == null)
      this.then_clause = paramExpVisitor.visitAndUpdate(this.then_clause, paramD); 
    if (paramExpVisitor.exitValue == null && this.else_clause != null)
      this.else_clause = paramExpVisitor.visitAndUpdate(this.else_clause, paramD); 
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/gnu/expr/IfExp.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */