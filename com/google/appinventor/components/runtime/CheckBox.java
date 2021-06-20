package com.google.appinventor.components.runtime;

import android.content.Context;
import android.widget.CheckBox;
import com.google.appinventor.components.annotations.DesignerComponent;
import com.google.appinventor.components.annotations.DesignerProperty;
import com.google.appinventor.components.annotations.PropertyCategory;
import com.google.appinventor.components.annotations.SimpleObject;
import com.google.appinventor.components.annotations.SimpleProperty;
import com.google.appinventor.components.common.ComponentCategory;

@DesignerComponent(category = ComponentCategory.USERINTERFACE, description = "Checkbox that raises an event when the user clicks on it. There are many properties affecting its appearance that can be set in the Designer or Blocks Editor.", version = 2)
@SimpleObject
public final class CheckBox extends ToggleBase<CheckBox> {
  public CheckBox(ComponentContainer paramComponentContainer) {
    super(paramComponentContainer);
    this.view = new CheckBox((Context)paramComponentContainer.$context());
    Checked(false);
    initToggle();
  }
  
  @DesignerProperty(defaultValue = "False", editorType = "boolean")
  @SimpleProperty
  public void Checked(boolean paramBoolean) {
    this.view.setChecked(paramBoolean);
    this.view.invalidate();
  }
  
  @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "True if the box is checked, false otherwise.")
  public boolean Checked() {
    return this.view.isChecked();
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/com/google/appinventor/components/runtime/CheckBox.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */