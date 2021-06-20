package gnu.expr;

import gnu.bytecode.CodeAttr;
import gnu.bytecode.ExitableBlock;
import gnu.bytecode.Label;
import gnu.bytecode.Type;
import gnu.mapping.CallContext;
import gnu.mapping.OutPort;

public class BlockExp extends Expression {
  Expression body;
  
  Expression exitBody;
  
  Target exitTarget;
  
  ExitableBlock exitableBlock;
  
  Declaration label;
  
  public void apply(CallContext paramCallContext) throws Throwable {
    try {
      object = this.body.eval(paramCallContext);
    } catch (BlockExitException object) {}
    paramCallContext.writeValue(object);
  }
  
  public void compile(Compilation paramCompilation, Target paramTarget) {
    Type type;
    Target target;
    CodeAttr codeAttr = paramCompilation.getCode();
    if (this.exitBody == null && paramTarget instanceof StackTarget) {
      type = paramTarget.getType();
    } else {
      type = null;
    } 
    this.exitableBlock = codeAttr.startExitableBlock(type, true);
    if (this.exitBody == null) {
      target = paramTarget;
    } else {
      target = Target.Ignore;
    } 
    this.exitTarget = target;
    this.body.compileWithPosition(paramCompilation, paramTarget);
    if (this.exitBody != null) {
      Label label = new Label(codeAttr);
      codeAttr.emitGoto(label);
      codeAttr.endExitableBlock();
      this.exitBody.compileWithPosition(paramCompilation, paramTarget);
      label.define(codeAttr);
    } else {
      codeAttr.endExitableBlock();
    } 
    this.exitableBlock = null;
  }
  
  protected boolean mustCompile() {
    return false;
  }
  
  public void print(OutPort paramOutPort) {
    paramOutPort.startLogicalBlock("(Block", ")", 2);
    if (this.label != null) {
      paramOutPort.print(' ');
      paramOutPort.print(this.label.getName());
    } 
    paramOutPort.writeSpaceLinear();
    this.body.print(paramOutPort);
    if (this.exitBody != null) {
      paramOutPort.writeSpaceLinear();
      paramOutPort.print("else ");
      this.exitBody.print(paramOutPort);
    } 
    paramOutPort.endLogicalBlock(")");
  }
  
  public void setBody(Expression paramExpression) {
    this.body = paramExpression;
  }
  
  public void setBody(Expression paramExpression1, Expression paramExpression2) {
    this.body = paramExpression1;
    this.exitBody = paramExpression2;
  }
  
  public void setLabel(Declaration paramDeclaration) {
    this.label = paramDeclaration;
  }
  
  protected <R, D> R visit(ExpVisitor<R, D> paramExpVisitor, D paramD) {
    return paramExpVisitor.visitBlockExp(this, paramD);
  }
  
  protected <R, D> void visitChildren(ExpVisitor<R, D> paramExpVisitor, D paramD) {
    this.body = paramExpVisitor.visitAndUpdate(this.body, paramD);
    if (paramExpVisitor.exitValue == null && this.exitBody != null)
      this.exitBody = paramExpVisitor.visitAndUpdate(this.exitBody, paramD); 
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/gnu/expr/BlockExp.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */