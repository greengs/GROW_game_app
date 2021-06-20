package com.google.appinventor.components.runtime;

import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatCallback;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.view.ActionMode;
import com.google.appinventor.components.runtime.util.PaintUtil;
import com.google.appinventor.components.runtime.util.SdkLevel;
import com.google.appinventor.components.runtime.util.theme.ClassicThemeHelper;
import com.google.appinventor.components.runtime.util.theme.HoneycombThemeHelper;
import com.google.appinventor.components.runtime.util.theme.IceCreamSandwichThemeHelper;
import com.google.appinventor.components.runtime.util.theme.ThemeHelper;

public class AppInventorCompatActivity extends Activity implements AppCompatCallback {
  static final int DEFAULT_PRIMARY_COLOR;
  
  private static final String LOG_TAG = AppInventorCompatActivity.class.getSimpleName();
  
  private static boolean actionBarEnabled;
  
  private static boolean classicMode;
  
  private static Theme currentTheme;
  
  private static boolean didSetClassicModeFromYail;
  
  private static int primaryColor;
  
  private AppCompatDelegate appCompatDelegate;
  
  LinearLayout frameWithTitle;
  
  protected ThemeHelper themeHelper;
  
  TextView titleBar;
  
  static {
    DEFAULT_PRIMARY_COLOR = PaintUtil.hexStringToInt("&HFF3F51B5");
    classicMode = false;
    currentTheme = Theme.PACKAGED;
    didSetClassicModeFromYail = false;
  }
  
  private void applyTheme() {
    Log.d(LOG_TAG, "applyTheme " + currentTheme);
    setClassicMode(false);
    switch (currentTheme) {
      default:
        return;
      case CLASSIC:
        setClassicMode(true);
        setTheme(16973829);
        return;
      case DEVICE_DEFAULT:
      case BLACK_TITLE_TEXT:
        setTheme(16974124);
        return;
      case DARK:
        break;
    } 
    setTheme(16974121);
  }
  
  protected static int getPrimaryColor() {
    return primaryColor;
  }
  
  protected static boolean isActionBarEnabled() {
    return actionBarEnabled;
  }
  
  public static boolean isClassicMode() {
    return classicMode;
  }
  
  public static boolean isEmulator() {
    return (Build.PRODUCT.contains("google_sdk") || Build.PRODUCT.equals("sdk") || Build.PRODUCT.contains("sdk_gphone"));
  }
  
  public static void setClassicModeFromYail(boolean paramBoolean) {
    if (!didSetClassicModeFromYail) {
      Log.d(LOG_TAG, "Setting classic mode from YAIL: " + paramBoolean);
      classicMode = paramBoolean;
      didSetClassicModeFromYail = true;
    } 
  }
  
  private boolean shouldCreateTitleBar() {
    return !((!isAppCompatMode() || (this.themeHelper.hasActionBar() && isActionBarEnabled())) && (this.titleBar != null || (!isRepl() && !classicMode)));
  }
  
  public ActionBar getSupportActionBar() {
    Window.Callback callback = getWindow().getCallback();
    try {
      return (this.appCompatDelegate == null) ? null : this.appCompatDelegate.getSupportActionBar();
    } catch (IllegalStateException illegalStateException) {
      this.appCompatDelegate = null;
      classicMode = true;
      getWindow().setCallback(callback);
      return null;
    } 
  }
  
  protected void hideTitleBar() {
    if (this.titleBar != null) {
      if (this.titleBar.getParent() != this.frameWithTitle) {
        if (this.titleBar.getParent() != null)
          ((View)this.titleBar.getParent()).setVisibility(8); 
        return;
      } 
    } else {
      return;
    } 
    this.titleBar.setVisibility(8);
  }
  
  public final boolean isAppCompatMode() {
    return (this.appCompatDelegate != null);
  }
  
  protected boolean isRepl() {
    return false;
  }
  
  protected void maybeShowTitleBar() {
    if (this.titleBar != null) {
      this.titleBar.setVisibility(0);
      Log.d(LOG_TAG, "titleBar visible");
      if (this.titleBar.getParent() != null && this.titleBar.getParent() != this.frameWithTitle) {
        Log.d(LOG_TAG, "Setting parent visible");
        ((View)this.titleBar.getParent()).setVisibility(0);
      } 
    } 
  }
  
  public void onConfigurationChanged(Configuration paramConfiguration) {
    super.onConfigurationChanged(paramConfiguration);
    if (this.appCompatDelegate != null)
      this.appCompatDelegate.onConfigurationChanged(paramConfiguration); 
  }
  
  public void onCreate(Bundle paramBundle) {
    boolean bool;
    if (classicMode || SdkLevel.getLevel() < 11) {
      bool = true;
    } else {
      bool = false;
    } 
    classicMode = bool;
    if (classicMode) {
      this.themeHelper = (ThemeHelper)new ClassicThemeHelper();
    } else if (SdkLevel.getLevel() < 14) {
      this.themeHelper = (ThemeHelper)new HoneycombThemeHelper(this);
      this.themeHelper.requestActionBar();
      actionBarEnabled = true;
    } else {
      this.themeHelper = (ThemeHelper)new IceCreamSandwichThemeHelper(this);
      if (currentTheme != Theme.PACKAGED)
        applyTheme(); 
      this.appCompatDelegate = AppCompatDelegate.create(this, this);
      this.appCompatDelegate.onCreate(paramBundle);
    } 
    super.onCreate(paramBundle);
    this.frameWithTitle = new LinearLayout((Context)this);
    this.frameWithTitle.setOrientation(1);
    setContentView((View)this.frameWithTitle);
    actionBarEnabled = this.themeHelper.hasActionBar();
    this.titleBar = (TextView)findViewById(16908310);
    if (shouldCreateTitleBar()) {
      this.titleBar = new TextView((Context)this);
      this.titleBar.setBackgroundResource(17301653);
      this.titleBar.setTextAppearance((Context)this, 16973907);
      this.titleBar.setGravity(16);
      this.titleBar.setSingleLine();
      this.titleBar.setShadowLayer(2.0F, 0.0F, 0.0F, -1157627904);
      if (!isClassicMode() || SdkLevel.getLevel() < 11)
        this.frameWithTitle.addView((View)this.titleBar, new ViewGroup.LayoutParams(-1, -2)); 
      return;
    } 
    Log.d(LOG_TAG, "Already have a title bar (classic mode): " + this.titleBar);
  }
  
  protected void onDestroy() {
    super.onDestroy();
    if (this.appCompatDelegate != null)
      this.appCompatDelegate.onDestroy(); 
  }
  
  protected void onPostCreate(Bundle paramBundle) {
    super.onPostCreate(paramBundle);
    if (this.appCompatDelegate != null)
      this.appCompatDelegate.onPostCreate(paramBundle); 
  }
  
  protected void onPostResume() {
    super.onPostResume();
    if (this.appCompatDelegate != null)
      this.appCompatDelegate.onPostResume(); 
  }
  
  protected void onStop() {
    super.onStop();
    if (this.appCompatDelegate != null)
      this.appCompatDelegate.onStop(); 
  }
  
  public void onSupportActionModeFinished(ActionMode paramActionMode) {}
  
  public void onSupportActionModeStarted(ActionMode paramActionMode) {}
  
  protected void onTitleChanged(CharSequence paramCharSequence, int paramInt) {
    super.onTitleChanged(paramCharSequence, paramInt);
    if (this.appCompatDelegate != null) {
      this.appCompatDelegate.setTitle(paramCharSequence);
      return;
    } 
    if (this.titleBar != null) {
      this.titleBar.setText(paramCharSequence);
      return;
    } 
  }
  
  @Nullable
  public ActionMode onWindowStartingSupportActionMode(ActionMode.Callback paramCallback) {
    return null;
  }
  
  protected void setActionBarEnabled(boolean paramBoolean) {
    actionBarEnabled = paramBoolean;
  }
  
  protected void setAppInventorTheme(Theme paramTheme) {
    if (Form.getActiveForm().isRepl() && paramTheme != currentTheme) {
      currentTheme = paramTheme;
      applyTheme();
      return;
    } 
  }
  
  protected void setClassicMode(boolean paramBoolean) {
    if (isRepl() && SdkLevel.getLevel() >= 11)
      classicMode = paramBoolean; 
  }
  
  public void setContentView(View paramView) {
    LinearLayout linearLayout;
    View view = paramView;
    if (paramView != this.frameWithTitle) {
      this.frameWithTitle.addView(paramView, (ViewGroup.LayoutParams)new FrameLayout.LayoutParams(-1, -1));
      linearLayout = this.frameWithTitle;
    } 
    if (this.appCompatDelegate != null) {
      this.appCompatDelegate.setContentView((View)linearLayout);
      return;
    } 
    super.setContentView((View)linearLayout);
  }
  
  protected void setPrimaryColor(int paramInt) {
    int i;
    ActionBar actionBar = getSupportActionBar();
    if (paramInt == 0) {
      i = DEFAULT_PRIMARY_COLOR;
    } else {
      i = paramInt;
    } 
    if (actionBar != null && i != primaryColor) {
      primaryColor = i;
      actionBar.setBackgroundDrawable((Drawable)new ColorDrawable(paramInt));
    } 
  }
  
  protected void styleTitleBar() {
    boolean bool;
    ActionBar actionBar = getSupportActionBar();
    Log.d(LOG_TAG, "actionBarEnabled = " + actionBarEnabled);
    String str = LOG_TAG;
    StringBuilder stringBuilder = (new StringBuilder()).append("!classicMode = ");
    if (!classicMode) {
      bool = true;
    } else {
      bool = false;
    } 
    Log.d(str, stringBuilder.append(bool).toString());
    Log.d(LOG_TAG, "actionBar = " + actionBar);
    if (actionBar != null) {
      actionBar.setBackgroundDrawable((Drawable)new ColorDrawable(getPrimaryColor()));
      if (actionBarEnabled) {
        actionBar.show();
        hideTitleBar();
        return;
      } 
      actionBar.hide();
    } 
    maybeShowTitleBar();
  }
  
  public enum Theme {
    BLACK_TITLE_TEXT, CLASSIC, DARK, DEVICE_DEFAULT, PACKAGED;
    
    static {
      BLACK_TITLE_TEXT = new Theme("BLACK_TITLE_TEXT", 3);
      DARK = new Theme("DARK", 4);
      $VALUES = new Theme[] { PACKAGED, CLASSIC, DEVICE_DEFAULT, BLACK_TITLE_TEXT, DARK };
    }
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/com/google/appinventor/components/runtime/AppInventorCompatActivity.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */