package com.google.appinventor.components.runtime;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.os.Parcelable;
import android.provider.MediaStore;
import android.util.Log;
import com.google.appinventor.components.annotations.DesignerComponent;
import com.google.appinventor.components.annotations.PropertyCategory;
import com.google.appinventor.components.annotations.SimpleEvent;
import com.google.appinventor.components.annotations.SimpleFunction;
import com.google.appinventor.components.annotations.SimpleObject;
import com.google.appinventor.components.annotations.SimpleProperty;
import com.google.appinventor.components.annotations.UsesPermissions;
import com.google.appinventor.components.common.ComponentCategory;
import com.google.appinventor.components.runtime.util.BulkPermissionRequest;
import com.google.appinventor.components.runtime.util.NougatUtil;
import com.google.appinventor.components.runtime.util.QUtil;
import java.io.File;
import java.util.Date;

@DesignerComponent(category = ComponentCategory.MEDIA, description = "A component to take a picture using the device's camera. After the picture is taken, the name of the file on the phone containing the picture is available as an argument to the AfterPicture event. The file name can be used, for example, to set the Picture property of an Image component.", iconName = "images/camera.png", nonVisible = true, version = 3)
@SimpleObject
@UsesPermissions(permissionNames = "android.permission.WRITE_EXTERNAL_STORAGE, android.permission.READ_EXTERNAL_STORAGE,android.permission.CAMERA")
public class Camera extends AndroidNonvisibleComponent implements ActivityResultListener, Component {
  private static final String CAMERA_INTENT = "android.media.action.IMAGE_CAPTURE";
  
  private static final String CAMERA_OUTPUT = "output";
  
  private final ComponentContainer container;
  
  private boolean havePermission = false;
  
  private Uri imageFile;
  
  private int requestCode;
  
  private boolean useFront;
  
  public Camera(ComponentContainer paramComponentContainer) {
    super(paramComponentContainer.$form());
    this.container = paramComponentContainer;
    UseFront(false);
  }
  
  private void deleteFile(Uri paramUri) {
    File file = new File(paramUri.getPath());
    try {
      if (file.delete()) {
        Log.i("CameraComponent", "Deleted file " + paramUri.toString());
        return;
      } 
      Log.i("CameraComponent", "Could not delete file " + paramUri.toString());
      return;
    } catch (SecurityException securityException) {
      Log.i("CameraComponent", "Got security exception trying to delete file " + paramUri.toString());
      return;
    } 
  }
  
  private void scanFileToAdd(File paramFile) {
    Intent intent = new Intent("android.intent.action.MEDIA_SCANNER_SCAN_FILE");
    intent.setData(NougatUtil.getPackageUri(this.form, paramFile));
    this.container.$context().getApplicationContext().sendBroadcast(intent);
  }
  
  @SimpleEvent
  public void AfterPicture(String paramString) {
    EventDispatcher.dispatchEvent(this, "AfterPicture", new Object[] { paramString });
  }
  
  @SimpleFunction
  public void TakePicture() {
    Uri uri;
    if (!this.havePermission) {
      this.form.askPermission(new BulkPermissionRequest(this, "TakePicture", new String[] { "android.permission.CAMERA", "android.permission.WRITE_EXTERNAL_STORAGE" }) {
            public void onGranted() {
              Camera.access$002(me, true);
              me.TakePicture();
            }
          });
      return;
    } 
    String str = Environment.getExternalStorageState();
    if ("mounted".equals(str)) {
      Log.i("CameraComponent", "External storage is available and writable");
      File file = new File(QUtil.getExternalStorageDir((Context)this.form), "Pictures/");
      if (!file.exists())
        file.mkdir(); 
      file = new File(QUtil.getExternalStorageDir((Context)this.form), "Pictures/app_inventor_" + (new Date()).getTime() + ".jpg");
      this.imageFile = Uri.fromFile(file);
      ContentValues contentValues = new ContentValues();
      contentValues.put("_data", this.imageFile.getPath());
      contentValues.put("mime_type", "image/jpeg");
      contentValues.put("title", this.imageFile.getLastPathSegment());
      if (this.requestCode == 0)
        this.requestCode = this.form.registerForActivityResult(this); 
      if (Build.VERSION.SDK_INT < 24) {
        uri = this.container.$context().getContentResolver().insert(MediaStore.Images.Media.INTERNAL_CONTENT_URI, contentValues);
      } else {
        uri = NougatUtil.getPackageUri(this.form, (File)uri);
      } 
      Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
      intent.putExtra("output", (Parcelable)uri);
      if (this.useFront)
        intent.putExtra("android.intent.extras.CAMERA_FACING", 1); 
      this.container.$context().startActivityForResult(intent, this.requestCode);
      return;
    } 
    if ("mounted_ro".equals(uri)) {
      this.form.dispatchErrorOccurredEvent(this, "TakePicture", 704, new Object[0]);
      return;
    } 
    this.form.dispatchErrorOccurredEvent(this, "TakePicture", 705, new Object[0]);
  }
  
  @SimpleProperty(description = "Specifies whether the front-facing camera should be used (when available). If the device does not have a front-facing camera, this option will be ignored and the camera will open normally.")
  @Deprecated
  public void UseFront(boolean paramBoolean) {
    this.useFront = paramBoolean;
  }
  
  @SimpleProperty(category = PropertyCategory.BEHAVIOR)
  @Deprecated
  public boolean UseFront() {
    return this.useFront;
  }
  
  public void resultReturned(int paramInt1, int paramInt2, Intent paramIntent) {
    Log.i("CameraComponent", "Returning result. Request code = " + paramInt1 + ", result code = " + paramInt2);
    if (paramInt1 == this.requestCode && paramInt2 == -1) {
      File file = new File(this.imageFile.getPath());
      if (file.length() != 0L) {
        scanFileToAdd(file);
        AfterPicture(this.imageFile.toString());
        return;
      } 
      deleteFile(this.imageFile);
      if (paramIntent != null && paramIntent.getData() != null) {
        Uri uri = paramIntent.getData();
        Log.i("CameraComponent", "Calling Camera.AfterPicture with image path " + uri.toString());
        AfterPicture(uri.toString());
        return;
      } 
      Log.i("CameraComponent", "Couldn't find an image file from the Camera result");
      this.form.dispatchErrorOccurredEvent(this, "TakePicture", 201, new Object[0]);
      return;
    } 
    deleteFile(this.imageFile);
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/com/google/appinventor/components/runtime/Camera.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */