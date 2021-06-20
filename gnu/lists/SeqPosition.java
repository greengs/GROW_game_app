package gnu.lists;

import java.util.Enumeration;
import java.util.ListIterator;
import java.util.NoSuchElementException;

public class SeqPosition implements ListIterator, Enumeration {
  public int ipos;
  
  public AbstractSequence sequence;
  
  public SeqPosition() {}
  
  public SeqPosition(AbstractSequence paramAbstractSequence) {
    this.sequence = paramAbstractSequence;
  }
  
  public SeqPosition(AbstractSequence paramAbstractSequence, int paramInt) {
    this.sequence = paramAbstractSequence;
    this.ipos = paramInt;
  }
  
  public SeqPosition(AbstractSequence paramAbstractSequence, int paramInt, boolean paramBoolean) {
    this.sequence = paramAbstractSequence;
    this.ipos = paramAbstractSequence.createPos(paramInt, paramBoolean);
  }
  
  public static SeqPosition make(AbstractSequence paramAbstractSequence, int paramInt) {
    return new SeqPosition(paramAbstractSequence, paramAbstractSequence.copyPos(paramInt));
  }
  
  public void add(Object paramObject) {
    setPos(this.sequence.addPos(getPos(), paramObject));
  }
  
  public SeqPosition copy() {
    return new SeqPosition(this.sequence, this.sequence.copyPos(getPos()));
  }
  
  public void finalize() {
    release();
  }
  
  public final int fromEndIndex() {
    return this.sequence.fromEndIndex(getPos());
  }
  
  public int getContainingSequenceSize() {
    return this.sequence.getContainingSequenceSize(getPos());
  }
  
  public Object getNext() {
    return this.sequence.getPosNext(getPos());
  }
  
  public int getNextKind() {
    return this.sequence.getNextKind(getPos());
  }
  
  public String getNextTypeName() {
    return this.sequence.getNextTypeName(getPos());
  }
  
  public Object getNextTypeObject() {
    return this.sequence.getNextTypeObject(getPos());
  }
  
  public int getPos() {
    return this.ipos;
  }
  
  public Object getPrevious() {
    return this.sequence.getPosPrevious(getPos());
  }
  
  public boolean gotoChildrenStart() {
    int i = this.sequence.firstChildPos(getPos());
    if (i == 0)
      return false; 
    this.ipos = i;
    return true;
  }
  
  public final void gotoEnd(AbstractSequence paramAbstractSequence) {
    setPos(paramAbstractSequence, paramAbstractSequence.endPos());
  }
  
  public boolean gotoNext() {
    int i = this.sequence.nextPos(this.ipos);
    if (i != 0) {
      this.ipos = i;
      return true;
    } 
    this.ipos = -1;
    return false;
  }
  
  public boolean gotoPrevious() {
    int i = this.sequence.previousPos(this.ipos);
    if (i != -1) {
      this.ipos = i;
      return true;
    } 
    this.ipos = 0;
    return false;
  }
  
  public final void gotoStart(AbstractSequence paramAbstractSequence) {
    setPos(paramAbstractSequence, paramAbstractSequence.startPos());
  }
  
  public final boolean hasMoreElements() {
    return hasNext();
  }
  
  public boolean hasNext() {
    return this.sequence.hasNext(getPos());
  }
  
  public boolean hasPrevious() {
    return this.sequence.hasPrevious(getPos());
  }
  
  public boolean isAfter() {
    return this.sequence.isAfterPos(getPos());
  }
  
  public Object next() {
    Object object = getNext();
    if (object == Sequence.eofValue || !gotoNext())
      throw new NoSuchElementException(); 
    return object;
  }
  
  public final Object nextElement() {
    return next();
  }
  
  public int nextIndex() {
    return this.sequence.nextIndex(getPos());
  }
  
  public Object previous() {
    Object object = getPrevious();
    if (object == Sequence.eofValue || !gotoPrevious())
      throw new NoSuchElementException(); 
    return object;
  }
  
  public final int previousIndex() {
    return this.sequence.nextIndex(getPos()) - 1;
  }
  
  public void release() {
    if (this.sequence != null) {
      this.sequence.releasePos(getPos());
      this.sequence = null;
    } 
  }
  
  public void remove() {
    boolean bool;
    AbstractSequence abstractSequence = this.sequence;
    int i = getPos();
    if (isAfter()) {
      bool = true;
    } else {
      bool = true;
    } 
    abstractSequence.removePos(i, bool);
  }
  
  public void set(AbstractSequence paramAbstractSequence, int paramInt, boolean paramBoolean) {
    if (this.sequence != null)
      this.sequence.releasePos(this.ipos); 
    this.sequence = paramAbstractSequence;
    this.ipos = paramAbstractSequence.createPos(paramInt, paramBoolean);
  }
  
  public void set(SeqPosition paramSeqPosition) {
    if (this.sequence != null)
      this.sequence.releasePos(this.ipos); 
    this.sequence = paramSeqPosition.sequence;
    paramSeqPosition.ipos = this.sequence.copyPos(paramSeqPosition.ipos);
  }
  
  public final void set(Object paramObject) {
    if (isAfter()) {
      setPrevious(paramObject);
      return;
    } 
    setNext(paramObject);
  }
  
  public void setNext(Object paramObject) {
    this.sequence.setPosNext(getPos(), paramObject);
  }
  
  public void setPos(int paramInt) {
    if (this.sequence != null)
      this.sequence.releasePos(getPos()); 
    this.ipos = paramInt;
  }
  
  public void setPos(AbstractSequence paramAbstractSequence, int paramInt) {
    if (this.sequence != null)
      this.sequence.releasePos(getPos()); 
    this.ipos = paramInt;
    this.sequence = paramAbstractSequence;
  }
  
  public void setPrevious(Object paramObject) {
    this.sequence.setPosPrevious(getPos(), paramObject);
  }
  
  public String toInfo() {
    StringBuffer stringBuffer = new StringBuffer(60);
    stringBuffer.append('{');
    if (this.sequence == null) {
      stringBuffer.append("null sequence");
      stringBuffer.append(" ipos: ");
      stringBuffer.append(this.ipos);
      stringBuffer.append('}');
      return stringBuffer.toString();
    } 
    stringBuffer.append(this.sequence.getClass().getName());
    stringBuffer.append('@');
    stringBuffer.append(System.identityHashCode(this.sequence));
    stringBuffer.append(" ipos: ");
    stringBuffer.append(this.ipos);
    stringBuffer.append('}');
    return stringBuffer.toString();
  }
  
  public String toString() {
    if (this.sequence == null)
      return toInfo(); 
    Object object = this.sequence.getPosNext(this.ipos);
    StringBuilder stringBuilder = (new StringBuilder()).append("@").append(nextIndex()).append(": ");
    if (object == null) {
      object = "(null)";
      return stringBuilder.append((String)object).toString();
    } 
    object = object.toString();
    return stringBuilder.append((String)object).toString();
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/gnu/lists/SeqPosition.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */