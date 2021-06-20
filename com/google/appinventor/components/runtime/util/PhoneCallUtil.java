package com.google.appinventor.components.runtime.util;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

public class PhoneCallUtil {
  public static void makePhoneCall(Context paramContext, String paramString) {
    if (paramString != null && paramString.length() > 0)
      paramContext.startActivity(new Intent("android.intent.action.CALL", Uri.parse("tel:" + paramString))); 
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/com/google/appinventor/components/runtime/util/PhoneCallUtil.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */