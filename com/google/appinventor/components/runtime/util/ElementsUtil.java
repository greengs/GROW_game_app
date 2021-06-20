package com.google.appinventor.components.runtime.util;

import com.google.appinventor.components.runtime.errors.YailRuntimeError;

public class ElementsUtil {
  public static YailList elements(YailList paramYailList, String paramString) {
    String[] arrayOfString = paramYailList.toStringArray();
    for (int i = 0; i < arrayOfString.length; i++) {
      if (!(arrayOfString[i] instanceof String))
        throw new YailRuntimeError("Items passed to " + paramString + " must be Strings", "Error"); 
    } 
    return paramYailList;
  }
  
  public static YailList elementsFromString(String paramString) {
    YailList yailList = new YailList();
    if (paramString.length() > 0)
      yailList = YailList.makeList((Object[])paramString.split(" *, *")); 
    return yailList;
  }
  
  public static int selectionIndex(int paramInt, YailList paramYailList) {
    if (paramInt > 0) {
      int i = paramInt;
      return (paramInt > paramYailList.size()) ? 0 : i;
    } 
    return 0;
  }
  
  public static int setSelectedIndexFromValue(String paramString, YailList paramYailList) {
    for (int i = 0; i < paramYailList.size(); i++) {
      if (paramYailList.getString(i).equals(paramString))
        return i + 1; 
    } 
    return 0;
  }
  
  public static String setSelectionFromIndex(int paramInt, YailList paramYailList) {
    return (paramInt == 0) ? "" : paramYailList.getString(paramInt - 1);
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/com/google/appinventor/components/runtime/util/ElementsUtil.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */