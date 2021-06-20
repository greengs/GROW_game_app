package com.google.appinventor.components.runtime;

import android.app.DatePickerDialog;
import android.content.Context;
import android.os.Handler;
import com.google.appinventor.components.annotations.DesignerComponent;
import com.google.appinventor.components.annotations.PropertyCategory;
import com.google.appinventor.components.annotations.SimpleEvent;
import com.google.appinventor.components.annotations.SimpleFunction;
import com.google.appinventor.components.annotations.SimpleObject;
import com.google.appinventor.components.annotations.SimpleProperty;
import com.google.appinventor.components.common.ComponentCategory;
import com.google.appinventor.components.runtime.util.Dates;
import java.text.DateFormatSymbols;
import java.util.Calendar;
import java.util.GregorianCalendar;

@DesignerComponent(category = ComponentCategory.USERINTERFACE, description = "<p>A button that, when clicked on, launches a popup dialog to allow the user to select a date.</p>", version = 3)
@SimpleObject
public class DatePicker extends ButtonBase {
  private Handler androidUIHandler;
  
  private boolean customDate = false;
  
  private DatePickerDialog date;
  
  private DatePickerDialog.OnDateSetListener datePickerListener = new DatePickerDialog.OnDateSetListener() {
      public void onDateSet(android.widget.DatePicker param1DatePicker, int param1Int1, int param1Int2, int param1Int3) {
        if (param1DatePicker.isShown()) {
          DatePicker.access$002(DatePicker.this, param1Int1);
          DatePicker.access$102(DatePicker.this, param1Int2);
          DatePicker.access$202(DatePicker.this, DatePicker.this.javaMonth + 1);
          DatePicker.access$302(DatePicker.this, param1Int3);
          DatePicker.this.date.updateDate(DatePicker.this.year, DatePicker.this.javaMonth, DatePicker.this.day);
          DatePicker.access$502(DatePicker.this, Dates.DateInstant(DatePicker.this.year, DatePicker.this.month, DatePicker.this.day));
          DatePicker.this.androidUIHandler.post(new Runnable() {
                public void run() {
                  DatePicker.this.AfterDateSet();
                }
              });
        } 
      }
    };
  
  private int day;
  
  private Form form;
  
  private Calendar instant;
  
  private int javaMonth;
  
  private String[] localizedMonths = (new DateFormatSymbols()).getMonths();
  
  private int month;
  
  private int year;
  
  public DatePicker(ComponentContainer paramComponentContainer) {
    super(paramComponentContainer);
    this.form = paramComponentContainer.$form();
    Calendar calendar = Calendar.getInstance();
    this.year = calendar.get(1);
    this.javaMonth = calendar.get(2);
    this.month = this.javaMonth + 1;
    this.day = calendar.get(5);
    this.instant = Dates.DateInstant(this.year, this.month, this.day);
    this.date = new DatePickerDialog((Context)this.container.$context(), this.datePickerListener, this.year, this.javaMonth, this.day);
    this.androidUIHandler = new Handler();
  }
  
  @SimpleEvent(description = "Event that runs after the user chooses a Date in the dialog")
  public void AfterDateSet() {
    EventDispatcher.dispatchEvent(this, "AfterDateSet", new Object[0]);
  }
  
  @SimpleProperty(category = PropertyCategory.APPEARANCE, description = "the Day of the month that was last picked using the DatePicker.")
  public int Day() {
    return this.day;
  }
  
  @SimpleProperty(category = PropertyCategory.APPEARANCE, description = "the instant of the date that was last picked using the DatePicker.")
  public Calendar Instant() {
    return this.instant;
  }
  
  @SimpleFunction(description = "Launches the DatePicker dialog.")
  public void LaunchPicker() {
    click();
  }
  
  @SimpleProperty(category = PropertyCategory.APPEARANCE, description = "the number of the Month that was last picked using the DatePicker. Note that months start in 1 = January, 12 = December.")
  public int Month() {
    return this.month;
  }
  
  @SimpleProperty(category = PropertyCategory.APPEARANCE, description = "Returns the name of the Month that was last picked using the DatePicker, in textual format.")
  public String MonthInText() {
    return this.localizedMonths[this.javaMonth];
  }
  
  @SimpleFunction(description = "Allows the user to set the date to be displayed when the date picker opens.\nValid values for the month field are 1-12 and 1-31 for the day field.\n")
  public void SetDateToDisplay(int paramInt1, int paramInt2, int paramInt3) {
    int i = paramInt2 - 1;
    try {
      GregorianCalendar gregorianCalendar = new GregorianCalendar(paramInt1, i, paramInt3);
      gregorianCalendar.setLenient(false);
      gregorianCalendar.getTime();
    } catch (IllegalArgumentException illegalArgumentException) {
      this.form.dispatchErrorOccurredEvent(this, "SetDateToDisplay", 2401, new Object[0]);
    } 
    this.date.updateDate(paramInt1, i, paramInt3);
    this.instant = Dates.DateInstant(paramInt1, paramInt2, paramInt3);
    this.customDate = true;
  }
  
  @SimpleFunction(description = "Allows the user to set the date from the instant to be displayed when the date picker opens.")
  public void SetDateToDisplayFromInstant(Calendar paramCalendar) {
    int i = Dates.Year(paramCalendar);
    int j = Dates.Month(paramCalendar);
    int k = Dates.Day(paramCalendar);
    this.date.updateDate(i, j, k);
    Dates.DateInstant(i, j, k);
    this.customDate = true;
  }
  
  @SimpleProperty(category = PropertyCategory.APPEARANCE, description = "the Year that was last picked using the DatePicker")
  public int Year() {
    return this.year;
  }
  
  public void click() {
    if (!this.customDate) {
      Calendar calendar = Calendar.getInstance();
      int i = calendar.get(1);
      int j = calendar.get(2);
      int k = calendar.get(5);
      this.date.updateDate(i, j, k);
      this.instant = Dates.DateInstant(i, j + 1, k);
    } else {
      this.customDate = false;
    } 
    this.date.show();
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/com/google/appinventor/components/runtime/DatePicker.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */