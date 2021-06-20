package gnu.kawa.util;

import java.util.Hashtable;

public class RangeTable implements Cloneable {
  Hashtable hash = new Hashtable<Object, Object>(200);
  
  Object[] index = new Object[128];
  
  public Object clone() {
    return copy();
  }
  
  public RangeTable copy() {
    RangeTable rangeTable = new RangeTable();
    rangeTable.index = (Object[])this.index.clone();
    rangeTable.hash = (Hashtable)this.hash.clone();
    return rangeTable;
  }
  
  public Object lookup(int paramInt, Object paramObject) {
    return ((paramInt & 0x7F) == paramInt) ? this.index[paramInt] : this.hash.get(new Integer(paramInt));
  }
  
  public void remove(int paramInt) {
    remove(paramInt, paramInt);
  }
  
  public void remove(int paramInt1, int paramInt2) {
    if (paramInt1 <= paramInt2)
      while (true) {
        if ((paramInt1 & 0x7F) == paramInt1) {
          this.index[paramInt1] = null;
        } else {
          this.hash.remove(new Integer(paramInt1));
        } 
        if (paramInt1 != paramInt2) {
          paramInt1++;
          continue;
        } 
        return;
      }  
  }
  
  public void set(int paramInt1, int paramInt2, Object paramObject) {
    if (paramInt1 <= paramInt2)
      while (true) {
        if ((paramInt1 & 0x7F) == paramInt1) {
          this.index[paramInt1] = paramObject;
        } else {
          this.hash.put(new Integer(paramInt1), paramObject);
        } 
        if (paramInt1 != paramInt2) {
          paramInt1++;
          continue;
        } 
        return;
      }  
  }
  
  public void set(int paramInt, Object paramObject) {
    set(paramInt, paramInt, paramObject);
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/gnu/kawa/util/RangeTable.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */