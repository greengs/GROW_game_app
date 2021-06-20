package com.google.appinventor.components.runtime;

import android.app.Activity;
import android.content.Context;
import android.content.res.ColorStateList;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import androidx.appcompat.widget.SwitchCompat;
import androidx.core.graphics.drawable.DrawableCompat;
import com.google.appinventor.components.annotations.DesignerComponent;
import com.google.appinventor.components.annotations.DesignerProperty;
import com.google.appinventor.components.annotations.PropertyCategory;
import com.google.appinventor.components.annotations.SimpleEvent;
import com.google.appinventor.components.annotations.SimpleObject;
import com.google.appinventor.components.annotations.SimpleProperty;
import com.google.appinventor.components.common.ComponentCategory;
import com.google.appinventor.components.runtime.util.SdkLevel;

@DesignerComponent(category = ComponentCategory.USERINTERFACE, description = "Toggle switch that raises an event when the user clicks on it. There are many properties affecting its appearance that can be set in the Designer or Blocks Editor.", version = 1)
@SimpleObject
public final class Switch extends ToggleBase<CompoundButton> {
  private final Activity activity;
  
  private final SwitchCompat switchView;
  
  private int thumbColorActive;
  
  private int thumbColorInactive;
  
  private int trackColorActive;
  
  private int trackColorInactive;
  
  public Switch(ComponentContainer paramComponentContainer) {
    super(paramComponentContainer);
    this.activity = paramComponentContainer.$context();
    if (SdkLevel.getLevel() < 14) {
      this.switchView = null;
      this.view = (CompoundButton)new CheckBox((Context)this.activity);
    } else {
      this.switchView = new SwitchCompat((Context)this.activity);
      this.view = (CompoundButton)this.switchView;
    } 
    On(false);
    ThumbColorActive(-1);
    ThumbColorInactive(-3355444);
    TrackColorActive(-16711936);
    TrackColorInactive(-7829368);
    initToggle();
  }
  
  private ColorStateList createSwitchColors(int paramInt1, int paramInt2) {
    int[] arrayOfInt = new int[0];
    return new ColorStateList(new int[][] { { 16842912 }, , arrayOfInt }, new int[] { paramInt1, paramInt2 });
  }
  
  @SimpleEvent(description = "User change the state of the `Switch` from On to Off or back.")
  public void Changed() {
    super.Changed();
  }
  
  @DesignerProperty(defaultValue = "False", editorType = "boolean")
  @SimpleProperty
  public void On(boolean paramBoolean) {
    this.view.setChecked(paramBoolean);
    this.view.invalidate();
  }
  
  @SimpleProperty(category = PropertyCategory.BEHAVIOR)
  public boolean On() {
    return this.view.isChecked();
  }
  
  @SimpleProperty(category = PropertyCategory.APPEARANCE)
  public int ThumbColorActive() {
    return this.thumbColorActive;
  }
  
  @DesignerProperty(defaultValue = "&HFFFFFFFF", editorType = "color")
  @SimpleProperty
  public void ThumbColorActive(int paramInt) {
    this.thumbColorActive = paramInt;
    if (this.switchView != null) {
      DrawableCompat.setTintList(this.switchView.getThumbDrawable(), createSwitchColors(paramInt, this.thumbColorInactive));
      this.view.invalidate();
    } 
  }
  
  @SimpleProperty(category = PropertyCategory.APPEARANCE, userVisible = true)
  public int ThumbColorInactive() {
    return this.thumbColorInactive;
  }
  
  @DesignerProperty(defaultValue = "&HFFCCCCCC", editorType = "color")
  @SimpleProperty
  public void ThumbColorInactive(int paramInt) {
    this.thumbColorInactive = paramInt;
    if (this.switchView != null) {
      DrawableCompat.setTintList(this.switchView.getThumbDrawable(), createSwitchColors(this.thumbColorActive, paramInt));
      this.view.invalidate();
    } 
  }
  
  @SimpleProperty(category = PropertyCategory.APPEARANCE, userVisible = true)
  public int TrackColorActive() {
    return this.trackColorActive;
  }
  
  @DesignerProperty(defaultValue = "&HFF00FF00", editorType = "color")
  @SimpleProperty(description = "Color of the toggle track when switched on", userVisible = true)
  public void TrackColorActive(int paramInt) {
    this.trackColorActive = paramInt;
    if (this.switchView != null) {
      DrawableCompat.setTintList(this.switchView.getTrackDrawable(), createSwitchColors(paramInt, this.trackColorInactive));
      this.view.invalidate();
    } 
  }
  
  @SimpleProperty(category = PropertyCategory.APPEARANCE, userVisible = true)
  public int TrackColorInactive() {
    return this.trackColorInactive;
  }
  
  @DesignerProperty(defaultValue = "&HFF444444", editorType = "color")
  @SimpleProperty(description = "Color of the toggle track when switched off", userVisible = true)
  public void TrackColorInactive(int paramInt) {
    this.trackColorInactive = paramInt;
    if (this.switchView != null) {
      DrawableCompat.setTintList(this.switchView.getTrackDrawable(), createSwitchColors(this.trackColorActive, paramInt));
      this.view.invalidate();
    } 
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/com/google/appinventor/components/runtime/Switch.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */