package androidx.appcompat.view.menu;

import android.widget.ListView;
import androidx.annotation.RestrictTo;

@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
public interface ShowableListMenu {
  void dismiss();
  
  ListView getListView();
  
  boolean isShowing();
  
  void show();
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/androidx/appcompat/view/menu/ShowableListMenu.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */