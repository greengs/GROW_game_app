package com.google.appinventor.components.runtime;

import com.google.appinventor.components.annotations.DesignerComponent;
import com.google.appinventor.components.annotations.DesignerProperty;
import com.google.appinventor.components.annotations.PropertyCategory;
import com.google.appinventor.components.annotations.SimpleEvent;
import com.google.appinventor.components.annotations.SimpleFunction;
import com.google.appinventor.components.annotations.SimpleObject;
import com.google.appinventor.components.annotations.SimpleProperty;
import com.google.appinventor.components.common.ComponentCategory;
import com.google.appinventor.components.runtime.errors.YailRuntimeError;
import com.google.appinventor.components.runtime.util.Dates;
import com.google.appinventor.components.runtime.util.TimerInternal;
import java.util.Calendar;
import java.util.GregorianCalendar;

@DesignerComponent(category = ComponentCategory.SENSORS, description = "<p>Non-visible component that provides the instant in time using the internal clock on the phone. It can fire a timer at regularly set intervals and perform time calculations, manipulations, and conversions.</p> <p>Methods to convert an instant to text are also available. Acceptable patterns are empty string, MM/DD/YYYY HH:mm:ss a, or MMM d, yyyyHH:mm. The empty string will provide the default format, which is \"MMM d, yyyy HH:mm:ss a\" for FormatDateTime \"MMM d, yyyy\" for FormatDate. To see all possible format, please see <a href=\"https://docs.oracle.com/javase/7/docs/api/java/text/SimpleDateFormat.html\" _target=\"_blank\">here</a>. </p> ", iconName = "images/clock.png", nonVisible = true, version = 4)
@SimpleObject
public class Clock extends AndroidNonvisibleComponent implements Component, AlarmHandler, OnStopListener, OnResumeListener, OnDestroyListener, Deleteable {
  private static final boolean DEFAULT_ENABLED = true;
  
  private static final int DEFAULT_INTERVAL = 1000;
  
  private boolean onScreen = false;
  
  private boolean timerAlwaysFires = true;
  
  private TimerInternal timerInternal;
  
  public Clock() {
    super(null);
  }
  
  public Clock(ComponentContainer paramComponentContainer) {
    super(paramComponentContainer.$form());
    this.timerInternal = new TimerInternal(this, true, 1000);
    this.form.registerForOnResume(this);
    this.form.registerForOnStop(this);
    this.form.registerForOnDestroy(this);
    if (this.form instanceof ReplForm)
      this.onScreen = true; 
  }
  
  @SimpleFunction(description = "Returns an instant in time some days after the given instant.")
  public static Calendar AddDays(Calendar paramCalendar, int paramInt) {
    paramCalendar = (Calendar)paramCalendar.clone();
    Dates.DateAdd(paramCalendar, 5, paramInt);
    return paramCalendar;
  }
  
  @SimpleFunction(description = "Returns an instant in time some duration after the argument")
  public static Calendar AddDuration(Calendar paramCalendar, long paramLong) {
    paramCalendar = (Calendar)paramCalendar.clone();
    Dates.DateAddInMillis(paramCalendar, paramLong);
    return paramCalendar;
  }
  
  @SimpleFunction(description = "Returns an instant in time some hours after the given instant.")
  public static Calendar AddHours(Calendar paramCalendar, int paramInt) {
    paramCalendar = (Calendar)paramCalendar.clone();
    Dates.DateAdd(paramCalendar, 11, paramInt);
    return paramCalendar;
  }
  
  @SimpleFunction(description = "Returns an instant in time some minutes after the given instant.")
  public static Calendar AddMinutes(Calendar paramCalendar, int paramInt) {
    paramCalendar = (Calendar)paramCalendar.clone();
    Dates.DateAdd(paramCalendar, 12, paramInt);
    return paramCalendar;
  }
  
  @SimpleFunction(description = "Returns an instant in time some months after the given instant.")
  public static Calendar AddMonths(Calendar paramCalendar, int paramInt) {
    paramCalendar = (Calendar)paramCalendar.clone();
    Dates.DateAdd(paramCalendar, 2, paramInt);
    return paramCalendar;
  }
  
  @SimpleFunction(description = "Returns an instant in time some seconds after the given instant.")
  public static Calendar AddSeconds(Calendar paramCalendar, int paramInt) {
    paramCalendar = (Calendar)paramCalendar.clone();
    Dates.DateAdd(paramCalendar, 13, paramInt);
    return paramCalendar;
  }
  
  @SimpleFunction(description = "Returns An instant in time some weeks after the given instant.")
  public static Calendar AddWeeks(Calendar paramCalendar, int paramInt) {
    paramCalendar = (Calendar)paramCalendar.clone();
    Dates.DateAdd(paramCalendar, 3, paramInt);
    return paramCalendar;
  }
  
  @SimpleFunction(description = "Returns an instant in time some years after the given instant.")
  public static Calendar AddYears(Calendar paramCalendar, int paramInt) {
    paramCalendar = (Calendar)paramCalendar.clone();
    Dates.DateAdd(paramCalendar, 1, paramInt);
    return paramCalendar;
  }
  
  @SimpleFunction(description = "Returns the day of the month (1-31) from the instant.")
  public static int DayOfMonth(Calendar paramCalendar) {
    return Dates.Day(paramCalendar);
  }
  
  @SimpleFunction(description = "Returns duration, which is milliseconds elapsed between instants.")
  public static long Duration(Calendar paramCalendar1, Calendar paramCalendar2) {
    return paramCalendar2.getTimeInMillis() - paramCalendar1.getTimeInMillis();
  }
  
  @SimpleFunction(description = "Converts the duration to the number of days.")
  public static long DurationToDays(long paramLong) {
    return Dates.ConvertDuration(paramLong, 5);
  }
  
  @SimpleFunction(description = "Converts the duration to the number of hours.")
  public static long DurationToHours(long paramLong) {
    return Dates.ConvertDuration(paramLong, 11);
  }
  
  @SimpleFunction(description = "Converts the duration to the number of minutes.")
  public static long DurationToMinutes(long paramLong) {
    return Dates.ConvertDuration(paramLong, 12);
  }
  
  @SimpleFunction(description = "Converts the duration to the number of seconds.")
  public static long DurationToSeconds(long paramLong) {
    return Dates.ConvertDuration(paramLong, 13);
  }
  
  @SimpleFunction(description = "Converts the duration to the number of weeks.")
  public static long DurationToWeeks(long paramLong) {
    return Dates.ConvertDuration(paramLong, 3);
  }
  
  @SimpleFunction(description = "Text representing the date of an instant in the specified pattern")
  public static String FormatDate(Calendar paramCalendar, String paramString) {
    try {
      return Dates.FormatDate(paramCalendar, paramString);
    } catch (IllegalArgumentException illegalArgumentException) {
      throw new YailRuntimeError("Illegal argument for pattern in Clock.FormatDate. Acceptable values are empty string, MM/dd/YYYY, or MMM d, yyyy. For all possible patterns, see https://docs.oracle.com/javase/7/docs/api/java/text/SimpleDateFormat.html", "Sorry to be so picky.");
    } 
  }
  
  @SimpleFunction(description = "Returns text representing the date and time of an instant in the specified pattern")
  public static String FormatDateTime(Calendar paramCalendar, String paramString) {
    try {
      return Dates.FormatDateTime(paramCalendar, paramString);
    } catch (IllegalArgumentException illegalArgumentException) {
      throw new YailRuntimeError("Illegal argument for pattern in Clock.FormatDateTime. Acceptable values are empty string, MM/dd/YYYY hh:mm:ss a, MMM d, yyyy HH:mm For all possible patterns, see https://docs.oracle.com/javase/7/docs/api/java/text/SimpleDateFormat.html", "Sorry to be so picky.");
    } 
  }
  
  @SimpleFunction(description = "Text representing the time of an instant")
  public static String FormatTime(Calendar paramCalendar) {
    return Dates.FormatTime(paramCalendar);
  }
  
  @SimpleFunction(description = "Returns the instant in time measured as milliseconds since 1970.")
  public static long GetMillis(Calendar paramCalendar) {
    return paramCalendar.getTimeInMillis();
  }
  
  @SimpleFunction(description = "Returns the hour of the day (0-23) from the instant.")
  public static int Hour(Calendar paramCalendar) {
    return Dates.Hour(paramCalendar);
  }
  
  @SimpleFunction(description = "Returns an instant in time specified by MM/dd/YYYY hh:mm:ss or MM/dd/YYYY or hh:mm.")
  public static Calendar MakeInstant(String paramString) {
    try {
      return Dates.DateValue(paramString);
    } catch (IllegalArgumentException illegalArgumentException) {
      throw new YailRuntimeError("Argument to MakeInstant should have form MM/dd/YYYY hh:mm:ss, or MM/dd/YYYY or hh:mm", "Sorry to be so picky.");
    } 
  }
  
  @SimpleFunction(description = "Returns an instant in time specified by the milliseconds since 1970 in UTC.")
  public static Calendar MakeInstantFromMillis(long paramLong) {
    Calendar calendar = Dates.Now();
    calendar.setTimeInMillis(paramLong);
    return calendar;
  }
  
  @SimpleFunction(description = "Returns the minute of the hour (0-59) from the instant.")
  public static int Minute(Calendar paramCalendar) {
    return Dates.Minute(paramCalendar);
  }
  
  @SimpleFunction(description = "Returns the month of the year represented as a number from 1 to 12).")
  public static int Month(Calendar paramCalendar) {
    return Dates.Month(paramCalendar) + 1;
  }
  
  @SimpleFunction(description = "Returns the name of the month from the instant, e.g., January, February, March...")
  public static String MonthName(Calendar paramCalendar) {
    return Dates.MonthName(paramCalendar);
  }
  
  @SimpleFunction(description = "Returns the current instant in time read from phone's clock.")
  public static Calendar Now() {
    return Dates.Now();
  }
  
  @SimpleFunction(description = "Returns the second of the minute (0-59) from the instant.")
  public static int Second(Calendar paramCalendar) {
    return Dates.Second(paramCalendar);
  }
  
  @SimpleFunction(description = "Returns the phone's internal time.")
  public static long SystemTime() {
    return Dates.Timer();
  }
  
  @SimpleFunction(description = "Returns the day of the week represented as a number from 1 (Sunday) to 7 (Saturday).")
  public static int Weekday(Calendar paramCalendar) {
    return Dates.Weekday(paramCalendar);
  }
  
  @SimpleFunction(description = "Returns the name of the day of the week from the instant.")
  public static String WeekdayName(Calendar paramCalendar) {
    return Dates.WeekdayName(paramCalendar);
  }
  
  @SimpleFunction(description = "The year")
  public static int Year(Calendar paramCalendar) {
    return Dates.Year(paramCalendar);
  }
  
  @SimpleFunction(description = "Returns an instant in time specified by year, month, date in UTC.\nValid values for the month field are 1-12 and 1-31 for the day field.")
  public Calendar MakeDate(int paramInt1, int paramInt2, int paramInt3) {
    try {
      GregorianCalendar gregorianCalendar = new GregorianCalendar(paramInt1, paramInt2 - 1, paramInt3);
      gregorianCalendar.setLenient(false);
      gregorianCalendar.getTime();
    } catch (IllegalArgumentException illegalArgumentException) {
      this.form.dispatchErrorOccurredEvent(this, "MakeDate", 2401, new Object[0]);
    } 
    return Dates.DateInstant(paramInt1, paramInt2, paramInt3);
  }
  
  @SimpleFunction(description = "Returns an instant in time specified by year, month, date, hour, minute, second in UTC.")
  public Calendar MakeInstantFromParts(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6) {
    // Byte code:
    //   0: new java/util/GregorianCalendar
    //   3: dup
    //   4: iload_1
    //   5: iload_2
    //   6: iconst_1
    //   7: isub
    //   8: iload_3
    //   9: invokespecial <init> : (III)V
    //   12: astore #7
    //   14: aload #7
    //   16: iconst_0
    //   17: invokevirtual setLenient : (Z)V
    //   20: aload #7
    //   22: invokevirtual getTime : ()Ljava/util/Date;
    //   25: pop
    //   26: iload_1
    //   27: iload_2
    //   28: iload_3
    //   29: invokestatic DateInstant : (III)Ljava/util/Calendar;
    //   32: astore #7
    //   34: aload #7
    //   36: bipush #11
    //   38: iload #4
    //   40: invokevirtual set : (II)V
    //   43: aload #7
    //   45: bipush #12
    //   47: iload #5
    //   49: invokevirtual set : (II)V
    //   52: aload #7
    //   54: bipush #13
    //   56: iload #6
    //   58: invokevirtual set : (II)V
    //   61: aload #7
    //   63: areturn
    //   64: astore #7
    //   66: aload_0
    //   67: getfield form : Lcom/google/appinventor/components/runtime/Form;
    //   70: aload_0
    //   71: ldc_w 'MakeInstantFromParts'
    //   74: sipush #2401
    //   77: iconst_0
    //   78: anewarray java/lang/Object
    //   81: invokevirtual dispatchErrorOccurredEvent : (Lcom/google/appinventor/components/runtime/Component;Ljava/lang/String;I[Ljava/lang/Object;)V
    //   84: goto -> 26
    //   87: astore #8
    //   89: aload_0
    //   90: getfield form : Lcom/google/appinventor/components/runtime/Form;
    //   93: aload_0
    //   94: ldc_w 'MakeInstantFromParts'
    //   97: sipush #2401
    //   100: iconst_0
    //   101: anewarray java/lang/Object
    //   104: invokevirtual dispatchErrorOccurredEvent : (Lcom/google/appinventor/components/runtime/Component;Ljava/lang/String;I[Ljava/lang/Object;)V
    //   107: aload #7
    //   109: areturn
    //   110: astore #7
    //   112: goto -> 66
    // Exception table:
    //   from	to	target	type
    //   0	14	64	java/lang/IllegalArgumentException
    //   14	26	110	java/lang/IllegalArgumentException
    //   34	61	87	java/lang/IllegalArgumentException
  }
  
  @SimpleFunction(description = "Returns an instant in time specified by hour, minute, second in UTC.")
  public Calendar MakeTime(int paramInt1, int paramInt2, int paramInt3) {
    GregorianCalendar gregorianCalendar = new GregorianCalendar();
    try {
      gregorianCalendar.set(11, paramInt1);
      gregorianCalendar.set(12, paramInt2);
      gregorianCalendar.set(13, paramInt3);
      return gregorianCalendar;
    } catch (IllegalArgumentException illegalArgumentException) {
      this.form.dispatchErrorOccurredEvent(this, "MakeTime", 2401, new Object[0]);
      return gregorianCalendar;
    } 
  }
  
  @SimpleEvent(description = "The Timer event runs when the timer has gone off.")
  public void Timer() {
    if (this.timerAlwaysFires || this.onScreen)
      EventDispatcher.dispatchEvent(this, "Timer", new Object[0]); 
  }
  
  @DesignerProperty(defaultValue = "True", editorType = "boolean")
  @SimpleProperty
  public void TimerAlwaysFires(boolean paramBoolean) {
    this.timerAlwaysFires = paramBoolean;
  }
  
  @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "Will fire even when application is not showing on the screen if true")
  public boolean TimerAlwaysFires() {
    return this.timerAlwaysFires;
  }
  
  @DesignerProperty(defaultValue = "True", editorType = "boolean")
  @SimpleProperty
  public void TimerEnabled(boolean paramBoolean) {
    this.timerInternal.Enabled(paramBoolean);
  }
  
  @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "Fires timer if true")
  public boolean TimerEnabled() {
    return this.timerInternal.Enabled();
  }
  
  @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "Interval between timer events in ms")
  public int TimerInterval() {
    return this.timerInternal.Interval();
  }
  
  @DesignerProperty(defaultValue = "1000", editorType = "non_negative_integer")
  @SimpleProperty
  public void TimerInterval(int paramInt) {
    this.timerInternal.Interval(paramInt);
  }
  
  public void alarm() {
    Timer();
  }
  
  public void onDelete() {
    this.timerInternal.Enabled(false);
  }
  
  public void onDestroy() {
    this.timerInternal.Enabled(false);
  }
  
  public void onResume() {
    this.onScreen = true;
  }
  
  public void onStop() {
    this.onScreen = false;
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/com/google/appinventor/components/runtime/Clock.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */