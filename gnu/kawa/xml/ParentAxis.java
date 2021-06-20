package gnu.kawa.xml;

import gnu.lists.AbstractSequence;
import gnu.lists.NodePredicate;
import gnu.lists.PositionConsumer;

public class ParentAxis extends TreeScanner {
  public static ParentAxis make(NodePredicate paramNodePredicate) {
    ParentAxis parentAxis = new ParentAxis();
    parentAxis.type = paramNodePredicate;
    return parentAxis;
  }
  
  public void scan(AbstractSequence paramAbstractSequence, int paramInt, PositionConsumer paramPositionConsumer) {
    paramInt = paramAbstractSequence.parentPos(paramInt);
    if (paramInt != paramAbstractSequence.endPos() && this.type.isInstancePos(paramAbstractSequence, paramInt))
      paramPositionConsumer.writePosition(paramAbstractSequence, paramInt); 
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/gnu/kawa/xml/ParentAxis.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */