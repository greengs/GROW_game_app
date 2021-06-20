package com.google.appinventor.components.runtime;

import com.google.appinventor.components.annotations.DesignerComponent;
import com.google.appinventor.components.annotations.SimpleEvent;
import com.google.appinventor.components.annotations.SimpleObject;
import com.google.appinventor.components.annotations.SimpleProperty;
import com.google.appinventor.components.common.ComponentCategory;

@DesignerComponent(category = ComponentCategory.SENSORS, description = "A sensor component that can measure the ambient air pressure.", iconName = "images/barometer.png", nonVisible = true, version = 1)
@SimpleObject
public class Barometer extends SingleValueSensor {
  public Barometer(ComponentContainer paramComponentContainer) {
    super(paramComponentContainer.$form(), 6);
  }
  
  @SimpleProperty(description = "The air pressure in hPa (millibar), if the sensor is available and enabled.")
  public float AirPressure() {
    return getValue();
  }
  
  @SimpleEvent
  public void AirPressureChanged(float paramFloat) {
    EventDispatcher.dispatchEvent(this, "AirPressureChanged", new Object[] { Float.valueOf(paramFloat) });
  }
  
  protected void onValueChanged(float paramFloat) {
    AirPressureChanged(paramFloat);
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/com/google/appinventor/components/runtime/Barometer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */