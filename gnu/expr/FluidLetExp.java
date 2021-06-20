package gnu.expr;

import gnu.bytecode.CodeAttr;
import gnu.bytecode.Scope;
import gnu.bytecode.Type;
import gnu.bytecode.Variable;
import gnu.mapping.OutPort;

public class FluidLetExp extends LetExp {
  public FluidLetExp(Expression[] paramArrayOfExpression) {
    super(paramArrayOfExpression);
  }
  
  private void doInits(Declaration paramDeclaration, int paramInt, Variable[] paramArrayOfVariable, Compilation paramCompilation, Variable paramVariable) {
    if (paramInt >= this.inits.length)
      return; 
    CodeAttr codeAttr = paramCompilation.getCode();
    paramArrayOfVariable[paramInt] = codeAttr.addLocal((Type)Type.pointer_type);
    paramDeclaration.allocateVariable(codeAttr);
    paramDeclaration.base.load(null, 2, paramCompilation, Target.pushObject);
    codeAttr.emitDup();
    codeAttr.emitStore(paramDeclaration.getVariable());
    this.inits[paramInt].compile(paramCompilation, Target.pushObject);
    doInits(paramDeclaration.nextDecl(), paramInt + 1, paramArrayOfVariable, paramCompilation, paramVariable);
    codeAttr.emitInvokeVirtual(Compilation.typeLocation.getDeclaredMethod("setWithSave", 1));
    codeAttr.emitStore(paramArrayOfVariable[paramInt]);
  }
  
  public void compile(Compilation paramCompilation, Target paramTarget) {
    Target target;
    Type type;
    CodeAttr codeAttr = paramCompilation.getCode();
    if (paramTarget instanceof IgnoreTarget) {
      type = null;
    } else {
      type = getType();
    } 
    if (type == null) {
      target = Target.Ignore;
    } else if (type == Type.pointer_type) {
      target = Target.pushObject;
    } else {
      target = new StackTarget(type);
    } 
    Scope scope = getVarScope();
    codeAttr.enterScope(scope);
    Variable variable = scope.addVariable(codeAttr, (Type)Compilation.typeCallContext, null);
    paramCompilation.loadCallContext();
    codeAttr.emitStore(variable);
    Variable[] arrayOfVariable = new Variable[this.inits.length];
    Declaration declaration2 = firstDecl();
    doInits(declaration2, 0, arrayOfVariable, paramCompilation, variable);
    codeAttr.emitTryStart(true, type);
    this.body.compileWithPosition(paramCompilation, target);
    codeAttr.emitFinallyStart();
    int i = 0;
    for (Declaration declaration1 = declaration2; i < this.inits.length; declaration1 = declaration1.nextDecl()) {
      declaration1.load(null, 2, paramCompilation, Target.pushObject);
      codeAttr.emitLoad(arrayOfVariable[i]);
      codeAttr.emitInvokeVirtual(Compilation.typeLocation.getDeclaredMethod("setRestore", 1));
      i++;
    } 
    codeAttr.emitTryCatchEnd();
    popScope(codeAttr);
    if (type != null)
      paramTarget.compileFromStack(paramCompilation, type); 
  }
  
  protected boolean mustCompile() {
    return true;
  }
  
  public void print(OutPort paramOutPort) {
    print(paramOutPort, "(FluidLet", ")");
  }
  
  protected <R, D> R visit(ExpVisitor<R, D> paramExpVisitor, D paramD) {
    return paramExpVisitor.visitFluidLetExp(this, paramD);
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/gnu/expr/FluidLetExp.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */