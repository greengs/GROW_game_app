package androidx.appcompat.widget;

import android.graphics.drawable.Drawable;
import android.os.Parcelable;
import android.util.SparseArray;
import android.view.Menu;
import android.view.Window;
import androidx.annotation.RestrictTo;
import androidx.appcompat.view.menu.MenuPresenter;

@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
public interface DecorContentParent {
  boolean canShowOverflowMenu();
  
  void dismissPopups();
  
  CharSequence getTitle();
  
  boolean hasIcon();
  
  boolean hasLogo();
  
  boolean hideOverflowMenu();
  
  void initFeature(int paramInt);
  
  boolean isOverflowMenuShowPending();
  
  boolean isOverflowMenuShowing();
  
  void restoreToolbarHierarchyState(SparseArray<Parcelable> paramSparseArray);
  
  void saveToolbarHierarchyState(SparseArray<Parcelable> paramSparseArray);
  
  void setIcon(int paramInt);
  
  void setIcon(Drawable paramDrawable);
  
  void setLogo(int paramInt);
  
  void setMenu(Menu paramMenu, MenuPresenter.Callback paramCallback);
  
  void setMenuPrepared();
  
  void setUiOptions(int paramInt);
  
  void setWindowCallback(Window.Callback paramCallback);
  
  void setWindowTitle(CharSequence paramCharSequence);
  
  boolean showOverflowMenu();
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/androidx/appcompat/widget/DecorContentParent.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */