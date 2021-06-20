package gnu.text;

import java.text.FieldPosition;
import java.text.NumberFormat;
import java.text.ParsePosition;

public class RomanIntegerFormat extends NumberFormat {
  static final String codes = "IVXLCDM";
  
  private static RomanIntegerFormat newRoman;
  
  private static RomanIntegerFormat oldRoman;
  
  public boolean oldStyle;
  
  public RomanIntegerFormat() {}
  
  public RomanIntegerFormat(boolean paramBoolean) {
    this.oldStyle = paramBoolean;
  }
  
  public static String format(int paramInt) {
    return format(paramInt, false);
  }
  
  public static String format(int paramInt, boolean paramBoolean) {
    if (paramInt <= 0 || paramInt >= 4999)
      return Integer.toString(paramInt); 
    StringBuffer stringBuffer = new StringBuffer(20);
    int i = 3;
    int j = 1000;
    label25: while (i >= 0) {
      int k = paramInt / j;
      int m = paramInt - k * j;
      if (k == 0)
        continue; 
      if (!paramBoolean && (k == 4 || k == 9)) {
        stringBuffer.append("IVXLCDM".charAt(i * 2));
        stringBuffer.append("IVXLCDM".charAt(i * 2 + (k + 1) / 5));
        continue;
      } 
      paramInt = k;
      if (k >= 5) {
        stringBuffer.append("IVXLCDM".charAt(i * 2 + 1));
        paramInt = k - 5;
      } 
      while (true) {
        if (--paramInt >= 0) {
          stringBuffer.append("IVXLCDM".charAt(i * 2));
          continue;
        } 
        j /= 10;
        i--;
        paramInt = m;
        continue label25;
      } 
    } 
    return stringBuffer.toString();
  }
  
  public static RomanIntegerFormat getInstance(boolean paramBoolean) {
    if (paramBoolean) {
      if (oldRoman == null)
        oldRoman = new RomanIntegerFormat(true); 
      return oldRoman;
    } 
    if (newRoman == null)
      newRoman = new RomanIntegerFormat(false); 
    return newRoman;
  }
  
  public StringBuffer format(double paramDouble, StringBuffer paramStringBuffer, FieldPosition paramFieldPosition) {
    long l = (long)paramDouble;
    if (l == paramDouble)
      return format(l, paramStringBuffer, paramFieldPosition); 
    paramStringBuffer.append(Double.toString(paramDouble));
    return paramStringBuffer;
  }
  
  public StringBuffer format(long paramLong, StringBuffer paramStringBuffer, FieldPosition paramFieldPosition) {
    // Byte code:
    //   0: lload_1
    //   1: lconst_0
    //   2: lcmp
    //   3: ifle -> 87
    //   6: aload_0
    //   7: getfield oldStyle : Z
    //   10: ifeq -> 79
    //   13: sipush #4999
    //   16: istore #5
    //   18: lload_1
    //   19: iload #5
    //   21: i2l
    //   22: lcmp
    //   23: ifge -> 87
    //   26: lload_1
    //   27: l2i
    //   28: aload_0
    //   29: getfield oldStyle : Z
    //   32: invokestatic format : (IZ)Ljava/lang/String;
    //   35: astore #7
    //   37: aload #4
    //   39: ifnull -> 125
    //   42: lconst_1
    //   43: lstore_1
    //   44: aload #7
    //   46: invokevirtual length : ()I
    //   49: istore #6
    //   51: iload #6
    //   53: istore #5
    //   55: iload #5
    //   57: iconst_1
    //   58: isub
    //   59: istore #5
    //   61: iload #5
    //   63: ifle -> 96
    //   66: ldc2_w 10
    //   69: lload_1
    //   70: lmul
    //   71: ldc2_w 9
    //   74: ladd
    //   75: lstore_1
    //   76: goto -> 55
    //   79: sipush #3999
    //   82: istore #5
    //   84: goto -> 18
    //   87: lload_1
    //   88: invokestatic toString : (J)Ljava/lang/String;
    //   91: astore #7
    //   93: goto -> 37
    //   96: new java/lang/StringBuffer
    //   99: dup
    //   100: iload #6
    //   102: invokespecial <init> : (I)V
    //   105: astore #8
    //   107: new java/text/DecimalFormat
    //   110: dup
    //   111: ldc '0'
    //   113: invokespecial <init> : (Ljava/lang/String;)V
    //   116: lload_1
    //   117: aload #8
    //   119: aload #4
    //   121: invokevirtual format : (JLjava/lang/StringBuffer;Ljava/text/FieldPosition;)Ljava/lang/StringBuffer;
    //   124: pop
    //   125: aload_3
    //   126: aload #7
    //   128: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   131: pop
    //   132: aload_3
    //   133: areturn
  }
  
  public Number parse(String paramString, ParsePosition paramParsePosition) {
    throw new Error("RomanIntegerFormat.parseObject - not implemented");
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/gnu/text/RomanIntegerFormat.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */