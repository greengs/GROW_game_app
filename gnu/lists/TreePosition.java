package gnu.lists;

import java.io.PrintStream;

public class TreePosition extends SeqPosition implements Cloneable {
  int depth;
  
  int[] istack;
  
  AbstractSequence[] sstack;
  
  int start;
  
  private Object xpos;
  
  public TreePosition() {
    this.depth = -1;
  }
  
  public TreePosition(AbstractSequence paramAbstractSequence, int paramInt) {
    super(paramAbstractSequence, paramInt, false);
  }
  
  public TreePosition(TreePosition paramTreePosition) {
    set(paramTreePosition);
  }
  
  public TreePosition(Object paramObject) {
    this.xpos = paramObject;
    this.depth = -1;
  }
  
  public Object clone() {
    return new TreePosition(this);
  }
  
  public void dump() {
    System.err.println("TreePosition dump depth:" + this.depth + " start:" + this.start);
    for (int i = 0; i <= this.depth; i++) {
      int j;
      AbstractSequence abstractSequence;
      if (i == 0) {
        abstractSequence = this.sequence;
      } else {
        abstractSequence = this.sstack[this.depth - i];
      } 
      System.err.print("#" + i + " seq:" + abstractSequence);
      PrintStream printStream = System.err;
      StringBuilder stringBuilder = (new StringBuilder()).append(" ipos:");
      if (i == 0) {
        j = this.ipos;
      } else {
        j = this.istack[this.depth - i];
      } 
      printStream.println(stringBuilder.append(j).toString());
    } 
  }
  
  public Object getAncestor(int paramInt) {
    if (paramInt == 0)
      return this.sequence.getPosNext(this.ipos); 
    paramInt = this.depth - paramInt;
    if (paramInt <= 0)
      return getRoot(); 
    paramInt += this.start;
    return this.sstack[paramInt].getPosNext(this.istack[paramInt]);
  }
  
  public int getDepth() {
    return this.depth + 1;
  }
  
  public Object getPosNext() {
    return (this.sequence == null) ? this.xpos : this.sequence.getPosNext(this.ipos);
  }
  
  public AbstractSequence getRoot() {
    return (this.depth == 0) ? this.sequence : this.sstack[this.start];
  }
  
  public boolean gotoAttributesStart() {
    if (this.sequence == null) {
      if (this.xpos instanceof AbstractSequence);
      return false;
    } 
    return this.sequence.gotoAttributesStart(this);
  }
  
  public boolean gotoChildrenStart() {
    if (this.sequence == null) {
      if (!(this.xpos instanceof AbstractSequence))
        return false; 
      this.depth = 0;
      this.sequence = (AbstractSequence)this.xpos;
      setPos(this.sequence.startPos());
      return true;
    } 
    return !!this.sequence.gotoChildrenStart(this);
  }
  
  public final boolean gotoParent() {
    return (this.sequence == null) ? false : this.sequence.gotoParent(this);
  }
  
  public void pop() {
    this.sequence.releasePos(this.ipos);
    popNoRelease();
  }
  
  public void popNoRelease() {
    int i = this.depth - 1;
    this.depth = i;
    if (i < 0) {
      this.xpos = this.sequence;
      this.sequence = null;
      return;
    } 
    this.sequence = this.sstack[this.start + this.depth];
    this.ipos = this.istack[this.start + this.depth];
  }
  
  public void push(AbstractSequence paramAbstractSequence, int paramInt) {
    int i = this.depth + this.start;
    if (i >= 0) {
      if (i == 0) {
        this.istack = new int[8];
        this.sstack = new AbstractSequence[8];
      } else if (i >= this.istack.length) {
        int j = i * 2;
        int[] arrayOfInt = new int[j];
        Object[] arrayOfObject = new Object[j];
        AbstractSequence[] arrayOfAbstractSequence = new AbstractSequence[j];
        System.arraycopy(this.istack, 0, arrayOfInt, 0, this.depth);
        System.arraycopy(this.sstack, 0, arrayOfAbstractSequence, 0, this.depth);
        this.istack = arrayOfInt;
        this.sstack = arrayOfAbstractSequence;
      } 
      this.sstack[i] = this.sequence;
      this.istack[i] = this.ipos;
    } 
    this.depth++;
    this.sequence = paramAbstractSequence;
    this.ipos = paramInt;
  }
  
  public void release() {
    while (this.sequence != null) {
      this.sequence.releasePos(this.ipos);
      pop();
    } 
    this.xpos = null;
  }
  
  public void set(TreePosition paramTreePosition) {
    release();
    int i = paramTreePosition.depth;
    this.depth = i;
    if (i < 0) {
      this.xpos = paramTreePosition.xpos;
      return;
    } 
    if (this.sstack == null || this.sstack.length <= i)
      this.sstack = new AbstractSequence[i + 10]; 
    if (this.istack == null || this.istack.length <= i)
      this.istack = new int[i + 10]; 
    for (i = 0; i < this.depth; i++) {
      int j = i + paramTreePosition.start;
      AbstractSequence abstractSequence1 = paramTreePosition.sstack[j];
      this.sstack[this.depth - 1] = abstractSequence1;
      this.istack[this.depth - i] = abstractSequence1.copyPos(paramTreePosition.istack[j]);
    } 
    AbstractSequence abstractSequence = paramTreePosition.sequence;
    this.sequence = abstractSequence;
    this.ipos = abstractSequence.copyPos(paramTreePosition.ipos);
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/gnu/lists/TreePosition.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */