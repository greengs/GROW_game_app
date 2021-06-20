package com.google.appinventor.components.runtime.util;

import com.google.appinventor.components.runtime.Component;
import com.google.appinventor.components.runtime.Form;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class BulkPermissionRequest {
  private String caller;
  
  private String[] permissions;
  
  private Component source;
  
  protected BulkPermissionRequest(Component paramComponent, String paramString, String... paramVarArgs) {
    this.source = paramComponent;
    this.caller = paramString;
    this.permissions = paramVarArgs;
  }
  
  public final List<String> getPermissions() {
    ArrayList<? super String> arrayList = new ArrayList(this.permissions.length);
    Collections.addAll(arrayList, this.permissions);
    return (List)arrayList;
  }
  
  public void onDenied(String[] paramArrayOfString) {
    Form form = (Form)this.source.getDispatchDelegate();
    int j = paramArrayOfString.length;
    for (int i = 0; i < j; i++) {
      String str = paramArrayOfString[i];
      form.dispatchPermissionDeniedEvent(this.source, this.caller, str);
    } 
  }
  
  public abstract void onGranted();
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/com/google/appinventor/components/runtime/util/BulkPermissionRequest.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */