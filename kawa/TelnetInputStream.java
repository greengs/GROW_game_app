package kawa;

import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;

public class TelnetInputStream extends FilterInputStream {
  static final int SB_IAC = 400;
  
  protected byte[] buf = new byte[512];
  
  Telnet connection;
  
  int count;
  
  int pos;
  
  int state = 0;
  
  int subCommandLength = 0;
  
  public TelnetInputStream(InputStream paramInputStream, Telnet paramTelnet) throws IOException {
    super(paramInputStream);
    this.connection = paramTelnet;
  }
  
  public int read() throws IOException {
    while (true) {
      if (this.pos >= this.count) {
        int m = this.in.available();
        if (m <= 0) {
          k = 1;
        } else {
          k = m;
          if (m > this.buf.length - this.subCommandLength)
            k = this.buf.length - this.subCommandLength; 
        } 
        int k = this.in.read(this.buf, this.subCommandLength, k);
        this.pos = this.subCommandLength;
        this.count = k;
        if (k <= 0)
          return -1; 
      } 
      byte[] arrayOfByte = this.buf;
      int i = this.pos;
      this.pos = i + 1;
      int j = arrayOfByte[i] & 0xFF;
      if (this.state == 0) {
        i = j;
        if (j == 255) {
          this.state = 255;
          continue;
        } 
        return i;
      } 
      if (this.state == 255) {
        if (j == 255) {
          this.state = 0;
          return 255;
        } 
        if (j == 251 || j == 252 || j == 253 || j == 254 || j == 250) {
          this.state = j;
          continue;
        } 
        if (j == 244) {
          System.err.println("Interrupt Process");
          this.state = 0;
          continue;
        } 
        if (j == 236)
          return -1; 
        this.state = 0;
        continue;
      } 
      if (this.state == 251 || this.state == 252 || this.state == 253 || this.state == 254) {
        this.connection.handle(this.state, j);
        this.state = 0;
        continue;
      } 
      if (this.state == 250) {
        if (j == 255) {
          this.state = 400;
          continue;
        } 
        arrayOfByte = this.buf;
        i = this.subCommandLength;
        this.subCommandLength = i + 1;
        arrayOfByte[i] = (byte)j;
        continue;
      } 
      if (this.state == 400) {
        if (j == 255) {
          arrayOfByte = this.buf;
          i = this.subCommandLength;
          this.subCommandLength = i + 1;
          arrayOfByte[i] = (byte)j;
          this.state = 250;
          continue;
        } 
        if (j == 240) {
          this.connection.subCommand(this.buf, 0, this.subCommandLength);
          this.state = 0;
          this.subCommandLength = 0;
          continue;
        } 
        this.state = 0;
        this.subCommandLength = 0;
        continue;
      } 
      System.err.println("Bad state " + this.state);
    } 
  }
  
  public int read(byte[] paramArrayOfbyte, int paramInt1, int paramInt2) throws IOException {
    // Byte code:
    //   0: iload_3
    //   1: ifgt -> 10
    //   4: iconst_0
    //   5: istore #5
    //   7: iload #5
    //   9: ireturn
    //   10: iconst_0
    //   11: istore #5
    //   13: aload_0
    //   14: getfield state : I
    //   17: ifne -> 34
    //   20: iload_2
    //   21: istore #6
    //   23: aload_0
    //   24: getfield pos : I
    //   27: aload_0
    //   28: getfield count : I
    //   31: if_icmplt -> 65
    //   34: aload_0
    //   35: invokevirtual read : ()I
    //   38: istore #6
    //   40: iload #6
    //   42: istore #5
    //   44: iload #6
    //   46: iflt -> 7
    //   49: aload_1
    //   50: iload_2
    //   51: iload #6
    //   53: i2b
    //   54: bastore
    //   55: iconst_0
    //   56: iconst_1
    //   57: iadd
    //   58: istore #5
    //   60: iload_2
    //   61: iconst_1
    //   62: iadd
    //   63: istore #6
    //   65: iload #5
    //   67: istore_2
    //   68: aload_0
    //   69: getfield state : I
    //   72: ifne -> 118
    //   75: iload #5
    //   77: istore_2
    //   78: aload_0
    //   79: getfield pos : I
    //   82: aload_0
    //   83: getfield count : I
    //   86: if_icmpge -> 118
    //   89: iload #5
    //   91: istore_2
    //   92: iload #5
    //   94: iload_3
    //   95: if_icmpge -> 118
    //   98: aload_0
    //   99: getfield buf : [B
    //   102: aload_0
    //   103: getfield pos : I
    //   106: baload
    //   107: istore #4
    //   109: iload #4
    //   111: iconst_m1
    //   112: if_icmpne -> 120
    //   115: iload #5
    //   117: istore_2
    //   118: iload_2
    //   119: ireturn
    //   120: aload_1
    //   121: iload #6
    //   123: iload #4
    //   125: bastore
    //   126: iload #5
    //   128: iconst_1
    //   129: iadd
    //   130: istore #5
    //   132: aload_0
    //   133: aload_0
    //   134: getfield pos : I
    //   137: iconst_1
    //   138: iadd
    //   139: putfield pos : I
    //   142: iload #6
    //   144: iconst_1
    //   145: iadd
    //   146: istore #6
    //   148: goto -> 75
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/kawa/TelnetInputStream.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */