package gnu.bytecode;

import java.io.DataOutputStream;
import java.io.IOException;
import java.lang.reflect.Field;

public class Field extends Location implements AttrContainer, Member {
  Attribute attributes;
  
  int flags;
  
  Field next;
  
  ClassType owner;
  
  Field rfield;
  
  String sourceName;
  
  public Field(ClassType paramClassType) {
    if (paramClassType.last_field == null) {
      paramClassType.fields = this;
    } else {
      paramClassType.last_field.next = this;
    } 
    paramClassType.last_field = this;
    paramClassType.fields_count++;
    this.owner = paramClassType;
  }
  
  public static Field searchField(Field paramField, String paramString) {
    while (paramField != null) {
      if (paramField.getSourceName() == paramString)
        return paramField; 
      paramField = paramField.next;
    } 
    return null;
  }
  
  void assign_constants(ClassType paramClassType) {
    ConstantPool constantPool = paramClassType.constants;
    if (this.name_index == 0 && this.name != null)
      this.name_index = (constantPool.addUtf8(this.name)).index; 
    if (this.signature_index == 0 && this.type != null)
      this.signature_index = (constantPool.addUtf8(this.type.getSignature())).index; 
    Attribute.assignConstants(this, paramClassType);
  }
  
  public final Attribute getAttributes() {
    return this.attributes;
  }
  
  public final ClassType getDeclaringClass() {
    return this.owner;
  }
  
  public final int getFlags() {
    return this.flags;
  }
  
  public final int getModifiers() {
    return this.flags;
  }
  
  public final Field getNext() {
    return this.next;
  }
  
  public Field getReflectField() throws NoSuchFieldException {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield rfield : Ljava/lang/reflect/Field;
    //   6: ifnonnull -> 27
    //   9: aload_0
    //   10: aload_0
    //   11: getfield owner : Lgnu/bytecode/ClassType;
    //   14: invokevirtual getReflectClass : ()Ljava/lang/Class;
    //   17: aload_0
    //   18: invokevirtual getName : ()Ljava/lang/String;
    //   21: invokevirtual getDeclaredField : (Ljava/lang/String;)Ljava/lang/reflect/Field;
    //   24: putfield rfield : Ljava/lang/reflect/Field;
    //   27: aload_0
    //   28: getfield rfield : Ljava/lang/reflect/Field;
    //   31: astore_1
    //   32: aload_0
    //   33: monitorexit
    //   34: aload_1
    //   35: areturn
    //   36: astore_1
    //   37: aload_0
    //   38: monitorexit
    //   39: aload_1
    //   40: athrow
    // Exception table:
    //   from	to	target	type
    //   2	27	36	finally
    //   27	32	36	finally
  }
  
  public String getSourceName() {
    if (this.sourceName == null)
      this.sourceName = getName().intern(); 
    return this.sourceName;
  }
  
  public final boolean getStaticFlag() {
    return ((this.flags & 0x8) != 0);
  }
  
  public final void setAttributes(Attribute paramAttribute) {
    this.attributes = paramAttribute;
  }
  
  public final void setConstantValue(Object paramObject, ClassType paramClassType) {
    // Byte code:
    //   0: iconst_0
    //   1: istore_3
    //   2: aload_2
    //   3: getfield constants : Lgnu/bytecode/ConstantPool;
    //   6: astore #5
    //   8: aload #5
    //   10: astore #4
    //   12: aload #5
    //   14: ifnonnull -> 32
    //   17: new gnu/bytecode/ConstantPool
    //   20: dup
    //   21: invokespecial <init> : ()V
    //   24: astore #4
    //   26: aload_2
    //   27: aload #4
    //   29: putfield constants : Lgnu/bytecode/ConstantPool;
    //   32: aload_0
    //   33: invokevirtual getType : ()Lgnu/bytecode/Type;
    //   36: invokevirtual getSignature : ()Ljava/lang/String;
    //   39: iconst_0
    //   40: invokevirtual charAt : (I)C
    //   43: lookupswitch default -> 116, 66 -> 184, 67 -> 161, 68 -> 232, 70 -> 216, 73 -> 184, 74 -> 200, 83 -> 184, 90 -> 142
    //   116: aload #4
    //   118: aload_1
    //   119: invokevirtual toString : ()Ljava/lang/String;
    //   122: invokevirtual addString : (Ljava/lang/String;)Lgnu/bytecode/CpoolString;
    //   125: astore_1
    //   126: new gnu/bytecode/ConstantValueAttr
    //   129: dup
    //   130: aload_1
    //   131: invokevirtual getIndex : ()I
    //   134: invokespecial <init> : (I)V
    //   137: aload_0
    //   138: invokevirtual addToFrontOf : (Lgnu/bytecode/AttrContainer;)V
    //   141: return
    //   142: aload_1
    //   143: invokestatic booleanValue : (Ljava/lang/Object;)Z
    //   146: ifeq -> 151
    //   149: iconst_1
    //   150: istore_3
    //   151: aload #4
    //   153: iload_3
    //   154: invokevirtual addInt : (I)Lgnu/bytecode/CpoolValue1;
    //   157: astore_1
    //   158: goto -> 126
    //   161: aload_1
    //   162: instanceof java/lang/Character
    //   165: ifeq -> 184
    //   168: aload #4
    //   170: aload_1
    //   171: checkcast java/lang/Character
    //   174: invokevirtual charValue : ()C
    //   177: invokevirtual addInt : (I)Lgnu/bytecode/CpoolValue1;
    //   180: astore_1
    //   181: goto -> 126
    //   184: aload #4
    //   186: aload_1
    //   187: checkcast java/lang/Number
    //   190: invokevirtual intValue : ()I
    //   193: invokevirtual addInt : (I)Lgnu/bytecode/CpoolValue1;
    //   196: astore_1
    //   197: goto -> 126
    //   200: aload #4
    //   202: aload_1
    //   203: checkcast java/lang/Number
    //   206: invokevirtual longValue : ()J
    //   209: invokevirtual addLong : (J)Lgnu/bytecode/CpoolValue2;
    //   212: astore_1
    //   213: goto -> 126
    //   216: aload #4
    //   218: aload_1
    //   219: checkcast java/lang/Number
    //   222: invokevirtual floatValue : ()F
    //   225: invokevirtual addFloat : (F)Lgnu/bytecode/CpoolValue1;
    //   228: astore_1
    //   229: goto -> 126
    //   232: aload #4
    //   234: aload_1
    //   235: checkcast java/lang/Number
    //   238: invokevirtual doubleValue : ()D
    //   241: invokevirtual addDouble : (D)Lgnu/bytecode/CpoolValue2;
    //   244: astore_1
    //   245: goto -> 126
  }
  
  public void setSourceName(String paramString) {
    this.sourceName = paramString;
  }
  
  public final void setStaticFlag(boolean paramBoolean) {
    if (paramBoolean) {
      this.flags |= 0x8;
      return;
    } 
    this.flags ^= 0xFFFFFFF7;
  }
  
  public String toString() {
    StringBuffer stringBuffer = new StringBuffer(100);
    stringBuffer.append("Field:");
    stringBuffer.append(getDeclaringClass().getName());
    stringBuffer.append('.');
    stringBuffer.append(this.name);
    return stringBuffer.toString();
  }
  
  void write(DataOutputStream paramDataOutputStream, ClassType paramClassType) throws IOException {
    paramDataOutputStream.writeShort(this.flags);
    paramDataOutputStream.writeShort(this.name_index);
    paramDataOutputStream.writeShort(this.signature_index);
    Attribute.writeAll(this, paramDataOutputStream);
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/gnu/bytecode/Field.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */