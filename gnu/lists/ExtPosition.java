package gnu.lists;

public class ExtPosition extends SeqPosition {
  int position = -1;
  
  public int getPos() {
    if (this.position < 0)
      this.position = PositionManager.manager.register(this); 
    return this.position;
  }
  
  public final boolean isAfter() {
    return ((this.ipos & 0x1) != 0);
  }
  
  public void release() {
    if (this.position >= 0)
      PositionManager.manager.release(this.position); 
    this.sequence = null;
  }
  
  public void setPos(AbstractSequence paramAbstractSequence, int paramInt) {
    throw paramAbstractSequence.unsupported("setPos");
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/gnu/lists/ExtPosition.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */