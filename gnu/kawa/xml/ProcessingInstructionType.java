package gnu.kawa.xml;

import gnu.bytecode.ClassType;
import gnu.bytecode.CodeAttr;
import gnu.bytecode.Method;
import gnu.bytecode.Type;
import gnu.bytecode.Variable;
import gnu.expr.Compilation;
import gnu.expr.TypeValue;
import gnu.lists.AbstractSequence;
import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

public class ProcessingInstructionType extends NodeType implements TypeValue, Externalizable {
  static final Method coerceMethod;
  
  static final Method coerceOrNullMethod;
  
  public static final ProcessingInstructionType piNodeTest = new ProcessingInstructionType(null);
  
  public static final ClassType typeProcessingInstructionType = ClassType.make("gnu.kawa.xml.ProcessingInstructionType");
  
  String target;
  
  static {
    coerceMethod = typeProcessingInstructionType.getDeclaredMethod("coerce", 2);
    coerceOrNullMethod = typeProcessingInstructionType.getDeclaredMethod("coerceOrNull", 2);
  }
  
  public ProcessingInstructionType(String paramString) {
    super(str);
    String str;
    this.target = paramString;
  }
  
  public static KProcessingInstruction coerce(Object paramObject, String paramString) {
    paramObject = coerceOrNull(paramObject, paramString);
    if (paramObject == null)
      throw new ClassCastException(); 
    return (KProcessingInstruction)paramObject;
  }
  
  public static KProcessingInstruction coerceOrNull(Object paramObject, String paramString) {
    paramObject = NodeType.coerceOrNull(paramObject, 32);
    return (KProcessingInstruction)((paramObject != null && (paramString == null || paramString.equals(paramObject.getTarget()))) ? paramObject : null);
  }
  
  public static ProcessingInstructionType getInstance(String paramString) {
    return (paramString == null) ? piNodeTest : new ProcessingInstructionType(paramString);
  }
  
  public Object coerceFromObject(Object paramObject) {
    return coerce(paramObject, this.target);
  }
  
  public void emitCoerceFromObject(CodeAttr paramCodeAttr) {
    paramCodeAttr.emitPushString(this.target);
    paramCodeAttr.emitInvokeStatic(coerceMethod);
  }
  
  protected void emitCoerceOrNullMethod(Variable paramVariable, Compilation paramCompilation) {
    CodeAttr codeAttr = paramCompilation.getCode();
    if (paramVariable != null)
      codeAttr.emitLoad(paramVariable); 
    codeAttr.emitPushString(this.target);
    codeAttr.emitInvokeStatic(coerceOrNullMethod);
  }
  
  public Type getImplementationType() {
    return (Type)ClassType.make("gnu.kawa.xml.KProcessingInstruction");
  }
  
  public boolean isInstance(Object paramObject) {
    return (coerceOrNull(paramObject, this.target) != null);
  }
  
  public boolean isInstancePos(AbstractSequence paramAbstractSequence, int paramInt) {
    boolean bool = false;
    int i = paramAbstractSequence.getNextKind(paramInt);
    if (i == 37) {
      if (this.target == null || this.target.equals(paramAbstractSequence.getNextTypeObject(paramInt)))
        bool = true; 
      return bool;
    } 
    return (i == 32) ? isInstance(paramAbstractSequence.getPosNext(paramInt)) : bool;
  }
  
  public void readExternal(ObjectInput paramObjectInput) throws IOException, ClassNotFoundException {
    this.target = (String)paramObjectInput.readObject();
  }
  
  public String toString() {
    StringBuilder stringBuilder = (new StringBuilder()).append("ProcessingInstructionType ");
    if (this.target == null) {
      String str1 = "*";
      return stringBuilder.append(str1).toString();
    } 
    String str = this.target;
    return stringBuilder.append(str).toString();
  }
  
  public void writeExternal(ObjectOutput paramObjectOutput) throws IOException {
    paramObjectOutput.writeObject(this.target);
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/gnu/kawa/xml/ProcessingInstructionType.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */