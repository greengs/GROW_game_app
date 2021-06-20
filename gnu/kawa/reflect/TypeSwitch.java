package gnu.kawa.reflect;

import gnu.bytecode.CodeAttr;
import gnu.bytecode.Type;
import gnu.bytecode.Variable;
import gnu.expr.ApplyExp;
import gnu.expr.Compilation;
import gnu.expr.Declaration;
import gnu.expr.Expression;
import gnu.expr.Inlineable;
import gnu.expr.LambdaExp;
import gnu.expr.Target;
import gnu.expr.TypeValue;
import gnu.mapping.CallContext;
import gnu.mapping.MethodProc;
import gnu.mapping.Procedure;

public class TypeSwitch extends MethodProc implements Inlineable {
  public static final TypeSwitch typeSwitch = new TypeSwitch("typeswitch");
  
  public TypeSwitch(String paramString) {
    setName(paramString);
    setProperty(Procedure.validateApplyKey, "gnu.kawa.reflect.CompileReflect:validateApplyTypeSwitch");
  }
  
  public void apply(CallContext paramCallContext) throws Throwable {
    Object[] arrayOfObject = paramCallContext.getArgs();
    Object object = arrayOfObject[0];
    int j = arrayOfObject.length - 1;
    for (int i = 1; i < j; i++) {
      if (((MethodProc)arrayOfObject[i]).match1(object, paramCallContext) >= 0)
        return; 
    } 
    ((Procedure)arrayOfObject[j]).check1(object, paramCallContext);
  }
  
  public void compile(ApplyExp paramApplyExp, Compilation paramCompilation, Target paramTarget) {
    Expression[] arrayOfExpression = paramApplyExp.getArgs();
    CodeAttr codeAttr = paramCompilation.getCode();
    codeAttr.pushScope();
    Variable variable = codeAttr.addLocal((Type)Type.pointer_type);
    arrayOfExpression[0].compile(paramCompilation, Target.pushObject);
    codeAttr.emitStore(variable);
    int i = 1;
    while (i < arrayOfExpression.length) {
      if (i > 1)
        codeAttr.emitElse(); 
      int j = i + 1;
      Expression expression = arrayOfExpression[i];
      if (expression instanceof LambdaExp) {
        LambdaExp lambdaExp = (LambdaExp)expression;
        Declaration declaration = lambdaExp.firstDecl();
        Type type = declaration.getType();
        if (!declaration.getCanRead()) {
          declaration = null;
        } else {
          declaration.allocateVariable(codeAttr);
        } 
        if (type instanceof TypeValue) {
          ((TypeValue)type).emitTestIf(variable, declaration, paramCompilation);
        } else {
          if (j < arrayOfExpression.length) {
            codeAttr.emitLoad(variable);
            type.emitIsInstance(codeAttr);
            codeAttr.emitIfIntNotZero();
          } 
          if (declaration != null) {
            codeAttr.emitLoad(variable);
            declaration.compileStore(paramCompilation);
          } 
        } 
        lambdaExp.allocChildClasses(paramCompilation);
        lambdaExp.body.compileWithPosition(paramCompilation, paramTarget);
        i = j;
        continue;
      } 
      throw new Error("not implemented: typeswitch arg not LambdaExp");
    } 
    i = arrayOfExpression.length - 2;
    while (true) {
      if (--i >= 0) {
        codeAttr.emitFi();
        continue;
      } 
      codeAttr.popScope();
      return;
    } 
  }
  
  public Type getReturnType(Expression[] paramArrayOfExpression) {
    return (Type)Type.pointer_type;
  }
  
  public int numArgs() {
    return -4094;
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/gnu/kawa/reflect/TypeSwitch.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */