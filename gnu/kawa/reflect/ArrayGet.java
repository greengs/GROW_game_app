package gnu.kawa.reflect;

import gnu.bytecode.Type;
import gnu.mapping.Procedure;
import gnu.mapping.Procedure2;
import gnu.mapping.PropertySet;
import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.lang.reflect.Array;

public class ArrayGet extends Procedure2 implements Externalizable {
  Type element_type;
  
  public ArrayGet(Type paramType) {
    this.element_type = paramType;
    setProperty(Procedure.validateApplyKey, "gnu.kawa.reflect.CompileArrays:validateArrayGet");
    Procedure.compilerKey.set((PropertySet)this, "*gnu.kawa.reflect.CompileArrays:getForArrayGet");
  }
  
  public Object apply2(Object paramObject1, Object paramObject2) {
    paramObject1 = Array.get(paramObject1, ((Number)paramObject2).intValue());
    return this.element_type.coerceToObject(paramObject1);
  }
  
  public boolean isSideEffectFree() {
    return true;
  }
  
  public void readExternal(ObjectInput paramObjectInput) throws IOException, ClassNotFoundException {
    this.element_type = (Type)paramObjectInput.readObject();
  }
  
  public void writeExternal(ObjectOutput paramObjectOutput) throws IOException {
    paramObjectOutput.writeObject(this.element_type);
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/gnu/kawa/reflect/ArrayGet.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */