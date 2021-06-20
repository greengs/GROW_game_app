package gnu.kawa.functions;

import gnu.mapping.Procedure;
import gnu.mapping.PropertySet;
import gnu.math.DFloNum;
import gnu.math.IntNum;
import gnu.math.RatNum;
import java.math.BigDecimal;
import java.math.BigInteger;

public class AddOp extends ArithOp {
  public static final AddOp $Mn;
  
  public static final AddOp $Pl = new AddOp("+", 1);
  
  int plusOrMinus;
  
  public static Object $Mn(Object paramObject) {
    switch (Arithmetic.classifyValue(paramObject)) {
      default:
        return Arithmetic.asNumeric(paramObject).neg();
      case 1:
        return new Integer(-Arithmetic.asInt(paramObject));
      case 2:
        return new Long(-Arithmetic.asLong(paramObject));
      case 3:
        return Arithmetic.asBigInteger(paramObject).negate();
      case 4:
        return IntNum.neg(Arithmetic.asIntNum(paramObject));
      case 5:
        return Arithmetic.asBigDecimal(paramObject).negate();
      case 6:
        return RatNum.neg(Arithmetic.asRatNum(paramObject));
      case 7:
        return new Float(-Arithmetic.asFloat(paramObject));
      case 8:
        return new Double(-Arithmetic.asDouble(paramObject));
      case 9:
        break;
    } 
    return new DFloNum(-Arithmetic.asDouble(paramObject));
  }
  
  public static Object $Mn(Object paramObject1, Object paramObject2) {
    return apply2(-1, paramObject1, paramObject2);
  }
  
  public static Object $Mn$V(Object paramObject1, Object paramObject2, Object paramObject3, Object[] paramArrayOfObject) {
    return applyN(-1, apply2(-1, apply2(-1, paramObject1, paramObject2), paramObject3), paramArrayOfObject);
  }
  
  public static Object $Pl(Object paramObject1, Object paramObject2) {
    return apply2(1, paramObject1, paramObject2);
  }
  
  public static Object $Pl$V(Object paramObject1, Object paramObject2, Object paramObject3, Object[] paramArrayOfObject) {
    return applyN(1, apply2(1, apply2(1, paramObject1, paramObject2), paramObject3), paramArrayOfObject);
  }
  
  static {
    $Mn = new AddOp("-", -1);
  }
  
  public AddOp(String paramString, int paramInt) {
    super(paramString, b);
    byte b;
    this.plusOrMinus = 1;
    this.plusOrMinus = paramInt;
    if (paramInt > 0) {
      paramString = "gnu.kawa.functions.CompileArith:$Pl";
    } else {
      paramString = "gnu.kawa.functions.CompileArith:$Mn";
    } 
    Procedure.compilerKey.set((PropertySet)this, paramString);
    setProperty(Procedure.validateApplyKey, "gnu.kawa.functions.CompileArith:validateApplyArithOp");
  }
  
  public static Object apply2(int paramInt, Object paramObject1, Object paramObject2) {
    float f1;
    float f2;
    long l1;
    long l2;
    int i = Arithmetic.classifyValue(paramObject1);
    int j = Arithmetic.classifyValue(paramObject2);
    if (i < j)
      i = j; 
    switch (i) {
      default:
        return Arithmetic.asNumeric(paramObject1).add(Arithmetic.asNumeric(paramObject2), paramInt);
      case 1:
        i = Arithmetic.asInt(paramObject1);
        j = Arithmetic.asInt(paramObject2);
        if (paramInt > 0) {
          paramInt = i + j;
          return new Integer(paramInt);
        } 
        paramInt = i - j;
        return new Integer(paramInt);
      case 2:
        l1 = Arithmetic.asLong(paramObject1);
        l2 = Arithmetic.asLong(paramObject2);
        if (paramInt > 0) {
          l1 += l2;
          return new Long(l1);
        } 
        l1 -= l2;
        return new Long(l1);
      case 3:
        paramObject1 = Arithmetic.asBigInteger(paramObject1);
        paramObject2 = Arithmetic.asBigInteger(paramObject2);
        return (paramInt > 0) ? paramObject1.add((BigInteger)paramObject2) : paramObject1.subtract((BigInteger)paramObject2);
      case 4:
        return IntNum.add(Arithmetic.asIntNum(paramObject1), Arithmetic.asIntNum(paramObject2), paramInt);
      case 5:
        paramObject1 = Arithmetic.asBigDecimal(paramObject1);
        paramObject2 = Arithmetic.asBigDecimal(paramObject2);
        return (paramInt > 0) ? paramObject1.add((BigDecimal)paramObject2) : paramObject1.subtract((BigDecimal)paramObject2);
      case 6:
        return RatNum.add(Arithmetic.asRatNum(paramObject1), Arithmetic.asRatNum(paramObject2), paramInt);
      case 7:
        f1 = Arithmetic.asFloat(paramObject1);
        f2 = Arithmetic.asFloat(paramObject2);
        if (paramInt > 0) {
          f1 += f2;
          return new Float(f1);
        } 
        f1 -= f2;
        return new Float(f1);
      case 8:
        d1 = Arithmetic.asDouble(paramObject1);
        d2 = Arithmetic.asDouble(paramObject2);
        if (paramInt > 0) {
          d1 += d2;
          return new Double(d1);
        } 
        d1 -= d2;
        return new Double(d1);
      case 9:
        break;
    } 
    double d1 = Arithmetic.asDouble(paramObject1);
    double d2 = Arithmetic.asDouble(paramObject2);
    if (paramInt > 0) {
      d1 += d2;
      return new DFloNum(d1);
    } 
    d1 -= d2;
    return new DFloNum(d1);
  }
  
  public static Object applyN(int paramInt, Object paramObject, Object[] paramArrayOfObject) {
    int j = paramArrayOfObject.length;
    for (int i = 0; i < j; i++)
      paramObject = apply2(paramInt, paramObject, paramArrayOfObject[i]); 
    return paramObject;
  }
  
  public static Object applyN(int paramInt, Object[] paramArrayOfObject) {
    int j = paramArrayOfObject.length;
    if (j == 0)
      return IntNum.zero(); 
    Object object = paramArrayOfObject[0];
    if (j == 1 && paramInt < 0)
      return $Mn(object); 
    int i = 1;
    while (true) {
      Object object1 = object;
      if (i < j) {
        object = apply2(paramInt, object, paramArrayOfObject[i]);
        i++;
        continue;
      } 
      return object1;
    } 
  }
  
  public Object applyN(Object[] paramArrayOfObject) {
    return applyN(this.plusOrMinus, paramArrayOfObject);
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/gnu/kawa/functions/AddOp.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */