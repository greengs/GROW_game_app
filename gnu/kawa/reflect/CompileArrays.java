package gnu.kawa.reflect;

import gnu.bytecode.ArrayType;
import gnu.bytecode.Type;
import gnu.expr.ApplyExp;
import gnu.expr.Compilation;
import gnu.expr.Expression;
import gnu.expr.InlineCalls;
import gnu.expr.Inlineable;
import gnu.expr.Target;
import gnu.kawa.lispexpr.LangPrimType;
import gnu.mapping.Procedure;
import gnu.mapping.Values;

public class CompileArrays implements Inlineable {
  public char code;
  
  Procedure proc;
  
  public CompileArrays(Procedure paramProcedure, char paramChar) {
    this.proc = paramProcedure;
    this.code = paramChar;
  }
  
  public static void compileArrayGet(ArrayGet paramArrayGet, ApplyExp paramApplyExp, Compilation paramCompilation, Target paramTarget) {
    Type type = paramArrayGet.element_type;
    Expression[] arrayOfExpression = paramApplyExp.getArgs();
    arrayOfExpression[0].compile(paramCompilation, (Type)ArrayType.make(type));
    arrayOfExpression[1].compile(paramCompilation, (Type)Type.int_type);
    paramCompilation.getCode().emitArrayLoad(type);
    paramTarget.compileFromStack(paramCompilation, type);
  }
  
  public static void compileArrayLength(ArrayLength paramArrayLength, ApplyExp paramApplyExp, Compilation paramCompilation, Target paramTarget) {
    Type type = paramArrayLength.element_type;
    paramApplyExp.getArgs()[0].compile(paramCompilation, (Type)ArrayType.make(type));
    paramCompilation.getCode().emitArrayLength();
    paramTarget.compileFromStack(paramCompilation, (Type)LangPrimType.intType);
  }
  
  public static void compileArrayNew(ArrayNew paramArrayNew, ApplyExp paramApplyExp, Compilation paramCompilation, Target paramTarget) {
    Type type = paramArrayNew.element_type;
    paramApplyExp.getArgs()[0].compile(paramCompilation, (Type)Type.intType);
    paramCompilation.getCode().emitNewArray(type.getImplementationType());
    paramTarget.compileFromStack(paramCompilation, (Type)ArrayType.make(type));
  }
  
  public static void compileArraySet(ArraySet paramArraySet, ApplyExp paramApplyExp, Compilation paramCompilation, Target paramTarget) {
    Type type = paramArraySet.element_type;
    Expression[] arrayOfExpression = paramApplyExp.getArgs();
    arrayOfExpression[0].compile(paramCompilation, (Type)ArrayType.make(type));
    arrayOfExpression[1].compile(paramCompilation, (Type)Type.int_type);
    arrayOfExpression[2].compile(paramCompilation, type);
    paramCompilation.getCode().emitArrayStore(type);
    paramCompilation.compileConstant(Values.empty, paramTarget);
  }
  
  public static CompileArrays getForArrayGet(Object paramObject) {
    return new CompileArrays((Procedure)paramObject, 'G');
  }
  
  public static CompileArrays getForArrayLength(Object paramObject) {
    return new CompileArrays((Procedure)paramObject, 'L');
  }
  
  public static CompileArrays getForArrayNew(Object paramObject) {
    return new CompileArrays((Procedure)paramObject, 'N');
  }
  
  public static CompileArrays getForArraySet(Object paramObject) {
    return new CompileArrays((Procedure)paramObject, 'S');
  }
  
  public static Expression validateArrayGet(ApplyExp paramApplyExp, InlineCalls paramInlineCalls, Type paramType, Procedure paramProcedure) {
    paramApplyExp.visitArgs(paramInlineCalls);
    paramApplyExp.setType(((ArrayGet)paramProcedure).element_type);
    return (Expression)paramApplyExp;
  }
  
  public static Expression validateArrayLength(ApplyExp paramApplyExp, InlineCalls paramInlineCalls, Type paramType, Procedure paramProcedure) {
    paramApplyExp.visitArgs(paramInlineCalls);
    paramApplyExp.setType((Type)LangPrimType.intType);
    return (Expression)paramApplyExp;
  }
  
  public static Expression validateArrayNew(ApplyExp paramApplyExp, InlineCalls paramInlineCalls, Type paramType, Procedure paramProcedure) {
    paramApplyExp.visitArgs(paramInlineCalls);
    paramApplyExp.setType((Type)ArrayType.make(((ArrayNew)paramProcedure).element_type));
    return (Expression)paramApplyExp;
  }
  
  public static Expression validateArraySet(ApplyExp paramApplyExp, InlineCalls paramInlineCalls, Type paramType, Procedure paramProcedure) {
    paramApplyExp.visitArgs(paramInlineCalls);
    paramApplyExp.setType((Type)Type.void_type);
    return (Expression)paramApplyExp;
  }
  
  public void compile(ApplyExp paramApplyExp, Compilation paramCompilation, Target paramTarget) {
    switch (this.code) {
      default:
        compileArrayLength((ArrayLength)this.proc, paramApplyExp, paramCompilation, paramTarget);
        return;
      case 'N':
        compileArrayNew((ArrayNew)this.proc, paramApplyExp, paramCompilation, paramTarget);
        return;
      case 'G':
        compileArrayGet((ArrayGet)this.proc, paramApplyExp, paramCompilation, paramTarget);
        return;
      case 'S':
        break;
    } 
    compileArraySet((ArraySet)this.proc, paramApplyExp, paramCompilation, paramTarget);
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/gnu/kawa/reflect/CompileArrays.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */