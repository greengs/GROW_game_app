package gnu.kawa.xml;

import gnu.bytecode.ClassType;
import gnu.bytecode.CodeAttr;
import gnu.bytecode.Method;
import gnu.bytecode.Type;
import gnu.bytecode.Variable;
import gnu.expr.ApplyExp;
import gnu.expr.Compilation;
import gnu.expr.ConsumerTarget;
import gnu.expr.Expression;
import gnu.expr.QuoteExp;
import gnu.expr.Special;
import gnu.expr.Target;
import gnu.lists.Consumable;
import gnu.lists.Consumer;
import gnu.mapping.CallContext;
import gnu.xml.XMLFilter;

public class MakeAttribute extends NodeConstructor {
  static final Method endAttributeMethod;
  
  public static final MakeAttribute makeAttribute = new MakeAttribute();
  
  public static final QuoteExp makeAttributeExp = new QuoteExp(makeAttribute);
  
  static final Method startAttributeMethod;
  
  static final ClassType typeMakeAttribute = ClassType.make("gnu.kawa.xml.MakeAttribute");
  
  static {
    startAttributeMethod = typeMakeAttribute.getDeclaredMethod("startAttribute", 2);
    endAttributeMethod = Compilation.typeConsumer.getDeclaredMethod("endAttribute", 0);
  }
  
  public static void startAttribute(Consumer paramConsumer, Object paramObject) {
    paramConsumer.startAttribute(paramObject);
  }
  
  public void apply(CallContext paramCallContext) {
    Consumer consumer = paramCallContext.consumer;
    null = pushNodeContext(paramCallContext);
    try {
      startAttribute((Consumer)null, paramCallContext.getNextArg());
      Special special = Special.dfault;
      while (true) {
        Object object = paramCallContext.getNextArg(special);
        if (object == special) {
          null.endAttribute();
          return;
        } 
        if (object instanceof Consumable) {
          ((Consumable)object).consume((Consumer)null);
          continue;
        } 
        paramCallContext.writeValue(object);
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
    codeAttr.emitDup();
    arrayOfExpression[0].compile(paramCompilation, Target.pushObject);
    codeAttr.emitInvokeStatic(startAttributeMethod);
    int i;
    for (i = 1; i < j; i++)
      compileChild(arrayOfExpression[i], paramCompilation, paramConsumerTarget); 
    codeAttr.emitInvokeInterface(endAttributeMethod);
  }
  
  public Type getReturnType(Expression[] paramArrayOfExpression) {
    return (Type)Compilation.typeObject;
  }
  
  public int numArgs() {
    return -4095;
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/gnu/kawa/xml/MakeAttribute.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */