package gnu.lists;

class LListPosition extends ExtPosition {
  Object xpos;
  
  public LListPosition(LList paramLList, int paramInt, boolean paramBoolean) {
    set(paramLList, paramInt, paramBoolean);
  }
  
  public LListPosition(LListPosition paramLListPosition) {
    this.sequence = paramLListPosition.sequence;
    this.ipos = paramLListPosition.ipos;
    this.xpos = paramLListPosition.xpos;
  }
  
  public SeqPosition copy() {
    return new LListPosition(this);
  }
  
  public Object getNext() {
    Pair pair = getNextPair();
    return (pair == null) ? LList.eofValue : pair.car;
  }
  
  public Pair getNextPair() {
    Object object;
    if ((this.ipos & 0x1) > 0) {
      if (this.xpos == null) {
        AbstractSequence abstractSequence = this.sequence;
        object = abstractSequence;
        if (this.ipos >> 1 != 0)
          Object object1 = ((Pair)abstractSequence).cdr; 
      } else {
        object = ((Pair)((Pair)this.xpos).cdr).cdr;
      } 
    } else if (this.xpos == null) {
      object = this.sequence;
    } else {
      object = ((Pair)this.xpos).cdr;
    } 
    return (object == LList.Empty) ? null : (Pair)object;
  }
  
  public Object getPrevious() {
    Pair pair = getPreviousPair();
    return (pair == null) ? LList.eofValue : pair.car;
  }
  
  public Pair getPreviousPair() {
    Object object1;
    int i = this.ipos;
    Object object2 = this.xpos;
    if ((i & 0x1) > 0) {
      if (object2 == null) {
        object1 = this.sequence;
      } else {
        object1 = ((Pair)object2).cdr;
      } 
    } else {
      object1 = object2;
      if (object2 == null)
        return null; 
    } 
    return (object1 == LList.Empty) ? null : (Pair)object1;
  }
  
  public boolean gotoNext() {
    boolean bool;
    if ((this.ipos & 0x1) != 0) {
      bool = true;
    } else {
      bool = false;
    } 
    int i = this.ipos;
    Object object = this.xpos;
    if (object != null) {
      Object object1 = object;
      if (bool)
        object1 = ((Pair)object).cdr; 
      if (((Pair)object1).cdr == LList.Empty)
        return false; 
      this.xpos = object1;
      this.ipos = (this.ipos | 0x1) + 2;
      return true;
    } 
    if (this.ipos >> 1 == 0) {
      if (this.sequence != LList.Empty) {
        this.ipos = 3;
        return true;
      } 
      return false;
    } 
    AbstractSequence abstractSequence = this.sequence;
    if (((Pair)abstractSequence).cdr != LList.Empty) {
      this.ipos = 5;
      this.xpos = abstractSequence;
      return true;
    } 
    return false;
  }
  
  public boolean gotoPrevious() {
    if (this.ipos >>> 1 == 0)
      return false; 
    if ((this.ipos & 0x1) != 0) {
      this.ipos -= 3;
      return true;
    } 
    int i = nextIndex();
    set((LList)this.sequence, i - 1, false);
    return true;
  }
  
  public boolean hasNext() {
    boolean bool = true;
    if (this.xpos == null)
      return (this.ipos >> 1 == 0) ? ((this.sequence != LList.Empty)) : ((((Pair)this.sequence).cdr == LList.Empty) ? false : bool); 
    Object object2 = ((Pair)this.xpos).cdr;
    Object object1 = object2;
    if ((this.ipos & 0x1) > 0)
      object1 = ((Pair)object2).cdr; 
    return (object1 == LList.Empty) ? false : bool;
  }
  
  public boolean hasPrevious() {
    return (this.ipos >>> 1 != 0);
  }
  
  public int nextIndex() {
    return this.ipos >> 1;
  }
  
  public void set(AbstractSequence paramAbstractSequence, int paramInt, boolean paramBoolean) {
    set((LList)paramAbstractSequence, paramInt, paramBoolean);
  }
  
  public void set(LList paramLList, int paramInt, boolean paramBoolean) {
    boolean bool;
    this.sequence = paramLList;
    if (paramBoolean) {
      bool = true;
    } else {
      bool = false;
    } 
    this.ipos = bool | paramInt << 1;
    if (paramBoolean) {
      paramInt -= 2;
    } else {
      paramInt--;
    } 
    if (paramInt >= 0)
      while (true) {
        Object object;
        if (--paramInt >= 0) {
          object = ((Pair)paramLList).cdr;
          continue;
        } 
        this.xpos = object;
        return;
      }  
    this.xpos = null;
  }
  
  public void setNext(Object paramObject) {
    (getNextPair()).car = paramObject;
  }
  
  public void setPrevious(Object paramObject) {
    (getPreviousPair()).car = paramObject;
  }
  
  public String toString() {
    StringBuffer stringBuffer = new StringBuffer();
    stringBuffer.append("LListPos[");
    stringBuffer.append("index:");
    stringBuffer.append(this.ipos);
    if (isAfter())
      stringBuffer.append(" after"); 
    if (this.position >= 0) {
      stringBuffer.append(" position:");
      stringBuffer.append(this.position);
    } 
    stringBuffer.append(']');
    return stringBuffer.toString();
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/gnu/lists/LListPosition.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */