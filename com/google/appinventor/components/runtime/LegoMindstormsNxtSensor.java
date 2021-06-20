package com.google.appinventor.components.runtime;

import com.google.appinventor.components.annotations.PropertyCategory;
import com.google.appinventor.components.annotations.SimpleObject;
import com.google.appinventor.components.annotations.SimpleProperty;

@SimpleObject
public abstract class LegoMindstormsNxtSensor extends LegoMindstormsNxtBase {
  static final int SENSOR_MODE_ANGLESTEPMODE = 224;
  
  static final int SENSOR_MODE_BOOLEANMODE = 32;
  
  static final int SENSOR_MODE_CELSIUSMODE = 160;
  
  static final int SENSOR_MODE_FAHRENHEITMODE = 192;
  
  static final int SENSOR_MODE_MASK_MODE = 224;
  
  static final int SENSOR_MODE_MASK_SLOPE = 31;
  
  static final int SENSOR_MODE_PCTFULLSCALEMODE = 128;
  
  static final int SENSOR_MODE_PERIODCOUNTERMODE = 96;
  
  static final int SENSOR_MODE_RAWMODE = 0;
  
  static final int SENSOR_MODE_TRANSITIONCNTMODE = 64;
  
  static final int SENSOR_TYPE_ANGLE = 4;
  
  static final int SENSOR_TYPE_CUSTOM = 9;
  
  static final int SENSOR_TYPE_LIGHT_ACTIVE = 5;
  
  static final int SENSOR_TYPE_LIGHT_INACTIVE = 6;
  
  static final int SENSOR_TYPE_LOWSPEED = 10;
  
  static final int SENSOR_TYPE_LOWSPEED_9V = 11;
  
  static final int SENSOR_TYPE_NO_SENSOR = 0;
  
  static final int SENSOR_TYPE_REFLECTION = 3;
  
  static final int SENSOR_TYPE_SOUND_DB = 7;
  
  static final int SENSOR_TYPE_SOUND_DBA = 8;
  
  static final int SENSOR_TYPE_SWITCH = 1;
  
  static final int SENSOR_TYPE_TEMPERATURE = 2;
  
  protected int port;
  
  private String sensorPortLetter;
  
  protected LegoMindstormsNxtSensor(ComponentContainer paramComponentContainer, String paramString) {
    super(paramComponentContainer, paramString);
  }
  
  @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "The sensor port that the sensor is connected to.", userVisible = false)
  public String SensorPort() {
    return this.sensorPortLetter;
  }
  
  public abstract void SensorPort(String paramString);
  
  public void afterConnect(BluetoothConnectionBase paramBluetoothConnectionBase) {
    initializeSensor("Connect");
  }
  
  protected abstract void initializeSensor(String paramString);
  
  protected final void setSensorPort(String paramString) {
    try {
      int i = convertSensorPortLetterToNumber(paramString);
      this.sensorPortLetter = paramString;
      this.port = i;
      if (this.bluetooth != null && this.bluetooth.IsConnected())
        initializeSensor("SensorPort"); 
      return;
    } catch (IllegalArgumentException illegalArgumentException) {
      this.form.dispatchErrorOccurredEvent(this, "SensorPort", 408, new Object[] { paramString });
      return;
    } 
  }
  
  static class SensorValue<T> {
    final boolean valid;
    
    final T value;
    
    SensorValue(boolean param1Boolean, T param1T) {
      this.valid = param1Boolean;
      this.value = param1T;
    }
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/com/google/appinventor/components/runtime/LegoMindstormsNxtSensor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */