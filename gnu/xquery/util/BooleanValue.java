package gnu.xquery.util;

import gnu.lists.Sequence;
import gnu.mapping.Procedure;
import gnu.mapping.Procedure1;
import gnu.mapping.Values;
import gnu.mapping.WrongType;

public class BooleanValue extends Procedure1 {
  public static final BooleanValue booleanValue = new BooleanValue("boolean-value");
  
  public BooleanValue(String paramString) {
    super(paramString);
    setProperty(Procedure.validateApplyKey, "gnu.xquery.util.CompileMisc:validateBooleanValue");
  }
  
  public static boolean booleanValue(Object paramObject) {
    boolean bool2 = true;
    if (paramObject instanceof Boolean)
      return ((Boolean)paramObject).booleanValue(); 
    if (paramObject instanceof Number && (paramObject instanceof gnu.math.RealNum || !(paramObject instanceof gnu.math.Numeric))) {
      double d = ((Number)paramObject).doubleValue();
      if (d != 0.0D) {
        boolean bool = bool2;
        return Double.isNaN(d) ? false : bool;
      } 
      return false;
    } 
    boolean bool1 = bool2;
    if (!(paramObject instanceof gnu.lists.SeqPosition)) {
      if (paramObject instanceof String || paramObject instanceof gnu.text.Path || paramObject instanceof gnu.kawa.xml.UntypedAtomic) {
        bool1 = bool2;
        return (paramObject.toString().length() <= 0) ? false : bool1;
      } 
      if (paramObject instanceof Values) {
        Values values = (Values)paramObject;
        Object object = values.getPosNext(0);
        if (object == Sequence.eofValue)
          return false; 
        if (values.nextDataIndex(0) < 0)
          return booleanValue(object); 
        bool1 = bool2;
        if (!(object instanceof gnu.lists.SeqPosition))
          throw new WrongType("fn:boolean", 1, paramObject, "boolean-convertible-value"); 
        return bool1;
      } 
      throw new WrongType("fn:boolean", 1, paramObject, "boolean-convertible-value");
    } 
    return bool1;
  }
  
  public static boolean not(Object paramObject) {
    return !booleanValue(paramObject);
  }
  
  public Object apply1(Object paramObject) {
    return booleanValue(paramObject) ? Boolean.TRUE : Boolean.FALSE;
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/gnu/xquery/util/BooleanValue.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */