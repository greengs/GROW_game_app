package com.google.appinventor.components.runtime;

public interface SpeechListener {
  void onError(int paramInt);
  
  void onPartialResult(String paramString);
  
  void onResult(String paramString);
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/com/google/appinventor/components/runtime/SpeechListener.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */