package com.google.appinventor.components.runtime.collect;

import java.util.HashMap;
import java.util.TreeMap;

public class Maps {
  public static <K, V> HashMap<K, V> newHashMap() {
    return new HashMap<K, V>();
  }
  
  public static <K, V> TreeMap<K, V> newTreeMap() {
    return new TreeMap<K, V>();
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/com/google/appinventor/components/runtime/collect/Maps.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */