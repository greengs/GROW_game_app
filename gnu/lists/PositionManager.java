package gnu.lists;

public class PositionManager {
  static final PositionManager manager = new PositionManager();
  
  int freeListHead = -1;
  
  int[] ivals = new int[50];
  
  SeqPosition[] positions = new SeqPosition[50];
  
  public PositionManager() {
    addToFreeList(this.ivals, 1, this.ivals.length);
  }
  
  private void addToFreeList(int[] paramArrayOfint, int paramInt1, int paramInt2) {
    int i = this.freeListHead;
    while (paramInt1 < paramInt2) {
      paramArrayOfint[paramInt1] = i;
      i = paramInt1;
      paramInt1++;
    } 
    this.freeListHead = i;
  }
  
  private int getFreeSlot() {
    int j = this.freeListHead;
    int i = j;
    if (j < 0) {
      i = this.positions.length;
      SeqPosition[] arrayOfSeqPosition = new SeqPosition[i * 2];
      int[] arrayOfInt = new int[i * 2];
      System.arraycopy(this.positions, 0, arrayOfSeqPosition, 0, i);
      System.arraycopy(this.ivals, 0, arrayOfInt, 0, i);
      this.positions = arrayOfSeqPosition;
      this.ivals = arrayOfInt;
      addToFreeList(arrayOfInt, i, i * 2);
      i = this.freeListHead;
    } 
    this.freeListHead = this.ivals[i];
    return i;
  }
  
  public static SeqPosition getPositionObject(int paramInt) {
    synchronized (manager) {
      return null.positions[paramInt];
    } 
  }
  
  public int register(SeqPosition paramSeqPosition) {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: invokespecial getFreeSlot : ()I
    //   6: istore_2
    //   7: aload_0
    //   8: getfield positions : [Lgnu/lists/SeqPosition;
    //   11: iload_2
    //   12: aload_1
    //   13: aastore
    //   14: aload_0
    //   15: getfield ivals : [I
    //   18: iload_2
    //   19: iconst_m1
    //   20: iastore
    //   21: aload_0
    //   22: monitorexit
    //   23: iload_2
    //   24: ireturn
    //   25: astore_1
    //   26: aload_0
    //   27: monitorexit
    //   28: aload_1
    //   29: athrow
    // Exception table:
    //   from	to	target	type
    //   2	21	25	finally
  }
  
  public void release(int paramInt) {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield positions : [Lgnu/lists/SeqPosition;
    //   6: iload_1
    //   7: aaload
    //   8: astore_2
    //   9: aload_2
    //   10: instanceof gnu/lists/ExtPosition
    //   13: ifeq -> 24
    //   16: aload_2
    //   17: checkcast gnu/lists/ExtPosition
    //   20: iconst_m1
    //   21: putfield position : I
    //   24: aload_0
    //   25: getfield positions : [Lgnu/lists/SeqPosition;
    //   28: iload_1
    //   29: aconst_null
    //   30: aastore
    //   31: aload_0
    //   32: getfield ivals : [I
    //   35: iload_1
    //   36: aload_0
    //   37: getfield freeListHead : I
    //   40: iastore
    //   41: aload_0
    //   42: iload_1
    //   43: putfield freeListHead : I
    //   46: aload_2
    //   47: invokevirtual release : ()V
    //   50: aload_0
    //   51: monitorexit
    //   52: return
    //   53: astore_2
    //   54: aload_0
    //   55: monitorexit
    //   56: aload_2
    //   57: athrow
    // Exception table:
    //   from	to	target	type
    //   2	24	53	finally
    //   24	50	53	finally
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/gnu/lists/PositionManager.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */