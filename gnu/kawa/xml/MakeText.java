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
import gnu.expr.Target;
import gnu.lists.Consumer;
import gnu.mapping.CallContext;
import gnu.mapping.Values;
import gnu.xml.NodeTree;
import gnu.xml.TextUtils;
import gnu.xml.XMLFilter;

public class MakeText extends NodeConstructor {
  public static final MakeText makeText = new MakeText();
  
  public static void text$X(Object paramObject, CallContext paramCallContext) {
    if (paramObject == null || (paramObject instanceof Values && ((Values)paramObject).isEmpty()))
      return; 
    Consumer consumer = paramCallContext.consumer;
    XMLFilter xMLFilter = NodeConstructor.pushNodeContext(paramCallContext);
    try {
      TextUtils.textValue(paramObject, (Consumer)xMLFilter);
      return;
    } finally {
      NodeConstructor.popNodeContext(consumer, paramCallContext);
    } 
  }
  
  public void apply(CallContext paramCallContext) {
    text$X(paramCallContext.getNextArg(null), paramCallContext);
  }
  
  public Object apply1(Object paramObject) {
    if (paramObject == null || (paramObject instanceof Values && ((Values)paramObject).isEmpty()))
      return paramObject; 
    NodeTree nodeTree = new NodeTree();
    TextUtils.textValue(paramObject, (Consumer)new XMLFilter((Consumer)nodeTree));
    return KText.make(nodeTree);
  }
  
  public void compile(ApplyExp paramApplyExp, Compilation paramCompilation, Target paramTarget) {
    ApplyExp.compile(paramApplyExp, paramCompilation, paramTarget);
  }
  
  public void compileToNode(ApplyExp paramApplyExp, Compilation paramCompilation, ConsumerTarget paramConsumerTarget) {
    String str;
    Method method;
    CodeAttr codeAttr = paramCompilation.getCode();
    Expression expression = paramApplyExp.getArgs()[0];
    Variable variable = paramConsumerTarget.getConsumerVariable();
    if (expression instanceof QuoteExp) {
      Object object = ((QuoteExp)expression).getValue();
      if (object instanceof String) {
        str = (String)object;
        object = CodeAttr.calculateSplit(str);
        int k = object.length();
        method = ((ClassType)variable.getType()).getMethod("write", new Type[] { (Type)Type.string_type });
        int j = 0;
        int i;
        for (i = 0; i < k; i++) {
          codeAttr.emitLoad(variable);
          int m = j + object.charAt(i);
          codeAttr.emitPushString(str.substring(j, m));
          codeAttr.emitInvoke(method);
          j = m;
        } 
        return;
      } 
    } 
    method.compile((Compilation)str, Target.pushObject);
    codeAttr.emitLoad(variable);
    codeAttr.emitInvokeStatic(ClassType.make("gnu.xml.TextUtils").getDeclaredMethod("textValue", 2));
  }
  
  public int numArgs() {
    return 4097;
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/gnu/kawa/xml/MakeText.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */