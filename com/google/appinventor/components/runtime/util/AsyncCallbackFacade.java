package com.google.appinventor.components.runtime.util;

public abstract class AsyncCallbackFacade<S, T> implements AsyncCallbackPair<S> {
  protected final AsyncCallbackPair<T> callback;
  
  public AsyncCallbackFacade(AsyncCallbackPair<T> paramAsyncCallbackPair) {
    this.callback = paramAsyncCallbackPair;
  }
  
  public void onFailure(String paramString) {
    this.callback.onFailure(paramString);
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/com/google/appinventor/components/runtime/util/AsyncCallbackFacade.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */