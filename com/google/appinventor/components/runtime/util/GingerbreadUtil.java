package com.google.appinventor.components.runtime.util;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.nfc.NdefMessage;
import android.nfc.NdefRecord;
import android.nfc.NfcAdapter;
import android.nfc.Tag;
import android.nfc.tech.Ndef;
import android.nfc.tech.NdefFormatable;
import android.os.Parcelable;
import android.util.Log;
import com.google.appinventor.components.runtime.NearField;
import java.io.IOException;
import java.net.CookieHandler;
import java.net.CookieManager;
import java.net.CookieStore;
import java.nio.charset.Charset;
import java.util.Locale;

public class GingerbreadUtil {
  public static boolean clearCookies(CookieHandler paramCookieHandler) {
    if (paramCookieHandler instanceof CookieManager) {
      CookieStore cookieStore = ((CookieManager)paramCookieHandler).getCookieStore();
      if (cookieStore != null) {
        cookieStore.removeAll();
        return true;
      } 
    } 
    return false;
  }
  
  public static NdefRecord createTextRecord(String paramString, boolean paramBoolean) {
    Charset charset;
    byte[] arrayOfByte3 = Locale.getDefault().getLanguage().getBytes(Charset.forName("US-ASCII"));
    if (paramBoolean) {
      charset = Charset.forName("UTF-8");
    } else {
      charset = Charset.forName("UTF-16");
    } 
    byte[] arrayOfByte1 = paramString.getBytes(charset);
    if (paramBoolean) {
      char c1 = Character.MIN_VALUE;
      c1 = (char)(arrayOfByte3.length + c1);
      byte[] arrayOfByte = new byte[arrayOfByte3.length + 1 + arrayOfByte1.length];
      arrayOfByte[0] = (byte)c1;
      System.arraycopy(arrayOfByte3, 0, arrayOfByte, 1, arrayOfByte3.length);
      System.arraycopy(arrayOfByte1, 0, arrayOfByte, arrayOfByte3.length + 1, arrayOfByte1.length);
      return new NdefRecord((short)1, NdefRecord.RTD_TEXT, new byte[0], arrayOfByte);
    } 
    char c = '';
    c = (char)(arrayOfByte3.length + c);
    byte[] arrayOfByte2 = new byte[arrayOfByte3.length + 1 + arrayOfByte1.length];
    arrayOfByte2[0] = (byte)c;
    System.arraycopy(arrayOfByte3, 0, arrayOfByte2, 1, arrayOfByte3.length);
    System.arraycopy(arrayOfByte1, 0, arrayOfByte2, arrayOfByte3.length + 1, arrayOfByte1.length);
    return new NdefRecord((short)1, NdefRecord.RTD_TEXT, new byte[0], arrayOfByte2);
  }
  
  public static void disableNFCAdapter(Activity paramActivity, NfcAdapter paramNfcAdapter) {
    paramNfcAdapter.disableForegroundNdefPush(paramActivity);
  }
  
  public static void enableNFCWriteMode(Activity paramActivity, NfcAdapter paramNfcAdapter, String paramString) {
    paramNfcAdapter.enableForegroundNdefPush(paramActivity, new NdefMessage(new NdefRecord[] { createTextRecord(paramString, true) }));
  }
  
  public static CookieHandler newCookieManager() {
    return new CookieManager();
  }
  
  public static NfcAdapter newNfcAdapter(Context paramContext) {
    return NfcAdapter.getDefaultAdapter(paramContext);
  }
  
  public static void resolveNFCIntent(Intent paramIntent, NearField paramNearField) {
    NdefMessage ndefMessage;
    if ("android.nfc.action.NDEF_DISCOVERED".equals(paramIntent.getAction())) {
      if (paramNearField.ReadMode()) {
        Parcelable[] arrayOfParcelable = paramIntent.getParcelableArrayExtra("android.nfc.extra.NDEF_MESSAGES");
        if (arrayOfParcelable != null) {
          NdefMessage[] arrayOfNdefMessage1 = new NdefMessage[arrayOfParcelable.length];
          int i = 0;
          while (true) {
            arrayOfNdefMessage = arrayOfNdefMessage1;
            if (i < arrayOfParcelable.length) {
              arrayOfNdefMessage1[i] = (NdefMessage)arrayOfParcelable[i];
              i++;
              continue;
            } 
            break;
          } 
        } else {
          byte[] arrayOfByte = new byte[0];
          NdefMessage ndefMessage1 = new NdefMessage(new NdefRecord[] { new NdefRecord((short)5, arrayOfByte, arrayOfByte, arrayOfByte) });
          arrayOfNdefMessage = new NdefMessage[1];
          arrayOfNdefMessage[0] = ndefMessage1;
        } 
        paramNearField.TagRead((new String(arrayOfNdefMessage[0].getRecords()[0].getPayload())).substring(3));
        return;
      } 
      Tag tag = (Tag)arrayOfNdefMessage.getParcelableExtra("android.nfc.extra.TAG");
      NdefMessage[] arrayOfNdefMessage = null;
      if (paramNearField.WriteType() == 1)
        ndefMessage = new NdefMessage(new NdefRecord[] { createTextRecord(paramNearField.TextToWrite(), true) }); 
      if (writeNFCTag(ndefMessage, tag)) {
        paramNearField.TagWritten();
        return;
      } 
      return;
    } 
    Log.e("nearfield", "Unknown intent " + ndefMessage);
  }
  
  public static boolean writeNFCTag(NdefMessage paramNdefMessage, Tag paramTag) {
    int i = (paramNdefMessage.toByteArray()).length;
    try {
      Ndef ndef = Ndef.get(paramTag);
      if (ndef != null) {
        ndef.connect();
        if (!ndef.isWritable())
          return false; 
        if (ndef.getMaxSize() >= i) {
          ndef.writeNdefMessage(paramNdefMessage);
          return true;
        } 
      } else {
        NdefFormatable ndefFormatable = NdefFormatable.get(paramTag);
        if (ndefFormatable != null)
          try {
            ndefFormatable.connect();
            ndefFormatable.format(paramNdefMessage);
            return true;
          } catch (IOException iOException) {
            return false;
          }  
      } 
    } catch (Exception exception) {}
    return false;
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/com/google/appinventor/components/runtime/util/GingerbreadUtil.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */