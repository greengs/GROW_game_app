package com.google.appinventor.components.runtime.util;

public interface AsyncCallbackPair<T> {
  void onFailure(String paramString);
  
  void onSuccess(T paramT);
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/com/google/appinventor/components/runtime/util/AsyncCallbackPair.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */