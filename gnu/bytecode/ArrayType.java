package gnu.bytecode;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.io.ObjectStreamException;
import java.util.List;

public class ArrayType extends ObjectType implements Externalizable {
  public Type elements;
  
  public ArrayType(Type paramType) {
    this(paramType, paramType.getName() + "[]");
  }
  
  ArrayType(Type paramType, String paramString) {
    this.this_name = paramString;
    this.elements = paramType;
  }
  
  public static ArrayType make(Type paramType) {
    ArrayType arrayType2 = paramType.array_type;
    ArrayType arrayType1 = arrayType2;
    if (arrayType2 == null) {
      arrayType1 = new ArrayType(paramType, paramType.getName() + "[]");
      paramType.array_type = arrayType1;
    } 
    return arrayType1;
  }
  
  static ArrayType make(String paramString) {
    Type type = Type.getType(paramString.substring(0, paramString.length() - 2));
    ArrayType arrayType2 = type.array_type;
    ArrayType arrayType1 = arrayType2;
    if (arrayType2 == null) {
      arrayType1 = new ArrayType(type, paramString);
      type.array_type = arrayType1;
    } 
    return arrayType1;
  }
  
  public int compare(Type paramType) {
    return (paramType == nullType) ? 1 : ((paramType instanceof ArrayType) ? this.elements.compare(((ArrayType)paramType).elements) : ((paramType.getName().equals("java.lang.Object") || paramType == toStringType) ? -1 : -3));
  }
  
  public Type getComponentType() {
    return this.elements;
  }
  
  public Type getImplementationType() {
    Type type = this.elements.getImplementationType();
    return (this.elements == type) ? this : make(type);
  }
  
  public String getInternalName() {
    return getSignature();
  }
  
  public int getMethods(Filter paramFilter, int paramInt, List<Method> paramList) {
    int i = 0;
    if (paramInt > 0) {
      int j = Type.objectType.getMethods(paramFilter, 0, paramList);
      i = j;
      if (paramInt > 1) {
        i = j;
        if (paramFilter.select(Type.clone_method)) {
          if (paramList != null)
            paramList.add(Type.clone_method); 
          i = j + 1;
        } 
      } 
    } 
    return i;
  }
  
  public Class getReflectClass() {
    try {
      if (this.reflectClass == null)
        this.reflectClass = Class.forName(getInternalName().replace('/', '.'), false, this.elements.getReflectClass().getClassLoader()); 
      this.flags |= 0x10;
    } catch (ClassNotFoundException classNotFoundException) {}
    return this.reflectClass;
  }
  
  public String getSignature() {
    if (this.signature == null)
      setSignature("[" + this.elements.getSignature()); 
    return this.signature;
  }
  
  public void readExternal(ObjectInput paramObjectInput) throws IOException, ClassNotFoundException {
    this.elements = (Type)paramObjectInput.readObject();
  }
  
  public Object readResolve() throws ObjectStreamException {
    ArrayType arrayType = this.elements.array_type;
    if (arrayType != null)
      return arrayType; 
    this.elements.array_type = this;
    return this;
  }
  
  public void writeExternal(ObjectOutput paramObjectOutput) throws IOException {
    paramObjectOutput.writeObject(this.elements);
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/gnu/bytecode/ArrayType.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */