package androidx.core.view;

import android.app.ActionBar;
import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Build;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
public class KeyEventDispatcher {
  private static boolean sActionBarFieldsFetched = false;
  
  private static Method sActionBarOnMenuKeyMethod = null;
  
  private static boolean sDialogFieldsFetched = false;
  
  private static Field sDialogKeyListenerField = null;
  
  private static boolean actionBarOnMenuKeyEventPre28(ActionBar paramActionBar, KeyEvent paramKeyEvent) {
    if (!sActionBarFieldsFetched) {
      try {
        sActionBarOnMenuKeyMethod = paramActionBar.getClass().getMethod("onMenuKeyEvent", new Class[] { KeyEvent.class });
      } catch (NoSuchMethodException noSuchMethodException) {}
      sActionBarFieldsFetched = true;
    } 
    if (sActionBarOnMenuKeyMethod != null)
      try {
        return ((Boolean)sActionBarOnMenuKeyMethod.invoke(paramActionBar, new Object[] { paramKeyEvent })).booleanValue();
      } catch (IllegalAccessException illegalAccessException) {
      
      } catch (InvocationTargetException invocationTargetException) {} 
    return false;
  }
  
  private static boolean activitySuperDispatchKeyEventPre28(Activity paramActivity, KeyEvent paramKeyEvent) {
    paramActivity.onUserInteraction();
    Window window = paramActivity.getWindow();
    if (window.hasFeature(8)) {
      ActionBar actionBar = paramActivity.getActionBar();
      if (paramKeyEvent.getKeyCode() == 82 && actionBar != null && actionBarOnMenuKeyEventPre28(actionBar, paramKeyEvent))
        return true; 
    } 
    if (!window.superDispatchKeyEvent(paramKeyEvent)) {
      View view = window.getDecorView();
      if (!ViewCompat.dispatchUnhandledKeyEventBeforeCallback(view, paramKeyEvent)) {
        if (view != null) {
          KeyEvent.DispatcherState dispatcherState = view.getKeyDispatcherState();
          return paramKeyEvent.dispatch((KeyEvent.Callback)paramActivity, dispatcherState, paramActivity);
        } 
        view = null;
        return paramKeyEvent.dispatch((KeyEvent.Callback)paramActivity, (KeyEvent.DispatcherState)view, paramActivity);
      } 
    } 
    return true;
  }
  
  private static boolean dialogSuperDispatchKeyEventPre28(Dialog paramDialog, KeyEvent paramKeyEvent) {
    DialogInterface.OnKeyListener onKeyListener = getDialogKeyListenerPre28(paramDialog);
    if (onKeyListener == null || !onKeyListener.onKey((DialogInterface)paramDialog, paramKeyEvent.getKeyCode(), paramKeyEvent)) {
      Window window = paramDialog.getWindow();
      if (!window.superDispatchKeyEvent(paramKeyEvent)) {
        View view = window.getDecorView();
        if (!ViewCompat.dispatchUnhandledKeyEventBeforeCallback(view, paramKeyEvent)) {
          if (view != null) {
            KeyEvent.DispatcherState dispatcherState = view.getKeyDispatcherState();
            return paramKeyEvent.dispatch((KeyEvent.Callback)paramDialog, dispatcherState, paramDialog);
          } 
          view = null;
          return paramKeyEvent.dispatch((KeyEvent.Callback)paramDialog, (KeyEvent.DispatcherState)view, paramDialog);
        } 
      } 
    } 
    return true;
  }
  
  public static boolean dispatchBeforeHierarchy(@NonNull View paramView, @NonNull KeyEvent paramKeyEvent) {
    return ViewCompat.dispatchUnhandledKeyEventBeforeHierarchy(paramView, paramKeyEvent);
  }
  
  public static boolean dispatchKeyEvent(@NonNull Component paramComponent, @Nullable View paramView, @Nullable Window.Callback paramCallback, @NonNull KeyEvent paramKeyEvent) {
    if (paramComponent != null) {
      if (Build.VERSION.SDK_INT >= 28)
        return paramComponent.superDispatchKeyEvent(paramKeyEvent); 
      if (paramCallback instanceof Activity)
        return activitySuperDispatchKeyEventPre28((Activity)paramCallback, paramKeyEvent); 
      if (paramCallback instanceof Dialog)
        return dialogSuperDispatchKeyEventPre28((Dialog)paramCallback, paramKeyEvent); 
      if ((paramView != null && ViewCompat.dispatchUnhandledKeyEventBeforeCallback(paramView, paramKeyEvent)) || paramComponent.superDispatchKeyEvent(paramKeyEvent))
        return true; 
    } 
    return false;
  }
  
  private static DialogInterface.OnKeyListener getDialogKeyListenerPre28(Dialog paramDialog) {
    if (!sDialogFieldsFetched) {
      try {
        sDialogKeyListenerField = Dialog.class.getDeclaredField("mOnKeyListener");
        sDialogKeyListenerField.setAccessible(true);
      } catch (NoSuchFieldException noSuchFieldException) {}
      sDialogFieldsFetched = true;
    } 
    if (sDialogKeyListenerField != null)
      try {
        return (DialogInterface.OnKeyListener)sDialogKeyListenerField.get(paramDialog);
      } catch (IllegalAccessException illegalAccessException) {} 
    return null;
  }
  
  public static interface Component {
    boolean superDispatchKeyEvent(KeyEvent param1KeyEvent);
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/androidx/core/view/KeyEventDispatcher.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */