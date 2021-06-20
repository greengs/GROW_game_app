package gnu.bytecode;

import java.io.DataOutputStream;
import java.io.IOException;

public abstract class Attribute {
  AttrContainer container;
  
  String name;
  
  int name_index;
  
  Attribute next;
  
  public Attribute(String paramString) {
    this.name = paramString;
  }
  
  public static void assignConstants(AttrContainer paramAttrContainer, ClassType paramClassType) {
    for (Attribute attribute = paramAttrContainer.getAttributes(); attribute != null; attribute = attribute.next) {
      if (!attribute.isSkipped())
        attribute.assignConstants(paramClassType); 
    } 
  }
  
  public static int count(AttrContainer paramAttrContainer) {
    int i = 0;
    Attribute attribute = paramAttrContainer.getAttributes();
    while (attribute != null) {
      int j = i;
      if (!attribute.isSkipped())
        j = i + 1; 
      attribute = attribute.next;
      i = j;
    } 
    return i;
  }
  
  public static Attribute get(AttrContainer paramAttrContainer, String paramString) {
    for (Attribute attribute = paramAttrContainer.getAttributes(); attribute != null; attribute = attribute.next) {
      if (attribute.getName() == paramString)
        return attribute; 
    } 
    return null;
  }
  
  public static int getLengthAll(AttrContainer paramAttrContainer) {
    int i = 0;
    Attribute attribute = paramAttrContainer.getAttributes();
    while (attribute != null) {
      int j = i;
      if (!attribute.isSkipped())
        j = i + attribute.getLength() + 6; 
      attribute = attribute.next;
      i = j;
    } 
    return i;
  }
  
  public static void writeAll(AttrContainer paramAttrContainer, DataOutputStream paramDataOutputStream) throws IOException {
    paramDataOutputStream.writeShort(count(paramAttrContainer));
    for (Attribute attribute = paramAttrContainer.getAttributes(); attribute != null; attribute = attribute.next) {
      if (!attribute.isSkipped()) {
        if (attribute.name_index == 0)
          throw new Error("Attribute.writeAll called without assignConstants"); 
        paramDataOutputStream.writeShort(attribute.name_index);
        paramDataOutputStream.writeInt(attribute.getLength());
        attribute.write(paramDataOutputStream);
      } 
    } 
  }
  
  public void addToFrontOf(AttrContainer paramAttrContainer) {
    setContainer(paramAttrContainer);
    setNext(paramAttrContainer.getAttributes());
    paramAttrContainer.setAttributes(this);
  }
  
  public void assignConstants(ClassType paramClassType) {
    if (this.name_index == 0)
      this.name_index = paramClassType.getConstants().addUtf8(this.name).getIndex(); 
  }
  
  public final AttrContainer getContainer() {
    return this.container;
  }
  
  public abstract int getLength();
  
  public final String getName() {
    return this.name;
  }
  
  public final int getNameIndex() {
    return this.name_index;
  }
  
  public final Attribute getNext() {
    return this.next;
  }
  
  public final boolean isSkipped() {
    return (this.name_index < 0);
  }
  
  public void print(ClassTypeWriter paramClassTypeWriter) {
    paramClassTypeWriter.print("Attribute \"");
    paramClassTypeWriter.print(getName());
    paramClassTypeWriter.print("\", length:");
    paramClassTypeWriter.println(getLength());
  }
  
  public final void setContainer(AttrContainer paramAttrContainer) {
    this.container = paramAttrContainer;
  }
  
  public final void setName(String paramString) {
    this.name = paramString.intern();
  }
  
  public final void setNameIndex(int paramInt) {
    this.name_index = paramInt;
  }
  
  public final void setNext(Attribute paramAttribute) {
    this.next = paramAttribute;
  }
  
  public final void setSkipped() {
    this.name_index = -1;
  }
  
  public final void setSkipped(boolean paramBoolean) {
    boolean bool;
    if (paramBoolean) {
      bool = true;
    } else {
      bool = false;
    } 
    this.name_index = bool;
  }
  
  public abstract void write(DataOutputStream paramDataOutputStream) throws IOException;
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/gnu/bytecode/Attribute.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */