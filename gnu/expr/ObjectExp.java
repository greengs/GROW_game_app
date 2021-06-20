package gnu.expr;

import gnu.bytecode.CodeAttr;
import gnu.bytecode.Method;
import gnu.bytecode.Type;
import gnu.bytecode.Variable;

public class ObjectExp extends ClassExp {
  public ObjectExp() {
    super(true);
  }
  
  public void compile(Compilation paramCompilation, Target paramTarget) {
    compileMembers(paramCompilation);
    CodeAttr codeAttr = paramCompilation.getCode();
    codeAttr.emitNew(this.type);
    codeAttr.emitDup(1);
    Method method = Compilation.getConstructor(this.type, this);
    if (this.closureEnvField != null) {
      Variable variable;
      LambdaExp lambdaExp = outerLambda();
      if (Compilation.defaultCallConvention < 2) {
        variable = (getOwningLambda()).heapFrame;
      } else if (((LambdaExp)variable).heapFrame != null) {
        variable = ((LambdaExp)variable).heapFrame;
      } else {
        variable = ((LambdaExp)variable).closureEnv;
      } 
      if (variable == null) {
        codeAttr.emitPushThis();
      } else {
        codeAttr.emitLoad(variable);
      } 
    } 
    codeAttr.emitInvokeSpecial(method);
    paramTarget.compileFromStack(paramCompilation, (Type)getCompiledClassType(paramCompilation));
  }
  
  public Type getType() {
    return (Type)this.type;
  }
  
  protected <R, D> R visit(ExpVisitor<R, D> paramExpVisitor, D paramD) {
    return paramExpVisitor.visitObjectExp(this, paramD);
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/gnu/expr/ObjectExp.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */