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

@DesignerComponent(category = ComponentCategory.LEGOMINDSTORMS, description = "A component that provides a high-level interface to a gyro sensor on a LEGO MINDSTORMS EV3 robot.", iconName = "images/legoMindstormsEv3.png", nonVisible = true, version = 1)
@SimpleObject
public class Ev3GyroSensor extends LegoMindstormsEv3Sensor implements Deleteable {
  private static final String DEFAULT_SENSOR_MODE_STRING = "angle";
  
  private static final int DELAY_MILLISECONDS = 50;
  
  private static final int SENSOR_MODE_ANGLE = 0;
  
  private static final String SENSOR_MODE_ANGLE_STRING = "angle";
  
  private static final int SENSOR_MODE_RATE = 1;
  
  private static final String SENSOR_MODE_RATE_STRING = "rate";
  
  private static final int SENSOR_TYPE = 32;
  
  private Handler eventHandler = new Handler();
  
  private int mode = 0;
  
  private String modeString = "angle";
  
  private double previousValue = -1.0D;
  
  private boolean sensorValueChangedEventEnabled = false;
  
  private final Runnable sensorValueChecker = new Runnable() {
      public void run() {
        if (Ev3GyroSensor.this.bluetooth != null && Ev3GyroSensor.this.bluetooth.IsConnected()) {
          double d = Ev3GyroSensor.this.getSensorValue("");
          if (Ev3GyroSensor.this.previousValue < 0.0D) {
            Ev3GyroSensor.access$102(Ev3GyroSensor.this, d);
            Ev3GyroSensor.this.eventHandler.postDelayed(this, 50L);
            return;
          } 
          if (Ev3GyroSensor.this.mode == 1 && Math.abs(d) >= 1.0D) {
            Ev3GyroSensor.this.SensorValueChanged(d);
          } else if (Ev3GyroSensor.this.mode == 0 && Math.abs(d - Ev3GyroSensor.this.previousValue) >= 1.0D) {
            Ev3GyroSensor.this.SensorValueChanged(d);
          } 
          Ev3GyroSensor.access$102(Ev3GyroSensor.this, d);
        } 
        Ev3GyroSensor.this.eventHandler.postDelayed(this, 50L);
      }
    };
  
  public Ev3GyroSensor(ComponentContainer paramComponentContainer) {
    super(paramComponentContainer, "Ev3GyroSensor");
    this.eventHandler.post(this.sensorValueChecker);
    Mode("angle");
    SensorValueChangedEventEnabled(false);
  }
  
  private double getSensorValue(String paramString) {
    return readInputSI(paramString, 0, this.sensorPortNumber, 32, this.mode);
  }
  
  private void setMode(String paramString) {
    if ("angle".equals(paramString)) {
      this.mode = 0;
    } else if ("rate".equals(paramString)) {
      this.mode = 1;
    } else {
      throw new IllegalArgumentException();
    } 
    this.modeString = paramString;
  }
  
  @SimpleFunction(description = "Returns the current angle or rotation speed based on current mode, or -1 if the value cannot be read from sensor.")
  public double GetSensorValue() {
    return getSensorValue("");
  }
  
  @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "The sensor mode can be a text constant of either \"rate\" or \"angle\", which correspond to SetAngleMode or SetRateMode respectively.")
  public String Mode() {
    return this.modeString;
  }
  
  @DesignerProperty(defaultValue = "angle", editorType = "lego_ev3_gyro_sensor_mode")
  @SimpleProperty
  public void Mode(String paramString) {
    try {
      setMode(paramString);
      return;
    } catch (IllegalArgumentException illegalArgumentException) {
      this.form.dispatchErrorOccurredEvent(this, "Mode", 3103, new Object[] { "Mode" });
      return;
    } 
  }
  
  @SimpleEvent(description = "Called then the sensor value changed.")
  public void SensorValueChanged(double paramDouble) {
    EventDispatcher.dispatchEvent(this, "SensorValueChanged", new Object[] { Double.valueOf(paramDouble) });
  }
  
  @DesignerProperty(defaultValue = "False", editorType = "boolean")
  @SimpleProperty
  public void SensorValueChangedEventEnabled(boolean paramBoolean) {
    this.sensorValueChangedEventEnabled = paramBoolean;
  }
  
  @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "Whether the SensorValueChanged event should fire when the sensor value changed.")
  public boolean SensorValueChangedEventEnabled() {
    return this.sensorValueChangedEventEnabled;
  }
  
  @SimpleFunction(description = "Measures the orientation of the sensor.")
  public void SetAngleMode() {
    setMode("angle");
  }
  
  @SimpleFunction(description = "Measures the angular velocity of the sensor.")
  public void SetRateMode() {
    setMode("rate");
  }
  
  public void onDelete() {
    super.onDelete();
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/com/google/appinventor/components/runtime/Ev3GyroSensor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */