package gnu.kawa.reflect;

import gnu.bytecode.Type;
import gnu.mapping.Procedure;
import gnu.mapping.Procedure1;
import gnu.mapping.PropertySet;
import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.lang.reflect.Array;

public class ArrayNew extends Procedure1 implements Externalizable {
  Type element_type;
  
  public ArrayNew(Type paramType) {
    this.element_type = paramType;
    setProperty(Procedure.validateApplyKey, "gnu.kawa.reflect.CompileArrays:validateArrayNew");
    Procedure.compilerKey.set((PropertySet)this, "*gnu.kawa.reflect.CompileArrays:getForArrayNew");
  }
  
  public Object apply1(Object paramObject) {
    return Array.newInstance(this.element_type.getImplementationType().getReflectClass(), ((Number)paramObject).intValue());
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


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/gnu/kawa/reflect/ArrayNew.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */