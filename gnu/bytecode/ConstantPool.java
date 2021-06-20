package gnu.bytecode;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class ConstantPool {
  public static final byte CLASS = 7;
  
  public static final byte DOUBLE = 6;
  
  public static final byte FIELDREF = 9;
  
  public static final byte FLOAT = 4;
  
  public static final byte INTEGER = 3;
  
  public static final byte INTERFACE_METHODREF = 11;
  
  public static final byte LONG = 5;
  
  public static final byte METHODREF = 10;
  
  public static final byte NAME_AND_TYPE = 12;
  
  public static final byte STRING = 8;
  
  public static final byte UTF8 = 1;
  
  int count;
  
  CpoolEntry[] hashTab;
  
  boolean locked;
  
  CpoolEntry[] pool;
  
  public ConstantPool() {}
  
  public ConstantPool(DataInputStream paramDataInputStream) throws IOException {
    this.count = paramDataInputStream.readUnsignedShort() - 1;
    this.pool = new CpoolEntry[this.count + 1];
    int i = 1;
    while (i <= this.count) {
      byte b = paramDataInputStream.readByte();
      CpoolEntry cpoolEntry = getForced(i, b);
      int j = i;
      switch (b) {
        case 2:
          i = j + 1;
          break;
        case 1:
          ((CpoolUtf8)cpoolEntry).string = paramDataInputStream.readUTF();
          j = i;
        case 3:
        case 4:
          ((CpoolValue1)cpoolEntry).value = paramDataInputStream.readInt();
          j = i;
        case 5:
        case 6:
          ((CpoolValue2)cpoolEntry).value = paramDataInputStream.readLong();
          j = i + 1;
        case 7:
          ((CpoolClass)cpoolEntry).name = (CpoolUtf8)getForced(paramDataInputStream.readUnsignedShort(), 1);
          j = i;
        case 8:
          ((CpoolString)cpoolEntry).str = (CpoolUtf8)getForced(paramDataInputStream.readUnsignedShort(), 1);
          j = i;
        case 9:
        case 10:
        case 11:
          cpoolEntry = cpoolEntry;
          ((CpoolRef)cpoolEntry).clas = getForcedClass(paramDataInputStream.readUnsignedShort());
          ((CpoolRef)cpoolEntry).nameAndType = (CpoolNameAndType)getForced(paramDataInputStream.readUnsignedShort(), 12);
          j = i;
        case 12:
          cpoolEntry = cpoolEntry;
          ((CpoolNameAndType)cpoolEntry).name = (CpoolUtf8)getForced(paramDataInputStream.readUnsignedShort(), 1);
          ((CpoolNameAndType)cpoolEntry).type = (CpoolUtf8)getForced(paramDataInputStream.readUnsignedShort(), 1);
          j = i;
      } 
    } 
  }
  
  public CpoolClass addClass(CpoolUtf8 paramCpoolUtf8) {
    int i = CpoolClass.hashCode(paramCpoolUtf8);
    if (this.hashTab == null)
      rehash(); 
    int j = this.hashTab.length;
    for (CpoolEntry cpoolEntry = this.hashTab[(Integer.MAX_VALUE & i) % j]; cpoolEntry != null; cpoolEntry = cpoolEntry.next) {
      if (i == cpoolEntry.hash && cpoolEntry instanceof CpoolClass) {
        CpoolClass cpoolClass = (CpoolClass)cpoolEntry;
        if (cpoolClass.name == paramCpoolUtf8)
          return cpoolClass; 
      } 
    } 
    return new CpoolClass(this, i, paramCpoolUtf8);
  }
  
  public CpoolClass addClass(ObjectType paramObjectType) {
    CpoolClass cpoolClass = addClass(addUtf8(paramObjectType.getInternalName()));
    cpoolClass.clas = paramObjectType;
    return cpoolClass;
  }
  
  public CpoolValue2 addDouble(double paramDouble) {
    return addValue2(6, Double.doubleToLongBits(paramDouble));
  }
  
  public CpoolRef addFieldRef(Field paramField) {
    return addRef(9, addClass(paramField.owner), addNameAndType(paramField));
  }
  
  public CpoolValue1 addFloat(float paramFloat) {
    return addValue1(4, Float.floatToIntBits(paramFloat));
  }
  
  public CpoolValue1 addInt(int paramInt) {
    return addValue1(3, paramInt);
  }
  
  public CpoolValue2 addLong(long paramLong) {
    return addValue2(5, paramLong);
  }
  
  public CpoolRef addMethodRef(Method paramMethod) {
    CpoolClass cpoolClass = addClass(paramMethod.classfile);
    if ((paramMethod.getDeclaringClass().getModifiers() & 0x200) == 0) {
      byte b1 = 10;
      return addRef(b1, cpoolClass, addNameAndType(paramMethod));
    } 
    byte b = 11;
    return addRef(b, cpoolClass, addNameAndType(paramMethod));
  }
  
  public CpoolNameAndType addNameAndType(CpoolUtf8 paramCpoolUtf81, CpoolUtf8 paramCpoolUtf82) {
    int i = CpoolNameAndType.hashCode(paramCpoolUtf81, paramCpoolUtf82);
    if (this.hashTab == null)
      rehash(); 
    int j = this.hashTab.length;
    for (CpoolEntry cpoolEntry = this.hashTab[(Integer.MAX_VALUE & i) % j]; cpoolEntry != null; cpoolEntry = cpoolEntry.next) {
      if (i == cpoolEntry.hash && cpoolEntry instanceof CpoolNameAndType && ((CpoolNameAndType)cpoolEntry).name == paramCpoolUtf81 && ((CpoolNameAndType)cpoolEntry).type == paramCpoolUtf82)
        return (CpoolNameAndType)cpoolEntry; 
    } 
    return new CpoolNameAndType(this, i, paramCpoolUtf81, paramCpoolUtf82);
  }
  
  public CpoolNameAndType addNameAndType(Field paramField) {
    return addNameAndType(addUtf8(paramField.getName()), addUtf8(paramField.getSignature()));
  }
  
  public CpoolNameAndType addNameAndType(Method paramMethod) {
    return addNameAndType(addUtf8(paramMethod.getName()), addUtf8(paramMethod.getSignature()));
  }
  
  public CpoolRef addRef(int paramInt, CpoolClass paramCpoolClass, CpoolNameAndType paramCpoolNameAndType) {
    int i = CpoolRef.hashCode(paramCpoolClass, paramCpoolNameAndType);
    if (this.hashTab == null)
      rehash(); 
    int j = this.hashTab.length;
    for (CpoolEntry cpoolEntry = this.hashTab[(Integer.MAX_VALUE & i) % j]; cpoolEntry != null; cpoolEntry = cpoolEntry.next) {
      if (i == cpoolEntry.hash && cpoolEntry instanceof CpoolRef) {
        CpoolRef cpoolRef = (CpoolRef)cpoolEntry;
        if (cpoolRef.tag == paramInt && cpoolRef.clas == paramCpoolClass && cpoolRef.nameAndType == paramCpoolNameAndType)
          return cpoolRef; 
      } 
    } 
    return new CpoolRef(this, i, paramInt, paramCpoolClass, paramCpoolNameAndType);
  }
  
  public CpoolString addString(CpoolUtf8 paramCpoolUtf8) {
    int i = CpoolString.hashCode(paramCpoolUtf8);
    if (this.hashTab == null)
      rehash(); 
    int j = this.hashTab.length;
    for (CpoolEntry cpoolEntry = this.hashTab[(Integer.MAX_VALUE & i) % j]; cpoolEntry != null; cpoolEntry = cpoolEntry.next) {
      if (i == cpoolEntry.hash && cpoolEntry instanceof CpoolString) {
        CpoolString cpoolString = (CpoolString)cpoolEntry;
        if (cpoolString.str == paramCpoolUtf8)
          return cpoolString; 
      } 
    } 
    return new CpoolString(this, i, paramCpoolUtf8);
  }
  
  public final CpoolString addString(String paramString) {
    return addString(addUtf8(paramString));
  }
  
  public CpoolUtf8 addUtf8(String paramString) {
    String str = paramString.intern();
    int i = str.hashCode();
    if (this.hashTab == null)
      rehash(); 
    int j = this.hashTab.length;
    for (CpoolEntry cpoolEntry = this.hashTab[(Integer.MAX_VALUE & i) % j]; cpoolEntry != null; cpoolEntry = cpoolEntry.next) {
      if (i == cpoolEntry.hash && cpoolEntry instanceof CpoolUtf8) {
        CpoolUtf8 cpoolUtf8 = (CpoolUtf8)cpoolEntry;
        if (cpoolUtf8.string == str)
          return cpoolUtf8; 
      } 
    } 
    if (this.locked)
      throw new Error("adding new Utf8 entry to locked contant pool: " + str); 
    return new CpoolUtf8(this, i, str);
  }
  
  CpoolValue1 addValue1(int paramInt1, int paramInt2) {
    int i = CpoolValue1.hashCode(paramInt2);
    if (this.hashTab == null)
      rehash(); 
    int j = this.hashTab.length;
    for (CpoolEntry cpoolEntry = this.hashTab[(Integer.MAX_VALUE & i) % j]; cpoolEntry != null; cpoolEntry = cpoolEntry.next) {
      if (i == cpoolEntry.hash && cpoolEntry instanceof CpoolValue1) {
        CpoolValue1 cpoolValue1 = (CpoolValue1)cpoolEntry;
        if (cpoolValue1.tag == paramInt1 && cpoolValue1.value == paramInt2)
          return cpoolValue1; 
      } 
    } 
    return new CpoolValue1(this, paramInt1, i, paramInt2);
  }
  
  CpoolValue2 addValue2(int paramInt, long paramLong) {
    int i = CpoolValue2.hashCode(paramLong);
    if (this.hashTab == null)
      rehash(); 
    int j = this.hashTab.length;
    for (CpoolEntry cpoolEntry = this.hashTab[(Integer.MAX_VALUE & i) % j]; cpoolEntry != null; cpoolEntry = cpoolEntry.next) {
      if (i == cpoolEntry.hash && cpoolEntry instanceof CpoolValue2) {
        CpoolValue2 cpoolValue2 = (CpoolValue2)cpoolEntry;
        if (cpoolValue2.tag == paramInt && cpoolValue2.value == paramLong)
          return cpoolValue2; 
      } 
    } 
    return new CpoolValue2(this, paramInt, i, paramLong);
  }
  
  public final int getCount() {
    return this.count;
  }
  
  CpoolEntry getForced(int paramInt1, int paramInt2) {
    paramInt1 &= 0xFFFF;
    CpoolEntry cpoolEntry2 = this.pool[paramInt1];
    if (cpoolEntry2 == null) {
      if (this.locked)
        throw new Error("adding new entry to locked contant pool"); 
      CpoolEntry cpoolEntry = cpoolEntry2;
      switch (paramInt2) {
        default:
          cpoolEntry = cpoolEntry2;
        case 2:
          this.pool[paramInt1] = cpoolEntry;
          cpoolEntry.index = paramInt1;
          return cpoolEntry;
        case 1:
          cpoolEntry = new CpoolUtf8();
        case 3:
        case 4:
          cpoolEntry = new CpoolValue1(paramInt2);
        case 5:
        case 6:
          cpoolEntry = new CpoolValue2(paramInt2);
        case 7:
          cpoolEntry = new CpoolClass();
        case 8:
          cpoolEntry = new CpoolString();
        case 9:
        case 10:
        case 11:
          cpoolEntry = new CpoolRef(paramInt2);
        case 12:
          break;
      } 
      cpoolEntry = new CpoolNameAndType();
    } 
    CpoolEntry cpoolEntry1 = cpoolEntry2;
    if (cpoolEntry2.getTag() != paramInt2)
      throw new ClassFormatError("conflicting constant pool tags at " + paramInt1); 
    return cpoolEntry1;
  }
  
  CpoolClass getForcedClass(int paramInt) {
    return (CpoolClass)getForced(paramInt, 7);
  }
  
  public final CpoolEntry getPoolEntry(int paramInt) {
    return this.pool[paramInt];
  }
  
  void rehash() {
    int i;
    if (this.hashTab == null && this.count > 0) {
      i = this.pool.length;
      while (true) {
        int j = i - 1;
        if (j >= 0) {
          CpoolEntry cpoolEntry = this.pool[j];
          i = j;
          if (cpoolEntry != null) {
            cpoolEntry.hashCode();
            i = j;
          } 
          continue;
        } 
        break;
      } 
    } 
    if (this.count < 5) {
      i = 101;
    } else {
      i = this.count * 2;
    } 
    this.hashTab = new CpoolEntry[i];
    if (this.pool != null) {
      i = this.pool.length;
      while (true) {
        int j = i - 1;
        if (j >= 0) {
          CpoolEntry cpoolEntry = this.pool[j];
          i = j;
          if (cpoolEntry != null) {
            cpoolEntry.add_hashed(this);
            i = j;
          } 
          continue;
        } 
        break;
      } 
    } 
  }
  
  void write(DataOutputStream paramDataOutputStream) throws IOException {
    paramDataOutputStream.writeShort(this.count + 1);
    for (int i = 1; i <= this.count; i++) {
      CpoolEntry cpoolEntry = this.pool[i];
      if (cpoolEntry != null)
        cpoolEntry.write(paramDataOutputStream); 
    } 
    this.locked = true;
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/gnu/bytecode/ConstantPool.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */