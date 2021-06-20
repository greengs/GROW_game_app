package com.google.appinventor.components.runtime;

import com.google.appinventor.components.annotations.DesignerProperty;
import com.google.appinventor.components.annotations.PropertyCategory;
import com.google.appinventor.components.annotations.SimpleObject;
import com.google.appinventor.components.annotations.SimpleProperty;
import com.google.appinventor.components.runtime.util.Ev3BinaryParser;

@SimpleObject
public class LegoMindstormsEv3Sensor extends LegoMindstormsEv3Base {
  protected static final String DEFAULT_SENSOR_PORT = "1";
  
  protected int sensorPortNumber;
  
  protected LegoMindstormsEv3Sensor(ComponentContainer paramComponentContainer, String paramString) {
    super(paramComponentContainer, paramString);
    SensorPort("1");
  }
  
  @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "The sensor port that the sensor is connected to.", userVisible = false)
  public String SensorPort() {
    return portNumberToSensorPortLetter(this.sensorPortNumber);
  }
  
  @DesignerProperty(defaultValue = "1", editorType = "lego_ev3_sensor_port")
  @SimpleProperty
  public void SensorPort(String paramString) {
    setSensorPort("SensorPort", paramString);
  }
  
  protected final int readInputPercentage(String paramString, int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
    if (paramInt1 < 0 || paramInt1 > 3 || paramInt2 < 0 || paramInt2 > 3 || paramInt4 < -1 || paramInt4 > 7)
      throw new IllegalArgumentException(); 
    byte[] arrayOfByte = sendCommand(paramString, Ev3BinaryParser.encodeDirectCommand((byte)-103, true, 1, 0, "ccccccg", new Object[] { Byte.valueOf((byte)27), Byte.valueOf((byte)paramInt1), Byte.valueOf((byte)paramInt2), Byte.valueOf((byte)paramInt3), Byte.valueOf((byte)paramInt4), Byte.valueOf((byte)1), Byte.valueOf((byte)0) }), true);
    return (arrayOfByte != null && arrayOfByte.length == 2 && arrayOfByte[0] == 2) ? arrayOfByte[1] : -1;
  }
  
  protected final double readInputSI(String paramString, int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
    if (paramInt1 < 0 || paramInt1 > 3 || paramInt2 < 0 || paramInt2 > 3 || paramInt4 < -1 || paramInt4 > 7)
      throw new IllegalArgumentException(); 
    byte[] arrayOfByte = sendCommand(paramString, Ev3BinaryParser.encodeDirectCommand((byte)-103, true, 4, 0, "ccccccg", new Object[] { Byte.valueOf((byte)29), Byte.valueOf((byte)paramInt1), Byte.valueOf((byte)paramInt2), Byte.valueOf((byte)paramInt3), Byte.valueOf((byte)paramInt4), Byte.valueOf((byte)1), Byte.valueOf((byte)0) }), true);
    if (arrayOfByte != null && arrayOfByte.length == 5 && arrayOfByte[0] == 2)
      return ((Float)Ev3BinaryParser.unpack("xf", arrayOfByte)[0]).floatValue(); 
    this.form.dispatchErrorOccurredEvent(this, paramString, 3102, new Object[0]);
    return -1.0D;
  }
  
  protected final void setSensorPort(String paramString1, String paramString2) {
    try {
      this.sensorPortNumber = sensorPortLetterToPortNumber(paramString2);
      return;
    } catch (IllegalArgumentException illegalArgumentException) {
      this.form.dispatchErrorOccurredEvent(this, paramString1, 3105, new Object[] { paramString2 });
      return;
    } 
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/com/google/appinventor/components/runtime/LegoMindstormsEv3Sensor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */