package gnu.expr;

import gnu.mapping.OutPort;
import gnu.text.SourceMessages;

public class ErrorExp extends Expression {
  String message;
  
  public ErrorExp(String paramString) {
    this.message = paramString;
  }
  
  public ErrorExp(String paramString, Compilation paramCompilation) {
    paramCompilation.getMessages().error('e', paramString);
    this.message = paramString;
  }
  
  public ErrorExp(String paramString, SourceMessages paramSourceMessages) {
    paramSourceMessages.error('e', paramString);
    this.message = paramString;
  }
  
  public void compile(Compilation paramCompilation, Target paramTarget) {
    throw new Error(paramCompilation.getFileName() + ":" + paramCompilation.getLineNumber() + ": internal error: compiling error expression: " + this.message);
  }
  
  protected boolean mustCompile() {
    return false;
  }
  
  public void print(OutPort paramOutPort) {
    paramOutPort.startLogicalBlock("(Error", false, ")");
    paramOutPort.writeSpaceLinear();
    paramOutPort.print(this.message);
    paramOutPort.endLogicalBlock(")");
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/gnu/expr/ErrorExp.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */