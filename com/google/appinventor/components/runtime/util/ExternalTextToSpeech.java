package com.google.appinventor.components.runtime.util;

import android.content.Intent;
import com.google.appinventor.components.runtime.ActivityResultListener;
import com.google.appinventor.components.runtime.ComponentContainer;
import java.util.Locale;

public class ExternalTextToSpeech implements ITextToSpeech, ActivityResultListener {
  private static final String TTS_INTENT = "com.google.tts.makeBagel";
  
  private final ITextToSpeech.TextToSpeechCallback callback;
  
  private final ComponentContainer container;
  
  private int requestCode;
  
  public ExternalTextToSpeech(ComponentContainer paramComponentContainer, ITextToSpeech.TextToSpeechCallback paramTextToSpeechCallback) {
    this.container = paramComponentContainer;
    this.callback = paramTextToSpeechCallback;
  }
  
  public boolean isInitialized() {
    return true;
  }
  
  public int isLanguageAvailable(Locale paramLocale) {
    return -1;
  }
  
  public void onDestroy() {}
  
  public void onResume() {}
  
  public void onStop() {}
  
  public void resultReturned(int paramInt1, int paramInt2, Intent paramIntent) {
    if (paramInt1 == this.requestCode && paramInt2 == -1) {
      paramInt1 = 1;
    } else {
      paramInt1 = 0;
    } 
    if (paramInt1 != 0) {
      this.callback.onSuccess();
      return;
    } 
    this.callback.onFailure();
  }
  
  public void setPitch(float paramFloat) {}
  
  public void setSpeechRate(float paramFloat) {}
  
  public void speak(String paramString, Locale paramLocale) {
    Intent intent = new Intent("com.google.tts.makeBagel");
    intent.setFlags(131072);
    intent.setFlags(8388608);
    intent.setFlags(1073741824);
    intent.putExtra("message", paramString);
    intent.putExtra("language", paramLocale.getISO3Language());
    intent.putExtra("country", paramLocale.getISO3Country());
    if (this.requestCode == 0)
      this.requestCode = this.container.$form().registerForActivityResult(this); 
    this.container.$context().startActivityForResult(intent, this.requestCode);
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/com/google/appinventor/components/runtime/util/ExternalTextToSpeech.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */