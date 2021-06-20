package androidx.core.text;

import android.os.Build;
import android.text.TextUtils;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import java.util.Locale;

public final class TextUtilsCompat {
  private static final String ARAB_SCRIPT_SUBTAG = "Arab";
  
  private static final String HEBR_SCRIPT_SUBTAG = "Hebr";
  
  private static final Locale ROOT = new Locale("", "");
  
  private static int getLayoutDirectionFromFirstChar(@NonNull Locale paramLocale) {
    switch (Character.getDirectionality(paramLocale.getDisplayName(paramLocale).charAt(0))) {
      default:
        return 0;
      case 1:
      case 2:
        break;
    } 
    return 1;
  }
  
  public static int getLayoutDirectionFromLocale(@Nullable Locale paramLocale) {
    if (Build.VERSION.SDK_INT >= 17)
      return TextUtils.getLayoutDirectionFromLocale(paramLocale); 
    if (paramLocale != null && !paramLocale.equals(ROOT)) {
      String str = ICUCompat.maximizeAndGetScript(paramLocale);
      if (str == null)
        return getLayoutDirectionFromFirstChar(paramLocale); 
      if (str.equalsIgnoreCase("Arab") || str.equalsIgnoreCase("Hebr"))
        return 1; 
    } 
    return 0;
  }
  
  @NonNull
  public static String htmlEncode(@NonNull String paramString) {
    if (Build.VERSION.SDK_INT >= 17)
      return TextUtils.htmlEncode(paramString); 
    StringBuilder stringBuilder = new StringBuilder();
    for (int i = 0;; i++) {
      if (i < paramString.length()) {
        char c = paramString.charAt(i);
        switch (c) {
          default:
            stringBuilder.append(c);
            i++;
            continue;
          case '<':
            stringBuilder.append("&lt;");
            i++;
            continue;
          case '>':
            stringBuilder.append("&gt;");
            i++;
            continue;
          case '&':
            stringBuilder.append("&amp;");
            i++;
            continue;
          case '\'':
            stringBuilder.append("&#39;");
            i++;
            continue;
          case '"':
            break;
        } 
        stringBuilder.append("&quot;");
      } else {
        break;
      } 
    } 
    return stringBuilder.toString();
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/androidx/core/text/TextUtilsCompat.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */