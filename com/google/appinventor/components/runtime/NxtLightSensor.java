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

@DesignerComponent(category = ComponentCategory.LEGOMINDSTORMS, description = "A component that provides a high-level interface to a light sensor on a LEGO MINDSTORMS NXT robot.", iconName = "images/legoMindstormsNxt.png", nonVisible = true, version = 1)
@SimpleObject
public class NxtLightSensor extends LegoMindstormsNxtSensor implements Deleteable {
  private static final int DEFAULT_BOTTOM_OF_RANGE = 256;
  
  private static final String DEFAULT_SENSOR_PORT = "3";
  
  private static final int DEFAULT_TOP_OF_RANGE = 767;
  
  private boolean aboveRangeEventEnabled;
  
  private boolean belowRangeEventEnabled;
  
  private int bottomOfRange;
  
  private boolean generateLight;
  
  private Handler handler = new Handler();
  
  private State previousState = State.UNKNOWN;
  
  private final Runnable sensorReader = new Runnable() {
      public void run() {
        if (NxtLightSensor.this.bluetooth != null && NxtLightSensor.this.bluetooth.IsConnected()) {
          LegoMindstormsNxtSensor.SensorValue sensorValue = NxtLightSensor.this.getLightValue("");
          if (sensorValue.valid) {
            NxtLightSensor.State state;
            if (((Integer)sensorValue.value).intValue() < NxtLightSensor.this.bottomOfRange) {
              state = NxtLightSensor.State.BELOW_RANGE;
            } else if (((Integer)((LegoMindstormsNxtSensor.SensorValue)state).value).intValue() > NxtLightSensor.this.topOfRange) {
              state = NxtLightSensor.State.ABOVE_RANGE;
            } else {
              state = NxtLightSensor.State.WITHIN_RANGE;
            } 
            if (state != NxtLightSensor.this.previousState) {
              if (state == NxtLightSensor.State.BELOW_RANGE && NxtLightSensor.this.belowRangeEventEnabled)
                NxtLightSensor.this.BelowRange(); 
              if (state == NxtLightSensor.State.WITHIN_RANGE && NxtLightSensor.this.withinRangeEventEnabled)
                NxtLightSensor.this.WithinRange(); 
              if (state == NxtLightSensor.State.ABOVE_RANGE && NxtLightSensor.this.aboveRangeEventEnabled)
                NxtLightSensor.this.AboveRange(); 
            } 
            NxtLightSensor.access$302(NxtLightSensor.this, state);
          } 
        } 
        if (NxtLightSensor.this.isHandlerNeeded())
          NxtLightSensor.this.handler.post(NxtLightSensor.this.sensorReader); 
      }
    };
  
  private int topOfRange;
  
  private boolean withinRangeEventEnabled;
  
  public NxtLightSensor(ComponentContainer paramComponentContainer) {
    super(paramComponentContainer, "NxtLightSensor");
    SensorPort("3");
    BottomOfRange(256);
    TopOfRange(767);
    BelowRangeEventEnabled(false);
    WithinRangeEventEnabled(false);
    AboveRangeEventEnabled(false);
    GenerateLight(false);
  }
  
  private LegoMindstormsNxtSensor.SensorValue<Integer> getLightValue(String paramString) {
    byte[] arrayOfByte = getInputValues(paramString, this.port);
    return (arrayOfByte != null && getBooleanValueFromBytes(arrayOfByte, 4)) ? new LegoMindstormsNxtSensor.SensorValue<Integer>(true, Integer.valueOf(getUWORDValueFromBytes(arrayOfByte, 10))) : new LegoMindstormsNxtSensor.SensorValue<Integer>(false, null);
  }
  
  private boolean isHandlerNeeded() {
    return (this.belowRangeEventEnabled || this.withinRangeEventEnabled || this.aboveRangeEventEnabled);
  }
  
  @SimpleEvent(description = "Light level has gone above the range.")
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
  
  @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "Whether the BelowRange event should fire when the light level goes below the BottomOfRange.")
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
  
  @DesignerProperty(defaultValue = "False", editorType = "boolean")
  @SimpleProperty
  public void GenerateLight(boolean paramBoolean) {
    this.generateLight = paramBoolean;
    if (this.bluetooth != null && this.bluetooth.IsConnected())
      initializeSensor("GenerateLight"); 
  }
  
  @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "Whether the light sensor should generate light.")
  public boolean GenerateLight() {
    return this.generateLight;
  }
  
  @SimpleFunction(description = "Returns the current light level as a value between 0 and 1023, or -1 if the light level can not be read.")
  public int GetLightLevel() {
    if (checkBluetooth("GetLightLevel")) {
      LegoMindstormsNxtSensor.SensorValue<Integer> sensorValue = getLightValue("GetLightLevel");
      if (sensorValue.valid)
        return ((Integer)sensorValue.value).intValue(); 
    } 
    return -1;
  }
  
  @DesignerProperty(defaultValue = "3", editorType = "lego_nxt_sensor_port")
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
  
  @SimpleEvent(description = "Light level has gone within the range.")
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
  
  @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "Whether the WithinRange event should fire when the light level goes between the BottomOfRange and the TopOfRange.")
  public boolean WithinRangeEventEnabled() {
    return this.withinRangeEventEnabled;
  }
  
  protected void initializeSensor(String paramString) {
    byte b;
    int i = this.port;
    if (this.generateLight) {
      b = 5;
    } else {
      b = 6;
    } 
    setInputMode(paramString, i, b, 128);
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


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/com/google/appinventor/components/runtime/NxtLightSensor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */