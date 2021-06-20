package gnu.kawa.xml;

import gnu.lists.AbstractSequence;
import gnu.lists.ItemPredicate;
import gnu.lists.NodePredicate;
import gnu.lists.PositionConsumer;

public class PrecedingAxis extends TreeScanner {
  public static PrecedingAxis make(NodePredicate paramNodePredicate) {
    PrecedingAxis precedingAxis = new PrecedingAxis();
    precedingAxis.type = paramNodePredicate;
    return precedingAxis;
  }
  
  private static void scan(AbstractSequence paramAbstractSequence, int paramInt1, int paramInt2, NodePredicate paramNodePredicate, PositionConsumer paramPositionConsumer) {
    int i = paramAbstractSequence.parentPos(paramInt1);
    if (i != paramInt2) {
      scan(paramAbstractSequence, i, paramInt2, paramNodePredicate, paramPositionConsumer);
      i = paramAbstractSequence.firstChildPos(i);
      if (i != 0) {
        paramInt2 = i;
        if (paramNodePredicate.isInstancePos(paramAbstractSequence, i)) {
          paramPositionConsumer.writePosition(paramAbstractSequence, i);
          paramInt2 = i;
        } 
        while (true) {
          paramInt2 = paramAbstractSequence.nextMatching(paramInt2, (ItemPredicate)paramNodePredicate, paramInt1, true);
          if (paramInt2 != 0) {
            paramPositionConsumer.writePosition(paramAbstractSequence, paramInt2);
            continue;
          } 
          return;
        } 
      } 
    } 
  }
  
  public void scan(AbstractSequence paramAbstractSequence, int paramInt, PositionConsumer paramPositionConsumer) {
    scan(paramAbstractSequence, paramInt, paramAbstractSequence.endPos(), this.type, paramPositionConsumer);
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/gnu/kawa/xml/PrecedingAxis.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */