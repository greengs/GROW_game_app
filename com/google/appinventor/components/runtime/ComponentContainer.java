package com.google.appinventor.components.runtime;

import android.app.Activity;

public interface ComponentContainer {
  void $add(AndroidViewComponent paramAndroidViewComponent);
  
  Activity $context();
  
  Form $form();
  
  int Height();
  
  int Width();
  
  void setChildHeight(AndroidViewComponent paramAndroidViewComponent, int paramInt);
  
  void setChildWidth(AndroidViewComponent paramAndroidViewComponent, int paramInt);
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/com/google/appinventor/components/runtime/ComponentContainer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */