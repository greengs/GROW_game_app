package androidx.appcompat.view.menu;

import android.content.Context;
import android.os.Build;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;
import androidx.annotation.RestrictTo;
import androidx.core.internal.view.SupportMenu;
import androidx.core.internal.view.SupportMenuItem;
import androidx.core.internal.view.SupportSubMenu;

@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
public final class MenuWrapperFactory {
  public static Menu wrapSupportMenu(Context paramContext, SupportMenu paramSupportMenu) {
    return new MenuWrapperICS(paramContext, paramSupportMenu);
  }
  
  public static MenuItem wrapSupportMenuItem(Context paramContext, SupportMenuItem paramSupportMenuItem) {
    return (Build.VERSION.SDK_INT >= 16) ? new MenuItemWrapperJB(paramContext, paramSupportMenuItem) : new MenuItemWrapperICS(paramContext, paramSupportMenuItem);
  }
  
  public static SubMenu wrapSupportSubMenu(Context paramContext, SupportSubMenu paramSupportSubMenu) {
    return new SubMenuWrapperICS(paramContext, paramSupportSubMenu);
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/androidx/appcompat/view/menu/MenuWrapperFactory.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */