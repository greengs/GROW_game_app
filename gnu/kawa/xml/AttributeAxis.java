package gnu.kawa.xml;

import gnu.lists.AbstractSequence;
import gnu.lists.NodePredicate;
import gnu.lists.PositionConsumer;

public class AttributeAxis extends TreeScanner {
  public static AttributeAxis make(NodePredicate paramNodePredicate) {
    AttributeAxis attributeAxis = new AttributeAxis();
    attributeAxis.type = paramNodePredicate;
    return attributeAxis;
  }
  
  public void scan(AbstractSequence paramAbstractSequence, int paramInt, PositionConsumer paramPositionConsumer) {
    for (paramInt = paramAbstractSequence.firstAttributePos(paramInt); paramInt != 0 && paramAbstractSequence.getNextKind(paramInt) == 35; paramInt = paramAbstractSequence.nextPos(paramInt)) {
      if (this.type.isInstancePos(paramAbstractSequence, paramInt)) {
        paramPositionConsumer.writePosition(paramAbstractSequence, paramInt);
      } else if (paramAbstractSequence.getNextKind(paramInt) != 35) {
        break;
      } 
    } 
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/gnu/kawa/xml/AttributeAxis.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */