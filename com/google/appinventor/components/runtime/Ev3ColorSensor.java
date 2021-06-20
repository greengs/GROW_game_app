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

@DesignerComponent(category = ComponentCategory.LEGOMINDSTORMS, description = "A component that provides a high-level interface to a color sensor on a LEGO MINDSTORMS EV3 robot.", iconName = "images/legoMindstormsEv3.png", nonVisible = true, version = 1)
@SimpleObject
public class Ev3ColorSensor extends LegoMindstormsEv3Sensor implements Deleteable {
  private static final int DEFAULT_BOTTOM_OF_RANGE = 30;
  
  private static final String DEFAULT_SENSOR_MODE_STRING = "reflected";
  
  private static final int DEFAULT_TOP_OF_RANGE = 60;
  
  private static final int DELAY_MILLISECONDS = 50;
  
  private static final int SENSOR_MODE_AMBIENT = 1;
  
  private static final String SENSOR_MODE_AMBIENT_STRING = "ambient";
  
  private static final int SENSOR_MODE_COLOR = 2;
  
  private static final String SENSOR_MODE_COLOR_STRING = "color";
  
  private static final int SENSOR_MODE_REFLECTED = 0;
  
  private static final String SENSOR_MODE_REFLECTED_STRING = "reflected";
  
  private static final int SENSOR_TYPE = 29;
  
  private boolean aboveRangeEventEnabled;
  
  private boolean belowRangeEventEnabled;
  
  private int bottomOfRange;
  
  private boolean colorChangedEventEnabled;
  
  private Handler eventHandler = new Handler();
  
  private int mode = 0;
  
  private String modeString = "reflected";
  
  private int previousColor = -1;
  
  private int previousLightLevel = 0;
  
  private final Runnable sensorValueChecker = new Runnable() {
      public void run() {
        if (Ev3ColorSensor.this.bluetooth != null && Ev3ColorSensor.this.bluetooth.IsConnected())
          if (Ev3ColorSensor.this.mode == 2) {
            int i = Ev3ColorSensor.this.getSensorValue("");
            if (Ev3ColorSensor.this.previousColor < 0) {
              Ev3ColorSensor.access$202(Ev3ColorSensor.this, i);
              Ev3ColorSensor.this.eventHandler.postDelayed(this, 50L);
              return;
            } 
            if (i != Ev3ColorSensor.this.previousColor && Ev3ColorSensor.this.colorChangedEventEnabled)
              Ev3ColorSensor.this.ColorChanged(i, Ev3ColorSensor.this.toColorName("", i)); 
            Ev3ColorSensor.access$202(Ev3ColorSensor.this, i);
          } else {
            int i = Ev3ColorSensor.this.getSensorValue("");
            if (Ev3ColorSensor.this.previousLightLevel < 0) {
              Ev3ColorSensor.access$602(Ev3ColorSensor.this, i);
              Ev3ColorSensor.this.eventHandler.postDelayed(this, 50L);
              return;
            } 
            if (i < Ev3ColorSensor.this.bottomOfRange) {
              if (Ev3ColorSensor.this.belowRangeEventEnabled && Ev3ColorSensor.this.previousLightLevel >= Ev3ColorSensor.this.bottomOfRange)
                Ev3ColorSensor.this.BelowRange(); 
            } else if (i > Ev3ColorSensor.this.topOfRange) {
              if (Ev3ColorSensor.this.aboveRangeEventEnabled && Ev3ColorSensor.this.previousLightLevel <= Ev3ColorSensor.this.topOfRange)
                Ev3ColorSensor.this.AboveRange(); 
            } else if (Ev3ColorSensor.this.withinRangeEventEnabled && (Ev3ColorSensor.this.previousLightLevel < Ev3ColorSensor.this.bottomOfRange || Ev3ColorSensor.this.previousLightLevel > Ev3ColorSensor.this.topOfRange)) {
              Ev3ColorSensor.this.WithinRange();
            } 
            Ev3ColorSensor.access$602(Ev3ColorSensor.this, i);
          }  
        Ev3ColorSensor.this.eventHandler.postDelayed(this, 50L);
      }
    };
  
  private int topOfRange;
  
  private boolean withinRangeEventEnabled;
  
  public Ev3ColorSensor(ComponentContainer paramComponentContainer) {
    super(paramComponentContainer, "Ev3ColorSensor");
    this.eventHandler.post(this.sensorValueChecker);
    TopOfRange(60);
    BottomOfRange(30);
    BelowRangeEventEnabled(false);
    AboveRangeEventEnabled(false);
    WithinRangeEventEnabled(false);
    ColorChangedEventEnabled(false);
    Mode("reflected");
  }
  
  private int getSensorValue(String paramString) {
    int i = readInputPercentage(paramString, 0, this.sensorPortNumber, 29, this.mode);
    if (this.mode == 2) {
      switch (i) {
        default:
          return 0;
        case 12:
          return 1;
        case 25:
          return 2;
        case 37:
          return 3;
        case 50:
          return 4;
        case 62:
          return 5;
        case 75:
          return 6;
        case 87:
          break;
      } 
      return 7;
    } 
    return i;
  }
  
  private void setMode(String paramString) {
    this.previousColor = -1;
    this.previousLightLevel = -1;
    if ("reflected".equals(paramString)) {
      this.mode = 0;
    } else if ("ambient".equals(paramString)) {
      this.mode = 1;
    } else if ("color".equals(paramString)) {
      this.mode = 2;
    } else {
      throw new IllegalArgumentException();
    } 
    this.modeString = paramString;
  }
  
  private String toColorName(String paramString, int paramInt) {
    if (this.mode != 2)
      return "No Color"; 
    switch (paramInt) {
      default:
        return "No Color";
      case 0:
        return "No Color";
      case 1:
        return "Black";
      case 2:
        return "Blue";
      case 3:
        return "Green";
      case 4:
        return "Yellow";
      case 5:
        return "Red";
      case 6:
        return "White";
      case 7:
        break;
    } 
    return "Brown";
  }
  
  @SimpleEvent(description = "Light level has gone above the range.")
  public void AboveRange() {
    EventDispatcher.dispatchEvent(this, "AboveRange", new Object[0]);
  }
  
  @DesignerProperty(defaultValue = "False", editorType = "boolean")
  @SimpleProperty
  public void AboveRangeEventEnabled(boolean paramBoolean) {
    this.aboveRangeEventEnabled = paramBoolean;
  }
  
  @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "Whether the AboveRange event should fire when the light level goes above the TopOfRange.")
  public boolean AboveRangeEventEnabled() {
    return this.aboveRangeEventEnabled;
  }
  
  @SimpleEvent(description = "Light level has gone below the range.")
  public void BelowRange() {
    EventDispatcher.dispatchEvent(this, "BelowRange", new Object[0]);
  }
  
  @DesignerProperty(defaultValue = "False", editorType = "boolean")
  @SimpleProperty
  public void BelowRangeEventEnabled(boolean paramBoolean) {
    this.belowRangeEventEnabled = paramBoolean;
  }
  
  @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "Whether the BelowRange event should fire when the light level goes below the BottomOfRange.")
  public boolean BelowRangeEventEnabled() {
    return this.belowRangeEventEnabled;
  }
  
  @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "The bottom of the range used for the BelowRange, WithinRange, and AboveRange events.")
  public int BottomOfRange() {
    return this.bottomOfRange;
  }
  
  @DesignerProperty(defaultValue = "30", editorType = "non_negative_integer")
  @SimpleProperty
  public void BottomOfRange(int paramInt) {
    this.bottomOfRange = paramInt;
  }
  
  @SimpleEvent(description = "Called when the detected color has changed. The ColorChanged event will occur if the Mode property is set to \"color\" and the ColorChangedEventEnabled property is set to True.")
  public void ColorChanged(int paramInt, String paramString) {
    EventDispatcher.dispatchEvent(this, "ColorChanged", new Object[] { Integer.valueOf(paramInt), paramString });
  }
  
  @DesignerProperty(defaultValue = "False", editorType = "boolean")
  @SimpleProperty
  public void ColorChangedEventEnabled(boolean paramBoolean) {
    this.colorChangedEventEnabled = paramBoolean;
  }
  
  @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "Whether the ColorChanged event should fire when the Mode property is set to \"color\" and the detected color changes.")
  public boolean ColorChangedEventEnabled() {
    return this.colorChangedEventEnabled;
  }
  
  @SimpleFunction(description = "It returns the color code from 0 to 7 corresponding to no color, black, blue, green, yellow, red, white and brown.")
  public int GetColorCode() {
    return (this.mode != 2) ? 0 : getSensorValue("GetColorCode");
  }
  
  @SimpleFunction(description = "Return the color name in one of \"No Color\", \"Black\", \"Blue\", \"Green\", \"Yellow\", \"Red\", \"White\", \"Brown\".")
  public String GetColorName() {
    return (this.mode != 2) ? "No Color" : toColorName("GetColorName", getSensorValue("GetColorName"));
  }
  
  @SimpleFunction(description = "It returns the light level in percentage, or -1 when the light level cannot be read.")
  public int GetLightLevel() {
    return (this.mode == 2) ? -1 : getSensorValue("GetLightLevel");
  }
  
  @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "Get the current sensor mode.")
  public String Mode() {
    return this.modeString;
  }
  
  @DesignerProperty(defaultValue = "reflected", editorType = "lego_ev3_color_sensor_mode")
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
  
  @SimpleFunction(description = "Make the sensor read the light level without reflected light.")
  public void SetAmbientMode() {
    try {
      setMode("ambient");
      return;
    } catch (IllegalArgumentException illegalArgumentException) {
      this.form.dispatchErrorOccurredEvent(this, "SetAmbientMode", 3103, new Object[] { "SetAmbientMode" });
      return;
    } 
  }
  
  @SimpleFunction(description = "Enter the color detection mode.")
  public void SetColorMode() {
    try {
      setMode("color");
      return;
    } catch (IllegalArgumentException illegalArgumentException) {
      this.form.dispatchErrorOccurredEvent(this, "SetColorMode", 3103, new Object[] { "SetColorMode" });
      return;
    } 
  }
  
  @SimpleFunction(description = "Make the sensor read the light level with reflected light.")
  public void SetReflectedMode() {
    try {
      setMode("reflected");
      return;
    } catch (IllegalArgumentException illegalArgumentException) {
      this.form.dispatchErrorOccurredEvent(this, "SetReflectedMode", 3103, new Object[] { "SetReflectedMode" });
      return;
    } 
  }
  
  @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "The top of the range used for the BelowRange, WithinRange, and AboveRange events.")
  public int TopOfRange() {
    return this.topOfRange;
  }
  
  @DesignerProperty(defaultValue = "60", editorType = "non_negative_integer")
  @SimpleProperty
  public void TopOfRange(int paramInt) {
    this.topOfRange = paramInt;
  }
  
  @SimpleEvent(description = "Light level has gone within the range.")
  public void WithinRange() {
    EventDispatcher.dispatchEvent(this, "WithinRange", new Object[0]);
  }
  
  @DesignerProperty(defaultValue = "False", editorType = "boolean")
  @SimpleProperty
  public void WithinRangeEventEnabled(boolean paramBoolean) {
    this.withinRangeEventEnabled = paramBoolean;
  }
  
  @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "Whether the WithinRange event should fire when the light level goes between the BottomOfRange and the TopOfRange.")
  public boolean WithinRangeEventEnabled() {
    return this.withinRangeEventEnabled;
  }
  
  public void onDelete() {
    this.eventHandler.removeCallbacks(this.sensorValueChecker);
    super.onDelete();
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/com/google/appinventor/components/runtime/Ev3ColorSensor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */