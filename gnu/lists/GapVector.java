package gnu.lists;

public class GapVector extends AbstractSequence implements Sequence {
  public SimpleVector base;
  
  public int gapEnd;
  
  public int gapStart;
  
  protected GapVector() {}
  
  public GapVector(SimpleVector paramSimpleVector) {
    this.base = paramSimpleVector;
    this.gapStart = 0;
    this.gapEnd = paramSimpleVector.size;
  }
  
  public void add(int paramInt, Object paramObject) {
    gapReserve(paramInt, 1);
    this.base.set(paramInt, paramObject);
    this.gapStart++;
  }
  
  protected int addPos(int paramInt, Object paramObject) {
    int i = paramInt >>> 1;
    paramInt = i;
    if (i >= this.gapStart)
      paramInt = i + this.gapEnd - this.gapStart; 
    add(paramInt, paramObject);
    return paramInt + 1 << 1 | 0x1;
  }
  
  public void consumePosRange(int paramInt1, int paramInt2, Consumer paramConsumer) {
    if (!paramConsumer.ignoring()) {
      int i = paramInt1 >>> 1;
      int j = paramInt2 >>> 1;
      if (i < this.gapStart) {
        int k;
        if (j > this.gapStart) {
          k = j;
        } else {
          k = this.gapStart;
        } 
        consumePosRange(paramInt1, k << 1, paramConsumer);
      } 
      if (j > this.gapEnd) {
        paramInt1 = i;
        if (i < this.gapEnd)
          paramInt1 = this.gapEnd; 
        consumePosRange(paramInt1 << 1, paramInt2, paramConsumer);
        return;
      } 
    } 
  }
  
  public int createPos(int paramInt, boolean paramBoolean) {
    int i = paramInt;
    if (paramInt > this.gapStart)
      i = paramInt + this.gapEnd - this.gapStart; 
    if (paramBoolean) {
      paramInt = 1;
      return paramInt | i << 1;
    } 
    paramInt = 0;
    return paramInt | i << 1;
  }
  
  public void fill(Object paramObject) {
    this.base.fill(this.gapEnd, this.base.size, paramObject);
    this.base.fill(0, this.gapStart, paramObject);
  }
  
  public void fillPosRange(int paramInt1, int paramInt2, Object paramObject) {
    int i;
    if (paramInt1 == -1) {
      i = this.base.size;
    } else {
      i = paramInt1 >>> 1;
    } 
    if (paramInt2 == -1) {
      paramInt1 = this.base.size;
    } else {
      paramInt1 = paramInt2 >>> 1;
    } 
    if (this.gapStart < paramInt1) {
      paramInt2 = this.gapStart;
    } else {
      paramInt2 = paramInt1;
    } 
    while (i < paramInt2) {
      this.base.setBuffer(i, paramObject);
      i++;
    } 
    for (paramInt2 = this.gapEnd; paramInt2 < paramInt1; paramInt2++)
      this.base.setBuffer(paramInt2, paramObject); 
  }
  
  protected final void gapReserve(int paramInt) {
    gapReserve(this.gapStart, paramInt);
  }
  
  protected void gapReserve(int paramInt1, int paramInt2) {
    int i = 16;
    if (paramInt2 > this.gapEnd - this.gapStart) {
      int j = this.base.size;
      if (j >= 16)
        i = j * 2; 
      int k = j - this.gapEnd - this.gapStart;
      j = k + paramInt2;
      paramInt2 = i;
      if (i < j)
        paramInt2 = j; 
      paramInt2 = paramInt2 - k + paramInt1;
      this.base.resizeShift(this.gapStart, this.gapEnd, paramInt1, paramInt2);
      this.gapStart = paramInt1;
      this.gapEnd = paramInt2;
      return;
    } 
    if (paramInt1 != this.gapStart) {
      shiftGap(paramInt1);
      return;
    } 
  }
  
  public Object get(int paramInt) {
    int i = paramInt;
    if (paramInt >= this.gapStart)
      i = paramInt + this.gapEnd - this.gapStart; 
    return this.base.get(i);
  }
  
  public int getNextKind(int paramInt) {
    return hasNext(paramInt) ? this.base.getElementKind() : 0;
  }
  
  public int getSegment(int paramInt1, int paramInt2) {
    int i;
    int j = size();
    if (paramInt1 < 0 || paramInt1 > j)
      return -1; 
    if (paramInt2 < 0) {
      i = 0;
    } else {
      i = paramInt2;
      if (paramInt1 + paramInt2 > j)
        i = j - paramInt1; 
    } 
    paramInt2 = paramInt1;
    if (paramInt1 + i > this.gapStart) {
      if (paramInt1 >= this.gapStart)
        return paramInt1 + this.gapEnd - this.gapStart; 
      if (this.gapStart - paramInt1 > i >> 1) {
        shiftGap(paramInt1 + i);
        return paramInt1;
      } 
      shiftGap(paramInt1);
      return paramInt1 + this.gapEnd - this.gapStart;
    } 
    return paramInt2;
  }
  
  public boolean hasNext(int paramInt) {
    int i = paramInt >>> 1;
    paramInt = i;
    if (i >= this.gapStart)
      paramInt = i + this.gapEnd - this.gapStart; 
    return (paramInt < this.base.size);
  }
  
  protected boolean isAfterPos(int paramInt) {
    return ((paramInt & 0x1) != 0);
  }
  
  protected int nextIndex(int paramInt) {
    if (paramInt == -1) {
      paramInt = this.base.size;
    } else {
      paramInt >>>= 1;
    } 
    int i = paramInt;
    if (paramInt > this.gapStart)
      i = paramInt - this.gapEnd - this.gapStart; 
    return i;
  }
  
  protected void removePosRange(int paramInt1, int paramInt2) {
    paramInt1 >>>= 1;
    paramInt2 >>>= 1;
    if (paramInt1 > this.gapEnd) {
      shiftGap(paramInt1 - this.gapEnd + this.gapStart);
    } else if (paramInt2 < this.gapStart) {
      shiftGap(paramInt2);
    } 
    if (paramInt1 < this.gapStart) {
      this.base.clearBuffer(paramInt1, this.gapStart - paramInt1);
      this.gapStart = paramInt1;
    } 
    if (paramInt2 > this.gapEnd) {
      this.base.clearBuffer(this.gapEnd, paramInt2 - this.gapEnd);
      this.gapEnd = paramInt2;
    } 
  }
  
  public Object set(int paramInt, Object paramObject) {
    int i = paramInt;
    if (paramInt >= this.gapStart)
      i = paramInt + this.gapEnd - this.gapStart; 
    return this.base.set(i, paramObject);
  }
  
  protected void shiftGap(int paramInt) {
    int i = paramInt - this.gapStart;
    if (i > 0) {
      this.base.shift(this.gapEnd, this.gapStart, i);
    } else if (i < 0) {
      this.base.shift(paramInt, this.gapEnd + i, -i);
    } 
    this.gapEnd += i;
    this.gapStart = paramInt;
  }
  
  public int size() {
    return this.base.size - this.gapEnd - this.gapStart;
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/gnu/lists/GapVector.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */