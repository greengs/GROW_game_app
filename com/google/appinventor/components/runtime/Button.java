package com.google.appinventor.components.runtime;

import com.google.appinventor.components.annotations.DesignerComponent;
import com.google.appinventor.components.annotations.SimpleEvent;
import com.google.appinventor.components.annotations.SimpleObject;
import com.google.appinventor.components.common.ComponentCategory;

@DesignerComponent(category = ComponentCategory.USERINTERFACE, description = "Button with the ability to detect clicks.  Many aspects of its appearance can be changed, as well as whether it is clickable (<code>Enabled</code>), can be changed in the Designer or in the Blocks Editor.", version = 6)
@SimpleObject
public final class Button extends ButtonBase {
  public Button(ComponentContainer paramComponentContainer) {
    super(paramComponentContainer);
  }
  
  @SimpleEvent(description = "User tapped and released the button.")
  public void Click() {
    EventDispatcher.dispatchEvent(this, "Click", new Object[0]);
  }
  
  @SimpleEvent(description = "User held the button down.")
  public boolean LongClick() {
    return EventDispatcher.dispatchEvent(this, "LongClick", new Object[0]);
  }
  
  public void click() {
    Click();
  }
  
  public boolean longClick() {
    return LongClick();
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/com/google/appinventor/components/runtime/Button.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */