package kawa;

import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class TelnetOutputStream extends FilterOutputStream {
  public TelnetOutputStream(OutputStream paramOutputStream) {
    super(paramOutputStream);
  }
  
  public void write(int paramInt) throws IOException {
    if (paramInt == 255)
      this.out.write(paramInt); 
    this.out.write(paramInt);
  }
  
  public void write(byte[] paramArrayOfbyte) throws IOException {
    write(paramArrayOfbyte, 0, paramArrayOfbyte.length);
  }
  
  public void write(byte[] paramArrayOfbyte, int paramInt1, int paramInt2) throws IOException {
    int j = paramInt1 + paramInt2;
    int i = paramInt1;
    paramInt2 = paramInt1;
    paramInt1 = i;
    while (paramInt1 < j) {
      i = paramInt2;
      if (paramArrayOfbyte[paramInt1] == -1) {
        this.out.write(paramArrayOfbyte, paramInt2, paramInt1 + 1 - paramInt2);
        i = paramInt1;
      } 
      paramInt1++;
      paramInt2 = i;
    } 
    this.out.write(paramArrayOfbyte, paramInt2, j - paramInt2);
  }
  
  public void writeCommand(int paramInt) throws IOException {
    this.out.write(255);
    this.out.write(paramInt);
  }
  
  public final void writeCommand(int paramInt1, int paramInt2) throws IOException {
    this.out.write(255);
    this.out.write(paramInt1);
    this.out.write(paramInt2);
  }
  
  public final void writeDo(int paramInt) throws IOException {
    writeCommand(253, paramInt);
  }
  
  public final void writeDont(int paramInt) throws IOException {
    writeCommand(254, paramInt);
  }
  
  public final void writeSubCommand(int paramInt, byte[] paramArrayOfbyte) throws IOException {
    writeCommand(250, paramInt);
    write(paramArrayOfbyte);
    writeCommand(240);
  }
  
  public final void writeWill(int paramInt) throws IOException {
    writeCommand(251, paramInt);
  }
  
  public final void writeWont(int paramInt) throws IOException {
    writeCommand(252, paramInt);
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/kawa/TelnetOutputStream.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */