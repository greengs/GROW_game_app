package gnu.kawa.swingviews;

import gnu.lists.AbstractSequence;
import gnu.lists.CharBuffer;
import gnu.lists.SeqPosition;
import javax.swing.text.Position;

class GapPosition extends SeqPosition implements Position {
  public GapPosition(CharBuffer paramCharBuffer, int paramInt) {
    super((AbstractSequence)paramCharBuffer, paramInt, bool);
  }
  
  public int getOffset() {
    return nextIndex();
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/gnu/kawa/swingviews/GapPosition.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */