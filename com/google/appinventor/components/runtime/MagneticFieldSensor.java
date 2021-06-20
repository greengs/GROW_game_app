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

@DesignerComponent(category = ComponentCategory.SENSORS, description = "<p>Non-visible component that measures the ambient geomagnetic field for all three physical axes (x, y, z) in Tesla https://en.wikipedia.org/wiki/Tesla_(unit).</p>", iconName = "images/magneticSensor.png", nonVisible = true, version = 1)
@SimpleObject
public class MagneticFieldSensor extends AndroidNonvisibleComponent implements SensorEventListener, Deleteable, OnPauseListener, OnResumeListener, OnStopListener, SensorComponent {
  private double absoluteStrength;
  
  private boolean enabled = true;
  
  private boolean listening;
  
  private Sensor magneticSensor;
  
  private final SensorManager sensorManager;
  
  private float xStrength;
  
  private float yStrength;
  
  private float zStrength;
  
  public MagneticFieldSensor(ComponentContainer paramComponentContainer) {
    super(paramComponentContainer.$form());
    this.form.registerForOnResume(this);
    this.form.registerForOnStop(this);
    this.form.registerForOnPause(this);
    this.sensorManager = (SensorManager)paramComponentContainer.$context().getSystemService("sensor");
    this.magneticSensor = this.sensorManager.getDefaultSensor(2);
    startListening();
  }
  
  private Sensor getMagneticSensor() {
    Sensor sensor = this.sensorManager.getDefaultSensor(2);
    return (sensor != null) ? sensor : this.sensorManager.getDefaultSensor(2);
  }
  
  private void startListening() {
    if (!this.listening && this.sensorManager != null && this.magneticSensor != null) {
      this.sensorManager.registerListener(this, this.magneticSensor, 3);
      this.listening = true;
    } 
  }
  
  private void stopListening() {
    if (this.listening && this.sensorManager != null) {
      this.sensorManager.unregisterListener(this);
      this.listening = false;
      this.xStrength = 0.0F;
      this.yStrength = 0.0F;
      this.zStrength = 0.0F;
    } 
  }
  
  @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "Indicates the absolute strength of the field.")
  public double AbsoluteStrength() {
    return this.absoluteStrength;
  }
  
  @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "Indicates that there is a magnetic field sensor in the device and it is available.")
  public boolean Available() {
    return (this.sensorManager.getSensorList(2).size() > 0);
  }
  
  @DesignerProperty(defaultValue = "True", editorType = "boolean")
  @SimpleProperty
  public void Enabled(boolean paramBoolean) {
    if (this.enabled != paramBoolean)
      this.enabled = paramBoolean; 
    if (this.enabled) {
      startListening();
      return;
    } 
    stopListening();
  }
  
  @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "Indicates whether or not the magnetic field sensor is enabled and working.")
  public boolean Enabled() {
    return this.enabled;
  }
  
  @SimpleEvent(description = "Triggers when magnetic field has changed, setting the new values in parameters.")
  public void MagneticChanged(float paramFloat1, float paramFloat2, float paramFloat3, double paramDouble) {
    EventDispatcher.dispatchEvent(this, "MagneticChanged", new Object[] { Float.valueOf(paramFloat1), Float.valueOf(paramFloat2), Float.valueOf(paramFloat3), Double.valueOf(paramDouble) });
  }
  
  @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "Indicates the maximum range the magnetic sensor can reach.")
  public float MaximumRange() {
    return this.magneticSensor.getMaximumRange();
  }
  
  @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "Indicates the field's strength in the X-axis.")
  public float XStrength() {
    return this.xStrength;
  }
  
  @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "Indicates the field's strength in the Y-axis.")
  public float YStrength() {
    return this.yStrength;
  }
  
  @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "Indicates the field's strength in the Z-axis.")
  public float ZStrength() {
    return this.zStrength;
  }
  
  public void onAccuracyChanged(Sensor paramSensor, int paramInt) {}
  
  public void onDelete() {
    if (this.enabled)
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
    if (this.enabled && paramSensorEvent.sensor.getType() == 2) {
      float[] arrayOfFloat = (float[])paramSensorEvent.values.clone();
      this.xStrength = paramSensorEvent.values[0];
      this.yStrength = paramSensorEvent.values[1];
      this.zStrength = paramSensorEvent.values[2];
      this.absoluteStrength = Math.sqrt((this.xStrength * this.xStrength + this.yStrength * this.yStrength + this.zStrength * this.zStrength));
      MagneticChanged(this.xStrength, this.yStrength, this.zStrength, this.absoluteStrength);
    } 
  }
  
  public void onStop() {
    if (this.enabled)
      stopListening(); 
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/com/google/appinventor/components/runtime/MagneticFieldSensor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */