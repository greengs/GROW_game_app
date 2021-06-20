package com.google.appinventor.components.runtime;

import android.content.Intent;
import android.net.Uri;
import android.os.Parcelable;
import android.webkit.MimeTypeMap;
import com.google.appinventor.components.annotations.DesignerComponent;
import com.google.appinventor.components.annotations.SimpleFunction;
import com.google.appinventor.components.annotations.SimpleObject;
import com.google.appinventor.components.annotations.UsesPermissions;
import com.google.appinventor.components.common.ComponentCategory;
import com.google.appinventor.components.runtime.util.NougatUtil;
import java.io.File;

@DesignerComponent(category = ComponentCategory.SOCIAL, description = "Sharing is a non-visible component that enables sharing files and/or messages between your app and other apps installed on a device. The component will display a list of the installed apps that can handle the information provided, and will allow the user to choose one to share the content with, for instance a mail app, a social network app, a texting app, and so on.<br>The file path can be taken directly from other components such as the Camera or the ImagePicker, but can also be specified directly to read from storage. Be aware that different devices treat storage differently, so a few things to try if, for instance, you have a file called arrow.gif in the folder <code>Appinventor/assets</code>, would be: <ul><li><code>\"file:///sdcard/Appinventor/assets/arrow.gif\"</code></li> or <li><code>\"/storage/Appinventor/assets/arrow.gif\"</code></li></ul>", iconName = "images/sharing.png", nonVisible = true, version = 1)
@SimpleObject
@UsesPermissions(permissionNames = "android.permission.READ_EXTERNAL_STORAGE")
public class Sharing extends AndroidNonvisibleComponent {
  public Sharing(ComponentContainer paramComponentContainer) {
    super(paramComponentContainer.$form());
  }
  
  @SimpleFunction(description = "Shares a file through any capable application installed on the phone by displaying a list of the available apps and allowing the user to choose one from the list. The selected app will open with the file inserted on it.")
  public void ShareFile(String paramString) {
    ShareFileWithMessage(paramString, "");
  }
  
  @SimpleFunction(description = "Shares both a file and a message through any capable application installed on the phone by displaying a list of available apps and allowing the user to  choose one from the list. The selected app will open with the file and message inserted on it.")
  public void ShareFileWithMessage(String paramString1, String paramString2) {
    Uri uri;
    String str = paramString1;
    if (!paramString1.startsWith("file://"))
      str = "file://" + paramString1; 
    File file = new File(Uri.parse(str).getPath());
    if (file.isFile()) {
      paramString1 = str.substring(str.lastIndexOf(".") + 1).toLowerCase();
      str = MimeTypeMap.getSingleton().getMimeTypeFromExtension(paramString1);
      paramString1 = str;
      if (str == null)
        paramString1 = "application/octet-stream"; 
      uri = NougatUtil.getPackageUri(this.form, file);
      Intent intent = new Intent("android.intent.action.SEND");
      intent.putExtra("android.intent.extra.STREAM", (Parcelable)uri);
      intent.setFlags(1);
      intent.setType(paramString1);
      if (paramString2.length() > 0)
        intent.putExtra("android.intent.extra.TEXT", paramString2); 
      this.form.startActivity(intent);
      return;
    } 
    paramString1 = "ShareFile";
    if (paramString2.equals(""))
      paramString1 = "ShareFileWithMessage"; 
    this.form.dispatchErrorOccurredEvent(this, paramString1, 2001, new Object[] { uri });
  }
  
  @SimpleFunction(description = "Shares a message through any capable application installed on the phone by displaying a list of the available apps and allowing the user to choose one from the list. The selected app will open with the message inserted on it.")
  public void ShareMessage(String paramString) {
    Intent intent = new Intent("android.intent.action.SEND");
    intent.putExtra("android.intent.extra.TEXT", paramString);
    intent.setType("text/plain");
    this.form.startActivity(intent);
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/com/google/appinventor/components/runtime/Sharing.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */