package gnu.bytecode;

import java.io.DataInputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;

public class ClassFileInput extends DataInputStream {
  ClassType ctype;
  
  InputStream str;
  
  public ClassFileInput(ClassType paramClassType, InputStream paramInputStream) throws IOException, ClassFormatError {
    super(paramInputStream);
    this.ctype = paramClassType;
    if (!readHeader())
      throw new ClassFormatError("invalid magic number"); 
    paramClassType.constants = readConstants();
    readClassInfo();
    readFields();
    readMethods();
    readAttributes(paramClassType);
  }
  
  public ClassFileInput(InputStream paramInputStream) throws IOException {
    super(paramInputStream);
  }
  
  public static ClassType readClassType(InputStream paramInputStream) throws IOException, ClassFormatError {
    ClassType classType = new ClassType();
    new ClassFileInput(classType, paramInputStream);
    return classType;
  }
  
  CpoolClass getClassConstant(int paramInt) {
    return (CpoolClass)this.ctype.constants.getForced(paramInt, 7);
  }
  
  public Attribute readAttribute(String paramString, int paramInt, AttrContainer paramAttrContainer) throws IOException {
    CodeAttr codeAttr;
    short[] arrayOfShort2;
    Scope scope1;
    byte[] arrayOfByte1;
    short[] arrayOfShort1;
    Method method;
    SourceDebugExtAttr sourceDebugExtAttr;
    byte[] arrayOfByte3;
    Scope scope2;
    short[] arrayOfShort3;
    if (paramString == "SourceFile" && paramAttrContainer instanceof ClassType)
      return new SourceFileAttr(readUnsignedShort(), (ClassType)paramAttrContainer); 
    if (paramString == "Code" && paramAttrContainer instanceof Method) {
      codeAttr = new CodeAttr((Method)paramAttrContainer);
      codeAttr.fixup_count = -1;
      codeAttr.setMaxStack(readUnsignedShort());
      codeAttr.setMaxLocals(readUnsignedShort());
      arrayOfByte3 = new byte[readInt()];
      readFully(arrayOfByte3);
      codeAttr.setCode(arrayOfByte3);
      int i = readUnsignedShort();
      for (paramInt = 0; paramInt < i; paramInt++)
        codeAttr.addHandler(readUnsignedShort(), readUnsignedShort(), readUnsignedShort(), readUnsignedShort()); 
      readAttributes(codeAttr);
      return codeAttr;
    } 
    if (codeAttr == "LineNumberTable" && arrayOfByte3 instanceof CodeAttr) {
      int i = readUnsignedShort() * 2;
      arrayOfShort2 = new short[i];
      for (paramInt = 0; paramInt < i; paramInt++)
        arrayOfShort2[paramInt] = readShort(); 
      return new LineNumbersAttr(arrayOfShort2, (CodeAttr)arrayOfByte3);
    } 
    if (arrayOfShort2 == "LocalVariableTable" && arrayOfByte3 instanceof CodeAttr) {
      CodeAttr codeAttr1;
      Object object;
      CodeAttr codeAttr2 = (CodeAttr)arrayOfByte3;
      LocalVarsAttr localVarsAttr = new LocalVarsAttr(codeAttr2);
      Method method1 = localVarsAttr.getMethod();
      if (localVarsAttr.parameter_scope == null)
        localVarsAttr.parameter_scope = method1.pushScope(); 
      Scope scope = localVarsAttr.parameter_scope;
      if (scope.end == null)
        scope.end = new Label(codeAttr2.PC); 
      ConstantPool constantPool = method1.getConstants();
      int k = readUnsignedShort();
      int j = scope.start.position;
      int i = scope.end.position;
      paramInt = 0;
      while (true) {
        LocalVarsAttr localVarsAttr1 = localVarsAttr;
        if (paramInt < k) {
          Variable variable = new Variable();
          int i1 = readUnsignedShort();
          int n = i1 + readUnsignedShort();
          CodeAttr codeAttr3 = codeAttr1;
          if (i1 == j) {
            Object object1 = object;
            codeAttr3 = codeAttr1;
            if (n != object) {
              codeAttr3 = codeAttr1;
            } else {
              continue;
            } 
          } 
          while (((Scope)codeAttr3).parent != null && (i1 < ((Scope)codeAttr3).start.position || n > ((Scope)codeAttr3).end.position))
            scope2 = ((Scope)codeAttr3).parent; 
          scope1 = new Scope(new Label(i1), new Label(n));
          scope1.linkChild(scope2);
          j = i1;
          int m = n;
          scope2 = scope1;
          continue;
        } 
        return (Attribute)scope2;
        codeAttr2.addVariable((Variable)SYNTHETIC_LOCAL_VARIABLE_12);
        SYNTHETIC_LOCAL_VARIABLE_12.setName(readUnsignedShort(), constantPool);
        SYNTHETIC_LOCAL_VARIABLE_12.setSignature(readUnsignedShort(), constantPool);
        ((Variable)SYNTHETIC_LOCAL_VARIABLE_12).offset = readUnsignedShort();
        paramInt++;
        object = SYNTHETIC_LOCAL_VARIABLE_6;
        codeAttr1 = codeAttr2;
      } 
    } 
    if (scope1 == "Signature" && scope2 instanceof Member)
      return new SignatureAttr(readUnsignedShort(), (Member)scope2); 
    if (scope1 == "StackMapTable" && scope2 instanceof CodeAttr) {
      arrayOfByte1 = new byte[paramInt];
      readFully(arrayOfByte1, 0, paramInt);
      return new StackMapTableAttr(arrayOfByte1, (CodeAttr)scope2);
    } 
    if ((arrayOfByte1 == "RuntimeVisibleAnnotations" || arrayOfByte1 == "RuntimeInvisibleAnnotations") && (scope2 instanceof Field || scope2 instanceof Method || scope2 instanceof ClassType)) {
      byte[] arrayOfByte = new byte[paramInt];
      readFully(arrayOfByte, 0, paramInt);
      return new RuntimeAnnotationsAttr((String)arrayOfByte1, arrayOfByte, (AttrContainer)scope2);
    } 
    if (arrayOfByte1 == "ConstantValue" && scope2 instanceof Field)
      return new ConstantValueAttr(readUnsignedShort()); 
    if (arrayOfByte1 == "InnerClasses" && scope2 instanceof ClassType) {
      int i = readUnsignedShort() * 4;
      arrayOfShort1 = new short[i];
      for (paramInt = 0; paramInt < i; paramInt++)
        arrayOfShort1[paramInt] = readShort(); 
      return new InnerClassesAttr(arrayOfShort1, (ClassType)scope2);
    } 
    if (arrayOfShort1 == "EnclosingMethod" && scope2 instanceof ClassType)
      return new EnclosingMethodAttr(readUnsignedShort(), readUnsignedShort(), (ClassType)scope2); 
    if (arrayOfShort1 == "Exceptions" && scope2 instanceof Method) {
      method = (Method)scope2;
      int i = readUnsignedShort();
      arrayOfShort3 = new short[i];
      for (paramInt = 0; paramInt < i; paramInt++)
        arrayOfShort3[paramInt] = readShort(); 
      method.setExceptions(arrayOfShort3);
      return method.getExceptionAttr();
    } 
    if (method == "SourceDebugExtension" && arrayOfShort3 instanceof ClassType) {
      sourceDebugExtAttr = new SourceDebugExtAttr((ClassType)arrayOfShort3);
      byte[] arrayOfByte = new byte[paramInt];
      readFully(arrayOfByte, 0, paramInt);
      sourceDebugExtAttr.data = arrayOfByte;
      sourceDebugExtAttr.dlength = paramInt;
      return sourceDebugExtAttr;
    } 
    byte[] arrayOfByte2 = new byte[paramInt];
    readFully(arrayOfByte2, 0, paramInt);
    return new MiscAttr((String)sourceDebugExtAttr, arrayOfByte2);
  }
  
  public int readAttributes(AttrContainer paramAttrContainer) throws IOException {
    int j = readUnsignedShort();
    Attribute attribute = paramAttrContainer.getAttributes();
    int i = 0;
    label26: while (i < j) {
      Attribute attribute1 = attribute;
      if (attribute != null)
        while (true) {
          attribute1 = attribute.getNext();
          if (attribute1 == null) {
            attribute1 = attribute;
            int k = readUnsignedShort();
            CpoolUtf8 cpoolUtf8 = (CpoolUtf8)this.ctype.constants.getForced(k, 1);
            int m = readInt();
            cpoolUtf8.intern();
            Attribute attribute3 = readAttribute(cpoolUtf8.string, m, paramAttrContainer);
            Attribute attribute2 = attribute1;
            if (attribute3 != null) {
              if (attribute3.getNameIndex() == 0)
                attribute3.setNameIndex(k); 
              if (attribute1 == null) {
                paramAttrContainer.setAttributes(attribute3);
              } else {
                if (paramAttrContainer.getAttributes() == attribute3) {
                  paramAttrContainer.setAttributes(attribute3.getNext());
                  attribute3.setNext(null);
                } 
                attribute1.setNext(attribute3);
              } 
              attribute2 = attribute3;
            } 
            i++;
            continue label26;
          } 
          attribute = attribute1;
        }  
      continue;
    } 
    return j;
  }
  
  public void readClassInfo() throws IOException {
    this.ctype.access_flags = readUnsignedShort();
    this.ctype.thisClassIndex = readUnsignedShort();
    String str = (getClassConstant(this.ctype.thisClassIndex)).name.string;
    this.ctype.this_name = str.replace('/', '.');
    this.ctype.setSignature("L" + str + ";");
    this.ctype.superClassIndex = readUnsignedShort();
    if (this.ctype.superClassIndex == 0) {
      this.ctype.setSuper((ClassType)null);
    } else {
      str = (getClassConstant(this.ctype.superClassIndex)).name.string;
      this.ctype.setSuper(str.replace('/', '.'));
    } 
    int i = readUnsignedShort();
    if (i > 0) {
      this.ctype.interfaces = new ClassType[i];
      this.ctype.interfaceIndexes = new int[i];
      for (int j = 0; j < i; j++) {
        int k = readUnsignedShort();
        this.ctype.interfaceIndexes[j] = k;
        str = ((CpoolClass)this.ctype.constants.getForced(k, 7)).name.string.replace('/', '.');
        this.ctype.interfaces[j] = ClassType.make(str);
      } 
    } 
  }
  
  public ConstantPool readConstants() throws IOException {
    return new ConstantPool(this);
  }
  
  public void readFields() throws IOException {
    int j = readUnsignedShort();
    ConstantPool constantPool = this.ctype.constants;
    for (int i = 0; i < j; i++) {
      int k = readUnsignedShort();
      int m = readUnsignedShort();
      int n = readUnsignedShort();
      Field field = this.ctype.addField();
      field.setName(m, constantPool);
      field.setSignature(n, constantPool);
      field.flags = k;
      readAttributes(field);
    } 
  }
  
  public void readFormatVersion() throws IOException {
    int i = readUnsignedShort();
    int j = readUnsignedShort();
    this.ctype.classfileFormatVersion = 65536 * j + i;
  }
  
  public boolean readHeader() throws IOException {
    if (readInt() != -889275714)
      return false; 
    readFormatVersion();
    return true;
  }
  
  public void readMethods() throws IOException {
    int j = readUnsignedShort();
    for (int i = 0; i < j; i++) {
      int k = readUnsignedShort();
      int m = readUnsignedShort();
      int n = readUnsignedShort();
      Method method = this.ctype.addMethod((String)null, k);
      method.setName(m);
      method.setSignature(n);
      readAttributes(method);
    } 
  }
  
  public final void skipAttribute(int paramInt) throws IOException {
    for (int i = 0; i < paramInt; i += j) {
      int k = (int)skip((paramInt - i));
      int j = k;
      if (k == 0) {
        if (read() < 0)
          throw new EOFException("EOF while reading class files attributes"); 
        j = 1;
      } 
    } 
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/gnu/bytecode/ClassFileInput.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */