package gnu.kawa.xml;

import gnu.xml.NodeTree;
import org.w3c.dom.Attr;
import org.w3c.dom.CDATASection;
import org.w3c.dom.Comment;
import org.w3c.dom.DOMConfiguration;
import org.w3c.dom.DOMException;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.DocumentFragment;
import org.w3c.dom.DocumentType;
import org.w3c.dom.Element;
import org.w3c.dom.EntityReference;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.ProcessingInstruction;
import org.w3c.dom.Text;

public class KDocument extends KNode implements Document {
  public KDocument(NodeTree paramNodeTree, int paramInt) {
    super(paramNodeTree, paramInt);
  }
  
  public Node adoptNode(Node paramNode) throws DOMException {
    throw new DOMException((short)9, "adoptNode not implemented");
  }
  
  public Attr createAttribute(String paramString) {
    throw new UnsupportedOperationException("createAttribute not implemented");
  }
  
  public Attr createAttributeNS(String paramString1, String paramString2) {
    throw new UnsupportedOperationException("createAttributeNS not implemented");
  }
  
  public CDATASection createCDATASection(String paramString) {
    throw new UnsupportedOperationException("createCDATASection not implemented");
  }
  
  public Comment createComment(String paramString) {
    throw new UnsupportedOperationException("createComment not implemented");
  }
  
  public DocumentFragment createDocumentFragment() {
    throw new UnsupportedOperationException("createDocumentFragment not implemented");
  }
  
  public Element createElement(String paramString) {
    throw new UnsupportedOperationException("createElement not implemented");
  }
  
  public Element createElementNS(String paramString1, String paramString2) {
    throw new UnsupportedOperationException("createElementNS not implemented");
  }
  
  public EntityReference createEntityReference(String paramString) {
    throw new UnsupportedOperationException("createEntityReference implemented");
  }
  
  public ProcessingInstruction createProcessingInstruction(String paramString1, String paramString2) {
    throw new UnsupportedOperationException("createProcessingInstruction not implemented");
  }
  
  public Text createTextNode(String paramString) {
    throw new UnsupportedOperationException("createTextNode not implemented");
  }
  
  public DocumentType getDoctype() {
    return null;
  }
  
  public KElement getDocumentElement() {
    for (int i = ((NodeTree)this.sequence).posFirstChild(this.ipos);; i = this.sequence.nextPos(i)) {
      if (i == -1)
        return null; 
      if (this.sequence.getNextKind(i) != 36)
        return (KElement)make((NodeTree)this.sequence, i); 
    } 
  }
  
  public String getDocumentURI() {
    return null;
  }
  
  public DOMConfiguration getDomConfig() {
    throw new DOMException((short)9, "getDomConfig not implemented");
  }
  
  public Element getElementById(String paramString) {
    return null;
  }
  
  public NodeList getElementsByTagNameNS(String paramString1, String paramString2) {
    throw new UnsupportedOperationException("getElementsByTagNameNS not implemented yet");
  }
  
  public DOMImplementation getImplementation() {
    throw new UnsupportedOperationException("getImplementation not implemented");
  }
  
  public String getInputEncoding() {
    return null;
  }
  
  public String getNodeName() {
    return "#document";
  }
  
  public short getNodeType() {
    return 9;
  }
  
  public String getNodeValue() {
    return null;
  }
  
  public Node getParentNode() {
    return null;
  }
  
  public boolean getStrictErrorChecking() {
    return false;
  }
  
  public String getTextContent() {
    return null;
  }
  
  protected void getTextContent(StringBuffer paramStringBuffer) {}
  
  public String getXmlEncoding() {
    return null;
  }
  
  public boolean getXmlStandalone() {
    return false;
  }
  
  public String getXmlVersion() {
    return "1.1";
  }
  
  public boolean hasAttributes() {
    return false;
  }
  
  public Node importNode(Node paramNode, boolean paramBoolean) {
    throw new UnsupportedOperationException("importNode not implemented");
  }
  
  public void normalizeDocument() {}
  
  public Node renameNode(Node paramNode, String paramString1, String paramString2) throws DOMException {
    throw new DOMException((short)9, "renameNode not implemented");
  }
  
  public void setDocumentURI(String paramString) {}
  
  public void setStrictErrorChecking(boolean paramBoolean) {}
  
  public void setXmlStandalone(boolean paramBoolean) {}
  
  public void setXmlVersion(String paramString) {}
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/gnu/kawa/xml/KDocument.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */