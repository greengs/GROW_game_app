package gnu.mapping;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.FilterWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;

public class LogWriter extends FilterWriter {
  private Writer log;
  
  public LogWriter(Writer paramWriter) {
    super(paramWriter);
  }
  
  public void close() throws IOException {
    if (this.log != null)
      this.log.close(); 
    super.close();
  }
  
  public void closeLogFile() throws IOException {
    if (this.log != null)
      this.log.close(); 
    this.log = null;
  }
  
  public void echo(char[] paramArrayOfchar, int paramInt1, int paramInt2) throws IOException {
    if (this.log != null)
      this.log.write(paramArrayOfchar, paramInt1, paramInt2); 
  }
  
  public void flush() throws IOException {
    if (this.log != null)
      this.log.flush(); 
    super.flush();
  }
  
  public final Writer getLogFile() {
    return this.log;
  }
  
  public void setLogFile(Writer paramWriter) {
    this.log = paramWriter;
  }
  
  public void setLogFile(String paramString) throws IOException {
    this.log = new PrintWriter(new BufferedWriter(new FileWriter(paramString)));
  }
  
  public void write(int paramInt) throws IOException {
    if (this.log != null)
      this.log.write(paramInt); 
    super.write(paramInt);
  }
  
  public void write(String paramString, int paramInt1, int paramInt2) throws IOException {
    if (this.log != null)
      this.log.write(paramString, paramInt1, paramInt2); 
    super.write(paramString, paramInt1, paramInt2);
  }
  
  public void write(char[] paramArrayOfchar, int paramInt1, int paramInt2) throws IOException {
    if (this.log != null)
      this.log.write(paramArrayOfchar, paramInt1, paramInt2); 
    super.write(paramArrayOfchar, paramInt1, paramInt2);
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/gnu/mapping/LogWriter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */