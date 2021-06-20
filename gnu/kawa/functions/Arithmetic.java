package gnu.kawa.functions;

import gnu.bytecode.ClassType;
import gnu.bytecode.Type;
import gnu.kawa.lispexpr.LangObjType;
import gnu.kawa.lispexpr.LangPrimType;
import gnu.math.DFloNum;
import gnu.math.IntNum;
import gnu.math.Numeric;
import gnu.math.RatNum;
import java.math.BigDecimal;
import java.math.BigInteger;

public class Arithmetic {
  public static final int BIGDECIMAL_CODE = 5;
  
  public static final int BIGINTEGER_CODE = 3;
  
  public static final int DOUBLE_CODE = 8;
  
  public static final int FLOAT_CODE = 7;
  
  public static final int FLONUM_CODE = 9;
  
  public static final int INTNUM_CODE = 4;
  
  public static final int INT_CODE = 1;
  
  public static final int LONG_CODE = 2;
  
  public static final int NUMERIC_CODE = 11;
  
  public static final int RATNUM_CODE = 6;
  
  public static final int REALNUM_CODE = 10;
  
  static LangObjType typeDFloNum = LangObjType.dflonumType;
  
  static LangObjType typeIntNum;
  
  static ClassType typeNumber;
  
  static ClassType typeNumeric;
  
  static LangObjType typeRatNum = LangObjType.rationalType;
  
  static LangObjType typeRealNum = LangObjType.realType;
  
  static {
    typeNumber = ClassType.make("java.lang.Number");
    typeNumeric = ClassType.make("gnu.math.Numeric");
    typeIntNum = LangObjType.integerType;
  }
  
  public static BigDecimal asBigDecimal(Object paramObject) {
    return (paramObject instanceof BigDecimal) ? (BigDecimal)paramObject : ((paramObject instanceof BigInteger) ? new BigDecimal((BigInteger)paramObject) : ((paramObject instanceof Long || paramObject instanceof Integer || paramObject instanceof Short || paramObject instanceof Byte) ? BigDecimal.valueOf(((Number)paramObject).longValue()) : new BigDecimal(paramObject.toString())));
  }
  
  public static BigInteger asBigInteger(Object paramObject) {
    return (paramObject instanceof BigInteger) ? (BigInteger)paramObject : ((paramObject instanceof IntNum) ? new BigInteger(paramObject.toString()) : BigInteger.valueOf(((Number)paramObject).longValue()));
  }
  
  public static double asDouble(Object paramObject) {
    return ((Number)paramObject).doubleValue();
  }
  
  public static float asFloat(Object paramObject) {
    return ((Number)paramObject).floatValue();
  }
  
  public static int asInt(Object paramObject) {
    return ((Number)paramObject).intValue();
  }
  
  public static IntNum asIntNum(Object paramObject) {
    return (paramObject instanceof IntNum) ? (IntNum)paramObject : ((paramObject instanceof BigInteger) ? IntNum.valueOf(paramObject.toString(), 10) : ((paramObject instanceof BigDecimal) ? asIntNum((BigDecimal)paramObject) : IntNum.make(((Number)paramObject).longValue())));
  }
  
  public static IntNum asIntNum(BigDecimal paramBigDecimal) {
    return IntNum.valueOf(paramBigDecimal.toBigInteger().toString(), 10);
  }
  
  public static IntNum asIntNum(BigInteger paramBigInteger) {
    return IntNum.valueOf(paramBigInteger.toString(), 10);
  }
  
  public static long asLong(Object paramObject) {
    return ((Number)paramObject).longValue();
  }
  
  public static Numeric asNumeric(Object paramObject) {
    Numeric numeric = Numeric.asNumericOrNull(paramObject);
    return (numeric != null) ? numeric : (Numeric)paramObject;
  }
  
  public static RatNum asRatNum(Object paramObject) {
    return (RatNum)((paramObject instanceof RatNum) ? paramObject : ((paramObject instanceof BigInteger) ? IntNum.valueOf(paramObject.toString(), 10) : ((paramObject instanceof BigDecimal) ? RatNum.valueOf((BigDecimal)paramObject) : IntNum.make(((Number)paramObject).longValue()))));
  }
  
  public static int classifyType(Type paramType) {
    byte b = 8;
    if (paramType instanceof gnu.bytecode.PrimType) {
      char c = paramType.getSignature().charAt(0);
      return (c == 'V' || c == 'Z' || c == 'C') ? 0 : ((c != 'D') ? ((c == 'F') ? 7 : ((c == 'J') ? 2 : 1)) : b);
    } 
    String str = paramType.getName();
    return paramType.isSubtype((Type)typeIntNum) ? 4 : (paramType.isSubtype((Type)typeRatNum) ? 6 : (paramType.isSubtype((Type)typeDFloNum) ? 9 : (!"java.lang.Double".equals(str) ? ("java.lang.Float".equals(str) ? 7 : ("java.lang.Long".equals(str) ? 2 : (("java.lang.Integer".equals(str) || "java.lang.Short".equals(str) || "java.lang.Byte".equals(str)) ? 1 : ("java.math.BigInteger".equals(str) ? 3 : ("java.math.BigDecimal".equals(str) ? 5 : (paramType.isSubtype((Type)typeRealNum) ? 10 : (paramType.isSubtype((Type)typeNumeric) ? 11 : 0))))))) : b)));
  }
  
  public static int classifyValue(Object paramObject) {
    byte b2 = -1;
    if (paramObject instanceof Numeric)
      return (paramObject instanceof IntNum) ? 4 : ((paramObject instanceof RatNum) ? 6 : ((paramObject instanceof DFloNum) ? 9 : ((paramObject instanceof gnu.math.RealNum) ? 10 : 11))); 
    byte b1 = b2;
    if (paramObject instanceof Number) {
      if (paramObject instanceof Integer || paramObject instanceof Short || paramObject instanceof Byte)
        return 1; 
      if (paramObject instanceof Long)
        return 2; 
      if (paramObject instanceof Float)
        return 7; 
      if (paramObject instanceof Double)
        return 8; 
      if (paramObject instanceof BigInteger)
        return 3; 
      b1 = b2;
      if (paramObject instanceof BigDecimal)
        return 5; 
    } 
    return b1;
  }
  
  public static Object convert(Object paramObject, int paramInt) {
    Object object;
    switch (paramInt) {
      default:
        return paramObject;
      case 1:
        object = paramObject;
        return !(paramObject instanceof Integer) ? Integer.valueOf(((Number)paramObject).intValue()) : object;
      case 2:
        object = paramObject;
        return !(paramObject instanceof Long) ? Long.valueOf(((Number)paramObject).longValue()) : object;
      case 3:
        return asBigInteger(paramObject);
      case 4:
        return asIntNum(paramObject);
      case 5:
        return asBigDecimal(paramObject);
      case 6:
        return asRatNum(paramObject);
      case 7:
        object = paramObject;
        return !(paramObject instanceof Float) ? Float.valueOf(asFloat(paramObject)) : object;
      case 8:
        object = paramObject;
        return !(paramObject instanceof Double) ? Double.valueOf(asDouble(paramObject)) : object;
      case 9:
        object = paramObject;
        return !(paramObject instanceof DFloNum) ? DFloNum.make(asDouble(paramObject)) : object;
      case 11:
        return asNumeric(paramObject);
      case 10:
        break;
    } 
    return asNumeric(paramObject);
  }
  
  public static boolean isExact(Number paramNumber) {
    return (paramNumber instanceof Numeric) ? ((Numeric)paramNumber).isExact() : ((!(paramNumber instanceof Double) && !(paramNumber instanceof Float) && !(paramNumber instanceof BigDecimal)));
  }
  
  public static Type kindType(int paramInt) {
    switch (paramInt) {
      default:
        return (Type)Type.pointer_type;
      case 1:
        return (Type)LangPrimType.intType;
      case 2:
        return (Type)LangPrimType.longType;
      case 3:
        return (Type)ClassType.make("java.math.BigInteger");
      case 4:
        return (Type)typeIntNum;
      case 5:
        return (Type)ClassType.make("java.math.BigDecimal");
      case 6:
        return (Type)typeRatNum;
      case 7:
        return (Type)LangPrimType.floatType;
      case 8:
        return (Type)LangPrimType.doubleType;
      case 9:
        return (Type)typeDFloNum;
      case 10:
        return (Type)typeRealNum;
      case 11:
        break;
    } 
    return (Type)typeNumeric;
  }
  
  public static Number toExact(Number paramNumber) {
    if (paramNumber instanceof Numeric)
      return (Number)((Numeric)paramNumber).toExact(); 
    if (!(paramNumber instanceof Double) && !(paramNumber instanceof Float)) {
      Number number = paramNumber;
      return (Number)((paramNumber instanceof BigDecimal) ? DFloNum.toExact(paramNumber.doubleValue()) : number);
    } 
    return (Number)DFloNum.toExact(paramNumber.doubleValue());
  }
  
  public static Number toInexact(Number paramNumber) {
    if (paramNumber instanceof Numeric)
      return (Number)((Numeric)paramNumber).toInexact(); 
    Number number = paramNumber;
    if (!(paramNumber instanceof Double)) {
      number = paramNumber;
      if (!(paramNumber instanceof Float)) {
        number = paramNumber;
        if (!(paramNumber instanceof BigDecimal))
          return Double.valueOf(paramNumber.doubleValue()); 
      } 
    } 
    return number;
  }
  
  public static String toString(Object paramObject, int paramInt) {
    switch (classifyValue(paramObject)) {
      default:
        return asNumeric(paramObject).toString(paramInt);
      case 1:
        return Integer.toString(asInt(paramObject), paramInt);
      case 2:
        return Long.toString(asLong(paramObject), paramInt);
      case 3:
        return asBigInteger(paramObject).toString(paramInt);
      case 4:
        return asIntNum(paramObject).toString(paramInt);
      case 5:
        if (paramInt == 10)
          return asBigDecimal(paramObject).toString(); 
      case 7:
        if (paramInt == 10)
          return Float.toString(asFloat(paramObject)); 
        break;
      case 8:
      case 9:
        break;
    } 
    if (paramInt == 10)
      return Double.toString(asDouble(paramObject)); 
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/gnu/kawa/functions/Arithmetic.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */