package androidx.core.view.inputmethod;

import android.os.Build;
import android.os.Bundle;
import android.view.inputmethod.EditorInfo;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public final class EditorInfoCompat {
  private static final String CONTENT_MIME_TYPES_KEY = "androidx.core.view.inputmethod.EditorInfoCompat.CONTENT_MIME_TYPES";
  
  private static final String[] EMPTY_STRING_ARRAY = new String[0];
  
  public static final int IME_FLAG_FORCE_ASCII = -2147483648;
  
  public static final int IME_FLAG_NO_PERSONALIZED_LEARNING = 16777216;
  
  @NonNull
  public static String[] getContentMimeTypes(EditorInfo paramEditorInfo) {
    if (Build.VERSION.SDK_INT >= 25) {
      arrayOfString1 = paramEditorInfo.contentMimeTypes;
      return (arrayOfString1 == null) ? EMPTY_STRING_ARRAY : arrayOfString1;
    } 
    if (((EditorInfo)arrayOfString1).extras == null)
      return EMPTY_STRING_ARRAY; 
    String[] arrayOfString2 = ((EditorInfo)arrayOfString1).extras.getStringArray("androidx.core.view.inputmethod.EditorInfoCompat.CONTENT_MIME_TYPES");
    String[] arrayOfString1 = arrayOfString2;
    return (arrayOfString2 == null) ? EMPTY_STRING_ARRAY : arrayOfString1;
  }
  
  public static void setContentMimeTypes(@NonNull EditorInfo paramEditorInfo, @Nullable String[] paramArrayOfString) {
    if (Build.VERSION.SDK_INT >= 25) {
      paramEditorInfo.contentMimeTypes = paramArrayOfString;
      return;
    } 
    if (paramEditorInfo.extras == null)
      paramEditorInfo.extras = new Bundle(); 
    paramEditorInfo.extras.putStringArray("androidx.core.view.inputmethod.EditorInfoCompat.CONTENT_MIME_TYPES", paramArrayOfString);
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/androidx/core/view/inputmethod/EditorInfoCompat.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */