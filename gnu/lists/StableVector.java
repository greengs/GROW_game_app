package gnu.lists;

public class StableVector extends GapVector {
  static final int END_POSITION = 1;
  
  protected static final int FREE_POSITION = -2;
  
  static final int START_POSITION = 0;
  
  protected int free;
  
  protected int[] positions;
  
  protected StableVector() {}
  
  public StableVector(SimpleVector paramSimpleVector) {
    super(paramSimpleVector);
    this.positions = new int[16];
    this.positions[0] = 0;
    this.positions[1] = paramSimpleVector.getBufferLength() << 1 | 0x1;
    this.free = -1;
    int i = this.positions.length;
    while (true) {
      if (--i > 1) {
        this.positions[i] = this.free;
        this.free = i;
        continue;
      } 
      break;
    } 
  }
  
  protected int addPos(int paramInt, Object paramObject) {
    int k = this.positions[paramInt];
    int j = k >>> 1;
    int i = j;
    if (j >= this.gapStart)
      i = j + this.gapEnd - this.gapStart; 
    j = paramInt;
    if ((k & 0x1) == 0) {
      if (paramInt == 0) {
        j = createPos(0, true);
        add(i, paramObject);
        return j;
      } 
    } else {
      add(i, paramObject);
      return j;
    } 
    this.positions[paramInt] = k | 0x1;
    j = paramInt;
    add(i, paramObject);
    return j;
  }
  
  protected void adjustPositions(int paramInt1, int paramInt2, int paramInt3) {
    if (this.free >= -1)
      unchainFreelist(); 
    int i = this.positions.length;
    while (true) {
      int j = i - 1;
      if (j > 0) {
        int k = this.positions[j];
        i = j;
        if (k != -2) {
          int m = k ^ Integer.MIN_VALUE;
          i = j;
          if (m >= (paramInt1 ^ Integer.MIN_VALUE)) {
            i = j;
            if (m <= (paramInt2 ^ Integer.MIN_VALUE)) {
              this.positions[j] = k + paramInt3;
              i = j;
            } 
          } 
        } 
        continue;
      } 
      break;
    } 
  }
  
  protected int allocPositionIndex() {
    if (this.free == -2)
      chainFreelist(); 
    if (this.free < 0) {
      int k = this.positions.length;
      int[] arrayOfInt = new int[k * 2];
      System.arraycopy(this.positions, 0, arrayOfInt, 0, k);
      int j = k * 2;
      while (true) {
        if (--j >= k) {
          arrayOfInt[j] = this.free;
          this.free = j;
          continue;
        } 
        this.positions = arrayOfInt;
        break;
      } 
    } 
    int i = this.free;
    this.free = this.positions[this.free];
    return i;
  }
  
  protected void chainFreelist() {
    this.free = -1;
    int i = this.positions.length;
    while (true) {
      int j = i - 1;
      if (j > 1) {
        i = j;
        if (this.positions[j] == -2) {
          this.positions[j] = this.free;
          this.free = j;
          i = j;
        } 
        continue;
      } 
      break;
    } 
  }
  
  public void consumePosRange(int paramInt1, int paramInt2, Consumer paramConsumer) {
    super.consumePosRange(this.positions[paramInt1], this.positions[paramInt2], paramConsumer);
  }
  
  public int copyPos(int paramInt) {
    int i = paramInt;
    if (paramInt > 1) {
      i = allocPositionIndex();
      this.positions[i] = this.positions[paramInt];
    } 
    return i;
  }
  
  public int createPos(int paramInt, boolean paramBoolean) {
    // Byte code:
    //   0: iconst_1
    //   1: istore #4
    //   3: iload_1
    //   4: ifne -> 13
    //   7: iload_2
    //   8: ifne -> 13
    //   11: iconst_0
    //   12: ireturn
    //   13: iload_2
    //   14: ifeq -> 27
    //   17: iload_1
    //   18: aload_0
    //   19: invokevirtual size : ()I
    //   22: if_icmpne -> 27
    //   25: iconst_1
    //   26: ireturn
    //   27: iload_1
    //   28: aload_0
    //   29: getfield gapStart : I
    //   32: if_icmpgt -> 51
    //   35: iload_1
    //   36: istore_3
    //   37: iload_1
    //   38: aload_0
    //   39: getfield gapStart : I
    //   42: if_icmpne -> 63
    //   45: iload_1
    //   46: istore_3
    //   47: iload_2
    //   48: ifeq -> 63
    //   51: iload_1
    //   52: aload_0
    //   53: getfield gapEnd : I
    //   56: aload_0
    //   57: getfield gapStart : I
    //   60: isub
    //   61: iadd
    //   62: istore_3
    //   63: aload_0
    //   64: invokevirtual allocPositionIndex : ()I
    //   67: istore #5
    //   69: aload_0
    //   70: getfield positions : [I
    //   73: astore #6
    //   75: iload_2
    //   76: ifeq -> 95
    //   79: iload #4
    //   81: istore_1
    //   82: aload #6
    //   84: iload #5
    //   86: iload_1
    //   87: iload_3
    //   88: iconst_1
    //   89: ishl
    //   90: ior
    //   91: iastore
    //   92: iload #5
    //   94: ireturn
    //   95: iconst_0
    //   96: istore_1
    //   97: goto -> 82
  }
  
  public int endPos() {
    return 1;
  }
  
  public void fillPosRange(int paramInt1, int paramInt2, Object paramObject) {
    fillPosRange(this.positions[paramInt1], this.positions[paramInt2], paramObject);
  }
  
  protected void gapReserve(int paramInt1, int paramInt2) {
    int i = this.gapEnd;
    int j = this.gapStart;
    if (paramInt2 > i - j) {
      int k = this.base.size;
      super.gapReserve(paramInt1, paramInt2);
      paramInt2 = this.base.size;
      if (paramInt1 == j) {
        adjustPositions(i << 1, paramInt2 << 1 | 0x1, paramInt2 - k << 1);
        return;
      } 
      adjustPositions(i << 1, k << 1 | 0x1, j - i << 1);
      adjustPositions(this.gapStart << 1, paramInt2 << 1 | 0x1, this.gapEnd - this.gapStart << 1);
      return;
    } 
    if (paramInt1 != this.gapStart) {
      shiftGap(paramInt1);
      return;
    } 
  }
  
  public boolean hasNext(int paramInt) {
    int i = this.positions[paramInt] >>> 1;
    paramInt = i;
    if (i >= this.gapStart)
      paramInt = i + this.gapEnd - this.gapStart; 
    return (paramInt < this.base.getBufferLength());
  }
  
  protected boolean isAfterPos(int paramInt) {
    return ((this.positions[paramInt] & 0x1) != 0);
  }
  
  public int nextIndex(int paramInt) {
    int i = this.positions[paramInt] >>> 1;
    paramInt = i;
    if (i > this.gapStart)
      paramInt = i - this.gapEnd - this.gapStart; 
    return paramInt;
  }
  
  public int nextPos(int paramInt) {
    int k = this.positions[paramInt];
    int j = k >>> 1;
    int i = j;
    if (j >= this.gapStart)
      i = j + this.gapEnd - this.gapStart; 
    if (i >= this.base.getBufferLength()) {
      releasePos(paramInt);
      return 0;
    } 
    i = paramInt;
    if (paramInt == 0)
      i = createPos(0, true); 
    this.positions[i] = k | 0x1;
    return i;
  }
  
  public void releasePos(int paramInt) {
    if (paramInt >= 2) {
      if (this.free == -2)
        chainFreelist(); 
      this.positions[paramInt] = this.free;
      this.free = paramInt;
    } 
  }
  
  protected void removePosRange(int paramInt1, int paramInt2) {
    super.removePosRange(this.positions[paramInt1], this.positions[paramInt2]);
    int i = this.gapStart;
    int j = this.gapEnd;
    if (this.free >= -1)
      unchainFreelist(); 
    paramInt1 = this.positions.length;
    while (true) {
      paramInt2 = paramInt1 - 1;
      if (paramInt2 > 0) {
        int k = this.positions[paramInt2];
        paramInt1 = paramInt2;
        if (k != -2) {
          int m = k >> 1;
          if ((k & 0x1) != 0) {
            paramInt1 = 1;
          } else {
            paramInt1 = 0;
          } 
          if (paramInt1 != 0) {
            paramInt1 = paramInt2;
            if (m >= i) {
              paramInt1 = paramInt2;
              if (m < j) {
                this.positions[paramInt2] = this.gapEnd << 1 | 0x1;
                paramInt1 = paramInt2;
              } 
            } 
            continue;
          } 
          paramInt1 = paramInt2;
          if (m > i) {
            paramInt1 = paramInt2;
            if (m <= j) {
              this.positions[paramInt2] = this.gapStart << 1;
              paramInt1 = paramInt2;
            } 
          } 
        } 
        continue;
      } 
      break;
    } 
  }
  
  protected void shiftGap(int paramInt) {
    int k;
    int i = this.gapStart;
    int j = paramInt - i;
    if (j > 0) {
      int m = this.gapEnd;
      i = i - m << 1;
      k = m << 1;
      j = (m + j << 1) - 1;
    } else if (paramInt != i) {
      k = (paramInt << 1) + 1;
      j = i << 1;
      i = this.gapEnd - i << 1;
    } else {
      return;
    } 
    super.shiftGap(paramInt);
    adjustPositions(k, j, i);
  }
  
  public int startPos() {
    return 0;
  }
  
  protected void unchainFreelist() {
    for (int i = this.free; i >= 0; i = j) {
      int j = this.positions[i];
      this.positions[i] = -2;
    } 
    this.free = -2;
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/gnu/lists/StableVector.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */