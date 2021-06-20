package gnu.xquery.util;

import gnu.kawa.functions.Arithmetic;
import gnu.kawa.functions.NumberCompare;
import gnu.kawa.xml.KNode;
import gnu.kawa.xml.XDataType;
import gnu.lists.Sequence;
import gnu.lists.TreeList;
import gnu.mapping.Values;
import gnu.xml.TextUtils;

public class MinMax {
  static Object convert(Object paramObject) {
    Object object = KNode.atomicValue(paramObject);
    paramObject = object;
    if (object instanceof gnu.kawa.xml.UntypedAtomic)
      paramObject = XDataType.doubleType.valueOf(TextUtils.stringValue(object)); 
    return paramObject;
  }
  
  public static Object max(Object paramObject, NamedCollator paramNamedCollator) {
    return minMax(paramObject, true, paramNamedCollator);
  }
  
  public static Object min(Object paramObject, NamedCollator paramNamedCollator) {
    return minMax(paramObject, false, paramNamedCollator);
  }
  
  public static Object minMax(Object paramObject, boolean paramBoolean, NamedCollator paramNamedCollator) {
    byte b = 16;
    if (paramObject instanceof Values) {
      TreeList treeList = (TreeList)paramObject;
      int i = 0;
      if (!paramBoolean)
        b = 4; 
      paramObject = treeList.getPosNext(0);
      if (paramObject == Sequence.eofValue)
        return Values.empty; 
      paramObject = convert(paramObject);
      while (true) {
        int j = treeList.nextPos(i);
        Object object2 = treeList.getPosNext(j);
        Object object1 = paramObject;
        if (object2 != Sequence.eofValue) {
          object1 = convert(object2);
          if (paramObject instanceof Number || object1 instanceof Number) {
            int m;
            int k = Arithmetic.classifyValue(paramObject);
            i = Arithmetic.classifyValue(object1);
            int n = NumberCompare.compare(paramObject, k, object1, i, false);
            if (n == -3)
              throw new IllegalArgumentException("values cannot be compared"); 
            if (k < i) {
              m = i;
            } else {
              m = k;
            } 
            if (n == -2) {
              object1 = NumberValue.NaN;
              k = 1;
            } else if (!NumberCompare.checkCompareCode(n, b)) {
              if (m != i) {
                k = 1;
              } else {
                k = 0;
              } 
            } else {
              if (m != k) {
                k = 1;
              } else {
                k = 0;
              } 
              object1 = paramObject;
            } 
            i = j;
            paramObject = object1;
            if (k != 0) {
              paramObject = Arithmetic.convert(object1, m);
              i = j;
            } 
            continue;
          } 
          i = j;
          if (!Compare.atomicCompare(b, paramObject, object1, paramNamedCollator)) {
            paramObject = object1;
            i = j;
          } 
          continue;
        } 
        return object1;
      } 
    } 
    paramObject = convert(paramObject);
    Compare.atomicCompare(16, paramObject, paramObject, paramNamedCollator);
    return paramObject;
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/gnu/xquery/util/MinMax.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */