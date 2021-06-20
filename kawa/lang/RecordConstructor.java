package kawa.lang;

import gnu.bytecode.ClassType;
import gnu.bytecode.Field;
import gnu.bytecode.Type;
import gnu.lists.LList;
import gnu.lists.Pair;
import gnu.mapping.ProcedureN;
import gnu.mapping.WrappedException;
import gnu.mapping.WrongArguments;

public class RecordConstructor extends ProcedureN {
  Field[] fields;
  
  ClassType type;
  
  public RecordConstructor(ClassType paramClassType) {
    init(paramClassType);
  }
  
  public RecordConstructor(ClassType paramClassType, Object paramObject) {
    this.type = paramClassType;
    if (paramObject == null) {
      init(paramClassType);
      return;
    } 
    int j = LList.listLength(paramObject, false);
    this.fields = new Field[j];
    Field field = paramClassType.getFields();
    int i = 0;
    label17: while (true) {
      if (i < j) {
        Pair pair = (Pair)paramObject;
        String str = pair.getCar().toString();
        for (paramObject = field;; paramObject = paramObject.getNext()) {
          if (paramObject == null)
            throw new RuntimeException("no such field " + str + " in " + paramClassType.getName()); 
          if (paramObject.getSourceName() == str) {
            this.fields[i] = (Field)paramObject;
            paramObject = pair.getCdr();
            i++;
            continue label17;
          } 
        } 
        break;
      } 
      return;
    } 
  }
  
  public RecordConstructor(ClassType paramClassType, Field[] paramArrayOfField) {
    this.type = paramClassType;
    this.fields = paramArrayOfField;
  }
  
  public RecordConstructor(Class paramClass) {
    init((ClassType)Type.make(paramClass));
  }
  
  public RecordConstructor(Class paramClass, Object paramObject) {
    this((ClassType)Type.make(paramClass), paramObject);
  }
  
  public RecordConstructor(Class paramClass, Field[] paramArrayOfField) {
    this((ClassType)Type.make(paramClass), paramArrayOfField);
  }
  
  private void init(ClassType paramClassType) {
    this.type = paramClassType;
    Field field1 = paramClassType.getFields();
    int i = 0;
    Field field2 = field1;
    while (field2 != null) {
      int j = i;
      if ((field2.getModifiers() & 0x9) == 1)
        j = i + 1; 
      field2 = field2.getNext();
      i = j;
    } 
    this.fields = new Field[i];
    i = 0;
    while (field1 != null) {
      if ((field1.getModifiers() & 0x9) == 1) {
        Field[] arrayOfField = this.fields;
        int j = i + 1;
        arrayOfField[i] = field1;
        i = j;
      } 
      field1 = field1.getNext();
    } 
  }
  
  public Object applyN(Object[] paramArrayOfObject) {
    Object object;
    try {
      object = this.type.getReflectClass().newInstance();
      if (paramArrayOfObject.length != this.fields.length)
        throw new WrongArguments(this, paramArrayOfObject.length); 
    } catch (InstantiationException instantiationException) {
      throw new GenericError(instantiationException.toString());
    } catch (IllegalAccessException illegalAccessException) {
      throw new GenericError(illegalAccessException.toString());
    } 
    int i = 0;
    while (i < illegalAccessException.length) {
      Field field = this.fields[i];
      try {
        field.getReflectField().set(object, illegalAccessException[i]);
        i++;
      } catch (Exception exception) {
        throw new WrappedException("illegal access for field " + field.getName(), exception);
      } 
    } 
    return object;
  }
  
  public String getName() {
    return this.type.getName() + " constructor";
  }
  
  public int numArgs() {
    int i = this.fields.length;
    return i << 12 | i;
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/kawa/lang/RecordConstructor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */