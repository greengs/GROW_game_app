package androidx.core.internal.view;

import android.view.Menu;
import androidx.annotation.RestrictTo;

@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
public interface SupportMenu extends Menu {
  public static final int CATEGORY_MASK = -65536;
  
  public static final int CATEGORY_SHIFT = 16;
  
  public static final int FLAG_KEEP_OPEN_ON_SUBMENU_OPENED = 4;
  
  public static final int SUPPORTED_MODIFIERS_MASK = 69647;
  
  public static final int USER_MASK = 65535;
  
  public static final int USER_SHIFT = 0;
  
  void setGroupDividerEnabled(boolean paramBoolean);
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/androidx/core/internal/view/SupportMenu.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */