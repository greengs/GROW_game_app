package gnu.xquery.util;

import gnu.bytecode.Type;
import gnu.expr.ApplyExp;
import gnu.expr.Compilation;
import gnu.expr.Expression;
import gnu.expr.Inlineable;
import gnu.expr.Target;
import gnu.lists.Consumer;
import gnu.mapping.CallContext;
import gnu.mapping.MethodProc;
import gnu.mapping.Procedure;
import gnu.mapping.Values;

public class OrderedMap extends MethodProc implements Inlineable {
  public static final OrderedMap orderedMap = new OrderedMap();
  
  static {
    orderedMap.setProperty(Procedure.validateApplyKey, "gnu.xquery.util.CompileMisc:validateApplyOrderedMap");
  }
  
  public static Object[] makeTuple$V(Object[] paramArrayOfObject) {
    return paramArrayOfObject;
  }
  
  public void apply(CallContext paramCallContext) throws Throwable {
    OrderedTuples orderedTuples;
    Object[] arrayOfObject = paramCallContext.getArgs();
    Object object = arrayOfObject[0];
    if (arrayOfObject.length == 2) {
      orderedTuples = (OrderedTuples)arrayOfObject[1];
    } else {
      Object[] arrayOfObject1 = new Object[orderedTuples.length - 2];
      System.arraycopy(orderedTuples, 2, arrayOfObject1, 0, arrayOfObject1.length);
      orderedTuples = OrderedTuples.make$V((Procedure)orderedTuples[1], arrayOfObject1);
    } 
    Values.writeValues(object, (Consumer)orderedTuples);
    orderedTuples.run$X(paramCallContext);
  }
  
  public void compile(ApplyExp paramApplyExp, Compilation paramCompilation, Target paramTarget) {
    CompileMisc.compileOrderedMap(paramApplyExp, paramCompilation, paramTarget, (Procedure)this);
  }
  
  public Type getReturnType(Expression[] paramArrayOfExpression) {
    return (Type)Type.pointer_type;
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/gnu/xquery/util/OrderedMap.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */