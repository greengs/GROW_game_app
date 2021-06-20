package gnu.bytecode;

import java.io.DataOutputStream;
import java.io.IOException;

public class InnerClassesAttr extends Attribute {
  int count;
  
  short[] data;
  
  public InnerClassesAttr(ClassType paramClassType) {
    super("InnerClasses");
    addToFrontOf(paramClassType);
  }
  
  public InnerClassesAttr(short[] paramArrayOfshort, ClassType paramClassType) {
    this(paramClassType);
    this.count = (short)(paramArrayOfshort.length >> 2);
    this.data = paramArrayOfshort;
  }
  
  public static InnerClassesAttr getFirstInnerClasses(Attribute paramAttribute) {
    while (true) {
      if (paramAttribute == null || paramAttribute instanceof InnerClassesAttr)
        return (InnerClassesAttr)paramAttribute; 
      paramAttribute = paramAttribute.next;
    } 
  }
  
  void addClass(CpoolClass paramCpoolClass, ClassType paramClassType) {
    short s = 0;
    int i = this.count;
    this.count = i + 1;
    int j = i * 4;
    if (this.data == null) {
      this.data = new short[16];
    } else if (j >= this.data.length) {
      short[] arrayOfShort1 = new short[j * 2];
      System.arraycopy(this.data, 0, arrayOfShort1, 0, j);
      this.data = arrayOfShort1;
    } 
    ConstantPool constantPool = paramClassType.constants;
    ClassType classType2 = (ClassType)paramCpoolClass.getClassType();
    String str = classType2.getSimpleName();
    if (str == null || str.length() == 0) {
      i = 0;
    } else {
      i = (constantPool.addUtf8(str)).index;
    } 
    this.data[j] = (short)paramCpoolClass.index;
    ClassType classType1 = classType2.getDeclaringClass();
    short[] arrayOfShort = this.data;
    if (classType1 != null)
      s = (short)(constantPool.addClass(classType1)).index; 
    arrayOfShort[j + 1] = s;
    this.data[j + 2] = (short)i;
    i = classType2.getModifiers();
    this.data[j + 3] = (short)(i & 0xFFFFFFDF);
  }
  
  public void assignConstants(ClassType paramClassType) {
    super.assignConstants(paramClassType);
  }
  
  public int getLength() {
    return this.count * 8 + 2;
  }
  
  public void print(ClassTypeWriter paramClassTypeWriter) {
    ConstantPool constantPool;
    ClassType classType = (ClassType)this.container;
    if (this.data == null) {
      constantPool = null;
    } else {
      constantPool = classType.getConstants();
    } 
    paramClassTypeWriter.print("Attribute \"");
    paramClassTypeWriter.print(getName());
    paramClassTypeWriter.print("\", length:");
    paramClassTypeWriter.print(getLength());
    paramClassTypeWriter.print(", count: ");
    paramClassTypeWriter.println(this.count);
    for (int i = 0; i < this.count; i++) {
      int k;
      String str;
      ClassType classType1;
      CpoolClass cpoolClass;
      if (constantPool == null) {
        j = 0;
      } else {
        j = this.data[i * 4] & 0xFFFF;
      } 
      if (constantPool == null || j == 0) {
        cpoolClass = null;
      } else {
        cpoolClass = constantPool.getForcedClass(j);
      } 
      if (cpoolClass != null && cpoolClass.clas instanceof ClassType) {
        classType1 = (ClassType)cpoolClass.clas;
      } else {
        classType1 = null;
      } 
      paramClassTypeWriter.print(' ');
      if (j == 0 && classType1 != null) {
        k = classType1.getModifiers();
      } else {
        k = this.data[i * 4 + 3] & 0xFFFF;
      } 
      paramClassTypeWriter.print(Access.toString(k, 'I'));
      paramClassTypeWriter.print(' ');
      if (j == 0 && classType1 != null) {
        str = classType1.getSimpleName();
      } else {
        k = this.data[i * 4 + 2] & 0xFFFF;
        if (constantPool == null || k == 0) {
          str = "(Anonymous)";
        } else {
          paramClassTypeWriter.printOptionalIndex(k);
          str = ((CpoolUtf8)constantPool.getForced(k, 1)).string;
        } 
      } 
      paramClassTypeWriter.print(str);
      paramClassTypeWriter.print(" = ");
      if (cpoolClass != null) {
        str = cpoolClass.getClassName();
      } else {
        str = "(Unknown)";
      } 
      paramClassTypeWriter.print(str);
      paramClassTypeWriter.print("; ");
      if (j == 0 && classType1 != null) {
        String str1 = classType1.getName();
        j = str1.lastIndexOf('.');
        str = str1;
        if (j > 0)
          str = str1.substring(j + 1); 
        j = str.lastIndexOf('$') + 1;
        if (j < str.length()) {
          j = str.charAt(j);
          if (j >= 48 && j <= 57) {
            paramClassTypeWriter.print("not a member");
            continue;
          } 
        } 
        paramClassTypeWriter.print("member of ");
        paramClassTypeWriter.print(classType.getName());
        continue;
      } 
      int j = this.data[i * 4 + 1] & 0xFFFF;
      if (j == 0) {
        paramClassTypeWriter.print("not a member");
      } else {
        paramClassTypeWriter.print("member of ");
        paramClassTypeWriter.print(((CpoolClass)constantPool.getForced(j, 7)).getStringName());
      } 
      continue;
      paramClassTypeWriter.println();
    } 
  }
  
  public void write(DataOutputStream paramDataOutputStream) throws IOException {
    paramDataOutputStream.writeShort(this.count);
    for (int i = 0; i < this.count; i++) {
      paramDataOutputStream.writeShort(this.data[i * 4]);
      paramDataOutputStream.writeShort(this.data[i * 4 + 1]);
      paramDataOutputStream.writeShort(this.data[i * 4 + 2]);
      paramDataOutputStream.writeShort(this.data[i * 4 + 3]);
    } 
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/gnu/bytecode/InnerClassesAttr.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */