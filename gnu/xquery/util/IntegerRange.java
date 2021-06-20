package gnu.xquery.util;

import gnu.kawa.xml.KNode;
import gnu.lists.Consumer;
import gnu.mapping.CallContext;
import gnu.mapping.MethodProc;
import gnu.mapping.Values;
import gnu.math.IntNum;

public class IntegerRange extends MethodProc {
  public static final IntNum MAX_INT;
  
  public static final IntNum MIN_INT;
  
  public static final IntegerRange integerRange = new IntegerRange("to");
  
  static {
    MIN_INT = IntNum.make(-2147483648);
    MAX_INT = IntNum.make(2147483647);
  }
  
  public IntegerRange(String paramString) {
    setName(paramString);
  }
  
  public static void integerRange(IntNum paramIntNum1, IntNum paramIntNum2, Consumer paramConsumer) {
    IntNum intNum = paramIntNum1;
    if (IntNum.compare(paramIntNum1, MIN_INT) >= 0) {
      intNum = paramIntNum1;
      if (IntNum.compare(paramIntNum2, MAX_INT) <= 0) {
        int i = paramIntNum1.intValue();
        int j = paramIntNum2.intValue();
        if (i <= j)
          while (true) {
            paramConsumer.writeInt(i);
            if (i != j) {
              i++;
              continue;
            } 
            return;
          }  
        return;
      } 
    } 
    while (true) {
      if (IntNum.compare(intNum, paramIntNum2) <= 0) {
        paramConsumer.writeObject(intNum);
        intNum = IntNum.add(intNum, 1);
        continue;
      } 
      return;
    } 
  }
  
  public void apply(CallContext paramCallContext) {
    Object object2 = paramCallContext.getNextArg();
    Object object1 = paramCallContext.getNextArg();
    paramCallContext.lastArg();
    object2 = KNode.atomicValue(object2);
    Object object3 = KNode.atomicValue(object1);
    if (object2 == Values.empty || object2 == null || object3 == Values.empty || object3 == null)
      return; 
    object1 = object2;
    if (object2 instanceof gnu.kawa.xml.UntypedAtomic)
      object1 = IntNum.valueOf(object2.toString().trim(), 10); 
    object2 = object3;
    if (object3 instanceof gnu.kawa.xml.UntypedAtomic)
      object2 = IntNum.valueOf(object3.toString().trim(), 10); 
    integerRange((IntNum)object1, (IntNum)object2, paramCallContext.consumer);
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/gnu/xquery/util/IntegerRange.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */