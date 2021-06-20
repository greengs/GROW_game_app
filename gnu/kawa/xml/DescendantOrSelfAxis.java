package gnu.kawa.xml;

import gnu.lists.AbstractSequence;
import gnu.lists.ItemPredicate;
import gnu.lists.NodePredicate;
import gnu.lists.PositionConsumer;

public class DescendantOrSelfAxis extends TreeScanner {
  public static final DescendantOrSelfAxis anyNode = new DescendantOrSelfAxis(NodeType.anyNodeTest);
  
  private DescendantOrSelfAxis(NodePredicate paramNodePredicate) {
    this.type = paramNodePredicate;
  }
  
  public static DescendantOrSelfAxis make(NodePredicate paramNodePredicate) {
    return (paramNodePredicate == NodeType.anyNodeTest) ? anyNode : new DescendantOrSelfAxis(paramNodePredicate);
  }
  
  public void scan(AbstractSequence paramAbstractSequence, int paramInt, PositionConsumer paramPositionConsumer) {
    if (this.type.isInstancePos(paramAbstractSequence, paramInt))
      paramPositionConsumer.writePosition(paramAbstractSequence, paramInt); 
    if (!(paramAbstractSequence instanceof gnu.lists.TreeList)) {
      for (paramInt = paramAbstractSequence.firstChildPos(paramInt); paramInt != 0; paramInt = paramAbstractSequence.nextPos(paramInt))
        scan(paramAbstractSequence, paramInt, paramPositionConsumer); 
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


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/gnu/kawa/xml/DescendantOrSelfAxis.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */