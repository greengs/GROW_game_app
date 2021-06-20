package gnu.xquery.util;

import gnu.bytecode.ClassType;
import gnu.bytecode.CodeAttr;
import gnu.bytecode.Method;
import gnu.bytecode.Type;
import gnu.bytecode.Variable;
import gnu.expr.ApplyExp;
import gnu.expr.Compilation;
import gnu.expr.ConsumerTarget;
import gnu.expr.Declaration;
import gnu.expr.ErrorExp;
import gnu.expr.Expression;
import gnu.expr.IfExp;
import gnu.expr.InlineCalls;
import gnu.expr.LambdaExp;
import gnu.expr.Language;
import gnu.expr.LetExp;
import gnu.expr.PrimProcedure;
import gnu.expr.QuoteExp;
import gnu.expr.ReferenceExp;
import gnu.expr.Target;
import gnu.kawa.functions.AddOp;
import gnu.kawa.functions.ValuesMap;
import gnu.kawa.reflect.CompileReflect;
import gnu.kawa.reflect.OccurrenceType;
import gnu.kawa.xml.ChildAxis;
import gnu.kawa.xml.CoerceNodes;
import gnu.kawa.xml.DescendantAxis;
import gnu.kawa.xml.DescendantOrSelfAxis;
import gnu.kawa.xml.NodeSetType;
import gnu.kawa.xml.NodeType;
import gnu.kawa.xml.SortNodes;
import gnu.kawa.xml.XDataType;
import gnu.mapping.Procedure;
import gnu.math.IntNum;
import gnu.xquery.lang.XQuery;

public class CompileMisc {
  static final Method castMethod;
  
  static final Method castableMethod;
  
  static final ClassType typeTuples = ClassType.make("gnu.xquery.util.OrderedTuples");
  
  static final ClassType typeXDataType = ClassType.make("gnu.kawa.xml.XDataType");
  
  static {
    castMethod = typeXDataType.getDeclaredMethod("cast", 1);
    castableMethod = typeXDataType.getDeclaredMethod("castable", 1);
  }
  
  public static void compileOrderedMap(ApplyExp paramApplyExp, Compilation paramCompilation, Target paramTarget, Procedure paramProcedure) {
    Expression[] arrayOfExpression = paramApplyExp.getArgs();
    if (arrayOfExpression.length != 2) {
      ApplyExp.compile(paramApplyExp, paramCompilation, paramTarget);
      return;
    } 
    CodeAttr codeAttr = paramCompilation.getCode();
    Variable variable = codeAttr.pushScope().addVariable(codeAttr, (Type)typeTuples, null);
    arrayOfExpression[1].compile(paramCompilation, Target.pushValue((Type)typeTuples));
    codeAttr.emitStore(variable);
    ConsumerTarget consumerTarget = new ConsumerTarget(variable);
    arrayOfExpression[0].compile(paramCompilation, (Target)consumerTarget);
    Method method = typeTuples.getDeclaredMethod("run$X", 1);
    codeAttr.emitLoad(variable);
    PrimProcedure.compileInvoke(paramCompilation, method, paramTarget, paramApplyExp.isTailCall(), 182, (Type)Type.pointer_type);
    codeAttr.popScope();
  }
  
  public static Expression validateApplyCastAs(ApplyExp paramApplyExp, InlineCalls paramInlineCalls, Type paramType, Procedure paramProcedure) {
    paramApplyExp.visitArgs(paramInlineCalls);
    paramApplyExp = CompileReflect.inlineClassName(paramApplyExp, 0, paramInlineCalls);
    Expression[] arrayOfExpression = paramApplyExp.getArgs();
    return (Expression)((arrayOfExpression.length == 2 && arrayOfExpression[0] instanceof QuoteExp && ((QuoteExp)arrayOfExpression[0]).getValue() instanceof XDataType) ? new ApplyExp(castMethod, arrayOfExpression) : paramApplyExp);
  }
  
  public static Expression validateApplyCastableAs(ApplyExp paramApplyExp, InlineCalls paramInlineCalls, Type paramType, Procedure paramProcedure) {
    paramApplyExp.visitArgs(paramInlineCalls);
    paramApplyExp = CompileReflect.inlineClassName(paramApplyExp, 1, paramInlineCalls);
    Expression[] arrayOfExpression = paramApplyExp.getArgs();
    return (Expression)((arrayOfExpression.length == 2 && arrayOfExpression[1] instanceof QuoteExp && ((QuoteExp)arrayOfExpression[1]).getValue() instanceof XDataType) ? new ApplyExp(castableMethod, new Expression[] { arrayOfExpression[1], arrayOfExpression[0] }) : paramApplyExp);
  }
  
  public static Expression validateApplyOrderedMap(ApplyExp paramApplyExp, InlineCalls paramInlineCalls, Type paramType, Procedure paramProcedure) {
    ApplyExp applyExp;
    paramApplyExp.visitArgs(paramInlineCalls);
    Expression[] arrayOfExpression = paramApplyExp.getArgs();
    if (arrayOfExpression.length > 2) {
      Expression[] arrayOfExpression1 = new Expression[arrayOfExpression.length - 1];
      System.arraycopy(arrayOfExpression, 1, arrayOfExpression1, 0, arrayOfExpression1.length);
      Method method = typeTuples.getDeclaredMethod("make$V", 2);
      applyExp = new ApplyExp(paramProcedure, new Expression[] { arrayOfExpression[0], (Expression)new ApplyExp(method, arrayOfExpression1) });
    } 
    return (Expression)applyExp;
  }
  
  public static Expression validateApplyRelativeStep(ApplyExp paramApplyExp, InlineCalls paramInlineCalls, Type paramType, Procedure paramProcedure) {
    String str;
    paramApplyExp.visitArgs(paramInlineCalls);
    Expression[] arrayOfExpression = paramApplyExp.getArgs();
    Expression expression2 = arrayOfExpression[0];
    Expression expression1 = arrayOfExpression[1];
    Compilation compilation = paramInlineCalls.getCompilation();
    if (expression1 instanceof LambdaExp && compilation.mustCompile) {
      ApplyExp applyExp1;
      Expression expression3;
      Declaration declaration3;
      LambdaExp lambdaExp = (LambdaExp)expression1;
      if (lambdaExp.min_args != 3 || lambdaExp.max_args != 3)
        return (Expression)paramApplyExp; 
      lambdaExp.setInlineOnly(true);
      lambdaExp.returnContinuation = (Expression)paramApplyExp;
      lambdaExp.inlineHome = paramInlineCalls.getCurrentLambda();
      Expression expression4 = lambdaExp.body;
      Declaration declaration4 = lambdaExp.firstDecl().nextDecl();
      Declaration declaration2 = declaration4.nextDecl();
      declaration4.setNext(declaration2.nextDecl());
      declaration2.setNext(null);
      lambdaExp.min_args = 2;
      lambdaExp.max_args = 2;
      Type type2 = expression2.getType();
      if (type2 != null && NodeType.anyNodeTest.compare(type2) == -3) {
        Language language = paramInlineCalls.getCompilation().getLanguage();
        str = "step input is " + language.formatType(type2) + " - not a node sequence";
        paramInlineCalls.getMessages().error('e', str);
        return (Expression)new ErrorExp(str);
      } 
      Type type1 = str.getTypeRaw();
      if (type1 == null || type1 == Type.pointer_type) {
        type1 = OccurrenceType.itemPrimeType(expression4.getType());
        if (NodeType.anyNodeTest.compare(type1) >= 0) {
          type1 = NodeSetType.getInstance(type1);
        } else {
          type1 = OccurrenceType.getInstance(type1, 0, -1);
        } 
        str.setType(type1);
      } 
      if (declaration2.getCanRead()) {
        ClassType classType = CoerceNodes.typeNodes;
        compilation.letStart();
        declaration3 = compilation.letVariable(null, (Type)classType, (Expression)new ApplyExp((Procedure)CoerceNodes.coerceNodes, new Expression[] { expression2 }));
        compilation.letEnter();
        LetExp letExp = new LetExp(new Expression[] { (Expression)new ApplyExp(classType.getDeclaredMethod("size", 0), new Expression[] { (Expression)new ReferenceExp(declaration3) }) });
        letExp.addDeclaration(declaration2);
        letExp.body = (Expression)new ApplyExp(str.getFunction(), new Expression[] { (Expression)new ReferenceExp(declaration3), (Expression)lambdaExp });
        return (Expression)compilation.letDone((Expression)letExp);
      } 
      String str2 = str;
      Declaration declaration1 = declaration3;
      String str1 = str2;
      if (declaration3 instanceof ApplyExp) {
        ApplyExp applyExp = (ApplyExp)declaration3;
        declaration1 = declaration3;
        str1 = str2;
        if (applyExp.getFunction().valueIfConstant() instanceof ValuesFilter) {
          Expression expression = applyExp.getArgs()[1];
          declaration1 = declaration3;
          str1 = str2;
          if (expression instanceof LambdaExp) {
            LambdaExp lambdaExp1 = (LambdaExp)expression;
            Declaration declaration = lambdaExp1.firstDecl();
            declaration1 = declaration3;
            str1 = str2;
            if (declaration != null) {
              declaration = declaration.nextDecl();
              declaration1 = declaration3;
              str1 = str2;
              if (declaration != null) {
                declaration1 = declaration3;
                str1 = str2;
                if (declaration.nextDecl() == null) {
                  declaration1 = declaration3;
                  str1 = str2;
                  if (!declaration.getCanRead()) {
                    declaration1 = declaration3;
                    str1 = str2;
                    if (ClassType.make("java.lang.Number").compare(lambdaExp1.body.getType()) == -3) {
                      expression3 = applyExp.getArg(0);
                      lambdaExp.body = expression3;
                      applyExp.setArg(0, (Expression)str);
                      applyExp1 = applyExp;
                    } 
                  } 
                } 
              } 
            } 
          } 
        } 
      } 
      ApplyExp applyExp2 = applyExp1;
      if (expression2 instanceof ApplyExp) {
        applyExp2 = applyExp1;
        if (expression3 instanceof ApplyExp) {
          ApplyExp applyExp4 = (ApplyExp)expression2;
          ApplyExp applyExp3 = (ApplyExp)expression3;
          Object object2 = applyExp4.getFunction().valueIfConstant();
          Object object1 = applyExp3.getFunction().valueIfConstant();
          applyExp2 = applyExp1;
          if (object2 == RelativeStep.relativeStep) {
            applyExp2 = applyExp1;
            if (object1 instanceof ChildAxis) {
              applyExp2 = applyExp1;
              if (applyExp4.getArgCount() == 2) {
                object2 = applyExp4.getArg(1);
                applyExp2 = applyExp1;
                if (object2 instanceof LambdaExp) {
                  object2 = object2;
                  applyExp2 = applyExp1;
                  if (((LambdaExp)object2).body instanceof ApplyExp) {
                    applyExp2 = applyExp1;
                    if (((ApplyExp)((LambdaExp)object2).body).getFunction().valueIfConstant() == DescendantOrSelfAxis.anyNode) {
                      str.setArg(0, applyExp4.getArg(0));
                      applyExp3.setFunction((Expression)new QuoteExp(DescendantAxis.make(((ChildAxis)object1).getNodePredicate())));
                      return (Expression)applyExp1;
                    } 
                  } 
                } 
              } 
            } 
          } 
        } 
      } 
      return (Expression)applyExp2;
    } 
    return (Expression)str;
  }
  
  public static Expression validateApplyValuesFilter(ApplyExp paramApplyExp, InlineCalls paramInlineCalls, Type paramType, Procedure paramProcedure) {
    ApplyExp applyExp;
    ValuesFilter valuesFilter = (ValuesFilter)paramProcedure;
    paramApplyExp.visitArgs(paramInlineCalls);
    Expression[] arrayOfExpression = paramApplyExp.getArgs();
    Expression expression = arrayOfExpression[1];
    if (expression instanceof LambdaExp) {
      LambdaExp lambdaExp = (LambdaExp)expression;
      if (lambdaExp.min_args == 3 && lambdaExp.max_args == 3) {
        paramApplyExp.setType(arrayOfExpression[0].getType());
        Compilation compilation = paramInlineCalls.getCompilation();
        Declaration declaration2 = lambdaExp.firstDecl();
        Declaration declaration3 = declaration2.nextDecl();
        Declaration declaration1 = declaration3.nextDecl();
        lambdaExp.setInlineOnly(true);
        lambdaExp.returnContinuation = (Expression)paramApplyExp;
        lambdaExp.inlineHome = paramInlineCalls.getCurrentLambda();
        lambdaExp.remove(declaration3, declaration1);
        lambdaExp.min_args = 2;
        lambdaExp.max_args = 2;
        if (declaration1.getCanRead() || valuesFilter.kind == 'R') {
          ApplyExp applyExp1;
          Method method;
          ClassType classType;
          LetExp letExp2;
          compilation.letStart();
          Expression expression2 = arrayOfExpression[0];
          if (valuesFilter.kind == 'P') {
            Type type = expression2.getType();
            method = Compilation.typeValues.getDeclaredMethod("countValues", 1);
          } else {
            classType = SortNodes.typeSortedNodes;
            applyExp1 = new ApplyExp((Procedure)SortNodes.sortNodes, new Expression[] { expression2 });
            method = CoerceNodes.typeNodes.getDeclaredMethod("size", 0);
          } 
          Declaration declaration = compilation.letVariable("sequence", (Type)classType, (Expression)applyExp1);
          compilation.letEnter();
          Expression expression3 = lambdaExp.body;
          Expression expression1 = expression3;
          if (lambdaExp.body.getType() != XDataType.booleanType)
            applyExp = new ApplyExp(ValuesFilter.matchesMethod, new Expression[] { expression3, (Expression)new ReferenceExp(declaration3) }); 
          ApplyExp applyExp2 = applyExp;
          if (valuesFilter.kind == 'R') {
            Declaration declaration4 = new Declaration(null, (Type)Type.intType);
            applyExp2 = new ApplyExp((Procedure)AddOp.$Mn, new Expression[] { (Expression)new ReferenceExp(declaration1), (Expression)new ReferenceExp(declaration4) });
            letExp2 = new LetExp(new Expression[] { (Expression)new ApplyExp((Procedure)AddOp.$Pl, new Expression[] { (Expression)applyExp2, (Expression)new QuoteExp(IntNum.one()) }) });
            lambdaExp.replaceFollowing(declaration2, declaration4);
            letExp2.add(declaration3);
            letExp2.body = (Expression)applyExp;
          } 
          lambdaExp.body = (Expression)new IfExp((Expression)letExp2, (Expression)new ReferenceExp(declaration2), (Expression)QuoteExp.voidExp);
          applyExp = new ApplyExp((Procedure)ValuesMap.valuesMapWithPos, new Expression[] { (Expression)lambdaExp, (Expression)new ReferenceExp(declaration) });
          applyExp.setType(declaration2.getType());
          lambdaExp.returnContinuation = (Expression)applyExp;
          LetExp letExp1 = new LetExp(new Expression[] { (Expression)new ApplyExp(method, new Expression[] { (Expression)new ReferenceExp(declaration) }) });
          letExp1.add(declaration1);
          letExp1.body = gnu.kawa.functions.CompileMisc.validateApplyValuesMap(applyExp, paramInlineCalls, paramType, (Procedure)ValuesMap.valuesMapWithPos);
          return (Expression)compilation.letDone((Expression)letExp1);
        } 
      } 
    } 
    return (Expression)applyExp;
  }
  
  public static Expression validateArithOp(ApplyExp paramApplyExp, InlineCalls paramInlineCalls, Type paramType, Procedure paramProcedure) {
    paramApplyExp.visitArgs(paramInlineCalls);
    return (Expression)paramApplyExp;
  }
  
  public static Expression validateBooleanValue(ApplyExp paramApplyExp, InlineCalls paramInlineCalls, Type paramType, Procedure paramProcedure) {
    paramApplyExp.visitArgs(paramInlineCalls);
    Expression[] arrayOfExpression = paramApplyExp.getArgs();
    if (arrayOfExpression.length == 1) {
      Expression expression = arrayOfExpression[0];
      Type type = expression.getType();
      if (type == XDataType.booleanType)
        return expression; 
      if (type == null)
        paramApplyExp.setType((Type)XDataType.booleanType); 
      if (expression instanceof QuoteExp) {
        object = ((QuoteExp)expression).getValue();
        try {
          if (BooleanValue.booleanValue(object)) {
            object = XQuery.trueExp;
          } else {
            object = XQuery.falseExp;
          } 
        } catch (Throwable object) {
          paramInlineCalls.getMessages().error('e', "cannot convert to a boolean");
          return (Expression)new ErrorExp("cannot convert to a boolean");
        } 
      } else {
        return (Expression)object;
      } 
    } else {
      return (Expression)object;
    } 
    return (Expression)object;
  }
  
  public static Expression validateCompare(ApplyExp paramApplyExp, InlineCalls paramInlineCalls, Type paramType, Procedure paramProcedure) {
    paramApplyExp.visitArgs(paramInlineCalls);
    Expression expression = paramApplyExp.inlineIfConstant(paramProcedure, paramInlineCalls);
    if (expression != paramApplyExp)
      return expression; 
    Compare compare = (Compare)paramProcedure;
    if ((compare.flags & 0x20) == 0)
      paramApplyExp = new ApplyExp(ClassType.make("gnu.xquery.util.Compare").getDeclaredMethod("apply", 4), new Expression[] { (Expression)new QuoteExp(IntNum.make(compare.flags)), paramApplyExp.getArg(0), paramApplyExp.getArg(1), (Expression)QuoteExp.nullExp }); 
    if (paramApplyExp.getTypeRaw() == null)
      paramApplyExp.setType((Type)XDataType.booleanType); 
    return (Expression)paramApplyExp;
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/gnu/xquery/util/CompileMisc.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */