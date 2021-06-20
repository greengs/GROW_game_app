package com.google.appinventor.components.runtime;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.nfc.NfcAdapter;
import android.util.Log;
import com.google.appinventor.components.annotations.DesignerComponent;
import com.google.appinventor.components.annotations.DesignerProperty;
import com.google.appinventor.components.annotations.PropertyCategory;
import com.google.appinventor.components.annotations.SimpleEvent;
import com.google.appinventor.components.annotations.SimpleObject;
import com.google.appinventor.components.annotations.SimpleProperty;
import com.google.appinventor.components.annotations.UsesPermissions;
import com.google.appinventor.components.common.ComponentCategory;
import com.google.appinventor.components.runtime.util.GingerbreadUtil;
import com.google.appinventor.components.runtime.util.SdkLevel;

@DesignerComponent(category = ComponentCategory.SENSORS, description = "<p>Non-visible component to provide NFC capabilities.  For now this component supports the reading and writing of text tags only (if supported by the device)</p><p>In order to read and write text tags, the component must have its <code>ReadMode</code> property set to True or False respectively.</p><p><strong>Note:</strong> This component will only work on Screen1 of any App Inventor app.</p>", iconName = "images/nearfield.png", nonVisible = true, version = 1)
@SimpleObject
@UsesPermissions(permissionNames = "android.permission.NFC")
public class NearField extends AndroidNonvisibleComponent implements OnStopListener, OnResumeListener, OnPauseListener, OnNewIntentListener, Deleteable {
  private static final String TAG = "nearfield";
  
  private Activity activity;
  
  private NfcAdapter nfcAdapter;
  
  private boolean readMode = true;
  
  protected int requestCode;
  
  private String tagContent = "";
  
  private String textToWrite = "";
  
  private int writeType;
  
  public NearField(ComponentContainer paramComponentContainer) {
    super(paramComponentContainer.$form());
    this.activity = paramComponentContainer.$context();
    this.writeType = 1;
    if (SdkLevel.getLevel() >= 9) {
      NfcAdapter nfcAdapter = GingerbreadUtil.newNfcAdapter((Context)this.activity);
    } else {
      paramComponentContainer = null;
    } 
    this.nfcAdapter = (NfcAdapter)paramComponentContainer;
    this.form.registerForOnResume(this);
    this.form.registerForOnNewIntent(this);
    this.form.registerForOnPause(this);
    Log.d("nearfield", "Nearfield component created");
  }
  
  @SimpleProperty(category = PropertyCategory.BEHAVIOR)
  public String LastMessage() {
    Log.d("nearfield", "String message method stared");
    return this.tagContent;
  }
  
  @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "Specifies whether the NFC hardware should operate in read or write mode.")
  public void ReadMode(boolean paramBoolean) {
    Log.d("nearfield", "Read mode set to" + paramBoolean);
    this.readMode = paramBoolean;
    if (!this.readMode && SdkLevel.getLevel() >= 9)
      GingerbreadUtil.enableNFCWriteMode(this.activity, this.nfcAdapter, this.textToWrite); 
  }
  
  @DesignerProperty(defaultValue = "True", editorType = "boolean")
  @SimpleProperty(category = PropertyCategory.BEHAVIOR)
  public boolean ReadMode() {
    Log.d("nearfield", "boolean method stared");
    return this.readMode;
  }
  
  @SimpleEvent
  public void TagRead(String paramString) {
    Log.d("nearfield", "Tag read: got message " + paramString);
    this.tagContent = paramString;
    EventDispatcher.dispatchEvent(this, "TagRead", new Object[] { paramString });
  }
  
  @SimpleEvent
  public void TagWritten() {
    Log.d("nearfield", "Tag written: " + this.textToWrite);
    EventDispatcher.dispatchEvent(this, "TagWritten", new Object[0]);
  }
  
  @SimpleProperty(category = PropertyCategory.BEHAVIOR)
  public String TextToWrite() {
    Log.d("nearfield", "String message method stared");
    return this.textToWrite;
  }
  
  @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "Specifies the content that will be written to the tag when in write mode. This method has no effect if ReadMode is true.")
  public void TextToWrite(String paramString) {
    Log.d("nearfield", "Text to write set to" + paramString);
    this.textToWrite = paramString;
    if (!this.readMode && this.writeType == 1 && SdkLevel.getLevel() >= 9)
      GingerbreadUtil.enableNFCWriteMode(this.activity, this.nfcAdapter, this.textToWrite); 
  }
  
  @SimpleProperty(category = PropertyCategory.BEHAVIOR)
  public int WriteType() {
    return this.writeType;
  }
  
  public void onDelete() {}
  
  public void onNewIntent(Intent paramIntent) {
    Log.d("nearfield", "Nearfield on onNewIntent.  Intent is: " + paramIntent);
    resolveIntent(paramIntent);
  }
  
  public void onPause() {
    Log.d("nearfield", "OnPause method started.");
    if (this.nfcAdapter != null)
      GingerbreadUtil.disableNFCAdapter(this.activity, this.nfcAdapter); 
  }
  
  public void onResume() {
    Intent intent = this.activity.getIntent();
    Log.d("nearfield", "Nearfield on onResume.  Intent is: " + intent);
  }
  
  public void onStop() {}
  
  void resolveIntent(Intent paramIntent) {
    Log.d("nearfield", "resolve intent. Intent is: " + paramIntent);
    if (SdkLevel.getLevel() >= 9)
      GingerbreadUtil.resolveNFCIntent(paramIntent, this); 
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/com/google/appinventor/components/runtime/NearField.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */