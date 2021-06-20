package gnu.kawa.xml;

import gnu.bytecode.ClassType;
import gnu.bytecode.Type;
import gnu.math.DateTime;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.TimeZone;

public class XTimeType extends XDataType {
  public static final XTimeType dateTimeType;
  
  public static final XTimeType dateType;
  
  private static TimeZone fixedTimeZone;
  
  public static final XTimeType gDayType;
  
  public static final XTimeType gMonthDayType;
  
  public static final XTimeType gMonthType;
  
  public static final XTimeType gYearMonthType;
  
  public static final XTimeType gYearType;
  
  public static final XTimeType timeType;
  
  static ClassType typeDateTime = ClassType.make("gnu.math.DateTime");
  
  static {
    dateTimeType = new XTimeType("dateTime", 20);
    dateType = new XTimeType("date", 21);
    timeType = new XTimeType("time", 22);
    gYearMonthType = new XTimeType("gYearMonth", 23);
    gYearType = new XTimeType("gYear", 24);
    gMonthType = new XTimeType("gMonth", 27);
    gMonthDayType = new XTimeType("gMonthDay", 25);
    gDayType = new XTimeType("gDay", 26);
  }
  
  XTimeType(String paramString, int paramInt) {
    super(paramString, (Type)typeDateTime, paramInt);
  }
  
  static int components(int paramInt) {
    byte b = 126;
    switch (paramInt) {
      default:
        b = 0;
      case 20:
      case 28:
        return b;
      case 21:
        return 14;
      case 22:
        return 112;
      case 23:
        return 6;
      case 24:
        return 2;
      case 25:
        return 12;
      case 26:
        return 8;
      case 27:
        return 4;
      case 29:
        return 6;
      case 30:
        break;
    } 
    return 120;
  }
  
  private static TimeZone fixedTimeZone() {
    // Byte code:
    //   0: ldc gnu/kawa/xml/XTimeType
    //   2: monitorenter
    //   3: getstatic gnu/kawa/xml/XTimeType.fixedTimeZone : Ljava/util/TimeZone;
    //   6: ifnonnull -> 24
    //   9: invokestatic getDefault : ()Ljava/util/TimeZone;
    //   12: invokevirtual getRawOffset : ()I
    //   15: ldc 60000
    //   17: idiv
    //   18: invokestatic minutesToTimeZone : (I)Ljava/util/TimeZone;
    //   21: putstatic gnu/kawa/xml/XTimeType.fixedTimeZone : Ljava/util/TimeZone;
    //   24: getstatic gnu/kawa/xml/XTimeType.fixedTimeZone : Ljava/util/TimeZone;
    //   27: astore_0
    //   28: ldc gnu/kawa/xml/XTimeType
    //   30: monitorexit
    //   31: aload_0
    //   32: areturn
    //   33: astore_0
    //   34: ldc gnu/kawa/xml/XTimeType
    //   36: monitorexit
    //   37: aload_0
    //   38: athrow
    // Exception table:
    //   from	to	target	type
    //   3	24	33	finally
    //   24	28	33	finally
  }
  
  public static DateTime parseDateTime(String paramString, int paramInt) {
    DateTime dateTime = DateTime.parse(paramString, paramInt);
    if (dateTime.isZoneUnspecified())
      dateTime.setTimeZone(fixedTimeZone()); 
    return dateTime;
  }
  
  public boolean isInstance(Object paramObject) {
    return (paramObject instanceof DateTime && components(this.typeCode) == ((DateTime)paramObject).components());
  }
  
  public DateTime now() {
    return new DateTime(components(this.typeCode) | 0x80, (GregorianCalendar)Calendar.getInstance(fixedTimeZone()));
  }
  
  public Object valueOf(String paramString) {
    return parseDateTime(paramString, components(this.typeCode));
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/gnu/kawa/xml/XTimeType.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */