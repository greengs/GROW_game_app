package com.google.appinventor.components.runtime.util;

import java.util.Locale;

public interface ITextToSpeech {
  boolean isInitialized();
  
  int isLanguageAvailable(Locale paramLocale);
  
  void onDestroy();
  
  void onResume();
  
  void onStop();
  
  void setPitch(float paramFloat);
  
  void setSpeechRate(float paramFloat);
  
  void speak(String paramString, Locale paramLocale);
  
  public static interface TextToSpeechCallback {
    void onFailure();
    
    void onSuccess();
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/com/google/appinventor/components/runtime/util/ITextToSpeech.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */