package gnu.kawa.xml;

import gnu.xml.NodeTree;
import org.w3c.dom.Attr;
import org.w3c.dom.DOMException;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.w3c.dom.TypeInfo;

public class KElement extends KNode implements Element {
  public KElement(NodeTree paramNodeTree, int paramInt) {
    super(paramNodeTree, paramInt);
  }
  
  public String getAttribute(String paramString) {
    String str = paramString;
    if (paramString == null)
      str = ""; 
    NodeTree nodeTree = (NodeTree)this.sequence;
    int i = nodeTree.getAttribute(this.ipos, null, str);
    return (i == 0) ? "" : KNode.getNodeValue(nodeTree, i);
  }
  
  public String getAttributeNS(String paramString1, String paramString2) {
    String str = paramString1;
    if (paramString1 == null)
      str = ""; 
    paramString1 = paramString2;
    if (paramString2 == null)
      paramString1 = ""; 
    NodeTree nodeTree = (NodeTree)this.sequence;
    int i = nodeTree.getAttribute(this.ipos, str, paramString1);
    return (i == 0) ? "" : getNodeValue(nodeTree, i);
  }
  
  public KAttr getAttributeNode(String paramString) {
    String str = paramString;
    if (paramString == null)
      str = ""; 
    NodeTree nodeTree = (NodeTree)this.sequence;
    int i = nodeTree.getAttribute(this.ipos, null, str);
    return (i == 0) ? null : new KAttr(nodeTree, i);
  }
  
  public KAttr getAttributeNodeNS(String paramString1, String paramString2) {
    String str = paramString1;
    if (paramString1 == null)
      str = ""; 
    paramString1 = paramString2;
    if (paramString2 == null)
      paramString1 = ""; 
    NodeTree nodeTree = (NodeTree)this.sequence;
    int i = nodeTree.getAttribute(this.ipos, str, paramString1);
    return (i == 0) ? null : new KAttr(nodeTree, i);
  }
  
  public NodeList getElementsByTagNameNS(String paramString1, String paramString2) {
    throw new UnsupportedOperationException("getElementsByTagNameNS not implemented yet");
  }
  
  public short getNodeType() {
    return 1;
  }
  
  public String getNodeValue() {
    return null;
  }
  
  public TypeInfo getSchemaTypeInfo() {
    return null;
  }
  
  public String getTagName() {
    return this.sequence.getNextTypeName(this.ipos);
  }
  
  public boolean hasAttribute(String paramString) {
    NodeTree nodeTree = (NodeTree)this.sequence;
    int i = this.ipos;
    String str = paramString;
    if (paramString == null)
      str = ""; 
    return (nodeTree.getAttribute(i, null, str) != 0);
  }
  
  public boolean hasAttributeNS(String paramString1, String paramString2) {
    String str = paramString1;
    if (paramString1 == null)
      str = ""; 
    paramString1 = paramString2;
    if (paramString2 == null)
      paramString1 = ""; 
    return (((NodeTree)this.sequence).getAttribute(this.ipos, str, paramString1) != 0);
  }
  
  public boolean hasAttributes() {
    return ((NodeTree)this.sequence).posHasAttributes(this.ipos);
  }
  
  public void removeAttribute(String paramString) throws DOMException {
    throw new DOMException((short)7, "removeAttribute not supported");
  }
  
  public void removeAttributeNS(String paramString1, String paramString2) throws DOMException {
    throw new DOMException((short)7, "removeAttributeNS not supported");
  }
  
  public Attr removeAttributeNode(Attr paramAttr) throws DOMException {
    throw new DOMException((short)7, "removeAttributeNode not supported");
  }
  
  public void setAttribute(String paramString1, String paramString2) throws DOMException {
    throw new DOMException((short)7, "setAttribute not supported");
  }
  
  public void setAttributeNS(String paramString1, String paramString2, String paramString3) throws DOMException {
    throw new DOMException((short)7, "setAttributeNS not supported");
  }
  
  public Attr setAttributeNode(Attr paramAttr) throws DOMException {
    throw new DOMException((short)7, "setAttributeNode not supported");
  }
  
  public Attr setAttributeNodeNS(Attr paramAttr) throws DOMException {
    throw new DOMException((short)7, "setAttributeNodeNS not supported");
  }
  
  public void setIdAttribute(String paramString, boolean paramBoolean) throws DOMException {
    throw new DOMException((short)7, "setIdAttribute not supported");
  }
  
  public void setIdAttributeNS(String paramString1, String paramString2, boolean paramBoolean) throws DOMException {
    throw new DOMException((short)7, "setIdAttributeNS not supported");
  }
  
  public void setIdAttributeNode(Attr paramAttr, boolean paramBoolean) throws DOMException {
    throw new DOMException((short)7, "setIdAttributeNode not supported");
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/gnu/kawa/xml/KElement.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */