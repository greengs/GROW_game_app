package com.google.appinventor.components.runtime;

import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.provider.MediaStore;
import android.util.Log;
import android.webkit.MimeTypeMap;
import com.google.appinventor.components.annotations.DesignerComponent;
import com.google.appinventor.components.annotations.PropertyCategory;
import com.google.appinventor.components.annotations.SimpleObject;
import com.google.appinventor.components.annotations.SimpleProperty;
import com.google.appinventor.components.annotations.UsesPermissions;
import com.google.appinventor.components.common.ComponentCategory;
import com.google.appinventor.components.runtime.util.FileUtil;
import com.google.appinventor.components.runtime.util.MediaUtil;
import com.google.appinventor.components.runtime.util.QUtil;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Comparator;

@DesignerComponent(category = ComponentCategory.MEDIA, description = "A special-purpose button. When the user taps an image picker, the device's image gallery appears, and the user can choose an image. After an image is picked, it is saved, and the <code>Selected</code> property will be the name of the file where the image is stored. In order to not fill up storage, a maximum of 10 images will be stored.  Picking more images will delete previous images, in order from oldest to newest.", version = 5)
@SimpleObject
@UsesPermissions(permissionNames = "android.permission.WRITE_EXTERNAL_STORAGE")
public class ImagePicker extends Picker implements ActivityResultListener {
  private static final String FILE_PREFIX = "picked_image";
  
  private static final String LOG_TAG = "ImagePicker";
  
  private static final String imagePickerDirectoryName = "/Pictures/_app_inventor_image_picker";
  
  private static int maxSavedFiles = 10;
  
  private boolean havePermission = false;
  
  private String selectionSavedImage = "";
  
  private String selectionURI;
  
  public ImagePicker(ComponentContainer paramComponentContainer) {
    super(paramComponentContainer);
  }
  
  private void copyToExternalStorageAndDeleteSource(File paramFile, String paramString) {
    File file2 = null;
    File file3 = new File(QUtil.getExternalStoragePath((Context)this.container.$form()) + "/Pictures/_app_inventor_image_picker");
    File file1 = file2;
    try {
      file3.mkdirs();
      file1 = file2;
      File file = File.createTempFile("picked_image", paramString, file3);
      file1 = file;
      this.selectionSavedImage = file.getPath();
      file1 = file;
      Log.i("ImagePicker", "saved file path is: " + this.selectionSavedImage);
      file1 = file;
      FileUtil.copyFile(paramFile.getAbsolutePath(), file.getAbsolutePath());
      file1 = file;
      Log.i("ImagePicker", "Image was copied to " + this.selectionSavedImage);
    } catch (IOException iOException) {
      String str = "destination is " + this.selectionSavedImage + ": error is " + iOException.getMessage();
      Log.i("ImagePicker", "copyFile failed. " + str);
      this.container.$form().dispatchErrorOccurredEvent(this, "SaveImage", 1601, new Object[] { str });
      this.selectionSavedImage = "";
      file1.delete();
    } 
    paramFile.delete();
    trimDirectory(maxSavedFiles, file3);
  }
  
  private void saveSelectedImageToExternalStorage(String paramString) {
    if (this.container.$form().isDeniedPermission("android.permission.WRITE_EXTERNAL_STORAGE")) {
      this.container.$form().dispatchPermissionDeniedEvent(this, "ImagePicker", "android.permission.WRITE_EXTERNAL_STORAGE");
      return;
    } 
    this.selectionSavedImage = "";
    try {
      File file = MediaUtil.copyMediaToTempFile(this.container.$form(), this.selectionURI);
      Log.i("ImagePicker", "temp file path is: " + file.getPath());
      copyToExternalStorageAndDeleteSource(file, paramString);
      return;
    } catch (IOException iOException) {
      Log.i("ImagePicker", "copyMediaToTempFile failed: " + iOException.getMessage());
      this.container.$form().dispatchErrorOccurredEvent(this, "ImagePicker", 1602, new Object[] { iOException.getMessage() });
      return;
    } 
  }
  
  private void trimDirectory(int paramInt, File paramFile) {
    File[] arrayOfFile = paramFile.listFiles();
    Arrays.sort(arrayOfFile, new Comparator<File>() {
          public int compare(File param1File1, File param1File2) {
            return Long.valueOf(param1File1.lastModified()).compareTo(Long.valueOf(param1File2.lastModified()));
          }
        });
    int j = arrayOfFile.length;
    for (int i = 0; i < j - paramInt; i++)
      arrayOfFile[i].delete(); 
  }
  
  @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "Path to the file containing the image that was selected.")
  public String Selection() {
    return this.selectionSavedImage;
  }
  
  public void click() {
    if (!this.havePermission) {
      this.container.$form().askPermission("android.permission.WRITE_EXTERNAL_STORAGE", new PermissionResultHandler() {
            public void HandlePermissionResponse(String param1String, boolean param1Boolean) {
              if (param1Boolean) {
                ImagePicker.access$002(ImagePicker.this, true);
                ImagePicker.this.click();
                return;
              } 
              ImagePicker.this.container.$form().dispatchPermissionDeniedEvent(ImagePicker.this, "Click", param1String);
            }
          });
      return;
    } 
    super.click();
  }
  
  protected Intent getIntent() {
    return new Intent("android.intent.action.PICK", MediaStore.Images.Media.INTERNAL_CONTENT_URI);
  }
  
  public void resultReturned(int paramInt1, int paramInt2, Intent paramIntent) {
    if (paramInt1 == this.requestCode && paramInt2 == -1) {
      Uri uri = paramIntent.getData();
      this.selectionURI = uri.toString();
      Log.i("ImagePicker", "selectionURI = " + this.selectionURI);
      ContentResolver contentResolver = this.container.$context().getContentResolver();
      MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();
      String str = "." + mimeTypeMap.getExtensionFromMimeType(contentResolver.getType(uri));
      Log.i("ImagePicker", "extension = " + str);
      saveSelectedImageToExternalStorage(str);
      AfterPicking();
    } 
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/com/google/appinventor/components/runtime/ImagePicker.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */