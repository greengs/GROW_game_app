package gnu.kawa.xml;

import gnu.lists.AbstractSequence;
import gnu.lists.ItemPredicate;
import gnu.lists.NodePredicate;
import gnu.lists.PositionConsumer;

public class FollowingAxis extends TreeScanner {
  public static FollowingAxis make(NodePredicate paramNodePredicate) {
    FollowingAxis followingAxis = new FollowingAxis();
    followingAxis.type = paramNodePredicate;
    return followingAxis;
  }
  
  public void scan(AbstractSequence paramAbstractSequence, int paramInt, PositionConsumer paramPositionConsumer) {
    int j = paramAbstractSequence.endPos();
    int i = paramAbstractSequence.nextPos(paramInt);
    paramInt = i;
    if (i != 0) {
      paramInt = i;
      if (this.type.isInstancePos(paramAbstractSequence, i)) {
        paramPositionConsumer.writePosition(paramAbstractSequence, i);
        paramInt = i;
      } 
    } 
    while (true) {
      paramInt = paramAbstractSequence.nextMatching(paramInt, (ItemPredicate)this.type, j, true);
      if (paramInt == 0)
        return; 
      paramPositionConsumer.writePosition(paramAbstractSequence, paramInt);
    } 
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/gnu/kawa/xml/FollowingAxis.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */