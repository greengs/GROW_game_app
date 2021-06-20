package com.google.appinventor.components.runtime;

import android.app.TimePickerDialog;
import android.content.Context;
import android.os.Handler;
import android.text.format.DateFormat;
import com.google.appinventor.components.annotations.DesignerComponent;
import com.google.appinventor.components.annotations.PropertyCategory;
import com.google.appinventor.components.annotations.SimpleEvent;
import com.google.appinventor.components.annotations.SimpleFunction;
import com.google.appinventor.components.annotations.SimpleObject;
import com.google.appinventor.components.annotations.SimpleProperty;
import com.google.appinventor.components.common.ComponentCategory;
import com.google.appinventor.components.runtime.util.Dates;
import java.util.Calendar;

@DesignerComponent(category = ComponentCategory.USERINTERFACE, description = "<p>A button that, when clicked on, launches  a popup dialog to allow the user to select a time.</p>", version = 3)
@SimpleObject
public class TimePicker extends ButtonBase {
  private Handler androidUIHandler;
  
  private boolean customTime = false;
  
  private Form form;
  
  private int hour = 0;
  
  private Calendar instant;
  
  private int minute = 0;
  
  private TimePickerDialog time;
  
  private TimePickerDialog.OnTimeSetListener timePickerListener = new TimePickerDialog.OnTimeSetListener() {
      public void onTimeSet(android.widget.TimePicker param1TimePicker, int param1Int1, int param1Int2) {
        if (param1TimePicker.isShown()) {
          TimePicker.access$002(TimePicker.this, param1Int1);
          TimePicker.access$102(TimePicker.this, param1Int2);
          TimePicker.access$202(TimePicker.this, Dates.TimeInstant(TimePicker.this.hour, TimePicker.this.minute));
          TimePicker.this.androidUIHandler.post(new Runnable() {
                public void run() {
                  TimePicker.this.AfterTimeSet();
                }
              });
        } 
      }
    };
  
  public TimePicker(ComponentContainer paramComponentContainer) {
    super(paramComponentContainer);
    this.form = paramComponentContainer.$form();
    Calendar calendar = Calendar.getInstance();
    this.hour = calendar.get(11);
    this.minute = calendar.get(12);
    this.time = new TimePickerDialog((Context)this.container.$context(), this.timePickerListener, this.hour, this.minute, DateFormat.is24HourFormat((Context)this.container.$context()));
    this.instant = Dates.TimeInstant(this.hour, this.minute);
    this.androidUIHandler = new Handler();
  }
  
  @SimpleEvent(description = "This event is run when a user has set the time in the popup dialog.")
  public void AfterTimeSet() {
    EventDispatcher.dispatchEvent(this, "AfterTimeSet", new Object[0]);
  }
  
  @SimpleProperty(category = PropertyCategory.APPEARANCE, description = "The hour of the last time set using the time picker. The hour is in a 24 hour format. If the last time set was 11:53 pm, this property will return 23.")
  public int Hour() {
    return this.hour;
  }
  
  @SimpleProperty(category = PropertyCategory.APPEARANCE, description = "The instant of the last time set using the time picker")
  public Calendar Instant() {
    return this.instant;
  }
  
  @SimpleFunction(description = "Launches the TimePicker dialog.")
  public void LaunchPicker() {
    click();
  }
  
  @SimpleProperty(category = PropertyCategory.APPEARANCE, description = "The minute of the last time set using the time picker")
  public int Minute() {
    return this.minute;
  }
  
  @SimpleFunction(description = "Set the time to be shown in the Time Picker popup. Current time is shown by default.")
  public void SetTimeToDisplay(int paramInt1, int paramInt2) {
    if (paramInt1 < 0 || paramInt1 > 23) {
      this.form.dispatchErrorOccurredEvent(this, "SetTimeToDisplay", 2301, new Object[0]);
      return;
    } 
    if (paramInt2 < 0 || paramInt2 > 59) {
      this.form.dispatchErrorOccurredEvent(this, "SetTimeToDisplay", 2302, new Object[0]);
      return;
    } 
    this.time.updateTime(paramInt1, paramInt2);
    this.instant = Dates.TimeInstant(paramInt1, paramInt2);
    this.customTime = true;
  }
  
  @SimpleFunction(description = "Set the time from the instant to be shown in the Time Picker dialog. Current time is shown by default.")
  public void SetTimeToDisplayFromInstant(Calendar paramCalendar) {
    int i = Dates.Hour(paramCalendar);
    int j = Dates.Minute(paramCalendar);
    this.time.updateTime(i, j);
    Dates.TimeInstant(i, j);
    this.customTime = true;
  }
  
  public void click() {
    if (!this.customTime) {
      Calendar calendar = Calendar.getInstance();
      int i = calendar.get(11);
      int j = calendar.get(12);
      this.time.updateTime(i, j);
      this.instant = Dates.TimeInstant(this.hour, this.minute);
    } else {
      this.customTime = false;
    } 
    this.time.show();
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/com/google/appinventor/components/runtime/TimePicker.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */