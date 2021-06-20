package gnu.bytecode;

import java.io.DataOutputStream;
import java.io.IOException;

public class SourceFileAttr extends Attribute {
  String filename;
  
  int filename_index;
  
  public SourceFileAttr(int paramInt, ClassType paramClassType) {
    super("SourceFile");
    this.filename = ((CpoolUtf8)paramClassType.constants.getForced(paramInt, 1)).string;
    this.filename_index = paramInt;
  }
  
  public SourceFileAttr(String paramString) {
    super("SourceFile");
    this.filename = paramString;
  }
  
  public static String fixSourceFile(String paramString) {
    String str2 = System.getProperty("file.separator", "/");
    String str1 = paramString;
    if (str2 != null) {
      str1 = paramString;
      if (str2.length() == 1) {
        char c = str2.charAt(0);
        str1 = paramString;
        if (c != '/')
          str1 = paramString.replace(c, '/'); 
      } 
    } 
    return str1;
  }
  
  public static void setSourceFile(ClassType paramClassType, String paramString) {
    Attribute attribute = Attribute.get(paramClassType, "SourceFile");
    if (attribute != null && attribute instanceof SourceFileAttr) {
      ((SourceFileAttr)attribute).setSourceFile(paramString);
      return;
    } 
    (new SourceFileAttr(paramString)).addToFrontOf(paramClassType);
  }
  
  public void assignConstants(ClassType paramClassType) {
    super.assignConstants(paramClassType);
    if (this.filename_index == 0)
      this.filename_index = paramClassType.getConstants().addUtf8(this.filename).getIndex(); 
  }
  
  public final int getLength() {
    return 2;
  }
  
  public String getSourceFile() {
    return this.filename;
  }
  
  public void print(ClassTypeWriter paramClassTypeWriter) {
    paramClassTypeWriter.print("Attribute \"");
    paramClassTypeWriter.print(getName());
    paramClassTypeWriter.print("\", length:");
    paramClassTypeWriter.print(getLength());
    paramClassTypeWriter.print(", ");
    paramClassTypeWriter.printOptionalIndex(this.filename_index);
    paramClassTypeWriter.print('"');
    paramClassTypeWriter.print(getSourceFile());
    paramClassTypeWriter.println('"');
  }
  
  public void setSourceFile(String paramString) {
    this.filename = paramString;
    this.filename_index = 0;
  }
  
  public void write(DataOutputStream paramDataOutputStream) throws IOException {
    paramDataOutputStream.writeShort(this.filename_index);
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/gnu/bytecode/SourceFileAttr.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */