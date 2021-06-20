package gnu.kawa.lispexpr;

import gnu.bytecode.ClassType;
import gnu.bytecode.Type;
import gnu.kawa.functions.GetNamedPart;
import gnu.mapping.Namespace;
import gnu.mapping.WrappedException;
import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.io.ObjectStreamException;

public class ClassNamespace extends Namespace implements Externalizable {
  ClassType ctype;
  
  public ClassNamespace() {}
  
  public ClassNamespace(ClassType paramClassType) {
    setName("class:" + paramClassType.getName());
    this.ctype = paramClassType;
  }
  
  public static ClassNamespace getInstance(String paramString, ClassType paramClassType) {
    synchronized (nsTable) {
      ClassNamespace classNamespace1;
      Object object = nsTable.get(paramString);
      if (object instanceof ClassNamespace) {
        classNamespace1 = (ClassNamespace)object;
        return classNamespace1;
      } 
      ClassNamespace classNamespace2 = new ClassNamespace(paramClassType);
      nsTable.put(classNamespace1, classNamespace2);
      return classNamespace2;
    } 
  }
  
  public Object get(String paramString) {
    try {
      return GetNamedPart.getTypePart((Type)this.ctype, paramString);
    } catch (Throwable throwable) {
      throw WrappedException.wrapIfNeeded(throwable);
    } 
  }
  
  public ClassType getClassType() {
    return this.ctype;
  }
  
  public void readExternal(ObjectInput paramObjectInput) throws IOException, ClassNotFoundException {
    this.ctype = (ClassType)paramObjectInput.readObject();
    setName("class:" + this.ctype.getName());
  }
  
  public Object readResolve() throws ObjectStreamException {
    String str = getName();
    if (str != null) {
      Namespace namespace = (Namespace)nsTable.get(str);
      if (namespace instanceof ClassNamespace)
        return namespace; 
      nsTable.put(str, this);
    } 
    return this;
  }
  
  public void writeExternal(ObjectOutput paramObjectOutput) throws IOException {
    paramObjectOutput.writeObject(this.ctype);
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/gnu/kawa/lispexpr/ClassNamespace.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */