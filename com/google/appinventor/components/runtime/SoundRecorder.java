package com.google.appinventor.components.runtime;

import android.media.MediaRecorder;
import android.os.Environment;
import android.util.Log;
import com.google.appinventor.components.annotations.DesignerComponent;
import com.google.appinventor.components.annotations.DesignerProperty;
import com.google.appinventor.components.annotations.PropertyCategory;
import com.google.appinventor.components.annotations.SimpleEvent;
import com.google.appinventor.components.annotations.SimpleFunction;
import com.google.appinventor.components.annotations.SimpleObject;
import com.google.appinventor.components.annotations.SimpleProperty;
import com.google.appinventor.components.annotations.UsesPermissions;
import com.google.appinventor.components.common.ComponentCategory;
import com.google.appinventor.components.runtime.errors.PermissionException;
import com.google.appinventor.components.runtime.util.BulkPermissionRequest;
import com.google.appinventor.components.runtime.util.FileUtil;
import java.io.IOException;

@DesignerComponent(category = ComponentCategory.MEDIA, description = "<p>Multimedia component that records audio.</p>", iconName = "images/soundRecorder.png", nonVisible = true, version = 2)
@SimpleObject
@UsesPermissions(permissionNames = "android.permission.RECORD_AUDIO,android.permission.WRITE_EXTERNAL_STORAGE,android.permission.READ_EXTERNAL_STORAGE")
public final class SoundRecorder extends AndroidNonvisibleComponent implements Component, MediaRecorder.OnErrorListener, MediaRecorder.OnInfoListener {
  private static final String TAG = "SoundRecorder";
  
  private RecordingController controller;
  
  private boolean havePermission = false;
  
  private String savedRecording = "";
  
  public SoundRecorder(ComponentContainer paramComponentContainer) {
    super(paramComponentContainer.$form());
  }
  
  @SimpleEvent(description = "Provides the location of the newly created sound.")
  public void AfterSoundRecorded(String paramString) {
    EventDispatcher.dispatchEvent(this, "AfterSoundRecorded", new Object[] { paramString });
  }
  
  @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "Specifies the path to the file where the recording should be stored. If this property is the empty string, then starting a recording will create a file in an appropriate location.  If the property is not the empty string, it should specify a complete path to a file in an existing directory, including a file name with the extension .3gp.")
  public String SavedRecording() {
    return this.savedRecording;
  }
  
  @DesignerProperty(defaultValue = "", editorType = "string")
  @SimpleProperty
  public void SavedRecording(String paramString) {
    this.savedRecording = paramString;
  }
  
  @SimpleFunction
  public void Start() {
    if (!this.havePermission) {
      this.form.runOnUiThread(new Runnable() {
            public void run() {
              SoundRecorder.this.form.askPermission(new BulkPermissionRequest(me, "Start", new String[] { "android.permission.RECORD_AUDIO", "android.permission.WRITE_EXTERNAL_STORAGE" }) {
                    public void onGranted() {
                      SoundRecorder.access$002(me, true);
                      me.Start();
                    }
                  });
            }
          });
      return;
    } 
    if (this.controller != null) {
      Log.i("SoundRecorder", "Start() called, but already recording to " + this.controller.file);
      return;
    } 
    Log.i("SoundRecorder", "Start() called");
    if (!Environment.getExternalStorageState().equals("mounted")) {
      this.form.dispatchErrorOccurredEvent(this, "Start", 705, new Object[0]);
      return;
    } 
    try {
      this.controller = new RecordingController(this.savedRecording);
      try {
        this.controller.start();
        StartedRecording();
        return;
      } catch (Throwable throwable) {
        this.controller = null;
        this.form.dispatchErrorOccurredEvent(this, "Start", 802, new Object[] { throwable.getMessage() });
      } 
    } catch (PermissionException permissionException) {
      this.form.dispatchPermissionDeniedEvent(this, "Start", permissionException);
      return;
    } catch (Throwable throwable) {
      this.form.dispatchErrorOccurredEvent(this, "Start", 802, new Object[] { throwable.getMessage() });
      return;
    } 
  }
  
  @SimpleEvent(description = "Indicates that the recorder has started, and can be stopped.")
  public void StartedRecording() {
    EventDispatcher.dispatchEvent(this, "StartedRecording", new Object[0]);
  }
  
  @SimpleFunction
  public void Stop() {
    if (this.controller == null) {
      Log.i("SoundRecorder", "Stop() called, but already stopped.");
      return;
    } 
    try {
      Log.i("SoundRecorder", "Stop() called");
      Log.i("SoundRecorder", "stopping");
      this.controller.stop();
      Log.i("SoundRecorder", "Firing AfterSoundRecorded with " + this.controller.file);
      AfterSoundRecorded(this.controller.file);
      return;
    } catch (Throwable throwable) {
      this.form.dispatchErrorOccurredEvent(this, "Stop", 801, new Object[0]);
      return;
    } finally {
      this.controller = null;
      StoppedRecording();
    } 
  }
  
  @SimpleEvent(description = "Indicates that the recorder has stopped, and can be started again.")
  public void StoppedRecording() {
    EventDispatcher.dispatchEvent(this, "StoppedRecording", new Object[0]);
  }
  
  public void onError(MediaRecorder paramMediaRecorder, int paramInt1, int paramInt2) {
    if (this.controller == null || paramMediaRecorder != this.controller.recorder) {
      Log.w("SoundRecorder", "onError called with wrong recorder. Ignoring.");
      return;
    } 
    this.form.dispatchErrorOccurredEvent(this, "onError", 801, new Object[0]);
    try {
      this.controller.stop();
      return;
    } catch (Throwable throwable) {
      Log.w("SoundRecorder", throwable.getMessage());
      return;
    } finally {
      this.controller = null;
      StoppedRecording();
    } 
  }
  
  public void onInfo(MediaRecorder paramMediaRecorder, int paramInt1, int paramInt2) {
    // Byte code:
    //   0: aload_0
    //   1: getfield controller : Lcom/google/appinventor/components/runtime/SoundRecorder$RecordingController;
    //   4: ifnull -> 18
    //   7: aload_1
    //   8: aload_0
    //   9: getfield controller : Lcom/google/appinventor/components/runtime/SoundRecorder$RecordingController;
    //   12: getfield recorder : Landroid/media/MediaRecorder;
    //   15: if_acmpeq -> 27
    //   18: ldc 'SoundRecorder'
    //   20: ldc 'onInfo called with wrong recorder. Ignoring.'
    //   22: invokestatic w : (Ljava/lang/String;Ljava/lang/String;)I
    //   25: pop
    //   26: return
    //   27: iload_2
    //   28: lookupswitch default -> 64, 1 -> 65, 800 -> 110, 801 -> 130
    //   64: return
    //   65: aload_0
    //   66: getfield form : Lcom/google/appinventor/components/runtime/Form;
    //   69: aload_0
    //   70: ldc 'recording'
    //   72: sipush #801
    //   75: iconst_0
    //   76: anewarray java/lang/Object
    //   79: invokevirtual dispatchErrorOccurredEvent : (Lcom/google/appinventor/components/runtime/Component;Ljava/lang/String;I[Ljava/lang/Object;)V
    //   82: ldc 'SoundRecorder'
    //   84: ldc 'Recoverable condition while recording. Will attempt to stop normally.'
    //   86: invokestatic i : (Ljava/lang/String;Ljava/lang/String;)I
    //   89: pop
    //   90: aload_0
    //   91: getfield controller : Lcom/google/appinventor/components/runtime/SoundRecorder$RecordingController;
    //   94: getfield recorder : Landroid/media/MediaRecorder;
    //   97: invokevirtual stop : ()V
    //   100: aload_0
    //   101: aconst_null
    //   102: putfield controller : Lcom/google/appinventor/components/runtime/SoundRecorder$RecordingController;
    //   105: aload_0
    //   106: invokevirtual StoppedRecording : ()V
    //   109: return
    //   110: aload_0
    //   111: getfield form : Lcom/google/appinventor/components/runtime/Form;
    //   114: aload_0
    //   115: ldc 'recording'
    //   117: sipush #804
    //   120: iconst_0
    //   121: anewarray java/lang/Object
    //   124: invokevirtual dispatchErrorOccurredEvent : (Lcom/google/appinventor/components/runtime/Component;Ljava/lang/String;I[Ljava/lang/Object;)V
    //   127: goto -> 82
    //   130: aload_0
    //   131: getfield form : Lcom/google/appinventor/components/runtime/Form;
    //   134: aload_0
    //   135: ldc 'recording'
    //   137: sipush #805
    //   140: iconst_0
    //   141: anewarray java/lang/Object
    //   144: invokevirtual dispatchErrorOccurredEvent : (Lcom/google/appinventor/components/runtime/Component;Ljava/lang/String;I[Ljava/lang/Object;)V
    //   147: goto -> 82
    //   150: astore_1
    //   151: ldc 'SoundRecorder'
    //   153: ldc 'SoundRecorder was not in a recording state.'
    //   155: aload_1
    //   156: invokestatic i : (Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   159: pop
    //   160: aload_0
    //   161: getfield form : Lcom/google/appinventor/components/runtime/Form;
    //   164: aload_0
    //   165: ldc 'Stop'
    //   167: sipush #803
    //   170: iconst_0
    //   171: anewarray java/lang/Object
    //   174: invokevirtual dispatchErrorOccurredEventDialog : (Lcom/google/appinventor/components/runtime/Component;Ljava/lang/String;I[Ljava/lang/Object;)V
    //   177: aload_0
    //   178: aconst_null
    //   179: putfield controller : Lcom/google/appinventor/components/runtime/SoundRecorder$RecordingController;
    //   182: aload_0
    //   183: invokevirtual StoppedRecording : ()V
    //   186: return
    //   187: astore_1
    //   188: aload_0
    //   189: aconst_null
    //   190: putfield controller : Lcom/google/appinventor/components/runtime/SoundRecorder$RecordingController;
    //   193: aload_0
    //   194: invokevirtual StoppedRecording : ()V
    //   197: aload_1
    //   198: athrow
    // Exception table:
    //   from	to	target	type
    //   82	100	150	java/lang/IllegalStateException
    //   82	100	187	finally
    //   151	177	187	finally
  }
  
  private class RecordingController {
    final String file;
    
    final MediaRecorder recorder;
    
    RecordingController(String param1String) throws IOException {
      String str = param1String;
      if (param1String.equals(""))
        str = FileUtil.getRecordingFile(SoundRecorder.this.form, "3gp").getAbsolutePath(); 
      this.file = str;
      this.recorder = new MediaRecorder();
      this.recorder.setAudioSource(1);
      this.recorder.setOutputFormat(1);
      this.recorder.setAudioEncoder(1);
      Log.i("SoundRecorder", "Setting output file to " + this.file);
      this.recorder.setOutputFile(this.file);
      Log.i("SoundRecorder", "preparing");
      this.recorder.prepare();
      this.recorder.setOnErrorListener(SoundRecorder.this);
      this.recorder.setOnInfoListener(SoundRecorder.this);
    }
    
    void start() throws IllegalStateException {
      Log.i("SoundRecorder", "starting");
      try {
        this.recorder.start();
        return;
      } catch (IllegalStateException illegalStateException) {
        Log.e("SoundRecorder", "got IllegalStateException. Are there two recorders running?", illegalStateException);
        throw new IllegalStateException("Is there another recording running?");
      } 
    }
    
    void stop() {
      this.recorder.setOnErrorListener(null);
      this.recorder.setOnInfoListener(null);
      this.recorder.stop();
      this.recorder.reset();
      this.recorder.release();
    }
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/com/google/appinventor/components/runtime/SoundRecorder.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */