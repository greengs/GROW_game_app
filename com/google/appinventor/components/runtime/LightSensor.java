package com.google.appinventor.components.runtime;

import com.google.appinventor.components.annotations.DesignerComponent;
import com.google.appinventor.components.annotations.SimpleEvent;
import com.google.appinventor.components.annotations.SimpleObject;
import com.google.appinventor.components.annotations.SimpleProperty;
import com.google.appinventor.components.common.ComponentCategory;

@DesignerComponent(category = ComponentCategory.SENSORS, description = "A sensor component that can measure the light level.", iconName = "images/lightsensor.png", nonVisible = true, version = 1)
@SimpleObject
public class LightSensor extends BufferedSingleValueSensor {
  private static final int BUFFER_SIZE = 10;
  
  public LightSensor(ComponentContainer paramComponentContainer) {
    super(paramComponentContainer.$form(), 5, 10);
  }
  
  @SimpleProperty(description = "The average of the 10 most recent light levels measured, in lux.")
  public float AverageLux() {
    return getAverageValue();
  }
  
  @SimpleEvent(description = "Called when a change is detected in the light level.")
  public void LightChanged(float paramFloat) {
    EventDispatcher.dispatchEvent(this, "LightChanged", new Object[] { Float.valueOf(paramFloat) });
  }
  
  @SimpleProperty(description = "The most recent light level, in lux, if the sensor is available and enabled.")
  public float Lux() {
    return getValue();
  }
  
  protected void onValueChanged(float paramFloat) {
    LightChanged(paramFloat);
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/com/google/appinventor/components/runtime/LightSensor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */