package gnu.expr;

import gnu.bytecode.CodeAttr;
import gnu.bytecode.Type;

public class ClassInitializer extends Initializer {
  ClassExp cexp;
  
  public ClassInitializer(ClassExp paramClassExp, Compilation paramCompilation) {
    this.field = paramClassExp.allocFieldFor(paramCompilation);
    paramClassExp.compileMembers(paramCompilation);
    this.cexp = paramClassExp;
    if (this.field.getStaticFlag()) {
      this.next = paramCompilation.clinitChain;
      paramCompilation.clinitChain = this;
      return;
    } 
    LambdaExp lambdaExp = paramClassExp.getOwningLambda();
    this.next = lambdaExp.initChain;
    lambdaExp.initChain = this;
  }
  
  public void emit(Compilation paramCompilation) {
    CodeAttr codeAttr = paramCompilation.getCode();
    if (!this.field.getStaticFlag())
      codeAttr.emitPushThis(); 
    this.cexp.compilePushClass(paramCompilation, Target.pushValue((Type)Compilation.typeClassType));
    if (this.field.getStaticFlag()) {
      codeAttr.emitPutStatic(this.field);
      return;
    } 
    codeAttr.emitPutField(this.field);
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/gnu/expr/ClassInitializer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */