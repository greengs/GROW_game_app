package gnu.expr;

import gnu.bytecode.ClassType;
import gnu.bytecode.CodeAttr;
import gnu.bytecode.Type;
import gnu.mapping.CallContext;
import gnu.mapping.OutPort;

public class TryExp extends Expression {
  CatchClause catch_clauses;
  
  Expression finally_clause;
  
  Expression try_clause;
  
  public TryExp(Expression paramExpression1, Expression paramExpression2) {
    this.try_clause = paramExpression1;
    this.finally_clause = paramExpression2;
  }
  
  public void apply(CallContext paramCallContext) throws Throwable {
    try {
      this.try_clause.apply(paramCallContext);
      paramCallContext.runUntilDone();
      return;
    } catch (Throwable throwable) {
      for (CatchClause catchClause = this.catch_clauses; catchClause != null; catchClause = catchClause.next) {
        if (((ClassType)catchClause.firstDecl().getTypeExp().eval(paramCallContext)).isInstance(throwable)) {
          paramCallContext.value1 = throwable;
          catchClause.apply(paramCallContext);
          return;
        } 
      } 
      throw throwable;
    } finally {
      if (this.finally_clause != null)
        this.finally_clause.eval(paramCallContext); 
    } 
  }
  
  public void compile(Compilation paramCompilation, Target paramTarget) {
    boolean bool;
    Target target;
    Type type;
    CodeAttr codeAttr = paramCompilation.getCode();
    if (this.finally_clause != null) {
      bool = true;
    } else {
      bool = false;
    } 
    if (paramTarget instanceof StackTarget || paramTarget instanceof ConsumerTarget || paramTarget instanceof IgnoreTarget || (paramTarget instanceof ConditionalTarget && this.finally_clause == null)) {
      target = paramTarget;
    } else {
      target = Target.pushValue(paramTarget.getType());
    } 
    if (target instanceof StackTarget) {
      type = target.getType();
    } else {
      type = null;
    } 
    codeAttr.emitTryStart(bool, type);
    this.try_clause.compileWithPosition(paramCompilation, target);
    for (CatchClause catchClause = this.catch_clauses; catchClause != null; catchClause = catchClause.getNext())
      catchClause.compile(paramCompilation, target); 
    if (this.finally_clause != null) {
      codeAttr.emitFinallyStart();
      this.finally_clause.compileWithPosition(paramCompilation, Target.Ignore);
      codeAttr.emitFinallyEnd();
    } 
    codeAttr.emitTryCatchEnd();
    if (target != paramTarget)
      paramTarget.compileFromStack(paramCompilation, paramTarget.getType()); 
  }
  
  public final CatchClause getCatchClauses() {
    return this.catch_clauses;
  }
  
  public final Expression getFinallyClause() {
    return this.finally_clause;
  }
  
  public Type getType() {
    return (this.catch_clauses == null) ? this.try_clause.getType() : super.getType();
  }
  
  protected boolean mustCompile() {
    return false;
  }
  
  public void print(OutPort paramOutPort) {
    paramOutPort.startLogicalBlock("(Try", ")", 2);
    paramOutPort.writeSpaceFill();
    this.try_clause.print(paramOutPort);
    for (CatchClause catchClause = this.catch_clauses; catchClause != null; catchClause = catchClause.getNext())
      catchClause.print(paramOutPort); 
    if (this.finally_clause != null) {
      paramOutPort.writeSpaceLinear();
      paramOutPort.print(" finally: ");
      this.finally_clause.print(paramOutPort);
    } 
    paramOutPort.endLogicalBlock(")");
  }
  
  public final void setCatchClauses(CatchClause paramCatchClause) {
    this.catch_clauses = paramCatchClause;
  }
  
  protected <R, D> R visit(ExpVisitor<R, D> paramExpVisitor, D paramD) {
    return paramExpVisitor.visitTryExp(this, paramD);
  }
  
  protected <R, D> void visitChildren(ExpVisitor<R, D> paramExpVisitor, D paramD) {
    this.try_clause = paramExpVisitor.visitAndUpdate(this.try_clause, paramD);
    for (CatchClause catchClause = this.catch_clauses; paramExpVisitor.exitValue == null && catchClause != null; catchClause = catchClause.getNext())
      paramExpVisitor.visit(catchClause, paramD); 
    if (paramExpVisitor.exitValue == null && this.finally_clause != null)
      this.finally_clause = paramExpVisitor.visitAndUpdate(this.finally_clause, paramD); 
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/gnu/expr/TryExp.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */