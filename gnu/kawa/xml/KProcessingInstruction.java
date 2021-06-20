package gnu.kawa.xml;

import gnu.xml.NodeTree;
import org.w3c.dom.DOMException;
import org.w3c.dom.ProcessingInstruction;

public class KProcessingInstruction extends KNode implements ProcessingInstruction {
  public KProcessingInstruction(NodeTree paramNodeTree, int paramInt) {
    super(paramNodeTree, paramInt);
  }
  
  public static KProcessingInstruction valueOf(String paramString1, String paramString2) {
    NodeTree nodeTree = new NodeTree();
    nodeTree.writeProcessingInstruction(paramString1, paramString2, 0, paramString2.length());
    return new KProcessingInstruction(nodeTree, 0);
  }
  
  public String getData() {
    return getNodeValue();
  }
  
  public String getNodeName() {
    return getTarget();
  }
  
  public short getNodeType() {
    return 7;
  }
  
  public String getTarget() {
    return ((NodeTree)this.sequence).posTarget(this.ipos);
  }
  
  public void setData(String paramString) throws DOMException {
    throw new DOMException((short)7, "setData not supported");
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/gnu/kawa/xml/KProcessingInstruction.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */