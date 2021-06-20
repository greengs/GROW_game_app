package kawa.lang;

import gnu.lists.Consumer;

public class VarListPat extends Pattern {
  int min_length;
  
  public VarListPat(int paramInt) {
    this.min_length = paramInt;
  }
  
  public boolean match(Object paramObject, Object[] paramArrayOfObject, int paramInt) {
    int i = 0;
    while (i < this.min_length) {
      if (paramObject instanceof gnu.lists.Pair) {
        paramObject = paramObject;
        paramArrayOfObject[paramInt + i] = paramObject.getCar();
        paramObject = paramObject.getCdr();
        i++;
        continue;
      } 
      return false;
    } 
    paramArrayOfObject[paramInt + i] = paramObject;
    return true;
  }
  
  public void print(Consumer paramConsumer) {
    paramConsumer.write("#<varlist-pattern min:");
    paramConsumer.writeInt(this.min_length);
    paramConsumer.write(62);
  }
  
  public int varCount() {
    return this.min_length + 1;
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/kawa/lang/VarListPat.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */