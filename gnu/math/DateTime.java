package gnu.math;

import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

public class DateTime extends Quantity implements Cloneable {
  public static final int DATE_MASK = 14;
  
  static final int DAY_COMPONENT = 3;
  
  public static final int DAY_MASK = 8;
  
  public static TimeZone GMT;
  
  static final int HOURS_COMPONENT = 4;
  
  public static final int HOURS_MASK = 16;
  
  static final int MINUTES_COMPONENT = 5;
  
  public static final int MINUTES_MASK = 32;
  
  static final int MONTH_COMPONENT = 2;
  
  public static final int MONTH_MASK = 4;
  
  static final int SECONDS_COMPONENT = 6;
  
  public static final int SECONDS_MASK = 64;
  
  static final int TIMEZONE_COMPONENT = 7;
  
  public static final int TIMEZONE_MASK = 128;
  
  public static final int TIME_MASK = 112;
  
  static final int YEAR_COMPONENT = 1;
  
  public static final int YEAR_MASK = 2;
  
  private static final Date minDate = new Date(Long.MIN_VALUE);
  
  GregorianCalendar calendar = new GregorianCalendar();
  
  int mask;
  
  int nanoSeconds;
  
  Unit unit = Unit.date;
  
  static {
    GMT = TimeZone.getTimeZone("GMT");
  }
  
  public DateTime(int paramInt) {
    this.calendar.setGregorianChange(minDate);
    this.calendar.clear();
    this.mask = paramInt;
  }
  
  public DateTime(int paramInt, GregorianCalendar paramGregorianCalendar) {
    this.mask = paramInt;
  }
  
  public static DateTime add(DateTime paramDateTime, Duration paramDuration, int paramInt) {
    if (paramDuration.unit == Unit.duration || (paramDuration.unit == Unit.month && (paramDateTime.mask & 0xE) != 14))
      throw new IllegalArgumentException("invalid date/time +/- duration combinatuion"); 
    DateTime dateTime = new DateTime(paramDateTime.mask, (GregorianCalendar)paramDateTime.calendar.clone());
    if (paramDuration.months != 0) {
      int j;
      int k;
      int i = dateTime.getYear() * 12 + dateTime.calendar.get(2) + paramDuration.months * paramInt;
      int n = dateTime.calendar.get(5);
      if (i >= 12) {
        k = i / 12;
        j = i % 12;
        dateTime.calendar.set(0, 1);
        i = daysInMonth(j, k);
      } else {
        i = 11 - i;
        dateTime.calendar.set(0, 0);
        k = i / 12 + 1;
        j = 11 - i % 12;
        i = daysInMonth(j, 1);
      } 
      int m = n;
      if (n > i)
        m = i; 
      dateTime.calendar.set(k, j, m);
    } 
    long l = paramDateTime.nanoSeconds + paramInt * (paramDuration.seconds * 1000000000L + paramDuration.nanos);
    if (l != 0L) {
      long l1 = l;
      if ((paramDateTime.mask & 0x70) == 0) {
        long l3 = l % 86400000000000L;
        l1 = l3;
        if (l3 < 0L)
          l1 = l3 + 86400000000000L; 
        l1 = l - l1;
      } 
      l = dateTime.calendar.getTimeInMillis();
      long l2 = l1 / 1000000000L;
      dateTime.calendar.setTimeInMillis(l + l2 * 1000L);
      dateTime.nanoSeconds = (int)(l1 % 1000000000L);
    } 
    return dateTime;
  }
  
  public static DateTime addMinutes(DateTime paramDateTime, int paramInt) {
    return addSeconds(paramDateTime, paramInt * 60);
  }
  
  public static DateTime addSeconds(DateTime paramDateTime, int paramInt) {
    DateTime dateTime = new DateTime(paramDateTime.mask, (GregorianCalendar)paramDateTime.calendar.clone());
    long l = paramInt * 1000000000L;
    if (l != 0L) {
      l += paramDateTime.nanoSeconds;
      long l1 = paramDateTime.calendar.getTimeInMillis();
      long l2 = l / 1000000L;
      dateTime.calendar.setTimeInMillis(l1 + l2);
      dateTime.nanoSeconds = (int)(l % 1000000L);
    } 
    return dateTime;
  }
  
  private static void append(int paramInt1, StringBuffer paramStringBuffer, int paramInt2) {
    int i = paramStringBuffer.length();
    paramStringBuffer.append(paramInt1);
    paramInt1 = i + paramInt2 - paramStringBuffer.length();
    while (true) {
      if (--paramInt1 >= 0) {
        paramStringBuffer.insert(i, '0');
        continue;
      } 
      break;
    } 
  }
  
  public static int compare(DateTime paramDateTime1, DateTime paramDateTime2) {
    long l3 = paramDateTime1.calendar.getTimeInMillis();
    long l5 = paramDateTime2.calendar.getTimeInMillis();
    long l4 = l3;
    long l2 = l5;
    if (((paramDateTime1.mask | paramDateTime2.mask) & 0xE) == 0) {
      long l = l3;
      if (l3 < 0L)
        l = l3 + 86400000L; 
      l4 = l;
      l2 = l5;
      if (l5 < 0L) {
        l2 = l5 + 86400000L;
        l4 = l;
      } 
    } 
    int j = paramDateTime1.nanoSeconds;
    int i = paramDateTime2.nanoSeconds;
    long l1 = l4 + (j / 1000000);
    l2 += (i / 1000000);
    j %= 1000000;
    i %= 1000000;
    return (l1 < l2) ? -1 : ((l1 > l2) ? 1 : ((j < i) ? -1 : ((j > i) ? 1 : 0)));
  }
  
  public static int daysInMonth(int paramInt1, int paramInt2) {
    switch (paramInt1) {
      default:
        return 31;
      case 3:
      case 5:
      case 8:
      case 10:
        return 30;
      case 1:
        break;
    } 
    return isLeapYear(paramInt2) ? 29 : 28;
  }
  
  public static boolean isLeapYear(int paramInt) {
    return (paramInt % 4 == 0 && (paramInt % 100 != 0 || paramInt % 400 == 0));
  }
  
  public static TimeZone minutesToTimeZone(int paramInt) {
    if (paramInt == 0)
      return GMT; 
    StringBuffer stringBuffer = new StringBuffer("GMT");
    toStringZone(paramInt, stringBuffer);
    return TimeZone.getTimeZone(stringBuffer.toString());
  }
  
  public static DateTime parse(String paramString, int paramInt) {
    boolean bool2;
    boolean bool1 = true;
    DateTime dateTime = new DateTime(paramInt);
    paramString = paramString.trim();
    int j = paramString.length();
    int i = 0;
    if ((paramInt & 0xE) != 0) {
      bool2 = true;
    } else {
      bool2 = false;
    } 
    if ((paramInt & 0x70) == 0)
      bool1 = false; 
    if (bool2) {
      paramInt = dateTime.parseDate(paramString, 0, paramInt);
      i = paramInt;
      if (bool1)
        if (paramInt < 0 || paramInt >= j || paramString.charAt(paramInt) != 'T') {
          i = -1;
        } else {
          i = paramInt + 1;
        }  
    } 
    paramInt = i;
    if (bool1)
      paramInt = dateTime.parseTime(paramString, i); 
    if (dateTime.parseZone(paramString, paramInt) != j)
      throw new NumberFormatException("Unrecognized date/time '" + paramString + '\''); 
    return dateTime;
  }
  
  private static int parseDigits(String paramString, int paramInt) {
    int i = paramInt;
    paramInt = -1;
    int j = paramString.length();
    while (true) {
      if (i < j) {
        int k = Character.digit(paramString.charAt(i), 10);
        if (k >= 0) {
          if (paramInt > 20000)
            return 0; 
          if (paramInt < 0) {
            paramInt = k;
          } else {
            paramInt = paramInt * 10 + k;
          } 
          i++;
          continue;
        } 
      } 
      return (paramInt < 0) ? i : (i | paramInt << 16);
    } 
  }
  
  public static Duration sub(DateTime paramDateTime1, DateTime paramDateTime2) {
    long l1 = paramDateTime1.calendar.getTimeInMillis();
    long l2 = paramDateTime2.calendar.getTimeInMillis();
    int i = paramDateTime1.nanoSeconds;
    int j = paramDateTime2.nanoSeconds;
    long l3 = (i / 1000000);
    long l4 = (j / 1000000);
    i = j % 1000000;
    l1 = l1 + l3 - l2 + l4;
    l2 = l1 / 1000L;
    i = (int)(l1 % 1000L * 1000000L + i - i);
    return Duration.make(0, l2 + (i / 1000000000), i % 1000000000, Unit.second);
  }
  
  public static void toStringZone(int paramInt, StringBuffer paramStringBuffer) {
    if (paramInt == 0) {
      paramStringBuffer.append('Z');
      return;
    } 
    if (paramInt < 0) {
      paramStringBuffer.append('-');
      paramInt = -paramInt;
    } else {
      paramStringBuffer.append('+');
    } 
    append(paramInt / 60, paramStringBuffer, 2);
    paramStringBuffer.append(':');
    append(paramInt % 60, paramStringBuffer, 2);
  }
  
  public Numeric add(Object paramObject, int paramInt) {
    if (paramObject instanceof Duration)
      return add(this, (Duration)paramObject, paramInt); 
    if (paramObject instanceof DateTime && paramInt == -1)
      return sub(this, (DateTime)paramObject); 
    throw new IllegalArgumentException();
  }
  
  public Numeric addReversed(Numeric paramNumeric, int paramInt) {
    if (paramNumeric instanceof Duration && paramInt == 1)
      return add(this, (Duration)paramNumeric, paramInt); 
    throw new IllegalArgumentException();
  }
  
  public DateTime adjustTimezone(int paramInt) {
    TimeZone timeZone;
    DateTime dateTime = new DateTime(this.mask, (GregorianCalendar)this.calendar.clone());
    if (paramInt == 0) {
      timeZone = GMT;
    } else {
      StringBuffer stringBuffer = new StringBuffer("GMT");
      toStringZone(paramInt, stringBuffer);
      timeZone = TimeZone.getTimeZone(stringBuffer.toString());
    } 
    dateTime.calendar.setTimeZone(timeZone);
    if ((dateTime.mask & 0x80) != 0) {
      long l = this.calendar.getTimeInMillis();
      dateTime.calendar.setTimeInMillis(l);
      if ((this.mask & 0x70) == 0) {
        dateTime.calendar.set(11, 0);
        dateTime.calendar.set(12, 0);
        dateTime.calendar.set(13, 0);
        dateTime.nanoSeconds = 0;
      } 
      return dateTime;
    } 
    dateTime.mask |= 0x80;
    return dateTime;
  }
  
  public DateTime cast(int paramInt) {
    int i = this.mask & 0xFFFFFF7F;
    if (paramInt == i)
      return this; 
    DateTime dateTime = new DateTime(paramInt, (GregorianCalendar)this.calendar.clone());
    if (((i ^ 0xFFFFFFFF) & paramInt) != 0 && (i != 14 || paramInt != 126))
      throw new ClassCastException("cannot cast DateTime - missing conponents"); 
    if (isZoneUnspecified()) {
      dateTime.mask &= 0xFFFFFF7F;
    } else {
      dateTime.mask |= 0x80;
    } 
    paramInt = i & (paramInt ^ 0xFFFFFFFF);
    if ((paramInt & 0x70) != 0) {
      dateTime.calendar.clear(11);
      dateTime.calendar.clear(12);
      dateTime.calendar.clear(13);
    } else {
      dateTime.nanoSeconds = this.nanoSeconds;
    } 
    if ((paramInt & 0x2) != 0) {
      dateTime.calendar.clear(1);
      dateTime.calendar.clear(0);
    } 
    if ((paramInt & 0x4) != 0)
      dateTime.calendar.clear(2); 
    if ((paramInt & 0x8) != 0)
      dateTime.calendar.clear(5); 
    return dateTime;
  }
  
  public int compare(Object paramObject) {
    return (paramObject instanceof DateTime) ? compare(this, (DateTime)paramObject) : ((Numeric)paramObject).compareReversed(this);
  }
  
  public int components() {
    return this.mask & 0xFFFFFF7F;
  }
  
  public int getDay() {
    return this.calendar.get(5);
  }
  
  public int getHours() {
    return this.calendar.get(11);
  }
  
  public int getMinutes() {
    return this.calendar.get(12);
  }
  
  public int getMonth() {
    return this.calendar.get(2) + 1;
  }
  
  public int getNanoSecondsOnly() {
    return this.nanoSeconds;
  }
  
  public int getSecondsOnly() {
    return this.calendar.get(13);
  }
  
  public int getWholeSeconds() {
    return this.calendar.get(13);
  }
  
  public int getYear() {
    int j = this.calendar.get(1);
    int i = j;
    if (this.calendar.get(0) == 0)
      i = 1 - j; 
    return i;
  }
  
  public int getZoneMinutes() {
    return this.calendar.getTimeZone().getRawOffset() / 60000;
  }
  
  public boolean isExact() {
    return ((this.mask & 0x70) == 0);
  }
  
  public boolean isZero() {
    throw new Error("DateTime.isZero not meaningful!");
  }
  
  public boolean isZoneUnspecified() {
    return ((this.mask & 0x80) == 0);
  }
  
  public Complex number() {
    throw new Error("number needs to be implemented!");
  }
  
  int parseDate(String paramString, int paramInt1, int paramInt2) {
    if (paramInt1 < 0)
      return paramInt1; 
    int m = paramString.length();
    int k = 0;
    int j = k;
    int i = paramInt1;
    if (paramInt1 < m) {
      j = k;
      i = paramInt1;
      if (paramString.charAt(paramInt1) == '-') {
        i = paramInt1 + 1;
        j = 1;
      } 
    } 
    k = i;
    if ((paramInt2 & 0x2) == 0) {
      if (j == 0)
        return -1; 
      paramInt1 = -1;
      j = k;
    } else {
      k = parseDigits(paramString, k);
      paramInt1 = k >> 16;
      k &= 0xFFFF;
      if (k != i + 4 && (k <= i + 4 || paramString.charAt(i) == '0'))
        return -1; 
      if (j != 0 || paramInt1 == 0) {
        this.calendar.set(0, 0);
        this.calendar.set(1, paramInt1 + 1);
        j = k;
      } else {
        this.calendar.set(1, paramInt1);
        j = k;
      } 
    } 
    i = j;
    if ((paramInt2 & 0xC) != 0) {
      if (j >= m || paramString.charAt(j) != '-')
        return -1; 
      k = j + 1;
      if ((paramInt2 & 0x4) != 0) {
        i = parseDigits(paramString, k);
        int i1 = i >> 16;
        j = i & 0xFFFF;
        if (i1 <= 0 || i1 > 12 || j != k + 2)
          return -1; 
        this.calendar.set(2, i1 - 1);
        i = j;
        if ((paramInt2 & 0x8) != 0) {
          i = i1;
        } else {
          return i;
        } 
      } else {
        i = -1;
        j = k;
      } 
      if (j >= m || paramString.charAt(j) != '-')
        return -1; 
      int n = j + 1;
      k = parseDigits(paramString, n);
      j = k >> 16;
      k &= 0xFFFF;
      if (j > 0 && k == n + 2) {
        if ((paramInt2 & 0x4) == 0) {
          paramInt1 = 31;
        } else {
          if ((paramInt2 & 0x2) == 0)
            paramInt1 = 2000; 
          paramInt1 = daysInMonth(i - 1, paramInt1);
        } 
        if (j <= paramInt1) {
          this.calendar.set(5, j);
          return k;
        } 
      } 
      return -1;
    } 
    return i;
  }
  
  int parseTime(String paramString, int paramInt) {
    if (paramInt < 0)
      return paramInt; 
    int k = paramString.length();
    int i = parseDigits(paramString, paramInt);
    int j = i >> 16;
    i &= 0xFFFF;
    if (j <= 24 && i == paramInt + 2 && i != k && paramString.charAt(i) == ':') {
      paramInt = i + 1;
      i = parseDigits(paramString, paramInt);
      int m = i >> 16;
      i &= 0xFFFF;
      if (m < 60 && i == paramInt + 2 && i != k && paramString.charAt(i) == ':') {
        paramInt = parseDigits(paramString, ++i);
        int n = paramInt >> 16;
        paramInt &= 0xFFFF;
        if (n < 60 && paramInt == i + 2) {
          i = paramInt;
          if (paramInt + 1 < k) {
            i = paramInt;
            if (paramString.charAt(paramInt) == '.') {
              i = paramInt;
              if (Character.digit(paramString.charAt(paramInt + 1), 10) >= 0) {
                i = paramInt + 1;
                int i1 = 0;
                paramInt = 0;
                while (true) {
                  if (i < k) {
                    int i2 = Character.digit(paramString.charAt(i), 10);
                    if (i2 >= 0) {
                      int i3;
                      if (paramInt < 9) {
                        i3 = i1 * 10 + i2;
                      } else {
                        i3 = i1;
                        if (paramInt == 9) {
                          i3 = i1;
                          if (i2 >= 5)
                            i3 = i1 + 1; 
                        } 
                      } 
                      paramInt++;
                      i++;
                      i1 = i3;
                      continue;
                    } 
                  } 
                  while (paramInt < 9) {
                    i1 *= 10;
                    paramInt++;
                  } 
                  this.nanoSeconds = i1;
                  break;
                } 
              } 
            } 
          } 
          if (j == 24 && (m != 0 || n != 0 || this.nanoSeconds != 0))
            return -1; 
          this.calendar.set(11, j);
          this.calendar.set(12, m);
          this.calendar.set(13, n);
          return i;
        } 
      } 
    } 
    return -1;
  }
  
  int parseZone(String paramString, int paramInt) {
    if (paramInt >= 0) {
      int i = parseZoneMinutes(paramString, paramInt);
      if (i == 0)
        return -1; 
      if (i != paramInt) {
        int j = i & 0xFFFF;
        if (i >> 16 == 0) {
          timeZone = GMT;
          this.calendar.setTimeZone(timeZone);
          this.mask |= 0x80;
          return j;
        } 
        TimeZone timeZone = TimeZone.getTimeZone("GMT" + timeZone.substring(paramInt, j));
        this.calendar.setTimeZone(timeZone);
        this.mask |= 0x80;
        return j;
      } 
    } 
    return paramInt;
  }
  
  int parseZoneMinutes(String paramString, int paramInt) {
    // Byte code:
    //   0: iconst_0
    //   1: istore #5
    //   3: aload_1
    //   4: invokevirtual length : ()I
    //   7: istore #4
    //   9: iload_2
    //   10: iload #4
    //   12: if_icmpeq -> 19
    //   15: iload_2
    //   16: ifge -> 23
    //   19: iload_2
    //   20: istore_3
    //   21: iload_3
    //   22: ireturn
    //   23: aload_1
    //   24: iload_2
    //   25: invokevirtual charAt : (I)C
    //   28: istore #8
    //   30: iload #8
    //   32: bipush #90
    //   34: if_icmpne -> 41
    //   37: iload_2
    //   38: iconst_1
    //   39: iadd
    //   40: ireturn
    //   41: iload #8
    //   43: bipush #43
    //   45: if_icmpeq -> 57
    //   48: iload #8
    //   50: bipush #45
    //   52: if_icmpeq -> 57
    //   55: iload_2
    //   56: ireturn
    //   57: iload_2
    //   58: iconst_1
    //   59: iadd
    //   60: istore_2
    //   61: aload_1
    //   62: iload_2
    //   63: invokestatic parseDigits : (Ljava/lang/String;I)I
    //   66: istore #7
    //   68: iload #7
    //   70: bipush #16
    //   72: ishr
    //   73: istore #9
    //   75: iload #5
    //   77: istore_3
    //   78: iload #9
    //   80: bipush #14
    //   82: if_icmpgt -> 21
    //   85: iload #9
    //   87: bipush #60
    //   89: imul
    //   90: istore #6
    //   92: iload #7
    //   94: ldc_w 65535
    //   97: iand
    //   98: istore #7
    //   100: iload #5
    //   102: istore_3
    //   103: iload #7
    //   105: iload_2
    //   106: iconst_2
    //   107: iadd
    //   108: if_icmpne -> 21
    //   111: iload #5
    //   113: istore_3
    //   114: iload #7
    //   116: iload #4
    //   118: if_icmpge -> 21
    //   121: iload #6
    //   123: istore_2
    //   124: iload #7
    //   126: istore #4
    //   128: aload_1
    //   129: iload #7
    //   131: invokevirtual charAt : (I)C
    //   134: bipush #58
    //   136: if_icmpne -> 204
    //   139: iload #7
    //   141: iconst_1
    //   142: iadd
    //   143: istore #7
    //   145: aload_1
    //   146: iload #7
    //   148: invokestatic parseDigits : (Ljava/lang/String;I)I
    //   151: istore_2
    //   152: iload_2
    //   153: ldc_w 65535
    //   156: iand
    //   157: istore #4
    //   159: iload_2
    //   160: bipush #16
    //   162: ishr
    //   163: istore_2
    //   164: iload_2
    //   165: ifle -> 187
    //   168: iload #5
    //   170: istore_3
    //   171: iload_2
    //   172: bipush #60
    //   174: if_icmpge -> 21
    //   177: iload #5
    //   179: istore_3
    //   180: iload #9
    //   182: bipush #14
    //   184: if_icmpeq -> 21
    //   187: iload #6
    //   189: iload_2
    //   190: iadd
    //   191: istore_2
    //   192: iload #5
    //   194: istore_3
    //   195: iload #4
    //   197: iload #7
    //   199: iconst_2
    //   200: iadd
    //   201: if_icmpne -> 21
    //   204: iload #5
    //   206: istore_3
    //   207: iload_2
    //   208: sipush #840
    //   211: if_icmpgt -> 21
    //   214: iload_2
    //   215: istore_3
    //   216: iload #8
    //   218: bipush #45
    //   220: if_icmpne -> 226
    //   223: iload_2
    //   224: ineg
    //   225: istore_3
    //   226: iload_3
    //   227: bipush #16
    //   229: ishl
    //   230: iload #4
    //   232: ior
    //   233: ireturn
  }
  
  public void setTimeZone(TimeZone paramTimeZone) {
    this.calendar.setTimeZone(paramTimeZone);
  }
  
  public String toString() {
    StringBuffer stringBuffer = new StringBuffer();
    toString(stringBuffer);
    return stringBuffer.toString();
  }
  
  public void toString(StringBuffer paramStringBuffer) {
    boolean bool1;
    boolean bool2 = true;
    int i = components();
    if ((i & 0xE) != 0) {
      bool1 = true;
    } else {
      bool1 = false;
    } 
    if ((i & 0x70) == 0)
      bool2 = false; 
    if (bool1) {
      toStringDate(paramStringBuffer);
      if (bool2)
        paramStringBuffer.append('T'); 
    } 
    if (bool2)
      toStringTime(paramStringBuffer); 
    toStringZone(paramStringBuffer);
  }
  
  public void toStringDate(StringBuffer paramStringBuffer) {
    int i = components();
    if ((i & 0x2) != 0) {
      int k = this.calendar.get(1);
      int j = k;
      if (this.calendar.get(0) == 0) {
        j = --k;
        if (k != 0) {
          paramStringBuffer.append('-');
          j = k;
        } 
      } 
      append(j, paramStringBuffer, 4);
    } else {
      paramStringBuffer.append('-');
    } 
    if ((i & 0xC) != 0) {
      paramStringBuffer.append('-');
      if ((i & 0x4) != 0)
        append(getMonth(), paramStringBuffer, 2); 
      if ((i & 0x8) != 0) {
        paramStringBuffer.append('-');
        append(getDay(), paramStringBuffer, 2);
      } 
    } 
  }
  
  public void toStringTime(StringBuffer paramStringBuffer) {
    append(getHours(), paramStringBuffer, 2);
    paramStringBuffer.append(':');
    append(getMinutes(), paramStringBuffer, 2);
    paramStringBuffer.append(':');
    append(getWholeSeconds(), paramStringBuffer, 2);
    Duration.appendNanoSeconds(this.nanoSeconds, paramStringBuffer);
  }
  
  public void toStringZone(StringBuffer paramStringBuffer) {
    if (isZoneUnspecified())
      return; 
    toStringZone(getZoneMinutes(), paramStringBuffer);
  }
  
  public Unit unit() {
    return this.unit;
  }
  
  public DateTime withZoneUnspecified() {
    if (isZoneUnspecified())
      return this; 
    DateTime dateTime = new DateTime(this.mask, (GregorianCalendar)this.calendar.clone());
    dateTime.calendar.setTimeZone(TimeZone.getDefault());
    dateTime.mask &= 0xFFFFFF7F;
    return dateTime;
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/gnu/math/DateTime.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */