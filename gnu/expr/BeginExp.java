package gnu.expr;

import gnu.bytecode.Type;
import gnu.lists.Consumer;
import gnu.lists.VoidConsumer;
import gnu.mapping.CallContext;
import gnu.mapping.OutPort;
import java.util.Vector;

public class BeginExp extends Expression {
  Vector compileOptions;
  
  Expression[] exps;
  
  int length;
  
  public BeginExp() {}
  
  public BeginExp(Expression paramExpression1, Expression paramExpression2) {
    this.exps = new Expression[2];
    this.exps[0] = paramExpression1;
    this.exps[1] = paramExpression2;
    this.length = 2;
  }
  
  public BeginExp(Expression[] paramArrayOfExpression) {
    this.exps = paramArrayOfExpression;
    this.length = paramArrayOfExpression.length;
  }
  
  public static final Expression canonicalize(Expression paramExpression) {
    if (paramExpression instanceof BeginExp) {
      BeginExp beginExp = (BeginExp)paramExpression;
      if (beginExp.compileOptions == null) {
        int i = beginExp.length;
        if (i == 0)
          return QuoteExp.voidExp; 
        if (i == 1)
          return canonicalize(beginExp.exps[0]); 
      } 
    } 
    return paramExpression;
  }
  
  public static final Expression canonicalize(Expression[] paramArrayOfExpression) {
    int i = paramArrayOfExpression.length;
    return (i == 0) ? QuoteExp.voidExp : ((i == 1) ? canonicalize(paramArrayOfExpression[0]) : new BeginExp(paramArrayOfExpression));
  }
  
  public final void add(Expression paramExpression) {
    if (this.exps == null)
      this.exps = new Expression[8]; 
    if (this.length == this.exps.length) {
      Expression[] arrayOfExpression1 = new Expression[this.length * 2];
      System.arraycopy(this.exps, 0, arrayOfExpression1, 0, this.length);
      this.exps = arrayOfExpression1;
    } 
    Expression[] arrayOfExpression = this.exps;
    int i = this.length;
    this.length = i + 1;
    arrayOfExpression[i] = paramExpression;
  }
  
  public void apply(CallContext paramCallContext) throws Throwable {
    int j = this.length;
    Consumer consumer = paramCallContext.consumer;
    paramCallContext.consumer = (Consumer)VoidConsumer.instance;
    int i = 0;
    while (true) {
      if (i < j - 1) {
        try {
          this.exps[i].eval(paramCallContext);
        } finally {
          paramCallContext.consumer = consumer;
        } 
        continue;
      } 
      paramCallContext.consumer = consumer;
      this.exps[i].apply(paramCallContext);
      return;
    } 
  }
  
  public void compile(Compilation paramCompilation, Target paramTarget) {
    pushOptions(paramCompilation);
    try {
      int j = this.length;
      int i;
      for (i = 0; i < j - 1; i++)
        this.exps[i].compileWithPosition(paramCompilation, Target.Ignore); 
      this.exps[i].compileWithPosition(paramCompilation, paramTarget);
      return;
    } finally {
      popOptions(paramCompilation);
    } 
  }
  
  public final int getExpressionCount() {
    return this.length;
  }
  
  public final Expression[] getExpressions() {
    return this.exps;
  }
  
  public Type getType() {
    return this.exps[this.length - 1].getType();
  }
  
  protected boolean mustCompile() {
    return false;
  }
  
  public void popOptions(Compilation paramCompilation) {
    if (this.compileOptions != null && paramCompilation != null)
      paramCompilation.currentOptions.popOptionValues(this.compileOptions); 
  }
  
  public void print(OutPort paramOutPort) {
    paramOutPort.startLogicalBlock("(Begin", ")", 2);
    paramOutPort.writeSpaceFill();
    printLineColumn(paramOutPort);
    if (this.compileOptions != null) {
      paramOutPort.writeSpaceFill();
      paramOutPort.startLogicalBlock("(CompileOptions", ")", 2);
      int m = this.compileOptions.size();
      for (int k = 0; k < m; k += 3) {
        Object object1 = this.compileOptions.elementAt(k);
        Object object2 = this.compileOptions.elementAt(k + 2);
        paramOutPort.writeSpaceFill();
        paramOutPort.startLogicalBlock("", "", 2);
        paramOutPort.print(object1);
        paramOutPort.print(':');
        paramOutPort.writeSpaceLinear();
        paramOutPort.print(object2);
        paramOutPort.endLogicalBlock("");
      } 
      paramOutPort.endLogicalBlock(")");
    } 
    int j = this.length;
    for (int i = 0; i < j; i++) {
      paramOutPort.writeSpaceLinear();
      this.exps[i].print(paramOutPort);
    } 
    paramOutPort.endLogicalBlock(")");
  }
  
  public void pushOptions(Compilation paramCompilation) {
    if (this.compileOptions != null && paramCompilation != null)
      paramCompilation.currentOptions.pushOptionValues(this.compileOptions); 
  }
  
  public void setCompileOptions(Vector paramVector) {
    this.compileOptions = paramVector;
  }
  
  public final void setExpressions(Expression[] paramArrayOfExpression) {
    this.exps = paramArrayOfExpression;
    this.length = paramArrayOfExpression.length;
  }
  
  protected <R, D> R visit(ExpVisitor<R, D> paramExpVisitor, D paramD) {
    pushOptions(paramExpVisitor.comp);
    try {
      paramD = (D)paramExpVisitor.visitBeginExp(this, paramD);
      return (R)paramD;
    } finally {
      popOptions(paramExpVisitor.comp);
    } 
  }
  
  protected <R, D> void visitChildren(ExpVisitor<R, D> paramExpVisitor, D paramD) {
    this.exps = paramExpVisitor.visitExps(this.exps, this.length, paramD);
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/gnu/expr/BeginExp.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */