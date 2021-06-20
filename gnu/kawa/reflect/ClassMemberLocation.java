package gnu.kawa.reflect;

import gnu.bytecode.ClassType;
import gnu.bytecode.Type;
import gnu.expr.Compilation;
import gnu.expr.Language;
import gnu.mapping.Environment;
import gnu.mapping.Location;
import gnu.mapping.Named;
import gnu.mapping.Symbol;
import gnu.mapping.UnboundLocationException;
import gnu.mapping.WrappedException;
import java.lang.reflect.Field;

public abstract class ClassMemberLocation extends Location {
  Object instance;
  
  String mname;
  
  Field rfield;
  
  ClassType type;
  
  public ClassMemberLocation(Object paramObject, ClassType paramClassType, String paramString) {
    this.instance = paramObject;
    this.type = paramClassType;
    this.mname = paramString;
  }
  
  public ClassMemberLocation(Object paramObject, Class paramClass, String paramString) {
    this.instance = paramObject;
    this.type = (ClassType)Type.make(paramClass);
    this.mname = paramString;
  }
  
  public ClassMemberLocation(Object paramObject, Field paramField) {
    this.instance = paramObject;
    this.rfield = paramField;
    this.mname = paramField.getName();
  }
  
  public static void define(Object paramObject, Field paramField, String paramString, Language paramLanguage, Environment paramEnvironment) throws IllegalAccessException {
    Object object1;
    boolean bool;
    Object object2;
    Object object3 = paramField.get(paramObject);
    Type type = Type.make(paramField.getType());
    boolean bool1 = type.isSubtype((Type)Compilation.typeLocation);
    type.isSubtype((Type)Compilation.typeProcedure);
    int i = paramField.getModifiers();
    if ((i & 0x10) != 0) {
      bool = true;
    } else {
      bool = false;
    } 
    if (bool && object3 instanceof Named && !bool1) {
      object2 = ((Named)object3).getSymbol();
    } else {
      object2 = Compilation.demangleName(paramField.getName(), true);
    } 
    if (object2 instanceof Symbol) {
      object2 = object2;
    } else {
      String str1 = paramString;
      if (paramString == null)
        str1 = ""; 
      object2 = Symbol.make(str1, object2.toString().intern());
    } 
    String str = null;
    paramString = null;
    if (bool1 && bool) {
      paramObject = object3;
    } else {
      paramString = str;
      if (bool)
        object1 = paramLanguage.getEnvPropertyFor(paramField, object3); 
      if ((i & 0x8) != 0) {
        bool = true;
      } else {
        bool = false;
      } 
      if (bool) {
        paramObject = new StaticFieldLocation(paramField);
      } else {
        paramObject = new FieldLocation(paramObject, paramField);
      } 
    } 
    paramEnvironment.addLocation((Symbol)object2, object1, (Location)paramObject);
  }
  
  public static void defineAll(Object paramObject, Language paramLanguage, Environment paramEnvironment) throws IllegalAccessException {
    Field[] arrayOfField = paramObject.getClass().getFields();
    int i = arrayOfField.length;
    while (true) {
      int j = i - 1;
      if (j >= 0) {
        Field field = arrayOfField[j];
        String str = field.getName();
        i = j;
        if (!str.startsWith("$Prvt$")) {
          i = j;
          if (!str.endsWith("$instance")) {
            define(paramObject, field, null, paramLanguage, paramEnvironment);
            i = j;
          } 
        } 
        continue;
      } 
      break;
    } 
  }
  
  public Object get(Object paramObject) {
    Field field = getRField();
    if (field == null)
      return paramObject; 
    try {
      return field.get(this.instance);
    } catch (IllegalAccessException illegalAccessException) {
      throw WrappedException.wrapIfNeeded(illegalAccessException);
    } 
  }
  
  public ClassType getDeclaringClass() {
    return this.type;
  }
  
  public final Object getInstance() {
    return this.instance;
  }
  
  public String getMemberName() {
    return this.mname;
  }
  
  public Class getRClass() {
    Field field = this.rfield;
    if (field != null)
      return field.getDeclaringClass(); 
    try {
      return this.type.getReflectClass();
    } catch (Exception exception) {
      return null;
    } 
  }
  
  public Field getRField() {
    Field field2 = this.rfield;
    Field field1 = field2;
    if (field2 == null)
      try {
        field1 = this.type.getReflectClass().getField(this.mname);
        this.rfield = field1;
        return field1;
      } catch (Exception exception) {
        return null;
      }  
    return (Field)exception;
  }
  
  public boolean isBound() {
    return (getRField() != null);
  }
  
  public boolean isConstant() {
    return (getRField() != null && (this.rfield.getModifiers() & 0x10) != 0);
  }
  
  public void set(Object paramObject) {
    setup();
    try {
      this.rfield.set(this.instance, paramObject);
      return;
    } catch (IllegalAccessException illegalAccessException) {
      throw WrappedException.wrapIfNeeded(illegalAccessException);
    } 
  }
  
  public final void setInstance(Object paramObject) {
    this.instance = paramObject;
  }
  
  void setup() {
    if (this.rfield == null)
      try {
        Class clazz = this.type.getReflectClass();
        try {
          this.rfield = clazz.getField(this.mname);
          return;
        } catch (NoSuchFieldException noSuchFieldException) {
          UnboundLocationException unboundLocationException = new UnboundLocationException(null, "Unbound location  - no field " + this.mname + " in " + this.type.getName());
          unboundLocationException.initCause(noSuchFieldException);
          throw unboundLocationException;
        } 
      } catch (RuntimeException runtimeException) {
        UnboundLocationException unboundLocationException = new UnboundLocationException(null, "Unbound location - " + runtimeException.toString());
        unboundLocationException.initCause(runtimeException);
        throw unboundLocationException;
      }  
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/gnu/kawa/reflect/ClassMemberLocation.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */