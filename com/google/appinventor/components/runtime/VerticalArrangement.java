package com.google.appinventor.components.runtime;

import com.google.appinventor.components.annotations.DesignerComponent;
import com.google.appinventor.components.annotations.SimpleObject;
import com.google.appinventor.components.common.ComponentCategory;
import com.google.appinventor.components.common.ComponentConstants;

@DesignerComponent(category = ComponentCategory.LAYOUT, description = "<p>A formatting element in which to place components that should be displayed one below another.  (The first child component is stored on top, the second beneath it, etc.)  If you wish to have components displayed next to one another, use <code>HorizontalArrangement</code> instead.</p>", version = 3)
@SimpleObject
public class VerticalArrangement extends HVArrangement {
  public VerticalArrangement(ComponentContainer paramComponentContainer) {
    super(paramComponentContainer, 1, ComponentConstants.NONSCROLLABLE_ARRANGEMENT);
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/com/google/appinventor/components/runtime/VerticalArrangement.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */