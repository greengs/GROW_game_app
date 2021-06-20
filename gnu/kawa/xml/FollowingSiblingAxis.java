package gnu.kawa.xml;

import gnu.lists.AbstractSequence;
import gnu.lists.ItemPredicate;
import gnu.lists.NodePredicate;
import gnu.lists.PositionConsumer;

public class FollowingSiblingAxis extends TreeScanner {
  public static FollowingSiblingAxis make(NodePredicate paramNodePredicate) {
    FollowingSiblingAxis followingSiblingAxis = new FollowingSiblingAxis();
    followingSiblingAxis.type = paramNodePredicate;
    return followingSiblingAxis;
  }
  
  public void scan(AbstractSequence paramAbstractSequence, int paramInt, PositionConsumer paramPositionConsumer) {
    int i = paramAbstractSequence.endPos();
    while (true) {
      paramInt = paramAbstractSequence.nextMatching(paramInt, (ItemPredicate)this.type, i, false);
      if (paramInt == 0)
        return; 
      paramPositionConsumer.writePosition(paramAbstractSequence, paramInt);
    } 
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/gnu/kawa/xml/FollowingSiblingAxis.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */