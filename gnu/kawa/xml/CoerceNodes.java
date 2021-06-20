package gnu.kawa.xml;

import gnu.bytecode.ClassType;
import gnu.bytecode.Method;
import gnu.bytecode.Type;
import gnu.expr.ApplyExp;
import gnu.expr.Compilation;
import gnu.expr.ConsumerTarget;
import gnu.expr.Expression;
import gnu.expr.Inlineable;
import gnu.expr.Target;
import gnu.lists.Consumer;
import gnu.mapping.Procedure1;
import gnu.mapping.Values;

public class CoerceNodes extends Procedure1 implements Inlineable {
  public static final CoerceNodes coerceNodes = new CoerceNodes();
  
  public static final Method makeNodesMethod;
  
  public static final ClassType typeNodes = ClassType.make("gnu.kawa.xml.Nodes");
  
  static {
    makeNodesMethod = typeNodes.getDeclaredMethod("<init>", 0);
  }
  
  public Object apply1(Object paramObject) {
    Nodes nodes = new Nodes();
    Values.writeValues(paramObject, (Consumer)nodes);
    return nodes;
  }
  
  public void compile(ApplyExp paramApplyExp, Compilation paramCompilation, Target paramTarget) {
    Expression[] arrayOfExpression = paramApplyExp.getArgs();
    if (arrayOfExpression.length != 1) {
      ApplyExp.compile(paramApplyExp, paramCompilation, paramTarget);
      return;
    } 
    ConsumerTarget.compileUsingConsumer(arrayOfExpression[0], paramCompilation, paramTarget, makeNodesMethod, null);
  }
  
  public Type getReturnType(Expression[] paramArrayOfExpression) {
    return (Type)typeNodes;
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/gnu/kawa/xml/CoerceNodes.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */