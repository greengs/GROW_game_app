package gnu.bytecode;

public class RuntimeAnnotationsAttr extends MiscAttr {
  int numEntries;
  
  public RuntimeAnnotationsAttr(String paramString, byte[] paramArrayOfbyte, AttrContainer paramAttrContainer) {
    super(paramString, paramArrayOfbyte, 0, paramArrayOfbyte.length);
    addToFrontOf(paramAttrContainer);
    this.numEntries = u2(0);
  }
  
  public void print(ClassTypeWriter paramClassTypeWriter) {
    paramClassTypeWriter.print("Attribute \"");
    paramClassTypeWriter.print(getName());
    paramClassTypeWriter.print("\", length:");
    paramClassTypeWriter.print(getLength());
    paramClassTypeWriter.print(", number of entries: ");
    paramClassTypeWriter.println(this.numEntries);
    int j = this.offset;
    this.offset = j + 2;
    for (int i = 0; i < this.numEntries; i++)
      printAnnotation(2, paramClassTypeWriter); 
    this.offset = j;
  }
  
  public void printAnnotation(int paramInt, ClassTypeWriter paramClassTypeWriter) {
    int i = u2();
    paramClassTypeWriter.printSpaces(paramInt);
    paramClassTypeWriter.printOptionalIndex(i);
    paramClassTypeWriter.print('@');
    paramClassTypeWriter.printContantUtf8AsClass(i);
    i = u2();
    paramClassTypeWriter.println();
    int j = paramInt + 2;
    for (paramInt = 0; paramInt < i; paramInt++) {
      int k = u2();
      paramClassTypeWriter.printSpaces(j);
      paramClassTypeWriter.printOptionalIndex(k);
      paramClassTypeWriter.printConstantTersely(k, 1);
      paramClassTypeWriter.print(" => ");
      printAnnotationElementValue(j, paramClassTypeWriter);
      paramClassTypeWriter.println();
    } 
  }
  
  public void printAnnotationElementValue(int paramInt, ClassTypeWriter paramClassTypeWriter) {
    CpoolEntry cpoolEntry;
    int m = u1();
    if ((paramClassTypeWriter.flags & 0x8) != 0) {
      paramClassTypeWriter.print("[kind:");
      if (m >= 65 && m <= 122) {
        paramClassTypeWriter.print((char)m);
      } else {
        paramClassTypeWriter.print(m);
      } 
      paramClassTypeWriter.print("] ");
    } 
    int i = 0;
    int j = 0;
    int k = 0;
    byte b2 = 0;
    byte b1 = b2;
    switch (m) {
      default:
        return;
      case 66:
      case 67:
      case 73:
      case 83:
      case 90:
        b1 = b2;
        if (!false)
          b1 = 3; 
      case 74:
        i = b1;
        if (b1 == 0)
          i = 5; 
      case 68:
        j = i;
        if (i == 0)
          j = 6; 
      case 70:
        k = j;
        if (j == 0)
          k = 4; 
      case 115:
        paramInt = k;
        if (k == 0)
          paramInt = 1; 
        i = u2();
        cpoolEntry = paramClassTypeWriter.getCpoolEntry(i);
        paramClassTypeWriter.printOptionalIndex(cpoolEntry);
        if (m == 90 && cpoolEntry != null && cpoolEntry.getTag() == 3) {
          cpoolEntry = cpoolEntry;
          if (((CpoolValue1)cpoolEntry).value == 0 || ((CpoolValue1)cpoolEntry).value == 1) {
            String str;
            if (((CpoolValue1)cpoolEntry).value == 0) {
              str = "false";
            } else {
              str = "true";
            } 
            paramClassTypeWriter.print(str);
            return;
          } 
        } 
        paramClassTypeWriter.printConstantTersely(i, paramInt);
        return;
      case 101:
        paramInt = u2();
        i = u2();
        paramClassTypeWriter.print("enum[");
        if ((paramClassTypeWriter.flags & 0x8) != 0)
          paramClassTypeWriter.print("type:"); 
        paramClassTypeWriter.printOptionalIndex(paramInt);
        paramClassTypeWriter.printContantUtf8AsClass(paramInt);
        if ((paramClassTypeWriter.flags & 0x8) != 0) {
          paramClassTypeWriter.print(" value:");
        } else {
          paramClassTypeWriter.print(' ');
        } 
        paramClassTypeWriter.printOptionalIndex(i);
        paramClassTypeWriter.printConstantTersely(i, 1);
        paramClassTypeWriter.print("]");
        return;
      case 99:
        paramInt = u2();
        paramClassTypeWriter.printOptionalIndex(paramInt);
        paramClassTypeWriter.printContantUtf8AsClass(paramInt);
        return;
      case 64:
        paramClassTypeWriter.println();
        paramClassTypeWriter.printSpaces(paramInt + 2);
        printAnnotation(paramInt + 2, paramClassTypeWriter);
        return;
      case 91:
        break;
    } 
    j = u2();
    paramClassTypeWriter.print("array length:");
    paramClassTypeWriter.print(j);
    i = 0;
    while (true) {
      if (i < j) {
        paramClassTypeWriter.println();
        paramClassTypeWriter.printSpaces(paramInt + 2);
        paramClassTypeWriter.print(i);
        paramClassTypeWriter.print(": ");
        printAnnotationElementValue(paramInt + 2, paramClassTypeWriter);
        i++;
      } 
    } 
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/gnu/bytecode/RuntimeAnnotationsAttr.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */