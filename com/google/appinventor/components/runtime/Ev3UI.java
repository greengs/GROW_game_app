package com.google.appinventor.components.runtime;

import com.google.appinventor.components.annotations.DesignerComponent;
import com.google.appinventor.components.annotations.SimpleFunction;
import com.google.appinventor.components.annotations.SimpleObject;
import com.google.appinventor.components.annotations.UsesPermissions;
import com.google.appinventor.components.common.ComponentCategory;
import com.google.appinventor.components.runtime.util.Ev3BinaryParser;

@DesignerComponent(category = ComponentCategory.LEGOMINDSTORMS, description = "A component that provides a high-level interface to a LEGO MINDSTORMS EV3 robot, with functions to draw graphs on EV3 screen.", iconName = "images/legoMindstormsEv3.png", nonVisible = true, version = 1)
@SimpleObject
@UsesPermissions(permissionNames = "android.permission.INTERNET,android.permission.WRITE_EXTERNAL_STORAGE,android.permission.READ_EXTERNAL_STORAGE")
public class Ev3UI extends LegoMindstormsEv3Base {
  public Ev3UI(ComponentContainer paramComponentContainer) {
    super(paramComponentContainer, "Ev3UI");
  }
  
  @SimpleFunction(description = "Draw a circle on the screen.")
  public void DrawCircle(int paramInt1, int paramInt2, int paramInt3, int paramInt4, boolean paramBoolean) {
    byte b;
    if ((paramInt1 != 0 && paramInt1 != 1) || paramInt4 < 0) {
      this.form.dispatchErrorOccurredEvent(this, "DrawCircle", 3103, new Object[] { "DrawCircle" });
      return;
    } 
    if (paramBoolean) {
      b = 24;
    } else {
      b = 4;
    } 
    sendCommand("DrawCircle", Ev3BinaryParser.encodeDirectCommand((byte)-124, false, 0, 0, "ccccc", new Object[] { Byte.valueOf(b), Byte.valueOf((byte)paramInt1), Short.valueOf((short)paramInt2), Short.valueOf((short)paramInt3), Short.valueOf((short)paramInt4) }), false);
    sendCommand("DrawCircle", Ev3BinaryParser.encodeDirectCommand((byte)-124, false, 0, 0, "c", new Object[] { Byte.valueOf((byte)0) }), false);
  }
  
  @SimpleFunction(description = "Draw a built-in icon on screen.")
  public void DrawIcon(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5) {
    if (paramInt1 != 0 && paramInt1 != 1) {
      this.form.dispatchErrorOccurredEvent(this, "DrawIcon", 3103, new Object[] { "DrawIcon" });
      return;
    } 
    sendCommand("DrawIcon", Ev3BinaryParser.encodeDirectCommand((byte)-124, false, 0, 0, "cccccc", new Object[] { Byte.valueOf((byte)6), Byte.valueOf((byte)paramInt1), Short.valueOf((short)paramInt2), Short.valueOf((short)paramInt3), Integer.valueOf(paramInt4), Integer.valueOf(paramInt5) }), false);
    sendCommand("DrawIcon", Ev3BinaryParser.encodeDirectCommand((byte)-124, false, 0, 0, "c", new Object[] { Byte.valueOf((byte)0) }), false);
  }
  
  @SimpleFunction(description = "Draw a line on the screen.")
  public void DrawLine(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5) {
    if (paramInt1 != 0 && paramInt1 != 1) {
      this.form.dispatchErrorOccurredEvent(this, "DrawLine", 3103, new Object[] { "DrawLine" });
      return;
    } 
    sendCommand("DrawLine", Ev3BinaryParser.encodeDirectCommand((byte)-124, false, 0, 0, "cccccc", new Object[] { Byte.valueOf((byte)3), Byte.valueOf((byte)paramInt1), Short.valueOf((short)paramInt2), Short.valueOf((short)paramInt3), Short.valueOf((short)paramInt4), Short.valueOf((short)paramInt5) }), false);
    sendCommand("DrawLine", Ev3BinaryParser.encodeDirectCommand((byte)-124, false, 0, 0, "c", new Object[] { Byte.valueOf((byte)0) }), false);
  }
  
  @SimpleFunction(description = "Draw a point on the screen.")
  public void DrawPoint(int paramInt1, int paramInt2, int paramInt3) {
    if (paramInt1 != 0 && paramInt1 != 1) {
      this.form.dispatchErrorOccurredEvent(this, "DrawPoint", 3103, new Object[] { "DrawPoint" });
      return;
    } 
    sendCommand("DrawPoint", Ev3BinaryParser.encodeDirectCommand((byte)-124, false, 0, 0, "cccc", new Object[] { Byte.valueOf((byte)2), Byte.valueOf((byte)paramInt1), Short.valueOf((short)paramInt2), Short.valueOf((short)paramInt3) }), false);
    sendCommand("DrawPoint", Ev3BinaryParser.encodeDirectCommand((byte)-124, false, 0, 0, "c", new Object[] { Byte.valueOf((byte)0) }), false);
  }
  
  @SimpleFunction(description = "Draw a rectangle on the screen.")
  public void DrawRect(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, boolean paramBoolean) {
    byte b;
    if (paramInt1 != 0 && paramInt1 != 1) {
      this.form.dispatchErrorOccurredEvent(this, "DrawRect", 3103, new Object[] { "DrawRect" });
      return;
    } 
    if (paramBoolean) {
      b = 9;
    } else {
      b = 10;
    } 
    sendCommand("DrawRect", Ev3BinaryParser.encodeDirectCommand((byte)-124, false, 0, 0, "cccccc", new Object[] { Byte.valueOf(b), Byte.valueOf((byte)paramInt1), Short.valueOf((short)paramInt2), Short.valueOf((short)paramInt3), Short.valueOf((short)paramInt4), Short.valueOf((short)paramInt5) }), false);
    sendCommand("DrawRect", Ev3BinaryParser.encodeDirectCommand((byte)-124, false, 0, 0, "c", new Object[] { Byte.valueOf((byte)0) }), false);
  }
  
  @SimpleFunction(description = "Fill the screen with a color.")
  public void FillScreen(int paramInt) {
    if (paramInt != 0 && paramInt != 1) {
      this.form.dispatchErrorOccurredEvent(this, "FillScreen", 3103, new Object[] { "FillScreen" });
      return;
    } 
    sendCommand("FillScreen", Ev3BinaryParser.encodeDirectCommand((byte)-124, false, 0, 0, "cccc", new Object[] { Byte.valueOf((byte)19), Byte.valueOf((byte)paramInt), Short.valueOf((short)0), Short.valueOf((short)0) }), false);
    sendCommand("FillScreen", Ev3BinaryParser.encodeDirectCommand((byte)-124, false, 0, 0, "c", new Object[] { Byte.valueOf((byte)0) }), false);
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/com/google/appinventor/components/runtime/Ev3UI.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */