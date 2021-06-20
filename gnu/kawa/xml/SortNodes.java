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

public class SortNodes extends Procedure1 implements Inlineable {
  public static final Method canonicalizeMethod;
  
  public static final Method makeSortedNodesMethod;
  
  public static final SortNodes sortNodes = new SortNodes();
  
  public static final ClassType typeSortedNodes = ClassType.make("gnu.kawa.xml.SortedNodes");
  
  static {
    makeSortedNodesMethod = typeSortedNodes.getDeclaredMethod("<init>", 0);
    canonicalizeMethod = Compilation.typeValues.getDeclaredMethod("canonicalize", 0);
  }
  
  public Object apply1(Object paramObject) {
    SortedNodes sortedNodes = new SortedNodes();
    Values.writeValues(paramObject, (Consumer)sortedNodes);
    return (sortedNodes.count > 1) ? sortedNodes : ((sortedNodes.count == 0) ? Values.empty : sortedNodes.get(0));
  }
  
  public void compile(ApplyExp paramApplyExp, Compilation paramCompilation, Target paramTarget) {
    Method method;
    Expression[] arrayOfExpression = paramApplyExp.getArgs();
    if (arrayOfExpression.length != 1 || !paramCompilation.mustCompile) {
      ApplyExp.compile(paramApplyExp, paramCompilation, paramTarget);
      return;
    } 
    if (paramTarget instanceof ConsumerTarget || (paramTarget instanceof gnu.expr.StackTarget && paramTarget.getType().isSubtype((Type)Compilation.typeValues))) {
      paramApplyExp = null;
    } else {
      method = canonicalizeMethod;
    } 
    ConsumerTarget.compileUsingConsumer(arrayOfExpression[0], paramCompilation, paramTarget, makeSortedNodesMethod, method);
  }
  
  public Type getReturnType(Expression[] paramArrayOfExpression) {
    return (Type)Compilation.typeObject;
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/gnu/kawa/xml/SortNodes.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */