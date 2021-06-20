package androidx.core.content;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import java.util.ArrayList;

public final class MimeTypeFilter {
  @Nullable
  public static String matches(@Nullable String paramString, @NonNull String[] paramArrayOfString) {
    if (paramString == null)
      return null; 
    String[] arrayOfString = paramString.split("/");
    int j = paramArrayOfString.length;
    int i = 0;
    while (i < j) {
      String str = paramArrayOfString[i];
      paramString = str;
      if (!mimeTypeAgainstFilter(arrayOfString, str.split("/"))) {
        i++;
        continue;
      } 
      return paramString;
    } 
    return null;
  }
  
  @Nullable
  public static String matches(@Nullable String[] paramArrayOfString, @NonNull String paramString) {
    if (paramArrayOfString == null)
      return null; 
    String[] arrayOfString = paramString.split("/");
    int j = paramArrayOfString.length;
    int i = 0;
    while (i < j) {
      String str = paramArrayOfString[i];
      paramString = str;
      if (!mimeTypeAgainstFilter(str.split("/"), arrayOfString)) {
        i++;
        continue;
      } 
      return paramString;
    } 
    return null;
  }
  
  public static boolean matches(@Nullable String paramString1, @NonNull String paramString2) {
    return (paramString1 == null) ? false : mimeTypeAgainstFilter(paramString1.split("/"), paramString2.split("/"));
  }
  
  @NonNull
  public static String[] matchesMany(@Nullable String[] paramArrayOfString, @NonNull String paramString) {
    int i = 0;
    if (paramArrayOfString == null)
      return new String[0]; 
    ArrayList<String> arrayList = new ArrayList();
    String[] arrayOfString = paramString.split("/");
    int j = paramArrayOfString.length;
    while (i < j) {
      String str = paramArrayOfString[i];
      if (mimeTypeAgainstFilter(str.split("/"), arrayOfString))
        arrayList.add(str); 
      i++;
    } 
    return arrayList.<String>toArray(new String[arrayList.size()]);
  }
  
  private static boolean mimeTypeAgainstFilter(@NonNull String[] paramArrayOfString1, @NonNull String[] paramArrayOfString2) {
    if (paramArrayOfString2.length != 2)
      throw new IllegalArgumentException("Ill-formatted MIME type filter. Must be type/subtype."); 
    if (paramArrayOfString2[0].isEmpty() || paramArrayOfString2[1].isEmpty())
      throw new IllegalArgumentException("Ill-formatted MIME type filter. Type or subtype empty."); 
    return (paramArrayOfString1.length == 2 && ("*".equals(paramArrayOfString2[0]) || paramArrayOfString2[0].equals(paramArrayOfString1[0])) && ("*".equals(paramArrayOfString2[1]) || paramArrayOfString2[1].equals(paramArrayOfString1[1])));
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/androidx/core/content/MimeTypeFilter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */