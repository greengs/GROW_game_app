package gnu.ecmascript;

import gnu.mapping.Procedure2;

public class BinaryOp extends Procedure2 {
  int op;
  
  public BinaryOp(String paramString, int paramInt) {
    setName(paramString);
    this.op = paramInt;
  }
  
  public double apply(double paramDouble1, double paramDouble2) {
    switch (this.op) {
      default:
        return Double.NaN;
      case 1:
        return paramDouble1 + paramDouble2;
      case 2:
        return paramDouble1 - paramDouble2;
      case 3:
        return paramDouble1 * paramDouble2;
      case 4:
        break;
    } 
    return ((int)paramDouble1 << ((int)paramDouble2 & 0x1F));
  }
  
  public Object apply2(Object paramObject1, Object paramObject2) {
    return (this.op == 5) ? ((Convert.toNumber(paramObject1) < Convert.toNumber(paramObject2)) ? Boolean.TRUE : Boolean.FALSE) : new Double(apply(Convert.toNumber(paramObject1), Convert.toNumber(paramObject2)));
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/gnu/ecmascript/BinaryOp.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */