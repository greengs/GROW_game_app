package gnu.lists;

public class Convert {
  public static Convert instance = new Convert();
  
  public static Convert getInstance() {
    return instance;
  }
  
  public static double parseDouble(String paramString) {
    return Double.parseDouble(paramString);
  }
  
  public static void setInstance(Convert paramConvert) {
    instance = paramConvert;
  }
  
  public static boolean toBoolean(Object paramObject) {
    return instance.objectToBoolean(paramObject);
  }
  
  public static byte toByte(Object paramObject) {
    return instance.objectToByte(paramObject);
  }
  
  public static byte toByteUnsigned(Object paramObject) {
    return instance.objectToByteUnsigned(paramObject);
  }
  
  public static char toChar(Object paramObject) {
    return instance.objectToChar(paramObject);
  }
  
  public static double toDouble(Object paramObject) {
    return instance.objectToDouble(paramObject);
  }
  
  public static float toFloat(Object paramObject) {
    return instance.objectToFloat(paramObject);
  }
  
  public static int toInt(Object paramObject) {
    return instance.objectToInt(paramObject);
  }
  
  public static int toIntUnsigned(Object paramObject) {
    return instance.objectToIntUnsigned(paramObject);
  }
  
  public static long toLong(Object paramObject) {
    return instance.objectToLong(paramObject);
  }
  
  public static long toLongUnsigned(Object paramObject) {
    return instance.objectToLongUnsigned(paramObject);
  }
  
  public static Object toObject(byte paramByte) {
    return instance.byteToObject(paramByte);
  }
  
  public static Object toObject(char paramChar) {
    return instance.charToObject(paramChar);
  }
  
  public static Object toObject(double paramDouble) {
    return instance.doubleToObject(paramDouble);
  }
  
  public static Object toObject(float paramFloat) {
    return instance.floatToObject(paramFloat);
  }
  
  public static Object toObject(int paramInt) {
    return instance.intToObject(paramInt);
  }
  
  public static Object toObject(long paramLong) {
    return instance.longToObject(paramLong);
  }
  
  public static Object toObject(short paramShort) {
    return instance.shortToObject(paramShort);
  }
  
  public static Object toObject(boolean paramBoolean) {
    return instance.booleanToObject(paramBoolean);
  }
  
  public static Object toObjectUnsigned(byte paramByte) {
    return instance.byteToObjectUnsigned(paramByte);
  }
  
  public static Object toObjectUnsigned(int paramInt) {
    return instance.intToObjectUnsigned(paramInt);
  }
  
  public static Object toObjectUnsigned(long paramLong) {
    return instance.longToObjectUnsigned(paramLong);
  }
  
  public static Object toObjectUnsigned(short paramShort) {
    return instance.shortToObjectUnsigned(paramShort);
  }
  
  public static short toShort(Object paramObject) {
    return instance.objectToShort(paramObject);
  }
  
  public static short toShortUnsigned(Object paramObject) {
    return instance.objectToShortUnsigned(paramObject);
  }
  
  public Object booleanToObject(boolean paramBoolean) {
    return paramBoolean ? Boolean.TRUE : Boolean.FALSE;
  }
  
  public Object byteToObject(byte paramByte) {
    return new Byte(paramByte);
  }
  
  public Object byteToObjectUnsigned(byte paramByte) {
    return new Integer(paramByte & 0xFF);
  }
  
  public Object charToObject(char paramChar) {
    return new Character(paramChar);
  }
  
  public Object doubleToObject(double paramDouble) {
    return new Double(paramDouble);
  }
  
  public Object floatToObject(float paramFloat) {
    return new Float(paramFloat);
  }
  
  public Object intToObject(int paramInt) {
    return new Integer(paramInt);
  }
  
  public Object intToObjectUnsigned(int paramInt) {
    return (paramInt >= 0) ? new Integer(paramInt) : new Long(paramInt & 0xFFFFFFFFL);
  }
  
  public Object longToObject(long paramLong) {
    return new Long(paramLong);
  }
  
  public Object longToObjectUnsigned(long paramLong) {
    return new Long(paramLong);
  }
  
  public boolean objectToBoolean(Object paramObject) {
    return (!(paramObject instanceof Boolean) || ((Boolean)paramObject).booleanValue());
  }
  
  public byte objectToByte(Object paramObject) {
    return ((Number)paramObject).byteValue();
  }
  
  public byte objectToByteUnsigned(Object paramObject) {
    return ((Number)paramObject).byteValue();
  }
  
  public char objectToChar(Object paramObject) {
    return ((Character)paramObject).charValue();
  }
  
  public double objectToDouble(Object paramObject) {
    return ((Number)paramObject).doubleValue();
  }
  
  public float objectToFloat(Object paramObject) {
    return ((Number)paramObject).floatValue();
  }
  
  public int objectToInt(Object paramObject) {
    return ((Number)paramObject).intValue();
  }
  
  public int objectToIntUnsigned(Object paramObject) {
    return ((Number)paramObject).intValue();
  }
  
  public long objectToLong(Object paramObject) {
    return ((Number)paramObject).longValue();
  }
  
  public long objectToLongUnsigned(Object paramObject) {
    return ((Number)paramObject).longValue();
  }
  
  public short objectToShort(Object paramObject) {
    return ((Number)paramObject).shortValue();
  }
  
  public short objectToShortUnsigned(Object paramObject) {
    return ((Number)paramObject).shortValue();
  }
  
  public Object shortToObject(short paramShort) {
    return new Short(paramShort);
  }
  
  public Object shortToObjectUnsigned(short paramShort) {
    return new Integer(0xFFFF & paramShort);
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/gnu/lists/Convert.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */