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
import gnu.mapping.Symbol;
import gnu.xml.NamespaceBinding;
import gnu.xml.XMLFilter;
import gnu.xml.XName;

public class MakeElement extends NodeConstructor {
  static final Method endElementMethod;
  
  public static final MakeElement makeElement = new MakeElement();
  
  static final Method startElementMethod3;
  
  static final Method startElementMethod4;
  
  static final ClassType typeMakeElement = ClassType.make("gnu.kawa.xml.MakeElement");
  
  public int copyNamespacesMode = 1;
  
  private boolean handlingKeywordParameters;
  
  NamespaceBinding namespaceNodes;
  
  public Symbol tag;
  
  static {
    startElementMethod3 = typeMakeElement.getDeclaredMethod("startElement", 3);
    startElementMethod4 = typeMakeElement.getDeclaredMethod("startElement", 4);
    endElementMethod = typeMakeElement.getDeclaredMethod("endElement", 2);
  }
  
  public static void endElement(Consumer paramConsumer, Object paramObject) {
    paramConsumer.endElement();
  }
  
  public static Symbol getTagName(ApplyExp paramApplyExp) {
    Expression[] arrayOfExpression = paramApplyExp.getArgs();
    if (arrayOfExpression.length > 0) {
      Expression expression = arrayOfExpression[0];
      if (expression instanceof QuoteExp) {
        Object object = ((QuoteExp)expression).getValue();
        if (object instanceof Symbol)
          return (Symbol)object; 
      } 
    } 
    return null;
  }
  
  public static void startElement(Consumer paramConsumer, Object paramObject, int paramInt) {
    if (paramObject instanceof Symbol) {
      paramObject = paramObject;
    } else {
      paramObject = Symbol.make("", paramObject.toString(), "");
    } 
    if (paramConsumer instanceof XMLFilter)
      ((XMLFilter)paramConsumer).copyNamespacesMode = paramInt; 
    paramConsumer.startElement(paramObject);
  }
  
  public static void startElement(Consumer paramConsumer, Object paramObject, int paramInt, NamespaceBinding paramNamespaceBinding) {
    if (paramObject instanceof Symbol) {
      paramObject = new XName((Symbol)paramObject, paramNamespaceBinding);
    } else {
      paramObject = new XName(Symbol.make("", paramObject.toString(), ""), paramNamespaceBinding);
    } 
    if (paramConsumer instanceof XMLFilter)
      ((XMLFilter)paramConsumer).copyNamespacesMode = paramInt; 
    paramConsumer.startElement(paramObject);
  }
  
  public void apply(CallContext paramCallContext) {
    Consumer consumer = paramCallContext.consumer;
    XMLFilter xMLFilter = pushNodeContext(paramCallContext);
    try {
      Object object;
      if (this.tag != null) {
        object = this.tag;
      } else {
        object = paramCallContext.getNextArg();
      } 
      if (this.namespaceNodes != null) {
        startElement((Consumer)xMLFilter, object, this.copyNamespacesMode, this.namespaceNodes);
      } else {
        startElement((Consumer)xMLFilter, object, this.copyNamespacesMode);
      } 
      Special special = Special.dfault;
      while (true) {
        Object object1 = paramCallContext.getNextArg(special);
        if (object1 == special) {
          endElement((Consumer)xMLFilter, object);
          return;
        } 
        if (object1 instanceof Consumable) {
          ((Consumable)object1).consume((Consumer)xMLFilter);
        } else {
          paramCallContext.writeValue(object1);
        } 
        if (isHandlingKeywordParameters())
          xMLFilter.endAttribute(); 
      } 
    } finally {
      popNodeContext(consumer, paramCallContext);
    } 
  }
  
  public void compileToNode(ApplyExp paramApplyExp, Compilation paramCompilation, ConsumerTarget paramConsumerTarget) {
    int i;
    Variable variable = paramConsumerTarget.getConsumerVariable();
    Expression[] arrayOfExpression = paramApplyExp.getArgs();
    int j = arrayOfExpression.length;
    CodeAttr codeAttr = paramCompilation.getCode();
    codeAttr.emitLoad(variable);
    codeAttr.emitDup();
    if (this.tag == null) {
      arrayOfExpression[0].compile(paramCompilation, Target.pushObject);
      i = 1;
    } else {
      paramCompilation.compileConstant(this.tag, Target.pushObject);
      i = 0;
    } 
    codeAttr.emitDup(1, 1);
    codeAttr.emitPushInt(this.copyNamespacesMode);
    if (this.namespaceNodes != null) {
      paramCompilation.compileConstant(this.namespaceNodes, Target.pushObject);
      codeAttr.emitInvokeStatic(startElementMethod4);
    } else {
      codeAttr.emitInvokeStatic(startElementMethod3);
    } 
    while (i < j) {
      compileChild(arrayOfExpression[i], paramCompilation, paramConsumerTarget);
      if (isHandlingKeywordParameters()) {
        codeAttr.emitLoad(variable);
        codeAttr.emitInvokeInterface(MakeAttribute.endAttributeMethod);
      } 
      i++;
    } 
    codeAttr.emitInvokeStatic(endElementMethod);
  }
  
  public NamespaceBinding getNamespaceNodes() {
    return this.namespaceNodes;
  }
  
  public Type getReturnType(Expression[] paramArrayOfExpression) {
    return (Type)Compilation.typeObject;
  }
  
  public boolean isHandlingKeywordParameters() {
    return this.handlingKeywordParameters;
  }
  
  public int numArgs() {
    return (this.tag == null) ? -4095 : -4096;
  }
  
  public void setHandlingKeywordParameters(boolean paramBoolean) {
    this.handlingKeywordParameters = paramBoolean;
  }
  
  public void setNamespaceNodes(NamespaceBinding paramNamespaceBinding) {
    this.namespaceNodes = paramNamespaceBinding;
  }
  
  public String toString() {
    return "makeElement[" + this.tag + "]";
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/gnu/kawa/xml/MakeElement.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */