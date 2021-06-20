package gnu.kawa.functions;

import gnu.bytecode.Type;
import gnu.expr.Language;
import gnu.mapping.Procedure2;
import gnu.mapping.Values;
import java.lang.reflect.Array;

class SetArray extends Procedure2 {
  Object array;
  
  Type elementType;
  
  public SetArray(Object paramObject, Language paramLanguage) {
    this.elementType = paramLanguage.getTypeFor(paramObject.getClass().getComponentType());
    this.array = paramObject;
  }
  
  public Object apply2(Object paramObject1, Object paramObject2) {
    paramObject2 = this.elementType.coerceFromObject(paramObject2);
    Array.set(this.array, ((Number)paramObject1).intValue(), paramObject2);
    return Values.empty;
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/gnu/kawa/functions/SetArray.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */