package com.google.appinventor.components.runtime;

import com.google.appinventor.components.annotations.DesignerComponent;
import com.google.appinventor.components.annotations.SimpleFunction;
import com.google.appinventor.components.annotations.SimpleObject;
import com.google.appinventor.components.annotations.UsesPermissions;
import com.google.appinventor.components.common.ComponentCategory;
import com.google.appinventor.components.runtime.util.Ev3BinaryParser;

@DesignerComponent(category = ComponentCategory.LEGOMINDSTORMS, description = "A component that provides a high-level interface to sound functionalities on LEGO MINDSTORMS EV3 robot.", iconName = "images/legoMindstormsEv3.png", nonVisible = true, version = 1)
@SimpleObject
@UsesPermissions(permissionNames = "android.permission.INTERNET,android.permission.WRITE_EXTERNAL_STORAGE,android.permission.READ_EXTERNAL_STORAGE")
public class Ev3Sound extends LegoMindstormsEv3Base {
  public Ev3Sound(ComponentContainer paramComponentContainer) {
    super(paramComponentContainer, "Ev3Sound");
  }
  
  @SimpleFunction(description = "Make the robot play a tone.")
  public void PlayTone(int paramInt1, int paramInt2, int paramInt3) {
    String str = Thread.currentThread().getStackTrace()[1].getMethodName();
    if (paramInt1 < 0 || paramInt1 > 100 || paramInt2 < 250 || paramInt2 > 10000 || paramInt3 < 0 || paramInt3 > 65535) {
      this.form.dispatchErrorOccurredEvent(this, str, 3103, new Object[] { str });
      return;
    } 
    sendCommand(str, Ev3BinaryParser.encodeDirectCommand((byte)-108, true, 0, 0, "cccc", new Object[] { Byte.valueOf((byte)1), Byte.valueOf((byte)paramInt1), Short.valueOf((short)paramInt2), Short.valueOf((short)paramInt3) }), true);
  }
  
  @SimpleFunction(description = "Stop any sound on the robot.")
  public void StopSound() {
    sendCommand(Thread.currentThread().getStackTrace()[1].getMethodName(), Ev3BinaryParser.encodeDirectCommand((byte)-108, false, 0, 0, "c", new Object[] { Byte.valueOf((byte)0) }), false);
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/com/google/appinventor/components/runtime/Ev3Sound.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */