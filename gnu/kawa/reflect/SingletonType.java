package gnu.kawa.reflect;

import gnu.bytecode.ClassType;
import gnu.bytecode.CodeAttr;
import gnu.bytecode.ObjectType;
import gnu.bytecode.Type;
import gnu.mapping.Values;

public class SingletonType extends ObjectType {
  static final SingletonType instance = new SingletonType("singleton");
  
  public SingletonType(String paramString) {
    super(paramString);
  }
  
  public static Object coerceToSingleton(Object paramObject) {
    Object object = paramObject;
    if (paramObject instanceof Values)
      object = ((Values)paramObject).canonicalize(); 
    if (object == null || object instanceof Values)
      throw new ClassCastException("value is not a singleton"); 
    return object;
  }
  
  public static final SingletonType getInstance() {
    return instance;
  }
  
  public Object coerceFromObject(Object paramObject) {
    return coerceToSingleton(paramObject);
  }
  
  public int compare(Type paramType) {
    byte b = -1;
    int j = OccurrenceType.itemCountRange(paramType);
    int i = j & 0xFFF;
    j >>= 12;
    if (j == 0 || i > 1)
      return -3; 
    if (i == 1 && j == 1)
      return Type.pointer_type.compare(paramType); 
    j = Type.pointer_type.compare(paramType);
    i = b;
    if (j != 0) {
      i = b;
      if (j != -1)
        return -2; 
    } 
    return i;
  }
  
  public void emitCoerceFromObject(CodeAttr paramCodeAttr) {
    paramCodeAttr.emitInvokeStatic(ClassType.make("gnu.kawa.reflect.SingletonType").getDeclaredMethod("coerceToSingleton", 1));
  }
  
  public Type getImplementationType() {
    return (Type)Type.pointer_type;
  }
  
  public Class getReflectClass() {
    return getImplementationType().getReflectClass();
  }
  
  public boolean isInstance(Object paramObject) {
    return (paramObject != null && !(paramObject instanceof Values));
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/gnu/kawa/reflect/SingletonType.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */