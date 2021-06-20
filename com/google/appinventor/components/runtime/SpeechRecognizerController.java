package com.google.appinventor.components.runtime;

public abstract class SpeechRecognizerController {
  SpeechListener speechListener;
  
  void addListener(SpeechListener paramSpeechListener) {
    this.speechListener = paramSpeechListener;
  }
  
  void start() {}
  
  void stop() {}
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/com/google/appinventor/components/runtime/SpeechRecognizerController.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */