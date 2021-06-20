package gnu.kawa.xml;

import gnu.xml.NodeTree;
import org.w3c.dom.CharacterData;
import org.w3c.dom.DOMException;

public abstract class KCharacterData extends KNode implements CharacterData {
  public KCharacterData(NodeTree paramNodeTree, int paramInt) {
    super(paramNodeTree, paramInt);
  }
  
  public void appendData(String paramString) throws DOMException {
    throw new DOMException((short)7, "appendData not supported");
  }
  
  public void deleteData(int paramInt1, int paramInt2) throws DOMException {
    replaceData(paramInt1, paramInt2, "");
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
  
  public void insertData(int paramInt, String paramString) throws DOMException {
    replaceData(paramInt, 0, paramString);
  }
  
  public void replaceData(int paramInt1, int paramInt2, String paramString) throws DOMException {
    throw new DOMException((short)7, "replaceData not supported");
  }
  
  public void setData(String paramString) throws DOMException {
    throw new DOMException((short)7, "setData not supported");
  }
  
  public String substringData(int paramInt1, int paramInt2) throws DOMException {
    String str = getData();
    if (paramInt1 < 0 || paramInt2 < 0 || paramInt1 + paramInt2 >= str.length())
      throw new DOMException((short)1, "invalid index to substringData"); 
    return str.substring(paramInt1, paramInt2);
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/gnu/kawa/xml/KCharacterData.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */