package gnu.kawa.xml;

import gnu.lists.AbstractSequence;
import gnu.lists.Consumable;
import gnu.lists.Consumer;
import gnu.lists.PositionConsumer;
import gnu.lists.SeqPosition;
import gnu.mapping.CharArrayOutPort;
import gnu.mapping.Namespace;
import gnu.mapping.Symbol;
import gnu.text.Path;
import gnu.xml.NodeTree;
import gnu.xml.XMLPrinter;
import java.io.Writer;
import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.UserDataHandler;

public abstract class KNode extends SeqPosition implements Node, Consumable {
  public KNode(NodeTree paramNodeTree, int paramInt) {
    super((AbstractSequence)paramNodeTree, paramInt);
  }
  
  public static Object atomicValue(Object paramObject) {
    Object object = paramObject;
    if (paramObject instanceof KNode) {
      paramObject = paramObject;
      object = ((NodeTree)((KNode)paramObject).sequence).typedValue(((KNode)paramObject).ipos);
    } 
    return object;
  }
  
  public static KNode coerce(Object paramObject) {
    if (paramObject instanceof KNode)
      return (KNode)paramObject; 
    if (paramObject instanceof NodeTree) {
      paramObject = paramObject;
      return make((NodeTree)paramObject, paramObject.startPos());
    } 
    if (paramObject instanceof SeqPosition && !(paramObject instanceof gnu.lists.TreePosition)) {
      paramObject = paramObject;
      if (((SeqPosition)paramObject).sequence instanceof NodeTree)
        return make((NodeTree)((SeqPosition)paramObject).sequence, ((SeqPosition)paramObject).ipos); 
    } 
    return null;
  }
  
  public static String getNodeValue(NodeTree paramNodeTree, int paramInt) {
    StringBuffer stringBuffer = new StringBuffer();
    getNodeValue(paramNodeTree, paramInt, stringBuffer);
    return stringBuffer.toString();
  }
  
  public static void getNodeValue(NodeTree paramNodeTree, int paramInt, StringBuffer paramStringBuffer) {
    paramNodeTree.stringValue(paramNodeTree.posToDataIndex(paramInt), paramStringBuffer);
  }
  
  public static KNode make(NodeTree paramNodeTree) {
    return make(paramNodeTree, 0);
  }
  
  public static KNode make(NodeTree paramNodeTree, int paramInt) {
    int j = paramNodeTree.posToDataIndex(paramInt);
    int i = paramInt;
    paramInt = j;
    while (paramInt < paramNodeTree.data.length && paramNodeTree.data[paramInt] == '') {
      i = paramInt + 5;
      paramInt = i;
      if (i == paramNodeTree.gapStart)
        paramInt = paramNodeTree.gapEnd; 
      i = paramInt << 1;
    } 
    switch (paramNodeTree.getNextKindI(paramNodeTree.posToDataIndex(i))) {
      default:
        return new KText(paramNodeTree, i);
      case 33:
        return new KElement(paramNodeTree, i);
      case 35:
        return new KAttr(paramNodeTree, i);
      case 34:
        return new KDocument(paramNodeTree, i);
      case 31:
        return new KCDATASection(paramNodeTree, i);
      case 36:
        return new KComment(paramNodeTree, i);
      case 37:
        return new KProcessingInstruction(paramNodeTree, i);
      case 0:
        break;
    } 
    if (!paramNodeTree.isEmpty())
      return null; 
  }
  
  public Node appendChild(Node paramNode) throws DOMException {
    throw new DOMException((short)7, "appendChild not supported");
  }
  
  public Path baseURI() {
    return ((NodeTree)this.sequence).baseUriOfPos(this.ipos, true);
  }
  
  public Node cloneNode(boolean paramBoolean) {
    if (!paramBoolean)
      throw new UnsupportedOperationException("shallow cloneNode not implemented"); 
    NodeTree nodeTree = new NodeTree();
    ((NodeTree)this.sequence).consumeNext(this.ipos, (Consumer)nodeTree);
    return make(nodeTree);
  }
  
  public short compareDocumentPosition(Node paramNode) throws DOMException {
    if (!(paramNode instanceof KNode))
      throw new DOMException((short)9, "other Node is a " + paramNode.getClass().getName()); 
    paramNode = paramNode;
    AbstractSequence abstractSequence = ((KNode)paramNode).sequence;
    if (this.sequence == abstractSequence) {
      int j = abstractSequence.compare(this.ipos, ((KNode)paramNode).ipos);
      return (short)j;
    } 
    int i = this.sequence.stableCompare(abstractSequence);
    return (short)i;
  }
  
  public void consume(Consumer paramConsumer) {
    if (paramConsumer instanceof PositionConsumer) {
      ((PositionConsumer)paramConsumer).consume(this);
      return;
    } 
    ((NodeTree)this.sequence).consumeNext(this.ipos, paramConsumer);
  }
  
  public KNode copy() {
    return make((NodeTree)this.sequence, this.sequence.copyPos(getPos()));
  }
  
  public NamedNodeMap getAttributes() {
    throw new UnsupportedOperationException("getAttributes not implemented yet");
  }
  
  public String getBaseURI() {
    Path path = ((NodeTree)this.sequence).baseUriOfPos(this.ipos, true);
    return (path == null) ? null : path.toString();
  }
  
  public NodeList getChildNodes() {
    SortedNodes sortedNodes = new SortedNodes();
    for (int i = this.sequence.firstChildPos(this.ipos); i != 0; i = this.sequence.nextPos(i))
      sortedNodes.writePosition(this.sequence, i); 
    return sortedNodes;
  }
  
  public NodeList getElementsByTagName(String paramString) {
    throw new UnsupportedOperationException("getElementsByTagName not implemented yet");
  }
  
  public Object getFeature(String paramString1, String paramString2) {
    return null;
  }
  
  public Node getFirstChild() {
    int i = ((NodeTree)this.sequence).posFirstChild(this.ipos);
    return make((NodeTree)this.sequence, i);
  }
  
  public Node getLastChild() {
    int j = 0;
    for (int i = this.sequence.firstChildPos(this.ipos); i != 0; i = this.sequence.nextPos(i))
      j = i; 
    return (j == 0) ? null : make((NodeTree)this.sequence, j);
  }
  
  public String getLocalName() {
    return ((NodeTree)this.sequence).posLocalName(this.ipos);
  }
  
  public String getNamespaceURI() {
    return ((NodeTree)this.sequence).posNamespaceURI(this.ipos);
  }
  
  public Node getNextSibling() {
    int i = ((NodeTree)this.sequence).nextPos(this.ipos);
    return (i == 0) ? null : make((NodeTree)this.sequence, i);
  }
  
  public String getNodeName() {
    return this.sequence.getNextTypeName(this.ipos);
  }
  
  public Object getNodeNameObject() {
    return ((NodeTree)this.sequence).getNextTypeObject(this.ipos);
  }
  
  public Symbol getNodeSymbol() {
    Object object = ((NodeTree)this.sequence).getNextTypeObject(this.ipos);
    return (object == null) ? null : ((object instanceof Symbol) ? (Symbol)object : Namespace.EmptyNamespace.getSymbol(object.toString().intern()));
  }
  
  public abstract short getNodeType();
  
  public String getNodeValue() {
    StringBuffer stringBuffer = new StringBuffer();
    getNodeValue(stringBuffer);
    return stringBuffer.toString();
  }
  
  public void getNodeValue(StringBuffer paramStringBuffer) {
    getNodeValue((NodeTree)this.sequence, this.ipos, paramStringBuffer);
  }
  
  public Document getOwnerDocument() {
    return (this.sequence.getNextKind(this.ipos) == 34) ? new KDocument((NodeTree)this.sequence, 0) : null;
  }
  
  public Node getParentNode() {
    int i = this.sequence.parentPos(this.ipos);
    return (i == -1) ? null : make((NodeTree)this.sequence, i);
  }
  
  public String getPrefix() {
    return ((NodeTree)this.sequence).posPrefix(this.ipos);
  }
  
  public Node getPreviousSibling() {
    int j = this.sequence.parentPos(this.ipos);
    int i = j;
    if (j == -1)
      i = 0; 
    int k = ((NodeTree)this.sequence).posToDataIndex(this.ipos);
    i = this.sequence.firstChildPos(i);
    while (true) {
      j = i;
      int m = this.sequence.nextPos(j);
      if (m != 0) {
        i = m;
        if (((NodeTree)this.sequence).posToDataIndex(m) == k)
          return (j == 0) ? null : make((NodeTree)this.sequence, j); 
        continue;
      } 
      return (j == 0) ? null : make((NodeTree)this.sequence, j);
    } 
  }
  
  public String getTextContent() {
    StringBuffer stringBuffer = new StringBuffer();
    getTextContent(stringBuffer);
    return stringBuffer.toString();
  }
  
  protected void getTextContent(StringBuffer paramStringBuffer) {
    getNodeValue(paramStringBuffer);
  }
  
  public Object getUserData(String paramString) {
    return null;
  }
  
  public boolean hasAttributes() {
    return false;
  }
  
  public boolean hasChildNodes() {
    return (((NodeTree)this.sequence).posFirstChild(this.ipos) >= 0);
  }
  
  public Node insertBefore(Node paramNode1, Node paramNode2) throws DOMException {
    throw new DOMException((short)7, "insertBefore not supported");
  }
  
  public boolean isDefaultNamespace(String paramString) {
    return ((NodeTree)this.sequence).posIsDefaultNamespace(this.ipos, paramString);
  }
  
  public boolean isEqualNode(Node paramNode) {
    throw new UnsupportedOperationException("getAttributesisEqualNode not implemented yet");
  }
  
  public boolean isSameNode(Node paramNode) {
    if (paramNode instanceof KNode) {
      paramNode = paramNode;
      if (this.sequence == ((KNode)paramNode).sequence)
        return this.sequence.equals(this.ipos, ((KNode)paramNode).ipos); 
    } 
    return false;
  }
  
  public boolean isSupported(String paramString1, String paramString2) {
    return false;
  }
  
  public String lookupNamespaceURI(String paramString) {
    return ((NodeTree)this.sequence).posLookupNamespaceURI(this.ipos, paramString);
  }
  
  public String lookupPrefix(String paramString) {
    return ((NodeTree)this.sequence).posLookupPrefix(this.ipos, paramString);
  }
  
  public void normalize() {}
  
  public Node removeChild(Node paramNode) throws DOMException {
    throw new DOMException((short)7, "removeChild not supported");
  }
  
  public Node replaceChild(Node paramNode1, Node paramNode2) throws DOMException {
    throw new DOMException((short)7, "replaceChild not supported");
  }
  
  public void setNodeValue(String paramString) throws DOMException {
    throw new DOMException((short)7, "setNodeValue not supported");
  }
  
  public void setPrefix(String paramString) throws DOMException {
    throw new DOMException((short)7, "setPrefix not supported");
  }
  
  public void setTextContent(String paramString) throws DOMException {
    throw new DOMException((short)7, "setTextContent not supported");
  }
  
  public Object setUserData(String paramString, Object paramObject, UserDataHandler paramUserDataHandler) {
    throw new UnsupportedOperationException("setUserData not implemented yet");
  }
  
  public String toString() {
    CharArrayOutPort charArrayOutPort = new CharArrayOutPort();
    XMLPrinter xMLPrinter = new XMLPrinter((Writer)charArrayOutPort);
    ((NodeTree)this.sequence).consumeNext(this.ipos, (Consumer)xMLPrinter);
    xMLPrinter.close();
    charArrayOutPort.close();
    return charArrayOutPort.toString();
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/gnu/kawa/xml/KNode.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */