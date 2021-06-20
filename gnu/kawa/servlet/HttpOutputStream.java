package gnu.kawa.servlet;

import java.io.IOException;
import java.io.OutputStream;

class HttpOutputStream extends OutputStream {
  byte[] buffer;
  
  HttpRequestContext context;
  
  int count;
  
  OutputStream out;
  
  public HttpOutputStream(HttpRequestContext paramHttpRequestContext, int paramInt) {
    this.context = paramHttpRequestContext;
    this.buffer = new byte[paramInt];
  }
  
  public void close() throws IOException {
    if (this.out == null) {
      maybeSendResponseHeaders(this.count);
      this.out = this.context.getResponseStream();
    } 
    flush();
    this.out.close();
  }
  
  public void flush() throws IOException {
    if (this.out == null) {
      maybeSendResponseHeaders(-1);
      this.out = this.context.getResponseStream();
    } 
    if (this.count > 0) {
      this.out.write(this.buffer, 0, this.count);
      this.count = 0;
    } 
  }
  
  void maybeSendResponseHeaders(int paramInt) throws IOException {
    int i = this.context.statusCode;
    if (i != -999) {
      this.context.sendResponseHeaders(i, this.context.statusReasonPhrase, paramInt);
      this.context.statusCode = -999;
    } 
  }
  
  public boolean reset() {
    boolean bool = false;
    this.count = 0;
    if (this.out == null)
      bool = true; 
    return bool;
  }
  
  public void write(int paramInt) throws IOException {
    if (this.count >= this.buffer.length)
      flush(); 
    byte[] arrayOfByte = this.buffer;
    int i = this.count;
    this.count = i + 1;
    arrayOfByte[i] = (byte)paramInt;
  }
  
  public void write(byte[] paramArrayOfbyte, int paramInt1, int paramInt2) throws IOException {
    int j = this.buffer.length - this.count;
    int i = paramInt1;
    for (paramInt1 = j; paramInt2 > paramInt1; paramInt1 = this.buffer.length) {
      System.arraycopy(paramArrayOfbyte, i, this.buffer, this.count, paramInt1);
      this.count += paramInt1;
      flush();
      i += paramInt1;
      paramInt2 -= paramInt1;
    } 
    if (paramInt2 > 0) {
      System.arraycopy(paramArrayOfbyte, i, this.buffer, this.count, paramInt2);
      this.count += paramInt2;
    } 
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/gnu/kawa/servlet/HttpOutputStream.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */