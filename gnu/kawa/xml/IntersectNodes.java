package gnu.kawa.xml;

import gnu.lists.AbstractSequence;
import gnu.lists.Consumer;
import gnu.mapping.Procedure2;
import gnu.mapping.Values;

public class IntersectNodes extends Procedure2 {
  public static final IntersectNodes exceptNodes;
  
  public static final IntersectNodes intersectNodes = new IntersectNodes(false);
  
  boolean isExcept;
  
  static {
    exceptNodes = new IntersectNodes(true);
  }
  
  public IntersectNodes(boolean paramBoolean) {
    this.isExcept = paramBoolean;
  }
  
  public Object apply2(Object paramObject1, Object paramObject2) {
    SortedNodes sortedNodes1 = new SortedNodes();
    SortedNodes sortedNodes2 = new SortedNodes();
    SortedNodes sortedNodes3 = new SortedNodes();
    Values.writeValues(paramObject1, (Consumer)sortedNodes1);
    Values.writeValues(paramObject2, (Consumer)sortedNodes2);
    int i = 0;
    paramObject1 = null;
    int k = 0;
    int j = 0;
    int m = 0;
    label30: while (true) {
      paramObject2 = sortedNodes1.getSeq(m);
      if (paramObject2 == null)
        return sortedNodes3; 
      int n = sortedNodes1.getPos(m);
      if (j == -1) {
        j = AbstractSequence.compare((AbstractSequence)paramObject2, n, (AbstractSequence)paramObject1, k);
        int i1 = k;
      } else if (j == 0) {
        j = 1;
        int i1 = k;
      } else {
        int i1 = k;
      } 
      while (true) {
        int i1;
        boolean bool;
        k = j;
        if (j > 0) {
          paramObject1 = sortedNodes2.getSeq(i);
          if (paramObject1 == null) {
            k = -2;
          } else {
            i1 = sortedNodes2.getPos(i);
            j = AbstractSequence.compare((AbstractSequence)paramObject2, n, (AbstractSequence)paramObject1, i1);
            i++;
            continue;
          } 
        } 
        if (k == 0) {
          bool = true;
        } else {
          bool = false;
        } 
        if (bool != this.isExcept)
          sortedNodes3.writePosition((AbstractSequence)paramObject2, n); 
        m++;
        j = k;
        k = i1;
        continue label30;
      } 
      break;
    } 
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/gnu/kawa/xml/IntersectNodes.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */