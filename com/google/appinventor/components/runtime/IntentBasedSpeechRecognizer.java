package com.google.appinventor.components.runtime;

import android.content.Intent;

public class IntentBasedSpeechRecognizer extends SpeechRecognizerController implements ActivityResultListener {
  private ComponentContainer container;
  
  private Intent recognizerIntent;
  
  private int requestCode;
  
  private String result;
  
  public IntentBasedSpeechRecognizer(ComponentContainer paramComponentContainer, Intent paramIntent) {
    this.container = paramComponentContainer;
    this.recognizerIntent = paramIntent;
  }
  
  public void resultReturned(int paramInt1, int paramInt2, Intent paramIntent) {
    if (paramInt1 == this.requestCode && paramInt2 == -1) {
      if (paramIntent.hasExtra("android.speech.extra.RESULTS")) {
        this.result = paramIntent.getExtras().getStringArrayList("android.speech.extra.RESULTS").get(0);
      } else {
        this.result = "";
      } 
      this.speechListener.onResult(this.result);
    } 
  }
  
  public void start() {
    if (this.requestCode == 0)
      this.requestCode = this.container.$form().registerForActivityResult(this); 
    this.container.$context().startActivityForResult(this.recognizerIntent, this.requestCode);
  }
  
  public void stop() {}
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/com/google/appinventor/components/runtime/IntentBasedSpeechRecognizer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */