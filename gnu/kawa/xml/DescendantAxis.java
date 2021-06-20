package gnu.kawa.xml;

import gnu.lists.AbstractSequence;
import gnu.lists.ItemPredicate;
import gnu.lists.NodePredicate;
import gnu.lists.PositionConsumer;

public class DescendantAxis extends TreeScanner {
  public static DescendantAxis make(NodePredicate paramNodePredicate) {
    DescendantAxis descendantAxis = new DescendantAxis();
    descendantAxis.type = paramNodePredicate;
    return descendantAxis;
  }
  
  public void scan(AbstractSequence paramAbstractSequence, int paramInt, PositionConsumer paramPositionConsumer) {
    if (!(paramAbstractSequence instanceof gnu.lists.TreeList)) {
      for (paramInt = paramAbstractSequence.firstChildPos(paramInt); paramInt != 0; paramInt = paramAbstractSequence.nextPos(paramInt)) {
        if (this.type.isInstancePos(paramAbstractSequence, paramInt))
          paramPositionConsumer.writePosition(paramAbstractSequence, paramInt); 
        scan(paramAbstractSequence, paramInt, paramPositionConsumer);
      } 
    } else {
      int i = paramAbstractSequence.nextPos(paramInt);
      while (true) {
        paramInt = paramAbstractSequence.nextMatching(paramInt, (ItemPredicate)this.type, i, true);
        if (paramInt != 0) {
          paramPositionConsumer.writePosition(paramAbstractSequence, paramInt);
          continue;
        } 
        return;
      } 
    } 
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/gnu/kawa/xml/DescendantAxis.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */