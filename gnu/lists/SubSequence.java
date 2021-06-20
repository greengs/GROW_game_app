package gnu.lists;

public class SubSequence extends AbstractSequence implements Sequence {
  AbstractSequence base;
  
  int ipos0;
  
  int ipos1;
  
  public SubSequence() {}
  
  public SubSequence(AbstractSequence paramAbstractSequence) {
    this.base = paramAbstractSequence;
  }
  
  public SubSequence(AbstractSequence paramAbstractSequence, int paramInt1, int paramInt2) {
    this.base = paramAbstractSequence;
    this.ipos0 = paramInt1;
    this.ipos1 = paramInt2;
  }
  
  public void clear() {
    removePosRange(this.ipos0, this.ipos1);
  }
  
  public int compare(int paramInt1, int paramInt2) {
    return this.base.compare(paramInt1, paramInt2);
  }
  
  public int createPos(int paramInt, boolean paramBoolean) {
    return this.base.createRelativePos(this.ipos0, paramInt, paramBoolean);
  }
  
  public int createRelativePos(int paramInt1, int paramInt2, boolean paramBoolean) {
    return this.base.createRelativePos(paramInt1, paramInt2, paramBoolean);
  }
  
  public int endPos() {
    return this.ipos1;
  }
  
  public void finalize() {
    this.base.releasePos(this.ipos0);
    this.base.releasePos(this.ipos1);
  }
  
  public Object get(int paramInt) {
    if (paramInt < 0 || paramInt >= size())
      throw new IndexOutOfBoundsException(); 
    int i = this.base.nextIndex(this.ipos0);
    return this.base.get(i + paramInt);
  }
  
  protected int getIndexDifference(int paramInt1, int paramInt2) {
    return this.base.getIndexDifference(paramInt1, paramInt2);
  }
  
  public int getNextKind(int paramInt) {
    return (this.base.compare(paramInt, this.ipos1) >= 0) ? 0 : this.base.getNextKind(paramInt);
  }
  
  public Object getPosNext(int paramInt) {
    return (this.base.compare(paramInt, this.ipos1) >= 0) ? eofValue : this.base.getPosNext(paramInt);
  }
  
  public Object getPosPrevious(int paramInt) {
    return (this.base.compare(paramInt, this.ipos0) <= 0) ? eofValue : this.base.getPosPrevious(paramInt);
  }
  
  protected boolean isAfterPos(int paramInt) {
    return this.base.isAfterPos(paramInt);
  }
  
  protected int nextIndex(int paramInt) {
    return getIndexDifference(paramInt, this.ipos0);
  }
  
  public void releasePos(int paramInt) {
    this.base.releasePos(paramInt);
  }
  
  public void removePosRange(int paramInt1, int paramInt2) {
    int i;
    AbstractSequence abstractSequence = this.base;
    if (paramInt1 == 0) {
      i = this.ipos0;
    } else {
      i = paramInt1;
      if (paramInt1 == -1)
        i = this.ipos1; 
    } 
    if (paramInt2 == -1) {
      paramInt1 = this.ipos1;
    } else {
      paramInt1 = paramInt2;
      if (paramInt2 == 0)
        paramInt1 = this.ipos0; 
    } 
    abstractSequence.removePosRange(i, paramInt1);
  }
  
  public int size() {
    return this.base.getIndexDifference(this.ipos1, this.ipos0);
  }
  
  public int startPos() {
    return this.ipos0;
  }
  
  protected Sequence subSequencePos(int paramInt1, int paramInt2) {
    return new SubSequence(this.base, paramInt1, paramInt2);
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/gnu/lists/SubSequence.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */