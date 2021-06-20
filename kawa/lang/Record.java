package kawa.lang;

import gnu.bytecode.ArrayClassLoader;
import gnu.bytecode.ClassType;
import gnu.bytecode.CodeAttr;
import gnu.bytecode.Field;
import gnu.bytecode.Method;
import gnu.bytecode.Type;
import gnu.expr.Compilation;
import gnu.lists.LList;
import gnu.lists.Pair;
import gnu.mapping.SimpleSymbol;
import gnu.mapping.WrappedException;
import java.io.PrintWriter;
import java.lang.reflect.Field;
import java.util.Vector;

public class Record {
  static Field getField(Class paramClass, String paramString) throws NoSuchFieldException {
    for (Field field = ((ClassType)Type.make(paramClass)).getFields(); field != null; field = field.getNext()) {
      if ((field.getModifiers() & 0x9) == 1 && field.getSourceName().equals(paramString))
        return field.getReflectField(); 
    } 
    throw new NoSuchFieldException();
  }
  
  public static boolean isRecord(Object paramObject) {
    return paramObject instanceof Record;
  }
  
  public static ClassType makeRecordType(String paramString, LList paramLList) {
    ClassType classType2 = ClassType.make("kawa.lang.Record");
    String str = Compilation.mangleNameIfNeeded(paramString);
    ClassType classType1 = new ClassType(str);
    classType1.setSuper(classType2);
    classType1.setModifiers(33);
    Method method1 = classType1.addMethod("<init>", Type.typeArray0, (Type)Type.voidType, 1);
    Method method2 = classType2.addMethod("<init>", Type.typeArray0, (Type)Type.voidType, 1);
    CodeAttr codeAttr = method1.startCode();
    codeAttr.emitPushThis();
    codeAttr.emitInvokeSpecial(method2);
    codeAttr.emitReturn();
    LList lList = paramLList;
    if (!paramString.equals(str)) {
      CodeAttr codeAttr1 = classType1.addMethod("getTypeName", Type.typeArray0, (Type)Compilation.typeString, 1).startCode();
      codeAttr1.emitPushString(paramString);
      codeAttr1.emitReturn();
      lList = paramLList;
    } 
    while (lList != LList.Empty) {
      Pair pair = (Pair)lList;
      String str1 = pair.getCar().toString();
      classType1.addField(Compilation.mangleNameIfNeeded(str1), (Type)Type.pointer_type, 1).setSourceName(str1.intern());
      lList = (LList)pair.getCdr();
    } 
    byte[] arrayOfByte = classType1.writeToArray();
    ArrayClassLoader arrayClassLoader = new ArrayClassLoader(new String[] { str }, new byte[][] { arrayOfByte });
    try {
      Type.registerTypeForClass(arrayClassLoader.loadClass(str), (Type)classType1);
      return classType1;
    } catch (ClassNotFoundException classNotFoundException) {
      throw new InternalError(classNotFoundException.toString());
    } 
  }
  
  public static Object set1(Object paramObject1, String paramString, Object paramObject2) {
    Class<?> clazz = paramObject1.getClass();
    try {
      Field field = getField(clazz, paramString);
      Object object = field.get(paramObject1);
      field.set(paramObject1, paramObject2);
      return object;
    } catch (NoSuchFieldException noSuchFieldException) {
      throw new GenericError("no such field " + paramString + " in " + clazz.getName());
    } catch (IllegalAccessException illegalAccessException) {
      throw new GenericError("illegal access for field " + paramString);
    } 
  }
  
  public static LList typeFieldNames(ClassType paramClassType) {
    return typeFieldNames(paramClassType.getReflectClass());
  }
  
  public static LList typeFieldNames(Class paramClass) {
    LList lList2 = LList.Empty;
    Field field = ((ClassType)Type.make(paramClass)).getFields();
    Vector<SimpleSymbol> vector = new Vector(100);
    while (field != null) {
      if ((field.getModifiers() & 0x9) == 1)
        vector.addElement(SimpleSymbol.valueOf(field.getSourceName())); 
      field = field.getNext();
    } 
    int i = vector.size();
    LList lList1 = lList2;
    while (true) {
      Pair pair;
      if (--i >= 0) {
        pair = new Pair(vector.elementAt(i), lList1);
        continue;
      } 
      return (LList)pair;
    } 
  }
  
  public boolean equals(Object paramObject) {
    if (this != paramObject) {
      Class<?> clazz = getClass();
      if (paramObject == null || paramObject.getClass() != clazz)
        return false; 
      for (Field field = ((ClassType)Type.make(clazz)).getFields();; field = field.getNext()) {
        if (field != null) {
          if ((field.getModifiers() & 0x9) == 1) {
            try {
              Field field1 = field.getReflectField();
              Object object1 = field1.get(this);
              Object object2 = field1.get(paramObject);
              if (!object1.equals(object2))
                return false; 
              field = field.getNext();
            } catch (Exception exception) {
              throw new WrappedException(exception);
            } 
            continue;
          } 
        } else {
          return true;
        } 
      } 
    } 
    return true;
  }
  
  public Object get(String paramString, Object<?> paramObject) {
    paramObject = (Object<?>)getClass();
    try {
      return getField((Class)paramObject, paramString).get(this);
    } catch (NoSuchFieldException noSuchFieldException) {
      throw new GenericError("no such field " + paramString + " in " + paramObject.getName());
    } catch (IllegalAccessException illegalAccessException) {
      throw new GenericError("illegal access for field " + paramString);
    } 
  }
  
  public String getTypeName() {
    return getClass().getName();
  }
  
  public int hashCode() {
    Field[] arrayOfField = getClass().getFields();
    char c = '〹';
    int i = 0;
    while (i < arrayOfField.length) {
      char c1;
      Field field = arrayOfField[i];
      try {
        Object object = field.get(this);
        c1 = c;
        if (object != null)
          c1 = c ^ object.hashCode(); 
      } catch (IllegalAccessException illegalAccessException) {
        c1 = c;
      } 
      i++;
      c = c1;
    } 
    return c;
  }
  
  public void print(PrintWriter paramPrintWriter) {
    paramPrintWriter.print(toString());
  }
  
  public Object put(String paramString, Object paramObject) {
    return set1(this, paramString, paramObject);
  }
  
  public String toString() {
    StringBuffer stringBuffer = new StringBuffer(200);
    stringBuffer.append("#<");
    stringBuffer.append(getTypeName());
    for (Field field = ((ClassType)Type.make(getClass())).getFields(); field != null; field = field.getNext()) {
      if ((field.getModifiers() & 0x9) == 1) {
        Object object;
        try {
          object = field.getReflectField().get(this);
        } catch (Exception exception) {
          object = "#<illegal-access>";
        } 
        stringBuffer.append(' ');
        stringBuffer.append(field.getSourceName());
        stringBuffer.append(": ");
        stringBuffer.append(object);
      } 
    } 
    stringBuffer.append(">");
    return stringBuffer.toString();
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/kawa/lang/Record.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */