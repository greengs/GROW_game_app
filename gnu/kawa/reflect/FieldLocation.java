package gnu.kawa.reflect;

import gnu.bytecode.ClassType;
import gnu.bytecode.Field;
import gnu.bytecode.Type;
import gnu.expr.Compilation;
import gnu.expr.Declaration;
import gnu.mapping.Location;
import gnu.mapping.WrappedException;
import java.lang.reflect.Field;

public class FieldLocation extends ClassMemberLocation {
  static final int CONSTANT = 4;
  
  static final int INDIRECT_LOCATION = 2;
  
  public static final int KIND_FLAGS_SET = 64;
  
  public static final int PROCEDURE = 16;
  
  static final int SETUP_DONE = 1;
  
  public static final int SYNTAX = 32;
  
  static final int VALUE_SET = 8;
  
  Declaration decl;
  
  private int flags;
  
  Object value;
  
  public FieldLocation(Object paramObject, ClassType paramClassType, String paramString) {
    super(paramObject, paramClassType, paramString);
  }
  
  public FieldLocation(Object paramObject, String paramString1, String paramString2) {
    super(paramObject, ClassType.make(paramString1), paramString2);
  }
  
  public FieldLocation(Object paramObject, Field paramField) {
    super(paramObject, paramField);
    this.type = (ClassType)Type.make(paramField.getDeclaringClass());
  }
  
  private Object getFieldValue() {
    super.setup();
    try {
      return this.rfield.get(this.instance);
    } catch (Throwable throwable) {
      throw WrappedException.wrapIfNeeded(throwable);
    } 
  }
  
  public static FieldLocation make(Object paramObject, Declaration paramDeclaration) {
    Field field = paramDeclaration.field;
    paramObject = new FieldLocation(paramObject, field.getDeclaringClass(), field.getName());
    paramObject.setDeclaration(paramDeclaration);
    return (FieldLocation)paramObject;
  }
  
  public static FieldLocation make(Object paramObject, String paramString1, String paramString2) {
    return new FieldLocation(paramObject, ClassType.make(paramString1), paramString2);
  }
  
  public Object get(Object paramObject) {
    try {
      setup();
      if ((this.flags & 0x8) != 0) {
        Object object = this.value;
        object1 = object;
        if ((this.flags & 0x4) != 0)
          return object; 
      } else {
        Object object = getFieldValue();
        object1 = object;
        if ((this.type.getDeclaredField(this.mname).getModifiers() & 0x10) != 0) {
          this.flags |= 0x8;
          if ((this.flags & 0x2) == 0)
            this.flags |= 0x4; 
          this.value = object;
          object1 = object;
        } 
      } 
    } catch (Throwable object1) {
      return paramObject;
    } 
    Object object2 = object1;
    if ((this.flags & 0x2) != 0) {
      object2 = Location.UNBOUND;
      Location location = (Location)object1;
      object1 = location.get(object2);
      if (object1 == object2)
        return paramObject; 
      object2 = object1;
      if (location.isConstant()) {
        this.flags |= 0x4;
        this.value = object1;
        return object1;
      } 
    } 
    return object2;
  }
  
  public Declaration getDeclaration() {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield flags : I
    //   6: bipush #64
    //   8: iand
    //   9: ifne -> 16
    //   12: aload_0
    //   13: invokevirtual setKindFlags : ()V
    //   16: aload_0
    //   17: getfield decl : Lgnu/expr/Declaration;
    //   20: astore_2
    //   21: aload_2
    //   22: astore_1
    //   23: aload_2
    //   24: ifnonnull -> 138
    //   27: aload_0
    //   28: invokevirtual getMemberName : ()Ljava/lang/String;
    //   31: astore_2
    //   32: aload_0
    //   33: invokevirtual getDeclaringClass : ()Lgnu/bytecode/ClassType;
    //   36: astore_1
    //   37: aload_1
    //   38: aload_2
    //   39: invokevirtual getDeclaredField : (Ljava/lang/String;)Lgnu/bytecode/Field;
    //   42: astore_3
    //   43: aload_3
    //   44: ifnonnull -> 53
    //   47: aconst_null
    //   48: astore_1
    //   49: aload_0
    //   50: monitorexit
    //   51: aload_1
    //   52: areturn
    //   53: aload_1
    //   54: invokestatic find : (Lgnu/bytecode/ClassType;)Lgnu/expr/ModuleInfo;
    //   57: invokevirtual getModuleExp : ()Lgnu/expr/ModuleExp;
    //   60: invokevirtual firstDecl : ()Lgnu/expr/Declaration;
    //   63: astore_1
    //   64: aload_1
    //   65: ifnull -> 89
    //   68: aload_1
    //   69: getfield field : Lgnu/bytecode/Field;
    //   72: ifnull -> 125
    //   75: aload_1
    //   76: getfield field : Lgnu/bytecode/Field;
    //   79: invokevirtual getName : ()Ljava/lang/String;
    //   82: aload_2
    //   83: invokevirtual equals : (Ljava/lang/Object;)Z
    //   86: ifeq -> 125
    //   89: aload_1
    //   90: ifnonnull -> 133
    //   93: new java/lang/RuntimeException
    //   96: dup
    //   97: new java/lang/StringBuilder
    //   100: dup
    //   101: invokespecial <init> : ()V
    //   104: ldc 'no field found for '
    //   106: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   109: aload_0
    //   110: invokevirtual append : (Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   113: invokevirtual toString : ()Ljava/lang/String;
    //   116: invokespecial <init> : (Ljava/lang/String;)V
    //   119: athrow
    //   120: astore_1
    //   121: aload_0
    //   122: monitorexit
    //   123: aload_1
    //   124: athrow
    //   125: aload_1
    //   126: invokevirtual nextDecl : ()Lgnu/expr/Declaration;
    //   129: astore_1
    //   130: goto -> 64
    //   133: aload_0
    //   134: aload_1
    //   135: putfield decl : Lgnu/expr/Declaration;
    //   138: goto -> 49
    // Exception table:
    //   from	to	target	type
    //   2	16	120	finally
    //   16	21	120	finally
    //   27	43	120	finally
    //   53	64	120	finally
    //   68	89	120	finally
    //   93	120	120	finally
    //   125	130	120	finally
    //   133	138	120	finally
  }
  
  public Type getFType() {
    return this.type.getDeclaredField(this.mname).getType();
  }
  
  public Field getField() {
    return this.type.getDeclaredField(this.mname);
  }
  
  public boolean isBound() {
    if ((this.flags & 0x40) == 0)
      setKindFlags(); 
    if ((this.flags & 0x4) != 0 || (this.flags & 0x2) == 0)
      return true; 
    if ((this.flags & 0x8) != 0) {
      Object object = this.value;
      return ((Location)object).isBound();
    } 
    try {
      setup();
      Object object = getFieldValue();
      this.flags |= 0x8;
      this.value = object;
      return ((Location)object).isBound();
    } catch (Throwable throwable) {
      return false;
    } 
  }
  
  public boolean isConstant() {
    boolean bool = false;
    if ((this.flags & 0x40) == 0)
      setKindFlags(); 
    if ((this.flags & 0x4) != 0)
      return true; 
    if (isIndirectLocation()) {
      if ((this.flags & 0x8) != 0) {
        Object object = this.value;
        return ((Location)object).isConstant();
      } 
      try {
        setup();
        Object object = getFieldValue();
        this.flags |= 0x8;
        this.value = object;
        return ((Location)object).isConstant();
      } catch (Throwable throwable) {
        return false;
      } 
    } 
    return bool;
  }
  
  public boolean isIndirectLocation() {
    return ((this.flags & 0x2) != 0);
  }
  
  public boolean isProcedureOrSyntax() {
    if ((this.flags & 0x40) == 0)
      setKindFlags(); 
    return ((this.flags & 0x30) != 0);
  }
  
  public void set(Object paramObject) {
    Object object;
    setup();
    if ((this.flags & 0x2) == 0)
      try {
        this.rfield.set(this.instance, paramObject);
        return;
      } catch (Throwable throwable) {
        throw WrappedException.wrapIfNeeded(throwable);
      }  
    if ((this.flags & 0x8) != 0) {
      object = this.value;
    } else {
      this.flags |= 0x8;
      object = getFieldValue();
      this.value = object;
    } 
    ((Location)object).set(throwable);
  }
  
  public void setDeclaration(Declaration paramDeclaration) {
    this.decl = paramDeclaration;
  }
  
  void setKindFlags() {
    String str = getMemberName();
    Field field = getDeclaringClass().getDeclaredField(str);
    int i = field.getModifiers();
    Type type = field.getType();
    if (type.isSubtype((Type)Compilation.typeLocation))
      this.flags |= 0x2; 
    if ((i & 0x10) != 0)
      if ((this.flags & 0x2) == 0) {
        this.flags |= 0x4;
        if (type.isSubtype((Type)Compilation.typeProcedure))
          this.flags |= 0x10; 
        if (type instanceof ClassType && ((ClassType)type).isSubclass("kawa.lang.Syntax"))
          this.flags |= 0x20; 
      } else {
        Location location = (Location)getFieldValue();
        if (location instanceof FieldLocation) {
          location = location;
          if ((((FieldLocation)location).flags & 0x40) == 0)
            location.setKindFlags(); 
          this.flags |= ((FieldLocation)location).flags & 0x34;
          if ((((FieldLocation)location).flags & 0x4) != 0) {
            if ((((FieldLocation)location).flags & 0x8) != 0) {
              this.value = ((FieldLocation)location).value;
              this.flags |= 0x8;
            } 
          } else {
            this.value = location;
            this.flags |= 0x8;
          } 
        } else if (location.isConstant()) {
          Object object = location.get(null);
          if (object instanceof gnu.mapping.Procedure)
            this.flags |= 0x10; 
          if (object instanceof kawa.lang.Syntax)
            this.flags |= 0x20; 
          this.flags |= 0xC;
          this.value = object;
        } 
      }  
    this.flags |= 0x40;
  }
  
  public void setProcedure() {
    this.flags |= 0x54;
  }
  
  public void setRestore(Object paramObject) {
    if ((this.flags & 0x2) == 0) {
      super.setRestore(paramObject);
      return;
    } 
    ((Location)this.value).setRestore(paramObject);
  }
  
  public void setSyntax() {
    this.flags |= 0x64;
  }
  
  public Object setWithSave(Object paramObject) {
    if ((this.flags & 0x40) == 0)
      setKindFlags(); 
    if ((this.flags & 0x2) == 0)
      return super.setWithSave(paramObject); 
    if ((this.flags & 0x8) != 0) {
      Object object1 = this.value;
      return ((Location)object1).setWithSave(paramObject);
    } 
    this.flags |= 0x8;
    Object object = getFieldValue();
    this.value = object;
    return ((Location)object).setWithSave(paramObject);
  }
  
  void setup() {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield flags : I
    //   6: iconst_1
    //   7: iand
    //   8: ifeq -> 14
    //   11: aload_0
    //   12: monitorexit
    //   13: return
    //   14: aload_0
    //   15: invokespecial setup : ()V
    //   18: aload_0
    //   19: getfield flags : I
    //   22: bipush #64
    //   24: iand
    //   25: ifne -> 32
    //   28: aload_0
    //   29: invokevirtual setKindFlags : ()V
    //   32: aload_0
    //   33: aload_0
    //   34: getfield flags : I
    //   37: iconst_1
    //   38: ior
    //   39: putfield flags : I
    //   42: aload_0
    //   43: monitorexit
    //   44: return
    //   45: astore_1
    //   46: aload_0
    //   47: monitorexit
    //   48: aload_1
    //   49: athrow
    // Exception table:
    //   from	to	target	type
    //   2	13	45	finally
    //   14	32	45	finally
    //   32	44	45	finally
    //   46	48	45	finally
  }
  
  public String toString() {
    StringBuffer stringBuffer = new StringBuffer();
    stringBuffer.append("FieldLocation[");
    if (this.instance != null) {
      stringBuffer.append(this.instance);
      stringBuffer.append(' ');
    } 
    if (this.type == null) {
      String str1 = "(null)";
      stringBuffer.append(str1);
      stringBuffer.append('.');
      stringBuffer.append(this.mname);
      stringBuffer.append(']');
      return stringBuffer.toString();
    } 
    String str = this.type.getName();
    stringBuffer.append(str);
    stringBuffer.append('.');
    stringBuffer.append(this.mname);
    stringBuffer.append(']');
    return stringBuffer.toString();
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/gnu/kawa/reflect/FieldLocation.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */