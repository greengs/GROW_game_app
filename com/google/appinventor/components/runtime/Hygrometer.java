package com.google.appinventor.components.runtime;

import com.google.appinventor.components.annotations.DesignerComponent;
import com.google.appinventor.components.annotations.SimpleEvent;
import com.google.appinventor.components.annotations.SimpleObject;
import com.google.appinventor.components.annotations.SimpleProperty;
import com.google.appinventor.components.common.ComponentCategory;

@DesignerComponent(category = ComponentCategory.SENSORS, description = "A sensor component that can measure the relative ambient air humidity. Most Android devices do not have this sensor.", iconName = "images/hygrometer.png", nonVisible = true, version = 1)
@SimpleObject
public class Hygrometer extends SingleValueSensor {
  public Hygrometer(ComponentContainer paramComponentContainer) {
    super(paramComponentContainer.$form(), 12);
  }
  
  @SimpleProperty(description = "The relative ambient humidity as a percentage, if the sensor is available and enabled.")
  public float Humidity() {
    return getValue();
  }
  
  @SimpleEvent(description = "Called when a change is detected in the ambient air humidity (expressed as a percentage).")
  public void HumidityChanged(float paramFloat) {
    EventDispatcher.dispatchEvent(this, "HumidityChanged", new Object[] { Float.valueOf(paramFloat) });
  }
  
  protected void onValueChanged(float paramFloat) {
    HumidityChanged(paramFloat);
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/com/google/appinventor/components/runtime/Hygrometer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */