package com.google.appinventor.components.runtime;

import android.view.View;
import com.google.appinventor.components.annotations.DesignerProperty;
import com.google.appinventor.components.annotations.PropertyCategory;
import com.google.appinventor.components.annotations.SimpleObject;
import com.google.appinventor.components.annotations.SimpleProperty;
import com.google.appinventor.components.annotations.SimplePropertyCopier;

@SimpleObject
public abstract class AndroidViewComponent extends VisibleComponent {
  private int column = -1;
  
  protected final ComponentContainer container;
  
  private int lastSetHeight = -3;
  
  private int lastSetWidth = -3;
  
  private int percentHeightHolder = -3;
  
  private int percentWidthHolder = -3;
  
  private int row = -1;
  
  protected AndroidViewComponent(ComponentContainer paramComponentContainer) {
    this.container = paramComponentContainer;
  }
  
  @SimpleProperty(userVisible = false)
  public int Column() {
    return this.column;
  }
  
  @SimpleProperty(userVisible = false)
  public void Column(int paramInt) {
    this.column = paramInt;
  }
  
  @SimplePropertyCopier
  public void CopyHeight(AndroidViewComponent paramAndroidViewComponent) {
    Height(paramAndroidViewComponent.lastSetHeight);
  }
  
  @SimplePropertyCopier
  public void CopyWidth(AndroidViewComponent paramAndroidViewComponent) {
    Width(paramAndroidViewComponent.lastSetWidth);
  }
  
  @SimpleProperty
  public int Height() {
    return (int)(getView().getHeight() / this.container.$form().deviceDensity());
  }
  
  @SimpleProperty(description = "Specifies the vertical height of the %type%, measured in pixels.")
  public void Height(int paramInt) {
    this.container.setChildHeight(this, paramInt);
    this.lastSetHeight = paramInt;
    if (paramInt <= -1000) {
      this.container.$form().registerPercentLength(this, paramInt, Form.PercentStorageRecord.Dim.HEIGHT);
      return;
    } 
    this.container.$form().unregisterPercentLength(this, Form.PercentStorageRecord.Dim.HEIGHT);
  }
  
  @SimpleProperty(description = "Specifies the vertical height of the %type% as a percentage of the height of the Screen.")
  public void HeightPercent(int paramInt) {
    if (paramInt < 0 || paramInt > 100) {
      this.container.$form().dispatchErrorOccurredEvent(this, "HeightPercent", 2801, new Object[] { Integer.valueOf(paramInt) });
      return;
    } 
    Height(-paramInt - 1000);
  }
  
  @SimpleProperty(userVisible = false)
  public int Row() {
    return this.row;
  }
  
  @SimpleProperty(userVisible = false)
  public void Row(int paramInt) {
    this.row = paramInt;
  }
  
  @DesignerProperty(defaultValue = "True", editorType = "visibility")
  @SimpleProperty
  public void Visible(boolean paramBoolean) {
    byte b;
    View view = getView();
    if (paramBoolean) {
      b = 0;
    } else {
      b = 8;
    } 
    view.setVisibility(b);
  }
  
  @SimpleProperty(category = PropertyCategory.APPEARANCE, description = "Specifies whether the %type% should be visible on the screen. Value is true if the %type% is showing and false if hidden.")
  public boolean Visible() {
    return (getView().getVisibility() == 0);
  }
  
  @SimpleProperty
  public int Width() {
    return (int)(getView().getWidth() / this.container.$form().deviceDensity());
  }
  
  @SimpleProperty(description = "Specifies the horizontal width of the %type%, measured in pixels.")
  public void Width(int paramInt) {
    this.container.setChildWidth(this, paramInt);
    this.lastSetWidth = paramInt;
    if (paramInt <= -1000) {
      this.container.$form().registerPercentLength(this, paramInt, Form.PercentStorageRecord.Dim.WIDTH);
      return;
    } 
    this.container.$form().unregisterPercentLength(this, Form.PercentStorageRecord.Dim.WIDTH);
  }
  
  @SimpleProperty(description = "Specifies the horizontal width of the %type% as a percentage of the width of the Screen.")
  public void WidthPercent(int paramInt) {
    if (paramInt < 0 || paramInt > 100) {
      this.container.$form().dispatchErrorOccurredEvent(this, "WidthPercent", 2801, new Object[] { Integer.valueOf(paramInt) });
      return;
    } 
    Width(-paramInt - 1000);
  }
  
  public HandlesEventDispatching getDispatchDelegate() {
    return this.container.$form();
  }
  
  public int getSetHeight() {
    return (this.percentHeightHolder == -3) ? Height() : this.percentHeightHolder;
  }
  
  public int getSetWidth() {
    return (this.percentWidthHolder == -3) ? Width() : this.percentWidthHolder;
  }
  
  public abstract View getView();
  
  public void setLastHeight(int paramInt) {
    this.percentHeightHolder = paramInt;
  }
  
  public void setLastWidth(int paramInt) {
    this.percentWidthHolder = paramInt;
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/com/google/appinventor/components/runtime/AndroidViewComponent.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */