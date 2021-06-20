package com.google.appinventor.components.runtime;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognitionListener;
import android.speech.SpeechRecognizer;

public class ServiceBasedSpeechRecognizer extends SpeechRecognizerController implements RecognitionListener {
  private ComponentContainer container;
  
  private Intent recognizerIntent;
  
  private String result;
  
  private SpeechRecognizer speech = null;
  
  public ServiceBasedSpeechRecognizer(ComponentContainer paramComponentContainer, Intent paramIntent) {
    this.container = paramComponentContainer;
    this.recognizerIntent = paramIntent;
  }
  
  private int getErrorMessage(int paramInt) {
    switch (paramInt) {
      default:
        return 0;
      case 3:
        return 3801;
      case 5:
        return 3802;
      case 9:
        return 3803;
      case 2:
        return 3804;
      case 1:
        return 3805;
      case 7:
        return 3806;
      case 8:
        return 3807;
      case 4:
        return 3808;
      case 6:
        break;
    } 
    return 3809;
  }
  
  public void onBeginningOfSpeech() {}
  
  public void onBufferReceived(byte[] paramArrayOfbyte) {}
  
  public void onEndOfSpeech() {}
  
  public void onError(int paramInt) {
    paramInt = getErrorMessage(paramInt);
    this.speechListener.onError(paramInt);
  }
  
  public void onEvent(int paramInt, Bundle paramBundle) {}
  
  public void onPartialResults(Bundle paramBundle) {
    if (paramBundle.isEmpty()) {
      this.result = "";
    } else {
      this.result = paramBundle.getStringArrayList("results_recognition").get(0);
    } 
    this.speechListener.onPartialResult(this.result);
  }
  
  public void onReadyForSpeech(Bundle paramBundle) {}
  
  public void onResults(Bundle paramBundle) {
    if (paramBundle.isEmpty()) {
      this.result = "";
    } else {
      this.result = paramBundle.getStringArrayList("results_recognition").get(0);
    } 
    this.speechListener.onResult(this.result);
  }
  
  public void onRmsChanged(float paramFloat) {}
  
  public void start() {
    this.speech = SpeechRecognizer.createSpeechRecognizer((Context)this.container.$context());
    this.speech.setRecognitionListener(this);
    this.speech.startListening(this.recognizerIntent);
  }
  
  public void stop() {
    if (this.speech != null)
      this.speech.stopListening(); 
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/com/google/appinventor/components/runtime/ServiceBasedSpeechRecognizer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */