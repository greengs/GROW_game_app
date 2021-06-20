package gnu.kawa.functions;

import gnu.math.RealNum;
import gnu.text.EnglishIntegerFormat;
import gnu.text.IntegerFormat;
import gnu.text.RomanIntegerFormat;
import java.text.Format;

public class IntegerFormat extends IntegerFormat {
  private static IntegerFormat plainDecimalFormat;
  
  public static IntegerFormat getInstance() {
    if (plainDecimalFormat == null)
      plainDecimalFormat = new IntegerFormat(); 
    return plainDecimalFormat;
  }
  
  public static Format getInstance(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6) {
    boolean bool = true;
    int i = paramInt1;
    if (paramInt1 == -1073741824) {
      if (paramInt3 == -1073741824 && paramInt3 == -1073741824 && paramInt4 == -1073741824 && paramInt5 == -1073741824) {
        if ((paramInt6 & 0x1) == 0)
          bool = false; 
        return (Format)(((paramInt6 & 0x2) != 0) ? RomanIntegerFormat.getInstance(bool) : EnglishIntegerFormat.getInstance(bool));
      } 
      i = 10;
    } 
    paramInt1 = paramInt2;
    if (paramInt2 == -1073741824)
      paramInt1 = 1; 
    paramInt2 = paramInt3;
    if (paramInt3 == -1073741824)
      paramInt2 = 32; 
    paramInt3 = paramInt4;
    if (paramInt4 == -1073741824)
      paramInt3 = 44; 
    paramInt4 = paramInt5;
    if (paramInt5 == -1073741824)
      paramInt4 = 3; 
    if (i == 10 && paramInt1 == 1 && paramInt2 == 32 && paramInt3 == 44 && paramInt4 == 3 && paramInt6 == 0)
      return (Format)getInstance(); 
    IntegerFormat integerFormat = new IntegerFormat();
    integerFormat.base = i;
    integerFormat.minWidth = paramInt1;
    integerFormat.padChar = paramInt2;
    integerFormat.commaChar = paramInt3;
    integerFormat.commaInterval = paramInt4;
    integerFormat.flags = paramInt6;
    return (Format)integerFormat;
  }
  
  public String convertToIntegerString(Object paramObject, int paramInt) {
    return (paramObject instanceof RealNum) ? ((RealNum)paramObject).toExactInt(4).toString(paramInt) : super.convertToIntegerString(paramObject, paramInt);
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/gnu/kawa/functions/IntegerFormat.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */