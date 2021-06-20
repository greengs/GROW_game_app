package com.google.appinventor.components.runtime;

import com.google.appinventor.components.annotations.SimpleObject;

@SimpleObject
public abstract class AndroidNonvisibleComponent implements Component {
  protected final Form form;
  
  protected AndroidNonvisibleComponent(Form paramForm) {
    this.form = paramForm;
  }
  
  public HandlesEventDispatching getDispatchDelegate() {
    return this.form;
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/com/google/appinventor/components/runtime/AndroidNonvisibleComponent.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */