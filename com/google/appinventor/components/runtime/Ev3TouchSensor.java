package com.google.appinventor.components.runtime;

import android.os.Handler;
import com.google.appinventor.components.annotations.DesignerComponent;
import com.google.appinventor.components.annotations.DesignerProperty;
import com.google.appinventor.components.annotations.PropertyCategory;
import com.google.appinventor.components.annotations.SimpleEvent;
import com.google.appinventor.components.annotations.SimpleFunction;
import com.google.appinventor.components.annotations.SimpleObject;
import com.google.appinventor.components.annotations.SimpleProperty;
import com.google.appinventor.components.common.ComponentCategory;

@DesignerComponent(category = ComponentCategory.LEGOMINDSTORMS, description = "A component that provides a high-level interface to a touch sensor on a LEGO MINDSTORMS EV3 robot.", iconName = "images/legoMindstormsEv3.png", nonVisible = true, version = 1)
@SimpleObject
public class Ev3TouchSensor extends LegoMindstormsEv3Sensor implements Deleteable {
  private static final int DELAY_MILLISECONDS = 50;
  
  private static final int SENSOR_MODE_TOUCH = 0;
  
  private static final String SENSOR_MODE_TOUCH_STRING = "touch";
  
  private static final int SENSOR_TYPE = 16;
  
  private static final int SENSOR_VALUE_THRESHOLD = 50;
  
  private Handler eventHandler = new Handler();
  
  private int mode = 0;
  
  private String modeString = "touch";
  
  private boolean pressedEventEnabled;
  
  private boolean releasedEventEnabled;
  
  private int savedPressedValue = -1;
  
  private final Runnable sensorValueChecker = new Runnable() {
      public void run() {
        if (Ev3TouchSensor.this.bluetooth != null && Ev3TouchSensor.this.bluetooth.IsConnected()) {
          int i = Ev3TouchSensor.this.getPressedValue("");
          if (Ev3TouchSensor.this.savedPressedValue < 0) {
            Ev3TouchSensor.access$102(Ev3TouchSensor.this, i);
            Ev3TouchSensor.this.eventHandler.postDelayed(this, 50L);
            return;
          } 
          if (Ev3TouchSensor.this.savedPressedValue < 50) {
            if (Ev3TouchSensor.this.releasedEventEnabled && i >= 50)
              Ev3TouchSensor.this.Pressed(); 
          } else if (Ev3TouchSensor.this.pressedEventEnabled && i < 50) {
            Ev3TouchSensor.this.Released();
          } 
          Ev3TouchSensor.access$102(Ev3TouchSensor.this, i);
        } 
        Ev3TouchSensor.this.eventHandler.postDelayed(this, 50L);
      }
    };
  
  public Ev3TouchSensor(ComponentContainer paramComponentContainer) {
    super(paramComponentContainer, "Ev3TouchSensor");
    this.eventHandler.post(this.sensorValueChecker);
    PressedEventEnabled(false);
    ReleasedEventEnabled(false);
  }
  
  private int getPressedValue(String paramString) {
    return readInputPercentage(paramString, 0, this.sensorPortNumber, 16, this.mode);
  }
  
  @SimpleFunction(description = "Returns true if the touch sensor is pressed.")
  public boolean IsPressed() {
    return (getPressedValue("IsPressed") >= 50);
  }
  
  @SimpleEvent(description = "Called when the touch sensor is pressed.")
  public void Pressed() {
    EventDispatcher.dispatchEvent(this, "Pressed", new Object[0]);
  }
  
  @DesignerProperty(defaultValue = "False", editorType = "boolean")
  @SimpleProperty
  public void PressedEventEnabled(boolean paramBoolean) {
    this.pressedEventEnabled = paramBoolean;
  }
  
  @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "Whether the Released event should fire when the touch sensor is pressed.")
  public boolean PressedEventEnabled() {
    return this.pressedEventEnabled;
  }
  
  @SimpleEvent(description = "Called when the touch sensor is pressed.")
  public void Released() {
    EventDispatcher.dispatchEvent(this, "Released", new Object[0]);
  }
  
  @DesignerProperty(defaultValue = "False", editorType = "boolean")
  @SimpleProperty
  public void ReleasedEventEnabled(boolean paramBoolean) {
    this.releasedEventEnabled = paramBoolean;
  }
  
  @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "Whether the Released event should fire when the touch sensor is released.")
  public boolean ReleasedEventEnabled() {
    return this.releasedEventEnabled;
  }
  
  public void onDelete() {
    this.eventHandler.removeCallbacks(this.sensorValueChecker);
    super.onDelete();
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/com/google/appinventor/components/runtime/Ev3TouchSensor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */