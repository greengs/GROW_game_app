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
import gnu.expr.Inlineable;
import gnu.expr.QuoteExp;
import gnu.expr.Target;
import gnu.lists.Consumer;
import gnu.mapping.CallContext;
import gnu.mapping.MethodProc;
import gnu.xml.NodeTree;
import gnu.xml.XMLFilter;

public abstract class NodeConstructor extends MethodProc implements Inlineable {
  static final Method popNodeConsumerMethod;
  
  static final Method popNodeContextMethod;
  
  static final Method pushNodeConsumerMethod;
  
  static final Method pushNodeContextMethod;
  
  static final ClassType typeKNode;
  
  static final ClassType typeNodeConstructor;
  
  static final ClassType typeXMLFilter = ClassType.make("gnu.xml.XMLFilter");
  
  static {
    typeKNode = ClassType.make("gnu.kawa.xml.KNode");
    typeNodeConstructor = ClassType.make("gnu.kawa.xml.NodeConstructor");
    pushNodeContextMethod = typeNodeConstructor.getDeclaredMethod("pushNodeContext", 1);
    popNodeContextMethod = typeNodeConstructor.getDeclaredMethod("popNodeContext", 2);
    pushNodeConsumerMethod = typeNodeConstructor.getDeclaredMethod("pushNodeConsumer", 1);
    popNodeConsumerMethod = typeNodeConstructor.getDeclaredMethod("popNodeConsumer", 2);
  }
  
  public static void compileChild(Expression paramExpression, Compilation paramCompilation, ConsumerTarget paramConsumerTarget) {
    if (paramExpression instanceof ApplyExp) {
      ApplyExp applyExp = (ApplyExp)paramExpression;
      Expression expression = applyExp.getFunction();
      if (expression instanceof QuoteExp) {
        Object object = ((QuoteExp)expression).getValue();
        if (object instanceof NodeConstructor) {
          ((NodeConstructor)object).compileToNode(applyExp, paramCompilation, paramConsumerTarget);
          return;
        } 
      } 
    } 
    paramExpression.compileWithPosition(paramCompilation, (Target)paramConsumerTarget);
  }
  
  public static void compileUsingNodeTree(Expression paramExpression, Compilation paramCompilation, Target paramTarget) {
    ConsumerTarget.compileUsingConsumer(paramExpression, paramCompilation, paramTarget, typeNodeConstructor.getDeclaredMethod("makeNode", 0), typeNodeConstructor.getDeclaredMethod("finishNode", 1));
  }
  
  public static KNode finishNode(XMLFilter paramXMLFilter) {
    return KNode.make((NodeTree)paramXMLFilter.out);
  }
  
  public static XMLFilter makeNode() {
    return new XMLFilter((Consumer)new NodeTree());
  }
  
  public static void popNodeConsumer(Consumer paramConsumer1, Consumer paramConsumer2) {
    if (paramConsumer1 != paramConsumer2) {
      KNode kNode;
      Consumer consumer = paramConsumer2;
      if (paramConsumer2 instanceof XMLFilter)
        kNode = KNode.make((NodeTree)((XMLFilter)paramConsumer2).out); 
      paramConsumer1.writeObject(kNode);
    } 
  }
  
  public static void popNodeContext(Consumer paramConsumer, CallContext paramCallContext) {
    Consumer consumer = paramCallContext.consumer;
    if (paramConsumer != consumer) {
      KNode kNode;
      Consumer consumer1 = consumer;
      if (consumer instanceof XMLFilter)
        kNode = KNode.make((NodeTree)((XMLFilter)consumer).out); 
      paramConsumer.writeObject(kNode);
      paramCallContext.consumer = paramConsumer;
    } 
  }
  
  public static XMLFilter pushNodeConsumer(Consumer paramConsumer) {
    return (paramConsumer instanceof XMLFilter) ? (XMLFilter)paramConsumer : new XMLFilter((Consumer)new NodeTree());
  }
  
  public static XMLFilter pushNodeContext(CallContext paramCallContext) {
    Consumer consumer = paramCallContext.consumer;
    if (consumer instanceof XMLFilter)
      return (XMLFilter)consumer; 
    XMLFilter xMLFilter = new XMLFilter((Consumer)new NodeTree());
    paramCallContext.consumer = (Consumer)xMLFilter;
    return xMLFilter;
  }
  
  public void compile(ApplyExp paramApplyExp, Compilation paramCompilation, Target paramTarget) {
    if (paramTarget instanceof gnu.expr.IgnoreTarget) {
      ApplyExp.compile(paramApplyExp, paramCompilation, paramTarget);
      return;
    } 
    if (!(paramTarget instanceof ConsumerTarget)) {
      compileUsingNodeTree((Expression)paramApplyExp, paramCompilation, paramTarget);
      return;
    } 
    ConsumerTarget consumerTarget = (ConsumerTarget)paramTarget;
    Variable variable1 = consumerTarget.getConsumerVariable();
    if (variable1.getType().isSubtype((Type)typeXMLFilter)) {
      compileToNode(paramApplyExp, paramCompilation, consumerTarget);
      return;
    } 
    int i = (paramApplyExp.getArgs()).length;
    CodeAttr codeAttr = paramCompilation.getCode();
    Variable variable2 = codeAttr.pushScope().addVariable(codeAttr, (Type)typeXMLFilter, null);
    if (consumerTarget.isContextTarget()) {
      paramCompilation.loadCallContext();
      codeAttr.emitInvokeStatic(pushNodeContextMethod);
    } else {
      codeAttr.emitLoad(variable1);
      codeAttr.emitInvokeStatic(pushNodeConsumerMethod);
    } 
    codeAttr.emitStore(variable2);
    codeAttr.emitTryStart(true, (Type)Type.void_type);
    compileToNode(paramApplyExp, paramCompilation, new ConsumerTarget(variable2));
    codeAttr.emitTryEnd();
    codeAttr.emitFinallyStart();
    codeAttr.emitLoad(variable1);
    if (consumerTarget.isContextTarget()) {
      paramCompilation.loadCallContext();
      codeAttr.emitInvokeStatic(popNodeContextMethod);
    } else {
      codeAttr.emitLoad(variable2);
      codeAttr.emitInvokeStatic(popNodeConsumerMethod);
    } 
    codeAttr.emitFinallyEnd();
    codeAttr.emitTryCatchEnd();
    codeAttr.popScope();
  }
  
  public abstract void compileToNode(ApplyExp paramApplyExp, Compilation paramCompilation, ConsumerTarget paramConsumerTarget);
  
  public Type getReturnType(Expression[] paramArrayOfExpression) {
    return (Type)Compilation.typeObject;
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/gnu/kawa/xml/NodeConstructor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */