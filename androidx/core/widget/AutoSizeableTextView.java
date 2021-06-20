package androidx.core.widget;

import android.os.Build;
import androidx.annotation.NonNull;
import androidx.annotation.RestrictTo;

@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
public interface AutoSizeableTextView {
  @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
  public static final boolean PLATFORM_SUPPORTS_AUTOSIZE;
  
  static {
    boolean bool;
    if (Build.VERSION.SDK_INT >= 27) {
      bool = true;
    } else {
      bool = false;
    } 
    PLATFORM_SUPPORTS_AUTOSIZE = bool;
  }
  
  int getAutoSizeMaxTextSize();
  
  int getAutoSizeMinTextSize();
  
  int getAutoSizeStepGranularity();
  
  int[] getAutoSizeTextAvailableSizes();
  
  int getAutoSizeTextType();
  
  void setAutoSizeTextTypeUniformWithConfiguration(int paramInt1, int paramInt2, int paramInt3, int paramInt4) throws IllegalArgumentException;
  
  void setAutoSizeTextTypeUniformWithPresetSizes(@NonNull int[] paramArrayOfint, int paramInt) throws IllegalArgumentException;
  
  void setAutoSizeTextTypeWithDefaults(int paramInt);
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/androidx/core/widget/AutoSizeableTextView.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */