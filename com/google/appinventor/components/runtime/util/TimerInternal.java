package com.google.appinventor.components.runtime.util;

import android.os.Handler;
import com.google.appinventor.components.runtime.AlarmHandler;

public final class TimerInternal implements Runnable {
  private AlarmHandler component;
  
  private boolean enabled;
  
  private Handler handler;
  
  private int interval;
  
  public TimerInternal(AlarmHandler paramAlarmHandler, boolean paramBoolean, int paramInt) {
    this(paramAlarmHandler, paramBoolean, paramInt, new Handler());
  }
  
  public TimerInternal(AlarmHandler paramAlarmHandler, boolean paramBoolean, int paramInt, Handler paramHandler) {
    this.handler = paramHandler;
    this.component = paramAlarmHandler;
    this.enabled = paramBoolean;
    this.interval = paramInt;
    if (paramBoolean)
      paramHandler.postDelayed(this, paramInt); 
  }
  
  public void Enabled(boolean paramBoolean) {
    if (this.enabled)
      this.handler.removeCallbacks(this); 
    this.enabled = paramBoolean;
    if (paramBoolean)
      this.handler.postDelayed(this, this.interval); 
  }
  
  public boolean Enabled() {
    return this.enabled;
  }
  
  public int Interval() {
    return this.interval;
  }
  
  public void Interval(int paramInt) {
    this.interval = paramInt;
    if (this.enabled) {
      this.handler.removeCallbacks(this);
      this.handler.postDelayed(this, paramInt);
    } 
  }
  
  public void run() {
    if (this.enabled) {
      this.component.alarm();
      if (this.enabled)
        this.handler.postDelayed(this, this.interval); 
    } 
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/com/google/appinventor/components/runtime/util/TimerInternal.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */