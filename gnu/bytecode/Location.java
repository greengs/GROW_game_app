package gnu.bytecode;

public class Location {
  protected String name;
  
  int name_index;
  
  int signature_index;
  
  protected Type type;
  
  public final String getName() {
    return this.name;
  }
  
  public final String getSignature() {
    return this.type.getSignature();
  }
  
  public final Type getType() {
    return this.type;
  }
  
  public final void setName(int paramInt, ConstantPool paramConstantPool) {
    if (paramInt <= 0) {
      this.name = null;
    } else {
      this.name = ((CpoolUtf8)paramConstantPool.getForced(paramInt, 1)).string;
    } 
    this.name_index = paramInt;
  }
  
  public final void setName(String paramString) {
    this.name = paramString;
  }
  
  public void setSignature(int paramInt, ConstantPool paramConstantPool) {
    CpoolUtf8 cpoolUtf8 = (CpoolUtf8)paramConstantPool.getForced(paramInt, 1);
    this.signature_index = paramInt;
    this.type = Type.signatureToType(cpoolUtf8.string);
  }
  
  public final void setType(Type paramType) {
    this.type = paramType;
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/gnu/bytecode/Location.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */