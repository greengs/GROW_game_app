package gnu.xquery.util;

import gnu.kawa.util.GeneralHashTable;

class DistinctValuesHashTable extends GeneralHashTable {
  NamedCollator collator;
  
  public DistinctValuesHashTable(NamedCollator paramNamedCollator) {
    this.collator = paramNamedCollator;
  }
  
  public int hash(Object paramObject) {
    if (paramObject == null)
      return 0; 
    if (paramObject instanceof Number && (paramObject instanceof gnu.math.RealNum || !(paramObject instanceof gnu.math.Numeric))) {
      int j = Float.floatToIntBits(((Number)paramObject).floatValue());
      int i = j;
      return (j == Integer.MIN_VALUE) ? 0 : i;
    } 
    return paramObject.hashCode();
  }
  
  public boolean matches(Object paramObject1, Object paramObject2) {
    return (paramObject1 != paramObject2 && (!NumberValue.isNaN(paramObject1) || !NumberValue.isNaN(paramObject2))) ? Compare.apply(72, paramObject1, paramObject2, this.collator) : true;
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/gnu/xquery/util/DistinctValuesHashTable.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */