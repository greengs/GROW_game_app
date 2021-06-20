package androidx.core.content;

import android.content.SharedPreferences;
import androidx.annotation.NonNull;

@Deprecated
public final class SharedPreferencesCompat {
  @Deprecated
  public static final class EditorCompat {
    private static EditorCompat sInstance;
    
    private final Helper mHelper = new Helper();
    
    @Deprecated
    public static EditorCompat getInstance() {
      if (sInstance == null)
        sInstance = new EditorCompat(); 
      return sInstance;
    }
    
    @Deprecated
    public void apply(@NonNull SharedPreferences.Editor param1Editor) {
      this.mHelper.apply(param1Editor);
    }
    
    private static class Helper {
      public void apply(@NonNull SharedPreferences.Editor param2Editor) {
        try {
          param2Editor.apply();
          return;
        } catch (AbstractMethodError abstractMethodError) {
          param2Editor.commit();
          return;
        } 
      }
    }
  }
  
  private static class Helper {
    public void apply(@NonNull SharedPreferences.Editor param1Editor) {
      try {
        param1Editor.apply();
        return;
      } catch (AbstractMethodError abstractMethodError) {
        param1Editor.commit();
        return;
      } 
    }
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/androidx/core/content/SharedPreferencesCompat.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */