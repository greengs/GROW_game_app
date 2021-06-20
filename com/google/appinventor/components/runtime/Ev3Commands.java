package com.google.appinventor.components.runtime;

import com.google.appinventor.components.annotations.DesignerComponent;
import com.google.appinventor.components.annotations.SimpleFunction;
import com.google.appinventor.components.annotations.SimpleObject;
import com.google.appinventor.components.annotations.UsesPermissions;
import com.google.appinventor.components.common.ComponentCategory;
import com.google.appinventor.components.runtime.util.Ev3BinaryParser;

@DesignerComponent(category = ComponentCategory.LEGOMINDSTORMS, description = "A component that provides a low-level interface to a LEGO MINDSTORMS EV3 robot, with functions to send system or direct commands to EV3 robots.", iconName = "images/legoMindstormsEv3.png", nonVisible = true, version = 1)
@SimpleObject
@UsesPermissions(permissionNames = "android.permission.INTERNET,android.permission.WRITE_EXTERNAL_STORAGE,android.permission.READ_EXTERNAL_STORAGE")
public class Ev3Commands extends LegoMindstormsEv3Base {
  public Ev3Commands(ComponentContainer paramComponentContainer) {
    super(paramComponentContainer, "Ev3Commands");
  }
  
  @SimpleFunction(description = "Get the battery current.")
  public double GetBatteryCurrent() {
    byte[] arrayOfByte = sendCommand(Thread.currentThread().getStackTrace()[1].getMethodName(), Ev3BinaryParser.encodeDirectCommand((byte)-127, true, 4, 0, "cg", new Object[] { Byte.valueOf((byte)2), Byte.valueOf((byte)0) }), true);
    return (arrayOfByte != null && arrayOfByte.length == 5 && arrayOfByte[0] == 2) ? ((Float)Ev3BinaryParser.unpack("xf", arrayOfByte)[0]).floatValue() : -1.0D;
  }
  
  @SimpleFunction(description = "Get the battery voltage.")
  public double GetBatteryVoltage() {
    byte[] arrayOfByte = sendCommand(Thread.currentThread().getStackTrace()[1].getMethodName(), Ev3BinaryParser.encodeDirectCommand((byte)-127, true, 4, 0, "cg", new Object[] { Byte.valueOf((byte)1), Byte.valueOf((byte)0) }), true);
    return (arrayOfByte != null && arrayOfByte.length == 5 && arrayOfByte[0] == 2) ? ((Float)Ev3BinaryParser.unpack("xf", arrayOfByte)[0]).floatValue() : -1.0D;
  }
  
  @SimpleFunction(description = "Get the firmware build on EV3.")
  public String GetFirmwareBuild() {
    String str = Thread.currentThread().getStackTrace()[1].getMethodName();
    byte[] arrayOfByte = sendCommand(str, Ev3BinaryParser.encodeDirectCommand((byte)-127, true, 100, 0, "cg", new Object[] { Byte.valueOf(127), Byte.valueOf((byte)0) }), true);
    if (arrayOfByte != null && arrayOfByte[0] == 2)
      return String.valueOf(Ev3BinaryParser.unpack("xS", arrayOfByte)[0]); 
    this.form.dispatchErrorOccurredEvent(this, str, 3102, new Object[0]);
    return null;
  }
  
  @SimpleFunction(description = "Get the firmware version on EV3.")
  public String GetFirmwareVersion() {
    String str = Thread.currentThread().getStackTrace()[1].getMethodName();
    byte[] arrayOfByte = sendCommand(str, Ev3BinaryParser.encodeDirectCommand((byte)-127, true, 100, 0, "ccg", new Object[] { Byte.valueOf((byte)10), Short.valueOf((short)100), Byte.valueOf((byte)0) }), true);
    if (arrayOfByte != null && arrayOfByte[0] == 2)
      return String.valueOf(Ev3BinaryParser.unpack("xS", arrayOfByte)[0]); 
    this.form.dispatchErrorOccurredEvent(this, str, 3102, new Object[0]);
    return null;
  }
  
  @SimpleFunction(description = "Get the hardware version of EV3.")
  public String GetHardwareVersion() {
    String str = Thread.currentThread().getStackTrace()[1].getMethodName();
    byte[] arrayOfByte = sendCommand(str, Ev3BinaryParser.encodeDirectCommand((byte)-127, true, 100, 0, "ccg", new Object[] { Byte.valueOf((byte)9), Short.valueOf((short)100), Byte.valueOf((byte)0) }), true);
    if (arrayOfByte != null && arrayOfByte[0] == 2)
      return String.valueOf(Ev3BinaryParser.unpack("xS", arrayOfByte)[0]); 
    this.form.dispatchErrorOccurredEvent(this, str, 3102, new Object[0]);
    return null;
  }
  
  @SimpleFunction(description = "Get the OS build on EV3.")
  public String GetOSBuild() {
    String str = Thread.currentThread().getStackTrace()[1].getMethodName();
    byte[] arrayOfByte = sendCommand(str, Ev3BinaryParser.encodeDirectCommand((byte)3, true, 100, 0, "ccg", new Object[] { Byte.valueOf((byte)12), Short.valueOf((short)100), Byte.valueOf((byte)0) }), true);
    if (arrayOfByte != null && arrayOfByte[0] == 2)
      return String.valueOf(Ev3BinaryParser.unpack("xS", arrayOfByte)[0]); 
    this.form.dispatchErrorOccurredEvent(this, str, 3102, new Object[0]);
    return null;
  }
  
  @SimpleFunction(description = "Get the OS version on EV3.")
  public String GetOSVersion() {
    String str = Thread.currentThread().getStackTrace()[1].getMethodName();
    byte[] arrayOfByte = sendCommand(str, Ev3BinaryParser.encodeDirectCommand((byte)-127, true, 100, 0, "ccg", new Object[] { Byte.valueOf((byte)3), Short.valueOf((short)100), Byte.valueOf((byte)0) }), true);
    if (arrayOfByte != null && arrayOfByte[0] == 2)
      return String.valueOf(Ev3BinaryParser.unpack("xS", arrayOfByte)[0]); 
    this.form.dispatchErrorOccurredEvent(this, str, 3102, new Object[0]);
    return null;
  }
  
  @SimpleFunction(description = "Keep the EV3 brick from shutdown for a period of time.")
  public void KeepAlive(int paramInt) {
    String str = Thread.currentThread().getStackTrace()[1].getMethodName();
    if (paramInt < 0 || paramInt > 255) {
      this.form.dispatchErrorOccurredEvent(this, str, 3103, new Object[] { str });
      return;
    } 
    sendCommand(str, Ev3BinaryParser.encodeDirectCommand((byte)-112, false, 0, 0, "c", new Object[] { Byte.valueOf((byte)paramInt) }), false);
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/com/google/appinventor/components/runtime/Ev3Commands.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */