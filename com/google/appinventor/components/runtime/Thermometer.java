package com.google.appinventor.components.runtime;

import com.google.appinventor.components.annotations.DesignerComponent;
import com.google.appinventor.components.annotations.SimpleEvent;
import com.google.appinventor.components.annotations.SimpleObject;
import com.google.appinventor.components.annotations.SimpleProperty;
import com.google.appinventor.components.common.ComponentCategory;

@DesignerComponent(category = ComponentCategory.SENSORS, description = "A sensor component that can measure the ambient (external) temperature. Most Android devices do not have this sensor.", iconName = "images/thermometer.png", nonVisible = true, version = 1)
@SimpleObject
public class Thermometer extends SingleValueSensor {
  public Thermometer(ComponentContainer paramComponentContainer) {
    super(paramComponentContainer.$form(), 13);
  }
  
  @SimpleProperty(description = "The temperature in degrees Celsius, if the sensor is available and enabled")
  public float Temperature() {
    return getValue();
  }
  
  @SimpleEvent(description = "Called when a change is detected in the temperature (in degrees Celsius).")
  public void TemperatureChanged(float paramFloat) {
    EventDispatcher.dispatchEvent(this, "TemperatureChanged", new Object[] { Float.valueOf(paramFloat) });
  }
  
  protected void onValueChanged(float paramFloat) {
    TemperatureChanged(paramFloat);
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/com/google/appinventor/components/runtime/Thermometer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */