package gnu.xquery.util;

import gnu.kawa.functions.Arithmetic;
import gnu.kawa.xml.KNode;
import gnu.kawa.xml.XDataType;
import gnu.mapping.Procedure1;
import gnu.mapping.Values;
import gnu.math.IntNum;
import gnu.math.Numeric;
import gnu.math.RealNum;
import gnu.xml.TextUtils;
import java.math.BigDecimal;

public class NumberValue extends Procedure1 {
  public static final Double NaN;
  
  public static final NumberValue numberValue = new NumberValue();
  
  static {
    NaN = new Double(Double.NaN);
  }
  
  public static Object abs(Object paramObject) {
    if (paramObject != null && paramObject != Values.empty) {
      paramObject = numberCast(paramObject);
      if (paramObject instanceof Double) {
        paramObject = paramObject;
        long l = Double.doubleToRawLongBits(paramObject.doubleValue());
        return (l < 0L) ? Double.valueOf(Double.longBitsToDouble(l & Long.MAX_VALUE)) : paramObject;
      } 
      if (paramObject instanceof Float) {
        paramObject = paramObject;
        int i = Float.floatToRawIntBits(paramObject.floatValue());
        return (i < 0) ? Float.valueOf(Float.intBitsToFloat(i & Integer.MAX_VALUE)) : paramObject;
      } 
      if (paramObject instanceof BigDecimal) {
        BigDecimal bigDecimal = (BigDecimal)paramObject;
        paramObject = bigDecimal;
        if (bigDecimal.signum() < 0)
          paramObject = bigDecimal.negate(); 
        return paramObject;
      } 
      return ((Numeric)paramObject).abs();
    } 
    return paramObject;
  }
  
  public static Object ceiling(Object paramObject) {
    Number number = numberCast(paramObject);
    return (number == null) ? paramObject : ((number instanceof Double) ? XDataType.makeDouble(Math.ceil(((Double)number).doubleValue())) : ((number instanceof Float) ? XDataType.makeFloat((float)Math.ceil(((Float)number).floatValue())) : ((number instanceof BigDecimal) ? Arithmetic.asIntNum(((BigDecimal)number).divide(XDataType.DECIMAL_ONE, 0, 2).toBigInteger()) : ((RealNum)number).toInt(2))));
  }
  
  public static Object floor(Object paramObject) {
    Number number = numberCast(paramObject);
    return (number == null) ? paramObject : ((number instanceof Double) ? XDataType.makeDouble(Math.floor(((Double)number).doubleValue())) : ((number instanceof Float) ? XDataType.makeFloat((float)Math.floor(((Float)number).floatValue())) : ((number instanceof BigDecimal) ? Arithmetic.asIntNum(((BigDecimal)number).divide(XDataType.DECIMAL_ONE, 0, 3).toBigInteger()) : ((RealNum)number).toInt(1))));
  }
  
  public static boolean isNaN(Object paramObject) {
    return ((paramObject instanceof Double || paramObject instanceof Float || paramObject instanceof gnu.math.DFloNum) && Double.isNaN(((Number)paramObject).doubleValue()));
  }
  
  public static Number numberCast(Object paramObject) {
    if (paramObject == Values.empty || paramObject == null)
      return null; 
    Object object = paramObject;
    if (paramObject instanceof Values) {
      Values values = (Values)paramObject;
      int j = values.startPos();
      int i = 0;
      while (true) {
        j = values.nextPos(j);
        object = paramObject;
        if (j != 0) {
          if (i)
            throw new ClassCastException("non-singleton sequence cast to number"); 
          paramObject = values.getPosPrevious(j);
          i++;
          continue;
        } 
        break;
      } 
    } 
    return (object instanceof KNode || object instanceof gnu.kawa.xml.UntypedAtomic) ? (Double)XDataType.doubleType.valueOf(TextUtils.stringValue(object)) : (Number)object;
  }
  
  public static Object numberValue(Object paramObject) {
    paramObject = KNode.atomicValue(paramObject);
    if (paramObject instanceof gnu.kawa.xml.UntypedAtomic || paramObject instanceof String) {
      double d1;
      try {
        return XDataType.doubleType.valueOf(TextUtils.stringValue(paramObject));
      } catch (Throwable throwable) {
        d1 = Double.NaN;
      } 
      return XDataType.makeDouble(d1);
    } 
    if (throwable instanceof Number && (throwable instanceof RealNum || !(throwable instanceof Numeric))) {
      double d1 = ((Number)throwable).doubleValue();
      return XDataType.makeDouble(d1);
    } 
    double d = Double.NaN;
    return XDataType.makeDouble(d);
  }
  
  public static Object round(Object paramObject) {
    byte b = 4;
    Number number = numberCast(paramObject);
    if (number == null)
      return paramObject; 
    if (number instanceof Double) {
      double d = ((Double)number).doubleValue();
      if (d >= -0.5D && d <= 0.0D && (d < 0.0D || Double.doubleToLongBits(d) < 0L)) {
        d = 0.0D;
        return XDataType.makeDouble(d);
      } 
      d = Math.floor(d + 0.5D);
      return XDataType.makeDouble(d);
    } 
    if (number instanceof Float) {
      float f = ((Float)number).floatValue();
      if (f >= -0.5D && f <= 0.0D && (f < 0.0D || Float.floatToIntBits(f) < 0)) {
        f = 0.0F;
        return XDataType.makeFloat(f);
      } 
      f = (float)Math.floor(f + 0.5D);
      return XDataType.makeFloat(f);
    } 
    if (number instanceof BigDecimal) {
      paramObject = number;
      if (paramObject.signum() < 0)
        b = 5; 
      return Arithmetic.asIntNum(paramObject.divide(XDataType.DECIMAL_ONE, 0, b).toBigInteger());
    } 
    return ((RealNum)number).toInt(4);
  }
  
  public static Object roundHalfToEven(Object paramObject) {
    return roundHalfToEven(paramObject, IntNum.zero());
  }
  
  public static Object roundHalfToEven(Object paramObject, IntNum paramIntNum) {
    // Byte code:
    //   0: aload_0
    //   1: invokestatic numberCast : (Ljava/lang/Object;)Ljava/lang/Number;
    //   4: astore #4
    //   6: aload #4
    //   8: ifnonnull -> 13
    //   11: aload_0
    //   12: areturn
    //   13: aload_0
    //   14: instanceof java/lang/Double
    //   17: ifne -> 27
    //   20: aload_0
    //   21: instanceof java/lang/Float
    //   24: ifeq -> 55
    //   27: aload_0
    //   28: checkcast java/lang/Number
    //   31: invokevirtual doubleValue : ()D
    //   34: dstore_2
    //   35: dload_2
    //   36: dconst_0
    //   37: dcmpl
    //   38: ifeq -> 11
    //   41: dload_2
    //   42: invokestatic isInfinite : (D)Z
    //   45: ifne -> 11
    //   48: dload_2
    //   49: invokestatic isNaN : (D)Z
    //   52: ifne -> 11
    //   55: getstatic gnu/kawa/xml/XDataType.decimalType : Lgnu/kawa/xml/XDataType;
    //   58: aload #4
    //   60: invokevirtual cast : (Ljava/lang/Object;)Ljava/lang/Object;
    //   63: checkcast java/math/BigDecimal
    //   66: aload_1
    //   67: invokevirtual intValue : ()I
    //   70: bipush #6
    //   72: invokevirtual setScale : (II)Ljava/math/BigDecimal;
    //   75: astore_0
    //   76: aload #4
    //   78: instanceof java/lang/Double
    //   81: ifeq -> 92
    //   84: aload_0
    //   85: invokevirtual doubleValue : ()D
    //   88: invokestatic makeDouble : (D)Ljava/lang/Double;
    //   91: areturn
    //   92: aload #4
    //   94: instanceof java/lang/Float
    //   97: ifeq -> 108
    //   100: aload_0
    //   101: invokevirtual floatValue : ()F
    //   104: invokestatic makeFloat : (F)Ljava/lang/Float;
    //   107: areturn
    //   108: aload #4
    //   110: instanceof gnu/math/IntNum
    //   113: ifeq -> 124
    //   116: getstatic gnu/kawa/xml/XIntegerType.integerType : Lgnu/kawa/xml/XIntegerType;
    //   119: aload_0
    //   120: invokevirtual cast : (Ljava/lang/Object;)Ljava/lang/Object;
    //   123: areturn
    //   124: aload_0
    //   125: areturn
  }
  
  public Object apply1(Object paramObject) {
    if (paramObject != Values.empty && paramObject != null)
      try {
        return numberValue(paramObject);
      } catch (Throwable throwable) {} 
    return NaN;
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/gnu/xquery/util/NumberValue.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */