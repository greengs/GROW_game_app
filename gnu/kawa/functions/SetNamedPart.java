package gnu.kawa.functions;

import gnu.bytecode.ClassType;
import gnu.bytecode.Type;
import gnu.kawa.reflect.SlotSet;
import gnu.mapping.Environment;
import gnu.mapping.HasSetter;
import gnu.mapping.Procedure;
import gnu.mapping.Procedure3;
import gnu.mapping.Symbol;
import gnu.mapping.Values;

public class SetNamedPart extends Procedure3 implements HasSetter {
  public static final SetNamedPart setNamedPart = new SetNamedPart();
  
  static {
    setNamedPart.setName("setNamedPart");
    setNamedPart.setProperty(Procedure.validateApplyKey, "gnu.kawa.functions.CompileNamedPart:validateSetNamedPart");
  }
  
  public Object apply3(Object paramObject1, Object paramObject2, Object paramObject3) {
    Object object = paramObject1;
    if (paramObject1 instanceof gnu.mapping.Namespace) {
      paramObject1 = paramObject1;
      object = paramObject1.getName();
      if (object.startsWith("class:")) {
        object = ClassType.make(object.substring(6));
      } else {
        paramObject1 = paramObject1.getSymbol(paramObject2.toString());
        Environment.getCurrent();
        Environment.getCurrent().put((Symbol)paramObject1, paramObject3);
        return Values.empty;
      } 
    } 
    paramObject1 = object;
    if (object instanceof Class)
      paramObject1 = Type.make((Class)object); 
    if (paramObject1 instanceof ClassType)
      try {
        SlotSet.setStaticField(paramObject1, paramObject2.toString(), paramObject3);
        return Values.empty;
      } catch (Throwable throwable) {} 
    SlotSet.setField(paramObject1, paramObject2.toString(), paramObject3);
    return Values.empty;
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/gnu/kawa/functions/SetNamedPart.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */