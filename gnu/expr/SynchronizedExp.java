package gnu.expr;

import gnu.bytecode.CodeAttr;
import gnu.bytecode.Type;
import gnu.bytecode.Variable;
import gnu.mapping.CallContext;
import gnu.mapping.OutPort;

public class SynchronizedExp extends Expression {
  Expression body;
  
  Expression object;
  
  public SynchronizedExp(Expression paramExpression1, Expression paramExpression2) {
    this.object = paramExpression1;
    this.body = paramExpression2;
  }
  
  public void apply(CallContext paramCallContext) throws Throwable {
    synchronized (this.object.eval(paramCallContext)) {
      Object object = this.body.eval(paramCallContext);
      paramCallContext.writeValue(object);
      return;
    } 
  }
  
  public void compile(Compilation paramCompilation, Target paramTarget) {
    Type type;
    CodeAttr codeAttr = paramCompilation.getCode();
    this.object.compile(paramCompilation, Target.pushObject);
    codeAttr.emitDup(1);
    Variable variable = codeAttr.pushScope().addVariable(codeAttr, (Type)Type.pointer_type, null);
    codeAttr.emitStore(variable);
    codeAttr.emitMonitorEnter();
    if (paramTarget instanceof IgnoreTarget || paramTarget instanceof ConsumerTarget) {
      type = null;
    } else {
      type = paramTarget.getType();
    } 
    codeAttr.emitTryStart(false, type);
    this.body.compileWithPosition(paramCompilation, paramTarget);
    codeAttr.emitLoad(variable);
    codeAttr.emitMonitorExit();
    codeAttr.emitTryEnd();
    codeAttr.emitCatchStart(null);
    codeAttr.emitLoad(variable);
    codeAttr.emitMonitorExit();
    codeAttr.emitThrow();
    codeAttr.emitCatchEnd();
    codeAttr.emitTryCatchEnd();
    codeAttr.popScope();
  }
  
  protected boolean mustCompile() {
    return false;
  }
  
  public void print(OutPort paramOutPort) {
    paramOutPort.print("(Synchronized ");
    this.object.print(paramOutPort);
    paramOutPort.print(" ");
    this.body.print(paramOutPort);
    paramOutPort.print(")");
  }
  
  protected <R, D> R visit(ExpVisitor<R, D> paramExpVisitor, D paramD) {
    return paramExpVisitor.visitSynchronizedExp(this, paramD);
  }
  
  protected <R, D> void visitChildren(ExpVisitor<R, D> paramExpVisitor, D paramD) {
    this.object = paramExpVisitor.visitAndUpdate(this.object, paramD);
    if (paramExpVisitor.exitValue == null)
      this.body = paramExpVisitor.visitAndUpdate(this.body, paramD); 
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/gnu/expr/SynchronizedExp.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */