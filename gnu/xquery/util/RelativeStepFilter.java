package gnu.xquery.util;

import gnu.kawa.xml.SortedNodes;
import gnu.lists.AbstractSequence;
import gnu.lists.Consumer;
import gnu.lists.FilterConsumer;
import gnu.lists.PositionConsumer;
import gnu.lists.SeqPosition;

public class RelativeStepFilter extends FilterConsumer implements PositionConsumer {
  char seen;
  
  SortedNodes snodes;
  
  public RelativeStepFilter(Consumer paramConsumer) {
    super(paramConsumer);
  }
  
  protected void beforeContent() {
    if (this.seen == 'N')
      throw new Error("path returns mix of atoms and nodes"); 
    this.seen = 'A';
  }
  
  public void consume(SeqPosition paramSeqPosition) {
    writePosition(paramSeqPosition.sequence, paramSeqPosition.ipos);
  }
  
  public void finish() {
    if (this.snodes != null)
      this.snodes.consume(this.base); 
    this.snodes = null;
  }
  
  public void writeObject(Object paramObject) {
    if (paramObject instanceof SeqPosition) {
      paramObject = paramObject;
      writePosition(((SeqPosition)paramObject).sequence, ((SeqPosition)paramObject).ipos);
      return;
    } 
    super.writeObject(paramObject);
  }
  
  public void writePosition(AbstractSequence paramAbstractSequence, int paramInt) {
    if (this.seen == 'A')
      throw new Error("path returns mix of atoms and nodes"); 
    this.seen = 'N';
    if (this.snodes == null)
      this.snodes = new SortedNodes(); 
    this.snodes.writePosition(paramAbstractSequence, paramInt);
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/gnu/xquery/util/RelativeStepFilter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */