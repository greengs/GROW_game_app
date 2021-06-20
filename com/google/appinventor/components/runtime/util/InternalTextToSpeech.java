package com.google.appinventor.components.runtime.util;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import java.util.HashMap;
import java.util.Locale;

public class InternalTextToSpeech implements ITextToSpeech {
  private static final String LOG_TAG = "InternalTTS";
  
  private final Activity activity;
  
  private final ITextToSpeech.TextToSpeechCallback callback;
  
  private volatile boolean isTtsInitialized;
  
  private Handler mHandler = new Handler();
  
  private int nextUtteranceId = 1;
  
  private TextToSpeech tts;
  
  private int ttsMaxRetries = 20;
  
  private int ttsRetryDelay = 500;
  
  public InternalTextToSpeech(Activity paramActivity, ITextToSpeech.TextToSpeechCallback paramTextToSpeechCallback) {
    this.activity = paramActivity;
    this.callback = paramTextToSpeechCallback;
    initializeTts();
  }
  
  private void initializeTts() {
    if (this.tts == null) {
      Log.d("InternalTTS", "INTERNAL TTS is reinitializing");
      this.tts = new TextToSpeech((Context)this.activity, new TextToSpeech.OnInitListener() {
            public void onInit(int param1Int) {
              if (param1Int == 0)
                InternalTextToSpeech.access$002(InternalTextToSpeech.this, true); 
            }
          });
    } 
  }
  
  private void speak(final String message, Locale paramLocale, final int retries) {
    final HashMap<Object, Object> loc;
    Log.d("InternalTTS", "InternalTTS speak called, message = " + message);
    if (retries > this.ttsMaxRetries) {
      Log.d("InternalTTS", "max number of speak retries exceeded: speak will fail");
      this.callback.onFailure();
    } 
    if (this.isTtsInitialized) {
      Log.d("InternalTTS", "TTS initialized after " + retries + " retries.");
      this.tts.setLanguage(paramLocale);
      this.tts.setOnUtteranceCompletedListener(new TextToSpeech.OnUtteranceCompletedListener() {
            public void onUtteranceCompleted(String param1String) {
              InternalTextToSpeech.this.activity.runOnUiThread(new Runnable() {
                    public void run() {
                      InternalTextToSpeech.this.callback.onSuccess();
                    }
                  });
            }
          });
      hashMap = new HashMap<Object, Object>();
      retries = this.nextUtteranceId;
      this.nextUtteranceId = retries + 1;
      hashMap.put("utteranceId", Integer.toString(retries));
      TextToSpeech textToSpeech1 = this.tts;
      TextToSpeech textToSpeech2 = this.tts;
      if (textToSpeech1.speak(message, 0, hashMap) == -1) {
        Log.d("InternalTTS", "speak called and tts.speak result was an error");
        this.callback.onFailure();
      } 
      return;
    } 
    Log.d("InternalTTS", "speak called when TTS not initialized");
    this.mHandler.postDelayed(new Runnable() {
          public void run() {
            Log.d("InternalTTS", "delaying call to speak.  Retries is: " + retries + " Message is: " + message);
            InternalTextToSpeech.this.speak(message, loc, retries + 1);
          }
        }this.ttsRetryDelay);
  }
  
  public boolean isInitialized() {
    return this.isTtsInitialized;
  }
  
  public int isLanguageAvailable(Locale paramLocale) {
    return this.tts.isLanguageAvailable(paramLocale);
  }
  
  public void onDestroy() {
    Log.d("InternalTTS", "Internal TTS got onDestroy");
    if (this.tts != null) {
      this.tts.shutdown();
      this.isTtsInitialized = false;
      this.tts = null;
    } 
  }
  
  public void onResume() {
    Log.d("InternalTTS", "Internal TTS got onResume");
    initializeTts();
  }
  
  public void onStop() {
    Log.d("InternalTTS", "Internal TTS got onStop");
  }
  
  public void setPitch(float paramFloat) {
    this.tts.setPitch(paramFloat);
  }
  
  public void setSpeechRate(float paramFloat) {
    this.tts.setSpeechRate(paramFloat);
  }
  
  public void speak(String paramString, Locale paramLocale) {
    Log.d("InternalTTS", "Internal TTS got speak");
    speak(paramString, paramLocale, 0);
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/com/google/appinventor/components/runtime/util/InternalTextToSpeech.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */