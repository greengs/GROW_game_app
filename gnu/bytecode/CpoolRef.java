package gnu.bytecode;

import java.io.DataOutputStream;
import java.io.IOException;

public class CpoolRef extends CpoolEntry {
  CpoolClass clas;
  
  CpoolNameAndType nameAndType;
  
  int tag;
  
  CpoolRef(int paramInt) {
    this.tag = paramInt;
  }
  
  CpoolRef(ConstantPool paramConstantPool, int paramInt1, int paramInt2, CpoolClass paramCpoolClass, CpoolNameAndType paramCpoolNameAndType) {
    super(paramConstantPool, paramInt1);
    this.tag = paramInt2;
    this.clas = paramCpoolClass;
    this.nameAndType = paramCpoolNameAndType;
  }
  
  static final int hashCode(CpoolClass paramCpoolClass, CpoolNameAndType paramCpoolNameAndType) {
    return paramCpoolClass.hashCode() ^ paramCpoolNameAndType.hashCode();
  }
  
  public final CpoolClass getCpoolClass() {
    return this.clas;
  }
  
  public final CpoolNameAndType getNameAndType() {
    return this.nameAndType;
  }
  
  public int getTag() {
    return this.tag;
  }
  
  public int hashCode() {
    if (this.hash == 0)
      this.hash = hashCode(this.clas, this.nameAndType); 
    return this.hash;
  }
  
  public void print(ClassTypeWriter paramClassTypeWriter, int paramInt) {
    // Byte code:
    //   0: aload_0
    //   1: getfield tag : I
    //   4: tableswitch default -> 32, 9 -> 104, 10 -> 110, 11 -> 116
    //   32: ldc '<Unknown>Ref'
    //   34: astore_3
    //   35: iload_2
    //   36: ifle -> 63
    //   39: aload_1
    //   40: aload_3
    //   41: invokevirtual print : (Ljava/lang/String;)V
    //   44: iload_2
    //   45: iconst_2
    //   46: if_icmpne -> 122
    //   49: aload_1
    //   50: ldc ' class: '
    //   52: invokevirtual print : (Ljava/lang/String;)V
    //   55: aload_1
    //   56: aload_0
    //   57: getfield clas : Lgnu/bytecode/CpoolClass;
    //   60: invokevirtual printOptionalIndex : (Lgnu/bytecode/CpoolEntry;)V
    //   63: aload_0
    //   64: getfield clas : Lgnu/bytecode/CpoolClass;
    //   67: aload_1
    //   68: iconst_0
    //   69: invokevirtual print : (Lgnu/bytecode/ClassTypeWriter;I)V
    //   72: iload_2
    //   73: iconst_2
    //   74: if_icmpge -> 131
    //   77: aload_1
    //   78: bipush #46
    //   80: invokevirtual print : (C)V
    //   83: aload_0
    //   84: getfield nameAndType : Lgnu/bytecode/CpoolNameAndType;
    //   87: aload_1
    //   88: iconst_0
    //   89: invokevirtual print : (Lgnu/bytecode/ClassTypeWriter;I)V
    //   92: iload_2
    //   93: iconst_2
    //   94: if_icmpne -> 103
    //   97: aload_1
    //   98: bipush #62
    //   100: invokevirtual print : (C)V
    //   103: return
    //   104: ldc 'Field'
    //   106: astore_3
    //   107: goto -> 35
    //   110: ldc 'Method'
    //   112: astore_3
    //   113: goto -> 35
    //   116: ldc 'InterfaceMethod'
    //   118: astore_3
    //   119: goto -> 35
    //   122: aload_1
    //   123: bipush #32
    //   125: invokevirtual print : (C)V
    //   128: goto -> 63
    //   131: aload_1
    //   132: ldc ' name_and_type: '
    //   134: invokevirtual print : (Ljava/lang/String;)V
    //   137: aload_1
    //   138: aload_0
    //   139: getfield nameAndType : Lgnu/bytecode/CpoolNameAndType;
    //   142: invokevirtual printOptionalIndex : (Lgnu/bytecode/CpoolEntry;)V
    //   145: aload_1
    //   146: bipush #60
    //   148: invokevirtual print : (C)V
    //   151: goto -> 83
  }
  
  void write(DataOutputStream paramDataOutputStream) throws IOException {
    paramDataOutputStream.writeByte(this.tag);
    paramDataOutputStream.writeShort(this.clas.index);
    paramDataOutputStream.writeShort(this.nameAndType.index);
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/gnu/bytecode/CpoolRef.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */