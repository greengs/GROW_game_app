package com.google.appinventor.components.runtime.util;

import android.telephony.PhoneNumberUtils;
import java.util.Locale;

public final class LollipopUtil {
  public static String formatNumber(String paramString) {
    return PhoneNumberUtils.formatNumber(paramString, Locale.getDefault().getCountry());
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/com/google/appinventor/components/runtime/util/LollipopUtil.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */