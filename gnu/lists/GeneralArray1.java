package gnu.lists;

public class GeneralArray1 extends GeneralArray implements Sequence {
  public void consumePosRange(int paramInt1, int paramInt2, Consumer paramConsumer) {
    if (!paramConsumer.ignoring())
      while (true) {
        if (!equals(paramInt1, paramInt2)) {
          if (!hasNext(paramInt1))
            throw new RuntimeException(); 
          this.base.consume(this.offset + this.strides[0] * (paramInt1 >>> 1), 1, paramConsumer);
          paramInt1 = nextPos(paramInt1);
          continue;
        } 
        return;
      }  
  }
  
  public int createPos(int paramInt, boolean paramBoolean) {
    if (paramBoolean) {
      boolean bool1 = true;
      return bool1 | paramInt << 1;
    } 
    boolean bool = false;
    return bool | paramInt << 1;
  }
  
  protected int nextIndex(int paramInt) {
    return (paramInt == -1) ? size() : (paramInt >>> 1);
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/gnu/lists/GeneralArray1.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */