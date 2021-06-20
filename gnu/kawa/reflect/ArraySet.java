package gnu.kawa.reflect;

import gnu.bytecode.Type;
import gnu.mapping.Procedure;
import gnu.mapping.Procedure3;
import gnu.mapping.PropertySet;
import gnu.mapping.Values;
import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.lang.reflect.Array;

public class ArraySet extends Procedure3 implements Externalizable {
  Type element_type;
  
  public ArraySet(Type paramType) {
    this.element_type = paramType;
    setProperty(Procedure.validateApplyKey, "gnu.kawa.reflect.CompileArrays:validateArraySet");
    Procedure.compilerKey.set((PropertySet)this, "*gnu.kawa.reflect.CompileArrays:getForArraySet");
  }
  
  public Object apply3(Object paramObject1, Object paramObject2, Object paramObject3) {
    Array.set(paramObject1, ((Number)paramObject2).intValue(), this.element_type.coerceFromObject(paramObject3));
    return Values.empty;
  }
  
  public void readExternal(ObjectInput paramObjectInput) throws IOException, ClassNotFoundException {
    this.element_type = (Type)paramObjectInput.readObject();
  }
  
  public void writeExternal(ObjectOutput paramObjectOutput) throws IOException {
    paramObjectOutput.writeObject(this.element_type);
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/gnu/kawa/reflect/ArraySet.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */