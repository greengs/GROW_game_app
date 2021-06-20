package com.google.appinventor.components.runtime;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Build;
import com.google.appinventor.components.annotations.DesignerProperty;
import com.google.appinventor.components.annotations.SimpleObject;
import com.google.appinventor.components.annotations.SimpleProperty;

@SimpleObject
public abstract class SingleValueSensor extends AndroidNonvisibleComponent implements OnPauseListener, OnResumeListener, SensorComponent, SensorEventListener, Deleteable {
  private static final int DEFAULT_REFRESH_TIME = 1000;
  
  protected boolean enabled;
  
  protected int refreshTime;
  
  private Sensor sensor;
  
  protected final SensorManager sensorManager;
  
  protected int sensorType;
  
  protected float value;
  
  public SingleValueSensor(ComponentContainer paramComponentContainer, int paramInt) {
    super(paramComponentContainer.$form());
    this.sensorType = paramInt;
    this.form.registerForOnResume(this);
    this.form.registerForOnPause(this);
    this.refreshTime = 1000;
    this.enabled = true;
    this.sensorManager = (SensorManager)paramComponentContainer.$context().getSystemService("sensor");
    this.sensor = this.sensorManager.getDefaultSensor(paramInt);
    startListening();
  }
  
  @SimpleProperty(description = "Specifies whether or not the device has the hardware to support the %type% component.")
  public boolean Available() {
    return isAvailable();
  }
  
  @DesignerProperty(defaultValue = "True", editorType = "boolean")
  @SimpleProperty
  public void Enabled(boolean paramBoolean) {
    setEnabled(paramBoolean);
  }
  
  @SimpleProperty(description = "If enabled, then device will listen for changes.")
  public boolean Enabled() {
    return this.enabled;
  }
  
  @SimpleProperty(description = "The requested minimum time in milliseconds between changes in readings being reported. Android is not guaranteed to honor the request. Setting this property has no effect on pre-Gingerbread devices.")
  public int RefreshTime() {
    return this.refreshTime;
  }
  
  @DesignerProperty(defaultValue = "1000", editorType = "non_negative_integer")
  @SimpleProperty
  public void RefreshTime(int paramInt) {
    this.refreshTime = paramInt;
    if (this.enabled) {
      stopListening();
      startListening();
    } 
  }
  
  protected float getValue() {
    return this.value;
  }
  
  protected boolean isAvailable() {
    return (this.sensorManager.getSensorList(this.sensorType).size() > 0);
  }
  
  public void onAccuracyChanged(Sensor paramSensor, int paramInt) {}
  
  public void onDelete() {
    if (this.enabled)
      stopListening(); 
  }
  
  public void onPause() {
    if (this.enabled)
      stopListening(); 
  }
  
  public void onResume() {
    if (this.enabled)
      startListening(); 
  }
  
  public void onSensorChanged(SensorEvent paramSensorEvent) {
    if (this.enabled && paramSensorEvent.sensor.getType() == this.sensorType) {
      this.value = paramSensorEvent.values[0];
      onValueChanged(this.value);
    } 
  }
  
  protected abstract void onValueChanged(float paramFloat);
  
  protected void setEnabled(boolean paramBoolean) {
    if (this.enabled == paramBoolean)
      return; 
    this.enabled = paramBoolean;
    if (paramBoolean) {
      startListening();
      return;
    } 
    stopListening();
  }
  
  protected void startListening() {
    if (Build.VERSION.SDK_INT >= 9) {
      int i = this.refreshTime;
      this.sensorManager.registerListener(this, this.sensor, i * 1000);
      return;
    } 
    this.sensorManager.registerListener(this, this.sensor, 2);
  }
  
  protected void stopListening() {
    this.sensorManager.unregisterListener(this);
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/com/google/appinventor/components/runtime/SingleValueSensor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */