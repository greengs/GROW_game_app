package gnu.lists;

import java.io.Writer;

public class ConsumerWriter extends Writer {
  protected Consumer out;
  
  public ConsumerWriter(Consumer paramConsumer) {
    this.out = paramConsumer;
  }
  
  public void close() {
    flush();
  }
  
  public void finalize() {
    close();
  }
  
  public void flush() {}
  
  public void write(char[] paramArrayOfchar, int paramInt1, int paramInt2) {
    this.out.write(paramArrayOfchar, paramInt1, paramInt2);
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/gnu/lists/ConsumerWriter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */