package com.google.appinventor.components.runtime;

import android.hardware.SensorEvent;
import com.google.appinventor.components.annotations.SimpleObject;

@SimpleObject
public abstract class BufferedSingleValueSensor extends SingleValueSensor {
  private AveragingBuffer buffer;
  
  public BufferedSingleValueSensor(ComponentContainer paramComponentContainer, int paramInt1, int paramInt2) {
    super(paramComponentContainer.$form(), paramInt1);
    this.buffer = new AveragingBuffer(paramInt2);
  }
  
  protected float getAverageValue() {
    return this.buffer.getAverage();
  }
  
  public void onSensorChanged(SensorEvent paramSensorEvent) {
    if (this.enabled && paramSensorEvent.sensor.getType() == this.sensorType) {
      float[] arrayOfFloat = paramSensorEvent.values;
      this.buffer.insert(Float.valueOf(arrayOfFloat[0]));
      super.onSensorChanged(paramSensorEvent);
    } 
  }
  
  private class AveragingBuffer {
    private Float[] data;
    
    private int next;
    
    private AveragingBuffer(int param1Int) {
      this.data = new Float[param1Int];
      this.next = 0;
    }
    
    private float getAverage() {
      double d = 0.0D;
      int j = 0;
      int i = 0;
      while (i < this.data.length) {
        int k = j;
        double d1 = d;
        if (this.data[i] != null) {
          d1 = d + this.data[i].floatValue();
          k = j + 1;
        } 
        i++;
        j = k;
        d = d1;
      } 
      if (j != 0)
        d /= j; 
      return (float)d;
    }
    
    private void insert(Float param1Float) {
      Float[] arrayOfFloat = this.data;
      int i = this.next;
      this.next = i + 1;
      arrayOfFloat[i] = param1Float;
      if (this.next == this.data.length)
        this.next = 0; 
    }
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/com/google/appinventor/components/runtime/BufferedSingleValueSensor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */