package kawa.standard;

import gnu.bytecode.ClassType;
import gnu.mapping.ProcedureN;
import gnu.mapping.WrappedException;
import gnu.mapping.WrongArguments;
import gnu.mapping.WrongType;
import kawa.lang.Record;

public class make extends ProcedureN {
  public Object applyN(Object[] paramArrayOfObject) {
    Class<Class<?>> clazz;
    int i = paramArrayOfObject.length;
    if (i == 0)
      throw new WrongArguments(this, i); 
    Object object = paramArrayOfObject[0];
    if (object instanceof Class) {
      clazz = (Class)object;
    } else if (object instanceof ClassType) {
      clazz = ((ClassType)object).getReflectClass();
    } else {
      clazz = null;
    } 
    if (clazz == null)
      throw new WrongType(this, 1, object, "class"); 
    try {
      clazz = (Class)clazz.newInstance();
      int j = 1;
      while (j < i) {
        int k = j + 1;
        object = paramArrayOfObject[j];
        j = k + 1;
        Record.set1(paramArrayOfObject[k], object.getName(), clazz);
      } 
    } catch (Exception exception) {
      throw new WrappedException(exception);
    } 
    return clazz;
  }
  
  public int numArgs() {
    return -4095;
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/kawa/standard/make.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */