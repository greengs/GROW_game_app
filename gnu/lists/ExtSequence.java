package gnu.lists;

public abstract class ExtSequence extends AbstractSequence {
  public int copyPos(int paramInt) {
    return (paramInt <= 0) ? paramInt : PositionManager.manager.register(PositionManager.getPositionObject(paramInt).copy());
  }
  
  protected boolean isAfterPos(int paramInt) {
    return (paramInt <= 0) ? (!(paramInt >= 0)) : (!(((PositionManager.getPositionObject(paramInt)).ipos & 0x1) == 0));
  }
  
  protected int nextIndex(int paramInt) {
    return (paramInt == -1) ? size() : ((paramInt == 0) ? 0 : PositionManager.getPositionObject(paramInt).nextIndex());
  }
  
  protected void releasePos(int paramInt) {
    if (paramInt > 0)
      PositionManager.manager.release(paramInt); 
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/gnu/lists/ExtSequence.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */