package androidx.core.app;

import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import androidx.annotation.CallSuper;
import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo;
import androidx.collection.SimpleArrayMap;
import androidx.core.view.KeyEventDispatcher;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LifecycleRegistry;
import androidx.lifecycle.ReportFragment;

@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
public class ComponentActivity extends Activity implements LifecycleOwner, KeyEventDispatcher.Component {
  private SimpleArrayMap<Class<? extends ExtraData>, ExtraData> mExtraDataMap = new SimpleArrayMap();
  
  private LifecycleRegistry mLifecycleRegistry = new LifecycleRegistry(this);
  
  public boolean dispatchKeyEvent(KeyEvent paramKeyEvent) {
    View view = getWindow().getDecorView();
    return (view != null && KeyEventDispatcher.dispatchBeforeHierarchy(view, paramKeyEvent)) ? true : KeyEventDispatcher.dispatchKeyEvent(this, view, (Window.Callback)this, paramKeyEvent);
  }
  
  public boolean dispatchKeyShortcutEvent(KeyEvent paramKeyEvent) {
    View view = getWindow().getDecorView();
    return (view != null && KeyEventDispatcher.dispatchBeforeHierarchy(view, paramKeyEvent)) ? true : super.dispatchKeyShortcutEvent(paramKeyEvent);
  }
  
  @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
  public <T extends ExtraData> T getExtraData(Class<T> paramClass) {
    return (T)this.mExtraDataMap.get(paramClass);
  }
  
  public Lifecycle getLifecycle() {
    return (Lifecycle)this.mLifecycleRegistry;
  }
  
  protected void onCreate(@Nullable Bundle paramBundle) {
    super.onCreate(paramBundle);
    ReportFragment.injectIfNeededIn(this);
  }
  
  @CallSuper
  protected void onSaveInstanceState(Bundle paramBundle) {
    this.mLifecycleRegistry.markState(Lifecycle.State.CREATED);
    super.onSaveInstanceState(paramBundle);
  }
  
  @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
  public void putExtraData(ExtraData paramExtraData) {
    this.mExtraDataMap.put(paramExtraData.getClass(), paramExtraData);
  }
  
  @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
  public boolean superDispatchKeyEvent(KeyEvent paramKeyEvent) {
    return super.dispatchKeyEvent(paramKeyEvent);
  }
  
  @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
  public static class ExtraData {}
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/androidx/core/app/ComponentActivity.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */