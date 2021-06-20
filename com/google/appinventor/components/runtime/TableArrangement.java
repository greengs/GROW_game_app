package com.google.appinventor.components.runtime;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import com.google.appinventor.components.annotations.DesignerComponent;
import com.google.appinventor.components.annotations.DesignerProperty;
import com.google.appinventor.components.annotations.SimpleObject;
import com.google.appinventor.components.annotations.SimpleProperty;
import com.google.appinventor.components.common.ComponentCategory;
import com.google.appinventor.components.runtime.util.ViewUtil;

@DesignerComponent(category = ComponentCategory.LAYOUT, description = "<p>A formatting element in which to place components that should be displayed in tabular form.</p>", version = 1)
@SimpleObject
public class TableArrangement extends AndroidViewComponent implements Component, ComponentContainer {
  private final Activity context;
  
  private final TableLayout viewLayout;
  
  public TableArrangement(ComponentContainer paramComponentContainer) {
    super(paramComponentContainer);
    this.context = paramComponentContainer.$context();
    this.viewLayout = new TableLayout((Context)this.context, 2, 2);
    paramComponentContainer.$add(this);
  }
  
  public void $add(AndroidViewComponent paramAndroidViewComponent) {
    this.viewLayout.add(paramAndroidViewComponent);
  }
  
  public Activity $context() {
    return this.context;
  }
  
  public Form $form() {
    return this.container.$form();
  }
  
  @SimpleProperty(userVisible = false)
  public int Columns() {
    return this.viewLayout.getNumColumns();
  }
  
  @DesignerProperty(defaultValue = "2", editorType = "non_negative_integer")
  @SimpleProperty(userVisible = false)
  public void Columns(int paramInt) {
    this.viewLayout.setNumColumns(paramInt);
  }
  
  @SimpleProperty(userVisible = false)
  public int Rows() {
    return this.viewLayout.getNumRows();
  }
  
  @DesignerProperty(defaultValue = "2", editorType = "non_negative_integer")
  @SimpleProperty(userVisible = false)
  public void Rows(int paramInt) {
    this.viewLayout.setNumRows(paramInt);
  }
  
  public View getView() {
    return (View)this.viewLayout.getLayoutManager();
  }
  
  public void setChildHeight(AndroidViewComponent paramAndroidViewComponent, int paramInt) {
    int i = paramInt;
    if (paramInt <= -1000) {
      i = this.container.$form().Height();
      if (i > -1000 && i <= 0) {
        i = -1;
      } else {
        i = -(paramInt + 1000) * i / 100;
      } 
    } 
    paramAndroidViewComponent.setLastHeight(i);
    ViewUtil.setChildHeightForTableLayout(paramAndroidViewComponent.getView(), i);
  }
  
  public void setChildWidth(AndroidViewComponent paramAndroidViewComponent, int paramInt) {
    System.err.println("TableArrangment.setChildWidth: width = " + paramInt + " component = " + paramAndroidViewComponent);
    int i = paramInt;
    if (paramInt <= -1000) {
      i = this.container.$form().Width();
      if (i > -1000 && i <= 0) {
        i = -1;
      } else {
        System.err.println("%%TableArrangement.setChildWidth(): width = " + paramInt + " parent Width = " + i + " child = " + paramAndroidViewComponent);
        i = -(paramInt + 1000) * i / 100;
      } 
    } 
    paramAndroidViewComponent.setLastWidth(i);
    ViewUtil.setChildWidthForTableLayout(paramAndroidViewComponent.getView(), i);
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/com/google/appinventor/components/runtime/TableArrangement.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */