package com.google.appinventor.components.runtime;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import com.google.appinventor.components.annotations.DesignerComponent;
import com.google.appinventor.components.annotations.DesignerProperty;
import com.google.appinventor.components.annotations.PropertyCategory;
import com.google.appinventor.components.annotations.SimpleEvent;
import com.google.appinventor.components.annotations.SimpleObject;
import com.google.appinventor.components.annotations.SimpleProperty;
import com.google.appinventor.components.common.ComponentCategory;

@DesignerComponent(category = ComponentCategory.SENSORS, description = "<p>Non-visible component that can measure angular velocity in three dimensions in units of degrees per second.</p><p>In order to function, the component must have its <code>Enabled</code> property set to True, and the device must have a gyroscope sensor.</p>", iconName = "images/gyroscopesensor.png", nonVisible = true, version = 1)
@SimpleObject
public class GyroscopeSensor extends AndroidNonvisibleComponent implements SensorEventListener, Deleteable, OnPauseListener, OnResumeListener {
  private boolean enabled;
  
  private final Sensor gyroSensor = this.sensorManager.getDefaultSensor(4);
  
  private boolean listening;
  
  private final SensorManager sensorManager = (SensorManager)this.form.getSystemService("sensor");
  
  private float xAngularVelocity;
  
  private float yAngularVelocity;
  
  private float zAngularVelocity;
  
  public GyroscopeSensor(ComponentContainer paramComponentContainer) {
    super(paramComponentContainer.$form());
    this.form.registerForOnResume(this);
    this.form.registerForOnPause(this);
    Enabled(true);
  }
  
  private void startListening() {
    if (!this.listening) {
      this.sensorManager.registerListener(this, this.gyroSensor, 0);
      this.listening = true;
    } 
  }
  
  private void stopListening() {
    if (this.listening) {
      this.sensorManager.unregisterListener(this);
      this.listening = false;
      this.xAngularVelocity = 0.0F;
      this.yAngularVelocity = 0.0F;
      this.zAngularVelocity = 0.0F;
    } 
  }
  
  @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "Indicates whether a gyroscope sensor is available.")
  public boolean Available() {
    return (this.sensorManager.getSensorList(4).size() > 0);
  }
  
  @DesignerProperty(defaultValue = "True", editorType = "boolean")
  @SimpleProperty(description = "If enabled, then sensor events will be generated and XAngularVelocity, YAngularVelocity, and ZAngularVelocity properties will have meaningful values.")
  public void Enabled(boolean paramBoolean) {
    if (this.enabled != paramBoolean) {
      this.enabled = paramBoolean;
      if (paramBoolean) {
        startListening();
        return;
      } 
    } else {
      return;
    } 
    stopListening();
  }
  
  @SimpleProperty(category = PropertyCategory.BEHAVIOR)
  public boolean Enabled() {
    return this.enabled;
  }
  
  @SimpleEvent(description = "Indicates that the gyroscope sensor data has changed. The timestamp parameter is the time in nanoseconds at which the event occurred.")
  public void GyroscopeChanged(float paramFloat1, float paramFloat2, float paramFloat3, long paramLong) {
    EventDispatcher.dispatchEvent(this, "GyroscopeChanged", new Object[] { Float.valueOf(paramFloat1), Float.valueOf(paramFloat2), Float.valueOf(paramFloat3), Long.valueOf(paramLong) });
  }
  
  @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "The angular velocity around the X axis, in degrees per second.")
  public float XAngularVelocity() {
    return this.xAngularVelocity;
  }
  
  @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "The angular velocity around the Y axis, in degrees per second.")
  public float YAngularVelocity() {
    return this.yAngularVelocity;
  }
  
  @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "The angular velocity around the Z axis, in degrees per second.")
  public float ZAngularVelocity() {
    return this.zAngularVelocity;
  }
  
  public void onAccuracyChanged(Sensor paramSensor, int paramInt) {}
  
  public void onDelete() {
    stopListening();
  }
  
  public void onPause() {
    stopListening();
  }
  
  public void onResume() {
    if (this.enabled)
      startListening(); 
  }
  
  public void onSensorChanged(SensorEvent paramSensorEvent) {
    if (this.enabled) {
      this.xAngularVelocity = (float)Math.toDegrees(paramSensorEvent.values[0]);
      this.yAngularVelocity = (float)Math.toDegrees(paramSensorEvent.values[1]);
      this.zAngularVelocity = (float)Math.toDegrees(paramSensorEvent.values[2]);
      GyroscopeChanged(this.xAngularVelocity, this.yAngularVelocity, this.zAngularVelocity, paramSensorEvent.timestamp);
    } 
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/com/google/appinventor/components/runtime/GyroscopeSensor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */