package gnu.kawa.functions;

import gnu.mapping.Procedure;
import gnu.mapping.PropertySet;
import gnu.math.DFloNum;
import gnu.math.IntNum;
import gnu.math.Numeric;
import gnu.math.RatNum;
import java.math.BigDecimal;

public class MultiplyOp extends ArithOp {
  public static final MultiplyOp $St = new MultiplyOp("*");
  
  public MultiplyOp(String paramString) {
    super(paramString, 3);
    setProperty(Procedure.validateApplyKey, "gnu.kawa.functions.CompileArith:validateApplyArithOp");
    Procedure.compilerKey.set((PropertySet)this, "*gnu.kawa.functions.CompileArith:forMul");
  }
  
  public static Object apply(Object paramObject1, Object paramObject2) {
    return ((Numeric)paramObject1).mul(paramObject2);
  }
  
  public Object applyN(Object[] paramArrayOfObject) {
    int k = paramArrayOfObject.length;
    if (k == 0)
      return IntNum.one(); 
    Number number = (Number)paramArrayOfObject[0];
    int j = Arithmetic.classifyValue(number);
    int i = 1;
    while (true) {
      Object object = number;
      if (i < k) {
        IntNum intNum;
        BigDecimal bigDecimal;
        RatNum ratNum;
        Float float_;
        Double double_;
        DFloNum dFloNum;
        object = paramArrayOfObject[i];
        int n = Arithmetic.classifyValue(object);
        int m = j;
        if (j < n)
          m = n; 
        switch (m) {
          case 1:
            number = new Integer(Arithmetic.asInt(number) * Arithmetic.asInt(object));
            i++;
            j = m;
            break;
          case 2:
            number = new Long(Arithmetic.asLong(number) * Arithmetic.asLong(object));
            i++;
            j = m;
            break;
          case 3:
            number = Arithmetic.asBigInteger(number).multiply(Arithmetic.asBigInteger(object));
            i++;
            j = m;
            break;
          case 4:
            intNum = IntNum.times(Arithmetic.asIntNum(number), Arithmetic.asIntNum(object));
            i++;
            j = m;
            break;
          case 5:
            bigDecimal = Arithmetic.asBigDecimal(intNum).multiply(Arithmetic.asBigDecimal(object));
            i++;
            j = m;
            break;
          case 6:
            ratNum = RatNum.times(Arithmetic.asRatNum(bigDecimal), Arithmetic.asRatNum(object));
            i++;
            j = m;
            break;
          case 7:
            float_ = new Float(Arithmetic.asFloat(ratNum) * Arithmetic.asFloat(object));
            i++;
            j = m;
            break;
          case 8:
            double_ = new Double(Arithmetic.asDouble(float_) * Arithmetic.asDouble(object));
            i++;
            j = m;
            break;
          case 9:
            dFloNum = new DFloNum(Arithmetic.asDouble(double_) * Arithmetic.asDouble(object));
            i++;
            j = m;
            break;
        } 
        continue;
      } 
      return object;
    } 
  }
  
  public Object defaultResult() {
    return IntNum.one();
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/gnu/kawa/functions/MultiplyOp.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */