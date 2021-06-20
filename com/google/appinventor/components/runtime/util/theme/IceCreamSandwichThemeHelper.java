package com.google.appinventor.components.runtime.util.theme;

import android.app.Activity;
import android.text.Html;
import androidx.appcompat.app.ActionBar;
import com.google.appinventor.components.runtime.AppInventorCompatActivity;
import com.google.appinventor.components.runtime.Component;
import com.google.appinventor.components.runtime.Form;
import com.google.appinventor.components.runtime.util.ImageViewUtil;

public class IceCreamSandwichThemeHelper implements ThemeHelper {
  private final AppInventorCompatActivity activity;
  
  public IceCreamSandwichThemeHelper(AppInventorCompatActivity paramAppInventorCompatActivity) {
    this.activity = paramAppInventorCompatActivity;
  }
  
  public boolean hasActionBar() {
    return (this.activity.getSupportActionBar() != null);
  }
  
  public void requestActionBar() {
    this.activity.getWindow().requestFeature(8);
  }
  
  public void setActionBarAnimation(boolean paramBoolean) {
    ActionBar actionBar = this.activity.getSupportActionBar();
    if (actionBar != null)
      actionBar.setShowHideAnimationEnabled(paramBoolean); 
  }
  
  public boolean setActionBarVisible(boolean paramBoolean) {
    ActionBar actionBar = this.activity.getSupportActionBar();
    if (actionBar == null) {
      if (this.activity instanceof Form)
        ((Form)this.activity).dispatchErrorOccurredEvent((Component)this.activity, "ActionBar", 907, new Object[0]); 
      return false;
    } 
    if (paramBoolean) {
      actionBar.show();
      return true;
    } 
    actionBar.hide();
    return true;
  }
  
  public void setTitle(String paramString) {
    ActionBar actionBar = this.activity.getSupportActionBar();
    if (actionBar != null)
      actionBar.setTitle(paramString); 
  }
  
  public void setTitle(String paramString, boolean paramBoolean) {
    ActionBar actionBar = this.activity.getSupportActionBar();
    if (actionBar != null) {
      if (paramBoolean) {
        actionBar.setTitle((CharSequence)Html.fromHtml("<font color=\"black\">" + paramString + "</font>"));
        ImageViewUtil.setMenuButtonColor((Activity)this.activity, -16777216);
        return;
      } 
    } else {
      return;
    } 
    actionBar.setTitle(paramString);
    ImageViewUtil.setMenuButtonColor((Activity)this.activity, -1);
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/com/google/appinventor/components/runtime/util/theme/IceCreamSandwichThemeHelper.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */