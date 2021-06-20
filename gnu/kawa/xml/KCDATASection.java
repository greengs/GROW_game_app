package gnu.kawa.xml;

import gnu.xml.NodeTree;
import org.w3c.dom.CDATASection;

public class KCDATASection extends KText implements CDATASection {
  public KCDATASection(NodeTree paramNodeTree, int paramInt) {
    super(paramNodeTree, paramInt);
  }
  
  public String getData() {
    return getNodeValue();
  }
  
  public int getLength() {
    StringBuffer stringBuffer = new StringBuffer();
    NodeTree nodeTree = (NodeTree)this.sequence;
    nodeTree.stringValue(nodeTree.posToDataIndex(this.ipos), stringBuffer);
    return stringBuffer.length();
  }
  
  public String getNodeName() {
    return "#cdata-section";
  }
  
  public short getNodeType() {
    return 4;
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/gnu/kawa/xml/KCDATASection.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */