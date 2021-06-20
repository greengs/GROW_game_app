package gnu.kawa.xml;

import gnu.bytecode.ClassType;
import gnu.bytecode.CodeAttr;
import gnu.bytecode.Method;
import gnu.bytecode.Variable;
import gnu.expr.ApplyExp;
import gnu.expr.Compilation;
import gnu.expr.ConsumerTarget;
import gnu.expr.Expression;
import gnu.expr.Target;
import gnu.lists.Consumer;
import gnu.lists.TreeList;
import gnu.lists.XConsumer;
import gnu.mapping.CallContext;
import gnu.mapping.Values;
import gnu.xml.XMLFilter;

public class MakeWithBaseUri extends NodeConstructor {
  static final Method beginEntityMethod;
  
  static final Method endEntityMethod;
  
  public static final MakeWithBaseUri makeWithBaseUri = new MakeWithBaseUri();
  
  static final ClassType typeXConsumer = ClassType.make("gnu.lists.XConsumer");
  
  static {
    beginEntityMethod = typeXConsumer.getDeclaredMethod("beginEntity", 1);
    endEntityMethod = typeXConsumer.getDeclaredMethod("endEntity", 0);
  }
  
  public void apply(CallContext paramCallContext) {
    Consumer consumer = paramCallContext.consumer;
    XMLFilter xMLFilter = NodeConstructor.pushNodeContext(paramCallContext);
    null = paramCallContext.getNextArg();
    Object object = paramCallContext.getNextArg();
    if (xMLFilter instanceof XConsumer)
      ((XConsumer)xMLFilter).beginEntity(null); 
    try {
      Values.writeValues(object, (Consumer)xMLFilter);
      return;
    } finally {
      if (xMLFilter instanceof XConsumer)
        ((XConsumer)xMLFilter).endEntity(); 
      if (xMLFilter instanceof TreeList)
        ((TreeList)xMLFilter).dump(); 
      NodeConstructor.popNodeContext(consumer, paramCallContext);
    } 
  }
  
  public void compileToNode(ApplyExp paramApplyExp, Compilation paramCompilation, ConsumerTarget paramConsumerTarget) {
    Variable variable = paramConsumerTarget.getConsumerVariable();
    Expression[] arrayOfExpression = paramApplyExp.getArgs();
    int i = arrayOfExpression.length;
    CodeAttr codeAttr = paramCompilation.getCode();
    codeAttr.emitLoad(variable);
    arrayOfExpression[0].compile(paramCompilation, Target.pushObject);
    codeAttr.emitInvokeInterface(beginEntityMethod);
    compileChild(arrayOfExpression[1], paramCompilation, paramConsumerTarget);
    codeAttr.emitLoad(variable);
    codeAttr.emitInvokeInterface(endEntityMethod);
  }
  
  public int numArgs() {
    return 8194;
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/gnu/kawa/xml/MakeWithBaseUri.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */