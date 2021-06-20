package com.google.appinventor.components.runtime.util;

import androidx.annotation.NonNull;
import java.util.Iterator;

public interface YailObject<T> extends Iterable<T> {
  Object getObject(int paramInt);
  
  boolean isEmpty();
  
  @NonNull
  Iterator<T> iterator();
  
  int size();
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/com/google/appinventor/components/runtime/util/YailObject.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */