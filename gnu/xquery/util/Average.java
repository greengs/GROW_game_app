package gnu.xquery.util;

import gnu.lists.Sequence;
import gnu.lists.TreeList;
import gnu.mapping.Procedure1;
import gnu.mapping.Values;
import gnu.math.IntNum;

public class Average extends Procedure1 {
  public static final Average avg = new Average("avg");
  
  public Average(String paramString) {
    super(paramString);
  }
  
  public Object apply1(Object paramObject) throws Throwable {
    Values values = Values.empty;
    int i = 0;
    if (paramObject instanceof Values) {
      TreeList treeList = (TreeList)paramObject;
      int j = 0;
      paramObject = values;
      while (true) {
        Object object = treeList.getPosNext(j);
        if (object != Sequence.eofValue) {
          i++;
          if (paramObject == Values.empty) {
            paramObject = object;
          } else {
            paramObject = ArithOp.add.apply2(paramObject, object);
          } 
          j = treeList.nextPos(j);
          continue;
        } 
        return (paramObject == Values.empty) ? paramObject : ArithOp.div.apply2(paramObject, IntNum.make(i));
      } 
    } 
    i = 1;
    return (paramObject == Values.empty) ? paramObject : ArithOp.div.apply2(paramObject, IntNum.make(i));
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/gnu/xquery/util/Average.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */