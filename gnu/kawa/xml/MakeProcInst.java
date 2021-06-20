package gnu.kawa.xml;

import gnu.bytecode.ClassType;
import gnu.bytecode.CodeAttr;
import gnu.expr.ApplyExp;
import gnu.expr.Compilation;
import gnu.expr.ConsumerTarget;
import gnu.expr.Expression;
import gnu.expr.Target;
import gnu.lists.Consumer;
import gnu.lists.XConsumer;
import gnu.mapping.CallContext;
import gnu.mapping.Values;
import gnu.xml.TextUtils;
import gnu.xml.XMLFilter;

public class MakeProcInst extends NodeConstructor {
  public static final MakeProcInst makeProcInst = new MakeProcInst();
  
  public static void procInst$C(Object paramObject1, Object paramObject2, Consumer paramConsumer) {
    paramObject1 = KNode.atomicValue(paramObject1);
    if (!(paramObject1 instanceof String) && !(paramObject1 instanceof UntypedAtomic))
      throw new ClassCastException("invalid type of processing-instruction target [XPTY0004]"); 
    if (!(paramConsumer instanceof XConsumer))
      return; 
    StringBuffer stringBuffer = new StringBuffer();
    if (paramObject2 instanceof Values) {
      paramObject2 = ((Values)paramObject2).getValues();
      for (int k = 0; k < paramObject2.length; k++) {
        if (k > 0)
          stringBuffer.append(' '); 
        TextUtils.stringValue(paramObject2[k], stringBuffer);
      } 
    } else {
      TextUtils.stringValue(paramObject2, stringBuffer);
    } 
    int j = stringBuffer.length();
    int i;
    for (i = 0; i < j && Character.isWhitespace(stringBuffer.charAt(i)); i++);
    paramObject2 = new char[j - i];
    stringBuffer.getChars(i, j, (char[])paramObject2, 0);
    ((XConsumer)paramConsumer).writeProcessingInstruction(paramObject1.toString(), (char[])paramObject2, 0, paramObject2.length);
  }
  
  public static void procInst$X(Object paramObject1, Object paramObject2, CallContext paramCallContext) {
    Consumer consumer = paramCallContext.consumer;
    XMLFilter xMLFilter = NodeConstructor.pushNodeContext(paramCallContext);
    try {
      procInst$C(paramObject1, paramObject2, (Consumer)xMLFilter);
      return;
    } finally {
      NodeConstructor.popNodeContext(consumer, paramCallContext);
    } 
  }
  
  public void apply(CallContext paramCallContext) {
    procInst$X(paramCallContext.getNextArg(null), paramCallContext.getNextArg(null), paramCallContext);
  }
  
  public void compileToNode(ApplyExp paramApplyExp, Compilation paramCompilation, ConsumerTarget paramConsumerTarget) {
    CodeAttr codeAttr = paramCompilation.getCode();
    Expression[] arrayOfExpression = paramApplyExp.getArgs();
    arrayOfExpression[0].compile(paramCompilation, Target.pushObject);
    arrayOfExpression[1].compile(paramCompilation, Target.pushObject);
    codeAttr.emitLoad(paramConsumerTarget.getConsumerVariable());
    codeAttr.emitInvokeStatic(ClassType.make("gnu.kawa.xml.MakeProcInst").getDeclaredMethod("procInst$C", 3));
  }
  
  public int numArgs() {
    return 8194;
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/gnu/kawa/xml/MakeProcInst.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */