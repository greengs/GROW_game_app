package androidx.appcompat.app;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import androidx.annotation.IdRes;
import androidx.annotation.LayoutRes;
import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo;
import androidx.appcompat.R;
import androidx.appcompat.view.ActionMode;
import androidx.core.view.KeyEventDispatcher;

public class AppCompatDialog extends Dialog implements AppCompatCallback {
  private AppCompatDelegate mDelegate;
  
  private final KeyEventDispatcher.Component mKeyDispatcher = new KeyEventDispatcher.Component() {
      public boolean superDispatchKeyEvent(KeyEvent param1KeyEvent) {
        return AppCompatDialog.this.superDispatchKeyEvent(param1KeyEvent);
      }
    };
  
  public AppCompatDialog(Context paramContext) {
    this(paramContext, 0);
  }
  
  public AppCompatDialog(Context paramContext, int paramInt) {
    super(paramContext, getThemeResId(paramContext, paramInt));
    getDelegate().onCreate(null);
    getDelegate().applyDayNight();
  }
  
  protected AppCompatDialog(Context paramContext, boolean paramBoolean, DialogInterface.OnCancelListener paramOnCancelListener) {
    super(paramContext, paramBoolean, paramOnCancelListener);
  }
  
  private static int getThemeResId(Context paramContext, int paramInt) {
    int i = paramInt;
    if (paramInt == 0) {
      TypedValue typedValue = new TypedValue();
      paramContext.getTheme().resolveAttribute(R.attr.dialogTheme, typedValue, true);
      i = typedValue.resourceId;
    } 
    return i;
  }
  
  public void addContentView(View paramView, ViewGroup.LayoutParams paramLayoutParams) {
    getDelegate().addContentView(paramView, paramLayoutParams);
  }
  
  public boolean dispatchKeyEvent(KeyEvent paramKeyEvent) {
    View view = getWindow().getDecorView();
    return KeyEventDispatcher.dispatchKeyEvent(this.mKeyDispatcher, view, (Window.Callback)this, paramKeyEvent);
  }
  
  @Nullable
  public <T extends View> T findViewById(@IdRes int paramInt) {
    return getDelegate().findViewById(paramInt);
  }
  
  public AppCompatDelegate getDelegate() {
    if (this.mDelegate == null)
      this.mDelegate = AppCompatDelegate.create(this, this); 
    return this.mDelegate;
  }
  
  public ActionBar getSupportActionBar() {
    return getDelegate().getSupportActionBar();
  }
  
  @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
  public void invalidateOptionsMenu() {
    getDelegate().invalidateOptionsMenu();
  }
  
  protected void onCreate(Bundle paramBundle) {
    getDelegate().installViewFactory();
    super.onCreate(paramBundle);
    getDelegate().onCreate(paramBundle);
  }
  
  protected void onStop() {
    super.onStop();
    getDelegate().onStop();
  }
  
  public void onSupportActionModeFinished(ActionMode paramActionMode) {}
  
  public void onSupportActionModeStarted(ActionMode paramActionMode) {}
  
  @Nullable
  public ActionMode onWindowStartingSupportActionMode(ActionMode.Callback paramCallback) {
    return null;
  }
  
  public void setContentView(@LayoutRes int paramInt) {
    getDelegate().setContentView(paramInt);
  }
  
  public void setContentView(View paramView) {
    getDelegate().setContentView(paramView);
  }
  
  public void setContentView(View paramView, ViewGroup.LayoutParams paramLayoutParams) {
    getDelegate().setContentView(paramView, paramLayoutParams);
  }
  
  public void setTitle(int paramInt) {
    super.setTitle(paramInt);
    getDelegate().setTitle(getContext().getString(paramInt));
  }
  
  public void setTitle(CharSequence paramCharSequence) {
    super.setTitle(paramCharSequence);
    getDelegate().setTitle(paramCharSequence);
  }
  
  boolean superDispatchKeyEvent(KeyEvent paramKeyEvent) {
    return super.dispatchKeyEvent(paramKeyEvent);
  }
  
  public boolean supportRequestWindowFeature(int paramInt) {
    return getDelegate().requestWindowFeature(paramInt);
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/androidx/appcompat/app/AppCompatDialog.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */