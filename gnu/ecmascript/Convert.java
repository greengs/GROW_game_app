package gnu.ecmascript;

public class Convert {
  public static double toInteger(double paramDouble) {
    return Double.isNaN(paramDouble) ? 0.0D : ((paramDouble < 0.0D) ? Math.ceil(paramDouble) : Math.floor(paramDouble));
  }
  
  public static double toInteger(Object paramObject) {
    return toInteger(toNumber(paramObject));
  }
  
  public static double toNumber(Object paramObject) {
    double d = Double.NaN;
    if (paramObject instanceof Number)
      return ((Number)paramObject).doubleValue(); 
    if (paramObject instanceof Boolean)
      return ((Boolean)paramObject).booleanValue() ? 1.0D : 0.0D; 
    if (paramObject instanceof String)
      try {
        return Double.valueOf((String)paramObject).doubleValue();
      } catch (NumberFormatException numberFormatException) {
        return Double.NaN;
      }  
    return d;
  }
  
  public int toInt32(double paramDouble) {
    return (Double.isNaN(paramDouble) || Double.isInfinite(paramDouble)) ? 0 : (int)paramDouble;
  }
  
  public int toInt32(Object paramObject) {
    return toInt32(toNumber(paramObject));
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/gnu/ecmascript/Convert.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */