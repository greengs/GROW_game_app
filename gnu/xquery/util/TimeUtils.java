package gnu.xquery.util;

import gnu.kawa.xml.XTimeType;
import gnu.mapping.Values;
import gnu.mapping.WrongType;
import gnu.math.DateTime;
import gnu.math.Duration;
import gnu.math.IntNum;
import gnu.xml.TextUtils;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.TimeZone;

public class TimeUtils {
  static final ThreadLocal<DateTime> currentDateTimeLocal = new ThreadLocal<DateTime>();
  
  public static Object adjustDateTimeToTimezone(Object paramObject) {
    return adjustDateTimeToTimezone(paramObject, getImplicitTimezone());
  }
  
  public static Object adjustDateTimeToTimezone(Object paramObject1, Object paramObject2) {
    return (paramObject1 == Values.empty || paramObject1 == null) ? paramObject1 : adjustDateTimeToTimezoneRaw(coerceToDateTime("adjust-dateTime-to-timezone", paramObject1), paramObject2);
  }
  
  static Object adjustDateTimeToTimezoneRaw(DateTime paramDateTime, Object paramObject) {
    if (paramObject == Values.empty || paramObject == null)
      return paramDateTime.withZoneUnspecified(); 
    paramObject = paramObject;
    if (paramObject.getNanoSecondsOnly() != 0 || paramObject.getSecondsOnly() != 0)
      throw new IllegalArgumentException("timezone offset with fractional minute"); 
    int i = (int)paramObject.getTotalMinutes();
    if (i < -840 || i > 840)
      throw new IllegalArgumentException("timezone offset out of range"); 
    return paramDateTime.adjustTimezone(i);
  }
  
  public static Object adjustDateToTimezone(Object paramObject) {
    return adjustDateToTimezone(paramObject, getImplicitTimezone());
  }
  
  public static Object adjustDateToTimezone(Object paramObject1, Object paramObject2) {
    return (paramObject1 == Values.empty || paramObject1 == null) ? paramObject1 : adjustDateTimeToTimezoneRaw(coerceToDate("adjust-date-to-timezone", paramObject1), paramObject2);
  }
  
  public static Object adjustTimeToTimezone(Object paramObject) {
    return adjustTimeToTimezone(paramObject, getImplicitTimezone());
  }
  
  public static Object adjustTimeToTimezone(Object paramObject1, Object paramObject2) {
    return (paramObject1 == Values.empty || paramObject1 == null) ? paramObject1 : adjustDateTimeToTimezoneRaw(coerceToTime("adjust-time-to-timezone", paramObject1), paramObject2);
  }
  
  static IntNum asInteger(int paramInt) {
    return IntNum.make(paramInt);
  }
  
  static DateTime coerceToDate(String paramString, Object paramObject) {
    if (XTimeType.dateType.isInstance(paramObject))
      return (DateTime)paramObject; 
    if (paramObject instanceof gnu.kawa.xml.KNode || paramObject instanceof gnu.kawa.xml.UntypedAtomic)
      return XTimeType.parseDateTime(TextUtils.stringValue(paramObject), 14); 
    throw new WrongType(paramString, 1, paramObject, "xs:date");
  }
  
  static DateTime coerceToDateTime(String paramString, Object paramObject) {
    if (XTimeType.dateTimeType.isInstance(paramObject))
      return (DateTime)paramObject; 
    if (paramObject instanceof gnu.kawa.xml.KNode || paramObject instanceof gnu.kawa.xml.UntypedAtomic)
      return XTimeType.parseDateTime(TextUtils.stringValue(paramObject), 126); 
    throw new WrongType(paramString, 1, paramObject, "xs:dateTime");
  }
  
  static Duration coerceToDuration(String paramString, Object paramObject) {
    if (paramObject instanceof Duration)
      return (Duration)paramObject; 
    throw new WrongType(paramString, 1, paramObject, "xs:duration");
  }
  
  static DateTime coerceToTime(String paramString, Object paramObject) {
    if (XTimeType.timeType.isInstance(paramObject))
      return (DateTime)paramObject; 
    if (paramObject instanceof gnu.kawa.xml.KNode || paramObject instanceof gnu.kawa.xml.UntypedAtomic)
      return XTimeType.parseDateTime(TextUtils.stringValue(paramObject), 112); 
    throw new WrongType(paramString, 1, paramObject, "xs:time");
  }
  
  public static DateTime currentDate() {
    return currentDateTime().cast(14);
  }
  
  public static DateTime currentDateTime() {
    DateTime dateTime2 = currentDateTimeLocal.get();
    DateTime dateTime1 = dateTime2;
    if (dateTime2 == null) {
      dateTime1 = now();
      currentDateTimeLocal.set(dateTime1);
    } 
    return dateTime1;
  }
  
  public static DateTime currentTime() {
    return currentDateTime().cast(112);
  }
  
  public static Object dateTime(Object paramObject1, Object paramObject2) {
    int i;
    boolean bool;
    if (paramObject1 == null || paramObject1 == Values.empty)
      return paramObject1; 
    if (paramObject2 == null || paramObject2 == Values.empty)
      return paramObject2; 
    paramObject1 = coerceToDate("dateTime", paramObject1);
    paramObject2 = coerceToTime("dateTime", paramObject2);
    StringBuffer stringBuffer = new StringBuffer();
    paramObject1.toStringDate(stringBuffer);
    stringBuffer.append('T');
    paramObject2.toStringTime(stringBuffer);
    if (!paramObject1.isZoneUnspecified()) {
      i = 1;
    } else {
      i = 0;
    } 
    if (!paramObject2.isZoneUnspecified()) {
      bool = true;
    } else {
      bool = false;
    } 
    if (i || bool) {
      int j = paramObject1.getZoneMinutes();
      int k = paramObject2.getZoneMinutes();
      if (i && bool && j != k)
        throw new Error("dateTime: incompatible timezone in arguments"); 
      if (i) {
        i = j;
      } else {
        i = k;
      } 
      DateTime.toStringZone(i, stringBuffer);
    } 
    return XTimeType.dateTimeType.valueOf(stringBuffer.toString());
  }
  
  public static Object dayFromDate(Object paramObject) {
    return (paramObject == null || paramObject == Values.empty) ? paramObject : asInteger(coerceToDate("day-from-date", paramObject).getDay());
  }
  
  public static Object dayFromDateTime(Object paramObject) {
    return (paramObject == null || paramObject == Values.empty) ? paramObject : asInteger(coerceToDateTime("day-from-dateTime", paramObject).getDay());
  }
  
  public static Object daysFromDuration(Object paramObject) {
    return (paramObject == null || paramObject == Values.empty) ? paramObject : asInteger(coerceToDuration("days-from-duration", paramObject).getDays());
  }
  
  public static Duration getImplicitTimezone() {
    return Duration.makeMinutes(TimeZone.getDefault().getRawOffset() / 60000);
  }
  
  static Number getSeconds(DateTime paramDateTime) {
    int i = paramDateTime.getSecondsOnly();
    long l = paramDateTime.getNanoSecondsOnly();
    return (Number)((l == 0L) ? IntNum.make(i) : new BigDecimal(BigInteger.valueOf(l + i * 1000000000L), 9));
  }
  
  public static Object hoursFromDateTime(Object paramObject) {
    return (paramObject == null || paramObject == Values.empty) ? paramObject : asInteger(coerceToDateTime("hours-from-dateTime", paramObject).getHours());
  }
  
  public static Object hoursFromDuration(Object paramObject) {
    return (paramObject == null || paramObject == Values.empty) ? paramObject : asInteger(coerceToDuration("hours-from-duration", paramObject).getHours());
  }
  
  public static Object hoursFromTime(Object paramObject) {
    return (paramObject == null || paramObject == Values.empty) ? paramObject : asInteger(coerceToTime("hours-from-time", paramObject).getHours());
  }
  
  public static Object implicitTimezone() {
    return timeZoneFromXTime(currentDateTime());
  }
  
  public static Object minutesFromDateTime(Object paramObject) {
    return (paramObject == null || paramObject == Values.empty) ? paramObject : asInteger(coerceToDateTime("minutes-from-dateTime", paramObject).getMinutes());
  }
  
  public static Object minutesFromDuration(Object paramObject) {
    return (paramObject == null || paramObject == Values.empty) ? paramObject : asInteger(coerceToDuration("minutes-from-duration", paramObject).getMinutes());
  }
  
  public static Object minutesFromTime(Object paramObject) {
    return (paramObject == null || paramObject == Values.empty) ? paramObject : asInteger(coerceToTime("minutes-from-time", paramObject).getMinutes());
  }
  
  public static Object monthFromDate(Object paramObject) {
    return (paramObject == null || paramObject == Values.empty) ? paramObject : asInteger(coerceToDate("month-from-date", paramObject).getMonth());
  }
  
  public static Object monthFromDateTime(Object paramObject) {
    return (paramObject == null || paramObject == Values.empty) ? paramObject : asInteger(coerceToDateTime("month-from-dateTime", paramObject).getMonth());
  }
  
  public static Object monthsFromDuration(Object paramObject) {
    return (paramObject == null || paramObject == Values.empty) ? paramObject : asInteger(coerceToDuration("months-from-duration", paramObject).getMonths());
  }
  
  public static DateTime now() {
    return XTimeType.dateTimeType.now();
  }
  
  public static BigDecimal secondsBigDecimalFromDuration(long paramLong, int paramInt) {
    boolean bool;
    long l;
    if (paramInt == 0)
      return BigDecimal.valueOf(paramLong); 
    byte b = 9;
    if ((int)paramLong != paramLong) {
      bool = true;
    } else {
      bool = false;
    } 
    if (bool) {
      l = paramInt;
      paramInt = b;
    } else {
      l = 1000000000L * paramLong + paramInt;
      paramInt = b;
    } 
    while (l % 10L == 0L) {
      l /= 10L;
      paramInt--;
    } 
    BigDecimal bigDecimal2 = new BigDecimal(BigInteger.valueOf(l), paramInt);
    BigDecimal bigDecimal1 = bigDecimal2;
    return bool ? BigDecimal.valueOf(paramLong).add(bigDecimal2) : bigDecimal1;
  }
  
  public static Object secondsFromDateTime(Object paramObject) {
    return (paramObject == null || paramObject == Values.empty) ? paramObject : getSeconds(coerceToDateTime("seconds-from-dateTime", paramObject));
  }
  
  public static Object secondsFromDuration(Object paramObject) {
    if (paramObject == null || paramObject == Values.empty)
      return paramObject; 
    paramObject = coerceToDuration("seconds-from-duration", paramObject);
    int i = paramObject.getSecondsOnly();
    int j = paramObject.getNanoSecondsOnly();
    return (j == 0) ? asInteger(i) : secondsBigDecimalFromDuration(i, j);
  }
  
  public static Object secondsFromTime(Object paramObject) {
    return (paramObject == null || paramObject == Values.empty) ? paramObject : getSeconds(coerceToTime("seconds-from-time", paramObject));
  }
  
  static Object timeZoneFromXTime(DateTime paramDateTime) {
    return paramDateTime.isZoneUnspecified() ? Values.empty : Duration.makeMinutes(paramDateTime.getZoneMinutes());
  }
  
  public static Object timezoneFromDate(Object paramObject) {
    return (paramObject == null || paramObject == Values.empty) ? paramObject : timeZoneFromXTime(coerceToDate("timezone-from-date", paramObject));
  }
  
  public static Object timezoneFromDateTime(Object paramObject) {
    return (paramObject == null || paramObject == Values.empty) ? paramObject : timeZoneFromXTime(coerceToDateTime("timezone-from-datetime", paramObject));
  }
  
  public static Object timezoneFromTime(Object paramObject) {
    return (paramObject == null || paramObject == Values.empty) ? paramObject : timeZoneFromXTime(coerceToTime("timezone-from-time", paramObject));
  }
  
  public static Object yearFromDate(Object paramObject) {
    return (paramObject == null || paramObject == Values.empty) ? paramObject : asInteger(coerceToDate("year-from-date", paramObject).getYear());
  }
  
  public static Object yearFromDateTime(Object paramObject) {
    return (paramObject == null || paramObject == Values.empty) ? paramObject : asInteger(coerceToDateTime("year-from-dateTime", paramObject).getYear());
  }
  
  public static Object yearsFromDuration(Object paramObject) {
    return (paramObject == null || paramObject == Values.empty) ? paramObject : asInteger(coerceToDuration("years-from-duration", paramObject).getYears());
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/gnu/xquery/util/TimeUtils.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */