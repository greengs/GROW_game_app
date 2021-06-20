package gnu.kawa.xml;

import gnu.xml.NodeTree;
import org.w3c.dom.DOMException;
import org.w3c.dom.Text;

public class KText extends KCharacterData implements Text {
  public KText(NodeTree paramNodeTree, int paramInt) {
    super(paramNodeTree, paramInt);
  }
  
  public static KText make(String paramString) {
    NodeTree nodeTree = new NodeTree();
    nodeTree.append(paramString);
    return new KText(nodeTree, 0);
  }
  
  public String getNodeName() {
    return "#text";
  }
  
  public short getNodeType() {
    return 3;
  }
  
  public String getWholeText() {
    throw new UnsupportedOperationException("getWholeText not implemented yet");
  }
  
  public boolean hasAttributes() {
    return false;
  }
  
  public boolean isElementContentWhitespace() {
    return false;
  }
  
  public Text replaceWholeText(String paramString) throws DOMException {
    throw new DOMException((short)7, "splitText not supported");
  }
  
  public Text splitText(int paramInt) throws DOMException {
    throw new DOMException((short)7, "splitText not supported");
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/gnu/kawa/xml/KText.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */