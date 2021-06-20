package com.google.appinventor.components.runtime;

public interface HandlesEventDispatching {
  boolean canDispatchEvent(Component paramComponent, String paramString);
  
  void dispatchErrorOccurredEvent(Component paramComponent, String paramString, int paramInt, Object... paramVarArgs);
  
  boolean dispatchEvent(Component paramComponent, String paramString1, String paramString2, Object[] paramArrayOfObject);
  
  void dispatchGenericEvent(Component paramComponent, String paramString, boolean paramBoolean, Object[] paramArrayOfObject);
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/com/google/appinventor/components/runtime/HandlesEventDispatching.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */