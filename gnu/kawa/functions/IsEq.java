package gnu.kawa.functions;

import gnu.bytecode.CodeAttr;
import gnu.bytecode.Type;
import gnu.expr.ApplyExp;
import gnu.expr.Compilation;
import gnu.expr.ConditionalTarget;
import gnu.expr.Expression;
import gnu.expr.Inlineable;
import gnu.expr.Language;
import gnu.expr.Target;
import gnu.mapping.Procedure2;

public class IsEq extends Procedure2 implements Inlineable {
  Language language;
  
  public IsEq(Language paramLanguage, String paramString) {
    this.language = paramLanguage;
    setName(paramString);
  }
  
  public static void compile(Expression[] paramArrayOfExpression, Compilation paramCompilation, Target paramTarget, Language paramLanguage) {
    Object object1;
    Object object2;
    CodeAttr codeAttr = paramCompilation.getCode();
    paramArrayOfExpression[0].compile(paramCompilation, Target.pushObject);
    paramArrayOfExpression[1].compile(paramCompilation, Target.pushObject);
    if (paramTarget instanceof ConditionalTarget) {
      ConditionalTarget conditionalTarget = (ConditionalTarget)paramTarget;
      if (conditionalTarget.trueBranchComesFirst) {
        codeAttr.emitGotoIfNE(conditionalTarget.ifFalse);
      } else {
        codeAttr.emitGotoIfEq(conditionalTarget.ifTrue);
      } 
      conditionalTarget.emitGotoFirstBranch(codeAttr);
      return;
    } 
    codeAttr.emitIfEq();
    if (paramTarget.getType() instanceof gnu.bytecode.ClassType) {
      object1 = paramLanguage.booleanObject(true);
      object2 = paramLanguage.booleanObject(false);
      paramCompilation.compileConstant(object1, Target.pushObject);
      codeAttr.emitElse();
      paramCompilation.compileConstant(object2, Target.pushObject);
      if (object1 instanceof Boolean && object2 instanceof Boolean) {
        object1 = Compilation.scmBooleanType;
      } else {
        object1 = Type.pointer_type;
      } 
    } else {
      codeAttr.emitPushInt(1);
      codeAttr.emitElse();
      codeAttr.emitPushInt(0);
      object1 = object2.getTypeFor(boolean.class);
    } 
    codeAttr.emitFi();
    paramTarget.compileFromStack(paramCompilation, (Type)object1);
  }
  
  public boolean apply(Object paramObject1, Object paramObject2) {
    return (paramObject1 == paramObject2);
  }
  
  public Object apply2(Object paramObject1, Object paramObject2) {
    Language language = this.language;
    if (paramObject1 == paramObject2) {
      boolean bool1 = true;
      return language.booleanObject(bool1);
    } 
    boolean bool = false;
    return language.booleanObject(bool);
  }
  
  public void compile(ApplyExp paramApplyExp, Compilation paramCompilation, Target paramTarget) {
    compile(paramApplyExp.getArgs(), paramCompilation, paramTarget, this.language);
  }
  
  public Type getReturnType(Expression[] paramArrayOfExpression) {
    return this.language.getTypeFor(boolean.class);
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/gnu/kawa/functions/IsEq.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */