package com.google.appinventor.components.runtime;

import android.content.Intent;
import android.os.Build;
import com.google.appinventor.components.annotations.DesignerComponent;
import com.google.appinventor.components.annotations.DesignerProperty;
import com.google.appinventor.components.annotations.PropertyCategory;
import com.google.appinventor.components.annotations.SimpleEvent;
import com.google.appinventor.components.annotations.SimpleFunction;
import com.google.appinventor.components.annotations.SimpleObject;
import com.google.appinventor.components.annotations.SimpleProperty;
import com.google.appinventor.components.annotations.UsesPermissions;
import com.google.appinventor.components.common.ComponentCategory;

@DesignerComponent(category = ComponentCategory.MEDIA, description = "Component for using Voice Recognition to convert from speech to text", iconName = "images/speechRecognizer.png", nonVisible = true, version = 2)
@SimpleObject
@UsesPermissions(permissionNames = "android.permission.RECORD_AUDIO,android.permission.INTERNET")
public class SpeechRecognizer extends AndroidNonvisibleComponent implements Component, OnClearListener, SpeechListener {
  private final ComponentContainer container;
  
  private boolean havePermission = false;
  
  private Intent recognizerIntent;
  
  private String result;
  
  private SpeechRecognizerController speechRecognizerController;
  
  private boolean useLegacy = true;
  
  public SpeechRecognizer(ComponentContainer paramComponentContainer) {
    super(paramComponentContainer.$form());
    paramComponentContainer.$form().registerForOnClear(this);
    this.container = paramComponentContainer;
    this.result = "";
    this.recognizerIntent = new Intent("android.speech.action.RECOGNIZE_SPEECH");
    this.recognizerIntent.putExtra("android.speech.extra.LANGUAGE_MODEL", "free_form");
    this.recognizerIntent.putExtra("android.speech.extra.PARTIAL_RESULTS", true);
    UseLegacy(this.useLegacy);
  }
  
  @SimpleEvent
  public void AfterGettingText(String paramString, boolean paramBoolean) {
    EventDispatcher.dispatchEvent(this, "AfterGettingText", new Object[] { paramString, Boolean.valueOf(paramBoolean) });
  }
  
  @SimpleEvent
  public void BeforeGettingText() {
    EventDispatcher.dispatchEvent(this, "BeforeGettingText", new Object[0]);
  }
  
  @SimpleFunction
  public void GetText() {
    if (!this.havePermission) {
      this.form.runOnUiThread(new Runnable() {
            public void run() {
              SpeechRecognizer.this.form.askPermission("android.permission.RECORD_AUDIO", new PermissionResultHandler() {
                    public void HandlePermissionResponse(String param2String, boolean param2Boolean) {
                      if (param2Boolean) {
                        SpeechRecognizer.access$002(me, true);
                        me.GetText();
                        return;
                      } 
                      SpeechRecognizer.this.form.dispatchPermissionDeniedEvent(me, "GetText", "android.permission.RECORD_AUDIO");
                    }
                  });
            }
          });
      return;
    } 
    BeforeGettingText();
    this.speechRecognizerController.addListener(this);
    this.speechRecognizerController.start();
  }
  
  @SimpleProperty(category = PropertyCategory.BEHAVIOR)
  public String Result() {
    return this.result;
  }
  
  @SimpleFunction
  public void Stop() {
    if (this.speechRecognizerController != null)
      this.speechRecognizerController.stop(); 
  }
  
  @DesignerProperty(defaultValue = "True", editorType = "boolean")
  @SimpleProperty(description = "If true, a separate dialog is used to recognize speech (the default). If false, speech is recognized in the background and partial results are also provided.")
  public void UseLegacy(boolean paramBoolean) {
    this.useLegacy = paramBoolean;
    Stop();
    if (paramBoolean == true || Build.VERSION.SDK_INT < 8) {
      this.speechRecognizerController = new IntentBasedSpeechRecognizer(this.container, this.recognizerIntent);
      return;
    } 
    this.speechRecognizerController = new ServiceBasedSpeechRecognizer(this.container, this.recognizerIntent);
  }
  
  @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "If true, an app can retain their older behaviour.")
  public boolean UseLegacy() {
    return this.useLegacy;
  }
  
  public void onClear() {
    Stop();
    this.speechRecognizerController = null;
    this.recognizerIntent = null;
  }
  
  public void onError(int paramInt) {
    this.form.dispatchErrorOccurredEvent(this, "GetText", paramInt, new Object[0]);
  }
  
  public void onPartialResult(String paramString) {
    this.result = paramString;
    AfterGettingText(this.result, true);
  }
  
  public void onResult(String paramString) {
    this.result = paramString;
    AfterGettingText(this.result, false);
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/com/google/appinventor/components/runtime/SpeechRecognizer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */