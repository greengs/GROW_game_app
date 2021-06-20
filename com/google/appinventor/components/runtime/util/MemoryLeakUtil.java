package com.google.appinventor.components.runtime.util;

import android.util.Log;
import com.google.appinventor.components.runtime.collect.Maps;
import java.lang.ref.WeakReference;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class MemoryLeakUtil {
  private static final String LOG_TAG = "MemoryLeakUtil";
  
  private static final Map<String, WeakReference<Object>> TRACKED_OBJECTS;
  
  private static final AtomicInteger prefixGenerator = new AtomicInteger(0);
  
  static {
    TRACKED_OBJECTS = Maps.newTreeMap();
  }
  
  public static void checkAllTrackedObjects(boolean paramBoolean1, boolean paramBoolean2) {
    Log.i("MemoryLeakUtil", "Checking Tracked Objects ----------------------------------------");
    System.gc();
    byte b = 0;
    int i = 0;
    Iterator<Map.Entry> iterator = TRACKED_OBJECTS.entrySet().iterator();
    while (iterator.hasNext()) {
      int j;
      byte b1;
      Map.Entry entry = iterator.next();
      String str = (String)entry.getKey();
      entry = ((WeakReference<Map.Entry>)entry.getValue()).get();
      if (entry != null) {
        b1 = b + 1;
        j = i;
      } else {
        j = ++i;
        b1 = b;
        if (paramBoolean2) {
          iterator.remove();
          j = i;
          b1 = b;
        } 
      } 
      i = j;
      b = b1;
      if (paramBoolean1) {
        String str1;
        str = str.substring(str.indexOf("_") + 1);
        StringBuilder stringBuilder = (new StringBuilder()).append("Object with tag ").append(str).append(" has ");
        if (entry != null) {
          str1 = "not ";
        } else {
          str1 = "";
        } 
        Log.i("MemoryLeakUtil", stringBuilder.append(str1).append("been garbage collected.").toString());
        i = j;
        b = b1;
      } 
    } 
    Log.i("MemoryLeakUtil", "summary: collected " + i);
    Log.i("MemoryLeakUtil", "summary: remaining " + b);
    Log.i("MemoryLeakUtil", "-----------------------------------------------------------------");
  }
  
  public static boolean isTrackedObjectCollected(String paramString, boolean paramBoolean) {
    System.gc();
    WeakReference<Object> weakReference = TRACKED_OBJECTS.get(paramString);
    if (weakReference != null) {
      Object object = weakReference.get();
      String str = paramString.substring(paramString.indexOf("_") + 1);
      StringBuilder stringBuilder = (new StringBuilder()).append("Object with tag ").append(str).append(" has ");
      if (object != null) {
        str = "not ";
      } else {
        str = "";
      } 
      Log.i("MemoryLeakUtil", stringBuilder.append(str).append("been garbage collected.").toString());
      if (paramBoolean && object == null)
        TRACKED_OBJECTS.remove(paramString); 
      return (object == null);
    } 
    throw new IllegalArgumentException("key not found");
  }
  
  public static String trackObject(String paramString, Object paramObject) {
    if (paramString == null) {
      paramString = prefixGenerator.incrementAndGet() + "_";
      TRACKED_OBJECTS.put(paramString, new WeakReference(paramObject));
      return paramString;
    } 
    paramString = prefixGenerator.incrementAndGet() + "_" + paramString;
    TRACKED_OBJECTS.put(paramString, new WeakReference(paramObject));
    return paramString;
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/com/google/appinventor/components/runtime/util/MemoryLeakUtil.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */