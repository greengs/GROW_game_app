package gnu.kawa.xml;

import gnu.bytecode.Type;
import gnu.expr.ApplyExp;
import gnu.expr.Compilation;
import gnu.expr.ConsumerTarget;
import gnu.expr.Expression;
import gnu.expr.Inlineable;
import gnu.expr.Target;
import gnu.kawa.functions.AppendValues;
import gnu.lists.Consumer;
import gnu.mapping.Procedure;
import gnu.mapping.Procedure2;
import gnu.mapping.Values;

public class UnionNodes extends Procedure2 implements Inlineable {
  public static final UnionNodes unionNodes = new UnionNodes();
  
  public Object apply2(Object paramObject1, Object paramObject2) {
    SortedNodes sortedNodes = new SortedNodes();
    Values.writeValues(paramObject1, (Consumer)sortedNodes);
    Values.writeValues(paramObject2, (Consumer)sortedNodes);
    return sortedNodes;
  }
  
  public void compile(ApplyExp paramApplyExp, Compilation paramCompilation, Target paramTarget) {
    ConsumerTarget.compileUsingConsumer((Expression)new ApplyExp((Procedure)AppendValues.appendValues, paramApplyExp.getArgs()), paramCompilation, paramTarget, SortNodes.makeSortedNodesMethod, null);
  }
  
  public Type getReturnType(Expression[] paramArrayOfExpression) {
    return (Type)Compilation.typeObject;
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/gnu/kawa/xml/UnionNodes.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */