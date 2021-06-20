package androidx.appcompat.view.menu;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import androidx.annotation.RestrictTo;
import androidx.core.internal.view.SupportMenu;
import androidx.core.internal.view.SupportSubMenu;

@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
class SubMenuWrapperICS extends MenuWrapperICS implements SubMenu {
  SubMenuWrapperICS(Context paramContext, SupportSubMenu paramSupportSubMenu) {
    super(paramContext, (SupportMenu)paramSupportSubMenu);
  }
  
  public void clearHeader() {
    getWrappedObject().clearHeader();
  }
  
  public MenuItem getItem() {
    return getMenuItemWrapper(getWrappedObject().getItem());
  }
  
  public SupportSubMenu getWrappedObject() {
    return (SupportSubMenu)this.mWrappedObject;
  }
  
  public SubMenu setHeaderIcon(int paramInt) {
    getWrappedObject().setHeaderIcon(paramInt);
    return this;
  }
  
  public SubMenu setHeaderIcon(Drawable paramDrawable) {
    getWrappedObject().setHeaderIcon(paramDrawable);
    return this;
  }
  
  public SubMenu setHeaderTitle(int paramInt) {
    getWrappedObject().setHeaderTitle(paramInt);
    return this;
  }
  
  public SubMenu setHeaderTitle(CharSequence paramCharSequence) {
    getWrappedObject().setHeaderTitle(paramCharSequence);
    return this;
  }
  
  public SubMenu setHeaderView(View paramView) {
    getWrappedObject().setHeaderView(paramView);
    return this;
  }
  
  public SubMenu setIcon(int paramInt) {
    getWrappedObject().setIcon(paramInt);
    return this;
  }
  
  public SubMenu setIcon(Drawable paramDrawable) {
    getWrappedObject().setIcon(paramDrawable);
    return this;
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/androidx/appcompat/view/menu/SubMenuWrapperICS.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */