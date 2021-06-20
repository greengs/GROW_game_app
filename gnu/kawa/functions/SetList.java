package gnu.kawa.functions;

import gnu.bytecode.Type;
import gnu.mapping.Procedure2;
import gnu.mapping.Values;
import java.util.List;

class SetList extends Procedure2 {
  Type elementType;
  
  List list;
  
  public SetList(List paramList) {
    this.list = paramList;
  }
  
  public Object apply2(Object paramObject1, Object paramObject2) {
    this.list.set(((Number)paramObject1).intValue(), paramObject2);
    return Values.empty;
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/gnu/kawa/functions/SetList.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */