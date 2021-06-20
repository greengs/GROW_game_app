package gnu.kawa.functions;

import gnu.bytecode.ArrayType;
import gnu.bytecode.ClassType;
import gnu.bytecode.Field;
import gnu.bytecode.Type;
import gnu.expr.ApplyExp;
import gnu.expr.Compilation;
import gnu.expr.Declaration;
import gnu.expr.Expression;
import gnu.expr.InlineCalls;
import gnu.expr.Language;
import gnu.expr.QuoteExp;
import gnu.expr.ReferenceExp;
import gnu.kawa.reflect.ArrayGet;
import gnu.kawa.reflect.CompileReflect;
import gnu.kawa.reflect.Invoke;
import gnu.mapping.Named;
import gnu.mapping.Procedure;

public class CompilationHelpers {
  public static final Declaration setterDecl;
  
  static final Field setterField;
  
  static final ClassType setterType;
  
  static final ClassType typeList = ClassType.make("java.util.List");
  
  static {
    setterType = ClassType.make("gnu.kawa.functions.Setter");
    setterField = setterType.getDeclaredField("setter");
    setterDecl = new Declaration("setter", setterField);
    setterDecl.noteValue((Expression)new QuoteExp(Setter.setter));
  }
  
  private static boolean nonNumeric(Expression paramExpression) {
    boolean bool2 = false;
    boolean bool1 = bool2;
    if (paramExpression instanceof QuoteExp) {
      Object object = ((QuoteExp)paramExpression).getValue();
      bool1 = bool2;
      if (!(object instanceof gnu.math.Numeric)) {
        bool1 = bool2;
        if (!(object instanceof gnu.text.Char)) {
          bool1 = bool2;
          if (!(object instanceof gnu.mapping.Symbol))
            bool1 = true; 
        } 
      } 
    } 
    return bool1;
  }
  
  public static Expression validateApplyToArgs(ApplyExp paramApplyExp, InlineCalls paramInlineCalls, Type paramType, Procedure paramProcedure) {
    Expression[] arrayOfExpression = paramApplyExp.getArgs();
    int i = arrayOfExpression.length - 1;
    if (i >= 0) {
      ApplyExp applyExp;
      Expression expression2 = arrayOfExpression[0];
      Expression expression1 = expression2;
      if (!expression2.getFlag(1)) {
        if (expression2 instanceof gnu.expr.LambdaExp) {
          Expression[] arrayOfExpression1 = new Expression[i];
          System.arraycopy(arrayOfExpression, 1, arrayOfExpression1, 0, i);
          return paramInlineCalls.visit((new ApplyExp(expression2, arrayOfExpression1)).setLine((Expression)paramApplyExp), paramType);
        } 
        expression1 = paramInlineCalls.visit(expression2, null);
        arrayOfExpression[0] = expression1;
      } 
      Type type = expression1.getType().getRealType();
      Compilation compilation = paramInlineCalls.getCompilation();
      Language language = compilation.getLanguage();
      if (type.isSubtype((Type)Compilation.typeProcedure)) {
        Expression[] arrayOfExpression1 = new Expression[i];
        System.arraycopy(arrayOfExpression, 1, arrayOfExpression1, 0, i);
        ApplyExp applyExp1 = new ApplyExp(expression1, arrayOfExpression1);
        applyExp1.setLine((Expression)paramApplyExp);
        return expression1.validateApply(applyExp1, paramInlineCalls, paramType, null);
      } 
      expression2 = null;
      if (CompileReflect.checkKnownClass(type, compilation) < 0) {
        expression1 = expression2;
      } else if (type.isSubtype((Type)Compilation.typeType) || language.getTypeFor(expression1, false) != null) {
        applyExp = new ApplyExp((Procedure)Invoke.make, arrayOfExpression);
      } else if (type instanceof ArrayType) {
        applyExp = new ApplyExp((Procedure)new ArrayGet(((ArrayType)type).getComponentType()), arrayOfExpression);
      } else {
        expression1 = expression2;
        if (type instanceof ClassType) {
          ClassType classType = (ClassType)type;
          expression1 = expression2;
          if (classType.isSubclass(typeList)) {
            expression1 = expression2;
            if (i == 1)
              applyExp = new ApplyExp(classType.getMethod("get", new Type[] { (Type)Type.intType }), arrayOfExpression); 
          } 
        } 
      } 
      if (applyExp != null) {
        applyExp.setLine((Expression)paramApplyExp);
        return paramInlineCalls.visitApplyOnly(applyExp, paramType);
      } 
    } 
    paramApplyExp.visitArgs(paramInlineCalls);
    return (Expression)paramApplyExp;
  }
  
  public static Expression validateIsEqv(ApplyExp paramApplyExp, InlineCalls paramInlineCalls, Type paramType, Procedure paramProcedure) {
    paramApplyExp.visitArgs(paramInlineCalls);
    Expression[] arrayOfExpression = paramApplyExp.getArgs();
    if (nonNumeric(arrayOfExpression[0]) || nonNumeric(arrayOfExpression[1]))
      paramApplyExp = new ApplyExp((Procedure)((IsEqv)paramProcedure).isEq, arrayOfExpression); 
    return (Expression)paramApplyExp;
  }
  
  public static Expression validateSetter(ApplyExp paramApplyExp, InlineCalls paramInlineCalls, Type paramType, Procedure paramProcedure) {
    Expression expression;
    Type type;
    paramApplyExp.visitArgs(paramInlineCalls);
    Expression[] arrayOfExpression = paramApplyExp.getArgs();
    ApplyExp applyExp1 = paramApplyExp;
    if (arrayOfExpression.length == 1) {
      Expression expression1 = arrayOfExpression[0];
      type = expression1.getType();
      if (type instanceof ArrayType)
        return (Expression)new SetArrayExp(expression1, (ArrayType)type); 
    } else {
      return (Expression)applyExp1;
    } 
    if (type instanceof ClassType && ((ClassType)type).isSubclass(typeList)) {
      applyExp1 = paramApplyExp;
      return (Expression)(!(paramApplyExp instanceof SetListExp) ? new SetListExp(paramApplyExp.getFunction(), arrayOfExpression) : applyExp1);
    } 
    ApplyExp applyExp2 = applyExp1;
    if (applyExp1 instanceof ReferenceExp) {
      Declaration declaration = ((ReferenceExp)applyExp1).getBinding();
      applyExp2 = applyExp1;
      if (declaration != null)
        expression = declaration.getValue(); 
    } 
    applyExp1 = paramApplyExp;
    if (expression instanceof QuoteExp) {
      Object object = ((QuoteExp)expression).getValue();
      applyExp1 = paramApplyExp;
      if (object instanceof Procedure) {
        object = ((Procedure)object).getSetter();
        applyExp1 = paramApplyExp;
        if (object instanceof Procedure) {
          if (object instanceof java.io.Externalizable)
            return (Expression)new QuoteExp(object); 
          object = Declaration.getDeclaration((Named)object);
          applyExp1 = paramApplyExp;
          if (object != null)
            return (Expression)new ReferenceExp((Declaration)object); 
        } 
      } 
    } 
    return (Expression)applyExp1;
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/gnu/kawa/functions/CompilationHelpers.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */