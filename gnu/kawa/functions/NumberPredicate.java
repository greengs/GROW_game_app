package gnu.kawa.functions;

import gnu.bytecode.CodeAttr;
import gnu.bytecode.Type;
import gnu.expr.ApplyExp;
import gnu.expr.Compilation;
import gnu.expr.Expression;
import gnu.expr.Inlineable;
import gnu.expr.Language;
import gnu.expr.StackTarget;
import gnu.expr.Target;
import gnu.kawa.lispexpr.LangObjType;
import gnu.mapping.Procedure;
import gnu.mapping.Procedure1;

public class NumberPredicate extends Procedure1 implements Inlineable {
  public static final int EVEN = 2;
  
  public static final int ODD = 1;
  
  Language language;
  
  final int op;
  
  public NumberPredicate(Language paramLanguage, String paramString, int paramInt) {
    super(paramString);
    this.language = paramLanguage;
    this.op = paramInt;
    setProperty(Procedure.validateApplyKey, "gnu.kawa.functions.CompileArith:validateApplyNumberPredicate");
  }
  
  public Object apply1(Object paramObject) {
    paramObject = LangObjType.coerceIntNum(paramObject);
    switch (this.op) {
      default:
        throw new Error();
      case 1:
        bool = paramObject.isOdd();
        return getLanguage().booleanObject(bool);
      case 2:
        break;
    } 
    if (!paramObject.isOdd()) {
      bool = true;
      return getLanguage().booleanObject(bool);
    } 
    boolean bool = false;
    return getLanguage().booleanObject(bool);
  }
  
  public void compile(ApplyExp paramApplyExp, Compilation paramCompilation, Target paramTarget) {
    Target target;
    Expression[] arrayOfExpression = paramApplyExp.getArgs();
    if (arrayOfExpression.length == 1 && (this.op == 1 || this.op == 2)) {
      Expression expression = arrayOfExpression[0];
      if (Arithmetic.classifyType(expression.getType()) <= 4) {
        target = StackTarget.getInstance((Type)Type.intType);
        CodeAttr codeAttr = paramCompilation.getCode();
        if (this.op == 2)
          codeAttr.emitPushInt(1); 
        expression.compile(paramCompilation, target);
        codeAttr.emitPushInt(1);
        codeAttr.emitAnd();
        if (this.op == 2)
          codeAttr.emitSub(Type.intType); 
        paramTarget.compileFromStack(paramCompilation, (Type)Type.booleanType);
        return;
      } 
    } 
    ApplyExp.compile((ApplyExp)target, paramCompilation, paramTarget);
  }
  
  protected final Language getLanguage() {
    return this.language;
  }
  
  public int numArgs() {
    return 4097;
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/gnu/kawa/functions/NumberPredicate.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */