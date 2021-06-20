package gnu.kawa.xml;

import gnu.xml.NodeTree;
import org.w3c.dom.Comment;

public class KComment extends KCharacterData implements Comment {
  public KComment(NodeTree paramNodeTree, int paramInt) {
    super(paramNodeTree, paramInt);
  }
  
  public static KComment valueOf(String paramString) {
    NodeTree nodeTree = new NodeTree();
    nodeTree.writeComment(paramString, 0, paramString.length());
    return new KComment(nodeTree, 0);
  }
  
  public String getNodeName() {
    return "#comment";
  }
  
  public short getNodeType() {
    return 8;
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/gnu/kawa/xml/KComment.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */