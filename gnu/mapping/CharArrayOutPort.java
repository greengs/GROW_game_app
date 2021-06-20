package gnu.mapping;

import gnu.lists.Consumer;
import java.io.Writer;

public class CharArrayOutPort extends OutPort {
  public CharArrayOutPort() {
    super((Writer)null, false, CharArrayInPort.stringPath);
  }
  
  public void close() {}
  
  protected boolean closeOnExit() {
    return false;
  }
  
  public int length() {
    return this.bout.bufferFillPointer;
  }
  
  public void reset() {
    this.bout.bufferFillPointer = 0;
  }
  
  public void setLength(int paramInt) {
    this.bout.bufferFillPointer = paramInt;
  }
  
  public char[] toCharArray() {
    int i = this.bout.bufferFillPointer;
    char[] arrayOfChar = new char[i];
    System.arraycopy(this.bout.buffer, 0, arrayOfChar, 0, i);
    return arrayOfChar;
  }
  
  public String toString() {
    return new String(this.bout.buffer, 0, this.bout.bufferFillPointer);
  }
  
  public String toSubString(int paramInt) {
    return new String(this.bout.buffer, paramInt, this.bout.bufferFillPointer - paramInt);
  }
  
  public String toSubString(int paramInt1, int paramInt2) {
    if (paramInt2 > this.bout.bufferFillPointer)
      throw new IndexOutOfBoundsException(); 
    return new String(this.bout.buffer, paramInt1, paramInt2 - paramInt1);
  }
  
  public void writeTo(int paramInt1, int paramInt2, Consumer paramConsumer) {
    paramConsumer.write(this.bout.buffer, paramInt1, paramInt2);
  }
  
  public void writeTo(Consumer paramConsumer) {
    paramConsumer.write(this.bout.buffer, 0, this.bout.bufferFillPointer);
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/gnu/mapping/CharArrayOutPort.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */