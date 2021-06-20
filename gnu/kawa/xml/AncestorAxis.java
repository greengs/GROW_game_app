package gnu.kawa.xml;

import gnu.lists.AbstractSequence;
import gnu.lists.NodePredicate;
import gnu.lists.PositionConsumer;

public class AncestorAxis extends TreeScanner {
  public static AncestorAxis make(NodePredicate paramNodePredicate) {
    AncestorAxis ancestorAxis = new AncestorAxis();
    ancestorAxis.type = paramNodePredicate;
    return ancestorAxis;
  }
  
  private static void scan(AbstractSequence paramAbstractSequence, int paramInt1, int paramInt2, NodePredicate paramNodePredicate, PositionConsumer paramPositionConsumer) {
    paramInt1 = paramAbstractSequence.parentPos(paramInt1);
    if (paramInt1 != paramInt2) {
      scan(paramAbstractSequence, paramInt1, paramInt2, paramNodePredicate, paramPositionConsumer);
      if (paramNodePredicate.isInstancePos(paramAbstractSequence, paramInt1))
        paramPositionConsumer.writePosition(paramAbstractSequence, paramInt1); 
    } 
  }
  
  public void scan(AbstractSequence paramAbstractSequence, int paramInt, PositionConsumer paramPositionConsumer) {
    scan(paramAbstractSequence, paramInt, paramAbstractSequence.endPos(), this.type, paramPositionConsumer);
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/gnu/kawa/xml/AncestorAxis.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */