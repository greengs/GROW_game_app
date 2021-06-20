package gnu.kawa.functions;

import gnu.bytecode.CodeAttr;
import gnu.bytecode.Label;
import gnu.bytecode.Method;
import gnu.bytecode.Scope;
import gnu.bytecode.Type;
import gnu.bytecode.Variable;
import gnu.expr.ApplyExp;
import gnu.expr.Compilation;
import gnu.expr.ConsumerTarget;
import gnu.expr.Declaration;
import gnu.expr.Expression;
import gnu.expr.IfExp;
import gnu.expr.Inlineable;
import gnu.expr.LambdaExp;
import gnu.expr.QuoteExp;
import gnu.expr.ReferenceExp;
import gnu.expr.StackTarget;
import gnu.expr.Target;
import gnu.lists.Consumer;
import gnu.mapping.CallContext;
import gnu.mapping.MethodProc;
import gnu.mapping.Procedure;
import gnu.math.IntNum;

public class ValuesMap extends MethodProc implements Inlineable {
  public static final ValuesMap valuesMap = new ValuesMap(-1);
  
  public static final ValuesMap valuesMapWithPos = new ValuesMap(1);
  
  private final int startCounter;
  
  private ValuesMap(int paramInt) {
    this.startCounter = paramInt;
    setProperty(Procedure.validateApplyKey, "gnu.kawa.functions.CompileMisc:validateApplyValuesMap");
  }
  
  static LambdaExp canInline(ApplyExp paramApplyExp, ValuesMap paramValuesMap) {
    byte b = 2;
    Expression[] arrayOfExpression = paramApplyExp.getArgs();
    if (arrayOfExpression.length == 2) {
      Expression expression = arrayOfExpression[0];
      if (expression instanceof LambdaExp) {
        LambdaExp lambdaExp = (LambdaExp)expression;
        if (lambdaExp.min_args == lambdaExp.max_args) {
          int i = lambdaExp.min_args;
          if (paramValuesMap.startCounter < 0)
            b = 1; 
          if (i == b)
            return lambdaExp; 
        } 
      } 
    } 
    return null;
  }
  
  public static void compileInlined(LambdaExp paramLambdaExp, Expression paramExpression, int paramInt, Method paramMethod, Compilation paramCompilation, Target paramTarget) {
    IfExp ifExp;
    Declaration declaration2;
    Expression[] arrayOfExpression;
    Declaration declaration1 = paramLambdaExp.firstDecl();
    CodeAttr codeAttr = paramCompilation.getCode();
    Scope scope = codeAttr.pushScope();
    Type type = declaration1.getType();
    if (paramInt >= 0) {
      Variable variable = scope.addVariable(codeAttr, (Type)Type.intType, "position");
      codeAttr.emitPushInt(paramInt);
      codeAttr.emitStore(variable);
      declaration2 = new Declaration(variable);
    } else {
      scope = null;
      declaration2 = null;
    } 
    if (declaration1.isSimple() && paramMethod == null) {
      declaration1.allocateVariable(codeAttr);
    } else {
      String str = Compilation.mangleNameIfNeeded(declaration1.getName());
      declaration1 = new Declaration(codeAttr.addLocal(type.getImplementationType(), str));
    } 
    if (paramInt >= 0) {
      arrayOfExpression = new Expression[2];
      arrayOfExpression[0] = (Expression)new ReferenceExp(declaration1);
      arrayOfExpression[1] = (Expression)new ReferenceExp(declaration2);
    } else {
      arrayOfExpression = new Expression[1];
      arrayOfExpression[0] = (Expression)new ReferenceExp(declaration1);
    } 
    ApplyExp applyExp2 = new ApplyExp((Expression)paramLambdaExp, arrayOfExpression);
    ApplyExp applyExp1 = applyExp2;
    if (paramMethod != null) {
      applyExp1 = applyExp2;
      if (applyExp2.getType().getImplementationType() != Type.booleanType)
        applyExp1 = new ApplyExp(paramMethod, new Expression[] { (Expression)applyExp2, (Expression)new ReferenceExp(declaration2) }); 
      ifExp = new IfExp((Expression)applyExp1, (Expression)new ReferenceExp(declaration1), (Expression)QuoteExp.voidExp);
    } 
    Variable variable1 = codeAttr.addLocal((Type)Type.intType);
    Variable variable2 = codeAttr.addLocal((Type)Type.pointer_type);
    Variable variable3 = codeAttr.addLocal((Type)Type.intType);
    paramExpression.compileWithPosition(paramCompilation, Target.pushObject);
    codeAttr.emitStore(variable2);
    codeAttr.emitPushInt(0);
    codeAttr.emitStore(variable1);
    Label label1 = new Label(codeAttr);
    Label label2 = new Label(codeAttr);
    label1.define(codeAttr);
    codeAttr.emitLoad(variable2);
    codeAttr.emitLoad(variable1);
    codeAttr.emitInvokeStatic(Compilation.typeValues.getDeclaredMethod("nextIndex", 2));
    codeAttr.emitDup((Type)Type.intType);
    codeAttr.emitStore(variable3);
    codeAttr.emitGotoIfIntLtZero(label2);
    codeAttr.emitLoad(variable2);
    codeAttr.emitLoad(variable1);
    codeAttr.emitInvokeStatic(Compilation.typeValues.getDeclaredMethod("nextValue", 2));
    StackTarget.convert(paramCompilation, (Type)Type.objectType, type);
    declaration1.compileStore(paramCompilation);
    ifExp.compile(paramCompilation, paramTarget);
    if (paramInt >= 0)
      codeAttr.emitInc((Variable)scope, (short)1); 
    codeAttr.emitLoad(variable3);
    codeAttr.emitStore(variable1);
    codeAttr.emitGoto(label1);
    label2.define(codeAttr);
    codeAttr.popScope();
  }
  
  public void apply(CallContext paramCallContext) throws Throwable {
    Procedure procedure = (Procedure)paramCallContext.getNextArg();
    Consumer consumer = paramCallContext.consumer;
    Object object = paramCallContext.getNextArg();
    Procedure.checkArgCount(procedure, 1);
    if (object instanceof gnu.mapping.Values) {
      int j = 0;
      int i = this.startCounter;
      object = object;
      while (true) {
        j = object.nextPos(j);
        if (j != 0) {
          Object object1 = object.getPosPrevious(j);
          if (this.startCounter >= 0) {
            procedure.check2(object1, IntNum.make(i), paramCallContext);
            i++;
          } else {
            procedure.check1(object1, paramCallContext);
          } 
          paramCallContext.runUntilDone();
          continue;
        } 
        break;
      } 
    } else {
      if (this.startCounter >= 0) {
        procedure.check2(object, IntNum.make(this.startCounter), paramCallContext);
      } else {
        procedure.check1(object, paramCallContext);
      } 
      paramCallContext.runUntilDone();
    } 
  }
  
  public void compile(ApplyExp paramApplyExp, Compilation paramCompilation, Target paramTarget) {
    LambdaExp lambdaExp = canInline(paramApplyExp, this);
    if (lambdaExp == null) {
      ApplyExp.compile(paramApplyExp, paramCompilation, paramTarget);
      return;
    } 
    Expression[] arrayOfExpression = paramApplyExp.getArgs();
    if (!(paramTarget instanceof gnu.expr.IgnoreTarget) && !(paramTarget instanceof ConsumerTarget)) {
      ConsumerTarget.compileUsingConsumer((Expression)paramApplyExp, paramCompilation, paramTarget);
      return;
    } 
    compileInlined(lambdaExp, arrayOfExpression[1], this.startCounter, (Method)null, paramCompilation, paramTarget);
  }
  
  public Type getReturnType(Expression[] paramArrayOfExpression) {
    return (Type)Type.pointer_type;
  }
  
  public int numArgs() {
    return 8194;
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/gnu/kawa/functions/ValuesMap.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */