package gnu.kawa.functions;

import gnu.bytecode.CodeAttr;
import gnu.bytecode.Type;
import gnu.expr.ApplyExp;
import gnu.expr.Compilation;
import gnu.expr.Expression;
import gnu.expr.Inlineable;
import gnu.expr.QuoteExp;
import gnu.expr.Target;
import gnu.kawa.lispexpr.LangObjType;
import gnu.lists.LList;
import gnu.lists.Pair;
import gnu.mapping.ProcedureN;

public class MakeList extends ProcedureN implements Inlineable {
  public static final MakeList list = new MakeList();
  
  static {
    list.setName("list");
  }
  
  public static void compile(Expression[] paramArrayOfExpression, int paramInt, Compilation paramCompilation) {
    int k;
    int j = paramArrayOfExpression.length - paramInt;
    CodeAttr codeAttr = paramCompilation.getCode();
    if (j == 0) {
      (new QuoteExp(LList.Empty)).compile(paramCompilation, Target.pushObject);
      return;
    } 
    if (j <= 4) {
      for (int m = 0; m < j; m++)
        paramArrayOfExpression[paramInt + m].compile(paramCompilation, Target.pushObject); 
      codeAttr.emitInvokeStatic(Compilation.scmListType.getDeclaredMethod("list" + j, null));
      return;
    } 
    paramArrayOfExpression[paramInt].compile(paramCompilation, Target.pushObject);
    codeAttr.emitInvokeStatic(Compilation.scmListType.getDeclaredMethod("list1", null));
    codeAttr.emitDup(1);
    paramInt++;
    int i = j - 1;
    while (true) {
      j = i;
      k = paramInt;
      if (i >= 4) {
        paramArrayOfExpression[paramInt].compile(paramCompilation, Target.pushObject);
        paramArrayOfExpression[paramInt + 1].compile(paramCompilation, Target.pushObject);
        paramArrayOfExpression[paramInt + 2].compile(paramCompilation, Target.pushObject);
        paramArrayOfExpression[paramInt + 3].compile(paramCompilation, Target.pushObject);
        i -= 4;
        paramInt += 4;
        codeAttr.emitInvokeStatic(Compilation.scmListType.getDeclaredMethod("chain4", null));
        continue;
      } 
      break;
    } 
    while (j > 0) {
      paramArrayOfExpression[k].compile(paramCompilation, Target.pushObject);
      j--;
      k++;
      codeAttr.emitInvokeStatic(Compilation.scmListType.getDeclaredMethod("chain1", null));
    } 
    codeAttr.emitPop(1);
  }
  
  public static Object list$V(Object[] paramArrayOfObject) {
    LList lList = LList.Empty;
    int i = paramArrayOfObject.length;
    while (true) {
      Pair pair;
      if (--i >= 0) {
        pair = new Pair(paramArrayOfObject[i], lList);
        continue;
      } 
      return pair;
    } 
  }
  
  public Object applyN(Object[] paramArrayOfObject) {
    return list$V(paramArrayOfObject);
  }
  
  public void compile(ApplyExp paramApplyExp, Compilation paramCompilation, Target paramTarget) {
    compile(paramApplyExp.getArgs(), 0, paramCompilation);
    paramTarget.compileFromStack(paramCompilation, paramApplyExp.getType());
  }
  
  public Type getReturnType(Expression[] paramArrayOfExpression) {
    return (Type)((paramArrayOfExpression.length > 0) ? Compilation.typePair : LangObjType.listType);
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/gnu/kawa/functions/MakeList.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */