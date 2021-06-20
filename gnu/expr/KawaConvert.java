package gnu.expr;

import gnu.lists.Convert;
import gnu.math.DFloNum;
import gnu.math.IntNum;
import gnu.text.Char;

public class KawaConvert extends Convert {
  public static Convert instance = new KawaConvert();
  
  public static Convert getInstance() {
    return instance;
  }
  
  public static void setInstance(Convert paramConvert) {
    instance = paramConvert;
  }
  
  public Object byteToObject(byte paramByte) {
    return IntNum.make(paramByte);
  }
  
  public Object byteToObjectUnsigned(byte paramByte) {
    return IntNum.make(paramByte & 0xFF);
  }
  
  public Object charToObject(char paramChar) {
    return Char.make(paramChar);
  }
  
  public Object doubleToObject(double paramDouble) {
    return DFloNum.make(paramDouble);
  }
  
  public Object floatToObject(float paramFloat) {
    return DFloNum.make(paramFloat);
  }
  
  public Object intToObject(int paramInt) {
    return IntNum.make(paramInt);
  }
  
  public Object intToObjectUnsigned(int paramInt) {
    return IntNum.make(paramInt & 0xFFFFFFFFL);
  }
  
  public Object longToObject(long paramLong) {
    return IntNum.make(paramLong);
  }
  
  public Object longToObjectUnsigned(long paramLong) {
    return IntNum.makeU(paramLong);
  }
  
  public char objectToChar(Object paramObject) {
    return ((Char)paramObject).charValue();
  }
  
  public Object shortToObject(short paramShort) {
    return IntNum.make(paramShort);
  }
  
  public Object shortToObjectUnsigned(short paramShort) {
    return IntNum.make(0xFFFF & paramShort);
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/gnu/expr/KawaConvert.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */