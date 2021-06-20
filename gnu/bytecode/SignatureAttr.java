package gnu.bytecode;

import java.io.DataOutputStream;
import java.io.IOException;

public class SignatureAttr extends Attribute {
  String signature;
  
  int signature_index;
  
  public SignatureAttr(int paramInt, Member paramMember) {
    super("Signature");
    if (paramMember instanceof ClassType) {
      paramMember = paramMember;
    } else {
      paramMember = paramMember.getDeclaringClass();
    } 
    this.signature = ((CpoolUtf8)((ClassType)paramMember).constants.getForced(paramInt, 1)).string;
    this.signature_index = paramInt;
  }
  
  public SignatureAttr(String paramString) {
    super("Signature");
    this.signature = paramString;
  }
  
  public void assignConstants(ClassType paramClassType) {
    super.assignConstants(paramClassType);
    if (this.signature_index == 0)
      this.signature_index = paramClassType.getConstants().addUtf8(this.signature).getIndex(); 
  }
  
  public final int getLength() {
    return 2;
  }
  
  public final String getSignature() {
    return this.signature;
  }
  
  public void print(ClassTypeWriter paramClassTypeWriter) {
    super.print(paramClassTypeWriter);
    paramClassTypeWriter.print("  ");
    paramClassTypeWriter.printOptionalIndex(this.signature_index);
    paramClassTypeWriter.print('"');
    paramClassTypeWriter.print(getSignature());
    paramClassTypeWriter.println('"');
  }
  
  protected void setSignature(String paramString) {
    this.signature = paramString;
    this.signature_index = 0;
  }
  
  public void write(DataOutputStream paramDataOutputStream) throws IOException {
    paramDataOutputStream.writeShort(this.signature_index);
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/gnu/bytecode/SignatureAttr.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */