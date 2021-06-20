package gnu.kawa.functions;

import gnu.bytecode.ClassType;
import gnu.bytecode.CodeAttr;
import gnu.bytecode.Method;
import gnu.bytecode.PrimType;
import gnu.bytecode.Type;
import gnu.bytecode.Variable;
import gnu.expr.ApplyExp;
import gnu.expr.BeginExp;
import gnu.expr.Compilation;
import gnu.expr.ConditionalTarget;
import gnu.expr.Declaration;
import gnu.expr.ExpVisitor;
import gnu.expr.Expression;
import gnu.expr.IfExp;
import gnu.expr.InlineCalls;
import gnu.expr.Inlineable;
import gnu.expr.Keyword;
import gnu.expr.LambdaExp;
import gnu.expr.Language;
import gnu.expr.LetExp;
import gnu.expr.QuoteExp;
import gnu.expr.ReferenceExp;
import gnu.expr.Target;
import gnu.expr.TryExp;
import gnu.kawa.lispexpr.LangObjType;
import gnu.kawa.reflect.CompileReflect;
import gnu.kawa.reflect.Invoke;
import gnu.kawa.reflect.SlotGet;
import gnu.lists.LList;
import gnu.mapping.Namespace;
import gnu.mapping.Procedure;
import gnu.mapping.WrongArguments;
import kawa.standard.Scheme;

public class CompileMisc implements Inlineable {
  static final int CONVERT = 2;
  
  static final int NOT = 3;
  
  static Method coerceMethod;
  
  public static final ClassType typeContinuation = ClassType.make("kawa.lang.Continuation");
  
  static ClassType typeType;
  
  int code;
  
  Procedure proc;
  
  public CompileMisc(Procedure paramProcedure, int paramInt) {
    this.proc = paramProcedure;
    this.code = paramInt;
  }
  
  private static LambdaExp canInlineCallCC(ApplyExp paramApplyExp) {
    Expression[] arrayOfExpression = paramApplyExp.getArgs();
    if (arrayOfExpression.length == 1) {
      Expression expression = arrayOfExpression[0];
      if (expression instanceof LambdaExp) {
        LambdaExp lambdaExp = (LambdaExp)expression;
        if (lambdaExp.min_args == 1 && lambdaExp.max_args == 1 && !lambdaExp.firstDecl().getCanWrite())
          return lambdaExp; 
      } 
    } 
    return null;
  }
  
  public static void compileCallCC(ApplyExp paramApplyExp, Compilation paramCompilation, Target paramTarget, Procedure paramProcedure) {
    ClassType classType;
    LambdaExp lambdaExp = canInlineCallCC(paramApplyExp);
    if (lambdaExp == null) {
      ApplyExp.compile(paramApplyExp, paramCompilation, paramTarget);
      return;
    } 
    CodeAttr codeAttr = paramCompilation.getCode();
    Declaration declaration1 = lambdaExp.firstDecl();
    if (declaration1.isSimple() && !declaration1.getCanRead() && !declaration1.getCanWrite()) {
      declaration1.setCanCall(false);
      CompileTimeContinuation compileTimeContinuation = new CompileTimeContinuation();
      if (paramTarget instanceof gnu.expr.StackTarget) {
        Type type = paramTarget.getType();
      } else {
        paramApplyExp = null;
      } 
      compileTimeContinuation.exitableBlock = codeAttr.startExitableBlock((Type)paramApplyExp, ExitThroughFinallyChecker.check(declaration1, lambdaExp.body));
      compileTimeContinuation.blockTarget = paramTarget;
      declaration1.setValue((Expression)new QuoteExp(compileTimeContinuation));
      (new ApplyExp((Expression)lambdaExp, new Expression[] { (Expression)QuoteExp.nullExp })).compile(paramCompilation, paramTarget);
      codeAttr.endExitableBlock();
      return;
    } 
    Variable variable = codeAttr.pushScope().addVariable(codeAttr, (Type)typeContinuation, null);
    Declaration declaration2 = new Declaration(variable);
    codeAttr.emitNew(typeContinuation);
    codeAttr.emitDup((Type)typeContinuation);
    paramCompilation.loadCallContext();
    codeAttr.emitInvokeSpecial(typeContinuation.getDeclaredMethod("<init>", 1));
    codeAttr.emitStore(variable);
    if (paramTarget instanceof gnu.expr.IgnoreTarget || paramTarget instanceof gnu.expr.ConsumerTarget) {
      paramApplyExp = null;
    } else {
      classType = Type.objectType;
    } 
    codeAttr.emitTryStart(false, (Type)classType);
    (new ApplyExp((Expression)lambdaExp, new Expression[] { (Expression)new ReferenceExp(declaration2) })).compile(paramCompilation, paramTarget);
    if (codeAttr.reachableHere()) {
      codeAttr.emitLoad(variable);
      codeAttr.emitPushInt(1);
      codeAttr.emitPutField(typeContinuation.getField("invoked"));
    } 
    codeAttr.emitTryEnd();
    codeAttr.emitCatchStart(null);
    codeAttr.emitLoad(variable);
    if (paramTarget instanceof gnu.expr.ConsumerTarget) {
      paramCompilation.loadCallContext();
      codeAttr.emitInvokeStatic(typeContinuation.getDeclaredMethod("handleException$X", 3));
    } else {
      codeAttr.emitInvokeStatic(typeContinuation.getDeclaredMethod("handleException", 2));
      paramTarget.compileFromStack(paramCompilation, (Type)Type.objectType);
    } 
    codeAttr.emitCatchEnd();
    codeAttr.emitTryCatchEnd();
    codeAttr.popScope();
  }
  
  public static void compileConvert(Convert paramConvert, ApplyExp paramApplyExp, Compilation paramCompilation, Target paramTarget) {
    Expression[] arrayOfExpression = paramApplyExp.getArgs();
    if (arrayOfExpression.length != 2)
      throw new Error("wrong number of arguments to " + paramConvert.getName()); 
    CodeAttr codeAttr = paramCompilation.getCode();
    Type type = Scheme.getTypeValue(arrayOfExpression[0]);
    if (type != null) {
      arrayOfExpression[1].compile(paramCompilation, Target.pushValue(type));
      if (codeAttr.reachableHere())
        paramTarget.compileFromStack(paramCompilation, type); 
      return;
    } 
    if (typeType == null)
      typeType = ClassType.make("gnu.bytecode.Type"); 
    if (coerceMethod == null)
      coerceMethod = typeType.addMethod("coerceFromObject", Compilation.apply1args, (Type)Type.pointer_type, 1); 
    arrayOfExpression[0].compile(paramCompilation, (Type)LangObjType.typeClassType);
    arrayOfExpression[1].compile(paramCompilation, Target.pushObject);
    codeAttr.emitInvokeVirtual(coerceMethod);
    paramTarget.compileFromStack(paramCompilation, (Type)Type.pointer_type);
  }
  
  public static CompileMisc forConvert(Object paramObject) {
    return new CompileMisc((Procedure)paramObject, 2);
  }
  
  public static CompileMisc forNot(Object paramObject) {
    return new CompileMisc((Procedure)paramObject, 3);
  }
  
  public static Expression validateApplyAppendValues(ApplyExp paramApplyExp, InlineCalls paramInlineCalls, Type paramType, Procedure paramProcedure) {
    paramApplyExp.visitArgs(paramInlineCalls);
    Expression[] arrayOfExpression = paramApplyExp.getArgs();
    if (arrayOfExpression.length == 1)
      return arrayOfExpression[0]; 
    if (arrayOfExpression.length == 0)
      return (Expression)QuoteExp.voidExp; 
    Expression expression2 = paramApplyExp.inlineIfConstant(paramProcedure, paramInlineCalls);
    Expression expression1 = expression2;
    return (Expression)((expression2 == paramApplyExp) ? paramApplyExp : expression1);
  }
  
  public static Expression validateApplyCallCC(ApplyExp paramApplyExp, InlineCalls paramInlineCalls, Type paramType, Procedure paramProcedure) {
    LambdaExp lambdaExp = canInlineCallCC(paramApplyExp);
    if (lambdaExp != null) {
      lambdaExp.setInlineOnly(true);
      lambdaExp.returnContinuation = (Expression)paramApplyExp;
      lambdaExp.inlineHome = paramInlineCalls.getCurrentLambda();
      Declaration declaration = lambdaExp.firstDecl();
      if (!declaration.getFlag(8192L))
        declaration.setType((Type)typeContinuation); 
    } 
    paramApplyExp.visitArgs(paramInlineCalls);
    return (Expression)paramApplyExp;
  }
  
  public static Expression validateApplyConstantFunction0(ApplyExp paramApplyExp, InlineCalls paramInlineCalls, Type paramType, Procedure paramProcedure) {
    paramApplyExp.visitArgs(paramInlineCalls);
    int i = paramApplyExp.getArgCount();
    return (Expression)((i != 0 && paramInlineCalls != null) ? paramInlineCalls.noteError(WrongArguments.checkArgCount(paramProcedure, i)) : ((ConstantFunction0)paramProcedure).constant);
  }
  
  public static Expression validateApplyConvert(ApplyExp paramApplyExp, InlineCalls paramInlineCalls, Type paramType, Procedure paramProcedure) {
    Compilation compilation = paramInlineCalls.getCompilation();
    Language language = compilation.getLanguage();
    Expression[] arrayOfExpression = paramApplyExp.getArgs();
    if (arrayOfExpression.length == 2) {
      arrayOfExpression[0] = paramInlineCalls.visit(arrayOfExpression[0], null);
      Type type = language.getTypeFor(arrayOfExpression[0]);
      if (type instanceof Type) {
        arrayOfExpression[0] = (Expression)new QuoteExp(type);
        arrayOfExpression[1] = paramInlineCalls.visit(arrayOfExpression[1], type);
        CompileReflect.checkKnownClass(type, compilation);
        paramApplyExp.setType(type);
        return (Expression)paramApplyExp;
      } 
    } 
    paramApplyExp.visitArgs(paramInlineCalls);
    return (Expression)paramApplyExp;
  }
  
  public static Expression validateApplyFormat(ApplyExp paramApplyExp, InlineCalls paramInlineCalls, Type paramType, Procedure paramProcedure) {
    ApplyExp applyExp;
    PrimType primType;
    paramApplyExp.visitArgs(paramInlineCalls);
    ClassType classType2 = Type.objectType;
    Expression[] arrayOfExpression = paramApplyExp.getArgs();
    ClassType classType1 = classType2;
    if (arrayOfExpression.length > 0) {
      classType1 = ClassType.make("gnu.kawa.functions.Format");
      Object object = arrayOfExpression[0].valueIfConstant();
      Type type = arrayOfExpression[0].getType();
      if (object == Boolean.FALSE || type.isSubtype((Type)LangObjType.stringType)) {
        if (object == Boolean.FALSE) {
          byte b1 = 1;
          Expression[] arrayOfExpression2 = new Expression[arrayOfExpression.length + 1 - b1];
          arrayOfExpression2[0] = (Expression)new QuoteExp(Integer.valueOf(0), (Type)Type.intType);
          System.arraycopy(arrayOfExpression, b1, arrayOfExpression2, 1, arrayOfExpression2.length - 1);
          ApplyExp applyExp1 = new ApplyExp(classType1.getDeclaredMethod("formatToString", 2), arrayOfExpression2);
          applyExp1.setType((Type)Type.javalangStringType);
          return (Expression)applyExp1;
        } 
        byte b = 0;
        Expression[] arrayOfExpression1 = new Expression[arrayOfExpression.length + 1 - b];
        arrayOfExpression1[0] = (Expression)new QuoteExp(Integer.valueOf(0), (Type)Type.intType);
        System.arraycopy(arrayOfExpression, b, arrayOfExpression1, 1, arrayOfExpression1.length - 1);
        applyExp = new ApplyExp(classType1.getDeclaredMethod("formatToString", 2), arrayOfExpression1);
        applyExp.setType((Type)Type.javalangStringType);
        return (Expression)applyExp;
      } 
      if (object == Boolean.TRUE || type.isSubtype((Type)ClassType.make("java.io.Writer"))) {
        Expression[] arrayOfExpression1 = arrayOfExpression;
        if (object == Boolean.TRUE) {
          arrayOfExpression1 = new Expression[arrayOfExpression.length];
          arrayOfExpression1[0] = (Expression)QuoteExp.nullExp;
          System.arraycopy(arrayOfExpression, 1, arrayOfExpression1, 1, arrayOfExpression.length - 1);
        } 
        applyExp = new ApplyExp(classType1.getDeclaredMethod("formatToWriter", 3), arrayOfExpression1);
        applyExp.setType((Type)Type.voidType);
        return (Expression)applyExp;
      } 
      classType1 = classType2;
      if (type.isSubtype((Type)ClassType.make("java.io.OutputStream")))
        primType = Type.voidType; 
    } 
    applyExp.setType((Type)primType);
    return null;
  }
  
  public static Expression validateApplyMakeProcedure(ApplyExp paramApplyExp, InlineCalls paramInlineCalls, Type paramType, Procedure paramProcedure) {
    Object object1;
    Type type;
    Object object2;
    Object object3;
    paramApplyExp.visitArgs(paramInlineCalls);
    Expression[] arrayOfExpression = paramApplyExp.getArgs();
    int j = arrayOfExpression.length;
    paramInlineCalls = null;
    boolean bool = false;
    paramProcedure = null;
    int i = 0;
    while (i < j) {
      Expression expression = arrayOfExpression[i];
      if (expression instanceof QuoteExp) {
        Object object4 = ((QuoteExp)expression).getValue();
        if (object4 instanceof Keyword) {
          String str = ((Keyword)object4).getName();
          int m = i + 1;
          Expression expression1 = arrayOfExpression[m];
          if (str == "name") {
            Object object5 = object3;
            i = m;
            Type type1 = type;
            object4 = object2;
            if (expression1 instanceof QuoteExp) {
              object4 = ((QuoteExp)expression1).getValue().toString();
              type1 = type;
              i = m;
              object5 = object3;
            } 
          } else {
            Object object5 = object3;
            i = m;
            Type type1 = type;
            object4 = object2;
            if (str == "method") {
              int n = object3 + 1;
              Expression expression2 = expression1;
              i = m;
              object4 = object2;
            } 
          } 
          continue;
        } 
      } 
      int k = object3 + 1;
      Object object = object2;
      continue;
      i++;
      object3 = SYNTHETIC_LOCAL_VARIABLE_4;
      type = paramType;
      object2 = SYNTHETIC_LOCAL_VARIABLE_9;
    } 
    if (object3 == true && type instanceof LambdaExp) {
      LambdaExp lambdaExp = (LambdaExp)type;
      int k = 0;
      while (true) {
        object1 = type;
        if (k < j) {
          Expression expression = arrayOfExpression[k];
          i = k;
          if (expression instanceof QuoteExp) {
            object1 = ((QuoteExp)expression).getValue();
            i = k;
            if (object1 instanceof Keyword) {
              object1 = ((Keyword)object1).getName();
              Expression expression1 = arrayOfExpression[++k];
              if (object1 == "name") {
                lambdaExp.setName((String)object2);
                i = k;
              } else {
                i = k;
                if (object1 != "method") {
                  lambdaExp.setProperty(Namespace.EmptyNamespace.getSymbol((String)object1), expression1);
                  i = k;
                } 
              } 
            } 
          } 
          k = i + 1;
          continue;
        } 
        break;
      } 
    } 
    return (Expression)object1;
  }
  
  public static Expression validateApplyMap(ApplyExp paramApplyExp, InlineCalls paramInlineCalls, Type paramType, Procedure paramProcedure) {
    ReferenceExp referenceExp;
    BeginExp beginExp;
    boolean bool;
    Map map = (Map)paramProcedure;
    boolean bool1 = map.collect;
    paramApplyExp.visitArgs(paramInlineCalls);
    Expression[] arrayOfExpression2 = paramApplyExp.getArgs();
    int i = arrayOfExpression2.length;
    if (i < 2)
      return (Expression)paramApplyExp; 
    i--;
    Expression expression2 = arrayOfExpression2[0];
    if (!expression2.side_effects()) {
      bool = true;
    } else {
      bool = false;
    } 
    LetExp letExp1 = new LetExp(new Expression[] { expression2 });
    Declaration declaration2 = letExp1.addDeclaration("%proc", (Type)Compilation.typeProcedure);
    declaration2.noteValue(arrayOfExpression2[0]);
    Expression[] arrayOfExpression1 = new Expression[1];
    LetExp letExp2 = new LetExp(arrayOfExpression1);
    letExp1.setBody((Expression)letExp2);
    if (bool1) {
      j = i + 1;
    } else {
      j = i;
    } 
    LambdaExp lambdaExp = new LambdaExp(j);
    arrayOfExpression1[0] = (Expression)lambdaExp;
    Declaration declaration1 = letExp2.addDeclaration("%loop");
    declaration1.noteValue((Expression)lambdaExp);
    arrayOfExpression1 = new Expression[i];
    LetExp letExp3 = new LetExp(arrayOfExpression1);
    Declaration[] arrayOfDeclaration1 = new Declaration[i];
    Declaration[] arrayOfDeclaration2 = new Declaration[i];
    int j;
    for (j = 0; j < i; j++) {
      String str = "arg" + j;
      arrayOfDeclaration1[j] = lambdaExp.addDeclaration(str);
      arrayOfDeclaration2[j] = letExp3.addDeclaration(str, (Type)Compilation.typePair);
      arrayOfExpression1[j] = (Expression)new ReferenceExp(arrayOfDeclaration1[j]);
      arrayOfDeclaration2[j].noteValue(arrayOfExpression1[j]);
    } 
    if (bool1) {
      Declaration declaration = lambdaExp.addDeclaration("result");
    } else {
      arrayOfExpression1 = null;
    } 
    Expression[] arrayOfExpression5 = new Expression[i + 1];
    if (bool1) {
      j = i + 1;
    } else {
      j = i;
    } 
    Expression[] arrayOfExpression4 = new Expression[j];
    for (j = 0; j < i; j++) {
      arrayOfExpression5[j + 1] = paramInlineCalls.visitApplyOnly(SlotGet.makeGetField((Expression)new ReferenceExp(arrayOfDeclaration2[j]), "car"), null);
      arrayOfExpression4[j] = paramInlineCalls.visitApplyOnly(SlotGet.makeGetField((Expression)new ReferenceExp(arrayOfDeclaration2[j]), "cdr"), null);
    } 
    if (!bool)
      referenceExp = new ReferenceExp(declaration2); 
    arrayOfExpression5[0] = (Expression)referenceExp;
    Expression expression3 = paramInlineCalls.visitApplyOnly(new ApplyExp((Expression)new ReferenceExp(map.applyFieldDecl), arrayOfExpression5), null);
    if (bool1) {
      referenceExp = new ReferenceExp((Declaration)arrayOfExpression1);
      arrayOfExpression4[i] = (Expression)Invoke.makeInvokeStatic(Compilation.typePair, "make", new Expression[] { expression3, (Expression)referenceExp });
    } 
    Expression expression1 = paramInlineCalls.visitApplyOnly(new ApplyExp((Expression)new ReferenceExp(declaration1), arrayOfExpression4), null);
    if (!bool1)
      beginExp = new BeginExp(expression3, expression1); 
    lambdaExp.body = (Expression)beginExp;
    letExp3.setBody(lambdaExp.body);
    lambdaExp.body = (Expression)letExp3;
    if (bool1) {
      j = i + 1;
    } else {
      j = i;
    } 
    Expression[] arrayOfExpression3 = new Expression[j];
    QuoteExp quoteExp = new QuoteExp(LList.Empty);
    j = i;
    while (true) {
      ApplyExp applyExp;
      if (--j >= 0) {
        QuoteExp quoteExp1;
        ReferenceExp referenceExp1 = new ReferenceExp(arrayOfDeclaration1[j]);
        if (bool1) {
          ReferenceExp referenceExp2 = new ReferenceExp((Declaration)arrayOfExpression1);
        } else {
          quoteExp1 = QuoteExp.voidExp;
        } 
        lambdaExp.body = (Expression)new IfExp(paramInlineCalls.visitApplyOnly(new ApplyExp((Procedure)map.isEq, new Expression[] { (Expression)referenceExp1, (Expression)quoteExp }), null), (Expression)quoteExp1, lambdaExp.body);
        arrayOfExpression3[j] = arrayOfExpression2[j + 1];
        continue;
      } 
      if (bool1)
        arrayOfExpression3[i] = (Expression)quoteExp; 
      Expression expression5 = paramInlineCalls.visitApplyOnly(new ApplyExp((Expression)new ReferenceExp(declaration1), arrayOfExpression3), null);
      Expression expression4 = expression5;
      if (bool1)
        applyExp = Invoke.makeInvokeStatic(Compilation.scmListType, "reverseInPlace", new Expression[] { expression5 }); 
      letExp2.setBody((Expression)applyExp);
      return (Expression)(bool ? letExp2 : letExp1);
    } 
  }
  
  public static Expression validateApplyNot(ApplyExp paramApplyExp, InlineCalls paramInlineCalls, Type paramType, Procedure paramProcedure) {
    paramApplyExp.visitArgs(paramInlineCalls);
    paramApplyExp.setType(paramInlineCalls.getCompilation().getLanguage().getTypeFor(boolean.class));
    return paramApplyExp.inlineIfConstant(paramProcedure, paramInlineCalls);
  }
  
  public static Expression validateApplyValuesMap(ApplyExp paramApplyExp, InlineCalls paramInlineCalls, Type paramType, Procedure paramProcedure) {
    paramApplyExp.visitArgs(paramInlineCalls);
    LambdaExp lambdaExp = ValuesMap.canInline(paramApplyExp, (ValuesMap)paramProcedure);
    if (lambdaExp != null) {
      lambdaExp.setInlineOnly(true);
      lambdaExp.returnContinuation = (Expression)paramApplyExp;
      lambdaExp.inlineHome = paramInlineCalls.getCurrentLambda();
    } 
    return (Expression)paramApplyExp;
  }
  
  public void compile(ApplyExp paramApplyExp, Compilation paramCompilation, Target paramTarget) {
    switch (this.code) {
      default:
        throw new Error();
      case 2:
        compileConvert((Convert)this.proc, paramApplyExp, paramCompilation, paramTarget);
        return;
      case 3:
        break;
    } 
    compileNot((Not)this.proc, paramApplyExp, paramCompilation, paramTarget);
  }
  
  public void compileNot(Not paramNot, ApplyExp paramApplyExp, Compilation paramCompilation, Target paramTarget) {
    ConditionalTarget conditionalTarget1;
    ConditionalTarget conditionalTarget2;
    Expression expression = paramApplyExp.getArgs()[0];
    Language language = paramNot.language;
    if (paramTarget instanceof ConditionalTarget) {
      boolean bool;
      conditionalTarget2 = (ConditionalTarget)paramTarget;
      conditionalTarget1 = new ConditionalTarget(conditionalTarget2.ifFalse, conditionalTarget2.ifTrue, language);
      if (!conditionalTarget2.trueBranchComesFirst) {
        bool = true;
      } else {
        bool = false;
      } 
      conditionalTarget1.trueBranchComesFirst = bool;
      expression.compile(paramCompilation, (Target)conditionalTarget1);
      return;
    } 
    CodeAttr codeAttr = paramCompilation.getCode();
    Type type = conditionalTarget2.getType();
    if (conditionalTarget2 instanceof gnu.expr.StackTarget && type.getSignature().charAt(0) == 'Z') {
      expression.compile(paramCompilation, (Target)conditionalTarget2);
      codeAttr.emitNot(conditionalTarget2.getType());
      return;
    } 
    QuoteExp quoteExp = QuoteExp.getInstance(conditionalTarget1.booleanObject(true));
    IfExp.compile(expression, (Expression)QuoteExp.getInstance(conditionalTarget1.booleanObject(false)), (Expression)quoteExp, paramCompilation, (Target)conditionalTarget2);
  }
  
  static class ExitThroughFinallyChecker extends ExpVisitor<Expression, TryExp> {
    Declaration decl;
    
    public static boolean check(Declaration param1Declaration, Expression param1Expression) {
      ExitThroughFinallyChecker exitThroughFinallyChecker = new ExitThroughFinallyChecker();
      exitThroughFinallyChecker.decl = param1Declaration;
      exitThroughFinallyChecker.visit(param1Expression, null);
      return (exitThroughFinallyChecker.exitValue != null);
    }
    
    protected Expression defaultValue(Expression param1Expression, TryExp param1TryExp) {
      return param1Expression;
    }
    
    protected Expression visitReferenceExp(ReferenceExp param1ReferenceExp, TryExp param1TryExp) {
      if (this.decl == param1ReferenceExp.getBinding() && param1TryExp != null)
        this.exitValue = Boolean.TRUE; 
      return (Expression)param1ReferenceExp;
    }
    
    protected Expression visitTryExp(TryExp param1TryExp1, TryExp param1TryExp2) {
      if (param1TryExp1.getFinallyClause() != null)
        param1TryExp2 = param1TryExp1; 
      visitExpression((Expression)param1TryExp1, param1TryExp2);
      return (Expression)param1TryExp1;
    }
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/gnu/kawa/functions/CompileMisc.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */