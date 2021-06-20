package gnu.lists;

public class U8Vector extends ByteVector {
  public U8Vector() {
    this.data = ByteVector.empty;
  }
  
  public U8Vector(int paramInt) {
    this.data = new byte[paramInt];
    this.size = paramInt;
  }
  
  public U8Vector(int paramInt, byte paramByte) {
    byte[] arrayOfByte = new byte[paramInt];
    this.data = arrayOfByte;
    this.size = paramInt;
    while (true) {
      if (--paramInt >= 0) {
        arrayOfByte[paramInt] = paramByte;
        continue;
      } 
      break;
    } 
  }
  
  public U8Vector(Sequence paramSequence) {
    this.data = new byte[paramSequence.size()];
    addAll(paramSequence);
  }
  
  public U8Vector(byte[] paramArrayOfbyte) {
    this.data = paramArrayOfbyte;
    this.size = paramArrayOfbyte.length;
  }
  
  public int compareTo(Object paramObject) {
    return compareToInt(this, (U8Vector)paramObject);
  }
  
  public final Object get(int paramInt) {
    if (paramInt > this.size)
      throw new IndexOutOfBoundsException(); 
    return Convert.toObjectUnsigned(this.data[paramInt]);
  }
  
  public final Object getBuffer(int paramInt) {
    return Convert.toObjectUnsigned(this.data[paramInt]);
  }
  
  public int getElementKind() {
    return 17;
  }
  
  public String getTag() {
    return "u8";
  }
  
  public final int intAtBuffer(int paramInt) {
    return this.data[paramInt] & 0xFF;
  }
  
  public Object setBuffer(int paramInt, Object paramObject) {
    byte b = this.data[paramInt];
    this.data[paramInt] = Convert.toByteUnsigned(paramObject);
    return Convert.toObjectUnsigned(b);
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/gnu/lists/U8Vector.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */