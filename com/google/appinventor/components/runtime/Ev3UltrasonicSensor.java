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

@DesignerComponent(category = ComponentCategory.LEGOMINDSTORMS, description = "A component that provides a high-level interface to an ultrasonic sensor on a LEGO MINDSTORMS EV3 robot.", iconName = "images/legoMindstormsEv3.png", nonVisible = true, version = 1)
@SimpleObject
public class Ev3UltrasonicSensor extends LegoMindstormsEv3Sensor implements Deleteable {
  private static final int DEFAULT_BOTTOM_OF_RANGE = 30;
  
  private static final String DEFAULT_SENSOR_MODE_STRING = "cm";
  
  private static final int DEFAULT_TOP_OF_RANGE = 90;
  
  private static final int DELAY_MILLISECONDS = 50;
  
  private static final int SENSOR_MODE_CM = 0;
  
  private static final String SENSOR_MODE_CM_STRING = "cm";
  
  private static final int SENSOR_MODE_INCH = 1;
  
  private static final String SENSOR_MODE_INCH_STRING = "inch";
  
  private static final int SENSOR_TYPE = 30;
  
  private boolean aboveRangeEventEnabled;
  
  private boolean belowRangeEventEnabled;
  
  private int bottomOfRange;
  
  private Handler eventHandler = new Handler();
  
  private int mode = 0;
  
  private String modeString = "cm";
  
  private double previousDistance = -1.0D;
  
  private final Runnable sensorValueChecker = new Runnable() {
      public void run() {
        if (Ev3UltrasonicSensor.this.bluetooth != null && Ev3UltrasonicSensor.this.bluetooth.IsConnected()) {
          double d = Ev3UltrasonicSensor.this.getDistance("");
          if (Ev3UltrasonicSensor.this.previousDistance < 0.0D) {
            Ev3UltrasonicSensor.access$102(Ev3UltrasonicSensor.this, d);
            Ev3UltrasonicSensor.this.eventHandler.postDelayed(this, 50L);
            return;
          } 
          if (d < Ev3UltrasonicSensor.this.bottomOfRange) {
            if (Ev3UltrasonicSensor.this.belowRangeEventEnabled && Ev3UltrasonicSensor.this.previousDistance >= Ev3UltrasonicSensor.this.bottomOfRange)
              Ev3UltrasonicSensor.this.BelowRange(); 
          } else if (d > Ev3UltrasonicSensor.this.topOfRange) {
            if (Ev3UltrasonicSensor.this.aboveRangeEventEnabled && Ev3UltrasonicSensor.this.previousDistance <= Ev3UltrasonicSensor.this.topOfRange)
              Ev3UltrasonicSensor.this.AboveRange(); 
          } else if (Ev3UltrasonicSensor.this.withinRangeEventEnabled && (Ev3UltrasonicSensor.this.previousDistance < Ev3UltrasonicSensor.this.bottomOfRange || Ev3UltrasonicSensor.this.previousDistance > Ev3UltrasonicSensor.this.topOfRange)) {
            Ev3UltrasonicSensor.this.WithinRange();
          } 
          Ev3UltrasonicSensor.access$102(Ev3UltrasonicSensor.this, d);
        } 
        Ev3UltrasonicSensor.this.eventHandler.postDelayed(this, 50L);
      }
    };
  
  private int topOfRange;
  
  private boolean withinRangeEventEnabled;
  
  public Ev3UltrasonicSensor(ComponentContainer paramComponentContainer) {
    super(paramComponentContainer, "Ev3UltrasonicSensor");
    this.eventHandler.post(this.sensorValueChecker);
    TopOfRange(90);
    BottomOfRange(30);
    BelowRangeEventEnabled(false);
    AboveRangeEventEnabled(false);
    WithinRangeEventEnabled(false);
    Unit("cm");
  }
  
  private double getDistance(String paramString) {
    double d2 = readInputSI(paramString, 0, this.sensorPortNumber, 30, this.mode);
    double d1 = d2;
    if (d2 == 255.0D)
      d1 = -1.0D; 
    return d1;
  }
  
  private void setMode(String paramString) {
    this.previousDistance = -1.0D;
    if ("cm".equals(paramString)) {
      this.mode = 0;
    } else if ("inch".equals(paramString)) {
      this.mode = 1;
    } else {
      throw new IllegalArgumentException();
    } 
    this.modeString = paramString;
  }
  
  @SimpleEvent(description = "Called when the detected distance has gone above the range.")
  public void AboveRange() {
    EventDispatcher.dispatchEvent(this, "AboveRange", new Object[0]);
  }
  
  @DesignerProperty(defaultValue = "False", editorType = "boolean")
  @SimpleProperty
  public void AboveRangeEventEnabled(boolean paramBoolean) {
    this.aboveRangeEventEnabled = paramBoolean;
  }
  
  @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "Whether the AboveRange event should fire when the distance goes above the TopOfRange.")
  public boolean AboveRangeEventEnabled() {
    return this.aboveRangeEventEnabled;
  }
  
  @SimpleEvent(description = "Called when the detected distance has gone below the range.")
  public void BelowRange() {
    EventDispatcher.dispatchEvent(this, "BelowRange", new Object[0]);
  }
  
  @DesignerProperty(defaultValue = "False", editorType = "boolean")
  @SimpleProperty
  public void BelowRangeEventEnabled(boolean paramBoolean) {
    this.belowRangeEventEnabled = paramBoolean;
  }
  
  @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "Whether the BelowRange event should fire when the distance goes below the BottomOfRange.")
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
  
  @SimpleFunction(description = "Returns the current distance in centimeters as a value between 0 and 254, or -1 if the distance can not be read.")
  public double GetDistance() {
    return getDistance("GetDistance");
  }
  
  @SimpleFunction(description = "Measure the distance in centimeters.")
  public void SetCmUnit() {
    try {
      setMode("cm");
      return;
    } catch (IllegalArgumentException illegalArgumentException) {
      this.form.dispatchErrorOccurredEvent(this, "SetCmUnit", 3103, new Object[] { "SetCmUnit" });
      return;
    } 
  }
  
  @SimpleFunction(description = "Measure the distance in inches.")
  public void SetInchUnit() {
    try {
      setMode("inch");
      return;
    } catch (IllegalArgumentException illegalArgumentException) {
      this.form.dispatchErrorOccurredEvent(this, "SetInchUnit", 3103, new Object[] { "SetInchUnit" });
      return;
    } 
  }
  
  @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "The top of the range used for the BelowRange, WithinRange, and AboveRange events.")
  public int TopOfRange() {
    return this.topOfRange;
  }
  
  @DesignerProperty(defaultValue = "90", editorType = "non_negative_integer")
  @SimpleProperty
  public void TopOfRange(int paramInt) {
    this.topOfRange = paramInt;
  }
  
  @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "The distance unit, which can be either \"cm\" or \"inch\".")
  public String Unit() {
    return this.modeString;
  }
  
  @DesignerProperty(defaultValue = "cm", editorType = "lego_ev3_ultrasonic_sensor_mode")
  @SimpleProperty
  public void Unit(String paramString) {
    try {
      setMode(paramString);
      return;
    } catch (IllegalArgumentException illegalArgumentException) {
      this.form.dispatchErrorOccurredEvent(this, "Unit", 3103, new Object[] { "Unit" });
      return;
    } 
  }
  
  @SimpleEvent(description = "Called when the detected distance has gone within the range.")
  public void WithinRange() {
    EventDispatcher.dispatchEvent(this, "WithinRange", new Object[0]);
  }
  
  @DesignerProperty(defaultValue = "False", editorType = "boolean")
  @SimpleProperty
  public void WithinRangeEventEnabled(boolean paramBoolean) {
    this.withinRangeEventEnabled = paramBoolean;
  }
  
  @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "Whether the WithinRange event should fire when the distance goes between the BottomOfRange and the TopOfRange.")
  public boolean WithinRangeEventEnabled() {
    return this.withinRangeEventEnabled;
  }
  
  public void onDelete() {
    this.eventHandler.removeCallbacks(this.sensorValueChecker);
    super.onDelete();
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/com/google/appinventor/components/runtime/Ev3UltrasonicSensor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */