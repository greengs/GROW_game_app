package gnu.kawa.xml;

import gnu.bytecode.ClassType;
import gnu.bytecode.CodeAttr;
import gnu.bytecode.Method;
import gnu.bytecode.ObjectType;
import gnu.bytecode.Type;
import gnu.bytecode.Variable;
import gnu.expr.ApplyExp;
import gnu.expr.Compilation;
import gnu.expr.ConditionalTarget;
import gnu.expr.Declaration;
import gnu.expr.Expression;
import gnu.expr.Target;
import gnu.expr.TypeValue;
import gnu.kawa.reflect.InstanceOf;
import gnu.lists.AbstractSequence;
import gnu.lists.NodePredicate;
import gnu.mapping.Procedure;
import gnu.xml.NodeTree;
import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

public class NodeType extends ObjectType implements TypeValue, NodePredicate, Externalizable {
  public static final int ATTRIBUTE_OK = 4;
  
  public static final int COMMENT_OK = 16;
  
  public static final int DOCUMENT_OK = 8;
  
  public static final int ELEMENT_OK = 2;
  
  public static final int PI_OK = 32;
  
  public static final int TEXT_OK = 1;
  
  public static final NodeType anyNodeTest;
  
  static final Method coerceMethod;
  
  static final Method coerceOrNullMethod;
  
  public static final NodeType commentNodeTest;
  
  public static final NodeType documentNodeTest;
  
  public static final NodeType nodeType;
  
  public static final NodeType textNodeTest;
  
  public static final ClassType typeKNode = ClassType.make("gnu.kawa.xml.KNode");
  
  public static final ClassType typeNodeType = ClassType.make("gnu.kawa.xml.NodeType");
  
  int kinds = -1;
  
  static {
    nodeType = new NodeType("gnu.kawa.xml.KNode");
    coerceMethod = typeNodeType.getDeclaredMethod("coerceForce", 2);
    coerceOrNullMethod = typeNodeType.getDeclaredMethod("coerceOrNull", 2);
    documentNodeTest = new NodeType("document-node", 8);
    textNodeTest = new NodeType("text", 1);
    commentNodeTest = new NodeType("comment", 16);
    anyNodeTest = new NodeType("node");
  }
  
  public NodeType(String paramString) {
    this(paramString, -1);
  }
  
  public NodeType(String paramString, int paramInt) {
    super(paramString);
    this.kinds = paramInt;
  }
  
  public static KNode coerceForce(Object paramObject, int paramInt) {
    KNode kNode = coerceOrNull(paramObject, paramInt);
    if (kNode == null)
      throw new ClassCastException("coerce from " + paramObject.getClass()); 
    return kNode;
  }
  
  public static KNode coerceOrNull(Object paramObject, int paramInt) {
    KNode kNode = null;
    if (paramObject instanceof NodeTree) {
      paramObject = KNode.make((NodeTree)paramObject);
    } else if (paramObject instanceof KNode) {
      paramObject = paramObject;
    } else {
      return kNode;
    } 
    if (!isInstance(((KNode)paramObject).sequence, ((KNode)paramObject).ipos, paramInt))
      paramObject = null; 
    return (KNode)paramObject;
  }
  
  public static boolean isInstance(AbstractSequence paramAbstractSequence, int paramInt1, int paramInt2) {
    paramInt1 = paramAbstractSequence.getNextKind(paramInt1);
    if (paramInt2 < 0)
      return !(paramInt1 == 0); 
    switch (paramInt1) {
      default:
        return true;
      case 0:
        return false;
      case 17:
      case 18:
      case 19:
      case 20:
      case 21:
      case 22:
      case 23:
      case 24:
      case 25:
      case 26:
      case 27:
      case 28:
      case 29:
      case 32:
        return !((paramInt2 & 0x1) == 0);
      case 33:
        return !((paramInt2 & 0x2) == 0);
      case 35:
        return !((paramInt2 & 0x4) == 0);
      case 34:
        return !((paramInt2 & 0x8) == 0);
      case 36:
        return !((paramInt2 & 0x10) == 0);
      case 37:
        break;
    } 
    return !((paramInt2 & 0x20) == 0);
  }
  
  public Object coerceFromObject(Object paramObject) {
    return coerceForce(paramObject, this.kinds);
  }
  
  public int compare(Type paramType) {
    return getImplementationType().compare(paramType);
  }
  
  public Expression convertValue(Expression paramExpression) {
    ApplyExp applyExp = new ApplyExp(coerceMethod, new Expression[] { paramExpression });
    applyExp.setType((Type)this);
    return (Expression)applyExp;
  }
  
  public void emitCoerceFromObject(CodeAttr paramCodeAttr) {
    paramCodeAttr.emitPushInt(this.kinds);
    paramCodeAttr.emitInvokeStatic(coerceMethod);
  }
  
  protected void emitCoerceOrNullMethod(Variable paramVariable, Compilation paramCompilation) {
    CodeAttr codeAttr = paramCompilation.getCode();
    if (paramVariable != null)
      codeAttr.emitLoad(paramVariable); 
    codeAttr.emitPushInt(this.kinds);
    codeAttr.emitInvokeStatic(coerceOrNullMethod);
  }
  
  public void emitIsInstance(Variable paramVariable, Compilation paramCompilation, Target paramTarget) {
    CodeAttr codeAttr;
    ConditionalTarget conditionalTarget;
    if (paramTarget instanceof ConditionalTarget) {
      conditionalTarget = (ConditionalTarget)paramTarget;
      emitCoerceOrNullMethod(paramVariable, paramCompilation);
      codeAttr = paramCompilation.getCode();
      if (conditionalTarget.trueBranchComesFirst) {
        codeAttr.emitGotoIfCompare1(conditionalTarget.ifFalse, 198);
      } else {
        codeAttr.emitGotoIfCompare1(conditionalTarget.ifTrue, 199);
      } 
      conditionalTarget.emitGotoFirstBranch(codeAttr);
      return;
    } 
    InstanceOf.emitIsInstance(this, (Variable)codeAttr, paramCompilation, (Target)conditionalTarget);
  }
  
  public void emitTestIf(Variable paramVariable, Declaration paramDeclaration, Compilation paramCompilation) {
    CodeAttr codeAttr = paramCompilation.getCode();
    emitCoerceOrNullMethod(paramVariable, paramCompilation);
    if (paramDeclaration != null) {
      codeAttr.emitDup();
      paramDeclaration.compileStore(paramCompilation);
    } 
    codeAttr.emitIfNotNull();
  }
  
  public Procedure getConstructor() {
    return null;
  }
  
  public Type getImplementationType() {
    return (Type)typeKNode;
  }
  
  public boolean isInstance(Object paramObject) {
    if (paramObject instanceof KNode) {
      paramObject = paramObject;
      return isInstancePos(((KNode)paramObject).sequence, paramObject.getPos());
    } 
    return false;
  }
  
  public boolean isInstancePos(AbstractSequence paramAbstractSequence, int paramInt) {
    return isInstance(paramAbstractSequence, paramInt, this.kinds);
  }
  
  public void readExternal(ObjectInput paramObjectInput) throws IOException, ClassNotFoundException {
    String str = paramObjectInput.readUTF();
    if (str.length() > 0)
      setName(str); 
    this.kinds = paramObjectInput.readInt();
  }
  
  public String toString() {
    return "NodeType " + getName();
  }
  
  public void writeExternal(ObjectOutput paramObjectOutput) throws IOException {
    String str2 = getName();
    String str1 = str2;
    if (str2 == null)
      str1 = ""; 
    paramObjectOutput.writeUTF(str1);
    paramObjectOutput.writeInt(this.kinds);
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/gnu/kawa/xml/NodeType.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */