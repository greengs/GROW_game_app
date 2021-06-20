package gnu.expr;

import gnu.bytecode.Type;
import gnu.kawa.util.IdentityHashTable;
import gnu.mapping.CallContext;
import gnu.mapping.OutPort;

public class ExitExp extends Expression {
  BlockExp block;
  
  Expression result = QuoteExp.voidExp;
  
  public ExitExp(BlockExp paramBlockExp) {
    this.block = paramBlockExp;
  }
  
  public ExitExp(Expression paramExpression, BlockExp paramBlockExp) {
    this.block = paramBlockExp;
  }
  
  public void apply(CallContext paramCallContext) throws Throwable {
    throw new BlockExitException(this, this.result.eval(paramCallContext));
  }
  
  public void compile(Compilation paramCompilation, Target paramTarget) {
    Expression expression;
    paramCompilation.getCode();
    if (this.result == null) {
      expression = QuoteExp.voidExp;
    } else {
      expression = this.result;
    } 
    expression.compileWithPosition(paramCompilation, this.block.exitTarget);
    this.block.exitableBlock.exit();
  }
  
  protected Expression deepCopy(IdentityHashTable paramIdentityHashTable) {
    Expression expression = deepCopy(this.result, paramIdentityHashTable);
    if (expression == null && this.result != null)
      return null; 
    Object object = paramIdentityHashTable.get(this.block);
    if (object == null) {
      object = this.block;
      object = new ExitExp(expression, (BlockExp)object);
      ((ExitExp)object).flags = getFlags();
      return (Expression)object;
    } 
    object = object;
    object = new ExitExp(expression, (BlockExp)object);
    ((ExitExp)object).flags = getFlags();
    return (Expression)object;
  }
  
  public Type getType() {
    return (Type)Type.neverReturnsType;
  }
  
  protected boolean mustCompile() {
    return false;
  }
  
  public void print(OutPort paramOutPort) {
    paramOutPort.startLogicalBlock("(Exit", false, ")");
    paramOutPort.writeSpaceFill();
    if (this.block == null || this.block.label == null) {
      paramOutPort.print("<unknown>");
    } else {
      paramOutPort.print(this.block.label.getName());
    } 
    if (this.result != null) {
      paramOutPort.writeSpaceLinear();
      this.result.print(paramOutPort);
    } 
    paramOutPort.endLogicalBlock(")");
  }
  
  protected <R, D> R visit(ExpVisitor<R, D> paramExpVisitor, D paramD) {
    return paramExpVisitor.visitExitExp(this, paramD);
  }
  
  protected <R, D> void visitChildren(ExpVisitor<R, D> paramExpVisitor, D paramD) {
    this.result = paramExpVisitor.visitAndUpdate(this.result, paramD);
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/gnu/expr/ExitExp.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */