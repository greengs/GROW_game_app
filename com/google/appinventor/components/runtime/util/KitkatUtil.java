package com.google.appinventor.components.runtime.util;

import android.content.Intent;
import android.os.Build;
import android.provider.Telephony;
import android.telephony.SmsMessage;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class KitkatUtil {
  public static List<SmsMessage> getMessagesFromIntent(Intent paramIntent) {
    ArrayList<? super SmsMessage> arrayList = new ArrayList();
    SmsMessage[] arrayOfSmsMessage = Telephony.Sms.Intents.getMessagesFromIntent(paramIntent);
    if (arrayOfSmsMessage != null && arrayOfSmsMessage.length >= 0)
      Collections.addAll(arrayList, arrayOfSmsMessage); 
    return (List)arrayList;
  }
  
  public static int getMinHeight(TextView paramTextView) {
    return (Build.VERSION.SDK_INT >= 16) ? paramTextView.getMinHeight() : paramTextView.getHeight();
  }
  
  public static int getMinWidth(TextView paramTextView) {
    return (Build.VERSION.SDK_INT >= 16) ? paramTextView.getMinWidth() : paramTextView.getWidth();
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/com/google/appinventor/components/runtime/util/KitkatUtil.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */