package gnu.kawa.xml;

import gnu.lists.AbstractSequence;
import gnu.lists.ItemPredicate;
import gnu.lists.NodePredicate;
import gnu.lists.PositionConsumer;

public class ChildAxis extends TreeScanner {
  public static ChildAxis make(NodePredicate paramNodePredicate) {
    ChildAxis childAxis = new ChildAxis();
    childAxis.type = paramNodePredicate;
    return childAxis;
  }
  
  public void scan(AbstractSequence paramAbstractSequence, int paramInt, PositionConsumer paramPositionConsumer) {
    for (paramInt = paramAbstractSequence.firstChildPos(paramInt, (ItemPredicate)this.type); paramInt != 0; paramInt = paramAbstractSequence.nextMatching(paramInt, (ItemPredicate)this.type, -1, false))
      paramPositionConsumer.writePosition(paramAbstractSequence, paramInt); 
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/gnu/kawa/xml/ChildAxis.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */