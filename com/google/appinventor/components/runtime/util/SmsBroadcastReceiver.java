package com.google.appinventor.components.runtime.util;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.PhoneNumberUtils;
import android.telephony.SmsMessage;
import android.util.Log;
import androidx.core.app.NotificationCompat;
import com.google.appinventor.components.runtime.ReplForm;
import com.google.appinventor.components.runtime.Texting;
import java.util.Iterator;

public class SmsBroadcastReceiver extends BroadcastReceiver {
  public static final int NOTIFICATION_ID = 8647;
  
  public static final String TAG = "SmsBroadcastReceiver";
  
  private String getMessage(Intent paramIntent) {
    try {
      if (paramIntent.getAction().equals("com.google.android.apps.googlevoice.SMS_RECEIVED"))
        return paramIntent.getExtras().getString("com.google.android.apps.googlevoice.TEXT"); 
      if (SdkLevel.getLevel() >= 19) {
        StringBuilder stringBuilder1 = new StringBuilder();
        for (SmsMessage smsMessage : KitkatUtil.getMessagesFromIntent(paramIntent)) {
          if (smsMessage != null)
            stringBuilder1.append(smsMessage.getMessageBody()); 
        } 
        return stringBuilder1.toString();
      } 
    } catch (NullPointerException nullPointerException) {
      Log.w("SmsBroadcastReceiver", "Unable to retrieve message body from SmsMessage", nullPointerException);
      return "";
    } 
    StringBuilder stringBuilder = new StringBuilder();
    Object[] arrayOfObject = (Object[])nullPointerException.getExtras().get("pdus");
    int j = arrayOfObject.length;
    for (int i = 0; i < j; i++)
      stringBuilder.append(SmsMessage.createFromPdu((byte[])arrayOfObject[i]).getMessageBody()); 
    return stringBuilder.toString();
  }
  
  private String getPhoneNumber(Intent paramIntent) {
    String str2 = "";
    String str1 = str2;
    try {
      String str;
      SmsMessage smsMessage;
      if (paramIntent.getAction().equals("com.google.android.apps.googlevoice.SMS_RECEIVED")) {
        str1 = str2;
        str = paramIntent.getExtras().getString("com.google.android.apps.googlevoice.PHONE_NUMBER");
        str1 = str;
        return PhoneNumberUtils.formatNumber(str);
      } 
      str1 = str2;
      if (SdkLevel.getLevel() >= 19) {
        str1 = str2;
        Iterator<SmsMessage> iterator = KitkatUtil.getMessagesFromIntent((Intent)str).iterator();
        str = str2;
        while (true) {
          str1 = str;
          str2 = str;
          if (iterator.hasNext()) {
            str1 = str;
            smsMessage = iterator.next();
            if (smsMessage != null) {
              str1 = str;
              str = smsMessage.getOriginatingAddress();
              str1 = str;
              if (SdkLevel.getLevel() >= 21) {
                str1 = str;
                str = LollipopUtil.formatNumber(str);
                continue;
              } 
              str1 = str;
              str = PhoneNumberUtils.formatNumber(str);
            } 
            continue;
          } 
          break;
        } 
      } else {
        SmsMessage smsMessage1 = smsMessage;
        Object[] arrayOfObject = (Object[])str.getExtras().get("pdus");
        smsMessage1 = smsMessage;
        int j = arrayOfObject.length;
        int i = 0;
        smsMessage1 = smsMessage;
        while (true) {
          smsMessage = smsMessage1;
          if (i < j) {
            str = SmsMessage.createFromPdu((byte[])arrayOfObject[i]).getOriginatingAddress();
            str1 = str;
            str = PhoneNumberUtils.formatNumber(str);
            str1 = str;
            i++;
            continue;
          } 
          break;
        } 
      } 
    } catch (NullPointerException nullPointerException) {
      Log.w("SmsBroadcastReceiver", "Unable to retrieve originating address from SmsMessage", nullPointerException);
      str2 = str1;
    } 
    return str2;
  }
  
  private boolean isRepl(Context paramContext) {
    boolean bool = false;
    try {
      String str = paramContext.getPackageName();
      boolean bool1 = Class.forName(str + ".Screen1").getSuperclass().equals(ReplForm.class);
      if (bool1)
        bool = true; 
      return bool;
    } catch (ClassNotFoundException classNotFoundException) {
      classNotFoundException.printStackTrace();
      return false;
    } 
  }
  
  private void sendNotification(Context paramContext, String paramString1, String paramString2) {
    Log.i("SmsBroadcastReceiver", "sendingNotification " + paramString1 + ":" + paramString2);
    String str = paramContext.getPackageName();
    Log.i("SmsBroadcastReceiver", "Package name : " + str);
    try {
      str = str + ".Screen1";
      Intent intent = new Intent(paramContext, Class.forName(str));
      try {
        intent.setAction("android.intent.action.MAIN");
        intent.addCategory("android.intent.category.LAUNCHER");
        intent.addFlags(805306368);
        PendingIntent pendingIntent = PendingIntent.getActivity(paramContext, 0, intent, 134217728);
        ((NotificationManager)paramContext.getSystemService("notification")).notify(null, 8647, (new NotificationCompat.Builder(paramContext)).setSmallIcon(17301648).setTicker(paramString1 + " : " + paramString2).setWhen(System.currentTimeMillis()).setAutoCancel(true).setDefaults(1).setContentTitle("Sms from " + paramString1).setContentText(paramString2).setContentIntent(pendingIntent).setNumber(Texting.getCachedMsgCount()).build());
        Log.i("SmsBroadcastReceiver", "Notification sent, classname: " + str);
        return;
      } catch (ClassNotFoundException null) {}
    } catch (ClassNotFoundException classNotFoundException) {}
    classNotFoundException.printStackTrace();
  }
  
  public void onReceive(Context paramContext, Intent paramIntent) {
    Log.i("SmsBroadcastReceiver", "onReceive");
    String str2 = getPhoneNumber(paramIntent);
    String str1 = getMessage(paramIntent);
    Log.i("SmsBroadcastReceiver", "Received " + str2 + " : " + str1);
    int i = Texting.isReceivingEnabled(paramContext);
    if (i == 1) {
      Log.i("SmsBroadcastReceiver", (paramContext.getApplicationInfo()).packageName + " Receiving is not enabled, ignoring message.");
      return;
    } 
    if ((i == 2 || isRepl(paramContext)) && !Texting.isRunning()) {
      Log.i("SmsBroadcastReceiver", (paramContext.getApplicationInfo()).packageName + " Texting isn't running, and either receivingEnabled is FOREGROUND or we are the repl.");
      return;
    } 
    Texting.handledReceivedMessage(paramContext, str2, str1);
    if (Texting.isRunning()) {
      Log.i("SmsBroadcastReceiver", (paramContext.getApplicationInfo()).packageName + " App in Foreground, delivering message.");
      return;
    } 
    Log.i("SmsBroadcastReceiver", (paramContext.getApplicationInfo()).packageName + " Texting isn't running, but receivingEnabled == 2, sending notification.");
    sendNotification(paramContext, str2, str1);
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/com/google/appinventor/components/runtime/util/SmsBroadcastReceiver.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */