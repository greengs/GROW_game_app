package gnu.text;

import gnu.lists.Consumer;
import java.text.FieldPosition;
import java.text.NumberFormat;
import java.text.ParsePosition;

public class EnglishIntegerFormat extends NumberFormat {
  private static EnglishIntegerFormat cardinalEnglish;
  
  public static final String[] ones = new String[] { 
      null, "one", "two", "three", "four", "five", "six", "seven", "eight", "nine", 
      "ten", "eleven", "twelve", "thirteen", "fourteen", "fifteen", "sixteen", "seventeen", "eighteen", "nineteen" };
  
  public static final String[] onesth = new String[] { 
      null, "first", "second", "third", "fourth", "fifth", "sixth", "seventh", "eighth", "ninth", 
      "tenth", "eleventh", "twelveth", "thirteenth", "fourteenth", "fifteenth", "sixteenth", "seventeenth", "eighteenth", "nineteenth" };
  
  private static EnglishIntegerFormat ordinalEnglish;
  
  public static final String[] power1000s;
  
  public static final String[] tens = new String[] { null, null, "twenty", "thirty", "forty", "fifty", "sixty", "seventy", "eighty", "ninety" };
  
  public static final String[] tensth = new String[] { null, null, "twentieth", "thirtieth", "fortieth", "fiftieth", "sixtieth", "seventieth", "eightieth", "ninetieth" };
  
  public boolean ordinal;
  
  static {
    power1000s = new String[] { 
        null, " thousand", " million", " billion", " trillion", " quadrillion", " quintillion", " sextillion", " septillion", " octillion", 
        " nonillion", " decillion", " undecillion", " duodecillion", " tredecillion", " quattuordecillion", " quindecillion", " sexdecillion", " septendecillion", " octodecillion", 
        " novemdecillion", " vigintillion" };
  }
  
  public EnglishIntegerFormat(boolean paramBoolean) {
    this.ordinal = paramBoolean;
  }
  
  public static EnglishIntegerFormat getInstance(boolean paramBoolean) {
    if (paramBoolean) {
      if (ordinalEnglish == null)
        ordinalEnglish = new EnglishIntegerFormat(true); 
      return ordinalEnglish;
    } 
    if (cardinalEnglish == null)
      cardinalEnglish = new EnglishIntegerFormat(false); 
    return cardinalEnglish;
  }
  
  public StringBuffer format(double paramDouble, StringBuffer paramStringBuffer, FieldPosition paramFieldPosition) {
    long l = (long)paramDouble;
    if (l == paramDouble)
      return format(l, paramStringBuffer, paramFieldPosition); 
    paramStringBuffer.append(Double.toString(paramDouble));
    return paramStringBuffer;
  }
  
  public StringBuffer format(long paramLong, StringBuffer paramStringBuffer, FieldPosition paramFieldPosition) {
    if (paramLong == 0L) {
      String str;
      if (this.ordinal) {
        str = "zeroth";
      } else {
        str = "zero";
      } 
      paramStringBuffer.append(str);
    } else {
      long l = paramLong;
      if (paramLong < 0L) {
        paramStringBuffer.append("minus ");
        l = -paramLong;
      } 
      format(paramStringBuffer, l, 0, this.ordinal);
    } 
    if (paramFieldPosition != null);
    return paramStringBuffer;
  }
  
  void format(StringBuffer paramStringBuffer, long paramLong, int paramInt, boolean paramBoolean) {
    long l = paramLong;
    if (paramLong >= 1000L) {
      format(paramStringBuffer, paramLong / 1000L, paramInt + 1, false);
      paramLong %= 1000L;
      if (paramLong > 0L) {
        paramStringBuffer.append(", ");
        l = paramLong;
      } else {
        l = paramLong;
        if (paramBoolean) {
          paramStringBuffer.append("th");
          l = paramLong;
        } 
      } 
    } 
    if (l > 0L) {
      int i = (int)l;
      if (paramBoolean && paramInt == 0) {
        paramBoolean = true;
      } else {
        paramBoolean = false;
      } 
      format999(paramStringBuffer, i, paramBoolean);
      if (paramInt >= power1000s.length) {
        paramStringBuffer.append(" times ten to the ");
        format(paramStringBuffer, (paramInt * 3), 0, true);
        paramStringBuffer.append(" power");
        return;
      } 
    } else {
      return;
    } 
    if (paramInt > 0) {
      paramStringBuffer.append(power1000s[paramInt]);
      return;
    } 
  }
  
  void format999(StringBuffer paramStringBuffer, int paramInt, boolean paramBoolean) {
    int i = paramInt;
    if (paramInt >= 100) {
      i = paramInt / 100;
      paramInt %= 100;
      if (i > 1) {
        paramStringBuffer.append(ones[i]);
        paramStringBuffer.append(' ');
      } 
      paramStringBuffer.append("hundred");
      if (paramInt > 0) {
        paramStringBuffer.append(' ');
        i = paramInt;
      } else {
        i = paramInt;
        if (paramBoolean) {
          paramStringBuffer.append("th");
          i = paramInt;
        } 
      } 
    } 
    paramInt = i;
    if (i >= 20) {
      String[] arrayOfString;
      paramInt = i / 10;
      i %= 10;
      if (paramBoolean && i == 0) {
        arrayOfString = tensth;
      } else {
        arrayOfString = tens;
      } 
      paramStringBuffer.append(arrayOfString[paramInt]);
      paramInt = i;
      if (i > 0) {
        paramStringBuffer.append('-');
        paramInt = i;
      } 
    } 
    if (paramInt > 0) {
      String[] arrayOfString;
      if (paramBoolean) {
        arrayOfString = onesth;
      } else {
        arrayOfString = ones;
      } 
      paramStringBuffer.append(arrayOfString[paramInt]);
    } 
  }
  
  public Number parse(String paramString, ParsePosition paramParsePosition) {
    throw new Error("EnglishIntegerFormat.parseObject - not implemented");
  }
  
  public void writeBoolean(boolean paramBoolean, Consumer paramConsumer) {
    long l;
    if (paramBoolean) {
      l = 1L;
    } else {
      l = 0L;
    } 
    writeLong(l, paramConsumer);
  }
  
  public void writeInt(int paramInt, Consumer paramConsumer) {
    writeLong(paramInt, paramConsumer);
  }
  
  public void writeLong(long paramLong, Consumer paramConsumer) {
    StringBuffer stringBuffer = new StringBuffer();
    format(paramLong, stringBuffer, (FieldPosition)null);
    paramConsumer.write(stringBuffer, 0, stringBuffer.length());
  }
  
  public void writeObject(Object paramObject, Consumer paramConsumer) {
    writeLong(((Number)paramObject).longValue(), paramConsumer);
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/gnu/text/EnglishIntegerFormat.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */