package androidx.appcompat.app;

import androidx.annotation.Nullable;
import androidx.appcompat.view.ActionMode;

public interface AppCompatCallback {
  void onSupportActionModeFinished(ActionMode paramActionMode);
  
  void onSupportActionModeStarted(ActionMode paramActionMode);
  
  @Nullable
  ActionMode onWindowStartingSupportActionMode(ActionMode.Callback paramCallback);
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/androidx/appcompat/app/AppCompatCallback.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */