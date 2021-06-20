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

@DesignerComponent(category = ComponentCategory.LEGOMINDSTORMS, description = "A component that provides a high-level interface to a sound sensor on a LEGO MINDSTORMS NXT robot.", iconName = "images/legoMindstormsNxt.png", nonVisible = true, version = 1)
@SimpleObject
public class NxtSoundSensor extends LegoMindstormsNxtSensor implements Deleteable {
  private static final int DEFAULT_BOTTOM_OF_RANGE = 256;
  
  private static final String DEFAULT_SENSOR_PORT = "2";
  
  private static final int DEFAULT_TOP_OF_RANGE = 767;
  
  private boolean aboveRangeEventEnabled;
  
  private boolean belowRangeEventEnabled;
  
  private int bottomOfRange;
  
  private Handler handler = new Handler();
  
  private State previousState = State.UNKNOWN;
  
  private final Runnable sensorReader = new Runnable() {
      public void run() {
        if (NxtSoundSensor.this.bluetooth != null && NxtSoundSensor.this.bluetooth.IsConnected()) {
          LegoMindstormsNxtSensor.SensorValue sensorValue = NxtSoundSensor.this.getSoundValue("");
          if (sensorValue.valid) {
            NxtSoundSensor.State state;
            if (((Integer)sensorValue.value).intValue() < NxtSoundSensor.this.bottomOfRange) {
              state = NxtSoundSensor.State.BELOW_RANGE;
            } else if (((Integer)((LegoMindstormsNxtSensor.SensorValue)state).value).intValue() > NxtSoundSensor.this.topOfRange) {
              state = NxtSoundSensor.State.ABOVE_RANGE;
            } else {
              state = NxtSoundSensor.State.WITHIN_RANGE;
            } 
            if (state != NxtSoundSensor.this.previousState) {
              if (state == NxtSoundSensor.State.BELOW_RANGE && NxtSoundSensor.this.belowRangeEventEnabled)
                NxtSoundSensor.this.BelowRange(); 
              if (state == NxtSoundSensor.State.WITHIN_RANGE && NxtSoundSensor.this.withinRangeEventEnabled)
                NxtSoundSensor.this.WithinRange(); 
              if (state == NxtSoundSensor.State.ABOVE_RANGE && NxtSoundSensor.this.aboveRangeEventEnabled)
                NxtSoundSensor.this.AboveRange(); 
            } 
            NxtSoundSensor.access$302(NxtSoundSensor.this, state);
          } 
        } 
        if (NxtSoundSensor.this.isHandlerNeeded())
          NxtSoundSensor.this.handler.post(NxtSoundSensor.this.sensorReader); 
      }
    };
  
  private int topOfRange;
  
  private boolean withinRangeEventEnabled;
  
  public NxtSoundSensor(ComponentContainer paramComponentContainer) {
    super(paramComponentContainer, "NxtSoundSensor");
    SensorPort("2");
    BottomOfRange(256);
    TopOfRange(767);
    BelowRangeEventEnabled(false);
    WithinRangeEventEnabled(false);
    AboveRangeEventEnabled(false);
  }
  
  private LegoMindstormsNxtSensor.SensorValue<Integer> getSoundValue(String paramString) {
    byte[] arrayOfByte = getInputValues(paramString, this.port);
    return (arrayOfByte != null && getBooleanValueFromBytes(arrayOfByte, 4)) ? new LegoMindstormsNxtSensor.SensorValue<Integer>(true, Integer.valueOf(getUWORDValueFromBytes(arrayOfByte, 10))) : new LegoMindstormsNxtSensor.SensorValue<Integer>(false, null);
  }
  
  private boolean isHandlerNeeded() {
    return (this.belowRangeEventEnabled || this.withinRangeEventEnabled || this.aboveRangeEventEnabled);
  }
  
  @SimpleEvent(description = "Sound level has gone above the range.")
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
  
  @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "Whether the AboveRange event should fire when the sound level goes above the TopOfRange.")
  public boolean AboveRangeEventEnabled() {
    return this.aboveRangeEventEnabled;
  }
  
  @SimpleEvent(description = "Sound level has gone below the range.")
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
  
  @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "Whether the BelowRange event should fire when the sound level goes below the BottomOfRange.")
  public boolean BelowRangeEventEnabled() {
    return this.belowRangeEventEnabled;
  }
  
  @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "The bottom of the range used for the BelowRange, WithinRange, and AboveRange events.")
  public int BottomOfRange() {
    return this.bottomOfRange;
  }
  
  @DesignerProperty(defaultValue = "256", editorType = "non_negative_integer")
  @SimpleProperty
  public void BottomOfRange(int paramInt) {
    this.bottomOfRange = paramInt;
    this.previousState = State.UNKNOWN;
  }
  
  @SimpleFunction(description = "Returns the current sound level as a value between 0 and 1023, or -1 if the sound level can not be read.")
  public int GetSoundLevel() {
    if (checkBluetooth("GetSoundLevel")) {
      LegoMindstormsNxtSensor.SensorValue<Integer> sensorValue = getSoundValue("GetSoundLevel");
      if (sensorValue.valid)
        return ((Integer)sensorValue.value).intValue(); 
    } 
    return -1;
  }
  
  @DesignerProperty(defaultValue = "2", editorType = "lego_nxt_sensor_port")
  @SimpleProperty(userVisible = false)
  public void SensorPort(String paramString) {
    setSensorPort(paramString);
  }
  
  @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "The top of the range used for the BelowRange, WithinRange, and AboveRange events.")
  public int TopOfRange() {
    return this.topOfRange;
  }
  
  @DesignerProperty(defaultValue = "767", editorType = "non_negative_integer")
  @SimpleProperty
  public void TopOfRange(int paramInt) {
    this.topOfRange = paramInt;
    this.previousState = State.UNKNOWN;
  }
  
  @SimpleEvent(description = "Sound level has gone within the range.")
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
  
  @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "Whether the WithinRange event should fire when the sound level goes between the BottomOfRange and the TopOfRange.")
  public boolean WithinRangeEventEnabled() {
    return this.withinRangeEventEnabled;
  }
  
  protected void initializeSensor(String paramString) {
    setInputMode(paramString, this.port, 7, 0);
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


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/com/google/appinventor/components/runtime/NxtSoundSensor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */