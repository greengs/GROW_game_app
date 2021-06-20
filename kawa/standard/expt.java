package kawa.standard;

import gnu.mapping.Procedure2;
import gnu.math.Complex;
import gnu.math.IntNum;
import gnu.math.Numeric;

public class expt extends Procedure2 {
  public static final expt expt = new expt("expt");
  
  public expt(String paramString) {
    super(paramString);
  }
  
  public static IntNum expt(IntNum paramIntNum, int paramInt) {
    return IntNum.power(paramIntNum, paramInt);
  }
  
  public static Numeric expt(Object paramObject1, Object paramObject2) {
    return (Numeric)((paramObject2 instanceof IntNum) ? ((Numeric)paramObject1).power((IntNum)paramObject2) : Complex.power((Complex)paramObject1, (Complex)paramObject2));
  }
  
  public Object apply2(Object paramObject1, Object paramObject2) {
    return expt(paramObject1, paramObject2);
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/kawa/standard/expt.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */