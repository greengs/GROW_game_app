package gnu.bytecode;

import java.io.OutputStream;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.Writer;

public class ClassTypeWriter extends PrintWriter {
  public static final int PRINT_CONSTANT_POOL = 1;
  
  public static final int PRINT_CONSTANT_POOL_INDEXES = 2;
  
  public static final int PRINT_EXTRAS = 8;
  
  public static final int PRINT_VERBOSE = 15;
  
  public static final int PRINT_VERSION = 4;
  
  ClassType ctype;
  
  int flags;
  
  public ClassTypeWriter(ClassType paramClassType, OutputStream paramOutputStream, int paramInt) {
    super(paramOutputStream);
    this.ctype = paramClassType;
    this.flags = paramInt;
  }
  
  public ClassTypeWriter(ClassType paramClassType, Writer paramWriter, int paramInt) {
    super(paramWriter);
    this.ctype = paramClassType;
    this.flags = paramInt;
  }
  
  public static void print(ClassType paramClassType, PrintStream paramPrintStream, int paramInt) {
    ClassTypeWriter classTypeWriter = new ClassTypeWriter(paramClassType, paramPrintStream, paramInt);
    classTypeWriter.print();
    classTypeWriter.flush();
  }
  
  public static void print(ClassType paramClassType, PrintWriter paramPrintWriter, int paramInt) {
    ClassTypeWriter classTypeWriter = new ClassTypeWriter(paramClassType, paramPrintWriter, paramInt);
    classTypeWriter.print();
    classTypeWriter.flush();
  }
  
  CpoolEntry getCpoolEntry(int paramInt) {
    CpoolEntry[] arrayOfCpoolEntry = this.ctype.constants.pool;
    return (arrayOfCpoolEntry == null || paramInt < 0 || paramInt >= arrayOfCpoolEntry.length) ? null : arrayOfCpoolEntry[paramInt];
  }
  
  public void print() {
    if ((this.flags & 0x4) != 0) {
      print("Classfile format major version: ");
      print(this.ctype.getClassfileMajorVersion());
      print(", minor version: ");
      print(this.ctype.getClassfileMinorVersion());
      println('.');
    } 
    if ((this.flags & 0x1) != 0)
      printConstantPool(); 
    printClassInfo();
    printFields();
    printMethods();
    printAttributes();
  }
  
  public void print(ClassType paramClassType) {
    this.ctype = paramClassType;
    print();
  }
  
  public void printAttributes() {
    ClassType classType = this.ctype;
    println();
    print("Attributes (count: ");
    print(Attribute.count(classType));
    println("):");
    printAttributes(classType);
  }
  
  public void printAttributes(AttrContainer paramAttrContainer) {
    for (Attribute attribute = paramAttrContainer.getAttributes(); attribute != null; attribute = attribute.next)
      attribute.print(this); 
  }
  
  public void printClassInfo() {
    int i;
    println();
    print("Access flags:");
    print(Access.toString(this.ctype.getModifiers(), 'C'));
    println();
    print("This class: ");
    printOptionalIndex(this.ctype.thisClassIndex);
    printConstantTersely(this.ctype.thisClassIndex, 7);
    print(" super: ");
    if (this.ctype.superClassIndex == -1) {
      print("<unknown>");
    } else if (this.ctype.superClassIndex == 0) {
      print("0");
    } else {
      printOptionalIndex(this.ctype.superClassIndex);
      printConstantTersely(this.ctype.superClassIndex, 7);
    } 
    println();
    print("Interfaces (count: ");
    int[] arrayOfInt = this.ctype.interfaceIndexes;
    if (arrayOfInt == null) {
      i = 0;
    } else {
      i = arrayOfInt.length;
    } 
    print(i);
    print("):");
    println();
    for (int j = 0; j < i; j++) {
      print("- Implements: ");
      int k = arrayOfInt[j];
      printOptionalIndex(k);
      printConstantTersely(k, 7);
      println();
    } 
  }
  
  final void printConstantOperand(int paramInt) {
    print(' ');
    printOptionalIndex(paramInt);
    CpoolEntry[] arrayOfCpoolEntry = this.ctype.constants.pool;
    if (arrayOfCpoolEntry != null && paramInt >= 0 && paramInt < arrayOfCpoolEntry.length) {
      CpoolEntry cpoolEntry = arrayOfCpoolEntry[paramInt];
      if (cpoolEntry != null) {
        print('<');
        cpoolEntry.print(this, 1);
        print('>');
        return;
      } 
    } 
    print("<invalid constant index>");
  }
  
  public void printConstantPool() {
    CpoolEntry[] arrayOfCpoolEntry = this.ctype.constants.pool;
    int j = this.ctype.constants.count;
    for (int i = 1; i <= j; i++) {
      CpoolEntry cpoolEntry = arrayOfCpoolEntry[i];
      if (cpoolEntry != null) {
        print('#');
        print(cpoolEntry.index);
        print(": ");
        cpoolEntry.print(this, 2);
        println();
      } 
    } 
  }
  
  final void printConstantTersely(int paramInt1, int paramInt2) {
    printConstantTersely(getCpoolEntry(paramInt1), paramInt2);
  }
  
  final void printConstantTersely(CpoolEntry paramCpoolEntry, int paramInt) {
    if (paramCpoolEntry == null) {
      print("<invalid constant index>");
      return;
    } 
    if (paramCpoolEntry.getTag() != paramInt) {
      print("<unexpected constant type ");
      paramCpoolEntry.print(this, 1);
      print('>');
      return;
    } 
    paramCpoolEntry.print(this, 0);
  }
  
  final void printContantUtf8AsClass(int paramInt) {
    CpoolEntry cpoolEntry = getCpoolEntry(paramInt);
    if (cpoolEntry != null && cpoolEntry.getTag() == 1) {
      String str = ((CpoolUtf8)cpoolEntry).string;
      Type.printSignature(str, 0, str.length(), this);
      return;
    } 
    printConstantTersely(paramInt, 1);
  }
  
  public void printFields() {
    println();
    print("Fields (count: ");
    print(this.ctype.fields_count);
    print("):");
    println();
    int i = 0;
    for (Field field = this.ctype.fields; field != null; field = field.next) {
      print("Field name: ");
      if (field.name_index != 0)
        printOptionalIndex(field.name_index); 
      print(field.getName());
      print(Access.toString(field.flags, 'F'));
      print(" Signature: ");
      if (field.signature_index != 0)
        printOptionalIndex(field.signature_index); 
      printSignature(field.type);
      println();
      printAttributes(field);
      i++;
    } 
  }
  
  public void printMethod(Method paramMethod) {
    println();
    print("Method name:");
    if (paramMethod.name_index != 0)
      printOptionalIndex(paramMethod.name_index); 
    print('"');
    print(paramMethod.getName());
    print('"');
    print(Access.toString(paramMethod.access_flags, 'M'));
    print(" Signature: ");
    if (paramMethod.signature_index != 0)
      printOptionalIndex(paramMethod.signature_index); 
    print('(');
    for (int i = 0; i < paramMethod.arg_types.length; i++) {
      if (i > 0)
        print(','); 
      printSignature(paramMethod.arg_types[i]);
    } 
    print(')');
    printSignature(paramMethod.return_type);
    println();
    printAttributes(paramMethod);
  }
  
  public void printMethods() {
    println();
    print("Methods (count: ");
    print(this.ctype.methods_count);
    print("):");
    println();
    for (Method method = this.ctype.methods; method != null; method = method.next)
      printMethod(method); 
  }
  
  void printName(String paramString) {
    print(paramString);
  }
  
  public final void printOptionalIndex(int paramInt) {
    if ((this.flags & 0x2) != 0) {
      print('#');
      print(paramInt);
      print('=');
    } 
  }
  
  public final void printOptionalIndex(CpoolEntry paramCpoolEntry) {
    printOptionalIndex(paramCpoolEntry.index);
  }
  
  public final void printQuotedString(String paramString) {
    print('"');
    int j = paramString.length();
    int i = 0;
    label24: while (i < j) {
      char c = paramString.charAt(i);
      if (c == '"') {
        print("\\\"");
        continue;
      } 
      if (c >= ' ' && c < '') {
        print(c);
        continue;
      } 
      if (c == '\n') {
        print("\\n");
        continue;
      } 
      print("\\u");
      int k = 4;
      while (true) {
        if (--k >= 0) {
          print(Character.forDigit(c >> k * 4 & 0xF, 16));
          continue;
        } 
        i++;
        continue label24;
      } 
    } 
    print('"');
  }
  
  public final int printSignature(String paramString, int paramInt) {
    int j = paramString.length();
    if (paramInt >= j) {
      print("<empty signature>");
      return paramInt;
    } 
    int i = Type.signatureLength(paramString, paramInt);
    if (i > 0) {
      String str = Type.signatureToName(paramString.substring(paramInt, paramInt + i));
      if (str != null) {
        print(str);
        return paramInt + i;
      } 
    } 
    char c = paramString.charAt(paramInt);
    if (c != '(') {
      print(c);
      return paramInt + 1;
    } 
    i = paramInt + 1;
    print(c);
    for (paramInt = 0;; paramInt++) {
      if (i >= j) {
        print("<truncated method signature>");
        return i;
      } 
      c = paramString.charAt(i);
      if (c == ')') {
        print(c);
        return printSignature(paramString, i + 1);
      } 
      if (paramInt > 0)
        print(','); 
      i = printSignature(paramString, i);
    } 
  }
  
  public final void printSignature(Type paramType) {
    if (paramType == null) {
      print("<unknown type>");
      return;
    } 
    printSignature(paramType.getSignature());
  }
  
  public final void printSignature(String paramString) {
    int i = printSignature(paramString, 0);
    if (i < paramString.length()) {
      print("<trailing junk:");
      print(paramString.substring(i));
      print('>');
    } 
  }
  
  public void printSpaces(int paramInt) {
    while (true) {
      if (--paramInt >= 0) {
        print(' ');
        continue;
      } 
      break;
    } 
  }
  
  public void setClass(ClassType paramClassType) {
    this.ctype = paramClassType;
  }
  
  public void setFlags(int paramInt) {
    this.flags = paramInt;
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/gnu/bytecode/ClassTypeWriter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */