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

@DesignerComponent(category = ComponentCategory.LEGOMINDSTORMS, description = "A component that provides a high-level interface to an ultrasonic sensor on a LEGO MINDSTORMS NXT robot.", iconName = "images/legoMindstormsNxt.png", nonVisible = true, version = 1)
@SimpleObject
public class NxtUltrasonicSensor extends LegoMindstormsNxtSensor implements Deleteable {
  private static final int DEFAULT_BOTTOM_OF_RANGE = 30;
  
  private static final String DEFAULT_SENSOR_PORT = "4";
  
  private static final int DEFAULT_TOP_OF_RANGE = 90;
  
  private boolean aboveRangeEventEnabled;
  
  private boolean belowRangeEventEnabled;
  
  private int bottomOfRange;
  
  private Handler handler = new Handler();
  
  private State previousState = State.UNKNOWN;
  
  private final Runnable sensorReader = new Runnable() {
      public void run() {
        if (NxtUltrasonicSensor.this.bluetooth != null && NxtUltrasonicSensor.this.bluetooth.IsConnected()) {
          LegoMindstormsNxtSensor.SensorValue sensorValue = NxtUltrasonicSensor.this.getDistanceValue("");
          if (sensorValue.valid) {
            NxtUltrasonicSensor.State state;
            if (((Integer)sensorValue.value).intValue() < NxtUltrasonicSensor.this.bottomOfRange) {
              state = NxtUltrasonicSensor.State.BELOW_RANGE;
            } else if (((Integer)((LegoMindstormsNxtSensor.SensorValue)state).value).intValue() > NxtUltrasonicSensor.this.topOfRange) {
              state = NxtUltrasonicSensor.State.ABOVE_RANGE;
            } else {
              state = NxtUltrasonicSensor.State.WITHIN_RANGE;
            } 
            if (state != NxtUltrasonicSensor.this.previousState) {
              if (state == NxtUltrasonicSensor.State.BELOW_RANGE && NxtUltrasonicSensor.this.belowRangeEventEnabled)
                NxtUltrasonicSensor.this.BelowRange(); 
              if (state == NxtUltrasonicSensor.State.WITHIN_RANGE && NxtUltrasonicSensor.this.withinRangeEventEnabled)
                NxtUltrasonicSensor.this.WithinRange(); 
              if (state == NxtUltrasonicSensor.State.ABOVE_RANGE && NxtUltrasonicSensor.this.aboveRangeEventEnabled)
                NxtUltrasonicSensor.this.AboveRange(); 
            } 
            NxtUltrasonicSensor.access$302(NxtUltrasonicSensor.this, state);
          } 
        } 
        if (NxtUltrasonicSensor.this.isHandlerNeeded())
          NxtUltrasonicSensor.this.handler.post(NxtUltrasonicSensor.this.sensorReader); 
      }
    };
  
  private int topOfRange;
  
  private boolean withinRangeEventEnabled;
  
  public NxtUltrasonicSensor(ComponentContainer paramComponentContainer) {
    super(paramComponentContainer, "NxtUltrasonicSensor");
    SensorPort("4");
    BottomOfRange(30);
    TopOfRange(90);
    BelowRangeEventEnabled(false);
    WithinRangeEventEnabled(false);
    AboveRangeEventEnabled(false);
  }
  
  private void configureUltrasonicSensor(String paramString) {
    lsWrite(paramString, this.port, new byte[] { 2, 65, 2 }, 0);
  }
  
  private LegoMindstormsNxtSensor.SensorValue<Integer> getDistanceValue(String paramString) {
    lsWrite(paramString, this.port, new byte[] { 2, 66 }, 1);
    for (int i = 0; i < 3; i++) {
      if (lsGetStatus(paramString, this.port) > 0) {
        byte[] arrayOfByte = lsRead(paramString, this.port);
        if (arrayOfByte != null) {
          i = getUBYTEValueFromBytes(arrayOfByte, 4);
          if (i >= 0 && i <= 254)
            return new LegoMindstormsNxtSensor.SensorValue<Integer>(true, Integer.valueOf(i)); 
        } 
        break;
      } 
    } 
    return new LegoMindstormsNxtSensor.SensorValue<Integer>(false, null);
  }
  
  private boolean isHandlerNeeded() {
    return (this.belowRangeEventEnabled || this.withinRangeEventEnabled || this.aboveRangeEventEnabled);
  }
  
  @SimpleEvent(description = "Distance has gone above the range.")
  public void AboveRange() {
    EventDispatcher.dispatchEvent(this, "AboveRange", new Object[0]);
  }
  
  @DesignerProperty(defaultValue = "False", editorType = "boolean")
  @SimpleProperty
  public void AboveRangeEventEnabled(boolean paramBoolean) {
    boolean bool = isHandlerNeeded();
    this.aboveRangeEventEnabled = paramBoolean;
    paramBoolean = isHandlerNeeded();
    if (bool && !paramBoolean)
      this.handler.removeCallbacks(this.sensorReader); 
    if (!bool && paramBoolean) {
      this.previousState = State.UNKNOWN;
      this.handler.post(this.sensorReader);
    } 
  }
  
  @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "Whether the AboveRange event should fire when the distance goes above the TopOfRange.")
  public boolean AboveRangeEventEnabled() {
    return this.aboveRangeEventEnabled;
  }
  
  @SimpleEvent(description = "Distance has gone below the range.")
  public void BelowRange() {
    EventDispatcher.dispatchEvent(this, "BelowRange", new Object[0]);
  }
  
  @DesignerProperty(defaultValue = "False", editorType = "boolean")
  @SimpleProperty
  public void BelowRangeEventEnabled(boolean paramBoolean) {
    boolean bool = isHandlerNeeded();
    this.belowRangeEventEnabled = paramBoolean;
    paramBoolean = isHandlerNeeded();
    if (bool && !paramBoolean)
      this.handler.removeCallbacks(this.sensorReader); 
    if (!bool && paramBoolean) {
      this.previousState = State.UNKNOWN;
      this.handler.post(this.sensorReader);
    } 
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
    this.previousState = State.UNKNOWN;
  }
  
  @SimpleFunction(description = "Returns the current distance in centimeters as a value between 0 and 254, or -1 if the distance can not be read.")
  public int GetDistance() {
    if (checkBluetooth("GetDistance")) {
      LegoMindstormsNxtSensor.SensorValue<Integer> sensorValue = getDistanceValue("GetDistance");
      if (sensorValue.valid)
        return ((Integer)sensorValue.value).intValue(); 
    } 
    return -1;
  }
  
  @DesignerProperty(defaultValue = "4", editorType = "lego_nxt_sensor_port")
  @SimpleProperty(userVisible = false)
  public void SensorPort(String paramString) {
    setSensorPort(paramString);
  }
  
  @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "The top of the range used for the BelowRange, WithinRange, and AboveRange events.")
  public int TopOfRange() {
    return this.topOfRange;
  }
  
  @DesignerProperty(defaultValue = "90", editorType = "non_negative_integer")
  @SimpleProperty
  public void TopOfRange(int paramInt) {
    this.topOfRange = paramInt;
    this.previousState = State.UNKNOWN;
  }
  
  @SimpleEvent(description = "Distance has gone within the range.")
  public void WithinRange() {
    EventDispatcher.dispatchEvent(this, "WithinRange", new Object[0]);
  }
  
  @DesignerProperty(defaultValue = "False", editorType = "boolean")
  @SimpleProperty
  public void WithinRangeEventEnabled(boolean paramBoolean) {
    boolean bool = isHandlerNeeded();
    this.withinRangeEventEnabled = paramBoolean;
    paramBoolean = isHandlerNeeded();
    if (bool && !paramBoolean)
      this.handler.removeCallbacks(this.sensorReader); 
    if (!bool && paramBoolean) {
      this.previousState = State.UNKNOWN;
      this.handler.post(this.sensorReader);
    } 
  }
  
  @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "Whether the WithinRange event should fire when the distance goes between the BottomOfRange and the TopOfRange.")
  public boolean WithinRangeEventEnabled() {
    return this.withinRangeEventEnabled;
  }
  
  protected void initializeSensor(String paramString) {
    setInputMode(paramString, this.port, 11, 0);
    configureUltrasonicSensor(paramString);
  }
  
  public void onDelete() {
    this.handler.removeCallbacks(this.sensorReader);
    super.onDelete();
  }
  
  private enum State {
    ABOVE_RANGE, BELOW_RANGE, UNKNOWN, WITHIN_RANGE;
    
    static {
      ABOVE_RANGE = new State("ABOVE_RANGE", 3);
      $VALUES = new State[] { UNKNOWN, BELOW_RANGE, WITHIN_RANGE, ABOVE_RANGE };
    }
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/com/google/appinventor/components/runtime/NxtUltrasonicSensor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */