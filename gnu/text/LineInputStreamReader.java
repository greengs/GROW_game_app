package gnu.text;

import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CoderResult;

public class LineInputStreamReader extends LineBufferedReader {
  byte[] barr = new byte[8192];
  
  ByteBuffer bbuf = ByteBuffer.wrap(this.barr);
  
  char[] carr;
  
  CharBuffer cbuf = null;
  
  Charset cset;
  
  CharsetDecoder decoder;
  
  InputStream istrm;
  
  public LineInputStreamReader(InputStream paramInputStream) {
    super((Reader)null);
    this.bbuf.position(this.barr.length);
    this.istrm = paramInputStream;
  }
  
  private int fillBytes(int paramInt) throws IOException {
    int i = 0;
    int j = this.istrm.read(this.barr, paramInt, this.barr.length - paramInt);
    this.bbuf.position(0);
    ByteBuffer byteBuffer = this.bbuf;
    if (j >= 0)
      i = j; 
    byteBuffer.limit(i + paramInt);
    return j;
  }
  
  public void close() throws IOException {
    if (this.in != null)
      this.in.close(); 
    this.istrm.close();
  }
  
  public int fill(int paramInt) throws IOException {
    if (this.cset == null)
      setCharset("UTF-8"); 
    if (this.buffer != this.carr) {
      this.cbuf = CharBuffer.wrap(this.buffer);
      this.carr = this.buffer;
    } 
    this.cbuf.limit(this.pos + paramInt);
    this.cbuf.position(this.pos);
    int i = 0;
    while (true) {
      CoderResult coderResult = this.decoder.decode(this.bbuf, this.cbuf, false);
      int j = this.cbuf.position() - this.pos;
      paramInt = i;
      if (j <= 0)
        if (!coderResult.isUnderflow()) {
          paramInt = i;
        } else {
          paramInt = this.bbuf.remaining();
          if (paramInt > 0)
            this.bbuf.compact(); 
          if (fillBytes(paramInt) < 0) {
            paramInt = 1;
          } else {
            continue;
          } 
        }  
      i = j;
      if (j == 0) {
        i = j;
        if (paramInt != 0)
          i = -1; 
      } 
      return i;
    } 
  }
  
  public int getByte() throws IOException {
    return (!this.bbuf.hasRemaining() && fillBytes(0) <= 0) ? -1 : (this.bbuf.get() & 0xFF);
  }
  
  public void markStart() throws IOException {}
  
  public boolean ready() throws IOException {
    return (this.pos < this.limit || this.bbuf.hasRemaining() || this.istrm.available() > 0);
  }
  
  public void resetStart(int paramInt) throws IOException {
    this.bbuf.position(paramInt);
  }
  
  public void setCharset(String paramString) {
    Charset charset = Charset.forName(paramString);
    if (this.cset == null) {
      setCharset(charset);
      return;
    } 
    if (!charset.equals(this.cset))
      throw new RuntimeException("encoding " + paramString + " does not match previous " + this.cset); 
  }
  
  public void setCharset(Charset paramCharset) {
    this.cset = paramCharset;
    this.decoder = paramCharset.newDecoder();
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/gnu/text/LineInputStreamReader.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */