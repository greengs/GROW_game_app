package com.google.appinventor.components.runtime;

import com.google.appinventor.components.annotations.DesignerComponent;
import com.google.appinventor.components.annotations.SimpleObject;
import com.google.appinventor.components.common.ComponentCategory;
import com.google.appinventor.components.common.ComponentConstants;

@DesignerComponent(category = ComponentCategory.LAYOUT, description = "<p>A formatting element in which to place components that should be displayed from left to right.  If you wish to have components displayed one over another, use <code>VerticalArrangement</code> instead.</p><p>This version is scrollable.", version = 1)
@SimpleObject
public class HorizontalScrollArrangement extends HVArrangement {
  public HorizontalScrollArrangement(ComponentContainer paramComponentContainer) {
    super(paramComponentContainer, 0, ComponentConstants.SCROLLABLE_ARRANGEMENT);
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/com/google/appinventor/components/runtime/HorizontalScrollArrangement.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */