package gnu.kawa.xml;

import gnu.lists.AbstractSequence;
import gnu.lists.NodePredicate;
import gnu.lists.PositionConsumer;
import gnu.mapping.CallContext;
import gnu.mapping.MethodProc;
import gnu.mapping.Procedure;
import gnu.mapping.WrongType;
import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

public abstract class TreeScanner extends MethodProc implements Externalizable {
  public NodePredicate type;
  
  TreeScanner() {
    setProperty(Procedure.validateApplyKey, "gnu.kawa.xml.CompileXmlFunctions:validateApplyTreeScanner");
  }
  
  public void apply(CallContext paramCallContext) throws Throwable {
    PositionConsumer positionConsumer = (PositionConsumer)paramCallContext.consumer;
    Object object = paramCallContext.getNextArg();
    paramCallContext.lastArg();
    try {
      KNode kNode = (KNode)object;
      scan(kNode.sequence, kNode.getPos(), positionConsumer);
      return;
    } catch (ClassCastException classCastException) {
      throw new WrongType(getDesc(), -4, object, "node()");
    } 
  }
  
  public String getDesc() {
    String str2 = getClass().getName();
    int i = str2.lastIndexOf('.');
    String str1 = str2;
    if (i > 0)
      str1 = str2.substring(i + 1); 
    return str1 + "::" + this.type;
  }
  
  public NodePredicate getNodePredicate() {
    return this.type;
  }
  
  public int numArgs() {
    return 4097;
  }
  
  public void readExternal(ObjectInput paramObjectInput) throws IOException, ClassNotFoundException {
    this.type = (NodePredicate)paramObjectInput.readObject();
  }
  
  public abstract void scan(AbstractSequence paramAbstractSequence, int paramInt, PositionConsumer paramPositionConsumer);
  
  public String toString() {
    return "#<" + getClass().getName() + ' ' + this.type + '>';
  }
  
  public void writeExternal(ObjectOutput paramObjectOutput) throws IOException {
    paramObjectOutput.writeObject(this.type);
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/gnu/kawa/xml/TreeScanner.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */