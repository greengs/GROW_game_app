package androidx.core.os;

import androidx.annotation.RestrictTo;
import java.util.Locale;

@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
final class LocaleHelper {
  static Locale forLanguageTag(String paramString) {
    if (paramString.contains("-")) {
      String[] arrayOfString = paramString.split("-", -1);
      if (arrayOfString.length > 2)
        return new Locale(arrayOfString[0], arrayOfString[1], arrayOfString[2]); 
      if (arrayOfString.length > 1)
        return new Locale(arrayOfString[0], arrayOfString[1]); 
      if (arrayOfString.length == 1)
        return new Locale(arrayOfString[0]); 
    } else if (paramString.contains("_")) {
      String[] arrayOfString = paramString.split("_", -1);
      if (arrayOfString.length > 2)
        return new Locale(arrayOfString[0], arrayOfString[1], arrayOfString[2]); 
      if (arrayOfString.length > 1)
        return new Locale(arrayOfString[0], arrayOfString[1]); 
      if (arrayOfString.length == 1)
        return new Locale(arrayOfString[0]); 
    } else {
      return new Locale(paramString);
    } 
    throw new IllegalArgumentException("Can not parse language tag: [" + paramString + "]");
  }
  
  static String toLanguageTag(Locale paramLocale) {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append(paramLocale.getLanguage());
    String str = paramLocale.getCountry();
    if (str != null && !str.isEmpty()) {
      stringBuilder.append("-");
      stringBuilder.append(paramLocale.getCountry());
    } 
    return stringBuilder.toString();
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/androidx/core/os/LocaleHelper.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */