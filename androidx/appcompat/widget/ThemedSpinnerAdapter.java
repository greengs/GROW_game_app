package androidx.appcompat.widget;

import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.widget.SpinnerAdapter;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.view.ContextThemeWrapper;

public interface ThemedSpinnerAdapter extends SpinnerAdapter {
  @Nullable
  Resources.Theme getDropDownViewTheme();
  
  void setDropDownViewTheme(@Nullable Resources.Theme paramTheme);
  
  public static final class Helper {
    private final Context mContext;
    
    private LayoutInflater mDropDownInflater;
    
    private final LayoutInflater mInflater;
    
    public Helper(@NonNull Context param1Context) {
      this.mContext = param1Context;
      this.mInflater = LayoutInflater.from(param1Context);
    }
    
    @NonNull
    public LayoutInflater getDropDownViewInflater() {
      return (this.mDropDownInflater != null) ? this.mDropDownInflater : this.mInflater;
    }
    
    @Nullable
    public Resources.Theme getDropDownViewTheme() {
      return (this.mDropDownInflater == null) ? null : this.mDropDownInflater.getContext().getTheme();
    }
    
    public void setDropDownViewTheme(@Nullable Resources.Theme param1Theme) {
      if (param1Theme == null) {
        this.mDropDownInflater = null;
        return;
      } 
      if (param1Theme == this.mContext.getTheme()) {
        this.mDropDownInflater = this.mInflater;
        return;
      } 
      this.mDropDownInflater = LayoutInflater.from((Context)new ContextThemeWrapper(this.mContext, param1Theme));
    }
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/androidx/appcompat/widget/ThemedSpinnerAdapter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */