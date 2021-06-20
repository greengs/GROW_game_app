package gnu.kawa.xml;

import gnu.lists.AbstractSequence;

public class SortedNodes extends Nodes {
  int nesting = 0;
  
  int compareIndex(int paramInt1, AbstractSequence paramAbstractSequence, int paramInt2) {
    if (this.data[paramInt1] != '')
      throw new RuntimeException("invalid kind of value to compare"); 
    return AbstractSequence.compare((AbstractSequence)this.objects[getIntN(paramInt1 + 1)], getIntN(paramInt1 + 3), paramAbstractSequence, paramInt2);
  }
  
  int find(int paramInt1, int paramInt2, AbstractSequence paramAbstractSequence, int paramInt3) {
    int j = 0;
    int i = paramInt2;
    for (paramInt2 = j; paramInt2 < i; paramInt2 = j + 1) {
      j = paramInt2 + i >>> 1;
      int k = compareIndex(j * 5 + paramInt1, paramAbstractSequence, paramInt3);
      if (k == 0)
        return -1; 
      if (k > 0) {
        i = j;
        continue;
      } 
    } 
    return paramInt2 * 5 + paramInt1;
  }
  
  public void writePosition(AbstractSequence paramAbstractSequence, int paramInt) {
    if (this.count > 0) {
      int i = this.gapStart - 5;
      int j = compareIndex(i, paramAbstractSequence, paramInt);
      if (j < 0) {
        i = this.gapEnd;
        i = find(i, (this.data.length - i) / 5, paramAbstractSequence, paramInt);
        if (i < 0)
          return; 
        j = i - this.gapEnd;
        if (j > 0) {
          System.arraycopy(this.data, this.gapEnd, this.data, this.gapStart, j);
          this.gapEnd = i;
          this.gapStart += j;
        } 
      } else if (j != 0) {
        i = find(0, i / 5, paramAbstractSequence, paramInt);
        if (i >= 0) {
          j = this.gapStart - i;
          if (j > 0) {
            System.arraycopy(this.data, i, this.data, this.gapEnd - j, j);
            this.gapStart = i;
            this.gapEnd -= j;
          } 
        } else {
          return;
        } 
      } else {
        return;
      } 
    } 
    super.writePosition(paramAbstractSequence, paramInt);
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/gnu/kawa/xml/SortedNodes.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */