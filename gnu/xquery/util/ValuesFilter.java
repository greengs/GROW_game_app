package gnu.xquery.util;

import gnu.bytecode.ClassType;
import gnu.bytecode.Method;
import gnu.bytecode.Type;
import gnu.expr.ApplyExp;
import gnu.expr.Compilation;
import gnu.expr.ConsumerTarget;
import gnu.expr.Expression;
import gnu.expr.Inlineable;
import gnu.expr.LambdaExp;
import gnu.expr.Target;
import gnu.kawa.functions.NumberCompare;
import gnu.kawa.functions.ValuesMap;
import gnu.kawa.xml.SortedNodes;
import gnu.lists.Consumer;
import gnu.mapping.CallContext;
import gnu.mapping.MethodProc;
import gnu.mapping.Procedure;
import gnu.mapping.Values;
import gnu.math.IntNum;

public class ValuesFilter extends MethodProc implements Inlineable {
  public static final ValuesFilter exprFilter;
  
  public static final ValuesFilter forwardFilter = new ValuesFilter('F');
  
  public static final Method matchesMethod;
  
  public static final ValuesFilter reverseFilter = new ValuesFilter('R');
  
  public static final ClassType typeValuesFilter;
  
  char kind;
  
  int last_or_position_needed = 2;
  
  static {
    exprFilter = new ValuesFilter('P');
    typeValuesFilter = ClassType.make("gnu.xquery.util.ValuesFilter");
    matchesMethod = typeValuesFilter.getDeclaredMethod("matches", 2);
  }
  
  public ValuesFilter(char paramChar) {
    this.kind = paramChar;
    setProperty(Procedure.validateApplyKey, "gnu.xquery.util.CompileMisc:validateApplyValuesFilter");
  }
  
  public static ValuesFilter get(char paramChar) {
    return (paramChar == 'F') ? forwardFilter : ((paramChar == 'R') ? reverseFilter : exprFilter);
  }
  
  public static boolean matches(Object paramObject, long paramLong) {
    Object object = paramObject;
    if (paramObject instanceof Values)
      object = ((Values)paramObject).canonicalize(); 
    return (object instanceof Number) ? ((object instanceof IntNum) ? (!(IntNum.compare((IntNum)object, paramLong) != 0)) : ((object instanceof Double || object instanceof Float || object instanceof gnu.math.DFloNum) ? (!(((Number)object).doubleValue() != paramLong)) : ((object instanceof Long || object instanceof Integer || object instanceof Short || object instanceof Byte) ? (!(paramLong != ((Number)object).longValue())) : NumberCompare.applyWithPromotion(8, IntNum.make(paramLong), object)))) : BooleanValue.booleanValue(object);
  }
  
  public void apply(CallContext paramCallContext) throws Throwable {
    IntNum intNum1;
    Object object = paramCallContext.getNextArg();
    Procedure procedure = (Procedure)paramCallContext.getNextArg();
    Consumer consumer = paramCallContext.consumer;
    if (this.kind != 'P') {
      SortedNodes sortedNodes = new SortedNodes();
      Values.writeValues(object, (Consumer)sortedNodes);
    } else if (object instanceof Values) {
      Values values = (Values)object;
    } else {
      intNum1 = IntNum.one();
      if (matches(procedure.apply3(object, intNum1, intNum1), 1L))
        consumer.writeObject(object); 
      return;
    } 
    int k = intNum1.size();
    int j = 0;
    IntNum intNum2 = IntNum.make(k);
    int m = procedure.maxArgs();
    int i = 0;
    while (true) {
      if (i < k) {
        int n = intNum1.nextPos(j);
        Object object1 = intNum1.getPosPrevious(n);
        if (this.kind == 'R') {
          j = k - i;
        } else {
          j = i + 1;
        } 
        object = IntNum.make(j);
        if (m == 2) {
          object = procedure.apply2(object1, object);
        } else {
          object = procedure.apply3(object1, object, intNum2);
        } 
        if (matches(object, j))
          consumer.writeObject(object1); 
        i++;
        j = n;
        continue;
      } 
      return;
    } 
  }
  
  public void compile(ApplyExp paramApplyExp, Compilation paramCompilation, Target paramTarget) {
    Expression[] arrayOfExpression = paramApplyExp.getArgs();
    Expression expression1 = arrayOfExpression[0];
    Expression expression2 = arrayOfExpression[1];
    if (paramTarget instanceof gnu.expr.IgnoreTarget) {
      expression1.compile(paramCompilation, paramTarget);
      expression2.compile(paramCompilation, paramTarget);
      return;
    } 
    if (!(expression2 instanceof LambdaExp)) {
      ApplyExp.compile(paramApplyExp, paramCompilation, paramTarget);
      return;
    } 
    if (!(paramTarget instanceof ConsumerTarget)) {
      ConsumerTarget.compileUsingConsumer((Expression)paramApplyExp, paramCompilation, paramTarget);
      return;
    } 
    ValuesMap.compileInlined((LambdaExp)expression2, expression1, 1, matchesMethod, paramCompilation, paramTarget);
  }
  
  public Type getReturnType(Expression[] paramArrayOfExpression) {
    return (Type)Type.pointer_type;
  }
  
  public int numArgs() {
    return 8194;
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/gnu/xquery/util/ValuesFilter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */