package gnu.kawa.xml;

import gnu.bytecode.CodeAttr;
import gnu.bytecode.Method;
import gnu.bytecode.Variable;
import gnu.expr.ApplyExp;
import gnu.expr.Compilation;
import gnu.expr.ConsumerTarget;
import gnu.expr.Expression;
import gnu.lists.Consumable;
import gnu.lists.Consumer;
import gnu.mapping.CallContext;
import gnu.mapping.Location;
import gnu.xml.XMLFilter;

public class DocumentConstructor extends NodeConstructor {
  public static final DocumentConstructor documentConstructor = new DocumentConstructor();
  
  static final Method endDocumentMethod;
  
  static final Method startDocumentMethod = Compilation.typeConsumer.getDeclaredMethod("startDocument", 0);
  
  static {
    endDocumentMethod = Compilation.typeConsumer.getDeclaredMethod("endDocument", 0);
  }
  
  public void apply(CallContext paramCallContext) {
    Consumer consumer = paramCallContext.consumer;
    null = pushNodeContext(paramCallContext);
    try {
      String str = Location.UNBOUND;
      null.startDocument();
      while (true) {
        Object object = paramCallContext.getNextArg(str);
        if (object == str) {
          null.endDocument();
          return;
        } 
        if (object instanceof Consumable) {
          ((Consumable)object).consume((Consumer)null);
          continue;
        } 
        null.writeObject(object);
      } 
    } finally {
      popNodeContext(consumer, paramCallContext);
    } 
  }
  
  public void compileToNode(ApplyExp paramApplyExp, Compilation paramCompilation, ConsumerTarget paramConsumerTarget) {
    Variable variable = paramConsumerTarget.getConsumerVariable();
    Expression[] arrayOfExpression = paramApplyExp.getArgs();
    int j = arrayOfExpression.length;
    CodeAttr codeAttr = paramCompilation.getCode();
    codeAttr.emitLoad(variable);
    codeAttr.emitInvokeInterface(startDocumentMethod);
    int i;
    for (i = 0; i < j; i++)
      compileChild(arrayOfExpression[i], paramCompilation, paramConsumerTarget); 
    codeAttr.emitLoad(variable);
    codeAttr.emitInvokeInterface(endDocumentMethod);
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/gnu/kawa/xml/DocumentConstructor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */