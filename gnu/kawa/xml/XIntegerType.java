package gnu.kawa.xml;

import gnu.bytecode.ClassType;
import gnu.bytecode.Type;
import gnu.kawa.functions.Arithmetic;
import gnu.math.IntNum;
import gnu.math.RealNum;
import java.math.BigDecimal;

public class XIntegerType extends XDataType {
  public static final XIntegerType byteType;
  
  public static final XIntegerType intType;
  
  public static final XIntegerType integerType;
  
  public static final XIntegerType longType;
  
  public static final XIntegerType negativeIntegerType;
  
  public static final XIntegerType nonNegativeIntegerType;
  
  public static final XIntegerType nonPositiveIntegerType;
  
  public static final XIntegerType positiveIntegerType;
  
  public static final XIntegerType shortType;
  
  static ClassType typeIntNum = ClassType.make("gnu.math.IntNum");
  
  public static final XIntegerType unsignedByteType;
  
  public static final XIntegerType unsignedIntType;
  
  public static final XIntegerType unsignedLongType;
  
  public static final XIntegerType unsignedShortType;
  
  boolean isUnsignedType;
  
  public final IntNum maxValue;
  
  public final IntNum minValue;
  
  static {
    integerType = new XIntegerType("integer", decimalType, 5, null, null);
    longType = new XIntegerType("long", integerType, 8, IntNum.make(Long.MIN_VALUE), IntNum.make(Long.MAX_VALUE));
    intType = new XIntegerType("int", longType, 9, IntNum.make(-2147483648), IntNum.make(2147483647));
    shortType = new XIntegerType("short", intType, 10, IntNum.make(-32768), IntNum.make(32767));
    byteType = new XIntegerType("byte", shortType, 11, IntNum.make(-128), IntNum.make(127));
    nonPositiveIntegerType = new XIntegerType("nonPositiveInteger", integerType, 6, null, IntNum.zero());
    negativeIntegerType = new XIntegerType("negativeInteger", nonPositiveIntegerType, 7, null, IntNum.minusOne());
    nonNegativeIntegerType = new XIntegerType("nonNegativeInteger", integerType, 12, IntNum.zero(), null);
    unsignedLongType = new XIntegerType("unsignedLong", nonNegativeIntegerType, 13, IntNum.zero(), IntNum.valueOf("18446744073709551615"));
    unsignedIntType = new XIntegerType("unsignedInt", unsignedLongType, 14, IntNum.zero(), IntNum.make(4294967295L));
    unsignedShortType = new XIntegerType("unsignedShort", unsignedIntType, 15, IntNum.zero(), IntNum.make(65535));
    unsignedByteType = new XIntegerType("unsignedByte", unsignedShortType, 16, IntNum.zero(), IntNum.make(255));
    positiveIntegerType = new XIntegerType("positiveInteger", nonNegativeIntegerType, 17, IntNum.one(), null);
  }
  
  public XIntegerType(Object paramObject, XDataType paramXDataType, int paramInt, IntNum paramIntNum1, IntNum paramIntNum2) {
    super(paramObject, (Type)typeIntNum, paramInt);
    this.minValue = paramIntNum1;
    this.maxValue = paramIntNum2;
    this.baseType = paramXDataType;
  }
  
  public XIntegerType(String paramString, XDataType paramXDataType, int paramInt, IntNum paramIntNum1, IntNum paramIntNum2) {
    this(paramString, paramXDataType, paramInt, paramIntNum1, paramIntNum2);
    this.isUnsignedType = paramString.startsWith("unsigned");
  }
  
  public Object cast(Object paramObject) {
    if (paramObject instanceof Boolean) {
      if (((Boolean)paramObject).booleanValue()) {
        paramObject = IntNum.one();
        return valueOf((IntNum)paramObject);
      } 
      paramObject = IntNum.zero();
      return valueOf((IntNum)paramObject);
    } 
    return (paramObject instanceof IntNum) ? valueOf((IntNum)paramObject) : ((paramObject instanceof BigDecimal) ? valueOf(Arithmetic.asIntNum((BigDecimal)paramObject)) : ((paramObject instanceof RealNum) ? valueOf(((RealNum)paramObject).toExactInt(3)) : ((paramObject instanceof Number) ? valueOf(RealNum.toExactInt(((Number)paramObject).doubleValue(), 3)) : super.cast(paramObject))));
  }
  
  public Object coerceFromObject(Object paramObject) {
    return valueOf((IntNum)paramObject);
  }
  
  public boolean isInstance(Object paramObject) {
    if (paramObject instanceof IntNum) {
      if (this == integerType)
        return true; 
      if (paramObject instanceof XInteger) {
        paramObject = ((XInteger)paramObject).getIntegerType();
      } else {
        paramObject = integerType;
      } 
      while (true) {
        if (paramObject != null) {
          if (paramObject == this)
            return true; 
          paramObject = ((XDataType)paramObject).baseType;
          continue;
        } 
        return false;
      } 
    } 
    return false;
  }
  
  public boolean isUnsignedType() {
    return this.isUnsignedType;
  }
  
  public IntNum valueOf(IntNum paramIntNum) {
    IntNum intNum = paramIntNum;
    if (this != integerType) {
      if ((this.minValue != null && IntNum.compare(paramIntNum, this.minValue) < 0) || (this.maxValue != null && IntNum.compare(paramIntNum, this.maxValue) > 0))
        throw new ClassCastException("cannot cast " + paramIntNum + " to " + this.name); 
      intNum = new XInteger(paramIntNum, this);
    } 
    return intNum;
  }
  
  public IntNum valueOf(String paramString, int paramInt) {
    return valueOf(IntNum.valueOf(paramString.trim(), paramInt));
  }
  
  public Object valueOf(String paramString) {
    return valueOf(IntNum.valueOf(paramString.trim(), 10));
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/gnu/kawa/xml/XIntegerType.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */