package gnu.bytecode;

import java.util.List;
import java.util.Vector;

public class ObjectType extends Type {
  static final int ADD_ENCLOSING_DONE = 8;
  
  static final int ADD_FIELDS_DONE = 1;
  
  static final int ADD_MEMBERCLASSES_DONE = 4;
  
  static final int ADD_METHODS_DONE = 2;
  
  static final int EXISTING_CLASS = 16;
  
  static final int HAS_OUTER_LINK = 32;
  
  public int flags;
  
  protected ObjectType() {
    this.size = 4;
  }
  
  public ObjectType(String paramString) {
    this.this_name = paramString;
    this.size = 4;
  }
  
  public static Class getContextClass(String paramString) throws ClassNotFoundException {
    try {
      return Class.forName(paramString, false, ObjectType.class.getClassLoader());
    } catch (ClassNotFoundException classNotFoundException) {
      try {
        return Class.forName(paramString, false, getThreadContextClassLoader());
      } catch (ClassNotFoundException classNotFoundException1) {
        return Class.forName(paramString, false, getContextClassLoader());
      } 
    } 
  }
  
  public static ClassLoader getContextClassLoader() {
    try {
      return ClassLoader.getSystemClassLoader();
    } catch (SecurityException securityException) {
      return ObjectType.class.getClassLoader();
    } 
  }
  
  public static ClassLoader getThreadContextClassLoader() {
    try {
      return Thread.currentThread().getContextClassLoader();
    } catch (SecurityException securityException) {
      return ObjectType.class.getClassLoader();
    } 
  }
  
  public Object coerceFromObject(Object paramObject) {
    Object object = paramObject;
    if (paramObject != null) {
      if (this == Type.toStringType)
        return paramObject.toString(); 
    } else {
      return object;
    } 
    Class clazz = getReflectClass();
    Class<?> clazz1 = paramObject.getClass();
    object = paramObject;
    if (!clazz.isAssignableFrom(clazz1))
      throw new ClassCastException("don't know how to coerce " + clazz1.getName() + " to " + getName()); 
    return object;
  }
  
  public int compare(Type paramType) {
    return (paramType == nullType) ? 0 : -1;
  }
  
  public void emitCoerceFromObject(CodeAttr paramCodeAttr) {
    if (this == Type.toStringType) {
      paramCodeAttr.emitDup();
      paramCodeAttr.emitIfNull();
      paramCodeAttr.emitPop(1);
      paramCodeAttr.emitPushNull();
      paramCodeAttr.emitElse();
      paramCodeAttr.emitInvokeVirtual(Type.toString_method);
      paramCodeAttr.emitFi();
      return;
    } 
    if (this != Type.objectType) {
      paramCodeAttr.emitCheckcast(this);
      return;
    } 
  }
  
  public Field getField(String paramString, int paramInt) {
    return null;
  }
  
  public Type getImplementationType() {
    if (this == nullType)
      return objectType; 
    ObjectType objectType = this;
    return (this == toStringType) ? javalangStringType : objectType;
  }
  
  public String getInternalName() {
    return getName().replace('.', '/');
  }
  
  public Method getMethod(String paramString, Type[] paramArrayOfType) {
    return Type.objectType.getMethod(paramString, paramArrayOfType);
  }
  
  public int getMethods(Filter paramFilter, int paramInt, List<Method> paramList) {
    return Type.objectType.getMethods(paramFilter, paramInt, paramList);
  }
  
  public final int getMethods(Filter paramFilter, int paramInt, Vector paramVector, String paramString) {
    return Type.objectType.getMethods(paramFilter, paramInt, paramVector, paramString);
  }
  
  public Class getReflectClass() {
    try {
      if (this.reflectClass == null)
        this.reflectClass = getContextClass(getInternalName().replace('/', '.')); 
      this.flags |= 0x10;
    } catch (ClassNotFoundException classNotFoundException) {}
    return this.reflectClass;
  }
  
  public final boolean isExisting() {
    boolean bool = false;
    Type type2 = getImplementationType();
    Type type1 = type2;
    if (type2 instanceof ArrayType)
      type1 = ((ArrayType)type2).getComponentType(); 
    if (type1 == this)
      return ((this.flags & 0x10) != 0); 
    if (!(type1 instanceof ObjectType) || ((ObjectType)type1).isExisting())
      bool = true; 
    return bool;
  }
  
  public boolean isInstance(Object paramObject) {
    return (this == nullType) ? ((paramObject == null)) : super.isInstance(paramObject);
  }
  
  public Type promote() {
    ObjectType objectType = this;
    if (this == nullType)
      objectType = objectType; 
    return objectType;
  }
  
  public final void setExisting(boolean paramBoolean) {
    if (paramBoolean) {
      this.flags |= 0x10;
      return;
    } 
    this.flags &= 0xFFFFFFEF;
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/gnu/bytecode/ObjectType.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */