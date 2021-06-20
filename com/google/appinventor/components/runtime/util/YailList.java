package com.google.appinventor.components.runtime.util;

import com.google.appinventor.components.runtime.errors.YailRuntimeError;
import gnu.lists.LList;
import gnu.lists.Pair;
import gnu.math.IntNum;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import org.json.JSONException;

public class YailList extends Pair implements YailObject {
  public YailList() {
    super(YailConstants.YAIL_HEADER, LList.Empty);
  }
  
  private YailList(Object paramObject) {
    super(YailConstants.YAIL_HEADER, paramObject);
  }
  
  public static String YailListElementToString(Object paramObject) {
    return (paramObject instanceof IntNum) ? ((IntNum)paramObject).toString(10) : ((paramObject instanceof Long) ? Long.toString(((Long)paramObject).longValue()) : (Number.class.isInstance(paramObject) ? YailNumberToString.format(((Number)paramObject).doubleValue()) : String.valueOf(paramObject)));
  }
  
  public static YailList makeEmptyList() {
    return new YailList();
  }
  
  public static YailList makeList(Collection<?> paramCollection) {
    return new YailList(Pair.makeList(new ArrayList(paramCollection)));
  }
  
  public static YailList makeList(List paramList) {
    return new YailList(Pair.makeList(paramList));
  }
  
  public static YailList makeList(Set<?> paramSet) {
    return new YailList(Pair.makeList(new ArrayList(paramSet)));
  }
  
  public static YailList makeList(Object[] paramArrayOfObject) {
    return new YailList(Pair.makeList(paramArrayOfObject, 0));
  }
  
  public Object getObject(int paramInt) {
    return get(paramInt + 1);
  }
  
  public String getString(int paramInt) {
    return get(paramInt + 1).toString();
  }
  
  public int size() {
    return super.size() - 1;
  }
  
  public Object[] toArray() {
    if (this.cdr instanceof Pair)
      return ((Pair)this.cdr).toArray(); 
    if (this.cdr instanceof LList)
      return ((LList)this.cdr).toArray(); 
    throw new YailRuntimeError("YailList cannot be represented as an array", "YailList Error.");
  }
  
  public String toJSONString() {
    try {
      StringBuilder stringBuilder = new StringBuilder();
      null = "";
      stringBuilder.append('[');
      int j = size();
      for (int i = 1; i <= j; i++) {
        Object object = get(i);
        stringBuilder.append(null).append(JsonUtil.getJsonRepresentation(object));
        null = ",";
      } 
      stringBuilder.append(']');
      return stringBuilder.toString();
    } catch (JSONException jSONException) {
      throw new YailRuntimeError("List failed to convert to JSON.", "JSON Creation Error.");
    } 
  }
  
  public String toString() {
    if (this.cdr instanceof Pair)
      return ((Pair)this.cdr).toString(); 
    if (this.cdr instanceof LList)
      return ((LList)this.cdr).toString(); 
    throw new RuntimeException("YailList cannot be represented as a String");
  }
  
  public String[] toStringArray() {
    int j = size();
    String[] arrayOfString = new String[j];
    for (int i = 1; i <= j; i++)
      arrayOfString[i - 1] = YailListElementToString(get(i)); 
    return arrayOfString;
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/com/google/appinventor/components/runtime/util/YailList.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */