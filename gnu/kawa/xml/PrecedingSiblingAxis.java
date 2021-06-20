package gnu.kawa.xml;

import gnu.lists.AbstractSequence;
import gnu.lists.ItemPredicate;
import gnu.lists.NodePredicate;
import gnu.lists.PositionConsumer;

public class PrecedingSiblingAxis extends TreeScanner {
  public static PrecedingSiblingAxis make(NodePredicate paramNodePredicate) {
    PrecedingSiblingAxis precedingSiblingAxis = new PrecedingSiblingAxis();
    precedingSiblingAxis.type = paramNodePredicate;
    return precedingSiblingAxis;
  }
  
  public void scan(AbstractSequence paramAbstractSequence, int paramInt, PositionConsumer paramPositionConsumer) {
    int i = paramAbstractSequence.endPos();
    int j = paramAbstractSequence.parentPos(paramInt);
    if (j != i) {
      j = paramAbstractSequence.firstChildPos(j);
      if (j != 0) {
        i = j;
        if (this.type.isInstancePos(paramAbstractSequence, j)) {
          paramPositionConsumer.writePosition(paramAbstractSequence, j);
          i = j;
        } 
        while (true) {
          i = paramAbstractSequence.nextMatching(i, (ItemPredicate)this.type, paramInt, false);
          if (i != 0) {
            paramPositionConsumer.writePosition(paramAbstractSequence, i);
            continue;
          } 
          return;
        } 
      } 
    } 
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/gnu/kawa/xml/PrecedingSiblingAxis.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */